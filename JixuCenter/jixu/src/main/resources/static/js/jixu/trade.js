var ACCRUED = 1;

function tradeCreate(target_id, alert_id, info){
	var tradeForm = getData(fetch(getURL("tradeForm"), {tradeType:info["tradeType"]}));
	$("#" + target_id).empty();
	
	info.messageHeader = tradeForm.messageHeader;
	var e = $.extend(true, {}, tradeEditForm)
	e.source.on = false;
	e.source.value = info;
	tradeForm["sets"] = tradeForm.createSets;
	tradeForm.forms.splice(0,1);
	tradeForm.buttons.splice(1,1);
	
	e.form.form = tradeForm;
	e.form.inputs = info;
	e.form.func = tradeForm.func;
	e.event.create.source.func = function(dataFor){
		tradeSave(dataFor, info)
	};

	genTabs(target_id, "trade_tabs", "trade_cons", {"basic":"基本信息"});
	genTradeEditForm("basic_display", info["tp"], e.source, e.form, e.event, 4, alert, false);
	if (info.expense){
		expenseCreate(info.assetType, info.port, alert, info.date);
	}
	if (info.collateral){
		collateralCreate(info.assetType, info.port, alert, info.date);
	}
	$("#trade_title").text(translate[info.tp] + "创建");
	$("a[href='#basic_display']").tab("show");
}

function caCreate(target_id, alert_id, info){
    form_address = fetch(getURL("caFormTypes"), {caType:info["tradeType"]})
	var tradeForm = getData(form_address);
	$("#" + target_id).empty();
	
	info.messageHeader = tradeForm.messageHeader;
	var e = $.extend(true, {}, tradeEditForm)
	e.source.on = false;
	tradeForm["sets"] = tradeForm.createSets;
	tradeForm.forms.splice(0,1);
	tradeForm.buttons.splice(1,1);
	
	e.form.form = tradeForm;
	e.form.inputs = info;
	e.form.func = tradeForm.func;
	e.event.create.source.func = function(dataFor){
		tradeSave(dataFor, info)
	};

	genTabs(target_id, "trade_tabs", "trade_cons", {"basic":"基本信息"});
	genTradeEditForm("basic_display", info["tp"], e.source, e.form, e.event, 4, alert, false);
	
	$("#trade_title").text(translate[info.tp] + "创建");
	$("a[href='#basic_display']").tab("show");
}

function expenseCreate(assetType, port, alert_id, nav_date){
	$("#expense_div").remove();
	var $div = $("<div id='expense_div'></div>")
	var $title = $("<div class='col-md-1'></div>");
	$div.append("<br>");
	$div.append($title);
	$div.append("<br><br>");
	var data = fetch(getURL("tradeExpenses"), {assetType:assetType});
	if (Object.keys(data).length > 0){
		addTabs("trade_tabs", "trade_cons", {"expenses":"费用详情"});
		$("#expenses_display").append($div);
		var f = $.extend(true, {}, tableForm);	
		f.source.on = false;
		f.form.name = "tradeExpense_table";
		f.event.update.on = false;
		f.event.add.form.name = "tradeExpense_add";
		f.event.add.source.address = "tradeCreate";
		f.event.add.source.func = function(id, dataFor){
			var $exp = $("#" + dataFor + "_" + id + "_expense");
			updateSelectOptions($exp, data);
			portCashAccount("port", dataFor + "_" + id + "_bankAccount")
			$("#" + dataFor + "_" + id + "_feeTradeCurrency").val($("#tradeCurrency").val());
			settleCurrency(dataFor + "_" + id + "_bankAccount", dataFor + "_" + id + "_tableFeeCurrency");
			defaultFxRate2("tradeDate", "port", dataFor + "_" + id + "_feeTradeCurrency", dataFor + "_" + id + "_fxRate");
			expenseSettleDate("settleDate");

			updateTotalExpense("totalExpense", "basic_a", "expenses_display");
			
			//var expVal = fetch(getURL("checkExpenseType"), {id: $exp.val()}, "trade_alert");
			//updateExpense(expVal, id);
			
			$exp.on("change", function(){
				var expVal = fetch(getURL("checkExpenseType"), {id: $(this).val()}, "trade_alert");
				updateExpense(expVal, id);
			})
		};
		f.event.del.source.func = function(id, dataFor){
			totalExpense("totalExpense", "expenses_display");
		}
		genTableForm("expense_div", "tradeExpense", f.source, f.form, f.event, alert, true);
		$("#tradeExpense_add").text("增加费用");
		$("#tradeExpense_add").appendTo($title);
	}
};

