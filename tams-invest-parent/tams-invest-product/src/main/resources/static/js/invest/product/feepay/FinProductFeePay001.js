
(function() {
	
	var getSearchParams = function() {
		var param = $.serializeToObject('#searchForm');
		param.payDateStart = $('#FinProductFeePay001_paydate').startDate();
		param.payDateEnd = $('#FinProductFeePay001_paydate').endDate();
		if (param.ptlId){
			param.ptlId = param.ptlId.join(',');
		}
		if (param.finprodId){
			param.finprodId = param.finprodId.join(',');
		}
		if (param.feeAccount){
			param.feeAccount = param.feeAccount.join(',');
		}
		if (param.ccy){
			param.ccy = param.ccy.join(',');
		}
		return param;
	}
	
	var table = $('#dataTable')
			.joyintable(
					{
						ajax : {
							url : 'selectForPage',
							type : 'POST',
							data : function() {
								return getSearchParams();
							}
						},
						searchOnInit :REM_TYPE && REM_TYPE == 'R1S6',
						dblclick : function(table, data) {
							if (!data.tradeId || data.tradeId === ''){
								data.confirmFlag = 'confirm'
							}
							data.mdate = data.mdateStr;
							data.pageModel = PAGE_MODEL_REFERENCE;
							Joyin.dialog(JoyinUtil.getLabel("FinProductFeePay002.reference"),
											JoyinUtil.getRealPath("invest/product/feepay/FinProductFeePay002/init"),
											"98%", "95%", data, function() {
												table.reload();
											});
						},

						cols : [
								{
									field : 'effectStatusStr',
									title : 'FinProductFeePay001.table.status',
									width : 100,
									sortField : 'effectStatus'
								},
								{
									field : 'ptlId',
									title : 'FinProductFeePay001.table.portFolioId',
									width : 110
								},
								{
									field : 'finprodId',
									title : 'FinProductFeePay001.table.finprodMarketId',
									width : 110,
									render : function(data, type, row, meta) {
										return '<a  href="javascript:;" onclick="FinProductCommon.showProdDetail(\''
												+ row.finprodId
												+ '\')" class="pro-link" style="cursor: pointer;">'
												+ row.finprodId != 'undefined' ? row.finprodId : '' + '</a>';
									}
								
								},
								{
									field : 'finprodAbbr',
									title : 'FinProductFeePay001.table.finprodAbbr',
									width : 110
								},
								{
									field : 'manageAcctount',
									title : 'FinProductFeePay001.table.manageAcctount',
									width : 120
								},
								{
									field : 'feeAccount',
									title : 'FinProductFeePay001.table.feeAccount',
									width : 110
								},
								/*费用类型 guyunfei 20190923 start*/
								{
									field : 'feeType1Str',
									title : 'FinProductFeePay001.table.feeType1',
									width : 100,
									sortField : 'feeType1'
								},
                            	/*费用类型 guyunfei 20190923 end*/
								{
									field : 'feeName',
									title : 'FinProductFeePay001.table.feeName',
									width : 100
								},
								{
									field : 'positionStartDate',
									title : 'FinProductFeePay001.table.feePayStartDate',
									width : 120,
									type:'date'
								}, 
								{
									field : 'positionEndDate',
									title : 'FinProductFeePay001.table.feePayEndDate',
									width : 120,
									type:'date'
								}, 
								{
									field : 'payDate',
									title : 'FinProductFeePay001.table.payDate',
									width : 80,
									type:'date'
								}, 
								{
									field : 'ccyStr',
									sortField: 'CCY',
									title : 'FinProductFeePay001.table.ccy',
									width : 90
								},
								{
									field : 'ccy',
									visible : false
								},
								{
									field : 'finprodMarketId',
									visible : false
								},
								{
									field : 'basis',
									visible : false
								},
								{
									field : 'feeAmt',
									title : 'FinProductFeePay001.table.feeAmt',
									width : 100,
									type : 'number'
								},
//								{
//									field : 'finprodId',
//									visible : false
//								},
								{
									field : 'feeId',
									visible : false
								}, {
									field : 'cashId',
									visible : false
								}, {
									field : 'cashNum',
									visible : false
								}, {
									field : 'mdateStr',
									visible : false
								},{
									field : 'payDateStr',
									visible : false
								},{
									field : 'positionStartDateStr',
									visible : false
								},{
									field : 'positionEndDateStr',
									visible : false
								}, {
									field : 'tradeId',
									visible : false
								}, {
									field : 'feeType',
									visible : false
								}, {
									field : 'unclearAmt',
									visible : false
								}, {
                                    field : 'isPerpaid',
                                    visible : false
                                }, ]
					});
	
	/**
	 * 新建
	 */
	var openNew = function() {
		Joyin.dialog(JoyinUtil.getLabel("FinProductFeePay002.new"),
                JoyinUtil.getRealPath("invest/product/feepay/FinProductFeePay002/init"),
                "98%", "95%",{pageModel : PAGE_MODEL_NEW, confirmFlag : 'new'},function(){table.reload();}
        );
	}
	
	/**
	 * 导出
	 */
	var exportExcel = function() {
		Joyin.exportExcel('export', getSearchParams());
	};
    
	/**
	 * 确认
	 */
    var confirm = function(){
    	if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
            //判断是否选中元素
        	if( selArr != null &&  selArr.length > 0 ){
        		var data = selArr;
        		window.listdto  = data;
                Joyin.ajaxJson(basePath + 'invest/product/feepay/FinProductFeePay001/checkConfirm', data, function(result) {
                	Joyin.dialog(JoyinUtil.getLabel("FinProductFeePay004.confirm"),
                            JoyinUtil.getRealPath("invest/product/feepay/FinProductFeePay004/init"),
                            "98%", "95%",{pageModel : PAGE_MODEL_NEW, confirmFlag : 'confirm', finprodId: data[0].finprodId,
                		calcEndDate:data[0].positionEndDate,ptlId:data[0].ptlId,
                		feeId: data[0].feeId, ccy: data[0].ccy, feeType: data[0].feeType, feeName: data[0].feeName},
                		function(){table.reload();}
                		
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
                Joyin.ajaxJson(basePath + 'invest/product/feepay/FinProductFeePay001/check', data, function(result) {
                    Joyin.ajaxJson(basePath + 'invest/product/feepay/FinProductFeePay001/checkHasAfterTrade', data, function(result) {
                        if(result == true){
                    	    Joyin.confirm(JoyinUtil.getMessage("BUSS_COM_E_0097"), function() {
                                Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                                result = result.replace(/\s+/g,"");
                                if (!result) {
                                    result = '无';
                                }
                                    data.revokeReason = result;
                                    WorkFlow.doRevokeList(basePath + 'invest/product/feepay/FinProductFeePay001/doRevoke', data, "FinProductFeePay001_btn_revoke", "FinProductFeePay002");
                                });
                            });
                        }else{
                            Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                                result = result.replace(/\s+/g,"");
                                if (!result) {
                                    result = '无';
                                }
                                data.revokeReason = result;
                                WorkFlow.doRevokeList(basePath + 'invest/product/feepay/FinProductFeePay001/doRevoke', data, "FinProductFeePay001_btn_revoke", "FinProductFeePay002");
                            });

                        }
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
		$('#FinProductFeePay001_btn_search').click(function() {
			table.load();
		});
		$('#FinProductFeePay001_btn_export').click(function() {
			exportExcel();
		});
		$('#FinProductFeePay001_btn_add').click(function() {
			openNew();
		});
		$('#FinProductFeePay001_btn_confirm').click(function() {
			confirm();
		});
		$('#FinProductFeePay001_btn_revoke').click(function() {
			revoke();
		});
	});
}());