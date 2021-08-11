(function () {
    var exportExcel = function() {
        Joyin.exportExcel('export', {detailSeqNo:detailSeqNo, taskId:taskId, execStatus: execStatus});
    };

    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'findBatchExeTask',
            type: 'POST',
            data: {detailSeqNo: detailSeqNo, taskId:taskId, execStatus: execStatus}
        },
        checkbox: false,
        page: true,
        searchOnInit: true,
        done:function(data){
        },
        cols: [
            {field: 'finprodId',sortField:'FINPROD_ID', width: 160, title: '资产代码'}
            , {field: 'finprodName',sortField:'FINPROD_ID',width: 160, title: '资产名称'}
            , {
                field: 'execStatus',sortField:'EXEC_STATUS', width: 160, title: '执行状态', render: function (data, type, row, meta) {
                    if (data == 'UNR') {
                        return '未执行';
                    } else if (data == 'WARN') {
                        return '警告'
                    } else if (data == 'NOR') {
                        return '正常结束'
                    } else if (data == 'EXP') {
                        return '异常结束'
                    } else if (data == 'BRK') {
                        return '中断'
                    }
                    return '未执行';
                }
            }
            , {field: 'execDetail', sortable:false, title: '备注'}
        ]
    });

    var fn = function(){
        table.reload();
        setTimeout(fn, 30000);
    }

    var preMessageId = '';
    $(function () {
        setTimeout(function () {
            fn();
        }, 500);
        $("body").on("click", "#setting_btn_refreshStatus", function () {
            window.location.href = window.location.href;
        });
        $('#ModImpaireBatchTask002_btn_export').click(function() {
            exportExcel();
        });
        $("#ModImpaireBatchTask002_btn_filter").remove();
    })
}());
