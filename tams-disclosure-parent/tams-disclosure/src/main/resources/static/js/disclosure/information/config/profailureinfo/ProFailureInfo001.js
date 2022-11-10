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
            reportIssueDateStart:$('#reportIssueDate').startDate(),
            reportIssueDateEnd:$('#reportIssueDate').endDate(),
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
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("产品不成立信息维护查看"),
                basePath + "disclosure/information/config/profailureinfo/ProFailureInfo002/init",
                "99%", "99%", data,function(){table.reload();});
        },
        /**
         * 定义表头
         */
        cols: [
            // {field:'prodAdjustId',  visible: false},
            {field:'dataStatusStr',  title: 'ProElementAdj001.dataStatus',sortField:"dataStatus",width: 100},//数据状态
            {field:'prodName',  title: '产品全称',sortField:"prodName"},
            {field:'prodRegistNo',  title: 'ProElementAdj001.prodRegistNo',sortField:"prodRegistNo"},//产品登记编码
            {field:'nocreateReason',  title: '不成立原因',sortField:"nocreateReason"}//不成立原因
            ,{field:'reportIssueDate',  title: '报告发布日期',sortField:"reportIssueDate",type:'date'}//报告发布日期
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
        Joyin.ajaxJson(basePath + 'disclosure/information/config/profailureinfo/ProFailureInfo001/check', data, function(editResult){
            Joyin.dialog(JoyinUtil.getLabel("产品不成立信息维护编辑"),
                basePath + "disclosure/information/config/profailureinfo/ProFailureInfo002/init",
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
        Joyin.ajaxJson(basePath + 'disclosure/information/config/profailureinfo/ProFailureInfo001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'disclosure/information/config/profailureinfo/ProFailureInfo001/delete', object,
                    "ProFailureInfo001_btn_delete", "ProFailureInfo002",function() {
                        table.reload();
                    });
            });
        });
    });



    /**
     * 导入按钮
     */
    $('#ProFailureInfo001_btn_import').click(function() {
        Joyin.confirm(JoyinUtil.getMessage('CHB_ISIMPORT'), function () {
            Joyin.uploadFile(function(fileName) {
                Joyin.ajax('checkImportData', {fileName: fileName}, function() {
                    Joyin.ajax('importFile', {fileName: fileName});
                    /*if (checkResult) {
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
                                                Joyin.ajaxJson(basePath + 'disclosure/information/config/profailureinfo/ProFailureInfo001/importFile', data,
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
                                            Joyin.ajaxJson(basePath + 'disclosure/information/config/profailureinfo/ProFailureInfo001/importFile', data,
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
                                            Joyin.ajaxJson(basePath + 'disclosure/information/config/profailureinfo/ProFailureInfo001/importFile', data,
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
                                        Joyin.ajaxJson(basePath + 'disclosure/information/config/profailureinfo/ProFailureInfo001/importFile', data,
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
                    }*/
                },function() {
                    $('iframe').contents().find("#loading").hide();
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
        $('#ProFailureInfo001_btn_export').click(function () {
            exportExcel();
        });
        //初始化查询
        $('#ProFailureInfo001_btn_search').click(function () {
            table.load();
            $('#ProFailureInfo001_btn_delete').show();
        });
        //新建(点击打开新建画面)
        $('#ProFailureInfo001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("产品不成立信息维护新建"),
                basePath + "disclosure/information/config/profailureinfo/ProFailureInfo002/init","100%", "100%", {pageModel:PAGE_MODEL_NEW},
                function() {
                    table.reload();
                });
        });
        //删除
        $('#ProFailureInfo001_btn_delete').click(function () {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("请至少选择一条记录"));
                return;
            }
            for(var i=0;i<selArr.length;i++){
                if(selArr[i].dataStatus =="D"){
                    return Joyin.error("只能删除有效数据，请重新选择");
                }
            }
            var object = {selectedItem: selArr, operateType: "delete"};
            Joyin.ajaxJson(basePath + 'disclosure/information/config/profailureinfo/ProFailureInfo001/check', object, function (result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")), function () {
                    WorkFlow.doDeleteList(basePath + 'disclosure/information/config/profailureinfo/ProFailureInfo001/delete', object,
                        "ProFailureInfo001_btn_delete", "ProFailureInfo002");
                });
            });
        });
    });
}());
