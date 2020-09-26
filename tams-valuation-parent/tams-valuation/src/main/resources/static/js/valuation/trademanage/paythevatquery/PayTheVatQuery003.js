$(function () {
	//当前是否包含流程
	var workflowIdNow = $("#workflowId").val();
	   var getPageParams = function() {
	        return {
	        	booksetId: $('#booksetId').val(),
	        	booksetName : $('#booksetName').val(),
	        }
	    };
	    
	    var gradeChange=function(){
	        var finprodId=$.trim($('#booksetId').val());
	        if(finprodId==''||finprodId==null){
	            return;
	        }else{
	            $.ajax({
	                url : 'getFinprodAbbr',
	                type : "POST",
	                data : {finprodId:finprodId},
	                success: function(data){		                	                	
	                        $('#finprodAbbr').val(data.message);	                      	                   
	                },
	                failure : function() {
	                }
	            });
	        }
	    }
	    
	    /**
		 * 撤销启动
		 */
		var saveOrSubmit = function(operateType) {
			var object = $.serializeToObject('#submitForm');
			object.pageModel = pageModel;
			object.operateType = operateType;	
			 if (operateType === 'submit') {
				Joyin.ajaxJson(basePath + 'valuation/trademanage/paythevatquery/PayTheVatQuery003/check', object, function(result) {
				    Joyin.confirm(result.data, function() {
							WorkFlow.doSubmitList(basePath + 'valuation/trademanage/paythevatquery/PayTheVatQuery003/doSave', object, "PayTheVatQuery003_btn_submit");
				    });
				});
			 }
		};
		
		
		
	    /**
	     * 绑定按钮事件
	     */
	  $('#PayTheVatQuery003_btn_submit').click(function() {
	        saveOrSubmit('submit');
	   });
});