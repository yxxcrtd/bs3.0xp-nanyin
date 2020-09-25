(function() {
	var getSearchParams = function() {
		var object = {};
		object.prdIssuePlanStrategyList = ISSUE_PLAN_STRATEGY;
		return object;
	};
	var table=$("#prdPlanInfoTable").joyintable({
		ajax: {
			url: JoyinUtil.getRealPath('invest/product/publish/ProductPublishCounting003/getPublishCountingList'),
			type: 'POST',
			data: function() {
				return getSearchParams();
			}
		},
		checkbox: false,
		page: false,
		loadonce: true,
		cols:[
			{field:'assetType',title: 'ProductPublishCounting002.order', width:200,sortable:false, visible:false}
			,{field:'prdPlanSerisId',title: 'ProductPublishCounting002.prdPlanSerisName', width:250,sortable:false}
			,{field:'strategyId',title: 'ProductPublishCounting002.tacticConfigCode', width:200,sortable:false}
			,{field:'assetTypeStr',title: 'ProductPublishCounting002.assetType', width:200,sortable:false}
			,{field:'configScale1',title: 'ProductPublishCounting002.tacticConfigAmt1', width:150,sortable:false,type: 'number'}
			,{field:'configScale',title: 'ProductPublishCounting002.tacticConfigAmt', width:200,sortable:false,type: 'number'}
		]
	});

	table.load();
}());
