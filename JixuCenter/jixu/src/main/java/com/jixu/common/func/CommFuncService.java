package com.jixu.common.func;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.pojo.Users;
import com.jixu.common.tool.JixuContextBeansUtil;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.InterfaceSourceType;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.FeeTypesAmount;
import com.jixu.common.types.JixuDictCode;
import com.jixu.common.util.DataConvert;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.JsonUtil;
import com.jixu.common.util.ReturnResult;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.BkCashAcctDao;
import com.jixu.dao.intf.BkCashCloDao;
import com.jixu.dao.intf.FxCurrencyDao;
import com.jixu.dao.intf.FxRateDao;
import com.jixu.dao.intf.InterfaceSourceDao;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.JobsHisDao;
import com.jixu.dao.intf.NavDao;
import com.jixu.dao.intf.PortfolioHdrDao;
import com.jixu.dao.intf.SecPriceDao;
import com.jixu.dao.intf.TranSubRedDao;
import com.jixu.framework.dao.intf.HolidayLSDao;
import com.jixu.framework.dao.intf.UsersDao;
import com.jixu.framework.msg.MsgFactory;
import com.jixu.mvc.control.service.PortfolioCfgService;
import com.jixu.secTran.SecurityBasic;
import com.jixu.secTran.SecurityCoreFactory;
import com.joyintech.framework.util.CacheUtil;
import com.orm.obj.BkCashClo;
import com.orm.obj.FxCurrency;
import com.orm.obj.IssueInfo;
import com.orm.obj.JobsHis;
import com.orm.obj.Nav;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.SecAcct;
import com.orm.obj.TranBasicFee;
import com.orm.obj.TranSubRed;

@Service("commfuncService")
public class CommFuncService {

	//private static final Logger logger = LoggerFactory.getLogger(CommFuncService.class);

	@Autowired
	private SecPriceDao secPriceDao;
	
	@Autowired
	private IssueInfoDao issueInfoDao;
	
	@Autowired
	private PortfolioHdrDao portfolioHdrDao;
	
	@Autowired
	private TranSubRedDao tranSubRedDao;

	@Autowired
	private FxRateDao fxRateDao;
	
	@Autowired
	private NavDao navDao;
	
	@Autowired
	private JobsHisDao jobsHisDao;

	@Autowired
	private FxCurrencyDao fxCurrencyDao;
	
	@Autowired
	private HolidayLSDao holidayLSDao;
	
	@Autowired
	private BkCashCloDao bkCashCloDao;
	
	@Autowired
	private InterfaceSourceDao interfaceSourceDao;
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private SecurityCoreFactory secCoreFactory;
	
	@Autowired
	private PortfolioCfgService portCfgService;
	
	@Autowired
	private OutSystemInterface outSystemInterface;
	
	@Autowired
	private BkCashAcctDao bkCashAcctDao;
	
	/**
	 * 获取价格。根据投资组合配置的数据接口，获取包括当天之前最新的一条价格记录,如果获取不到价格，则为0
	 * @param portnum	投资组合
	 * @param cusip		证券系统编号
	 * @param priceDate	价格日期yyyymmdd
	 * @return
	 */
	public double loadCusipPrice(String portnum, String cusip, Date priceDate) {
		IssueInfo sec=issueInfoDao.loadIssueByCusip(cusip);
		if(sec!=null) {
			if(!JixuUserUtil.isJoyinTablesFlag()) {
				if(sec.getRpSecType()!=null){
					//类似swap的证券，是没有细分类型的
					//默认获取投资组合配置的数据源，如果没有数据，再取手工配置的数据
					int sourceID=interfaceSourceDao.getInterfaceSourceID(portnum, InterfaceSourceType.price, sec.getRpSecType().getId());
					double price=secPriceDao.loadCusipPrice(cusip, priceDate, sourceID);
					/*if(price==0){
						logger.error("Fail to load price " + portnum + "/" + priceDate + "/" + sec.getSecurityid());
					}*/
					return price;
				}else{
					return 0;
				}
			}else {
				return outSystemInterface.loadCusipPrice(portnum, sec.getSecurityid(), sec.getIssueType(), sec.getExchangeCode(), priceDate);
			}
		}else 
			return 0;
	}
	
