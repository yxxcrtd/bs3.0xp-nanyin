package com.jixu.adapter.control.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.jixu.adapter.model.ExpInterfaceSourceDto;
import com.jixu.adapter.model.JobStatusDto;
import com.jixu.adapter.types.AdapterJobResultDto;
import com.jixu.common.pojo.InterfaceColConfig;
import com.jixu.common.pojo.InterfaceMsgColMap;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.ResultContainer;
import com.orm.obj.AdapterColConfig;
import com.orm.obj.AdapterMsgColMap;

public interface ConfigAdptService {
	
	public List<InterfaceSource> loadInterfaceSourceList(String sourcename, int sourceType, String secType);
	
	public InterfaceSource loadInterfaceSource(int sourceid);
	
	public List<InterfaceColConfig> loadInterfaceColCfgList(int sourceID, int colType);
	
	public List<InterfaceColConfig> loadInterfaceAdvanceColCfgList(int sourceID);
	
	public List<AdapterColConfig> loadAdapterColConfigList(int dataSetID, int colType);
	
	public ResultContainer createInterfaceBasicCol(int sourceID, List<Object> param_list);
	
	public MsgContainer createAdapterBasicCol(int dataSetID, List<HashMap<String, Object>> param_list);
	
	public ResultContainer createInterfaceAdvanceCol(int sourceID, List<List<String>> param_list);
	
	public MsgContainer createNewAdapterAdvanceCol(int dataSetID,String exColName,int exColFunctionID,String exColFuncStr);
	
	public MsgContainer createAdapterBasicColParams(int dataSetID,String exColName,int exColFunctionID,String exColFuncStr);
	
	public MsgContainer createNewAdapterColList(int sourceID, List<HashMap<String, Object>> param_list);
	
	public ResultContainer updateInterfaceAdvanceCol(int sourceID, int colID, List<Object> param_list);
	
	public ResultContainer updateInterfaceBasicCol(int sourceID, int colID, List<Object> param_list);
	
	public ResultContainer deleteInterfaceCol(int colID);
	
	/**
	 * 改变接口的状态，逻辑删除
	 * @param sourceID
	 */
	public void deleteInterfaceSource(int sourceID);
	
	/**
	 * 导出模板可供选择的基础增强列
	 * @param sourceID
	 * @return
	 */
	public HashMap<Integer, String> loadExpTemplateBasicAdvanceList(int sourceID);
	
	public HashMap<Integer, String> loadInterfaceColumnList(int sourceID);
	
	public void updateInterfaceSource(int sourceID, String msg);
	
	public String getConfigMsg(int sourceID);
	
	public List<String> getMsgParams(int sourceID);
	
	public ResultContainer createMsgColMap(int sourceID, List<Object> param_list); 
	
	public MsgContainer createAdapterMsgColMap(int adapterID, List<HashMap<String, Object>> param_list); 
	
	public List<AdapterMsgColMap> loadAdapterMsgColMapList(int adapterID);
	
	public List<InterfaceMsgColMap> loadInterfaceMsgColMapList(int interfaceID);
	
	public void deleteInterfaceMsgColMap(int id);
	
	public ResultContainer updateInterfaceMsgColMap(int sourceID, int msgMapID, List<Object> param_list);
	
	public List<InterfaceColConfig> loadInterfaceMapColList(int interfaceID);
	
	public ResultContainer createMapCharCol(int cfgID, String fromChar, String toChar);
	
	public ResultContainer createInterfaceFilterCol(int sourceID, List<Object> param_list, int colType);
	
	public List<InterfaceColConfig> loadInterfaceColCfgFilterList(int sourceID);
	
	public void deleteInterfaceFilterByID(int filterID);
	
	public void updateInterfaceFilterByID(int cfgID, List<Object> param_list);
	
	/**
	 * 自动生成查询数据库的字段用作基础列配置
	 * @param interfaceSourceID
	 */
	public void autoGenerateSQLBasicColumn(int interfaceSourceID);
	
	public void autoGenerateSQLParamsColumn(int interfaceSourceID);
	
	/**
	 * 创参数列
	 * @param sourceID
	 * @param param_list
	 * @return
	 */
	public ResultContainer createInterfaceParamsCol(int sourceID, List<Object> param_list);
	
	public ResultContainer createUpdateColParamsCol(int sourceID, List<Object> param_list);
	
	public ResultContainer createDeleteColParamsCol(int sourceID, List<Object> param_list);
	
	public List<InterfaceColConfig> loadInterfaceParamsColCfgList(int sourceID);
	
	public ResultContainer updateInterfaceParamsCol(int sourceID, int colID, List<Object> param_list);
	
	public ResultContainer updateDeleteColParamsCol(int sourceID, int colID, List<Object> param_list);
	
	public ResultContainer updateColParamsCol(int sourceID, int colID, List<Object> param_list);
	
	/**
	 * 获取任务的当前状态
	 * @return
	 */
	public List<JobStatusDto> getLatestJobStatus(int sourceType, String category, String sourcename);
	
	/**
	 * 如果是数据库类型，获取字段；否则则返回空的hashmap
	 * @param interfaceID
	 * @return
	 */
	public HashMap<String, String> getDBTableFields(String interfaceID);
	
	/**
	 * 获取基础列
	 * @param interfaceID
	 * @return
	 */
	public HashMap<String, Object> loadBasicColumns(int interfaceID);

	public List<InterfaceSource> loadExpInterfaceSourceList(String sourcename, String expClassID);

	//public List<JobStatusDto> getLatestExpJobStatus();
	
	public ResultContainer createExpInterfaceSource(List<Object> paramList);
	
	public ResultContainer updateExpInterfaceSource(int sourceID, List<Object> paramList);
	
	public ResultContainer createExpInterfaceBasicCol(int sourceID, List<Object> param_list);
	
	public ResultContainer updateExpInterfaceBasicCol(int sourceID, int colID, List<Object> param_list);

	public ResultContainer createExpInterfaceOutputCol(int sourceID, List<Object> param_list);
	
	public ResultContainer updateExpInterfaceOutputCol(int sourceID, int colID, List<Object> param_list);
	
	/**
	 * 获取导出的详细信息
	 * @param sourceid
	 * @return
	 */
	public ExpInterfaceSourceDto loadAdtpExpInterfaceDetail(int sourceid) ;
	
	/**
	 * 上游数据是sql查询，根据sql语句，返回所有的列名
	 * @param sourceID
	 * @return
	 */
	public HashMap<String, String> getSourceSQLColumnMap(int sourceID);
	
	/**
	 * 导出模块，根据sql语句自动创建基础列
	 * @param interfaceSourceID
	 */
	public void autoGenExportSQLBasicColumn(int interfaceSourceID);
	
	/**
	 * 根据coltype类型获取配置
	 * @param sourceID
	 * @param colType
	 * @return
	 */
	public List<InterfaceColConfig> loadInterfaceColCfgList(int sourceID, int[] colType);
	
	/**
	 * 获取适配器执行该时间后第一条记录的结果
	 * @param sourceID
	 * @param executeTime
	 * @return
	 */
	public AdapterJobResultDto getAdapterLatestStatus(int sourceID, Date executeTime);
}
