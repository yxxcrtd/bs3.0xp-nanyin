(function() {
    var init=function() {
        if(pageModel==PAGE_MODEL_REFERENCE){
            $("#poiType").attr('disabled',true);
            $('#poiId').attr('disabled',true);
            $('#poiOrder').attr('disabled',true);
            $('#poiSql').attr('disabled',true);
        }
    };

    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.operateType = operateType;
        object.pageModel = pageModel;
        Joyin.ajaxJson("check",object,function(result){
            if(result){
                Joyin.ajaxJson("doSave",object,function(result){
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                });
            }
        })
    }

    $(function() {
        init();
        // 按钮事件
        $('#DisPoiQuery002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());
