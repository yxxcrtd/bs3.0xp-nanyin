(function () {
    /**
     * 定义数据表格
     */
    var dealDataTable=$("#dealDataTable").joyintable({
        data:dealList == null ? null:dealList,
        checkbox : false,
        searchOnInit:true,
        height : 150,
        page:false,
        cols:[
            {field:'applyTradeId', sortField:'applyTradeId',width:200, title: 'ExchangeTransMatch002.applyNo'},
            {field:'secAcctName', sortField:'secAcctId',width:200, title: 'ExchangeTransMatch002.secAcctNo'},
            {field:'finprodMarketId', sortField:'finprodMarketId',width:200, title: 'ExchangeTransMatch002.finprodId'},
            {field:'matchTypeStr', sortField:'matchType',width:200, title: 'ExchangeTransMatch002.trdMatchType'},
            {field:'tradeDateStr', sortField:'tradeDate',width:200, title: 'ExchangeTransMatch002.tradeDate'},
            {field:'tradeMarketStr', sortField:'tradeMarket',width:200, title: 'ExchangeTransMatch002.tradeMarket'},
            {field:'tradePlatStr', sortField:'tradePlat',width:200, title: 'ExchangeTransMatch002.tradePlat'},
            {field:'shareAmt', sortField:'shareAmt',width:200, title: 'ExchangeTransMatch002.dealNum',type: 'number'},
            {field:'tradeAmt', sortField:'tradeAmt',width:200, title: 'ExchangeTransMatch002.dealAmout',type: 'number'},
            {field:'unitFprice', sortField:'unitFprice',width:200, title: 'ExchangeTransMatch002.dealPrice',type: 'number'}
        ]

    });

    /**
     * 定义数据表格
     */
    var entrustDataTable=$("#entrustDataTable").joyintable({
        data:entrustList == null ? null:entrustList,
        checkbox : false,
        searchOnInit:true,
        height : 150,
        page:false,
        cols:[
            {field:'entrustId', sortField:'entrustId',width:200, title: 'ExchangeTransMatch002.busiCode'},
            {field:'portfolioId', sortField:'portfolioId',width:200, title: 'ExchangeTransMatch002.portfolioId'},
            {field:'portfolioName', sortField:'portfolioName',width:200, title: 'ExchangeTransMatch002.portfolioName'},
            {field:'finprodType2Str', sortField:'finprodType2',width:200, title: 'ExchangeTransMatch002.finprodType2'},
            {field:'finprodMarketId', sortField:'finprodMarketId',width:200, title: 'ExchangeTransMatch002.finprodMarketId'},
            {field:'psStr', sortField:'ps',width:200, title: 'ExchangeTransMatch002.etrps'},
            {field:'price', sortField:'price',width:200, title: 'ExchangeTransMatch002.entrustPrice',type: 'number'},
            {field:'entrustNum', sortField:'entrustNum',width:200, title: 'ExchangeTransMatch002.dealNum',type: 'number'},
            {field:'entrustAmt', sortField:'entrustAmt',width:200, title: 'ExchangeTransMatch002.entrustAmount',type: 'number'},
            {field:'tradeDateStr', sortField:'entrustDate',width:200, title: 'ExchangeTransMatch002.tradeDate'}
        ]

    });

    /**
     * 画面加载完成
     */
    $(function() {

    });
}());