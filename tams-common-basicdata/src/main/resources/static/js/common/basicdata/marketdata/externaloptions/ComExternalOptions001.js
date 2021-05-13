$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {

            targetId: $.trim($('#targetId').val()),
            finprodId: $.trim($('#finprodId').val()),
            valDate: $.trim($('#valDate').val()),
            targetType: $.trim($('#targetType').val()),
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
        checkbox: true,
        cellMinWidth : 100,
        /*修改列宽 lishuping 20190829 start*/
        cols: [
            {field:'targetId', width:160, title: '合约标的' },
            {field:'finprodId2', width:160, title: '交易编号' },
            {field:'targetId', width:160, title: '标的代码' },
            {field:'targetName', width:160, title: '合约标的' },
            {field:'targetTypeStr', width:160, title: '标的类型' },
            {field:'targetType', width:160, title: '标的类型',visible:false },
            {field:'valDate', width:160, type:'date', title: '估值日期' },
            {field:'valuation',type:"money", width:160, title: '收盘价' ,digit:3},
            /*{field:'publishDate', width:160, type:'date', title: '公布日' },*/
            {field:'operation', width:160,title: 'SysCommon.operation', type:'action',
                buttons: ['edit', 'delt']}
        ],
        /*修改列宽 lishuping 20190829 end*/
    });

    /**
     * 事件绑定
     */
    var tableBind = function () {

        /**
         * 绑定事件：外汇估值信息 - 新建
         */
        $("#ComExternalOptions001_btn_add").click(function(){
            Joyin.dialog(JoyinUtil.getLabel("新建场外期权信息（ "+ "<span style='color:red'> * </span>" + "必填）"),
                basePath + "common/basicdata/marketdata/externaloptions/ComExternalOptions002/init",
                "85%", "85%", {pageModel : PAGE_MODEL_NEW,  operateType:"new"},function(){
                    table.reload();

                });
        });

        /**
         * 绑定事件：外汇估值信息 - 编辑
         */
        table.bind('click', '.edit', function(data) {
            var object = {};
            object.finprodId = data.finprodId;
            Joyin.confirm("请确认是否修改?", function(){
                Joyin.dialog(JoyinUtil.getLabel("编辑场外期权信息（ "+ "<span style='color:red'> * </span>" + "必填）"),
                    basePath + "common/basicdata/marketdata/externaloptions/ComExternalOptions002/init",
                    "80%", "85%", {pageModel:PAGE_MODEL_EDIT, finprodId2: data.finprodId2, valmodelId:data.valmodelId,datasourceType: data.datasourceType, valDate: data.valDate, operateType:"edit"},function(){
                        table.reload();
                    });
            });
        });

        /**
         * 删除-走流程
         */
        table.bind('click', '.delt', function(data) {
            var object = {};
            object.finprodId2 = data.finprodId2;
            object.targetId = data.targetId;
            object.valDate = data.valDate;
            object.valuation = data.valuation;
            object.publishDate = data.publishDate;
            var object = {selectedItem: [data], operateType:"delete"};
            Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/externaloptions/ComExternalOptions001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(
                        basePath + 'common/basicdata/marketdata/externaloptions/ComExternalOptions001/delete',
                        object,
                        "ComExternalOptions001_btn_delete",
                        "ComExternalOptions002",
                        function() {
                            table.reload();
                        }
                    );
                });
            });
        });
        $('#ComExternalOptions001_btn_delete').click(function () {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType: "delete"};
            Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/externaloptions/ComExternalOptions001/check', object, function (result) {
                Joyin.confirm("请确认是否删除?", function () {
                    WorkFlow.doDeleteList(basePath + 'common/basicdata/marketdata/externaloptions/ComExternalOptions001/delete', object,
                        "ComExternalOptions001_btn_delete", "ExternalOptions002");
                });
            });
        });
        /** 事件绑定: 删除 */
         /*$('#ComExternalOptions001_btn_delete').click(function() {
            var object = {};
            var rowArr = table.getAllCheckedRows();// 所有选中行数据
            if(rowArr == null || rowArr.length === 0 || rowArr.length >1){
                Joyin.error("请选择1条记录进行删除。");
            }else{
                object.finprodId2 = rowArr[0].finprodId2;
                object.targetId = rowArr[0].targetId;
                object.valDate = rowArr[0].valDate;
                object.valuation = rowArr[0].valuation;
                object.publishDate = rowArr[0].publishDate;
                Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/externaloptions/ComExternalOptions001/check', object, function(result) {
                    object.operateType = "delete";
                    Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                        WorkFlow.doDeleteList(basePath + 'common/basicdata/marketdata/externaloptions/ComExternalOptions001/delete', object,
                            "ComExternalOptions001_btn_delete",
                            "ComExternalOptions002",
                            function() {
                            vatTaxTable.reload();
                        });
                    });
                });
            }
        });*/
        /**
         * 导入按钮
         */
        $('#ComExternalOptions001_btn_import').click(function() {
            Joyin.uploadFile({pageId:'ComExternalOptions001MTM'},function(fileName) {
                Joyin.ajax(basePath + 'common/basicdata/marketdata/externaloptions/ComExternalOptions001/checkImportData', {fileName: fileName}, function(checkResult) {
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
                                    Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/externaloptions/ComExternalOptions001/importFile', data,
                                        function (resultObj) {
                                            if (resultObj.statuCode.toString() === '200') {
                                                /** modify by yanya on 20210329 修正导入逻辑**/
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
                                Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/externaloptions/ComExternalOptions001/importFile', data,
                                    function (resultObj) {
                                        if (resultObj.statuCode.toString() === '200') {
                                            /** modify by yanya on 20210329 修正导入逻辑**/
                                            //Joyin.alert("文件导入成功！");
                                            table.reload();
                                            return;
                                        }
                                    });
                            }
                        },1000);
                    }
                });
            },function() {
                table.reload();
            });
        });
    };


    /**
     * 导出
     */
    var exportExcel = function() {
        if (table) {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length === 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ComExternalOptions001_btn_search').click(function() {
            debugger;
            table.load();
        });

        // 绑定导出事件
        $('#ComExternalOptions001_btn_export').click(function() {
            exportExcel();
        });

        //新建、修改、删除事件绑定
        tableBind();
    });
    

});
