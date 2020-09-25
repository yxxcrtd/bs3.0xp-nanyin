$(function() {
	/**
	 * 获取查询参数
	 */
	var getSearchParams = function() {
		return {
			jobId : $.trim($('#jobId').val()),
			repId : $.trim($('#repId').val())
		}
	};
	//新建
	var sava = function() {
		var object = $.serializeToObject('#submitForm', true);
		object.pageModel = pageModel;
		var chooseProdTable = object.chooseProdTable;
		var settedRule = object.settedRule;
		// 记录日志用
		object["quartsConfigDescLog"] = newQuartsConfigDesc(object);
		Joyin
				.ajaxJson(
						"checkSave",
						object,
						function(result) {
							if (result) {
								var message = JoyinUtil
										.getMessage("REPORT_E_0018");
								if (settedRule.length != 0) {
									message = JoyinUtil
											.getMessage("DIS_COMMON_C_0001");
								}
								Joyin
										.confirm(
												message,
												function() {
													//新建
													Joyin
															.ajax(
																	basePath
																			+ 'disclosure/information/manage/workmanage/ReportWorkManage002/insert',
																	{
																		pageModel : pageModel,
																		object : JSON
																				.stringify(object),
																		chooseProdTable : JSON
																				.stringify(chooseProdTable),
																		settedRule : JSON
																				.stringify(settedRule)
																	},
																	function(
																			result) {
																		var index = parent.layer
																				.getFrameIndex(window.name);
																		parent.layer
																				.close(index);
																	});
												});

							}
						});

	}

	/**
	 * 记录日志用
	 * @param type 年月日周时分
	 * @param value 值*。
	 * @param arr 输入的制定值
	 * @returns 例：每年每月每日每时每分
	 */
	function toQuartsConfigDesc(type, value, arr) {
		var typeStr = "";
		var arrStr = arr ? arr.join(",") : "";
		var all = JoyinUtil.getLabel("SystemBatchQuartzConfig002.every");
		if (type == "yearType") {
			typeStr = JoyinUtil.getLabel("SystemBatchQuartzConfig002.year");
		} else if (type == "monthType") {
			typeStr = JoyinUtil.getLabel("SystemBatchQuartzConfig002.month");
		} else if (type == "dayType") {
			typeStr = JoyinUtil.getLabel("SystemBatchQuartzConfig002.day");
		} else if (type == "weekType") {
			typeStr = JoyinUtil.getLabel("SystemBatchQuartzConfig002.week");
		} else if (type == "hourType") {
			typeStr = JoyinUtil.getLabel("SystemBatchQuartzConfig002.hour");
		} else if (type == "minuteType") {
			typeStr = JoyinUtil.getLabel("SystemBatchQuartzConfig002.minute");
		}
		if (value == "*") {
			return all + typeStr;
		}
		if (type == "hourType") {
			return arrStr
					+ JoyinUtil.getLabel("SystemBatchQuartzConfig002.hour2");
		}
		if (type == "minuteType") {
			return arrStr
					+ JoyinUtil.getLabel("SystemBatchQuartzConfig002.minute2");
		}
		if (type == "weekType" && arr) {
			var weekArr = [];
			for ( var indx in arr) {
				weekArr
						.push(JoyinUtil
								.getLabel("SystemBatchQuartzConfig002.week"
										+ arr[indx]));
			}
			return all + weekArr.join();
		}
		return arrStr + typeStr;
	}

	// 记录日志用，变更前
	function odataQuartsConfigDesc(obj) {
		var quartsConfigDesc = "";
		var yearAppointList = [];
		if (obj.yearAppointList) {
			yearAppointList.push(obj.yearAppointList.odata);
			//yearAppointList = obj.yearAppointList.odata;
			delete obj.yearAppointList.label;
		}
		var monthAppointList = [];
		if (obj.monthAppointList) {
			monthAppointList.push(obj.monthAppointList.odata);
			//monthAppointList = obj.monthAppointList.odata;
			delete obj.monthAppointList.label;
		}
		var dayAppointList = [];
		if (obj.dayAppointList) {
			dayAppointList.push(obj.dayAppointList.odata);
			//dayAppointList = obj.dayAppointList.odata;
			delete obj.dayAppointList.label;
		}
		var weekAppointList = [];
		if (obj.weekAppointList) {
			weekAppointList.push(obj.weekAppointList.odata);
			//weekAppointList = obj.weekAppointList.odata;
			delete obj.weekAppointList.label;
		}
		var hourAppointList = [];
		if (obj.hourAppointList) {
			hourAppointList.push(obj.hourAppointList.odata);
			//hourAppointList = obj.hourAppointList.odata;
			delete obj.hourAppointList.label;
		}
		var minuteAppointList = [];
		if (obj.minuteAppointList) {
			minuteAppointList.push(obj.minuteAppointList.odata);
			//minuteAppointList = obj.minuteAppointList.odata;
			delete obj.minuteAppointList.label;
		}
		quartsConfigDesc = quartsConfigDesc
				+ toQuartsConfigDesc("yearType", obj.yearType.odata,
						yearAppointList);
		quartsConfigDesc = quartsConfigDesc
				+ toQuartsConfigDesc("monthType", obj.monthType.odata,
						monthAppointList);
		if (obj.weekType.odata == ".") {
			quartsConfigDesc = quartsConfigDesc
					+ toQuartsConfigDesc("weekType", obj.weekType.odata,
							weekAppointList);
		} else {
			quartsConfigDesc = quartsConfigDesc
					+ toQuartsConfigDesc("dayType", obj.dayType.odata,
							dayAppointList);
		}
		quartsConfigDesc = quartsConfigDesc
				+ toQuartsConfigDesc("hourType", obj.hourType.odata,
						hourAppointList);
		quartsConfigDesc = quartsConfigDesc
				+ toQuartsConfigDesc("minuteType", obj.minuteType.odata,
						minuteAppointList);
		delete obj.yearType.label;
		delete obj.monthType.label;
		delete obj.dayType.label;
		delete obj.weekType.label;
		delete obj.hourType.label;
		delete obj.minuteType.label;

		return quartsConfigDesc;
	}

	// 记录日志用，变更后
	function newQuartsConfigDesc(obj) {
		var quartsConfigDesc = "";
		var yearAppointList = [];
		if (obj.yearAppointList) {
			yearAppointList.push(obj.yearAppointList);
			obj.yearAppointList = yearAppointList;
			//yearAppointList = obj.yearAppointList;
		} else {
			obj.yearAppointList = yearAppointList;
		}
		var monthAppointList = [];
		if (obj.monthAppointList) {
			monthAppointList.push(obj.monthAppointList);
			obj.monthAppointList = monthAppointList;
			//monthAppointList = obj.monthAppointList;
		} else {
			obj.monthAppointList = monthAppointList;
		}
		var dayAppointList = [];
		if (obj.dayAppointList) {
			dayAppointList.push(obj.dayAppointList);
			obj.dayAppointList = dayAppointList;
			//dayAppointList = obj.dayAppointList;
		} else {
			obj.dayAppointList = dayAppointList;
		}
		var weekAppointList = [];
		if (obj.weekAppointList) {
			weekAppointList.push(obj.weekAppointList);
			obj.weekAppointList = weekAppointList;
			//weekAppointList = obj.weekAppointList;
		} else {
			obj.weekAppointList = weekAppointList;
		}
		var hourAppointList = [];
		if (obj.hourAppointList) {
			hourAppointList.push(obj.hourAppointList);
			obj.hourAppointList = hourAppointList;
			//hourAppointList = obj.hourAppointList;
		} else {
			obj.hourAppointList = hourAppointList;
		}
		var minuteAppointList = [];
		if (obj.minuteAppointList) {
			minuteAppointList.push(obj.minuteAppointList);
			obj.minuteAppointList = minuteAppointList;
			//minuteAppointList = obj.minuteAppointList;
		} else {
			obj.minuteAppointList = minuteAppointList;
		}
		quartsConfigDesc = quartsConfigDesc
				+ toQuartsConfigDesc("yearType", obj.yearType, yearAppointList);
		quartsConfigDesc = quartsConfigDesc
				+ toQuartsConfigDesc("monthType", obj.monthType,
						monthAppointList);
		if (obj.weekType == ".") {
			quartsConfigDesc = quartsConfigDesc
					+ toQuartsConfigDesc("weekType", obj.weekType,
							weekAppointList);
		} else {
			quartsConfigDesc = quartsConfigDesc
					+ toQuartsConfigDesc("dayType", obj.dayType, dayAppointList);
		}
		quartsConfigDesc = quartsConfigDesc
				+ toQuartsConfigDesc("hourType", obj.hourType, hourAppointList);
		quartsConfigDesc = quartsConfigDesc
				+ toQuartsConfigDesc("minuteType", obj.minuteType,
						minuteAppointList);

		return quartsConfigDesc;
	}
	var chooseProdTable = $("#chooseProdTable")
			.joyintable(
					{
						ajax : {
							url : basePath
									+ 'disclosure/information/manage/workmanage/ReportWorkManage002/findProdPage',
							type : 'POST',
							data : function() {
								return getSearchParams();
							}
						},
						checkbox : false,
						rowno : true,
						searchOnInit : true,
						loadonce : true,
						page : false,
						cols : [ {
							field : 'prodId',
							title : '产品代码',
							fixed : true
						}, {
							field : 'prodName',
							title : '产品名称',
							fixed : true
						}, {
							field : 'operation',
							title : 'SysCommon.operation',
							type : 'action',
							buttons : [ 'delt' ]
						} ]
					});

	chooseProdTable.bind('click', '.delt', function(data, index) {
		chooseProdTable.removeRow(data);
	});
	/**
	 * 预览产品增加时间
	 */
	function addProd(param) {
		var index = 0;
		var obj = chooseProdTable.getAllRows();
		for ( var i in obj) {
			if (obj[i].prodId == param.prodId) {
				index++;
			}
		}
		if (index == 0) {
			chooseProdTable.addRow(param);
		}
	}
	/**
	 * 预览产品增加时间
	 */
	function getSelectData(param) {
		var obj = chooseProdTable.getAllRows();
		for ( var j in param) {
			var index = 0;
			for ( var i in obj) {
				if (obj[i].prodId == param[j].prodId) {
					index++;
				}
			}
			if (index == 0) {
				chooseProdTable.addRow(param[j]);
			}
		}
	}
	/**
	 * 预览产品列表
	 * @returns
	 */
	function previewProdSelect() {
		var object = $.serializeToObject('#submitForm', true);
		object.pageModel = pageModel;
		var settedRule = object.settedRule;
		var chooseProdTable = object.chooseProdTable;
		Joyin
				.dialog(
						JoyinUtil.getLabel('预览产品列表'),
						basePath
								+ 'disclosure/information/manage/workmanage/ReportWorkManage003/init',
						'90%', '90%', {
							settedRule : JSON.stringify(settedRule),
							hasProdTable : JSON.stringify(chooseProdTable)
						}, function(result) {
							var param = result;
							//addProd(top.addProd);
							getSelectData(top.getSelectData);
						})
	}
	var settedRule = $("#settedRule")
			.joyintable(
					{
						ajax : {
							url : basePath
									+ 'disclosure/information/manage/workmanage/ReportWorkManage002/findRulePage',
							type : 'POST',
							data : function() {
								return getSearchParams();
							}
						},
						checkbox : false,
						searchOnInit : true,
						loadonce : true,
						page : false,
						cols : [ {
							field : 'ruleType1Name',
							title : '字段值',
							fixed : true
						}, {
							field : 'ruleType2Name',
							title : '比较条件',
							fixed : true
						}, {
							field : 'ruleType3Name',
							title : '值类型',
							fixed : true
						}, {
							field : 'ruleType4Name',
							title : '值',
							fixed : true
						}, {
							field : 'operation',
							title : 'SysCommon.operation',
							type : 'action',
							buttons : [ 'delt' ]
						}, {
							field : 'ruleType1',
							visible : false
						}, {
							field : 'ruleType2',
							visible : false
						}, {
							field : 'ruleType3',
							visible : false
						}, {
							field : 'ruleType4',
							visible : false
						} ]
					});
	settedRule.bind('click', '.delt', function(data, index) {
		settedRule.removeRow(data);
	});
	/**
	 * 规则增加事件
	 * @returns
	 */
	function addrule() {
		var rule = new Object;
		if($('#ruleType1').select2('data')[0]){
			rule.ruleType1 = $('#ruleType1').select2('data')[0].id;
			rule.ruleType1Name = $('#ruleType1').select2('data')[0].text;
		}
		else{
			Joyin.alert("请选择字段值",7);
			return;
		}
		
		if($('#ruleType2').select2('data')[0]){
			rule.ruleType2 = $('#ruleType2').select2('data')[0].id;
			rule.ruleType2Name = $('#ruleType2').select2('data')[0].text;
		}
		else{
			Joyin.alert("请选择比较条件",7);
			return;
		}
		if($('#ruleType3').select2('data')[0]){
			rule.ruleType3 = $('#ruleType3').select2('data')[0].id;
			rule.ruleType3Name = $('#ruleType3').select2('data')[0].text;
		}
		else{
			Joyin.alert("请选择值类型",7);
			return;
		}		
		if (rule.ruleType1 == 'PROFIT_TYPE') {
			if($('#profitType').select2('data')[0]){
				rule.ruleType4 = $('#profitType').select2('data')[0].id;
				rule.ruleType4Name = $('#profitType').select2('data')[0].text;
			}
			else{
				Joyin.alert("请选择收益类型",7);
				return;
			}
			
		} else if (rule.ruleType1 == 'RAISE_TYPE') {
			if($('#raiseType').select2('data')[0]){
				rule.ruleType4 = $('#raiseType').select2('data')[0].id;
				rule.ruleType4Name = $('#raiseType').select2('data')[0].text;
			}
			else{
				Joyin.alert("请选择募集方式",7);
				return;
			}
		} else if (rule.ruleType1 == 'ISSUE_MODE') {
			if($('#issueMode').select2('data')[0]){
				rule.ruleType4 = $('#issueMode').select2('data')[0].id;
				rule.ruleType4Name = $('#issueMode').select2('data')[0].text;
			}
			else{
				Joyin.alert("请选择发行方式",7);
				return;
			}
		} else if (rule.ruleType1 == 'ISSUE_STATUS') {
			if ($('#issueStauts').select2('data')[0]) {
				rule.ruleType4 = $('#issueStauts').select2('data')[0].id;
				rule.ruleType4Name = $('#issueStauts').select2('data')[0].text;
			} else {
				Joyin.alert("请选择发行状态", 7);
				return;
			}
		} else if (rule.ruleType1 == 'OPEN_MODE') {
			if ($('#openMode').select2('data')[0]) {
				rule.ruleType4 = $('#openMode').select2('data')[0].id;
				rule.ruleType4Name = $('#openMode').select2('data')[0].text;
			} else {
				Joyin.alert("请选择开放模式", 7);
				return;
			}
			
		} else if (rule.ruleType1 == 'IS_INDEPE_ACC') {
			if ($('#isIndepeAcc').select2('data')[0]) {
				rule.ruleType4 = $('#isIndepeAcc').select2('data')[0].id;
				rule.ruleType4Name = $('#isIndepeAcc').select2('data')[0].text;
			} else {
				Joyin.alert("请选择是否", 7);
				return;
			}
			
		} else if (rule.ruleType1 == 'PROD_TYPE') {
			if ($('#prodType').select2('data')[0]) {
				rule.ruleType4 = $('#prodType').select2('data')[0].id;
				rule.ruleType4Name = $('#prodType').select2('data')[0].text;
			} else {
				Joyin.alert("请选择产品类型", 7);
				return;
			}
			
		} else if (rule.ruleType1 == 'FINA_TYPE') {
			if ($('#finaType').select2('data')[0]) {
				rule.ruleType4 = $('#finaType').select2('data')[0].id;
				rule.ruleType4Name = $('#finaType').select2('data')[0].text;
			} else {
				Joyin.alert("请选择理财类型", 7);
				return;
			}
			
		} else if (rule.ruleType1 == 'PRODPROFITFLAG') {
			if ($('#prodproFitflag').select2('data')[0]) {
				rule.ruleType4 = $('#prodproFitflag').select2('data')[0].id;
				rule.ruleType4Name = $('#prodproFitflag').select2('data')[0].text;
			} else {
				Joyin.alert("请选择收益标识", 7);
				return;
			}
			
		} else if (rule.ruleType1 == 'SALETARGET_DECLARE') {
			if ($('#saletargetDeclare').select2('data')[0]) {
				rule.ruleType4 = $('#saletargetDeclare').select2('data')[0].id;
				rule.ruleType4Name = $('#saletargetDeclare').select2('data')[0].text;
			} else {
				Joyin.alert("请选择客户类型", 7);
				return;
			}
			
		}else if (rule.ruleType1 == 'PROD_TERM_TYPE') {
			if ($('#prodTermType').select2('data')[0]) {
				rule.ruleType4 = $('#prodTermType').select2('data')[0].id;
				rule.ruleType4Name = $('#prodTermType').select2('data')[0].text;
			} else {
				Joyin.alert("请选择产品期限类型", 7);
				return;
			}
			
		}
		else {
			if (rule.ruleType3 == '03') {
				// 平台日
				//rule.ruleType4 = rule.ruleType3;
				//rule.ruleType4Name = rule.ruleType3Name;
			} else {
				if($('#chooseDate').val()){
					rule.ruleType4 = $('#chooseDate').val();
					rule.ruleType4Name = $('#chooseDate').val();
				}
				else{
					Joyin.alert("请选择日期",7);
					return;
				}
				
			}
		}
		var obj = settedRule.getAllRows();
		var index = 0;
		for ( var i in obj) {
			if (obj[i].ruleType1 == rule.ruleType1
					&& obj[i].ruleType2 == rule.ruleType2
					&& obj[i].ruleType3 == rule.ruleType3
					&& obj[i].ruleType4 == rule.ruleType4) {
				index++;
			}
		}
		if (index == 0) {
			settedRule.addRow(rule);
		}
		else{
			Joyin.alert("规则已存在，请重新设置！",7);
			return;
		}
	}
	function ruleType1Change() {
		var ruleType1 = $('#ruleType1').select2('data')[0].id;
		var ruleType3;
		if ($('#ruleType3').val()) {
			ruleType3 = $('#ruleType3').select2('data')[0].id;
		}
		if (ruleType1 == 'PROFIT_TYPE') {
			$('#raiseTypeSelect').hide();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').show();
			$('#issueModeSelect').hide();
			$('#issueStautsSelect').hide();
			$('#openModeSelect').hide();
			$('#isIndepeAccSelect').hide();
			$('#prodTypeSelect').hide();
			$('#finaTypeSelect').hide();
			$('#prodTermTypeSelect').hide();
			$('#prodproFitflagSelect').hide();
			$('#saletargetDeclareSelect').hide();
		} else if (ruleType1 == 'RAISE_TYPE') {
			$('#raiseTypeSelect').show();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').hide();
			$('#issueModeSelect').hide();
			$('#issueStautsSelect').hide();
			$('#openModeSelect').hide();
			$('#isIndepeAccSelect').hide();
			$('#prodTypeSelect').hide();
			$('#finaTypeSelect').hide();
			$('#prodTermTypeSelect').hide();
			$('#prodproFitflagSelect').hide();
			$('#saletargetDeclareSelect').hide();
		} else if (ruleType1 == 'ISSUE_MODE') {
			$('#raiseTypeSelect').hide();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').hide();
			$('#issueModeSelect').show();
			$('#issueStautsSelect').hide();
			$('#openModeSelect').hide();
			$('#isIndepeAccSelect').hide();
			$('#prodTypeSelect').hide();
			$('#finaTypeSelect').hide();
			$('#prodTermTypeSelect').hide();
			$('#prodproFitflagSelect').hide();
			$('#saletargetDeclareSelect').hide();
		} else if (ruleType1 == 'ISSUE_STATUS') {
			$('#raiseTypeSelect').hide();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').hide();
			$('#issueModeSelect').hide();
			$('#issueStautsSelect').show();
			$('#openModeSelect').hide();
			$('#isIndepeAccSelect').hide();
			$('#prodTypeSelect').hide();
			$('#finaTypeSelect').hide();
			$('#prodTermTypeSelect').hide();
			$('#prodproFitflagSelect').hide();
			$('#saletargetDeclareSelect').hide();
		} else if (ruleType1 == 'OPEN_MODE') {
			$('#raiseTypeSelect').hide();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').hide();
			$('#issueModeSelect').hide();
			$('#issueStautsSelect').hide();
			$('#openModeSelect').show();
			$('#isIndepeAccSelect').hide();
			$('#prodTypeSelect').hide();
			$('#finaTypeSelect').hide();
			$('#prodTermTypeSelect').hide();
			$('#prodproFitflagSelect').hide();
			$('#saletargetDeclareSelect').hide();
		} else if (ruleType1 == 'IS_INDEPE_ACC') {
			$('#raiseTypeSelect').hide();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').hide();
			$('#issueModeSelect').hide();
			$('#issueStautsSelect').hide();
			$('#openModeSelect').hide();
			$('#isIndepeAccSelect').show();
			$('#prodTypeSelect').hide();
			$('#finaTypeSelect').hide();
			$('#prodTermTypeSelect').hide();
			$('#prodproFitflagSelect').hide();
			$('#saletargetDeclareSelect').hide();
		} else if (ruleType1 == 'PROD_TYPE') {
			$('#raiseTypeSelect').hide();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').hide();
			$('#issueModeSelect').hide();
			$('#issueStautsSelect').hide();
			$('#openModeSelect').hide();
			$('#isIndepeAccSelect').hide();
			$('#prodTypeSelect').show();
			$('#finaTypeSelect').hide();
			$('#prodTermTypeSelect').hide();
			$('#prodproFitflagSelect').hide();
			$('#saletargetDeclareSelect').hide();
		} else if (ruleType1 == 'FINA_TYPE') {
			$('#raiseTypeSelect').hide();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').hide();
			$('#issueModeSelect').hide();
			$('#issueStautsSelect').hide();
			$('#openModeSelect').hide();
			$('#isIndepeAccSelect').hide();
			$('#prodTypeSelect').hide();
			$('#finaTypeSelect').show();
			$('#prodTermTypeSelect').hide();
			$('#prodproFitflagSelect').hide();
			$('#saletargetDeclareSelect').hide();
		}else if(ruleType1 == 'PROD_TERM_TYPE'){
			$('#raiseTypeSelect').hide();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').hide();
			$('#issueModeSelect').hide();
			$('#issueStautsSelect').hide();
			$('#openModeSelect').hide();
			$('#isIndepeAccSelect').hide();
			$('#prodTypeSelect').hide();
			$('#finaTypeSelect').hide();
			$('#prodTermTypeSelect').show();
			$('#prodproFitflagSelect').hide();
			$('#saletargetDeclareSelect').hide();
		} else if(ruleType1 == 'PRODPROFITFLAG'){
			$('#raiseTypeSelect').hide();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').hide();
			$('#issueModeSelect').hide();
			$('#issueStautsSelect').hide();
			$('#openModeSelect').hide();
			$('#isIndepeAccSelect').hide();
			$('#prodTypeSelect').hide();
			$('#finaTypeSelect').hide();
			$('#prodTermTypeSelect').hide();
			$('#prodproFitflagSelect').show();
			$('#saletargetDeclareSelect').hide();
		} else if(ruleType1 == 'SALETARGET_DECLARE'){
			$('#raiseTypeSelect').hide();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').hide();
			$('#issueModeSelect').hide();
			$('#issueStautsSelect').hide();
			$('#openModeSelect').hide();
			$('#isIndepeAccSelect').hide();
			$('#prodTypeSelect').hide();
			$('#finaTypeSelect').hide();
			$('#prodTermTypeSelect').hide();
			$('#prodproFitflagSelect').hide();
			$('#saletargetDeclareSelect').show();
		}
		else {
			if (ruleType3 == '03' || !$('#ruleType3').val()) {
				$('#raiseTypeSelect').hide();
				$('#chooseDateSelect').hide();
				$('#profitTypeSelect').hide();
				$('#issueModeSelect').hide();
				$('#issueStautsSelect').hide();
				$('#openModeSelect').hide();
				$('#isIndepeAccSelect').hide();
				$('#prodTypeSelect').hide();
				$('#finaTypeSelect').hide();
				$('#prodTermTypeSelect').hide();
				$('#prodproFitflagSelect').hide();
				$('#saletargetDeclareSelect').hide();
			} else {
				$('#raiseTypeSelect').hide();
				$('#chooseDateSelect').show();
				$('#profitTypeSelect').hide();
				$('#issueModeSelect').hide();
				$('#issueStautsSelect').hide();
				$('#openModeSelect').hide();
				$('#isIndepeAccSelect').hide();
				$('#prodTypeSelect').hide();
				$('#finaTypeSelect').hide();
				$('#prodTermTypeSelect').hide();
				$('#prodproFitflagSelect').hide();
				$('#saletargetDeclareSelect').hide();
			}
		}
	}
	function ruleChange() {
		var ruleId = $('#ruleId').select2('data')[0].col;
		$('#repName').val(ruleId);
	}
	/**
	 * 画面加载完成
	 */
	$(function() {
		$('#raiseTypeSelect').hide();
		$('#chooseDateSelect').hide();
		$('#profitTypeSelect').hide();
		$('#issueModeSelect').hide();
		$('#issueStautsSelect').hide();
		$('#openModeSelect').hide();
		$('#isIndepeAccSelect').hide();
		$('#prodTypeSelect').hide();
		$('#finaTypeSelect').hide();
		$('#prodTermTypeSelect').hide();
		$('#prodproFitflagSelect').hide();
		$('#saletargetDeclareSelect').hide();
		//预览产品列表点击事件
		$('#ReportWorkManage002_btn_previewProdList').click(previewProdSelect);
		//规则增加事件
		$('#ReportWorkManage002_btn_add').click(addrule);
		//规则1改变事件
		$('#ruleType1').change(ruleType1Change);
		//规则3改变事件
		$('#ruleType3').change(ruleType1Change);
		//规则名称改变事件
		$('#ruleId').change(ruleChange);
		//保存绑定点击事件
		$('#ReportWorkManage002_btn_save').click(function() {
			sava();
		})

		if (pageModel != '0') {
			$('#jobId').attr("disabled", true);
		}

	});

});