	public IssueInfo loadSecByCusip(String cusip){
		return issueInfoDao.loadIssueByCusip(cusip);
	}

	/**
	 * 获取价格。根据投资组合配置的数据接口，获取包括当天之前最新的一条汇率记录,如果获取不到汇率，则为1。例如：投资组合配置的汇率数据源是央行汇率，
	 * 人民币兑美元的汇率，2018年1月1日，为6.6
	 * @param portnum
	 * @param funcCurCode
	 * @param localCurCdoe
	 * @param date
	 * @return
	 */
	public double loadFxrate(String portnum, String funcCurCode, String localCurCdoe, Date date) {
		if(funcCurCode.equals(localCurCdoe))
			return 1;
		
		if(!JixuUserUtil.isJoyinTablesFlag()) {
			//默认获取投资组合配置的数据源，如果没有数据，再取手工配置的数据
			int sourceID=interfaceSourceDao.getInterfaceSourceID(portnum, InterfaceSourceType.fxRate, 0);
			double fxrate = fxRateDao.loadFxrate(funcCurCode, localCurCdoe, date, sourceID);
			if(fxrate==0){
				fxrate=1;
				//logger.info("Fail to load fxcode '" + funcCurCode + "/" + localCurCdoe +  "' rate on " + date);
			}
			return fxrate;
		}else {
			return outSystemInterface.loadFxrate(portnum, funcCurCode, localCurCdoe, date);
		}
	}
	
	/**
	 * 根据投资组合编号,获取所有的节假日
	 * @param portnum 投资组合编号
	 * @return
	 */
	public List<Date> getHolidays(String portnum){
		PortfolioHdr portfolio=this.loadPortfolio(portnum);
		List<Date> holidayList=holidayLSDao.loadHolidayList(portfolio.getHolidaySD());
		return holidayList;
	}

	/**
	 * 根据投资组合编号，获取投资组合实例。该查询放入缓存
	 * @param portnum	投资组合编号
	 * @return
	 */
	public PortfolioHdr loadPortfolio(String portnum){
		return portfolioHdrDao.loadPortfolio(portnum);
	}

	/**
	 * 计算交易中的费用加上/减去交易成本,最后实际应收应付
	 * @param tranType	交易类型
	 * @param cash		成本金额
	 * @param feeList	费用列表
	 * @return
	 */
	public double calTranCashWithFee(int tranType, double cash, List<TranBasicFee> feeList){
		double feeTotal=0;
		for(TranBasicFee feetran:feeList){
			FeeTypesAmount feetypeamt=genFeeTypesAmount(feetran);
			feeTotal=feeTotal+feetypeamt.getCashExpensedFee()+feetypeamt.getCashCapitalFee();
		}
		
		SecurityBasic secBasic=secCoreFactory.createSecTranTypeBean(tranType);
		boolean buysalefalg=secBasic.buySaleFlag();
		if(buysalefalg){
			cash=RoundUtil.roundMoney2Dec(cash+feeTotal);
		}else{
			cash=RoundUtil.roundMoney2Dec(cash-feeTotal);
		}
		
		return cash;
	}
	
	/**
	 * 计算交易中的费用加上/减去交易成本,最后实际应收应付
	 * @param tranType	交易类型
	 * @param feeList	费用列表
	 * @return
	 */
	public double calTranCashWithFee(List<TranBasicFee> feeList){
		double feeTotal=0;
		for(TranBasicFee feetran:feeList){
			FeeTypesAmount feetypeamt=genFeeTypesAmount(feetran);
			feeTotal=feeTotal+feetypeamt.getCashExpensedFee()+feetypeamt.getCashCapitalFee();
		}
		return RoundUtil.roundMoney2Dec(feeTotal);
	}
	
