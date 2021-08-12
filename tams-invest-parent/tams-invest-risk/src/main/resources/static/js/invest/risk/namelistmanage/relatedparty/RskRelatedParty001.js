$(function(){

    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            instituteName: $('#instituteName').val()
        };
    };

    /**
     * 表格数据查询
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        checkbox: false,
        cols:[
            {field:'instituteName', width:'30%', sortField:'INSTITUTE_NAME', title: 'RskRelatedParty001.instituteName'}
           ,{field:'instituteCode', width:'20%', sortField:'INSTITUTE_CODE', title: 'RskRelatedParty001.instituteCode'}
           ,{field:'limitAmt', width:'15%', sortField:'LIMIT_AMT', title: 'RskRelatedParty001.limitAmt'}
           ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit','delt']}
        ]
    });

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        deleteItems([data]);
    });

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
            Joyin.dialog(JoyinUtil.getLabel("RskRelatedParty002"),
                basePath + "invest/risk/namelistmanage/relatedparty/RskRelatedParty002/init",
                "95%", "90%", {pageModel:PAGE_MODEL_EDIT,
                    instituteName:data.instituteName,instituteCode:data.instituteCode,limitAmt:data.limitAmt},function() {
                    table.reload();});
    });

    /**
     * 删除
     */
    var deleteItems = function(selectedItem) {
        var object = {selectedItem : selectedItem, operateType : "delete"};
        Joyin.confirm(
                JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")), function() {
                    Joyin.ajaxJson(basePath + 'invest/risk/namelistmanage/relatedparty/RskRelatedParty001/delete',
                        object,function(){table.reload();});
                });
    };

    /**
     * 导出
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };

    $(function() {
        // 绑定按钮事件
        // 查询
        $('#RskRelatedParty001_btn_search').click(function() {
            table.load();
//            $('#RskRelatedParty001_btn_delete').show();
        });
        // 导出
        $('#RskRelatedParty001_btn_export').click(function() {
            exportExcel();
        });
        // 新建
        $('#RskRelatedParty001_btn_import').click(function() {
            Joyin.confirm(JoyinUtil.getLabel("RskRelatedParty001.confirm"), function() {
                Joyin.uploadFile(function(fileName) {
                    Joyin.ajax(basePath +"invest/risk/namelistmanage/relatedparty/RskRelatedParty001/importFile",
                            {fileName : fileName},function(result){
                                if(!result || !($.isArray(result) && result.length > 0)) {
                                    layui.layer.closeAll();
                                    table.reload();
                                }
                            });
                });
            });
        });
        // 删除
        $('#RskRelatedParty001_btn_delete').click(function() {
            // 取出选中的记录
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
            }
            // 若一条记录都未选中
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            deleteItems(selArr);
        });
    });
})
