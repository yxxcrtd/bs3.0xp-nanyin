(function(){
	
	/**
	 * 获取查询参数
	 */
    var getSearchParams = function() {
    	var pageParam = $.serializeToObject('#searchForm');
        
        return pageParam;
    };

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getSearchParams();//获取请求参数
            }//ajax请求数据
        },
        page:true,
        cols:[
            {field:'errCode', title: 'ErrorCodeInfoCompare001.errCode', width:"20%", sortField: 'ERR_CODE'}
            ,{field:'errMsg', title: 'ErrorCodeInfoCompare001.errMsg', width:"80%", sortField: 'ERR_MSG'}
        ],
        dblclick:function(table, data) {
            data.operateType = "dblclick";
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getMessage("ERRCODEINFO_DETAL"),
                    JoyinUtil.getRealPath("invest/bankuplink/basic/errorcodeinfocompare/ErrorCodeInfoCompare002/init"),
                    "70%", "50%", data,
                    function(){table.reload();});
        },
    });


    /**
     * 导出
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();//获取参数
        paramObj.exportFlag = true;
        Joyin.exportExcel('export', paramObj);//导出
    };

    /**
     * 画面加载完成
     */
    $(function() {
    	
        /** 绑定查询按钮 **/
        $('#ErrorCodeInfoCompare001_btn_search').click(function() {
        	table.load();//加载表格
        });

        /** 绑定导出按钮 **/
        $('#ErrorCodeInfoCompare001_btn_export').click(function() {
        	exportExcel();//导出
        });
        
    });


}());