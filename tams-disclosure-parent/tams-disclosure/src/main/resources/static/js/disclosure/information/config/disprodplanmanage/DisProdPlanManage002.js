var upFile;
var upFileName;

// 产品成立日
var prodSetDate;
// 募集方式：不区分母子产品 01 公募/02 私募
var raiseType ;
// 发行方式：无需区分母子产品 01 开放式/02 封闭式
var issuingWay ;
// 是否是客户周期型产品：不区分母子产品 01 是/02 否
var isPeriod ;



var laydate = layui.laydate;
//年月选择器
/*laydate.render({
	elem: '#openStartDate'
	,min: '2022-3-1'
	,max: '2022-6-31'
})*/

$(function() {

	var prodPlanId = $('#prodPlanId').val();
	var flag = false;
	if ('' == prodPlanId || null == prodPlanId) {

	} else {
		//修改页面
		// $("#prodRegistNo").attr("disabled", "disabled");
		$("#finprodId").attr("disabled", "disabled");
	}

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

		object.raiseType = raiseType;
		object.issuingWay = issuingWay;
		object.isPeriod = isPeriod;
		/*Joyin.ajaxJson(
			basePath+ 'disclosure/information/config/disprodplanmanage/DisProdPlanManage002/check',object,
			function(result) {
				Joyin.confirm(
					result.data,
					function() {
						Joyin.ajax(basePath + 'disclosure/information/config/disprodplanmanage/DisProdPlanManage002/insert',
							{
								pageModel : pageModel,
								object : JSON.stringify(object)
							},
							function(result) {
								var index = parent.layer.getFrameIndex(window.name);
								parent.layer.close(index);
							});
					});

			});*/
		if (pageModel == PAGE_MODEL_NEW) {
			object.operateType = 'new';
		} else if (pageModel == PAGE_MODEL_EDIT) {
			object.operateType = 'update';
		}
		Joyin.ajaxJson(basePath + 'disclosure/information/config/disprodplanmanage/DisProdPlanManage002/check',
			object, function(result) {
				Joyin.confirm(result.data, function() {
					if (operateType === 'save') {
						WorkFlow.doSaveList(basePath + 'disclosure/information/config/disprodplanmanage/DisProdPlanManage002/save',
							object, "DisProdPlanManage002_btn_save");
					} else {
						WorkFlow.doSubmitList(basePath  + 'disclosure/information/config/disprodplanmanage/DisProdPlanManage002/save',
							object, "DisProdPlanManage002_btn_submit");
					}
				});
			});

	};


	/**
	 * 获取查询参数
	 */
	var getSearchParams = function() {
		return {
			id : $.trim($('#id').val())
		}
	};


	function finprodIdChange() {
		var finprodId = $('#finprodId').select2('data')[0].id;
		$('#prodId').val(finprodId);

		/*var prodName = $('#finProdId').select2('data')[0].id;
		var prodId = $('#finProdId').select2('data')[0].text;
		$('#prodName').val(prodName);
		$('#prodId').val(prodId);*/

		Joyin.ajax(basePath + 'disclosure/information/config/disprodplanmanage/DisProdPlanManage002/getPrdInfo',
			{
				"finprodId":finprodId
			},
			function(result) {
				if(result.setDate == null){
					return '';
				}else{
					prodSetDate= new Date(result.setDate).format("yyyy-MM-dd");
					raiseType = result.raiseType ;
					issuingWay = result.issuingWay ;
					isPeriod = result.isPeriod ;
					// 修改 开放开始日
					changeOpenStartDate();
					// 修改 产品冷静期
					changeCollingoffPeriod();
					// 修改 运作起始日
					changeOperationStartDate();
					// 修改 自动赎回日
					changeAutoRedeemDate();
					// 修改 客户实际持有天数状态
					changeHoldDays();
				}
				if(pageModel == PAGE_MODEL_EDIT){
					if($('#openStartDate').val() != '' && $('#openStartDate').val() != null
						&& $('#openEndDate').val() != '' && $('#openEndDate').val() != null
						&& $('#operationStartDate').val() != '' && $('#operationStartDate').val() != null){
						if($('#openStartDate').val() < platDate
							&& $('#openEndDate').val() > platDate
							&& $('#operationStartDate').val() > platDate){
							$("#openStartDate").attr("disabled",true);
						}else if($('#openEndDate').val() < platDate
							&& $('#operationStartDate').val() > platDate){
							$("#openStartDate").attr("disabled",true);
							$("#openEndDate").attr("disabled",true);
						}else if($('#operationStartDate').val() < platDate){
							$("#openStartDate").attr("disabled",true);
							$("#openEndDate").attr("disabled",true);
							$("#operationStartDate").attr("disabled",true);
							$("#collingoffPeriod").attr("disabled",true);
						}
					}
					if($('#autoRedeemDate').val() != '' && $('#autoRedeemDate').val() != null){
						if($('#autoRedeemDate').val() < platDate){
							$("#openStartDate").attr("disabled",true);
							$("#openEndDate").attr("disabled",true);
							$("#operationStartDate").attr("disabled",true);
							$("#collingoffPeriod").attr("disabled",true);
							$("#autoRedeemDate").attr("disabled",true);
							$("#holdDays").attr("disabled",true);
						}
					}
				}
			});

	}

	// 修改 开放开始日
	function changeOpenStartDate(){
		var openStartDate = $('#openStartDate').val();
		var openEndDate = $('#openEndDate').val();
		if(prodSetDate != null){
			if (openStartDate && prodSetDate){
				var days = (Date.ofDate(openStartDate) - Date.ofDate(prodSetDate))/(24*60*60*1000);
				if(days<0){
					Joyin.error(JoyinUtil.getMessage("开放开始日必须小于"+prodSetDate, JoyinUtil.getLabel("DisProdPlanManage002.prodSetDate")));
					$('#openStartDate').val('');
				}
			}
		}

		if (openStartDate && openEndDate){
			var days = (Date.ofDate(openEndDate) - Date.ofDate(openStartDate))/(24*60*60*1000);
			if(days<0){
				Joyin.error(JoyinUtil.getMessage("开放结束日不能小于开放开始日", JoyinUtil.getLabel("DisProdPlanManage002.openEndDate")));
				$('#openEndDate').val('');
			}
		}
	}
	// 修改 开放结束日
	function changeOpenEndDate(){
		var openStartDate = $('#openStartDate').val();
		var openEndDate = $('#openEndDate').val();
		if (openStartDate && openEndDate){
			var days = (Date.ofDate(openEndDate) - Date.ofDate(openStartDate))/(24*60*60*1000);
			if(days<0){
				Joyin.error(JoyinUtil.getMessage("开放结束日必须小于开放开始日", JoyinUtil.getLabel("DisProdPlanManage002.openEndDate")));
				$('#openEndDate').val('');
			}
		}
	}

	// 修改 产品冷静期
	function changeCollingoffPeriod(){
		// 若募集方式为“私募”必填，否则置灰不允许填写；
		if(raiseType == '02'){ //私募
			var collingoffPeriod = $('#collingoffPeriod').val();

			if(prodSetDate != null){
				if (collingoffPeriod && prodSetDate){
					var days = (Date.ofDate(collingoffPeriod) - Date.ofDate(prodSetDate))/(24*60*60*1000);
					if(days<0){
						Joyin.error(JoyinUtil.getMessage("产品冷静期必须大于"+prodSetDate, JoyinUtil.getLabel("DisProdPlanManage002.prodSetDate")));
						$('#collingoffPeriod').val('');
					}
				}
			}
			$('#collingoffPeriod').attr("disabled",false);

		}else{ //公募
			$('#collingoffPeriod').attr("disabled",true);
		}
		// if(pageModel == PAGE_MODEL_EDIT){
		// 	if($('#operationStartDate').val() < platDate){
		// 		$("#openStartDate").attr("disabled",true);
		// 		$("#openEndDate").attr("disabled",true);
		// 		$("#operationStartDate").attr("disabled",true);
		// 		$("#collingoffPeriod").attr("disabled",true);
		// 	}
		// }

	}

	// 修改 运作起始日
	function changeOperationStartDate(){
		var operationStartDate = $('#operationStartDate').val();
		if(prodSetDate != null){
			if (operationStartDate && prodSetDate){
				var days = (Date.ofDate(operationStartDate) - Date.ofDate(prodSetDate))/(24*60*60*1000);
				if(days<0){
					Joyin.error(JoyinUtil.getMessage("运作起始日必须大于"+prodSetDate, JoyinUtil.getLabel("DisProdPlanManage002.prodSetDate")));
					$('#operationStartDate').val('');
				}
			}
		}
	}

	// 修改 自动赎回日
	function changeAutoRedeemDate(){
		// 当“是否是开放周期型产品”为“是”时必填，否则置灰不可填，且不能小于该条数据的运作起始日
		if(issuingWay !=null && issuingWay =='01' && isPeriod !=null && isPeriod =='01' ){
			var autoRedeemDate = $('#autoRedeemDate').val();
			if(prodSetDate != null){
				if (autoRedeemDate && prodSetDate){
					var days = (Date.ofDate(autoRedeemDate) - Date.ofDate(prodSetDate))/(24*60*60*1000);
					if(days<0){
						Joyin.error(JoyinUtil.getMessage("自动赎回日必须大于"+prodSetDate, JoyinUtil.getLabel("DisProdPlanManage002.prodSetDate")));
						$('#autoRedeemDate').val('');
					}
				}
			}
			$('#autoRedeemDate').attr("disabled",false);
		}else{
			$('#autoRedeemDate').val('');
			$('#autoRedeemDate').attr("disabled",true);
		}
		// if(pageModel == PAGE_MODEL_EDIT){
		// 	if($('#autoRedeemDate').val() != '' && $('#autoRedeemDate').val() != null){
		// 		if($('#autoRedeemDate').val() < platDate){
		// 			$("#openStartDate").attr("disabled",true);
		// 			$("#openEndDate").attr("disabled",true);
		// 			$("#operationStartDate").attr("disabled",true);
		// 			$("#collingoffPeriod").attr("disabled",true);
		// 			$("#autoRedeemDate").attr("disabled",true);
		// 			$("#holdDays").attr("disabled",true);
		// 		}
		// 	}
		// }
	}

	// 修改 客户实际持有天数
	function changeHoldDays(){
		debugger;
		// 当“是否是开放周期型产品”为“是”时必填，否则置灰不可填，且不能小于该条数据的运作起始日
		if(issuingWay !=null && issuingWay =='01' && isPeriod !=null && isPeriod =='01' ){
			$('#holdDays').attr("disabled",false);
		}else{
			$('#holdDays').val('');
			$('#holdDays').attr("disabled",true);
		}
		// if(pageModel == PAGE_MODEL_EDIT){
		// 	if($('#autoRedeemDate').val() != '' && $('#autoRedeemDate').val() != null){
		// 		if($('#autoRedeemDate').val() < platDate){
		// 			$("#openStartDate").attr("disabled",true);
		// 			$("#openEndDate").attr("disabled",true);
		// 			$("#operationStartDate").attr("disabled",true);
		// 			$("#collingoffPeriod").attr("disabled",true);
		// 			$("#autoRedeemDate").attr("disabled",true);
		// 			$("#holdDays").attr("disabled",true);
		// 		}
		// 	}
		// }
	}

	$('#openStartDate').change(function(){
		changeOpenStartDate();
	});
	$('#openEndDate').change(function(){
		changeOpenEndDate();
	});

	$('#openStartDate').change(function(){
		changeOpenStartDate();
	});
	$('#openEndDate').change(function(){
		changeOpenEndDate();
	});
	$('#collingoffPeriod').change(function(){
		changeCollingoffPeriod();
	});
	$('#operationStartDate').change(function(){
		changeOperationStartDate();
	});
	$('#autoRedeemDate').change(function(){
		changeAutoRedeemDate();
	});


	$(function() {
		// 规则1改变事件
		$('#finprodId').change(finprodIdChange);
		// 绑定按钮事件
		$('#DisProdPlanManage002_btn_save').click(function() {
			saveOrSubmit('save');
		});
		$('#DisProdPlanManage002_btn_submit').click(function() {
			saveOrSubmit('submit');
		});

		var finprodId = $('#finprodId').val();
		if(finprodId !=null && finprodId !='' && finprodId.length>0){
			finprodIdChange();
		}
		if(pageModel == PAGE_MODEL_EDIT){
			if($('#openStartDate').val() != '' && $('#openStartDate').val() != null
				&& $('#openEndDate').val() != '' && $('#openEndDate').val() != null
				&& $('#operationStartDate').val() != '' && $('#operationStartDate').val() != null){
				if($('#openStartDate').val() < platDate
					&& $('#openEndDate').val() > platDate
					&& $('#operationStartDate').val() > platDate){
					$("#openStartDate").attr("disabled",true);
				}else if($('#openEndDate').val() < platDate
					&& $('#operationStartDate').val() > platDate){
					$("#openStartDate").attr("disabled",true);
					$("#openEndDate").attr("disabled",true);
				}else if($('#operationStartDate').val() < platDate){
					$("#openStartDate").attr("disabled",true);
					$("#openEndDate").attr("disabled",true);
					$("#operationStartDate").attr("disabled",true);
					$("#collingoffPeriod").attr("disabled",true);
				}
			}
			if($('#autoRedeemDate').val() != '' && $('#autoRedeemDate').val() != null){
				if($('#autoRedeemDate').val() < platDate){
					$("#openStartDate").attr("disabled",true);
					$("#openEndDate").attr("disabled",true);
					$("#operationStartDate").attr("disabled",true);
					$("#collingoffPeriod").attr("disabled",true);
					$("#autoRedeemDate").attr("disabled",true);
					$("#holdDays").attr("disabled",true);
				}
			}
		}
	});
});

