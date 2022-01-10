$(function() {
    var entrustIdcols = [
        {field:'entrustStatusStr',  title: '指令状态'},
        {field:'transactioncom', title: '执行进度(%)',render : function(data, type, row, meta){
                return "<div class='layui-progress layui-progress-big' style='margin-top:1px;'><div class='layui-progress-bar layui-bg-green' style='width:"+row.transactioncom+"%'><span class='layui-progress-text' style='color:black;'>"+row.transactioncom+"%</span></div></div>";
            }, width:'8%',sortable:false},
        {field:'tradeFinishPercent', title: '成交进度(%)',render : function(data, type, row, meta){
                return "<div class='layui-progress layui-progress-big' style='margin-top:1px;'><div class='layui-progress-bar layui-bg-green' style='width:"+row.tradeFinishPercent+"%'><span class='layui-progress-text' style='color:black;'>"+row.tradeFinishPercent+"%</span></div></div>";
            }, width:'8%',sortable:false},
        {field:'entrustNum',  title: '指令额度(万元)',type:'number',digit:'2'},
        {field:'portfolioId',  title: '组合代码'},
        {field:'portfolioName',  title: '组合名称'},
        {field:'finprodMarketId',  title: '资产代码'},
        {field:'finprodAbbr',  title: '资产简称'},
        {field:'settleSpreedStr',  title: '清算速度'},
        {field:'psStr',  title: '交易方向'},
        {field:'secAcctName',  title: '证券账户'},
        {field:'priceModelStr',  title: '价格模式'},
        {field:'businessModelStr',  title: '业务模式'},
        {field:'price',title:'净价(元)',width:150,type:'number',digit:'2'},
        {field:'matureYield',title:'到期收益率(%)',width:150,type:'number',digit:'4'},
        {field:'exerYield',title:'行权收益率(%)',width:150,type:'number',digit:'4'},
        {field:'tradeMarketStr',  title: '交易场所'},
        {field:'invManagerName',  title: '投资经理'},
        {field:'entrustDate',title:'指令日期',width:150,type:'date'},
        {field:'transmitTime',  title: '下达时间'},
        {field:'entrustId',  title: '指令代码'},
        {field:'remark',  title: '备注'}
    ];

    var dealcols = [
        {field:'validStatusStr',  title: '有效状态'},
        {field:'quoteTypeStr',  title: '交易类型'},
        {field:'settleDate',  title: '成交日期',type:'date'},
        {field:'psStr',  title: '本方方向'},
        {field:'finprodMarketId',  title: '资产代码'},
        {field:'finprodAbbr',  title: '资产简称'},
        {field:'tradeStatusStr',  title: '报价状态'},
        {field:'sendStatusStr',  title: '发送状态'},
        {field:'shareAmt',  title: '券面总额(万元)',type:'number',digit:'2'},
        {field:'price',  title: '净价(元)',type:'number',digit:'2'},
        {field:'matureYield', title: '到期收益率(%)',type:'number',digit:'4'},
        {field:'exerYield',  title: '行权收益率(%)',type:'number',digit:'4'},
        {field:'settleSpeedStr',  title: '清算速度'},
        {field:'quoteRoleTypeStr',title:'本方角色'},
        {field:'ouPartyName',title:'本方'},
        {field:'ouTraderName',title:'本方交易员'},
        {field:'cpPartyName',  title: '对手方'},
        {field:'cpTraderName',  title: '对手方交易员'},
        {field:'reqEffTime',title:'请求有效'},
        {field:'resEffTime',  title: '回复有效'},
        {field:'shareAmtHun',  title: '回复量(万)',type:'number',digit:'2'},
        {field:'quoteId',  title: '业务编号'},
        {field:'trdId',  title: '成交编号'},
        {field:'remark',  title: '备注'}
    ];
    /**
     * 定义数据表格
     */
    var entrustIdsTable=$("#entrustIdsTable").joyintable({
        data: (entity && entity.entrustList) ? entity.entrustList : [],
        cols:entrustIdcols,
        searchOnInit: true,
        loadonce:true,
        height:200,
        page:false,
    });

    var dealTable=$("#dealTable").joyintable({
        data: (entity && entity.dealTransactionList) ? entity.dealTransactionList : [],
        cols:dealcols,
        searchOnInit: true,
        loadonce:true,
        height:400,
        page:false,
    });

    function saveOrSubmit(operateType) {
        Joyin.confirm("是否确认提交", function() {
            WorkFlow.doSubmitList(basePath + 'cfets/bankuplink/bond/IbuQuoteBond/doForcedFinish', {"entrustId":$("#entrustId").val()}, "EntrustForcedFinish_btn_submit");
        });
    };


    $(function(){
        $('#EntrustForcedFinish_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
});
