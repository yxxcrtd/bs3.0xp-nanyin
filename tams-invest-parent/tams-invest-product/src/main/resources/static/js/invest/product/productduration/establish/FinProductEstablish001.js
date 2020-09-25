(function(){
	
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var param = {};
    	var finprodId = $('#FinProductEstablish001_finprodId').val();
    	if(finprodId){
    		param.finprodId = finprodId.join(",");
    	}
    	var ptlId = $('#FinProductEstablish001_ptlId').val();
    	if(ptlId){
    		param.ptlId = ptlId.join(",");
    	}
    	param.vdateStart = $('#FinProductEstablish001_vdate').startDate();
    	param.vdateEnd = $('#FinProductEstablish001_vdate').endDate();
    	param.effectStatus = $('#FinProductEstablish001_effectStatus').val();
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
            Joyin.dialog(JoyinUtil.getLabel("FinProductEstablish002.reference"),
                    JoyinUtil.getRealPath("invest/product/productduration/establish/FinProductEstablish002/init"),
                    "98%", "95%",data,function(){table.reload();}
            );
        },
        
        cols:[
             {field:'effectStatus', visible : false}
            , {field:'effectStatusStr', title: 'FinProductEstablish001.table.effectStatus', sortField:'effectStatus', width:100}
            ,{field:'bussId', title: 'FinProductEstablish001.table.bussId', width:200}
//            ,{field:'ptlId', title: 'FinProductEstablish001.table.ptlId', width:130}
			,{field:'finprodMarketId', title: 'FinProductEstablish001.table.finprodMarketId', width:180,
				render : function(data, type, row, meta) {
					return '<a  href="javascript:;" onclick="FinProductCommon.showProdDetail(\''
							+ row.finprodId
							+ '\')" class="pro-link" style="cursor: pointer;">'
							+ row.finprodMarketId + '</a>';
				}
			}
			,{field:'finprodAbbr', title: 'FinProductEstablish001.table.finprodAbbr', width:180}
			,{field:'dealTypeStr',sortField: 'dealType', title: 'FinProductEstablish001.table.ps', width:100}
//			,{field:'saleDepartment', title: 'FinProductEstablish001.table.saleDepartment', sortField:'saleDepartment', width:150}
			,{field:'sonId', title: 'FinProductEstablish001.table.sonId', width:100,sortable:false}
			,{field:'vdateStr', title: 'FinProductEstablish001.table.vdate', sortField:'vdate', width:140}
			,{field:'mdateStr', title: 'FinProductEstablish001.table.mdate', sortField:'mdate', width:140}
			,{field:'currentSubscribe', title: 'FinProductEstablish001.table.currentSubscribe', type:'money', width:180}
			//update by leipeng 20190625  【收益型产品认申购管理】查询列表缺少‘子产品号’，去掉‘本期起息金额’字段。
			/*,{field:'arrivalCapital', title: 'FinProductEstablish001.table.arrivalCapital', type:'money', width:180}*/
			,{field:'ccyStr', title: 'FinProductEstablish001.table.ccy', sortField:'ccy', width:160}
			,{field:'finprodId', visible : false}
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
     * 新建
     */
    var openNew = function(){
        Joyin.dialog(JoyinUtil.getLabel("FinProductEstablish002.new"),
        		JoyinUtil.getRealPath("invest/product/productduration/establish/FinProductEstablish002/init"),
                "98%", "95%",{pageModel : PAGE_MODEL_NEW},function(){table.reload();}
                );
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
        		data.operateType = 'confirm';
        		data.pageModel = PAGE_MODEL_NEW;
        		Joyin.dialog(JoyinUtil.getLabel("FinProductEstablish002"),
                        JoyinUtil.getRealPath("invest/product/productduration/establish/FinProductEstablish002/init"),
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
                Joyin.ajaxJson(basePath + 'invest/product/productduration/establish/FinProductEstablish001/check', data, function(result) {
                	Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                    result = result.replace(/\s+/g,"");
                    if (!result) {
                        result = '无';
                    }
                        data.revokeReason = result;
                        data.tradeId = data.bussId;
                        var finProductInfoDto = {};
                        finProductInfoDto.vdate = data.vdateStr;
                        finProductInfoDto.finprodMakretId = data.finprodMarketId;
                        data.finProductInfoDto = finProductInfoDto;
                        WorkFlow.doRevokeList(basePath + 'invest/product/productduration/establish/FinProductEstablish001/doRevoke', data, "FinProductEstablish001_btn_revoke", "FinProductEstablish002");
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
        $('#FinProductEstablish001_btn_search').click(function() { 
            table.load();
        });
        $('#FinProductEstablish001_btn_export').click(function() {
        	exportExcel();
        });
        $('#FinProductEstablish001_btn_add').click(function() {
        	openNew();
        });
        $('#FinProductEstablish001_btn_confirm').click(function() {
        	confirm();
        });
        $('#FinProductEstablish001_btn_revoke').click(function() {
        	revoke();
        });
	});
}());