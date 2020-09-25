(function() {
	/**
	 * 费用信息
	 */
	$("#feeInfo_dataTable").joyintable({
		ajax : {
			url : '/invest/product/valuation/FinProductValuation002/selectFeeInfo',
			type : 'POST',
			data : {
				pvalueid : window.parent.param.pvalueid
			}
		},
		searchOnInit : true,
		checkbox : false,
		loadonce : true,
		height : 190,
		rowno : true,
		page : false,
		cols : [ {
			field : 'assetName',
			title : 'FinProductValuationFeeInfo.table.feeName',
			width : 200
		}, {
			field : 'basis',
			title : 'FinProductValuationFeeInfo.table.basis',
			width : 120
		}, {
			field : 'assetValue',
			title : 'FinProductValuationFeeInfo.table.feeAmt',
			type : 'number',
			width : 180
		} ]
	});

}());