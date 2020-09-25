(function () {
	
	var finProductCode = function(){
		
		var finProductCode = $('#finProductCode').val();
		
		if (finProductCode){
    		Joyin.ajax(basePath + 'invest/product/rddesign/prdrisklevel/PrdRiskLevel002/selectPageParam', {finProductCode : finProductCode}, function(result){
    			if (result){
    				
    				if(!result.riskLevel){
    					Joyin.alert("未查询到模拟交易，请检查数据！");
    					$("#finProductName").val("");
        				$("#riskLevel").val("").trigger('change');
        				$("#ajustRiskLevel").val("").trigger('change');
        				return;
    				}else{
    					$("#finProductName").val(result.finProductName);
        				$("#riskLevel").val(result.riskLevel).trigger('change');
        				$("#ajustRiskLevel").val(result.ajustRiskLevel).trigger('change');
    				}
    				
    			} else {
    				Joyin.alert("所选值为空！");
    				$("#finProductName").val("");
    				$("#riskLevel").val("").trigger('change');
    				$("#ajustRiskLevel").val("").trigger('change');
    				return;
    			}
    		})
    	}
	};

	// 加载初始数据
	var pageInit = function () {
		
		var seqNo = $("#seqNo").val();
		
		if(seqNo){ // 修改
			$("#finProductCode").attr("disabled","disabled");
		}
		
		$("#finProductCode").change(function(){
			finProductCode();
		});
	}
	
    function saveAction(){
        saveOrSubmitAction('save');
    }

    function submitAction(){
        saveOrSubmitAction('submit');
    }

    function saveOrSubmitAction(operateType){
        var object = $.serializeToObject('#PrdRiskLevel002_Form', false);
        object.operateType = operateType;
        
        Joyin.ajaxJson(basePath + "invest/product/rddesign/prdrisklevel/PrdRiskLevel002/check",object,function (result) {
            Joyin.confirm(result.data,function () {
                if(operateType === 'save'){
                    WorkFlow.doSaveList(basePath + "invest/product/rddesign/prdrisklevel/PrdRiskLevel002/insert",object,"PrdRiskLevel002_btn_save");
                }else {
                    WorkFlow.doSubmitList(basePath + "invest/product/rddesign/prdrisklevel/PrdRiskLevel002/insert",object,"PrdRiskLevel002_btn_submit");
                }
            })
        });
    }

    $(function () {
    	// 页面初始化，加载数据
    	pageInit();
        // 保存
        $('#PrdRiskLevel002_btn_save').click(saveAction);
        // 提交
        $('#PrdRiskLevel002_btn_submit').click(submitAction);
    });
}());