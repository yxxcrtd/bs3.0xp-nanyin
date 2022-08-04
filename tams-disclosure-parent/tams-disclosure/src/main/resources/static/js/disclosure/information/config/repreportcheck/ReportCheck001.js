(function () {
    /**
     * 获取查询参数
     */
    // var getSearchParams = function () {
    //     var data = $.serializeToObject('#searchForm');
    //     return data;
    // };

    var getSearchParams = function() {
         return {
             checkDateStart:$('#checkDate').startDate(),
             checkDateEnd:$('#checkDate').endDate(),
             prodId:$('#prodId').val() == null ? null : $('#prodId').val().join(","),
             reportType:$('#reportType').val() == null ? null : $('#reportType').val().join(",")
         };
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

        /**
         * 定义表头
         */
        cols: [
            {field:'reportCheckId',  visible: false,width:80}
            ,{field:'reportTypeName',  title: '报表类别 ',sortField:"reportType",width:200,sortable:false}
            ,{field:'reportName',  title: '报表名称',sortField:"reportName",width:200,sortable:false}
            , {field:'prodName',  title: '产品名称',sortField:"prodName",width:300,sortable:false}
            , {field:'prodId',  title: '产品代码',sortField:"prodId",width:200,sortable:false}
            ,{field:'checkDate',  title: '检查日期',sortField:"checkDate",type:'date',width:120,sortable:false}
            ,{field:'errormsg',  title: '错误信息',sortField:"errormsg",width:400,sortable:false}
            ,{
                title : '操作',
                field : 'operation',
                type : 'action',
                width : 120,
                buttons : [
                    {
                        text : '&#xe615;',
                        clas : 'batch',
                        title : '预览'
                    } ]
            },
        ]
    });


    /**
     * 导出选中的表格数据
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();//请求参数json格式
        Joyin.exportExcel('export', paramObj);//导出（共通）
    };

    /**
     * 画面加载完成
     */
    $(function () {
        //导出
        $('#ReportCheck001_btn_export').click(function () {
            exportExcel();
        });
        //初始化查询
        $('#ReportCheck001_btn_search').click(function () {
            table.load();
            //$('#ReportCheck001_btn_delete').show();
        });

    });
}());
