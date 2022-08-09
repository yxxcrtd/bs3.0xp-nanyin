(function() {

	function saveOrSubmit(operateType) {
		var effDate = $('#effDateStr').val();
		if(!effDate){
			Joyin.error("生效日期不能为空!");
			return;
		}
		var object = $.serializeToObject('#submitForm', false);

		if(FileCommon && FileCommon.getUploadFileInfo().length >0) {
			object.uploadFileList = FileCommon.getUploadFileInfo();
		}
		Joyin.ajaxJson(
			basePath+ 'disclosure/information/reportpage/ReportPageManage003/check',object,
			function(result) {
				Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil
					.getLabel("SysCommon.submit")), function() {
					if(operateType === 'save'){
						object.operationType = 'save';
						WorkFlow.doSaveList(basePath + 'disclosure/information/reportpage/ReportPageManage003/save', object, "ReportPageManage003_btn_save");
					} else {
						object.operationType = 'submit';
						WorkFlow.doSubmitList(basePath + 'disclosure/information/reportpage/ReportPageManage003/save', object, "ReportPageManage003_btn_submit");
					}
				});
			});

	}

	$(function() {
		var workflowType = $("#workflowType").val();
		//禁用界面
		if(workflowType !='01'){

			$("#offRepId").attr("disabled",true);
			//启用下拉框,不可见
			$("#startDiv").attr("style","display:none")
		}

		//修建,修改
		if(pageModel==='0' || pageModel === '1'){
			$("#commentDiv").hide();
		} else if(pageModel==='2'){
			$("#commentDiv").show();
		}

		// 绑定按钮事件
		$('#ReportPageManage003_btn_save').click(function() {
			saveOrSubmit('save');
		});

		$('#ReportPageManage003_btn_submit').click(function() {
			saveOrSubmit('submit');
		});
	});
}());