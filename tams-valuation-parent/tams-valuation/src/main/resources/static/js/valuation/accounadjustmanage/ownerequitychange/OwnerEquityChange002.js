$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var condition = $.serializeToObject('#searchForm');
    	condition.tableId = tableId;
    	condition.reportTermType = reportTermType;
    	condition.reportTerm = reportTerm;
    	condition.reportDimen = reportDimen;
    	condition.booksetGroup = booksetGroup;
    	condition.lastDimen = lastDimen;
    	//画面元素显示控制
    	pageControl();
        return condition;
    };
    
	var AssetTable = $("#dataTable").joyintable({
	    ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        checkbox: false,
        searchOnInit : true,
        loadonce: true,
        page: false,
        cols: [
                {field:'booksetId',title:'账套',sortable: false,width:165},
                {field:'project',  title: '项目',sortable: false,width:345},
                {field:'tIncomeFund', type:"money",sortable: false, title: '（本期）实收资本',width:165}
               ,{field:'tUnQuarterProfit', type:"money",sortable: false, title: '（本期）未分配利润',width:165}
               ,{field:'tOtherCompIncome', type:"money",sortable: false, title: '（本期）其他综合收益',width:165}
               ,{field:'tOwnerEquityTot', type:"money",sortable: false, title: '（本期）所有者权益合计',width:165}
               ,{field:'yIncomeFund', type:"money",sortable: false, title: '（上期）实收资本',width:165}
               ,{field:'yUnQuarterProfit', type:"money",sortable: false, title: '（上期）未分配利润',width:165}
               ,{field:'yOtherCompIncome', type:"money",sortable: false, title: '（上期）其他综合收益',width:165}
               ,{field:'yOwnerEquityTot', type:"money",sortable: false, title: '（上期）所有者权益合计',width:165}
           ]
     });
	
	var pageControl = function() {
		// 不可编辑
		$("input").attr("disabled",true);
    	$("#OwnerEquityChange002_btn_search").hide();
    	$("#OwnerEquityChange002_btn_reset").hide();
    	$("#OwnerEquityChange002_btn_filter").hide();
    	$("#OwnerEquityChange002_btn_export").hide();
	}
	
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
     * 画面加载完成
     */
    $(function() {
    	$("#tIncomeFund").val(formatMoney($("#tIncomeFund").val()));
    	$("#tUnQuarterProfit").val(formatMoney($("#tUnQuarterProfit").val()));
    	$("#tOtherCompIncome").val(formatMoney($("#tOtherCompIncome").val()));
    	$("#tOwnerEquityTot").val(formatMoney($("#tOwnerEquityTot").val()));
    	$("#yIncomeFund").val(formatMoney($("#yIncomeFund").val()));
    	$("#yUnQuarterProfit").val(formatMoney($("#yUnQuarterProfit").val()));
    	$("#yOtherCompIncome").val(formatMoney($("#yOtherCompIncome").val()));
    	$("#yOwnerEquityTot").val(formatMoney($("#yOwnerEquityTot").val()));
    });
});



