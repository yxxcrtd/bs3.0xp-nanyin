package com.jixu.adapter.common.func;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.adapter.control.service.impl.AutoJobAction;
import com.jixu.adapter.dao.intf.InterfaceAdapterDao;
import com.jixu.adapter.service.parent.AfterCallClassesAbstract;
import com.jixu.adapter.types.InterfaceAutoType;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.tool.JixuContextBeansUtil;
import com.jixu.common.tool.QuartzManager;
import com.jixu.common.type.JobModel;
import com.jixu.common.util.DateUtil;

@Component
public class AdapterSysInit implements CommandLineRunner{
	
	private static final Logger logger = LoggerFactory.getLogger(AdapterSysInit.class);
	
	@Resource(name="interfaceAdapterDao")
	private InterfaceAdapterDao interfaceSourceDao;
	
	@Resource(name="quartzManager")
	private QuartzManager jobManager;
	
	public static HashMap<String, AfterCallClassesAbstract> callExeClassMap;
	
	public static HashMap<String, String> callExeClassNameMap;
	
	public static String AdapterProjectGroup;
	
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void run(String... arg0) throws Exception {
		initAutoJob();
		initCallClasses();
	}
	
	private void initAutoJob(){
		logger.info("开始初始化自动运行任务");
		
		List<InterfaceSource> isList=interfaceSourceDao.loadAutoExecuteJobs();
		
		for(InterfaceSource source:isList){
			JobModel<AutoJobAction> job = new JobModel<AutoJobAction>();
			job.setJobID(source.getSourceID());
			job.setJobName(source.getSourceName());
			job.setJobGroup(AdapterSysInit.AdapterProjectGroup);
			job.setCronExp(InterfaceAutoType.genAutoJobTime(source.getAutoType(), source.getAutoNDays(), source.getAutoTime()));
			Date jobStartTime=null;
			if(source.getStartDate()!=null && source.getStartTime()!=null){
				jobStartTime=DateUtil.convertStr2Time(source.getStartDate().trim() + " " + source.getStartTime().trim());
			}
			job.setJobStartTime(jobStartTime);
			job.setJobClass(AutoJobAction.class);
			try{
				jobManager.modifyJobTime(job);
			}catch(SchedulerException se){
				logger.error("Error", se);
			}
		}
		
		jobManager.startJobs();
		logger.info("初始化自动运行任务结束");
	}
	
	private void initCallClasses() {
		Map<String, AfterCallClassesAbstract> maps=JixuContextBeansUtil.getBeansOfType(AfterCallClassesAbstract.class);
		callExeClassMap=new HashMap<String, AfterCallClassesAbstract>();
		callExeClassNameMap=new HashMap<String, String>();
		callExeClassNameMap.put("0", "-");
		for (Entry<String, AfterCallClassesAbstract> entry : maps.entrySet()) {
			callExeClassMap.put(entry.getKey(), entry.getValue());
			callExeClassNameMap.put(entry.getKey(), entry.getValue().callClassName());
		}
	}

	public String getAdapterProjectGroup() {
		return AdapterProjectGroup;
	}

	@Value("${jixu.project.group}")
	public void setAdapterProjectGroup(String adapterProjectGroup) {
		AdapterProjectGroup = adapterProjectGroup;
	}
}
