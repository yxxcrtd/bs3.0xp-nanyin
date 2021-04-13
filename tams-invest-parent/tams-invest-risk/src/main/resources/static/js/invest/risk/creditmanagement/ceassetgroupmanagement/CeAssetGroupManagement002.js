/**
 * CeAssetGroupManagement002 资产系列新建（含修改）
 */
$(function() {
    
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.operateType = operateType;
        var assetpoolName = object.assetpoolName;
        if(assetpoolName){
            var a = assetpoolName.split("");
            var num=0;
            for(var i=0;i<a.length;i++){
                if(a[i]>=0&&a[i<=9]||a[i]>='a'&&a[i]<='z'){
                    num=num+1;
                }else{
                    num=num+3;
                }
            }
            if(num>50){
                Joyin.error("系列名称过长！");
                return;
            }
        }
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
