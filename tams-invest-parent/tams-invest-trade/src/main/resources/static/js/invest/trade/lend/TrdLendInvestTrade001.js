(function(){
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        var pageParam =  {
            entrustId: $('#entrustId').val()
            ,portfolioId: $('#portfolioId').val()
            ,paBussType: $('#paBussType').val()
            ,ps: $('#ps').val()
        	,validStatus: $('#validStatus').val()
            ,tradeDateBegin: $('#tradeDate').startDate()
            ,tradeDateEnd: $('#tradeDate').endDate()
        };
        if ($("#portfolioId").val()) {
            pageParam.portfolioId = $("#portfolioId").val().join(",");
        } else {
            pageParam.portfolioId = null;
        }
        
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
            data.pageModel = PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("TrdLendInvestTrade002.reference"),
                    JoyinUtil.getRealPath("invest/trade/lend/TrdLendInvestTrade002/init"),
                    "99%", "99%", data/*,
                    function(){table.reload();}*/);
        },
        cols:[
            {field:'validStatusText', width:100, sortField:'VALID_STATUS', title: 'TrdLendInvestTrade001.validStatus'}
            ,{field:'tradeId', width:170, sortField:'TRADE_ID', title: 'TrdLendInvestTrade001.tradeId'}
        	,{field:'entrustId', width:170, sortField:'ENTRUST_ID', title: 'TrdLendInvestTrade001.entrustId'}
        	,{field:'portfolioId', width:140, sortField:'PORTFOLIO_ID', title: 'TrdLendInvestTrade001.portfolioId',
        	    render : function(data, type, row, meta) {
                    return "<a class='pro-link' style='cursor: pointer;'>" + row.portfolioId + "</a>";
                }
        	}
        	,{field:'portfolioName', width:140, sortField:'PORTFOLIO_NAME', title: 'TrdLendInvestTrade001.portfolioName'}
        	,{field:'paBussTypeText', width:110, sortField:'PA_BUSS_TYPE', title: 'TrdLendInvestTrade001.paBussType'}
            ,{field:'busiPsText', width:100, sortField:'BUSI_PS', title: 'TrdLendInvestTrade001.ps'}
            ,{field:'tradeDate', width:100, sortField:'TRADE_DATE', title: 'TrdLendInvestTrade001.tradeDate', type: 'date'}
        	,{field:'tradeAmt', width:120, sortField:'TRADE_AMT', title: 'TrdLendInvestTrade001.tradeAmt', type: 'money'}
        	,{field:'ccyText', width:130, sortField:'CCY', title: 'TrdLendInvestTrade001.ccy'}
        	,{field:'remark', width:150, sortField:'REMARK', title: 'TrdLendInvestTrade001.remark'}
        	,{field:'paBussType', visible: false}
        	,{field:'finprodType2', visible: false}
        	,{field:'workflowId', visible: false}
        	,{field:'workflowStatus', visible: false}
        ],
        //loadonce:true
    });
  
    var exportExcel = function(){       
        Joyin.exportExcel('export', getPageParams());
    };
  
//    /**
//     * 绑定编辑事件
//     */
//    table.bind('click', '.edit', function(data, index) {
//        var paramObj = {};
//        paramObj.pageModel = PAGE_MODEL_EDIT;
//        paramObj.tradeId = data.tradeId;
//        paramObj.workflowId = data.workflowId;
//        paramObj.workflowStatus = data.workflowStatus;
//
//        Joyin.ajaxJson(basePath + 'invest/trade/lend/TrdLendInvestTrade002/check', data, function(result) {
//            Joyin.dialog(JoyinUtil.getLabel("TrdLendInvestTrade002.edit"), basePath
//                    + "invest/trade/lend/TrdLendInvestTrade002/init", "99%", "99%", paramObj, function() {
//                table.reload();
//            });
//        });
//    });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/trade/lend/TrdLendInvestTrade001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/trade/lend/TrdLendInvestTrade001/delete', object, "TrdLendInvestTrade001_btn_revoke", "TrdLendInvestTrade001",function() {
                	table.reload();
                });
            });
        });
    });
    
    table.bind('click','.pro-link', function(row) {
        Joyin.dialog(JoyinUtil.getLabel("PtlManagementInfo002"),
                basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
                "95%", "90%",{
                pageModel:PAGE_MODEL_REFERENCE,
                portfolioId:row.portfolioId,
                portfolioName: row.portfolioName});
    });
    
    $(function () {
        // 绑定按钮事件
        $('#TrdLendInvestTrade001_btn_search').click(function() {
            table.load();
            $('#TrdLendInvestTrade001_btn_revoke').show();
        });
        
        $('#TrdLendInvestTrade001_btn_export').click(function() {
            exportExcel();
        });
        $('#TrdLendInvestTrade001_btn_add').click(
                function() {
                    Joyin.dialog(JoyinUtil.getLabel("TrdLendInvestTrade002"), basePath
                            + "invest/trade/lend/TrdLendInvestTrade002/init", "99%", "99%", {
                        pageModel : PAGE_MODEL_NEW
                    }, function() {
                        table.reload();
                    });
                });
        $('#TrdLendInvestTrade001_btn_revoke').click(function() {
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
            
            var checkObj = {};
            checkObj.tradeId = selArr[0].tradeId;
            checkObj.tradeDate = selArr[0].tradeDate;
            checkObj.paBussType = selArr[0].paBussType;
            checkObj.operateType = "revoke";
            Joyin.ajaxJson(basePath + 'invest/trade/lend/TrdLendInvestTrade001/checkRevoke', checkObj, function(result) {
                Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                    result = result.replace(/\s+/g,"");
                    if (!result) {
                        result = '无';
                    }
                    checkObj.revokeReason = result;
                    WorkFlow.doDeleteList(basePath + 'invest/trade/lend/TrdLendInvestTrade001/doRevoke', checkObj, "TrdLendInvestTrade001_btn_revoke", "TrdLendInvestTrade002");
                });
            });
        });
    });
}());