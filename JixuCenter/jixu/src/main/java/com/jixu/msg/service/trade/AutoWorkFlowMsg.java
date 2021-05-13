package com.jixu.msg.service.trade;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.JobsType;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.WorkFlowSteps;
import com.jixu.common.types.WorkFlowStatus;
import com.jixu.common.util.DateUtil;
import com.jixu.job.service.AutoMaturityService;
import com.jixu.job.service.CalNavService;
import com.jixu.job.service.CloseControlService;
import com.jixu.job.service.ContractualSetService;
import com.jixu.job.service.ExecuteJobService;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.jixu.mvc.control.service.VoucherService;
import com.jixu.mvc.control.service.WorkFlowService;
import com.orm.obj.PortfolioHdr;

/** 
 * @ClassName 		AutoWorkFlowMsg
 * @author  		Wang Zhongyuan
 * @Date 			2018年7月10日 下午3:51:39 	
 * @Description		[]  
 * @return  
 */

@Service
public class AutoWorkFlowMsg extends MsgExecuteService{
	
	private static final Logger logger = LoggerFactory.getLogger(AutoWorkFlowMsg.class);
	
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
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		
		HashMap<String, Object> fieldsHM=msgcontainer.getFieldsHM();
		
		//操作方式    做账   撤账
		String operation=getFieldValue(fieldsHM, MsgFields.operation);
		
		//开始日期
		String startDate=getFieldValue(fieldsHM, MsgFields.start).replaceAll("-", "");
		checkDateFormat(msgcontainer,msgHeader(),startDate);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		//结束日期
		String endDate=getFieldValue(fieldsHM, MsgFields.end).replaceAll("-", "");
		checkDateFormat(msgcontainer,msgHeader(),endDate);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		//投资组合
		String ports=getFieldValue(fieldsHM, "portnum");
		String[] portsStr=ports.split("\\|");
		List<String> ports_list =new ArrayList<String>();
		String[] portnums=new String[portsStr.length];
		for(int i=0;i<portsStr.length;i++){
			portnums[i]=portsStr[i];
			ports_list.add(portsStr[i]);
		}
		
		//当前登录用户
		int userID=msgcontainer.getUserID();
		
