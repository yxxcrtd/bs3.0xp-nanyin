package com.jixu.adapter.msg.service.config;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.quartz.SchedulerException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.jixu.adapter.common.func.AdapterSysInit;
import com.jixu.adapter.control.service.impl.AutoJobAction;
import com.jixu.adapter.msg.service.impl.MsgExecuteService;
import com.jixu.adapter.types.ActiveType;
import com.jixu.adapter.types.InterfaceAutoType;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.tool.QuartzManager;
import com.jixu.common.type.JobModel;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.util.AESUtil;
import com.jixu.common.util.DateUtil;
import com.jixu.framework.msg.MsgError;

/** 
 * @ClassName 		AssembleBNK
 * @author  		Pan Wei 
 * @Date 			2016年8月12日 下午2:01:02 	
 * @Description		[返回关联银行账户]  
 */
@Service
public class InterfaceSourceCfgMsg extends MsgExecuteService{
	
	@Resource(name="quartzManager")
	private QuartzManager jobManager;
	
	@Override
	public String msgHeader() {
		return null;
	}
	
	@Override
	public String msgFields() {
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		HashMap<String, Object> fieldsHM=msgcontainer.getFieldsHM();
		
		String operation=getFieldValue(fieldsHM, MsgFields.operation);
		
		InterfaceSource oldsource=null;
		if(fieldsHM.containsKey("id")){
			int sourceid=Integer.valueOf(Integer.valueOf(getFieldValue(fieldsHM, "id")));
			oldsource=interfaceSourceDao.loadEntity(InterfaceSource.class, sourceid);
			
			if(operation.equals(delete)){
				if(oldsource==null){
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, getFieldValue(fieldsHM, MsgFields.header),String.valueOf(delete)));
				}else{
					interfaceSourceDao.deleteEntity(oldsource);
				}
				return;
			}
		}
		
		String name=getFieldValue(fieldsHM, "name");
		String desc=getFieldValue(fieldsHM, "desc");
		String categoryStr=getFieldValue(fieldsHM, "category");
		String typeStr=getFieldValue(fieldsHM, "type");
		String trantypeStr=getFieldValue(fieldsHM, "trantype");
		
		String category="0";
		if(categoryStr!=null && !categoryStr.equals(""))
			category=categoryStr;
		
		int type=1;
		if(typeStr!=null && !typeStr.equals(""))
			type=Integer.parseInt(typeStr);
		
		int trantype=1;
		if(trantypeStr!=null && !trantypeStr.equals(""))
			trantype=Integer.parseInt(trantypeStr);
		
		
		String deliverType=getFieldValue(fieldsHM, "deliverType");
		String user=getFieldValue(fieldsHM, "user");
		String pwd=getFieldValue(fieldsHM, "pwd");
		String fileformat=getFieldValue(fieldsHM, "fileformat");
		String fileencode=getFieldValue(fieldsHM, "fileencode");
		String fileloc=getFieldValue(fieldsHM, "fileloc");
		String filename=getFieldValue(fieldsHM, "filename");
		String multifiles=getFieldValue(fieldsHM, "multifiles");
		String tablename=getFieldValue(fieldsHM, "tablename");
		String startcol=getFieldValue(fieldsHM, "startcol");
		String delimiter=getFieldValue(fieldsHM, "delimiter");
		String end=getFieldValue(fieldsHM, "end");
		String holiday=getFieldValue(fieldsHM, "holiday");
		String autodo=getFieldValue(fieldsHM, "autodo");
		String autoDateFormat=getFieldValue(fieldsHM, "autoDateFormat");
		String fromDay=getFieldValue(fieldsHM, "fromDay");
		String autoTime=getFieldValue(fieldsHM, "autoTime");
		String moveto=getFieldValue(fieldsHM, "moveto");
		String afterCallClass=getFieldValue(fieldsHM, "afterCallClass");
		String depNoDataGoFlag=getFieldValue(fieldsHM, "depNoDataGoFlag");
		
		String dbformat="11";
		if(fieldsHM.containsKey("dbFormat"))
			dbformat=getFieldValue(fieldsHM, "dbFormat");
		
		String dbURL="";
		if(fieldsHM.containsKey("dbURL"))
			dbURL=getFieldValue(fieldsHM, "dbURL");
		
		String dbTable="";
		if(fieldsHM.containsKey("dbTable"))
			dbTable=getFieldValue(fieldsHM, "dbTable");
		
		String dbSQL="";
		if(fieldsHM.containsKey("dbSQL"))
			dbSQL=getFieldValue(fieldsHM, "dbSQL");

		String dependency="0";
		if(fieldsHM.containsKey("dependency"))
			dependency=getFieldValue(fieldsHM, "dependency");
		
		String startDate="";
		if(fieldsHM.containsKey("startDate"))
			startDate=getFieldValue(fieldsHM, "startDate");
		
		String startTime="";
		if(fieldsHM.containsKey("startTime"))
			startTime=getFieldValue(fieldsHM, "startTime");
		
		InterfaceSource is=new InterfaceSource();
		is.setSourceName(name);
		is.setSourceDesc(desc);
		is.setSourceType(1);	//导入适配器1
		is.setSourceSecType(category);
		is.setTranType(trantype);
		is.setChannelType(Integer.valueOf(deliverType));
		is.setChannelAccount(user);
		is.setChannelPWD(AESUtil.Encrypt(pwd));
		is.setEnCodeType(Integer.valueOf(fileencode));
		is.setFileMultiFlag(Boolean.valueOf(multifiles));
		String startLineStr=startcol;
		if(!startLineStr.equals("")){
			is.setTableStartLine(Integer.valueOf(startLineStr));
		}
		if(!dependency.equals("")){
			is.setDependency(Integer.valueOf(dependency));
		}
		
		boolean isdepNoDataGoFlag=Boolean.valueOf(depNoDataGoFlag);
		if(isdepNoDataGoFlag)
			is.setDepNoDataGoFlag(1);
		else
			is.setDepNoDataGoFlag(0);
		
		is.setTableSpilitChar(delimiter);
		is.setTableEndChar(end);
		is.setHoliday(holiday);
		is.setAutoFlag(Boolean.valueOf(autodo));
		is.setAutoType(Integer.valueOf(autoDateFormat));
		String ndays=fromDay;
		if(!ndays.equals("")){
			is.setAutoNDays(Integer.valueOf(ndays));
		}
		is.setAutoTime(autoTime);
		is.setBackupPath(moveto);
		is.setTargeDBTable(dbTable);
		is.setTablename(tablename);
		is.setBatchExeFlag(true);
		is.setActiveStatus(ActiveType.active);
		is.setStartDate(startDate);
		is.setStartTime(startTime);
		
		if(is.getChannelType()==InterfaceConfig.dbType){
			is.setFileType(Integer.valueOf(dbformat));
			is.setFilepath(dbURL);
			is.setDbSQL(dbSQL);
		}else{
			is.setFileType(Integer.valueOf(fileformat));
			is.setFilepath(fileloc);
			is.setFileName(filename);
		}
		
		if(is.isAutoFlag()){
			if(startDate!=null && !startDate.trim().equals("") && startTime!=null && !startTime.trim().equals("")){
				Date jobStart=DateUtil.convertStr2Time(startDate.trim() + " " + startTime.trim());
				if(jobStart==null){
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorMsg("开始日期和开始时间格式不正确");
					return;
				}
			}
		}
		
		if(is.getBackupPath()==null || is.getBackupPath().trim().equals("") || is.getChannelType()==3){
			is.setBackupFlag(false);
		}else{
			is.setBackupFlag(true);
		}
		
		is.setAfterCallClass(afterCallClass);
		is.setProjectGroup(AdapterSysInit.AdapterProjectGroup);
		
		
		try{
			if(operation.toUpperCase().equals(add)){
				interfaceSourceDao.saveEntityBean(is);
				msgcontainer.setObjID(is.getSourceID());
				msgcontainer.setEntityObj(is);
				
				addAutoJob(is);
			}else{
				BeanUtils.copyProperties(is, oldsource,new String[]{"sourceID", "expSourceClass", "msg", "customBeforeExecute", "customAfterExecute"});
				
				interfaceSourceDao.updateEntityBean(oldsource);
				if (oldsource != null){
					msgcontainer.setObjID(oldsource.getSourceID());
				}
				msgcontainer.setEntityObj(oldsource);
				if(is.isAutoFlag()){
					modifyAutoJob(oldsource);
				}else{
					removeAutoJob(oldsource);
				}
			}
		}catch(SchedulerException se){
			logger.error("Error", se);
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(se.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}
	
	private JobModel<AutoJobAction> setJobModel(InterfaceSource source){
		JobModel<AutoJobAction> job = new JobModel<AutoJobAction>();
		job.setJobID(source.getSourceID());
		job.setJobName(source.getSourceName());
		job.setJobGroup(AdapterSysInit.AdapterProjectGroup);
		job.setCronExp(InterfaceAutoType.genAutoJobTime(source.getAutoType(), source.getAutoNDays(), source.getAutoTime()));
		job.setJobClass(AutoJobAction.class);
		if(InterfaceAutoType.getByValue(source.getAutoType())==InterfaceAutoType.eachBusinessDay){
			List<Date> hlist=holidayLSDao.loadHolidayList(source.getHoliday());
			job.setHolidayList(hlist);
		}
		
		Date jobStartTime=DateUtil.convertStr2Time(source.getStartDate().trim() + " " + source.getStartTime().trim());
		job.setJobStartTime(jobStartTime);
		
		return job;
	}
	
	private JobModel<AutoJobAction> setRemoveJobModel(InterfaceSource source){
		JobModel<AutoJobAction> job = new JobModel<AutoJobAction>();
		job.setJobID(source.getSourceID());
		job.setJobName(source.getSourceName());
		job.setJobGroup(AdapterSysInit.AdapterProjectGroup);
		job.setJobClass(AutoJobAction.class);
		
		return job;
	}
	
	private void modifyAutoJob(InterfaceSource source) throws SchedulerException{
		if(source.isAutoFlag()){
			JobModel<AutoJobAction> job=setJobModel(source);
			jobManager.modifyJobTime(job);
		}
	}
	
	private void addAutoJob(InterfaceSource source) throws SchedulerException{
		if(source.isAutoFlag()){
			JobModel<AutoJobAction> job=setJobModel(source);
			jobManager.addJob(job);
		}
	}
	
	
	private void removeAutoJob(InterfaceSource source) throws SchedulerException{
		JobModel<AutoJobAction> job=setRemoveJobModel(source);
		jobManager.removeJob(job);
	}

	@Override
	public String messageForm() {
		// TODO Auto-generated method stub
		return "/forms/config/bank_add_form";
	}
	
	public String messageName(){
		return "银行操作";
	}
	
}
