$(function(){
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        // add by 2021-04-06  wbq  3.5投资交易查询优化 begin
        var createUser=$("#createUser").val()
        if(createUser){
            createUser=createUser.join(",");
        }
        // add by 2021-04-06  wbq  3.5投资交易查询优化 end
        var portfolioIdTemp = $('#portfolioId').val();
        var portfolioId = null;
        if(portfolioIdTemp != null)
            portfolioId = portfolioIdTemp.join(",");
        return {
            entrustId:$('#entrustId').val(),
            portfolioId:portfolioId,
            finprodType:$('#finprodType').val(),
           /* 增加2,3,4分类的查询参数、zhuyongkang 2019/7/30  start*/
            type9:$('#type9').val(),
            type10:$('#type10').val(),
            type11:$('#type11').val(),
          /* zhuyongkang 2019/7/30   end*/
            finprodId:$('#finprodId').val(),
            ps:$('#ps').val(),
            status:$('#status').val(),
            tradeDateBegin:$('#tradeDate').startDate(),
            tradeDateEnd:$('#tradeDate').endDate(),
            createUser:createUser
        };
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
                    basePath + "valuation/trademanage/trdfundvaluationtrade/TrdFundValuationTrade002/init",
                    "98%", "98%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
                    portfolioId:data.portfolioId,
                    entrustId:data.entrustId,
                    finprodId:data.finprodId,
                    tradeId:data.tradeId});
        },
        cols:[
            {field:'tradeId', width:180, title: '交易编号'}
            ,{field:'outTradeId', width:150, title: '外部成交编号'}
            ,{field:'portfolioId', width:150, title: '产品代码'}
            ,{field:'portfolioName', width:150, title: '产品名称'}
            ,{field:'finprodId', width:150, title: '资产代码'}
            ,{field:'finprodName', width:150, title: '资产名称'}
            ,{field:'psStr', width:150, title: '交易类型',sortField:'ps'}
            ,{field:'tradeMarketStr', width:150, title: '交易场所',sortField:'tradeMarket'}
            ,{field:'tradeDate', type:'date' ,width:150, title: '交易日', visible: false}
            ,{field:'tradeDateStr',width:150, title: '交易日'}
            ,{field:'tradeAmt', width:150, title: '交易金额',type:'number'}
            ,{field:'nums', width:150, title: '交易份额',type:'number'}
            ,{field:'unitValue', width:150, title: '单位净值',type:'number'}
            ,{field:'ccy', width:150, title: '币种',sortField:'ccy'}
            ,{field:'mdateSettleAmt', width:150, title: '结算金额',type:'number'}
            ,{field:'assetAccount', width:150, title: '资金账户'}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });
    /**
     * 导出事件
     */
    var exportExcel = function() {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };
    
    /**
     * 画面加载完成
     */
    $(function() {
        $('#TrdFundValuationTrade001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("TrdFundValuationTrade002"),
                    basePath+ "valuation/trademanage/trdfundvaluationtrade/TrdFundValuationTrade002/init",
                        "98%", "98%", {pageModel:PAGE_MODEL_NEW});
        });
       
        //导出
        $('#TrdFundValuationTrade001_btn_export').click(function() {
            exportExcel();
        });
        //查询
        $('#TrdFundValuationTrade001_btn_search').click(function(){
            table.load();
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
            Joyin.dialog(JoyinUtil.getLabel("FinPlanProfitAsset002"),
                    basePath + "invest/asset/plan/planProfit/FinPlanProfitAsset002/init",
                    "95%", "90%",{
                    pageModel:PAGE_MODEL_REFERENCE,
                    finprodId: row.finprodId});
        });
    });
}());