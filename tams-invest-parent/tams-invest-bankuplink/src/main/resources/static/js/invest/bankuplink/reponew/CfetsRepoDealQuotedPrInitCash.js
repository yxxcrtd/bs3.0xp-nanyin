(function(){

    /**组合代码查询得到组合现金信息*/
    var queryCash = function(ccy,vdate,portfolioId) {
        var param = {
            ccy : ccy,
            vdate : vdate,
            portfolioId:portfolioId
        };
        if (portfolioId != null && portfolioId != '') {
            document.getElementById('tgAcctCash').innerHTML = '0.00万元';
            document.getElementById('secAcctCash').innerHTML = '0.00万元';
            document.getElementById('t1Cash').innerHTML = '0.00万元';
            Joyin.ajaxJson(basePath + 'invest/bankuplink/reponew/CfetsRepoDealQuotedPr001/findCashAmountInfo', param, function (result) {
                if (result) {
                    document.getElementById('tgAcctCash').innerHTML = JoyinUtil.formatNumber(result.tgAcctCash, 2, 10000, 2) + '万元';
                    document.getElementById('secAcctCash').innerHTML = JoyinUtil.formatNumber(result.secAcctCash, 2, 10000, 2) + '万元';
                    document.getElementById('t1Cash').innerHTML = JoyinUtil.formatNumber(result.t1Cash, 2, 10000, 2) + '万元';
                }
            }, false);
        }

    }


    /**
     * 对外公开的接口
     */
    parent.CfetsRepoDealQuotedPrInitCash = {
        queryCash:queryCash
    };


    $(function() {
        dynview = new DynamicView("CfetsRepoDealQuotedPrInitCash", "CfetsRepoDealQuotedPrInitCash_dyn");
        dynview.init();
    });

}());