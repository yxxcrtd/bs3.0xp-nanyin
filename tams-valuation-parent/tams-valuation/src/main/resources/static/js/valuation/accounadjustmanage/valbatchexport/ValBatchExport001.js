$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var param = $.serializeToObject('#searchForm');
        if(param.reportType){
            param.reportType = param.reportType.join(",");
        }
        return param
    };

    /**
     * 金额格式化: 框架数据表格 render 后
     */
    var formatMoney = function(s, n) {
        n = n > 0 && n <= 20 ? n : 2;
        s = parseFloat((s + '').replace(/[^\d\.-]/g, '')).toFixed(n) + '';
        var l = s.split('.')[0].split('').reverse(), r = s.split('.')[1];
        var t = '';
        for (var i = 0; i < l.length; i++) {
            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? ',' : '');
        }
        return t.split('').reverse().join('') + '.' + r;

    }

    /**
     * 联动绑定 - 筛选条件
     */
    var linkBind = function() {
        // 绑定联动：账套-筛选条件-日期、期限
        $("#booksetId").change(function() {
            var booksetId = $("#booksetId").val();
            var reportTermType = $("#reportTermType").val();
            if (booksetId != "" && [ '01', '02', '03', '04', '05' ].indexOf(reportTermType) > -1) {
                Joyin.ajax(basePath + 'valuation/accounadjustmanage/valbatchexport/ValBatchExport001/getDateInfo', {
                    booksetId : booksetId,
                    reportTermType : reportTermType
                }, function(data) {
                    if (reportTermType === '01') {
                        $("#date_reportDate").val(data.reportDate).trigger("change");
                    } else if (reportTermType === '02') {
                        $("#month_reportDate").val(data.reportDate).trigger("change");
                    } else if ([ '03', '04','05' ].indexOf(reportTermType) > -1) {
                        $("#year_reportDate").val(data.reportDate).trigger("change");
                        $("#reportTerm").textSelect('setValue', data.reportTerm);
                    } 
//                    else if (reportTermType === '05') {
//                        $("#year_reportDate").val(data.reportDate).trigger("change");
//                    }
                });
            } else {
                $("#date_reportDate").val("").trigger("change");
                $("#month_reportDate").val("").trigger("change");
                $("#year_reportDate").val("").trigger("change");
                $("#reportTerm").textSelect('setValue', false);
            }
        });
        $("#reportType").change(function() {
            var reportType = $("#reportType").val();
            if (reportType.indexOf("02") >= 0 || reportType.indexOf("03") >= 0) {
                $("#lastDimendiv").show();
            } else {
                $("#lastDimendiv").hide();
            }
        });
        $("#reportTermType").change(function() {

            var booksetId = $("#booksetId").val();
            var booksetGroup = $("#booksetGroup").val();
            var reportTermType = $("#reportTermType").val();
            if (reportTermType != '' && (booksetId != '' || booksetGroup != '')) {
                Joyin.ajax(basePath + 'valuation/accounadjustmanage/valbatchexport/ValBatchExport001/getDateInfo', {
                    booksetId : booksetId,
                    booksetGroup : booksetGroup,
                    reportTermType : reportTermType
                }, function(data) {
                    if (reportTermType === '01') {
                        $("#date_reportDate").val(data.reportDate).trigger("change");
                    } else if (reportTermType === '02') {
                        $("#month_reportDate").val(data.reportDate).trigger("change");
                    } else if ([ '03', '04','05' ].indexOf(reportTermType) > -1) {
                        $("#year_reportDate").val(data.reportDate).trigger("change");
                        $("#reportTerm").textSelect('setValue', data.reportTerm);
                    } 
//                    else if (reportTermType === '05') {
//                        $("#data_reportDate").val(data.reportDate).trigger("change");
//                    }
                });
            } else {
                $("#date_reportDate").val("").trigger("change");
                $("#month_reportDate").val("").trigger("change");
                $("#year_reportDate").val("").trigger("change");
                $("#reportTerm").textSelect('setValue', false);
            }

            var laydate = layui.laydate;
            var arr1 = [ "03", "04" ] // 03-季报, 04-半年报
            var arr2 = ["03", "04", "05"] // 03-季报, 04-半年报, 05-年报
//            var arr2 = [ "03", "04" ] // 03-季报, 04-半年报, 05-年报
            var reportTermType = $("#reportTermType").val();
            if (arr1.indexOf(reportTermType) >= 0) {// 报表期限类型为半年报或季报时，可以选择报表期限
                $("#c_reportTerm").show();
            } else {
                $("#c_reportTerm").hide();
            }
            if (arr2.indexOf(reportTermType) >= 0) {// 03-季报, 04-半年报,
                // 05-年报
                laydate.render({
                    elem : '#year_reportDate',
                    type : 'year',
                    format : 'yyyy'
                });
                $("#c_year_reportDate").show();
                $("#c_month_reportDate").hide();
                $("#c_date_reportDate").hide();
                clearDateInput();
            }
            if (reportTermType == '02') {// 月报
                laydate.render({
                    elem : '#month_reportDate',
                    type : 'month',
                    format : 'yyyy-MM'
                });
                $("#c_year_reportDate").hide();
                $("#c_month_reportDate").show();
                $("#c_date_reportDate").hide();
                clearDateInput();
            }
            if (reportTermType == '01') {// 日报
                laydate.render({
                    elem : '#date_reportDate',
                    type : 'date',
                    format : 'yyyy-MM-dd'
                });
                $("#c_year_reportDate").hide();
                $("#c_month_reportDate").hide();
                $("#c_date_reportDate").show();
                clearDateInput();
            }
//            if (reportTermType == '05') {// 年报
//                laydate.render({
//                    elem : '#date_reportDate',
//                    type : 'date',
//                    format : 'yyyy-MM-dd'
//                });
//                $("#c_year_reportDate").hide();
//                $("#c_month_reportDate").hide();
//                $("#c_date_reportDate").show();
//                clearDateInput();
//            }
        });
        $("#year_reportDate").click(function(event) {
            event.stopPropagation();// 阻止事件冒泡
        });
        $("#month_reportDate").click(function(event) {
            event.stopPropagation();// 阻止事件冒泡
        });
        $("#date_reportDate").click(function(event) {
            event.stopPropagation();// 阻止事件冒泡
        });

    }

    /**
     * 清空日期 input
     */
    var clearDateInput = function() {
        $("#year_reportDate").val("");
        $("#month_reportDate").val("");
        $("#date_reportDate").val("");
        $("#reportTerm").val("");
        $("#reportTerm").find("option").remove(); // 框架缺陷，防止同code不会更新

        $("#year_reportDate").val("");
        $("#month_reportDate").val("");
        $("#date_reportDate").val("");
        $("#reportTerm").val("");
    }

    /**
     * 导出
     */
    var exportExcel = function() {
        Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/valbatchexport/ValBatchExport001/check',
                getSearchParams(), function(result) {
                    if (result.data) {
                        Joyin.exportExcel('export', getSearchParams());
                    }
                });
    };

    /**
     * 画面加载完成
     */
    $(function() {
        linkBind(); // 联动绑定 - 筛选条件

        $("#batchBooksetType").change(function() {
            $("#booksetId").val('');
            var batchBooksetType = $("#batchBooksetType").val();
            $("#booksets").val(batchBooksetType);
            if ('0' == batchBooksetType) {
                $("#dynamicName").html("账套组<i class='must'>*</i>");
            } else {
                $("#dynamicName").html("账套<i class='must'>*</i>");
            }
        });

        // 绑定导出事件
        $('#ValBatchExport001_btn_export').click(function() {
            exportExcel();
        });

    });

});
