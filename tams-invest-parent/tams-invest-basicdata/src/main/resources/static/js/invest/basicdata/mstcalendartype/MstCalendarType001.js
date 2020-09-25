/**
 * MstCalendarType001 日历类型管理查询（含删除）
 */
$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var calendarType = $('#calendarType').val();
        return {
            calendarName: $('#calendarName').val(),
            calendarType:calendarType ? calendarType.join(","):''
        }
    };
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
        /**
         * 双击查看
         */
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/basicdata/mstcalendartype/MstCalendarType002/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,
                                   tradeCalendarId:data.tradeCalendarId,
                                   calenderName:data.calenderName});
        },
        cols:[
             {field:'tradeCalendarId',visible: false}
            ,{field:'calendarName',width:'50%', title: 'MstCalendarType001.calendarName',
                render: function (data, type, row, meta) {
                    return "<a  class='pro-link' style='cursor: pointer;'>"+row.calendarName+"</a>";
                 }
            }
            ,{field:'calendarType',width:'50%', title: 'MstCalendarType001.calendarType'}
            ,{field:'operation',title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstcalendartype/MstCalendarType002/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("MstCalendarType001.edit"),
                    basePath + "invest/basicdata/mstcalendartype/MstCalendarType002/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_EDIT,
                                   tradeCalendarId:data.tradeCalendarId,
                                   calenderName:data.calenderName},function() {
                                        table.reload();
                                    });
        });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstcalendartype/MstCalendarType001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/basicdata/mstcalendartype/MstCalendarType001/delete', object, 
                        "MstCalendarType001_btn_delete", "MstCalendarType002",function() {
                    table.reload();
                });
            });
        });
    });
    /**
     * 绑定导出事件
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
        $('#MstCalendarType001_btn_search').click(function() {
            table.load();
            $('#MstCalendarType001_btn_delete').show();
        });
        $('#MstCalendarType001_btn_export').click(function() {
            exportExcel();
        });
        $('#MstCalendarType001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("MstCalendarType002"),
                        basePath + "invest/basicdata/mstcalendartype/MstCalendarType002/init",
                        "80%", "90%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#MstCalendarType001_btn_holidays').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("MstCalendarType003"),
                        basePath + "invest/basicdata/mstcalendartype/MstCalendarType003/init",
                        "60%", "60%");
        });
        $('#MstCalendarType001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/basicdata/mstcalendartype/MstCalendarType001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/basicdata/mstcalendartype/MstCalendarType001/delete', object, 
                            "MstCalendarType001_btn_delete","MstCalendarType002");
                });
            });
        });
        //交易日历名称链接事件
        table.bind('click','.pro-link', function(row) {
            Joyin.dialog(JoyinUtil.getLabel("MstCalendarType004"),
                    basePath + "invest/basicdata/mstcalendartype/MstCalendarType004/init",
                    "95%", "90%",{tradeCalendarId:row.tradeCalendarId,
            calendarName: row.calendarName});
        });
    });
}());