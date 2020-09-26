$(function() {
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.exchargeCode=$("#exchargeCode").val();
        Joyin.ajaxJson(basePath + 'submitmaintenance/exchangerateinfo/SvrMstExchangeQuotationInfo002/check', object, function(result) {
            Joyin.confirm(result.data, function(){
                Joyin.ajax("save",object, function(result) {
                	parent.layer.close(parent.layer.getFrameIndex(window.name));
                    });
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
        $('#SvrMstExchangeQuotationInfo002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());