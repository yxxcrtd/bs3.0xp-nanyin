$(function() {

    function saveOrSubmit() {
        var object = $.serializeToObject('#submitForm');
        if (!object.prodId){
            Joyin.error("子产品代码不能为空！");
            return;
        }
        if (!object.profitBaseDate){
            Joyin.error("本期收益分配基准日不能为空！");
            return;
        }
        Joyin.ajaxJson(basePath + 'disclosure/information/config/bonuscalist/BonusCalist003/check',
            object, function(result) {
            Joyin.ajaxJson(basePath + 'disclosure/information/config/bonuscalist/BonusCalist003/calculateData',
                       object, function(result) {
                               var index = parent.layer.getFrameIndex(window.name);
                               parent.layer.close(index);
                               table.reload();
            });
        });
    }


    function addrule1() {
        Joyin.dialog("产品选择",basePath+"disclosure/information/config/disprodplanmanage/DisProdPlanManage004/init","80%","80%",
            {},function () {
                var finprodIds = $('#finprodIds').data('finprodIds');
                if(finprodIds.length ==0){
                    return;
                }
                $('#prodId').textSelect('setValue',finprodIds);
            });
        return;
    };
    /**
     * 画面加载完成
     */
    $(function() {
        $('#RepProdIndex001_btn_treeAdd').click(addrule1);

        // 绑定按钮事件
        $('#BonusCalist003_btn_submit').click(function() {
            saveOrSubmit();
        });
    });
}());