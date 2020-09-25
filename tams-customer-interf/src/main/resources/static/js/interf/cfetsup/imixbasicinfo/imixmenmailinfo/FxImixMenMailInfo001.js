(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        var pageParam = {
            jyyxm: $('#jyyxm').val()
            , jycy: $('#jycy').val()
            , lxdh: $('#lxdh').val()
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
        /**
         * 定义表头
         */
        cols: [
            {field: 'jycyid', width:'150', sortField:'jycyid', title: 'FxImixMenMailInfo001.jycyid'}
            , {field: 'jycy', width:'120', sortField:'jycy', title: 'FxImixMenMailInfo001.jycy'}
            , {field: 'jyyxm', width:'120', sortField:'jyyxm', title: 'FxImixMenMailInfo001.jyyxm'}
            , {field: 'lxdh', width:'120', sortField:'lxdh', title: 'FxImixMenMailInfo001.lxdh'}
            , {field: 'cz', width:'120', sortField:'cz', title: 'FxImixMenMailInfo001.cz'}
            , {field: 'email', width:'120', sortField:'email', title: 'FxImixMenMailInfo001.email'}
            , {field: 'msn', width:'120', sortField:'msn', title: 'FxImixMenMailInfo001.msn'}
            , {field: 'txdz', width:'120', sortField:'txdz', title: 'FxImixMenMailInfo001.txdz'}
            , {field: 'yzbm', width:'120', sortField:'yzbm', title: 'FxImixMenMailInfo001.yzbm'}
            , {field: 'jyyxmszm', width:'120', sortField:'jyyxmszm', title: 'FxImixMenMailInfo001.jyyxmszm'}
            , {field: 'zjgdm', width:'120', sortField:'zjgdm', title: 'FxImixMenMailInfo001.zjgdm'}
            , {field: 'zjgmc', width:'120', sortField:'zjgmc', title: 'FxImixMenMailInfo001.zjgmc'}
        ]
    });

    /**
     * 画面加载完成
     */
    $(function () {
        // 初始化查询
        $('#FxImixMenMailInfo001_btn_search').click(function () {
            dataTable.load();
        });

    });
}());