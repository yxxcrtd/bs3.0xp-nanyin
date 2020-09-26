/**
 * Created by Administrator on 2017/9/22 0022.
 */
/*By zhengyan*/
function getPrevMonth(){
    /*var date = Date.ofDate();
    date.setMonth(date.getMonth()-1);
    return date.format('yyyy-MM-dd');*/
    var nowdays = Date.ofDate();
    var year = nowdays.getFullYear();
    var month = nowdays.getMonth();
    if(month==0)
    {
        month=12;
        year=year-1;
    }
    if (month < 10) {
        month = "0" + month;
    }
    return  year + "-" + month + "-" + "01";//上个月的第一天

}
$(document).ready(function() {
//    var myDate= Date.ofDate(); 
//    var startYear=myDate.getFullYear()-50;
//    var endYear=myDate.getFullYear()+50;
//    var obj=document.getElementById('selectYear');
//    for (var i=startYear;i<=endYear;i++){
//        obj.options.add(new Option(i,i));
//    }
//    obj.options[obj.options.length-51].selected=1;
    var calendar_left  = $('#calendar').fullCalendar({
        customButtons: {
            myCustomButton: {
                text: '<',
                click: function() {
                    $('#calendar').fullCalendar('prev');
                    $('#calendar1').fullCalendar('prev');
                }
            }
        },

        header: {
            left: 'myCustomButton',
            center: 'title',
            right: ''
        },

        // customize the button names,
        // otherwise they'd all just say "list"
        views: {
            listDay: { buttonText: 'list day' },
            listWeek: { buttonText: 'list week' }
        },
        selectable:true,
        selectHelper: false,
        select: function(start, end, allDay) {
            $('#Holidaymarket001_btnSetHolid,#Holidaymarket001_btnSetWork').attr('currentCheckDateStart',Date.ofDate(start).format('yyyy-MM-dd'));
            $('#Holidaymarket001_btnSetHolid,#Holidaymarket001_btnSetWork').attr('currentCheckDateEnd',Date.ofDate(end).format('yyyy-MM-dd'));//截止日期需要减一
        },
        unselectAuto:true,
        defaultView: 'month',
        defaultDate: getPrevMonth(),
        navLinks: true, // can click day/week names to navigate views
        editable: false,
        eventLimit: true, // allow "more" link when too many events
        events: function(start, end, timezone, callback) {
            var startDate = Date.ofDate(start);
            startDate = startDate.format('yyyy-MM-dd');
            var endDate = Date.ofDate(end);
            endDate = endDate.format('yyyy-MM-dd');
            var date = this.getDate().format('YYYY-MM');
            $.ajax({
                type : "POST",
                url: basePath+"submitmaintenance/holidaymarket/Holidaymarket001/initHolid",
                dataType: 'json',
                data: {
                    start: startDate,
                    end: endDate,
                    marketCalendarType:$('#marketCalendarType').val()
                },
                success: function(data) {
                    var events = [];
                    for(var i = 0;i < data.length;i++){
                        var title = '';
                        var start = Date.ofDate(data[i].busiDate);
                        start = start.format('yyyy-MM-dd');
                        if(start.indexOf(date) == -1){
                            continue;
                        }
                        var backgroundColor = '';
                        var borderColor = '';
                        if(data[i].isHoliday == '0'){
                            title = '假';
                            backgroundColor = '#90c31f';
                            borderColor = '#90c31f';
                        }else{
                            title = '班';
                            backgroundColor = '#4da6ff';
                            borderColor = '#4da6ff';
                        }
                        events.push({
                            title: title,
                            start: start,
                            className:'holiday-des',
                            backgroundColor:backgroundColor,
                            borderColor:borderColor
                        });
                    }
                    callback(events);
                }
            });
        }
    });  
    var calendar_right  = $('#calendar1').fullCalendar({
        customButtons: {
            myCustomButton: {
                text: '>',
                click: function() {
                    $('#calendar').fullCalendar('next');
                    $('#calendar1').fullCalendar('next');
                }
            }
        },

        header: {
            left: '',
            center: 'title',
            right: 'myCustomButton'
        },

        // customize the button names,
        // otherwise they'd all just say "list"
        views: {
            listDay: { buttonText: 'list day' },
            listWeek: { buttonText: 'list week' }
        },
        unselectAuto:true,
        selectable:true,
        selectHelper: false,
        select: function(start, end, allDay) {
            $('#Holidaymarket001_btn_setHolid,#Holidaymarket001_btn_setWork').attr('currentCheckDateStart',Date.ofDate(start).format('yyyy-MM-dd'));
            $('#Holidaymarket001_btn_setHolid,#Holidaymarket001_btn_setWork').attr('currentCheckDateEnd',Date.ofDate(end).format('yyyy-MM-dd'));//截止日期需要减一
        },
        defaultView: 'month',
        defaultDate: Date.ofDate().format('yyyy-MM-dd'),
        navLinks: true, // can click day/week names to navigate views
        editable: false,
        eventLimit: true, // allow "more" link when too many events
        events: function(start, end, timezone, callback) {
            var startDate = Date.ofDate(start);
            startDate = startDate.format('yyyy-MM-dd');
            var endDate = Date.ofDate(end);
            endDate = endDate.format('yyyy-MM-dd');
            var date = this.getDate().format('YYYY-MM');
            $.ajax({
                type : "POST",
                url: basePath+"submitmaintenance/holidaymarket/Holidaymarket001/initHolid",
                dataType: 'json',
                data: {
                    start: startDate,
                    end: endDate,
                    marketCalendarType:$('#marketCalendarType').val()
                },
                success: function(data) {
                    var events = [];
                    for(var i = 0;i < data.length;i++){
                        var title = '';
                        var start = Date.ofDate(data[i].busiDate);
                        start = start.format('yyyy-MM-dd');
                        if(start.indexOf(date) == -1){
                            continue;
                        }
                        var backgroundColor = '';
                        var borderColor = '';
                        if(data[i].isHoliday == '0'){
                            title = '假';
                            backgroundColor = '#90c31f';
                            borderColor = '#90c31f';
                        }else{
                            title = '班';
                            backgroundColor = '#4da6ff';
                            borderColor = '#4da6ff';
                        }
                        events.push({
                            title: title,
                            start: start,
                            className:'holiday-des',
                            backgroundColor:backgroundColor,
                            borderColor:borderColor
                        });
                    }
                    callback(events);
                }
            });
        }
    });

    //设为节假日
    $('#Holidaymarket001_btn_setHolid').click(function(){
        var start = $(this).attr('currentCheckDateStart');
        var end = $(this).attr('currentCheckDateEnd');
        if(start === undefined 
                || start === null 
                || start === ''){
            layer.alert('请选中需要设置为节假日的日期！');
            return;
        }
        setDateByType(start,end,"holid");
    });
    
    //设为工作日
    $('#Holidaymarket001_btn_setWork').click(function(){
        var start = $(this).attr('currentCheckDateStart');
        var end = $(this).attr('currentCheckDateEnd');
        if(start === undefined 
                || start === null 
                || start === ''){
            layer.alert('请选中需要设置为工作日的日期！');
            return;
        }
        setDateByType(start,end,"work");
    });
    
    function setDateByType(start,end,type){
        if($('#marketCalendarType').val() == null || $('#marketCalendarType').val() == ''){
            layer.alert('日期设置市场日历类型不能为空！');
            return;
        }
        $.ajax({  
            type : "POST",
            url : basePath+"submitmaintenance/holidaymarket/Holidaymarket001/setDateByType",
            data : {  
                start:start,
                end:end,
                type:type,
                marketCalendarType:$('#marketCalendarType').val()
            },
            success : function(data) {
                if(data != null){
                    if(data.result != null && data.result != '' && data.result > 0){
                        calendar_left.fullCalendar('refetchEvents');
                        calendar_right.fullCalendar('refetchEvents');
                    }else if(type === 'holid'){
                        layer.alert('设置节假日失败！');
                    }else{
                        layer.alert('设置工作日失败！');
                    }
                }else if(type === 'holid'){
                    layer.alert('设置节假日失败！');
                }else{
                    layer.alert('设置工作日失败！');
                }
            },
            error:function(){
                layer.alert('系统错误！');
            }
        });
    }
    
    $('#Holidaymarket001_btn_search').click(function(){
        var year = $('#selectYear').val();
        
        var leftDate = getPrevMonth();
        var rightDate = Date.ofDate().format('yyyy-MM-dd');
        
        if(year) {
            leftDate = Date.ofDate(year,0,1);
            rightDate = Date.ofDate(year,1,1);
        }
        
        calendar_left.fullCalendar( 'gotoDate', leftDate);
        calendar_right.fullCalendar( 'gotoDate', rightDate);

        calendar_left.fullCalendar('refetchEvents');
        calendar_right.fullCalendar('refetchEvents');
    })
    
    // 导入按钮点击事件
    $('#Holidaymarket001_btn_import').click(function() {
        Joyin.forConfirmInit("请确认是否要导入数据？", null, function() {
            Joyin.uploadFile(function(fileName) {
                Joyin.ajax('importFile', {fileName: fileName});
            },function() {
                table.load();
            });
        });
    });
});
