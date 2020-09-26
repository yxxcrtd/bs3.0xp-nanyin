$(function() {
  
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            name : $.trim($('#ValAssetBookParameter001_name').val()),
            paramCode : $.trim($('#paramCode').val()),
        }
    };
/**
 * 表格加载
 */
    var table = $("#dataTable").joyintable({
        ajax : {
            url : 'ValAssetBookParameter001search',
            type : 'POST',
            data : function() {
                return getSearchParams();
            }
        },
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("查看"),
            		basePath + "valuation/accounadjustmanage/assetbookparameter/ValAssetBookParameter002/init",
                "85%", "85%", {pageModel:PAGE_MODEL_REFERENCE,
            	booksetId:data.booksetId,paramCode:data.paramCode, operateType:"view"},function(){
                    table.reload();
                });
        },
		cellMinWidth:100 ,
	    checkbox:true,
        cols : [ 
                {field : 'booksetId', title : '账套代码',sort : true},
                {field : 'booksetName', title : '账套名称',sort : true},
                {field : 'paramCode', title : '参数代码',sort : true},
                {field : 'paramName', title : '参数名称',sort : true },
                {field : 'paramValue', title : '参数值',sort : true},
                {field : 'remark', title : '参数说明',sort : true },
                {field:'operation', title: '操 作', type:'action', 
    	        	buttons: [
    	                {text: '&#xe642;',clas: 'edit',title: '编辑'},
    	                ]
    	        }
               ],
    });
    
    /**
     * 账套事件绑定
     */
    var tableBind = function () {
    	/**
         * 绑定编辑事件
         */
        table.bind('click', '.edit', function(data, index) {
            Joyin.dialog(JoyinUtil.getLabel("修改账套参数"), basePath
                    + "valuation/accounadjustmanage/assetbookparameter/ValAssetBookParameter002/init",
                    "90%",
                    "90%",
                    {booksetId:data.booksetId,paramCode:data.paramCode, pageModel : PAGE_MODEL_EDIT},    
                    function(){
                    	table.reload();
                    }
              );
        });
        
      /**
      * 新建账套参数
      */
     $('#ValAssetBookParameter001_btn_add').click(
	        function() {
	            var name= $.trim($('#ValAssetBookParameter001_name').val());//账套名称
	    		Joyin.ajaxJson(basePath + "valuation/accounadjustmanage/assetbookparameter/ValAssetBookParameter001/check", {booksetId:name}, function(result) {
	    	            Joyin.dialog(JoyinUtil.getLabel("新建账套参数"), basePath
	    	                    + "valuation/accounadjustmanage/assetbookparameter/ValAssetBookParameter002/init", "90%",
	    	                    "90%",{pageModel : PAGE_MODEL_NEW},
	    	                    function(){
	    	                    	table.reload();
	    	                    },
	    	                    null
	    	            ); 
	    		});
     });
        
        /**
         * 筛选
         */
        $('#ValAssetBookParameter001_btn_search').click(function() {
            table.load();
        });

    };
    
    /**
     * 导出
     */
    var exportExcel = function() {
        if (table) {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length === 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };

    /**
     * 页面加载完后执行
     */
    $(function(){
    	tableBind();// 账套事件绑定
    	
    	  // 绑定导出事件
        $('#ValAssetBookParameter001_btn_export').click(function() {
            exportExcel();
        });
    });

});