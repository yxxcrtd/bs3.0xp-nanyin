(function(){
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

        //交易类型  多选择
        var psTemp = $('#ps').val();
        var ps = null;
        if(psTemp != null){
            ps = psTemp.join(",");
        }

        return {
            tradeId: $('#tradeId').val(),
            portfolioId: portfolioId,
            tradeDateBegin: $("#tradeDate").startDate(),
            tradeDateEnd: $("#tradeDate").endDate(),
            ps: ps,
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
                basePath + "valuation/trademanage/trdlendvaluationtrade/TrdLendValuationTrade002/init",
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
            ,{field:'busiPs', width:150, title: '交易类型',sortField:'ps'}
            ,{field:'tradeDateStr', width:150,  title: '交易日',sortField:'tradeDate'}
            ,{field:'tradeDate',title: '交易日',visible:false}
            ,{field:'settDateStr', width:150,  title: '交割日',sortField:'settDate'}
            ,{field:'settDate', title: '交割日',visible:false}
            ,{field:'counterId', title: '交易对手',width:100}
            ,{field:'prinAmt', width:150, title: '本金金额',type:'number',sortField:'prinAmt'}
            ,{field:'intAmt', width:150, title: '应记利息',type:'number',sortField:'intAmt'}
            ,{field:'settleAmt', width:150, title: '结算金额',type:'number',sortField:'settleAmt'}
            ,{field:'dealAmt', width:150, title: '本币交易金额',type:'number',sortField:'dealAmt'}
            ,{field:'ccyFmt', width:150, title: '币种',sortField:'ccy'}
            ,{field:'ccy', title: '币种',visible:false}
            ,{field:'trdAcctId', title: '资金账户',width:100}
            ,{field:'action', title: '操作' , type:'action',buttons: ['edit', 'delt'] , width:'10%'}
        ],
        //loadonce:true
    });
  


    /**
     * 导出选中的表格数据
     */
    var exportExcel = function() {

        var selArr = [];
        var datas = null;
        if (table != null && table != '') {
            selArr = table.getAllCheckedRows();
            datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getPageParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };


    
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


        /**
         * 绑定编辑事件
         */
        table.bind('click', '.edit', function(data, index) {
            data.pageModel = PAGE_MODEL_EDIT;
            var object = {selectedItem: [data], operateType:"edit"};//请求参数
            Joyin.dialog(JoyinUtil.getLabel("信用拆借交易-修改"),
                basePath + "valuation/trademanage/trdlendvaluationtrade/TrdLendValuationTrade002/init",//打开新界面
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
                    basePath + 'valuation/trademanage/trdlendvaluationtrade/TrdLendValuationTrade001/delete',
                    object,
                    "TrdLendValuationTrade001_btn_delete",
                    "TrdLendValuationTrade002",
                    function() {
                        table.reload();
                    }
                );
            });
        });
    });
}());