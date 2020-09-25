$(function() {
	
	function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.operateType = operateType;
		object.pageModel = pageModel;
        object.tableList =  table.getAllRows();
        object.effDate = object.effdate;
        var workflowId;
        if(workflowId != null || workflowId != ''){
            object.workflowId = workflowId;
        }
    	Joyin.ajaxJson(basePath + 'invest/product/nolimit/nonstProUplimitMain/NonstProUplimitMain002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/product/nolimit/nonstProUplimitMain/NonstProUplimitMain002/save', object, "NonstProUplimitMain002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/product/nolimit/nonstProUplimitMain/NonstProUplimitMain002/save', object, "NonstProUplimitMain002_btn_submit");
                }
            });
        });
    
    };
    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        searchOnInit:false,
    	loadonce: true,
        rowno: true,
        page: false,
        addbtn: true,
        height : 200,
        checkbox :false,
        data:tableList,
        cols:[
        	//产品规划系列编号
        	{field:'prdPlanSerisId', title: 'NonstProUplimitMain.prdPlanSerisId',edit:true,  width:200,sortable:false, 
            	editOption:{type:'select',id: 'prdPlanSerisId',url: JoyinUtil.getRealPath('/invest/product/nolimit/nonstProUplimitMain/NonstProUplimitMain001/selectPrdFamily'),showField:'id'}
            }
        	//产品规划系列名称
            ,{field:'prdPlanSerisName', title: 'NonstProUplimitMain.prdPlanSerisName',edit:true,  width:200,sortable:false,
            	editOption:{type:'type', id: 'prdPlanSerisName'}
            }
        	//非标资产比例上限
           ,{field:'nonStandardLimit', title: 'NonstProUplimitMain.nonStandardLimit',edit:true, width:200, sortable:false, 
  
           }
           ,{field:'operation',width:80,multiple:true,title: 'SysCommon.operation', type:'action', buttons: [ 'delt'],editOption:{id: 'prdPlanSerisName'}
           }
       ],
       beforeEditCell : function(preVal, field, rowData){
    	   //修改模式下不允许更改编号
    	   if(field == "prdPlanSerisId" && pageModel==PAGE_MODEL_EDIT){
    	        return false;
    	    }else {
    	    	return true
    	    }
    	   if(field =="nonStandardLimit" && pageModel==PAGE_MODEL_EDIT){
    	        return true;
    	    }
    	   if(field =="prdPlanSerisName" && pageModel==PAGE_MODEL_EDIT){
   	        return true;
   	    }
       },
       editCell: function(value, data, field, cell, column){
    	   var tableList = table.getAllRows();
        	//产品规划系列名称选择联动
    		if(field == "prdPlanSerisId"){
    			Joyin.ajaxJson(basePath + 'invest/product/nolimit/nonstProUplimitMain/NonstProUplimitMain002/queryPrdPlanSerName', 
        	        	{prdPlanSerisId:value}, 
        	        	function(result) {
        	        		table.renderRow(data.LAY_TABLE_INDEX, {prdPlanSerisName: result.root[0].prdPlanSerisName});
        	        		tableList=table.getAllRows();
        	        	})
    		}
        },
        done : function(table, allData){
        	return true;
        }
    });
	/**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
    	table.removeRow(data);
    });
	$(function(){
        // 绑定按钮事件
    	$('#NonstProUplimitMain002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#NonstProUplimitMain002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    })
}());