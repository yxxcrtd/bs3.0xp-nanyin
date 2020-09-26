$(function () {

	/**
	 * 修改/新建
	 */
	var saveOrSubmit = function(operateType) {
		var object = $.serializeToObject('#schemeForm');
		object.pageModel = pageModel;
		object.operateType = operateType;
		
		if($('#operateType').val() === 'copy') {
			if($("#copyRange").val() == null || $("#copyRange").val().length == 0){
				Joyin.error("复制范围不能为空！");
				return;
			}
		}
		
		
		Joyin.ajaxJson(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme002/check', object, function(result) {
		    Joyin.confirm(result.data, function() {
				var o_operateType = $('#operateType').val();
				if(o_operateType === 'copy' ){
					object.refSchId = $("#o_schId").val(); // 被拷贝 方案代码
					object.operateType = o_operateType;
				}
		    	
	    		if(operateType === 'save'){
				    WorkFlow.doSaveList(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme002/save', 
								object, "TaxScheme002_btn_save");
				}else{// submit
					WorkFlow.doSubmitList(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme002/save', 
							object, "TaxScheme002_btn_submit");
				}
		    });
		});
	};
	
	/**
	 * 根据操作类型：显示按钮
	 */
    function initShow() {
        var operateType = $('#operateType').val();
        if(operateType === 'edit') {
        	 $("#schId").attr("disabled","disabled");
        }
        
        if(pageModel == PAGE_MODEL_REFERENCE){
        	$('#isDefault').next().addClass('disabled').find('li').off('click');
        }
        
        if(operateType == 'copy' || $("#refSchId").val() != "" ){ // 复制范围
        	$("#c_copyRange").show();
        }else {
        	$("#c_copyRange").hide();
        }
        
    };
	
	
    $(function() {
        // 绑定按钮事件
        $('#TaxScheme002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TaxScheme002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        
        
        initShow();// 根据操作类型：显示按钮
    	
    });
});