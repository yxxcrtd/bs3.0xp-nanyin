$(function() {

	var gradeChange = function() {
		btnShow();
	};

	/**
	 * 修改/新建
	 */
	var saveOrSubmit = function(operateType) {
		var object = $.serializeToObject('#submitForm');
		object.pageModel = pageModel;
		object.operateType = operateType;
		object.indexDetail = $('#indexDetail').val();
		var indexVal = $('#indexValue').val();
		indexVal = indexVal.replace(' ','&nbsp;');
		object.indexValue = indexVal;

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
//		var indexType = $('#indexType').val();
//		if (indexType === '01') {
//			$("#indexValue").show();
//			$("#test1").hide();
//			$('#ReportContentEnum003_btn_submit').show();
//		} else {
//			$("#test1").show();
//		//	$("#indexValue").hide();
//			$("#indexValue").show();
//			$('#ReportContentEnum003_btn_submit').hide();
//		}

		var operateType = $('#operateType').val();
		if (operateType === 'edit') {
			$("#ReportContentEnum003_btn_save").show();
			$("#ReportContentEnum003_btn_submit").hide();
		} else {
			$("#ReportContentEnum003_btn_save").hide();
			$("#ReportContentEnum003_btn_submit").show();
		}
		if (operateType == 'view') {
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
//	var settedRule = $("#settedRule")
//			.joyintable(
//					{
//						ajax : {
//							url : basePath
//									+ 'disclosure/information/config/reportcontentenum/ReportContentEnum003/findRulePage',
//							type : 'POST',
//							data : function() {
//								return getSearchParams();
//							}
//						},
//						checkbox : false,
//						searchOnInit : true,
//						loadonce : true,
//						page : false,
//						cellMinWidth : 100,
//						height:250,
//						cols : [ {
//							field : 'ruleType1Name',
//							title : '字段值',
//							width : 100,
//							fixed : true
//						}, {
//							field : 'ruleType2Name',
//							title : '比较条件',
//							width : 80,
//							fixed : true
//						}, {
//							field : 'ruleType3Name',
//							title : '值类型',
//							width : 100,
//							fixed : true
//						}, {
//							field : 'ruleType4Name',
//							title : '值',
//							width : 120,
//							fixed : true
//						}, {
//							field : 'operation',
//							width : 20,
//							title : 'SysCommon.operation',
//							type : 'action',
//							buttons : [ 'delt' ]
//						}, {
//							field : 'ruleType1',
//							visible : false
//						}, {
//							field : 'ruleType2',
//							visible : false
//						}, {
//							field : 'ruleType3',
//							visible : false
//						}, {
//							field : 'ruleType4',
//							visible : false
//						} ]
//					});
//	settedRule.bind('click', '.delt', function(data, index) {
//		if(data.ruleType1 == 'FINPROD_NAME'){
//			$('#finprodIds').data('finprodIds',null);
//		}
//		settedRule.removeRow(data);
//		var obj = settedRule.getAllRows();
//		var describe = "";
//		var index = 0;
//		for ( var i in obj) {
//			if(obj[i].ruleType4Name.length!=0){
//				describe += obj[i].ruleType1Name + obj[i].ruleType2Name
//				+ obj[i].ruleType4Name + ";";
//			}else{
//				describe += obj[i].ruleType1Name + obj[i].ruleType2Name
//				+ obj[i].ruleType3Name + ";";
//			}
//			
//		}
//		$('#indexDetail').val(describe);
//	});

	/**
	 * 规则增加事件
	 * @returns
	 */
	function addrule() {
			Joyin.dialog("产品选择",basePath+"disclosure/information/config/reportcontentenum/ReportContentEnum004/init","80%","80%",
				{},function () {
					var finprodIds = $('#finprodIds').data('finprodIds');
					if(finprodIds.length ==0){
						return;
					}
					$('#indexDetail').val(finprodIds);
				});
			return;
	}


	//隐藏全部下拉框
	function hideAllSeclt(){
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
		$("#investTypeSelect").hide();
		$("#openFreqSelect").hide();
		$('#issuingWaySelect').hide();
		$('#repProdTypeSelect').hide()
	}
	function ruleType1Change() {
		var ruleType1 = $('#ruleType1').select2('data')[0].id;
		var ruleType3;
		if ($('#ruleType3').val()) {
			ruleType3 = $('#ruleType3').select2('data')[0].id;
		}
		//先对选择框进行全部隐藏
		hideAllSeclt();
		$('#ruleType2Div').show();
		$('#ruleType3Div').show();
		if (ruleType1 == 'PROFIT_TYPE') {
			$('#profitTypeSelect').show();
		} else if (ruleType1 == 'RAISE_TYPE') {
			$('#raiseTypeSelect').show();
		} else if (ruleType1 == 'ISSUE_MODE') {
			$('#issueModeSelect').show();
		} else if (ruleType1 == 'ISSUE_STATUS') {
			$('#issueStautsSelect').show();
		} else if (ruleType1 == 'OPEN_MODE') {
			$('#openModeSelect').show();
		} else if (ruleType1 == 'IS_INDEPE_ACC' || ruleType1 == 'IS_INDEX' || ruleType1 == 'IS_PERIOD') {
			$('#isIndepeAccSelect').show();
		} else if (ruleType1 == 'PROD_TYPE') {
			$('#prodTypeSelect').show();
		} else if (ruleType1 == 'FINA_TYPE') {
			$('#finaTypeSelect').show();
		} else if(ruleType1 == 'PROD_TERM_TYPE'){
			$('#prodTermTypeSelect').show();
		}else if(ruleType1 == 'RISKTYPE'){
			$('#riskTypeSelect').show();
		}else if(ruleType1 == 'RISK_LEVEL'){
			$('#riskLevelSelect').show();
		} else if (ruleType1 == 'INVEST_TYPE'){
			//投资性质
			$("#investTypeSelect").show();
		} else if(ruleType1 == 'OPEN_FREQ'){
			$("#openFreqSelect").show();
		}  else if(ruleType1 == 'FINPROD_NAME'){
			$('#ruleType2Div').hide();
			$('#ruleType3Div').hide();
		} else if(ruleType1 == 'ISSUING_WAY'){
			$('#issuingWaySelect').show();
		}else if(ruleType1 == 'REP_PROD_TYPE'){
			$('#repProdTypeSelect').show();
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
		//先对选择框进行全部隐藏
		hideAllSeclt();
		if (ruleType1 == 'PROFIT_TYPE') {
			$('#profitTypeSelect').show();
		} else if (ruleType1 == 'RAISE_TYPE') {
			$('#raiseTypeSelect').show();
		} else if (ruleType1 == 'ISSUE_MODE') {
			$('#issueModeSelect').show();
		} else if (ruleType1 == 'ISSUE_STATUS') {
			$('#issueStautsSelect').show();
		} else if (ruleType1 == 'OPEN_MODE') {
			$('#openModeSelect').show();
		} else if (ruleType1 == 'IS_INDEPE_ACC' || ruleType1 == 'IS_INDEX' || ruleType1 == 'IS_PERIOD') {
			$('#isIndepeAccSelect').show();
		} else if (ruleType1 == 'PROD_TYPE') {
			$('#prodTypeSelect').show();
		} else if (ruleType1 == 'FINA_TYPE') {
			$('#finaTypeSelect').show();
		}else if (ruleType1 == 'PROD_TERM_TYPE') {
			$('#prodTermTypeSelect').show();
		}else if (ruleType1 == 'RISKTYPE') {
			$('#riskTypeSelect').show();
		}else if (ruleType1 == 'RISK_LEVEL') {
			$('#riskLevelSelect').show();
		} else if(ruleType1 == 'INVEST_TYPE'){
			$('#investTypeSelect').show();
		}else if(ruleType1 == 'OPEN_FREQ'){
			$("#openFreqSelect").show();
		}else if(ruleType1 == 'ISSUING_WAY'){
			$('#issuingWaySelect').show();
		} else if(ruleType1 == 'REP_PROD_TYPE'){
			$('#repProdTypeSelect').show();
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
//		$('#raiseTypeSelect').hide();
//		$('#chooseDateSelect').hide();
//		$('#profitTypeSelect').hide();
//		$('#issueModeSelect').hide();
//		$('#issueStautsSelect').hide();
//		$('#openModeSelect').hide();
//		$('#isIndepeAccSelect').hide();
//		$('#prodTypeSelect').hide();
//		$('#finaTypeSelect').hide();
//		$('#prodTermTypeSelect').hide();
//		$('#riskTypeSelect').hide();
//		$('#riskLevelSelect').hide();
//		$('#investTypeSelect').hide();
//		$('#openFreqSelect').hide();
//		$('#repProdTypeSelect').hide();
//		$('#ruletyped').hide();
		// 规则增加事件
		$('#ReportContentEnum003_btn_add').click(addrule);
		// 规则1改变事件
//		$('#ruleType1').change(ruleType1Change);
//		// 规则3改变事件
//		$('#ruleType3').change(ruleType3Change);
//		$('#indexType').change(function() {
//			gradeChange();
//		});
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
