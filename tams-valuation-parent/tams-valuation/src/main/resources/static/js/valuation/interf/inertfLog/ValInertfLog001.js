$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        
        return {
           tmpName : $('#ValInertfLog001_tmp').val(),
           fileName : $('#ValInertfLog001_name').val(),
           exDate:$("#ValInertfLog001_exdate").val(),
           check:$("#ValInertfLog001_check").val()
        }
    };
    
    
    /**
     * 表格加载
     */
        var table = $("#dataTable").joyintable({
            ajax : {
                url : 'initTable',
                type : 'POST',
                data : function() {
                    return getSearchParams();
                }
            },
//            searchOnInit : true,
//            cellMinWidth : 100,
//            loadonce : true,
            cols : [ {field : 'tmpName', fixed : true, title : '模板名称',sort : true}, 
            {field : 'fileName', title : '文件名称',sort : true}, 
            {field : 'exDate',title : '文件日期',sort : true}, 
            {field : 'num',title : '明细记录编号', sort : true },
            {field : 'isCheckName', title : '是否通过', sortField:'isCheck', sort : true },  
            {field : 'detail',title : '记录内容', sort : true},
            {field : 'remark', title : '备注',sort : true },
            ]
        });
        /**
         * 筛选
         */
        $('#ValInertfLog001_btn_search').click(function() {
            table.load();
        });
        
        
});