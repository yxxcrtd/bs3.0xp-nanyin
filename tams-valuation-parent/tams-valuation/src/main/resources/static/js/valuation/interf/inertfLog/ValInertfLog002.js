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
            cols : [  
            {field : 'exDate',title : '日期',sort : true}, 
            {field : 'fileName', title : '接口文件名称',sort : true}, 
            {field : 'num',title : '执行状态', sort : true },
            {field : 'detail',title : '执行结果', sort : true},
            {field : 'remark', title : '操作日期',sort : true },
            ]
        });
        /**
         * 筛选
         */
        $('#ValInertfLog001_btn_search').click(function() {
            table.load();
        });
        
        
});