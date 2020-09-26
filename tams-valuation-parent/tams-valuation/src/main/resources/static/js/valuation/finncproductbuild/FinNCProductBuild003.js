$(function () {
    
	/**
	 * 绑定保存事件
	 */
	var saveOrSubmit = function(operateType) {
		
		var object = $.serializeToObject('#saveForm');
		object.operateType = operateType;
		
		Joyin.ajaxJson(basePath + 'valuation/finncproductbuild/FinNCProductBuild003/check', object, function(result) {
		    Joyin.confirm(result.data, function() {
		    	WorkFlow.doSaveList(basePath + 'valuation/finncproductbuild/FinNCProductBuild003/save', 
						object, "FinNCProductBuild003_btn_save");
		    });
		});
	};
    
    //页面全部加载完毕后
    $(function() {
    	/**
    	 * 绑定保存事件
    	 */
        $('#FinNCProductBuild003_btn_save').click(function() {
            saveOrSubmit('save');
        });
        
    });

});



