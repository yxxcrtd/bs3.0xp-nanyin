$(function () {
	var table=$("#dataTable").joyintable({
	    data:[
		  	{"jybh":"JY0000000000000001","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ0000000000000001","jjmc":"某某的某某基金名称","shengour":"2018/04/15","qrsd":"T+N","sgqrr":"2018/04/15","jysc":"上交所","sgje":"6,000,000,000,000","duishou":"李德富","bfzh":"本方账户1","dfzh":"对方账户1","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"6.25%"},
			{"jybh":"JY0000000000000002","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ0000000000000001","jjmc":"某某的某某基金名称","shengour":"2018/04/15","qrsd":"T+N","sgqrr":"2018/04/15","jysc":"上交所","sgje":"6,000,000,000,000","duishou":"李德富","bfzh":"本方账户1","dfzh":"对方账户1","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"6.25%"},
			{"jybh":"JY0000000000000003","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ0000000000000001","jjmc":"某某的某某基金名称","shengour":"2018/04/15","qrsd":"T+N","sgqrr":"2018/04/15","jysc":"上交所","sgje":"6,000,000,000,000","duishou":"李德富","bfzh":"本方账户1","dfzh":"对方账户1","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"6.25%"},
			{"jybh":"JY0000000000000004","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ0000000000000001","jjmc":"某某的某某基金名称","shengour":"2018/04/15","qrsd":"T+N","sgqrr":"2018/04/15","jysc":"上交所","sgje":"6,000,000,000,000","duishou":"李德富","bfzh":"本方账户1","dfzh":"对方账户1","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"6.25%"},
			{"jybh":"JY0000000000000005","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ0000000000000001","jjmc":"某某的某某基金名称","shengour":"2018/04/15","qrsd":"T+N","sgqrr":"2018/04/15","jysc":"上交所","sgje":"6,000,000,000,000","duishou":"李德富","bfzh":"本方账户1","dfzh":"对方账户1","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"6.25%"},
			{"jybh":"JY0000000000000006","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ0000000000000001","jjmc":"某某的某某基金名称","shengour":"2018/04/15","qrsd":"T+N","sgqrr":"2018/04/15","jysc":"上交所","sgje":"6,000,000,000,000","duishou":"李德富","bfzh":"本方账户1","dfzh":"对方账户1","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"6.25%"},
			{"jybh":"JY0000000000000007","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ0000000000000001","jjmc":"某某的某某基金名称","shengour":"2018/04/15","qrsd":"T+N","sgqrr":"2018/04/15","jysc":"上交所","sgje":"6,000,000,000,000","duishou":"李德富","bfzh":"本方账户1","dfzh":"对方账户1","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"6.25%"},
			{"jybh":"JY0000000000000008","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ0000000000000001","jjmc":"某某的某某基金名称","shengour":"2018/04/15","qrsd":"T+N","sgqrr":"2018/04/15","jysc":"上交所","sgje":"6,000,000,000,000","duishou":"李德富","bfzh":"本方账户1","dfzh":"对方账户1","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"6.25%"},
			{"jybh":"JY0000000000000009","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ0000000000000001","jjmc":"某某的某某基金名称","shengour":"2018/04/15","qrsd":"T+N","sgqrr":"2018/04/15","jysc":"上交所","sgje":"6,000,000,000,000","duishou":"李德富","bfzh":"本方账户1","dfzh":"对方账户1","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"6.25%"},
			{"jybh":"JY0000000000000010","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ0000000000000001","jjmc":"某某的某某基金名称","shengour":"2018/04/15","qrsd":"T+N","sgqrr":"2018/04/15","jysc":"上交所","sgje":"6,000,000,000,000","duishou":"李德富","bfzh":"本方账户1","dfzh":"对方账户1","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"6.25%"},
			{"jybh":"JY0000000000000011","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ0000000000000001","jjmc":"某某的某某基金名称","shengour":"2018/04/15","qrsd":"T+N","sgqrr":"2018/04/15","jysc":"上交所","sgje":"6,000,000,000,000","duishou":"李德富","bfzh":"本方账户1","dfzh":"对方账户1","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"6.25%"},
			{"jybh":"JY0000000000000012","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ0000000000000001","jjmc":"某某的某某基金名称","shengour":"2018/04/15","qrsd":"T+N","sgqrr":"2018/04/15","jysc":"上交所","sgje":"6,000,000,000,000","duishou":"李德富","bfzh":"本方账户1","dfzh":"对方账户1","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"6.25%"},
			{"jybh":"JY0000000000000013","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ0000000000000001","jjmc":"某某的某某基金名称","shengour":"2018/04/15","qrsd":"T+N","sgqrr":"2018/04/15","jysc":"上交所","sgje":"6,000,000,000,000","duishou":"李德富","bfzh":"本方账户1","dfzh":"对方账户1","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"6.25%"},
			{"jybh":"JY0000000000000014","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ0000000000000001","jjmc":"某某的某某基金名称","shengour":"2018/04/15","qrsd":"T+N","sgqrr":"2018/04/15","jysc":"上交所","sgje":"6,000,000,000,000","duishou":"李德富","bfzh":"本方账户1","dfzh":"对方账户1","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"6.25%"},
			{"jybh":"JY0000000000000015","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ0000000000000001","jjmc":"某某的某某基金名称","shengour":"2018/04/15","qrsd":"T+N","sgqrr":"2018/04/15","jysc":"上交所","sgje":"6,000,000,000,000","duishou":"李德富","bfzh":"本方账户1","dfzh":"对方账户1","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"6.25%"},
			{"jybh":"JY0000000000000016","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ0000000000000001","jjmc":"某某的某某基金名称","shengour":"2018/04/15","qrsd":"T+N","sgqrr":"2018/04/15","jysc":"上交所","sgje":"6,000,000,000,000","duishou":"李德富","bfzh":"本方账户1","dfzh":"对方账户1","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"6.25%"},
			{"jybh":"JY0000000000000017","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ0000000000000001","jjmc":"某某的某某基金名称","shengour":"2018/04/15","qrsd":"T+N","sgqrr":"2018/04/15","jysc":"上交所","sgje":"6,000,000,000,000","duishou":"李德富","bfzh":"本方账户1","dfzh":"对方账户1","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"6.25%"},
			{"jybh":"JY0000000000000018","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ0000000000000001","jjmc":"某某的某某基金名称","shengour":"2018/04/15","qrsd":"T+N","sgqrr":"2018/04/15","jysc":"上交所","sgje":"6,000,000,000,000","duishou":"李德富","bfzh":"本方账户1","dfzh":"对方账户1","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"6.25%"},
			{"jybh":"JY0000000000000019","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ0000000000000001","jjmc":"某某的某某基金名称","shengour":"2018/04/15","qrsd":"T+N","sgqrr":"2018/04/15","jysc":"上交所","sgje":"6,000,000,000,000","duishou":"李德富","bfzh":"本方账户1","dfzh":"对方账户1","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"6.25%"},
			{"jybh":"JY0000000000000020","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ0000000000000001","jjmc":"某某的某某基金名称","shengour":"2018/04/15","qrsd":"T+N","sgqrr":"2018/04/15","jysc":"上交所","sgje":"6,000,000,000,000","duishou":"李德富","bfzh":"本方账户1","dfzh":"对方账户1","ruzhangr":"2018/04/16","jybz":"人民币","jzbz":"人民币","huilv":"6.25%"}
		 ],
		//height:781,
		searchOnInit: true,
		cols: [
			  {field:'jybh',title: '交易编号',width:160,fixed:true,sort: true},
			  {field:'cpmc',title: '产品',width:240,fixed:true,sort: true},
		 	  {field:'ztdm', title: '账套代码',width:130,sort: true},
		 	  {field:'jjdm',title: '基金代码',width:160,sort: true},
			  {field:'jjmc',title: '基金名称',width:240,sort: true},
			  {field:'shengour',title: '申购日',width:90,sort: true},
			  {field:'qrsd',title: '确认速度',width:80,sort: true},
			  {field:'sgqrr',title: '申购确认日',width:90,sort: true},
			  {field:'jysc',title: '交易市场',width:120,sort: true},
			  {field:'sgje',title: '申购金额(元)',width:150,sort: true},
			  {field:'duishou',title: '交易对手',width:150,sort: true},
			  {field:'bfzh',title: '本方账户',width:200,sort: true},
			  {field:'dfzh',title: '对方账户',width:200,sort: true},
			  {field:'ruzhangr',title: '入账日',width:90,sort: true},
			  {field:'jybz',title: '交易币种',width:90,sort: true},
			  {field:'jzbz',title: '交易币种',width:90,sort: true},
			  {field:'huilv',title: '汇率',width:80,sort: true},
		 	  //{fixed:'right',width:'90', title: '操作', align:'center', type:'action', buttons: ['edit','delt']}
		 	]
	  });
  
  //新建
  $('#ValFundPurchase001_btn_add').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建-申购申请"),
      basePath + "valuation/trademanage/fundpurchase/ValFundPurchase002/init",
      "1000px", "420px", {pageModel:PAGE_MODEL_NEW});
  });
  $('#ValFundPurchase001_btn_add1').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建-申购确认(货币型)"),
      basePath + "valuation/trademanage/fundpurchase/ValFundPurchase003/init",
      "1000px", "420px", {pageModel:PAGE_MODEL_NEW});
  });
  $('#ValFundPurchase001_btn_add2').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建-申购确认(净值型)"),
      basePath + "valuation/trademanage/fundpurchase/ValFundPurchase004/init",
      "1000px", "420px", {pageModel:PAGE_MODEL_NEW});
  });
  $('#ValFundPurchase001_btn_add3').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建-申购返款"),
      basePath + "valuation/trademanage/fundpurchase/ValFundPurchase005/init",
      "1000px", "420px", {pageModel:PAGE_MODEL_NEW});
  });
  
});