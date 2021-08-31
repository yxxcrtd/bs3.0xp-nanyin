(function(){
    //试算结果
    var riskResultdata;
    var cluflag;

    /**
     * 控制显示行权收益率
     */
    var exerYieldDivContrl = function() {
        // 是否含权债为是，且未来有行权计划时，行权收益率显示，其余情况不显示；
        var param = {};
        param.finprodId = $('#CfetsBondDealQuotedPriceSingle001_hidden_finprodId').val();
        param.tradeDate = $('#CfetsBondDealQuotedPriceSingle001_settleDate').val();
        if(param.finprodId&&param.tradeDate){
            Joyin.ajaxSync(basePath + 'invest/trade/bond/TrdBondInvestTrade002/isExerYieldDivShow', param, function (data) {
                if (data == yesNoFlag_yes) {
                    $("#CfetsBondDealQuotedPriceSingle001_div_exerYield").show();
                } else {
                    $("#CfetsBondDealQuotedPriceSingle001_div_exerYield").hide();
                }
            });
        }
    };


    $(function () {
        // 初始话隐藏行权收益率
        $("#CfetsBondDealQuotedPriceSingle001_div_exerYield").hide();
        // 委托代码
        var entrustId = $('#CfetsBondDealQuotedPriceSingle001_hidden_entrustId').val();
        if (entrustId && entrustId != ''){
            $('#CfetsBondDealQuotedPriceSingle001_entrustId').textSelect('setValue',entrustId);
            $('#CfetsBondDealQuotedPriceSingle001_entrustId').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_portfolioName').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_ps').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_finprodName').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_settleSpreed').attr('disabled', true);
            //add by lijie2
            if(PRICE_MODEL && PRICE_MODEL == PRICE_MODEL_PRM01){
                $('#CfetsBondDealQuotedPriceSingle001_unitCprice').attr('disabled', true);
                $('#CfetsBondDealQuotedPriceSingle001_matureYield').attr('disabled', true);
                $('#CfetsBondDealQuotedPriceSingle001_exerYield').attr('disabled', true);
                $("input[name='calculateField']").attr('disabled', true);
                $("input[name=calculateField][value=01]").prop("checked","true");
                layui.form.render();
            }else if(assetTypeOne == assetTypeOne_abs){
                $('#CfetsBondDealQuotedPriceSingle001_matureYield').attr('disabled', true);
                $('#CfetsBondDealQuotedPriceSingle001_exerYield').attr('disabled', true);
                $("input[name='calculateField']").attr('disabled', true);
                $("#CfetsBondDealQuotedPriceSingle001_hidden_calculateField").val("01");
                $("input[name=calculateField][value=01]").prop("checked","true");
                layui.form.render();
            }
            editFlag = false;
        }
        // 画面操作模式
        var quotaPageModel = $('#CfetsBondDealQuotedPriceSingle001_hidden_quotaPageModel').val();
        // 做市报价回复
        if (quotaPageModel && quotaPageModel == 'marketResponse'){
            $('#CfetsBondDealQuotedPriceSingle001_div_responseAmt').toggle(true);
            $('#CfetsBondDealQuotedPriceSingle001_div_settleCcy').toggle(false);
            if (ibuFlag_startDialogueQuoteWithNoEntrust != '02'){
                $('#CfetsBondDealQuotedPriceSingle001_portfolioName').attr('disabled', true);
            }
            $('#CfetsBondDealQuotedPriceSingle001_ps').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_mySide').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_counterId').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_counterTrader').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_finprodName').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_finprodMarketId').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_settleSpreed').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_settleDate').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_avaNum').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_settleMethod').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_settleType').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_btn_done').toggle(false);
            $('#CfetsBondDealQuotedPriceSingle001_btn_refuse').toggle(false);
            $('#CfetsBondDealQuotedPriceSingle001_btn_save').toggle(false);
            $('#CfetsBondDealQuotedPriceSingle001_btn_submit').toggle(true);
            $('#CfetsBondDealQuotedPriceSingle001_btn_cancel').toggle(true);
        }
        // 对话报价回复(除委托代码外置灰)
        else if (quotaPageModel && quotaPageModel == 'dialogueResponse'){
            $('#CfetsBondDealQuotedPriceSingle001_div_settleCcy').toggle(true);
            $('#CfetsBondDealQuotedPriceSingle001_btn_done').toggle(true);
            $('#CfetsBondDealQuotedPriceSingle001_btn_refuse').toggle(true);
            $('#CfetsBondDealQuotedPriceSingle001_btn_save').toggle(false);
            $('#CfetsBondDealQuotedPriceSingle001_btn_submit').toggle(false);
            $('#CfetsBondDealQuotedPriceSingle001_btn_cancel').toggle(false);
            if (ibuFlag_startDialogueQuoteWithNoEntrust != '02'){
                $('#CfetsBondDealQuotedPriceSingle001_portfolioName').attr('disabled', true);
            }
            $('#CfetsBondDealQuotedPriceSingle001_mySideTrader').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_ps').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_mySide').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_counterId').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_counterTrader').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_finprodName').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_settleSpreed').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_unitCprice').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_matureYield').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_exerYield').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_avaNum').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_settleMethod').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_settleType').attr('disabled', true);
            //无指令时，组合可改
            if (ibuFlag_startDialogueQuoteWithNoEntrust == '02'){
                var mySideTemp = $('#CfetsBondDealQuotedPriceSingle001_mySide').val();
                if(mySideTemp){
                    $('#CfetsBondDealQuotedPriceSingle001_responseMySide').val(mySideTemp);
                }
            }
        }
        // 对话报价发起和保存后双击
        else if (quotaPageModel && quotaPageModel == 'new'){
            $('#CfetsBondDealQuotedPriceSingle001_div_responseAmt').toggle(false);
            $('#CfetsBondDealQuotedPriceSingle001_div_settleCcy').toggle(true);
            if (mySideTrader && mySideTrader != ''){
                $('#CfetsBondDealQuotedPriceSingle001_mySideTrader').textSelect('setValue',mySideTrader);
            }
            if (counterTrader && counterTrader != ''){
                $('#CfetsBondDealQuotedPriceSingle001_counterTrader').textSelect('setValue',counterTrader);
            }
            if (ouTraderIdCanEditFlag === 'N'){
                $('#CfetsBondDealQuotedPriceSingle001_mySideTrader').attr('disabled', true);
            }
            $('#CfetsBondDealQuotedPriceSingle001_btn_done').toggle(false);
            $('#CfetsBondDealQuotedPriceSingle001_btn_refuse').toggle(false);
            $('#CfetsBondDealQuotedPriceSingle001_btn_save').toggle(false);
            $('#CfetsBondDealQuotedPriceSingle001_btn_submit').toggle(false);
            $('#CfetsBondDealQuotedPriceSingle001_btn_cancel').toggle(false);
        }// 对话报价发起和保存后双击
        else if (quotaPageModel && quotaPageModel == 'edit'){
            $('#CfetsBondDealQuotedPriceSingle001_div_settleCcy').toggle(true);
            $('#CfetsBondDealQuotedPriceSingle001_mySide').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_mySideTrader').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_counterId').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_counterTrader').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_settleType').attr('disabled', false);
            $('#CfetsBondDealQuotedPriceSingle001_remark').attr('disabled', true);
            //修改无委托初始化增加不可修改项 add by yzg 20210123 start
            $('#CfetsBondDealQuotedPriceSingle001_entrustId').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_portfolioName').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_ps').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_finprodName').attr('disabled', true);
            //修改无委托初始化增加不可修改项 add by yzg 20210123 end
            $('#CfetsBondDealQuotedPriceSingle001_btn_done').toggle(false);
            $('#CfetsBondDealQuotedPriceSingle001_btn_refuse').toggle(false);
            $('#CfetsBondDealQuotedPriceSingle001_btn_save').toggle(true);
            $('#CfetsBondDealQuotedPriceSingle001_btn_submit').toggle(true);
            $('#CfetsBondDealQuotedPriceSingle001_btn_cancel').toggle(true);
        }
        exerYieldDivContrl();
        // 含权、资产一级类型
        var isRight = $('#CfetsBondDealQuotedPriceSingle001_hidden_isRight').val();

        var assetType1 = $('#CfetsBondDealQuotedPriceSingle001_hidden_assetTypeOne').val();
        if (assetType1 == assetTypeOne_abs){
            $('#CfetsBondDealQuotedPriceSingle001_div_abs1').toggle(true);
            $('#CfetsBondDealQuotedPriceSingle001_div_abs2').toggle(true);
        }else{
            $('#CfetsBondDealQuotedPriceSingle001_div_abs1').toggle(false);
            $('#CfetsBondDealQuotedPriceSingle001_div_abs2').toggle(false);
        }

    });
    
    var setDhDiv = function(selArr){
        $("#CfetsBondDealQuotedPriceSingle001_hidden_quotaPageModel").val('new');
        if(selArr&&selArr[0]){
            $("#CfetsBondDealQuotedPriceSingle001_entrustId").val(selArr[0].entrustId).trigger('change');
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
                    }
                    exerYieldDivContrl();
                }
            });
        }
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
        calcAmt();
    });

    /**
     * 到期收益率
     */
    $('#CfetsBondDealQuotedPriceSingle001_matureYield').on('change',function(){
        calcAmt('02');
    });
    /**
     * 行权收益率
     */
    $('#CfetsBondDealQuotedPriceSingle001_exerYield').on('change',function(){
        calcAmt('03');
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
        var calcField = $("[name=calculateField]:checked").val();
        finprodId = $('#CfetsBondDealQuotedPriceSingle001_hidden_finprodId').val();
        if(!finprodId){
            return;
        }
        if (calculateField && calculateField != ''){
            if (calculateField != calcField){
                return;
            }
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

    /**
     * 保存按钮按下处理
     */
    $('#CfetsBondDealQuotedPriceSingle001_btn_save').click(function(){
        saveOrSubmitAction('save');
    });

    /**
     * 提交按钮按下处理
     */
    $('#CfetsBondDealQuotedPriceSingle001_btn_submit').click(function(){
        saveOrSubmitAction('submit');
    });

    /**
     * 成交
     */
    $('#CfetsBondDealQuotedPriceSingle001_btn_done').click(function(){
        saveOrSubmitAction('done');
    });

    /**
     * 拒绝
     */
    $('#CfetsBondDealQuotedPriceSingle001_btn_refuse').click(function(){
        saveOrSubmitAction('refuse');
    });

    /**
     * 保存
     */
    var saveOrSubmitAction = function(operatorType) {
        var object = $.serializeToObject('#submitForm', true);
        if(!object.calculateField){
            object.calculateField = $("#CfetsBondDealQuotedPriceSingle001_hidden_calculateField").val();
        }
        if(!object.finprodId){
            object.finprodId = $("#CfetsBondDealQuotedPriceSingle001_hidden_finprodId").val();
        }
        object.operatorType = operatorType;
        Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle001/check', object,
            function(result) {
                if (result.data) {
                    Joyin.confirm(result.data,
                        function() {
                            //保存 走流程
                            if (operatorType === 'save') {
                                WorkFlow.doSaveList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle001/save', object, "CfetsBondDealQuotedPriceSingle001_btn_save", null,
                                    function() {
                                        var index = window.parent.layer.getFrameIndex(window.name);
                                        window.parent.layer.close(index);
                                    })
                            }
                            //成交 走流程
                            else if (operatorType === 'done') {
                                WorkFlow.doSubmitList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle001/save', object, "CfetsBondDealQuotedPriceSingle001_btn_done", null,
                                    function() {
                                        var index = window.parent.layer.getFrameIndex(window.name);
                                        window.parent.layer.close(index);
                                    })
                            }
                            //提交 走流程
                            else if (operatorType === 'submit') {
                                WorkFlow.doSubmitList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle001/save', object, "CfetsBondDealQuotedPriceSingle001_btn_submit", null,
                                    function() {
                                        var index = window.parent.layer.getFrameIndex(window.name);
                                        window.parent.layer.close(index);
                                    })
                            }
                            //拒绝 不走流程
                            else if (operatorType === 'refuse'){
                                Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle001/saveData', object, function(result){
                                    var index = window.parent.layer.getFrameIndex(window.name);
                                    window.parent.layer.close(index);
                                })
                            }
                        })
                }
            });
    };


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
        var obj = {
            tradeDirection: $("#CfetsBondDealQuotedPriceSingle001_ps").val(),
            timePoint: MY_SUP_TIME_TYPE_02,
            valDate: sysDate,
            portfolioCode: $("#CfetsBondDealQuotedPriceSingle001_portfolioId").val(),
            tradeAmount: $("#CfetsBondDealQuotedPriceSingle001_fpriceAmt").val(),
            assetType: FINPROD_TYPE_F01,
            isEnabled: "01",
            assetCode: $("#CfetsBondDealQuotedPriceSingle001_finprodId").val(),
            counterId: $('#CfetsBondDealQuotedPriceSingle001_counterId').val()
        };

        var object = {
            tradeDirection: ["ps", "CfetsBondDealQuotedPriceSingle.ps"],
            portfolioCode: ["portfolioId", "CfetsBondDealQuotedPriceSingle.portfolioId"],
            tradeAmount: ["fpriceAmt", "CfetsBondDealQuotedPriceSingle.fprinceAmt"],
            assetType: ["assetType", "CfetsBondDealQuotedPriceSingle.assetType"],
            counterId: ["counterId", "CfetsBondDealQuotedPriceSingle.counterId"],
            data: obj
        };
        return object;
    };

    function saveRiskResultdata(paraRiskResultdata) {
        riskResultdata = paraRiskResultdata;
    }
    function saveCluflag(paraCluflag) {
        cluflag = paraCluflag;
    }

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
        reset: reset,
        riskResultdata: riskResultdata,
        cluflag: cluflag,
        saveRiskResultdata: saveRiskResultdata,
        saveCluflag: saveCluflag
    }
    CfetsBondDealQuotedPriceSingle001 = {
        setDhDiv: setDhDiv,
        getriskdata: getriskdata,
        getSaveOrSubmitParams: getSaveOrSubmitParams,
        reset: reset,
        riskResultdata: riskResultdata,
        cluflag: cluflag,
        saveRiskResultdata: saveRiskResultdata,
        saveCluflag: saveCluflag
    };

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