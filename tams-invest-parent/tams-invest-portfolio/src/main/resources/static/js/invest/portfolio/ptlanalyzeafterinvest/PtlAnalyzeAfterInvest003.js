$(function () {
	
	/**
	 * 初始参数
	 */
	var categoryData = CATEGORYDATA;
	var values = VALUES;
	var warningLine = WARNINGLINE;
	var closeLine = CLOSELINE;
	
    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        var result = $.serializeToObject('#searchForm');
        if ($("#searchDate").val()) {
            result.searchDateStart = $("#searchDate").startDate();
            result.searchDateEnd = $("#searchDate").endDate();
        }
        var temp=result.sex;
        if($("#finprodId").val() == null || $("#finprodId").val() == ''){
            result.finprodId = $("#finprodId").val();
        }
        else{
            result.finprodId = $("#finprodId").val().join(',');
        }
        return result;
    };
    
    var dataTable=$("#dataTable").joyintable({
        checkbox:false,
        cols: [{field:'triggerDate', title: 'PtlAnalyzeAfterInvest003.triggerDate', sortable:false}//触发时间
            ,{field:'triggerPrice', title: 'PtlAnalyzeAfterInvest003.triggerPrice', sortable:false}//触发时股价
            ,{field:'closeLine', title: 'PtlAnalyzeAfterInvest001.closeLine', sortable:false}//预警线
            ,{field:'warningLine', title: 'PtlAnalyzeAfterInvest001.warningLine', sortable:false}//平仓线
            ,{field:'statement', title: 'PtlAnalyzeAfterInvest003.statement', sortable:false}//说明
        ]
    });
  
    
    /**
     * 初始化数据
     */
    var initData = function() {
    	CATEGORYDATA = CATEGORYDATA.split(',');
    	var temp = VALUES.split(',');
    	VALUES = [];
    	for(var i = 0; i< temp.length; i+=4){
			VALUES.push([parseFloat(temp[i]),
				parseFloat(temp[i+1]),
				parseFloat(temp[i+2]),
				parseFloat(temp[i+3])
			]);
    	}
    }
    
    /**
     * 加工表格数据
     */
    var getDatas = function() {
    	var dateRange = $('#dateRange').val().split(' - ');
    	var warningFlag = true;
    	var closeFlag = true;
    	var warningTime = 0; //触发预警次数
    	var closeTime = 0;	//触发平仓次数
    	var rowDatas = [];
    	for(var index = 0; index < CATEGORYDATA.length; index++){
    		if(dateRange[0] > CATEGORYDATA[index] || 
    				dateRange[1] < CATEGORYDATA[index]) 
    			continue;
    		//用 收盘价 和 预警线、平仓线比较
    		if(warningFlag && VALUES[index][1] > WARNINGLINE){
    			var rowData = {
    					triggerDate: CATEGORYDATA[index],
    	    			triggerPrice: VALUES[index][1],
    	    			closeLine: CLOSELINE,
    	    			warningLine: WARNINGLINE,
    	    			statement: JoyinUtil.getLabel('PtlAnalyzeAfterInvest003.warningTrigger')
    	    	};
    			rowDatas.push(rowData);
    	    	warningTime++
    	    	warningFlag = false;
    		}else if(VALUES[index][1] < WARNINGLINE){
    			warningFlag = true;
    		}
    		
    		if(closeFlag && VALUES[index][1] > CLOSELINE){
    			var rowData = {
    					triggerDate: CATEGORYDATA[index],
    	    			triggerPrice: VALUES[index][1],
    	    			closeLine: CLOSELINE,
    	    			warningLine: WARNINGLINE,
    	    			statement: JoyinUtil.getLabel('PtlAnalyzeAfterInvest003.closeTrigger')
    	    	};
    			rowDatas.push(rowData);
    	    	closeTime++
    	    	closeFlag = false;
    		}else if(VALUES[index][1] < CLOSELINE){
    			closeFlag = true;
    		}
    	}
    	$("#warningTime").text(warningTime);
    	$("#closeTime").text(closeTime);
    	return{
    		rowDatas: rowDatas,
    		warningTime: warningTime,
    		closeTime: closeTime
    	}
    };
    
    $(function () {
    	initData();
    	dataTable.load();
    	
        //查询按钮
        $("#PtlAnalyzeAfterInvest003_btn_search").click(function () {
        	dataTable.clear();
        	var datas = getDatas();
        	$("#warningTime").text(datas.warningTime);
        	$("#closeTime").text(datas.closeTime);
        	for(var i=0; i<datas.rowDatas.length; i++) 
        		dataTable.addRow(datas.rowDatas[i]);
        });
        
        //绑定导出按钮
        $('#PtlAnalyzeAfterInvest003_btn_export').click(function() {
        	var rows = getDatas().rowDatas;
        	var data = '';
        	for(var i = 0; i<rows.length; i++){
        		for(var key in rows[i]){
        			data += rows[i][key]+'+';
        		}
        		data =  data.substring(0, data.length - 1);  
        		data += ' ';
        	}
    		data =  data.substring(0, data.length - 1);
        	Joyin.exportExcel('export', {data: data});
        });
    })
});
