(function() {
	/**
	 * 资产信息
	 */
	$("#assetInfo_dataTable").joyintable({
		ajax : {
			url : '/invest/product/valuation/FinProductValuation002/selectAssetInfo',
			type : 'POST',
			data : {
				pvalueid : window.parent.param.pvalueid
			}
		},
		searchOnInit : true,
		checkbox : false,
		loadonce : true,
		height : 290,
		rowno : true,
		page : false,
		cols : [ {
			field : 'firstTypeStr',
			title : 'FinProductValuationAssetInfo.table.firstType',
			width : 120
		}, {
			field : 'assetCode',
			title : 'FinProductValuationAssetInfo.table.assetCode',
			width : 150
		}, {
			field : 'assetName',
			title : 'FinProductValuationAssetInfo.table.assetName',
			width : 150
		}, {
			field : 'invtGoalsStr',
			title : 'FinProductValuationAssetInfo.table.invtGoals',
			width : 150
		}, {
			field : 'assetMdate',
			title : 'FinProductValuationAssetInfo.table.assetMdate',
			type : 'date'
		}, {
			field : 'faceAmt',
			title : 'FinProductValuationAssetInfo.table.faceAmt',
			type : 'number',
			width : 120
		}, {
			field : 'ccyStr',
			title : 'FinProductValuationAssetInfo.table.ccy',
			width : 120
		}, {
			field : 'cprice',
			title : 'FinProductValuationAssetInfo.table.cprice',
			type : 'number',
			digit : 4
		}, {
			field : 'assetValue',
			title : 'FinProductValuationAssetInfo.table.assetValue',
			type : 'number',
			width : 160
		}, {
			field : 'duration',
			title : 'FinProductValuationAssetInfo.table.duration',
			type : 'number'
		}, {
			field : 'channel',
			title : 'invest.channelId',
			width : 120
		}, {
			field : 'mgrAcct',
			title : 'FinProductValuationAssetInfo.table.mgrAcct',
			width : 130,
			visible : false
		}, {
			field : 'bondAcct',
			title : 'FinProductValuationAssetInfo.table.bondAcct',
			width : 150
		}, {
			field : 'invtYield',
			title : 'FinProductValuationAssetInfo.table.invtYield',
			type : 'number',
			digit : 4
		}, {
			field : 'exchange',
			title : 'FinProductValuationAssetInfo.table.exchange',
			type : 'number',
			digit : 4
		} ]
	});

}());