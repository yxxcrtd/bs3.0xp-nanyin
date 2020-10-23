 /**
 * RskValSumname003 映射明细新建（含修改）
 */
$(function() {
    /*$("#RskValSumname003_btn_submit").click(function() {
        var object = $.serializeToObject('#submitForm');
        object.operateType = "submit";
        object.pageModel = pageModel;
        //判断是新建界面还是编辑界面
        if(pageModel===PAGE_MODEL_NEW){
            Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalsumname/RskValSumname003/check',object, function(result) {
                Joyin.confirm(result.data, function() {
                    Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalsumname/RskValSumname003/insert',
                        object,function(result){
                            //点击保存后关闭窗口
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        })
                    })
                })
            }
        else {
            Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalsumname/RskValSumname003/check',object,function(result) {
                Joyin.confirm(result.data, function() {
                    Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalsumname/RskValSumname003/update',
                        object,function(result){
                            //点击保存后关闭窗口
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        })
                })
            })
        }
    });*/
    var submit = function(){
        var object = $.serializeToObject('#submitForm');
        Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode004/save',
                object,function(result){
                    //点击保存后关闭窗口
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                });
    }
    $(function() {
        $('#RskValAssetCode004_btn_save').click(function(){
         // RskValAssetAction.isConfirm();
            parent.window['RskValAssetAction'].isConfirm();
            submit();
        })
    });
});
