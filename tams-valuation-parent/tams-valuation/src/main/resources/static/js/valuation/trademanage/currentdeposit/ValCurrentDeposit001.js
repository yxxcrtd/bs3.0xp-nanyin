$(function () {
	var table=$("#dataTable").joyintable({
	    data:[
		  	{"zjzh":"账户1000000000000000000001","zjmc":"资金账户名称","lilv":"5.9%","sxr":"2018/04/15","sjzt":"待审核","jigou":"机构名称1","bumen":"部门名称1","cjr":"李德富","cjsj":"2018/04/15","gxsj":"2018/04/16","cjr":"李德富"},
			{"zjzh":"账户1000000000000000000002","zjmc":"资金账户名称","lilv":"5.9%","sxr":"2018/04/15","sjzt":"待审核","jigou":"机构名称1","bumen":"部门名称1","cjr":"李德富","cjsj":"2018/04/15","gxsj":"2018/04/16","cjr":"李德富"},
			{"zjzh":"账户1000000000000000000003","zjmc":"资金账户名称","lilv":"5.9%","sxr":"2018/04/15","sjzt":"待审核","jigou":"机构名称1","bumen":"部门名称1","cjr":"李德富","cjsj":"2018/04/15","gxsj":"2018/04/16","cjr":"李德富"},
			{"zjzh":"账户1000000000000000000004","zjmc":"资金账户名称","lilv":"5.9%","sxr":"2018/04/15","sjzt":"待审核","jigou":"机构名称1","bumen":"部门名称1","cjr":"李德富","cjsj":"2018/04/15","gxsj":"2018/04/16","cjr":"李德富"},
			{"zjzh":"账户1000000000000000000005","zjmc":"资金账户名称","lilv":"5.9%","sxr":"2018/04/15","sjzt":"待审核","jigou":"机构名称1","bumen":"部门名称1","cjr":"李德富","cjsj":"2018/04/15","gxsj":"2018/04/16","cjr":"李德富"},
			{"zjzh":"账户1000000000000000000006","zjmc":"资金账户名称","lilv":"5.9%","sxr":"2018/04/15","sjzt":"待审核","jigou":"机构名称1","bumen":"部门名称1","cjr":"李德富","cjsj":"2018/04/15","gxsj":"2018/04/16","cjr":"李德富"},
			{"zjzh":"账户1000000000000000000007","zjmc":"资金账户名称","lilv":"5.9%","sxr":"2018/04/15","sjzt":"待审核","jigou":"机构名称1","bumen":"部门名称1","cjr":"李德富","cjsj":"2018/04/15","gxsj":"2018/04/16","cjr":"李德富"},
			{"zjzh":"账户1000000000000000000008","zjmc":"资金账户名称","lilv":"5.9%","sxr":"2018/04/15","sjzt":"待审核","jigou":"机构名称1","bumen":"部门名称1","cjr":"李德富","cjsj":"2018/04/15","gxsj":"2018/04/16","cjr":"李德富"},
			{"zjzh":"账户1000000000000000000009","zjmc":"资金账户名称","lilv":"5.9%","sxr":"2018/04/15","sjzt":"待审核","jigou":"机构名称1","bumen":"部门名称1","cjr":"李德富","cjsj":"2018/04/15","gxsj":"2018/04/16","cjr":"李德富"},
			{"zjzh":"账户1000000000000000000010","zjmc":"资金账户名称","lilv":"5.9%","sxr":"2018/04/15","sjzt":"待审核","jigou":"机构名称1","bumen":"部门名称1","cjr":"李德富","cjsj":"2018/04/15","gxsj":"2018/04/16","cjr":"李德富"}	
		 ],
		//height:781,
		searchOnInit: true,
		cols: [
			  {field:'zjzh',title: '资金账户账号',width:240,sort: true},
			  {field:'zjmc',title: '资金账户名称',width:120,sort: true},
		 	  {field:'lilv', title: '利率',width:90,sort: true},
		 	  {field:'sxr',title: '生效日',width:150,sort: true},
			  {field:'sjzt',title: '数据状态',width:90,sort: true},
			  {field:'jigou',title: '机构',width:200,sort: true},
			  {field:'bumen',title: '部门',width:200,sort: true},
			  {field:'cjr',title: '创建人',width:90,sort: true},
			  {field:'cjsj',title: '创建时间',width:150,sort: true},
			  {field:'gxsj',title: '更新时间',width:150,sort: true},
			  {field:'gxr',title: '更新人',width:90,sort: true},
		 	  //{fixed:'right',width:'90', title: '操作', align:'center', type:'action', buttons: ['edit','delt']}
		 	]
	  });
  
  //新建
  $('#ValCurrentDeposit001_btn_add').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建活期存款收息"),
      basePath + "valuation/trademanage/currentdeposit/ValCurrentDeposit002/init",
      "1000px", "360px", {pageModel:PAGE_MODEL_NEW});
  });
  
  

});