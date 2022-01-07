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
        //产品代码 多选择
        var portfolioIdTemp = $('#portfolioId').val();
        var portfolioId = null;
        if(portfolioIdTemp != null){
            portfolioId = portfolioIdTemp.join(",");
        }

        //资产代码  多选择
        var finprodIdTemp = $('#finprodId').val();
        var finprodId = null;
        if(finprodIdTemp != null){
            finprodId = finprodIdTemp.join(",");
        }

        //交易类型  多选择
        var psTemp = $('#ps').val();
        var ps = null;
        if(psTemp && psTemp != null && psTemp != ''){
            ps = psTemp.join(",");
        }
        return {
            tradeId:$('#tradeId').val(),
            portfolioId:portfolioId,
            finprodType:$('#finprodType').val(),
            finprodId:finprodId,
            ps:ps,
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
                    basePath + "valuation/trademanage/trdfixincomevaluationtrade/TrdFixIncomeValuationTrade002/init",
                    "98%", "98%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
                    portfolioId:data.portfolioId,
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
            ,{field:'tradeDate', width:150, title: '交易日'}
            ,{field:'settleDate', width:150, title: '交割日'}
            ,{field:'invAimStr', width:150, title: '会计分类'}
            ,{field:'tradeAmt', width:150, title: '本金金额',type:'number'}
            ,{field:'fpriceAmt', width:150, title: '全价金额',type:'number'}
            ,{field:'intAmt', width:150, title: '应收利息总额',type:'number'}
            ,{field:'ccy', width:150, title: '币种',sortField:'ccy'}
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
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        var object = {selectedItem: [data], operateType:"edit"};//请求参数
        Joyin.dialog(JoyinUtil.getLabel("固收类交易-修改"),
            basePath + "valuation/trademanage/trdfixincomevaluationtrade/TrdFixIncomeValuationTrade002/init",//打开新界面
            "98%", "98%", {pageModel:PAGE_MODEL_EDIT,//请求的page_model
                tradeId:data.tradeId  //金融产品代码
            },function(){
                table.reload();//回调函数，表格刷新
            });
    });

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
            WorkFlow.doDeleteList(
                basePath + 'valuation/trademanage/trdfixincomevaluationtrade/TrdFixIncomeValuationTrade001/delete',
                object,
                "TrdFixIncomeValuationTrade001_btn_delete",
                "TrdFixIncomeValuationTrade002",
                function() {
                    table.reload();
                }
            );
        });
    });
    
    /**
     * 画面加载完成
     */
    $(function() {
        $('#TrdFixIncomeValuationTrade001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("TrdFixIncomeValuationTrade002"),
                    basePath+ "valuation/trademanage/trdfixincomevaluationtrade/TrdFixIncomeValuationTrade002/init",
                        "98%", "98%", {pageModel:PAGE_MODEL_NEW});
        });
       
        //导出
        $('#TrdFixIncomeValuationTrade001_btn_export').click(function() {
            exportExcel();
        });
        //查询
        $('#TrdFixIncomeValuationTrade001_btn_search').click(function(){
            table.load();
        });

    });
}());