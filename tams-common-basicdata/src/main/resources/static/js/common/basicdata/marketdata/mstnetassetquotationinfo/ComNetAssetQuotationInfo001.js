/**
 * ComNetAssetQuotationInfo001 计划类（净值型）资产估值行情信息查询（含删除）
 */
$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            finProdMarketId: $('#finProdMarketId').val(),
            finProdName:$('#finProdName').val(),
            valDate:$('#valDate').val()
        }
    };
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        /**
         * 双击查看
         */
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "common/basicdata/marketdata/mstnetassetquotationinfo/ComNetAssetQuotationInfo002/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,
                                   finprodId:data.finprodId,
                                    valDate:data.valDate});
        },
        cols:[
             {field:'finprodId',visible: false}
            ,{field:'finProdMarketId', width:200, title: 'ComNetAssetQuotationInfo001.assetCode'}
            ,{field:'finProdName', title: 'ComNetAssetQuotationInfo001.assetName'}
            ,{field:'valDate', width:200, title: 'ComNetAssetQuotationInfo001.valuationDate',type:'date'}
            ,{field:'valuation', title: 'ComNetAssetQuotationInfo001.netValue',type:'number',digit:4}
            ,{field:'importDate',width:200, title: 'ComNetAssetQuotationInfo001.importDate'}
            ,{field:'operation', width:200,title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/mstnetassetquotationinfo/ComNetAssetQuotationInfo002/check', data, function(result) {
            Joyin.confirm("请确认是否修改?", function(){
                Joyin.dialog(JoyinUtil.getLabel("ValNetAssetQuotationInfo002.edit"),
                    basePath + "common/basicdata/marketdata/mstnetassetquotationinfo/ComNetAssetQuotationInfo002/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_EDIT,
                        finprodId:data.finprodId,
                        valDate:data.valDate},function() {
                        table.reload();
                    });
            });
        });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/mstnetassetquotationinfo/ComNetAssetQuotationInfo001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'common/basicdata/marketdata/mstnetassetquotationinfo/ComNetAssetQuotationInfo001/delete', object,
                        "ComNetAssetQuotationInfo001_btn_delete", "ComNetAssetQuotationInfo002",function() {
                    table.reload();
                });
            });
        });
    });
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        //modify by yanya on 20210209 增加按照页面选择数据进行导出
        //Joyin.exportExcel('export',getSearchParams())
        var selArr = [];
        var datas = null;
        if (table != null && table != '') {
            selArr = table.getAllCheckedRows();
            datas = JSON.stringify(selArr);
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
     * 导入按钮
     */
    $('#ValNetAssetQuotationInfo001_btn_import').click(function() {
        Joyin.uploadFile({pageId:'ValNetAssetQuotationInfo001MTM'},function(fileName) {
            Joyin.ajax(basePath + 'common/basicdata/marketdata/mstnetassetquotationinfo/ComNetAssetQuotationInfo001/checkImportData', {fileName: fileName}, function(checkResult) {
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
                                Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/mstnetassetquotationinfo/ComNetAssetQuotationInfo001/importFile', data,
                                    function (resultObj) {
                                        //modify by yanya on 20210329 提示两遍信息删除
                                        if (resultObj.statuCode.toString() === '200') {
                                            // Joyin.alert("文件导入成功！");
                                            /** modify by yanya on 20210329 修正导入逻辑**/
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
                            Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/mstnetassetquotationinfo/ComNetAssetQuotationInfo001/importFile', data,
                                function (resultObj) {
                                    //modify by yanya on 20210329 提示两遍信息删除
                                    if (resultObj.statuCode.toString() === '200') {
                                        //Joyin.alert("文件导入成功！");
                                        /** modify by yanya on 20210329 修正导入逻辑**/
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
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ComNetAssetQuotationInfo001_btn_search').click(function() {
            table.load();
            $('#ComNetAssetQuotationInfo001_btn_delete').show();
        });
        $('#ComNetAssetQuotationInfo001_btn_export').click(function() {
            exportExcel();
        });
        $('#ComNetAssetQuotationInfo001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("ComNetAssetQuotationInfo002"),
                        basePath + "common/basicdata/marketdata/mstnetassetquotationinfo/ComNetAssetQuotationInfo002/init",
                        "70%", "90%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#ComNetAssetQuotationInfo001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/mstnetassetquotationinfo/ComNetAssetQuotationInfo001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'common/basicdata/marketdata/mstnetassetquotationinfo/ComNetAssetQuotationInfo001/delete', object,
                            "ComNetAssetQuotationInfo001_btn_delete","ComNetAssetQuotationInfo002");
                });
            });
        });
    });
}());