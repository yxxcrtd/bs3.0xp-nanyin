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
			var object = $.serializeToObject('#submitForm',true);
			object.pageModel = pageModel;
			object.operateType = operateType;	
			 if (operateType === 'revoke') {
				Joyin.ajaxJson(basePath + 'invest/product/paythevatquery/PayTheVatQuery003/check', object, function(result) {
				    Joyin.confirm(result.data, function() {
							WorkFlow.doRevokeList(basePath + 'invest/product/paythevatquery/PayTheVatQuery003/doSave', object, "FinproductPayTheVatQuery003_btn_submit");
				    });
				});
			 }
		};
		
		
		
	    /**
	     * 绑定按钮事件
	     */
	  $('#FinproductPayTheVatQuery003_btn_submit').click(function() {
	        saveOrSubmit('revoke');
	   });
	  
	  $(function () {
	      if(isShowWaterTrafficTax) {
	          $('#waterTrafficTax_div').show();
	      } else {
	          $('#waterTrafficTax_div').hide();
	      }

	      if(payType && payType == '02'){
               $("#bussTrdTransferOrder_div").hide();
          } else {
               $("#bussTrdTransferOrder_div").show();
          }

	      //备注金额重新赋值 modify by jiangpeng on 20210514 ZGBSSE-366 备注金额取两位小数
	      $("#remark").val($("#remark").val().split(":")[0]+":"+(parseFloat($("#appreciationTax").val())+parseFloat($("#additionalTax").val())).toFixed(2));
	  });
});