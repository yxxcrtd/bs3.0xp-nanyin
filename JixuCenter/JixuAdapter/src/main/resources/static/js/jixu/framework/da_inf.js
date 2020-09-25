var funclist = {
  //导入配置列表
  importConfigList: {
    header: "importConfigList",
    inputs: { ports: "ports", category: "category", type: "type" }
  },

  //接口配置-页面显示
  interface: {
    header: "interface",
    inputs: {}
  },

  //数据适配器-页面显示
  dataAdapter: {
    header: "dataAdapter",
    inputs: {}
  },

  //接口配置-详情页面显示
  interfacePage: {
    header: "interfacePage",
    inputs: { id: "id" }
  },

  //数据适配器配置-详情页面显示
  adapterPage: {
    header: "adapterPage",
    inputs: { id: "id" }
  },

  //接口配置-创建
  interfaceCreate: {
    header: "interfaceCreate",
    inputs: {
      params: [
        "name",
        "desc",
        "category",
        "type",
        "trantype",
        "deliverType",
        "dbUser",
        "dbPwd",
        "fileformat",
        "fileencode",
        "fileloc",
        "filename",
        "multifiles",
        "dbFormat",
        "dbURL",
        "dbSQL",
        "tablename",
        "startcol",
        "delimiter",
        "end",
        "dbTable",
        "holiday",
        "autodo",
        "autoDateFormat",
        "fromDay",
        "autoTime",
        "moveto"
      ],
      json: "json"
    }
  },

  //接口配置-详情
  interfaceDetail: {
    header: "interfaceDetail",
    inputs: { id: "id" }
  },

  //接口配置-更新
  interfaceUpdate: {
    header: "interfaceUpdate",
    inputs: { id: "id", params: "params", json: "json" }
  },

  //接口配置-查询
  interfaceList: {
    header: "interfaceList",
    inputs: { name: "name", category: "category", type: "type" }
  },

  //接口配置-基础列详情
  basicColDetail: {
    header: "interfaceBasicColDetail",
    inputs: { id: "id" }
  },

  //接口配置-基础列创建
  basicColCreate: {
    header: "basicColCreate",
    inputs: {tablejson: "tableConnList" }
  },

  //接口配置-基础列更新
  basicColUpdate: {
    header: "basicColUpdate",
    inputs: { pid: "pid", id: "id", params: "params" }
  },

  //接口配置-基础列删除
  basicColRemove: {
    header: "basicColRemove",
    inputs: { pid: "pid", id: "id" }
  },

  //接口配置-增强列详情
  advanceColDetail: {
    header: "advanceColDetail",
    inputs: { id: "id" }
  },

  //接口配置-增强列创建
  advanceColCreate: {
    header: "advanceColCreate",
    inputs: { pid: "pid", params: "params" }
  },

  //接口配置-增强列更新
  advanceColUpdate: {
    header: "advanceColUpdate",
    inputs: { pid: "pid", id: "id", params: "params" }
  },

  //接口配置-增强列删除
  advanceColRemove: {
    header: "advanceColRemove",
    inputs: { pid: "pid", id: "id" }
  },

  mapColDetail: {
    header: "mapColDetail",
    inputs: { id: "id" }
  },

  mapColCreate: {
    header: "mapColCreate",
    inputs: { pid: "pid", params: "params" }
  },

  mapColUpdate: {
    header: "mapColUpdate",
    inputs: { pid: "pid", id: "id", params: "params" }
  },

  mapColRemove: {
    header: "mapColRemove",
    inputs: { pid: "pid", id: "id" }
  },

  //消息模块详情
  msgColDetail: {
    header: "msgColDetail",
    inputs: { id: "id" }
  },

  //接口配置-筛选列详情
  filterColDetail: {
    header: "filterColDetail",
    inputs: { id: "id" }
  },

  //接口配置-筛选列创建
  filterColCreate: {
    header: "filterColCreate",
    inputs: { pid: "pid", params: "params" }
  },

  //接口配置-筛选列更新
  filterColUpdate: {
    header: "filterColUpdate",
    inputs: { pid: "pid", id: "id", params: "params" }
  },

  //接口配置-筛选列删除
  filterColRemove: {
    header: "filterColRemove",
    inputs: { pid: "pid", id: "id" }
  },

  //接口配置-消息列创建
  msgColCreate: {
    header: "msgColCreate",
    inputs: { pid: "pid", params: "params" }
  },

  //接口配置-消息列更新
  msgColUpdate: {
    header: "msgColUpdate",
    inputs: { pid: "pid", id: "id", params: "params" }
  },

  //接口配置-消息列删除
  msgColRemove: {
    header: "msgColRemove",
    inputs: { pid: "pid", id: "id" }
  },

  allMsgColsRemove: {
    header: "allMsgColsRemove",
    inputs: { id: "id" }
  },

  message: {
    header: "message",
    inputs: { id: "id" }
  },

  //接口配置-消息更新
  messageUpdate: {
    header: "messageUpdate",
    inputs: { id: "id", msg: "msg" }
  },

  msgKeyWords: {
    header: "msgKeyWords",
    inputs: { id: "id" }
  },

  interfaceTypes: {
    header: "interfaceTypes",
    inputs: {}
  },

  //接口-导入数据
  importData: {
    header: "importData",
    inputs: {
      id: "id",
      port: "port",
      workflowStep: "workflowStep",
      date: "date"
    }
  },

  //接口-调试导入数据
  importDataDebug: {
    header: "importData",
    inputs: {
      id: "id",
      port: "port",
      workflowStep: "workflowStep",
      date: "date"
    }
  },

  //接口-上传并导入数据
  uploadImportData: {
    header: "uploadImportData",
    inputs: {
      id: "id",
      port: "port",
      workflowStep: "workflowStep",
      date: "date"
    }
  },

  //接口-调试上传并导入数据
  uploadImportDataDebug: {
    header: "uploadImportDataDebug",
    inputs: {
      id: "id",
      port: "port",
      workflowStep: "workflowStep",
      date: "date"
    }
  },

  deliverTypes: {
    header: "deliverTypes",
    inputs: {}
  },

  autoDateFormats: {
    header: "autoDateFormats",
    inputs: {}
  },

  fileFormats: {
    header: "fileFormats",
    inputs: {}
  },

  dbFormats: {
    header: "dbFormats",
    inputs: {}
  },

  fileEncodes: {
    header: "fileEncodes",
    inputs: {}
  },

  validators: {
    header: "validators",
    inputs: {}
  },

  functions: {
    header: "functions",
    inputs: {}
  },

  paramFunctions: {
    header: "paramFunctions",
    inputs: {}
  },

  interfaceCols: {
    header: "interfaceCols",
    inputs: { id: "id" }
  },

  interfaceParamColDetail: {
    header: "interfaceParamColDetail",
    inputs: { id: "id" }
  },

  messageTypes: {
    header: "messageTypes",
    inputs: {}
  },

  filterOperators: {
    header: "filterOperators",
    inputs: {}
  },

  logicOperators: {
    header: "logicOperators",
    inputs: {}
  },

  functionMap: {
    header: "functionMap",
    inputs: {}
  },

  interfaceParamFunctionMap: {
    header: "interfaceParamFunctionMap",
    inputs: {}
  },

  adapterTypes: {
    header: "adapterTypes",
    inputs: {}
  },

  dbTest: {
    header: "dbTest",
    inputs: {
      dbURL: "dbURL",
      dbFormat: "dbFormat",
      dbUser: "dbUser",
      dbPwd: "dbPwd"
    }
  },

  //接口配置-参数详情
  paramColDetail: {
    header: "paramColDetail",
    inputs: { id: "id" }
  },

  //接口配置-参数创建
  interfaceParamColCreate: {
    header: "interfaceParamColCreate",
    inputs: { pid: "pid", params: ["name", "func", "parameter"], json: "json" }
  },
  //接口-删除接口
  deleteInterfaceSource: {
    header: "deleteInterfaceSource",
    inputs: { id: "id" }
  },

  //四个对目标库读写并设置关键键值的函数
  getTargetMergeUpdateColumns: {
    header: "getTargetMergeUpdateColumns",
    inputs: { id: "id" }
  },

  getTargetMergeDeleteColumns: {
    header: "getTargetMergeDeleteColumns",
    inputs: { id: "id" }
  },

  setTargetMergeUpdateColumns: {
    header: "setTargetMergeUpdateColumns",
    inputs: { id: "id", tablejson: "tableupdatejson" }
  },

  setTargetMergeDeleteColumns: {
    header: "setTargetMergeDeleteColumns",
    inputs: { id: "id", tablejson: "tabledeletejson" }
  },

  //任务运行状态
  jobLatestStatus: {
    header: "jobLatestStatus",
    inputs: {}
  },
  //接口配置-参数更新
  paramColUpdate: {
    header: "paramColUpdate",
    inputs: { pid: "pid", id: "id", params: "params" }
  },

  //接口配置-参数删除
  paramColRemove: {
    header: "paramColRemove",
    inputs: { pid: "pid", id: "id" }
  },

  //适配器-数据库-创建
  dataSourceCreate: {
    header: "dataSourceCreate",
    inputs: { json: "json" }
  },
  //适配器-数据库-更新
  dataSourceUpdate: {
    header: "dataSourceUpdate",
    inputs: { json: "json" }
  },
  //适配器-数据库-删除
  dataSourceRemove: {
    header: "dataSourceRemove",
    inputs: { id: "id" }
  },
  //适配器-数据库-详情
  dataSourceDetail: {
    header: "dataSourceDetail",
    inputs: { id: "id" }
  },
  
  //数据库类型列表
  dbType:{
	  header:"dbType",
	  input:{}
  },
  
  //数据库连接状态测试
  dbConnTest: {
	    header: "dbConnTest",
	    inputs: {
	      dbURL: "dbURL",
	      dbFormat: "dbFormat",
	      userName: "userName",
	      userPassword: "userPassword"
	    }
	  },

  //适配器-数据库-数据库清单
  dataSourceOptDBList: {
    header: "dataSourceOptDBList",
    inputs: {}
  },
  //适配器-数据库-查询
  dataSourceList: {
    header: "dataSourceList",
    inputs: { connName: "dbConnName", connType: "dbConnType" }
  },
  //适配集-创建-SQL查询
  executeSql: {
	    header: "executeSql",
	    inputs: {
	    	dataSourceID: "dataSourceId",
	    	basicDataSql: "basicDataSql"
	    }
  },
  //适配器-基础数据集-创建
  basicDataSetCreate: {
    header: "basicDataSetCreate",
    inputs: {
      SQLstatement: "SQLstatement",
      dataSourceId: "dataSourceId",
      dsName: "dsName",
      json: "json" //后期应当去掉
    }
  },
  //适配器-基础数据集-查询
  basicDataSetList: {
    header: "basicDataSetList",
    inputs: { dsName: "dsName" }
  },

  //适配器-基础数据集-详情
  basicDataSetDetail: {
    header: "basicDataSetDetail",
    inputs: { id: "id" }
  },
  //适配器-基础数据集-更新
  basicDataSetUpdate: {
    header: "basicDataSetUpdate",
    inputs: { json: "json" }
  },

  //适配器-基础数据集-删除
  basicDataSetRemove: {
    header: "basicDataSetRemove",
    inputs: { id: "id" }
  },
  
  //基础列计算方式列表
  basicParamFunctionMap: {
    header: "basicParamFunctionMap",
    inputs: {}
  },
  
  //适配器-关联数据集-增加一行基础列
  basicDataSetAddRow: {
    header: "basicDataSetAddRow",
    inputs: { id: "id",basicColName:"name",basicFunctionID:"func",basicFuncStr:"parameter"}
  },
  

//适配器-关联数据集-创建
  unionDataSetCreate: {
    header: "unionDataSetCreate",
    inputs: { json: "json" }
  },
  
  //适配器-关联数据集-基数数据集下拉
  dataSetBasicOptList: {
	    header: "dataSetBasicOptList",
	    inputs: {  }
	  },

  //适配器-关联数据集-详情
  unionDataSetDetail: {
    header: "unionDataSetDetail",
    inputs: { id: "id" }
  },

  //适配器-关联数据集-更新
  unionDataSetUpdate: {
    header: "unionDataSetUpdate",
    inputs: { json: "json" }
  },

  //适配器-关联数据集-删除
  unionDataSetRemove: {
    header: "unionDataSetRemove",
    inputs: { id: "id" }
  },
  

  //适配器-关联数据集-增加一行增强列
  unionDataSetAddRow: {
    header: "unionDataSetAddRow",
    inputs: { id: "id",exColName:"exColName",exColFunctionID:"exColFunctionID",exColFuncStr:"exColFuncStr"}
  },
  unionDataSetRemoveRow:{
	header:"unionDataSetRemoveRow",
	input:{id: "id",exColID:"exColID"}
  },
  unionDataSetUpdateRow: {
	    header: "unionDataSetUpdateRow",
	    inputs: { id: "id",exColID:"exColID",exColName:"exColName",exColFunctionID:"exColFunctionID",exColFuncStr:"exColFuncStr"}
	  },

  unionDataSetSaveColList:{
	header:"unionDataSetSaveColList",
	inputs:{id:"id",exColListTable:"exColListTable"}
  },
//适配器-关联数据集-查询基础列
  dataSetBasicColsOptList: {
    header: "dataSetBasicColsOptList",
    inputs: { dsBasicID: "id"}
  },
  
//适配器-关联数据集-查询增强增强列
  dataSetRelativeColsOptList: {
    header: "dataSetRelativeColsOptList",
    inputs: { dsRelativeID: "id"}
  },
  
  //适配器-关联数据集-修改-关联列列表
  unionDataSetExColsList:{
	  header:"unionDataSetExColsList",
	  inputs:{unionDSid:"unionDSid"}
  },
  
  //适配器-关联数据集-增加列=函数功能列表
  unionDataSetColFunctionOptList:{
	  header:"unionDataSetColFunctionOptList",
	  input:{}
  },
  
  //适配器-关联数据集-查询
  unionDataSetList: {
    header: "unionDataSetList",
    inputs: { dsName: "name" }
  },

  //适配器-数据适配器-创建
  adapterCreate: {
    header: "adapterCreate",
    inputs: { json: "json" }
  },

  //适配器-数据适配器-详情
  adapterDetail: {
    header: "adapterDetail",
    inputs: { id: "id" }
  },

  //适配器-数据适配器-更新
  adapterUpdate: {
    header: "adapterUpdate",
    inputs: { json: "json" }
  },

  //适配器-数据适配器-删除
  adapterRemove: {
    header: "adapterRemove",
    inputs: { id: "id" }
  },

  //适配器-数据适配器-查询
  adapterList: {
    header: "adapterList",
    inputs: { dsName: "dsName" }
  },
  
  dataSetAllOptList:{
	  header:"dataSetAllOptList",
	  inputs:{}
  }
  ,
  genMappingBySQL:{
	  header:"genMappingBySQL",
	  inputs:{msgSQL:"msgSQL"}
  }
  ,
  daOneMappingSave:{
	  header:"daOneMappingSave",
	  input:{sourceCol:"sourceCol",targetCol:"targetCol"}
  },
  
  dataSetAllColOptList:{
	  header:"dataSetAllColOptList",
	  input:{dsID:"dsID"}
  }
};

function getParam(cb_func) {
  var str = paramCopy(funclist[cb_func]);
  return str;
}
