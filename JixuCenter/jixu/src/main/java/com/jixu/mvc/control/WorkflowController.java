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
import com.jixu.common.pojo.MsgLog;
import com.jixu.common.pojo.Users;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.JobsType;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.WorkFlowSteps;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.JsonUtil;
import com.jixu.common.util.Pages;
import com.jixu.dao.intf.JobsHisDao;
import com.jixu.dao.intf.MsgLogDao;
import com.jixu.data.service.impl.OutDataCenter;
import com.jixu.mvc.control.model.WfFailedData;
import com.jixu.mvc.control.service.PeriodRunService;
import com.jixu.mvc.control.service.WorkFlowService;
import com.orm.obj.JobsHis;
import com.orm.obj.PortfolioHdr;

@Controller
public class WorkflowController extends ExceptionControl{
	
	@Resource(name="workFlowService")
	private WorkFlowService wfService;
	
	@Resource(name="commfuncService")
	private CommFuncService commService;
	
	@Resource(name="periodRunService")
	private PeriodRunService periodRunService;
	
	@Autowired
	private OutDataCenter outDataCenter;
	
	@Autowired
	private JobsHisDao jobsHisDao;
	
	@Autowired
	private MsgLogDao msgLogDao;
	
	@RequestMapping(value="/workflow.do", method = RequestMethod.GET, name="每日流程-页面显示")
	public String workflow() {
		return "workflow";
	}
	
	@RequestMapping(value="/workflow/WFSteps.do", method = RequestMethod.POST)  
    public void wfSteps(HttpServletResponse response) {
		/*HashMap<Integer, String> steps = new HashMap<Integer, String>();
		steps.put(0, "市场数据处理");
		steps.put(1, "交易数据处理");
		steps.put(501, "到期自动作业");
		steps.put(502, "交易估值关帐");
		steps.put(503, "每日会计作业");
		steps.put(504, "每月会计作业");
		steps.put(505, "基金净值计算");
		steps.put(506, "会计总账关帐");*/
		
		HashMap<Integer, String> steps=WorkFlowSteps.operations;
			
		JsonUtil.writeObjToResponse(response, steps);
	}
	
	@RequestMapping(value="/workflow/updateWFStatus.do", method = RequestMethod.POST)  
    public void updateWFStatus(@RequestParam(value = "category", required = true) int category,
    					  	   @RequestParam(value = "ports", required = true) String ports,
    					  	   @RequestParam(value = "date", required = true) String date,
    					  	   @RequestParam(value = "status", required = true) int status,
    					  	   HttpServletResponse response) {
		List<String> ports_list = JSONObject.parseArray(ports, String.class);
		
		String[] portnums=new String[ports_list.size()];
		for(int i=0;i<ports_list.size();i++){
			portnums[i]=ports_list.get(i).toString();
		}
		
		if(category==WorkFlowSteps.generalClose){
			outDataCenter.exeDataSender(status,portnums,new java.sql.Date(DateUtil.convertStr2Date2(date).getTime()));
		}
		
		wfService.updateWorkFlowStep(category, ports_list, DateUtil.convertStr2Date2(date), status);
		JsonUtil.writeObjToResponse(response, "");
	}
	
