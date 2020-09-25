$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        var result = $.serializeToObject('#searchForm');
        result.pledgeStatus = $("#pledgeStatus").val();
        var temp=result.sex;
        if($("#finprodId").val() == null || $("#finprodId").val() == ''){
            result.finprodId = $("#finprodId").val();
        }
        else{
            result.finprodId = $("#finprodId").val().join(',');
        }
        return result;
    };
    var dataTable=$("#dataTable").joyintable({
         ajax: {
             url: 'findPage',
             type: 'POST',
             data: function() {
                 return getSearchParams();
             }
         },
         checkbox: false,
         rowno: true,
         cols: [{field:'finprodMarketId', title: 'PtlAnalyzeAfterInvest001.finprodId'}//资产代码
            ,{field:'finprodAbbr', title: 'PtlAnalyzeAfterInvest001.finprodName'}//资产名称
            ,{field:'vdateStr', title: 'PtlAnalyzeAfterInvest001.vDate',type:'date',width:150}//起息日
            ,{field:'vdateStr', title: 'PtlAnalyzeAfterInvest001.mDate',type:'date',width:150}//到期日
            ,{field:'pledgeStockId', title: 'PtlAnalyzeAfterInvest001.pledgeStockId'}//质押股票代码
            ,{field:'pledgeStockName', title: 'PtlAnalyzeAfterInvest001.pledgeStockName'}//质押股票名称
            ,{field:'warningLine', title: 'PtlAnalyzeAfterInvest001.warningLine'}//预警线
            ,{field:'closeLine', title: 'PtlAnalyzeAfterInvest001.closeLine'}//平仓线
            ,{field:'presentPrice', title: 'PtlAnalyzeAfterInvest001.presentPrice'}//当前价格
            ,{field:'parentInfo', width:150, visible: false}
            /*,{field:'operation', title: 'SysCommon.operation', type: 'action', width: 20, buttons: [{
                	clas: 'markeToMarket',
                    text: JoyinUtil.getLabel('PtlAnalyzeAfterInvest001.markeToMarket'),
                    title: JoyinUtil.getLabel('PtlAnalyzeAfterInvest001.markeToMarket')
                }]//操作：盯市
            }*/
            ,{field: 'operation', title: 'SysCommon.operation', type: 'action', 
                render: function (data, type, row, meta) {
                   
                        if(purviewElements.indexOf('_manual')!=-1){
                            return '<i class="btn layui-icon manual" title="盯市">盯市</i>';
                        }
                    } 
                }
        ]
    });
    
    /**
     * 绑定盯市事件
     */
    dataTable.bind('click', '.manual', function(data, index) {
        var params = {
                finprodMarketId: data.finprodMarketId,
                finprodAbbr: data.finprodAbbr,
                pledgeStockId: data.pledgeStockId,
                pledgeStockName: data.pledgeStockName,
                warningLine: data.warningLine,
                closeLine: data.closeLine
            }
        Joyin.dialog(JoyinUtil.getLabel("PtlAnalyzeAfterInvest002"),
                basePath + "invest/portfolio/ptlanalyzeafterinvest/PtlAnalyzeAfterInvest002/init",
                "97%", "97%", params);
    });
    /**
     * 导出
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();//获取参数
        Joyin.exportExcel('export', paramObj);//导出
    };
    $(function () {
        //查询按钮
        $("#PtlAnalyzeAfterInvest001_btn_search").click(function () {
            dataTable.load();
        });
        //绑定导出按钮
        $('#PtlAnalyzeAfterInvest001_btn_export').click(function() {
            exportExcel();//导出
        });
    })
});
