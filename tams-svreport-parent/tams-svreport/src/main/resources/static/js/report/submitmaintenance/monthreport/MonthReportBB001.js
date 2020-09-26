$(function() {
	
	 // 查询按钮点击事件
    $('#MonthReportBB001_btn_search').click(function() {
        var exportdate = $("#reportmonth").val();
        if(exportdate ==null||exportdate =="" ||!exportdate || exportdate.length == 0){
            Joyin.error('请选择日期');
            return;
        }
        exportMethod("0");
    });
    
    // 导出中债Excel按钮点击事件
    $('#MonthReportBB001_btn_export').click(function() {
    	var exportdate = $("#reportmonth").val();
        if(exportdate ==null||exportdate =="" ||!exportdate || exportdate.length == 0){
            Joyin.error('请选择日期');
            return;
        }
        exportMethod("0");
    });
    
	//导出方法
    var exportMethod = function (flag){
    	var param={exportdate:$("#reportmonth").val()};
        Joyin.exportExcel("export",param);
    };
    
    $('#MonthReportBB001_btn_recal').click(function() {
		var exportdate = $("#reportmonth").val();
    	var param={exportdate:$("#reportmonth").val()};
		if(exportdate ==null||exportdate =="" ||!exportdate || exportdate.length == 0){
            Joyin.error('请选择日期');
            return;
        }
		exportdate = exportdate.substring(0,7);
//		var month = parseInt(exportdate.substring(5));
        Joyin.confirm("请确认是否要重新计算"+exportdate+"当月月度统计表？", function() {
            Joyin.ajax('recal', param);
        });
    });
    
    
     
    $("#searchDate").change(function() {
    	var exportdate = $("#searchDate").val();
    	var arr = exportdate.split("-");
        var vYear = arr[0];
        var vMon = arr[1]; 
        var month = parseInt(vMon);
        if(month==0){
	         vYear = exportdate.getFullYear()-1;
	         vMon = 12;
	    }else{
	         vMon = month -1;
	    }
	    var reportmonth = vYear +"-";
	    if(vMon>=10){
			reportmonth = reportmonth + vMon;
	    }else{
	    	reportmonth = reportmonth +"0"+ vMon;
	    }
    	$("#reportmonth").val(reportmonth);
    	
    	
    	$.ajax({  
            type : "POST",
            url : basePath+"submitmaintenance/monthreport/MonthReportBB001/getSubmitstatus",
            data : {  
                reportmonth:$('#reportmonth').val()
            },
            success : function(data) {
                if(data != null){
                	$("#submitstatus").val(data.submitstatus);
                }
            },
            error:function(){
                layer.alert('系统错误！');
            }
        });
        
    });
    
    
    $('#MonthReportBB001_btn_manual').click(function() {
    	var exportdate = $("#reportmonth").val();
		if(exportdate ==null||exportdate =="" ||!exportdate || exportdate.length == 0){
            Joyin.error('请选择日期');
            return;
        }
        
    	if("未处理"!=$('#submitstatus').val()){
    		Joyin.error('请选择未处理数据!');
            return;
    	}
		Joyin.confirm("是否对"+exportdate+"的报表进行手工处理?", function() {
    	$.ajax({  
            type : "POST",
            url : basePath+"submitmaintenance/monthreport/MonthReportBB001/manualSubmitstatus",
            data : {  
                reportmonth:$('#reportmonth').val()
            },
            success : function(data) {
                if(data != null&&data.reportexists != null){
                	if("N" == data.reportexists){
                		Joyin.error('系统还未生成该月份报表!');
                	}else if("Y" == data.reportexists && "E" ==data.submitstatus){
                		$("#submitstatus").val('已处理');
    					var param={info:"手工处理成功"};
				        Joyin.ajax('showInfo', param);
                	}
                }
            },
            error:function(){
                layer.alert('系统错误！');
            }
        });
	    });
    });
    
    $('#MonthReportBB001_btn_cancelManual').click(function() {
    	var exportdate = $("#reportmonth").val();
		if(exportdate ==null||exportdate =="" ||!exportdate || exportdate.length == 0){
            Joyin.error('请选择日期');
            return;
        }
        
    	if("已处理"!=$('#submitstatus').val()){
    		Joyin.error('请选择已处理数据!');
            return;
    	}

    	
		Joyin.confirm("是否对"+exportdate+"的报表进行取消手工处理?", function() {
    	$.ajax({  
            type : "POST",
            url : basePath+"submitmaintenance/monthreport/MonthReportBB001/cancelSubmitstatus",
            data : {  
                reportmonth:$('#reportmonth').val()
            },
            success : function(data) {
                if(data != null){
                	if("E" ==data.cancelstatus){
                		$("#submitstatus").val('未处理');
    					var param={info:"取消手工处理成功"};
				        Joyin.ajax('showInfo', param);
                	}
                }
            },
            error:function(){
                layer.alert('系统错误！');
            }
        });
	    });
    });
    
}());