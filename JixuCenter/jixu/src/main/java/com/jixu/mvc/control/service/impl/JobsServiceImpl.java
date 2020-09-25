package com.jixu.mvc.control.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.common.func.CommFuncService;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.JobsType;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.WorkFlowSteps;
import com.jixu.common.types.WorkFlowStatus;
import com.jixu.common.util.DateUtil;
import com.jixu.dao.intf.PortfolioHdrDao;
import com.jixu.job.obj.WorkFlowStepStatus;
import com.jixu.job.service.AutoMaturityService;
import com.jixu.job.service.CalNavService;
import com.jixu.job.service.CloseControlService;
import com.jixu.job.service.ContractualSetService;
import com.jixu.job.service.ExecuteJobService;
import com.jixu.mvc.control.service.JobsService;
import com.jixu.mvc.control.service.WorkFlowService;
import com.orm.obj.PortfolioHdr;

@Service("jixuJobsService")
public class JobsServiceImpl implements JobsService{
	
	@Resource(name="workFlowService")
	private WorkFlowService wfService;
	
	@Resource(name="executeJob")
	private ExecuteJobService executeJob;
	
	@Resource(name="closeService")
	private CloseControlService closeControlService;
	
	@Resource(name="calNavService")
	private CalNavService calNav;
	
	@Resource(name="inteSetService")
	private AutoMaturityService inteSetService;
	
	@Resource(name="contractualSetService")
	private ContractualSetService setCashService;
	
	@Resource(name="commfuncService")
	protected CommFuncService commfuncService;
	
	@Autowired
	private PortfolioHdrDao portfolioHdrDao;
	
	@Override
	public ResultContainer executeJobs(List<String> ports, Date ledgerDate) {
		int[] workFlowSteps = null;
		if(DateUtil.isLastDayOfMonth(DateUtil.ConvertUtil2Sql(ledgerDate))){
			workFlowSteps= new int[]{WorkFlowSteps.autojob,WorkFlowSteps.dailyClose,WorkFlowSteps.daily,WorkFlowSteps.monthly,WorkFlowSteps.nav,WorkFlowSteps.generalClose};
		}else {
			workFlowSteps= new int[]{WorkFlowSteps.autojob,WorkFlowSteps.dailyClose,WorkFlowSteps.daily,WorkFlowSteps.nav,WorkFlowSteps.generalClose};
		}
		
		int[] dailyJobs= {JobsType.PURSALEJNL, JobsType.GENERAL, JobsType.INCOME, JobsType.CASH, JobsType.SUBRED, JobsType.EXPEND};

		PortfolioHdr portfolio=portfolioHdrDao.loadPortfolio(ports.get(0));
		Date nextGlGlose=portfolio.getNextGLClose();
		ResultContainer result=new ResultContainer();
		while(!nextGlGlose.after(ledgerDate)) {
			//循环按日每天做账
			result=executeAppointedJobs(ports, nextGlGlose, workFlowSteps, dailyJobs, true);
			if(!result.isCorrectFlag()) {
				break;
			}
			nextGlGlose=DateUtil.getNextDay(nextGlGlose);
		}
		
		return result;
	}
	
