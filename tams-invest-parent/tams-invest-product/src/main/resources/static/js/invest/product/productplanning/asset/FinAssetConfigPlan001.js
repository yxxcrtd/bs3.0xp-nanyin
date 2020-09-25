(function(){
	
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var param = {};
    	if ($('#FinAssetConfigPlan001_assetType').val()){
    		param.assetType = $('#FinAssetConfigPlan001_assetType').val().join(',');
    	}
    	param.benchDate = $("#FinAssetConfigPlan001_benchDate").val();
    	param.genDate = $('#FinAssetConfigPlan001_genDate').val();
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
            Joyin.dialog(JoyinUtil.getLabel("FinAssetConfigPlan002"),
                    JoyinUtil.getRealPath("invest/product/productplanning/asset/FinAssetConfigPlan002/init"),
                    "90%", "90%",data,function(){table.reload();}
            );
        },
        //rowno: true,
        cols:[
            {field:'assetConfigCode', visible:false}
             ,{field:'tagetScale', title: 'FinAssetConfigPlan001.tagetScale', width:120}
 			,{field:'benchDate', title: 'FinAssetConfigPlan001.benchDate', type:'date',width:100}
			,{field:'genDate', title: 'FinAssetConfigPlan001.genDate', type:'date',width:100}
            ,{field:'configMonth', title: 'FinAssetConfigPlan001.configMonth' ,type:'date', visible:false}
             ,{field:'configMonthStr', title: 'FinAssetConfigPlan001.configMonth' ,width:100}
 			,{field:'assetTypeStr',sortField:'assetType', title: 'FinAssetConfigPlan001.assetType', width:140}//调整类型
            ,{field:'assetType', title: 'FinAssetConfigPlan001.configMonth' ,visible:false}
            ,{field:'assetMonth', visible:false}
			,{field:'totalRatio', title: 'FinAssetConfigPlan001.totalRatio', type:'number', digit:2, visible:false}
			,{field:'totalRatioStr', title: 'FinAssetConfigPlan001.totalRatio', width:80, sortable:false}
			,{field:'totalAmt', title: 'FinAssetConfigPlan001.totalAmt', type:'number', digit:2, width:120}
			,{field:'curPerAmt', title: 'FinAssetConfigPlan001.curPerAmt', type:'number', digit:2, width:120}
			,{field:'curEndAmt', title: 'FinAssetConfigPlan001.curEndAmt', type:'number', width:120, sortable:false, digit:2, visible:true}
            ,{field:'curInvAmt', title: 'FinAssetConfigPlan001.curInvAmt', type:'number', width:120, sortable:false, digit:2, visible:true}			
            ,{field:'operation', width:280,title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
			
        ]
    });
    
    /**
     * 导出
     */
    var exportExcel = function(){
        Joyin.exportExcel('export',getSearchParams());
    };
    
    var openNew = function(){
        Joyin.dialog(JoyinUtil.getLabel("FinAssetConfigPlan002.new"),
                JoyinUtil.getRealPath("invest/product/productplanning/asset/FinAssetConfigPlan002/init"),
                "75%", "75%",{pageModel : PAGE_MODEL_NEW},function(){table.table ? table.reload() : table.load();}
        );
    }
        
    $(function() {
    
        $('#FinAssetConfigPlan001_btn_search').click(function() { 
            table.load();
        });
        $('#FinAssetConfigPlan001_btn_export').click(function() {
        	exportExcel();
        });
        $('#FinAssetConfigPlan001_btn_add').click(function() {
        	openNew();
        });
        
        /**
         * 绑定编辑事件
         */
        table.bind('click', '.edit', function(data, index) {
            data.pageModel = PAGE_MODEL_EDIT;
            data.operateType = 'edit';
            Joyin.ajaxJson(basePath + 'invest/product/productplanning/asset/FinAssetConfigPlan001/check', data, function(result) {
                Joyin.dialog(JoyinUtil.getLabel("FinAssetConfigPlan002.edit"),
                        basePath + "invest/product/productplanning/asset/FinAssetConfigPlan002/init",
                        "98%", "95%", {assetConfigCode : data.assetConfigCode,assetType : data.assetType,configMonth : data.configMonth,
                	pageModel : PAGE_MODEL_EDIT},function(){table.reload();});
                
            });
        });
        
        table.bind('click', '.delt', function(data, index) {
            var object = {selectedItem: [data], operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/product/productplanning/asset/FinAssetConfigPlan001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("FinAssetConfigPlan001_001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/product/productplanning/asset/FinAssetConfigPlan001/delete', object, 
                            "FinAssetConfigPlan001_btn_delete", "FinAssetConfigPlan002",function() {
                        table.reload();
                    });
                });
            });
        });
        
	});
}());