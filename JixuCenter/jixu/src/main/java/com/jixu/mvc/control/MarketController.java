package com.jixu.mvc.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jixu.common.controller.ExceptionControl;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.func.SystemSelect;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.FutureTypes;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.TradeMarket;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.CouponData;
import com.jixu.common.types.InterfaceAutoType;
import com.jixu.common.types.PriceDecideType;
import com.jixu.common.types.SwapSetPrincipalType;
import com.jixu.common.util.DataConvert;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.JsonUtil;
import com.jixu.common.util.Pages;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.SecContractDao;
import com.jixu.dao.intf.TranCloDao;
import com.jixu.dao.intf.TranDao;
import com.jixu.framework.msg.MsgFactory;
import com.jixu.mvc.control.model.MarketFxRateModel;
import com.jixu.mvc.control.model.MarketPriceModel;
import com.jixu.mvc.control.model.MarketStockDetailModel;
import com.jixu.mvc.control.model.MarketStockModel;
import com.jixu.mvc.control.service.MarketDataService;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.SecContract;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranClo;

@Controller
public class MarketController extends ExceptionControl{
	
	@Resource(name="marketDataService")
	private MarketDataService marketDataService;
	
	@Resource(name="msgFactory")
	private MsgFactory msgFactory;
	
	@Resource(name="commfuncService")
	private CommFuncService commService;
	
	@Autowired
	private SecContractDao secContractDao;
	
	@Autowired
	protected IssueInfoDao issueInfoDao;
	
	@Autowired
	private TranCloDao tranCloDao;
	
	@Autowired
	private TranDao tranDao;
	
	@RequestMapping(value="/market.do", method = RequestMethod.GET, name = "市场-页面显示")
	public String market() {
		return "market";
	}
	
