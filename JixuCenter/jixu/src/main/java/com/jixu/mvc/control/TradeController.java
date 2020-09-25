package com.jixu.mvc.control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jixu.common.controller.ExceptionControl;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.func.SecPositionCenter;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.LongShortType;
import com.jixu.common.type.LotSelectionType;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.MsgTemplate;
import com.jixu.common.type.PortTranMapType;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.TradeMarket;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.CorpActionTypes;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.IncomeExpenseType;
import com.jixu.common.types.LotType;
import com.jixu.common.types.OptionType;
import com.jixu.common.types.TradeSettleLedger;
import com.jixu.common.types.TranFeeTypes;
import com.jixu.common.types.ValuationType;
import com.jixu.common.util.DataConvert;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.JsonUtil;
import com.jixu.common.util.MsgPackage;
import com.jixu.common.util.Pages;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.SmfDivDao;
import com.jixu.dao.intf.TranBasicDao;
import com.jixu.dao.intf.TranFeeDefDao;
import com.jixu.dao.intf.TranSumDao;
import com.jixu.framework.msg.MsgFactory;
import com.jixu.msg.dto.CashDividendDto;
import com.jixu.msg.dto.InterestSwapMaturityDto;
import com.jixu.msg.dto.StockDividendDto;
import com.jixu.msg.dto.StockNewReceiveDto;
import com.jixu.msg.dto.StockResverseSplitDto;
import com.jixu.msg.dto.StockSplitDto;
import com.jixu.msg.dto.SwapFXOpenDto;
import com.jixu.mvc.control.model.SecPositionModel;
import com.jixu.mvc.control.model.SecTradeDetailModel;
import com.jixu.mvc.control.model.SecTradeSummaryModel;
import com.jixu.mvc.control.model.VoucherDetailsView;
import com.jixu.mvc.control.service.ConfigService;
import com.jixu.mvc.control.service.SecCashService;
import com.jixu.mvc.control.service.TradeService;
import com.jixu.secTran.SecurityAssetService;
import com.jixu.secTran.SecurityBasic;
import com.jixu.secTran.SecurityCoreFactory;
import com.joyintech.calculation.config.NumberEnum;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.orm.obj.BkCashAcct;
import com.orm.obj.CashFlowCfg;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.SmfDiv;
import com.orm.obj.TranBasic;
import com.orm.obj.TranBasicFee;
import com.orm.obj.TranCollateral;
import com.orm.obj.TranFeeDef;
import com.orm.obj.TranSum;

@Controller
public class TradeController extends ExceptionControl{
	
	@Resource(name="tradeService")
	private TradeService tradeService;
	
	@Resource(name="secCoreFactory")
	private SecurityCoreFactory secCoreFactory;
	
	@Resource(name="commfuncService")
	private CommFuncService commService;
	
	@Resource(name="msgPackage")
	protected MsgPackage msgPackage;
	
	@Resource(name="msgFactory")
	private MsgFactory msgFactory;
	
	@Autowired
	private IssueInfoDao issueInfoDao;
	
	@Autowired
	private TranFeeDefDao tranFeeDefDao; 
	
	@Resource(name="SecCashService")
	private SecCashService secCashService;
	
	@Autowired
	private TranBasicDao tranBasicDao;
	
	@Autowired
	protected TranSumDao tranSumDao;
	
	@Autowired
	private SmfDivDao smfDivDao;
	
	@Resource(name = "configService")
	private ConfigService configService;
	
	@Resource(name="secPositionCenter")
	private SecPositionCenter posCenter; 
	
	@RequestMapping(value="/trade.do", method = RequestMethod.GET, name="交易-页面显示")
	public String trade() {
		return "trade";
	}
	
	@RequestMapping(value="/trade/cash.do", method = RequestMethod.POST, name="交易-现金交易查询")  
    public void cashTradeList(@RequestParam(value = "config", required = true) String resources,
    					  	  @RequestParam(value = "type", required = true) int type,
    					      @RequestParam(value = "portfolio", required = true) String portfolio,
    					      @RequestParam(value = "start", required = true) String startStr,
    					      @RequestParam(value = "end", required = true) String endStr,
    					      @RequestParam(value = "displayDelete", required = true) Boolean displayDelete,
    					      @RequestParam(value = "num", required = false) int num,
    					      HttpServletResponse response) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		Pages<SecTradeSummaryModel> pages=new Pages<SecTradeSummaryModel>(num);
		
		Date start=DateUtil.convertStr2DateAll(startStr);
		Date end=DateUtil.convertStr2DateAll(endStr);
		
