(function() {

    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
    	var finprodId;
    	var prodType;
    	if($("#FinProductQuotation001_finprodId").val()){
    		finprodId=$("#FinProductQuotation001_finprodId").val().join(",");
    	}
    	if($("#prodType").val()){
    	    prodType=$("#prodType").val().join(",");
        }
        return {
            finprodId: finprodId,
            prodType: prodType,
            saleDepartment: $('#FinProductQuotation001_saleDepartment').val(),
            effDateStart: $('#FinProductQuotation001_effDate').startDate(),
            effDateEnd: $('#FinProductQuotation001_effDate').endDate(),
            sendStatus : $('#sendStatus').val()
        }
    };
    
    /**
     * 定义数据表格
     */
    var tableCols = new Array();
    tableCols = [
		{field:'sendStatusStr', title: 'FinProductQuotation001.sendStatus', 
			render : function(data, type, row, meta) {
				//alert(isSendStatusTableLinkShow+'*'+row.sendStatusStr+'*');
			 	if (isSendStatusTableLinkShow === 'N') {
			 		return row.sendStatusStr;
				}
				return "<a  class='pro-link sendStatusLink' style='cursor: pointer;'>"+row.sendStatusStr+"</ a>";
			},sortField:"sendStatus"
		},
        {field: 'finprodMarketId', title: 'FinProductQuotation001.finprodMarketId'}
        , {field: 'finprodAbbr', title: 'FinProductQuotation001.finprodAbbr'}
        /*, {field: 'prodType', title: 'FinProductQuotation002.prodType'}*/
       /* , {field: 'saleDepartmentStr', title: 'FinProductQuotation001.saleDepartment', sortField : 'saleDepartment'}*/
        , {field: 'effDate', title: 'FinProductQuotation001.effDate', type : 'date'}
        , {field: 'yield', title: 'FinProductQuotation001.yield', type : 'number',digit:4}
        , {field: 'finprodId', visible:false}
        , {field: 'vdate', visible:false}
        , {field: 'cashId', visible:false}
        , {field: 'profitType', visible:false}
        , {field: 'isInitial', visible:false}
        , {field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}//编辑和删除事件
    ];
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function () {
                return getSearchParams();
            }
        },
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("FinProductQuotation002.detail"),
                    JoyinUtil.getRealPath("invest/product/messageadjust/finproductquotation/FinProductQuotation002/init"),
                    "98%", "95%", data,
                    function(){table.reload();});
        },
        cols: tableCols
    });
    if (isSendStatusTableShow === 'N') {
		tableCols[0].visible = false;
	}
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = 'edit';
        Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finproductquotation/FinProductQuotation001/check', data, function(result) {
	        Joyin.dialog(JoyinUtil.getLabel("FinProductQuotation002.edit"),
	                basePath + "invest/product/messageadjust/finproductquotation/FinProductQuotation002/init",
	                "98%", "95%", data,function(){table.reload();});
        })
        /*Joyin.ajaxJson(basePath + 'invest/product/productelement/scheduling/FinProductBuild001/check', data, function(result) {
        	Joyin.dialog(JoyinUtil.getLabel("FinProductScheduling002.edit"),
                    basePath + "invest/product/messageadjust/finproductquotation/FinProductQuotation002",
                    "98%", "95%", data,function(){table.reload();});
            
        });*/
    });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
    	//发起流程
    	var object = {selectedItem: [data], operateType:"delete", finprodMarketId : data.finprodMarketId, effDate : data.effDate};
    	data.operateType = 'delete';
    	Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finproductquotation/FinProductQuotation001/check', data, function(result) {
    		Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
    			WorkFlow.doDeleteList(basePath + 'invest/product/messageadjust/finproductquotation/FinProductQuotation001/delete', object, "FinProductQuotation001_btn_delete", "FinProductQuotation002");
    		});
    	});
//        table.removeRow(data);
    });
    
	// 发送状态link页面，各系统酌情使用
	table.bind('click','.sendStatusLink', function(row) {
		console.log(row.finprodId);
       var label='';
       var pagePath='';
       pagePath = "invest/product/messageadjust/finproductquotation/FinProductQuotation006/init";
       Joyin.dialog(JoyinUtil.getLabel(label), basePath + pagePath,
           "95%", "90%", {
               pageModel: PAGE_MODEL_REFERENCE,
               finprodId: row.finprodId
           });
	});
    
    /**
     * 发送销售系统接口
     */
    var sendProductQuotationToSale = function(){
    	if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
            //判断是否选中元素
        	if( selArr != null &&  selArr.length > 0 ){
        		var data = {};
        		data.selectedItem = selArr;
        		data.operateType = 'sendProductQuotation';
                Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finproductquotation/FinProductQuotation001/sendProductQuotationToSale', data,
            		function(result) {
                		var cashIdStr = "";
                		$.each(selArr, function(idx,v) {
                			cashIdStr = cashIdStr + v.cashId + ",";
                		});
                	}
                );
            }else{
            	Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
        }else{
        	Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
        	return;
        }
    }
    //导出
    var exportExcel = function () {
        Joyin.exportExcel('export', getSearchParams());
    };

    /**
     * 弹出新建画面
     * @returns
     */
    function showDialog() {
        Joyin.dialog(JoyinUtil.getLabel("FinProductQuotation002.new"),
                JoyinUtil.getRealPath("invest/product/messageadjust/finproductquotation/FinProductQuotation002/init"),
                "90%", "80%",{pageModel : PAGE_MODEL_NEW},function(){table.reload();}
        );
    }
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
    	//查询
    	$('#FinProductQuotation001_btn_search').click(function(){
    		table.load();
    	});
    	
    	$('#FinProductQuotation001_btn_delete').click(function(){
    		if(table!=null && table!=''){
        		var tableData = table.getAllCheckedRows();
            }
        	if( ! tableData || tableData.length==0){
        		 Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                 return;
            }
        	 var object = {selectedItem: tableData, operateType:"delete"};
             Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finproductquotation/FinProductQuotation001/check', object, function(result) {
                 Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                     WorkFlow.doDeleteList(basePath + 'invest/product/messageadjust/finproductquotation/FinProductQuotation001/delete', object, 
                             "FinProductQuotation001_btn_delete","FinProductQuotation002");
                 });
             });
        	
    	});
    	//
        $('#FinProductQuotation001_btn_add').click(showDialog);
        //导出
        $('#FinProductQuotation001_btn_export').click(function() {
            exportExcel();
        });
        $('#FinProductQuotation001_btn_sendSale').click(function() {
        	sendProductQuotationToSale();
        });
    });
}());