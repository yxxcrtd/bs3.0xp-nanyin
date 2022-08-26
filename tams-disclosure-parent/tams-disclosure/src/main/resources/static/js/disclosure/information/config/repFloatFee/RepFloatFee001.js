$(function() {

    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var startDate=$('#affirmDate').startDate();
        var endDate=$('#affirmDate').endDate();
        return {
            prodId : $('#prodId').val() == null ? null : $('#prodId').val().join(","),
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
            Joyin.dialog(JoyinUtil.getLabel("产品超额业绩报酬信息维护查看"),
                basePath + "disclosure/information/config/repFloatFee/RepFloatFee002/init",
                "99%", "99%", {
                    pageModel: PAGE_MODEL_REFERENCE,
                    prodId: data.prodId,
                    affirmDate: data.affirmDateStr
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
            title : '子产品代码'
        }, {
            field : 'prodName',width:'10%',
            title : '产品名称'
        }, {
            field : 'affirmDateStr',width:'10%',
            title : '确认日',
            sortField : 'affirmDate'
        }, {
            field : 'businessCodeStr',width:'10%',
            title : '业务代码',
            sortField : 'businessCode'
        }, {
            field : 'affirmNum',width:'10%',
            title : '确认份额',
            type : 'number',digit:2
        }, {
            field : 'affirmAmt',width:'10%',
            title : '确认金额',
            type : 'number',digit:2
        }, {
            field : 'aboveAmt',width:'10%',
            title : '超额报酬',
            type : 'number',digit:2
        }, {
            field : 'errorMsg',width:'10%',
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
        $('#RepFloatFee001_btn_search').click(function() {
            table.load();
        });

        //新建点击事件
        $('#RepFloatFee001_btn_add').click(function() {
            var data={operateType:"add", pageModel:PAGE_MODEL_NEW};
            Joyin.dialog('产品超额业绩报酬信息维护-新建',basePath+'disclosure/information/config/repFloatFee/RepFloatFee002/init', '90%', '80%',data,function(){table.reload();});
        });

        // 行内删除按钮点击事件
        table.bind('click', '.delt', function(data, index) {
            var object = {selectedItem: [data], operateType: "delete"};//请求参数
            Joyin.ajaxJson(basePath + 'disclosure/information/config/repFloatFee/RepFloatFee001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage('REPORT_COMMON_C_0003'), function() {
                    var deleteUrl = basePath + 'disclosure/information/config/repFloatFee/RepFloatFee001/deleteData';
                    WorkFlow.doDeleteList(deleteUrl, object, "RepFloatFee001_btn_delete", "RepFloatFee002",function(){
                        table.reload();
                    });
                });
            });
        });

        // 删除按钮点击事件 删除数据
        $('#RepFloatFee001_btn_delete').click(function() {
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

            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'disclosure/information/config/repFloatFee/RepFloatFee001/deleteData', object, "RepFloatFee001_btn_delete", "RepFloatFee002", function () {
                    table.reload();//删除事件请求及回调函数
                });
            });
        });

        /**
         * 编辑页面
         */
        table.bind('click', '.edit', function(data, index) {
            var object = {pageModel:PAGE_MODEL_EDIT,
                prodId: data.prodId,
                affirmDate: data.affirmDateStr,
                operateType: PAGE_MODEL_EDIT};//请求参数
            Joyin.dialog(JoyinUtil.getLabel("产品超额业绩报酬信息维护-编辑"),
                JoyinUtil.getRealPath("disclosure/information/config/repFloatFee/RepFloatFee002/init"),
                "90%", "80%", object,
                function(data){
                    table.reload();
                });
        });

        $('#RepFloatFee001_btn_import').click(function() {
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
                                                            Joyin.ajaxJson(basePath + 'disclosure/information/config/repFloatFee/RepFloatFee001/importFile', data,
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
                                                        Joyin.ajaxJson(basePath + 'disclosure/information/config/repFloatFee/RepFloatFee001/importFile', data,
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
                                                        Joyin.ajaxJson(basePath + 'disclosure/information/config/repFloatFee/RepFloatFee001/importFile', data,
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
                                                    Joyin.ajaxJson(basePath + 'disclosure/information/config/repFloatFee/RepFloatFee001/importFile', data,
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
                                                        Joyin.ajaxJson(basePath + 'disclosure/information/config/repFloatFee/RepFloatFee001/importFile', data,
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
                                                    Joyin.ajaxJson(basePath + 'disclosure/information/config/repFloatFee/RepFloatFee001/importFile', data,
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
                                                    Joyin.ajaxJson(basePath + 'disclosure/information/config/repFloatFee/RepFloatFee001/importFile', data,
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
                                                Joyin.ajaxJson(basePath + 'disclosure/information/config/repFloatFee/RepFloatFee001/importFile', data,
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
                    layui.layer.closeAll()
                    table.reload();
                });
            });
        });
        /**
         * 导出按钮
         */
        $('#RepFloatFee001_btn_export').click(function() {
            exportExcel();
        });

        /**
         * 绑定导出事件
         */
        var exportExcel = function() {
            var param = getSearchParams();
            Joyin.exportExcel("export", param);
        };
        function init(){
        }
    });
}());