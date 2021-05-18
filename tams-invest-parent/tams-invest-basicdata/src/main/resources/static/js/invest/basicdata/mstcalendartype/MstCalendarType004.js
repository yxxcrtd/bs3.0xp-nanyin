/**
 * MstCalendarType004 日历类型管理交易日历名称明细查询
 */
$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var isHoliday = $('#isHoliday').val();
        var startCDate = $('#cDate').startDate();
        var endCDate = $('#cDate').endDate();
        return {
            isHoliday: isHoliday,
            startCDate: startCDate,
            endCDate: endCDate
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
                var parm = getSearchParams();;
                parm.tradeCalendarId = tradeCalendarId;
                return parm;
            }
        },
        checkbox:false,
        cols:[
              {field:'cDate', width:583,title: 'MstCalendarType004.date',type:'date'}
             ,{field:'isHoliday', title: 'MstCalendarType004.isHolidayFlag'}
         ]
     });
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();
        paramObj.tradeCalendarId = tradeCalendarId;
        if(paramObj.startCDate =="" && paramObj.endCDate =="" ){
           Joyin.error("请输入查询日期");
           return false;
        }
        else {
           Joyin.exportExcel('export', paramObj);
        }
    };
    /**
     * 画面加载完成
     */
    $(function() {
        table.load();
        // 绑定按钮事件
        $('#MstCalendarType004_btn_search').click(function() {
            var object = getSearchParams();
            object.operateType = "search";
            Joyin.ajaxJson(basePath + 'invest/basicdata/mstcalendartype/MstCalendarType004/check', object, function(result) {
                table.reload();
            })
        });
        $('#MstCalendarType004_btn_export').click(function() {
            exportExcel();
        });
    });
}());