(function(){
	
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var param = $.serializeToObject('#searchForm');
    	return param;
    };
    
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
        
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("TacticConfig001.reference"),
                    JoyinUtil.getRealPath("invest/product/productplanning/tacticconfig/TacticConfig002/init"),
                    "98%", "95%",data,function(){table.reload();}
            );
        },

		cols: [
	    	 {field:'tacticConfigCode',title: 'TacticConfig001.tacticConfigCode',width:'10%', sortField:'tacticConfigCode' }
	        ,{field:'vdateStr', title: 'TacticConfig001.vdate', width:'10%', sortField:'vdate'}
	        ,{field:'edateStr', title: 'TacticConfig001.edate', width:'10%',sortField:'edate'}
	        ,{field:'assetTypeStr', title: 'TacticConfig001.assetType', width:'10%',sortField:'assetType'}
	        ,{field:'theoConfigRateStr', title: 'TacticConfig001.theoConfigRate', width:'10%',sortField:'theoConfigRate',type:'number',digit:6}
			,{field:'samleAverRateStr', title: 'TacticConfig001.samleAverRate', width:'10%',sortField:'samleAverRate',type:'number',digit:6}
			,{field:'vixStr', title: 'TacticConfig001.vix', width:'10%',sortField:'vix',type:'number',digit:6}
			,{field:'corRateStr', title: 'TacticConfig001.corRate', width:'10%',sortField:'corRate'}
			,{field:'corSceneStr', title: 'TacticConfig001.corScene', width:'10%',sortField:'corScene'}
			,{field:'corRatioStr', title: 'TacticConfig001.corRatio', width:'10%',sortField:'corRatio'}
			,{field:'corView', title: 'TacticConfig001.corView', width:'10%',sortField:'corView'}
			,{field:'remark', title: 'TacticConfig001.remark', width:'10%',sortField:'remark'}
	        ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
	    ]
	});
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = 'edit';
        Joyin.ajaxJson(basePath + 'invest/product/productplanning/tacticconfig/TacticConfig001/check', data, function(result) {
	        Joyin.dialog(JoyinUtil.getLabel("TacticConfig001.edit"),
	              basePath + "invest/product/productplanning/tacticconfig/TacticConfig002/init",
	              "98%", "95%", data ,function(){table.reload();});
        });
            
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
    	data.operateType = 'delete';
        Joyin.ajaxJson(basePath + 'invest/product/productplanning/tacticconfig/TacticConfig001/check', data, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("TacticConfig001.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/product/productplanning/tacticconfig/TacticConfig001/delete', data, "TacticConfig001_btn_delete", "TacticConfig002");
            });
        });
    });
	
    /**
     * 新建
     */
    var openNew = function(){
        Joyin.dialog(JoyinUtil.getLabel("TacticConfig002"),
                JoyinUtil.getRealPath("invest/product/productplanning/tacticconfig/TacticConfig002/init"),
                "98%", "95%",{pageModel : PAGE_MODEL_NEW},function(){table.reload();}
        );
    }
    
    /**
     * 导出
     */
    var exportExcel = function(){
        Joyin.exportExcel('export',getSearchParams());
    };
    
	$(function(){
		//新建
		$('#TacticConfig001_btn_add').click(function() {
        	openNew();
        });
		//查询
		$('#TacticConfig001_btn_search').click(function() { 
            table.load();
        });
		//导出
        $('#TacticConfig001_btn_export').click(function() {
        	exportExcel();
        });
	});
}());