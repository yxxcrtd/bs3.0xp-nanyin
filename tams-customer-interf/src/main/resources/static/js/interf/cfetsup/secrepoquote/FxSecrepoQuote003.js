(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        var pageParam = {
            entrustId:entrustId,
            // 组合代码
             portfolioId: $('#portfolioId').val()
            // 债券三级类型
            , type3: $('#type3').val()
            // 债券四级类型
            , type4: $('#type3').val()
            // 债券代码
            , finprodId: $('#finprodId').val()
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
            url: 'queryForPage',
            // 请求类型
            type: 'POST',
            data: function () {
                // 获取请求参数
                return getSearchParams();
            }
        },
        loadonce:true,
        page: false,
        height: 'full-' + 220,
        /**
         * 定义表头
         */
        cols: [
            {field: 'finprodId', width:100, sortField:'finprodId' ,title: 'FxSecrepoQuote003.finprodId'}
            , {field: 'finprodAbbr', width:120, sortField:'finprodAbbr', title: 'FxSecrepoQuote003.finprodAbbr'}
            , {field: 'repoAmt', width:120, sortField:'repoAmt', title: 'FxSecrepoQuote003.repoAmt',edit: true, type: 'number'}
            ,{field:'dscCostAmt',sortField:'dscCostAmt', width:80, title: 'FxSecrepoQuote003.dscCostAmt', type: 'number'}
            , {field: 'faceTotalAmt', width:80, sortField:'trustTotalAmt', title: 'FxSecrepoQuote003.trustTotalAmt', type: 'number'}
            , {field: 'trustTotalAmt', width:180, sortField:'trustTotalAmt', title: 'FxSecrepoQuote003.trustTotalAmt', type: 'number'}
            , {field: 'netprice', width:120, sortField:'netprice', title: 'FxSecrepoQuote003.netprice', type: 'number'}
            , {field: 'cdate', width:120, sortField:'cdate', title: 'FxSecrepoQuote003.cdate', type: 'date'}
            , {field: 'convrate', width:120, sortField:'convrate', title: 'FxSecrepoQuote003.convrate', type: 'number'}
            , {field: 'trustee', width:120, sortField:'trustee', title: 'FxSecrepoQuote003.trustee'}
            , {field: 'mdate', width:120, sortField:'mdate', title: 'FxSecrepoQuote003.mdate' , type: 'date'}
        ]
    });

    function save() {
        var selArr;
        if (dataTable != null && dataTable != '') {
            selArr = dataTable.getAllCheckedRows();
        }
        if(selArr != null && selArr.length >= 1){
            var object = {
                data:JSON.stringify(selArr)
            };
            Joyin.ajax(basePath + 'interf/cfetsup/secrepoquote/FxSecrepoQuote003/save',object, function(result) {
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
        $('#FxSecrepoQuote003_btn_search').click(function () {
            dataTable.load();
        });

        $('#FxSecrepoQuote003_btn_submit').click(function () {
            save();
        });

    });
}());