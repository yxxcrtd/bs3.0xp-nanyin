package com.jixu.adapter.service;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.joyintech.framework.file.SafelyFile;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jixu.adapter.custom.service.CustomExecuteService;
import com.jixu.adapter.dao.intf.AdapterUniqueRunDao;
import com.jixu.adapter.dao.intf.FileBackupRecordDao;
import com.jixu.adapter.dao.intf.InterfaceAdapterDao;
import com.jixu.adapter.dbexe.ExecuteDBScripts;
import com.jixu.adapter.reader.ReadDataFile;
import com.jixu.adapter.types.CustomParams;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.tool.JixuContextBeansUtil;
import com.jixu.common.util.DateUtil;
import com.orm.obj.AdapterUniqueRun;
import com.orm.obj.FileBackupRecord;
import com.orm.obj.InterfaceLog;

@Component
public class AdapterComFunction {
	
	@Autowired
	private AdapterUniqueRunDao adapterUniqueRunDao;
	
	@Autowired
	private FileBackupRecordDao fileBackupRecordDao;

	@Resource(name="interfaceAdapterDao")
	private InterfaceAdapterDao interfaceSourceDao;
	
	private Map<Integer, ReadDataFile> msgMap = new HashMap<Integer, ReadDataFile>();
	
	private Map<Integer, ExecuteDBScripts> sqlMap=new HashMap<Integer, ExecuteDBScripts>();
	
	private static final Logger logger = LoggerFactory.getLogger(AdapterComFunction.class);
	
	/**
	 * 判定改适配器是否在运行中(数据库和内存的变量双重判定)
	 * @param sourceID
	 * @return
	 */
	public boolean checkRunning(int sourceID, Set<Integer> runningSet){
		boolean notRunFlag=false;
		if(runningSet.contains(sourceID)){
			logger.info("适配器冲突，正在运行中(0): " + sourceID);
			return notRunFlag;
		}
		
		boolean insertFlag=false;
		
		try{
			AdapterUniqueRun aur=adapterUniqueRunDao.loadEntity(AdapterUniqueRun.class, sourceID);
			String ipaddress="";
			try{
				InetAddress ia = InetAddress.getLocalHost();
				ipaddress = ia.getHostName();//获取计算机主机名
			}catch(UnknownHostException ue){
				logger.error("无法获取该服务器的IP地址");
				return false;
			}
			
			if(aur!=null){
				//在数据库该ID是否在运行
				String dbipaddress=aur.getOpMachineName();
				if(dbipaddress.equals(ipaddress)){
					if(!runningSet.contains(sourceID)){
						//如果是本服务器运行了该适配器，并且在内存里发现没有运行适配器，则判定为上一次适配器异常关闭，重新运行适配器
						adapterUniqueRunDao.deleteAdapterUniqueRun(aur);
						logger.info("删除适配器数据库唯一判定标记(判定为残留错误数据): " + sourceID);
						insertFlag=true;
					}else{
						//如果正在运行，并且缓存中有适配器，则判定正在运行
						logger.info("适配器冲突，正在运行中(1): " + sourceID);
					}
				}else{
					//如果是其它服务器运行的适配器，直接判定冲突，正在运行
					logger.info("适配器冲突，正在运行中(2): " + sourceID);
				}
			}else{
				//如果没有在运行，往数据库插入数据，利用主键唯一性，不允许同时插入两条相同主键的值，用此来判定唯一性
				insertFlag=true;
			}
			
			if(insertFlag){
				//如果没有在运行，往数据库插入数据，利用主键唯一性，不允许同时插入两条相同主键的值，用此来判定唯一性
				AdapterUniqueRun newAur=new AdapterUniqueRun();
				newAur.setAdapterID(sourceID);
				newAur.setOpMachineName(ipaddress);
				newAur.setStartTime(DateUtil.convertD2YYYY_MM_DDHHMMSS(new Date()));
				try{
					adapterUniqueRunDao.saveAdapterUniqueRun(newAur);
					logger.info("增加适配器数据库唯一判定标记: " + sourceID);
					notRunFlag=true;
				}catch(Exception e){
					logger.error("Error", e);
					logger.info("适配器冲突，正在运行中(3): " + sourceID);
				}
			}
			
		}catch(Exception e){
			logger.error("适配器"+sourceID+"判定运行异常错误");
			logger.error("Error", e);
			
		}
		
		if(notRunFlag){
			//如果判定运行该适配器，则加入到内存中。和数据库一起来判定该适配器是否正在运行
			logger.info("增加适配器内存唯一判定标记: " + sourceID);
			runningSet.add(sourceID);
		}
		
		return notRunFlag;
	}
	
	/**
	 * 读取哪个类型文件
	 * @param readFileType
	 * @return
	 */
	public ReadDataFile createReadFileBean(int readFileType) {
		if(msgMap==null || msgMap.size()==0) {
			Map<String, ReadDataFile> beanMap = JixuContextBeansUtil.getApplicationContext().getBeansOfType(ReadDataFile.class);
			for (ReadDataFile fileBean : beanMap.values()) {
				msgMap.put(fileBean.getFileType(), fileBean);
	        }
		}
		return msgMap.get(readFileType);
	}
	
