$(function () {

	/**
	 * 修改/新建
	 */
	var saveOrSubmit = function(operateType) {
		var object = $.serializeToObject('#schemeForm');
		object.pageModel = pageModel;
		object.operateType = operateType;
		
		Joyin.ajaxJson(basePath + 'valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme002/check', object, function(result) {
		    Joyin.confirm(result.data, function() {
		    	if(operateType === 'save'){
		    		object.operateType = "save";
				    WorkFlow.doSaveList(basePath + 'valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme002/save', 
								object, "ValuationMethodScheme002_btn_save");
				}else{// submit
					WorkFlow.doSubmitList(basePath + 'valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme002/save', 
							object, "ValuationMethodScheme002_btn_submit");
				}
		    });
		});
	};
	
	/**
	 * 根据操作类型：显示按钮
	 */
    function btnShow() {
        var operateType = $('#operateType').val();
        if(operateType === 'edit') {
        	 $("#schemeId").attr("disabled","disabled");
        }
        
        if(pageModel == PAGE_MODEL_REFERENCE){
        	$('#isdefault').next().addClass('disabled').find('li').off('click');
        }
        
        if(operateType == 'view'){
        	$('input').attr("readonly","readonly");
        }
    };
	
	
    $(function() {
        // 绑定按钮事件
        $('#ValuationMethodScheme002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#ValuationMethodScheme002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        
        
        btnShow();// 根据操作类型：显示按钮
    	
    });
});