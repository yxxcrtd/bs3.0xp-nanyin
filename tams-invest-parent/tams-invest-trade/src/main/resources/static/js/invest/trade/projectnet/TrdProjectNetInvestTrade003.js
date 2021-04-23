//@ sourceURL=TrdProjectNetInvestTrade003.js
/**
 * 权益类资产投资交易确认
 */
$(function() {
    var getriskdata = function () {
        var obj = {timePoint:MY_SUP_TIME_TYPE_02,valDate:$('#tradeDate').val(),portfolioCode:$('#portfolioId').val(),tradeAmount:$('#shareAmt').val(),assetType:FINPROD_TYPE_F17,isEnabled:'01',assetCode:$('#finprodId').val(),tradeDirection:$('#ps').val()}
        var object = {valDate:['tradeDate','TrdProjectNetInvestTrade002.tradeDate'],portfolioCode:['portfolioId','TrdProjectNetInvestTrade002.portfolioId'],tradeAmount:['shareAmt','TrdProjectNetInvestTrade003.tradeSha'],assetType:['type6','TrdProjectNetInvestTrade002.type6'],tradeDirection:['ps','TrdProjectNetInvestTrade001.ps'],data:obj}
        return object;
    }

    function saveOrSubmit(operateType) {
        debugger;
        // 划款指令隐藏时，自动生成划款指令信息
        generateOrders(function () {
            var s;
            if(BaseRiskMathComm&&$('#ps').val()==ps_T19) {
                s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
                if(!s && s != ''){
                    return;
                }
                if (window.BaseRiskMathComm.isCalcConditionChanged(getriskdata())){
                    return;
                }
            }
            if (window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()) {
                return;
            }
            var object = $.serializeToObject('#submitForm',true);
            if ($('#select2-finprodId-container').attr('title')){
                object.finprodName = $('#select2-finprodId-container').attr('title');
            }
            object.finprodId = $('#finprodId').val();
            object.pageModel = pageModel;
            object.operateType = operateType;
            object.tradeId = $('#tradeId').val();
            object.bTradeId = $('#bTradeId').val();
            object.prinAmt = $('#tradeAmtHid').val();
            if(FileCommon) {
                if(pageModel == PAGE_MODEL_REFERENCE) {
                    object.fileArray = $('#fileUploader').data('data');
                }
                else {
                    object.fileArray = FileCommon.getUploadFileInfo();
                }
                if(object.fileArray && _.isArray(object.fileArray)){
                    $.each(object.fileArray, function(index,obj){
                        obj.seqNo = obj.fileNo;
                    });
                }
            }
            Joyin.ajaxJson(basePath + 'invest/trade/projectnet/TrdProjectNetInvestTrade003/check', object, function(result) {
                Joyin.confirm(result.data, function() {
                    if(operateType === 'save') {
                        if(object.ps == ps_T24){
                            WorkFlow.doSaveList(basePath + 'invest/trade/projectnet/TrdProjectNetInvestTrade003/save', object, "TrdProjectNetInvestTrade003_btn_total","TrdProjectNetInvestTrade003");
                        }else{
                            WorkFlow.doSaveList(basePath + 'invest/trade/projectnet/TrdProjectNetInvestTrade003/save', object, "TrdProjectNetInvestTrade003_btn_save","TrdProjectNetInvestTrade003");
                        }
                    }
                    else {
                        if(object.ps == ps_T24){
                            WorkFlow.doSubmitList(basePath + 'invest/trade/projectnet/TrdProjectNetInvestTrade003/save', object, "TrdProjectNetInvestTrade003_btn_total","TrdProjectNetInvestTrade003");
                        }else{
                            WorkFlow.doSubmitList(basePath + 'invest/trade/projectnet/TrdProjectNetInvestTrade003/save', object, "TrdProjectNetInvestTrade003_btn_submit","TrdProjectNetInvestTrade003");
                        }
                    }
                });
            });
        });
    };

    //查询组合可用现金
    var getPtlCashPositionInfo = function(){
        param = {portfolioId:$('#portfolioId').val(),ccy:$('#ccy').val(),vdate:$('#tradeDate').val(),calId:$('#calendarId').val(), chlId: $("#chlId").val()}
        Joyin.ajaxJson(basePath + 'invest/trade/projectnet/TrdProjectNetInvestTrade002/getProAvailableCash',param,
            function(result) {
                $('#proAvailableCash').val(result.cashT).trigger('blur');
                $('#proAvailableCash1').val(result.cashT1).trigger('blur');
                $('#accAvailableCash').val(result.cashT2).trigger('blur');
                $('#channelAvailableCash').val(result.cashTD).trigger('blur');
            });
    }
    //查询组合可用持仓
    var getPtlSecPositionInfo = function(){
        param = {portfolioId:$('#portfolioId').val(),finprodId:$('#finprodId').val(),cdate:$('#tradeDate').val(), secAcctId: $("#chlId").val()}
        Joyin.ajaxJson(basePath + 'invest/trade/projectnet/TrdProjectNetInvestTrade002/getPtlSecPosition',param,
            function(result) {
                $('#assetAvailablePosition').val(result).trigger('blur');
            });
    }
    //画面初始化
    var init = function(){

        $('#finprodMarketId').on('change', function () {
            var finprodMarketId = $('#finprodMarketId').val();
            // var finprodId1;
            // var finprodIdarr;
            // var finprodIda;
            // if(finprodId){
            //     var finprodtId = finprodId.split("_");
            //     finprodId1 = finprodtId[1];
            // }
            var ps = $('#ps').val();
            var portfolioId =  $('#portfolioId').val();
            if(finprodMarketId){
                var data = $('#finprodMarketId').select2('data')[0].object;
                $("#finprodId").val(data.finprodId);
                $("#finprodName").val(data.finprodId);
                $("#finprodNameAdd").val(finprodMarketId);
                $("#calendarId").val(data.calendarId).trigger('change');
                $("#ccy").val(data.ccy).trigger('change');
                $("#type6").val(data.type6).trigger('change');
                /*添加二三四级联动 zhangpuyu 2019/8/7 start*/
                $("#type9").val(data.type9).trigger('change');
                $("#type10").val(data.type10).trigger('change');
                $("#type11").val(data.type11).trigger('change');
                /*添加二三四级联动 zhangpuyu 2019/8/7 start*/
                // $("#trdInvestPersonInfo_counterId").val(data.issuer).trigger('change');
                $("#type7").val(data.type7).trigger('change');
            }
            ChannelAssetCommon.setChannelSelectObject($("#chlId"), $('#finprodId').val(), true, function () {

            });
            if (ps == ps_T05 && finprodId && portfolioId) {
                getPtlCashPositionInfo();
            }
            else if((ps == ps_T19 || ps == ps_T24) && finprodId && portfolioId){
                getPtlSecPositionInfo();
            }
//            var netDate = $('#netDate').val();
//            var tradeAmt = $('#tradeAmt').val();
//            var type6 = $('#type6').val();
//            if(netDate && finprodId){
//                var data = {finprodId:finprodId,netDate:netDate}
//                Joyin.ajaxJson(basePath + 'invest/trade/projectnet/TrdProjectNetInvestTrade003/getPrice', data , function(result) {
//                    $('#unitCprice').val(result).trigger('blur').change();
//                });
//            }
        });
        /**
         * 交易方向
         */
        $('#ps').on('change',function(){
            var ps = $('#ps').val();
            var finprodId = $('#finprodId').val();
            var portfolioId =  $('#portfolioId').val();
            if((ps == ps_T19 || ps == ps_T24)){
                $('#proAvailableCash_div').hide();
                $('#proAvailableCash1_div').hide();
                $('#accAvailableCash_div').hide();
                $('#assetAvailablePosition_div').show();
                $('#expectedArrivalDate_div').show();
                $('#transferInformation').show();
                $('#purchaseFee_div').hide();
                $('#redemptionFee_div').show();
                //赎回确认的时候如果交易金额或者交易份额为空的时候可编辑 yjm start 20191014
                if ($('#tradeAmt').val()){
                    $('#tradeAmt').attr('disabled',true);
                } else {
                    $('#tradeAmt').attr('disabled',false);
                }
                if ($('#shareAmt').val()){
                    $('#shareAmt').attr('disabled',true);
                } else {
                    $('#shareAmt').attr('disabled',false);
                }
                //赎回确认的时候如果交易金额或者交易份额为空的时候可编辑 yjm end 20191014
                // $('#channelFee_div').show();
                if(ps == ps_T24 && single==1){
                    $('#expectedArrivalDate').attr('disabled',false);
                    $('#expectedArrivalDate').attr('data-ignore',true);
                    $('#TrdProjectNetInvestTrade003_btn_save').attr('data-ignore',true).removeClass("layui-btn-disabled");;
                    $('#TrdProjectNetInvestTrade003_btn_submit').attr('data-ignore',true).removeClass("layui-btn-disabled");;
                    $('#TrdProjectNetInvestTrade003_btn_risk').attr('data-ignore',true).removeClass("layui-btn-disabled");;
                }
                if(finprodId && portfolioId){
                    getPtlSecPositionInfo();
                }
            }else{
                $('#proAvailableCash_div').show();
                $('#proAvailableCash1_div').show();
                $('#accAvailableCash_div').show();
                $('#assetAvailablePosition_div').hide();
                $('#expectedArrivalDate_div').hide();
                $('#transferInformation').hide();
                $('#carryAmt_div').hide();
                $('#purchaseFee_div').show();
                $('#redemptionFee_div').hide();
                $('#tradeAmt').attr('disabled',true);
                $('#shareAmt').attr('disabled',false);
                // $('#channelFee_div').hide();
                if(finprodId && portfolioId){
                    getPtlCashPositionInfo();
                }
            }
            // SNSTZJY-220
            // 【0710】计划类净值型申购确认交易画面，应该删除【托管户剩余现金】和【组合T+1日可用现金】
            if (ps == ps_T05){
                $('#proAvailableCash1_div').hide();
                $('#accAvailableCash_div').hide();
            }
        });
        /**
         * 净值日期
         */
        $('#netDate').change(function(e, isInit){
            var netDate = $('#netDate').val();
            var finprodId = $('#finprodId').val();
            var tradeAmt = $('#tradeAmt').val();
            if(netDate && finprodId){
                var data = {finprodId:finprodId,netDate:netDate}
                Joyin.ajaxJson(basePath + 'invest/trade/projectnet/TrdProjectNetInvestTrade003/getPrice', data , function(result) {
                    if (result.success) {
                        $('#unitCprice').val(result.price).trigger('blur').trigger("change", isInit);
                    }
                    else {
                        Joyin.error("所选净值日无净值, 默认按1处理, 请维护净值后重新交易")
                        $('#unitCprice').val(1).trigger("change", isInit);
                    }
                });
            }
        });
        var 赎回方式 = '01' // 金额赎回
        /**
         * 单位净价
         */
        $('#unitCprice').change(function(e, isInit){
            var unitCprice = $('#unitCprice').val();
            var ps = $('#ps').val();
            var shareAmt = $('#shareAmt').val();
            if(ps == ps_T05){
                var tradeAmtHid = $('#tradeAmtHid').val();
                // 申购确认，调整交易份额  交易份额不应显示反算理论值 yjm 20190726
                if(!shareAmt && unitCprice != null && unitCprice != '' && unitCprice != 0){
                    $('#shareAmt').val(tradeAmtHid/unitCprice).trigger('blur');
                }
            }else if((ps == ps_T19 || ps == ps_T24)){
                var tradeAmt = $('#tradeAmt').val();
                if (isInit && shareAmt) {
                    赎回方式 = '02'
                }
                if(unitCprice != null && unitCprice != '' && 赎回方式 === '02'){
                    $('#tradeAmt').val(shareAmt*unitCprice).trigger('blur');
                    $('#tradeAmtHid').val(shareAmt*unitCprice).trigger('blur');
                    $('#feeAmtRedem').change();
                }else if(unitCprice != null && unitCprice != '' && unitCprice != 0 && 赎回方式 === '01'){

                    $('#shareAmt').val(tradeAmt/unitCprice).trigger('blur');
                }
            }
        });
        /**
         * 组合代码
         */
        $('#portfolioId').change(function(){
            var portfolioId =  $('#portfolioId').val();
            var ps = $('#ps').val();
            var finprodId = $('#finprodId').val();
            if(ps == ps_T05 && finprodId && portfolioId){
                getPtlCashPositionInfo();
            }
            if((ps == ps_T19 || ps == ps_T24) && finprodId && portfolioId){
                getPtlSecPositionInfo();
            }
        });
        /**
         * 申购费
         */
        $('#feeAmtPay').change(function(){
            var feeAmtPay = $('#feeAmtPay').val();
            var tradeAmt = $('#tradeAmt').val();
            /*var channelFee = $('#channelFee').val();
            if(!channelFee){
                channelFee=0
            }*/
            if(!feeAmtPay){
                feeAmtPay=0;
            }
            if(tradeAmt){
                $('#tradeAmtHid').val(Number(tradeAmt)-Number(feeAmtPay)).trigger('blur');
                var unitCprice = $('#unitCprice').val();
                if(unitCprice){
                    $('#shareAmt').val($('#tradeAmtHid').val()/unitCprice).trigger('blur');
                }
            }
        });
        /**
         * 赎回费
         */
        $('#feeAmtRedem').change(function(){
            var feeAmtRedem = $('#feeAmtRedem').val();
            var tradeAmt = $('#tradeAmtHid').val();
            //var channelFee = $('#channelFee').val();
            if(!feeAmtRedem){
                feeAmtRedem = 0;
            }
            /*if(!channelFee){
                channelFee=0
            }*/
            if(tradeAmt){
                $('#tradeAmt').val(Number(tradeAmt)-Number(feeAmtRedem)).trigger('blur');
            }
        });
        /**
         * 通道费
         */
        /*$('#channelFee').change(function(){
            var ps = $("#ps").val();
            if (ps == ps_T05) {
                $('#feeAmtPay').change()
            }
            else if (ps == ps_T19 || ps == ps_T24) {
                $('#feeAmtRedem').change();
            }
        });*/

        /**
         * 交易金额
         */
        $('#tradeAmt').change(function(){
            var feeAmtPay = $('#feeAmtPay').val();
            var tradeAmt = $('#tradeAmt').val();
            var unitCprice = $('#unitCprice').val();
            var ps = $('#ps').val();
            if(unitCprice && $('#tradeAmt').val() && ps != ps_T19 && ps != ps_T24){
                $('#shareAmt').val($('#tradeAmt').val()/unitCprice).trigger('blur');
            }
        });
        //需求提出不要反算金额 yjm start 20191029
        /*$('#shareAmt').change(function(){
        	if($('#ps').val() == ps_T19){
	            var unitCprice = $('#unitCprice').val();
	            var shareAmt = $('#shareAmt').val();
	            if(unitCprice && shareAmt){
	                $('#tradeAmt').val(unitCprice*shareAmt).trigger('blur');
	            }
        	}
        });*/
        //需求提出不要反算金额 yjm end 20191029


        $('#expectedArrivalDate').change(function(){
            var expectedArrivalDate = $('#expectedArrivalDate').val();
            if(expectedArrivalDate){
                window.BussTrdTransferOrder['bussTrdTransferOrder'].generateClick();
            }
        });

        window.componentLoadCallback = function(isDone, pagePath, params){
            if(isDone){
                var riskparam = getriskdata();
                if (pageModel==PAGE_MODEL_NEW){
                    riskparam.data =null;
                }
                window.BaseRiskMathComm.getparam(riskparam);
                window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                    var data = {};
                    data.counterId = $('#trdInvestPersonInfo_counterId').val();
                    data.tradeAmt = $('#tradeAmt').val();
                    data.profitType = $('#profitType').val();
                    data.ps = $('#ps').val();
                    data.finprodId = $('#finprodId').val();
                    data.ccy = $('#ccy').val();
                    data.chlId = $("#chlId").val();
                    data.tradeDate = $('#tradeDate').val();
                    data.finprodType2 = $('#finprodType2').val();
                    data.profitType = $('#profitType').val();
                    if(data.ps == ps_T24){
                        data.tradeDate = $('#expectedArrivalDate').val();
                    }
                    return {
                        'isTotalTrade':YES_OR_NO_N
                        ,'bankLendingType':null
                        ,'portfolioId':$("#portfolioId").val()
                        ,'deal':data
                    };
                });
//                if(entrustId != ''){
//                    if(etrEntrust){
//                        window.TrdInvestPersonInfo002['trdInvestPersonInfo'].setEntrust(function () {
//                            return etrEntrust;
//                        });
//                    }
//                }
            }
        }
        $("#chlId").change(function () {
            var portfolioId =  $('#portfolioId').val();
            var ps = $('#ps').val();
            var finprodId = $('#finprodId').val();
            if (ps == ps_T05 && finprodId && portfolioId) {
                getPtlCashPositionInfo();
            }
            else if((ps == ps_T19 || ps == ps_T24) && finprodId && portfolioId){
                getPtlSecPositionInfo();
            }
        })
        $('#ps').change();
//        $('#type6').change();
        $('#finprodId').change();
        $('#netDate').trigger("change", true);

    };
    //add by lizhi 2020-2-26 划款指令是否自动生成  start
    var generateOrders = function (callback) {
        if(GEN_PAY_ORDERS_AUTO =='01'){
            $('#bussTrdTransferOrder_btn_generate').click();
        }
        // 回调方法
        if (callback && typeof callback === 'function') {
            callback();
        }
    }
    //add by lizhi 2020-2-26 划款指令是否自动生成  end
    /**
     * 画面加载完成
     */
    $(function() {
        //初始化画面
        init();
        // 是否显示划款指令
        if(GEN_PAY_ORDERS_AUTO=='01'){
            $('#transferInformation').hide() ;
        }else{
            //$('#transferInformation').show() ;
        }
        // 绑定按钮事件
        $('#TrdProjectNetInvestTrade003_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TrdProjectNetInvestTrade003_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $('#TrdProjectNetInvestTrade003_btn_view').click(function(){
            var finprodId = $('#finprodId').val();
            if(finprodId){
                Joyin.dialog(JoyinUtil.getLabel("FinProjectNetTargetAsset002.title"),
                    basePath + "invest/asset/projectnet/FinProjectNetTargetAsset002/init",
                    "95%", "90%",{pageModel:PAGE_MODEL_REFERENCE,
                        finprodId: finprodId});
            }
        });
        //风险试算
        $('#TrdProjectNetInvestTrade003_btn_risk').click(function(){
            //-----------add by jjpei 2020-01-07 风险新版本 start----------
            //---申购确认与到账不需要风险试算----
            if($("#ps").val()!=ps_T19){
                return;
            }
            var object = $.serializeToObject('#submitForm', true);
            object.finprodId = $('#finprodId').val();
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/1/RSK002/F04', object,getriskdata());
            //-----------add by jjpei 2020-01-07 风险新版本 end----------
        })
        //是否展示资产四五级分类
        if(IS_SHOW_TYPE == '01'){
            $('#type11Div').show();
        }else{
            $('#type11Div').hide();
        }
    });
});
