/**
 * 估值信息业务组件JS
 */
(function(){
	
	var getElementById = function(id) {
		return $('#' + valuationInfoEntityName + '_' + id);
	};
    /**
     * 利率类型改变时执行的方法
     * @param rateType
     * @returns
     */
    function rateType(pageInit) {
    	// 收益类型
    	var selectedValue = $('#' + profitTypeElement).val();
    	// 单位净值小数位
		var unitvalueDec = getElementById('unitvalueDec');
		// 利率小数位
		var rateDec = getElementById('rateDec');
		// 万份收益小数位
		var milionProfitDec = getElementById('milionProfitDec');
    	if (!selectedValue) {
    		// 收益类型选择为空时
    		// 所有项目活性
    		unitvalueDec.val('').prop('disabled', false); // 单位净值小数位
    		getElementById('unitvalueMode').prop('disabled', false); // 单位净值尾数处理方式
    		milionProfitDec.prop('disabled', false); // 万份收益小数位
    		getElementById('milionProfitDecMode').prop('disabled', false); // 万份收益尾数处理方式
    		rateDec.val('').prop('disabled', false); // 利率小数位
    		getElementById('ratevalueMode').prop('disabled', false); // 利率处理方式
    		getElementById('isCountingCurInt').prop('disabled', false); // 托管活期利息计提
    		getElementById('div').hide();
    	}else if(selectedValue == EXPECTED_RETURN){
    		// 当收益类型为“收益型”时，“利率小数位”默认保留2位，“利率处理方式”默认“四舍五入”，“托管活期是否计息”默认为“是”，其他字段字段置灰不可用；
    		if(!rateDec.val()) {    			
    			rateDec.val(2).trigger('change'); // 利率小数位   			
    		}
    		rateDec.prop('disabled', false); // 利率小数位
    		rateDec.prop('require', true); // 利率小数位必填
            getElementById('isCountingCurInt').prop('disabled', false); // 托管活期利息计提
//          if(!getElementById('ratevalueMode').val()) {
            if(!v_ratevalueMode) {
            	//getElementById('isCountingCurInt').prop('checked', true);
            	getElementById('ratevalueMode').prop('require', true);
            	getElementById('ratevalueMode').attr('initValue', MANT_PROC_MODE).val(MANT_PROC_MODE).prop('disabled', false).trigger('change'); // 利率处理方式             
            }
            unitvalueDec.val('').prop('disabled', true).trigger('change'); // 单位净值小数位
            getElementById('unitvalueMode').val('').prop('disabled', true).trigger('change'); // 单位净值尾数处理方式
            milionProfitDec.val('').prop('disabled', true).trigger('change'); // 万份收益小数位
            getElementById('milionProfitDecMode').val('').prop('disabled', true).trigger('change'); // 万份收益尾数处理方式
            getElementById('div').show();
            getElementById('dealLossModeDiv').hide();//隐藏当期亏损处理 add by yejiejie 2019-1-24
    	}
    	/*
    	else if(selectedValue == NET_WORTH){
    		//当收益类型为 “净值型”时，“单位净值小数位”保留4位，
    		//“单位净值位数处理方式”默认为“四舍五入”，“托管活期是否计息”默认为“是”，其他字段置灰不可用；
    		if(!unitvalueDec.val() && !pageInit) {
    			unitvalueDec.val(4).trigger('change'); // 单位净值小数位
    		}
    		unitvalueDec.prop('disabled', false); // 单位净值小数位
            getElementById('isCountingCurInt').prop('disabled', false); // 托管活期利息计提
            if(!pageInit) {
            	getElementById('isCountingCurInt').prop('checked', true);
                getElementById('unitvalueMode').val(MANT_PROC_MODE).prop('disabled', false).trigger('change'); // 单位净值尾数处理方式
            }
            milionProfitDec.val('').prop('disabled', true).trigger('change'); // 万份收益小数位
            getElementById('milionProfitDecMode').val('').prop('disabled', true).trigger('change'); // 万份收益尾数处理方式
            rateDec.val('').prop('disabled', true).trigger('change');// 利率小数位
            getElementById('ratevalueMode').val('').prop('disabled', true).trigger('change'); // 利率处理方式
            getElementById('dealLossMode').val('').parent().parent().css("display","none");//add by zhangweiqi 
            getElementById('div').show();
            
    	}else if(selectedValue == CURRENCY){
    		//当收益类型为“货币型”时，“万份收益小数位”默认保留4位，“万份收益位数处理方式”默认为“四舍五入”，其他字段置灰不可用。
    		if(!milionProfitDec.val() && !pageInit) {
    			milionProfitDec.val(4).trigger('change');// 万份收益小数位
    		}
    		milionProfitDec.prop('disabled', false);// 万份收益小数位
            unitvalueDec.val('').prop('disabled', true).trigger('change'); // 单位净值小数位
            getElementById('unitvalueMode').val('').prop('disabled', true).trigger('change'); // 单位净值尾数处理方式
            rateDec.val('').prop('disabled', true).trigger('change'); // 利率小数位
            getElementById('ratevalueMode').val('').prop('disabled', true).trigger('change'); // 利率处理方式
            getElementById('isCountingCurInt').prop('disabled', false); // 托管活期利息计提
            if(!pageInit) {
            	getElementById('isCountingCurInt').prop('checked', true);
            	getElementById('milionProfitDecMode').val(MANT_PROC_MODE).prop('disabled', false).trigger('change'); // 万份收益尾数处理方式
            }
            getElementById('dealLossMode').val('').parent().parent().css("display","inline");//add by zhangweiqi 
            getElementById('div').show();
    	}
    	*/
    	if(selectedValue == NET_WORTH || selectedValue == CURRENCY){
    		getElementById('milionProfitDec').attr('required', 'true');
    		getElementById('unitvalueDec').attr('required', 'true');
    		getElementById('milionProfitDecMode').attr('required', 'true');
    		getElementById('unitvalueMode').attr('required', 'true');
	    	if(!v_milionProfitDec){
	    		milionProfitDec.val(4).trigger('change');// 万份收益小数位
			}
			if(!v_unitvalueDec) {
				unitvalueDec.val(4).trigger('change');// 单位净值小数位
			}
			if(!v_milionProfitDecMode){
				getElementById('milionProfitDecMode').attr('initValue', MANT_PROC_MODE).val(MANT_PROC_MODE).prop('disabled', false).trigger('change'); // 万份收益尾数处理方式

			}
			if(!v_unitvalueMode){
				getElementById('unitvalueMode').attr('initValue', MANT_PROC_MODE).val(MANT_PROC_MODE).prop('disabled', false).trigger('change'); // 单位净值尾数处理方式
			}
			if(selectedValue == CURRENCY){//货币产品
				getElementById('dealLossMode').val('').parent().parent().css("display","block");
			}else{
				getElementById('dealLossMode').val('').parent().parent().css("display","none");
			}
	        rateDec.val('').parent().parent().css("display","none").trigger('change');
	        getElementById('ratevalueMode').val('').parent().parent().css("display","none").trigger('change');
	        getElementById('div').show();
    	}
    	// 额外参数：收益类型
    	getElementById('profitType').val(selectedValue);
    }
    
    var valInitAction = function(input_finprodId){
    	Joyin.ajax(JoyinUtil.getRealPath('component/prod/BussFinProductValuation/queryValParamByFinprodId'), {finprodId : input_finprodId}, function(data){
    		FinProductCommon.initPageDataAction(valuationInfoEntityName, data);
    	})
    }
    
    window.BussFinProductValuation = {
		valInitAction : valInitAction
    }
	
	$(function() {
		rateType(true);
        // 父画面处理模式联动事件
		$('#' + profitTypeElement).on('change', function () {
	        rateType();
	    });
	});
}());