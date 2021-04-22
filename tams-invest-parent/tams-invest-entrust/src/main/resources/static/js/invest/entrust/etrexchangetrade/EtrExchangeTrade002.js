/**
 * 持仓信息（右上）
 */
(function(){
	
    function getPositionsSearchParams(){
        var data = $.serializeToObject('#searchForm_cc');
        data.portfolioId = data.portfolioId ? data.portfolioId.join(','):'';
        data.tradeMarket = data.acctTradeMarket;
        data.finprodType2 = data.finprodType2;
        return data;
    }
    
	function getZqzyPositionParams() {
		var data = $.serializeToObject('#searchForm_zqzy');
		return data;
	}
    
    /**
     * 定义数据表格
     */
    var positionsTable = $("#positionsTable").joyintable({ 
        ajax: {
            url: 'findPositionsPage',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getPositionsSearchParams();//获取请求参数
            }//ajax请求数据
        },
        dblclick:function(table, data) {
            if (data.finprodType2 != FINPROD_TYPE_TG_F01){
                data.entrustPrice = data.price;
            }
            data.secId = data.finprodMarketId;
            if(data.sellNum){
                data.entrustNum = data.sellNum;
            }
            else{
                data.entrustNum = "0";
            }
            data.ps = PS_T02;
            data.targetType = TARGET_CLASS_01;
            //价格模式默认带出限价
            data.priceModel = "PRM02";
            if(parent.EtrExchangeTrade001.getCurrentTab() == 'dz'){
                data.declaType = DECLA_TYPE_PRM05;
            }
            if(parent.EtrExchangeTrade001.getCurrentTab() == 'gd'){
                data.declaType = DECLA_TYPE_PRM14;
            }
            if(window.parent.checkDbClickData(data)){
                parent.EtrExchangeTrade001.dbclickRefresh002(data);
            }
        },
        /**
         * 定义表格名称
         */
        checkbox:false,
        searchOnInit:true,
        cols:[
             {field:'portfolioId', title: 'EtrExchangeTrade002.portfolioId', sortField:'portfolioId',width : 100}//组合名称
            ,{field:'portfolioName', title: 'EtrExchangeTrade002.portfolioName', sortField:'portfolioName',width : 100}//组合名称
            //,{field:'secAcctId', title: 'EtrExchangeTrade002.secAcctId', sortField:'secAcctId',width : 130}//证券账户代码
           // ,{field:'secAcctName', title: 'EtrExchangeTrade002.secAcctName',sortField:'secAcctName', width : 130}//证券账户名称
            ,{field:'tradeMarketStr', title: 'EtrExchangeTrade002.tradeMarket',sortField:'tradeMarket', width : 100}//交易市场
            ,{field:'finprodId', title: 'EtrExchangeTrade002.finprodId', width : 180,visible:false}//资产代码
            ,{field:'finpordName', title: 'EtrExchangeTrade002.finpordName', width : 160,visible:false}//资产名称
            ,{field:'finprodMarketId', title: 'EtrExchangeTrade002.finprodId', width : 180}//资产代码
            ,{field:'secName', title: 'EtrExchangeTrade002.finpordName', width : 160}//资产名称
            ,{field:'finprodType2', title: 'EtrExchangeTrade002.finprodType', width : 160,render: function (data, type, row, meta) {
                    if (data == 'F01'){data = "债券";} if(data == 'F06'){data = "基金";}if (data=='F10'){data="股票";} return data;}}//资产类型
            // ,{field:'invAimStr', title: 'EtrExchangeTrade002.invAim',sortField:'invAim', width : 150}//投资目的
            ,{field:'positionsNum', title: 'EtrExchangeTrade002.positionsNum',type:'number', digit : 0, width : 150}//持仓数量
            ,{field:'sellNum', title: 'EtrExchangeTrade002.sellNum',type:'number', digit : 0, width : 150}//可卖数量
            ,{field:'positionsAmt', title: 'EtrExchangeTrade002.positionsAmt',type:'number', width : 150}//持仓成本
            ,{field:'price', title: 'EtrExchangeTrade002.price',type:'number', width : 100}//最新价
            ,{field:'floatingPL', title: 'EtrExchangeTrade002.floatingPL',type:'number', width : 150}//浮动盈亏（%）
        ]
    });

    /**
     * 定义数据表格
     */
    var positionsTable_zqzy = $("#positionsTable_zqzy").joyintable({ 
        ajax: {
            url: 'findZqzyPositionsPage',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getZqzyPositionParams();//获取请求参数
            }//ajax请求数据
        },
        dblclick:function(table, data) {
            data.secId = data.finprodMarketId;
            if(data.repoNum){
                data.entrustNum = data.repoNum;
            }
            else{
                data.entrustNum = "0";
            }
            // 数据字典.共通业务类型.债券质押式回购竞价指令
            data.entrustType = ENTRUST_BUSS_TYPE_15;
            data.ps = PS_T69;
            data.targetType = TARGET_CLASS_01;
            data.priceModel = PRICE_MODEL_PRM01;
            if(window.parent.checkDbClickData(data)){
                parent.EtrExchangeTrade001.dbclickRefresh002(data);
            }
        },
        /**
         * 定义表格名称
         */
        checkbox:false,
        searchOnInit:true,
        height: document.documentElement.clientHeight -110,
        cols:[
            {field:'portfolioId', title: 'EtrExchangeTrade002.portfolioId', sortField:'portfolioId',width : 100}//组合名称
            ,{field:'portfolioName', title: 'EtrExchangeTrade002.portfolioName', sortField:'portfolioName',width : 100}//组合名称
            ,{field:'secAcctId', title: 'EtrExchangeTrade002.secAcctId', sortField:'secAcctId',width : 130}//证券账户代码
            ,{field:'secAcctName', title: 'EtrExchangeTrade002.secAcctName',sortField:'secAcctName', width : 130}//证券账户名称
            ,{field:'tradeMarketStr', title: 'EtrExchangeTrade002.tradeMarket',sortField:'tradeMarket', width : 100}//交易市场
            ,{field:'finprodId', title: 'EtrExchangeTrade002.finprodId', width : 180,visible:false}//资产代码
            ,{field:'finpordName', title: 'EtrExchangeTrade002.finpordName', width : 160,visible:false}//资产名称
            ,{field:'finprodMarketId', title: 'EtrExchangeTrade002.finprodId', width : 180}//市场代码
            ,{field:'secName', title: 'EtrExchangeTrade002.finpordName', width : 160}//资产名称
            ,{field:'price', title: 'EtrExchangeTrade002.repoPrice', width : 160}//折算率（%）
            ,{field:'positionsNum', title: 'EtrExchangeTrade002.positionsNum',type:'number',digit : 0, width : 150}//持仓数量
            ,{field:'repoNum', title: 'EtrExchangeTrade002.repoNum',type:'number', digit : 0, width : 150}//质押数量
            ,{field:'secNum', title: 'EtrExchangeTrade002.secNum',type:'number', digit : 0,  width : 150}//标准券数量
            ,{field:'canRepoNum', title: 'EtrExchangeTrade002.canRepoNum',type:'number', digit : 0, width : 150}//可质押数量
            ],
		    done : function(res) {
                // 最后一行汇总标准券数量
                var totalNum = Number(0);
                var allRows = positionsTable_zqzy.getAllRows();
                if (allRows){
                    allRows.forEach(item => {
                        totalNum +=  Number(item.secNum);
                    });
                    $('#secNumTotal').text(totalNum+ '张')
                }
		    var param = getZqzyPositionParams();
		    clearlabelElement();
		    if(param.portfolioId){
		    	secNum_sh = 0;
		    	secNum_sz = 0;
			   if (res != null && res.length > 0) {
				for (var i = 0; i < res.length; i++) {
					if(res[i].tradeMarket == "03"){
						secNum_sh += Number(res[i].canUseSecNum);
					}else if(res[i].tradeMarket == "04"){
						secNum_sz += Number(res[i].canUseSecNum);
					}
				}
	            Joyin.ajax(basePath + 'invest/entrust/etrexchangetrade/EtrExchangeTrade002/loadSecNum', param, function(result) {
	                if(result) {
	                    $("#usableNum_sh").text(result.secNum_sh + '张');
	                    if(secNum_sh != 0){
	                    	var usableNum_sh = result.secNum_sh.replace(/,/g,'')
	                    	var res_sh = parseFloat(usableNum_sh)/parseFloat(secNum_sh);
	                    	$("#usingRate_sh").text(Math.round(res_sh*10000)/100 + '%');
	                    	$("#usingRate_sh").css('color', res_sh < 1 ? "green":"red");
	                    	$("#usableNum_sh").css('color', res_sh < 1 ? "green":"red");
	                    	
	                    	$("#secNum_sh").text(toThousands(secNum_sh - parseFloat(usableNum_sh)) + '张');
		    	            $("#secNum_sh").css('color', secNum_sh - parseFloat(usableNum_sh) > 0 ? "green":"red");
	                    }
	                    $("#usableNum_sz").text(result.secNum_sz + '张');
	                    if(secNum_sz != 0){
	                    	var usableNum_sz = result.secNum_sz.replace(/,/g,'');
	                    	var res_sz = parseFloat(usableNum_sz)/parseFloat(secNum_sz);
	                    	$("#usingRate_sz").text(Math.round(res_sz*10000)/100 + '%');
	                    	$("#usingRate_sz").css('color', res_sz < 1 ? "green":"red");
	                    	$("#usableNum_sz").css('color', res_sz < 1 ? "green":"red");
	                    	
	                    	$("#secNum_sz").text(toThousands(secNum_sz - parseFloat(usableNum_sz)) + '张');
		    	            $("#secNum_sz").css('color', secNum_sz - parseFloat(usableNum_sz) > 0 ? "green":"red");
	                    }
	                }
	            });
	          }
		   }
		}
    });
    
    function toThousands(num) {
        return (num || 0).toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,');
    }
    
	function clearlabelElement() {
		$("#secNum_sh").text('--');
		$("#secNum_sh").css('color', 'black');
		$("#secNum_sz").text('--');
		$("#secNum_sz").css('color', 'black');
		$("#usableNum_sh").text('--');
		$("#usableNum_sh").css('color', 'black');
		$("#usableNum_sz").text('--');
		$("#usableNum_sz").css('color', 'black');
		$("#usingRate_sh").text('--');
		$("#usingRate_sh").css('color', 'black');
		$("#usingRate_sz").text('--');
		$("#usingRate_sz").css('color', 'black');
	}

    /**
	 * 刷新列表
	 */
    var refreshPositionsTable = function(){
        positionsTable.table ? positionsTable.reload() : positionsTable.load();
    }
    /**
     * 刷新债券质押列表
     */
    var refreshZqzyPositionsTable = function(){
        positionsTable_zqzy.table ? positionsTable_zqzy.reload() : positionsTable_zqzy.load();
        //loadSecNum();
    }
    /**
     * 更新上交所和深交所汇总标准券数量
     */
    var loadSecNum = function() {
        
    }
    var init = function() {
        //loadSecNum();
    }
    

	var zqzyPositionRefresh = function(portfolioId) {
		$("#portfolioId_zqzy").textSelect('setValue', portfolioId);
		if (!portfolioId) {
			$("#portfolioId_zqzy").val("").trigger('change');
		}
		refreshZqzyPositionsTable();// 加载表格
		
	}
    /**
	 * 画面加载完成
	 */
    $(function() {
    	// 切换到质押持仓，查询页面
        layui.use(['element', 'form'], function() {
            // 绑定交易平台TAB切换事件
            var element = layui.element;
            element.on('tab(tradePlatform)', function(data) {
                if(data.index == 0) {
                	refreshPositionsTable();
                }else if(data.index == 1) {
                	var width = $("#date_zqzy").parent().width();
                	$("#select2-portfolioId_zqzy-container").parent().css("max-width", width);
                	refreshZqzyPositionsTable();
                }
            });
        });
        
        // 绑定查询按钮
        $('#EtrExchangeTrade002_search_btn').click(function() {
        	refreshPositionsTable();//加载表格
        });
        // 绑定债券质押查询按钮
        $('#EtrExchangeTrade002_zqzy_search_btn').click(function() {
        	refreshZqzyPositionsTable();// 加载表格
        });
        init();
        /**
         * 定时刷新
         */
        setInterval(function(){
            refreshPositionsTable();
            refreshZqzyPositionsTable();
            //loadSecNum();
        },etrExchange_posTable_refresh_period);
    });
    
    /**
     * 对外公开的接口
     */
    parent.EtrExchangeTrade002 = {
    	zqzyPositionRefresh:zqzyPositionRefresh,
    }

}());