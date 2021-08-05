$(function() {
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.pageId = pageId;
        Joyin.ajaxJson(basePath + 'invest/basicdata/futuresdepositpercent/FuturesDepositPercent002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/basicdata/futuresdepositpercent/FuturesDepositPercent002/save', object, "FuturesDepositPercent002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/basicdata/futuresdepositpercent/FuturesDepositPercent002/save', object, "FuturesDepositPercent002_btn_submit");
                }
            });
        });
    };


    var init = function() {
        //编辑模式下  资产代码、资产简称、投向日不可以修改
        if (pageModel == PAGE_MODEL_EDIT) {
            $('#futuresType').attr("disabled", true);
            $('#futuresContractId').attr("disabled", true);
            $('#futuresCompany').attr("disabled", true);
            $('#effDate').attr("disabled", true);
        }
    }


    /**
     * 画面加载完成
     */
    $(function() {

        init();
        /**
         * 绑定事件
         */
        $('#FuturesDepositPercent002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });

        $('#FuturesDepositPercent002_btn_save').click(function() {
            saveOrSubmit('save');
        });
    });

}());