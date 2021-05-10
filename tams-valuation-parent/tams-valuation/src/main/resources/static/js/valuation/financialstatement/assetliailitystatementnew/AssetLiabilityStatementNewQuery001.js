$(function () {
	/**
	 * 获取查询参数
	 */
	var getSearchParams = function() {
		var searchObject = $.serializeToObject('#searchForm');
		searchObject.profitType = $.trim($('#profitType').val());
		return searchObject;
	};

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
	 * 定义数据表格 - 资产
	 */
	var AssetTable = $("#AssetTable").joyintable({
		ajax: {
			url: 'findAssets',
			type: 'POST',
			data: function() {
				return getSearchParams();
			}
		},
		checkbox: false,
		loadonce: true,
		page: false,
		cellMinWidth : 200,
		dblclick:function(table, data) {
			if (reportDimen == "1") {// 账套
				return;
			}
			if (data.itemName == undefined) {
				return;
			}
			var condition = $.serializeToObject('#searchForm');
			condition.itemName = data.itemName;
			condition.itemCode = data.itemCode;
			condition.endingBalance = data.endingBalance;
			condition.initialBalance = data.initialBalance;
			condition.tableId = "AssetTable";
			condition.booksetGroup = $("#booksetGroup").val();
			Joyin.dialog(JoyinUtil.getLabel("资产负债表-账套指标明细"),
				basePath + "valuation/financialstatement/assetliailitystatementnew/AssetLiabilityStatementNewQuery002/init",
				"95%", "90%", condition, function(){
				});
		},
		cols: [
			{field:'itemName', title: '资产', sortable: false, render: function (data, type, row, meta) {
					if(data.indexOf("总计") > 0) {
						return '<strong>' + data + '</strong>';
					}
					return data;
				}},
			{field:'itemCode', title: '资产名称', width:240, visible: false},
			{field:'endingBalance', title: '期/年末余额', type:"money", width:240, sortable: false, render: function (data, type, row, meta) {
					if(data == '') {
						return '';
					}
					return formatMoney(data, 2);
				}},
			{field:'initialBalance', title: '期/年初余额', type:"money", width:240, sortable: false, render: function (data, type, row, meta) {
					if(data == '') {
						return '';
					}
					return formatMoney(data, 2);
				}},
		]
	});

	/**
	 * 定义数据表格 - 负债和所有者权益
	 */
	var LiabilityInterestTable = $("#LiabilityInterestTable").joyintable({
		ajax: {
			url: 'findLiabilityInterest',
			type: 'POST',
			data: function() {
				return getSearchParams();
			}
		},
		checkbox: false,
		loadonce: true,
		page: false,
		cellMinWidth : 200,
		dblclick:function(table, data) {
			if (reportDimen == "1") {// 单账套
				return;
			}
			var condition = $.serializeToObject('#searchForm');
			condition.itemName = data.itemName;
			condition.itemCode = data.itemCode;
			condition.endingBalance = data.endingBalance;
			condition.initialBalance = data.initialBalance;
			condition.tableId = "LiabilityInterestTable";
			condition.booksetGroup = $("#booksetGroup").val();
			Joyin.dialog(JoyinUtil.getLabel("资产负债表-账套指标明细"),
				basePath + "valuation/financialstatement/assetliailitystatementnew/AssetLiabilityStatementNewQuery002/init",
				"95%", "90%", condition, function(){
				});
		},
		cols: [
			{field:'itemName', title: '负债和净资产',  sortable: false, render: function (data, type, row, meta) {
					if(data.indexOf("总计") > 0 || data.indexOf("合计") > 0) {
						return '<strong>' + data + '</strong>';
					}
					return data;
				}},
			{field:'itemCode', title: '资产名称', width:240, visible: false},
			{field:'endingBalance', title: '期/年末余额',type:"money", width:240, sortable: false},
			{field:'initialBalance', title: '期/年初余额',type:"money", width:240, sortable: false}
		]
	});

	/**
	 * 联动绑定 - 筛选条件
	 */
	var linkBind = function(){
		$("#c_booksetId").hide();
		$("#reportDimen").change(function() {
			$("#booksetId").val('');
			var reportDimen = $("#reportDimen").val();
			$("#booksets").val(reportDimen);
			if ('0' == reportDimen) { // 账套组
				$("#c_booksetId").hide();
				$("#booksetId").val("").trigger("change");// select清空
				$("#c_booksetGroup").show();
			} else {
				$("#c_booksetId").show();
				$("#c_booksetGroup").hide();
				$("#booksetGroup").val("").trigger("change");
			}
		});

		//  绑定联动：账套-筛选条件-日期、期限
		$("#booksetId").change(function() {
			var booksetId = $("#booksetId").val();
			var reportTermType = $("#reportTermType").val();
			if(booksetId != "" && ['01','02','03','04','05'].indexOf(reportTermType) > -1){
				Joyin.ajax(
					basePath + 'valuation/financialstatement/assetliailitystatementnew/AssetLiabilityStatementNewQuery001/getDateInfo',
					{booksetId : booksetId,reportTermType : reportTermType},
					function (data) {
						if(reportTermType === '01'){
							$("#date_reportDate").val(data.reportDate).trigger("change");
						}else if(reportTermType === '02'){
							$("#month_reportDate").val(data.reportDate).trigger("change");
						}else if(['03','04'].indexOf(reportTermType) > -1){
							$("#year_reportDate").val(data.reportDate).trigger("change");
							$("#reportTerm").textSelect('setValue',data.reportTerm);
						}else if(reportTermType === '05'){
							$("#year_reportDate").val(data.reportDate).trigger("change");
						}
					});
			}else{
				$("#date_reportDate").val("").trigger("change");
				$("#month_reportDate").val("").trigger("change");
				$("#year_reportDate").val("").trigger("change");
				$("#reportTerm").textSelect('setValue',false);
			}
		});

		//  绑定联动：账套组-筛选条件-日期、期限
		$("#booksetGroup").change(function() {
			var booksetGroup = $("#booksetGroup").val();
			var reportTermType = $("#reportTermType").val();
			if(booksetGroup != "" && ['01','02','03','04','05'].indexOf(reportTermType) > -1){
				Joyin.ajax(
					basePath + 'valuation/financialstatement/assetliailitystatementnew/AssetLiabilityStatementNewQuery001/getDateInfo',
					{booksetGroup : booksetGroup,reportTermType : reportTermType},
					function (data) {
						if(reportTermType === '01'){
							$("#date_reportDate").val(data.reportDate).trigger("change");
						}else if(reportTermType === '02'){
							$("#month_reportDate").val(data.reportDate).trigger("change");
						}else if(['03','04'].indexOf(reportTermType) > -1){
							$("#year_reportDate").val(data.reportDate).trigger("change");
							$("#reportTerm").textSelect('setValue',data.reportTerm);
						}else if(reportTermType === '05'){
							$("#year_reportDate").val(data.reportDate).trigger("change");
						}
					});
			}else{
				$("#date_reportDate").val("").trigger("change");
				$("#month_reportDate").val("").trigger("change");
				$("#year_reportDate").val("").trigger("change");
				$("#reportTerm").textSelect('setValue',false);
			}
		});

		$("#reportTermType").change(function(){

			var booksetId = $("#booksetId").val();
			var booksetGroup = $("#booksetGroup").val();
			var reportTermType = $("#reportTermType").val();
			if(reportTermType != '' && (booksetId != '' || booksetGroup != '')){
				Joyin.ajax(
					basePath + 'valuation/financialstatement/assetliailitystatementnew/AssetLiabilityStatementNewQuery001/getDateInfo',
					{booksetId : booksetId,booksetGroup : booksetGroup,reportTermType : reportTermType},
					function (data) {
						if(reportTermType === '01'){
							$("#date_reportDate").val(data.reportDate).trigger("change");
						}else if(reportTermType === '02'){
							$("#month_reportDate").val(data.reportDate).trigger("change");
						}else if(['03','04'].indexOf(reportTermType) > -1){
							$("#year_reportDate").val(data.reportDate).trigger("change");
							$("#reportTerm").textSelect('setValue',data.reportTerm);
						}else if(reportTermType === '05'){
							$("#year_reportDate").val(data.reportDate).trigger("change");
						}
					});
			}else{
				$("#date_reportDate").val("").trigger("change");
				$("#month_reportDate").val("").trigger("change");
				$("#year_reportDate").val("").trigger("change");
				$("#reportTerm").textSelect('setValue',false);
			}

			var laydate = layui.laydate;
			var arr1 = ["03", "04"] // 03-季报, 04-半年报
			var arr2 = ["03", "04", "05"] // 03-季报, 04-半年报, 05-年报
			var reportTermType = $("#reportTermType").val();
			if(arr1.indexOf(reportTermType) >= 0){// 报表期限类型为半年报或季报时，可以选择报表期限
				$("#c_reportTerm").show();
			}else{
				$("#c_reportTerm").hide();
			}
			if(arr2.indexOf(reportTermType) >= 0){// 03-季报, 04-半年报, 05-年报
				laydate.render({
					elem: '#year_reportDate',
					type: 'year',
					format: 'yyyy'
				});
				$("#c_year_reportDate").show();
				$("#c_month_reportDate").hide();
				$("#c_date_reportDate").hide();
				clearDateInput();
			}
			if(reportTermType == '02'){// 月报
				laydate.render({
					elem: '#month_reportDate',
					type: 'month',
					format: 'yyyy-MM'
				});
				$("#c_year_reportDate").hide();
				$("#c_month_reportDate").show();
				$("#c_date_reportDate").hide();
				clearDateInput();
			}
			if(reportTermType == '01'){// 日报
				laydate.render({
					elem: '#date_reportDate',
					type: 'date',
					format: 'yyyy-MM-dd'
				});
				$("#c_year_reportDate").hide();
				$("#c_month_reportDate").hide();
				$("#c_date_reportDate").show();
				clearDateInput();
			}
		});
		$("#year_reportDate").click(function(event){
			event.stopPropagation();// 阻止事件冒泡
		});
		$("#month_reportDate").click(function(event){
			event.stopPropagation();// 阻止事件冒泡
		});
		$("#date_reportDate").click(function(event){
			event.stopPropagation();// 阻止事件冒泡
		});
	}

	/**
	 * 清空日期 input
	 */
	var clearDateInput = function() {
		$("#year_reportDate").val("");
		$("#month_reportDate").val("");
		$("#date_reportDate").val("");
		$("#reportTerm").val("");
		$("#reportTerm").find("option").remove(); // 框架缺陷，防止同code不会更新

		$("#year_reportDate").val("");
		$("#month_reportDate").val("");
		$("#date_reportDate").val("");
		$("#reportTerm").val("");
	}

	/**
	 * 设置表头
	 */
	var showHeadInfo = function(){
		Joyin.ajaxJson(basePath + 'valuation/financialstatement/assetliailitystatementnew/AssetLiabilityStatementNewQuery001/getHeadInfo', getSearchParams(), function(result) {
			$("#org").html("编制单位： " + result.org);
			$("#statementType").html("报表类型： " + result.statementType);
			$("#statementDate").html("统计日期： " + result.statementDate);
		});
	}
	//理财产品份额净值 and 理财产品份额总额份
	var jzfeAndZfe = function () {
		//理财产品份额净值 = 资产类合计-负债类合计
		//资产类合计
		var ZCHJ = 0;
		//负债类合计
		var FZZJ = 0;
		//实收理财金额
		var SYLCJE = 0;
		for (var i = 0; i < AssetTable.getAllRows().length; i++) {
			var endingBalance1 = AssetTable.getAllRows()[i].endingBalance;
			var itemCode1 = AssetTable.getAllRows()[i].itemCode;
			if ("ZCHJ" === itemCode1){
				ZCHJ = endingBalance1;
			}
		}
		for (var i = 0; i < LiabilityInterestTable.getAllRows().length; i++) {
			var endingBalance2 = LiabilityInterestTable.getAllRows()[i].endingBalance;
			var itemCode2 = LiabilityInterestTable.getAllRows()[i].itemCode;
			if ("FZ_15" === itemCode2){
				FZZJ = endingBalance2;
			}
			if ("SYZQY_01" === itemCode2){
				SYLCJE = endingBalance2;
			}
		}
		//理财产品份额净值 = 资产类合计-负债类合计
		document.getElementById("valuation").innerHTML= formatMoney(JoyinMath.doMinus(ZCHJ, FZZJ), 2);
		//理财产品份额总额份 = 实收理财资金
		document.getElementById("sumprice").innerHTML = formatMoney(SYLCJE, 2);
	}

	/**
	 * 查询按钮绑定
	 */
	var searchBind = function(){
		// 绑定按钮事件
		$('#AssetLiabilityStatementNewQuery001_btn_search').click(function() {
			Joyin.ajaxJson(basePath + 'valuation/financialstatement/assetliailitystatementnew/AssetLiabilityStatementNewQuery001/check', getSearchParams(), function(result) {
				reportDimen = $("#reportDimen").val();
				showHeadInfo(); // 设置表头
				if(!AssetTable.table) {
					$.when(AssetTable.load(), LiabilityInterestTable.load()).done(function () {
						jzfeAndZfe();
					});
				} else {
					$.when(AssetTable.reload(), LiabilityInterestTable.reload()).done(function () {
						jzfeAndZfe();
					});
				}
			});
		});
	}

	/**
	 * 导出
	 */
	var exportExcel = function() {
		Joyin.ajaxJson(basePath + 'valuation/financialstatement/assetliailitystatementnew/AssetLiabilityStatementNewQuery001/check', getSearchParams(), function(result) {
			if(result.data){
				Joyin.exportExcel('export', getSearchParams());
			}
		});
	};

	/**
	 * 画面加载完成
	 */
	$(function() {
		searchBind(); // 查询按钮绑定
		linkBind(); // 联动绑定 - 筛选条件

		// 绑定导出事件
		$('#AssetLiabilityStatementNewQuery001_btn_export').click(function() {
			exportExcel();
		});

	});

});



