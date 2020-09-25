$(function () {
    var dataTable;
    
    var selectedPortfolioId;
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        var result = $.serializeToObject('#submitForm');
        if ($("#searchDate").val()) {
            result.searchDateStart = $("#searchDate").startDate();
            result.searchDateEnd = $("#searchDate").endDate();
        }
        if($("#portfolioId").val() == null || $("#portfolioId").val() == ''){
            result.portfolioId = $("#portfolioId").val();
        }
        else{
            result.portfolioId = $("#portfolioId").val().join(',');
        }
        if($("#profitType").val() == null || $("#profitType").val() == ''){
            result.profitType = $("#profitType").val();
        }
        else{
            result.profitType = $("#profitType").val().join(',');
        }
	    if($("#prodManager").val() == null || $("#prodManager").val() == ''){
            result.prodManager = null;
        }
        else{
            result.prodManager = $("#prodManager").val().join(',');
        }
	    
	    if($("#comparisonIndex").val()){
	    	result.comparisonIndex = $("#comparisonIndex").val();
        }
	 
        if($("#benchmark").val()){
        	result.benchmark = $("#benchmark").val();
        	result.benchmarkType = $("#benchmark").select2('data')[0].object;
        }
       
        
        if($("#coefficient").val()){
        	result.coefficient = $("#coefficient").val();
        }
        
        if($("#spreadRate").val()){
        	result.spreadRate = $("#spreadRate").val();
        }
        
        if($("#noRiskRate").val()){
        	result.noRiskRate = $("#noRiskRate").val();
        	result.noRiskRateType = $("#noRiskRate").select2('data')[0].object;
        }
        
        if($("#noRiskCoefficient").val()){
        	result.noRiskCoefficient = $("#noRiskCoefficient").val();
        }
        
        if($("#noRiskSpreadRate").val()){
        	result.noRiskSpreadRate = $("#noRiskSpreadRate").val();
        }
        
        return result;
    };
    
    var cols=new Array();
    var map = new Map();
    var comparisonIndex = $("#comparisonIndex").val();
    
    var xlineMap = new Map();
    xlineMap.set("01","单位净值");
    xlineMap.set("02","累计单位净值");
    xlineMap.set("03","年化收益率");
    xlineMap.set("04","七日年化收益率");

    var ylineMap = new Map();
    ylineMap.set("01","累计年化收益率");
    ylineMap.set("02","累计年化收益率");
    ylineMap.set("03","收益率");
    ylineMap.set("04","收益率");
    
    map.set("profitType","profitTypeName");
    getTableCols();
  //指定图表配置项和数据
    function showTable(data){
    	
    	var myChart = echarts.init(document.getElementById('main'));
    	var yrightlength = lineData.yrightaxisdata.length;
    	
    	var yleftlength = lineData.yleftaxisdata.length;
    	
        var option = {
        	    tooltip: {
        	        trigger: 'axis',
        	        axisPointer: {
        	            type: 'cross',
        	            crossStyle: {
        	                color: '#999'
        	            }
        	        }
        	    },
        	    legend: {
        	        data: [xlineMap.get(comparisonIndex), ylineMap.get(comparisonIndex)]
        	    },
        	    xAxis: [
        	        {
        	            type: 'category',
        	            data: lineData.xaxisdata,
        	            axisPointer: {
        	                type: 'shadow'
        	            }
        	        }
        	    ],
        	    yAxis: [
        	        {
        	            type: 'value',
        	            name: xlineMap.get(comparisonIndex),
        	            min: lineData.yleftaxisdata[0] - 0.2,
        	            max: lineData.yleftaxisdata[yleftlength-1] + 0.2,
        	            interval: (lineData.yleftaxisdata[yleftlength-1]  + 0.4 -lineData.yleftaxisdata[0])/5,
        	            axisLabel: {
        	                formatter: '{value}'
        	            }
        	        },
        	        {
        	            type: 'value',
        	            name: ylineMap.get(comparisonIndex),
        	            min: lineData.yrightaxisdata[0] -1,
        	            max: lineData.yrightaxisdata[yrightlength-1]+ 1,
        	            interval: (lineData.yrightaxisdata[yrightlength-1]-lineData.yrightaxisdata[0] + 2)/5,
        	            axisLabel: {
        	                formatter: '{value}'
        	            }
        	        }
        	    ],
        	    series: [
        	        {
        	            name: xlineMap.get(comparisonIndex),
        	            type: 'line',
        	            data: lineData.yleftdata
        	        },
        	        {
        	            name: ylineMap.get(comparisonIndex),
        	            type: 'line',
        	            yAxisIndex: 1,
        	            data: lineData.yrightdata
        	        }
        	    ]
        	};
        myChart.setOption(option, true);
    	
    }
    
    dataTable =$("#dataTable").joyintable({
         ajax: {
             url: 'findPage',
             type: 'POST',
             data: function() {
                return getPageParams();
             }
         },
//        checkbox:false,
//        tree:true,
///*        treeId: 'childInfo',
//        treeUpId: 'parentInfo',*/
//        treeShowName: 'portfolioId',
//        lazyload:true,
//        isMulti: true,
//        height:250,
//        searchOnInit: false,
        dblclick:function(table, data) {
        	selectedPortfolioId = data.portfolioId;
        	getLineData(data.portfolioId);
        },
        cols: [
               {field:'portfolioId', title: '组合代码',width:150, rowspan: 2,sortable: false}//组合名称
               ,{field:'portfolioName', title: '组合名称',width:90,rowspan: 2,sortable: false}//资产类型
               ,{field:'profitType', title: '收益类型', width:120,rowspan: 2,sortable: false}//资产二级类型
               ,{field:'finprodType2', visible: false,width:90, rowspan: 2}//资产类型
               ,{field:'type5',visible: false,width:100, rowspan: 2}//资产二级类型
              ,{field:'assetValue', title: '总资产', width:120,rowspan: 2,sortable: false}//资产代码
             ,{field:'assetNetvalue', title: '净资产',width:120, rowspan: 2,sortable: false}//资产名称
             ,{field:'unitValue',  title: '单位净值/万分收益',width: 200, rowspan: 2,sortable: false,type:'number'}//数量
             ,{field:'totUniitValue',title: '累计单位净值',  width: 200, rowspan: 2,sortable: false,type:'number'}//成本(元)
             ,{field:'yield7',title: '七日年化收益率',  width: 200, rowspan: 2,sortable: false,type:'number'}
             ,{field:'tdyYield',title: '当日年化收益率',  width: 200, rowspan: 2,sortable: false,type:'number'}
             ,{field:'assetRate',title: '总资产占产品比重',  width: 200, rowspan: 2,sortable: false,type:'number'}
             ,{field:'assetGrD',title: '总资产增长率（较上一日）',  width: 200, rowspan: 2,sortable: false,type:'number'}
             ,{field:'assetGrW',title: '总资产增长率（较本周初）',  width: 200, rowspan: 2,sortable: false,type:'number'}
             ,{field:'assetGrM',title: '总资产增长率（较本月初）',  width: 200, rowspan: 2,sortable: false,type:'number'}
             ,{field:'assetGrS',title: '总资产增长率（较本季初）',  width: 200, rowspan: 2,sortable: false,type:'number'}
             ,{field:'assetGrY',title: '总资产增长率（较本年初）',  width: 200, rowspan: 2,sortable: false,type:'number'}
             ,{field:'netassetGrD',title: '净资产增长率（较上一日）',  width: 200, rowspan: 2,sortable: false,type:'number'}
             ,{field:'netassetGrW',title: '净资产增长率（较本周初）',  width: 200, rowspan: 2,sortable: false,type:'number'}
             ,{field:'netassetGrM',title: '净资产增长率（较本月初）',  width: 200, rowspan: 2,sortable: false,type:'number'}
             ,{field:'netassetGrS',title: '净资产增长率（较本季初）',  width: 200, rowspan: 2,sortable: false,type:'number'}
             ,{field:'netassetGrY',title: '净资产增长率（较本年初）',  width: 200, rowspan: 2,sortable: false,type:'number'}
             ,{field:'netassetGrY',title: '累计单位净值增长率（较上一日）',  width: 200, rowspan: 2,sortable: false,type:'number'}
             ,{field:'netassetGrY',title: '累计单位净值增长率（较本周初）',  width: 200, rowspan: 2,sortable: false,type:'number'}
             ,{field:'netassetGrY',title: '累计单位净值增长率（较本月初）',  width: 200, rowspan: 2,sortable: false,type:'number'}
             ,{field:'netassetGrY',title: '累计单位净值增长率（较本季初）',  width: 200, rowspan: 2,sortable: false,type:'number'}
             ,{field:'netassetGrY',title: '累计单位净值增长率（较本年初）',  width: 200, rowspan: 2,sortable: false,type:'number'}
             ,{field:'netassetGrY',title: '波动率（区间）',  width: 200, rowspan: 2,sortable: false,type:'number'}
             ,{field:'netassetGrY',title: '波动率（本周）',  width: 200, rowspan: 2,sortable: false,type:'number'}
             ,{field:'netassetGrY',title: '波动率（本月）',  width: 200, rowspan: 2,sortable: false,type:'number'}
             ,{field:'absoIncome', title: '绝对回报',width: 120,  rowspan: 2,sortable: false,type:'number'}//市值(元)
             ,{field:'relaIncome',  title: '相对回报', width: 120,rowspan: 2,sortable: false,type:'number'}//应收利息(元)
             ,{field:'maxIncrease',  title: '最大涨幅', width: 120,rowspan: 2,sortable: false,type:'number'}//应收利息(元)
             ,{field:'maxDrW',  title: '最大回撤', width: 120,rowspan: 2,sortable: false,type:'number'}
               ,{field:'childInfo', visible: false,rowspan: 2}
               ,{field:'parentInfo',visible: false,rowspan: 2}
//               ,{title: '规模占比%', colspan: 4,align:'center',sortable: false}//市值占比%
           ]
        ,done: function() {
        	$(".laytable-cell-group").css({'text-align':'center'});
//        	showTable("JZKFPT0221");
    }
    });
    
    function updateTableCols(){
		 $("#dataTableDiv") .empty();
	     $("#dataTableDiv").append("<table id=\"dataTable\"></table>");
	     //重新加载表格
	     dataTable=$("#dataTable").joyintable({
	         ajax: {
	             url: 'findPage',
	             type: 'POST',
	             data: function() {
	                 return getPageParams();
	             }
	         },
	         checkbox:false,
	         tree:true,
/*	         treeId: 'childInfo',
	         treeUpId: 'parentInfo',*/
	         treeShowName: 'portfolioId',
	         lazyload:true,
	         isMulti: true,
	         height:250,
	         searchOnInit: false,
	         cols: cols
	         ,done: function() {
	        	 $(".laytable-cell-group").css({'text-align':'center'});
	         }
	     });
    }

    
    function getLineData(portfolioId){
    	var paramObj = getPageParams();//获取参数
    	//对需要计算的组合赋值
    	paramObj.portfolioId = portfolioId;
    	
    	Joyin.ajax(basePath + 'invest/portfolio/ptlperformanceanalysis/PtlPerformanceAnalysis001/findLineData', 
    			paramObj	
		,function(result) {
    		if(result != null){
    			showTable(result);
    		}
    		
	    });
    	
    }
    
    
    $("#comparisonIndex").bind("change", function () {

       comparisonIndex= $("#comparisonIndex").val();
       
       //TODO异步请求
       getLineData(selectedPortfolioId);
       
       
       
    });

    /**
     *加载动态表头
     */
    function getTableCols()
    {
    	
        var parm={};
        parm.type=1;
        Joyin.ajaxSync(
            basePath + "invest/portfolio/ptlperformanceanalysis/PtlPerformanceAnalysis001/getShowDataTableCols",parm,
            function(result) {
                if(result!="") {
                    var dataTableCols = JSON.parse(result);
                    
                    if (dataTableCols.length > 0) {
                    	cols[0] = new Array();
                    	for(var j = 0 ; j < dataTableCols[0].length ;j++){
                    		
                    		cols[0][j] = {
                                    title: dataTableCols[0][j].title,
                                    sortable: dataTableCols[0][j].sortable,
                                    align: 'center',
                                    colspan: dataTableCols[0][j].colspan
                                };
                    	}
                        	
                    	cols[1] = new Array();	
                        for (var i = 0; i < dataTableCols[1].length; i++) {
                        	var fieldMsg = map.get(dataTableCols[1][i].fieldMsg)==null?dataTableCols[1][i].fieldMsg:map.get(dataTableCols[1][i].fieldMsg);
                        	cols[1][i] = {
                                field: fieldMsg,
                                title: dataTableCols[1][i].title,
                                sortable: dataTableCols[1][i].sortable,
                                type: dataTableCols[1][i].type
                            };
                        }
                    }
                }

            }
        );
    }
    /**
     * 导出
     */
    var exportExcel = function() {
        var paramObj = getPageParams();//获取参数
        Joyin.exportExcel('export', paramObj);//导出
    };
  
    $(function () {
        
        $("#PtlPerformanceAnalysis001_btn_search").click(function () {
//        	if($("#searchDate").val() == null || $("#searchDate").val() == ''){
//        		Joyin.alert("请输入查询时间");
//        		return ;
//        	}
//        	
//        	if(Date.parse($("#searchDate").endDate())> Date.parse(platDate)){
//        		Joyin.alert("查询结束日应小于平台日期");
//        		return;
//        	}
//        	
//        	if($("#benchmark").val() == null || $("#benchmark").val() == ''){
//        		Joyin.alert("请选择业绩基准");
//        		return ;
//        	}
//        	
//        	if($("#coefficient").val() == null || $("#coefficient").val() == ''){
//        		Joyin.alert("请选择系数");
//        		return ;
//        	}
//        	
//        	if($("#spreadRate").val() == null || $("#spreadRate").val() == ''){
//        		Joyin.alert("请选择利差");
//        		return ;
//        	}
//        	
//        	if($("#noRiskRate").val() == null || $("#noRiskRate").val() == ''){
//        		Joyin.alert("请选择风险利率");
//        		return ;
//        	}
//        	
//        	if($("#noRiskCoefficient").val() == null || $("#noRiskCoefficient").val() == ''){
//        		Joyin.alert("请选择无风险利率系数");
//        		return ;
//        	}
//        	
//        	if($("#noRiskSpreadRate").val() == null || $("#noRiskSpreadRate").val() == ''){
//        		Joyin.alert("请选择无风险利差");
//        		return ;
//        	}
//            var paramObj = getPageParams();//获取参数
        	if (!dataTable.table) {
        		dataTable.load();
        	} else {
        		dataTable.reload();
        	}
        	
//        	var rowData = {};
//        	rowData.portfolioId = 'Test0430';
//        	rowData.portfolioName = '测试产品0430';
//        	rowData.profitTypeName = '净值型';
//        	rowData.assetValue = '100,000,000';
//        	rowData.assetNetvalue = '100,000,000';
//        	rowData.unitValue = '1.018';
//        	rowData.totUniitValue= '1.018';
//        	rowData.absoIncome = '100,000';
//        	rowData.relaIncome ='100,000';
//        	rowData.maxIncrease = '0.2';
//        	dataTable.addRow(rowData);
//        	dataTable.reload();
        });
         //绑定导出按钮
        $('#PtlPerformanceAnalysis001_btn_export').click(function() {
          //  Joyin.alert("功能暂未实现！");
            exportExcel();//导出
        });
        //指标设置
        $('#PtlPerformanceAnalysis001_btn_target').click(function() {
            Joyin.dialog("指标设置",
                JoyinUtil.getRealPath("invest/portfolio/ptlperformanceanalysis/PtlPerformanceAnalysis002/init"),
                "80%", "80%", {
                    pageModel: PAGE_MODEL_NEW
                },function () {
                  //设置指标成功后重新刷新列
                  updateTableCols();
                   // $("#displayType").trigger("change");
                });
        });
        
        //格式化表头
        $(".laytable-cell-group").css({'text-align':'center'});
    })
});
