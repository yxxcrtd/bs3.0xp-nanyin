$(function () {
    
    /**
     * 定义数据表格
     */
	var table=$("#ValManualVoucher002_table").joyintable({
	    ajax: {
            url: basePath + 'valuation/accounadjustmanage/manualvoucher/ValManualVoucher002/searchList',
            type: 'POST',
            data: {tradeId : $("#tradeId").val()}
        },
        searchOnInit: true,
        checkbox: false,
		cols: [
		   {field:'subjectNo', title: '科目号'},
           {field:'subjectName', title: '科目名称'},
           {field:'cdFlag', title: '借贷'},
           {field:'ccy', title: '原币币种'},
           {field:'happenAmt', type:"money", title: '原币金额'},
           {field:'bCcy', title: '记账币种'},
           {field:'bHappenAmt', type:"money", title: '本币金额'},
           {field:'happenNumber', type:"number", title: '数量'}
		],
		loadonce:true
	});
	
});