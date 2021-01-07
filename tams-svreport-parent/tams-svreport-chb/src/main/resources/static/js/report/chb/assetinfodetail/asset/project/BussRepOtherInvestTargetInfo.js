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