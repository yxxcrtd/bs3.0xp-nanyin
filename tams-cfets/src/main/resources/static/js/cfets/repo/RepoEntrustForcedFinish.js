$(function () {
    var entrustIdcols = [
        {field: 'entrustStatusStr', title: '指令状态', width: 150},
        {
            field: 'transactioncom', title: '执行进度(%)', width: 150, render: function (data, type, row, meta) {
                return "<div class='layui-progress layui-progress-big' style='margin-top:1px;'><div class='layui-progress-bar layui-bg-green' style='width:" + row.transactioncom + "%'><span class='layui-progress-text' style='color:black;'>" + row.transactioncom + "%</span></div></div>";
            }, width: '8%', sortable: false
        },
        {
            field: 'tradeFinishPercent', title: '成交进度(%)', width: 150, render: function (data, type, row, meta) {
                return "<div class='layui-progress layui-progress-big' style='margin-top:1px;'><div class='layui-progress-bar layui-bg-green' style='width:" + row.tradeFinishPercent + "%'><span class='layui-progress-text' style='color:black;'>" + row.tradeFinishPercent + "%</span></div></div>";
            }, width: '8%', sortable: false
        },
        {field: 'entrustNum', title: '指令额度(元)', type: 'number', digit: '2', width: 150},
        {field: 'portfolioId', title: '组合代码', width: 150},
        {field: 'portfolioName', title: '组合名称', width: 150},
        {field: 'finprodMarketNames', title: '质押券', width: 150, visible: (repo_pledge_model === '02')},
        {field: 'pledgeStockName', title: '质押券库', width: 150, visible: (repo_pledge_model === '03')},
        {field: 'settleSpreedStr', title: '清算速度', width: 150},
        {field: 'psStr', title: '交易方向', width: 150},
        {field: 'priceModelStr', title: '价格模式', width: 150},
        {field: 'repoTerm', title: '回购期限(天)', width: 150},
        {field: 'tradeYield', title: '回购利率(%)', width: 150, type: 'money', digit: '4'},
        {field: 'invManagerName', title: '投资经理', width: 150},
        {field: 'counterName', title: '交易对手', width: 150},
        {
            field: 'entrustDate', title: '指令日期', render: function (data, type, row, meta) {
                let date = new Date(row.entrustDate);
                return date.format('yyyy-MM-dd');
            }, width: 120
        },
        {field: 'transmitTime', title: '下达时间', width: 150},
        {field: 'entrustId', title: '指令代码', width: 150},
        {field: 'remark', title: '备注', width: 150}
    ];

    var dealcols = [
        {field: 'validStatusStr', title: '有效状态', width: 120},
        {field: 'quoteTypeStr', title: '交易类型', width: 120},
        {
            field: 'tradeDate', title: '成交日期', render: function (data, type, row, meta) {
                let date = new Date(row.tradeDate);
                return date.format('yyyy-MM-dd');
            }, width: 120
        },
        {field: 'psStr', title: '本方方向', width: 120},
        {field: 'tradeStatusStr', title: '报价状态', width: 120},
        {field: 'sendStatusStr', title: '发送状态', width: 120},
        {field: 'tradeAmt', title: '交易金额(元)', type: 'money', digit: '2', width: 120},
        {field: 'rate', title: '回购利率(%)', type: 'money', digit: '4', width: 120},
        {field: 'repoTerm', title: '回购期限(天)', width: 120},
        {field: 'quoteRoleTypeStr', title: '本方角色', width: 120},
        {field: 'ouPartyName', title: '本方', width: 120},
        {field: 'ouTraderName', title: '本方交易员', width: 120},
        {field: 'cpPartyName', title: '对手方', width: 120},
        {field: 'cpTraderName', title: '对手方交易员', width: 120},
        {field: 'settleSpeedStr', title: '清算速度', width: 120},
        {
            field: 'vSettleDate', title: '首次结算日', render: function (data, type, row, meta) {
                let date = new Date(row.vSettleDate);
                return date.format('yyyy-MM-dd');
            }, width: 120
        },
        {
            field: 'mSettleDate', title: '到期结算日', render: function (data, type, row, meta) {
                let date = new Date(row.mSettleDate);
                return date.format('yyyy-MM-dd');
            }, width: 120
        },
        {field: 'quoteId', title: '业务编号', width: 120},
        {field: 'trdId', title: '成交编号', width: 120},
        {field: 'remark', title: '备注', width: 120}
    ];
    /**
     * 定义数据表格
     */
    var entrustIdsTable = $("#entrustIdsTable").joyintable({
        data: (entity && entity.entrustList) ? entity.entrustList : [],
        cols: entrustIdcols,
        searchOnInit: true,
        loadonce: true,
        height: 200,
        page: false,
    });

    var dealTable = $("#dealTable").joyintable({
        data: (entity && entity.dealTransactionList) ? entity.dealTransactionList : [],
        cols: dealcols,
        searchOnInit: true,
        loadonce: true,
        height: 400,
        page: false,
    });

    function saveOrSubmit(operateType) {
        Joyin.confirm("是否确认提交", function () {
            WorkFlow.doSubmitList(basePath + 'cfets/bankuplink/repo/RepoEntrustForcedFinish/save', {"entrustId": $("#entrustId").val()}, "RepoEntrustForcedFinish_btn_submit");
        });
    };


    $(function () {
        $('#EntrustForcedFinish_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
    });
});