function updateExpense(flag, id){
	if (flag == ACCRUED){
		$("#tradeExpense_" + id + "_expenseAmount").val(0);
		$("#tradeExpense_" + id + "_expenseAmount").prop("disabled", true);
		$("#tradeExpense_" + id + "_cashAmount").val(0);
		$("#tradeExpense_" + id + "_cashAmount").prop("disabled", true);
	}
	else{
		$("#tradeExpense_" + id + "_expenseAmount").val('');
		$("#tradeExpense_" + id + "_expenseAmount").prop("disabled", false);
		$("#tradeExpense_" + id + "_cashAmount").val('');
		$("#tradeExpense_" + id + "_cashAmount").prop("disabled", false);
	}
}

//创建抵押界面
function collateralCreate(assetType, port, alert_id, nav_date){
	$("#collateral_div").remove();
	var $div = $("<div id='collateral_div'></div>")
	var $title = $("<div class='col-md-1'></div>");
	$div.append("<br>");
	$div.append($title);
	$div.append("<br><br>");
	
	var smfs = fetch(getURL("smfsInPort"), {port:port, date:nav_date});
	addTabs("trade_tabs", "trade_cons", {"collaterals":"抵押物"});
	$("#collaterals_display").append($div);
	var f = $.extend(true, {}, tableForm);	
	f.source.on = false;
	f.form.name = "tradeCollateral_table";
	f.event.update.on = false;
	f.event.add.form.name = "tradeCollateral_add";
	f.event.add.source.address = "tradeCreate";
	f.event.add.source.func = function(id, dataFor){
		secAcct(port, dataFor + "_" + id + "_secAcct");
		$("#" + dataFor + "_" + id + "_secid").typeahead({source:smfs}); 
	};
	genTableForm("collateral_div", "tradeCollateral", f.source, f.form, f.event, alert, true);
	$("#tradeCollateral_add").text("增加抵押物");
	$("#tradeCollateral_add").appendTo($title);
	
}

function tradeSave(dataFor, info){
	var create = true;
	$("#" + dataFor).validate();
	if ($("#" + dataFor).valid()){
		var expense = getExpenses();
		var collateral = getCollaterals();
		if (expense.status && collateral.status){
			if (confirm("是否确认创建?")){
	 			var params = [];
	 			var param_names = [];
	 			var expenses = [];
	 			var colls = [];
	 			var type = "";
	 			
	 			var json = {};
	 			json.header = info.messageHeader;
	 			json.operation = "ADD";
	 			json.expenses = expense.expenses;
	 			json.collaterals = collateral.collaterals;
	 			
	 			$.each($("#trade_detail [data-for='" + dataFor + "']"), function(index, value){
	 				if ($(value).attr("id") == "tradeType"){
	 					type = $(value).val();
	 				}
	 				if ($(value).prop("type") == "checkbox"){
	 					params.push($(value).is(":checked"));
	 					json[$(value).attr("name")] = $(value).is(":checked");
	 				}
	 				else {
	 					params.push($(value).val()||$(value).text());
	 					json[$(value).attr("name")] = $(value).val()||$(value).text();
	 				}
	 				if ($(value).attr("name")){
	 					param_names.push($(value).attr("name"));
	 				}
	 			});
	 			
 				var address = "tradeCreate"
	 			if (dataFor == "ca"){
	 				address = "caCreate";
	 			}
 				logInfo(address, {type:type, params:JSON.stringify(params), expenses:JSON.stringify(expense.expenses), collaterals:JSON.stringify(collateral.colls),json:JSON.stringify(json)}, param_names)
	 			invoke(getURL(address),{type:type, params:JSON.stringify(params), 
	 									           expenses:JSON.stringify(expense.expenses),
	 									           collaterals:JSON.stringify(collateral.colls),
	 									           json:JSON.stringify(json)}, function(data){
	 				var new_id = data;
	 				$("#trade_alert").empty();
	            	$("#content_alert").empty();
	            	$("[data-for='" + dataFor + "']").prop("disabled", true);
	            	$("[data-for*='tradeExpense']").prop("disabled", true);
	            	$("[data-for*='tradeCollateral']").prop("disabled", true);
	            	$("#trade_alert").append(genAlert("success", translate[dataFor] + "创建成功!"));
	            	$("#content_alert").append(genAlert("success", translate[dataFor] + "创建成功!"));
	     			$("#" + dataFor + "_create").prop("disabled", true);
	 			}, ["trade_alert","content_alert"]);
	 		}
		}
		else {
			$("#trade_alert").empty();
			$("#trade_alert").append(genAlert("fail", "费用或抵押物仍有没有存储的条目，无法保存交易！"));
			$("#content_alert").append(genAlert("fail", "费用或抵押物仍有没有存储的条目，无法保存交易！"));
		}
	}
}

