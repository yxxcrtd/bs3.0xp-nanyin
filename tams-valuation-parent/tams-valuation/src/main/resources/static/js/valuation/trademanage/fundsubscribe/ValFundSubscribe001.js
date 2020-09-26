$(function () {
	var table=$("#dataTable").joyintable({
	    data:[
		  	{"jybh":"JY000000000000000001","cpmc":"某某的某某的某某的产品名称","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","jjmc":"某某的某某的某某的基金名称","jigou":"机构名称1","rengou":"2018/04/15","qrsd":"T+1","rgqrr":"2018/04/17","jysc":"中国黄金交易所","rgje":"6,000,000,000,000","jyds":"李德富","ruzhang":"2018/04/30","bfzh":"我方账户1","dfzh":"对方账户1","jybz":"人民币","jzbz":"人民币","huilv":"2.15%"},
			{"jybh":"JY000000000000000002","cpmc":"某某的某某的某某的产品名称","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","jjmc":"某某的某某的某某的基金名称","jigou":"机构名称1","rengou":"2018/04/15","qrsd":"T+1","rgqrr":"2018/04/17","jysc":"中国黄金交易所","rgje":"6,000,000,000,000","jyds":"李德富","ruzhang":"2018/04/30","bfzh":"我方账户1","dfzh":"对方账户1","jybz":"人民币","jzbz":"人民币","huilv":"2.15%"},
			{"jybh":"JY000000000000000003","cpmc":"某某的某某的某某的产品名称","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","jjmc":"某某的某某的某某的基金名称","jigou":"机构名称1","rengou":"2018/04/15","qrsd":"T+1","rgqrr":"2018/04/17","jysc":"中国黄金交易所","rgje":"6,000,000,000,000","jyds":"李德富","ruzhang":"2018/04/30","bfzh":"我方账户1","dfzh":"对方账户1","jybz":"人民币","jzbz":"人民币","huilv":"2.15%"},
			{"jybh":"JY000000000000000004","cpmc":"某某的某某的某某的产品名称","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","jjmc":"某某的某某的某某的基金名称","jigou":"机构名称1","rengou":"2018/04/15","qrsd":"T+1","rgqrr":"2018/04/17","jysc":"中国黄金交易所","rgje":"6,000,000,000,000","jyds":"李德富","ruzhang":"2018/04/30","bfzh":"我方账户1","dfzh":"对方账户1","jybz":"人民币","jzbz":"人民币","huilv":"2.15%"},
			{"jybh":"JY000000000000000005","cpmc":"某某的某某的某某的产品名称","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","jjmc":"某某的某某的某某的基金名称","jigou":"机构名称1","rengou":"2018/04/15","qrsd":"T+1","rgqrr":"2018/04/17","jysc":"中国黄金交易所","rgje":"6,000,000,000,000","jyds":"李德富","ruzhang":"2018/04/30","bfzh":"我方账户1","dfzh":"对方账户1","jybz":"人民币","jzbz":"人民币","huilv":"2.15%"},
			{"jybh":"JY000000000000000006","cpmc":"某某的某某的某某的产品名称","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","jjmc":"某某的某某的某某的基金名称","jigou":"机构名称1","rengou":"2018/04/15","qrsd":"T+1","rgqrr":"2018/04/17","jysc":"中国黄金交易所","rgje":"6,000,000,000,000","jyds":"李德富","ruzhang":"2018/04/30","bfzh":"我方账户1","dfzh":"对方账户1","jybz":"人民币","jzbz":"人民币","huilv":"2.15%"},
			{"jybh":"JY000000000000000007","cpmc":"某某的某某的某某的产品名称","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","jjmc":"某某的某某的某某的基金名称","jigou":"机构名称1","rengou":"2018/04/15","qrsd":"T+1","rgqrr":"2018/04/17","jysc":"中国黄金交易所","rgje":"6,000,000,000,000","jyds":"李德富","ruzhang":"2018/04/30","bfzh":"我方账户1","dfzh":"对方账户1","jybz":"人民币","jzbz":"人民币","huilv":"2.15%"},
			{"jybh":"JY000000000000000008","cpmc":"某某的某某的某某的产品名称","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","jjmc":"某某的某某的某某的基金名称","jigou":"机构名称1","rengou":"2018/04/15","qrsd":"T+1","rgqrr":"2018/04/17","jysc":"中国黄金交易所","rgje":"6,000,000,000,000","jyds":"李德富","ruzhang":"2018/04/30","bfzh":"我方账户1","dfzh":"对方账户1","jybz":"人民币","jzbz":"人民币","huilv":"2.15%"},
			{"jybh":"JY000000000000000009","cpmc":"某某的某某的某某的产品名称","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","jjmc":"某某的某某的某某的基金名称","jigou":"机构名称1","rengou":"2018/04/15","qrsd":"T+1","rgqrr":"2018/04/17","jysc":"中国黄金交易所","rgje":"6,000,000,000,000","jyds":"李德富","ruzhang":"2018/04/30","bfzh":"我方账户1","dfzh":"对方账户1","jybz":"人民币","jzbz":"人民币","huilv":"2.15%"},
			{"jybh":"JY000000000000000010","cpmc":"某某的某某的某某的产品名称","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","jjmc":"某某的某某的某某的基金名称","jigou":"机构名称1","rengou":"2018/04/15","qrsd":"T+1","rgqrr":"2018/04/17","jysc":"中国黄金交易所","rgje":"6,000,000,000,000","jyds":"李德富","ruzhang":"2018/04/30","bfzh":"我方账户1","dfzh":"对方账户1","jybz":"人民币","jzbz":"人民币","huilv":"2.15%"}
		 ],
		//height:781,
		searchOnInit: true,
		cols: [
			  {field:'jybh',title: '交易编号',width:160,fixed:true,sort: true},
			  {field:'cpmc',title: '产品',width:240,fixed:true,sort: true},
		 	  {field:'ztdm', title: '账套代码',width:130,sort: true},
		 	  {field:'jjdm',title: '基金代码',width:160,sort: true},
			  {field:'jjmc',title: '基金名称',width:240,sort: true},
			  {field:'rengou',title: '认购日',width:120,sort: true},
			  {field:'qrsd',title: '确认速度',width:80,sort: true},
			  {field:'rgqrr',title: '认购确认日',width:90,sort: true},
			  {field:'jysc',title: '交易市场',width:180,sort: true},
			  {field:'rgje',title: '认购金额(元)',width:150,sort: true},
			  {field:'jyds',title: '交易对手',width:120,sort: true},
			  {field:'ruzhang',title: '入账日',width:120,sort: true},
			  {field:'bfzh',title: '本方账户',width:180,sort: true},
			  {field:'dfzh',title: '对方账户',width:180,sort: true},
			  {field:'jybz',title: '交易币种',width:100,sort: true},
			  {field:'jzbz',title: '记账币种',width:100,sort: true},
			  {field:'huilv',title: '汇率',width:100,sort: true},
		 	  //{fixed:'right',width:'90', title: '操作', align:'center', type:'action', buttons: ['edit','delt']}
		 	]
	  });
  
  //新建
  $('#ValFundSubscribe001_btn_add').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建-认购申请(货币型)"),
      basePath + "valuation/trademanage/fundsubscribe/ValFundSubscribe002/init",
      "1000px", "400px", {pageModel:PAGE_MODEL_NEW});
  });
  $('#ValFundSubscribe001_btn_add1').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建-认购申请(净值型)"),
      basePath + "valuation/trademanage/fundsubscribe/ValFundSubscribe003/init",
      "1000px", "400px", {pageModel:PAGE_MODEL_NEW});
  });
  $('#ValFundSubscribe001_btn_add2').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建-认购确认"),
      basePath + "valuation/trademanage/fundsubscribe/ValFundSubscribe004/init",
      "1000px", "360px", {pageModel:PAGE_MODEL_NEW});
  });
  $('#ValFundSubscribe001_btn_add3').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建-认购返款"),
      basePath + "valuation/trademanage/fundsubscribe/ValFundSubscribe005/init",
      "1000px", "350px", {pageModel:PAGE_MODEL_NEW});
  });
  $('#ValFundSubscribe001_btn_add4').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建-返款利息确认"),
      basePath + "valuation/trademanage/fundsubscribe/ValFundSubscribe006/init",
      "1000px", "350px", {pageModel:PAGE_MODEL_NEW});
  });

});