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
                    basePath + "submitmaintenance/mstcalendartype/SvrMstCalendarType002/init",
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
        Joyin.ajaxJson(basePath + 'submitmaintenance/mstcalendartype/SvrMstCalendarType002/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("MstCalendarType001.edit"),
                    basePath + "submitmaintenance/mstcalendartype/SvrMstCalendarType002/init",
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
        var keyList = [];
        keyList.push(data.tradeCalendarId +'|'+Date.ofDate(data.cDate).format());
        Joyin.ajaxJson(basePath + 'submitmaintenance/mstcalendartype/SvrMstCalendarType001/check', object, function(result) {
        	Joyin.confirm("请确认是否要删除选中记录？", function() {
                Joyin.ajax('delete', {deleteKeys: keyList.join(',')}, function() {
                    table.reload();
                });
            });
        });
    });
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
    	if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
         }
    	var param = getSearchParams();
         if (!selArr || selArr.length == 0) {
         } else {
        	 param.keyList = JSON.stringify(selArr);
         }
        Joyin.exportExcel('export', param);
    };
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#SvrMstCalendarType001_btn_search').click(function() {
            table.load();
            $('#SvrMstCalendarType001_btn_delete').show();
        });
        $('#SvrMstCalendarType001_btn_export').click(function() {
            exportExcel();
        });
        $('#SvrMstCalendarType001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("MstCalendarType002"),
                        basePath + "submitmaintenance/mstcalendartype/SvrMstCalendarType002/init",
                        "80%", "90%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#SvrMstCalendarType001_btn_holidays').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("MstCalendarType003"),
                        basePath + "submitmaintenance/mstcalendartype/SvrMstCalendarType003/init",
                        "60%", "60%");
        });
        $('#SvrMstCalendarType001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            var keyList = [];
            $.each(selArr, function(i, row) {
            	keyList.push(row.tradeCalendarId +'|'+Date.ofDate(row.cDate).format());
            });
            Joyin.ajaxJson(basePath + 'submitmaintenance/mstcalendartype/SvrMstCalendarType001/check', object, function(result) {
            	Joyin.confirm("请确认是否要删除选中记录？", function() {
                    Joyin.ajax('delete', {deleteKeys: keyList.join(',')}, function() {
                        table.reload();
                    });
                });
            });
        });
        //交易日历名称链接事件
        table.bind('click','.pro-link', function(row) {
            Joyin.dialog(JoyinUtil.getLabel("MstCalendarType004"),
                    basePath + "submitmaintenance/mstcalendartype/SvrMstCalendarType004/init",
                    "95%", "90%",{tradeCalendarId:row.tradeCalendarId,
            calendarName: row.calendarName});
        });
    });
}());