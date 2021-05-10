$(function() {

    /**
     * 获取查询参数
     */
    var getPageParams = function() {
    	var condition = $.serializeToObject('#searchForm')
        condition.reportTermType = reportTermType;
        condition.lastDimen = lastDimen;
        condition.reportTerm = reportTerm;
        condition.booksetGroup = booksetGroup;
    	return condition;
    };
    var table = $("#dataTable").joyintable({
        ajax : {
            url :'findPage',
            type : 'POST',
            data : function() {
                return getPageParams();
            }
        },
        checkbox : false,
        searchOnInit : true,
        loadonce : true,
        page : false,
        cols : [ {
            field : 'booksetId',
            sortable : false,
            title : '账套'
        },
          {
            field : 'project',
            sortable : false,
            title : '指标'
        }, {
            field : 'thisTermAmt',
            width : 400,
            type : "money",
            sortable : false,
            title : '本期/年金额'
        }, {
            field : 'lastTermAmt',
            width : 400,
            type : "money",
            sortable : false,
            title : '上期/年金额'
        }
        ]
    });
    
	var pageControl = function() {
		// 不可编辑
		$("input").attr("disabled",true);
    	$("#ValProfitNewQuery002_btn_search").hide();
    	$("#ValProfitNewQuery002_btn_reset").hide();
    	$("#ValProfitNewQuery002_btn_filter").hide();
    	$("#ValProfitNewQuery002_btn_export").hide();
	}
	
	/**
     * 金额格式化: 框架数据表格 render 后 
     */
    var formatMoney = function (s, n) {
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
    	//画面元素显示控制
    	pageControl();
    	$("#thisTermAmt").val(formatMoney($("#thisTermAmt").val()));
    	$("#lastTermAmt").val(formatMoney($("#lastTermAmt").val()));
    });
}());
