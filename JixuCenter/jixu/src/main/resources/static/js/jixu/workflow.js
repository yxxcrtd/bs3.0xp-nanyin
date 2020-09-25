var task = {};
task["price"] = price;
task["smf"] = smf;
task["ex"] = fxRate;
task["shareholder"] = ta;
task["cash"] = cash;
task["ca"] = corpAction;
task["fx"] = fxTrade;
task["portTrade"] = tran;
task["auto"] = autojob
task["dailyClose"] = dailyClose;
task["daily"] = daily;
task["monthly"] = monthly;
task["nav"] = nav;
task["generalClose"] = generalClose;

var groups = {};
groups["market"] = MARKET;
groups["trade"] = TRADE; 
groups["job"] = JOB;

var taskIds = {};

taskIds["价格"] = "#market_head";
taskIds["汇率"] = "#market_head";
taskIds["证券主文件"] = "#market_head";
taskIds["股东交易"] = "#trade_head";
taskIds["现金交易"] = "#trade_head";
taskIds["公司行为交易"] = "#trade_head";
taskIds["换汇交易"] = "#trade_head";
taskIds["投资组合交易"] = "#trade_head";
taskIds["到期自动作业"] = "#job_head";
taskIds["交易估值关账"] = "#job_head";
taskIds["每日会计作业"] = "#job_head";
taskIds["每月会计作业"] = "#job_head";
taskIds["基金净值计算"] = "#job_head";
taskIds["会计总账关账"] = "#job_head";

var T_MARKET = ["price", "smf", "ex"];
var T_TRADE = ["shareholder", "cash", "ca", "fx", "portTrade"];
var T_JOB = ["auto", "dailyClose", "daily", "monthly", "nav", "generalClose"];
var R_T_JOB = ["generalClose", "nav", "monthly", "daily", "dailyClose", "auto"];

var PORTS = [];
var NAMES = [];
var PORTS_MAP = [];
var NAV_DATE = "";
var TABLE = getForm("workflow_table");;
function genWorkflow(target_id){
	invoke(getURL("currentWFStatus"), {ports:JSON.stringify(PORTS), date:NAV_DATE}, function(data){
		//Use "data" in real environment like below
		drawWorkflow(target_id, data, TABLE);
		statusHandler(target_id);
	}, "alertAll");
}

function statusHandler(target_id){
	$("[cat='opr']").off("click");
	$("[cat='opr']").on("click", function(){
		var group = $(this).attr("group");
		var dataFor = $(this).attr("dataFor");
		var opr = $(this).attr("opr");
		var status = 4;
		autoRefresh(false);
		
		if (opr == COMPLETE || opr == NOTSTART){
			status = WORKON;
		}
		else if (opr == WORKON){
			if (parseInt($("#" + dataFor + "_fail").text()) > 0){
				alert("失败数量不为0，无法完成此操作！");
			}
			else {
				status = COMPLETE;
			}	
		}
		if (status < 4){
			if (group == "job" && status == WORKON && opr == COMPLETE){
				redoJob("content_detail", dataFor, "content_title", "alertAll");
			}
			else if (group == "job" && status == COMPLETE && parseInt($("#" + dataFor + "_success").text()) == 0){
				if (confirm("没有执行过任何操作，是否确认完成?")){
					invoke(getURL("updateWFStatus"), {category:task[dataFor], ports:JSON.stringify(PORTS), date: NAV_DATE, status:status}, function(d){
						genWorkflow("wf");
						autoRefresh(true);
					}, "alertAll");
				}
			}
			else{
				invoke(getURL("updateWFStatus"), {category:task[dataFor], ports:JSON.stringify(PORTS), date: NAV_DATE, status:status}, function(d){
					genWorkflow("wf");
					autoRefresh(true);
				}, "alertAll");
			}
		}
	});
}

function redoJob(target_id, cat, title_id, alert_id){
	invoke(getURL("redoJob"), {id:task[cat], date:NAV_DATE, ports:JSON.stringify(PORTS)}, function(data){
		$("#" + target_id).empty();
		$("#" + title_id).text("工作流日志详情-重开 " + translate[cat]);
		drawTable(target_id, markData(data));
		$("#" + target_id).append("<font color='red'>*错误详情请在日志中查询</font>");
		invoke(getURL("updateWFStatus"), {category:task[cat], ports:JSON.stringify(PORTS), date: NAV_DATE, status:WORKON}, function(d){
			$("#wf").hide();
			$("#content_modal").modal();
		}, alert_id);
	}, alert_id);
}

function logHandler(target_id, alert_id, title_id){
	$(document).on("click", "[cat='log']", function(){
		autoRefresh(false);
		var cat = $(this).attr("dataFor");
		var group = $(this).attr("group");
		$("#" + target_id).empty();
		$("#" + title_id).text("工作流日志详情-" + translate[cat]);
		//invoke(getURL("operateLog"), {category:task[cat], date:NAV_DATE, ports:JSON.stringify(PORTS)}, function(data){
		//	drawTable(target_id, data, "table-border");
		//}, alert_id);
		drawPagTable(target_id, "operateLog", {category:task[cat], date:NAV_DATE, ports:JSON.stringify(PORTS)}, "", "", alert_id);
		$("#wf").hide();
		$("#content_modal").modal();	
	});
}

