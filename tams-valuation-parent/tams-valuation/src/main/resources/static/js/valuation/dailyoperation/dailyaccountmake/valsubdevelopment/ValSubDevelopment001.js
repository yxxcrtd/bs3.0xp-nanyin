$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            booksetIds: $('#booksetIds').val(),
            thirdSubjectId: $('#thirdSubjectId').val()
        }
    };

    /**
     * 画面加载完成
     */
    $(function() {

        /**
         * 绑定“确认拓展”按钮事件
         */
        $('#ValSubDevelopment001_btn_submit').click(function() {
            var object = $.serializeToObject('#submitForm');
            Joyin.ajaxJson('/valuation/dailyoperation/dailyaccountmake/valsubdevelopment/ValSubDevelopment001/check', object, function(result1) {
                Joyin.confirm("是否确认拓展？", function () {
                    JoyinProgress.showInDialog('uploadFileProgressBar');
                    Joyin.ajaxJson('/valuation/dailyoperation/dailyaccountmake/valsubdevelopment/ValSubDevelopment001/submit', object,function () {
                        //点击保存后关闭窗口
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                        window.location.reload();
                    });
                });
            });

        });

        /**
         * 绑定“脚本生成”按钮事件
         */
        $('#ValSubDevelopment001_btn_sql').click(function() {
            generateSql();
        });


        // 导出脚本
        var generateSql = function() {
            var object = $.serializeToObject('#submitForm');
            Joyin.ajaxJson('/valuation/dailyoperation/dailyaccountmake/valsubdevelopment/ValSubDevelopment001/check',
                object, function(result) {
                    Joyin.exportExcel('generateSql', object);
                });
        };

        /**
         * 绑定“重置”按钮事件
         */
        $('#ValSubDevelopment001_btn_reset').click(function() {
            window.location.reload();
        });

    });

})