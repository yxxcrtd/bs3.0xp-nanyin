/**
 * 债券类资产中债报送组件JS
 * @auth zhangnian
 */
(function(){


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
        var  initBussRepAssetAstBill= function(){
            var assetTvalueBill=$('#assetType2').val();
            if(assetTvalueBill=='1210'){
                //类型默认05置灰不可修改
                $("#svrBussRepAssetAstBillInfoDto_notstandSecType").val('05').trigger("change");
                $("#svrBussRepAssetAstBillInfoDto_notstandSecType").attr("disabled",true);
            }else if(assetTvalueBill=='1201'){
                $("#svrBussRepAssetAstBillInfoDto_notstandSecType").val('').trigger("change");
                $("#svrBussRepAssetAstBillInfoDto_notstandSecType").attr("disabled",false);
            }
        }
        starFinaciall();
         initBussRepAssetAstBill();


    })
}());