	/**
	 * 根据费用配置，计算费用明细。获取该交易的现金资本化费用，应收应付资本化费用，现金成本化费用，应收应付成本化费用
	 * @param fee
	 * @return	返回费用具体金额，里面包括现金资本化费用，应收应付资本化费用，现金成本化费用，应收应付成本化费用
	 */
	public FeeTypesAmount genFeeTypesAmount(TranBasicFee fee){
		double totalfee=fee.getTotalFeeamt();
		double rcpypercent=0;
		if(totalfee!=0){
			rcpypercent=fee.getAmtexclfromcash()/totalfee;	
		}
		
		double capitalFee=fee.getCapitalisedfeeamt();
		double expensedFee=fee.getExpenseFeeamt();
		
		double rcpyCapitalFee=RoundUtil.roundMoney2Dec(capitalFee*rcpypercent);
		double cashCapitalFee=RoundUtil.roundMoney2Dec(capitalFee-rcpyCapitalFee);
		
		double rcpyExpensedFee=RoundUtil.roundMoney2Dec(expensedFee*rcpypercent); 
		double cashExpensedFee=RoundUtil.roundMoney2Dec(expensedFee-rcpyExpensedFee);
		
		if(fee.getRecOrPay()==DBCR.receive){
			cashCapitalFee=-1*cashCapitalFee;
			rcpyCapitalFee=-1*rcpyCapitalFee;
			cashExpensedFee=-1*cashExpensedFee;
			rcpyExpensedFee=-1*rcpyExpensedFee;
		}
		
		FeeTypesAmount feeModel=new FeeTypesAmount();
		feeModel.setRecOrPay(fee.getRecOrPay());
		feeModel.setCashCapitalFee(cashCapitalFee);
		feeModel.setRcpyCapitalFee(rcpyCapitalFee);
		feeModel.setCashExpensedFee(cashExpensedFee);
		feeModel.setRcpyExpensedFee(rcpyExpensedFee);
		
		return feeModel;
	}
	
	/**
	 * 更新job历史表，工作流将读取job历史表清单统计数据
	 * @param userID	用户id
	 * @param portfolio	投资组合实例
	 * @param ledgerDate	出账日
	 * @param jobID			jobID
	 * @param container		容器，执行结果也会在容器里带出来，包括错误信息
	 */
	public void updateJobsHis(int userID, PortfolioHdr portfolio, Date ledgerDate, int jobID, ResultContainer container){
		if(!JixuUserUtil.isJoyinTablesFlag()) {
			JobsHis oldjobshis=jobsHisDao.loadJobsHist(portfolio.getId(), ledgerDate, jobID);
			if(oldjobshis==null){
				JobsHis jobshis=new JobsHis();
				jobshis.setJobID(jobID);
				jobshis.setLedgerDate(ledgerDate);
				jobshis.setPortfolio(portfolio);
				jobshis.setStatus(container.isCorrectFlag());
				jobshis.setFailedDesc(container.getErrorMsg());
				jobshis.setUserID(userID);
				jobsHisDao.saveEntity(jobshis);
			}else{
				oldjobshis.setStatus(container.isCorrectFlag());
				oldjobshis.setFailedDesc(container.getErrorMsg());
				oldjobshis.setUserID(userID);
				jobsHisDao.updateEntity(oldjobshis);
			}
		}
	}
	
	/**
	 * 获取最后一个净值日
	 * @param portnum	投资组合编号
	 * @param ledgerDate	出账日
	 * @return	最后一个净值日
	 */
	public Date loadLatestNavDate(String portnum, Date ledgerDate){
		Nav nav=navDao.loadLatestNAV(portnum, ledgerDate);
		if(nav==null){
			return DateUtil.convertStr2Date("20000101");
		}else{
			return nav.getNavDate();
		}
	}
	
	/**
	 * 根据货币ID获取货币简称
	 * @param fxCurNum	货币ID
	 * @return	货币简称，类似CNY,USD
	 */
	public String loadFxCurrencyCode(String curID) {
		FxCurrency fxcur=null;
		if(JixuUserUtil.isJoyinTablesFlag()) {
			fxcur=outSystemInterface.loadFxCurrency(curID);
		}else {
			fxcur=fxCurrencyDao.loadFxCurrency(curID);
		}
		if(fxcur!=null){
			return fxcur.getCurrCode();
		}else{
			return "";
		}
	}
	
