/**
 * MstCalendarType002 日历类型管理新建（含修改）
 */
$(function() {
    //判断是提交或是保存
    function saveOrSubmit(operateType) {
        var calendarType = $('#calendarType').val();
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.operateType = operateType; 
        object.calendarName = $("#calendarName").val();
        object.calendarType =calendarType ? calendarType.join(","):'';
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstcalendartype/MstCalendarType002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/basicdata/mstcalendartype/MstCalendarType002/save', object, "MstCalendarType002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/basicdata/mstcalendartype/MstCalendarType002/save', object, "MstCalendarType002_btn_submit");
                }
            });
        });
    };
    //画面初始化
    var init = function(){
        //编辑模式资产代码、估值日期、资产名称和管理人不可编辑
        if (pageModel==PAGE_MODEL_EDIT){
            $('#calendarName').attr("disabled",true);
        }
    };
    $(function() {
        //初始化画面
        init();
        // 绑定按钮事件
        $('#MstCalendarType002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#MstCalendarType002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());