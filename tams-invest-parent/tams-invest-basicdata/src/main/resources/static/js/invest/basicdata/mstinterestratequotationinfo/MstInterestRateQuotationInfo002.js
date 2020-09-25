(function() {
	/**
     * 获取表单参数
     */
    var submitForm = $.serializeToObject('#submitForm');
	
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstinterestratequotationinfo/MstInterestRateQuotationInfo002/check', object, function(result) { 
           if(operateType === 'save') {
              Joyin.confirm( JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.save")),function() {
               WorkFlow.doSaveList(basePath + 'invest/basicdata/mstinterestratequotationinfo/MstInterestRateQuotationInfo002/save', object, "MstInterestRateQuotationInfo002_btn_save");
              });
           }else {
               Joyin.confirm( JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.submit")),function() {
                   WorkFlow.doSubmitList(basePath + 'invest/basicdata/mstinterestratequotationinfo/MstInterestRateQuotationInfo002/save', object, "MstInterestRateQuotationInfo002_btn_submit");
               });
           }
        }); 	
       }; 
    
    function　init(){
        if(pageModel != PAGE_MODEL_NEW){
            $("#intRateId").attr("disabled",true);
            $("#effDate").attr("disabled",true);
            $("#ccy").attr("disabled",true);
        }
    }
    /**
     * 画面加载完成
     */
    $(function() {
        init();
        // 绑定按钮事件
        $('#MstInterestRateQuotationInfo002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#MstInterestRateQuotationInfo002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });

    });
}());