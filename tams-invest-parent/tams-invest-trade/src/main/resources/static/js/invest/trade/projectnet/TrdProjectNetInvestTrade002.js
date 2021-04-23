/**
 * 权益类资产投资交易新建
 */
$(function() {
    var i = 0;
    var getriskdata = function () {
        var obj = {timePoint:MY_SUP_TIME_TYPE_02,valDate:$('#tradeDate').val(),portfolioCode:$('#portfolioId').val(),tradeAmount:$('#tradeAmt').val(),assetType:FINPROD_TYPE_F17,isEnabled:'01',assetCode:$('#finprodId').val(),tradeDirection:$('#ps').val()}
        //---add by 裴家俊  2019/03/20  添加资产二级类型条件---start-----
        if($('#type7').val() && $('#type7').val() != ''){
            obj.assetSecType = $('#type7').val();
        }
        //---add by 裴家俊  2019/03/20  添加资产二级类型条件---end-----
        var object = {valDate:['tradeDate','TrdProjectNetInvestTrade002.tradeDate'],portfolioCode:['portfolioId','TrdProjectNetInvestTrade002.portfolioId'],tradeAmount:['tradeAmt','TrdProjectNetInvestTrade003.tradeAmt'],assetType:['type6','TrdProjectNetInvestTrade002.type6'],tradeDirection:['ps','TrdProjectNetInvestTrade001.ps'],data:obj}
        /*if($('#shareAmt').val() != null && $('#shareAmt').val() != ''){
            obj.tradeAmount = $('#shareAmt').val()
            obj.amtType = '1';
            object.tradeAmount = ['shareAmt','TrdProjectNetInvestTrade002.tradeSha']
        }*/
        if($('#ps').val() == ps_mr||$('#ps').val() == ps_mc||$('#ps').val() == ps_T06){
            obj.tradeAmount = $('#shareAmt').val();
            obj.amtType = '1';
            object.tradeAmount = ['shareAmt','TrdProjectNetInvestTrade002.tradeSha']
        }
        return object;
    }

    function saveOrSubmit(operateType) {
        if (GEN_PAY_ORDERS_AUTO == '01' && $('#ps').val()!=ps_T06) {
            window.BussTrdTransferOrder['bussTrdTransferOrder'].generateOrders(function () {
                realSaveOrSubmit(operateType);
            });
        }
        else {
            realSaveOrSubmit(operateType);
        }
    }

    function realSaveOrSubmit(operateType) {
        // 划款指令隐藏时，自动生成划款指令信息
        var s;
        if(BaseRiskMathComm&&$('#ps').val()!=ps_T06) {
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
        var object = $.serializeToObject('#submitForm', true);
        if ($('#select2-finprodId-container').attr('title')){
            object.finprodName = $('#select2-finprodId-container').attr('title');
        }
        object.finprodId = $('#finprodId').val();
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.tradeId = $('#tradeId').val();
        object.prinAmt = $('#tradeAmtHid').val();

        if(FileCommon) {
            object.fileArray = FileCommon.getUploadFileInfo();
            if(object.fileArray && _.isArray(object.fileArray)){
                $.each(object.fileArray, function(index,obj){
                    obj.seqNo = obj.fileNo;
                });
            }
        }
        if ($('#trdInvestPersonInfo_counterId').select2('data') && $('#trdInvestPersonInfo_counterId').select2('data')[0]) {
            object.counterType = $('#trdInvestPersonInfo_counterId').select2('data')[0].object;;
        }
        Joyin.ajaxJson(basePath + 'invest/trade/projectnet/TrdProjectNetInvestTrade002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/trade/projectnet/TrdProjectNetInvestTrade002/save', object, "TrdProjectNetInvestTrade002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/trade/projectnet/TrdProjectNetInvestTrade002/save', object, "TrdProjectNetInvestTrade002_btn_submit");
                }
            });
        });
    };
    //查询组合可用现金
    var getPtlCashPositionInfo = function(){
        if ($('#portfolioId').val()) {
            param = {portfolioId:$('#portfolioId').val(),ccy:$('#ccy').val(),vdate:$('#tradeDate').val(),calId:$('#calendarId').val(), chlId: $("#chlId").val()}
            Joyin.ajaxJson(basePath + 'invest/trade/projectnet/TrdProjectNetInvestTrade002/getProAvailableCash',param,
                function(result) {
                    $('#proAvailableCash').val(result.cashT).trigger('blur');
                    $('#proAvailableCash1').val(result.cashT1).trigger('blur');
                    $('#accAvailableCash').val(result.cashT2).trigger('blur');
                    $('#channelAvailableCash').val(result.cashTD).trigger('blur');
                });
        }
    }
    //查询组合可用持仓
    var getPtlSecPositionInfo = function(){
        if ($('#portfolioId').val()) {
            param = {portfolioId:$('#portfolioId').val(),finprodId:$('#finprodId').val(),cdate:$('#tradeDate').val(), secAcctId: $("#chlId").val()}
            Joyin.ajaxJson(basePath + 'invest/trade/projectnet/TrdProjectNetInvestTrade002/getPtlSecPosition',param,
                function(result) {
                    $('#assetAvailablePosition').val(result).trigger('blur');
                });
        }
    }
    //查询资产管理人
    var selectManeger = function(){
        if($('#finprodMarketId').val()){
            var data = $('#finprodMarketId').select2('data')[0].object;
            Joyin.ajaxJson(basePath + 'invest/trade/fund/TrdFundInvestTrade002/selectManeger',{issuer:data.issuer},
                function(result) {
                    $("#trdInvestPersonInfo_counterId").textSelect('setValue',result.id);
                });
        }
    }
    /**
     * 画星星
     */
    var star = function() {
        // 加红星星
        // 查找label
        var _findPrevLabel = function(element, level) {
            var $el = element.prev();
            if (!$el.is('label') && level > 0) {
                $el = $el.find('label');
                if ($el.length === 0) {
                    return _findPrevLabel(element.parent(), level - 1);
                }
            }
            return $el;
        };

        // 非查看模式
        if (typeof pageModel !== 'undefined'
            && (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_NEW)) {
            $('.must').each(function() {
                $(this).remove();
            })
            $('*[required]').each(function() {
                var $el = $(this);
                var $target = _findPrevLabel($el, 3);
                if ($target.find('> .must').length === 0) {
                    $target.append($('<i class="must">*</i>'));
                }
            });
        }
    }
    /**
     * 动态设置不可编辑
     */
    var checkAndAssign = function(el, value, editable){
        if(value != null && value != ''){
            $(el).val(value).trigger('change');
            if(!editable){
                $(el).attr("disabled",true);
            }
        }
    };
    //画面初始化
    var init = function(){

        var entrustId = $("#entrustId").val();

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
            var ccy = $('#ccy').val();
            if(finprodMarketId){
                var data = $('#finprodMarketId').select2('data')[0].object;
                $("#finprodId").val(data.finprodId);
                $("#finprodName").val(data.finprodId);
                $("#finprodNameAdd").val(finprodMarketId);
                if (data.calendarId !== "") {
                    $("#calendarId").val(data.calendarId).trigger('change');
                }else {
                    // 如果标的信息中的交易日历为空，默认01 中国法定节假日
                    $("#calendarId").val("01").trigger('change');
                }
                $("#ccy").val(data.ccy).trigger('change');
                $("#type6").val(data.type6).trigger('change');
                /*添加资产二级-四级分类 zhangpuyu 2019/7/31 start*/
                $("#type9").val(data.type9).trigger('change');
                $("#type10").val(data.type10).trigger('change');
                $("#type11").val(data.type11).trigger('change');
                /*添加资产二级-四级分类 zhangpuyu 2019/7/31 end*/
                $("#type7").val(data.type7).trigger('change');
                $("#tradeMarket").val(data.tradeMarket).trigger('change');
                if ((ps == ps_T04 || ps == ps_T06) &&　!entrustId) {
                    $('#trdInvestPersonInfo_counterId option').remove();
//                $("#trdInvestPersonInfo_counterId").textSelect('setValue',data.issuer);
                    selectManeger();
                }
                //add  by zhangnian  20190917  start
                /*if($('#ps').val()==ps_T04){
                    $("#settleSpreed").val(data.purSpreed).change();
                }else if($('#ps').val()==ps_T06){
                    $("#settleSpreed").val(data.redSpreed).change();
                }*/
                //add  by zhangnian  20190917  end
                /*if (($('#ps').val() == ps_T04 || $('#ps').val() == ps_mr) && finprodId && portfolioId) {*/
                if (($('#ps').val() == ps_T04 || $('#ps').val() == ps_mr)  && portfolioId) {
                    getPtlCashPositionInfo();
                }

                $("#purSpeed").val("T+"+data.purSpreed).change();
                $("#redSpeed").val("T+"+data.redSpreed).change();

            }
            ChannelAssetCommon.setChannelSelectObject($("#chlId"), $('#finprodId').val(), $("#entrustId").val(), function () {

            });
            $('#netDate').change();

            if ((ps == ps_T06 || ps == ps_mc) && finprodId && portfolioId) {
                getPtlSecPositionInfo();
            }
        });

        $("#chlId").change(function () {
            var finprodId = $('#finprodId').val();
            var ps = $('#ps').val();
            var portfolioId =  $('#portfolioId').val();
            var ccy = $('#ccy').val();
            /*if ((ps == ps_T04 || ps == ps_mr) && finprodId && portfolioId) {*/
            if ((ps == ps_T04 || ps == ps_mr)  && portfolioId) {
                getPtlCashPositionInfo();
            }
            if ((ps == ps_T06 || ps == ps_mc) && finprodId && portfolioId) {
                getPtlSecPositionInfo();
            }
        })

        /**
         * 交易方向
         */
        $('#ps').on('change',function(){
            var ps = $('#ps').val();
            var finprodId = $('#finprodId').val();
            var portfolioId =  $('#portfolioId').val();
            var ccy = $('#ccy').val();
            if(ps == ps_T04){
                $('#tradeAmt').attr('required',true);
                $('#proAvailableCash_div').show();
                $('#proAvailableCash1_div').hide();
                // $('#accAvailableCash_div').show();
                $('#div_channelCash').hide();
                $('#assetAvailablePosition_div').hide();
                $('.expectedArrivalDate_div').hide();
                $('#transferInformation').show();
                $('#tradeSha_div').hide();
                $('#shareAmt').val("");
                $('#tradeAmt_div').show();
                $('#tradeAmt').attr('disabled',false);
                $('#date_div').hide();
                $('#fee_div').hide();
                $('#settleSpreed_div').show();
                if(finprodId){
                    $('#trdInvestPersonInfo_counterId option').remove()
//                    $("#trdInvestPersonInfo_counterId").textSelect('setValue',$('#finprodId').select2('data')[0].object.issuer);
                    selectManeger();
                    /*var data = $('#finprodId').select2('data')[0].object;
                    $("#settleSpreed").val(data.purSpreed);*/
                }
                /*if(finprodId && portfolioId && ccy){*/
                if(portfolioId && ccy){
                    getPtlCashPositionInfo();
                }
            }else if(ps == ps_T06){
                $('#tradeAmt').attr('required',false);
                $('#proAvailableCash_div').hide();
                $('#proAvailableCash1_div').hide();
                // $('#accAvailableCash_div').hide();
                $('#div_channelCash').hide();
                $('#assetAvailablePosition_div').show();
                $('.expectedArrivalDate_div').show();
                $('#transferInformation').hide();
                $('#tradeSha_div').show();
                $("#type7").change();
                $('#date_div').hide();
                $('#fee_div').hide();
                $('#settleSpreed_div').show();
                if(finprodId){
                    $('#trdInvestPersonInfo_counterId option').remove()
//                    $("#trdInvestPersonInfo_counterId").textSelect('setValue',$('#finprodId').select2('data')[0].object.issuer);
                    selectManeger();
                    /*var data = $('#finprodId').select2('data')[0].object;
                    $("#settleSpreed").val(data.redSpreed);*/
                }
//                $('#tradeAmt_div').hide();
//                $('#tradeAmt').val("");
                if(finprodId && portfolioId){
                    getPtlSecPositionInfo();
                }
                if(ps == ps_T06){
                    $('#tradeAmt').val('').attr('disabled',true);
                    $('#shareAmt').attr('disabled',false);
                }
                //-----add by 裴家俊 切换到赎回时清空风险试算列表 start ----
                if($("#baseRiskMathCommTable").data('joyintable')!=null) {
                    $("#riskTrialSpan").empty();
                    $("#baseRiskMathCommTable").data('joyintable').reload({data: []});
                    $("#riskMathFlag").val("01");
                }
                //-----add by 裴家俊 切换到赎回时清空风险试算列表 end ----
            }else if(ps == ps_mr){
                $('#date_div').show();
                $('#fee_div').show();
                $('#proAvailableCash_div').show();
                $('#proAvailableCash1_div').hide();
                // $('#accAvailableCash_div').show();
                $('#div_channelCash').hide();
                $('#assetAvailablePosition_div').hide();
                $('.expectedArrivalDate_div').hide();
                $('#transferInformation').show();
                $('#tradeSha_div').show();
                $('#carryAmt_div').hide();
                $('#purchaseFee_div').show();
                $('#redemptionFee_div').hide();
                $('#tradeAmt').attr('disabled',true);
                $('#shareAmt').attr('disabled',false);
                $('#channelFee_div').hide();
                $('#hostingFee_div').hide();
                $('#settleSpreed_div').hide();
                /*if(finprodId && portfolioId){*/
                if(portfolioId){
                    getPtlCashPositionInfo();
                }
                $('#tradeAmt').val('').attr('disabled',true);
                $('#shareAmt').attr('disabled',false);
                $('#tradeAmt').attr('required',false);
                $('#netDate').change();
            }else if(ps == ps_mc){
                $('#date_div').show();
                $('#fee_div').show();
                $('#proAvailableCash_div').hide();
                $('#proAvailableCash1_div').hide();
                // $('#accAvailableCash_div').hide();
                $('#div_channelCash').hide();
                // modify by yushilong 除交易方向为赎回皆隐藏 20200721 start
                $('.expectedArrivalDate_div').hide();
                // modify by yushilong 除交易方向为赎回皆隐藏 20200721 end
                $('#assetAvailablePosition_div').show();
                $('#transferInformation').show();
                $('#purchaseFee_div').hide();
                $('#tradeSha_div').show();
                $('#redemptionFee_div').show();
                $('#tradeAmt').attr('disabled',true);
                $('#shareAmt').attr('disabled',false);
                $('#channelFee_div').show();
                $('#hostingFee_div').show();
                $('#settleSpreed_div').hide();
                if(finprodId && portfolioId){
                    getPtlSecPositionInfo();
                }
                $('#tradeAmt').val('').attr('disabled',true);
                $('#shareAmt').attr('disabled',false);
                $('#tradeAmt').attr('required',false);
                $('#netDate').change();
            }
            // 交易方向变更 清空交易对手 yjm 2019/07/01 start
            if ($('#trdInvestPersonInfo_counterId').select2('data') && $('#trdInvestPersonInfo_counterId').select2('data')[0]) {
                var 交易对手 =  $('#trdInvestPersonInfo_counterId').select2('data')[0].object;
                if (typeof 交易对手 != 'undefined') {
                    if ((交易对手 != 'N' && (ps == ps_mc || ps == ps_mr)) || (ps && 交易对手 && 交易对手 != 'S' && ps != ps_mc && ps != ps_mr)) {
                        clearTrader();
                    }
                }
            }
            //交易方向变更 清空交易对手 yjm 2019/07/01 end

            //清算速度
            $('#settleSpreed').trigger('change');
            star();
        });

        var clearTrader = function () {
            $('#trdInvestPersonInfo_counterId').val('').change();
            $('#trdInvestPersonInfo_counterTrader').val('');
            $('#trdInvestPersonInfo_contactInfo').val('');
        }

        // zhangnian add 20190917 交易日期+清算速度 联动计算确认日期 start
        var calcCfmDate = function() {
            var tradedate = $('#tradeDate').val();
            var settleSpeed = $('#settleSpreed').val();
            var calendarId = $('#calendarId').val();
            if (!tradedate || !settleSpeed) {
                return;
            }
            // 为空时默认 中国法定节假日
            if (!calendarId){
                calendarId = '01'
            }
            if (settleSpeed === settleSpreed_T1){
                settleSpeed = 1;
            }else if(settleSpeed === settleSpreed_T2){
                settleSpeed = 2;
            }else {settleSpeed = 0;}
            Joyin.ajaxSync(basePath + 'invest/trade/TrdCommonController/getNextWorkDay', {
                'calendarId': calendarId,
                'tradeDate': tradedate,
                'settleSpreed':settleSpeed
            }, function (result) {
                $("#purCfmDate").val(result).trigger("change");
                $('#expectedArrivalDate').val(result).trigger("change");
            });
        }
        // zhangnian add 20190917 交易日期+清算速度 联动计算确认日期 end
        /**
         * 基金类型
         */
