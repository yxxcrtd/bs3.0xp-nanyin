$(function(){
    /**
     * 获取查询参数
     */
    var getPageParams = function() {

        //产品代码 多选择
        var portfolioIdTemp = $('#portfolioId').val();
        var portfolioId = null;
        if(portfolioIdTemp != null){
            portfolioId = portfolioIdTemp.join(",");
        }

        //资产代码  多选择
        var finprodMarketIdTemp = $('#finprodMarketId').val();
        var finprodMarketId = null;
        if(finprodMarketIdTemp != null){
            finprodMarketId = finprodMarketIdTemp.join(",");
        }

        //交易类型  多选择
        var psTemp = $('#ps').val();
        var ps = null;
        if(psTemp != null){
            ps = psTemp.join(",");
        }

        return {
            tradeId: $('#tradeId').val(),
            portfolioId: portfolioId,
            finprodMarketId: finprodMarketId,
            tradeDateBegin: $("#tradeDate").startDate(),
            tradeDateEnd: $("#tradeDate").endDate(),
            ps: ps
        }
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
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "valuation/trademanage/trddepositvaluationtrade/TrdDepositValuationTrade002/init",
                    "98%", "98%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
                    portfolioId:data.portfolioId,
                    entrustId:data.entrustId,
                    finprodId:data.finprodId,
                    tradeId:data.tradeId});
        },
        cols:[
            {field:'tradeId', width:150, sortField:'tradeId',title: '交易编号'}
            ,{field:'outTradeId', width:150, sortField:'outTradeId',title: '外部交易编号'}
            ,{field:'portfolioId', width:150, title: '产品代码',sortField:'portfolioId'}
            ,{field:'portfolioName', width:150, title: '产品名称',sortField:'portfolioName'}
            ,{field:'finprodMarketId', width:150, sortField:'finprodMarketId',title: '资产代码'}
            ,{field:'finprodName', width:150, sortField:'finprodName',title: '资产名称'}
            ,{field:'psStr', width:150, title: 'TrdDepositValuation001.ps',sortField:'ps'}
            ,{field:'ps', title: '交易类型',visible:false}
            ,{field:'tradeDate', width:150,  title: '交易日',type:'date'}
            ,{field:'settDate', width:150,  title: '交割日',type:'date'}
            ,{field:'prinAmt', width:150, title: '交易本金',type:'number',sortField:'prinAmt'}
            ,{field:'intAmt', width:150, title: '应记利息',type:'number',sortField:'intAmt'}
            ,{field:'counterId', title: '交易对手',width:100}
            ,{field:'ccyFmt', width:150, title: '币种',sortField:'ccy'}
            ,{field:'ccy', title: '币种',visible:false}
            ,{field:'settleAmt', width:150, title: '结算金额',type:'number',sortField:'settleAmt'}
            ,{field:'dealAmt', width:150, title: '本币交易金额',type:'number',sortField:'dealAmt'}
            ,{field:'trdAcctId', title: '资金账户',width:100}
            ,{field:'action', title: '操作' , type:'action',buttons: ['edit', 'delt'] , width:'10%'}
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
        $('#TrdDepositValuationTrade001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("TrdDepositValuationTrade002"),
                    basePath+ "valuation/trademanage/trddepositvaluationtrade/TrdDepositValuationTrade002/init",
                        "98%", "98%", {pageModel:PAGE_MODEL_NEW});
        });
       
        //导出
        $('#TrdDepositValuationTrade001_btn_export').click(function() {
            exportExcel();
        });
        //查询
        $('#TrdDepositValuationTrade001_btn_search').click(function(){
            table.load();
        });
        //撤销操作
        $('#TrdDepositValuationTrade001_btn_revoke').click(function(){
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
            Joyin.ajaxJson(basePath + 'invest/trade/trddepositinvesttrade/TrdDepositInvestTrade001/check', object, function(result) {//数据验证
//                Joyin.confirm(JoyinUtil.getMessage('COMMON_C_0001',JoyinUtil.getMessage(JoyinUtil.getLabel("SysCommon.delete"))), function(){
                Joyin.prompt(JoyinUtil.getLabel('TrdDepositInvestTrade001.cancelText'), 0, function (result) {
                    for(var select in object.selectedItem){
                        object.selectedItem[select].cancelRemark = result;
                    }
//                    WorkFlow.doRevokeList(basePath + 'invest/trade/trddepositinvesttrade/TrdDepositInvestTrade001/delete', object, "TrdDepositInvestTrade001_btn_delete", "TrdDepositInvestTrade002");//删除
                    WorkFlow.doDeleteList(basePath + 'invest/trade/trddepositinvesttrade/TrdDepositInvestTrade001/delete', object, "TrdDepositInvestTrade001_btn_revoke", "TrdDepositInvestTrade002");//删除
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
        //资产代码点击查看明细
        table.bind('click','.pro-link-fin', function(row) {
            Joyin.dialog(JoyinUtil.getLabel("FinDepositTarget002"),
                    basePath + "invest/asset/depo/FinDepositTarget002/init",
                    "95%", "90%",{
                    pageModel:PAGE_MODEL_REFERENCE,
                    finprodId: row.finprodId});
        });
        //是否展示资产四五级分类
        if(IS_SHOW_TYPE == '01'){
        	$('#type11Div').show();
        }else{
        	$('#type11Div').hide();
        }
    });
}());