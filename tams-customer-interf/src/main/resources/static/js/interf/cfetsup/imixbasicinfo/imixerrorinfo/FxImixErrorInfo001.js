(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        var pageParam = {
            // 市场描述
            marketindicator: $('#marketindicator').val()
            // 错误代码
            , applerrorcode: $('#errorCode').val()
        };
        return pageParam;
    };
    /**
     * 定义数据表格
     */

    var dataTable = $("#dataTable").joyintable({
        // ajax请求数据
        ajax: {
            // 请求路径
            url: 'selectForPage',
            // 请求类型
            type: 'POST',
            data: function () {
                // 获取请求参数
                return getSearchParams();
            }
        },
        rowno:true,
        /**
         * 定义表头
         */
        cols: [
            {field: 'marketindicator', width:'40%', sortField:'marketindicator', title: 'FxImixErrorInfo001.marketindicator'}
            , {field: 'applerrorcode', width:'20%', sortField:'applerrorcode', title: 'FxImixErrorInfo001.applerrorcode'}
            , {field: 'applerrordesc', width:'40%', sortField:'applerrordesc', title: 'FxImixErrorInfo001.applerrordesc'}
        ]
    });

    /**
     * 画面加载完成
     */
    $(function () {
        // 初始化查询
        $('#FxImixErrorInfo001_btn_search').click(function () {
            dataTable.load();
        });

    });
}());