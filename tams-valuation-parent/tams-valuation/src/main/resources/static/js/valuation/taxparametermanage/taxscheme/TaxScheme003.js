$(function () {

	/**
	 * 修改/新建
	 */
	var saveOrSubmit = function(operateType) {
		var object = $.serializeToObject('#dataForm');
		object.pageModel = pageModel;
		object.operateType = operateType;
		
		Joyin.ajaxJson(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme003/check', object, function(result) {
		    Joyin.confirm(result.data, function() {
		    	if(operateType === 'save'){
				    WorkFlow.doSaveList(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme003/save', 
								object, "TaxScheme003_btn_save");
				}else{// submit
					WorkFlow.doSubmitList(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme003/save', 
							object, "TaxScheme003_btn_submit");
				}
		    });
		});
	};
	
	
	/**
	 * 根据操作类型：显示按钮等
	 */
    var initShow = function () {
        var operateType = $('#operateType').val();
        if(operateType === 'edit') {
        	 $("#assetClassify").attr("disabled", "disabled");
        	 $("#capiIncomeFeature").attr("disabled", "disabled");
        	 $('#incomeType').next().addClass('disabled').find('li').off('click');
        }
        
        if(pageModel == PAGE_MODEL_REFERENCE){
        	$('#incomeType').next().addClass('disabled').find('li').off('click');
        	$('#taxableSign').next().addClass('disabled').find('li').off('click');
        }
        
        var schemeId = $("#schemeId").val();
        if(schemeId != null && schemeId != ''){
        	$("#schemeId").attr("disabled", "disabled");
        }

    };
    

	
    $(function() {
        // 绑定按钮事件
        $('#TaxScheme003_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TaxScheme003_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        				
        
        initShow();// 根据操作类型：显示按钮等
        
    	
    });
});