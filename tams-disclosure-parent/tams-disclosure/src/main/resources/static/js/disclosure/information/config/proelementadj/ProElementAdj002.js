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
		 Joyin.ajaxJson(basePath + 'disclosure/information/config/proelementadj/ProElementAdj002/check',
                 object, function(result) {
                     Joyin.confirm(result.data, function() {
                         if (operateType === 'save') {
                             WorkFlow.doSaveList(basePath + 'disclosure/information/config/proelementadj/ProElementAdj002/save',
                                     object, "ProElementAdj002_btn_save");
                         } else {
                             WorkFlow.doSubmitList(basePath  + 'disclosure/information/config/proelementadj/ProElementAdj002/save',
                                     object, "ProElementAdj002_btn_submit");
                         }
                     });
         });
	}


	/**
	 * 输入有效性验证
	 */
	var checkInput = function(obj) {
		// if (obj.customerId == null || $.trim(obj.customerId) == "") {
		// 		Joyin.error("交易商名称不可为空!");
		// 		return false;
		// }
		// if (obj.tradeMarket == null || $.trim(obj.tradeMarket) == "") {
		// 		Joyin.error("交易场所不可为空!");
		// 		return false;
		// }
		// if (obj.dealerNo == null || $.trim(obj.dealerNo) == "") {
		// 		Joyin.error("交易商代码不可为空!");
		// 		return false;
		// }
		return true;
	}

	/**
	 * 页面初始化
	 */
	var init = function() {
		if (pageModel == PAGE_MODEL_EDIT){
			$('#prodRegistNo').prop("disabled",true);
			$('#prodId').prop("disabled",true);
		}
	};
	/**
	 * 画面加载完成
	 */
	$(function() {
		init();
		// 绑定按钮事件
		$('#ProElementAdj002_btn_save').click(function() {
			saveOrSubmit('save');
		});


		// 绑定按钮事件
		$('#ProElementAdj002_btn_submit').click(function() {
			saveOrSubmit('submit');
		});
	});
}());