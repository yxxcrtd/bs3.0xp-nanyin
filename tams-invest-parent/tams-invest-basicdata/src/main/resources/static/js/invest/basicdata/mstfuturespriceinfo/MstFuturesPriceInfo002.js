$(function() {
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.pageId = pageId;
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstfuturespriceinfo/MstFuturesPriceInfo002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/basicdata/mstfuturespriceinfo/MstFuturesPriceInfo002/save', object, "MstFuturesPriceInfo002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/basicdata/mstfuturespriceinfo/MstFuturesPriceInfo002/save', object, "MstFuturesPriceInfo002_btn_submit");
                }
            });
        });
    };

    var getObjectAttr = function () {
        var futuresId = $('#futuresId').val()
        if (futuresId !='' && futuresId!=null){
            Joyin.ajaxJson(basePath + 'invest/basicdata/mstfuturespriceinfo/MstFuturesPriceInfo001/getContractId?futuresId='+futuresId,null, function(result) {
                var obj = result[0].object;
                if (obj!=null && obj!=''){
                    if (obj.futuresType == 'FU02'){
                        $('#preSettlePrice').attr("data-textrange",3);
                        $('#openPrice').attr("data-textrange",3);
                        $('#closePrice').attr("data-textrange",3);
                        $('#highPrice').attr("data-textrange",3);
                        $('#lowPrice').attr("data-textrange",3);
                        $('#settlePrice').attr("data-textrange",3);
                    }
                    if(obj.futuresType == 'FU01'){
                        $('#preSettlePrice').attr("data-textrange",1);
                        $('#openPrice').attr("data-textrange",1);
                        $('#closePrice').attr("data-textrange",1);
                        $('#highPrice').attr("data-textrange",1);
                        $('#lowPrice').attr("data-textrange",1);
                        $('#settlePrice').attr("data-textrange",1);
                    }
                }
                $('#windCode').val('').trigger('change');
                if (obj.windCode!='' && obj.windCode!=null){
                    $('#windCode').textSelect('setValue',obj.windCode);
                }
            });
        }
    }
    $('#futuresId').change(function () {
        getObjectAttr();

    });


    //画面初始化
    var init = function(){
        $('#windCode').attr('disabled',true);
        //编辑模式下  合约标的、行情日期不可修改
        if(pageModel==PAGE_MODEL_EDIT){
            $('#futuresId').attr("disabled",true);
            $('#tradeDate').attr("disabled",true);
        }

    }

    /**
     * 画面加载完成
     */
    $(function() {

        //页面初始化及绑定onchange事件
        init();
        /**
         * 绑定事件
         */
        $('#MstFuturesPriceInfo002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });

        $('#MstFuturesPriceInfo002_btn_save').click(function() {
            saveOrSubmit('save');
        });
    });
}());