	/**
	 * 根据货币ID获取货币简称
	 * @param fxCurNum	货币ID
	 * @return	货币简称，类似CNY,USD
	 */
	public String loadFxCurrencyName(String curID) {
		FxCurrency fxcur=null;
		if(JixuUserUtil.isJoyinTablesFlag()) {
			fxcur=outSystemInterface.loadFxCurrency(curID);
		}else {
			fxcur=fxCurrencyDao.loadFxCurrency(curID);
		}
		if(fxcur!=null){
			return fxcur.getCurrName();
		}else{
			return "";
		}
	}
	
	/*** 加载某种货币信息 **/
	public String loadFxCurIDByCurCode(String currcode) {
		FxCurrency fxcur=null;
		if(JixuUserUtil.isJoyinTablesFlag()) {
			//joyin没有id这个主键，jixu这里有个id主键，再有code。目前joyin的做法是id=code
			fxcur=outSystemInterface.loadFxCurrency(currcode);
		}else {
			fxcur=fxCurrencyDao.loadFxCurrencyIDByCurCode(currcode);
		}
		if(fxcur!=null){
			return fxcur.getId();
		}else{
			return "";
		}
	}

	/**
	 * 根据用户ID获取用户实例，通过缓存获取
	 * @param userID	用户ID
	 * @return	用户实例
	 */
	@Cacheable(value="portDataCache", key="'UserID'+#userID")
	public Users loadUserByID(int userID){
		return (Users)usersDao.loadEntity(Users.class, userID);
	}
	
	/**
	 * 执行消息，传递给MsgFactory执行
	 * @param request	httprequest，获取执行用户id
	 * @param jsonMsg	执行的json消息
	 * @return	消息执行结果，错误信息
	 */
	@SuppressWarnings("unchecked")
	public MsgContainer executeMsg(HttpServletRequest request, String jsonMsg){
		int userID=JixuUserUtil.getUserIDbyRequest(request);
		HashMap<String, Object> fieldsMap=JSONObject.parseObject(jsonMsg, HashMap.class);
		MsgContainer msgcontainer=new MsgContainer();
		msgcontainer.setFieldsHM(fieldsMap);
		msgcontainer.setJsonMsg(jsonMsg);
		msgcontainer.setUserID(userID);
		msgcontainer=MsgFactory.executeMsgBean(msgcontainer);
		return msgcontainer;
	}
	
	/**
	 * 执行消息，传递给MsgFactory执行
	 * @param request	httprequest，获取执行用户id
	 * @param jsonMsg	执行的json消息
	 * @return	消息执行结果，错误信息
	 */
	@SuppressWarnings("unchecked")
	public MsgContainer executeMsg(String jsonMsg){
		HashMap<String, Object> fieldsMap=JSONObject.parseObject(jsonMsg, HashMap.class);
		MsgContainer msgcontainer=new MsgContainer();
		msgcontainer.setFieldsHM(fieldsMap);
		msgcontainer.setJsonMsg(jsonMsg);
		msgcontainer.setUserID(JixuUserUtil.getUserID());
		msgcontainer=MsgFactory.executeMsgBean(msgcontainer);
		return msgcontainer;
	}
	
	public MsgContainer executeMsgNoTx(String jsonMsg){
		HashMap<String, Object> fieldsMap=JSONObject.parseObject(jsonMsg, HashMap.class);
		MsgContainer msgcontainer=new MsgContainer();
		msgcontainer.setFieldsHM(fieldsMap);
		msgcontainer.setJsonMsg(jsonMsg);
		msgcontainer.setUserID(JixuUserUtil.getUserID());
		msgcontainer=MsgFactory.executeMsgBeanNoTx(msgcontainer);
		return msgcontainer;
	}
	
