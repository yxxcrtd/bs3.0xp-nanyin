$(function() {
    
    /**
     * 导出表格
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
	 * 获取查询参数
	 */
	var getSearchParams = function() {

		return {
			currencyPair : $("#currencyPair").val(),			
			foreignExchangeType : $('#foreignExchangeType').val(),			
			startDate : $('#tradeDate').startDate(),
			endDate : $('#tradeDate').endDate()

		}
	};

	
	/**
	 * 定义数据表格
	 */
	var table = $("#dataTable").joyintable({
		ajax : {
			url : 'tableLoad',
			type : 'POST',
			data : function() {
				return getSearchParams();
			}
		},
			
		 cellMinWidth : 100,
//		 loadonce : true,
		cols : [ 
		         {
			field : '',
			width : 120,
			title : '货币对'
		}, {
			field : '',
			width : 120,
			title : '外汇类型'
		},
		{
			field : 'tradeDate',
			width : 120,
			title : '交易日期'
		}
		, {
			field : '',
			width : 120,
			title : '期限'
		},{
			field : '',
			width : 120,
			title : '到期日'
		},{
			field : '',
			width : 120,
			title : '开盘价(元)'
		}, {
			field : '',
			width : 120,
			title : '收盘价(元)'
		}, {
			field : '',
			width : 120,
			title : '最高价(元)'
		}, {
			field : '',
			width : 120,
			title : '最低价(元)'
		}, {
			field : '',
			width : 120,
			title : '数据来源'
		}
		]
	});

	
	/**
	 * 查询
	 */
	$('#ValExchangeRateInfo001_btn_search').click(function() {
		table.load();
	});
	 /**
     * 导出
     */
    $('#ValExchangeRateInfo001_btn_export').click(function() {
        exportExcel();
    });
});