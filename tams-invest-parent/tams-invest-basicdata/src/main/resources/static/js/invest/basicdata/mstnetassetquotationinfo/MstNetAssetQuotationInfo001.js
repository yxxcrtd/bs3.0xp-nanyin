/**
 * MstNetAssetQuotationInfo001 计划类（净值型）资产估值行情信息查询（含删除）
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
                    basePath + "invest/basicdata/mstnetassetquotationinfo/MstNetAssetQuotationInfo002/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,
                                   finprodId:data.finprodId,
                                    valDate:data.valDate});
        },
        cols:[
             {field:'finprodId',visible: false}
            ,{field:'finProdMarketId', width:200, title: 'MstNetAssetQuotationInfo001.assetCode'}
            ,{field:'finProdName', title: 'MstNetAssetQuotationInfo001.assetName'}
            ,{field:'valDate', width:200, title: 'MstNetAssetQuotationInfo001.valuationDate',type:'date'}
            ,{field:'valuation', title: 'MstNetAssetQuotationInfo001.netValue'}
            ,{field:'importDate',width:200, title: 'MstNetAssetQuotationInfo001.importDate'}
            ,{field:'operation', width:200,title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstnetassetquotationinfo/MstNetAssetQuotationInfo002/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("MstNetAssetQuotationInfo002.edit"),
                    basePath + "invest/basicdata/mstnetassetquotationinfo/MstNetAssetQuotationInfo002/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_EDIT,
                                   finprodId:data.finprodId,
                                   valDate:data.valDate},function() {
                                        table.reload();
                                    });
        });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstnetassetquotationinfo/MstNetAssetQuotationInfo001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/basicdata/mstnetassetquotationinfo/MstNetAssetQuotationInfo001/delete', object, 
                        "MstNetAssetQuotationInfo001_btn_delete", "MstNetAssetQuotationInfo002",function() {
                    table.reload();
                });
            });
        });
    });
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#MstNetAssetQuotationInfo001_btn_search').click(function() {
            table.load();
            $('#MstNetAssetQuotationInfo001_btn_delete').show();
        });
        $('#MstNetAssetQuotationInfo001_btn_export').click(function() {
            exportExcel();
        });
        $('#MstNetAssetQuotationInfo001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("MstNetAssetQuotationInfo002"),
                        basePath + "invest/basicdata/mstnetassetquotationinfo/MstNetAssetQuotationInfo002/init",
                        "70%", "90%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#MstNetAssetQuotationInfo001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/basicdata/mstnetassetquotationinfo/MstNetAssetQuotationInfo001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/basicdata/mstnetassetquotationinfo/MstNetAssetQuotationInfo001/delete', object, 
                            "MstNetAssetQuotationInfo001_btn_delete","MstNetAssetQuotationInfo002");
                });
            });
        });        
        // 导入按钮点击事件
        $('#MstNetAssetQuotationInfo001_btn_import').click(function() {
            Joyin.confirm("请确认是否要导入数据？", function() {
                Joyin.uploadFile(function(fileName) {
                    Joyin.ajax(basePath + 'invest/basicdata/mstnetassetquotationinfo/MstNetAssetQuotationInfo001/checkConfirmMsgForImportData', {fileName: fileName}, function() {
                        Joyin.ajax(basePath + 'invest/basicdata/mstnetassetquotationinfo/MstNetAssetQuotationInfo001/importFile', {fileName: fileName});
                    });
                },function() {
                    table.reload();
                });
            });
        });
    });
}());