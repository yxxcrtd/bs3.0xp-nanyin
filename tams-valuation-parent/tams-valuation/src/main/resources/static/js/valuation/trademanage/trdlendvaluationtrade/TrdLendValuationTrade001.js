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
            Joyin.dialog(JoyinUtil.getLabel("TrdLendValuationTrade002.reference"),
                    JoyinUtil.getRealPath("valuation/trademanage/trdlendvaluationtrade/TrdLendValuationTrade002/init"),
                    "99%", "99%", data/*,
                    function(){table.reload();}*/);
        },
        cols:[
            {field:'tradeId', width:150, sortField:'tradeId',title: '交易编号'}
            ,{field:'outTradeId', width:150, sortField:'outTradeId',title: '外部交易编号'}
            ,{field:'portfolioId', width:150, title: '产品代码',sortField:'portfolioId'}
            ,{field:'portfolioName', width:150, title: '产品名称',sortField:'portfolioName'}
            ,{field:'busiPs', width:150, title: '交易类型',sortField:'busiPs'}
            ,{field:'tradeDate', width:150,  title: '交易日',type:'date'}
            ,{field:'settDate', width:150,  title: '交割日',type:'date'}
            ,{field:'counterId', title: '交易对手',width:100}
            ,{field:'prinAmt', width:150, title: '本金金额',type:'number',sortField:'prinAmt'}
            ,{field:'intAmt', width:150, title: '应记利息',type:'number',sortField:'intAmt'}
            ,{field:'settleAmt', width:150, title: '结算金额',type:'number',sortField:'settleAmt'}
            ,{field:'dealAmt', width:150, title: '本币交易金额',type:'number',sortField:'dealAmt'}
            ,{field:'ccy', width:150, title: '币种',sortField:'ccy'}
            ,{field:'trdAcctId', title: '资金账户',width:100}
            ,{field:'action', title: '操作' , type:'action',buttons: ['edit', 'delt'] , width:'10%'}
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
    
    // table.bind('click','.pro-link', function(row) {
    //     Joyin.dialog(JoyinUtil.getLabel("PtlManagementInfo002"),
    //             basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
    //             "95%", "90%",{
    //             pageModel:PAGE_MODEL_REFERENCE,
    //             portfolioId:row.portfolioId,
    //             portfolioName: row.portfolioName});
    // });
    
    $(function () {
        // 绑定按钮事件
        $('#TrdLendValuationTrade001_btn_search').click(function() {
            table.load();
        });
        
        $('#TrdLendValuationTrade001_btn_export').click(function() {
            exportExcel();
        });
        $('#TrdLendValuationTrade001_btn_add').click(
                function() {
                    Joyin.dialog(JoyinUtil.getLabel("TrdLendValuationTrade002"), basePath
                            + "valuation/trademanage/trdlendvaluationtrade/TrdLendValuationTrade002/init", "99%", "99%", {
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
                    checkObj.revokeReason = result;
                    WorkFlow.doDeleteList(basePath + 'invest/trade/lend/TrdLendInvestTrade001/doRevoke', checkObj, "TrdLendInvestTrade001_btn_revoke", "TrdLendInvestTrade002");
                });
            });
        });
    });
}());