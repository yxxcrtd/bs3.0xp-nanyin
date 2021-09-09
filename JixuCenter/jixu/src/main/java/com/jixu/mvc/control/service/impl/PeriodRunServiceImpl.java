package com.jixu.mvc.control.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jixu.common.type.JobsType;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.WorkFlowSteps;
import com.jixu.common.types.WorkFlowStatus;
import com.jixu.common.util.DateUtil;
import com.jixu.job.service.AutoMaturityService;
import com.jixu.job.service.CalNavService;
import com.jixu.job.service.CloseControlService;
import com.jixu.job.service.ContractualSetService;
import com.jixu.job.service.ExecuteJobService;
import com.jixu.mvc.control.service.PeriodRunService;
import com.jixu.mvc.control.service.VoucherService;
import com.jixu.mvc.control.service.WorkFlowService;
import com.orm.obj.PortfolioHdr;

@Service("periodRunService")
public class PeriodRunServiceImpl implements PeriodRunService{
	
	@Resource(name="workFlowService")
	private WorkFlowService wfService;
	
	@Resource(name="executeJob")
	private ExecuteJobService executeJob;
	
	@Resource(name="inteSetService")
	private AutoMaturityService inteSetService;
	
	@Resource(name="calNavService")
	private CalNavService calNav;
	
	@Resource(name="closeService")
	private CloseControlService closeControlService;
	
	@Resource(name="contractualSetService")
	private ContractualSetService setCashService;
	
	@Resource(name="voucherService")
	private VoucherService voucherService;
	
	private static final Logger logger = LoggerFactory.getLogger(PeriodRunServiceImpl.class);
	
	// 自动区间做账
	@Override
	public List<Object> autoRunJob(int userID, List<String> ports_list,Date startDate, Date endDate) {
		// 投资组合
		String[] portnums = convertData(ports_list);
		List<Object> data = new ArrayList<Object>();
		
		//开启新线程之前,添加如下代码 :将RequestAttributes对象设置为子线程共享
		//刷新页面后开启多线程,有bug,拿不到session
		//ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		//RequestContextHolder.setRequestAttributes(sra, true);
		
		while (!startDate.after(endDate)) { // 判断开始日期不在结束日期后
			ResultContainer mainContainer = null;
			// 获取当前工作流
			wfService.fetchCurWorkFlow(portnums, startDate, userID, mainContainer);
			// 更改市场数据信息和交易数据信息状态
			int[] categorys = { WorkFlowSteps.price, WorkFlowSteps.fxRate,WorkFlowSteps.smf, WorkFlowSteps.ta, WorkFlowSteps.cash,WorkFlowSteps.corpAction, WorkFlowSteps.fxTrade,WorkFlowSteps.tran };
			for (int i = 0; i < categorys.length; i++) {
				wfService.updateWorkFlowStep(categorys[i], ports_list, startDate,WorkFlowStatus.finish);
			}
			// 会计账务处理jobID
			List<Integer> accountSteps = new ArrayList<Integer>(WorkFlowSteps.accountOperations.get(WorkFlowSteps.runAllJobs));
			// 多组合执行全部 先查询当前job的状态 list里面存的是要执行的jobID
			List<Integer> list = wfService.loadWorkFlowJobStatus(portnums, startDate,WorkFlowStatus.finish);
			if(list.size()==0){
				List<Object> item = new ArrayList<Object>();
				item.add(DateUtil.convertD2YYYY_MM_DD(endDate));
				item.add("-");
				item.add("-");
				item.add("-");
				item.add("当天已做账,不要重复做账");
				data.add(item);
				return data;
			}

			for (Integer jobID : accountSteps) {
				// 如果list中不包含当前jobID 说明几个投资组合都已经执行过了 直接跳过
				if (!list.contains(jobID)) {
					continue;
				}
				switch (jobID) {
				case WorkFlowSteps.autojob:
					mainContainer = executeJob.executeAutoJob(userID, portnums,startDate);
					if (!mainContainer.isCorrectFlag()) {
						mainContainer = executeJob.returnAutoJobFalseMsg(mainContainer);
					}
					break;
				case WorkFlowSteps.dailyClose:
					mainContainer = executeJob.executeCloseTranJob(userID,portnums, startDate);
					break;
				case WorkFlowSteps.generalClose:
					mainContainer = executeJob.executeCloseGLJob(userID,portnums, startDate); // 并发关总账
					break;
				case WorkFlowSteps.nav:
					mainContainer = executeJob.executeCreateNavJob(userID,portnums, startDate);
					break;
				case WorkFlowSteps.daily:
					mainContainer = executeJob.removeJob(userID, portnums,startDate);
					if (mainContainer.isCorrectFlag()) {
						mainContainer = executeJob.executeDailyJob(userID,portnums, startDate);
					}
					break;
				case WorkFlowSteps.monthly:
					if (DateUtil.isLastDayOfMonth(DateUtil.ConvertUtil2Sql(startDate)))
						mainContainer = executeJob.executeJob(userID, portnums,startDate, JobsType.PeriodSet);
					else
						mainContainer = new MsgContainer();
					break;
				}

				// 输出做账结果
			    data=returnPeriodData(data,mainContainer,startDate);
				if (mainContainer != null && mainContainer.isCorrectFlag()) {
					// 更新工作流状态
					wfService.updateWorkFlowStep(jobID, ports_list, startDate,WorkFlowStatus.finish);
				} else {
					break;
				}
			}
			 /*//比较当天凭证 并输出凭证比较结果到log
			  mainContainer=voucherService.testCaseDailyVoucher(portnums,startDate); 
			  List<ResultContainer> containerList=(List<ResultContainer>)mainContainer.getEntityObj(); 
			  if(containerList!=null){
				  for(ResultContainer container:containerList){
					  if(!container.isCorrectFlag()){
						  logger.debug(container.getErrorMsg()+"  核对不一致!");
						  
					  } 
				  }
			  }*/
			// 下一天
			if(mainContainer != null && !mainContainer.isCorrectFlag()){
				break;
			}
			startDate = DateUtil.getNextDay(startDate);
		}
		return data;
	}

