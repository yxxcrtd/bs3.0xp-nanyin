$(function () {
	var table=$("#dataTable").joyintable({
	    data:[
		  	{"jybh":"JY000000000000000001","cpdm":"PD000000000000000001","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000001","jylx":"拆借","qxpz":"隔夜(O/N)","yjdzr":"2018/04/15","jyfx":"拆入首期","jyr":"2018/04/15","qxr":"2018/04/15","dqr":"2018/04/15","jyrl":"中国银行间交易日历","jxjc":"A/360","jybz":"人民币","jzbz":"人民币","huilv":"0","jyy":"李德富","jyds":"齐勇军","tongdao":"","benjin":"60,000,000.00","yjlixi":"0","yjje":"600,000,000.00","htbh":"HT000000000000000001","bjsytz":"收益特征","ckzh":"零售客户高流动性开放式理财产品","dszjzh":"货币型月20号开放式产品（日结）","ruzhangr":"2018/04/31","tdfei":"500","lilvlx":"固定利率","cjlilvx":"0","jzlilv":"0","fudongbl":"0","crlicha":"100,000","cclicha":"100,000"},
			{"jybh":"JY000000000000000002","cpdm":"PD000000000000000002","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000001","jylx":"拆借","qxpz":"隔夜(O/N)","yjdzr":"2018/04/15","jyfx":"拆入首期","jyr":"2018/04/15","qxr":"2018/04/15","dqr":"2018/04/15","jyrl":"中国银行间交易日历","jxjc":"A/360","jybz":"人民币","jzbz":"人民币","huilv":"0","jyy":"李德富","jyds":"齐勇军","tongdao":"","benjin":"60,000,000.00","yjlixi":"0","yjje":"600,000,000.00","htbh":"HT000000000000000001","bjsytz":"收益特征","ckzh":"零售客户高流动性开放式理财产品","dszjzh":"货币型月20号开放式产品（日结）","ruzhangr":"2018/04/31","tdfei":"500","lilvlx":"固定利率","cjlilvx":"0","jzlilv":"0","fudongbl":"0","crlicha":"100,000","cclicha":"100,000"},
			{"jybh":"JY000000000000000003","cpdm":"PD000000000000000003","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000001","jylx":"拆借","qxpz":"隔夜(O/N)","yjdzr":"2018/04/15","jyfx":"拆入首期","jyr":"2018/04/15","qxr":"2018/04/15","dqr":"2018/04/15","jyrl":"中国银行间交易日历","jxjc":"A/360","jybz":"人民币","jzbz":"人民币","huilv":"0","jyy":"李德富","jyds":"齐勇军","tongdao":"","benjin":"60,000,000.00","yjlixi":"0","yjje":"600,000,000.00","htbh":"HT000000000000000001","bjsytz":"收益特征","ckzh":"零售客户高流动性开放式理财产品","dszjzh":"货币型月20号开放式产品（日结）","ruzhangr":"2018/04/31","tdfei":"500","lilvlx":"固定利率","cjlilvx":"0","jzlilv":"0","fudongbl":"0","crlicha":"100,000","cclicha":"100,000"},
			{"jybh":"JY000000000000000004","cpdm":"PD000000000000000004","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000001","jylx":"拆借","qxpz":"隔夜(O/N)","yjdzr":"2018/04/15","jyfx":"拆入首期","jyr":"2018/04/15","qxr":"2018/04/15","dqr":"2018/04/15","jyrl":"中国银行间交易日历","jxjc":"A/360","jybz":"人民币","jzbz":"人民币","huilv":"0","jyy":"李德富","jyds":"齐勇军","tongdao":"","benjin":"60,000,000.00","yjlixi":"0","yjje":"600,000,000.00","htbh":"HT000000000000000001","bjsytz":"收益特征","ckzh":"零售客户高流动性开放式理财产品","dszjzh":"货币型月20号开放式产品（日结）","ruzhangr":"2018/04/31","tdfei":"500","lilvlx":"固定利率","cjlilvx":"0","jzlilv":"0","fudongbl":"0","crlicha":"100,000","cclicha":"100,000"},
			{"jybh":"JY000000000000000005","cpdm":"PD000000000000000005","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000001","jylx":"拆借","qxpz":"隔夜(O/N)","yjdzr":"2018/04/15","jyfx":"拆入首期","jyr":"2018/04/15","qxr":"2018/04/15","dqr":"2018/04/15","jyrl":"中国银行间交易日历","jxjc":"A/360","jybz":"人民币","jzbz":"人民币","huilv":"0","jyy":"李德富","jyds":"齐勇军","tongdao":"","benjin":"60,000,000.00","yjlixi":"0","yjje":"600,000,000.00","htbh":"HT000000000000000001","bjsytz":"收益特征","ckzh":"零售客户高流动性开放式理财产品","dszjzh":"货币型月20号开放式产品（日结）","ruzhangr":"2018/04/31","tdfei":"500","lilvlx":"固定利率","cjlilvx":"0","jzlilv":"0","fudongbl":"0","crlicha":"100,000","cclicha":"100,000"},
			{"jybh":"JY000000000000000006","cpdm":"PD000000000000000006","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000001","jylx":"拆借","qxpz":"隔夜(O/N)","yjdzr":"2018/04/15","jyfx":"拆入首期","jyr":"2018/04/15","qxr":"2018/04/15","dqr":"2018/04/15","jyrl":"中国银行间交易日历","jxjc":"A/360","jybz":"人民币","jzbz":"人民币","huilv":"0","jyy":"李德富","jyds":"齐勇军","tongdao":"","benjin":"60,000,000.00","yjlixi":"0","yjje":"600,000,000.00","htbh":"HT000000000000000001","bjsytz":"收益特征","ckzh":"零售客户高流动性开放式理财产品","dszjzh":"货币型月20号开放式产品（日结）","ruzhangr":"2018/04/31","tdfei":"500","lilvlx":"固定利率","cjlilvx":"0","jzlilv":"0","fudongbl":"0","crlicha":"100,000","cclicha":"100,000"},
			{"jybh":"JY000000000000000007","cpdm":"PD000000000000000007","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000001","jylx":"拆借","qxpz":"隔夜(O/N)","yjdzr":"2018/04/15","jyfx":"拆入首期","jyr":"2018/04/15","qxr":"2018/04/15","dqr":"2018/04/15","jyrl":"中国银行间交易日历","jxjc":"A/360","jybz":"人民币","jzbz":"人民币","huilv":"0","jyy":"李德富","jyds":"齐勇军","tongdao":"","benjin":"60,000,000.00","yjlixi":"0","yjje":"600,000,000.00","htbh":"HT000000000000000001","bjsytz":"收益特征","ckzh":"零售客户高流动性开放式理财产品","dszjzh":"货币型月20号开放式产品（日结）","ruzhangr":"2018/04/31","tdfei":"500","lilvlx":"固定利率","cjlilvx":"0","jzlilv":"0","fudongbl":"0","crlicha":"100,000","cclicha":"100,000"},
			{"jybh":"JY000000000000000008","cpdm":"PD000000000000000008","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000001","jylx":"拆借","qxpz":"隔夜(O/N)","yjdzr":"2018/04/15","jyfx":"拆入首期","jyr":"2018/04/15","qxr":"2018/04/15","dqr":"2018/04/15","jyrl":"中国银行间交易日历","jxjc":"A/360","jybz":"人民币","jzbz":"人民币","huilv":"0","jyy":"李德富","jyds":"齐勇军","tongdao":"","benjin":"60,000,000.00","yjlixi":"0","yjje":"600,000,000.00","htbh":"HT000000000000000001","bjsytz":"收益特征","ckzh":"零售客户高流动性开放式理财产品","dszjzh":"货币型月20号开放式产品（日结）","ruzhangr":"2018/04/31","tdfei":"500","lilvlx":"固定利率","cjlilvx":"0","jzlilv":"0","fudongbl":"0","crlicha":"100,000","cclicha":"100,000"},
			{"jybh":"JY000000000000000009","cpdm":"PD000000000000000009","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000001","jylx":"拆借","qxpz":"隔夜(O/N)","yjdzr":"2018/04/15","jyfx":"拆入首期","jyr":"2018/04/15","qxr":"2018/04/15","dqr":"2018/04/15","jyrl":"中国银行间交易日历","jxjc":"A/360","jybz":"人民币","jzbz":"人民币","huilv":"0","jyy":"李德富","jyds":"齐勇军","tongdao":"","benjin":"60,000,000.00","yjlixi":"0","yjje":"600,000,000.00","htbh":"HT000000000000000001","bjsytz":"收益特征","ckzh":"零售客户高流动性开放式理财产品","dszjzh":"货币型月20号开放式产品（日结）","ruzhangr":"2018/04/31","tdfei":"500","lilvlx":"固定利率","cjlilvx":"0","jzlilv":"0","fudongbl":"0","crlicha":"100,000","cclicha":"100,000"},
			{"jybh":"JY000000000000000010","cpdm":"PD000000000000000010","cpmc":"收益型开放式周期型171030产品","ztdm":"ZT0000000001","jylx":"拆借","qxpz":"隔夜(O/N)","yjdzr":"2018/04/15","jyfx":"拆入首期","jyr":"2018/04/15","qxr":"2018/04/15","dqr":"2018/04/15","jyrl":"中国银行间交易日历","jxjc":"A/360","jybz":"人民币","jzbz":"人民币","huilv":"0","jyy":"李德富","jyds":"齐勇军","tongdao":"","benjin":"60,000,000.00","yjlixi":"0","yjje":"600,000,000.00","htbh":"HT000000000000000001","bjsytz":"收益特征","ckzh":"零售客户高流动性开放式理财产品","dszjzh":"货币型月20号开放式产品（日结）","ruzhangr":"2018/04/31","tdfei":"500","lilvlx":"固定利率","cjlilvx":"0","jzlilv":"0","fudongbl":"0","crlicha":"100,000","cclicha":"100,000"},
		 ],
		//height:781,
		searchOnInit: true,
		cols: [
			  {field:'jybh',title: '交易编号',width:160,fixed:true,sort: true},
		 	  {field:'cpdm', title: '产品代码',width:165,sort: true},
		 	  {field:'cpmc',title: '产品名称',width:240,sort: true},
			  {field:'ztdm',title: '账套代码',width:110,sort: true},
			  {field:'jylx',title: '交易类型',width:110,sort: true},
			  {field:'qxpz',title: '期限品种',width:90,sort: true},
			  {field:'yjdzr',title: '预计到账日',width:90,sort: true},
			  {field:'jyfx',title: '交易方向',width:90,sort: true},
			  {field:'jyr',title: '交易日',width:90,sort: true},
			  {field:'qxr',title: '起息日',width:90,sort: true},
			  {field:'dqr',title: '到期日',width:90,sort: true},
			  {field:'jyrl',title: '交易日历',width:160,sort: true},
			  {field:'jxjc',title: '计息基础',width:90,sort: true},
			  {field:'jybz',title: '交易币种',width:110,sort: true},
			  {field:'jzbz',title: '记账币种',width:110,sort: true},
			  {field:'huilv',title: '汇率',width:80,sort: true},
			  {field:'jyy',title: '交易员',width:75,sort: true},
			  {field:'jyds',title: '交易对手',width:150,sort: true},
			  {field:'tongdao',title: '通道',width:120,sort: true},
			  {field:'benjin',title: '本金金额(元)',width:160,sort: true},
			  {field:'yjlixi',title: '应计利息',width:120,sort: true},
			  {field:'yjje',title: '交易金额(元)',width:200,sort: true},
			  {field:'htbh',title: '合同编号',width:200,sort: true},
			  {field:'bjsytz',title: '本金收益特征',width:120,sort: true},
			  {field:'ckzh',title: '出款账户',width:200,sort: true},
			  {field:'dszjzh',title: '对手方资金账户',width:200,sort: true},
			  {field:'ruzhangr',title: '入账日',width:90,sort: true},
			  {field:'tdfei',title: '通道费(元)',width:120,sort: true},
			  {field:'lilvlx',title: '利率类型',width:90,sort: true},
			  {field:'cjlilv',title: '拆借利率(%)',width:75,sort: true},
			  {field:'jzlilv',title: '基准利率(%)',width:75,sort: true},
			  {field:'fudongbl',title: '浮动比例',width:75,sort: true},
			  {field:'crlicha',title: '拆入利差',width:120,sort: true},
			  {field:'cclicha',title: '拆出利差',width:120,sort: true},
		 	  //{fixed:'right',width:'90', title: '操作', align:'center', type:'action', buttons: ['edit','delt']}
		 	]
	  });
  
  //新建
  $('#ValInterbankTransaction001_btn_add').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建拆借交易-首期"),
      basePath + "valuation/trademanage/interbanktransaction/ValInterbankTransaction002/init",
      "1000px", "560px", {pageModel:PAGE_MODEL_NEW});
  });
  $('#ValInterbankTransaction001_btn_add1').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建拆借交易-到期"),
      basePath + "valuation/trademanage/interbanktransaction/ValInterbankTransaction003/init",
      "1000px", "610px", {pageModel:PAGE_MODEL_NEW});
  });
  $('#ValInterbankTransaction001_btn_add2').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建拆借交易-转入"),
      basePath + "valuation/trademanage/interbanktransaction/ValInterbankTransaction004/init",
      "1000px", "560px", {pageModel:PAGE_MODEL_NEW});
  });
  $('#ValInterbankTransaction001_btn_add3').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建拆借交易-转出"),
      basePath + "valuation/trademanage/interbanktransaction/ValInterbankTransaction005/init",
      "1000px", "610px", {pageModel:PAGE_MODEL_NEW});
  });
  

});