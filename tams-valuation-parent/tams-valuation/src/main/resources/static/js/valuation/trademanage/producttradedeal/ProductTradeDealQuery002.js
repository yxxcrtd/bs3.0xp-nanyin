$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	finprodId: s_finprodId,
            settleDate: s_settleDate//确认日  
        }
    };
    
    /**
     * 定义数据表格
     * 普通产品-明细查询表
     */
	var commonDealTable=$("#commonDealTable").joyintable({
	    ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        checkbox: false,
		cellMinWidth : 100,
		cols: [
		   {field:'tradeId', title: '交易编码', sort: true},
           {field:'finprodId', title: '产品代码', sort: true},
		   {field:'finprodAbbr', title: '产品简称', sort: true},
		   {field:'psCode', title: '交易类型Code', sort: true, visible:false},
		   {field:'ps', title: '交易类型', sort: true},
           {field:'ccyCode', title: '交易币种Code',sort: true, visible:false},
           {field:'ccy', title: '交易币种',sort: true},
           {field:'shareAmt', type:'money', title: '交易份额', sort: true},
           {field:'tradeAmt', type:'money', title: '交易金额', sort: true},
           {field:'prinAmt', type:'money', title: '本金金额', sort: true},
           {field:'intAmt', type:'money',title: '红利/利息金额', sort: true},
           {field:'tradeDate', title: '本金支付日', sort: true},
           {field:'settleDate', title: '红利/利息支付日', sort: true}
		],
	});
    
    /**
     * 定义数据表格
     * 母产品-明细查询表
     */
	var parentDealTable=$("#parentDealTable").joyintable({
	    ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        checkbox: false,
		cellMinWidth : 100,
		cols: [
		   {field:'tradeId', title: '交易编码', sort: true},
           {field:'finprodId', title: '子产品代码', sort: true},
		   {field:'finprodAbbr', title: '子产品简称', sort: true},
		   {field:'psCode', title: '交易类型Code', sort: true, visible:false},
		   {field:'ps', title: '交易类型', sort: true},
           {field:'ccyCode', title: '交易币种Code',sort: true, visible:false},
           {field:'ccy', title: '交易币种',sort: true},
           {field:'shareAmt', type:'money', title: '交易份额', sort: true},
           {field:'tradeAmt', type:'money', title: '交易金额', sort: true},
           {field:'prinAmt', type:'money', title: '本金金额', sort: true},
           {field:'intAmt', type:'money',title: '红利/利息金额', sort: true},
           {field:'tradeDate', title: '本金支付日', sort: true},
           {field:'settleDate', title: '红利/利息支付日', sort: true}
		],
	});
	
	
	/**
     * 定义数据表格
     * 子产品-明细查询表
     */
	var childDealTable=$("#childDealTable").joyintable({
	    ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        checkbox: false,
		cellMinWidth : 100,
		cols: [
		   {field:'tradeId', title: '交易编码', sort: true},
		   {field:'psCode', title: '交易类型Code', sort: true, visible:false},
		   {field:'ps', title: '交易类型', sort: true},
           {field:'ccyCode', title: '交易币种Code',sort: true, visible:false},
           {field:'ccy', title: '交易币种',sort: true},
           {field:'shareAmt', type:"money", title: '交易份额', sort: true},
           {field:'tradeAmt', type:"money", title: '交易金额', sort: true},
           {field:'prinAmt', type:"money", title: '本金金额', sort: true},
           {field:'intAmt', type:"money", title: '红利/利息金额', sort: true},
           {field:'tradeDate', title: '本金支付日', sort: true},
           {field:'settleDate', title: '红利/利息支付日', sort: true},
           //{field:'', title: '备注', width:90, sort: true}
		],
	});

	var exportExcel = function() {
		var paramObj = getSearchParams();
		Joyin.exportExcel('export', paramObj);
	};
	
	
	/**
     * 画面加载完成
     */
    $(function() {
    		// s_hasChildren: 01:含有子节点；02:不含有子节点
    		// s_pFinprodId: 0:父节点；
    		if(s_hasChildren == '02'&&s_pFinprodId =='0'){ // 普通产品 (没子没母)
    			commonDealTable.load();
    			$("#commonDealTable-title").show();
    			$("#childDealTable-title").hide();
    			$("#parentDealTable-title").hide();
    			
    			$("#c_commonDealTable").show();
    			$("#c_childDealTable").hide();
    			$("#parentDealTable-title").hide();
    			$("#c_parentDealTable").hide();
    		} else if(s_hasChildren == '02') { // 子产品
    			childDealTable.load();
    			$("#commonDealTable-title").hide();
    			$("#childDealTable-title").show();
    			$("#parentDealTable-title").hide();
    			
    			$("#c_commonDealTable").hide();
    			$("#c_childDealTable").show();
    			$("#c_parentDealTable").hide();
    		} else{ // 母产品
    			parentDealTable.load();
    			$("#commonDealTable-title").hide();
    			$("#childDealTable-title").hide();
    			$("#parentDealTable-title").show();
    			
    			$("#c_commonDealTable").hide();
    			$("#c_parentDealTable").show();
    			$("#c_childDealTable").hide();
    		}
    		
    		 //  绑定导出事件
            $('#ProductTradeDealQuery002_btn_export').click(function() {
                exportExcel();
            });
    });
    var docH= $(document).width();
    $('.dealtable').css('width',docH-40);
	
});



