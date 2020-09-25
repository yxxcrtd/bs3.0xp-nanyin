(function() {
	
    function onSaveOrSubmitAction(operateType) {
        var object = $.serializeToObject('#submitForm', true);
        object.pageModel = pageModel;
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + 'invest/riskredis/quota/portfolioindicator/RskSupervisionPortfPoint004/check', object, function(result) {
    		Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList('/invest/riskredis/quota/portfolioindicator/RskSupervisionPortfPoint004/save', object, "RskSupervisionPortfPoint004_btn_save");
                }
                else {
                    WorkFlow.doSubmitList('/invest/riskredis/quota/portfolioindicator/RskSupervisionPortfPoint004/save', object, "RskSupervisionPortfPoint004_btn_submit");
                }
        });
    });   	    
    };	

    function init(){
    	if($("#portfolioLevel").val()=='01')
    		$("#portfMixDiv").hide();
    	else
    		$("#portfMixDiv").show();
    }
    /**
     * 画面加载完成
     */
    $(function() {
    	//初始方法调用
    	init();
        // 绑定按钮事件
        $('#RskSupervisionPortfPoint004_btn_save').click(function() { 
        	onSaveOrSubmitAction('save');
        });
        // 绑定按钮事件
        $('#RskSupervisionPortfPoint004_btn_submit').click(function() { 
        	onSaveOrSubmitAction('submit');
        });
    });
}());