	/**
	 * joyin的工作流执行消息
	 * @param jsonMsg
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public MsgContainer executeNewProcessMsg(String jsonMsg){
		HashMap<String, Object> fieldsMap=JSONObject.parseObject(jsonMsg, HashMap.class);
		MsgContainer msgcontainer=new MsgContainer();
		msgcontainer.setNewProcessFlag(true);
		msgcontainer.setFieldsHM(fieldsMap);
		msgcontainer.setJsonMsg(jsonMsg);
		msgcontainer.setUserID(JixuUserUtil.getUserID());
		msgcontainer=MsgFactory.executeMsg(msgcontainer);
		return msgcontainer;
	}
	
	/**
	 * 执行消息，传递给MsgFactory执行
	 * @param userID	用户id
	 * @param jsonMsg	执行的json消息
	 * @return	消息执行结果，错误信息
	 */
	@SuppressWarnings("unchecked")
	public MsgContainer executeMsg(int userID, String jsonMsg){
		HashMap<String, Object> fieldsMap=JSONObject.parseObject(jsonMsg, HashMap.class);
		MsgContainer msgcontainer=new MsgContainer();
		msgcontainer.setFieldsHM(fieldsMap);
		msgcontainer.setUserID(userID);
		msgcontainer=MsgFactory.executeMsgBean(msgcontainer);
		return msgcontainer;
	}
	
	/**
	 * 统一处理Controller的返回界面信息
	 * @param container	信息容器，包含正确/错误标志位，错误信息
	 * @return	返回界面的信息
	 */
	public Object controllerReturn(MsgContainer container){
		if(container.isCorrectFlag()){
			return container.getObjID();
		}else{
			HashMap<String, Object> result = new HashMap<String, Object>();
			HashMap<String, Object> issue = new HashMap<String, Object>();
			issue.put("code", container.getErrorCode());
			issue.put("level", "fail");
			issue.put("detail", container.getErrorMsg());
			result.put("issue", issue);
			return result;
		}
	}
	
	/**
	 * 判断ID编号是否存在
	 * @param response
	 * @param idStr
	 * @return
	 */
	public boolean checkIDExist(HttpServletResponse response, String idStr){
		if(DataConvert.isInteger(idStr)){
			return true;
		}else{
			HashMap<String, Object> result = new HashMap<String, Object>();
			HashMap<String, Object> issue = new HashMap<String, Object>();
			issue.put("code", ReturnResult.id_not_exist);
			issue.put("level", "fail");
			issue.put("detail", ReturnResult.getResultHM().get(ReturnResult.id_not_exist));
			result.put("issue", issue);
			JsonUtil.writeObjToResponse(response, result);
			return false;
		}
	}
	
