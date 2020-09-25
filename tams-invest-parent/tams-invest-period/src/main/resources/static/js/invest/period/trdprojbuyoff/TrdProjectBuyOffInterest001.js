var TrdProjectBuyOffInterest001 = function () {

    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        var result = $.serializeToObject('#searchForm');
        if (result.assetType) {
            result.assetType = result.assetType.join(",")
        }
        if (result.assetId) {
            result.assetId = result.assetId.join(",")
        }
        return result;
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
        dblclick: function (table, data) {
            //在新建界面进行显示单行的详细信息
            data.pageModel = PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("TrdProjectBuyOffInterest002.look"),
                basePath + "invest/period/trdprojbuyoff/TrdProjectBuyOffInterest002/init",
                "40%", "55%", data, function () {
                });
        },
        cols: [
            {field: 'assetTypeString', sortField:'assetType', width: 200, title: '项目类型'}
            , {field: 'assetId', width: 200, title: '资产代码'}
            , {field: 'assetName', width: 200, title: '资产名称'}
            , {field: 'rate', width: 200, title: '买断利率（%）', type: 'number'}
            , {field: 'effDate', width: 200, sortable:false, title: '生效日期'}
            , {field: 'cashId', visible: false, width: 200}
            , {field: 'assetType', visible: false, width: 200}
        ],
        checkbox: true,
        progressBar: true
    });

    var exportExcel = function () {
        Joyin.exportExcel('export', getSearchParams());
    };

    var upload = function () {
        Joyin.confirm("导入时，同一资产同一生效日时，数据会覆盖原数据，是否继续", function () {
            Joyin.uploadFile(function (fileName) {
                Joyin.ajax(basePath + "invest/period/trdprojbuyoff/TrdProjectBuyOffInterest001/importFile", {
                    fileName: fileName
                }, function () {
                });
            });
        })
    }

    var deleteData = function () {
        var selectedData = table.getAllCheckedRows();
        if (!selectedData || selectedData.length == 0) {
            Joyin.error("请至少选择一条数据");
            return;
        }
        Joyin.confirm("确认删除选择的数据", function () {
            Joyin.ajaxJson(basePath + "invest/period/trdprojbuyoff/TrdProjectBuyOffInterest001/deleteData", {
                selectedData: selectedData
            }, function () {
                table.reload();
            });
        })
    }

    /**
     * 画面加载完成
     */
    $(function () {
        $("#TrdProjectBuyOffInterest001_btn_search").click(function () {
            table.load();
        })
        $("#TrdProjectBuyOffInterest001_btn_delete").click(function () {
            var rows = table.getAllCheckedRows();
        })
        $("#TrdProjectBuyOffInterest001_btn_add").click(function () {
            Joyin.dialog(JoyinUtil.getLabel("TrdProjectBuyOffInterest002.add"),
                basePath + "invest/period/trdprojbuyoff/TrdProjectBuyOffInterest002/init",
                "40%", "55%", {pageModel: PAGE_MODEL_NEW}, function () {
                });
        });

        /**
         * 导入按钮
         */
        $('#TrdProjectBuyOffInterest001_btn_import').click(upload);

        /**
         * 删除按钮
         */
        $('#TrdProjectBuyOffInterest001_btn_deleteData').click(deleteData);

        /**
         * 导出
         */
        $('#TrdProjectBuyOffInterest001_btn_export').click(exportExcel);
    });

    return {refreshTable: function () {
            table.reload();
        }}
}();

