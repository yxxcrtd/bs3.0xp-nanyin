(function(){
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        var pageParam = {
            finprodId:$('#finprodId').val()
            ,finprodAbbr: $('#finprodAbbr').val()
            ,tradeMarket: $('#tradeMarket').val()
            ,appearMarketId: $('#appearMarketId').val()
            ,appearMarketAbbr: $('#appearMarketAbbr').val()
            ,isAdded: $('#isAdded').val()
            ,tradeDate: $('#tradeDate').val()
        };
        return pageParam;
    };

    function saveOrSubmit(operateType) {
        debugger;
        var object = getPageParams();
        object.pageModel = pageModel;
        object.operateType = operateType;
        if ($('#workflowId').val() != null && $('#workflowId').val() != ''){
            object.workflowId = $('#workflowId').val()
        }
        Joyin.ajaxJson(basePath + 'invest/trade/bondmarkettrade/TrdBondMarketTrade002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                Joyin.ajaxJson(basePath + 'invest/trade/bondmarkettrade/TrdBondMarketTrade002/autoAddBond', object, function(result) {
                        if(operateType === 'save') {
                        if ($('#totleTradeId').val() != null && $('#totleTradeId').val() != ''){
                            object.totleTradeId = $('#totleTradeId').val();
                            Joyin.ajaxJson(basePath + 'invest/trade/bondmarkettrade/TrdBondMarketTrade002/getSaveData',object , function(result){
                                object = result.data;
                                WorkFlow.doSaveList(basePath + 'invest/trade/bondmarkettrade/TrdBondMarketTrade002/save', object, "TrdBondMarketTrade002_btn_save");
                            });    
                        }else{
                            WorkFlow.doSaveList(basePath + 'invest/trade/bondmarkettrade/TrdBondMarketTrade002/save', object, "TrdBondMarketTrade002_btn_save");
                        }
                    }
                    else {
                        WorkFlow.doSubmitList(basePath + 'invest/trade/bondmarkettrade/TrdBondMarketTrade002/save', object, "TrdBondMarketTrade002_btn_submit");
                    }
                },false);
            });
        },false);
    };

    /**
     * 画面联动处理
     * 1.1发行代码
     */
    $('#finprodId').on("change", function () {
        if ($('#finprodId').val() == null || $('#finprodId').val() == ''){
            $('#tradeMarket').val('').trigger('change');
            $('#finprodAbbr').val('').trigger('change');
            $('#appearMarketId').val('').trigger('change');
            $('#appearMarketId').attr("disabled",true);
        }else{
            var data = $('#finprodId').select2('data')[0].object;
            if (data != undefined && data.tradeMarket){
                $('#tradeMarket').val(data.tradeMarket).trigger('change');
            }
            if (data != undefined && data.finprodAbbr){
                $('#finprodAbbr').val(data.finprodAbbr).trigger('change');
            }
            $('#appearMarketId').attr("disabled",false);
        }
    });

    /**
     * 画面联动处理
     * 1.2发行代码
     */
    $('#appearMarketId').on("change", function () {
        if ($('#appearMarketId').val() == null || $('#appearMarketId').val() == '') {
            $('#appearMarketAbbr').val('').trigger('change');
            $('#isAdded').val('').trigger('change');
        }else {
            var data = $('#appearMarketId').select2('data')[0].object;
            if (data != undefined && data.appearMarketAbbr){
                $('#appearMarketAbbr').val(data.appearMarketAbbr).trigger('change');
            }
            if (data != undefined && data.isAdded){
                $('#isAdded').val(data.isAdded).trigger('change');
            }    
        }
    });
    
    $(function () {
        if (pageModel != null && pageModel != ''){
            $('#pageModel').val(pageModel).trigger('change');    
        }
        //
        $('#appearMarketId').attr("disabled",true);
        // 绑定按钮事件
        $('#TrdBondMarketTrade002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TrdBondMarketTrade002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        
    });
}());