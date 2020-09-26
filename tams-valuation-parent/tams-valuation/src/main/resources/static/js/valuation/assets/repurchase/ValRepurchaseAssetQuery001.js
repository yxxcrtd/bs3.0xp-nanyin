$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	repAsset: $.trim($('#repAsset').val()),  
        	finprodType: $.trim($('#finprodType').val()),         	      	
        	vdateBegin: $('#vDate').startDate(),
            vdateEnd: $('#vDate').endDate(),
            mdateBegin: $('#mDate').startDate(),
            mdateEnd: $('#mDate').endDate()
        }
    };
    
    /**
     * 定义数据表格
     */
	var table=$("#dataTable").joyintable({
	    ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
		checkbox: true,
		/*cellMinWidth : 90,*/
		dblclick:function(table, data) {//绑定双击事件
            Joyin.dialog(JoyinUtil.getLabel("查看回购类资产"),
                    JoyinUtil.getRealPath("/valuation/assets/repurchase/ValRepurchaseAssetQuery002/init"),
                    "90%", "90%", {pageModel:PAGE_MODEL_REFERENCE,
            	finprodId:data.finprodId,finprodType:data.finprodType},
                    function(){            	    
            	    });
        },
		cols: [
		   {field:'finprodId', title: '资产代码',width : 160, sort: true,fixed:true},
		   {field:'finprodTypeCode', sortField:'finprodType',title: '资产类型', width : 160, sort: true,fixed:true},
		   {field:'finprodType', title: '资产类型',width : 150, visible: false},
		   {field:'tradeMarketFmt', sortField:'tradeMarket', title: '交易场所',width : 150,sort: true},
           {field:'vdate1', title: '起息日', width : 110,sort: true},
		   {field:'mdate1', title: '到期日', width : 110,sort: true},
		   {field:'term', title: '回购期限', width : 110,sort: true},
           {field:'termTypeFmt',sortField:'termType', title: '期限品种', width : 150,sort: true},
           {field:'basis', title: '计息基础',width : 110, sortField:'basisCode',sort: true},
           {field:'intRate',width : 110, title: '固定利率(%)',type: 'money', sort: true},
           {field:'prin', title: '首次结算金额', type: 'money',width : 150, sort: true},
           {field:'mPrinAmt', title: '到期本金', type: 'money',width : 150, sort: true},
           {field:'mIntAmt', title: '到期利息', type: 'money',width : 150, sort: true},
           {field:'mTradeAmt',width : 150, title: '到期结算金额', type: 'money', sort: true}
	
		],
	});
	
	/**
     * 导出
     */
	var exportExcel = function() {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };
	
	/**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ValRepurchaseAssetQuery001_btn_search').click(function() {
            table.load();
        });
        $('#ValRepurchaseAssetQuery001_btn_export').click(function() {
           exportExcel();
       });
    });
	
});