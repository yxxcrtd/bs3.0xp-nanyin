/**
 * 资产指令查看组件
 */
var EtrShowFinPlanAssetDetailInfo = function () {
    return {
        searchDetail : function (params,url) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                basePath + url,
                "98%", "98%",
                params);
        }
    }
}();