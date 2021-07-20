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
		if ($('#tradeDate').startDate()) {
			pageParam.tradeDateBegin = $('#tradeDate').startDate();
		}
		if ($('#tradeDate').endDate()) {
			pageParam.tradeDateEnd = $('#tradeDate').endDate();
		}
		if ($('#hkMarket').val()) {
			pageParam.hkMarketList = $('#hkMarket').val().join(",");
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
			Joyin.dialog(JoyinUtil.getLabel("TrdStockConnect002.reference"),
					basePath +"invest/trade/trdstockconnect/TrdStockConnect002/init",
							"98%", "98%", data);
		},
		cols : [
				{field : 'validStatusText',sortField : 'VALID_STATUS',width : 100,title : 'TrdStock001.validStatus'}
				,{field : 'tradeId',sortField : 'TRADE_ID',width : 170,title : 'TrdStock001.tradeId'}
				,{field : 'entrustId',sortField : 'ENTRUST_ID',width : 180,title : '指令编号'}
				,{field : 'portfolioId',sortField : 'PORTFOLIO_ID',width : 140,title : 'TrdStock001.portfolioId',
					render : function(data, type, row, meta) {
						return "<a class='portfolioId-link' style='cursor: pointer;'>"
								+ row.portfolioId + "</a>";
					}
				}
				,{field : 'portfolioName',sortField : 'PORTFOLIO_NAME',width : 140,title : 'TrdStock001.portfolioName'}
				,{field : 'finprodId',sortField : 'finprod_id',width : 140,title : 'TrdStock001.finprodId',
					render : function(data, type, row, meta) {
						return "<a class='finprodId-link' style='cursor: pointer;'>"
								+ row.finprodId + "</a>";
					}
				}
				,{field : 'finprodAbbr',sortField : 'FINPROD_ABBR',width : 140,title : 'TrdStock001.finprodAbbr'}
				,{field : 'psStr',sortField : 'ps',width : 100,title : 'TrdStock001.ps'}
				,{field : 'hkMarketStr',sortField : 'hk_market',width : 120,title : '港股通市场'}
				,{field : 'tradeDate',sortField : 'TRADE_DATE',width : 100,title : 'TrdStock001.tradeDate',type : 'date'}
				,{field : 'unitCprice',sortField : 'UNIT_CPRICE',width : 170,title : '交易价格（港元）',type : 'money', digit:3}
				,{field : 'shareAmt',sortField : 'SHARE_AMT',width : 150,title : '交易数量（股）'}
				,{field : 'dealAmt',sortField : 'DEAL_AMT',width : 150,title : '成交金额（港元）',type : 'money', digit:2}
				,{field : 'tradeAmt',sortField : 'TRADE_AMT',width : 150,title : '交易金额（元）',type : 'money', digit:2}
				,{field : 'commission',sortField : 'COMMISSION',width : 150,title : '佣金（元）',type : 'money', digit:2}
				,{field : 'stampDuty',sortField : 'STAMPDUTY',width : 150,title : '印花税（港元）'}
				,{field : 'transactionLevy',sortField : 'TRANSACTION_LEVY',width : 150,title : '交易征费（港元）',type : 'money', digit:2}
				,{field : 'transactionFee',sortField : 'TRANSACTION_FEE',width : 150,title : '交易费（港元）',type : 'money', digit:2}
				,{field : 'transactionSystemFee',sortField : 'TRANSACTION_SYSTEM_FEE',width : 190,title : '交易系统使用费（港元）',type : 'money', digit:2}
				,{field : 'sharePaymentFee',sortField : 'SHARE_PAYMENT_FEE',width : 180,title : '股份交收费（港元）',type : 'money', digit:2}
				,{field : 'remark',sortField : 'REMARK',width : 150,title : 'TrdStock001.remark'}
				,{field : 'validStatus',visible : false}
				,{field : 'ps',visible : false}
				,{field : 'tradeMarket',visible : false}
				,{field : 'tradeMarketMode',visible : false}
				,{field : 'tradePlat',visible : false}
				,{field : 'workflowId', visible:false}
				,{field : 'ccy',visible : false} 
				,{field : 'hkMarket',visible : false}
				]
			});

	var exportExcel = function() {
		Joyin.exportExcel('export', getPageParams());
	};

	/**
	 * 绑定编辑事件
	 */
	table.bind('click','.edit',function(data, index) {
		data.operateType = "edit";
		data.pageModel = PAGE_MODEL_EDIT;
		Joyin.ajaxJson(basePath+'invest/trade/trdstockconnect/TrdStockConnect002/check',data,function(result) {
			Joyin.dialog(JoyinUtil.getLabel("TrdStockConnect002.edit"),
					basePath+ "cebbank/trdstockconnect/TrdStockConnect002/init",
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
		Joyin.ajaxJson(basePath+ 'invest/trade/trdstockconnect/TrdStockConnect001/check',object,function(result) {
			Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")),function() {
				WorkFlow.doDeleteList(basePath+ 'cebbank/trdstockconnect/TrdStockConnect001/delete',object,"TrdStockConnect001_btn_revoke","TrdStockConnect001",function() {
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
		$('#TrdStockConnect001_btn_search').click(function() {
			table.load();
			/*$('#TrdStockConnect001_btn_revoke').show();*/
		});

		$('#TrdStockConnect001_btn_export').click(function() {
			exportExcel();
		});
		$('#TrdStockConnect001_btn_add').click(function() {
			Joyin.dialog(JoyinUtil.getLabel("TrdStockConnect002"),
							basePath+ "invest/trade/trdstockconnect/TrdStockConnect002/init",
							"99%", "99%", {
								pageModel : PAGE_MODEL_NEW
							}, function() {
								table.reload();
			});
		});
		/**
		 * 撤销
		 */
		$('#TrdStockConnect001_btn_revoke').click(function() {
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
			Joyin.ajaxJson(basePath+ 'invest/trade/trdstockconnect/TrdStockConnect001/checkRevoke',checkObj,function(result) {
				Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"),0,function(result) {
					checkObj.revokeReason = result;
					WorkFlow.doRevokeList(basePath+ 'invest/trade/trdstockconnect/TrdStockConnect001/doRevoke',checkObj,"TrdStockConnect001_btn_revoke","TrdStockConnect002");
				});
			});
		});
	});
}());