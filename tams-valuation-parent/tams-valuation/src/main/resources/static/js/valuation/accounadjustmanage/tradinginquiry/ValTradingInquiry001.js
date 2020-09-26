$(function () {
  var table=$("#dataTable").joyintable({
	data:[
	  {"jzrq":"2018/05/28","jzls":"BK201805280000422208","ztdm":"ZT0000000000001","ztmc":"某某账套名称","kmh":"220701J20171012","kmmc":"应付托管费","jiedaifx":"贷方","bizhong":"人民币(CNY)","jine":"30,000.00","shuliang":"1","yefx":"贷方","kmye":"270,000.00","ywbm":"BK201805280000422208","ywlb":"J20171012","ywlb":"净值型产品费用计提","jzwd":"合肥黄山路支行",},
	  {"jzrq":"2018/05/28","jzls":"BK201805280000422208","ztdm":"ZT0000000000001","ztmc":"某某账套名称","kmh":"220701J20171012","kmmc":"应付托管费","jiedaifx":"贷方","bizhong":"人民币(CNY)","jine":"30,000.00","shuliang":"1","yefx":"贷方","kmye":"270,000.00","ywbm":"BK201805280000422208","ywlb":"J20171012","ywlb":"净值型产品费用计提","jzwd":"合肥黄山路支行",},
	  {"jzrq":"2018/05/28","jzls":"BK201805280000422208","ztdm":"ZT0000000000001","ztmc":"某某账套名称","kmh":"220701J20171012","kmmc":"应付托管费","jiedaifx":"贷方","bizhong":"人民币(CNY)","jine":"30,000.00","shuliang":"1","yefx":"贷方","kmye":"270,000.00","ywbm":"BK201805280000422208","ywlb":"J20171012","ywlb":"净值型产品费用计提","jzwd":"合肥黄山路支行",},
	  {"jzrq":"2018/05/28","jzls":"BK201805280000422208","ztdm":"ZT0000000000001","ztmc":"某某账套名称","kmh":"220701J20171012","kmmc":"应付托管费","jiedaifx":"贷方","bizhong":"人民币(CNY)","jine":"30,000.00","shuliang":"1","yefx":"贷方","kmye":"270,000.00","ywbm":"BK201805280000422208","ywlb":"J20171012","ywlb":"净值型产品费用计提","jzwd":"合肥黄山路支行",},
	  {"jzrq":"2018/05/28","jzls":"BK201805280000422208","ztdm":"ZT0000000000001","ztmc":"某某账套名称","kmh":"220701J20171012","kmmc":"应付托管费","jiedaifx":"贷方","bizhong":"人民币(CNY)","jine":"30,000.00","shuliang":"1","yefx":"贷方","kmye":"270,000.00","ywbm":"BK201805280000422208","ywlb":"J20171012","ywlb":"净值型产品费用计提","jzwd":"合肥黄山路支行",},
	  {"jzrq":"2018/05/28","jzls":"BK201805280000422208","ztdm":"ZT0000000000001","ztmc":"某某账套名称","kmh":"220701J20171012","kmmc":"应付托管费","jiedaifx":"贷方","bizhong":"人民币(CNY)","jine":"30,000.00","shuliang":"1","yefx":"贷方","kmye":"270,000.00","ywbm":"BK201805280000422208","ywlb":"J20171012","ywlb":"净值型产品费用计提","jzwd":"合肥黄山路支行",},
	  {"jzrq":"2018/05/28","jzls":"BK201805280000422208","ztdm":"ZT0000000000001","ztmc":"某某账套名称","kmh":"220701J20171012","kmmc":"应付托管费","jiedaifx":"贷方","bizhong":"人民币(CNY)","jine":"30,000.00","shuliang":"1","yefx":"贷方","kmye":"270,000.00","ywbm":"BK201805280000422208","ywlb":"J20171012","ywlb":"净值型产品费用计提","jzwd":"合肥黄山路支行",},
	  {"jzrq":"2018/05/28","jzls":"BK201805280000422208","ztdm":"ZT0000000000001","ztmc":"某某账套名称","kmh":"220701J20171012","kmmc":"应付托管费","jiedaifx":"贷方","bizhong":"人民币(CNY)","jine":"30,000.00","shuliang":"1","yefx":"贷方","kmye":"270,000.00","ywbm":"BK201805280000422208","ywlb":"J20171012","ywlb":"净值型产品费用计提","jzwd":"合肥黄山路支行",},
	  {"jzrq":"2018/05/28","jzls":"BK201805280000422208","ztdm":"ZT0000000000001","ztmc":"某某账套名称","kmh":"220701J20171012","kmmc":"应付托管费","jiedaifx":"贷方","bizhong":"人民币(CNY)","jine":"30,000.00","shuliang":"1","yefx":"贷方","kmye":"270,000.00","ywbm":"BK201805280000422208","ywlb":"J20171012","ywlb":"净值型产品费用计提","jzwd":"合肥黄山路支行",},
	  {"jzrq":"2018/05/28","jzls":"BK201805280000422208","ztdm":"ZT0000000000001","ztmc":"某某账套名称","kmh":"220701J20171012","kmmc":"应付托管费","jiedaifx":"贷方","bizhong":"人民币(CNY)","jine":"30,000.00","shuliang":"1","yefx":"贷方","kmye":"270,000.00","ywbm":"BK201805280000422208","ywlb":"J20171012","ywlb":"净值型产品费用计提","jzwd":"合肥黄山路支行",},
	  {"jzrq":"2018/05/28","jzls":"BK201805280000422208","ztdm":"ZT0000000000001","ztmc":"某某账套名称","kmh":"220701J20171012","kmmc":"应付托管费","jiedaifx":"贷方","bizhong":"人民币(CNY)","jine":"30,000.00","shuliang":"1","yefx":"贷方","kmye":"270,000.00","ywbm":"BK201805280000422208","ywlb":"J20171012","ywlb":"净值型产品费用计提","jzwd":"合肥黄山路支行",},
	  {"jzrq":"2018/05/28","jzls":"BK201805280000422208","ztdm":"ZT0000000000001","ztmc":"某某账套名称","kmh":"220701J20171012","kmmc":"应付托管费","jiedaifx":"贷方","bizhong":"人民币(CNY)","jine":"30,000.00","shuliang":"1","yefx":"贷方","kmye":"270,000.00","ywbm":"BK201805280000422208","ywlb":"J20171012","ywlb":"净值型产品费用计提","jzwd":"合肥黄山路支行",},
	  {"jzrq":"2018/05/28","jzls":"BK201805280000422208","ztdm":"ZT0000000000001","ztmc":"某某账套名称","kmh":"220701J20171012","kmmc":"应付托管费","jiedaifx":"贷方","bizhong":"人民币(CNY)","jine":"30,000.00","shuliang":"1","yefx":"贷方","kmye":"270,000.00","ywbm":"BK201805280000422208","ywlb":"J20171012","ywlb":"净值型产品费用计提","jzwd":"合肥黄山路支行",},
	  {"jzrq":"2018/05/28","jzls":"BK201805280000422208","ztdm":"ZT0000000000001","ztmc":"某某账套名称","kmh":"220701J20171012","kmmc":"应付托管费","jiedaifx":"贷方","bizhong":"人民币(CNY)","jine":"30,000.00","shuliang":"1","yefx":"贷方","kmye":"270,000.00","ywbm":"BK201805280000422208","ywlb":"J20171012","ywlb":"净值型产品费用计提","jzwd":"合肥黄山路支行",},
	  {"jzrq":"2018/05/28","jzls":"BK201805280000422208","ztdm":"ZT0000000000001","ztmc":"某某账套名称","kmh":"220701J20171012","kmmc":"应付托管费","jiedaifx":"贷方","bizhong":"人民币(CNY)","jine":"30,000.00","shuliang":"1","yefx":"贷方","kmye":"270,000.00","ywbm":"BK201805280000422208","ywlb":"J20171012","ywlb":"净值型产品费用计提","jzwd":"合肥黄山路支行",},
	  {"jzrq":"2018/05/28","jzls":"BK201805280000422208","ztdm":"ZT0000000000001","ztmc":"某某账套名称","kmh":"220701J20171012","kmmc":"应付托管费","jiedaifx":"贷方","bizhong":"人民币(CNY)","jine":"30,000.00","shuliang":"1","yefx":"贷方","kmye":"270,000.00","ywbm":"BK201805280000422208","ywlb":"J20171012","ywlb":"净值型产品费用计提","jzwd":"合肥黄山路支行",},
	  {"jzrq":"2018/05/28","jzls":"BK201805280000422208","ztdm":"ZT0000000000001","ztmc":"某某账套名称","kmh":"220701J20171012","kmmc":"应付托管费","jiedaifx":"贷方","bizhong":"人民币(CNY)","jine":"30,000.00","shuliang":"1","yefx":"贷方","kmye":"270,000.00","ywbm":"BK201805280000422208","ywlb":"J20171012","ywlb":"净值型产品费用计提","jzwd":"合肥黄山路支行",},
	  {"jzrq":"2018/05/28","jzls":"BK201805280000422208","ztdm":"ZT0000000000001","ztmc":"某某账套名称","kmh":"220701J20171012","kmmc":"应付托管费","jiedaifx":"贷方","bizhong":"人民币(CNY)","jine":"30,000.00","shuliang":"1","yefx":"贷方","kmye":"270,000.00","ywbm":"BK201805280000422208","ywlb":"J20171012","ywlb":"净值型产品费用计提","jzwd":"合肥黄山路支行",},
	  {"jzrq":"2018/05/28","jzls":"BK201805280000422208","ztdm":"ZT0000000000001","ztmc":"某某账套名称","kmh":"220701J20171012","kmmc":"应付托管费","jiedaifx":"贷方","bizhong":"人民币(CNY)","jine":"30,000.00","shuliang":"1","yefx":"贷方","kmye":"270,000.00","ywbm":"BK201805280000422208","ywlb":"J20171012","ywlb":"净值型产品费用计提","jzwd":"合肥黄山路支行",},
	  {"jzrq":"2018/05/28","jzls":"BK201805280000422208","ztdm":"ZT0000000000001","ztmc":"某某账套名称","kmh":"220701J20171012","kmmc":"应付托管费","jiedaifx":"贷方","bizhong":"人民币(CNY)","jine":"30,000.00","shuliang":"1","yefx":"贷方","kmye":"270,000.00","ywbm":"BK201805280000422208","ywlb":"J20171012","ywlb":"净值型产品费用计提","jzwd":"合肥黄山路支行",},
	],
	searchOnInit: true,
	cellMinWidth:100 ,
	cols:[
	      {field:'jzrq', title:'记账日期', width:100, sort:true},
	      {field:'jzls', title:'记账流水', width:150, sort:true},
		  {field:'ztdm', title:'账套代码', width:150, sort:true},
		  {field:'ztmc', title:'账套名称', width:200, sort:true},
		  {field:'kmh', title:'科目号', width:150, sort:true},
		  {field:'kmmc', title:'科目名称', width:150, sort:true},
		  {field:'jiedaifx', title:'借贷方向', width:90, align:'center', sort:true},
		  {field:'bizhong', title:'币种', width:120, sort:true},
		  {field:'jine', title:'借/贷方金额', width:160, align:'right', sort:true},
		  {field:'shuliang', title:'数量', width:100, sort:true},
		  {field:'yefx', title:'余额方向', width:90, align:'center', sort:true},
		  {field:'kmye', title:'科目余额', width:160, align:'right', sort:true},
		  {field:'ywbm', title:'业务编码', width:200, sort:true},
		  {field:'ywlb', title:'业务类别', width:150, sort:true},
		  {field:'jzwd', title:'记账网点', width:150, sort:true},
		  //{fixed:'right', width:'90', title:'操作', align:'center', type:'action', buttons: ['edit','delt']}
		]
  });
  
  //新建
  $('#ValTradingInquiry001_btn_add').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("ValBalanceBuild002"),
      basePath + "valuation/accounadjustmanage/tradinginquiry/ValTradingInquiry002/init",
      "1000px", "360px", {pageModel:PAGE_MODEL_NEW});
  });

});