		if(type==TransTypes.CASHMOVE){
			List<String> titles = Arrays.asList("交易编号","投资组合","交易类型", "交易日期", "结转日期", "转入银行账户","转入货币", "转入金额", "转入汇率", "转出银行账户", "转出货币", "转出金额","转出汇率","数据来源");
			result.put("titles", titles);
			
			pages=tradeService.loadCashTranList(portfolio, start, end,pages,displayDelete);
			List<SecTradeSummaryModel> summaryList=pages.getResultList();
			
			List<Object> data = new ArrayList<Object>();
			
			for(SecTradeSummaryModel tran:summaryList){
				List<Object> item = new ArrayList<Object>();
				item.add(tran.getTanum());
				item.add(tran.getPortname());
				item.add(tran.getTrantype());
				item.add(tran.getTradeDate());
				item.add(tran.getSetDate());
				item.add(tran.getBankAcct());
				item.add(tran.getCurrency());
				item.add(tran.getAmount());
				item.add(tran.getRate());
				item.add(tran.getBankAcct2());
				item.add(tran.getCurrency2());
				item.add(tran.getAmount2());
				item.add(tran.getRate2());
				item.add("数据来源");
				data.add(item);
			}
			
			result.put("data", data);
			result.put("pages", pages.getTotalPages());	
			
		}else if(type==TransTypes.CASHOFF || type==TransTypes.CashContribution){
			List<String> titles = Arrays.asList("交易编号","投资组合","过账状态", "交易类型", "借贷类型", "交易日期", "结转日期", "冲销科目", "银行账户","货币", "金额", "汇率","交易描述");
			result.put("titles", titles);
			
			pages=tradeService.loadCashOffList(portfolio, start, end, type,pages,displayDelete);
			List<SecTradeSummaryModel> summaryList=pages.getResultList();
			
			List<Object> data = new ArrayList<Object>();
			
			for(SecTradeSummaryModel tran:summaryList){
				List<Object> item = new ArrayList<Object>();
				item.add(tran.getTanum());
				item.add(tran.getPortname());
				item.add(tran.getPostType());
				item.add(tran.getTrantype());
				item.add(tran.getDbcr());
				item.add(tran.getTradeDate());
				item.add(tran.getSetDate());
				if(tran.getGlAccount()==null){
					item.add("");
				}else{
					item.add(tran.getGlAccount());
				}
				if(tran.getAmount()!=0){
					item.add(tran.getBankAcct());
					item.add(tran.getCurrency());
					item.add(tran.getAmount());
					item.add(tran.getRate());
				}else{
					item.add(tran.getBankAcct2());
					item.add(tran.getCurrency2());
					item.add(tran.getAmount2());
					item.add(tran.getRate2());
				}
				if(tran.getTranDesc()==null){
					item.add("");
				}else{
					item.add(tran.getTranDesc());
				}
				data.add(item);
			}
			
			result.put("data", data);
			result.put("pages", pages.getTotalPages());	
		}
		
			
		JsonUtil.writeObjToResponse(response, result);
	}
	
	
	@RequestMapping(value="/trade/newCash.do", method = RequestMethod.POST, name="交易-新现金交易查询")  
    public void newCashTradeList(@RequestParam(value = "config", required = true) String resources,
    					  	  @RequestParam(value = "type", required = true) int type,
    					      @RequestParam(value = "portfolio", required = true) String portfolio,
    					      @RequestParam(value = "start", required = true) String startStr,
    					      @RequestParam(value = "end", required = true) String endStr,
    					      @RequestParam(value = "displayDelete", required = true) Boolean displayDelete,
    					      @RequestParam(value = "page", required = true) int num,
 	    					  @RequestParam(value = "limits", required = true) int limits,
 	    					  @RequestParam(value = "export2excel", required = false) String export2excel,
    					      HttpServletResponse response) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		//判断是否需要导出  如果导出  limits需要很大
		limits=pageLimits(export2excel,limits);
		
		Pages<SecTradeSummaryModel> pages=new Pages<SecTradeSummaryModel>(num,limits);
		
		Date start=DateUtil.convertStr2DateAll(startStr);
		Date end=DateUtil.convertStr2DateAll(endStr);
		
		if(type==TransTypes.CASHMOVE){
			pages=tradeService.loadCashTranList(portfolio, start, end,pages,displayDelete);
			List<SecTradeSummaryModel> summaryList=pages.getResultList();
			
			List<Object> data = new ArrayList<Object>();
			
			for(SecTradeSummaryModel tran:summaryList){
				List<Object> item = new ArrayList<Object>();
				item.add(tran.getTanum());
				item.add(tran.getPortname());
				item.add(tran.getTrantypeCode());
				item.add(tran.getTrantype());
				item.add(tran.getTradeDate());
				item.add(tran.getSetDate());
				item.add(tran.getBankAcct());
				item.add(tran.getCurrency());
				item.add(tran.getAmount());
				item.add(tran.getRate());
				item.add(tran.getBankAcct2());
				item.add(tran.getCurrency2());
				item.add(tran.getAmount2());
				item.add(tran.getRate2());
				item.add("数据来源");
				data.add(item);
			}
			
			result.put("data", data);
			result.put("pages", pages.getTotalPages());	
			result.put("totalCount", pages.getTotalCount());	
			
		}else if(type==TransTypes.CASHOFF || type==TransTypes.CashContribution){			
			pages=tradeService.loadCashOffList(portfolio, start, end, type,pages,displayDelete);
			List<SecTradeSummaryModel> summaryList=pages.getResultList();
			
			List<Object> data = new ArrayList<Object>();
			
			for(SecTradeSummaryModel tran:summaryList){
				List<Object> item = new ArrayList<Object>();
				item.add(tran.getTanum());
				item.add(tran.getPortname());
				item.add(tran.getPostType());
				item.add(tran.getTrantypeCode());
				item.add(tran.getTrantype());
				item.add(tran.getDbcr());
				item.add(tran.getTradeDate());
				item.add(tran.getSetDate());
				if(tran.getGlAccount()==null){
					item.add("");
				}else{
					item.add(tran.getGlAccount());
				}
				if(tran.getAmount()!=0){
					item.add(tran.getBankAcct());
					item.add(tran.getCurrency());
					item.add(tran.getAmount());
					item.add(tran.getRate());
				}else{
					item.add(tran.getBankAcct2());
					item.add(tran.getCurrency2());
					item.add(tran.getAmount2());
					item.add(tran.getRate2());
				}
				if(tran.getTranDesc()==null){
					item.add("");
				}else{
					item.add(tran.getTranDesc());
				}
				data.add(item);
			}
			
			result.put("data", data);
			result.put("pages", pages.getTotalPages());
			result.put("totalCount", pages.getTotalCount());	
		}
		
		//导出excel或返给前台显示
		returnControllerList(export2excel,result,response);
	}
	
	
	@RequestMapping(value="/trade/shareholder.do", method = RequestMethod.POST, name="交易-股东交易查询")  
    public void shareholderTradeList(@RequestParam(value = "config", required = true) String resources,
    					  @RequestParam(value = "type", required = true) int type,
    					  @RequestParam(value = "portfolio", required = true) String portfolio,
    					  @RequestParam(value = "start", required = true) String startStr,
    					  @RequestParam(value = "end", required = true) String endStr,
    					  @RequestParam(value = "displayDelete", required = true) Boolean displayDelete,
    					  @RequestParam(value = "num", required = false) int num,
    					  HttpServletResponse response) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		Pages<SecTradeSummaryModel> pages=new Pages<SecTradeSummaryModel>(num);
		
		List<String> titles = Arrays.asList("交易编号","投资组合","过账状态", "交易时间", "结算时间", "交易类型","金额", "份额", "费用", "数据来源");
		result.put("titles", titles);
		
		Date start=DateUtil.convertStr2DateAll(startStr);
		Date end=DateUtil.convertStr2DateAll(endStr);
		pages=tradeService.loadSubRedTranList(portfolio, type, start, end,pages,displayDelete);
		List<SecTradeSummaryModel> subredTranList=pages.getResultList();
		
		List<Object> data = new ArrayList<Object>();
		for(SecTradeSummaryModel tran:subredTranList){
			List<Object> item = new ArrayList<Object>();
			item.add(tran.getTanum());
			item.add(tran.getPortname());
			item.add(tran.getPostType());
			item.add(tran.getTradeDate());
			item.add(tran.getSetDate());
			item.add(tran.getTrantype());
			item.add(tran.getCost());
			item.add(tran.getAmount());
			item.add(tran.getFee());
			item.add("交易接口");
			data.add(item);
		}
		result.put("data", data);
		result.put("pages", pages.getTotalPages());	
		JsonUtil.writeObjToResponse(response, result);
	}
	
	/**
	 * 交易查询
	 * @param resources
	 * @param type
	 * @param smf
	 * @param portfolio
	 * @param start
	 * @param end
	 * @param num
	 * @param response
	 */
	@RequestMapping(value="/trade/trade.do", method = RequestMethod.POST, name="交易-投资组合交易查询")  
    public void transactionList(@RequestParam(value = "config", required = true) String resources,
    					        @RequestParam(value = "type", required = true) String type,
    					        @RequestParam(value = "smf", required = true) String smf,
    					        @RequestParam(value = "portfolio", required = true) String portfolio,
    					        @RequestParam(value = "start", required = true) String start,
    					        @RequestParam(value = "end", required = true) String end,
    					        @RequestParam(value = "displayDelete", required = true) Boolean displayDelete,
    					        @RequestParam(value = "num", required = false) int num,
    					        HttpServletResponse response) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号","投资组合","交易类型","过账状态","证券代码","证券名称","交易时间","结算时间", "数量","本金", "费用","利息");
		result.put("titles", titles);
		
		String secType=type;
		
		String[] smfArr=smf.split("\\|");
		String secID="";
		if(smfArr.length==3){
			secID=smfArr[1];
		}else{
			secID=smf;
		}
		Date startDate=DateUtil.convertStr2DateAll(start);
		Date endDate=DateUtil.convertStr2DateAll(end);
		
		Pages<SecTradeSummaryModel> pages=new Pages<SecTradeSummaryModel>(num);
		pages=tradeService.loadTranBasicList(pages,secType, secID, portfolio, startDate, endDate,displayDelete);
		List<SecTradeSummaryModel> tradeList=pages.getResultList();
		
		List<Object> data = new ArrayList<Object>();
		
		for(SecTradeSummaryModel trade:tradeList){
			List<Object> item = new ArrayList<Object>();
			item.add(trade.getTanum());
			item.add(trade.getPortname());
			item.add(trade.getTrantype());
			item.add(trade.getPostType());
			item.add(trade.getSecID());
			item.add(trade.getSecurityName());
			item.add(trade.getTradeDate());
			item.add(trade.getSetDate());
			item.add(trade.getAmount());
			item.add(trade.getCost());
			item.add(trade.getFee());
			item.add(trade.getInterest());
			data.add(item);
		}
		result.put("data", data);
		result.put("pages", pages.getTotalPages());	
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/trade/newTradeList.do", method = RequestMethod.POST, name="交易-新交易查询")  
    public void newTradeList(@RequestParam(value = "config", required = true) String resources,
    					        @RequestParam(value = "type", required = true) String type,
    					        @RequestParam(value = "smf", required = true) String smf,
    					        @RequestParam(value = "portfolio", required = true) String portfolio,
    					        @RequestParam(value = "start", required = true) String start,
    					        @RequestParam(value = "end", required = true) String end,
    					        @RequestParam(value = "displayDelete", required = true) Boolean displayDelete,
    					        @RequestParam(value = "page", required = true) int num,
   	    					  	@RequestParam(value = "limits", required = true) int limits,
   	    					  	@RequestParam(value = "export2excel", required = false) String export2excel,
    					        HttpServletResponse response) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号","投资组合","交易编码","交易类型","过账状态","证券代码","证券名称","交易时间","结算时间", "数量","本金", "费用","利息");
		result.put("titles", titles);
		
		String secType=type;
		
		String[] smfArr=smf.split("\\|");
		String secID="";
		if(smfArr.length==3){
			secID=smfArr[1];
		}else{
			secID=smf;
		}
		Date startDate=DateUtil.convertStr2DateAll(start);
		Date endDate=DateUtil.convertStr2DateAll(end);
		
		//判断是否需要导出  如果导出  limits需要很大
		limits=pageLimits(export2excel,limits);
		
		Pages<SecTradeSummaryModel> pages=new Pages<SecTradeSummaryModel>(num,limits);
		
		pages=tradeService.loadTranBasicList(pages,secType, secID, portfolio, startDate, endDate,displayDelete);
		List<SecTradeSummaryModel> tradeList=pages.getResultList();
		
		List<Object> data = new ArrayList<Object>();
		
		for(SecTradeSummaryModel trade:tradeList){
//			if(trade.getPostType().equals("未过账")){
//				continue;
//			}
			List<Object> item = new ArrayList<Object>();
			item.add(trade.getTanum());
			item.add(trade.getPortname());
			item.add(trade.getTrantypeCode());
			item.add(trade.getTrantype());
			item.add(trade.getPostType());
			item.add(trade.getSecID());
			item.add(trade.getSecurityName());
			item.add(trade.getTradeDate());
			item.add(trade.getSetDate());
			item.add(DataConvert.convertCashNoCalFormat(trade.getAmount()));
			if(secType.equals(AssetTypes.SWAP_FX)) {
				item.add(DataConvert.convertCashNoCalFormat(trade.getAmount2()));
			}else {
				item.add(DataConvert.convertCashNoCalFormat(trade.getCost()));
			}
			item.add(DataConvert.convertCashNoCalFormat(trade.getFee()));
			item.add(DataConvert.convertCashNoCalFormat(trade.getInterest()));
			data.add(item);
		}
		result.put("data", data);
		result.put("pages", pages.getTotalPages());	
		result.put("totalCount", pages.getTotalCount());	
		//导出excel或返给前台显示
		returnControllerList(export2excel,result,response);
	}
	//开仓交易tranbasic信息查询
	@RequestMapping(value="/trade/FCOpenTrade.do", method = RequestMethod.POST)  
    public void FCOpentrade( @RequestParam(value = "smf", required = true) String smf,
    					    @RequestParam(value = "portfolio", required = false) String portfolio,
    					    HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String[] smfArr=smf.split("\\|");
		String secID="";
		if(smfArr.length==3){
			secID=smfArr[0];
		}else{
			secID=smf;
		}
		TranBasic tb=tradeService.getFCOpenTranBasic(portfolio, secID,TransTypes.FORWORD_CURRENCY_OPEN);
		if(tb!=null){
			result.put(MsgFields.longCurID, tb.getFxcurrency());
			result.put(MsgFields.shortCurID, tb.getFxcurrency2());
			result.put(MsgFields.tradeCash, Math.abs(tb.getAmount()));
			result.put(MsgFields.tradeCash2, Math.abs(tb.getAmount2()));
			result.put(MsgFields.longFX, tb.getFxrate());
			result.put(MsgFields.shortFX, tb.getFxrate2());
			result.put(MsgFields.bankAcct, tb.getBkCashacctNo().getId());
			result.put(MsgFields.bankAcct2, tb.getBkCashacctNo2().getId());
		}
		JsonUtil.writeObjToResponse(response, result);
	}
	
	//利率互换信息查询
	@RequestMapping(value = "/trade/swapInterestTrade.do", method = RequestMethod.POST)
	public void swapInterestTrade(@RequestParam(value = "smf", required = true) String smf,
			@RequestParam(value = "portfolio", required = true) String portfolio, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String[] smfArr = smf.split("\\|");
		String secID = "";
		if (smfArr.length == 3) {
			secID = smfArr[0];
		} else {
			secID = smf;
		}
		
		TranBasic tb=tradeService.getFCOpenTranBasic(portfolio, secID,TransTypes.SWAP_OPEN_Interest);
		if(tb!=null){
			IssueInfo sec=tb.getCusip();
			result.put(MsgFields.principal, tb.getAmount());
			result.put(MsgFields.incomeCurNo, sec.getIncomeCur());
			result.put(MsgFields.tradeFX, tb.getFxrate());
			result.put(MsgFields.bankAcct, tb.getBkCashacctNo().getId());
			result.put(MsgFields.principal2, tb.getAmount2());
			result.put(MsgFields.incomeCurNo2, sec.getIncomeCur2());
			result.put(MsgFields.tradeFX2, tb.getFxrate2());
			result.put(MsgFields.bankAcct2, tb.getBkCashacctNo2().getId());
		}
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value="/trade/getTrade.do", method = RequestMethod.POST, name="交易-投资组合交易获取")  
    public void getTrade(@RequestParam(value = "tradeNum", required = true) String tradeNumStr,
    					HttpServletResponse response) {

		String tradeNum="";
		if(!tradeNumStr.endsWith("#")){
			tradeNum=tradeNumStr;
		}else{
			//临时处理，凭证查询时使用，当交易编号以#结尾，则判定为估增或者收入日记帐，则去查询头寸相关.这时传入的tradenum实则是trancloid
			tradeNum=tradeNumStr.replace("#", "");
			getGeneralIncomePostion(response, tradeNum);
			return;
		}
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("交易编号", "类型", "交易描述", "数量", "交易时间", "结算时间", "证券代码", "本金", "利息",  "费用", "货币", "汇率");
		result.put("titles", titles);
		SecTradeDetailModel secTrade=tradeService.loadTranBasicByID(tradeNum);
		
		List<Object> data = new ArrayList<Object>();
		List<Object> item = new ArrayList<Object>();
		item.add(secTrade.getTanum());
		item.add(SecurityCoreFactory.getAllTranTypeHM().get(secTrade.getTranType()));
		item.add(secTrade.getDesc());
		item.add(secTrade.getAmount());
		item.add(secTrade.getTradeDate());
		item.add(secTrade.getSetDate());
		if(secTrade.getSecID().equals("-")){
			item.add(secTrade.getSecID());
		}else{
			item.add(secTrade.getSecID().split("\\|")[1]);
		}
		item.add(secTrade.getPrincipal());
		item.add(secTrade.getIntatmat());
		item.add(secTrade.getFeeTotal());
		item.add(secTrade.getFxCurCode());
		item.add(secTrade.getFxrate());
		data.add(item);
		result.put("data", data);
			
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/trade/fx.do", method = RequestMethod.POST, name="交易-换汇交易查询")  
    public void fxTradeList(@RequestParam(value = "config", required = true) String resources,
    					  @RequestParam(value = "type", required = true) String type,
    					  @RequestParam(value = "portfolio", required = true) String portfolio,
    					  @RequestParam(value = "start", required = true) String start,
    					  @RequestParam(value = "end", required = true) String end,
    					  @RequestParam(value = "displayDelete", required = true) Boolean displayDelete,
    					  @RequestParam(value = "num", required = false) int num,
    					  HttpServletResponse response) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		Pages<SecTradeSummaryModel> pages=new Pages<SecTradeSummaryModel>(num);
		
		List<String> titles = Arrays.asList("交易编号","投资组合","交易类型", "过账状态", "交易时间", "结算时间", "对手方", "多方金额", "多方货币", "空方金额", "空方货币", "数据来源");
		result.put("titles", titles);
		
		Date startDate=DateUtil.convertStr2DateAll(start);
		Date endDate=DateUtil.convertStr2DateAll(end);
		pages=tradeService.loadFXTranTranList(portfolio, type, startDate, endDate,pages,displayDelete);
		List<SecTradeSummaryModel> subredTranList=pages.getResultList();
		
		List<Object> data = new ArrayList<Object>();
		for(SecTradeSummaryModel tran:subredTranList){
			List<Object> item = new ArrayList<Object>();
			item.add(tran.getTanum());
			item.add(tran.getPortname());
			item.add(tran.getTrantype());
			item.add(tran.getPostType());
			item.add(tran.getTradeDate());
			item.add(tran.getSetDate());
			item.add("对手方");
			item.add(tran.getAmount());
			item.add(tran.getCurrency());
			item.add(tran.getAmount2());
			item.add(tran.getCurrency2());
			item.add("交易接口");
			data.add(item);
		}
		result.put("data", data);
		result.put("pages", pages.getTotalPages());	
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/trade/newFx.do", method = RequestMethod.POST, name="交易-新换汇交易查询")  
    public void newFxTradeList(@RequestParam(value = "config", required = true) String resources,
    					  @RequestParam(value = "type", required = true) String type,
    					  @RequestParam(value = "portfolio", required = true) String portfolio,
    					  @RequestParam(value = "start", required = true) String start,
    					  @RequestParam(value = "end", required = true) String end,
    					  @RequestParam(value = "displayDelete", required = true) Boolean displayDelete,
    					  @RequestParam(value = "page", required = true) int num,
	    				  @RequestParam(value = "limits", required = true) int limits,
	    				  @RequestParam(value = "export2excel", required = false) String export2excel,
    					  HttpServletResponse response) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		//判断是否需要导出  如果导出  limits需要很大
		limits=pageLimits(export2excel,limits);
		
		Pages<SecTradeSummaryModel> pages=new Pages<SecTradeSummaryModel>(num,limits);
		List<String> titles = Arrays.asList("交易编号","投资组合","交易编码","交易类型", "过账状态", "交易时间", "结算时间", "对手方", "多方金额", "多方货币", "空方金额", "空方货币", "数据来源");
		result.put("titles", titles);
		
		Date startDate=DateUtil.convertStr2DateAll(start);
		Date endDate=DateUtil.convertStr2DateAll(end);
		pages=tradeService.loadFXTranTranList(portfolio, type, startDate, endDate,pages,displayDelete);
		List<SecTradeSummaryModel> subredTranList=pages.getResultList();
		
		List<Object> data = new ArrayList<Object>();
		for(SecTradeSummaryModel tran:subredTranList){
//			if(tran.getPostType().equals("未过账")){
//				continue;
//			}
			List<Object> item = new ArrayList<Object>();
			item.add(tran.getTanum());
			item.add(tran.getPortname());
			item.add(tran.getTrantypeCode());
			item.add(tran.getTrantype());
			item.add(tran.getPostType());
			item.add(tran.getTradeDate());
			item.add(tran.getSetDate());
			item.add("对手方");
			item.add(DataConvert.convertCashNoCalFormat(tran.getAmount()));
			item.add(tran.getCurrency());
			item.add(DataConvert.convertCashNoCalFormat(Math.abs(tran.getAmount2())));
			item.add(tran.getCurrency2());
			item.add("交易接口");
			data.add(item);
		}
		result.put("data", data);
		result.put("pages", pages.getTotalPages());	
		result.put("totalCount", pages.getTotalCount());	
		//导出excel或返给前台显示
		returnControllerList(export2excel,result,response);
	}

	@RequestMapping(value="/trade/ca.do", method = RequestMethod.POST)
    public void corpActionList(@RequestParam(value = "config", required = true) String resources,
    					        @RequestParam(value = "type", required = true) int type,
    					        @RequestParam(value = "smf", required = true) String smf,
    					        @RequestParam(value = "start", required = true) String start,
    					        @RequestParam(value = "end", required = true) String end,
    					        HttpServletResponse response) {
	
		//List<Object> r_list = JsonUtil.stringToList(resources);
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("交易编号","证券代码", "证券名称", "公司行为代码","公司行为名称","股权登记日", "除权除息日", "结算日", "利率");
		result.put("titles", titles);

		Date startDate=null;
		if(start.equals("")){
			startDate=DateUtil.convertStr2Date("20000101");
		}else{
			startDate=DateUtil.convertStr2Date2(start);
		}
		
		Date endDate=null;
		if(end.equals("")){
			endDate=DateUtil.convertStr2Date("29991231");
		}else{
			endDate=DateUtil.convertStr2Date2(end);
		}
		
		String[] smfArr=smf.split("\\|");
		String smfsecid="";
		if(smfArr.length==3){
			smfsecid=smfArr[1];
		}else{
			smfsecid=smf;
		}
		IssueInfo sec=issueInfoDao.loadSecurity(smfsecid);
		String cusip="";
		if(sec!=null){
			cusip=sec.getId();
		}
		
		List<SmfDiv> divList=smfDivDao.loadSmfDivList(cusip, startDate, endDate, type);
		
		List<Object> data = new ArrayList<Object>();
		
		for(SmfDiv div:divList){
			HashMap<String, Object> detailHM=new HashMap<String, Object>();
			IssueInfo inerSec=issueInfoDao.loadIssueByCusip(div.getCusip());
			detailHM.put("trade_id", div.getId());
			detailHM.put("secid", inerSec.getSecurityid());
			detailHM.put("secname", inerSec.getSecurityname());
			detailHM.put("tradetypeCodeCol", div.getCaType());
			detailHM.put("corpActionType", secCoreFactory.getcorpActionNameMap().get(div.getCaType()));
			detailHM.put("recordDate", DateUtil.convertD2YYYY_MM_DD(div.getRecordDate()));
			detailHM.put("exDividentDate", DateUtil.convertD2YYYY_MM_DD(div.getExDate()));
			if(div.getPayDate()==null)
				detailHM.put("payDate", "-");
			else
				detailHM.put("payDate", DateUtil.convertD2YYYY_MM_DD(div.getPayDate()));
			
			detailHM.put("caRate", div.getRate());
			
			data.add(detailHM);
		}
		result.put("data", data);
			
		JsonUtil.writeObjToResponse(response, result);
	}

	//几许专用的公司行为列表
	@RequestMapping(value="/trade/ca_jx.do", method = RequestMethod.POST, name="公司行为-查询")
	public void corpActionList_jx(@RequestParam(value = "config", required = true) String resources,
							   	  @RequestParam(value = "type", required = true) int type,
							   	  @RequestParam(value = "smf", required = true) String smf,
							   	  @RequestParam(value = "start", required = true) String start,
								  @RequestParam(value = "end", required = true) String end,
								  HttpServletResponse response) {

		//List<Object> r_list = JsonUtil.stringToList(resources);
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("交易编号","证券代码", "证券名称", "公司行为代码","公司行为名称","股权登记日", "除权除息日", "结算日", "利率");
		result.put("titles", titles);

		Date startDate=null;
		if(start.equals("")){
			startDate=DateUtil.convertStr2Date("20000101");
		}else{
			startDate=DateUtil.convertStr2Date2(start);
		}

		Date endDate=null;
		if(end.equals("")){
			endDate=DateUtil.convertStr2Date("29991231");
		}else{
			endDate=DateUtil.convertStr2Date2(end);
		}

		String[] smfArr=smf.split("\\|");
		String smfsecid="";
		if(smfArr.length==3){
			smfsecid=smfArr[1];
		}else{
			smfsecid=smf;
		}
		IssueInfo sec=issueInfoDao.loadSecurity(smfsecid);
		String cusip="";
		if(sec!=null){
			cusip=sec.getId();
		}

		List<SmfDiv> divList=smfDivDao.loadSmfDivList(cusip, startDate, endDate, type);

		List<Object> data = new ArrayList<Object>();

		for(SmfDiv div:divList){
			List<Object> item = new ArrayList<Object>();
			IssueInfo inerSec=issueInfoDao.loadIssueByCusip(div.getCusip());
			item.add(div.getId());
			item.add(inerSec.getSecurityid());
			item.add(inerSec.getSecurityname());
			item.add(div.getCaType());
			item.add(secCoreFactory.getcorpActionNameMap().get(div.getCaType()));
			item.add(DateUtil.convertD2YYYY_MM_DD(div.getRecordDate()));
			item.add(DateUtil.convertD2YYYY_MM_DD(div.getExDate()));
			if(div.getPayDate()==null)
				item.add('-');
			else
				item.add(DateUtil.convertD2YYYY_MM_DD(div.getPayDate()));
			item.add(div.getRate());
			data.add(item);
		}
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/trade/opr.do", method = RequestMethod.POST)  
    public void operationList(@RequestParam(value = "config", required = true) String resources,
    					  @RequestParam(value = "type", required = true) String type,
    					  @RequestParam(value = "portfolio", required = true) String portfolio,
    					  @RequestParam(value = "start", required = true) String start,
    					  @RequestParam(value = "end", required = true) String end,
    					  HttpServletResponse response) {
		List<Object> r_list = JsonUtil.stringToList(resources);
		System.out.println(r_list + "|" + type + "|" + portfolio + "|" + start + "|" + end);
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("交易编号", "操作类型", "操作时间", "结算时间", "借方科目", "借方金额", "贷方科目", "贷方金额", "银行账户","最后更新时间","操作人");
		result.put("titles", titles);
		
		List<Object> data = new ArrayList<Object>();
		List<Object> item = new ArrayList<Object>();
		item.add("0003");
		item.add("手工分录");
		item.add("10/07/2014");
		item.add("10/10/2014");
		item.add("证券清算款");
		item.add(5000);
		item.add("股票成本");
		item.add(5000);
		item.add("CHA00034");
		item.add("17:45:34 10/09/2013");
		item.add("Jixu");
		data.add(item);
		result.put("data", data);
			
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/trade/tradeDetail.do", method = RequestMethod.POST, name="交易-详情")  
    public void tradeDetail(@RequestParam(value = "id", required = true) String tradeID,
    						@RequestParam(value = "type", required = true) int tradeType,
    					    HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		SecTradeDetailModel detail=tradeService.loadTranBasicByID(tradeID);
		List<String> titles=null;
		
		if(detail.getTranType()==TransTypes.REVSTOCKSPLIT) {
			StockResverseSplitDto dto=new StockResverseSplitDto();
			dto.setId(detail.getTanum());
			dto.setAssetType(detail.getSecType());
			dto.setPort(detail.getPortnum());
			dto.setTradeType(String.valueOf(detail.getTranType()));
			dto.setDivDate(DateUtil.convertD2YYYY_MM_DD(detail.getDivBaseDate()));
			dto.setTradeDate(detail.getTradeDate());
			dto.setAlRate(String.valueOf(detail.getAlRate()));
			dto.setSecAcct(detail.getSecacct());
			result.put("data", dto);
		}else if(detail.getTranType()==TransTypes.STOCKSPLIT){
			StockSplitDto dto=new StockSplitDto();
			dto.setId(detail.getTanum());
			dto.setAssetType(detail.getSecType());
			dto.setPort(detail.getPortnum());
			dto.setTradeType(String.valueOf(detail.getTranType()));
			dto.setDivDate(DateUtil.convertD2YYYY_MM_DD(detail.getDivBaseDate()));
			dto.setTradeDate(detail.getTradeDate());
			dto.setAlRate(String.valueOf(detail.getAlRate()));
			dto.setSecAcct(detail.getSecacct());
			result.put("data", dto);
		}else if(detail.getTranType()==TransTypes.NewStock_Receive){
			StockNewReceiveDto dto=new StockNewReceiveDto();
			dto.setId(detail.getTanum());
			dto.setAssetType(detail.getSecType());
			dto.setBankAcct(String.valueOf(detail.getCashacct()));
			dto.setBroker(String.valueOf(detail.getBrokerCode()));
			dto.setManager(String.valueOf(detail.getManager()));
			dto.setMarket(String.valueOf(detail.getTradeMarket()));
			dto.setPort(detail.getPortnum());
			dto.setPrice(String.valueOf(detail.getPrice()));
			dto.setPrincipal(String.valueOf(detail.getPrincipal()));
			dto.setSecAcct(String.valueOf(detail.getSecacct()));
			dto.setSecid(detail.getSecID());
			dto.setSettleCash(String.valueOf(detail.getSetCash()));
			dto.setSettleCurrency(detail.getSetCurID());
			dto.setSettleDate(detail.getSetDate());
			dto.setSettleFX(String.valueOf(detail.getSetFxRate()));
			dto.setShares(String.valueOf(detail.getUnits()));
			dto.setTradeCash(String.valueOf(detail.getCash()));
			dto.setTradeCurrency(detail.getFxcurrency());
			dto.setTradeDate(detail.getTradeDate());
			dto.setTradeFX(String.valueOf(detail.getFxrate()));
			dto.setTradeType(String.valueOf(detail.getTranType()));
			result.put("data", dto);
		}else if(detail.getTranType()==TransTypes.StockDividend){
			StockDividendDto dto=new StockDividendDto();
			dto.setId(detail.getTanum());
			dto.setAlRate(String.valueOf(detail.getAlRate()));
			dto.setAssetType(detail.getSecType());
			dto.setPort(detail.getPortnum());
			dto.setDivDate(DateUtil.convertD2YYYY_MM_DD(detail.getDivBaseDate()));
			dto.setSecid(detail.getSecID());
			dto.setTradeDate(detail.getTradeDate());
			dto.setTradeType(String.valueOf(detail.getTranType()));
			dto.setSecAcct(detail.getSecacct());
			result.put("data", dto);
		}else if(detail.getTranType()==TransTypes.CashDividend){
			CashDividendDto dto=new CashDividendDto();
			dto.setId(detail.getTanum());
			dto.setAlRate(String.valueOf(detail.getAlRate()));
			dto.setAssetType(detail.getSecType());
			dto.setPort(detail.getPortnum());
			dto.setDivDate(DateUtil.convertD2YYYY_MM_DD(detail.getDivBaseDate()));
			dto.setSecid(detail.getSecID());
			dto.setSettleDate(detail.getSetDate());
			dto.setTradeDate(detail.getTradeDate());
			dto.setTradeType(String.valueOf(detail.getTranType()));
			dto.setSecAcct(detail.getSecacct());
			result.put("data", dto);
		}else if(detail.getTranType()==TransTypes.SWAP_Maturity_Interest || detail.getTranType()==TransTypes.SWAP_Pay_Interest){
			InterestSwapMaturityDto dto=new InterestSwapMaturityDto();
			dto.setId(detail.getTanum());
			dto.setAssetType(detail.getSecType());
			dto.setSecid(detail.getSecID());
			dto.setTradeType(String.valueOf(detail.getTranType()));
			dto.setPort(detail.getPortnum());
			dto.setTradeDate(detail.getTradeDate());
			dto.setSettleDate(detail.getSetDate());
			dto.setPrincipal(String.valueOf(detail.getPrincipal()));
			dto.setIntatmat(String.valueOf(detail.getIntatmat()));
			dto.setBankAcct(String.valueOf(detail.getCashacct()));
			dto.setIncurrency(detail.getFxCurCode());
			dto.setTradeFX(String.valueOf(detail.getFxrate()));
			dto.setPrincipal2(String.valueOf(detail.getPrincipal2()));
			dto.setIntatmat2(String.valueOf(detail.getIntatmat2()));
			dto.setBankAcct2(String.valueOf(detail.getCashacct2()));
			dto.setIncurrency2(detail.getFxCurCode2());
			dto.setDivBasicDate(DateUtil.convertD2YYYY_MM_DD(detail.getDivBaseDate()));
			dto.setTradeFX2(String.valueOf(detail.getFxrate2()));
			if(detail.isSetIntatmat()) {
				dto.setSetAllInterest("1");	
			}else {
				dto.setSetAllInterest("0");	
			}
			
			if(detail.isSetIntatmat2()) {
				dto.setSetAllInterest2("1");	
			}else {
				dto.setSetAllInterest2("0");	
			}
			result.put("data", dto);
		}else if(detail.getTranType()==TransTypes.SWAP_FX_OPEN){
			SwapFXOpenDto dto=new SwapFXOpenDto();
			dto.setId(detail.getTanum());
			dto.setAssetType(detail.getSecType());
			dto.setSecid(detail.getSecID());
			dto.setTradeType(String.valueOf(detail.getTranType()));
			dto.setPort(detail.getPortnum());
			dto.setGlgroup(String.valueOf(detail.getGlcol()));
			dto.setManager(String.valueOf(detail.getManager()));
			dto.setTradeDate(detail.getTradeDate());
			dto.setSettleDate(detail.getSetDate());
			// dto.setPrincipal(String.valueOf(detail.getPrincipal()));
			BigDecimal principal = new BigDecimal(detail.getPrincipal());
		    dto.setPrincipal(ArithmeticUtil.round(principal, NumberEnum.MONEY).toPlainString());
			// dto.setTradeCash(String.valueOf(detail.getCash()));
		    BigDecimal cash = new BigDecimal(detail.getCash());
		    dto.setTradeCash(ArithmeticUtil.round(cash, NumberEnum.MONEY).toPlainString());
			dto.setBankAcct(String.valueOf(detail.getCashacct()));
			dto.setTradeFX(String.valueOf(detail.getFxrate()));
			// dto.setPrincipal2(String.valueOf(detail.getPrincipal2()));
			BigDecimal principal2 = new BigDecimal(detail.getPrincipal2());
		    dto.setPrincipal2(ArithmeticUtil.round(principal2, NumberEnum.MONEY).toPlainString());
			// dto.setTradeCash2(String.valueOf(detail.getCash2()));
		    BigDecimal cash2 = new BigDecimal(detail.getCash2());
		    dto.setTradeCash2(ArithmeticUtil.round(cash2, NumberEnum.MONEY).toPlainString());
			dto.setBankAcct2(String.valueOf(detail.getCashacct2()));
			dto.setMaturityDate(detail.getAlDate());
			dto.setTradeFX2(String.valueOf(detail.getFxrate2()));
			dto.setSwapExchangeInd(detail.getSwapExchangeInd());
			result.put("data", dto);
		}else {
			HashMap<String, Object> detailHM=new HashMap<String, Object>();
			detailHM.put(MsgFields.tanum, detail.getTanum());
			detailHM.put(MsgFields.assetType, detail.getSecType());
			detailHM.put(MsgFields.secid, detail.getSecID());
			detailHM.put(MsgFields.tradeType, detail.getTranType());
			detailHM.put(MsgFields.portnum, detail.getPortnum());
			detailHM.put(MsgFields.portnum2, detail.getPortnum2());
			detailHM.put(MsgFields.tradeMarket, detail.getTradeMarket());
			detailHM.put(MsgFields.manager, detail.getManager());
			detailHM.put(MsgFields.tradeDate, detail.getTradeDate());
			detailHM.put(MsgFields.settleDate, detail.getSetDate());
			detailHM.put(MsgFields.broker, detail.getBrokerCode());		
			detailHM.put(MsgFields.secAcct, detail.getSecacct());
			detailHM.put(MsgFields.bankAcct, detail.getCashacct());
			detailHM.put(MsgFields.shares, detail.getUnits());
			detailHM.put(MsgFields.price, detail.getPrice());
			detailHM.put(MsgFields.principal, Math.abs(detail.getPrincipal()));
			detailHM.put(MsgFields.intatmat, Math.abs(detail.getIntatmat2()));
			//定存卖出交易，应该是accruedIncome，返回了maturityInterest
			detailHM.put(MsgFields.accrued, Math.abs(detail.getIntatmat()));
			detailHM.put(MsgFields.accrued2, Math.abs(detail.getIntatmat2()));
			detailHM.put(MsgFields.totalExpense, detail.getFeeTotal());
			detailHM.put(MsgFields.totalExpense2, detail.getFeeTotal2());
			detailHM.put(MsgFields.tradeLedgerCur, detail.getLedgerCurrency());
			detailHM.put(MsgFields.fxCur, detail.getFxcurrency());
			detailHM.put(MsgFields.tradeCash, Math.abs(detail.getCash()));
			detailHM.put(MsgFields.tradeFX, detail.getFxrate());
			detailHM.put(MsgFields.tradeLedgerCur2, detail.getLedgerCurCode()); 
			detailHM.put(MsgFields.settleCash, Math.abs(detail.getSetCash()));
			detailHM.put(MsgFields.settleFxRate, detail.getSetFxRate());
			detailHM.put(MsgFields.settleFxCur2, detail.getSetcurCode2());
			detailHM.put(MsgFields.settleCash2, Math.abs(detail.getSetCash2()));
			detailHM.put(MsgFields.settleFxRate2, detail.getSetFxRate2());
			detailHM.put(MsgFields.tranDesc, detail.getDesc());
			detailHM.put(MsgFields.glacct, detail.getGlacct());
			detailHM.put(MsgFields.offsetType, detail.getDbcr());
			detailHM.put(MsgFields.settleFxCur, detail.getSetCurID());
			
			detailHM.put(MsgFields.tradeCash2, Math.abs(detail.getCash2()));
			detailHM.put(MsgFields.secAcct2, detail.getSecacct2());
			detailHM.put(MsgFields.bankAcct2, detail.getCashacct2());
			detailHM.put(MsgFields.fxCur2, detail.getFxcurrency2());
			detailHM.put(MsgFields.contract, detail.getContract());
			detailHM.put(MsgFields.lotMethod, detail.getHiloCode());
			detailHM.put(MsgFields.valuationMethod, detail.getIntent());
			detailHM.put(MsgFields.maturityDate, detail.getAlDate());
			detailHM.put(MsgFields.shares2, detail.getAmount2());
			detailHM.put(MsgFields.principal2, detail.getPrincipal2());
			detailHM.put(MsgFields.price2, detail.getPrice2());
			detailHM.put(MsgFields.repoRate, detail.getAlRate());
			detailHM.put(MsgFields.alRate, detail.getAlRate());
			detailHM.put(MsgFields.desc,detail.getDesc());
			detailHM.put(MsgFields.secid2, detail.getSecID2());
			detailHM.put(MsgFields.effectiveRate, detail.getAlRate());
			detailHM.put(MsgFields.glgroup, detail.getGlcol());
			detailHM.put(MsgFields.swapExchangeInd, detail.getSwapExchangeInd());
			if(detail.getHiloCode()>0 && detail.getSpecificLotID()>0){
				detailHM.put(MsgFields.contract, detail.getSpecificLotID());
			}
			
			if(detail.getTranType()==TransTypes.CASHOFF){
				if(detail.getAmount()>0){
					detailHM.put(MsgFields.secAcct, detail.getSecacct());
					detailHM.put(MsgFields.bankAcct, detail.getCashacct());
					detailHM.put(MsgFields.fxCur, detail.getFxcurrency());
					detailHM.put(MsgFields.tradeCash, detail.getAmount());
				}else if(detail.getAmount()==0 && detail.getAmount2()>0){
					detailHM.put(MsgFields.secAcct, detail.getSecacct2());
					detailHM.put(MsgFields.bankAcct, detail.getCashacct2());
					detailHM.put(MsgFields.fxCur, detail.getFxcurrency2());
					detailHM.put(MsgFields.tradeCash, detail.getAmount2());
				}
			}else if(detail.getTranType()==TransTypes.REPO_LEND || detail.getTranType()==TransTypes.REPO_BORROW || 
					detail.getTranType()==TransTypes.REPO_BORROW_MATURITY || detail.getTranType()==TransTypes.TimeDep_Buy){
				detailHM.put(MsgFields.maturityTotal, Math.abs(detail.getAmount2()));
			}else if(detail.getTranType()==TransTypes.CashDividend || detail.getTranType()==TransTypes.StockDividend
					|| detail.getTranType()==TransTypes.CommonFund_Divident || detail.getTranType()==TransTypes.CommonFund_Divident2Share
					||detail.getTranType()==TransTypes.Share_Divident ||  detail.getTranType()==TransTypes.REVSTOCKSPLIT || detail.getTranType()==TransTypes.STOCKSPLIT){
				detailHM.put(MsgFields.recordDate, DateUtil.convertD2YYYY_MM_DD(detail.getDivBaseDate()));
				detailHM.put(MsgFields.exDate, detail.getTradeDate());
			}else if(detail.getTranType()==TransTypes.Borrow_Buy || detail.getTranType()==TransTypes.Sec_Lend){
				detailHM.put(MsgFields.maturityTotal, detail.getAmount2());
				if(detail.getTranType()==TransTypes.Sec_Lend){
					detailHM.put(MsgFields.principal, detail.getPrincipal2());
					detailHM.put(MsgFields.price, detail.getPrice2());
				}
			}else if(detail.getTranType()==TransTypes.HK_HGT_Buy || detail.getTranType()==TransTypes.HK_HGT_Sale){
				detailHM.put(MsgFields.settleDate2, detail.getSettleDate2());
			}else if(detail.getTranType()==TransTypes.ShareBankZGMaturity){
				double principal2=detail.getPrincipal2();
				if(principal2>0){
					detailHM.put("performanceFee", principal2);
				}else{
					detailHM.put("clawBack", principal2*-1);
				}
			}else if(detail.getTranType()==TransTypes.FORWORD_CURRENCY_OPEN){
				   String[] secids=detail.getSecID().split("\\|");
				   if(secids.length==3){
					   detailHM.put(MsgFields.secid,secids[1]);
					   detailHM.put(MsgFields.contract,secids[2]);
				   }
				   detailHM.put(MsgFields.valuationSide,ValuationType.VALSIDE_LONG);
				   if(detail.getCash2()<0){
					   detailHM.put(MsgFields.valuationSide,ValuationType.VALSIDE_SHORT);
					   detailHM.put(MsgFields.tradeCash2,Math.abs(detail.getCash2()));
				   }
			}else if(detail.getTranType()==TransTypes.FORWORD_CURRENCY_Cutting||detail.getTranType()==TransTypes.FORWORD_CURRENCY_Maturity){
				   detailHM.put(MsgFields.settleFxRate,detail.getFxrate2());
				   detailHM.put(MsgFields.tradeFX,detail.getFxrate());
				   detailHM.put("diffCash",Math.abs(detail.getCash()));
				   if(detail.getCash()>0){
					   detailHM.put("diffSide",DBCR.receive);
				   }else{
					   detailHM.put("diffSide",DBCR.pay);
				   }
			}else if(detail.getTranType()==TransTypes.SWAP_OPEN_Interest || detail.getTranType()==TransTypes.SWAP_FX_OPEN){
			      String[] secids=detail.getSecID().split("\\|");
			      String cusip=secids[0];
			      IssueInfo ii=issueInfoDao.loadIssueByCusip(cusip);
			      if(ii!=null){
					   CashFlowCfg cfg = configService.loadCashFlowCfg(ii.getCashFlowCfgID());
					   if(cfg!=null) {
						   detailHM.put("cashflowcfg", cfg.getCashFlowID());
						   detailHM.put("couponRate", cfg.getCoupon());
						   detailHM.put("base", cfg.getVrBase());
						   detailHM.put("factor", cfg.getVrBasisPtAdjustment());
						   detailHM.put("couponType", cfg.getCpnType());
						   detailHM.put("autoFlag", cfg.isAutoSetFlag());
						   detailHM.put("autoInterestFlag", cfg.isAutoInterestFlag());
					   }
					   
					   
					   CashFlowCfg cfg2 = configService.loadCashFlowCfg(ii.getCashFlowCfgID2());
					   if(cfg2!=null) {
						   detailHM.put("cashflowcfg2", cfg2.getCashFlowID());
						   detailHM.put("couponRate2", cfg2.getCoupon());
						   detailHM.put("base2", cfg2.getVrBase());
						   detailHM.put("factor2", cfg2.getVrBasisPtAdjustment());
						   detailHM.put("couponType2", cfg2.getCpnType());
						   detailHM.put("autoFlag2", cfg2.isAutoSetFlag());
						   detailHM.put("autoInterestFlag2", cfg2.isAutoInterestFlag());
					   }
			      }
			}
			result.put("data", detailHM);
		}
		
		result.put("titles", titles);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/trade/tradeUpdate.do", method = RequestMethod.POST, name="交易-更新")
	public void tradeUpdate(@RequestParam(value = "json", required = true) String json,
						    @RequestParam(value = "operType", required = false) String operType,
						    HttpServletRequest request,
			 			    HttpServletResponse response){
		exeOpertion(json, operType, response);
	}
	
	@RequestMapping(value="/trade/tradeRemove.do", method = RequestMethod.POST, name="交易-删除")
	public void tradeRemove(@RequestParam(value = "id", required = true) String tradeID,
							@RequestParam(value = "operType", required = false) String operType,
							HttpServletRequest request,
							HttpServletResponse response){
		boolean wflFlag=false;
		if(operType!=null)
			wflFlag=true;
		ResultContainer resultcontainer=tradeService.deleteTran(tradeID,wflFlag);
		controllerReturnNew(response,resultcontainer);
	}
	
	@RequestMapping(value="/trade/tradeCreate.do", method = RequestMethod.POST, name="交易-创建")
	public void tradeCreate(@RequestParam(value = "json", required = true) String json,
							@RequestParam(value = "operType", required = false) String operType,
			 			    HttpServletResponse response){
		exeOpertion(json, operType, response);
	}
	
	@RequestMapping(value="/trade/caDetail.do", method = RequestMethod.POST)
    public void corpActionDetail(@RequestParam(value = "id", required = true) int caID,
    					      HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		//List<String> titles = Arrays.asList("公司行为编号", "公司行为类型",  "证券名称/ID", "除权除息日", "公布日期", "付款日期", "分红比率");
		SmfDiv div=smfDivDao.loadEntity(SmfDiv.class, caID);
		IssueInfo sec=issueInfoDao.loadIssueByCusip(div.getCusip());
		
		HashMap<String, Object> detailHM=new HashMap<String, Object>();
		detailHM.put("id",caID);
		detailHM.put("corpActionType", div.getCaType());
		detailHM.put("secid","0|"+sec.getSecurityid()+"|"+sec.getSecurityname());
		detailHM.put("recordDate", DateUtil.convertD2YYYY_MM_DD(div.getRecordDate()));
		detailHM.put("exDividentDate", DateUtil.convertD2YYYY_MM_DD(div.getExDate()));
		detailHM.put("caRate", div.getRate());
		if(div.getCaType()==CorpActionTypes.CASHPAYMENTSONLY) {
			detailHM.put("payDate", DateUtil.convertD2YYYY_MM_DD(div.getPayDate()));
		}
		result.put("data", detailHM);	
		JsonUtil.writeObjToResponse(response, result);
	}

	//几许系统使用的公司行为详情
	@RequestMapping(value="/trade/caDetail_jx.do", method = RequestMethod.POST, name="公司行为-详情")
	public void corpActionDetail_jx(@RequestParam(value = "id", required = true) int caID,
								 HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		SmfDiv div=smfDivDao.loadEntity(SmfDiv.class, caID);
		IssueInfo sec=issueInfoDao.loadIssueByCusip(div.getCusip());

		HashMap<String, Object> detailHM=new HashMap<String, Object>();
		detailHM.put("id",caID);
		detailHM.put("corpActionType", div.getCaType());
		detailHM.put("secid","0|"+sec.getSecurityid()+"|"+sec.getSecurityname());
		detailHM.put("assetType", sec.getIssueType());
		detailHM.put("recordDate", DateUtil.convertD2YYYY_MM_DD(div.getRecordDate()));
		detailHM.put("exDividentDate", DateUtil.convertD2YYYY_MM_DD(div.getExDate()));
		detailHM.put("alRate", div.getRate());
		if(div.getCaType()==CorpActionTypes.CASHPAYMENTSONLY) {
			detailHM.put("payDate", DateUtil.convertD2YYYY_MM_DD(div.getPayDate()));
		}
		result.put("data", detailHM);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/trade/caUpdate.do", method = RequestMethod.POST, name="公司行为-更新")
	 public void corpActionUpdate(@RequestParam(value = "id", required = true) int tradeID,
			 						@RequestParam(value = "params", required = true) String params,
			 						@RequestParam(value = "json", required = true) String json,
			 						HttpServletRequest request,
			 						HttpServletResponse response){
		HashMap<String, Object> fieldsMap=JSONObject.parseObject(json, HashMap.class);
			
		MsgContainer container=new MsgContainer();
		container.setFieldsHM(fieldsMap);
		container.setUserID(JixuUserUtil.getUserIDbyRequest(request));
		container=MsgFactory.executeMsg(container);
		if(container.isCorrectFlag()){
			JsonUtil.writeObjToResponse(response, container.getObjID());
		}else{
			container.setCorrectFlag(container.isCorrectFlag());
			container.setErrorMsg(container.getErrorMsg());
			JsonUtil.writeObjToResponse(response, genErrorResult(container));
		}
		
		JsonUtil.writeObjToResponse(response, "");
	}
	
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	@RequestMapping(value="/trade/caRemove.do", method = RequestMethod.POST, name="公司行为-删除")
	public void corpActionRemove(@RequestParam(value = "id", required = true) int caID,
			 			   	HttpServletResponse response){
		SmfDiv div=smfDivDao.loadEntity(SmfDiv.class, caID);
		smfDivDao.deleteEntity(div);
		JsonUtil.writeObjToResponse(response, caID);
	}

	@RequestMapping(value="/trade/caCreate.do", method = RequestMethod.POST, name="公司行为-创建")
	 public void corpActionCreate(@RequestParam(value = "json", required = true) String json,
			 				@RequestParam(value = "operType", required = false) String operType,
							HttpServletResponse response){
		exeOpertion(json, operType, response);
	}
	
	@RequestMapping(value="/trade/portAssets.do", method = RequestMethod.POST)
	public void portAssets(@RequestParam(value = "all", required = true) boolean all,
						   HttpServletResponse response){
		HashMap<String, String> result = AssetTypes.getPortAsset(all);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/trade/fxAssets.do", method = RequestMethod.POST)
	public void fxAssets(@RequestParam(value = "all", required = true) boolean all,
						   HttpServletResponse response){
		HashMap<String, String> result = AssetTypes.getFxAsset(all);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/trade/allAssets.do", method = RequestMethod.POST)
	public void allAssets(@RequestParam(value = "all", required = true) boolean all,
						   HttpServletResponse response){
		HashMap<String, String> result = AssetTypes.getAllAssets(all);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/trade/tradeTypes.do", method = RequestMethod.POST)
	public void tradeTypes(@RequestParam(value = "type", required = true) String assetType,
					     @RequestParam(value = "all", required = true) boolean all,
						 HttpServletResponse response){
		HashMap<Integer, String> result=SecurityCoreFactory.getTranTypeHM(assetType, all);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/trade/allTradeTypes.do", method = RequestMethod.POST)
	public void allTradeTypes(HttpServletResponse response){
		HashMap<Integer, String> result = SecurityCoreFactory.getAllTranTypeHM();
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/trade/caTypes.do", method = RequestMethod.POST)
	public void caTypes(HttpServletResponse response){
		HashMap<Integer, String> result =secCoreFactory.getcorpActionNameMap();
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/trade/CashOffsetOption.do", method = RequestMethod.POST)
	public void getCashOffsetOption(HttpServletResponse response){
		HashMap<Integer, String> result = IncomeExpenseType.optionHM;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/trade/tradeExpenses.do", method = RequestMethod.POST)
	public void tradeExpenses(@RequestParam(value = "assetType", required = true) String assetType, 
							  HttpServletResponse response){
		HashMap<Integer, String> result = tradeService.loadTranFeeDefByTranTypeID(assetType);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	//返回交易费用名称  新框架前端费用显示需要
	 @RequestMapping(value="/trade/tradeExpenseType.do", method = RequestMethod.POST)
	 public void tradeExpenseType(
	        @RequestParam(value = "expense", required = true) String expenseCodeStr,
	         HttpServletResponse response){
		if(expenseCodeStr!=""){
			int expenseCode=Integer.valueOf(expenseCodeStr);
			TranFeeDef feeDef = tranFeeDefDao.loadTranFeeDef(expenseCode);
			if (feeDef != null) {
				JsonUtil.writeObjToResponse(response, feeDef.getFeename());
			}
		}else{
			JsonUtil.writeObjToResponse(response, "");
		}
		
	 }
	
	@RequestMapping(value = "/trade/tradeExpenseDetail.do", method = RequestMethod.POST)
	public void tradeExpenseDetail(@RequestParam(value = "id", required = true) String tradeID, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		if(tradeID!=null){
			TranBasic tran=tranBasicDao.loadTranBasicByID(tradeID);
			PortfolioHdr portfolio=commService.loadPortfolio(tran.getPortfolio());
			String priCur="";
			if(tran.getCusip()!=null){
				IssueInfo sec=issueInfoDao.loadIssueByCusip(tran.getCusip().getId());
				priCur=sec.getPriCur();
			}else{
				priCur=portfolio.getCurID();
			}
			List<Object> data = new ArrayList<Object>();
			List<TranBasicFee> feeList=tradeService.loadTranBasicFeeList(tradeID);
			for(TranBasicFee fee:feeList){
				List<Object> item = new ArrayList<Object>();
				TranFeeDef feeDef=tranFeeDefDao.loadTranFeeDef(fee.getFeeTypeID().getId());
				BkCashAcct bkca=fee.getBkCashAcct();

				if(fee.getCurrID()!=null && !fee.getCurrID().equals(""))
					priCur=fee.getCurrID();

				//费用编号
				item.add(fee.getId());
				//费用选择
				item.add(feeDef.getId());
				//交易货币
				item.add(priCur);
				//费用货币
				item.add(bkca.getFxcurrency());
				//汇率
				item.add(fee.getFxrate());
				//"费用|费率"
				item.add(fee.getTotalFeeamt());
				//"费用化金额"
				item.add(fee.getExpenseFeeamt());
				//"应收应付金额"
				item.add(fee.getAmtexclfromcash());
				//费用银行账户
				item.add(bkca.getId());
				//结转时间
				item.add(DateUtil.convertD2YYYY_MM_DD(fee.getSettleDate()));

				data.add(item);
			}
			result.put("data", data);
		}
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value = "/trade/newTradeExpenseDetail.do", method = RequestMethod.POST)
	public void newTradeExpenseDetail(@RequestParam(value = "id", required = false) String tradeID, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		if(tradeID!=null){
			TranBasic tran=tranBasicDao.loadTranBasicByID(tradeID);
	        PortfolioHdr portfolio=commService.loadPortfolio(tran.getPortfolio());
	        String priCur="";
	        if(tran.getCusip()!=null){
	        	IssueInfo sec=issueInfoDao.loadIssueByCusip(tran.getCusip().getId());
	        	priCur=sec.getPriCur();
	        }else{
	        	priCur=portfolio.getCurID();
	        }
			List<Object> data = new ArrayList<Object>();
			List<TranBasicFee> feeList=tradeService.loadTranBasicFeeList(tradeID);
		    for(TranBasicFee fee:feeList){
		       HashMap<String, Object> map=new HashMap<String, Object>();
			   TranFeeDef feeDef=tranFeeDefDao.loadTranFeeDef(fee.getFeeTypeID().getId());
			   BkCashAcct bkca=fee.getBkCashAcct();
			   
			   if(fee.getCurrID()!=null && !fee.getCurrID().equals(""))
		        	priCur=fee.getCurrID();
			   
			   map.put("Expensescode", fee.getId());
			   map.put("expense", feeDef.getId());
			   map.put("bankAccount", bkca.getId());
			   map.put("tableFeeCurrency", bkca.getFxcurrency());
			   map.put("fxRate", fee.getFxrate());
			   if(fee.getAmtexclfromcash()==0)
				   map.put("cashAmount", 0);
			   else
				   map.put("cashAmount", 1);
			   map.put("totalAmount", fee.getTotalFeeamt());
			   map.put("expenseAmount", fee.getExpenseFeeamt());
			   map.put("expenseSettleDate", DateUtil.convertD2YYYY_MM_DD(fee.getSettleDate()));
			   map.put("feeTradeCurrency", priCur);
			   data.add(map);
		     }
		      result.put("data", data);
		}
        JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value = "/trade/tradeCollateralDetail.do", method = RequestMethod.POST)
	public void tradeCollateralDetail(@RequestParam(value = "id", required = true) String tradeID, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
        List<String> titles = Arrays.asList("编号","证券账户", "抵押证券","抵押数量");
        result.put("titles", titles);
        List<Object> data = new ArrayList<Object>();
        
        List<TranCollateral> collateralList=tradeService.loadTranCollateralList(tradeID);
      
        for(TranCollateral collateral:collateralList){
        List<Object> item1 = new ArrayList<Object>();
	        item1.add(collateral.getId());
	        item1.add(collateral.getTanum().getSecacct());
	        IssueInfo sec=collateral.getCusip();
	        item1.add(sec.getSecurityid()+"|"+sec.getSecurityname());
	        item1.add(collateral.getAmount());
	        data.add(item1);
        }
        
        result.put("data", data);
        JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/trade/assetForm.do", method = RequestMethod.POST)
	public void assetForm(@RequestParam(value = "assetType", required = true) String assetType,
						  HttpServletResponse response){
		SecurityAssetService service=secCoreFactory.createSecAssetTypeBean(assetType);
		String assetFormURL="";
		if(service!=null)
			assetFormURL=service.assetFormURL();
		JsonUtil.writeObjToResponse(response, assetFormURL);
	}
	
	//根据给定的assetType获得 newAssetForm
	@RequestMapping(value="/trade/newAssetForm.do", method = RequestMethod.POST)
	public void newAssetForm(@RequestParam(value = "assetType", required = true) String assetType,
							@RequestParam(value = "operation", required = true) String operation,
							 HttpServletResponse response){
		String newAssetFormURL=secCoreFactory.createSecAssetTypeBean(assetType).newAssetFormURL(operation);
		JsonUtil.writeObjToResponse(response, newAssetFormURL);
	}
	
	//TODO: tradeType获得tradeForm
	@RequestMapping(value="/trade/tradeForm.do", method = RequestMethod.POST)
	public void tradeForm(@RequestParam(value = "tradeType", required = true) int tradeType,
						  HttpServletResponse response){
		SecurityBasic secBasic=secCoreFactory.createSecTranTypeBean(tradeType);
		String tradeFormURL = secBasic.tradeFormURL();
		JsonUtil.writeObjToResponse(response, tradeFormURL);
	}
	
	@RequestMapping(value="/trade/newTradeForm.do", method = RequestMethod.POST)
	public void newTradeForm(@RequestParam(value = "tradeType", required = true) String tradeType,
						  HttpServletResponse response){
		int tranType=Integer.valueOf(tradeType);
		SecurityBasic secBasic=secCoreFactory.createSecTranTypeBean(tranType);
		String tradeFormURL = secBasic.newTradeFormURL();
		JsonUtil.writeObjToResponse(response, tradeFormURL);
	}
	
	//TODO: caType获得caForm
	@RequestMapping(value="/trade/caForm.do", method = RequestMethod.POST)
	public void caForm(@RequestParam(value = "caType", required = true) int caType,
						  HttpServletResponse response){
		String caFormURL = "";
		//TODO: 需要根据后面的公司行为的框架体系做更正
		if (caType == CorpActionTypes.CASHPAYMENTSONLY){
			caFormURL = "ca_cashDividends_edit";
		}else if(caType == CorpActionTypes.STOCKDIVIDEND){
			caFormURL = "ca_stockDividends_edit";
		}else if(caType == CorpActionTypes.STOCKSPLIT){
			caFormURL = "ca_stockSplits_edit";
		}else if(caType == CorpActionTypes.STOCK_RESVERSE_SPLIT){
			caFormURL = "ca_stockResverseSplits_edit";
		}
		JsonUtil.writeObjToResponse(response, caFormURL);
	}

	//caType获得caForm, 几许独立使用
	@RequestMapping(value="/trade/caFormTypes.do", method = RequestMethod.POST)
	public void caFormType(@RequestParam(value = "caType", required = true) int caType,
						   HttpServletResponse response){
		String caFormURL = "";
		//TODO: 需要根据后面的公司行为的框架体系做更正
		if (caType == CorpActionTypes.CASHPAYMENTSONLY){
			caFormURL = "/forms/trade/caForms/ca_cashdiv_form";
		}else if(caType == CorpActionTypes.STOCKDIVIDEND){
			caFormURL = "/forms/trade/caForms/ca_stockdiv_form";
		}else if(caType == CorpActionTypes.STOCKSPLIT){
			caFormURL = "/forms/trade/caForms/ca_stocksplit_form";
		}else if(caType == CorpActionTypes.STOCK_RESVERSE_SPLIT){
			caFormURL = "/forms/trade/caForms/ca_reverse_split_form";
		}
		JsonUtil.writeObjToResponse(response, caFormURL);
	}
	
	@RequestMapping(value="/trade/secTypes.do", method = RequestMethod.POST)
	public void secTypes(HttpServletResponse response){
		HashMap<String, String> result = SecurityCoreFactory.getAllAssetTypeHM();
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/trade/tradeMarkets.do", method = RequestMethod.POST)
	public void tradeMarkets(HttpServletResponse response){
		HashMap<Integer, String> result = TradeMarket.getBankAcctTypeHM();
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/trade/calcTradeExpenses.do", method = RequestMethod.POST)
	public void calcTradeExpenses(@RequestParam(value = "port", required = true) String port,
								  @RequestParam(value = "expenses", required = true) String expenses,
								  HttpServletResponse response){
		List<Object> exp_list = JsonUtil.stringToList(expenses);
		double totalfeeamt=tradeService.calTotalFeeAmount(exp_list,port);
		JsonUtil.writeObjToResponse(response, totalfeeamt);
	}
	
	//新界面计算交易费用
	@RequestMapping(value = "/trade/newCalcTradeExpenses.do", method = RequestMethod.POST)
	public void newCalcTradeExpenses(@RequestParam(value = "port", required = false) String port,
			@RequestParam(value = "expenses", required = false) String expenses, HttpServletResponse response) {
		double totalfeeamt=0.0;
		if(port!=null && !port.equals("") && expenses!=null){
			List<HashMap> mapList = (ArrayList<HashMap>) JSON.parseArray(expenses, HashMap.class);
			totalfeeamt = tradeService.newCalTotalFeeAmount(mapList, port);
		}
		JsonUtil.writeObjToResponse(response, totalfeeamt);
	}
	
	//不随交易支付的费用
	@PostMapping(value = "/trade/newCalcTradeExpenses2.do")
	public void newCalcTradeExpenses2(String port, String expenses, HttpServletResponse response) {
		double totalfeeamt=0.0;
		if(port!=null && !port.equals("") && expenses!=null){
			List<HashMap> mapList = (ArrayList<HashMap>) JSON.parseArray(expenses, HashMap.class);
			totalfeeamt = tradeService.newCalTotalFeeAmount2(mapList, port);
		}
		JsonUtil.writeObjToResponse(response, totalfeeamt);
	}
	
	@RequestMapping(value="/trade/calcTradeTotal.do", method = RequestMethod.POST)
	public void calcTradeTotal(@RequestParam(value = "tradeType", required = true) int tradeType,
							   @RequestParam(value = "principal", required = true) double principal,
							   @RequestParam(value = "income", required = true) double income,
							   @RequestParam(value = "expense", required = true) double expense,
							   @RequestParam(value = "trate", required = true) double trate,
							   @RequestParam(value = "srate", required = true) double srate,
							   @RequestParam(value = "secid", required = true) String secid,
							   HttpServletResponse response){
		List<Double> result = new ArrayList<Double>();
		double tamount = 0;
		
		if(tradeType==TransTypes.Futures_LongBuy || tradeType==TransTypes.Futures_ShortSale 
				|| tradeType==TransTypes.Futures_LongSale || tradeType==TransTypes.Futures_ShortBuy){
			IssueInfo longsec=issueInfoDao.loadSecurity(secid.split("\\|")[1]);
			double contractSize=longsec.getContractSize();
			principal=principal*contractSize;
		}
		SecurityBasic secBasic=secCoreFactory.createSecTranTypeBean(tradeType);
		boolean buysalefalg=secBasic.buySaleFlag();
		if(buysalefalg){
			tamount=RoundUtil.roundMoney2Dec(principal+income+expense);
		}else{
			tamount=RoundUtil.roundMoney2Dec(principal+income-expense);
		}
		
		tamount=RoundUtil.roundMoney2Dec(tamount);
		double samount = tamount * trate;
		samount=RoundUtil.roundMoney2Dec(samount/srate);
		result.add(tamount);
		result.add(samount);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/trade/newCalcTradeTotal.do", method = RequestMethod.POST)
	 public void newCalcTradeTotal(@RequestParam(value = "tradeType", required = false) String tradeType,
	          @RequestParam(value = "principal", required = true) double principal,
	          @RequestParam(value = "income", required = true) double income,
	          @RequestParam(value = "expense", required = true) String expenseStr,
	          @RequestParam(value = "trate", required = false) String trateStr,
	          @RequestParam(value = "srate", required = false) String srateStr,
	          @RequestParam(value = "secid", required = false) String secidStr,
	          HttpServletResponse response){
		double tamount=0;
		double samount=0;
		HashMap<String, Double> map = new HashMap<String, Double>();
		if(trateStr!=null && srateStr!=null && secidStr!=null && !tradeType.equals("")){
			double trate=0;
			if(!trateStr.equals(""))
				trate=Double.valueOf(trateStr);
			double srate=0;
			if(!srateStr.equals(""))
				srate=Double.valueOf(srateStr);
			SecurityBasic secBasic = secCoreFactory.createSecTranTypeBean(Integer.valueOf(tradeType));
			boolean buysalefalg = secBasic.buySaleFlag();
			double expense=0;
			if(expenseStr!="") {
				expense=Double.valueOf(expenseStr);
			}
			if (buysalefalg) {
				tamount = RoundUtil.roundMoney2Dec(principal + income + expense);
			} else {
				tamount = RoundUtil.roundMoney2Dec(principal + income - expense);
			}

			tamount = RoundUtil.roundMoney2Dec(tamount);
			samount = tamount * trate;
			samount = RoundUtil.roundMoney2Dec(samount / srate);
		}
		map.put(MsgFields.tradeCash, tamount);
		map.put(MsgFields.settleCash, samount);
		JsonUtil.writeObjToResponse(response, map);
	 }
	
	@RequestMapping(value="/trade/newCalcFxRate.do", method = RequestMethod.POST)
	  public void newCalcFxRate(
	           @RequestParam(value = "longCash", required = true) double longCash,
	           @RequestParam(value = "shortCash", required = true) double shortCash,
	           HttpServletResponse response){
		HashMap<String, Double> map = new HashMap<String, Double>();
		double longCashFx=0;
		if(shortCash!=0)
			longCashFx=RoundUtil.round(longCash/shortCash, 5);
		map.put(MsgFields.longFX, longCashFx);
		JsonUtil.writeObjToResponse(response, map);
	  }
	
	@RequestMapping(value="/trade/calcMaturityTotal.do", method = RequestMethod.POST)
	public void calcMaturityTotal(@RequestParam(value = "tradeType", required = true) int tradeType,
								  @RequestParam(value = "smf", required = true) String smf,
							      @RequestParam(value = "rate", required = true) double rate,
							      @RequestParam(value = "principal", required = true) double principal,
							      @RequestParam(value = "tradeDate", required = true) String tradeDate,
							      @RequestParam(value = "settleDate", required = true) String settleDate,
							      @RequestParam(value = "maturityDate", required = true) String maturityDate,
							      HttpServletResponse response){
		
		List<Double> result = new ArrayList<Double>();
		double mi=0;
		double mt=0;
		
		String[] secidArr=smf.split("\\|");
		if(secidArr.length==3 && principal>0 && rate>0){
			smf=secidArr[1];
			mi = tradeService.genRepoMaturity(smf, rate, principal, DateUtil.convertStr2DateAll(tradeDate), DateUtil.convertStr2DateAll(settleDate), DateUtil.convertStr2DateAll(maturityDate));
			mt = principal+mi;
		}
		result.add(mi);
		result.add(mt);
		
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/trade/calcMaturityInterest.do", method = RequestMethod.POST)
	public void calcMaturityInterest(@RequestParam(value = "tradeType", required = true) int tradeType,
								  @RequestParam(value = "smf", required = true) String smf,
							      @RequestParam(value = "maturityInterest", required = true) double interest,
							      @RequestParam(value = "principal", required = true) double principal,
							      @RequestParam(value = "tradeDate", required = true) String tradeDate,
							      @RequestParam(value = "maturityDate", required = true) String maturityDate,
							      HttpServletResponse response){
		
		List<Double> result = new ArrayList<Double>();
		double rate = 3.5 ;
		double mt = 110000;
		result.add(rate);
		result.add(mt);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/trade/calcEffectiveInterest.do", method = RequestMethod.POST)
	public void calcEffectiveInterest(
								  @RequestParam(value = "portid", required = true) String portid,
								  @RequestParam(value = "smf", required = true) String smf,
							      @RequestParam(value = "amount", required = true) double amount,
							      @RequestParam(value = "principal", required = true) double principal,
							      @RequestParam(value = "tradeDate", required = true) String tradeDateStr,
							      @RequestParam(value = "settleDate", required = true) String settleDateStr,
							      HttpServletResponse response){
		
		String[] smfArr=smf.split("\\|");
		String secID="";
		if(smfArr.length==3){
			secID=smfArr[1];
		}else{
			secID=smf;
		}
		Date tradeDate=DateUtil.convertStr2Date(tradeDateStr.replaceAll("-", ""));
		Date settleDate=DateUtil.convertStr2Date(settleDateStr.replaceAll("-", ""));
		double rate = tradeService.calAmortRate(portid,secID, tradeDate, settleDate, amount, principal);
		JsonUtil.writeObjToResponse(response, rate);
	}
	
	@RequestMapping(value="/trade/dummy.do", method = RequestMethod.POST)
	public void dummy(HttpServletResponse response){
		JsonUtil.writeObjToResponse(response, "");
	}
	
	//估值方法
	@RequestMapping(value="/trade/valuationMethods.do", method = RequestMethod.POST)
	public void valuationMethods(@RequestParam(value = "assetType", required = true) String assetType, 
			HttpServletResponse response){
		HashMap<Integer, String> result = ValuationType.getOptions(assetType);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	//估值方向
	@RequestMapping(value = "/trade/valuationSide.do", method = RequestMethod.POST)
	public void valuationSide(HttpServletResponse response) {
		HashMap<Integer, String> result = ValuationType.getOptionSide();
		JsonUtil.writeObjToResponse(response, result);
	}
		
	//TODO
	@RequestMapping(value="/trade/divSettleMethod.do", method = RequestMethod.POST)
	public void divSettleMethod(HttpServletResponse response){
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		result.put(0, "现金分红");
		result.put(1, "红利再投");
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/trade/optionTypes.do", method = RequestMethod.POST)
	public void optionTypes(HttpServletResponse response){
		HashMap<Integer, String> result = OptionType.optionHM;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	//TODO
	@RequestMapping(value="/trade/optionContractTypes.do", method = RequestMethod.POST)
	public void optionContractTypes(HttpServletResponse response){
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		result.put(0, "美式期权");
		result.put(1, "英式期权");
		JsonUtil.writeObjToResponse(response, result);
	}
	
	//TODO
	@RequestMapping(value="/trade/futuresPriceUnits.do", method = RequestMethod.POST)
	public void futuresPriceUnits(HttpServletResponse response){
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		result.put(0, "元");
		result.put(1, "股指点数");
		JsonUtil.writeObjToResponse(response, result);
	}
	
	//TODO
	@RequestMapping(value="/trade/futuresPurposes.do", method = RequestMethod.POST)
	public void futuresPurpose(HttpServletResponse response){
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		result.put(0, "投机交易");
		result.put(1, "套期保值");
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/trade/accrualBasis.do", method = RequestMethod.POST)
	public void accrualBasis(HttpServletResponse response){
		HashMap<Integer, String> result = TradeSettleLedger.accruedBaseDateOption;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value = "/trade/verifySecurity.do", method = RequestMethod.POST)
	public void verifyURL(@RequestParam(value = "assetType", required = true) String assetType,
						  @RequestParam(value = "secid", required = true) String secid,
						  HttpServletRequest request,
						  HttpServletResponse response) {
		boolean checkFlag=false;
		String[] secArr=secid.split("\\|");
		if(secArr.length>=3){
			IssueInfo sec=issueInfoDao.loadSecurity(secArr[1]);
			if(sec!=null && sec.getIssueType().equals(assetType)){
				checkFlag=true;
			}
		}
		JsonUtil.writeObjToResponse(response, checkFlag);
	}
	
	/**
	 * 定期存款获取合同编号
	 * @param tradeType
	 * @param secid
	 * @param port
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/trade/lotSelect.do", method = RequestMethod.POST)
	public void lotSelect(@RequestParam(value = "tradeType", required = true) int tradeType,
			  			  @RequestParam(value = "secid", required = true) String secid,
			  			  @RequestParam(value = "port", required = true) String port,
			  			  HttpServletRequest request,
			  			  HttpServletResponse response){
		HashMap<String, String> result = new HashMap<String, String>();
		String[] secids=secid.split("\\|");
		if(secids.length>0){
			secid=secids[1];
		}
		
		List<String> lotList=tradeService.loadLotUnits(port, secid);
		
		for(String lotStr:lotList){
			String contract=lotStr.split("\\|")[1];
			result.put(contract, lotStr);
		}
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/trade/lotMethods.do", method = RequestMethod.POST)
	public void lotMethods(HttpServletResponse response){
		HashMap<Integer, String> result = LotSelectionType.optionHM;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	/**
	 * 获取该证券所有的头寸
	 * @param secid
	 * @param port
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/trade/lotPositionSelect.do", method = RequestMethod.POST)
	public void lotSelect(@RequestParam(value = "secid", required = false) String secid,
			  			  @RequestParam(value = "port", required = true) String port,
			  			  HttpServletRequest request,
			  			  HttpServletResponse response){
		
		HashMap<String, String> result = new HashMap<String, String>();
		if(secid!=null&&secid!=""){
			String[] secids=secid.split("\\|");
			if(secids.length==3){
				secid=secids[1];
			}else{
				secid=secids[0];
			}
			List<String> lotList=tradeService.loadLotUnitsPosition(port, secid);
			
			for(String lotStr:lotList){
				String contract=lotStr.split("\\|")[2];
				result.put(contract, lotStr);
			}
		}
		
		JsonUtil.writeObjToResponse(response, result);
	}
	
	
	/**
	 * 获取期货平仓的选择批次
	 * @param secid
	 * @param port
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/trade/futureLotSelect.do", method = RequestMethod.POST)
	public void futureLotSelect(@RequestParam(value = "secid", required = false) String secid,
			  			  @RequestParam(value = "port", required = false) String port,
			  			  @RequestParam(value = "secAcct", required = false) String secAcct,
			  			  @RequestParam(value = "longshort", required = false) String longshort,
			  			  HttpServletRequest request,
			  			  HttpServletResponse response){
		
		HashMap<String, String> result = new HashMap<String, String>();
		if(secid!="" && secid!=null && secAcct!="" && longshort!=""){
			String[] secids=secid.split("\\|");
			if(secids.length==3){
				secid=secids[1];
			}else{
				secid=secids[0];
			}
			String secacct=secAcct;
			int lshort=Integer.valueOf(longshort);
			List<String> lotList=tradeService.loadFutureLotSelect(port, secid ,secacct,lshort);
			
			for(String lotStr:lotList){
				String contract=lotStr.split("\\|")[2];
				result.put(contract, lotStr);
			}
		}
		
		JsonUtil.writeObjToResponse(response, result);
	}
	
	/**
	 * 获取该期权的行权头寸
	 */
	@RequestMapping(value="/trade/optionExercisePosition.do", method = RequestMethod.POST)
	public void optionExercisePosition(@RequestParam(value = "smfID", required = true) String secid,
			  			  @RequestParam(value = "port", required = true) String port,
			  			  @RequestParam(value = "secacct", required = true) String secacct,
			  			  HttpServletRequest request,
			  			  HttpServletResponse response){
		HashMap<String, Object> result = new HashMap<String, Object>();
		String[] secids=secid.split("\\|");
		String cusip=secids[0];
		TranSum tSum = tranSumDao.loadTSum(port, cusip, secacct,LongShortType.longTerm);
		if(tSum!=null){
			result.put(MsgFields.shares, tSum.getAmount());
		}
		JsonUtil.writeObjToResponse(response, result);
	}
	
	
	private HashMap<String, Object> genErrorResult(ResultContainer container){
    	HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> issue = new HashMap<String, Object>();
		issue.put("code", container.getHeader());
		issue.put("level", "fail");
		issue.put("detail", container.getErrorMsg());
		result.put("issue", issue);
		return result;
    }
	
	private String genCollatMsg(List<Object> collFields){
		//TRANFEESUB(Operation,FeeType,Cur(),CapitalisedFeeamt,ExpensedFeeamt,TotalFeeamt,AMTEXCLFROMCASH,DEFERREDFEEAMT,fxRate)
		String collatMsg=MsgTemplate.Collat_Header+"(";
		String subCollatMsgTmp="";
		for(Object obj:collFields){
			List<String> collatArrList=(List<String>)obj;
			String secacct=collatArrList.get(0);
			String secid=collatArrList.get(1).split("\\|")[0];
			String units=collatArrList.get(2);
			
			
			String subcollmsg=msgPackage.collatMsgSub(secacct, secid, units);
			subCollatMsgTmp=subCollatMsgTmp+subcollmsg+",";
		}
		if(subCollatMsgTmp.length()>1){
			subCollatMsgTmp=subCollatMsgTmp.substring(0,subCollatMsgTmp.length()-1);
		}
		collatMsg=collatMsg+subCollatMsgTmp+")";
		
		return collatMsg;
	}
	
	//根据证券id 份数  价格 计算成本
	@RequestMapping(value="/trade/tranPrincipal.do", method = RequestMethod.POST)
	public void tranPrincipal(@RequestParam(value = "secid", required = false) String secid,
							  @RequestParam(value = "share", required = false) String shareStr,
				  			  @RequestParam(value = "price", required = false) String priceStr,
				  			  HttpServletRequest request,
				  			  HttpServletResponse response){
		double result = 0.0;
		if(secid!=null&&shareStr!=null&&priceStr!=null){
			String[] smfArr=secid.split("\\|");
			String secID="";
			if(smfArr.length>1){
				secID=smfArr[1];
			}
			double share=Double.valueOf(shareStr);
			double price=Double.valueOf(priceStr);
			if (secID.equals("")){
				result = RoundUtil.roundMoney2Dec( share * price);
			}
			else{
				IssueInfo sec=issueInfoDao.loadSecurity(secID);
				double contractSize=sec.getContractSize();
				result =RoundUtil.roundMoney2Dec( share * price);
				if(sec.getIssueType().equals(AssetTypes.FUTURES)){
					result=RoundUtil.roundMoney2Dec(result*contractSize);
				}
			}
		}
		JsonUtil.writeObjToResponse(response, result);
	}
		
	//根据证券id 份数  成本 计算价格
	@RequestMapping(value="/trade/tranPrice.do", method = RequestMethod.POST)
	public void tranPrice(@RequestParam(value = "secid", required = false) String secid,
						  @RequestParam(value = "share", required = false) String shareStr,
			  			  @RequestParam(value = "principal", required = false) String principalStr,
			  			  HttpServletRequest request,
			  			  HttpServletResponse response){
		double result = 0.0;
		if(secid!=null&&shareStr!=null&&principalStr!=null){
			String secID="";
			String[] smfArr=secid.split("\\|");
			if(smfArr.length>1){
				secID=smfArr[1];
			}
			double share=Double.valueOf(shareStr);
			double principal=Double.valueOf(principalStr);
			if(share!=0) {
				if (secID.equals("")){
					result = principal/share;
				}
				else{
					IssueInfo sec=issueInfoDao.loadSecurity(secID);
					double contractSize=sec.getContractSize();
					result = principal/share;
					if(sec.getIssueType().equals(AssetTypes.FUTURES)){
						result=RoundUtil.roundMoney2Dec(result/contractSize);
					}
				}
			}
		}
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value = "/trade/PortTranMapType.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getPortTranMapType(@RequestParam(value = "portnum", required = true) String portnum,HttpServletRequest request){
		List<PortTranMapType> typeList=tradeService.getPortTranMapTypeList(portnum);
		return typeList;
	}
	
	/**
	 * 点击估增/收入凭证最后的编号，直接跳转到该方法，显示信息
	 * @param response
	 * @param tranCloID
	 */
	private void getGeneralIncomePostion(HttpServletResponse response, String tranCloID){
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("证券代码", "产品名称", "日期", "证券名称", "类型", "数量", "货币","摊余成本","成本(原币)","市值(原币)","利息(原币)", "货币", "成本(本币)","市值(本币)","利息(本币)","汇率");
		result.put("titles", titles);
		List<SecPositionModel> positionList=secCashService.getSecPositionByCloID(tranCloID);
		List<Object> data = new ArrayList<Object>();
		
		for(SecPositionModel position:positionList){
			List<Object> item = new ArrayList<Object>();
			item.add(position.getSecID());
			item.add(position.getPortname());
			item.add(position.getAsOfDate());
			item.add(position.getSecDesc());
			item.add(position.getSecType());
			//item.add(position.getAltDate());
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
		
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value = "/trade/getTradeVoucherByTanum.do", method = RequestMethod.POST)
	public void getTradeVoucherByTanum(@RequestParam(value = "tradeID", required = true) String tradeID, 
							HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		ArrayList<Object> data = new ArrayList<Object>();
		ResultContainer container=tradeService.getTradeVouhcerDetailsViewList(tradeID);
		if(container.isCorrectFlag()){
			List<VoucherDetailsView> vdList=(List<VoucherDetailsView>)container.getEntityObj();
			
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
		}else{
			commService.controllerReturn(response, container);
		}
	}
	
	/**
	 * 计算组合批次的绩效费用和回拨费用
	 * @param portnum
	 * @param secids
	 * @param contract
	 * @param shares
	 * @param response
	 */
	@RequestMapping(value="/trade/portPerformanceFee.do", method = RequestMethod.POST)
	public void calcPortPerformanceFee(@RequestParam(value = "port", required = true) String portnum,
									 @RequestParam(value = "secid", required = true) String secids,
									 @RequestParam(value = "contract", required = true) int contract,
									 @RequestParam(value = "shares", required = false) double shares,
									 HttpServletResponse response){
		String[] secidarr=secids.split("\\|");
		String secid=secids;
		if(secidarr.length>1){
			secid=secidarr[1];
		}
		IssueInfo sec=issueInfoDao.loadSecurity(secid);
		double[] returns=tradeService.calPerformanceFee(portnum, sec.getId(), contract, shares);
		
		HashMap<String, Double> cc = new HashMap<String, Double>();
		cc.put("performanceFee", returns[0]);
		cc.put("clawback", returns[1]);
		JsonUtil.writeObjToResponse(response, cc);
	}
	
	/**
	 * 计算组合批次的应付收益
	 * @param portnum
	 * @param secids
	 * @param contract
	 * @param tradeDate
	 * @param shares
	 * @param response
	 */
	@RequestMapping(value="/trade/portIncomePayable.do", method = RequestMethod.POST)
	public void portIncomePayable(@RequestParam(value = "port", required = true) String portnum,
									 @RequestParam(value = "secid", required = true) String secids,
									 @RequestParam(value = "contract", required = true) int contract,
									 @RequestParam(value = "tradeDate", required = true) String tradeDate,
									 @RequestParam(value = "shares", required = true) double shares,
									 HttpServletResponse response){
		String[] secidarr=secids.split("\\|");
		String secid=secids;
		if(secidarr.length>1){
			secid=secidarr[1];
		}
		IssueInfo sec=issueInfoDao.loadSecurity(secid);
		double payincomes=tradeService.getSalePortIncomes(portnum, sec.getId(), contract, shares);
		JsonUtil.writeObjToResponse(response, payincomes);
	}
	
	/**
	 * 计算组证券卖出应付的利息
	 * @param portnum
	 * @param secids
	 * @param contract
	 * @param tradeDate
	 * @param shares
	 * @param response
	 */
	@RequestMapping(value="/trade/incomeSalePayable.do", method = RequestMethod.POST)
	public void incomeSalePayable(@RequestParam(value = "port", required = true) String portnum,
									 @RequestParam(value = "secid", required = true) String secids,
									 @RequestParam(value = "contract", required = true) int contract,
									 @RequestParam(value = "tradeDate", required = true) String tradeDate,
									 @RequestParam(value = "shares", required = true) double shares,
									 HttpServletResponse response){
		String[] secidarr=secids.split("\\|");
		String secid=secids;
		if(secidarr.length>1){
			secid=secidarr[1];
		}
		IssueInfo sec=issueInfoDao.loadSecurity(secid);
		double payincomes=tradeService.getIncomeSalePayable(portnum, sec.getId(), contract, shares);
		JsonUtil.writeObjToResponse(response, payincomes);
	}
	
	/**
	 * 获取股权登记日当天的股票数量
	 * @param portnum
	 * @param secid
	 * @param divDate
	 * @param response
	 */
	@RequestMapping(value="/trade/caSecPositionShares.do", method = RequestMethod.POST)
	public void incomeSalePayable(@RequestParam(value = "port", required = false) String portnum,
									 @RequestParam(value = "secid", required = false) String secid,
									 @RequestParam(value = "divDate", required = false) String divDate,
									 HttpServletResponse response){
		double units=100;
		if(portnum!=null && secid!=null && divDate!=null) {
			Date closeDate=DateUtil.convertStr2Date2(divDate);
			units=tradeService.getPositionUnits(portnum, secid, closeDate);
		}
		JsonUtil.writeObjToResponse(response, units);
	}
	
	/**
	 * 获得组合批次的单位净值
	 * @param portnum
	 * @param secids
	 * @param contract
	 * @param tradeDate
	 * @param response
	 */
	@RequestMapping(value="/trade/portBatchNAV.do", method = RequestMethod.POST)
	public void portBatchNAV(@RequestParam(value = "port", required = true) String portnum,
							 @RequestParam(value = "secid", required = true) String secids,
							 @RequestParam(value = "contract", required = true) int contract,
							 @RequestParam(value = "tradeDate", required = true) String tradeDate,
							 HttpServletResponse response){
		String[] secidarr=secids.split("\\|");
		String secid=secids;
		if(secidarr.length>1){
			secid=secidarr[1];
		}
		IssueInfo sec=issueInfoDao.loadSecurity(secid);
		double nav=tradeService.getPortBatchNAV(portnum, sec.getId(), contract);
		JsonUtil.writeObjToResponse(response, nav);
	}
	
	/**
	 * 获得组合批次的某个费用的当前计提信息
	 * @param portnum
	 * @param secids
	 * @param contract
	 * @param expenseType
	 * @param tradeDate
	 * @param response
	 */
	@RequestMapping(value="/trade/portExpensePayable.do", method = RequestMethod.POST)
	public void portExpensePayable(@RequestParam(value = "port", required = true) String portnum,
							 	   @RequestParam(value = "secid", required = true) String secids,
							 	   @RequestParam(value = "contract", required = true) int contract,
							 	   @RequestParam(value = "expenseType", required = true) int expenseType,
							 	   @RequestParam(value = "tradeDate", required = true) String tradeDate,
							 	   HttpServletResponse response){
		String[] secidarr=secids.split("\\|");
		String secid=secids;
		if(secidarr.length>1){
			secid=secidarr[1];
		}
		IssueInfo sec=issueInfoDao.loadSecurity(secid);
		double fees=tradeService.getPortExpensePayable(portnum, sec.getId(), contract, expenseType);
		JsonUtil.writeObjToResponse(response, fees);
	}
	
	@RequestMapping(value="/trade/lotChoices.do", method = RequestMethod.POST)
	public void lotChoices(HttpServletResponse response){
		HashMap<Integer, String> result = LotType.getLotChoices();
		JsonUtil.writeObjToResponse(response, result);
	}
	
	private void exeOpertion(String json, String operType, HttpServletResponse response) {
		HashMap<String, Object> fieldsMap = JSONObject.parseObject(json, HashMap.class);
		MsgContainer container=new MsgContainer();
		container.setFieldsHM(fieldsMap);
		container.setJsonMsg(json);
		container.setUserID(JixuUserUtil.getUserID());
		if(operType==null || operType.equals(""))
			container=tradeService.operateTran(container);
		else
			container=tradeService.operateTranWF(container);
		controllerReturnNew(response, container);
	}
	
	/**
	 * 获取上一个计息截止日
	 * @param response
	 */
	@RequestMapping(value = "/trade/returnLastDivBasicDate.do", method = RequestMethod.POST)
	public void returnLastDivBasicDate(
			@RequestParam(value = "secid", required = false) String secid,
			@RequestParam(value = "tradeDate", required = false) String tradeDate,
			@RequestParam(value = "tradeType", required = false) int tradeType,
			HttpServletResponse response) {
		String returnDate=DateUtil.convertD2YYYY_MM_DD(new Date());
		if(secid==null) {
			returnDate=DateUtil.convertD2YYYY_MM_DD(new Date());
		}else {
			//获取证券账号
			String[] smfArr=secid.split("\\|");
			String secID="";
			if(smfArr.length==3){
				secID=smfArr[1];
			}else{
				secID=secid;
			}
			Date lastEndDate=tradeService.returnLastDivBasicDate(secID, DateUtil.convertStr2Date2(tradeDate), tradeType);
			
			returnDate=DateUtil.convertD2YYYY_MM_DD(lastEndDate);
		}
		JsonUtil.writeObjToResponse(response, returnDate);
	}
	
	/**
	 * 	获取费用化金额
	 * @param expense
	 * @param totalAmount
	 */
	@PostMapping(value="/trade/getTradeFeeDtoByIDandAmount.do")
	public void getTradeFeeDtoByIDandAmount(String expense, String totalAmount, HttpServletResponse response) {
		if(StringUtils.isEmpty(expense))
			expense="0";
		if(StringUtils.isEmpty(totalAmount))
			totalAmount="0";
		
		int expenseInt=Integer.parseInt(expense);
		double totalAmountDouble=Double.parseDouble(totalAmount);
		double fee=tradeService.genUnCapitalizedPercent(expenseInt, totalAmountDouble);
		JsonUtil.writeObjToResponse(response, fee);
	}
	
	/**
	 * 	随交易支付金额
	 * @param expense
	 * @param totalAmount
	 */
	@PostMapping(value="/trade/isPaywithTrade.do")
	public void isPaywithTrade(String expense, HttpServletResponse response) {
		if(StringUtils.isEmpty(expense))
			expense="0";
		int expenseInt=Integer.parseInt(expense);
		int ispay=tradeService.isPaywithTrade(expenseInt);
		JsonUtil.writeObjToResponse(response, ispay);
	}
	
	/**
	 * 获取是否随交易支付下拉框
	 * @param response
	 */
	@PostMapping(value="/trade/getPayTradeType.do")
	public void getPayTradeType(HttpServletResponse response) {
		HashMap<Integer, String> result=TranFeeTypes.getPayTradeType();
		JsonUtil.writeObjToResponse(response, result);
	}
	
	/**
	 * 获取是否随交易支付
	 * @param cashAmount
	 * @param response
	 */
	@PostMapping(value="/trade/getPayTradeTypeValue.do")
	public void getPayTradeTypeValue(String cashAmount,HttpServletResponse response) {
		if(StringUtils.isEmpty(cashAmount))
			cashAmount="0";
		int cashAmountInt=Integer.parseInt(cashAmount);
		HashMap<Integer, String> result=TranFeeTypes.getPayTradeType();
		String value=result.get(cashAmountInt);
		JsonUtil.writeObjToResponse(response, value);
	}
}
