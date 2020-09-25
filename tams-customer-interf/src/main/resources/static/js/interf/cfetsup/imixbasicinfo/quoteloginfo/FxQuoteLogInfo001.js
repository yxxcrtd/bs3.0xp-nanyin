(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        var pageParam = {
            //外部报价编码
            quoteid: $('#quoteid').val()
            //内部报价编码
            ,nbquoteid: $('#nbquoteid').val()
            // 发送类型
            ,sendtype: $('#sendtype').val()
        };
        return pageParam;
    };
    /**
     * 定义数据表格
     */

    var listTbl = $("#dataTable").joyintable({
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
        rowno:false,
        /**
         * 定义表头
         */
        cols: [
            {field: 'quoteid',  sortField:'quoteid', title: 'FxQuoteLogInfo001.quoteid'}
            , {field: 'sendtype', sortField:'sendtype', title: 'FxQuoteLogInfo001.sendtype'}
            , {field: 'quotetype',  sortField:'quotetype', title: 'FxQuoteLogInfo001.quotetype'}
            , {field: 'message',  sortField:'message', title: 'FxQuoteLogInfo001.message'}
            , {field: 'creatorid', sortField:'creatorid', title: 'FxQuoteLogInfo001.creatorid'}
            , {field: 'createdtime', sortField:'createdtime', title: 'FxQuoteLogInfo001.createdtime',type: 'date'}
            ,{field: 'lastmodifierid',sortField:'lastmodifierid', title: 'FxQuoteLogInfo001.lastmodifierid'}
            , {field: 'lastmodifiedtime', sortField:'lastmodifiedtime', title: 'FxQuoteLogInfo001.lastmodifiedtime',type: 'date'}
        ]
    });


    /**
     * 画面加载完成
     */
    $(function () {
        // 初始化查询
        $('#FxQuoteLogInfo001_btn_search').click(function () {
            listTbl.load();
        });

    });
}());