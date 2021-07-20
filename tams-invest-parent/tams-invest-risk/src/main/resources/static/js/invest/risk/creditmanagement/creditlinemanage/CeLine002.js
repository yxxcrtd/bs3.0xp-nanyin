/**
 * MstCalendarType002 日历类型管理新建（含修改）
 */
$(function() {
    //判断是提交或是保存
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.operateType = operateType; 
    	if($('#creditTagId').val() != null && $('#creditTagId').val() != ''){
    		object.creditTagId = $('#creditTagId').val();
    	}
    	
    	//上农商实际只会对机构授信管控标签，默认为机构授信
    	object.creditUsingType = CREDIT_USING_TYPE_01;
    	//是否外汇和货币对都隐藏了，给与默认值
    	object.isFx = NO;
        Joyin.ajaxJson(basePath + 'invest/risk/creditmanagement/creditlinemanage/CeLine002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/risk/creditmanagement/creditlinemanage/CeLine002/save', object, "CeLine002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/risk/creditmanagement/creditlinemanage/CeLine002/save', object, "CeLine002_btn_submit");
                }
            });
        });
    };

    $(function() {
 
        // 绑定按钮事件
        $('#CeLine002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#CeLine002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        
        
        if(isNbCredit=="01"){
    		$("#isPerpetualSecDiv").hide();
    		$("#issueTypeDiv").hide();        	
        }

		
    });
}());