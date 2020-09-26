//点击查询按钮
$('#ValExchangeRateInfo001_BTN_search').click(function() {
    $(function() {
        /**
         * 定义数据表格
         */
        var table=$("#dataTable").joyintable({
            data:[
    {"CurrencyPair":"CNY/USD","type":"远期","date":"2018-5-3","preprice":"88","startprice":"77","maxprice":"88","minprice":"77","endprice":"85" },   
    {"CurrencyPair":"CNY/USD","type":"远期","date":"2018-5-3","preprice":"77","startprice":"77","maxprice":"88","minprice":"77","endprice":"85" }, 
    {"CurrencyPair":"CNY/USD","type":"远期","date":"2018-5-3","preprice":"77","startprice":"77","maxprice":"88","minprice":"77","endprice":"85" }, 
    {"CurrencyPair":"CNY/USD","type":"远期","date":"2018-5-3","preprice":"77","startprice":"77","maxprice":"88","minprice":"77","endprice":"85" }, 
    {"CurrencyPair":"CNY/USD","type":"远期","date":"2018-5-3","preprice":"77","startprice":"77","maxprice":"88","minprice":"77","endprice":"85" }, 
    {"CurrencyPair":"CNY/USD","type":"远期","date":"2018-5-3","preprice":"77","startprice":"77","maxprice":"88","minprice":"77","endprice":"85" }, 
    {"CurrencyPair":"CNY/USD","type":"远期","date":"2018-5-3","preprice":"77","startprice":"77","maxprice":"88","minprice":"77","endprice":"85" }, 
    {"CurrencyPair":"CNY/USD","type":"远期","date":"2018-5-3","preprice":"77","startprice":"77","maxprice":"88","minprice":"77","endprice":"85" }, 
    {"CurrencyPair":"CNY/USD","type":"远期","date":"2018-5-3","preprice":"77","startprice":"77","maxprice":"88","minprice":"77","endprice":"85" }, 
    {"CurrencyPair":"CNY/USD","type":"远期","date":"2018-5-3","preprice":"77","startprice":"77","maxprice":"88","minprice":"77","endprice":"85" }, 
    {"CurrencyPair":"CNY/USD","type":"远期","date":"2018-5-3","preprice":"88","startprice":"77","maxprice":"88","minprice":"77","endprice":"85" }
            ],
            searchOnInit: true,
            cols: [
                {field:'CurrencyPair', title: '货币对', sort: true},  
                {field:'type', title: '外汇类型', sort: true},
                {field:'date', title: '交易日期', sort: true},
                {field:'startprice', title: '开盘价（元）', sort: true},
                {field:'endprice', title: '收盘价（元）', sort: true},
                {field:'maxprice', title: '最高价（元）', sort: true},
                {field:'minprice', title: '最低价（元）', sort: true}
            ]
        }); })
})

//点击导出按钮
$('#ValExchangeRateInfo001_BTN_export').click(function() {
    
    
    
})