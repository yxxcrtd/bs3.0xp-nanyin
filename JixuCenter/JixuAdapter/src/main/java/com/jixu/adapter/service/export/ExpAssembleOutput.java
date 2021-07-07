package com.jixu.adapter.service.export;

import java.io.File;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.jixu.adapter.dao.intf.InterfaceColConfigDao;
import com.jixu.adapter.dao.intf.InterfaceLogDao;
import com.jixu.adapter.reader.ReadDataFile;
import com.jixu.adapter.service.AdapterComFunction;
import com.jixu.adapter.service.AdditionFunc;
import com.jixu.adapter.service.ExecuateChildTask;
import com.jixu.adapter.service.InterfaceFieldsService;
import com.jixu.adapter.service.parent.AfterCallClassesAbstract;
import com.jixu.adapter.types.AdapterRunStatus;
import com.jixu.adapter.types.CallClassParamsDto;
import com.jixu.adapter.types.ExeRecordInfo;
import com.jixu.adapter.types.FileEncodeType;
import com.jixu.adapter.writer.WriteDataFile;
import com.jixu.adapter.writer.WriteSftpFile;
import com.jixu.common.pojo.InterfaceColConfig;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.tool.JixuContextBeansUtil;
import com.jixu.common.type.DataCounter;
import com.jixu.common.type.DataReadResult;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.util.ReturnResult;
import com.orm.obj.InterfaceLog;

/**
 * @ClassName ExpAssembleOutput
 * @author Pan Wei
 * @Date 2017年5月3日 下午1:11:00
 * @Description []
 */

@Service("expAssembleOutput")
public class ExpAssembleOutput extends InterfaceFieldsService {

	private static final Logger logger = LoggerFactory.getLogger(ExpAssembleOutput.class);

	@Resource(name = "exportDataSource")
	private ExportDataSource expDataSource;

	@Resource(name = "taskExecutor")
	private ThreadPoolTaskExecutor taskExecutor;

	@Autowired
	private ExecuateChildTask execuateChildTask;

	@Autowired
	private AdapterComFunction adapterComFunction;

	@Autowired
	private InterfaceLogDao interfaceLogDao;

	private int acceptFailedNum = 3;

	@Autowired
	private InterfaceColConfigDao interfaceColConfigDao;

	@Value("${jixu.adapter.targetDB.dbType}")
	private int targetDBType;

	private Set<Integer> runningSet = Collections.synchronizedSet(new HashSet<Integer>());

	@Autowired
	private WriteSftpFile writeSftpFile;

	@Value("${jixu.platedate}")
	private Boolean isplatedate;

	@Transactional(value = "jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer executeExport(int interfaceSourceID, String[] params) {
//		InterfaceSource is=interfaceSourceDao.loadEntity(InterfaceSource.class, interfaceSourceID);
//		ExportDataService expData=expDataSource.getExpDataFuncMap().get(is.getExpSourceClass());
//		List<HashMap<String, Object>> dataList=expData.genExportData(params);
//		ResultContainer container=genOutPutData(is, dataList);
//		//String portfolio=Integer.valueOf(params[0]);
//		//String endDateStr=params[1];
//		//PortfolioHdr portfolio
//		if(container.isCorrectFlag()){
//			container= writeOutputFile(is, container);
//		}
//		return container;
		return exeSQLFetchResult(interfaceSourceID, null);
	}

	public ResultContainer preViewExport(int interfaceSourceID, String[] params) {
		// InterfaceSource is=interfaceSourceDao.loadEntity(InterfaceSource.class,
		// interfaceSourceID);
		// ExportDataService
		// expData=expDataSource.getExpDataFuncMap().get(is.getExpSourceClass());
		// List<HashMap<String, Object>> dataList=expData.genExportData(params);
		// return genOutPutData(is, dataList);
		return null;
	}

