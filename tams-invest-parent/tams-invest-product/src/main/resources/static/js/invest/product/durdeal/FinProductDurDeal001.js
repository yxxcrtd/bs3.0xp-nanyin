/**
 * 产品存续期交易查询
 */
(function() {
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        var pageParam =  {
             portfolioId: $('#portfolioId').val()
            ,prodId: $('#prodId').val()
            ,dealModeStr: $('#dealMode').val()
            ,cashDateBegin: $('#cashDate').startDate()
            ,cashDateEnd: $('#cashDate').endDate()
            ,confirmDateBegin: $('#confirmDate').startDate()
            ,confirmDateEnd: $('#confirmDate').endDate()
            ,dealType:$('#dealType').val()
            ,effectStatus:$('#effectStatus').val()
            ,ccy:$('#ccy').val()
            ,tableId: "detail"
        };
        if ($("#portfolioId").val()) {
            pageParam.portfolioId = $("#portfolioId").val().join(",");
        } else {
            pageParam.portfolioId = null;
        }
        if ($("#prodId").val()) {
            pageParam.prodId = $("#prodId").val().join(",");
        } else {
            pageParam.prodId = null;
        }
        if ($('#dealMode').val()) {
        	pageParam.dealMode = $("#dealMode").val();
        }
        if ($('#dataTable').parent().attr("class") == "layui-tab-item") {
        	pageParam.tableId = "detail";
        }else {
        	pageParam.tableId = "total";
        } 
        if ($("#dealType").val()) {
            pageParam.dealType = $("#dealType").val();
        } else {
            pageParam.dealType = null;
        }
        if ($("#effectStatus").val()) {
            pageParam.effectStatus = $("#effectStatus").val();
        } else {
            pageParam.effectStatus = null;
        }
        if ($("#ccy").val()) {
            pageParam.ccy = $("#ccy").val();
        } else {
            pageParam.ccy = null;
        } 
        // add by wangyang1 【吉林银行】 增加 产品经理  2019年9月29日 start
        if ($("#prodManager").val()) {
            pageParam.prodManager = $("#prodManager").val();
        } else {
            pageParam.prodManager = null;
        }  
        // add by wangyang1 【吉林银行】 增加 产品经理  end 
        return pageParam;
    };

    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        rowno:true,
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        height: 'full-' + 220,
//        dblclick:function(table, data) {
//            data.pageModel = PAGE_MODEL_REFERENCE;
//            Joyin.dialog(JoyinUtil.getLabel("FinProductDurDeal002"),
//                    JoyinUtil.getRealPath("invest/product/durdeal/FinProductDurDeal002/init"),
//                    "99%", "99%", data);
//        },
        cols:[
            {field:'portfolioId', sortField: 'portfolioId',width:120, title: 'FinProductDurDeal001.table.portfolioId'}
            ,{field:'marketId', sortField: 'marketId',width:120, title: 'FinProductDurDeal001.table.prodId'}
            ,{field:'dealModeStr', sortField: 'dealMode', width:200, title: 'FinProductDurDeal001.table.dealMode'}
//            ,{field:'saleDepartmentText', sortField: 'saleDepartment',  width:120, title: 'FinProductDurDeal001.table.saleDepartment'}
            ,{field:'confirmDate', sortField: 'confirmDate',width:120, title: 'FinProductDurDeal001.table.confirmDate', type: 'date'}
            //,{field:'cashDate', width:120, title: 'FinProductDurDeal001.table.cashDate', type: 'date'}
            ,{field:'tdyTotalQty', width:130, title: 'FinProductDurDeal001.table.tdyTotalQty', type: 'money',sortable: false}
            ,{field:'tdyBuyMoney', width:130, title: 'FinProductDurDeal001.table.tdyBuyMoney', type: 'money'}
            ,{field:'tdyBuyQty', width:130, title: 'FinProductDurDeal001.table.tdyBuyQty', type: 'money'}
            ,{field:'tdyRedMoney', width:130, title: 'FinProductDurDeal001.table.tdyRedMoney', type: 'money'}
            ,{field:'tdyRedQty', width:130, title: 'FinProductDurDeal001.table.tdyRedQty', type: 'money'}
            ,{field:'tdyCarriedInterest', width:130, title: 'FinProductDurDeal001.table.tdyCarriedInterest', type: 'money'}
            ,{field:'tdyCarriedMoney', width:140, title: 'FinProductDurDeal001.table.tdyCarriedMoney', type: 'money'}
            ,{field:'tdyCarriedQty', width:140, title: 'FinProductDurDeal001.table.tdyCarriedQty', type: 'money'}
            ,{field:'tdyForceIncreaseMoney', width:130, title: 'FinProductDurDeal001.table.tdyForceIncreaseMoney', type: 'money'}
            ,{field:'tdyForceIncreaseQty', width:130, title: 'FinProductDurDeal001.table.tdyForceIncreaseQty', type: 'money'}
            ,{field:'tdyForceReduceMoney', width:130, title: 'FinProductDurDeal001.table.tdyForceReduceMoney', type: 'money'}
            ,{field:'tdyForceReduceQty', width:130, title: 'FinProductDurDeal001.table.tdyForceReduceQty', type: 'money'}
            ,{field:'yield', width:200, title: 'FinProductDurDeal001.table.yield', type: 'money', digit: 4,sortable: false}
            ,{field:'ccyStr', sortField: 'ccy', width:130, title: 'FinProductDurDeal001.table.ccy'}
            /*添加 当日净申赎份额和当日净申述金额 查询列 */
            ,{field:'tdyRedemptionQty', width:200, title: '当日净申赎份额',type: 'money',sortable: false}
            ,{field:'tdyRedemptionMoney', width:200, title: '当日净申赎金额',type: 'money',sortable: false}
            ,{field:'prodId', visible : false}
            ,{field:'profitType', visible : false}
            ,{field:'isLay', visible : false}
//            ,{field:'tradeId', visible : false}
//            ,{field: 'operation', title: 'FinProductDurDeal001.table.detail', type: 'action', buttons: ['showDetail'],
//                render: function (data, type, row, meta) {
//                    return "<a class='pro-link showDetail' style='cursor: pointer;'>" + "查看明细" + "</a>";
//                }
//            }
        ],
        checkbox: false

    });

    /**
     * 定义数据表格
     */
    var detailDataTable = $("#detailDataTable").joyintable({
    	rowno:true,
    	ajax: {
            url: 'findDetailPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        height: 'full-' + 220,
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            var isCycle = data.isCycle;
            var profitType = data.profitType;
            var dealType = data.dealType;

            debugger;

            if(profitType=='01' || profitType=='03'){
            	if(profitType=='01'){
            		if(dealType=='C8'){
                		//净值型分红方案确认撤销
            			Joyin.dialog(JoyinUtil.getLabel("FinNetProductBonusScheme003.view"),
                                JoyinUtil.getRealPath("invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme003/init"),
                                "98%", "95%", data,function(){detailDataTable.reload();});
                	}else if(dealType=='C9'){
                		//净值型红利下发撤销
                		Joyin.dialog(JoyinUtil.getLabel("FinNetProductBonusScheme004.view"), 
                                JoyinUtil.getRealPath("invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme004/init"), 
                                "98%", "95%", data,function(){detailDataTable.reload();});
                	}else if(dealType=='C10'){
                		//净值型红利再投撤销
                		Joyin.dialog(JoyinUtil.getLabel("FinNetProductBonusScheme005.view"), 
                                JoyinUtil.getRealPath("invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme005/init"), 
                                "98%", "95%", data,function(){detailDataTable.reload();});
                	}else{
                		Joyin.dialog(JoyinUtil.getLabel("FinNetProductDuration.view"),
                                JoyinUtil.getRealPath("invest/product/productduration/netproductDuration/FinNetProductDuration/init"),
                                "98%", "95%",data,function(){detailDataTable.reload();}
                        );
                	}
            	}else{
            		Joyin.dialog(JoyinUtil.getLabel("FinNetProductDuration.view"),
                            JoyinUtil.getRealPath("invest/product/productduration/netproductDuration/FinNetProductDuration/init"),
                            "98%", "95%",data,function(){detailDataTable.reload();}
                    );
            	}
            	
            	
            }else if(profitType=='02'){
            	if(dealType=='C1'||dealType=='C2'){//收益型产品认购申购
	                Joyin.dialog(JoyinUtil.getLabel("FinProductEstablish002.view"),
	                    JoyinUtil.getRealPath("/invest/product/productduration/establish/FinProductEstablish002/init"),	
	                    "98%", "95%",data,function(){detailDataTable.reload();}
	                );
	            	//alert("请到预期收益产品相关模块操作，暂时未集成");
	            }
            	else if(dealType=='C17'||dealType=='C18'){//收益型产品提前赎回
	                Joyin.dialog(JoyinUtil.getLabel("FinProductAddReduce002.view"),
	                    JoyinUtil.getRealPath("/invest/product/productduration/addreduce/FinProductAddReduce002/init"),
	                    "98%", "95%",data,function(){detailDataTable.reload();}
	                );
	            }
            	else if(dealType=='C7'){//收益型产品中间付息
	                Joyin.dialog(JoyinUtil.getLabel("FinProductPayInt003.view"),
	                    JoyinUtil.getRealPath("/invest/product/payint/FinProductPayInt003/init"),	
	                    "98%", "95%",data,function(){detailDataTable.reload();}
	                );
	            }
            	else if(dealType=='C4'||dealType=='C5'||dealType=='C20'){//收益型产品赎回到期
	                Joyin.dialog(JoyinUtil.getLabel("FinProductExpire002.view"),
	                    JoyinUtil.getRealPath("/invest/product/productduration/expire/FinProductExpire002/init"),	
	                    "98%", "95%",data,function(){detailDataTable.reload();}
	                );
	            }
            }           
        },
        cols:[

             {field:'effectStatusStr', sortField:'effectStatus',width:120, title: 'FinProductDurDeal001.table.effectStatus'}
            ,{field:'marketId', width:120, title: 'FinProductDurDeal001.table.prodId'}
            ,{field:'finprodAbbr', width:120, title: 'FinProductDurDeal001.table.finprodAbbr'}
            ,{field:'confirmDate', width:120, title: 'FinProductDurDeal001.table.confirmDate', type: 'date'}
            ,{field:'cashDate', width:120, title: 'FinProductDurDeal001.table.cashDate', type: 'date'}
            ,{field:'dealTypeStr', sortField: 'dealType', width:120, title: 'FinProductDurDeal001.table.dealType'}
            ,{field:'ccyStr', sortField: 'ccy', width:130, title: 'FinProductDurDeal001.table.ccy'}
            ,{field:'tdyBuyMoney', width:130, title: 'FinProductDurDeal001.table.purAmt', type: 'money'}
            ,{field:'tdyBuyQty', width:130, title: 'FinProductDurDeal001.table.purNum', type: 'money'}
            ,{field:'tdyRedMoney', width:130, title: 'FinProductDurDeal001.table.redAmt', type: 'money'}
            ,{field:'tdyRedQty', width:130, title: 'FinProductDurDeal001.table.redNum', type: 'money'}
            ,{field:'redCost', width:130, title: 'FinProductDurDeal001.table.redCost', type: 'money'}            
            ,{field:'redInt', width:130, title: 'FinProductDurDeal001.table.redInt', type: 'money'}
            ,{field:'bonusCash', width:130, title: 'FinProductDurDeal001.table.bonusCash', type: 'money'}            
            ,{field:'tdyCarriedMoney', width:140, title: 'FinProductDurDeal001.table.bonusShareAmt'}
            ,{field:'tdyCarriedQty', width:140, title: 'FinProductDurDeal001.table.bonusShareNum'}
            ,{field:'tdyForceIncreaseMoney',width:140, title: 'FinProductDurDeal001.table.forceIncreaseMoney'}
            ,{field:'tdyForceIncreaseQty',width:140, title: 'FinProductDurDeal001.table.forceIncreaseQty'}
            ,{field:'tdyForceReduceMoney',width:140, title: 'FinProductDurDeal001.table.forceReduceMoney'}
            ,{field:'tdyForceReduceQty',width:140, title: 'FinProductDurDeal001.table.forceReduceQty'}
            ,{field:'genType', width:200, title: 'FinProductDurDeal001.table.genType'}
            ,{field:'dealRemark', width:200, title: 'FinProductDurDeal001.table.dealRemark'}
            ,{field:'finprodId', visible : false}
            ,{field:'dealType', visible : false}
            ,{field:'portfolioId', visible : false}
            ,{field:'profitType', visible : false}
            ,{field:'isLay', visible : false}
            ,{field:'isCycle', visible : false}
            ,{field:'tradeId', visible : false}
            ,{field: 'operation', title: 'FinProductDurDeal001.table.operation', type: 'action', 
//            	buttons: ['dealRevoke'],
                render: function (data, type, row, meta) {
                	if(purviewElements.indexOf('_manual')!=-1 && row.effectStatus=="01" && row.permitFlag=="01")
                    return "<a class='pro-link manual' style='cursor: pointer;'>" + "撤销" + "</a>";
                }
            }
        ],
        checkbox: false
    });
    
    /**
     * 绑定查看明细事件
     */
