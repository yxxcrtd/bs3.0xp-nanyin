$(function() {
  //跳转的页码
    var pageCount = 1;
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            //估值日
            valDate : $('#valDate').val()
        }
    };

    /**
     * 导出
     */
    var exportExcel = function() {
        var param={};
            param = getSearchParams();
            if (!param.valDate || param.valDate.length == 0) {
                Joyin.error(JoyinUtil.getMessage("请输入估值日期!"));
                return;
            }
            param.exportflag = 'export';
            Joyin.exportExcel("export",param);

    };
    $(function(){
    		//导出中债Excel
    	    $('#RskValBotDetailReport001_btn_export').click(function() {
    	    	debugger;
    	        exportExcel();
    	    });
       });
}());