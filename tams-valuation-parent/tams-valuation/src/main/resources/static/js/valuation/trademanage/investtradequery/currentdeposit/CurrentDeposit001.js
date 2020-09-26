$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            tradeId: $.trim($('#tradeId').val()), //交易编号
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
        searchOnInit:true,
        cols: [
           {field:'num', title: '序号', sort: true,rowno:true},
           {field:'effDate', title: '计提日', sort: true},
           {field:'basis', title: '计提基数', sort: true},
           {field:'yield', title: '利率', sort: true},
           {field:'intAmt', title: '利息金额', sort: true}
        ]
    });
    
    

    
    /**
     * 画面加载完成
     */
    $(function() {
    	var width = $(document).width();
    	$(".layui-field-title").width((width-50)+"px"); 
        table.reload();

    });
    
});



