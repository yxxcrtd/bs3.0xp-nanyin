    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        return {
            executeDate: $('#executeDate').val()
        }
    };
    var exportFileDownload = function() {
        Joyin.ajaxJson('check', getPageParams(), function(result) {
            Joyin.exportExcel('export', getPageParams());
        });
    };
/**
 * excel导入共通处理js
 */
$(function() {
	    // 重置
	    $("#InterfFileDownload001_btn_download").on('click', function(){
	        var date = $('#executeDate').val();
	        if(date==null||date==''){
	            layui.layer.alert("请选择附件下载日期");
	            return;
	        }else{
	            exportFileDownload();
	        }
	    });
    });
