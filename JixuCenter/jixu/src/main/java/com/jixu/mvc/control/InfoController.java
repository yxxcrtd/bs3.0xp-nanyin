package com.jixu.mvc.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.controller.ExceptionControl;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.CashPositionModel;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.type.LongShortType;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.NavAssetObj;
import com.jixu.common.types.OptionType;
import com.jixu.common.util.DataConvert;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.JsonUtil;
import com.jixu.common.util.MsgPackage;
import com.jixu.common.util.Pages;
import com.jixu.common.util.RoundUtil;
import com.jixu.common.util.SubListUtil;
import com.jixu.data.service.OutDataInterface;
import com.jixu.framework.msg.MsgFactory;
//import com.jixu.mq.producer.ActiveMQSender;
import com.jixu.mvc.control.model.SecPositionModel;
import com.jixu.mvc.control.model.VoucherDetailsView;
import com.jixu.mvc.control.model.VouchersModel;
import com.jixu.mvc.control.service.SecCashService;
import com.jixu.mvc.control.service.VoucherService;
import com.jixu.secTran.SecurityCoreFactory;
import com.joyintech.framework.security.SecurityUtil;
import com.orm.obj.BkCashFlow;
import com.orm.obj.BkCashRcpy;
import com.orm.obj.Nav;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Voucher;
import com.orm.obj.VoucherDetails;

@Controller
public class InfoController extends ExceptionControl{
	@Resource(name="voucherService")
	private VoucherService voucherService;

	@Resource(name="SecCashService")
	private SecCashService secCashService;

	@Resource(name="commfuncService")
	private CommFuncService commService;

	@Resource(name="msgPackage")
	private MsgPackage msgPackage;

	@Resource(name="msgFactory")
	private MsgFactory msgFactory;

//	@Resource(name="ActiveMQProducer")
//	private ActiveMQSender actSender;

	@Resource(name="JixuTbSecPosition")
	private OutDataInterface outDate;

	@RequestMapping(value="/info.do", method = RequestMethod.GET, name="财务信息-页面显示")
	public String info() {
		return "info";
	}

	@RequestMapping(value="/info/voucherCreate.do", method = RequestMethod.POST, name="凭证-手工分录创建")
    public void voucheCreate(@RequestParam(value = "portID", required = true) String portID,
    						@RequestParam(value = "date", required = true) String date,
    						@RequestParam(value = "voucher", required = true) String voucher,
    						HttpServletRequest request,
    						HttpServletResponse response){

		int userID=JixuUserUtil.getUserIDbyRequest(request);
		List<Object> v_list = JsonUtil.stringToList(voucher);

		ResultContainer container=voucherService.createManualVoucher(userID,portID, date.replaceAll("-", ""), v_list);
		if(container.isCorrectFlag()){
			JsonUtil.writeObjToResponse(response, container.getEntityID());
		}else{
			HashMap<String, Object> result = new HashMap<String, Object>();
			HashMap<String, Object> issue = new HashMap<String, Object>();
			issue.put("code", container.getErrorCode());
			issue.put("level", "fail");
			issue.put("detail", container.getErrorMsg());
			result.put("issue", issue);
			JsonUtil.writeObjToResponse(response, result);
		}
	}

	@RequestMapping(value="/info/voucherUpdate.do", method = RequestMethod.POST, name="凭证-手工分录更新")
    public void voucheUpdate(@RequestParam(value = "id", required = true) String voucherID,
    						@RequestParam(value = "voucher", required = true) String voucher,
    						HttpServletResponse response){
		JsonUtil.writeObjToResponse(response, voucherID);
	}

