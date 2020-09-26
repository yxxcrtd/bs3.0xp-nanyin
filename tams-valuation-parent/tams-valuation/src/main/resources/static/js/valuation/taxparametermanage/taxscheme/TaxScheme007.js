$(function () {

	/**
	 * 修改/新建
	 */
	var saveOrSubmit = function(operateType) {
		var object = $.serializeToObject('#dataForm');
		object.pageModel = pageModel;
		object.operateType = operateType;
		
		Joyin.ajaxJson(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme007/check', object, function(result) {
		    Joyin.confirm(result.data, function() {
		    	if(operateType === 'save'){
				    WorkFlow.doSaveList(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme007/save', 
								object, "TaxScheme007_btn_save");
				}else{// submit
					WorkFlow.doSubmitList(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme007/save', 
							object, "TaxScheme007_btn_submit");
				}
		    });
		});
	};
	
	
	/**
	 * 根据操作类型：显示按钮等
	 */
    var initShow = function () {
    };
    
    /**
     * 事件联动
     */
    var actionBind=function() {
        $("#configId").change(function () {
            var object = {};
            object.configId = $("#configId").val();
            Joyin.ajax(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme007/getConfigInfoByKey', object, function(result) {
                $("#tableId").val(result.tableId).trigger("change");
                $("#subjectNature").val(result.subjectNature).trigger("change");
                $("#vatType").val(result.vatType).trigger("change");
                $("#taxPType").val(result.taxPType).trigger("change");
                $("#incomeSource").val(result.incomeSource).trigger("change");
                $("#inOut").val(result.inOut).trigger("change");
                $("#calcType").val(result.calcType).trigger("change");
                $("#cdFlag").val(result.cdFlag).trigger("change");
                $("#calcScale").val(result.calcScale).trigger("change");
                $("#calcSnature").val(result.calcSnature).trigger("change");
                $("#calcBase").val(result.calcBase).trigger("change");
                $("#isCarrt").val(result.isCarrt);
                $("#intRepayHandle").val(result.intRepayHandle);
            });
        });
    };

	
    $(function() {
        // 绑定按钮事件
        $('#TaxScheme007_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TaxScheme007_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        				
        
        initShow();// 根据操作类型：显示按钮等
        actionBind();// 事件绑定

    });
});