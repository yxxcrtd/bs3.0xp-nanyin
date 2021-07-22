(function(){

    /**根据市场代码，查询行情信息，展示出来*/
    var queryAmountInfo = function(secId,secAbbr,finprodId,portfolioId,chlFinprodId,ccy,sysDateT1,calId) {
        var param = {
            finprodId : finprodId,
            portfolioId : portfolioId,
            chlFinprodId : chlFinprodId,
            ccy:ccy,
            sysDateT1:sysDateT1,
            calId:calId
        };
        if (portfolioId != null && portfolioId != '') {

            document.getElementById('positionAmount').innerHTML = '0.00万元';
            document.getElementById('canSellAmount').innerHTML = '0.00万元';
            document.getElementById('canBuyAmount').innerHTML = '0.00万元';
            document.getElementById('costPrice').innerHTML = '0.00元';
            Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/findSecAmountInfo', param, function (result) {
                if (result) {
                    document.getElementById('positionAmount').innerHTML = JoyinUtil.formatNumber(result.positionAmount, 2, 10000, 2)+'万元';
                    document.getElementById('canSellAmount').innerHTML = JoyinUtil.formatNumber(result.canSellAmount, 2, 10000, 2)+'万元';
                    // document.getElementById('canBuyAmount').innerHTML = JoyinUtil.formatNumber(result.canBuyAmount, 2, 10000, 2)+'万元';
                    document.getElementById('costPrice').innerHTML = JoyinUtil.formatNumber(result.costPrice, 2, null, 2) ;
                }
            }, false);
        }

        $("#secId").html('');
        $("#secId").html(secId);
        $("#secAbbr").html('');
        $("#secAbbr").html(secAbbr);

    }

    var calCanBuyAmount = function(portfolioId,ccy,sysDate,price) {
        var param = {
            portfolioId : portfolioId,
            ccy : ccy,
            sysDate : sysDate,
            price:price
        };

        document.getElementById('canBuyAmount').innerHTML = '0.00万元';
        Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/calCanBuyAmount', param, function (result) {
            if (result) {
                document.getElementById('canBuyAmount').innerHTML = JoyinUtil.formatNumber(result.canBuyAmount, 2, 10000, 2)+'万元';
             }
        }, false);
    }

    /**
     * 对外公开的接口
     */
    parent.EtrInitAmount = {
        queryAmountInfo:queryAmountInfo,
        calCanBuyAmount:calCanBuyAmount
    }


    $(function() {
        dynview = new DynamicView("EtrInitAmount", "EtrInitAmount_dyn");
        dynview.init();
    });

}());