	@RequestMapping(value="/info/voucherList.do", method = RequestMethod.POST, name="凭证-查询")
    public void voucherList(@RequestParam(value = "voucher", required = true) int journalCode,
    						@RequestParam(value = "currency", required = true) String currency,
    						@RequestParam(value = "secid", required = true) String secid,
    						@RequestParam(value = "portfolio", required = true) String portfolio,
    						@RequestParam(value = "start", required = true) String start,
    						@RequestParam(value = "end", required = true) String end,
    						@RequestParam(value = "acctNum", required = true) String acctNum,
    						@RequestParam(value = "num", required = false) int num,
    						HttpServletResponse response) {
		if(start.equals(""))
			start="2000-01-01";
		if(end.equals(""))
			end=DateUtil.convertD2YYYY_MM_DD(new Date());

		Date startDate=DateUtil.convertStr2Date2(start);
		Date endDate=DateUtil.convertStr2Date2(end);

		String startVoucherNum="";
		String endVoucherNum="";
		String secID=secid;
		String currencycode=currency;
		Pages<VouchersModel> pages=new Pages<VouchersModel>(num);
		pages.setPageMaxRecords(100000);
		pages=voucherService.fetchVouchers(pages, portfolio, startDate, endDate, journalCode, startVoucherNum, endVoucherNum, secID, currencycode, acctNum);

		HashMap<String, Object> result = new HashMap<String, Object>();

		List<List<Object>> data = new ArrayList<List<Object>>();

		List<VouchersModel> vouchersModelList=pages.getResultList();

		for(VouchersModel voucherModel:vouchersModelList){
			Voucher v=voucherModel.getVoucher();
			List<VoucherDetails> vds=voucherModel.getVoucherDetailsList();
			double totalCR=0;
			double totalDR=0;
			for(VoucherDetails eachVD:vds){
				totalCR=totalCR+eachVD.getCr_func();
				totalDR=totalDR+eachVD.getDr_func();
			}
			totalCR=RoundUtil.roundMoney2Dec(totalCR);
			totalDR=RoundUtil.roundMoney2Dec(totalDR);

			ArrayList<Object> item = new ArrayList<Object>();
			item.add(v.getVouchernum());
			item.add(commService.loadPortfolio(v.getPortfolio()).getShortName());

			item.add(DateUtil.convertD2YYYY_MM_DD(v.getLedgerDate()));
			item.add(v.getDescription());
			if(vds.get(0).getSecurityID()==null){
				item.add("-");
			}else{
				item.add(vds.get(0).getSecurityID());
			}
			item.add(vds.get(0).getFunctionCurrency());
			item.add(vds.get(0).getLocalCurrency());
			item.add(DataConvert.convertCash2US(totalCR));
			item.add(DataConvert.convertCash2US(totalDR));
			//item.add(v.getImportedUser());
			//item.add("trade1234");
			if(v.getJournalSourceCode()==JournalTypes.CASH || v.getJournalSourceCode()==JournalTypes.PURSALEJNL || v.getJournalSourceCode()==JournalTypes.SUBRED){
				item.add(v.getTaNum());
			}else if(v.getJournalSourceCode()==JournalTypes.GENERAL || v.getJournalSourceCode()==JournalTypes.INCOME){
				if(v.getTaNum()==null) {
					item.add("-");
				}else
					item.add(v.getTaNum()+"#");
			}else{
				item.add("-");
			}
			data.add(item);
		}

		result.put("data", data);
		List<String> titles = Arrays.asList("凭证号","投资组合","记账日期", "摘要", "证券代码", "本币", "原币", "借方(本币)", "贷方(本币)", "交易/头寸编号");



//		DataReadResult dataSet=new DataReadResult();
//		dataSet.setDataHeader(titles);
//		dataSet.setDataResult(data);
//
//		try {
//			downLoadFile(response, dataSet);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(1111);

		result.put("titles", titles);
		result.put("pages", pages.getTotalPages());
		JsonUtil.writeObjToResponse(response, result);
    }

	@RequestMapping(value="/info/voucherTypeList.do", method = RequestMethod.POST)
	 public void voucherTypeList(HttpServletResponse response){
		HashMap<Integer, String> result = JournalTypes.getJournalTypeHM();
		result.put(0, "全部");
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value="/info/voucherDetail.do", method = RequestMethod.POST, name="凭证-详情")
    public void voucherDetail(@RequestParam(value = "id", required = true) String voucherNum,
    		HttpServletRequest request,
    		HttpServletResponse response) {

		HashMap<String, Object> result = new HashMap<String, Object>();
		ArrayList<Object> data = new ArrayList<Object>();
		List<VoucherDetailsView> vdList=voucherService.fetchVoucherDetails(voucherNum);
		for(VoucherDetailsView vd:vdList){
			ArrayList<Object> item = new ArrayList<Object>();
			item.add(vd.getAccountNum());
			item.add(vd.getAcctname());
			item.add(DataConvert.convertAmount2US(vd.getAmount()));
			item.add(vd.getFunctionCurrency());
			item.add(DataConvert.convertCash2US(vd.getDr_func()));
			item.add(DataConvert.convertCash2US(vd.getCr_func()));
			item.add(vd.getLocalCurrency());
			item.add(DataConvert.convertCash2US(vd.getDr_local()));
			item.add(DataConvert.convertCash2US(vd.getCr_local()));
			if(vd.getSecurityID()==null){
				item.add("");
			}else{
				item.add(vd.getSecurityID());
			}
			item.add(DataConvert.convertCash2US(vd.getPrice()));
			item.add(vd.getFxRate());

			data.add(item);
		}
		result.put("data", data);
		List<String> titles = Arrays.asList("科目编号", "科目名称", "数量", "本币", "借方金额(本币)", "贷方金额(本币)", "原币", "借方金额(原币)", "贷方金额(原币)", "证券代码", "价格", "汇率");
		result.put("titles", titles);
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value="/info/voucherEntryTypes.do", method = RequestMethod.POST)
	public void voucherEntryTypesTypes(HttpServletResponse response){
		JsonUtil.writeObjToResponse(response, DBCR.jdOptionsHM);
	}

