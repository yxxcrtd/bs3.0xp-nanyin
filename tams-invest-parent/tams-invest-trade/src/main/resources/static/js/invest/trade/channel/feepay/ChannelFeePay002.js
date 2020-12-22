/**
 * ChannelFeePay001 通道资产查询
 */
$(function() {

    window.componentLoadCallback = function (isDone, pagePath, params) {
        if (isDone) {
            window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                if (!$("#tradeAmt").val()) {
                    Joyin.error("实付费用")
                    return;
                }
                if (!$("#settleDate").val()) {
                    Joyin.error("请输入资金到账日")
                    return;
                }
                if (!$("#portfolioId").val()) {
                    Joyin.error("请输入组合")
                    return;
                }
                var deal = {};
                deal.ccy = $("#ccy").val();
                deal.tradeAmt = $("#tradeAmt").val();
                deal.settleDate = $("#settleDate").val();
                deal.ps = T10;
                deal.tradeDate = $("#settleDate").val();
                deal.finprodType2 = F28;
                var data = $("#finprodMarketId").find("option:selected").data("data");
                deal.chlId = data.object;
                return {
                    'isTotalTrade': YN_N
                    , 'bankLendingType': null
                    , 'portfolioId': $("#portfolioId").val()
                    , 'deal': deal
                };
            });
            if (formTodoList) {
                window.BussTrdTransferOrder['bussTrdTransferOrder'].saveCalcCondition();
            }
        }
    }

    function saveOrSubmit(operateType) {
        // 划款指令参数变更校验
        if(window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()){
            return ;
        }
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#informationForm',true);
        object.operateType = operateType;
        var data = $("#finprodMarketId").find("option:selected").data("data");
        object.channelName = data.text;
        var finprodId = data.object ? data.object || '' : '';
        object.finprodId = finprodId;
        object.finprodMarketId = $("#finprodMarketId").val();
        object.chlId = finprodId;
        if(FileCommon) {
            object.uploadFileList = FileCommon.getUploadFileInfo();
        }
        Joyin.ajaxJson(basePath + 'invest/trade/channel/feepay/ChannelFeePay002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/trade/channel/feepay/ChannelFeePay002/save', object, "ChannelFeePay002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/trade/channel/feepay/ChannelFeePay002/save', object, "ChannelFeePay002_btn_submit");
                }
            });
        });
    }

    var searchTheoryAmt = function() {
        //BS1.9
        //通道
        var dataChl = $("#finprodMarketId").find("option:selected").data("data");
        var dataPtl = $("#portfolioId").find("option:selected").data("data");
        var chlId = dataChl.object ? dataChl.object || '' : '';
        var ptlId = dataPtl.id ? dataPtl.id || '' : '';
        var finprodMarketId = $("#finprodMarketId").val();
      //add by yujiaping 2020-12-22 TISHKBS-1617 【合作资产费用支付管理】所选合作资产费用收取方式设置为“回款前扣除”，计提结束日清空、置灰，不可填start
        Joyin.ajax(basePath + "invest/trade/channel/feepay/ChannelFeePay002/getCollectType", {finprodMarketId : finprodMarketId},
                function(result){
                    if (result == '01') {
                    	$('#mdate').val('').trigger('change').attr('disabled',true);                      
                    }else{
                    	$('#mdate').attr('disabled',false);   
                    }
                });
        //add by yujiaping 2020-12-22 TISHKBS-1617 【合作资产费用支付管理】所选合作资产费用收取方式设置为“回款前扣除”，计提结束日清空、置灰，不可填end
        if ($("#finprodMarketId").val() && $("#mdate").val() && $("#portfolioId").val()) {
            $("#vdate").val('');
            $("#settleDate").val($("#mdate").val());
            $("#theoryInt").val('').trigger('blur');
            $("#tradeAmt").val('').trigger('blur');
            // 给币种赋个默认值CNY by zhaodongdong 20191210 start
            //$("#ccy").val('').trigger('change');
            // 给币种赋个默认值CNY by zhaodongdong 20191210 end
            var param = {chlId: chlId, ptlId: ptlId, mdate: $("#mdate").val()};
            Joyin.ajax(basePath + "invest/trade/channel/feepay/ChannelFeePay002/searchTheoryAmt", param,
                function(result){
                    if (result) {
                        $("#vdate").val(new Date(result.vdate).format('yyyy-MM-dd'));
                        $("#theoryInt").val(result.tradeAmt).trigger('blur');
                        $("#tradeAmt").val(result.tradeAmt).trigger('blur');
                        $("#ccy").val(result.ccy).trigger('change');
                    }
                });            
        }
    }
    /**
     * 画面加载完成
     */
    $(function() {
        /**
         * 保存
         */
        $('#ChannelFeePay002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        /**
         * 提交
         */
        $('#ChannelFeePay002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $("#finprodMarketId").change(searchTheoryAmt);
        $("#mdate").change(searchTheoryAmt);
        $("#portfolioId").change(searchTheoryAmt);
    });
}());