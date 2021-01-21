/**
 * 佣金设置查询
 */
(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            tradeMarket : $('#MstCommissionSet001_tradeMarket').val(),
            feeType : $('#MstCommissionSet001_feeType').val()
        }
    };
    // 新建
    function showDialog() {
        Joyin.dialog(JoyinUtil.getLabel('MstCommissionSet002'), basePath
                + 'invest/basicdata/mstcommissionset/MstCommissionSet002/init', '90%', '80%', {
            pageModel : PAGE_MODEL_NEW
        }, function() {
            table.reload();
        });
    }

    /**
     * 定义数据表格
     */
    var table = $("#dataTable")
            .joyintable(
                    {
                        ajax : {
                            url : 'findPage',
                            type : 'POST',
                            data : function() {
                                return getSearchParams();
                            }
                        },
                        dblclick : function(table, data) {
                            data.valueDate = new Date(data.valueDate).format();
                            data.pageModel = PAGE_MODEL_REFERENCE;
                            Joyin
                                    .dialog(
                                            JoyinUtil.getLabel("SysCommon.refrence"),
                                            JoyinUtil
                                                    .getRealPath("invest/basicdata/mstcommissionset/MstCommissionSet002/init"),
                                            "90%", "80%", data, function() {
                                                table.reload();
                                            });
                        },

                        cols : [

                        {
                            field : 'batchSeq',
                            visible : false,
                            width : 150
                        }, {
                                field : 'feeTypeStr',
                                title : 'MstCommissionSet001.feeType',
                                sortable : false,
                                width : 150
                          },{
                            field : 'secAcctId',
                            visible : false,
                            width : 150
                        }, {
                            field : 'secAcctName',
                            title : 'MstCommissionSet001.secAcct',
                            sortable : false,
                            width : 150
                        }, {
                            field : 'tradeMarketStr',
                            title : 'MstCommissionSet001.tradeMarket',
                            sortField : 'tradeMarket',
                            width : 150
                        }, {
                            field : 'tradeTypeStr',
                            title : 'MstCommissionSet001.tradeType',
                            sortField : 'tradeType',
                            width : 150
                        }, {
                            field : 'psStr',
                            title : 'MstCommissionSet001.ps',
                            sortable : false,
                            width : 150
                        }, {
                            field : 'termTypeStr',
                            title : 'MstCommissionSet001.termType',
                            sortable : false,
                            width : 150
                        }, {
                            field : 'windtypeoneStr',
                            title : '资产分类一',
                            sortable : false,
                            width : 150
                        },{
                            field : 'windtypetwoStr',
                            title : '资产分类二',
                            sortable : false,
                            width : 150
                        },{
                            field : 'chargeTypeStr',
                            title : 'MstCommissionSet001.chargeType',
                            sortField : 'chargeType',
                            width : 150
                        },{
                            field : 'chargeBasisStr',
                            title : 'MstCommissionSet001.chargeBasis',
                            sortField : 'chargeBasis',
                            width : 150
                        },{
                            field : 'isGradeStr',
                            title : '费用是否分档',
                            sortable : false,
                            width : 150
                        },{
                            field : 'feeRate',
                            title : 'MstCommissionSet001.chargeRate',
                            sortable : false,
                            width : 185
                        }, {
                            field : 'feeMaxi',
                            title : 'MstCommissionSet002.feeMaxi',
                            sortField : 'feeMaxi',type:'money',
                            width : 150
                        }, {
                            field : 'feeMini',
                            title : 'MstCommissionSet002.feeMini',
                            sortField : 'feeMini',type:'money',
                            width : 150
                        },{
                            field : 'isPayWithTradeStr',
                            title : 'MstCommissionSet002.isPayWithTrade',
                            sortField : 'isPayWithTrade',
                            visible:false,
                            width : 150
                         }, {
                            field : 'effDate',
                            title : 'MstCommissionSet002.effDate',
                            sortField : 'effDate',
                            type : 'date'
                        }, {
                            field : 'action',
                            title : '操作',
                            type : 'action',
                            buttons : [ 'edit', 'delt' ]
                        } ],
                    });

    var exportExcel = function() {
        // Joyin.exportExcel('export', getSearchParams())
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = 'edit'
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstcommissionset/MstCommissionSet002/check',
                data, function(result) {
                    Joyin.dialog(JoyinUtil.getLabel("MstCommissionSet002.edit"), basePath
                            + "invest/basicdata/mstcommissionset/MstCommissionSet002/init", "90%",
                            "80%", data, function() {
                                table.reload();
                            });
                });

    });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {
            selectedItem : [ data ],
            operateType : "delete"
        };
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstcommissionset/MstCommissionSet001/check',
                object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil
                            .getLabel("SysCommon.delete")), function() {
                        WorkFlow.doDeleteList(basePath
                                + 'invest/basicdata/mstcommissionset/MstCommissionSet001/delete',
                                object, "MstCommissionSet001_btn_delete", "MstCommissionSet002",
                                function() {
                                    table.reload();
                                });
                    });
                });
    });

    $(function() {
        // 绑定按钮事件
        $('#MstCommissionSet001_btn_search').click(function() {
            table.load();
            $('#MstCommissionSet001_btn_delete').show();
        });
        $('#MstCommissionSet001_btn_export').click(function() {
            exportExcel();
        });
        $('#MstCommissionSet001_btn_add').click(function() {
            showDialog();
        });
        $('#MstCommissionSet001_btn_delete')
                .click(
                        function() {
                            if (table != null && table != '') {
                                var selArr = table.getAllCheckedRows();
                            }
                            if (!selArr || selArr.length == 0) {
                                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                                return;
                            }
                            var object = {
                                selectedItem : selArr,
                                operateType : "delete"
                            };
                            Joyin
                                    .ajaxJson(
                                            basePath
                                                    + 'invest/basicdata/mstcommissionset/MstCommissionSet001/check',
                                            object,
                                            function(result) {
                                                Joyin
                                                        .confirm(
                                                                JoyinUtil
                                                                        .getMessage(
                                                                                "COMMON_C_0001",
                                                                                JoyinUtil
                                                                                        .getLabel("SysCommon.delete")),
                                                                function() {
                                                                    WorkFlow
                                                                            .doDeleteList(
                                                                                    basePath
                                                                                            + 'invest/basicdata/mstcommissionset/MstCommissionSet001/delete',
                                                                                    object,
                                                                                    "MstCommissionSet001_btn_delete",
                                                                                    "MstCommissionSet002");
                                                                });
                                            });
                        });
    });
}());