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
        if ($("#mDate").val()) {
            result.mdateStart = $("#mDate").startDate();
            result.mdateEnd = $("#mDate").endDate();
        }
        if ($("#arrivalDate").val()) {
            result.arrivalDateStart = $("#arrivalDate").startDate();
            result.arrivalDateEnd = $("#arrivalDate").endDate();
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
        searchOnInit :REM_TYPE && (REM_TYPE == 'R2S4'||REM_TYPE == 'R2S5'||REM_TYPE == 'R2S6'||REM_TYPE == 'R3S10'),
        dblclick: function (table, data) {
            //在新建界面进行显示单行的详细信息
            data.pageModel = PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("TrdPlanInterestDuration002.edit"),
                basePath + "invest/period/trdplanintduration/TrdPlanInterestDuration002/init",
                "98%", "95%", data, function () {
                });
        },
        cols: [
            {field: 'statusValidString', width: 200, title: '有效状态', sortField: 'statusValid'}
            , {field: 'busiCode', width: 200, title: '业务编号', sortField: 'busiCode'}
            , {field: 'paBussTypeString', width: 200, title: '类型', sortField: 'PA_BUSS_TYPE'}
            , {field: 'finprodMarketId', width: 200, title: '资产代码', sortField: 'FINPROD_MARKET_ID',
                render : function(data, type, row, meta) {
                    if(row.finprodMarketId !=undefined){
                        return "<a onclick=\"showDetailByFinprodId('"+row.finprodId+"')\" + style='cursor: pointer;'>"+row.finprodMarketId+"</a>";
                    }
                }}
            , {field: 'finprodMarketName', width: 200, title: '资产名称', sortField: 'finprodMarketName'}
            , {field: 'qty', width: 200, title: '剩余本金', type: 'number', sortField: 'qty'}
            , {field: 'returnAmount', width: 200, title: '还本金额', type: 'number', sortField: 'returnAmount'}
            , {field: 'yield', width: 200, title: '预期收益率%', type: 'number', sortField: 'yield'}
            , {field: 'vDate', width: 200, title: '计息开始日', sortField: 'vDate'}
            , {field: 'mDate', width: 200, title: '计息结束日', sortField: 'mDate'}
            , {field: 'interestPayDay', width: 200, title: '理论支付日', sortField: 'interestPayDay'}
            , {field: 'arrivalDate', width: 200, title: '资金到账日', sortField: 'arrivalDate'}
            , {field: 'fpriceAmt', width: 200, title: '到账金额', type: 'number', sortField: 'FPRICE_AMT'}
            , {field: 'statusValid', visible: false}
            , {field: 'cashType', visible: false}
            , {field: 'workflowId', visible: false}

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
        var confirmTrade = function (data, PAGE_MODEL_EDIT) {
            data.pageModel = PAGE_MODEL_EDIT;
            Joyin.dialog(JoyinUtil.getLabel("TrdPlanInterestDuration002.edit"),
                basePath + "invest/period/trdplanintduration/TrdPlanInterestDuration002/init",
                "98%", "95%", data, function () {
            	 table.reload();
                });
        };
        confirmTrade(data, PAGE_MODEL_NEW);
    });

    var exportExcel = function () {
        Joyin.exportExcel('export', getSearchParams());
    };

    /**
     * 画面加载完成
     */
    $(function () {
        $("#TrdPlanInterestDuration001_btn_search").click(function () {
            table.load();
        })

        $("#TrdPlanInterestDuration001_btn_export").click(exportExcel);

        $("#TrdPlanInterestDuration001_btn_revoke").click(function () {
            var rows = table.getAllCheckedRows();
            if (rows.length != 1) {
                Joyin.error(JoyinUtil.getMessage('请选择一条数据'))
                return;
            }
            var object = rows[0];
            object.operateType = "revoke";
            Joyin.ajax(basePath + 'invest/period/trdplanintduration/TrdPlanInterestDuration001/revokeCheck', object, function (result) {
                Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                    result = result.replace(/\s+/g,"");
                    if (!result) {
                        result = '无';
                    }
                    object.revokeReason = result;
                    WorkFlow.doRevoke(basePath + 'invest/period/trdplanintduration/TrdPlanInterestDuration001/revoke', object, "TrdPlanInterestDuration001_btn_revoke", "TrdPlanInterestDuration002",function(){
                        table.reload();//回调事件请求及回调函数
                    });
                });
            });
        })
    });
}());

//不同资产的查看页面
var showDetailByFinprodId=function(finprodId){
    var label= '计划类(利率型)资产标的信息查看';
    var pagePath = "invest/asset/plan/planProfit/FinPlanProfitAsset002/init";
    Joyin.dialog(JoyinUtil.getLabel(label),basePath+pagePath,
        "95%", "90%",{
            pageModel:PAGE_MODEL_REFERENCE,
            finprodId:finprodId
        });
}