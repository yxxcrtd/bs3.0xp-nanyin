$(function () {
    //隐藏导出按钮
//    $('#InvestTradeQuery001_btn_export').hide();
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	paTradeId: $.trim($('#paTradeId').val()), //交易编号
        	paAsset: $.trim($('#paAsset').val()), //资产
        	selectBusiType: $.trim($('#paBusiType').val()), //业务类型
        	bookSetId: $.trim($('#bookSetId').val()), //投资账户
        	paPs: $.trim($('#paPs').val()), //交易方向
        	tradeDateBegin: $('#tradeDate').startDate(),
        	tradeDateEnd: $('#tradeDate').endDate()//发生日期
        }
    };
    var index = 0;
    var tableInit = function(titleId, show) {
        var title = $('#' + titleId);
        $('.layui-tab-title > li').removeClass('layui-this');
        $('.layui-tab-content').find('.layui-tab-item').removeClass('layui-show');
        title.toggle(show);
        title.toggleClass('layui-this', show);
        $('.layui-tab-content').find('.layui-tab-item:eq(' + title.index() + ')').toggleClass('layui-show', show);
        index ++;
//        $('.layui-tab-title > li:visible:first').trigger('click');
    };
   /**
    * 金额格式化: 框架数据表格 render 后 
    */
   var formatMoney = function (s, n)
   {
	     n = n > 0 && n <= 20 ? n : 2;
	     s = parseFloat((s + '').replace(/[^\d\.-]/g, '')).toFixed(n) + '';
	     var l = s.split('.') [0].split('').reverse(),
	       r = s.split('.') [1];
	     var t = '';
	     for (var i = 0; i < l.length; i++)
	     {
	       t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? ',' : '');
	     }
	     return t.split('').reverse().join('') + '.' + r;

    }
    /**
     * 定义数据表格(债券交易)
     */
	var bondTable=$("#bond-table").joyintable({
	    ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
            	//过滤业务类型 - 债券交易,交易所债券交易 -债券交易预处理
            	var nowBusitypeArr =  ['02','20','24'];
            	
            	//获取查询参数
            	var paMap = getSearchParams();
            	//数组转字符串，塞入查询参数中
            	paMap.filterBusiType = nowBusitypeArr.join(',');
            	
                return paMap;
            }
        },
		done:function(data){
		     //业务类型
		    var busiType= $.trim($('#paBusiType').val());
		    if(busiType.indexOf('02')>=0||busiType.indexOf('20')>=0){
		        tableInit('bond-title', true);
		    }else{
		        //若查询到数据，显示页面
		        tableInit('bond-title', data!=null && data.length > 0);
		    }
            
            showTotal('bond-title', 'bond-table');// 为 tab 显示 数据总数   
		},
		dblclick:function(table, data) {// 绑定双击事件，
			showbondTrade(table,data);   
        },
        checkbox: true,
		cols:[
			   {field:'tradeId', title: '交易编号',width:180, sort: true},
			   {field:'busiTypeCode', title: '业务类型',width:100, sort: true, visible:false},
			   {field:'prodId', title: '账套代码',width:150, sort: true},
			   {field:'prodAbbr', title: '账套名称',width:320, sort: true},
			   {field:'assetId', title: '债券代码',width:150, sort: true},
			   {field:'assetAbbr', title: '债券简称',width:160, sort: true},
			   {field:'psCode', title: '交易方向Code',width:100, sort: true, visible:false},
			   {field:'ps', title: '交易方向',width:100, sort: true,sortField:'psCode'},
			   {field:'tradeMarketCode', title: '交易场所code',width:100, sort: true, visible:false},
			   {field:'tradeMarket', title: '交易场所',width:160, sort: true,sortField:'tradeMarketCode'},
			   {field:'invAimCode', title: '投资目的Code',width:100, sort: true, visible:false},
			   {field:'invAim', title: '投资目的',width:380, sort: true,sortField:'invAimCode'},
			   {field:'tradeDate', title: '发生日',width:120, sort: true},
			   {field:'ccyCode', title: '交易币种code',width:100, sort: true, visible:false},
			   {field:'ccy', title: '币种',width:150, sort: true,sortField:'ccyCode'},
			   {field:'shareAmt', type:"money", title: '券面总额/交易本金',width:200, sort: true},
			   {field:'unitCprice', type:"money", digit: 6, title: '单位净价',width:100, sort: true},
			   {field:'unitFprice', type:"money", digit: 6, title: '单位全价',width:100, sort: true},
			   {field:'cpriceAmt', type:"money", title: '净价总额',width:150, sort: true},
			   {field:'intAmt', type:"money", title: '应收利息总额',width:150, sort: true},
			   {field:'fpriceAmt', type:"money", title: '全价总额',width:150, sort: true},
			   {field:'feeAmt', type:"money", title: '交易总费用',width:100, sort: true},
			   {field:'tdFeeAmt', type:"money", title: '通道费',width:100, sort: true},
			   {field:'tradeAmt', type:"money", title: '交易金额',width:150, sort: true},
			   {field:'yield', align:"right", unit: 10000, title: '到期收益率(%)',width:120, sort: true},
			   {field:'exerYield', align:"right", unit: 10000, title: '行权收益率(%)',width:120, sort: true},
			   {field:'tradeStatusCode', title: '交易状态code',width:100, sort: true, visible:false},
			   {field:'tradeStatus', title: '交易状态',width:100, sort: true,sortField:'tradeStatusCode'},
			   {field:'operation', title: 'SysCommon.operation', type:'action',
				   buttons:[{text:'&#xe65e;', clas: 'orders', title:'查看结算指令'}]}
			]
	});
	
