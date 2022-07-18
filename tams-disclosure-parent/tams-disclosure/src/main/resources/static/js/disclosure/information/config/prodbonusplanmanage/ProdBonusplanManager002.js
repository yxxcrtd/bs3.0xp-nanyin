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
			basePath+ 'disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager002/check',object,
			function(result) {
				Joyin.confirm(
					result.data,
					function() {
						Joyin.ajax(basePath + 'disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager002/insert',
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


	function prodIdChange() {
		//var prodId = $('#prodId').select2('data')[0].id;
		// console.log($('#prodId').select2('data'));
		var  prodName = $('#prodId').select2('data')[0].text;
		$('#prodName').val(prodName);
	}

	// 修改 收益分配基准日
	function changeSettDate(){
		var settDate = $('#settDate').val();
		var paymentDate = $('#paymentDate').val();
		if (settDate < platDate){
			Joyin.error('到期日小于平台日，无法修改到期日！', function () {
				// table.renderRow(data.LAY_TABLE_INDEX);
				return;
			});
		}

		if (paymentDate && settDate){
			var days = (Date.ofDate(paymentDate) - Date.ofDate(settDate))/(24*60*60*1000);
			if(days<0){
				Joyin.error(JoyinUtil.getMessage("现金红利发放日必须小于收益分配基准日", JoyinUtil.getLabel("ProdBonusplanManager001.settDate")));
				$('#paymentDate').val('');
				return;
			}
		}
	}

	function changePaymentDate(){
		var settDate = $('#settDate').val();
		var paymentDate = $('#paymentDate').val();
		console.log(platDate);
		if (settDate < platDate){
			Joyin.error('到期日小于平台日，无法修改到期日！', function () {
				// table.renderRow(data.LAY_TABLE_INDEX);
				return;
			});
		}
		if (paymentDate && settDate){
			var days = (Date.ofDate(paymentDate) - Date.ofDate(settDate))/(24*60*60*1000);
			if(days<0){
				Joyin.error(JoyinUtil.getMessage("现金红利发放日必须小于收益分配基准日", JoyinUtil.getLabel("ProdBonusplanManager001.settDate")));
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
		$('#prodId').change(prodIdChange);
		// 绑定按钮事件
		$('#ProdBonusplanManager002_btn_save').click(function() {
			saveOrSubmit('save');
		});
		$('#ProdBonusplanManager002_btn_submit').click(function() {
			saveOrSubmit('submit');
		});


	});
});

