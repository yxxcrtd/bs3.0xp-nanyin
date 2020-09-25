package com.jixu.mvc.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.controller.ExceptionControl;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.JobsType;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.WorkFlowSteps;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.JsonUtil;
import com.jixu.job.service.AutoMaturityService;
import com.jixu.job.service.CalNavService;
import com.jixu.job.service.CloseControlService;
import com.jixu.job.service.ContractualSetService;
import com.jixu.job.service.ExecuteJobService;
import com.jixu.msg.dto.JobExeDto;
import com.jixu.mvc.control.service.JobsService;
import com.jixu.mvc.control.service.WorkFlowService;
import com.orm.obj.PortfolioHdr;

@RestController
public class JobsController extends ExceptionControl{
	
	@RequestMapping(value="/job.do", method = RequestMethod.GET, name="作业-页面显示")
	public String client() {
		return "job";
	}
	
	@Resource(name="closeService")
	private CloseControlService closeControlService;
	
	@Resource(name="calNavService")
	private CalNavService calNav;
	
	@Resource(name="executeJob")
	private ExecuteJobService executeJob;
	
	@Resource(name="contractualSetService")
	private ContractualSetService setCashService;
	
	@Resource(name="inteSetService")
	private AutoMaturityService inteSetService;
	
	@Resource(name="commfuncService")
	private CommFuncService commService;
	
	@Resource(name="workFlowService")
	private WorkFlowService wfService;
	
	@Resource(name="jixuJobsService")
	private JobsService jobsService;
	