	@RequestMapping(value="/info/voucherRemove.do", method = RequestMethod.POST, name="凭证-删除")
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void voucheRemove(@RequestParam(value = "id", required = true) int vouchernum,
							HttpServletRequest request,
    						HttpServletResponse response){
		int userID=JixuUserUtil.getUserIDbyRequest(request);
		HashMap<String, Object> fieldsMap=msgPackage.journal("DELETE", vouchernum);
		MsgContainer container=new MsgContainer();
		container.setFieldsHM(fieldsMap);
		container.setUserID(userID);
		container=msgFactory.executeMsg(container);

		if(container.isCorrectFlag()){
			JsonUtil.writeObjToResponse(response, container.getObjID() + "");
		}else{
			HashMap<String, Object> result = new HashMap<String, Object>();
			HashMap<String, Object> issue = new HashMap<String, Object>();
			issue.put("code", container.getErrorCode());
			issue.put("level", "fail");
			issue.put("detail", container.getErrorMsg());
			result.put("issue", issue);
			JsonUtil.writeObjToResponse(response, result);
		}
	}

	@RequestMapping(value="/info/cash.do", method = RequestMethod.POST, name="现金头寸-查询")
    public void cashPosition(@RequestParam(value = "portfolio", required = true) String portfolio,
    						 @RequestParam(value = "start", required = true) String start,
    						 @RequestParam(value = "end", required = true) String end,
    						 HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();

		List<String> titles = Arrays.asList("编号","银行", "账户", "账户名称", "货币", "当前余额","应收利息", "投资组合货币","当前价值");
		result.put("titles", titles);

		List<Object> data = new ArrayList<Object>();
		Date endDate=DateUtil.convertStr2DateAll(end);

		List<CashPositionModel> cashPositionList=secCashService.getCashPosition(portfolio, endDate);
		for(int i=0;i<cashPositionList.size();i++){
			CashPositionModel cashPosition=cashPositionList.get(i);
			List<Object> item = new ArrayList<Object>();
			item.add(cashPosition.getCashacctID());
			item.add(cashPosition.getBank());
			item.add(cashPosition.getBankAcct());
			item.add(cashPosition.getBankAcctName());
			item.add(cashPosition.getLocalCurCode());
			item.add(DataConvert.convertCash2US(cashPosition.getLocalBalance()));
			item.add(DataConvert.convertCash2US(cashPosition.getInterestIncome()));
			item.add(cashPosition.getFuncCurCode());
			item.add(DataConvert.convertCash2US(cashPosition.getFuncBalance()));
			data.add(item);

		}
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value="/info/cashDetail.do", method = RequestMethod.POST, name="现金头寸-详情")
    public void cashDetail(@RequestParam(value = "accountID", required = true) int accountID,
    					   @RequestParam(value = "portID", required = true) String portID,
    					   @RequestParam(value = "start", required = true) String start,
    					   @RequestParam(value = "end", required = true) String end,
    					   @RequestParam(value = "num", required = true) int num,
    					   HttpServletResponse response) {
		HashMap<String, Object> cashflow = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("银行时间", "交易类型", "交易金额", "交易凭证号");
		cashflow.put("titles", titles);
		List<Object> tData = new ArrayList<Object>();
		List<List<String>> cashList=secCashService.loadCashflowHisSummary(portID, accountID);
		//对list进行分页显示
		int pageSize=10;
		Pages<List<String>> pages=new Pages<List<String>>(num,pageSize);
		pages=SubListUtil.getPageList(cashList, num, pages);
		cashList=pages.getResultList();

		for(List<String> fieldsList:cashList){
			List<Object> tItem = new ArrayList<Object>();
			for(String field:fieldsList){
				tItem.add(field);
			}
			tData.add(tItem);
		}
		cashflow.put("data", tData);
		cashflow.put("pages", pages.getTotalPages());

		JsonUtil.writeObjToResponse(response, cashflow);
	}