function failHandler(target_id, alert_id, title_id){
	$(document).on("click", "a[cat='fail']", function(){
		autoRefresh(false);
		var cat = $(this).attr("dataFor");
		var group = $(this).attr("group");
		$("#" + target_id).empty();
		$("#" + title_id).text("工作流失败处理详情-" + translate[cat]);
		var r = 0;
		
		var t = $.extend(true, {}, checkTable);
		t.source.address = "errorReport";
		t.source.inputs = {category:task[cat], group:groups[group], date:NAV_DATE, ports:JSON.stringify(PORTS)};
		t.event.add.on = false;
		t.event.del.on = false;
		t.event.detail.on = false;
		var status = $("#" + cat + "_status").attr("status");
		
		if (group == "market" || group == "trade"){
			if (status == COMPLETE){
				t.source.func = function(data){
					$.each(data.data, function(i, error){
						var $detail = $("#error_" + error[0] + "_check");
						var ignore = error[8];
						if (ignore == "是"){
							$detail.attr("ignore", true);
							$detail.attr("class", "btn btn-danger");
							$detail.text("撤销");
							$("#" + error[0]).addClass("success");
						}
						else{
							$detail.attr("ignore", false);
							$detail.attr("class", "btn btn-success");
							$detail.text("忽略");
							$("#" + error[0]).addClass("danger");
						}
					});
				}
				genTableWithCheck(target_id, "error", t.source, t.event, [], alert_id, false);
			}
			else{
				t.source.func = function(data){
					var $ignoreDiv = $("<div class='col-md-1 col-md-offset-11' id='ignoreAll'></div>");
					var $ignoreBtn = genButton("ignoreAll_btn", "success", "全部忽略");
					$ignoreDiv.append($ignoreBtn);
					$ignoreDiv.append("<br><br>");
					var $table = $("#" + target_id).find("table");
					$ignoreDiv.insertBefore($table);
					
					$ignoreBtn.off("click");
					$ignoreBtn.on("click", function(){
						if (confirm("是否全部忽略？")){
							$.each($("[data-for='error_check']"), function(i, error){
								if ($(error).attr("ignore") === "false"){
									var errorID = $(error).attr("id").split("_")[1];
									var ignore = $(this).attr("ignore");
									var changeTo = !(ignore === "true");
									invoke(getURL("errorIgnore"), {id:errorID, ignore:changeTo}, function(data){
										$(error).attr("ignore", true);
										$(error).attr("class", "btn btn-danger");
										$(error).text("撤销");
										$("#" + errorID).attr("class", "success");
										$("#" + errorID).find("td").eq(8).text("是");
									}, alert_id);
								}
							});
						};
					});
					
					$.each(data.data, function(i, error){
						var $detail = $("#error_" + error[0] + "_check");
						var ignore = error[8];
						if (ignore == "是"){
							$detail.attr("ignore", true);
							$detail.attr("class", "btn btn-danger");
							$detail.text("撤销");
							$("#" + error[0]).addClass("success");
						}
						else{
							$detail.attr("ignore", false);
							$detail.attr("class", "btn btn-success");
							$detail.text("忽略");
							$("#" + error[0]).addClass("danger");
						}
					});
					$("[data-for='error_check']").off("click");
					$("[data-for='error_check']").on("click", function(){
						var errorID = $(this).attr("id").split("_")[1];
						var ignore = $(this).attr("ignore");
						var changeTo = !(ignore === "true");
						if (confirm("是否确认改变状态？")){
							invoke(getURL("errorIgnore"), {id:errorID, ignore:changeTo}, function(data){
								var $detail =  $("#error_" + errorID + "_check");
								if (changeTo){
									$detail.attr("ignore", true);
									$detail.attr("class", "btn btn-danger");
									$detail.text("撤销");
									$("#" + errorID).attr("class", "success");
									$("#" + errorID).find("td").eq(8).text("是");
								}
								else{
									$detail.attr("ignore", false);
									$detail.attr("class", "btn btn-success");
									$detail.text("忽略");
									$("#" + errorID).attr("class", "danger");
									$("#" + errorID).find("td").eq(8).text("否");
								}
							}, alert_id);
						}
					});
				};
				genTableWithCheck(target_id, "error", t.source, t.event, ["check"], alert_id, false);
				
			}
		}
		else if (group == "job"){
			t.source.func = function(data){
				$("[data-for='error_check']").text("重做");
				$("[data-for='error_check']").on("click", function(){
					if(confirm("是否要执行该作业?")){
						var $tr = $(this).parent().parent();
						var tds = $tr.find("td");
						var jobID = tds.eq(0).text();
						var portID = tds.eq(2).text();
						invoke(getURL("runSingleJob"), {jobID:jobID, date:NAV_DATE, portID:portID}, function(data){
							$("#" + alert_id).append(genAlert("success", data.data[0] + "执行" + data.data[2]));
							$tr.remove();
						}, alert_id);
					}
				});
			};
			genTableWithCheck(target_id, "error", t.source, t.event, ["check"], alert_id, false);
		}
		
		$("#wf").hide();
		$("#content_modal").modal();	
	});
}

function successHandler(target_id, alert_id, title_id){
	$(document).on("click", "a[cat='success']", function(){
		autoRefresh(false);
		var cat = $(this).attr("dataFor");
		var group = $(this).attr("group");
		$("#" + target_id).empty();
		$("#" + title_id).text("工作流成功处理详情-" + translate[cat]);
		var r = 0;
		
		if (group == "market"){
			r = marketSuccess(target_id, cat, alert_id);
		}
		else if (group == "trade"){
			r = tradeSuccess(target_id, cat, alert_id);
		}
		else if (group = "job"){
			r = jobSuccess(target_id, cat, alert_id);
		}
		if (r == 0){
			$("#wf").hide();
			$("#ports").hide();
			$("#success").show();
			
		}
	});
	$(document).on("click", "a[data-for='success_return']", function(){
		genWorkflow("wf");
		autoRefresh(true);
		$("#wf").show();
		$("#ports").show();
		$("#success").hide();
		var task = $("#success_title").text().split("-")[1];
		window.location.hash = "#";
		window.location.hash = taskIds[task] || "#";
		return false;
	});
}

function marketSuccess(target_id, cat, alert_id){
	var t = $.extend(true, {}, checkTable);

	var resources = fetch(getURL("resourcesInPorts"), {ports:JSON.stringify(PORTS), type:task[cat]});
	var resources_list = Object.keys(resources);
	var r_options = $.extend(true, {}, resources);
	r_options[ALL] = "全部";
	
	if (cat == "price"){
		var inputs = {};
		inputs["2"] = {config:ALL, type:ALL};
	}
	else if(cat == "smf"){
		var inputs = {};
		inputs["3"] = {config:ALL, type:ALL};
	}
	//处理Filter Form
	var tp = cat;
	cat = cat + "List";
	drawFilterForm(target_id, cat, "wf_" + cat + "_filter", inputs, true);
	$("#" + cat + "_return_btn").attr("data-for", "success_return");
	$("#" + cat + "_date_filter").val(NAV_DATE).prop("disabled", true);
	updateSelectOptions($("#" + cat + "_resource_filter"), r_options);
	$("#" + cat + "_resource_filter").val(ALL);
	if(tp == "price" || tp == "ex"){
		wfMarketDetail(tp + "List_list", tp, resources_list, "alertAll");
	}
	$("a.btn.btn-primary").on("click", function(){
	 	var action = $(this).attr("id").split("_")[1];
	 	if (action == "search"){
	 		wfMarketDetail(tp + "List_list", tp, resources_list, "alertAll");
	 	}
	});
	return 0;
}

