$(function () {
	function saveOrSubmit(operateType) {
		var object = $.serializeToObject('#submitForm');
		object.operateType = operateType;
        object.pageModel = pageModel;
        
        if (operateType === 'submit') {
        	Joyin.ajax(basePath + 'valuation/accounadjustmanage/booksetdateswitch/ValBooksetDateSwitch002/check', object, function(result) {
                Joyin.confirm(result.data, function() {
                	WorkFlow.doSubmit(basePath + 'valuation/accounadjustmanage/booksetdateswitch/ValBooksetDateSwitch002/doUpdate', object, "ValBooksetDateSwitch002_btn_submit");
                });
        	});
        }
	}
    /**
     * 绑定按钮事件
     */
    $('#ValBooksetDateSwitch002_btn_submit').click(function() {
        saveOrSubmit('submit');
    });
    
});