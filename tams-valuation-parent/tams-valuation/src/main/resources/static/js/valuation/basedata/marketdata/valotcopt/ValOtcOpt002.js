$(function () {
    
	/**
	 * 根据操作类型：显示按钮等
	 */
    var initShow = function () {
    	targetNameShow();//联动显示标的名称
    };
		
    /**
     * 绑定联动
     */
    var linkBind=function() {
    	//联动显示标的名称
        $("#booksetId").change(function(){
        	targetNameShow();
        });
    };
    
    /**
     * 联动显示标的名称
     */
    var targetNameShow = function () {
    	var booksetId = $('#booksetId').val();
    	if(!(typeof(booksetId) == 'undefined')){
			  Joyin.ajax(
						basePath + 'valuation/basedata/marketdata/valotcopt/ValOtcOpt002/getBooksetName', 
						{targetId : targetId},
						function (data) {
							  $("#booksetName").val('data.booksetName').trigger("change");
				  });
		  }
    };

	/**
     * 画面加载完成
     */
    $(function() {
        
        //根据操作类型：显示按钮等
        initShow();
        
        //绑定联动
        linkBind();
        
    });
}());