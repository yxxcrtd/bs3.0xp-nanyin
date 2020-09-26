/**
 * 外汇远期投资交易
 */
(function(){
	/**
     * 获取查询参数
     */
    var getPageParams = function() {
        var pageParam = {
            entrustId: $('#entrustId').val()
            ,portfolioId: $('#portfolioId').val()
            ,excDealType: $('#excDealType').val()
            ,curPair: $('#curPair').val()
            ,ps: $('#ps').val()
        	,deliveryType: $('#deliveryType').val()
            ,validStatus: $('#validStatus').val()
            ,tradeDateBegin: $('#tradeDate').startDate()
            ,tradeDateEnd: $('#tradeDate').endDate()
            ,settleDateBegin: $('#settleDate').startDate()
            ,settleDateEnd: $('#settleDate').endDate()
        };
        // 组合代码
        if ($("#portfolioId").val()) {
            pageParam.portfolioId = $("#portfolioId").val().join(",");
        } else {
            pageParam.portfolioId = null;
        }
        // 交易方向
        if ($("#ps").val()) {
            pageParam.ps = $("#ps").val().join(",");
        } else {
            pageParam.ps = null;
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
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("TrdForwexInvestTrade002.reference"),
                    JoyinUtil.getRealPath("invest/trade/forwex/TrdForwexInvestTrade002/init"),
                    "99%", "99%", data);
        },
        cols:[
            {field:'validStatusStr',sortField:'validStatus', width:100, title: 'TrdForwexInvestTrade.statusValid'}
            ,{field:'tradeId', sortField:'tradeId',width:170, title: 'TrdForwexInvestTrade.tradeId'}
            ,{field:'entrustId',sortField:'entrustId', width:180, title: 'TrdForwexInvestTrade.entrustId'}
        	,{field:'portfolioId',sortField:'portfolioId', width:140, title: 'TrdForwexInvestTrade.portfolioId',
                render : function(data, type, row, meta) {
                    return "<a class='portfolioId-link' style='cursor: pointer;'>" + row.portfolioId + "</a>";
                }
        	}
        	,{field:'portfolioName', sortField:'portfolioName',width:140, title: 'TrdForwexInvestTrade.portfolioName'}
        	,{field:'excDealTypeStr', sortField:'excDealType',width:140, title: 'TrdForwexInvestTrade.excDealType'}
        	,{field:'curPair', sortField:'curPair',width:140, title: 'TrdForwexInvestTrade.curPair'}
        	,{field:'psStr', sortField:'ps',width:100, title: 'TrdForwexInvestTrade.excPs'}
        	,{field:'deliveryTypeStr', sortField:'deliveryType',width:100, title: 'TrdForwexInvestTrade.deliveryType'}
        	,{field:'tradeDateStr', sortField:'tradeDate',width:100, title: 'TrdForwexInvestTrade.tradeDate'}
        	,{field:'termTypeStr', sortField:'termType',width:100, title: 'TrdForwexInvestTrade.term'}
        	,{field:'settleDateStr', sortField:'settleDate',width:100, title: 'TrdForwexInvestTrade.settleDate'}
        	,{field:'unitCprice', sortField:'unitCprice',width:100, title: 'TrdForwexInvestTrade.unitCprice'}
        	,{field:'unitInt', sortField:'unitInt',width:100, title: 'TrdForwexInvestTrade.unitInt'}
        	,{field:'unitFprice', sortField:'unitFprice',width:100, title: 'TrdForwexInvestTrade.unitFprice'}
        	,{field:'pricedDateStr', sortField:'pricedDate',width:100, title: 'TrdForwexInvestTrade.pricedDate'}
        	,{field:'pricedDateRate', sortField:'pricedDateRate',width:100, title: 'TrdForwexInvestTrade.pricedDateRate'}
        	,{field:'difPsStr', sortField:'difPs',width:100, title: 'TrdForwexInvestTrade.difPs'}
        	,{field:'difPayAmt', sortField:'difPayAmt',width:100, title: 'TrdForwexInvestTrade.difPayAmt'}
        	,{field:'ccyStr', sortField:'ccy',width:100, title: 'TrdForwexInvestTrade.ccy'}
        	,{field:'prinAmt', sortField:'prinAmt',width:100, title: 'TrdForwexInvestTrade.prinAmt'}
        	,{field:'corCcyStr', sortField:'corCcy',width:100, title: 'TrdForwexInvestTrade.corCcy'}
        	,{field:'fpriceAmt', sortField:'fpriceAmt',width:100, title: 'TrdForwexInvestTrade.fpriceAmt'}
        	,{field:'usdAmt', sortField:'usdAmt',width:100, title: 'TrdForwexInvestTrade.usdAmt'}
        	,{field:'remark', width:150, title: 'TrdForwexInvestTrade.remark'}
            ,{field:'workflowId', visible: false}
            ,{field:'workflowStatus', visible: false}
            ,{field:'finprodType2', visible: false}
            ,{field:'finprodId', visible: false}
            ,{field:'tradeId1', visible: false}
        ],
    });
  
    var exportExcel = function(){
        Joyin.exportExcel('export', getPageParams());
    };
  
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
    
	
	$(function () {
        // 绑定按钮事件
        $('#TrdForwexInvestTrade001_btn_search').click(function() {
            table.load();
            $('#TrdForwexInvestTrade001_btn_revoke').show();
        });
        
        $('#TrdForwexInvestTrade001_btn_export').click(function() {
            exportExcel();
        });
        $('#TrdForwexInvestTrade001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("TrdForwexInvestTrade002"),
                    basePath + "invest/trade/forwex/TrdForwexInvestTrade002/init",
                    "98%", "90%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#TrdForwexInvestTrade001_btn_revoke').click(function() {
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
            checkObj.finprodName = selArr[0].finprodName;
            checkObj.tradeDate = selArr[0].tradeDateStr;
            checkObj.operateType = "revoke";
            checkObj.ps = selArr[0].ps;
            Joyin.ajaxJson(basePath + 'invest/trade/forwex/TrdForwexInvestTrade001/checkRevoke', checkObj, function(result) {
                Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                    result = result.replace(/\s+/g,"");
                    if (!result) {
                        result = '无';
                    }
                    checkObj.revokeReason = result;
                    WorkFlow.doDeleteList(basePath + 'invest/trade/forwex/TrdForwexInvestTrade001/doRevoke', checkObj, "TrdForwexInvestTrade001_btn_revoke", "TrdForwexInvestTrade002");
                });
            });

        });
    });
}());