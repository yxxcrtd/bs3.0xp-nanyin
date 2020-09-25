/**
 * 业务基准共通JS
 * @auth zhengyan
 */
(function(){
    var getElementById = function(id) {
        return $('#' + 'bussBenchMarkFloatInfoDto' + '_' + id);
    };
    
    /*
    var profitTypeChange = function () {
        var selectedValueProfitType = $('#' + profitType).val();
        if (selectedValueProfitType) {
            // 非预期收益型
            if (selectedValueProfitType == PROFIT_TYPE_01) {
                getElementById('div').show();
            }
            else{
                getElementById('div').hide();
            }
        } else {
            getElementById('div').hide();
        }
    }*/
    
    var benchMarkFloatInitAction = function(input_finprodId){
    	Joyin.ajax(JoyinUtil.getRealPath('component/prod/BussBenchMarkInfo/queryBenchMarkInfoByFinprodIdAndBaseRuleType'), {finprodId : input_finprodId, baseRuleType : '02'}, function(data){
    		FinProductCommon.initPageDataAction('bussBenchMarkFloatInfoDto', data);
    	})
    }
    //净值周期型非多固定期限使用
    var benchMarkFloatInitActionForCycle = function(input_finprodId, input_dealMode){
    	Joyin.ajax(JoyinUtil.getRealPath('component/prod/BussBenchMarkInfo/queryBenchMarkInfoByFinprodIdAndBaseRuleType'), {finprodId : input_finprodId, baseRuleType : '02', dealMode : input_dealMode}, function(data){
    		FinProductCommon.initPageDataAction('bussBenchMarkFloatInfoDto', data);
    	})
    }
    window.BussBenchMarkFloatInfo = {
		benchMarkFloatInitAction : benchMarkFloatInitAction,
		benchMarkFloatInitActionForCycle : benchMarkFloatInitActionForCycle
    }
    
    /**
     * 产品处理模式联动事件
     */
    /*var dealModeChange = function (){
        //收益类型为净值型 且产品处理模式为分档 改界面不显示  其他场景显示
        if($('#' + profitType).val() === PROFIT_TYPE_01 && $('#' + dealModeElement).val() === DISPOSE_PATTERN_11){
            getElementById('rateType').val('').trigger('change');
            getElementById('resetType').val('').trigger('change');
            getElementById('div').hide();
        } else {
            getElementById('div').show();
        }
    }*/
    
    $(function() {
        // 利率类型改变时触发的事件
        getElementById('rateType').change(function() {
            if(getElementById('rateType').val() === '') {
                getElementById('rate').val('').trigger('change').prop('disabled', false);
                getElementById('indexId').val('').trigger('change').prop('disabled', false);
                getElementById('coefficient').val('').trigger('change').prop('disabled', false);
                getElementById('spreadRate').val('').trigger('change').prop('disabled', false);
                getElementById('resetType').val('').trigger('change').prop('disabled', false);
            } else if(getElementById('rateType').val() === INT_TYPE_01) {
                getElementById('rate').trigger('change').prop('disabled', false);
                getElementById('indexId').val('').trigger('change').prop('disabled', true);
                getElementById('coefficient').val('').trigger('change').prop('disabled', true);
                getElementById('spreadRate').val('').trigger('change').prop('disabled', true);
                //当利率为固定利率时，业绩基准重置规则要置灰 new 20191207 cxq
                getElementById('resetType').val('').trigger('change').prop('disabled', true);                
                //当利率为固定利率时，业绩基准重置规则不要置灰
               // getElementById('resetType').val('').trigger('change').prop('disabled', false);
            } else {
                getElementById('rate').val('').trigger('change').prop('disabled', true);
                getElementById('indexId').trigger('change').prop('disabled', false);
                if(!getElementById('coefficient').val()){
                    getElementById('coefficient').val('1.00').trigger('change').prop('disabled', false);
                        }else{
                    	getElementById('coefficient').trigger('change').prop('disabled', false);
                    }
                getElementById('spreadRate').trigger('change').prop('disabled', false);
                getElementById('resetType').val('').trigger('change').prop('disabled', false);
            }
        });
        /*
        profitTypeChange();
        // 父画面收益类型联动事件
        $('#' + profitType).change(profitTypeChange);
        //产品处理模式联动事件
        dealModeChange();
        $('#' + dealModeElement).change(dealModeChange);*/
    });
}());