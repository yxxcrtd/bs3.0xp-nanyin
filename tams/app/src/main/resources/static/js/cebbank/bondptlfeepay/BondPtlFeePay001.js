
(function() {
	
	var getSearchParams = function() {
		var object = $.serializeToObject('#searchForm');
		var secAcctId = object.secAcctId;
        if(null != secAcctId && "" != secAcctId){
        	secAcctId=object.secAcctId.join(',');
        }
        return {
        	secAcctId:secAcctId,
        	payDateStart : $('#BondPtlFeePay001_paydate').startDate(),
        	payDateEnd : $('#BondPtlFeePay001_paydate').endDate(),
        	status: $('#BondPtlFeePay001_status').val()
        }
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
							Joyin.dialog("证券组合费支付查看",
											JoyinUtil.getRealPath("cebbank/bondptlfeepay/BondPtlFeePay002/init"),
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
									width : 140
								},
								{
									field : 'secAcctId',
									title : '证券账号',
									width : 110
								},
								{
									field : 'feeId',
									title : '费用id',
									width : 110,
									visible : false
								},
								{
									field : 'feeName',
									title : '费用名称',
									width : 100,
									visible : false
								}, 
								{
									field : 'payDate',
									title : '付费日期',
									width : 80,
									type:'date'
								}, 
								{
									field : 'actPayAmt',
									title : '实付金额（元）',
									width : 140,
									type : 'number'
								} ]
					});
	
	/**
	 * 新建
	 */
	var openNew = function() {
		Joyin.dialog("证券组合费支付新建",
				basePath + "cebbank/bondptlfeepay/BondPtlFeePay002/init",
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
                Joyin.ajaxJson(basePath + 'cebbank/bondptlfeepay/BondPtlFeePay001/check', data, function(result) {
                	Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                    result = result.replace(/\s+/g,"");
                    if (!result) {
                        result = '无';
                    }
                        data.revokeReason = result;
                        WorkFlow.doDeleteList(basePath + 'cebbank/bondptlfeepay/BondPtlFeePay001/doRevoke', data, "BondPtlFeePay001_btn_revoke", "BondPtlFeePay002");
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
		$('#BondPtlFeePay001_btn_search').click(function() {
			table.load();
		});
		$('#BondPtlFeePay001_btn_export').click(function() {
			exportExcel();
		});
		$('#BondPtlFeePay001_btn_add').click(function() {
			openNew();
		});
		$('#BondPtlFeePay001_btn_revoke').click(function() {
			revoke();
		});
	});
}());