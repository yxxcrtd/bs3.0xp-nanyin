/**
 * MstCalendarType003 日历类型管理节假日查询
 */
$(function() {
    //画面初始化
    var init = function(){       
            $('#result').attr("disabled",true);       
    };
    //画面联动：输入交易日历名称和查询日期，联动显示查询结果
    var queryData = function() {
        var calendarName = $("#calendarName").val();
        var cDate=$("#cDate").val();
        if(calendarName == "" || cDate == ""){
            return ;
        }
        var object = {'calendarName':calendarName,'cDate':cDate};
        Joyin.ajax(basePath + "submitmaintenance/mstcalendartype/SvrMstCalendarType003/getResult", object, function(result){
            //如果查询结果数量不为0，联动出查询结果
            if(result>0){
                $('#result').val("是");
            }
            else $('#result').val("否");
        });
    };
    $(function() {        
        //初始化画面
        init(); 
        //联动事件
        $('#calendarName').change(function() {
            queryData();
        });
        $('#cDate').change(function() {
            queryData();
        });
    });
}());