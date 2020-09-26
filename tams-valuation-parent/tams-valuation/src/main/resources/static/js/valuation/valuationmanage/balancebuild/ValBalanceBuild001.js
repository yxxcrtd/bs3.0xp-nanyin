$(function () {
  var table=$("#dataTable").joyintable({
	data:[
	  {"sczt":"<span class='unaffirm'>未生成</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25",},
	  {"sczt":"<span class='unaffirm'>未生成</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06",},
	  {"sczt":"<span class='unaffirm'>未生成</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25",},
	  {"sczt":"<span class='unaffirm'>未生成</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06",},
	  {"sczt":"<span class='unaffirm'>未生成</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25",},
	  {"sczt":"<span class='unaffirm'>未生成</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06",},
	  {"sczt":"<span class='wrong'>已生成(有错凭证)</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25",},
	  {"sczt":"<span class='wrong'>已生成(有错凭证)</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06",},
	  {"sczt":"<span class='affirm'>已生成(无错凭证)</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25",},
	  {"sczt":"<span class='affirm'>已生成(无错凭证)</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06",},
	  {"sczt":"<span class='affirm'>已生成(无错凭证)</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25",},
	  {"sczt":"<span class='affirm'>已生成(无错凭证)</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06",},
	  {"sczt":"<span class='affirm'>已生成(无错凭证)</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25",},
	  {"sczt":"<span class='affirm'>已生成(无错凭证)</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06",},
	  {"sczt":"<span class='affirm'>已生成(无错凭证)</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25",},
	  {"sczt":"<span class='affirm'>已生成(无错凭证)</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06",},
	  {"sczt":"<span class='affirm'>已生成(无错凭证)</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25",},
	  {"sczt":"<span class='affirm'>已生成(无错凭证)</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06",},
	  {"sczt":"<span class='affirm'>已生成(无错凭证)</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25",},
	  {"sczt":"<span class='affirm'>已生成(无错凭证)</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06",},
	  {"sczt":"<span class='affirm'>已生成(无错凭证)</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25",},
	  {"sczt":"<span class='affirm'>已生成(无错凭证)</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06",},
	],
	searchOnInit: true,
	cellMinWidth:100 ,
	cols:[
	      {field:'sczt', title:'生成状态', width:140, fixed:true, sort:true},
		  {field:'cpdm', title:'产品代码', width:150, fixed:true, sort:true},
		  {field:'cpjc', title:'产品简称', minWidth:240, sort:true},
		  {field:'cplx', title:'产品类型', width:90, align:'center', sort:true},
		  {field:'gzrq', title:'估值日期', width:120, sort:true},
		  //{fixed:'right', width:'90', title:'操作', align:'center', type:'action', buttons: ['edit','delt']}
		]
  });
  
  //新建
  $('#ValBalanceBuild001_btn_add').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("ValBalanceBuild002"),
      basePath + "valuation/valuationmanage/balancebuild/ValBalanceBuild002/init",
      "1000px", "360px", {pageModel:PAGE_MODEL_NEW});
  });

});