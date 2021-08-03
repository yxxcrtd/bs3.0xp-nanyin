(function(){
    /** 获取查询参数 **/
        //质押式回购行情查询

    /** 定义数据表格 **/
        //现券买卖历史报价查询
    var dvpTransferTable=$("#dvpTransferTable").joyintable({
            ajax: {
                url: 'queryPage',//请求路径
                type: 'POST',//请求类型
                data: function() {
                    return getDvpTransferSearchParams();//获取请求参数
                }//ajax请求数据
            },
            /**
             * 定义表格名称
             */
            cols:[
                {field:'transVariety', title: '交易品种', width:100 ,sortField:'B_Q_I_TRANS' ,render : function(data, type, row, meta) {
                        if (data == ps_pay_name){
                            return '<a style="color:red;">' + data + '</a>';
                        }else if (data == ps_sell_name){
                            return '<a style="color:green;">' + data + '</a>';
                        }
                    }
                }//交易品种
                ,{field:'weightAvgRate', title: 'FinBankQuotationInfo001.weightAvgRate', width:150 ,sortField:'B_Q_I_AVG_RATE'}//加权平均利率
                ,{field:'weightAvgRateDebt', title: 'FinBankQuotationInfo001.weightAvgRateDebt', width:200 ,sortField:'B_Q_I_AVG_RATE_DEBT'}//加权平均利率(利率债)
                ,{field:'newRate', title: 'FinBankQuotationInfo001.newRate', width:150 ,sortField:'B_Q_I_NEW_RATE'}//最新利率
                ,{field:'openRate', title: 'FinBankQuotationInfo001.openRate', width:150 ,sortField:'B_Q_I_OPEN_RATE'}//开盘利率
                ,{field:'maxRate', title: 'FinBankQuotationInfo001.maxRate', width:150 ,sortField:'B_Q_I_MAX_RATE'}//最高利率
                ,{field:'mixRate', title: 'FinBankQuotationInfo001.mixRate', width:150 ,sortField:'B_Q_I_MIX_RATE'}//最低利率
                ,{field:'avgRepTerm', title: 'FinBankQuotationInfo001.avgRepTerm', width:150 ,sortField:'B_Q_I_AVG_REPO_TERM'}//平均回购期限
            ],
            dblclick:function(table, data) {
                data.operateType = "dblclick";
                data.pageModel=PAGE_MODEL_REFERENCE;
                var quoteType = data.quoteType;
                var quoteRoleType = data.quoteRoleType;
                // var isDzh = data.isDzh;
                var label = "FinBankQuotationInfo001"; var url = "invest.basicdata.finbankquotationinfo/FinBankQuotationInfo001/init";
            }
        });





    //买断式回购行情  todo
    var transferTable=$("#transferTable").joyintable({
        ajax: {
            url: 'queryPage',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getTransferSearchParams();//获取请求参数
            }//ajax请求数据
        },
        /**
         * 定义表格名称
         */
        cols:[
            {field:'transVariety', title: '交易品种', width:100 ,sortField:'B_Q_I_TRANS' ,render : function(data, type, row, meta) {
                    if (data == ps_pay_name){
                        return '<a style="color:red;">' + data + '</a>';
                    }else if (data == ps_sell_name){
                        return '<a style="color:green;">' + data + '</a>';
                    }
                }
            }//交易品种
            ,{field:'weightAvgRate', title: 'FinBankQuotationInfo001.weightAvgRate', width:150 ,sortField:'B_Q_I_AVG_RATE'}//加权平均利率
            ,{field:'newRate', title: 'FinBankQuotationInfo001.newRate', width:150 ,sortField:'B_Q_I_NEW_RATE'}//最新利率
            ,{field:'openRate', title: 'FinBankQuotationInfo001.openRate', width:150 ,sortField:'B_Q_I_OPEN_RATE'}//开盘利率
            ,{field:'maxRate', title: 'FinBankQuotationInfo001.maxRate', width:150 ,sortField:'B_Q_I_MAX_RATE'}//最高利率
            ,{field:'mixRate', title: 'FinBankQuotationInfo001.mixRate', width:150 ,sortField:'B_Q_I_MIX_RATE'}//最低利率
            ,{field:'avgRepoTerm', title: 'FinBankQuotationInfo001.avgRepTerm', width:150 ,sortField:'B_Q_I_AVG_REPO_TERM'}//平均回购期限
        ],
        dblclick:function(table, data) {
            data.operateType = "dblclick";
            data.pageModel=PAGE_MODEL_REFERENCE;

        },
    });



    /**
     * 导出
     */
        //质押式回购行情查询
    var dvpExportExcel = function() {
            var paramObj = getDvpTransferSearchParams();//获取参数
            paramObj.tableId='dvpTransferTable';
            Joyin.exportExcel('export', paramObj);//导出
        };
    //买断式回购行情查询
    var exportExcel = function() {
        var paramObj = getTransferSearchParams();//获取参数
        paramObj.tableId='transferTable';
        Joyin.exportExcel('export', paramObj);//导出
    };

    /**
     * 画面加载完成
     */
    $(function() {

        /** 绑定查询按钮 **/
        //质押式回购行情查询
        $('#FinBankQuotationInfo001_btn_zySearch').click(function() {
            dvpTransferTable.table ? dvpTransferTable.reload() : dvpTransferTable.load();
        });
        //买断式回购行情查询
        $('#FinBankQuotationInfo001_btn_mdSearch').click(function() {
            transferTable.table ? transferTable.reload() : transferTable.load();
        });


        /** 绑定导出按钮 **/
        $('#CfetsHisDealQuery001_btn_export').click(function() {
            //质押式回购行情查询
            dvpExportExcel();//导出
        });

        $('.substitutionSearchDiv #CfetsHisDealQuery001_btn_export').click(function() {
            //买断式回购行情查询
            exportExcel();
        });

        /*$('#CfetsHisDealQuery001_btn_repoExport').click(function() {
            exportExcel();//导出
        });
*/

    });

}());