function tradeSuccess(target_id, cat, alert_id){
	var t = $.extend(true, {}, checkTable);

	var resources = fetch(getURL("resourcesInPorts"), {ports:JSON.stringify(PORTS), type:task[cat]});
	var resources_list = Object.keys(resources);
	var r_options = $.extend(true, {}, resources);
	r_options[ALL] = "全部";
	
	var inputs = {};
	if (cat == "portTrade"){
		inputs[4] = {config:ALL, type:ALL};
	}
	else if (cat == "cash"){
		inputs[2] = {type:CASH, all:false}; 
	}
	else if (cat == "shareholder"){
		inputs[2] = {type:CAPITAL, all:false};
	}
	
	var tp = cat;
	cat = cat + "List";
	drawFilterForm(target_id, cat, "wf_" +  cat + "_filter", inputs, true);
	//处理filter form
	$("#" + cat + "_return_btn").attr("data-for", "success_return");
	$("#" + cat + "_date_filter").val(NAV_DATE).prop("disabled", true);
	updateSelectOptions($("#" + cat + "_resource_filter"), r_options);
	$("#" + cat + "_resource_filter").val(ALL);
	updateSelectOptions($("#" + cat + "_port_filter"), fetch(getURL("portsMap"), {ports:JSON.stringify(PORTS)}));
	
	$("a.btn.btn-primary").on("click", function(){
		var action = "";
		if (typeof($(this).attr("id")) != "undefined"){
			action = $(this).attr("id").split("_")[1];
			if (action == "search"){
				var config = [];
				var con = $("#" + cat + "_resource_filter").val();
				if (con == ALL){
					config = resources_list;
				}
				else {
					config = [con];
				}
				var inputs = {config:JSON.stringify(config),
				  		      smf:$("#" + cat + "_secid_filter").val()||"",
				  		      type:$("#" + cat + "_type_filter").val(),
				  		      portfolio:$("#" + cat + "_port_filter").val()||"",
				  		      start:NAV_DATE,
				  		      end:NAV_DATE,
				  		      displayDelete:$("#"+ cat + "_delete_filter").is(":checked") || false};
				var t = $.extend(true, {}, checkTable);
				t.source.address = tp + "Trades";
				t.source.inputs = inputs;
				t.event.add.on = false;
				t.event.detail.on = false;
				if (tp == "ca"){
					t.event.detail.source.address = "caDetail";
					t.event.detail.source.func = caDetail;
					t.event.update.on = false;
					t.event.update.source.func = caUpdate;
				}
				else {
					t.event.detail.source.address = "tradeDetail";
					t.event.detail.source.func = tradeDetail;
					t.event.detail.source.inputs["type"] = $("#" + cat + "_type_filter").val();
					t.event.update.on = false;
					t.event.update.source.func = tradeUpdate;
				}
				t.event.detail.form.target_id = "trade_detail";
				t.event.detail.form.alert_id = "trade_alert";
				$("#" + tp + "List_list").empty();
				genTableWithCheck(tp + "List_list", tp, t.source, t.event, ["detail"], "alertAll", false);
			}
		}
	});
	
	return 0;
}

