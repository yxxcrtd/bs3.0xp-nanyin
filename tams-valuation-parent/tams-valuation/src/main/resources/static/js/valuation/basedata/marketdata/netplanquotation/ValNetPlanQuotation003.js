$(function () {
	//当前是否包含流程
	var workflowIdNow = $("#workflowId").val();
	   var getPageParams = function() {
	        return {
	        	finprodId: $('#finprodId').val(),
	        	finprodAbbr : $('#finprodAbbr').val(),
	        }
	    };
	    
	    var gradeChange=function(){
	        var finprodId=$.trim($('#finprodId').val());
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
		 * 修改/新建启动
		 */
		var saveOrSubmit = function(operateType) {
			var object = $.serializeToObject('#submitForm');
			object.pageModel = pageModel;
			object.operateType = operateType;
			
			var valueAsset=object.valueAsset;
			var valueAssetlast= valueAsset.indexOf(".");
			if(valueAssetlast<0&&valueAsset.length>16){
				Joyin.alert("输入的资产净值太长，请重新输入",2);
			}else if(valueAssetlast>16){
				Joyin.alert("输入的资产净值太长，请重新输入",2);
			}
			else if(isNaN(valueAsset)){
				Joyin.alert("资产净值请输入数值,资产净值不能为名称",2);
			}else{
				Joyin.ajaxJson(basePath + 'valuation/basedata/marketdata/netplanquotation/ValNetPlanQuotation003/check', object, function(result) {
				    Joyin.confirm(result.data, function() {
				    	if(operateType === 'save'){
				    		object.operateType = "save";
						    WorkFlow.doSubmitList(basePath + 'valuation/basedata/marketdata/netplanquotation/ValNetPlanQuotation003/doUpdate', 
										object, "ValNetPlanQuotation003_btn_save");
						}else{// submit
							WorkFlow.doSubmitList(basePath + 'valuation/basedata/marketdata/netplanquotation/ValNetPlanQuotation003/save', 
									object, "ValNetPlanQuotation003_btn_submit",null,function(data){
							     	var index = parent.layer.getFrameIndex(window.name);
		                             parent.layer.close(index);
							});
						}
				    });
				});
			}		
		};
		
	    
		/**
		 * 根据操作类型：显示按钮
		 */
	     function btnShow() {
	    	 var editFlag = $('#editFlag').val();     	 
	        if(editFlag === '02') {
	        	 $("#ValNetPlanQuotation003_btn_save").show();
	        	 $("#ValNetPlanQuotation003_btn_submit").hide();
	             document.getElementById('finprodId').disabled= true;
	             document.getElementById('finprodAbbr').disabled= true;
	             document.getElementById('valueDate').disabled= true;
	        }else if(editFlag === '01'){
	        	 $("#ValNetPlanQuotation003_btn_save").hide();
	        	 $("#ValNetPlanQuotation003_btn_submit").show();
	        }	     
	    };
	    
	    /**
	     * 画面加载完成
	     */
	    $(function() {	   
	      $('#finprodId').change(function(){
	          gradeChange();
	      });
	      
	      // 绑定按钮事件
	        $('#ValNetPlanQuotation003_btn_save').click(function() {
	            saveOrSubmit('save');
	           
	        });
	        $('#ValNetPlanQuotation003_btn_submit').click(function() {
	            saveOrSubmit('submit');
	        });
	        
	        
	        btnShow();// 根据操作类型：显示按钮
	    });

});