function tradeUpdate(id, dataFor, info){
	if ($("#" + dataFor + "_update").text() == "保存"){
		if ($("#" + dataFor).valid()){
			var expense = getExpenses();
			var collateral = getCollaterals();
			if (expense.status && collateral.status){
				if (confirm("是否确认保存?")){
					var btn = $(this).button('操作中...')
		 			var params = [];
					var expenses = [];
					var param_names =[];
					var json = {};
		 			json.header = info.messageHeader;
		 			json.id = id;
		 			json.operation = "UPDATE";
		 			json.expenses = expense.expenses;
		 			json.collaterals = collateral.collaterals;
		 			
		 			$.each($("#trade_detail [data-for='" + dataFor + "']"), function(index, value){
		 				if ($(value).prop("type") == "checkbox"){
		 					params.push($(value).is(":checked"));
		 					json[$(value).attr("name")] = $(value).is(":checked");
		 				}
		 				else {
		 					params.push($(value).val()||$(value).text());
		 					json[$(value).attr("name")] = $(value).val()||$(value).text();
		 				}
		 				if($(value).attr("name")){
		 					param_names.push($(value).attr("name"));
		 				}
		 			});
		 			
		 			logInfo("tradeUpdate", {id:id, params:JSON.stringify(params),
						  expenses:JSON.stringify(expense.expenses),
							  collaterals:JSON.stringify(collateral.colls),
							  json:JSON.stringify(json)}, param_names);
		 			invoke(getURL("tradeUpdate"),{id:id, params:JSON.stringify(params),
		 										  expenses:JSON.stringify(expense.expenses),
		 										  collaterals:JSON.stringify(collateral.colls),
		 										  json:JSON.stringify(json)}, function(data){
		 				$("#trade_alert").empty();
		 				$("#content_alert").empty();
		 				$("#trade_alert").append(genAlert("success", translate[dataFor] + "保存成功!"));
		 				$("#content_alert").append(genAlert("success", translate[dataFor] + "保存成功!"));
		                	$("[data-for='" + dataFor + "']").prop("disabled", true);
			            	$("[data-for='" + dataFor + "']").prop("disabled", true);
			            	$("[data-for*='tradeExpense']").prop("disabled", true);
			            	$("[data-for*='tradeCollateral']").prop("disabled", true);
			            	
		                	$("#" + dataFor + "_update").attr("class", "btn btn-primary")
		         		$("#" + dataFor + "_update").text("更新");
		                	$("[data-for='tradeExpense_add']").prop("disabled", true);
		            		$("[data-for='tradeExpense_delete']").prop("disabled", true);
		            		
		            		$("[data-for='tradeCollateral_add']").prop("disabled", true);
		            		$("[data-for='tradeCollateral_delete']").prop("disabled", true);
		            		$("#" + dataFor + "_smf_update").prop("disabled", true);
	            		
		 			}, "trade_alert");
				}
			}
			else{
				$("#trade_alert").empty();
				$("#content_alert").empty();
				$("#trade_alert").append(genAlert("fail", "费用或抵押物仍有没有存储的条目，无法保存交易！"));
				$("#content_alert").append(genAlert("fail", "费用或抵押物仍有没有存储的条目，无法保存交易！"));
			}
		}
	}
	else {
		$.each($("[data-for='" + dataFor + "']"), function(index, value){
			if ($(value).prop("editable") == false){
				$(value).prop("disabled", true);
			}
			else {
				$(value).prop("disabled", false);
			}
		});
		$("#" + dataFor + "_update").attr("class", "btn btn-success")
		$("#" + dataFor + "_update").text("保存");
		
		$("[data-for*='tradeExpense']").prop("disabled", false);
		$("[data-for*='tradeCollateral']").prop("disabled", false);
    	
		$("[data-for='tradeExpense_add']").prop("disabled", false);
		$("[data-for='tradeExpense_delete']").prop("disabled", false);
		
		$("[data-for='tradeCollateral_add']").prop("disabled", false);
		$("[data-for='tradeCollateral_delete']").prop("disabled", false);
		
		$("#" + dataFor + "_smf_update").prop("disabled", false);
	}
}
function caUpdate(id, dataFor, info){
	if ($("#" + dataFor + "_update").text() == "保存"){
		if ($("#" + dataFor).valid()){
			var expense = getExpenses();
			var collateral = getCollaterals();
			if (expense.status && collateral.status){
				if (confirm("是否确认保存?")){
					var btn = $(this).button('操作中...')
		 			var params = [];
					var expenses = [];
					var param_names = [];
					var json = {};
		 			json.header = info.messageHeader;
		 			json.id = id;
		 			json.operation = "UPDATE";
		 			json.expenses = expense.expenses;
		 			json.collaterals = collateral.collaterals;
		 			
		 			$.each($("#trade_detail [data-for='" + dataFor + "']"), function(index, value){
		 				if ($(value).prop("type") == "checkbox"){
		 					params.push($(value).is(":checked"));
		 					json[$(value).attr("name")] = $(value).is(":checked");
		 				}
		 				else {
		 					params.push($(value).val()||$(value).text());
		 					json[$(value).attr("name")] = $(value).val()||$(value).text();
		 				}
		 				if($(value).attr("name")){
		 					param_names.push($(value).attr("name"));
		 				}
		 			});
		 			logInfo("caUpdate", {id:id, params:JSON.stringify(params),
							  expenses:JSON.stringify(expense.expenses),
								  collaterals:JSON.stringify(collateral.colls),
								  json:JSON.stringify(json)}, param_names);
		 			invoke(getURL("caUpdate"),   {id:id, params:JSON.stringify(params),
		 										  expenses:JSON.stringify(expense.expenses),
		 										  collaterals:JSON.stringify(collateral.colls),
		 										  json:JSON.stringify(json)}, function(data){
		 				$("#trade_alert").empty();
		 				$("#content_alert").empty();
		 				$("#trade_alert").append(genAlert("success", translate[dataFor] + "保存成功!"));
		 				$("#content_alert").append(genAlert("success", translate[dataFor] + "保存成功!"));
	                	$("[data-for='" + dataFor + "']").prop("disabled", true);
		            	$("[data-for='" + dataFor + "']").prop("disabled", true);
		            	$("[data-for*='tradeExpense']").prop("disabled", true);
		            	$("[data-for*='tradeCollateral']").prop("disabled", true);
		            	
	                	$("#" + dataFor + "_update").attr("class", "btn btn-primary")
	         			$("#" + dataFor + "_update").text("更新");
	                	$("[data-for='tradeExpense_add']").prop("disabled", true);
	            		$("[data-for='tradeExpense_delete']").prop("disabled", true);
	            		
	            		$("[data-for='tradeCollateral_add']").prop("disabled", true);
	            		$("[data-for='tradeCollateral_delete']").prop("disabled", true);
		 			}, "trade_alert");
				}
			}
			else{
				$("#trade_alert").empty();
				$("#content_alert").empty();
				$("#trade_alert").append(genAlert("fail", "费用或抵押物仍有没有存储的条目，无法保存交易！"));
				$("#content_alert").append(genAlert("fail", "费用或抵押物仍有没有存储的条目，无法保存交易！"));
			}
		}
	}
	else {
		$.each($("[data-for='" + dataFor + "']"), function(index, value){
			if ($(value).prop("editable") == false){
				$(value).prop("disabled", true);
			}
			else {
				$(value).prop("disabled", false);
			}
		});
		$("#" + dataFor + "_update").attr("class", "btn btn-success")
		$("#" + dataFor + "_update").text("保存");
		
		$("[data-for*='tradeExpense']").prop("disabled", false);
    	$("[data-for*='tradeCollateral']").prop("disabled", false);
    	
		$("[data-for='tradeExpense_add']").prop("disabled", false);
		$("[data-for='tradeExpense_delete']").prop("disabled", false);
		
		$("[data-for='tradeCollateral_add']").prop("disabled", false);
		$("[data-for='tradeCollateral_delete']").prop("disabled", false);
	}
}

