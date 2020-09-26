$(function() {
	/**
     * 导出
     */
    var exportExcel = function() {
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
    };

    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            finprodId : $('#finprodId').val(),
            finprodAbbr : $('#finprodAbbr').val(),
            valueSource : $('#valueSource').val(),
            valueDate : $('#valueDate').val(),
            valueDateBegin: $('#valueDate').startDate(),
            valueDateEnd: $('#valueDate').endDate()
        }
    };
/**
 * 表格加载
 */
    var table = $("#dataTable").joyintable({
        ajax : {
            url : 'ValNetPlanQuotation001Search',
            type : 'POST',
            data : function() {
                return getSearchParams();
            }
        },
    	dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("查看详情："+data.finprodId),
                    JoyinUtil.getRealPath("/valuation/basedata/marketdata/netplanquotation/ValNetPlanQuotation002/init"),
                    "90%", "90%", {pageModel:PAGE_MODEL_REFERENCE,finprodId:data.finprodId,valueDate:data.valueDate},
                    function(){
                       //table.reload();
                        });
        },
        cellMinWidth : 100,
        cols : [ 
                {field : 'finprodId', title : '资产代码', sort : true}, 
                {field : 'finprodAbbr', title : '资产名称', sort : true}, 
               // {field : 'valueSourceName',title : '数据来源',sortField:'valueSource', sort : true}, 
                {field : 'valueSource',title : '数据来源', sort : true,visible:false}, 
                {field : 'valueSourceFmt',title : '数据来源',sortField:"valueSource", sort : true}, 
                {field : 'valueDateFmt',title : '行情日期',sort : true ,sortField:"valueDate"},
                {field : 'valueDate',title : '行情日期',sort : true ,visible:false}, 
                {field : 'valueAsset',title : '资产净值',type:'money',digit:4,width:220,sort : true}  
               ]
    });
   // ValNetPlanQuotation001Search
    
    // 绑定新建事件
    $('#ValNetPlanQuotation001_btn_add').click(function() {
        Joyin.dialog(JoyinUtil.getLabel("新建计划类资产净值信息（<i class='iconfont must' style='display:inline'>*</i>必填）"),
                    basePath + "valuation/basedata/marketdata/netplanquotation/ValNetPlanQuotation003/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_NEW,editFlag:"01"},function(){
                        table.reload();
                    });
    });
    
    /**
     * 筛选
     */
    $('#ValNetPlanQuotation001_btn_search').click(function() {
        table.load();
    });
    /**
     * 导出
     */
    $('#ValNetPlanQuotation001_btn_export').click(function() {
        exportExcel();
    });
    /**
     * 重置
     */
    $('#ValNetPlanQuotation001_btn_reset').click(function() {
        location.reload();
    });
    
    /**
     * 删除--走流程
     */
    $('#ValNetPlanQuotation001_btn_delete').click(function() {
    	var object = {};
    	var rowArr = table.getAllCheckedRows();// 所有选中行数据
    	
    	if(rowArr == null || rowArr.length == 0){
    		layer.msg("请选择一条记录！");   		
    	}else if(rowArr != null && rowArr.length>1){
			layer.msg("请选择一条记录！");
		}else{		
    	    object=rowArr[0];
	    	object.operateType = "delete";
		   	Joyin.ajaxJson(basePath + 'valuation/basedata/marketdata/netplanquotation/ValNetPlanQuotation001/check', object, function(result) {
		           Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
		        	   WorkFlow.doDeleteList(
	                    		basePath + 'valuation/basedata/marketdata/netplanquotation/ValNetPlanQuotation001/delete', 
	                    		object, 
	                    		"ValNetPlanQuotation003_btn_delete", 
	                    		"ValNetPlanQuotation003",
	                    		function() {
	                    			table.reload();
	                    		}
	                    	);
		           });
	       });
    	}
    });
    
    $('#ValNetPlanQuotation001_btn_edit').click(function() {
         //alert("你点击了修改");
    	var object = {};
    	var rowArr = table.getAllCheckedRows();// 所有选中行数据   	
    	if(rowArr == null || rowArr.length == 0){
    		layer.msg("请选择一条记录！");   		
    	}else if(rowArr != null && rowArr.length>1){
			layer.msg("请选择一条记录！");
		}else{	
			object =rowArr[0];			
			var finprodId = object.finprodId;
			var valueDate = object.valueDateFmt;
			 Joyin.dialog(JoyinUtil.getLabel("修改计划类资产净值信息（<i class='iconfont must' style='display:inline'>*</i>必填）"),
	                    basePath + "valuation/basedata/marketdata/netplanquotation/ValNetPlanQuotation003/init",
	                    "95%", "90%",{pageModel:PAGE_MODEL_EDIT,data:rowArr,editFlag:"02",finprodId:finprodId,valueDate:valueDate},function(){
	                        table.reload();
	         });
    	}
    });
    
});