$(function() {
	
	var sourceData = splitData();
	
	//切割数组，把数组中的日期和数据分离，返回数组中的日期和数据
    function splitData() {
        var categoryData = [];
        var values = [];
        var data = getSourceData();
        for(var i = 0; i < data.length; i++){
	    	categoryData.push(data[i].dealDate.split(' ')[0]);
	    	values.push([
	    		parseFloat(data[i].openPrice), 
	    		parseFloat(data[i].closePrice), //收盘价 data[1]
	    		parseFloat(data[i].lowPrice), 
	    		parseFloat(data[i].highPrice), 
	    	]);
    	}
        return {
            categoryData: categoryData, //数组中的日期 x轴对应的日期
            values: values              //数组中的数据 y轴对应的数据
        };
    }
    
  //计算MA平均线，N日移动平均线=N日收盘价之和/N  dayCount要计算的天数(5,10,20,30)
    function calculateMA(dayCount) {
        var result = [];
        for (var i = 0, len = sourceData.values.length; i < len; i++) {
            if (i < dayCount) {
                result.push('-');
                continue;
            }
            var sum = 0;
            for (var j = 0; j < dayCount; j++) {
                //收盘价总和
                sum += sourceData.values[i - j][1];
            }
            result.push(sum / dayCount);
        }
        return result;
    }
    
    var option = {
            title: {    //标题
                text: '',
                left: 0
            },
            tooltip: {  //提示框
                trigger: 'axis',    //触发类型：坐标轴触发
                axisPointer: {  //坐标轴指示器配置项
                    type: 'cross'   //指示器类型，十字准星
                }
            },
            legend: {   //图例控件，点击图例控制哪些系列不现实
                data: ['日K', 'MA5', 'MA10', 'MA20', 'MA30']
            },
            grid: {     //直角坐标系
                show:true,
                left: '20%',    //grid组件离容器左侧的距离
                right: '20%',
                bottom: '15%',
            },
            xAxis: {
                type: 'category',   //坐标轴类型，类目轴
                data: sourceData.categoryData,
                boundaryGap : false,    //刻度作为分割线，标签和数据点会在两个刻度上
                axisLine: {onZero: false},
                splitLine: {show: false},   //是否显示坐标轴轴线
                min: 'dataMin', //特殊值，数轴上的最小值作为最小刻度
                max: 'dataMax'  //特殊值，数轴上的最大值作为最大刻度
            },
            yAxis: {
                scale: true,    //坐标刻度不强制包含零刻度
                splitArea: {
                    show: true  //显示分割区域
                }
            },
            dataZoom: [     //用于区域缩放
                {
                    filterMode:'filter',    //当前数据窗口外的数据被过滤掉来达到数据窗口缩放的效果  默认值filter
                    type: 'inside', //内置型数据区域缩放组件
                    start: 50,      //数据窗口范围的起始百分比
                    end: 100        //数据窗口范围的结束百分比
                },
                {
                    show: true,
                    type: 'slider', //滑动条型数据区域缩放组件
                    y: '90%',
                    start: 50,
                    end: 100
                }
            ],
            series: [   //图表类型
                {
                    name: '日K',
                    type: 'candlestick',    //K线图
                    data: sourceData.values,     //y轴对应的数据

                    /////////////////////////////////图标标线///////////////////////////

                    markLine: {
                        symbol: ['none', 'none'],   //标线两端的标记类型
                        data: [
                            {
                                name: JoyinUtil.getLabel('PtlAnalyzeAfterInvest001.closeLine'),
                                yAxis: parseFloat(WARNINGLINE)
                            }, 
                            {
                                name: JoyinUtil.getLabel('PtlAnalyzeAfterInvest001.warningLine'),
                                yAxis: parseFloat(CLOSELINE)
                            }
                        ]

                    }

                },

                {   //MA5 5天内的收盘价之和/5
                    name: 'MA5',
                    type: 'line',
                    data: calculateMA(5),
                    smooth: true,
                    lineStyle: {
                        normal: {opacity: 0.5}
                    }
                },
                {
                    name: 'MA10',
                    type: 'line',
                    data: calculateMA(10),
                    smooth: true,
                    lineStyle: {    //标线的样式
                        normal: {opacity: 0.5}
                    }
                },
                {
                    name: 'MA20',
                    type: 'line',
                    data: calculateMA(20),
                    smooth: true,
                    lineStyle: {
                        normal: {opacity: 0.5}
                    }
                },
                {
                    name: 'MA30',
                    type: 'line',
                    data: calculateMA(30),
                    smooth: true,
                    lineStyle: {
                        normal: {opacity: 0.5}
                    }
                },

            ]
        };
    
    /**
     * 初始化数据sourceData
     */
    function getSourceData(){
    	var temp = [];
    	var data = {pledgeStockId: PLEDGESTOCKID};
    	Joyin.ajaxJson(basePath + 'invest/portfolio/ptlanalyzeafterinvest/PtlAnalyzeAfterInvest002/getSourceData',data,
                function(result) {
    		temp = result;
        },false);
    	return temp;
    }
    
    /**
     * 绘制图表
     */
    function showEcharts(){
        $("#pledgeChart").show();
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('pledgeChart'));
        myChart.setOption(option);
    }

    /**
     * 初始化画面
     */
    function init(){
        $("#finprodId").text(FINPRODID);
        $("#finprodName").text(FINPRODNAME);
        $("#pledgeStockId").text(PLEDGESTOCKID);
        $("#pledgeStockName").text(PLEDGESTOCKNAME);
        showEcharts();
    }
    
    /**
     * 画面加载完成
     */
    $(function() {
        init();
        
        //统计按钮
        $("#PtlAnalyzeAfterInvest002_btn_statistics").click(function () {
        	var param = {
        			categoryData: sourceData.categoryData,
        			values: sourceData.values,
                    warningLine: WARNINGLINE,
                    closeLine: CLOSELINE
        	}
            Joyin.dialog(JoyinUtil.getLabel("PtlAnalyzeAfterInvest003"),
                basePath + "invest/portfolio/ptlanalyzeafterinvest/PtlAnalyzeAfterInvest003/init",
                "98%", "98%", param);
        });
        
        //绑定导出按钮
        $('#PtlAnalyzeAfterInvest002_btn_export').click(function() {
        	/*var data = '';
        	for(var i = 0; i < sourceData.values.length; i++){
        		for(var j = 0; j< sourceData.values[i].length; j++){
        			data += sourceData.values[i][j]+'+';
        		}
        		data += sourceData.categoryData[i]+' ';
        	}
    		data =  data.substring(0, data.length - 1);PLEDGESTOCKID
        	Joyin.exportExcel('export', {data: data});*/
        	Joyin.exportExcel('export', {pledgeStockId: PLEDGESTOCKID});
        });
    });
}());