function tradeDetail(itemID, tp, data, inputs){
		var info = {};
		if (tp == "portTrade" || tp == "fx"){
 			info["assetType"] = inputs.type;
 		}
 		else if (tp == "cash"){
 			info["assetType"] = CASH;
 		}
 		else if (tp == "shareholder"){
 			info["assetType"] = CAPITAL;
 		}
		
		if (info.assetType == "0"){
			alert("请选择正确的资产类型!");
		}
		else{
			var assetForm = getData(fetch(getURL("assetForm"), {assetType:info["assetType"]}));
			if (data.data instanceof Array){
				info["tradeType"] = data.data[assetForm.tradeTypeID];
			}
			else{
				info["tradeType"] = data.data["tradeType"];
			}
			
			var tradeForm = getData(fetch(getURL("tradeForm"), {tradeType:info["tradeType"]}));
			tradeForm["sets"] = tradeForm.editSets;
			tradeForm.buttons.splice(0,1);
			
			$.each(tradeForm.forms, function(i, form){
				if (data.data instanceof Array){
					info[form.cat] = data.data[i];
				}
				else{
					info[form.cat] = data.data[form.cat];
				}
				
			});
			info["tp"] = tp;
			info["client"] = $("#portfolio_client_filter").val();
	 		info["ports"] = [info.port];
	 		info["ports_str"] = JSON.stringify(info.ports);
	 		info["id"] = itemID;
	 		info["messageHeader"] = tradeForm.messageHeader;
	 		
			var f = $.extend(true, {}, tradeEditForm)
	        f.source.on = false;
			f.source.value = data.data;
			
			f.form.form = tradeForm;
			f.form.inputs = info;
			f.form.func = tradeForm.func;
			
			f.event.update.on = false;
			f.event.update.source.inputs["id"] = itemID;
			f.event.update.source.func = function(id, dataFor){
				tradeUpdate(id, dataFor, info);
			}
			f.event.update.source.address = "tradeUpdate";
			f.event.create.on = false;
			
			$("#trade_title").text(translate[tp] + "详情-编号" + itemID);
	        $("#trade_detail").empty();
			
			genTabs("trade_detail", "trade_tabs", "trade_cons", {"basic":"基本信息"});
			genTradeEditForm("basic_display", info["tp"], f.source, f.form, f.event, 4, "trade_alert", true);
			if (assetForm.expense){
				expenseDetail(itemID, info.port, info.assetType, fetch(getURL("tradeExpenseDetail"), {id:itemID}));
			}
			if (assetForm.collateral){
				collateralDetail(itemID, info.port, info.assetType, fetch(getURL("tradeCollateralDetail"), {id:itemID}));
			}
			$("#trade_title").text("编号：" + itemID + "-" + translate[info.tp] + "详情");
			$("#trade_modal").modal();
			$("a[href='#basic_display']").tab("show");
		}
}

