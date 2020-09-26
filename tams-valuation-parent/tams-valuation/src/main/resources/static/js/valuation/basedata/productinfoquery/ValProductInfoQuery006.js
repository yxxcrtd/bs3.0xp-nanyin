$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	finprodId: s_finprodId,
        	cashType2: s_cashType2 
        }
    };
    
    /**
     * 定义数据表格
     * 母产品-明细查询表
     */
	var dataTable=$("#dataTable").joyintable({
	    ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        checkbox: false,
		cellMinWidth : 100,
		rowno: true,
		cols: [
           {field:'bankDesc', title: '关联资金账户', sort: true}
		],
	});
	
	
	
	
	
	/**
     * 画面加载完成
     */
    $(function() {
    	dataTable.load();
    });
   
	
});



