$(function() {
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm',true);
        object.pageModel = pageModel;
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstopicsparamante/MstOpicsParaMante002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/basicdata/mstopicsparamante/MstOpicsParaMante002/save', object, "MstOpicsParaMante002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/basicdata/mstopicsparamante/MstOpicsParaMante002/save', object, "MstOpicsParaMante002_btn_submit");
                }
            });
        });
    };
    var init = function(){
        if(pageModel==PAGE_MODEL_EDIT){
            $('#paraType').attr("disabled",true);
            $('#itemId').attr("disabled",true);
        }
    };
    
    /**
     * 画面加载完成
     */
    $(function() {
        init();
        // 绑定按钮事件
        $('#MstOpicsParaMante002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#MstOpicsParaMante002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());