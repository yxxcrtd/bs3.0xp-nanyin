//@ sourceURL=EtrExchangeTrade003.js
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
            url: 'selectEtrExchange',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getSearchParams();//获取请求参数
            }//ajax请求数据
        },
        dblclick:function(table, data) {
            if(window.parent.dbclickCheck(data)){
            	if (data.priceModel == PRICE_MODEL_PRM03) {
            	    data.entrustPrice = "0.00";//市价指令，价格默认显示0
            	} else {
            	    data.entrustPrice = data.price;
            	}
                data.secId = data.finprodMarketId;
                data.seatId = data.counterSeat;
                data.agreeOnId = data.counterConNo;
                data.acctNo= data.dealCurAcct;
                //大宗交易、竞价交易  正回购、逆回购  [大宗交易&&深交所 ->交易平台_综合协议交易平台,大宗交易&&上交所 ->交易平台_大宗交易平台] 
                if((data.tradePlat == '02' || data.tradePlat == '04' || data.tradePlat == '05') && (data.ps == '03' || data.ps == '04')){
                	data.finprodType2 ='ZYSHG';
                }
                parent.EtrExchangeTrade001.dbclickRefresh(data);
            }
        },
        /**
         * 定义表格名称
         */
        searchOnInit:true,
        /*loadonce:true,*/
        cols:[
             {field:'entrustId', title: 'EtrExchangeTrade003.entrustId', sortField:'ENTRUST_ID',width : 200}//
            ,{field:'entrustStatus', title: 'EtrExchangeTrade003.entrustStatus',visible:false, sortField:'ENTRUST_STATUS',width : 150}//组合名称
            ,{field:'entrustStatusString', title: 'EtrExchangeTrade003.entrustStatus', sortField:'ENTRUST_STATUS',width : 150}//组合名称
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

            ,{field:'portfolioName', title: 'EtrExchangeTrade003.portfolioName', sortField:'PORTFOLIO_NAME',width : 150}//资产代码
            ,{field:'finprodAbbr', title: 'EtrExchangeTrade003.finpordName',sortField:'FINPROD_ABBR', width : 150}//资产名称
            ,{field:'assetTypeString', title: 'EtrExchangeTrade003.finprodType2',sortField:'ASSET_TYPE', width : 100}//投资目的
            ,{field:'tradeMarketString', title: 'EtrExchangeTrade003.tradeMarket',sortField:'TRADE_MARKET', width : 100}//资产名称
            ,{field:'tradePlat', title: 'EtrExchangeTrade003.tradePlat',sortField:'TRADE_PLAT', width : 100,visible:false}//投资目的
            ,{field:'tradePlatString', title: 'EtrExchangeTrade003.tradePlat',sortField:'TRADE_PLAT', width : 100}//投资目的
            ,{field:'ps', title: 'EtrExchangeTrade003.ps',sortField:'PS',visible:false, width : 100}//投资目的
            ,{field:'psStr', title: 'EtrExchangeTrade003.ps',sortField:'PS', width : 100}//投资目的
            ,{field:'priceModel', title: 'EtrExchangeTrade003.priceModel',visible:false,sortField:'PRICE_MODEL', width : 100}//投资目的
            ,{field:'priceModelString', title: 'EtrExchangeTrade003.priceModel',sortField:'PRICE_MODEL', width : 100}//投资目的
            ,{field:'price', title: 'EtrExchangeTrade003.price',type:'number',digit:4, width : 120,sortField:'PRICE',
            	render: function(data, type, row, meta) {
            	    if (row.priceModel == PRICE_MODEL_PRM03) {
            	        return "0.00";//市价指令，价格默认显示0
            	    } else {
            	        return row.price;
            	    }
            	}
            }//指令价格
            ,{field:'entrustAmt', title: 'EtrExchangeTrade003.entrustAmt',type:'number',digit:4, width : 120,sortField:'ENTRUST_AMT'}//可卖数量
            ,{field:'vdate', title: 'EtrExchangeTrade003.vdate',type:'date', width : 120,sortField:'VDATE'}//持仓成本
            ,{field:'mdate', title: 'EtrExchangeTrade003.mdate',type:'date', width : 120,sortField:'MDATE'}//最新价
            ,{field:'finprodId',visible: false}//资产ID
            ,{field:'ps', visible: false}//交易方向
            ,{field:'portfolioId', visible: false}//组合代码
            ,{field:'paAssetType', visible:false}//平安资产类型
            ,{field:'entrustType', visible:false}//平安资产类型
            ,{field:'finprodMarketId', visible:false}//平安资产类型
            ,{field:'counterSeat', visible:false}//平安资产类型
            ,{field:'counterConNo', visible:false}//平安资产类型
            ,{field:'counterId', visible:false}//平安资产类型
            ,{field:'finprodType2', visible:false}//平安资产类型
            ,{field:'entrustNum', visible:false}//平安资产类型
            ,{field:'dealCurAcct', visible:false}//平安资产类型
            ,{field:'targetType', visible:false}//平安资产类型
            ,{field:'counterOrg', visible:false}//对手方交易商
            ,{field:'counterTrader', visible:false}//对手方交易员
            ,{field:'trader', visible:false}//本方交易员
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
        setInterval(function(){myrefresh()}, etrExchange_entrustTable_refresh_period);
    });
}());