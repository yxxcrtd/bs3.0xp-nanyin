$(function () {
    var sysFlowData = JSON.parse(sysFlow);
    var enFlowData = JSON.parse(enFlow);
    var sysFlowTable = $("#sysFlowTable").joyintable({
        searchOnInit: true,
        height: 400,
        checkbox: false,
        cols: [
            {field: 'settleDate', title: '现金流日期', width: 120, sortable: false}//现金流日期
            , {field: 'paBizTypeString', title: '业务类型', width: 200, sortable: false}//业务类型
            , {field: 'finprodId', title: '产品/资产代码', width: 150, sortable: false}//产品/资产代码
            , {field: 'finprodName', title: '产品/资产名称', width: 200, sortable: false}//产品/资产名称
            , {field: 'settleAmt', title: '金额', width: 200, sortable: false, type: 'money'}//实际本息总额
        ],
        data: sysFlowData
    });
    var enFlowTable = $("#enFlowTable").joyintable({
        searchOnInit: true,
        height: 400,
        checkbox: false,
        cols: [{field: 'settleDate', title: '到账日', width: 120, sortable: false}//到账日
            , {field: 'settleAmt', title: '金额', width: 200, sortable: false, type:'number'}//金额
            , {field: 'counterAcctNo', title: '对手方账户', width: 150, sortable: false}//对手方账户
            , {field: 'counterAcctName', title: '对手方账户名称', width: 200, sortable: false}//对手账户名称
            , {field: 'usage', title: '用途', width: 200, sortable: false}//用途
        ],
        data: enFlowData
    });

    /**
     * 匹配
     */
    var manualMatch = function () {
        Joyin.ajaxJson('manualMatch', {sysFlow: sysFlowData, enFlow: enFlowData, remark: $("#remark").val()}, function (data) {
            parent.parenPage.refreshTable();
            Joyin.closeTopWindow();
        })
    }

    /**
     * 取消匹配
     */
    var manualMatchCancel = function () {
        Joyin.confirm("确认操作", function() {
            Joyin.ajaxJson('cancelMatch', {sysFlow: sysFlowData, enFlow: enFlowData, groupId: groupId, isCancel: true}, function (data) {
                parent.parenPage.refreshTable();
                Joyin.closeTopWindow();
            })
        })
    }


    $(function () {
        $("#OutflowFundsCheckManagement002_btn_match").click(manualMatch)
        $("#OutflowFundsCheckManagement002_btn_cancelMatch").click(manualMatchCancel)
    })
})
;
