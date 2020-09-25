(function(){
	
	/**
     * 获取查询参数
     */

    var getSearchParams = function() {
    	var param = {};
    	var designSerialName = $('#FinProductPlanningEstablish001_designSerialName').val();
    	if(designSerialName){
    		param.planningSeriesName = designSerialName.join(",");
    	}else {
            param.planningSeriesName="";
        }
    	if($('#FinProductPlanningEstablish001_bdate').val()) {
            param.baseDate = $('#FinProductPlanningEstablish001_bdate').val();
        }else{
            param.baseDate ="";
        }
    	if($('#FinProductPlanningEstablish001_sdate').val()) {
            param.planningStartDate = $('#FinProductPlanningEstablish001_sdate').val();
        }else{
            param.planningStartDate ="";
        }
    	if($('#FinProductPlanningEstablish001_edate').val()) {
            param.planningEndDate = $('#FinProductPlanningEstablish001_edate').val();
        }else{
            param.planningEndDate="";
        }
        // param.validStatus= $('#FinProductPlanningEstablish001_effectStatus').val();
        return param;
    };
	/**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({ 
    	ajax: {
            url: 'findForPageAll',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        checkbox: false,
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("FinProductPlanningEstablish002.reference"),
                    JoyinUtil.getRealPath("invest/product/productplanning/informationMaintenance/FinProductPlanningEstablish002/init"),
                    "98%", "95%",data,function(){table.reload();}
            );
        },
        cols:[
             // {field:'validStatusText', width:120, title: 'FinProductPlanningEstablish001.table.validStatus',sortField:'validStatus',}
            {field:'planningSeriesNo', width:150, title: 'FinProductPlanningEstablish001.table.designId'}
            ,{field:'planningSeriesName', width:150, title: 'FinProductPlanningEstablish001.table.designSerialName'}
            ,{field:'baseDate', width:130, title: 'FinProductPlanningEstablish001.table.bdateStartDate', type: 'date'}
            ,{field:'planningStartDate', width:130, title: 'FinProductPlanningEstablish001.table.sdateStartDate', type: 'date'}
            ,{field:'planningEndDate', width:130, title: 'FinProductPlanningEstablish001.table.sdateEndDate', type: 'date'}
            ,{field:'initialPlanningValue', width:120, title: 'FinProductPlanningEstablish001.table.initial', type: 'money'}
            ,{field:'planningChangeValue', width:120, title: 'FinProductPlanningEstablish001.table.change', type: 'money'}
            ,{field:'planningTargetValue', width:120, title: 'FinProductPlanningEstablish001.table.target', type: 'money'}
            ,{field:'remarks',  width:120, title: 'FinProductPlanningEstablish001.table.remake'}
            ,{field:'operateType', title: 'SysCommon.operation', type:'action',width : 120, buttons: ['edit','delt']}
            ,{field:'planningNo',  visible: false}
            ,{field:'workflowId', visible: false}
            ,{field:'productsDue',  visible: false}
        ]
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = 'edit';
        Joyin.ajaxJson(basePath + 'invest/product/productplanning/informationMaintenance/FinProductPlanningEstablish001/check', data, function(result) {
        	Joyin.dialog(JoyinUtil.getLabel("FinProductPlanningEstablish002.edit"),
                    basePath + "invest/product/productplanning/informationMaintenance/FinProductPlanningEstablish002/init",
                    "98%", "95%", {planningNo : data.planningNo, pageModel : PAGE_MODEL_EDIT},function(){table.reload();});
        });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {planningNo: data.planningNo,planningSeriesNo:data.planningSeriesNo,operateType:"delete"};
    	// data.operateType = 'delete';
        Joyin.ajaxJson(basePath + 'invest/product/productplanning/informationMaintenance/FinProductPlanningEstablish001/check', data, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/product/productplanning/informationMaintenance/FinProductPlanningEstablish001/delete', object, "FinProductPlanningEstablish002_btn_delete", "FinProductPlanningEstablish002");
            });
        });
    });
    /**
     * 导出
     */
    var exportExcel = function(){
        Joyin.exportExcel('export',getSearchParams());
    };
    
    /**
     * 新建
     */
    var openNew = function(){
        Joyin.dialog(JoyinUtil.getLabel("FinProductPlanningEstablish002.new"),
        		JoyinUtil.getRealPath("invest/product/productplanning/informationMaintenance/FinProductPlanningEstablish002/init"),
                "98%", "95%",{pageModel : PAGE_MODEL_NEW},function(){table.reload();}
                );
    };
    
	$(function() {
        $('#FinProductPlanningEstablish001_btn_search').click(function() {
            table.load();
        });
        $('#FinProductPlanningEstablish001_btn_export').click(function() {
        	exportExcel();
        });
        $('#FinProductPlanningEstablish001_btn_new').click(function() {
        	openNew();
        });
	});
}());