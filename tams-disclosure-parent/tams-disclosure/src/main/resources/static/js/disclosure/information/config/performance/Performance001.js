(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var prodId = $('#prodId').val()
        if(prodId && prodId != ''){
            prodId = prodId.join(',');
        }else {
            prodId = null;
        }
         return {
            prodId:prodId,
            // dataStatus:$('#dataStatus').val()
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
            Joyin.dialog(JoyinUtil.getLabel("产品业绩比较基准查看"),
                basePath + "disclosure/information/config/performance/Performance002/init",
                "99%", "99%", data);
        },
        /**
         * 定义表头
         */
        cols: [
            {field:'uuid',  visible: false},
            {field:'statusStr',  title: 'ProElementAdj001.dataStatus',sortField:"statusStr"},//数据状态
            {field:'prodId',  title: 'ProElementAdj001.prodId',sortField:"prodId"}//子产品代码
            ,{field:'effDate', width: 180, title: '业绩比较基准生效日',sortField:"effDate",type:'date'}
            ,{field:'expDate', width: 180, title: '业绩比较基准生效截止日',sortField:"expDate",type:'date'}
            ,{field:'zzqj',width: 260, title : 'Performance001.zzxz', type : 'number'}
            ,{field:'zzcf',width: 300, title : 'Performance001.zzxzh', type : 'number'}
            ,{field:'zz500',width: 220, title : 'Performance001.zzzs', type : 'number'}
            ,{field:'hs300',width: 220, title : 'Performance001.hszs', type : 'number'}
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
            Joyin.dialog(JoyinUtil.getLabel("产品业绩比较基准编辑"),
                basePath + "disclosure/information/config/performance/Performance002/init",
                "95%", "90%",data,function() {
                    table.reload();
                });
    });


    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'disclosure/information/config/performance/Performance001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'disclosure/information/config/performance/Performance001/delete', object,
                    "Performance001_btn_delete", "Performance002",function() {
                        table.reload();
                    });
            });
        });
    });



    /**
     * 导入按钮
     */
    $('#Performance001_btn_import').click(function() {
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
        $('#Performance001_btn_export').click(function () {
            exportExcel();
        });
        //初始化查询
        $('#Performance001_btn_search').click(function () {
            table.load();
            $('#Performance001_btn_delete').show();
        });
        //新建(点击打开新建画面)
        $('#Performance001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("产品业绩比较基准新建"),
                basePath + "disclosure/information/config/performance/Performance002/init","100%", "100%", {pageModel:PAGE_MODEL_NEW},
                function() {
                    table.reload();
                });
        });
        //删除
        $('#Performance001_btn_delete').click(function () {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            for(var i=0;i<selArr.length;i++){
                if(selArr[i].status =="D"){
                    return Joyin.error("只能删除有效数据，请重新选择");
                }
            }
            var object = {selectedItem: selArr, operateType: "delete"};
            Joyin.ajaxJson(basePath + 'disclosure/information/config/performance/Performance001/check', object, function (result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")), function () {
                    WorkFlow.doDeleteList(basePath + 'disclosure/information/config/performance/Performance001/delete', object,
                        "Performance001_btn_delete", "Performance002");
                });
            });
        });
    });
}());
