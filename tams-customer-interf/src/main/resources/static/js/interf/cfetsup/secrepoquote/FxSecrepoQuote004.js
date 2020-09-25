(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        var pageParam = {
            // 组合代码
            portfolioId: $('#portfolioId').val(),
            vdate: $('#vdate').val(),
            mdate: $('#mdate').val()
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
        height: 'full-' + 220,
        /**
         * 定义表头
         */
        cols: [
            {field: 'entrustId', width:150, sortField:'entrustId' ,title: 'FxSecrepoQuote004.entrustId'}
            , {field: 'portfolioId', width:150, sortField:'portfolioId', title: 'FxSecrepoQuote004.portfolioId'}
            , {field: 'portfolioName', width:150, sortField:'portfolioName', title: 'FxSecrepoQuote004.portfolioName'}
            , {field:'vdate',sortField:'vdate', width:80, title: 'FxSecrepoQuote004.vdate', type: 'date'}
            , {field: 'mdate', width:80, sortField:'trustTotalAmt', title: 'FxSecrepoQuote004.mdate', type: 'date'}
            , {field: 'entrustDate', width:180, sortField:'entrustDate', title: 'FxSecrepoQuote004.entrustDate', type: 'date'}
            , {field: 'busiPs', width:120, sortField:'busiPs', title: 'FxSecrepoQuote004.busiPs',
                 render:function (data) {
                    var text = "";
                    if(data == "03"){
                        text = "正回购";
                    }else if(data == "04"){
                        text = "逆回购";
                    }
                    return text;
                 }
              }
            , {field: 'assetType', width:120, sortField:'assetType', title: 'FxSecrepoQuote004.assetType',
                 render:function (data) {
                     return "质押式回购";
                 }
             }
            , {field: 'entrustStatus', width:120, sortField:'entrustStatus', title: 'FxSecrepoQuote004.entrustStatus',
                 render:function (data) {
                    var text = "";
                    if(data == '04'){
                        text = "已完成";
                    }
                    return text;
                 }
            }
            , {field: 'entrustAmt', width:120, sortField:'entrustAmt', title: 'FxSecrepoQuote004.entrustAmt', type: 'number'}
        ]
    });

    function save() {
        var selArr;
        if (dataTable != null && dataTable != '') {
            selArr = dataTable.getAllCheckedRows();
        }
        if(selArr != null && selArr.length >= 1){
            var object = {
                entrustId:selArr[0].entrustId,
                data:JSON.stringify(selArr)
            };
            Joyin.ajax(basePath + 'interf/cfetsup/secrepoquote/FxSecrepoQuote004/save',object, function(result) {
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
        $('#FxSecrepoQuote004_btn_search').click(function () {
            dataTable.load();
        });

        $('#FxSecrepoQuote004_btn_submit').click(function () {
            save();
        });

    });
}());