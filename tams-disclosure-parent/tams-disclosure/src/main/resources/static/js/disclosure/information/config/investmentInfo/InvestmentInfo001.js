(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var booksetId = $('#booksetId').val()
        if(booksetId && booksetId !== ''){
            booksetId = booksetId.join(',');
        }else {
            booksetId = null;
        }


        return {
            booksetId:booksetId,
            indexPeriod:$('#indexPeriod').val(),
            indexYear:$('#indexYear').val(),
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
            console.log("123");
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("前十大投资者信息查看"),
                basePath + "disclosure/information/config/investmentInfo/InvestmentInfo002/init",
                "99%", "99%", data,function(){table.reload();});
        },
        /**
         * 定义表头
         */
        cols: [
            // {field:'prodAdjustId',  visible: false},
            {field:'statusStr',  title: 'ProElementAdj001.dataStatus',sortField:"status",width: 100},//数据状态
            {field:'itemId',  title: '业务主键',sortField:"itemId",width: 50},//业务主键
            {field:'booksetId',  title: '产品主代码',sortField:"booksetId"},
            {field:'prodName',  title: '产品全称',sortField:"prodName"},//产品主代码
            {field:'indexYear',  title: '年度',sortField:"indexYear"},//年度
            {field:'indexTypeStr',  title: '指标类型',sortField:"indexType",width: 100},//指标类型
            {field:'tzRank',  title: '排名',sortField:"tzRank",type:'number'},//排名
            {field:'tzTypeStr',  title: '类别',sortField:"tzType",width: 100},//类别
            {field:'holdShare',  title: '持有份额',sortField:"holdShare",type:'number'},//持有份额
            {field:'proportionShareStr',  title: '占总份额比例（%）',sortField:"proportionShare"},//占总份额比例（%）
            {field:'updateTime',  title: '更新时间',sortField:"updateTime",type:'datetime'}, //更新时间
            {field:'updateUser',title:'更新人',sortField:"updateUser"},//更新人
            {field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt'],
                render: function (data, type, row, meta) {
                    if ( row.status === 'E')
                        return '<i class="btn layui-icon edit edit" title="编辑" data-ignore="false"></i> ' +
                            '<i class="btn layui-icon delt delt" title="删除" data-ignore="false"></i>';
                    else
                        return '';
                }
            }
        ]

    });
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'disclosure/information/config/investmentInfo/InvestmentInfo001/check', data, function(editResult){
            Joyin.dialog(JoyinUtil.getLabel("前十大投资者信息维护编辑"),
                basePath + "disclosure/information/config/investmentInfo/InvestmentInfo002/init",
                "95%", "90%",data,function() {
                    table.reload();
                });
        });
    });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        alert(111111);
        console.log("+++"+data);
        var object = {selectedItem: [data], operateType:"delete"};
        console.log("+++"+object);
        Joyin.ajaxJson(basePath + 'disclosure/information/config/investmentInfo/InvestmentInfo001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'disclosure/information/config/investmentInfo/InvestmentInfo002/delete', object,
                    "InvestmentInfo001_btn_delete", "InvestmentInfo002",function() {
                        table.reload();
                    });
            });
        });
    });


    $('#InvestmentInfo001_btn_import').click(function() {
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
    function addrule() {
        Joyin.dialog("产品选择",basePath+"disclosure/information/config/disprodplanmanage/DisProdPlanManage003/init","80%","80%",
            {},function () {
                var finprodIds = $('#finprodIds').data('finprodIds');
                if(!finprodIds || finprodIds.length ==0){
                    return;
                }
                $('#booksetId').textSelect('setValue',finprodIds);
            });
        return;
    };

    /**
     * 画面加载完成
     */
    $(function () {
        $('#InvestmentInfo001_btn_treeAdd').click(addrule);
        //导出
        $('#InvestmentInfo001_btn_export').click(function () {
            exportExcel();
        });
        //初始化查询
        $('#InvestmentInfo001_btn_search').click(function () {
            table.load();
            $('#InvestmentInfo001_btn_delete').show();
        });
        //新建(点击打开新建画面)
        $('#InvestmentInfo001_btn_add').click(function() {
            console.log("点击新建");
            Joyin.dialog(JoyinUtil.getLabel("前十大投资者信息新建"),
                basePath + "disclosure/information/config/investmentInfo/InvestmentInfo002/init","100%", "100%", {pageModel:PAGE_MODEL_NEW},
                function() {
                    table.reload();
                });
        });
        //删除
        $('#InvestmentInfo001_btn_delete').click(function () {
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
            console.log(object);
            Joyin.ajaxJson(basePath + 'disclosure/information/config/investmentInfo/InvestmentInfo001/check', object, function (result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")), function () {
                    WorkFlow.doDeleteList(basePath + 'disclosure/information/config/investmentInfo/InvestmentInfo002/delete', object,
                        "InvestmentInfo001_btn_delete", "InvestmentInfo002");
                });
            });
        });
    });
}());
