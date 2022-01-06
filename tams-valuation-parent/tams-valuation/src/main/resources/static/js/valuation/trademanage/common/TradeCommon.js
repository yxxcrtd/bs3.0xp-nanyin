var TradeCommon = function () {
    var ps;
    var setPsForInnerTrade = function (psId, 内部交易对手) {
        // 内部交易对手时，只能选卖出
        if (内部交易对手) {
            $("#"+psId).val('T02').prop('disabled', true).change();
        }
        else {
            $("#"+psId).prop('disabled', false).change();
        }
    }

    var getSecPositionForSaleAll = function (param, callback) {
        if (!param.portfolioId || !param.finprodId || !param.invAim || !(param.secAcctId || param.chlId)) {
            return;
        }
        Joyin.ajaxJson(basePath + 'component/trade/BussTrdAvailableCashQuery/getShowHandNum', param,
            function (result) {
                if (typeof callback == 'function') {
                    callback.call(this, result)
                }
            });
    }

    return {setPsForInnerTrade: setPsForInnerTrade, getSecPositionForSaleAll: getSecPositionForSaleAll}
}();