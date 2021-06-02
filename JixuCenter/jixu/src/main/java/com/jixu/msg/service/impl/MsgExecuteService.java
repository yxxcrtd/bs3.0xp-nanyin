package com.jixu.msg.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.func.OutSystemInterface;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.pojo.MsgLog;
import com.jixu.common.pojo.PreVerify;
import com.jixu.common.pojo.Users;
import com.jixu.common.pojo.VerifyConfig;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.MsgTemplate;
import com.jixu.common.type.WorkFlowSteps;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.FinProductTypes;
import com.jixu.common.types.OSType;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.types.TradeSettleLedger;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.FileReader;
import com.jixu.common.util.JsonUtil;
import com.jixu.common.util.ReturnResult;
import com.jixu.common.util.RoundUtil;
import com.jixu.common.valid.ValidatorUtil;
import com.jixu.dao.intf.BkCashAcctDao;
import com.jixu.dao.intf.BkInteSetHisDao;
import com.jixu.dao.intf.BrokerDao;
import com.jixu.dao.intf.CashFlowCfgDao;
import com.jixu.dao.intf.CoaaGroupDao;
import com.jixu.dao.intf.CountryDao;
import com.jixu.dao.intf.ExchangeCfgDao;
import com.jixu.dao.intf.FxCurrencyDao;
import com.jixu.dao.intf.FxRateDao;
import com.jixu.dao.intf.InterfaceSourceDao;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.IssuerDao;
import com.jixu.dao.intf.ManagersDao;
import com.jixu.dao.intf.MsgLogDao;
import com.jixu.dao.intf.PortfolioHdrDao;
import com.jixu.dao.intf.RPSecTypeDao;
import com.jixu.dao.intf.SecAcctDao;
import com.jixu.dao.intf.SecPriceDao;
import com.jixu.dao.intf.TranBasicDao;
import com.jixu.dao.intf.TranBasicFeeDao;
import com.jixu.dao.intf.TranCloDao;
import com.jixu.dao.intf.TranCollateralDao;
import com.jixu.dao.intf.TranFeeDefDao;
import com.jixu.data.service.impl.OutDataCenter;
import com.jixu.framework.dao.intf.HolidayLSDao;
import com.jixu.framework.dao.intf.PreVerifyDao;
import com.jixu.framework.dao.intf.UsersDao;
import com.jixu.framework.dao.intf.VerifyConfigDao;
import com.jixu.framework.msg.BaseMsgDto;
import com.jixu.framework.msg.MsgAssemble;
import com.jixu.framework.msg.MsgError;
import com.jixu.framework.msg.MsgFactory;
import com.jixu.framework.msg.MsgService;
import com.jixu.framework.msg.ParseMessage;
import com.jixu.msg.dto.TranExpenseDto;
import com.jixu.post.service.PostIssueService;
import com.jixu.secTran.SecurityCoreFactory;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import com.orm.obj.BkCashAcct;
import com.orm.obj.BkInteSetHis;
import com.orm.obj.Broker;
import com.orm.obj.CashFlowCfg;
import com.orm.obj.CoaaGroup;
import com.orm.obj.Country;
import com.orm.obj.ExchangeCfg;
import com.orm.obj.FinProductType;
import com.orm.obj.IssueInfo;
import com.orm.obj.Managers;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.RPSecType;
import com.orm.obj.SecAcct;
import com.orm.obj.TranBasic;
import com.orm.obj.TranBasicFee;
import com.orm.obj.TranCollateral;
import com.orm.obj.TranFeeDef;

/** 
 * @ClassName 		MsgExecuteService
 * @author  		Pan Wei 
 * @Date 			2017年5月29日 下午1:04:49 	
 * @Description		[]  
 */

//public abstract class MsgExecuteService implements MsgService{
public abstract class MsgExecuteService<T extends BaseMsgDto> implements MsgService<T>{
	protected static final Logger logger = LoggerFactory.getLogger(MsgExecuteService.class);
	
	@Resource(name="parseMsg")
	private ParseMessage parseMsg;
	
	@Autowired
	protected SecAcctDao secAcctDao;
	
	@Autowired
	protected TranFeeDefDao tranFeeDefDao;
	
	@Autowired
	private BkInteSetHisDao bkInteSetHisDao;
	
	@Autowired
	protected TranCollateralDao tranCollateralDao;
	
	@Autowired
	protected TranBasicFeeDao tranBasicFeeDao;
	
	@Autowired
	private PreVerifyDao preVerifyDao;
	
	@Autowired
	protected FxCurrencyDao fxCurrencyDao;
	
	@Resource(name="commfuncService")
	protected CommFuncService commService;
	
	@Resource(name="msgAssemble")
	protected MsgAssemble msgAssemble;
	
	@Resource(name="postIssues")
	protected PostIssueService postIssue;
	
	@Autowired
	private HolidayLSDao holidayLSDao;
	
	@Autowired
	private MsgLogDao msgLogDao;
	
	@Autowired
	protected CashFlowCfgDao cashFlowCfgDao;
	
	@Autowired
	protected InterfaceSourceDao interfaceSourceDao;
	
	@Autowired
	protected SecPriceDao secPriceDao;
	
	@Autowired
	protected FxRateDao fxRateDao;
	
	@Autowired
	protected CountryDao countryDao;
	
	@Autowired
	protected IssuerDao issuerDao;
	
	@Autowired	
	protected TranCloDao tranCloDao;
	
	@Autowired
	protected RPSecTypeDao rpSecTypeDao;
	
	@Autowired
	protected IssueInfoDao issueInfoDao;
	