	/**
	 * 统一处理Controller的返回界面信息。通过reponse返回值
	 * @param response	httpresponse
	 * @param container 信息容器，包含正确/错误标志位，错误信息
	 */
	public void controllerReturn(HttpServletResponse response, MsgContainer container){
		if(container.isCorrectFlag()){
			if(container.getObjID()!=0)
				JsonUtil.writeObjToResponse(response, container.getObjID());
			else
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
	
	/**
	 * 统一处理Controller的返回界面信息。通过reponse返回值
	 * @param response	httpresponse
	 * @param container 信息容器，包含正确/错误标志位，错误信息
	 */
	public void controllerReturn(HttpServletResponse response, ResultContainer container){
		if(container.isCorrectFlag()){
			JsonUtil.writeObjToResponse(response, container.getObjID());
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
	
	/**
	 * 计算当天的基金份额
	 * @param portnum
	 * @param asOfDate
	 * @return
	 */
	public double calFundShares(String portnum, Date asOfDate){
		Nav nav=navDao.loadLatestNAV(portnum, asOfDate);
		List<String> portList=portCfgService.loadPortfolioClassIDRecursion(portnum);
		List<TranSubRed> subredList=tranSubRedDao.loadTranSubRedInClass(portList, asOfDate);
		double totalAmount=0;
		for(TranSubRed subred:subredList){
			if(subred.getTranType()==TransTypes.SUB || subred.getTranType()==TransTypes.Share_Split
					|| subred.getTranType()==TransTypes.Share_init || subred.getTranType()==TransTypes.Share_Div2Share){
				totalAmount=totalAmount+subred.getAmount();
			}else if(subred.getTranType()==TransTypes.RED){
				totalAmount=totalAmount-subred.getAmount();
			}
		}
		
		double sharesOutStanding=0;
		
		if(nav!=null){
			sharesOutStanding=RoundUtil.roundMoney2Dec(nav.getSharesOutStanding()+totalAmount);
		}else{
			sharesOutStanding=RoundUtil.roundMoney2Dec(totalAmount);
		}
		
		return sharesOutStanding;
	}
	
	/**
	 * 获取投资组合某日期的现金头寸
	 * @param portnum
	 * @param closeDate
	 * @return
	 */
	public List<BkCashClo> loadBkCashClo(String portnum, Date closeDate){
		return bkCashCloDao.loadBkCashClo(portnum, closeDate);
	}
	
	/**
	 * 复制result container的内容到msgcontainer
	 * @param resultContainer
	 * @param msgContainer
	 */
	public void copyResult2MsgContainer(ResultContainer resultContainer, MsgContainer msgContainer){
		msgContainer.setCorrectFlag(resultContainer.isCorrectFlag());
		msgContainer.setErrorCode(resultContainer.getErrorCode());
		msgContainer.setErrorMsg(resultContainer.getErrorMsg());
		msgContainer.setEntityObj(resultContainer.getEntityObj());
		msgContainer.setObjID(resultContainer.getObjID());
	}
	
	/**
	 * @return	匹配外部的数据字典
	 */
	public HashMap<String, HashMap<String, Object>> mapOutDataDict(){
		HashMap<String, HashMap<String, Object>> map=new HashMap<String, HashMap<String,Object>>();
		
		//银行
//		List<BkCashAcct> cashAcctList=bkCashAcctDao.loadEntityAll(BkCashAcct.class);
//		HashMap<String, Object> cashAcctMap=new HashMap<String, Object>();
//		for(BkCashAcct cashacct:cashAcctList) {
//			//key 银行账户
//			//value 银行在jixu的id
//			cashAcctMap.put(cashacct.getExternalacctnumber(), String.valueOf(cashacct.getId()));
//		}
//		map.put(JixuDictCode.JX_JOYIN_MAP_BANK_CASH_ACCT, cashAcctMap);
		
		//证券账户
		HashMap<String, Object> secAcctMap=new HashMap<String, Object>();
		List<SecAcct> secAcctList=bkCashAcctDao.loadEntityAll(SecAcct.class);
		for(SecAcct secacct:secAcctList) {
			//key: 投资组合
			//value: 证券账户
			secAcctMap.put(secacct.getPortfolio(), secacct.getAcctID());
		}
		map.put(JixuDictCode.JX_JOYIN_MAP_SEC_ACCT, secAcctMap);
		
		return map;
	}
	
	/**
	 * 获取浮动利率的第三方模型类
	 * @return
	 */
	public HashMap<String, String> getFloatRateIDClassMap(){
		HashMap<String, String> floatRateMap=new HashMap<String, String>();
		Map<String, FloatRateService> floatfuncMap=JixuContextBeansUtil.getBeansOfType(FloatRateService.class);
		for (Map.Entry<String, FloatRateService> entry: floatfuncMap.entrySet()) {
			FloatRateService fileBean=entry.getValue();
			//用于在界面上显示  基准类名称
			floatRateMap.put(entry.getKey(), fileBean.getFuncName());
		}
		return floatRateMap;
	}
	
	/**
	 * 是否单估值系统运行
	 * @return
	 */
	public boolean isJoyinSingleValSystem() {
		String returnvalue=CacheUtil.getSysParam("ISSINGLEVAL");
		if(returnvalue==null) {
			return false;
		}else if(returnvalue.equals("01")) {
			return true;
		}else if(returnvalue.equals("02")){
			return false;
		}else {
			return false;
		}
	}
}
