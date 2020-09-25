/**
 * 总量/总比例维护
 */
(function () {
    /**
     * 获取查询参数
     */

    var getPageParams = function () {
        var pageParam = $.serializeToObject('#searchForm');
        pageParam.fromDate = $('#effDate').startDate();
        pageParam.toDate = $('#effDate').endDate();
        return pageParam;
    };

    /**
     * 组合信息查询
     */
    var table = $("#dataTable1").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function () {
                return getPageParams();
            }
        },
        // addbtn:true,
        height: 'full-' + 220,
        checkbox: false,
        // tree:true,
        // loadonce:true,
        cols: [
            {field: 'nonStandardLimit', width: 120, title: 'TotalMaintain001.nonStandardLimit', type: 'number'}
            , {field: 'nonStandardRotio', width: 120, title: 'TotalMaintain001.nonStandardRotio', type: 'number'}
            , {field: 'totalPlannedAssets', width: 120, title: 'TotalMaintain001.totalPlannedAssets', type: 'number'}
            , {field: 'effDate', width: 200, title: 'TotalMaintain001.effDate', type: 'date'}
            , {field: 'operation', width: 200, title: 'SysCommon.operation', type: 'action', buttons: ['edit', 'delt']}

        ]
    });
    table.bind('click', '.edit', function (data, index) {
        Joyin.dialog(JoyinUtil.getLabel("非标限额修改"),
            basePath + "invest/product/nolimit/totalMaintain/TotalMaintain002/init",
            "80%", "80%", {
                pageModel: PAGE_MODEL_EDIT,
                effDate: data.effDate,
                nonStandardLimit: data.nonStandardLimit,
                nonStandardRotio: data.nonStandardRotio
            });
    });
    table.bind('click', '.delt', function (data, index) {
        Joyin.confirm("是否删除该行数据", function () {
            WorkFlow.doDeleteList(basePath + "invest/product/nolimit/totalMaintain/TotalMaintain002/delete", data, "TotalMaintain001_btn_delete", "TotalMaintain001", function () {
                table.reload();
            })
        })
    });


    var showDig = function () {

    }
    /**
     * 导出
     */
    var exportExcel = function () {
        Joyin.exportExcel('export', getPageParams());
    };


    /**
     * 日期转字符串
     */
    function dateToString(obj) {
        var date = new Date(obj);
        var year = date.getFullYear();
        var month = (date.getMonth() + 1).toString();
        var day = (date.getDate()).toString();
        if (month.length === 1) {
            month = "0" + month;
        }
        if (day.length === 1) {
            day = "0" + day;
        }
        var dateTime = year + "-" + month + "-" + day;
        return dateTime;
    }

    $(function () {
        $('#TotalMaintain001_btn_new').click(function () {
            Joyin.dialog(JoyinUtil.getLabel("TotalMaintain002.new"),
                basePath + "invest/product/nolimit/totalMaintain/TotalMaintain002/init",
                "80%", "80%", {
                    pageModel: PAGE_MODEL_NEW
                });
        });
        $('#TotalMaintain001_btn_search').click(function () {
            table.load();
        })
        $('#TotalMaintain001_btn_export').click(function() {
        exportExcel();
    });
    });
}());