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
			fundCode : $("#fundCode").val(),
			fundName : $('#fundName').val(),
			fundType : $('#fundType').val(),
			valueSource : $('#valueSource').val(),
			startDate : $('#valueDate').startDate(),
			endDate : $('#valueDate').endDate()

		}
	};

	
	/**
	 * 定义数据表格
	 */
	var table = $("#dataTable").joyintable({
		ajax : {
			url : 'findPage',
			type : 'POST',
			data : function() {
				return getSearchParams();
			}
		},
		
/*		dblclick:function(table , data) {
	          console.log(data);
	          Joyin.dialog(JoyinUtil.getLabel("查看详情"), basePath
	                  + "common/basicdata/marketdata/finproductvaluation/ValFinProductValuation002/init","90%",
	                  "90%", {pageModel:PAGE_MODEL_REFERENCE,datas:JSON.stringify(data)},
	                  function(){
	                      table.reload();
	                      pageModel : PAGE_MODEL_NEW
	                  },
	                  null
	          ); 
		},*/
		
	
		 cellMinWidth : 100,
//		 loadonce : true,
		cols : [ {
			field : 'finprodId',
			width : 120,
			title : '基金代码'
		}, {
			field : 'fundName',
			//width : 120,
			title : '基金名称'
		}, {
			field : 'fundTypeFmt',
			sortField:'fundType', 
			width : 120,
			title : '基金类型'
		}, 
//		{
//			field : 'valueSourceFmt',
//			sortField:'valueSource', 
//			width : 120,
//			title : '估值来源'
//		}, 
		{
			field : 'valueDate',
			width : 120,
			title : '估值日期'
		}, {
			field : 'closePrice',
			width : 100,
			type  : "money",
			title : '收盘价'
		}, {
			field : 'averagePrice',
			width : 100,
			type  : "money",
			title : '平均价'
		}, {
			field : 'maxPrice',
			width : 100,
			type  : "money",
			title : '最高价'
		}, {
			field : 'minPrice',
			width : 100,
			type  : "money",
			title : '最低价'
		}, {
			field : 'buyOnePrice',
			width : 100,
			type  : "money",
			title : '买一价'
		}, {
			field : 'sellOnePrice',
			width : 100,
			type  : "money",
			title : '卖一价'
		} ]
	});

	
	/**
	 * 画面加载完成
	 */
	
	/**
	 * 查询
	 */
	$('#ComFundMarketInfo001_btn_search').click(function() {
		table.load();
	});
	 /**
     * 导出
     */
    $('#ComFundMarketInfo001_btn_export').click(function() {
        exportExcel();
    });
    
    // 导入按钮点击事件
    $('#ComFundMarketInfo001_btn_import').click(function() {
        Joyin.confirm("请确认是否要导入数据？", function() {
            Joyin.uploadFile(function(fileName) {
                Joyin.ajax(basePath + 'common/basicdata/marketdata/fundmarketinfo/ComFundMarketInfo001/checkConfirmMsgForImportData', {fileName: fileName}, function() {
                    Joyin.ajax(basePath + 'common/basicdata/marketdata/fundmarketinfo/ComFundMarketInfo001/importFile', {fileName: fileName});
                });
            },function() {
                table.reload();
            });
        });
    });
});