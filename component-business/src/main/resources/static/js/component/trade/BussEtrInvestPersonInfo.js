/**
 * 投资委托人员信息管理
 */
(function(){
    /**
     * 画星星
     */
    var star = function() {
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
        if (typeof pageModel !== 'undefined'
                && (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_NEW)) {
            $('.must').each(function() {
                $(this).remove();
            })
            $('*[required]').each(function() {
                var $el = $(this);
                var $target = _findPrevLabel($el, 3);
                if ($target.find('> .must').length === 0) {
                    $target.append($('<i class="must">*</i>'));
                }
            });
        }
    }
    
    $(function() {
        var name = '#'+entityName+'_counterId';
        if(single == "1"){
            $(name).attr('required',true);
        }
        star();
        $(name).change(function () {// 内部交易对手时，只能选择卖出
            if ($(name).select2('data') && $(name).select2('data')[0]) {
            	if(pageModel != PAGE_MODEL_REFERENCE){
            		var 交易对手 =  $(name).select2('data')[0].object;
                    if (!$(name).val()) {
                        // 【债券投资委托】交易对手选择内部交易对手时，清算速度为T+0不可改
                        $("#settleSpreed").prop("disabled", false).change();
                        $("#settleSpeed").prop("disabled", false).change();
                    }
                    // 【债券投资委托】交易对手选择内部交易对手时，清算速度为T+0不可改
                    if (交易对手 == 'N') {
                        $("#settleSpreed").val('01').prop("disabled", true).change();
                        $("#settleSpeed").val('01').prop("disabled", true).change();
                        $('#entrustIntegrateNum').val(0).prop("disabled", true).trigger("change");
                        $('#entrustOwnNum').val($("#entrustNum").val()).prop("disabled", true).trigger("change");
                    } else {
                        $("#settleSpreed").prop("disabled", false);
                        $("#settleSpeed").prop("disabled", false);
                        $('#entrustIntegrateNum').prop("disabled", false);
                        $('#entrustOwnNum').prop("disabled", false);
                    }
            	}
            }
        })
    });
    
}());