$(function() {
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var param = $.serializeToObject('#searchForm');
    	return param;
    };
    
    // 表格的列
    var tabCols = [
         {field:'assetCode',sortField:'assetCode', title: '资产代码', width:250}
		,{field:'assetName',sortField:'assetName', title: '资产名称', width:150}
		,{field:'assetTerm',sortField:'assetTerm', title: '期次', width:150}
		,{field:'vdate',sortField:'vdate', title: '起息日', width:150}
		,{field:'mdate',sortField:'mdate', title: '到期日', width:150}
		,{field:'assetAmt',sortField:'assetAmt', title: '融资金额', width:150}
		,{field:'assetRate',sortField:'assetRate', title: '预期收益率（%）', width:200}
    ];
    
	/**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({ 
    	ajax: {
            url: 'selectForPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
		checkbox: false,
		tree:true,
		treeId: 'childInfo',
	    treeUpId: 'parentInfo',
	    treeShowName: 'assetCode',
	    lazyload:true,
	    searchOnInit: false,
	    page:false,
	    loadonce:false,
	    //isMulti: true,
	    cols: tabCols,
	    done:function(){
            $(".laytable-cell-group").css({'text-align':'center'})
        }
	});
    
    /**
     * 导出
     */
    var exportExcel = function(){
        Joyin.exportExcel('export',getSearchParams());
    };

	$(function() {
        $('#ParentAssetManage001_btn_search').click(function() { 
            table.load();
        });
        $('#ParentAssetManage001_btn_export').click(function() {
        	exportExcel();
        });     
	});
})