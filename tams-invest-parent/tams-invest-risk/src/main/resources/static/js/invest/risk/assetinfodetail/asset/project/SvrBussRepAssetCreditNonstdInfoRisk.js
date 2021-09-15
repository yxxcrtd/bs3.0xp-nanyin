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
            getElementById('repExerciseDate').attr('disabled', true).trigger('change');
            getElementById('repFirstExerciseDate').attr('disabled', true).trigger('change');
            getElementById('repExercisePeriod').attr('disabled', true).trigger('change');
            if(getElementById('repExerciseType').val()=='' ||
                getElementById('repExerciseType').val()=='01'){
                getElementById('repExerciseDate').val('');
                getElementById('repFirstExerciseDate').val('');
                getElementById('repExercisePeriod').val('');
            }

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
            getElementById('repExercisePrice').val('').attr('disabled', true).trigger('change');
        }else if(getElementById('repContainType').val() != null &&　getElementById('repContainType').val() != ''){
            getElementById('repExercisePrice').attr('disabled', false).trigger('change');
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
    	if (getElementById('repDeferredInterestType').val() == '01') {
            //递延利息是否计息
            getElementById('repDeferredInterestInt').val('').attr('disabled', true).trigger('change');
        }else if(getElementById('repDeferredInterestType').val() != null &&　getElementById('repDeferredInterestType').val() != ''){
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

    //是否有浮动因子
    var  floatFactorFlagChangeType=function () {
      if(getElementById('floatFactorFlag').val()=='02'){
          getElementById('floatFactor').val('').trigger("change");
          getElementById('floatFactor').attr("disabled",true);

          getElementById('spreadRate').val('').trigger("change");
          getElementById('spreadRate').attr("disabled",true);

      }else if(getElementById('floatFactorFlag').val() != null &&　getElementById('floatFactorFlag').val() != ''){
          getElementById('floatFactor').attr("disabled",false);
          getElementById('spreadRate').attr("disabled",false);

      }
    }

    var  repPartRedemFlagChangeType=function(){
        if(getElementById('repPartRedemFlag').val()=='02'){
            getElementById('repPartRedemRate').val('').trigger("change");
            getElementById('repPartRedemRate').attr("disabled",true);
        }else if(getElementById('repPartRedemFlag').val() != null &&　getElementById('repPartRedemFlag').val() != ''){
            getElementById('repPartRedemRate').attr("disabled",false);
        }
    }

    var  initAssetCreditNonstd=function(){
        var assetTvalueCreditNonstd=$('#assetType2').val();
        if(assetTvalueCreditNonstd=='2201'||assetTvalueCreditNonstd=='2202'){
            //份额面值置灰不可填
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPrinBaseamt").val('').trigger("change");
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPrinBaseamt").attr("disabled",true);
            //法定到期日置灰不可填
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repFmdate").val('').trigger("change");
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repFmdate").attr("disabled",true);
            //计息类型置灰不可填
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repCouponSpecies").val('').trigger("change");
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repCouponSpecies").attr("disabled",true);
            //规则付息标识
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_interestRuleFlag").val('').trigger("change");
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_interestRuleFlag").attr("disabled",true);
            //利息发布方式
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repIntpayRule").val('').trigger("change");
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repIntpayRule").attr("disabled",true);
            //还本付息情况
           // $("#svrBussRepAssetCreditNonstdInfoRiskDto_repRepayCmt").val('').trigger("change");
          //  $("#svrBussRepAssetCreditNonstdInfoRiskDto_repRepayCmt").attr("disabled",true);

            //浮动因子
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_floatFactor").val('').trigger("change");
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_floatFactor").attr("disabled",true);
            //担保方式
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repGuaType").val('').trigger("change");
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repGuaType").attr("disabled",true);
            //抵质押物类型
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPledgeType").val('').trigger("change");
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPledgeType").attr("disabled",true);
            //抵质押物价值
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPledgeValue").val('').trigger("change");
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPledgeValue").attr("disabled",true);
            //担保性质
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repNatureGuarantee").val('').trigger("change");
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repNatureGuarantee").attr("disabled",true);
            //担保人与融资人关系
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repRelationFinan").val('').trigger("change");
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repRelationFinan").attr("disabled",true);
            //行权价格
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repExercisePrice").val('').trigger("change");
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repExercisePrice").attr("disabled",true);
            //递延利息是否计息
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repDeferredInterestInt").val('').trigger("change");
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repDeferredInterestInt").attr("disabled",true);
            //部分赎回比例%
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPartRedemRate").val('').trigger("change");
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPartRedemRate").attr("disabled",true);

            //是否有浮动因子
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_floatFactorFlag").val('').trigger("change");
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_floatFactorFlag").attr("disabled",true);
            //结构档次
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repStructGrade").val('').trigger("change");
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repStructGrade").attr("disabled",true);
            //还本方式
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repRepaymentType").val('').trigger("change");
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repRepaymentType").attr("disabled",true);
            //分期还本条款标识
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPeriodRepaymentFlag").val('').trigger("change");
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPeriodRepaymentFlag").attr("disabled",true);
            //基础资产类型
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repBasicAssetType").val('').trigger("change");
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repBasicAssetType").attr("disabled",true);
            //超额收益分配比例%
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repExcessProfitRate").val('').trigger("change");
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repExcessProfitRate").attr("disabled",true);
            //含权类型
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repContainType").val('').trigger("change");
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repContainType").attr("disabled",true);

            //永续条款类型
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPerpetualClauseType").val('').trigger("change");
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPerpetualClauseType").attr("disabled",true);

            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repDeferredInterestType").val('').trigger("change");
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repDeferredInterestType").attr("disabled",true);


            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPartRedemFlag").val('').trigger("change");
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPartRedemFlag").attr("disabled",true);


            $("#svrBussRepAssetCreditNonstdInfoRiskDto_creditOrgCode").val('').trigger("change");
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_creditOrgCode").attr("disabled",true);

            $("#svrBussRepAssetCreditNonstdInfoRiskDto_creditOrgName").val('').trigger("change");
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_creditOrgName").attr("disabled",true);



        }else{

            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPrinBaseamt").attr("disabled",false);
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repFmdate").attr("disabled",false);
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repCouponSpecies").attr("disabled",false);
            //规则付息标识
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_interestRuleFlag").attr("disabled",false);
            //利息发布方式
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repIntpayRule").attr("disabled",false);
            //还本付息情况
            //$("#svrBussRepAssetCreditNonstdInfoRiskDto_repRepayCmt").attr("disabled",false);
            //浮动因子
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_floatFactor").attr("disabled",false);
            //担保方式
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repGuaType").attr("disabled",false);
            //抵质押物类型
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPledgeType").attr("disabled",false);
            //抵质押物价值
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPledgeValue").attr("disabled",false);
            //担保性质
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repNatureGuarantee").attr("disabled",false);
            //担保人与融资人关系
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repRelationFinan").attr("disabled",false);
            //行权价格
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repExercisePrice").attr("disabled",false);
            //递延利息是否计息
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repDeferredInterestInt").attr("disabled",false);
            //部分赎回比例%
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPartRedemRate").attr("disabled",false);
            //是否有浮动因子
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_floatFactorFlag").attr("disabled",false);
            //结构档次
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repStructGrade").attr("disabled",false);
            //还本方式
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repRepaymentType").attr("disabled",false);
            //分期还本条款标识
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPeriodRepaymentFlag").attr("disabled",false);
            //基础资产类型
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repBasicAssetType").attr("disabled",false);
            //超额收益分配比例%
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repExcessProfitRate").attr("disabled",false);
            //含权类型
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repContainType").attr("disabled",false);
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPerpetualClauseType").attr("disabled",false);
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repDeferredInterestType").attr("disabled",false);
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPartRedemFlag").attr("disabled",false);
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_creditOrgCode").attr("disabled",false);
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_creditOrgName").attr("disabled",false);

        }

        if(assetTvalueCreditNonstd=='2201'||assetTvalueCreditNonstd=='2202'||assetTvalueCreditNonstd=='1202'||assetTvalueCreditNonstd=='1203'
            ||assetTvalueCreditNonstd=='1204'||assetTvalueCreditNonstd=='1206'||assetTvalueCreditNonstd=='1207'||assetTvalueCreditNonstd=='1208'||
            assetTvalueCreditNonstd=='1209'){
            //收/受益权类型，下拉框选择空白，且置灰不可修改
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repProfitType").val('').trigger("change");
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repProfitType").attr("disabled",true);
        }else{
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repProfitType").attr("disabled",false);
        }
    }

    $(function() {
        // layui.form.on('switch(repIndustryFlag2)',function(){
        //     repIndustryFlagChange2();
        // });
        // repIndustryFlagChange2();
        initAssetCreditNonstd();
        repGuaTypeChangeType();
        byRepContainType();
        floatFactorFlagChangeType();
        getElementById('repFinancierType1').change(function(){
            if(getElementById('repFinancierType1').val()=='01'){
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
        getElementById('repPartRedemFlag').change(function(){
            repPartRedemFlagChangeType();
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

        getElementById('floatFactorFlag').change(function(){
            floatFactorFlagChangeType();
        });


    });
}());