(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        var pageParam = {
            // 交易成员id
            jycyid: $('#jycyid').val()
            // 交易成员简称
            , jycyname: $('#jycyname').val()
        };
        return pageParam;
    };
    /**
     * 定义数据表格
     */

    var listTbl = $("#counterTable").joyintable({
        // ajax请求数据
        ajax: {
            // 请求路径
            url: 'findPage',
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
            {field: 'jycyid', width:110, sortField:'jycyid', title: 'FxSecRequestQuoteCounter.jycyid'}
            ,{field: 'jycyname', width:100, sortField:'jycyname', title: 'FxSecRequestQuoteCounter.jycyname'}
            , {field: 'jgdm', width:110, sortField:'jgdm', title: 'FxSecRequestQuoteCounter.jgdm'}
            , {field: 'jycyfullname', width:110, sortField:'jycyfullname', title: 'FxSecRequestQuoteCounter.jycyfullname'}
        ]
    });
    function save() {
        var selArr;
        if (listTbl != null && listTbl != '') {
            selArr = listTbl.getAllCheckedRows();
        }
        var object = {
            data:JSON.stringify(selArr)
        };
        if(selArr != null && selArr.length >= 1){
            Joyin.ajax(basePath + 'interf/cfetsup/secrequestquote/FxSecRequestQuote003/save',object, function(result) {
                top.returnData = result;
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            });
        }else{
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
        }
    }
    /**
     * 画面加载完成
     */
    $(function () {
        // 初始化查询
        listTbl.load();

        $('#FxSecRequestQuote003_btn_search').click(function () {
            listTbl.load();
        });

        $('#FxSecRequestQuote003_btn_confirm').click(function () {
            save();
        });
    });

}());