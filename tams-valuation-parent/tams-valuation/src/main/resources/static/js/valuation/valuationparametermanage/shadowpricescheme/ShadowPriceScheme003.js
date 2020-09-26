$(function () {

	/**
	 * 修改/新建
	 */
	var saveOrSubmit = function(operateType) {
		var object = $.serializeToObject('#schemeForm');
		object.pageModel = pageModel;
		object.operateType = operateType;
		var quotaDigit = $('#quotaDigit').val();
		if(quotaDigit.length>10){
			Joyin.alert("行情位数最大只能为10位",2);
			return;
		}
		else{
			Joyin.ajaxJson(basePath + 'valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme003/check', object, function(result) {
			    Joyin.confirm(result.data, function() {
			    	if(operateType === 'save'){
			    		object.operateType = "save";
					    WorkFlow.doSubmitList(basePath + 'valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme003/doUpdate', 
									object, "ShadowPriceScheme003_btn_save");
					}else{// submit
						WorkFlow.doSubmitList(basePath + 'valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme003/save', 
								object, "ShadowPriceScheme003_btn_submit");
					}
			    });
			});
		}
	};
	
	/**
	 * 根据操作类型：显示按钮
	 */
    function btnShow() {
        var operateType = $('#operateType').val();
        var workflowId = $('#workflowId').val();
        if(operateType === 'edit') {
        	 $("#ShadowPriceScheme003_btn_save").show();
        	 $("#ShadowPriceScheme003_btn_submit").hide();
        	 $("#schemeId").attr("disabled","disabled");
        }else {
        	 $("#ShadowPriceScheme003_btn_save").hide();
        	 $("#ShadowPriceScheme003_btn_submit").show();
           	 $("#schemeId").attr("disabled","disabled");
        	 //$("#schemeId").attr("readonly","readonly");
        }
        
        if(operateType === 'add') {
        	$("#secType").val("01").trigger("change");
        	$("#valuationDate").val("02").trigger("change");
        }else{
        	$("#schemeId").attr("disabled", "disabled");
        	$("#secType").attr("disabled", "disabled");
        	$("#tradeMarket").attr("disabled", "disabled");
        	$("#secDetailType").attr("disabled", "disabled");
        }
        priceClassShow(); // 01-债券,估值市价为收盘价、平均价、最高价、最低价、卖一价或者买一价时显示价格类型
    };
    
    /**
     * 绑定联动
     */
    var linkBind=function() {
 //     01-债券,估值市价为收盘价、平均价、最高价、最低价、卖一价或者买一价时显示价格类型
        $("#valuationPrice").change(function(){
            priceClassShow();
        });
        $("#valuationPrice2").change(function(){
            priceClassShow();
        });
        $("#secDetailType").change(function(){
            priceClassShow();
        });
        $("#secType").change(function(){
            priceClassShow();
        });
    };
    
    // 01-债券,估值市价为收盘价、平均价、最高价、最低价、卖一价或者买一价时显示价格类型
    var priceClassShow = function (){
            var operateType = $('#operateType').val();
            var secType = $("#secType").val();
            var valuationPrice = $("#valuationPrice").val();
            var valuationPrice2 = $("#valuationPrice2").val();
            if(secType === '01' && (['01','02','03','04','05','06'].indexOf(valuationPrice) > -1 || ['01','02','03','04','05','06'].indexOf(valuationPrice2) > -1) ){
            	var secDetailType = $("#secDetailType").val();
                $("#c_priceClass").show();
                $("#priceClass").attr("disabled", false);
                if(['028','029','030'].indexOf(secDetailType) > -1){
                    if(operateType === 'add'){
                        $("#priceClass").val("02").trigger("change");
                    }
                }else{
                    $("#priceClass").val("01").trigger("change");
                    $("#priceClass").attr("disabled", "disabled");
                }
            }else{
                $("#c_priceClass").hide();
                $("#priceClass").val("");
            }
    };
	
	
    $(function() {
        // 绑定按钮事件
        $('#ShadowPriceScheme003_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#ShadowPriceScheme003_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        
        
        btnShow();// 根据操作类型：显示按钮
        linkBind();//绑定联动
    	
    });
});