(function(){
	
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var param = {};
    	var finprodId = $('#FinProductExpire001_finprodId').val();
    	if(finprodId){
    		param.finprodId = finprodId.join(",");
    	}
    	var ptlId = $('#FinProductExpire001_ptlId').val();
    	if(ptlId){
    		param.ptlId = ptlId.join(",");
    	}
//    	param.payDateStart = $('#FinProductExpire001_payDate').startDate();
//    	param.payDateEnd = $('#FinProductExpire001_payDate').endDate();
    	param.mdateStart = $('#FinProductExpire001_mdate').startDate();
		param.mdateEnd = $('#FinProductExpire001_mdate').endDate();
    	param.effectStatus = $('#FinProductExpire001_effectStatus').val();
        return param;
    };
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
            Joyin.dialog(JoyinUtil.getLabel("FinProductExpire002"),
                    JoyinUtil.getRealPath("invest/product/productduration/expire/FinProductExpire002/init"),
                    "98%", "95%",data,function(){table.reload();}
            );
        },
        
        cols:[
             {field:'effectStatus', visible : false}
			,{field:'effectStatusStr', title: 'FinProductExpire001.table.effectStatus', sortField:'effectStatus', width:100}
			,{field:'busiNo', title: 'FinProductExpire001.table.busiNo', width:170}
			,{field:'ptlId', title: 'FinProductExpire001.table.ptlId', width:130}
			,{field:'finprodMarketId', title: 'FinProductExpire001.table.prodMarketId', width:100,
				render : function(data, type, row, meta) {
					if (row.finprodId && row.finprodId != '' && typeof row.finprodId != 'undefined'){
						return '<a  href="javascript:;" onclick="FinProductCommon.showProdDetail(\''
						+ row.finprodId
						+ '\')" class="pro-link" style="cursor: pointer;">'
						+ row.finprodMarketId + '</a>';
					}else{
						return row.finprodMarketId;
					}
				}
			}
			,{field:'finprodAbbr', title: 'FinProductExpire001.table.prodAbbr', width:100}
			,{field:'dealTypeStr',sortField: 'dealType', title: 'FinProductExpire001.table.ps', width:100}
//			,{field:'saleDepartmentStr', title: 'FinProductExpire001.table.saleDepartment', sortField:'saleDepartment', width:130}
			,{field:'branch', title: 'FinProductExpire001.table.branch', width:100}
			,{field:'vdate', title: 'FinProductExpire001.table.startDate', type:'date', width:100,visible : false}
			,{field:'mdate', title: 'FinProductExpire001.table.endDate', type:'date', width:100}
			,{field:'prinamt', title: 'FinProductExpire001.table.prinamt', type:'number', width:100}
			//,{field:'actYield', title: 'FinProductExpire001.table.actYield', type:'number', width:100}
			,{field:'actCusProfit', title: 'FinProductExpire001.table.actCusProfit', type:'number', width:100}
			//,{field:'actSaleFee', title: 'FinProductExpire001.table.actSaleFee', type:'number', width:100}
			//,{field:'actTrustFee', title: 'FinProductExpire001.table.actTrustFee', type:'number', width:100}
			,{field:'ccyStr', title: 'FinProductExpire001.table.ccy', sortField:'ccy', width:130}
			,{field:'finprodId', visible : false}
			,{field:'finprodName', visible : false}
			,{field:'dealMode', visible : false}
			,{field:'saleDepartment', visible : false}
			,{field:'ccy', visible : false}
			,{field:'payDateStr', visible : false}
			,{field:'workflowId', visible : false}
        ]
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = 'edit';
        Joyin.ajaxJson(basePath + 'invest/product/productelement/build/FinProductBuild001/check', data, function(result) {
        	Joyin.dialog(JoyinUtil.getLabel("FinProductBuild002.edit"),
                    basePath + "invest/product/productelement/build/FinProductBuild002/init",
                    "98%", "95%", {finprodId : data.finprodId, pageModel : PAGE_MODEL_EDIT},function(){table.reload();});
            
        });
    });
    
    /**
     * 弹出新建画面
     * @returns
     */
    function showDialog() {
        Joyin.dialog(JoyinUtil.getLabel("FinProductExpire001.new"),
                JoyinUtil.getRealPath("invest/product/productduration/expire/FinProductExpire002/init"),
                "90%", "95%",{pageModel : PAGE_MODEL_NEW},function(){table.reload();}
        );
    }
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
    	var object = {selectedItem: [data], operateType:"delete", finprodId : data.finprodId, finprodMarketId : data.finprodMarketId, finprodAbbr : data.finprodAbbr, vdate : data.vdate};
    	data.operateType = 'delete';
        Joyin.ajaxJson(basePath + 'invest/product/productelement/build/FinProductBuild001/check', data, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/product/productelement/build/FinProductBuild001/delete', object, "FinProductBuild001_btn_delete", "FinProductBuild002");
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
     * 确认
     */
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
        		data.pageModel = PAGE_MODEL_NEW;
        		Joyin.dialog(JoyinUtil.getLabel("FinProductExpire002"),
                        JoyinUtil.getRealPath("invest/product/productduration/expire/FinProductExpire002/init"),
                        "98%", "95%", data, function(){table.reload();}
                );
            }else{
            	Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
        }else{
        	Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
        	return;
        }
    };
    
    /**
     * 撤销
     */
    var revoke = function(){
    	if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
            //判断是否选中元素
        	if( selArr != null &&  selArr.length > 0 ){
        		if (selArr.length != 1){
        			Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                    return;
        		}
        		var data = selArr[0];
        		data.operateType = 'revoke';
        		data.pageModel = PAGE_MODEL_REFERENCE;
                Joyin.ajaxJson(basePath + 'invest/product/productduration/expire/FinProductExpire001/check', data, function(result) {
                	Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                    result = result.replace(/\s+/g,"");
                    if (!result) {
                        result = '无';
                    }
                        data.revokeReason = result;
                        // update by dgf 20200117  赎回到期撤销在流程中显示错误
                        WorkFlow.doRevokeList(basePath + 'invest/product/productduration/expire/FinProductExpire001/doRevoke', data, "FinProductExpire001_btn_revoke", "FinProductExpire002");
                    });
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
    
    
	$(function() {
        $('#FinProductExpire001_btn_search').click(function() { 
            table.load();
        });
        $('#FinProductExpire001_btn_export').click(function() {
        	exportExcel();
        });
        /*$('#FinProductExpire001_btn_confirm').click(function() {
        	confirm();
        });*/
        $('#FinProductExpire001_btn_add').click(showDialog);
        $('#FinProductExpire001_btn_revoke').click(function() {
        	revoke();
        });
	});
}());