function caDetail(itemID, tp, data, inputs){
	var info = {};
	info["corpActionType"] = data.data["corpActionType"];
    info["tradeType"] = data.data["corpActionType"];
	form_address = fetch(getURL("caFormTypes"), {caType:info["corpActionType"]})
	var tradeForm = getData(form_address);
	tradeForm["sets"] = tradeForm.editSets;
	tradeForm.buttons.splice(0,1);
	
	$.each(tradeForm.forms, function(i, form){
		if (data.data instanceof Array){
			info[form.cat] = data.data[i];
		}
		else{
			info[form.cat] = data.data[form.cat];
		}
		
	});
	info["tp"] = tp;	
	info["id"] = itemID;
	info["messageHeader"] = tradeForm.messageHeader;
		
	var f = $.extend(true, {}, tradeEditForm)
    f.source.on = false;
	f.source.value = data.data;
	
	f.form.form = tradeForm;
	f.form.inputs = info;
	f.form.func = tradeForm.func;
	
	f.event.update.on = false;
	f.event.update.source.inputs["id"] = itemID;
	f.event.update.source.func = function(id, dataFor){
		caUpdate(id, dataFor, info);
	}
	f.event.update.source.address = "caUpdate";
	f.event.create.on = false;
	
	$("#trade_title").text(translate[tp] + "详情-编号" + itemID);
    $("#trade_detail").empty();
	
	genTabs("trade_detail", "trade_tabs", "trade_cons", {"basic":"基本信息"});
	genTradeEditForm("basic_display", info["tp"], f.source, f.form, f.event, 4, "trade_alert", true);
	$("#trade_title").text("编号：" + itemID + "-" + translate[info.tp] + "详情");
	$("#trade_modal").modal();
	$("a[href='#basic_display']").tab("show");
	
}

