/**
 * 行情（左下）
 */
(function(){

    /**根据市场代码，查询行情信息，展示出来*/
    var queryStockInfo = function(finprodMarketId,hkseMarket) {
        var params = {
            finprodMarketId: finprodMarketId,
            hkseMarket: hkseMarket,
        }
        $.ajax({
        	url: "findQuotations",
            async: false,
            type: "POST",
            dataType:"json",
            data: params,
            success: function(info) {
                var result = info.data;
                if (result != null && result != '') {
                    var xtradeTickData = new Object();
                    xtradeTickData.stockCode = finprodMarketId;
                    //xtradeTickData.stockName = finprodName;
                    xtradeTickData.open = result.openPrice;
                    xtradeTickData.highest = result.highPrice;
                    xtradeTickData.lowest = result.lowPrice;
                    xtradeTickData.latest = result.lastPrice;
                    xtradeTickData.preclose = result.preClosePrice;
                    xtradeTickData.tradeVolume = result.volume || 0;
                    xtradeTickData.tradeAmount = result.tradeBalance || 0;
                    xtradeTickData.ceiling = result.upperLimitPrice;
                    xtradeTickData.floor = result.lowerLimitPrice;
                    xtradeTickData.change = xtradeTickData.latest - xtradeTickData.preclose;
                    xtradeTickData.changePercent = xtradeTickData.change / (xtradeTickData.preclose > 0 ? xtradeTickData.preclose : 1);

                    /**
                     * 卖出数量，顺序，1档 ~ 10档
                     */
                    xtradeTickData.sellVolumes = result.askVolume instanceof Array ? result.askVolume : [];

                    /**
                     * 卖出报价，顺序，1档 ~ 10档
                     */
                    xtradeTickData.sells = result.askPrice instanceof Array ? result.askPrice : [];

                    /**
                     * 买入数量，顺序，10档 ~ 1档
                     */
                    xtradeTickData.buyVolumes = result.bidVolume instanceof Array ? result.bidVolume: [];

                    /**
                     * 买入报价，顺序，10档 ~ 1档
                     */
                    xtradeTickData.buys = result.bidPrice instanceof Array ? result.bidPrice : [];
                    updateLevel2(xtradeTickData);
                }
                else {
                    updateLevel2();
                }
            }
        });

    }

    /**
     * 更新行情面板数据信息
     * @param {TickData} tick_data
     */
    var updateLevel2= function (tick_data) {
        var level2 = document.querySelector(".level2-panel");
        var $bulletin = level2.querySelector('.price-bulletin');
        var $boundary = level2.querySelector('.price-boundary');
        var $level_sell = level2.querySelector('.level-sell');
        var $level_buy = level2.querySelector('.level-buy');

        var $header = level2.querySelector('h3');

        var $lastPrice = $bulletin.querySelector('.latest-price > a');
        var $up = $bulletin.querySelector('.change-direction > .icon-up');
        var $down = $bulletin.querySelector('.change-direction > .icon-down');
        var $change = $bulletin.querySelector('.change-info > .change');
        var $percent = $bulletin.querySelector('.change-info > .percentage');

        var $open = $boundary.querySelector('.item-open > a');
        var $preclose = $boundary.querySelector('.item-preclose > a');
        var $ceiling = $boundary.querySelector('.item-ceiling > a');
        var $floor = $boundary.querySelector('.item-floor > a');

        var $sells = makeLevel2($level_sell);
        var $buys = makeLevel2($level_buy);

        /**
         * @param {Array<LevelItem>} $sells
         * @param {Array<LevelItem>} $buys
         * @param {TickData} tick_data
         */
        const updateLevels = function ($sells, $buys, tick_data) {
            var sellIndex = $sells.length - 1;

            var countNum = 0.01;

            /*if (tick_data.finprodType == "F01" || tick_data.finprodType == "ZYSHG") {
                countNum = 1;
            }*/

            $sells.forEach(($item, level_no) => {
                var $price = tick_data.sells[sellIndex] || 0;
                $item.$price.textContent = parent.EtrStockConnect002.formatPrice($price);
                $item.$volume.textContent = Math.floor((tick_data.sellVolumes[sellIndex--] || 0) * countNum);
            });

            $buys.forEach(($item, level_no) => {
                var $price = tick_data.buys[level_no] || 0;
                $item.$price.textContent = parent.EtrStockConnect002.formatPrice($price);
                $item.$volume.textContent = Math.floor((tick_data.buyVolumes[level_no] || 0) * countNum);
            });
        };

        /**
         * 清除已设置合约level2行情信息
         */

        if (!tick_data) {

            const zero = '0.00'
            $header.textContent='-----';
            $lastPrice.textContent = zero;
            $lastPrice.className = 'price-anchor';
            $up.style.display = 'none';
            $down.style.display = 'none';
            $change.textContent = zero;
            $change.className = 'change';
            $percent.textContent = '0.00%';
            $percent.className = 'percentage';
            $open.textContent = zero;
            $open.className = 'price-anchor';
            $preclose.textContent = zero;
            $ceiling.textContent = zero;
            $floor.textContent = zero;

            $sells.forEach(($item, level_no) => {

                $item.$price.textContent = zero;
                $item.$volume.textContent = 0;
            });

            $buys.forEach(($item, level_no) => {

                $item.$price.textContent = zero;
                $item.$volume.textContent = 0;
            });

            delete this._last_tick_data;
        }

        else if (this._last_tick_data === undefined || this._last_tick_data.stockCode !== tick_data.stockCode) {

            /**
             * 新合约
             */

            let change = tick_data.latest - tick_data.preclose;
            let is_up = change > 0;
            let is_down = change < 0;
            let color_class = is_up ? 's-color-red' : is_down ? 's-color-green' : '';
            let anchor_class = 'price-anchor ' + color_class;
            let direction_flag = is_up ? '+' : is_down ? '-' : '';

            var stockCodetmp = "";
            if(tick_data.stockCode) {
                var index = tick_data.stockCode.lastIndexOf(".");
                stockCodetmp = tick_data.stockCode.substring(0,index);
            }
            var stockNametmp = "";
            if(tick_data.stockName) {
                var index = tick_data.stockName.lastIndexOf("|");
                stockNametmp = tick_data.stockName.substring(index,tick_data.stockName.length);
            }
            $header.textContent = stockCodetmp + stockNametmp;
            $lastPrice.textContent = parent.EtrStockConnect002.formatPrice(tick_data.latest);
            $lastPrice.className = anchor_class;
            $up.style.display = is_up ? 'inline' : 'none';
            $down.style.display = is_down ? 'inline' : 'none';
            $change.textContent = direction_flag +  parent.EtrStockConnect002.formatPrice(change);
            $change.className = 'change ' + color_class;
            $percent.textContent = direction_flag + (100 * change / tick_data.preclose).toFixed(2) + '%';
            $percent.className = 'percentage ' + color_class;
            $open.textContent = parent.EtrStockConnect002.formatPrice(tick_data.open);
            $open.className = anchor_class;
            $preclose.textContent = parent.EtrStockConnect002.formatPrice(tick_data.preclose);;
            //999999999.9999表示无涨停限制，-999999999.9999表示无跌停限制
            $ceiling.textContent = tick_data.ceiling >= 999999999.9999 ? '-----' :  parent.EtrStockConnect002.formatPrice(tick_data.ceiling);
            $floor.textContent = tick_data.floor <= -999999999.9999 ? '-----' : parent.EtrStockConnect002.formatPrice(tick_data.floor);

            updateLevels($sells, $buys, tick_data);
            this._last_tick_data = tick_data;
        }
        else {

            /**
             * 合约未产生变化，行情发生变化
             */

            let change = tick_data.latest - tick_data.preclose;
            let hot_change = tick_data.latest - this._last_tick_data.latest;

            if (hot_change == 0) {
                return;
            }

            let is_up = change > 0;
            let is_down = change < 0;
            let is_hot_up = hot_change > 0;
            let is_hot_down = hot_change < 0;

            let color_class = is_up ? 's-color-red' : is_down ? 's-color-green' : '';
            let anchor_class = 'price-anchor ' + color_class;
            let direction_flag = is_up ? '+' : is_down ? '-' : '';

            $lastPrice.textContent = parent.EtrStockConnect002.formatPrice(tick_data.latest);
            $lastPrice.className = anchor_class;
            $up.style.display = is_hot_up ? 'inline' : 'none';
            $down.style.display = is_hot_down ? 'inline' : 'none';
            $change.textContent = direction_flag + parent.EtrStockConnect002.formatPrice(change);
            $change.className = 'change ' + color_class;
            $percent.textContent = direction_flag + (100 * change / tick_data.preclose).toFixed(2) + '%';
            $percent.className = 'percentage ' + color_class;

            /**
             * 如果合约未产生变化，仅为tick数据变更，则仅更新level2各档位价格和数量，不再检查各表单的价格值是否在有效涨跌停范围内
             */
            updateLevels($sells, $buys, tick_data);

            this._last_tick_data = tick_data;
        }
    }

    var makeLevel2 = function($level_root) {
        var levels = [];
        var $items = $level_root.querySelectorAll('.level-item');

        for (let idx = 0; idx < $items.length; idx++) {
            var object = new Object();
            object.$price = $items[idx].querySelector('.level-price');
            object.$volume = $items[idx].querySelector('.level-volume');
            levels.push(object);
        }
        return levels;
    }

    /**
     * 展示参考汇率
     */
    function setReferenceRate(result) {
        $("#referenceRate_in").text(result.referenceRateIn);
        $("#referenceRate_out").text(result.referenceRateOut);
    }

    function setCashOrNum() {
        $("#referenceRate_in").text('0.00');
        $("#referenceRate_out").text('0.00');
    }

    /**
     * 对外公开的接口
     */
    parent.EtrStockConnectHq = {
        queryStockInfo:queryStockInfo,
        setReferenceRate:setReferenceRate,
        setCashOrNum:setCashOrNum,
    }

    /**
     * 画面加载完成
     */
    $(function() {

        /**
         * 点击自动带入指令价格
         */
        $(".level-price").click(function(e){
            var quotaPrice = e.target.innerText;
            if(quotaPrice){
                parent.EtrStockConnect002.setQuotaPrice(quotaPrice);
            }
        });

    });

}());