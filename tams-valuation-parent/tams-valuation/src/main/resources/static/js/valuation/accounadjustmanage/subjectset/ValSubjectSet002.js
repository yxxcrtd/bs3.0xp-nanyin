$(function() {
    function saveOrSubmit() {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/subjectset/ValSubjectSet002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/subjectset/ValSubjectSet002/save', object, function(result) {
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                });
            });
        });
    };
    /**
     * 画面加载完成
     */
    $(document).ready(function(){
        // 绑定按钮事件
        $('#ValSubjectSet002_btn_submit').click(function() {
            saveOrSubmit();
        });
        
        //修改上级科目号时，自动添加到科目号中
        $("#fsubjectId").change(function(){
            $("#subjectNo").val($("#fsubjectId").val());
        });
    });
}());