	@RequestMapping(value="/info/position.do", method = RequestMethod.POST, name="证券头寸-查询")
    public void securityPosition(@RequestParam(value = "portfolio", required = true) String portnum,
    						 	 @RequestParam(value = "start", required = true) String start,
    						 	 @RequestParam(value = "end", required = true) String end,
    						 	 @RequestParam(value = "secid", required = false) String secid,
    						 	 @RequestParam(value = "num", required = true) int num,
    						 	 HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("证券代码","投资组合", "内部代码", "证券名称", "类型", "多空", "数量", "货币","摊余成本","成本(原币)","市值(原币)","利息(原币)", "货币", "成本(本币)","市值(本币)","利息(本币)","汇率");
		result.put("titles", titles);

		List<Object> data = new ArrayList<Object>();

		Date endDate=null;
		if(end.equals("")){
			PortfolioHdr portfolio=commService.loadPortfolio(portnum);
			endDate=portfolio.getLastGLClose();
		}else{
			endDate=DateUtil.convertStr2Date2(end);
		}

		List<SecPositionModel> positionList=new ArrayList<SecPositionModel>();

		if(secid!=null && !secid.equals("")){
			String[] secids=secid.split("\\|");
			String secidFinal="";
			if(secids.length==3){
				secidFinal=secids[1];
			}else{
				secidFinal=secid;
			}
			positionList=secCashService.getSecPosition(portnum, secidFinal, endDate);
		}else{
			positionList=secCashService.getSecPositions(portnum, endDate);
		}

		//对list进行分页显示
		Pages<SecPositionModel> pages=new Pages<SecPositionModel>(num);
		pages=SubListUtil.getPageList(positionList, num, pages);
		positionList=pages.getResultList();
		//防止该组合下真的没有头寸
		if(positionList!=null){
			for(SecPositionModel position:positionList){
				List<Object> item = new ArrayList<Object>();
				item.add(position.getSecID());
				item.add(position.getPortname());
				item.add(position.getCusip());
				item.add(position.getSecDesc());
				item.add(position.getSecType());
				//item.add(position.getAltDate());
				if(position.getLsindicator()==LongShortType.longTerm){
					item.add("多");
				}else{
					item.add("空");
				}
				item.add(DataConvert.convertCash2US(position.getAmount()));
				item.add(position.getCurCode());
				item.add(DataConvert.convertCash2US(position.getAmortValue()));
				item.add(DataConvert.convertCash2US(position.getCost()));
				item.add(DataConvert.convertCash2US(position.getMkValue()));
				item.add(DataConvert.convertCash2US(position.getAccrued()));
				item.add(position.getFuncCurCode());
				item.add(DataConvert.convertCash2US(position.getFuncCost()));
				item.add(DataConvert.convertCash2US(position.getFunMkValue()));
				item.add(DataConvert.convertCash2US(position.getAccruedFunc()));
				item.add(position.getFxRate());

				data.add(item);
			}
		}

		result.put("data", data);
		result.put("pages", pages.getTotalPages());
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value="/info/positionTradeDetail.do", method = RequestMethod.POST, name="证券头寸-交易详情")
    public void positionTradeDetail(@RequestParam(value = "secID", required = true) String secID,
    					   @RequestParam(value = "portID", required = true) String portID,
    					   @RequestParam(value = "start", required = true) String start,
    					   @RequestParam(value = "end", required = true) String end,
    					   @RequestParam(value = "num", required = false) int num,
    					   HttpServletResponse response) {
		HashMap<String, Object> trades = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("交易时间", "交易类型", "成交数量", "交易凭证号", "结算凭证号");
		trades.put("titles", titles);
		List<Object> tData = new ArrayList<Object>();
		List<List<String>> resultList=secCashService.loadTranHisSummary(portID, secID);
		for(List<String> fieldsList:resultList){
			List<Object> tItem = new ArrayList<Object>();
			for(String field:fieldsList){
				tItem.add(field);
			}
			tData.add(tItem);
		}
		trades.put("data", tData);
		trades.put("pages", 1);
		JsonUtil.writeObjToResponse(response, trades);
	}

	@RequestMapping(value="/info/positionAmortDetail.do", method = RequestMethod.POST, name="证券头寸-摊销详情")
    public void positionAmortDetail(@RequestParam(value = "secID", required = true) String secID,
    					   @RequestParam(value = "portID", required = true) String portID,
    					   @RequestParam(value = "start", required = true) String start,
    					   @RequestParam(value = "end", required = true) String end,
    					   @RequestParam(value = "num", required = false) int num,
    					   HttpServletResponse response) {
		HashMap<String, Object> amorts = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("日期", "批次", "票面价值", "期初摊余成本", "有效利率","利息收入", "息票率", "应收利息", "期末摊余成本", "市值", "偏离度");
		amorts.put("titles", titles);

		Date endDate=null;
		if(end.equals("")){
			endDate=new Date();
		}else{
			endDate=DateUtil.convertStr2Date2(end);
		}

		//摊销详情
		List<Object> tData =secCashService.positionAmortDetail(secID, portID,endDate);
		amorts.put("data", tData);
		amorts.put("pages", 1);
		JsonUtil.writeObjToResponse(response, amorts);
	}


