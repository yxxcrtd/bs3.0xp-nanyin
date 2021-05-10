/**
 * 债券投资委托查询
 */
(function () {
    /**
     * 获取查询参数
     */
    var searchform = function () {
        var object = {
            finprodId: $('#finprodId').val(),
            lossDate: $("#lossDate").val(),
            lossTestType: $("#lossTestType").val(),
            lossRatio: $("#lossRatio").val(),
            pdValue: $("#pdValue").val(),
            lgdValue: $("#lgdValue").val()
        };
        return object;
    };


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
            /*data.operateType = "dblclick";
            data.pageModel = PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("TrdAssetLoss003.refrence"),
                JoyinUtil.getRealPath("valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss003/init"),
                "98%", "95%", data,
                function () {
                    table.reload();
                });*/
        },
        cols: [
            {field: 'bookSetId', title: '账套代码', width: '10%', sortField: "bookSetId"}
            , {field: 'bookSetName', title: '账套名称', width: '10%', sortField: "bookSetName"}
            , {field: 'invAim', title: '投资目的', width: '10%', sortField: "invAim"}
            , {field: 'bookValue', title: '账面价值', width: '10%', sortField: "bookValue"}
            , {field: 'interestReceivable', title: '应收利息', width: '10%', sortField: "interestReceivable"}
            , {field: 'eadDefaultExposure', title: 'EAD违约风险暴露', width: '10%', sortField: "eadDefaultExposure"}
            , {field: 'thisLossValue', title: '本次减值损失', width: '10%', sortField: "thisLossValue"}
            , {field: 'lossRatio', title: '减值比例%', width: '10%', sortField: "lossRatio"}
            , {field: 'tradeId', title: '交易编号', width: '10%', sortField: "tradeId", visible: false}
        ],
    });

    $(function () {
        table.load();
    });

}());