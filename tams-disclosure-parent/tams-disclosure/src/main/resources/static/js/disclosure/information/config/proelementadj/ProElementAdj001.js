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
        var adjField = $('#adjField').val()
        if(adjField && adjField != ''){
            adjField = adjField.join(',');
        }else {
            adjField = null;
        }
         return {
            prodRegistNo:prodRegistNo,
            prodId:prodId,
            isSalesAgency:$('#isSalesAgency').val(),
            adjField:adjField,
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
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("产品要素调整查看"),
                basePath + "disclosure/information/config/proelementadj/ProElementAdj002/init",
                "99%", "99%", data,function(){table.reload();});
        },
        /**
         * 定义表头
         */
        cols: [
            {field:'prodAdjustId',  visible: false},
            {field:'dataStatusStr',  title: 'ProElementAdj001.dataStatus',sortField:"dataStatus"},//数据状态
            {field:'prodRegistNo',  title: 'ProElementAdj001.prodRegistNo',sortField:"prodRegistNo"},//产品登记编码
            {field:'prodId',  title: 'ProElementAdj001.prodId',sortField:"prodId"}//子产品代码
            ,{field:'adjReason',  title: '调整此产品要素原因',sortField:"adjReason"} //调整此产品要素原因
            ,{field:'prodAdjRemark',  title: 'ProElementAdj001.prodAdjRemark',sortField:"prodAdjRemark"} //此产品要素调整（补充性）描述文字1
            ,{field:'prodAdjRemark2',  title: 'ProElementAdj001.prodAdjRemark2',sortField:"prodAdjRemark2"} //此产品要素调整（补充性）描述文字2
            ,{field:'adjField',  title: 'ProElementAdj001.adjField',sortField:"adjField"} //调整要素
            ,{field:'preAdjust',  title: '调整前',sortField:"preAdjust"} //调整前
            ,{field:'afterAdjust',  title: '调整后',sortField:"afterAdjust"} //调整后
            ,{field:'effDate',  title: '生效时间',sortField:"effDate",type:'date'}//生效时间
            ,{field:'adjReason',  title: '对此要素调整（原因）',sortField:"adjReason"} //对此要素调整（原因）
            ,{field:'fieldAdjRemark',  title: '调整此要素（补充说明文1）',sortField:"fieldAdjRemark"} //调整此要素（补充说明文1）
            ,{field:'fieldAdjRemark2',  title: '调整此要素（补充说明文2）',sortField:"fieldAdjRemark2"} //调整此要素（补充说明文2）
            ,{field:'rptIssueDate',  title: '报告发布日期',sortField:"rptIssueDate",type:'date'}//报告发布日期
            ,{field:'updateTime',  title: '更新时间',sortField:"updateTime",type:'date'} //更新时间
            ,{field:'updateUser',title:'更新人',sortField:"updateUser"}//更新人
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt'],
                render: function (data, type, row, meta) {
                    if (row.dataStatus == '02')
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
        Joyin.ajaxJson(basePath + 'disclosure/information/config/proelementadj/ProElementAdj001/check', data, function(editResult){
            Joyin.dialog(JoyinUtil.getLabel("产品要素调整编辑"),
                basePath + "disclosure/information/config/proelementadj/ProElementAdj002/init",
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
        Joyin.ajaxJson(basePath + 'disclosure/information/config/proelementadj/ProElementAdj001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'disclosure/information/config/proelementadj/ProElementAdj001/delete', object,
                    "ProElementAdj001_btn_delete", "ProElementAdj002",function() {
                        table.reload();
                    });
            });
        });
    });



    /**
     * 导入按钮
     */

    $('#ProElementAdj001_btn_import').click(function() {
        Joyin.confirm("请确认是否要导入数据？", function () {
        Joyin.uploadFile({pageId:'ProElementAdjInfo'},function(fileName) {
            Joyin.ajax(basePath + 'disclosure/information/config/proelementadj/ProElementAdj001/checkImportData', {fileName: fileName}, function(checkResult) {
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
                                    Joyin.ajaxJson(basePath + 'disclosure/information/config/proelementadj/ProElementAdj001/importFile', data,
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
                                Joyin.ajaxJson(basePath + 'disclosure/information/config/proelementadj/ProElementAdj001/importFile', data,
                                    function (resultObj) {
                                        if (resultObj.statuCode.toString() === '200') {
                                            /** modify by yanya on 20210329 修正导入逻辑**/
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
        $('#ProElementAdj001_btn_export').click(function () {
            exportExcel();
        });
        //初始化查询
        $('#ProElementAdj001_btn_search').click(function () {
            table.load();
            $('#ProElementAdj001_btn_delete').show();
        });
        //新建(点击打开新建画面)
        $('#ProElementAdj001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("产品要素调整新建"),
                basePath + "disclosure/information/config/proelementadj/ProElementAdj002/init","100%", "100%", {pageModel:PAGE_MODEL_NEW},
                function() {
                    table.reload();
                });
        });
        //删除
        $('#ProElementAdj001_btn_delete').click(function () {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            for(var i=0;i<selArr.length;i++){
                if(selArr[i].dataStatus =="02"){
                    return Joyin.error("只能删除有效数据，请重新选择");
                }
            }
            var object = {selectedItem: selArr, operateType: "delete"};
            Joyin.ajaxJson(basePath + 'disclosure/information/config/proelementadj/ProElementAdj001/check', object, function (result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")), function () {
                    WorkFlow.doDeleteList(basePath + 'disclosure/information/config/proelementadj/ProElementAdj001/delete', object,
                        "ProElementAdj001_btn_delete", "ProElementAdj002");
                });
            });
        });
    });
}());
