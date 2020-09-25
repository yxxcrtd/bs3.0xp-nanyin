(function() {

	/**
	 * 获取试算参数
	 */
	var getCalcParams = function() {
		var param = $.serializeToObject('#searchForm');
		if (param.prodcode) {
			param.prodcode = param.prodcode.join(",");
		}
		param.vldateStart = $('#FinProductValuation001_vldate').startDate();
		param.vldateEnd = $('#FinProductValuation001_vldate').endDate();
		return param;
	};

	/**
	 * 定义数据表格
	 */
	var dataTable = $("#dataTable").joyintable({
		ajax : {
			url : 'selectForPage',
			type : 'POST',
			data : function() {
				return getCalcParams();
			}
		},
		checkbox : false,
		rowno : true,
		cols : [ {
			field : 'prodcode',
			title : 'FinProductValuation001.prodcode',
			width : 130
		}, {
			field : 'prod4short',
			title : 'FinProductValuation001.table.prod4short',
			width : 150
		}, {
			field : 'profitTypeStr',
			title : 'FinProductValuation001.table.profitType',
			width : 100
		}, {
			field : 'vldate',
			title : 'FinProductValuation001.vldate',
			type : 'date'
		}, {
			field : 'prodNetAsset',
			title : 'FinProductValuation001.table.prodNetAsset',
			width : 160,
			type : 'number'
		}, {
			field : 'prodTotQty',
			title : 'FinProductValuation001.table.prodTotQty',
			width : 160,
			type : 'number'
		}, {
			field : 'unpayProfit',
			title : 'FinProductValuation001.table.unpayProfit',
			width : 100,
			type : 'number'
		}, {
			field : 'yield',
			title : 'FinProductValuation001.table.yield',
			width : 100,
			type : 'number',
			digit : 4
		}, {
			field : 'totYield',
			title : 'FinProductValuation001.table.totYield',
			width : 110,
			type : 'number',
			digit : 4
		}, {
			field : 'netValue',
			title : 'FinProductValuation001.table.netValue',
			width : 100,
			type : 'number',
			digit : 4
		}, {
			field : 'totNetValue',
			title : 'FinProductValuation001.table.totNetValue',
			width : 120,
			type : 'number',
			digit : 4
		}, {
			field : 'curYield',
			title : 'FinProductValuation001.table.curYield',
			width : 130,
			type : 'number',
			digit : 4
		}, {
			field : 'totCurYield',
			title : 'FinProductValuation001.table.totCurYield',
			width : 120,
			type : 'number',
			digit : 4
		}, {
			field : 'yield7',
			title : 'FinProductValuation001.table.yield7',
			width : 120,
			type : 'number',
			digit : 4
		}, {
			field : 'operation',
			title : 'SysCommon.operation',
			type : 'action',
			buttons : [ {
				text : '明细',
				clas : 'detail',
				title : '明细'
			} ]
		}, {
			field : 'valueid',
			visible : false
		}, {
			field : 'vldateStr',
			visible : false
		}, {
			field : 'ccy',
			visible : false
		}, {
			field : 'profitType',
			visible : false
		} ],

		// 行双击
		dblclick : function(table, record) {
			fncView(record);
		},
	});

	/**
	 * 绑定明细查看
	 */
	dataTable.bind('click', '.detail', function(record, index) {
		fncView(record);
	});

	// 查看
	function fncView(record) {
		record.pageModel = PAGE_MODEL_REFERENCE;
		window.param = {
			pvalueid : record.valueid,
			prodcode : record.prodcode,
			vldateStr : record.vldateStr,
			ccy : record.ccy,
			profitType : record.profitType
		};
		Joyin.dialog(JoyinUtil.getLabel("FinProductValuation002.title"), JoyinUtil.getRealPath("invest/product/valuation/FinProductValuation002/init"), "98%", "95%", record, function() {
			// table.reload();
		});
	}

	// 加载表格数据
	var loadTableData = function() {
		dataTable.table ? dataTable.reload() : dataTable.load();
	}

	// 计算估值
	var calcValue = function() {
		var parmas = getCalcParams();
		Joyin.ajaxJson(basePath + 'invest/product/valuation/FinProductValuation001/calcValue', parmas, function(result) {
			loadTableData();
		});
	}

	$(function() {
		// 初始化
		$("#FinProductValuation001_btn_search span").html("&nbsp;&nbsp;试&nbsp;算");
		$("#FinProductValuation001_btn_search i").removeClass("icon-search");
		$("#FinProductValuation001_btn_search i").addClass("icon-calculator");
		$("#FinProductValuation001_btn_search").click(function() {
			calcValue();
		});
	});
}());