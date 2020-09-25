(function(){
	
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var param = $.serializeToObject('#searchForm');
    	if(param.repFinprodName){
    		param.repFinprodName = param.repFinprodName.join(",");
    	}
    	if(param.repFinprodInsId){
    		param.repFinprodInsId = param.repFinprodInsId.join(",");
    	}
    	param.repRaisePstrdateStart = $('#repRaisePdate').startDate();
    	param.repRaisePstrdateEnd = $('#repRaisePdate').endDate();
    	param.key="REP_FINPROD_INS_ID";
    	param.order="asc";
    	return param;
    };
    // 表格的列
    var tabCols = [
    	{field:'repFinprodInsId', title: 'ProductReportRegister001.repFinprodInsId', width:130,sortField:'REP_FINPROD_INS_ID'}
    	,{field:'repFinprodName', title: 'ProductReportRegister001.repFinprodName', width:100,sortField:'REP_FINPROD_NAME'}
        ,{field:'repFinprodBrand', title: 'ProductReportRegister001.repFinprodBrand', width:100,sortField:'REP_FINPROD_BRAND'}
        ,{field:'repFinprodStage',sortField:'REP_FINPROD_STAGE', title: 'ProductReportRegister001.repFinprodStage', width:130}
        ,{field:'repTermStr', title: 'ProductReportRegister001.repTerm', width:100, sortField:'REP_TERM'}
		,{field:'repOperateModeStr', title: 'ProductReportRegister001.repOperateMode', width:100,sortField:'REP_OPERATE_MODE'}
		,{field:'repProfitTypeStr', title: 'ProductReportRegister001.repProfitType', width:130,sortField:'REP_PROFIT_TYPE'}
		,{field:'repRaisePstrdate', title: 'ProductReportRegister001.repRaisePstrdate', width:160,type:'date',sortField:'REP_RAISE_PSTRDATE'}
		,{field:'repRaisePenddate', title: 'ProductReportRegister001.repRaisePenddate', width:100,type:'date',sortField:'REP_RAISE_PENDDATE'}
		,{field:'repIssueTypeStr', title: 'ProductReportRegister001.daiding', width:100,sortField:'REP_ISSUE_TYPE'}
		,{field:'repRaiseAmtPlan', title: 'ProductReportRegister001.repRaiseAmtPlan', width:130,sortField:'REP_RAISE_AMT_PLAN',type:'number',digit:'2'}
		,{field:'repRemark', title: 'ProductReportRegister001.repRemark',width:130,sortField:'REP_REMARK'}
		,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
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
            Joyin.dialog(JoyinUtil.getLabel("ProductReportRegister001.reference"),
                    JoyinUtil.getRealPath("invest/product/productelement/reportregister/ProductReportRegister002/init"),
                    "98%", "95%",data,function(){table.reload();}
            );
        },

		cols: tabCols,
	});
    
    
    // 发送状态link页面，各系统酌情使用
	table.bind('click','.sendStatusLink', function(row) {
		console.log(row.finprodId);
       var label='';
       var pagePath='';
       pagePath = "invest/product/productelement/reportregister/ProductReportRegister002/init";
       Joyin.dialog(JoyinUtil.getLabel(label), basePath + pagePath,
           "95%", "90%", {
               pageModel: PAGE_MODEL_REFERENCE,
               finprodId: row.finprodId
           });
	});
       
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = 'edit';
        data.pageId='ProductReportRegister002';
        Joyin.ajaxJson(basePath + 'invest/product/productelement/reportregister/ProductReportRegister001/check', data, function(result) {
        	Joyin.dialog(JoyinUtil.getLabel("ProductReportRegister001.edit"),
                    basePath + "invest/product/productelement/reportregister/ProductReportRegister002/init",
                    "98%", "95%", {repFinprodId : data.repFinprodId, pageModel : PAGE_MODEL_EDIT},function(){table.reload();});
            
        });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
    	var object = {selectedItem: [data], operateType:"delete", repFinprodId : data.repFinprodId};
    	data.operateType = 'delete';
    	data.pageId='ProductReportRegister002';
        Joyin.ajaxJson(basePath + 'invest/product/productelement/reportregister/ProductReportRegister001/check', data, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/product/productelement/reportregister/ProductReportRegister001/delete', object, "ProductReportRegister001_btn_delete", "ProductReportRegister002");
            });
        });
    });
    
    /**
     * 导出
     */
    var exportExcel = function(){
        Joyin.exportExcel('export',getSearchParams());
    };
    
    var confirm = function(){
    	if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
            //判断是否选中元素
        	if( selArr != null &&  selArr.length > 0 ){
        		if (selArr.length != 1){
        			Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                    return;
        		}
        		var data = selArr[0];
        		data.operateType = 'confirm';
                Joyin.ajaxJson(basePath + 'invest/product/productelement/build/FinProductBuild001/check', data, function(result) {
                	Joyin.dialog(JoyinUtil.getLabel("FinProductBuild002.confirm"),
                            JoyinUtil.getRealPath("invest/product/productelement/build/FinProductBuild002/init"),
                            "98%", "95%",{pageModel : PAGE_MODEL_NEW, confirmFlag : true, finprodId: data.finprodId},function(){table.reload();}
                    );
                });
            }else{
            	Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
        }else{
        	Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
        	return;
        }
    }
    
    var openNew = function(){
        Joyin.dialog(JoyinUtil.getLabel("ProductReportRegister001.new"),
                JoyinUtil.getRealPath("invest/product/productelement/reportregister/ProductReportRegister002/init"),
                "98%", "95%",{pageModel : PAGE_MODEL_NEW},function(){table.reload();}
        );
    }
    
	
	$(function() {
        $('#ProductReportRegister001_btn_search').click(function() { 
            table.load();
        });
        $('#ProductReportRegister001_btn_export').click(function() {
        	exportExcel();
        });
        $('#ProductReportRegister001_btn_add').click(function() {
        	openNew();
        });
	});
}());