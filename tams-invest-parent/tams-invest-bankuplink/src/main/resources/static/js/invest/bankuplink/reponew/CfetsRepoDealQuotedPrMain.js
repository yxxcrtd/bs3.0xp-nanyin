(function () {
    //add by meifaxin 20210929 上下左右拖拽start
    Split(['#left_bottom_panel_dev', '#right_bottom_panel_dev'], {
        sizes: [42, 58],
        minSize: 100,
        direction: 'horizontal',
    });

    Split(['#top_panel_dev', '#bottom_panel_dev'], {
        sizes: [50, 50],
        minSize: 100,
        direction: 'vertical',
    });
    var defaultStyle = "";
    $("#enlarge").click(function () {
        enlarge_narrow("enlarge");
    });
    $("#narrow").click(function () {
        enlarge_narrow("narrow");
    });
    function enlarge_narrow(flag) {
        let rightDiv = $("#right_bottom_panel_dev");
        if(!rightDiv.attr("flag") || rightDiv.attr("flag") === "1"){
            defaultStyle = rightDiv.attr("style");
            rightDiv.attr("style", "position: absolute; left: 0px; top:0px; width: 100%; height:100%;background: #fff; z-index: 100");
            rightDiv.attr("flag","0");
        }else{
            rightDiv.attr("style", " float:right; width:57.5%; height:100%; display: inline-block;");
            rightDiv.attr("flag","1");
        }
        if(flag === "narrow"){
            $("#enlarge").show();
            $("#narrow").hide();
        }else{
            $("#enlarge").hide();
            $("#narrow").show();
        }
    }
    //add by meifaxin 20210929 上下左右拖拽 end
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
                    WorkFlow.doSaveList(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/save', object, "CfetsRepoDealQuotedPrice002_btn_save", 'CfetsRepoDealQuotedPr002', function(result){
                        // var index = window.parent.layer.getLabelFrameIndex(window.name);
                        // windowindow.parent.layer.close(indexOf);
                        document.getElementById('cfetsRepoDealQuotedPr002_iframe').contentWindow.location.reload(true);
                        document.getElementById('CfetsRepoDealQuotedPr001_iframe').contentWindow.location.reload(true);
                        document.getElementById('EtrEtfInfoHq').contentWindow.location.reload(true);
                    })
                })
            }
        });
    };
    var cfetsRepoDealQuotedPre002Sumbit = function (object) {
        Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/check', object, function(result) {
            if (result.data){
                Joyin.confirm(result.data, function() {
                    WorkFlow.doSubmitList(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/save', object, "CfetsRepoDealQuotedPrice002_btn_submit", 'CfetsRepoDealQuotedPr002', function(result){
                        // var index = window.parent.layer.getFrameIndex(window.name);
                        // window.parent.layer.close(index);
                        document.getElementById('cfetsRepoDealQuotedPr002_iframe').contentWindow.location.reload(true);
                        document.getElementById('CfetsRepoDealQuotedPr001_iframe').contentWindow.location.reload(true);
                        document.getElementById('EtrEtfInfoHq').contentWindow.location.reload(true);
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
                        // var index = window.parent.layer.getFrameIndex(window.name);
                        // window.parent.layer.close(index);
                        document.getElementById('cfetsRepoDealQuotedPr003_iframe').contentWindow.location.reload(true);
                        document.getElementById('CfetsRepoDealQuotedPr001_iframe').contentWindow.location.reload(true);
                        document.getElementById('EtrEtfInfoHq').contentWindow.location.reload(true);
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
                        // var index = window.parent.layer.getFrameIndex(window.name);
                        // window.parent.layer.close(index);
                        document.getElementById('cfetsRepoDealQuotedPr003_iframe').contentWindow.location.reload(true);
                        document.getElementById('CfetsRepoDealQuotedPr001_iframe').contentWindow.location.reload(true);
                        document.getElementById('EtrEtfInfoHq').contentWindow.location.reload(true);
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
