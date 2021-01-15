(function(){
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        var pageParam = {
            tradeDate: $('#tradeDate').val()
            ,counterId:$('#counterId').val()
            ,counterTrader: $('#counterTrader').val()
            ,finprodId: $('#finprodId').val()
            ,ps: $('#ps').val()
            ,settleSpreed: $('#settleSpreed').val()
            ,validStatus: $('#validStatus').val()
            ,registOrg: $('#registOrg').val()
            ,accountId: $('#accountId').val()
            ,secAcctId: $('#secAcctId').val()
        };
        // if ($("#portfolioId").val()) {
        //     pageParam.portfolioId = $("#portfolioId").val().join(",");
        // } else {
        //     pageParam.portfolioId = null;
        // }
        return pageParam;
    };

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            data.tradeId = data.totleTradeId
            Joyin.dialog(JoyinUtil.getLabel("TrdBondInvestTrade002.reference"),
                    JoyinUtil.getRealPath("invest/trade/pretrdbondtrade/PreTrdBondInvestTrade002/init"),
                    "99%", "99%", data);
        },
        cols:[
            {field:'validStatusText',sortField:'validStatus', width:100, title: 'PreTrdBondInvestTrade001.validStatus'}
            ,{field:'tradeDateText', sortField:'tradeDate',width:100, title: 'PreTrdBondInvestTrade001.tradeDate', type: 'date'}
            ,{field:'counterId', sortField:'counterId',width:200, title: 'PreTrdBondInvestTrade001.counterId'}
            ,{field:'counterTrader',sortField:'counterTrader', width:140, title: 'PreTrdBondInvestTrade001.counterTrader'}
            ,{field:'ps', sortField:'PS',width:100, title: 'PreTrdBondInvestTrade001.ps'}
            ,{field:'finprodId',sortField:'finprodId', width:140, title: 'PreTrdBondInvestTrade001.finprodId',
                // render : function(data, type, row, meta) {
                //     return "<a class='finprodId-link' style='cursor: pointer;'>" + row.finprodId + "</a>";
                // }
            }
            ,{field:'finprodName', sortField:'finprodName',width:180, title: 'PreTrdBondInvestTrade001.finprodName'}
            ,{field:'unitCprice',sortField:'unitCprice',width:100, title:'PreTrdBondInvestTrade001.unitCprice', type: 'money'}
            ,{field:'yield',sortField:'yield', width:150, title: 'PreTrdBondInvestTrade001.yield', type: 'money'}
            ,{field:'exerYield',sortField:'exerYield', width:150, title: 'PreTrdBondInvestTrade001.exerYield', type: 'money'}
            ,{field:'shareAmt',sortField:'shareAmt', width:150, title: 'PreTrdBondInvestTrade001.tradeAmt', type: 'money'}
            ,{field:'settleSpeedText', sortField:'settleSpeedText',width:180, title: 'PreTrdBondInvestTrade001.settleSpreed'}
            ,{field:'deliveryType', sortField:'deliveryType',width:180, title: 'PreTrdBondInvestTrade001.settMode'}
            ,{field:'clearType', sortField:'clearType',width:180, title: 'PreTrdBondInvestTrade001.clearType'}
            ,{field:'effTimeText', sortField:'effTimeText',width:180, title: 'PreTrdBondInvestTrade001.effTime'}
            ,{field:'remark', sortField:'remark',width:300, title: 'PreTrdBondInvestTrade001.text'}
            ,{field:'acctBankDep', sortField:'acctBankDep',width:200, title: 'PreTrdBondInvestTrade001.accountBankName'}
            ,{field:'acctName', sortField:'acctNo',width:200, title: 'PreTrdBondInvestTrade001.accountName'}
            ,{field:'acctNo', sortField:'acctNo',width:200, title: 'PreTrdBondInvestTrade001.accountNo'}
            ,{field:'acctBankDepoPayno', sortField:'acctBankDepoPayno',width:200, title: 'PreTrdBondInvestTrade001.paySysNo'}
            ,{field:'secAcctName', sortField:'secAcctId',width:200, title: 'PreTrdBondInvestTrade001.secAcctName'}
            ,{field:'registOrg', sortField:'registOrg',width:200, title: 'PreTrdBondInvestTrade001.registOrgName'}
            ,{field:'secAcctId', sortField:'secAcctId',width:200, title: 'PreTrdBondInvestTrade001.secAcctNo'}
            ,{field:'unitInt', sortField:'unitInt',width:100, title: 'PreTrdBondInvestTrade001.unitInt', type: 'money',digit:6}
            ,{field:'ccy', sortField:'CCY',width:100, title: 'PreTrdBondInvestTrade001.ccy'}
        	,{field:'totleTradeId', visible: false}
        	,{field:'workflowId', visible: false}
        ],
    });
  
    var exportExcel = function(){
        Joyin.exportExcel('export', getPageParams());
    };
  
    $(function () {
        // 绑定按钮事件
        $('#PreTrdBondInvestTrade001_btn_search').click(function() {
            table.load();
            $('#PreTrdBondInvestTrade001_btn_revoke').show();
        });
        
        $('#PreTrdBondInvestTrade001_btn_export').click(function() {
            exportExcel();
        });

        // 更新状态
        $("#PreTrdBondInvestTrade001_btn_refresh").click(function(){
    		if(table!=null && table!=''){
                var arrs = table.getAllCheckedRows();//判断表格不为空，然后获取选择的数据
            }
            if (!arrs || arrs.length == 0) {//判断是否选择，如果没有则报错COMMON_E_0026
                Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0046"));
                return;
            }
            var tradeId = "";
            var tradeIdArr = new Array();
            $.each(arrs,function(v,t){
            	if(t.validStatus == STATUS_REVOKE){
            		tradeIdArr[v] = t.tradeId + STR_R;
            	}else{
            		tradeIdArr[v] = t.tradeId;
            	}
            });
            tradeId = tradeIdArr.join('/');
            var object = {tradeId:tradeId};
            Joyin.ajax(basePath + 'invest/trade/bond/PreTrdBondInvestTrade001/refreshStatus', object, function() {
            	table.reload();
            });
    	});

        // 债券交易预录入新建
        $('#PreTrdBondInvestTrade001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("PreTrdBondInvestTrade002"), basePath
                            + "invest/trade/pretrdbondtrade/PreTrdBondInvestTrade002/init", "99%", "99%", {
                        pageModel : PAGE_MODEL_NEW
                    }, function() {
                        table.reload();
                    });
        });
        

        /**
         * 撤销
         */
        $('#PreTrdBondInvestTrade001_btn_revoke').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            // 列表中未勾选
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            // 列表中勾选对象1条以上时
            if (selArr.length > 1) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                return;
            }
            if(selArr[0].validStatus != $("#validStatusRevoke").val()){
                Joyin.error("只能选择状态为有效的数据进行操作。");
                return;
            }
            var checkObj = {};
            checkObj.tradeId = selArr[0].tradeId;
            checkObj.systemFinprodId = selArr[0].systemFinprodId;
            checkObj.tradeDate = selArr[0].tradeDate;
            checkObj.operateType = "revoke";
            Joyin.ajaxJson(basePath + 'invest/trade/pretrdbondtrade/PreTrdBondInvestTrade001/checkRevoke', checkObj, function(result) {
                Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                    result = result.replace(/\s+/g,"");
                    if (!result) {
                        result = '无';
                    }
                    checkObj.revokeReason = result;
                    WorkFlow.doDeleteList(basePath + 'invest/trade/pretrdbondtrade/PreTrdBondInvestTrade001/doRevoke', checkObj, "PreTrdBondInvestTrade001_btn_revoke", "PreTrdBondInvestTrade002");
                });
            });
//            
//            if(table!=null && table!=''){
//                var selArr = table.getAllCheckedRows();
//            }
//            if (!selArr || selArr.length == 0) {
//                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
//                return;
//            }
//            var object = {selectedItem: selArr, operateType:"delete"};
//            Joyin.ajaxJson(basePath + 'invest/trade/bond/PreTrdBondInvestTrade001/check', object, function(result) {
//                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
//                    WorkFlow.doDeleteList(basePath + 'invest/trade/bond/PreTrdBondInvestTrade001/delete', object, "PreTrdBondInvestTrade001_btn_revoke", "PreTrdBondInvestTrade001");
//                });
//            });
        });
    });
}());