function jobSuccess(target_id, cat, alert_id){
	var cats = {};
	cats[SEC_POSITION] = "position";
	cats[CASH_POSITION] = "cash";
	cats[RD] = "rd";
	cats[RCPY] = "rcpy";
	
	drawFilterForm(target_id, cat, "wf_" +  cat + "_filter", {}, true);
	//处理filter form
	$("#" + cat + "_return_btn").attr("data-for", "success_return");
	$("#" + cat + "_date_filter").val(NAV_DATE).prop("disabled", true);
	updateSelectOptions($("#" + cat + "_port_filter"), fetch(getURL("portsMap"), {ports:JSON.stringify(PORTS)}));
	var tps = {};
	if (cat == "auto"){
		tps[RD] = translate["rd"];
		tps[RCPY] = translate["rcpy"];
		updateSelectOptions($("#" + cat + "_category_filter"), tps);
	}
	else if (cat == "dailyClose"){
		var tps = {};
		tps[SEC_POSITION] = translate["position"];
		tps[CASH_POSITION] = translate["cashposition"];
		updateSelectOptions($("#" + cat + "_category_filter"), tps);
	}

	$("a.btn.btn-primary").on("click", function(){
		var action = "";
		if (typeof($(this).attr("id")) != "undefined"){
			action = $(this).attr("id").split("_")[1];
			dataFor = $(this).attr("id").split("_")[0];
			var inputs = {portfolio: $("#" + dataFor + "_port_filter").val(), 
	  	  		          start: $("#" + dataFor + "_date_filter").val(), 
	  	  		          end: $("#" + dataFor + "_date_filter").val()};
			
			if (action == "search"){
				var dom_id = dataFor + "_list";
				var category = $("#" + dataFor + "_category_filter").val();
				if (dataFor == "auto" || dataFor == "dailyClose"){
					var postFunc = function(){
						$("a[data-for='rd_rcpy']").on("click", function(){
							var rcpy = $(this).attr("id").split("_")[2];
							$("#rd_title").text("应收应付 - " + rcpy);
							invoke(getURL("getRCPY"), {rcpyNum:rcpy}, function(data){
								$("#rd_detail").empty();
                                drawTable("rd_detail", data);
							});   
						});
	                	$("a[data-for='rd_trade']").on("click", function(){
							var trade = $(this).attr("id").split("_")[2];
							$("#rd_title").text("交易 - " + trade);
							invoke(getURL("getTrade"), {tradeNum:trade}, function(data){
								$("#rd_detail").empty();
                                drawTable("rd_detail", data);
							});
						});
						$("a[data-for='rcpy_trade']").on("click", function(){
							var trade = $(this).attr("id").split("_")[2];
							$("#rcpy_title").text("交易 - " + trade);
							invoke(getURL("getTrade"), {tradeNum:trade}, function(data){
								$("#rcpy_detail").empty();
                                drawTable("rcpy_detail", data);
							});
						});
					};
					var preFunc;
					var func = cats[category];
					if (func == "rd"){
						preFunc = function(data){
							for (var i = 0; i < data.data.length; i++){
	                			data.data[i][11] = "<a href='#' id='rd_rcpy_" + data.data[i][11] + "' role='button' data-for='" + func + "_rcpy' data-toggle='modal' data-target='#" + func + "_modal'>#" + data.data[i][11] +"</a>";
	                			data.data[i][12] = "<a href='#' id='rd_trade_" + data.data[i][12] + "' role='button' data-for='" + func + "_trade' data-toggle='modal' data-target='#" + func + "_modal'>#" + data.data[i][12] +"</a>"
	                		}
							return data;
						}
					}
					else if (func == "rcpy"){
						preFunc = function(data){
							for (var i = 0; i < data.data.length; i++){
	                			data.data[i][11] = "<a href='#' id='rcpy_trade_" + data.data[i][11] + "' role='button' data-for='" + func + "_trade' data-toggle='modal' data-target='#" + func + "_modal'>#" + data.data[i][11] +"</a>";
	                		}
							return data;
						}
						func = "rcpyCreated";
					}
					drawPagTable(dom_id, func, inputs, preFunc, postFunc);
				}
				else if (dataFor == "nav"){
//					$("#" + dom_id).empty();
//					var inputs = {};
//					inputs["id"] = "ValuationReport";
//					inputs["port"] = $("#" + dataFor + "_port_filter").val();
//					inputs["start"] = NAV_DATE;
//					inputs["end"] = NAV_DATE;					
//					var viewable = fetch(getURL("isViewable"), inputs, "alertAll");
//					if (viewable){
//						getReport(getURL("viewReport"), inputs, displayReport, dom_id, "alertAll");
//					}
//					getReport(getURL("genReport"), inputs, displayReport, dom_id, "alertAll", "report_view_btn");
					drawPagTable(dom_id, "nav", inputs, preFunc, "", "alertAll");
				}
				else if (dataFor == "generalClose"){
					drawPagTable(dom_id, "nav", inputs, preFunc, "", "alertAll");
				}
				else if (dataFor == "daily" || dataFor == "monthly"){
					if (dataFor == "daily"){
						inputs["voucher"] = $("#" + dataFor + "_category_filter").val();
						inputs["secid"] = $("#" + dataFor + "_secid_filter").val();
						inputs["currency"] = $("#" + dataFor + "_currency_filter").val();
						inputs["acctNum"] = $("#" + dataFor + "_acctNum_filter").val();
					}
					else {
						inputs["voucher"] = 18;
						inputs["secid"] = ALL;
						inputs["currency"] = ALL;
					}
					
					var t = $.extend(true, {}, checkTable);
		            t.source.address = "voucherList";
		    		t.source.inputs = inputs;
		    		t.source.func = function(data){
		    			$.each($("tr[data-for='voucher']"), function(index, tr){
		    				var $td = $(tr).find("td").eq(9);
		    				var content = $td.text();
		    				if (content != "" && content !="-"){
		    					$td.empty();
		        				var $a = $("<a href='#'>#" + content+ "</a>");
		        				$a.attr("id", "voucher_trade_" + content);
		        				$a.attr("data-for", "voucher_trade");
		        				$td.append($a);
		    				}
		    			});
		    			$("a[data-for='voucher_trade']").on("click", function(){
							var trade = $(this).attr("id").split("_")[2];
							$("#voucher_title").text("交易 - " + trade);
							invoke(getURL("getTrade"), {tradeNum:trade}, function(tdata){
								$("#voucher_detail").empty();
		                        drawTable("voucher_detail", tdata);
		                        $("#voucher_modal").modal();
							});
						});
		    		}
		    		t.event.add.on = false;
		    		t.event.del.on = false;
		    		t.event.detail.on = false;
		    		t.event.detail.source.address = "voucherDetail";
		    		t.event.detail.source.func = function(id, dataFor, data){
		    			$("#voucher_detail").empty();
		    			$("#voucher_title").text("凭证详情 -" + id);
		                drawTable("voucher_detail", data);
		                $("#voucher_modal").modal();
		    		};
		    		$("#" + dom_id).empty();
		    		genTableWithCheck(dom_id, "voucher", t.source, t.event, ["detail"], "alertAll", false);
				}
			}
		}
	});
	return 0;
}

function taskHandler(target_id, alert_id, title_id){
	$(document).on("click", "button[cat='task']", function(){
	//$("[cat='task']").on("click", function(){
		var cat = $(this).attr("dataFor");
		var group = $(this).attr("group");
		var r = 0;
		$("#" + target_id).empty();
		$("#" + title_id).text("工作流任务-" + translate[cat]);
		if (group == "market"){
			r = marketTask(target_id, cat, alert_id);
		}
		else if (group == "trade"){
			r = tradeTask(target_id, cat, alert_id);
		}
		else if (group = "job"){
			r = jobTask(target_id, cat, alert_id);
		}
		if (r == 0){
			$("#wf").hide();
			$("#content_modal").modal();
		}
	});
}

function jobTask(target_id, cat, alert_id){
	invoke(getURL("runJob"), {id:task[cat], date:NAV_DATE, ports:JSON.stringify(PORTS)}, function(data){
		$("#" + target_id).empty();
		drawTable(target_id, markData(data));
		$("#" + target_id).append("<font color='red'>*错误详情请在日志中查询</font>")
	}, alert_id);
	return 0;
}

