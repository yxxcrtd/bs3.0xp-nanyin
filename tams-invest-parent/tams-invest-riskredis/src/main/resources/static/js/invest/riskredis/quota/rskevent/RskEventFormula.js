/**
 * 风控事项关联计算对象-公式配置
 */
(function() {
    /**
     * 保存或提交按钮执行事件
     */
    var onSaveOrSubmitAction = function(operateType){
        var params = $.serializeToObject('#submitForm');
        if (!params.paraCode) {
            Joyin.error(JoyinUtil.getMessage('RSKRDS_W_0041',JoyinUtil.getLabel('RskEventFormula.paraCode')));
            return;
        }
        if (!params.oprType) {
            Joyin.error(JoyinUtil.getMessage('RSKRDS_W_0041',JoyinUtil.getLabel('RskEventFormula.oprType')));
            return;
        }
        if (!params.relItem) {
            Joyin.error(JoyinUtil.getMessage('RSKRDS_W_0041',JoyinUtil.getLabel('RskEventFormula.relItem')));
            return;
        }
        top.returnData=params.paraCode + " " + params.oprType + " " + params.relItem;
        WorkFlow.cancel();
    };

    /*
     * 
     * 画面加载完成
     */
    $(function() {
        // 保存按钮执行事件
        $('#RskEventFormula_btn_save').click(function(){
            onSaveOrSubmitAction('save');
        });
    });

}());