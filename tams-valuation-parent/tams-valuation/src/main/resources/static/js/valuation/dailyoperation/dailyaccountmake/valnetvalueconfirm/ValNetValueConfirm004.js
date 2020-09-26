
(function() {
	
    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
    	var object = $.serializeToObject('#submitForm');

    	object.finprodId = $('#finprodId').val();
    	object.valDate = $('#valDate').val();
    	object.netUnitValue = $('#netUnitValue').val();
        object.netUnitValueEdit = $('#netUnitValueEdit').val();
        object.assetValueEdit = $('#assetValueEdit').val();
        object.capiTal = $('#capiTal').val();
        object.valBalFlagCode = $('#valBalFlagCode').val();
        object.operateType = operateType;
        //add by hechuan 2019-11-5 start
        object.profitTypeCode = $('#profitTypeCode').val();
        //add by hechuan 2019-11-5 end
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'valuation/dailyoperation/dailyaccountmake/valnetvalueconfirm/ValNetValueConfirm004/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'valuation/dailyoperation/dailyaccountmake/valnetvalueconfirm/ValNetValueConfirm004/save', object, "ValNetValueConfirm004_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'valuation/dailyoperation/dailyaccountmake/valnetvalueconfirm/ValNetValueConfirm004/save', object, "ValNetValueConfirm004_btn_submit");
                }
            });
        });
    }
    
        
    $(function() {
        // 产品收益类型为货币型时，页面指标显示变为货币型的相应指标
        if(profitTypeMoney == $("#profitTypeCode").val()){
            $("#netUnitValueLabel").html("万份收益");
            $("#netUnitValueEditLabel").html("修改后万分收益");
            $("#assetValueEditLabel").html("修改后本日收益");
        }   
        
        // 按钮事件
        $('#ValNetValueConfirm004_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#ValNetValueConfirm004_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
       
    });
}());
