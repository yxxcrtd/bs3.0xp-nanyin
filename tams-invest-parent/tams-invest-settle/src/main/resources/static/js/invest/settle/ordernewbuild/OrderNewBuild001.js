(function(){
	
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var param = $.serializeToObject('#searchForm');
    	if ($("#portfolioId").val()) {
    		param.portfolioId = $("#portfolioId").val().join(",");
        }
        if ($("#prdId").val()) {
        	param.prdId = $("#prdId").val().join(",");
        } 
        if ($("#busiType").val()) {
        	param.busiType = $("#busiType").val().join(",");
        } 
        if ($("#settleDate").val()) {
        	param.settleDateStart = $('#settleDate').startDate();
        	param.settleDateEnd = $('#settleDate').endDate();
        }
        param.commonBusiType = 'PTL15';
    	
    	return param;
    };
    // 表格的列
    var tabCols = [
    	{field: 'actOrderId', title: 'OrderNewBuild002.orderId', width: '10%', sortable: false, render: function (data, type, row, meta) {
            return "<a class='pro-link' style='cursor: pointer;' " +
                "href='javascript:openReferce(\"" + data + "\")'>" + data + "</a>";
        	}
    	}
    	,{field:'busiTypeStr',title: 'OrderNewBuild002.busiType',width:'10%',sortField:'busiType'}
    	,{field:'busiType',visible : false}
    	,{field:'orderStatus',visible : false}
        ,{field:'payTypeStr', title: 'OrderNewBuild001.payType', width:'10%', sortField:'payType'}
        ,{field:'payType',visible : false}
        ,{field:'portfolioId', title: 'OrderNewBuild001.portfolioId', width:'10%',sortField:'portfolioId'}
        ,{field:'prdId', title: 'OrderNewBuild001.prdId', width:'10%',sortable:'prdId'}
        ,{field:'settleDateStr', type:'date',title: 'OrderNewBuild001.settleDate', width:'10%',sortField:'settleDate'}
		,{field:'settleAmt', title: 'OrderNewBuild001.settleAmt', width:'10%',sortField:'settleAmt',type:'number'}
		,{field:'sendDateStr', title: 'OrderNewBuild001.sendDate', width:'10%',sortField:'sendDate'}
		,{field:'ccyStr', title: 'OrderNewBuild002.ccy', width:'10%',sortField:'ccy'}
		,{field:'ccy',visible : false}
		,{field:'sendStatusStr', title: 'OrderNewBuild001.sendStatus', width:'10%',sortField:'sendStatus'}
		,{field:'sendStatus',visible : false}
		,{field:'orderRecStatusStr', title: 'OrderNewBuild001.orderRecStatus', width:'10%',sortField:'orderRecStatus'}
		,{field:'orderRecStatus',visible : false}
		,{field:'orderRetStatusStr', title: 'OrderNewBuild001.orderRetStatus', width:'10%',sortField:'orderRetStatus'}
		,{field:'orderRetStatus',visible : false}
        	
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
    	
		cols: tabCols,
	});
    
    //撤销
    var revoke = function(){
    	if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();//判断表格不为空，然后获取选择的数据
        }
        if (!selArr || selArr.length == 0) {//判断是否选择，如果没有则报错COMMON_E_0002
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }else if(selArr.length > 1){
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
            return;
        }else if(selArr[0].orderStatus == orderStatus_revoke){
            Joyin.error(JoyinUtil.getLabel("OrderNewBuild001.revoke.orderStatus"));
            return;
        }else if(selArr[0].sendStatus == sendStatus_success){
        	Joyin.error(JoyinUtil.getLabel("OrderNewBuild001.revoke.sendStatus"));
            return;
        }
        var data = selArr[0];
        data.operateType = 'revoke';
        data.pageModel = PAGE_MODEL_EDIT;
        data.pageId = "OrderNewBuild002";
        Joyin.ajaxJson(basePath + 'invest/settle/ordernewbuild/OrderNewBuild001/check', data, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("OrderNewBuild001.revoke")), function(){
                WorkFlow.doRevokeList(basePath + 'invest/settle/ordernewbuild/OrderNewBuild001/revoke', data, "OrderNewBuild001_btn_revoke", "OrderNewBuild002");
            });
        });
    }
    
    //指令发送
    var send = function(){
    	if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();//判断表格不为空，然后获取选择的数据
        }
        if (!selArr || selArr.length == 0) {//判断是否选择，如果没有则报错COMMON_E_0002
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }else if(selArr.length > 1){
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
            return;
        }else if (selArr[0].sendStatus == sendStatus_success){
        	Joyin.error(JoyinUtil.getLabel("OrderNewBuild001.send.error"));
            return;
        }
        var object = selArr[0];
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("OrderNewBuild001.send")), function(){
        	Joyin.ajaxJson(basePath + 'invest/settle/ordernewbuild/OrderNewBuild001/send', object, function(result) {
    			table.reload();
    		});
        });
        
    }
    
    //指令查证
    var verify = function() {
    	if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();//判断表格不为空，然后获取选择的数据
        }
        if (!selArr || selArr.length == 0) {//判断是否选择，如果没有则报错COMMON_E_0002
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }else if(selArr.length > 1){
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
            return;
        }
        var object = selArr[0];
        Joyin.ajaxJson(basePath + 'invest/settle/ordernewbuild/OrderNewBuild001/verify', object, function(result) {
			table.reload();
		});
    }
    
    /**
     * 导出
     */
    var exportExcel = function(){
        Joyin.exportExcel('export',getSearchParams());
    };
    
    //新建
    var openNew = function(){
        Joyin.dialog(JoyinUtil.getLabel("OrderNewBuild002"),
                JoyinUtil.getRealPath("invest/settle/ordernewbuild/OrderNewBuild002/init"),
                "98%", "95%",{pageModel : PAGE_MODEL_NEW},function(){table.reload();}
        );
    }
    
	$(function() {
		//查询
		$('#OrderNewBuild001_btn_search').click(function() { 
            table.load();
        });
        //新建
        $('#OrderNewBuild001_btn_add').click(function() {
        	openNew();
        });
        //撤销
        $('#OrderNewBuild001_btn_revoke').click(function(){
        	revoke();
        })
        //指令发送
        $('#OrderNewBuild001_btn_resend').click(function(){
        	send();
        })
        //指令查证
        $('#OrderNewBuild001_btn_verify').click(function(){
        	verify();
        })
        //导出
        $('#OrderNewBuild001_btn_export').click(function() {
        	exportExcel();
        });
        
	});
}());


//详情页
var openReferce = function(data){
	var obj = {
		pageModel : PAGE_MODEL_REFERENCE,
		actOrderId : data
	}
    Joyin.dialog(JoyinUtil.getLabel("OrderNewBuild002.reference"),
    	JoyinUtil.getRealPath("invest/settle/ordernewbuild/OrderNewBuild002/init"),
        	"98%", "95%",obj,function(){}
    	);
}