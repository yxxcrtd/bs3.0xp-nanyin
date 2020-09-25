$(function() {
    
    /**
     * 获取表单参数
     */
        var informationform = $.serializeToObject('#submitForm');
    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.operateType = operateType;
        Joyin.ajax(basePath + 'invest/basicdata/parammaintenance/ParamMaintenance002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSave(basePath + 'invest/basicdata/parammaintenance/ParamMaintenance002/save', object, "ParamMaintenance002_btn_save");
                }
                else {
                    WorkFlow.doSubmit(basePath + 'invest/basicdata/parammaintenance/ParamMaintenance002/save', object, "ParamMaintenance002_btn_submit");
                }
            });
        });
    }
    
    $(function() {
        // 按钮事件
        $('#ParamMaintenance002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#ParamMaintenance002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        
    });
}());