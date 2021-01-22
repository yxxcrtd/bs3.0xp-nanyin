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
        var  initBussRepEquity = function(){
            var assetTvalueEquity=$('#assetType2').val();
            //权益类资产
            if(assetTvalueEquity=='1305'||assetTvalueEquity=='1302'){
                $("#SvrBussRepEquityInfoDto_equityQuit").val('').trigger("change");
                $("#SvrBussRepEquityInfoDto_equityQuit").attr("disabled",true);
            }else if(assetTvalueEquity=='1301'){
                $("#SvrBussRepEquityInfoDto_equityQuit").attr("disabled",false);
            }
        }
        starFinaciall();
        initBussRepEquity();


    })
}());