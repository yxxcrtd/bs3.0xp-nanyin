var upFile;
var upFileName;

// 产品成立日
var prodSetDate;
var raiseType ;

var laydate = layui.laydate;
//年月选择器
/*laydate.render({
	elem: '#openStartDate'
	,min: '2022-3-1'
	,max: '2022-6-31'
})*/

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
					console.log(result);
					// 修改 开放开始日
					changeOpenStartDate();
					// 修改 产品冷静期
					changeCollingoffPeriod();
					// 修改 运作起始日
					changeOperationStartDate()
					// 修改 自动赎回日
					changeAutoRedeemDate()
					console.log(prodSetDate);
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
				Joyin.error(JoyinUtil.getMessage("开放结束日必须小于开放开始日", JoyinUtil.getLabel("DisProdPlanManage002.openEndDate")));
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


		}else{ //公募
			$('#collingoffPeriod').attr("disabled",true);
		}
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
		//if(raiseType == '02'){ //私募
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


		/*}else{ //公募

		}*/
	}


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


	});
});

