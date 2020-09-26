$(function () {

	/**
	 * 修改/新建
	 */
	var saveOrSubmit = function(operateType) {
		
	    var getSecDetailType = function() {
	        return {
	        	secDetailType: $.trim($('#secDetailType').val()),
	        }
	    };
		
		$("#schemeId").removeAttr("disabled");
		var object = $.serializeToObject('#dataForm');
		object.pageModel = pageModel;
		object.operateType = operateType;
		
		Joyin.ajaxJson(basePath + 'valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme003/check', object, function(result) {
		    Joyin.confirm(result.data, function() {
		    	if(operateType === 'save'){
				    WorkFlow.doSaveList(basePath + 'valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme003/save', 
								object, "ValuationMethodScheme003_btn_save");
				}else{// submit
					WorkFlow.doSubmitList(basePath + 'valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme003/save', 
							object, "ValuationMethodScheme003_btn_submit");
				}
		    });
		});
//		$("#schemeId").attr("disabled", "disabled");
	};
	
	
	/**
	 * 根据操作类型：显示按钮等
	 */
    var initShow = function () {
        var operateType = $('#operateType').val();
        if(operateType === 'add') {
        }else if(operateType === 'edit' || operateType === 'copy') {
        	$("#secDetailType").attr("disabled", "disabled");
        	$("#secType").attr("disabled", "disabled");
        	$("#tradeMarket").attr("disabled", "disabled");
        }else {
        	 $('#valuationDate').next().addClass('disabled').find('li').off('click');
        }
        
        var schemeId = $("#schemeId").val();
        if(schemeId != null && schemeId != ''){
        	$("#schemeId").attr("disabled", "disabled");
        }
        
        secTypeLinkShow();// 联动：证券品种为债券时显示: 证券细分类，投资目的
        
        valMethodLinkShow();//  估值方法为市值法/市值成本孰低法 显示：估值第一市价、估值第二市价、行情日期、行情位数、行情位数处理

        priceClassShow(); // 01-债券,估值方法为市值法/市值成本孰低法且估值市价为收盘价、平均价、最高价、最低价、卖一价或者买一价时显示价格类型
    };
    
    /**
     * 绑定联动
     */
    var linkBind=function() {
//    	联动：估值第一市价不可和估值第二市价相同
    	$("#valuationPrice").change(function(){
        	var valPrice = $("#valuationPrice").val();
        	var valPrice2 = $("#valuationPrice2").val();
        	var num = $("#select2-valuationPrice-results").find("li").size();
        	if(num > 0 && valPrice == valPrice2){
        		$("#valuationPrice").val("").trigger("change");// select清空
            	layer.msg("估值第一市价不可和估值第二市价相同！");
        	}
        });
        $("#valuationPrice2").change(function(){
        	var valPrice = $("#valuationPrice").val();
        	var valPrice2 = $("#valuationPrice2").val();
        	var num = $("#select2-valuationPrice2-results").find("li").size();
        	if(num > 0 && valPrice == valPrice2){
        		$("#valuationPrice2").val("").trigger("change");// select清空
            	layer.msg("估值第一市价不可和估值第二市价相同！");
        	}
        });
        
        $("#quotaDigit").change(function(){
        	var quotaDigit = $('#quotaDigit').val();
            if(quotaDigit<0 || quotaDigit>12){
                layer.alert('[0,12]的整数！');
                $('#quotaDigit').val('');
                return ;
                }
        });
        
//      证券品种为债券时显示: 证券细分类，投资目的
        $("#secType").change(function(){
        	secTypeLinkShow();
        });
        
        
//      估值方法为市值法/市值成本孰低法 显示：估值第一市价、估值第二市价、行情日期、行情位数、行情位数处理
        $("#valuationMethod").change(function(){
        	valMethodLinkShow();
        });
        
        
 //     01-债券,估值方法为市值法/市值成本孰低法且估值市价为收盘价、平均价、最高价、最低价、卖一价或者买一价时显示价格类型
        $("#valuationPrice").change(function(){
            priceClassShow();
        });
        $("#valuationPrice2").change(function(){
            priceClassShow();
        });
        $("#secDetailType").change(function(){
            priceClassShow();
        });
    };
    
//  联动：证券品种为债券时显示: 证券细分类，投资目的
    var secTypeLinkShow = function() {
        var secType = $("#secType").val();
        
        $("#invAim").attr("disabled", false);
        $("#valuationMethod").attr("disabled", false);

//		$("#valuationSource").val("").trigger("change");
//		$("#modelName").val("").trigger("change");
		if ( ['04','05'].indexOf(secType) < 0 ) {
				$("#c_valuationSource").hide();
//				$("#c_modelName").hide();
		}
		
    	if(secType === '01'){// 01-债券
    		$("#c_secDetailType").show();
    		$("#c_invAim").show();
			$("#c_tradeMarket").show();
			
    	} else if(secType === '02') {// 隐藏并且置空
    		$("#c_secDetailType").hide();
    		$("#c_tradeMarket").show();
    		$("#c_invAim").hide();
    		
    	}else if(secType === '03') {// 换汇
			$("#c_tradeMarket").hide();
			$("#c_secDetailType").hide();
			$("#c_invAim").hide();
			
			$("#valuationMethod").val("01").trigger("change");
            $("#valuationMethod").attr("disabled", "disabled");
			
		}else if(secType === '04' || secType === '05') {// 利率互换和外汇
			$("#c_tradeMarket").hide();
			$("#c_secDetailType").hide();
			$("#c_invAim").hide();
			$("#c_valuationSource").show();
//			$("#c_modelName").show();
			
			$("#valuationMethod").val("05").trigger("change");
            $("#valuationMethod").attr("disabled", "disabled");
			
		} else if(secType === '06') {// 股票
			$("#c_tradeMarket").show();
			$("#c_secDetailType").hide();
			$("#c_invAim").show();

			$("#invAim").val("FVTPL").trigger("change");
            $("#invAim").attr("disabled", "disabled");
			$("#valuationMethod").val("01").trigger("change");
            $("#valuationMethod").attr("disabled", "disabled");
			
//		}else if(secType === '07' || secType === '08' || secType === '09') {
//			$("#c_tradeMarket").show();
//			$("#c_secDetailType").hide();
//			$("#c_invAim").hide();
//			
//			$("#valuationMethod").val("04").trigger("change");
//            $("#valuationMethod").attr("disabled", "disabled");
			
    	}else if(secType === '10' || secType === '11') {
			$("#c_tradeMarket").hide();
			$("#c_secDetailType").hide();
			$("#c_invAim").show();
			
			$("#invAim").val("FVTPL").trigger("change");
            $("#invAim").attr("disabled", "disabled");
			$("#valuationMethod").val("01").trigger("change");
            $("#valuationMethod").attr("disabled", "disabled");

//    	}else if(secType === '12') {
//			$("#c_tradeMarket").hide();
//			$("#c_secDetailType").hide();
//			$("#c_invAim").show();
			
//	        $("#invAim").change(function(){
//	            var invAim = $("#invAim").val();
//	            if(invAim === 'AC') {
//					$("#valuationMethod").val("04").trigger("change");
//		            $("#valuationMethod").attr("disabled", "disabled");
//	    		}else {
//	    	        $("#valuationMethod").attr("disabled", false);
//	    			$("#valuationMethod").val("").trigger("change");
//	        	}
//	        });
    	}
    };
	
 // 估值方法为市值法/市值成本孰低法 显示：估值第一市价、估值第二市价、行情日期、行情位数、行情位数处理
    var valMethodLinkShow = function (){
            var secType = $("#secType").val();
			var valMethod = $("#valuationMethod").val();
            var valuationPrice = $("#valuationPrice").val();
            var valuationPrice2 = $("#valuationPrice2").val();
			if(valMethod == '01' || valMethod == '03'){
		    	if(secType === '03'){// 03-换汇
					$("#c_valuationPrice").show();
					$("#c_valuationPrice2").hide();
					$("#c_valuationDate").hide();
					$("#c_quotaDigit").hide();
					$("#c_priceTailMode").hide();
		    		$("#c_priceClass").hide();
		    		
					$("#valuationPrice").val("08").trigger("change");
		            $("#valuationPrice").attr("disabled", "disabled");

					$("#valuationPrice2").val("").trigger("change");
					$("#valuationDate").next().find("li").removeClass("laytable-checkbox-selected");
					$("#valuationDate").val("");
					$("#quotaDigit").val("");
					$("#priceTailMode").val("");
					$("#priceClass").val("");
		    	}else{
	    	        $("#valuationPrice").attr("disabled", false);
					$("#c_valuationPrice").show();
					$("#c_valuationPrice2").show();
					$("#c_valuationDate").show();
					$("#c_quotaDigit").show();
					$("#c_priceTailMode").show();
		    	}
			}else{// 隐藏并且置空
				$("#c_valuationPrice").hide();
				$("#c_valuationPrice2").hide();
				$("#c_valuationDate").hide();
				$("#c_quotaDigit").hide();
				$("#c_priceTailMode").hide();
	    		$("#c_priceClass").hide();

				$("#valuationPrice").val("").trigger("change");
				$("#valuationPrice2").val("").trigger("change");
				$("#valuationDate").next().find("li").removeClass("laytable-checkbox-selected");
				$("#valuationDate").val("");
				$("#quotaDigit").val("");
				$("#priceTailMode").val("");
				$("#priceClass").val("");
			}
//		}
    };
    
    // 01-债券,估值方法为市值法/市值成本孰低法且估值市价为收盘价、平均价、最高价、最低价、卖一价或者买一价时显示价格类型
    var priceClassShow = function (){
            var operateType = $('#operateType').val();
            var secType = $("#secType").val();
            var valMethod = $("#valuationMethod").val();
            var valuationPrice = $("#valuationPrice").val();
            var valuationPrice2 = $("#valuationPrice2").val();
            if((valMethod == '01' || valMethod == '03') && secType === '01'){
                if(['01','02','03','04','05','06'].indexOf(valuationPrice) > -1 || ['01','02','03','04','05','06'].indexOf(valuationPrice2) > -1){
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
            }
    };
	
    $(function() {
        // 绑定按钮事件
        $('#ValuationMethodScheme003_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#ValuationMethodScheme003_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        				
        
        initShow();// 根据操作类型：显示按钮等

		/**
		 * 框架升级后需要去除childrenIds（否则回显不正常）
		 * FIXME 解决 去除childrenIds后，不能清空子表单
		 */
        $("select").each(function () {
            var select = $(this);
			var parentIds = $(this).attr("parentIds");
			if (parentIds) {
                var parentIdArr = parentIds.split(",");
                for (var i = 0; i < parentIdArr.length; i++) {
                    $("#" + parentIdArr[i] ).change(function () {
                        select.val("").trigger("change");
                    });
                }

			}
		});
        
        linkBind();//绑定联动
    	
    });
});