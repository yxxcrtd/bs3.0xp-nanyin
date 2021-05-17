package com.jixu.adapter.service.impl;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;

import com.joyintech.framework.file.SafelyFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.jixu.adapter.dao.intf.InterfaceAdapterDao;
import com.jixu.adapter.dao.intf.InterfaceLogDao;
import com.jixu.adapter.dbexe.ExecuteDBScripts;
import com.jixu.adapter.reader.ReadDBAbstract;
import com.jixu.adapter.reader.ReadDataFile;
import com.jixu.adapter.service.AdapterComFunction;
import com.jixu.adapter.service.ExecuateChildTask;
import com.jixu.adapter.service.parent.AfterCallClassesAbstract;
import com.jixu.adapter.types.AdapterRunStatus;
import com.jixu.adapter.types.CallClassParamsDto;
import com.jixu.adapter.types.SQLMsgTemplate;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.tool.JixuContextBeansUtil;
import com.jixu.common.type.DataCounter;
import com.jixu.common.type.DataReadResult;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.util.ReturnResult;
import com.jixu.framework.msg.MsgAssemble;
import com.jixu.framework.msg.MsgFactory;
import com.orm.obj.InterfaceLog;

/** 
 * @ClassName 		ImportDataService
 * @author  		Pan Wei 
 * @Date 			2016年12月6日 下午1:23:18 	
 * @Description		[]  
 */

@Service("interafaceDataCenter")
public class DataServiceCenter implements InitializingBean, ApplicationContextAware{
	
	@Resource(name="interfaceAdapterDao")
	private InterfaceAdapterDao interfaceSourceDao;
	
	@Autowired
	private InterfaceLogDao interfaceLogDao;
	
	@Resource(name="interfaceAssembleMsg")
	private InterfaceAssembleMsg inerfaceMsg;
	
	@Resource(name="msgAssemble")
	private MsgAssemble msgAssemble;
	
	@Autowired
	private AdapterComFunction adapterComFunction;
	
	@Autowired
	private ExecuateChildTask execuateChildTask;
	
	@Resource(name="taskExecutor")
	private ThreadPoolTaskExecutor taskExecutor; 
	
	@Value("${jixu.adapter.targetDB.dbType}")
	private int targetDBType;
	
	private int acceptFailedNum=3;
	
	private int executeNum=5000;
	
	private Set<Integer> runningSet=Collections.synchronizedSet(new HashSet<Integer>());
	
	private static final Logger logger = LoggerFactory.getLogger(DataServiceCenter.class);
	
	public ResultContainer importData(int userID, int sourceID, Date asOfDate, int workflowStep){
		List<List<Object>> list=new ArrayList<List<Object>>();
		return importData(userID, sourceID, asOfDate, workflowStep,list, null);
	}
	
