/**
 * 债券类资产中债报送组件JS
 * @auth zhangnian
 */
(function(){
    
    var getElementById = function(id) {
        return $('#' + entityName_BussFinBondRepInfo + '_' + id);
    };
    if(getElementById('finprodType2').val() == FINPROD_TYPE_TG_F18){
        getElementById('repPrivateprdFlag_div').show();
        getElementById('repBondtostockFlag_div').show();
        getElementById('project_other_div').show();
    }
    $(function() {
    	$("#"+issuerElement).change(function() {
    		
    		if($('#'+issuerElement).val() != null && $('#'+issuerElement).val() != ''){
    			var params = {'issuer': $('#'+issuerElement).val()};
                Joyin.ajaxJson(JoyinUtil.getRealPath('/component/report/asset/bond/BussFinBondRepInfo/getInfoByIssuer'), params, function (data) {
                    getElementById("repPartyType3").val(data.repPartyType3).trigger("change");
                    if(data.repPartyType2 == PARTY_TYPE2_01 || data.repPartyType2 == PARTY_TYPE2_02
                    		|| data.repPartyType2 == PARTY_TYPE2_03){
                    	getElementById("repPartyType2").val(data.repPartyType2).trigger("change");
                    }
                    else{
                    	getElementById("repPartyType2").val(PARTY_TYPE2_99).trigger("change");
                    }
                    if(getElementById("repPartyType1").val()==null||getElementById("repPartyType1").val()==""){
                        getElementById("repPartyType1").val(data.repPartyType1).trigger("change");
                    }
                    getElementById("repIndustry").val(data.repIndustry).trigger("change");
                    getElementById("repIndustryLevel2").val(data.repIndustryLevel2).trigger("change");
                });
    		}
    		else{
    			getElementById("repPartyType3").val('').trigger("change");
                getElementById("repPartyType2").val('').trigger("change");
                if(getElementById("repPartyType1").val()==null||getElementById("repPartyType1").val()==""){
                    getElementById("repPartyType1").val('').trigger("change");
                }
                getElementById("repIndustry").val('').trigger("change");
                getElementById("repIndustryLevel2").val('').trigger("change");
    		}
    	});
        getElementById('repPartyType1').change(function(){
            if(getElementById('repPartyType1').val()==SVR_PARTY_TYPE1_01){
                getElementById('repInstitSize_div').show();
            }else{
                getElementById('repInstitSize_div').val('').trigger("change");
                getElementById('repInstitSize_div').hide();
            }
        });
        /*getElementById('repAssetType').change(function(){
            if(getElementById('finprodType2').val() == FINPROD_TYPE_TG_F18){
                if(getElementById('repAssetType').val()) {
                    getElementById('content_one_div').nextAll().show();
                    getElementById('repAssetType_div').nextAll().show();
                }else{
                    getElementById('content_one_div').nextAll().hide();
                    getElementById('repAssetType_div').nextAll().hide();
                }
            }
        });*/
    })
}());