$(function () {

    /**
     * 画面加载完成
     */
    $(function() {

        /**
         * 绑定“确认拓展”按钮事件
         */
        $('#ValAccountEncryption001_btn_submit').click(function() {
            var object = $.serializeToObject('#submitForm');
            Joyin.ajaxJson('/valuation/dailyoperation/dailyaccountmake/valaccountencryption/ValAccountEncryption001/check', object, function(result1) {
                Joyin.confirm("是否确认生成？", function () {
                    Joyin.ajaxJson('/valuation/dailyoperation/dailyaccountmake/valaccountencryption/ValAccountEncryption001/submit', object,function (data) {
                        $('#bokDetailId').val(data.data.bokDetailId);
                        //点击保存后关闭窗口
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    });
                });
            });

        });
        /**
         * 绑定“重置”按钮事件
         */
        $('#ValAccountEncryption001_btn_reset').click(function() {
            window.location.reload();
        });

    });

})