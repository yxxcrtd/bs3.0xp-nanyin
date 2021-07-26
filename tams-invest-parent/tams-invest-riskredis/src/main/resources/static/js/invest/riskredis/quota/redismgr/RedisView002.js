$(function() {

	function saveOrSubmit(operateType) {
		var object = $.serializeToObject('#submitForm', true);
		object.operateType = operateType;
		object.pageModel = pageModel;
		if (object.paraCode) {
			object.paraCode = object.paraCode.toString();
		}
		Joyin.ajaxJson(basePath + 'invest/riskredis/quota/redismgr/RskRedisParamConfig002/check', object, function(result) {
			Joyin.confirm(result.data, function() {
				if (operateType === 'save') {
					WorkFlow.doSaveList(basePath + 'invest/riskredis/quota/redismgr/RskRedisParamConfig002/save',
						object, "RskRedisParamConfig002_btn_save");
				} else {
					WorkFlow.doSubmitList(basePath + 'invest/riskredis/quota/redismgr/RskRedisParamConfig002/save',
						object, "RskRedisParamConfig002_btn_submit");
				}
				})
		})
	}

	$(function() {
		// 绑定按钮事件
		$('#RskRedisParamConfig002_btn_save').click(function() {
			saveOrSubmit('save');
		});
		$('#RskRedisParamConfig002_btn_submit').click(function() {
			saveOrSubmit('submit');
		});
	});
});
