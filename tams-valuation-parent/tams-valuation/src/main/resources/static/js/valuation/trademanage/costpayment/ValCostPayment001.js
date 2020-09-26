$(function () {
	var table=$("#dataTable").joyintable({
	    data:[
		  	{"jybh":"JY000000000000000001","fymc":"费用名称","cpdm":"PD000000000000000001","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000001","zcdm":"ZC000000000000000001","scffr":"2018/04/15","bcffjzr":"2018/04/15","bcffjzr":"2018/04/15","jybz":"人民币","jzbz":"人民币","huilv":"0","tongdao":"","llfy":"60,000,000.00","sjfy":"0","ruzhangr":"2018/04/31"},
			{"jybh":"JY000000000000000002","fymc":"费用名称","cpdm":"PD000000000000000002","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000002","zcdm":"ZC000000000000000002","scffr":"2018/04/15","bcffjzr":"2018/04/15","bcffjzr":"2018/04/15","jybz":"人民币","jzbz":"人民币","huilv":"0","tongdao":"","llfy":"60,000,000.00","sjfy":"0","ruzhangr":"2018/04/31"},
			{"jybh":"JY000000000000000003","fymc":"费用名称","cpdm":"PD000000000000000003","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000003","zcdm":"ZC000000000000000003","scffr":"2018/04/15","bcffjzr":"2018/04/15","bcffjzr":"2018/04/15","jybz":"人民币","jzbz":"人民币","huilv":"0","tongdao":"","llfy":"60,000,000.00","sjfy":"0","ruzhangr":"2018/04/31"},
			{"jybh":"JY000000000000000004","fymc":"费用名称","cpdm":"PD000000000000000004","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000004","zcdm":"ZC000000000000000004","scffr":"2018/04/15","bcffjzr":"2018/04/15","bcffjzr":"2018/04/15","jybz":"人民币","jzbz":"人民币","huilv":"0","tongdao":"","llfy":"60,000,000.00","sjfy":"0","ruzhangr":"2018/04/31"},
			{"jybh":"JY000000000000000005","fymc":"费用名称","cpdm":"PD000000000000000005","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000005","zcdm":"ZC000000000000000005","scffr":"2018/04/15","bcffjzr":"2018/04/15","bcffjzr":"2018/04/15","jybz":"人民币","jzbz":"人民币","huilv":"0","tongdao":"","llfy":"60,000,000.00","sjfy":"0","ruzhangr":"2018/04/31"},
			{"jybh":"JY000000000000000006","fymc":"费用名称","cpdm":"PD000000000000000006","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000006","zcdm":"ZC000000000000000006","scffr":"2018/04/15","bcffjzr":"2018/04/15","bcffjzr":"2018/04/15","jybz":"人民币","jzbz":"人民币","huilv":"0","tongdao":"","llfy":"60,000,000.00","sjfy":"0","ruzhangr":"2018/04/31"},
			{"jybh":"JY000000000000000007","fymc":"费用名称","cpdm":"PD000000000000000007","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000007","zcdm":"ZC000000000000000007","scffr":"2018/04/15","bcffjzr":"2018/04/15","bcffjzr":"2018/04/15","jybz":"人民币","jzbz":"人民币","huilv":"0","tongdao":"","llfy":"60,000,000.00","sjfy":"0","ruzhangr":"2018/04/31"},
			{"jybh":"JY000000000000000008","fymc":"费用名称","cpdm":"PD000000000000000008","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000008","zcdm":"ZC000000000000000008","scffr":"2018/04/15","bcffjzr":"2018/04/15","bcffjzr":"2018/04/15","jybz":"人民币","jzbz":"人民币","huilv":"0","tongdao":"","llfy":"60,000,000.00","sjfy":"0","ruzhangr":"2018/04/31"},
			{"jybh":"JY000000000000000009","fymc":"费用名称","cpdm":"PD000000000000000009","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000009","zcdm":"ZC000000000000000009","scffr":"2018/04/15","bcffjzr":"2018/04/15","bcffjzr":"2018/04/15","jybz":"人民币","jzbz":"人民币","huilv":"0","tongdao":"","llfy":"60,000,000.00","sjfy":"0","ruzhangr":"2018/04/31"},
			{"jybh":"JY000000000000000010","fymc":"费用名称","cpdm":"PD000000000000000010","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000010","zcdm":"ZC000000000000000010","scffr":"2018/04/15","bcffjzr":"2018/04/15","bcffjzr":"2018/04/15","jybz":"人民币","jzbz":"人民币","huilv":"0","tongdao":"","llfy":"60,000,000.00","sjfy":"0","ruzhangr":"2018/04/31"},
		 ],
		//height:781,
		searchOnInit: true,
		cols: [
			  {field:'jybh',title: '交易编号',width:160,fixed:true,sort: true},
			  {field:'fymc',title: '费用名称',width:120,fixed:true,sort: true},
		 	  {field:'cpdm', title: '产品代码',width:165,sort: true},
		 	  {field:'cpmc',title: '产品名称',width:240,sort: true},
			  {field:'ztdm',title: '账套代码',width:110,sort: true},
			  {field:'zcdm', title: '资产代码',width:165,sort: true},
			  {field:'scffr',title: '上次付费日',width:90,sort: true},
			  {field:'bcffjzr',title: '本次付费截止日',width:90,sort: true},
			  {field:'zfr',title: '支付日',width:90,sort: true},
			  {field:'jybz',title: '交易币种',width:110,sort: true},
			  {field:'jzbz',title: '记账币种',width:110,sort: true},
			  {field:'huilv',title: '汇率',width:80,sort: true},
			  {field:'tongdao',title: '通道',width:120,sort: true},
			  {field:'llfy',title: '理论费用',width:160,sort: true},
			  {field:'sjfy',title: '实付费用',width:120,sort: true},
			  {field:'ruzhangr',title: '入账日',width:90,sort: true},
		 	  //{fixed:'right',width:'90', title: '操作', align:'center', type:'action', buttons: ['edit','delt']}
		 	]
	  });
  
  //新建
  $('#ValCostPayment001_btn_add').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建费用支付"),
      basePath + "valuation/trademanage/costpayment/ValCostPayment002/init",
      "1000px", "360px", {pageModel:PAGE_MODEL_NEW});
  });
  
  

});