/**
 * CeAssetGroupManagement002 资产系列新建（含修改）
 */
$(function() {
    
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + 'invest/risk/creditmanagement/ceassetgroupmanagement/CeAssetGroupManagement002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if (operateType == 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/risk/creditmanagement/ceassetgroupmanagement/CeAssetGroupManagement002/save', object, "CeAssetGroupManagement002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/risk/creditmanagement/ceassetgroupmanagement/CeAssetGroupManagement002/save', object, "CeAssetGroupManagement002_btn_submit");
                };
            });
        });
    };
    
    $(function(){
        /**
         * 画面加载完成
         */
        // 绑定按钮事件
        $('#CeAssetGroupManagement002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#CeAssetGroupManagement002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    })
});
