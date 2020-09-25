/**
 * 投资评价管理
 */
(function() {
    /**
     * 获取查询参数
     */

    var getPageParams = function() {
        var pageParam = $.serializeToObject('#searchForm');

        return pageParam;
    };

    /**
     * 组合信息查询
     */
    var table1 = $("#dataTable1").joyintable({
        ajax: {
            url: 'findPortInfo',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        height: 'full-' + 220,
        checkbox:false,
        // tree:true,
        loadonce:true,
        cols:[
            {field:'portfolioId', width:120, title: 'FinInvestmentEvaluate001.portfolioId'}
            ,{field:'portfolioName', width:120, title: 'FinInvestmentEvaluate001.portfolioName'}
            ,{field:'effDate', width:200, title: 'FinInvestmentEvaluate001.mdate',type:'date'}
            ,{field:'evaStr', width:120, title: 'FinInvestmentEvaluate001.evalIndex',sortField:'evalIndex'}
            ,{field:'indexId', width:130, title: 'FinInvestmentEvaluate001.indexId',type:'number',digit:4}
        ]
    });

    /**
     * 评价日资产配置情况
     */
    var table2 = $("#dataTable2").joyintable({

        ajax: {
            url: 'findPage2',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        checkbox:false,
        height: 'full-' + 220,
        cols:[
             {field:'portfolioId', width:120, title: 'FinInvestmentEvaluate001.portfolioId'}
            ,{field:'portfolioName', width:130, title: 'FinInvestmentEvaluate001.portfolioName'}
            ,{field:'portfolioShare', width:120, title: 'FinInvestmentEvaluate001.portfolioShare'}
            ,{field:'assetId', width:120, title: 'FinInvestmentEvaluate001.assetId'}
            ,{field:'assetName', width:130, title: 'FinInvestmentEvaluate001.assetName'}
            ,{field:'finprodType2Str', width:130, title: 'FinInvestmentEvaluate001.finprodType2',sortField:'finprodType2'}
            ,{field:'type9Str', width:130, title: 'FinInvestmentEvaluate001.type9',sortField:'type9'}
            ,{field:'type10Str', width:130, title: 'FinInvestmentEvaluate001.type10',sortField:'type10'}
            ,{field:'type11Str', width:130, title: 'FinInvestmentEvaluate001.type11',sortField:'type11'}
            ,{field:'type12Str', width:130, title: 'FinInvestmentEvaluate001.type12',sortField:'type12'}
            ,{field:'shareAmt', width:130, title: 'FinInvestmentEvaluate001.shareAmt',type:'number'}
            ,{field:'netAssetValue', width:130, title: 'FinInvestmentEvaluate001.netAssetValue',type:'number'}
            // ,{field: 'operation', title: 'Productelementdes001.table.operation', type: 'action', buttons: ['dealRevoke'],
            //     render: function (data, type, row, meta) {
            //         if(row.effectStatus=="01")
            //             return "<a class='pro-link dealRevoke' style='cursor: pointer;'>" + "撤销" + "</a>";
            //     }
            // }
        ],
    });
    //净值表现
    var table3 = $("#dataTable3").joyintable({
        ajax: {
            url: 'findPage3',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        checkbox:false,
        height: 'full-' + 220,
        cols:[
            {field:'portfolioId', width:120, title: 'FinInvestmentEvaluate001.portfolioId'}
            ,{field:'portfolioName', width:120, title: 'FinInvestmentEvaluate001.portfolioName'}
            ,{field:'cdate', width:120, title: 'FinInvestmentEvaluate001.valDate',type:'date'}
            ,{field:'tdyUnitValue', width:120, title: 'FinInvestmentEvaluate001.netUnitValue',type:'number'}
            ,{field:'tdyUnitValueSum', width:120, title: 'FinInvestmentEvaluate001.totalNetValue',type:'number'}
            ,{field:'yield7', width:120, title: 'FinInvestmentEvaluate001.sevenDayRate',type:'number'}
            ,{field:'tdyShareAmt', width:120, title: 'FinInvestmentEvaluate001.prdQty',type:'number'}
        ]
        // done:function () {
        //     var data = table3.getAllRows();
        //     if(data.length){
        //         showEchart3();
        //     }
        // }
    });


    //收益指标
    var table4 = $("#dataTable4").joyintable({
        ajax: {
            url: 'findPage4',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        loadonce:true,
        checkbox:false,
        height: 'full-' + 220,
        cols:[
            {field:'portfolioId', width:120, title: 'FinInvestmentEvaluate001.portfolioId'}
            ,{field:'portfolioName', width:120, title: 'FinInvestmentEvaluate001.portfolioName'}
            ,{field:'startDate', width:120, title: '区间开始日',type:'date'}
            ,{field:'endDate', width:120, title: 'FinInvestmentEvaluate001.accrEndDate',type:'date'}
            ,{field:'yearYield', width:120, title: 'FinInvestmentEvaluate001.yearYield',type:'number',digit:4}
            ,{field:'netValueGrowthRate', width:120, title: 'FinInvestmentEvaluate001.netValueGrowthRate',type:'number',digit:4}
            ,{field:'winRate', width:120, title: 'FinInvestmentEvaluate001.winProbability',type:'number',digit:4}
            ,{field:'plusMinusEarningsPeriodRatio', width:120, title: 'FinInvestmentEvaluate001.revenueCycleRatio',type:'number',digit:4}
        ]
    });
    //风险指标
    var table5 = $("#dataTable5").joyintable({
        ajax: {
            url: 'findPage5',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        checkbox:false,
        height: 'full-' + 220,
        loadonce:true,
        cols:[
            {field:'portfolioId', width:120, title: 'FinInvestmentEvaluate001.portfolioId'}
            ,{field:'portfolioName', width:120, title: 'FinInvestmentEvaluate001.portfolioName'}
            ,{field:'startDate', width:120, title: '区间开始日',type:'date'}
            ,{field:'endDate', width:120, title: 'FinInvestmentEvaluate001.accrEndDate',type:'date'}
            ,{field:'fluctuateRate', width:120, title: 'FinInvestmentEvaluate001.volatility',type:'number',digit:4}
            ,{field:'yearFluctuateRate', width:120, title: 'FinInvestmentEvaluate001.yearVolatility',type:'number',digit:4}
            ,{field:'descendingStandardDeviation', width:120, title: 'FinInvestmentEvaluate001.downsideSD',type:'number',digit:4}
            ,{field:'annualizedTraynorRatio', width:120, title: 'FinInvestmentEvaluate001.renaultRatio',type:'number',digit:4}
            ,{field:'maximumRangeRetracement', width:120, title: 'FinInvestmentEvaluate001.maxWithdrawal',type:'number'}
            ,{field:'historyBiggistWorthBack', width:120, title: 'FinInvestmentEvaluate001.hisMaxWithdrawal',type:'number'}
        ]
    });
    //风险调整收益指标
    var table6 = $("#dataTable6").joyintable({
        ajax: {
            url: 'findPage6',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        checkbox:false,
        loadonce:true,
        height: 'full-' + 220,
        cols:[
            {field:'portfolioId', width:120, title: 'FinInvestmentEvaluate001.portfolioId'}
            ,{field:'portfolioName', width:120, title: 'FinInvestmentEvaluate001.portfolioName'}
            ,{field:'startDate', width:120, title: '区间开始日',type:'date'}
            ,{field:'endDate', width:120, title: 'FinInvestmentEvaluate001.accrEndDate',type:'date'}
            ,{field:'annualizedSharpe', width:120, title: 'FinInvestmentEvaluate001.annualSharpRatio',type:'number',digit:4}
            ,{field:'annualKarmaRatio', width:120, title: 'FinInvestmentEvaluate001.annualKamaRatio',type:'number',digit:4}
            ,{field:'annualizedSotinoRatio', width:120, title: 'FinInvestmentEvaluate001.annualSotinoRatio',type:'number',digit:4}
            ,{field:'jensenIndex', width:120, title: 'FinInvestmentEvaluate001.jansenIndex',type:'number',digit:4}
        ]
    });
    //策略执行有效性
    var table7 = $("#dataTable7").joyintable({
        ajax: {
            url: 'findPage7',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        checkbox:false,
        height: 'full-' + 220,
        loadonce:true,
        cols:[
            {field:'portfolioId', width:120, title: 'FinInvestmentEvaluate001.portfolioId'}
            ,{field:'portfolioName', width:120, title: 'FinInvestmentEvaluate001.portfolioName'}
            ,{field:'effDate', width:120, title: 'FinInvestmentEvaluate001.mdate',type:'date'}
            ,{field:'batchId', width:120, title: 'FinInvestmentEvaluate001.strategyDetId'}
            ,{field:'timePointEff', width:120, title: 'FinInvestmentEvaluate001.pointAvailability',type:'number',digit:4}
            ,{field:'avgAmtRatio', width:120, title: 'FinInvestmentEvaluate001.averageAvailability',type:'number',digit:4}
        ]
    });
    //策略执收益来源
    var table8 = $("#dataTable8").joyintable({
        ajax: {
            url: 'findPage8',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        checkbox:false,
        loadonce:true,
        height: 'full-' + 220,
        cols:[
            {field:'portfolioId', width:120, title: 'FinInvestmentEvaluate001.portfolioId'}
            ,{field:'portfolioName', width:120, title: 'FinInvestmentEvaluate001.portfolioName'}
            ,{field:'cDate', width:120, title: 'FinInvestmentEvaluate001.mdate',type:'date'}
            ,{field:'finprodId', width:120, title: 'FinInvestmentEvaluate001.assetId'}
            ,{field:'finprodName', width:120, title: 'FinInvestmentEvaluate001.assetName'}
            ,{field:'bonds', width:120, title: '资产持仓',type:'number'}
            ,{field:'calcAssetContributionRate', width:120, title: 'FinInvestmentEvaluate001.incomeRate',type:'number',digit:4}
        ]
    });
   //  /**
   //   * 绑定查看明细事件
   //   */
   // table.bind('click', '.showDetail', function (data, index) {
   // var requirementId=data.requirementId;
   // Joyin.dialog(JoyinUtil.getLabel("Productelementdes002"),
   //         basePath + "invest/product/rddesign/productelementdes/productelementdes003/init",
   //         "90%", "90%", {pageModel:PAGE_MODEL_REFERENCE, requirementId:requirementId
   //         });   });

    var showDig=function () {
        
    }
    /**
     * 导出
     */
    var exportExcel = function() {
        Joyin.exportExcel('export', getPageParams());
    };
    //日期限制
    var dateChange=function(){
     var vd=$('#startDate').val();var md=$('#endDate').val();
     var td=new Date(); alert(sysDate);
     td=td.getFullYear()+'-'+(td.getMonth()+1)+'-'+td.getDate();

     if (!(td>vd)) {Joyin.error(JoyinUtil.getMessage("FIN_INVEST_E_0138"));
             $('#startDate').val('');
     };
     if (!(td>md)) {Joyin.error(JoyinUtil.getMessage("FIN_INVEST_E_0139"));
     $('#endDate').val('');};
     if((md<vd) && md){
         Joyin.error(JoyinUtil.getMessage("FIN_INVEST_E_0137"));
         $('#startDate').val('');
     };

}
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
                left: '6%',
                right: '7%',
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

            },
            yAxis: {
                type: 'value'
            },
            series: obj.series
        };
        return option;
    }
    //净值表现
    function echart3(obj) {
        var data = obj;
        //日期
        var xArr = [];
        var tdyUnitValueArr = [];//单位净值
        var tdyUnitValueSumArr = [];//累计净值
        var yield7Arr = [];//七天年化收益率
        var tdyShareAmtArr=[];//产品份额
        for(var i = 0; i < data.length; i++){
            xArr.push(dateToString(data[i].cdate));
            tdyUnitValueArr.push(data[i].tdyUnitValue);
            tdyUnitValueSumArr.push(data[i].tdyUnitValueSum);
            yield7Arr.push(data[i].yield7);
            tdyShareAmtArr.push(data[i].tdyShareAmt);
        }
        return {
            title : '净值表现' ,
            legend: ['单位净值/万份收益','累计净值','七日年化收益率','产品份额'],
            xArr  : xArr,
            series: [            {
                name:'单位净值/万份收益',
                type:'line',
               data:tdyUnitValueArr
            },{
                name:'累计净值',
                type:'line',
               data:tdyUnitValueSumArr
            },{
                name:'七日年化收益率',
                type:'line',
                data:yield7Arr
            }
                ,{
                    name:'产品份额',
                    type:'line',
                    data:tdyShareAmtArr
                }
            ]
        };
    }
    function showEchart3(obj){
        //净值表现echart图
        var myChart3 = echarts.init(document.getElementById('eChart3'));
        var option=getOption(echart3(obj));
        myChart3.setOption(option);
    }

//收益指标
    function echart4() {
        var data = table4.options.allData;
        //日期
        var xArr = [];
        var yearYieldArr = [];//年化收益率（%）
        var netValueGrowthRateArr = [];//净值增长率（%）
        var winRateArr = [];//胜率（%）
        var plusMinusEarningsPeriodRatioArr=[];//正负收益周期比（%）
        for(var i = 0; i < data.length; i++){
            xArr.push(dateToString(data[i].endDate));
            yearYieldArr.push(data[i].yearYield);
            netValueGrowthRateArr.push(data[i].netValueGrowthRate);
            winRateArr.push(data[i].winRate);
            plusMinusEarningsPeriodRatioArr.push(data[i].plusMinusEarningsPeriodRatio);
        }
        return {
            title : '收益指标' ,
            legend: ['年化收益率（%）','净值增长率（%）','胜率（%）','正负收益周期比（%）'],
            xArr  : xArr,
            series: [            {
                name:'年化收益率（%）',
                type:'line',

                data:yearYieldArr
            },{
                name:'净值增长率（%）',
                type:'line',

                data:netValueGrowthRateArr
            },{
                name:'胜率（%）',
                type:'line',

                data:winRateArr
            }
                ,{
                    name:'正负收益周期比（%）',
                    type:'line',

                    data:plusMinusEarningsPeriodRatioArr
                }
            ]
        };
    }
    function showEchart4(){
        //收益指标echart图
        var myChart4 = echarts.init(document.getElementById('eChart4'));
        var option=getOption(echart4());
        myChart4.setOption(option);
    }
    //风险指标
    function echart5() {
        var data = table5.options.allData;
        //日期
        var xArr = [];
        var fluctuateRateArr = [];//波动率（%）
        var yearFluctuateRateArr = [];//年化波动率（%）
        var descendingStandardDeviationArr = [];//下行标准差
        var annualizedTraynorRatioArr=[];//年化特累诺比率（%）
        var maximumRangeRetracementArr=[];//期间最大净值回撤
        var historyBiggistWorthBackArr=[];//历史最大净值回撤
        for(var i = 0; i < data.length; i++){
            xArr.push(dateToString(data[i].endDate));
            fluctuateRateArr.push(data[i].fluctuateRate);
            yearFluctuateRateArr.push(data[i].yearFluctuateRate);
            descendingStandardDeviationArr.push(data[i].descendingStandardDeviation);
            annualizedTraynorRatioArr.push(data[i].annualizedTraynorRatio);
            maximumRangeRetracementArr.push(data[i].maximumRangeRetracement);
            historyBiggistWorthBackArr.push(data[i].historyBiggistWorthBack);
        }
        return {
            title : '风险指标' ,
            legend: ['波动率（%）','年化波动率（%）','下行标准差','年化特累诺比率（%）','期间最大净值回撤','历史最大净值回撤'],
            xArr  : xArr,
            series: [            {
                name:'波动率（%）',
                type:'line',

                data:fluctuateRateArr
            },{
                name:'年化波动率（%）',
                type:'line',

                data:yearFluctuateRateArr
            },{
                name:'下行标准差',
                type:'line',

                data:descendingStandardDeviationArr
            }
                ,{
                    name:'年化特累诺比率',
                    type:'line',

                    data:annualizedTraynorRatioArr
                }
                ,{
                    name:'期间最大净值回撤',
                    type:'line',

                    data:maximumRangeRetracementArr
                }
                ,{
                    name:'历史最大净值回撤',
                    type:'line',

                    data:historyBiggistWorthBackArr
                }
            ]
        };
    }
    function showEchart5(){
        //风险指标echart图
        var myChart5 = echarts.init(document.getElementById('eChart5'));
        var option=getOption(echart5());
        myChart5.setOption(option);
    }
    //风险调整收益指标
    function echart6() {
        var data = table6.options.allData;
        //日期
        var xArr = [];
        var annualizedSharpeArr = [];//年化夏普比率（%）
        var annualKarmaRatioArr = [];//年化卡玛比率（%）
        var annualizedSotinoRatioArr = [];//年化索提诺比率（%）
        var jansenIndexArr=[];//詹森指数
        for(var i = 0; i < data.length; i++){
            xArr.push(dateToString(data[i].endDate));
            annualizedSharpeArr.push(data[i].annualizedSharpe);
            annualKarmaRatioArr.push(data[i].annualKarmaRatio);
            annualizedSotinoRatioArr.push(data[i].annualizedSotinoRatio);
            jansenIndexArr.push(data[i].jensenIndex);
        }
        return {
            title : '风险调整收益指标' ,
            legend: ['年化夏普比率（%）','年化卡玛比率（%）','年化索提诺比率（%）','詹森指数'],
            xArr  : xArr,
            series: [            {
                name:'年化夏普比率（%）',
                type:'line',

                data:annualizedSharpeArr
            },{
                name:'年化卡玛比率（%）',
                type:'line',

                data:annualKarmaRatioArr
            },{
                name:'年化索提诺比率（%）',
                type:'line',

                data:annualizedSotinoRatioArr
            }
                ,{
                    name:'詹森指数',
                    type:'line',

                    data:jansenIndexArr
                }
            ]
        };
    }
    function showEchart6(){
        //风险调整收益指标echart图
        var myChart6 = echarts.init(document.getElementById('eChart6'));
        var option=getOption(echart6());
        myChart6.setOption(option);
    }
    $(function () {
        $('#portfolioId').change(function () {
            if (!$('#startDate').val() || !$('#startDate').val()){
                $('#portfolioId').empty();
                Joyin.error(JoyinUtil.getMessage("请输入起始日和评价日"));
             }
        })
        // 绑定按钮事件
        $('#FinInvestmentEvaluate001_btn_search').click(function() {
            if (!$('#portfolioId').val()) {
                Joyin.error(JoyinUtil.getMessage("FinInvestmentEvaluate001_P"));
        }else if(!$('#startDate').val()){
                Joyin.error(JoyinUtil.getMessage("FinInvestmentEvaluate001_V"));
            }else if (!$('#endDate').val()) {
                Joyin.error(JoyinUtil.getMessage("FinInvestmentEvaluate001_M"));
            }else {
               table1.load(); table2.load(); table3.load(); table4.load();
               table5.load(); table6.load(); table7.load(); table8.load();
                Joyin.ajaxJson(basePath + 'invest/product/investmentevaluate/FinInvestmentEvaluate001/findPage3e',getPageParams(), function(result) {
                    showEchart3(result);
                });
                showEchart4();showEchart5();showEchart6();
            }
        });
     //起始日小于评价日，评价日小于平台日期
     //    $('#startDate').change(function () {
     //        dateChange();
     //    });
     //    $('#endDate').change(function () {
     //        dateChange();
     //    });
    });
}());