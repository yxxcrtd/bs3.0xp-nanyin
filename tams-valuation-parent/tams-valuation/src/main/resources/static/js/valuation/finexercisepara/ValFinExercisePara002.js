$(function () {

	/**
	 * 修改/新建
	 */
	var saveOrSubmit = function(operateType) {
		var object = $.serializeToObject('#dataForm');
		object.pageModel = pageModel;
		object.operateType = operateType;
		
		Joyin.ajaxJson(basePath + 'valuation/finexercisepara/ValFinExercisePara002/check', object, function(result) {
		    Joyin.confirm(result.data, function() {
		    	if(operateType === 'submit'){
				    WorkFlow.doSubmitList(basePath + 'valuation/finexercisepara/ValFinExercisePara002/save', 
								object, "ValFinExercisePara002_btn_submit");
				}
		    });
		});
	};

    /**
     * 界面初始化
     */
    var initShow = function() {
    	if(pageModel == PAGE_MODEL_NEW) {
    		
    	}else if(pageModel == PAGE_MODEL_EDIT) {
    		$("#booksetId").attr("disabled","disabled");
    		$("#finprodId").attr("disabled","disabled");
    	}
    }
    
    var linkBind = function (){
    	//  绑定联动：资产代码-资产名称-交易市场
        $("#finprodId").change(function(){
    		  var s_finprodId =  $("#finprodId").val();
    		  Joyin.ajax(
    				basePath + 'valuation/finexercisepara/ValFinExercisePara002/getFinprodInfo', 
    				{finprodId : s_finprodId},
    				function (data) {
    					  $("#finprodName").val(data.finprodName);
    		  });
        });
    }
	
    $(function() {
    	
    	initShow(); // 界面初始化
    	linkBind(); // 联动绑定
        // 绑定按钮事件
        $('#ValFinExercisePara002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        				

    });
    
    
});