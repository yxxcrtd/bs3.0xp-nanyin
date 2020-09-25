/**
 * 股票委托查询
 */
(function () {
    /**
     * 获取查询参数
     */
    var searchform = function () {
        var data = $.serializeToObject('#searchForm');
        if ($('#portfolioId').val()) {
            data.portfolioId = $('#portfolioId').val().join(",");
        }
        return data;
    };

    /**
     * 新建
     */
    function showDialog() {
        Joyin.dialog(JoyinUtil.getLabel('EtrStock002'), basePath + 'invest/entrust/etrstock/EtrStock002/init', '98%', '95%', function () {
            table.reload();
        });
    }

    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function () {
                return searchform();
            }
        },
        dblclick: function (table, data) {
            data.operateType = "dblclick";
            data.pageModel = PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("EtrStock002.refence"),
                JoyinUtil.getRealPath("invest/entrust/etrstock/EtrStock002/init"),
                "98%", "95%", data,
                function () {
                    table.reload();
                }
            );
        },
        cols: [
            {field: 'entrustStatus', visible: false},
            {field: 'busEntrustStatus', title: 'EtrStock001.entrustStatus', width: '10%', sortField: "entrustStatus"},
            {field: 'entrustId', visible: false},
            {field: 'portfolioId', title: 'EtrStock001.portfolioId', width: '10%',
                render: function (data, type, row, meta) {
                    return "<a  class='pro-link portfolioIdLink' style='cursor: pointer;'>" + row.portfolioId + "</ a>";
                }, sortField: "portfolioId"
            },
            {field: 'portfolioName', title: 'EtrStock001.portfolioName', width: '10%', sortField: "portfolioName"},
            {field: 'finprodId', visible: false},
            {field: 'finprodMarketId', title: 'EtrStock001.finprodId', width: '10%',
                render: function (data, type, row, meta) {
                    return "<a  class='pro-link finprodIdLink' style='cursor: pointer;'>" + data + "</ a>";
                }, sortField: "finprodMarketId"
            },
            {field: 'finprodName', title: 'EtrStock001.finprodName', width: '10%', sortField: "finprodName"},
            {field: 'ps', visible: false},
            {field: 'busPs', title: 'EtrStock001.ps', width: '10%', sortField: "ps"},
            {field: 'tradeMarket', visible: false},
            {field: 'busTradeMarket', title: 'EtrStock001.tradeMarket', width: '10%', sortField: "tradeMarket"},
            {field: 'dealMode', visible: false},
            {field: 'busDealMode', title: 'EtrStock001.dealMode', width: '10%', sortField: "dealMode"},
            {field: 'board', visible: false},
            {field: 'busBoard', title: 'EtrStock001.board', width: '10%', sortField: "board"},
            {field: 'ccy', visible: false},
            {field: 'busCcy', title: 'EtrStock001.ccy', width: '10%', sortField: "ccy"},
            {field: 'vdate', title: 'EtrStock001.vdate', width: '10%', type: 'date', sortField: "vdate"},
            {field: 'mdate', title: 'EtrStock001.mdate', width: '10%', type: 'date', sortField: "mdate"},
            {field: 'entrustNum',type: 'number',title: 'EtrStock001.entrustNum',width: '10%',sortField: "entrustNum"},
            {field: 'entrustAmt',type: 'number',title: 'EtrStock001.entrustAmt',width: '10%',sortField: "entrustAmt"},
            {field: 'price', type: 'number', title: 'EtrStock001.price', width: '10%', sortField: "price"},
            {field: 'finishAmt', type: 'number', title: 'EtrStock001.finishAmt', width: '10%', sortField: "finishAmt"},
            {field: 'priceModelStr', title: 'EtrStock001.priceModel', width: '10%', sortField: "priceModel"}, //新增价格模式	王贵奇	2019/10/10
            {field: 'transactioncom', title: 'EtrStock001.transactioncom',
                render: function (data, type, row, meta) {
                    return "<div class='layui-progress layui-progress-big' style='margin-top:5px;'><div class='layui-progress-bar layui-bg-green' style='width:" + (row.transactioncom*100).toFixed(2) + "%'><span class='layui-progress-text'>" + (row.transactioncom*100).toFixed(2) + "%</span></div></div>";
                }, width: '10%', sortable: false
            },
            {field: 'action', title: '操作', type: 'action', buttons: ['edit'], width: '10%'}
        ]
    });
    /**
     * 导出
     */
    var exportExcel = function () {
        Joyin.exportExcel(basePath + "invest/entrust/etrstock/EtrStock001/export", searchform());
    };

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function (data, index) {
        data.operateType = "edit";
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'invest/entrust/etrstock/EtrStock001/check', data, function (result) {
            Joyin.dialog(JoyinUtil.getLabel("EtrStock002.edit"),
                JoyinUtil.getRealPath("invest/entrust/etrstock/EtrStock002/init"),"98%", "95%", data,
                function () {
                    table.reload();
                }
            );
        });
    });
    /**
     * 绑定删除事件
     */

    /**
     * 组合代码链接
     */
    table.bind('click', '.portfolioIdLink', function (row) {
        Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init","98%", "90%", {
            pageModel: PAGE_MODEL_REFERENCE,//page_model:查看
            portfolioId: row.portfolioId
        });
    });
    /**
     * 资产代码链接
     */
    table.bind('click', '.finprodIdLink', function (row) {
        Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),basePath + "invest/asset/stock/FinStockInfo002/init","95%", "80%", {
            pageModel: PAGE_MODEL_REFERENCE,
            finprodId: row.finprodId,  //金融产品代码
        });
    });
    /**
     * 编辑按钮事件
     */
    $(function () {
        /**
         * 查询按钮
         */
        $('#EtrStock001_btn_search').click(function () {
            table.load();
            $('#EtrStock001_btn_close').show();
            $('#EtrStock001_btn_revoke').show();
        });
        /**
         * 导出按钮
         */
        $('#EtrStock001_btn_export').click(function () {
            exportExcel();
        });
        /**
         * 新建
         */
        $('#EtrStock001_btn_add').click(function () {
            showDialog();
        });
        /**
         * 关闭委托
         */
        $('#EtrStock001_btn_close').click(function () {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            else {
                var object = {selectedItem: selArr, operateType: "close"};
                Joyin.ajaxJson(basePath + 'invest/entrust/etrstock/EtrStock001/check', object, function (result) {
                    Joyin.confirm(JoyinUtil.getMessage("ETR_C_0002"), function () {
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrstock/EtrStock001/close', object, function (result) {
                            table.reload();
                        });
                    });
                });
            }
        });
        /**
         * 撤销
         */
        $('#EtrStock001_btn_revoke').click(function () {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }else {
                var object = {selectedItem: selArr, operateType: "recall"};
                Joyin.ajaxJson(basePath + 'invest/entrust/etrstock/EtrStock001/check', object, function (result) {
                    Joyin.confirm(JoyinUtil.getMessage("BUSS_COM_C_0032", "撤销"), function () {
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrstock/EtrStock001/recall', object, function (result) {
                            table.reload();
                        });
                    });
                });
            }
        });
        // 多对多导入
        $('#EtrStock001_btn_import').click(function() {
                Joyin.uploadFile({pageId:'EtrStock003MTM'},function(fileName) {
                            Joyin.ajax(basePath + 'invest/entrust/etrstock/EtrStock003/checkImportData', {fileName: fileName}, function(checkResult) {
                                // 如果大于100条数据，提示大于100条重新导入，如果不大于100条数据，进入多对对界面
                                if(checkResult.data.correctData[1] != "0"){
                                setTimeout( function () {
                                    Joyin.confirm(checkResult.data.confirmMsg,function() {
                                        var result = {};
                                        result.tempList = checkResult.data.correctData;
                                        result.operateType = "import";
                                        result.pageModel='';
                                        Joyin.dialog(JoyinUtil.getLabel("EtrStock003.edit"),
                                            JoyinUtil.getRealPath("invest/entrust/etrstock/EtrStock003/init"),
                                            "98%", "95%", result,
                                            function(){table.reload();});
                                    });
                                },1000);
                                }else{
                                    Joyin.alert(checkResult.data.confirmMsg);
                                }
                            });
                },function() {
                    table.reload();
                });
           
        });
    });
}());