$(function () {
    // 获取查询参数
    var getSearchParams = function () {
        return {
            prodId: $('#prodId').val() == null ? null : $('#prodId').val().join(","),
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
            {title: "产品代码", field: 'prodId', width: 150, sortField: 'prodId'},
            {title: "产品名称", field: 'prodName', width: 150, sortField: 'prodName'},
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
    $('#FinAssetLiabilityStatementQuery001_btn_search').click(function () {
        table.load();
    })

    /**
     * 导出
     * flag:0 全部导出，选中导出
     */
    var exportExcel = function () {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas: datas
            });
        }
    };

    function init() {
        layui.laydate.render({
            elem: '#month_reportDate',
            type: 'month',
            format: 'yyyy-MM'
        });
        $("#month_reportDate").click(function (event) {
            event.stopPropagation();// 阻止事件冒泡
        });
        // 绑定按钮事件
        $('#SvrFinAssetLiabilityStatementQuery001_btn_search').click(function () {
            table.load();
        });
        $('#SvrFinAssetLiabilityStatementQuery001_btn_export').click(function () {
            exportExcel();
        });
    }

    init();

})