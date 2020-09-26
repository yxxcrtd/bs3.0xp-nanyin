/**
 * 计息组件JS
 * @auth zhangnian
 */
(function(){
    
    //获取本画面元素id
    var getElementById = function(id,type) {
        if(type=="div"){
            return $('#' + entityName_FinInterestFactor + '_' + id+'_'+'div');
        }else{
            return $('#' + entityName_FinInterestFactor + '_' + id);
        }
    };
    //获取付息信息画面元素id
    var getPayInterestElementById = function(id){
        return $('#' + entityName_PayInterest + '_' + id);
    }
    
    //获取行权计划画面元素id
    var getBusiFinExercisePlanElementById = function(id){
         return $('#' + entityName_BusiFinExercisePlan + '_' + id);
    }

    // 增加推荐方与推荐费率 add by renping 20191014 start
    //获取推荐方与推荐费率画面元素id
    var getBussFinRecommentElementById = function(id){
         return $('#' + entityName_BussFinRecomment + '_' + id);
    }
    // 增加推荐方与推荐费率 add by renping 20191014 end
    
    var getBussFinRepaymentElementById = function(id,type) {
        if(type=='div'){
            return $('#' + entityName_BussFinRepayment + '_' + id+'_'+type);
        }else{
            return $('#' + entityName_BussFinRepayment + '_' + id);
        }
    };
    
    //画面根据资产类型show or hide;
    var init = function(){
        showAll();
         if(INT_ASSET_TYPE==FINPROD_TYPE_TG_F17){
            //计划类资产
            getElementById('couponSpecies','div').hide();
            /*getElementById('basisStyle','div').hide();*/
            getElementById('rate','div').hide();
            getElementById('rateMax','div').hide();
            getElementById('rateMin','div').hide();
            getElementById('indexId','div').hide();
            getElementById('coefficient','div').hide();
            getElementById('spreadRate','div').hide();
            getElementById('resetType','div').hide();
            getElementById('observeBefDay','div').hide();
            getElementById('observeBefUnit','div').hide();
            getElementById('rateType','div').hide();
        }
        /*disrateType();*/
    }
    //展示所有元素
    var showAll = function(){
        getElementById('div').find('div').show();
    }
    
    /**
     * 基准利率规则change
     */
    var handleresetType = function(){
        var resetType = getElementById('resetType').val();
        var rateType = getElementById('rateType').val();
        if(rateType==INT_TYPE_02){
            if(resetType == RESET_TYPE_01){
                getElementById('observeBefDay').removeAttr('disabled');
                getElementById('observeBefUnit').removeAttr('disabled');
            }else{
                if (typeof init != 'undefined' && !init) {
                    getElementById('observeBefDay').val('').trigger('change');
                    getElementById('observeBefUnit').val('').trigger('change');
                }
                else {
                    getElementById('observeBefDay').trigger('change');
                    getElementById('observeBefUnit').trigger('change');
                }
                getElementById('observeBefDay').attr('disabled',true).trigger("change");
                getElementById('observeBefUnit').attr('disabled',true).trigger("change");
            }
        }else{
            if (typeof init != 'undefined' && !init) {
                getElementById('observeBefDay').val('').trigger('change');
                getElementById('observeBefUnit').val('').trigger('change');
            }
            else {
                getElementById('observeBefDay').trigger('change');
                getElementById('observeBefUnit').trigger('change');
            }
            getElementById('observeBefDay').attr('disabled',true).trigger("change");
            getElementById('observeBefUnit').attr('disabled',true).trigger("change");
        }
        //layui.form.render();
    }
    
    /**
     * 计息基础change
     */
    var handleBasis = function(){
        var basis = getElementById('basis').val();
        var couponSpecies = getElementById('couponSpecies').val();
        if(INT_ASSET_TYPE == FINPROD_TYPE_TG_F01){
            if(basis==BASIS_A_A){
                if(couponSpecies == COUPON_SPECIES_04){
                    getElementById('basisStyle').attr('initValue', INTPAY_RULE_01).val(INTPAY_RULE_01).prop('disabled', true).trigger('change'); // 利率处理方式
                }else{
                    getElementById('basisStyle').removeAttr('disabled',true).trigger("change");
                }
            }else{
                getElementById('basisStyle').attr('disabled',true).val('').trigger("change");
            }
        }else if(INT_ASSET_TYPE == FINPROD_TYPE_TG_F17||INT_ASSET_TYPE == FINPROD_TYPE_TG_F18){
            if(basis==BASIS_A_A_D){
                getElementById('basisStyle').attr('initValue', INTPAY_RULE_01).val(INTPAY_RULE_01).prop('disabled', true).trigger('change'); // 利率处理方式
            }else{
                getElementById('basisStyle').val("").prop('disabled', true).trigger('change');
            }
        }
    }
    /**
     * 息票品种change
     * 当息票品种选择为贴现、零息、利随本清时，父页面.是否含权债显示为“否”，且不可编辑。否则，父页面.是否含权债可编辑。
     * 当息票品种选择为贴现、零息、利随本清时，付息频率、推算方式、首次付息日清空，改为只读。否则，这些字段可编辑。
     * 当息票品种选择为贴现、零息，并且父页面的起息日、到期日、发行价格录入数据时，初始利率自动计算，其他要素为只读；否则，其他要素可编辑。
     * 当息票品种选择为利随本清时，利率类型为固定利率，只读。
     */
    var handleCouponSpecies = function(){
        var couponSpecies = getElementById('couponSpecies').val();
        if(couponSpecies == COUPON_SPECIES_02||couponSpecies == COUPON_SPECIES_03||couponSpecies == COUPON_SPECIES_04){
            //是否含权债置否且不可编辑
            $('#isRight').next().removeClass('layui-form-onswitch').trigger('change');
            $('#isRight').next().find('em').text('否');
            $('#isRight').attr('disabled',true).trigger('change');
            //隐藏行权计划
            getBusiFinExercisePlanElementById('div').hide();
            $('#busiFinExercisePlanDto_div').hide();
            //付息频率
            getPayInterestElementById('payCycle').val('').trigger('change');
            getPayInterestElementById('payCycle').attr('disabled',true).trigger('change');
            //推算方式 
            getPayInterestElementById('calcRule').val('').trigger('change');
            getPayInterestElementById('calcRule').attr('disabled',true).trigger('change');
          //add by wangpeng 20190828 修复债券类资产新建时选择利随本清时候 推算方式为空 strat 
            if(INT_ASSET_TYPE==FINPROD_TYPE_TG_F01&&couponSpecies == COUPON_SPECIES_04){
            	//推算方式 
                getPayInterestElementById('calcRule').val(CALC_RULE_01).trigger('change');
                getPayInterestElementById('calcRule').attr('disabled',true).trigger('change')
            }
          //add by wangpeng 20190828 end
            //首次付息日
            getPayInterestElementById('firstPayDate').val('').trigger('change');
            getPayInterestElementById('firstPayDate').attr('disabled',true).trigger('change');
        }else{
            $('#isRight').removeAttr('disabled',true).trigger('change');
            getPayInterestElementById('payCycle').removeAttr('disabled',true).trigger('change');
          //add by wangpeng 20190828 修复债券类资产新建时选择利随本清时候 推算方式为空 strat
// 初始化时会把字段清空 delete by renping 20191012 start
//            if(pageModel!=PAGE_MODEL_REFERENCE){
//            	 getPayInterestElementById('calcRule').val('').trigger('change');
//            }
// 初始化时会把字段清空 delete by renping 20191012 end
            //add by wangpeng 20190828 end
            getPayInterestElementById('calcRule').removeAttr('disabled',true).trigger('change')
            //getPayInterestElementById('firstPayDate').removeAttr('disabled',true).trigger('change');
        }
        if(couponSpecies == COUPON_SPECIES_02||couponSpecies == COUPON_SPECIES_03){
             //%,按钮同时出现
            var child=$(".begin-show").find("#FinInterestFactor_btn_calRate");
            if(child.length>0){
                $(".begin-show").css("width","116px")
            }
            //TODO
            //当息票品种选择为贴现、零息，并且父页面的起息日、到期日、发行价格录入数据时，初始利率自动计算，其他要素为只读；否则，其他要素可编辑
            getElementById('rateType').val('').trigger('change');
            getElementById('rateType').prop('disabled',true).trigger('change');
            getElementById('basis').val('').trigger('change');
            getElementById('basis').prop('disabled',true).trigger('change');
            getElementById('basisStyle').val('').trigger('change');
            getElementById('basisStyle').prop('disabled',true).trigger('change');
            getElementById('rateMax').val('').trigger('change');
            getElementById('rateMax').prop('disabled',true).trigger('change');
            getElementById('rateMin').val('').trigger('change');
            getElementById('rateMin').prop('disabled',true).trigger('change');
            getElementById('indexId').val('').trigger('change');
            getElementById('indexId').prop('disabled',true).trigger('change');
            getElementById('coefficient').val('').trigger('change');
            getElementById('coefficient').prop('disabled',true).trigger('change');
            getElementById('spreadRate').val('').trigger('change');
            getElementById('spreadRate').prop('disabled',true).trigger('change');
            getElementById('resetType').val('').trigger('change');
            getElementById('resetType').prop('disabled',true).trigger('change');
            getElementById('observeBefDay').val('').trigger('change');
            getElementById('observeBefDay').prop('disabled',true).trigger('change');
            //隐藏付息计划、还本计划
            getPayInterestElementById('div').hide();
            getBussFinRepaymentElementById('div').hide();
            //显示初始利率计算按钮
            $('#FinInterestFactor_btn_calRate').show();
            getElementById('rate').prop('disabled',true);
        }else{
            //%,按钮同时出现
            var child=$(".begin-show").find("#FinInterestFactor_btn_calRate");
            if(child.length>0){
                $(".begin-show").css("width","30px")
            }
            getElementById('basis').prop('disabled',false).trigger('change');
           /* getElementById('basisStyle').prop('disabled',false).trigger('change');*/
            getElementById('rateMax').prop('disabled',false).trigger('change');
            getElementById('rateMin').prop('disabled',false).trigger('change');
            getElementById('indexId').prop('disabled',false).trigger('change');
            getElementById('coefficient').prop('disabled',false).trigger('change');
            getElementById('spreadRate').prop('disabled',false).trigger('change');
            getElementById('resetType').prop('disabled',false).trigger('change');
            getElementById('observeBefDay').prop('disabled',false).trigger('change');
            getPayInterestElementById('div').show();
            getBussFinRepaymentElementById('div').show();
            $('#FinInterestFactor_btn_calRate').hide();
            getElementById('rate').prop('disabled',false);
        }
        if(couponSpecies == COUPON_SPECIES_04){
            //当息票品种选择为利随本清时，利率类型为固定利率，只读。
            getElementById('rateType').val(INT_TYPE_01).trigger('change');
            getElementById('rateType').prop('disabled',true).trigger('change');
        }
        //息票品种为附息时才能操作利率类型
        if(couponSpecies == COUPON_SPECIES_01){
            //getElementById('rateType').val('').trigger('change');
            getElementById('rateType').prop('disabled',false).trigger('change');
        }
        //禁用付息计划
        if(FinPayInterestStdInfo){
            FinPayInterestStdInfo.disableFinPayInterest();
        }
        //禁用还本计划
        if(BussFinRepayment){
            BussFinRepayment.disableBussFinRepay();
        }
        //单独禁用利率类型
        if(pageModel==PAGE_MODEL_REFERENCE){
            getElementById('rateType').prop('disabled',true).trigger('change');
        }
    }
    
    /**
     * 利率类型change  
     * 利率类型为浮动利率时  利率上限、利率下限、浮动利率、系数、利差可填，其他均不可填写！
     */
    var handleRateType = function(init){
        var rateType = getElementById('rateType').val();
        if(rateType == INT_TYPE_02){
            getElementById('rateMax').removeAttr('disabled',true).trigger("change");
            getElementById('rateMin').removeAttr('disabled',true).trigger("change");
            getElementById('indexId').removeAttr('disabled',true).trigger("change");
            getElementById('coefficient').removeAttr('disabled',true).trigger("change");
            getElementById('spreadRate').removeAttr('disabled',true).trigger("change");
            getElementById('resetType').removeAttr('disabled',true).trigger("change");
        }else{
            if (typeof init != 'undefined' && !init) {
                getElementById('rateMax').attr('disabled',true).val('').trigger("change");
                getElementById('rateMin').attr('disabled',true).val('').trigger("change");
                getElementById('indexId').attr('disabled',true).val('').trigger("change");
                getElementById('coefficient').attr('disabled',true).val('').trigger("change");
                getElementById('spreadRate').attr('disabled',true).val('').trigger("change");
                getElementById('resetType').attr('disabled',true).val('').trigger("change");
            }
            else {
                getElementById('rateMax').attr('disabled',true).trigger("change");
                getElementById('rateMin').attr('disabled',true).trigger("change");
                getElementById('indexId').attr('disabled',true).trigger("change");
                getElementById('coefficient').attr('disabled',true).trigger("change");
                getElementById('spreadRate').attr('disabled',true).trigger("change");
                getElementById('resetType').attr('disabled',true).trigger("change");
            }
        }
        handleresetType(init);
    }
    
    /**
     * 买断利率=借款利率-资产推荐费率，自动计算，不可修改
     */
    var calbuyOutRate = function(thisId){
        var loadRate = getElementById('loadRate').val();
        var referRate = $('#referRate').val();
        //存款类
        if(INT_ASSET_TYPE==FINPROD_TYPE_TG_F07){
            if(loadRate!=null && loadRate!=''&&referRate!=null && referRate!=''){
                var buyOutRate =   JoyinMath.doMinus(loadRate,referRate);
                if(BigNumber(buyOutRate)<=0){
                    Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0036',[JoyinUtil.getLabel("ValFinInterestFactor.loadRate"), JoyinUtil.getLabel("ValFinInterestFactor.referRate")]), function () {
                        if (thisId == 'loadRate') {
                            getElementById('loadRate').val('').change().focus();
                        }
                        else if (thisId == 'referRate') {
                            $('#referRate').val('').change().focus();
                        }
                    });
                    return;
                }
                $('#buyOutRate').val(buyOutRate).trigger('change');
            }else{
                $('#buyOutRate').val('').trigger('change');
            }
        }else if(INT_ASSET_TYPE==FINPROD_TYPE_TG_F17){
            //计划类资产
        }
        layui.form.render();
    }
    
    /**
     * 资产推荐费率 = 净投资收益率 - 买断利率，自动计算，不可修改
     */
    var calreferRate = function(){
        var investRate = getElementById('investRate').val();
        var buyOutRate = $('#buyOutRate').val();
        if(INT_ASSET_TYPE==FINPROD_TYPE_TG_F18||INT_ASSET_TYPE==FINPROD_TYPE_TG_F17||INT_ASSET_TYPE==FINPROD_TYPE_TG_F07){
            if(investRate!=null && investRate!=''&&buyOutRate!=null && buyOutRate!=''){
                var referRate =   JoyinMath.doMinus(investRate,buyOutRate,4);
                $('#referRate').val(referRate).trigger('change');
                /*if(BigNumber(referRate)<=0){
                    Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0004',[JoyinUtil.getLabel("FinInterestFactor.investRate"), JoyinUtil.getLabel("FinInterestFactor.buyOutRate")]));
                    return;
                }*/
            }else{
                $('#referRate').val('').trigger('change');
            }
        }
        layui.form.render();
    }

    // 增加推荐方与推荐费率 add by renping 20191014 start
    /**
     * 	买断利率=净投资收益率-资产推荐费率，其中资产推荐费率为多个资产提供方的推荐费率合计值
     */
    var calBuyOutRateByTable = function(thisId, tableData){
        var investRate = getElementById('investRate').val();
        /*if(INT_ASSET_TYPE==FINPROD_TYPE_TG_F07){
        	investRate = getElementById('loadRate').val();
        }*/
        if(INT_ASSET_TYPE==FINPROD_TYPE_TG_F18||INT_ASSET_TYPE==FINPROD_TYPE_TG_F17||INT_ASSET_TYPE==FINPROD_TYPE_TG_F07){
        	var allRows = getBussFinRecommentElementById('bussFinRecommentTable').data('joyintable').getAllRows();
            if(investRate!=null && investRate!=''&&allRows){
                var recfeeRate = 0;
            	allRows.forEach(function(value){
            		recfeeRate = JoyinMath.doAdd(recfeeRate,value.recfeeRate,4);
                })
                var buyOutRate = JoyinMath.doMinus(investRate,recfeeRate,4);
            	//存款类
                /*if(INT_ASSET_TYPE==FINPROD_TYPE_TG_F07){
                    if(BigNumber(buyOutRate)<=0){
                        Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0036',[JoyinUtil.getLabel("FinInterestFactor.loadRate"), JoyinUtil.getLabel("FinInterestFactor.referRate")]), function () {
                        	if (thisId == 'loadRate') {
                                getElementById('loadRate').val('').change().focus();
                            } else {
                                $('#buyOutRate').val(JoyinMath.doAdd(buyOutRate, thisId.recfeeRate, 4)).trigger('blur');
                            	thisId.recfeeRate = "0";
                            	tableData.renderRow(thisId.LAY_TABLE_INDEX, thisId);
                            }
                        });
                        return;
                    }
                }*/
                $('#buyOutRate').val(buyOutRate).trigger('blur');
            }else{
                $('#buyOutRate').val('').trigger('blur');
            }
        }
        layui.form.render();
    }
    // 增加推荐方与推荐费率 add by renping 20191014 end

    var calRate = function(){
        var object = {};var msg;
        object.issuePrice = $('#issuePrice').val();
        object.vdate = $('#vdate').val();
        object.mdate = $('#mdate').val();
        object.calendarId = $("#calendarId").val();
        object.couponSpecies= getElementById('couponSpecies').val();
        if(object.vdate==''||typeof object.vdate == 'undefined'){
            msg = JoyinUtil.getMessage('COMMON_E_0057',JoyinUtil.getLabel("ValFinBondTargetAsset002.vdate"));
        }
        if(object.mdate==''||typeof object.mdate == 'undefined'){
            msg = JoyinUtil.getMessage('COMMON_E_0057',JoyinUtil.getLabel("ValFinBondTargetAsset002.mdate"));
        }
        if(object.issuePrice==''||typeof object.issuePrice == 'undefined'){
            msg = JoyinUtil.getMessage('COMMON_E_0057',JoyinUtil.getLabel("ValFinBondTargetAsset002.issuePrice"));
        }
        if(object.couponSpecies==''||typeof object.couponSpecies == 'undefined'){
            msg = JoyinUtil.getMessage('COMMON_E_0057',JoyinUtil.getLabel("ValFinInterestFactor.couponSpecies"));
        }
        if(new Date(object.vdate).DateDiff('d',object.mdate)<0){
            msg = JoyinUtil.getMessage('BUSS_COM_E_0003',[JoyinUtil.getLabel("ValFinBondTargetAsset002.vdate"),JoyinUtil.getLabel("ValFinBondTargetAsset002.mdate")]);
        }
        if(object.calendarId==''||typeof object.calendarId == 'undefined'){
            msg = JoyinUtil.getMessage('COMMON_E_0057',JoyinUtil.getLabel("ValFinBondTargetAsset002.tradeCalendarCode"));
        }
        if(msg){
            Joyin.error(msg);
            return false;
        }else{
            Joyin.ajaxJson(basePath + 'valuation/component/element/ValFinInterestFactor/getyieldByPriceCalculator', object, function(result) {
                getElementById('rate').val(result);
            });
        }
    }
    
    // 公开接口
    window.ValFinInterestFactor = {
            calbuyOutRate: calbuyOutRate,
            calreferRate:calreferRate,
            // 增加推荐方与推荐费率 add by renping 20191014 start
            calBuyOutRateByTable:calBuyOutRateByTable,
            // 增加推荐方与推荐费率 add by renping 20191014 end
            handleCouponSpecies:handleCouponSpecies
    };
    
    $(function(){
        init();
        /*getElementById('loadRate').change(calbuyOutRate).trigger("change");*/
        /*getElementById('referRate').change(calbuyOutRate).trigger("change");*/
        //仅债券类有息票品种
        if(INT_ASSET_TYPE == FINPROD_TYPE_TG_F01){
            //getElementById('couponSpecies').change(handleCouponSpecies).trigger("change");
        }
        getElementById('resetType').change(handleresetType).trigger("change");
        if(INT_ASSET_TYPE == FINPROD_TYPE_TG_F01||INT_ASSET_TYPE == FINPROD_TYPE_TG_F17||INT_ASSET_TYPE == FINPROD_TYPE_TG_F18){
            getElementById('basis').change(handleBasis).trigger("change");
        }
        getElementById('rateType').on('change', function (e, init) {
            handleRateType(init);
        }).trigger("change", true);
        $('#FinInterestFactor_btn_calRate').click(calRate);
        
       
    });
}());