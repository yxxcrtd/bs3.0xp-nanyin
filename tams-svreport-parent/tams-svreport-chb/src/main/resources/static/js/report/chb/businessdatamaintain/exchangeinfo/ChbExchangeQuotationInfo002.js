$(function() {
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.exchargeCode=$("#exchargeCode").val();
        Joyin.ajaxJson(basePath + 'chb/businessdatamaintain/exchangeinfo/ChbExchangeQuotationInfo002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                    WorkFlow.doSaveList(basePath + 'chb/businessdatamaintain/exchangeinfo/ChbExchangeQuotationInfo002/save', object, "ChbExchangeQuotationInfo002_btn_save");
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
        $('#ChbExchangeQuotationInfo002_btn_save').click(function() {
            saveOrSubmit('save');
        });
    });
}());