
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
        var infoDevStatus = $("#infoDevStatus").val();
        if(!infoDevStatus){
            Joyin.error("开发状态不能为空!");
            return;
        }
        var object = $.serializeToObject('#submitForm', false);
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.submit")), function() {
            WorkFlow.doSubmitList(basePath + 'disclosure/infoCompliance/infoCompliance003/save', object, "InfoCompliance003_btn_submit");
        });

    }

    //控件加载完成后
    window.componentLoadCallback = function(isDone, pagePath, params){
    }
    /**
     * 画面加载完成
     */
    $(function() {

        $('#InfoCompliance003_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());