	@RequestMapping(value="/workflow/currentWFStatus.do", method = RequestMethod.POST)  
    public void currentWFStatus(@RequestParam(value = "ports", required = true) String ports,
    					  	   @RequestParam(value = "date", required = true) String date,
    					  	   HttpServletRequest request,
    					  	   HttpServletResponse response) {
		List<Object> ports_list = JsonUtil.stringToList(ports);
		
		String[] portsInt=new String[ports_list.size()];
		for(int i=0;i<ports_list.size();i++){
			portsInt[i]=ports_list.get(i).toString();
		}
		
		Date ledgerDate=DateUtil.convertStr2Date2(date);
		
		ResultContainer container =new ResultContainer();
		HashMap<String, Object> wf=wfService.fetchCurWorkFlow(portsInt, ledgerDate, JixuUserUtil.getUserIDbyRequest(request), container);
		if(container.isCorrectFlag()){
			JsonUtil.writeObjToResponse(response, wf);
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
	
	@RequestMapping(value="/workflow/getAccountingDate.do", method = RequestMethod.POST)  
    public void getAccountingDate(@RequestParam(value = "ports", required = true) String ports, 
    							HttpServletResponse response) {
		List<Object> ports_list = JsonUtil.stringToList(ports);
		String[] portsInt=new String[ports_list.size()];
		for(int i=0;i<ports_list.size();i++){
			portsInt[i]=ports_list.get(i).toString();
		}
		
		ResultContainer container=new ResultContainer();
		Date ledgerDate=wfService.fetchNextLedgerDate(portsInt, container);
		
		
		if(container.isCorrectFlag()){
			JsonUtil.writeObjToResponse(response, DateUtil.convertD2YYYY_MM_DD(ledgerDate));
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
	@RequestMapping(value="/workflow/errorReport.do", method = RequestMethod.POST, name="每日流程-错误报告")  
    public void errorReport(@RequestParam(value = "category", required = true) int category,
    					    @RequestParam(value = "group", required = true) int group,
    						@RequestParam(value = "ports", required = true) String ports,
    					  	@RequestParam(value = "date", required = true) String date,
    					  	HttpServletResponse response) {
		List<Object> ports_list = JsonUtil.stringToList(ports);
		String[] portsInt=new String[ports_list.size()];
		for(int i=0;i<ports_list.size();i++){
			portsInt[i]=ports_list.get(i).toString();
		}
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Object> data = new ArrayList<Object>();
		
		//TODO-当group为3时，展示job的error内容
		if (group == 3){
			List<String> titles = Arrays.asList("作业编号", "作业名称", "组合编号", "所属组合", "错误描述", "操作人", "失败日期");
			result.put("titles", titles); 
			List<JobsHis> errorList=wfService.loadErrorJobsHis(portsInt, DateUtil.convertStr2Date(date.replaceAll("-", "")), category);
			
			for(JobsHis jobshis:errorList){
				List<Object> item = new ArrayList<Object>();
				item.add(jobshis.getId());
				item.add(JobsType.jobsTypeHM.get(jobshis.getJobID()));
				item.add(jobshis.getPortfolio().getId());
				item.add(jobshis.getPortfolio().getShortName());
				item.add(jobshis.getFailedDesc());
				item.add("-");	//to-do pending
				item.add(DateUtil.convertD2YYYY_MM_DD(jobshis.getLedgerDate()));
				data.add(item);
			}
		}else{	
	        List<String> titles = Arrays.asList("编号", "数据来源", "所属组合", "所在文件", "错误描述","消息", "操作人", "失败时间","是否忽略");
	        result.put("titles", titles);    
	        List<WfFailedData> failedList=wfService.loadErrorMsgLog(portsInt, category, DateUtil.convertStr2Date2(date));
	        for(WfFailedData failedData:failedList){
	        	List<Object> item1 = new ArrayList<Object>();
	        	item1.add(failedData.getId());
	        	item1.add(failedData.getSource());
	        	item1.add(failedData.getPortname());
	        	item1.add(failedData.getFilename());
	        	item1.add(failedData.getErrDesc());
	        	item1.add(failedData.getMsg());
	        	item1.add(failedData.getOperationor());
	        	item1.add(DateUtil.convertD2YYYY_MM_DDHHMMSS(failedData.getOperationTime()));
	        	if(failedData.isIngore()){
	        		item1.add("是");
	        	}else{
	        		item1.add("否");
	        	}
	        	data.add(item1);
	        }
		}
		
        result.put("data", data);
        JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/workflow/errorIgnore.do", method = RequestMethod.POST, name="每日流程-操作错误忽略")  
    public void errorIgnore(@RequestParam(value = "id", required = true) int errorID,
    						@RequestParam(value = "ignore", required = true) boolean ignore,
    					  	HttpServletResponse response){
		wfService.ignoreErrorMsg(errorID, ignore);
		JsonUtil.writeObjToResponse(response, "");
	}
	
	@RequestMapping(value="/workflow/operateLog.do", method = RequestMethod.POST, name="每日流程-操作日志")  
    public void operateLog(@RequestParam(value = "category", required = true) int category,
    					   @RequestParam(value = "ports", required = true) String ports,
    					   @RequestParam(value = "date", required = true) String date,
    					   @RequestParam(value = "num", required = true) int num,
    					   HttpServletResponse response) {
		if(category>9){
			HashMap<String, Object> result = new HashMap<String, Object>();
	        List<String> titles = Arrays.asList("编号","作业名称","投资组合","状态","操作人","操作时间","错误信息");
	        result.put("titles", titles);
	        List<Object> data = new ArrayList<Object>();
	        
	        List<Object> portList = JsonUtil.stringToList(ports);
	        String[] portsArr=new String[portList.size()];
			for(int i=0;i<portList.size();i++){
				portsArr[i]=portList.get(i).toString();
			}
			Date ledgerDate=DateUtil.convertStr2Date(date.replaceAll("-", ""));
			
			List<JobsHis> list=jobsHisDao.loadJobsHis(portsArr,ledgerDate);
		
			for(JobsHis job:list){
				List<Object> item1 = new ArrayList<Object>();
				item1.add(job.getId());
				item1.add(JobsType.jobsTypeHM.get(job.getJobID()));
				
				if(job.getPortfolio()==null){
					item1.add("-");
				}else{
					PortfolioHdr portfolio=commService.loadPortfolio(job.getPortfolio().getId());
					item1.add(portfolio.getShortName());
				}
				
				if(job.isStatus()){
					item1.add("成功");
				}else{
					item1.add("失败");
				}
				
				Users user=commService.loadUserByID(job.getUserID());
				item1.add(user.getUsername());
				item1.add(DateUtil.convertD2YYYY_MM_DDHHMMSS(job.getOperationTime()));
				
				String errorinfo=job.getFailedDesc();
				if(errorinfo.length()>=80){
					item1.add(errorinfo.substring(0, 79));
				}else{
					item1.add(errorinfo);
				}
				data.add(item1);
			}
	        result.put("data", data);
	        JsonUtil.writeObjToResponse(response, result);
		}else{
			HashMap<String, Object> result = new HashMap<String, Object>();
	        List<String> titles = Arrays.asList("编号","操作类别","数据源","所在组合","状态","操作人","操作时间","错误信息");
	        result.put("titles", titles);
	        List<Object> data = new ArrayList<Object>();
	        
	        List<Object> portList = JsonUtil.stringToList(ports);
	        String[] portsArr=new String[portList.size()];
			for(int i=0;i<portList.size();i++){
				portsArr[i]=portList.get(i).toString();
			}
			Date ledgerDate=DateUtil.convertStr2Date(date.replaceAll("-", ""));
			List<MsgLog> msgLogList=new ArrayList<MsgLog>();
	        int totalPages=0;
			if(SystemSelect.getSystemDBFrameWork()==1){
				int pageSize=50;
				Page<MsgLog> page=msgLogDao.loadPageHelperMsgLog(PageHelper.startPage(num, pageSize),portsArr, ledgerDate, category);
				msgLogList=page.getResult();
				totalPages=page.getPages();
			}else{
				//hibernate分页
		        Pages<MsgLog> pages=new Pages<MsgLog>(num);
		        pages.setPageMaxRecords(50);
		        pages=msgLogDao.loadMsgLog(pages, portsArr, ledgerDate, category);
		        msgLogList=pages.getResultList();
		        totalPages=pages.getTotalPages();
			}
			
			HashMap<Integer, String> usermap=new HashMap<Integer, String>();
			HashMap<Integer, String> sourcemap=new HashMap<Integer, String>();
			
			for(MsgLog log:msgLogList){
				List<Object> item1 = new ArrayList<Object>();
				item1.add(log.getLogid());
				item1.add(WorkFlowSteps.operations.get(log.getOperationType()));
			     
				if(log.getSource()==null){
					item1.add("-");
				}else{
					int sourceid=log.getSource().getSourceID();
					if(sourcemap.containsKey(sourceid)){
						item1.add(sourcemap.get(sourceid));
					}else{
						String sourcename=log.getSource().getSourceName();
						if(sourcename.equals("")){
							sourcename=log.getSource().getSourceDesc();
						}
						sourcemap.put(sourceid, sourcename);
						item1.add(sourcename);
					}
				}
				
				if(log.getPortfolio().equals("") || log.getPortfolio().equals("0")){
					item1.add("-");
				}else{
					PortfolioHdr portfolio=commService.loadPortfolio(log.getPortfolio());
					item1.add(portfolio.getShortName());
				}
				
				if(log.isStatus()){
					item1.add("成功");
				}else{
					if(log.isIngore()){
						item1.add("失败忽略");
					}else{
						item1.add("失败");
					}
				}
				
				item1.add(log.getUpdateUser());
				
				item1.add(DateUtil.convertD2YYYY_MM_DDHHMMSS(log.getOperationTime()));
				
				String errorinfo=log.getErrorinfo();
				if(errorinfo.length()>=30){
					item1.add(errorinfo.substring(0, 29));
				}else{
					item1.add(errorinfo);
				}
				
				data.add(item1);
			}
			result.put("pages", totalPages);
	        result.put("data", data);
	        JsonUtil.writeObjToResponse(response, result);
		}
	}
	
	@RequestMapping(value="/workflow/periodRunJob.do", method = RequestMethod.POST, name="每日流程-批量做账")
	public void runAllJobs(@RequestParam(value = "startDate", required = true) String start,
						   @RequestParam(value = "endDate", required = true) String end,
						   @RequestParam(value = "ports", required = true) String ports,
						   HttpServletRequest request,
						   HttpServletResponse response){
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("做账日期","作业名称", "投资组合", "执行结果", "详细信息");		
		result.put("titles", titles);
		
		//做账日期和投资组合
		int userID=JixuUserUtil.getUserIDbyRequest(request);
		String portNum=ports;
		List<String> ports_list = new ArrayList<String>();
		ports_list.add(portNum);
		Date startDate=DateUtil.convertStr2Date2(start);
		Date endDate=DateUtil.convertStr2Date2(end);
		
		List<Object> data=periodRunService.autoRunJob(userID, ports_list, startDate, endDate);
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/workflow/periodRedoJob.do", method = RequestMethod.POST, name="每日流程-批量撤账")
	public void redoAllJobs(@RequestParam(value = "startDate", required = true) String start,
						   @RequestParam(value = "endDate", required = true) String end,
						   @RequestParam(value = "ports", required = true) String ports,
						   HttpServletRequest request,
						   HttpServletResponse response){
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("做账日期","作业名称", "投资组合", "执行结果", "详细信息");		
		result.put("titles", titles);
		
		//做账日期和投资组合
		int userID=JixuUserUtil.getUserIDbyRequest(request);
		String portNum=ports;
		List<String> ports_list = new ArrayList<String>();
		ports_list.add(portNum);
		Date startDate=DateUtil.convertStr2Date2(start);
		Date endDate=DateUtil.convertStr2Date2(end);
		
		List<Object> data=periodRunService.autoRedoJob(userID, ports_list, startDate, endDate);
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}
}
