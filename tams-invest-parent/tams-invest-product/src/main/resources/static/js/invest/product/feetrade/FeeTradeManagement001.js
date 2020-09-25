/**
 * 产品发行规划试算
 */
(function() {
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
    	var param = {
    			
    	}
    	return param;
    };
    /**
     * 定义产品系列数据表格
     */
    var table = $("#table").joyintable({
        ajax: {
        	url: 'findPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        lazyload:false,
        cols:[
        	{field:'bookSetId',title: 'FeeTradeManagement001.bookSetId', width:180,sortable:false}
        	,{field:'bookSetName',title: 'FeeTradeManagement001.bookSetName', width:180,sortable:false}
            ,{field:'feeType',title: 'FeeTradeManagement002.feeType', width:180,sortable:false, editOption:{type:'select', result: feeTypeSelect}}
            ,{field:'payDate',title: 'FeeTradeManagement002.payDate', width:180,sortable:false, type: 'date'}
            ,{field:'feeEndDate',title: 'FeeTradeManagement001.feeEndDate', width:180,sortable:false, type: 'date'}
            ,{field:'shouldPayAmt',title: 'FeeTradeManagement001.shouldPayAmt', width:180,sortable:false,type: 'money'}
            ,{field:'payAmt',title: 'FeeTradeManagement001.payAmt', width:180,sortable:false,type: 'money'}
            ,{field:'isCountOver',title: 'FeeTradeManagement001.isCountOver', width:180,sortable:false, editOption:{type:'select', result: YES_OR_NO}}
            ,{field:'isNeedTan',title: 'FeeTradeManagement001.isNeedTan', width:180,sortable:false, editOption:{type:'select', result: YES_OR_NO}}
            ,{field:'tradeId',title: '交易编号', width:250,sortable:false,visible:true}
        ]
    });
    /**
     * 费用交易管理-新建
     */
    $('#FeeTradeManagement001_btn_add').click(
		function() {
			Joyin.dialog(JoyinUtil.getLabel("FeeTradeManagement002_new"), basePath
				+ "invest/product/feetrade/FeeTradeManagement002/init",
					"95%", "90%", {pageModel : PAGE_MODEL_NEW},function(){});
    });
    /**
     * 费用交易管理-修改
     */
    $('#FeeTradeManagement001_btn_modify').click(
		function() {
			var checkedRows = table.getAllCheckedRows();
			if(checkedRows.length != 1){
				Joyin.error("请一条数据数据修改");
				return;
			}
			var obj = checkedRows[0];
			obj.pageModel = PAGE_MODEL_EDIT;
			Joyin.dialog(JoyinUtil.getLabel("费用交易-修改"), basePath
				+ "invest/product/feetrade/FeeTradeManagement002/init",
					"95%", "90%", obj, function(){});
    });
    /**
     * 产品系列发行试算-删除
     */
    $('#FeeTradeManagement001_btn_delete')
    	.click(function() {
			var checkedRows = table.getAllCheckedRows();
			if(checkedRows.length != 1){
				Joyin.error("请一条需要删除的数据");
				return;
			}
			var object = checkedRows[0];
			object.operateType = "delete";
	        Joyin.ajaxJson(basePath + 'invest/product/feetrade/FeeTradeManagement001/check', object, function(result) {
	        Joyin.confirm(
	                JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")),
	                function() {
	                    WorkFlow.doDeleteList(basePath
                    		+ 'invest/product/feetrade/FeeTradeManagement001/delete',
	                            object, "FeeTradeManagement001_btn_delete", 
	                            	"FeeTradeManagement002",function(){table.reload();});
	                });
	         });
    	}
	);
    /**
     * 产品系列资产大类-导出
     */
    $('#FeeTradeManagement001_ast_export').click(function(){
		exportPrdExcel("SECOND");
	});
    var exportPrdExcel = function(val) {
    	var paramObj ;//获取参数
        if(val == "FIRST"){
            paramObj = getPageParams();
            paramObj.data = JSON.stringify(prdTable.getAllCheckedRows());
        }else{
            paramObj = getAssetParams();
            paramObj.data = JSON.stringify(assetTable.getAllCheckedRows());
        }

        Joyin.exportExcel('export', paramObj);
    };
    /**
     * 产品系列发行试算-导出
     */
    $('#FeeTradeManagement001_prd_export').click(function(){
		exportPrdExcel("FIRST");
	});
    $(function () {
    	
    	//产品系列列表查询
    	$('#FeeTradeManagement001_search').click(function() {
    		table.load();
        });
    });
}());