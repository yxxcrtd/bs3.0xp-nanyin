$(function () {

	/**
	 * 修改/新建
	 */
	var saveOrSubmit = function(operateType) {
		var isDisabled = $("#configId").attr("disabled"); // 是否禁用标识
		if(isDisabled){// 避免：select 禁用无法获得值
			$("#configId").removeAttr("disabled"); 
		}
		
		var object = $.serializeToObject('#dataForm');
		object.pageModel = pageModel;
		object.operateType = operateType;
		
		Joyin.ajaxJson(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme006/check', object, function(result) {
		    Joyin.confirm(result.data, function() {
		    	if(operateType === 'save'){
				    WorkFlow.doSaveList(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme006/save', 
								object, "TaxScheme006_btn_save");
				}else{// submit
					WorkFlow.doSubmitList(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme006/save', 
							object, "TaxScheme006_btn_submit");
				}
		    });
		});
		
		if(isDisabled){
			$("#configId").attr("disabled", "disabled");
		}
	};
	
	
	/**
	 * 根据操作类型：显示按钮等
	 */
    var initShow = function () {
        var operateType = $('#operateType').val();
        var dictCode = $('#dictCode').val();
        
	   	 if($.trim(dictCode) == ''){ // 数据字典为空时，使用文本框输入
			 $("#c_select_configValue").hide();
			 $("#c_input_configValue").show();
			 
			 $("#select_configValue").val("").trigger("change");
			 $("#select_configValue").next().find("li").removeClass("laytable-checkbox-selected");
		 }else{
			 $("#c_select_configValue").show();
			 $("#c_input_configValue").hide();
			 
			 $("#input_configValue").val("");
		 }

        if(operateType === 'edit') {
        	 $("#configId").attr("disabled", "disabled");
        }
    };
    
    /**
     * 绑定联动
     */
    var linkBind=function() {
    	// 根据对应项目是否使用数据字典，若数据字典为空时，使用文本框输入
    	$("#configId").change(function(){
        	Joyin.ajax(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme006/findValue',
    			{configId: $('#configId').val()}, 
    			function(data) {
    				if(data.length <= 0){// 若数据字典为空时，使用文本框输入
    					$("#c_select_configValue").hide();
    					$("#c_input_configValue").show();
    				}else {
    					$("#c_select_configValue").show();
   					 	$("#c_input_configValue").hide();
    				}
    				
    				$("#select_configValue").val("").trigger("change");
    				$("#select_configValue").next().find("li").removeClass("laytable-checkbox-selected");
					$("#input_configValue").val("");
        	});
        });
    }

	
    $(function() {
        // 绑定按钮事件
        $('#TaxScheme006_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TaxScheme006_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        				
        
        initShow();// 根据操作类型：显示按钮等
        linkBind();//绑定联动
        
        
    	
    });
});