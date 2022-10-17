/**
 * 产品费用信息维护信息
 */
$(function() {

    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var startEffdate=$('#effdate').startDate();
        var endEffdate=$('#effdate').endDate();
        var startEffdelidate=$('#effdelidate').startDate();
        var endEffdelidate=$('#effdelidate').endDate();
        return {
            prodId : $('#prodId').val() == null ? null : $('#prodId').val().join(","),
            status : $('#status').val(),
            startEffdate : startEffdate,
            endEffdate : endEffdate,
            startEffdelidate : startEffdelidate,
            endEffdelidate : endEffdelidate
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
            Joyin.dialog(JoyinUtil.getLabel("关联交易情况维护查看"),
                basePath + "disclosure/information/config/repRelDeal/RepRelDeal002/init",
                "99%", "99%", {
                    pageModel: PAGE_MODEL_REFERENCE,
                    uuid: data.uuid
                });
        },
        cols : [ {
            field : 'statusStr',width:'5%',
            title : '数据状态',
            sortField : 'STATUS'
        }, {
            field : 'status',width:'5%',
            visible :false
        }, {
            field : 'uuid',width:'10%',
            visible :false
        },{
            field : 'prodId',width:'10%',
            title : '产品主代码'
        }, {
            field : 'prodName',width:'10%',
            title : '产品名称'
        }, {
            field : 'creditorAmt',width:'20%',
            title : '投资于关联方发行的证券、关联方作为融资人的非标准化债权类资产业务交易金额',
            type : 'number',digit:2
        }, {
            field : 'bondAmt',width:'10%',
            title : '投资于关联方承销的证券交易金额',
            type : 'number',digit:2
        }, {
            field : 'investAmt',width:'10%',
            title : '与关联方作为交易对手开展的投融资业务交易金额',
            type : 'number',digit:2
        }, {
            field : 'assetAmt',width:'10%',
            title : '投资关联方作为管理人的资产管理产品交易金额',
            type : 'number',digit:2
        },{
            field : 'trustFee',width:'10%',
            title : '支付关联方托管费',
            type : 'number',digit:2
        },{
            field : 'sellFee',width:'10%',
            title : '支付关联方代销费',
            type : 'number',digit:2
        },{
            field : 'otherFee',width:'10%',
            title : '发生其他关联交易金额',
            type : 'number',digit:2
        },{
            field : 'effdateStr',width:'8%',
            title : '生效日期',
            sortField : 'effdate'
        }, {
            field : 'effdelidateStr',width:'8%',
            title : '生效截止日期',
            sortField : 'effdelidate'
        },{
            field : 'updateTime',width:'8%',
            title : '更新时间',
            render : function(data, type, row, meta) {
                if(row.updateTime != null && row.updateTime != ''){
                    return new Date(row.updateTime).Format('yyyy-MM-dd hh:mm:ss');
                }
            }
        }, {
            field : 'updateUser',width:'8%',
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
    function addrule() {
        Joyin.dialog("产品选择",basePath+"disclosure/information/config/disprodplanmanage/DisProdPlanManage003/init","80%","80%",
            {},function () {
                var finprodIds = $('#finprodIds').data('finprodIds');
                if(finprodIds.length ==0){
                    return;
                }
                $('#prodId').textSelect('setValue',finprodIds);
            });
        return;
    };

    /**
     * 绑定按钮事件
     */
    $(function() {

        init();
        $('#RepRelDeal001_btn_treeAdd').click(addrule);

        /**
         * 查询按钮
         */
        $('#RepRelDeal001_btn_search').click(function() {
            table.load();
        });

        //新建点击事件
        $('#RepRelDeal001_btn_add').click(function() {
            var data={operateType:"add", pageModel:PAGE_MODEL_NEW};
            Joyin.dialog('关联交易情况维护-新建',basePath+'disclosure/information/config/repRelDeal/RepRelDeal002/init', '100%', '100%',data,function(){table.reload();});
        });

        // 行内删除按钮点击事件
        table.bind('click', '.delt', function(data, index) {
            var object = {selectedItem: [data], operateType: "delete"};//请求参数
            Joyin.ajaxJson(basePath + 'disclosure/information/config/repRelDeal/RepRelDeal001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage('REPORT_COMMON_C_0003'), function() {
                    var deleteUrl = basePath + 'disclosure/information/config/repRelDeal/RepRelDeal001/deleteData';
                    WorkFlow.doDeleteList(deleteUrl, object, "RepRelDeal001_btn_delete", "RepRelDeal002",function(){
                        table.reload();
                    });
                });
            });
        });

        // 删除按钮点击事件 删除数据
        $('#RepRelDeal001_btn_delete').click(function() {
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
                WorkFlow.doDeleteList(basePath + 'disclosure/information/config/repRelDeal/RepRelDeal001/deleteData', object, "RepRelDeal001_btn_delete", "RepRelDeal002", function () {
                    table.reload();//删除事件请求及回调函数
                });
            });
        });

        /**
         * 编辑页面
         */
        table.bind('click', '.edit', function(data, index) {
            var object = {pageModel:PAGE_MODEL_EDIT,
                uuid: data.uuid,
                operateType: PAGE_MODEL_EDIT};//请求参数
            Joyin.dialog(JoyinUtil.getLabel("关联交易情况维护-编辑"),
                JoyinUtil.getRealPath("disclosure/information/config/repRelDeal/RepRelDeal002/init"),
                "100%", "100%", object,
                function(data){
                    table.reload();
                });
        });

        $('#RepRelDeal001_btn_import').click(function() {
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
                                                            Joyin.ajaxJson(basePath + 'disclosure/information/config/repRelDeal/RepRelDeal001/importFile', data,
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
                                                        Joyin.ajaxJson(basePath + 'disclosure/information/config/repRelDeal/RepRelDeal001/importFile', data,
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
                                                        Joyin.ajaxJson(basePath + 'disclosure/information/config/repRelDeal/RepRelDeal001/importFile', data,
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
                                                    Joyin.ajaxJson(basePath + 'disclosure/information/config/repRelDeal/RepRelDeal001/importFile', data,
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
                                                        Joyin.ajaxJson(basePath + 'disclosure/information/config/repRelDeal/RepRelDeal001/importFile', data,
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
                                                    Joyin.ajaxJson(basePath + 'disclosure/information/config/repRelDeal/RepRelDeal001/importFile', data,
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
                                                    Joyin.ajaxJson(basePath + 'disclosure/information/config/repRelDeal/RepRelDeal001/importFile', data,
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
                                                Joyin.ajaxJson(basePath + 'disclosure/information/config/repRelDeal/RepRelDeal001/importFile', data,
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
        $('#RepRelDeal001_btn_export').click(function() {
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