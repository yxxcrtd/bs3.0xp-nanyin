$(function () {
	var table=$("#dataTable").joyintable({
	  data:[
		{"jybh":"JY000000000000000001","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","shqrr":"2018/04/15","shfene":"100,000","yshl":"100,000","sshl":"50,000","shuhuify":"1,000","shuhuije":"100,000","yjdzr":"2018/04/16","sjdzr":"2018/04/17","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","lilv":"5.9%"},
		{"jybh":"JY000000000000000002","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","shqrr":"2018/04/15","shfene":"100,000","yshl":"100,000","sshl":"50,000","shuhuify":"1,000","shuhuije":"100,000","yjdzr":"2018/04/16","sjdzr":"2018/04/17","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","lilv":"5.9%"},
		{"jybh":"JY000000000000000003","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","shqrr":"2018/04/15","shfene":"100,000","yshl":"100,000","sshl":"50,000","shuhuify":"1,000","shuhuije":"100,000","yjdzr":"2018/04/16","sjdzr":"2018/04/17","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","lilv":"5.9%"},
		{"jybh":"JY000000000000000004","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","shqrr":"2018/04/15","shfene":"100,000","yshl":"100,000","sshl":"50,000","shuhuify":"1,000","shuhuije":"100,000","yjdzr":"2018/04/16","sjdzr":"2018/04/17","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","lilv":"5.9%"},
		{"jybh":"JY000000000000000005","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","shqrr":"2018/04/15","shfene":"100,000","yshl":"100,000","sshl":"50,000","shuhuify":"1,000","shuhuije":"100,000","yjdzr":"2018/04/16","sjdzr":"2018/04/17","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","lilv":"5.9%"},
		{"jybh":"JY000000000000000006","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","shqrr":"2018/04/15","shfene":"100,000","yshl":"100,000","sshl":"50,000","shuhuify":"1,000","shuhuije":"100,000","yjdzr":"2018/04/16","sjdzr":"2018/04/17","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","lilv":"5.9%"},
		{"jybh":"JY000000000000000007","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","shqrr":"2018/04/15","shfene":"100,000","yshl":"100,000","sshl":"50,000","shuhuify":"1,000","shuhuije":"100,000","yjdzr":"2018/04/16","sjdzr":"2018/04/17","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","lilv":"5.9%"},
		{"jybh":"JY000000000000000008","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","shqrr":"2018/04/15","shfene":"100,000","yshl":"100,000","sshl":"50,000","shuhuify":"1,000","shuhuije":"100,000","yjdzr":"2018/04/16","sjdzr":"2018/04/17","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","lilv":"5.9%"},
		{"jybh":"JY000000000000000009","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","shqrr":"2018/04/15","shfene":"100,000","yshl":"100,000","sshl":"50,000","shuhuify":"1,000","shuhuije":"100,000","yjdzr":"2018/04/16","sjdzr":"2018/04/17","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","lilv":"5.9%"},
		{"jybh":"JY000000000000000010","cpmc":"某某的某某产品名称","ztdm":"ZT0000000000001","jjdm":"JJ000000000000000001","scdm":"SC0000000000001","jjmc":"某某的某某基金名称","shqrr":"2018/04/15","shfene":"100,000","yshl":"100,000","sshl":"50,000","shuhuify":"1,000","shuhuije":"100,000","yjdzr":"2018/04/16","sjdzr":"2018/04/17","ruzhangr":"2018/04/17","jybz":"人民币","jzbz":"人民币","lilv":"5.9%"},
	 ],
	  //height:781,
	  searchOnInit: true,
	  cols: [
		  {field:'jybh',title: '交易编号',width:160,fixed:true,sort: true},
		  {field:'cpmc',title: '产品',width:240,fixed:true,sort: true},
		  {field:'ztdm',title: '账套代码',width:130,sort: true},
		  {field:'jjdm',title: '基金代码',width:160,sort: true},
		  {field:'scdm',title: '市场代码',width:130,sort: true},
		  {field:'jjmc',title: '基金名称',width:240,sort: true},
		  {field:'shqrr',title: '赎回确认日',width:90,sort: true},
		  {field:'shfene',title: '赎回份额',width:120,sort: true},
		  {field:'yshl',title: '应收红利',width:150,sort: true},
		  {field:'sshl',title: '实收红利',width:150,sort: true},
		  {field:'shuhuify',title: '赎回费用',width:150,sort: true},
		  {field:'shuhuije',title: '赎回金额',width:150,sort: true},
		  {field:'yjdzr',title: '预计到账日',width:90,sort: true},
		  {field:'sjdzr',title: '实际到账日',width:90,sort: true},
		  {field:'ruzhangr',title: '入账日',width:90,sort: true},
		  {field:'jybz',title: '交易币种',width:90,sort: true},
		  {field:'jzbz',title: '记账币种',width:90,sort: true},
		  {field:'lilv', title: '利率',width:90,sort: true},
		  //{fixed:'right',width:'90', title: '操作', align:'center', type:'action', buttons: ['edit','delt']}
		]
  });
  
  //新建
  $('#ValFundRedemption001_btn_add').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建-赎回确认(货币型)"),
      basePath + "valuation/trademanage/fundredemption/ValFundRedemption002/init",
      "1000px", "400px", {pageModel:PAGE_MODEL_NEW});
  });
  $('#ValFundRedemption001_btn_add1').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建-赎回确认(净值型)"),
      basePath + "valuation/trademanage/fundredemption/ValFundRedemption003/init",
      "1000px", "400px", {pageModel:PAGE_MODEL_NEW});
  });
  $('#ValFundRedemption001_btn_add2').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建-赎回到账(货币型)"),
      basePath + "valuation/trademanage/fundredemption/ValFundRedemption004/init",
      "1000px", "400px", {pageModel:PAGE_MODEL_NEW});
  });
  $('#ValFundRedemption001_btn_add3').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建-赎回到账(净值型)"),
      basePath + "valuation/trademanage/fundredemption/ValFundRedemption005/init",
      "1000px", "400px", {pageModel:PAGE_MODEL_NEW});
  });

});