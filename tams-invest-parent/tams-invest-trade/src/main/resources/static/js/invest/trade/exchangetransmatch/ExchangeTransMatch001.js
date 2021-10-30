(function () {

    var getPageParams = function() {
        var object = $.serializeToObject('#searchForm', true);

        if(object.applyNo) {
            object.applyNo = object.applyNo.join(",")
        }

        if(object.tradeDate) {
            object.tradeDateBegin = object.tradeDate.startDate();
            object.tradeDateEnd = object.tradeDate.endDate();
        }
        return object;
    };

    var getDealPageParams = function() {
        var object = $.serializeToObject('#searchForm_deal', true);

        if(object.portfolioId) {
            object.portfolioId = object.portfolioId.join(",")
        }

        if(object.finprodType2) {
            object.finprodType2 = object.finprodType2.join(",")
        }

        if(object.tradeDate) {
            object.tradeDateBegin = object.tradeDate.startDate();
            object.tradeDateEnd = object.tradeDate.endDate();
        }
        return object;
    };

    var getTransMatchInfoPageParams = function () {
        var object = $.serializeToObject('#searchForm_TransMacthInfo', true);
        if(object.tradeDate) {
            object.tradeDateBegin = object.tradeDate.startDate();
            object.tradeDateEnd = object.tradeDate.endDate();
        }
        return object;
    }

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },

        cols:[
            {field:'matchStatusStr', sortField:'matchStatus',width:200, title: 'ExchangeTransMatch001.matchStatus'},
            {field:'matchProgressStr', sortField:'matchProgress',width:200, title: 'ExchangeTransMatch001.matchProgress'},
            {field:'tradeMarketStr', sortField:'tradeMarket',width:200, title: 'ExchangeTransMatch001.tradeMarket'},
            {field:'applyNo', sortField:'applyNo',width:200, title: 'ExchangeTransMatch001.applyNo'},
            {field:'secAcctNo', sortField:'secAcctNo',width:200, title: 'ExchangeTransMatch001.secAcctNo'},
            {field:'finprodType2Str', sortField:'finprodType2',width:200, title: 'ExchangeTransMatch001.finprodType2'},
            {field:'finprodMarketId', sortField:'finprodMarketId',width:200, title: 'ExchangeTransMatch001.finprodId'},
            {field:'finprodAbbr', sortField:'finprodAbbr',width:200, title: 'ExchangeTransMatch001.finprodAbbr'},
            {field:'seatNo', sortField:'seatNo',width:200, title: 'ExchangeTransMatch001.seatNo'},
            {field:'tradePlatStr', sortField:'tradePlat',width:200, title: 'ExchangeTransMatch001.tradePlat'},
            {field:'psStr', sortField:'ps',width:200, title: 'ExchangeTransMatch001.ps'},
            {field:'remainAmount', sortField:'remainAmount',width:200, title: 'ExchangeTransMatch001.remainAmount',type: 'money'},
            {field:'remainNum', sortField:'remainNum',width:200, title: 'ExchangeTransMatch001.remainNum',type: 'number'},
            {field:'dealPrice', sortField:'dealPrice',width:200, title: 'ExchangeTransMatch001.dealPrice',type: 'number'},
            {field:'dealDate', sortField:'dealDate',width:200, title: 'ExchangeTransMatch001.dealDate',type: 'date'},
            {field:'fundAccount', sortField:'fundAccount',width:200, title: 'ExchangeTransMatch001.fundAccount'},

            {field:'matchStatus', visible:false},
            {field:'matchProgress', visible:false},
            {field:'finprodType2', visible:false},
            {field:'finprodId', visible:false},
            {field:'ps', visible:false},
            {field:'tradeMarket', visible:false},
            {field:'tradePlat', visible:false}
        ]

    });

    /**
     * 定义数据表格
     */
    var entrustDataTable=$("#entrustDataTable").joyintable({
        ajax: {
            url: 'findEntrustPage',
            type: 'POST',
            data: function() {
                return getDealPageParams();
            }
        },
        cols:[
            {field:'entrustTypeStr', sortField:'entrustType',width:200, title: 'ExchangeTransMatch001.entrustType'},
            {field:'portfolioId', sortField:'portfolioId',width:200, title: 'ExchangeTransMatch001.portfolioId'},
            {field:'portfolioName', sortField:'portfolioName',width:200, title: 'ExchangeTransMatch001.portfolioName'},
            {field:'finprodMarketId', sortField:'finprodMarketId',width:200, title: 'ExchangeTransMatch001.finprodId'},
            {field:'finprodAbbr', sortField:'finprodAbbr',width:200, title: 'ExchangeTransMatch001.finprodAbbr'},
            {field:'entrustDate', sortField:'entrustDate',width:200, title: 'ExchangeTransMatch001.entrustDate',type: 'date'},
            {field:'tradeDate', sortField:'tradeDate',width:200, title: 'ExchangeTransMatch001.tradeDate',type: 'date'},
            {field:'psStr', sortField:'ps',width:200, title: 'ExchangeTransMatch001.ertPs'},
            {field:'tradeMarketStr', sortField:'tradeMarket',width:200, title: 'ExchangeTransMatch001.tradeMarket'},
            {field:'tradePlatStr', sortField:'tradePlat',width:200, title: 'ExchangeTransMatch001.tradePlat'},
            {field:'entrustAmount', sortField:'entrustAmount',width:200, title: 'ExchangeTransMatch001.entrustAmount',type: 'number'},
            {field:'entrustPrice', sortField:'entrustPrice',width:200, title: 'ExchangeTransMatch001.entrustPrice',type: 'number'},
            {field:'entrustNum', sortField:'entrustNum',width:200, title: 'ExchangeTransMatch001.entrustNum',type: 'number'},
            {field:'secAcctNo', sortField:'secAcctNo',width:200, title: 'ExchangeTransMatch001.secAcctNo'},
            {field:'fundAccount', sortField:'fundAccount',width:200, title: 'ExchangeTransMatch001.fundAccount'},
            {field:'entrustType', visible:false},
            {field:'ps', visible:false},
            {field:'tradeMarket', visible:false},
            {field:'tradePlat', visible:false}
        ]

    });

    /**
     * 定义数据表格
     */
    var transMatchInfoTable=$("#exchageTransMatchInfo").joyintable({
        ajax: {
            url: 'findTransMatchInfoPage',
            type: 'POST',
            data: function() {
                return getTransMatchInfoPageParams();
            }
        },
        cols:[
            {field:'matchTypeStr', sortField:'matchType',width:200, title: 'ExchangeTransMatch001.matchType'},
            {field:'busiCode', sortField:'busiCode',width:200, title: 'ExchangeTransMatch001.busiCode'},
            {field:'tradeMarketStr', sortField:'tradeMarket',width:200, title: 'ExchangeTransMatch001.tradeMarket'},
            {field:'applyNo', sortField:'applyNo',width:200, title: 'ExchangeTransMatch001.applyNo'},
            {field:'portfolioId', sortField:'portfolioId',width:200, title: 'ExchangeTransMatch001.portfolioId'},
            {field:'portfolioName', sortField:'portfolioName',width:200, title: 'ExchangeTransMatch001.portfolioName'},
            {field:'finprodType2Str', sortField:'finprodType2',width:200, title: 'ExchangeTransMatch001.finprodType2'},
            {field:'finprodMarketId', sortField:'finprodMarketId',width:200, title: 'ExchangeTransMatch001.finprodId'},
            {field:'psStr', sortField:'ps',width:200, title: 'ExchangeTransMatch001.ps'},
            {field:'tradeAmout', sortField:'tradeAmout',width:200, title: 'ExchangeTransMatch001.tradeAmout',type: 'number'},
            {field:'tradeNum', sortField:'tradeNum',width:200, title: 'ExchangeTransMatch001.tradeNum',type: 'number'},
            {field:'tradePrice', sortField:'tradePrice',width:200, title: 'ExchangeTransMatch001.tradePrice',type: 'number'},
            {field:'tradeDate', sortField:'tradeDate',width:200, title: 'ExchangeTransMatch001.tradeDate',type: 'date'},
            {field:'ccyStr', sortField:'ccy',width:200, title: 'ExchangeTransMatch001.ccy'},
            {field:'matchType', visible:false},
            {field:'tradeMarket', visible:false},
            {field:'finprodType2', visible:false},
            {field:'ps', visible:false},
            {field:'ccy', visible:false}
        ]

    });

    /**
     * 画面加载完成
     */
    $(function() {

        //成交数据查询
        $('#ExchangeTransMatch001_deal_btn_search').click(function () {
            table.table?table.reload():table.load();
        });

        //委托交易数据查询
        $('#ExchangeTransMatch001_entrust_btn_search').click(function () {
            entrustDataTable.table?entrustDataTable.reload():entrustDataTable.load();
        });

        //匹配信息查询
        $('#ExchangeTransMatch001_btn_search').click(function () {
            transMatchInfoTable.table?transMatchInfoTable.reload():transMatchInfoTable.load();
        });

        //成交数据-匹配查询
        $('#ExchangeTransMatch001_deal_btn_match_search').click(function () {
            if(table) {
                var selArr = table.getAllCheckedRows();
                if (!selArr || selArr.length == 0) {
                    Joyin.error(JoyinUtil.getMessage("TRDFUND_E_XXT_001"));
                    return;
                }else if(!selArr || selArr.length > 1){
                    Joyin.error(JoyinUtil.getMessage("REPORT_COMMON_E_0012"));
                    return;
                }

            }

        });

        //成交数据-成交匹配
        $('#ExchangeTransMatch001_deal_btn_transaction_macth').click(function () {

            var object = $.serializeToObject('#searchForm_deal', true);

            if(table) {
                var dealArr = table.getAllCheckedRows();
                if (!dealArr || dealArr.length == 0) {
                    Joyin.error(JoyinUtil.getMessage("TRDFUND_E_XXT_001"));
                    return;
                }else if(!dealArr || dealArr.length > 1){
                    Joyin.error(JoyinUtil.getMessage("REPORT_COMMON_E_0012"));
                    return;
                }
                object.dealList = dealArr;
            }

            if(entrustDataTable) {
                var entrustArr = entrustDataTable.getAllCheckedRows();
                if (!entrustArr || entrustArr.length == 0) {
                    Joyin.error(JoyinUtil.getMessage("TRDFUND_E_XXT_001"));
                    return;
                }
                object.entrustList = entrustArr;
            }

            Joyin.ajaxJson(basePath + 'invest/trade/exchangetransmatch/ExchangeTransMatch001/check', object, function(result) {
                Joyin.confirm("请确认是否进行成交匹配？", function() {
                    Joyin.ajaxJson(JoyinUtil.getRealPath('invest/trade/exchangetransmatch/ExchangeTransMatch001/transactionMacth'),object,function (result) {
                        table.table?table.reload():table.load();
                        EntrustTable.table?EntrustTable.reload():EntrustTable.load();
                    },false);
                });
            });
        });

        //成交数据-成交录单
        $('#ExchangeTransMatch001_deal_btn_transaction').click(function () {
            if(table) {
                var selArr = table.getAllCheckedRows();
                if (!selArr || selArr.length == 0) {
                    Joyin.error(JoyinUtil.getMessage("TRDFUND_E_XXT_001"));
                    return;
                }else if(!selArr || selArr.length > 1){
                    Joyin.error(JoyinUtil.getMessage("REPORT_COMMON_E_0012"));
                    return;
                }

            }
        });

        //委托交易数据-匹配查询
        $('#ExchangeTransMatch001_entrust_btn_match_search').click(function () {

        });

        //委托交易数据-成交匹配
        $('#ExchangeTransMatch001_entrust_btn_transaction_macth').click(function () {

        });

        //匹配查询-提交
        $('#TrdMatchInfo_btn_submit').click(function () {
        });

        //匹配查询-取消匹配
        $('#TrdMatchInfo_btn_quit').click(function () {

        });

        //成交重置按钮
        $('#ExchangeTransMatch001_deal_btn_clean').click(function () {
            $('#tradeMarket').val('').trigger('change');
            $('#tradePlat').val('').trigger('change');
            $('#ps').val('').trigger('change');
            $('#secAcctNo').val('').trigger('change');
            $('#applyNo').val('').trigger('change');
            $('#finprodId').val('').trigger('change');
            $('#tradeDate').val('').trigger('change');
            $('#matchStatus').val('').trigger('change');
            $('#tradeAmt1').val('').trigger('change');
            $('#tradeAmt2').val('').trigger('change');
        });

        //委托交易重置按钮
        $('#ExchangeTransMatch001_entrust_btn_clean').click(function () {
            $('#portfolioId').val('').trigger('change');
            $('#dealFinprodType2').val('').trigger('change');
            $('#dealFinprodId').val('').trigger('change');
            $('#dealPs').val('').trigger('change');
            $('#dealTradePlat').val('').trigger('change');
            $('#deaalTradeDate').val('').trigger('change');
        });

        //更多按钮
        $('#ExchangeTransMatch001_deal_btn_more').click(function () {
            if($('#ExchangeTransMatch001_deal_btn_more').find('span').html()=="更多"){
                $('#ExchangeTransMatch001_more_div').show();
                $('#ExchangeTransMatch001_deal_btn_more').find('span').html("取消更多");
            }else{
                $('#ExchangeTransMatch001_more_div').hide();
                $('#ExchangeTransMatch001_deal_btn_more').find('span').html("更多");
            }
        });
    });

}());