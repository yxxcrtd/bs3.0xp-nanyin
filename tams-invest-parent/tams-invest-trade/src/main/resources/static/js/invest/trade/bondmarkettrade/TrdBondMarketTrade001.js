(function(){
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        var pageParam = {
            finprodId: $('#finprodId').val()
            ,appearMarketId: $('#appearMarketId').val()
            ,tradeDateBegin: $('#tradeDate').startDate()
            ,tradeDateEnd: $('#tradeDate').endDate()
        };
        if ($("#finprodId").val()) {
            pageParam.finprodId = $("#finprodId").val().join(",");
        } else {
            pageParam.finprodId = null;
        }
        if ($("#appearMarketId").val()) {
            pageParam.appearMarketId = $("#appearMarketId").val().join(",");
        } else {
            pageParam.appearMarketId = null;
        }
        return pageParam;
    };

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
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            data.totleTradeId = data.totleTradeId;
            Joyin.dialog(JoyinUtil.getLabel("TrdBondMarketTrade002.reference"),
                JoyinUtil.getRealPath("invest/trade/bondmarkettrade/TrdBondMarketTrade002/init"),
                "99%", "99%", data/*,
                    function(){table.reload();}*/);
        },
        cols:[
            {field:'totleTradeId', sortField:'totleTradeId',width:220, title: 'TrdBondMarketTrade001.totleTradeId',visible:false}
            ,{field:'rowNum',width:60, title: 'TrdBondMarketTrade001.rowNum',sortable: false}
            ,{field:'finprodId',sortField:'finprodId', width:180, title: 'TrdBondMarketTrade001.finprodId'}
            ,{field:'finprodAbbr',sortField:'finprodAbbr', width:180, title: 'TrdBondMarketTrade001.finprodAbbr'}
            ,{field:'tradeMarket', sortField:'tradeMarket',width:130, title: 'TrdBondMarketTrade001.tradeMarket',visible:false}
            ,{field:'tradeMarketText', sortField:'tradeMarket',width:130, title: 'TrdBondMarketTrade001.tradeMarket'}
            ,{field:'appearMarketId',sortField:'appearMarketId', width:180, title: 'TrdBondMarketTrade001.appearMarketId'}
            ,{field:'appearMarketAbbr',sortField:'appearMarketAbbr', width:180, title: 'TrdBondMarketTrade001.appearMarketAbbr'}
            ,{field:'tradeDate', sortField:'tradeDate',width:100, title: 'TrdBondMarketTrade001.tradeDate', type: 'date'}
        ],
    });

    $(function () {
        // 绑定按钮事件
        $('#TrdBondMarketTrade001_btn_search').click(function() {
            table.load();
        });

        $('#TrdBondMarketTrade001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("TrdBondMarketTrade002"), basePath
                + "invest/trade/bondmarkettrade/TrdBondMarketTrade002/init", "99%", "99%", {
                pageModel : PAGE_MODEL_NEW
            }, function() {
                table.reload();
            });
        });


        /**
         * 撤销
         */
        $('#TrdBondMarketTrade001_btn_revoke').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            // 列表中未勾选
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            // 列表中勾选对象1条以上时
            if (selArr.length > 1) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                return;
            }
            // 流程中不允许撤销
            if(selArr[0].validStatus == '01'){
                Joyin.error(JoyinUtil.getMessage("流程中的交易不允许撤销"));
                return;
            }
            debugger;
            var checkObj = {};
            checkObj.totleTradeId = selArr[0].totleTradeId;
            checkObj.tradeDate = selArr[0].tradeDate;
            checkObj.finprodAbbr = selArr[0].finprodAbbr;
            checkObj.finprodId = selArr[0].finprodId;
            checkObj.tradeMarket = selArr[0].tradeMarket;
            checkObj.appearMarketId = selArr[0].appearMarketId;
            checkObj.appearMarketAbbr = selArr[0].appearMarketAbbr;
            checkObj.operateType = "revoke";
            Joyin.ajaxJson(basePath + 'invest/trade/bondmarkettrade/TrdBondMarketTrade001/checkRevoke', checkObj, function(result) {
                var resdata = result.data;
                Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                    Joyin.confirm("是否确认撤销", function() {
                        result = result.replace(/\s+/g,"");
                        if (!result) {
                            result = '无';
                        }
                        checkObj.revokeReason = result;
                        WorkFlow.doRevokeList(basePath + 'invest/trade/bondmarkettrade/TrdBondMarketTrade001/doRevoke', checkObj, "TrdBondMarketTrade001_btn_revoke", "TrdBondMarketTrade002");
                    });
                });
            });
        });
        
        
    });
}());