//	债券交易双击详情
	function showbondTrade(table,data){
	    /** modify by yanya on 20210310 增加行权的交易 start**/
		//var ps1 = ['T01', 'T02', 'T03', 'T20', 'T21', 'T04','T05','T49'];//债券交易(交易方向为买入、卖出、行权、转入、转出、申购、申购确认、债转股);
        //债券交易(交易方向为买入、卖出、行权、转入、转出、申购、申购确认、债转股);
            //modify by yanya on 20210320 去除赎回结转
        //var ps1 = ['T01', 'T02', 'T03', 'T20', 'T21', 'T04','T05','T49', 'T39', 'T67'];
        var ps1 = ['T01', 'T02', 'T03', 'T20', 'T21', 'T04','T05','T49', 'T39', 'T67', 'T68'];
        /** modify by yanya on 20210310 增加行权的交易 end**/
		var ps2 = ['T08', 'T09', 'T22', 'T23', 'T25', 'T26'];//债券存续期(交易方向为收息、还本;收息到账、还本到账); 应收款项转入、转出 add by tanxuan 20191223
        var wide="98%";
        var high="85%";
        var title="查看详情";
	    if(ps1.indexOf(data.psCode)>=0){
	    	 title="查看债券交易"
        }
	    if(ps2.indexOf(data.psCode)>=0){
	    	 title="查看债券存续期"
	    }
	    Joyin.dialog(JoyinUtil.getLabel(title),
               JoyinUtil.getRealPath("/valuation/trademanage/investtradequery/bandtrade/BondTradeQuery001/init"),
               wide, high, {pageModel:PAGE_MODEL_REFERENCE,tradeId:data.tradeId,prodId:data.prodId,ps:data.psCode},
               function(){
                   //bondTable.reload();
               });
	}
	
	
	/**
     *  债券交易,绑定查看结算指令列表
     */
    bondTable.bind('click', '.orders', function(data, index) {
    	showOrders(data, index, this);
    });
    
    
    /**
     * 查看结算指令列表动作（模仿债券交易绑定结算指令，绑定时调用此方法即可，不要复制）
     * 注意：data 必须含有 tradeId 和 busiTypeCode
     */
    function showOrders(data, index, table) {
    	var feeTypeCode = "";
    	if(data.feeTypeCode){
    		feeTypeCode = data.feeTypeCode;
    	}
    	Joyin.ajax(
  		  basePath + "valuation/trademanage/investtradequery/ValOrdersQuery002/countOrders",
  		  {tradeId:data.tradeId, busiType: data.busiTypeCode},
  		  function(count) {
				if(count == 0){
					layer.open({title : '消息',content : '该笔交易尚未生成结算指令'});
				}else{
			        data.pageModel = PAGE_MODEL_EDIT;
			        Joyin.dialog(JoyinUtil.getLabel("查看结算指令列表"),
			                basePath + "valuation/trademanage/investtradequery/ValOrdersQuery002/init",
			                "95%", "80%", {pageModel:PAGE_MODEL_EDIT,
			        	tradeId:data.tradeId, busiType: data.busiTypeCode,feeType: feeTypeCode},function(){
			        		// table.reload();
			        	 });
				}
	    });
	}
    
    
    
	 /**
     * 定义数据表格(计划类资产查询—收益型)
     */
	var planProfitTable=$("#plan-profit-table").joyintable({
	    ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
            	//过滤业务类型 - 计划类资产(收益型)查询
            	var nowBusitypeArr=['09'];
            	//获取查询参数
            	var paMap = getSearchParams();
        	    //数组转字符串，塞入查询参数中
        	    paMap.filterBusiType = nowBusitypeArr.join(',');
        	    paMap.finprodType='F17';
        	    paMap.profitType='02';
                return paMap;
            }
        },
		done:function(data){
            var busiType= $.trim($('#paBusiType').val());
            if(busiType.indexOf('09')>=0){
                tableInit('plan-profit-title', true);
            }else{
                //若查询到数据，显示页面
                tableInit('plan-profit-title', data!=null && data.length > 0);
            }
            
            showTotal('plan-profit-title', 'plan-profit-table');// 为 tab 显示 数据总数   
		},
		//交易方向为应收款项转入转出禁用双击事件
		
		dblclick:function(table, data) {
//			var zyps=['T25','T26'];
//			if(zyps.indexOf(data.psCode)<0){
//				showPlanProduct(table,data);
//			}
			showPlanProduct(table,data);
        },
		checkbox: true,
		cols:[
              {field:'tradeId', title: '交易编号',width:180, sort: true},
              {field:'busiTypeCode', title: '业务类型',width:100, sort: true, visible:false},
              {field:'prodId', title: '账套代码',width:150, sort: true},
              {field:'prodAbbr', title: '账套名称',width:150, sort: true},
              {field:'assetId', title: '资产代码',width:150, sort: true},
              {field:'assetAbbr', title: '资产名称',width:150, sort: true},
              {field:'psCode', title: '交易方向Code',width:100, sort: true, visible:false},
              {field:'ps', title: '交易方向',width:100, sort: true,sortField:'psCode'},
              {field:'tradeDate', title: '发生日',width:120, sort: true},
              {field:'ccyCode', title: '交易币种code',width:100, sort: true, visible:false},
              {field:'ccy', title: '币种',width:150, sort: true,sortField:'ccyCode'},
              {field:'shareAmt', title: '交易份额',width:100, sort: true},
              {field:'unitCprice', title: '单位净价', digit: 6, width:100, sort: true},
              {field:'unitFprice', title: '单位全价', digit: 6, width:100, sort: true},
              {field:'cpriceAmt', title: '净价总额',width:100, sort: true},
              {field:'intAmt', title: '应收利息总额',width:150, sort: true},
              {field:'fpriceAmt', title: '全价总额',width:100, sort: true},
//              {field:'feeAmt', title: '总交易费用',width:150, sort: true},
              {field:'tdFeeAmt', title: '通道费',width:100, sort: true},
              {field:'tradeAmt', title: '交易金额',width:100, sort: true},
              {field:'yield', title: '到期收益率(%)',width:150, sort: true},
              {field:'tradeStatusCode', title: '交易状态code',width:100, sort: true, visible:false},
              {field:'tradeStatus', title: '交易状态',width:100, sort: true,sortField:'tradeStatusCode'},
              {field:'operation', title: 'SysCommon.operation', type:'action', fixed: 'right' ,
                  buttons:[{text:'&#xe65e;', clas: 'orders', title:'查看结算指令'}]}
           ]
	});

	
    /**
     * 定义数据表格(存款类资产交易查询)
     */
    var depositloan=$("#deposit-loan-table").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                //过滤业务类型 - 存借款交易
                var nowBusitypeArr =  ['03'];
                //获取查询参数
                var paMap = getSearchParams();
                //数组转字符串，塞入查询参数中
                paMap.filterBusiType = nowBusitypeArr.join(',');
                return paMap;
            }
        },
        done:function(data){
            var busiType= $.trim($('#paBusiType').val());
            if(busiType.indexOf('03')>=0){
                tableInit('deposit-loan-title', true);
            }else{
                //若查询到数据，显示页面
                tableInit('deposit-loan-title', data!=null && data.length > 0);
            }

           
           showTotal('deposit-loan-title', 'deposit-loan-table');// 为 tab 显示 数据总数   
        },
        dblclick:function(table, data) {// 绑定双击事件，
                Joyin.dialog(JoyinUtil.getLabel("查看存款类资产交易"),
                        JoyinUtil.getRealPath("/valuation/trademanage/investtradequery/posittrade/DepositLoanTrade001/init"),
                        "90%", "90%", {pageModel:PAGE_MODEL_REFERENCE, tradeId:data.tradeId,prodId:data.prodId},
                        function(){
                            //depositloan.reload();
                        });
        },
        checkbox: true,
        cols:[
              {field:'tradeId', title: '交易编号',width:150, sort: true},
              {field:'busiTypeCode', title: '业务类型',width:150, sort: true, visible:false},
              {field:'prodId', title: '账套代码',width:150, sort: true},
              {field:'prodAbbr', title: '账套名称',width:150, sort: true},
              {field:'assetId', title: '资产代码',width:150, sort: true,visible:false},
              {field:'assetAbbr', title: '资产简称',width:180, sort: true},                        
              {field:'psCode', title: '交易方向',width:100, sort: true,visible:false},
              {field:'ps', title: '交易方向',width:100, sort: true ,sortField:'psCode'},
              {field:'counterId', title: '交易对手',width:150, sort: true},
              {field:'intAmt',type:"money", title: '应计利息',width:100, sort: true},
//              {field:'',type:"money", title: '应计利息', digit: 6, width:100, sort: true},
              {field:'tradeDate', title: '交易日',width:110, sort: true},
              {field:'settleDate', title: '交割日',width:110, sort: true},
              {field:'ccyCode', title: '币种',width:150, sort: true, visible:false},
              {field:'ccy', title: '币种',width:150, sort: true ,sortField:'ccyCode'},
              {field:'prinAmt',type:"money", title: '交易本金',width:150, sort: true},
              {field:'tradeAmt',type:"money", title: '交易金额',width:150, sort: true},
              {field:'pass', title: '通道',width:150, sort: true},
              {field:'tradeStatusCode', title: '交易状态code',width:100, sort: true,visible:false},
              {field:'tradeStatus', title: '交易状态',width:100, sort: true,sortField:'tradeStatusCode'},
              {field:'operation', title: 'SysCommon.operation', type:'action', fixed: 'right',
                  buttons:[{text:'&#xe65e;', clas: 'orders', title:'查看结算指令'}]}
           ]
    });
    /**
     *  存款类资产交易查询,绑定查看结算指令列表
     */
    depositloan.bind('click', '.orders', function(data, index) {
        showOrders(data, index,this);
    });
	/**
     * 定义数据表格(计划类资产查询—净值型)
     */
    var planNetvalueTable=$("#plan-netvalue-table").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                //过滤业务类型 - 计划类资产(收益型)查询
                var nowBusitypeArr=['09'];
                //获取查询参数
                var paMap = getSearchParams();
                //数组转字符串，塞入查询参数中
                paMap.filterBusiType = nowBusitypeArr.join(',');
        	    paMap.finprodType='F17';
                paMap.profitType='01';
                return paMap;
            }
        },
        done:function(data){
            var busiType= $.trim($('#paBusiType').val());
            if(busiType.indexOf('09')>=0){
                tableInit('plan-netvalue-title', true);
            }else{
                //若查询到数据，显示页面
                tableInit('plan-netvalue-title', data!=null && data.length > 0);
            }
            
            showTotal('plan-netvalue-title', 'plan-netvalue-table');// 为 tab 显示 数据总数   
        },
        dblclick:function(table, data) {
        	var zyps=['T25','T26'];
			if(zyps.indexOf(data.psCode)<0){
				showPlanProduct(table,data);
			}
        },
        checkbox: true,
        cols:[
              {field:'tradeId', title: '交易编号',width:180, sort: true},
              {field:'busiTypeCode', title: '业务类型',width:100, sort: true, visible:false},
              {field:'prodId', title: '账套代码',width:150, sort: true},
              {field:'prodAbbr', title: '账套名称',width:150, sort: true},
              {field:'assetId', title: '资产代码',width:150, sort: true},
              {field:'assetAbbr', title: '资产名称',width:150, sort: true},
              {field:'psCode', title: '交易方向Code',width:100, sort: true, visible:false},
              {field:'ps', title: '交易方向',width:100, sort: true,sortField:'psCode'},
              {field:'purchaseDate', title: '申购日',width:120, sort: true},
              {field:'tradeDate', title: '申购/赎回确认日',width:180, sort: true},
              {field:'payDate', title: '到账日',width:120, sort: true},
              {field:'cpriceDate', title: '净值日',width:120, sort: true},
              {field:'unitCprice', type:"money",title: '单位净值',width:120, sort: true},
              {field:'shareAmt', type:"money",title: '交易份额',width:120, sort: true},
              {field:'tradeAmt', type:"money",title: '交易金额',width:120, sort: true},
              {field:'branchDate', title: '分红日',width:120, sort: true},
              {field:'bonusReinvestDate', title: '红利再投日',width:150, sort: true},
              {field:'unitInt', type:"money",title: '单位分红',width:120, sort: true},
              {field:'cashBranchAmt', type:"money",title: '现金分红金额',width:150, sort: true},
              {field:'bonusReinvestQty',type:"money", title: '红利再投份额',width:150, sort: true},
              {field:'bonusReinvestAmt', type:"money",title: '红利再投金额',width:150, sort: true},
              {field:'reinvestUnitCprice',type:"money", title: '再投单位净值',width:150, sort: true},
              {field:'enterDate', title: '入账日',width:120, sort: true},
              {field:'ccyCode', title: '币种code',width:100, sort: true, visible:false},
              {field:'ccy', title: '币种',width:150, sort: true,sortField:'ccyCode'},
              {field:'operation', title: 'SysCommon.operation', type:'action', fixed: 'right' ,
                  buttons:[{text:'&#xe65e;', clas: 'orders', title:'查看结算指令'}]}
           ]
    });
    /**
     * 定义数据表格(计划类资产查询—货币型)
     */
    var planCurrencyTable=$("#plan-currency-table").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                //过滤业务类型 - 计划类资产(收益型)查询
                var nowBusitypeArr=['09'];
                //获取查询参数
                var paMap = getSearchParams();
                //数组转字符串，塞入查询参数中
                paMap.filterBusiType = nowBusitypeArr.join(',');
        	    paMap.finprodType='F17';
                paMap.profitType='03';
                return paMap;
            }
        },
        done:function(data){
            var busiType= $.trim($('#paBusiType').val());
            if(busiType.indexOf('09')>=0){
                tableInit('plan-currency-title', true);
            }else{
                //若查询到数据，显示页面
                tableInit('plan-currency-title', data!=null && data.length > 0);
            }
            
            showTotal('plan-currency-title', 'plan-currency-table');// 为 tab 显示 数据总数   
        },
        dblclick:function(table, data) {
        	var zyps=['T25','T26'];
			if(zyps.indexOf(data.psCode)<0){
				showPlanProduct(table,data);
			}
        },
        checkbox: true,
        cols:[
              {field:'tradeId', title: '交易编号',width:180, sort: true},
              {field:'busiTypeCode', title: '业务类型',width:100, sort: true, visible:false},
              {field:'prodId', title: '账套代码',width:150, sort: true},
              {field:'prodAbbr', title: '账套名称',width:150, sort: true},
              {field:'assetId', title: '资产代码',width:150, sort: true},
              {field:'assetAbbr', title: '资产名称',width:150, sort: true},
              {field:'psCode', title: '交易方向Code',width:100, sort: true, visible:false},
              {field:'ps', title: '交易方向',width:100, sort: true,sortField:'psCode'},
              {field:'purchaseDate', title: '申购日',width:120, sort: true},
              {field:'tradeDate', title: '申购/赎回确认日',width:150, sort: true},
              {field:'payDate', title: '到账日',width:120, sort: true},
              {field:'shareAmt', type:"money",title: '交易份额',width:120, sort: true},
              {field:'tradeAmt', type:"money",title: '交易金额',width:120, sort: true},
              {field:'branchDate', title: '分红日',width:120, sort: true},
              {field:'bonusReinvestDate', title: '红利再投日',width:150, sort: true},
              {field:'unitInt', type:"money",title: '单位分红',width:120, sort: true},
              {field:'cashBranchAmt',type:"money", title: '现金分红金额',width:150, sort: true},
              {field:'bonusReinvestQty',type:"money", title: '红利再投份额',width:150, sort: true},
              {field:'bonusReinvestAmt',type:"money", title: '红利再投金额',width:150, sort: true},
              {field:'enterDate', title: '入账日',width:120, sort: true},
              {field:'ccyCode', title: '币种code',width:100, sort: true, visible:false},
              {field:'ccy', title: '币种',width:150, sort: true,sortField:'ccyCode'},
              {field:'operation', title: 'SysCommon.operation', type:'action', fixed: 'right' ,
                  buttons:[{text:'&#xe65e;', clas: 'orders', title:'查看结算指令'}]}
           ]
    });
    /**
     * 绑定查看结算指令列表
     */
    planProfitTable.bind('click', '.orders', function(data, index) {
        showOrders(data, index, this);
    });
    /**
     * 绑定查看结算指令列表
     */
    planNetvalueTable.bind('click', '.orders', function(data, index) {
        showOrders(data, index, this);
    });
    /**
     * 绑定查看结算指令列表
     */
    planCurrencyTable.bind('click', '.orders', function(data, index) {
        showOrders(data, index, this);
    });
	function showPlanProduct(table,data){
	    //T01:买入,T02:卖出,T03:行权,T20:转入,T21:转出
	    var ps1=['T01','T02','T03','T20','T21'];
	    //T04:申购,T05:申购确认,T19:赎回确认,T21:赎回到账
	    var ps2=['T04','T05','T19','T24'];
	    //T11:现金分红,T12:红利再投
	    var ps3=['T11','T12'];
	    //T08:还本,T12:收息
        var ps4=['T08','T09','T22','T23','T25','T26'];
        var wide="80%";
        var high="80%";
        var title="查看计划类交易";
	    if(ps2.indexOf(data.psCode)>=0){
	        var wide="90%";
	        var high="80%";
	    }
	    if(ps1.indexOf(data.psCode)>=0){
        }
	    if(ps3.indexOf(data.psCode)>=0){
	    }
	    if(ps4.indexOf(data.psCode)>=0){
           title="查看计划类存续期交易"
	    }
	    Joyin.dialog(JoyinUtil.getLabel(title),
               JoyinUtil.getRealPath("/valuation/trademanage/investtradequery/planassetsdeal/ValPlanAssetsDeal001/init"),
               wide, high, {pageModel:PAGE_MODEL_REFERENCE,tradeId:data.tradeId,prodId:data.prodId,ps:data.psCode},
               function(){
                   //table.reload();
               });
	}

	 /**
     * 定义数据表格(拆借交易)
     */
	var lendTable=$("#lend-table").joyintable({
	    ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
            	//过滤业务类型 - 拆借交易查询   
            	var nowBusitypeArr=['01'];
            	//获取查询参数
            	var paMap = getSearchParams();
        	    //数组转字符串，塞入查询参数中
        	    paMap.filterBusiType = nowBusitypeArr.join(',');
                return paMap;
            }
        },
		done:function(data){
            var busiType= $.trim($('#paBusiType').val());
            if(busiType.indexOf('01')>=0){
                tableInit('lend-title', true);
            }else{
                //若查询到数据，显示页面
                tableInit('lend-title', data!=null && data.length > 0);
            }
			
			showTotal('lend-title', 'lend-table');// 为 tab 显示 数据总数   
		},
        dblclick:function(table, data) {
        	showLendTrade(table,data);
        },
        checkbox: true,
		cols:[
              {field:'tradeId', title: '交易编号',width:180, sort: true},
              {field:'prodId', title: '账套代码',width:150, sort: true},
              {field:'prodAbbr', title: '账套名称',width:320, sort: true},
              {field:'busiTypeCode', title: '业务类型',width:100, sort: true, visible:false},
              {field:'busiType', title: '业务类型',width:100, sort: true, visible:false},
              {field:'finprodTypeCode', title: '拆借方向Code',width:100, sort: true, visible:false},
              {field:'finprodType', title: '拆借方向',width:100, sort: true,sortField:'finprodTypeCode'},
              {field:'psCode', title: '交易方向code',width:100, sort: true, visible:false},
              {field:'ps', title: '交易方向',width:100, sort: true,sortField:'psCode'},
              {field:'counterId', title: '交易对手',width:100, sort: true, visible:true},
              {field:'counterName', title: '交易对手',width:120, sort: true, visible:false},
              {field:'prinAmt', type:"money",title: '拆借金额',width:150, sort: true},
              {field:'termTypeLend', title: '期限品种',width:100, sort: true,sortField:'termTypeLendCode'},
              {field:'termTypeLendCode', title: '期限品种Code',width:100, sort: true, visible:false},
              {field:'ccyCode', title: '交易币种code',width:100, sort: true, visible:false},
              {field:'ccy', title: '币种',width:150, sort: true,sortField:'ccyCode'},
              {field:'tradeDate', title: '交易日',width:120, sort: true},
              {field:'settleDate', title: '交割日',width:120, sort: true},
              {field:'vDate', title: '起息日',width:120, sort: true},
              {field:'mDate', title: '到期日',width:120, sort: true},
              {field:'intRate', align:"right", unit: 10000, title: '拆借利率(%)',width:120, sort: true},
              {field:'intAmt', type:"money", title: '应计利息', digit: 2, width:100, sort: true},
              {field:'tradeAmt', type:"money",title: '到期本息和',width:150, sort: true},
              {field:'tradeStatusCode', title: '交易状态Code',width:100, sort: true, visible:false},
              {field:'tradeStatus', title: '交易状态',width:100, sort: true,sortField:'tradeStatusCode'},
              {field:'operation', title: 'SysCommon.operation', type:'action', 
                  buttons:[{text:'&#xe65e;', clas: 'orders', title:'查看结算指令'}]}
           ]
	});
	/**
	 * 拆借交易双击详情
	 */
	function showLendTrade(table,data){
        var wide="98%";
        var high="85%";
    	title="查看拆借交易"
	    Joyin.dialog(JoyinUtil.getLabel(title),
               JoyinUtil.getRealPath("/valuation/trademanage/investtradequery/lendtrade/LendTradeQuery001/init"),
               wide, high, {pageModel:PAGE_MODEL_REFERENCE,tradeId:data.tradeId,prodId:data.prodId},
               function(){
                   //lendTable.reload();
               });
	}
	
	/**
     *  拆借交易,绑定查看结算指令列表
     */
	lendTable.bind('click', '.orders', function(data, index) {
    	showOrders(data, index, this);
    });
	

	/**
     * 定义数据表格(基金类资产(货币型)查询)
     */
    var fundCurrencyTable=$("#fund-currency-table").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                //过滤业务类型 - 基金类资产(货币型)查询
                var nowBusitypeArr =  ['06'];
                
                //获取查询参数
                var paMap = getSearchParams();
                //数组转字符串，塞入查询参数中
                paMap.filterBusiType = nowBusitypeArr.join(',');
                paMap.profitType='03';
                return paMap;
            }
        },
        dblclick:function(table, data) {
            //C3:份额强增,C6:份额强减,T20:转入,T21:转出
            var ps1=['C3','C6','T20','T21'];
            //T04:申购,T05:申购确认,T19:赎回确认,T24:赎回到账,C1:产品认购,T18:认购确认,认购
            var ps2=['T04','T05','T19','T24','C1','T18','T52'];
            //T11:现金分红,T12:红利再投
            var ps3=['T11','T12'];
            var wide="90%";
            var high="80%";
            var title="查看基金交易-"+data.ps;
            if(ps2.indexOf(data.psCode)>=0){
//                var wide="90%";
//                var high="80%";
            }
            if(ps1.indexOf(data.psCode)>=0){
            }
            if(ps3.indexOf(data.psCode)>=0){
               title="查看基金存续期交易-"+data.ps;
            }
            Joyin.dialog(JoyinUtil.getLabel(title),
                   JoyinUtil.getRealPath("/valuation/trademanage/investtradequery/fundcurrency/FundCurrency001/init"),
                   wide, high, {pageModel:PAGE_MODEL_REFERENCE,tradeId:data.tradeId,ps:data.psCode,busiType:data.busiTypeCode,prodId:data.prodId},
                   function(){
                       //fundCurrencyTable.reload();
                   });
        },
        done:function(data){
            var busiType= $.trim($('#paBusiType').val());
            if(busiType.indexOf('06')>=0){
                tableInit('fund-currency-title', true);
            }else{
                //若查询到数据，显示页面
                tableInit('fund-currency-title', data!=null && data.length > 0);
            }
            
            showTotal('fund-currency-title', 'fund-currency-table');// 为 tab 显示 数据总数   
        },
        checkbox: true,
        cols:[
              {field:'tradeId', title: '交易编号',width:180, sort: true},
              {field:'prodId', title: '账套代码',width:150, sort: true},
              {field:'prodAbbr', title: '账套名称',width:320, sort: true},
              {field:'assetId', title: '资产代码',width:150, sort: true},
              {field:'assetAbbr', title: '资产名称',width:200, sort: true},
              {field:'psCode', title: '交易方向Code',width:100, sort: true, visible:false},
              {field:'ps', title: '交易方向',width:120, sort: true,sortField:'psCode'},
              {field:'tradeDate', title: '交易日',width:120, sort: true},
              {field:'confirmDate', title: '确认日',width:120, sort: true},
              {field:'shareAmt',type:"money", title: '交易份额',width:140, sort: true},
              {field:'feeAmt', type:"money",title: '交易费用',width:120, sort: true},
              {field:'tradeAmt',type:"money", title: '交易金额',width:140, sort: true},
              {field:'bookDate', title: '入账日',width:120, sort: true},
              {field:'ccyCode', title: '交易币种code',width:100, sort: true, visible:false},
              {field:'ccy', title: '币种',width:150, sort: true,sortField:'ccyCode'},
//              {field:'settleAmt', title: '退款金额',width:120, sort: true},
              {field:'intAmt', type:"money", title: '利息金额',width:120, sort: true},
              {field:'settleDate', title: '预计到账日',width:120, sort: true},
              {field:'realDate', title: '实际到账日',width:120, sort: true},
              {field:'cashAmt',type:"money", title: '现金分红金额',width:140, sort: true},
              {field:'bonusAmt', type:"money",title: '红利再投金额',width:140, sort: true},
              {field:'bonusShare', type:"money",title: '红利再投份额',width:140, sort: true},
              {field:'bAmt',type:"money",title: '应收红利',width:120, sort: true},
              {field:'realAmt',type:"money",title: '实收红利',width:120, sort: true},
              {field:'busiTypeCode', title: '业务类型',width:100, sort: true, visible:false},
              {field:'operation', title: 'SysCommon.operation', type:'action', fixed: 'right' ,
                  buttons:[{text:'&#xe65e;', clas: 'orders', title:'查看结算指令'}]}
           ]
    });
     /**
     * 为基金类资产(货币型)查询绑定查看结算指令列表
     */
    fundCurrencyTable.bind('click', '.orders', function(data, index) {
        showOrders(data, index);
    });

    /**
     * 定义数据表格(债券重分类)
     */
	var reclassTable=$("#value-reclass-table").joyintable({
	    ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
            	//过滤业务类型 - 债券重分类   
            	var nowBusitypeArr=['30'];
            	//获取查询参数
            	var paMap = getSearchParams();
        	    //数组转字符串，塞入查询参数中
        	    paMap.filterBusiType = nowBusitypeArr.join(',');
                return paMap;
            }
        },
		done:function(data){
            var busiType= $.trim($('#paBusiType').val());
            if(busiType.indexOf('30')>=0){
                tableInit('value-reclass-title', true);
            }else{
                //若查询到数据，显示页面
                tableInit('value-reclass-title', data!=null && data.length > 0);
            }
			
			showTotal('value-reclass-title', 'value-reclass-table');// 为 tab 显示 数据总数   
		},
        dblclick:function(table, data) {

        },
        checkbox: true,
		cols:[
              		{field:'tradeId',title: '交易编号'}
		        	,{field:'prodId', title: '账套代码'}
		            ,{field:'prodAbbr',  title: '账套名称'}
		            ,{field:'tradeDate',  title: '重分类日期'}
		            ,{field:'assetId',  title: '资产代码'}
		            ,{field:'assetAbbr',  title: '资产名称'}
		            ,{field:'invAim', sortField:"invAimCode",  title: '原会计分类'}
		            ,{field:'newInvAimFmt', sortField:"newInvAim", title: '新会计分类'}
           ]
	});
	
	/**
     * 定义数据表格(基金类资产(净值型)查询)
     */
    var fundNetvalueTable=$("#fund-netvalue-table").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                //过滤业务类型 - 基金类资产(净值型)查询
                var nowBusitypeArr =  ['07'];
                
                //获取查询参数
                var paMap = getSearchParams();
                //数组转字符串，塞入查询参数中
                paMap.filterBusiType = nowBusitypeArr.join(',');
                paMap.profitType='01';
                return paMap;
            }
        },
        dblclick:function(table, data) {
            //C3:份额强增,C6:份额强减,T20:转入,T21:转出
            var ps1=['C3','C6','T20','T21'];
            //T04:申购,T05:申购确认,T19:赎回确认,T24:赎回到账,C1:产品认购,T18:认购确认
            var ps2=['T04','T05','T19','T24','C1','T18'];
            //T11:现金分红,T12:红利再投
            var ps3=['T11','T12'];
            var wide="90%";
            var high="80%";
            var title="查看基金交易-"+data.ps;
            if(ps2.indexOf(data.psCode)>=0){
//                var wide="90%";
//                var high="80%";
            }
            if(ps1.indexOf(data.psCode)>=0){
            }
            if(ps3.indexOf(data.psCode)>=0){
               title="查看基金存续期交易-"+data.ps;
            }
            Joyin.dialog(JoyinUtil.getLabel(title),
                   JoyinUtil.getRealPath("/valuation/trademanage/investtradequery/fundcurrency/FundCurrency001/init"),
                   wide, high, {pageModel:PAGE_MODEL_REFERENCE,tradeId:data.tradeId,ps:data.psCode,busiType:data.busiTypeCode,prodId:data.prodId},
                   function(){
                       //fundNetvalueTable.reload();
                   });
        },
        done:function(data){
            var busiType= $.trim($('#paBusiType').val());
            if(busiType.indexOf('07')>=0){
                tableInit('fund-netvalue-title', true);
            }else{
                //若查询到数据，显示页面
                tableInit('fund-netvalue-title', data!=null && data.length > 0);
            }
            
            showTotal('fund-netvalue-title', 'fund-netvalue-table');// 为 tab 显示 数据总数  
        },
        checkbox: true,
        cols:[
              {field:'tradeId', title: '交易编号',width:180,sort: true},
              {field:'prodId', title: '账套代码',width:150,sort: true},
              {field:'prodAbbr', title: '账套名称',width:320,sort: true},
              {field:'assetId', title: '资产代码',width:150,sort: true},
              {field:'assetAbbr', title: '资产名称',width:150,sort: true},
              {field:'psCode', title: '交易方向Code',width:100, sort: true, visible:false},
              {field:'ps', title: '交易方向',width:120, sort: true,sortField:'psCode'},
              {field:'tradeDate', title: '交易日',width:120, sort: true},
              {field:'confirmDate', title: '确认日',width:120, sort: true},
              {field:'shareAmt',type:"money", title: '交易份额',width:140, sort: true},
              {field:'feeAmt',type:"money", title: '交易费用',width:120, sort: true},
              {field:'tradeAmt',type:"money", title: '交易金额',width:140, sort: true},
              {field:'bookDate', title: '入账日',width:120, sort: true},
              {field:'ccyCode', title: '交易币种code',width:100, sort: true, visible:false},
              {field:'ccy', title: '币种',width:150, sort: true,sortField:'ccyCode'},
//              {field:'settleAmt', title: '退款金额',width:120, sort: true},
              {field:'intAmt',type:"money", title: '利息金额',width:120, sort: true},
              {field:'cpriceDate', title: '净值日',width:120, sort: true},
              {field:'unitCprice', type:"money",title: '赎回净值',width:120, sort: true},
              {field:'settleDate', title: '预计到账日',width:140, sort: true},
              {field:'realDate', title: '实际到账日',width:140, sort: true},
              {field:'cashAmt', type:"money",title: '现金分红金额',width:140, sort: true},
              {field:'bonusAmt',type:"money", title: '红利再投金额',width:140, sort: true},
              {field:'bonusShare',type:"money", title: '红利再投份额',width:140, sort: true},
              {field:'busiTypeCode', title: '业务类型',width:100, sort: true, visible:false},
              {field:'operation', title: 'SysCommon.operation', type:'action', fixed: 'right' ,
                  buttons:[{text:'&#xe65e;', clas: 'orders', title:'查看结算指令'}]}
           ]
    });
     /**
     * 为基金类资产(净值型)查询绑定查看结算指令列表
     */
    fundNetvalueTable.bind('click', '.orders', function(data, index) {
        showOrders(data, index);
    });
    
    
    /**
     * 定义数据表格(回购交易)
     */
	var repurchaseTable=$("#repurchase-table").joyintable({
	    ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
            	//过滤业务类型 - 回购交易查询  
            	var nowBusitypeArr=['04', '05'];
            	//获取查询参数
            	var paMap = getSearchParams();
        	    //数组转字符串，塞入查询参数中
        	    paMap.filterBusiType = nowBusitypeArr.join(',');
                return paMap;
            }
        },
		done:function(data){
		    var busiType= $.trim($('#paBusiType').val());
            if(busiType.indexOf('04')>=0||busiType.indexOf('05')>=0){
                tableInit('repurchase-title', true);
            }else{
                //若查询到数据，显示页面
                tableInit('repurchase-title', data!=null && data.length > 0);
            }
            
            showTotal('repurchase-title', 'repurchase-table');// 为 tab 显示 数据总数  
		},
        dblclick:function(table, data) {
        	showRepurchaseTrade(table,data);
        },
        checkbox: true,
		cols:[
              {field:'tradeId', title: '交易编号',width:180, sort: true},
              {field:'prodId', title: '账套代码',width:150, sort: true},
              {field:'prodAbbr', title: '账套名称',width:320, sort: true},
              {field:'busiTypeCode', title: '业务类型',width:100, sort: true, visible:false},
              {field:'busiType', title: '回购类型',width:150, sort: true,sortField:'busiTypeCode'},
              {field:'finprodTypeCode', title: '回购方向Code',width:100, sort: true, visible:false},
              {field:'finprodType', title: '回购方向',width:150, sort: true,sortField:'finprodTypeCode'},
              {field:'psCode', title: '交易方向Code',width:100, sort: true, visible:false},
              {field:'ps', title: '交易方向',width:100, sort: true,sortField:'psCode'},
              {field:'counterId', title: '交易对手',width:100, sort: true,visible:true},
              {field:'counterName', title: '交易对手',width:180, sort: true,visible:false},
              {field:'ccyCode', title: '交易币种code',width:100, sort: true, visible:false},
			  {field:'ccy', title: '币种',width:150, sort: true,sortField:'ccyCode'},
              {field:'tradeDate', title: '交易日',width:120, sort: true},
              {field:'vDate', title: '起息日',width:120, sort: true},
              {field:'mDate', title: '到期日',width:120, sort: true},
              {field:'termType', title: '期限品种',width:100, sort: true},
              {field:'intRate',align:"right", unit: 10000, title: '回购利率(%)',width:120, sort: true},
              {field:'prinAmt', type:"money", title: '交易本金',width:150, sort: true},
              {field:'firSetAmt', type:"money", title: '首次结算金额',width:150, sort: true},
              {field:'expSetAmt',type:"money", title: '到期结算金额',width:150, sort: true},
              {field:'intAmt', type:"money", title: '应计利息', digit: 6, width:120, sort: true},
              {field:'divAmt', type:"money", title: '派息金额',width:120, sort: true},
              {field:'tranAmt', type:"money", title: '转入/转出利息金额',width:150, sort: true},
			  {field:'feeAmtPay', type:"money", title: '随交易支付费用',width:150, sort: true, render: function (data, type, row, meta) {
		            if(data == '') {
		            	return '';
		            }
		            return formatMoney(data, 2);
	              }},
	              {field:'feeAmtUnpay', type:"money", title: '不随交易支付费用',width:150, sort: true, render: function (data, type, row, meta) {
		            if(data == '') {
		            	return '';
		            }
		            return formatMoney(data, 2);
	              }},
              {field:'tradeAmt',type:"money", title: '交易金额',width:150, sort: true},
              {field:'chlId', title: '通道',width:100, sort: true},
              {field:'tradeStatusCode', title: '交易状态',width:100, sort: true, visible:false},
              {field:'tradeStatus', title: '交易状态',width:100, sort: true,sortField:'tradeStatusCode'},
              {field:'operation', title: 'SysCommon.operation', type:'action', 
                  buttons:[{text:'&#xe65e;', clas: 'orders', title:'查看结算指令'}]}
           ]
	});
	/**
	 * 回购交易双击详情
	 */
	function showRepurchaseTrade(table,data){
        var wide="98%";
        var high="85%";
    	title="查看回购交易"
//	PAGE_MODEL_EDIT / PAGE_MODEL_REFERENCE
	    Joyin.dialog(JoyinUtil.getLabel(title),
               JoyinUtil.getRealPath("/valuation/trademanage/investtradequery/repurchasetrade/RepurchaseTradeQuery001/init"),
               wide, high, {pageModel:PAGE_MODEL_REFERENCE,tradeId:data.tradeId,prodId:data.prodId},
               function(){
                   //repurchaseTable.reload();
               });
	}
	
	/**
     *  回购交易,绑定查看结算指令列表
     */
	repurchaseTable.bind('click', '.orders', function(data, index) {
    	showOrders(data, index, this);
    });
    

    /**
     * 定义数据表格(活期存款收息交易查询)
     */
    var currentDepositTable=$("#current-deposit-table").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                //过滤业务类型 - 活期存款收息交易查询
                var nowBusitypeArr =  ['15'];
                
                //获取查询参数
                var paMap = getSearchParams();
                //数组转字符串，塞入查询参数中
                paMap.filterBusiType = nowBusitypeArr.join(',');
                
                return paMap;
            }
        },
        //活期存款收息交易（双击查询结果）查看
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("查看活期存款交易"),
                    JoyinUtil.getRealPath("/valuation/trademanage/investtradequery/currentdeposit/CurrentDeposit001/init"),
                    "90%", "90%", {pageModel:PAGE_MODEL_REFERENCE,
                tradeId:data.tradeId,prodId:data.prodId},
                    function(){
                    //currentDepositTable.reload();
                    });
        },
        done:function(data){
            var busiType= $.trim($('#paBusiType').val());
            if(busiType.indexOf('15')>=0){
                tableInit('current-deposit-title', true);
            }else{
                //若查询到数据，显示页面
                tableInit('current-deposit-title', data!=null && data.length > 0);
            }
            
            showTotal('current-deposit-title', 'current-deposit-table');// 为 tab 显示 数据总数  
        },
        checkbox: true,
        cellMinWidth : 100,
        cols:[
              {field:'tradeId', title: '交易编号',width:180,sort: true},
              {field:'prodId', title: '账套代码',width:150,sort: true},
              {field:'prodAbbr', title: '账套名称',width:150,sort: true},
              {field:'acctName', title: '资金账户名',width:280,sort: true},
              {field:'vDate', title: '上次收息日',width:120,sort: true},
              {field:'mDate', title: '本次收息日',width:120, sort: true},
              {field:'theoryIntAmt', title: '理论利息',type:"money",width:100, sort: true},
              {field:'realIntAmt', title: '实际利息', type:"money",width:120, sort: true},
              {field:'tradeStatusCode', title: '交易状态',sort: true, visible:false},
              {field:'tradeStatus', title: '交易状态',width:120, sort: true,sortField:'tradeStatusCode'},
              {field:'busiTypeCode', title: '业务类型',width:150, sort: true, visible:false},
              {field:'operation', title: 'SysCommon.operation', type:'action', fixed: 'right' ,
                  buttons:[{text:'&#xe65e;', clas: 'orders', title:'查看结算指令'}]}
           ]
        
        } ); 
    /**
     * 为活期存款收息交易绑定查看结算指令列表
     */
    currentDepositTable.bind('click', '.orders', function(data, index) {
        showOrders(data, index);
    });
    
    /**
     * 为活期存款收息交易资金账户绑定查看活期存款收息交易
     */
    var selectByAccountName = function (tradeId, pageModel) {
        var params = {tradeId: tradeId, pageModel: pageModel};
        Joyin.dialog(JoyinUtil.getLabel("查看活期存款收息交易"),'selectByAccountName',
              "90%", "90%", params,function(){
                  currentDepositTable.reload();
              });
    }
    
    
    /**
     * 定义数据表格(交易账户收划款查询)
     */
    var tradeAccountTable=$("#trade-account-table").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                //过滤业务类型 - 交易账户收划款查询
                var nowBusitypeArr =  ['19'];
                
                //获取查询参数
                var paMap = getSearchParams();
                //数组转字符串，塞入查询参数中
                paMap.filterBusiType = nowBusitypeArr.join(',');
                
                return paMap;
            }
        },
        //交易账户收划款（双击查询结果）查看
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("查看交易账户信息"),
                    JoyinUtil.getRealPath("/valuation/trademanage/investtradequery/tradeaccount/TradeAccount001/init"),
                    "90%", "90%", {pageModel:PAGE_MODEL_REFERENCE,
                tradeId:data.tradeId,prodId:data.prodId},
                    function(){
                    //table.reload();
                    });
        },
        done:function(data){
            var busiType= $.trim($('#paBusiType').val());
            if(busiType.indexOf('19')>=0){
                tableInit('trade-account-title', true);
            }else{
                //若查询到数据，显示页面
                tableInit('trade-account-title', data!=null && data.length > 0);
            }
            
            showTotal('trade-account-title', 'trade-account-table');// 为 tab 显示 数据总数  
        },
        checkbox: true,
        cellMinWidth : 100,
        cols:[
              {field:'tradeId', title: '交易编号',width:180,sort: true},
              {field:'prodId', title: '账套代码',width:180,sort: true},
              {field:'prodAbbr', title: '账套名称',width:200,sort: true},
              {field:'typeFt', title: '收划款类型',width:150,sort: true,sortField:'type'},
              {field:'settleDat', title: '收划款日期',width:120, sort: true},
              {field:'settleAmt', type:'money',title: '收划款金额',width:120,sort: true},
              {field:'ccyCode', title: '交易币种code',width:100, sort: true, visible:false},
              {field:'ccy', title: '币种',width:150, sort: true,sortField:'ccyCode'},
              {field:'myAcctName', title: '收款账户户名',width:120, sort: true},
              {field:'myAcctNo', title: '收款账户账号',width:120,sort: true},
              {field:'counterAcctName', title: '付款账户户名',width:120, sort: true},
              {field:'counterAcctNo', title: '付款账户账号',width:120, sort: true},
              {field:'statusFt', title: '收划款状态',width:120, sort: true,sortField:'status'},
              {field:'busiTypeCode', title: '业务类型',width:100, sort: true, visible:false},
              {field:'operation', title: 'SysCommon.operation', type:'action', fixed: 'right' ,
                  buttons:[{text:'&#xe65e;', clas: 'orders', title:'查看结算指令'}]}
           ]
        
        } ); 
    /**
     * 为交易账户收划款绑定查看结算指令列表
     */
    tradeAccountTable.bind('click', '.orders', function(data, index) {
        showOrders(data, index);
    });
    
    /**
     * 定义数据表格(产品费用交易查询)
     */
    var cashtransactionsTable=$("#cash-transactions-table").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                //过滤业务类型 - 费用交易
                var nowBusitypeArr =  ['18'];
                //获取查询参数
                var paMap = getSearchParams();
                //数组转字符串，塞入查询参数中
                paMap.filterBusiType = nowBusitypeArr.join(',');
                return paMap;
            }
        },
        done:function(data){
            var busiType= $.trim($('#paBusiType').val());
            if(busiType.indexOf('18')>=0){
                tableInit('cash-transactions-title', true);
            }else{
                //若查询到数据，显示页面
                tableInit('cash-transactions-title', data!=null && data.length > 0);
            }

           showTotal('cash-transactions-title', 'cash-transactions-table');// 为 tab 显示 数据总数  
        },
        checkbox: true,
        cellMinWidth : 100,
        cols:[
              {field:'tradeId', title: '交易编号', sort: true},
              {field:'prodId', title: '账套代码', sort: true},
              {field:'prodAbbr', title: '账套名称',sort: true},
              {field:'feeTypeNameCode', title: '交易类型', sort: true, visible:false},
              {field:'finprodTypeName', title: '交易类型', sort: true,sortField:'feeTypeNameCode'},
              {field:'tradeDate', title: '交易日', sort: true},                        
              {field:'tradeAmt', title: '交易金额', type:"money", sort: true},
              {field:'ccyCode', title: '币种', sort: true, visible:false},
              {field:'ccy', title: '币种', sort: true,sortField:'ccyCode'},
              {field:'tradeStatusCode', title: '交易状态',sort: true, visible:false},
              {field:'tradeStatus', title: '交易状态',sort: true,sortField:'tradeStatusCode'},
              {field:'remark', title: '备注', sort: true},
              {field:'busiTypeCode', title: '业务类型',width:150, sort: true, visible:false},
              {field:'operation', title: 'SysCommon.operation', type:'action', fixed: 'right',
                  buttons:[{text:'&#xe65e;', clas: 'orders', title:'查看结算指令'}]}
           ]
    });
    
  /**
   *  为产品费用交易查询绑定查看结算指令列表
   */
    cashtransactionsTable.bind('click', '.orders', function(data, index) {
           showOrders(data, index);
    });
    
    
    /**
     * 定义数据表格(增值税交易询)
     */
    var valueAddedtaxsTable=$("#value-addedtaxs-table").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                //过滤业务类型 - 费用交易
                var nowBusitypeArr =  ['16'];
                //获取查询参数
                var paMap = getSearchParams();
                //数组转字符串，塞入查询参数中
                paMap.filterBusiType = nowBusitypeArr.join(',');
                return paMap;
            }
        },
        done:function(data){
            var busiType= $.trim($('#paBusiType').val());
            if(busiType.indexOf('16')>=0){
                tableInit('value-addedtaxs-title', true);
            }else{
                //若查询到数据，显示页面
                tableInit('value-addedtaxs-title', data!=null && data.length > 0);
            }
           
           showTotal('value-addedtaxs-title', 'value-addedtaxs-table');// 为 tab 显示 数据总数  
        },
        checkbox: true,
        cellMinWidth : 100,
        cols:[
              {field:'tradeId', title: '交易编号', sort: true},
              {field:'prodId', title: '账套代码', sort: true},
              {field:'prodAbbr', title: '账套名称',sort: true},
              {field:'feeTypeCode', title: '交易类型', sort: true, visible:false},
              {field:'feeTypeName', title: '交易类型', sort: true,sortField:'feeTypeCode'},
              {field:'tradeDate', title: '交易日', sort: true},                        
              {field:'tradeAmt', type:'money',title: '交易金额',  sort: true},
              {field:'ccyCode', title: '币种', sort: true, visible:false},
              {field:'ccy', title: '币种', sort: true,sortField:'ccyCode'},
              {field:'tradeStatusCode', title: '交易状态',sort: true, visible:false},
              {field:'tradeStatus', title: '交易状态',sort: true,sortField:'tradeStatusCode'},
              {field:'remark', title: '备注', sort: true},
              {field:'busiTypeCode', title: '业务类型',width:150, sort: true, visible:false},
              {field:'feeTypeCode', title: '费用类型编号',width:150, sort: true, visible:false},
              {field:'operation', title: 'SysCommon.operation', type:'action', fixed: 'right',
                  buttons:[{text:'&#xe65e;', clas: 'orders', title:'查看结算指令'}]}
           ]
    });
    
    /**
     * 为增值税交易绑定查看结算指令列表
     */
    valueAddedtaxsTable.bind('click', '.orders', function(data, index) {
        showOrders(data, index);
    });
    
    
    /**
     * 定义数据表格(债券借贷交易查询)
     */
    var bondLendTable=$("#bond-lend-table").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                //过滤业务类型 - 费用交易
                var nowBusitypeArr =  ['14'];
                //获取查询参数
                var paMap = getSearchParams();
                //数组转字符串，塞入查询参数中
                paMap.filterBusiType = nowBusitypeArr.join(',');
                return paMap;
            }
        },
        dblclick:function(table, data) {
            //T01:买入  T07:到期
            var ps1=['T01','T07'];
            if(ps1.indexOf(data.psCode)>=0){
                var wide="90%";
                var high="80%";
                Joyin.dialog(JoyinUtil.getLabel("查看债券借贷"),
                       JoyinUtil.getRealPath("/valuation/trademanage/investtradequery/bondlend/ValBondLend001/init"),
                       wide, high, {pageModel:PAGE_MODEL_REFERENCE,tradeId:data.tradeId,ps:data.psCode,prodId:data.prodId},
                       function(){
                       });
            }
            
        },
        done:function(data){
            var busiType= $.trim($('#paBusiType').val());
            if(busiType.indexOf('14')>=0){
                tableInit('bond-lend-title', true);
            }else{
                //若查询到数据，显示页面
                tableInit('bond-lend-title', data!=null && data.length > 0);
            }
           
           showTotal('bond-lend-title', 'bond-lend-table');// 为 tab 显示 数据总数  
        },
        checkbox: true,
        cellMinWidth : 100,
        cols:[
              {field:'tradeId', title: '交易编号',width:180, sort: true},
              {field:'prodId', title: '账套代码',width:150, sort: true},
              {field:'prodAbbr', title: '账套名称',width:320, sort: true},
              {field:'assetId', title: '标的债券代码',width:150, sort: true},
              {field:'assetAbbr', title: '标的债券简称',width:150, sort: true},
              {field:'psCode', title: '交易方向Code',width:100, sort: true, visible:false},
              {field:'ps', title: '交易方向',width:100, sort: true,sortField:'psCode'},
              {field:'finprodTypeCode', title: '借贷方向Code',width:100, sort: true, visible:false},
              {field:'finprodType', title: '借贷方向',width:150, sort: true,sortField:'finprodTypeCode'},
              {field:'termType', title: '借贷期限(天)',width:120, sort: true},
              {field:'basis', title: '计费基础',width:100, sort: true},
              {field:'intRate',align:"right", unit: 10000, title: '费率(%)',width:120, sort: true},
              {field:'ccyCode', title: '币种Code',width:150, sort: true, visible:false},
              {field:'ccy', title: '币种',width:150, sort: true,sortField:'ccyCode'},
              {field:'shareAmt', type:"money", title: '券面总额/交易本金',width:200, sort: true},
              {field:'cdAmt',type:"money", title: '借贷费用',width:150, sort: true},
              {field:'tradeAmt',type:"money", title: '交易金额',width:150, sort: true},
              {field:'registOrgName', title: '登记托管机构',sortField:'registOrg',width:250, sort: true},
              {field:'registOrg', title: '登记托管机构Code',width:100, sort: true, visible:false},
              {field:'feeAmt', title: '结算手续费',width:100, sort: true},
              {field:'isPayWithTradeStr', title: '是否随交易支付',width:150, sort: true,sortField:'isPayWithTrade'},
              {field:'isPayWithTrade', title: '是否随交易支付Code',width:100, sort: true, visible:false},
              {field:'tradeStatusCode', title: '交易状态',width:100, sort: true, visible:false},
              {field:'tradeStatus', title: '交易状态',width:100, sort: true,sortField:'tradeStatusCode'},
              {field:'busiTypeCode', title: '业务类型',width:100, sort: true, visible:false},
              {field:'operation', title: 'SysCommon.operation', type:'action',
                  buttons:[{text:'&#xe65e;', clas: 'orders', title:'查看结算指令'}]}
           ]
    });
    
    /**
     * 为债券借贷交易绑定查看结算指令列表
     */
    bondLendTable.bind('click', '.orders', function(data, index) {
        showOrders(data, index);
    });
    
    /**
     * 定义数据表格(逾期罚息交易查询)
     */
    var overduePenaltyTable=$("#overdue-penalty-table").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                //过滤业务类型 - 逾期罚息交易
                var nowBusitypeArr =  ['37'];
                //获取查询参数
                var paMap = getSearchParams();
                //数组转字符串，塞入查询参数中
                paMap.filterBusiType = nowBusitypeArr.join(',');
                return paMap;
            }
        },
        dblclick:function(table, data) {
            var wide="90%";
            var high="80%";
            Joyin.dialog(JoyinUtil.getLabel("查看逾期交易信息"),
                   JoyinUtil.getRealPath("/valuation/trademanage/investtradequery/overduepenalty/ValOverduePenalty001/init"),
                   wide, high, {pageModel:PAGE_MODEL_REFERENCE,tradeId:data.tradeId,ps:data.psCode,prodId:data.prodId},
                   function(){
                   });
        },
        done:function(data){
            var busiType= $.trim($('#paBusiType').val());
            if(busiType.indexOf('37')>=0){
                tableInit('overdue-penalty-title', true);
            }else{
                //若查询到数据，显示页面
                tableInit('overdue-penalty-title', data!=null && data.length > 0);
            }
           
           showTotal('overdue-penalty-title', 'overdue-penalty-table');// 为 tab 显示 数据总数  
        },
        checkbox: true,
        cellMinWidth : 100,
        cols:[
              {field:'tradeId', title: '交易编号',width:180, sort: true},
              {field:'prodId', title: '账套代码',width:150, sort: true},
              {field:'prodAbbr', title: '账套名称',width:240, sort: true},
              {field:'assetId', title: '资产代码',width:150, sort: true},
              {field:'assetAbbr', title: '资产名称',width:240, sort: true},
              {field:'psCode', title: '交易方向Code',width:100, sort: true, visible:false},
              {field:'ps', title: '交易方向',width:130, sort: true,sortField:'psCode'},
              {field:'tradeMarketCode', title: '交易场所Code',width:100, sort: true, visible:false},
              {field:'tradeMarket', title: '交易场所',width:150, sort: true,sortField:'tradeMarketCode'},
			   {field:'invAimCode', title: '投资目的Code',width:100, sort: true, visible:false},
			   {field:'invAim', title: '投资目的',width:200, sort: true,sortField:'invAimCode'},
			   {field:'tradeDate', title: '发生日',width:120, sort: true},
              {field:'ccyCode', title: '币种Code',width:150, sort: true, visible:false},
              {field:'ccy', title: '币种',width:150, sort: true,sortField:'ccyCode'},
              {field:'prinAmt', type:"money", title: '交易本金',width:150, sort: true},
              {field:'intAmt',type:"money", title: '交易利息',width:150, sort: true},
              {field:'tradeAmt',type:"money", title: '交易金额',width:150, sort: true},
              {field:'tradeStatusCode', title: '交易状态',width:100, sort: true, visible:false},
              {field:'tradeStatus', title: '交易状态',width:100, sort: true,sortField:'tradeStatusCode'},
              {field:'busiTypeCode', title: '业务类型',width:100, sort: true, visible:false},
              {field:'operation', title: 'SysCommon.operation', type:'action', 
                  buttons:[{text:'&#xe65e;', clas: 'orders', title:'查看结算指令'}]}
           ]
    });
    
    /**
     * 为逾期罚息交易绑定查看结算指令列表
     */
    overduePenaltyTable.bind('click', '.orders', function(data, index) {
        showOrders(data, index, this);
    });
    
  
    /**
     * 为 tab 显示 数据总数     
     * tabTitle : tab 标题
     * tabTable : tab 内的表
     */
    var showTotal = function (tabTitle, tabTable){
    	var title = $('#' + tabTitle);
    	var table = $('#' + tabTable);
    	var oldText = title.text().split("（")[0];
    	var count =  table.parent().find(".layui-laypage-count").text().replace(/[^0-9]/ig,"");
        title.text(oldText + '（' + (count==""?0:count) +  '）');
    }
    
	// 根据下标选项卡下标动态加载对应表格
	var loadTable = function(index) {
		// 判断当前选项卡对应的表格是否已经加载，如果对应下标的数据在loaded缓存中不存在，则表示未加载
        	//加载表格 - 债券交易
			index === 0 && bondTable.table.table.scrollPatch();
        	//加载表格 - 计划类资产(收益型)查询
			index === 1 && planProfitTable.table.table.scrollPatch();
        	//加载表格 - 计划类资产(净值型)查询
			index === 2 && planNetvalueTable.table.table.scrollPatch();
        	//加载表格 - 计划类资产(货币性)查询
			index === 3 && planCurrencyTable.table.table.scrollPatch();
        	//加载表格 - 存款类资产交易查询
			index === 4 && depositloan.table.table.scrollPatch();
        	//加载表格 - 拆借交易查询
			index === 5 && lendTable.table.table.scrollPatch();
        	//加载表格 - 回购交易查询
			index === 6 && repurchaseTable.table.table.scrollPatch();
        	//加载表格 - 基金类资产(货币型)查询 
			index === 7 && fundCurrencyTable.table.table.scrollPatch();
            //加载表格 - 基金类资产(净值型)查询
			index === 8 && fundNetvalueTable.table.table.scrollPatch();
            //加载表格 - 活期存款收息交易查询
			index === 9 && currentDepositTable.table.table.scrollPatch();
            //加载表格 - 现金类交易查询
			index === 10 && cashtransactionsTable.table.table.scrollPatch();
            //加载表格 - 交易账户收划款查询
			index === 11 && tradeAccountTable.table.table.scrollPatch();
			//加载表格 - 增值税交易查询
			index === 12 && valueAddedtaxsTable.table.table.scrollPatch();
			//加载表格 - 债券重分类交易查询
			index === 13 && reclassTable.table.table.scrollPatch();
			//加载表格 - 债券借贷交易查询
            index === 14 && bondLendTable.table.table.scrollPatch();
            //加载表格 - 逾期罚息交易查询
            index === 15 && overduePenaltyTable.table.table.scrollPatch();
			
	};
	var allLoadTable = function() {

        $('.layui-tab-content').find('.layui-tab-item').addClass('layui-show');
            //加载表格 - 债券交易
            bondTable.load();
            //加载表格 - 计划类资产(收益型)查询
            planProfitTable.options.height = bondTable.getHeight();
            planProfitTable.load();
            //加载表格 - 计划类资产(净值型)查询
            planNetvalueTable.options.height = bondTable.getHeight();
            planNetvalueTable.load();
            //加载表格 - 计划类资产(货币性)查询
            planCurrencyTable.options.height = bondTable.getHeight();
            planCurrencyTable.load();
            //加载表格 - 存款类资产交易查询
            depositloan.options.height = bondTable.getHeight();
            depositloan.load();
            //加载表格 - 拆借交易查询
            lendTable.options.height = bondTable.getHeight();
            lendTable.load();
            //加载表格 - 回购交易查询
            repurchaseTable.options.height = bondTable.getHeight();
            repurchaseTable.load();
            //加载表格 - 基金类资产(货币型)查询 
            fundCurrencyTable.options.height = bondTable.getHeight();
            fundCurrencyTable.load();
            //加载表格 - 基金类资产(净值型)查询
            fundNetvalueTable.options.height = bondTable.getHeight();
            fundNetvalueTable.load();
            //加载表格 - 活期存款收息交易查询
            currentDepositTable.options.height = bondTable.getHeight();
            currentDepositTable.load();
            //加载表格 - 现金类交易查询
            cashtransactionsTable.options.height = bondTable.getHeight();
            cashtransactionsTable.load();
            //加载表格 - 交易账户收划款查询
            tradeAccountTable.options.height = bondTable.getHeight();
            tradeAccountTable.load();
            //加载表格 - 增值税交易查询
            valueAddedtaxsTable.options.height = bondTable.getHeight();
            valueAddedtaxsTable.load();
            //加载表格 - 债券重分类交易查询
            reclassTable.options.height = bondTable.getHeight();
            reclassTable.load();
            //加载表格 - 债券借贷交易查询
            bondLendTable.options.height = bondTable.getHeight();
            bondLendTable.load();
            //加载表格 - 债券借贷交易查询
            overduePenaltyTable.options.height = bondTable.getHeight();
            overduePenaltyTable.load();
            
        }
	
	var exportExcel = function() {
		
		var id = $(".layui-this").attr("id");
		var profitType;
		var rows;
		var nowBusitypeArr;
		
		//债券交易
		if(id=='bond-title'){
			//因为初始化默认债券为选择..所以特殊处理查看当前界面是否初始化
			if(bondTable.table){
				nowBusitypeArr = ['02','20'];
				rows = bondTable.getAllCheckedRows();
			}else{
				return;
			}
		//计划类资产(收益型)查询
		}else if(id=='plan-profit-title'){
			nowBusitypeArr = ['09'];
			profitType = ['02'];
			rows = planProfitTable.getAllCheckedRows();
		//计划类资产(净值型)查询
		}else if(id=='plan-netvalue-title'){
			nowBusitypeArr = ['09'];
			profitType = ['01'];
			rows = planNetvalueTable.getAllCheckedRows();
		//计划类资产(货币性)查询
		}else if(id=='plan-currency-title'){
			nowBusitypeArr = ['09'];
			profitType = ['03'];
			rows = planCurrencyTable.getAllCheckedRows();
		//存款类资产交易查询
		}else if(id=='deposit-loan-title'){
			nowBusitypeArr = ['03'];
			rows = depositloan.getAllCheckedRows();
		//拆借交易查询
		}else if(id=='lend-title'){
			nowBusitypeArr = ['01'];
			rows = lendTable.getAllCheckedRows();
		//回购交易查询
		}else if(id=='repurchase-title'){
			nowBusitypeArr = ['04','05'];
			rows = repurchaseTable.getAllCheckedRows();
		//基金类资产(货币型)查询 
		}else if(id=='fund-currency-title'){
			nowBusitypeArr = ['06'];
			rows = fundCurrencyTable.getAllCheckedRows();
		//基金类资产(净值型)查询
		}else if(id=='fund-netvalue-title'){
			nowBusitypeArr = ['07'];
			rows = fundNetvalueTable.getAllCheckedRows();
		//活期存款收息交易查询
		}else if(id=='current-deposit-title'){
			nowBusitypeArr = ['15'];
			rows = currentDepositTable.getAllCheckedRows();
		//现金类交易查询
		}else if(id=='cash-transactions-title'){
			nowBusitypeArr = ['18'];
			rows = cashtransactionsTable.getAllCheckedRows();
		//交易账户收划款查询
		}else if(id=='trade-account-title'){
			nowBusitypeArr = ['19'];
			rows = tradeAccountTable.getAllCheckedRows();
		//增值税交易查询
		}else if(id=='value-addedtaxs-title'){
			nowBusitypeArr = ['16'];
			rows = valueAddedtaxsTable.getAllCheckedRows();
		//债券重分类交易查询
		}else if(id=='value-reclass-title'){
			nowBusitypeArr = ['30'];
			rows = reclassTable.getAllCheckedRows();
		//债券借贷交易查询
        }else if(id=='bond-lend-title'){
            nowBusitypeArr = ['14'];
            rows = bondLendTable.getAllCheckedRows();
        //逾期罚息交易查询
        }else if(id=='overdue-penalty-title'){
            nowBusitypeArr = ['37'];
            rows = overduePenaltyTable.getAllCheckedRows();
        }
		
		
    	if(nowBusitypeArr){
    		var paMap = getSearchParams();
            //数组转字符串，塞入查询参数中
            paMap.filterBusiType = nowBusitypeArr.join(',');
            paMap.profitType = profitType ;
            if(rows&&rows.length>0){
                var datas = JSON.stringify(rows);
                paMap.expDatas = datas;
            }
            paMap.expId = id;
        	Joyin.exportExcel('export', paMap);
    	}

		
//        //债券交易
//        var rows1 = bondTable.getAllCheckedRows();
//        //计划类资产(收益型)查询
//        var rows2 = planProfitTable.getAllCheckedRows();
//        //计划类资产(净值型)查询
//        var rows3 = planNetvalueTable.getAllCheckedRows();
//        //计划类资产(货币性)查询
//        var rows4 = planCurrencyTable.getAllCheckedRows();
//        //存款类资产交易查询
//        var rows5 = depositloan.getAllCheckedRows();
//        //拆借交易查询
//        
//        var rows6 = lendTable.getAllCheckedRows();
//        //回购交易查询
//        var rows7 = repurchaseTable.getAllCheckedRows();
//        //基金类资产(货币型)查询 
//        var rows8 = fundCurrencyTable.getAllCheckedRows();
//        //基金类资产(净值型)查询
//        var rows9 = fundNetvalueTable.getAllCheckedRows();
//        //活期存款收息交易查询
//        var rows10 = currentDepositTable.getAllCheckedRows();
//        //现金类交易查询
//        var rows11 = cashtransactionsTable.getAllCheckedRows();
//        //交易账户收划款查询
//        var rows12 = tradeAccountTable.getAllCheckedRows();
//        //增值税交易查询
//        var rows13 = valueAddedtaxsTable.getAllCheckedRows();
        
//        var rows = rows1.length+rows2.length+rows3.length+rows4.length+rows5.length+rows6.length+rows7.length+rows8.length+rows9.length+rows10.length+rows11.length+rows12.length+rows13.length;
//        if(rows<=0){
//            layer.alert("请选择一条记录导出!");
//            return;
//        }
        //只能选择一个交易导出
//        if((rows1.length>0&&rows2.length<=0&&rows3.length<=0&&rows4.length<=0&&rows5.length<=0&&rows6.length<=0&&rows7.length<=0&&rows8.length<=0&&rows9.length<=0&&rows10.length<=0&&rows11.length<=0&&rows12.length<=0&&rows13.length<=0)||
//           (rows1.length<=0&&rows2.length>0&&rows3.length<=0&&rows4.length<=0&&rows5.length<=0&&rows6.length<=0&&rows7.length<=0&&rows8.length<=0&&rows9.length<=0&&rows10.length<=0&&rows11.length<=0&&rows12.length<=0&&rows13.length<=0)||
//           (rows1.length<=0&&rows2.length<=0&&rows3.length>0&&rows4.length<=0&&rows5.length<=0&&rows6.length<=0&&rows7.length<=0&&rows8.length<=0&&rows9.length<=0&&rows10.length<=0&&rows11.length<=0&&rows12.length<=0&&rows13.length<=0)||
//           (rows1.length<=0&&rows2.length<=0&&rows3.length<=0&&rows4.length>0&&rows5.length<=0&&rows6.length<=0&&rows7.length<=0&&rows8.length<=0&&rows9.length<=0&&rows10.length<=0&&rows11.length<=0&&rows12.length<=0&&rows13.length<=0)||
//           (rows1.length<=0&&rows2.length<=0&&rows3.length<=0&&rows4.length<=0&&rows5.length>0&&rows6.length<=0&&rows7.length<=0&&rows8.length<=0&&rows9.length<=0&&rows10.length<=0&&rows11.length<=0&&rows12.length<=0&&rows13.length<=0)||
//           (rows1.length<=0&&rows2.length<=0&&rows3.length<=0&&rows4.length<=0&&rows5.length<=0&&rows6.length>0&&rows7.length<=0&&rows8.length<=0&&rows9.length<=0&&rows10.length<=0&&rows11.length<=0&&rows12.length<=0&&rows13.length<=0)||
//           (rows1.length<=0&&rows2.length<=0&&rows3.length<=0&&rows4.length<=0&&rows5.length<=0&&rows6.length<=0&&rows7.length>0&&rows8.length<=0&&rows9.length<=0&&rows10.length<=0&&rows11.length<=0&&rows12.length<=0&&rows13.length<=0)||
//           (rows1.length<=0&&rows2.length<=0&&rows3.length<=0&&rows4.length<=0&&rows5.length<=0&&rows6.length<=0&&rows7.length<=0&&rows8.length>0&&rows9.length<=0&&rows10.length<=0&&rows11.length<=0&&rows12.length<=0&&rows13.length<=0)||
//           (rows1.length<=0&&rows2.length<=0&&rows3.length<=0&&rows4.length<=0&&rows5.length<=0&&rows6.length<=0&&rows7.length<=0&&rows8.length<=0&&rows9.length>0&&rows10.length<=0&&rows11.length<=0&&rows12.length<=0&&rows13.length<=0)||
//           (rows1.length<=0&&rows2.length<=0&&rows3.length<=0&&rows4.length<=0&&rows5.length<=0&&rows6.length<=0&&rows7.length<=0&&rows8.length<=0&&rows9.length<=0&&rows10.length>0&&rows11.length<=0&&rows12.length<=0&&rows13.length<=0)||
//           (rows1.length<=0&&rows2.length<=0&&rows3.length<=0&&rows4.length<=0&&rows5.length<=0&&rows6.length<=0&&rows7.length<=0&&rows8.length<=0&&rows9.length<=0&&rows10.length<=0&&rows11.length>0&&rows12.length<=0&&rows13.length<=0)||
//           (rows1.length<=0&&rows2.length<=0&&rows3.length<=0&&rows4.length<=0&&rows5.length<=0&&rows6.length<=0&&rows7.length<=0&&rows8.length<=0&&rows9.length<=0&&rows10.length<=0&&rows11.length<=0&&rows12.length>0&&rows13.length<=0)||
//           (rows1.length<=0&&rows2.length<=0&&rows3.length<=0&&rows4.length<=0&&rows5.length<=0&&rows6.length<=0&&rows7.length<=0&&rows8.length<=0&&rows9.length<=0&&rows10.length<=0&&rows11.length<=0&&rows12.length<=0&&rows13.length>0)
//        ){
//            if(rows1.length>0){
//                var datas1 = JSON.stringify(rows1);
//                Joyin.exportExcel('export', {datas1:datas1});
//            }else if(rows2.length>0){
//                var datas2 = JSON.stringify(rows2);
//                Joyin.exportExcel('export', {datas2:datas2});
//            }else if(rows3.length>0){
//                var datas3 = JSON.stringify(rows3);
//                Joyin.exportExcel('export', {datas3:datas3});
//            }else if(rows4.length>0){
//                var datas4 = JSON.stringify(rows4);
//                Joyin.exportExcel('export', {datas4:datas4});
//            }else if(rows5.length>0){
//                var datas5 = JSON.stringify(rows5);
//                Joyin.exportExcel('export', {datas5:datas5});
//            }else if(rows6.length>0){
//                var datas6 = JSON.stringify(rows6);
//                Joyin.exportExcel('export', {datas6:datas6});
//            }else if(rows7.length>0){
//                var datas7 = JSON.stringify(rows7);
//                Joyin.exportExcel('export', {datas7:datas7});
//            }else if(rows8.length>0){
//                var datas8 = JSON.stringify(rows8);
//                Joyin.exportExcel('export', {datas8:datas8});
//            }else if(rows9.length>0){
//                var datas9 = JSON.stringify(rows9);
//                Joyin.exportExcel('export', {datas9:datas9});
//            }else if(rows10.length>0){
//                var datas10 = JSON.stringify(rows10);
//                Joyin.exportExcel('export', {datas10:datas10});
//            }else if(rows11.length>0){
//                var datas11 = JSON.stringify(rows11);
//                Joyin.exportExcel('export', {datas11:datas11});
//            }else if(rows12.length>0){
//                var datas12 = JSON.stringify(rows12);
//                Joyin.exportExcel('export', {datas12:datas12});
//            }else if(rows13.length>0){
//                var datas13 = JSON.stringify(rows13);
//                Joyin.exportExcel('export', {datas13:datas13});
//            }
//        }else {
//            layer.alert("请选择一种交易导出!");
//            return;
//        }
    };
    
    // 绑定按钮事件
    $('#InvestTradeQuery001_btn_search').click(function() {
        //显示所有title Tab 容器
        $('.layui-tab').removeAttr('style');
        $('.layui-tab').find("li").hide();// 单个隐藏所有title
    	// 加载表格数据
    	allLoadTable();
    	//loadTable(index);
    	//隐藏导出按钮
//    	$('#InvestTradeQuery001_btn_export').hide();
    	var t = setInterval(function() {
    		if(index == $('.layui-tab-title > li').length) {
    			$('.layui-tab-title > li:visible:first').trigger('click');
    			clearInterval(t);
    			t = null;
    			index = 0;
    		}
    	}, 500);
    	
    });
    //活期存款收息交易（点击资金账户名）查看
    $("body").on("click", ".tradeStatus", function () {
        selectByAccountName($(this).data("tradeid"), PAGE_MODEL_REFERENCE);
    });
    //导出
//    $('#InvestTradeQuery001_btn_').click(function() {
//        exportExcel();
//    });
    // 选项卡点击事件
    layui.element.on('tab(tabContainer)', function(obj) {
    	var index = obj.index;
    	// 加载表格数据
    	loadTable(index);
    });
    $(function() {
       // 绑定导出事件
        $('#InvestTradeQuery001_btn_export').click(function() {
            exportExcel();
        });

    });
    
});