/**
 * 条件新建
 */
$(function() {

    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
    	/**
    	 * 获取列表参数
    	 */
    	var object = $.serializeToObject('#submitForm', true);
		Joyin.ajaxJson(basePath + 'invest/riskredis/quota/supervisionindividuation/RskSupervisionIndividuation007/check', object, function(result) {
			Joyin.confirm(result.data, function() {
				if(operateType === 'save') {
					WorkFlow.doSaveList(basePath + 'invest/riskredis/quota/supervisionindividuation/RskSupervisionIndividuation007/save', object, "RskSupervisionIndividuation007_btn_save");
				}
				else {
					WorkFlow.doSubmitList(basePath + 'invest/riskredis/quota/supervisionindividuation/RskSupervisionIndividuation007/save', object, "RskSupervisionIndividuation007_btn_submit");
				}
			});
		});

    }

    $(function() {
    	if (pageModel === PAGE_MODEL_EDIT) {
    		$('#conditionItem').attr("disabled",true);
		}
        // 按钮事件
        $('#RskSupervisionIndividuation007_btn_save').click(function() {
            saveOrSubmit('save');
        });
    });
}());