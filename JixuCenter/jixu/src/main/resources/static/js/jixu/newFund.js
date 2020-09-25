var PORT = {};
var INTS = {};
var BANKACCTS = {};
var SECACCTS = {};
var EXPENSES = {};
var INTERFACES = {};
var COA = {};

//Function for NEW_FUND
function drawPortConfig(target_id, client, data){
	var options = {};
	options.target = "portPane";
	options.tp = "port";
	options.form = "port";
	options.value = data;
	options.inputs = {};
	options.inputs["mother"] = {client:client};
	options.formFunc = function(data){
		updateSelectOptions($("#port_glgroup"), fetch(getURL("glListInCOA"), {coaID:$("#port_coa").val()}));
		COA = fetch(getURL("acctListInCOA"), {coaID:$("#port_coa").val()});
		if(data && data[11]){
			$("#port_glgroup").val(data[11]);
		}
		$("#port_coa").on("change", function(){
     		var ops = fetch(getURL("glListInCOA"), {coaID:$(this).val()});
     		updateSelectOptions($("#port_glgroup"), ops);
     		COA = fetch(getURL("acctListInCOA"), {coaID:$(this).val()});
     	});
	};
	options.alert = "alertAll";
	drawConfigEditForm(options);
}

function drawInterestConfig(target_id, data){
	var options = {};
	options.target = target_id;
	options.tp = "interest";
	options.value = [];
	$.each(data, function(id, d){
		if(d && d.info){
			options.value.push(d.info);
		}
	});
	options.form = "bankInterest_table"; 
	options.formFunc = function(data){
		$.each(data, function(i, v){
			var id = v[0];
			var dataFor = "interest";
			var $month = $("#" + dataFor + "_" + id + "_month");
			var $freq = $("#" + dataFor + "_" + id + "_frequency");
			updateSelectOptions($month, fetch(getURL("interestMonth"), {freq:parseInt($freq.val())}));
			$month.val(v[8]);
			$freq.on("change", function(){
				var v = $(this).val();
				updateSelectOptions($month, fetch(getURL("interestMonth"), {freq:parseInt(v)}));
			});
			var $btd = $("#" + id).find("td").last();
			var $bt = $("<button  class='btn btn-success' data-for='interest_rate'>利率</button>");
			$bt.attr("id", dataFor + "_" + id + "_rate");
			$btd.append($bt);
		});
		$(document).on("click", "button[data-for='interest_rate']", function(){
			var id = $(this).attr("id").split("_")[1];
			rateDetail(id);
			return false;
		});
	};
	
	options.addFunc = function(id, dataFor){
		var $btd = $("#" + id).find("td").last();
		var $bt = $("<button  class='btn btn-success' data-for='interest_rate'>利率</button>");
		$bt.attr("id", dataFor + "_" + id + "_rate");
		$btd.append($bt);
		var $month = $("#" + dataFor + "_" + id + "_month");
		var $freq = $("#" +dataFor + "_" + id + "_frequency");
		updateSelectOptions($month, fetch(getURL("interestMonth"), {freq:parseInt($freq.val())}));
		$freq.on("change", function(){
			var v = $(this).val();
			updateSelectOptions($month, fetch(getURL("interestMonth"), {freq:parseInt(v)}));
		});
		INTS[id] = {};
	};
	
	options.delFunc = function(id, dataFor){
		delete INTS[id];
	};
	options.alert = "alertAll";
	drawConfigTableForm(options);
}

