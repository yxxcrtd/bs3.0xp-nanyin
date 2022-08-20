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
		object.operationType = operateType;
		Joyin.ajaxJson(
			basePath+ 'disclosure/information/reportpage/ReportPageManage003/check',object,
			function(result) {
				Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil
					.getLabel("SysCommon.submit")), function() {
					if(operateType === 'save'){
						WorkFlow.doSaveList(basePath + 'disclosure/information/reportpage/ReportPageManage003/save', object, "ReportPageManage003_btn_save");
					} else {
						WorkFlow.doSubmitList(basePath + 'disclosure/information/reportpage/ReportPageManage003/save', object, "ReportPageManage003_btn_submit");
					}
				});
			});

	}

	$(function() {
		debugger;
		var workflowType = $("#workflowType").val();
		//禁用界面
		if(workflowType !='01'){

			$("#offRepId").attr("disabled",true);
			//启用下拉框,不可见
			$("#startDiv").attr("style","display:none");
			$("#jinyongbitian").attr("style","display:inline");
		} else {
			$("#jinyongLabel").attr("style","padding-right:20px");
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

		var seqno =  $("#seqno").val();
		if(seqno){
			Joyin.ajax(basePath + 'disclosure/infoCompliance/infoCompliance002/getCommentList', {seqno:seqno}, function(result) {

				var commentList = $("#commentList");
				for(var i=0; i<result.length;i++){
					debugger;
					var obj = result[i];
					var comment = obj.comment;
					var createUser = obj.createUser;

					var div = $('<div class="upload layui-upload"></div>');
					var h3 = $('<h3 class="pro-module-title"><span class="title-des"></span>审批意见<i class="iconfont must" style="display: inline;">*</i></h3>');
					var table = $('<table class="layui-table"></table>');
					var remark = $(['<div class="layui-form-item">'
						,'<label style="float: left">'+createUser+'<i class="iconfont must" style="display: inline;">*</i></label>'
						,'<div class="layui-input-inline input-inline" style="margin:0 10px;width: 80%!important;">'
						,'<textarea id="remark" name="remark" maxlength="500" class="layui-textarea" disabled="" autocomplete="off">'+comment+'</textarea>'
						,'</div></div>'
					].join(''));
					div.append(h3).append(table).append(remark);
					commentList.append(div);
				}
			});
		}
	});
}());