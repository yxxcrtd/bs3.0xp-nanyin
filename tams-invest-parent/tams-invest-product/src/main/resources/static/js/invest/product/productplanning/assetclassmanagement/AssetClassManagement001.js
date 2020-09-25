(function(){
	
	/**
	 * 获取查询参数
	 */
  var getSearchParams = function() {
        var param = $.serializeToObject('#searchForm');
        if(param.customAssetTypeTwo){
            param.customAssetTypeTwo = param.customAssetTypeTwo.join(",");
        }
        if(param.customAssetTypeThree){
            param.customAssetTypeThree = param.customAssetTypeThree.join(",");
        }
        if(param.customAssetTypeFour){
            param.customAssetTypeFour = param.customAssetTypeFour.join(",");
        }
        if(param.customAssetTypeFive){
            param.customAssetTypeFive = param.customAssetTypeFive.join(",");
        }
        if(param.assetType){
            param.assetType = param.assetType.join(",");
        }
        if(param.pinanAssetType){
            param.pinanAssetType = param.pinanAssetType.join(",");
        }
        return param;
        
    };
    
  var tabCols = [
        {field:'customAssetTypeTwoName', title: 'AssetClassManagement.customAssetTypeTwo', width:150,sortField:'customAssetTypeTwo'},// 资产二级分类
        {field:'customAssetTypeThreeName', title: 'AssetClassManagement.customAssetTypeThree', width:150,sortField:'customAssetTypeThree'},
        {field:'customAssetTypeFourName', title: 'AssetClassManagement.customAssetTypeFour', width:150,sortField:'customAssetTypeFour'},
        {field:'customAssetTypeFiveName', title: 'AssetClassManagement.customAssetTypeFive', width:150,sortField:'customAssetTypeFive'},
        {field:'assetTypeName', title: 'AssetClassManagement.assetType', width:150,sortField:'assetType'},
        {field:'pinanAssetTypeName', title: 'AssetClassManagement.pinanAssetType', width:170,sortField:'pinanAssetType'},
        {field:'isNonstandardName', title: 'AssetClassManagement.isNonstandard', width:150,sortField:'isNonstandard'},
        {field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt'],width:120}
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
	 * 绑定删除事件 未修改
	 */
  table.bind('click', '.delt', function(data, index) {
    	// 发起流程
    	var object = {selectedItem: [data], customAssetTypeTwo: data.customAssetTypeTwo ,customAssetTypeThree: data.customAssetTypeThree ,customAssetTypeFour: data.customAssetTypeFour ,customAssetTypeFive: data.customAssetTypeFive};
    	data.operateType = 'delete';
    	Joyin.ajaxJson(basePath + '/invest/product/productplanning/assetclassmanagement/AssetClassManagement001/check', object, function(result) {
	        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
	         	WorkFlow.doDeleteList(basePath + '/invest/product/productplanning/assetclassmanagement/AssetClassManagement001/delete', object, "AssetClassManagement001_btn_delete","AssetClassManagement002");
	        });
    	})
    });    
    
    /**
	 * 绑定编辑事件
	 */
  table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = 'edit';
        Joyin.ajaxJson(basePath + 'invest/product/productplanning/assetclassmanagement/AssetClassManagement002/check', data, function(result) {
	        Joyin.dialog(JoyinUtil.getLabel("AssetClassManagement002.edit"),
	                basePath + "invest/product/productplanning/assetclassmanagement/AssetClassManagement002/init",
	                "98%", "95%", data,function(){table.reload();});
        })     
    });
    /**
	 * 导出
	 */
  var exportExcel = function(){
        Joyin.exportExcel('export',getSearchParams());
    }; 
   /* $('#customAssetTypeFive').change(function() {
    if ($("#customAssetTypeTwo").val() != null) {
		if ($("#customAssetTypeThree").val() != null) {
			if ($("#customAssetTypeFour").val() != null	) {	 
				
                    if($("#customAssetTypeTwo").val() === '05' || $("#customAssetTypeThree").val ==='22' ){
                    	$("#customAssetTypeTwo").val() ==='01';
                    }
                    
				}
			}

		}

	})*/


  $(function() {
        $('#AssetClassManagement001_btn_search').click(function() { 
            table.load();
        });
        $('#AssetClassManagement001_btn_export').click(function() {
        	exportExcel();
        });
      // 批量删除
        $('#AssetClassManagement001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + '/invest/product/productplanning/assetclassmanagement/AssetClassManagement001/check', object, function(result) {														
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                	WorkFlow.doDeleteList(basePath + '/invest/product/productplanning/assetclassmanagement/AssetClassManagement001/delete', object, "AssetClassManagement001_btn_delete","AssetClassManagement002");
                });
            });
        });
        
        // 新建按钮点击
  $('#AssetClassManagement001_btn_add').click(function() {
                 Joyin.dialog(JoyinUtil.getLabel("AssetClassManagement002"),
                        basePath+ "invest/product/productplanning/assetclassmanagement/AssetClassManagement002/init",
                            "85%", "85%", {pageModel:PAGE_MODEL_NEW},function(){
                                table.reload();
                       
            
        });
	});
	});
}());