	/**
	 * 获取数据库的执行类
	 * @param dbtype
	 * @return
	 */
	public ExecuteDBScripts createExeDB(int dbtype) {
		Map<String, ExecuteDBScripts> dbScriptsBeans = JixuContextBeansUtil.getApplicationContext().getBeansOfType(ExecuteDBScripts.class);
		if(sqlMap==null || sqlMap.size()==0) {
			for (ExecuteDBScripts sqlbean : dbScriptsBeans.values()) {
				sqlMap.put(sqlbean.dbType(), sqlbean);
	        }
		}
		
		return sqlMap.get(dbtype);
	}
	
	/**
	 * @param userID
	 * @param source
	 * @param ledgerDate
	 * @return
	 */
	public HashMap<String, Object> initGlobalVal(int userID, InterfaceSource source, Date ledgerDate){
		HashMap<String, Object> initGlobalValHM=new HashMap<String, Object>();
		initGlobalValHM.put(InterfaceConfig.Global_Folder, source.getFilepath());
		initGlobalValHM.put(InterfaceConfig.Global_FileName, source.getFileName());
		initGlobalValHM.put(InterfaceConfig.Global_LedgerDate, ledgerDate);
		initGlobalValHM.put(InterfaceConfig.Global_ExeUserID, userID);
		initGlobalValHM.put(InterfaceConfig.Global_AdapterID, source.getSourceID());
		
		return initGlobalValHM;
	}
	
	/**
	 * 文件备份共通
	 * @param sourceid
	 * @param newFolder
	 * @param fileList
	 * @return
	 */
	public String moveFiles(int sourceid, String newFolder, List<String> fileList){
		String errormsg="";
		if(fileList.size()>0){
			String dateStr=DateUtil.convertD2YYYYMMDD(new Date());
			String targetPath=newFolder+"/"+dateStr;
			try{
				File targetFolder =new SafelyFile(targetPath);
				
				if(!targetFolder.exists()){
					if(!targetFolder.mkdirs()){
						errormsg="创建文件夹失败: " + targetPath;
						logger.info(errormsg);
						return errormsg;
					}
				}
				
				for(String sourceFilePath:fileList){
					File sourceFile=new SafelyFile(sourceFilePath);
					
					String targetFilePath=targetPath+"/"+sourceFile.getName();
					File targetFile=new SafelyFile(targetFilePath);
					if(targetFile.exists()){
						/*if(targetFile.delete()){
							errormsg="删除文件夹失败: " + targetPath+"/"+sourceFile.getName();
							logger.info(errormsg);
							return errormsg;
						}*/
						//判断文件已经存在就在原有基础上加上时分秒作为新的文件名
						String date=DateUtil.convertDate2StrHHMMSS(new Date()).replaceAll(":", "");
						String fileName=sourceFile.getName();
						String finalFileName=fileName+"."+date;
						targetFilePath=targetPath+"/"+finalFileName;
						targetFile=new SafelyFile(targetFilePath);
					}
					if(!sourceFile.renameTo(targetFile)){
						errormsg="备份文件失败("+sourceid+"): " + sourceFilePath + " | " + targetFilePath;
						logger.info(errormsg);
						return errormsg;
					}
					
					FileBackupRecord backup=new FileBackupRecord();
					backup.setSourceID(sourceid);
					backup.setActionTime(new Date());
					backup.setFromFilePath(sourceFilePath);
					backup.setToFilePath(targetFilePath);
					fileBackupRecordDao.createBackupRecord(backup);
				
					logger.info("移动文件("+sourceid+"): " + sourceFilePath + " 备份到 " + targetFilePath);
				}
			}catch(Exception e){
				logger.error("Error", e);
				return e.getMessage();
			}
		}
		return errormsg;
	}
	
	public ResultContainer executeCustom(boolean beforeFlag, int sourceID, HashMap<String, Object> params, InterfaceLog ilog) {
		InterfaceSource source=interfaceSourceDao.loadEntity(InterfaceSource.class, sourceID);
		CustomParams customparams=new CustomParams();
		customparams.setSource(source);
		customparams.setParamsMap(params);
		customparams.setIlog(ilog);
		ResultContainer container=new ResultContainer();
		if(beforeFlag) {
			//适配器执行前处理逻辑
			if(StringUtils.isNotEmpty(source.getCustomBeforeExecute())) {
				CustomExecuteService service=(CustomExecuteService)JixuContextBeansUtil.getBean(source.getCustomBeforeExecute());
				container=service.executeCustom(customparams);
			}
		}else {
			//适配器执行后处理逻辑
			if(StringUtils.isNotEmpty(source.getCustomAfterExecute())) {
				CustomExecuteService service=(CustomExecuteService)JixuContextBeansUtil.getBean(source.getCustomAfterExecute());
				container=service.executeCustom(customparams);
			}
		}
		return container;
	}
}
