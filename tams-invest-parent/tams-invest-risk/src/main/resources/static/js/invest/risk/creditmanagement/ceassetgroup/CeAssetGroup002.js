(function() {
	
	function onSaveOrSubmitAction(operateType) {
        var object = $.serializeToObject('#ceAssetGroupForm', true);
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.creditMstId='0003';
        if(FileCommon) {
            object.uploadFileList = FileCommon.getUploadFileInfo();
        }
    	 Joyin.ajaxJson(basePath + 'invest/risk/creditmanagement/ceassetgroup/CeAssetGroup002/check', object, function(result) {        	
     		Joyin.confirm(result.data, function() {
		        if(operateType === 'save') {
		            WorkFlow.doSaveList(basePath + 'invest/risk/creditmanagement/ceassetgroup/CeAssetGroup002/save', object, "CeAssetGroup002_btn_save");
		        }
		        else {
		            WorkFlow.doSubmitList(basePath + 'invest/risk/creditmanagement/ceassetgroup/CeAssetGroup002/save', object, "CeAssetGroup002_btn_submit");
		        }
     		});
    	 });   
    };	
	
    //新建或编辑初始化
    function init(){
    	if(pageModel!=PAGE_MODEL_NEW){
    		$("select[name='customerId']").attr('disabled',true).trigger('change');
            $("input[name='pflsh']").attr('disabled',true);
        }
    }
    /**
     * 画面加载完成
     */
    $(function() {
    	init();
        // 保存按钮执行事件
        $('#CeAssetGroup002_btn_save').click(function(){
    		onSaveOrSubmitAction('save');
        });
        // 提交按钮执行事件
        $('#CeAssetGroup002_btn_submit').click(function(){
    		onSaveOrSubmitAction('submit');
        });
    });
}());