$(function () {	
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	tradeId: s_tradeId, //交易编号
        	busiType: s_busiType, //交易方向
        	feeType: s_feeType//费用类型
        }
    };

    
    /**
     * 定义数据表格
     * 债券交易-结算指令
     */
	var bondTable=$("#bondTable").joyintable({
	    ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            	
            }
        },
        dblclick:function(table, data) {// 绑定双击事件，
            Joyin.dialog(JoyinUtil.getLabel("查看结算指令"),
                    basePath + "valuation/trademanage/investtradequery/ValOrdersQuery003/init",
                    "98%", "95%", {pageModel:PAGE_MODEL_REFERENCE,
            	orderId:data.orderId, busiType: data.busiTypeCode},function(){bondTable.reload();});
        },
        checkbox: false,
		cellMinWidth : 100,
		cols: [
		   {field:'orderId', title: '指令编码', sort: true},
		   {field:'busiTypeCode', title: '业务类型Code',width:150, sort: true, visible:false},
		   {field:'prodId', title: '投资主体代码', sort: true},
		   {field:'prodAbbr', title: '投资主体名称', sort: true},
		   {field:'assetId', title: '债券代码', sort: true},
           {field:'assetAbbr', title: '债券简称', sort: true},
		   {field:'orderTypeCode', title: '指令类型Code',sort: true , visible:false},
		   {field:'orderType', title: '指令类型',sort: true},
		   {field:'settleDate', title: '结算日期', sort: true},
		   {field:'settleAmt', type:"money", title: '结算金额',sort: true},
		   {field:'settleStatusCode', title: '结算状态Code', sort: true , visible:false},
		   {field:'settleStatus', title: '结算状态', sort: true}
		]
	});
	
	/**
     * 定义数据表格
     * 回购交易-结算指令
     */
	var repurchaseTable=$("#repurchaseTable").joyintable({
	    ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            	
            }
        },
        dblclick:function(table, data) {// 绑定双击事件，
            Joyin.dialog(JoyinUtil.getLabel("查看结算指令"),
                    basePath + "valuation/trademanage/investtradequery/ValOrdersQuery003/init",
                    "98%", "95%", {pageModel:PAGE_MODEL_REFERENCE,
            	orderId:data.orderId, busiType: data.busiTypeCode},function(){repurchaseTable.reload();});
        },
        checkbox: false,
		cellMinWidth : 100,
		cols: [
		   {field:'orderId', title: '指令编码', sort: true},
		   {field:'busiTypeCode', title: '业务类型Code',width:150, sort: true, visible:false},
		   {field:'prodId', title: '投资主体代码', sort: true},
		   {field:'prodAbbr', title: '投资主体名称', sort: true},
		   {field:'orderTypeCode', title: '指令类型-指令类型Code',sort: true , visible:false},
		   {field:'orderType', title: '指令类型',sort: true},
		   {field:'settleDate', title: '结算日期', sort: true},
		   {field:'settleAmt', title: '结算金额', type:"money",sort: true},
		   {field:'settleStatusCode', title: '交易状态Code', sort: true , visible:false},
		   {field:'settleStatus', title: '交易状态', sort: true}
		]
	});
	
	/**
     * 定义数据表格
     * 拆借交易-结算指令
     */
	var lendTable=$("#lendTable").joyintable({
	    ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            	
            }
        },
        dblclick:function(table, data) {// 绑定双击事件，
            Joyin.dialog(JoyinUtil.getLabel("查看结算指令"),
                    basePath + "valuation/trademanage/investtradequery/ValOrdersQuery003/init",
                    "98%", "95%", {pageModel:PAGE_MODEL_REFERENCE,
            	orderId:data.orderId, busiType: data.busiTypeCode},function(){lendTable.reload();});
        },
        checkbox: false,
		cellMinWidth : 100,
		cols: [
		   {field:'orderId', title: '指令编码', sort: true},
		   {field:'busiTypeCode', title: '业务类型Code',width:150, sort: true, visible:false},
		   {field:'prodId', title: '投资主体代码', sort: true},
		   {field:'prodAbbr', title: '投资主体名称', sort: true},
		   {field:'orderTypeCode', title: '拆借方向-指令类型Code',sort: true , visible:false},
		   {field:'orderType', title: '指令类型',sort: true},
		   {field:'settleDate', title: '结算日期', sort: true},
		   {field:'settleAmt', title: '结算金额', type:"money",sort: true},
		   {field:'settleStatusCode', title: '结算状态Code', sort: true , visible:false},
		   {field:'settleStatus', title: '结算状态', sort: true}
		]
	});

	/**
     * 定义数据表格
     * 基金类交易-结算指令
     */
    var fundTable=$("#fundTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
                
            }
        },
        dblclick:function(table, data) {// 绑定双击事件，
            Joyin.dialog(JoyinUtil.getLabel("查看结算指令"),
                    basePath + "valuation/trademanage/investtradequery/ValOrdersQuery003/init",
                    "98%", "95%", {pageModel:PAGE_MODEL_REFERENCE,
                orderId:data.orderId, busiType: data.busiTypeCode},function(){fundTable.reload();});
        },
        checkbox: false,
        cellMinWidth : 100,
        cols: [
           {field:'orderId', title: '指令编码', sort: true},
           {field:'busiTypeCode', title: '业务类型Code',width:150, sort: true, visible:false},
           {field:'prodId', title: '投资主体代码', sort: true},
           {field:'prodAbbr', title: '投资主体名称', sort: true},
           {field:'assetId', title: '资产代码', sort: true},
           {field:'assetAbbr', title: '资产名称', sort: true},
           {field:'orderTypeCode', title: '指令类型Code',sort: true , visible:false},
           {field:'orderType', title: '指令类型',sort: true},
           {field:'settleDate', title: '结算日期', sort: true},
           {field:'tdFeeAmt', title: '通道费',type:"money", sort: true},
           {field:'settleAmt', title: '结算金额', type:"money",sort: true},
           {field:'settleStatusCode', title: '结算状态Code', sort: true , visible:false},
           {field:'settleStatus', title: '结算状态', sort: true}
        ]
    });
    
    
    /**
     * 定义数据表格
     * 活期存款收息-结算指令
     */
    var currentDepositTable=$("#currentDepositTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
                
            }
        },
        dblclick:function(table, data) {// 绑定双击事件，
            Joyin.dialog(JoyinUtil.getLabel("查看结算指令"),
                    basePath + "valuation/trademanage/investtradequery/ValOrdersQuery003/init",
                    "98%", "95%", {pageModel:PAGE_MODEL_REFERENCE,
                orderId:data.orderId, busiType: data.busiTypeCode},function(){fundTable.reload();});
        },
        checkbox: false,
        cellMinWidth : 100,
        cols: [
           {field:'orderId', title: '指令编码', sort: true},
           {field:'busiTypeCode', title: '业务类型Code',width:150, sort: true, visible:false},
           {field:'prodId', title: '投资主体代码', sort: true},
           {field:'prodAbbr', title: '投资主体名称', sort: true},
           {field:'assetId', title: '资产代码', sort: true},
           {field:'assetAbbr', title: '资产名称', sort: true},
           {field:'orderTypeCode', title: '指令类型Code',sort: true , visible:false},
           {field:'orderType', title: '指令类型',sort: true},
           {field:'settleDate', title: '结算日期', sort: true},
           {field:'tdFeeAmt', title: '通道费',type:"money", sort: true},
           {field:'settleAmt', title: '结算金额',type:"money",sort: true},
           {field:'settleStatusCode', title: '结算状态Code', sort: true , visible:false},
           {field:'settleStatus', title: '结算状态', sort: true}
        ]
    });
    
    /**
     * 定义数据表格
     * 产品费用/增值税交易-结算指令
     */
    var feeTable=$("#feeTable").joyintable({
        ajax: {
            url: 'findFeePage',
            type: 'POST',
            data: function() {
                return getSearchParams();
                
            }
        },
        checkbox: false,
        cellMinWidth : 100,
        cols: [
           {field:'orderId', title: '指令编码', sort: true},
           {field:'busiTypeCode', title: '业务类型Code',width:150, sort: true, visible:false},
           {field:'prodId', title: '投资主体代码', sort: true},
           {field:'prodAbbr', title: '投资主体名称', sort: true},
           {field:'assetId', title: '资产代码', sort: true},
           {field:'feeType', title: '资产名称', sort: true},
           {field:'orderTypeCode', title: '指令类型Code',sort: true , visible:false},
           {field:'orderType', title: '指令类型',sort: true},
           {field:'settleDate', title: '结算日期', sort: true},
           {field:'tdFeeAmt', title: '通道费',type:"money", sort: true},
           {field:'settleAmt', title: '结算金额',type:"money",sort: true},
           {field:'settleStatusCode', title: '结算状态Code', sort: true , visible:false},
           {field:'settleStatus', title: '结算状态', sort: true}
        ]
    });
    
    
    /**
     * 定义数据表格
     * 存借款交易-结算指令
     */
    var depositTable=$("#depositTable").joyintable({
        ajax: {
            url: 'findDepPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
                
            }
        },
        dblclick:function(table, data) {// 绑定双击事件，
            Joyin.dialog(JoyinUtil.getLabel("查看结算指令"),
                    basePath + "valuation/trademanage/investtradequery/ValOrdersQuery003/init",
                    "98%", "95%", {pageModel:PAGE_MODEL_REFERENCE,
                orderId:data.orderId, busiType: data.busiTypeCode,dealType:03},function(){depositTable.reload();});
        },
        checkbox: false,
       // cellMinWidth : 160,
        cols: [
           {field:'orderId', title: '指令编号',width:150, sort: true},
           {field:'busiTypeCode', title: '业务类型Code',width:150, sort: true, visible:false},
           {field:'prodId', title: '投资主体代码',width:150, sort: true},
           {field:'prodAbbr', title: '投资主体名称',width:150, sort: true},
           {field:'assetId', title: '资产代码',width:180, sort: true},
           {field:'assetAbbr', title: '资产简称',width:200, sort: true},
           {field:'orderTypeCode', title: '指令类型Code',width:150,sort: true , visible:false},
           {field:'orderType', title: '指令类型',width:290,sort: true},
           {field:'settleDate', title: '结算日期',width:110, sort: true},
           {field:'tdFeeAmt',  title: '通道费',type:"money",width:150,sort: true},
           {field:'settleAmt', type:"money", title: '结算金额',width:150,sort: true},
           {field:'settleStatusCode', title: '结算状态Code',width:150, sort: true , visible:false},
           {field:'settleStatus', title: '结算状态',width:150, sort: true}
        ]
    });
    
    /**
     * 定义数据表格
     * 计划类资产-结算指令
     */
    var planTable=$("#planTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
                
            }
        },
        dblclick:function(table, data) {// 绑定双击事件，
            Joyin.dialog(JoyinUtil.getLabel("查看结算指令"),
                    basePath + "valuation/trademanage/investtradequery/ValOrdersQuery003/init",
                    "98%", "95%", {pageModel:PAGE_MODEL_REFERENCE,
                orderId:data.orderId, busiType: data.busiTypeCode},function(){planTable.reload();});
        },
        checkbox: false,
       // cellMinWidth : 160,
        cols: [
           {field:'orderId', title: '指令编号',width:150, sort: true},
           {field:'busiTypeCode', title: '业务类型Code',width:150, sort: true, visible:false},
           {field:'prodId', title: '投资主体代码',width:150, sort: true},
           {field:'prodAbbr', title: '投资主体名称',width:150, sort: true},
           {field:'assetId', title: '资产代码',width:180, sort: true},
           {field:'assetAbbr', title: '资产名称',width:200, sort: true},
           {field:'orderTypeCode', title: '指令类型Code',width:150,sort: true , visible:false},
           {field:'orderType', title: '指令类型',width:290,sort: true},
           {field:'settleDate', title: '结算日期',width:110, sort: true},
           {field:'tdFeeAmt',  title: '通道费',type:"money",width:150,sort: true},
           {field:'settleAmt', type:"money", title: '结算金额',width:150,sort: true},
           {field:'settleStatusCode', title: '结算状态Code',width:150, sort: true , visible:false},
           {field:'settleStatus', title: '结算状态',width:150, sort: true}
        ]
    });
    
    
    
    /**
     * 定义数据表格
     * 现金类交易-结算指令
     */
    var cashtransactionsTable=$("#cashtransactionsTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        dblclick:function(table, data) {// 绑定双击事件，
            Joyin.dialog(JoyinUtil.getLabel("查看结算指令"),
                    basePath + "valuation/trademanage/investtradequery/ValOrdersQuery003/init",
                    "98%", "95%", {pageModel:PAGE_MODEL_REFERENCE,
                orderId:data.orderId, busiType: data.busiTypeCode},function(){cashtransactionsTable.reload();});
        },
        checkbox: false,
       // cellMinWidth : 160,
        cols: [
    		   {field:'orderId', title: '指令编码', sort: true},
    		   //{field:'busiTypeCode', title: '业务类型Code',width:150, sort: true, visible:false},
    		   {field:'prodId', title: '资产主体代码', sort: true},
    		   {field:'prodAbbr', title: '资产主体名称', sort: true},   		   
    		   {field:'orderType', title: '指令类型',sort: true},
    		   {field:'settleDate', title: '结算日期', sort: true},
    		   {field:'settleAmt', title: '结算金额',type:"money",sort: true},
    		   {field:'settleStatusCode', title: '结算状态Code', sort: true , visible:false},
    		   {field:'settleStatus', title: '结算状态', sort: true}
    		]
    });
    
    /**
     * 定义数据表格
     * 债券借贷交易-结算指令
     */
    var bondLendTable=$("#bondLendTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
                
            }
        },
        dblclick:function(table, data) {// 绑定双击事件，
            Joyin.dialog(JoyinUtil.getLabel("查看结算指令"),
                    basePath + "valuation/trademanage/investtradequery/ValOrdersQuery003/init",
                    "98%", "95%", {pageModel:PAGE_MODEL_REFERENCE,
                orderId:data.orderId, busiType: data.busiTypeCode},function(){bondLendTable.reload();});
        },
        checkbox: false,
        cellMinWidth : 100,
        cols: [
           {field:'orderId', title: '指令编码', sort: true},
           {field:'busiTypeCode', title: '业务类型Code',width:150, sort: true, visible:false},
           {field:'prodId', title: '投资主体代码', sort: true},
           {field:'prodAbbr', title: '投资主体名称', sort: true},
           {field:'orderTypeCode', title: '指令类型-指令类型Code',sort: true , visible:false},
           {field:'orderType', title: '指令类型',sort: true},
           {field:'settleDate', title: '结算日期', sort: true},
           {field:'settleAmt', title: '结算金额', type:"money",sort: true},
           {field:'settleStatusCode', title: '交易状态Code', sort: true , visible:false},
           {field:'settleStatus', title: '交易状态', sort: true}
        ]
    });
    
    
	/**
     * 画面加载完成
     */
    $(function() {
    	var busiType1 = ['02','20'];// 过滤业务类型 - 债券交易,交易所债券交易
    	var busiType2 = ['04', '05'];// 债券质押式回购交易 和 债券买断式回购交易
    	var busiType3 = ['01'];
    	var busiType4 = ['06','07'];//过滤业务类型 - 基金类交易
        var busiType5 = ['03'];//过滤业务类型 - 存款类资产
        var busiType6 = ['15'];//过滤业务类型 - 活期存款收息交易,费用交易
        var busiType7 = ['19'];//过滤业务类型 - 交易账户收划款交易
        var busiType8 = ['09'];//过滤业务类型 - 计划类资产      
    	var busiType9 = ['16'];// 过滤业务类型 - 费用交易,增值税交易
        var busiType10 = ['18'];//现金类交易
        var busiType11 = ['14'];//债券借贷交易


        
    	if(busiType1.indexOf(s_busiType) >= 0){//  债券交易,交易所债券交易
    		bondTable.load();
    		$("#c_bondTable").show();//表格容器显示
    	}else if(busiType2.indexOf(s_busiType) >= 0){// 债券质押式回购交易 和 债券买断式回购交易  
    		repurchaseTable.load();
    		$("#c_repurchaseTable").show();//表格容器显示
    	}else if(busiType4.indexOf(s_busiType) >= 0){// 基金类交易  
    	    fundTable.load();
            $("#c_fundTable").show();//表格容器显示
        }else if(busiType6.indexOf(s_busiType) >= 0){// 活期存款收息交易
        	currentDepositTable.load();
            $("#c_currentDepositTable").show();//表格容器显示
        }else if(busiType7.indexOf(s_busiType) >= 0){// 交易账户收划款交易
            fundTable.load();
            $("#c_fundTable").show();//表格容器显示
        }if(busiType3.indexOf(s_busiType) >= 0){// 拆借交易  
    		lendTable.load();
    		$("#c_lendTable").show();//表格容器显示
    	}if(busiType5.indexOf(s_busiType) >= 0){//存款类资产
    	    depositTable.load();
            $("#c_depositTable").show();//表格容器显示
        }if(busiType8.indexOf(s_busiType) >= 0){//计划类资产
            planTable.load();
            $("#c_planTable").show();//表格容器显示
        }if(busiType9.indexOf(s_busiType) >= 0){//费用交易
        	feeTable.load();
        	$("#c_feeTable").show();
        }if(busiType10.indexOf(s_busiType) >= 0){//现金类交易
        	cashtransactionsTable.load();
            $("#c_cashtransactionsTable").show();//表格容器显示
        }if(busiType11.indexOf(s_busiType) >= 0){//债券借贷交易
            bondLendTable.load();
            $("#c_bondLendTable").show();//表格容器显示
        }
        
    });
	
});



