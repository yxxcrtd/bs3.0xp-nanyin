(function () {
    var init = function () {

    }

    function saveOrSubmit(operateType) {
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#informationForm');
        object.finprodId = $("#assetId").select2('data')[0].col;
        Joyin.ajaxJson(basePath + 'invest/period/trdprojbuyoff/TrdProjectBuyOffInterest002/check', object, function (result) {
            Joyin.confirm(result.data, function () {
                Joyin.ajaxJson(basePath + 'invest/period/trdprojbuyoff/TrdProjectBuyOffInterest002/save', object, function (result) {
                    parent.TrdProjectBuyOffInterest001.refreshTable();
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                });
            });
        });
    }

    /**
     * 画面加载完成
     */
    $(function () {
        init();
        $('#TrdProjectBuyOffInterest002_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
        $('#assetId').change(function () {
            if ($("#assetId").select2('data')[0].object) {
                $("#assetName").val($("#assetId").select2('data')[0].object);
            }
        });
    });
}());

