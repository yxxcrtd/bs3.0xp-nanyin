$(function () {
  var table=$("#dataTable").joyintable({
    data:[
	  	{"jybh":"JY000000000000000001","cpdm":"PD000000000000000001","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000001","jylx":"存款类资产","zcdm":"CK201800204","jyfx":"买入","jybz":"人民币","jyr":"2018/04/15","jgr":"2018/04/15 - 2018/04/30","jzbz":"人民币","huilv":"0","jyy":"李德富","jyds":"齐勇军","tongdao":"","benjin":"60,000,000.00","yjlixi":"0","yjje":"600,000,000.00","ckzh":"零售客户高流动性开放式理财产品","dszjzh":"货币型月20号开放式产品（日结）","ruzhangr":"2018/04/31","tdfei":"500"},
		{"jybh":"JY000000000000000002","cpdm":"PD000000000000000002","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000001","jylx":"存款类资产","zcdm":"CK201800204","jyfx":"买入","jybz":"人民币","jyr":"2018/04/15","jgr":"2018/04/15 - 2018/04/30","jzbz":"人民币","huilv":"0","jyy":"李德富","jyds":"齐勇军","tongdao":"","benjin":"60,000,000.00","yjlixi":"0","yjje":"600,000,000.00","ckzh":"零售客户高流动性开放式理财产品","dszjzh":"货币型月20号开放式产品（日结）","ruzhangr":"2018/04/31","tdfei":"500"},
	  	{"jybh":"JY000000000000000003","cpdm":"PD000000000000000003","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000001","jylx":"存款类资产","zcdm":"CK201800204","jyfx":"买入","jybz":"人民币","jyr":"2018/04/15","jgr":"2018/04/15 - 2018/04/30","jzbz":"人民币","huilv":"0","jyy":"李德富","jyds":"齐勇军","tongdao":"","benjin":"60,000,000.00","yjlixi":"0","yjje":"600,000,000.00","ckzh":"零售客户高流动性开放式理财产品","dszjzh":"货币型月20号开放式产品（日结）","ruzhangr":"2018/04/31","tdfei":"500"},
	  	{"jybh":"JY000000000000000004","cpdm":"PD000000000000000004","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000001","jylx":"存款类资产","zcdm":"CK201800204","jyfx":"买入","jybz":"人民币","jyr":"2018/04/15","jgr":"2018/04/15 - 2018/04/30","jzbz":"人民币","huilv":"0","jyy":"李德富","jyds":"齐勇军","tongdao":"","benjin":"60,000,000.00","yjlixi":"0","yjje":"600,000,000.00","ckzh":"零售客户高流动性开放式理财产品","dszjzh":"货币型月20号开放式产品（日结）","ruzhangr":"2018/04/31","tdfei":"500"},
	  	{"jybh":"JY000000000000000005","cpdm":"PD000000000000000005","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000001","jylx":"存款类资产","zcdm":"CK201800204","jyfx":"买入","jybz":"人民币","jyr":"2018/04/15","jgr":"2018/04/15 - 2018/04/30","jzbz":"人民币","huilv":"0","jyy":"李德富","jyds":"齐勇军","tongdao":"","benjin":"60,000,000.00","yjlixi":"0","yjje":"600,000,000.00","ckzh":"零售客户高流动性开放式理财产品","dszjzh":"货币型月20号开放式产品（日结）","ruzhangr":"2018/04/31","tdfei":"500"},
	  	{"jybh":"JY000000000000000006","cpdm":"PD000000000000000006","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000001","jylx":"存款类资产","zcdm":"CK201800204","jyfx":"买入","jybz":"人民币","jyr":"2018/04/15","jgr":"2018/04/15 - 2018/04/30","jzbz":"人民币","huilv":"0","jyy":"李德富","jyds":"齐勇军","tongdao":"","benjin":"60,000,000.00","yjlixi":"0","yjje":"600,000,000.00","ckzh":"零售客户高流动性开放式理财产品","dszjzh":"货币型月20号开放式产品（日结）","ruzhangr":"2018/04/31","tdfei":"500"},
		{"jybh":"JY000000000000000007","cpdm":"PD000000000000000007","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000001","jylx":"存款类资产","zcdm":"CK201800204","jyfx":"买入","jybz":"人民币","jyr":"2018/04/15","jgr":"2018/04/15 - 2018/04/30","jzbz":"人民币","huilv":"0","jyy":"李德富","jyds":"齐勇军","tongdao":"","benjin":"60,000,000.00","yjlixi":"0","yjje":"600,000,000.00","ckzh":"零售客户高流动性开放式理财产品","dszjzh":"货币型月20号开放式产品（日结）","ruzhangr":"2018/04/31","tdfei":"500"},
	  	{"jybh":"JY000000000000000008","cpdm":"PD000000000000000008","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000001","jylx":"存款类资产","zcdm":"CK201800204","jyfx":"买入","jybz":"人民币","jyr":"2018/04/15","jgr":"2018/04/15 - 2018/04/30","jzbz":"人民币","huilv":"0","jyy":"李德富","jyds":"齐勇军","tongdao":"","benjin":"60,000,000.00","yjlixi":"0","yjje":"600,000,000.00","ckzh":"零售客户高流动性开放式理财产品","dszjzh":"货币型月20号开放式产品（日结）","ruzhangr":"2018/04/31","tdfei":"500"},
	  	{"jybh":"JY000000000000000009","cpdm":"PD000000000000000009","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000001","jylx":"存款类资产","zcdm":"CK201800204","jyfx":"买入","jybz":"人民币","jyr":"2018/04/15","jgr":"2018/04/15 - 2018/04/30","jzbz":"人民币","huilv":"0","jyy":"李德富","jyds":"齐勇军","tongdao":"","benjin":"60,000,000.00","yjlixi":"0","yjje":"600,000,000.00","ckzh":"零售客户高流动性开放式理财产品","dszjzh":"货币型月20号开放式产品（日结）","ruzhangr":"2018/04/31","tdfei":"500"},
	  	{"jybh":"JY000000000000000010","cpdm":"PD000000000000000010","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000001","jylx":"存款类资产","zcdm":"CK201800204","jyfx":"买入","jybz":"人民币","jyr":"2018/04/15","jgr":"2018/04/15 - 2018/04/30","jzbz":"人民币","huilv":"0","jyy":"李德富","jyds":"齐勇军","tongdao":"","benjin":"60,000,000.00","yjlixi":"0","yjje":"600,000,000.00","ckzh":"零售客户高流动性开放式理财产品","dszjzh":"货币型月20号开放式产品（日结）","ruzhangr":"2018/04/31","tdfei":"500"},
	  	{"jybh":"JY000000000000000011","cpdm":"PD000000000000000011","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000001","jylx":"存款类资产","zcdm":"CK201800204","jyfx":"买入","jybz":"人民币","jyr":"2018/04/15","jgr":"2018/04/15 - 2018/04/30","jzbz":"人民币","huilv":"0","jyy":"李德富","jyds":"齐勇军","tongdao":"","benjin":"60,000,000.00","yjlixi":"0","yjje":"600,000,000.00","ckzh":"零售客户高流动性开放式理财产品","dszjzh":"货币型月20号开放式产品（日结）","ruzhangr":"2018/04/31","tdfei":"500"},
		{"jybh":"JY000000000000000012","cpdm":"PD000000000000000012","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000001","jylx":"存款类资产","zcdm":"CK201800204","jyfx":"买入","jybz":"人民币","jyr":"2018/04/15","jgr":"2018/04/15 - 2018/04/30","jzbz":"人民币","huilv":"0","jyy":"李德富","jyds":"齐勇军","tongdao":"","benjin":"60,000,000.00","yjlixi":"0","yjje":"600,000,000.00","ckzh":"零售客户高流动性开放式理财产品","dszjzh":"货币型月20号开放式产品（日结）","ruzhangr":"2018/04/31","tdfei":"500"},
	  	{"jybh":"JY000000000000000013","cpdm":"PD000000000000000013","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000001","jylx":"存款类资产","zcdm":"CK201800204","jyfx":"买入","jybz":"人民币","jyr":"2018/04/15","jgr":"2018/04/15 - 2018/04/30","jzbz":"人民币","huilv":"0","jyy":"李德富","jyds":"齐勇军","tongdao":"","benjin":"60,000,000.00","yjlixi":"0","yjje":"600,000,000.00","ckzh":"零售客户高流动性开放式理财产品","dszjzh":"货币型月20号开放式产品（日结）","ruzhangr":"2018/04/31","tdfei":"500"},
	  	{"jybh":"JY000000000000000014","cpdm":"PD000000000000000014","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000001","jylx":"存款类资产","zcdm":"CK201800204","jyfx":"买入","jybz":"人民币","jyr":"2018/04/15","jgr":"2018/04/15 - 2018/04/30","jzbz":"人民币","huilv":"0","jyy":"李德富","jyds":"齐勇军","tongdao":"","benjin":"60,000,000.00","yjlixi":"0","yjje":"600,000,000.00","ckzh":"零售客户高流动性开放式理财产品","dszjzh":"货币型月20号开放式产品（日结）","ruzhangr":"2018/04/31","tdfei":"500"},
	  	{"jybh":"JY000000000000000015","cpdm":"PD000000000000000015","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000001","jylx":"存款类资产","zcdm":"CK201800204","jyfx":"买入","jybz":"人民币","jyr":"2018/04/15","jgr":"2018/04/15 - 2018/04/30","jzbz":"人民币","huilv":"0","jyy":"李德富","jyds":"齐勇军","tongdao":"","benjin":"60,000,000.00","yjlixi":"0","yjje":"600,000,000.00","ckzh":"零售客户高流动性开放式理财产品","dszjzh":"货币型月20号开放式产品（日结）","ruzhangr":"2018/04/31","tdfei":"500"},
	 ],
	//height:781,
	searchOnInit: true,
	cols: [
		  {field:'jybh',title: '交易编号',width:160,fixed:true,sort: true},
	 	  {field:'cpdm', title: '产品代码',width:165,sort: true},
	 	  {field:'cpmc',title: '产品名称',width:240,sort: true},
		  {field:'ztdm',title: '账套代码',width:110,sort: true},
		  {field:'jylx',title: '交易类型',width:110,sort: true},
		  {field:'zcdm',title: '资产代码',width:165,sort: true},
		  {field:'jyfx',title: '交易方向',width:90,sort: true},
		  {field:'jybz',title: '交易币种',width:110,sort: true},
		  {field:'jyr',title: '交易日',width:90,sort: true},
		  {field:'jgr',title: '交割日',width:160,sort: true},
		  {field:'jzbz',title: '记账币种',width:110,sort: true},
		  {field:'huilv',title: '汇率',width:80,sort: true},
		  {field:'jyy',title: '交易员',width:75,sort: true},
		  {field:'jyds',title: '交易对手',width:150,sort: true},
		  {field:'tongdao',title: '通道',width:120,sort: true},
		  {field:'benjin',title: '本金金额(元)',width:160,sort: true},
		  {field:'yjlixi',title: '应计利息总额(元)',width:120,sort: true},
		  {field:'yjje',title: '交易金额(元)',width:200,sort: true},
		  {field:'ckzh',title: '出款账户',width:200,sort: true},
		  {field:'dszjzh',title: '对手方资金账户',width:200,sort: true},
		  {field:'ruzhangr',title: '入账日',width:90,sort: true},
		  {field:'tdfei',title: '通道费(元)',width:120,sort: true},
	 	  //{fixed:'right',width:'90', title: '操作', align:'center', type:'action', buttons: ['edit','delt']}
	 	]
  });
  
  //新建
  $('#ValDepositTrede001_btn_add').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建存款类资产交易-买入"),
      basePath + "valuation/trademanage/deposittrede/ValDepositTrede002/init",
      "1000px", "450px", {pageModel:PAGE_MODEL_NEW});
  });
  $('#ValDepositTrede001_btn_add1').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建存款类资产交易-到期卖出"),
      basePath + "valuation/trademanage/deposittrede/ValDepositTrede003/init",
      "1000px", "540px", {pageModel:PAGE_MODEL_NEW});
  });
  $('#ValDepositTrede001_btn_add2').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建存款类资产交易-收息"),
      basePath + "valuation/trademanage/deposittrede/ValDepositTrede004/init",
      "1000px", "450px", {pageModel:PAGE_MODEL_NEW});
  });
  $('#ValDepositTrede001_btn_add3').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建存款类资产交易-还本"),
      basePath + "valuation/trademanage/deposittrede/ValDepositTrede005/init",
      "1000px", "540px", {pageModel:PAGE_MODEL_NEW});
  });
  $('#ValDepositTrede001_btn_add4').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建存款类资产交易-转入"),
      basePath + "valuation/trademanage/deposittrede/ValDepositTrede006/init",
      "1000px", "450px", {pageModel:PAGE_MODEL_NEW});
  });
  $('#ValDepositTrede001_btn_add5').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建存款类资产交易-转出"),
      basePath + "valuation/trademanage/deposittrede/ValDepositTrede007/init",
      "1000px", "540px", {pageModel:PAGE_MODEL_NEW});
  });

});