	@Override
	public ResultContainer redoAllJobs(List<String> ports, Date ledgerDate) {
		int[] redoWorkFlowSteps= {WorkFlowSteps.generalClose,WorkFlowSteps.nav,WorkFlowSteps.monthly,WorkFlowSteps.daily,WorkFlowSteps.dailyClose,WorkFlowSteps.autojob};
		return redoAppointedJobs(ports, ledgerDate, redoWorkFlowSteps);
	}
	
	
	@Override
	public ResultContainer redoAppointedJobs(List<String> ports, Date ledgerDate, int[] jobsSteps) {
		int userID=JixuUserUtil.getUserID();
		String[] portnums=new String[ports.size()];
		for(int i=0;i<ports.size();i++){
			portnums[i]=ports.get(i).toString();
		}
		
		ResultContainer resultContainer=new ResultContainer();
		List<ResultContainer> innerList=new ArrayList<ResultContainer>();
		
		//会计账务处理jobID
		List<Integer> accountSteps=Arrays.stream(jobsSteps).boxed().collect(Collectors.toList());
		//多组合撤销全部    先查询当前job的状态    list里面存的是要撤销的jobID
		List<WorkFlowStepStatus> wfsslist=wfService.loadFinishWorkFlowJob(portnums, ledgerDate);
		for(WorkFlowStepStatus wfss:wfsslist){
			//按照日期回退
			Date backDate=wfss.getLedgerDate();
			HashMap<Integer, List<String>> stepPortsMap=wfss.getStepPortsMap();
			
			for (Integer jobID : accountSteps) {
				//如果list中不包含当前jobID 说明几个投资组合都已经撤销过了  直接跳过
				if(!stepPortsMap.containsKey(jobID)){
					continue;
				}
				
				List<String> portsList=stepPortsMap.get(jobID);
				String[] portsArr=portsList.toArray(new String[portsList.size()]);
				ResultContainer mainContainer=null;
				switch(jobID){
				case WorkFlowSteps.dailyClose:
					mainContainer=closeControlService.reopenTran(userID, portsArr, backDate);
					break;
				case WorkFlowSteps.generalClose:
					mainContainer=closeControlService.reopenGL(userID, portsArr, backDate);
					break;
				case WorkFlowSteps.nav:
					mainContainer=calNav.deleteNav(userID, portsArr, backDate);
					if(mainContainer.isCorrectFlag())
						calNav.delValuationReport(portsArr, backDate);
					break;
				case WorkFlowSteps.daily:
					mainContainer=executeJob.removeJob(userID, portsArr, backDate);
					break;
				case WorkFlowSteps.autojob:
					mainContainer=setCashService.deleteCash(userID, portsArr, backDate);
					break;
				case WorkFlowSteps.monthly:
					//判断当天是否是一个月最后一天   是否执行每月会计   
					if(DateUtil.isLastDayOfMonth(DateUtil.ConvertUtil2Sql(backDate)))
						mainContainer=executeJob.removeJob(userID, portsArr, backDate, JournalTypes.SETDOWN);
					else
						mainContainer=new MsgContainer();
					break;
				}
				
				if(mainContainer!=null){
					List<ResultContainer> containerList=(List<ResultContainer>)mainContainer.getEntityObj();
					if(containerList!=null){
						innerList.addAll(containerList);
					}
					//判断如撤账成功  更新工作流状态  否则终止for循环
					if(mainContainer.isCorrectFlag())
						wfService.updateWorkFlowStep(jobID, portsList, backDate, WorkFlowStatus.process);
					else{
						resultContainer.setCorrectFlag(false);
						resultContainer.setEntityObj(innerList);
						return resultContainer;
					}
				}
			}
		}
		
		resultContainer.setEntityObj(innerList);
		return resultContainer;
	}
	
