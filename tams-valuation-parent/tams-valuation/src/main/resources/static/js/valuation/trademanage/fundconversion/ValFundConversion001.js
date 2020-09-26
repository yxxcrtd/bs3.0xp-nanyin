$(function () {
	var table=$("#dataTable").joyintable({
	    data:[
		  	{"cpmc":"某某的某某产品名称1","ztdm":"ZT0000000000001","zcjjdm":"JJ000000000000000001","zcjjmc":"某某的某某基金名称","zcscdm":"SC0000000000001","zcjysc":"上交所","zcrq":"2018/04/15","zcfe":"10,000","zcfy":"1,000","zcje":"50,000","zrjjdm":"JJ000000000000000001","zrjjmc":"某某的某某基金名称","zrscdm":"SC0000000000001","zrjysc":"深交所","zrfe":"10,000","zrjz":"50,000","zrje":"50,000","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"cpmc":"某某的某某产品名称2","ztdm":"ZT0000000000001","zcjjdm":"JJ000000000000000001","zcjjmc":"某某的某某基金名称","zcscdm":"SC0000000000001","zcjysc":"上交所","zcrq":"2018/04/15","zcfe":"10,000","zcfy":"1,000","zcje":"50,000","zrjjdm":"JJ000000000000000001","zrjjmc":"某某的某某基金名称","zrscdm":"SC0000000000001","zrjysc":"深交所","zrfe":"10,000","zrjz":"50,000","zrje":"50,000","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"cpmc":"某某的某某产品名称3","ztdm":"ZT0000000000001","zcjjdm":"JJ000000000000000001","zcjjmc":"某某的某某基金名称","zcscdm":"SC0000000000001","zcjysc":"上交所","zcrq":"2018/04/15","zcfe":"10,000","zcfy":"1,000","zcje":"50,000","zrjjdm":"JJ000000000000000001","zrjjmc":"某某的某某基金名称","zrscdm":"SC0000000000001","zrjysc":"深交所","zrfe":"10,000","zrjz":"50,000","zrje":"50,000","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"cpmc":"某某的某某产品名称4","ztdm":"ZT0000000000001","zcjjdm":"JJ000000000000000001","zcjjmc":"某某的某某基金名称","zcscdm":"SC0000000000001","zcjysc":"上交所","zcrq":"2018/04/15","zcfe":"10,000","zcfy":"1,000","zcje":"50,000","zrjjdm":"JJ000000000000000001","zrjjmc":"某某的某某基金名称","zrscdm":"SC0000000000001","zrjysc":"深交所","zrfe":"10,000","zrjz":"50,000","zrje":"50,000","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"cpmc":"某某的某某产品名称5","ztdm":"ZT0000000000001","zcjjdm":"JJ000000000000000001","zcjjmc":"某某的某某基金名称","zcscdm":"SC0000000000001","zcjysc":"上交所","zcrq":"2018/04/15","zcfe":"10,000","zcfy":"1,000","zcje":"50,000","zrjjdm":"JJ000000000000000001","zrjjmc":"某某的某某基金名称","zrscdm":"SC0000000000001","zrjysc":"深交所","zrfe":"10,000","zrjz":"50,000","zrje":"50,000","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"cpmc":"某某的某某产品名称6","ztdm":"ZT0000000000001","zcjjdm":"JJ000000000000000001","zcjjmc":"某某的某某基金名称","zcscdm":"SC0000000000001","zcjysc":"上交所","zcrq":"2018/04/15","zcfe":"10,000","zcfy":"1,000","zcje":"50,000","zrjjdm":"JJ000000000000000001","zrjjmc":"某某的某某基金名称","zrscdm":"SC0000000000001","zrjysc":"深交所","zrfe":"10,000","zrjz":"50,000","zrje":"50,000","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"cpmc":"某某的某某产品名称7","ztdm":"ZT0000000000001","zcjjdm":"JJ000000000000000001","zcjjmc":"某某的某某基金名称","zcscdm":"SC0000000000001","zcjysc":"上交所","zcrq":"2018/04/15","zcfe":"10,000","zcfy":"1,000","zcje":"50,000","zrjjdm":"JJ000000000000000001","zrjjmc":"某某的某某基金名称","zrscdm":"SC0000000000001","zrjysc":"深交所","zrfe":"10,000","zrjz":"50,000","zrje":"50,000","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"cpmc":"某某的某某产品名称8","ztdm":"ZT0000000000001","zcjjdm":"JJ000000000000000001","zcjjmc":"某某的某某基金名称","zcscdm":"SC0000000000001","zcjysc":"上交所","zcrq":"2018/04/15","zcfe":"10,000","zcfy":"1,000","zcje":"50,000","zrjjdm":"JJ000000000000000001","zrjjmc":"某某的某某基金名称","zrscdm":"SC0000000000001","zrjysc":"深交所","zrfe":"10,000","zrjz":"50,000","zrje":"50,000","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"cpmc":"某某的某某产品名称9","ztdm":"ZT0000000000001","zcjjdm":"JJ000000000000000001","zcjjmc":"某某的某某基金名称","zcscdm":"SC0000000000001","zcjysc":"上交所","zcrq":"2018/04/15","zcfe":"10,000","zcfy":"1,000","zcje":"50,000","zrjjdm":"JJ000000000000000001","zrjjmc":"某某的某某基金名称","zrscdm":"SC0000000000001","zrjysc":"深交所","zrfe":"10,000","zrjz":"50,000","zrje":"50,000","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"cpmc":"某某的某某产品名称10","ztdm":"ZT0000000000001","zcjjdm":"JJ000000000000000001","zcjjmc":"某某的某某基金名称","zcscdm":"SC0000000000001","zcjysc":"上交所","zcrq":"2018/04/15","zcfe":"10,000","zcfy":"1,000","zcje":"50,000","zrjjdm":"JJ000000000000000001","zrjjmc":"某某的某某基金名称","zrscdm":"SC0000000000001","zrjysc":"深交所","zrfe":"10,000","zrjz":"50,000","zrje":"50,000","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"}	
		 ],
		//height:781,
		searchOnInit: true,
		cols: [
			  {field:'cpmc',title: '产品',width:240,fixed:true,sort: true},
			  {field:'ztdm',title: '账套代码',width:130,fixed:true,sort: true},
		 	  {field:'zcjjdm', title: '转出基金代码',width:160,sort: true},
		 	  {field:'zcjjmc',title: '转出基金名称',width:240,sort: true},
			  {field:'zcscdm',title: '转出市场代码',width:100,sort: true},
			  {field:'zcjysc',title: '交易市场',width:150,sort: true},
			  {field:'zcrq',title: '转出日期',width:90,sort: true},
			  {field:'zcfe',title: '转出份额',width:150,sort: true},
			  {field:'zcfy',title: '转出费用',width:150,sort: true},
			  {field:'zcje',title: '转出金额',width:150,sort: true},
			  {field:'zrjjdm',title: '转入基金代码',width:160,sort: true},
		 	  {field:'zrjjmc',title: '转入基金名称',width:240,sort: true},
			  {field:'zrscdm',title: '转入市场代码',width:100,sort: true},
			  {field:'zrjysc',title: '交易市场',width:150,sort: true},
			  {field:'zrfe',title: '转入份额',width:150,sort: true},
			  {field:'zrjz',title: '转入净值',width:150,sort: true},
			  {field:'zrje',title: '转入金额',width:150,sort: true},
			  {field:'ruzhangr',title: '入账日',width:90,sort: true},
			  {field:'jybz',title: '交易币种',width:90,sort: true},
			  {field:'jzbz',title: '记账币种',width:90,sort: true},
			  {field:'huilv',title: '汇率',width:90,sort: true},
		 	  //{fixed:'right',width:'90', title: '操作', align:'center', type:'action', buttons: ['edit','delt']}
		 	]
	  });
  
  //新建
  $('#ValFundConversion001_btn_add').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建-基金转入转出交易(货币型转出)"),
      basePath + "valuation/trademanage/fundconversion/ValFundConversion002/init",
      "1000px", "360px", {pageModel:PAGE_MODEL_NEW});
  });
  $('#ValFundConversion001_btn_add1').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建-基金转入转出交易(净值型转出）"),
      basePath + "valuation/trademanage/fundconversion/ValFundConversion003/init",
      "1000px", "360px", {pageModel:PAGE_MODEL_NEW});
  });
  

});