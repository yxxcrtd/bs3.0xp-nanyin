
(function(){
    /**
     * 获取查询参数
     */
    var searchform =function(){
        return $.serializeToObject('#searchForm');
    };


    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return searchform();
            }
        },
//        dblclick:function(table, data) {
//            data.operateType = "dblclick";
//            data.pageModel=PAGE_MODEL_REFERENCE;
//            Joyin.dialog(JoyinUtil.getLabel("MstNaturalPerson002"),
//                    JoyinUtil.getRealPath("invest/basicdata/mstnaturalperson/MstNaturalPerson002/init"),
//                    "98%", "95%", data,
//                    function(){table.reload();});
//        },
        cols:[
            {field:'checkDate', title: 'SysPrebatchResult001.checkDate', width:'10%',sortField:'checkDate'}
            ,{field:'taskId', title: 'SysPrebatchResult001.taskId', width:'10%',sortField:'taskId'}
            ,{field:'taskStatus', title: 'SysPrebatchResult001.taskStatus', width:'10%',sortField:'taskStatus'}
            ,{field:'detailCount', title: 'SysPrebatchResult001.detailCount', width:'10%',sortField:'detailCount'}
            ,{field:'executeDate', title: 'SysPrebatchResult001.executeDate', width:'10%',sortField:'executeDate'}
            ,{field:'duration', title: 'SysPrebatchResult001.duration', width:'10%',sortable:false}
            ,{field:'startTime', title: 'SysPrebatchResult001.startTime', width:'10%',sortField:'startTime'}
            ,{field:'endTime', title: 'SysPrebatchResult001.endTime', width:'10%',sortField:'endTime'}
            ,{field:'seqNo', visible: false}
            ,{field:'batchSeqNo', visible: false}
            ,{field:'action', title: '操作' , type:'action',buttons: ['edit'] , width:'10%'}
        ],
    });
    var exportExcel = function(){
        Joyin.exportExcel(basePath+"invest/prebatch/SysPrebatchResult001/export", searchform());
    };

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.operateType = "edit";
        data.pageModel=PAGE_MODEL_EDIT;
        Joyin.dialog(JoyinUtil.getLabel("SysPrebatchResult001"),
                JoyinUtil.getRealPath("invest/prebatch/SysPrebatchResult001/init"),
                "98%", "95%", data,
           function(){table.reload();});
    });
    /**
     * 绑定删除事件
     */
//    table.bind('click', '.delt', function(data, index) {
//        var object = {selectedItem: [data], operateType:"delete"};
//        Joyin.ajaxJson(basePath + 'invest/prebatch/SysPrebatchResult001/check', object, function(result) {
//            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
//                WorkFlow.doDeleteList(basePath + 'invest/prebatch/SysPrebatchResult001/delete', object,"MstNaturalPerson001_btn_delete", "MstNaturalPerson002", function(result) {
//                                        table.reload();
//                                    });
//            });
//        });
//    });
    /**
     * 编辑按钮事件
     */
    $(function () {
        /**
         * 查询按钮
         */
        $('#SysPrebatchResult001_btn_search').click(function() {
            table.load();
           // $('#SysPrebatchResult001_btn_delete').show();
        });
        /**
         * 导出按钮
         */
        $('#SysPrebatchResult001_btn_export').click(function() {
            exportExcel();
        });
        /**
         * 新建按钮
         */
        $('#SysPrebatchResult001_btn_add').click(function() {
            Joyin.confirm(JoyinUtil.getMessage('COMMON_C_0001', JoyinUtil.getLabel('SysCommon.execute')), function () {

                            Joyin.ajax('executePreBatch', {}, function (result) {
                                table.reload();
                            })
              })
        });
//        /**
//         * 批量删除按钮
//         */
//        $('#MstNaturalPerson001_btn_delete').click(function() {
//            if(table!=null && table!=''){
//                var selArr = table.getAllCheckedRows();
//            }
//            if (!selArr || selArr.length == 0) {
//                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
//                return;
//            }
//            var object = {selectedItem: selArr, operateType:"delete"};
//            Joyin.ajaxJson(basePath + 'invest/basicdata/mstnaturalperson/MstNaturalPerson001/check', object, function(result) {
//                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
//                    WorkFlow.doDeleteList(basePath + 'invest/basicdata/mstnaturalperson/MstNaturalPerson001/delete', object,"MstNaturalPerson001_btn_delete", "MstNaturalPerson002", function(result) {
//                        table.reload();
//                    });
//                });
//            });
//        });
    });
}());