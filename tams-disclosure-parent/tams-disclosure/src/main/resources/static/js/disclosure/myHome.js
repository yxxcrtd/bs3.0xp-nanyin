var h1;//待披露 表格div初始化高度
var h2;//今日已披露 表格div初始化高度
var h3;//预计披露 表格div初始化高度
var h3m;//预计披露 表格 中表格主体部分初始化高度

var tabh1;//待披露 表格初始化高度
var tabh2;//待披露 表格初始化高度
var tabh3;//待披露 表格初始化高度
$(document).ready($(function () {
    'use strict';
    // 去除转授权
    $('#setDelegation', parent.window.document).remove();
    /* 菜单栏logo变小*/
    $(".logo-img",parent.window.document).css("height","33px");
    
    if($("#ssoLogOut", parent.window.document).length > 0 ){
        // 去除[注销]（增加了单点登录的[注销登录]）
        $("#authLogout", parent.window.document).remove();
        // 注销登录绑定点击事件
        $("#ssoLogOut", parent.window.document).click(function(){
            ssoLogOut();
        });
    }
    
    
    // ------------------------------------------------------------------------------------------------------
    // ----------------------------------------------- 信息披露首页改造  开始----------------------------------------
    // ------------------------------------------------------------------------------------------------------
    // 分辨率宽<=1366
    if (1366 >= window.screen.width) {
        tabh1 = 144;
        tabh2 = 91;
        tabh3 = 114;
    } else {
        tabh1 = 186;
        tabh2 = 108;
        tabh3 = 147;
    }
    
    //系统时间
    var sysDate = null;
    // 初始化披露截止日
    Joyin.ajaxSync(
        basePath + "disclosure/getSystemNow",
        null,
        function(result){
            if(result){
                sysDate = result.data.sysDate;
                $('#searchDate').val(result.data.startDate + ' - ' + result.data.endDate);
            }
        }
    );
    // 查询条件 - 披露截止日
    var getSearchParams = function() {
        return {
//            searchDate :$("#searchDate").val()
            searchDateStart : $('#searchDate').startDate(),
            searchDateEnd : $('#searchDate').endDate()
        }
    };
    // tab跳转的链接
    var tabHref = function() {
        return function (data, type, row, meta) {
            if(data && data > '0' && row.firstCol != '合计'){ //非合计行和数据不为0则给跳转链接
                var str ='<a href=\'javascript:redirectUrl("'+sysDate+'","'+type.field+'","'+row.firstCol+'","'+meta.elem+'")\'><span class="datacell">'+data+'</span></a>';
                return str;
            }else if(data && data > '0' && row.firstCol == '合计'){
                return '<span class="datacell">'+data+'</span>';
            }
            return '<span>'+data+'</span>';
        }
    };
    
    var dataTableCols = null;
    var dataTable1Cols = new Array();
    var dataTable2Cols = new Array();
    var dataTable3Cols = new Array();
    var paramObj = null;
    // 加载动态表头
    Joyin.ajaxSync(
        basePath + "disclosure/getdataTableCols", 
        paramObj, 
        function(result) {
            dataTableCols = JSON.parse(result);
            //给有数据的单元格绑定跳转新tab的方法
            for ( var i = 0; i <dataTableCols.length; i++){
                dataTableCols[i].render = tabHref();
            }
            //定义首页三个表格的表头
            dataTable1Cols[0] = {field : 'firstCol', title : '状态',sortable : false, width : 105, style:'font-size:14px;'};
            dataTable2Cols[0] = {field : 'firstCol', title : '状态',sortable : false, width : 105, style:'font-size:14px;'};
            dataTable3Cols[0] = {field : 'firstCol', title : '日期',sortable : false, width : 105, style:'font-size:14px;'};
            //将后台组装的表头增加到首页的表头当中去
            for ( var i = 0; i <dataTableCols.length; i++){
                dataTable1Cols.push(dataTableCols[i]);
                dataTable2Cols.push(dataTableCols[i]);
                dataTable3Cols.push(dataTableCols[i]);
            }
        }
    );
    //待披露 表格
    var table1=$("#dataTable1").joyintable({
        ajax : {
            url : basePath + "disclosure/searchdataDpl",
            type : 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        checkbox:false,
        page:false,
        loadonce:true,//前台分页，后台不做
        searchOnInit:true, //初始化立即加载数据
        resize:true,
        cols : dataTable1Cols,
        height:tabh1,
        done:function(res,page,count){
            //防止出现横向滚动条 
            $(".layui-table-box").css("width","101%");
        }
    });
    //今日已披露 表格
    var table2=$("#dataTable2").joyintable({
        ajax: {
            url: basePath + "disclosure/searchdataYpl",
            type: 'POST'
        },
        checkbox:false,
        page:false,
        loadonce:true,//前台分页，后台不做
        searchOnInit:true, //初始化立即加载数据
        resize:true,
        height:tabh2,
        cols : dataTable2Cols
    });
    
    //预计披露 表格
    var table3=$("#dataTable3").joyintable({
        ajax : {
            url : basePath + "disclosure/searchdataYjpl",
            type : 'POST'
        },
        checkbox:false,
        page:false,
        loadonce:true,//前台分页，后台不做
        searchOnInit:true, //初始化立即加载数据
        resize:true,
        height:tabh3,
        cols : dataTable3Cols
        ,done:function(res,page,count){
            $('#dataTable3').next().width($('#dataTable2').next().width());
        }
    });
    
    //披露截止日修改事件
    $("#searchDate").change(function() {
        h1 = $('#dataTable1').next().height();
        if(!$("#searchDate").val()){
            Joyin.error("披露截止日不能为空！");
            return;
        }
        table1.reload();
        $('#dataTable1').next().height(h1);// 解决table.reload()重置高度的问题：设置为初始化的高度
    });
    
    // 给'首页'tab绑定点击事件，若点击，则刷新‘我的主页’表格内数据
    $("[lay-id='-1']", parent.window.document).click(function(){
        h1 = $('#dataTable1').next().height();
        h2 = $('#dataTable2').next().height();
        h3 = $('#dataTable3').next().height();
        h3m = $('#dataTable3').next().children().first().children().first().next().height();
        table1.reload();
        table2.reload();
        table3.reload();
        
        setTableHeight();
    });
    // ------------------------------------------------------------------------------------------------------
    // ----------------------------------------------- 信息披露首页改造  结束----------------------------------------
    // ------------------------------------------------------------------------------------------------------
    //使主页自适应高度
    $(window.parent.document).find("iframe").load(function() {
		$("#carouselHome",parent.window.document).css("height","100%");
		var frameHeight = $(window.parent.document).height()-90;
		$("[pageid='disclosureHome']",parent.window.document).css("height",frameHeight);
    });
}));

/**
 * 在不同的分辨率下，对三个表的高度进行设定
 *      解决table.reload()重置高度的问题：设置为初始化的高度
 * @returns
 */
function setTableHeight(){
//    $('#dataTable1').next().height(h1);
//    $('#dataTable2').next().height(h2);
//    $('#dataTable3').next().height(h3);
//    $('#dataTable3').next().children().first().children().first().next().height(h3m);
//    // 分辨率宽<=1366
//    if(1366 >= window.screen.width){
//        $('#dataTable1').next().height(162);
//        $('#dataTable2').next().height(69);
//        $('#dataTable3').next().height(131);
//    }
//    else{
//        $('#dataTable1').next().height(194);
//        $('#dataTable2').next().height(77);
//        $('#dataTable3').next().height(160);
//    }
	
	$('#dataTable3').next().width($('#dataTable2').next().width());
}
/**
 * 跳转tab。
 * sysDate=系统时间
 * field=点击事件的列Field
 * firstCol=点击事件的行第一列名称
 * elem=表格元素
 */
function redirectUrl(sysDate,field,firstCol,elem){
    //日期参数，默认为系统时间
    var paraDate = sysDate;
    var T1 = addDate(sysDate, 1);
    var T2 = addDate(sysDate, 2);
    var T3 = addDate(sysDate, 3);
    var searchDateStart = $('#searchDate').startDate();
    var searchDateEnd = $('#searchDate').endDate();
    
    //画面初始化地址
//    var url = basePath;
    var url = "";
    //画面id
    var pageId = "";
    //taba标题
    var title = "";
    //MENU_ID
    var id = "";
    switch (firstCol) {
        case "生成失败":
            pageId = "ReportWorkMonitor001";
            title = "报表作业监控";
            url = url + "/disclosure/information/manage/workmonitor/ReportWorkMonitor002/init";
            id = "REPORT_003_003";
            paraDate = searchDateEnd;
            break;
        case "待确认":
            pageId = "ReportConfirmManage001";
            title = "报表确认管理";
            url = url + "/disclosure/information/reveal/reportconfirm/ReportConfirmManage001/init";
            id = "REPORT_006_003_002";
            paraDate = searchDateEnd;
            break;
        case "待披露":
            pageId = "ReportRevealManage001";
            title = "报表披露管理";
            url = url + "/disclosure/information/reveal/reportreveal/ReportRevealManage001/init";
            id = "REPORT_004_003";
            paraDate = searchDateEnd;
            break;
        case "已披露":
            pageId = "ReportRevealManage001";
            title = "报表披露管理";
            url = url + "/disclosure/information/reveal/reportreveal/ReportRevealManage001/init";
            id = "REPORT_004_003";
            break;
        case T1:
        case T2:
        case T3:
            pageId = "EstimateReveal001";
            title = "预计披露";
            url = url + "/disclosure/information/manage/estimatereveal/EstimateReveal001/init";
            id = "REPORT_003_004";
            break;
        default:
            break;
    }
    //参数。 confromPage=01表示从首页跳转到tab。
    var urlPara = "?firstCol=" + firstCol.replace("+","") + "&repId=" + field.replace("repId","") 
        + "&searchDate=" + paraDate + "&confromPage=01" + "&searchDateStart=" + searchDateStart
        + "&searchDateEnd=" + searchDateEnd;
    
    // 今日状态增加生成失败，与待披露的生成失败区分 zhangyf 20190307 start
    if("#dataTable2" == $.trim(elem) && "生成失败" == $.trim(firstCol)){
    	urlPara = "?firstCol=" + firstCol.replace("+","") + "&repId=" + field.replace("repId","") 
        + "&searchDate=" + sysDate + "&confromPage=01" + "&execDate=" + sysDate;
    }
    // 今日状态增加生成失败，与待披露的生成失败区分 zhangyf 20190307 end
    var newTab = {
        pageId : pageId,
        title : title,
        url : url+ urlPara,
        id : id,
        icon : ""
    };
    top.tab.tabDelete(newTab.id);
    top.tab.tabAdd(newTab);
    
}
/**
 * 日期增加
 *  在原有日期基础上，增加days天数，默认增加1天
 * @param date
 * @param days
 * @returns
 */
function addDate(date, days) {
    if(days == undefined || days == '') {
        days = 1;
    }
    var date = new Date(date);
    date.setDate(date.getDate() + days);
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var mm = "'" + month + "'";
    var dd = "'" + day + "'";
    
    //单位数前面加0
    if(mm.length == 3) {
        month = "0" + month;
    }
    if(dd.length == 3) {
        day = "0" + day;
    }

    var newDate = date.getFullYear() + "/" + month + "/" + day
    return newDate;
}

//单点登录退出
function ssoLogOut() {
    //登出请求url
    //var logoutUrl = $('#ssoLogOut',parent.window.document).attr('data-href');
    $.ajax({
        type:"POST",
        url:basePath+"auth/logout", //访问的链接
        dataType:"jsonp",  //数据格式设置为jsonp
        jsonp:"callback",  //Jquery生成验证参数的名称
        complete:function(XMLHttpRequest, textStatus){
        	$.cookie('tams_user', null, { expires: -100, path: '/' });//清除cookie：tams_user
            console.log("登出完成...");
            window.top.close();//frame的关闭当前窗口
        }
    });
}
