/**
 * 附件信息--共通业务组件js
 * @auth zhangnian
 */
var FileCommon = function() {
    return {
        /**
         * 获取上传文件信息事件
         */
        getUploadFileInfo : function() {
            var fun = $('#fileUploader').data('getSelectedFiles');
            if(typeof fun === 'function') {
            	return fun();
            }
            return [];
        }
    };
}();

$(function() {
	$('#multFileBtn').click(function() {
		$('.mult-file-btn', '#FileCommon_DIV').trigger('click');
	});

   $("#EtrIbRepoinfoFile_btn_save").click(function() {
       var object =  $.serializeToObject("#searchForm");
       object.fileArray = FileCommon.getUploadFileInfo();
       Joyin.ajaxJson(basePath + 'invest/entrust/etribrepo/EtrIbRepoinfoFile/save', object, function(result1) {
           Joyin.closeTopWindow();
       });

   });
});