//    table.bind('click', '.showDetail', function (data, index) {
//        data.pageModel = PAGE_MODEL_REFERENCE;
//        if (data.cashDate) {
//            data.cashDate = new Date(data.cashDate).format("yyyy-MM-dd");
//        }
//        Joyin.dialog(JoyinUtil.getLabel("FinProductDurDeal002"), 
//                JoyinUtil.getRealPath("invest/product/durdeal/FinProductDurDeal002/init"), "80%", "80%", data);
//    });
    
    /**
     * 撤销界面--根据条件进入不同的交易撤销界面
     */
    detailDataTable.bind('click', '.manual', function (data, index) {
        data.pageModel = PAGE_MODEL_REFERENCE;
        data.operateType = 'revoke';
        var isCycle = data.isCycle;
        var profitType = data.profitType;
        var dealType = data.dealType;
        Joyin.ajaxJson(basePath + 'invest/product/durdeal/FinProductDurDeal001/check', data, function(result) {
            if (profitType == '01' || profitType == '03') {
                if (profitType == '01') {
                    if (dealType == 'C8') {
                        //净值型分红方案确认撤销
                        Joyin.dialog(JoyinUtil.getLabel("FinNetProductBonusScheme003.revoke"),
                            JoyinUtil.getRealPath("invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme003/init"),
                            "98%", "95%", data, function () {
                                detailDataTable.reload();
                            });
                    } else if (dealType == 'C9') {
                        //净值型红利下发撤销
                        Joyin.dialog(JoyinUtil.getLabel("FinNetProductBonusScheme004.revoke"),
                            JoyinUtil.getRealPath("invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme004/init"),
                            "98%", "95%", data, function () {
                                detailDataTable.reload();
                            });
                    } else if (dealType == 'C10') {
                        //净值型红利再投撤销
                        Joyin.dialog(JoyinUtil.getLabel("FinNetProductBonusScheme005.revoke"),
                            JoyinUtil.getRealPath("invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme005/init"),
                            "98%", "95%", data, function () {
                                detailDataTable.reload();
                            });
                    } else {
                        Joyin.dialog(JoyinUtil.getLabel("FinNetProductDuration.revokepage"),
                            JoyinUtil.getRealPath("invest/product/productduration/netproductDuration/FinNetProductDuration/init"),
                            "98%", "95%", data, function () {
                                detailDataTable.reload();
                            }
                        );
                    }
                } else {
                    Joyin.dialog(JoyinUtil.getLabel("FinNetProductDuration.revokepage"),
                        JoyinUtil.getRealPath("invest/product/productduration/netproductDuration/FinNetProductDuration/init"),
                        "98%", "95%", data, function () {
                            detailDataTable.reload();
                        }
                    );
                }


            } else if (profitType == '02') {
                if (dealType == 'C1' || dealType == 'C2') {//收益型产品认购申购撤销
                    Joyin.dialog(JoyinUtil.getLabel("FinProductEstablish002.revoke"),
                        JoyinUtil.getRealPath("/invest/product/productduration/establish/FinProductEstablish002/init"),
                        "98%", "95%", data, function () {
                            detailDataTable.reload();
                        }
                    );
                    //alert("请到预期收益产品相关模块操作，暂时未集成");
                } else if (dealType == 'C17' || dealType == 'C18') {//收益型产品提前赎回撤销
                    Joyin.dialog(JoyinUtil.getLabel("FinProductAddReduce002.revoke"),
                        JoyinUtil.getRealPath("/invest/product/productduration/addreduce/FinProductAddReduce002/init"),
                        "98%", "95%", data, function () {
                            detailDataTable.reload();
                        }
                    );
                } else if (dealType == 'C7') {//收益型产品中间付息撤销
                    Joyin.dialog(JoyinUtil.getLabel("FinProductPayInt003.revoke"),
                        JoyinUtil.getRealPath("/invest/product/payint/FinProductPayInt003/init"),
                        "98%", "95%", data, function () {
                            detailDataTable.reload();
                        }
                    );
                } else if (dealType == 'C4' || dealType == 'C5' || dealType == 'C20') {//收益型产品赎回到期撤销,增加提前终止
                    Joyin.dialog(JoyinUtil.getLabel("FinProductExpire002.revoke"),
                        JoyinUtil.getRealPath("/invest/product/productduration/expire/FinProductExpire002/init"),
                        "98%", "95%", data, function () {
                            detailDataTable.reload();
                        }
                    );
                }
            }
        });
    });

    /**
     * 导出
     */
    var exportExcel = function() {
        Joyin.exportExcel('export', getPageParams());
    };
    
    /**
     * 同步
     */
    var synchroData = function() {
        Joyin.ajax(basePath + 'invest/product/durdeal/FinProductDurDeal001/synchroData', null, function(result) {
            
        })
    };
    
    $(function () {
        // 绑定按钮事件
        $('#FinProductDurDeal001_btn_search').click(function() {
        	//update by yzg 20200307 两个表格同时加载操作列不固定 start
            //table.load();
            //detailDataTable.load();
        	if($('.layui-show').attr("id") == "detailDataTableDiv"){
        		detailDataTable.load();
        	}else{
        		table.load();
        	}
        	//update by yzg 20200307 两个表格同时加载操作列不固定 end
        });
        $('#FinProductDurDeal001_btn_export').click(function() {
            exportExcel();
        });
        $('#FinProductDurDeal001_btn_synchro').click(function() {
            synchroData();
        }); 
    });
}());