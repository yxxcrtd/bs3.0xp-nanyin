(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        var pageParam = {
            // 是否特殊债
            specialdebt: $('#specialdebt').val()
        };
        if ($("#secid").val()) {
            pageParam.secid = $("#secid").val().join(",");
        } else {
            pageParam.secid = null;
        }
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
              {field: 'seqno',title: 'FxImixBondInfo001.seqno'}
            , {field: 'secid',title: 'FxImixBondInfo001.secid'}
            , {field: 'specialdebt',title: 'FxImixBondInfo001.specialdebt'}
            , {field: 'creatorid', title: 'FxImixBondInfo001.creatorid'}
            , {field: 'createdtime',width : 150,title: 'FxImixBondInfo001.createdtime',type: 'date'}
            , {field: 'lastmodifierid', title: 'FxImixBondInfo001.lastmodifierid'}
            , {field: 'lastmodifiedtime',title: 'FxImixBondInfo001.lastmodifiedtime',type: 'date'}
        ]
    });

    /**
     * 画面加载完成
     */
    $(function () {
        // 初始化查询
        $('#FxImixBondInfo001_btn_search').click(function () {
            listTbl.load();
        });

    });
}());