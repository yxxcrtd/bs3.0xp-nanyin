package com.jixu.adapter.msg.service.config;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.quartz.SchedulerException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONObject;
import com.jixu.adapter.common.func.AdapterSysInit;
import com.jixu.adapter.control.service.impl.AutoJobAction;
import com.jixu.adapter.model.ExpInterfaceSourceDto;
import com.jixu.adapter.msg.service.impl.MsgExecuteService;
import com.jixu.adapter.types.ActiveType;
import com.jixu.adapter.types.InterfaceAutoType;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.tool.QuartzManager;
import com.jixu.common.type.JobModel;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.util.AESUtil;
import com.jixu.common.util.DateUtil;

@Service
public class ExpInterfaceSourceCfgMsg extends MsgExecuteService<ExpInterfaceSourceDto>{
	
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
		ExpInterfaceSourceDto dto=JSONObject.parseObject(msgcontainer.getJsonMsg(), ExpInterfaceSourceDto.class);
		validateDto(msgcontainer, dto);
		
		if(!msgcontainer.isCorrectFlag())
			return;
		
		String operation=dto.getOperation();
		
		InterfaceSource oldsource=null;
		if(dto.getId()>0){
			//判断是否有
			oldsource=interfaceSourceDao.loadEntity(InterfaceSource.class, dto.getId());
		}
//		
//		String name=getFieldValue(fieldsHM, "name");	//导出名称
//		String desc=getFieldValue(fieldsHM, "desc");	//导出描述
//		//"resource"
//		String deliverType=getFieldValue(fieldsHM, "deliverType");	//传递类型 数据库，文件，MQ
//		String fileformat=getFieldValue(fieldsHM, "fileformat");	//文件类型	excel, csv, txt
//		String fileencode=getFieldValue(fieldsHM, "fileencode");	//文件编码格式
//		String fileloc=getFieldValue(fieldsHM, "fileloc");	//导出文件地址
//		String filename=getFieldValue(fieldsHM, "filename");	//导出文件名
//		String multifiles=getFieldValue(fieldsHM, "multifiles");	//是否支持多文件导出
//		String tablename=getFieldValue(fieldsHM, "tablename");	//excel，导出表名
//		String startcol=getFieldValue(fieldsHM, "startcol");	//开始行
//		String delimiter=getFieldValue(fieldsHM, "delimiter");	//分割标记 csv文件 逗号，竖线等
//		String end=getFieldValue(fieldsHM, "end");		//结束标记位
//		//"includeCol":false
//		String user=getFieldValue(fieldsHM, "user");	//用户名
//		String pwd=getFieldValue(fieldsHM, "pwd");		//密码
//		String holiday=getFieldValue(fieldsHM, "holiday");	//节假日
//		String title=getFieldValue(fieldsHM, "title");	//是否包含标题header
//		String autodo=getFieldValue(fieldsHM, "autodo");	//是否自动导出
//		String autoDateFormat=getFieldValue(fieldsHM, "autoDateFormat");	//自动导出类型	
//		String fromDay=getFieldValue(fieldsHM, "fromDay");	//第N天
//		String autoTime=getFieldValue(fieldsHM, "autoTime");	//自动导出时间
//		//String moveto=getFieldValue(fieldsHM, "moveto");
//		
//		String dbformat="11";	//数据库类型
//		if(fieldsHM.containsKey("dbFormat"))
//			dbformat=getFieldValue(fieldsHM, "dbFormat");	
//		
//		String dbURL="";	//数据库地址
//		if(fieldsHM.containsKey("dbURL"))	
//			dbURL=getFieldValue(fieldsHM, "dbURL");
//		
//		String dbTable="";	//目标数据库
//		if(fieldsHM.containsKey("dbTable"))
//			dbTable=getFieldValue(fieldsHM, "dbTable");
//		
//		String dbSQL="";	//执行SQL
//		if(fieldsHM.containsKey("dbSQL"))
//			dbSQL=getFieldValue(fieldsHM, "dbSQL");
//
//		String dependency="0";	//依赖上游适配器
//		if(fieldsHM.containsKey("dependency"))
//			dependency=getFieldValue(fieldsHM, "dependency");
//		
//		String startDate="";	//第一次执行自动导出的日期
//		if(fieldsHM.containsKey("startDate"))
//			startDate=getFieldValue(fieldsHM, "startDate");
//		
//		String startTime="";	//第一次执行自动导出的时间
//		if(fieldsHM.containsKey("startTime"))
//			startTime=getFieldValue(fieldsHM, "startTime");
		String startDate=dto.getStartDate();
		String startTime=dto.getStartTime();
		
