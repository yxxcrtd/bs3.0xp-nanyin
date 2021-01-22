/**
 * 权益类资产中债报送组件JS
 * @auth zhangnian
 */
(function () {
    var getElementById = function (id) {
        return $('#' + entityName_BussRepEquityInfo + '_' + id);
    };
    if(getElementById('finprodType2').val() == FINPROD_TYPE_TG_F18){
        getElementById('repPrivateprdFlag_div').show();
        getElementById('repBondtostockFlag_div').show();
        getElementById('project_other_div').show();
    }
    //T01:从finStockInfo进入
    var gp1=['F10'];
    //T07:从
    var gp2=['MST23']
    if (initgp == 'F10'|| initgp == 'MST23') {
        $("#div01").hide();
        $("#div02").show();
        $("#div03").show();//modify by jiangpeng on 20200731 显示交易流通场所
        $("#"+initentity+"_repInvestStage").attr("disabled",true).trigger('change');
        //修改股票标的界面是否为质押融资点不了 By 赵东东 start
        //$("#"+initentity+"_repIsPledge").attr("disabled",true).trigger('change');

        //股票标的界面进入则显示 债转股，商业银行优先股 add by wj 20200408
        $("#"+initentity+"_repDeptEquity_div").show();
        $("#repDeptEquity").attr("required",true);
        $("#"+initentity+"_repBankSecPrefer_div").show();
        $("#repBankSecPrefer").attr("required",true);

    } else {
        /*$("#div01").show();
        getElementById('content_one_div').nextAll().hide();
        $('#div01').nextAll().hide();*/
        /*$("#div01").show();*/
        $("#div02").hide();
        $("#div03").show();
    }
    /*getElementById('repAssetType').change(function(){
        if(initgp != 'F10'&& initgp != 'MST23'){
            if(getElementById('repAssetType').val()){
                getElementById('content_one_div').nextAll().show();
                $('#div01').nextAll().show();
                $("#div02").hide();
                $("#div03").show();
            }else{
                getElementById('content_one_div').nextAll().hide();
                $('#div01').nextAll().hide();
            }
        }
    })*/

    getElementById('repPartyType1').change(function(){
        if(getElementById('finprodType2').val() == FINPROD_TYPE_TG_F18
        ||getElementById('finprodType2').val() == FINPROD_TYPE_TG_F01){
            if(getElementById('repPartyType1').val()==SVR_PARTY_TYPE1_01){
                getElementById('repInstitSize_div').show();
            }else{
                getElementById('repInstitSize_div').val('').trigger("change");
                getElementById('repInstitSize_div').hide();
            }
        }
    });
}());