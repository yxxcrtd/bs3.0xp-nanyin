$(function () {
    
//	/**
//	 * 修改/新建
//	 */
//	var saveOrSubmit = function(operateType) {
//		
//		var object = $.serializeToObject('#submitForm');
//		object.pageModel = pageModel;
//		object.operateType = operateType;
//		
//		Joyin.ajaxJson(basePath + 'valuation/basedata/marketdata/valoptinfo/ValOptInfo002/check', object, function(result) {
//		    Joyin.confirm(result.data, function() {
//		    	if(operateType === 'save'){
//				    WorkFlow.doSaveList(basePath + 'valuation/basedata/marketdata/valoptinfo/ValOptInfo002/save', 
//								object, "ValOptInfo002_btn_save");
//				}else{// submit
//					WorkFlow.doSubmitList(basePath + 'valuation/basedata/marketdata/valoptinfo/ValOptInfo002/save', 
//							object, "ValOptInfo002_btn_submit");
//				}
//		    });
//		});
//	};
    
	/**
	 * 根据操作类型：显示按钮等
	 */
    var initShow = function () {
//        var operateType = $('#operateType').val();
//        if(operateType === 'add') {
//        }else if(operateType === 'edit') {
//        	$("#targetId").attr("disabled", "disabled");
//        }
//    	
//    	targetNameShow();//联动显示标的名称
    };
		
    /**
     * 绑定联动
     */
    var linkBind=function() {
    	//联动显示标的名称
//        $("#targetId").change(function(){
//        	targetNameShow();
//        });
    };
    
//    /**
//     * 联动显示标的名称
//     */
//    var targetNameShow = function () {
//    	var targetId = $('#targetId').val();
//    	if(!(typeof(targetId) == 'undefined')){
//			  Joyin.ajax(
//						basePath + 'valuation/basedata/marketdata/valoptinfo/ValOptInfo002/getTargetName', 
//						{targetId : targetId},
//						function (data) {
//							  $("#targetName").val(data.targetName).trigger("change");
//				  });
//		  }
//    };

	/**
     * 画面加载完成
     */
    $(function() {
    	
//        // 绑定按钮事件
//        $('#ValOptInfo002_btn_save').click(function() {
//            saveOrSubmit('save');
//        });
//        
//        $('#ValOptInfo002_btn_submit').click(function() {
//            saveOrSubmit('submit');
//        });
        
        //根据操作类型：显示按钮等
        initShow();
        
        //绑定联动
        linkBind();
        
    });
}());