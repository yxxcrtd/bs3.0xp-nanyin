(function () {
	
	/**
     * 获取查询参数
     */
    var getSearchParams = function () {
        return {
        	batchExedetailSeqNo: $('#batchExedetailSeqNo').val(),
        	taskId: $('#taskId').val()
        }
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
        cols: [
              {field: 'seqNo', visible: false}
            , {field: 'batchExeSeqNo', visible: false}
            , {field: 'taskId', visible: false}
            , {field: 'booksetId', width: 150, title: '账套代码'}
            , {field: 'happenDate', width: 100, title: '发生日期'}
            , {field: 'booksetDate', width: 100, title: '账套日期'}
            , {field: 'bookDate', width: 100, title: '入账日期'}
            , {field: 'finprodTypeFmt',sortField: "finprodId", width: 150, title: '金融产品类型'}
            , {field: 'marketCode', width: 150, title: '市场代码'}
            , {field: 'invAimFmt',sortField: "invAim", width: 200, title: ' 投资目的'}
            , {field: 'valueSourceFmt',sortField: "valueSource", width: 100, title: '估值来源'}
            , {field: 'theValueDate', width: 150, title: '理论行情日期'}
            , {field: 'actValueDate', width: 150, title: '实际行情日期'}
            , {field: 'actMarketPrice', width: 150, title: '实际行情价格'}
        ],
        checkbox:false,
    });
    
    var exportExcel = function () {
        Joyin.exportExcel('export',getSearchParams());
    };
    
    $(function(){  
            table.load();
            
            $("body").on("click", "#setting_btn_refreshStatus", function () {
                window.location.href = window.location.href;
            });

            $('#ValBatchLog002_btn_export').click(function () {
                exportExcel();
            });
            
            $("#ValBatchLog002_btn_filter").remove();
    });
}());