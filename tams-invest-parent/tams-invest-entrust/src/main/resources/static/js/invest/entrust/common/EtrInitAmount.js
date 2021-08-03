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

        document.getElementById('positionAmount').innerHTML = '0.00万元';
        document.getElementById('canSellAmount').innerHTML = '0.00万元';
        document.getElementById('canBuyAmount').innerHTML = '0.00万元';
        document.getElementById('costPrice').innerHTML = '0.00元';
        document.getElementById('cashAmount').innerHTML = '0.00万元';
        if (portfolioId != null && portfolioId != '') {
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

    var calCanBuyAmount = function(portfolioIds,ccy,sysDate,price) {
        document.getElementById('canBuyAmount').innerHTML = '0.00万元';
        var AmountData = {
            positionAmount:Number(0),
            canSellAmount:Number(0),
            canBuyAmount:Number(0)
        };
        if (portfolioIds != null && portfolioIds != '') {
            for (var i in portfolioIds) {
                var param = {
                    portfolioId : portfolioIds[i],
                    ccy : ccy,
                    sysDate : sysDate,
                    price:price
                };
                Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/calCanBuyAmount', param, function (result) {
                    if (result) {
                        if(result.canBuyAmount) AmountData.canBuyAmount+= Number(result.canBuyAmount);
                    }
                }, false);
            }
            document.getElementById('canBuyAmount').innerHTML = JoyinUtil.formatNumber(AmountData.canBuyAmount, 2, 10000, 2)+'万元';
        }
    }

    /**根据市场代码，查询行情信息，展示出来*/
    var queryAmountInfoMuti = function(secId,secAbbr,finprodId,portfolioIds,chlFinprodId,ccy,sysDateT1,calId) {
        document.getElementById('positionAmount').innerHTML = '0.00万元';
        document.getElementById('canSellAmount').innerHTML = '0.00万元';
        document.getElementById('canBuyAmount').innerHTML = '0.00万元';
        document.getElementById('costPrice').innerHTML = '0.00元';
        document.getElementById('cashAmount').innerHTML = '0.00万元';

        var AmountData = {
            positionAmount:Number(0),
            canSellAmount:Number(0),
            canBuyAmount:Number(0),
            cashAmount:Number(0)
        };


        if (portfolioIds != null && portfolioIds != '') {
            for(var i in portfolioIds){
                var param = {
                    finprodId : finprodId,
                    portfolioId : portfolioIds[i],
                    chlFinprodId : chlFinprodId,
                    ccy:ccy,
                    sysDateT1:sysDateT1,
                    calId:calId
                };
                Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/findSecAmountInfo', param, function (result) {
                    if (result) {
                        // document.getElementById('positionAmount').innerHTML = JoyinUtil.formatNumber(result.positionAmount, 2, 10000, 2)+'万元';
                        // document.getElementById('canSellAmount').innerHTML = JoyinUtil.formatNumber(result.canSellAmount, 2, 10000, 2)+'万元';
                        // document.getElementById('canBuyAmount').innerHTML = JoyinUtil.formatNumber(result.canBuyAmount, 2, 10000, 2)+'万元';
                        // document.getElementById('costPrice').innerHTML = JoyinUtil.formatNumber(result.costPrice, 2, null, 2) ;
                        if(result.positionAmount) AmountData.positionAmount += Number(result.positionAmount);
                        if(result.canSellAmount) AmountData.canSellAmount += Number(result.canSellAmount);
                        // if(result.canBuyAmount) AmountData.canBuyAmount += Number(result.canBuyAmount);
                        if(result.cashAmount) AmountData.cashAmount+= Number(result.cashAmount);
                    }
                }, false);
            }

            document.getElementById('positionAmount').innerHTML = JoyinUtil.formatNumber(AmountData.positionAmount, 2, 10000, 2)+'万元';
            document.getElementById('canSellAmount').innerHTML = JoyinUtil.formatNumber(AmountData.canSellAmount, 2, 10000, 2)+'万元';
            // document.getElementById('canBuyAmount').innerHTML = JoyinUtil.formatNumber(AmountData.canBuyAmount, 2, 10000, 2)+'万元';
            document.getElementById('cashAmount').innerHTML = JoyinUtil.formatNumber(AmountData.cashAmount, 2, 10000, 2)+'万元';

        }
        $("#secId").html('');
        $("#secId").html(secId);
        $("#secAbbr").html('');
        $("#secAbbr").html(secAbbr);
    }

    /**根据市场代码，查询行情信息，展示出来*/
    var changeElement = function(changeFlg) {
        if (changeFlg == "01") {
            $("#costPrice_div").show();
            $("#cashAmount_div").hide();
        } else if (changeFlg == "02") {
            $("#costPrice_div").hide();
            $("#cashAmount_div").show();
        }
    }


    /**
     * 对外公开的接口
     */
    parent.EtrInitAmount = {
        queryAmountInfo:queryAmountInfo,
        calCanBuyAmount:calCanBuyAmount,
        queryAmountInfoMuti:queryAmountInfoMuti,
        changeElement:changeElement
    }


    $(function() {
        dynview = new DynamicView("EtrInitAmount", "EtrInitAmount_dyn");
        dynview.init();
        $("#cashAmount_div").hide();
    });

}());