(function() {
	var init=function() {
        if(pageModel!=PAGE_MODEL_NEW){
            $("#registBankCode").attr('disabled',true);
            $("#busiSeqno").attr('disabled',true);
            $("#regCode").attr('disabled',true);
            $("#orgIndentifyMark").attr('disabled',true);
            editFlag=false;
        }
        
    };
    
    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
    	var object = $.serializeToObject('#submitForm');
    	object.busiType = $("#busiType").val();
    	object.equityConfDate = $("#equityConfDate").val();
    	object.shareConfDatetime = $("#shareConfDatetime").val();
        object.operateType = operateType;
        object.pageModel = pageModel;
        Joyin.ajaxJson("check",object,function(result){
            if(result){
            	layui.layer.open({type:3});
                    Joyin.ajaxJson("doSave",object,function(result){
                    	layui.layer.closeAll();
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                });
            }
        })
    }
    
    $(function() {
    	init();
        // 按钮事件
        $('#EquityBusinessDetails002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());
