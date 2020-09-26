$(function() {
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.exchargeCode=$("#exchargeCode").val();
        Joyin.ajaxJson(basePath + 'valuation/basedata/marketdata/valexchangerateinfo/ValExchangeQuotationInfo002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'valuation/basedata/marketdata/valexchangerateinfo/ValExchangeQuotationInfo002/save', object, "ValExchangeQuotationInfo002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'valuation/basedata/marketdata/valexchangerateinfo/ValExchangeQuotationInfo002/save', object, "ValExchangeQuotationInfo002_btn_submit");
                }
            });
        });
    };
  
    //画面初始化
    var init = function(){
        if(pageModel==PAGE_MODEL_EDIT){
            $('#exchargeCode').attr("disabled",true);
            $('#tradeDate').attr("disabled",true);
        }
    };
    /**
     * 画面加载完成
     */
    $(function() {
    	//初始化画面
        init(); 
        // 绑定按钮事件
        $('#ValExchangeQuotationInfo002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#ValExchangeQuotationInfo002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());