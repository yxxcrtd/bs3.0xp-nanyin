(function(){
    //试算结果
    var riskResultdata;
    var cluflag;
    /**
     * 是否可编辑
     */
    var editFlag = true;
    var entrustIdChangeFlag = false;
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

    /**
     * 委托代码
     */
    var entrustIdChangeAction = function(){
        var entrustId = $('#CfetsBondDealQuotedPriceSingle001_entrustId').val();
        if (entrustId && entrustId != ''){
            Joyin.ajax(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle001/queryEntrustInfoByEntrustId', {entrustId : entrustId}, function(result) {
                if (result.data){
                    entrustIdChangeFlag = true;
                    $('#CfetsBondDealQuotedPriceSingle001_remark').val(result.data.remark);
                    $('#CfetsBondDealQuotedPriceSingle001_hidden_entrustAmt').val(result.data.entrustNum);
                    $('#CfetsBondDealQuotedPriceSingle001_hidden_finprodId').val(result.data.finprodId);
                    // 区分画面打开模式
                    var quotaPageModel = $('#CfetsBondDealQuotedPriceSingle001_hidden_quotaPageModel').val();

                    if (result.data.calculateField){
                        if (result.data.calculateField === calcTypeUnitCprice){
                            $("input[name=calculateField][value=01]").prop("checked","true");
                            $('#calculateField_span_unitCprice').css({'color':'blue'});
                            $('#calculateField_span_yield').css({'color':'black'});
                            $('#calculateField_span_exerYield').css({'color':'black'});
                        }else if (result.data.calculateField === calcTypeYield){
                            $("input[name=calculateField][value=02]").prop("checked","true");
                            $('#calculateField_span_yield').css({'color':'blue'});
                            $('#calculateField_span_unitCprice').css({'color':'black'});
                            $('#calculateField_span_exerYield').css({'color':'black'});
                        }else if (result.data.calculateField === calcTypeExecYield){
                            $("input[name=calculateField][value=03]").prop("checked","true");
                            $('#calculateField_span_exerYield').css({'color':'blue'});
                            $('#calculateField_span_unitCprice').css({'color':'black'});
                            $('#calculateField_span_yield').css({'color':'black'});
                        }else{
                            $("input[name=calculateField][value=01]").prop("checked","true");
                            $('#calculateField_span_unitCprice').css({'color':'blue'});
                            $('#calculateField_span_yield').css({'color':'black'});
                            $('#calculateField_span_exerYield').css({'color':'black'});
                        }
                        layui.form.render();
                    }

                    // 回复对话报价
                    if (quotaPageModel && (quotaPageModel == 'dialogueResponse')){
                        $('#CfetsBondDealQuotedPriceSingle001_portfolioName').attr('disabled', true).textSelect('setValue',result.data.portfolioId);
                    }
                    else {
                        // 做市方回复  
                        if (quotaPageModel != 'marketResponse'){
                            // 这些要素应该是做市方已有的信息
                            $('#CfetsBondDealQuotedPriceSingle001_mySide').textSelect('setValue',result.data.mySide);
                            $('#CfetsBondDealQuotedPriceSingle001_counterId').textSelect('setValue',result.data.counterId);
                            $('#CfetsBondDealQuotedPriceSingle001_counterTrader').textSelect('setValue',result.data.counterTrader);
                            $('#CfetsBondDealQuotedPriceSingle001_finprodId').attr('disabled', true).textSelect('setValue',result.data.finprodMarketId);
                            $('#CfetsBondDealQuotedPriceSingle001_ps').val(result.data.ps).trigger('change').attr('disabled', true);
                            $('#CfetsBondDealQuotedPriceSingle001_settleSpreed').val(result.data.settleSpreed).trigger('change').attr('disabled', true);
                            $('#CfetsBondDealQuotedPriceSingle001_settleDate').val(result.data.settleDateStr).attr('disabled', true);
                            $('#CfetsBondDealQuotedPriceSingle001_avaNum').val(result.data.avaNumStr).trigger('blur');
                        }else{
                            // 做市报价回复选择委托后回复量默认显示委托剩余额度
                            $('#CfetsBondDealQuotedPriceSingle001_responseAmt').val(result.data.avaNumStr).trigger('blur');
                        }
                        // 对话报价发起模式
                        $('#CfetsBondDealQuotedPriceSingle001_portfolioId').attr('disabled', true).textSelect('setValue',result.data.portfolioId);
                        $('#CfetsBondDealQuotedPriceSingle001_mySide').textSelect('setValue',result.data.mySide);
                        $('#CfetsBondDealQuotedPriceSingle001_mySideTrader').textSelect('setValue',result.data.mySideTrader);
                        $('#CfetsBondDealQuotedPriceSingle001_settleMethod').val(result.data.settleMethod).trigger('change');
                        $('#CfetsBondDealQuotedPriceSingle001_settleType').val(result.data.settleType).trigger('change');
                        $('#CfetsBondDealQuotedPriceSingle001_div_settleCcy').val(result.data.settleCcy).trigger('change');
                        $('#CfetsBondDealQuotedPriceSingle001_unitCprice').val(result.data.price).trigger('blur');
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

                        $('#CfetsBondDealQuotedPriceSingle001_portfolioId').attr('disabled', true);
                        $('#CfetsBondDealQuotedPriceSingle001_ps').attr('disabled', true);
                        $('#CfetsBondDealQuotedPriceSingle001_finprodId').attr('disabled', true);
                        $('#CfetsBondDealQuotedPriceSingle001_settleSpreed').attr('disabled', true);
                        //add by lijie2 
                        if(result.data && result.data.priceModel == PRICE_MODEL_PRM01){
                            $('#CfetsBondDealQuotedPriceSingle001_unitCprice').attr('disabled', true);
                            $('#CfetsBondDealQuotedPriceSingle001_matureYield').attr('disabled', true);
                            $('#CfetsBondDealQuotedPriceSingle001_exerYield').attr('disabled', true);
                            $("input[name='calculateField']").attr('disabled', true);
                            $("#CfetsBondDealQuotedPriceSingle001_hidden_calculateField").val("01");
                            // $("input[name=calculateField][value=01]").prop("checked","true");
                            // layui.form.render();
                        }else if(result.data && result.data.assetTypeOne == assetTypeOne_abs){
                            $('#CfetsBondDealQuotedPriceSingle001_matureYield').attr('disabled', true);
                            $('#CfetsBondDealQuotedPriceSingle001_exerYield').attr('disabled', true);
                            $("input[name='calculateField']").attr('disabled', true);
                            $("#CfetsBondDealQuotedPriceSingle001_hidden_calculateField").val("01");
                            // $("input[name=calculateField][value=01]").prop("checked","true");
                            // layui.form.render();
                        }else{
                            $('#CfetsBondDealQuotedPriceSingle001_unitCprice').attr('disabled', false);
                            $('#CfetsBondDealQuotedPriceSingle001_matureYield').attr('disabled', false);
                            $('#CfetsBondDealQuotedPriceSingle001_exerYield').attr('disabled', false);
                            $("input[name='calculateField']").attr('disabled', false);
                            $("#CfetsBondDealQuotedPriceSingle001_hidden_calculateField").val(result.data.calculateField);
                            layui.form.render();
                        }
                    }
                    entrustIdChangeFlag = false;
                    calcAmt($("#CfetsBondDealQuotedPriceSingle001_hidden_calculateField").val());
                }
            });
        }
        else {
            $('#CfetsBondDealQuotedPriceSingle001_remark').val("");
            var quotaPageModel = $('#CfetsBondDealQuotedPriceSingle001_hidden_quotaPageModel').val();
            //add by yzg 20210116 委托代码清空联动清空 start
            if(!quotaPageModel || (quotaPageModel != 'dialogueResponse')){
                $('#CfetsBondDealQuotedPriceSingle001_hidden_entrustAmt').val('');
                $('#CfetsBondDealQuotedPriceSingle001_hidden_finprodId').val('');
                $('#CfetsBondDealQuotedPriceSingle001_mySide').val('').trigger('change');
                $('#CfetsBondDealQuotedPriceSingle001_counterId').val('').trigger('change');
                $('#CfetsBondDealQuotedPriceSingle001_counterTrader').textSelect('setValue','');
                $('#CfetsBondDealQuotedPriceSingle001_finprodName').attr('disabled', false).val('').trigger('change');
                $('#CfetsBondDealQuotedPriceSingle001_ps').val('').trigger('change').attr('disabled', false);
                $('#CfetsBondDealQuotedPriceSingle001_settleSpreed').val('01').trigger('change').attr('disabled', false);
                $('#CfetsBondDealQuotedPriceSingle001_settleDate').val('');
                $('#CfetsBondDealQuotedPriceSingle001_avaNum').val('').trigger('blur');
                $('#CfetsBondDealQuotedPriceSingle001_responseAmt').val('').trigger('blur');
                $('#CfetsBondDealQuotedPriceSingle001_portfolioId').attr('disabled', true).val('').trigger('change');
                $('#CfetsBondDealQuotedPriceSingle001_mySideTrader').val('');
                $('#CfetsBondDealQuotedPriceSingle001_settleMethod').val('03').trigger('change');
                $('#CfetsBondDealQuotedPriceSingle001_settleType').val('01').trigger('change');
                $('#CfetsBondDealQuotedPriceSingle001_div_settleCcy').val('CNY').trigger('change');
                $('#CfetsBondDealQuotedPriceSingle001_unitCprice').val('').trigger('blur').attr('disabled', false);;
                $('#CfetsBondDealQuotedPriceSingle001_matureYield').val('').trigger('blur').attr('disabled', false);;
                $('#CfetsBondDealQuotedPriceSingle001_exerYield').val('').trigger('blur');
                $('#CfetsBondDealQuotedPriceSingle001_unitInt').val('').trigger('blur');
                $('#CfetsBondDealQuotedPriceSingle001_unitFprice').val('').trigger('blur');
                $('#CfetsBondDealQuotedPriceSingle001_cpriceAmt').val('').trigger('blur');
                $('#CfetsBondDealQuotedPriceSingle001_parValue').val('').trigger('blur');
                $('#CfetsBondDealQuotedPriceSingle001_intAmt').val('').trigger('blur');
                $('#CfetsBondDealQuotedPriceSingle001_fpriceAmt').val('').trigger('blur');
                $('#CfetsBondDealQuotedPriceSingle001_parValueAmt').val('').trigger('blur');
            }else if(quotaPageModel == 'dialogueResponse'){
                $('#CfetsBondDealQuotedPriceSingle001_portfolioId').val('').trigger('change');
            }
            //add by yzg 20210116 委托代码清空联动清空 end
            if (ibuFlag_startDialogueQuoteWithNoEntrust == '02'){
                $('#CfetsBondDealQuotedPriceSingle001_portfolioId').attr('disabled', false);
            }
            $('#CfetsBondDealQuotedPriceSingle001_settleDate').val(platDate);
        }
    };
    
    //资产代码
    var finprodIdChangeAction =  function () {
        var chlFinprodId = '';
        var finprodIdhid = '';
        var ccy =  '';

        var data = $('#CfetsBondDealQuotedPriceSingle001_finprodId').select2('data')[0].object;
        if(data){
            finprodIdhid = data.finprodId;
            ccy = data.ccy;
            $('#CfetsBondDealQuotedPriceSingle001_hidden_finprodId').val(data.finprodId);
            $("#CfetsBondDealQuotedPriceSingle001_settleCcy").val(data.ccy).trigger("change");
            if (data.isRight == yesNoFlag_yes){
                $('#CfetsBondDealQuotedPriceSingle001_div_exerYield').toggle(true);
            }
            else{
                $('#CfetsBondDealQuotedPriceSingle001_div_exerYield').toggle(false);
            }
            if (data.assetType1 == assetTypeOne_abs){
                $('#CfetsBondDealQuotedPriceSingle001_div_abs1').toggle(true);
                $('#CfetsBondDealQuotedPriceSingle001_div_abs2').toggle(true);

                $('#CfetsBondDealQuotedPriceSingle001_matureYield').attr('disabled', true);
                $('#CfetsBondDealQuotedPriceSingle001_exerYield').attr('disabled', true);
                $("input[name='calculateField']").attr('disabled', true);
                $("#CfetsBondDealQuotedPriceSingle001_hidden_calculateField").val("01");
                $("input[name=calculateField][value=01]").prop("checked","true");
                layui.form.render();

            }else{
                $('#CfetsBondDealQuotedPriceSingle001_div_abs1').toggle(false);
                $('#CfetsBondDealQuotedPriceSingle001_div_abs2').toggle(false);
                $('#CfetsBondDealQuotedPriceSingle001_matureYield').attr('disabled', false);
                $('#CfetsBondDealQuotedPriceSingle001_exerYield').attr('disabled', false);
                $("input[name='calculateField']").attr('disabled', false);
            }
        }else{
            $('#CfetsBondDealQuotedPriceSingle001_hidden_finprodId').val("");
            $("#CfetsBondDealQuotedPriceSingle001_settleCcy").val(CCY_CNY).trigger("change");
        }
        var calcField = $("[name=calculateField]:checked").val();
        if(calcField){
            calcAmt(calcField);
        }
        // 行情数据加载
        var portfolioId = $("#CfetsBondDealQuotedPriceSingle001_portfolioId").val();
        var sysDateT1 = $('#CfetsBondDealQuotedPriceSingle001_tradeDate').val();
        var calId = '';
        if (todoList == false&&parent.parent.EtrIbBondinfoHq) {
            parent.parent.EtrIbBondinfoHq.querySecinfo(finprodIdhid,portfolioId,chlFinprodId,ccy,sysDateT1,calId);
        }
    };

    /**
     * 组合代码
     */
    var portfolioIdChangeAction = function () {
        var quotaPageModel = $('#CfetsBondDealQuotedPriceSingle001_hidden_quotaPageModel').val();
        if(quotaPageModel != 'dialogueResponse'){
            $('#CfetsBondDealQuotedPriceSingle001_mySide').val('').trigger('change');
        }
        var portfolioId = $("#CfetsBondDealQuotedPriceSingle001_portfolioId").val();
        var vdate = $('#CfetsBondDealQuotedPriceSingle001_tradeDate').val();
        var ccy= $("#CfetsBondDealQuotedPriceSingle001_settleCcy").val();
        if(window.EtrInitCash){
            window.EtrInitCash.queryCash(ccy,vdate,portfolioId);
        }
    };

    /**
     * 清算速度
     */
    var settleSpreedChangeAction = function(){
        var settleSpreed = $('#CfetsBondDealQuotedPriceSingle001_settleSpreed').val();
        if (settleSpreed == ACCOUNT_SPEED_1 || settleSpreed == ACCOUNT_SPEED_2){
            Joyin.ajax(basePath + 'invest/bankuplink/common/CfetsQuotedCommon/getNextWorkDay', {
                'tradeDate': platDate,
                'settleSpreed': settleSpreed
            }, function (date) {
                $("#CfetsBondDealQuotedPriceSingle001_settleDate").val(date).trigger("change");
                var calcField = $("[name=calculateField]:checked").val();
                if(calcField){
                    calcAmt(calcField);
                }
            });
        }else{
            $('#CfetsBondDealQuotedPriceSingle001_settleDate').val(platDate);
            var calcField = $("[name=calculateField]:checked").val();
            if(calcField){
                calcAmt(calcField);
            }
        }
    };
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

    /**
     * 计算金额相关
     */
    var calcAmt = function(calculateField){
        if(entrustIdChangeFlag){
            return;
        }
        var calcField = $("[name=calculateField]:checked").val();
        var finprodId = $('#CfetsBondDealQuotedPriceSingle001_hidden_finprodId').val();
        if(!finprodId){
            return;
        }
        if (calculateField && calculateField != ''){
            if (calculateField != calcField){
                return;
            }
        }
        if (calcField){
            if (calcField === calcTypeUnitCprice && !$('#CfetsBondDealQuotedPriceSingle001_unitCprice').val()){
                return;
            }
            if (calcField === calcTypeYield && !$('#CfetsBondDealQuotedPriceSingle001_matureYield').val()){
                return;
            }
            if (calcField === calcTypeExecYield && !$('#CfetsBondDealQuotedPriceSingle001_exerYield').val()){
                return;
            }


            var object = $.serializeToObject('#submitForm', true);
            object.calculateField = calcField;
            object.finprodId = finprodId;
            // 区分画面打开模式
            var quotaPageModel = $('#CfetsBondDealQuotedPriceSingle001_hidden_quotaPageModel').val();
            // 做市报价回复
            if (quotaPageModel && quotaPageModel == 'marketResponse' ){
                if (!object.responseAmt){
                    return;
                }
                object.avaNum = new BigNumber(object.responseAmt).multipliedBy(new BigNumber(10000));
            }else{
                if (!object.avaNum){
                    return;
                }
                object.avaNum = new BigNumber(object.avaNum).multipliedBy(new BigNumber(10000))
            }
            // object.avaNum = new BigNumber(object.avaNum).multipliedBy(new BigNumber(10000));

            Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle001/calcAmt', object, function(result){
                if (result.data){
                    entrustIdChangeFlag = true;
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
                    entrustIdChangeFlag = false;
                }
            })
        }
    };

    /**
     * 接收子页面返回的委托信息
     */
    var saveEntrustSelectData = function(entrustInfo){
        if (entrustInfo){
            $('#CfetsBondDealQuotedPriceSingle001_entrustId').textSelect('setValue',entrustInfo.entrustId);
        }
    }

    /**
     * 画面初始化
     */
    var initPage = function(){
        calcAmt();
        var quotaPageModel = $('#CfetsBondDealQuotedPriceSingle001_hidden_quotaPageModel').val();
        if(quotaPageModel && quotaPageModel == 'dialogueResponse'){
            //$('#CfetsBondDealQuotedPriceSingle001_portfolioName').attr('disabled', true);
            $('#calculateField_exerYield').attr('disabled', true);
            $('#calculateField_yield').attr('disabled', true);
        }
        if(workflowId || (pageModel == PAGE_MODEL_REFERENCE || pageModel == PAGE_MODEL_EDIT) && !(quotaPageModel && (quotaPageModel == 'dialogueResponse'))){//流程中、修改、查看
            $('#CfetsBondDealQuotedPriceSingle001_entrustId_search').css('opacity',0.4);
        }

        /**
         * 券面总额
         */
        $('#CfetsBondDealQuotedPriceSingle001_avaNum').on('change',function(){
            calcAmt();
        });
        /**
         * 回复量
         */
        $('#CfetsBondDealQuotedPriceSingle001_responseAmt').on('change',function(){
            calcAmt();
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
        /**
         * 单位净价
         */
        $('#CfetsBondDealQuotedPriceSingle001_unitCprice').on('blur',function(){
            // 根据净价计算净价偏离度
            calDeviationDegree();
            var portfolioIds = [];
            portfolioIds.push($("#CfetsBondDealQuotedPriceSingle001_portfolioId").val());
            if(parent.parent.EtrIbBondinfoHq){
                parent.parent.EtrIbBondinfoHq.calCanBuyAmount(portfolioIds,$('#CfetsBondDealQuotedPriceSingle001_settleCcy').val(),
                    $('#CfetsBondDealQuotedPriceSingle001_tradeDate').val(),$('#CfetsBondDealQuotedPriceSingle001_unitCprice').val());
            }
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
    }

    $(function () {
        // 初始化页面
        initPage();
        // 委托代码
        var entrustId = $('#CfetsBondDealQuotedPriceSingle001_hidden_entrustId').val();
        if (entrustId && entrustId != ''){
            $('#CfetsBondDealQuotedPriceSingle001_entrustId').textSelect('setValue',entrustId);
            $('#CfetsBondDealQuotedPriceSingle001_entrustId').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_portfolioId').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_ps').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_finprodId').attr('disabled', true);
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
            $('#CfetsBondDealQuotedPriceSingle001_finprodId').attr('disabled', true);
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
            $('#CfetsBondDealQuotedPriceSingle001_mySideTrader').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_ps').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_mySide').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_counterId').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle001_counterTrader').attr('disabled', true);
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
            $('#CfetsBondDealQuotedPriceSingle001_ps').attr('disabled', true);
            //修改无委托初始化增加不可修改项 add by yzg 20210123 end
            $('#CfetsBondDealQuotedPriceSingle001_btn_done').toggle(false);
            $('#CfetsBondDealQuotedPriceSingle001_btn_refuse').toggle(false);
            $('#CfetsBondDealQuotedPriceSingle001_btn_save').toggle(false);
            $('#CfetsBondDealQuotedPriceSingle001_btn_submit').toggle(false);
            $('#CfetsBondDealQuotedPriceSingle001_btn_cancel').toggle(false);

        }
        // 含权、资产一级类型
        var isRight = $('#CfetsBondDealQuotedPriceSingle001_hidden_isRight').val();
        /*if (isRight == yesNoFlag_yes){
            $('#CfetsBondDealQuotedPriceSingle001_div_exerYield').toggle(true);
        }
        else{
            $('#CfetsBondDealQuotedPriceSingle001_div_exerYield').toggle(false);
        }*/
        exerYieldDivContrl();
        var assetType1 = $('#CfetsBondDealQuotedPriceSingle001_hidden_assetTypeOne').val();
        if (assetType1 == assetTypeOne_abs){
            $('#CfetsBondDealQuotedPriceSingle001_div_abs1').toggle(true);
            $('#CfetsBondDealQuotedPriceSingle001_div_abs2').toggle(true);
        }else{
            $('#CfetsBondDealQuotedPriceSingle001_div_abs1').toggle(false);
            $('#CfetsBondDealQuotedPriceSingle001_div_abs2').toggle(false);
        }

        /**
         * 委托代码
         */
        $('#CfetsBondDealQuotedPriceSingle001_entrustId').change(function(){
            entrustIdChangeAction();
        });
        /**
         * 委托代码放大镜
         */
        $('#CfetsBondDealQuotedPriceSingle001_entrustId_search').click(function(){
            if(workflowId || (pageModel == PAGE_MODEL_REFERENCE || pageModel == PAGE_MODEL_EDIT) && !(quotaPageModel && (quotaPageModel == 'dialogueResponse'))){//流程中、修改、查看
                return;
            }
            Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice006"),
                basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice006/init", "99%", "80%", {
                    pageModel : PAGE_MODEL_NEW, finprodId : $('#CfetsBondDealQuotedPriceSingle001_finprodMarketId').val()
                    , ps : $('#CfetsBondDealQuotedPriceSingle001_ps').val(), entrustId : $('#CfetsBondDealQuotedPriceSingle001_entrustId').val(), editFlag : editFlag
                    , mySide : $('#CfetsBondDealQuotedPriceSingle001_mySide').val(), quotaPageModel : $('#CfetsBondDealQuotedPriceSingle001_hidden_quotaPageModel').val()
                    , settleSpreed : $('#CfetsBondDealQuotedPriceSingle001_settleSpreed').val()
                })
        })
        /**
         * 资产简称
         */
        $('#CfetsBondDealQuotedPriceSingle001_finprodId').change(function(){
            finprodIdChangeAction();
        });
        /**
         * 组合名称
         */
        $('#CfetsBondDealQuotedPriceSingle001_portfolioId').change(function(){
            portfolioIdChangeAction();
        });
        /**
         * 清算速度
         */
        $('#CfetsBondDealQuotedPriceSingle001_settleSpreed').change(function(){
            settleSpreedChangeAction();
        });
        /**
         * 券面总额
         */
        $('#CfetsBondDealQuotedPriceSingle001_avaNum').blur(function(){
            calcAmt();
        });
        /**
         * 券面总额
         */
        $("#CfetsBondDealQuotedPriceSingle001_avaNum").change(function() {
            calcAmt();
        });
        /**
         * 回复量
         */
        $('#CfetsBondDealQuotedPriceSingle001_responseAmt').blur(function(){
            calcAmt();
        });
        /**
         * 单位净价
         */
        $('#CfetsBondDealQuotedPriceSingle001_unitCprice').blur(function(){
            calcAmt('01');
        });
        /**
         * 到期收益率
         */
        $('#CfetsBondDealQuotedPriceSingle001_matureYield').blur(function(){
            calcAmt('02');
        });
        /**
         * 行权收益率
         */
        $('#CfetsBondDealQuotedPriceSingle001_exerYield').blur(function(){
            calcAmt('03');
        });

        var calculateFieldChange = function(init) {
            var calculateField = $("[name=calculateField" + "]:radio").next(".layui-form-radioed").prev("input").val();
            calcAmt(calculateField);
        }

        layui.use(['form'], function() {
            // 绑定计算模式的点击事件
            var form = layui.form;
            form.on('radio(calculateField)', function(data) {
                layui.form.render(); //更新
                calculateFieldChange();
            });
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
         * 本方
         */
        $('#CfetsBondDealQuotedPriceSingle001_mySide').change(function(){
            mySideChangeAction();
        });
        /**
         * 对手方方
         */
        $('#CfetsBondDealQuotedPriceSingle001_counterId').change(function(){
            counterIdChangeAction();
        });
    });


    /**
     * 本方
     */
    var mySideChangeAction = function(){
        $('#CfetsBondDealQuotedPriceSingle001_mySideTrader').val('').trigger('change');
    }
    /**
     * 对手方
     */
    var counterIdChangeAction = function(){
        $('#CfetsBondDealQuotedPriceSingle001_counterTrader').val('').trigger('change');
    }

    /**
     * 对话界面赋值
     * @param selArr
     */
    var setDhDiv = function(selArr){
        $("#CfetsBondDealQuotedPriceSingle001_hidden_quotaPageModel").val('new');
        if(selArr&&selArr[0]){
            $("#CfetsBondDealQuotedPriceSingle001_entrustId").textSelect('setValue',selArr[0].entrustId);
            // var entrustId = selArr[0].entrustId;
            // if (entrustId && entrustId != ''){
            //     Joyin.ajax(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle001/queryEntrustInfoByEntrustId', {entrustId : entrustId}, function(result) {
            //         if (result.data){
            //             $('#CfetsBondDealQuotedPriceSingle001_remark').val(result.data.remark);
            //             $('#CfetsBondDealQuotedPriceSingle001_hidden_entrustAmt').val(result.data.entrustNum);
            //             $('#CfetsBondDealQuotedPriceSingle001_hidden_finprodId').val(result.data.finprodId);
            //             // 区分画面打开模式
            //             var quotaPageModel = $('#CfetsBondDealQuotedPriceSingle001_hidden_quotaPageModel').val();
            //
            //             // 回复对话报价
            //             if (quotaPageModel && (quotaPageModel == 'dialogueResponse')){
            //                 $('#CfetsBondDealQuotedPriceSingle001_portfolioId').textSelect('setValue',result.data.portfolioId);
            //             }
            //             else {
            //                 // 做市方回复
            //                 if (quotaPageModel != 'marketResponse'){
            //                     // 这些要素应该是做市方已有的信息
            //                     $('#CfetsBondDealQuotedPriceSingle001_mySide').textSelect('setValue',result.data.mySide);
            //                     $('#CfetsBondDealQuotedPriceSingle001_counterId').textSelect('setValue',result.data.counterId);
            //                     $('#CfetsBondDealQuotedPriceSingle001_counterTrader').textSelect('setValue',result.data.counterTrader);
            //                     $('#CfetsBondDealQuotedPriceSingle001_finprodId').textSelect('setValue',result.data.finprodMarketId);
            //                     $('#CfetsBondDealQuotedPriceSingle001_ps').val(result.data.ps).trigger('change');
            //                     $('#CfetsBondDealQuotedPriceSingle001_avaNum').val(result.data.avaNumStr).trigger('blur');
            //                     $('#CfetsBondDealQuotedPriceSingle001_settleSpreed').val(result.data.settleSpreed).trigger('change');
            //                     $('#CfetsBondDealQuotedPriceSingle001_settleDate').val(result.data.settleDateStr);
            //                 }else{
            //                     // 做市报价回复选择委托后回复量默认显示委托剩余额度
            //                     $('#CfetsBondDealQuotedPriceSingle001_responseAmt').val(result.data.avaNumStr).trigger('blur');
            //                 }
            //                 // 对话报价发起模式
            //                 $('#CfetsBondDealQuotedPriceSingle001_portfolioId').textSelect('setValue',result.data.portfolioId);
            //                 $('#CfetsBondDealQuotedPriceSingle001_finprodId').textSelect('setValue',result.data.finprodMarketId);
            //                 $('#CfetsBondDealQuotedPriceSingle001_mySide').textSelect('setValue',result.data.mySide);
            //                 $('#CfetsBondDealQuotedPriceSingle001_mySideTrader').textSelect('setValue',result.data.mySideTrader);
            //                 $('#CfetsBondDealQuotedPriceSingle001_settleMethod').val(result.data.settleMethod).trigger('change');
            //                 $('#CfetsBondDealQuotedPriceSingle001_settleType').val(result.data.settleType).trigger('change');
            //                 $('#CfetsBondDealQuotedPriceSingle001_settleCcy').val(result.data.settleCcy).trigger('change');
            //                 $('#CfetsBondDealQuotedPriceSingle001_unitCprice').val(result.data.price).trigger('change');
            //                 $('#CfetsBondDealQuotedPriceSingle001_matureYield').val(result.data.matureYield).trigger('blur');
            //                 $('#CfetsBondDealQuotedPriceSingle001_exerYield').val(result.data.exerYield).trigger('blur');
            //                 $('#CfetsBondDealQuotedPriceSingle001_unitInt').val(result.data.unitInt).trigger('blur');
            //                 $('#CfetsBondDealQuotedPriceSingle001_unitFprice').val(result.data.unitFprice).trigger('blur');
            //                 $('#CfetsBondDealQuotedPriceSingle001_cpriceAmt').val(result.data.cpriceAmt).trigger('blur');
            //                 $('#CfetsBondDealQuotedPriceSingle001_parValue').val(result.data.parValue).trigger('blur');
            //                 $('#CfetsBondDealQuotedPriceSingle001_intAmt').val(result.data.intAmt).trigger('blur');
            //                 $('#CfetsBondDealQuotedPriceSingle001_fpriceAmt').val(result.data.fpriceAmt).trigger('blur');
            //                 $('#CfetsBondDealQuotedPriceSingle001_parValueAmt').val(result.data.parValueAmt).trigger('blur');
            //                 $('#CfetsBondDealQuotedPriceSingle001_secManageAcctId').textSelect('setValue',result.data.secManageAcctId);
            //             }
            //             exerYieldDivContrl();
            //         }
            //     });
            // }
        }
    }

    /**
     * 净值偏离度
     */
    function calDeviationDegree() {
        if ($("#CfetsBondDealQuotedPriceSingle001_hidden_finprodId").val() && $("#CfetsBondDealQuotedPriceSingle001_unitCprice").val()) {
            var paramObj = {};
            paramObj.finprodId = $('#CfetsBondDealQuotedPriceSingle001_hidden_finprodId').val();
            if($('#CfetsBondDealQuotedPriceSingle001_finprodId').val()){
                paramObj.finprodMarketId = $('#CfetsBondDealQuotedPriceSingle001_finprodId').val();
            }
            paramObj.unitCprice = $("#CfetsBondDealQuotedPriceSingle001_unitCprice").val();
            Joyin.ajax(JoyinUtil.getRealPath('/invest/trade/bond/TrdBondInvestTrade003/calcDeviationDegree'), paramObj, function (data) {
                $("#deviationDegree").text(data);
            });
        } else {
            $("#deviationDegree").text("");
        }
    }

    /**
     * 重置按钮
     */
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
        if(!object.calculateField) {
            object.calculateField = $("#CfetsBondDealQuotedPriceSingle001_hidden_calculateField").val();
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
        saveCluflag: saveCluflag,
        saveEntrustSelectData: saveEntrustSelectData
    }
    CfetsBondDealQuotedPriceSingle001 = {
        setDhDiv: setDhDiv,
        getriskdata: getriskdata,
        getSaveOrSubmitParams: getSaveOrSubmitParams,
        reset: reset,
        riskResultdata: riskResultdata,
        cluflag: cluflag,
        saveRiskResultdata: saveRiskResultdata,
        saveCluflag: saveCluflag,
        saveEntrustSelectData: saveEntrustSelectData
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