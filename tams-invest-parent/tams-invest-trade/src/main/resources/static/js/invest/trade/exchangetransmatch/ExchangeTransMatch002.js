(function () {
    /**
     * 定义数据表格
     */
    var dealDataTable=$("#dealDataTable").joyintable({
        data:dealList == null ? null:dealList,
        checkbox : false,
        height : 200,
        page:false,
        cols:[
            {field:'applyNo', sortField:'applyNo',width:200, title: 'ExchangeTransMatch002.applyNo'},
            {field:'secAcctNo', sortField:'secAcctNo',width:200, title: 'ExchangeTransMatch002.secAcctNo'},
            {field:'finprodMarketId', sortField:'finprodMarketId',width:200, title: 'ExchangeTransMatch002.finprodId'},
            {field:'matchTypeStr', sortField:'matchType',width:200, title: 'ExchangeTransMatch002.trdMatchType'},
            {field:'tradeDate', sortField:'tradeDate',width:200, title: 'ExchangeTransMatch002.tradeDate',type: 'date'},
            {field:'tradeMarketStr', sortField:'tradeMarket',width:200, title: 'ExchangeTransMatch002.tradeMarket'},
            {field:'tradePlatStr', sortField:'tradePlat',width:200, title: 'ExchangeTransMatch002.tradePlat'},
            {field:'tradeNum', sortField:'tradeNum',width:200, title: 'ExchangeTransMatch002.tradeNum',type: 'number'},
            {field:'tradeAmout', sortField:'tradeAmout',width:200, title: 'ExchangeTransMatch002.tradeAmout',type: 'number'},
            {field:'tradePrice', sortField:'tradePrice',width:200, title: 'ExchangeTransMatch002.tradePrice',type: 'number'}
        ]

    });

    /**
     * 定义数据表格
     */
    var entrustDataTable=$("#entrustDataTable").joyintable({
        data:entrustList == null ? null:entrustList,
        checkbox : false,
        height : 200,
        page:false,
        cols:[
            {field:'busiCode', sortField:'busiCode',width:200, title: 'ExchangeTransMatch002.busiCode'},
            {field:'portfolioId', sortField:'portfolioId',width:200, title: 'ExchangeTransMatch002.portfolioId'},
            {field:'portfolioName', sortField:'portfolioName',width:200, title: 'ExchangeTransMatch002.portfolioName'},
            {field:'finprodType2Str', sortField:'finprodType2',width:200, title: 'ExchangeTransMatch002.finprodType2'},
            {field:'finprodMarketId', sortField:'finprodMarketId',width:200, title: 'ExchangeTransMatch002.finprodMarketId'},
            {field:'psStr', sortField:'ps',width:200, title: 'ExchangeTransMatch002.etrps'},
            {field:'tradePrice', sortField:'tradePrice',width:200, title: 'ExchangeTransMatch002.entrustPrice',type: 'number'},
            {field:'tradeNum', sortField:'tradeNum',width:200, title: 'ExchangeTransMatch002.dealNum',type: 'number'},
            {field:'tradeAmout', sortField:'tradeAmout',width:200, title: 'ExchangeTransMatch002.tradeAmout',type: 'number'},
            {field:'tradeDate', sortField:'tradeDate',width:200, title: 'ExchangeTransMatch002.tradeDate',type: 'date'}
        ]

    });

    /**
     * 画面加载完成
     */
    $(function() {

    });
}());