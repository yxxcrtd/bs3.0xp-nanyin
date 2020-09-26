$(function(){
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        var result = $.serializeToObject('#searchForm');
        // 组合代码
        var portfolioIdTemp = $('#portfolioId').val();
        if(portfolioIdTemp != null)
            result.portfolioId = portfolioIdTemp.join(",");
        // 货币对
        var curPairTemp = $('#curPair').val();
        if(curPairTemp != null)
            result.curPair = curPairTemp.join(",");
        // 期限类型
        var excFxsTermTypeTemp = $('#excFxsTermType').val();
        if(excFxsTermTypeTemp != null)
            result.excFxsTermType = excFxsTermTypeTemp.join(",");
        result.tradeDateBegin = $('#tradeDate').startDate();
        result.tradeDateEnd = $('#tradeDate').endDate();
        result.vDateBegin = $('#vdate').startDate();
        result.vDateEnd = $('#vdate').endDate();
        result.mDateBegin = $('#mdate').startDate();
        result.mDateEnd = $('#mdate').endDate();
        return result;
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
            data.pageModel = PAGE_MODEL_REFERENCE; //page_model:查看
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/trade/trdfxswapinvesttrade/TrdFxswapInvestTrade002/init",
                    "98%", "98%", data);
        },
        cols:[
            {field:'tradeStatusStr', title: 'TrdFxswapInvestTrade001.status', width:100, sortField:'tradeStatus'}
            ,{field:'tradeId', title: 'TrdFxswapInvestTrade001.tradeId', width:120, sortField:'tradeId'}
            ,{field:'entrustId', title: 'TrdFxswapInvestTrade001.entrustId', width:120, sortField:'entrustId'}
            ,{field:'portfolioId', title: 'TrdFxswapInvestTrade001.portfolioId', width:100,render: function (data, type, row, meta) {
                return "<a  class='pro-link' style='cursor: pointer;'>"+row.portfolioId+"</a>";
            }, sortField:'portfolioId'}
            ,{field:'portfolioName', title: 'TrdFxswapInvestTrade001.portfolioName', width:150, sortField:'portfolioName'}
            ,{field:'excDealTypeStr', title: 'TrdFxswapInvestTrade001.excDealType', width:100, sortField:'excDealType'}
            ,{field:'curPairStr', title: 'TrdFxswapInvestTrade001.curPair', width:100,sortField:'curPair'}
            ,{field:'psStr', title: 'TrdFxswapInvestTrade001.ps', width:100,sortField:'ps'}
            ,{field:'dealCurStr', title: 'TrdFxswapInvestTrade002.dealCur', width:100,sortField:'dealCur'}
            ,{field:'corCcyStr', title: 'TrdFxswapInvestTrade002.corCcy', width:100,sortField:'corCcy'}
            ,{field:'excFxsTermTypeStr', title: 'TrdFxswapInvestTrade002.excFxsTermType', width:100,sortField:'excFxsTermType'}
            ,{field:'tradeDate',  title: 'TrdFxswapInvestTrade002.tradeDate', width:100,type:'date', sortField:'tradeDate'}
            ,{field:'spotRate', title: 'TrdFxswapInvestTrade002.spotRate', width:100,type:'number',sortField:'spotRate'}
            ,{field:'nTermTypeExcStr', title: 'TrdFxswapInvestTrade002.nTermTypeExc', width:100,sortField:'nTermTypeExc'}
            ,{field:'vdate',  title: 'TrdFxswapInvestTrade002.vdate', width:100,type:'date', sortField:'vdate'}
            ,{field:'nPoint', title: 'TrdFxswapInvestTrade002.nPoint', width:100,type:'number',sortField:'nPoint', sortField:'nPoint'}
            ,{field:'nFprice', title: 'TrdFxswapInvestTrade002.nFprice', width:100,type:'number',sortField:'nFprice', sortField:'nFprice'}
            ,{field:'nDealCurAmt', title: 'TrdFxswapInvestTrade002.nDealCurAmt', width:100,type:'number',sortField:'nDealCurAmt'}
            ,{field:'nCorCurAmt', title: 'TrdFxswapInvestTrade002.nCorCurAmt', width:100,type:'number',sortField:'nCorCurAmt'}
            ,{field:'nUsdAmt', title: 'TrdFxswapInvestTrade002.nUsdAmt', width:100,type:'number',sortField:'nUsdAmt'}
            ,{field:'fTermTypeExcStr', title: 'TrdFxswapInvestTrade002.fTermTypeExc', width:100,sortField:'fTermTypeExc'}
            ,{field:'mdate',  title: 'TrdFxswapInvestTrade002.mdate', width:100,type:'date', sortField:'mdate'}
            ,{field:'fPoint', title: 'TrdFxswapInvestTrade002.fPoint', width:100,type:'number',sortField:'fPoint'}
            ,{field:'fFprice', title: 'TrdFxswapInvestTrade002.fFprice', width:100,type:'number',sortField:'fFprice'}
            ,{field:'fDealCurAmt', title: 'TrdFxswapInvestTrade002.fDealCurAmt', width:100,type:'number',sortField:'fDealCurAmt'}
            ,{field:'fCorCurAmt', title: 'TrdFxswapInvestTrade002.fCorCurAmt', width:100,type:'number',sortField:'fCorCurAmt'}
            ,{field:'fUsdAmt', title: 'TrdFxswapInvestTrade002.fUsdAmt', width:100,type:'number',sortField:'fUsdAmt'}
            ,{field:'remark', title: 'TrdFxswapInvestTrade001.remark', width:100}
        ]
    });
    /**
     * 导出事件
     */
    var exportExcel = function() {
        var paramObj = getPageParams();
        Joyin.exportExcel('export', paramObj);
    };
    
    /**
     * 画面加载完成
     */
    $(function() {
        $('#TrdFxswapInvestTrade001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("TrdFxswapInvestTrade002"),
                    basePath+ "invest/trade/trdfxswapinvesttrade/TrdFxswapInvestTrade002/init",
                        "98%", "98%", {pageModel:PAGE_MODEL_NEW});
        });
       
        //导出
        $('#TrdFxswapInvestTrade001_btn_export').click(function() {
            exportExcel();
        });
        //查询
        $('#TrdFxswapInvestTrade001_btn_search').click(function(){
            table.load();
        });
        //撤销操作
        $('#TrdFxswapInvestTrade001_btn_revoke').click(function(){
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();//判断表格不为空，然后获取选择的数据
            }
            if (!selArr || selArr.length == 0) {//判断是否选择，如果没有则报错COMMON_E_0026
                Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0046"));
                return;
            }
            // 列表中勾选对象1条以上时
            if (selArr.length > 1) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                return;
            }else if(selArr[0].tradeStatus !== TRADE_STATUS){
                Joyin.error(JoyinUtil.getLabel("TrdPlanRateInvestTrade002.revokeStatus"))
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};//请求参数
            Joyin.ajaxJson(basePath + 'invest/trade/trdfxswapinvesttrade/TrdFxswapInvestTrade001/check', object, function(result) {//数据验证
                Joyin.prompt(JoyinUtil.getLabel('TrdFxswapInvestTrade001.cancelText'), 0, function (result) {
                    for(var select in object.selectedItem){
                        object.selectedItem[select].cancelRemark = result;
                    }
                    WorkFlow.doDeleteList(basePath + 'invest/trade/trdfxswapinvesttrade/TrdFxswapInvestTrade001/delete', object, "TrdFxswapInvestTrade001_btn_revoke", "TrdFxswapInvestTrade002");//删除
                });
            });
        });
        //组合点击查看详情
        table.bind('click','.pro-link', function(row) {
            Joyin.dialog(JoyinUtil.getLabel("PtlManagementInfo002"),
                    basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
                    "95%", "90%",{
                    pageModel:PAGE_MODEL_REFERENCE,
                    portfolioId:row.portfolioId,
                    portfolioName: row.portfolioName});
            
        });
    });
}());