//        $("#type7").change(function(){
//            var type7 = $('#type7').val();
//            var ps = $('#ps').val();
//            if(type7 == plansType01 && ps == ps_T06){
//                $('#tradeAmt').val('').attr('disabled',true);
//                $('#shareAmt').attr('disabled',false);
//            }else{
//                $('#tradeAmt').val('').attr('disabled',false);
//                $('#shareAmt').val('').attr('disabled',false);
//            }
//        });
        $('#tradeAmt').change(function(){
            var tradeAmt = $('#tradeAmt').val();
            var ps = $('#ps').val();
            if(ps == ps_T04 || ps == ps_T06){
                //非委托可修改联动  委托过来 不可以修改
                if(!$("#entrustId").val()){
                    if(tradeAmt){
                        $('#shareAmt').val('').attr('disabled',true).attr('required',true);
                    }else{
                        $('#shareAmt').attr('disabled',false).attr('required',true);
                    }
                }else{
                    if(tradeAmt){
                        $('#shareAmt').val('').attr('disabled',true).attr('required',true);
                    }
                }
                star();
            }
            if(ps == ps_mr || ps == ps_mc){
                var feeAmtPay = $('#feeAmtPay').val();
                var unitCprice = $('#unitCprice').val();
                if(unitCprice && $('#tradeAmt').val()){
                    $('#shareAmt').val($('#tradeAmt').val()/unitCprice).trigger('blur');
                }
            }
        });

        /**
         * 净值日期
         */
        $('#netDate').change(function(){
            var netDate = $('#netDate').val();
            var finprodId = $('#finprodId').val();
            var tradeAmt = $('#tradeAmt').val();
            if(netDate && finprodId){
                var data = {finprodId:finprodId,netDate:netDate}
                Joyin.ajaxJson(basePath + 'invest/trade/projectnet/TrdProjectNetInvestTrade003/getPrice', data , function(result) {
                    if (result.success) {
                        $('#unitCprice').val(result.price).trigger('blur').change();
                    }
                    else {
                        if(pageModel != PAGE_MODEL_REFERENCE){
                            Joyin.error("所选净值日无净值, 默认按1处理, 请维护净值后重新交易");
                        }
                        $('#unitCprice').val(1).trigger('blur').change();
                    }
                });
            }
        });
        /**
         * 单位净价
         */
        $('#unitCprice').change(function(){
            var unitCprice = $('#unitCprice').val();
            var tradeAmt = $('#tradeAmt').val();
            var ps = $('#ps').val();
            var shareAmt = $('#shareAmt').val();
//            if(ps == ps_mr || ps == ps_mc){
//                if(unitCprice != null && unitCprice != '' && unitCprice != 0){
//                    $('#shareAmt').val(tradeAmt/unitCprice).trigger('blur');
//                }
//            }else
            if((ps == ps_mr || ps == ps_mc)){
                if(unitCprice != null && unitCprice != '' && shareAmt){
                    $('#tradeAmt').val(shareAmt*unitCprice).trigger('blur').attr('disabled',true);
                }else if(unitCprice != null && unitCprice != '' && unitCprice != 0 && tradeAmt){
                    $('#shareAmt').val(tradeAmt/unitCprice).trigger('blur').attr('disabled',true);
                }
            }
        });
        $('#shareAmt').change(function(){
            var shareAmt = $('#shareAmt').val();
            var ps = $('#ps').val();
            if(ps == ps_T04 || ps == ps_T06){
                //非委托可修改联动  委托过来 不可以修改
                if(!$("#entrustId").val()){
                    if(shareAmt){
                        $('#tradeAmt').val('').attr('disabled',true).attr('required',false);
                    }else{
                        if(ps == ps_T04){
                            $('#tradeAmt').attr('disabled',false).attr('required',true);
                        }
                        if(ps == ps_T06){
                            $('#tradeAmt').attr('disabled',true).attr('required',false);
                        }
                    }
                }else{
                    if(shareAmt){
                        $('#tradeAmt').val('').attr('disabled',true).attr('required',false);
                    }
                }
                star();
            }
            if(ps == ps_mr || ps == ps_mc){
                $('#tradeAmt').val("").trigger('blur');
                var unitCprice = $('#unitCprice').val();
                if(unitCprice && shareAmt){
                    $('#tradeAmt').val(unitCprice*shareAmt).trigger('blur');
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
            var ccy = $('#ccy').val();
            /*if((ps == ps_T04 || ps == ps_mr) && finprodId && portfolioId){*/
            if((ps == ps_T04 || ps == ps_mr)  && portfolioId){
                getPtlCashPositionInfo();
            }
            if((ps == ps_T06 || ps == ps_mc) && finprodId && portfolioId){
                getPtlSecPositionInfo();
            }
        });

        /**
         * 清算速度change事件
         */
        $('#settleSpreed').change(function(){
            var settleSpeed = $('#settleSpreed').val();
            var tradeDate = $('#tradeDate').val();
            if(pageModel==PAGE_MODEL_NEW){
                if (settleSpeed && settleSpeed != '' && tradeDate && tradeDate != ''){
                    /*var num = 0;
                    if (settleSpeed === settleSpreed_T1){
                        num = 1;
                    }else if(settleSpeed === settleSpreed_T2){
                        num = 2;
                    }*/
                    /* var num = settleSpeed;
                     $('#expectedArrivalDate').val(DateUtil.parse(tradeDate).DateAdd('d', num).Format('yyyy-MM-dd')).trigger('change');*/
                }
                if($("#settleSpreed_div").is(":hidden")){
                    var num = 0;
                    $('#expectedArrivalDate').val(DateUtil.parse(tradeDate).DateAdd('d', num).Format('yyyy-MM-dd')).trigger('change');
                }
            }
            //计算确认日期
            if (i < 2 && pageModel==PAGE_MODEL_EDIT){
                i = i + 1;
                return
            }
            calcCfmDate();
        });

        $('#entrustId').change(function(){
            var entrustId = $('#entrustId').val();
            if(entrustId){
                $('#portfolioId').attr('disabled',true);
                $('#ps').attr('disabled',true);
                $('#finprodId').attr('disabled',true);
            }else{
                $('#portfolioId').attr('disabled',false);
                $('#ps').attr('disabled',false);
                $('#finprodId').attr('disabled',false);
            }
        });
        window.componentLoadCallback = function(isDone, pagePath, params){
            if(isDone){
                var riskparam = getriskdata();
                if (pageModel==PAGE_MODEL_NEW){
                    riskparam.data =null;
                }
                window.BaseRiskMathComm.getparam(riskparam);
                ChannelAssetCommon.setChannelSelect($("#chlId"), $('#finprodId').val(), $("#entrustId").val(), function () {
                    window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                        var data = {};
                        data.counterId = $('#trdInvestPersonInfo_counterId').val();
                        data.tradeAmt = $('#tradeAmt').val();
                        data.profitType = $('#profitType').val();
                        data.ps = $('#ps').val();
                        data.finprodId = $('#finprodId').val();
                        data.ccy = $('#ccy').val();
                        data.chlId = $('#chlId').val();
                        data.tradeDate = $('#tradeDate').val();
                        data.finprodType2 = $('#finprodType2').val();
                        data.profitType = $('#profitType').val();
                        return {
                            'isTotalTrade':YES_OR_NO_N
                            ,'bankLendingType':null
                            ,'portfolioId':$("#portfolioId").val()
                            ,'chlId':$("#chlId").val()
                            ,'deal':data
                            // add by yushilong 限制划款申请对金额非空校验 20200706 start
                            ,'repPageId':'TrdProjectNetInvestTrade002'
                            // add by yushilong 限制划款申请对金额非空校验 20200706 end
                        };
                    });
                    if (pageModel == PAGE_MODEL_EDIT) {
                        window.BussTrdTransferOrder['bussTrdTransferOrder'].saveCalcCondition();
                    }
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

        $('#ps').change();
//        $('#type6').change();

        $('#finprodId').change();
        $('#tradeAmt').change();
        $('#shareAmt').change();
        $('#entrustId').change();
    };

    //delete by yushilong 合并后改部分不再使用 20200707 start
    /*//add by lizhi 2020-2-26 划款指令是否自动生成  start
    var generateOrders = function (callback) {
        // add by dubaojun 2020-07-06 缺陷SNSTZJY-151对应：基金赎回和转换申请不生成划款指令 start
    	if($("#ps").val() && $("#ps").val() != ps_T06) {
	    	// 如果还款指令设置为隐藏，则自动生成划款指令
	    	if(GEN_PAY_ORDERS_AUTO =='01'){
	    		$('#bussTrdTransferOrder_btn_generate').click();
	    	}
    	}
        // add by dubaojun 2020-07-06 缺陷SNSTZJY-151对应：基金赎回和转换申请不生成划款指令 end
    	// 回调方法
	    if (callback && typeof callback === 'function') {
	        callback();
	    }
    }
    //add by lizhi 2020-2-26 划款指令是否自动生成  end*/
    //delete by yushilong 合并后改部分不再使用 20200707 end
    /**
     * 画面加载完成
     */
    $(function() {
        //初始化画面
        init();
        // 是否显示划款指令
        if(GEN_PAY_ORDERS_AUTO === '01'){
            $('#transferInformation').hide() ;
        }else{
            // 交易方向决定是否显示划款指令
            let ps = $('#ps').val();
            if (ps !== ps_T06) {
                $('#transferInformation').show();
            }
        }
        // 绑定按钮事件
        $('#TrdProjectNetInvestTrade002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TrdProjectNetInvestTrade002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $('#TrdProjectNetInvestTrade002_btn_view').click(function(){
            var finprodId = $('#finprodId').val();
            if(finprodId){
                Joyin.dialog(JoyinUtil.getLabel("FinProjectNetTargetAsset002.title"),
                    basePath + "invest/asset/projectnet/FinProjectNetTargetAsset002/init",
                    "95%", "90%",{pageModel:PAGE_MODEL_REFERENCE,
                        finprodId: finprodId});
            }
        });
        //风险试算
        $('#TrdProjectNetInvestTrade002_btn_risk').click(function(){
            //window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
            //-----------add by jjpei 2020-01-07 风险新版本 start----------
            //---赎回申请不需要风险试算----
            if($("#ps").val()==ps_T06){
                return;
            }
            var object = $.serializeToObject('#submitForm', true);
            object.finprodId = $('#finprodId').val();
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/1/RSK002/F04', object,getriskdata());
            //-----------add by jjpei 2020-01-07 风险新版本 end----------
            window.BaseRiskMathComm.initBaseRiskMathCommPageCus(getriskdata());

        })
        //是否展示资产四五级分类
        if(IS_SHOW_TYPE == '01'){
            $('#selector5').show();
        }else{
            $('#selector5').hide();
        }
    });
}());