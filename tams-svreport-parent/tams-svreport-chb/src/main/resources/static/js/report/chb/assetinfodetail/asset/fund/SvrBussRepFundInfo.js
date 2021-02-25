/**
 * 分红计划组件
 */
(function(){
	/**
     * 画星星
     */
    var starBussRep = function() {
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
	
	var getElementById = function(id) {
		return $('#' + BussRepFundInfoentityName + '_' + id);
	};

    var  isGovernFundChangeType=function(){
		if(getElementById('isGovernFund').val()=='02'){
			getElementById('governInvestFund').val('').trigger("change");
			getElementById('governInvestFund').attr('disabled', true)
		}else{
			getElementById('governInvestFund').attr('disabled', false);
		}
	};

    var  initFundAssetType2=function(){
		var assetTvalueFund=$('#assetType2').val();
		//公募基金/私募基金
		if(assetTvalueFund=='2607'||assetTvalueFund=='2610'||assetTvalueFund=='2613'||
			assetTvalueFund=='2611'||assetTvalueFund=='2612'||assetTvalueFund=='2617'){
			$("#SvrBussRepFundInfoDto_isGovernFund").val('').trigger("change");
			$("#SvrBussRepFundInfoDto_isGovernFund").attr("disabled",false);
		}else{
			$("#SvrBussRepFundInfoDto_isGovernFund").val('').trigger("change");
			$("#SvrBussRepFundInfoDto_isGovernFund").attr("disabled",true);
		}
	}
	$(function() {

		isGovernFundChangeType();
		initFundAssetType2();
		starBussRep();
		getElementById('isGovernFund').change(function(){
			isGovernFundChangeType();
		});


	});

}());