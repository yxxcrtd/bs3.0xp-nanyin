
(function(){
    /**
     * 获取查询参数
     */
    var searchform =function(){
        var param =  $.serializeToObject('#searchForm');

        param.startDate= $('#checkDate').startDate();
        param.endDate= $('#checkDate').endDate();
        if(param.checkPoint){
            param.checkPoint = param.checkPoint.join(",");
        }
        if(param.prebatchStatus){
            param.prebatchStatus = param.prebatchStatus.join(",");
        }
        return param;



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
        cols:[
            {field:'checkDateStr', title: 'SysPrebatchResult001.checkDate', width:'8%',sortField:'CHECK_DATE'}
            ,{field:'taskIdStr', title: 'SysPrebatchResult001.taskId', width:'16%',sortField:'TASK_ID'}
            ,{field:'taskStatusStr', title: 'SysPrebatchResult001.taskStatus', width:'6%',sortField:'TASK_STATUS'}
            ,{field:'detailCount', title: 'SysPrebatchResult001.detailCount', width:'6%',sortField:'DETAIL_COUNT'}
            ,{field:'executeDateStr', title: 'SysPrebatchResult001.executeDate', width:'8%',sortField:'EXECUTE_DATE'}
            ,{
                field: 'processTime', width:'8%', title: '耗时(S)',sortable:false, render: function (data, type, row, meta) {
                    return data*1/1000;
                }
            }
            ,{field:'startTimeStr', title: 'SysPrebatchResult001.startTime', width:'12%',sortField:'START_TIME'}
            ,{field:'endTimeStr', title: 'SysPrebatchResult001.endTime', width:'12%',sortField:'END_TIME'}
            ,{field:'seqNo', visible: false}
            ,{field:'batchSeqNo', visible: false}
            ,{field:'action', title: '操作' , type:'action' , width:'10%'
                ,render: function (data, type, row, meta) {
                                if (row.detailCount!=null && row.detailCount && row.detailCount >0 ){
                                    return '<i class="btn layui-icon edit" title="详情">&#xe615;</i>';
                                }else{
                                    return "";
                                }
                            }
            }
        ],
        checkbox: false
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
        Joyin.dialog(JoyinUtil.getLabel("SysPrebatchResult002"),
                JoyinUtil.getRealPath("invest/prebatch/SysPrebatchResult002/init"),
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