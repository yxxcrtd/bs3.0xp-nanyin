(function() {
	 
	function onSaveOrSubmitAction(operateType) {
        var object = $.serializeToObject('#ceAssetForm', true);
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.creditMstId='0002';
        if(FileCommon) {
            object.uploadFileList = FileCommon.getUploadFileInfo();
        }
    	 Joyin.ajaxJson(basePath + 'invest/risk/creditmanagement/ceasset/CeAsset002/check', object, function(result) {        	
     		Joyin.confirm(result.data, function() {
		        if(operateType === 'save') {
		            WorkFlow.doSaveList(basePath + 'invest/risk/creditmanagement/ceasset/CeAsset002/save', object, "CeAsset002_btn_save");
		        }
		        else {
		            WorkFlow.doSubmitList(basePath + 'invest/risk/creditmanagement/ceasset/CeAsset002/save', object, "CeAsset002_btn_submit");
		        }
     		});
    	 });   
    };	
	//modify by gaopeng 20191012 start 编辑时资产名称不可编辑
    //新建或编辑初始化
    function init(){
    	if(pageModel!=PAGE_MODEL_NEW){
            $("input[name='pflsh']").attr('disabled',true);
            $("#customerId").attr('disabled',true);
        }
    }
    //modify by gaopeng 20191012 end 编辑时资产名称不可编辑
    /**
     * 画面加载完成
     */
    $(function() {
    	init();
        // 保存按钮执行事件
        $('#CeAsset002_btn_save').click(function(){
    		onSaveOrSubmitAction('save');
        });
        // 提交按钮执行事件
        $('#CeAsset002_btn_submit').click(function(){
    		onSaveOrSubmitAction('submit');
        });
    });
}());