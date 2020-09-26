$(function () {
	var table=$("#dataTable").joyintable({
		  data:[
			{"jybh":"JY000000000000000001","cpmc":"某某的某某产品名称1","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","zrrq":"2018/04/15","zrfe":"10,000","yjhl":"10,000","zrfy":"100","zrje":"50,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"jybh":"JY000000000000000002","cpmc":"某某的某某产品名称1","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","zrrq":"2018/04/15","zrfe":"10,000","yjhl":"10,000","zrfy":"100","zrje":"50,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"jybh":"JY000000000000000003","cpmc":"某某的某某产品名称1","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","zrrq":"2018/04/15","zrfe":"10,000","yjhl":"10,000","zrfy":"100","zrje":"50,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"jybh":"JY000000000000000004","cpmc":"某某的某某产品名称1","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","zrrq":"2018/04/15","zrfe":"10,000","yjhl":"10,000","zrfy":"100","zrje":"50,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"jybh":"JY000000000000000005","cpmc":"某某的某某产品名称1","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","zrrq":"2018/04/15","zrfe":"10,000","yjhl":"10,000","zrfy":"100","zrje":"50,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"jybh":"JY000000000000000006","cpmc":"某某的某某产品名称1","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","zrrq":"2018/04/15","zrfe":"10,000","yjhl":"10,000","zrfy":"100","zrje":"50,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"jybh":"JY000000000000000007","cpmc":"某某的某某产品名称1","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","zrrq":"2018/04/15","zrfe":"10,000","yjhl":"10,000","zrfy":"100","zrje":"50,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"jybh":"JY000000000000000008","cpmc":"某某的某某产品名称1","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","zrrq":"2018/04/15","zrfe":"10,000","yjhl":"10,000","zrfy":"100","zrje":"50,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"jybh":"JY000000000000000009","cpmc":"某某的某某产品名称1","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","zrrq":"2018/04/15","zrfe":"10,000","yjhl":"10,000","zrfy":"100","zrje":"50,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
			{"jybh":"JY000000000000000010","cpmc":"某某的某某产品名称1","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","zrrq":"2018/04/15","zrfe":"10,000","yjhl":"10,000","zrfy":"100","zrje":"50,000","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","huilv":"5.9%"},
		 ],
		//height:781,
		  searchOnInit: true,
		  cols: [
			  {field:'jybh',title: '交易编号',width:160,fixed:true,sort: true},
			  {field:'cpmc',title: '产品',width:240,fixed:true,sort: true},
			  {field:'ztdm',title: '账套代码',width:130,fixed:true,sort: true},
			  {field:'jjdm', title: '基金代码',width:160,sort: true},
			  {field:'scdm', title: '市场代码',width:100,sort: true},
			  {field:'jjmc',title: '基金名称',width:240,sort: true},
			  {field:'zrrq',title: '转入日期',width:90,sort: true},
			  {field:'zrfe',title: '转入份额',width:150,sort: true},
			  {field:'yjhl',title: '应计红利',width:150,sort: true},
			  {field:'zrfy',title: '转入费用',width:150,sort: true},
			  {field:'zrje',title: '转入金额',width:150,sort: true},
			  {field:'ruzhangr',title: '入账日',width:90,sort: true},
			  {field:'jybz',title: '交易币种',width:90,sort: true},
			  {field:'jzbz',title: '记账币种',width:90,sort: true},
			  {field:'huilv',title: '汇率',width:90,sort: true},
			  //{fixed:'right',width:'90', title: '操作', align:'center', type:'action', buttons: ['edit','delt']}
			]
		  });
  
  //新建
  $('#ValFundTransfer001_btn_add').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建-基金转入(货币型)"),
      basePath + "valuation/trademanage/fundtransfer/ValFundTransfer002/init",
      "1000px", "420px", {pageModel:PAGE_MODEL_NEW});
  });
  $('#ValFundTransfer001_btn_add1').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建-基金转入(净值型)"),
      basePath + "valuation/trademanage/fundtransfer/ValFundTransfer003/init",
      "1000px", "420px", {pageModel:PAGE_MODEL_NEW});
  });
  $('#ValFundTransfer001_btn_add2').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建-基金转出(货币型)"),
      basePath + "valuation/trademanage/fundtransfer/ValFundTransfer004/init",
      "1000px", "420px", {pageModel:PAGE_MODEL_NEW});
  });
  $('#ValFundTransfer001_btn_add3').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建-基金转出(净值型)"),
      basePath + "valuation/trademanage/fundtransfer/ValFundTransfer004/init",
      "1000px", "420px", {pageModel:PAGE_MODEL_NEW});
  });

});