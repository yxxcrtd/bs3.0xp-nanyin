
(function() {
	
    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit() {
        var param = $.serializeToObject('#searchForm');
        param.startDate = $('#repRaisePdate').startDate();
        param.endDate = $('#repRaisePdate').endDate();
        return param;
    };
    /**
     * 日期转字符串
     */
    function dateToString(obj){
        var date = new Date(obj);
        var year = date.getFullYear();
        var month =(date.getMonth() + 1).toString();
        var day = (date.getDate()).toString();
        if (month.length === 1) {
            month = "0" + month;
        }
        if (day.length === 1) {
            day = "0" + day;
        }
        var dateTime = year + "-" + month + "-" + day;
        return dateTime;
    }
    //折线图共通
    function getOption(obj) {
        var option = {
            /*title: {
                text: obj.title
            },*/
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data:obj.legend
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: obj.xArr,
                axisLabel: {
                    interval:0,
                    rotate:15
                }

            },
            yAxis: {
                type: 'value',
                min:'dataMin', //就是这两个 最小值
                max:'dataMax'
            },
            series: obj.series
        };
        return option;
    };
    function echart1(data) {
        //日期
        var xArr = [];
        var netUnitValueArr = [];//单位净值
        for(var i = 0; i < data.length; i++){
            xArr.push(dateToString(data[i].vDate));
            netUnitValueArr.push(data[i].netUnitValue);

        }
        return {
            title : '净值趋势' ,
            legend: ['单位净值'],
            xArr  : xArr,
            series: [            {
                name:'单位净值',
                type:'line',
                stack: '总量',
                data:netUnitValueArr
            }
            ]
        };
    };
    function showEchart1(data){
        //单位净值echart图
        var myChart1 = echarts.init(document.getElementById('eChart1'));
        var option=getOption(echart1(data));
        myChart1.setOption(option);
    }

    function echart2(data) {
        //日期
        var xArr = [];
        var yieldTermArr = [];//当前周期年化
        var intRateArr = [];//业绩比较基准
        for(var i = 0; i < data.length; i++){
            xArr.push(dateToString(data[i].vDate));
            yieldTermArr.push(data[i].yieldTerm);
            intRateArr.push(data[i].intRate);
        }
        return {
            title : '年化收益率' ,
            legend: ['当前周期年化','业绩比较基准'],
            xArr  : xArr,
            series: [            {
                name:'当前周期年化',
                type:'line',
                data:yieldTermArr
            },{
                name:'业绩比较基准',
                type:'line',
                data:intRateArr
            }
            ]
        };
    };
    function showEchart2(data){
        //单位净值echart图
        var myChart2 = echarts.init(document.getElementById('eChart2'));
        var option=getOption(echart2(data));
        myChart2.setOption(option);
    }

    $('#repRaisePdate').change(function () {
        var endDate=$('#repRaisePdate').endDate();
        if (!(endDate<sysDate)){
            Joyin.error(JoyinUtil.getMessage("产品查询结束日应小于平台日"));
            $('#repRaisePdate').val('');
        }
    })
        
    $(function() {
        // 按钮事件
        $('#ValNetValueConfirm005_btn_search').click(function() {
            if (!$('#finprodId').val()){
                Joyin.error(JoyinUtil.getMessage("产品简称不能为空"));
                return;
            } ;
            if (!$('#repRaisePdate').val()){
                Joyin.error(JoyinUtil.getMessage("查询区间不能为空"));
                return;
            } ;
            var param=saveOrSubmit();
            Joyin.ajaxJson(basePath + 'valuation/dailyoperation/dailyaccountmake/valnetvalueconfirm/ValNetValueConfirm005/getInfo',param, function(result) {
                // if (!result.length>0) {
                //     Joyin.error(JoyinUtil.getMessage("该产品该区间内无数据"));
                // };
                showEchart1(result);
                showEchart2(result);
            });
        });
    });
}());
