$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var options=$("#repName option:selected");
        return {
        	repName :options.text(),
        	ruleName: $.trim($('#ruleName').val())
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
        minWidth:100,
		checkbox: true,
		cols: [ 
		        {field:'id', title: '报表编号',visible:false},
		        {field:'ruleName', title: '规则名称',sort: true},
		        {field:'repName', title: '报表名称',sort: true},
		        {field:'detail', title: '报表披露规则说明',sort: true},
		        {field:'basisDateTypeName', title: '基准日',sort: true,sortField:"basisDateType"},
		        {field:'defaultFileName', title: '默认文件名称',sort: true,sortField:"id"},
		        {field:'fileOutType', title: '默认输出格式',sort: true},
		        {field:'defaultFileType', title: '默认输出路径',sort: true,sortField:"id"},
		        {field:'operation',width:150, title: 'SysCommon.operation', type:'action', buttons: ['edit']}
                
		]
	});

	   /**
     * 导出
     */
    var exportExcel = function() {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {	     
        	var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };
    table.bind('click', '.edit', function(data, index) {
    	data.operateType = "edit";
    	Joyin.dialog(JoyinUtil.getLabel("编辑"),
                JoyinUtil.getRealPath("/disclosure/information/manage/workrule/ReportRule002/init"),
                "90%", "90%", {pageModel:PAGE_MODEL_EDIT,id:data.id},
                function(data){
                	table.reload();
                });
    });
    /**
     * 跳转页面2
     */
//    var redirectUrl2 = function(param){
//        Joyin.dialog(JoyinUtil.getLabel("编辑"),
//                JoyinUtil.getRealPath("/disclosure/information/manage/workrule/ReportRule002/init"),
//                "90%", "90%", {pageModel:PAGE_MODEL_EDIT,id:param},
//                function(data){
//                	table.reload();
//                });  
//    }
//    window.redirectUrl2 = redirectUrl2;
	/**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ReportRule001_btn_search').click(function() {
            table.load();
        });
        $('#ReportRule001_btn_export').click(function() {
        	exportExcel;
        });
        $('#ReportRule001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("新建"),
                    JoyinUtil.getRealPath("/disclosure/information/manage/workrule/ReportRule002/init"),
                    "80%", "90%", {pageModel:PAGE_MODEL_NEW},
                    function(data){
                    	table.reload();
                    }); 
        });
        
        /**
         * 删除
         */
        $('#ReportRule001_btn_delete').click(function() {
        	
        	var object = {};
        	var rowArr = table.getAllCheckedRows();// 所有选中行数据
        	if(rowArr == null || rowArr.length == 0){
        		layer.msg("请选择一条记录！");   		
        	}/*else if(rowArr != null && rowArr.length>1){
        		
    			layer.msg("请选择一条记录！");
    		}*/else{		
        	    object.selectedItem=rowArr;
    	    	object.operateType = "delete";
    		   	Joyin.ajaxJson(basePath + 'disclosure/information/manage/workrule/ReportRule001/check', object, function(result) {
    		           Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
	    		           	Joyin.ajaxJson(basePath + 'disclosure/information/manage/workrule/ReportRule002/delete', object, function(result) {
	    		           		table.reload();
	    		           	});
    		           });
    	       });
        	}
        });
        
        
        /**
         * 导出
         */
        $('#ReportRule001_btn_export').click(function() {
            exportExcel();
        });
       

		
    });     
	
});


