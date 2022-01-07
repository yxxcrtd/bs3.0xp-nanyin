$(function() {


    /**
     * 获取表单参数
     */
    var submitForm = function() {
        return $.serializeToObject('#submitForm');
    }


    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#submitForm', false);
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.pageId = pageId;

        Joyin.ajaxJson(basePath + 'valuation/trademanage/trdlendvaluationtrade/TrdLendValuationTrade002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'valuation/trademanage/trdlendvaluationtrade/TrdLendValuationTrade002/save', object, "TrdLendValuationTrade002_btn_save");
                } else {
                    WorkFlow.doSubmitList(basePath + 'valuation/trademanage/trdlendvaluationtrade/TrdLendValuationTrade002/save', object, "TrdLendValuationTrade002_btn_submit");
                }
            });
        })
    }



    /**
     * 画面加载完成
     */
    $(function() {
    	//初始化画面
        // init();
        // 绑定按钮事件
        $('#TrdLendValuationTrade002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TrdLendValuationTrade002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });

    });

    //应计利息联动处理     应计利息=本金金额*拆借利率*（到期日-起息日）*计息基础
    var intAmtCalculate=function () {
        if($('#prinAmt').val() !=null && $('#prinAmt').val() != ''
            && $('#intRate').val() != null && $('#intRate').val() != '' && $('#mdate').val() != null && $('#mdate').val() != ''
            && $('#vdate').val() != null && $('#vdate').val() != '' && $('#basis').val() != null && $('#basis').val() != ''){
            var result1=(DateUtil.parse($('#mdate').val())-DateUtil.parse($('#vdate').val()))/1000/60/60/24;   //计算 到期日-起息日
            var result2=JoyinMath.doMul($('#intRate').val(),0.01,4);
            var result3=JoyinMath.doMul($('#prinAmt').val(),result2,4);
            var result4=JoyinMath.doMul(result3,result1,4);
            var result6=$('#basis').val();
            if(result6=='A/365'){
                result6 = result6.split('/')[1];  //取365
            }else {
                result6=result6.split('/')[1];  //取360
            }
            var result5=JoyinMath.doMul(result4,result6,2);
            $('#intAmt').val(result5);
        }else {
            $('#intAmt').val('');
        }
    }

    // 本金金额 联动
    $('#prinAmt').change(function(){
        intAmtCalculate();

        settleAmtCalculate();
    });

    // 拆借利率 联动
    $('#intRate').change(function(){
        intAmtCalculate();
    });

    //到期日 联动
    $('#mdate').change(function(){
        intAmtCalculate();
    });

    //起息日 联动
    $('#vdate').change(function(){
        intAmtCalculate();
    });

    //计息基础 联动
    $('#basis').change(function(){
        intAmtCalculate();
    });



    //计算结算金额  结算金额=交易本金+应计利息+其他费用
    var settleAmtCalculate = function(){
        if($('#prinAmt').val() != null && $('#prinAmt').val() != ''
            && $('#dealFeeAmt').val() != null && $('#dealFeeAmt').val() != ''
            && $('#settFeeAmt').val() != null && $('#settFeeAmt').val() != ''
            && $('#otherAmt').val() != null && $('#otherAmt').val() != ''){
            var result1 = JoyinMath.doAdd($('#prinAmt').val(), $('#dealFeeAmt').val(),2);
            var result2 = JoyinMath.doAdd(result1, $('#settFeeAmt').val(),2);
            var result3 = JoyinMath.doAdd(result2, $('#otherAmt').val(),2);
            $('#settleAmt').val(result3);
            $('#dealAmt').val(result3);
        }else{
            $('#settleAmt').val('');
            $('#dealAmt').val('');
        }
    }


    //交易手续费 联动
    $('#dealFeeAmt').change(function(){
        settleAmtCalculate();
    });

    //结算服务费 联动
    $('#settFeeAmt').change(function(){
        settleAmtCalculate();
    });

    //其他费用 联动
    $('#otherAmt').change(function(){
        settleAmtCalculate();
    });

    //根据产品名称联动出产品代码
    $('#portfolioId').change(function(){
        var portfolioId = $('#portfolioId').val();
        if(portfolioId){
            var dataPort = $('#portfolioId').select2('data')[0].object;
            var finprodId = dataPort.finprodId;
            $('#portfolioName').val(finprodId).trigger('change');

        }else {
            $('#portfolioName').val('').trigger('change');
        }
    });

    //校验   交割日必须大于等于交易日
    var checkDate=function () {
        var tradeDate = $('#tradeDate').val();
        var settDate = $('#settDate').val();
        if(tradeDate&&settDate){
            if((new Date(tradeDate).DateDiff('d',settDate))<0){
                Joyin.error("交割日必须大于等于交易日");
                $('#settDate').val("");
            }
        }
    }
    $("#tradeDate").change(function () {
        checkDate();
    });

    $("#settDate").change(function () {
        checkDate();
    });


    //校验   到期日必须大于等于起息日
    var checkDate01=function () {
        var vdate = $('#vdate').val();
        var mdate = $('#mdate').val();
        if(vdate&&mdate){
            if((new Date(vdate).DateDiff('d',mdate))<0){
                Joyin.error("到期日必须大于等于起息日");
                $('#mdate').val("");
            }
        }
    }

    $("#vdate").change(function () {
        checkDate01();
    });

    $("#mdate").change(function () {
        checkDate01();
    });




}());