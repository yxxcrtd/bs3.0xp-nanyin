$(function () {
  var table=$("#dataTable").joyintable({
	data:[
	  {"sczt":"<span class='unaffirm'>未生成</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25","lcfe":"200,004,729.03","cpzjz":"200,032,857.06","shouyilv":"0.383791","jzzs":"<a class='link-blue ValBuild001_jzzs' href='javascript:void(0);'>查看趋势图</a>","gzbmx":"<a class='link-blue ValBuild001_gzbxq' href='javascript:void(0);'>详情</a>",},
	  {"sczt":"<span class='unaffirm'>未生成</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06","lcfe":"800,000,000.00","cpzjz":"800,000,000.00","shouyilv":"0.071000","jzzs":"<a class='link-blue ValBuild001_jzzs' href='javascript:void(0);'>查看趋势图</a>","gzbmx":"<a class='link-blue ValBuild001_gzbxq' href='javascript:void(0);'>详情</a>",},
	  {"sczt":"<span class='unaffirm'>未生成</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25","lcfe":"200,004,729.03","cpzjz":"200,032,857.06","shouyilv":"0.383791","jzzs":"<a class='link-blue ValBuild001_jzzs' href='javascript:void(0);'>查看趋势图</a>","gzbmx":"<a class='link-blue ValBuild001_gzbxq' href='javascript:void(0);'>详情</a>",},
	  {"sczt":"<span class='unaffirm'>未生成</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06","lcfe":"800,000,000.00","cpzjz":"800,000,000.00	","shouyilv":"0.071000","jzzs":"<a class='link-blue ValBuild001_jzzs' href='javascript:void(0);'>查看趋势图</a>","gzbmx":"<a class='link-blue ValBuild001_gzbxq' href='javascript:void(0);'>详情</a>",},
	  {"sczt":"<span class='unaffirm'>未生成</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25","lcfe":"200,004,729.03","cpzjz":"200,032,857.06","shouyilv":"0.383791","jzzs":"<a class='link-blue ValBuild001_jzzs' href='javascript:void(0);'>查看趋势图</a>","gzbmx":"<a class='link-blue ValBuild001_gzbxq' href='javascript:void(0);'>详情</a>",},
	  {"sczt":"<span class='unaffirm'>未生成</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06","lcfe":"800,000,000.00","cpzjz":"800,000,000.00	","shouyilv":"0.071000","jzzs":"<a class='link-blue ValBuild001_jzzs' href='javascript:void(0);'>查看趋势图</a>","gzbmx":"<a class='link-blue ValBuild001_gzbxq' href='javascript:void(0);'>详情</a>",},
	  {"sczt":"<span class='wrong'>已生成(有错凭证)</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25","lcfe":"200,004,729.03","cpzjz":"200,032,857.06","shouyilv":"0.383791","jzzs":"<a class='link-blue ValBuild001_jzzs' href='javascript:void(0);'>查看趋势图</a>","gzbmx":"<a class='link-blue ValBuild001_gzbxq' href='javascript:void(0);'>详情</a>",},
	  {"sczt":"<span class='wrong'>已生成(有错凭证)</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06","lcfe":"800,000,000.00","cpzjz":"800,000,000.00","shouyilv":"0.071000","jzzs":"<a class='link-blue ValBuild001_jzzs' href='javascript:void(0);'>查看趋势图</a>","gzbmx":"<a class='link-blue ValBuild001_gzbxq' href='javascript:void(0);'>详情</a>",},
	  {"sczt":"<span class='affirm'>已生成(无错凭证)</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25","lcfe":"200,004,729.03","cpzjz":"200,032,857.06","shouyilv":"0.383791","jzzs":"<a class='link-blue ValBuild001_jzzs' href='javascript:void(0);'>查看趋势图</a>","gzbmx":"<a class='link-blue ValBuild001_gzbxq' href='javascript:void(0);'>详情</a>",},
	  {"sczt":"<span class='affirm'>已生成(无错凭证)</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06","lcfe":"800,000,000.00","cpzjz":"800,000,000.00","shouyilv":"0.071000","jzzs":"<a class='link-blue ValBuild001_jzzs' href='javascript:void(0);'>查看趋势图</a>","gzbmx":"<a class='link-blue ValBuild001_gzbxq' href='javascript:void(0);'>详情</a>",},
	  {"sczt":"<span class='affirm'>已生成(无错凭证)</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25","lcfe":"200,004,729.03","cpzjz":"200,032,857.06","shouyilv":"0.383791","jzzs":"<a class='link-blue ValBuild001_jzzs' href='javascript:void(0);'>查看趋势图</a>","gzbmx":"<a class='link-blue ValBuild001_gzbxq' href='javascript:void(0);'>详情</a>",},
	  {"sczt":"<span class='affirm'>已生成(无错凭证)</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06","lcfe":"800,000,000.00","cpzjz":"800,000,000.00","shouyilv":"0.071000","jzzs":"<a class='link-blue ValBuild001_jzzs' href='javascript:void(0);'>查看趋势图</a>","gzbmx":"<a class='link-blue ValBuild001_gzbxq' href='javascript:void(0);'>详情</a>",},
	  {"sczt":"<span class='affirm'>已生成(无错凭证)</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25","lcfe":"200,004,729.03","cpzjz":"200,032,857.06","shouyilv":"0.383791","jzzs":"<a class='link-blue ValBuild001_jzzs' href='javascript:void(0);'>查看趋势图</a>","gzbmx":"<a class='link-blue ValBuild001_gzbxq' href='javascript:void(0);'>详情</a>",},
	  {"sczt":"<span class='affirm'>已生成(无错凭证)</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06","lcfe":"800,000,000.00","cpzjz":"800,000,000.00","shouyilv":"0.071000","jzzs":"<a class='link-blue ValBuild001_jzzs' href='javascript:void(0);'>查看趋势图</a>","gzbmx":"<a class='link-blue ValBuild001_gzbxq' href='javascript:void(0);'>详情</a>",},
	  {"sczt":"<span class='affirm'>已生成(无错凭证)</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25","lcfe":"200,004,729.03","cpzjz":"200,032,857.06","shouyilv":"0.383791","jzzs":"<a class='link-blue ValBuild001_jzzs' href='javascript:void(0);'>查看趋势图</a>","gzbmx":"<a class='link-blue ValBuild001_gzbxq' href='javascript:void(0);'>详情</a>",},
	  {"sczt":"<span class='affirm'>已生成(无错凭证)</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06","lcfe":"800,000,000.00","cpzjz":"800,000,000.00","shouyilv":"0.071000","jzzs":"<a class='link-blue ValBuild001_jzzs' href='javascript:void(0);'>查看趋势图</a>","gzbmx":"<a class='link-blue ValBuild001_gzbxq' href='javascript:void(0);'>详情</a>",},
	  {"sczt":"<span class='affirm'>已生成(无错凭证)</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25","lcfe":"200,004,729.03","cpzjz":"200,032,857.06","shouyilv":"0.383791","jzzs":"<a class='link-blue ValBuild001_jzzs' href='javascript:void(0);'>查看趋势图</a>","gzbmx":"<a class='link-blue ValBuild001_gzbxq' href='javascript:void(0);'>详情</a>",},
	  {"sczt":"<span class='affirm'>已生成(无错凭证)</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06","lcfe":"800,000,000.00","cpzjz":"800,000,000.00","shouyilv":"0.071000","jzzs":"<a class='link-blue ValBuild001_jzzs' href='javascript:void(0);'>查看趋势图</a>","gzbmx":"<a class='link-blue ValBuild001_gzbxq' href='javascript:void(0);'>详情</a>",},
	  {"sczt":"<span class='affirm'>已生成(无错凭证)</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25","lcfe":"200,004,729.03","cpzjz":"200,032,857.06","shouyilv":"0.383791","jzzs":"<a class='link-blue ValBuild001_jzzs' href='javascript:void(0);'>查看趋势图</a>","gzbmx":"<a class='link-blue ValBuild001_gzbxq' href='javascript:void(0);'>详情</a>",},
	  {"sczt":"<span class='affirm'>已生成(无错凭证)</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06","lcfe":"800,000,000.00","cpzjz":"800,000,000.00","shouyilv":"0.071000","jzzs":"<a class='link-blue ValBuild001_jzzs' href='javascript:void(0);'>查看趋势图</a>","gzbmx":"<a class='link-blue ValBuild001_gzbxq' href='javascript:void(0);'>详情</a>",},
	  {"sczt":"<span class='affirm'>已生成(无错凭证)</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25","lcfe":"200,004,729.03","cpzjz":"200,032,857.06","shouyilv":"0.383791","jzzs":"<a class='link-blue ValBuild001_jzzs' href='javascript:void(0);'>查看趋势图</a>","gzbmx":"<a class='link-blue ValBuild001_gzbxq' href='javascript:void(0);'>详情</a>",},
	  {"sczt":"<span class='affirm'>已生成(无错凭证)</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06","lcfe":"800,000,000.00","cpzjz":"800,000,000.00","shouyilv":"0.071000","jzzs":"<a class='link-blue ValBuild001_jzzs' href='javascript:void(0);'>查看趋势图</a>","gzbmx":"<a class='link-blue ValBuild001_gzbxq' href='javascript:void(0);'>详情</a>",},
	],
	searchOnInit: true,
	cellMinWidth:100 ,
	cols:[
	      {field:'sczt', title:'生成状态', width:140, fixed:true, sort:true},
		  {field:'cpdm', title:'产品代码', width:150, sort:true},
		  {field:'cpjc', title:'产品简称', width:240, sort:true},
		  {field:'cplx', title:'产品类型', minWidth:90, align:'center', sort:true},
		  {field:'gzrq', title:'估值日期', minWidth:120, align:'center', sort:true},
		  {field:'lcfe', title:'理财份额', minWidth:200, align:'right', sort: true},
		  {field:'cpzjz', title:'产品总净值', minWidth:200, align:'right', sort: true},
		  {field:'shouyilv', title:'单位净值/万份收益率', minWidth:160, align:'right', sort:true},
		  {field:'jzzs', title:'净值走势', minWidth:100, sort:true},
		  //{field:'gzbmx', title:'明细', fixed:'right', width:90, sort:true},
		  //{fixed:'right', width:90, title:'操作', align:'center', type:'action', buttons: ['edit','delt']}
		]
  });
  
  //查看详情
  $('.ValBuild001_gzbxq').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("ValBuild002"),
      basePath + "valuation/valuationmanage/valbuild/ValBuild002/init",
      "96%", "90%", {pageModel:PAGE_MODEL_NEW});
  });
  $('.ValBuild001_jzzs').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("ValBuild003"),
      basePath + "valuation/valuationmanage/valbuild/ValBuild003/init",
      "1000px", "500px", {pageModel:PAGE_MODEL_NEW});
  });
  
  

});