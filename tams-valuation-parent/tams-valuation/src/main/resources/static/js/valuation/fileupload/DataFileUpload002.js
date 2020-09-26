
/**
 * excel导入共通处理js
 */

//定义表格
var table;

var FileUpload001 = function() {
    //上传文件信息
    var FileUpload001_uploadFileInfo = [];
    return {
        /**
         * 文件上传后记录文件信息
         */
        onUploaded : function(res) {    
            if(res && res.length > 0){
            	if($('#fileType').val() == '' ){
            		layer.msg("文档类型不能为空。");
            		return ;
            	}
            	
            	var object = {};
            	object.fileNameOnly = res[0].srcName;
            	object.fileType = $('#fileType').val();
            	Joyin.ajax(basePath + 'valuation/fileupload/DataFileUpload002/check', object, function(result) {
        		    Joyin.confirm("是否确认上传？", function() {
        		    	// 上传文件
        		    	Joyin.ajax('uploadFile', {
                        	fileName1: res[0].fileName,
                        	fileNameOnly:res[0].srcName,
                        	fileName :$('#fileName').val(),
                        	fileType: $('#fileType').val()
                        }, function(result) {
                        	//EXCELIMP_E_0            
                        	window.location.reload();
                        });      
        		    });
        		});                  
            }
        }      
    };
}();

$(function() {
	    // 重置
	    $("#FileUpload001_btn_reset").on('click', function(){
	        document.location.reload();
	    });
    });
