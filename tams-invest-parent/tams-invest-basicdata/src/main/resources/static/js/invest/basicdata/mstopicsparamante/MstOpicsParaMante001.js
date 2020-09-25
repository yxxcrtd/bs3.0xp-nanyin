$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            paraType: $('#paraType').val()
        }
    };

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax : {
            url : 'selectOpicsParaManteInfo',
            type : 'POST',
            data : function() {
                return getSearchParams();
            }
        },
        dblclick : function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"), basePath
                    + "invest/basicdata/mstopicsparamante/MstOpicsParaMante002/init",
                    "95%", "90%", {
                        pageModel : PAGE_MODEL_REFERENCE,
                        itemId:data.itemId,
                        paraType:data.paraType
                    });
        },
        cols:[
             {field:'paraTypeText', width:200, title: 'MstOpicsParaMante001.paraType'}
            ,{field:'paraType', visible: false}
            ,{field:'itemId', width:200, title: 'MstOpicsParaMante001.itemId'}
            ,{field:'itemName', width:200, title: 'MstOpicsParaMante001.itemName'}
            ,{field:'itemValueCn', width:200, title: 'MstOpicsParaMante001.itemValueCn'}
            ,{field:'itemValueEn', width:200, title: 'MstOpicsParaMante001.itemValueEn'}
            ,{field:'orders', width:200, title: 'MstOpicsParaMante001.orders'}
            ,{field:'remark', width:200, title: 'MstOpicsParaMante001.remark'}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });
   
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstopicsparamante/MstOpicsParaMante002/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("MstOpicsParaMante002.edit"),
                    basePath + "invest/basicdata/mstopicsparamante/MstOpicsParaMante002/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_EDIT,
                                paraType:data.paraType,
                                itemId:data.itemId
                                    },function() {
                                        table.reload();
                                    });
        });
    });
    
    /**
     * 绑定删除事件
     */   
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstopicsparamante/MstOpicsParaMante001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/basicdata/mstopicsparamante/MstOpicsParaMante001/delete', object, "MstOpicsParaMante001_btn_delete", "MstOpicsParaMante002",function() {
                    table.reload();
                });
            });
        });
    });
    
    /**
     * 导出事件
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    
    /**
     * 画面加载完成
     */
$(function() {
    // 绑定按钮事件
    $('#MstOpicsParaMante001_btn_search').click(function() {
        table.load();
        $('#MstOpicsParaMante001_btn_delete').show();
    });
    $('#MstOpicsParaMante001_btn_export').click(function() {
        exportExcel();    });
    $('#MstOpicsParaMante001_btn_add').click(function() {
        Joyin.dialog(JoyinUtil.getLabel("MstOpicsParaMante002"),
                    basePath + "invest/basicdata/mstopicsparamante/MstOpicsParaMante002/init", "85%",
                        "68%", {pageModel:PAGE_MODEL_NEW});
    });
    $('#MstOpicsParaMante001_btn_delete').click(function() {
        if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
        }
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        var object = {selectedItem: selArr, operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstopicsparamante/MstOpicsParaMante001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/basicdata/mstopicsparamante/MstOpicsParaMante001/delete', object, "MstOpicsParaMante001_btn_delete", "MstOpicsParaMante002");
            });
        });
    });
});
}());