	@RequestMapping(value="/job/runSingleJob.do", method = RequestMethod.POST, name="作业-单组合执行")
	public void runSingleJob(@RequestParam(value = "jobID", required = true) int jobID,
					@RequestParam(value = "date", required = true) String date,
					@RequestParam(value = "portID", required = true) String portID,
					HttpServletRequest request,
					HttpServletResponse response){
		int userID=JixuUserUtil.getUserIDbyRequest(request);
		
		ResultContainer container=executeJob.reExecuteJob(userID, portID, DateUtil.convertStr2Date(date.replaceAll("-", "")), jobID);
		PortfolioHdr portfolio=(PortfolioHdr)container.getEntityObj();
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("作业名称", "投资组合", "执行结果", "详细信息");		
		result.put("titles", titles);
		List<Object> data = new ArrayList<Object>();
		
		List<Object> item = new ArrayList<Object>();
		item.add(container.getHeader());
		item.add(portfolio.getShortName());
		if(container.isCorrectFlag()){
			item.add("成功");
		}else{
			item.add("失败");
		}
		item.add(container.getErrorMsg());
		data.add(item);
		
		result.put("data", data);	
		result.put("resultFlag", container.isCorrectFlag());
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/job/runJob.do", method = RequestMethod.POST, name="作业-多组合执行")
	public void runJob(@RequestParam(value = "id", required = true) int jobID,
					@RequestParam(value = "date", required = true) String date,
					@RequestParam(value = "ports", required = true) String ports,
					HttpServletRequest request,
					HttpServletResponse response){
		int userID=JixuUserUtil.getUserIDbyRequest(request);
		
		List<Object> ports_list = JsonUtil.stringToList(ports);
		String[] portnums=new String[ports_list.size()];
		for(int i=0;i<ports_list.size();i++){
			portnums[i]=ports_list.get(i).toString();
		}
		Date ledgerDate=DateUtil.convertStr2Date2(date);
		
		//开启新线程之前,添加如下代码 :将RequestAttributes对象设置为子线程共享
		//刷新页面后开启多线程,有bug,拿不到session
		//ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		//RequestContextHolder.setRequestAttributes(sra, true);
		
		ResultContainer mainContainer=null;
		switch(jobID){
		case WorkFlowSteps.autojob:
			mainContainer=executeJob.executeAutoJob(userID, portnums, ledgerDate);
			if(!mainContainer.isCorrectFlag()){
				mainContainer=executeJob.returnAutoJobFalseMsg(mainContainer);
			}
			break;
		case WorkFlowSteps.dailyClose:
			//mainContainer=closeControlService.closeTran(userID, portnums, ledgerDate);
			mainContainer=executeJob.executeCloseTranJob(userID, portnums, ledgerDate);
			break;
		case WorkFlowSteps.generalClose:
			//mainContainer=closeControlService.closeGL(userID, portnums, ledgerDate);
			mainContainer=executeJob.executeCloseGLJob(userID, portnums, ledgerDate); //并发关总账
			//dataCenter.exeDataSender(portnums, DateUtil.convertStr2Date2(date));
			break;
		case WorkFlowSteps.nav:
			//mainContainer=calNav.createNav(userID, portnums, ledgerDate);
			mainContainer=executeJob.executeCreateNavJob(userID, portnums, ledgerDate);
			if(mainContainer.isCorrectFlag())
				executeJob.genValuationReports(portnums, ledgerDate);
			break;
		case WorkFlowSteps.daily:
			mainContainer=executeJob.removeJob(userID, portnums, ledgerDate);
			if(mainContainer.isCorrectFlag()){
				mainContainer=executeJob.executeDailyJob(userID, portnums, ledgerDate);
			}
			break;
		case WorkFlowSteps.monthly:
			mainContainer=executeJob.executeJob(userID, portnums, DateUtil.convertStr2Date2(date), JobsType.PeriodSet);	
			break;
		}
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("作业名称", "投资组合", "执行结果", "详细信息");		
		result.put("titles", titles);
		List<Object> data = new ArrayList<Object>();
		
		if(mainContainer!=null){
			List<ResultContainer> containerList=(List<ResultContainer>)mainContainer.getEntityObj();
			for(ResultContainer container:containerList){
				PortfolioHdr portfolio=null;
				if(container.getEntityObj()!=null) {
					portfolio=(PortfolioHdr)container.getEntityObj();
				}else {
					portfolio=commService.loadPortfolio(container.getEntityID());
				}
				List<Object> item = new ArrayList<Object>();
				item.add(container.getHeader());
				item.add(portfolio.getShortName());
				if(container.isCorrectFlag()){
					item.add("成功");
				}else{
					item.add("失败");
				}
				item.add(container.getErrorMsg());
				data.add(item);
			}
			result.put("data", data);	
			result.put("resultFlag", mainContainer.isCorrectFlag());
		}else{
			result.put("resultFlag", false);
		}
		
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/job/runAllJobs.do", method = RequestMethod.POST, name="作业-多组合执行全部")
	public void runAllJobs(@RequestParam(value = "date", required = true) String date,
						   @RequestParam(value = "ports", required = true) String ports,
						   HttpServletRequest request,
						   HttpServletResponse response){
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("作业名称", "投资组合", "执行结果", "详细信息");		
		result.put("titles", titles);
		
		List<String> ports_list = JSONObject.parseArray(ports, String.class);
		Date ledgerDate=DateUtil.convertStr2Date2(date);
		ResultContainer resultContainer=jobsService.executeJobs(ports_list, ledgerDate);
		
		List<ResultContainer> containerList=(List<ResultContainer>)resultContainer.getEntityObj();
		
		List<Object> data = new ArrayList<Object>();
		for(ResultContainer container:containerList){
			PortfolioHdr portfolio=null;
			if(container.getEntityObj()!=null) {
				portfolio=(PortfolioHdr)container.getEntityObj();
			}else {
				portfolio=commService.loadPortfolio(container.getEntityID());
			}
			
			List<Object> item = new ArrayList<Object>();
			item.add(container.getHeader());
			item.add(portfolio.getShortName());
			if(container.isCorrectFlag()){
				item.add("成功");
			}else{
				item.add("失败");
			}
			item.add(container.getErrorMsg());
			data.add(item);
		}
		
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/job/redoJob.do", method = RequestMethod.POST, name = "作业-撤销")
	public void redoJob(@RequestParam(value = "id", required = true) int jobID,
					    @RequestParam(value = "date", required = true) String date,
					    @RequestParam(value = "ports", required = true) String ports,
					    HttpServletRequest request,
					    HttpServletResponse response){
		int userID=JixuUserUtil.getUserIDbyRequest(request);
		
		List<Object> ports_list = JsonUtil.stringToList(ports);
		
		String[] portnums=new String[ports_list.size()];
		for(int i=0;i<ports_list.size();i++){
			portnums[i]=ports_list.get(i).toString();
		}
		
		ResultContainer mainContainer=null;
		Date ledgerDate=DateUtil.convertStr2Date2(date);
		switch(jobID){
		case WorkFlowSteps.dailyClose:
			mainContainer=closeControlService.reopenTran(userID, portnums, ledgerDate);
			break;
		case WorkFlowSteps.generalClose:
			mainContainer=closeControlService.reopenGL(userID, portnums, ledgerDate);
			break;
		case WorkFlowSteps.nav:
			mainContainer=calNav.deleteNav(userID, portnums, ledgerDate);
			if(mainContainer.isCorrectFlag())
				calNav.delValuationReport(portnums, ledgerDate);
			break;
		case WorkFlowSteps.daily:
			mainContainer=executeJob.removeJob(userID, portnums, ledgerDate);
			break;
		case WorkFlowSteps.autojob:
			mainContainer=setCashService.deleteCash(userID, portnums, ledgerDate);
			break;
		case WorkFlowSteps.monthly:
			mainContainer=executeJob.removeJob(userID, portnums, ledgerDate, JournalTypes.SETDOWN);
			break;
		}
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("作业名称", "投资组合", "执行结果", "详细信息");		
		result.put("titles", titles);
		List<Object> data = new ArrayList<Object>();
		
		if(mainContainer!=null){
			List<ResultContainer> containerList=(List<ResultContainer>)mainContainer.getEntityObj();
			for(ResultContainer container:containerList){
				PortfolioHdr portfolio=(PortfolioHdr)container.getEntityObj();
				List<Object> item = new ArrayList<Object>();
				item.add(container.getHeader());
				item.add(portfolio.getShortName());
				if(container.isCorrectFlag()){
					item.add("成功");
				}else{
					item.add("失败");
				}
				item.add(container.getErrorMsg());
				data.add(item);
			}
			result.put("data", data);	
			result.put("resultFlag", mainContainer.isCorrectFlag());
		}else{
			result.put("resultFlag", false);
		}
		
		JsonUtil.writeObjToResponse(response, result);
	}
	
	
	@RequestMapping(value="/job/redoAllJobs.do", method = RequestMethod.POST, name="作业-多组合撤销全部")
	public void redoAllJobs(@RequestParam(value = "date", required = true) String date,
						   @RequestParam(value = "ports", required = true) String ports,
						   HttpServletRequest request,
						   HttpServletResponse response){
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("作业名称", "投资组合", "执行结果", "错误详情");		
		result.put("titles", titles);
		List<Object> data = new ArrayList<Object>();
		
		List<String> ports_list = JSONObject.parseArray(ports, String.class);
		Date ledgerDate=DateUtil.convertStr2Date2(date);
		
		ResultContainer resultContainer=jobsService.redoAllJobs(ports_list, ledgerDate);
		List<ResultContainer> containerList=(List<ResultContainer>)resultContainer.getEntityObj();
		
		for(ResultContainer container:containerList){
			List<Object> item = new ArrayList<Object>();
			item.add(container.getHeader());
			item.add(container.getObjID());
			if(container.isCorrectFlag()){
				item.add("成功");
			}else{
				item.add("失败");
			}
			item.add(container.getErrorMsg());
			data.add(item);
		}
		
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/job/successJob.do", method = RequestMethod.POST)
	public void successJob(@RequestParam(value = "id", required = true) int jobID,
					       @RequestParam(value = "date", required = true) String date,
					       @RequestParam(value = "ports", required = true) String ports,
					       HttpServletResponse response){
		
		List<Object> ports_list = JsonUtil.stringToList(ports);
		System.out.println("Run job " + jobID + " on " + ports_list + " on " + date);
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("作业名称", "投资组合", "开始时间",  "结束时间", "操作人");		
		result.put("titles", titles);
		
		List<Object> data = new ArrayList<Object>();
		List<Object> item = new ArrayList<Object>();
		
		item.add("自动结转和到期");
		item.add("几许组合1");
		item.add("2016-05-26 14:24:23");
		item.add("2016-05-26 14:25:23");;
		item.add("Brooks");
		data.add(item);
		
		List<Object> item1 = new ArrayList<Object>();
		
		item1.add("估值增值日记账");
		item1.add("几许组合1");
		item1.add("2016-05-26 14:24:23");
		item1.add("2016-05-26 14:25:23");;
		item1.add("Brooks");
		data.add(item1);
		
		result.put("data", data);	
		JsonUtil.writeObjToResponse(response, result);
	}
	
	/**
	 * 执行投资组合任务
	 * @param ports
	 * @param ledgerDate
	 * @param jobsSteps
	 * @param dailyJobIDs
	 */
	@PostMapping(value="/job/runAppointJob")
	public ResultContainer runAppointJob(@Valid @RequestBody JobExeDto dto){
		Date ledgerDate=DateUtil.convertStr2Date2(dto.getLedgerDate());
		ResultContainer container=jobsService.executeJobByJobID(dto.getPortnum(), ledgerDate, dto.getJobID());
		container.setEntityObj("");
		return container;
	}
}
