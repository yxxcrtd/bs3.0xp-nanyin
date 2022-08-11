(function () {
    /**
     * 获取查询参数
     */
    // var getSearchParams = function () {
    //     var data = $.serializeToObject('#searchForm');
    //     return data;
    // };

    var getSearchParams = function() {
        var prodRegistNo = $('#prodRegistNo').val();
        if(prodRegistNo && prodRegistNo != ''){
            prodRegistNo = prodRegistNo.join(',');
        }else {
            prodRegistNo = null;
        }
        var prodId = $('#prodId').val()
        if(prodId && prodId != ''){
            prodId = prodId.join(',');
        }else {
            prodId = null;
        }
         return {
            prodRegistNo:prodRegistNo,
            prodId:prodId,
            rptIssueDateStart:$('#rptIssueDate').startDate(),
            rptIssueDateEnd:$('#rptIssueDate').endDate(),
            dataStatus:$('#dataStatus').val()
        };
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
            data.pageModel = PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("巨额赎回信息维护信息查看"),
                basePath + "disclosure/information/config/hugeinfopro/HugeInfoPro002/init",
                "99%", "99%", data);
        },
        /**
         * 定义表头
         */
        cols: [
            {field:'redemptionId',  visible: false},
            {field:'dataStatusStr',  title: 'ProElementAdj001.dataStatus',sortField:"dataStatus"},//数据状态
            {field:'prodRegistNo',  title: 'ProElementAdj001.prodRegistNo',sortField:"prodRegistNo"},//产品登记编码
            {field:'prodId',  title: 'ProElementAdj001.prodId',sortField:"prodId"}//子产品代码
            ,{field:'dealDate',  title: '巨额赎回日期',sortField:"dealDate",type:'date'} //巨额赎回时间
            ,{field:'triggerRemark',  title: 'HugeInfoPro001.triggerRemark',sortField:"triggerRemark"} //触发的情形说明文字
            ,{field:'rejectRemark',  title: 'HugeInfoPro001.rejectRemark',sortField:"rejectRemark"} //拒绝赎回说明（文字）
            ,{field:'rptIssueDate',  title: '报告发布日期',sortField:"rptIssueDate",type:'date'}//报告发布日期
            ,{field:'updateTime',  title: '更新时间',sortField:"updateTime",type:'datetime'} //更新时间
            ,{field:'updateUser',title:'更新人',sortField:"updateUser"}//更新人
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt'],
                render: function (data, type, row, meta) {
                    if (row.dataStatus == 'D')
                        return '';
                    else
                        return '<i class="btn layui-icon edit edit" title="编辑" data-ignore="false"></i> ' +
                            '<i class="btn layui-icon delt delt" title="删除" data-ignore="false"></i>';
                }
        }
        ]

    });
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'disclosure/information/config/hugeinfopro/HugeInfoPro001/check', data, function(editResult){
            Joyin.dialog(JoyinUtil.getLabel("巨额赎回信息维护编辑"),
                basePath + "disclosure/information/config/hugeinfopro/HugeInfoPro002/init",
                "95%", "90%",data,function() {
                    table.reload();
                });
        });
    });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'disclosure/information/config/hugeinfopro/HugeInfoPro001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'disclosure/information/config/hugeinfopro/HugeInfoPro001/delete', object,
                    "HugeInfoPro001_btn_delete", "HugeInfoPro002",function() {
                        table.reload();
                    });
            });
        });
    });



    /**
     * 导入按钮
     */

    // $('#HugeInfoPro001_btn_import').click(function() {
    //     debugger;
    //     Joyin.confirm("请确认是否要导入数据？", function () {
    //     Joyin.uploadFile({pageId:'HugeInfoPro'},function(fileName) {
    //         Joyin.ajax(basePath + 'disclosure/information/config/hugeinfopro/HugeInfoPro001/checkImportData', {fileName: fileName}, function(checkResult) {
    //             if (checkResult) {
    //                 setTimeout( function () {
    //                         if(checkResult.data.errorMsg){
    //                             Joyin.error(checkResult.data.errorMsg);
    //                             return;
    //                         }
    //                         if (checkResult.data.confirmMsg) {
    //                             Joyin.confirm(checkResult.data.confirmMsg, function () {
    //                                 var data = {
    //                                     tempList: checkResult.data.correctData
    //                                 }
    //                                 Joyin.ajaxJson(basePath + 'disclosure/information/config/hugeinfopro/HugeInfoPro001/importFile', data,
    //                                     function (resultObj) {
    //                                         if (resultObj.statuCode.toString() === '200') {
    //                                             /** modify by yanya on 20210329 修正导入逻辑**/
    //                                             //Joyin.alert("文件导入成功！");
    //                                             table.reload();
    //                                             return;
    //                                         }
    //                                     });
    //                             },{}, function () {
    //                                 layui.layer.closeAll()
    //                                 table.reload();
    //                             });
    //                         }else {
    //                             var data = {
    //                                 tempList: checkResult.data.correctData
    //                             }
    //                             Joyin.ajaxJson(basePath + 'disclosure/information/config/hugeinfopro/HugeInfoPro001/importFile', data,
    //                                 function (resultObj) {
    //                                     if (resultObj.statuCode.toString() === '200') {
    //                                         /** modify by yanya on 20210329 修正导入逻辑**/
    //                                         //Joyin.alert("文件导入成功！");
    //                                         table.reload();
    //                                         return;
    //                                     }
    //                                 });
    //                         }
    //                     }
    //                     ,1000);
    //             }
    //         });
    //     },function() {
    //         table.reload();
    //     });
    //     });
    // });



    $('#HugeInfoPro001_btn_import').click(function() {
        Joyin.confirm("请确认是否要导入数据？", function () {
            Joyin.uploadFile({pageId:'HugeInfoPro'},function(fileName) {
                Joyin.ajax(basePath + 'disclosure/information/config/hugeinfopro/HugeInfoPro001/checkImportData', {fileName: fileName}, function(checkResult) {
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
                                                Joyin.ajaxJson(basePath + 'disclosure/information/config/hugeinfopro/HugeInfoPro001/importFile', data,
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
                                            Joyin.ajaxJson(basePath + 'disclosure/information/config/hugeinfopro/HugeInfoPro001/importFile', data,
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
                                            Joyin.ajaxJson(basePath + 'disclosure/information/config/hugeinfopro/HugeInfoPro001/importFile', data,
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
                                        Joyin.ajaxJson(basePath + 'disclosure/information/config/hugeinfopro/HugeInfoPro001/importFile', data,
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



    /**
     * 导出选中的表格数据
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();//请求参数json格式
        Joyin.exportExcel('export', paramObj);//导出（共通）
    };

    /**
     * 画面加载完成
     */
    $(function () {
        //导出
        $('#HugeInfoPro001_btn_export').click(function () {
            exportExcel();
        });
        //初始化查询
        $('#HugeInfoPro001_btn_search').click(function () {
            table.load();
            $('#HugeInfoPro001_btn_delete').show();
        });
        //新建(点击打开新建画面)
        $('#HugeInfoPro001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("巨额赎回信息维护新建"),
                basePath + "disclosure/information/config/hugeinfopro/HugeInfoPro002/init","100%", "100%", {pageModel:PAGE_MODEL_NEW},
                function() {
                    table.reload();
                });
        });
        //删除
        $('#HugeInfoPro001_btn_delete').click(function () {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            for(var i=0;i<selArr.length;i++){
                if(selArr[i].dataStatus =="D"){
                    return Joyin.error("只能删除有效数据，请重新选择");
                }
            }
            var object = {selectedItem: selArr, operateType: "delete"};
            Joyin.ajaxJson(basePath + 'disclosure/information/config/hugeinfopro/HugeInfoPro001/check', object, function (result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")), function () {
                    WorkFlow.doDeleteList(basePath + 'disclosure/information/config/hugeinfopro/HugeInfoPro001/delete', object,
                        "HugeInfoPro001_btn_delete", "HugeInfoPro002");
                });
            });
        });
    });
}());