	@RequestMapping(value="/info/nav.do", method = RequestMethod.POST, name="净值-查询")
    public void navReport(@RequestParam(value = "portfolio", required = true) String portfolio,
    					  @RequestParam(value = "start", required = true) String start,
    					  @RequestParam(value = "end", required = true) String end,
    					  @RequestParam(value = "num", required = false) int num,
    					  HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("日期", "单位净值", "累计净值", "总净值", "份额", "分红");
		result.put("titles", titles);
		Date searchDate=null;
		if(!end.equals("")){
			searchDate=DateUtil.convertStr2Date(end.replaceAll("-", ""));
		}else{
			searchDate=new Date();
		}

		List<NavAssetObj> navAssetObjList=new ArrayList<NavAssetObj>();
		Pages<NavAssetObj> pages=new Pages<NavAssetObj>(num);
		pages=secCashService.getNavObjList(pages,portfolio, searchDate);
		navAssetObjList=pages.getResultList();

		List<Object> data = new ArrayList<Object>();
		for(NavAssetObj navObj:navAssetObjList){
			List<Object> item = new ArrayList<Object>();
			item.add(DateUtil.convertD2YYYY_MM_DD(navObj.getNavDate()));
			item.add(navObj.getNavPerShare());
			item.add(navObj.getNavPerShare());
			item.add(DataConvert.convertCash2US(navObj.getNetAsset()));
			item.add(DataConvert.convertCash2US(navObj.getShares()));
			item.add(navObj.getDividends());
			data.add(item);
		}
		result.put("data", data);
		result.put("pages",pages.getTotalPages());
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value="/info/navDetail.do", method = RequestMethod.POST, name="净值-详情")
    public void navDetail( @RequestParam(value = "portID", required = true) String portID,
    					   @RequestParam(value = "navDate", required = true) String date,
    					   HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		ArrayList<Object> data = new ArrayList<Object>();
		List<Nav> navList=secCashService.getNavDetail(portID, DateUtil.convertStr2Date2(date));
		PortfolioHdr portfolio=commService.loadPortfolio(portID);

		Nav nav1=navList.get(0);
		Nav nav2=navList.get(1);
		data.add(portID);
		data.add(portfolio.getShortName());
		data.add(date);
		data.add(RoundUtil.roundMoney(nav2.getNavAmount(),portfolio.getNavPrecision()));

		data.add(DataConvert.convertAmount2US(nav1.getAsset()));
		data.add(DataConvert.convertAmount2US(nav1.getLiabilities()));
		data.add(DataConvert.convertAmount2US(nav2.getIncome()));
		data.add(DataConvert.convertAmount2US(nav2.getExpenses()));
		data.add(DataConvert.convertAmount2US(nav2.getCapital()));
		data.add(DataConvert.convertAmount2US(nav2.getCapitalUnreal()*-1));
		data.add(DataConvert.convertAmount2US(nav2.getCapitalRG()));

		data.add(DataConvert.convertAmount2US(nav2.getSharesSettled()));
		data.add(RoundUtil.roundMoney(nav2.getNavAmount(),portfolio.getNavPrecision()));
		data.add(0);
		result.put("data", data);
		List<String> titles = Arrays.asList("组合编号", "组合名称", "净值时间", "单位净值", "资产", "负债", "收入", "开支", "实收资本", "已实现损益", "未实现损益", "基金份额", "累计单位净值", "分红");
		result.put("titles", titles);
		JsonUtil.writeObjToResponse(response, result);
	}

	/**
	 * 获取某个组合最近的一个总净值
	 * @param portfolio
	 * @param response
	 */
	@RequestMapping(value="/info/latestTotalNAV.do", method = RequestMethod.POST)
    public void latestTotalNAV(@RequestParam(value = "portfolio", required = true) String portfolio, HttpServletResponse response) {
		Pages<NavAssetObj> pages=new Pages<NavAssetObj>();
		pages=secCashService.getNavObjList(pages,portfolio, DateUtil.convertStr2Date("29991231"));
		List<NavAssetObj> navList=pages.getResultList();
		NavAssetObj nav=navList.get(0);
		JsonUtil.writeObjToResponse(response, nav.getNetAsset());
	}

	/**
	 * 给出一个组合,总净值,总份额计算单位净值
	 * @param portID
	 * @param totalNAV
	 * @param shares
	 * @param response
	 */
	@RequestMapping(value="/info/calcNAV.do", method = RequestMethod.POST)
    public void calcNAV(@RequestParam(value = "portfolio", required = true) String portID,
    				    @RequestParam(value = "totalNAV", required = true) double totalNAV,
    				    @RequestParam(value = "shares", required = true) double shares,
    			        HttpServletResponse response) {
		PortfolioHdr portfolio=commService.loadPortfolio(portID);
		JsonUtil.writeObjToResponse(response, RoundUtil.roundMoney(totalNAV/shares, portfolio.getNavPrecision()));
	}