	/**
	 * 导入数据
	 * @param userID	用户ID
	 * @param sourceID	接口配置编号
	 * @param asOfDate	导入日期
	 * @param workflowStep	工作流步骤
	 * @return	返回导入数据的状态及错误信息
	 */
	public ResultContainer importData(int userID, int sourceID, Date asOfDate, int workflowStep,List<List<Object>> sourceDataList, HashMap<String, Object> outParams){
		ResultContainer container=new ResultContainer();
		//查看当前适配器是否在运行
		boolean checkNotRunFlag=adapterComFunction.checkRunning(sourceID, runningSet);
		if(!checkNotRunFlag){
			return container;
		}

		//如果本适配器的数据已经被导入，则标记为true
		//最后在所有子线程都成功的前提下，把maxupdatetime设值到interfaceparamslastmax表里去
		boolean importDataSuccessFlag=false;
		//所有子线程执行成功的标记位
		boolean childExeSuccessFlag=false;
		//如果父适配器没有数据，是否再调用子适配器	0:不调用	1:调用
		int depNoDataGoFlag=0;
		int filternum=0;
		int totalfilternum=0;
		int readTotalNums=0;	//处理的所有数据量
		
		boolean backupFlag=false;
		
		try{
			String sourcename="";
			String backupFolder="";
			String afterCallClass="";
			//调用下游数据传入的参数
			CallClassParamsDto callDto=new CallClassParamsDto();
			List<String> sourceFilesList=new ArrayList<String>();
			List<MsgContainer> msgcontainerList=new ArrayList<MsgContainer>();
			
			InterfaceLog ilog=new InterfaceLog(userID, sourceID);
			if(outParams!=null) {
				ilog.setOutParams(JSONObject.toJSONString(outParams));
			}
			
			DataCounter dataCounter=new DataCounter();
			dataCounter.setOutParamsMap(outParams);
			DataReadResult dataReadResult=new DataReadResult();
			
			try{
				InterfaceSource source=interfaceSourceDao.loadEntity(InterfaceSource.class, sourceID);
				sourcename=source.getSourceName();
				//调用下游数据的复制
				afterCallClass=source.getAfterCallClass();
				callDto.setAdapterID(source.getSourceID());
				callDto.setAdapterName(source.getSourceName());
				
				backupFlag=source.isBackupFlag();
				if(backupFlag){
					//如果是文件导入，需要备份文件
					backupFolder=source.getBackupPath();
				}
				depNoDataGoFlag=source.getDepNoDataGoFlag();
				
				ReadDataFile fileBean=createReadFileBean(source.getFileType());
				interfaceLogDao.createInterfaceLog(ilog);
				
				if(fileBean==null&&sourceDataList.size()<=0){
					container.setCorrectFlag(false);
					container.setErrorCode(ReturnResult.interface_fileType_notExist);
					container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.interface_fileType_notExist));
					return container;
				}

				String targeTtablename=source.getTargeDBTable();
				boolean dbinsertFlag=false;
				if(targeTtablename!=null && !targeTtablename.trim().equals("")) 
					dbinsertFlag=true;
				
				//自定义处理逻辑
				container=adapterComFunction.executeCustom(true, sourceID, outParams, ilog);
				if(!container.isCorrectFlag()) {
					throw new Exception(container.getErrorMsg());
				}
				
				logger.info(source.getSourceName()+ "		开始运行适配器");

				//Add Pan Wei 2019/10/10
				if(source.getChannelType()==InterfaceConfig.fileType) {
					if(source.isFileMultiFlag()) {
						//文件读取，分多文件读取。则每读取一次之后，先处理数据，入数据库。之后再读取另一个文件
						//读取一个文件，在read的具体实现类里，往dataCounter的readedFiles写入已经读的文件。后一次判定不读已读取的文件
						logger.info(source.getSourceName()+ "		多文件读取");
						logger.info(source.getSourceName()+ "		注意: 多文件读取，最终的进库数据总量并不一定等于读取总量，中间可能会有数据被更新");
					}
				}
				
				while(true){
					//读完所有数据，跳出。多批次读文件时，会修改该值，是否已经读完所有文件的
					dataCounter.setFetchRecords(0);
					dataCounter.setTotalRecords(0);
					while(dataCounter.getFetchRecords()<=dataCounter.getTotalRecords()){
						if(dataCounter.getFetchRecords()==0 && dbinsertFlag){
							if(source.getTargeDBTable()!=null) {
								//清空临时表数据	上一轮如果是执行失败，临时表会剩余数据
								String[] tableNames = targeTtablename.split(",");
								for(String tableName : tableNames){
									logger.info(source.getSourceName() + "		清空临时表数据 ");
									sqlMap.get(targetDBType).cleanTmpTableRecords(tableName);
								}
							}
						}
						
						//数据适配器: 如果数据还没有取完，则持续取数据
						//直接传过来的数据 不是读取文件
						if(sourceDataList.size()>0){
							dataReadResult.setDataResult(sourceDataList);
							dataCounter.setFetchRecords(sourceDataList.size());
							dataCounter.setTotalRecords(sourceDataList.size());
							dataCounter.setCheckFlag(true);
						}else{
							dataReadResult=fileBean.readFileData(source, dataCounter);
						}
						sourceFilesList=dataReadResult.getFilePathList();
						logger.info(source.getSourceName() + "		已获取 "+dataCounter.getFetchRecords()+"/"+dataCounter.getTotalRecords());
						if(dataCounter.isCheckFlag()){
							//本段查询数据成功
							if(dataReadResult.getDataResult().size()>0){
								HashMap<String, Object> initGlobalValHM=adapterComFunction.initGlobalVal(userID, source, asOfDate);
								
								if(dataReadResult!=null){
									container=inerfaceMsg.genMsg(source, dataReadResult.getDataResult(), initGlobalValHM);
									if(container.isCorrectFlag()){
										filternum=container.getObjID();
										totalfilternum = totalfilternum + filternum;
										if(dbinsertFlag) {
											List<HashMap<String, Object>> fieldsList=(List<HashMap<String, Object>>)container.getEntityObj();
											String sourcemsg="["+source.getMsg()+"]";
											//由于老的消息没有[],因此升级完多消息之后,还是在文本框里把[]去除,这样可以支持老的消息的格式
											List<SQLMsgTemplate> msgTmpList=JSONObject.parseArray(sourcemsg, SQLMsgTemplate.class);
											
											for(SQLMsgTemplate msgTmp : msgTmpList){
												//把消息体转成json消息
												String msg=JSONObject.toJSONString(msgTmp);
												MsgContainer msgcontainer=exeMsg(userID, workflowStep, asOfDate, source, msg, fieldsList);
												
												if(!msgcontainer.isCorrectFlag()){
													container.setCorrectFlag(false);
													container.setErrorMsg(msgcontainer.getErrorMsg());
													break;
												}
												container.setEntityObj(msgcontainerList);
											}
										}else {
											List<String> msgList=(List<String>)container.getEntityObj();
											int numSize=msgList.size();
											for(int i=0;i<numSize;i++){
												String msg=msgList.get(i);
												MsgContainer msgcontainer=exeMsg(userID, workflowStep, asOfDate, source, msg, null);
												if(!msgcontainer.isCorrectFlag()){
													container.setCorrectFlag(false);
													container.setErrorMsg(msgcontainer.getErrorMsg());
													break;
												}
												container.setEntityObj(msgcontainerList);
											}
										}
										logger.info(source.getSourceName() + "		已插入"+dataCounter.getFetchRecords());
									}else{
										break;
									}
								}
							}
						}else{
							//本段查询数据失败,重新查询
							logger.error(source.getSourceName()+	"		本轮取数失败:" + dataCounter.getFailedNum() + " " +	dataCounter.getErrorMsg());
							if(dataCounter.getFailedNum()==acceptFailedNum){
								//如果失败次数已经超过了判定次数，则停止数据导入，发送信息给数据管理。
								container.setCorrectFlag(false);
								container.setErrorMsg("数据导入失败, 获取数量和目标数量不一致");
								logger.error(source.getSourceName() + "		数据导入失败！ 获取数量和目标数量不一致");
								break;
							}else{
								Thread.sleep(1000L);
							}
						}
						
						if(dataCounter.getFetchRecords()==dataCounter.getTotalRecords()){
							logger.info(source.getSourceName() + "		取数成功结束");
							//如果数据已经取完，跳出取数逻辑
							break;
						}
					}
					
					if(container.isCorrectFlag() && dataCounter.getFetchRecords()>0 && dbinsertFlag){
						//验证临时表的数量是否和取数值相等
						// TODO edit by wangqidong
						if(source.getTargeDBTable()!=null) {
							String[] tableNames = source.getTargeDBTable().split(",");
							
							for(String tablename:tableNames){
								int tmpTableRecords=sqlMap.get(targetDBType).getTmpTableRecordsNum(source, tablename);
								logger.info(source.getSourceName()+"		临时表数据总量: " + totalfilternum);
								logger.info(source.getSourceName()+"		过滤数据总量: " + totalfilternum);
								if(tmpTableRecords==dataCounter.getTotalRecords()-filternum){
									//清除撤账数据，主要用在凭证表，余额表，估值表等物理删除的应用场景
									logger.info(source.getSourceName() + "		数据整合-删除开始: ");
									//edit by wangqidong
									int delNums=sqlMap.get(targetDBType).getMergeDeleteNums(source, tablename);
									logger.info(source.getSourceName() + "		数据整合-删除数量: "	+ delNums);
									ilog.setDelRecords(delNums);
									if(delNums>0){
										sqlMap.get(targetDBType).exeMergerDeleteData(source, tablename);
									}
									
									//判定取数成功，合并临时表和数据表
									logger.info(source.getSourceName() + "		数据整合-合并开始: ");
									// TODO edit by wangqidong
									sqlMap.get(targetDBType).exeMergerData(source, tablename);
									logger.info(source.getSourceName() + "		数据整合-结束");
									
									//获取本次整合的最新标记判断位，存入数据库。下次整合读取判断标记位之后的数据
									importDataSuccessFlag=true;
								}else{
									container.setCorrectFlag(false);
									String tmpTableError="临时表数据量和查询数据量不相等: "+tmpTableRecords+"/"+dataCounter.getTotalRecords();
									logger.info(source.getSourceName() + "		"+tmpTableError);
									container.setErrorMsg(tmpTableError);
								}
							}
						}
					}
					
					readTotalNums=readTotalNums+dataCounter.getFetchRecords();
					
					if(dataCounter.isAllDataReadyFlag() || !container.isCorrectFlag()) {
						//读完所有数据，跳出。多批次读文件时，会修改该值，是否已经读完所有文件的
						break;
					}
				}
				
			}catch(Exception e){
				container.setCorrectFlag(false);
//				String errormsg=e.toString() + " | " + e.getMessage();
//				if(e.getCause()!=null){
//					errormsg=e.getCause().getMessage()+" | "+errormsg;
//				}
				container.setErrorMsg("系统异常,请联系管理员");
				logger.error("Error", e);
			}finally{
				PreparedStatement ps=dataCounter.getPreparedStatement();
				Connection conn=dataCounter.getConn();
				try {
					if(ps!=null){
						ps.close();
					}
					if(conn!=null){
						conn.close();
					}
				} catch (SQLException e) {
					logger.error("Error", e);
				}
			}
			
