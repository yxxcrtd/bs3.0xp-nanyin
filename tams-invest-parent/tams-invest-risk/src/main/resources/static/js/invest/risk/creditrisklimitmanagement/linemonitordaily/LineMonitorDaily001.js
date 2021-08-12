(function() {
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
	   var result = $.serializeToObject('#searchForm');
	   if ($("#searchDate").val()) {
		   result.searchDate = $("#searchDate").val();
	   }

       return result;
    };

    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
    	if($("#dailyType").val() == null || $("#dailyType").val() == ""){
    		Joyin.alert("请选择导出类型");
    		return;
    	}
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };

    /**
     * 画面加载完成
     */
    $(function() {

        $('#LineMonitorDaily001_btn_export').click(function() {
            exportExcel();
        });

    });
}());