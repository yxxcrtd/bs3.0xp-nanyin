(function() {
    var init=function() {
        if(pageModel==PAGE_MODEL_REFERENCE){
            $('#id').attr('disabled',true);
            $("#tabNum").attr('disabled',true);
            $('#subName').attr('disabled',true);
            $('#subType').attr('disabled',true);
            $('#subFormType').attr('disabled',true);
            $('#subWidth').attr('disabled',true);
            $('#subTmpType').attr('disabled',true);
            $('#subChartType').attr('disabled',true);
            $('#subDefualt').attr('disabled',true);
        }
        
        if(pageModel==PAGE_MODEL_EDIT){
        	 $("#id").attr('disabled',true);
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
        $('#PoiReportModelManageChildren002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());
