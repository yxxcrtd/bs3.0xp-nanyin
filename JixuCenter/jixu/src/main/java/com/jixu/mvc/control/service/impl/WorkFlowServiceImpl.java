package com.jixu.mvc.control.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.common.func.CommFuncService;
import com.jixu.common.pojo.MsgLog;
import com.jixu.common.pojo.UserOpRuleMap;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.WorkFlowSteps;
import com.jixu.common.types.WorkFlowStatus;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.ReturnResult;
import com.jixu.dao.intf.JobsHisDao;
import com.jixu.dao.intf.MsgLogDao;
import com.jixu.dao.intf.PortfolioInterfaceMapDao;
import com.jixu.dao.intf.WorkFlowFactoryDao;
import com.jixu.framework.dao.intf.UserOpRuleMapDao;
import com.jixu.job.obj.WorkFlowStepStatus;
import com.jixu.mvc.control.model.WfFailedData;
import com.jixu.mvc.control.model.WfStatus;
import com.jixu.mvc.control.service.WorkFlowService;
import com.orm.obj.JobsHis;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.PortfolioInterfaceMap;
import com.orm.obj.WorkFlowFactory;

/** 
 * @ClassName 		WorkFlowServiceImpl
 * @author  		Pan Wei 
 * @Date 			2016年9月18日 上午10:47:34 	
 * @Description		[]  
 */

@Service("workFlowService")
public class WorkFlowServiceImpl implements WorkFlowService{
	
	//private static Logger logger = Logger.getLogger(WorkFlowServiceImpl.class);
	
	@Resource(name="commfuncService")
	private CommFuncService commService;
	
	@Autowired
	private WorkFlowFactoryDao workFlowFactoryDao;
	
	@Autowired
	private UserOpRuleMapDao userOpRuleMapDao;
	
	@Autowired
	private PortfolioInterfaceMapDao portfolioInterfaceMapDao;
	
	@Autowired
	private JobsHisDao jobsHisDao;
	
