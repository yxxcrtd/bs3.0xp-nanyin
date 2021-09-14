(function () {
    $(".layui-tab").show();
    var clickPortfolioId = function (data) {
        Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
            basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
            "98%", "90%", {
                pageModel: PAGE_MODEL_REFERENCE,//page_model:查看
                portfolioId: data
            });//主键组合代码
    };
    var errorShow = function (data) {
        Joyin.error(data);
        return;
    };
    var cfetsRepoDealQuotedPre002Save = function (object) {
        Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/check', object, function(result) {
            if (result.data){
                Joyin.confirm(result.data, function() {
                    WorkFlow.doSaveList(basePath + 'invest/bankuplink/reponew/CfetsRepoDealQuotedPrice002/save', object, "CfetsRepoDealQuotedPrice002_btn_save", 'CfetsRepoDealQuotedPr002', function(result){
                        var index = window.parent.layer.getFrameIndex(window.name);
                        window.parent.layer.close(index);
                    })
                })
            }
        });
    };
    var cfetsRepoDealQuotedPre002Sumbit = function (object) {
        Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/check', object, function(result) {
            if (result.data){
                Joyin.confirm(result.data, function() {
                    WorkFlow.doSubmitList(basePath + 'invest/bankuplink/reponew/CfetsRepoDealQuotedPrice002/save', object, "CfetsRepoDealQuotedPrice002_btn_submit", 'CfetsRepoDealQuotedPr002', function(result){
                        var index = window.parent.layer.getFrameIndex(window.name);
                        window.parent.layer.close(index);
                    })
                })
            }
        });
    };
    var cfetsRepoDealQuotedPre003Save = function (object) {
        Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/check', object, function (result) {
            if (result.data) {
                Joyin.confirm(result.data, function () {
                    WorkFlow.doSaveList(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice003/save', object, "CfetsRepoDealQuotedPrice003_btn_save", 'CfetsRepoDealQuotedPr003', function (result) {
                        var index = window.parent.layer.getFrameIndex(window.name);
                        window.parent.layer.close(index);
                    })
                })
            }
        });
    };
    var cfetsRepoDealQuotedPre003Sumbit = function (object) {
        Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/check', object, function (result) {
            if (result.data) {
                Joyin.confirm(result.data, function () {
                    WorkFlow.doSubmitList(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice003/save', object, "CfetsRepoDealQuotedPrice003_btn_submit", 'CfetsRepoDealQuotedPr003', function (result) {
                        var index = window.parent.layer.getFrameIndex(window.name);
                        window.parent.layer.close(index);
                    })
                })
            }
        });
    };
    /**
     * 对外公开的接口
     */
    parent.CfetsRepoDealQuotedPrMain = {
        clickPortfolioId: clickPortfolioId,
        errorShow:errorShow,
        cfetsRepoDealQuotedPre002Save: cfetsRepoDealQuotedPre002Save,
        cfetsRepoDealQuotedPre002Sumbit: cfetsRepoDealQuotedPre002Sumbit,
        cfetsRepoDealQuotedPre003Save: cfetsRepoDealQuotedPre003Save,
        cfetsRepoDealQuotedPre003Sumbit: cfetsRepoDealQuotedPre003Sumbit
    };
    CfetsRepoDealQuotedPrMain = {
        clickPortfolioId: clickPortfolioId,
        errorShow:errorShow,
        cfetsRepoDealQuotedPre002Save: cfetsRepoDealQuotedPre002Save,
        cfetsRepoDealQuotedPre002Sumbit: cfetsRepoDealQuotedPre002Sumbit,
        cfetsRepoDealQuotedPre003Save: cfetsRepoDealQuotedPre003Save,
        cfetsRepoDealQuotedPre003Sumbit: cfetsRepoDealQuotedPre003Sumbit
    };
})();
