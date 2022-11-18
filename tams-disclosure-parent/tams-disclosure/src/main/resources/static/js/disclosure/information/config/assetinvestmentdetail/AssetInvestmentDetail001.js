(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var prodId = $('#prodId').val();
        if(prodId && prodId != ''){
            prodId = prodId.join(',');
        }else {
            prodId = null;
        }


         return {
             prodId:prodId,
             assetCode:$('#assetCode').val(),
             assetName:$('#assetName').val(),
             dateStart:$('#pubDate').startDate(),
             dateEnd:$('#pubDate').endDate(),
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
            Joyin.dialog(JoyinUtil.getLabel("前十名资产投资明细查看"),
                basePath + "disclosure/information/config/assetinvestmentdetail/AssetInvestmentDetail002/init",
                "99%", "99%", data,function(){table.reload();});
        },
        /**
         * 定义表头
         */
        cols: [
            // {field:'prodAdjustId',  visible: false},
            {field:'statusStr',  title: 'ProElementAdj001.dataStatus',sortField:"status",width: 100},//数据状态
            {field:'prodId',  title: '产品主代码',sortField:"prodId"},
            {field:'prodName',  title: '产品全称',sortField:"prodName"},
            {field:'pubDate',  title: '日期',sortField:"pubDate",type:'date'}
            ,{field:'assetCode',  title: '资产代码',sortField:"assetCode"}
            ,{field:'assetName',  title: '资产名称',sortField:"assetName"}
            ,{field:'fairValue',  title: '公允价值（元）',sortField:"fairValue",type:'number'}
            ,{field:'pdDrop',  title: '占产品资产比例（%）',sortField:"pdDrop"}
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
        Joyin.ajaxJson(basePath + 'disclosure/information/config/assetinvestmentdetail/AssetInvestmentDetail001/check', data, function(editResult){
            Joyin.dialog(JoyinUtil.getLabel("前十名资产投资明细编辑"),
                basePath + "disclosure/information/config/assetinvestmentdetail/AssetInvestmentDetail002/init",
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
        Joyin.ajaxJson(basePath + 'disclosure/information/config/assetinvestmentdetail/AssetInvestmentDetail001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'disclosure/information/config/assetinvestmentdetail/AssetInvestmentDetail001/delete', object,
                    "AssetInvestmentDetail001_btn_delete", "AssetInvestmentDetail002",function() {
                        table.reload();
                    });
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

    function addrule() {
        var data = {};
        data.prodId=$('#prodId').val()== null ? null : $('#prodId').val().join(",");
        Joyin.dialog("产品选择",basePath+"disclosure/information/config/disprodplanmanage/DisProdPlanManage003/init","80%","80%",
            data,function () {
                var finprodIds = $('#finprodIds').data('finprodIds');
                if(!finprodIds || finprodIds.length ==0){
                    return;
                }
                $('#prodId').textSelect('setValue',finprodIds);
            });
        return;
    };

    /**
     * 画面加载完成
     */
    $(function () {
        $('#AccountAmount001_btn_treeAdd').click(addrule);
        //初始化查询
        $('#AssetInvestmentDetail001_btn_search').click(function () {
            table.load();
            $('#AssetInvestmentDetail001_btn_delete').show();
        });
        //新建(点击打开新建画面)
        $('#AssetInvestmentDetail001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("前十名资产投资明细新建"),
                basePath + "disclosure/information/config/assetinvestmentdetail/AssetInvestmentDetail002/init","100%", "100%", {pageModel:PAGE_MODEL_NEW},
                function() {
                    table.reload();
                });
        });
        //删除
        $('#AssetInvestmentDetail001_btn_delete').click(function () {
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
            Joyin.ajaxJson(basePath + 'disclosure/information/config/assetinvestmentdetail/AssetInvestmentDetail001/check', object, function (result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")), function () {
                    WorkFlow.doDeleteList(basePath + 'disclosure/information/config/assetinvestmentdetail/AssetInvestmentDetail001/delete', object,
                        "AssetInvestmentDetail001_btn_delete", "AssetInvestmentDetail002");
                });
            });
        });
    });
}());
