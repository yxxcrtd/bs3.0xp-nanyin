(function(){

    repoInfoTableOption = {
        rowno: true,
        searchOnInit: true,
        checkbox: false,
        page: false,
        cols: [
            {field: 'settleStatusStr',width: "150",title: '交收状态', sortable:false},
            {field: 'settleStatus', visible: false},
            {field: 'finprodId', visible: false},
            {field: 'tradeId',width: "150", title: '原业务编号', sortable:false},
            {field: 'tradeDate',width: "150", title: '交易日期', sortable:false},
            {field: 'settleDate',width: "150", title: '交收日期', sortable:false},
            {field: 'finprodMarketId',width: "150", title: '资产代码', sortable:false},
            {field: 'finprodAbbr',width: "150", title: '资产简称', sortable:false},
            {field: 'finprodType2Str',width: "150", title: '资产类型', sortable:false},
            {field: 'finprodType2', visible: false},
            {field: 'tgBusiType', visible: false},
            {field: 'tgBusiTypeStr',width: "150", title: '业务类型', sortable:false},
            {field: 'ps', visible: false},
            {field: 'psStr',width: "150", title: '交易方向', sortable:false},
            {field: 'settleNum',width: "150", title: '交收数量(万元)', type: 'money', digit: 2, sortable:false},
            {field: 'settleAmt',width: "150", title: '交收金额', type: 'money', digit: 2,sortable:false},
            {field: 'portfolioId',width: "150", title: '组合代码', sortable:false},
            {field: 'portfolioName',width: "150", title: '组合名称', sortable:false},
            {field: 'cleanStatusStr',width: "150", title: '清算状态', sortable:false},
            {field: 'cleanStatus', visible: false},
            {field: 'tradeMarket', visible: false},
            {field: 'tradeMarketStr',width: "150", title: '交易场所', sortable:false},
            {field: 'tradeMarketMode', visible: false},
            {field: 'tradeMarketModeStr',width: "150", title: '交易市场', sortable:false},
            {field: 'secAcctId',width: "150", title: '证券账户', sortable:false},
            {field: 'counterName',width: "150", title: '交易对手', sortable:false},
            {field: 'cleanStlStatusStr',width: "150", title: '结算指令状态', sortable:false},
            {field: 'cleanArgStatusStr',width: "150", title: '结算合同状态', sortable:false},
            {field: 'remark',width: "150", title: '备注', sortable:false}
        ],
        beforeEditCell:function(){
        	return false;
        },
        data: []
    }

    if (settleinfoList && settleinfoList.length > 0) {
        repoInfoTableOption.data = repoInfoTableOption.data.concat(settleinfoList);
    }

     /**
      * 定义数据表格
      */
     var repoInfoTable = $("#settleinfoList").joyintable(repoInfoTableOption);

     function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm', true);
        Joyin.confirm("请确认是否提交？", function() {
            WorkFlow.doSubmitList(basePath + 'cfets/manualsettle/CfetsManualSettle/doManualSettle', object, "CfetsManualSettle_btn_submit");
        });
     }

     /**
      * 画面加载完成
      */
     $(function() {
        $('#CfetsManualSettle_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
     });
}());