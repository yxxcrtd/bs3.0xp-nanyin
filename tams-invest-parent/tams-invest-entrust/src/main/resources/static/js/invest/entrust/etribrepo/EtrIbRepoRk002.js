/**
 * EtrIbRepoRk002 资产系列新建（含修改）
 */
$(function() {
    
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + 'invest/entrust/etribrepo/EtrIbRepoRk002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if (operateType == 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/entrust/etribrepo/EtrIbRepoRk002/save', object, "EtrIbRepoRk002_btn_save");
                    EtrIbRepoRk.loadTree();
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/entrust/etribrepo/EtrIbRepoRk002/save', object, "EtrIbRepoRk002_btn_submit");
                    EtrIbRepoRk.loadTree();
                };
            });
        });
    };
    
    $(function(){
        /**
         * 画面加载完成
         */
        // 绑定按钮事件
        $('#EtrIbRepoRk002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#EtrIbRepoRk002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    })
});
