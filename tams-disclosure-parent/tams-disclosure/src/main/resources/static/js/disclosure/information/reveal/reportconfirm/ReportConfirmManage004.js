var upFile;
var upFileName;

$(function () {
	var getSearchParams = function(){
		// 获得产品下拉框被选择的值
		var options=$("#prodId option:selected");
		return {
			prodId : options.val(),
			prodName : options.text(),
			reportName : $('#reportName').val(),
			reportType : $('#reportType').val(),
			isConfirm : $('#conFirmType').val(),
			revealChannel : $('#revealChannel').val(),
			isReveal : $('#revealType').val(),
			uploadDescribe : $('#uploadDescribe').val(),
			fileName : $('#fileName').val(),
			filePath : $('#filePath').val(),
			reportDateBegin : $('#reportDate').startDate(),
			reportDateEnd: $('#reportDate').endDate(),
			revealEndDate: $('#revealEndDate').val()
		}
	}
	
	var submit = function(operateType){
		//提交前判断文件大小 5M
		// var filesize = $('#filesize').val();
		// debugger;
		// if(filesize>5*1024*1024){
		// 	Joyin.error(JoyinUtil.getMessage("文件超过5M,请重新选择上传文件"));
        //     return;
		// }else{
		// 向后台传参
		var params = getSearchParams();
		Joyin.ajax(basePath + 'disclosure/information/reveal/reportconfirm/ReportConfirmManage004/querySameCount',params,function(){
        	// params.operateType = "01";
        	// Joyin.ajax('uploadAndSubmit',params, function(){
            // 	Joyin.closeTopWindow();
    		// });
			var object = {};
			var options=$("#prodId option:selected");
			object.operateType = operateType;
			object.pageModel = pageModel;
			object.prodid = $('#prodId').val();
			object.prodName = options.text();
			object.reportName = $('#reportName').val();
			object.reportCategory = $('#reportType').val();
			object.isConfirm = $('#conFirmType').val();
			object.revealChannel = $('#revealChannel').val();
			object.isReveal = $('#revealType').val();
			object.detail = $('#uploadDescribe').val();
			object.filename = $('#fileName').val();
			object.filepath = $('#filePath').val();
			object.startDate = $('#reportDate').startDate();
			object.endDate= $('#reportDate').endDate();
			object.revealEndDate= $('#revealEndDate').val();
			object.comment= $('#comment').val();
			if(object.prodid == "" || object.prodid == null){
				Joyin.error(JoyinUtil.getMessage("产品名称不能为空"));
				return;
			}
			if(object.reportName == "" || object.reportName == null){
				Joyin.error(JoyinUtil.getMessage("报表名称不能为空"));
				return;
			}
			if(object.reportCategory == "" || object.reportCategory == null){
				Joyin.error(JoyinUtil.getMessage("报表类别不能为空"));
				return;
			}
			if(object.isConfirm == "" || object.isConfirm == null){
				Joyin.error(JoyinUtil.getMessage("确认方式必选"));
				return;
			}
			if(object.isReveal == "" || object.isReveal == null){
				Joyin.error(JoyinUtil.getMessage("披露方式必选"));
				return;
			}
			if(object.revealChannel == "" || object.revealChannel == null){
				Joyin.error(JoyinUtil.getMessage("披露渠道必选"));
				return;
			}
			if(object.filename == "" || object.filename == null){
				Joyin.error(JoyinUtil.getMessage("报表不能为空"));
				return;
			}
			if(object.startDate == "" || object.startDate == null){
				Joyin.error(JoyinUtil.getMessage("报告期不能为空"));
				return;
			}
			if(object.revealEndDate == "" || object.revealEndDate == null){
				Joyin.error(JoyinUtil.getMessage("披露截止日不能为空"));
				return;
			}

			if(FileCommon2 && FileCommon2.getUploadFileInfo().length >0) {
				if(FileCommon2.getUploadFileInfo().length >10){
					Joyin.error("其他附件超过10个");
					return;
				}
				var fileArray = FileCommon2.getUploadFileInfo();
				if(fileArray && _.isArray(fileArray)){

					$.each(fileArray, function(index,obj){
						var srcName = obj.srcName;
						if(!srcName){
							srcName = obj.fileName;
						}
						debugger;
						var index= srcName.lastIndexOf('.');
						var ext = srcName.substr(index+1).toUpperCase();
						debugger;
						//支持word、pdf、Excel、zip 文件
						if(ext!='DOC' && ext!='DOCX' && ext!='PDF' && ext!='ZIP'){
							checkFlag = false;
							Joyin.error(srcName+"文件格式不是word、pdf或zip,不支持上传!");
							return;
						}
						if(obj.size > 5*1024*1024){
							checkFlag = false;
							Joyin.error(srcName+"文件大于5M,不支持上传");
							return;
						}
					});

				}
				object.uploadFileList2 = FileCommon2.getUploadFileInfo();
			}

			if(operateType === 'save') {
				WorkFlow.doSaveList(basePath + 'disclosure/information/reveal/reportconfirm/ReportConfirmManage004/save', object, "ReportConfirmManage004_btn_save");
			}
			else {
				WorkFlow.doSubmitList(basePath + 'disclosure/information/reveal/reportconfirm/ReportConfirmManage004/save', object, "ReportConfirmManage004_btn_submit");
			}
		});}
	// }
	$("#uploadReport").css('width','200px');
	$("#uploadReport").css('height','100px');


	/**
     * 按钮点击事件
     */
    $(function() {
    	debugger;
    	// 绑定提交按钮事件
        $('#ReportConfirmManage004_btn_submit').click(function() {
        	submit('submit');
        });
		$('#ReportConfirmManage004_btn_save').click(function() {
			submit('save');
		});
		var pageModel = $("#pageModel").val();
		var viewFlag = $("#viewFlag").val();
		upFileName = $("#fileName").val();
		if(pageModel==='0' || pageModel === '1'){
			$("#commentDiv").hide();
			$("#upLoadDiv3").hide();
		} else if(pageModel==='2'){
			//查看界面,不显示上传审批意见
			if(viewFlag !='view'){
				$("#commentDiv").show();
				$("#upLoadDiv3").show();
			} else{
				$("#commentDiv").hide();
				$("#upLoadDiv3").hide();
			}
		}
		var seqno =  $("#seqno").val();
		if(seqno){
			Joyin.ajax(basePath + 'disclosure/information/reveal/reportconfirm/ReportConfirmManage004/getCommentList', {seqno:seqno}, function(result) {
				console.log(result);
				var commentList = $("#commentList");
				for(var i=0; i<result.length;i++){
					debugger;
					var obj = result[i];
					var comment = obj.comment;
					var fileList = obj.fileList;
					console.log(result);
					var div = $('<div class="upload layui-upload"></div>');
					var h3 = $('<h3 class="pro-module-title"><span class="title-des"></span>修订后附件<i class="iconfont must" style="display: inline;">*</i></h3>');
					var table = $('<table class="layui-table"></table>');
					var remark = $(['<div class="layui-form-item">'
						,'<label style="float: left">修订意见<i class="iconfont must" style="display: inline;">*</i></label>'
						,'<div class="layui-input-inline input-inline" style="margin:0 10px;width: 80%!important;">'
						,'<textarea id="remark" name="remark" maxlength="500" class="layui-textarea" disabled="" autocomplete="off">'+comment+'</textarea>'
						,'</div></div>'
					].join(''));

					for (var i1=0; i1<fileList.length;i1++){
						var file = fileList[i1];
						var tr = $(['<tr id="upload-'+ file.fileNo +'">'
							,'<td>'+(i1+1)+'</td>'
							,'<td><div class="layui-table-cell">'
							,'<a href="'
							, JoyinUtil.getRealPath('/framework/download/' + file.fileNo)
							,'">'
							,file.fileName
							,'</a></div></td>'
							,'<td>'+file.uploadUser+'</td>'
							,'<td>上传成功</td>'
							,'<td>'+file.createTimeText+'</td>'
							,'</tr>'].join(''));
						table.append(tr);
					}
					div.append(h3).append(table).append(remark);
					commentList.append(div);
				}
			});
		}
    });
});
//

// var FileCommon = {
// 	onUploaded : function(res) {
// 		if(res && res.length > 0){
// 			$('#fileName').val(res[0].srcName);
// 			$('#filePath').val(res[0].fileNo);
// 			$('#filesize').val(res[0].size);
// 			if($('#reportName').val()==""){
// 				$('#reportName').val(res[0].srcName);
// 			}
// 			Joyin.ajax('uploadFile', {
// 				fileNo : res[0].fileNo,
// 				fileName : res[0].fileName,
// 				size :res[0].size
// 			}, function(result) {
// 				upFileName = res[0].srcName;
// 			});
//
// 		}
// 	}
// };

var onUploaded = function(res) {
	if(res && res.length > 0){
		$('#fileName').val(res[0].srcName);
		$('#filePath').val(res[0].fileNo);
		$('#filesize').val(res[0].size);
		if($('#reportName').val()==""){
			$('#reportName').val(res[0].srcName);
		}
		Joyin.ajax('uploadFile', {
			fileNo : res[0].fileNo,
			fileName : res[0].fileName,
			size :res[0].size
		}, function(result) {
			upFileName = res[0].srcName;
		});

	}
}
window.onUploaded = onUploaded;


