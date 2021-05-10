$(function () {
    
    /**
     * 定义数据表格
     */
	var table=$("#ValManualVoucherTemplete002_table").joyintable({
	    ajax: {
            url: basePath + 'valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete002/searchList',
            type: 'POST',
            data: {tmpCode : $("#tmpCode").val()}
        },
        searchOnInit: true,
        checkbox: false,
		cols: [
		   {field:'tmpCode', title: '模板编号'},
           {field:'tmpName', title: '模板名称'},
		   {field:'subjectNo', title: '科目号'},
           {field:'subjectName', title: '科目名称'},
           {field:'cdFlag', title: '借贷'},
           {field:'ccy', title: '币种'},
           {field:'oHappenAmt', type:"money", title: '金额'},
           {field:'happenNumber', type:"number", title: '数量'}
		],
		loadonce:true
	});
	
});