	//TODO: 给出一个组合,总净值,单位净值，计算总份额
	@RequestMapping(value="/info/calcShares.do", method = RequestMethod.POST)
	public void calcShares(@RequestParam(value = "portfolio", required = true) String portID,
						@RequestParam(value = "totalNAV", required = true) double totalNAV,
	    				@RequestParam(value = "NAV", required = true) double NAV,
	    				HttpServletResponse response) {
			JsonUtil.writeObjToResponse(response, RoundUtil.roundMoney(totalNAV/NAV, 0));
		}

	@RequestMapping(value="/info/rd.do", method = RequestMethod.POST, name="实收实付-查询")
    public void rdReport(@RequestParam(value = "portfolio", required = true) String portfolio,
    					  @RequestParam(value = "start", required = true) String start,
    					  @RequestParam(value = "end", required = true) String end,
    					  @RequestParam(value = "num", required = false) int num,
    					  HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号","日期", "实收实付描述", "货币", "剩余金额", "原始金额", "类型", "证券代码", "银行", "银行账户", "银行账户描述", "应收应付编号", "交易编号");
		result.put("titles", titles);

		List<Object> data = new ArrayList<Object>();
		//String portNum=Integer.valueOf(portfolio);

		Pages<BkCashFlow> pages=new Pages<BkCashFlow>(num);

		Date startDate=DateUtil.convertStr2DateAll(start);
		Date endDate=DateUtil.convertStr2DateAll(end);
		Pages<BkCashFlow> cashFlowPage=secCashService.getCashFlow(pages, portfolio, startDate, endDate);
		List<BkCashFlow> cashFlowList=cashFlowPage.getResultList();
		for(BkCashFlow cashFlow:cashFlowList){
			List<Object> item = new ArrayList<Object>();
			item.add(cashFlow.getId());
			item.add(DateUtil.convertD2YYYY_MM_DD(cashFlow.getBankcashdate()));
			item.add(cashFlow.getCashdesc());
			item.add(secCashService.loadFxCurrCode(cashFlow.getFxcurrency()));
			item.add(DataConvert.convertCash2US(cashFlow.getFxremainbal()));
			item.add(DataConvert.convertCash2US(cashFlow.getFxorigbal()));
			item.add(DBCR.ysyfOptionsHM.get(cashFlow.getDbcr()));
			if(cashFlow.getCusip() != null)
				item.add(cashFlow.getCusip().getSecurityid());
			else
				item.add("");

			item.add(cashFlow.getBkCashAcct().getBkinfo().getBankDesc());
			item.add(cashFlow.getBkCashAcct().getExternalacctnumber());
			item.add(cashFlow.getBkCashAcct().getBankdesc());
			item.add(cashFlow.getRcpyid().getId());
			item.add(cashFlow.getRcpyid().getTanum().getId());
			data.add(item);
		}
		result.put("data", data);
		result.put("pages", pages.getTotalPages());

		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value="/info/rcpy.do", method = RequestMethod.POST, name="应收应付-查询")
    public void rcpyReport(@RequestParam(value = "portfolio", required = true) String portfolio,
    					   @RequestParam(value = "start", required = true) String start,
    					   @RequestParam(value = "end", required = true) String end,
    					   @RequestParam(value = "num", required = false) int num,
    					   HttpServletResponse response) {


		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号","到期日期","生成日期","应收应付描述", "货币", "剩余结算金额", "原始结算金额", "类型", "证券代码", "银行", "银行账户", "交易编号");
		result.put("titles", titles);

		List<Object> data = new ArrayList<Object>();
		Date startDate=DateUtil.convertStr2DateAll(start);
		Date endDate=DateUtil.convertStr2DateAll(end);

		List<BkCashRcpy> rcpyList=new ArrayList<BkCashRcpy>();
		Pages<BkCashRcpy> pages=new Pages<BkCashRcpy>(num);
		Pages<BkCashRcpy> BkCashRcpypages=secCashService.getCashRcpy(pages,portfolio, startDate, endDate);
		rcpyList=BkCashRcpypages.getResultList();

		String rcpydc="";
		String rcpyMsg="";
		for(BkCashRcpy rcpy:rcpyList){
			List<Object> item = new ArrayList<Object>();
			rcpydc=DBCR.dcOptionsHM.get(rcpy.getDbcr());

			rcpyMsg=rcpy.getRcpyDesc();
			item.add(rcpy.getId());
			item.add(DateUtil.convertD2YYYY_MM_DD(rcpy.getPaydate()));
			item.add(DateUtil.convertD2YYYY_MM_DD(rcpy.getLedger()));
			item.add(rcpyMsg);
			if(rcpy.getFxcurrency()==null){
				continue;
			}else{
				item.add(secCashService.loadFxCurrCode(rcpy.getFxcurrency()));
			}
			item.add(DataConvert.convertCash2US(rcpy.getFxremainbal()));
			item.add(DataConvert.convertCash2US(rcpy.getFxorigbal()));
			item.add(rcpydc);
			if(rcpy.getCusip()==null){
				item.add("");
			}else{
				item.add(rcpy.getCusip().getSecurityid());
			}
			item.add(rcpy.getBkCashAcct().getBkinfo().getBankDesc());
			item.add(rcpy.getBkCashAcct().getExternalacctnumber());
			item.add(rcpy.getTanum().getId());
			data.add(item);
		}
		result.put("data", data);
		result.put("pages",pages.getTotalPages());
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value="/info/rcpyCreated.do", method = RequestMethod.POST)
    public void rcpyCreated(@RequestParam(value = "portfolio", required = true) String portfolio,
    					   @RequestParam(value = "start", required = true) String start,
    					   @RequestParam(value = "end", required = true) String end,
    					   @RequestParam(value = "num", required = false) int num,
    					   HttpServletResponse response) {


		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("到期日期","生成日期","应收应付描述", "货币", "剩余结算金额", "原始结算金额", "类型", "证券代码", "银行", "银行账户", "交易编号");
		result.put("titles", titles);

		List<Object> data = new ArrayList<Object>();

		Pages<BkCashRcpy> pages=new Pages<BkCashRcpy>(num);

		Date startDate=DateUtil.convertStr2DateAll(start);
		Date endDate=DateUtil.convertStr2DateAll(end);
		Pages<BkCashRcpy> BkCashRcpypages=secCashService.getCashRcpy(pages,portfolio, startDate, endDate);
		List<BkCashRcpy> rcpyList=BkCashRcpypages.getResultList();
		String rcpydc="";
		String rcpyMsg="";
		for(BkCashRcpy rcpy:rcpyList){
			List<Object> item = new ArrayList<Object>();
			rcpydc=DBCR.dcOptionsHM.get(rcpy.getDbcr());

			rcpyMsg=SecurityCoreFactory.getAllTranTypeHM().get(rcpy.getTrantype());
			if(rcpy.getRcpyDesc() != null){
				rcpyMsg=rcpyMsg+ "-"+rcpy.getRcpyDesc();
			}

			item.add(DateUtil.convertD2YYYY_MM_DD(rcpy.getPaydate()));
			item.add(DateUtil.convertD2YYYY_MM_DD(rcpy.getLedger()));
			item.add(rcpyMsg);
			item.add(secCashService.loadFxCurrCode(rcpy.getFxcurrency()));
			item.add(DataConvert.convertCash2US(rcpy.getFxremainbal()));
			item.add(DataConvert.convertCash2US(rcpy.getFxorigbal()));
			item.add(rcpydc);
			if(rcpy.getCusip()==null){
				item.add("");
			}else{
				item.add(rcpy.getCusip().getSecurityid());
			}
			item.add(rcpy.getBkCashAcct().getBkinfo().getBankDesc());
			item.add(rcpy.getBkCashAcct().getExternalacctnumber());
			item.add(rcpy.getTanum().getId());
			data.add(item);
		}
		result.put("data", data);
		result.put("pages", pages.getTotalPages());
		JsonUtil.writeObjToResponse(response, result);
	}
	//TODO-更改RCPY的时间和账户信息
	@RequestMapping(value="/info/updateRCPY.do", method = RequestMethod.POST, name="应收应付-更新")
	public void updateRCPY(@RequestParam(value = "json", required = true) String json,
							HttpServletRequest request,
			 			    HttpServletResponse response){
		System.out.println(json);
		JsonUtil.writeObjToResponse(response, true);
	}

