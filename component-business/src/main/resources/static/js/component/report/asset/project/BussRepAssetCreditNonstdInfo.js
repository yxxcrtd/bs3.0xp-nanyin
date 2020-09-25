/**
 * 信贷及非标资产中债报送组件JS
 * @auth zhangnian
 */
(function(){
    
    var getElementById = function(id) {
        return $('#' + entityName_BussRepAssetCreditNonstdInfo + '_' + id);
    };
    
    var repIndustryFlagChange2 = function(){
        
        var repIndustryFlag = getElementById('repIndustryFlag')[0].checked;
        if(repIndustryFlag){
            getElementById('repMonitType').attr('disabled',false);
            getElementById('repMonTypeComm').attr('disabled',false);
        }else{
            getElementById('repMonTypeComm').val('');
            getElementById('repMonitType').val('').trigger('change');
            getElementById('repMonTypeComm').attr('disabled',true);
            getElementById('repMonitType').attr('disabled',true);
        }
    }

    //行权方式事件
    function byRepExerciseType() {
        if (getElementById('repExerciseType').val() == SVR_EXERCISE_TYPE_02) {
            //固定行权日
            getElementById('repExerciseDate').attr('disabled', false).trigger('change');
            getElementById('repFirstExerciseDate').val('').attr('disabled', true).trigger('change');
            getElementById('repExercisePeriod').val('').attr('disabled', true).trigger('change');
        } else if (getElementById('repExerciseType').val() == SVR_EXERCISE_TYPE_03) {
            //首次行权日
            getElementById('repFirstExerciseDate').attr('disabled', false).trigger('change');
            //行权周期
            getElementById('repExercisePeriod').attr('disabled', false).trigger('change');
            getElementById('repExerciseDate').val('').attr('disabled', true).trigger('change');
        }else{
            getElementById('repExerciseDate').val('').attr('disabled', true).trigger('change');
            getElementById('repFirstExerciseDate').val('').attr('disabled', true).trigger('change');
            getElementById('repExercisePeriod').val('').attr('disabled', true).trigger('change');
        }
    }

    //含权类型事件
    function byRepContainType() {
        if (getElementById('repContainType').val() == SVR_CONTAIN_TYPE_01 || getElementById('repContainType').val() == SVR_CONTAIN_TYPE_04) {
            //行权价格
          //  getElementById('repExercisePrice').attr('disabled', false).trigger('change');
            $("#repContainType").attr("required", true).trigger('change');
        }else{
          //  getElementById('repExercisePrice').val('').attr('disabled', true).trigger('change');
            $("#repContainType").attr("required", false).trigger('change');

        }
    }
    //递延利息是否计息
    function byRepDeferredInterestType(){
    	if (getElementById('repDeferredInterestType').val() == SVR_DEFERRED_INTEREST_TYPE_01) {
            //递延利息是否计息
            getElementById('repDeferredInterestInt').val('').attr('disabled', true).trigger('change');
        }else{
            getElementById('repDeferredInterestInt').attr('disabled', false).trigger('change');
        }
    }
    //永续条款类型事件
    function byRepPerpetualClauseType() {
        if (getElementById('repPerpetualClauseType').val() == SVR_PERPETUAL_CLAUSE_TYPE_01 || getElementById('repPerpetualClauseType').val() == SVR_PERPETUAL_CLAUSE_TYPE_02) {
            //首次重定价日期
            getElementById('repFirstReprceDate').attr('disabled', false).trigger('change');
            //重定价周期
            getElementById('repReprceCycle').attr('disabled', false).trigger('change');
        }else{
            getElementById('repFirstReprceDate').val('').attr('disabled', true).trigger('change');
            getElementById('repReprceCycle').val('').attr('disabled', true).trigger('change');
        }
    }

    //担保方式事件
    function byRepGuaType() {
        if (getElementById('repGuaType').val() == SVR_GUA_TYPE_03 || getElementById('repGuaType').val() == SVR_GUA_TYPE_04) {
            //抵质押物类型
            getElementById('repPledgeType').attr('disabled', false).trigger('change');
            //抵质押物价值（元）
            getElementById('repPledgeValue').attr('disabled', false).trigger('change');
            getElementById('repNatureGuarantee').val('').attr('disabled', true).trigger('change');
            getElementById('repRelationFinan').val('').attr('disabled', true).trigger('change');
        } else if (getElementById('repGuaType').val() == SVR_GUA_TYPE_01 || getElementById('repGuaType').val() == SVR_GUA_TYPE_02) {
            //担保性质
            getElementById('repNatureGuarantee').attr('disabled', false).trigger('change');
            //担保人与融资人关系
            getElementById('repRelationFinan').attr('disabled', false).trigger('change');
            getElementById('repPledgeType').val('').attr('disabled', true).trigger('change');
            getElementById('repPledgeValue').val('').attr('disabled', true).trigger('change');
        }else{
            getElementById('repPledgeType').val('').attr('disabled', true).trigger('change');
            getElementById('repPledgeValue').val('').attr('disabled', true).trigger('change');
            getElementById('repNatureGuarantee').val('').attr('disabled', true).trigger('change');
            getElementById('repRelationFinan').val('').attr('disabled', true).trigger('change');
        }
    }
    
    var repPartRedemFlag2 = function(){
        
        var repPartRedemFlag = getElementById('repPartRedemFlag')[0].checked;
        if(repPartRedemFlag){
            getElementById('repPartRedemRate').attr('disabled',false);
        }else{
            getElementById('repPartRedemRate').val('').trigger('change');
            getElementById('repPartRedemRate').attr('disabled',true);
        }
    }

    $(function() {
        layui.form.on('switch(repIndustryFlag2)',function(){
            repIndustryFlagChange2();
        });
        repIndustryFlagChange2();
        getElementById('repFinancierType1').change(function(){
            if(getElementById('repFinancierType1').val()==SVR_PARTY_TYPE1_01){
                getElementById('repInstitSize_div').show();
            }else{
                getElementById('repInstitSize_div').val('').trigger("change");
                getElementById('repInstitSize_div').hide();
            }
        });
        /*getElementById('repAssetType').change(function(){
            if(getElementById('repAssetType').val()){
                getElementById('content_one_div').nextAll().show();
                getElementById('repAssetType_div').nextAll().show();
            }else{
                getElementById('content_one_div').nextAll().hide();
                getElementById('repAssetType_div').nextAll().hide();
            }
        });*/
        //行权方式事件 中债新增 20200407 start
        getElementById('repExerciseType').change(function(){
            byRepExerciseType();
        });
        //含权类型事件
        getElementById('repContainType').change(function(){
            byRepContainType();
        });
        //永续条款类型事件
        getElementById('repPerpetualClauseType').change(function(){
            byRepPerpetualClauseType();
        });
        //担保方式事件 中债新增 20200407 end
        getElementById('repGuaType').change(function(){
            byRepGuaType();
        });
        getElementById('repDeferredInterestType').change(function(){
        	byRepDeferredInterestType();
        });
        
        //部分赎回标识 事件
        layui.form.on('switch(repPartRedemFlag2)',function(){
        	repPartRedemFlag2();
        });
        
    });
}());