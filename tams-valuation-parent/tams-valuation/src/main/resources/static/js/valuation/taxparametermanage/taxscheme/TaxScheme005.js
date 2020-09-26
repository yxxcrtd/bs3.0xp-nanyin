$(function () {

	/**
	 * 修改/新建
	 */
	var saveOrSubmit = function(operateType) {
		var isDisabled = $("#assetId").attr("disabled"); // 是否禁用标识
		if(isDisabled){// 避免：select 禁用无法获得值
			$("#assetId").removeAttr("disabled"); 
		}
		$("#assetClassify").removeAttr("disabled"); 
	  	$("#capiIncomeFeature").removeAttr("disabled"); 
	  	
		var object = $.serializeToObject('#dataForm');
		object.finprodType = object.assetId.split("(")[1]; //	主键是 finprod_id + finprod_type： finprod_id(finprod_type
		object.assetId = object.assetId.split("(")[0]; //	主键是 finprod_id + finprod_type： finprod_id(finprod_type
		object.pageModel = pageModel;
		object.operateType = operateType;
		
		Joyin.ajaxJson(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme005/check', object, function(result) {
		    Joyin.confirm(result.data, function() {
		    	if(operateType === 'save'){
				    WorkFlow.doSaveList(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme005/save', 
								object, "TaxScheme005_btn_save");
				}else{// submit
					WorkFlow.doSubmitList(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme005/save', 
							object, "TaxScheme005_btn_submit");
				}
		    });
		});
		
		if(isDisabled){
			$("#assetId").attr("disabled", "disabled");
		}
		$("#assetClassify").attr("disabled", "disabled");
	  	$("#capiIncomeFeature").attr("disabled", "disabled");
	};
	
	
	/**
	 * 根据操作类型：显示按钮等
	 */
    var initShow = function () {
        var operateType = $('#operateType').val();
        if(operateType === 'edit') {
        	 $("#assetId").attr("disabled", "disabled");
        	 $("#incomeType").attr("disabled", "disabled");
        }else if(operateType === 'add'){
            $("#taxRate").val("0.00").trigger("change");
        }
        
        if(pageModel == PAGE_MODEL_REFERENCE){
        	$('#taxableSign').next().addClass('disabled').find('li').off('click');
        }
        
        var schemeId = $("#schemeId").val();
        if(schemeId != null && schemeId != ''){
        	$("#schemeId").attr("disabled", "disabled");
        }

    };
    
    /**
     * 绑定联动
     */
    var linkBind=function() {
        
//        绑定联动：资产代码-资产名称-交易市场
        $("#assetId").change(function(){
        	if($("#assetId").val() != '' ){
        		var s_assetId =  $("#assetId").val().split("(")[0];
            	var s_finprodType =  $("#assetId").val().split("(")[1];
    			Joyin.ajax(
    				 basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme005/getAssetInfo', 
    				 {assetId : s_assetId, finprodType : s_finprodType},
    				 function (data) {
    				  	$("#assetClassify").val(data.assetClassify).trigger("change");
    				  	$("#capiIncomeFeature").val(data.capiIncomeFeature).trigger("change");
    			});
        	}else{
        		$("#assetClassify").val("").trigger("change");
			  	$("#capiIncomeFeature").val("").trigger("change");
        	}
        });

    }

	
    $(function() {
        // 绑定按钮事件
        $('#TaxScheme005_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TaxScheme005_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        				
        
        initShow();// 根据操作类型：显示按钮等
        linkBind();//绑定联动
        
    	
    });
});