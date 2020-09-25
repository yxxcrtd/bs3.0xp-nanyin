// todo 数据接口，处理与底层相关的数据逻辑

var funclist = {
  system_users_login: {
    header: "sampledata.json",
    inputs: {
      username: "username",
      password: "password",
      broker: "broker",
      user: "system_user"
    }
  },
  system_group_update: {
    header: "sampledata2.json",
    inputs: { username: "username", group: "group" }
  },
  get_broker_byBank: {
    header: "samplebroker.json",
    inputs: {}
  },
  system_bank_name: {
    header: "countryList",
    inputs: {}
  },
  clientList: {
    header: "clientList",
    inputs: {}
  },
  countryList: {
    header: "countryList",
    inputs: {}
  },
  stateList: {
    header: "stateList",
    inputs: {}
  },
  currencyList: {
    header: "currencyList",
    inputs: { all: false }
  },
  lotSelectList: {
    header: "lotSelectList",
    inputs: {}
  },
  holidayList: {
    header: "holidayList",
    inputs: {}
  },
  portTypes: {
    header: "portTypes",
    inputs: {}
  },
  coaList: {
    header: "coaList",
    inputs: {}
  },
  glListInCOA: {
    header: "glListInCOA",
    inputs: { coaID: "coa" }
  },
  weekendAccrualList: {
    header: "weekendAccrualList",
    inputs: {}
  },
  weekendValuationList: {
    header: "weekendValuationList",
    inputs: {}
  },
  acctDateOption: {
    header: "acctDateOption",
    inputs: {}
  },
  portSingleList: {
    header: "portSingleList",
    inputs: { client: "client" }
  },
  managerGroupList: {
    header: "managerGroupList",
    inputs: {}
  },
  portCreate: {
    header: "portCreate",
    inputs: {"json": "json", "params": ["client", "name", "code", "country", "state", "currency", "lot", "holiday", "date", "type", "coa", "glgroup", "nav", "weekendAccrual", "weekendVal", "acctDate", "mother", "manager", "navto1", "income2share"]}
  },
  portList: {
	header: "portList",
	inputs: {"client":"client", "name":"name", "type":"type"}
  },
  glListInCOA:{
  	header:"glListInCOA",
  	inputs:{"coaID":"coa"}
  },
  portShortList:{
	  	header:"portShortList",
	  	inputs:{"client":"client"}
  },
  //列出所有接口配置
  interfaceList: {
    header: "interfaceList",
    inputs: { name: "name", category: "category", type: "type" }
  },
  //列出接口类型
  interfaceTypes: {
    header: "interfaceTypes",
    inputs: {}
  },
  //创建接口配置
  interfaceCreate: {
    header: "interfaceCreate",
    inputs: { params: "ALL_PARAMS_LIST", json: "ALL_PARAMS_MESSAGE" }
  },
  //更新接口配置
  interfaceUpdate: {
    header: "interfaceUpdate",
    inputs: { id: "id", params: "ALL_PARAMS_LIST", json: "ALL_PARAMS_MESSAGE" }
  },
  //查询接口配置详情
  interfaceDetail: {
    header: "interfaceDetail",
    inputs: { id: "id" }
  },
  //列出某一接口下全部基础列信息
  interfaceBasicColDetail: {
    header: "interfaceBasicColDetail",
    inputs: { id: "id" }
  },
  //创建某一接口下基础列信息
  interfaceBasicColCreate: {
    header: "interfaceBasicColCreate",
    inputs: {
      pid: "pid",
      params: "ALL_PARAMS_LIST",
      json: "ALL_PARAMS_MESSAGE"
    }
  },
  //更新某一接口下基础列信息
  interfaceBasicColUpdate: {
    header: "interfaceBasicColUpdate",
    inputs: {
      id: "id",
      pid: "pid",
      params: "ALL_PARAMS_LIST",
      json: "ALL_PARAMS_MESSAGE"
    }
  },
  //删除某一接口下基础列信息
  interfaceBasicColRemove: {
    header: "interfaceBasicColRemove",
    inputs: { id: "id", pid: "pid" }
  },
  //列出某一接口下全部增强列信息
  interfaceAdvanceColDetail: {
    header: "interfaceAdvanceColDetail",
    inputs: { id: "id" }
  },
  //创建某一接口下增强列信息
  interfaceAdvanceColCreate: {
    header: "interfaceAdvanceColCreate",
    inputs: {
      pid: "pid",
      params: "ALL_PARAMS_LIST",
      json: "ALL_PARAMS_MESSAGE"
    }
  },
  //更新某一接口下增强列信息
  interfaceAdvanceColUpdate: {
    header: "interfaceAdvanceColUpdate",
    inputs: {
      id: "id",
      pid: "pid",
      params: "ALL_PARAMS_LIST",
      json: "ALL_PARAMS_MESSAGE"
    }
  },
  //删除某一接口下增强列信息
  interfaceAdvanceColRemove: {
    header: "interfaceAdvanceColRemove",
    inputs: { id: "id", pid: "pid" }
  },
  //列出某一接口下全部筛选列信息
  interfaceFilterColDetail: {
    header: "interfaceFilterColDetail",
    inputs: { id: "id" }
  },
  //创建某一接口下筛选列信息
  interfaceFilterColCreate: {
    header: "interfaceFilterColCreate",
    inputs: {
      pid: "pid",
      params: "ALL_PARAMS_LIST",
      json: "ALL_PARAMS_MESSAGE"
    }
  },
  //更新某一接口下筛选列信息
  interfaceFilterColUpdate: {
    header: "interfaceFilterColUpdate",
    inputs: {
      id: "id",
      pid: "pid",
      params: "ALL_PARAMS_LIST",
      json: "ALL_PARAMS_MESSAGE"
    }
  },
  //删除某一接口下筛选列信息
  interfaceFilterColRemove: {
    header: "interfaceFilterColRemove",
    inputs: { id: "id", pid: "pid" }
  },
  //列出某一接口下全部消息列信息
  interfaceMsgColDetail: {
    header: "interfaceMsgColDetail",
    inputs: { id: "id" }
  },
  //创建某一接口下消息列信息
  interfaceMsgColCreate: {
    header: "interfaceMsgColCreate",
    inputs: {
      pid: "pid",
      params: "ALL_PARAMS_LIST",
      json: "ALL_PARAMS_MESSAGE"
    }
  },
  //更新某一接口下消息列信息
  interfaceMsgColUpdate: {
    header: "interfaceMsgColUpdate",
    inputs: {
      id: "id",
      pid: "pid",
      params: "ALL_PARAMS_LIST",
      json: "ALL_PARAMS_MESSAGE"
    }
  },
  //删除某一接口下消息列信息
  interfaceMsgColRemove: {
    header: "interfaceMsgColRemove",
    inputs: { id: "id", pid: "pid" }
  },
  //删除某一接口下所有消息列信息
  interfaceAllMsgColsRemove: {
    header: "interfaceAllMsgColsRemove",
    inputs: { id: "id" }
  },
  //返回系统支持的所有接口数据源类型
  interfaceDeliverTypes: {
    header: "interfaceDeliverTypes",
    inputs: {}
  },
  //返回全部自动日期格式类型
  interfaceAutoDateFormats: {
    header: "interfaceAutoDateFormats",
    inputs: {}
  },
  //返回接口支持的全部文件类型
  interfaceFileFormats: {
    header: "interfaceFileFormats",
    inputs: {}
  },
  //返回接口支持的全部文件编码类型
  interfaceFileEncodes: {
    header: "interfaceFileEncodes",
    inputs: {}
  },
  //返回接口支持的所有校验函数
  interfaceValidators: {
    header: "interfaceValidators",
    inputs: {}
  },
  //返回接口支持的所有数据操作类型
  interfaceFunctions: {
    header: "interfaceFunctions",
    inputs: {}
  },
  //返回接口支持的所有数据操作的详细信息
  interfaceFunctionMap: {
    header: "interfaceFunctionMap",
    inputs: {}
  },
  //返回某一接口的全部列信息
  interfaceCols: {
    header: "interfaceCols",
    inputs: { id: "id" }
  },
  //返回接口支持的全部消息类型
  interfaceMessageTypes: {
    header: "interfaceMessageTypes",
    inputs: {}
  },
  //返回某一接口拼接的消息
  interfaceMessage: {
    header: "interfaceMessage",
    inputs: { id: "id" }
  },
  //更新某一接口拼接的消息
  interfaceMessageUpdate: {
    header: "interfaceMessageUpdate",
    inputs: { id: "id" }
  },
  //更新某一接口全部的消息关键字
  interfaceMsgKeyWords: {
    header: "interfaceMsgKeyWords",
    inputs: { id: "id" }
  },
  //返回接口支持的所有过滤运算符
  interfaceFilterOperators: {
    header: "interfaceFilterOperators",
    inputs: {}
  },
  //返回接口支持的所有逻辑运算符
  interfaceLogicOperators: {
    header: "interfaceLogicOperators",
    inputs: {}
  }
};
/*
$.get(prefix + "/static/js/jixu/framework/dt_inf_jixu_funclist.json")
  .done(function(fl) {
    funclist = fl;
  })
  .fail(function() {
    console.log("无法打开接口函数列表！");
  });

  */
function getParam(cb_func) {
  var str = paramCopy(funclist[cb_func]);
  return str;
  //return funclist[cb_func];
  
}

function getServerData(msg) {
  var returndata = {};
  var requrl = "../mock/sampledata.json";
  $r = $.get(requrl)
    .done(function(j) {
      returndata = j;
    })
    .fail("错误：无法访问服务" + requrl);

  return returndata;
  if (msg.header === "system_users_login") {
    message = "userlogin.json";
  } else if (cb_val == "system_users_password") {
    returndata = "password";
  } else if (cb_val == "system_cookie_user") {
    returndata = "cookieuser";
  }
}