		InterfaceSource is=new InterfaceSource();
		is.setSourceName(dto.getName());
		is.setSourceDesc(dto.getDesc());
		is.setSourceType(0);	//导出适配器0
		is.setSourceSecType(dto.getCategory());
		is.setTranType(0);
		is.setChannelType(dto.getDeliverType());
		is.setChannelAccount(dto.getUser());
		is.setChannelPWD(AESUtil.Encrypt(dto.getPwd()));
		is.setEnCodeType(dto.getFileencode());
		//is.setFileMultiFlag(Boolean.valueOf(multifiles));
		is.setTableStartLine(Integer.parseInt(dto.getStartcol()));
		is.setDependency(dto.getDependency());
		
		if(dto.isDepNoDataGoFlag()) {
			//空数据是否执行依赖下去
			is.setDepNoDataGoFlag(1);
		}else
			is.setDepNoDataGoFlag(0);
		is.setOutNoDataGenFileFlag(dto.isOutNoDataGenFileFlag());
		is.setTableSpilitChar(dto.getDelimiter());
		is.setTableEndChar(dto.getEnd());
		is.setHoliday(dto.getHoliday());
		is.setAutoFlag(dto.isAutodo());
		is.setAutoType(dto.getAutoDateFormat());
		is.setAutoNDays(Integer.parseInt(dto.getFromDay()));
		is.setAutoTime(dto.getAutoTime());
		//is.setTargeDBTable(dto);
		is.setTablename(dto.getTablename());
		is.setBatchExeFlag(true);
		is.setActiveStatus(ActiveType.active);
		is.setStartDate(startDate);
		is.setStartTime(startTime);
		is.setWriteSpilitChar(dto.getWriteSplit());
		is.setBackupPath(dto.getMoveto());
		
		if(is.getChannelType()==InterfaceConfig.dbType) {
			is.setFileType(dto.getDbFormat());
			is.setFilepath(dto.getDbURL());
			is.setDbSQL(dto.getDbSQL());
		}else {
			is.setFileType(dto.getFileformat());
			is.setFilepath(dto.getFileloc());
			is.setFileName(dto.getFilename());
		}
		is.setOutFileType(dto.getExpFileformat());
		is.setOutFilepath(dto.getExpFileloc());
		is.setOutFileName(dto.getExpFilename());
		is.setOutFileEnCodeType(dto.getExpFileencode());
		is.setOutFileTitleFlag(dto.isIncludeCol());
		is.setAfterCallClass(dto.getAfterCallClass());
		is.setProjectGroup(AdapterSysInit.AdapterProjectGroup);
		is.setEndChar(dto.getEndChar());
		
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
		
		if(is.getBackupPath()==null || is.getBackupPath().trim().equals("") || is.getChannelType()==4){
			is.setBackupFlag(false);
		}else{
			is.setBackupFlag(true);
		}
		
		try{
			if(operation.toUpperCase().equals(add)){
				interfaceSourceDao.saveEntityBean(is);
				msgcontainer.setObjID(is.getSourceID());
				msgcontainer.setEntityObj(is);
				
				addAutoJob(is);
			}else{
				BeanUtils.copyProperties(is, oldsource,new String[]{"sourceID","expSourceClass","fileMultiFlag","targeDBTable", "customBeforeExecute", "customAfterExecute"});
				
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
