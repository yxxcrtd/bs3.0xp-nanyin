//$(function() {
//    $("#ValAssetBook002_btn_submit").click(function() {
//        var object = $.serializeToObject('#submitForm');
//        object.pageModel = pageModel;
//        Joyin.ajaxJson('doCommit', object, function(result){
//            if(result.errorList.length==0){
//                var index = parent.layer.getFrameIndex(window.name);
//                parent.layer.close(index);
//            }
//        });
//    })
//});

$(function () {
	// 已生成凭证的账套不允许修改估值方案
//	if (hasVoucher) {
//		$("#schemeId").val("");
//		$("#schemeId").attr("disabled",true);
//	} else {
//		$("#schemeId").attr("disabled",false);
//	}
	
	// 需求变更，当修改估值方案时提示：修改估值方案，需要重新执行批处理任务，是否确认修改？
	var o_schemeId = $("#schemeId").val();
	$("#schemeId").change(function(){
		if(o_schemeId != ""){
			Joyin.confirm("修改估值方案，需要重新执行批处理任务，<br/>是否确认修改？", function(){
//			    console.log('确认');
			}, function() {
//			    console.log('取消');
			    $("#schemeId").val(o_schemeId).trigger("change");
			});
		}
	});
	
	
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.operateType = operateType;
        object.pageModel = pageModel;
        
        if (operateType === 'submit') {
            Joyin.ajax(basePath + 'valuation/accounadjustmanage/assetbook/ValAssetBook002/check', object, function(result) {
                Joyin.confirm(result.data, function() {
                    WorkFlow.doSubmit(basePath + 'valuation/accounadjustmanage/assetbook/ValAssetBook002/doUpdate', object, "ValAssetBook002_btn_submit");
                });
            });
        }
    }
    
    var setDate = $('#bookSetDate').val();
	var oldStartDate = $('#oldStartDate').val();
	var oldEndDate = $('#oldEndDate').val();

    if(setDate&&setDate>oldStartDate){
    	$('#startDate').prop('disabled',true)
    }
    
    /**
     * 绑定按钮事件
     */
    $('#ValAssetBook002_btn_submit').click(function() {
        saveOrSubmit('submit');
    });
    
    $('#startDate').change(function() {
    	var startDate = $('#startDate').val();
    	if(startDate<oldStartDate){
    		Joyin.alert('账套开始日不得小于产品起息日!');
    		$('#startDate').val(oldStartDate);
    	}
    });
    
    $('#endDate').change(function() {
    	var endDate = $('#endDate').val();
    	if(endDate<oldEndDate){
            Joyin.confirm("确定将账套到期日往前调整？", function() {
                $('#endDate').val(endDate);
                flag = true;
            },null,function() {
                $('#endDate').val(oldEndDate);
            });
            
        }
    });
 if($('#booksetId').val()){
     var object = $.serializeToObject('#submitForm');
     Joyin.ajax(basePath + 'valuation/accounadjustmanage/assetbook/ValAssetBook002/selectRecondSize', object, function(result) {
            if(result){
                $('#schemeId').prop('disabled',true);
                $('#taxSchemeId').prop('disabled',true);
            }
      });
  }

    
});