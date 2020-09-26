$(function () {
  var table=$("#dataTable").joyintable({
	data:[
	  {"qrzt":"<span class='unaffirm'>未确认</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25","lcfe":"200,004,729.03","cpzjz":"200,032,857.06","shouyilv":"0.383791",},
	  {"qrzt":"<span class='unaffirm'>未确认</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06","lcfe":"800,000,000.00","cpzjz":"800,000,000.00	","shouyilv":"0.071000",},
	  {"qrzt":"<span class='unaffirm'>未确认</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25","lcfe":"200,004,729.03","cpzjz":"200,032,857.06","shouyilv":"0.383791",},
	  {"qrzt":"<span class='unaffirm'>未确认</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06","lcfe":"800,000,000.00","cpzjz":"800,000,000.00	","shouyilv":"0.071000",},
	  {"qrzt":"<span class='unaffirm'>未确认</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25","lcfe":"200,004,729.03","cpzjz":"200,032,857.06","shouyilv":"0.383791",},
	  {"qrzt":"<span class='unaffirm'>未确认</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06","lcfe":"800,000,000.00","cpzjz":"800,000,000.00	","shouyilv":"0.071000",},
	  {"qrzt":"<span class='unaffirm'>未确认</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25","lcfe":"200,004,729.03","cpzjz":"200,032,857.06","shouyilv":"0.383791",},
	  {"qrzt":"<span class='unaffirm'>未确认</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06","lcfe":"800,000,000.00","cpzjz":"800,000,000.00	","shouyilv":"0.071000",},
	  {"qrzt":"<span class='affirm'>已确认</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25","lcfe":"200,004,729.03","cpzjz":"200,032,857.06","shouyilv":"0.383791",},
	  {"qrzt":"<span class='affirm'>已确认</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06","lcfe":"800,000,000.00","cpzjz":"800,000,000.00	","shouyilv":"0.071000",},
	  {"qrzt":"<span class='affirm'>已确认</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25","lcfe":"200,004,729.03","cpzjz":"200,032,857.06","shouyilv":"0.383791",},
	  {"qrzt":"<span class='affirm'>已确认</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06","lcfe":"800,000,000.00","cpzjz":"800,000,000.00	","shouyilv":"0.071000",},
	  {"qrzt":"<span class='affirm'>已确认</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25","lcfe":"200,004,729.03","cpzjz":"200,032,857.06","shouyilv":"0.383791",},
	  {"qrzt":"<span class='affirm'>已确认</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06","lcfe":"800,000,000.00","cpzjz":"800,000,000.00	","shouyilv":"0.071000",},
	  {"qrzt":"<span class='affirm'>已确认</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25","lcfe":"200,004,729.03","cpzjz":"200,032,857.06","shouyilv":"0.383791",},
	  {"qrzt":"<span class='affirm'>已确认</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06","lcfe":"800,000,000.00","cpzjz":"800,000,000.00	","shouyilv":"0.071000",},
	  {"qrzt":"<span class='affirm'>已确认</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25","lcfe":"200,004,729.03","cpzjz":"200,032,857.06","shouyilv":"0.383791",},
	  {"qrzt":"<span class='affirm'>已确认</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06","lcfe":"800,000,000.00","cpzjz":"800,000,000.00	","shouyilv":"0.071000",},
	  {"qrzt":"<span class='affirm'>已确认</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25","lcfe":"200,004,729.03","cpzjz":"200,032,857.06","shouyilv":"0.383791",},
	  {"qrzt":"<span class='affirm'>已确认</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06","lcfe":"800,000,000.00","cpzjz":"800,000,000.00	","shouyilv":"0.071000",},
	  {"qrzt":"<span class='affirm'>已确认</span>","cpdm":"CMHB001","cpjc":"货币型产品开放式001","cplx":"货币型","gzrq":"2017/12/25","lcfe":"200,004,729.03","cpzjz":"200,032,857.06","shouyilv":"0.383791",},
	  {"qrzt":"<span class='affirm'>已确认</span>","cpdm":"A130F0003","cpjc":"惠益13号2期同业","cplx":"收益型","gzrq":"2017/07/06","lcfe":"800,000,000.00","cpzjz":"800,000,000.00	","shouyilv":"0.071000",},
	],
	searchOnInit: true,
	cellMinWidth:100 ,
	cols:[
          {field:'qrzt', title:'确认状态', width:90, fixed:true, sort:true},
		  {field:'cpdm', title:'产品代码', width:150, sort:true},
		  {field:'cpjc', title:'产品简称', width:240, sort:true},
		  {field:'cplx', title:'产品类型', minWidth:90, align:'center', sort:true},
		  {field:'gzrq', title:'估值日期', minWidth:120, align:'center', sort:true},
		  {field:'lcfe', title:'理财份额', minWidth:200, align:'right', sort: true},
		  {field:'cpzjz', title:'产品总净值', minWidth:200, align:'right', sort: true},
		  {field:'shouyilv', title:'单位净值/万份收益率', minWidth:160, align:'right', sort:true},
		  //{fixed:'right', width:90, title:'操作', align:'center', type:'action', buttons: ['edit','delt']}
		]
  });
  
  //查看详情
  $('.ValConfirm001_detail').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("ValConfirm002"),
      basePath + "valuation/valuationmanage/valuationconfirm/ValConfirm002/init",
      "96%", "90%", {pageModel:PAGE_MODEL_NEW});
  });
});