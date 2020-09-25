/**
 * RskValRelateImport002 映射新建（含修改）
 */
$(function() {
    if (pageModel != PAGE_MODEL_NEW) {
        $('#RskValRelateImport002_ptlId').prop('disabled',true);
    }
    $('#RskValRelateImport002_btn_submit').click(function() {
        var param = $.serializeToObject('#submitForm');
        param.operateType = "save";
        param.pageModel = pageModel;
        //判断是新建页面还是编辑页面
        if (pageModel == PAGE_MODEL_NEW) {
            Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalrelateimport/RskValRelateImport002/check', param, function(result) {
                Joyin.confirm(result.data, function() {
                    Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalrelateimport/RskValRelateImport002/insert', param, function(result) {
                        //点击保存后关闭窗口
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    })
                })
            })
        }
        else {
            Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalrelateimport/RskValRelateImport002/check', param, function(result) {
                Joyin.confirm(result.data, function() {
                    Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalrelateimport/RskValRelateImport002/update', param, function() {
                        //点击保存后关闭窗口
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    })
                })
            })
        }
    })
}());