var TrdIRSDuration001;
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
        if ($("#vdate").val()) {
        	result.vdateStart = $("#vdate").startDate();
        	result.vdateEnd = $("#vdate").endDate();
        }
        if ($("#mdate").val()) {
            result.mdateStart = $("#mdate").startDate();
            result.mdateEnd = $("#mdate").endDate();
        }
        if ($("#payDate").val()) {
            result.payDateStart = $("#payDate").startDate();
            result.payDateEnd = $("#payDate").endDate();
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
        dblclick:function(table, data) {
            //在新建界面进行显示单行的详细信息
            data.pageModel = PAGE_MODEL_REFERENCE;
            if(data.beforeEndPs){
                Joyin.dialog("利率互换提前终止交易查看",
                    basePath + "invest/period/trdirsduration/TrdIRSDuration003/init",
                    "98%", "95%", data, function(){});
            } else {
                Joyin.dialog("利率互换收付息交易查看",
                    basePath + "invest/period/trdirsduration/TrdIRSDuration002/init",
                    "98%", "95%", data, function(){});
            }
        },
        cols: [
              {field: 'statusValidString', width: 120, title: 'TrdIRSDuration001.statusValid', sortField: 'statusValid'}
            , {field: 'busiCode', width: 100, title: 'TrdIRSDuration001.busiCode',sortField:'busiCode'}
            , {field: 'portfolioId', width:150, title: 'TrdIRSDuration001.portfolioId',
                render : function(data, type, row, meta) {
                    return '<a onclick="showPtlManagement(\'' + row.portfolioId
                        + '\')" class="pro-link"  style="cursor: pointer;">'+row.portfolioId+'</a>';
                }}
            , {field: 'portfolioName', width:150, title: 'TrdIRSDuration001.portfolioName'}
            , {field: 'finprodId', width:180, title: 'TrdIRSDuration001.finprodId',sortField:'finprodId',
                render : function(data, type, row, meta) {
                    return "<a onclick=\"showDetailByFinprodId('"+row.finprodId+"')\" + style='cursor: pointer;'>"+row.finprodId+"</a>";
                }}
            , {field: 'tradeDate', width:150, title: 'TrdIRSDuration001.tradeDate',type:'date', sortField:'tradeDate'}
            , {field: 'irsTypeName', width:150, title: 'TrdIRSDuration001.irsType', sortField:'irsType'}
            , {field: 'vdate', width:150, title: 'TrdIRSDuration001.vdate',type:'date', sortField:'vdate'}
            , {field: 'mdate', width:150, title: 'TrdIRSDuration001.mdate',type:'date', sortField:'mdate'}
            , {field: 'ccyName', width:150, title: 'TrdIRSDuration001.ccy', sortField:'ccy'}
            , {field: 'restPrin', width:150, title: 'TrdIRSDuration001.restPrin', type:'money',sortField:'restPrin'}
            , {field: 'pridBussTypeName', width:150, title: 'TrdIRSDuration001.pridBussType', sortField:'pridBussType'}
            , {field: 'irsPsIntTypeName', width:150, title: 'TrdIRSDuration001.irsPsIntType', sortField:'irsPsIntType'}
            , {field: 'fixActPayDate', width:150, title: 'TrdIRSDuration001.fixActPayDate',type:'date', sortField:'fixActPayDate'}
            , {field: 'fixIntAmt', width:150, title: 'TrdIRSDuration001.fixIntAmt', type:'money',sortField:'fixIntAmt'}
            , {field: 'floatActPayDate', width:150, title: 'TrdIRSDuration001.floatActPayDate',type:'date', sortField:'floatActPayDate'}
            , {field: 'floatIntAmt', width:150, title: 'TrdIRSDuration001.floatIntAmt', type:'money',sortField:'floatIntAmt'}
            //增加提前终止日 add by xueying 20200728 start
            , {field: 'beforeEndDate', width:150, title: 'TrdIRSDuration001.beforeEndDate', type:'date',sortField:'beforeEndDate'}
            //增加提前终止日 add by xueying 20200728 end
            , {field: 'beforeEndPrin', width:150, title: 'TrdIRSDuration001.beforeEndPrin',type:'money',sortField:'beforeEndPrin'}
            , {field: 'beforeEndPsName', width:150, title: 'TrdIRSDuration001.beforeEndPs', sortField:'beforeEndPs'}
            , {field: 'beforeEndAmt', width:150, title: 'TrdIRSDuration001.beforeEndAmt', type:'money',sortField:'beforeEndAmt'}
            , {field: 'remark', width:150, title: 'TrdIRSDuration001.remark', sortField:'remark'}
            , {field: 'operation', title: 'SysCommon.operation', type: 'action', buttons: ['edit']
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
            Joyin.dialog(JoyinUtil.getLabel('TrdIRSDuration002'),
                basePath + "invest/period/trdirsduration/TrdIRSDuration002/init",
                "98%", "95%", data, function () {
                });
        };
        confirmTrade(data, PAGE_MODEL_NEW);
    });

    /**
     * 导出
     */
    var exportExcel = function () {
        Joyin.exportExcel('export', getSearchParams());
    };
    /**
     * 打开付息计划维护画面
     */
    var psIntPlanClick = function () {

        var data = table.getAllCheckedRows();
       // 列表中未勾选
        if (!data || data.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        // 列表中勾选对象1条以上时
        if (data.length > 1) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
            return;
        }
        Joyin.dialog("利率互换收付息计划维护",
            basePath + "invest/period/trdirsduration/TrdIRSDuration004/init",
            "98%", "95%", data[0], function () {
            });
    };

    /**
     * 打开提前终止画面
     */
    var beforeEndClick = function () {
        var data = table.getAllCheckedRows();
        var para = {};
        if(data.length > 1){
            Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0046'));
            return false;
        } else if(data.length == 1){
            para = data[0];
            para.busiCode = "";
        }
        para.pageModel = PAGE_MODEL_NEW;
        Joyin.dialog(JoyinUtil.getLabel('TrdIRSDuration003'),
            basePath + "invest/period/trdirsduration/TrdIRSDuration003/init",
            "98%", "95%", para, function () {
            });
    };


    /**
     * 画面加载完成
     */
    $(function () {
        $("#TrdIRSDuration001_btn_export").click(exportExcel)
        $("#TrdIRSDuration001_btn_psIntPlan").click(psIntPlanClick)
        $("#TrdIRSDuration001_btn_beforeEnd").click(beforeEndClick)
        $("#TrdIRSDuration001_btn_search").click(function () {
            table.load();
        })
        $("#TrdIRSDuration001_btn_revoke").click(function () {
            var rows = table.getAllCheckedRows();
            if (rows.length != 1) {
                Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0046'))
                return;
            }
            var object = rows[0];
            object.operateType = "revoke";
            Joyin.ajax(basePath + 'invest/period/trdirsduration/TrdIRSDuration001/revokeCheck', object, function (result) {
                Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                    result = result.replace(/\s+/g,"");
                    if (!result) {
                        result = '无';
                    }
                    object.revokeReason = result;
                    WorkFlow.doRevoke(basePath + 'invest/period/trdirsduration/TrdIRSDuration001/revoke', object, "TrdIRSDuration002_btn_revoke", "TrdIRSDuration002");
                });
            });
        })
    });
    TrdIRSDuration001 = {reloadTable: function () {
            table.reload();
        }}
}(TrdIRSDuration001));

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
//不同资产的查看页面
var showDetailByFinprodId=function(finprodId){
    var label='';
    var pagePath='';
    //利率互换标的信息查看
    label = 'TrdIRSTrade002';
    pagePath = "/invest/trade/irs/TrdIRSTrade002/init";
    Joyin.dialog(JoyinUtil.getLabel(label), basePath + pagePath,
        "95%", "90%", {
            pageModel: PAGE_MODEL_REFERENCE,
            tradeId: finprodId
        });
}