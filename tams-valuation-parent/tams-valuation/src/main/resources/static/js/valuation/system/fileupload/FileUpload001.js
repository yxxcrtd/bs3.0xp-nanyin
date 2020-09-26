
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
                Joyin.ajax('uploadFile', {
                	fileName: res[0].fileName,
                	execDate: $('#executeDate').val()
                }, function(result) {
                	//EXCELIMP_E_0020
                	window.location.reload();
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
