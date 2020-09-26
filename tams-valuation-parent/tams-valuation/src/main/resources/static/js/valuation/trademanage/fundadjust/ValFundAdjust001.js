$(function () {
	var table=$("#dataTable").joyintable({
	    data:[
		  	{"cpmc":"某某的某某产品名称1","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","jysc":"深交所","tzrq":"2018/04/15","tzje":"10,000","tzfe":"10,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"cpmc":"某某的某某产品名称2","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","jysc":"深交所","tzrq":"2018/04/15","tzje":"10,000","tzfe":"10,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"cpmc":"某某的某某产品名称3","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","jysc":"深交所","tzrq":"2018/04/15","tzje":"10,000","tzfe":"10,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"cpmc":"某某的某某产品名称4","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","jysc":"深交所","tzrq":"2018/04/15","tzje":"10,000","tzfe":"10,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"cpmc":"某某的某某产品名称5","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","jysc":"深交所","tzrq":"2018/04/15","tzje":"10,000","tzfe":"10,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"cpmc":"某某的某某产品名称6","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","jysc":"深交所","tzrq":"2018/04/15","tzje":"10,000","tzfe":"10,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"cpmc":"某某的某某产品名称7","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","jysc":"深交所","tzrq":"2018/04/15","tzje":"10,000","tzfe":"10,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"cpmc":"某某的某某产品名称8","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","jysc":"深交所","tzrq":"2018/04/15","tzje":"10,000","tzfe":"10,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"cpmc":"某某的某某产品名称9","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","jysc":"深交所","tzrq":"2018/04/15","tzje":"10,000","tzfe":"10,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"cpmc":"某某的某某产品名称10","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","jysc":"深交所","tzrq":"2018/04/15","tzje":"10,000","tzfe":"10,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
		 ],
		//height:781,
		searchOnInit: true,
		cols: [
			  {field:'cpmc',title: '产品',width:240,fixed:true,sort: true},
			  {field:'ztdm',title: '账套代码',width:130,fixed:true,sort: true},
			  {field:'jjdm', title: '基金代码',width:160,fixed:true,sort: true},
			  {field:'scdm', title: '市场代码',width:100,sort: true},
			  {field:'jjmc',title: '基金名称',width:240,sort: true},
			  {field:'jysc',title: '交易市场',width:150,sort: true},
			  {field:'tzrq',title: '调增日期',width:90,sort: true},
			  {field:'tzje',title: '调增金额',width:150,sort: true},
			  {field:'tzfe',title: '调增份额',width:150,sort: true},
			  {field:'ruzhangr',title: '入账日',width:90,sort: true},
			  {field:'jybz',title: '交易币种',width:90,sort: true},
			  {field:'jzbz',title: '记账币种',width:90,sort: true},
			  {field:'huilv',title: '汇率',width:90,sort: true},
		 	  //{fixed:'right',width:'90', title: '操作', align:'center', type:'action', buttons: ['edit','delt']}
		 	]
	  });
  
  //新建
  $('#ValFundAdjust001_btn_add').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建-份额调增"),
      basePath + "valuation/trademanage/fundadjust/ValFundAdjust002/init",
      "1000px", "360px", {pageModel:PAGE_MODEL_NEW});
  });
  $('#ValFundAdjust001_btn_add1').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建-份额调减"),
      basePath + "valuation/trademanage/fundadjust/ValFundAdjust003/init",
      "1000px", "360px", {pageModel:PAGE_MODEL_NEW});
  });
  

});