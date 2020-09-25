$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var pageParam = {
            transferDateStart: $('#transferDate').startDate()
            ,transferDateEnd: $('#transferDate').endDate()
        };

        if ($("#portfolioId").val()) {
            pageParam.portfolioId = $("#portfolioId").val().join(",");
        } else {
            pageParam.portfolioId = null;
        }
        if ($("#finprodId").val()) {
            pageParam.finprodId = $("#finprodId").val().join(",");
        } else {
            pageParam.finprodId = null;
        }
        if ($("#statusValid").val()) {
            pageParam.statusValid = $("#statusValid").val().join(",");
        } else {
            pageParam.statusValid = null;
        }
        if ($("#counterId").val()) {
            pageParam.counterId = $("#counterId").val().join(",");
        } else {
            pageParam.counterId = null;
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
                return getSearchParams();
            }
        },
        // dblclick:function(table, data) {
        //     data.pageModel=PAGE_MODEL_REFERENCE;
        //     Joyin.dialog(JoyinUtil.getLabel("TrdFundInvestTrade002.reference"),
        //         JoyinUtil.getRealPath("invest/trade/bondtostock/TrdBondToStock002/init"),
        //         "98%", "90%", data,
        //         function(){table.reload();});
        // },
        cols:[
            //有效状态
            {field:'statusValidStr',sortField:'statusValid', width:100, title: 'TrdSecOverTransfer001.statusValid'}
            //组合代码
            ,{field:'portfolioName',sortField:'portfolioId', width:150, title: 'TrdSecOverTransfer001.portfolioName',
                render : function(data, type, row, meta) {
                    return "<a class='portfolioId-link' style='cursor: pointer;'>" + row.portfolioName + "</a>";
                }
            }
            //组合名称
            ,{field:'portfolioName', sortField:'portfolioName',width:140, title: 'TrdSecOverTransfer001.portfolioName', visible: false}
            //金融产品代码
            ,{field:'finprodId',sortField:'finprodId', width:180, title: 'TrdSecOverTransfer001.finprodId',
                render : function(data, type, row, meta) {
                    return "<a class='finprodId-link' style='cursor: pointer;'>" + row.finprodId + "</a>";
                }
            }
            //金融产品名称
            ,{field:'finprodName', sortField:'finprodName',width:140, title: 'TrdSecOverTransfer001.finprodName'}
            //转让日期
            ,{field:'transferDate', sortField:'transferDate',width:100, title: 'TrdSecOverTransfer001.transferDate', type: 'date'}
            //未支付本金
            ,{field:'theoryPrin',sortField:'theoryPrin', width:120, title: 'TrdSecOverTransfer001.theoryPrin', type: 'money'}
            //未支付利息
            ,{field:'theoryInt',sortField:'theoryInt', width:120, title: 'TrdSecOverTransfer001.theoryInt', type: 'money'}
            //罚息
            ,{field:'theoryPenaltyInt',sortField:'theoryPenaltyInt', width:120, title: 'TrdSecOverTransfer001.theoryPenaltyInt', type: 'number'}
            //转让本金
            ,{field:'prinAmt', sortField:'prinAmt',width:100, title: 'TrdSecOverTransfer001.prinAmt', type: 'number'}
            //转让利息
            ,{field:'intAmt',sortField:'intAmt', width:120, title: 'TrdSecOverTransfer001.intAmt', type: 'money'}
            //转让罚息
            ,{field:'penaltyInt',sortField:'penaltyInt', width:120, title: 'TrdSecOverTransfer001.penaltyInt', type: 'money'}
            //实际支付金额
            ,{field:'tradeAmt',sortField:'tradeAmt', width:120, title: 'TrdSecOverTransfer001.tradeAmt', type: 'money'}
            //交易对手
            ,{field:'counterId', sortField:'counterId',width:130, title: 'TrdSecOverTransfer001.counterId'}
            // 备注
            ,{field:'remark', sortField:'',width:400, title: 'TrdSecOverTransfer001.remark'}
        ]
    });
    /**
     * 点击组合代码链接跳转到该组合信息查看页面
     */
    table.bind('click','.portfolioId-link', function(row) {
        Joyin.dialog(JoyinUtil.getLabel("PtlManagementInfo002"),
            basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
            "95%", "90%",
            { pageModel: PAGE_MODEL_REFERENCE,
                portfolioId: row.portfolioId,
                portfolioName: row.portfolioName });
    });
    /**
     * 点击资产代码链接跳转到该资产标的信息查看页面；
     */
    table.bind('click','.finprodId-link', function(row) {
        var urlBox={
            "F01":"invest/asset/bond/FinBondTargetAsset002/init",
            "F18":"invest/asset/project/FinProjectTargetAsset002/init",
            "F07":"invest/asset/depo/FinDepositTarget002/init",
            "F17":"invest/asset/plan/planProfit/FinPlanProfitAsset002/init"
        }
        var urlInfo = urlBox[row.finprodId.slice(0,3)];
        if(!urlInfo){
            urlInfo = "invest/asset/bond/FinBondTargetAsset002/init";
        }
        Joyin.dialog(JoyinUtil.getLabel("FinBondTargetAsset002"),
            basePath + urlInfo,
            "95%", "90%",
            { pageModel:PAGE_MODEL_REFERENCE,
                finprodId: row.finprodId,
                finprodName: row.finprodName });
    });
    var exportExcel = function(){
        Joyin.exportExcel('export', getSearchParams());
    };
    $(function () {
        $("#TrdSecOverTransfer001_btn_search").click(function () {
            if(table.table){
                table.reload();
            }
            else{
                table.load();
            }
        });
        /**
         * 撤销
         */
        $('#TrdSecOverTransfer001_btn_revoke').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("TRDFUND_E_XXT_001"));
                return;
            }
            else if( !selArr || selArr.length > 1 ) {
                Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0046"));
                return;
            }
            var obj = selArr[0];
            obj.operateType = 'revoke';
            Joyin.ajaxJson(basePath + 'invest/period/trdsecovertransfer/TrdSecOverTransfer001/check', obj, function(result1) {
                Joyin.prompt(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.revoke")),0 ,function(result2){
                    obj.cancelRemark = result2;
                    WorkFlow.doRevokeList(basePath + 'invest/period/trdsecovertransfer/TrdSecOverTransfer001/doRevoke', obj, "TrdSecOverTransfer001_btn_revoke", "TrdSecOverdueDuration003");
                });
            });
        });
        $('#TrdSecOverTransfer001_btn_export').click(function() {
            exportExcel();
        });
    });
}());