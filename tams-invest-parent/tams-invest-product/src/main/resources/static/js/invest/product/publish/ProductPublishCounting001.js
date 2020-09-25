/**
 * 产品发行规划试算
 */
(function() {
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
    	var param = {
    			prdIssuePlanCode : $("#prdIssueplanCode").val(),
    			vdateStr : $("#vdateStr").val(),
    			edateStr : $("#edateStr").val()
    	}
    	return param;
    };
    var getAssetParams = function() {
    	var param = {
    			prdIssuePlanCode : $("#astIssueplanCode").val(),
    			vdateStr : $("#astVdateStr").val(),
    			edateStr : $("#astEdateStr").val()
    	}
    	return param;
    };
    /**
     * 定义产品系列数据表格
     */
    var prdTable = $("#productCountTable").joyintable({
        ajax: {
        	url: 'findPrdPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        tree:true,
        treeId: 'childInfo',
        treeUpId: 'parentInfo',
        treeShowName: 'prdIssueplanName',
        lazyload:false,
        cols:[
        	// {field:'status', width:120, title: '有效状态'}
            {field:'prdIssueplanCode', width:140, title: '规划编号'}
            ,{field:'prdIssueplanName', width:140, title: '规划名称'}
            ,{field:'vdateStr', width:140, title: '规划起始日'}
            ,{field:'edateStr', width:140, title: '规划结束日'}
            ,{field:'prdPlanSerisId', width:140, title: '产品系列'}
            ,{field:'planTargetAmtStr', width:140, title: '规划目标值(万元)',type: 'money'}
            ,{field:'planChangeAmtStr', width:140, title: '剩余规划变动值（万元）',type: 'money'}
            ,{field:'strategyId', width:140, title: '已配置策略'}
            ,{field:'configScaleStr', width:140, title: '已配置规模',type: 'money'}
            //BSTOT-328 guyunfei 20200108 start
            ,{field:'assetTypeStr', width:140, title: '资产大类',sortable: false}
            //BSTOT-328 guyunfei 20200108 end
            ,{field:'assetConfigScaleStr', width:140, title: '资产大类配置规模',type: 'money'}
            //dgf 20200110 start
            ,{field:'remark', title: '备注',sortable: false}
            //dgf 20200110 end
            ,{field:'hasChildren', title: '是否有子节点',visible:false }
        ]
    });
    
    
    /**
     * 资产大类数据表格
     */
    var assetTable = $("#assetTable").joyintable({
        ajax: {
        	url: 'findAssetPage',
            type: 'POST',
            data: function() {
                return getAssetParams();
            }
        },
        tree:true,
        treeId: 'childInfo',
        treeUpId: 'parentInfo',
        treeShowName: 'prdIssueplanName',
        lazyload:false,
        cols:[
            // {field:'status', width:120, title: '有效状态'}
            {field:'prdIssueplanCode', width:140, title: '规划编号'}
            ,{field:'prdIssueplanName', width:140, title: '规划名称'}
            ,{field:'vdateStr', width:140, title: '规划起始日'}
            ,{field:'edateStr', width:140, title: '规划结束日'}
            //BSTOT-328 guyunfei 20200108 start
            ,{field:'assetTypeStr', width:140, title: '资产大类', sortable: false}
            //BSTOT-328 guyunfei 20200108 end
            ,{field:'planTargetAmtStr', width:140, title: '规划目标值(修正后)(万元)',type: 'money'}
            ,{field:'planChangeAmtStr', width:160, title: '剩余规划变动值（万元）',type: 'money'}
            ,{field:'remark', title: '备注',sortable: false}
            ,{field:'hasChildren', title: '是否有子节点',visible:false }
        ]
    });
    /**
     * 产品系列发行试算-新建
     */
    $('#ProductPublishCounting001_btn_add').click(
		function() {
			Joyin.dialog(JoyinUtil.getLabel("ProductPublishCounting002"), basePath
				+ "invest/product/publish/ProductPublishCounting002/init",
					"95%", "90%", {pageModel : PAGE_MODEL_NEW},function(){});
    });
    /**
     * 产品系列发行试算-新建
     */
    $('#ProductPublishCounting001Ast_btn_add').click(
		function() {
			Joyin.dialog(JoyinUtil.getLabel("ProductPublishCounting002"), basePath
				+ "invest/product/publish/ProductPublishCounting002/init",
					"95%", "90%", {pageModel : PAGE_MODEL_NEW},function(){});
    });
    /**
     * 产品系列发行试算-修改
     */
    $('#ProductPublishCounting001_btn_modify').click(
		function() {
			var checkedRows = prdTable.getAllCheckedRows();
			if(checkedRows.length == 0){
				Joyin.error("请选择二次试算数据");
				return;
			}
			if(!checkedRows[0].prdIssueplanCode || checkedRows[0].prdIssueplanCode == ''){
                Joyin.error("请选择有规划编号的数据");
                return;
            }

			//var 
			for(var i in checkedRows){
				
			}
			Joyin.dialog(JoyinUtil.getLabel("二次试算"), basePath
				+ "invest/product/publish/ProductPublishCounting002/init",
					"95%", "90%", {pageModel : PAGE_MODEL_EDIT,prdIssueplanCode : checkedRows[0].prdIssueplanCode}
						,function(){});
    });
    /**
     * 产品系列发行试算-删除
     */
    $('#ProductPublishCounting001_btn_delete')
    	.click(function() {
			var checkedRows = prdTable.getAllCheckedRows();
			if(checkedRows.length == 0){
				Joyin.error("请选择需要删除的数据");
				return;
			}
			var object = checkedRows[0];
			object.operateType = "delete";
	        Joyin.ajaxJson(basePath + 'invest/product/publish/ProductPublishCounting001/check', object, function(result) {
	        Joyin.confirm(
	                JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")),
	                function() {
	                    WorkFlow.doDeleteList(basePath
	                            + 'invest/product/publish/ProductPublishCounting001/delete',
	                            object, "ProductPublishCounting001_btn_delete",
	                            "ProductPublishCounting002",function(){table.reload();});
	                });
	         });
    	}
	);
    /**
     * 产品系列资产大类-导出
     */
    $('#ProductPublishCounting001_ast_export').click(function(){
		exportPrdExcel("SECOND");
	});
    var exportPrdExcel = function(val) {
    	var paramObj ;//获取参数
        if(val == "FIRST"){
            paramObj = getPageParams();
            paramObj.data = JSON.stringify(prdTable.getAllCheckedRows());
        }else{
            paramObj = getAssetParams();
            paramObj.data = JSON.stringify(assetTable.getAllCheckedRows());
        }

        Joyin.exportExcel('export', paramObj);
    };
    /**
     * 产品系列发行试算-导出
     */
    $('#ProductPublishCounting001_prd_export').click(function(){
		exportPrdExcel("FIRST");
	});
    $(function () {
    	
    	//产品系列列表查询
    	$('#ProductPublishCounting001_pro_search').click(function() {
    		prdTable.load();
        });
    	//资产大类列表查询
    	$('#ProductPublishCounting001_ast_search').click(function() {
    		assetTable.load();
        });
    });
}());