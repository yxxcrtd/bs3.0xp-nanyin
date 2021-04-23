(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        var param = $.serializeToObject('#searchForm');
        return param;
    };
    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function () {
                return getSearchParams();
            }
        },
        /**
         * 定义表格双击事件
         */
        dblclick: function (table, data) {
            //在新建界面进行显示单行的详细信息
            Joyin.dialog(JoyinUtil.getLabel("MstProjectCatInfo002.view"),
                basePath + "invest/basicdata/projectcatinfo/MstProjectCatInfo002/init",
                "98%", "98%",
                {
                    pageModel: PAGE_MODEL_REFERENCE
                    , finprodId: data.finprodId
                    , valDate:data.valDateStr
                }
            );
        },

        /**
         * 定义表头
         */
        cols: [
            {
                field: 'finProdMarketId'
                , title: 'MstProjectCatInfo001.finprodId'
                , type: 'text'
                , sortField: 'finProdMarketId'
                , width: 200
            }
            , {
                field: 'finProdName'
                , title: 'MstProjectCatInfo001.finProdName'
                , type: 'text'
                , sortable: false
                , width: 300
            }

            , {
                field: 'valDate'
                , title: 'MstProjectCatInfo001.valDate'
                , type: 'date'
                , sortField: 'valDate'
                , width: 200
            }

            , {
                field: 'valuation'
                , title: 'MstProjectCatInfo001.valuation'
                , type: 'money'
                , sortField: 'valuation'
                , width: 200
            }

            , {
                field: 'importDate'
                , title: 'MstProjectCatInfo001.importDate'
                , type: 'date'
                , sortField: 'importDate'
                , width: 200
            }

            , {
                field: 'operation'
                , title: 'SysCommon.operation'
                , type: 'action'
                , buttons: [
                    'edit'
                    , 'delt'
                ]
                , width: 200
            }
        ]
    });
    table.bind('click', '.edit', function (data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'invest/basicdata/projectcatinfo/MstProjectCatInfo001/check', data, function (result) {//请求路径，请求参数回调函数
            Joyin.dialog(JoyinUtil.getLabel("MstProjectCatInfo002.edit"),
                basePath + "invest/basicdata/projectcatinfo/MstProjectCatInfo002/init",
                "98%", "98%",
                {
                    pageModel: PAGE_MODEL_EDIT
                    , finprodId: data.finprodId
                    , valDate: data.valDateStr
                }, function () {
                    table.reload();
                }
            );
        })
    });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function (data, index) {
        var object = {selectedItem: [data], operateType: "delete"};//请求参数
        Joyin.ajaxJson(basePath + 'invest/basicdata/projectcatinfo/MstProjectCatInfo001/check', object, function (result) {//请求路径，请求参数回调函数
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")), function () {//删除事件的请求
                WorkFlow.doDeleteList(basePath + 'invest/basicdata/projectcatinfo/MstProjectCatInfo001/delete', object, "MstProjectCatInfo001_btn_delete", "MstProjectCatInfo001", function () {
                    table.reload();
                });
            });
        });
    });


    function MstProjectCatInfo001_btn_addBtn() {
        showDialog();
    }

    function showDialog() {
        Joyin.dialog(JoyinUtil.getLabel('MstProjectCatInfo002'),
            basePath + 'invest/basicdata/projectcatinfo/MstProjectCatInfo002/init', "60%", "75%", {pageModel: PAGE_MODEL_NEW})
    }

    function MstProjectCatInfo001_btn_deleteBtn() {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
        }
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        var object = {selectedItem: selArr, operateType: "delete"};
        Joyin.ajaxJson(basePath + 'invest/basicdata/projectcatinfo/MstProjectCatInfo001/check', object, function (result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")), function () {
                WorkFlow.doDeleteList(basePath + 'invest/basicdata/projectcatinfo/MstProjectCatInfo001/delete', object, "MstProjectCatInfo001_btn_delete", "MstProjectCatInfo001"
                    , function () {
                        table.reload();//删除事件请求及回调函数
                    });
            });
        });
    }

    function exportExcel() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    }

    /**
     * 画面加载完成
     */
    $(function () {
        //导出
        $('#MstProjectCatInfo001_btn_export').click(exportExcel);

        $('#MstProjectCatInfo001_btn_search').click(function () {
            table.load();

        });
        $('#MstProjectCatInfo001_btn_add').click(function () {
            MstProjectCatInfo001_btn_addBtn();
        })
        $('#MstProjectCatInfo001_btn_delete').click(function () {
            MstProjectCatInfo001_btn_deleteBtn();
        })

    });
}());
