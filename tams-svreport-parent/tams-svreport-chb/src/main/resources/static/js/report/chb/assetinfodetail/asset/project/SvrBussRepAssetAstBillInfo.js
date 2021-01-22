/**
 * 非标资产(票据或信用)中债报送组件JS
 * @auth zhangnian
 */
(function(){
    
    var getElementById = function(id) {
        return $('#' + entityName_BussRepAssetAstBillInfo + '_' + id);
    };
    
    var repAssetTypeChange = function(){
        var repAssetType = getElementById('repAssetType').val();
        if(repAssetType){
            getElementById('content_one_div').nextAll().show();
            getElementById('repAssetType_div').nextAll().show();
        }else{
            getElementById('content_one_div').nextAll().hide();
            getElementById('repAssetType_div').nextAll().hide();
        }
        if(repAssetType==CHB_ASSET_TYPE_PROJECT_1201||repAssetType==CHB_ASSET_TYPE_PROJECT_1210){
            $('#bussRepAssetAstBillInfoDto_repAstBillType_div').show();
        }else{
            getElementById('repAstBillType').val('').trigger('change');
            $('#bussRepAssetAstBillInfoDto_repAstBillType_div').hide();
        }
    }

    var  initAstBill= function(){
        var assetTvalue2=$('#assetType2').val();
        if(repAssetType=='1210'){
            //类型默认05置灰不可修改
            $("#SvrBussRepAssetAstBillInfoDto_notstandSecType").val('05').trigger("change");
            $("#SvrBussRepAssetAstBillInfoDto_notstandSecType").attr("disabled",true);
        }else{
            $("#SvrBussRepAssetAstBillInfoDto_notstandSecType").val('').trigger("change");
            $("#SvrBussRepAssetAstBillInfoDto_notstandSecType").attr("disabled",false);

        }
    }
    $(function(){
        initAstBill();
        //资产/负债类别change
        /*getElementById('repAssetType').change(repAssetTypeChange).trigger('change');*/
    });


    
}());