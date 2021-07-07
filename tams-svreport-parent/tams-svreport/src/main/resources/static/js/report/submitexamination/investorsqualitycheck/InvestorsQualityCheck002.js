$(function () {
    getPageParams = function() {
        var obj = $.serializeToObject('#searchForm');
        return obj;
    };

    //进行文件处理
    fileHandle = function(){
        var paramObj = getPageParams();
        Joyin.ajax('investorsSummary', paramObj, function(result) {
        });
    }

    $(function() {
        $('#InvestorsQualityCheck002_btn_execute').bind("click",fileHandle);
    });
}())