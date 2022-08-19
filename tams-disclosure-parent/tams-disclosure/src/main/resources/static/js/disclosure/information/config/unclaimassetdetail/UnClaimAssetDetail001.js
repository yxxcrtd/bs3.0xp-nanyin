/**
 * 非标债权类资产明细维护查询
 */
(function(){
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            prodId: $('#prodId').val()==null?null:$('#prodId').val().join(","),
            effDateStart: $.trim($('#effDate').startDate()),
            effDateEnd: $.trim($('#effDate').endDate()),
            effEndDateStart: $.trim($('#effEndDate').startDate()),
            effEndDateEnd: $.trim($('#effEndDate').endDate()),
            dataStatus:$('#dataStatus').val()
        }
    };
    // 新建
    function showDialog() {
        Joyin.dialog(JoyinUtil.getLabel('UnClaimAssetDetail002'), basePath +'/disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail002/init', '98%', '95%', {pageModel:PAGE_MODEL_NEW},function(){
            table.reload();
        });

    }

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
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("UnClaimAssetDetail002.reference"),
                JoyinUtil.getRealPath("disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail002/init"),
                "95%", "95%",data,function(){table.reload();});
        },

        cols:[
            {field:'nostdAssetId', visible: false},
            {field:'dataStatusStr', title: 'UnClaimAssetDetail002.dataStatus',width:150, sortField:'datastatus'},
            {field:'prodId', title: 'UnClaimAssetDetail002.prodId', width:150},
            {field:'prodName', title: 'UnClaimAssetDetail002.prodName',width:150},
            {field:'effDateStr', title: 'UnClaimAssetDetail002.effDate',  width:150,type:'date', sortField:'effDate'}
            ,{field:'effEndDateStr', title: 'UnClaimAssetDetail002.effEndDate', width:150,type:'date', sortField:'effEndDate'}
            ,{field:'updateTime',type: 'datetime', title: 'UnClaimAssetDetail002.updateTime', width:150}
            ,{field:'updateUser', title: 'UnClaimAssetDetail002.updateUser', width:150}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt'],
                render: function (data, type, row, meta) {
                    if (row.dataStatus == 'D')
                        return '';
                    else
                        return '<i class="btn layui-icon edit edit" title="编辑" data-ignore="false"></i> ' +
                            '<i class="btn layui-icon delt delt" title="删除" data-ignore="false"></i>';
                }
            }
        ],
    });
    /**
     * 导出
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();//请求参数json格式
        Joyin.exportExcel('export', paramObj);//导出（共通）
    };

     /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.dialog(JoyinUtil.getLabel("非标债权类资产明细编辑"),
            basePath + "disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail002/init",
            "95%", "90%", data,
            // {pageModel:PAGE_MODEL_EDIT,
            //     prodId:data.prodId,effDate:data.effDate,effEndDate:data.effEndDate},
            function(){table.reload();})
    });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        // Joyin.ajaxJson(basePath + 'disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail001/delete', object, "UnClaimAssetDetail001_btn_delete", "UnClaimAssetDetail002",function(){
                    table.reload();
                });
            });
        // });
    });

    $(function () {
        // 绑定按钮事件
        $('#UnClaimAssetDetail001_btn_search').click(function() {
            table.load();
            $('#UnClaimAssetDetail001_btn_delete').show();
        });
        $('#UnClaimAssetDetail001_btn_export').click(function() {
            exportExcel();
        });
        $('#UnClaimAssetDetail001_btn_add').click(function() {
            showDialog();
        });
        $('#UnClaimAssetDetail001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            for(var i=0;i<selArr.length;i++){
                if(selArr[i].dataStatus=='D'){
                    Joyin.error("请选择有效状态的数据进行删除");
                    return;
                }
            }
            var object = {
                selectedItem: selArr,
                operateType:"delete"
            };

            // Joyin.ajaxJson(basePath + 'disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail001/delete', object, "UnClaimAssetDetail001_btn_delete", "UnClaimAssetDetail002",function(){
                    });
                });
            // });
        });

        // 导入按钮点击事件
        // $('#UnClaimAssetDetail001_btn_import').click(function() {
        //     Joyin.confirm(JoyinUtil.getLabel("请确认是否要导入数据？"), function() {
        //         Joyin.uploadFile(function(fileName) {
        //             Joyin.ajax(basePath + 'disclosure/information/config/unclaimassetdetail/UnClaimAssetdetail001/checkImportData', {fileName: fileName}, function(checkResult) {
        //                 Joyin.ajax(basePath +"disclosure/information/config/unclaimassetdetail/UnClaimAssetdetail001/importFile", {
        //                     fileName : fileName
        //                 },function(result){
        //                     if(!result || !($.isArray(result) && result.length > 0)) {
        //                         layui.layer.closeAll()
        //                         table.reload();
        //                     }
        //                 });
        //             });
        //         });
        //     });
        // });


        $('#UnClaimAssetDetail001_btn_import').click(function() {
            Joyin.confirm("请确认是否要导入数据？", function () {
                Joyin.uploadFile(function(fileName) {
                    Joyin.ajax(basePath + 'disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail001/checkImportData', {fileName: fileName}, function(checkResult) {
                        if (checkResult) {
                            setTimeout( function () {
                                    if(checkResult.data.errorMsg){
                                        Joyin.error(checkResult.data.errorMsg);
                                        return;
                                    }
                                    if (checkResult.data.confirmMsg) {
                                        Joyin.confirm(checkResult.data.confirmMsg, function () {
                                            if (checkResult.data.confirmMsg2) {
                                                Joyin.confirm(checkResult.data.confirmMsg2, function () {
                                                    var data = {
                                                        tempList: checkResult.data.correctData
                                                    }
                                                    Joyin.ajaxJson(basePath + 'disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail001/importFile', data,
                                                        function (resultObj) {
                                                            if (resultObj.statuCode.toString() === '200') {
                                                                table.reload();
                                                                return;
                                                            }
                                                        });
                                                },{}, function () {
                                                    layui.layer.closeAll()
                                                    table.reload();
                                                });
                                            }else{
                                                var data = {
                                                    tempList: checkResult.data.correctData
                                                }
                                                Joyin.ajaxJson(basePath + 'disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail001/importFile', data,
                                                    function (resultObj) {
                                                        if (resultObj.statuCode.toString() === '200') {
                                                            table.reload();
                                                            return;
                                                        }
                                                    });
                                            }

                                        },{}, function () {
                                            layui.layer.closeAll()
                                            table.reload();
                                        });
                                    }else {
                                        if (checkResult.data.confirmMsg2) {
                                            Joyin.confirm(checkResult.data.confirmMsg2, function () {
                                                var data = {
                                                    tempList: checkResult.data.correctData
                                                }
                                                Joyin.ajaxJson(basePath + 'disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail001/importFile', data,
                                                    function (resultObj) {
                                                        if (resultObj.statuCode.toString() === '200') {
                                                            table.reload();
                                                            return;
                                                        }
                                                    });
                                            },{}, function () {
                                                layui.layer.closeAll()
                                                table.reload();
                                            });
                                        }else{
                                            var data = {
                                                tempList: checkResult.data.correctData
                                            }
                                            Joyin.ajaxJson(basePath + 'disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail001/importFile', data,
                                                function (resultObj) {
                                                    if (resultObj.statuCode.toString() === '200') {
                                                        table.reload();
                                                        return;
                                                    }
                                                });
                                        }
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

    });
}());