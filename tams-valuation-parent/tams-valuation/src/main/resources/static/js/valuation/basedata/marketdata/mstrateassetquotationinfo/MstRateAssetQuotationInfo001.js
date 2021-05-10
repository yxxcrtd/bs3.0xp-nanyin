/**
 * MstRateAssetQuotationInfo001 计划类（净值型）资产估值行情信息查询（含删除）
 */
$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        return {
            finProdMarketId: $('#finProdMarketId').val(),
            finProdName: $('#finProdName').val(),
            valDate: $('#valDate').val()
        }
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
         * 双击查看
         */
        dblclick: function (table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                basePath + "valuation/basedata/marketdata/mstrateassetquotationinfo/MstRateAssetQuotationInfo002/init",
                "95%", "90%", {
                    pageModel: PAGE_MODEL_REFERENCE,
                    finprodId: data.finprodId,
                    valDate: data.valDate
                });
        },
        cols: [
            {field: 'finprodId', visible: false}
            , {field: 'finProdMarketId', width: 200, title: '资产代码'}
            , {field: 'finProdName', title: '资产简称'}
            , {field: 'valDate', width: 200, title: '估值日期', type: 'date'}
            , {field: 'valuation', title: '估值',type:'number',digit:4}
            , {field: 'importDate', width: 200, title: '导入日'}
            , {field: 'remark', width: 200, title: '备注'}
            , {field: 'operation', width: 200, title: 'SysCommon.operation', type: 'action', buttons: ['edit', 'delt']}
        ]
    });
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function (data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'valuation/basedata/marketdata/mstrateassetquotationinfo/MstRateAssetQuotationInfo002/check', data, function (result) {
            Joyin.confirm("请确认是否要修改？", function(){
                Joyin.dialog("项目类/计划类利率型估值行情编辑",
                    basePath + "valuation/basedata/marketdata/mstrateassetquotationinfo/MstRateAssetQuotationInfo002/init",
                    "95%", "90%", {
                        pageModel: PAGE_MODEL_EDIT,
                        finprodId: data.finprodId,
                        valDate: data.valDate
                    }, function () {
                        table.reload();
                    });
            });
        });
    });

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function (data, index) {
        var object = {selectedItem: [data], operateType: "delete"};
        Joyin.ajaxJson(basePath + 'valuation/basedata/marketdata/mstrateassetquotationinfo/MstRateAssetQuotationInfo001/check', object, function (result) {
            Joyin.confirm("请确认是否要删除?", function () {
                WorkFlow.doDeleteList(basePath + 'valuation/basedata/marketdata/mstrateassetquotationinfo/MstRateAssetQuotationInfo001/delete', object,
                    "MstRateAssetQuotationInfo002_btn_delete", "MstRateAssetQuotationInfo002", function () {
                        table.reload();
                    });
            });
        });
    });
    /**
     * 绑定导出事件
     */
    var exportExcel = function () {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            Joyin.exportExcel(basePath+"valuation/basedata/marketdata/mstrateassetquotationinfo/MstRateAssetQuotationInfo001/export", getSearchParams());
        } else {
            Joyin.exportExcel(basePath+"valuation/basedata/marketdata/mstrateassetquotationinfo/MstRateAssetQuotationInfo001/export", {
                datas : datas
            });
        }
    };
    /**
     * 画面加载完成
     */
    $(function () {
        // 绑定按钮事件
        $('#MstRateAssetQuotationInfo001_btn_search').click(function () {
            table.load();
            $('#MstRateAssetQuotationInfo001_btn_delete').show();
        });
        $('#MstRateAssetQuotationInfo001_btn_export').click(function () {
            exportExcel();
        });
        $('#MstRateAssetQuotationInfo001_btn_add').click(function () {
            Joyin.dialog("项目类/计划类利率型估值行情新建",
                basePath + "valuation/basedata/marketdata/mstrateassetquotationinfo/MstRateAssetQuotationInfo002/init",
                "60%", "75%", {pageModel: PAGE_MODEL_NEW});
        });
        $('#MstRateAssetQuotationInfo001_btn_delete').click(function () {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error("请从列表中选择数据!");
                return;
            }
            var object = {selectedItem: selArr, operateType: "delete"};
            Joyin.ajaxJson(basePath + 'valuation/basedata/marketdata/mstrateassetquotationinfo/MstRateAssetQuotationInfo001/check', object, function (result) {
                Joyin.confirm("请确认是否要删除?", function () {
                    WorkFlow.doDeleteList(basePath + 'valuation/basedata/marketdata/mstrateassetquotationinfo/MstRateAssetQuotationInfo001/delete', object,
                        "MstRateAssetQuotationInfo002_btn_delete", "MstRateAssetQuotationInfo002");
                });
            });
        });
        /**
         * 导入按钮
         */
        $('#MstRateAssetQuotationInfo001_btn_import').click(function() {
            /** modify by yanya on 20210329 修正导入逻辑**/
            Joyin.uploadFile({pageId:'MstRateAssetQuotationInfo001MTM'},function(fileName) {
                Joyin.ajax(basePath + 'valuation/basedata/marketdata/mstrateassetquotationinfo/MstRateAssetQuotationInfo001/checkImportData', {fileName: fileName}, function(checkResult) {
                    if (checkResult) {
                        setTimeout( function () {
                            if(checkResult.data.errorMsg){
                                Joyin.error(checkResult.data.errorMsg);
                                return;
                            }
                            if (checkResult.data.confirmMsg) {
                                Joyin.confirm(checkResult.data.confirmMsg, function () {
                                    var data = {
                                        tempList: checkResult.data.correctData
                                    }
                                    Joyin.ajaxJson(basePath + 'valuation/basedata/marketdata/mstrateassetquotationinfo/MstRateAssetQuotationInfo001/importFile', data,
                                        function (resultObj) {
                                            if (resultObj.statuCode.toString() === '200') {
                                                //Joyin.alert("文件导入成功！");
                                                table.reload();
                                                return;
                                            }
                                        });
                                },{}, function () {
                                    layui.layer.closeAll()
                                    table.reload();
                                });
                            }else {
                                var data = {
                                    tempList: checkResult.data.correctData
                                }
                                Joyin.ajaxJson(basePath + 'valuation/basedata/marketdata/mstrateassetquotationinfo/MstRateAssetQuotationInfo001/importFile', data,
                                    function (resultObj) {
                                        if (resultObj.statuCode.toString() === '200') {
                                            //Joyin.alert("文件导入成功！");
                                            table.reload();
                                            return;
                                        }
                                    });
                            }
                        }
                        ,1000);
                    }
                });
            },function() {
                table.reload();
            });
        });
    });
}());