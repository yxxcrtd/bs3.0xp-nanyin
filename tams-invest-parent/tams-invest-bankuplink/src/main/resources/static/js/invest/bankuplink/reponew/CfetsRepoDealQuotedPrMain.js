(function () {
    $(".layui-tab").show();
    var init = function() {
        Split(['#top_panel_dev', '#bottom_panel_dev'], {
            sizes: [48, 44],
            minSize: 100,
            direction: 'vertical',
        });

        Split(['#left_bottom_panel_dev', '#right_bottom_panel_dev'], {
            sizes: [35, 65],
            minSize: [300,600],
            direction: 'horizontal',
        });
    };
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
    /**
     * 对外公开的接口
     */
    parent.CfetsRepoDealQuotedPrMain = {
        clickPortfolioId: clickPortfolioId,
        errorShow:errorShow
    };
    CfetsRepoDealQuotedPrMain = {
        clickPortfolioId: clickPortfolioId,
        errorShow:errorShow
    };
        /**
         * 绑定事件
         */
        $(function () {

            init();

        });
    })();