	//自动区间撤账
	@Override
	public List<Object> autoRedoJob(int userID, List<String> ports_list,Date startDate, Date endDate) {
		// 投资组合
		String[] portnums = convertData(ports_list);
		List<Object> data = new ArrayList<Object>();
		//开启新线程之前,添加如下代码 :将RequestAttributes对象设置为子线程共享
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		RequestContextHolder.setRequestAttributes(sra, true);
		
		while(!endDate.before(startDate)){  //判断结束日期不在开始日期前
			ResultContainer mainContainer = null;
			//获取当前工作流
			wfService.fetchCurWorkFlow(portnums, endDate, userID, mainContainer);
			//会计账务处理jobID
			List<Integer> accountSteps=new ArrayList<Integer>(WorkFlowSteps.accountOperations.get(WorkFlowSteps.redoAllJobs));
			//多组合撤销全部    先查询当前job的状态    list里面存的是要撤销的jobID
			List<Integer> list=wfService.loadWorkFlowJobStatus(portnums, endDate, WorkFlowStatus.process);
			if(list.size()==0){
				List<Object> item = new ArrayList<Object>();
				item.add(DateUtil.convertD2YYYY_MM_DD(endDate));
				item.add("-");
				item.add("-");
				item.add("-");
				item.add("当天已撤账,不要重复撤账");
				data.add(item);
				return data;
			}
			for (Integer jobID : accountSteps) {
				//如果list中不包含当前jobID 说明几个投资组合都已经撤销过了  直接跳过
				if(!list.contains(jobID)){
					continue;
				}
				switch(jobID){
				case WorkFlowSteps.dailyClose:
					mainContainer=closeControlService.reopenTran(userID, portnums, endDate);
					break;
				case WorkFlowSteps.generalClose:
					mainContainer=closeControlService.reopenGL(userID, portnums, endDate);
					break;
				case WorkFlowSteps.nav:
					mainContainer=calNav.deleteNav(userID, portnums, endDate);
					if(mainContainer.isCorrectFlag())
						calNav.delValuationReport(portnums, endDate);
					break;
				case WorkFlowSteps.daily:
					mainContainer=executeJob.removeJob(userID, portnums, endDate);
					break;
				case WorkFlowSteps.autojob:
					mainContainer=setCashService.deleteCash(userID, portnums, endDate);
					break;
				case WorkFlowSteps.monthly:
					//判断当天是否是一个月最后一天   是否执行每月会计   
					if(DateUtil.isLastDayOfMonth(DateUtil.ConvertUtil2Sql(endDate)))
						mainContainer=executeJob.removeJob(userID, portnums, endDate, JournalTypes.SETDOWN);
					else
						mainContainer=new MsgContainer();
					break;
				}
				
				// 输出撤账结果
				 data=returnPeriodData(data,mainContainer,endDate);
				// 更新工作流状态
				if(mainContainer != null && mainContainer.isCorrectFlag()){
					wfService.updateWorkFlowStep(jobID, ports_list, endDate, WorkFlowStatus.process);
				}else{
					break;
				}
			}
			
			if(mainContainer != null && !mainContainer.isCorrectFlag()){
				break;
			}
			
			//更改市场数据信息和交易数据信息状态
			int[] categorys={7,8,5,6,4,2,3,1};
			for (int i = 0; i < categorys.length; i++) {
				wfService.updateWorkFlowStep(categorys[i], ports_list, endDate, WorkFlowStatus.process);
			}
			//上一天
			endDate=DateUtil.getNextNDays(endDate, -1);
		}
		return data;
	}
	
	
	public List<Object> returnPeriodData(List<Object> data,ResultContainer mainContainer,Date jobDate){
		List<ResultContainer> containerList = (List<ResultContainer>) mainContainer.getEntityObj();
		if (containerList != null) {
			for (ResultContainer container : containerList) {
				PortfolioHdr portfolio = (PortfolioHdr) container.getEntityObj();
				List<Object> item = new ArrayList<Object>();
				item.add(DateUtil.convertD2YYYY_MM_DD(jobDate));
				item.add(container.getHeader());
				item.add(portfolio.getShortName());
				if (container.isCorrectFlag()) {
					item.add("成功");
				} else {
					item.add("失败");
				}
				item.add(container.getErrorMsg());
				data.add(item);
			}
		}
		return data;
	}
	
	public String[] convertData(List<String> ports_list){
		String[] portnums = new String[ports_list.size()];
		for (int i = 0; i < ports_list.size(); i++) {
			portnums[i] = ports_list.get(i);
		}
		return portnums;
	}
}
