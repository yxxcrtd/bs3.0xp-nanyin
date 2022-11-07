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
		}
		Joyin.ajaxJson(basePath + 'disclosure/information/config/accountamount/AccountAmount002/check', object, function (result) {
				if (result) {
					if (operateType === 'save') {
						WorkFlow.doSaveList(basePath + 'disclosure/information/config/accountamount/AccountAmount002/save', object, "AccountAmount002_btn_save");
					} else {
						WorkFlow.doSubmitList(basePath + 'disclosure/information/config/accountamount/AccountAmount002/save', object, "AccountAmount002_btn_submit");
					}
				}

			}
		)
	}


	/**
	 * 页面初始化
	 */
	var init = function() {
		$('#booksetId').change(function () {
			var prodId = $('#booksetId').val();
			var data = $('#booksetId').select2("data")[0].id;
			$('#prodName').val(data);
		});


		if (pageModel == PAGE_MODEL_EDIT){
			$('#booksetId').prop("disabled",true);
			$('#dateStart').prop("disabled",true);
			$('#dateEnd').prop("disabled",true);
			$('#subjectNo').prop("disabled",true);
		}


	};
	/**
	 * 画面加载完成
	 */
	$(function() {
		init();
		// 绑定按钮事件
		$('#AccountAmount002_btn_save').click(function() {
			saveOrSubmit('save');
		});


		// 绑定按钮事件
		$('#AccountAmount002_btn_submit').click(function() {
			saveOrSubmit('submit');
		});
	});
}());