		Date start=DateUtil.convertStr2Date(startDate);
		Date end=DateUtil.convertStr2Date(endDate);;
		// 如果operation是add  表示自动跑账
		if(operation.equalsIgnoreCase(add)){
			while(!start.after(end)){  //判断开始日期不在结束日期后
				//获取当前工作流
				wfService.fetchCurWorkFlow(portnums, start, userID, msgcontainer);
				//更改市场数据信息和交易数据信息状态
				int[] categorys={2,3,1,7,8,5,6,4};
				for (int i = 0; i < categorys.length; i++) {
					wfService.updateWorkFlowStep(categorys[i], ports_list, start, WorkFlowStatus.finish);
				}
				//会计账务处理jobID
				List<Integer> accountSteps=new ArrayList<Integer>(WorkFlowSteps.accountOperations.get(WorkFlowSteps.runAllJobs));
				//多组合执行全部   先查询当前job的状态    list里面存的是要执行的jobID  
				List<Integer> list=wfService.loadWorkFlowJobStatus(portnums, start, WorkFlowStatus.finish);
				if(list.size()<=0){
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorMsg("当天已做账,不要重复做账");
					return;
				}
				ResultContainer mainContainer=null;
				for (Integer jobID : accountSteps) {
					//如果list中不包含当前jobID 说明几个投资组合都已经执行过了  直接跳过
					if(!list.contains(jobID)){
						continue;
					}
					switch(jobID){
					case WorkFlowSteps.autojob:
						mainContainer=executeJob.executeAutoJob(userID, portnums, start);
						if(!mainContainer.isCorrectFlag()) {
							mainContainer=executeJob.returnAutoJobFalseMsg(mainContainer);
						}
						break;
					case WorkFlowSteps.dailyClose:
						mainContainer=executeJob.executeCloseTranJob(userID, portnums, start);
						break;
					case WorkFlowSteps.generalClose:
						mainContainer=executeJob.executeCloseGLJob(userID, portnums, start); //并发关总账
						break;
					case WorkFlowSteps.nav:
						mainContainer=executeJob.executeCreateNavJob(userID, portnums, start);
						if(mainContainer.isCorrectFlag())
							executeJob.genValuationReports(portnums, start);
						break;
					case WorkFlowSteps.daily:
						mainContainer=executeJob.removeJob(userID, portnums, start);
						if(mainContainer.isCorrectFlag()){
							mainContainer=executeJob.executeDailyJob(userID, portnums, start);
						}
						break;
					case WorkFlowSteps.monthly:
						if(DateUtil.isLastDayOfMonth(DateUtil.ConvertUtil2Sql(start)))
							mainContainer=executeJob.executeJob(userID, portnums, start, JobsType.PeriodSet);	
						else
							mainContainer=new MsgContainer();
						break;
					}
					
					// 打印做账结果到interfacelog
					List<ResultContainer> containerList= null;
					if (mainContainer != null){
						containerList = (List<ResultContainer>)mainContainer.getEntityObj();
					}
					if(containerList!=null){
						for(ResultContainer container:containerList){
							PortfolioHdr portfolio=(PortfolioHdr)container.getEntityObj();
							if(container.isCorrectFlag()){
								logger.debug("做账日期:"+DateUtil.convertD2YYYY_MM_DD(start)+"作业名称:"+container.getHeader()
										+" 投资组合:"+portfolio.getShortName()+" 执行结果:成功"+" 错误详情:"+container.getErrorMsg());
							}else{
								logger.debug("做账日期:"+DateUtil.convertD2YYYY_MM_DD(start)+"作业名称:"+container.getHeader()
										+" 投资组合:"+portfolio.getShortName()+" 执行结果:失败"+" 错误详情:"+container.getErrorMsg());
							}
						}
					}
					if(mainContainer.isCorrectFlag()){
						// 更新工作流状态
						wfService.updateWorkFlowStep(jobID, ports_list, start, WorkFlowStatus.finish);
					}else{
						msgcontainer.setCorrectFlag(false);
						msgcontainer.setErrorMsg("做账失败,详情请查看日志");
						return;
					}
				}
				//比较当天凭证  
			/*	mainContainer=voucherService.testCaseDailyVoucher(portnums, start);
				if(!mainContainer.isCorrectFlag()){
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorMsg("凭证不一致,详情请查看日志");
					return;
				}*/
				//下一天
				start=DateUtil.getNextNDays(start, 1);
			}
			// 如果operation是delete  表示自动撤账
		}else if(operation.equalsIgnoreCase(delete)){
			while(!end.before(start)){  //判断结束日期不在开始日期前
				//获取当前工作流
				wfService.fetchCurWorkFlow(portnums, end, userID, msgcontainer);
				//会计账务处理jobID
				List<Integer> accountSteps=new ArrayList<Integer>(WorkFlowSteps.accountOperations.get(WorkFlowSteps.redoAllJobs));
				//多组合撤销全部    先查询当前job的状态    list里面存的是要撤销的jobID
				List<Integer> list=wfService.loadWorkFlowJobStatus(portnums, end, WorkFlowStatus.process);
				if(list.size()<=0){
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorMsg("当天已撤账,不要重复撤账");
					return;
				}
				ResultContainer mainContainer=null;
				for (Integer jobID : accountSteps) {
					//如果list中不包含当前jobID 说明几个投资组合都已经撤销过了  直接跳过
					if(!list.contains(jobID)){
						continue;
					}
					switch(jobID){
					case WorkFlowSteps.dailyClose:
						mainContainer=closeControlService.reopenTran(userID, portnums, end);
						break;
					case WorkFlowSteps.generalClose:
						mainContainer=closeControlService.reopenGL(userID, portnums, end);
						break;
					case WorkFlowSteps.nav:
						mainContainer=calNav.deleteNav(userID, portnums, end);
						if(mainContainer.isCorrectFlag())
							calNav.delValuationReport(portnums, end);
						break;
					case WorkFlowSteps.daily:
						mainContainer=executeJob.removeJob(userID, portnums, end);
						break;
					case WorkFlowSteps.autojob:
						mainContainer=setCashService.deleteCash(userID, portnums, end);
						break;
					case WorkFlowSteps.monthly:
						//判断当天是否是一个月最后一天   是否执行每月会计   
						if(DateUtil.isLastDayOfMonth(DateUtil.ConvertUtil2Sql(end)))
							mainContainer=executeJob.removeJob(userID, portnums, end, JournalTypes.SETDOWN);
						else
							mainContainer=new MsgContainer();
						break;
					}
					
					// 输出执行结果到interfacelog
					List<ResultContainer> containerList= null;
					if (mainContainer != null){
						containerList = (List<ResultContainer>)mainContainer.getEntityObj();
					}
					if(containerList!=null){
						for(ResultContainer container:containerList){
							PortfolioHdr portfolio=(PortfolioHdr)container.getEntityObj();
							if(container.isCorrectFlag()){
								logger.debug("做账日期:"+DateUtil.convertD2YYYY_MM_DD(end)+"作业名称:"+container.getHeader()
										+" 投资组合:"+portfolio.getShortName()+" 执行结果:成功"+" 错误详情:"+container.getErrorMsg());
							}else{
								logger.debug("做账日期:"+DateUtil.convertD2YYYY_MM_DD(end)+"作业名称:"+container.getHeader()
										+" 投资组合:"+portfolio.getShortName()+" 执行结果:失败"+" 错误详情:"+container.getErrorMsg());
							}
						}
					}
					// 更新工作流状态
					if(mainContainer.isCorrectFlag()){
						wfService.updateWorkFlowStep(jobID, ports_list, end, WorkFlowStatus.process);
					}else{
						msgcontainer.setCorrectFlag(false);
						msgcontainer.setErrorMsg("撤账失败,详情请查看日志");
						return;
					}
				}
				
				//更改市场数据信息和交易数据信息状态
				int[] categorys={7,8,5,6,4,2,3,1};
				for (int i = 0; i < categorys.length; i++) {
					wfService.updateWorkFlowStep(categorys[i], ports_list, end, WorkFlowStatus.process);
				}
				//上一天
				end=DateUtil.getNextNDays(end, -1);
			}
		}
	}
	
	@Override
	public String messageForm() {
		return null;
	}
	
	@Override
	public String messageName() {
		return "工作流自动作业";
	}
	
	@Override
	public String msgHeader() {
		return null;
	}
}
