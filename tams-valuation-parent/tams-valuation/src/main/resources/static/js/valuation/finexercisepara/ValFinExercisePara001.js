$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            booksetId: $.trim($('#booksetId').val()),
            finprodId: $.trim($('#finprodId').val())
        }
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
        cellMinWidth : 200,
        cols:[
            {field:'booksetId', title: '账套代码', width:200, sort : true}
            ,{field:'booksetName', title: '账套名称',sort : true}
            ,{field:'finprodId', title: '资产代码',sort : true}
            ,{field:'branch', title: '分支序号',sort : true, visible:false}
            ,{field:'finprodName', title: '资产名称',sort : true}
            ,{field:'exeDateType', title: '含权债摊销日设置', sort : true}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', width:120, fixed: 'right', 
            	buttons: [
	                {text: '&#xe642;',clas: 'edit',title: '编辑'},
	                {text: '&#xe640;',clas: 'delt',title: '删除'}]
//            	render:function(data,type,row,meta){
//					var html= "";
//					if(purviewElements.indexOf('_edit')!=-1){
//	                	  html = html+"<i class='btn layui-icon edit' title='编辑'>&#xe642;</i>";
//	                  }
//	                  if(purviewElements.indexOf('_delete')!=-1){
//	                	  html = html+"<i class='btn icon-delete delete' title = '删除'></i>";
//	                  }
//	                  return html;
//				} 
            }
        ],
		dblclick:function(table, data) {
			Joyin.dialog(JoyinUtil.getLabel("查看含权债摊销参数设置"),
		               basePath + "valuation/finexercisepara/ValFinExercisePara002/init",
		               "85%", "85%", {pageModel:PAGE_MODEL_REFERENCE, booksetId : data.booksetId, finprodId : data.finprodId, branch : data.branch},function(){
		            	   //table.reload();
		       });
		}
    });
    
    /**
     * 表格事件绑定
     */
    var tableBind = function () {
    	/**
		 * 绑定事件：编辑
		 */
		table.bind('click', '.edit', function(data, index) {
		   	Joyin.dialog(JoyinUtil.getLabel("编辑含权债摊销参数设置（ "+ "<span style='color:red'> * </span>" + "必填）"),
		               basePath + "valuation/finexercisepara/ValFinExercisePara002/init",
		               "85%", "85%", {pageModel:PAGE_MODEL_EDIT, booksetId : data.booksetId, finprodId : data.finprodId , branch : data.branch},function(){
		            	   table.reload();
		       });
	    });
       
    	/**
    	 * 绑定事件：删除
    	 */
        table.bind('click', '.delete', function(data, index) {
    		var object = {};
    		object.booksetId = data.booksetId;
        	object.finprodId = data.finprodId;
        	object.branch = data.branch;
        	object.operateType = "delete";
    	   	Joyin.ajaxJson(basePath + 'valuation/finexercisepara/ValFinExercisePara001/check', object, function(result) {
    	           Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
    	        	   WorkFlow.doDeleteList(basePath + 'valuation/finexercisepara/ValFinExercisePara001/delete', object, "ValFinExercisePara001_btn_delete", "ValFinExercisePara002",function() {
    	        		   table.reload();
       	              });
    	           });
    	       });
       });
    }
    

    
    /**
     * 画面加载完成
     */
    $(function() {
    	
    	tableBind(); // 表格事件绑定
    	
        // 绑定按钮事件
        $('#ValFinExercisePara001_btn_search').click(function() {
            table.load();
        });
        
        /**
		 * 绑定事件：新建
		 */
		$("#ValFinExercisePara001_btn_add").click(function(event){
			Joyin.dialog(JoyinUtil.getLabel("新建含权债摊销参数设置（ "+ "<span style='color:red'> * </span>" + "必填）"),
		               basePath + "valuation/finexercisepara/ValFinExercisePara002/init",
		               "70%", "85%", {pageModel:PAGE_MODEL_NEW},function(){
		            	   table.reload();
		       });
		});
		
		/** 事件绑定: 导出 */
	    $('#ValFinExercisePara001_btn_export').click(function() {
	    	if (table != null && table != '') {
	            var selArr = table.getAllCheckedRows();
	            var datas = JSON.stringify(selArr);
	        }
	        if (!selArr || selArr.length == 0) {
	            var paramObj = getSearchParams();
	            Joyin.exportExcel('export', paramObj);
	        } else {
	            Joyin.exportExcel('export', {
	                datas : datas
	            });
	        }
	    });
    });
}());