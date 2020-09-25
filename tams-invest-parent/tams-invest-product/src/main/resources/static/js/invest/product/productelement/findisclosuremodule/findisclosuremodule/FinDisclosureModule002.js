/**
 * 信息披露模板管理新建
 */
(function() {
	/**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
    	/**
    	 * 获取列表参数
    	 */
        var object = $.serializeToObject('#submitForm');
        var file = $('#annex').data('getSelectedFiles')();
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.pageId = pageId;
        if(file && _.isArray(file) && file.length > 0){
        	object.fileName = file[0].srcName ? file[0].srcName : file[0].fileName;
        	object.fileId = file[0].fileNo;
//        	object.seqNo = file[0].fileNo;
//        	object.path = file[0].targetFile;
        }
        object.revealFileId = revealFileId;
        if(workflowId != null && workflowId != ''){
        	object.workflowId = workflowId;
        }
        Joyin.ajaxJson(basePath + 'invest/product/productelement/findisclosuremodule/findisclosuremodule/FinDisclosureModule002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/product/productelement/findisclosuremodule/findisclosuremodule/FinDisclosureModule002/save', object, "FinDisclosureModule002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/product/productelement/findisclosuremodule/findisclosuremodule/FinDisclosureModule002/save', object, "FinDisclosureModule002_btn_submit");
                }
            });
        });
    }
   
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#FinDisclosureModule002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#FinDisclosureModule002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());


var FinDisclosureModule002 = function() {
    return {
        /**
         * 文件上传后记录文件信息
         */
        onUploaded : function(res) {
            if(res && res.length > 0){
            	// 为了消除进度条
                Joyin.ajax(JoyinUtil.getRealPath('invest/product/productelement/findisclosuremodule/findisclosuremodule/FinDisclosureModule002/uploadFile'), {fileNo: res[0].fileNo, revealFileId: revealFileId});
            }
        }
    };
}();