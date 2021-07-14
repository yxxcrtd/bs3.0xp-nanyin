/**
 * 新股申购查询
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
        Joyin.dialog(JoyinUtil.getLabel('EtrNewStock002'), basePath + 'invest/entrust/etrnewstock/EtrNewStock002/init', '98%', '95%', function () {
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
            Joyin.dialog(JoyinUtil.getLabel("EtrNewStock002.refence"),
                JoyinUtil.getRealPath("invest/entrust/etrnewstock/EtrNewStock002/init"),
                "98%", "95%", data,
                function () {
                    table.reload();
                }
            );
        },
        cols: [
            {field: 'entrustStatus', visible: false},
            //有效状态
            {field: 'busEntrustStatus', title: 'EtrNewStock001.entrustStatus', width: '10%', sortField: "entrustStatus"},
            //投资经理
            {field: 'invManagerId', title: 'EtrNewStock001.invManagerId', width: '10%', sortField: "invManagerId"},
            //组合代码
            {field: 'portfolioId', title: 'EtrNewStock001.portfolioId', width: '10%',
                render: function (data, type, row, meta) {
                    return "<a  class='pro-link portfolioIdLink' style='cursor: pointer;'>" + row.portfolioId + "</ a>";
                }, sortField: "portfolioId"
            },
            //组合名称
            {field: 'portfolioName', title: 'EtrNewStock001.portfolioName', width: '10%', sortField: "portfolioName"},
            {field: 'finprodId', visible: false},
            //资产代码
            {field: 'finprodMarketId', title: 'EtrNewStock001.finprodId', width: '10%',
                render: function (data, type, row, meta) {
                    return "<a  class='pro-link finprodIdLink' style='cursor: pointer;'>" + row.finprodMarketId + "</ a>";
                }, sortField: "finprodMarketId"
            },
            //资产名称
            {field: 'finprodAbbr', title: 'EtrNewStock001.finprodAbbr', width: '10%', sortField: "finprodAbbr"},
            //申购日期
            {field: 'entrustDate', title: 'EtrNewStock001.entrustDate', width: '10%', type: 'date', sortField: "entrustDate"},
            //申购方式
            {field: 'purchModle', title: 'EtrNewStock001.purchModle', width: '10%', sortField: "purchModle"},
            //申购数量
            {field: 'entrustNum',type: 'number',title: 'EtrNewStock001.entrustNum',width: '10%',sortField: "entrustNum"},
            //申购价格
            {field: 'price', type: 'number', title: 'EtrNewStock001.price', width: '10%', sortField: "price"},
            //申购金额
            {field: 'entrustAmt',type: 'number',title: 'EtrNewStock001.entrustAmt',width: '10%',sortField: "entrustAmt"},
            //币种
            {field: 'ccy', visible: false},
            {field: 'busCcy', title: 'EtrNewStock001.ccy', width: '10%', sortField: "ccy"},
            //备注
            {field: 'remark', title: 'EtrNewStock001.remark', width: '10%', sortField: "remark"},
            //操作
            {field: 'action', title: '操作', type: 'action', buttons: ['edit'], width: '10%'}
        ]
    });
    /**
     * 导出
     */
    var exportExcel = function () {
        Joyin.exportExcel(basePath + "invest/entrust/etrnewstock/EtrNewStock001/export", searchform());
    };

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function (data, index) {
        data.operateType = "edit";
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'invest/entrust/etrnewstock/EtrNewStock001/check', data, function (result) {
            Joyin.dialog(JoyinUtil.getLabel("EtrNewStock002.edit"),
                JoyinUtil.getRealPath("invest/entrust/etrnewstock/EtrNewStock002/init"),"98%", "95%", data,
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
        $('#EtrNewStock001_btn_search').click(function () {
            table.load();
            $('#EtrNewStock001_btn_close').show();
            $('#EtrNewStock001_btn_revoke').show();
        });
        /**
         * 导出按钮
         */
        $('#EtrNewStock001_btn_export').click(function () {
            exportExcel();
        });
        /**
         * 新建
         */
        $('#EtrNewStock001_btn_add').click(function () {
            showDialog();
        });
        /**
         * 关闭委托
         */
        $('#EtrNewStock001_btn_close').click(function () {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            else {
                var object = {selectedItem: selArr, operateType: "close"};
                Joyin.ajaxJson(basePath + 'invest/entrust/etrnewstock/EtrNewStock001/check', object, function (result) {
                    Joyin.confirm(JoyinUtil.getMessage("ETR_C_0002"), function () {
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrnewstock/EtrNewStock001/close', object, function (result) {
                            table.reload();
                        });
                    });
                });
            }
        });
        /**
         * 撤销
         */
        $('#EtrNewStock001_btn_revoke').click(function () {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }else {
                var object = {selectedItem: selArr, operateType: "recall"};
                Joyin.ajaxJson(basePath + 'invest/entrust/etrnewstock/EtrNewStock001/check', object, function (result) {
                    Joyin.confirm(JoyinUtil.getMessage("BUSS_COM_C_0032", "撤销"), function () {
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrnewstock/EtrNewStock001/recall', object, function (result) {
                            table.reload();
                        });
                    });
                });
            }
        });
        // 多对多导入
        $('#EtrNewStock001_btn_import').click(function() {
                Joyin.uploadFile({pageId:'EtrStock003MTM'},function(fileName) {
                            Joyin.ajax(basePath + 'invest/entrust/etrnewstock/EtrStock003/checkImportData', {fileName: fileName}, function(checkResult) {
                                // 如果大于100条数据，提示大于100条重新导入，如果不大于100条数据，进入多对对界面
                                if(checkResult.data.correctData[1] != "0"){
                                setTimeout( function () {
                                    Joyin.confirm(checkResult.data.confirmMsg,function() {
                                        var result = {};
                                        result.tempList = checkResult.data.correctData;
                                        result.operateType = "import";
                                        result.pageModel='';
                                        Joyin.dialog(JoyinUtil.getLabel("EtrStock003.edit"),
                                            JoyinUtil.getRealPath("invest/entrust/etrnewstock/EtrStock003/init"),
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