function tradeTask(target_id, cat, alert_id){
	var inputs = {};
	
	inputs[2] = {ports:JSON.stringify(PORTS), category:task[cat], type:ALL};
	if (cat == "portTrade"){
		inputs[3] = {config:ALL, type:ALL};
	}
	else if (cat == "cash"){
		inputs[1] = {type:CASH, all:false}; 
	}
	else if (cat == "shareholder"){
		inputs[1] = {type:CAPITAL, all:false};
	}
	
	else if (cat == "ca"){
		inputs[0] = {"config":ALL,"type":COMMON_STOCK}
	}
	
	drawFilterForm(target_id, cat, "wf_" + cat + "_filter", inputs, true);
	updateSelectOptions($("#" + cat + "_port_filter"), PORTS_MAP);
	$("#" + target_id).append("<div id='result'></div>");
	
	$("#" + cat +"_import_filter").fileupload({
		url:getURL("uploadImportData"),
		dataType:'json',
		formData:{id:$("#" + cat + "_resource_filter").val(), 
				  port: $("#" + cat + "_port_filter").val(),
				  workflowStep: task[cat],
				  date:NAV_DATE},
		done: function (e, data){
			$("#result").empty();
			$("#spinner").hide();
			drawTable("result", markData(data.result));
			$("#result").append("<font color='red'>*错误详情请在日志中查询</font>");
		},
		progressall: function(e, data){
			$("#spinner").show();
			$("#result").empty();
			$("#result").append("<h4>数据导入中，请稍后......</h4>");
		}
	});
	
	$("#" + cat +"_import_btn").off("click");
	$("#" + cat +"_import_btn").on("click", function(){
		if (confirm("是否需要导入?")){
			$("#result").empty();
			$("#result").append("<h4>数据导入中，请稍后......</h4>");
			invoke(getURL("importData"), {id:$("#" + cat + "_resource_filter").val(), 
										  port: $("#" + cat + "_port_filter").val(),
										  workflowStep: task[cat],
										  date:NAV_DATE}, function(data){
				$("#result").empty();
				drawTable("result", markData(data));
				$("#result").append("<font color='red'>*错误详情请在日志中查询</font>")
			}, alert_id);
		}
		else {
			$("#result").empty();
		}
	});
	
	$("#" + cat + "_create_btn").off("click");
	$("#" + cat + "_create_btn").on("click", function(){
		$("#result").empty();
		var info = {};
		info["tp"] = cat;
 		info["port"] = $("#" + cat + "_port_filter").val() || PORTS[0];
 		info["ports"] = [info.port];
 		info["date"] = NAV_DATE;
 		info["ports_str"] = JSON.stringify(info.ports);
 		info["client"] = fetch(getURL("clientByPort"), {portID:info.port}, "content_alert");
 		
 		if (cat == "ca"){
 			var exist = fetch(getURL("verifySecurity"), {assetType:COMMON_STOCK, secid:$("#ca_secid_filter").val()||""},"alertAll");
			if (exist){
				info["tradeType"] = $("#ca_type_filter").val();
				info["secid"] = $("#ca_secid_filter").val();
				info['assetType'] = COMMON_STOCK;
				caCreate("trade_detail", "trade_alert", info);
				$("#trade_modal").modal();
			}
			else{
				$("#alertAll").empty();
				$("#alertAll").append(genAlert("fail", "您输入的证券不存在，请输入正确的证券名称！"));
			}
 		}
 		else{
 			if (cat == "portTrade" || cat == "fx"){
 	 			info["assetType"] = $("#" + cat + "_type_filter").val();
 	 		}
 	 		else if (cat == "cash"){
 	 			info["assetType"] = CASH;
 	 		}
 	 		else if (cat == "shareholder"){
 	 			info["assetType"] = CAPITAL;
 	 		}
 	 		var assetForm = getData(fetch(getURL("assetForm"), {assetType:info["assetType"]}));
 	 		info["expense"] = assetForm.expense;
 	 		info["collateral"] = assetForm.collateral;
 	 		if (assetForm.PreForm){
 	 			$("#preTrade_detail").empty();
 	 			drawTradeFilterForm("preTrade_detail", "preTrade", assetForm.PreForm, info, false);
 	 			$("#preTrade_modal").modal();
 	 			$("#preTrade_proceed_btn").on("click", function(){
 	 				if ($("#preTrade_filter").valid()){
 	 					var exist = false;
	 					if ($("#preTrade_secid_filter").val()){
	 						exist = fetch(getURL("verifySecurity"), {assetType:info.assetType, secid:$("#preTrade_secid_filter").val()},"preTrade_alert");
	 					}
	 					else {
	 						exist = true;
	 					}
 	 					if (exist){
 	 						$.each(assetForm.info, function(k, v){
 		 						info[k] = $("#" + v).val();
 		 					});
 		 					tradeCreate("trade_detail", "trade_alert", info);
 		 					$("#preTrade_modal").modal("hide");
 		 					$("#trade_modal").modal();
 	 					}
 	 					else{
 	 						$("#preTrade_alert").empty();
 	 						$("#preTrade_alert").append(genAlert("fail", "您输入的证券不存在，请输入正确的证券名称！"));
 	 					}
 	 				}
 	 				return false;
 	 			});
 	 		}
 	 		else {
 	 			$.each(assetForm.info, function(k, v){
 						info[k] = $("#" + v).val();
 					});
 	 			tradeCreate("trade_detail", "trade_alert", info);
 	 			$("#trade_modal").modal();
 	 		}
 		}
 		
	});
	return 0;
}

function marketTask(target_id, cat, alert_id){
	var inputs = {};
	
	inputs["0"] = {ports:JSON.stringify(PORTS), category:task[cat], type:ALL};
	drawFilterForm(target_id, cat, "wf_" + cat + "_filter", inputs, true);
	$("#" + target_id).append("<div id='result'></div>");
	
	$("#" + cat +"_import_filter").fileupload({
		url:getURL("uploadImportData"),
		dataType:'json',
		formData:{id:$("#" + cat + "_resource_filter").val(), 
				  port: -1,
				  workflowStep: task[cat],
				  date:NAV_DATE},
		done: function (e, data){
			$("#result").empty();
			$("#spinner").hide();
			drawTable("result", markData(data.result));
			$("#result").append("<font color='red'>*错误详情请在日志中查询</font>");
		},
		progressall: function(e, data){
			$("#spinner").show();
			$("#result").empty();
			$("#result").append("<h4>数据导入中，请稍后......</h4>");
		}
	});
	
	$("#" + cat +"_import_btn").off("click");
	$("#" + cat +"_import_btn").on("click", function(){
		$("#result").empty();
		if (confirm("是否需要导入?")){
			$("#result").append("<h4>数据导入中，请稍后......</h4>");
			invoke(getURL("importData"), {id:$("#" + cat + "_resource_filter").val(), 
										  port:-1, 
										  workflowStep:task[cat],
										  date:NAV_DATE}, function(data){
				$("#result").empty();
				drawTable("result", markData(data));
				$("#result").append("<font color='red'>*错误详情请在日志中查询</font>")
			}, alert_id);
		}
		else{
			$("#result").empty();
		}
	});
	
	$("#" + cat + "_create_btn").off("click");
	$("#" + cat + "_create_btn").on("click", function(){
		$("#result").empty();
		marketCreate("result", cat, alert_id, NAV_DATE, "content");
	});
	
	return 0;
}

function completeAllHandler(target_id, alert_id, title_id){
	$(document).on("click", "button[cat='completeAll']", function(){
		var group = $(this).attr("group");
		autoRefresh(false);
		$("#spinner").show();
		var r = 0;
		if (group == "market"){
			r = marketCompleteAll();
		}
		else if (group == "trade"){
			r = tradeCompleteAll();
		}
		else if (group = "job"){
			r = jobCompleteAll(target_id, title_id);
		}
		if (r == 0){
			$("#" + alert_id).empty();
			$("#" + alert_id).append(genAlert("success", translate[group] + "所有步骤全部完成！"));
		}
		else {
			$("#" + alert_id).empty();
			$("#" + alert_id).append(genAlert("fail", translate[group] + "没有顺利完成！"));
		}
		if (group == "market" || group == "trade"){
			genWorkflow("wf");
			autoRefresh(true);
		}
	});
}

