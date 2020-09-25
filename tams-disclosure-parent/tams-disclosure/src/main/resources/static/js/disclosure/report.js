
//产生并将HTML报表插入界面中
function genReport(report_info){
    var username = report_info.username || REPORT_USERNAME;
    var pwd = report_info.password || REPORT_PASSWORD;

    var inputs = {j_username:username, j_password:pwd};
    $.each(report_info.params, function(k, v){
        inputs[k] = v;
    });

    var url = report_info.url || REPORT_SERVER_URL;

    var $container = $("#" + report_info.container);

    $.ajax({
        url: url + '/rest_v2/reports' + REPORT_SERVER_PATH + '/' + report_info.uri + '.html',
        async: true,
        type: "GET",
        data: inputs,
        success: function(report){	
			//将返回的HTML报表插入到界面中
        	var startIndex = report.indexOf("/jasperserver");
        	var endIndex = report.indexOf(".png");
        	var subStr = report.substring(startIndex, endIndex);
        	var repStr = basePath+"/image/disclosure/zxlogo";
        	var newReport = report.replace(subStr,repStr);
            $container.html(newReport);
        },
        error: function(xhr, errorType, errorMsg){
			//在此处理错误
        }
    });
}

function fakeClick(obj) {
	   var ev = document.createEvent("MouseEvents");
	   ev.initMouseEvent("click", true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
	   obj.dispatchEvent(ev);
	}

function exportRaw(name, data) {
     var urlObject = window.URL || window.webkitURL || window;
     var export_blob = new Blob([data]);
     var save_link = document.createElementNS("http://www.w3.org/1999/xhtml", "a");
     save_link.href = urlObject.createObjectURL(export_blob);
     save_link.download = name;
     fakeClick(save_link);
   } 
	   
	   


function genReportHtml(report_info){
    var username = report_info.username || REPORT_USERNAME;
    var pwd = report_info.password || REPORT_PASSWORD;

    var inputs = {j_username:username, j_password:pwd};
    $.each(report_info.params, function(k, v){
        inputs[k] = v;
    });
    
    var url = report_info.url || REPORT_SERVER_URL;


    $.ajax({
        url: url + '/rest_v2/reports' + REPORT_SERVER_PATH + '/' + report_info.uri + '.html',
        async: true,
        type: "GET",
        data: inputs,
        success: function(report){
        	var startIndex = report.indexOf("/jasperserver");
        	var endIndex = report.indexOf(".png");
        	var subStr = report.substring(startIndex, endIndex);
        	var repStr = "http://220.180.239.103:7080"+basePath+"/image/disclosure/zxlogo";
        	var newReport = report.replace(subStr,repStr);
  //      	exportRaw(report_info.downloadName + "." + report_info.formatType,report)
        	exportRaw(report_info.downloadName + "." + report_info.formatType,newReport)
        },
        error: function(xhr, errorType, errorMsg){
			//在此处理错误
        }
    });
}

//导出各种形式的报表
function exportReport(report_info){
    var username = report_info.username || REPORT_USERNAME;
    var pwd = report_info.password || REPORT_PASSWORD;

    var url = report_info.url || REPORT_SERVER_URL;

    var paramStr = '?j_username=' + username + '&' + 'j_password=' + pwd;
    $.each(report_info.params, function(k, v){
        paramStr = paramStr + '&' + k + '=' + v;
    });
    var link = document.createElement('a');
    link.href = url + '/rest_v2/reports' + REPORT_SERVER_PATH + '/' + report_info.uri + '.' + report_info.formatType + paramStr;
    link.download = report_info.downloadName + "." + report_info.formatType;
    link.click();
}


//获取需要导出文件的路径
function exportReportPath(report_info){
    var username = report_info.username || REPORT_USERNAME;
    var pwd = report_info.password || REPORT_PASSWORD;

    var url = report_info.url || REPORT_SERVER_URL;

    var paramStr = '?j_username=' + username + '&' + 'j_password=' + pwd;
    $.each(report_info.params, function(k, v){
        paramStr = paramStr + '&' + k + '=' + v;
    });
    
    var path = url + '/rest_v2/reports' + REPORT_SERVER_PATH + '/' + report_info.uri + '.' + report_info.formatType 
    					+ paramStr;

    return path
}

//查询某个目录中的报表
function searchReports(folder_info){
	var username = folder_info.username || REPORT_USERNAME;
    var pwd = folder_info.password || REPORT_PASSWORD;
	var url = folder_info.url || REPORT_SERVER_URL;
    var inputs = {j_username:username, j_password:pwd, folderUri:folder_info.folderUri, type:'reportUnit'};
	var reports = null;
	$.ajax({
        url: url + '/rest_v2/resources',
        async: false,
        type: "GET",
        data: inputs,
		dataType:"json",
        success: function(info){
            reports = info.resourceLookup;
        },
        error: function(xhr, errorType, errorMsg){
			//在此处理错误
        }
    });
	return reports;
}

//查询每个报表的输入参数
function getReportInputs(report_info){
    var username = report_info.username || REPORT_USERNAME;
    var pwd = report_info.password || REPORT_PASSWORD;
    var inputs = {j_username:username, j_password:pwd};
    var url = report_info.url || REPORT_SERVER_URL;

	var controls = null;
    $.ajax({
        url: url + '/rest_v2/reports' + report_info.uri + '/inputControls',
        async: false,
        type: "GET",
        data: inputs,
		dataType:"json",
        success: function(info){
            controls = info.inputControl;
        },
        error: function(xhr, errorType, errorMsg){
			//在此处理错误
        }
    });
	return controls;
}