	@Autowired
	protected OutDataCenter outDataCenter;
	
	@Autowired
	protected BkCashAcctDao bkCashAcctDao;
	
	@Autowired
	protected ExchangeCfgDao exchangeCfgDao;
	
	@Autowired
	protected TranBasicDao tranBasicDao;
	
	@Autowired
	protected CoaaGroupDao coaaGroupDao;
	
	@Autowired
	protected BrokerDao brokerDao;
	
	@Autowired
	private ManagersDao managersDao;
	
	@Autowired
	private VerifyConfigDao verifyConfigDao;
	
	@Autowired
	protected PortfolioHdrDao portfolioHdrDao;
	
	@Resource(name="outSystemInterface")
	protected OutSystemInterface outSystem;
	
	@Autowired
	private UsersDao usersDao;
	
	protected String add="ADD";
	protected String update="UPDATE";
	protected String delete="DELETE";
	
	public abstract String messageForm();
	
	public String msgFields(){
		String path = MsgExecuteService.class.getResource("/").getPath();
		String msgForm = null;
		if (OSType.detctOS() == OSType.WIN){
			msgForm = path.replace("WEB-INF/classes/", "data" + messageForm() + ".json").replaceFirst("/", "");
		}
		else{
			msgForm = path.replace("WEB-INF/classes/", "data" + messageForm() + ".json");
		}
		
		if(messageForm()==null){
			return null;
		}
		
		HashMap<String, Object> formMap = (HashMap<String, Object>)JsonUtil.stringToMap(FileReader.ReadFile(msgForm));
		if (formMap.isEmpty()){
			return null;
		}
		else{
			@SuppressWarnings("unchecked")
			List<HashMap<String, Object>> flist = (List<HashMap<String, Object>>)formMap.get("forms");
			Iterator<HashMap<String, Object>> iter = flist.iterator();
			String str = "";
			while(iter.hasNext()){
				HashMap<String, Object> hm=iter.next();
				if(hm.containsKey("cat")){
					str = str + hm.get("cat")+",";
				}else if(hm.containsKey("label")){
					str = str + iter.next().get("label")+",";
				}else{
					continue;
				}
			}
			str=str.substring(0, str.length()-1);
			return str;
		}
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void executeMessage(String msg, MsgContainer msgcontainer) {
		parseMsg.convertMessage(msg, msgcontainer);
		if(msgcontainer.isCorrectFlag()){
			String header=msgcontainer.getHeader();
			String template = MsgFactory.getMsgFieldsTemplateHM().get(header);
			if(template==null){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error10,msgcontainer.getHeader()));
			}else{
				int sizeNum = template.split(",").length;
				List<String> fields=msgcontainer.getFields();
				if((fields.size()==2 && fields.get(0).equals("DELETE")) || fields.size()==sizeNum || MsgTemplate.getSubListMsgHM().containsKey(msgcontainer.getHeader())){
					try{
						if(msgcontainer.isNewProcessFlag())
							executeNewProcessMsg(msgcontainer);
						else
							executeMsg(msgcontainer);
					} catch (NumberFormatException ne) {
						msgcontainer.setCorrectFlag(false);
						msgcontainer.setErrorMsg(MsgError.error4);
						return;
					} catch (MsgFieldLackException me) {
						msgcontainer.setCorrectFlag(false);
						msgcontainer.setErrorMsg(MsgError.error6);
						return;
					}
				}else{
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorMsg(MsgError.error3);
					return;
				}
			}
		}
	}
	
	/**
	 * 执行消息
	 * @param msgcontainer 传入消息，容器里的hashmap放入map的消息
	 * @throws NumberFormatException
	 * @throws MsgFieldLackException
	 */
	protected abstract void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException;
	
	protected MsgContainer completeEntity(T dto, MsgContainer container) {
		return null;
	};
	
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void doComplete(T entity, OperationType type) {
		MsgContainer msgcontainer=new MsgContainer();
		completeEntity(entity,msgcontainer);
	}
	
	/**
	 * jixu的消息执行
	 * @param msgcontainer
	 * @throws NumberFormatException
	 * @throws MsgFieldLackException
	 */
	protected void executeMsg(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException{
		//String operationtype=msgcontainer.getFieldsHM().get(MsgFields.operation).toString();
		execute(msgcontainer);
		if(msgcontainer.isCorrectFlag()) {
			//交易迁移到继承MsgTranExecute后，删除这段post逻辑
			Object obj=msgcontainer.getEntityObj();
			if(obj instanceof TranBasic) {
//				TranBasic tb=(TranBasic)obj;
//				//存入数据库后，获取交易主键tanum,提交交易,生成tran,transum表记录
//				if(!operationtype.equalsIgnoreCase(delete)){
//					postIssue.postIssue(msgcontainer.getEntityID(), msgcontainer);
//				}
			}else if(obj instanceof BaseMsgDto){
				T dto=(T)msgcontainer.getEntityObj();
				completeEntity(dto,msgcontainer);
			}
		}
	}
	
	
	//joyin的工作流逻辑
	protected void exeJoyinWFL(T dto, MsgContainer msgcontainer) {
		if(msgcontainer.isCorrectFlag()) {
			if(checkBusinessDataInFlow(dto)) {
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.process_entity_exist));
				return;
			}
			
			if(JixuUserUtil.joyinUserSystemFlag()) {
				HashMap<String, Object> fieldsMap=msgcontainer.getFieldsHM();
				ProcessParameter<T> proParam = new ProcessParameter<>();
				WorkFlowParameter<T> params = new WorkFlowParameter<>();
				// 业务代码
				params.setBusinessCode(dto.getHeader());
				// 业务类型
				params.setBusinessType(dto.getHeader());
				params.setPageId(dto.getHeader());
				params.setElementId(dto.getOperation());
				//设置流程业务说明, entity即传入的dto
				params.setComment(entity -> WorkFlowUtil.concatComment(dto.getHeader()+"-"+dto.getOperation()));
				
				proParam.setWorkFlowParameter(params);
				
				String returnMsg="";
				try {
					returnMsg=start(proParam, dto);
				}catch (Exception e) {
					logger.error("Error", e);
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorMsg(e.getMessage());
					return;
				}
				msgcontainer.setErrorMsg(returnMsg);
			}else {
				completeEntity(dto, msgcontainer);
			}
		}
	}
	
	/**
	 * joyin流程的执行消息
	 * @param msgcontainer
	 * @throws NumberFormatException
	 * @throws MsgFieldLackException
	 */
	protected void executeNewProcessMsg(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException{
		execute(msgcontainer);
		if(msgcontainer.isCorrectFlag()) {
			T dto=(T)msgcontainer.getEntityObj();
			exeJoyinWFL(dto, msgcontainer);
		}
	}
	
	/**
	 * 回撤交易，把已经提交的交易撤回
	 * @param tanum
	 * @param operation
	 * @return
	 */
	protected TranBasic reverseTran(String tanum, String operation, MsgContainer msgcontainer) {
		TranBasic oldTran=tranBasicDao.loadEntity(TranBasic.class,tanum);
		//撤帐
		//joyin不需要撤账，直接修改原有交易，撤账的时候会删除tran等记录
//		postIssue.reverse(tanum, msgcontainer);
		if(msgcontainer.isCorrectFlag()){
			if(operation.equalsIgnoreCase(delete)){
				/*Iterator<TranBasicFee> feeIter= oldTran.getTranBasicFees().iterator();
				while(feeIter.hasNext()){
					interfaceDAO.deleteEntity(feeIter.next());
				}
				
				Iterator<TranCollateral> collateralIter= oldTran.getTranCollaterals().iterator();
				while(collateralIter.hasNext()){
					interfaceDAO.deleteEntity(collateralIter.next());
				}*/
				
				if(!JixuUserUtil.isJoyinTablesFlag()) {
					//joyin用户登陆，不调用银行利息功能
					Iterator<BkInteSetHis> sethis=oldTran.getInteSetHis().iterator();
					while(sethis.hasNext()){
						bkInteSetHisDao.deleteEntity(sethis.next());
					}
				}
				
//				if(oldTran.getPostStatus().equals(PostStatusType.post)){
//					oldTran.setPostStatus(PostStatusType.unpost);
//				}else{
//					oldTran.setPostStatus(PostStatusType.delete);
//				}
				//撤销直接改成delete状态，因为现金股利没有post操作，因此在撤销的时候，用原来的逻辑，是退成unpost(N)状态
				oldTran.setPostStatus(PostStatusType.delete);
				tranBasicDao.updateEntity(oldTran);
			}else if(operation.equalsIgnoreCase(update)){
				Iterator<TranBasicFee> feeIter= oldTran.getTranBasicFees().iterator();
				while(feeIter.hasNext()){
					tranBasicFeeDao.deleteEntity(feeIter.next());
				}
				
				Iterator<TranCollateral> collateralIter= oldTran.getTranCollaterals().iterator();
				while(collateralIter.hasNext()){
					tranCollateralDao.deleteEntity(collateralIter.next());
				}
				if(!JixuUserUtil.isJoyinTablesFlag()) {
					Iterator<BkInteSetHis> sethis=oldTran.getInteSetHis().iterator();
					while(sethis.hasNext()){
						bkInteSetHisDao.deleteEntity(sethis.next());
					}
				}
			}
		}
		
		return oldTran;
	}
	
	protected MsgContainer checkTanum(String operation, Object tanumStr, MsgContainer msgcontainer) throws NumberFormatException{
		
		String tanum="";
		if(tanumStr!=null && !tanumStr.toString().equals("")){
			tanum=tanumStr.toString();
		}
		
		if(operation.equalsIgnoreCase(delete) || operation.equalsIgnoreCase(update)){
			if(tanum==null || tanum.equals("")){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error14, "交易编号: " + tanum));
			}else{
				TranBasic oldTran=tranBasicDao.loadEntity(TranBasic.class,tanum);
				if(oldTran==null){
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, "交易编号: " + tanum));
				}else{
					//撤帐
					postIssue.reverse(tanum, msgcontainer);
					if(msgcontainer.isCorrectFlag()){
						if(operation.equalsIgnoreCase(delete)){
							/*Iterator<TranBasicFee> feeIter= oldTran.getTranBasicFees().iterator();
							while(feeIter.hasNext()){
								interfaceDAO.deleteEntity(feeIter.next());
							}
							
							Iterator<TranCollateral> collateralIter= oldTran.getTranCollaterals().iterator();
							while(collateralIter.hasNext()){
								interfaceDAO.deleteEntity(collateralIter.next());
							}*/
							
							if(!JixuUserUtil.isJoyinTablesFlag()) {
								//joyin用户登陆，不调用银行利息功能
								Iterator<BkInteSetHis> sethis=oldTran.getInteSetHis().iterator();
								while(sethis.hasNext()){
									bkInteSetHisDao.deleteEntity(sethis.next());
								}
							}
							
//							if(oldTran.getPostStatus().equals(PostStatusType.post)){
//								oldTran.setPostStatus(PostStatusType.unpost);
//							}else{
//								oldTran.setPostStatus(PostStatusType.delete);
//							}
							//撤销直接改成delete状态，因为现金股利没有post操作，因此在撤销的时候，用原来的逻辑，是退成unpost(N)状态
							oldTran.setPostStatus(PostStatusType.delete);
							tranBasicDao.updateEntity(oldTran);
						}else if(operation.equalsIgnoreCase(update)){
							Iterator<TranBasicFee> feeIter= oldTran.getTranBasicFees().iterator();
							while(feeIter.hasNext()){
								tranBasicFeeDao.deleteEntity(feeIter.next());
							}
							
							Iterator<TranCollateral> collateralIter= oldTran.getTranCollaterals().iterator();
							while(collateralIter.hasNext()){
								tranCollateralDao.deleteEntity(collateralIter.next());
							}
							
							if(!JixuUserUtil.isJoyinTablesFlag()) {
								//joyin用户登陆，不调用银行利息功能
								Iterator<BkInteSetHis> sethis=oldTran.getInteSetHis().iterator();
								while(sethis.hasNext()){
									bkInteSetHisDao.deleteEntity(sethis.next());
								}
							}
						}
					}
				}
				msgcontainer.setEntityObj(oldTran);
			}
		}else if(operation.equalsIgnoreCase(add)){
			msgcontainer.setEntityID(tanum);
			if(!tanum.equals("")){
				TranBasic oldTran=tranBasicDao.loadEntity(TranBasic.class,tanum);
				if(oldTran != null){
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error7,"交易编号: "+tanum));
				}
			}
		}else{
			msgcontainer.setCorrectFlag(false);
		}
		return msgcontainer;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void executeMessage(MsgContainer msgcontainer) {
		executeMessageReal(msgcontainer);
	}

	@Override
	public void executeMessageNoTx(MsgContainer msgcontainer) {
		executeMessageReal(msgcontainer);
	}

	private void executeMessageReal(MsgContainer msgcontainer) {
		try {
			HashMap<String, Object> msgHM=msgcontainer.getFieldsHM();
			String header=getFieldValue(msgHM, MsgFields.header);
			
			int msglogid=msgcontainer.getObjID();
			
			if(msgcontainer.isVerifyFlag()){
				try{
					if(msgcontainer.isNewProcessFlag())
						executeNewProcessMsg(msgcontainer);
					else
						executeMsg(msgcontainer);
				} catch (NumberFormatException ne) {
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorMsg(MsgError.error4);
					return;
				} catch (MsgFieldLackException me) {
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorMsg(MsgError.error6);
					return;
				}
				MsgLog msglog=msgLogDao.loadEntity(MsgLog.class,msglogid);
				msglog.setStatus(msgcontainer.isCorrectFlag());
				msglog.setErrorinfo(msgcontainer.getErrorMsg());
				msgLogDao.updateEntity(msglog);
			}else{
				//把消息保存入msglog，状态是未执行
				MsgLog errormsg=new MsgLog();
				Object tradeDate=msgHM.get(MsgFields.tradeDate);
				
				int operationStep=0;
				if(msgHM.get(MsgFields.tradeType)!=null){
					int trantype=Integer.valueOf(getFieldValue(msgHM,MsgFields.tradeType));
					if(SecurityCoreFactory.getTranTypeWorkFlowStepMap().containsKey(trantype)){
						operationStep=SecurityCoreFactory.getTranTypeWorkFlowStepMap().get(trantype);
					}
				}else if(msgHM.get(MsgFields.issuer)!=null){
					operationStep=WorkFlowSteps.smf;
					tradeDate=getFieldValue(msgHM, MsgFields.activeDate);
				}
				
				errormsg.setMsg(JSON.toJSONString(msgHM));
				errormsg.setErrorinfo(msgcontainer.getErrorMsg());
				errormsg.setOperationTime(new Date());
				errormsg.setStatus(false);//状态:未执行
				errormsg.setSource(msgcontainer.getSource());
				errormsg.setOperationType(operationStep);
				if(tradeDate!=null){
					tradeDate=tradeDate.toString().replaceAll("-", "");
					errormsg.setLedgerDate(DateUtil.convertStr2Date(tradeDate.toString()));
				}
				errormsg.setIngore(false);
				
				
				if(msgHM.containsKey(MsgFields.portnum)){
					String portNumStr=getFieldValue(msgHM, MsgFields.portnum);
					if(portNumStr!=null){
						String portnum=portNumStr;
						errormsg.setPortfolio(portnum);
					}
				}else if(msgcontainer.getPortid()!=null && !msgcontainer.getPortid().equals("")){
					errormsg.setPortfolio(msgcontainer.getPortid());
				}
				
				msgLogDao.saveEntity(errormsg);
				
				boolean needVerifyFlag=false;
				
				if(JixuUserUtil.jixuUserSystemFlag()) {
					// 到审核表里查询，该用户执行的该消息，是否需要审核
					VerifyConfig vc=verifyConfigDao.loadVerifyConfigByHeader(header+"-"+getFieldValue(msgHM, MsgFields.operation).toUpperCase());
					if(vc!=null){
						PreVerify pv=new PreVerify();
						pv.setOperationDate(new Date());
						pv.setStatus(0);//待审核
						pv.setPortfolio(getFieldValue(msgHM, MsgFields.portnum));
						
						/*HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();//当前登录用户--提交人
						int userID=commService.getUserIDbyRequest(request);*/
						int userID=msgcontainer.getUserID();
						
						//如果是自动执行，userID=-99，则不需要审核
						if(userID==-99){
							try{
								executeMsg(msgcontainer);
							} catch (NumberFormatException ne) {
								msgcontainer.setCorrectFlag(false);
								msgcontainer.setErrorMsg(MsgError.error4);
								return;
							} catch (MsgFieldLackException me) {
								msgcontainer.setCorrectFlag(false);
								msgcontainer.setErrorMsg(MsgError.error6);
								return;
							}
						}
						
						Users u=usersDao.loadUserDetail(userID);
						pv.setSubmitterID(u.getUserid());
						
						pv.setVerifyconfigID(vc.getId());
						pv.setMsglogid(errormsg.getLogid());
						preVerifyDao.saveEntityBean(pv);
						msgcontainer.setCorrectFlag(false);
						msgcontainer.setErrorMsg("该操作需要审核!");
						
						needVerifyFlag=true;
					}
				}
				
				if(!needVerifyFlag){
					//如果需要审核
					try{
						if(msgcontainer.isNewProcessFlag())
							executeNewProcessMsg(msgcontainer);
						else	
							executeMsg(msgcontainer);
				    } catch (NumberFormatException ne) {
 				    	msgcontainer.setCorrectFlag(false);
				    	msgcontainer.setErrorMsg(MsgError.error4);
				    	return;
				    } catch (MsgFieldLackException me) {
				    	msgcontainer.setCorrectFlag(false);
				    	msgcontainer.setErrorMsg(MsgError.error6);
				    	return;
				    }
				}
			   //把所有的交易消息,记录到消息日志表里,以供workflow获取记录数量
				errormsg.setStatus(msgcontainer.isCorrectFlag());
			    errormsg.setErrorinfo(msgcontainer.getErrorMsg());
			    msgLogDao.updateEntity(errormsg);
			}
		} catch (MsgFieldLackException mle) {
			logger.error("ERROR", mle);
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(mle.getMessage());
		}
	}
	
	
	
	protected void genMsgContainer(String header, String fields, MsgContainer msgcontainer){
		msgAssemble.genMsgContainer(header,fields, msgcontainer);
	}
	
	protected String getFieldValue(HashMap<String, Object> fieldsHM, String key) throws MsgFieldLackException{
		Object value=fieldsHM.get(key);
		if(value==null){
			throw new MsgFieldLackException("该消息关键字段没有值提供: "+key);
		}else{
			return value.toString().trim();
		}
	}
	
	protected FinProductType returnFinProductType(String secid,int rpSecTypeID){
		FinProductType fpd = new FinProductType();
		fpd.setFinprod_id(secid);
		fpd.setType_1("01");
		fpd.setType_2(FinProductTypes.finProductType_2HM.get(rpSecTypeID));
		fpd.setType_3(FinProductTypes.finProductType_3HM.get(rpSecTypeID));
		fpd.setBranch(0);
		return fpd;
	}
	
	
	/**
	 * 检查时间格式
	 * @param msgcontainer
	 * @param header
	 * @param dateStr
	 * @return
	 */
	protected Date checkDateFormat(MsgContainer msgcontainer, String header, String dateStr){
		dateStr=dateStr.replaceAll("-", "");
		dateStr=dateStr.replaceAll("/", "");
		Date pdate=DateUtil.convertStr2Date(dateStr);
		if(dateStr.length()!=8 || pdate==null){
			msgcontainer.setErrorMsg(false, MsgError.genErrorMsg(MsgError.error5,header,dateStr));
		}
		return pdate;
	}
	
	/**
	 * 获取股票
	 * @param secid
	 * @param msgcontainer
	 * @return
	 */
	protected IssueInfo getSecurity(String secidArr, MsgContainer msgcontainer){
		String[] secids=secidArr.split("\\|");
		String secid="";
		if(secids.length==3){
			secid=secids[1];
		}else{
			secid=secids[0];
		}
		IssueInfo sec=issueInfoDao.loadSecurity(secid);
		if(sec==null){
			msgcontainer.setErrorMsg(false, MsgError.genErrorMsg(MsgError.error6,"证券代码: " + secid));
		}else{
			msgcontainer.setCorrectFlag(true);
			msgcontainer.setEntityObj(sec);
		}
		return sec;
	}
	
	/**
	 * 获取资产分类
	 * @param typeID
	 * @param msgcontainer
	 * @return
	 */
	protected RPSecType getRPSecType(int typeID, MsgContainer msgcontainer){
		RPSecType stockType = rpSecTypeDao.loadStockType(typeID);
		if (stockType == null) {
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, "RPSecType: " + typeID));
		}else{
			msgcontainer.setEntityObj(stockType);
		}
		return stockType;
	}
	
	/**
	 * 获取行业分类
	 * @param typeID
	 * @param msgcontainer
	 * @return
	 */
	protected RPSecType getRPSecType2(int typeID, MsgContainer msgcontainer){
		RPSecType stockType = rpSecTypeDao.loadStockType2(typeID);
		if (stockType == null) {
			msgcontainer.setErrorMsg(false, MsgError.genErrorMsg(MsgError.error6,"RPSecType2: " + typeID));
		}else{
			msgcontainer.setEntityObj(stockType);
		}
		return stockType;
	}

	/**
	 * 获取货币
	 * @param curID
	 * @param msgcontainer
	 * @return
	 */
	protected boolean getCurrency(String curID, MsgContainer msgcontainer){
		boolean exist=false;
		String currency = commService.loadFxCurrencyName(curID);
		if (currency==null || currency.equals("")) {
			msgcontainer.setErrorMsg(false, MsgError.genErrorMsg(MsgError.error6, "货币: " + curID));
		}else{
			exist=true;
			msgcontainer.setEntityObj(currency);
		}
		return exist;
	}
	
	/**
	 * 获取国家
	 * @param countryID
	 * @param msgcontainer
	 * @return
	 */
