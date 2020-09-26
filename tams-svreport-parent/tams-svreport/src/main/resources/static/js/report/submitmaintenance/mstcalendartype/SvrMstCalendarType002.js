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
        Joyin.ajaxJson(basePath + 'submitmaintenance/mstcalendartype/SvrMstCalendarType002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
            	Joyin.ajax("save",object, function(result) {
            		parent.layer.close(parent.layer.getFrameIndex(window.name));
                    });
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
        $('#SvrMstCalendarType002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());