//点击查询按钮
$('#ValSharesMarketInfo001_BTN_search').click(function() {
    $(function() {
        /**
         * 定义数据表格
         */
        var table=$("#dataTable").joyintable({
            data:[
    {"code":"834392","name":"joyin","day":"2018-5-3","preprice":"88","startprice":"77","maxprice":"88","minprice":"77","endprice":"85","state":"正常"},   
    {"code":"834392","name":"joyin","day":"2018-5-3","preprice":"77","startprice":"77","maxprice":"88","minprice":"77","endprice":"85","state":"正常"}, 
    {"code":"834392","name":"joyin","day":"2018-5-3","preprice":"77","startprice":"77","maxprice":"88","minprice":"77","endprice":"85","state":"正常"}, 
    {"code":"834392","name":"joyin","day":"2018-5-3","preprice":"77","startprice":"77","maxprice":"88","minprice":"77","endprice":"85","state":"正常"}, 
    {"code":"834392","name":"joyin","day":"2018-5-3","preprice":"77","startprice":"77","maxprice":"88","minprice":"77","endprice":"85","state":"正常"}, 
    {"code":"834392","name":"joyin","day":"2018-5-3","preprice":"77","startprice":"77","maxprice":"88","minprice":"77","endprice":"85","state":"正常"}, 
    {"code":"834392","name":"joyin","day":"2018-5-3","preprice":"77","startprice":"77","maxprice":"88","minprice":"77","endprice":"85","state":"正常"}, 
    {"code":"834392","name":"joyin","day":"2018-5-3","preprice":"77","startprice":"77","maxprice":"88","minprice":"77","endprice":"85","state":"正常"}, 
    {"code":"834392","name":"joyin","day":"2018-5-3","preprice":"77","startprice":"77","maxprice":"88","minprice":"77","endprice":"85","state":"正常"}, 
    {"code":"834392","name":"joyin","day":"2018-5-3","preprice":"77","startprice":"77","maxprice":"88","minprice":"77","endprice":"85","state":"正常"}, 
    {"code":"834392","name":"joyin","day":"2018-5-3","preprice":"88","startprice":"77","maxprice":"88","minprice":"77","endprice":"85","state":"正常"}
            ],
            searchOnInit: true,
            cols: [
                {field:'code', title: '股票代码', sort: true},
                {field:'name', title: '股票简称'},
                {field:'day', title: '交易日', sort: true},
                {field:'preprice', title: '前收盘价（元）', sort: true},
                {field:'startprice', title: '开盘价（元）', sort: true},
                {field:'maxprice', title: '最高价（元）', sort: true},
                {field:'minprice', title: '最低价（元）', sort: true},
                {field:'endprice', title: '收盘价（元）', sort: true},
                {field:'state', title: '交易状态', sort: true},
               
            ]
        }); })
})

//点击导出按钮
$('#ValSharesMarketInfo001_BTN_export').click(function() {
    
    
    
})