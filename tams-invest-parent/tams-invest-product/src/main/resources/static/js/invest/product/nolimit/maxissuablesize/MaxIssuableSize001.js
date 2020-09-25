(function(){
	
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	
        return {
        	queryDate: $.trim($("#queryDate").val()),
            prdPlanSerisId: $.trim($("#prdPlanSerisId").val())
        }    
    };
    
    var tabCols = [
        {field:'prdPlanSerisName', title: 'MaxIssuableSize001.prdPlanSerisName', width:420}, //产品系列规划名称
        {field:'nonStandardLimit', title: 'MaxIssuableSize001.nonStandardLimit', width:420}, //非标资产比例上限
        {field:'maxIssuableSize', title: 'MaxIssuableSize001.maxIssuableSize', width:420, digit: 2, type:'number',sortable: false}, //当前最大可发行规模
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
        cols: tabCols,
    });
    
    /**
     * 查询日必填
     */
    var CheckAndLoadTable=function(){
    	if ( $("#queryDate").val()==null || $("#queryDate").val()=='' ){
    		Joyin.error(JoyinUtil.getMessage("COMMON_E_0009",JoyinUtil.getLabel("MaxIssuableSize001.queryDate")));
            return;
    	}
    	table.load(); 	
    }
    
    /**
     * 批量导出
     */
    var exportExcel = function () {
        Joyin.exportExcel('export', getSearchParams());
    };
    
    /**
     * 绑定按钮事件  未修改
     */
	$(function() {
        $('#MaxIssuableSize001_btn_search').click(function() { 
            CheckAndLoadTable();
        });
        $('#MaxIssuableSize001_btn_export').click(function() {
        	exportExcel();
        });   
	});
	
	
}());