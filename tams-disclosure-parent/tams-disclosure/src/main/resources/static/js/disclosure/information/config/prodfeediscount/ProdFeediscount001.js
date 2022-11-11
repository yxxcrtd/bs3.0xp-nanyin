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
        var feename = $('#feename').val()
        if(feename && feename != ''){
            feename = feename.join(',');
        }else {
            feename = null;
        }
         return {
            prodRegistNo:prodRegistNo,
            prodId:prodId,
             feename:feename,
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
            Joyin.dialog(JoyinUtil.getLabel("产品费率优惠查看"),
                basePath + "disclosure/information/config/prodfeediscount/ProdFeediscount002/init",
                "99%", "99%", {workflowId:data.workflowId,prodDiscountId:data.prodDiscountId,pageModel:PAGE_MODEL_REFERENCE},function(){table.reload();});
        },
        /**
         * 定义表头
         */
        cols: [
            {field:'prodDiscountId',  visible: false,width:80}
            ,{field:'prodRegistNo',  title: '产品登记编码',sortField:"prodRegistNo",width:150}
            ,{field:'prodId',  title: '子产品代码',sortField:"prodId",width:150}
            ,{field:'prodName',  title: '子产品名称',sortField:"prodName",width:150}
            , {field:'prodDisRemark',  title: '单产品多费率描述文字',sortField:"prodDisRemark",width:200,sortable:false}
            , {field:'feename',  title: '优惠的费用',sortField:"feename",width:120}
            , {field:'preFeerate',  title: '优惠前费率',sortField:"preFeerate",width:120,sortable:false}
            , {field:'afterFeerate',  title: '优惠后费率',sortField:"afterFeerate",width:120,sortable:false}
            , {field:'startDate',  title: '优惠起始时间',sortField:"startDate",type:'date',width:120}
            , {field:'endDate',  title: '优惠截止时间',sortField:"endDate",type:'date',width:120}
            // , {field:'thisPreFeerate',  title: '本次优惠前费率',sortField:"thisPreFeerate",width:120,sortable:false}
            // , {field:'thisAfterFeerate',  title: '本次优惠后费率',sortField:"thisAfterFeerate",width:120,sortable:false}
            // , {field:'thisEndFeerate',  title: '本次优惠结束后费率',sortField:"thisEndFeerate",width:120,sortable:false}
            ,{field:'feeDisRemark',  title: '单费率多产品描述文字',sortField:"feeDisRemark",width:200,sortable:false}
            ,{field:'rptIssueDate',  title: '报告发布日期',sortField:"rptIssueDate",type:'date',width:120}
            ,{field:'dataStatusName',  title: '数据状态',sortField:"dataStatus",width:100}
            ,{field:'updateDateStr',  title: '更新时间',sortField:"updateTime",type:'date',width:180}
            ,{field:'updateUser',title:'更新人',sortField:"updateUser",width:80 }
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
        //Joyin.ajaxJson(basePath + 'disclosure/information/config/prodfeediscount/ProdFeediscount001/check', data, function(editResult){
            Joyin.dialog(JoyinUtil.getLabel("产品费率优惠编辑"),
                basePath + "disclosure/information/config/prodfeediscount/ProdFeediscount002/init",
                "99%", "99%",{workflowId:data.workflowId,prodDiscountId:data.prodDiscountId,pageModel:PAGE_MODEL_EDIT},function() {
                    table.reload();
                });
       // });
    });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        //Joyin.ajaxJson(basePath + 'disclosure/information/config/prodfeediscount/ProdFeediscount001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'disclosure/information/config/prodfeediscount/ProdFeediscount001/delete', object,
                    "ProdFeediscount001_btn_delete", "ProdFeediscount002",function() {
                        table.reload();
                    });
            });
        //});
    });



    /**
     * 导入按钮
     */

    $('#ProdFeediscount001_btn_import').click(function() {
        Joyin.confirm(JoyinUtil.getMessage('CHB_ISIMPORT'), function () {
            Joyin.uploadFile({pageId:'ProdFeediscount001'},function(fileName) {
                Joyin.ajax('checkImportData',{fileName: fileName}, function() {
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
                                        var data = {
                                            tempList: checkResult.data.correctData
                                        }
                                        Joyin.ajaxJson(basePath + 'disclosure/information/config/prodfeediscount/ProdFeediscount001/importFile', data,
                                            function (resultObj) {
                                                if (resultObj.statuCode.toString() === '200') {
                                                    /!** modify by yanya on 20210329 修正导入逻辑**!/
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
                                    Joyin.ajaxJson(basePath + 'disclosure/information/config/prodfeediscount/ProdFeediscount001/importFile', data,
                                        function (resultObj) {
                                            if (resultObj.statuCode.toString() === '200') {
                                                /!** modify by yanya on 20210329 修正导入逻辑**!/
                                                //Joyin.alert("文件导入成功！");
                                                table.reload();
                                                return;
                                            }
                                        });
                                }
                            },{}, function () {
                                layui.layer.closeAll()
                                table.reload();
                            });
                        }else{
                            if (checkResult.data.confirmMsg) {
                                Joyin.confirm(checkResult.data.confirmMsg, function () {
                                    var data = {
                                        tempList: checkResult.data.correctData
                                    }
                                    Joyin.ajaxJson(basePath + 'disclosure/information/config/prodfeediscount/ProdFeediscount001/importFile', data,
                                        function (resultObj) {
                                            if (resultObj.statuCode.toString() === '200') {
                                                /!** modify by yanya on 20210329 修正导入逻辑**!/
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
                                Joyin.ajaxJson(basePath + 'disclosure/information/config/prodfeediscount/ProdFeediscount001/importFile', data,
                                    function (resultObj) {
                                        if (resultObj.statuCode.toString() === '200') {
                                            /!** modify by yanya on 20210329 修正导入逻辑**!/
                                            //Joyin.alert("文件导入成功！");
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
        $('#ProdFeediscount001_btn_export').click(function () {
            exportExcel();
        });
        //初始化查询
        $('#ProdFeediscount001_btn_search').click(function () {
            table.load();
            $('#ProdFeediscount001_btn_delete').show();
        });
        //新建(点击打开新建画面)
        $('#ProdFeediscount001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("产品费率优惠新建"),
                basePath + "disclosure/information/config/prodfeediscount/ProdFeediscount002/init",
                "99%", "99%", {pageModel:PAGE_MODEL_NEW},
                function() {
                    table.reload();
                });
        });
        //删除
        $('#ProdFeediscount001_btn_delete').click(function () {
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
            //Joyin.ajaxJson(basePath + 'disclosure/information/config/prodfeediscount/ProdFeediscount001/check', object, function (result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")), function () {
                    WorkFlow.doDeleteList(basePath + 'disclosure/information/config/prodfeediscount/ProdFeediscount001/delete', object,
                        "ProdFeediscount001_btn_delete", "ProdFeediscount002");
                });
           // });
        });
    });
}());
