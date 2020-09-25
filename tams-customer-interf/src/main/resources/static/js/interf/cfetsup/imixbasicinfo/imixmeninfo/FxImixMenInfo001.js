(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        var pageParam = {
            // 交易成员id
            jycyid: $('#jycyid').val()
            // 交易成员名称
            , jycyqc: $('#jycyqc').val()
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
            {field: 'jycyid', width:'10%', sortField:'jycyid', title: 'FxImixMenInfo001.jycyid'}
            , {field: 'jgdm', width:'20%', sortField:'jgdm', title: 'FxImixMenInfo001.jgdm'}
            , {field: 'jycyqc', width:'10%', sortField:'jycyqc', title: 'FxImixMenInfo001.jycyqc'}
            , {field: 'zwjc', width:'10%', sortField:'zwjc', title: 'FxImixMenInfo001.zwjc'}
            , {field: 'ywqc', width:'10%', sortField:'ywqc', title: 'FxImixMenInfo001.ywqc'}
            , {field: 'ywjc', width:'10%', sortField:'ywjc', title: 'FxImixMenInfo001.ywjc'}
            , {field: 'createdtime', width:'10%', sortField:'createdtime', title: 'FxImixMenInfo001.createdtime',type:'date'}
            , {field: 'lastmodifiedtime', width:'10%', sortField:'lastmodifiedtime', title: 'FxImixMenInfo001.lastmodifiedtime',type:'date'}
        ]
    });

    /**
     * 画面加载完成
     */
    $(function () {
        // 初始化查询
        $('#FxImixMenInfo001_btn_search').click(function () {
            dataTable.load();
        });

    });
}());