$(function () {
    // 获取查询参数
    var getSearchParams = function () {
        return {
            reportDate: $('#month_reportDate').val()
        };
    }
    // 定义数据表格
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'searchPage',
            type: 'POST',
            data: function () {
                return getSearchParams();
            }
        },
        showDetail: false,//双击详情对比画面
        rowno: true, // 显示行号
        searchOnInit: false, //初始化的时候就请求后台数据
        cols: [
            {field: 'registSeqno', visible: false},
            // { title: "序号", field: 'statusStr',width:150,sortField:'status'},
            {title: "报告期", field: 'reportDate', width: 150, sortField: 'reportDate'},
            {
                title: '操作', field: 'operation', type: 'action', width: 150,
                buttons: [{text: '&#xe601;', clas: 'download', title: '导出'}]
            },
            {field: 'sendStatus', visible: false},
            {field: 'status', visible: false},
            {field: 'submitFeedbackStatus', visible: false}
        ]
    })

    table.bind('click', '.download', function (data, index) {
        var arrayObj = [];
        arrayObj.push(data);
        var datas = JSON.stringify(arrayObj);
        Joyin.exportExcel('export', {
            datas: datas
        });
    });

    // 查询按钮
    $('#HXProfitQuery001_btn_search').click(function () {
        table.load();
    })

    /**
     * 导出
     */
    var exportExcel = function () {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var allArr = table.getAllRows();
            if (!allArr || allArr.length == 0) {
                table.load();
                var searchObj = getSearchParams();
                var tableDate = $.ajax({url: "searchPage", type: 'POST', async: false, data: searchObj});
                var tableJson = JSON.parse(tableDate.responseText);
                if (tableJson.records == 0) {
                    Joyin.alert("无数据，无法导出!", 2);
                } else {
                    Joyin.exportExcel('export', {datas: JSON.stringify(tableJson.root)});
                }
            } else {
                if (!selArr || selArr.length == 0) {
                    var datas = JSON.stringify(allArr);
                } else {
                    var datas = JSON.stringify(selArr);
                }
                Joyin.exportExcel('export', {
                    datas: datas
                });
            }
        }
    };

    /**
     * 获取下个月份
     * @param addMonths
     * @returns {string}
     */
    function getNextMonth(addMonths) {
        var dd = new Date(Date.parse(platDate));
        var m = dd.getMonth() + 1;
        var y = dd.getMonth() + 1 + addMonths > 12 ? (dd.getFullYear() + 1) : dd.getFullYear();
        if (m + addMonths == 0){
            y = y - 1;
            m = 12;
        }else {
            if (m + addMonths > 12){
                m = '01';
            }else {
                m = m + 1 < 10 ? '0' + (m + addMonths) : (m + addMonths);
            }
        }
        return y + "-" + m;
    }

    function initReportDate(){
        $("#month_reportDate").val(getNextMonth(-1));
    }

    function init() {
        layui.laydate.render({
            elem: '#month_reportDate',
            type: 'month',
            format: 'yyyy-MM'
        });
        $("#month_reportDate").click(function (event) {
            event.stopPropagation();// 阻止事件冒泡
        });
        initReportDate();
        // 绑定按钮事件
        $('#SvrHXProfitQuery001_btn_search').click(function () {
            table.load();
        });
        $('#SvrHXProfitQuery001_btn_export').click(function () {
            exportExcel();
        });
    }

    init();

})