$(function () {
	var table=$("#dataTable").joyintable({
	    data:[
		  	{"cpmc":"某某的某某产品名称1","ztdm":"ZT0000000000001","mjj":"SC0000000000001","jjdm":"JJ000000000000000001","jjmc":"某某的某某基金名称","jysc":"深交所","cfrq":"2018/04/15","ccfe":"10,000","ccje":"10,000","ccjz":"10,000","crjj1":"ZJJ000000000000000001","crjjmc1":"某某的某某基金名称","ccfe1":"5,000","ccje1":"5,000","crjj2":"ZJJ000000000000000001","crjjmc2":"某某的某某基金名称","ccfe2":"5,000","ccje2":"5,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"cpmc":"某某的某某产品名称2","ztdm":"ZT0000000000001","mjj":"SC0000000000001","jjdm":"JJ000000000000000001","jjmc":"某某的某某基金名称","jysc":"深交所","cfrq":"2018/04/15","ccfe":"10,000","ccje":"10,000","ccjz":"10,000","crjj1":"ZJJ000000000000000001","crjjmc1":"某某的某某基金名称","ccfe1":"5,000","ccje1":"5,000","crjj2":"ZJJ000000000000000001","crjjmc2":"某某的某某基金名称","ccfe2":"5,000","ccje2":"5,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"cpmc":"某某的某某产品名称3","ztdm":"ZT0000000000001","mjj":"SC0000000000001","jjdm":"JJ000000000000000001","jjmc":"某某的某某基金名称","jysc":"深交所","cfrq":"2018/04/15","ccfe":"10,000","ccje":"10,000","ccjz":"10,000","crjj1":"ZJJ000000000000000001","crjjmc1":"某某的某某基金名称","ccfe1":"5,000","ccje1":"5,000","crjj2":"ZJJ000000000000000001","crjjmc2":"某某的某某基金名称","ccfe2":"5,000","ccje2":"5,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"cpmc":"某某的某某产品名称4","ztdm":"ZT0000000000001","mjj":"SC0000000000001","jjdm":"JJ000000000000000001","jjmc":"某某的某某基金名称","jysc":"深交所","cfrq":"2018/04/15","ccfe":"10,000","ccje":"10,000","ccjz":"10,000","crjj1":"ZJJ000000000000000001","crjjmc1":"某某的某某基金名称","ccfe1":"5,000","ccje1":"5,000","crjj2":"ZJJ000000000000000001","crjjmc2":"某某的某某基金名称","ccfe2":"5,000","ccje2":"5,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"cpmc":"某某的某某产品名称5","ztdm":"ZT0000000000001","mjj":"SC0000000000001","jjdm":"JJ000000000000000001","jjmc":"某某的某某基金名称","jysc":"深交所","cfrq":"2018/04/15","ccfe":"10,000","ccje":"10,000","ccjz":"10,000","crjj1":"ZJJ000000000000000001","crjjmc1":"某某的某某基金名称","ccfe1":"5,000","ccje1":"5,000","crjj2":"ZJJ000000000000000001","crjjmc2":"某某的某某基金名称","ccfe2":"5,000","ccje2":"5,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"cpmc":"某某的某某产品名称6","ztdm":"ZT0000000000001","mjj":"SC0000000000001","jjdm":"JJ000000000000000001","jjmc":"某某的某某基金名称","jysc":"深交所","cfrq":"2018/04/15","ccfe":"10,000","ccje":"10,000","ccjz":"10,000","crjj1":"ZJJ000000000000000001","crjjmc1":"某某的某某基金名称","ccfe1":"5,000","ccje1":"5,000","crjj2":"ZJJ000000000000000001","crjjmc2":"某某的某某基金名称","ccfe2":"5,000","ccje2":"5,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"cpmc":"某某的某某产品名称7","ztdm":"ZT0000000000001","mjj":"SC0000000000001","jjdm":"JJ000000000000000001","jjmc":"某某的某某基金名称","jysc":"深交所","cfrq":"2018/04/15","ccfe":"10,000","ccje":"10,000","ccjz":"10,000","crjj1":"ZJJ000000000000000001","crjjmc1":"某某的某某基金名称","ccfe1":"5,000","ccje1":"5,000","crjj2":"ZJJ000000000000000001","crjjmc2":"某某的某某基金名称","ccfe2":"5,000","ccje2":"5,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"cpmc":"某某的某某产品名称8","ztdm":"ZT0000000000001","mjj":"SC0000000000001","jjdm":"JJ000000000000000001","jjmc":"某某的某某基金名称","jysc":"深交所","cfrq":"2018/04/15","ccfe":"10,000","ccje":"10,000","ccjz":"10,000","crjj1":"ZJJ000000000000000001","crjjmc1":"某某的某某基金名称","ccfe1":"5,000","ccje1":"5,000","crjj2":"ZJJ000000000000000001","crjjmc2":"某某的某某基金名称","ccfe2":"5,000","ccje2":"5,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"cpmc":"某某的某某产品名称9","ztdm":"ZT0000000000001","mjj":"SC0000000000001","jjdm":"JJ000000000000000001","jjmc":"某某的某某基金名称","jysc":"深交所","cfrq":"2018/04/15","ccfe":"10,000","ccje":"10,000","ccjz":"10,000","crjj1":"ZJJ000000000000000001","crjjmc1":"某某的某某基金名称","ccfe1":"5,000","ccje1":"5,000","crjj2":"ZJJ000000000000000001","crjjmc2":"某某的某某基金名称","ccfe2":"5,000","ccje2":"5,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"cpmc":"某某的某某产品名称10","ztdm":"ZT0000000000001","mjj":"SC0000000000001","jjdm":"JJ000000000000000001","jjmc":"某某的某某基金名称","jysc":"深交所","cfrq":"2018/04/15","ccfe":"10,000","ccje":"10,000","ccjz":"10,000","crjj1":"ZJJ000000000000000001","crjjmc1":"某某的某某基金名称","ccfe1":"5,000","ccje1":"5,000","crjj2":"ZJJ000000000000000001","crjjmc2":"某某的某某基金名称","ccfe2":"5,000","ccje2":"5,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
		 ],
		//height:781,
		searchOnInit: true,
		cols: [
			  {field:'cpmc',title: '产品',width:240,fixed:true,sort: true},
			  {field:'ztdm',title: '账套代码',width:130,fixed:true,sort: true},
		 	  {field:'mjj', title: '母基金',width:100,fixed:true,sort: true},
			  {field:'jjdm', title: '基金代码',width:160,sort: true},
			  {field:'jjmc',title: '基金名称',width:240,sort: true},
			  {field:'jysc',title: '交易市场',width:150,sort: true},
			  {field:'cfrq',title: '拆分日期',width:90,sort: true},
			  {field:'ccfe',title: '拆出份额',width:150,sort: true},
			  {field:'ccje',title: '拆出金额',width:150,sort: true},
			  {field:'ccjz',title: '拆出净值',width:150,sort: true},
			  {field:'crjj1', title: '拆入基金1',width:160,sort: true},
			  {field:'crjjmc1',title: '基金1名称',width:240,sort: true},
			  {field:'ccfe1',title: '拆入份额1',width:150,sort: true},
			  {field:'ccje1',title: '拆入金额1',width:150,sort: true},
			  {field:'crjj2', title: '拆入基金2',width:160,sort: true},
			  {field:'crjjmc2',title: '基金2名称',width:240,sort: true},
			  {field:'ccfe2',title: '拆入份额2',width:150,sort: true},
			  {field:'ccje2',title: '拆入金额2',width:150,sort: true},
			  {field:'ruzhangr',title: '入账日',width:90,sort: true},
			  {field:'jybz',title: '交易币种',width:90,sort: true},
			  {field:'jzbz',title: '记账币种',width:90,sort: true},
			  {field:'huilv',title: '汇率',width:90,sort: true},
		 	  //{fixed:'right',width:'90', title: '操作', align:'center', type:'action', buttons: ['edit','delt']}
		 	]
	  });
  
  //新建
  $('#ValFundSplitMerge001_btn_add').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建-基金拆分"),
      basePath + "valuation/trademanage/fundsplitmerge/ValFundSplitMerge002/init",
      "1000px", "520px", {pageModel:PAGE_MODEL_NEW});
  });
  $('#ValFundSplitMerge001_btn_add1').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建-基金合并"),
      basePath + "valuation/trademanage/fundsplitmerge/ValFundSplitMerge003/init",
      "1000px", "520px", {pageModel:PAGE_MODEL_NEW});
  });
  
  

});