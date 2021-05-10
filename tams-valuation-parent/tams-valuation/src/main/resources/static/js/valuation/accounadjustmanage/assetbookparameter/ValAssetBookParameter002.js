$(function () {
	
//  绑定联动：参数名-参数值
    $("#paramCode").change(function(){
		  var paramCode = $("#paramCode").val();
		  var booksetId = $("#booksetId").val();
		  Joyin.ajax(
				basePath + 'valuation/accounadjustmanage/assetbookparameter/ValAssetBookParameter002/getParamValue', 
				{paramCode : paramCode,booksetId : booksetId,pageModel : pageModel},
				function (data) {
					  $("#paramValue").val(data.paramValue).trigger("change");
					  $("#remark").val(data.remark).trigger("change");
		  });
    });
    
	/**
	 * 根据操作类型显示
	 */
    var initShow = function () {
      var pageModel = $('#pageModel').val();
      var status = $('#status').val();
      if(pageModel ==='0') {
			$("#c_paramCode1").show();
			$("#c_booksetId1").show();
			$("#c_paramCode2").hide();
			$("#c_booksetId2").hide();
			$("#c_booksetName").hide();
			$("#c_paramName").hide();
      }else if(status ==='A'){
			$("#c_paramCode1").hide();
			$("#c_booksetId1").hide();
			$("#c_paramCode2").show();
			$("#c_booksetId2").show();
			$("#c_booksetName").hide();
			$("#c_paramName").hide();
        }
    };
	
	
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.paramName =  $('#paramCode').find('option:selected').text();
        object.operateType = operateType;
        object.pageModel = pageModel;
        
            Joyin.ajax(basePath + 'valuation/accounadjustmanage/assetbookparameter/ValAssetBookParameter002/check', object, function(result) {
                Joyin.confirm(result.data, function() {
    		    	if(operateType === 'save'){
    		    		WorkFlow.doSave(basePath + 'valuation/accounadjustmanage/assetbookparameter/ValAssetBookParameter002/doUpdate', object, "ValAssetBookParameter002_btn_save");
    				}else{// submit
    					WorkFlow.doSubmit(basePath + 'valuation/accounadjustmanage/assetbookparameter/ValAssetBookParameter002/doUpdate', object, "ValAssetBookParameter002_btn_submit");
    				}
                });
            });
    }

    $(function() {
        /**
         * 绑定按钮事件
         */
        $('#ValAssetBookParameter002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        
        $('#ValAssetBookParameter002_btn_save').click(function() {
            saveOrSubmit('save');
        });

        initShow();// 根据操作类型显示
    });
    
});