(function(){
    //试算结果
    var riskResultdata;
    var cluflag;
    var portfolioIds = [];
    /**
     * 是否可编辑
     */
    var editFlag = true;
    //这个标志是防止查询结果返回赋值后不停的联动calcAmt;
    var entrustIdChangeFlag = false;

    /**
     * 债券简称
     */
    var finprodIdChangeAction = function(){
        var data = $('#CfetsBondDealQuotedPriceMuti001_finprodId').select2('data')[0].object;
        var chlFinprodId = '';
        var finprodIdhid = '';
        var ccy =  '';
        if (data){
            finprodIdhid = data.finprodId;
            ccy = data.ccy;
            $('#CfetsBondDealQuotedPriceMuti001_hidden_finprodId').val(data.finprodId);
            $("#CfetsBondDealQuotedPriceMuti001_settleCcy").val(data.ccy);
            if (data.isRight == yesNoFlag_yes){
                $('#CfetsBondDealQuotedPriceMuti001_div_exerYield').toggle(true);
            }
            else{
                $('#CfetsBondDealQuotedPriceMuti001_div_exerYield').toggle(false);
            }
            if (data.paassetType1 == assetTypeOne_abs){
                $('#CfetsBondDealQuotedPriceMuti001_div_abs1').toggle(true);
                $('#CfetsBondDealQuotedPriceMuti001_div_abs2').toggle(true);

                $('#CfetsBondDealQuotedPriceMuti001_matureYield').attr('disabled', true);
                $('#CfetsBondDealQuotedPriceMuti001_exerYield').attr('disabled', true);
                $("input[name='calculateField']").attr('disabled', true);
                $("#CfetsBondDealQuotedPriceMuti001_hidden_calculateField").val("01");
                $("input[name=calculateField][value=01]").prop("checked","true");
                layui.form.render();

            }else{
                $('#CfetsBondDealQuotedPriceMuti001_div_abs1').toggle(false);
                $('#CfetsBondDealQuotedPriceMuti001_div_abs2').toggle(false);
                $('#CfetsBondDealQuotedPriceMuti001_matureYield').attr('disabled', false);
                $('#CfetsBondDealQuotedPriceMuti001_exerYield').attr('disabled', false);
                $("input[name='calculateField']").attr('disabled', false);
            }
            $('#CfetsBondDealQuotedPriceMuti001_settleCcy').val(data.ccy).trigger("change");
        }else{
            $('#CfetsBondDealQuotedPriceMuti001_finprodMarketId').val('');
            $("#CfetsBondDealQuotedPriceMuti001_hidden_finprodId").val('');
            $("#CfetsBondDealQuotedPriceMuti001_settleCcy").val(CCY_CNY);
        }
        var sysDateT1 = $('#CfetsBondDealQuotedPriceMuti001_tradeDate').val();
        var calId = '';
        if (parent.parent.CfetsBondDealQuotedPriceHq) {
            parent.parent.CfetsBondDealQuotedPriceHq.querySecinfo(finprodIdhid,'',chlFinprodId,ccy,sysDateT1,calId);
        }
        var calcField = $("[name=calculateField]:checked").val();
        if(calcField){
            calcAmt(calcField);
        }
    };

    /**
     * 清算速度
     */
    var settleSpreedChangeAction = function(){
        var settleSpreed = $('#CfetsBondDealQuotedPriceMuti001_settleSpreed').val();
        if (settleSpreed == ACCOUNT_SPEED_1 || settleSpreed == ACCOUNT_SPEED_2){
            Joyin.ajax(basePath + 'invest/bankuplink/common/CfetsQuotedCommon/getNextWorkDay', {
                'tradeDate': platDate,
                'settleSpreed': settleSpreed
            }, function (date) {
                $("#CfetsBondDealQuotedPriceMuti001_settleDate").val(date).trigger("change");
                var calcField = $("[name=calculateField]:checked").val();
                if(calcField){
                    calcAmt(calcField);
                }
            });
        }else{
            $('#CfetsBondDealQuotedPriceMuti001_settleDate').val(platDate);
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
            object.calculateField = $("#CfetsBondDealQuotedPriceMuti001_hidden_calculateField").val();
        }
        if(!object.finprodId){
            object.finprodId = $("#CfetsBondDealQuotedPriceMuti001_hidden_finprodId").val();
        }
        object.operatorType = operatorType;
        object.isDzh = 'Y';
        Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001/check', object,
            function(result) {
                debugger;
                if (result.data) {
                    Joyin.confirm(result.data,
                        function() {
                            //保存 走流程
                            if (operatorType === 'save') {
                                WorkFlow.doSaveList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001/save', object, "CfetsBondDealQuotedPriceMuti001_btn_save", "CfetsBondDealQuotedPriceMuti001",
                                    function() {
                                        var index = window.parent.layer.getFrameIndex(window.name);
                                        window.parent.layer.close(index);
                                    })
                            }
                            //成交 走流程
                            else if (operatorType === 'done') {
                                WorkFlow.doSubmitList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001/save', object, "CfetsBondDealQuotedPriceMuti001_btn_done", "CfetsBondDealQuotedPriceMuti001",
                                    function() {
                                        var index = window.parent.layer.getFrameIndex(window.name);
                                        window.parent.layer.close(index);
                                    })
                            }
                            //提交 走流程
                            else if (operatorType === 'submit') {
                                WorkFlow.doSubmitList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001/save', object, "CfetsBondDealQuotedPriceMuti001_btn_submit", "CfetsBondDealQuotedPriceMuti001",
                                    function() {
                                        var index = window.parent.layer.getFrameIndex(window.name);
                                        window.parent.layer.close(index);
                                    })
                            }
                            //拒绝 不走流程
                            else if (operatorType === 'refuse'){
                                Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001/saveData', object, function(result){
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
        var finprodId = $('#CfetsBondDealQuotedPriceMuti001_hidden_finprodId').val();
        var calcField = $("[name=calculateField]:checked").val();
        if(!finprodId){
            return;
        }

        if (calculateField && calculateField != ''){
            if (calculateField != calcField){
                return;
            }
        }
        if (calcField){

            if (calcField === calcTypeUnitCprice && !$('#CfetsBondDealQuotedPriceMuti001_unitCprice').val()){
                return;
            }
            if (calcField === calcTypeYield && !$('#CfetsBondDealQuotedPriceMuti001_matureYield').val()){
                return;
            }
            if (calcField === calcTypeExecYield && !$('#CfetsBondDealQuotedPriceMuti001_exerYield').val()){
                return;
            }

            var object = $.serializeToObject('#submitForm', true);
            object.calculateField = calcField;
            object.finprodId = finprodId;
            object.avaNum = new BigNumber(object.avaNum).multipliedBy(new BigNumber(10000));
            // object.settleDate = object.tradeDate;

            Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001/calcAmt', object, function(result){
                if (result.data){
                    if (calculateField == calcTypeUnitCprice){
                        if(pageModel != PAGE_MODEL_REFERENCE){
                            $('#CfetsBondDealQuotedPriceMuti001_matureYield').val(result.data.matureYield).trigger('blur');
                        }
                        $('#CfetsBondDealQuotedPriceMuti001_exerYield').val(result.data.exerYield).trigger('blur');
                    }else if (calculateField == calcTypeYield){
                        $('#CfetsBondDealQuotedPriceMuti001_unitCprice').val(result.data.price).trigger('blur');
                        $('#CfetsBondDealQuotedPriceMuti001_exerYield').val(result.data.exerYield).trigger('blur');
                    }else if (calculateField == calcTypeExecYield){
                        $('#CfetsBondDealQuotedPriceMuti001_unitCprice').val(result.data.price).trigger('blur');
                        if(pageModel != PAGE_MODEL_REFERENCE){
                            $('#CfetsBondDealQuotedPriceMuti001_matureYield').val(result.data.matureYield).trigger('blur');
                        }
                    }else{
                        $('#CfetsBondDealQuotedPriceMuti001_unitCprice').val(result.data.price).trigger('blur');
                        if(pageModel != PAGE_MODEL_REFERENCE){
                            $('#CfetsBondDealQuotedPriceMuti001_matureYield').val(result.data.matureYield).trigger('blur');
                        }
                        $('#CfetsBondDealQuotedPriceMuti001_exerYield').val(result.data.exerYield).trigger('blur');
                    }
                    $('#CfetsBondDealQuotedPriceMuti001_unitInt').val(result.data.unitInt).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_unitFprice').val(result.data.unitFprice).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_cpriceAmt').val(result.data.cpriceAmt).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_parValue').val(result.data.parValue).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_intAmt').val(result.data.intAmt).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_fpriceAmt').val(result.data.fpriceAmt).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_parValueAmt').val(result.data.parValueAmt).trigger('blur');

                    if(parent.parent.CfetsBondDealQuotedPriceHq){
                        parent.parent.CfetsBondDealQuotedPriceHq.calCanBuyAmount(portfolioIds,
                            $('#CfetsBondDealQuotedPriceMuti001_settleCcy').val(),
                            $('#CfetsBondDealQuotedPriceMuti001_tradeDate').val(),
                            $('#CfetsBondDealQuotedPriceMuti001_unitCprice').val());
                    }
                    entrustIdChangeFlag = false;
                }
            })
        }
    };

    /**
     * 画面初始化
     */
    var initPage = function(){
        calcAmt();
        var quotaPageModel = $('#CfetsBondDealQuotedPriceMuti001_hidden_quotaPageModel').val();
        if(quotaPageModel && quotaPageModel == 'dialogueResponse'){
            //$('#CfetsBondDealQuotedPriceMuti001_portfolioName').attr('disabled', true);
            $('#calculateField_exerYield').attr('disabled', true);
            $('#calculateField_yield').attr('disabled', true);
        }
        /**
         * 券面总额
         */
        $('#CfetsBondDealQuotedPriceMuti001_avaNum').on('change',function(){
            calcAmt();
            // proportionalMethod();
        });
        /**
         * 回复量
         */
        $('#CfetsBondDealQuotedPriceMuti001_responseAmt').on('change',function(){
            calcAmt();
        });
        /**
         * 单位净价
         */
        $('#CfetsBondDealQuotedPriceMuti001_unitCprice').on('change',function(){
            calcAmt('01');
        });
        /**
         * 到期收益率
         */
        $('#CfetsBondDealQuotedPriceMuti001_matureYield').on('change',function(){
            calcAmt('02');
        });
        /**
         * 行权收益率
         */
        $('#CfetsBondDealQuotedPriceMuti001_exerYield').on('change',function(){
            calcAmt('03');
        });
    }
    
    var  disabledElementByDzh = function(entrustList){
        //组合分配列表
        if (entrustList.length>0){
            // 回复对话报价
            if (quotaPageModel && (quotaPageModel == 'dialogueResponse')){
            }
            else {
                $('#CfetsBondDealQuotedPriceMuti001_settleSpreed').attr('disabled', true);
                // $('#CfetsBondDealQuotedPriceMuti001_mySide').attr('disabled', true);
                $('#CfetsBondDealQuotedPriceMuti001_settleDate').attr('disabled', true);
                $('#CfetsBondDealQuotedPriceMuti001_ps').attr('disabled', true);
                $('#CfetsBondDealQuotedPriceMuti001_finprodId').attr('disabled', true);
            }
        }
        else{
            if (!quotaPageModel || (quotaPageModel != 'dialogueResponse')) {
                //$('#CfetsBondDealQuotedPriceMuti001_settleSpreed').val('01').trigger('change').attr('disabled', false);
                $('#CfetsBondDealQuotedPriceMuti001_settleSpreed').trigger('change').attr('disabled', false);
                // $('#CfetsBondDealQuotedPriceMuti001_mySide').attr('disabled', false);
                $('#CfetsBondDealQuotedPriceMuti001_settleDate').attr('disabled', false);
                $('#CfetsBondDealQuotedPriceMuti001_ps').attr('disabled', false);
                $('#CfetsBondDealQuotedPriceMuti001_finprodId').attr('disabled', false);
            }
        }
    }

    /**
     * 代办中 不应该显示成交和拒绝按钮
     */
    var hideRefuseButton = function () {
        var quotaPageModel = $('#CfetsBondDealQuotedPriceMuti001_hidden_quotaPageModel').val();
        if(workflowId){
            if (quotaPageModel && quotaPageModel == 'dialogueResponse'){
                $('#CfetsBondDealQuotedPriceMuti001_btn_submit').remove();
            }else{
                $('#CfetsBondDealQuotedPriceMuti001_btn_done').remove();
            }
            $("#CfetsBondDealQuotedPriceMuti001_btn_refuse").hide();
        }
    }
    
    
    $(function () {
        // 初始化页面
        initPage();
        // 委托代码
        var entrustId = $('#CfetsBondDealQuotedPriceMuti001_hidden_entrustId').val();
        if (entrustId && entrustId != ''){
            $('#CfetsBondDealQuotedPriceMuti001_portfolioName').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_ps').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_finprodName').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_settleSpreed').attr('disabled', true);
            //add by lijie2
            if(PRICE_MODEL && PRICE_MODEL == PRICE_MODEL_PRM01){
                $('#CfetsBondDealQuotedPriceMuti001_unitCprice').attr('disabled', true);
                $('#CfetsBondDealQuotedPriceMuti001_matureYield').attr('disabled', true);
                $('#CfetsBondDealQuotedPriceMuti001_exerYield').attr('disabled', true);
                $("input[name='calculateField']").attr('disabled', true);
                $("input[name=calculateField][value=01]").prop("checked","true");
                layui.form.render();
            }else if(assetTypeOne == assetTypeOne_abs){
                $('#CfetsBondDealQuotedPriceMuti001_matureYield').attr('disabled', true);
                $('#CfetsBondDealQuotedPriceMuti001_exerYield').attr('disabled', true);
                $("input[name='calculateField']").attr('disabled', true);
                $("#CfetsBondDealQuotedPriceMuti001_hidden_calculateField").val("01");
                $("input[name=calculateField][value=01]").prop("checked","true");
                layui.form.render();
            }
            editFlag = false;
        }
        // 画面操作模式
        var quotaPageModel = $('#CfetsBondDealQuotedPriceMuti001_hidden_quotaPageModel').val();
        // 做市报价回复
        if (quotaPageModel && quotaPageModel == 'marketResponse'){
            $('#CfetsBondDealQuotedPriceMuti001_div_responseAmt').toggle(true);
            $('#CfetsBondDealQuotedPriceMuti001_div_settleCcy').toggle(false);
            if (ibuFlag_startDialogueQuoteWithNoEntrust != '02'){
                $('#CfetsBondDealQuotedPriceMuti001_portfolioName').attr('disabled', true);
            }
            $('#CfetsBondDealQuotedPriceMuti001_ps').attr('disabled', true);
            // $('#CfetsBondDealQuotedPriceMuti001_mySide').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_counterId').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_counterTrader').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_finprodName').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_finprodMarketId').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_settleSpreed').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_settleDate').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_avaNum').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_settleMethod').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_settleType').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_btn_done').toggle(false);
            $('#CfetsBondDealQuotedPriceMuti001_btn_refuse').toggle(false);
            $('#CfetsBondDealQuotedPriceMuti001_btn_save').toggle(false);
            $('#CfetsBondDealQuotedPriceMuti001_btn_submit').toggle(true);
            $('#CfetsBondDealQuotedPriceMuti001_btn_cancel').toggle(true);
        }
        // 对话报价回复(除委托代码外置灰)
        else if (quotaPageModel && quotaPageModel == 'dialogueResponse'){
            $('#CfetsBondDealQuotedPriceMuti001_div_settleCcy').toggle(true);
            $('#CfetsBondDealQuotedPriceMuti001_btn_done').toggle(true);
            $('#CfetsBondDealQuotedPriceMuti001_btn_refuse').toggle(true);
            $('#CfetsBondDealQuotedPriceMuti001_btn_save').toggle(false);
            $('#CfetsBondDealQuotedPriceMuti001_btn_submit').toggle(false);
            $('#CfetsBondDealQuotedPriceMuti001_btn_cancel').toggle(false);
            if (ibuFlag_startDialogueQuoteWithNoEntrust != '02'){
                $('#CfetsBondDealQuotedPriceMuti001_portfolioName').attr('disabled', true);
            }
            $('#CfetsBondDealQuotedPriceMuti001_mySideTrader').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_ps').attr('disabled', true);
            // $('#CfetsBondDealQuotedPriceMuti001_mySide').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_counterId').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_counterTrader').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_finprodName').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_settleSpreed').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_unitCprice').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_matureYield').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_exerYield').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_avaNum').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_settleMethod').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_settleType').attr('disabled', true);
            //无指令时，组合可改
            if (ibuFlag_startDialogueQuoteWithNoEntrust == '02'){
                var mySideTemp = $('#CfetsBondDealQuotedPriceMuti001_mySide').val();
                if(mySideTemp){
                    $('#CfetsBondDealQuotedPriceMuti001_responseMySide').val(mySideTemp);
                }
            }
        }
        // 对话报价发起和保存后双击
        else if (quotaPageModel && quotaPageModel == 'new'){
            $('#CfetsBondDealQuotedPriceMuti001_div_responseAmt').toggle(false);
            $('#CfetsBondDealQuotedPriceMuti001_div_settleCcy').toggle(true);
            if (mySideTrader && mySideTrader != ''){
                $('#CfetsBondDealQuotedPriceMuti001_mySideTrader').textSelect('setValue',mySideTrader);
            }
            if (counterTrader && counterTrader != ''){
                $('#CfetsBondDealQuotedPriceMuti001_counterTrader').textSelect('setValue',counterTrader);
            }
            if (ouTraderIdCanEditFlag === 'N'){
                $('#CfetsBondDealQuotedPriceMuti001_mySideTrader').attr('disabled', true);
            }
            if(isMain != "Y"){
                $('#CfetsBondDealQuotedPriceMuti001_btn_done').toggle(false);
                $('#CfetsBondDealQuotedPriceMuti001_btn_refuse').toggle(false);
                $('#CfetsBondDealQuotedPriceMuti001_btn_save').toggle(true);
                $('#CfetsBondDealQuotedPriceMuti001_btn_submit').toggle(true);
                $('#CfetsBondDealQuotedPriceMuti001_btn_cancel').toggle(true);
            }else{
                $('#CfetsBondDealQuotedPriceMuti001_btn_done').toggle(false);
                $('#CfetsBondDealQuotedPriceMuti001_btn_refuse').toggle(false);
                $('#CfetsBondDealQuotedPriceMuti001_btn_save').toggle(false);
                $('#CfetsBondDealQuotedPriceMuti001_btn_submit').toggle(false);
                $('#CfetsBondDealQuotedPriceMuti001_btn_cancel').toggle(false);
            }
        }// 对话报价发起和保存后双击
        else if (quotaPageModel && quotaPageModel == 'edit'){
            $('#CfetsBondDealQuotedPriceMuti001_div_settleCcy').toggle(true);
            // $('#CfetsBondDealQuotedPriceMuti001_mySide').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_mySideTrader').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_counterId').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_counterTrader').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_settleType').attr('disabled', false);
            $('#CfetsBondDealQuotedPriceMuti001_remark').attr('disabled', true);
            //修改无委托初始化增加不可修改项 add by yzg 20210123 start
            $('#CfetsBondDealQuotedPriceMuti001_portfolioName').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_ps').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti001_finprodName').attr('disabled', true);
            //修改无委托初始化增加不可修改项 add by yzg 20210123 end
            $('#CfetsBondDealQuotedPriceMuti001_btn_done').toggle(false);
            $('#CfetsBondDealQuotedPriceMuti001_btn_refuse').toggle(false);
            $('#CfetsBondDealQuotedPriceMuti001_btn_save').toggle(true);
            $('#CfetsBondDealQuotedPriceMuti001_btn_submit').toggle(true);
            $('#CfetsBondDealQuotedPriceMuti001_btn_cancel').toggle(true);

        }
        // 含权、资产一级类型
        var isRight = $('#CfetsBondDealQuotedPriceMuti001_hidden_isRight').val();
        if (isRight == yesNoFlag_yes){
            $('#CfetsBondDealQuotedPriceMuti001_div_exerYield').toggle(true);
        }
        else{
            $('#CfetsBondDealQuotedPriceMuti001_div_exerYield').toggle(false);
        }
        var assetType1 = $('#CfetsBondDealQuotedPriceMuti001_hidden_assetTypeOne').val();
        if (assetType1 == assetTypeOne_abs){
            $('#CfetsBondDealQuotedPriceMuti001_div_abs1').toggle(true);
            $('#CfetsBondDealQuotedPriceMuti001_div_abs2').toggle(true);
        }else{
            $('#CfetsBondDealQuotedPriceMuti001_div_abs1').toggle(false);
            $('#CfetsBondDealQuotedPriceMuti001_div_abs2').toggle(false);
        }

        /**
         * 资产简称
         */
        $('#CfetsBondDealQuotedPriceMuti001_finprodId').change(function(){
            finprodIdChangeAction();
        });

        /**
         * 清算速度
         */
        $('#CfetsBondDealQuotedPriceMuti001_settleSpreed').change(function(){
            settleSpreedChangeAction();
        });
        /**
         * 券面总额
         */
        $('#CfetsBondDealQuotedPriceMuti001_avaNum').blur(function(){
            calcAmt();
        });
        /**
         * 券面总额
         */
        $("#CfetsBondDealQuotedPriceMuti001_avaNum").change(function() {
            calcAmt();
            // proportionalMethod();
        });
        /**
         * 回复量
         */
        $('#CfetsBondDealQuotedPriceMuti001_responseAmt').blur(function(){
            calcAmt();
        });
        /**
         * 单位净价
         */
        $('#CfetsBondDealQuotedPriceMuti001_unitCprice').blur(function(){
            calcAmt('01');
            // 根据净价计算净价偏离度
            calDeviationDegree();
        });
        /**
         * 到期收益率
         */
        $('#CfetsBondDealQuotedPriceMuti001_matureYield').blur(function(){
            calcAmt('02');
        });
        /**
         * 行权收益率
         */
        $('#CfetsBondDealQuotedPriceMuti001_exerYield').blur(function(){
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
        $('#CfetsBondDealQuotedPriceMuti001_btn_save').click(function(){
            saveOrSubmitAction('save');
        });

        /**
         * 提交按钮按下处理
         */
        $('#CfetsBondDealQuotedPriceMuti001_btn_submit').click(function(){
            saveOrSubmitAction('submit');
        });

        /**
         * 成交
         */
        $('#CfetsBondDealQuotedPriceMuti001_btn_done').click(function(){
            saveOrSubmitAction('done');
        });

        /**
         * 拒绝
         */
        $('#CfetsBondDealQuotedPriceMuti001_btn_refuse').click(function(){
            saveOrSubmitAction('refuse');
        });

        /**
         * 本方
         */
        $('#CfetsBondDealQuotedPriceMuti001_mySide').change(function(){
            mySideChangeAction();
        });
        /**
         * 对手方方
         */
        $('#CfetsBondDealQuotedPriceMuti001_counterId').change(function(){
            counterIdChangeAction();
        });
        //拒绝按钮不应该在待办及审批环节出现
        hideRefuseButton();
    });

    var mySideChangeAction = function(){
        $('#CfetsBondDealQuotedPriceMuti001_mySideTrader').val('').trigger('change');
    }
    /**
     * 对手方
     */
    var counterIdChangeAction = function(){
        $('#CfetsBondDealQuotedPriceMuti001_counterTrader').val('').trigger('change');
    }

    /**
     * 净值偏离度
     */
    function calDeviationDegree() {
        if ($("#CfetsBondDealQuotedPriceMuti001_hidden_finprodId").val() && $("#CfetsBondDealQuotedPriceMuti001_unitCprice").val()) {
            var paramObj = {};
            paramObj.finprodId = $('#CfetsBondDealQuotedPriceMuti001_hidden_finprodId').val();
            if($('#CfetsBondDealQuotedPriceMuti001_finprodId').val()){
                paramObj.finprodMarketId = $('#CfetsBondDealQuotedPriceMuti001_finprodId').val();
            }
            paramObj.unitCprice = $("#CfetsBondDealQuotedPriceMuti001_unitCprice").val();
            Joyin.ajax(JoyinUtil.getRealPath('/invest/trade/bond/TrdBondInvestTrade003/calcDeviationDegree'), paramObj, function (data) {
                $("#deviationDegree").text(data);
            });
        } else {
            $("#deviationDegree").text("");
        }
    }


    var setDhDiv = function(entrustIdList){
        reset();
        $("#CfetsBondDealQuotedPriceMuti001_hidden_quotaPageModel").val('new');
        if(entrustIdList){
            $("#CfetsBondDealQuotedPriceMuti001_hidden_entrustId").val(entrustIdList).trigger('change');
        }
    }

    $("#CfetsBondDealQuotedPriceMuti001_hidden_entrustId").change(function () {
        var entrustId = $('#CfetsBondDealQuotedPriceMuti001_hidden_entrustId').val();
        if (entrustId && entrustId != ''){
            Joyin.ajax(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001/queryDialogueQuoteByEntrustIdList', {entrustId : entrustId}, function(result) {
                if (result.data){
                    $('#CfetsBondDealQuotedPriceMuti001_remark').val(result.data.remark);
                    $('#CfetsBondDealQuotedPriceMuti001_hidden_entrustAmt').val(result.data.entrustNum);
                    $('#CfetsBondDealQuotedPriceMuti001_hidden_finprodId').val(result.data.finprodId);
                    // 区分画面打开模式
                    var quotaPageModel = $('#CfetsBondDealQuotedPriceMuti001_hidden_quotaPageModel').val();
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

                    // 做市方回复
                    if (quotaPageModel != 'marketResponse'){
                        // 这些要素应该是做市方已有的信息
                        $('#CfetsBondDealQuotedPriceMuti001_mySide').textSelect('setValue',result.data.mySide);
                        $('#CfetsBondDealQuotedPriceMuti001_counterId').textSelect('setValue',result.data.counterId);
                        $('#CfetsBondDealQuotedPriceMuti001_counterTrader').textSelect('setValue',result.data.counterTrader);
                        $('#CfetsBondDealQuotedPriceMuti001_finprodId').textSelect('setValue',result.data.finprodMarketId);
                        $('#CfetsBondDealQuotedPriceMuti001_ps').val(result.data.ps).trigger('change');
                        $('#CfetsBondDealQuotedPriceMuti001_avaNum').val(result.data.avaNumStr).trigger('blur');
                        $('#CfetsBondDealQuotedPriceMuti001_settleDate').val(result.data.settleDateStr);
                    }else{
                        // 做市报价回复选择委托后回复量默认显示委托剩余额度
                        $('#CfetsBondDealQuotedPriceMuti001_responseAmt').val(result.data.avaNumStr).trigger('blur');
                    }
                    // 对话报价发起模式
                    $('#CfetsBondDealQuotedPriceMuti001_finprodId').textSelect('setValue',result.data.finprodMarketId);
                    $('#CfetsBondDealQuotedPriceMuti001_mySide').textSelect('setValue',result.data.mySide);
                    $('#CfetsBondDealQuotedPriceMuti001_mySideTrader').textSelect('setValue',result.data.mySideTrader);
                    $('#CfetsBondDealQuotedPriceMuti001_settleMethod').val(result.data.settleMethod).trigger('change');
                    $('#CfetsBondDealQuotedPriceMuti001_settleType').val(result.data.settleType).trigger('change');
                    $('#CfetsBondDealQuotedPriceMuti001_settleCcy').val(result.data.settleCcy).trigger('change');
                    $('#CfetsBondDealQuotedPriceMuti001_unitCprice').val(result.data.price).trigger('change');
                    $('#CfetsBondDealQuotedPriceMuti001_matureYield').val(result.data.matureYield).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_exerYield').val(result.data.exerYield).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_unitInt').val(result.data.unitInt).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_unitFprice').val(result.data.unitFprice).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_cpriceAmt').val(result.data.cpriceAmt).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_parValue').val(result.data.parValue).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_intAmt').val(result.data.intAmt).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_fpriceAmt').val(result.data.fpriceAmt).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_parValueAmt').val(result.data.parValueAmt).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_portfolioSeries').val(result.data.portfolioSeries).trigger('change');
                    $('#CfetsBondDealQuotedPriceMuti001_settleSpreed').val(result.data.settleSpreed).trigger('change');
                    if(result.data.cfetsBondDealDialogueQuoteDtoList){
                        portfolioIds=[];
                        // table.clear();
                        // for (var index in result.data.cfetsBondDealDialogueQuoteDtoList){
                        //     table.addRow(result.data.cfetsBondDealDialogueQuoteDtoList[index]);
                        // }
                        for (var index in result.data.cfetsBondDealDialogueQuoteDtoList){
                                portfolioIds.push(result.data.cfetsBondDealDialogueQuoteDtoList[index].portfolioId);
                        }
                        window.CfetsQuotedCommon001.clearTable();
                        window.CfetsQuotedCommon001.reloadPtlAllocation(result.data.cfetsBondDealDialogueQuoteDtoList);

                        // proportionalMethod();
                    }
                }
                calcAmt("01");
                if (parent.parent.CfetsBondDealQuotedPriceHq) {
                    parent.parent.CfetsBondDealQuotedPriceHq.querySecinfo(
                        $('#CfetsBondDealQuotedPriceMuti001_hidden_finprodId').val(),'','',
                        $("#CfetsBondDealQuotedPriceMuti001_settleCcy").val(),
                        $('#CfetsBondDealQuotedPriceMuti001_tradeDate').val(),'');
                    parent.parent.CfetsBondDealQuotedPriceHq.queryAmountInfoMuti(
                        $('#CfetsBondDealQuotedPriceMuti001_hidden_finprodId').val(),
                        portfolioIds,'',$('#CfetsBondDealQuotedPriceMuti001_settleCcy').val(),
                        $('#CfetsBondDealQuotedPriceMuti001_tradeDate').val(),
                        '');
                    parent.parent.CfetsBondDealQuotedPriceHq.calCanBuyAmount(portfolioIds,
                        $('#CfetsBondDealQuotedPriceMuti001_settleCcy').val(),
                        $('#CfetsBondDealQuotedPriceMuti001_tradeDate').val(),
                        $('#CfetsBondDealQuotedPriceMuti001_unitCprice').val());
                }
            });
        }
    });

    //     var proportionalMethod =function (){
    //         var dealAmt = $('#CfetsBondDealQuotedPriceMuti001_avaNum').val();
    //         if(!dealAmt){
    //             return;
    //         }
    //         //组合分配列表
    //         var ptlAllocationList = table.getAllRows();
    //         if(!ptlAllocationList||ptlAllocationList.length<1){
    //             return;
    //         }
    //         //升序排序
    //         ptlAllocationList = ptlAllocationList.sort(function (a,b) {
    //             return a.entrustAvaNum - b.entrustAvaNum;
    //         })
    //         //比例分配
    //         //分配汇总
    //         var avaTotal = 0;
    //         //剩余额度汇总
    //         var entrustAvaNumTotal = 0;
    //         $.each(ptlAllocationList,function (i,e) {
    //             entrustAvaNumTotal = JoyinMath.doAdd(entrustAvaNumTotal,e.entrustAvaNum);
    //         });
    //         $.each(ptlAllocationList,function (i,e) {
    // //                e.avaNum = JoyinMath.doMul(dealAmt,JoyinMath.doDiv(e.entrustAvaNum,entrustAvaNumTotal,6,1),2,1);
    //             e.avaNum = JoyinMath.doDiv(JoyinMath.doMul(dealAmt,e.entrustAvaNum,2,1),entrustAvaNumTotal,2,1);
    //             avaTotal = JoyinMath.doAdd(avaTotal,e.avaNum);
    //         });
    //         //尾差总数
    //         var tailDifference = JoyinMath.doMinus(dealAmt,avaTotal);
    //         handleTailDifference(tailDifference,ptlAllocationList,100);
    //         //刷新列表
    //         $.each(ptlAllocationList,function (i,e) {
    //             table.renderRow(e.LAY_TABLE_INDEX, {"avaNum": e.avaNum});
    //         });
    //     }
    //
    // /**
    //  * 尾差处理
    //  * @param tailDifference 尾差总数
    //  * @param list
    //  * @param unit 尾差单位
    //  */
    // var  handleTailDifference = function(tailDifference,ptlAllocationList,unit){
    //     //尾差比例
    //     var entrustIdArr = [];
    //     var tailDifferencePre = JoyinMath.doDiv(1,unit,unit.length-1);
    //     var length = ptlAllocationList.length;
    //     while(Number(tailDifference)>0&&length>0){
    //         if(Number(tailDifference)>=1){
    //             //尾差大于1 始终按委托条数平均拆分尾差
    //             tailDifferencePre = JoyinMath.doDiv(tailDifference,length,unit.length-1);
    //         }
    //         $.each(ptlAllocationList,function (i,e) {
    //             if(Number(e.avaNum) == Number(e.entrustAvaNum)||Number(tailDifference)<=Number(0)) {
    //                 if(entrustIdArr.indexOf(e.entrustId)<0){
    //                     entrustIdArr.push(e.entrustId);
    //                     length--;
    //                 }
    //                 return;
    //             }else{
    //                 e.avaNum = JoyinMath.doAdd(e.avaNum,tailDifferencePre);
    //                 tailDifference = JoyinMath.doMinus(tailDifference,tailDifferencePre);
    //             }
    //         });
    //     }
    // }
    //
    // var table=$("#cfetsQuoTedCommonTable").joyintable({
    //     data:cfetsBondDealDialogueQuoteDtoList==null?[]:JSON.parse(cfetsBondDealDialogueQuoteDtoList),
    //     searchOnInit: true,
    //     loadonce: true,
    //     page: false,
    //     addbtn: false,
    //     height : 120,
    //     cols:[
    //         {field:'entrustId', width:180, title: '指令代码',sortable: false,disabled:true}
    //         ,{field:'portfolioId', width:150,  title: '组合代码',edit:false,sortable: false,
    //             editOption:{type:'select', id:'portfolioId', multiple: false, showField:'text',searchable: true,
    //                 url: JoyinUtil.getRealPath('/business/common/CommonSelect/selectPortfolioId?act=true&&auth=false')
    //             }
    //         }
    //         ,{field:'portfolioName', width:160,  title: '组合名称',edit:false,sortable: false,
    //         }
    //         ,{field:'entrustAvaNum', width:160, title: '剩余额度（万元）',type:'number',sortable: false,edit:false}
    //         ,{field:'avaNum', width:150, title: '券面总额（万元）',edit:false,sortable: false,type:'number', digit:2}
    //         ,{field:'price', width:120, title: '净价(元)',edit:false,sortable: false,type:'number', digit:2}
    //         ,{field:'matureYield', width:120, title: '到期收益率%',edit:false,sortable: false,visible: true,type:'number', digit:2}
    //         ,{field:'exerYield', width:120, title: '行权收益率%',edit:false,sortable: false,type:'number', digit:2}
    //     ],
    //     editCell: function(value, data, field, cell, column){
    //         if(Number(value) > Number(data.entrustAvaNum)){
    //             table.renderRow(data.LAY_TABLE_INDEX, {"avaNum": tempAvaNum});
    //             Joyin.error("券面总额不得大于剩余额度!");
    //         }
    //     },
    //     beforeEditCell: function (preVal, field, rowData) {
    //         if (field === "avaNum") {
    //             tempAvaNum = preVal.replace(/\,/g, '');
    //         }
    //         return true;
    //     },
    //     done: function () {
    //         disabledElementByDzh(table.getAllRows());
    //     }
    // });

    var reset = function () {
        $("#CfetsBondDealQuotedPriceMuti001_hidden_entrustId").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti001_portfolioSeries").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti001_finprodId").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti001_ps").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti001_settleType").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti001_settleSpreed").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti001_tradeDate").val(sysDate).trigger("change");
        $("#CfetsBondDealQuotedPriceMuti001_settleDate").val(sysDate).trigger("change");
        $("#CfetsBondDealQuotedPriceMuti001_responseAmt").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti001_settleMethod").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti001_avaNum").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti001_unitCprice").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti001_unitInt").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti001_unitFprice").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti001_intAmt").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti001_exerYield").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti001_matureYield").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti001_fpriceAmt").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti001_parValue").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti001_parValueAmt").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti001_mySide").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti001_mySideTrader").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti001_counterId").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti001_counterTrader").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti001_remark").val("").trigger("change");
        window.CfetsQuotedCommon001.clearTable();
        layui.form.render(); // reload layui
        pageModel = PAGE_MODEL_NEW;
    };

    function getBaseRiskdata(item) {
        var obj = {
            tradeDirection: $('#CfetsBondDealQuotedPriceMuti001_ps').val(),
            timePoint: MY_SUP_TIME_TYPE_02,
            valDate: sysDate,
            assetType: FINPROD_TYPE_F01,
            isEnabled: '01',
            assetCode: $('#CfetsBondDealQuotedPriceMuti001_finprodId').val(),
            counterId: $('#CfetsBondDealQuotedPriceMuti001_counterId').val()
        }
        obj.ps = $('#CfetsBondDealQuotedPriceMuti001_ps').val();

        obj.price = $('#CfetsBondDealQuotedPriceMuti001_unitCprice').val();
        obj.ccy = 'CNY';
        return obj;
    }

    var getriskdata = function () {
        let dataList = [];
        let portfolioIdList = $.serializeToObject('#submitForm').cfetsBondDealDialogueQuoteDtoList;
        if (portfolioIdList.length > 0) {
            for (var index in portfolioIdList) {
                let item = portfolioIdList[index];
                let obj = getBaseRiskdata();
                obj.portfolioCode = item.portfolioId;
                obj.portfolioId = item.portfolioId;
                obj.entrustId = item.entrustId;
                if (item.avaNum) {
                    obj.tradeAmount = item.avaNum * 10000;
                }
                obj.fpriceAmt = obj.tradeAmount;
                dataList.push(obj);
            }
        }else{
            let obj = getBaseRiskdata();
            obj.portfolioCode="";
            if ($("#CfetsBondDealQuotedPriceMuti001_avaNum").val()) {
                obj.tradeAmount = $("#CfetsBondDealQuotedPriceMuti001_avaNum").val() * 10000;
            }
            dataList.push(obj);
        }

        let checkParam = {
            tradeDirection: ["ps", "CfetsBondDealQuotedPriceSingle.ps"],
            portfolioCode: ["portfolioId", "CfetsBondDealQuotedPriceSingle.portfolioId"],
            tradeAmount: ["tradeAmt", "CfetsBondDealQuotedPriceSingle.bondPrinamt"],
            assetType: ["assetType", "CfetsBondDealQuotedPriceSingle.assetType"],
            counterId: ["counterId", "CfetsBondDealQuotedPriceSingle.counterId"],
            data: dataList
        }
        checkParam.price = ['price', 'CfetsBondDealQuotedPriceSingle.unitCprice'];
        checkParam.ccy = ['ccy', '币种'];
        return checkParam;
    };

    function getSaveOrSubmitParams(operateType) {
        var object = $.serializeToObject('#submitForm', true);
        if(!object.calculateField){
            object.calculateField = $("#CfetsBondDealQuotedPriceMuti001_hidden_calculateField").val();
        }
        if(!object.finprodId){
            object.finprodId = $("#CfetsBondDealQuotedPriceMuti001_hidden_finprodId").val();
        }
        object.operatorType = operateType;
        object.isDzh = 'Y';
        return object;
    }

    function saveRiskResultdata(paraRiskResultdata) {
        riskResultdata = paraRiskResultdata;
    }
    function saveCluflag(paraCluflag) {
        cluflag = paraCluflag;
    }

    parent.CfetsBondDealQuotedPriceMuti001 = {
        setDhDiv: setDhDiv,
        getriskdata: getriskdata,
        getSaveOrSubmitParams: getSaveOrSubmitParams,
        reset: reset,
        riskResultdata: riskResultdata,
        cluflag: cluflag,
        saveRiskResultdata: saveRiskResultdata,
        saveCluflag: saveCluflag,
        disabledElementByDzh:disabledElementByDzh
    }
    CfetsBondDealQuotedPriceMuti001 = {
        setDhDiv: setDhDiv,
        getriskdata: getriskdata,
        getSaveOrSubmitParams: getSaveOrSubmitParams,
        reset: reset,
        riskResultdata: riskResultdata,
        cluflag: cluflag,
        saveRiskResultdata: saveRiskResultdata,
        saveCluflag: saveCluflag,
        disabledElementByDzh:disabledElementByDzh
    };

    window.componentLoadCallback = function(isDone, pagePath, params) {
        if (isDone) {
            calDeviationDegree();
        }
    };


}());