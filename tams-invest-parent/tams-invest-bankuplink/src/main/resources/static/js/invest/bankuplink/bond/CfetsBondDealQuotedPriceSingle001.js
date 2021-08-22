(function(){


    var setDhDiv = function(selArr){
        $("#CfetsBondDealQuotedPriceSingle001_hidden_quotaPageModel").val('new');
        if(selArr&&selArr[0]){
            $("#CfetsBondDealQuotedPriceSingle001_entrustId").val(selArr[0].entrustId).trigger('change');
            // $("#CfetsBondDealQuotedPriceSingle001_finprodId").textSelect('setValue',selArr[0].finprodMarketId);
            // $("#CfetsBondDealQuotedPriceSingle001_portfolioId").textSelect('setValue',selArr[0].portfolioId);
            // $("#CfetsBondDealQuotedPriceSingle001_ps").val(selArr[0].ps).trigger('change');
            // $("#CfetsBondDealQuotedPriceSingle001_avaNum").val(selArr[0].entrustNum).trigger('change');
        }
    }

    /**
     * 委托代码
     */
    $("#CfetsBondDealQuotedPriceSingle001_entrustId").change(function () {
        var entrustId = $('#CfetsBondDealQuotedPriceSingle001_entrustId').val();
        if (entrustId && entrustId != ''){
            Joyin.ajax(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle001/queryEntrustInfoByEntrustId', {entrustId : entrustId}, function(result) {
                if (result.data){
                    $('#CfetsBondDealQuotedPriceSingle001_remark').val(result.data.remark);
                    $('#CfetsBondDealQuotedPriceSingle001_hidden_entrustAmt').val(result.data.entrustNum);
                    $('#CfetsBondDealQuotedPriceSingle001_hidden_finprodId').val(result.data.finprodId);
                    // 区分画面打开模式
                    var quotaPageModel = $('#CfetsBondDealQuotedPriceSingle001_hidden_quotaPageModel').val();

                    // 回复对话报价
                    if (quotaPageModel && (quotaPageModel == 'dialogueResponse')){
                        $('#CfetsBondDealQuotedPriceSingle001_portfolioId').textSelect('setValue',result.data.portfolioId);
                    }
                    else {
                        // 做市方回复
                        if (quotaPageModel != 'marketResponse'){
                            // 这些要素应该是做市方已有的信息
                            $('#CfetsBondDealQuotedPriceSingle001_mySide').textSelect('setValue',result.data.mySide);
                            $('#CfetsBondDealQuotedPriceSingle001_counterId').textSelect('setValue',result.data.counterId);
                            $('#CfetsBondDealQuotedPriceSingle001_counterTrader').textSelect('setValue',result.data.counterTrader);
                            $('#CfetsBondDealQuotedPriceSingle001_finprodId').textSelect('setValue',result.data.finprodMarketId);
                            $('#CfetsBondDealQuotedPriceSingle001_ps').val(result.data.ps).trigger('change');
                            $('#CfetsBondDealQuotedPriceSingle001_avaNum').val(result.data.avaNumStr).trigger('blur');
                            $('#CfetsBondDealQuotedPriceSingle001_settleSpreed').val(result.data.settleSpreed).trigger('change');
                            $('#CfetsBondDealQuotedPriceSingle001_settleDate').val(result.data.settleDateStr);
                        }else{
                            // 做市报价回复选择委托后回复量默认显示委托剩余额度
                            $('#CfetsBondDealQuotedPriceSingle001_responseAmt').val(result.data.avaNumStr).trigger('blur');
                        }
                        // 对话报价发起模式
                        $('#CfetsBondDealQuotedPriceSingle001_portfolioId').textSelect('setValue',result.data.portfolioId);
                        $('#CfetsBondDealQuotedPriceSingle001_finprodId').textSelect('setValue',result.data.finprodMarketId);
                        $('#CfetsBondDealQuotedPriceSingle001_mySide').textSelect('setValue',result.data.mySide);
                        $('#CfetsBondDealQuotedPriceSingle001_mySideTrader').textSelect('setValue',result.data.mySideTrader);
                        $('#CfetsBondDealQuotedPriceSingle001_settleMethod').val(result.data.settleMethod).trigger('change');
                        $('#CfetsBondDealQuotedPriceSingle001_settleType').val(result.data.settleType).trigger('change');
                        $('#CfetsBondDealQuotedPriceSingle001_settleCcy').val(result.data.settleCcy).trigger('change');
                        $('#CfetsBondDealQuotedPriceSingle001_unitCprice').val(result.data.price).trigger('change');
                        $('#CfetsBondDealQuotedPriceSingle001_matureYield').val(result.data.matureYield).trigger('blur');
                        $('#CfetsBondDealQuotedPriceSingle001_exerYield').val(result.data.exerYield).trigger('blur');
                        $('#CfetsBondDealQuotedPriceSingle001_unitInt').val(result.data.unitInt).trigger('blur');
                        $('#CfetsBondDealQuotedPriceSingle001_unitFprice').val(result.data.unitFprice).trigger('blur');
                        $('#CfetsBondDealQuotedPriceSingle001_cpriceAmt').val(result.data.cpriceAmt).trigger('blur');
                        $('#CfetsBondDealQuotedPriceSingle001_parValue').val(result.data.parValue).trigger('blur');
                        $('#CfetsBondDealQuotedPriceSingle001_intAmt').val(result.data.intAmt).trigger('blur');
                        $('#CfetsBondDealQuotedPriceSingle001_fpriceAmt').val(result.data.fpriceAmt).trigger('blur');
                        $('#CfetsBondDealQuotedPriceSingle001_parValueAmt').val(result.data.parValueAmt).trigger('blur');
                        $('#CfetsBondDealQuotedPriceSingle001_secManageAcctId').textSelect('setValue',result.data.secManageAcctId);

                        // $('#CfetsBondDealQuotedPriceSingle001_portfolioId').attr('disabled', true);
                        // $('#CfetsBondDealQuotedPriceSingle001_ps').attr('disabled', true);
                        // $('#CfetsBondDealQuotedPriceSingle001_finprodId').attr('disabled', true);
                    }
                }
            });
        }
        // else {
        //     $('#CfetsBondDealQuotedPriceSingle001_remark').val("");
        //     var quotaPageModel = $('#CfetsBondDealQuotedPriceSingle001_hidden_quotaPageModel').val();
        //     //add by yzg 20210116 委托代码清空联动清空 start
        //     if(!quotaPageModel || (quotaPageModel != 'dialogueResponse')){
        //         $('#CfetsBondDealQuotedPriceSingle001_hidden_entrustAmt').val('');
        //         $('#CfetsBondDealQuotedPriceSingle001_hidden_finprodId').val('');
        //         $('#CfetsBondDealQuotedPriceSingle001_mySide').val('').trigger('change');
        //         $('#CfetsBondDealQuotedPriceSingle001_counterId').val('').trigger('change');
        //         $('#CfetsBondDealQuotedPriceSingle001_counterTrader').textSelect('setValue','');
        //         $('#CfetsBondDealQuotedPriceSingle001_finprodId').attr('disabled', false).val('').trigger('change');
        //         $('#CfetsBondDealQuotedPriceSingle001_ps').val('').trigger('change').attr('disabled', false);
        //         $('#CfetsBondDealQuotedPriceSingle001_settleSpreed').val('01').trigger('change').attr('disabled', false);
        //         $('#CfetsBondDealQuotedPriceSingle001_settleDate').val('');
        //         $('#CfetsBondDealQuotedPriceSingle001_avaNum').val('').trigger('blur');
        //         $('#CfetsBondDealQuotedPriceSingle001_responseAmt').val('').trigger('blur');
        //         $('#CfetsBondDealQuotedPriceSingle001_portfolioName').attr('disabled', true).val('').trigger('change');
        //         $('#CfetsBondDealQuotedPriceSingle001_mySideTrader').val('');
        //         $('#CfetsBondDealQuotedPriceSingle001_settleMethod').val('03').trigger('change');
        //         $('#CfetsBondDealQuotedPriceSingle001_settleType').val('01').trigger('change');
        //         $('#CfetsBondDealQuotedPriceSingle001_div_settleCcy').val('CNY').trigger('change');
        //         $('#CfetsBondDealQuotedPriceSingle001_unitCprice').val('').trigger('blur').attr('disabled', false);;
        //         $('#CfetsBondDealQuotedPriceSingle001_matureYield').val('').trigger('blur').attr('disabled', false);;
        //         $('#CfetsBondDealQuotedPriceSingle001_exerYield').val('').trigger('blur');
        //         $('#CfetsBondDealQuotedPriceSingle001_unitInt').val('').trigger('blur');
        //         $('#CfetsBondDealQuotedPriceSingle001_unitFprice').val('').trigger('blur');
        //         $('#CfetsBondDealQuotedPriceSingle001_cpriceAmt').val('').trigger('blur');
        //         $('#CfetsBondDealQuotedPriceSingle001_parValue').val('').trigger('blur');
        //         $('#CfetsBondDealQuotedPriceSingle001_intAmt').val('').trigger('blur');
        //         $('#CfetsBondDealQuotedPriceSingle001_fpriceAmt').val('').trigger('blur');
        //         $('#CfetsBondDealQuotedPriceSingle001_parValueAmt').val('').trigger('blur');
        //     }else if(quotaPageModel == 'dialogueResponse'){
        //         $('#CfetsBondDealQuotedPriceSingle001_portfolioName').val('').trigger('change');
        //     }
        //     $('#CfetsBondDealQuotedPriceSingle001_settleDate').val(platDate);
        // }
    });


    $("#CfetsBondDealQuotedPriceSingle001_portfolioId").change(function () {
        var portfolioId = $("#CfetsBondDealQuotedPriceSingle001_portfolioId").val();
        var vdate = $('#CfetsBondDealQuotedPriceSingle001_tradeDate').val();
        var ccy= $("#CfetsBondDealQuotedPriceSingle001_settleCcy").val();
        if(window.EtrInitCash){
            window.EtrInitCash.queryCash(ccy,vdate,portfolioId);
        }
    });

    $("#CfetsBondDealQuotedPriceSingle001_finprodId").change(function () {
        var chlFinprodId = '';
        var finprodIdhid = '';
        var ccy =  '';

        var data = $('#CfetsBondDealQuotedPriceSingle001_finprodId').select2('data')[0].object;
        if(data){
            finprodIdhid = data.finprodId;
            ccy = data.ccy;
            $('#CfetsBondDealQuotedPriceSingle001_hidden_finprodId').val(data.finprodId);
            $("#CfetsBondDealQuotedPriceSingle001_settleCcy").val(data.ccy);
        }else{
            $('#CfetsBondDealQuotedPriceSingle001_hidden_finprodId').val("");
            $("#CfetsBondDealQuotedPriceSingle001_settleCcy").val(CCY_CNY);
        }
        // $("#CfetsBondDealQuotedPriceSingle001_finprodId").val();
        var portfolioId = $("#CfetsBondDealQuotedPriceSingle001_portfolioId").val();
        var sysDateT1 = $('#CfetsBondDealQuotedPriceSingle001_tradeDate').val();
        var calId = '';
        if (todoList == false&&parent.parent.EtrIbBondinfoHq) {
            parent.parent.EtrIbBondinfoHq.querySecinfo(finprodIdhid,portfolioId,chlFinprodId,ccy,sysDateT1,calId);
        }
        if (data&&data.assetType1 == assetTypeOne_abs){
            $('#CfetsBondDealQuotedPriceSingle001_div_abs1').toggle(true);
            $('#CfetsBondDealQuotedPriceSingle001_div_abs2').toggle(true);
            layui.form.render();
        }else{
            $('#CfetsBondDealQuotedPriceSingle001_div_abs1').toggle(false);
            $('#CfetsBondDealQuotedPriceSingle001_div_abs2').toggle(false);
        }
    });

    /**
     * 单位净价
     */
    $('#CfetsBondDealQuotedPriceSingle001_unitCprice').on('change',function(){
        // 根据净价计算净价偏离度
        calDeviationDegree();
        var portfolioIds = [];
        portfolioIds.push($("#CfetsBondDealQuotedPriceSingle001_portfolioId").val());
        if(parent.parent.EtrIbBondinfoHq){
            parent.parent.EtrIbBondinfoHq.calCanBuyAmount(portfolioIds,$('#CfetsBondDealQuotedPriceSingle001_settleCcy').val(),
                $('#CfetsBondDealQuotedPriceSingle001_tradeDate').val(),$('#CfetsBondDealQuotedPriceSingle001_unitCprice').val());
        }
        calcAmt('01');
    });


    $('#CfetsBondDealQuotedPriceSingle001_avaNum').on('change',function(){
        calcAmt('01');
    });

    /**
     * 净值偏离度
     */
    function calDeviationDegree() {
        document.getElementById('deviationDegree').innerHTML = "<span style='color: red'>"+
            '0.0000%' +"</span>";
        if(!$("#CfetsBondDealQuotedPriceSingle001_unitCprice").val()||!$("#CfetsBondDealQuotedPriceSingle001_hidden_finprodId").val()
            ||!$('#CfetsBondDealQuotedPriceSingle001_tradeDate').val()){
            return;
        }
        Joyin.ajaxJson(basePath + "invest/entrust/etribbondinfo/EtrIbBondinfo002/calDeviationDegree",
            {
                price: $("#CfetsBondDealQuotedPriceSingle001_unitCprice").val(),
                finprodId: $("#CfetsBondDealQuotedPriceSingle001_hidden_finprodId").val(),
                vdate:$('#CfetsBondDealQuotedPriceSingle001_tradeDate').val()
            }, function (result) {
                if (result ) {
                    document.getElementById('deviationDegree').innerHTML ="<span style='color: red'>"+
                        JoyinUtil.formatNumber(result, 4, null, 2) + '%' +"</span>";
                }
            });
    }

    /**
     * 清算速度
     */
    $('#CfetsBondDealQuotedPriceSingle001_settleSpreed').on('change',function(){
        var settleSpreed = $('#CfetsBondDealQuotedPriceSingle001_settleSpreed').val();
        if (settleSpreed == ACCOUNT_SPEED_1 || settleSpreed == ACCOUNT_SPEED_2){
            Joyin.ajax(basePath + 'invest/bankuplink/common/CfetsQuotedCommon/getNextWorkDay', {
                'tradeDate': platDate,
                'settleSpreed': settleSpreed
            }, function (date) {
                $("#CfetsBondDealQuotedPriceSingle001_settleDate").val(date).trigger("change");
                calcAmt("01");
            });
        }else{
            $('#CfetsBondDealQuotedPriceSingle001_settleDate').val(platDate);
            calcAmt("01");
        }
    });


    /**
     * 计算金额相关
     */
    var calcAmt = function(calculateField){
        var finprodId;
        finprodId = $('#CfetsBondDealQuotedPriceSingle001_hidden_finprodId').val();
        if(!finprodId){
            return;
        }

        if (calculateField){
            if (calculateField === calcTypeUnitCprice && !$('#CfetsBondDealQuotedPriceSingle001_unitCprice').val()){
                return;
            }
            if (calculateField === calcTypeYield && !$('#CfetsBondDealQuotedPriceSingle001_matureYield').val()){
                return;
            }
            if (calculateField === calcTypeExecYield && !$('#CfetsBondDealQuotedPriceSingle001_exerYield').val()){
                return;
            }


            var object = $.serializeToObject('#submitForm', true);
            object.calculateField = calculateField;
            object.finprodId = finprodId;
            object.avaNum = new BigNumber(object.avaNum).multipliedBy(new BigNumber(10000));
            object.settleDate = object.tradeDate;

            Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle001/calcAmt', object, function(result){
                if (result.data){
                    if (calculateField == calcTypeUnitCprice){
                        if(pageModel != PAGE_MODEL_REFERENCE){
                            $('#CfetsBondDealQuotedPriceSingle001_matureYield').val(result.data.matureYield).trigger('blur');
                        }
                        $('#CfetsBondDealQuotedPriceSingle001_exerYield').val(result.data.exerYield).trigger('blur');
                    }else if (calculateField == calcTypeYield){
                        $('#CfetsBondDealQuotedPriceSingle001_unitCprice').val(result.data.price).trigger('blur');
                        $('#CfetsBondDealQuotedPriceSingle001_exerYield').val(result.data.exerYield).trigger('blur');
                    }else if (calculateField == calcTypeExecYield){
                        $('#CfetsBondDealQuotedPriceSingle001_unitCprice').val(result.data.price).trigger('blur');
                        if(pageModel != PAGE_MODEL_REFERENCE){
                            $('#CfetsBondDealQuotedPriceSingle001_matureYield').val(result.data.matureYield).trigger('blur');
                        }
                    }else{
                        $('#CfetsBondDealQuotedPriceSingle001_unitCprice').val(result.data.price).trigger('blur');
                        if(pageModel != PAGE_MODEL_REFERENCE){
                            $('#CfetsBondDealQuotedPriceSingle001_matureYield').val(result.data.matureYield).trigger('blur');
                        }
                        $('#CfetsBondDealQuotedPriceSingle001_exerYield').val(result.data.exerYield).trigger('blur');
                    }
                    $('#CfetsBondDealQuotedPriceSingle001_unitInt').val(result.data.unitInt).trigger('blur');
                    $('#CfetsBondDealQuotedPriceSingle001_unitFprice').val(result.data.unitFprice).trigger('blur');
                    $('#CfetsBondDealQuotedPriceSingle001_cpriceAmt').val(result.data.cpriceAmt).trigger('blur');
                    $('#CfetsBondDealQuotedPriceSingle001_parValue').val(result.data.parValue).trigger('blur');
                    $('#CfetsBondDealQuotedPriceSingle001_intAmt').val(result.data.intAmt).trigger('blur');
                    $('#CfetsBondDealQuotedPriceSingle001_fpriceAmt').val(result.data.fpriceAmt).trigger('blur');
                    $('#CfetsBondDealQuotedPriceSingle001_parValueAmt').val(result.data.parValueAmt).trigger('blur');
                    // entrustIdChangeFlag = false;
                }
            })
        }
    };
    /**
     * 本方
     */
    $('#CfetsBondDealQuotedPriceSingle001_mySide').change(function(){
        $('#CfetsBondDealQuotedPriceSingle001_mySideTrader').val('').trigger('change');
    });
    /**
     * 对手方方
     */
    $('#CfetsBondDealQuotedPriceSingle001_counterId').change(function(){
        $('#CfetsBondDealQuotedPriceSingle001_counterTrader').val('').trigger('change');
    });

    var reset = function () {
        $("#CfetsBondDealQuotedPriceSingle001_entrustId").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceSingle001_portfolioId").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceSingle001_finprodId").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceSingle001_ps").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceSingle001_settleType").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceSingle001_settleSpreed").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceSingle001_tradeDate").val(sysDate).trigger("change");
        $("#CfetsBondDealQuotedPriceSingle001_settleDate").val(sysDate).trigger("change");
        $("#CfetsBondDealQuotedPriceSingle001_responseAmt").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceSingle001_settleMethod").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceSingle001_secManageAcctId").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceSingle001_avaNum").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceSingle001_unitCprice").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceSingle001_unitInt").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceSingle001_unitFprice").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceSingle001_intAmt").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceSingle001_exerYield").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceSingle001_matureYield").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceSingle001_fpriceAmt").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceSingle001_parValue").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceSingle001_parValueAmt").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceSingle001_mySide").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceSingle001_mySideTrader").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceSingle001_counterId").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceSingle001_counterTrader").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceSingle001_remark").val(sysDate).trigger("change");
        layui.form.render(); // reload layui
        pageModel = PAGE_MODEL_NEW;
    };


    var getriskdata = function () {
        return null;
    };

    function getSaveOrSubmitParams(operateType) {
        var object = $.serializeToObject("#submitForm");
        if(!object.finprodId){
            object.finprodId = $('#CfetsBondDealQuotedPriceSingle001_hidden_finprodId').val();
        }
        object.operatorType = operateType;
        return object;
    }

    parent.CfetsBondDealQuotedPriceSingle001 = {
        setDhDiv: setDhDiv,
        getriskdata: getriskdata,
        getSaveOrSubmitParams: getSaveOrSubmitParams,
        reset: reset
    }

    window.componentLoadCallback = function(isDone, pagePath, params) {
        if (isDone) {
            calDeviationDegree();
            var portfolioId = $("#CfetsBondDealQuotedPriceSingle001_portfolioId").val();
            var vdate = $('#CfetsBondDealQuotedPriceSingle001_tradeDate').val();
            var ccy= $("#CfetsBondDealQuotedPriceSingle001_settleCcy").val();
            if(window.EtrInitCash){
                window.EtrInitCash.queryCash(ccy,vdate,portfolioId);
            }
        }
    };


}());