/**
 * 债券类资产中债报送组件JS
 * @auth zhangnian
 */
(function(){

    var getElementById = function(id) {
        return $('#' + entityName_BussFinBondRepInfo + '_' + id);
    };

    $(function() {
        var starFinaciall = function() {
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
        var  initFinanciall= function(){
            var assetTvalue2=$('#assetType2').val();
            if(assetTvalue2!='1403'){
                $("#svrBussRepFinanciallInfoDto_holdPurpose").val('99').trigger("change");
            }else{
                $("#svrBussRepFinanciallInfoDto_holdPurpose").val('').trigger("change");
            }
        }
        starFinaciall();
        initFinanciall();


    })
}());