	@Autowired
	private MsgLogDao msgLogDao;
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public HashMap<String, Object> fetchCurWorkFlow(String[] portfolios, Date ledgerDate, int userid, ResultContainer container) {
		//0 行情  1 交易 2 做账
		HashMap<String, Object> workflowHM=new HashMap<String, Object>();
		HashMap<Integer, Integer> baseStepHM=null;
		
		String rulesStr="";
		List<Integer> userRuleList=new ArrayList<Integer>();
		
		if(JixuUserUtil.jixuUserSystemFlag()){
			List<UserOpRuleMap> userRules=userOpRuleMapDao.loadUserRoles(userid);
			for(UserOpRuleMap rule:userRules){
				rulesStr=rulesStr+rule.getOperationRole();
				userRuleList.add(rule.getOperationRole());
			}
		}else{
			//如果是外部系统的用户权限控制
			HashMap<Integer, String> opertionSteps=WorkFlowSteps.operationRuleHM;
			for(Map.Entry<Integer, String> entry: opertionSteps.entrySet()){
				rulesStr=rulesStr+entry.getKey();
				userRuleList.add(entry.getKey());
			}
		}
		
		if(userRuleList.size()>0){
			List<String> portfolioList=new ArrayList<String>();
			for(String port:portfolios){
				portfolioList.add(port);
			}
			
			for(String port:portfolios){
				List<WorkFlowFactory> wffList=workFlowFactoryDao.loadWorkFlow(port, ledgerDate);
				if(wffList.size()==0 && rulesStr.contains("0") ){
					//如果数据库没有记录，新建workflow
					Date startTime=new Date();
					HashMap<Integer, String> wfStepHM=WorkFlowSteps.operations;
					Iterator<Entry<Integer,String>> wfST=wfStepHM.entrySet().iterator();
					while(wfST.hasNext()){
						Entry<Integer,String> entry=wfST.next();
						WorkFlowFactory wf1=new WorkFlowFactory();
						wf1.setPortfolio(new PortfolioHdr(port));
						wf1.setLedgerDate(ledgerDate);
						wf1.setWfStep(entry.getKey());
						wf1.setStartTime(startTime);
						wf1.setSuccessNum(0);
						wf1.setFailedNum(0);
						wf1.setEndTime(startTime);
						wf1.setStatus(WorkFlowStatus.preStart);
						workFlowFactoryDao.saveEntity(wf1);
						wffList.add(wf1);
					}
				}else if(wffList.size()==0 && !rulesStr.contains("0")){
					container.setCorrectFlag(false);
					container.setErrorCode(ReturnResult.wf_processUnCreate);
					container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.wf_processUnCreate));
					return null;
				}
				
				//展示工作流
				HashMap<Integer, WorkFlowFactory> wfHM=new HashMap<Integer, WorkFlowFactory>();
				for(WorkFlowFactory wf:wffList){
					wf.setSuccessNum(0);	//to-do pending
					wf.setFailedNum(0);
					wfHM.put(wf.getWfStep(), wf);
				}

				
				//比较几个投资组合的状态是否一致
				if(baseStepHM==null){
					baseStepHM=new HashMap<Integer, Integer>();
					baseStepHM.put(WorkFlowSteps.fxRate, wfHM.get(WorkFlowSteps.fxRate).getStatus());
					baseStepHM.put(WorkFlowSteps.price, wfHM.get(WorkFlowSteps.price).getStatus());
					baseStepHM.put(WorkFlowSteps.smf, wfHM.get(WorkFlowSteps.smf).getStatus());
					baseStepHM.put(WorkFlowSteps.corpAction, wfHM.get(WorkFlowSteps.corpAction).getStatus());
					baseStepHM.put(WorkFlowSteps.tran, wfHM.get(WorkFlowSteps.tran).getStatus());
					baseStepHM.put(WorkFlowSteps.fxTrade, wfHM.get(WorkFlowSteps.fxTrade).getStatus());
					baseStepHM.put(WorkFlowSteps.ta, wfHM.get(WorkFlowSteps.ta).getStatus());
					baseStepHM.put(WorkFlowSteps.cash, wfHM.get(WorkFlowSteps.cash).getStatus());
					baseStepHM.put(WorkFlowSteps.autojob, wfHM.get(WorkFlowSteps.autojob).getStatus());
					baseStepHM.put(WorkFlowSteps.nav, wfHM.get(WorkFlowSteps.nav).getStatus());
					baseStepHM.put(WorkFlowSteps.generalClose, wfHM.get(WorkFlowSteps.generalClose).getStatus());
					baseStepHM.put(WorkFlowSteps.daily, wfHM.get(WorkFlowSteps.daily).getStatus());
					baseStepHM.put(WorkFlowSteps.monthly, wfHM.get(WorkFlowSteps.monthly).getStatus());
					baseStepHM.put(WorkFlowSteps.dailyClose, wfHM.get(WorkFlowSteps.dailyClose).getStatus());
				}else{
					baseStepHM.put(WorkFlowSteps.fxRate, wfHM.get(WorkFlowSteps.fxRate).getStatus()>baseStepHM.get(WorkFlowSteps.fxRate)?wfHM.get(WorkFlowSteps.fxRate).getStatus():baseStepHM.get(WorkFlowSteps.fxRate));
					baseStepHM.put(WorkFlowSteps.price, wfHM.get(WorkFlowSteps.price).getStatus()>baseStepHM.get(WorkFlowSteps.price)?wfHM.get(WorkFlowSteps.price).getStatus():baseStepHM.get(WorkFlowSteps.price));
					baseStepHM.put(WorkFlowSteps.smf, wfHM.get(WorkFlowSteps.smf).getStatus()>baseStepHM.get(WorkFlowSteps.smf)?wfHM.get(WorkFlowSteps.smf).getStatus():baseStepHM.get(WorkFlowSteps.smf));
					baseStepHM.put(WorkFlowSteps.corpAction, wfHM.get(WorkFlowSteps.corpAction).getStatus()>baseStepHM.get(WorkFlowSteps.corpAction)?wfHM.get(WorkFlowSteps.corpAction).getStatus():baseStepHM.get(WorkFlowSteps.corpAction));
					baseStepHM.put(WorkFlowSteps.tran, wfHM.get(WorkFlowSteps.tran).getStatus()>baseStepHM.get(WorkFlowSteps.tran)?wfHM.get(WorkFlowSteps.tran).getStatus():baseStepHM.get(WorkFlowSteps.tran));
					baseStepHM.put(WorkFlowSteps.fxTrade, wfHM.get(WorkFlowSteps.fxTrade).getStatus()>baseStepHM.get(WorkFlowSteps.fxTrade)?wfHM.get(WorkFlowSteps.fxTrade).getStatus():baseStepHM.get(WorkFlowSteps.fxTrade));
					baseStepHM.put(WorkFlowSteps.ta, wfHM.get(WorkFlowSteps.ta).getStatus()>baseStepHM.get(WorkFlowSteps.ta)?wfHM.get(WorkFlowSteps.ta).getStatus():baseStepHM.get(WorkFlowSteps.ta));
					baseStepHM.put(WorkFlowSteps.cash, wfHM.get(WorkFlowSteps.cash).getStatus()>baseStepHM.get(WorkFlowSteps.cash)?wfHM.get(WorkFlowSteps.cash).getStatus():baseStepHM.get(WorkFlowSteps.cash));
					baseStepHM.put(WorkFlowSteps.autojob, wfHM.get(WorkFlowSteps.autojob).getStatus()>baseStepHM.get(WorkFlowSteps.autojob)?wfHM.get(WorkFlowSteps.autojob).getStatus():baseStepHM.get(WorkFlowSteps.autojob));
					baseStepHM.put(WorkFlowSteps.nav, wfHM.get(WorkFlowSteps.nav).getStatus()>baseStepHM.get(WorkFlowSteps.nav)?wfHM.get(WorkFlowSteps.nav).getStatus():baseStepHM.get(WorkFlowSteps.nav));
					baseStepHM.put(WorkFlowSteps.generalClose, wfHM.get(WorkFlowSteps.generalClose).getStatus()>baseStepHM.get(WorkFlowSteps.generalClose)?wfHM.get(WorkFlowSteps.generalClose).getStatus():baseStepHM.get(WorkFlowSteps.generalClose));
					baseStepHM.put(WorkFlowSteps.daily, wfHM.get(WorkFlowSteps.daily).getStatus()>baseStepHM.get(WorkFlowSteps.daily)?wfHM.get(WorkFlowSteps.daily).getStatus():baseStepHM.get(WorkFlowSteps.daily));
					baseStepHM.put(WorkFlowSteps.monthly, wfHM.get(WorkFlowSteps.monthly).getStatus()>baseStepHM.get(WorkFlowSteps.monthly)?wfHM.get(WorkFlowSteps.monthly).getStatus():baseStepHM.get(WorkFlowSteps.monthly));
					baseStepHM.put(WorkFlowSteps.dailyClose, wfHM.get(WorkFlowSteps.dailyClose).getStatus()>baseStepHM.get(WorkFlowSteps.dailyClose)?wfHM.get(WorkFlowSteps.dailyClose).getStatus():baseStepHM.get(WorkFlowSteps.dailyClose));
				}
				
				
				workflowHM.put("oprRole", userRuleList);
				workflowHM.put("portfolios", portfolioList);
				
				HashMap<String, HashMap<String, Object>> marketHM=new HashMap<String, HashMap<String, Object>>();
				List<WfStatus> wfStatusList=msgLogDao.loadMsgLogMarketTotalNum(portfolios, ledgerDate);
				for(WfStatus wfstatus:wfStatusList){
					if(wfstatus.isStatus()){
						wfHM.get(wfstatus.getOperation()).setSuccessNum(wfstatus.getTotalnum());
					}else{
						wfHM.get(wfstatus.getOperation()).setFailedNum(wfstatus.getTotalnum());
					}
				}
				
				wfStatusList=msgLogDao.loadMsgLoTradeTotalNum(portfolios, ledgerDate);
				for(WfStatus wfstatus:wfStatusList){
					if(wfstatus.isStatus()){
						wfHM.get(wfstatus.getOperation()).setSuccessNum(wfstatus.getTotalnum());
					}else{
						wfHM.get(wfstatus.getOperation()).setFailedNum(wfstatus.getTotalnum());
					}
				}
				
				if(!JixuUserUtil.isJoyinTablesFlag()) {
					wfStatusList=jobsHisDao.loadJobsTotalNum(portfolios, ledgerDate);
					for(WfStatus wfstatus:wfStatusList){
						if(wfstatus.isStatus()){
							wfHM.get(wfstatus.getOperation()).setSuccessNum(wfstatus.getTotalnum());
						}else{
							wfHM.get(wfstatus.getOperation()).setFailedNum(wfstatus.getTotalnum());
						}
					}
				}

				marketHM.put("price", genStepHM(wfHM.get(WorkFlowSteps.price), baseStepHM.get(WorkFlowSteps.price)));
				marketHM.put("ex", genStepHM(wfHM.get(WorkFlowSteps.fxRate), baseStepHM.get(WorkFlowSteps.fxRate)));
				marketHM.put("smf", genStepHM(wfHM.get(WorkFlowSteps.smf), baseStepHM.get(WorkFlowSteps.smf)));
				//市场HM
				workflowHM.put("market", marketHM);
			
				HashMap<String, HashMap<String, Object>> tradeHM=new HashMap<String, HashMap<String, Object>>();
				
				//int shareFailedNum=workFlowDAO.loadMsgLogMarketTotalNum(portfolios,WorkFlowSteps.ta,ledgerDate,false);
				tradeHM.put("shareholder", genStepHM(wfHM.get(WorkFlowSteps.ta), baseStepHM.get(WorkFlowSteps.ta)));
				tradeHM.put("cash", genStepHM(wfHM.get(WorkFlowSteps.cash), baseStepHM.get(WorkFlowSteps.cash)));
				tradeHM.put("ca", genStepHM(wfHM.get(WorkFlowSteps.corpAction), baseStepHM.get(WorkFlowSteps.corpAction)));
				tradeHM.put("fx", genStepHM(wfHM.get(WorkFlowSteps.fxTrade), baseStepHM.get(WorkFlowSteps.fxTrade)));
				tradeHM.put("portTrade", genStepHM(wfHM.get(WorkFlowSteps.tran), baseStepHM.get(WorkFlowSteps.tran)));
				//市场HM
				workflowHM.put("trade", tradeHM);
				
				HashMap<String, HashMap<String, Object>> jobHM=new HashMap<String, HashMap<String, Object>>();
				jobHM.put("auto", genStepHM(wfHM.get(WorkFlowSteps.autojob), baseStepHM.get(WorkFlowSteps.autojob)));
				jobHM.put("generalClose", genStepHM(wfHM.get(WorkFlowSteps.generalClose), baseStepHM.get(WorkFlowSteps.generalClose)));
				jobHM.put("daily", genStepHM(wfHM.get(WorkFlowSteps.daily), baseStepHM.get(WorkFlowSteps.daily)));
				jobHM.put("monthly", genStepHM(wfHM.get(WorkFlowSteps.monthly), baseStepHM.get(WorkFlowSteps.monthly)));
				jobHM.put("nav", genStepHM(wfHM.get(WorkFlowSteps.nav), baseStepHM.get(WorkFlowSteps.nav)));
				jobHM.put("dailyClose", genStepHM(wfHM.get(WorkFlowSteps.dailyClose), baseStepHM.get(WorkFlowSteps.dailyClose)));
				//jobHM
				workflowHM.put("job", jobHM);
			}
		}
		
		return workflowHM;
	}
	
	public HashMap<String, Object> genStepHM(WorkFlowFactory wf, int status){
		HashMap<String, Object> stepHM=new HashMap<String, Object>();
		stepHM.put("start", DateUtil.convertD2YYYY_MM_DDHHMMSS(wf.getStartTime()));
		stepHM.put("success", wf.getSuccessNum());
		stepHM.put("fail", wf.getFailedNum());
		stepHM.put("end", DateUtil.convertD2YYYY_MM_DDHHMMSS(wf.getEndTime()));
		stepHM.put("opr", status);
		return stepHM;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void updateWorkFlowStep(int step, List<String> portList, Date ledgerDate, int status) {
		Date curTime=new Date();
		String[] portfolios=new String[portList.size()];
		for(int i=0;i<portList.size();i++){
			portfolios[i]=portList.get(i).toString();
		}
		
		List<WorkFlowFactory> wfFactoryList=workFlowFactoryDao.loadWorkFlowWithStep(portfolios, ledgerDate, step);
		for(WorkFlowFactory wf:wfFactoryList){
			if(wf.getStatus()==WorkFlowStatus.preStart && status==WorkFlowStatus.process){
				wf.setStartTime(curTime);
			}else if(wf.getStatus()==WorkFlowStatus.process && status==WorkFlowStatus.finish){
				wf.setEndTime(curTime);
			}
			wf.setStatus(status);
			workFlowFactoryDao.updateEntity(wf);
			//exeAutoExp(wf.getPortfolio().getId(), step, status, ledgerDate);
		}
	}
	
//	private void exeAutoExp(String portnum, int step, int status, Date ledgerDate){
//		HashMap<Integer, Integer> expHM=getAutoExeConfigMap(portnum);
//		if(status==WorkFlowStatus.finish && expHM.containsKey(step)){
//			String[] params={String.valueOf(portnum), DateUtil.convertD2YYYY_MM_DD(ledgerDate)};
//			exeExport.executeExport(expHM.get(step), params);
//		}
//	}
	
	/**
	 * 获取HashMap<执行步骤,自动执行的导出接口>
	 * @param portnum
	 * @param step
	 * @param status
	 * @return
	 */
	@Cacheable(value="portDataCache", key="'ExportPortNum_'+#portnum")
	private HashMap<Integer, Integer> getAutoExeConfigMap(String portnum){
		List<PortfolioInterfaceMap> portInterfaceMapList=portfolioInterfaceMapDao.loadAutoExportList(portnum);
		HashMap<Integer, Integer> sourceHM=new HashMap<Integer, Integer>();
		for(PortfolioInterfaceMap piMap:portInterfaceMapList){
			sourceHM.put(piMap.getOperationStep(), piMap.getIneterfaceSource().getSourceID());
		}
		return sourceHM;
	}
	
	public Date fetchNextLedgerDate(String[] portfolios, ResultContainer container) {
		Date ledgerDate=null;
		if(portfolios.length>0){
			for(String portnum:portfolios){
				PortfolioHdr portfolio=commService.loadPortfolio(portnum);
				if(ledgerDate==null){
					ledgerDate=portfolio.getNextGLClose();
				}else{
					if(ledgerDate.compareTo(portfolio.getNextGLClose()) != 0){
						container.setCorrectFlag(false);
						container.setErrorCode(ReturnResult.wf_DateUnMatch);
						container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.wf_DateUnMatch));
					}
				}
			}
		}else{
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.wf_NoPortSelect);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.wf_NoPortSelect));
		}
		return ledgerDate;
	}
	
	public List<WfFailedData> loadErrorMsgLog(String[] ports, int category, Date ledgerDate) {
		return msgLogDao.loadMsgLogMarketList(ports,category,ledgerDate);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void ignoreErrorMsg(int logid, boolean ignoreStatus){
		MsgLog msglog=msgLogDao.loadEntity(MsgLog.class, logid);
		msglog.setIngore(ignoreStatus);
		msgLogDao.updateEntity(msglog);
	}
	
	public List<JobsHis> loadErrorJobsHis(String[] ports, Date ledgrDate, int workFlowStep) {
		List<Integer> jobsList=WorkFlowSteps.subJobslOptions.get(workFlowStep);
		return jobsHisDao.loadErrorJobsHis(ports, ledgrDate, jobsList);
	}

	@Override
	public List<Integer> loadWorkFlowJobStatus(String[] portfolios, Date ledgerDate,int status) {
		return workFlowFactoryDao.loadWorkFlowJobStatus(portfolios, ledgerDate, status);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public List<WorkFlowStepStatus> loadFinishWorkFlowJob(String[] portfolios, Date ledgerDate){
		fetchCurWorkFlow(portfolios, ledgerDate, JixuUserUtil.getUserID(), new ResultContainer());
		List<WorkFlowFactory> wffList=workFlowFactoryDao.loadCompleteWorkFlowAfterDay(portfolios, ledgerDate);
		
		List<WorkFlowStepStatus> wfssList=new ArrayList<WorkFlowStepStatus>();
		Date curDate=null;
		WorkFlowStepStatus wfss=null;
		for(WorkFlowFactory wff:wffList){
			if(curDate==null || curDate.compareTo(wff.getLedgerDate())!=0){
				curDate=wff.getLedgerDate();
				wfss=new WorkFlowStepStatus();
				wfss.setLedgerDate(curDate);
				wfssList.add(wfss);
			}
			
			HashMap<Integer, List<String>> stepPortsMap=wfss.getStepPortsMap();
			if(stepPortsMap.containsKey(wff.getWfStep())){
				stepPortsMap.get(wff.getWfStep()).add(wff.getPortfolio().getId());
			}else{
				List<String> portsList=new ArrayList<String>();
				portsList.add(wff.getPortfolio().getId());
				stepPortsMap.put(wff.getWfStep(), portsList);
			}
		}
		
		return wfssList;
	}
}
