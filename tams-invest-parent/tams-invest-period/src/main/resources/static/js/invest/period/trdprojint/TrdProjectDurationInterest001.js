var TrdProjectBuyOffInterest001 = function () {

    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        var result = $.serializeToObject('#searchForm');
        if (result.assetType) {
            result.assetType = result.assetType.join(",")
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
            Joyin.dialog(JoyinUtil.getLabel("TrdProjectBuyOffInterest002.look"), basePath + "invest/period/trdprojint/TrdProjectDurationInterest002/init",
                "97%", "97%", data, function () {
                });
        },
        cols: [
            {field: 'assetTypeString', width: 200, title: '项目类型'}
            , {field: 'assetId', width: 200, title: '资产代码'}
            , {field: 'assetName', width: 200, title: '资产名称'}
            , {field: 'amount', width: 200, title: '借款金额', type: 'number'}
            , {field: 'tradeDate', width: 200, title: '成交日'}
            , {field: 'vDate', width: 200, title: '起息日'}
            , {field: 'mDate', width: 200, title: '到期日'}
            , {field: 'ccy', width: 200, title: '币种'}
            , {field: 'assetType', visible: false}
            , {field: 'payCycle', visible: false}
            , {field: 'finprodId', visible: false}
        ],
        checkbox: true,
        progressBar: true
    });

    var exportExcel = function () {
        Joyin.exportExcel('export', getSearchParams());
    };

    /**
     * 画面加载完成
     */
    $(function () {
        $("#TrdProjectDurationInterest001_btn_search").click(function () {
            table.load();
        })

        $("#TrdProjectDurationInterest001_btn_add").click(function () {
            var selectedData = table.getAllCheckedRows();
            if (!selectedData || selectedData.length == 0) {
                Joyin.error("请至少选择一条数据");
                return;
            }
            var data = selectedData[0];
            data.pageModel = PAGE_MODEL_NEW;
            Joyin.dialog('付息计划维护', basePath + "invest/period/trdprojint/TrdProjectDurationInterest002/init", "97%", "97%",
                data, function () {
                });
        })

        /**
         * 导出按钮
         */
        $('#TrdProjectDurationInterest001_btn_export').click(exportExcel);
    });
}();

