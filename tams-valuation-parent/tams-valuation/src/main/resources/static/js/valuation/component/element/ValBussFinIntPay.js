/**
 * 分红计划组件
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
	
	var getElementById = function(id) {
		return $('#' + BussFinIntPayEntityName + '_' + id);
	};
    /**
     * 分红频率改变时执行的方法
     * @param rateType
     * @returns
     */
    function dayValueChang() {
    	var payCycle = $('#'+BussFinIntPayEntityName+'_'+"payCycle").val();
    	if(payCycle==Cycle_01){
    		getElementById('dayValue').prop('disabled', true).trigger('change');
    		getElementById('dayValue').val('');
    		getElementById('dayValue').prop('required', false);
    	}
    	if(payCycle!=Cycle_01){
    		getElementById('dayValue').prop('disabled', false);
    		getElementById('dayValue').prop('required', true).trigger('change');
    	}
    	//用于基金标的页面
    	if(fundTypeElement == "fundType"){
    		var fundType = $("#"+fundTypeElement).val();
    		/*修改if条件 lishuping 2019/8/13 start*/
        	if(payCycle==Cycle_02 /*&& fundType == h_01*/){
        		getElementById('endTdayRuleDiv').show();
        		getElementById('payTdayRuleDiv').show();
        		getElementById('endTdayRuleDiv').val(TDAY_RULE_02).trigger('change');
        		getElementById('payTdayRuleDiv').val(TDAY_RULE_02).trigger('change');
    		$('#isCompoundInt').prop('disabled', false);
        	}
            /*修改if条件 lishuping 2019/8/13 end*/
        	else{
        		getElementById('endTdayRuleDiv').hide();
        		getElementById('payTdayRuleDiv').hide();
        		getElementById('endTdayRuleDiv').val('').trigger('change');
        		getElementById('payTdayRuleDiv').val('').trigger('change');
    		$('#isCompoundInt').val('').trigger('change').prop('disabled', true);
        	}
    	}
    	//用于计划类资产(货币型)页面
    	else{
    		if(payCycle==Cycle_02){
    			//计息/付息营业日准则显示
    			getElementById('endTdayRuleDiv').show();
        		getElementById('payTdayRuleDiv').show();
        		//是否按日复利必填
        		$("#isCompoundInt").prop('required', true);
        		$("#isCompoundInt").prop('disabled', false).trigger('change');
        		//分红日显示
        		getElementById('dayValueDiv').show();
        		getElementById('endTdayRuleDiv').val(TDAY_RULE_02).trigger('change');
        		getElementById('payTdayRuleDiv').val(TDAY_RULE_02).trigger('change');
        	}
        	if(payCycle!=Cycle_02){
        		//计息/付息营业日准则隐藏
        		getElementById('endTdayRuleDiv').hide();
        		getElementById('payTdayRuleDiv').hide();
        		getElementById('endTdayRuleDiv').val('').trigger('change');
        		getElementById('payTdayRuleDiv').val('').trigger('change');
        		//是否按日复利置灰
        		$("#isCompoundInt").prop('required', false).trigger('change');
        		$("#isCompoundInt").prop('disabled', true).trigger('change');
        		if(isNotFromToDoList)
        			$("#isCompoundInt").val('').trigger('change');
        		//分红日隐藏
        		getElementById('dayValueDiv').hide();
        	}
    	}
    	star();
    	layui.form.render();
    }
    //分红计划的显示或隐藏事件
	function bussFinIntPayElement(){
		if(fundTypeElement == "fundType"){
			var fundType = $("#"+fundTypeElement).val();
			var payCycle = $('#'+BussFinIntPayEntityName+'_'+"payCycle").val();
			if(fundType != h_01){
				getElementById('payType').val('').trigger('change');
				getElementById('payCycleVal').val('').trigger('change');
				getElementById('dayValue').val('').trigger('change');
				getElementById('endTdayRule').val('').trigger('change');
				getElementById('payTdayRule').val('').trigger('change');
			}
			if(payCycle==Cycle_02 && fundType == h_01){
	    		getElementById('endTdayRuleDiv').show();
	    		getElementById('payTdayRuleDiv').show();
	    		getElementById('endTdayRuleDiv').val(TDAY_RULE_02).trigger('change');
	    		getElementById('payTdayRuleDiv').val(TDAY_RULE_02).trigger('change');
	    	}
	    	else{
	    		getElementById('endTdayRuleDiv').hide();
	    		getElementById('payTdayRuleDiv').hide();
	    		getElementById('endTdayRuleDiv').val('').trigger('change');
	    		getElementById('payTdayRuleDiv').val('').trigger('change');
	    	}
		}
		else{
			getElementById('endTdayRuleDiv').show();
    		getElementById('payTdayRuleDiv').show();
    		getElementById('endTdayRuleDiv').val(TDAY_RULE_02).trigger('change');
    		getElementById('payTdayRuleDiv').val(TDAY_RULE_02).trigger('change');
		}
	}
	$(function() {
		//分红频率发生改变时
		getElementById('payCycle').change(function() {
			dayValueChang();
	    });
		//基金类型发生改变时fundTypeElement
		$("#"+fundTypeElement).change(function() {
			bussFinIntPayElement();
		})
	});

}());