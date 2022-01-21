(function(){

    /**
     * 是否可编辑
     */
    var editFlag = true;
    //这个标志是防止查询结果返回赋值后不停的联动calcAmt;
    var entrustIdChangeFlag = false;
    /**
     * 委托代码
     */
    var entrustIdChangeAction = function(){
        var entrustId = $('#CfetsBondDealQuotedPrice004_entrustId').val();
        if (entrustId && entrustId != ''){
            Joyin.ajax(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice004/queryEntrustInfoByEntrustId', {entrustId : entrustId}, function(result) {
                if (result.data){
                    entrustIdChangeFlag = true;
                    $('#CfetsBondDealQuotedPrice004_remark').val(result.data.remark);
                    $('#CfetsBondDealQuotedPrice004_hidden_entrustAmt').val(result.data.entrustNum);
                    $('#CfetsBondDealQuotedPrice004_hidden_finprodId').val(result.data.finprodId);
                    // 区分画面打开模式
                    var quotaPageModel = $('#CfetsBondDealQuotedPrice004_hidden_quotaPageModel').val();

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
                        }
                        layui.form.render();
                    }

                    // 回复对话报价
                    if (quotaPageModel && (quotaPageModel == 'dialogueResponse')){
                        $('#CfetsBondDealQuotedPrice004_portfolioName').attr('disabled', true).textSelect('setValue',result.data.portfolioId);
                    }
                    else {
                        // 做市方回复
                        if (quotaPageModel != 'marketResponse'){
                            // 这些要素应该是做市方已有的信息
                            $('#CfetsBondDealQuotedPrice004_mySide').textSelect('setValue',result.data.mySide);
                            $('#CfetsBondDealQuotedPrice004_counterId').textSelect('setValue',result.data.counterId);
                            $('#CfetsBondDealQuotedPrice004_counterTrader').textSelect('setValue',result.data.counterTrader);
                            $('#CfetsBondDealQuotedPrice004_finprodName').attr('disabled', true).textSelect('setValue',result.data.finprodMarketId);
                            $('#CfetsBondDealQuotedPrice004_ps').val(result.data.ps).trigger('change').attr('disabled', true);
                            $('#CfetsBondDealQuotedPrice004_settleSpreed').val(result.data.settleSpreed).trigger('change').attr('disabled', true);
                            $('#CfetsBondDealQuotedPrice004_settleDate').val(result.data.settleDateStr).attr('disabled', true);
                            $('#CfetsBondDealQuotedPrice004_avaNum').val(result.data.avaNumStr).trigger('blur');
                        }else{
                            // 做市报价回复选择委托后回复量默认显示委托剩余额度
                            $('#CfetsBondDealQuotedPrice004_responseAmt').val(result.data.avaNumStr).trigger('blur');
                        }
                        // 对话报价发起模式
                        $('#CfetsBondDealQuotedPrice004_portfolioName').attr('disabled', true).textSelect('setValue',result.data.portfolioId);
                        $('#CfetsBondDealQuotedPrice004_mySide').textSelect('setValue',result.data.mySide);
                        $('#CfetsBondDealQuotedPrice004_mySideTrader').textSelect('setValue',result.data.mySideTrader);
                        $('#CfetsBondDealQuotedPrice004_settleMethod').val(result.data.settleMethod).trigger('change');
                        $('#CfetsBondDealQuotedPrice004_settleType').val(result.data.settleType).trigger('change');
                        $('#CfetsBondDealQuotedPrice004_div_settleCcy').val(result.data.settleCcy).trigger('change');
                        $('#CfetsBondDealQuotedPrice004_unitCprice').val(result.data.price).trigger('blur');
                        $('#CfetsBondDealQuotedPrice004_matureYield').val(result.data.matureYield).trigger('blur');
                        $('#CfetsBondDealQuotedPrice004_exerYield').val(result.data.exerYield).trigger('blur');
                        $('#CfetsBondDealQuotedPrice004_unitInt').val(result.data.unitInt).trigger('blur');
                        $('#CfetsBondDealQuotedPrice004_unitFprice').val(result.data.unitFprice).trigger('blur');
                        $('#CfetsBondDealQuotedPrice004_cpriceAmt').val(result.data.cpriceAmt).trigger('blur');
                        $('#CfetsBondDealQuotedPrice004_parValue').val(result.data.parValue).trigger('blur');
                        $('#CfetsBondDealQuotedPrice004_intAmt').val(result.data.intAmt).trigger('blur');
                        $('#CfetsBondDealQuotedPrice004_fpriceAmt').val(result.data.fpriceAmt).trigger('blur');
                        $('#CfetsBondDealQuotedPrice004_parValueAmt').val(result.data.parValueAmt).trigger('blur');

                        $('#CfetsBondDealQuotedPrice004_portfolioName').attr('disabled', true);
                        $('#CfetsBondDealQuotedPrice004_ps').attr('disabled', true);
                        $('#CfetsBondDealQuotedPrice004_finprodName').attr('disabled', true);
                        $('#CfetsBondDealQuotedPrice004_settleSpreed').attr('disabled', true);
                        //add by lijie2
                        if(result.data && result.data.priceModel == PRICE_MODEL_PRM01){
                            $('#CfetsBondDealQuotedPrice004_unitCprice').attr('disabled', true);
                            $('#CfetsBondDealQuotedPrice004_matureYield').attr('disabled', true);
                            $('#CfetsBondDealQuotedPrice004_exerYield').attr('disabled', true);
                            $("input[name='calculateField']").attr('disabled', true);
                            $("#CfetsBondDealQuotedPrice004_hidden_calculateField").val("01");
                            $("input[name=calculateField][value=01]").prop("checked","true");
                            layui.form.render();
                        }else if(result.data && result.data.assetTypeOne == assetTypeOne_abs){
                            $('#CfetsBondDealQuotedPrice004_matureYield').attr('disabled', true);
                            $('#CfetsBondDealQuotedPrice004_exerYield').attr('disabled', true);
                            $("input[name='calculateField']").attr('disabled', true);
                            $("#CfetsBondDealQuotedPrice004_hidden_calculateField").val("01");
                            $("input[name=calculateField][value=01]").prop("checked","true");
                            layui.form.render();
                        }else{
                            $('#CfetsBondDealQuotedPrice004_unitCprice').attr('disabled', false);
                            $('#CfetsBondDealQuotedPrice004_matureYield').attr('disabled', false);
                            $('#CfetsBondDealQuotedPrice004_exerYield').attr('disabled', false);
                            $("input[name='calculateField']").attr('disabled', false);
                            $("#CfetsBondDealQuotedPrice004_hidden_calculateField").val(result.data.calculateField);
                            layui.form.render();
                        }
                    }
                    entrustIdChangeFlag = false;
                }
            });
        }
        else {
            $('#CfetsBondDealQuotedPrice004_remark').val("");
            var quotaPageModel = $('#CfetsBondDealQuotedPrice004_hidden_quotaPageModel').val();
            //add by yzg 20210116 委托代码清空联动清空 start
            if(!quotaPageModel || (quotaPageModel != 'dialogueResponse')){
                $('#CfetsBondDealQuotedPrice004_hidden_entrustAmt').val('');
                $('#CfetsBondDealQuotedPrice004_hidden_finprodId').val('');
                $('#CfetsBondDealQuotedPrice004_mySide').val('').trigger('change');
                $('#CfetsBondDealQuotedPrice004_counterId').val('').trigger('change');
                $('#CfetsBondDealQuotedPrice004_counterTrader').textSelect('setValue','');
                $('#CfetsBondDealQuotedPrice004_finprodName').attr('disabled', false).val('').trigger('change');
                $('#CfetsBondDealQuotedPrice004_ps').val('').trigger('change').attr('disabled', false);
                $('#CfetsBondDealQuotedPrice004_settleSpreed').val('01').trigger('change').attr('disabled', false);
                $('#CfetsBondDealQuotedPrice004_settleDate').val('');
                $('#CfetsBondDealQuotedPrice004_avaNum').val('').trigger('blur');
                $('#CfetsBondDealQuotedPrice004_responseAmt').val('').trigger('blur');
                $('#CfetsBondDealQuotedPrice004_portfolioName').attr('disabled', true).val('').trigger('change');
                $('#CfetsBondDealQuotedPrice004_mySideTrader').val('');
                $('#CfetsBondDealQuotedPrice004_settleMethod').val('03').trigger('change');
                $('#CfetsBondDealQuotedPrice004_settleType').val('01').trigger('change');
                $('#CfetsBondDealQuotedPrice004_div_settleCcy').val('CNY').trigger('change');
                $('#CfetsBondDealQuotedPrice004_unitCprice').val('').trigger('blur').attr('disabled', false);;
                $('#CfetsBondDealQuotedPrice004_matureYield').val('').trigger('blur').attr('disabled', false);;
                $('#CfetsBondDealQuotedPrice004_exerYield').val('').trigger('blur');
                $('#CfetsBondDealQuotedPrice004_unitInt').val('').trigger('blur');
                $('#CfetsBondDealQuotedPrice004_unitFprice').val('').trigger('blur');
                $('#CfetsBondDealQuotedPrice004_cpriceAmt').val('').trigger('blur');
                $('#CfetsBondDealQuotedPrice004_parValue').val('').trigger('blur');
                $('#CfetsBondDealQuotedPrice004_intAmt').val('').trigger('blur');
                $('#CfetsBondDealQuotedPrice004_fpriceAmt').val('').trigger('blur');
                $('#CfetsBondDealQuotedPrice004_parValueAmt').val('').trigger('blur');
            }else if(quotaPageModel == 'dialogueResponse'){
                $('#CfetsBondDealQuotedPrice004_portfolioName').val('').trigger('change');
            }
            //add by yzg 20210116 委托代码清空联动清空 end
            if (ibuFlag_startDialogueQuoteWithNoEntrust == '02'){
                $('#CfetsBondDealQuotedPrice004_portfolioName').attr('disabled', false);
            }
            $('#CfetsBondDealQuotedPrice004_settleDate').val(platDate);
        }
    };
    /**
     * 债券简称
     */
    var finprodNameChangeAction = function(){
        var data = $('#CfetsBondDealQuotedPrice004_finprodName').select2('data')[0].object;
        if (data){
            $('#CfetsBondDealQuotedPrice004_finprodMarketId').val(data.finprodMarketId);
            $("#CfetsBondDealQuotedPrice004_hidden_finprodId").val(data.finprodId);
            if (data.isRight == yesNoFlag_yes){
                $('#CfetsBondDealQuotedPrice004_div_exerYield').toggle(true);
            }
            else{
                $('#CfetsBondDealQuotedPrice004_div_exerYield').toggle(false);
            }
            if (data.assetType1 == assetTypeOne_abs){
                $('#CfetsBondDealQuotedPrice004_div_abs1').toggle(true);
                $('#CfetsBondDealQuotedPrice004_div_abs2').toggle(true);

                $('#CfetsBondDealQuotedPrice004_matureYield').attr('disabled', true);
                $('#CfetsBondDealQuotedPrice004_exerYield').attr('disabled', true);
                $("input[name='calculateField']").attr('disabled', true);
                $("#CfetsBondDealQuotedPrice004_hidden_calculateField").val("01");
                $("input[name=calculateField][value=01]").prop("checked","true");
                layui.form.render();

            }else{
                $('#CfetsBondDealQuotedPrice004_div_abs1').toggle(false);
                $('#CfetsBondDealQuotedPrice004_div_abs2').toggle(false);
                $('#CfetsBondDealQuotedPrice004_matureYield').attr('disabled', false);
                $('#CfetsBondDealQuotedPrice004_exerYield').attr('disabled', false);
                $("input[name='calculateField']").attr('disabled', false);
            }
            $('#CfetsBondDealQuotedPrice004_settleCcy').val(data.ccy).trigger("change");
        }else{
            $('#CfetsBondDealQuotedPrice004_finprodMarketId').val('');
            $("#CfetsBondDealQuotedPrice004_hidden_finprodId").val('');
        }
        var calcField = $("[name=calculateField]:checked").val();
        if(calcField){
            calcAmt(calcField);
        }
    };
    /**
     * 组合名称
     */
    var portfolioNameChangeAction = function(){
        var portfolioId = $('#CfetsBondDealQuotedPrice004_portfolioName').val();
        $('#CfetsBondDealQuotedPrice004_portfolioId').val(portfolioId);
        var quotaPageModel = $('#CfetsBondDealQuotedPrice004_hidden_quotaPageModel').val();
        if(quotaPageModel != 'dialogueResponse'){
            $('#CfetsBondDealQuotedPrice004_mySide').val('').trigger('change');
        }
    };
    /**
     * 清算速度
     */
    var settleSpreedChangeAction = function(){
        var settleSpreed = $('#CfetsBondDealQuotedPrice004_settleSpreed').val();
        if (settleSpreed == ACCOUNT_SPEED_1 || settleSpreed == ACCOUNT_SPEED_2){
            Joyin.ajax(basePath + 'invest/bankuplink/common/CfetsQuotedCommon/getNextWorkDay', {
                'tradeDate': platDate,
                'settleSpreed': settleSpreed
            }, function (date) {
                $("#CfetsBondDealQuotedPrice004_settleDate").val(date).trigger("change");
                var calcField = $("[name=calculateField]:checked").val();
                if(calcField){
                    calcAmt(calcField);
                }
            });
        }else{
            $('#CfetsBondDealQuotedPrice004_settleDate').val(platDate);
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
        object.operatorType = operatorType;
        object.isDzh = 'Y';
        object.pageId = "IbuQuoteBond004";
        object.shareAmt = JoyinMath.doMul(object.shareAmt,10000);
        if(object.matureYield){
            object.matureYield = JoyinMath.doDiv(object.matureYield,100,6);
        }
        if(object.exerYield){
            object.exerYield = JoyinMath.doDiv(object.exerYield,100,6);
        }
        if(object.cfetsBondDealDialogueQuoteDtoList){
            $.each(object.cfetsBondDealDialogueQuoteDtoList,function(index,obj){
                obj.shareAmt =  JoyinMath.doMul(obj.shareAmt,10000);
            });
        }
        object.quoteStyle = $("input:radio[name=quoteStyle]:checked").val();
        object.calculateField = $("input:radio[name=calculateField]:checked").val();
        Joyin.confirm("是否确认提交?",
            function() {
                //保存 走流程
                if (operatorType === 'save') {
                    WorkFlow.doSaveList(basePath + 'cfets/bond/IbuQuoteBond004/save', object, "IbuQuoteBond004_btn_save", null,
                        function() {
                            var index = window.parent.layer.getFrameIndex(window.name);
                            window.parent.layer.close(index);
                        })
                }
                //提交 走流程
                else if (operatorType === 'submit') {
                    WorkFlow.doSubmitList(basePath + 'cfets/bond/IbuQuoteBond004/save', object, "IbuQuoteBond004_btn_submit", null,
                        function() {
                            var index = window.parent.layer.getFrameIndex(window.name);
                            window.parent.layer.close(index);
                        })
                }
            })
    };
    /**
     * 计算金额相关
     */
    var calcAmt = function(calculateField){
        if(entrustIdChangeFlag){
            return;
        }
        var calcField = $("[name=calculateField]:checked").val();
        var finprodId = $('#CfetsBondDealQuotedPrice004_hidden_finprodId').val();
        if(!finprodId){
            return;
        }
        if (calculateField && calculateField != ''){
            if (calculateField != calcField){
                //$("input[name=calculateField" + "][value='"+ calcField +"']").attr("checked",true);
                //layui.form.render(); //更新;
                return;
            }
        }

        if (calcField){
            if (calcField === calcTypeUnitCprice && !$('#CfetsBondDealQuotedPrice004_unitCprice').val()){
                return;
            }
            if (calcField === calcTypeYield && !$('#CfetsBondDealQuotedPrice004_matureYield').val()){
                return;
            }
            if (calcField === calcTypeExecYield && !$('#CfetsBondDealQuotedPrice004_exerYield').val()){
                return;
            }


            var object = $.serializeToObject('#submitForm', true);
            object.calculateField = calcField;
            object.finprodId = finprodId;
            // 区分画面打开模式
            var quotaPageModel = $('#CfetsBondDealQuotedPrice004_hidden_quotaPageModel').val();
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

            Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice004/calcAmt', object, function(result){
                if (result.data){
                    entrustIdChangeFlag = true;
                    if (calculateField == calcTypeUnitCprice){
                        if(pageModel != PAGE_MODEL_REFERENCE){
                            $('#CfetsBondDealQuotedPrice004_matureYield').val(result.data.matureYield).trigger('blur');
                        }
                        $('#CfetsBondDealQuotedPrice004_exerYield').val(result.data.exerYield).trigger('blur');
                    }else if (calculateField == calcTypeYield){
                        $('#CfetsBondDealQuotedPrice004_unitCprice').val(result.data.price).trigger('blur');
                        $('#CfetsBondDealQuotedPrice004_exerYield').val(result.data.exerYield).trigger('blur');
                    }else if (calculateField == calcTypeExecYield){
                        $('#CfetsBondDealQuotedPrice004_unitCprice').val(result.data.price).trigger('blur');
                        if(pageModel != PAGE_MODEL_REFERENCE){
                            $('#CfetsBondDealQuotedPrice004_matureYield').val(result.data.matureYield).trigger('blur');
                        }
                    }else{
                        $('#CfetsBondDealQuotedPrice004_unitCprice').val(result.data.price).trigger('blur');
                        if(pageModel != PAGE_MODEL_REFERENCE){
                            $('#CfetsBondDealQuotedPrice004_matureYield').val(result.data.matureYield).trigger('blur');
                        }
                        $('#CfetsBondDealQuotedPrice004_exerYield').val(result.data.exerYield).trigger('blur');
                    }
                    $('#CfetsBondDealQuotedPrice004_unitInt').val(result.data.unitInt).trigger('blur');
                    $('#CfetsBondDealQuotedPrice004_unitFprice').val(result.data.unitFprice).trigger('blur');
                    $('#CfetsBondDealQuotedPrice004_cpriceAmt').val(result.data.cpriceAmt).trigger('blur');
                    $('#CfetsBondDealQuotedPrice004_parValue').val(result.data.parValue).trigger('blur');
                    $('#CfetsBondDealQuotedPrice004_intAmt').val(result.data.intAmt).trigger('blur');
                    $('#CfetsBondDealQuotedPrice004_fpriceAmt').val(result.data.fpriceAmt).trigger('blur');
                    $('#CfetsBondDealQuotedPrice004_parValueAmt').val(result.data.parValueAmt).trigger('blur');
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
            $('#CfetsBondDealQuotedPrice004_entrustId').textSelect('setValue',entrustInfo.entrustId);
        }
    }
    /**
     * 画面初始化
     */
    var initPage = function(){

    }

    var  disabledElementByDzh = function(entrustList){
        //组合分配列表
        if (entrustList.length>0){
            // 回复对话报价
            if (quotaPageModel && (quotaPageModel == 'dialogueResponse')){
            }
            else {
                $('#CfetsBondDealQuotedPrice004_settleSpreed').attr('disabled', true);
                $('#CfetsBondDealQuotedPrice004_mySide').attr('disabled', true);
                $('#CfetsBondDealQuotedPrice004_settleDate').attr('disabled', true);
                $('#CfetsBondDealQuotedPrice004_ps').attr('disabled', true);
                $('#CfetsBondDealQuotedPrice004_finprodName').attr('disabled', true);
            }
        }
        else{
            if (!quotaPageModel || (quotaPageModel != 'dialogueResponse')) {
                $('#CfetsBondDealQuotedPrice004_settleSpreed').val('01').trigger('change').attr('disabled', false);
                $('#CfetsBondDealQuotedPrice004_mySide').attr('disabled', false);
                $('#CfetsBondDealQuotedPrice004_settleDate').attr('disabled', false);
                $('#CfetsBondDealQuotedPrice004_ps').attr('disabled', false);
                $('#CfetsBondDealQuotedPrice004_finprodName').attr('disabled', false);
            }
        }
    }

    /**
     * 代办中 不应该显示成交和拒绝按钮
     */
    var hideRefuseButton = function () {
        var quotaPageModel = $('#CfetsBondDealQuotedPrice004_hidden_quotaPageModel').val();
        if(workflowId){
            if (quotaPageModel && quotaPageModel == 'dialogueResponse'){
                $('#CfetsBondDealQuotedPrice004_btn_submit').remove();
            }else{
                $('#CfetsBondDealQuotedPrice004_btn_done').remove();
            }
            $("#CfetsBondDealQuotedPrice004_btn_refuse").hide();
        }
    }

    window.componentLoadCallback = function(isDone, pagePath, params){
        if(isDone){
            $('#submitForm').find('input,text,textarea,select').attr('disabled','disabled');
        }
    }
    $(function () {
        // 初始化页面
        initPage();



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
        $('#IbuQuoteBond004_btn_save').click(function(){
            saveOrSubmitAction('save');
        });

        /**
         * 提交按钮按下处理
         */
        $('#IbuQuoteBond004_btn_submit').click(function(){
            saveOrSubmitAction('submit');
        });

        /**
         * 本方
         */
        $('#CfetsBondDealQuotedPrice004_mySide').change(function(){
            mySideChangeAction();
        });
        /**
         * 对手方方
         */
        $('#CfetsBondDealQuotedPrice004_counterId').change(function(){
            counterIdChangeAction();
        });
        //拒绝按钮不应该在待办及审批环节出现
        hideRefuseButton();
    });

    /**
     * 本方
     */
    var mySideChangeAction = function(){
        /*$('#CfetsBondDealQuotedPrice004_mySideTrader').val('').trigger('change');
        // add by zhangliang 20200630 start
        var mySideTemp = $('#CfetsBondDealQuotedPrice004_mySide').val();
        if (!mySideInit || mySideTemp == mySideInit){
        	$('#CfetsBondDealQuotedPrice004_mySideTrader').val(mySideTraderInit).trigger('change');
        }*/
        $('#CfetsBondDealQuotedPrice004_mySideTrader').val('').trigger('change');
    }
    /**
     * 对手方
     */
    var counterIdChangeAction = function(){
        $('#CfetsBondDealQuotedPrice004_counterTrader').val('').trigger('change');
    }
    window.CfetsBondDealQuotedPrice004 = {
        saveEntrustSelectData: saveEntrustSelectData,
        disabledElementByDzh:disabledElementByDzh
    };
}());