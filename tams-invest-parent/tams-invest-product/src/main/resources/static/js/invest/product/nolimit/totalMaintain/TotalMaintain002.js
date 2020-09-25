/**
 * 总量/总比例维护
 */
(function () {
    /**
     * 获取查询参数
     */
    var edate=$('#effDate').val();
    function saveOrSubmit(operateType) {
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#submitForm');
        var workflowId;
        if (workflowId != null || workflowId != '') {
            object.workflowId = workflowId;
        }
        object.operateType = pageModel;
        Joyin.ajaxJson(basePath + 'invest/product/nolimit/totalMaintain/TotalMaintain002/check', object, function (result) {
            Joyin.confirm(result.data, function () {
                if (pageModel==PAGE_MODEL_EDIT){
                    object.effDate=edate;
                }
                console.log(object);
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/product/nolimit/totalMaintain/TotalMaintain002/save', object, "TotalMaintain002_btn_save");
                } else if (operateType === 'submit') {
                    WorkFlow.doSubmitList(basePath + 'invest/product/nolimit/totalMaintain/TotalMaintain002/save', object, "TotalMaintain002_btn_submit");
                }
            });
        });
    }


    var showDig = function () {

    }
    /**
     * 导出
     */
    var exportExcel = function () {
        Joyin.exportExcel('export', getPageParams());
    };


    $(function () {
        $('#TotalMaintain002_btn_save').click(function () {
            saveOrSubmit("save");
        });
        $('#TotalMaintain002_btn_submit').click(function () {
            saveOrSubmit("submit");
        })
        //如果是不是新建，则禁用生效日
        if (pageModel != '0') {
            $('#effDate').prop("disabled", true);
        }
    });
}());