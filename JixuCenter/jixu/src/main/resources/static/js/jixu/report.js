
function exportReport(inputs, alert) {
	$.ajax({
		 dataType:'native',
		 url: getURL("exportReport"),
		 async: true,
	     type: "POST",
	     data: inputs,
	     xhrFields: {
	    	 responseType:"blob"
	     },
	     beforeSend: function(){
	    	 $("#spinner").show(); 
	     },
	     success: function(data, status, xhr){
	    	if (typeof(alert) == "undefined"){
    			alert = "alertAll";
    		}
	    	if (typeof(data.issue) == "object"){
	    		alerts(alert, data.issue.level, data.issue.detail);
				$("#spinner").hide();
				return;
			}
	    	var filename = xhr.getResponseHeader("filename");
    		$("#spinner").hide();
    		if (window.navigator && window.navigator.msSaveOrOpenBlob){
    			window.navigator.msSaveOrOpenBlob(data, filename);
    		}
    		else {
    			var link = document.createElement('a');
        		link.href = window.URL.createObjectURL(data);
        		link.download = filename;
        		link.click();
    		}
	     },
	     error: function(xhr, errorType, errorMsg){
	    	 	if (typeof(alert) == "undefined"){
	    			alert = "alertAll";
	    		}
	    	 	var msg = "调用方法'" + address + "' 时发生错误;";
	    	 	alerts(alert, "fail", msg);
				if (alert != "alertAll"){
					$("#alertAll").empty();
					$("#alertAll").append(genAlert("fail", msg));
				}
				$("#spinner").hide();
				console.log(xhr.responseText);
		 },
		 complete: function(){
			 
		 }
	});
}

function getReport(address, inputs, func, target, alert, viewBtn) {
	$.ajax({
		 url: address,
		 async: true,
	     type: "POST",
	     data: inputs,
	     beforeSend: function(){
	    	 $("#spinner").show(); 
	     },
	     success: function(data){
	    	if (typeof(alert) == "undefined"){
    			alert = "alertAll";
    		}
	    	if (typeof(data.issue) == "object"){
	    		alerts(alert, data.issue.level, data.issue.detail);
				$("#spinner").hide();
				return;
			}
    		$("#spinner").hide();
    		func(target, inputs, data, alert, viewBtn);
	     },
	     error: function(xhr, errorType, errorMsg){
	    	 	if (typeof(alert) == "undefined"){
	    			alert = "alertAll";
	    		}
	    	 	var msg = "调用方法'" + address + "' 时发生错误;";
	    	 	alerts(alert, "fail", msg);
				if (alert != "alertAll"){
					$("#alertAll").empty();
					$("#alertAll").append(genAlert("fail", msg));
				}
				$("#spinner").hide();
				console.log(xhr.responseText);
		 },
		 complete: function(){
			 
		 }
	});
}

function displayReport(target, inputs, data, alert, viewBtn){
	var $target = $("#" + target);
	$target.empty();
	var $line = $("<div class='row' style='border-bottom: dotted; border-width: thin'></div>");
	$target.append($line);
	drawFilterForm(target, "report", "download_filter", {}, false);
	//var $table = $(data).find("table");
	//$table.appendTo($target);
	var $div = $("<div id='report_div'></div>");
	//data = data.replace(/842px/g, "1024px");
	$div.html(data);
	$div.appendTo($target);
	if (typeof(viewBtn) == "string"){
		$("#" + viewBtn).prop("disabled", false);
	}
	$("#report_download_btn").on("click", function(){
		inputs["type"] = $("#report_format_filter").val();
		exportReport(inputs, alert);
	});
}