//	@Cacheable(value="portDataCache", key="'CountryID'+#countryID")
	protected Country getCountry(String countryID, MsgContainer msgcontainer){
		Country country = countryDao.loadCountry(countryID);
		if (country == null) {
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, "国家: " + countryID));
		}else{
			msgcontainer.setEntityObj(country);
		}
		return country;
	}
	
	/**
	 * 获取证券交易所
	 * @param exchangeID
	 * @param msgcontainer
	 * @return
	 */
	protected ExchangeCfg getExchange(String exchangeID, MsgContainer msgcontainer){
		ExchangeCfg exchange = null;
		if(JixuUserUtil.isJoyinTablesFlag())
			exchange=outSystem.loadExchangeCfg(exchangeID);
		else
			exchange=exchangeCfgDao.loadExchange(exchangeID);
		if (exchange == null) {
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, "交易所: " + exchangeID));
		}else{
			msgcontainer.setEntityObj(exchange);
		}
		return exchange;
	}
	
	/**
	 * 获取CoaGroup
	 * @param coaGroupID
	 * @param msgcontainer
	 * @return
	 */
	protected CoaaGroup getCoaaGroup(int coaGroupID, MsgContainer msgcontainer){
		CoaaGroup coaGroup = coaaGroupDao.loadCoaaGroup(coaGroupID);
		if (coaGroup == null) {
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, "总账组(CoaGroup): " + coaGroupID));
		}else{
			msgcontainer.setEntityObj(coaGroup);
		}
		return coaGroup;
	}
	
	/**
	 * 获取数据源
	 * @param sourceID
	 * @param msgcontainer
	 * @return
	 */
	protected InterfaceSource getInterfaceSource(int sourceID, MsgContainer msgcontainer){
		InterfaceSource source = interfaceSourceDao.loadSource(sourceID);
		if (source == null) {
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, "数据源: " +  sourceID));
		}else{
			msgcontainer.setSource(source);
			msgcontainer.setEntityObj(source);
		}
		return source;
	}
	
	/**
	 * 获取券商
	 * @param brokerID
	 * @param msgcontainer
	 * @return
	 */
	protected Broker getBroker(String brokerID, MsgContainer msgcontainer){
		Broker broker =brokerDao.loadBroker(brokerID);
		if (broker == null) {
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(false, MsgError.genErrorMsg(MsgError.error6, "券商: "+brokerID));
		}else{
			msgcontainer.setEntityObj(broker);
		}
		return broker;
	}
	
	/**
	 * 获取现金帐号
	 * @param cashacct
	 * @param msgcontainer
	 * @return
	 */
	protected BkCashAcct getCashAcct(int cashacct, String portnum, MsgContainer msgcontainer){
		if(cashacct==0){
			msgcontainer.setErrorMsg(false, MsgError.genErrorMsg(MsgError.error6, msgcontainer.getHeader(), "银行账户: " + cashacct));
			return null;
		}
		return getCashAcct(String.valueOf(cashacct), portnum, msgcontainer);
	}
	
	/**
	 * 获取现金帐号
	 * @param cashacct
	 * @param msgcontainer
	 * @return
	 */
	protected BkCashAcct getCashAcct(String cashacct, String portnum, MsgContainer msgcontainer){
		if(cashacct.equals("0")){
			msgcontainer.setErrorMsg(false, MsgError.genErrorMsg(MsgError.error6, "投资组合: " + portnum + "    银行账户: " + cashacct));
			return null;
		}
		BkCashAcct acct = bkCashAcctDao.loadBankCashAcctByExAcct(cashacct, portnum);
		if (acct == null) {
			msgcontainer.setErrorMsg(false, MsgError.genErrorMsg(MsgError.error6, "  投资组合: " + portnum + "    银行账户: " + cashacct));
		}else{
			msgcontainer.setEntityObj(acct);
		}
		return acct;
	}
	
	/**
	 * 获取证券帐号
	 * @param secacctid
	 * @param msgcontainer
	 * @return
	 */
	protected SecAcct getSecAcct(String portnum, String secacctid, MsgContainer msgcontainer){
		if(secacctid==null || secacctid.equals("") || secacctid.equals("0")){
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.error6);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, "证券账户: " + secacctid));
			return null;
		}
		
		SecAcct secacct = null;
		if(JixuUserUtil.isJoyinTablesFlag() && !commService.isJoyinSingleValSystem()) {
			int cashnum=secAcctDao.loadDefaultCashAcctNameBySecAcctFromJoyin(portnum, secacctid);
			if(cashnum!=0) {
				secacct=new SecAcct(secacctid);
				secacct.setDefaultCashAcct(cashnum);
			}
		}
		
		if(secacct==null) {
			secacct = secAcctDao.loadSecAcct(secacctid);
		}
	
		if (secacct == null) {
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.error6);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, "证券账户: " + secacctid));
		}else{
			secacct.getDefaultCashAcct();
			msgcontainer.setEntityObj(secacct);
		}
		return secacct;
	}
	
	/**
	 * 获取基金经理组
	 * @param managerCode
	 * @param msgcontainer
	 * @return
	 */
	protected Managers getManagers(int managerGroupID, int managerCode, MsgContainer msgcontainer){
		Managers manager = managersDao.loadInvestManager(managerGroupID, managerCode);
		if (manager == null) {
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg("经理组"+managerGroupID+"不存在基金经理"+managerCode);
		}else{
			msgcontainer.setEntityObj(manager);
		}
		return manager;
	}
	
	/**
	 * 获取投资组合
	 * @param portnum
	 * @param msgcontainer
	 * @return
	 */
	protected PortfolioHdr getPortfolio(String portnum, MsgContainer msgcontainer){
		PortfolioHdr portfolio= commService.loadPortfolio(portnum);
		if (portfolio == null || portfolio.getId().equals("")) {
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, "投资组合: " + portnum));
		}else{
			msgcontainer.setEntityObj(portfolio);
		}
		return portfolio;
	}
	
	protected List<TranBasicFee> genTranBasicFee(MsgContainer msgcontainer, String feeMsg){
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("expenses", feeMsg);
		List<TranBasicFee> feeList=new ArrayList<TranBasicFee>();
		try {
			feeList=genTranBasicFee(msgcontainer, map);
		}catch(MsgFieldLackException me) {
			msgcontainer.setErrorMsg(false, me.getMessage());
			logger.error("Error", me);
		}
		return feeList;
	}
	
	protected List<TranBasicFee> genTranBasicFeeDto(MsgContainer msgcontainer, String portnum, List<TranExpenseDto> feeDto){
		List<TranBasicFee> feeList=new ArrayList<TranBasicFee>();
		try {
			feeList=genTranBasicFeeByDto(msgcontainer, portnum, feeDto);
		}catch(MsgFieldLackException me) {
			msgcontainer.setErrorMsg(false, me.getMessage());
			logger.error("Error", me);
		}
		return feeList;
	}
	
	/**
	 * 生成费用的map
	 * @param fieldsHM
	 * @return
	 */
	protected List<TranBasicFee> genTranBasicFee(MsgContainer msgcontainer, HashMap<String, Object> fieldsHM) throws MsgFieldLackException{
		Object feemsg=fieldsHM.get("expenses");
		List<TranBasicFee> feeList=new ArrayList<TranBasicFee>();
		if(feemsg!=null){
			List<HashMap> mapList = (ArrayList<HashMap>) JSON.parseArray(feemsg.toString(), HashMap.class);
			for(HashMap map:mapList){
				TranBasicFee fee=new TranBasicFee();
				if(map.containsKey("cashAmount"))
					fee.setAmtexclfromcash(Double.valueOf(getFieldValue(map, "cashAmount")));
				fee.setCurrID(getFieldValue(map, "feeTradeCurrency"));
				fee.setDeferredFeeamt(0);
				fee.setTotalFeeamt(Double.valueOf(getFieldValue(map, "totalAmount")));
				fee.setExpenseFeeamt(Double.valueOf(getFieldValue(map, "expenseAmount")));
				fee.setCapitalisedfeeamt(RoundUtil.roundMoney2Dec(fee.getTotalFeeamt()-fee.getExpenseFeeamt()));
				TranFeeDef tranFeeType = tranFeeDefDao.loadTranFeeDef(Integer.valueOf(getFieldValue(map, "expense")));
				fee.setFeeTypeID(tranFeeType);
				fee.setFxrate(Double.valueOf(getFieldValue(map, "fxRate")));
				fee.setBkCashAcct(new BkCashAcct(Integer.valueOf(getFieldValue(map, "bankAccount"))));
				Date settleDate=null;
				if(JixuUserUtil.isJoyinTablesFlag()) {
					settleDate=DateUtil.convertStr2Date("29991231");
				}else {
					if(map.containsKey("expenseSettleDate")) 
						settleDate=checkDateFormat(msgcontainer, "basicFee", getFieldValue(map, "expenseSettleDate").replaceAll("-", ""));
					else
						settleDate=checkDateFormat(msgcontainer, "basicFee", getFieldValue(map, "settleDate").replaceAll("-", ""));
				}
				fee.setSettleDate(settleDate);
				if(map.containsKey("rpType"))
					fee.setRecOrPay(Integer.valueOf(getFieldValue(map, "rpType")));
				else
					//普通股页面费用 不显示应收应付金额和类型
					fee.setRecOrPay(DBCR.pay);
				feeList.add(fee);
			}
		}
		return feeList;
	}
	
	/**
	 * 生成费用的map
	 * @param fieldsHM
	 * @return
	 */
	protected List<TranBasicFee> genTranBasicFeeByDto(MsgContainer msgcontainer, String portnum, List<TranExpenseDto> expenseDtoList) throws MsgFieldLackException{
		List<TranBasicFee> feeList=new ArrayList<TranBasicFee>();
		if(expenseDtoList!=null && expenseDtoList.size()>0){
			for(TranExpenseDto dto:expenseDtoList){
				TranBasicFee fee=new TranBasicFee();
				//费用过度阶段
				//CashAmount==0	随交易支付，证券清算款，和交易凭证一起
				//CashAmount!=1	不随交易支付
				if(dto.getCashAmount()==0) {
					//随交易支付部分=TotalFeeamt-Amtexclfromcash
					fee.setAmtexclfromcash(dto.getCashAmount());
				}else {
					//不随交易支付部分 Amtexclfromcash
					fee.setAmtexclfromcash(dto.getTotalAmount());
				}
				fee.setCurrID(dto.getFeeTradeCurrency());
				fee.setDeferredFeeamt(0);
				fee.setTotalFeeamt(dto.getTotalAmount());
				fee.setExpenseFeeamt(dto.getExpenseAmount());
				fee.setCapitalisedfeeamt(RoundUtil.roundMoney2Dec(fee.getTotalFeeamt()-fee.getExpenseFeeamt()));
				TranFeeDef tranFeeType = tranFeeDefDao.loadTranFeeDef(dto.getExpense());
				fee.setFeeTypeID(tranFeeType);
				fee.setFxrate(dto.getFxRate());
				
				BkCashAcct cashacct=bkCashAcctDao.loadBankCashAcctByExAcct(dto.getBankAccount(), portnum);
				fee.setBkCashAcct(cashacct);
				Date settleDate=null;
				if(JixuUserUtil.isJoyinTablesFlag()) {
					settleDate=DateUtil.convertStr2Date("29991231");
				}else {
					settleDate=checkDateFormat(msgcontainer, "basicFee", dto.getExpenseSettleDate().replaceAll("-", ""));
				}
				fee.setSettleDate(settleDate);
				//if(map.containsKey("rpType"))
				//fee.setRecOrPay(Integer.valueOf(getFieldValue(map, "rpType")));
				//普通股页面费用 不显示应收应付金额和类型
				fee.setRecOrPay(DBCR.pay);
				feeList.add(fee);
			}
		}
		return feeList;
	}
	
	
	/**
	 * 生成交易的抵押物消息
	 * @param fieldsHM
	 * @return
	 */
	protected List<TranCollateral> genTranBasicCollaterals(MsgContainer msgcontainer, HashMap<String, Object> fieldsHM, String portnum) throws MsgFieldLackException{
		Object colMsg=fieldsHM.get("collaterals");
		List<TranCollateral> colList=new ArrayList<TranCollateral>();
		if(colMsg!=null){
			List<HashMap> mapList = (ArrayList<HashMap>) JSON.parseArray(colMsg.toString(), HashMap.class);
			for(HashMap map:mapList){
				TranCollateral collateral=new TranCollateral();
				String secacctid=getFieldValue(map, MsgFields.secAcct);
				SecAcct secacct=getSecAcct(portnum, secacctid, msgcontainer);
				if(!msgcontainer.isCorrectFlag()){
					return null;
				}
				
				String[] secids=getFieldValue(map, MsgFields.secid).split("\\|");
				String secid="";
				if(secids.length==3){
					secid=secids[1];
				}else{
					secid=secids[0];
				}
				IssueInfo sec=getSecurity(secid, msgcontainer);
				if(!msgcontainer.isCorrectFlag()){
					return null;
				}
				
				collateral.setCusip(sec);
				collateral.setAmount(Double.valueOf(getFieldValue(map, MsgFields.shares)));
				collateral.setLocation(secacct.getAcctID());
				
				colList.add(collateral);
			}
		}
		return colList;
	}
	
	/**
	 * 获取投资组合
	 * @param portnum
	 * @param msgcontainer
	 * @return
	 */
	protected CashFlowCfg getCashFlowCfgo(String cashflowCfgCode, MsgContainer msgcontainer){
		CashFlowCfg cfg=null;
		if(cashflowCfgCode!=null && !cashflowCfgCode.equals("")){
			cfg=cashFlowCfgDao.loadEntity(CashFlowCfg.class, cashflowCfgCode);
		}
		return cfg;
	}
	
	protected Date getLedgerDate(Date tradeDate, Date settleDate, PortfolioHdr portfolio) {
		if(portfolio.getTradeSettleLedger()==TradeSettleLedger.NO_AFTER_CLOSE_LEDGER) {
			return tradeDate;
		}else {
			Date ledgerDate=null;
			switch(portfolio.getTradeSettleLedger()){
			case TradeSettleLedger.TradeDate:
				ledgerDate=tradeDate;
				break;
			case TradeSettleLedger.SettleDate:
				ledgerDate=settleDate;
				break;
			case TradeSettleLedger.TradeDate_1:
				List<Date> holidayList=holidayLSDao.loadHolidayList(portfolio.getHolidaySD());
				ledgerDate=DateUtil.getNextBusinessDay(tradeDate, holidayList);
				break;
			default:
				ledgerDate=tradeDate;
				break;
			}
			
			if(!ledgerDate.after(portfolio.getLastTranClose())){
				ledgerDate=portfolio.getNextTranClose();
			}
			return ledgerDate;
		}
	}
	
	/**
	 * 返回是否通过前端校验
	 * @param container
	 * @param dto
	 */
	protected void validateDto(MsgContainer container, BaseMsgDto dto){
		Map<String, StringBuffer> resultmap=ValidatorUtil.validate(dto);
		//获取前端的错误信息返回到前端页面
		if(resultmap!=null && resultmap.size()>0){
			StringBuffer sb=new StringBuffer();
			for(Map.Entry<String, StringBuffer> entry : resultmap.entrySet()){
				sb.append(" && ");
				sb.append(entry.getValue());
			}
			
			String message=sb.toString();
			message=message.replaceFirst(" && ", "");
			
			container.setCorrectFlag(false);
			container.setErrorMsg(message);
		}else {
			container.setEntityObj(dto);
		}
	}
	
	@Override
	public void doUpdate(T entity) {
		// TODO Auto-generated method stub
		MsgService.super.doUpdate(entity);
	}
	
	@Override
	public void doDelete(String workflowId) {
		// TODO Auto-generated method stub
		MsgService.super.doDelete(workflowId);
	}
	
	@Override
	public void doInsert(T entity, OperationType type) {
		// TODO Auto-generated method stub
		MsgService.super.doInsert(entity, type);
	}

	@Override
	public void doBack(T entity) {
		// TODO Auto-generated method stub
		MsgService.super.doBack(entity);
	}

	@Override
	public void doRevoke(T entity) {
		// TODO Auto-generated method stub
		MsgService.super.doRevoke(entity);
	}
	
	@Override
	public String generateTitle(T entity, OperationType type) {
		return MsgService.super.generateTitle(entity, type);
	}
}
