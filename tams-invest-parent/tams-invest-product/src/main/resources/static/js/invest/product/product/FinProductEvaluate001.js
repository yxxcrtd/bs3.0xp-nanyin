(function () {
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
    /**
     * 折线图共通 option
     */
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
                right: '7%',
                bottom: '3%',
                containLabel: true
            },
            /*toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },*/
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: obj.xArr
            },
            yAxis: {
                type: 'value'
            },
            series: obj.series
        };
        return option;
    }

    /**
     * 饼状图共通
     */
    function pieOption(obj) {
        var option = {
            title : {
                text: obj.title,
                x:'left'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{b} :<br/> {c} ({d}%)"
            },
            legend: {
                type: 'scroll',
                orient: 'vertical',
                /*x:'right',*/
                itemWidth: 10,
                right: 10,
                top: 20,
                bottom: 20,
                data: obj.legendDate,
                selected: obj.legendSelected
            },
            series : [
                {
                    /*name: '姓名',*/
                    type: 'pie',
                    radius : '50%',
                    center: ['30%', '50%'],
                    label: {
                        normal: {
                            show: false,
                            position: 'center'
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data: obj.series,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        return option;
    }

    /**
     * 获取查询参数
     */
    var getSearchParams = function(){
        return $.serializeToObject('#searchForm');
    };

    /**
     * 产品发行前信息table
     */
    var table = $("#dataTable1").joyintable({
        ajax:{
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        checkbox: false,
        cols:[
            {field:'finprodId', title: 'FinProductEvaluate001.table.finprodId',width:100},
            {field:'finprodName', title: 'FinProductEvaluate001.table.finprodName',width:100},
            /*guyunfei BSTOZ-1248-评价日格式不正确 20191112 start*/
            {field:'mdate', title: 'FinProductEvaluate001.table.mdate',width:100,type:'date'},
            /*guyunfei BSTOZ-1248-评价日格式不正确 20191112 end*/
            {field:'prdDesignId', title: 'FinProductEvaluate001.table.prdDesignId',width:150},
            {field:'prdDesignSerialName', title: 'FinProductEvaluate001.table.prdDesignSerialName',width:150},
            {field:'prdBaseGoal', title: 'FinProductEvaluate001.table.prdBaseGoal',width:130},
            {field:'prdCharater', title: 'FinProductEvaluate001.table.prdCharater',width:100},
            /*guyunfei BSTOO-59 数据字典转换 20191113 start */
            {field:'raiseTypeStr', title: 'FinProductEvaluate001.table.raiseType',width:130,sortField:'raiseType'},
            {field:'investNatureStr', title: 'FinProductEvaluate001.table.investNature',width:100,sortField:'investNature'},
            {field:'operateModeStr', title: 'FinProductEvaluate001.table.operateMode',width:130,sortField:'operateMode'},
            {field:'tarCustomerTypeStr', title: 'FinProductEvaluate001.table.tarCustomerType',width:130,sortField:'tarCustomerType'},
            {field:'tarInvestor', title: 'FinProductEvaluate001.table.tarInvestor',width:130},
            {field:'rskLevelStr', title: 'FinProductEvaluate001.table.rskLevel',width:130,sortField:'rskLevel'},
            /*guyunfei BSTOO-59 数据字典转换 20191113 end */
            {field:'openDescribe', title: 'FinProductEvaluate001.table.openDescribe',width:130},
            {field:'prdTerm', title: 'FinProductEvaluate001.table.prdTerm',width:130},
            {field:'planIssueDate', title: 'FinProductEvaluate001.table.planIssueDate',width:130},
            {field:'planIssueScale', title: 'FinProductEvaluate001.table.planIssueScale',width:130},
            {field:'saleArea', title: 'FinProductEvaluate001.table.saleArea',width:130},
            {field:'baseRuleInfo', title: 'FinProductEvaluate001.table.baseRuleInfo',width:130},
            {field:'feeRate', title: 'FinProductEvaluate001.table.feeRate',width:130},
            {field:'toffStart', title: 'FinProductEvaluate001.table.toffStart',width:130},
            {field:'hugeRedRatio', title: 'FinProductEvaluate001.table.hugeRedRatio',width:130},
            {field:'invAssetRate', title: 'FinProductEvaluate001.table.invAssetRate',width:130},
            {field:'intenInvStrategy', title: 'FinProductEvaluate001.table.intenInvStrategy',width:130},
        ]
    });
    /**
     * 评价日资产配置情况
     */
    var table2 = $("#dataTable2").joyintable({
        ajax:{
            url: 'findPage2',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        checkbox: false,
        tree:true,
        treeId: 'portfolioId',
        treeUpId: 'parentInfo',
        treeShowName: 'portfolioName',
        lazyload:true,
        cols:[
            {field:'parentInfo',visible:false},
            {field:'finprodName',title:'产品名称',width:100},
            {field:'portfolioId',title:'组合代码',width:100},
            {field:'portfolioName',title:'组合名称',width:100},
            {field:'portfolioShare',title:'组合份额',width:100},
            {field:'assetCode',title:'资产代码',width:100},
            {field:'assetName',title:'资产名称',width:100},
            {field:'finprodTypeStr',title:'资产大类',width:100,sortField:'finprodType'},
            {field:'secondTypeStr',title:'资产二级分类',width:120,sortField:'secondType'},
            {field:'thirdTypeStr',title:'资产三级分类',width:120,sortField:'thirdType'},
            {field:'fourthTypeStr',title:'资产四级分类',width:120,sortField:'fourthType'},
            {field:'fifthTypeStr',title:'资产五级分类',width:120,sortField:'fifthType'},
            {field:'shareNum',title:'持仓数量',width:100,type:'number'},
            {field:'assetNetNum',title:'资产净值',width:100,type:'number'},
        ]
    });
    /**
     * 产品净值表现
     */
    var table3 = $("#dataTable3").joyintable({
        ajax:{
            url:'findPage3',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        checkbox: false,
        loadonce:true,
        cols:[
            {field:'finprodId', title: '产品代码',width:100},
            {field:'finprodName', title: '产品名称',width:100},
            {field:'cdate', title: '估值日期',width:120,type:'date'},
            {field:'tdyUnitValue', title: '单位净值/万份收益',width:180,type:'number',digit : 4},
            {field:'tdyUnitValueSum', title: '累计净值',width:100,type:'number',digit : 4},
            {field:'yield7', title: '七日年化收益率（%）',width:180,type:'number',digit : 4},
            {field:'tdyShareAmt', title: '产品份额',width:120,type:'number'},
        ]
    });

    /**
     * 产品净值表现折线图数据
     */
    function getEchart1Data() {
        var data = table3.options.allData;
        //日期
        var xArr = [];
        var tdyUnitValueArr = [];
        var tdyUnitValueSumArr = [];
        var yield7Arr = [];
        var legendData = [];
        if(isHB){
            legendData = ['单位净值/万份收益','累计净值','七日年化收益率(%)'];
        }else{
            legendData = ['单位净值/万份收益','七日年化收益率(%)'];
        }
        for(var i = 0; i < data.length; i++){
            xArr.push(dateToString(data[i].cdate));
            tdyUnitValueArr.push(data[i].tdyUnitValue);
            tdyUnitValueSumArr.push(data[i].tdyUnitValueSum);
            yield7Arr.push(data[i].yield7);
        }
        var seriesData = [];
        if(isHB){
            seriesData = [
                {
                    name:'单位净值/万份收益',
                    type:'line',
                    data:tdyUnitValueArr
                },{
                    name:'累计净值',
                    type:'line',
                    data:tdyUnitValueSumArr
                },{
                    name:'七日年化收益率(%)',
                    type:'line',
                    data:yield7Arr
                }
            ];
        }else{
            seriesData = [
                {
                    name:'单位净值/万份收益',
                    type:'line',
                    data:tdyUnitValueArr
                },{
                    name:'七日年化收益率(%)',
                    type:'line',
                    data:yield7Arr
                }
            ];
        }
        return {
            title : '产品净值表现' ,
            legend: legendData,
            xArr  : xArr,
            series: seriesData
        };
    }

    /**
     * 产品净值表现折线图
     */
    function showEchart1(){
        var myChart = echarts.init(document.getElementById('chart1'));
        var option = getOption(getEchart1Data());
        myChart.setOption(option);
    }
    /**
     * 收益指标
     */
    var table4 = $("#dataTable4").joyintable({
        ajax:{
            url:'findPage4',
            type:'POST',
            data: function () {
                return getSearchParams();
            }
        },
        checkbox: false,
        loadonce:true,
        cols:[
            {field:'finprodId', title: '产品代码',width:100},
            {field:'finprodName', title: '产品名称',width:100},
            {field:'accrStrDate', title: '区间开始日',width:130,type:'date'},
            {field:'accrEndDate', title: '区间结束日',width:130,type:'date'},
            {field:'yearYieldDec', title: '年化收益率（%）',width:150,type:'number',digit : 4},
            {field:'rate', title: '净值增长率（%）',width:150,type:'number',digit : 4},
            {field:'winRate', title: '胜率（%）',width:100,type:'number',digit : 4},
            {field:'periodRadio', title: '正负收益周期比（%）',width:170,type:'number',digit : 4},
        ]
    });

    /**
     * 收益指标折线图数据
     */
    function getEchart2Data() {
        var data = table4.options.allData;
        //日期
        var xArr = [];
        var yearYieldDecArr = [];
        var rateArr = [];
        var winRateArr = [];
        var periodRadioArr = [];
        for(var i = 0; i < data.length; i++){
            xArr.push(dateToString(data[i].accrEndDate));
            yearYieldDecArr.push(data[i].yearYieldDec);
            rateArr.push(data[i].rate);
            winRateArr.push(data[i].winRate);
            periodRadioArr.push(data[i].periodRadio);
        }
        return {
            title : '收益指标' ,
            legend: ['年化收益率(%)','净值增长率(%)','胜率(%)','正负收益周期比(%)'],
            xArr  : xArr,
            series: [
                {
                    name:'年化收益率(%)',
                    type:'line',
                    data:yearYieldDecArr
                },{
                    name:'净值增长率(%)',
                    type:'line',
                    data:rateArr
                },{
                    name:'胜率(%)',
                    type:'line',
                    data:winRateArr
                },{
                    name:'正负收益周期比(%)',
                    type:'line',
                    data:periodRadioArr
                }
            ]
        };
    }
    /**
     * 收益指标折线图
     */
    function showEchart2(){
        var myChart = echarts.init(document.getElementById('chart2'));
        var option = getOption(getEchart2Data());
        myChart.setOption(option);
    }

    /**
     * 收益结构指标
     */
    var table5 = $("#dataTable5").joyintable({
        ajax:{
            url:'findPage5',
            type:'POST',
            data: function () {
                return getSearchParams();
            }
        },
        checkbox: false,
        loadonce:true,
        cols:[
            {field:'finprodId', title: '产品代码',width:100},
            {field:'finprodName', title: '产品名称',width:100},
            {field:'mdate', title: '评价日',width:120,type:'date'},
            {field:'subPortfolioId', title: '子组合代码',width:120},
            {field:'subPortfolioName', title: '子组合名称',width:120},
            {field:'cportfolioId', title: '子组合区间收益目标（%）',width:180,type:'number',digit : 4},
            {field:'sectionAnnualYield', title: '子组合区间年化收益率（%）',width:200,type:'number',digit : 4},
            {field:'contribution', title: '子组合收益贡献率（%）',width:180,type:'number',digit : 4},
            {field:'targetContribution', title: '子组合目标收益贡献率（%）',width:200,type:'number',digit : 4},
        ]
    });

    /**
     * 子组合区间收益目标饼状图数据
     */
    function pie1() {
        var data = table5.options.allData;
        var title = "";
        if(data.length){
            title = "子组合区间收益目标";
        }
        var arr = [];
        var nameArr = [];
        var nameSecArr = [];
        for(var i = 0; i < data.length; i++){
            let obj = {
                name : data[i].subPortfolioName,
                value: data[i].cportfolioId
            };
            arr.push(obj);
            nameArr.push(data[i].subPortfolioName);
            nameSecArr.push(
                data[i].subPortfolioName,
                true
            );
        }
        return {
            title : title,
            legendDate : nameArr,
            legendSelected : nameSecArr,
            series: arr
        }
    }

    /**
     * 子组合区间收益目标饼状图
     */
    function drowPie1() {
        var myChart = echarts.init(document.getElementById('chart3_1'));
        var option = pieOption(pie1());
        myChart.setOption(option);
    }

    /**
     * 子组合区间年化收益率饼状图数据
     */
    function pie2() {
        var data = table5.options.allData;
        var title = "";
        if(data.length){
            title = "子组合区间年化收益率";
        }
        var arr = [];
        var nameArr = [];
        var nameSecArr = [];
        for(var i = 0; i < data.length; i++){
            let obj = {
                name : data[i].subPortfolioName,
                value: data[i].sectionAnnualYield
            };
            arr.push(obj);
            nameArr.push(data[i].subPortfolioName);
            nameSecArr.push(
                data[i].subPortfolioName,
                true
            );
        }
        return {
            title : title,
            legendDate : nameArr,
            legendSelected : nameSecArr,
            series: arr
        }
    }
    /**
     * 子组合区间年化收益率饼状图
     */
    function drowPie2() {
        var myChart = echarts.init(document.getElementById('chart3_2'));
        var option = pieOption(pie2());
        myChart.setOption(option);
    }

    /**
     * 子组合收益贡献率饼状图数据
     */
    function pie3() {
        var data = table5.options.allData;
        var title = "";
        if(data.length){
            title = "子组合收益贡献率";
        }
        var arr = [];
        var nameArr = [];
        var nameSecArr = [];
        for(var i = 0; i < data.length; i++){
            let obj = {
                name : data[i].subPortfolioName,
                value: data[i].contribution
            };
            arr.push(obj);
            nameArr.push(data[i].subPortfolioName);
            nameSecArr.push(
                data[i].subPortfolioName,
                true
            );
        }
        return {
            title : title,
            legendDate : nameArr,
            legendSelected : nameSecArr,
            series: arr
        }
    }

    /**
     * 子组合收益贡献率饼状图
     */
    function drowPie3() {
        var myChart = echarts.init(document.getElementById('chart3_3'));
        var option = pieOption(pie3());
        myChart.setOption(option);
    }

    /**
     * 子组合目标收益贡献率饼状图数据
     */
    function pie4() {
        var data = table5.options.allData;
        var title = "";
        if(data.length){
            title = "子组合目标收益贡献率";
        }
        var arr = [];
        var nameArr = [];
        var nameSecArr = [];
        for(var i = 0; i < data.length; i++){
            let obj = {
                name : data[i].subPortfolioName,
                value: data[i].targetContribution
            };
            arr.push(obj);
            nameArr.push(data[i].subPortfolioName);
            nameSecArr.push(
                data[i].subPortfolioName,
                true
            );
        }
        return {
            title : title,
            legendDate : nameArr,
            legendSelected : nameSecArr,
            series: arr
        }
    }

    /**
     * 子组合目标收益贡献率饼状图
     */
    function drowPie4() {
        var myChart = echarts.init(document.getElementById('chart3_4'));
        var option = pieOption(pie4());
        myChart.setOption(option);
    }

    /**
     * 风险指标
     */
    var table6 = $("#dataTable6").joyintable({
        ajax:{
            url:'findPage6',
            type:'POST',
            data: function () {
                return getSearchParams();
            }
        },
        checkbox: false,
        loadonce:true,
        cols:[
            {field:'finprodId', title: '产品代码',width:100},
            {field:'finprodName', title: '产品名称',width:100},
            {field:'accrStrDate', title: '区间开始日',width:130,type:'date'},
            {field:'accrEndDate', title: '区间结束日',width:130,type:'date'},
            {field:'volatility', title: '波动率（%）',width:130,type:'number',digit : 4},
            {field:'calcYearVolatility', title: '年化波动率（%）',width:150,type:'number',digit : 4},
            {field:'calcDownStdDev', title: '下行标准差',width:150,type:'number',digit : 4},
            {field:'yearTerenoRatio', title: '年化特累诺比率（%）',width:180,type:'number',digit : 4},
            {field:'periodMaximumReversion', title: '期间最大净值回撤',width:180,type:'number',digit : 4},
            {field:'historyMaximumReversion', title: '历史最大净值回撤',width:180,type:'number',digit : 4},
        ]
    });
    /**
     * 风险指标折线图数据
     */
    function getEchart3Data() {
        var data = table6.options.allData;
        //日期
        var xArr = [];
        var volatilityArr = [];
        var calcYearVolatilityArr = [];
        var calcDownStdDevArr = [];
        var yearTerenoRatioArr = [];
        var periodMaximumReversionArr = [];
        var historyMaximumReversionArr = [];
        for(var i = 0; i < data.length; i++){
            xArr.push(dateToString(data[i].accrEndDate));
            volatilityArr.push(data[i].volatility);
            calcYearVolatilityArr.push(data[i].calcYearVolatility);
            calcDownStdDevArr.push(data[i].calcDownStdDev);
            yearTerenoRatioArr.push(data[i].yearTerenoRatio);
            periodMaximumReversionArr.push(data[i].periodMaximumReversion);
            historyMaximumReversionArr.push(data[i].historyMaximumReversion);
        }
        return {
            title : '风险指标' ,
            legend: ['波动率(%)','年化波动率(%)','下行标准差','年化特累诺比率(%)','期间最大净值回撤','历史最大净值回撤'],
            xArr  : xArr,
            series: [
                {
                    name:'波动率(%)',
                    type:'line',
                    data:volatilityArr
                },{
                    name:'年化波动率(%)',
                    type:'line',
                    data:calcYearVolatilityArr
                },{
                    name:'下行标准差',
                    type:'line',
                    data:calcDownStdDevArr
                },{
                    name:'年化特累诺比率(%)',
                    type:'line',
                    data:yearTerenoRatioArr
                },{
                    name:'期间最大净值回撤',
                    type:'line',
                    data:periodMaximumReversionArr
                },{
                    name:'历史最大净值回撤',
                    type:'line',
                    data:historyMaximumReversionArr
                }
            ]
        };
    }

    /**
     * 风险指标折线图
     */
    function showEchart3(){
        var myChart = echarts.init(document.getElementById('chart4'));
        var option = getOption(getEchart3Data());
        myChart.setOption(option);
    }

    /**
     * 风险调整收益指标
     */
    var table7 = $("#dataTable7").joyintable({
        ajax:{
            url:'findPage7',
            type:'POST',
            data: function () {
                return getSearchParams();
            }
        },
        loadonce: true,
        checkbox: false,
        cols:[
            {field:'finprodId', title: '产品代码',width:100},
            {field:'finprodName', title: '产品名称',width:100},
            {field:'accrStrDate', title: '区间开始日',width:130,type:'date'},
            {field:'accrEndDate', title: '区间结束日',width:130,type:'date'},
            {field:'yearSharpRatio', title: '年化夏普比率（%）',width:150,type:'number',digit : 4},
            {field:'yearKamaRatio', title: '年化卡玛比率（%）',width:150,type:'number',digit : 4},
            {field:'calcSortinoRatio', title: '年化索提诺比率（%）',width:170,type:'number',digit : 4},
            {field:'calcJensenIndex', title: '詹森指数',width:120,type:'number',digit : 4},

        ]
    });

    /**
     * 风险调整收益指标折线图数据
     */
    function getEchart4Data() {
        var data = table7.options.allData;
        //日期
        var xArr = [];
        var yearSharpRatioArr = [];
        var yearKamaRatioArr = [];
        var calcSortinoRatioArr = [];
        var calcJensenIndexArr = [];
        for(var i = 0; i < data.length; i++){
            xArr.push(dateToString(data[i].accrEndDate));
            yearSharpRatioArr.push(data[i].yearSharpRatio);
            yearKamaRatioArr.push(data[i].yearKamaRatio);
            calcSortinoRatioArr.push(data[i].calcSortinoRatio);
            calcJensenIndexArr.push(data[i].calcJensenIndex);
        }
        return {
            title : '风险调整收益指标' ,
            legend: ['年化夏普比率(%)','年化卡玛比率(%)','年化索提诺比率(%)','詹森指数'],
            xArr  : xArr,
            series: [
                {
                    name:'年化夏普比率(%)',
                    type:'line',
                    data:yearSharpRatioArr
                },{
                    name:'年化卡玛比率(%)',
                    type:'line',
                    data:yearKamaRatioArr
                },{
                    name:'年化索提诺比率(%)',
                    type:'line',
                    data:calcSortinoRatioArr
                },{
                    name:'詹森指数',
                    type:'line',
                    data:calcJensenIndexArr
                }
            ]
        };
    }
    /**
     * 风险调整收益指标折线图
     */
    function showEchart4(){
        var myChart = echarts.init(document.getElementById('chart5'));
        var option = getOption(getEchart4Data());
        myChart.setOption(option);
    }

    /**
     * check查询条件和加载table
     */
    var checkAndLoadTable = function(){
        if(vdate == null || vdate === ''){
            Joyin.error("请选择起始日！");
            return;
        }
        if(mdate == null || mdate === ''){
            Joyin.error("请选择评价日！");
            return;
        }
        if(vdate > mdate){
            Joyin.error("起始日需小于等于评价日！");
            return;
        }
        if(mdate >= sysDate){
            Joyin.error("评价日需小于平台时间！");
            return;
        }
        if($('#select_proName').val() == null || $('#select_proName').val() === ''){
            Joyin.error("请选择产品名称！");
            return;
        }
        table.load();
        table2.load();
        table3.load();
        table4.load();
        table5.load();
        table6.load();
        table7.load();

        //画图
        showEchart1();
        showEchart2();
        showEchart3();
        showEchart4();
        drowPie1();
        drowPie2();
        drowPie3();
        drowPie4();
    };
    $(function () {
        $("#FinProductEvaluate001_btn_search").click(function () {
            checkAndLoadTable();
            //判断是否为货币型产品
            //var type = $('#select_proName').select2('data')[0].object;
            // if(type == proType){
            //     table3.showColumns([{field:'tdyUnitValueSum',visible:false}]);
            //     isHB = false;
            // }
            // else{
            //     table3.showColumns([{field:'tdyUnitValueSum',visible:true}]);
            //     isHB = true;
            // }
            //showEchart1();
        });
    });
}());