//抵押详细界面
function collateralDetail(tradeID, port, assetType, colls){
		$("#collateral_div").remove();
		var $div = $("<div id='collateral_div'></div>")
		var $title = $("<div class='col-md-1'></div>");
		$div.append("<br>");
		$div.append($title);
		$div.append("<br><br>");
		
		var smfs = fetch(getURL("smfsInPort"), {port:port, date:$("#tradeDate").val()});
		addTabs("trade_tabs", "trade_cons", {"collaterals":"抵押物"});
		$("#collaterals_display").append($div);
		
		var f = $.extend(true, {}, tableForm);	
		f.source.on = false;
		f.source.value = colls.data;
		f.form.name = "tradeCollateral_table";
		f.form.func = function(data){
			$.each(data, function(i, v){
				var id = v[0];
				var dataFor = "tradeCollateral";
				secAcct(port, dataFor + "_" + id + "_secAcct", v[1]);
				$("#" + dataFor + "_" + id + "_secid").typeahead({source:smfs}); 
				$("#" + dataFor + "_" + id + "_secid").val(v[2]);
			});
		};
		f.event.update.on = false;
		f.event.update.source.address = "tradeUpdate";
		f.event.add.form.name = "tradeCollateral_add";
		f.event.add.source.address = "tradeUpdate";
		f.event.add.source.func = function(id, dataFor){
			secAcct(port, dataFor + "_" + id + "_secAcct");
			$("#" + dataFor + "_" + id + "_secid").typeahead({source:smfs}); 
		};
		f.event.del.source.address = "dummy";
		genTableForm("collateral_div", "tradeCollateral", f.source, f.form, f.event, "trade_alert", true);
		$("#tradeCollateral_add").text("增加抵押物");
		$("#tradeCollateral_add").appendTo($title);
		$("[data-for='tradeCollateral_add']").prop("disabled", true);
		$("[data-for='tradeCollateral_update']").prop("disabled", true);
		$("[data-for='tradeCollateral_delete']").prop("disabled", true);

}

