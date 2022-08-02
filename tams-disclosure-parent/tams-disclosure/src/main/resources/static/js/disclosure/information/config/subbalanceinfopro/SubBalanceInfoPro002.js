$(function() {
	/**
	 * 保存或提交
	 * 
	 * @param operateType
	 *            操作类型（save，submit）
	 * @returns
	 */
	function saveOrSubmit(operateType) {
		var object = $.serializeToObject('#submitForm');
		object.operateType = operateType;
		object.pageModel = pageModel;
		if (pageModel == PAGE_MODEL_NEW) {
			object.operateType = 'new';
		} else if (pageModel == PAGE_MODEL_EDIT) {
			object.operateType = 'update';
		}
		 // Joyin.ajaxJson(basePath + 'disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro002/check',
         //         object, function(result) {
         //             Joyin.confirm(result.data, function() {
         //                 if (operateType === 'save') {
         //                     WorkFlow.doSaveList(basePath + 'disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro002/save',
         //                             object, "SubBalanceInfoPro002_btn_save");
         //                 } else {
         //                     WorkFlow.doSubmitList(basePath  + 'disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro002/save',
         //                             object, "SubBalanceInfoPro002_btn_submit");
         //                 }
         //             });
         // });
		Joyin.ajaxJson(basePath + 'disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro002/check',object,function(result){
			if(result){
				Joyin.ajaxJson(basePath + 'disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro002/checkExistData', object, function(checkResult) {
					debugger;
					if(checkResult){
						object.operationType = 'modify';
						Joyin.confirm("有已删除的数据，是否覆盖", function () {
							if(operateType === 'save') {
								WorkFlow.doSaveList(basePath + 'disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro002/save', object, "SubBalanceInfoPro002_btn_save");
							}
							else {
								WorkFlow.doSubmitList(basePath + 'disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro002/save', object, "SubBalanceInfoPro002_btn_submit");
							}
						},{}, function () {
						});
					}else{
						if(operateType === 'save') {
							WorkFlow.doSaveList(basePath + 'disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro002/save', object, "SubBalanceInfoPro002_btn_save");
						}
						else {
							WorkFlow.doSubmitList(basePath + 'disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro002/save', object, "SubBalanceInfoPro002_btn_submit");
						}
					}
				})

			}
		})
	}


	/**
	 * 页面初始化
	 */
	var init = function() {
		$('#booksetId').change(function () {
			var prodId = $('#booksetId').val();
			var data = $('#booksetId').select2("data")[0].object;
			$('#prodName').val(data);
		});

		$('#balanceDate').change(function () {
            if ($('#balanceDate').val() > platDate){
            	Joyin.error("余额日期不能输入大于平台日"+"("+platDate+")"+"的日期！");
				$('#balanceDate').val('');
            	return;
			}
		});
		if (pageModel == PAGE_MODEL_EDIT){
			$('#booksetId').prop("disabled",true);
			$('#subjectNo').prop("disabled",true);
			$('#balanceDate').prop("disabled",true);
		}


	};
	/**
	 * 画面加载完成
	 */
	$(function() {
		init();
		// 绑定按钮事件
		$('#SubBalanceInfoPro002_btn_save').click(function() {
			saveOrSubmit('save');
		});


		// 绑定按钮事件
		$('#SubBalanceInfoPro002_btn_submit').click(function() {
			saveOrSubmit('submit');
		});
	});
}());