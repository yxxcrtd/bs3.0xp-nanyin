(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var objDiv= $("#RepFinProductBalance_DIV");
        var param = $.serializeToObject('#searchForm');
        if(param.portfolioId){
            param.portfolioId = param.portfolioId.join(",");
        }
        if(param.finprodId){
            param.finprodId = param.finprodId.join(",");
        }
        param.vdateStart = objDiv.find("input[name='vdate']").startDate();//起息日
        param.vdateEnd = objDiv.find("input[name='vdate']").endDate();//起息日
        param.mdateStart = objDiv.find("input[name='mdate']").startDate();//起息日
        param.mdateEnd = objDiv.find("input[name='mdate']").endDate()//起息日
        return param;
    };
    /**
     * 定义数据表格
     */
    
    var table=$("#dataTable").joyintable({
            ajax: {
                url: 'findPage',//请求路径
                type: 'POST',//请求类型
                data: function() {
                    return getSearchParams();//获取请求参数
                }//ajax请求数据
            },
        /**
         * 定义表头
         */
        cols:[
             {field:'finprodId',  title: 'RepFinProductBalance.finprodId',visible:false} //金融产品代码
            ,{field:'finprodMarketId',  title: 'RepFinProductBalance.finprodId',sortField:'FINPROD_MARKET_ID',width:200}//资产代码
            ,{field:'finprodAbbr',  title: 'RepFinProductBalance.finprodAbbr',sortField:'FINPROD_ABBR',width:200}//资产简称
            ,{field:'finprodName',  title: 'RepFinProductBalance.finprodName',sortField:'FINPROD_NAME',width:200}//资产简称
            ,{field:'portfolioId',  title: 'RepFinProductBalance.portfolioId',sortField:'PORTFOLIO_ID',width:200}//资产简称
            ,{field:'portfolioName',  title: 'RepFinProductBalance.portfolioName',sortField:'PORTFOLIO_NAME',width:200}//资产简称
            ,{field:'dealMode',  title: 'RepFinProductBalance.dealMode',sortField:'DEAL_MODE',width:200}//资产简称
            ,{field:'saleDepartment',  title: 'RepFinProductBalance.saleDepartment',sortField:'SALE_DEPARTMENT',width:200}//资产简称
            ,{field:'productType',  title: 'RepFinProductBalance.productType',sortField:'PRODUCT_TYPE',width:200}//资产简称
            ,{field:'ccy',  title: 'RepFinProductBalance.ccy',sortField:'CCY',width:200}//资产简称
            ,{field:'tradeAmt',  title: 'RepFinProductBalance.tradeAmt',type:'number',sortField:'TRADE_AMT',width:200}//资产简称
            ,{field:'tdyPrinAmt',  title: 'RepFinProductBalance.tdyPrinAmt',type:'number',sortField:'TDY_PRIN_AMT',width:200}//资产简称
            ,{field:'vdate',title:'RepFinProductBalance.vdate',type:'date',width:150}//起息日
            ,{field:'mdate',title:'RepFinProductBalance.mdate',type:'date',width:150}//到期日
            ,{field:'valuation',  title: 'RepFinProductBalance.valuation',type:'number',sortField:'VALUATION',width:200}//资产简称
            ,{field:'clientYield',  title: 'RepFinProductBalance.clientYield',type:'number',sortField:'CLIENTYIELD',width:200}//资产简称
            ,{field:'saleYield',  title: 'RepFinProductBalance.saleYield',type:'number',sortField:'SALEYIELD',width:200}//资产简称
            ,{field:'managerYield',  title: 'RepFinProductBalance.managerYield',type:'number',sortField:'MANAGERYIELD',width:200}//资产简称
            ,{field:'otherYield',  title: 'RepFinProductBalance.otherYield',type:'number',sortField:'OTHERYIELD',width:200}//资产简称
            ,{field:'issueTypeName',  title: 'RepFinProductBalance.issueType',sortField:'ISSUE_TYPE',width:200}//资产简称
        ]
    });
    
    
    /**
     * 导出选中的表格数据
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();//请求参数json格式
        Joyin.exportExcel('export', paramObj);//导出（共通）
    };
    
    
    /**
     * 画面加载完成
     */
    $(function() {
        //导出
        $('#RepFinProductBalance_btn_export').click(exportExcel);
        //初始化查询
        $('#RepFinProductBalance_btn_search').click(function() {
            if($('#queryDate').val()==''){
                Joyin.error("请输入日期。");
                return;
            }
            table.load();
        });
    });
}());