/**
 * 存款资产中债报送信息组件JS
 * @auth zhangnian
 */
(function(){
    
    var getElementById = function(id) {
        return $('#' + entityName_BussRepDepositInfo + '_' + id);
    };

    function byRepDepoType() {
        if (getElementById('repDepoType').val() == SVR_DEPO_TYPE_06) {
            getElementById('repTargetType').attr('disabled', false).trigger('change');
            getElementById('repTarget').attr('disabled', false).trigger('change');
        } else if(getElementById('repDepoType').val() != null){
            getElementById('repTargetType').val('').trigger('change');
            getElementById('repTarget').val('').trigger('change');
            getElementById('repTargetType').attr('disabled', true).trigger('change');
            getElementById('repTarget').attr('disabled', true).trigger('change');
        }
    }

    function byRepAssetType() {
        if (getElementById('repAssetType').val() == SVR_ASSET_TYPE_SECOND_LEVEL_1002 || getElementById('repAssetType').val() == SVR_ASSET_TYPE_SECOND_LEVEL_1003) {
            getElementById('repDepoType').attr('disabled', false).trigger('change');
        } else if(getElementById('repAssetType').val() != null){
            getElementById('repDepoType').val('').trigger('change');
            getElementById('repDepoType').attr('disabled', true).trigger('change');
        }
    }

    // $(function(){
    // 	//本行存款、他行存款change事件
    //     getElementById('repAssetType').change(function(){
    //         byRepAssetType();
    //     });
    //     //是否为结构性存款change事件
    //     getElementById('repDepoType').change(function(){
    //         byRepDepoType();
    //     });
    // });
}());