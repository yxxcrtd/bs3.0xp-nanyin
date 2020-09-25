(function() {
	/**
	 * 获取画面元素
	 */
	function getElementById(id) {
		return $('#' + id);
	}
	/**
	 * 保存或提交按钮执行事件
	 */
  var onSaveOrSubmitAction = function(operateType) {
		var object = $.serializeToObject('#AssetClassManagement002_Form');
		debugger;
		object.operateType = operateType;
		object.pageModel = pageModel;
		Joyin
				.ajaxJson(
						basePath
								+ 'invest/product/productplanning/assetclassmanagement/AssetClassManagement002/check',
						object,
						function(result) {
							Joyin
									.confirm(
											result.data,
											function() {
												debugger;
												if (operateType === 'save') {
													WorkFlow
															.doSaveList(
																	basePath
																			+ 'invest/product/productplanning/assetclassmanagement/AssetClassManagement002/save',
																	object,
																	"AssetClassManagement002_btn_save");
												} else {
													WorkFlow
															.doSubmitList(
																	basePath
																			+ 'invest/product/productplanning/assetclassmanagement/AssetClassManagement002/save',
																	object,
																	"AssetClassManagement002_btn_submit");
												}
											})
						})
	};
	// 资产二、三、四、五级是否置灰
  if (pageModel == PAGE_MODEL_EDIT) {
		$('#customAssetTypeTwo').prop('disabled', true);
		$('#customAssetTypeThree').prop('disabled', true);
		$('#customAssetTypeFour').prop('disabled', true);
		$('#customAssetTypeFive').prop('disabled', true);
	} else if (pageModel == PAGE_MODEL_NEW) {
		$('#customAssetTypeTwo').removeAttr("disabled");
		$('#customAssetTypeThree').removeAttr("disabled");
		$('#customAssetTypeFour').removeAttr("disabled");
		$('#customAssetTypeFive').removeAttr("disabled");

	}
	
/*	var customAssetTypeFive = getElementById('customAssetTypeFive').val();

	getElementById('customAssetTypeFive').change(function() {
		feeNameChangeAction();
	});

	 $('#customAssetTypeFive').change(function() { 
	function feeNameChangeAction() {
		if ($("#customAssetTypeTwo").val() != null) {
			if ($("#customAssetTypeThree").val() != null) {
				if ($("#customAssetTypeFour").val() != null) {

					if ($("#customAssetTypeTwo").val() === '05'
							|| $("#customAssetTypeThree").val === '22') {
						$("#customAssetTypeFive").val('01').trigger("change");
					}

				}
			}

		}

	}*/

	/*
	 * var assetTypeChange = function(){ if( $('customAssetTypeThree').val) };
	 */

	/**
	 * 初始化
	 */
  $(function() {
		// 保存按钮执行事件
		$('#AssetClassManagement002_btn_save').click(function() {
			onSaveOrSubmitAction('save');
		});
		// 提交按钮执行事件
		$('#AssetClassManagement002_btn_submit').click(function() {
			onSaveOrSubmitAction('submit');
		});

	});

}());
