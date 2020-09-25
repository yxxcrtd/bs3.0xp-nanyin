/**
 * 基金账户管理
 */
$(function() {
    /**
     * 保存或提交
     * 
     */
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.remark = $('#remark').val();
        object.pageModel = pageModel;
    	if(FileCommon) {
    		object.fileArray = FileCommon.getUploadFileInfo()
    	}
        Joyin.ajaxJson(basePath + 'invest/basicdata/fundsecuritiesaccount/FundSecuritiesAccount002/check',
                object, function(result) {
                    Joyin.confirm(result.data, function() {
                        if (operateType === 'save') {
                            WorkFlow.doSaveList(basePath
                                    + 'invest/basicdata/fundsecuritiesaccount/FundSecuritiesAccount002/save',
                                    object, "FundSecuritiesAccount002_btn_save");
                        } else {
                            WorkFlow.doSubmitList(basePath
                                    + 'invest/basicdata/fundsecuritiesaccount/FundSecuritiesAccount002/save',
                                    object, "FundSecuritiesAccount002_btn_submit");
                        }
                    });
                });
   
    };
	
    /**
	 * 画面加载完成
	 */
    $(function() {
        
        //绑定init界面转换事件
    	if(pageModel==PAGE_MODEL_EDIT){
            $('#secAcctId').attr("disabled","disabled");
        }
        // 绑定按钮事件
        $('#FundSecuritiesAccount002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#FundSecuritiesAccount002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());
