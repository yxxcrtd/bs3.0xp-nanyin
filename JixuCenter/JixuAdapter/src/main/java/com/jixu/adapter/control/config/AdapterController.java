package com.jixu.adapter.control.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.jixu.adapter.control.service.AdapterControlService;
import com.jixu.adapter.control.service.ConfigAdptService;
import com.jixu.adapter.service.impl.DataInterfaceService;
import com.jixu.adapter.service.impl.DataServiceCenter;
import com.jixu.common.controller.ExceptionControl;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.util.JsonUtil;
import com.orm.obj.Adapter;
import com.orm.obj.AdapterColConfig;
import com.orm.obj.AdapterMsgColMap;
import com.orm.obj.BasicDataSet;
import com.orm.obj.DataSetColTemp;
import com.orm.obj.DataSource;
import com.orm.obj.UnionDataSet;

@Controller
public class AdapterController extends ExceptionControl {

	@Resource(name = "adapterService")
	private AdapterControlService adapterService;

	@Resource(name = "interafaceDataCenter")
	private DataServiceCenter dataServiceCenter;

	@Resource(name = "dataInterfaceService")
	private DataInterfaceService interfaceService;

	@Resource(name = "configAdptService")
	private ConfigAdptService configService;

	//关联数据集-增加/修改行-基础列数据列表
	@RequestMapping(value = "/adapter/dataSetBasicColsOptList.do", method = RequestMethod.POST)
	public void dataSetBasicColsOptList(@RequestParam(value = "dsBasicID", required = true) String dsBasicID,
			HttpServletResponse response) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Object> data = new ArrayList<Object>();
		
