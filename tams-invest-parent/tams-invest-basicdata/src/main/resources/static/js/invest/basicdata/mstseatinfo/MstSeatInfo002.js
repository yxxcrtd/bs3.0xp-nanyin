/**
 * 营业部信息新建
 */
$(function() {
	/**
	 * 保存或提交
	 * 
	 * @param operateType
	 *            操作类型（save，submit）
	 * @returns
	 */
	function saveOrSubmit(operateType) {
		var object = $.serializeToObject('#mstSeatInfoForm');
		if(!checkInput(object)){
			return;
		}
		object.operateType = operateType;
		object.pageModel = pageModel;
		if (pageModel == PAGE_MODEL_NEW) {
			object.operateType = 'new';
		} else if (pageModel == PAGE_MODEL_EDIT) {
			object.operateType = 'update';
		}
		 Joyin.ajaxJson(basePath + 'invest/basicdata/mstseatinfo/MstSeatInfo002/check',
                 object, function(result) {
                     Joyin.confirm(result.data, function() {
                         if (operateType === 'save') {
                             WorkFlow.doSaveList(basePath + 'invest/basicdata/mstseatinfo/MstSeatInfo002/save',
                                     object, "MstSeatInfo002_btn_save");
                         } else {
                             WorkFlow.doSubmitList(basePath  + 'invest/basicdata/mstseatinfo/MstSeatInfo002/save',
                                     object, "MstSeatInfo002_btn_submit");
                         }
                     });
         });
	}


	/**
	 * 输入有效性验证
	 */
	var checkInput = function(obj) {
		if (obj.customerId == null || $.trim(obj.customerId) == "") {
				Joyin.error("券商代码不可为空!");
				return false;
		}
		if (obj.tradeMarket == null || $.trim(obj.tradeMarket) == "") {
				Joyin.error("交易市场不可为空!");
				return false;
		}
		if (obj.seatId == null || $.trim(obj.seatId) == "") {
				Joyin.error("席位号不可为空!");
				return false;
		}
		return true;
	}

	/**
	 * 页面初始化
	 */
	var init = function() {
		console.log(pageModel);
		$('#customerName').attr("readonly",true);
		if (pageModel == PAGE_MODEL_EDIT) {
			$("#seatId").attr("disabled", "disabled");
		}
	};
	
	/**
	 * 根据证券/期货公司名称查询对应的交易场所
	 */
	var getTradeMarketBybrokerId = function( customerId){
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstseatinfo/MstSeatInfo002/getTradeMarketBybrokerId?brokerId=' + customerId, null,
                function(result) {
	                if (result != null && result != '' && result.length > 0) {
		                var data = result[0];
		                var isBroker = data.isBroker != undefined ? data.isBroker : null;
		                var isFutureCom = data.isFutureCom != undefined ? data.isFutureCom : null;
		                console.log("isBroker:"+isBroker);
		                console.log("isFutureCom:"+isFutureCom);
	                }else{
	                }
                });
		
	}
	
	
	/**
	 * 券商下拉框选择改变事件
	 */
	$('#customerId').change(
	        function() {
		        var customerId = $('#customerId').val();
		        if (customerId != undefined && customerId != null && customerId != ""){
		        	 Joyin.ajaxJson(basePath + 'invest/basicdata/mstseatinfo/MstSeatInfo002/getMstCustomerInfo?brokerId=' + customerId, null,
				                function(result) {
					                if (result != null && result != '' && result.length > 0) {
						                var data = result[0];
						                var customerName = data.id != undefined ? data.id : null;
						                $('#customerName').val(customerName);
					                }else{
					                	$('#customerName').val(null);
					                }
				                });
			        }else{
			        	$('#customerName').html(null);
			        }
	        });
	
	/**
	 * 画面加载完成
	 */
	$(function() {
		init();
		// 绑定按钮事件
		$('#MstSeatInfo002_btn_save').click(function() {
			saveOrSubmit('save');
		});
		// 绑定按钮事件
		$('#MstSeatInfo002_btn_submit').click(function() {
			saveOrSubmit('submit');
		});
	});
}());