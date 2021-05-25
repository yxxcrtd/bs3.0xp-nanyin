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
	
	var submit = function(){
		//提交前判断文件大小 10M
		var filesize = $('#filesize').val();
		if(filesize>10485760){
			Joyin.error(JoyinUtil.getMessage("文件超过10M,请重新选择上传文件"));  
            return;
		}else{
		// 向后台传参
		var params = getSearchParams();
		Joyin.ajax('querySameCount',params,function(){
        	params.operateType = "01";
        	Joyin.ajax('uploadAndSubmit',params, function(){
            	Joyin.closeTopWindow();
    		});
		});}
	}
	$("#uploadReport").css('width','200px');
	$("#uploadReport").css('height','60px');
	/**
     * 按钮点击事件
     */
    $(function() {
    	// 绑定提交按钮事件
        $('#ReportConfirmManage004_btn_submit').click(function() {
        	submit();
        });
    });

    // 设置披露截止日设置默认值-平台日 swwan 20210514 start
	function init() {
		$("#revealEndDate").val(platDate);
	}
	init();
	// 设置披露截止日设置默认值-平台日 swwan 20210514 end
});
//
var FileCommon = {
		onUploaded : function(res) {
			
			
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
};