	/**
	 * 
	 * @param source
	 * @param dataContainer
	 * @return
	 */
	public ResultContainer genOutPutData(InterfaceSource source, List<List<Object>> dataContainer,
			HashMap<String, Object> initGlobalValHM) {
		ResultContainer container = new ResultContainer();
		int filterNum = 0;

		List<InterfaceColConfig> baseList = interfaceColConfigDao.loadCfgOrderByTypeAndID(source.getSourceID(),
				new int[] { InterfaceConfig.baseType });
		List<InterfaceColConfig> advanceList = interfaceColConfigDao.loadCfgOrderByTypeAndID(source.getSourceID(),
				new int[] { InterfaceConfig.advanceType, InterfaceConfig.globalType, InterfaceConfig.groupType,
						InterfaceConfig.groupAdvanceType });
		List<InterfaceColConfig> filterList = interfaceColConfigDao.loadCfgOrderByTypeAndID(source.getSourceID(),
				new int[] { InterfaceConfig.filterType });
		List<InterfaceColConfig> finalList = interfaceColConfigDao.loadCfgOrderByTypeAndColno(source.getSourceID(),
				new int[] { InterfaceConfig.exportFinalType });
		List<InterfaceColConfig> colcfgList = new ArrayList<InterfaceColConfig>();
		colcfgList.addAll(baseList);
		colcfgList.addAll(advanceList);
		colcfgList.addAll(filterList);

		AdditionFunc func = null;
		Object field = "";
		boolean filterFlag = true;

		List<HashMap<String, Object>> sourceList = new ArrayList<HashMap<String, Object>>();
		logger.debug("Start Basic................................................................................");

		for (List<Object> fieldsList : dataContainer) {
			HashMap<String, Object> colcfgMap = new HashMap<String, Object>();
			for (InterfaceColConfig colcfg : colcfgList) {
				func = null;
				if (colcfg.getFuncClass() != null) {
					// 获取接口类
					func = ineterfaceBeanService.getAdditionFuncBean(colcfg.getFuncClass());
				}

				if (colcfg.getColType() == InterfaceConfig.baseType) {
					if (fieldsList.size() < colcfg.getColNO()) {
						// 如果列的数量小于输入位数,填入空值
						colcfgMap.put(colcfg.getColname(), "");
						continue;
					}

					// 获取基础列
					field = fieldsList.get(colcfg.getColNO() - 1);

					if (colcfg.getColStartNO() >= 0) {
						try {
							byte[] byteFields = field.toString()
									.getBytes(FileEncodeType.optionHM.get(source.getEnCodeType()));
							field = new String(byteFields, colcfg.getColStartNO(),
									colcfg.getColEndNo() - colcfg.getColStartNO(),
									FileEncodeType.optionHM.get(source.getEnCodeType()));
						} catch (Exception ue) {
							logger.error("Error", ue);
							container.setCorrectFlag(false);
							StringBuilder builder = new StringBuilder();
							builder.append("Error: ");
							builder.append("\r\n");
							builder.append("Column: ");
							builder.append(colcfg.getColname());
							builder.append("\r\n");
							builder.append("Column StartNum: ");
							builder.append(colcfg.getColStartNO());
							builder.append("\r\n");
							builder.append("Column EndNum: ");
							builder.append(colcfg.getColEndNo());
							builder.append("\r\n");
							builder.append("Source Fields: ");
							builder.append(field);
							builder.append("\r\n");
							builder.append(ue.getMessage());
							container.setErrorMsg(builder.toString());
							return container;
						}

					}
					// 核对基础列是否符合需求
					if (func == null || func.validateFiled(field)) {
						colcfgMap.put(colcfg.getColname(), field);
					} else {
						// container.setCorrectFlag(false);
						StringBuilder builder = new StringBuilder();
						builder.append("Error: ");
						builder.append("\r\n");
						builder.append("Column Num: ");
						builder.append(colcfg.getColNO());
						builder.append("\r\n");
						builder.append("Column Value: ");
						builder.append(field);
						builder.append("\r\n");
						builder.append("Func name: ");
						builder.append(colcfg.getFuncClass());
						builder.append("\r\n");
						builder.append(func.fundUsefulDesc());
						container.setCorrectFlag(false);
						container.setErrorMsg(builder.toString());
						// return container;
						filterFlag = false;
						break;
					}
				}
			}

			if (filterFlag) {
				for (InterfaceColConfig colcfg : colcfgList) {
					if (colcfg.getFilterFuncClass() != null) {
						func = ineterfaceBeanService.getAdditionFuncBean(colcfg.getFilterFuncClass());
						if (func != null) {
							try {
								// 筛选功能
								if (colcfgMap.get(colcfg.getColname()) != null) {
									if (!func.filterField(colcfgMap.get(colcfg.getColname()),
											colcfg.getFilterFieldValue())) {
										filterFlag = false;
										break;
									}
								}
							} catch (Exception e) {
								logger.error("Error", e);
								container.setCorrectFlag(false);
								StringBuilder builder = new StringBuilder();
								builder.append("FuncName: ");
								builder.append(func.getFuncName());
								builder.append("\r\n");
								builder.append("ColumnName: ");
								builder.append(colcfg.getColname());
								builder.append("\r\n");
								builder.append("ColumnName Value: ");
								builder.append(colcfgMap.get(colcfg.getColname()));
								builder.append("\r\n");
								builder.append("Column Value: ");
								builder.append(colcfg.getFilterFieldValue());
								container.setErrorMsg(builder.toString() + "		" + "系统异常,请联系管理员");
								return container;
							}
						}
					}
				}
			}

			logger.debug("Basic Column: " + colcfgMap);

			if (filterFlag) {
				sourceList.add(colcfgMap);
			} else {
				filterNum++;
			}
			filterFlag = true;
		}

		List<HashMap<String, Object>> newSourceList = new ArrayList<HashMap<String, Object>>();

		try {
			newSourceList = genAdvanceMsg(sourceList, colcfgList, initGlobalValHM);
		} catch (Exception e) {
			AdditionFunc addExe = exception.getExceptionClass(e);
			container.setCorrectFlag(false);
			if (addExe == null) {
				container.setErrorMsg(e.toString());
			} else {
				StringBuilder sb = new StringBuilder();
				sb.append("系统异常,请联系管理员");
				sb.append("		\r\n");
				sb.append(addExe.getFuncName());
				sb.append(":	");
				sb.append(addExe.fundUsefulDesc());
				container.setErrorMsg(sb.toString());
			}
			return container;
		}

		List<String> dataHeader = new ArrayList<String>();
		//无论是否有数据，都把标题放入
		for (InterfaceColConfig finalCfg : finalList) {
			dataHeader.add(finalCfg.getColname());
		}
				
		List<List<Object>> finalDataOutput = new ArrayList<List<Object>>();
		// 获取所有配置的ID和名字
		HashMap<Integer, String> numNameHM = new HashMap<Integer, String>();
		for (InterfaceColConfig fieldCfg : colcfgList) {
			numNameHM.put(fieldCfg.getId(), fieldCfg.getColname());
		}

		for (int i = 0; i < newSourceList.size(); i++) {
			HashMap<String, Object> newSource = newSourceList.get(i);
			List<Object> finalFieldList = new ArrayList<Object>();

			int finalMapFieldNum = 0;
			String finalMapFieldName = "";
			for (InterfaceColConfig finalCfg : finalList) {
				finalMapFieldNum = Integer.valueOf(finalCfg.getFuncParams());
				finalMapFieldName = numNameHM.get(finalMapFieldNum);

				finalFieldList.add(newSource.get(finalMapFieldName));
				
			}
			finalDataOutput.add(finalFieldList);
		}

		DataReadResult writeSourceData = new DataReadResult();
		writeSourceData.setDataHeader(dataHeader);
		writeSourceData.setDataResult(finalDataOutput);
		container.setEntityObj(writeSourceData);

		container.setObjID(filterNum);
		return container;
	}

