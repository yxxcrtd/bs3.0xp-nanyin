/**
 * 产品费用信息维护信息
 */
$(function() {

    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var startDate=$('#payDate').startDate();
        var endDate=$('#payDate').endDate();
        return {
            prodId : $('#prodId').val(),
            feeType : $('#feeType').val(),
            status : $('#status').val(),
            startDate : startDate,
            endDate : endDate
        }
    };
    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax : {
            url : 'queryList',
            type : 'POST',
            data : function() {
                return getSearchParams();
            }
        },
        /**
         * 双击查看
         */
        dblclick: function (table, data) {
            debugger;
            Joyin.dialog(JoyinUtil.getLabel("产品费用交易信息维护查看"),
                basePath + "disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf002/init",
                "100%", "100%", {
                    pageModel: PAGE_MODEL_REFERENCE,
                    prodId: data.prodId,
                    feeType: data.feeType,
                    payDate: data.payDateStr
                });
        },
        cols : [ {
            field : 'statusStr',width:'10%',
            title : '数据状态',
            sortField : 'STATUS'
        }, {
            field : 'status',width:'10%',
            visible :false
        }, {
            field : 'prodId',width:'10%',
            title : '产品主代码'
        }, {
            field : 'prodName',width:'10%',
            title : '产品名称'
        }, {
            field : 'feeTypeStr',width:'10%',
            title : '费用类型',
            sortField : 'FEE_TYPE'
        }, {
            field : 'feeType',width:'10%',
            visible :false
        },  {
            field : 'paidFee',width:'10%',type: 'number',digit:2,
            title : '费用金额'
        }, {
            field : 'payDateStr',width:'10%',
            title : '支付日',
            sortField : 'PAY_DATE'
        }, {
            field : 'accrual',width:'10%',type: 'number',digit:2,
            title : '自成立以来发生额（贷方）'
        }, {
            field : 'errmsg',width:'10%',
            title : '错误信息'
        }, {
            field : 'updateTime',width:'10%',
            title : '更新时间',
            render : function(data, type, row, meta) {
                if(row.updateTime != null && row.updateTime != ''){
                    return new Date(row.updateTime).Format('yyyy-MM-dd hh:mm:ss');
                }
            }
        }, {
            field : 'updateUser',width:'10%',
            title : '更新人'
        },
            { field:'operation',width:'10%', title: '操作', type:'action', buttons: ['edit', 'delt'],
                render: function (data, type, row, meta) {
                    if (row.status == 'D')
                        return '';
                    else
                        return '<i class="btn layui-icon edit edit" title="编辑" data-ignore="false"></i> ' +
                            '<i class="btn layui-icon delt delt" title="删除" data-ignore="false"></i>';
                }
            }]
    });

    /**
     * 绑定按钮事件
     */
    $(function() {

        init();

        /**
         * 查询按钮
         */
        $('#SvrProdFeeDealInterf001_btn_search').click(function() {
            table.load();
        });

        //新建点击事件
        $('#SvrProdFeeDealInterf001_btn_add').click(function() {
            var data={operateType:"add", pageModel:PAGE_MODEL_NEW};
            Joyin.dialog('产品费用交易信息维护新建',basePath+'disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf002/init', '100%', '100%',data,function(){table.reload();});
        });

        // 行内删除按钮点击事件
        table.bind('click', '.delt', function(data, index) {
            var object = {selectedItem: [data], operateType: "delete"};//请求参数
            Joyin.ajaxJson(basePath + 'disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage('REPORT_COMMON_C_0003'), function() {
                    var deleteUrl = basePath + 'disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf001/deleteData';
                    WorkFlow.doDeleteList(deleteUrl, object, "SvrProdFeeDealInterf001_btn_delete", "SvrProdFeeDealInterf002",function(){
                        table.reload();
                    });
                });
            });
        });

        // 删除按钮点击事件 删除数据
        $('#SvrProdFeeDealInterf001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("请至少选择一条记录"));
                return;
            }
            for(var i=0;i<selArr.length;i++){
                if(selArr[i].status =="D"){
                    return Joyin.error("不能删除已删除的数据，请重新选择");
                }
                // selArr[i].operateType =  "delete";
            }
            var object = {selectedItem: selArr, operateType: "delete"};//请求参数
            Joyin.ajaxJson(basePath + 'disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf001/deleteData', object, "SvrProdFeeDealInterf001_btn_delete", "SvrProdFeeDealInterf002", function () {
                        table.reload();//删除事件请求及回调函数
                    });
                });
            });
        });

        /**
         * 编辑页面
         */
        table.bind('click', '.edit', function(data, index) {
            var object = {pageModel:PAGE_MODEL_EDIT,
                prodId: data.prodId,
                feeType: data.feeType,
                payDate: data.payDateStr,
                operateType: "edit"};//请求参数
            Joyin.ajaxJson(basePath + 'disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf001/check', object, function(result) {
                Joyin.dialog(JoyinUtil.getLabel("产品费用交易信息维护编辑"),
                    JoyinUtil.getRealPath("disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf002/init"),
                    "100%", "100%", object,
                    function(data){
                        table.reload();
                    });
            });
        });
        // $('#SvrProdFeeDealInterf001_btn_import').click(function() {
        //     Joyin.confirm(JoyinUtil.getLabel("请确认是否要导入数据？"), function() {
        //         Joyin.uploadFile({pageId:'SvrProdFeeDealInterf002',hideTpl : true},function(fileName) {
        //             Joyin.ajax(basePath +"disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf001/importFile", {
        //                 fileName : fileName
        //             },function(result){
        //                 if(!result || !($.isArray(result) && result.length > 0)) {
        //                     layui.layer.closeAll()
        //                     table.reload();
        //                 }
        //             });
        //         });
        //     });
        // });
        $('#SvrProdFeeDealInterf001_btn_import').click(function() {
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
                                if (checkResult.data.confirmMsg3) {
                                    Joyin.confirm(checkResult.data.confirmMsg3, function () {
                                        if (checkResult.data.confirmMsg) {
                                            Joyin.confirm(checkResult.data.confirmMsg, function () {
                                                if (checkResult.data.confirmMsg2) {
                                                    Joyin.confirm(checkResult.data.confirmMsg2, function () {
                                                        var data = {
                                                            tempList: checkResult.data.correctData
                                                        }
                                                        Joyin.ajaxJson(basePath + 'disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf001/importFile', data,
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
                                                    Joyin.ajaxJson(basePath + 'disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf001/importFile', data,
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
                                                    Joyin.ajaxJson(basePath + 'disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf001/importFile', data,
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
                                                Joyin.ajaxJson(basePath + 'disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf001/importFile', data,
                                                    function (resultObj) {
                                                        if (resultObj.statuCode.toString() === '200') {
                                                            table.reload();
                                                            return;
                                                        }
                                                    });
                                            }
                                        }

                                    },{}, function () {
                                        layui.layer.closeAll()
                                        table.reload();
                                    });
                                }else{
                                    if (checkResult.data.confirmMsg) {
                                        Joyin.confirm(checkResult.data.confirmMsg, function () {
                                            if (checkResult.data.confirmMsg2) {
                                                Joyin.confirm(checkResult.data.confirmMsg2, function () {
                                                    var data = {
                                                        tempList: checkResult.data.correctData
                                                    }
                                                    Joyin.ajaxJson(basePath + 'disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf001/importFile', data,
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
                                                Joyin.ajaxJson(basePath + 'disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf001/importFile', data,
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
                                                Joyin.ajaxJson(basePath + 'disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf001/importFile', data,
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
                                            Joyin.ajaxJson(basePath + 'disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf001/importFile', data,
                                                function (resultObj) {
                                                    if (resultObj.statuCode.toString() === '200') {
                                                        table.reload();
                                                        return;
                                                    }
                                                });
                                        }
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
         * 导出按钮
         */
        $('#SvrProdFeeDealInterf001_btn_export').click(function() {
            exportExcel();
        });

        /**
         * 绑定导出事件
         */
        var exportExcel = function() {
            var param = getSearchParams();
            // var rows = table.getAllCheckedRows();
            // if(rows.length > 0) {
            //     var keyList = [];
            //     $.each(rows, function(i, row) {
            //         keyList.push(row.posId);
            //     });
            //     param.keys = keyList.join(',');
            // }
            Joyin.exportExcel("export", param);
        };
        function init(){
        }
    });
}());