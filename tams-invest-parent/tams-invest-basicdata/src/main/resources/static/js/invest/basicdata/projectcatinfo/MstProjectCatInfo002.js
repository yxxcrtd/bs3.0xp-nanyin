/**
 * 项目类资产估值行情信息
 *
 * @auth mrbbp@qq.com
 */
(function () {
    /**
     * 获取页面参数
     */
    var getParams = function () {
        var params = $.serializeToObject('#submitForm', true);
        if ($('#remark') != null) {
            params.remark = $('#remark').val();
        }
        return params;
    }

    /**
     * 保存或提交按钮执行事件
     */
    var onSaveOrSubmitAction = function (operateType) {
        var params = getParams();
        params.operateType = operateType;
        params.pageModel = pageModel;

        Joyin.ajaxJson(basePath + 'invest/basicdata/projectcatinfo/MstProjectCatInfo002/check', params, function (result) {
            Joyin.confirm(result.data, function () {
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/basicdata/projectcatinfo/MstProjectCatInfo002/save', params, "MstProjectCatInfo002_btn_save");
                } else {
                    WorkFlow.doSubmitList(basePath + 'invest/basicdata/projectcatinfo/MstProjectCatInfo002/save', params, "MstProjectCatInfo002_btn_submit");
                }
            });
        });
    };

    var init = function () {
        // 初始化
        if (pageModel == PAGE_MODEL_NEW) {
            $('#finProdName').attr("disabled", true);
            $('#fundManager').attr("disabled", true);
        }
        if (pageModel == PAGE_MODEL_EDIT) {
            $('#finprodId').attr("disabled", true);
            $('#finProdName').attr("disabled", true);
            $('#fundManager').attr("disabled", true);
        }
        $("#finProdMarketId").trigger('change');
    };

    /**
     * 修改资产代码时，资产名称和管理人联动变化
     */
    $("#finProdMarketId").change(function () {
        var obj = $("#finProdMarketId").find("option:selected");
        var finprodId = $("#finProdMarketId").val();

        if($('#finProdMarketId').val()){
            var obj = $("#finProdMarketId").select2("data")[0].object;
            $('#finProdName').val(obj.finProdName);
            $('#fundManager').val(obj.customerName);
            $('#finprodId').val(obj.finprodId);
        }else{
            $('#finProdName').val('');
            $('#fundManager').val('');
            $('#finprodId').val('');
        }
    });

    /*
     * 画面加载完成
     */
    $(function () {

        // 初始化
        init();

        // 保存按钮执行事件
        $('#MstProjectCatInfo002_btn_save').click(function () {
            onSaveOrSubmitAction('save');
        });

        // 提交按钮执行事件
        $('#MstProjectCatInfo002_btn_submit').click(function () {
            onSaveOrSubmitAction('submit');
        });

        // 画面联动

    });

}());