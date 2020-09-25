(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        var pageParam = {
            // 主机构名称
            zjgmc: $('#zjgmc').val()
            // 从机构名称
            , cjgmc: $('#cjgmc').val()
            //主机构代码
            , zjglwm: $('#zjglwm').val()
            //从机构代码
            , cjglwm: $('#cjglwm').val()
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
            {field: 'zjgmc', width:'20%', sortField:'zjgmc', title: 'FxImixJgzcgx001.zjgmc'}
            , {field: 'zjglwm', width:'10%', sortField:'zjglwm', title: 'FxImixJgzcgx001.zjglwm'}
            , {field: 'cjgmc', width:'20%', sortField:'cjgmc', title: 'FxImixJgzcgx001.cjgmc'}
            , {field: 'cjglwm', width:'10%', sortField:'cjglwm', title: 'FxImixJgzcgx001.cjglwm'}
            , {field: 'zgxlxmc', width:'20%', sortField:'zgxlxmc', title: 'FxImixJgzcgx001.zgxlxmc'}
            , {field: 'cgxlxmc', width:'20%', sortField:'cgxlxmc', title: 'FxImixJgzcgx001.cgxlxmc'}
        ]
    });

    /**
     * 画面加载完成
     */
    $(function () {
        // 初始化查询
        $('#FxImixJgzcgx001_btn_search').click(function () {
            dataTable.load();
        });

    });
}());