	@Transactional(value = "jixuTXManager", rollbackFor = Exception.class)
	private ResultContainer writeOutputFile(InterfaceSource source, ResultContainer container) {
		DataReadResult writeSourceData = (DataReadResult) container.getEntityObj();
		WriteDataFile writeFile = expDataSource.getWriteFileBeanHM().get(source.getOutFileType());
		try {
			boolean writeFlag = writeFile.writeFile(source, writeSourceData);
			container.setCorrectFlag(writeFlag);
			container.setEntityObj(writeSourceData.getWriteFilePath());
		} catch (Exception e) {
			container.setCorrectFlag(false);
			container.setErrorMsg(e.getMessage());
			logger.error("Error", e);
		}
		return container;
	}

	public ResultContainer exeSQLFetchResult(int sourceID, HashMap<String, Object> outParams) {
		ResultContainer container = new ResultContainer();
		// 查看当前适配器是否在运行
		boolean checkNotRunFlag = adapterComFunction.checkRunning(sourceID, runningSet);
		if (!checkNotRunFlag) {
			return container;
		}

		// 如果本适配器的数据已经被执行成功，则标记为true
		// 最后在所有子线程都成功的前提下，把maxupdatetime设值到interfaceparamslastmax表里去
		boolean exeDataSuccessFlag = false;
		// 所有子线程执行成功的标记位
		boolean childExeSuccessFlag = false;
		// 如果父适配器没有数据，是否再调用子适配器 0:不调用 1:调用
		int depNoDataGoFlag = 0;

		boolean backupFlag = false;
		String sourcename = "";
		String afterCallClass = "";
		// 调用下游数据传入的参数
		CallClassParamsDto callDto = new CallClassParamsDto();

		List<String> sourceFilesList = new ArrayList<String>();

		try {
			// List<String> sourceFilesList=new ArrayList<String>();
			// List<MsgContainer> msgcontainerList=new ArrayList<MsgContainer>();
			String backupFolder = "";
			InterfaceLog ilog = new InterfaceLog(-99, sourceID);
			if (outParams != null) {
				ilog.setOutParams(JSONObject.toJSONString(outParams));
			}
			DataCounter dataCounter = new DataCounter();
			dataCounter.setOutParamsMap(outParams);
			DataReadResult dataReadResult = new DataReadResult();
			try {
				InterfaceSource source = interfaceSourceDao.loadEntity(InterfaceSource.class, sourceID);
				sourcename = source.getSourceName();

				// 调用下游数据的复制
				afterCallClass = source.getAfterCallClass();
				callDto.setAdapterID(source.getSourceID());
				callDto.setAdapterName(source.getSourceName());

				backupFlag = source.isBackupFlag();
				if (backupFlag) {
					// 如果是文件导入，需要备份文件
					backupFolder = source.getBackupPath();
				}

				depNoDataGoFlag = source.getDepNoDataGoFlag();

				// 获取数据库读取的类
				ReadDataFile fileBean = adapterComFunction.createReadFileBean(source.getFileType());
				interfaceLogDao.createInterfaceLog(ilog);

				if (fileBean == null) {
					container.setCorrectFlag(false);
					container.setErrorCode(ReturnResult.interface_fileType_notExist);
					container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.interface_fileType_notExist));
					logger.error("Error", ReturnResult.getResultHM().get(ReturnResult.interface_fileType_notExist));
					return container;
				}

				// 自定义处理逻辑
				container = adapterComFunction.executeCustom(true, sourceID, outParams, ilog);
				if (!container.isCorrectFlag()) {
					throw new Exception(container.getErrorMsg());
				}

				logger.info(source.getSourceName() + "		开始运行适配器");
				while (dataCounter.getFetchRecords() <= dataCounter.getTotalRecords()) {
					// 数据适配器: 如果数据还没有取完，则持续取数据
					// 直接传过来的数据 不是读取文件
					dataReadResult = fileBean.readFileData(source, dataCounter);

					sourceFilesList = dataReadResult.getFilePathList();
					if (dataCounter.isCheckFlag()) {
						if (source.getChannelType() == InterfaceConfig.SftpType) {
							writeSftpFile.writeFile(source, dataCounter);
							logger.info(source.getSourceName() + "		已获取 " + dataCounter.getFetchRecords() + "/"
									+ dataCounter.getTotalRecords());
						} else if (dataReadResult.getDataResult().size() > 0
								|| (dataReadResult.getDataResult().size() >= 0 && source.isOutNoDataGenFileFlag())) {
							logger.info(source.getSourceName() + "		已获取 " + dataCounter.getFetchRecords() + "/"
									+ dataCounter.getTotalRecords());
							HashMap<String, Object> initGlobalValHM = adapterComFunction.initGlobalVal(-99, source,
									new Date());
							List<List<Object>> datasList = dataReadResult.getDataResult();

//							InterfaceSource is=interfaceSourceDao.loadEntity(InterfaceSource.class, sourceID);
							container = genOutPutData(source, datasList, initGlobalValHM);
							// String portfolio=Integer.valueOf(params[0]);
							// String endDateStr=params[1];
							// PortfolioHdr portfolio
							if (container.isCorrectFlag()) {
								container = writeOutputFile(source, container);
								if (container.isCorrectFlag()) {
									// 获取文件路径和文件大小
									String filepath = container.getEntityObj().toString();
									ExeRecordInfo record = new ExeRecordInfo();
									File file = new SafelyFile(filepath);
									record.setFilePath(filepath);
									record.setFileSize(file.length());
									ilog.setRecordInfo(JSONObject.toJSONString(record));
								}
							}
						}
					} else {
						// 本段查询数据失败,重新查询
						logger.info(source.getSourceName() + "		已获取 " + dataCounter.getFetchRecords() + "/"
								+ dataCounter.getTotalRecords());
						logger.error(source.getSourceName() + "		本轮取数失败:" + dataCounter.getFailedNum() + " "
								+ dataCounter.getErrorMsg());
						if (dataCounter.getFailedNum() == acceptFailedNum) {
							// 如果失败次数已经超过了判定次数，则停止数据导入，发送信息给数据管理。
							container.setCorrectFlag(false);
							container.setErrorMsg("数据导入失败, 获取数量和目标数量不一致");
							logger.error(source.getSourceName() + "		数据导入失败！ 获取数量和目标数量不一致");
							break;
						} else {
							Thread.sleep(1000L);
						}
					}

					if (dataCounter.getFetchRecords() == dataCounter.getTotalRecords()) {
						logger.info(source.getSourceName() + "		取数成功结束");
						// 如果数据已经取完，跳出取数逻辑
						break;
					}
				}

				if (container.isCorrectFlag()) {
					ilog.setSuccessNum(dataCounter.getFetchRecords());
					ilog.setResultFlag(true);
				}

			} catch (Exception e) {
				container.setCorrectFlag(false);
//				String errormsg = e.toString() + " | " + e.getMessage();
//				if (e.getCause() != null) {
//					errormsg = e.getCause().getMessage() + " | " + errormsg;
//				}
				container.setErrorMsg("系统异常,请联系管理员");
				logger.error("Error", e);
			} finally {
				PreparedStatement ps = dataCounter.getPreparedStatement();
				Connection conn = dataCounter.getConn();
				try {
					if (ps != null) {
						ps.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					logger.error("Error", e);
				}
			}

			try {
				if (!container.isCorrectFlag()) {
					ilog.setErrorMsg(container.getErrorMsg());
				} else {
					if ((depNoDataGoFlag == 0 && dataCounter.getTotalRecords() > 0) || depNoDataGoFlag == 1) {
						// 如果执行正确，并且能查找出数据，则调用联机任务
						childExeSuccessFlag = execuateChildTask.execuateChildTask(sourceID, 1);
						exeDataSuccessFlag = true;
					} else {
						childExeSuccessFlag = true;
					}

					if (childExeSuccessFlag) {
						if (backupFlag) {
							String moveFileReturnMsg = adapterComFunction.moveFiles(sourceID, backupFolder,
									sourceFilesList);
							if (!moveFileReturnMsg.equals("")) {
								childExeSuccessFlag = false;
								container.setCorrectFlag(false);
								ilog.setResultFlag(false);
								ilog.setErrorMsg(moveFileReturnMsg);
							} else {
								ilog.setResultFlag(true);
							}
						} else
							ilog.setResultFlag(true);
					} else {
						container.setCorrectFlag(false);
						ilog.setResultFlag(false);
						ilog.setErrorMsg("子适配器执行失败");
					}

					if (container.isCorrectFlag()) {
						// 自定义处理逻辑
						container = adapterComFunction.executeCustom(false, sourceID, outParams, ilog);
						if (!container.isCorrectFlag()) {
							ilog.setResultFlag(false);
							ilog.setErrorMsg(container.getErrorMsg());
						}
					}
				}
				ilog.setEndTime(new Date());
				ilog.setCurStatus(AdapterRunStatus.stop);
				// 封装传给下游系统json相关信息
				callDto.setParams(JsonToDownstream(ilog));

				interfaceLogDao.updateInterfaceLog(ilog);
			} catch (Exception e) {
				container.setCorrectFlag(false);
				String errormsg = e.toString() + " | " + e.getMessage();
				if (e.getCause() != null) {
					errormsg = e.getCause().getMessage() + " | " + errormsg;
				}
				container.setErrorMsg(errormsg);
				logger.error("Error", e);

			} finally {
				// 保存最新的执行时间，最后移除正在执行的状态
				adapterComFunction.createExeDB(targetDBType).saveMaxCycleRmRunFlag(sourceID, exeDataSuccessFlag,
						childExeSuccessFlag, dataCounter.getMaxCycleMap());
				logger.info(sourcename + "		适配器运行结束");

				if (container.isCorrectFlag()) {
					// 直接调用下游程序，该程序必须继承CallExecuteService抽象类
					if (afterCallClass != null && !afterCallClass.trim().equals("") && !afterCallClass.equals("0")) {
						Object callServiceObj = JixuContextBeansUtil.getBean(afterCallClass);
						if (callServiceObj != null && callServiceObj instanceof AfterCallClassesAbstract) {
							AfterCallClassesAbstract service = (AfterCallClassesAbstract) callServiceObj;
							service.setDto(callDto);
							logger.info(sourcename + "		适配器调用下游程序: " + service.callClassName());
							Thread thread = new Thread(service);
							thread.start();
						}
					}
				}
			}
		} catch (Exception e) {
			container.setCorrectFlag(false);
			logger.error("Error", e);
		} finally {
			logger.info("删除适配器内存唯一判定标记: " + sourceID);
			runningSet.remove(sourceID);
		}

		return container;
	}

	/**
	 * 封装传给下游系统json相关信息
	 */
	private Map<String, Object> JsonToDownstream(InterfaceLog ilog) {
		ExeRecordInfo record = JSONObject.parseObject(ilog.getRecordInfo(), ExeRecordInfo.class);
		Map<String, Object> params = new HashMap<>();
		if (record != null) {
			File file = new SafelyFile(record.getFilePath());
			params.put("outFileName", file.getName());
			params.put("outFilePath", file.getParentFile().getPath());
			if (isplatedate) {
				List<Date> curDateList = interfaceSourceDao.getCurDate();
				params.put("platedate", curDateList.get(0));
			}
		}
		return params;
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
//				ExecuteReturnJobTask task=new ExecuteReturnJobTask(dataServiceCenter,this, userID, source, new Date());
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

//	public List<String> getFieldsHeader(InterfaceSource source){
//		List<InterfaceColConfig> baseList=interfaceColConfigDao.loadCfgOrderByTypeAndID(source.getSourceID(), new int[]{InterfaceConfig.exportBaseType});
//		List<String> headerList=new ArrayList<String>();
//		for(InterfaceColConfig col:baseList) {
//			headerList.add(col.getColname());
//		}
//		return headerList;
//	}
}
