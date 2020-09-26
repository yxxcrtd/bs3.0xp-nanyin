$(function() {

    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            finprodId : $.trim($('#finprodId').val()),
            booksetId : $.trim($('#booksetId').val()),
            vdateBegin : $('#vdate').startDate(),
            vdateEnd : $('#vdate').endDate(),
            recalType : $.trim($('#recalType').val()),
        }
    };
    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax : {
            url : 'findPage',
            type : 'POST',
            data : function() {
                return getSearchParams();
            }
        },
        checkbox : true,
        cellMinWidth : 100,
        cols : [ {
            field : 'busiId',
            title : '资产代码',
            sort : true
        }, {
            field : 'booksetId',
            title : '账套代码',
            sort : true
        }, {
            field : 'cdate',
            title : '会计日期',
            sort : true
        }, {
            field : 'recalTypeFt',
            title : '重算方式',
            sort : true,
            sortField : 'recalType'
        }, {
            field : 'dailydscYieldFt',
            title : '实际日利率',
            sort : true,
            width:250,
            type:"money",pattern: '#.000000000000',
            sortField : 'dailydscYield',
            render : function(data, type, row, meta) {
                if(row.recalType=='03'){
//                    console.log(row.dailydscYield);
//                    console.log(Number(row.dailydscYield).toFixed(12));
                    return  row.dailydscYieldFt;
                    
                }else{
                    return null;
                }
            }
        }, {
            field : 'operation',
            title : 'SysCommon.operation',
            type : 'action',
            buttons : [ 'delt' ]
        } ],
    });

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ValAssetsAmortizeSet001_btn_search').click(function() {
            table.load();
        });
        $('#ValAssetsAmortizeSet001_btn_export').click(function() {
            exportExcel();
        });
        // 绑定新建事件
        $('#ValAssetsAmortizeSet001_btn_add')
                .click(
                        function() {
                            Joyin
                                    .dialog(
                                            JoyinUtil
                                                    .getLabel("新建（<i class='iconfont must' style='display:inline'>*</i>必填）"),
                                            basePath
                                                    + "valuation/assetsamortize/ValAssetsAmortizeSet002/init",
                                            "90%", "95%", {
                                                pageModel : PAGE_MODEL_NEW
                                            }, function() {
                                                table.reload();
                                            });
                        });

    });

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {};
        object = data;
        object.operate = 'delete';
        var message = "是否删除" + "[" + data.busiId + "]产品";
        Joyin.ajaxJson(basePath + 'valuation/assetsamortize/ValAssetsAmortizeSet001/deleteCheck',
                object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage(message, JoyinUtil
                            .getLabel("SysCommon.delete")), function() {

                        WorkFlow.doDeleteList(basePath
                                + 'valuation/assetsamortize/ValAssetsAmortizeSet001/delete',
                                object, "ValAssetsAmortizeSet001_btn_delete",
                                "ValAssetsAmortizeSet002", function() {
                                    table.reload();
                                });

                    });
                });
    });

    /**
     * 导出
     */
    var exportExcel = function() {
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

});