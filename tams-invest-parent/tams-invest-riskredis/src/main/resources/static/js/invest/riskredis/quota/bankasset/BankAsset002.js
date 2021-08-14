/**
 * BankAsset002 商业银行净资产维护
 */
$(function () {
    //判断是提交或是保存
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm', true);
        object.pageModel = pageModel;
        object.operateType = operateType;
        //获取资产代码联想文本框的hidcode金融产品代码
        if (pageModel == PAGE_MODEL_NEW || pageModel == PAGE_MODEL_EDIT) {
            object.customerId = $('#customerId').val();
            object.insitituteCode= $('#insitituteCode').val();
        	
        }
        Joyin.ajaxJson(basePath + 'invest/riskredis/quota/bankasset/BankAsset002/check', object, function (result) {
            Joyin.confirm(result.data, function () {
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/riskredis/quota/bankasset/BankAsset002/save', object, "BankAsset002_btn_save");
                } else {
                    WorkFlow.doSubmitList(basePath + 'invest/riskredis/quota/bankasset/BankAsset002/save', object, "BankAsset002_btn_submit");
                }
            });
        });
    };
    //画面初始化
    var init = function () {
        //编辑模式资产代码、估值日期、资产简称不可编辑
        if (pageModel == PAGE_MODEL_EDIT) {
            $('#insitituteCode').attr("disabled", true);
            $('#effDate').attr("disabled", true);
        }
    };

    $(function () {
        //初始化画面
        init();
        // 绑定按钮事件
        $('#BankAsset002_btn_save').click(function () {
            saveOrSubmit('save');
        });
        $('#BankAsset002_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
    });
}());