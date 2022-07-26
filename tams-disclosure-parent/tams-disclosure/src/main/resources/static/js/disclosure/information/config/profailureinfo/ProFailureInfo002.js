$(function() {
	/**
	 * 保存或提交
	 * 
	 * @param operateType
	 *            操作类型（save，submit）
	 * @returns
	 */
	function saveOrSubmit(operateType) {
		var object = $.serializeToObject('#submitForm');
		// if(!checkInput(object)){
		// 	return;
		// }
		object.operateType = operateType;
		object.pageModel = pageModel;
		if (pageModel == PAGE_MODEL_NEW) {
			object.operateType = 'new';
		} else if (pageModel == PAGE_MODEL_EDIT) {
			object.operateType = 'update';
		}
		 Joyin.ajaxJson(basePath + 'disclosure/information/config/profailureinfo/ProFailureInfo002/check',
                 object, function(result) {
                     Joyin.confirm(result.data, function() {
                         if (operateType === 'save') {
                             WorkFlow.doSaveList(basePath + 'disclosure/information/config/profailureinfo/ProFailureInfo002/save',
                                     object, "ProFailureInfo002_btn_save");
                         } else {
                             WorkFlow.doSubmitList(basePath  + 'disclosure/information/config/profailureinfo/ProFailureInfo002/save',
                                     object, "ProFailureInfo002_btn_submit");
                         }
                     });
         });
	}

	/**
	 * 页面初始化
	 */
	var init = function() {

	};
	/**
	 * 画面加载完成
	 */
	$(function() {
		init();
		// 绑定按钮事件
		$('#ProFailureInfo002_btn_save').click(function() {
			saveOrSubmit('save');
		});


		// 绑定按钮事件
		$('#ProFailureInfo002_btn_submit').click(function() {
			saveOrSubmit('submit');
		});
	});
}());