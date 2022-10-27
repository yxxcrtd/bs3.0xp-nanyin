$(function() {

	var gradeChange = function() {
		btnShow();
	};

	/**
	 * 修改/新建
	 */
	var saveOrSubmit = function(operateType) {
		var object = $.serializeToObject('#submitForm');
		object.pageModel = pageModel;
		object.operateType = operateType;
		object.indexDetail = $('#indexDetail').val();
		var indexVal = $('#indexValue').val();
		indexVal = indexVal.replace(' ','&nbsp;');
		object.indexValue = indexVal;

		Joyin.ajaxJson(
					basePath+ 'disclosure/information/config/reportcontentenum/ReportContentEnum003/check',object,
						function(result) {
							Joyin.confirm(
									result.data,
									function() {
										if (operateType === 'save') {
											object.operateType = "save";
											WorkFlow.doSaveList(
													basePath+ 'disclosure/information/config/reportcontentenum/ReportContentEnum003/save',object,
																	"ReportContentEnum003_btn_save");
												} else {// submit
													WorkFlow.doSaveList(
																	basePath+ 'disclosure/information/config/reportcontentenum/ReportContentEnum003/save',object,
																	"ReportContentEnum003_btn_submit");
			}
		});
	});
};

	/**
	 * 根据操作类型：显示按钮
	 */
	function btnShow() {
		var operateType = $('#operateType').val();
		if (operateType === 'edit') {
			$("#ReportContentEnum003_btn_save").show();
			$("#ReportContentEnum003_btn_submit").hide();
		} else {
			$("#ReportContentEnum003_btn_save").hide();
			$("#ReportContentEnum003_btn_submit").show();
		}
		if (operateType == 'view') {
			$('input').attr("readonly", "readonly");
		}
	}
	;
	/**
	 * 获取查询参数
	 */
	var getSearchParams = function() {
		return {
			id : $.trim($('#id').val())
		}
	};

	/**
	 * 规则增加事件
	 * @returns
	 */
	function addrule() {
			Joyin.dialog("产品选择",basePath+"disclosure/information/config/reportcontentenum/ReportContentEnum004/init","80%","80%",
				{},function () {
					var finprodIds = $('#finprodIds').data('finprodIds');
					if(!finprodIds || finprodIds.length ==0){
						return;
					}
					$('#indexDetail').val(finprodIds);
				});
			return;
	}

	$(function() {
		// 规则增加事件
		$('#ReportContentEnum003_btn_add').click(addrule);
		// 绑定按钮事件
		$('#ReportContentEnum003_btn_save').click(function() {
			saveOrSubmit('save');
		});
		$('#ReportContentEnum003_btn_submit').click(function() {
			saveOrSubmit('submit');
		});

		 btnShow(); 
	});
});