	/**
	 * 
	 * @param ports	投资组合列表
	 * @param ledgerDate	做账日期
	 * @param jobsSteps		需要跑账的步骤, 自动化, 关交易, 日记账,计算净值,关总账
	 * @param dailyJobIDs	如果前面配了日记账，这里需要传入跑哪些日记账
	 * @param redoFlag		如果已经有了凭证，是否需要撤账
	 * @return
	 */
	public ResultContainer executeAppointedJobs(List<String> ports, Date ledgerDate, int[] jobsSteps, int[] dailyJobIDs, boolean redoFlag) {
		int userID=JixuUserUtil.getUserID();
		List<String> newPorts = new ArrayList<String>();
		for (String portnum : ports) {
			//判断投资组合是否存在(衍生品没有投资通道产品)
			PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
			if(portfolio!=null){
				newPorts.add(portnum);
			}
		}
		
		HashSet<String> portsSet=new HashSet<String>();
		newPorts.forEach(str->portsSet.add(str));
		String[] portnums=portsSet.stream().toArray(String[]::new);
		
		//开启新线程之前,添加如下代码 :将RequestAttributes对象设置为子线程共享
		//方法有bug, 多次刷新页面后, 在子线程不能获取session
		//ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		//RequestContextHolder.setRequestAttributes(sra, true);
		
		//会计账务处理jobID
		List<Integer> accountSteps=Arrays.stream(jobsSteps).boxed().collect(Collectors.toList());
		//多组合执行全部   先查询当前job的状态    list里面存的是要执行的jobID  参考之前执行全部川哥在前端写的判定条件
		ResultContainer resultContainer=new ResultContainer();
		List<ResultContainer> innerList=new ArrayList<ResultContainer>();
		
		for (int jobID : accountSteps) {
			//如果list中不包含当前jobID 说明几个投资组合都已经执行过了  直接跳过
			//joyin不判断，直接跑账，后面会重置工作流状态
//			if(!list.contains(jobID)){
//				continue;
//			}
			
			//重新获取投资组合，执行失败的投资组合不会继续下一步
			portnums=portsSet.stream().toArray(String[]::new);
			//失败的组合被移除时   当组合数为0时不再继续跑账
			if(portnums.length==0){
				break;
			}
			
			ResultContainer mainContainer=new ResultContainer();
			
			switch(jobID){
			case WorkFlowSteps.autojob:
				mainContainer=executeJob.executeAutoJob(userID, portnums, ledgerDate);
//				else
//					mainContainer=executeJob.returnAutoJobFalseMsg(mainContainer);
				break;
			case WorkFlowSteps.dailyClose:
				mainContainer=executeJob.executeCloseTranJob(userID, portnums, ledgerDate);
				break;
			case WorkFlowSteps.generalClose:
				mainContainer=executeJob.executeCloseGLJob(userID, portnums, ledgerDate); //并发关总账
				break;
			case WorkFlowSteps.nav:
				mainContainer=executeJob.executeCreateNavJob(userID, portnums, ledgerDate);
				if(mainContainer.isCorrectFlag())
					executeJob.genValuationReports(portnums, ledgerDate);
				break;
			case WorkFlowSteps.daily:
				if(redoFlag)
					mainContainer=executeJob.removeJob(userID, portnums, ledgerDate);
				
				//外部系统调用，则不需要删除
				if(mainContainer.isCorrectFlag()){
					//执行指定的job
					mainContainer=executeJob.executeAppointedDailyJob(portnums, ledgerDate, dailyJobIDs);
				}
				break;
			case WorkFlowSteps.monthly:
				mainContainer=executeJob.executeJob(userID, portnums, ledgerDate, JobsType.PeriodSet);	
				break;
			}
			
			if(mainContainer!=null){
				List<ResultContainer> containerList=(List<ResultContainer>)mainContainer.getEntityObj();
				
				if(containerList!=null){
					innerList.addAll(containerList);
				}
				//判断如果执行成功  更新工作流状态  否则终止for循环
				for(ResultContainer innercontainer:containerList) {
					if(innercontainer.isCorrectFlag()) {
						List<String> updatePortStateList=new ArrayList<String>();
						updatePortStateList.add(innercontainer.getEntityID());
						wfService.updateWorkFlowStep(jobID, updatePortStateList, ledgerDate, WorkFlowStatus.finish);
					}else{
						resultContainer.setCorrectFlag(false);
						//从投资组合set里去除执行失败的port
						portsSet.remove(innercontainer.getEntityID());
					}
				}
				
				if(!mainContainer.isCorrectFlag() && containerList.size()==0) {
					innerList.add(mainContainer);
					break;
				}
			}
		}
		
		resultContainer.setEntityObj(innerList);
		return resultContainer;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer executeJobByJobID(String portnum, Date ledgerDate, int jobID) {
		ResultContainer container=executeJob.executeJobByJobID(portnum, ledgerDate, jobID);
		int step=0;
		
		switch(jobID) {
			case JobsType.autoMaturity:
			case JobsType.ContractCash:
				step=WorkFlowSteps.autojob;
				break;
			case JobsType.closeTran:
				step=WorkFlowSteps.dailyClose;
				break;
			case JobsType.CASH:
			case JobsType.PURSALEJNL:
			case JobsType.GENERAL:
			case JobsType.SUBRED:
			case JobsType.INCOME:
			case JobsType.EXPEND:
				step=WorkFlowSteps.daily;
				break;
			case JobsType.calNav:
				step=WorkFlowSteps.nav;
				break;	
			case JobsType.clostGL:
				step=WorkFlowSteps.generalClose;
				break;
			case JobsType.PeriodSet:
				step=WorkFlowSteps.monthly;
				break;
		}
		
		if(step>0) {
			List<String> portList=new ArrayList<String>();
			portList.add(portnum);
			if(container.isCorrectFlag())
				wfService.updateWorkFlowStep(jobID, portList, ledgerDate, WorkFlowStatus.finish);
			else
				wfService.updateWorkFlowStep(jobID, portList, ledgerDate, WorkFlowStatus.process);
		}
		return container;
	}
}
