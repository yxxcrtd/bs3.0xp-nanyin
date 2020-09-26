$(function () {
    
    /**
     * 定义数据表格
     */
	var table=$("#ValNetPlanQuotation002_table").joyintable({
	    ajax: {
            url: basePath + 'valuation/basedata/marketdata/netplanquotation/ValNetPlanQuotation002/searchList',
            type: 'POST',
            data: {finprodId : $("#finprodId").val(),valueDate:$("#valueDate").val()}
        },
        searchOnInit: true,
        checkbox: false,
		cols: [
		   {field:'finprodId', title: '资产代码'},
           {field:'finprodAbbr', title: '资产名称'},
           {field:'valueSourceFmt',sortField:'valueSource', title: '估值来源'},
           {field:'valueDateFmt', title: '行情日期'},
           {field:'valueAsset', type:"money", title: '资产净值',digit:4,width:200}
		],
		loadonce:true
	});
	
});