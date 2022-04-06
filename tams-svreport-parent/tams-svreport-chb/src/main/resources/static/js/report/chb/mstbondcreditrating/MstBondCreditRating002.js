(function() {
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.operateType = operateType;
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'chb/mstbondcreditrating/mstbondcreditrating002/check', object, function(result) {
           //提交工作流
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.submit")), function() {
                    WorkFlow.doSubmitList(basePath + 'chb/mstbondcreditrating/mstbondcreditrating002/save', object, "MstBondCreditRating002_btn_submit");
            });
        });
    };

    /**
     * 画面加载完成
     */
    $(function () {
        // 绑定按钮事件
        $('#MstBondCreditRating002_btn_submit').click(function() {
            saveOrSubmit("submit");
        });
        
        //修改上级科目号时，自动添加到科目号中
        $("#fsubjectId").change(function(){
            $("#subjectNo").val($("#fsubjectId").val());
        });
    });
}());