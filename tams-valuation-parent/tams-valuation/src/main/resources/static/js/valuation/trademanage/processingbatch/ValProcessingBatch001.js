$(function () {
	var table=$("#dataTable").joyintable({
	    data:[
		  	{"pclmc":"补账批处理","beizhu":"系统管理员创建于2018-03-19 10:15:07启动批处理时创建。","zhuangtai":"有效"},
			{"pclmc":"交易日终","beizhu":"系统管理员创建于2018-03-19 10:15:07启动批处理时创建。","zhuangtai":"有效"},
			{"pclmc":"存款类资产拆借","beizhu":"系统管理员创建于2018-03-19 10:15:07启动批处理时创建。","zhuangtai":"有效"},
			{"pclmc":"利息计提","beizhu":"系统管理员创建于2018-03-19 10:15:07启动批处理时创建。","zhuangtai":"有效"},
			{"pclmc":"费用计提","beizhu":"系统管理员创建于2018-03-19 10:15:07启动批处理时创建。","zhuangtai":"有效"},
			{"pclmc":"活期存款计息","beizhu":"系统管理员创建于2018-03-19 10:15:07启动批处理时创建。","zhuangtai":"有效"},
			{"pclmc":"费用支付","beizhu":"系统管理员创建于2018-03-19 10:15:07启动批处理时创建。","zhuangtai":"有效"},
			{"pclmc":"通道费支付","beizhu":"系统管理员创建于2018-03-19 10:15:07启动批处理时创建。","zhuangtai":"有效"},
				
		 ],
		//height:781,
		searchOnInit: true,
		cols: [
			  {field:'pclmc',title: '批处理名称',sort: true},
			  {field:'beizhu',title: '备注',sort: true},
		 	  {field:'zhuangtai', title: '状态',width:150,sort: true},
		 	  {fixed:'right',width:100, title: '操作', align:'center', type:'action', buttons: ['edit','delt']}
		 	]
	  });
  
  //新建
  $('#ValProcessingBatch001_btn_add').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建活期存款收息"),
      basePath + "valuation/trademanage/processingbatch/ValProcessingBatch002/init",
      "1000px", "360px", {pageModel:PAGE_MODEL_NEW});
  });
  
  

});