(function() {
    function saveOrSubmit(operateType) {
        // 检查生成划款指令参数是否发生变化
        genCallback(operateType);
    }


    function benjing(object) {
        var finTradeProduct = {};
        finTradeProduct.finprodId = object.finprodId;
        finTradeProduct.branch = Number(bjBranch);
        finTradeProduct.isRushBack = object.isRushBack;
        object.finTradeProducts.push(finTradeProduct);
        var finTradeProductAdd = {};
        finTradeProductAdd.finprodId = object.finprodId;
        finTradeProductAdd.branch = Number(bjBranch);
        finTradeProductAdd.finprodType = finprodTypeGz;
        finTradeProductAdd.finprodType2 = finprodTypeTg;
        finTradeProductAdd.isAccrPenaltyInt = object.isAccrPenaltyInt;
        finTradeProductAdd.penaltyBaseType = object.penaltyBaseType;
        object.finTradeProductAdds.push(finTradeProductAdd);
        var finCashInfo = {};
        finCashInfo.finprodId = object.finprodId;
        finCashInfo.branch = Number(bjBranch);
        finCashInfo.finprodType = finprodType;
        object.finCashInfos.push(finCashInfo);
        var finCashCalcRule = {};
        finCashCalcRule.finprodId = object.finprodId;
        finCashCalcRule.branch = Number(bjBranch);
        finCashCalcRule.finprodType = finprodTypeGz;
        finCashCalcRule.finprodType2 = finprodTypeTg;
        if(object.yield){
            finCashCalcRule.yield = JoyinMath.doDiv(object.yield,100,4);
        }
        finCashCalcRule.basis = basis;
        var date = new Date($('#mdate').val());
        finCashCalcRule.effDate = date;
        object.finCashCalcRules.push(finCashCalcRule);
        var finCashPlan = {};
        finCashPlan.finprodId = object.finprodId;
        finCashPlan.branch = Number(bjBranch);
        finCashPlan.finprodType = finprodTypeGz;
        finCashPlan.finprodType2 = finprodTypeTg;
        finCashPlan.mdate = new Date(object.fxmdate);
        finCashPlan.vdate =  new Date(object.fxvdate);
        object.finCashPlans.push(finCashPlan);
    }

    function lixing(object) {
        var finTradeProduct = {};
        finTradeProduct.finprodId = object.finprodId;
        finTradeProduct.branch = Number(liBranch);
        finTradeProduct.isRushBack = object.isRushBack;
        object.finTradeProducts.push(finTradeProduct);
        var finTradeProductAdd = {};
        finTradeProductAdd.finprodId = object.finprodId;
        finTradeProductAdd.branch = Number(liBranch);
        finTradeProductAdd.finprodType = finprodTypeGz;
        finTradeProductAdd.finprodType2 = finprodTypeTg;
        finTradeProductAdd.isAccrPenaltyInt = object.isAccrPenaltyInt;
        finTradeProductAdd.penaltyBaseType = object.penaltyBaseType;
        object.finTradeProductAdds.push(finTradeProductAdd);
        var finCashInfo = {};
        finCashInfo.finprodId = object.finprodId;
        finCashInfo.branch = Number(liBranch);
        finCashInfo.finprodType = finprodType;
        object.finCashInfos.push(finCashInfo);
        var finCashCalcRule = {};
        finCashCalcRule.finprodId = object.finprodId;
        finCashCalcRule.finprodType = finprodTypeGz;
        finCashCalcRule.finprodType2 = finprodTypeTg;
        finCashCalcRule.branch = Number(liBranch);
        if(object.yield){
            finCashCalcRule.yield = JoyinMath.doDiv(object.yield,100,4);
        }
        finCashCalcRule.basis = basis;
        var date = new Date($('#mdate').val());
        finCashCalcRule.effDate = date;
        object.finCashCalcRules.push(finCashCalcRule);
        var finCashPlan = {};
        finCashPlan.finprodId = object.finprodId;
        finCashPlan.finprodType = finprodTypeGz;
        finCashPlan.finprodType2 = finprodTypeTg;
        finCashPlan.branch = Number(liBranch);
        finCashPlan.mdate = new Date(object.fxmdate);
        finCashPlan.vdate =  new Date(object.fxvdate);
        object.finCashPlans.push(finCashPlan);
    }
    
    function withOutBranch(object) {
        if(withoutBranch){
            var withoutBranchs = withoutBranch.split(",");
            $.each(withoutBranchs,function (index,element) {
                var finTradeProduct = {};
                finTradeProduct.finprodId = object.finprodId;
                finTradeProduct.branch = Number(element);
                finTradeProduct.isRushBack = object.isRushBack;
                object.finTradeProducts.push(finTradeProduct);
                var finTradeProductAdd = {};
                finTradeProductAdd.finprodId = object.finprodId;
                finTradeProductAdd.branch = Number(element);
                finTradeProductAdd.finprodType = finprodTypeGz;
                finTradeProductAdd.finprodType2 = finprodTypeTg;
                finTradeProductAdd.isAccrPenaltyInt = object.isAccrPenaltyInt;
                finTradeProductAdd.penaltyBaseType = object.penaltyBaseType;
                object.finTradeProductAdds.push(finTradeProductAdd);
                var finCashInfo = {};
                finCashInfo.finprodId = object.finprodId;
                finCashInfo.branch = Number(element);
                finCashInfo.finprodType = finprodType;
                object.finCashInfos.push(finCashInfo);
                var finCashCalcRule = {};
                finCashCalcRule.finprodId = object.finprodId;
                finCashCalcRule.branch = Number(element);
                finCashCalcRule.finprodType = finprodTypeGz;
                finCashCalcRule.finprodType2 = finprodTypeTg;
                if(object.yield){
                    finCashCalcRule.yield = JoyinMath.doDiv(object.yield,100,4);
                }
                finCashCalcRule.basis = basis;
                var date = new Date($('#mdate').val());
                finCashCalcRule.effDate = date;
                object.finCashCalcRules.push(finCashCalcRule);
                var finCashPlan = {};
                finCashPlan.finprodId = object.finprodId;
                finCashPlan.branch = Number(element);
                finCashPlan.finprodType = finprodTypeGz;
                finCashPlan.finprodType2 = finprodTypeTg;
                finCashPlan.mdate = new Date(object.fxmdate);
                finCashPlan.vdate =  new Date(object.fxvdate);
                object.finCashPlans.push(finCashPlan);
            })
        }
    }

    function genCallback(operateType) {
        if(bjBranch&&!liBranch){
            if($('#penaltyBaseType').val()=='02'||$('#penaltyBaseType').val()=='03'){
                Joyin.error("所维护的计息信息只有本金信息，不能选择罚息计息本金除逾期本金的其他字典!");
                return;
            }
        }
        if(!bjBranch&&liBranch){
            if($('#penaltyBaseType').val()=='01'||$('#penaltyBaseType').val()=='03'){
                Joyin.error("所维护的计息信息只有利息信息，不能选择罚息计息本金除逾期利息的其他字典!");
                return;
            }
        }
        if($('#isAccrPenaltyInt').val()=='Y'){
            if(!$('#penaltyBaseType').val()){
                Joyin.error("是否计提罚息为是的时候，罚息计息本金不能为空！");
                return;
            }
            if(!$('#yield').val()){
                Joyin.error("是否计提罚息为是的时候，罚息利率不能为空！");
                return;
            }
        }
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#informationForm', true);
        object.operateType = operateType;
        object.finTradeProducts = [];
        object.finTradeProductAdds = [];
        object.finCashInfos = [];
        object.finCashCalcRules = [];
        object.finCashPlans = [];
        object.finprodType = finprodType;
        object.workflowId = workflowId;
        object.doType = $('#doType').val();
        if(cashIds){
            object.cashIds = cashIds;
        }
        if($('#penaltyBaseType').val()) {
            if ($('#penaltyBaseType').val()=='01') {//本金
                object.bjBranch = bjBranch;
               benjing(object);
            } else if ($('#penaltyBaseType').val() == '02') {//利息
                object.liBranch = liBranch;
                if(withoutBranch){
                    object.withoutBranch =  withoutBranch;
                }
                lixing(object);
                if(withoutBranch){
                    withOutBranch(object);
                }
            } else {
                object.bjBranch = bjBranch;
                object.liBranch = liBranch;
                if(withoutBranch){
                    object.withoutBranch =  withoutBranch;
                }
                benjing(object);
                lixing(object);
                if(withoutBranch){
                    withOutBranch(object);
                }
            }
        }else{
            if(bjBranch){
                object.bjBranch = bjBranch;
                benjing(object);
            }
            if(liBranch){
                object.liBranch = liBranch;
                if(withoutBranch){
                    object.withoutBranch =  withoutBranch;
                }
                lixing(object);
                if(withoutBranch){
                    withOutBranch(object);
                }
            }
        }
        Joyin.ajaxJson(basePath + 'invest/period/trdoverduecalcupenalty/TrdOverdueCalcuPenalty002/check', object, function(result) {
            Joyin.confirm(result.data,function() {
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/period/trdoverduecalcupenalty/TrdOverdueCalcuPenalty002/save', object,"TrdOverdueCalcuPenalty002_btn_save");
                } else {
                    WorkFlow.doSubmitList(basePath + 'invest/period/trdoverduecalcupenalty/TrdOverdueCalcuPenalty002/save', object,"TrdOverdueCalcuPenalty002_btn_submit");
                }
            });
        });
    }

    function penaltyBaseTypeInvalue(value) {
        if (value == 'Y') {
            $('#penaltyBaseType').parent().parent().find('.layui-form-label').append('<i class="must">*</i>');
            $('#penaltyBaseType').attr('disabled', false);
            if ($('#oldPenaltyBaseType').val()) {
                $('#penaltyBaseType').val($('#oldPenaltyBaseType').val()).trigger('change');
            }
            if ($('#oldYield').val()) {
                $('#yield').val($('#oldYield').val());
            }
            $('#yield').parent().parent().find('.layui-form-label').append('<i class="must">*</i>');
            $('#yield').attr('disabled', false);
            $('#fxvdate').attr('disabled', false);
            changeFxVDate();
        } else {
            $('#penaltyBaseType').parent().parent().find('.layui-form-label').find('.must').remove();
            $('#penaltyBaseType').val('').attr('disabled', true).trigger('change');
            $('#yield').parent().parent().find('.layui-form-label').find('.must').remove();
            $('#yield').val('').attr('disabled', true).trigger('change');
            $('#fxvdate').val('').attr('disabled', true);
        }
    }

    function changeFxVDate() {
        var fxvdate = $('#fxvdate').val();
        if (fxvdate === null || fxvdate === '' || new Date(sysDate).DateDiff('d', fxvdate) < 0) {
            $('#fxvdate').val(sysDate);
        }
    }

    /**
     * 画面加载完成
     */
    $(function() {
        
        $('#fxmdate').on('change',function () {
            var fxvdate = $('#fxvdate').val();
            if($(this).val()&&fxvdate){
                if(DateUtil.parse($(this).val())<DateUtil.parse(fxvdate)){
                    $(this).val('');
                    Joyin.error('罚息截止日必须大于等于罚息开始日');
                }
            }
        })
        $('#isAccrPenaltyInt').on('change',function () {
            penaltyBaseTypeInvalue($(this).val());
        });
        $('#fxvdate').on('change',function () {
            if($('#isAccrPenaltyInt').val()==='Y'){
                changeFxVDate();
            }
        });

        if(pageModel!=PAGE_MODEL_REFERENCE){
            if(bjBranch){
                $('#bjBranch').val(bjBranch);
            }
            if(liBranch){
                $('#liBranch').val(liBranch);
            }
            if($('#mdate').val()){
                $('#oldMdate').val($('#mdate').val());
            }
            if($('#fxmdate').val()){
                $('#oldFxmdate').val($('#fxmdate').val());
            }
            if($('#fxmdate').val()){
                $('#oldFxmdate').val($('#fxmdate').val());
            }
            if(cashIds){
                $('#oldCashIds').val(cashIds);
            }
            if($('#penaltyBaseType').val()){
                $('#oldPenaltyBaseType').val($('#penaltyBaseType').val());
                if ($('#penaltyBaseType').val()=='01') {//本金
                    $('#oldBjBranch').val(bjBranch);
                }else if($('#penaltyBaseType').val()=='02'){
                    $('#oldLiBranch').val(liBranch);
                    if(withoutBranch){
                        $('#oldwithoutBranch').val(withoutBranch);
                    }
                }else{
                    $('#oldBjBranch').val(bjBranch);
                    $('#oldLiBranch').val(liBranch);
                    if(withoutBranch){
                        $('#oldwithoutBranch').val(withoutBranch);
                    }
                }
            }else{
                if(bjBranch){
                    $('#oldBjBranch').val(bjBranch);
                }
                if(liBranch){
                    $('#oldLiBranch').val(liBranch);
                    if(withoutBranch){
                        $('#oldwithoutBranch').val(withoutBranch);
                    }
                }
            }
            if($('#isAccrPenaltyInt').val()){
                penaltyBaseTypeInvalue($('#isAccrPenaltyInt').val());
            }
        }
        $('#TrdOverdueCalcuPenalty002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TrdOverdueCalcuPenalty002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());
