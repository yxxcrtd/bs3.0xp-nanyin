
/**
 * 上传文件
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
        var object = $.serializeToObject('#submitForm', false);
        if (!FileCommon || !FileCommon.getUploadFileInfo() || FileCommon.getUploadFileInfo().length == 0) {
            Joyin.error(JoyinUtil.getMessage("DOCUMENT_COM_E_0054"));
            return;
        }
        if(FileCommon) {
    		object.fileArray = FileCommon.getUploadFileInfo();
    		if(object.fileArray && _.isArray(object.fileArray)){
    			$.each(object.fileArray, function(index,obj){
    				obj.seqNo = obj.fileNo;
    			});
    		}
    	}
        object.businessId = businessId;
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil
                .getLabel("SysCommon.submit")), function() {
        	WorkFlow.doSubmitList(basePath + 'invest/document/documentmanagement/DocumentManagement002/save', object, "DocumentManagement002_btn_submit");
        });
    }
    
    //控件加载完成后
    window.componentLoadCallback = function(isDone, pagePath, params){
    }
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#DocumentManagement002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());
