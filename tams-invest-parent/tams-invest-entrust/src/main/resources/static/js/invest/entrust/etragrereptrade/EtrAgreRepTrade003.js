//@ sourceURL=EtrAgreRepTrade003.js
/**
 * 指令列表信息(右下)
 */
(function(){

    
    function getSearchParams(){
        var data ={};
        data.entrustStatus = $('#searchForm').find('input:radio:checked').val();
        return data;
    }
    
    /**
     * 定义数据表格
     */
    var dateTable = $("#dateTable").joyintable({ 
        ajax: {
            url: 'selectEtrEntrust',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getSearchParams();//获取请求参数
            }//ajax请求数据
        },
        dblclick:function(table, data) {
        	if(data.entrustStatus != ENTRUST_STATUS_05&&data.entrustStatus != ENTRUST_STATUS_07){
                //Joyin.error(data.entrustStatusString+"的指令不允许修改!");
    	        Joyin.error("委托状态不是已撤回、已退回，无法修改!");
    	        return false;
        	}
            window.parent.doResetRiskMathFlag();
        	parent.EtrAgreRepTrade001.setEtrExchangeItems(data);
        },
        /**
         * 定义表格名称
         */
        searchOnInit:true,
        /*loadonce:true,*/
        cols:[
             {field:'entrustId', title: 'EtrExchangeTrade003.entrustId', sortField:'entrustId',width : 200}//
            ,{field:'entrustStatus', title: 'EtrExchangeTrade003.entrustStatus',visible:false, sortField:'entrustStatus',width : 150}//组合名称
            ,{field:'entrustStatusString', title: 'EtrExchangeTrade003.entrustStatus', sortField:'entrustStatus',width : 150}//组合名称
            ,{field:'implementPercentAge', title: 'EtrExchangeTrade003.implementPercentAge',render: function (data, type, row, meta) {
                    return "<div class='layui-progress layui-progress-big' style='margin-top:5px;'><div class='layui-progress-bar layui-bg-green' style='width:"
                        + (row.implementPercentAge)
                        + "%'><span class='layui-progress-text' style='color:black;'>"
                        + (row.implementPercentAge)
                        + "%</span></div></div>";
                }, sortField:'implementPercent',width : 150}//证券账户代码
            ,{field:'dealPercentAge', title: 'EtrExchangeTrade003.dealPercentAge',render: function (data, type, row, meta) {
            	// 申报类指令成交进度显示为N/A 涉及指令业务类型：1.配股 2.配债 3.债转股 4.行权回售 5.新股申购 6.可转债申购
            	// 数据字典:上农商_指令业务类型("ENTRUST_BUSS_TYPE")
            	if(row.entrustType == "09" || row.entrustType == "10" || row.entrustType == "11" || row.entrustType == "12" || row.entrustType == "13" || row.entrustType == "16"){
            		return '<span class="s-color-gray">N/A</span>';
            	}
                return "<div class='layui-progress layui-progress-big' style='margin-top:5px;'><div class='layui-progress-bar layui-bg-green' style='width:"
                + (row.dealPercentAge)
                + "%'><span class='layui-progress-text' style='color:black;'>"
                + (row.dealPercentAge)
                + "%</span></div></div>";
                },sortField:'dealPercent', width : 150}//证券账户名称

            ,{field:'portfolioName', title: 'EtrExchangeTrade003.portfolioName', sortField:'portfolioName',width : 150}//资产代码
            ,{field:'finprodAbbr', title: 'EtrExchangeTrade003.finpordName',sortField:'FINPROD_ABBR', width : 150,visible:false}//资产名称
            ,{field:'dealCurAcct', title: '资金账号名称',sortField:'dealCurAcct', width : 200}
            ,{field:'tradeMarketString', title: '交易市场',sortField:'tradeMarket', width : 150}
            ,{field:'tradePlat', title: '交易平台',sortField:'tradePlat', width : 100,visible:false}
            ,{field:'tradePlatString', title: '交易平台',sortField:'tradePlat', width : 150}
            ,{field:'ps', title: '委托方向',sortField:'ps',visible:false, width : 100}
            ,{field:'psStr', title: '委托方向',sortField:'ps', width : 120}
            ,{field:'term', title: '回购期限',sortField:'TERM', width : 120}
            ,{field:'entrustAmt', title: '回购金额',type:'number',digit:2, width : 150,sortField:'entrustAmt'}
            ,{field:'price', title: '回购利率（%）',type:'number',digit:4, width : 150,sortField:'PRICE',render:function(data, type, row, meta){
                    if(data){
                        return JoyinMath.doMul(data, 100, 4, JoyinMath.ROUND_HALF_UP);
                    }
                    else {
                        return '';
                    }
                }}
            ,{field:'fristSettleDate', title: '首期结算日',type:'date', width : 120,sortField:'fristSettleDate'}
            ,{field:'expireSettleDate', title: '到期结算日',type:'date', width : 120,sortField:'expireSettleDate'}
            ,{field:'occupyDays', title: '占款天数',sortField:'occupyDays', width : 100}
            ,{field:'intAmt', title: '回购利息',type:'number',digit:2, width : 150,sortField:'intAmt'}
            ,{field:'expireSettleAmt', title: '到期结算金额',type:'number',digit:2, width : 150,sortField:'expireSettleAmt'}
            ,{field:'finprodId', title: '金融产品代码',sortField:'finprodId', width : 100,visible:false}
            ,{field:'finprodMarketId', title: '质押券代码',sortField:'finprodMarketId', width : 150}
            ,{field:'finprodAbbr', title: '质押券名称',sortField:'finprodAbbr', width : 150}
            ,{field:'conRatio', title: '折算率（%）',type:'number',digit:2,sortField:'conRatio', width : 120}
            ,{field:'repSecNum', title: '质押数量',type:'number',digit:0,sortField:'repSecNum', width : 120}
            ,{field:'printAmt', title: '质押面额',type:'number',digit:4,sortField:'printAmt', width : 120}
            ,{field:'secAcctId', title: '证券账户',sortField:'secAcctId', visible: false}
            ,{field:'counterId', title: '交易对手',sortField:'counterId', visible:false}
            ,{field:'counterName', title: '交易对手',sortField:'counterName', width : 150}
            ,{field:'vdate', title: 'EtrExchangeTrade003.vdate',type:'date', width : 120,sortField:'VDATE'}//持仓成本
            ,{field:'mdate', title: 'EtrExchangeTrade003.mdate',type:'date',sortField:'MDATE'}//最新价
        ]
    });

    /**
     * 刷新列表
     */
    var myrefresh = function(){
        dateTable.table ? dateTable.reload() : dateTable.load();
    }

    var getAllCheckedRows = function(){
        if(dateTable){
            return dateTable.getAllCheckedRows();
        }
    }
    //公开接口
    parent.EtrExchangeTrade003 = {
        getAllCheckedRows:getAllCheckedRows,
        myrefresh:myrefresh
    }
    /**
     * 画面加载完成
     */
    $(function() {
    	
    	/**
         * 关闭委托
         */
        $('#EtrExchangeTrade003_btn_import').click(function() {
            window.parent.importEtr();
        });
        /**
         * 关闭委托
         */
        $('#EtrExchangeTrade003_btn_close').click(function() {
            window.parent.closeEtr();
        });
        /**
         * 撤销
         */
        $('#EtrExchangeTrade003_btn_revoke').click(function() {
            window.parent.revokeEtr();
        });
        /**
         * radio事件
         */
        layui.form.on('radio(entrustStatus)',function(data){
            myrefresh();
        })
        /**
         * 定时刷新
         */
        //setInterval(function(){myrefresh()},300000);
        setTimeout(function(){
            myrefresh();
            setTimeout(arguments.callee,30000)
        },30000);
    });
}());