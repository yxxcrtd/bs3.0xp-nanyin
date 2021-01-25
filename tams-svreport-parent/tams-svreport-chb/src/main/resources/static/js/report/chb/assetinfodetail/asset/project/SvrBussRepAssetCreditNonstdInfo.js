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
        if (getElementById('repExerciseType').val() == '02') {
            //固定行权日
            getElementById('repExerciseDate').attr('disabled', false).trigger('change');
            getElementById('repFirstExerciseDate').val('').attr('disabled', true).trigger('change');
            getElementById('repExercisePeriod').val('').attr('disabled', true).trigger('change');
        } else if (getElementById('repExerciseType').val() == '03') {
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
        if(getElementById('repContainType').val()==''){
            //行权方式
            getElementById('repExerciseType').val('').attr('disabled', true).trigger('change');
        }else{
            getElementById('repExerciseType').attr('disabled', false).trigger('change');
        }
        if (getElementById('repContainType').val()=='02'||getElementById('repContainType').val()=='03'
        ||getElementById('repContainType').val()=='05'||getElementById('repContainType').val()=='06'||
            getElementById('repContainType').val()=='07'){
        //行权价格
            getElementById('_repExercisePrice').val('').attr('disabled', true).trigger('change');
        }else{
            getElementById('_repExercisePrice').attr('disabled', false).trigger('change');
        }
        // if(getElementById('repContainType').val()==''){
        //     //行权方式
        //     getElementById('repExerciseType').val('').attr('disabled', true).trigger('change');
        // }else  if(getElementById('repContainType').val()=='01'||getElementById('repContainType').val()=='03'){
        //     getElementById('repExerciseType').attr('disabled', false).trigger('change');
        //     //固定行权日
        //     getElementById('repExerciseDate').val('').attr('disabled', true).trigger('change');
        //    //首次行权日期
        //     getElementById('repFirstExerciseDate').val('').attr('disabled', true).trigger('change');
        //     //行权周期
        //     getElementById('repExercisePeriod').val('').attr('disabled', true).trigger('change');
        // }else{
        //     getElementById('repExerciseType').attr('disabled', false).trigger('change');
        //     getElementById('repExerciseDate').attr('disabled', false).trigger('change');
        //     //首次行权日期
        //     etElementById('repFirstExerciseDate').attr('disabled', false).trigger('change');
        //     //行权周期
        //     getElementById('repExercisePeriod').attr('disabled', false).trigger('change');
        // }
        // if (getElementById('repContainType').val()=='02'||getElementById('repContainType').val()=='03'
        // ||getElementById('repContainType').val()=='05'||getElementById('repContainType').val()=='06'||
        //     getElementById('repContainType').val()=='07'){
        // //行权价格
        //     getElementById('_repExercisePrice').val('').attr('disabled', true).trigger('change');
        // }else{
        //     getElementById('_repExercisePrice').attr('disabled', false).trigger('change');
        // }

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
        if (getElementById('repPerpetualClauseType').val() == '01' || getElementById('repPerpetualClauseType').val() == '02') {
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
        if(getElementById('repGuaType').val() == '01' || getElementById('repGuaType').val() == '02'||
            getElementById('repGuaType').val() == '05' || getElementById('repGuaType').val() == '99'){
             //抵质押物类型
            getElementById('repPledgeType').val('').attr('disabled', true).trigger('change');
            getElementById('repPledgeValue').val('').attr('disabled', true).trigger('change');
        }else{
            //抵质押物类型
            getElementById('repPledgeType').attr('disabled', false).trigger('change');
            getElementById('repPledgeValue').attr('disabled', false).trigger('change');
        }
        if(getElementById('repGuaType').val() == '03' || getElementById('repGuaType').val() == '04'||
            getElementById('repGuaType').val() == '05' || getElementById('repGuaType').val() == '99'){
            getElementById('repNatureGuarantee').val('').attr('disabled', true).trigger('change');
            getElementById('repRelationFinan').val('').attr('disabled', true).trigger('change');
        }else{
            getElementById('repNatureGuarantee').attr('disabled', false).trigger('change');
            getElementById('repRelationFinan').attr('disabled', false).trigger('change');
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

    var  repRateFlagChangeType=function(){
        var  repRateFlagval=getElementById('repRateFlag').val();
        if(repRateFlagval=='02'){
            getElementById('assetYield').val('').trigger("change");
            getElementById('assetYield').attr('disabled',true);
        }else{
            getElementById('assetYield').attr('disabled',false);
        }
    }

    var  repIndustryFlagChangeType=function(){
        var  repIndustryFlagval=getElementById('repIndustryFlag').val();
        if(repIndustryFlagval=='02'){
            getElementById('repMonitType').val('').trigger("change");
            getElementById('repMonitType').attr('disabled',true);

            getElementById('repMonTypeComm').val('').trigger("change");
            getElementById('repMonTypeComm').attr('disabled',true);
        }else{
            getElementById('repMonitType').attr('disabled',false);
            getElementById('repMonTypeComm').attr('disabled',false);

        }
    }

    var  repGuaTypeChangeType=function(){
         var  repGuaTypeVal=getElementById('repGuaType').val();

         if(repGuaTypeVal==''){
             getElementById('repGuaTypeRemark').val('').trigger("change");
             getElementById('repGuaTypeRemark').attr('disabled',true);
         }else{
             getElementById('repGuaTypeRemark').attr('disabled',false);
         }


    }

    var  initAssetCreditNonstd=function(){
        var assetTvalueCreditNonstd=$('#assetType2').val();
        if(assetTvalueCreditNonstd=='2201'||assetTvalueCreditNonstd=='2202'){
            //份额面值置灰不可填
            $("#SvrBussRepAssetCreditNonstdInfoDto_repPrinBaseamt").val('').trigger("change");
            $("#SvrBussRepAssetCreditNonstdInfoDto_repPrinBaseamt").attr("disabled",true);
            //法定到期日置灰不可填
            $("#SvrBussRepAssetCreditNonstdInfoDto_repFmdate").val('').trigger("change");
            $("#SvrBussRepAssetCreditNonstdInfoDto_repFmdate").attr("disabled",true);
            //计息类型置灰不可填
            $("#SvrBussRepAssetCreditNonstdInfoDto_repCouponSpecies").val('').trigger("change");
            $("#SvrBussRepAssetCreditNonstdInfoDto_repCouponSpecies").attr("disabled",true);
            //规则付息标识
            $("#SvrBussRepAssetCreditNonstdInfoDto_interestRuleFlag").val('').trigger("change");
            $("#SvrBussRepAssetCreditNonstdInfoDto_interestRuleFlag").attr("disabled",true);
            //利息发布方式
            $("#SvrBussRepAssetCreditNonstdInfoDto_repIntpayRule").val('').trigger("change");
            $("#SvrBussRepAssetCreditNonstdInfoDto_repIntpayRule").attr("disabled",true);
            //还本付息情况
            $("#SvrBussRepAssetCreditNonstdInfoDto_repRepayCmt").val('').trigger("change");
            $("#SvrBussRepAssetCreditNonstdInfoDto_repRepayCmt").attr("disabled",true);
            //是否有浮动因子
            $("#SvrBussRepAssetCreditNonstdInfoDto_floatFactorFlag").val('').trigger("change");
            $("#SvrBussRepAssetCreditNonstdInfoDto_floatFactorFlag").attr("disabled",true);
            //结构档次
            $("#SvrBussRepAssetCreditNonstdInfoDto_repStructGrade").val('').trigger("change");
            $("#SvrBussRepAssetCreditNonstdInfoDto_repStructGrade").attr("disabled",true);
            //还本方式
            $("#SvrBussRepAssetCreditNonstdInfoDto_repRepaymentType").val('').trigger("change");
            $("#SvrBussRepAssetCreditNonstdInfoDto_repRepaymentType").attr("disabled",true);
            //分期还本条款标识
            $("#SvrBussRepAssetCreditNonstdInfoDto_repPeriodRepaymentFlag").val('').trigger("change");
            $("#SvrBussRepAssetCreditNonstdInfoDto_repPeriodRepaymentFlag").attr("disabled",true);
            //基础资产类型
            $("#SvrBussRepAssetCreditNonstdInfoDto_repBasicAssetType").val('').trigger("change");
            $("#SvrBussRepAssetCreditNonstdInfoDto_repBasicAssetType").attr("disabled",true);
            //超额收益分配比例%
            $("#SvrBussRepAssetCreditNonstdInfoDto_repExcessProfitRate").val('').trigger("change");
            $("#SvrBussRepAssetCreditNonstdInfoDto_repExcessProfitRate").attr("disabled",true);
            //含权类型
            $("#SvrBussRepAssetCreditNonstdInfoDto_repContainType").val('').trigger("change");
            $("#SvrBussRepAssetCreditNonstdInfoDto_repContainType").attr("disabled",true);

            //永续条款类型
            $("#SvrBussRepAssetCreditNonstdInfoDto_repPerpetualClauseType").val('').trigger("change");
            $("#SvrBussRepAssetCreditNonstdInfoDto_repPerpetualClauseType").attr("disabled",true);

            $("#SvrBussRepAssetCreditNonstdInfoDto_repDeferredInterestType").val('').trigger("change");
            $("#SvrBussRepAssetCreditNonstdInfoDto_repDeferredInterestType").attr("disabled",true);


            $("#SvrBussRepAssetCreditNonstdInfoDto_repPartRedemFlag").val('').trigger("change");
            $("#SvrBussRepAssetCreditNonstdInfoDto_repPartRedemFlag").attr("disabled",true);


            $("#SvrBussRepAssetCreditNonstdInfoDto_creditOrgCode").val('').trigger("change");
            $("#SvrBussRepAssetCreditNonstdInfoDto_creditOrgCode").attr("disabled",true);

            $("#SvrBussRepAssetCreditNonstdInfoDto_creditOrgName").val('').trigger("change");
            $("#SvrBussRepAssetCreditNonstdInfoDto_creditOrgName").attr("disabled",true);



        }else{

            $("#SvrBussRepAssetCreditNonstdInfoDto_repPrinBaseamt").attr("disabled",false);
            $("#SvrBussRepAssetCreditNonstdInfoDto_repFmdate").attr("disabled",false);
            $("#SvrBussRepAssetCreditNonstdInfoDto_repCouponSpecies").attr("disabled",false);
            //规则付息标识
            $("#SvrBussRepAssetCreditNonstdInfoDto_interestRuleFlag").attr("disabled",false);
            //利息发布方式
            $("#SvrBussRepAssetCreditNonstdInfoDto_repIntpayRule").attr("disabled",false);
            //还本付息情况
            $("#SvrBussRepAssetCreditNonstdInfoDto_repRepayCmt").attr("disabled",false);
            //是否有浮动因子
            $("#SvrBussRepAssetCreditNonstdInfoDto_floatFactorFlag").attr("disabled",false);
            //结构档次
            $("#SvrBussRepAssetCreditNonstdInfoDto_repStructGrade").attr("disabled",false);
            //还本方式
            $("#SvrBussRepAssetCreditNonstdInfoDto_repRepaymentType").attr("disabled",false);
            //分期还本条款标识
            $("#SvrBussRepAssetCreditNonstdInfoDto_repPeriodRepaymentFlag").attr("disabled",false);
            //基础资产类型
            $("#SvrBussRepAssetCreditNonstdInfoDto_repBasicAssetType").attr("disabled",false);
            //超额收益分配比例%
            $("#SvrBussRepAssetCreditNonstdInfoDto_repExcessProfitRate").attr("disabled",false);
            //含权类型
            $("#SvrBussRepAssetCreditNonstdInfoDto_repContainType").attr("disabled",false);
            $("#SvrBussRepAssetCreditNonstdInfoDto_repPerpetualClauseType").attr("disabled",false);
            $("#SvrBussRepAssetCreditNonstdInfoDto_repDeferredInterestType").attr("disabled",false);
            $("#SvrBussRepAssetCreditNonstdInfoDto_repPartRedemFlag").attr("disabled",false);
            $("#SvrBussRepAssetCreditNonstdInfoDto_creditOrgCode").attr("disabled",false);
            $("#SvrBussRepAssetCreditNonstdInfoDto_creditOrgName").attr("disabled",false);

        }
    }

    $(function() {
        // layui.form.on('switch(repIndustryFlag2)',function(){
        //     repIndustryFlagChange2();
        // });
        // repIndustryFlagChange2();

        repGuaTypeChangeType();
        byRepContainType();
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
        //是否有预期收益率
        getElementById('repRateFlag').change(function(){
            repRateFlagChangeType();
        });
        //项目是否属于重点监控行业和领域
        getElementById('repIndustryFlag').change(function(){
            repIndustryFlagChangeType();
        });

        getElementById('repGuaType').change(function(){
            repGuaTypeChangeType();
        });
    });
}());