/**
 * 交易商新建
 */
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
		object.operateType = operateType;
		object.pageModel = pageModel;
		if (pageModel == PAGE_MODEL_NEW) {
			object.operateType = 'new';
		} else if (pageModel == PAGE_MODEL_EDIT) {
			object.operateType = 'update';
		}else{
			object.operateType = 'delete';
		}
		 Joyin.ajaxJson(basePath + 'disclosure/information/config/hugeinfopro/HugeInfoPro002/check',
                 object, function(result) {
                     Joyin.confirm(result.data, function() {
                         if (operateType === 'save') {
                             WorkFlow.doSaveList(basePath + 'disclosure/information/config/hugeinfopro/HugeInfoPro002/save',
                                     object, "HugeInfoPro002_btn_save");
                         } else {
                             WorkFlow.doSubmitList(basePath  + 'disclosure/information/config/hugeinfopro/HugeInfoPro002/save',
                                     object, "HugeInfoPro002_btn_submit");
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
		$('#HugeInfoPro002_btn_save').click(function() {
			saveOrSubmit('save');
		});


		// 绑定按钮事件
		$('#HugeInfoPro002_btn_submit').click(function() {
			saveOrSubmit('submit');
		});
	});
}());