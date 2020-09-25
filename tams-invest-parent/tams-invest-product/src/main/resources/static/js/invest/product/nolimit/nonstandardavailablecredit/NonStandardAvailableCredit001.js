$(function() {
	/**
	 * 获取查询参数
	 */
  var getSearchParams = function() {
		return {

			curveDate : $('#curveDate').val()
		 
		}
	};

  var tabCols = [  
	  {
		field : 'cutotalAssets',
		title : '当前总资产',
		width : 150,
		sortable:false
	}, {
		field : 'cuNonStandardTotalAssets',
		title : '当前非标总资产',
		width : 150,
		sortable:false
	}, {
		field : 'cuNonStandardTotalLimit',
		title : '当前非标总可用限额(按总量)',
		width : 150,
		sortable:false
	}, {
		field : 'totalPlannedAssets',
		title : '计划资产总量',
		width : 150,
		sortable:false
	}, {
		field : 'planNonStandTotalLimit',
		title : '当前非标总可用限额(按总比例)',
		width : 170,
		sortable:false
	},	{
		field : 'curveDate',	 
		title : '查询日',
		width : 150,
		type : 'date',
		sortable:false
    },
	/*
	 * {field:'operation', title: 'SysCommon.operation', type:'action', buttons:
	 * ['edit', 'delt'],width:120}
	 */
	];

	/**
	 * 定义数据表格
	 */
  var table = $("#dataTable").joyintable({
		ajax : {
			url : 'selectForPage',
			type : 'POST',
			data : function() {
				return getSearchParams();
			}
		},
		cols : tabCols,
	});

	/**
	 * check查询条件和计划类型
	 */

	  
	     
  var checkAndLoadTable = function() {
		 
    	var object = { curveDate : $('#curveDate').val()};

     
     
		Joyin
			.ajaxJson(
					basePath
							+ 'invest/product/nolimit/nonstandardavailablecredit/NonStandardAvailableCredit001/checkDate',
								object,
						function(result) {
							 if(result.message == null ){
							table.load();
							 }
							/* else 
								 table.clear();*/
						});
		

	};

	/**
	 * 导出
	 */
  var exportExcel = function() {
		Joyin.exportExcel('export', getSearchParams());
	};

	/**
	 * 画面加载完成
	 */
  $(function() {
		// 绑定按钮事件
		$('#NonStandardAvailableCredit001_btn_search').click(function() {
			checkAndLoadTable();
			

		});

		// 绑定导出事件
		$('#NonStandardAvailableCredit001_btn_export').click(function() {
			exportExcel();
		});

	});

});
