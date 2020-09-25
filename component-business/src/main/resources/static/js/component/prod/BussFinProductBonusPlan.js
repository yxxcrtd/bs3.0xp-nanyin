/**
 * 分红计划共通JS
 * @auth zhengyan
 */
(function(){
    
    var getElementById = function(id) {
        return $('#' + bussFinProductBonusPlanEntityName + '_' + id);
    };
    
    function init() {
        if (pageId === 'FinProductModule002') {
            getElementById('firstPayDate').prop('disabled', true);
        } else {
            getElementById('firstPayDate').prop('disabled', false);
        }
        profitTypeChange();
    }
    
    var profitTypeChange = function () {
        var selectedValueProfitType = $('#' + profitType).val();

        if (selectedValueProfitType) {
            // 货币型产品显示
            if (selectedValueProfitType === PROFIT_TYPE_03) {
                getElementById('div').show();
                getElementById('payType').val(BONUS_MODE_01).trigger('change');
            }
            else{
                getElementById('div').hide();
            }
        } else {
            getElementById('div').hide();
        }
    }
    
    var bonusPlanInitAction = function(input_finprodId){
    	Joyin.ajax(JoyinUtil.getRealPath('component/prod/BussFinProductBonusPlan/queryBonusPlanInfoByFinprodId'), {finprodId : input_finprodId}, function(data){
    		FinProductCommon.initPageDataAction(bussFinProductBonusPlanEntityName, data);
    	})
    }
    
    window.BussFinProductBonusPlan = {
		bonusPlanInitAction : bonusPlanInitAction
    }

    $(function() {
        init();
        // 推算方法联动处理
        getElementById('calcRule').change(function() {
        	var calcRule = getElementById('calcRule').val();
        	if (calcRule === CAL_RULE_05){
        		getElementById('firstPayDate').prop('disabled', false);
        	}else{
        		getElementById('firstPayDate').prop('disabled', true).val('').trigger('change');
        	}
        });
        
        // 分红规则改变时触发的事件
        getElementById('payCycle').change(function() {
            if(getElementById('payCycle').val() === '' || getElementById('payCycle').val() === BONUS_RULE_02) {
                getElementById('payCycleVal').prop('disabled', false);
                getElementById('payCycleUnit').prop('disabled', false);
                if(pageModel === PAGE_MODEL_NEW){
                	getElementById('calcRule').val(CAL_RULE_01).trigger('change')
                }
                getElementById('calcRule').prop('disabled', false);
                if (pageId != 'FinProductModule002') {
                    getElementById('firstPayDate').prop('disabled', false);
                }
                if(pageModel === PAGE_MODEL_NEW){
                	getElementById('endTdayRule').val(TDAY_RULE_04).trigger('change');
                    getElementById('payTdayRule').val(TDAY_RULE_02).trigger('change');
                }
                getElementById('endTdayRule').prop('disabled', false);
                getElementById('payTdayRule').prop('disabled', false);
            } else if(getElementById('payCycle').val() === BONUS_RULE_01) {
                getElementById('payCycleVal').prop('disabled', true).val('').trigger('change');
                getElementById('payCycleUnit').prop('disabled', true).val('').trigger('change');
                getElementById('calcRule').prop('disabled', true).val('').trigger('change');
                if (pageId != 'FinProductModule002') {
                    getElementById('firstPayDate').prop('disabled', true).val('').trigger('change');
                }
                getElementById('endTdayRule').prop('disabled', true).val('').trigger('change');
                getElementById('payTdayRule').prop('disabled', false);
            } else {
                getElementById('payCycleVal').prop('disabled', true).val('').trigger('change');
                getElementById('payCycleUnit').prop('disabled', true).val('').trigger('change');
                getElementById('calcRule').prop('disabled', true).val('').trigger('change');
                if (pageId != 'FinProductModule002') {
                    getElementById('firstPayDate').prop('disabled', true).val('').trigger('change');
                }
                getElementById('endTdayRule').prop('disabled', true).val('').trigger('change');
                getElementById('payTdayRule').prop('disabled', true).val('').trigger('change');
            }
        });
        // 父画面处理模式联动事件
        $('#' + profitType).change(profitTypeChange);
    });
}());