function drawSecAcctConfig(target_id, data){
	var options = {};
	options.target = target_id;
	options.tp = "secAcct";
	options.value = [];
	
	$.each(data, function(id, d){
		if(d && d.info){
			options.value.push(d.info);
		}
	});
	options.form = "secAcct_table";
	options.formFunc = function(data){
		$.each(data, function(i, v){
			var id = v[0];
			var dataFor = "secAcct";
			var banks = {};
			$.each(BANKACCTS, function(k, acct){
				if(acct && acct.info){
					banks[acct.info[0]] = acct.info[1];
				}
			});
			
			var $actSelect = $("#" + dataFor + "_" + id + "_bankAcct");
			$actSelect.prop("multiple", "multiple");
			updateSelectOptions($actSelect, banks);
			$actSelect.val('');
			$actSelect.multiselect();
			$actSelect.multiselect('select', v[4]);
		});
	}
	options.addFunc = function(id, dataFor){
		SECACCTS[id] = {};
		var $actSelect = $("#" + dataFor + "_" + id + "_bankAcct");
		var banks = {};
		$.each(BANKACCTS, function(k, acct){
			if(acct && acct.info){
				banks[acct.info[0]] = acct.info[1];
			}
		});
		$actSelect.attr("multiple", "multiple");
		updateSelectOptions($actSelect, banks);
		$actSelect.val('');
		$actSelect.multiselect();
		//$actSelect.multiselect('select', []);
	};
	options.delFunc = function(id, dataFor){
		delete SECACCTS[id];
	};
	options.alert = "alertAll";
	drawConfigTableForm(options);
}
function drawBankAcctConfig(target_id, data){
	var options = {};
	options.target = target_id;
	options.tp = "bankAccount";
	options.value = [];
	
	$.each(data, function(id, d){
		if(d && d.info){
			options.value.push(d.info);
		}
	});
	options.form = "bankAccount_table";
	
	options.formFunc = function(data){
		$.each(data, function(i, v){
			var id = v[0];
			var dataFor = "bankAccount";
			var interests = {};
			$.each(INTS, function(k, interest){
				if(interest && interest.info){
					interests[interest.info[0]] = interest.info[1];
				}
			});
			interests[0] = "无利息";
			updateSelectOptions($("#" + dataFor + "_" + id + "_interest"), interests);
			$("#" + dataFor + "_" + id + "_interest").val(v[6]);
			var $btd = $("#" + id).find("td").last();
			var $bt = $("<button  class='btn btn-success' data-for='acct_map'>科目映射</button>");
			$bt.attr("id", dataFor + "_" + id + "_map");
			$btd.append($bt);
		});
		$(document).on("click", "button[data-for='acct_map']", function(){
			var id = $(this).attr("id").split("_")[1];
			mapDetail(id);
			return false;
		});
	};
	
	options.addFunc = function(id, dataFor){
		var $btd = $("#" + id).find("td").last();
		var $bt = $("<button  class='btn btn-success' data-for='acct_map'>科目映射</button>");
		$bt.attr("id", dataFor + "_" + id + "_map");
		$btd.append($bt);
		
		var interests = {};
		$.each(INTS, function(k, interest){
			if(interest && interest.info){
				interests[interest.info[0]] = interest.info[1];
			}
		});
		interests[0] = "无利息";
		updateSelectOptions($("#" + dataFor + "_" + id + "_interest"), interests);
		
		BANKACCTS[id] = {};
	};
	
	options.delFunc = function(id, dataFor){
		delete BANKACCTS[id];
	};
	options.alert = "alertAll";
	drawConfigTableForm(options);
}

function drawInterfaceConfig(target_id, data){
	var options = {};
	options.target = target_id;
	options.tp = "portInterface";
	options.value = [];
	
	$.each(data, function(id, d){
		if(d && d.info){
			options.value.push(d.info);
		}	
	});
	options.form = "portInterface_table2";
	
	var func = function(id, dataFor, v){
		$config = $("#" + dataFor + "_" + id + "_config");
		$type = $("#" + dataFor + "_" + id + "_type");
		$asset = $("#" + dataFor + "_" + id + "_asset");
		
		updateSelectOptions($config, fetch(getURL("importConfigList"), {category: $type.val(), type: ALL}));
		var assets = {0:"全部"};
		if ($type.val() == price || $type.val() == fxRate || $type.val() == tran || $type.val() == corpAction){
			assets = fetch(getURL("assets"), {});
		}
		updateSelectOptions($config, fetch(getURL("importConfigList"), {category: $type.val(), type: ALL}));
		updateSelectOptions($asset, assets);
		if(v && v[1]){
			$config.val(v[1]);
		}
		if(v && v[2]){
			$asset.val(v[2]);
		}
		$type.on("change", function(){
			updateSelectOptions($config, fetch(getURL("importConfigList"), {portfolios: "all", category: $type.val(), type: ALL}));
			var assets = {0:"全部"};
			if ($type.val() == price || $type.val() == fxRate || $type.val() == tran || $type.val() == corpAction){
				assets = fetch(getURL("assets"), {});
			}
			updateSelectOptions($asset, assets);
		});
	}
	
	options.formFunc = function(data){
		$.each(data, function(i, v){
			var id = v[0];
			var dataFor = "portInterface";
			func(id, dataFor,v);
		});
	};
	
	options.addFunc = function(id, dataFor){
		func(id, dataFor);
		INTERFACES[id] = {};
	};
	
	options.delFunc = function(id, dataFor){
		delete INTERFACES[id];
	};
	options.alert = "alertAll";
	drawConfigTableForm(options);
}

