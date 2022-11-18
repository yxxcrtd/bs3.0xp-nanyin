/**
 * 非标债权类资产明细维护查询
 */
(function(){
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            prodId: $('#prodId').val()==null?null:$('#prodId').val().join(","),
            effDateStart: $.trim($('#effDate').startDate()),
            effDateEnd: $.trim($('#effDate').endDate()),
            effEndDateStart: $.trim($('#effEndDate').startDate()),
            effEndDateEnd: $.trim($('#effEndDate').endDate()),
            dataStatus:$('#dataStatus').val()
        }
    };
    // 新建
    function showDialog() {
        Joyin.dialog(JoyinUtil.getLabel('UnClaimAssetDetail002'), basePath +'/disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail002/init', '98%', '95%', {pageModel:PAGE_MODEL_NEW,editFlag:'01'},function(){
            table.reload();
        });

    }

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("UnClaimAssetDetail002.reference"),
                JoyinUtil.getRealPath("disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail002/init"),
                "95%", "95%",data,function(){table.reload();});
        },

        cols:[
            {field:'nostdAssetId', visible: false},
            {field:'dataStatusStr', title: 'UnClaimAssetDetail002.dataStatus',width:160, sortField:'datastatus'},
            {field:'prodId', title: 'UnClaimAssetDetail002.prodId', width:160},
            {field:'prodName', title: 'UnClaimAssetDetail002.prodName',width:160},
            {field:'effDateStr', title: 'UnClaimAssetDetail002.effDate',  width:160,type:'date', sortField:'effDate'}
            ,{field:'effEndDateStr', title: 'UnClaimAssetDetail002.effEndDate', width:160,type:'date', sortField:'effEndDate'}
            ,{field:'updateTime',type: 'datetime', title: 'UnClaimAssetDetail002.updateTime', width:160}
            ,{field:'updateUser', title: 'UnClaimAssetDetail002.updateUser', width:160}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt'],width: 160,
                render: function (data, type, row, meta) {
                    if (row.dataStatus == 'D' || row.dataStatus == 'C')
                        return '';
                    else
                        return '<i class="btn layui-icon edit edit" title="编辑" data-ignore="false"></i> ' +
                            '<i class="btn layui-icon delt delt" title="删除" data-ignore="false"></i>';
                }
            }
        ],
    });
    /**
     * 导出
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();//请求参数json格式
        Joyin.exportExcel('export', paramObj);//导出（共通）
    };

     /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        var object = {selectedItem: [data]};
        object.editFlag='02'
        Joyin.ajaxJson(basePath + '/disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail001/check', object, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("非标债权类资产明细编辑"),
                basePath + "disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail002/init",
                "95%", "90%", data,
                // {pageModel:PAGE_MODEL_EDIT,
                //     prodId:data.prodId,effDate:data.effDate,effEndDate:data.effEndDate},
                function(){table.reload();})
        });
    });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + '/disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail001/delete', object, "UnClaimAssetDetail001_btn_delete", "UnClaimAssetDetail002",function(){
                    table.reload();
                });
            });
        });
    });
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

    $(function () {
        // 绑定按钮事件
        $('#UnClaimAssetDetail001_btn_treeAdd').click(addrule);
        $('#UnClaimAssetDetail001_btn_search').click(function() {
            table.load();
            $('#UnClaimAssetDetail001_btn_delete').show();
        });
        $('#UnClaimAssetDetail001_btn_export').click(function() {
            exportExcel();
        });
        $('#UnClaimAssetDetail001_btn_add').click(function() {
            showDialog();
        });
        $('#UnClaimAssetDetail001_btn_submit').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            for(let i=0; i<selArr.length; i++){
                if(selArr[i].dataStatus!=='A'){
                    Joyin.error("只能对数据状态是待处理的数据进行批量审批");
                    return;
                }
            }
            var object = {
                selectedItem: selArr,
                operateType:"batchSubmit"
            };
            Joyin.ajaxJson(basePath + '/disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getLabel("请确认是否进行批量审批？"), function(){
                    WorkFlow.doSubmitList(basePath + 'disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail001/submit', object, "UnClaimAssetDetail001_btn_submit", "UnClaimAssetDetail002",function(){
                        table.reload();
                    });
                });
            });

        });

        $('#UnClaimAssetDetail001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            for(var i=0;i<selArr.length;i++){
                if(selArr[i].dataStatus=='D'){
                    Joyin.error("只能删除有效数据，请重新选择!");
                    return;
                }
            }
            var object = {
                selectedItem: selArr,
                operateType:"delete"
            };

            Joyin.ajaxJson(basePath + '/disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail001/delete', object, "UnClaimAssetDetail001_btn_delete", "UnClaimAssetDetail002",function(){
                    });
                });
            });
        });

        // 导入按钮点击事件
        // $('#UnClaimAssetDetail001_btn_import').click(function() {
        //     Joyin.confirm(JoyinUtil.getLabel("请确认是否要导入数据？"), function() {
        //         Joyin.uploadFile(function(fileName) {
        //             Joyin.ajax(basePath + 'disclosure/information/config/unclaimassetdetail/UnClaimAssetdetail001/checkImportData', {fileName: fileName}, function(checkResult) {
        //                 Joyin.ajax(basePath +"disclosure/information/config/unclaimassetdetail/UnClaimAssetdetail001/importFile", {
        //                     fileName : fileName
        //                 },function(result){
        //                     if(!result || !($.isArray(result) && result.length > 0)) {
        //                         layui.layer.closeAll()
        //                         table.reload();
        //                     }
        //                 });
        //             });
        //         });
        //     });
        // });


        $('#UnClaimAssetDetail001_btn_import').click(function() {
            Joyin.confirm(JoyinUtil.getMessage('CHB_ISIMPORT'), function () {
                Joyin.uploadFile({pageId:'UnClaimAssetDetail001'},function(fileName) {
                    Joyin.ajax('checkImportData', {fileName: fileName}, function() {
                        Joyin.ajax('importFile', {fileName: fileName},function(){table.reload();});
                    },function() {
                        $('iframe').contents().find("#loading").hide();
                    });
                },function() {
                    table.reload();
                });
            });
        });

    });
}());