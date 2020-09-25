/**
 * 债券行情信息新建
 */
(function() {
    
    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
    	/**
    	 * 获取列表参数
    	 */
        var object = $.serializeToObject('#submitForm');
        object.operateType = operateType;
        object.pageModel = pageModel;
        object.opType = opType;
      //若是子产品则保存子产品的产品代码
        if(object.prodStyle != null && object.prodStyle != ''){
        	object.pFinprodId = object.finprodId;
        	object.finprodId = object.prodStyle;
        }
        else if(object.investmentCycle != null && object.investmentCycle != ''){
        	object.pFinprodId = object.finprodId;
        	object.finprodId = object.investmentCycle;
        }
        if(workflowId != null || workflowId != ''){
        	object.workflowId = workflowId;
        }
        Joyin.ajaxJson(basePath + 'invest/product/messageadjust/bmquotation/FinProductBmQuotation002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/product/messageadjust/bmquotation/FinProductBmQuotation002/save', object, "FinProductBmQuotation002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/bmquotation/FinProductBmQuotation002/save', object, "FinProductBmQuotation002_btn_submit");
                }
            });
        });
    }
    var init = function(){
    	$("#prodStyle").val('').parent().parent().hide();
    	$("#investmentCycle").val('').parent().parent().hide();
    	var wfl_operType = $("#wfl_operType").val();
    	if(pageModel==PAGE_MODEL_EDIT){
    		if(wfl_operType == null || wfl_operType != 'NEW'){
    			$('#finprodMarketId').attr("disabled",true);
        		$('#effDate').attr("disabled",true);
        		$('#prodStyle').attr("disabled",true);
    		}
    	}
    	finprodIdChange($('#finprodId').val(),$('#dealMode').val());
    };
	//产品代码联动
    function finprodIdChange(finprodId,dealMode){
    	if(finprodId!=null && finprodId!='' ){
    	        Joyin.ajax('/invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/queryLayerProdInfoByFinprodId', {finprodId:finprodId}, function(result) {
    	          $("#prodStyle option").remove();
    	          $("#investmentCycle option").remove();
    	          if(result != null && result.length > 0){
    	            //$("#prodStyle").append("<option></option>");
    	        	if(dealMode == DEAL_MODE_11){
    	        		 for(var i in result){
    	    	            	$("#prodStyle").append("<option value='"+ result[i].id +"'>"+result[i].text + "</option>");
    	    	            	
    	    	            }
    	    	            $("#prodStyle").val(PROD_STYLE).parent().parent().show();
    	        	}
    	        	else if(dealMode == DEAL_MODE_10){
    	        		for(var i in result){
	    	            	$("#investmentCycle").append("<option value='"+ result[i].id +"'>"+result[i].text + "</option>");
	    	            	
	    	            }
	    	            $("#investmentCycle").val(INVESTMENT_CYCLE).parent().parent().show();
    	        	}
    	          }else{
    	            $("#prodStyle").val('').parent().parent().hide();
    	            $("#investmentCycle").val('').parent().parent().hide();
    	          }
    	        });
    	        
		}
    };
    $(function() {
    	init();
        // 按钮事件
        $('#FinProductBmQuotation002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#FinProductBmQuotation002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $('#finprodMarketId').change(function(){
        	var str = $('#finprodMarketId').select2('data')[0].object.split(',');
        	if(str != null && str.length==4){
        		$("#finprodId").val(str[0]);
        		$("#profitType").val(str[1]);
        		$("#finprodAbbr").val(str[2])
        		$("#dealMode").val(str[3])
        	}
        	//分档产品加载适用款型
        	finprodIdChange($('#finprodId').val(),$('#dealMode').val());
        })
    });
}());

function selectCallback(data) {
	$('#finprodAbbr').val(data.text);
}