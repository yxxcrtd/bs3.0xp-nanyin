/**
 * 持仓信息（右上）
 */
(function(){
    var positionCheckData ;
    var onWayRepCheckData;
    
    function getPositionsSearchParams(){
        var data = $.serializeToObject('#searchForm_cc');
        data.portfolioId = data.portfolioId ? data.portfolioId.join(','):'';
        return data;
    }
    
    /**
     * 定义数据表格
     */
    var positionsTable = $("#positionsTable").joyintable({ 
        ajax: {
            url: 'findPositionsPage',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getPositionsSearchParams();//获取请求参数
            }//ajax请求数据
        },
        dblclick:function(table, data) {
            window.parent.doResetRiskMathFlag();
            parent.EtrAgreRepTrade001.setpositionsItems(data);
            
        },
        click : function(table,data){
            positionsTable.checkRow(data.LAY_TABLE_INDEX);
            positionCheckData = data;
        },
        done: function(allData){
            if(positionCheckData){
                var all = positionsTable.getAllRows();
                for(var i=0;i<all.length;i++){
                    var val = all[i];
                    if(val.portfolioId == positionCheckData.portfolioId
                        && val.finprodId == positionCheckData.finprodId){
                        positionsTable.checkRow(val.LAY_TABLE_INDEX);
                        var el = $("div[lay-id='positionsTable'] tr[data-index='"+val.LAY_TABLE_INDEX+"']");
                        el.addClass(positionsTable.options.rowSelectedClass);
                        var tableHeight = $("div[lay-id='positionsTable'] div[class='layui-table-body layui-table-main']")[0].offsetHeight;
                        var rowHeight = el[0].offsetHeight;
                        var scroll = rowHeight*(val.LAY_TABLE_INDEX-3);
                        $("div[lay-id='positionsTable'] div[class='layui-table-body layui-table-main']").scrollTop(scroll);
                    }
                }
            }
        },
        /**
         * 定义表格名称
         */
        checkbox:false,
        searchOnInit:true,
        cols:[
             {field:'portfolioId', title: 'EtrExchangeTrade002.portfolioId', sortField:'portfolioId',width : 100}//组合名称
            ,{field:'portfolioName', title: 'EtrExchangeTrade002.portfolioName', sortField:'portfolioName',width : 100}//组合名称
            ,{field:'secAcctId', title: 'EtrExchangeTrade002.secAcctId', sortField:'secAcctId',width : 130}//证券账户代码
            ,{field:'secAcctName', title: 'EtrExchangeTrade002.secAcctName',sortField:'secAcctName', width : 130}//证券账户名称
            ,{field:'tradeMarketStr', title: 'EtrExchangeTrade002.tradeMarket',sortField:'tradeMarket', width : 100}//交易市场
            ,{field:'tradeMarket', title: 'EtrExchangeTrade002.tradeMarket',sortField:'tradeMarket', visible:false ,width : 100}//交易市场
            ,{field:'finprodId', title: 'EtrExchangeTrade002.finprodId', width : 180,visible:false}//资产代码
            ,{field:'finpordName', title: 'EtrExchangeTrade002.finpordName', width : 160,visible:false}//资产名称
            ,{field:'finprodMarketId', title: 'EtrExchangeTrade002.secId', width : 180}//资产代码
            ,{field:'secName', title: 'EtrExchangeTrade002.secName', width : 160}//资产名称
            // ,{field:'invAimStr', title: 'EtrExchangeTrade002.invAim',sortField:'invAim', width : 150}//投资目的
            ,{field:'positionsNum', title: 'EtrExchangeTrade002.positionsNum',type:'number',digit: 0, width : 150}//持仓数量
            ,{field:'sellNum', title: 'EtrExchangeTrade002.sellNum',type:'number',digit: 0, width : 150}//可卖数量
            ,{field:'positionsAmt', title: 'EtrExchangeTrade002.positionsAmt',type:'number', width : 150}//持仓成本
            ,{field:'price', title: 'EtrExchangeTrade002.price',type:'number', width : 100}//最新价
            ,{field:'floatingPL', title: 'EtrExchangeTrade002.floatingPL',type:'number', sortField:'floatingPL', width : 150}//浮动盈亏（%）
        ]
    });


    /**
	 * 刷新列表
	 */
    var refreshPositionsTable = function(){
        positionsTable.table ? positionsTable.reload() : positionsTable.load();
    }
  /*  *//**
     * 更新上交所和深交所汇总标准券数量
     *//*
    var loadSecNum = function() {
        var param = $.serializeToObject('#searchForm_zqzy');
        Joyin.ajax(basePath + 'invest/entrust/etrexchangetrade/EtrExchangeTrade002/loadSecNum', param, function(result) {
            if(result) {
                $("#secNum_sh").text(result.secNum_sh);
                $("#secNum_sh").css('color', result.secNum_color_sh);
                $("#secNum_sz").text(result.secNum_sz);
                $("#secNum_sz").css('color', result.secNum_color_sz);
            }
        });
    }*/
    var init = function() {
        //loadSecNum();
    }
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定查询按钮
        $('#EtrAgreRepTrade002_search_btn').click(function() {
            positionsTable.load();//加载表格
        });
        init();
        /**
         * 定时刷新
         */
        /*setInterval(function(){
            refreshPositionsTable();
            //loadSecNum();
        },300000);
        setInterval(function () {
            refreshOnWayRepTable();
        },30000);
        setInterval(function () {
            refreshUnOpenQuotaTable();
        },30000);*/
        setTimeout(function(){
            refreshPositionsTable();
            setTimeout(arguments.callee,POSTABLE_REFRESH_PERIOD)
        },POSTABLE_REFRESH_PERIOD);
        setTimeout(function(){
            refreshOnWayRepTable();
            setTimeout(arguments.callee,ONWAYREP_REFRESH_PERIOD)
        },ONWAYREP_REFRESH_PERIOD);
        setTimeout(function(){
            refreshUnOpenQuotaTable();
            setTimeout(arguments.callee,UNOPENQUOTA_REFRESH_PERIOD)
        },UNOPENQUOTA_REFRESH_PERIOD);
    });

    /**==================在途回购================== start */

    //TODO 需要的话，填充条件
    function getOnWayRepSearchParams(){
        var data = $.serializeToObject('#searchForm_cc');
        data.portfolioId = data.portfolioId ? data.portfolioId.join(','):'';
        return data;
    }
    /**
     * 定义数据表格
     */
    var onWayRepTable = $("#onWayRepTable").joyintable({
        ajax: {
            url: 'findOnWayRepPage',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getOnWayRepSearchParams();//获取请求参数
            }//ajax请求数据
        },
        click : function(table,data){
            onWayRepTable.checkRow(data.LAY_TABLE_INDEX);
            onWayRepCheckData = data;
        },
        done: function(allData){
            if(onWayRepCheckData){
                var all = onWayRepTable.getAllRows();
                for(var i=0;i<all.length;i++){
                    var val = all[i];
                    if(val.tradeId? val.tradeId == onWayRepCheckData.tradeId : val.intiTradeId == onWayRepCheckData.intiTradeId){
                        onWayRepTable.checkRow(val.LAY_TABLE_INDEX);
                        var el = $("div[lay-id='onWayRepTable'] tr[data-index='"+val.LAY_TABLE_INDEX+"']");
                        el.addClass(onWayRepTable.options.rowSelectedClass);
                        var tableHeight = $("div[lay-id='onWayRepTable'] div[class='layui-table-body layui-table-main']")[0].offsetHeight;
                        var rowHeight = el[0].offsetHeight;
                        var scroll = rowHeight*(val.LAY_TABLE_INDEX-3);
                        $("div[lay-id='onWayRepTable'] div[class='layui-table-body layui-table-main']").scrollTop(scroll);
                    }
                }
            }
        },
        dblclick:function(table, data) {
            if(data.ps == BACK_LEND_DIRECTION_04 && data.market == TRADE_MARKET_03){
                //Joyin.error(data.entrustStatusString+"的指令不允许修改!");
                Joyin.error("上海证券交易所逆回购不能主动发起请求！");
                return false;
            }
            window.parent.doResetRiskMathFlag();
            parent.EtrAgreRepTrade001.setNnWayRepItems(data);
        },
        /**
         * 定义表格名称
         */
        checkbox:false,
        searchOnInit:true,
        cols:[
            {field:'portfolioId', title: 'EtrExchangeTrade002.portfolioId', sortField:'portfolioId',width : 100}
            ,{field:'portfolioName', title: 'EtrExchangeTrade002.portfolioName', sortField:'portfolioName',width : 100}
            , {field: 'ps', title: '委托方向', sortField: 'ps', width: 100, visible: false}
            , {field: 'psStr', title: '委托方向', sortField: 'ps', width: 100}
            ,{field:'purStatus', title: '购回状态', sortField:'purStatus',width : 100}
            ,{field:'settleStatus', title: '购回交收状态', sortField:'settleStatus',width : 150}
            ,{field:'repDays', title: '回购天数', type:'number',digit: 0,sortField:'repDays',width : 100}
            ,{field:'backDate', title: '回购到期日', type:'date', sortField:'backDate',width : 100}
            ,{field:'market', title: '市场', sortField:'market',width : 100,visible: false}
            ,{field:'marketStr', title: '市场', sortField:'market',width : 100}
            ,{field:'stockName', title: '质押券名称', sortField:'stockName',width : 120}
            ,{field:'stockCode', title: '质押券代码', sortField:'stockCode',width : 120}
            ,{field:'discountRate', title: '折算率（%）', type:'number',sortField:'discountRate',width : 100}
            ,{field:'repSecNum', title: '质押券数量', type:'number',digit: 0 ,sortField:'repSecNum',width : 100}
            ,{field:'pledStrdPrinAmt', title: '质押券面总额', type:'number',sortField:'pledStrdPrinAmt',width : 100}
            ,{field:'tradeAmt', title: '回购金额', type:'number',sortField:'tradeAmt',width : 100}
            ,{field:'yield', title: '回购利率（%）',type:'number', sortField:'yield',digit: 4,width : 100}
            ,{field:'fristSettleDate', title: '首期结算日',type:'date', sortField:'fristSettleDate',width : 100}
            ,{field:'expireSettleDate', title: '到期结算日', type:'date',sortField:'expireSettleDate',width : 100}
            ,{field:'occupyDays', title: '实际占款天数', type:'number',sortField:'occupyDays',digit: 0,width : 80}
            ,{field:'mIntAmt', title: '回购利息',type:'number', sortField:'mIntAmt',width : 100}
            ,{field:'expireSettleAmt', title: '到期结算金额', type:'number',sortField:'expireSettleAmt',width : 100}
            ,{field:'tradeId', title: '成交编号', sortField:'tradeId',width : 100}
            ,{field:'intiTradeId', title: '初始交易编号', sortField:'intiTradeId',width : 100}
        ]
    });


    /**
     * 刷新列表
     */
    var refreshOnWayRepTable = function(){
        onWayRepTable.table ? onWayRepTable.reload() : onWayRepTable.load();
    }

    /**==================在途回购================== end */

    /**==================非公开行情================== start */
    //TODO 需要的话，填充条件
    function getUnOpenQuotaSearchParams(){
        var data = $.serializeToObject('#searchForm_cc');
        data.portfolioId = data.portfolioId ? data.portfolioId.join(','):'';
        return data;
    }
    /**
     * 定义数据表格
     */
    var unOpenQuotaTable = $("#unOpenQuotaTable").joyintable({
        ajax: {
            url: 'findUnOpenQuotaPage',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getUnOpenQuotaSearchParams();//获取请求参数
            }//ajax请求数据
        },
        dblclick:function(table, data) {
            window.parent.doResetRiskMathFlag();
           parent.EtrAgreRepTrade001.setunOpenQuotaItems(data);
        },
        /**
         * 定义表格名称
         */
        checkbox:false,
        searchOnInit:true,
        cols:[
            {field:'reqOnId', title: '请求编号', sortField:'reqOnId',width : 100, sortable:false}
            ,{field:'reqType', title: '请求类型', sortField:'reqType',width : 100, visible: false, sortable:false}
            ,{field:'reqTypeStr', title: '请求类型', sortField:'reqTypeStr',width : 100, visible: true, sortable:false}
            ,{field:'redoTypeStr', title: '续做类型',width : 100, visible: true, sortable:false}
            ,{field:'redoType', title: '续做类型', width : 100, visible: false}
            ,{field:'repSecId', title: '质押券代码', sortField:'repSecId',width : 100, sortable:false}
            ,{field:'pledgeStampName', title: '质押券名称', sortField:'pledgeStampName',width : 100, sortable:false}
            //,{field:'oriRepSecId', title: '原质押代码', sortField:'oriRepSecId',width : 100}  重复
            ,{field:'repSecNum', title: '质押数量', type:'number',sortField:'repSecNum',width : 100, digit: 0,sortable:false}
            ,{field:'pledgeStampBalance', title: '质押券面总额合计',type:'number', sortField:'pledgeStampBalance',width : 100, sortable:false}
            ,{field:'conRatio', title: '折算比例(%)',type:'number', sortField:'conRatio',width : 100, sortable:false}
            ,{field:'tradeAmt', title: '回购金额', type:'number',sortField:'tradeAmt',width : 100, sortable:false}
            ,{field:'yield', title: '回购利率(%)', type:'number',sortField:'yield',width : 100, digit: 4,sortable:false}
            ,{field:'term', title: '回购期限', type:'number',sortField:'term',width : 100, digit: 0,sortable:false}
            ,{field:'occupyDays', title: '实际占款天数', type:'number',sortField:'occupyDays',width : 100, digit: 0,sortable:false}
            ,{field:'fristSettleDate', title: '首期结算日',type:'date', sortField:'fristSettleDate',width : 100, sortable:false}
            ,{field:'backDate', title: '回购到期日',type:'date', sortField:'backDate',width : 100, sortable:false}
            ,{field:'expireSettleDate', title: '到期结算日', type:'date',sortField:'expireSettleDate',width : 100, sortable:false}
            ,{field:'origPledgeStampCode', title: '原质押券代码',sortField:'origPledgeStampCode',width : 100, sortable:false}
            ,{field:'intAmt', title: '回购利息', sortField:'intAmt',type:'number',width : 100, sortable:false}
            ,{field:'expireSettleAmt', title: '到期结算金额',type:'number', sortField:'expireSettleAmt',width : 100, sortable:false}
            ,{field:'tradeId', title: '成交编号', sortField:'tradeId',width : 100, sortable:false}
            ,{field:'sponsorTraderCode', title: '发起方交易员代码', sortField:'sponsorTraderCode',width : 100, sortable:false}
            ,{field:'sponsorDealerCode', title: '发起方交易商代码', sortField:'sponsorDealerCode',width : 100, sortable:false}
            ,{field:'traderCode', title: '本方交易员',sortField:'traderCode',width : 100, sortable:false}
            ,{field:'addCla', title: '补充协议', sortField:'addCla',width : 100, sortable:false}
        ]
    });


    /**
     * 刷新列表
     */
    var refreshUnOpenQuotaTable = function(){
        unOpenQuotaTable.table ? unOpenQuotaTable.reload() : unOpenQuotaTable.load();
    }
    /**==================非公开行情================== end */
}());