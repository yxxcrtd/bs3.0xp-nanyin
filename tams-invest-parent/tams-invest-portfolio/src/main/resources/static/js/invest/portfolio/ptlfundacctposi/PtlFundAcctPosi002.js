$(function() {
//	var flag = true;
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return{
            busiType: $('#busiType').val(),//业务类型
            ps: $('#ps').val(),//业交易方向
            portfolioId:PORTFOLIO_ID,
            hodingType:HODING_TYPE,
            acctId:ACCT_ID,
            ccy:CCY,
            cdate:CDATE,
            index:'1'
        }
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
        /*done:function(){
        	flag = false;
        },*/
        /**
         * 定义表格名称
         */
        cols:[
             {field:'cdateStr', sortField:'cdate', title: 'PtlFundAcctPosi001.date'}//,sortField:'tradeStatus'
             ,{field:'portfolioId', title: 'PtlActivityPeriod001.portfolioId'}
             ,{field:'portfolioName', title: 'PtlActivityPeriod001.portfolioName'}
             ,{field:'busiType', visible:false}
             ,{field:'busiTypeStr', title: '类型',sortField:'FINPROD_TYPE_TG'}
             ,{field:'ps', title: '交易方向',sortField:'PS'}
             ,{field:'busiNo', title: 'PtlFundAcctPosi002.busiNo'}
             ,{field:'finprodMarketId', title: 'PtlFundAcctPosi002.finprodMarketId'}
             ,{field:'finprodAbbr', title: 'PtlFundAcctPosi002.finprodAbbr'}
             ,{field:'amount', title: 'PtlFundAcctPosi001.amount2',type:'money'}
             ,{field:'ccyStr', sortField:'ccy', title: 'PtlFundAcctPosi001.ccy'}
        ]

    });
    
    /**
     * 绑定编辑事件
     */
    /*table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.dialog(JoyinUtil.getLabel("PtlActivityPeriod001.edit"),
                basePath +"invest/portfolio/ptlactivityperiod/PtlActivityPeriod002/init",
                "80%", "80%", {pageModel:PAGE_MODEL_EDIT,//page_model:编辑
                                portfolioId:data.portfolioId});//主键组合代码
        
    });*/
    
    /**
     * 导出
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();//获取参数
        Joyin.exportExcel('export', paramObj);//导出
    };
    /**
     * 画面加载完成
     */
    $(function() {
    	table.load();//加载表格
        // 绑定查询按钮
        $('#PtlFundAcctPosi002_btn_search').click(function() {
            table.load();//加载表格
//            flag = false;
        });
        //绑定到处按钮
        $('#PtlFundAcctPosi002_btn_export').click(function() {
            exportExcel();//导出
        });
    });
}());