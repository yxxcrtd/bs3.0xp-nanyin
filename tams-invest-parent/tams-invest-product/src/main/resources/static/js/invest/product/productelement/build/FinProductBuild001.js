(function(){
	
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var param = $.serializeToObject('#searchForm');
        //OPEX-856 【净值化产品管理】“投资性质”填入查询出结果后，然后清空这个筛选框。
        // 再次查询，查询结果还是之前填入“投资性质”时的情况。 guyunfei  20200214 start
    	if(param.prodId){
    		param.prodId = param.prodId.join(",");
    	}else{
            param.prodId = null;
		}
    	if(param.groupId){
    		param.groupId = param.groupId.join(",");
    	}else{
            param.groupId = null;
		}
    	if(param.targetCustomType){
    	    param.targetCustomType = param.targetCustomType.join(",");
    	}else{
            param.targetCustomType = null;
		}
    	if(param.investNature){
    	    param.investNature = param.investNature.join(",");
    	}else{
            param.investNature = null;
		}
    	if(param.operationType){
    	    param.operationType = param.operationType.join(",");
    	}else{
            param.operationType = null;
        }
    	if(param.issueStatus){
    	    param.issueStatus = param.issueStatus.join(",");
    	}else{
            param.issueStatus = null;
        }
    	if(param.prodRegistCode){
            param.prodRegistCode = param.prodRegistCode.join(",");
        }else{
            param.prodRegistCode = null;
		}
    	param.vdateStart = $('#FinProductBuild001_date_vdate').startDate();
    	param.vdateEnd = $('#FinProductBuild001_date_vdate').endDate();
    	param.mdateStart = $('#FinProductBuild001_date_mdate').startDate();
    	param.mdateEnd = $('#FinProductBuild001_date_mdate').endDate();
    	param.raiseStrDateStart = $('#FinProductBuild001_date_raiseStrDate').startDate();
    	param.raiseStrDateEnd = $('#FinProductBuild001_date_raiseStrDate').endDate();
    	param.raiseEndDateStart = $('#FinProductBuild001_date_raiseEndDate').startDate();
    	param.raiseEndDateEnd = $('#FinProductBuild001_date_raiseEndDate').endDate();
    	if(param.profitFlag){
            param.profitFlag = param.profitFlag.join(',');
		}else{
            param.profitFlag = null;
		}
		if(param.issueType){
            param.issueType = param.issueType.join(',');
		}else{
            param.issueType = null;
		}
        //OPEX-856 【净值化产品管理】“投资性质”填入查询出结果后，然后清空这个筛选框。
        // 再次查询，查询结果还是之前填入“投资性质”时的情况。 guyunfei  20200214 end
    	return param;
    };
    // 表格的列
    var tabCols = [
        {field:'effectStatus', title: 'FinProductBuild001.table.status', width:100, visible:false}
        ,{field:'sendStatusStr', title: 'FinProductBuild001.table.sendStatus', width:'100', visible: isSendStatusTableShow === YES,
			render : function(data, type, row, meta) {
			 	if (isSendStatusTableLinkShow === 'N') {
			 		return row.sendStatusStr;
				}
				return "<a  class='pro-link sendStatusLink' style='cursor: pointer;'>"+row.sendStatusStr+"</ a>";
			},sortField:"sendStatus"
		}
        ,{field:'sendRemark',sortField:'sendRemark', title: 'FinNetValueProductBuild001.table.sendStatusRemark', width:130, visible: isSendStatusTableShow === YES}
        ,{field:'issueStatusStr', title: 'FinProductBuild001.table.issueStatus', width:100, sortField:'issueStatus'}
		,{field:'portfolioId', title: 'FinProductBuild001.table.groupId', width:100}
		,{field:'finprodMarketId', title: 'FinProductBuild001.table.prodId', width:130}
		//,{field:'finprodName', title: 'FinProductBuild001.table.prodName', width:200}
		,{field:'finprodAbbr', title: 'FinProductBuild001.table.prodAbbr', width:160}
		,{field:'dealModeStr', title: 'FinProductBuild001.table.handleMode', width:100, sortField:'dealMode',visible:false}
		//,{field:'profitTypeStr', sortField:'profitType', title: 'FinProductBuild001.table.profitType', width:100}
		,{field:'issueTypeStr', sortField:'issueType', title: 'FinProductBuild001.table.issueType', width:100}
		//,{field:'rateTypeStr',sortField:'rateType', title: 'FinProductBuild001.table.rateType', width:100}
		//,{field:'yield', title: 'FinProductBuild001.table.yield', width:120}
		,{field:'repInvestorType', sortField:'repInvestorType', title: 'FinProductBuild001.table.targetCustomType', width:130}
		//FinProductBuild001.targetCustomer1=目标客户一级分类 add by libaisong
//		,{field:'targetCustomer1Str', sortField:'targetCustomer1', title: 'FinProductBuild001.targetCustomer1', width:130}
		//FinProductBuild001.targetCustomer2=目标客户二级分类 add by libaisong
//		,{field:'targetCustomer2Str', sortField:'targetCustomer2', title: 'FinProductBuild001.targetCustomer2', width:130}
		,{field:'vdate', title: 'FinProductBuild001.table.vdate', type:'date'}
		,{field:'mdate', title: 'FinProductBuild001.table.mdate', type:'date'}
		,{field:'firstSaleVdate', title: 'FinProductBuild001.table.raiseStrDate', type:'date'}
		,{field:'firstSaleMdate', title: 'FinProductBuild001.table.raiseEndDate', type:'date'}
		,{field:'toffStart', title: 'FinProductBuild001.table.subscribePoint', width:100,type:'money'}
		,{field:'saleMax', title: 'FinProductBuild001.table.iraseMax', width:100,type:'money'}
		,{field:'saleMin', title: 'FinProductBuild001.table.raiseLimit', width:100,type:'money'}
		,{field:'prodRegistCode', title: 'FinProductBuild001.table.prodRegistCode', width:100}
		,{field:'operationTypeStr',sortField:'operationType', title: 'FinProductBuild001.table.operationType', width:150}
		,{field:'investNatureStr',sortField:'investNature', title: 'FinProductBuild001.table.investNature', width:150}
		,{field:'saleDepartment',sortField:'saleDepartment', title: 'FinProductBuild001.table.saleDepartment', width:150,visible:false}
		,{field:'saleChannel',sortField:'saleChannel', title: 'FinProductBuild001.table.saleChannel', width:150}
		,{field:'profitFlagStr',sortField:'profitFlag', title: 'FinProductBuild001.table.profitFlag', width:150}
		,{field:'repRiskLevelStr',sortField:'repRiskLevel', title: 'FinProductBuild001.table.repRiskLevel', width:150}
		,{field:'ccyStr',sortField:'ccy', title: 'FinProductBuild001.table.ccy', width:100}
		,{field:'consignmentFlagStr',sortField:'consignmentFlag', title: 'FinProductBuild001.table.consignmentFlag', width:100}
		,{field:'baseRateTypeStr',sortField:'baseRateType', title: 'FinProductBuild001.table.baseRateType', width:100,visible:false}
		,{field:'rate', title: 'FinProductBuild001.table.rate', width:100,visible:false}
		,{field:'termDays', title: 'FinProductBuild001.table.termDays', width:100}
		,{field:'lowestAmt', title: 'FinProductBuild001.table.lowestAmt', width:100}
		,{field:'issueAmt', title: 'FinProductBuild001.table.issueAmt', width:100}
		,{field:'prodSeriesStr', title: 'FinProductBuild001.table.prodSeries', width:100,sortField:'prodSeries'}
        <!--ZGBUG-280产品端查询和新建页面有邮储银行的字段，希望可以删掉 guyunfei 20200310 start-->
		/*,{field:'prdDesignId',sortField:'prdDesignId',  title: 'FinProductBuild001.prdDesignId', width:150}
		,{field:'prdDesignName',sortField:'prdDesignName',  title: 'FinProductBuild001.prdDesignName', width:150}*/
        <!--ZGBUG-280产品端查询和新建页面有邮储银行的字段，希望可以删掉 guyunfei 20200310 end-->
		,{field:'prodClass', title: 'FinProductBuild001.table.prodClass', width:100}
		,{field:'rateType', title: 'FinProductBuild001.table.rateType', width:100}
		,{field:'yield', title: 'FinProductBuild001.table.yield', width:150}
		//,{field:'issueStatusRemark', title: 'FinProductBuild001.table.issueStatusRemark', width:150}
		,{field:'issueStatus', visible:false}
        ,{field:'finprodId', visible:false}
        ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
    ];
    //修改：页面列表加载不出来，tableCols is not defined
//    if (isSendStatusTableShow === 'N') {
//        tabCols[1].visible = false;
//	}
//	if (isSendRemarkTableShow === 'N') {
//	    tabCols[2].visible = false;
//	}
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
            Joyin.dialog(JoyinUtil.getLabel("FinProductBuild002.reference"),
                    JoyinUtil.getRealPath("invest/product/productelement/build/FinProductBuild002/init"),
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
       pagePath = "invest/product/productelement/build/FinProductBuild004/init";
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
        Joyin.dialog(JoyinUtil.getLabel("FinProductBuild002.new"),
                JoyinUtil.getRealPath("invest/product/productelement/build/FinProductBuild002/init"),
                "98%", "95%",{pageModel : PAGE_MODEL_NEW},function(){table.reload();}
        );
    }
    
    /**
     * 发行状态管理
     */
    var issueStatusMan = function(){
    	if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
            //判断是否选中元素
        	if( selArr != null &&  selArr.length > 0 ){
        		var data = {};
        		data.selectedItem = selArr;
        		data.operateType = 'issueStatus';
                Joyin.ajaxJson(basePath + 'invest/product/productelement/build/FinProductBuild001/check', data, function(result) {
                	var finprodIdStr = "";
            		$.each(selArr, function(idx,v) {
            			finprodIdStr = finprodIdStr + v.finprodId + ",";
            	    });
                	// 跳转发行状态管理页面
            		Joyin.dialog(JoyinUtil.getLabel("FinProductBuild003"),
                            JoyinUtil.getRealPath("invest/product/productelement/build/FinProductBuild003/init"),
                            "70%", "80%",{finprodIdStr : finprodIdStr.substring(0, finprodIdStr.length - 1)},function(){table.reload();}
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
    /**
     * 发送收益型产品信息到接口
     */
    var sendProductBuildInfoToSale = function() {
    	if (table != null && table != ''){
            var selArr = table.getAllCheckedRows();
            //判断是否选中元素
        	if( selArr != null &&  selArr.length > 0 ){
        		var data = {};
        		data.selectedItem = selArr;
        		data.operateType = 'sendProductBuildInfo';
                Joyin.ajaxJson(basePath + 'invest/product/productelement/build/FinProductBuild001/sendProductBuildInfoToSale', data,
            		function(result) {
                		var finprodIdStr = "";
                		$.each(selArr, function(idx,v) {
                			finprodIdStr = finprodIdStr + v.finprodId + ",";
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
	
	$(function() {
        $('#FinProductBuild001_btn_search').click(function() { 
            table.load();
        });
        $('#FinProductBuild001_btn_export').click(function() {
        	exportExcel();
        });
        $('#FinProductBuild001_btn_add').click(function() {
        	openNew();
        });
        $('#FinProductBuild001_btn_confirm').click(function() {
        	confirm();
        });
        $('#FinProductBuild001_btn_issueStatusMan').click(function() {
        	issueStatusMan();
        });
        $('#FinProductBuild001_btn_sendSale').click(function() {
        	sendProductBuildInfoToSale();
        });  
	});
}());