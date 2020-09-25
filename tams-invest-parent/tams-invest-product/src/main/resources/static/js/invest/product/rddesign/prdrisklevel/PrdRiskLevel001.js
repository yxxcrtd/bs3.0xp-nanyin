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
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        cols:[
        	{field:'generateTime', title: 'PrdRiskLevel002.page.finProductTime',type : 'datetime',sortField:'GENERATE_TIME'} // 生成时间
        	,{field:'prdDesignId', title: 'PrdRiskLevel002.page.finProductCode',sortField:'PRD_DESIGN_ID'} // 流水号
            ,{field:'finProductName', title: 'PrdRiskLevel001.table.finProductCode',sortField:'PRD_DESIGN_SERIAL_NAME'} // 产品设计系列名称
            ,{field:'riskLevelStr', title: 'PrdRiskLevel001.table.riskLevel',sortField:'RISK_LEVEL'} // 风险等级
            ,{field:'ajustRiskLevelStr', title: 'PrdRiskLevel001.table.ajustRiskLevel',sortField:'AJUST_RISK_LEVEL'} // 调整后风险等级
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit','delt']}
        ]
    });

    /**
     * check查询条件和加载table
     */
    var checkAndLoadTable = function () {
        table.load();
    };
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = 'edit';
        Joyin.ajaxJson(basePath + 'invest/product/rddesign/prdrisklevel/PrdRiskLevel002/check', data, function(result) {
        	Joyin.dialog(JoyinUtil.getLabel("PrdRiskLevel002.edit"),
                    basePath + "invest/product/rddesign/prdrisklevel/PrdRiskLevel002/init",
                    "98%", "95%", {pk : data.seqNo, pageModel : PAGE_MODEL_EDIT},function(){table.reload();});
        });
    });

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
		var object = {
							selectedItem : [ data ],
							operateType : "delete",
							requirementId : data.requirementId,
							finProductCode : data.finProductCode,
							seqNo : data.seqNo,
							riskLevelStr : data.riskLevelStr,
							ajustRiskLevelStr : data.ajustRiskLevelStr,
							riskLevel : data.riskLevel,
							ajustRiskLevel : data.ajustRiskLevel,
							generateTime:data.generateTime,
							finProductName:data.finProductName
						};
    	data.operateType = 'delete';
    	
    	Joyin.ajaxJson(basePath + 'invest/product/rddesign/prdrisklevel/PrdRiskLevel002/checkDelete', data, function(result) {
    		Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/product/rddesign/prdrisklevel/PrdRiskLevel002/delete', object, "PrdRiskLevel001_btn_delete", "PrdRiskLevel002");
            });
    	});
    });
    
    /**
     * 导出
     */
	var exportExcel = function() {
		
		// 组装参数
		var param = {};
		
		var finProductName = $("#finProductName").val();
		var prdDesignId = $("#id").val();
		var generateTime = $("#generateTime").val();
		
		var startDate = generateTime.slice(0, 10);
		var endDate = generateTime.slice(-10);
		
		if(finProductName){
			param.finProductName = finProductName[0];
		}
		if(prdDesignId){
			param.prdDesignId = prdDesignId;
		}
		if(generateTime){
			param.startDate = startDate;
			param.endDate = endDate;
		}
		
		Joyin.exportExcel('export',param);
	};

    /**
     * 新建
     */
    var openNew = function(){
        Joyin.dialog(JoyinUtil.getLabel("PrdRiskLevel002"),
                JoyinUtil.getRealPath("invest/product/rddesign/prdrisklevel/PrdRiskLevel002/init"),
                "98%", "95%",{pageModel : PAGE_MODEL_NEW},function(){table.reload();})
    }

    $(function () {
       $("#PrdRiskLevel001_btn_search").click(function () {
    	   table.load();
       });
       $("#PrdRiskLevel001_btn_new").click(function () {
    	   openNew();
       });
       $('#PrdRiskLevel001_btn_export').click(function() {
           exportExcel();
       });
    });
}());
