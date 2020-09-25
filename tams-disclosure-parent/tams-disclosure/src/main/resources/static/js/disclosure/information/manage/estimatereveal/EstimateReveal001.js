$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	prodId: $.trim($('#prodId').val()),
        	repName: $.trim($('#repName').val()),
        	revealEndDateBegin: $('#revealEndDate').startDate(),
            revealEndDateEnd: $('#revealEndDate').endDate(),
            jobId: $.trim($('#jobId').val()),
            repType: $.trim($('#repType').val())
        }
    };
    
    /**
     * 定义数据表格
     */
	var table=$("#dataTable").joyintable({
	    ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        minWidth:100,
		checkbox: true,
		cols: [ 
		        {field:'prodid', title: '产品代码',sort: true},
		        {field:'prodName', title: '产品名称',sort: true},
		        {field:'repTypeName', title: '报表类别',sort: true,sortField:"repType"},
		        {field:'repName', title: '报表名称',sort: true},
		        {field:'reportDate', title: '报告期',sort: true},
		        {field:'revealEndDateStr', title: '披露截止日',sort: true,sortField:"revealEndDate"},
		        {field:'jobName', title: '作业名称',sort: true},
		]
	});

	var exportExcel = function() {
		if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };
    
	/**
     * 画面加载完成
     */
    $(function() {
    	var revealEndDateHidden = $('#revealEndDateHidden').val();
    	if($('#confromPage').val() == '01'){
    		$('#revealEndDate').val(revealEndDateHidden + ' - ' + revealEndDateHidden);
    		table.load();
    	}
        // 绑定按钮事件
        $('#EstimateReveal001_btn_search').click(function() {
            table.load();
        });
        $('#EstimateReveal001_btn_export').click(function() {
            exportExcel();
        });
    });
});