	@RequestMapping(value="/info/settleRCPY.do", produces = "application/json; charset=utf-8",method = RequestMethod.POST, name="应收应付-结转")
	@ResponseBody
	public Object settleRCPY(@RequestParam(value = "json", required = true) String json,
							HttpServletRequest request,
			 			    HttpServletResponse response){
		MsgContainer container=commService.executeMsg(request, json);
		return commService.controllerReturn(container);
	}

	@RequestMapping(value="/info/deleteCash.do", produces = "application/json; charset=utf-8",method = RequestMethod.POST, name="实收实付-删除")
	@ResponseBody
	public Object deleteCash(@RequestParam(value = "id", required = true) String id,
							HttpServletRequest request,
			 			    HttpServletResponse response){
		HashMap<String, String> msghm=new HashMap<String, String>();
		msghm.put(MsgFields.header, "rcpy2CashMsg");
		msghm.put(MsgFields.operation, "DELETE");
		msghm.put(MsgFields.id, id);

		MsgContainer container=commService.executeMsg(request, JSONObject.toJSONString(msghm));
		return commService.controllerReturn(container);
	}

	@RequestMapping(value="/info/getRCPY.do", method = RequestMethod.POST, name="应收应付-详情")
    public void getRCPY(@RequestParam(value = "rcpyNum", required = true) int rcpyNum,
    					HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("到期日期", "应收应付描述", "货币", "剩余结算金额", "原始结算金额", "类型", "证券代码", "银行", "银行账户");
		result.put("titles", titles);

		List<Object> data = new ArrayList<Object>();
		List<Object> item = new ArrayList<Object>();

		BkCashRcpy rcpy=secCashService.loadCashRcpy(rcpyNum);
		item.add(DateUtil.convertD2YYYY_MM_DD(rcpy.getExpdcashdate()));
		item.add(rcpy.getRcpyDesc());
		item.add(secCashService.loadFxCurrCode(rcpy.getFxcurrency()));
		item.add(DataConvert.convertCash2US(rcpy.getRemainbal()));
		item.add(DataConvert.convertCash2US(rcpy.getOrigbal()));
		item.add(DBCR.dcOptionsHM.get(rcpy.getDbcr()));
		if(rcpy.getTanum().getCusip()!=null){
			item.add(rcpy.getTanum().getCusip().getSecurityid());
		}else{
			item.add("-");
		}

		item.add(rcpy.getBkCashAcct().getBkinfo().getBankDesc());
		item.add(rcpy.getBkCashAcct().getBankdesc());
		data.add(item);

		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value="/info/getRCPYBrief.do", method = RequestMethod.POST)
    public void getRCPYBrief(@RequestParam(value = "rcpyNum", required = true) int rcpyNum,
    					HttpServletResponse response) {



		HashMap<String, Object> item = new HashMap<String, Object>();
		BkCashRcpy rcpy=secCashService.loadCashRcpy(rcpyNum);
		item.put("settleDate", DateUtil.convertD2YYYY_MM_DD(rcpy.getExpdcashdate()));
		item.put("secAcct", rcpy.getBkCashAcct().getSecAcctCode());
		item.put("bankAcct", rcpy.getBkCashAcct().getId());
		item.put("port", rcpy.getPortfolio().getId());
		item.put("remainBal", DataConvert.convertCash2US(rcpy.getRemainbal()));
		item.put("origBal", DataConvert.convertCash2US(rcpy.getOrigbal()));
		JsonUtil.writeObjToResponse(response, item);
	}

