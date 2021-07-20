/**
 * huwenbin
 * 风险权数新建
 */
(function() {
    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
    	var object = $.serializeToObject('#informationForm');
    	if($('#effDateOld').val() != null && $('#effDateOld').val() != ''){
    		object.effDateOld = $('#effDateOld').val();
    	}
    	if($('#seqno').val() != null && $('#seqno').val() != ''){
    		object.seqno = $('#seqno').val();
    	}
        Joyin.ajaxJson(basePath + 'invest/risk/creditmanagement/creditrightsmanagement/CreditRightsManagement002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/risk/creditmanagement/creditrightsmanagement/CreditRightsManagement002/save', object, "CreditRightsManagement002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/risk/creditmanagement/creditrightsmanagement/CreditRightsManagement002/save', object, "CreditRightsManagement002_btn_submit");
                }
            });
        });
    }
    
    
    var init = function(){
    	var wfl_operType = $("#wfl_operType").val();
    	if(pageModel==PAGE_MODEL_EDIT){
    		if(wfl_operType == null || wfl_operType != 'NEW'){
    			$('#assetTypeOne').attr("disabled",true);
        		$('#assetTypeTwo').attr("disabled",true);
    		}
    	}
    };
        
        
    $(function() {
    	init();
        // 按钮事件
        $('#CreditRightsManagement002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#CreditRightsManagement002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());
