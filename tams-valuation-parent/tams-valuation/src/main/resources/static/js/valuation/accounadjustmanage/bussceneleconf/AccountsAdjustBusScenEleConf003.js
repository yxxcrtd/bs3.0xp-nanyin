$(function () {
	
	 /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            tableId : $('#AccountsAdjustBusScenEleConf003_tableId').val(),
            tableName : $('#AccountsAdjustBusScenEleConf003_tableName').val()
        }
    };
 
    /**
     * 表格加载
     */   
	  var table=$("#dataTable").joyintable({
		  ajax : {
	      	  url: 'findPage',
	          type: 'POST',
	          data : function() {
	              return getSearchParams();
	          }
	      },
		  searchOnInit: true,
		  cellMinWidth:100 ,
		  cols:[
	          {field:'tableId', title:'业务场景代码', width:100, sort:true},
	          {field:'tableName', title:'业务场景名称', width:120, sort:true},
			  {field:'dictCode', title:'数据项代码', minWidth:150, sort:true},
			  {field:'dictName', title:'数据项名称', minWidth:200, sort:true},
			  {field:'dictType', title:'数据类型', minWidth:100, sort:true},
			  {field:'dictSource', title:'数据来源', width:120, sort:true},
			  {field:'dictId', title:'数据id', width:0, sort:true},
			  {field : 'operation', title: '操作',align:'center', fixed:'right',type:'action', buttons: ['edit', 'delt']}
			]
	   });
	  /**
	     * 绑定编辑事件
	     */
	    table.bind('click', '.edit', function(data, index) {
	        data.pageModel = PAGE_MODEL_EDIT;
	        Joyin.dialog(JoyinUtil.getLabel("数据要素修改"),
	                basePath + "valuation/accounadjustmanage/bussceneleconf/AccountsAdjustBusScenEleConf004/init",
	                "95%", "80%", {pageModel:PAGE_MODEL_EDIT,
	        	                    dictId:data.dictId});
	    });
	    
	    /**
	     * 绑定删除事件
	     */
	    table.bind('click', '.delt', function(data, index) {
	        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
	        	Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/bussceneleconf/AccountsAdjustBusScenEleConf003/delete', data, function(result) {
	        		 table.reload();
	        	});
	        });
	    });

  
  //业务要素新建
  $('#AccountsAdjustBusScenEleConf003_add').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("业务要素新建"),
      basePath + "valuation/accounadjustmanage/bussceneleconf/AccountsAdjustBusScenEleConf004/init",
      "96%", "90%", {pageModel:PAGE_MODEL_NEW,tableId:$('#AccountsAdjustBusScenEleConf003_tableId').val()});
  });

});