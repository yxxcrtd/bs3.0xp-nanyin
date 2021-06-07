
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
        debugger;
        if (!FileCommon || !FileCommon.getUploadFileInfo() || FileCommon.getUploadFileInfo().length == 0) {
            Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0054"));
            return;
        }
        if(FileCommon) {
    		object.fileArray = FileCommon.getUploadFileInfo();
    		if(object.fileArray && _.isArray(object.fileArray)){
    			var flag=true;
    			$.each(object.fileArray, function(index,obj){
    				obj.seqNo = obj.fileNo;
    				var index=obj.srcName.lastIndexOf('.');
    				var ext = obj.srcName.substr(index+1);
    				if(ext!='zip'){
    					flag=false;
    				}
    				obj.targetFile = obj.targetFile.absoluteFile;
    			});
    			if(!flag){
    				Joyin.error("选择的文件中包含不支持的格式，支持的格式为zip文件！");
    				return;
    			}
    		}
    	}
        object.uploadArrStr=JSON.parse(dataArrStr);
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil
                .getLabel("SysCommon.submit")), function() {
        	
        	WorkFlow.doSubmitList(basePath + 'report/chb/perprodeclarereg/PerProDeclareReg002/save', object, "SvrPerProDeclareReg002_btn_submit");
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
        $('#SvrPerProDeclareReg002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());