function marketCompleteAll(){
	if (confirm("是否全部执行?")){
		$.each(T_MARKET, function(i, cat){
			var status = $("#" + cat + "_status").attr("status");
			if (status != COMPLETE){
				var result = fetch(getURL("updateWFStatus"), {category:task[cat], ports:JSON.stringify(PORTS), date: NAV_DATE, status:COMPLETE});
			}
		});
		return 0;
	}
	else {
		return 1;
	}
}

function tradeCompleteAll(){
	if (confirm("是否全部执行?")){
		$.each(T_TRADE, function(i, cat){
			var status = $("#" + cat + "_status").attr("status");
			if (status != COMPLETE){
				var result = fetch(getURL("updateWFStatus"), {category:task[cat], ports:JSON.stringify(PORTS), date: NAV_DATE, status:COMPLETE});
			}
		});
		return 0;
	}
	else {
		return 1;
	}
}

function jobCompleteAll(target_id, title_id){
	var lastd = lastDate(NAV_DATE);
	var status = 0;
	if (confirm("是否全部执行?")){
		invoke(getURL("runAllJobs"), {date:NAV_DATE, ports:JSON.stringify(PORTS)}, function(r){
			if (r.data.length > 0){
				$("#" + target_id).empty();
				$("#" + title_id).text("全部完成" + translate["job"] + "总结：");
				$("#wf").hide();
				$("#content_modal").modal();
				drawTable(target_id, markData(r));
				$("#" + target_id).append("<font color='red'>*错误详情请在日志中查询</font>");
			}
		}, "alertAll");
	}
	else {
		status = 1;
	}
	return status;
}

function redoAllHandler(target_id, alert_id, title_id){
	$(document).on("click", "button[cat='redoAll']", function(){
		var group = $(this).attr("group");
		autoRefresh(false);
		var r = 0;
		$("#spinner").show();
		if (group == "market"){
			r = marketRedoAll();
		}
		else if (group == "trade"){
			r = tradeRedoAll();
		}
		else if (group = "job"){
			r = jobRedoAll(target_id, title_id);
		}
		if (r == 0){
			$("#" + alert_id).empty();
			$("#" + alert_id).append(genAlert("success", translate[group] + "所有步骤全部撤销！"));
		}
		else {
			$("#" + alert_id).empty();
			$("#" + alert_id).append(genAlert("fail", translate[group] + "没有顺利完成撤销！"));
		}
		if (group == "trade" || group == "market"){
			genWorkflow("wf");
			autoRefresh(true);
		}
	});
}

function marketRedoAll(){
	if (confirm("是否全部执行?")){
		$.each(T_MARKET, function(i, cat){
			var status = $("#" + cat + "_status").attr("status");
			if (status == COMPLETE){
				var result = fetch(getURL("updateWFStatus"), {category:task[cat], ports:JSON.stringify(PORTS), date: NAV_DATE, status:WORKON});
			}
		});
		return 0;
	}
	else {
		return 1;
	}
}

function tradeRedoAll(){
	if (confirm("是否全部执行?")){
		$.each(T_TRADE, function(i, cat){
			var status = $("#" + cat + "_status").attr("status");
			if (status == COMPLETE){
				var result = fetch(getURL("updateWFStatus"), {category:task[cat], ports:JSON.stringify(PORTS), date: NAV_DATE, status:WORKON});
			}
		});
		return 0;
	}
	else {
		return 1;
	}
}

function jobRedoAll(target_id, title_id){
	var status = 0;
	if (confirm("是否全部执行?")){
		invoke(getURL("redoAllJobs"), {date:NAV_DATE, ports:JSON.stringify(PORTS)}, function(r){
			if (r.data.length > 0){
				$("#" + target_id).empty();
				$("#" + title_id).text("全部重开" + translate["job"] + "总结：");
				$("#wf").hide();
				$("#content_modal").modal();
				drawTable(target_id, markData(r));
				$("#" + target_id).append("<font color='red'>*错误详情请在日志中查询</font>");
			}
		}, "alertAll");
	}
	else {
		status = 1;
	}
	return status;
}

function drawPortsSelect(){
	autoRefresh(false);
	drawFilterForm("ports_detail", "portfolio", "ports_select", {}, true);
	//开启组合选择窗口
	$("#ports_search_btn").on("click", function(){
		$("#wf").hide();
		$("#ports_modal").modal();
		return false;
	});
	//搜索投资组合，并完成选择
	$("#portfolio_search_btn").click(function(){
		$("#workflow").hide();
		var inputs = {client:$("#portfolio_client_filter").val(), 
				  	  type: $("#portfolio_type_filter").val(),
				  	  name: $("#portfolio_name_filter").val()};
		invoke(getURL("portList"), inputs, function(data){
			$("#portfolio_list").empty();
          	genRadios("portfolio_list", "ports", data.data);
		});
		return false;
	});
	
	$("#portfolio_select_btn").click(function(){
		if ($(this).text() == "全选") {
			$("input[data-for='ports']").prop("checked", true);
			$(this).text("全除");	
		}
		else {
			$("input[data-for='ports']").prop("checked", false);
			$(this).text("全选");
		}
		return false;
	});
}

function autoRefresh(start){
	
}