function drawPortExpenseConfig(target_id, data){
	var $target = $("#" + target_id);
	
	var t = $.extend(true, {}, checkTable);
	var result = {};
	result["data"] = [];
	result.titles = ["编号", "类型", "描述", "费用基础", "借方科目", "贷方科目","金额", "费率"];
	$.each(data, function(id, d){
		result["data"].push(d.basicInfo);
	});
	$target.empty();
	var dataFor = "portExpense";
	drawTableWithCheck(target_id, dataFor, result, ["del", "add"], false);
	
	var save_func = function(id){
		var $footer = $("#expense_modal .modal-footer");
		$footer.find("button").remove();
		var $btn = $("<button class='btn btn-success'>保存<button>");
		$footer.append($btn);
		$footer.find("button").eq(1).addClass("btn btn-default").text("关闭");
		$btn.on('click', function(){
			var result = loadEditForm("portExpense", id, "portExpense");
			if (result){
				EXPENSES[id]["info"] = result;
				EXPENSES[id]["basicInfo"] = loadEditFormValue("portExpense", id, "portExpense");
				$("#alertAll").empty();
				$("#alertAll").append(genAlert("success", "投资组合费用创建成功!"));
				$("#expense_modal").modal('hide');
				drawPortExpenseConfig("expensePane",EXPENSES);
			}
			else{
				$("#expense_alert").empty();
				$("#expense_alert").append(genAlert("fail", "仍有信息没有填写完整，无法完成!"));
			}
		});
	}
	
	var create_func = function(id){
		var options = {};
		options.target = "expense_detail";
		options.tp = "portExpense";
		options.form = "portExpense";
		
		options.value = EXPENSES[id].info;
					
		options.inputs = {};
		options.formFunc = function(data){
			$("#portExpense_debitAcct").typeahead({source:COA});
			$("#portExpense_creditAcct").typeahead({source:COA});
			
			updateSelectOptions($("#portExpense_month"), fetch(getURL("expenseMonth"), {freq:$("#portExpense_frequency").val()}));
			if(data && data[12]){
				$("#portExpense_month").val(data[12]);
			}
 			$("#portExpense_frequency").on("change", function(){
    			var v = $(this).val();
    			updateSelectOptions($("#portExpense_month"), fetch(getURL("expenseMonth"), {freq:v}));
			});
		};
		options.alert = "expense_alert";
		drawConfigEditForm(options);
		$("#expense_title").text("投资组合费用配置");
		save_func(id);
		$("#expense_modal").modal();
	}
	
	$("button[data-for='" + dataFor + "_delete']").on("click", function(){	
		var id = $(this).attr("id").split("_")[1];
		if (confirm("是否确认删除?")){
    		var $btn = $(this);
    		$btn.closest("tr").remove();
    		delete EXPENSES[id];
		}
	
		return false;
	});
	
	$("tr[data-for='" + dataFor + "'] td").on("dblclick", function(){
			var id = $(this).parent().attr("id");
			create_func(id);
	});
	
	$("#" + dataFor + "_add").on("click", function(){
		var id = "TEM" + new Date().valueOf();
		EXPENSES[id] = {};
		EXPENSES[id].info = [id];
		EXPENSES[id].basicInfo = [id];
		EXPENSES[id].basicTitle = ["编号", "类型", "描述", "费用基础", "借方科目", "贷方科目","金额", "费率"];
		create_func(id);
	});
}

function mapDetail(id){
	var options = {};
	options.tp = "map";
	options.label = "科目映射";
	
	if (BANKACCTS[id]["mapping"]){
		options.value = BANKACCTS[id].mapping;	
	}
	else {
		options.value = [];
	}
	options.form = "bankCOA_table2";
	options.formFunc = function(data){
		$.each(data, function(i, v){
			var id = v[0];
			var dataFor = "map";
			$("#" + dataFor + "_" + id + "_acct").typeahead({source:COA});  
		});
	}

	options.addFunc = function(id, dataFor){
		$("#" + dataFor + "_" + id + "_acct").typeahead({source:COA});  
	}

	options.saveFunc = function (e) {
		var result = loadTableForm("form_map_div");
		if (result){
			BANKACCTS[id]["mapping"] = result;
			$("#alertAll").empty();
			$("#alertAll").append(genAlert("success", "科目映射创建成功!"));
			$("#map_modal").modal('hide');
		}
		else{
			$("#map_alert").empty();
			$("#map_alert").append(genAlert("fail", "仍有信息没有填写完整，无法完成!"));
		}
		
		return false;
	}
	options.alert = "map_alert";
	drawConfigTableDetail(options);
}

function rateDetail(id){
	var options = {};
	options.tp = "rate";
	options.label = "利率配置";
	
	if (INTS[id]["rates"]){
		options.value = INTS[id].rates;	
	}
	else{
		options.value = [];
	}
	options.form = "rate_table2";
	options.saveFunc = function (e) {
		var result = loadTableForm("form_rate_div");
		if (result){
			INTS[id]["rates"] = result;
			$("#alertAll").empty();
			$("#alertAll").append(genAlert("success", "利率创建成功!"));
			$("#rate_modal").modal('hide');
		}
		else{
			$("#rate_alert").empty();
			$("#rate_alert").append(genAlert("fail", "仍有信息没有填写完整，无法完成!"));
		}
		return false;
	}
	options.alert = "rate_alert";
	drawConfigTableDetail(options);
}