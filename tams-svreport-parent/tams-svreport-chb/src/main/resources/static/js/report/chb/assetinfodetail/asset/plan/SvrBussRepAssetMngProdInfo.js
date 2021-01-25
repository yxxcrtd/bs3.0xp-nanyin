/**
 * 债券类资产中债报送组件JS
 * @auth zhangnian
 */
(function(){

    var getElementById = function(id) {
        return $('#' + entityName + '_' + id);
    };
    if(getElementById('finprodType2').val() == FINPROD_TYPE_TG_F18){
        getElementById('repPrivateprdFlag_div').show();
        getElementById('repBondtostockFlag_div').show();
        getElementById('project_other_div').show();
    }
    $(function() {


        var startAssetMng = function() {
            // 加红星星
            // 查找label
            var _findPrevLabel = function(element, level) {
                var $el = element.prev();
                if (!$el.is('label') && level > 0) {
                    $el = $el.find('label');
                    if ($el.length === 0) {
                        return _findPrevLabel(element.parent(), level - 1);
                    }
                }
                return $el;
            };

            // 非查看模式
            // $('.must').each(function() {
            //     $(this).remove();
            // })
            $('*[required]').each(function() {
                var $el = $(this);
                var $target = _findPrevLabel($el, 3);
                if ($target.find('> .must').length === 0) {
                    $target.append($('<i class="must">*</i>'));
                }
            });
        }
        var initAssetMng = function(){
            alert("111");
            var assetTvalueAssetMng=$('#assetType2').val();
            //资产管理产品
            if(assetTvalueAssetMng=='1701'||assetTvalueAssetMng=='1703'||
                assetTvalueAssetMng=='1704'||assetTvalueAssetMng=='1706'||assetTvalueAssetMng=='1702'){
                $("#SvrBussRepAssetMngProdInfoDto_financialComissue").val('').trigger("change");
                $("#SvrBussRepAssetMngProdInfoDto_financialComissue").attr("disabled",true);
            }else{
                $("#SvrBussRepAssetMngProdInfoDto_financialComissue").attr("disabled",false);
            }
        }
        initAssetMng();
        startAssetMng();
    })
}());