
/**
 * 辅助核算新建
 */
(function() {

    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#submitForm', false);
        console.log(object);
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.pageId = pageId;

        Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/assistaccount/ValAssistAccounting002/check', object, function (result) {
            Joyin.confirm(result.data, function () {
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'valuation/accounadjustmanage/assistaccount/ValAssistAccounting002/save', object, "ValAssistAccounting002_btn_save");
                } else {
                    WorkFlow.doSubmitList(basePath + 'valuation/accounadjustmanage/assistaccount/ValAssistAccounting002/save', object, "ValAssistAccounting002_btn_submit");
                }
            });
        })
    }

    /**
     * 画面加载完成
     */
    $(function() {
        var hands = JSON.parse($('#handstr').val());
        var str = "";
        for(var i=0,l=hands.length;i<l;i++){
            if ('Y' == hands[i].status) {
                str = str + '<input type="checkbox"  name="handList"  value="' + hands[i].handCode + '"  title="' + hands[i].handName + '"  lay-skin="primary" checked   > '+"\n";
            } else {
                str = str + '<input type="checkbox"  name="handList"  value="' + hands[i].handCode + '"  title="' + hands[i].handName + '"  lay-skin="primary" > ' + "\n";
            }

        }
        //console.log(str);
        $('#hands').html(str);
        layui.form.render();

        // 绑定按钮事件
        $('#ValAssistAccounting002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#ValAssistAccounting002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });


    });


}());



