
(function() {
	
	var getSearchParams = function() {
		var param = $.serializeToObject('#searchForm');
		param.payDateStart = $('#InvestFeePay001_paydate').startDate();
		param.payDateEnd = $('#InvestFeePay001_paydate').endDate();
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
							Joyin.dialog(JoyinUtil.getLabel("InvestFeePay002.reference"),
											JoyinUtil.getRealPath("invest/portfolio/feepay/InvestFeePay002/init"),
											"98%", "95%", data, function() {
												table.reload();
											});
						},

						cols : [
								{
									field : 'effectStatusStr',
									title : 'InvestFeePay001.table.status',
									width : 110
								},
								{
									field : 'effectStatus',
									title : 'InvestFeePay001.table.status',
									width : 110,
									visible : false
								},
								{
									field : 'tradeId',
									title : 'InvestFeePay001.table.tradeId',
									width : 110
								},
								{
									field : 'secAcctId',
									title : 'InvestFeePay001.table.secAcctId',
									width : 110
								},
								{
									field : 'feeId',
									title : '费用id',
									width : 110,
									visible : false
								},
								{
									field : 'feeType1Str',
									title : 'InvestFeePay001.table.feeType',
									width : 100,
									sortField : 'feeType1'
								},
								{
									field : 'feeName',
									title : '费用名称',
									width : 100,
									visible : false
								},
								{
									field : 'positionStartDate',
									title : 'InvestFeePay001.table.positionStartDate',
									width : 80,
									type:'date'
								}, 
								{
									field : 'positionEndDate',
									title : 'InvestFeePay001.table.positionEndDate',
									width : 80,
									type:'date'
								}, 
								{
									field : 'payDate',
									title : 'InvestFeePay001.table.payDate',
									width : 80,
									type:'date'
								}, 
								{
									field : 'feeAmt',
									title : 'InvestFeePay001.table.payAmt',
									width : 100,
									type : 'number'
								}, 
								{
									field : 'actPayAmt',
									title : 'InvestFeePay001.table.actPayAmt',
									width : 100,
									type : 'number'
								} ]
					});
	
	/**
	 * 新建
	 */
	var openNew = function() {
		Joyin.dialog(JoyinUtil.getLabel("InvestFeePay002"),
				basePath + "invest/portfolio/feepay/InvestFeePay002/init",
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
                Joyin.ajaxJson(basePath + 'invest/portfolio/feepay/InvestFeePay001/check', data, function(result) {
                	Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                    result = result.replace(/\s+/g,"");
                    if (!result) {
                        result = '无';
                    }
                        data.revokeReason = result;
                        WorkFlow.doDeleteList(basePath + 'invest/portfolio/feepay/InvestFeePay001/doRevoke', data, "InvestFeePay001_btn_revoke", "InvestFeePay002");
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
		$('#InvestFeePay001_btn_search').click(function() {
			table.load();
		});
		$('#InvestFeePay001_btn_export').click(function() {
			exportExcel();
		});
		$('#InvestFeePay001_btn_add').click(function() {
			openNew();
		});
		$('#InvestFeePay001_btn_revoke').click(function() {
			revoke();
		});
	});
}());