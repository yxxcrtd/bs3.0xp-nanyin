/**
 * 存续产品风险等级评定
 */
(function () {
    /**
     * 获取查询参数
     */

    var getPageParams = function () {
        var pageParam = $.serializeToObject('#searchForm');
        pageParam.startDate = $('#generateTime').startDate();
        pageParam.endDate = $('#generateTime').endDate();
        return pageParam;
    };

    /**
     * 组合信息查询
     */
    var table = $("#dataTable").joyintable({
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
            {field: 'generateTime', width: 120, title: 'RiskLevelRating001.generateTime', type: 'datetime'}
            , {field: 'finProductCode', width: 120, title: 'RiskLevelRating001.finProductCode', type: 'text'}
            , {field: 'finprodAbbr', width: 120, title: 'RiskLevelRating001.finprodAbbr', type: 'text'}
            , {field: 'riskLevelStr', width: 200, title: 'RiskLevelRating001.riskLevel', type: 'text',sortField:'riskLevel'}
            , {field: 'ajustRiskLevelStr', width: 200, title: 'RiskLevelRating001.ajustRiskLevel1', type: 'text',sortField:'ajustRiskLevel'}
            , {field: 'seqNo', width: 200, type: 'text',visible: false}
            , {field: 'operation', width: 200, title: 'SysCommon.operation', type: 'action', buttons: ['edit', 'delt']}

        ]
    });
    table.bind('click', '.edit', function (data, index) {
        Joyin.dialog(JoyinUtil.getLabel("产品风险等级评定修改"),
            basePath + "invest/product/riskLevelRating/RiskLevelRating002/init",
            "90%", "90%", {
                pageModel: PAGE_MODEL_EDIT,
                finProductCode: data.finProductCode,
                riskLevel: data.riskLevel,
                ajustRiskLevel: data.ajustRiskLevel,
                seqNo:data.seqNo,
                finprodAbbr:data.finprodAbbr,
                generateTime:formatDate(data.generateTime)
            });
    });
    table.bind('click', '.delt', function (data, index) {
        Joyin.confirm("是否删除（"+data.finProductCode+"）产品评定日期为"+formatDate(data.generateTime)+"的数据", function () {
            WorkFlow.doDeleteList(basePath + "invest/product/riskLevelRating/RiskLevelRating002/delete", data, "RiskLevelRating002_btn_delete", "RiskLevelRating002", function () {
                table.reload();
            })
        })
    });

    function formatDate(inputTime) {
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
    }

    var showDig = function () {

    };
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
        $('#RiskLevelRating001_btn_new').click(function () {
            Joyin.dialog(JoyinUtil.getLabel("RiskLevelRating001.new"),
                basePath + "invest/product/riskLevelRating/RiskLevelRating002/init",
                "90%", "90%", {
                    pageModel: PAGE_MODEL_NEW
                });
        });
        $('#RiskLevelRating001_btn_search').click(function () {
            table.load();
        });
        $('#RiskLevelRating001_btn_export').click(function() {
        exportExcel();
    });
    });
}());