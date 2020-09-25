var upFile;
var upFileName;

$(function() {

	var gradeChange = function() {
		btnShow();
	};

	/**
	 * 修改/新建
	 */
	var saveOrSubmit = function(operateType) {
		var object = $.serializeToObject('#submitForm');
		if (upFileName) {
			object.indexValue = upFileName;
		}
		object.pageModel = pageModel;
		object.operateType = operateType;
		object.indexDetail = $('#indexDetail').val();

		Joyin.ajaxJson(
					basePath+ 'disclosure/information/config/reportcontentenum/ReportContentEnum003/check',object,
						function(result) {
							Joyin.confirm(
									result.data,
									function() {
										if (operateType === 'save') {
											object.operateType = "save";
											WorkFlow.doSaveList(
													basePath+ 'disclosure/information/config/reportcontentenum/ReportContentEnum003/save',object,
																	"ReportContentEnum003_btn_save");
												} else {// submit
													WorkFlow.doSaveList(
																	basePath+ 'disclosure/information/config/reportcontentenum/ReportContentEnum003/save',object,
																	"ReportContentEnum003_btn_submit");
			}
		});
	});
};

	/**
	 * 根据操作类型：显示按钮
	 */
	function btnShow() {
		var indexType = $('#indexType').val();
		if (indexType === '01') {
			$("#indexValue").show();
			$("#test1").hide();
			$('#ReportContentEnum003_btn_submit').show();
		} else {
			$("#test1").show();
		//	$("#indexValue").hide();
			$("#indexValue").show();
			$('#ReportContentEnum003_btn_submit').hide();
		}

		var operateType = $('#operateType').val();
		if (operateType === 'edit') {
			$("#ReportContentEnum003_btn_save").show();
			$("#ReportContentEnum003_btn_submit").hide();
		} else {
			$("#ReportContentEnum003_btn_save").hide();
			$("#ReportContentEnum003_btn_submit").show();
		}
		if (operateType == 'view') {
			// $('input').attr('disabled','disabled');
			$('input').attr("readonly", "readonly");
		}
	}
	;
	/**
	 * 获取查询参数
	 */
	var getSearchParams = function() {
		return {
			id : $.trim($('#id').val())
		}
	};
	var settedRule = $("#settedRule")
			.joyintable(
					{
						ajax : {
							url : basePath
									+ 'disclosure/information/config/reportcontentenum/ReportContentEnum003/findRulePage',
							type : 'POST',
							data : function() {
								return getSearchParams();
							}
						},
						checkbox : false,
						searchOnInit : true,
						loadonce : true,
						page : false,
						cellMinWidth : 100,
						height:250,
						cols : [ {
							field : 'ruleType1Name',
							title : '字段值',
							width : 100,
							fixed : true
						}, {
							field : 'ruleType2Name',
							title : '比较条件',
							width : 80,
							fixed : true
						}, {
							field : 'ruleType3Name',
							title : '值类型',
							width : 100,
							fixed : true
						}, {
							field : 'ruleType4Name',
							title : '值',
							width : 120,
							fixed : true
						}, {
							field : 'operation',
							width : 20,
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
		var obj = settedRule.getAllRows();
		var describe = "";
		var index = 0;
		for ( var i in obj) {
			if(obj[i].ruleType4Name.length!=0){
				describe += obj[i].ruleType1Name + obj[i].ruleType2Name
				+ obj[i].ruleType4Name + ";";
			}else{
				describe += obj[i].ruleType1Name + obj[i].ruleType2Name
				+ obj[i].ruleType3Name + ";";
			}
			
		}
		$('#indexDetail').val(describe);
	});
	/**
	 * 规则增加事件
	 * 
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
			
		} else if (rule.ruleType1 == 'PROD_TERM_TYPE') {
			if ($('#prodTermType').select2('data')[0]) {
				rule.ruleType4 = $('#prodTermType').select2('data')[0].id;
				rule.ruleType4Name = $('#prodTermType').select2('data')[0].text;
			} else {
				Joyin.alert("请选择产品期限类型", 7);
				return;
			}
			
		} else if (rule.ruleType1 == 'RISKTYPE') {
			if ($('#riskType').select2('data')[0]) {
				rule.ruleType4 = $('#riskType').select2('data')[0].id;
				rule.ruleType4Name = $('#riskType').select2('data')[0].text;
			} else {
				Joyin.alert("请选择投资者风险偏好", 7);
				return;
			}
			
		} else if (rule.ruleType1 == 'RISK_LEVEL') {
			if ($('#riskLevel').select2('data')[0]) {
				rule.ruleType4 = $('#riskLevel').select2('data')[0].id;
				rule.ruleType4Name = $('#riskLevel').select2('data')[0].text;
			} else {
				Joyin.alert("请选择产品风险评级", 7);
				return;
			}
			
		} else {
			if (rule.ruleType3 == '03') {
				// 平台日
				rule.ruleType4 = "";
				rule.ruleType4Name = "";
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
		var describe = "";
		var index = 0;
		for ( var i in obj) {
			if(obj[i].ruleType4Name.length!=0){
				describe += obj[i].ruleType1Name + obj[i].ruleType2Name
				+ obj[i].ruleType4Name + ";";
			}
			else{
				describe += obj[i].ruleType1Name + obj[i].ruleType2Name
				+ obj[i].ruleType3Name + ";";
			}
			
			if (obj[i].ruleType1 == rule.ruleType1
					&& obj[i].ruleType2 == rule.ruleType2
					&& obj[i].ruleType3 == rule.ruleType3
					&& obj[i].ruleType4 == rule.ruleType4) {
				index++;
			}
		}
		if (index == 0) {
			if(rule.ruleType4.length!=0){
				settedRule.addRow(rule);
				describe = describe + rule.ruleType1Name + rule.ruleType2Name
						+ rule.ruleType4Name+ ";"
				$('#indexDetail').val(describe);
			}
			else{
				settedRule.addRow(rule);
				describe = describe + rule.ruleType1Name + rule.ruleType2Name
						+ rule.ruleType3Name+ ";"
				$('#indexDetail').val(describe);
			}
			
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
			$('#riskTypeSelect').hide();
			$('#riskLevelSelect').hide();
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
			$('#riskTypeSelect').hide();
			$('#riskLevelSelect').hide();
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
			$('#riskTypeSelect').hide();
			$('#riskLevelSelect').hide();
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
			$('#riskTypeSelect').hide();
			$('#riskLevelSelect').hide();
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
			$('#riskTypeSelect').hide();
			$('#riskLevelSelect').hide();
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
			$('#riskTypeSelect').hide();
			$('#riskLevelSelect').hide();
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
			$('#riskTypeSelect').hide();
			$('#riskLevelSelect').hide();
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
			$('#riskTypeSelect').hide();
			$('#riskLevelSelect').hide();
		} else if(ruleType1 == 'PROD_TERM_TYPE'){
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
			$('#riskTypeSelect').hide();
			$('#riskLevelSelect').hide();
		}else if(ruleType1 == 'RISKTYPE'){
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
			$('#riskTypeSelect').show();
			$('#riskLevelSelect').hide();
		}else if(ruleType1 == 'RISK_LEVEL'){
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
			$('#riskTypeSelect').hide();
			$('#riskLevelSelect').show();
		}else {
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
				$('#riskTypeSelect').hide();
				$('#riskLevelSelect').hide();
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
				$('#riskTypeSelect').hide();
				$('#riskLevelSelect').hide();
			}
		}
	}
	function ruleType3Change() {
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
			$('#riskTypeSelect').hide();
			$('#riskLevelSelect').hide();
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
			$('#riskTypeSelect').hide();
			$('#riskLevelSelect').hide();
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
			$('#riskTypeSelect').hide();
			$('#riskLevelSelect').hide();
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
			$('#riskTypeSelect').hide();
			$('#riskLevelSelect').hide();
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
			$('#riskTypeSelect').hide();
			$('#riskLevelSelect').hide();
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
			$('#riskTypeSelect').hide();
			$('#riskLevelSelect').hide();
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
			$('#riskTypeSelect').hide();
			$('#riskLevelSelect').hide();
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
			$('#riskTypeSelect').hide();
			$('#riskLevelSelect').hide();
		}else if (ruleType1 == 'PROD_TERM_TYPE') {
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
			$('#riskTypeSelect').hide();
			$('#riskLevelSelect').hide();
		}else if (ruleType1 == 'RISKTYPE') {
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
			$('#riskTypeSelect').show();
			$('#riskLevelSelect').hide();
		}else if (ruleType1 == 'RISK_LEVEL') {
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
			$('#riskTypeSelect').hide();
			$('#riskLevelSelect').show();
		}else {
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
				$('#riskTypeSelect').hide();
				$('#riskLevelSelect').hide();
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
				$('#riskTypeSelect').hide();
				$('#riskLevelSelect').hide();
			}
		}
	}

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
		$('#riskTypeSelect').hide();
		$('#riskLevelSelect').hide();
		// 规则增加事件
		$('#ReportContentEnum003_btn_add').click(addrule);
		// 规则1改变事件
		$('#ruleType1').change(ruleType1Change);
		// 规则3改变事件
		$('#ruleType3').change(ruleType3Change);
		$('#indexType').change(function() {
			gradeChange();
		});
		// 绑定按钮事件
		$('#ReportContentEnum003_btn_save').click(function() {
			saveOrSubmit('save');
		});
		$('#ReportContentEnum003_btn_submit').click(function() {
			saveOrSubmit('submit');
		});

		 btnShow(); 
		/* fileShow(); */
	});
});

var FileCommon = {
	onUploaded : function(res) {
		if (res && res.length > 0) {
			Joyin.ajax('uploadFile', {
				fileName : res[0].fileName,
				fileNameOnly : res[0].srcName
			}, function(result) {
				fileShow(res[0].srcName, res[0].fileNo);
				upFileName = res[0].srcName;
				$('#indexFile').val(res[0].fileNo);
			});
		}
	}
};

var fileShow = function(fileName, fileNo) {
	if (fileName && fileNo) {
		$('#uploadFile').html(fileName);
		$('#uploadFile').attr('href',
				JoyinUtil.getRealPath('/framework/download/' + fileNo));
	} else {
		if ($('#indexType').val() == '02') {
			$('#uploadFile').html($('#indexValue').val());
			$('#uploadFile').attr(
					'href',
					JoyinUtil.getRealPath('/framework/download/'
							+ $('#indexFile').val()));
		}
	}
}
