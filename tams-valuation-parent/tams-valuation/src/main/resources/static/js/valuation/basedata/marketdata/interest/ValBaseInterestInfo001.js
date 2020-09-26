$(function() {
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        data:[
              {"publishdate":"2018-04-24","interestname":"一年期定期存款利率","interestcode":"DR007","currency":"CNY","interestrate":"3.000000000000","effectdate":"2017-05-01"},
              {"publishdate":"2018-04-24","interestname":"一年期定期存款利率","interestcode":"DR007","currency":"CNY","interestrate":"3.000000000000","effectdate":"2017-05-01"},
              {"publishdate":"2018-04-24","interestname":"一年期定期存款利率","interestcode":"DR007","currency":"CNY","interestrate":"3.000000000000","effectdate":"2017-05-01"},
              {"publishdate":"2018-04-24","interestname":"一年期定期存款利率","interestcode":"DR007","currency":"CNY","interestrate":"3.000000000000","effectdate":"2017-05-01"},
              {"publishdate":"2018-04-24","interestname":"一年期定期存款利率","interestcode":"DR007","currency":"CNY","interestrate":"3.000000000000","effectdate":"2017-05-01"},
              {"publishdate":"2018-04-24","interestname":"一年期定期存款利率","interestcode":"DR007","currency":"CNY","interestrate":"3.000000000000","effectdate":"2017-05-01"},
              {"publishdate":"2018-04-24","interestname":"一年期定期存款利率","interestcode":"DR007","currency":"CNY","interestrate":"3.000000000000","effectdate":"2017-05-01"},
              {"publishdate":"2018-04-24","interestname":"一年期定期存款利率","interestcode":"DR007","currency":"CNY","interestrate":"3.000000000000","effectdate":"2017-05-01"},
              {"publishdate":"2018-04-24","interestname":"一年期定期存款利率","interestcode":"DR007","currency":"CNY","interestrate":"3.000000000000","effectdate":"2017-05-01"},
              {"publishdate":"2018-04-24","interestname":"一年期定期存款利率","interestcode":"DR007","currency":"CNY","interestrate":"3.000000000000","effectdate":"2017-05-01"},
            ],
        //height:781,
        searchOnInit: true,
        cols:[
              {field:'publishdate', width:100, title: '发布日', sort: true},
              {field:'interestname', width:260, title: '利率名称'},
              {field:'interestcode', width:100, title: '利率代码', sort: true},
              {field:'currency', width:300, title: '币种'},
              {field:'interestrate', width:100, title: '利率值', sort: true},
              {field:'effectdate', width:100, title: '生效日', sort: true},
            ]
    });
    
//点击新建按钮
$('#ValBaseInterestInfo001_btn_add').click(function() {
        Joyin.dialog(JoyinUtil.getLabel("基准利率信息新建"),
        basePath + "/valuation/basedata/marketdata/interest/ValBaseInterestInfo002/init",
        "420px", "240px", {pageModel:PAGE_MODEL_NEW});
    });
}());