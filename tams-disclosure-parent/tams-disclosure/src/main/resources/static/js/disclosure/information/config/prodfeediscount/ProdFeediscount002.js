/**
 * 交易商新建
 */
$(function() {


	var prodDiscountId = $('#prodDiscountId').val();
	var flag = false;
	if ('' == prodDiscountId || null == prodDiscountId) {
		$("#prodName").attr("disabled", "disabled");

	} else {
		//修改页面
		$("#prodRegistNo").attr("disabled", "disabled");
		$("#prodId").attr("disabled", "disabled");
	}

	/**
	 * 保存或提交
	 * 
	 * @param operateType
	 *            操作类型（save，submit）
	 * @returns
	 */
	function saveOrSubmit(operateType) {
		var object = $.serializeToObject('#submitForm');
		// if(!checkInput(object)){
		// 	return;
		// }
		object.operateType = operateType;
		object.pageModel = pageModel;
		if (pageModel == PAGE_MODEL_NEW) {
			object.operateType = 'new';
		} else if (pageModel == PAGE_MODEL_EDIT) {
			object.operateType = 'update';
		}
		 Joyin.ajaxJson(basePath + 'disclosure/information/config/prodfeediscount/ProdFeediscount002/check',
                 object, function(result) {
                     Joyin.confirm(result.data, function() {
                         if (operateType === 'save') {
                             WorkFlow.doSaveList(basePath + 'disclosure/information/config/prodfeediscount/ProdFeediscount002/save',
                                     object, "ProdFeediscount002_btn_save");
                         } else {
                             WorkFlow.doSubmitList(basePath  + 'disclosure/information/config/prodfeediscount/ProdFeediscount002/save',
                                     object, "ProdFeediscount002_btn_submit");
                         }
                     });
         });
	}


	/**
	 * 输入有效性验证
	 */
	var checkInput = function(obj) {
		// if (obj.customerId == null || $.trim(obj.customerId) == "") {
		// 		Joyin.error("交易商名称不可为空!");
		// 		return false;
		// }
		// if (obj.tradeMarket == null || $.trim(obj.tradeMarket) == "") {
		// 		Joyin.error("交易场所不可为空!");
		// 		return false;
		// }
		// if (obj.dealerNo == null || $.trim(obj.dealerNo) == "") {
		// 		Joyin.error("交易商代码不可为空!");
		// 		return false;
		// }
		return true;
	}

	/**
	 * 页面初始化
	 */
	var init = function() {

	};

	$('#startDate').change(function(){
		changeDate();
	});
	$('#endDate').change(function(){
		changeDate();
	});

	// 修改 开始时间
	function changeDate(){
		var startDate = $('#startDate').val();
		var endDate = $('#endDate').val();
		// if (startDate && endDate){
		// 	var days = (Date.ofDate(endDate) - Date.ofDate(startDate))/(24*60*60*1000);
		// 	if(days<0){
		// 		Joyin.error(JoyinUtil.getMessage("优惠截至时间必须大于优惠开始时间", JoyinUtil.getLabel("ProdFeediscount002.endDate")));
		// 		$('#endDate').val('');
		// 		return;
		// 	}
		// }
	}

	/**
	 * 画面加载完成
	 */
	$(function() {
		init();
		// 绑定按钮事件
		$('#ProdFeediscount002_btn_save').click(function() {
			saveOrSubmit('save');
		});


		// 绑定按钮事件
		$('#ProdFeediscount002_btn_submit').click(function() {
			saveOrSubmit('submit');
		});

		$('#prodRegistNo').prop("disabled",true);
		if (pageModel == PAGE_MODEL_NEW){
			$('#prodId').change(function () {
				var prodId = $('#prodId').val();
				var data = $('#prodId').select2("data")[0].object;
				$('#prodRegistNo').textSelect('setValue',data);
				var prodName = $('#prodId').select2("data")[0].text;
				$('#prodName').val(prodName);
			});
		}
	});
}());