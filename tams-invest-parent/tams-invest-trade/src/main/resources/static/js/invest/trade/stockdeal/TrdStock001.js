(function() {
	/**
	 * 获取查询参数
	 */
	var getPageParams = function() {
		var pageParam = $.serializeToObject('#searchForm');
		if ($("#portfolioId").val()) {
			pageParam.portFolioIdList = $("#portfolioId").val().join(",");
		}
		if ($('#ps').val()) {
			pageParam.psList = $('#ps').val().join(",");
		}
		if ($('#tradeMarket').val()) {
			pageParam.tradeMarketList = $('#tradeMarket').val().join(",");
		}
		if ($('#dealMode').val()) {
			pageParam.dealModeList = $('#dealMode').val().join(",");
		}
		if ($('#board').val()) {
			pageParam.boardList = $('#board').val().join(",");
		}
		if ($('#tradeDate').startDate()) {
			pageParam.tradeDateBegin = $('#tradeDate').startDate();
		}
		if ($('#tradeDate').endDate()) {
			pageParam.tradeDateEnd = $('#tradeDate').endDate();
		}
		return pageParam;
	};

	/**
	 * 定义数据表格
	 */
	var table = $("#dataTable").joyintable({
		ajax : {
			url : 'findPage',
			type : 'POST',
			data : function() {
				return getPageParams();
			}
		},
		dblclick : function(table, data) {
			data.pageModel = PAGE_MODEL_REFERENCE;
			Joyin.dialog(JoyinUtil.getLabel("TrdStock002.reference"),
					basePath +"invest/trade/stockdeal/TrdStock002/init",
							"98%", "98%", data);
		},
		cols : [
				{field : 'validStatusText',sortField : 'VALID_STATUS',width : 100,title : 'TrdStock001.validStatus'}
				,{field : 'tradeId',sortField : 'TRADE_ID',width : 170,title : 'TrdStock001.tradeId'}
				,{field : 'entrustId',sortField : 'ENTRUST_ID',width : 180,title : 'TrdStock001.entrustId'}
				,{field : 'portfolioId',sortField : 'PORTFOLIO_ID',width : 140,title : 'TrdStock001.portfolioId',
					render : function(data, type, row, meta) {
						return "<a class='portfolioId-link' style='cursor: pointer;'>"
								+ row.portfolioId + "</a>";
					}
				}
				,{field : 'portfolioName',sortField : 'PORTFOLIO_NAME',width : 140,title : 'TrdStock001.portfolioName'}
				,{field : 'finprodAbbr',sortField : 'FINPROD_ABBR',width : 140,title : 'TrdStock001.finprodAbbr'}
				,{field : 'finprodId',sortField : 'finprod_id',width : 140,title : 'TrdStock001.finprodId',
					render : function(data, type, row, meta) {
						return "<a class='finprodId-link' style='cursor: pointer;'>"
								+ row.finprodId + "</a>";
					}
				}
				,{field : 'psStr',sortField : 'ps',width : 100,title : 'TrdStock001.ps'}
				,{field : 'tradeMarketStr',sortField : 'trade_market',width : 120,title : 'TrdStock001.tradeMarket'}
				,{field : 'dealModeStr',sortField : 'DEAL_MODE',width : 120,title : 'TrdStock001.dealMode'}
				,{field : 'boardStr',sortField : 'BOARD',width : 120,title : 'TrdStock001.board'}
				,{field : 'unitCprice',sortField : 'UNIT_CPRICE',width : 150,title : 'TrdStock001.unitCprice',type : 'money'}
				,{field : 'tradeAmt',sortField : 'TRADE_AMT',width : 150,title : 'TrdStock001.tradeAmt'}
				,{field : 'tradeDate',sortField : 'TRADE_DATE',width : 100,title : 'TrdStock001.tradeDate',type : 'date'}
				,{field : 'shareAmt',sortField : 'SHARE_AMT',width : 120,title : 'TrdStock001.shareAmt'}
				,{field : 'ccyStr',sortField : 'CCY',width : 130,title : 'TrdStock001.ccy'}
				,{field : 'remark',sortField : 'REMARK',width : 150,title : 'TrdStock001.remark'}
				,{field : 'validStatus',visible : false}
				,{field : 'ps',visible : false}
				,{field : 'tradeMarket',visible : false}
				,{field : 'tradeMarketMode',visible : false}
				,{field : 'tradePlat',visible : false}
				,{field : 'workflowId', visible:false}
				,{field : 'ccy',visible : false} 
				]
			});

	var exportExcel = function() {
		Joyin.exportExcel('export', getPageParams());
	};

	/**
	 * 画面联动处理 上市板
	 */
	$('#board').on("change", function() {
		var dealMode = $('#dealMode').val();
		var board = $("#board").val();
		if (board != BOARD_BD03 && dealMode == DEAL_MODE_03) {
			$("#board").val('');
			Joyin.error("交易方式为盘后定价交易，上市板只能选择科创板");
			return false;
		}
	});

	/**
	 * 画面联动处理 交易方式
	 */
	$('#dealMode').on("change", function() {
		var dealMode = $('#dealMode').val();
		if (dealMode) {
			var board = $("#board").val();
			if (board != BOARD_BD03 && dealMode == DEAL_MODE_03) {
				$('#dealMode').val('');
				Joyin.error("只有上市板为科创板的才可选择盘后定价交易");
				return false;
			}
		}
	});

	/**
	 * 绑定编辑事件
	 */
	table.bind('click','.edit',function(data, index) {
		data.operateType = "edit";
		data.pageModel = PAGE_MODEL_EDIT;
		Joyin.ajaxJson(basePath+'invest/trade/stockdeal/TrdStock002/check',data,function(result) {
			Joyin.dialog(JoyinUtil.getLabel("TrdStock002.edit"),
					basePath+ "invest/trade/stockdeal/TrdStock002/init",
					"99%","99%",{pageModel : PAGE_MODEL_EDIT,
								tradeId : data.tradeId}, function() {
									table.reload();
			});
		});
	});
	/**
	 * 绑定删除事件
	 */
	table.bind('click','.delt',function(data, index) {
		var object = {selectedItem : [ data ],operateType : "delete"};
		Joyin.ajaxJson(basePath+ 'invest/trade/stockdeal/TrdStock001/check',object,function(result) {
			Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")),function() {
				WorkFlow.doDeleteList(basePath+ 'invest/trade/stockdeal/TrdStock001/delete',object,"TrdStock001_btn_revoke","TrdStock001",function() {
							table.reload();
				});
			});
		});
	});

	/**
	 * 点击组合代码链接跳转到该组合信息查看页面
	 */
	table.bind('click','.portfolioId-link',function(row) {
		Joyin.dialog(JoyinUtil.getLabel("PtlManagementInfo002"),
				basePath+ "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
				"95%", "90%", {
				pageModel : PAGE_MODEL_REFERENCE,
				portfolioId : row.portfolioId,
				portfolioName : row.portfolioName});
	});
	/**
	 * 点击资产代码链接跳转到该资产标的信息查看页面；
	 */
	table.bind('click', '.finprodId-link', function(row) {
		Joyin.dialog(JoyinUtil.getLabel("FinStockInfo002.title"),
				basePath+ "invest/asset/stock/FinStockInfo002/init",
				"95%", "90%",{
					pageModel : PAGE_MODEL_REFERENCE,
					finprodId : row.finprodId,
				/* finprodName : row.finprodName */
				});
	});

	$(function() {
		// 绑定按钮事件
		$('#TrdStock001_btn_search').click(function() {
			table.load();
			$('#TrdStock001_btn_revoke').show();
		});

		$('#TrdStock001_btn_export').click(function() {
			exportExcel();
		});
		$('#TrdStock001_btn_add').click(function() {
			Joyin.dialog(JoyinUtil.getLabel("TrdStock002"),
							basePath+ "invest/trade/stockdeal/TrdStock002/init",
							"99%", "99%", {
								pageModel : PAGE_MODEL_NEW
							}, function() {
								table.reload();
			});
		});
		/**
		 * 撤销
		 */
		$('#TrdStock001_btn_revoke').click(function() {
			if (table != null && table != '') {
				var selArr = table.getAllCheckedRows();
			}
			// 列表中未勾选
			if (!selArr || selArr.length == 0) {
				Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
				return;
			}
			// 列表中勾选对象1条以上时
			if (selArr.length > 1) {
				Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
				return;
			}
			var checkObj = {};
			checkObj.tradeId = selArr[0].tradeId;
			checkObj.finprodName = selArr[0].finprodName;
			checkObj.tradeDate = selArr[0].tradeDate;
			checkObj.operateType = "revoke";
			checkObj.ps = selArr[0].ps;
			Joyin.ajaxJson(basePath+ 'invest/trade/stockdeal/TrdStock001/checkRevoke',checkObj,function(result) {
				Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"),0,function(result) {
					checkObj.revokeReason = result;
					WorkFlow.doRevokeList(basePath+ 'invest/trade/stockdeal/TrdStock001/doRevoke',checkObj,"TrdStock001_btn_revoke","TrdStock002");
				});
			});
		});
	});
}());