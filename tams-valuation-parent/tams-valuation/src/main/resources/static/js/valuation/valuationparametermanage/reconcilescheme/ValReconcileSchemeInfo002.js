$(function () {
    function saveOrSubmit(operateType) {
        // 修复GZHSXT-1430：避免 输入不存在的账套，导致异常。
        var dataId = $("#booksetId").attr("data-id");// 从列表中选择的值
        var alt = $("#booksetId").attr("alt");// 当前输入的值
        if(dataId != alt){ // 输入不存在的账套
            $("#booksetId").val("");
        }
        
        var object = $.serializeToObject('#submitForm');
        object.operateType = operateType;
        object.pageModel = pageModel;
        if (operateType === 'submit') {
            Joyin.ajaxJson(basePath + 'valuation/valuationparametermanage/reconcilescheme/ValReconcileSchemeInfo002/doCheck', object, function(result) {
                Joyin.ajaxJson(basePath + 'valuation/valuationparametermanage/reconcilescheme/ValReconcileSchemeInfo002/save', object, function(result) {
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                });
            });
        }
    }
    /**
     * 绑定按钮事件
     */
    $('#ValReconcileSchemeInfo002_btn_submit').click(function() {
        saveOrSubmit('submit');
    });
});
