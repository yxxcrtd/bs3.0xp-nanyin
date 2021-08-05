/**
 * 其他资产中债报送组件JS
 * @auth zhangnian
 */
(function(){
    
    var getElementById = function(id) {
        return $('#' + entityName_BussRepOtherInvestTargetInfo + '_' + id);
    };
    
    var repIndustryFlagChange = function(){
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
    $(function() {
        layui.form.on('switch(repIndustryFlag)',function(){
            repIndustryFlagChange();
        });
        repIndustryFlagChange();
        getElementById('repFinancierType1').change(function(){
            if(getElementById('repFinancierType1').val()==SVR_PARTY_TYPE1_01){
                getElementById('repInstitSize_div').show();
            }else{
                getElementById('repInstitSize_div').val('').trigger("change");
                getElementById('repInstitSize_div').hide();
            }
        });
        getElementById('repGuaType').change(function(){
            byRepGuaType();
        });
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
}());