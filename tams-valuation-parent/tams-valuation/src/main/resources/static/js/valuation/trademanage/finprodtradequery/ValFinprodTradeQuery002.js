$(function () {
	
	/**
     * 定义数据表格
     */
	var table=$("#dataTable").joyintable({
	    ajax: {
            url: basePath + 'valuation/trademanage/finprodtradequery/ValFinprodTradeQuery002/searchList',
            type: 'POST',
            data: {workflowId : workflowId}
        },
        searchOnInit: true,
        checkbox: false,
        cols: [
               {field:'busiType', title: '业务类型',width:150, sort: true},
			   {field:'prodId', title: '理财产品代码',width:120, sort: true},
		       {field:'finprodType', title: '金融产品类型',width:150},
		       {field:'finprodId', title: '金融产品代码',width:150, sort: true},
		       {field:'ps', title: '交易方向',width:100, sort: true},
		       {field:'tradeDate', title: '交易日',width:120, sort: true},
		       {field:'settleDate', title: '交割日',width:120, sort: true},
		       {field:'vdate', title: '起息日',width:120, sort: true},
	           {field:'mdate', title: '到期日',width:120, sort: true},
	           {field:'cpriceDate', title: '净值日期',width:120, sort: true},
	           {field:'unitCprice', type:"money", title: '单位净价',width:150, sort: true},
	           {field:'unitInt', type:"money", title: '单位利息',width:150, sort: true},
	           {field:'unitFprice', type:"money", title: '单位全价',width:150, sort: true},
	           {field:'parValue', type:"money", title: '单位面值',width:150, sort: true},
	           {field:'cpriceAmt', type:"money", title: '净价总额',width:150, sort: true},
	           {field:'shareAmt', type:"number", title: '交易份额',width:150, sort: true},
	           {field:'prinAmt', type:"money", title: '交易本金',width:150, sort: true},
	           {field:'intAmt', type:"money", title: '利息总额',width:150, sort: true},
	           {field:'fpriceAmt', type:"money", title: '全价总额',width:150, sort: true},
	           {field:'feeAmtPay', type:"money", title: '随交易支付费用',width:150, sort: true},
	           {field:'tradeAmt', type:"money", title: '交易金额',width:150, sort: true},
	           {field:'feeAmtUnpay', type:"money", title: '未随交易支付费用',width:150, sort: true},
	           {field:'feeAmt', type:"money", title: '总交易费用',width:150, sort: true},
			   {field:'ccy', title: '交易币种',width:150},
			   {field:'yield',  align:"right", title: '到期收益率(%)',width:150, sort: true},
	           {field:'exerYield',  align:"right", title: '行权收益率(%)',width:150, sort: true},
	           {field:'invAim', title: '投资目的',width:300},
	           {field:'tradeStatus', title: '交易状态',width:120, sort: true},
	           {field:'pTradeId', title: '原交易编号',width:170, sort: true},
	           {field:'isClean', title: '是否结清',width:100, sort: true},
	           {field:'totleTradeId', title: '汇总交易编号',width:170, sort: true},
	           {field:'rTradeId', title: '反向交易编号',width:170, sort: true},
	           {field:'chlId', title: '通道代码',width:150, sort: true},
	           {field:'counterId', title: '交易对手',width:150, sort: true},
//	           {field:'counterType', title: '交易对手类型',width:150, sort: true},
	           {field:'isOutTrade', title: '是否外部交易',width:150, sort: true, sortField:'isOutTrade'},
	           {field:'tradeMarketFmt', sortField:'tradeMarket', title: '交易场所',width:150, sort: true},
	           {field:'trader', title: '交易员',width:140, sort: true},
	           {field:'secManageAcctId', title: '证券管理户',width:150, sort: true},
	           {field:'tradeId', title: '交易编号',width:200, sort: true},
	           {field:'fTradeId', title: '关联系统交易编号',width:150, sort: true},
	           {field:'booksetId', title: '账套代码',width:150},
	           {field:'booksetDate', title: '录入日',width:120, sort: true},
	           {field:'bookDate', title: '入账日',width:120, sort: true}
           ] ,
		loadonce:true,
		page:false
	});
});