function drawWorkflow(target_id, data, table){
	var $table = $("<table style='width:95%' class='table table-bordered'></table>");
	$table.attr("id", "workflow");
	
	$.each(table, function(group, value){
		$table = genWFTable($table, data[group], table[group]);	
	});
	
	$("#" + target_id).empty();
	$("#" + target_id).append($table);
	
	//根据job的相应关系，来处理表格的可编辑状态
	
	//规则1：如果投资组合交易处于已完成状态，则价格、证券主文件、汇率不允许进行操作
	if ($("#portTrade_status").attr("status") == COMPLETE){
		$("[group='market']").prop("disabled", true);
	}
	//规则2：如果公司行为处于已完成状态，则证券主文件不能进行操作
	if ($("#ca_status").attr("status") == COMPLETE){
		$("[dataFor='smf']").prop("disabled", true);
	}
	//规则3：如果换汇交易或现金交易处于已完成状态，则汇率不能进行操作
	if ($("#fx_status").attr("status") == COMPLETE || $("#cash_status").attr("status") == COMPLETE){
		$("[dataFor='ex']").prop("disabled", true);
	}
	
	//规则4：如果市场数据还没有完成，所有trade的交易不能进行
	if ($("#price_status").attr("status") != COMPLETE || $("#smf_status").attr("status") != 0 || $("#ex_status").attr("status") != COMPLETE){
		$("[group='trade']").prop("disabled", true);
	}
	//规则5：如果交易估值关账已经完成，所有trade的交易不能进行
	if ($("#dailyClose_status").attr("status") == COMPLETE || $("#auto_status").attr("status") == COMPLETE){
		$("[group='trade']").prop("disabled", true);
	}
	//规则6: 如果交易有一个处于未完成状态，auto和dailyClose不能进行操作
	if ($("#shareholder_status").attr("status") != COMPLETE || 
		$("#cash_status").attr("status") != COMPLETE || 
		$("#ca_status").attr("status") != COMPLETE || 
		$("#fx_status").attr("status") != COMPLETE ||
		$("#portTrade_status").attr("status") != COMPLETE)
	{
		
		$("[dataFor='auto']").prop("disabled", true);
		$("[dataFor='dailyClose']").prop("disabled", true);
	}
	//规则7: 如果dailyClose处于完成状态，auto应不能操作
	if ($("#dailyClose_status").attr("status") == COMPLETE){
		$("[dataFor='auto']").prop("disabled", true);
	}
	
	//规则8: 如果自动结转未处于完成状态，则无法进行dailyClose
	if ($("#auto_status").attr("status") != COMPLETE){
		$("[dataFor='dailyClose']").prop("disabled", true);
	}
	
	//规则9: 如果每日会计账或每月会计账处于完成状态，dailyClose应不能操作
	if ($("#daily_status").attr("status") == COMPLETE || $("#monthly_status").attr("status") == COMPLETE){
		$("[dataFor='dailyClose']").prop("disabled", true);
	}
	//规则10: 如果交易估值和自动job未处于完成状态，其他所有的job均无法开启
	if ($("#auto_status").attr("status") != COMPLETE || $("#dailyClose_status").attr("status") != COMPLETE){
		$("[dataFor='daily']").prop("disabled", true);
		$("[dataFor='monthly']").prop("disabled", true);
	}
	
	//规则11: 如果每日估值和每月会计处理未处于完成状态，则无法计算NAV
	if ($("#daily_status").attr("status") != COMPLETE || $("#monthly_status").attr("status") != COMPLETE){
		$("[dataFor='nav']").prop("disabled", true);
	}
	
	if ($("#nav_status").attr("status") == COMPLETE){
		$("[dataFor='daily']").prop("disabled", true);
		$("[dataFor='monthly']").prop("disabled", true);
	}
	
	if ($("#nav_status").attr("status") != COMPLETE){
		$("[dataFor='generalClose']").prop("disabled", true);
	}
	
	if ($("#generalClose_status").attr("status") == COMPLETE){
		$("[dataFor='nav']").prop("disabled", true);
	}
	
	//规则10：如果market的operation中有一个操作被disabled掉，关闭completeAll和redoAll
	if ($("button[group='market'][cat='opr']").prop("disabled")){
		$("#market_completeAll").prop("disabled", true);
		$("#market_redoAll").prop("disabled", true);
	}
	
	//规则11：如果trade的operation中有一个操作被disabled掉，关闭completeAll和redoAll
	if ($("button[group='trade'][cat='opr']").prop("disabled")){
		$("#trade_completeAll").prop("disabled", true);
		$("#trade_redoAll").prop("disabled", true);
	}
	
	//规则12：如果jobe的operation中有一个操作处于非disabled状态，关闭completeAll和redoAll
	if ($("button[group='job'][cat='opr']:disabled").length >=6){
		$("#job_completeAll").prop("disabled", true);
		$("#job_redoAll").prop("disabled", true);
	}
	
	//始终将success, fail, log, recon功能开放
	$("[cat='success']").prop("disabled", false);
	$("[cat='fail']").prop("disabled", false);
	$("[cat='log']").prop("disabled", false);
	$("[cat='recon']").prop("disabled", false);
	
	//根据权限来处理表格的可编辑状态
	//0: 市场信息操作员
	//1: 交易操作员
	//2: 基金会计
	var oprs = {};
	oprs[0] = "market";
	oprs[1] = "trade";
	oprs[2] = "job";
	
	$("[cat='success']")
	$.each(oprs, function(role, opr){
		if (data.oprRole.indexOf(parseInt(role)) < 0){
			$("[group='" + opr + "']").prop("disabled", true);
			$("[group='" + opr + "']").closest("tr").hide();
			$("#" + opr + "_head").hide();
			$("#" + opr + "_title").hide();
		}
	});
}

