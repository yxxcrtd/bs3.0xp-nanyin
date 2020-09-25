/**
 * RskValStructure002 映射新建（含修改）
 */
$(function() {
    $("#RskValStructure002_btn_save").click(function() {
        var object = $.serializeToObject('#submitForm');
        object.operateType = "save";
        object.pageModel = pageModel;
        //判断是新建界面还是编辑界面
        if(pageModel===PAGE_MODEL_NEW){
            Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalstructure/RskValStructure002/check',object, function(result) {
                Joyin.confirm(result.data, function() {
                    Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalstructure/RskValStructure002/insert',
                        object,function(result){
                            //点击保存后关闭窗口
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        })
                    })
                })
            }
        else {
            Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalstructure/RskValStructure002/check',object,function(result) {
                Joyin.confirm(result.data, function() {
                    Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalstructure/RskValStructure002/update',
                        object,function(result){
                            //点击保存后关闭窗口
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        })
                })
            })
        }
    });
});
