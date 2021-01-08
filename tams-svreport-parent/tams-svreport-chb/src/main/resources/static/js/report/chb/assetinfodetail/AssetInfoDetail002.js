var QueryFunc=function(){
    return {
        serachKey:function(){
        }
    }
}();

(function() {
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
    /**
     * 中债报送show/hide
     */
    var showReportInfo = function(){
        var repAssetType = $('#assetType2').val();
        if (repAssetType=="1701"||repAssetType=="1702"||repAssetType=="1703"||repAssetType=="1704"
            ||repAssetType=="1705"||repAssetType=="1706") {
            $(".svrBussFinBondRepInfo_div").show();
            $(".svrBussRepPlacementInfo_div").hide();
        }else if((repAssetType=="1101"||repAssetType=="1102"||repAssetType=="1103"||repAssetType=="1104"
            ||repAssetType=="1105")){
            $(".svrBussFinBondRepInfo_div").hide();
            $(".svrBussRepPlacementInfo_div").show();
        }
        star();
        layui.form.render();
    }


    //控件加载完成后
    // window.componentLoadCallback = function(isDone, pagePath, params){
    //     if(isDone){
    //         if(typeof pageModel !== 'undefined'
    //             && (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_NEW)){
    //             $('.purSpeed').append('<i class="must">*</i>');
    //             $('.redSpeed').append('<i class="must">*</i>');
    //         }
    //         $('#assetType2').trigger('change');
    //     }
    // }
    var init = function(){

    }
    /**
     * 画面加载完成
     *
     */
    $(function() {

        $(".svrBussFinBondRepInfo_div").hide();
        $(".svrBussRepPlacementInfo_div").hide();
        init();
        //负债类别change事件
        $('#assetType2').change(function(){
            showReportInfo();
        });
    });
}());

