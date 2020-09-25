(function () {

    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        var result = $.serializeToObject('#searchForm');
        if ($("#statusValid").val()) {
            result.statusValid = $("#statusValid").val().join(",");
        } else {
            result.statusValid = null;
        }
        if ($("#portfolioId").val()) {
            result.portfolioId = $("#portfolioId").val().join(",");
        } else {
            result.portfolioId = null;
        }
        if ($("#ps").val()) {
            result.ps = $("#ps").val().join(",");
        } else {
            result.ps = null;
        }

        if($("#tradeDate").val()) {
            result.tradeDateStart = $("#tradeDate").startDate();
            result.tradeDateEnd = $("#tradeDate").endDate();
        }

        if($("#mDate").val()) {
            result.mdateStart = $("#mDate").startDate();
            result.mdateEnd = $("#mDate").endDate();
        }
        return result;
    };

    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function () {
                return getSearchParams();
            }
        },
        searchOnInit :REM_TYPE && (REM_TYPE == 'R2S4'||REM_TYPE == 'R2S5'||REM_TYPE == 'R2S6'||REM_TYPE == 'R3S15'),
        dblclick: function (table, data) {
            //在新建界面进行显示单行的详细信息
            data.pageModel = PAGE_MODEL_REFERENCE;
            Joyin.dialog("买断式回购存续期交易查看",
                basePath + "invest/period/trdbuyoutduration/TrdRepoBuyoutDuration002/init",
                "98%", "95%", data, function () {
                });
        },
        sortfont:true,
        cols: [
            {field: 'statusValidString',sortField:"statusValid", width: 200, title: '有效状态'}
            , {field: 'busiCode', width: 200, title: '业务编号'
                // , render: function (data, type, row, meta) {
                //     return "<a class='pro-link' style='cursor: pointer;' " +
                //         "href='javascript:openOperateWindow(\"" + row.finprodId + "\")'>" + data||'' + "</a>";
                // }
            }
            , {field: 'portfolioId', sortField:"PORTFOLIO_ID", width: 200, title: '组合代码',
                render : function(data, type, row, meta) {
                    return '<a onclick="showPtlManagement(\'' + row.portfolioId
                        + '\')" class="pro-link"  style="cursor: pointer;">'+row.portfolioId+'</a>';
                }
            }
            , {field: 'portfolioName', sortField:"PORTFOLIO_ID",width: 200, title: '组合名称'}
            , {field: 'type6String', sortField:"TYPE_6", width: 200, title: '回购类型'}
            , {field: 'finprodType2String',sortField:"FINPROD_TYPE2", width: 200, title: '交易方向'}
            , {field: 'tradeDate',sortField:"TRADE_DATE", width: 200, title: '交易日'}
            , {field: 'vDate', width: 200, title: '起息日'}
            , {field: 'mDate', width: 200, title: '到期日'}
            , {field: 'theoryPrin',sortField:"THEORY_PRIN", width: 200, title: '本金金额（元）', type: 'money'}
            , {field: 'settleDate',sortField:"SETTLE_DATE", width: 200, title: '资金到账日'}
            , {field: 'theoryInt',sortField:"THEORY_INT", width: 200, title: '回购利息', type: 'money'}
            , {field: 'tradeAmt',sortField:"TRADE_AMT", width: 200, title: '到期结算金额', type: 'money'}
            , {field: 'statusValid', visible: false}
            , {field: 'type6', visible: false}
            , {field: 'workflowId', visible: false}
            , {field: 'finprodType2', visible: false}
            , {
                field: 'operation', title: 'SysCommon.operation', type: 'action', buttons: ['edit']
                ,
                render: function (data, type, row, meta) {
                    if (row.statusValid != '01')
                        return "";
                    else
                        //modifiled by liupengfei for 多级法人改造 确认按钮权限控制 S
                        if(purviewElements.indexOf('_edit')!=-1){
                            return '<i class="btn layui-icon edit" title="确认">&#x1005;</i>';
                        }
                        //modifiled by liupengfei for 多级法人改造 确认按钮权限控制 E
                }
            }
        ],
        checkbox: true,
        progressBar: true
    });

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function (data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.dialog("买断回购存续期交易确认",
            basePath + "invest/period/trdbuyoutduration/TrdRepoBuyoutDuration002/init",
            "98%", "95%", data, function () {
            });
    });

    var exportExcel = function () {
        Joyin.exportExcel('export', getSearchParams());
    };

    /**
     * 画面加载完成
     */
    $(function () {
        $("#TrdRepoBuyoutDuration001_btn_search").click(function () {
            table.load();
        })
        $("#TrdRepoBuyoutDuration001_btn_revoke").click(function () {
            var rows = table.getAllCheckedRows();
            if (rows.length != 1) {
                Joyin.error(JoyinUtil.getMessage('请选择一条数据'))
                return;
            }
            var object = rows[0];
            object.operateType = "revoke";
            Joyin.ajax(basePath + 'invest/period/trdbuyoutduration/TrdRepoBuyoutDuration001/revokeCheck', object, function (result) {
                Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                    result = result.replace(/\s+/g,"");
                    if (!result) {
                        result = '无';
                    }
                    object.revokeReason = result;
                    WorkFlow.doRevoke(basePath + 'invest/period/trdbuyoutduration/TrdRepoBuyoutDuration001/revoke', object, "TrdRepoBuyoutDuration001_btn_revoke", "TrdRepoBuyoutDuration002");
                });
            });
        })
        $("#TrdRepoBuyoutDuration001_btn_export").click(exportExcel)
    });
}());

var openOperateWindow = function (tradeId) {
    Joyin.dialog('买断式回购交易查看',
        basePath + "invest/trade/trdrepotrade/TrdRepoTrade002/init?tradeId="+tradeId,
        "98%", "98%", {}, function () {
        });
}

/**
 * 组合信息查看页面
 */
var showPtlManagement=function(portfolioId){
    Joyin.dialog(JoyinUtil.getLabel("PtlManagementInfo002.see"),
        basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
        "95%", "90%",{
            pageModel:PAGE_MODEL_REFERENCE,
            portfolioId:portfolioId,
        });
}