	@RequestMapping(value="/market/price.do", method = RequestMethod.POST, name = "价格-查询")  
    public void priceList(@RequestParam(value = "config", required = true) String resources,
    					  @RequestParam(value = "smf", required = true) String smf,
    					  @RequestParam(value = "start", required = true) String start,
    					  @RequestParam(value = "end", required = true) String end,
    					  @RequestParam(value = "num", required = true) int num,
    					  HttpServletResponse response) {
		List<Object> sourceIDList = JsonUtil.stringToList(resources);
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("数据编号", "数据来源", "日期", "证券代码", "证券名称", "货币", "价格", "收益率","定价到");
		result.put("titles", titles);
		
		List<Object> data = new ArrayList<Object>();
		if(!start.equals("")){
			start=start.replaceAll("-","");
		}
		if(!end.equals("")){
			end=end.replaceAll("-", "");
		}
		
		String[] secidArr=smf.split("\\|");
		String secid="";
		if(secidArr.length>1){
			secid=secidArr[1];
		}else if(secidArr.length==1){
			secid=secidArr[0];
		}
		
		int[] sources=new int[sourceIDList.size()];
		for(int i=0;i<sourceIDList.size();i++){
			sources[i]=Integer.valueOf(sourceIDList.get(i).toString());
		}
		
		Date startDate=DateUtil.convertStr2DateAll(start);
		Date endDate=DateUtil.convertStr2DateAll(end);
		List<MarketPriceModel> priceList=null;
		int totalPages=0;
		//pageHelper分页
		if(SystemSelect.getSystemDBFrameWork()==1){
			int pageSize=30;
			Page<MarketPriceModel> page=marketDataService.loadPageHelperPriceList(PageHelper.startPage(num, pageSize), sources, secid, startDate, endDate);
			priceList=page.getResult();
			totalPages=page.getPages();
		}else{
			//hibernate分页
			Pages<MarketPriceModel> pages=new Pages<MarketPriceModel>(num);
			pages=marketDataService.loadPriceList(pages, sources, secid, startDate, endDate);
			priceList=pages.getResultList();
			totalPages=pages.getTotalPages();
		}
		
		for(MarketPriceModel price:priceList){
			List<Object> item = new ArrayList<Object>();
			
			item.add(price.getPriceid());
			item.add(price.getSourcename());
			item.add(DateUtil.convertD2YYYY_MM_DD(price.getPriceDate()));
			item.add(price.getSecid());
			item.add(price.getSecname());
			item.add(price.getCurency());
			item.add(price.getPrice());
			item.add(DataConvert.convertCashNoCalFormat(price.getYield()));
			item.add(PriceDecideType.priceDesideHM.get(price.getPriceto()));
			data.add(item);
		}
		
		result.put("data", data);
		result.put("pages", totalPages);	
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/market/ex.do", method = RequestMethod.POST, name="汇率-查询")  
    public void exchangeRateList(@RequestParam(value = "config", required = true) String resources,
    					  @RequestParam(value = "baseCurrency", required = true) String baseCurrency,
    					  @RequestParam(value = "localCurrency", required = true) String localCurrency,
    					  @RequestParam(value = "start", required = true) String start,
    					  @RequestParam(value = "end", required = true) String end,
    					  @RequestParam(value = "num", required = false) int num,
    					  HttpServletResponse response) {
		
		//int resourceConfig = 3;
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("数据编号", "数据来源", "日期","基础货币/原始货币", "汇率");
		result.put("titles", titles);	
		List<Object> data = new ArrayList<Object>();
		int totalPages=0;
		
		List<Object> r_list = JsonUtil.stringToList(resources);
		
		if(r_list.size()>0) {
			int resourceConfig = Integer.parseInt(r_list.get(0).toString());
			if(!start.equals("")){
				start=start.replaceAll("-","");
			}
			if(!end.equals("")){
				end=end.replaceAll("-", "");
			}
			
			Date startDate=DateUtil.convertStr2DateAll(start);
			Date endDate=DateUtil.convertStr2DateAll(end);
			List<MarketFxRateModel> fxModelList=new ArrayList<MarketFxRateModel>();
			//pageHelper分页
			/*
			 * if(frameWorkFlag==1){ int pageSize=30; Page<MarketFxRateModel>
			 * page=marketDataService.loadPageHelperFxRateList(PageHelper.startPage(num,
			 * pageSize), resourceConfig, localCurrency, baseCurrency, startDate, endDate);
			 * fxModelList=page.getResult(); totalPages=page.getPages(); }else{
			 */
			//hibernate分页
			Pages<MarketFxRateModel> pages=new Pages<MarketFxRateModel>(num);
			pages=marketDataService.loadFxRateList(pages, resourceConfig, localCurrency, baseCurrency, startDate, endDate);
			fxModelList=pages.getResultList();
			totalPages=pages.getTotalPages();
			
			for(MarketFxRateModel fxrate:fxModelList){
			List<Object> item = new ArrayList<Object>();
				item.add(fxrate.getFxid());
				item.add(fxrate.getSoucename());
				item.add(DateUtil.convertD2YYYY_MM_DD(fxrate.getRateDate()));
				item.add(fxrate.getBaseCurrency() + "/" + fxrate.getCurrency());
				item.add(fxrate.getRate());
				data.add(item);
			}
		}
		
		
		result.put("data", data);
		//如果经过计算pages为1时，不用把pages放进result
		result.put("pages", totalPages);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/market/ex_new.do", method = RequestMethod.POST, name="汇率-查询")  
    public void exchangeRateList_new(@RequestParam(value = "config", required = true) String resources,
    					  		 @RequestParam(value = "baseCurrency", required = true) String baseCurrency,
    					  		 @RequestParam(value = "localCurrency", required = true) String localCurrency,
    					  		 @RequestParam(value = "start", required = true) String start,
    					  		 @RequestParam(value = "end", required = true) String end,
    					  		 @RequestParam(value = "page", required = true) int num,
    	    					 @RequestParam(value = "limits", required = true) int limits,
    	    					 @RequestParam(value = "export2excel", required = false) String export2excel,
    					  		 HttpServletResponse response) {
		List<Object> r_list = JsonUtil.stringToList(resources);
		int resourceConfig = Integer.parseInt(r_list.get(0).toString());
		//int resourceConfig = 3;
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("数据编号", "日期","原始货币/基础货币", "汇率");
		result.put("titles", titles);
		
		if(!start.equals("")){
			start=start.replaceAll("-","");
		}
		if(!end.equals("")){
			end=end.replaceAll("-", "");
		}
		
		List<Object> data = new ArrayList<Object>();
		//判断是否需要导出  如果导出  limits需要很大
		limits=pageLimits(export2excel,limits);
		
		Pages<MarketFxRateModel> pages=new Pages<MarketFxRateModel>(num,limits);
		
		Date startDate=DateUtil.convertStr2DateAll(start);
		Date endDate=DateUtil.convertStr2DateAll(end);
		pages=marketDataService.loadFxRateList(pages, resourceConfig, localCurrency, baseCurrency, startDate, endDate);
		
		List<MarketFxRateModel> fxModelList=pages.getResultList();
		for(MarketFxRateModel fxrate:fxModelList){
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("fx_id",fxrate.getFxid());
			item.put("fx_date", DateUtil.convertD2YYYY_MM_DD(fxrate.getRateDate()));
			item.put("fx_currency",fxrate.getCurrency() + "/" + fxrate.getBaseCurrency());
			item.put("fx_rate", fxrate.getRate());
			data.add(item);
		}
		result.put("data", data);
		result.put("pages", pages.getTotalPages());	
		result.put("totalCount", pages.getTotalCount());	
		//导出excel或返给前台显示
		returnControllerList(export2excel,result,response);
	}
	
	@RequestMapping(value="/market/smf.do", method = RequestMethod.POST, name="证券主文件-查询")  
    public void smfList(@RequestParam(value = "config", required = true) String resources,
    					@RequestParam(value = "type", required = true) String type,	
    					@RequestParam(value = "smf", required = true) String smf,
    					@RequestParam(value = "start", required = true) String start,
  					    @RequestParam(value = "end", required = true) String end,
  					    @RequestParam(value = "num", required = false) int num,
    					HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("数据编号", "资产类别", "证券代码", "证券名称", "所属行业","货币","国家","总账组","数据来源");
		result.put("titles", titles);
		List<Object> data = new ArrayList<Object>();
		Pages<MarketStockModel> pages=new Pages<MarketStockModel>(num);
		
		List<Object> r_list = JsonUtil.stringToList(resources);
		int[] ids=new int[r_list.size()];
		for(int i=0;i<r_list.size();i++){
			ids[i]=Integer.valueOf(r_list.get(i).toString());
		}

		String secid="";
		String[] params=smf.split("\\|");
		if(params.length>=2){
			secid=params[1];
		}else{
			secid=smf;
		}
		marketDataService.loadstockList(ids, type, secid, pages);
		List<MarketStockModel> stockList=pages.getResultList();
		
		for(MarketStockModel stockModel:stockList){
		List<Object> item = new ArrayList<Object>();
			item.add(stockModel.getCusip());
			item.add(stockModel.getSecType());
			item.add(stockModel.getSecCode());
			item.add(stockModel.getSecName());
			item.add(stockModel.getIndustryName());
			item.add(stockModel.getCurName());
			item.add(stockModel.getCountry());
			item.add(stockModel.getCoaName());
			item.add(stockModel.getSourcename());
			data.add(item);
		}
		result.put("data", data);
		//如果经过计算pages为1时，不用把pages放进result
		result.put("pages", pages.getTotalPages());	
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/market/newSmf.do", method = RequestMethod.POST, name="新证券主文件-查询")  
    public void newSmf(@RequestParam(value = "config", required = true) String resources,
    					@RequestParam(value = "type", required = true) String type,	
    					@RequestParam(value = "smf", required = true) String smf,
    					@RequestParam(value = "start", required = true) String start,
  					    @RequestParam(value = "end", required = true) String end,
  					    @RequestParam(value = "page", required = true) int num,
  					    @RequestParam(value = "limits", required = true) int limits,
  					    @RequestParam(value = "export2excel", required = false) String export2excel,
    					HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Object> data = new ArrayList<Object>();
		//判断是否需要导出  如果导出  limits需要很大
		limits=pageLimits(export2excel,limits);
		
		Pages<MarketStockModel> pages=new Pages<MarketStockModel>(num,limits);
		
		List<Object> r_list = JsonUtil.stringToList(resources);
		int[] ids=new int[r_list.size()];
		for(int i=0;i<r_list.size();i++){
			ids[i]=Integer.valueOf(r_list.get(i).toString());
		}

		String secid="";
		String[] params=smf.split("\\|");
		if(params.length>=2){
			secid=params[1];
		}else{
			secid=smf;
		}
		marketDataService.loadstockList(ids, type, secid, pages);
		List<MarketStockModel> stockList=pages.getResultList();
		
		for(MarketStockModel stockModel:stockList){
			HashMap<String, Object> item=new HashMap<String, Object>();
			item.put("smf_id", stockModel.getCusip());
			item.put("secid", stockModel.getSecCode());
			item.put("secname", stockModel.getSecName());
			item.put("currency", stockModel.getCurName());
			item.put("countrycode", stockModel.getCountry());
			item.put("glgroupcode", stockModel.getCoaName());
			data.add(item);
		}
		result.put("data", data);
		result.put("pages", pages.getTotalPages());	
		result.put("totalCount", pages.getTotalCount());	
		//导出excel或返给前台显示
		returnControllerList(export2excel,result,response);
	}
	
	
	@RequestMapping(value="/market/smfShortList.do", method = RequestMethod.POST, name="证券主文件-列表")  
    public void smfShortList(@RequestParam(value = "config", required = true) int resourceConfig,
    						 @RequestParam(value = "type", required = true) String assetType,
    						 HttpServletResponse response) {
		ArrayList<String> result = new ArrayList<String>();
		
		//int sourceid=0;
		//if(!resourceConfig.toUpperCase().equals("ALL")){
			//sourceid=Integer.valueOf(resourceConfig);
		//}
		//		
		//int sectype=0;
		//if(!type.toUpperCase().equals("ALL")){
			//sectype=Integer.valueOf(type);
		//}
		int sourceid = resourceConfig;
		List<String> secList=marketDataService.loadSecIDNameList(sourceid, assetType);
			
		for(String secid:secList){
			result.add(secid);
		}
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value = "/market/newSmfShortList.do", method = RequestMethod.POST, name = "证券主文件-新列表")
	public void newSmfShortList(@RequestParam(value = "config", required = true) String resourceConfig,
			@RequestParam(value = "type", required = true) String assetType, HttpServletResponse response) {

		HashMap<String, String> result = new HashMap<String, String>();

		int sourceid = Integer.valueOf(resourceConfig);
		List<String> secList = marketDataService.loadSecIDNameList(sourceid, assetType);
		for (String secid : secList) {
			String[] str = secid.split("\\|");
			if (str.length == 3) {
				result.put(secid, secid);
			}
		}
		JsonUtil.writeObjToResponse(response, result);
	}
	
	//外汇远期证券主文件  
	@RequestMapping(value = "/market/newForwardSmfShortList.do", method = RequestMethod.POST, name = "证券主文件-新列表")
	public void newForwardSmfShortList(@RequestParam(value = "config", required = true) String resourceConfig,
			@RequestParam(value = "type", required = true) String assetType, HttpServletResponse response) {

		HashMap<String, String> result = new HashMap<String, String>();

		int sourceid = Integer.valueOf(resourceConfig);
		List<String> secList = marketDataService.loadSecIDNameList(sourceid, assetType);
		for (String secid : secList) {
			String[] str = secid.split("\\|");
			//只要被做过平仓 交割 到期的  都不再显示 防止被重复交易
			List<TranBasic> tbList=marketDataService.getForwardSmfTranBasic(str[0],TransTypes.FORWORD_CURRENCY_OPEN);
			if(tbList.size()>0){
				continue;
			}else{
				//如果交易开仓交易被删除了 证券主文件就不显示
				TranBasic tb=marketDataService.getForwardSmf(str[0], TransTypes.FORWORD_CURRENCY_OPEN);
				if(tb!=null){
					result.put(secid, secid);
				}else{
					continue;
				}
			}
		}
		JsonUtil.writeObjToResponse(response, result);
	}
	
	//新股中签证券主文件(要求该证券没有被做过交易)
	@RequestMapping(value = "/market/newReceiveSmfList.do", method = RequestMethod.POST, name = "证券主文件-新列表")
	public void newReceiveSmfList(@RequestParam(value = "config", required = true) String resourceConfig,
			@RequestParam(value = "type", required = true) String assetType, 
			HttpServletResponse response) {

		HashMap<String, String> result = new HashMap<String, String>();

		int sourceid = Integer.valueOf(resourceConfig);
		List<String> secList = marketDataService.loadSecIDNameList(sourceid, assetType);
		for (String secid : secList) {
			String[] str = secid.split("\\|");
			int count=marketDataService.getTranBasicPostNum(str[0]);
			if(count>0){
				continue;
			}else{
				result.put(secid, secid);
			}
		}
		JsonUtil.writeObjToResponse(response, result);
	}
	
	//获得SMF的交易货币
	@RequestMapping(value="/market/smfTradeCurrency.do", method = RequestMethod.POST)
	public void smfTradeCurrency(@RequestParam(value = "id", required = false) String smfID, HttpServletResponse response){
		String curID="1";
		if(smfID!=null&&!smfID.equals("")){
			String[] secidArr=smfID.split("\\|");
			if(secidArr.length==3){
				curID=marketDataService.loadSecCurrencyBySecCode(secidArr[1]);
			}
		}
		JsonUtil.writeObjToResponse(response, curID);
	}
	

	
	//获得空头和多头货币
	@RequestMapping(value="/market/smfLongShortCurrency.do", method = RequestMethod.POST)
	public void smfLongShortCurrency(@RequestParam(value = "id", required = true) String smfID, HttpServletResponse response){
		String[] secids=smfID.split("\\|");
		String cusip=secids[0];
		SecContract sc=secContractDao.loadSecContract(cusip);
		HashMap<String, String> cc = new HashMap<String, String>();
		cc.put("long", sc.getLongCurID());
		cc.put("short", sc.getShortCurID());
		JsonUtil.writeObjToResponse(response, cc);
	}
	
	//获得空头和多头本金
	@RequestMapping(value="/market/smfLongShortPrincipal.do", method = RequestMethod.POST)
	public void smfLongShortPrincipal(@RequestParam(value = "id", required = true) String smfID, HttpServletResponse response){
		String[] secids=smfID.split("\\|");
		String cusip=secids[0];
		SecContract sc=secContractDao.loadSecContract(cusip);
		HashMap<String, Double> cc = new HashMap<String, Double>();
		cc.put("long", sc.getLongCash());
		cc.put("short", sc.getShortCash());
		JsonUtil.writeObjToResponse(response, cc);
	}
	
	//获得期权smf中的行权价格
	@RequestMapping(value="/market/smfExercisePrice.do", method = RequestMethod.POST)
	public void smfExercisePrice(@RequestParam(value = "smfID", required = true) String smfID, HttpServletResponse response){
		HashMap<String, Double> cc = new HashMap<String, Double>();
		String[] secids=smfID.split("\\|");
		String cusip=secids[0];
		IssueInfo ii=issueInfoDao.loadIssueByCusip(cusip);
		if(ii!=null){
			cc.put(MsgFields.price, ii.getEndPrice());
		}
		JsonUtil.writeObjToResponse(response, cc);
	}
	
	//TODO：获得date时间点的smfID的持仓数量
	@RequestMapping(value="/market/smfCurrentAmounts.do", method = RequestMethod.POST)
	public void smfCurrentAmounts(@RequestParam(value = "id", required = true) String smfID, 
								  @RequestParam(value = "port", required = true) String portID,
								  @RequestParam(value = "date", required = true) String date,
								  @RequestParam(value = "contract", required = true) String lot,
								  HttpServletResponse response){
		String[] secids=smfID.split("\\|");
		String cusip=secids[0];
		int lotnum=1;
		if(!lot.equals("")){
			lotnum=Integer.valueOf(lot);
		}
		Date closeDate=DateUtil.convertStr2Date2(date);
		TranClo tc=tranCloDao.loadTranClo(portID,closeDate, cusip, lotnum);
		double totalAmount=0;
		if(tc!=null){
			totalAmount=tc.getAmount();
		}
		JsonUtil.writeObjToResponse(response, totalAmount);

	}
			
	//获得SMF的tradeCurrency
	@RequestMapping(value="/market/smfIncomeCurrency.do", method = RequestMethod.POST)
	public void smfIncomeCurrency(@RequestParam(value = "id", required = true) int smfID, HttpServletResponse response){
		JsonUtil.writeObjToResponse(response, 3);
	}
	
	@RequestMapping(value="/market/priceDetail.do", method = RequestMethod.POST, name="价格-详情")
	 public void priceDetail(@RequestParam(value = "id", required = true) int priceID,
			 			     HttpServletResponse response){
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号","价格基准", "证券", "价格时间", "价格", "当前收益率", "配置来源");
		result.put("titles", titles);
		
		MarketPriceModel marketprice=marketDataService.loadPriceDetail(priceID);
		if(marketprice!=null){
			List<Object> data = new ArrayList<Object>();
			data.add(priceID);
			data.add(marketprice.getPriceto());
			data.add(marketprice.getSecid());
			data.add(DateUtil.convertD2YYYY_MM_DD(marketprice.getPriceDate()));
			data.add(marketprice.getPrice());
			data.add(marketprice.getYield());
			data.add(marketprice.getSourceid());
			result.put("data", data);
		}	
		
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/market/priceUpdate.do", method = RequestMethod.POST, name="价格-更新")
	 public void priceUpdate(@RequestParam(value = "json", required = true) String json,
			    			HttpServletRequest request, 
			    			HttpServletResponse response){
		MsgContainer container=commService.executeMsg(request, json);
		controllerReturnNew(response, container);
	}
	
	@RequestMapping(value="/market/priceCreate.do", method = RequestMethod.POST, name="价格-创建")
	 public void priceCreate(@RequestParam(value = "json", required = true) String json,
			 			    HttpServletRequest request, 
			 				HttpServletResponse response){
		MsgContainer container=commService.executeMsg(request, json);
		controllerReturnNew(response, container);
	}
	
	@RequestMapping(value="/market/priceRemove.do", method = RequestMethod.POST, name="价格-删除")
	 public void priceRemove(@RequestParam(value = "id", required = true) int priceID,
			 				 HttpServletRequest request, 
			 			     HttpServletResponse response){
		
		int userID=JixuUserUtil.getUserIDbyRequest(request);
		MsgContainer container=new MsgContainer(); 
		HashMap<String, Object> fieldsHM=new HashMap<String, Object>();
		String header="assemblePriceMsg";
		fieldsHM.put(MsgFields.operation, "delete");
		fieldsHM.put(MsgFields.header, header);
		fieldsHM.put(MsgFields.id, String.valueOf(priceID));
		container.setFieldsHM(fieldsHM);
		container.setUserID(userID);
		
		container=MsgFactory.executeMsgBean(container);
		controllerReturnNew(response, container);
	}
	
	@RequestMapping(value="/market/exDetail.do", method = RequestMethod.POST, name="汇率-详情")
	 public void exDetail(@RequestParam(value = "id", required = true) int exID,
			 			     HttpServletResponse response){
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号", "汇率时间", "本地货币", "功能货币", "汇率", "导入配置");
		result.put("titles", titles);
		
		List<Object> data = new ArrayList<Object>();
		MarketFxRateModel fxrate=marketDataService.loadFxRateDetail(exID);
		data.add(fxrate.getFxid());
		data.add(DateUtil.convertD2YYYY_MM_DD(fxrate.getRateDate()));
		data.add(fxrate.getCurID());
		data.add(fxrate.getBaseCurID());
		data.add(fxrate.getRate());
		data.add(fxrate.getSourceid());
		result.put("data", data);
			
		JsonUtil.writeObjToResponse(response, result);
	}
	//NEW ADD
	@RequestMapping(value="/market/exDetail_new.do", method = RequestMethod.POST, name="汇率-详情")
	 public void exDetail_new(@RequestParam(value = "id", required = true) int exID,
			 			     HttpServletResponse response){
		HashMap<String, Object> result = new HashMap<String, Object>();
		//List<String> titles = Arrays.asList("编号", "汇率时间", "本地货币", "功能货币", "汇率", "导入配置");
		//result.put("titles", titles);
		
		List<Object> data = new ArrayList<Object>();
		MarketFxRateModel fxrate=marketDataService.loadFxRateDetail(exID);
		result.put("fx_id", fxrate.getFxid());
		result.put("fx_date", DateUtil.convertD2YYYY_MM_DD(fxrate.getRateDate()));
		result.put("localCurrency", fxrate.getCurID());
		result.put("funcCurrency", fxrate.getBaseCurID());
		result.put("rate", fxrate.getRate());
		result.put("source", fxrate.getSourceid());
			
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/market/exCreate.do", method = RequestMethod.POST, name="汇率-新建")
	 public void exCreate(@RequestParam(value = "json", required = true) String json,
			 				 HttpServletRequest request, 
			 			     HttpServletResponse response){
		
		MsgContainer container=commService.executeMsg(request, json);
		controllerReturnNew(response, container);
	}
	
	@RequestMapping(value="/market/exUpdate.do", method = RequestMethod.POST, name="汇率-更新")
	 public void exUpdate(@RequestParam(value = "id", required = true) int exID,
			 				 @RequestParam(value = "json", required = true) String json,
			 				 HttpServletRequest request, 
			 			     HttpServletResponse response){
		MsgContainer container=commService.executeMsg(request, json);
		controllerReturnNew(response, container);
	}
	
	@RequestMapping(value="/market/exRemove.do", method = RequestMethod.POST, name="汇率-删除")
	 public void exRemove(@RequestParam(value = "id", required = true) int exID,
			 				 HttpServletRequest request, 
			 			     HttpServletResponse response){
		
		int userID=JixuUserUtil.getUserIDbyRequest(request);
		MsgContainer container=new MsgContainer(); 
		HashMap<String, Object> fieldsHM=new HashMap<String, Object>();
		String header="assembleFxRateMsg";
		fieldsHM.put(MsgFields.operation, "delete");
		fieldsHM.put(MsgFields.header, header);
		fieldsHM.put(MsgFields.id, String.valueOf(exID));
		container.setFieldsHM(fieldsHM);
		container.setUserID(userID);
		
		container=MsgFactory.executeMsgBean(container);
		controllerReturnNew(response, container);
	}
	
	@RequestMapping(value="/market/smfDetail.do", method = RequestMethod.POST, name="证券主文件-详情")
	 public void smfDetail(@RequestParam(value = "id", required = true) String smfID,
			 			     HttpServletResponse response){
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		List<String> titles = Arrays.asList("编号", "证券代码", "证券名称", "证券类型", "发行人", "国家", "交易所", "成本货币", "收入货币", "总账组", "资产类别", "资产主类别", "资产次类别", 
											"导入配置", "付款频率", "天数计算", "节假日安排", "付款日期转换", "付款拖延", "计提拖延", "复利频率", "复利方法");
		
		MarketStockDetailModel stockModel=marketDataService.loadStockDetail(smfID);
		
		HashMap<String, Object> detailHM=new HashMap<String, Object>();
		
		detailHM.put(MsgFields.cusip, stockModel.getCusip());
		detailHM.put(MsgFields.secid, stockModel.getSecID());
		detailHM.put(MsgFields.securityName, stockModel.getSecName());
		detailHM.put(MsgFields.issuetype, stockModel.getSecType());
		detailHM.put(MsgFields.issuer, stockModel.getIssuerCode());
		detailHM.put(MsgFields.countryCode, stockModel.getCountryID());
		detailHM.put(MsgFields.exchangeCode, stockModel.getExchangeID());
		detailHM.put(MsgFields.priCurNo, stockModel.getPrinCurID());
		detailHM.put(MsgFields.incomeCurNo, stockModel.getIncomeCurID());
		detailHM.put(MsgFields.priCurNo2, stockModel.getPrinCurID2());
		detailHM.put(MsgFields.incomeCurNo2, stockModel.getIncomeCurID2());
		detailHM.put(MsgFields.glgroup, stockModel.getCoaNum());
		detailHM.put(MsgFields.rpSecType, stockModel.getAssetType3());
		detailHM.put(MsgFields.assetMajor,  stockModel.getAssetType1());
		detailHM.put(MsgFields.assetMinor,  stockModel.getAssetType2());
		detailHM.put(MsgFields.sourceID,  stockModel.getSourceID());
		detailHM.put(MsgFields.cashflowcfg, stockModel.getCashFlowCfgID());
		detailHM.put(MsgFields.cashflowcfg2, stockModel.getCashFlowCfgID2());
		detailHM.put(MsgFields.secExpireDate, DateUtil.convertD2YYYY_MM_DD(stockModel.getExpireDate()));
		detailHM.put(MsgFields.secContractSize, stockModel.getContractSize());
		detailHM.put(MsgFields.optionType, stockModel.getOptionType());
		detailHM.put(MsgFields.underCusip, stockModel.getUnderSecName());
		detailHM.put(MsgFields.activeDate, stockModel.getActiveDate());
		detailHM.put(MsgFields.fixIncomeType, stockModel.getFixIncomeType());
		detailHM.put(MsgFields.startPrice, stockModel.getStartPrice());
		detailHM.put(MsgFields.endPrice, stockModel.getEndPrice());
		detailHM.put(MsgFields.swapExchangeInd, stockModel.getSwapExchangeInd());
		detailHM.put(MsgFields.longCurID, stockModel.getLongCurID());
		detailHM.put(MsgFields.longCash, stockModel.getLongCash());
		detailHM.put(MsgFields.shortCurID, stockModel.getShortCurID());
		detailHM.put(MsgFields.shortCash, stockModel.getShortCash());
		detailHM.put(MsgFields.executeFX, stockModel.getExecuteFX());
		detailHM.put(MsgFields.productType, stockModel.getOptionType());
		detailHM.put(MsgFields.futuresType, stockModel.getFuturesType());
		detailHM.put(MsgFields.futuresTarget, stockModel.getFuturesTarget());
		detailHM.put(MsgFields.marginRate, stockModel.getMarginRate());
		
		result.put("titles", titles);
		result.put("data", detailHM);
		
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/market/smfCreate.do", method = RequestMethod.POST, name="证券主文件-新建")
	 public void smfCreate(@RequestParam(value = "params", required = false) String params,
			 			   @RequestParam(value = "json", required = true) String json,
			 			   HttpServletRequest request,
			 			   HttpServletResponse response){
		int userID=JixuUserUtil.getUserIDbyRequest(request);
		HashMap<String, Object> fieldsMap=JSONObject.parseObject(json, HashMap.class);
		
		//以下两个判断是因为交易和SMF同时在一个界面出现时，secid和assetType会重复，有些smf json文件会用smfid和smfAssetType代替以避免重复
		//这里将这两个特殊字段还原成secid和assetType
		if (fieldsMap.containsKey("smfid")){
			fieldsMap.put("secid", fieldsMap.get("smfid"));
			fieldsMap.remove("smfid");
		}
		if (fieldsMap.containsKey("smfAssetType")){
			fieldsMap.put("assetType", fieldsMap.get("smfAssetType"));
			fieldsMap.remove("smfAssetType");
		}
		MsgContainer container=new MsgContainer();
		container.setFieldsHM(fieldsMap);
		container.setUserID(userID);
		container=msgFactory.executeMsg(container);
		controllerReturnNew(response, container);
	}
	
	@RequestMapping(value="/market/smfUpdate.do", method = RequestMethod.POST, name="证券主文件-更新")
	 public void smfUpdate(@RequestParam(value = "id", required = true) String smfID,
			 				@RequestParam(value = "params", required = true) String params,
			 				@RequestParam(value = "json", required = true) String json,
			 				HttpServletRequest request,
			 				HttpServletResponse response){
		int userID=JixuUserUtil.getUserIDbyRequest(request);
		
		HashMap<String, Object> fieldsMap=JSONObject.parseObject(json, HashMap.class);
		//以下两个判断是因为交易和SMF同时在一个界面出现时，secid和assetType会重复，有些smf json文件会用smfid和smfAssetType代替以避免重复
		//这里将这两个特殊字段还原成secid和assetType
		if (fieldsMap.containsKey("smfid")){
			fieldsMap.put("secid", fieldsMap.get("smfid"));
			fieldsMap.remove("smfid");
		}
		if (fieldsMap.containsKey("smfAssetType")){
			fieldsMap.put("assetType", fieldsMap.get("smfAssetType"));
			fieldsMap.remove("smfAssetType");
		}
				
		MsgContainer container=new MsgContainer();
		container.setUserID(userID);
		container.setFieldsHM(fieldsMap);
		container=msgFactory.executeMsg(container);
		controllerReturnNew(response, container);
	}
	
	@RequestMapping(value="/market/smfRemove.do", method = RequestMethod.POST, name="证券主文件-删除")
	 public void smfRemove(@RequestParam(value = "id", required = true) String smfID,
			 			    HttpServletRequest request, 
			 				HttpServletResponse response){
		int userID=JixuUserUtil.getUserIDbyRequest(request);
		
		MsgContainer container=new MsgContainer(); 
		HashMap<String, Object> fieldsHM=new HashMap<String, Object>();
		String header="smfOperation";
		fieldsHM.put(MsgFields.operation, "delete");
		fieldsHM.put(MsgFields.header, header);
		fieldsHM.put(MsgFields.cusip, String.valueOf(smfID));
		container.setFieldsHM(fieldsHM);
		container.setUserID(userID);
		
		container=msgFactory.executeMsg(container);
		controllerReturnNew(response, container);
	}
	
	@RequestMapping(value="/market/smfType.do", method = RequestMethod.POST)
	public void smfType(@RequestParam(value = "id", required =true) String smfID, HttpServletResponse response){
		System.out.println("Get smf type for " + smfID);
		JsonUtil.writeObjToResponse(response, "stock");
	}
	
	@RequestMapping(value="/market/defaultFxRate.do", method = RequestMethod.POST)
	public void defaultFxRate(@RequestParam(value = "port", required =true) String port, 
							  @RequestParam(value = "date", required =true) String date, 
							  @RequestParam(value = "funcCurrency", required=true) String func,
							  @RequestParam(value = "localCurrency", required=true) String local,
							  HttpServletResponse response){
		Date fxdate=DateUtil.convertStr2DateAll(date);
		double fxrate=marketDataService.loadFxRate(port,local, func, fxdate);
		JsonUtil.writeObjToResponse(response, fxrate);
	}
	
	 @RequestMapping(value="/market/newDefaultFxRate.do", method = RequestMethod.POST)
	 public void newDefaultFxRate(@RequestParam(value = "port", required =false) String port, 
	         @RequestParam(value = "date", required =false) String date, 
	         @RequestParam(value = "localCurrency", required=false) String local,
	         HttpServletResponse response){
		 double fxrate=0;
		 if(local!=null && !local.equals("null") && !local.equals("")){
			 Date fxdate=DateUtil.convertStr2DateAll(date);
			 PortfolioHdr portfolio= commService.loadPortfolio(port);
			 String funcID=portfolio.getCurID();
			 fxrate=marketDataService.loadFxRate(port,local, funcID, fxdate);
		 }
		 JsonUtil.writeObjToResponse(response, fxrate);
	 }
	 
	
	//option-select-values price_add_form.json--"label": "定价基准",
	//option-select-values price_edit_form.json--"label": "定价基准",
    @RequestMapping(value="/market/PriceDecideTypeOption.do", method = RequestMethod.POST)
    public void getPriceDecideTypeOption(HttpServletResponse response){
    	HashMap<Integer, String> result = PriceDecideType.priceDesideHM;
       JsonUtil.writeObjToResponse(response, result);
   }
    
    @RequestMapping(value="/market/repoPaymentFreq.do", method = RequestMethod.POST)
	public void repoPaymentFreq(HttpServletResponse response){
		HashMap<Integer, String> result = CouponData.intCpnfReqTypeHM;
		JsonUtil.writeObjToResponse(response, result);
	}
    
    @RequestMapping(value="/market/repoPaymentDateOptions.do", method = RequestMethod.POST)
	public void repoPaymentDateOptions(HttpServletResponse response){
		HashMap<Integer, String> result = CouponData.delayDateTypeOption;
		JsonUtil.writeObjToResponse(response, result);
	}
    
    @RequestMapping(value="/market/compoundFreq.do", method = RequestMethod.POST)
	public void  compoundFreq(HttpServletResponse response){
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		result.put(0, "年度");
		result.put(1, "季度");
		result.put(1, "月度");
		JsonUtil.writeObjToResponse(response, result);
	}
    
    @RequestMapping(value="/market/compoundMethods.do", method = RequestMethod.POST)
	public void  compoundMethods(HttpServletResponse response){
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		result.put(0, "有效利率法");
		result.put(1, "复利法");
		JsonUtil.writeObjToResponse(response, result);
	}
    
    @RequestMapping(value="/market/carryOverTypes.do", method = RequestMethod.POST)
	public void carryOverTypes(HttpServletResponse response){
		HashMap<Integer, String> result = InterfaceAutoType.autoTypeHM;
		JsonUtil.writeObjToResponse(response, result);
	}
    
    @RequestMapping(value="/market/carryOverMethods.do", method = RequestMethod.POST)
	public void carryOverMethods(HttpServletResponse response){
		HashMap<Integer, String> result = CouponData.intCpnfReqTypeHM;
		JsonUtil.writeObjToResponse(response, result);
	}
    
    private HashMap<String, Object> genErrorResult(MsgContainer container){
    	HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> issue = new HashMap<String, Object>();
		issue.put("code", container.getHeader());
		issue.put("level", "fail");
		issue.put("detail", container.getErrorMsg());
		result.put("issue", issue);
		return result;
    }
    
    //固定类型
    @RequestMapping(value="/market/fixIncomeTypes.do", method = RequestMethod.POST)
	public void fixIncomeTypes(HttpServletResponse response){
    	HashMap<Integer, String> result = TradeMarket.getFixIncomeTypeHM();
		JsonUtil.writeObjToResponse(response, result);
	}
    
  //TODO
    @RequestMapping(value="/market/amortizedMethods.do", method = RequestMethod.POST)
	public void amortizedMethods(HttpServletResponse response){
    	HashMap<Integer, String> result = new HashMap<Integer, String>();
		result.put(0, "等额本金");
		result.put(1, "等额本息");
		JsonUtil.writeObjToResponse(response, result);
	}
    
    @RequestMapping(value="/market/swapSetPrincipalType.do", method = RequestMethod.POST)
	public void getswapSetPrincipalType(HttpServletResponse response){
    	HashMap<Integer, String> result = SwapSetPrincipalType.optionHM;
		JsonUtil.writeObjToResponse(response, result);
	}
    
    @RequestMapping(value="/market/swapFXType.do", method = RequestMethod.POST)
	public void getswapFXType(HttpServletResponse response){
    	HashMap<Integer, String> result = SwapSetPrincipalType.optionSwapFXHM;
		JsonUtil.writeObjToResponse(response, result);
	}
    
    //前端日期控件调用返回当前日期
	@RequestMapping(value = "/market/returnDateControl.do", method = RequestMethod.POST)
	public void returnDateControl(HttpServletResponse response) {
		Date date = new Date();
		JsonUtil.writeObjToResponse(response, DateUtil.convertD2YYYY_MM_DD(date));
	}
	
	//期货smf 交易所
	@RequestMapping(value="/market/futuresExchanges.do", method = RequestMethod.POST)
	public void futuresTypes(HttpServletResponse response){
		HashMap<String, String> result = FutureTypes.getExchangeTypes();
		JsonUtil.writeObjToResponse(response, result);
	}
	
	
	//期货smf  获取交易所包含的期货类型
	@RequestMapping(value="/market/futuresTypes.do", method = RequestMethod.POST)
	public void futuresTypes(@RequestParam(value = "exchange", required = true) String exchange, 
			HttpServletResponse response){
		HashMap<Integer, String> result = FutureTypes.getFutureTypes(exchange);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	//期货smf  获取交易所期货类型下的合约标的
	@RequestMapping(value="/market/futuresTargets.do", method = RequestMethod.POST)
	public void futuresTargets(
			@RequestParam(value = "exchange", required = true) String exchange, 
			@RequestParam(value = "futuresType", required = true) int futuresType, 
			HttpServletResponse response){
		HashMap<Integer, String> result = FutureTypes.getFutureTargets(exchange,futuresType);
		JsonUtil.writeObjToResponse(response, result);
	}
}
