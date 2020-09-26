$(function() {
	 /**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var handleFileType = $('#handleFileType').val();
    	if (handleFileType) {
    		handleFileType = handleFileType.join();
    	}
    	var fileHandleStatus = $('#fileHandleStatus').val();
    	if (fileHandleStatus) {
    		fileHandleStatus = fileHandleStatus.join();
    	}
    	return{
            //数据文件名
    		handleFileType: handleFileType,
    		//文件处理状态
    		fileHandleStatus:fileHandleStatus,
            //查询日期 开始
    		startDate:$('#searchDate').startDate(),
    		//查询日期结束
    		endDate:$('#searchDate').endDate()
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
        cols:[
             /*{field:'handleSeqno', width:150, title: '文件处理序列号'}
            ,*/{field:'handleFileName', width:140, title: '数据文件名'}
            ,{field:'handleFilePath', width:150, title: '数据文件路径'}
            ,{field:'handleFileType', width:130, title: '数据文件类别'}
            ,{field:'fileReadStartTime', width:180, title: '开始时间',type: 'datetime'}
            ,{field:'fileReadEndTime', width:180, title: '结束时间',type: 'datetime'}
            ,{field:'fileDataCount', width:120, title: '文件数据条数'}
            ,{field:'fileHandleStatus', width:120, title: '文件处理状态'}
            ,{field:'fileHandleResultMsg', width:200, title: '文件处理结果说明', sortable:false}
            ,{field:'errorFileSeqNo' ,title:'错误文件',render:function (data, type, row, meta){
                if(data == ''|| typeof data == "undefined") return "无错误文件";
                else return '<a href="javascript:;" class="download">下载</a>'
            }}
        ]
        ,done: function() {
            $("#dataTable").next('.layui-table-view').find(':checkbox').prop('disabled', false);
        }
    });

    table.bind('click', '.download',function (data,index) {
        var errorFileSeqNo = data.errorFileSeqNo;
        if(!errorFileSeqNo || errorFileSeqNo == null) {
            Joyin.error("没有错误文件,不能下载!");
            return;
        }
        param = {errorFileSeqNo:errorFileSeqNo};
        Joyin.exportExcel('export',param);

    });
    
    function init(){
        if(isFromMain=='01'){
            setTimeout(function() {
                $('#searchDate').val(searchDataStart+' - '+searchDataEnd);
                table.load();
            }, 500);
        }
    };
    init();
    
    $(function() {
        // 绑定按钮事件
        $('#ReportUpInterfMonitor001_btn_search').click(function() {
            table.load();
        });
    });

});