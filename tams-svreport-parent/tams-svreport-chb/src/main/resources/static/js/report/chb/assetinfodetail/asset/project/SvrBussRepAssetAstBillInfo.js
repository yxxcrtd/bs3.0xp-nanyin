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
    $(function(){
        //资产/负债类别change
        /*getElementById('repAssetType').change(repAssetTypeChange).trigger('change');*/
    });
    
}());