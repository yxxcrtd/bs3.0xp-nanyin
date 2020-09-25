/**
 * FinSubmittedModule002 申报附件方案管理新建（含修改）
 */
$(function() {
    $("#FinSubmittedModule002_btn_submit").click(function() {
        var object = $.serializeToObject('#submitForm');
        object.operateType = "submit";
        //判断是新建界面还是编辑界面
        if(pageModel===PAGE_MODEL_NEW){
            Joyin.ajaxJson(basePath + 'invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule002/check',object, function(result) {
                Joyin.confirm(result.data, function() {
                    Joyin.ajaxJson(basePath + 'invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule002/save',
                        object,function(result){
                            //点击保存后关闭窗口
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        })
                    })
                })
            }
        else {
            Joyin.ajaxJson(basePath + 'invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule002/check',object,function(result) {
                Joyin.confirm(result.data, function() {
                    Joyin.ajaxJson(basePath + 'invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule002/update',
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
