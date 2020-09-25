(function(){
	
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var param = $.serializeToObject('#searchForm');
    	return param;
    };
    // 表格的列
    var tabCols = [
    	{field:'dataStatus',title: '', visible : false}
        ,{field:'requirementId', title: 'ReportProductRequirement001.requirementId', width:'10%', sortField:'requirementId'}
        ,{field:'dataStatusStr', title: 'ReportProductRequirement001.dataStatus', width:'10%',sortField:'dataStatus'}
		,{field:'reportStatusStr', title: 'ReportProductRequirement001.reportStatus', width:'10%',sortField:'reportStatus'}
		,{field:'requirementBasis', title: 'ReportProductRequirement001.requirementBasis', width:'10%',sortField:'requirementBasis'}
		,{field:'requirementTimeStr', title: 'ReportProductRequirement001.requirementTime', width:'10%',sortField:'requirementTime'}
		,{field:'tarCustomer', title: 'ReportProductRequirement001.tarCustomer', width:'10%',sortField:'tarCustomer'}
		,{field:'prdScale', title: 'ReportProductRequirement001.prdScale', width:'10%',sortField:'prdScale'}
		,{field:'prdPeriod', title: 'ReportProductRequirement001.prdPeriod', width:'10%',sortField:'prdPeriod'}
		,{field:'prdRate', title: 'ReportProductRequirement001.prdRate', width:'10%',sortField:'prdRate'}
		,{field:'prdRequirementDetail', title: 'ReportProductRequirement001.prdRequirementDetail', width:'10%',sortField:'prdRequirementDetail'}
		,{field:'requirementSource', title: 'ReportProductRequirement001.requirementSource', width:'10%',visible : false}
		,{field:'requirementSourceStr', title: 'ReportProductRequirement001.requirementSource', width:'10%',sortField:'requirementSource'}
        //,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
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
    	
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("ReportProductRequirement002.reference"),
                    JoyinUtil.getRealPath("invest/product/rddesign/reportproductrequirement/ReportProductRequirement002/init"),
                    "98%", "95%",data,function(){table.reload();}
            );
        },

		cols: tabCols,
	});
    
    
    /**
     * 绑定编辑事件
     */
    /*table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = 'edit';
        Joyin.ajaxJson(basePath + 'invest/product/rddesign/colproductrequirement/ColProductRequirement001/check', data, function(result) {
        	Joyin.dialog(JoyinUtil.getLabel("ColProductRequirement002.edit"),
                    basePath + "invest/product/rddesign/colproductrequirement/ColProductRequirement002/init",
                    "98%", "95%", {requirementId : data.requirementId, pageModel : PAGE_MODEL_EDIT},function(){table.reload();});
            
        });
    });*/
    
    /**
     * 绑定删除事件
     */
   /* table.bind('click', '.delt', function(data, index) {
    	var object = {selectedItem: [data], operateType:"delete", requirementId : data.requirementId,dataStatus : data.dataStatus};
    	data.operateType = 'delete';
        Joyin.ajaxJson(basePath + 'invest/product/rddesign/colproductrequirement/ColProductRequirement001/check', data, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/product/rddesign/colproductrequirement/ColProductRequirement001/delete', object, "ColProductRequirement001_btn_delete", "ColProductRequirement002");
            });
        });
    });*/
    
    /**
     * 导出
     */
    var exportExcel = function(){
        Joyin.exportExcel('export',getSearchParams());
    };
    
    
    /**
     * 上报
     */
    var openNew = function(){
    	if(table!=null && table!=''){
    		var selArr = table.getAllCheckedRows();
    		if(selArr == 0){
    			Joyin.error(JoyinUtil.getMessage("FIN_PROD_E_0140"));
    		}
    		if( selArr != null &&  selArr.length > 0 ){
    			if (selArr.length != 1){
        			Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                    return;
        		}
    			var data = selArr[0];
    			if(data.dataStatus == dataStatus_effective_no){
    				Joyin.dialog(JoyinUtil.getLabel("ReportProductRequirement002.report"),
    		                JoyinUtil.getRealPath("invest/product/rddesign/reportproductrequirement/ReportProductRequirement002/init"),
    		                "98%", "95%",{pageModel : PAGE_MODEL_EDIT,requirementId : data.requirementId},function(){table.reload();}
    		        );
    			}else{
    				Joyin.error(JoyinUtil.getMessage("FIN_PROD_E_0032",JoyinUtil.getLabel("ReportProductRequirement002.dataSatus.02")));
    			}
    		}
    	}
        
    }
    
	$(function() {
        $('#ReportProductRequirement001_btn_search').click(function() { 
            table.load();
        });
        $('#ReportProductRequirement001_btn_export').click(function() {
        	exportExcel();
        });
        $('#ReportProductRequirement001_btn_report').click(function() {
        	openNew();
        });
	});
}());