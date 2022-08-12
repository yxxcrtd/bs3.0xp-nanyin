(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var booksetId = $('#booksetId').val()
        if(booksetId && booksetId != ''){
            booksetId = booksetId.join(',');
        }else {
            booksetId = null;
        }

        var subjectNo = $('#subjectNo').val()
        if(subjectNo && subjectNo != ''){
             subjectNo = subjectNo.join(',');
        }else {
             subjectNo = null;
        }

         return {
             booksetId:booksetId,
             subjectNo:subjectNo,
             balanceDateStart:$('#balanceDate').startDate(),
             balanceDateEnd:$('#balanceDate').endDate(),
             status:$('#status').val()
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
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("科目余额信息信息查看"),
                basePath + "disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro002/init",
                "99%", "99%", data,function(){table.reload();});
        },
        /**
         * 定义表头
         */
        cols: [
            // {field:'prodAdjustId',  visible: false},
            {field:'statusStr',  title: 'ProElementAdj001.dataStatus',sortField:"status",width: 100},//数据状态
            {field:'booksetId',  title: '产品主代码',sortField:"booksetId"},//产品主代码
            {field:'prodName',  title: '产品名称',sortField:"prodName"}//产品名称
            ,{field:'balanceDate',  title: '余额日期',sortField:"balanceDate",type:'date'}//余额日期
            ,{field:'subjectNo',  title: '科目号',sortField:"subjectNo"}//科目号
            ,{field:'oCcy',  title: '原币币种',sortField:"oCcy"}//原币币种
            ,{field:'oAmt',  title: '原币余额',sortField:"oAmt",type:'number'}//原币余额
            ,{field:'tdyODAmt',  title: '原币发生额(借方)',sortField:"tdyODAmt",type:'number'}//原币发生额（借方）
            ,{field:'errormsg',  title: '错误信息',sortField:"errormsg"}//错误信息
            ,{field:'updateTime',  title: '更新时间',sortField:"updateTime",type:'datetime'} //更新时间
            ,{field:'updateUser',title:'更新人',sortField:"updateUser"}//更新人
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt'],
                render: function (data, type, row, meta) {
                    if (row.status == 'D')
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
        Joyin.ajaxJson(basePath + 'disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro001/check', data, function(editResult){
            Joyin.dialog(JoyinUtil.getLabel("科目余额信息编辑"),
                basePath + "disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro002/init",
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
        Joyin.ajaxJson(basePath + 'disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro001/delete', object,
                    "SubBalanceInfoPro001_btn_delete", "SubBalanceInfoPro002",function() {
                        table.reload();
                    });
            });
        });
    });



    /**
     * 导入按钮
     */

    // $('#SubBalanceInfoPro001_btn_import').click(function() {
    //     Joyin.confirm("请确认是否要导入数据？", function () {
    //     Joyin.uploadFile({pageId:'SubBalanceInfo'},function(fileName) {
    //         Joyin.ajax(basePath + 'disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro001/checkImportData', {fileName: fileName}, function(checkResult) {
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
    //                                 Joyin.ajaxJson(basePath + 'disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro001/importFile', data,
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
    //                             Joyin.ajaxJson(basePath + 'disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro001/importFile', data,
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


    $('#SubBalanceInfoPro001_btn_import').click(function() {
        Joyin.confirm("请确认是否要导入数据？", function () {
            Joyin.uploadFile(function(fileName) {
                Joyin.ajax(basePath + 'disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro001/checkImportData', {fileName: fileName}, function(checkResult) {
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
                                                Joyin.ajaxJson(basePath + 'disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro001/importFile', data,
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
                                            Joyin.ajaxJson(basePath + 'disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro001/importFile', data,
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
                                            Joyin.ajaxJson(basePath + 'disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro001/importFile', data,
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
                                        Joyin.ajaxJson(basePath + 'disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro001/importFile', data,
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


    var error1= function(message, callback) {
        var index = layer.alert(message, {
            icon: 2,
            closeBtn: 0,
            area: ["500px", "200px"],
            maxHeight: 230
            // 按钮
        }, function () {
            assertClick();
            layer.close(index);
            if (callback && typeof callback === "function") {
                callback();
            }
        });
        return index;
    }
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
        $('#SubBalanceInfoPro001_btn_export').click(function () {
            exportExcel();
        });
        //初始化查询
        $('#SubBalanceInfoPro001_btn_search').click(function () {
            table.load();
            $('#SubBalanceInfoPro001_btn_delete').show();
        });
        //新建(点击打开新建画面)
        $('#SubBalanceInfoPro001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("科目余额信息新建"),
                basePath + "disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro002/init","100%", "100%", {pageModel:PAGE_MODEL_NEW},
                function() {
                    table.reload();
                });
        });
        //删除
        $('#SubBalanceInfoPro001_btn_delete').click(function () {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("请至少选择一条记录！"));
                return;
            }
            for(var i=0;i<selArr.length;i++){
                if(selArr[i].status =="D"){
                    return Joyin.error("只能删除有效数据，请重新选择");
                }
            }
            var object = {selectedItem: selArr, operateType: "delete"};
            Joyin.ajaxJson(basePath + 'disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro001/check', object, function (result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")), function () {
                    WorkFlow.doDeleteList(basePath + 'disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro001/delete', object,
                        "SubBalanceInfoPro001_btn_delete", "SubBalanceInfoPro002");
                });
            });
        });
    });
}());