		int unionDataSetID=Integer.valueOf(dsBasicID);
		UnionDataSet uds=adapterService.loadUnionDataSetDetail(unionDataSetID);
		List<AdapterColConfig> bconfigList=null;
		if(uds!=null){
			 bconfigList=configService.loadAdapterColConfigList(uds.getBasicDataSetId(), InterfaceConfig.baseType);
		}
	    if(bconfigList!=null&&bconfigList.size()>0){
	    	for(AdapterColConfig cfg:bconfigList){
		    	HashMap<String, Object> item = new HashMap<String, Object>();
		    	item.put("colID", String.valueOf(cfg.getId()));
		 		item.put("colName", cfg.getColname());
		 		data.add(item);
		    }
	    }
	    result.put("data", data);
	    JsonUtil.writeObjToResponse(response, result);
	}

	// 关联数据集-增加/修改行-增强列数据列表
	@RequestMapping(value = "/adapter/dataSetRelativeColsOptList.do", method = RequestMethod.POST)
	public void dataSetRelativeColsOptList(@RequestParam(value = "dsRelativeID", required = true) String dsRelativeID,
			HttpServletResponse response) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Object> data = new ArrayList<Object>();
		
		int unionDataSetID=Integer.valueOf(dsRelativeID);
		List<AdapterColConfig> configList=configService.loadAdapterColConfigList(unionDataSetID, InterfaceConfig.advanceType);
	        
	    for(AdapterColConfig acf:configList){
	    	HashMap<String, Object> item = new HashMap<String, Object>();
	    	item.put("exColID", String.valueOf(acf.getId()));
	 		item.put("exColName", acf.getColname());
	 		data.add(item);
	    }
	     result.put("data", data);
	     JsonUtil.writeObjToResponse(response, result);
		
	}

	//基础数据集列表
	@RequestMapping(value = "/adapter/dataSetBasicOptList.do", method = RequestMethod.POST)
	public void dataSetBasicOptList(HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Object> data = new ArrayList<Object>();
		List<BasicDataSet> list = adapterService.loadBasicDataSetList();
		for (BasicDataSet bds : list) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("option", bds.getName());
			item.put("value", bds.getId());
			data.add(item);
		}
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}

	//联合数据集的下拉菜单
	@RequestMapping(value = "/adapter/dataSetAllOptList.do", method = RequestMethod.POST)
	public void dataSetAllOptList(HttpServletResponse response) {

		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Object> data = new ArrayList<Object>();
		
		List<UnionDataSet> uList = adapterService.loadUnionDataSetList();
		for (UnionDataSet uds : uList) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("option", uds.getName());
			item.put("value", uds.getId());
			data.add(item);
		}
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}

	//提供目标数据集的基础列下拉菜单
	@RequestMapping(value = "/adapter/BasicdataSetColOptList.do", method = RequestMethod.POST)
	public void BasicdataSetColOptList(@RequestParam(value = "dsID", required = false) String dsID,
			HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Object> data = new ArrayList<Object>();
		int basicDataSetID=Integer.valueOf(dsID);
		List<AdapterColConfig> configList=configService.loadAdapterColConfigList(basicDataSetID, InterfaceConfig.baseType);
	        
	    for(AdapterColConfig cfg:configList){
	    	HashMap<String, Object> item = new HashMap<String, Object>();
	    	item.put("option", cfg.getColname());
	 		item.put("value", cfg.getColname());
	 		data.add(item);
	    }
	     result.put("data", data);
	     JsonUtil.writeObjToResponse(response, result);
	}
	
	//提供源数据集的列下拉菜单
	@RequestMapping(value = "/adapter/UniondataSetColOptList.do", method = RequestMethod.POST)
	public void UniondataSetColOptList(@RequestParam(value = "dsID", required = false) String dsID,
			HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Object> data = new ArrayList<Object>();
		int unionDataSetID=Integer.valueOf(dsID);
		List<AdapterColConfig> uconfigList=configService.loadAdapterColConfigList(unionDataSetID, InterfaceConfig.advanceType);
		
		UnionDataSet uds=adapterService.loadUnionDataSetDetail(unionDataSetID);
		List<AdapterColConfig> ubconfigList=null;
		if(uds!=null){
			 ubconfigList=configService.loadAdapterColConfigList(uds.getBasicDataSetId(), InterfaceConfig.baseType);
			 ubconfigList.addAll(uconfigList);
		}
		for(AdapterColConfig ubcfg:ubconfigList){
		   HashMap<String, Object> item = new HashMap<String, Object>();
		   item.put("option", ubcfg.getColname());
		   item.put("value", ubcfg.getColname());
		   data.add(item);
		 }
		 result.put("data", data);
		 JsonUtil.writeObjToResponse(response, result);
	}

	//todo
	@RequestMapping(value = "/adapter/genMappingBySQL.do", method = RequestMethod.POST)
	public void genMappingBySQL(HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Object> data = new ArrayList<Object>();

		HashMap<String, Object> item = new HashMap<String, Object>();
		item.put("sourceCol", "Date");
		item.put("targetCol", "UpdateDate");
		data.add(item);

		HashMap<String, Object> item1 = new HashMap<String, Object>();
		item1.put("sourceCol", "Bank");
		item1.put("targetCol", "Bankname");
		data.add(item1);

		HashMap<String, Object> item3 = new HashMap<String, Object>();
		item3.put("sourceCol", "Price");
		item3.put("targetCol", "LocalPrice");
		data.add(item3);

		HashMap<String, Object> item4 = new HashMap<String, Object>();
		item4.put("sourceCol", "Cost");
		item4.put("targetCol", "LocalCost");
		data.add(item4);

		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}

	//todo
	@RequestMapping(value = "/adapter/daOneMappingSave.do", method = RequestMethod.POST)
	public void daOneMappingSave(@RequestParam(value = "sourceCol", required = true) String sourceCol,
			@RequestParam(value = "targetCol", required = true) String targetCol, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Object> data = new ArrayList<Object>();

		HashMap<String, Object> item = new HashMap<String, Object>();
		item.put("code", "0");
		item.put("success", "1");
		item.put("message", "伪接口数据-映射保存成功:" + sourceCol + ":" + targetCol);

		result.put("status", item);
		JsonUtil.writeObjToResponse(response, result);
	}

	//数据库列表
	@RequestMapping(value = "/adapter/dataSourceOptDBList.do", method = RequestMethod.POST, name = "数据库列表")
	public void adapterList(HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Object> data = new ArrayList<Object>();
		List<DataSource> list = adapterService.loadDataSourceList();
		for (DataSource dataSource : list) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("option", dataSource.getConnName());
			item.put("value", dataSource.getId());
			data.add(item);
		}
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}

	// sql查询返回源数据表字段
	@RequestMapping(value = "/adapter/executeSql.do", method = RequestMethod.POST, name = "源数据库SQL查询")
	public void executeSql(@RequestParam(value = "dataSourceID", required = true) int dataSourceID,
			@RequestParam(value = "basicDataSql", required = true) String sql, HttpServletResponse response) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Object> data = new ArrayList<Object>();
		DataSource ds = adapterService.loadDataSoureDetail(dataSourceID);
		int dbFormat=ds.getConnType();
		String dbUser=ds.getUserName();
		String dbPwd=ds.getUserPassword();
		List<String> list=dataServiceCenter.createDBReadBean(dbFormat).getSourceTableColumnNames(ds.getHostAddress(), dbFormat, dbUser, dbPwd, sql);
		if(list!=null&&list.size()>0){
			for (String string : list) {
				HashMap<String, Object> item = new HashMap<String, Object>();
				item.put("col", string);
				data.add(item);
			}
		}
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	// sql查询返回目标数据库表字段
	@RequestMapping(value = "/adapter/executeTargetSql.do", method = RequestMethod.POST, name = "目标数据库SQL查询")
	public void executeTargetSql(@RequestParam(value = "id", required = true) String id,
					HttpServletRequest request,HttpServletResponse response) {
		
	}

	//数据库—创建
	@RequestMapping(value = "/adapter/dataSourceCreate.do", method = RequestMethod.POST, name = "适配器-数据库-创建")
	public void dataSourceCreate(@RequestParam(value = "json", required = true) String json, HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> fieldsMap = JSONObject.parseObject(json, HashMap.class);
		fieldsMap.put(MsgFields.header, "dataSourceMsg");
		fieldsMap.put(MsgFields.operation, MsgFields.add);
		MsgContainer container = executeNewMsg(request, fieldsMap);
		adapterControllerReturn(response, container, result);
		JsonUtil.writeObjToResponse(response, result);
	}

	//数据库—详情
	@RequestMapping(value = "/adapter/dataSourceDetail.do", method = RequestMethod.POST, name = "适配器-数据库-详情")
	public void dataSourceDetail(@RequestParam(value = "id", required = true) String id, HttpServletRequest request,
			HttpServletResponse response) {
		int dataSourcceID = Integer.valueOf(id);
		HashMap<String, Object> result = new HashMap<String, Object>();
		DataSource data = adapterService.loadDataSoureDetail(dataSourcceID);
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}

	//数据库—更新
	@RequestMapping(value = "/adapter/dataSourceUpdate.do", method = RequestMethod.POST, name = "适配器-数据库-更新")
	public void dataSourceUpdate(@RequestParam(value = "json", required = true) String json, HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> fieldsMap = JSONObject.parseObject(json, HashMap.class);
		fieldsMap.put(MsgFields.header, "dataSourceMsg");
		fieldsMap.put(MsgFields.operation, MsgFields.update);
		MsgContainer container = executeNewMsg(request, fieldsMap);
		adapterControllerReturn(response, container, result);
		JsonUtil.writeObjToResponse(response, result);
	}

	//数据库—删除
	@RequestMapping(value = "/adapter/dataSourceRemove.do", method = RequestMethod.POST, name = "适配器-数据库-删除")
	public void dataSourceRemove(@RequestParam(value = "id", required = true) String id, HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, String> msg = new HashMap<String, String>();
		msg.put(MsgFields.header, "dataSourceMsg");
		msg.put(MsgFields.operation, MsgFields.delete);
		msg.put(MsgFields.id, id);
		MsgContainer container = executeMsg(request, JSONObject.toJSONString(msg));
		adapterControllerReturn(response, container, result);
		JsonUtil.writeObjToResponse(response, result);
	}

	//数据库—查询
	@RequestMapping(value = "/adapter/dataSourceList.do", method = RequestMethod.POST, name = "适配器-数据库-查询")
	public void dataSourceList(@RequestParam(value = "connName", required = true) String connName,
			@RequestParam(value = "connType", required = true) String connType, HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("ID", "连接名称", "连接描述", "连接类型", "最后活动日期", "操作");
		int type = 0;
		if (connType != "" && !connType.equals("")) {
			type = Integer.valueOf(connType);
		}
		List<DataSource> list = adapterService.loadDataSourceList(connName, type);
		result.put("titles", titles);
		result.put("data", list);
		JsonUtil.writeObjToResponse(response, result);
	}

	//基础数据集-创建
	@RequestMapping(value = "/adapter/basicDataSetCreate.do", method = RequestMethod.POST, name = "适配器-基础数据集-创建")
	public void basicDataSetCreate(@RequestParam(value = "json", required = true) String json,
			HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> fieldsMap = JSONObject.parseObject(json, HashMap.class);
		fieldsMap.put(MsgFields.header, "basicDataSetMsg");
		fieldsMap.put(MsgFields.operation, MsgFields.add);
		MsgContainer container = executeNewMsg(request, fieldsMap);
		adapterControllerReturn(response, container, result);
		JsonUtil.writeObjToResponse(response, result);
	}

	//础数据集-详情
	@RequestMapping(value = "/adapter/basicDataSetDetail.do", method = RequestMethod.POST, name = "适配器-基础数据集-详情")
	public void basicDataSetDetail(@RequestParam(value = "id", required = true) String id, HttpServletRequest request,
			HttpServletResponse response) {
		int basicDataSetID = Integer.valueOf(id);
		HashMap<String, Object> result = new HashMap<String, Object>();
		BasicDataSet data = adapterService.loadBasicDataSetDetail(basicDataSetID);
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}

	//基础数据集-更新
	@RequestMapping(value = "/adapter/basicDataSetUpdate.do", method = RequestMethod.POST, name = "适配器-基础数据集-更新")
	public void basicDataSetUpdate(@RequestParam(value = "json", required = true) String json,
			HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> fieldsMap = JSONObject.parseObject(json, HashMap.class);
		fieldsMap.put(MsgFields.header, "basicDataSetMsg");
		fieldsMap.put(MsgFields.operation, MsgFields.update);
		MsgContainer container = executeNewMsg(request, fieldsMap);
		adapterControllerReturn(response, container, result);
		JsonUtil.writeObjToResponse(response, result);
	}

	//基础数据集-删除
	@RequestMapping(value = "/adapter/basicDataSetRemove.do", method = RequestMethod.POST, name = "适配器-基础数据集-删除")
	public void basicDataSetRemove(@RequestParam(value = "id", required = true) String id, HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, String> msg = new HashMap<String, String>();
		msg.put(MsgFields.header, "basicDataSetMsg");
		msg.put(MsgFields.operation, MsgFields.delete);
		msg.put(MsgFields.id, id);
		MsgContainer container = executeMsg(request, JSONObject.toJSONString(msg));
		adapterControllerReturn(response, container, result);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	//基础列计算方式列表
	@RequestMapping(value = "/adapter/basicParamFunctionMap.do", method = RequestMethod.POST, name = "基础列-计算方式")
	public void basicParamFunctionMap(HttpServletRequest request, HttpServletResponse response) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Object> data = new ArrayList<Object>();
		HashMap<Integer, String> basicColMap = interfaceService.getParamsMap();
		for (Integer key : basicColMap.keySet()) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("value", String.valueOf(key));
			item.put("option", basicColMap.get(key));
			data.add(item);
		}
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/adapter/basicDataSetList.do", method = RequestMethod.POST, name = "适配器-基础数据集-查询")
	public void basicDataSetList(@RequestParam(value = "dsName", required = true) String dsName,
			HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("ID", "数据集名称", "数据库名称", "数据库类型", "操作");
		List<BasicDataSet> list = adapterService.loadBasicDataSetList(dsName);
		result.put("titles", titles);
		result.put("data", list);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	// 保存一行基础列参数
	@RequestMapping(value = "/adapter/basicDataSetAddRow.do", method = RequestMethod.POST, name = "适配器-基础列参数-增加一行")
	public void basicDataSetAddRow(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "basicColName") String basicColName,
			@RequestParam(value = "basicFunctionID") String basicFunctionID,
			@RequestParam(value = "basicFuncStr") String basicFuncStr, HttpServletRequest request,
			HttpServletResponse response) {
			
		HashMap<String, Object> result = new HashMap<String, Object>();
		int dataSetID=Integer.valueOf(id);
		int funcID=Integer.valueOf(basicFunctionID);
		MsgContainer container=configService.createAdapterBasicColParams(dataSetID,basicColName,funcID,basicFuncStr);
		adapterControllerReturn(response, container, result);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	// 更新一行基础列参数到临时基础列
	@RequestMapping(value = "/adapter/basicDataSetUpdateRow.do", method = RequestMethod.POST, name = "适配器-联合数据集-更新增强列")
	public void basicDataSetUpdateRow(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "basicColName") String basicColName,
			@RequestParam(value = "basicFunctionID") String basicFunctionID,
			@RequestParam(value = "basicFuncStr") String basicFuncStr, HttpServletRequest request,
			HttpServletResponse response) {
			
		HashMap<String, Object> result = new HashMap<String, Object>();
		int dataSetID=Integer.valueOf(id);
		int funcID=Integer.valueOf(basicFunctionID);
		MsgContainer container=configService.createAdapterBasicColParams(dataSetID,basicColName,funcID,basicFuncStr);
		adapterControllerReturn(response, container, result);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	// 删除一行基础列参数到临时基础列数据集
	@RequestMapping(value = "/adapter/basicDataSetRemoveRow.do", method = RequestMethod.POST, name = "适配器-联合数据集-删除一行")
	public void basicDataSetRemoveRow(@RequestParam(value = "exColID", required = true) String exColID,
			HttpServletRequest request, HttpServletResponse response) {
			
		HashMap<String, Object> result = new HashMap<String, Object>();
			
		int bID=Integer.valueOf(exColID);
		adapterService.deleteDataSetColTemp(bID);
		HashMap<String, Object> status = new HashMap<String, Object>();
		status.put("message", "基础列编号：" + bID+"删除成功");
		result.put("status", status);
		JsonUtil.writeObjToResponse(response, result);
	}
	

	@RequestMapping(value = "/adapter/unionDataSetCreate.do", method = RequestMethod.POST, name = "适配器-联合数据集-创建")
	public void unionDataSetCreate(@RequestParam(value = "json", required = true) String json,
			HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> fieldsMap = JSONObject.parseObject(json, HashMap.class);
		fieldsMap.put(MsgFields.header, "unionDataSetMsg");
		fieldsMap.put(MsgFields.operation, MsgFields.add);
		MsgContainer container = executeNewMsg(request, fieldsMap);
		adapterControllerReturn(response, container, result);
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/adapter/unionDataSetDetail.do", method = RequestMethod.POST, name = "适配器-联合数据集-详情")
	public void unionDataSetDetail(@RequestParam(value = "id", required = true) String id, HttpServletRequest request,
			HttpServletResponse response) {
		int unionDataSetID = Integer.valueOf(id);
		HashMap<String, Object> result = new HashMap<String, Object>();
		UnionDataSet data = adapterService.loadUnionDataSetDetail(unionDataSetID);
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/adapter/unionDataSetUpdate.do", method = RequestMethod.POST, name = "适配器-联合数据集-更新")
	public void unionDataSetUpdate(@RequestParam(value = "json", required = true) String json,
			HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> fieldsMap = JSONObject.parseObject(json, HashMap.class);
		fieldsMap.put(MsgFields.header, "unionDataSetMsg");
		fieldsMap.put(MsgFields.operation, MsgFields.update);
		MsgContainer container = executeNewMsg(request, fieldsMap);
		adapterControllerReturn(response, container, result);
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/adapter/unionDataSetRemove.do", method = RequestMethod.POST, name = "适配器-联合数据集-删除")
	public void unionDataSetRemove(@RequestParam(value = "id", required = true) String id, HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, String> msg = new HashMap<String, String>();
		msg.put(MsgFields.header, "unionDataSetMsg");
		msg.put(MsgFields.operation, MsgFields.delete);
		msg.put(MsgFields.id, id);
		MsgContainer container = executeMsg(request, JSONObject.toJSONString(msg));
		adapterControllerReturn(response, container, result);
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/adapter/unionDataSetList.do", method = RequestMethod.POST, name = "适配器-联合数据集-查询")
	public void unionDataSetList(@RequestParam(value = "dsName", required = true) String dsName,
			HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("ID", "数据集名称", "数据库名称", "数据库类型", "操作");
		List<UnionDataSet> list = adapterService.loadUnionDataSetList(dsName);
		result.put("titles", titles);
		result.put("data", list);
		JsonUtil.writeObjToResponse(response, result);
	}

	// 保存一行增强列到临时增强列数据集
	@RequestMapping(value = "/adapter/unionDataSetAddRow.do", method = RequestMethod.POST, name = "适配器-联合数据集-增加一行-保存")
	public void unionDataSetAddRow(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "exColName") String exColName,
			@RequestParam(value = "exColFunctionID") String exColFunctionID,
			@RequestParam(value = "exColFuncStr") String exColFuncStr, HttpServletRequest request,
			HttpServletResponse response) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		int dataSetID=Integer.valueOf(id);
		int funcID=Integer.valueOf(exColFunctionID);
		MsgContainer container=configService.createNewAdapterAdvanceCol(dataSetID,exColName,funcID,exColFuncStr);
		adapterControllerReturn(response, container, result);
		JsonUtil.writeObjToResponse(response, result);
	}

	// 更新一行增强列到临时增强列数据集
	@RequestMapping(value = "/adapter/unionDataSetUpdateRow.do", method = RequestMethod.POST, name = "适配器-联合数据集-更新增强列")
	public void unionDataSetUpdateRow(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "exColID") String exColID, @RequestParam(value = "exColName") String exColName,
			@RequestParam(value = "exColFunctionID") String exColFunctionID,
			@RequestParam(value = "exColFuncStr") String exColFuncStr, HttpServletRequest request,
			HttpServletResponse response) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		int dataSetID=Integer.valueOf(id);
		int funcID=Integer.valueOf(exColFunctionID);
		MsgContainer container=configService.createNewAdapterAdvanceCol(dataSetID,exColName,funcID,exColFuncStr);
		adapterControllerReturn(response, container, result);
		JsonUtil.writeObjToResponse(response, result);
	}

	// 删除一行增强列到临时增强列数据集
	@RequestMapping(value = "/adapter/unionDataSetRemoveRow.do", method = RequestMethod.POST, name = "适配器-联合数据集-删除一行")
	public void unionDataSetRemoveRow(@RequestParam(value = "exColID", required = true) String exColID,
			HttpServletRequest request, HttpServletResponse response) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		int advanceColID=Integer.valueOf(exColID);
		adapterService.deleteDataSetColTemp(advanceColID);
		
		HashMap<String, Object> status = new HashMap<String, Object>();
		status.put("message", "增强列编号：" + exColID+"删除成功");
		result.put("status", status);
		JsonUtil.writeObjToResponse(response, result);
	}

	//保存增强列列表
	@RequestMapping(value = "/adapter/unionDataSetSaveColList.do", method = RequestMethod.POST, name = "适配器-联合数据集-保存增强列列表")
	public void unionDataSetSaveColList(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "exColListTable") String exColListTable, HttpServletRequest request,
			HttpServletResponse response) {
		
		List<HashMap<String, Object>> param_list = JsonUtil.stringToListHashmap(exColListTable);
		HashMap<String, Object> result = new HashMap<String, Object>();
		int dataSetID=Integer.valueOf(id);
		MsgContainer container=configService.createNewAdapterColList(dataSetID, param_list);
		adapterControllerReturn(response, container, result);
		JsonUtil.writeObjToResponse(response, result);
	}

	//联合数据集-功能函数名称
	@RequestMapping(value = "/adapter/unionDataSetColFunctionOptList.do", method = RequestMethod.POST, name = "适配器-联合数据集-增加一行-函数列表")
	public void unionDataSetColFunctionOptList(HttpServletRequest request, HttpServletResponse response) {

		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Object> data = new ArrayList<Object>();
		HashMap<Integer, String> map = interfaceService.getCalculateMap();
		for (Integer key : map.keySet()) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("value", String.valueOf(key));
			item.put("option", map.get(key));
			data.add(item);
		}
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}

	//联合数据集-增强列显示
	@RequestMapping(value = "/adapter/unionDataSetExColsList.do", method = RequestMethod.POST, name = "适配器-联合数据集-增强列列表")
	public void unionDataSetExColsList(@RequestParam(value = "unionDSid", required = true) String unionDSid, // 联合数据集id
			HttpServletRequest request, HttpServletResponse response) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Object> data = new ArrayList<Object>();
		 
		int dataSetID=Integer.valueOf(unionDSid);
		List<DataSetColTemp> tempList=adapterService.loadDataSetColTempByDataSetID(dataSetID);

        for(DataSetColTemp tempcol:tempList){
        	HashMap<String, Object> item = new HashMap<String, Object>();
    		item.put("exColID", String.valueOf(tempcol.getId()));
    		item.put("exColName", tempcol.getColname());
    		 if(tempcol.getFuncClass()==null || tempcol.getFuncClass()=="-"){
    			item.put("exColfunctionID", "0");
 	        }else{
 	        	item.put("exColfunctionID", interfaceService.getClassIDMap().get(tempcol.getFuncClass()));
 	        }
    		item.put("exColFuncStr", tempcol.getFuncParams());
    		data.add(item);
        }
        result.put("data", data);
        JsonUtil.writeObjToResponse(response, result);
		
	}

	@RequestMapping(value = "/adapter/adapterCreate.do", method = RequestMethod.POST, name = "数据适配器-创建")
	public void adapterCreate(@RequestParam(value = "json", required = true) String json,HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> fieldsMap = JSONObject.parseObject(json, HashMap.class);
		fieldsMap.put(MsgFields.header, "adapterMsg");
		fieldsMap.put(MsgFields.operation, MsgFields.add);
		MsgContainer container = executeNewMsg(request, fieldsMap);
		adapterControllerReturn(response, container, result);
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/adapter/adapterDetail.do", method = RequestMethod.POST, name = "数据适配器-详情")
	public void adapterDetail(@RequestParam(value = "id", required = true) String id, HttpServletRequest request,
			HttpServletResponse response) {
		int adapterID = Integer.valueOf(id);
		HashMap<String, Object> result = new HashMap<String, Object>();
		Adapter data = adapterService.loadAdapterDetail(adapterID);
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/adapter/adapterUpdate.do", method = RequestMethod.POST, name = "数据适配器-更新")
	public void adapterUpdate(@RequestParam(value = "json", required = true) String json, HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> fieldsMap = JSONObject.parseObject(json, HashMap.class);
		fieldsMap.put(MsgFields.header, "adapterMsg");
		fieldsMap.put(MsgFields.operation, MsgFields.update);
		MsgContainer container = executeNewMsg(request, fieldsMap);
		adapterControllerReturn(response, container, result);
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/adapter/adapterRemove.do", method = RequestMethod.POST, name = "数据适配器-删除")
	public void adapterRemove(@RequestParam(value = "id", required = true) String id, HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, String> msg = new HashMap<String, String>();
		msg.put(MsgFields.header, "adapterMsg");
		msg.put(MsgFields.operation, MsgFields.delete);
		msg.put(MsgFields.id, id);
		MsgContainer container = executeMsg(request, JSONObject.toJSONString(msg));
		adapterControllerReturn(response, container, result);
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/adapter/adapterList.do", method = RequestMethod.POST, name = "数据适配器-查询")
	public void adapterList(@RequestParam(value = "name", required = false) String name, HttpServletRequest request,
			HttpServletResponse response) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Object> data = new ArrayList<Object>();
		List<Adapter> list = adapterService.loadAdapterList(name);
		for (Adapter adapter : list) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("daID", String.valueOf(adapter.getId()));
			item.put("daName", adapter.getName());
			data.add(item);
		}
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	//数据库类型
	@RequestMapping(value="/adapter/dbType.do", method = RequestMethod.POST)
	public void dbType(HttpServletResponse response){
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Object> data = new ArrayList<Object>();
		
		HashMap<Integer, String> dbmap = InterfaceConfig.dbTypeOption;
		for (Integer dbTypeID : dbmap.keySet()) {
			HashMap<String, Object> item1 = new HashMap<String, Object>();
			item1.put("value", String.valueOf(dbTypeID));
			item1.put("option", dbmap.get(dbTypeID));
			data.add(item1);
		}
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	//数据库连接状态测试
	@RequestMapping(value="/adapter/dbConnTest.do", method = RequestMethod.POST)
	public void dbTest(@RequestParam(value = "dbURL", required = true) String dbURL,
					   @RequestParam(value = "dbFormat", required = true) int dbFormat,
					   @RequestParam(value = "userName", required = true) String userName,
					   @RequestParam(value = "userPassword", required = true) String userPassword,
					   HttpServletRequest request,
					   HttpServletResponse response) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> status = new HashMap<String, Object>();
		
		int statusCode=1;
		statusCode=dataServiceCenter.createDBReadBean(dbFormat).dbTest(dbURL, userName,userPassword,statusCode);
		
		status.put("success", statusCode);
		result.put("status", status);
		JsonUtil.writeObjToResponse(response, status);
	}
	
	//消息创建--映射表
	@RequestMapping(value = "/adapter/msgColCreate.do", method = RequestMethod.POST, name="适配器-消息列创建")
	public void msgColCreate(@RequestParam(value = "pid", required = true) int adapterID,
								 @RequestParam(value = "tablejson", required = true) String tablejson,
								 HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<HashMap<String, Object>> param_list = JsonUtil.stringToListHashmap(tablejson);
		MsgContainer container=configService.createAdapterMsgColMap(adapterID, param_list);
		adapterControllerReturn(response, container, result);
		JsonUtil.writeObjToResponse(response, result);
	}
	//消息详情--映射表
	@RequestMapping(value = "/adapter/msgColDetail.do", method = RequestMethod.POST, name="适配器-消息列详情")
	public void msgColDetail(@RequestParam(value = "pid", required = true) int adapterID,
								 HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
        List<Object> data = new ArrayList<Object>();
        
        List<AdapterMsgColMap> msgmapList=configService.loadAdapterMsgColMapList(adapterID);
        for(AdapterMsgColMap msgMap:msgmapList){
        	HashMap<String, Object> item = new HashMap<String, Object>();
        	item.put("sourceCol",msgMap.getAdacolConfigID());
    		item.put("targetCol", msgMap.getMsgCol());
            data.add(item);
        }
        result.put("data", data);
        JsonUtil.writeObjToResponse(response, result);
	}
	
	//基础列创建和更新
	@RequestMapping(value = "/adapter/basicColCreate.do", method = RequestMethod.POST, name="适配器-基础列创建更新")
	public void basicColCreate(@RequestParam(value = "pid", required = true) int dataSetID,
								 @RequestParam(value = "tablejson", required = true) String tablejson,
								 HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<HashMap<String, Object>> param_list = JsonUtil.stringToListHashmap(tablejson);
		MsgContainer container=configService.createAdapterBasicCol(dataSetID, param_list);
		adapterControllerReturn(response, container, result);
		JsonUtil.writeObjToResponse(response, result);
	}

}
