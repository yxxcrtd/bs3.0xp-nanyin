package com.jixu.adapter.control.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.jixu.adapter.common.func.AdapterSysInit;
import com.jixu.adapter.control.service.ConfigAdptService;
import com.jixu.adapter.dao.intf.AdapterDao;
import com.jixu.adapter.dao.intf.InterfaceAdapterDao;
import com.jixu.adapter.dao.intf.InterfaceColConfigDao;
import com.jixu.adapter.dao.intf.InterfaceLogDao;
import com.jixu.adapter.dao.intf.InterfaceMsgColMapDao;
import com.jixu.adapter.model.ExpInterfaceSourceDto;
//import com.jixu.adapter.export.ExportDataSource;
import com.jixu.adapter.model.JobStatusDto;
import com.jixu.adapter.service.export.ExportDataSource;
import com.jixu.adapter.service.impl.DataInterfaceService;
import com.jixu.adapter.service.impl.DataServiceCenter;
import com.jixu.adapter.types.ActiveType;
import com.jixu.adapter.types.AdapterJobResultDto;
import com.jixu.adapter.types.AdapterRunStatus;
import com.jixu.adapter.types.ExeRecordInfo;
import com.jixu.adapter.types.InterfaceAutoType;
import com.jixu.adapter.types.SQLMsgTemplate;
import com.jixu.common.pojo.InterfaceColConfig;
import com.jixu.common.pojo.InterfaceMsgColMap;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.tool.QuartzManager;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.type.InterfaceSourceType;
import com.jixu.common.type.JobModel;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.util.AESUtil;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.ReturnResult;
import com.jixu.framework.msg.MsgAssemble;
import com.orm.obj.AdapterColConfig;
import com.orm.obj.AdapterMsgColMap;
import com.orm.obj.DataSetColTemp;
import com.orm.obj.InterfaceLog;

@Service("configAdptService")
public class ConfigAdptServiceImpl implements ConfigAdptService{
	
	private static final Logger logger = LoggerFactory.getLogger(ConfigAdptServiceImpl.class);
	
	@Resource(name="dataInterfaceService")
	private DataInterfaceService interfaceService;
	
//	@Resource(name="exportDataSource")
//	private ExportDataSource exportSource;
	
	@Resource(name="msgAssemble")
	private MsgAssemble msgAssemble;
	
	@Resource(name="quartzManager")
	private QuartzManager jobManager;
	
	/*@Autowired
	private OutDataCenter outDataCenter;*/
	
	@Autowired
	private DataServiceCenter interafaceDataCenter;
	
	@Autowired
	private InterfaceLogDao interfaceLogDao;
	
	
	@Resource(name="interfaceAdapterDao")
	private InterfaceAdapterDao interfaceSourceDao;
	
	@Autowired
	private InterfaceColConfigDao interfaceColConfigDao;
	
	@Autowired
	private InterfaceMsgColMapDao interfaceMsgColMapDao;
	
	@Autowired
	private AdapterDao adapterDao;
	
	@Resource(name="exportDataSource")
	private ExportDataSource exportSource;
	
	@Value("${jixu.adapter.targetDB.dbType}")
	private int targetDBType;

	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void deleteInterfaceSource(int sourceID) {
		InterfaceSource source=interfaceSourceDao.loadEntity(InterfaceSource.class, sourceID);
		if(source.isAutoFlag()){
			removeAutoJob(source);
		}
		source.setActiveStatus(ActiveType.inActive);
		interfaceSourceDao.updateEntityBean(source);
	}
	
	@Override
	public List<InterfaceSource> loadInterfaceSourceList(String sourcename, int sourceType, String category) {
		return interfaceSourceDao.loadInterfaceByType(sourceType, category, sourcename);
	}
	
	@Override
	public InterfaceSource loadInterfaceSource(int sourceid) {
		return (InterfaceSource)interfaceSourceDao.loadEntity(InterfaceSource.class, sourceid);
	}
	
	@Override
	public ExpInterfaceSourceDto loadAdtpExpInterfaceDetail(int sourceid) {
		InterfaceSource source=(InterfaceSource)interfaceSourceDao.loadEntity(InterfaceSource.class, sourceid);
		ExpInterfaceSourceDto dto=new ExpInterfaceSourceDto();
		
		dto.setName(source.getSourceName());
		dto.setDesc(source.getSourceDesc());
		dto.setCategory(source.getSourceSecType());
		dto.setDeliverType(source.getChannelType());
		dto.setUser(source.getChannelAccount());
		dto.setPwd(AESUtil.Decrypt(source.getChannelPWD()));
		dto.setFileencode(source.getEnCodeType());
		dto.setStartcol(String.valueOf(source.getTableStartLine()));
		dto.setDependency(source.getDependency());
		
		if(source.getDepNoDataGoFlag()==1) {
			//空数据是否继续执行依赖下去
			dto.setDepNoDataGoFlag(true);
		}else
			dto.setDepNoDataGoFlag(false);
		dto.setOutNoDataGenFileFlag(source.isOutNoDataGenFileFlag());
		dto.setDelimiter(source.getTableSpilitChar());
		dto.setEnd(source.getTableEndChar());
		dto.setHoliday(source.getHoliday());
		dto.setAutodo(source.isAutoFlag());
		dto.setAutoDateFormat(source.getAutoType());
		dto.setFromDay(String.valueOf(source.getAutoNDays()));
		dto.setAutoTime(source.getAutoTime());
		dto.setTablename(source.getTablename());
		dto.setStartDate(source.getStartDate());
		dto.setStartTime(source.getStartTime());
		dto.setFileformat(source.getFileType());
		dto.setFileloc(source.getFilepath());
		dto.setFilename(source.getFileName());
		dto.setDbFormat(source.getFileType());
		dto.setDbURL(source.getFilepath());
		dto.setDbSQL(source.getDbSQL());
		dto.setExpFileformat(source.getOutFileType());
		dto.setExpFileloc(source.getOutFilepath());
		dto.setExpFilename(source.getOutFileName());
		dto.setExpFileencode(source.getOutFileEnCodeType());
		dto.setIncludeCol(source.isOutFileTitleFlag());
		dto.setAfterCallClass(source.getAfterCallClass());
		dto.setWriteSplit(source.getWriteSpilitChar());
		dto.setMoveto(source.getBackupPath());
		dto.setEndChar(source.getEndChar());
		
		return dto;
	}
	
	/**
	 * 获取接口的column配置信息
	 */
	@Override
	public List<InterfaceColConfig> loadInterfaceColCfgList(int sourceID, int colType) {
		List<InterfaceColConfig> configList=interfaceColConfigDao.loadInterfaceColConfig(sourceID);
		List<InterfaceColConfig> colList=new ArrayList<InterfaceColConfig>();
		for(InterfaceColConfig config:configList){
			if(config.getColType()==colType){
				colList.add(config);
			}
		}
		return colList;
	}
	
	/**
	 * 获取接口的column配置信息
	 */
	@Override
	public List<InterfaceColConfig> loadInterfaceColCfgList(int sourceID, int[] colType) {
		List<InterfaceColConfig> configList=interfaceColConfigDao.loadInterfaceColConfig(sourceID);
		List<InterfaceColConfig> colList=new ArrayList<InterfaceColConfig>();
		List<Integer> coltypeList=new ArrayList<Integer>();
		for(int tmptype:colType) {
			coltypeList.add(tmptype);
		}
		for(InterfaceColConfig config:configList){
			if(coltypeList.contains(config.getColType())){
				colList.add(config);
			}
		}
		return colList;
	}
	
	@Override
	public List<AdapterColConfig> loadAdapterColConfigList(int dataSetID, int colType) {
		List<AdapterColConfig> configList=adapterDao.loadAdapterColConfig(dataSetID);
		List<AdapterColConfig> colList=new ArrayList<AdapterColConfig>();
		for(AdapterColConfig config:configList){
			if(config.getColType()==colType){
				colList.add(config);
			}
		}
		return colList;
	}
	
