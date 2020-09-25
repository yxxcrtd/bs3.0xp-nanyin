(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        var pageParam = {
            //报价编号
            nbquoteid: $('#nbquoteid').val()
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
        /**
         * 定义表头
         */
        cols: [
            {field: 'seqno', sortField:'seqno', title: 'FxQuoteSendInfo001.seqno'}
            , {field: 'nbquoteid', sortField:'nbquoteid', title: 'FxQuoteSendInfo001.nbquoteid'}
            , {field: 'quotetype',  sortField:'quotetype',   title: 'FxQuoteSendInfo001.quotetype'}
            , {field: 'businesstype', sortField:'businesstype', title: 'FxQuoteSendInfo001.businesstype'}
            , {field: 'errorcode',  sortField:'errorcode', title: 'FxQuoteSendInfo001.errorcode'}
            , {field: 'errormessage',  sortField:'errormessage',   title: 'FxQuoteSendInfo001.errormessage'}
            , {field: 'creatorid',  sortField:'creatorid',   title: 'FxQuoteSendInfo001.creatorid'}
            , {field: 'createdtime', sortField:'createdtime', title: 'FxQuoteSendInfo001.createdtime',type: 'date'}
            , {field: 'lastmodifierid',  sortField:'lastmodifierid',   title: 'FxQuoteSendInfo001.lastmodifierid'}
            , {field: 'lastmodifiedtime',sortField:'lastmodifiedtime', title: 'FxQuoteSendInfo001.lastmodifiedtime',type: 'date'}
        ]
    });


    var exportExcel = function () {
        Joyin.exportExcel('export', getSearchParams());
    };

    /**
     * 画面加载完成
     */
    $(function () {
        // 初始化查询
        $('#FxQuoteSendInfo001_btn_search').click(function () {
            listTbl.load();
        });
        $('#FxQuoteSendInfo001_btn_export').click(function () {
            exportExcel();
        });

    });
}());