function genWFTable(target, data, table){
	var $table = target;
	var $ttr = $("<tr><td colspan='" + (table.titles.length + 1) + "'><h4><strong>" + table.label + "</strong></h4></td></tr>");
	$ttr.find("h4").append("&nbsp;&nbsp;<button class='btn btn-info' cat='completeAll' group='" + table.name + "' id='" + table.name + "_completeAll"+ "'>全部完成</button>");
	$ttr.find("h4").append("&nbsp;&nbsp;<button class='btn btn-danger' cat='redoAll' group='" + table.name + "' id='" + table.name + "_redoAll"+ "'>全部撤销</button>");
	$ttr.attr("id", table.name + "_head");
	$table.append($ttr);
	
	var $title_tr = $("<tr><td></td></tr>");
	$.each(table.titles, function(index, value){
		$title_tr.append("<td>" + value + "</td>");
	});
	$title_tr.attr("id", table.name + "_title")
	$table.append($title_tr);
	$.each(table.items, function(index, value){
		var k = value;
		var v = data[k];
		var $tr = $("<tr></tr>");
		//生成任务按钮
		var $td = $("<td></td>");
		var $task = $("<button class='btn btn-primary'>" + table[k].label +"</button>");
		$task.attr("id", k + "_task");
		$task.attr("dataFor", k);
		$task.attr("cat", "task");
		$task.attr("group", table.name);
		$td.append($task);
		$tr.append($td);
		if (v.opr != 1){
			$task.prop("disabled", true);
		}
		
		//生成开始时间
		var $td = $("<td></td>");
		var $start = $("<label>" + v.start + "</label>")
		$start.attr("id", k + "_start");
		$start.attr("dataFor", k);
		$start.attr("cat", "start");
		$start.attr("group", table.name);
		$td.append($start);
		$tr.append($td);
		
		//生成成功数量
		var $td = $("<td></td>");
		var $success = $("<a href='javascript:;'><font size='5' color='green'>" + v.success +"</font></a>");
		$success.attr("id", k + "_success");
		$success.attr("dataFor", k);
		$success.attr("cat", "success");
		$success.attr("group", table.name);
		$td.append($success);
		$tr.append($td);
		
		//生成失败数量
		var $td = $("<td></td>");
		var $fail = $("<a href='#'><font size='5' color='red'>" + v.fail +"</font></a>");
		$fail.attr("id", k + "_fail");
		$fail.attr("dataFor", k);
		$fail.attr("cat", "fail");
		$fail.attr("group", table.name);
		$td.append($fail);
		$tr.append($td);
		
		//生成结束时间
		var $td = $("<td></td>");
		var $end = $("<label>" + v.end + "</label>");
		$end.attr("id", k + "_end");
		$end.attr("dataFor", k);
		$end.attr("cat", "end");
		$end.attr("group", table.name);
		$td.append($end);
		$tr.append($td);
		
		//生成作业状态
		var $td = $("<td></td>");
		var text = "";
		var color = ""
		if (v.opr == COMPLETE){
			text = "已完成";
			color = "green"
		}
		else if (v.opr == WORKON){
			text = "进行中";
			color = "blue";
		}
		else if (v.opr == NOTSTART){
			text = "未开始";
			color = "red";
		}
		var $status = $("<strong><font size='4' color='" + color + "'>" + text +"</font></strong></a>");
		$status.attr("id", k + "_status");
		$status.attr("dataFor", k);
		$status.attr("cat", "status");
		$status.attr("group", table.name);
		$status.attr("status", v.opr);
		$td.append($status);
		$tr.append($td);
		
		//生成操作按钮
		var $td = $("<td></td>");
		//var opr_btns = ["opr", "log", "recon"];
		var opr_btns = ["opr", "log"];
		$.each(opr_btns, function(index, btn){
			$td.append("&nbsp;&nbsp;");
			var $b = $("<button></button>");
			$b.attr("id", k + "_" + btn);
			$b.attr("cat", btn);
			if (btn == "opr"){
				$b.attr("dataFor", k);
				$b.attr("group", table.name);
				$b.attr("opr", v.opr);
				//当数据库记录显示该项目已经完成状态0，button的text应显示下一个动作 -重开
				if (v.opr == COMPLETE){
					$b.text("重开");
					$b.addClass("btn btn-primary");
				}
				//当数据库记录显示该项目处于未完成状态1，button的text应显示下一个动作 -完成
				else if (v.opr == WORKON){
					$b.text("完成");
					$b.addClass("btn btn-success");
				}
				//当数据库记录没有该项目，处于未开始状态2，button的text应显示下一个动作-开始，
				else if (v.opr == NOTSTART){
					$b.text("开始");
					$b.addClass("btn btn-primary");
				}
			}
			else if (btn == "log"){
				$b.text("日志");
				$b.addClass("btn btn-default");
				$b.attr("dataFor", k);
				$b.attr("group", table.name);
			}
			else if (btn == "recon"){
				$b.text("对账");
				$b.addClass("btn btn-default");
				$b.attr("dataFor", k);
				$b.attr("group", table.name);
			}
			$td.append($b);
		});
		$tr.append($td);
		$table.append($tr);
	});
	return $table;
}

function displaySelectedPorts(target_id, names, links){
	$("#" + target_id).empty();
	if (names.length > 0){
		var $title = $("<a href='#' class='list-group-item active'>操作组合：</a>");
		$title.append("<a class='btn btn-info' href='#' id='gen_wf_btn' role='button'>工作流程<span class='glyphicon glyphicon-ok-sign' aria-hidden='true'></span></a>");
		var $div = $("<div class='list-group'></div>");
		$div.append($title);
		var port_options = {};
		$.each(names, function(i, name){
			var link = links[i];
			var port_id = link.split("=")[1];
			port_options[port_id] = name;
			var $a = genLink("", "", link, name);
			$a.addClass("list-group-item");
			var $span = $("<span class='glyphicon glyphicon-hand-up' aria-hidden='true'></span>");
			$span.attr("id", "voucher_" + port_id);
			$span.attr("data-for", "voucher_create");
			$a.append($span);
			
			$div.append($a);
			$("#" + target_id).append($div);
		});
		$("[data-for='voucher_create']").on("click", function(){
			var id = $(this).attr("id");
			var port_id = id.split("_")[1];
			
			$("#manual_detail").empty();
			manageManualEntry("manual_detail", "manual_title", "manual_alert", port_id, port_options, NAV_DATE, NAV_DATE);
			$("#manual_modal").modal();
			return false;
		});
	}
}

function markData(data){
	return data;
}

function drawExport(){
	$("#export_title").text("数据导出功能");
	$("#export_detail").empty();
	drawFilterForm("export_detail", "export", "wf_export_filter", {}, true);
	
	$("#export_date_filter").val(NAV_DATE).prop("disabled", true);
	$port = $("#export_port_filter");
	updateSelectOptions($port, PORTS_MAP);
	updateSelectOptions($("#export_config_filter"), fetch(getURL("portExportShortList"), {pid:$port.val()}, "export_alert"));
	
	$port.on("change", function(){
		updateSelectOptions($("#export_config_filter"), fetch(getURL("portExportShortList"), {pid:$port.val()}, "export_alert"));
	});
	
	$("#export_view_btn").on("click", function(){
		var portID = $("#export_port_filter").val();
		var configID = $("#export_config_filter").val();
		var exportDate = $("#export_date_filter").val();
		drawPagTable("export_list", "exportView", {portID:portID, configID:configID, exportDate:exportDate}, "", "", "export_alert");
	});
	
	$("#export_export_btn").on("click", function(){
		var portID = $("#export_port_filter").val();
		var configID = $("#export_config_filter").val();
		var exportDate = $("#export_date_filter").val();
		
		 $("#export_list").empty();
		 $("#export_list").text("正在导出数据，请稍后......");
		 
		invoke(getURL("exportData"), 
			   {portID:portID, configID:configID, exportDate:exportDate}, 
			   function(data){
				   $("#export_list").empty();
				   $("#export_list").text("导出数据成功，请查看：" + data);
			   }, 
			   "export_alert");
		return false;
	});
}