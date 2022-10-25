(function () {
    /**
     * 获取查询参数
     */
    // var getSearchParams = function () {
    //     var data = $.serializeToObject('#searchForm');
    //     return data;
    // };

    var getSearchParams = function() {
        var prodId = $('#prodId').val()
        if(prodId && prodId != ''){
            prodId = prodId.join(',');
        }else {
            prodId = null;
        }
         return {
             prodId:prodId,
             profitBaseDate:$('#profitBaseDate').val(),
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
            Joyin.dialog(JoyinUtil.getLabel("产品计算分红清单维护信息查看"),
                basePath + "disclosure/information/config/bonuscalist/BonusCalist002/init",
                "99%", "99%", data);
        },
        /**
         * 定义表头
         */
        cols: [
            {field:'bonusListId',  visible: false},
            {field:'dataStatusStr',  title: 'ProElementAdj001.dataStatus',sortField:"dataStatus",width: 80},//数据状态
            {field:'prodId',  title: 'ProElementAdj001.prodId',sortField:"prodId",width: 100},//子产品代码
            {field:'prodRegistNo',  title: 'ProElementAdj001.prodRegistNo',sortField:"prodRegistNo",width: 100}//产品登记编码
            ,{field:'prodName',  title: '产品名称',sortField:"prodRegistNo",width: 100}//产品名称
            ,{field:'prodAssets',  title: '产品资产净值',sortField:"prodAssets",type : 'number',width: 100} //产品资产净值
            ,{field:'prodEndBalance',  title: '产品期末余额(份额)',sortField:"prodEndBalance",type:'number',width: 100}
            ,{field:'unitNum',  title: '单位净值',sortField:"unitNum",type:'number',width: 100}
            ,{field:'lastProfitBaseDate',  title: '上一收益分配基准日',sortField:"lastProfitBaseDate",type:'date',width: 100}
            ,{field:'profitBaseDate',  title: '本期收益分配基准日',sortField:"profitBaseDate",type:'date',width: 100}
            ,{field:'runDays',  title: '运行天数',sortField:"runDays",width: 100}
            ,{field:'avaiProfit',  title: '可供分配利润',sortField:"avaiProfit",width: 100, digit: 2,type:'number'}
            ,{field:'perBase',  title: '业绩比较基准',sortField:"perBase",width: 100}
            ,{field:'perBaseProfit',  title: '业绩比较基准收益',sortField:"perBaseProfit",type:'number',width: 100}
            ,{field:'exceed',  title: '超出部分',sortField:"exceed",type:'number',width: 100}
            ,{field:'perFee',  title: '业绩报酬',sortField:"perFee",type:'number',width: 100}
            ,{field:'disBonusMoney',  title: '应分配分红金额',sortField:"disBonusMoney",type:'number',width: 100}
            ,{field:'unitBonus',  title: '单位分红',sortField:"unitBonus",type:'number',width: 100, digit: 4}
            ,{field:'yield',  title: '收益率',sortField:"yield",type:'number',width: 100, digit: 4}
            ,{field:'msg',  title: '错误信息',sortField:"msg",width: 100}
            ,{field:'updateTime',  title: '更新时间',sortField:"updateTime",type:'datetime',width: 100} //更新时间
            ,{field:'updateUser',title:'更新人',sortField:"updateUser",width: 100}//更新人
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
        Joyin.ajaxJson(basePath + 'disclosure/information/config/bonuscalist/BonusCalist001/check', data, function(editResult){
            Joyin.dialog(JoyinUtil.getLabel("分红计算清单维护编辑"),
                basePath + "disclosure/information/config/bonuscalist/BonusCalist002/init",
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
        Joyin.ajaxJson(basePath + 'disclosure/information/config/bonuscalist/BonusCalist001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'disclosure/information/config/bonuscalist/BonusCalist001/delete', object,
                    "BonusCalist001_btn_delete", "BonusCalist002",function() {
                        table.reload();
                    });
            });
        });
    });



    /**
     * 导入按钮
     */
    $('#BonusCalist001_btn_import').click(function() {
        Joyin.confirm(JoyinUtil.getMessage('CHB_ISIMPORT'), function () {
            Joyin.uploadFile(function(fileName) {
                Joyin.ajax('checkImportData', {fileName: fileName}, function() {
                    Joyin.ajax('importFile', {fileName: fileName});
                },function() {
                    $('iframe').contents().find("#loading").hide();
                });
            },function() {
                table.reload();
            });
        });
    });

    function addrule1() {
        Joyin.dialog("产品选择",basePath+"disclosure/information/config/disprodplanmanage/DisProdPlanManage004/init","80%","80%",
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

        $('#RepProdIndex001_btn_treeAdd').click(addrule1);
        //导出
        $('#BonusCalist001_btn_export').click(function () {
            exportExcel();
        });
        //初始化查询
        $('#BonusCalist001_btn_search').click(function () {
            table.load();
            $('#BonusCalist001_btn_delete').show();
        });
        //新建(点击打开新建画面)
        $('#BonusCalist001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("产品分红计算清单维护新建"),
                basePath + "disclosure/information/config/bonuscalist/BonusCalist002/init","100%", "100%", {pageModel:PAGE_MODEL_NEW},
                function() {
                    table.reload();
                });
        });
        //删除
        $('#BonusCalist001_btn_delete').click(function () {
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
            Joyin.ajaxJson(basePath + 'disclosure/information/config/bonuscalist/BonusCalist001/check', object, function (result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")), function () {
                    WorkFlow.doDeleteList(basePath + 'disclosure/information/config/bonuscalist/BonusCalist001/delete', object,
                        "BonusCalist001_btn_delete", "BonusCalist002" ,function () {
                                table.reload();
                        });
                });
            });
        });


        //批量审批
        $('#BonusCalist001_btn_submit').click(function () {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            for(var i=0;i<selArr.length;i++){
                if(!(selArr[i].dataStatus == "A")){
                    return Joyin.error("只能对数据状态是待处理的数据进行批量审批");
                }
            }
            var object = {selectedItem: selArr};
            Joyin.confirm(JoyinUtil.getLabel("请确认是否进行批量审批？"), function(){
            Joyin.ajaxJson(basePath + 'disclosure/information/config/bonuscalist/BonusCalist001/changeStatus', object.selectedItem, function (result) {
                table.reload();
            });
            });
        });

        //产品分红计算
        $('#BonusCalist001_btn_calculate').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("产品分红计算条件"),
                basePath + "disclosure/information/config/bonuscalist/BonusCalist003/init","80%", "80%", {},
                function() {

                    table.reload();
                });
        });
    });
}());