			try{
				if(!container.isCorrectFlag()){
					ilog.setErrorMsg(container.getErrorMsg());
				}else{
					ilog.setSuccessNum(readTotalNums);
					ilog.setResultFlag(true);
					
					
					if((depNoDataGoFlag==0 && readTotalNums>0) || depNoDataGoFlag==1){
						//如果执行正确，并且能查找出数据，则调用联机任务
						childExeSuccessFlag=execuateChildTask.execuateChildTask(sourceID, userID);
					}else{
						childExeSuccessFlag=true;
					}
					
					
					if(childExeSuccessFlag){
						if(backupFlag){
							String moveFileReturnMsg=adapterComFunction.moveFiles(sourceID, backupFolder, sourceFilesList);
							if(!moveFileReturnMsg.equals("")){
								childExeSuccessFlag=false;
								container.setCorrectFlag(false);
								ilog.setResultFlag(false);
								ilog.setErrorMsg(moveFileReturnMsg);
							}else{
								ilog.setResultFlag(true);
							}
						}else
							ilog.setResultFlag(true);
					}else{
						container.setCorrectFlag(false);
						ilog.setResultFlag(false);
						ilog.setErrorMsg("子适配器执行失败");
					}
				}
				
				if(container.isCorrectFlag()) {
					//自定义处理逻辑
					container=adapterComFunction.executeCustom(false, sourceID, outParams, ilog);
					if(!container.isCorrectFlag()) {
						ilog.setResultFlag(false);
						ilog.setErrorMsg(container.getErrorMsg());
					}
				}
				
				ilog.setEndTime(new Date());
				ilog.setCurStatus(AdapterRunStatus.stop);
				interfaceLogDao.updateInterfaceLog(ilog);
			}catch(Exception e){
				container.setCorrectFlag(false);
				String errormsg=e.toString() + " | " + e.getMessage();
				if(e.getCause()!=null){
					errormsg=e.getCause().getMessage()+" | "+errormsg;
				}
				container.setErrorMsg(errormsg);
				logger.error("Error", e);
				
			}finally{
				//保存最新的执行时间，最后移除正在执行的状态
				sqlMap.get(targetDBType).saveMaxCycleRmRunFlag(sourceID, importDataSuccessFlag, childExeSuccessFlag, dataCounter.getMaxCycleMap());
				logger.info(sourcename + "		适配器运行结束");
				
				if(container.isCorrectFlag()) {
					//直接调用下游程序，该程序必须继承CallExecuteService抽象类
					if(afterCallClass!=null && !afterCallClass.trim().equals("") && !afterCallClass.equals("0")) {
						Object callServiceObj=JixuContextBeansUtil.getBean(afterCallClass);
						if(callServiceObj!=null && callServiceObj instanceof AfterCallClassesAbstract) {
							AfterCallClassesAbstract service=(AfterCallClassesAbstract)callServiceObj;
							service.setDto(callDto);
							logger.info(sourcename + "		适配器调用下游程序: " + service.callClassName());
							Thread thread=new Thread(service);
                            thread.start();
						}
					}
				}
			}
		}catch(Exception e){
			container.setCorrectFlag(false);
			logger.error("Error", e);
		}finally{
			logger.info("删除适配器内存唯一判定标记: " + sourceID);
			runningSet.remove(sourceID);
		}
		
		return container;
	}
	