	@RequestMapping(value = "/info/OutSecPosition.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object outSecPosition(@RequestParam(value = "portnum", required = true) String portnum,
						@RequestParam(value = "asOfDate", required = true) java.sql.Date asOfDate,
    					HttpServletResponse response){
		return outDate.genResults(portnum, asOfDate);
	}

	@RequestMapping(value="/info/useageCashPosition.do", produces = "application/json; charset=utf-8")
	@ResponseBody
    public Object cashPosition(@RequestParam(value = "portnum", required = true) String portnum,
    						 @RequestParam(value = "asOfDate", required = true) java.sql.Date asOfDate,
    						 HttpServletResponse response) {
		return secCashService.getUseageCashPosition(portnum, asOfDate);
	}


	@RequestMapping(value="/info/compareVoucer.do", method = RequestMethod.POST)
    public void compareStandardVoucher(@RequestParam(value = "port", required = true) String portID,
    						@RequestParam(value = "date", required = true) String date,
    						HttpServletRequest request,
    						HttpServletResponse response){

		String[] port={portID};
		Date ledgerDate=DateUtil.convertStr2Date2(date);
		ResultContainer container=voucherService.testCaseDailyVoucher(port, ledgerDate);
		//将比较的结果返回到JixuTest
		JsonUtil.writeObjToResponse(response, container);
	}

	/**
	 * 理财产品的类型
	 * @param response
	 */
	@RequestMapping(value="/info/portProductTypes.do", method = RequestMethod.POST)
	public void portProductTypes(HttpServletResponse response){
		JsonUtil.writeObjToResponse(response, OptionType.portProductTypesHM);
	}

//	@RequestMapping(value="/info/getJoyinSession.do", method = RequestMethod.POST)
//	public void testGetSession(HttpServletResponse response) {
//		final List<String> permissionModules = (List<String>)SecurityUtil.getFromSession(GlobalConstant.SESSION_ALL_MODULES);
//		HashMap<String, Object> result = new HashMap<String, Object>();
//		result.put("session", permissionModules);
//		JsonUtil.writeObjToResponse(response, result);
//	}

	@RequestMapping(value="/info/getSessionBtn.do", method = RequestMethod.POST)
	public void testGetBtn(@RequestParam(value = "buttonId", required = false) String buttonId,
			HttpServletRequest request,HttpServletResponse response) {
		JsonUtil.writeObjToResponse(response, SecurityUtil.isPermitted(buttonId));
	}

}
