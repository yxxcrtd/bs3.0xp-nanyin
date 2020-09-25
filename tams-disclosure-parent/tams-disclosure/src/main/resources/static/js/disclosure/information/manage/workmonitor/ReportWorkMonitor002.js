$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            jobId: $.trim($('#jobId').val()),
            execStatus: $.trim($('#execStatus').val()),
            execResult: $.trim($('#execResult').val()),
            prodId: $.trim($('#prodId').val()),
            valDateBegin: $('#execDate').startDate(),
            valDateEnd: $('#execDate').endDate(),
            repName: $.trim($('#repName').val()),
            repType: $.trim($('#repType').val()),
            revealEndDateBegin: $('#revealEndDate').startDate(),
            revealEndDateEnd: $('#revealEndDate').endDate(),
            confromPage: $.trim($('#confromPage').val())
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
		        {field:'prodid', title: '产品代码',width:120,sort: true},
		        {field:'prodName', title: '产品名称',width:120,sort: true},
		        {field:'repTypeName', title: '报表类别',width:120,sort: true,sortField:"repType"},
		        {field:'repName', title: '报表名称',width:120,sort: true},
		        {field:'reportDate', title: '报告期',width:170,sort: true},
		        {field:'revealEndDateStr', title: '披露截止日',width:120,sort: true,sortField:"revealEndDate"},
		        /*{field:'execStatusName', title: '执行状态',width:120,sort: true,sortField:"execStatus"},*/
		        {field:'startExecDate', title: '开始执行时间',width:120,type:"datetime",sort: true},
		        {field:'endExecDate', title: '结束执行时间',width:120,type:"datetime",sort: true},
		        {field:'execResult', title: '执行结果',width:120,sort: true},
		        {field:'execResultExplain', title: '执行结果说明',width:120,sortable: false},
		        {field:'jobName', title: '作业名称',width:120,sort: true}
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
    	var execDateHidden = $('#execDateHidden').val();
//    	var revealEndDateHidden = $('#revealEndDateHidden').val();
    	var searchDateStart = $('#searchDateStart').val();
    	var searchDateEnd = $('#searchDateEnd').val();
    	if($('#confromPage').val() == '01'){
    		if(searchDateStart && searchDateEnd) {
    			$('#revealEndDate').val(searchDateStart + ' - ' + searchDateEnd);
    		}
    	}
    	if(execDateHidden && execDateHidden != "undefined") {
    		$('#execDate').val(execDateHidden + ' - ' + execDateHidden);
    	}else {
    		$('#execDate').val(null);
    	}
        // 绑定按钮事件
        $('#ReportWorkMonitor002_btn_search').click(function() {
        	table.load();
        });
        $('#ReportWorkMonitor002_btn_export').click(function() {
            exportExcel();
        });
        $("#ReportWorkMonitor002_btn_reset").hide();
        table.load();
    });
    
});
