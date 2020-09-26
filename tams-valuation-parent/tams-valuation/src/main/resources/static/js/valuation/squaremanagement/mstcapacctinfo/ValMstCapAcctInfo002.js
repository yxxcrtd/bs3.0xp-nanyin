(function() {
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.dealType = operateType;
        if(operateType === 'save') {
            WorkFlow.doSaveList(basePath + 'valuation/squaremanagement/mstcapacctinfo/ValMstCapAcctInfo002/save', object, "ValMstCapAcctInfo002_BTN_save");
        }
        else {
            WorkFlow.doSaveList(basePath + 'valuation/squaremanagement/mstcapacctinfo/ValMstCapAcctInfo002/save', object, "ValMstCapAcctInfo002_BTN_submit");
        }
    };
    //判断是由修改页面进入的还是由新建页面进入的
    function addOrEdit() {
        var dealType = $('#dealType').val();
        if(dealType === 'edit') {
        	 //账号置灰
        	 $('#acctNo').attr('disabled',"true");
        	 $("#ValMstCapAcctInfo002_BTN_save").hide();
        	 $("#ValMstCapAcctInfo002_BTN_submit").show();
        }else {
        	 $("#ValMstCapAcctInfo002_BTN_save").show();
        	 $("#ValMstCapAcctInfo002_BTN_submit").hide();
        }
        
    };
    /**
     * 画面加载完成
     */
    $(function() {
    	//判断进入页面的按钮是新建还是编辑
    	addOrEdit();
        // 绑定按钮事件
        $('#ValMstCapAcctInfo002_BTN_save').click(function() {
            saveOrSubmit('save');
        });
        $('#ValMstCapAcctInfo002_BTN_submit').click(function() {
            saveOrSubmit('submit');
        });

    });
}());