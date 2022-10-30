var upFile;
var upFileName;

// 产品成立日
var prodSetDate;
var raiseType ;
$(function() {
	var bonusPlanId = $('#bonusPlanId').val();
	if ('' == bonusPlanId || null == bonusPlanId) {

	} else {
		//修改页面
		$("#finProdId").attr("disabled", true);
		$("#bonusMode").attr("disabled", true);
		$("#settDate").attr("disabled", true);

	}


	var gradeChange = function() {
		btnShow();
	};

	/**
	 * 修改/新建
	 */
	var saveOrSubmit = function(operateType) {
		var object = $.serializeToObject('#submitForm');
		object.operateType = operateType;
		object.pageModel = pageModel;
		// if (pageModel == PAGE_MODEL_NEW) {
		// 	object.operateType = 'new';
		// } else if (pageModel == PAGE_MODEL_EDIT) {
		// 	object.operateType = 'update';
		// }
		Joyin.ajaxJson(basePath + 'disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager002/check',
			object, function(result) {
				Joyin.confirm(result.data, function() {
					if (operateType === 'save') {
						WorkFlow.doSaveList(basePath + 'disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager002/save',
							object, "ProdBonusplanManager002_btn_save");
					} else {
						WorkFlow.doSubmitList(basePath  + 'disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager002/save',
							object, "ProdBonusplanManager002_btn_submit");
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


	function prodIdChange() {
		var prodName = $('#finProdId').select2('data')[0].id;
		var prodId = $('#finProdId').select2('data')[0].text;
		$('#prodName').val(prodName);
		$('#prodId').val(prodId);
	}

	// 修改 收益分配基准日
	function changeSettDate(){
		var settDate = $('#settDate').val();
		var paymentDate = $('#paymentDate').val();
		if (settDate < platDate && pageModel == PAGE_MODEL_EDIT){
			// Joyin.error('到期日小于平台日，无法修改到期日！', function () {
			// 	// table.renderRow(data.LAY_TABLE_INDEX);
			// 	return;
			// });
		}

		if (paymentDate && settDate){
			var days = (Date.ofDate(paymentDate) - Date.ofDate(settDate))/(24*60*60*1000);
			if(days<0){
				Joyin.error(JoyinUtil.getMessage("现金红利发放日必须大于等于收益分配基准日", JoyinUtil.getLabel("ProdBonusplanManager001.settDate")));
				$('#paymentDate').val('');
				return;
			}
		}
	}

	function changePaymentDate(){
		var settDate = $('#settDate').val();
		var paymentDate = $('#paymentDate').val();
		if (settDate < platDate){
			// Joyin.error('收益分配基准日必须大于平台日', function () {
			// 	// table.renderRow(data.LAY_TABLE_INDEX);
			// 	$('#settDate').val('');
			// 	return;
			// });
		}
		if (paymentDate && settDate){
			var days = (Date.ofDate(paymentDate) - Date.ofDate(settDate))/(24*60*60*1000);
			if(days<0){
				Joyin.error(JoyinUtil.getMessage("现金红利发放日必须大于等于收益分配基准日", JoyinUtil.getLabel("ProdBonusplanManager001.settDate")));
				$('#paymentDate').val('');
				return;
			}
		}
	}

	$('#settDate').change(function(){
		changeSettDate();
	});
	$('#paymentDate').change(function(){
		changePaymentDate();
	});

	$('#achievement').change(function(){
		var  achievement = $('#achievement').val();
		if(achievement == '无'){
			$('#excessAchievementProp').val('0');
			$("#excessAchievementProp").attr("disabled", true );
		}else{
			$('#excessAchievementProp').val('');
			$("#excessAchievementProp").attr("disabled", false );
		}

	});


	$(function() {
		// 规则1改变事件
		$('#finProdId').change(prodIdChange);

		$('#settDate').change(function () {
			if ($('#settDate').val()<platDate){
                // Joyin.error("收益分配基准日需要大于等于平台日!");
				// $('#settDate').val('');
				// return;
			}
		});

		// 绑定按钮事件
		$('#ProdBonusplanManager002_btn_save').click(function() {
			saveOrSubmit('save');
		});
		$('#ProdBonusplanManager002_btn_submit').click(function() {
			saveOrSubmit('submit');
		});


	});
});

