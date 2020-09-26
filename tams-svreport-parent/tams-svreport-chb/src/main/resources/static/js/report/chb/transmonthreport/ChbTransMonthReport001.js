$(function() {
  //跳转的页码
    var pageCount = 1;
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            //结束日（统计日期）
            enddate : $('#enddate').val(),
            chbTransOrg : $('#chbTransOrg').val()
        }
    };
    
    var laydate = layui.laydate;
    laydate.render({
		elem: '#enddate',
		type: 'month',
		format: 'yyyy-MM'
			
	});


    /**
     * 导出
     */
    var exportExcel = function(flag) {
        var param={};
            param = getSearchParams();
            param.flag = flag;
            if (!param.enddate || param.enddate.length == 0) {
               // Joyin.error(JoyinUtil.getMessage("REPORT_COMMON_E_0006",JoyinUtil.getLabel("enddate")));
                Joyin.error(JoyinUtil.getMessage("请输入统计年月!"));
                return;
            }
            if(AUTHORITY_TYPE=='02'){
            	if(!param.chbTransOrg || param.chbTransOrg.length == 0){
            		Joyin.error(JoyinUtil.getMessage("请选择所属机构!"));
                    return;
            	}
            }
            param.exportflag = 'export';
            Joyin.exportExcel("export",param);
        
    };
    $(function(){
    		//导出中债Excel
    	    $('#ChbTransMonthReport001_btn_chbexport').click(function() {
    	    	debugger;
    	        exportExcel("0");
    	    });

       });
}());