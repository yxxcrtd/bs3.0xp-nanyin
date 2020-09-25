/**
 * 组合现金、持仓查询业务共通
 */
(function(){
    var entityName = ENTITY_NAME;
    var getElementById = function(id) {
        return $('#' + entityName + '_' + id);
    };
    var init = function () {
    };
    var showPosition = function (autoHide) {
        getElementById('div_position').show();
        if(autoHide){
            getElementById('div_cash').hide();
            getElementById('div_channelCash').hide();
        }
    };
    var hidePosition = function () {
        getElementById('div_position').hide();
    };
    var showCash = function (autoHide) {
        getElementById('div_cash').show();
        getElementById('div_channelCash').show();
        if(autoHide){
            getElementById('div_position').hide();
        }
    };
    
    //计划类利率型不需要通道剩余现金 新增 张庚林 2019/07/02 start
    var showCash2 = function (autoHide) {
        getElementById('div_cash').show();
        getElementById('div_channelCash').show();
        if(autoHide){
            getElementById('div_position').hide();
            getElementById('div_channelCash').hide();
        }
    };
  //计划类利率型不需要通道剩余现金 新增 张庚林 2019/07/02 end
    var hideCash = function () {
        getElementById('div_cash').hide();
        getElementById('div_channelCash').hide();
    };
    var updateCash = function (func) {
        var params = func();
        if(params.portfolioId && params.portfolioId.trim() 
                && params.calendarId && params.ccy && params.tradeDate){
            params.acctTypeList = ACCT_TYPE_A01 + "," + ACCT_TYPE_A07;
            Joyin.ajaxJson(JoyinUtil.getRealPath('/component/trade/BussTrdAvailableCashQuery/getAvailableCash'), params, function (data) {
                getElementById("avalibleCash").val(data.T_0).trigger("change").trigger('blur');
                getElementById("avalibleCashT1").val(data.T_1).trigger("change").trigger('blur');
                // 通道交易 新增 强成西 2019/03/26 start
                getElementById("channelAvailableCash").val(data.T_2).trigger("change").trigger('blur');
                // 通道交易 新增 强成西 2019/03/26 end
                getElementById("assetPosition").val(data.position).trigger("change").trigger('blur');
            });
        }
    }
    var updateSecAcctBalance = function (func) {
        var params = func();
        params.acctType = ACCT_TYPE_A01;
        if(params.ccy && params.portfolioId && params.portfolioId.trim()) {
            Joyin.ajaxJson(JoyinUtil.getRealPath('/component/trade/BussTrdAvailableCashQuery/getSecManageAvailableCash'), params, function (balance) {
                getElementById("secAcctBalance").val(balance).trigger("change").trigger("blur");
            });
        }
    }
    var setPosition = function (params){
    	
    	 getElementById("avalibleCash").val(params.avalibleCash).trigger("change").trigger('blur');
         getElementById("avalibleCashT1").val(params.avalibleCashT1).trigger("change").trigger('blur');
         // 通道交易 新增 强成西 2019/03/26 start
         getElementById("channelAvailableCash").val(params.channelAvailableCash).trigger("change").trigger('blur');
         // 通道交易 新增 强成西 2019/03/26 end
         getElementById("assetPosition").val(params.assetPosition).trigger("change").trigger('blur');
    }
    // 组合现金、持仓查询业务共通公开接口
    if(window.BussTrdAvailableCashQuery == undefined){
    }
    window.BussTrdAvailableCashQuery = {};
    
    // add by ZhouYu 2019-12-12 费用支付管理增加T日可用费用关联
    var showFeePay = function(autoHide){
    	if(feePayShow && CUSTOMER_BANK_CODE == '004'){
    		getElementById('div_cash').show();
    		getElementById('div_cash_T1').hide();
    		getElementById('div_channelCash').hide();
    		getElementById('div_position').hide();
            getElementById('div_channelCash').hide();
    	}else{
    		getElementById('div_cash').hide();
    		getElementById('div_cash_T1').hide();
    		getElementById('div_channelCash').hide();
    		getElementById('div_position').hide();
            getElementById('div_channelCash').hide();
    	}
    }
    
    window.BussTrdAvailableCashQuery[entityName] = {
        init: init,
        updateSecAcctBalance : updateSecAcctBalance,
        updateCash : updateCash,
        showPosition : showPosition,
        hidePosition : hidePosition,
        showCash : showCash,
        //计划类利率型不需要通道剩余现金 新增 张庚林 2019/07/02 start
        showCash2 : showCash2,
        //计划类利率型不需要通道剩余现金 新增 张庚林 2019/07/02 end
        hideCash : hideCash,
        showFeePay : showFeePay,
        setPosition : setPosition
    };
    init();
}());