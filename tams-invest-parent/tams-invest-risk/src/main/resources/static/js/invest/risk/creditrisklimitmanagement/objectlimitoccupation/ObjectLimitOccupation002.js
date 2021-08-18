/**
 * 
 */
$(function() {
    //判断是提交或是保存
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.operateType = operateType; 
    	if($('#seqno').val() != null && $('#seqno').val() != ''){
    		object.creditTagId = $('#seqno').val();
    	}
        Joyin.ajaxJson(basePath + 'invest/risk/creditrisklimitmanagement/objectlimitoccupation/ObjectLimitOccupation002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/risk/creditrisklimitmanagement/objectlimitoccupation/ObjectLimitOccupation002/save', object, "ObjectLimitOccupation002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/risk/creditrisklimitmanagement/objectlimitoccupation/ObjectLimitOccupation002/save', object, "ObjectLimitOccupation002_btn_submit");
                }
            });
        });
    };

    var checkAndDisabled = function(el){
        if($(el).val()){
            $(el).attr("disabled",true);
        }
    }
    
    $(function() {
    	if ($('#seqno').val()) {
        	checkAndDisabled('#customerId');
        	checkAndDisabled('#creditType');
        	checkAndDisabled('#term');
        	checkAndDisabled('#termUnit');
    	}

    	checkAndDisabled('#createUser');
        // 绑定按钮事件
        $('#ObjectLimitOccupation002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#ObjectLimitOccupation002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        
        
    });
}());