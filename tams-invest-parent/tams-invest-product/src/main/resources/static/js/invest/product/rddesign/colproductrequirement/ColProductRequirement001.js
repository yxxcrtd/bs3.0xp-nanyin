(function(){
	
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var param = $.serializeToObject('#searchForm');
    	param.requirementTimeStart = $('#requirementTime').startDate();
    	param.requirementTimeEnd = $('#requirementTime').endDate();
    	return param;
    };
    // 表格的列
    var tabCols = [
    	{field:'dataStatus',title: '', visible : false}
    	,{field:'requirementTime', title: '', visible : false}
        ,{field:'requirementId', title: 'ColProductRequirement001.requirementId', width:'10%', sortField:'requirementId'}
        ,{field:'dataStatusStr', title: 'ColProductRequirement001.dataStatus', width:'10%',sortField:'dataStatus'}
        ,{field:'reviewYesno', title: 'ColProductRequirement001.reviewYesno', width:'10%',visible : false}
        ,{field:'reviewYesnoStr', title: 'ColProductRequirement001.reviewYesno', width:'10%',sortField:'reviewYesno'}
		,{field:'reportStatusStr', title: 'ColProductRequirement001.reportStatus', width:'10%',sortField:'reportStatus'}
		,{field:'requirementBasis', title: 'ColProductRequirement001.requirementBasis', width:'10%',sortField:'requirementBasis'}
		,{field:'requirementTimeStr', title: 'ColProductRequirement001.requirementTime', width:'10%',sortField:'requirementTime'}
		,{field:'tarCustomer', title: 'ColProductRequirement001.tarCustomer', width:'10%',sortField:'tarCustomer'}
		,{field:'prdScale', title: 'ColProductRequirement001.prdScale', width:'10%',sortField:'prdScale'}
		,{field:'prdPeriod', title: 'ColProductRequirement001.prdPeriod', width:'10%',sortField:'prdPeriod'}
		,{field:'prdRate', title: 'ColProductRequirement001.prdRate', width:'10%',sortField:'prdRate'}
		,{field:'prdRequirementDetail', title: 'ColProductRequirement001.prdRequirementDetail', width:'10%',sortField:'prdRequirementDetail'}
		,{field:'requirementSource', title: 'ColProductRequirement001.requirementSource', width:'10%',visible : false}
		,{field:'requirementSourceStr', title: 'ColProductRequirement001.requirementSource', width:'10%',sortField:'requirementSource'}
        ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ,{field: 'reviewStart', title: 'ColProductRequirement001.review', type: 'action', buttons: [{
        	text:"<a class='reviewStart' style='cursor: pointer;'>" + "发起" + "</a>",
        	title:"发起初评"
        }],
        	
         }
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
            Joyin.dialog(JoyinUtil.getLabel("ColProductRequirement002.reference"),
                    JoyinUtil.getRealPath("invest/product/rddesign/colproductrequirement/ColProductRequirement002/init"),
                    "98%", "95%",data,function(){table.reload();}
            );
        },

		cols: tabCols,
	});
    
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = 'edit';
        Joyin.ajaxJson(basePath + 'invest/product/rddesign/colproductrequirement/ColProductRequirement001/check', data, function(result) {
        	Joyin.dialog(JoyinUtil.getLabel("ColProductRequirement002.edit"),
                    basePath + "invest/product/rddesign/colproductrequirement/ColProductRequirement002/init",
                    "98%", "95%", {requirementId : data.requirementId, pageModel : PAGE_MODEL_EDIT},function(){table.reload();});
            
        });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
    	var object = {selectedItem: [data], operateType:"delete", requirementId : data.requirementId,dataStatus : data.dataStatus,requirementTime : data.requirementTime};
    	data.operateType = 'delete';
        Joyin.ajaxJson(basePath + 'invest/product/rddesign/colproductrequirement/ColProductRequirement001/check', data, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/product/rddesign/colproductrequirement/ColProductRequirement001/delete', object, "ColProductRequirement001_btn_delete", "ColProductRequirement002");
            });
        });
    });
    
    /**
     * 绑定发起初评
     */
    table.bind('click','.reviewStart',function(data, index){
    	var object = {selectedItem: [data], operateType:"reviewStart", requirementId : data.requirementId,reviewYesno : data.reviewYesno,dataStatus : data.dataStatus};
    	Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("ColProductRequirement001.review.confirm")), function(){
    		Joyin.ajaxJson(basePath + 'invest/product/rddesign/colproductrequirement/ColProductRequirement001/reviewStart', object, function(result) {
    			table.reload();
    		});
        });
/*    	Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("ColProductRequirement001.review.confirm")),
    		JoyinUtil.getRealPath("invest/product/rddesign/colproductrequirement/ColProductRequirement001/reviewStart"),
            "98%", "95%",object,function(){table.reload();}
        );
*/    });
    /**
     * 导出
     */
    var exportExcel = function(){
        Joyin.exportExcel('export',getSearchParams());
    };
    
    
    var openNew = function(){
        Joyin.dialog(JoyinUtil.getLabel("ColProductRequirement002.new"),
                JoyinUtil.getRealPath("invest/product/rddesign/colproductrequirement/ColProductRequirement002/init"),
                "98%", "95%",{pageModel : PAGE_MODEL_NEW},function(){table.reload();}
        );
    }
    
	$(function() {
        $('#ColProductRequirement001_btn_search').click(function() { 
            table.load();
        });
        $('#ColProductRequirement001_btn_export').click(function() {
        	exportExcel();
        });
        $('#ColProductRequirement001_btn_add').click(function() {
        	openNew();
        });
	});
}());