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
		   {field:'booksetId',title:'账套',sortable: false},
		   {field:'itemName', title: '指标', sortable: false},
		   {field:'endingBalance', title: '期末余额', type:"money", width:400, sortable: false},
		   {field:'initialBalance', title: '年初余额', type:"money", width:400, sortable: false},
		]
	});
	
	var pageControl = function() {
		// 不可编辑
		$("input").attr("disabled",true);
    	$("#AssetLiabilityStatementQuery002_btn_search").hide();
    	$("#AssetLiabilityStatementQuery002_btn_reset").hide();
    	$("#AssetLiabilityStatementQuery002_btn_filter").hide();
    	$("#AssetLiabilityStatementQuery002_btn_export").hide();
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
    	$("#endingBalance").val(formatMoney($("#endingBalance").val()));
    	$("#initialBalance").val(formatMoney($("#initialBalance").val()));
    });
});



