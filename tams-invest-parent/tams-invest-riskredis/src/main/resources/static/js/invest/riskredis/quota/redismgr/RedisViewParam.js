(function(){

	function saveOrSubmit() {
		if(null==$("#value").val()||!$("#value").val()||''==$("#value").val()){
			Joyin.warn(JoyinUtil.getMessage('FW_E_0006',"现值"));
			return ;
		}
		var object = $.serializeToObject('#submitForm', true);
		object.pageModel = pageModel;
		Joyin.confirm("请确认更改参数值吗?", function() {
			Joyin.ajaxJson(basePath + 'invest/riskredis/quota/redismgr/RedisViewParam/setParamValue', object, function(result) {
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
				return;
			});
		})
	}

	/**
	 * 计算新值
	 */
	var calcParamValue = function() {
		var value;
		var oldValue = $("#oldValue").val();
		var supConnector = $("#supConnector").val();
		if(supConnector=="+"){
			value=JoyinMath.doAdd(oldValue,$("#changeValue").val())
		}
		if(supConnector=="-"){
			value=JoyinMath.doMinus(oldValue,$("#changeValue").val(),2)
		}
		if(supConnector=="*"){
			value=JoyinMath.doMul(oldValue,$("#changeValue").val(),2)
		}
		if(supConnector=="/"){
			value=JoyinMath.doDiv(oldValue,$("#changeValue").val(),2)
		}
		$("#value").val(value).trigger('change');
	}

	$(function() {
		/**
		 * 变化值更改
		 */
		$("#changeValue,#supConnector").on("change", function () {
			calcParamValue();
		});

		// 绑定按钮事件
		$('#RedisViewParam_btn_submit').click(function() {
			saveOrSubmit();
		});
	});
}());