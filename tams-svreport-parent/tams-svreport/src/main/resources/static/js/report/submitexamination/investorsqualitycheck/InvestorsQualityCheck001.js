$(function () {
    /*var tableInitFlag=true;*/
    var selectcolor;
    var color = ['rgb(168,222,100)','rgb(251,247,134)', 'rgb(255,153,153)'];
    /*var xmlData={};
    readConfig=function(){
        Joyin.ajaxSync('readConfig', null, function(result){
            //把读取到的配置文件赋值
        	xmlData.tops = result["top"];
        	xmlData.mids = result["mid"];
        });
    };
    readConfig();*/
    var curSelectedIndex = 0;

    var table0 = $('#table0').joyintable({
        ajax: {
            url: 'searchTableZJLX',
            type: 'POST',
            data: function() {
                var parm={};
                parm=getPageParams();
                parm.index=curSelectedIndex+1;
                return parm;
            },
        },
        cols: [
            {title: "识别标识", field: "indentifyMark",width:'25%'},
            {title: "投资者境内外标识", field: "holderMark",width:'25%'},
            {title: "投资者类别", field: "holderType",width:'25%'},
            {title: "证件类别", field: "identifyTypeStr",width:'25%'},
        ]
    });

    var table1 = $('#table1').joyintable({
        ajax: {
            url: 'searchTableZJPP',
            type: 'POST',
            data: function() {
                var parm={};
                parm=getPageParams();
                parm.index=curSelectedIndex+1;
                return parm;
            },
        },
        cols: [
            {title: "登记银行代码", field: "registBankCode",width:'20%'},
            {title: "识别标识", field: "indentifyMark",width:'20%'},
            {title: "证件类别", field: "identifyTypeStr",width:'20%',sortField:'identifyType'},
            {title: "证件号码", field: "identifyNumber",width:'20%'},
            {title: "投资者姓名", field: "holderName",width:'20%'},
        ]
    });

    var table2 = $('#table2').joyintable({
        ajax: {
            url: 'searchTableJSX',
            type: 'POST',
            data: function() {
                var parm={};
                parm=getPageParams();
                parm.index=curSelectedIndex+1;
                return parm;
            },
        },
        cols: [
            {title: "产品登记编码", field: "prdRegistCode",width:'20%'},
            {title: "识别标识", field: "indentifyMark",width:'20%'},
            {title: "持有日期", field: "holdDateStr",width:'20%',sortField:'holdDate'},
            {title: "报送状态", field: "sendStatus",width:'20%'},
            {title: "报送日期", field: "submitTimeStr",width:'20%',sortField:'submitTime'},
        ]
    });

    var table01 = $('#indexIdTable01').joyintable({
        ajax: {
            url: 'searchInvestorsPassRate',
            type: 'POST',
            data: function() {
                var parm=parm=getPageParams();
                return parm;
            },
        },
        click:function(table, data){
            document.getElementById("registration").innerText=objDataArr[data.pageType-1].xAxisDataDetail;
            document.getElementById("registrationTitle").innerText=objDataArr[data.pageType-1].xAxisDataRate;
            curSelectedIndex=data.pageType-1;
            checkOnClickRow(data);
        },
        page : false,
        loadonce : true,
        checkbox : false,
        rowno: false, // 显示行号
        cols: [
            {field:'unQualified', width:0,visible:false},
            {title: "质量核查不合格数量/总数量", field: "pageType",width:'80%',sortable:false,
                render: function (data, type, row, meta) {
                    var index = row.pageType-1;
                    //100%为绿色，非100%为红色
                    if(row.rate=="100"){
                        selectcolor=color[0];
                    }
                    else{
                        selectcolor=color[2];
                    }
                    return '<div class="layui-col-md6" style="float:left">'+'<span style="float:right">'+objDataArr[data-1].xAxisData+'</span>'+'</div>'+
                        '<div class="layui-col-md6" style="float:right ;padding-left: 5px;padding-right: 5px;">'+'<div style="float:left ;background:'+selectcolor
                        +';color:white ;height: 100%;width:100%">'+'<div style="padding-left: 5px;padding-right: 5px;">'+row.unQualified+"/"+row.total+'</div>'+'</div>'+'</div>';
                }
            },
            {title: "合格率", field: "rate",width:'20%',sortable:false,
                render: function (data, type, row, meta) {
                    return typeof row.rate == 'undefined' || !row.rate  ? '0%' : row.rate +'%';
                }
            }
        ]
    });

    // add by zhangliang 20210309 新增投资者质量核查相关 start
    var tableCPZT = $('#tableCPZT').joyintable({
        ajax: {
            url: 'searchTableCPZT',
            type: 'POST',
            data: function() {
                var parm={};
                parm=getPageParams();
                parm.index=curSelectedIndex+1;
                return parm;
            },
        },
        cols: [
            {title: "产品登记编码", field: "prdRegistCode",width:'20%'},
            {title: "识别标识", field: "indentifyMark",width:'20%'},
            {title: "持有日期", field: "holdDate",width:'20%', type:"date"},
            {title: "产品发行状态", field: "prdIssueSts",width:'20%'},
            {title: "实际终止日期", field: "prdAclMDate",width:'20%', type:"date"},
        ]
    });

    var tableSLPP = $('#tableSLPP').joyintable({
        ajax: {
            url: 'searchTableSLPP',
            type: 'POST',
            data: function() {
                var parm={};
                parm=getPageParams();
                parm.index=curSelectedIndex+1;
                return parm;
            },
        },
        cols: [
            {title: "产品登记编码", field: "prdRegistCode",width:'20%'},
            {title: "产品起息日", field: "prdVDate",width:'20%', type:"date"},
            {title: "持有日期", field: "holdDate",width:'20%', type:"date"},
            {title: "产品端个人投资者数", field: "prdSingleTotal",width:'20%'},
            {title: "产品端法人投资者数", field: "prdLegalTotal",width:'20%'},
            {title: "产品端非法人投资者数", field: "prdUnLegalTotal",width:'20%'},
            {title: "持有端个人投资者数", field: "investSingleTotal",width:'20%'},
            {title: "持有端法人投资者数", field: "investLegalTotal",width:'20%'},
            {title: "持有端非法人投资者数", field: "investUnLegalTotal",width:'20%'},
            {title: "错误记录数", field: "errRecTotal",width:'20%'},
        ]
    });

    var tableMJZFE = $('#tableMJZFE').joyintable({
        ajax: {
            url: 'searchTableMJQFE',
            type: 'POST',
            data: function() {
                var parm={};
                parm=getPageParams();
                parm.index=curSelectedIndex+1;
                return parm;
            },
        },
        loadonce : true,
        cols: [
            {title: "产品登记编码", field: "prdRegistCode",width:'20%'},
            {title: "产品起息日", field: "prdVDate",width:'20%', type:"date"},
            {title: "持有日期", field: "holdDate",width:'20%', type:"date"},
            {title: "产品端募集总份额", field: "prdRaiseTotalQty",width:'20%'},
            {title: "持有端募集总份额", field: "investRaiseTotalQty",width:'20%'},
        ]
    });

    var tableSJMJJE = $('#tableSJMJJE').joyintable({
        ajax: {
            url: 'searchTableMJQYE',
            type: 'POST',
            data: function() {
                var parm={};
                parm=getPageParams();
                parm.index=curSelectedIndex+1;
                return parm;
            },
        },
        loadonce : true,
        cols: [
            {title: "产品登记编码", field: "prdRegistCode",width:'20%'},
            {title: "产品起息日", field: "prdVDate",width:'20%', type:"date"},
            {title: "持有日期", field: "holdDate",width:'20%', type:"date"},
            {title: "产品端募集金额", field: "prdRaiseTotalAmt",width:'20%'},
            {title: "持有端募集金额", field: "investRaiseTotalAmt",width:'20%'},
        ]
    });

    var tableDJWZX = $('#tableDJWZX').joyintable({
        ajax: {
            url: 'searchTableDJWZX',
            type: 'POST',
            data: function() {
                var parm={};
                parm=getPageParams();
                parm.index=curSelectedIndex+1;
                return parm;
            },
        },
        cols: [
            {title: "产品登记编码", field: "prdRegistCode",width:'20%'},
            {title: "查询日期", field: "holdDate",width:'20%', type:"date"},
            {title: "产品起息日", field: "prdVDate",width:'20%', type:"date"},
            {title: "产品到期日", field: "prdMDate",width:'20%', type:"date"},
        ]
    });

    var tableCPYEFBS = $('#tableCPYEFBS').joyintable({
        ajax: {
            url: 'searchTableCPYEFBX',
            type: 'POST',
            data: function() {
                var parm={};
                parm=getPageParams();
                parm.index=curSelectedIndex+1;
                return parm;
            },
        },
        loadonce : true,
        cols: [
            {title: "产品登记编码", field: "prdRegistCode", width:'20%'},
            {title: "持有日期", field: "holdDate",width:'20%', type:"date"},
            {title: "业务结束日", field: "busiMDate",width:'20%', type:"date"},
            {title: "产品端最新产品余额", field: "prdBalanceAmt",width:'20%'},
            {title: "持有端产品余额", field: "investBalanceAmt",width:'20%'},
        ]
    });

    var tableCPYEKFS = $('#tableCPYEKFS').joyintable({
        ajax: {
            url: 'searchTableCPYEKFX',
            type: 'POST',
            data: function() {
                var parm={};
                parm=getPageParams();
                parm.index=curSelectedIndex+1;
                return parm;
            },
        },
        loadonce : true,
        cols: [
            {title: "产品登记编码", field: "prdRegistCode", width:'20%'},
            {title: "持有日期", field: "holdDate",width:'20%', type:"date"},
            {title: "业务结束日", field: "busiMDate",width:'20%', type:"date"},
            {title: "产品端最新产品余额", field: "prdBalanceAmt",width:'20%'},
            {title: "持有端产品余额", field: "investBalanceAmt",width:'20%'},
        ]
    });

    var tableCPFEFBS = $('#tableCPFEFBS').joyintable({
        ajax: {
            url: 'searchTableCPFEFBX',
            type: 'POST',
            data: function() {
                var parm={};
                parm=getPageParams();
                parm.index=curSelectedIndex+1;
                return parm;
            },
        },
        loadonce : true,
        cols: [
            {title: "产品登记编码", field: "prdRegistCode", width:'20%'},
            {title: "持有日期", field: "holdDate",width:'20%', type:"date"},
            {title: "业务结束日", field: "busiMDate",width:'20%', type:"date"},
            {title: "产品端最新产品份额", field: "prdTotalQty",width:'20%'},
            {title: "持有端产品份额", field: "investTotalQty",width:'20%'},
        ]
    });

    var tableCPFEKFS = $('#tableCPFEKFS').joyintable({
        ajax: {
            url: 'searchTableCPFEKFX',
            type: 'POST',
            data: function() {
                var parm={};
                parm=getPageParams();
                parm.index=curSelectedIndex+1;
                return parm;
            },
        },
        loadonce : true,
        cols: [
            {title: "产品登记编码", field: "prdRegistCode", width:'20%'},
            {title: "持有日期", field: "holdDate",width:'20%', type:"date"},
            {title: "业务结束日", field: "busiMDate",width:'20%', type:"date"},
            {title: "产品端最新产品份额", field: "prdTotalQty",width:'20%'},
            {title: "持有端产品份额", field: "investTotalQty",width:'20%'},
        ]
    });

    /**
     * 定义指标对象
     */
    var objDataArr = [{tableDiv : "tableDiv0", tableObj : table0, xAxisData : "证件类型", xAxisDataRate : "证件类型合格率", xAxisDataDetail : "用于检验境内个人投资者未使用“居民身份证”或“临时身份证”进行登记的情况"},
        {tableDiv : "tableDiv1", tableObj : table1, xAxisData : "证件匹配", xAxisDataRate : "证件匹配合格率", xAxisDataDetail : "用于校验投资者证件类型、证件号码相同，投资者姓名不同的情况"},
        {tableDiv : "tableDiv2", tableObj : table2, xAxisData : "及时性", xAxisDataRate : "及时性合格率", xAxisDataDetail : "用于检验银行是否按照T+1（下一工作日）及时性要求开展登记的情况"},
        {tableDiv : "tableDivCPZT", tableObj : tableCPZT, xAxisData : "产品状态", xAxisDataRate : "产品状态合格率", xAxisDataDetail : ""},
        {tableDiv : "tableDivSLPP", tableObj : tableSLPP, xAxisData : "投资者数量匹配", xAxisDataRate : "投资者数量匹配合格率", xAxisDataDetail : ""},
        {tableDiv : "tableDivMJZFE", tableObj : tableMJZFE, xAxisData : "募集总份额", xAxisDataRate : "募集总份额合格率", xAxisDataDetail : ""},
        {tableDiv : "tableDivSJMJJE", tableObj : tableSJMJJE, xAxisData : "实际募集金额（元）", xAxisDataRate : "实际募集金额（元）合格率", xAxisDataDetail : ""},
        {tableDiv : "tableDivDJWZX", tableObj : tableDJWZX, xAxisData : "登记完整性", xAxisDataRate : "登记完整性合格率", xAxisDataDetail : ""},
        {tableDiv : "tableDivCPYEFBS", tableObj : tableCPYEFBS, xAxisData : "产品余额（元）（封闭式产品）", xAxisDataRate : "产品余额（元）（封闭式产品）合格率", xAxisDataDetail : ""},
        {tableDiv : "tableDivCPYEKFS", tableObj : tableCPYEKFS, xAxisData : "产品余额（元）（开放式产品）", xAxisDataRate : "产品余额（元）（开放式产品）合格率", xAxisDataDetail : ""},
        {tableDiv : "tableDivCPFEFBS", tableObj : tableCPFEFBS, xAxisData : "产品份额（封闭式产品）", xAxisDataRate : "产品份额（封闭式产品）合格率", xAxisDataDetail : ""},
        {tableDiv : "tableDivCPFEKFS", tableObj : tableCPFEKFS, xAxisData : "产品份额（开放式产品）", xAxisDataRate : "产品份额（开放式产品）合格率", xAxisDataDetail : ""}];


    var checkOnClickRow = function (data) {
        // 所有表格div对象隐藏
        $.each(objDataArr,function(i,v){
            $('#' + objDataArr[i].tableDiv).toggle(false);
        })
        // 选中表格div对象显示
        $('#' + objDataArr[data.pageType - 1].tableDiv).toggle(true);

        // 选中table对象加载
        objDataArr[data.pageType - 1].tableObj.table ? objDataArr[data.pageType - 1].tableObj.reload() : objDataArr[data.pageType - 1].tableObj.load();
    };
    // add by zhangliang 20210309 新增投资者质量核查相关 end

    /**
     * 初始化的函数
     */
    var init = function(){
        //防止报出时间为空的信息
        setTimeout(function() {searchData();},500);
        document.getElementById("registrationTitle").innerText=objDataArr[0].xAxisDataRate;
        document.getElementById("registration").innerText=objDataArr[0].xAxisDataDetail;

    };

    var getPageParams = function() {
        return {
            orgCode : $('#orgCode').val()==null?null:$('#orgCode').val().join(","),
            // 查询日期
            searchDate : $('#searchDate').val(),
        };
    };

    var searchData = function () {
        var param=getPageParams();
        if(param.searchDate==''||param.searchDate==null){
            Joyin.error("查询日期不可为空！");
            return;
        }
        if(table01.table){
            table01.reload();
        }
        else{
            table01.load();
        }
    };

    $(function() {
    	init();
        // 查询按钮点击事件
        $('#InvestorsQualityCheck001_btn_search').bind("click",searchData);
    });
}())