	@Override
	public List<InterfaceColConfig> loadInterfaceAdvanceColCfgList(int sourceID) {
		List<InterfaceColConfig> configList=interfaceColConfigDao.loadInterfaceColConfig(sourceID);
		List<InterfaceColConfig> colList=new ArrayList<InterfaceColConfig>();
		for(InterfaceColConfig config:configList){
			if(config.getColType()==InterfaceConfig.advanceType || config.getColType()==InterfaceConfig.groupType
					 || config.getColType()==InterfaceConfig.groupAdvanceType  || config.getColType()==InterfaceConfig.globalType){
				colList.add(config);
			}
		}
		return colList;
	}
	
	@Override
	public List<InterfaceColConfig> loadInterfaceParamsColCfgList(int sourceID) {
		List<InterfaceColConfig> configList=interfaceColConfigDao.loadInterfaceColConfig(sourceID);
		List<InterfaceColConfig> colList=new ArrayList<InterfaceColConfig>();
		for(InterfaceColConfig config:configList){
			if(config.getColType()==InterfaceConfig.paramsType || config.getColType()==InterfaceConfig.paramsCycleType || config.getColType()==InterfaceConfig.paramsOut){
				colList.add(config);
			}
		}
		return colList;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer updateInterfaceParamsCol(int sourceID, int colID, List<Object> param_list) {
		ResultContainer container=new ResultContainer();
		
		InterfaceColConfig cfg=(InterfaceColConfig)interfaceColConfigDao.loadEntity(InterfaceColConfig.class, colID);
				
		String classname=interfaceService.getIDClassMap().get(Integer.valueOf(param_list.get(1).toString()));
		cfg.setFuncClass(classname);
		cfg.setColType(interfaceService.getAdditionFuncBean(classname).getFuncType());
		cfg.setFuncParams(param_list.get(2).toString());
		
		interfaceColConfigDao.updateEntityBean(cfg);
		container.setObjID(cfg.getId());
		
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer updateColParamsCol(int sourceID, int colID, List<Object> param_list) {
		ResultContainer container=new ResultContainer();
		
		InterfaceColConfig cfg=(InterfaceColConfig)interfaceColConfigDao.loadEntity(InterfaceColConfig.class, colID);
		cfg.setColname(param_list.get(0).toString());
		cfg.setColType(Integer.valueOf(param_list.get(1).toString()));
		interfaceColConfigDao.updateEntityBean(cfg);
		container.setObjID(cfg.getId());
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer updateDeleteColParamsCol(int sourceID, int colID, List<Object> param_list) {
		ResultContainer container=new ResultContainer();
		InterfaceColConfig cfg=(InterfaceColConfig)interfaceColConfigDao.loadEntity(InterfaceColConfig.class, colID);
		cfg.setColname(param_list.get(0).toString());
		cfg.setColType(InterfaceConfig.mergeDelKey);
		interfaceColConfigDao.updateEntityBean(cfg);
		container.setObjID(cfg.getId());
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer createInterfaceBasicCol(int sourceID, List<Object> param_list) {
		ResultContainer container=new ResultContainer();
		
		InterfaceColConfig cfg=new InterfaceColConfig();
		cfg.setInterfacesource(new InterfaceSource(sourceID));
		
		cfg.setColname(param_list.get(0).toString());
		cfg.setColNO(Integer.valueOf(param_list.get(1).toString()));
		cfg.setColStartNO(Integer.valueOf(param_list.get(2).toString()));
		cfg.setColEndNo(Integer.valueOf(param_list.get(3).toString()));
			
		String classname=interfaceService.getIDClassMap().get(Integer.valueOf(param_list.get(4).toString()));
		
		cfg.setFuncClass(classname);
		if(classname==null){
			cfg.setColType(InterfaceConfig.baseType);
		}else{
			cfg.setColType(interfaceService.getAdditionFuncBean(classname).getFuncType());
		}
		
		interfaceColConfigDao.saveEntityBean(cfg);
		container.setObjID(cfg.getId());
		
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public MsgContainer createAdapterBasicCol(int dataSetID, List<HashMap<String, Object>> param_list) {
		List<AdapterColConfig> colcfgList=adapterDao.loadAdapterColConfig(dataSetID,new int[]{InterfaceConfig.baseType});
		for (AdapterColConfig adapterColConfig : colcfgList) {
			adapterDao.deleteEntity(adapterColConfig);
		}
		
		MsgContainer container=new MsgContainer();
		for (HashMap<String, Object> hashMap : param_list) {
			AdapterColConfig acc=new AdapterColConfig();
			acc.setColname(hashMap.get("col").toString());
			acc.setColType(InterfaceConfig.baseType);
			acc.setDataSetID(dataSetID);
			
			interfaceColConfigDao.saveEntityBean(acc);
			container.setErrorMsg("基础列编号:"+acc.getId()+"创建成功");;
		}
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer createInterfaceParamsCol(int sourceID, List<Object> param_list) {
		ResultContainer container=new ResultContainer();

		InterfaceColConfig cfg=new InterfaceColConfig();
		cfg.setInterfacesource(new InterfaceSource(sourceID));
			
		cfg.setColname(param_list.get(0).toString());
				
		String classname=interfaceService.getIDClassMap().get(Integer.valueOf(param_list.get(1).toString()));
		cfg.setFuncClass(classname);
		cfg.setColType(interfaceService.getAdditionFuncBean(classname).getFuncType());
		cfg.setFuncParams(param_list.get(2).toString());
				
		interfaceColConfigDao.saveEntityBean(cfg);
		container.setObjID(cfg.getId());
		
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer createUpdateColParamsCol(int sourceID, List<Object> param_list) {
		ResultContainer container=new ResultContainer();

		InterfaceColConfig cfg=new InterfaceColConfig();
		cfg.setInterfacesource(new InterfaceSource(sourceID));
		cfg.setColname(param_list.get(0).toString());
		cfg.setColType(Integer.valueOf(param_list.get(1).toString()));
		interfaceColConfigDao.saveEntityBean(cfg);
		container.setObjID(cfg.getId());
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer createDeleteColParamsCol(int sourceID, List<Object> param_list) {
		ResultContainer container=new ResultContainer();

		InterfaceColConfig cfg=new InterfaceColConfig();
		cfg.setInterfacesource(new InterfaceSource(sourceID));
		cfg.setColname(param_list.get(0).toString());
		cfg.setColType(InterfaceConfig.mergeDelKey);
		interfaceColConfigDao.saveEntityBean(cfg);
		container.setObjID(cfg.getId());
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer createInterfaceAdvanceCol(int sourceID, List<List<String>> param_list) {
		ResultContainer container=new ResultContainer();
		//获取改接口所有的增强列，全局变量，group配置
		List<InterfaceColConfig> colcfgList=interfaceColConfigDao.loadCfgOrderByTypeAndID(sourceID, 
				new int[]{InterfaceConfig.advanceType,InterfaceConfig.globalType,InterfaceConfig.groupType,InterfaceConfig.groupAdvanceType});
		HashMap<String, InterfaceColConfig> colConfigHM=new HashMap<String, InterfaceColConfig>();
		for(InterfaceColConfig colcfg:colcfgList){
			colConfigHM.put(colcfg.getColname(), colcfg);
		}
		
		int newid=0;
		
		//如果有同名，则是更新。如果是新的变量，则新建。目前是一次性更新和保存所有配置
		for(int i=0;i<param_list.size();i++){
			List<String> parms = param_list.get(i);
			String colname=parms.get(0);
			int intfaceID=Integer.valueOf(parms.get(1));
			String colParams=parms.get(2);
			
			if(colConfigHM.containsKey(colname)){
				InterfaceColConfig cfg=colConfigHM.get(colname);
				cfg.setColname(colname);

				String classname=interfaceService.getIDClassMap().get(intfaceID);
				cfg.setFuncClass(classname);
				cfg.setColType(interfaceService.getAdditionFuncBean(classname).getFuncType());
				cfg.setFuncParams(colParams);
				cfg.setOrderNum(i);

				interfaceColConfigDao.updateEntityBean(cfg);
			}else{
				InterfaceColConfig cfg=new InterfaceColConfig();
				cfg.setInterfacesource(new InterfaceSource(sourceID));
				
				cfg.setColname(colname);
					
				String classname=interfaceService.getIDClassMap().get(intfaceID);
				cfg.setFuncClass(classname);
				cfg.setColType(interfaceService.getAdditionFuncBean(classname).getFuncType());
				cfg.setFuncParams(colParams);
				cfg.setOrderNum(i);
				
				interfaceColConfigDao.saveEntityBean(cfg);
				newid=cfg.getId();
			}
		}
		container.setObjID(newid);
		
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public MsgContainer  createNewAdapterColList(int dataSetID, List<HashMap<String, Object>> param_list) {
		MsgContainer container=new MsgContainer();
		List<AdapterColConfig> colcfgList=adapterDao.loadAdapterColConfig(dataSetID, 
				new int[]{InterfaceConfig.advanceType,InterfaceConfig.globalType,InterfaceConfig.groupType,InterfaceConfig.groupAdvanceType});
		for (AdapterColConfig acc : colcfgList) {
			adapterDao.deleteEntity(acc);
		}
		//保存临时表到AdapterColConfig
		for(int i=0;i<param_list.size();i++){
			HashMap<String, Object> parms = param_list.get(i);
			String colname=(String) parms.get("exColName");
			int functionID=(int) parms.get("exColfunctionID");
			String funcParams=(String) parms.get("exColFuncStr");
			
			AdapterColConfig cfg=new AdapterColConfig();
			cfg.setDataSetID(dataSetID);
			cfg.setColname(colname);
			String classname=interfaceService.getIDClassMap().get(functionID);
			cfg.setFuncClass(classname);
			cfg.setColType(interfaceService.getAdditionFuncBean(classname).getFuncType());
			cfg.setFuncParams(funcParams);
			cfg.setOrderNum(i);
			interfaceColConfigDao.saveEntityBean(cfg);
			container.setErrorMsg("id:"+cfg.getId()+" 保存成功");
		}
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public MsgContainer createNewAdapterAdvanceCol(int dataSetID,String exColName,int exColFunctionID,String exColFuncStr) {
		MsgContainer container=new MsgContainer();
		//获取改接口所有的增强列存入临时表中，全局变量，group配置
		List<DataSetColTemp> tempList=adapterDao.loadDataSetColTemp(dataSetID,
				new int[]{InterfaceConfig.advanceType,InterfaceConfig.globalType,InterfaceConfig.groupType,InterfaceConfig.groupAdvanceType});
		HashMap<String, DataSetColTemp> colConfigHM=new HashMap<String, DataSetColTemp>();
		for(DataSetColTemp templist:tempList){
			colConfigHM.put(templist.getColname(), templist);
		}
		
		if(colConfigHM.containsKey(exColName)){
			DataSetColTemp tempCol=colConfigHM.get(exColName);
			tempCol.setColname(exColName);
			String classname=interfaceService.getIDClassMap().get(exColFunctionID);
			tempCol.setFuncClass(classname);
			tempCol.setColType(interfaceService.getAdditionFuncBean(classname).getFuncType());
			tempCol.setFuncParams(exColFuncStr);
			interfaceColConfigDao.updateEntityBean(tempCol);
			container.setErrorMsg("增强列编号: "+tempCol.getId()+"修改成功");
		}else{
			DataSetColTemp tempCol=new DataSetColTemp();
			tempCol.setDataSetID(dataSetID);
			tempCol.setColname(exColName);
			String classname=interfaceService.getIDClassMap().get(exColFunctionID);
			tempCol.setFuncClass(classname);
			tempCol.setColType(interfaceService.getAdditionFuncBean(classname).getFuncType());
			tempCol.setFuncParams(exColFuncStr);
			interfaceColConfigDao.saveEntityBean(tempCol);
			container.setErrorMsg("增强列编号: "+tempCol.getId()+"保存成功");
		}
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public MsgContainer createAdapterBasicColParams(int dataSetID,String exColName,int exColFunctionID,String exColFuncStr) {
		MsgContainer container=new MsgContainer();
		//获取该接口所有的基础列参数存入临时表中
		List<DataSetColTemp> tempList=adapterDao.loadDataSetColTemp(dataSetID,
				new int[]{InterfaceConfig.paramsType,InterfaceConfig.paramsCycleType});
		HashMap<String, DataSetColTemp> colConfigHM=new HashMap<String, DataSetColTemp>();
		for(DataSetColTemp templist:tempList){
			colConfigHM.put(templist.getColname(), templist);
		}
		
		if(colConfigHM.containsKey(exColName)){
			DataSetColTemp tempCol=colConfigHM.get(exColName);
			tempCol.setColname(exColName);
			String classname=interfaceService.getIDClassMap().get(exColFunctionID);
			tempCol.setFuncClass(classname);
			tempCol.setColType(interfaceService.getAdditionFuncBean(classname).getFuncType());
			tempCol.setFuncParams(exColFuncStr);
			interfaceColConfigDao.updateEntityBean(tempCol);
			container.setErrorMsg("基础列参数编号: "+tempCol.getId()+"修改成功");
		}else{
			DataSetColTemp tempCol=new DataSetColTemp();
			tempCol.setDataSetID(dataSetID);
			tempCol.setColname(exColName);
			String classname=interfaceService.getIDClassMap().get(exColFunctionID);
			tempCol.setFuncClass(classname);
			tempCol.setColType(interfaceService.getAdditionFuncBean(classname).getFuncType());
			tempCol.setFuncParams(exColFuncStr);
			interfaceColConfigDao.saveEntityBean(tempCol);
			container.setErrorMsg("基础列参数编号: "+tempCol.getId()+"保存成功");
		}
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer createInterfaceFilterCol(int sourceID, List<Object> param_list, int colType) {
		ResultContainer container=new ResultContainer();
		int colcfgID=Integer.valueOf(param_list.get(0).toString());
		
		InterfaceColConfig cfg=(InterfaceColConfig)interfaceColConfigDao.loadEntity(InterfaceColConfig.class, colcfgID);
		
		if(colType==InterfaceConfig.filterType){
			cfg.setFilterFuncClass(interfaceService.getIDClassMap().get(Integer.valueOf(param_list.get(1).toString())));
			cfg.setFilterFieldValue(param_list.get(2).toString());
			cfg.setFilterType(Integer.valueOf(param_list.get(3).toString()));
		}
		
		interfaceColConfigDao.saveEntityBean(cfg);
		container.setObjID(cfg.getId());
		
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer updateInterfaceAdvanceCol(int sourceID, int colID, List<Object> param_list) {
		ResultContainer container=new ResultContainer();
		
		InterfaceColConfig cfg=(InterfaceColConfig)interfaceColConfigDao.loadEntity(InterfaceColConfig.class, colID);
		
		cfg.setColname(param_list.get(0).toString());

		String classname=interfaceService.getIDClassMap().get(Integer.valueOf(param_list.get(1).toString()));
		cfg.setFuncClass(classname);
		cfg.setColType(interfaceService.getAdditionFuncBean(classname).getFuncType());
		cfg.setFuncParams(param_list.get(2).toString());

		interfaceColConfigDao.updateEntityBean(cfg);
		container.setObjID(cfg.getId());
		
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer updateInterfaceBasicCol(int sourceID, int colID, List<Object> param_list) {
		ResultContainer container=new ResultContainer();
		
		InterfaceColConfig cfg=(InterfaceColConfig)interfaceColConfigDao.loadEntity(InterfaceColConfig.class, colID);
		
		cfg.setColname(param_list.get(0).toString());
		cfg.setColNO(Integer.valueOf(param_list.get(1).toString()));
		cfg.setColStartNO(Integer.valueOf(param_list.get(2).toString()));
		cfg.setColEndNo(Integer.valueOf(param_list.get(3).toString()));
		
		String classname=interfaceService.getIDClassMap().get(Integer.valueOf(param_list.get(4).toString()));
		cfg.setFuncClass(classname);
		if(classname==null){
			cfg.setColType(InterfaceConfig.baseType);
		}else{
			cfg.setColType(interfaceService.getAdditionFuncBean(classname).getFuncType());
		}
		
		interfaceColConfigDao.updateEntityBean(cfg);
		container.setObjID(cfg.getId());
		
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer deleteInterfaceCol(int colID) {
		ResultContainer container=new ResultContainer();
		boolean existFlag=interfaceMsgColMapDao.checkCfgColExistInMap(colID);
		if(existFlag) {
			container.setCorrectFlag(false);
			container.setErrorMsg("该列已被其它配置应用，不能被删除");
		}else {
			InterfaceColConfig cfg=(InterfaceColConfig)interfaceColConfigDao.loadEntity(InterfaceColConfig.class, colID);
			interfaceColConfigDao.deleteEntity(cfg);
		}
		return container;
	}
	
	@Override
	public HashMap<Integer, String> loadInterfaceColumnList(int sourceID) {
		List<InterfaceColConfig> configList=interfaceColConfigDao.loadInterfaceColConfig(sourceID);
		HashMap<Integer, String> colHM=new HashMap<Integer, String>();
		for(InterfaceColConfig col:configList){
			colHM.put(col.getId(), col.getColname());
		}
		return colHM;
	}
	
	@Override
	public HashMap<Integer, String> loadExpTemplateBasicAdvanceList(int sourceID) {
		List<InterfaceColConfig> configList=interfaceColConfigDao.loadExpTemplateBasicAdvanceCfg(sourceID);
		HashMap<Integer, String> colHM=new HashMap<Integer, String>();
		for(InterfaceColConfig col:configList){
			colHM.put(col.getId(), col.getColname());
		}
		return colHM;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void updateInterfaceSource(int sourceID, String msg) {
		InterfaceSource source=(InterfaceSource)interfaceSourceDao.loadEntity(InterfaceSource.class, sourceID);
		source.setMsg(msg);
		interfaceSourceDao.updateEntityBean(source);
	}
	
//	@Override
//	public String getConfigMsg(int sourceID) {
//		InterfaceSource source=(InterfaceSource)interfaceSourceDao.loadEntity(InterfaceSource.class, sourceID);
//		String msg = source.getMsg();
//		ArrayList<String> msglist = new ArrayList<String>();
//		//消息模板为空时，才会自动生成消息
//		if(msg==null || msg.equals("")){
//			if(source.getTargeDBTable()!=null && !source.getTargeDBTable().trim().equals("")){
//				String[] string = source.getTargeDBTable().split(",");
//				for(int i=0;i<string.length;i++){
//					List<String> columnList=interafaceDataCenter.createDBReadBean(targetDBType).getTableColumnNames(source);
//					msg=genSQLMsgTemplate(source.getTargeDBTable()+"_tmp", columnList);
//					msglist.add(msg);
//				}
//				
//			}else{
//				msg="";
//				
//			}
//		}
//		if(msg==null){
//			msg="";
//		}
//		return msg;
//	}
	
	@Override
	public String getConfigMsg(int sourceID) {
		InterfaceSource source=(InterfaceSource)interfaceSourceDao.loadEntity(InterfaceSource.class, sourceID);
		String msg = source.getMsg();
		//TODO 消息模板为空时，才会自动生成消息  add by wangqidong
		//如果是多条消息，则返回list转成json的格式
		if(msg==null || msg.equals("")){
			List<SQLMsgTemplate> msglist = new ArrayList<SQLMsgTemplate>();
			if(source.getTargeDBTable()!=null && !source.getTargeDBTable().trim().equals("")){
				String[] tableNames = source.getTargeDBTable().split(",");
				for(String tableName:tableNames){
					List<String> columnList=interafaceDataCenter.createDBReadBean(targetDBType).getTableColumnNames(tableName);
					SQLMsgTemplate sqlmsgt=genSQLMsgTemplate(tableName+"_tmp", columnList,tableName);
					msglist.add(sqlmsgt);
				}
				msg=JSONObject.toJSONString(msglist);
				if(msg.length()>0) {
					//老版本只支持一条消息,这里需要把[]符号去掉,同时支持老版本和新版本
					msg=msg.substring(1, msg.length()-1);
				}
				
			}else{
				msg="";
			}
		}
		if(msg==null){
			msg="";
		}
		return msg;
	}
	
//	private StringBuilder genMsgTemplateOld(StringBuilder msgBuilder, String msgHeader){
//		String fieldMsg=MsgFactory.getMsgFieldsTemplateHM().get(msgHeader.toUpperCase());
//		String[] fieldsArr=fieldMsg.split(",");
//		msgBuilder.append(msgHeader);
//		msgBuilder.append("(");
//		for(int i=0;i<fieldsArr.length;i++){
//			String field=fieldsArr[i];
//			if(field.contains("(") && field.contains(")")){
//				String subMsgHeader=field.substring(0, field.indexOf("("));
//				msgBuilder=genMsgTemplateOld(msgBuilder, subMsgHeader);
//			}else{
//				field=field.replaceAll("\\(", "");
//				field=field.replaceAll("\\)", "");
//				msgBuilder.append(InterfaceConfig.fieldStart);
//				msgBuilder.append(field);
//				msgBuilder.append(InterfaceConfig.fieldEnd);
//			}
//			if(i<fieldsArr.length-1){
//				msgBuilder.append(",");	
//			}
//		}
//		msgBuilder.append(")");
//		
//		return msgBuilder;
//	}
	
	private SQLMsgTemplate genSQLMsgTemplate(String tablenametmp, List<String> columnList,String tablename){
		SQLMsgTemplate msgt=new SQLMsgTemplate();		
		//自动生成SQL语句
		StringBuilder msgBuilder=new StringBuilder();
		if(columnList.size()>0){
			msgt.setHeader("executeSQLMsg");
			msgBuilder.append("insert into ");
			msgBuilder.append(tablenametmp);
			msgBuilder.append(" (");
			
			for(int i=0;i<columnList.size();i++){
				msgBuilder.append(columnList.get(i).replaceAll("'", ""));
				if(i<columnList.size()-1){
					msgBuilder.append(",");	
				}
			}
			msgBuilder.append(") values (");
			
			for(int i=0;i<columnList.size();i++){
				if(columnList.get(i).contains("'"))
					msgBuilder.append("'");
				msgBuilder.append(InterfaceConfig.fieldStart);
				//去除tmp,字段里不需要增加temp
				msgBuilder.append(tablename);
				msgBuilder.append(".");
				msgBuilder.append(columnList.get(i).replaceAll("'", ""));
				msgBuilder.append(InterfaceConfig.fieldEnd);
				if(columnList.get(i).contains("'"))
					msgBuilder.append("'");
				if(i<columnList.size()-1){
					msgBuilder.append(",");	
				}
			}
			msgBuilder.append(")");
			
			msgt.setSqlBody(msgBuilder.toString());
		}
	
		return msgt;
	}
	
//	private StringBuilder genMsgTemplate(StringBuilder msgBuilder, String msgHeader){
//		MsgService msgService=MsgFactory.getMsgMap().get(msgHeader);
//		String fieldMsg=msgService.msgFields();
//		String[] fieldsArr=fieldMsg.split(",");
//		msgBuilder.append("{");
//		msgBuilder.append("\"header\":");
//		msgBuilder.append("\"");
//		msgBuilder.append(InterfaceConfig.fieldStart);
//		msgBuilder.append(msgHeader);
//		msgBuilder.append(InterfaceConfig.fieldEnd);
//		msgBuilder.append("\"");
//		msgBuilder.append(",");
//		for(int i=0;i<fieldsArr.length;i++){
//			String field=fieldsArr[i];
//		
//			field=field.replaceAll("\\(", "");
//			field=field.replaceAll("\\)", "");
//			msgBuilder.append("\"");
//			msgBuilder.append(field);
//			msgBuilder.append("\"");
//			msgBuilder.append(":");
//			msgBuilder.append("\"");
//			msgBuilder.append(InterfaceConfig.fieldStart);
//			msgBuilder.append(field);
//			msgBuilder.append(InterfaceConfig.fieldEnd);
//			msgBuilder.append("\"");
//			if(i<fieldsArr.length-1){
//				msgBuilder.append(",");	
//			}
//		}
//		msgBuilder.append("}");
//		
//		return msgBuilder;
//	}
	
	@Override
	public List<String> getMsgParams(int sourceID) {
		InterfaceSource source=(InterfaceSource)interfaceSourceDao.loadEntity(InterfaceSource.class, sourceID);
		String msg=source.getMsg();
		List<String> paramsList=new ArrayList<String>();
		if(msg!=null){
			String[] msgarr=msg.split(InterfaceConfig.fieldStart);
			if(msgarr.length>1){
				for(int i=1;i<msgarr.length;i++){
					int endpoint=msgarr[i].indexOf(InterfaceConfig.fieldEnd);
					if(endpoint>0){
						paramsList.add(msgarr[i].substring(0, endpoint));
					}
				}
			}
		}
		return paramsList;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer createMsgColMap(int sourceID, List<Object> param_list) {
		ResultContainer container=new ResultContainer();
		String msgfieldname=param_list.get(0).toString();
		int cfgID=Integer.valueOf(param_list.get(1).toString());
		if(cfgID==0){
			container.setCorrectFlag(false);
			container.setErrorMsg("请选择对应的基础列/增强列");
			return container;
		}
		
		List<InterfaceMsgColMap> configList=interfaceMsgColMapDao.loadInterfaceMsgColMap(sourceID);
		for(InterfaceMsgColMap msgcol:configList){
			if(msgcol.getMsgCol().equals(msgfieldname)){
				container.setCorrectFlag(false);
				container.setErrorCode(ReturnResult.interface_duplicate_data);
				container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.interface_duplicate_data));
				return container;
			}
		}
		
		InterfaceMsgColMap msgColMap=new InterfaceMsgColMap();
		msgColMap.setMsgCol(msgfieldname);
		msgColMap.setInterfaceSource(new InterfaceSource(sourceID));
		msgColMap.setColConfig(new InterfaceColConfig(cfgID));
		interfaceMsgColMapDao.saveEntityBean(msgColMap);
		
		container.setEntityObj(msgColMap);
		container.setObjID(msgColMap.getId());
		
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public MsgContainer createAdapterMsgColMap(int adapterID, List<HashMap<String, Object>> param_list) {
		MsgContainer container=new MsgContainer();
		for(int i=0;i<param_list.size();i++){
			HashMap<String, Object> parms = param_list.get(i);
			//目标数据字段
			String msgfieldname = (String) parms.get("targetCol");
			int cfgID = (int) parms.get("sourceCol");
			if (cfgID == 0) {
				container.setCorrectFlag(false);
				container.setErrorMsg("请选择对应的基础列/增强列");
				return container;
			}

			List<AdapterMsgColMap> configList = interfaceMsgColMapDao.loadAdapterMsgColMap(adapterID);
			for (AdapterMsgColMap msgcol : configList) {
				if (msgcol.getMsgCol().equals(msgfieldname)) {
					container.setCorrectFlag(false);
					container.setErrorCode(ReturnResult.interface_duplicate_data);
					container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.interface_duplicate_data));
					return container;
				}
			}

			AdapterMsgColMap msgColMap = new AdapterMsgColMap();
			msgColMap.setMsgCol(msgfieldname);
			msgColMap.setAdapterID(adapterID);
			msgColMap.setAdacolConfigID(cfgID);
			interfaceMsgColMapDao.saveEntityBean(msgColMap);

			container.setEntityObj(msgColMap);
			container.setObjID(msgColMap.getId());

		}
		return container;
	}
	
	public List<AdapterMsgColMap> loadAdapterMsgColMapList(int adapterID) {
		List<AdapterMsgColMap> configList=interfaceMsgColMapDao.loadAdapterMsgColMap(adapterID);
		return configList;
	}
	
	public List<InterfaceMsgColMap> loadInterfaceMsgColMapList(int interfaceID) {
		List<InterfaceMsgColMap> configList=interfaceMsgColMapDao.loadInterfaceMsgColMap(interfaceID);
		return configList;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void deleteInterfaceMsgColMap(int id) {
		InterfaceMsgColMap msgMap=(InterfaceMsgColMap)interfaceMsgColMapDao.loadEntity(InterfaceMsgColMap.class, id);
		interfaceMsgColMapDao.deleteEntity(msgMap);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer updateInterfaceMsgColMap(int sourceID, int msgMapID, List<Object> param_list) {
		ResultContainer container=new ResultContainer();
		String msgfieldname=param_list.get(0).toString();
		int cfgID=Integer.valueOf(param_list.get(1).toString());
		List<InterfaceMsgColMap> configList=interfaceMsgColMapDao.loadInterfaceMsgColMap(sourceID);
		for(InterfaceMsgColMap msgcol:configList){
			if(msgcol.getMsgCol().equals(msgfieldname)){
				container.setCorrectFlag(false);
				container.setErrorCode(ReturnResult.interface_duplicate_data);
				container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.interface_duplicate_data));
				return container;
			}
		}
		
		InterfaceMsgColMap msgColMap=(InterfaceMsgColMap)interfaceMsgColMapDao.loadEntity(InterfaceMsgColMap.class, msgMapID);
		msgColMap.setMsgCol(msgfieldname);
		msgColMap.setColConfig(new InterfaceColConfig(cfgID));
		interfaceMsgColMapDao.saveEntityBean(msgColMap);
		
		container.setEntityObj(msgColMap);
		container.setObjID(msgColMap.getId());
		
		return container;
	}
	
	public List<InterfaceColConfig> loadInterfaceMapColList(int interfaceID) {
		List<InterfaceColConfig> configList=interfaceColConfigDao.loadInterfaceColConfig(interfaceID);
		List<InterfaceColConfig> colList=new ArrayList<InterfaceColConfig>();
		for(InterfaceColConfig config:configList){
			if((config.getCharTo() != null && !config.getCharTo().equals("")) ||
					(config.getCharFrom() != null && !config.getCharFrom().equals(""))){
				colList.add(config);
			}
		}
		return colList;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer createMapCharCol(int cfgID, String fromChar, String toChar) {
		InterfaceColConfig cfg=(InterfaceColConfig)interfaceColConfigDao.loadEntity(InterfaceColConfig.class, cfgID);
		cfg.setCharFrom(fromChar);
		cfg.setCharTo(toChar);
		
		interfaceColConfigDao.updateEntityBean(cfg);
		ResultContainer container=new ResultContainer();
		container.setObjID(cfg.getId());
		return container;
	}
	
	/**
	 * 获取过滤的数据
	 */
	public List<InterfaceColConfig> loadInterfaceColCfgFilterList(int sourceID) {
		List<InterfaceColConfig> configList=interfaceColConfigDao.loadInterfaceColConfig(sourceID);
		List<InterfaceColConfig> colList=new ArrayList<InterfaceColConfig>();
		for(InterfaceColConfig config:configList){
			if(config.getFilterFuncClass()!=null){
				colList.add(config);
			}
		}
		return colList;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void deleteInterfaceFilterByID(int filterID) {
		InterfaceColConfig colcfg=(InterfaceColConfig)interfaceColConfigDao.loadEntity(InterfaceColConfig.class, filterID);
		colcfg.setFilterFieldValue(null);
		colcfg.setFilterFuncClass(null);
		colcfg.setFilterType(0);

		interfaceColConfigDao.updateEntityBean(colcfg);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void updateInterfaceFilterByID(int cfgID, List<Object> param_list) {
		InterfaceColConfig colcfg=(InterfaceColConfig)interfaceColConfigDao.loadEntity(InterfaceColConfig.class, cfgID);
		colcfg.setFilterFuncClass(interfaceService.getIDClassMap().get(Integer.valueOf(param_list.get(1).toString())));
		colcfg.setFilterFieldValue(param_list.get(2).toString());
		colcfg.setFilterType(Integer.valueOf(param_list.get(3).toString()));
		interfaceColConfigDao.updateEntityBean(colcfg);
	}
	
	/**
	 * 上游数据是sql查询，根据sql语句，返回所有的列名
	 * @param sourceID
	 * @return
	 */
	@Override
	public HashMap<String, String> getSourceSQLColumnMap(int sourceID){
		HashMap<String, String> result=new HashMap<String, String>();
		InterfaceSource is=interfaceSourceDao.loadSource(sourceID);
		if(is.getChannelType()!=InterfaceConfig.dbType)
			return result;
		
		List<String> columnList=interafaceDataCenter.createDBReadBean(is.getFileType()).getSourceTableColumnNames(is);
		for(String col:columnList){
			result.put(col, col);
		}
		
		List<String> globalValsList=InterfaceConfig.globalValsList;
		for(String col:globalValsList) {
			result.put(col, col);
		}
		
		return result;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void autoGenerateSQLBasicColumn(int interfaceSourceID) {
		//根据sql语句自动创建基础列
		InterfaceSource is=interfaceSourceDao.loadSource(interfaceSourceID);
		if(is.getChannelType()!=InterfaceConfig.dbType)
			return;
		
		List<String> columnList=interafaceDataCenter.createDBReadBean(is.getFileType()).getSourceTableColumnNames(is);
		
		for(int i=0;i<columnList.size();i++){
			InterfaceColConfig cfg=new InterfaceColConfig();
			cfg.setInterfacesource(is);
			
			cfg.setColname(columnList.get(i));
			cfg.setColNO(i+1);
			cfg.setColStartNO(-1);
			cfg.setColEndNo(-1);
				
			cfg.setColType(InterfaceConfig.baseType);
			
			interfaceColConfigDao.saveEntityBean(cfg);
		}
		
		List<String> globalValsList=InterfaceConfig.globalValsList;
		int basenum=columnList.size();
		for(int i=0;i<globalValsList.size();i++){
			InterfaceColConfig cfg=new InterfaceColConfig();
			cfg.setInterfacesource(is);
			cfg.setColname(globalValsList.get(i));
			cfg.setColNO(i+1+basenum);
			cfg.setColStartNO(-1);
			cfg.setColEndNo(-1);
				
			cfg.setColType(InterfaceConfig.baseType);
			
			interfaceColConfigDao.saveEntityBean(cfg);
		}
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void autoGenExportSQLBasicColumn(int sourceID) {
		//根据sql语句自动创建基础列
		InterfaceSource is=interfaceSourceDao.loadSource(sourceID);
		if(is.getChannelType()!=InterfaceConfig.dbType)
			return;
		
		List<String> columnList=interafaceDataCenter.createDBReadBean(is.getFileType()).getSourceTableColumnNames(is);
		
		for(int i=0;i<columnList.size();i++){
			InterfaceColConfig cfg=new InterfaceColConfig();
			cfg.setInterfacesource(is);
			
			cfg.setColname(columnList.get(i));
			cfg.setColNO(i+1);
			cfg.setColStartNO(-1);
			cfg.setColEndNo(-1);
			cfg.setFuncClass(columnList.get(i));
			cfg.setColType(InterfaceConfig.exportBaseType);
			
			interfaceColConfigDao.saveEntityBean(cfg);
		}
		
		List<String> globalValsList=InterfaceConfig.globalValsList;
		int basenum=columnList.size();
		for(int i=0;i<globalValsList.size();i++){
			InterfaceColConfig cfg=new InterfaceColConfig();
			cfg.setInterfacesource(is);
			cfg.setColname(globalValsList.get(i));
			cfg.setColNO(i+1+basenum);
			cfg.setColStartNO(-1);
			cfg.setColEndNo(-1);
			cfg.setFuncClass(globalValsList.get(i));
				
			cfg.setColType(InterfaceConfig.exportBaseType);
			
			interfaceColConfigDao.saveEntityBean(cfg);
		}
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void autoGenerateSQLParamsColumn(int interfaceSourceID) {
		InterfaceSource is=interfaceSourceDao.loadSource(interfaceSourceID);
		if(is.getChannelType()!=InterfaceConfig.dbType)
			return;
		String sql=is.getDbSQL();
		
		List<String> paramsList=new ArrayList<String>();
		while(sql.contains(":")){
			String param=sql.substring(sql.indexOf(":"),sql.length());
			if(param.contains(" ")){
				param=param.substring(0, param.indexOf(" "));
			}
			
			String finalParam=param.replace(":", "");
			if(paramsList.contains(finalParam))
				continue;
			else
				paramsList.add(finalParam);
			
			InterfaceColConfig cfg=new InterfaceColConfig();
			cfg.setInterfacesource(is);
			cfg.setFuncClass("");
			cfg.setFuncParams("");
			cfg.setColname(finalParam);
				
			cfg.setColType(InterfaceConfig.paramsType);
			
			sql=sql.replace(param, "");
			
			interfaceColConfigDao.saveEntityBean(cfg);
		}
	}
	
	/**
	 * 获取任务的当前状态
	 * @return
	 */
	public List<JobStatusDto> getLatestJobStatus(int sourceType, String category, String sourcename){
		List<InterfaceLog> logList=interfaceLogDao.getLatestLog();
		List<InterfaceSource> sourceList=interfaceSourceDao.loadInterfaceByType(sourceType, category, sourcename);
		
		List<JobStatusDto> dtoList=new ArrayList<JobStatusDto>();
		
		HashMap<Integer, String> sourceMap=new HashMap<Integer, String>();
		for(InterfaceSource is:sourceList){
			sourceMap.put(is.getSourceID(), is.getSourceName());
		}
		
		for(InterfaceSource source:sourceList){
			JobStatusDto dto=new JobStatusDto();
			dto.setSourceID(source.getSourceID());
			dto.setJobName(source.getSourceName());
			dto.setJobDesc(source.getSourceDesc());
			dto.setDataConn(InterfaceConfig.dbTypeOption.get(source.getFileType()));
			if(sourceType==InterfaceSourceType.exportType)
				dto.setFileFormat(InterfaceConfig.fileTypeOption.get(source.getOutFileType()));
			else
				dto.setFileFormat(InterfaceConfig.fileTypeOption.get(source.getFileType()));
			
			boolean exeflag=false;
			for(InterfaceLog log:logList){
				if(log.getSourceID()==source.getSourceID()){
					dto.setLastStartTime(DateUtil.convertD2YYYY_MM_DDHHMMSS(log.getStartTime()));
					dto.setJobStatus(AdapterRunStatus.optionHM.get(log.getCurStatus()));
					if(log.getCurStatus()==AdapterRunStatus.running){
						//正在运行中
						dto.setLastEndTime("-");
						dto.setLastStatus("-");
					}else{
						dto.setLastEndTime(DateUtil.convertD2YYYY_MM_DDHHMMSS(log.getEndTime()));
						dto.setLastStatus(AdapterRunStatus.successFailedHM.get(log.isResultFlag()));
					}
					exeflag=true;
					break;
				}
			}
			
			if(!exeflag){
				dto.setLastStartTime("-");
				dto.setLastEndTime("-");
				dto.setLastStatus("-");
				dto.setJobStatus(AdapterRunStatus.optionHM.get(AdapterRunStatus.stop));
			}
			
			if(source.isAutoFlag()){
				if(InterfaceAutoType.getByValue(source.getAutoType())==InterfaceAutoType.eachNMinutes){
					dto.setNextStartTime("每隔"+source.getAutoNDays()+"分钟");
				}else{
					dto.setNextStartTime(source.getAutoTime());
				}
			}else{
				dto.setNextStartTime("-");
			}
			if(sourceMap.containsKey(source.getDependency())){
				dto.setDepenceAdapter(sourceMap.get(source.getDependency()));
			}else{
				dto.setDepenceAdapter("-");
			}
			
			dtoList.add(dto);
		}
		
		return dtoList;
	}

	/**
	 * 获取目标表的使用到的字段。目标是直接查询目标表的字段，现在可以考虑从消息里的字段中获取，因为更新的字段肯定存在于消息中。这样可以支持多表匹配，又增加效率
	 */
	@Override
	public HashMap<String, String> getDBTableFields(String interfaceID) {
		int interfaceSourceID=Integer.valueOf(interfaceID);
		HashMap<String, String> fields = new HashMap<String, String>();
		InterfaceSource source=(InterfaceSource)interfaceSourceDao.loadEntity(InterfaceSource.class,interfaceSourceID);
		
		if(source.getTargeDBTable()!=null && !source.getTargeDBTable().trim().equals("")){
			//TODO这里也改了一下
			String[] tableNames = source.getTargeDBTable().split(",");
			int tablenums=tableNames.length;
			for(String tableName:tableNames){
				List<String> columnList=interafaceDataCenter.createDBReadBean(targetDBType).getTableColumnNames(tableName);
				for (String value : columnList) {
					if(tablenums>1) {
						//如果目标表的数量大于1,则用表名作为字段的前缀
						value=tableName+"."+value;
					}
					fields.put(value, value);
				}
			}
			
		}
		
		return fields;
	}
	
	@Override
	public HashMap<String, Object> loadBasicColumns(int interfaceID) {
		InterfaceSource is=loadInterfaceSource(interfaceID);
		HashMap<String, Object> basicData=new HashMap<String, Object>();
		basicData.put("name", is.getSourceName());
		basicData.put("desc",is.getSourceDesc());
		basicData.put("category",is.getSourceSecType());
		basicData.put("type",is.getSourceSecType());
		basicData.put("trantype",is.getTranType());
		basicData.put("deliverType",is.getChannelType());
		basicData.put("user",is.getChannelAccount());
		basicData.put("pwd",AESUtil.Decrypt(is.getChannelPWD()));
		basicData.put("fileformat",is.getFileType());
		basicData.put("fileencode",is.getEnCodeType());
		basicData.put("fileloc",is.getFilepath());
		basicData.put("filename",is.getFileName());
		basicData.put("multifiles",is.isFileMultiFlag());
		basicData.put("tablename",is.getTablename());
		basicData.put("startcol",is.getTableStartLine());
		basicData.put("delimiter",is.getTableSpilitChar());
		basicData.put("end",is.getTableEndChar());
		basicData.put("holiday",is.getHoliday());
		basicData.put("autodo",is.isAutoFlag());
		basicData.put("autoDateFormat",is.getAutoType());
		basicData.put("fromDay",is.getAutoNDays());
		basicData.put("autoTime",is.getAutoTime());
		basicData.put("moveto",is.getBackupPath());
		basicData.put("dependency",is.getDependency());
		
		basicData.put("startDate",is.getStartDate());
		basicData.put("startTime",is.getStartTime());
		
		basicData.put("dbFormat", is.getFileType());
		basicData.put("dbURL", is.getFilepath());
		if(is.getChannelType()==InterfaceConfig.dbType){
			basicData.put("dbSQL", is.getDbSQL());
		}else{
			basicData.put("dbSQL", "");
		}
		basicData.put("dbTable", is.getTargeDBTable());
		basicData.put("afterCallClass", is.getAfterCallClass());
		
		if(is.getDepNoDataGoFlag()==0) {
			//空数据不继续执行下去
			basicData.put("depNoDataGoFlag", false);
		}else
			basicData.put("depNoDataGoFlag", true);
		
		return basicData;
	}
	
	@Override
	public List<InterfaceSource> loadExpInterfaceSourceList(String sourcename, String expClassID) {
		if(expClassID==null) {
			return interfaceSourceDao.loadExpInterfaceSourceList();
		}else {
			String classname=exportSource.getIdClassMap().get(Integer.parseInt(expClassID));
			return interfaceSourceDao.loadExpInterfaceSourceList(sourcename, classname);
		}
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer createExpInterfaceSource(List<Object> paramList) {
		ResultContainer container=new ResultContainer();
		
		try{
			InterfaceSource is=new InterfaceSource();
			is.setSourceName(paramList.get(0).toString());
			is.setSourceDesc(paramList.get(1).toString());
			is.setExpSourceClass(exportSource.getIdClassMap().get(Integer.valueOf(paramList.get(2).toString())));
			is.setChannelType(Integer.valueOf(paramList.get(3).toString()));
			is.setChannelAccount(paramList.get(4).toString());
			is.setChannelPWD(AESUtil.Encrypt(paramList.get(5).toString()));
			is.setFileType(Integer.valueOf(paramList.get(6).toString()));
			is.setEnCodeType(Integer.valueOf(paramList.get(7).toString()));
			is.setFilepath(paramList.get(8).toString());
			is.setFileName(paramList.get(9).toString());
			
			int multiInt=Integer.valueOf(paramList.get(10).toString());
			if(multiInt==0){
				//多投资组合分开导出
				is.setFileMultiFlag(true);
			}else{
				//多投资组合合并导出
				is.setFileMultiFlag(false);
			}
			
			is.setTablename(paramList.get(11).toString());
			
			String startLineStr=paramList.get(12).toString();
			if(!startLineStr.equals("")){
				is.setTableStartLine(Integer.valueOf(startLineStr));
			}
			is.setTableSpilitChar(paramList.get(13).toString());
			is.setTableEndChar(paramList.get(14).toString());
			//15	to-do pending
			is.setBackupFlag(Boolean.valueOf(paramList.get(16).toString()));
			is.setHoliday(paramList.get(17).toString());
			is.setAutoFlag(Boolean.valueOf(paramList.get(18).toString()));
			is.setAutoType(Integer.valueOf(paramList.get(19).toString()));
			String ndays=paramList.get(20).toString();
			if(!ndays.equals("")){
				is.setAutoNDays(Integer.valueOf(ndays));
			}
			is.setAutoTime(paramList.get(21).toString());
			
			interfaceSourceDao.saveEntityBean(is);
			container.setObjID(is.getSourceID());
			
			addAutoJob(is);
			
		}catch(NumberFormatException ne){
			container.setErrorMsg(ne.getMessage());
			container.setCorrectFlag(false);
		}
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer createExpInterfaceBasicCol(int sourceID, List<Object> param_list) {
		ResultContainer container=new ResultContainer();
		InterfaceColConfig cfg=new InterfaceColConfig();
		if(param_list.size()==5) {
			cfg.setInterfacesource(new InterfaceSource(sourceID));
			
			cfg.setColname(param_list.get(0).toString());
			cfg.setColNO(Integer.valueOf(param_list.get(1).toString()));
			cfg.setColStartNO(Integer.valueOf(param_list.get(2).toString()));
			cfg.setColEndNo(Integer.valueOf(param_list.get(3).toString()));
				
			String classname=interfaceService.getIDClassMap().get(Integer.valueOf(param_list.get(4).toString()));
			
			cfg.setFuncClass(classname);
			if(classname==null){
				cfg.setColType(InterfaceConfig.exportBaseType);
			}else{
				cfg.setColType(interfaceService.getAdditionFuncBean(classname).getFuncType());
			}
			
			interfaceColConfigDao.saveEntityBean(cfg);
			container.setObjID(cfg.getId());
			
			return container;
		}else {
			cfg.setInterfacesource(new InterfaceSource(sourceID));
			
			cfg.setColname(param_list.get(0).toString());
			String classname=exportSource.fetchModelFieldsNameHM(sourceID).get(Integer.valueOf(param_list.get(1).toString()));
			
			cfg.setFuncClass(classname);
			cfg.setColType(InterfaceConfig.exportBaseType);
			
			interfaceColConfigDao.saveEntityBean(cfg);
			container.setObjID(cfg.getId());
		}
		
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer updateExpInterfaceBasicCol(int sourceID, int colID, List<Object> param_list) {
		ResultContainer container=new ResultContainer();
		
		InterfaceColConfig cfg=(InterfaceColConfig)interfaceColConfigDao.loadEntity(InterfaceColConfig.class, colID);
		cfg.setColname(param_list.get(0).toString());
		String classname=exportSource.fetchModelFieldsNameHM(sourceID).get(Integer.valueOf(param_list.get(1).toString()));
		cfg.setFuncClass(classname);
		interfaceColConfigDao.updateEntityBean(cfg);
		container.setObjID(cfg.getId());
		
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer updateExpInterfaceSource(int sourceID, List<Object> paramList) {
		InterfaceSource is=loadInterfaceSource(sourceID);
		
		ResultContainer container=new ResultContainer();
		
		try{
			is.setSourceName(paramList.get(0).toString());
			is.setSourceDesc(paramList.get(1).toString());
			is.setExpSourceClass(exportSource.getIdClassMap().get(Integer.valueOf(paramList.get(2).toString())));
			is.setChannelType(Integer.valueOf(paramList.get(3).toString()));
			is.setChannelAccount(paramList.get(4).toString());
			is.setChannelPWD(AESUtil.Encrypt(paramList.get(5).toString()));
			is.setFileType(Integer.valueOf(paramList.get(6).toString()));
			is.setEnCodeType(Integer.valueOf(paramList.get(7).toString()));
			is.setFilepath(paramList.get(8).toString());
			is.setFileName(paramList.get(9).toString());
			
			int multiInt=Integer.valueOf(paramList.get(10).toString());
			if(multiInt==0){
				//多投资组合分开导出
				is.setFileMultiFlag(true);
			}else{
				//多投资组合合并导出
				is.setFileMultiFlag(false);
			}
			
			is.setTablename(paramList.get(11).toString());
			
			String startLineStr=paramList.get(12).toString();
			if(!startLineStr.equals("")){
				is.setTableStartLine(Integer.valueOf(startLineStr));
			}
			is.setTableSpilitChar(paramList.get(13).toString());
			is.setTableEndChar(paramList.get(14).toString());
			//15	to-do pending
			is.setBackupFlag(Boolean.valueOf(paramList.get(16).toString()));
			is.setHoliday(paramList.get(17).toString());
			is.setAutoFlag(Boolean.valueOf(paramList.get(18).toString()));
			is.setAutoType(Integer.valueOf(paramList.get(19).toString()));
			is.setAutoTime(paramList.get(20).toString());
			
			interfaceSourceDao.updateEntityBean(is);
			container.setObjID(is.getSourceID());
			
			if(is.isAutoFlag()){
				modifyAutoJob(is);
			}else{
				removeAutoJob(is);
			}
		}catch(NumberFormatException ne){
			container.setErrorMsg(ne.getMessage());
			container.setCorrectFlag(false);
		}
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer createExpInterfaceOutputCol(int sourceID, List<Object> param_list) {
		ResultContainer container=new ResultContainer();
		
		InterfaceColConfig cfg=new InterfaceColConfig();
		cfg.setInterfacesource(new InterfaceSource(sourceID));
		
		cfg.setColname(param_list.get(0).toString());
		cfg.setColNO(Integer.valueOf(param_list.get(1).toString()));
		
		cfg.setFuncParams(param_list.get(2).toString());
		cfg.setColType(InterfaceConfig.exportFinalType);
		
		
		interfaceColConfigDao.saveEntityBean(cfg);
		container.setObjID(cfg.getId());
		
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer updateExpInterfaceOutputCol(int sourceID, int colID, List<Object> param_list) {
		ResultContainer container=new ResultContainer();
		
		InterfaceColConfig cfg=(InterfaceColConfig)interfaceColConfigDao.loadEntity(InterfaceColConfig.class, colID);
		cfg.setColname(param_list.get(0).toString());
		cfg.setColNO(Integer.valueOf(param_list.get(1).toString()));
		
		cfg.setFuncParams(param_list.get(2).toString());
		interfaceColConfigDao.updateEntityBean(cfg);
		container.setObjID(cfg.getId());
		
		return container;
	}
	

	private JobModel<AutoJobAction> setJobModel(InterfaceSource source){
		JobModel<AutoJobAction> job = new JobModel<AutoJobAction>();
		job.setJobID(source.getSourceID());
		job.setJobName(source.getSourceName());
		job.setJobGroup(AdapterSysInit.AdapterProjectGroup);
		job.setCronExp(InterfaceAutoType.genAutoJobTime(source.getAutoType(), source.getAutoNDays(), source.getAutoTime()));
		job.setJobClass(AutoJobAction.class);
		
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
	
	private void addAutoJob(InterfaceSource source){
		if(source.isAutoFlag()){
			JobModel<AutoJobAction> job=setJobModel(source);
			try{
				jobManager.addJob(job);
			}catch(SchedulerException se){
				logger.error("Error", se);
			}
		}
	}
	
	private void modifyAutoJob(InterfaceSource source){
		if(source.isAutoFlag()){
			JobModel<AutoJobAction> job=setJobModel(source);
			try{
				jobManager.modifyJobTime(job);
			}catch(SchedulerException se){
				logger.error("Error", se);
			}
		}
	}
	
	private void removeAutoJob(InterfaceSource source){
		JobModel<AutoJobAction> job=setRemoveJobModel(source);
		try{
			jobManager.removeJob(job);
		}catch(SchedulerException se){
			logger.error("Error", se);
		}
	}
	
	@Override
	public AdapterJobResultDto getAdapterLatestStatus(int sourceID, Date executeTime) {
		AdapterJobResultDto dto=new AdapterJobResultDto();
		dto.setAdapterID(sourceID);
		InterfaceLog log=interfaceLogDao.getLatestLog(sourceID, executeTime);
		if(log!=null) {
			if(log.isResultFlag()) {
				//成功
				dto.setExecuteStatus(1);
			}else {
				if(log.getCurStatus()==AdapterRunStatus.running) {
					//运行中
					dto.setExecuteStatus(3);
				}else {
					//失败
					dto.setExecuteStatus(2);
				}
			}
			
			dto.setErrorMsg(log.getErrorMsg());
			dto.setDeleteNum(log.getDelRecords());
			dto.setSuccessNum(log.getSuccessNum());
			dto.setFailedNum(log.getFailedNum());
			dto.setStartTime(DateUtil.convertD2YYYY_MM_DDHHMMSS(log.getStartTime()));
			dto.setEndTime(DateUtil.convertD2YYYY_MM_DDHHMMSS(log.getEndTime()));
			dto.setOutParams(log.getOutParams());
			ExeRecordInfo recordinfo=JSONObject.parseObject(log.getRecordInfo(), ExeRecordInfo.class);
			dto.setWriteFilepath(recordinfo.getFilePath());
			dto.setWriteFileSize(recordinfo.getFileSize());
		}
		return dto;
	}
}


