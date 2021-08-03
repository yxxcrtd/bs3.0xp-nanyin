/**
 * 期货类标 的资产
 */

$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        var pageParam =  {
            finprodMarketId: $("#finprodMarketId").val(),
            futuresType: $("#futuresType").val(),
            tradeMarket: $("#tradeMarket").val(),
            futuresContractId: $("#futuresContractId").val(),
            lastTradeDateStart:$('#lastTradeDate').startDate(),
            lastTradeDateEnd:$('#lastTradeDate').endDate(),
            status: $("#status").val()
        };
        return pageParam;
    };
    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function () {
                return getSearchParams();
            }
        },
        /**
         * 双击查看
         */
        dblclick: function (table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                basePath + "invest/asset/futures/FinFutures002/init",
                "95%", "90%", {pageModel: PAGE_MODEL_REFERENCE, finprodId: data.finprodId});
        },

        cols: [
            {field: 'finprodMarketId', width: 150, title: 'FinFutures001.finprodMarketId'}
            , {
                field: 'futuresTypeStr',
                sortField: 'futuresType',
                width: 150,
                title: 'FinFutures001.futuresType'
            }
            , {
                field: 'futuresContractIdStr',
                sortField: 'futuresContractId',
                width: 150,
                title: 'FinFutures001.futuresContractId'
            }
            , {
                field: 'tradeMarketStr',
                sortField: 'tradeMarket',
                width: 150,
                title: 'FinFutures001.tradeMarket'
            }
            , {
                field: 'lastTradeDate',
                width: 150,
                title: 'FinFutures001.lastTradeDate',
                type: 'date'
            }
            , {
                field: 'lastSettleDate',
                width: 150,
                title: 'FinFutures001.lastSettleDate',
                type: 'date'
            }
            , {
                field: 'deliveryMethodStr',
                sortField: 'deliveryMethod',
                width: 150,
                title: 'FinFutures001.deliveryMethod'
            }
            , {field: 'ccy', width: 150, title: 'FinFutures001.ccy'}
            , {
                field: 'operation',
                width: 100,
                title: 'SysCommon.operation',
                type: 'action',
                buttons: ['edit', 'delt']
            }
        ]
    });
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function (data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'invest/asset/futures/FinFutures001/check', data, function (result) {
            Joyin.ajaxJson(basePath + 'invest/asset/futures/FinFutures001/editCheck', data, function (editResult) {
                Joyin.dialog(JoyinUtil.getLabel("FinFutures002.edit"),
                    basePath + "invest/asset/futures/FinFutures002/init",
                    "95%", "90%", data, function () {
                        table.reload();
                    });
            });
        });
    });

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function (data, index) {
        var object = {selectedItem: [data], operateType: "delete"};
        Joyin.ajaxJson(basePath + 'invest/asset/futures/FinFutures001/check', object, function (result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")), function () {
                WorkFlow.doDeleteList(basePath + 'invest/asset/futures/FinFutures001/delete', object,
                    "FinFutures001_btn_delete", "FinFutures002", function () {
                        table.reload();
                    });
            });
        });
    });
    /**
     * 绑定导出事件
     */
    var exportExcel = function () {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    /**
     * 画面加载完成
     */
    $(function () {
    	$("#status").val("01");
    	table.load();
        // 绑定按钮事件
        $('#FinFutures001_btn_search').click(function () {
        	$("#status").val("02");
            table.load();
            $('#FinFutures001_btn_delete').show();
        });
        $('#FinFutures001_btn_export').click(function () {
            exportExcel();
        });
        $('#FinFutures001_btn_add').click(function () {
            Joyin.dialog(JoyinUtil.getLabel("FinFutures002"),
                basePath + "invest/asset/futures/FinFutures002/init",
                "98%", "98%", {pageModel: PAGE_MODEL_NEW});
        });
        $('#FinFutures001_btn_delete').click(function () {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType: "delete"};
            Joyin.ajaxJson(basePath + 'invest/asset/futures/FinFutures001/check', object, function (result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")), function () {
                    WorkFlow.doDeleteList(basePath + 'invest/asset/futures/FinFutures001/delete', object,
                        "FinFutures001_btn_delete", "FinFutures002");
                });
            });
        });

    });
    //是否展示资产四五级分类
    if (IS_SHOW_TYPE == '01') {
        $('#type11Div').show();
    } else {
        $('#type11Div').hide();
    }
}());