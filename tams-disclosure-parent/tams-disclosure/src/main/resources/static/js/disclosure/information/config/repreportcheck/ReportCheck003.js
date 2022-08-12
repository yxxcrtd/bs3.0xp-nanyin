(function () {

    $(function () {

        $("#showtable").append("加载中");
        var $container = $("#showtable");
        Joyin.ajax('previewReport', {fileNo:fileNo}, function(result) {
            $container.html(result);
        });
    });
}());