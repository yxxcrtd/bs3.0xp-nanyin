//预览
var previewbtn = function(){
	var object = $.serializeToObject('#submitForm');
	if($("#booksetId").val()){
		object.booksetId = $("#booksetId").val().join(',');
	}
	Joyin.ajaxJson(basePath + 'valuation/bokdetailidupdate/BokDetailIdUpdate002/getBokdetailId', object, function(result){
		if(result.errorList.length==0){
			$("#bokdetailId").val(result.data);
		}
    });
};

//确认调整
var submitbtn = function(){
	var object = $.serializeToObject('#submitForm');
	if($("#booksetId").val()){
		object.booksetId = $("#booksetId").val().join(',');
	}
	Joyin.ajaxJson(basePath + 'valuation/bokdetailidupdate/BokDetailIdUpdate002/doSumit', object, function(result){
		if(result.errorList.length==0){
			layui.layer.alert(result.data.rstDesc);
		}
    });
	
};
//脚本生成
var scriptGeneration = function(){
	var object = $.serializeToObject('#submitForm');
	if($("#booksetId").val()){
		object.booksetId = $("#booksetId").val().join(',');
	}
	Joyin.ajaxJson(basePath + 'valuation/bokdetailidupdate/BokDetailIdUpdate002/check', object, function(result){
		if (result.errorList.length == 0) {
			Joyin.exportExcel('scriptGeneration', object);
		}
    });
};

/**
 * 画面加载完成
 */
$(function() {
	// 绑定预览事件
    $('#BokDetailIdUpdate002_btn_preview').click(function() {
    	previewbtn();
    });
    
    // 绑定重置
    $('#BokDetailIdUpdate002_btn_reset').click(function() {
    	window.location.reload();
    });
    
    // 绑定确认调整
    $('#BokDetailIdUpdate002_btn_submit').click(function() {
    	submitbtn();
    });
    // 绑定【脚本生成】
    $('#BokDetailIdUpdate002_btn_scriptGeneration').click(function() {
		scriptGeneration();
    });
    
    $("#busiDetailType").change(function() {
    	$('#busiId').val("").trigger("change");
    });
    
    $("#booksetId").change(function() {
    	$('#busiDetailType').val("").trigger("change");
    	$('#busiId').val("").trigger("change");
    });
	
});