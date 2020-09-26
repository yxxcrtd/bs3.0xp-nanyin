$(function () {

	/**
	 * 修改/新建
	 */
	var saveOrSubmit = function(operateType) {
		var object = $.serializeToObject('#schemeForm');
		object.pageModel = pageModel;
		if(operateType === 'save'){
            object.operateType = "save";
		}
		Joyin.ajaxJson(basePath + 'valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme002/check', object, function(result) {
		    Joyin.confirm(result.data, function() {
		        if(operateType === 'save'){
				    WorkFlow.doSubmitList(basePath + 'valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme002/doUpdate', 
								object, "ShadowPriceScheme002_btn_save");
				}else{// submit
					WorkFlow.doSubmitList(basePath + 'valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme002/save', 
							object, "ShadowPriceScheme002_btn_submit");
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
        	 $("#ShadowPriceScheme002_btn_save").show();
        	 $("#ShadowPriceScheme002_btn_submit").hide();
        	 $("#schId").attr("disabled","disabled");
        }else {
        	 $("#ShadowPriceScheme002_btn_save").hide();
        	 $("#ShadowPriceScheme002_btn_submit").show();
        }
        if(operateType == 'view'){
        	//$("#isDefault").attr("disabled","disabled");
        	$('input').attr("readonly","readonly");
        }
    };
	
	
    $(function() {
        // 绑定按钮事件
        $('#ShadowPriceScheme002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#ShadowPriceScheme002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        
        
        btnShow();// 根据操作类型：显示按钮
    	
    });
});