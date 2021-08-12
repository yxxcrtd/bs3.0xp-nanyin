/**
 * 
 */
$(function() {
    //判断是提交或是保存
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.operateType = operateType; 
    	if($('#creditRuleId').val() != null && $('#creditRuleId').val() != ''){
    		object.creditTagId = $('#creditRuleId').val();
    	}
        Joyin.ajaxJson(basePath + 'invest/risk/creditrisklimitmanagement/singlelimitoccupation/SingleLimitOccupation002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/risk/creditrisklimitmanagement/singlelimitoccupation/SingleLimitOccupation002/save', object, "SingleLimitOccupation002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/risk/creditrisklimitmanagement/singlelimitoccupation/SingleLimitOccupation002/save', object, "SingleLimitOccupation002_btn_submit");
                }
            });
        });
    };

    $(function() {
 
        // 绑定按钮事件
        $('#SingleLimitOccupation002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#SingleLimitOccupation002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        
        
    });
}());