//	/**
//	 * 根据父ID的依赖关系，执行子节点的job
//	 * 每个适配器都可以配置父适配器，在父适配器执行结束后，自动被调用
//	 * @param sourceID
//	 * @param userID
//	 */
//	private boolean execuateChildTask(int sourceID, int userID){
//		List<Future<ResultContainer>> resultList=new ArrayList<Future<ResultContainer>>();
//		
//		List<InterfaceSource> interfaceSource=interfaceSourceDao.loadChildInterfaces(sourceID);
//		for(InterfaceSource source:interfaceSource){
//			if(sourceID!=source.getSourceID()){
//				ExecuteReturnJobTask task=new ExecuteReturnJobTask(this,expAssembleOutput, userID, source, new Date());
//				Future<ResultContainer> future=taskExecutor.submit(task);
//				resultList.add(future);
//			}
//		}
//		
//		boolean returnFlag=true;
//		try{
//			for(Future<ResultContainer> future:resultList){
//				ResultContainer container=future.get();
//				if(!container.isCorrectFlag()){
//					returnFlag=false;
//				}
//			}
//		}catch(InterruptedException ie){
//			returnFlag=false;
//			logger.error("Error", ie);
//		}catch(Exception e){
//			returnFlag=false;
//			logger.error("Error", e);
//		}
//
//		return returnFlag;
//	}
	
	/**
	 * 上传并读取接口文件
	 */
	public ResultContainer uploadData(int userID, int sourceID, Date asOfDate, int workflowStep, List<MultipartFile> mpfList){
		InterfaceSource source=interfaceSourceDao.loadEntity(InterfaceSource.class, sourceID);
		ReadDataFile fileBean=createReadFileBean(source.getFileType());
		
		ResultContainer container=new ResultContainer();
		List<MsgContainer> msgcontainerList=new ArrayList<MsgContainer>();
		
		try{
			for(MultipartFile mpf:mpfList){
				//上传的文件先复制到配置的文件夹下,然后上传
				String filePath = source.getFilepath() + "/" + mpf.getOriginalFilename();
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(new SafelyFile(filePath)));
			
				if(fileBean==null){
					container.setCorrectFlag(false);
					container.setErrorCode(ReturnResult.interface_fileType_notExist);
					container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.interface_fileType_notExist));
					return container;
				}
				
				source.setFileName(mpf.getOriginalFilename());
				DataCounter dataCounter=new DataCounter();
				DataReadResult dataReadResult=fileBean.readFileData(source, dataCounter);
				if(dataReadResult!=null){
					HashMap<String, Object> initGlobalValHM=adapterComFunction.initGlobalVal(userID, source, asOfDate);
					container=inerfaceMsg.genMsg(source, dataReadResult.getDataResult(), initGlobalValHM);
					if(container.isCorrectFlag()){
						List<String> msgList=(List<String>)container.getEntityObj();
						for(String msg:msgList){
							//TODO 导出的配置，重新写代码
							//MsgContainer msgcontainer=exeMsg(userID, workflowStep, asOfDate, msg, source);
							//msgcontainerList.add(msgcontainer);
						}
					}
				}
			}
			container.setEntityObj(msgcontainerList);
		}catch(Exception e){
			container.setCorrectFlag(false);
			container.setErrorMsg("上传并读取接口文件异常");//e.toString());
			logger.error("Error", e);
			return container;
		}
		
		return container;
	}
	
	
	/**
	 * 选择数据库的脚本运行
	 * @param userID
	 * @param msg
	 * @return
	 */
	private MsgContainer exeSqlMsg(int userID, String msg, InterfaceSource is, List<HashMap<String, Object>> fieldsMap){
		MsgContainer mainContainer=new MsgContainer();
	
		List<MsgContainer> containerList=new ArrayList<MsgContainer>();
		for(HashMap<String, Object> dataMap:fieldsMap){
			MsgContainer msgcontainer=new MsgContainer();
			msgcontainer.setFieldsHM(dataMap);
			msgcontainer.setUserID(userID);
			msgcontainer.setSource(is);
			// 在MsgContainer 增加了一个字段是是msg，在下一个方法中直接使用msg，不用再获取一次
			msgcontainer.setMsg(msg);
		
			if(!is.isBatchExeFlag()){
				//不是批量执行，需要每条验证数据，则一条一条发出
				sqlMap.get(targetDBType).execute(msgcontainer);
			}else{
				containerList.add(msgcontainer);
			}
		}
		
		if(is.isBatchExeFlag()){
			//批量执行
			mainContainer=executeBatchNums(containerList);
		}
		
		
		return mainContainer;
		
	}
	
	public MsgContainer executeBatchNums(List<MsgContainer> containerList){
		List<MsgContainer> list=new ArrayList<MsgContainer>();
		MsgContainer mainContainer=new MsgContainer();
		for (int i = 0; i < containerList.size(); i++) {
			list.add(containerList.get(i));
			if(i%executeNum==0&&i!=0||i==containerList.size()-1){
				mainContainer=sqlMap.get(targetDBType).executeBatch(list);
				if(mainContainer.isCorrectFlag())
					list.clear();
				else
					return mainContainer;
			}
		}
		return mainContainer;
	}
	
	@SuppressWarnings("unchecked")
	private MsgContainer exeMsg(int userID, int workflowStep, Date asOfDate, InterfaceSource source, String msg, List<HashMap<String, Object>> fieldsMap){
		MsgContainer msgcontainer=new MsgContainer();
		
		HashMap<String,Object> msgMsg=JSONObject.parseObject(msg, HashMap.class);
		msgcontainer.setFieldsHM(msgMsg);
		msgcontainer.setUserID(userID);
		logger.debug(msg);
		if(msgMsg.size()!=0){
			logger.debug("JSON HashMap Format Message");
			if(msgMsg.get("header").equals("executeSQLMsg")){
				//如果是直接插入sql语句，走sql脚本执行的路
				msgcontainer=exeSqlMsg(userID, msg, source, fieldsMap);
			}else{
				msgcontainer=MsgFactory.executeMsg(msgcontainer);
			}
		}else{
			logger.debug("Old Array Format Message");
			msgcontainer.setOperatoionStep(workflowStep);
			msgcontainer.setLedgerDate(asOfDate);
			msgAssemble.genMsgContainer(msg,msgcontainer);
		}
		
		if(!msgcontainer.isCorrectFlag()){
			msgcontainer.setCorrectFlag(msgcontainer.isCorrectFlag());
			msgcontainer.setErrorMsg(msgcontainer.getErrorMsg());
		}
		return msgcontainer;
	}
	
	private Map<Integer, ReadDataFile> msgMap = new HashMap<Integer, ReadDataFile>();
	private Map<Integer, ExecuteDBScripts> sqlMap=new HashMap<Integer, ExecuteDBScripts>();
	private Map<Integer, ReadDBAbstract> dbBeanMap=new HashMap<Integer, ReadDBAbstract>();
    private ApplicationContext applicationContext;
	
    
	public void afterPropertiesSet() throws Exception {
		logger.info("开始初始化数据中心...");
		Map<String, ReadDataFile> beanMap = applicationContext.getBeansOfType(ReadDataFile.class);
		for (ReadDataFile fileBean : beanMap.values()) {
			msgMap.put(fileBean.getFileType(), fileBean);
        }
		
		Map<String, ExecuteDBScripts> dbScriptsBeans = applicationContext.getBeansOfType(ExecuteDBScripts.class);
		for (ExecuteDBScripts sqlbean : dbScriptsBeans.values()) {
			sqlMap.put(sqlbean.dbType(), sqlbean);
        }
		
		Map<String, ReadDBAbstract> dbBeans=applicationContext.getBeansOfType(ReadDBAbstract.class);
		for (ReadDBAbstract fileBean : dbBeans.values()) {
			dbBeanMap.put(fileBean.getFileType(), fileBean);
        }
		
		logger.info("初始化数据中心结束");
	}
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	/**
	 * 读取哪个类型文件
	 * @param readFileType
	 * @return
	 */
	public ReadDataFile createReadFileBean(int readFileType) {
		return msgMap.get(readFileType);
	}
	
	/**
	 * 获取数据库读取类
	 * @param readDBType
	 * @return
	 */
	public ReadDBAbstract createDBReadBean(int readDBType) {
		return dbBeanMap.get(readDBType);
	}
}