function expenseDetail(tradeID, port, assetType, expenses){
	$("#expense_div").remove();
	var $div = $("<div id='expense_div'></div>")
	var $title = $("<div class='col-md-1'></div>");
	$div.append("<br>");
	$div.append($title);
	$div.append("<br><br>");
	var expTypes = fetch(getURL("tradeExpenses"), {assetType:assetType});
	if (Object.keys(expTypes).length > 0 || expenses.length > 0){
		addTabs("trade_tabs", "trade_cons", {"expenses":"费用详情"});
		$("#expenses_display").append($div);
		var f = $.extend(true, {}, tableForm);	
		f.source.on = false;
		f.source.value = expenses.data;
		f.form.name = "tradeExpense_table";
		f.form.func = function(data){
			$.each(data, function(i, v){
				var id = v[0];
				var dataFor = "tradeExpense";
				var $exp = $("#" + dataFor + "_" + id + "_expense");
				updateSelectOptions($exp, expTypes);
				$exp.val(v[1]);
				portCashAccount("port", dataFor + "_" + id + "_bankAccount", v[8]);
				defaultFxRate2("tradeDate", "port", dataFor + "_" + id + "_tableFeeCurrency", dataFor + "_" + id + "_fxRate", v[4]);
				settleCurrency(dataFor + "_" + id + "_bankAccount", dataFor + "_" + id + "_tableFeeCurrency", v[3]);
				expenseSettleDate("settleDate");
				updateTotalExpense("totalExpense", "basic_a", "expenses_display");

				$exp.on("change", function(){
					var expVal = fetch(getURL("checkExpenseType"), {id: $(this).val()}, "trade_alert");
					updateExpense(expVal, id);
				})
			});
		};
		f.event.update.on = false;
		f.event.update.source.address = "tradeUpdate";
		f.event.del.source.address = "dummy";
		f.event.del.source.func = function(id, dataFor){
			totalExpense("totalExpense", "expenses_display");
		}
		f.event.add.form.name = "tradeExpense_add";
		f.event.add.source.address = "tradeUpdate";
		f.event.add.source.func = function(id, dataFor){
			var $exp = $("#" + dataFor + "_" + id + "_expense");
			updateSelectOptions($exp, expTypes);
			portCashAccount("port", dataFor + "_" + id + "_bankAccount");
			settleCurrency(dataFor + "_" + id + "_bankAccount", dataFor + "_" + id + "_tableFeeCurrency");
			defaultFxRate2("tradeDate", "port", dataFor + "_" + id + "_tableFeeCurrency", dataFor + "_" + id + "_fxRate");
			expenseSettleDate("settleDate");

			updateTotalExpense("totalExpense", "basic_a", "expenses_display");
			
			$exp.on("change", function(){
				var expVal = fetch(getURL("checkExpenseType"), {id: $(this).val()}, "trade_alert");
				updateExpense(expVal, id);
			})
		};
		genTableForm("expense_div", "tradeExpense", f.source, f.form, f.event, "trade_alert", true);
		$("#tradeExpense_add").text("增加费用");
		$("#tradeExpense_add").appendTo($title);
		$("[data-for='tradeExpense_add']").prop("disabled", true);
		$("[data-for='tradeExpense_delete']").prop("disabled", true);
	}
}

function getExpenses(){
	var expenses = [];
	var exps = [];
	var status = true;
	$.each($("[data-for='tradeExpense_tr']"), function(index, tr){
		var expID = $(tr).attr("id");
		var expList = [];
		var expMap = {};
		expMap.id = expID;
		$.each($("[data-for='tradeExpense_" + expID + "']"), function(index, exp){
			var v = $(exp).val();
			if (typeof(v) == "undefined" || v == ""){
				status = false;
			}
			else{
				expList.push($(exp).val());
				expMap[$(exp).attr("name").split("_")[2]] = $(exp).val();
			}
		});
		expenses.push(expMap);
		exps.push(expList);
	});
	return {status:status, expenses:expenses, exps:exps};
}

function getCollaterals(){
	var colls = [];
	var collaterals = [];
	var status = true;
	$.each($("[data-for='tradeCollateral_tr']"), function(index, tr){
		var collID = $(tr).attr("id");
		var collList = [];
		var collMap = {};
		collMap.id = collID;
		$.each($("[data-for='tradeCollateral_" + collID + "']"), function(index, coll){
			var v = $(coll).val();
			if (typeof(v) == "undefined" || v == ""){
				status = false;
			}
			else{
				collList.push($(coll).val());
				collMap[$(coll).attr("name").split("_")[2]] = $(coll).val();
			}
		});
		colls.push(collList);
		collaterals.push(collMap);
	});
	return {status:status, colls:colls, collaterals:collaterals};
}

function checkExpenseSaved(){
	if ($("[data-for='tradeExpense_update']").text().indexOf("保存") < 0){
		return true;
	}
	else{
		return false;
	}
}

function checkCollateralSaved(){
	if ($("[data-for='tradeCollateral_update']").text().indexOf("保存") < 0){
		return true;
	}
	else{
		return false;
	}
}