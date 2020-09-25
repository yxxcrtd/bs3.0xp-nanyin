function drawTradeFilterForm(target_id, dataFor, data, inputs, addList){
	var $target = $("#" + target_id);
	$target.append("<br/>");
	var $form = $("<form class='form-inline'></form>");
	$form.attr("id", dataFor + "_filter");
	var rule = {};
	
	for (var i = 0; i < data.length; i++){
		var label = data[i].label;
		var category = data[i].category;
		var value = data[i].value;
		var type = data[i].type;
		
		var dom_id = dataFor + "_" + category + "_filter";
		
		var r = data[i].rules;
		if (typeof(r) != "undefined" && r !="" && Object.keys(r).length > 0){
			rule[dom_id] = r;
		}
		
		var $form_group = $("<div class='form-group'></div>");
		$form_group.append("&nbsp;&nbsp;");
		
		if (type == "input") {
			var $label = $("<label for='" + dom_id + "'>" + label + "</label>");
			$form_group.append($label);
			$form_group.append("&nbsp;&nbsp;");
			$form_group.append(genInput(dom_id, dataFor + "_filter", value, false));
			$form.append($form_group);
		}
		else if (type == "select") {
			var $label = $("<label for='" + dom_id + "'>" + label + "</label>");
			$form_group.append($label);
			$form_group.append("&nbsp;&nbsp;");
			var options;
			if (data[i].options.type == "fetch"){
				if (typeof(data[i].options.inputs) != "undefined"){
					options = fetch(getURL(data[i].options.url), data[i].options.inputs);
				}
				else {
					options = fetch(getURL(data[i].options.url), {});
				}
			}
			else if (data[i].options.type == "function"){
				var inps = data[i].options.inputs;
				$.each(inps, function(k,v){
					if (typeof(v) == "string"){
						inps[k] = inputs[v];
					}
				});
				options = fetch(getURL(data[i].options.url), inps);
			}
			else{
				options = data[i].options.values;
			}
			$form_group.append("&nbsp;&nbsp;");
			$form_group.append(genSelect(dom_id, dataFor + "_filter", options, value, false));
			$form.append($form_group);
		}
		else if (type == "auto") {
			var $label = $("<label for='" + dom_id + "'>" + label + "</label>");
			$form_group.append($label);
			$form_group.append("&nbsp;&nbsp;");
			var options;
			if (data[i].options.type == "fetch"){
				options = fetch(getURL(data[i].options.url), "");
			}
			else if (data[i].options.type == "function"){
				var inps = data[i].options.inputs;
				$.each(inps, function(k,v){
					if (typeof(v) == "string"){
						inps[k] = inputs[v];
					}
				});
				options = fetch(getURL(data[i].options.url), inps);
			}
			else{
				options = data[i].options.values;
			}
			$form_group.append("&nbsp;&nbsp;");
			$form_group.append(genAuto(dom_id, dataFor + "_filter", options, value, false));
			$form.append($form_group);
		}
		else if (type == "button"){
			if (category == "create"){
				var url = data[i].url;
				if (typeof(url) != "undefined"){
					var checkURL = getURL(url).replace(prefix, "");
					var checkResult = fetch(getURL(verifyPermFunc), {checkURL:checkURL});
					var flag = checkResult["status"];
					if (flag){
						$form.append("&nbsp;&nbsp;");
						$form.append(genButton(dataFor + "_" + category, data[i].class, label));
					}
				}
				else {
					$form.append("&nbsp;&nbsp;");
					$form.append(genButton(dataFor + "_" + category, data[i].class, label));
				}
			}
			else {
				$form.append("&nbsp;&nbsp;");
				$form.append(genButton(dataFor + "_" + category, data[i].class, label));
			}
		}
		
		else if (type == "file"){
			$form.append("&nbsp;&nbsp;");
			$form.append(genFileUpload(dom_id, dataFor + "_filter", data[i].class, label));
		}
		else if (type == "date"){
			var $label = $("<label for='" + dom_id + "'>" + label + "</label>");
			$form_group.append($label);
			$form_group.append("&nbsp;&nbsp;");
			$form_group.append(genDateInput(dom_id, dataFor + "_filter", value, false));
			$form.append($form_group);
		}
	}
	var $target = $("#" + target_id);
	$target.append($form);
	$target.append("<br/>");
	$form.validate({rules:rule, errorClass: "form-error"});
	if (addList){
		$div = $("<div id='" + dataFor + "_list'></div>");
		$target.append($div);
	}	
}

var tradeEditForm = {
		source : {
			on:true,
			address:"",
			inputs:{},
			value:[]
		},
		form : {
			form: "",
			inputs: {},
			func: ""
		},

		event : {
			create:{
				on:false,
				source:{
					address:"",
					inputs: {},
					func:""
				}
			},
			update:{
				on:false,
				source:{
					address:"",
					inputs: {},
					func:""
				}
			}
		}
}

function genTradeEditForm(target_id, dataFor, source, form, event, num_in_line, alert, disabled){
	//处理数据源和表格内容，并将表格画到界面上；
	if (typeof(source["on"])=="undefined" || source["on"]){
		invoke(getURL(source.address), source.inputs, function(data){
			drawTradeEditForm(target_id, dataFor, form.form, form.inputs, data.data, num_in_line, disabled);
			setDatePick();
			if (typeof(event.update) != "undefined"){
				attachUpdate();
			}
			if (typeof(event.create) != "undefined"){
				attachCreate();
			}
			if (typeof(form.func) == "object"){
				var funcBody = "";
				$.each(form.func, function(i,str){
					funcBody = funcBody + str + ";"; 
				});
				var formFunc = function(d){eval(funcBody)};
				formFunc(data);
			}
		}, alert);
	}
	else {
		drawTradeEditForm(target_id, dataFor, form.form, form.inputs, source.value, num_in_line, disabled);
		setDatePick()
		if (typeof(event.update) != "undefined"){
			attachUpdate();
		}
		if (typeof(event.create) != "undefined"){
			attachCreate();
		}
		if (typeof(form.func) == "object"){
			var funcBody = "";
			$.each(form.func, function(i,str){
				funcBody = funcBody + str + ";"; 
			});
			var formFunc = function(d){eval(funcBody)};
			formFunc(source.value);
		}
	}
	function attachUpdate(){
		$("#" + dataFor + "_update").off("click").on("click", function(){
				if (typeof(event.update["on"]) == "undefined" || event.update["on"]){
					if ($(this).text() == "保存"){
						if ($("#" + dataFor).valid()){
							if (confirm("是否确认保存?")){
								var btn = $(this).button('操作中...')
				     			var params = [];
								var param_names = [];
								var json = {};
					 			json.header = form.inputs.messageHeader || dataFor + "Update";
					 			json.id = event.update.source.inputs.id;
					 			json.operation = "UPDATE";
				     			$.each($("[data-for='" + dataFor + "']"), function(index, value){
				     				if ($(value).prop("type") == "checkbox"){
				     					params.push($(value).is(":checked"));
				     					json[$(value).attr("name")] = $(value).is(":checked");
				     				}
				     				else if ($(value).prop("type") == "checkboxes"){
				     					var l = [];
				     					$.each($(value).find("input"), function(i, v){
				     						if ($(v).prop("type") == "checkbox"){
				     							if($(v).is(":checked")){
				     								l.push($(v).attr("value"));
				     							}
				     						}
				     					});
				     					params.push(l);
				     					json["checkboxes"] = l;
				     				}
				     				else {
				     					params.push($(value).val());
				     					if($(value).attr("name")){
				     						json[$(value).attr("name")] = $(value).val();
				     					}
				     				}
				     				if($(value).attr("name")){
			     						param_names.push($(value).attr("name"));
			     					}
				     			});
				     			event.update.source.inputs["params"] = JSON.stringify(params);
				     			event.update.source.inputs["json"] = JSON.stringify(json);
				     			logInfo(event.update.source.address, event.update.source.inputs, param_names);
				     			invoke(getURL(event.update.source.address),event.update.source.inputs, function(data){
				     				$("#" + alert).empty();
			 	                	$("#" + alert).append(genAlert("success", translate[dataFor] + "保存成功!"));
			 	                	$.each($("[data-for='" + dataFor + "']"), function(i, v){
			 	                		if ($(v).prop("type") == "checkboxes"){
			 	                			$(v).find("input").prop("disabled", true);
			 	                		}
			 	                		else{
			 	                			$(v).prop("disabled", true);
			 	                		}
			 	                	});
			 	             
			 	                	$(btn).attr("class", "btn btn-primary");
			 	                	$(btn).text("更新");
			 	                	
			 	                	if (typeof(event.update.source.func) == "function"){
			 	                		event.update.source.func(event.update.source.inputs.id, dataFor);
			 	                	}
				     			},alert);
							}	
						}
					}
					else {
						var checkURL = getURL(event.update.source.address).replace(prefix, "");
						var checkResult = fetch(getURL(verifyPermFunc), {checkURL:checkURL});
						var flag = checkResult["status"];
						var urlName = checkResult["urlName"];
						if (flag){
							$("#" + alert).empty();
							$.each($("[data-for='" + dataFor + "']"), function(index, value){
								if ($(value).prop("editable") == false){
									if ($(value).prop("type") == "checkboxes"){
										$(value).find("input").prop("disabled", true);
									}
									else{
										$(value).prop("disabled", true);
									}
								}
								else {
									if ($(value).prop("type") == "checkboxes"){
										$(value).find("input").prop("disabled", false);
									}
									else{
										$(value).prop("disabled", false);
									}	
								}
							});
							
							$(this).attr("class", "btn btn-success")
							$(this).text("保存");
							if (typeof(event.update.source.func) == "function"){
								event.update.source.func(event.update.source.inputs.id, dataFor);
							}
						}
						else {
							$("#" + alert).empty();
							$("#" + alert).append(genAlert("fail", "没有权限调用 " + urlName));
						}
					}
				}
				else{
					if (typeof(event.update.source.func) == "function"){
						var checkURL = getURL(event.update.source.address).replace(prefix, "");
						var checkResult = fetch(getURL(verifyPermFunc), {checkURL:checkURL});
						var flag = checkResult["status"];
						var urlName = checkResult["urlName"];
						if (flag){
							event.update.source.func(event.update.source.inputs.id, dataFor);
						}
						else {
							$("#" + alert).empty();
							$("#" + alert).append(genAlert("fail", "没有权限调用 " + urlName));
						}
					}
				}
			
			return false;	
		});
	}
	function attachCreate(){
		$("#" + dataFor + "_create").off("click").on("click", function(){
			if (typeof(event.create["on"]) == "undefined" || event.create["on"]){
				if ($("#" + dataFor).valid()){
					if (confirm("是否确认创建?")){
						var btn = $(this).button('操作中...')
		     			var params = [];
						var param_names = [];
						var json = {};
			 			json.header = form.inputs.messageHeader || dataFor + "Create";
			 			json.operation = "ADD";
			 			if (event.create.source.inputs.id){
			 				json.id = event.create.source.inputs.id;
			 			}
			 			 
		     			$.each($("[data-for='" + dataFor + "']"), function(index, value){
		     				if ($(value).prop("type") == "checkbox"){
		     					params.push($(value).is(":checked"));
		     					json[$(value).attr("name")] = $(value).is(":checked");
		     				}
		     				else if ($(value).prop("type") == "checkboxes"){
		     					var l = [];
		     					$.each($(value).find("input"), function(i, v){
		     						if ($(v).prop("type") == "checkbox"){
		     							if($(v).is(":checked")){
		     								l.push($(v).attr("value"));
		     							}
		     						}
		     					});
		     					params.push(l);
		     					json["checkboxes"] = l;
		     				}
		     				else {
		     					params.push($(value).val());
		     					if($(value).attr("name")){
		     						json[$(value).attr("name")] = $(value).val();
		     					}
		     				}
		     				if($(value).attr("name")){
	     						param_names.push($(value).attr("name"));
	     					}
		     			});
		     			event.create.source.inputs["params"] = JSON.stringify(params);
		     			event.create.source.inputs["json"] = JSON.stringify(json);
		     			logInfo(event.create.source.address, event.create.source.inputs, param_names);
		     			invoke(getURL(event.create.source.address),event.create.source.inputs, function(data){
		     				var new_id = data;
		                	$("#" + alert).empty();
		                	$.each($("[data-for='" + dataFor + "']"), function(i, v){
	 	                		if ($(v).prop("type") == "checkboxes"){
	 	                			$(v).find("input").prop("disabled", true);
	 	                		}
	 	                		else{
	 	                			$(v).prop("disabled", true);
	 	                		}
	 	                	});
		                	$("#" + alert).append(genAlert("success", translate[dataFor] + "创建成功!"));
		         			$(btn).prop("disabled", true);
		         			//$("#" + dataFor + "_search_btn").trigger("click");
		         			if (typeof(event.create.source.func) == "function"){
		    					event.create.source.func(dataFor, new_id);
		    				}
		     			},alert);
					}	
				}
			}
			else{
				if (typeof(event.create.source.func) == "function"){
					event.create.source.func(dataFor);
				}
			}
			return false;
		});
	}
}

function drawTradeEditForm(target_id, dataFor, form_data, inputs, values, num_in_line, disabled){
	$form = $("<form class='form-horizontal' id='" + dataFor + "'></form>");
	$checkboxes = $("<div id='checkboxes'></div>");
	var forms = form_data.forms;
	var sets = form_data.sets;
	var buttons = form_data.buttons;
	var rule = {};
	
	if (typeof(sets) == "undefined"){
		sets = [];
		sets.push({legend:"EMPTY", items:Object.keys(forms)});
	}
	$.each(sets, function(index, s){
		var $field = $("<fieldset class='myfield'></fieldset>");
		$legend = $("<legend></legend>");
		$legend.text(s.legend);
		$field.append($legend);
		
		var $form_group = $("<div class='form-group'></div>");
		var nums = s.len || num_in_line;
		
		$.each(s.items, function(index, i){
			if (values instanceof Array){
				if (values.length > 0){
					if (typeof(values[i])!= "undefined"){
						forms[i].value = values[i];
					}
				}
				else{
					if (typeof(forms[i].value) == "string" && (forms[i].value != "")){
						forms[i].value = inputs[forms[i].value];
					}
				}
			}
			else if(typeof(values) == "object") {
				if (typeof(values[forms[i].cat])!= "undefined"){
					forms[i].value = values[forms[i].cat];
				}
				else{
					if (typeof(forms[i].value) == "string" && (forms[i].value != "")){
						forms[i].value = inputs[forms[i].value];
					}
				}
			}
			else{
				if (typeof(forms[i].value) == "string" && (forms[i].value != "")){
					forms[i].value = inputs[forms[i].value];
					console.log(forms[i].value);
				}
			}
			
			var dom_id = forms[i].cat;
			var r = forms[i].rules;
			if (typeof(r) != "undefined" && r !="" && Object.keys(r).length > 0){
				rule[dom_id] = r;
			}
			if (index % nums == 0){
				$form_group = $("<div class='form-group'></div>");
			}
			var ed = true;
			if (typeof(forms[i].editable) != "undefined"){
				ed = forms[i].editable;
			}
			var $div = $("<div class='col-md-2'></div>");
			
			var dis = disabled;
			if (typeof(forms[i].editable) == "boolean"){
				dis = ! forms[i].editable;
			}
			if (forms[i].type == "input") {
				$form_group.append("<label for='" + dom_id + "' class='col-md-1 control-label'>" + forms[i].label + "</label>");
				var $input = genInput(dom_id, dataFor, forms[i].value, dis, ed);
				$div.append($input);
			} 
			else if (forms[i].type == "pwd"){
				$form_group.append("<label for='" + dom_id + "' class='col-md-1 control-label'>" + forms[i].label + "</label>");
				var $input = genPwd(dom_id, dataFor, forms[i].value, dis, ed);
				$div.append($input);
			}
			else if (forms[i].type == "select") {
				$form_group.append("<label for='" + dom_id + "' class='col-md-1 control-label'>" + forms[i].label + "</label>");
				var options;
				
				if (forms[i].options.type == "fetch"){
					if (typeof(forms[i].options.inputs) != "undefined"){
						options = fetch(getURL(forms[i].options.url), forms[i].options.inputs);
					}
					else {
						options = fetch(getURL(forms[i].options.url), {});
					}
				}
				else if (forms[i].options.type == "function"){
					var inps = forms[i].options.inputs;
					$.each(inps, function(k,v){
						if (typeof(v) == "string"){
							inps[k] = inputs[v];
						}
					});
					options = fetch(getURL(forms[i].options.url), inps);
				}
				else{
					options = forms[i].options.values;
				}
				$select = genSelect(dom_id, dataFor, options, forms[i].value, dis, ed);
				$div.append($select);
			}
			else if (forms[i].type == "auto") {
				$form_group.append("<label for='" + dom_id + "' class='col-md-1 control-label'>" + forms[i].label + "</label>");
				var options;
				
				if (forms[i].options.type == "fetch"){
					options = fetch(getURL(forms[i].options.url), "");
				}
				else if (forms[i].options.type == "function"){
					var inps = forms[i].options.inputs;
					$.each(inps, function(k,v){
						if (typeof(v) == "string"){
							inps[k] = inputs[v];
						}
					});
					options = fetch(getURL(forms[i].options.url), inps);
				}
				else{
					options = [];
				}
				
				$auto = genAuto(dom_id, dataFor, options, forms[i].value, dis, ed);
				$div.append($auto);
			}
			else if (forms[i].type == "date") {
				$form_group.append("<label for='" + dom_id + "' class='col-md-1 control-label'>" + forms[i].label + "</label>");
				var $input = genDateInput(dom_id, dataFor, forms[i].value, dis, ed);
				$div.append($input);
			}
			else if (forms[i].type == "label") {
				$form_group.append("<label for='" + dom_id + "' class='col-md-1 control-label'>" + forms[i].label + "</label>");
				$div.append("<label id='" + dom_id + "' control-label' text-left>" + forms[i].value + "</label>");
			}
			else if (forms[i].type == "checkbox") {
				$form_group.append("<label for='" + dom_id + "' class='col-md-1 control-label'>" + forms[i].label + "</label>");
				var $check = genCheckbox(dom_id, dataFor, forms[i].value, dis, ed);
				$div.append($check);
			}
			else if (forms[i].type == "a") {
				$form_group.append("<label for='" + dom_id + "' class='col-md-1 control-label'>" + forms[i].label + "</label>");
				var $a = genLink(dom_id, dataFor, forms[i].value, dis, ed);
				$div.append($a);
			}
			else if (forms[i].type == "checkboxes") {
				var dom_id = dataFor + "_" + forms[i].cat;
				$checkboxes.append("<label for='" + dom_id + "' class='col-md-1'>" + forms[i].label + "</label>");
				var options;
				
				if (forms[i].options.type == "fetch"){
					if (typeof(forms[i].options.inputs) != "undefined"){
						options = fetch(getURL(forms[i].options.url), forms[i].options.inputs);
					}
					else {
						options = fetch(getURL(forms[i].options.url), {});
					}
				}
				else if (forms[i].options.type == "function"){
					var inps = forms[i].options.inputs;
					$.each(inps, function(k,v){
						if (typeof(v) == "string"){
							inps[k] = inputs[v];
						}
					});
					options = fetch(getURL(forms[i].options.url), inps);
				}
				else{
					options = forms[i].options.values;
				}
				var $boxes = genCheckboxes(dom_id, dataFor, options, forms[i].value, dis, ed);
				$checkboxes.append($boxes);
			}
			$form_group.append($div);
			if (s.legend === "EMPTY"){
				$form.append($form_group);
			}
			else{
				$field.append($form_group);
			}
		});
		if (s.legend != "EMPTY"){
			$form.append($field);
		}
	});
	
	$form.append($checkboxes);
	var $form_group = $("<div class='form-group'></div>");
	var $div = $("<div class='col-md-offset-10 col-md-2'></div>")
	for (var i = 0; i < buttons.length; i++){
		if (buttons[i].type == "update"){
			$div.append("<button id='" + dataFor + "_update' class='btn btn-primary'>" + buttons[i].label +"</button>");
		}
		else if (buttons[i].type == "create"){
			$div.append("<button id='" + dataFor + "_create' class='btn btn-success'>" + buttons[i].label +"</button>");
		}
		else if (buttons[i].type == "delete"){
			$div.append("<button id='" + dataFor + "_del' class='btn btn-danger'>" + buttons[i].label +"</button>");
		}
		$div.append("&nbsp;&nbsp;")
	}
    $form_group.append($div);
    $form.append($form_group);
    
	$("#" + target_id).append("</br>")
	$("#" + target_id).append($form);

	$form.validate({rules:rule, errorClass: "form-error"});
}

//通过porfolio得到证券账户
function secAcct(port, secAcct_id, value){
	var $secAcct = $("#" + secAcct_id);
	updateSelectOptions($secAcct, fetch(getURL("secAcctListInPort"), {pid:port}));
	if (typeof(value) != "undefined"){
		$secAcct.val(value);
	}
}

function secAccount(port_id, secAcct_id){
	var $port = $("#" + port_id);
	var $secAcct = $("#" + secAcct_id);
	var secAcct = $secAcct.val();
	
	updateSelectOptions($secAcct, fetch(getURL("secAcctListInPort"), {pid: $port.val()}));
	if(secAcct){
		$secAcct.val(secAcct);
		$secAcct.change();
	}
	$port.off("change");
	$port.on("change", function(){
		var accounts = fetch(getURL("secAcctListInPort"), {pid: $port.val()});
		updateSelectOptions($secAcct, accounts);
		$secAcct.trigger("change");
	});
}

//通过券商-->证券账户, 证券账户-->现金账户
function secCashAccount(secAcct_id, bankAcct_id, value){
	var $secAcct = $("#" + secAcct_id);
	var $bankAcct = $("#" + bankAcct_id);
	var oldVal = $bankAcct.val();
	updateSelectOptions($bankAcct, fetch(getURL("secCashList"), {secAcct: $secAcct.val()}));
	//if (typeof(value) != "undefined"){
	//	$bankAcct.val(value);
	//}
	//else{
	//	$bankAcct.val(oldVal);
	//}
	$secAcct.off("change");
	$secAcct.on("change", function(){
		var accounts = fetch(getURL("secCashList"), {secAcct: $(this).val()});
		updateSelectOptions($bankAcct, accounts);
		$bankAcct.trigger("change");
	});
}

//通过组合得到现金账户
function portCashAccount(port_id, bankAcct_id, value){
	var $port = $("#" + port_id);
	var $bankAcct = $("#" + bankAcct_id);
	var oldVal = $bankAcct.val();
	updateSelectOptions($bankAcct, fetch(getURL("portBankShortList"), {portID: $port.val()}));
	//if (typeof(value) != "undefined"){
	//	$bankAcct.val(value);
	//}
	//else{
	//	$bankAcct.val(oldVal);
	//}
}

function getFutureTypes(exchange_id, futuresTypes_id, value){
    var $exchange = $("#" + exchange_id);
    var $types = $("#" + futuresTypes_id);

    var tps = $types.val();
    if ($exchange.val() != "undefined" && $exchange.val() !=""){
        dd = fetch(getURL("futuresTypesWithExchange"), {exchange: $exchange.val()})
        updateSelectOptions($types, dd);
        if (tps && tps != ""){
            $types.val(tps);
            $types.change();
        }
    }
    $exchange.off("change");
    $exchange.on("change", function(){
            if ($exchange.val() != "undefined" && $exchange.val() !=""){
                dd = fetch(getURL("futuresTypesWithExchange"), {exchange: $exchange.val()})
                updateSelectOptions($types, dd);
                $types.trigger("change");
            }

    });
}


function getFutureTargets(exchange_id, futuresTypes_id, futuresTargets_id, value){
    var $exchange = $("#" + exchange_id);
    var $types = $("#" + futuresTypes_id);
    var $targets = $("#" + futuresTargets_id);
    var targets = $targets.val()

    if ($types.val() != "undefined" && $types.val() != ""){
        if ($exchange.val() != "undefined" && $exchange.val() !=""){
            updateSelectOptions($targets, fetch(getURL("futuresTargetsWithExchange"), {exchange: $exchange.val(), futuresType: $types.val()}));
            if (targets && targets !=""){
                $targets.val(targets);
                $targets.change();
            }
        }
    }

    $types.on("change", function(){
        	if ($types.val() != "undefined" && $types.val() != ""){
                    if ($exchange.val() != "undefined" && $exchange.val() !=""){
                        updateSelectOptions($targets, fetch(getURL("futuresTargetsWithExchange"), {exchange: $exchange.val(), futuresType: $types.val()}));
                    }
                }
    });
}

function principal(share_id, price_id, principal_id){
	var $share = $("#" + share_id);
	var $price = $("#" + price_id);
	var $principal = $("#" + principal_id);
	var secid = $("#secid").val()||"";
	
	$share.on("change", function(){
		var share = $share.val()||0;
		var price = $price.val()||0;
		var principal = fetch(getURL("tranPrincipal"), {secid:secid, share:share, price:price});
	
		$principal.val(principal);
		$principal.trigger("change");
	});
	
	$price.on("change", function(){
		var share = $share.val()||0;
		var price = $price.val()||0;
		var principal = fetch(getURL("tranPrincipal"), {secid:secid, share:share, price:price});
		
		$principal.val(principal);
		$principal.trigger("change");
	})
	
	$principal.on("change", function(){
		var share = $share.val()||0;
		var principal = $principal.val()||0;
		var price;
		if (parseFloat(share) > 0){
			price = fetch(getURL("tranPrice"), {secid:secid, share:share, principal:principal});
			$price.val(price);
		}
	});
}

function b2sCalCash(principal2_id, principal_id, accruedIncome_id, tradeCash_id){
	var $principal2 = $("#" + principal2_id);
	var $principal = $("#" + principal_id);
	var $accruedIncome=$("#" + accruedIncome_id);
	var $tradeCash = $("#" + tradeCash_id);
	
	$principal2.on("change", function(){
		var principal = $principal.val()||0;
		var principal2 = $principal2.val()||0;
		var accruedIncome=$accruedIncome.val();
		$tradeCash.val(Math.round((Number(principal)-Number(principal2)+Number(accruedIncome))*100)/100);
		$tradeCash.trigger("change");
	});
	
	$principal.on("change", function(){
		var principal = $principal.val()||0;
		var principal2 = $principal2.val()||0;
		var accruedIncome=$accruedIncome.val();
		$tradeCash.val(Math.round((Number(principal)-Number(principal2)+Number(accruedIncome))*100)/100);
		$tradeCash.trigger("change");
	});
	$accruedIncome.on("change", function(){
		var principal = $principal.val()||0;
		var principal2 = $principal2.val()||0;
		var accruedIncome=$accruedIncome.val();
		$tradeCash.val(Math.round((Number(principal)-Number(principal2)+Number(accruedIncome))*100)/100);
		$tradeCash.trigger("change");
	})
}

function totalAmount(type_id, principal_id, income_id, expense_id, trate_id, srate_id, tamount_id, samount_id, sec_id){
	var $principal = $("#" + principal_id);
	var $income = $("#" + income_id);
	var $expense = $("#" + expense_id);
	var $trate = $("#" + trate_id);
	var $srate = $("#" + srate_id);
	var $secid = $("#" + sec_id);
	
	var tradeType = $("#" + type_id).val();
	
	var $tamount = $("#" + tamount_id);
	var $samount = $("#" + samount_id);
	
	$.each([$principal, $income, $expense, $trate, $srate], function(i,v){
		v.on("change", function(){
			var amounts = fetch(getURL("calcTradeTotal"), {tradeType:tradeType, principal:$principal.val()||0, 
											               income:$income.val()||0, expense:$expense.val()||0, 
											               trate:$trate.val()||1, srate:$srate.val()||1,
											               secid:$secid.val()||""});
			$tamount.val(amounts[0]);
			$samount.val(amounts[1]);
		});
	});
}

function totalAmount2(type_id, principal_id, income_id, trate_id, srate_id, tamount_id, samount_id, sec_id){
	var $principal = $("#" + principal_id);
	var $income = $("#" + income_id);
	var $trate = $("#" + trate_id);
	var $srate = $("#" + srate_id);
	var $secid = $("#" + sec_id);
	
	var tradeType = $("#" + type_id).val();
	
	var $tamount = $("#" + tamount_id);
	var $samount = $("#" + samount_id);
	
	$.each([$principal, $income, $trate, $srate], function(i,v){
		v.on("change", function(){
			var amounts = fetch(getURL("calcTradeTotal"), {tradeType:tradeType, principal:$principal.val()||0, 
											               income:$income.val()||0, expense:0, 
											               trate:$trate.val()||1, srate:$srate.val()||1,
											               secid:$secid.val()||""});
			$tamount.val(amounts[0]);
			$samount.val(amounts[1]);
		});
	});
}

function totalAmount3(type_id, principal_id, income_id,  fee_id, clawback_id, expense_id, trate_id, srate_id, tamount_id, samount_id, sec_id){
	var $principal = $("#" + principal_id);
	var $fee = $("#" + fee_id);
	var $clawback = $("#" + clawback_id);
	var $expense = $("#" + expense_id);
	var $trate = $("#" + trate_id);
	var $srate = $("#" + srate_id);
	var $secid = $("#" + sec_id);
	 var $income = $("#" + income_id);
	
	var tradeType = $("#" + type_id).val();
	
	var $tamount = $("#" + tamount_id);
	var $samount = $("#" + samount_id);
	
	$.each([$principal, $expense, $trate, $srate, $fee, $income], function(i,v){
		v.on("change", function(){
			var cb = $clawback.val()||0;
			var fee = $fee.val()||0;
			var ic = $income.val()||0;
			var amounts = fetch(getURL("calcTradeTotal"), {tradeType:tradeType, principal:$principal.val()||0, 
											               income:(ic - fee), expense:$expense.val()||0, 
											               trate:$trate.val()||1, srate:$srate.val()||1,
											               secid:$secid.val()||""});
			$tamount.val(amounts[0]);
			$samount.val(amounts[1]);
		});
	});
}

function maturityTotal(type_id, smf_id, rate_id, principal_id, tradeDate_id, settleDate_id, maturityDate_id, maturityInterest_id, maturityTotal_id){
	var tradeType = $("#" + type_id).val();

	var smf = $("#" + smf_id).val();
	
	var $prin = $("#" + principal_id);
	var $td = $("#" + tradeDate_id);
	var $sd = $("#" + settleDate_id);
	var $md = $("#" + maturityDate_id);
	
	var $mi = $("#" + maturityInterest_id);
	var $mt = $("#" + maturityTotal_id);
	var $rate = $("#" + rate_id);
	
	$.each([$prin, $td, $sd, $md, $rate], function(i,v){
		v.on("change", function(){
			var amounts = fetch(getURL("calcMaturityTotal"), {tradeType:tradeType, smf:smf, rate:$rate.val()||0,
															  principal:$prin.val()||0, tradeDate:$td.val()||"",
															  settleDate:$sd.val()||"", maturityDate:$md.val()||""});
			$mi.val(amounts[0]);
			$mt.val(amounts[1]);
		});
	});
}

function maturityInterest(type_id, smf_id, rate_id, principal_id, tradeDate_id, maturityDate_id, maturityInterest_id, maturityTotal_id){
	var tradeType = $("#" + type_id).val();
	var smf = $("#" + smf_id).val();
	
	var $prin = $("#" + principal_id);
	var $td = $("#" + tradeDate_id);
	var $md = $("#" + maturityDate_id);
	
	var $mi = $("#" + maturityInterest_id);
	var $mt = $("#" + maturityTotal_id);
	var $rate = $("#" + rate_id);
	
	
	$mi.on("change", function(){
		var amounts = fetch(getURL("calcMaturityInterest"), {tradeType:tradeType, smf:smf, maturityInterest:$mi.val()||0, principal:$prin.val()||0, tradeDate:$td.val()||"", maturityDate:$md.val()||""});
		$rate.val(amounts[0]);
		$mt.val(amounts[1]);
	});
}

function effectiveInterest(port_id, smf_id, rate_id, amount_id, principal_id, tradeDate_id, settleDate_id, valuation_id){
	var smf = $("#" + smf_id).val();
	var $amount = $("#" + amount_id);
	var $principal = $("#" + principal_id);
	var $rate = $("#" + rate_id);
	var $td = $("#" + tradeDate_id);
	var $sd = $("#" + settleDate_id);
	var $port = $("#" + port_id);
	var $vid = $("#" + valuation_id)
	
	var AMORTIZE = 2;
	var func = function(){
		if ($vid.val() == AMORTIZE){
			var rate = fetch(getURL("calcEffectiveInterest"), {portid:$port.val(), smf:smf,  amount:$amount.val()||0,  principal:$principal.val()||0, tradeDate:$td.val()||"", settleDate:$sd.val()||""});
			$rate.val(rate);
		}
	}
	$amount.on("change", func);
	$principal.on("change", func);
	$td.on("change", func);
}


function updateExpSettleDate(value){
	$.each($("[data-for='tradeExpense_tr']"), function(index, tr){
		var expID = $(tr).attr("id");
		var expID = $(tr).attr("id");
		var $sd = $("#tradeExpense_" + expID + "_expenseSettleDate");
		if (! $sd.prop("disabled")){
			$sd.val(value);
		}
	});
}

function expenseSettleDate(settleD_id){
	var $settleDate = $("#" + settleD_id);
	
	var d = $settleDate.val();
	if (typeof(d) == "undefined" || d == ""){	
	}
	else {
		updateExpSettleDate(d);
	}
	
	$settleDate.off("change");
	$settleDate.on("change", function(){
		var d = $(this).val();
		if (typeof(d) == "undefined" || d == ""){	
		}
		else {
			updateExpSettleDate(d);
		}
	});
}

function updateTotalExpense(exp_id, trade_a, exp_tab){
	$("#" + trade_a).on("click", function (e) {
		totalExpense(exp_id, exp_tab);
	})
};

//更新总费用金额，及相应点击事件
function totalExpense(exp_id, exp_tab, value){
	$exp = $("#" + exp_id);
	var port = $("#port").val()|""
	var expenses = [];
	$.each($("[data-for='tradeExpense_tr']"), function(index, tr){
		var expID = $(tr).attr("id");
		var expList = {};
		expList['expense'] = $("#tradeExpense_" + expID + "_expense").val();
		expList['feeTradeCurrency'] = $("#tradeExpense_" + expID + "_feeTradeCurrency").val();
        expList['tableFeeCurrency'] = $("#tradeExpense_" + expID + "_tableFeeCurrency").val();
        expList['fxRate'] = $("#tradeExpense_" + expID + "_fxRate").val()||0;
        expList['totalAmount'] = $("#tradeExpense_" + expID + "_totalAmount").val()||0;
        expList['expenseAmount'] = $("#tradeExpense_" + expID + "_expenseAmount").val()||0;
        expList['cashAmount'] = $("#tradeExpense_" + expID + "_cashAmount").val()||0;
        expList['bankAccount'] = $("#tradeExpense_" + expID + "_bankAccount").val()||0;
        expList['expenseSettleDate'] = $("#tradeExpense_" + expID + "_expenseSettleDate").val()||0;
		expenses.push(expList);
	});
	
	if (typeof(value) == "undefined"){
		var total = fetch(getURL("calcTradeExpenses"), {port:port, expenses:JSON.stringify(expenses)});
		$exp.val(total);
		$exp.trigger("change");
	}
	else{
		$exp.val(value);
	}
	
	$exp.off("click");
	$exp.on("click", function(){
		$("a[href='#" + exp_tab +"']").tab("show");
	});
}

//通过smf得到交易货币
function tradeCurrency(smf_id, tc_id, value){
	var $smf = $("#" + smf_id);
	var $tc = $("#" + tc_id);
	if (typeof(value) != "undefined"){
		$tc.val(value);
	}
	else{
		$tc.val(fetch(getURL("smfTradeCurrency"), {id:$smf.val()}));
		$tc.trigger("change");
	}
}

//通过smf得到远端货币
function longShortCurrency(smf_id, long_id, short_id){
	var $smf = $("#" + smf_id);
	var $long = $("#" + long_id);
	var $short = $("#" + short_id);
	
	$smf.on("change", function(){
		var currencies = fetch(getURL("smfLongShortCurrency"), {id:$smf.val()});
		$long.val(currencies["long"]);
		$long.trigger("change");
		
		$short.val(currencies["short"]);
		$short.trigger("change");
	});
}

//通过投资组合，得到投资组合记账货币
function portCurrency(port_id, fc_id, value){
	var $port = $("#" + port_id);
	var $fc = $("#" + fc_id);
	if (typeof(value) != "undefined"){
		$fc.val(value);
	}
	else{
		$fc.val(fetch(getURL("portCurrency"), {id:$port.val()}));
		$fc.trigger("change");
	}
}

//通过bank account得到结算货币
function settleCurrency(bankAcct_id, sc_id, value){
	var $bankAcct = $("#" + bankAcct_id);
	var $sc = $("#" + sc_id);
	if (typeof(value) != "undefined"){
		$sc.val(value);
	}
	else{
		$sc.val(fetch(getURL("bankAccountCurrency"), {id:$bankAcct.val()}));
		$sc.trigger("change");
	}
	$bankAcct.on("change", function(){
		$sc.val(fetch(getURL("bankAccountCurrency"), {id:$(this).val()}));
		$sc.trigger("change");
	});
}

function defaultFxRate(date_id, func_id, local_id, fxRate_id, value){
	var $date = $("#" + date_id);
	var $func = $("#" + func_id);
	var $local = $("#" + local_id);
	var port = $("#port").val()||"";
	
	var $fxRate = $("#" + fxRate_id);
	
	if (typeof(value) != "undefined"){
		$fxRate.val(value);
	}
	else{
		if (!$fxRate.val()){
			$fxRate.val(fetch(getURL("defaultFxRate"), {port:port, date:$date.val()||"", funcCurrency:$func.val(), localCurrency:$local.val()}));
		}
	}
	$date.on("change", function(){
		$fxRate.val(fetch(getURL("defaultFxRate"), {port:port, date:$date.val()||"", funcCurrency:$func.val(), localCurrency:$local.val()}));
		$fxRate.trigger("change");
	});
	
	$func.off("change");
	$func.on("change", function(){
		$fxRate.val(fetch(getURL("defaultFxRate"), {port:port, date:$date.val()||"", funcCurrency:$func.val(), localCurrency:$local.val()}));
		$fxRate.trigger("change");
	});
	
	$local.off("change");
	$local.on("change", function(){
		$fxRate.val(fetch(getURL("defaultFxRate"), {port:port, date:$date.val()||"", funcCurrency:$func.val(), localCurrency:$local.val()}));
		$fxRate.trigger("change");
	});
}

function defaultFxRate2(date_id, port_id, local_id, fxRate_id, value){
	var $date = $("#" + date_id);
	var $port = $("#" + port_id);
	var func = fetch(getURL("portCurrency"), {id:$port.val()})
	var $local = $("#" + local_id);
	var port = $("#port").val()||"";

	var $fxRate = $("#" + fxRate_id);

	if (typeof(value) != "undefined"){
		$fxRate.val(value);
	}
	else{
		if (!$fxRate.val()){
			$fxRate.val(fetch(getURL("defaultFxRate"), {port:port, date:$date.val()||"", funcCurrency:func, localCurrency:$local.val()}));
		}
	}
	$date.on("change", function(){
		$fxRate.val(fetch(getURL("defaultFxRate"), {port:port, date:$date.val()||"", funcCurrency:func, localCurrency:$local.val()}));
		$fxRate.trigger("change");
	});

	$local.off("change");
	$local.on("change", function(){
		$fxRate.val(fetch(getURL("defaultFxRate"), {port:port, date:$date.val()||"", funcCurrency:func, localCurrency:$local.val()}));
		$fxRate.trigger("change");
	});
}

function totalNAV(nav_id, port_id){
	var $nav = $("#" + nav_id);
	var $port = $("#" + port_id);
	
	if (!$nav.val()){
		$nav.val(fetch(getURL("totalNAV"), {portfolio:$port.val()}));
	}
}

function calcNAV(nav_id, shares_id, price_id, port_id){
	var $nav = $("#" + nav_id);
	var $shares = $("#" + shares_id);
	var $price = $("#" + price_id);
	var $port = $("#" + port_id);
	
	$price.on("change", function(){
		$shares.val(fetch(getURL("calcShares"), {portfolio:$port.val(), totalNAV:$nav.val(), NAV:$price.val()}));
	});
	
	$shares.on("change", function(){
		$price.val(fetch(getURL("calcNAV"), {portfolio:$port.val(), totalNAV:$nav.val(), shares:$shares.val()}));
	});
}

function lotSelection(lotMethods_id, lot_id){
	var $lotMethods = $("#" + lotMethods_id);
	var $lot = $("#" + lot_id);
	
	var SPECIFIC = 6;
	
	if (($lotMethods.val() == SPECIFIC) && (! $lotMethods.prop("disabled"))){
		$lot.prop("disabled", false);
	}
	
	$lotMethods.off("change").on("change", function(){
		if ($lotMethods.val() == SPECIFIC){
			$lot.prop("disabled", false);
		}
		else{
			$lot.prop("disabled", true);
		}
	});
}

var SCHEDULE = "84";
var NATRUE_DAY = "2";
var BASIS = "2";

var FIXEDRATE = "70";
var FLOAT = "86";
var RESET = "85";
var FIXED = "0";

var SMF = 1;

function cfCreate(cf_id, name){
	var n = name || "cf";
	var detail = n + "_detail";
	var alert = n + "_alert";
	var display = n + "_display";
	var modal = n + "_modal";
	var title = n + "_title";

	$("#" + detail).empty();
	var e = $.extend(true, {}, editForm);
	e.source.on = false;
	e.form.name = "cf_add";
	e.form.func = function(data){
		ratesCreate(n);
		paymentsCreate(n);
		floatsCreate(n, "cfFloat_add");
		parsCreate(n);
		$("#" + n + "_payments_tab").hide();
		$("#" + n + "_rates_tab").hide();
		$("#" + n + "_floats_tab").hide();
		
		updateSelectOptions($("#" + n + "_paymentMonth"), fetch(getURL("paymentMonth"), {freq:$("#" + n + "_frequency").val()}));
		$("#" + n + "_frequency").off("change").on("change", function(){
			var v = $(this).val();
			if (v == SCHEDULE){
				$("#" + n + "_paymentMonth").prop("disabled", true);
				$("#" + n + "_paymentDay").prop("disabled", true);
				$("#" + n + "_paymentN").prop("disabled", true);
				$("#" + n + "_paymentNType").prop("disabled", true);
				$("#" + n + "_payments_tab").show();
			}
			else{
				$("#" + n + "_paymentMonth").prop("disabled", false);
				$("#" + n + "_paymentDay").prop("disabled", false);
				$("#" + n + "_paymentN").prop("disabled", false);
				$("#" + n + "_paymentNType").prop("disabled", false);
				$("#" + n + "_payments_tab").hide();
			}
			updateSelectOptions($("#" + n + "_paymentMonth"), fetch(getURL("paymentMonth"), {freq:v}));
		});
		
		$("#" + n + "_externalRate").off("change").on("change",function(){
			if ($(this).is(":checked")){
				$("#" + n + "_contractRate").prop("disabled", true);
				$("#" + n + "_effectiveRate").prop("disabled", true);
				$("#" + n + "_fixedValue").prop("disabled", true);
			}
			else{
				$("#" + n + "_contractRate").prop("disabled", false);
				$("#" + n + "_effectiveRate").prop("disabled", false);
				$("#" + n + "_fixedValue").prop("disabled", false);
			}
		});
		
		
		$("#" + n + "_cfType").off("change").on("change", function(){
			var cfType = $(this).val(); 
			if ( cfType == FIXEDRATE){
				$("#" + n + "_fixedValue").prop("disabled", false);
				$("#" + n + "_contractRate").prop("disabled", false);
				$("#" + n + "_rates_tab").hide();
				$("#" + n + "_floats_tab").hide();
			}
			else if (cfType == FLOAT){
				$("#" + n + "_fixedValue").prop("disabled", true);
				$("#" + n + "_contractRate").prop("disabled", false);
				$("#" + n + "_rates_tab").show();
				$("#" + n + "_floats_tab").show();
			}
			else if (cfType == RESET){
				$("#" + n + "_fixedValue").prop("disabled", true);
				$("#" + n + "_contractRate").prop("disabled", false);
				$("#" + n + "_rates_tab").show();
				$("#" + n + "_floats_tab").hide();
			}
			else if (cfType == FIXED){
				$("#" + n + "_fixedValue").prop("disabled", false);
				$("#" + n + "_contractRate").prop("disabled", false);
				$("#" + n + "_rates_tab").hide();
				$("#" + n + "_floats_tab").hide();
			}
		});
		
		$("#" + n + "_accrEndType").val(NATRUE_DAY);
		$("#" + n + "_accrStartType").val(NATRUE_DAY);
		$("#" + n + "_basis").val(BASIS);
		$("#" + n + "_accrEndbasis").val(BASIS);
		$("#" + n + "_cfType").val(FIXEDRATE).change();
	};
	e.event.create.on = false;
	e.event.create.source.func = function(dataFor){
		cfSave(cf_id, n);
	}
	e.event.update.on = false;
	var tabs = {};
	tabs[n] = "基本信息";
	tabs[n + "_rates"] = "利息重置";
	tabs[n + "_payments"] = "付息计划";
	tabs[n + "_floats"] = "浮动利率重置";
	tabs[n + "_pars"] = "还本计划";
	genTabs(detail, n + "_tabs", n + "_cons", tabs);
	genEditForm2(display, n, e.source, e.form, e.event, 3, alert, false);
	
	$("#" + title).text("新建-" + translate["cf"]);
	$("#smf_modal").modal("hide");
	//$("#content_modal").modal("hide");
	
	$("#" + modal).find(".modal-footer").find("button").text("返回");
	$("#" + modal).modal();
	
	$("#" + title).text(translate["cf"] + "创建");
	$("a[href='#" + display + "']").tab("show");
}

function ratesCreate(n){
	var div = n + "_rates_div";
	var display = n + "_rates_display";
	var dataFor = n + "Rate";
	var alert = n + "_alert";

	$("#" + div).remove();
	var $div = $("<div id='" + div + "'></div>")
	var $title = $("<div class='col-md-1'></div>");
	$div.append("<br>");
	$div.append($title);
	$div.append("<br><br>");

	$("#" + display).append($div);
	var f = $.extend(true, {}, tableForm);	
	f.source.on = false;
	f.form.name = "cfRates_create_table";
	f.event.add.form.name = "cfRates_create_table";
	f.event.add.source.address = "cfRateCreate";
	genTableForm(div, dataFor, f.source, f.form, f.event, alert, true);
	$("#" + dataFor + "_add").text("增加利率");
	$("#" + dataFor + "_add").appendTo($title);
	
};

function paymentsCreate(n){
	var div = n + "_payments_div";
	var display = n + "_payments_display";
	var dataFor = n + "Payment";
	var alert = n + "_alert";

	$("#" + div).remove();
	var $div = $("<div id='" + div + "'></div>");
	var $title = $("<div class='col-md-1'></div>");
	$div.append("<br>");
	$div.append($title);
	$div.append("<br><br>");

	$("#" + display).append($div);
	var f = $.extend(true, {}, tableForm);	
	f.source.on = false;
	f.form.name = "cfPayments_create_table";
	f.event.add.form.name = "cfPayments_create_table";
	f.event.add.source.address = "cfPaymentCreate";
	genTableForm(div, dataFor, f.source, f.form, f.event, alert, true);
	$("#" + dataFor + "_add").text("增加付息计划");
	$("#" + dataFor + "_add").appendTo($title);
	
};

function floatsCreate(n, form_name, cfID){
	var div = n + "_floats_div";
	var display = n + "_floats_display";
	var dataFor = n + "Float";
	var alert = n + "_alert";

	$("#" + div).remove();
	var $div = $("<div id='" + div + "'></div>")
	
	$("#" + display).append($div);
	var e = $.extend(true, {}, editForm);
	e.source.on = false;
	e.form.name = form_name || "cfFloat_add";
	e.form.func = function(data){
		updateSelectOptions($("#" + dataFor + "_month"), fetch(getURL("paymentMonth"), {freq:$("#" + dataFor + "_frequency").val()}));
		$("#" + dataFor + "_frequency").off("change").on("change", function(){
			var v = $(this).val();
			if (v == SCHEDULE){
				$("#" + dataFor + "_month").prop("disabled", true);
				$("#" + dataFor + "_day").prop("disabled", true);
				$("#" + dataFor + "vrResetNDays").prop("disabled", true);
			}
			else{
				$("#" + dataFor + "_month").prop("disabled", false);
				$("#" + dataFor + "_day").prop("disabled", false);
				$("#" + dataFor + "vrResetNDays").prop("disabled", false);
			}
			updateSelectOptions($("#" + dataFor + "_month"), fetch(getURL("paymentMonth"), {freq:v}));
		});
	};
	e.event.create.source.address = "cfFloatCreate";
	if (cfID){
		e.event.create.source.inputs = {pid:cfID}
	}
	e.event.update.on = false;
	genEditForm(div, dataFor, e.source, e.form, e.event, 4, alert, false);
	
};

function floatsCreateUpdate(n, data, cfID){
	var div = n + "_floats_div";
	var display = n + "_floats_display";
	var dataFor = n + "Float";
	var alert = n + "_alert";

	$("#" + div).remove();
	var $div = $("<div id='" + div + "'></div>")
	
	$("#" + display).append($div);
	var e = $.extend(true, {}, editForm);
	e.source.on = false;
	e.source.value = data;
	e.form.name = "cfFloat_edit";
	e.form.func = function(d){
		updateSelectOptions($("#" + dataFor + "_month"), fetch(getURL("paymentMonth"), {freq:$("#" + dataFor + "_frequency").val()}));
		$("#" + dataFor + "_frequency").off("change").on("change", function(){
			var v = $(this).val();
			if (v == SCHEDULE){
				$("#" + dataFor + "_month").prop("disabled", true);
				$("#" + dataFor + "_day").prop("disabled", true);
				$("#" + dataFor + "vrResetNDays").prop("disabled", true);
			}
			else{
				$("#" + dataFor + "_month").prop("disabled", false);
				$("#" + dataFor + "_day").prop("disabled", false);
				$("#" + dataFor + "_vrResetNDays").prop("disabled", false);
			}
			updateSelectOptions($("#" + dataFor + "_month"), fetch(getURL("paymentMonth"), {freq:v}));
		});
	};
	e.event.update.source.address = "cfFloatUpdate";
	if (cfID){
		e.event.update.source.inputs = {pid:cfID}
	}
	e.event.create.on = false;
	genEditForm(div, dataFor, e.source, e.form, e.event, 4, alert, true);	
}

function parsCreate(n){
	var div = n + "_pars_div";
	var display = n + "_pars_display";
	var dataFor = n + "Par";
	var alert = n + "_alert";

	$("#" + div).remove();
	var $div = $("<div id='" + div + "'></div>")
	var $title = $("<div class='col-md-1'></div>");
	$div.append("<br>");
	$div.append($title);
	$div.append("<br><br>");

	$("#" + display).append($div);
	var f = $.extend(true, {}, tableForm);	
	f.source.on = false;
	f.form.name = "cfPars_create_table";
	f.event.add.form.name = "cfPars_create_table";
	f.event.add.source.address = "cfParCreate";
	genTableForm(div, dataFor, f.source, f.form, f.event, alert, true);
	$("#" + dataFor + "_add").text("增加还本计划");
	$("#" + dataFor + "_add").appendTo($title);
};

function getRates(n){
	var dataFor = n + "Rate"
	
	var expenses = [];
	var exps = [];
	var status = true;
	
	if ($("#" + n + "_rates_tab").is(':visible')){
		$.each($("[data-for='" + dataFor + "_tr']"), function(index, tr){
			var expID = $(tr).attr("id");
			var expList = [];
			var expMap = {};
			expMap.id = expID;
			$.each($("[data-for='" + dataFor + "_" + expID + "']"), function(index, exp){
				var v = $(exp).val();
				if (typeof(v) == "undefined" || v == ""){
					status = false;
				}
				else{
					expList.push($(exp).val());
					expMap[$(exp).attr("name").split("_")[2]] = $(exp).val();
				}
			});
			expenses.push(expList);
			exps.push(expMap);
		});
	}
	return {status:status, rates:exps};
}

function getPayments(n){
	var dataFor = n + "Payment"

	var expenses = [];
	var exps = [];
	var status = true;
	if ($("#" + n + "_payments_tab").is(':visible')){
		$.each($("[data-for='" + dataFor + "_tr']"), function(index, tr){
			var expID = $(tr).attr("id");
			var expList = [];
			var expMap = {};
			expMap.id = expID;
			$.each($("[data-for='" + dataFor + "_" + expID + "']"), function(index, exp){
				var v = $(exp).val();
				if (typeof(v) == "undefined" || v == ""){
					status = false;
				}
				else{
					expList.push($(exp).val());
					expMap[$(exp).attr("name").split("_")[2]] = $(exp).val();
				}
			});
			expenses.push(expList);
			exps.push(expMap);
		});
	}
	return {status:status, payments:exps};
}

function getFloats(n){
	var dataFor = n + "Float"

	var floats = {};
	var status = true;
	if ($("#" + n + "_floats_tab").is(':visible')){
		$.each($("[data-for='" + dataFor + "']"), function(index, value){
			if($(value).attr("name")){
				floats[$(value).attr("name").split('_')[1]] =  $(value).val();;
			}
		});
	}
	return {status:status, floats:floats};
}

function getPars(n){
	var dataFor = n + "Par"

	var expenses = [];
	var exps = [];
	var status = true;
	
	if ($("#" + n + "_pars_tab").is(':visible')){
		$.each($("[data-for='" + dataFor + "_tr']"), function(index, tr){
			var expID = $(tr).attr("id");
			var expList = [];
			var expMap = {};
			expMap.id = expID;
			$.each($("[data-for='" + dataFor + "_" + expID + "']"), function(index, exp){
				var v = $(exp).val();
				if (typeof(v) == "undefined" || v == ""){
					status = false;
				}
				else{
					expList.push($(exp).val());
					expMap[$(exp).attr("name").split("_")[2]] = $(exp).val();
				}
			});
			expenses.push(expList);
			exps.push(expMap);
		});
	}
	return {status:status, pars:exps};
}

function cfSave(cf_id, n){
	var create = true;
	var alert = n + "_alert";
	$("#" + n).validate();
	if ($("#" + n).valid()){
		var rates = getRates(n);
		var payments = getPayments(n);
		var floats = getFloats(n);
		var pars = getPars(n);
		if (rates.status && payments.status && floats.status && pars.status){
			if (confirm("是否确认创建?")){
	 			var params = [];
	 			
	 			var json = {};
	 			json.header = "cashFlowMsg";
	 			json.operation = "ADD";
	 			json.rates = rates.rates;
	 			json.payments = payments.payments;
	 			json.floats = floats.floats;
	 			json.pars = pars.pars;
	 			
	 			$.each($("#" + n + "_display [data-for='" + n + "']"), function(index, value){
	 				if ($(value).prop("type") == "checkbox"){
	 					params.push($(value).is(":checked"));
	 					json[$(value).attr("name").split("_")[1]] = $(value).is(":checked");
	 				}
	 				else {
	 					params.push($(value).val()||$(value).text());
	 					json[$(value).attr("name").split("_")[1]] = $(value).val()||$(value).text();
	 				}
	 			});
	 			
 				var address = "cfCreate"
	 			invoke(getURL(address),{params:JSON.stringify(params), json:JSON.stringify(json)}, function(data){
	 				var new_id = data;
	 				$("#" + alert).empty();
	 				$("[data-for='" + n + "']").prop("disabled", true);
		            	$("[data-for*='" + n + "Rate']").prop("disabled", true);
		            	$("[data-for*='"+ n + "Payment']").prop("disabled", true);
		            	$("#" + alert).append(genAlert("success", translate["cf"] + "创建成功!"));
		     		$("#" + n + "_create").prop("disabled", true);
		     		$("#" + cf_id).val(new_id);
		 			}, [alert,"content_alert"]);
 					
	 		}
		}
		else {
			$("#" + alert).empty();
			$("#" + alert).append(genAlert("fail", "利率重置、付息计划、浮动利率重置、还本计划仍有没有存储的条目，无法保存交易!"));
		}
	}
}

function cfUpdate(cf_id, name){
	$cf = $("#" + cf_id);
	var cfID = $cf.val();

	var n = name || "cf";
	var detail = n + "_detail";
	var alert = n + "_alert";
	var display = n + "_display";
	var modal = n + "_modal";
	
	$("#" + detail).empty();
	var e = $.extend(true, {}, editForm);
	e.source.on = true;
	e.source.address = "cfDetail";
	e.source.inputs = {id:$cf.val()};
	e.form.name = "cf_edit";
	
	e.form.func = function(data){
		ratesUpdate(cfID, n);
		paymentsUpdate(cfID, n);
		floatsUpdate(cfID, n);
		parsUpdate(cfID, n);
		$("#" + n + "_payments_tab").hide();
		$("#" + n + "_rates_tab").hide();
		$("#" + n + "_floats_tab").hide();
		
		if ($("#" + n + "_frequency").val() == SCHEDULE){
			$("#" + n + "_payments_tab").show();
		}
		if($("#" + n + "_cfType").val() == FLOAT){
			$("#" + n + "_floats_tab").show();
			$("#" + n + "_rates_tab").show();
		}
		if($("#" + n + "_cfType").val() == RESET){
			$("#" + n + "_rates_tab").show();
		}
		
		updateSelectOptions($("#" + n + "_paymentMonth"), fetch(getURL("paymentMonth"), {freq:$("#" + n + "_frequency").val()}));
		$("#" + n + "_paymentMonth").val(data.data[16]);
		$("#" + n + "_frequency").off("change").on("change", function(){
			var v = $(this).val();
			if (v == SCHEDULE){
				$("#" + n + "_paymentMonth").prop("disabled", true);
				$("#" + n + "_paymentDay").prop("disabled", true);
				$("#" + n + "_paymentN").prop("disabled", true);
				$("#" + n + "_paymentNType").prop("disabled", true);
				$("#" + n + "_payments_tab").show();
			}
			else{
				$("#" + n + "_paymentMonth").prop("disabled", false);
				$("#" + n + "_paymentDay").prop("disabled", false);
				$("#" + n + "_paymentN").prop("disabled", false);
				$("#" + n + "_paymentNType").prop("disabled", false);
				$("#" + n + "_payments_tab").hide();
			}
			updateSelectOptions($("#cf_paymentMonth"), fetch(getURL("paymentMonth"), {freq:v}));
		});
		
		$("#" + n + "_externalRate").off("change").on("change",function(){
			if ($(this).is(":checked")){
				$("#" + n + "_contractRate").prop("disabled", true);
				$("#" + n + "_effectiveRate").prop("disabled", true);
				$("#" + n + "_fixedValue").prop("disabled", true);
			}
			else{
				$("#" + n + "_contractRate").prop("disabled", false);
				$("#" + n + "_effectiveRate").prop("disabled", false);
				$("#" + n + "_fixedValue").prop("disabled", false);
			}
		});
		
		$("#" + n + "_cfType").off("change").on("change", function(){
			var cfType = $(this).val(); 
			if ( cfType == FIXEDRATE){
				$("#" + n + "_fixedValue").prop("disabled", false);
				$("#" + n + "_contractRate").prop("disabled", false);
				$("#" + n + "_rates_tab").hide();
				$("#" + n + "_floats_tab").hide();
			}
			else if (cfType == FLOAT){
				$("#" + n + "_fixedValue").prop("disabled", true);
				$("#" + n + "_contractRate").prop("disabled", false);
				$("#" + n + "_rates_tab").show();
				$("#" + n + "_floats_tab").show();
			}
			else if (cfType == RESET){
				$("#" + n + "_fixedValue").prop("disabled", true);
				$("#" + n + "_contractRate").prop("disabled", false);
				$("#" + n + "_rates_tab").show();
				$("#" + n + "_floats_tab").hide();
			}
			else if (cfType == FIXED){
				$("#" + n + "_rates_tab").hide();
				$("#" + n + "_floats_tab").hide();
			}
		});
	};
	e.event.create.on = false;
	e.event.update.on = true;
	e.event.update.source.address = "cfUpdate";
	e.event.update.source.inputs = {id:$cf.val()};
	e.event.update.source.func = function(id, dataFor){
		$cf.val(id);
		if($("#" + n + "_frequency").val() == SCHEDULE){
			$("#" + n + "_paymentMonth").prop("disabled", true);
			$("#" + n + "_paymentDay").prop("disabled", true);
			$("#" + n + "_paymentN").prop("disabled", true);
			$("#" + n + "_paymentNType").prop("disabled", true);
		}
		if ($("#" + n + "_cfType").val() == FIXED){
			$("#" + n + "_fixedValue").prop("disabled", false);
			$("#" + n + "_contractRate").prop("disabled", true);
		}
		else{
			$("#" + n + "_fixedValue").prop("disabled", false);
			$("#" + n + "_contractRate").prop("disabled", false);
		}
		if($("#" + n + "_externalRate").is(":checked")){
			$("#" + n + "_contractRate").prop("disabled", true);
			$("#" + n + "_effectiveRate").prop("disabled", true);
			$("#" + n + "_fixedValue").prop("disabled", true);
		}
	}
	var tabs = {};
	tabs[n] = "基本信息";
	tabs[n + "_rates"] = "利息重置";
	tabs[n + "_payments"] = "付息计划";
	tabs[n + "_floats"] = "浮动利率重置";
	tabs[n + "_pars"] = "还本计划";
	genTabs(detail, n + "_tabs", n + "_cons", tabs);
	
	genEditForm2(display, n, e.source, e.form, e.event, 3, alert, true);
	$("#" + n + "_title").text("更新-" + translate["cf"]);
	$("#smf_modal").modal("hide");
	$("#content_modal").modal("hide");
	$("#" + modal).find(".modal-footer").find("button").text("返回");
	$("#" + modal).modal();
	
	$("a[href='#" + display + "']").tab("show");
}

function cfUpdate2(cfID, name){
	var n = name || "cf";
	var detail = n + "_detail";
	var alert = n + "_alert";
	var display = n + "_display";
	var modal = n + "_modal";

	$("#" + detail).empty();
	var e = $.extend(true, {}, editForm);
	e.source.on = true;
	e.source.address = "cfDetail";
	e.source.inputs = {id:cfID};
	e.form.name = "cf_edit";
	
	e.form.func = function(data){
		ratesUpdate(cfID, n);
		paymentsUpdate(cfID, n);
		floatsUpdate(cfID, n);
		parsUpdate(cfID, n);
		
		$("#" + n + "_payments_tab").hide();
		$("#" + n + "_rates_tab").hide();
		$("#" + n + "_floats_tab").hide();
		
		if ($("#" + n + "_frequency").val() == SCHEDULE){
			$("#" + n + "_payments_tab").show();
		}
		if($("#" + n + "_cfType").val() == FLOAT){
			$("#" + n + "_floats_tab").show();
			$("#" + n + "_rates_tab").show();
		}
		if($("#" + n + "_cfType").val() == RESET){
			$("#" + n + "_rates_tab").show();
		}
		
		updateSelectOptions($("#" + n + "_paymentMonth"), fetch(getURL("paymentMonth"), {freq:$("#" + n + "_frequency").val()}));
		$("#" + n + "_paymentMonth").val(data.data[16]);
		$("#" + n + "_frequency").off("change").on("change", function(){
			var v = $(this).val();
			if (v == SCHEDULE){
				$("#" + n + "_paymentMonth").prop("disabled", true);
				$("#" + n + "_paymentDay").prop("disabled", true);
				$("#" + n + "_paymentN").prop("disabled", true);
				$("#" + n + "_paymentNType").prop("disabled", true);
				$("#" + n + "_payments_tab").show();
			}
			else{
				$("#" + n + "_paymentMonth").prop("disabled", false);
				$("#" + n + "_paymentDay").prop("disabled", false);
				$("#" + n + "_paymentN").prop("disabled", false);
				$("#" + n + "_paymentNType").prop("disabled", false);
				$("#" + n + "_payments_tab").hide();
			}
			updateSelectOptions($("#cf_paymentMonth"), fetch(getURL("paymentMonth"), {freq:v}));
		});
		
		$("#" + n + "_externalRate").off("change").on("change",function(){
			if ($(this).is(":checked")){
				$("#" + n + "_contractRate").prop("disabled", true);
				$("#" + n + "_effectiveRate").prop("disabled", true);
				$("#" + n + "_fixedValue").prop("disabled", true);
			}
			else{
				$("#" + n + "_contractRate").prop("disabled", false);
				$("#" + n + "_effectiveRate").prop("disabled", false);
				$("#" + n + "_fixedValue").prop("disabled", false);
			}
		});
		
		$("#" + n + "_cfType").off("change").on("change", function(){
			var cfType = $(this).val(); 
			if ( cfType == FIXEDRATE){
				$("#" + n + "_fixedValue").prop("disabled", false);
				$("#" + n + "_contractRate").prop("disabled", false);
				$("#" + n + "_rates_tab").hide();
				$("#" + n + "_floats_tab").hide();
			}
			else if (cfType == FLOAT){
				$("#" + n + "_fixedValue").prop("disabled", true);
				$("#" + n + "_contractRate").prop("disabled", false);
				$("#" + n + "_rates_tab").show();
				$("#" + n + "_floats_tab").show();
			}
			else if (cfType == RESET){
				$("#" + n + "_fixedValue").prop("disabled", true);
				$("#" + n + "_contractRate").prop("disabled", false);
				$("#" + n + "_rates_tab").show();
				$("#" + n + "_floats_tab").hide();
			}
			else if (cfType == FIXED){
				$("#" + n + "_rates_tab").hide();
				$("#" + n + "_floats_tab").hide();
			}
		});
	};
	e.event.create.on = false;
	e.event.update.on = true;
	e.event.update.source.address = "cfUpdate";
	e.event.update.source.inputs = {id:cfID};
	e.event.update.source.func = function(id, dataFor){
		//$cf.val(id);
		if($("#" + n + "_frequency").val() == SCHEDULE){
			$("#" + n + "_paymentMonth").prop("disabled", true);
			$("#" + n + "_paymentDay").prop("disabled", true);
			$("#" + n + "_paymentN").prop("disabled", true);
			$("#" + n + "_paymentNType").prop("disabled", true);
		}
		if ($("#" + n + "_cfType").val() == FIXED){
			$("#" + n + "_fixedValue").prop("disabled", false);
			$("#" + n + "_contractRate").prop("disabled", true);
		}
		else{
			$("#" + n + "_fixedValue").prop("disabled", false);
			$("#" + n + "_contractRate").prop("disabled", false);
		}
		if($("#" + n + "_externalRate").is(":checked")){
			$("#" + n + "_contractRate").prop("disabled", true);
			$("#" + n + "_effectiveRate").prop("disabled", true);
			$("#" + n + "_fixedValue").prop("disabled", true);
		}
	}
	var tabs = {};
	tabs[n] = "基本信息";
	tabs[n + "_rates"] = "利息重置";
	tabs[n + "_payments"] = "付息计划";
	tabs[n + "_floats"] = "浮动利率重置";
	tabs[n + "_pars"] = "还本计划";
	genTabs(detail, n + "_tabs", n + "_cons", tabs);
	
	genEditForm2(display, n, e.source, e.form, e.event, 3, alert, true);
	$("#smf_modal").modal("hide");
	$("#content_modal").modal("hide");
	$("#" + n + "_title").text("更新-" + translate["cf"]);
	$("#" + modal).find(".modal-footer").find("button").text("返回");
	$("#" + modal).modal();
	
	$("a[href='#" + display + "']").tab("show");
}

function ratesUpdate(cfID, n){
	var div = n + "_rates_div";
	var display = n + "_rates_display";
	var dataFor = n + "Rate";
	var alert = n + "_alert";

	$("#" + div).remove();
	var $div = $("<div id='" + div + "'></div>")

	$("#" + display).append($div);
	var f = $.extend(true, {}, tableForm);	
	
	f.source.address = "cfRateDetail";
	f.source.inputs = {id:cfID};
	f.form.name = "cfRates_table";
	f.form.func = function(){
		var $title = $("<div class='col-md-1'></div>");
		var $form = $("#form_" + dataFor + "_div");
		$title.insertBefore($form);
		$("<br><br>").insertAfter($title);
		$("<br>").insertBefore($title);
		$("#" + dataFor + "_add").text("增加利率");
		$("#" + dataFor + "_add").appendTo($title);
	};
	f.event.update.source.address = "cfRateUpdate";
	f.event.update.source.inputs = {pid:cfID};
	
	f.event.del.source.address = "cfRateRemove";
	f.event.del.source.inputs = {pid:cfID};
	
	f.event.add.source.address = "cfRateCreate";
	f.event.add.source.inputs = {pid:cfID};
	f.event.add.form.name = "cfRates_add";
	
	genTableForm(div, dataFor, f.source, f.form, f.event, alert, true);
	
}

function paymentsUpdate(cfID, n){
	var div = n + "_payments_div";
	var display = n + "_payments_display";
	var dataFor = n + "Payment";
	var alert = n + "_alert";

	$("#" + div).remove();
	var $div = $("<div id='" + div + "'></div>")
	var $title = $("<div class='col-md-1'></div>");
	$div.append("<br>");
	$div.append($title);
	$div.append("<br><br>");

	$("#payments_display").append($div);
	var f = $.extend(true, {}, tableForm);	
	
	f.source.address = "cfPaymentDetail";
	f.source.inputs = {id:cfID};
	f.form.name = "cfPayments_table";
	f.form.func = function(){
		var $title = $("<div class='col-md-1'></div>");
		var $form = $("#form_" + dataFor + "_div");
		$title.insertBefore($form);
		$("<br><br>").insertAfter($title);
		$("<br>").insertBefore($title);
		
		$("#cfPayment_add").text("增加付息计划");
		$("#cfPayment_add").appendTo($title);
	};
	f.event.update.source.address = "cfPaymentUpdate";
	f.event.update.source.inputs = {pid:cfID};
	
	f.event.del.source.address = "cfPaymentRemove";
	f.event.del.source.inputs = {pid:cfID};
	
	f.event.add.source.address = "cfPaymentCreate";
	f.event.add.source.inputs = {pid:cfID};
	f.event.add.form.name = "cfPayments_add";
	
	genTableForm(div, dataFor, f.source, f.form, f.event, alert, true);
	
}

function floatsUpdate(cfID, n){
	var div = n + "_floats_div";
	var display = n + "_floats_display";
	var dataFor = n + "Float";
	var alert = n + "_alert";

	$("#" + div).remove();
	var $div = $("<div id='" + div + "'></div>")
	$("#" + display).append($div);
	
	
	invoke(getURL("cfFloatDetail"), {id:cfID}, function(data){
		var e = $.extend(true, {}, editForm);
		var dd = data.data;
		
		if (dd){
			if (typeof(dd) == "object" && Object.keys(dd).length <= 0){
				floatsCreate(n, "cfFloat_create", cfID)
			}
			else{
				floatsCreateUpdate(n, dd, cfID)
			}
		}
		else{
			floatsCreate(n, "cfFloat_create", cfID)
		}
	}, "cf_alert"); 
}

function parsUpdate(cfID, n){
	var div = n + "_pars_div";
	var display = n + "_pars_display";
	var dataFor = n + "Par";
	var alert = n + "_alert";
	
	$("#" + div).remove();
	var $div = $("<div id='" + div + "'></div>")

	$("#" + display).append($div);
	var f = $.extend(true, {}, tableForm);	
	
	f.source.address = "cfParDetail";
	f.source.inputs = {id:cfID};
	f.form.name = "cfPars_table";
	f.form.func = function(){
		var $title = $("<div class='col-md-1'></div>");
		var $form = $("#form_" + dataFor + "_div");
		$title.insertBefore($form);
		$("<br><br>").insertAfter($title);
		$("<br>").insertBefore($title);
		$("#" + dataFor + "_add").text("增加还本计划");
		$("#" + dataFor + "_add").appendTo($title);
	};
	f.event.update.source.address = "cfParUpdate";
	f.event.update.source.inputs = {pid:cfID};
	
	f.event.del.source.address = "cfParRemove";
	f.event.del.source.inputs = {pid:cfID};
	
	f.event.add.source.address = "cfParCreate";
	f.event.add.source.inputs = {pid:cfID};
	f.event.add.form.name = "cfPars_add";
	
	genTableForm(div, dataFor, f.source, f.form, f.event, alert, true);
	
}

function cfConfig(cf_id, name, modal){
	var n = name || "cf";
	var $cf = $("#" + cf_id);
	if ($("#smf_update").text()){
		$("#smf_update").parent().prepend("&nbsp;&nbsp;");
		$("<button id='smf_" + n + "_update' class='btn btn-info'>现金流配置</button>").prependTo($("#smf_update").parent());
		
		//$("#smf_" + n + "_update").prop("disabled", true);
		$("#smf_" + n + "_update").on("click", function(){
			if($cf.val()){
				cfUpdate(cf_id, n);
			}
			else{
				cfCreate(cf_id, n);
			}
			return false;
		});
	}
	else{
		$("#smf_create").parent().prepend("&nbsp;&nbsp;");
		$("<button id='smf_" + n + "_create' class='btn btn-info'>现金流配置</button>").prependTo($("#smf_create").parent());
		$("#smf_" + n + "_create").on("click", function(){
			if($cf.val()){
				cfUpdate(cf_id, n);
			}
			else{
				cfCreate(cf_id, n);
			}
			return false;
		});
	}
	
	$("#" + n + "_modal").on("hidden.bs.modal", function(){
		if($("#content_modal").attr("id")){
			$("#content_modal").modal();
		}
		else{
			$("#smf_modal").modal();
		}
		
	});
}

function cfConfigLongShort(cf_id, name){
	var n = name || "cf";
	var $cf = $("#" + cf_id);
	if ($("#smf_update").text()){
		var $div = $("<div class='col-md-2'></div>");
		$div.append($("<button id='smf_" + n + "_update' class='btn btn-info'>...</button>"));
		$div.insertAfter($("#" + cf_id).parent());
		
		$("#smf_" + n + "_update").prop("disabled", false);
		$("#smf_" + n + "_update").on("click", function(){
			if($cf.val()){
				cfUpdate(cf_id, n);
			}
			else{
				cfCreate(cf_id, n);
			}
			return false;
		});
	}
	else{
		var $div = $("<div class='col-md-2'></div>");
		$div.append($("<button id='smf_" + n + "_create' class='btn btn-info'>...</button>"));
		$div.insertAfter($("#" + cf_id).parent());
		
		$("#smf_" + name + "_create").on("click", function(){
			if($cf.val()){
				cfUpdate(cf_id, n);
			}
			else{
				cfCreate(cf_id, n);
			}
			return false;
		});
	}
	$("#" + n + "_modal").on("hidden.bs.modal", function(){
		if($("#content_modal").attr("id")){
			if($("#smf").parent().attr("id") == "smf_detail"){
				$("#smf_modal").modal();
			}
			else{
				$("#content_modal").modal();
			}
		}
		else{
			$("#smf_modal").modal();
		}
	});
}

function smfConfig(smf_id, name, longCurrency_id, shortCurrency_id){
	var n = name || "smf";
	var $smf = $("#" + smf_id);
	var $long = $("#" + longCurrency_id);
	var $short = $("#" + shortCurrency_id);
	
	if ($("#portTrade_update").text()){
		var $div = $("<div class='col-md-2'></div>");
		$div.append($("<button id='portTrade_" + n + "_update' class='btn btn-info'>合约设置</button>"));
		$div.insertAfter($smf.parent());
		
		$("#portTrade_" + n + "_update").prop("disabled", true);
		$("#portTrade_" + n + "_update").off("click").on("click", function(){
			if($smf.val()){
				$smf.prop("disabled", true);
				smfUpdate(smf_id, n);
			}
			else{
				smfCreate(smf_id, n);
			}
			return false;
		});
	}
	else{
		var $div = $("<div class='col-md-2'></div>");
		$div.append($("<button id='portTrade_" + n + "_create' class='btn btn-info'>合约设置</button>"));
		$div.insertAfter($smf.parent());
		$("#portTrade_" + n + "_create").off("click").on("click", function(){
			if($smf.val()){
				smfUpdate(smf_id, n);
			}
			else{
				smfCreate(smf_id, n);
			}
			return false;
		});
	}
	$("#" + n + "_modal").on("hidden.bs.modal", function(){
		if ($smf.val()){
			var currencies = fetch(getURL("smfLongShortCurrency"), {id:$smf.val()});
			if ($long){
				$long.val(currencies["long"]);
				$long.trigger("change");
			}
			if ($short){
				$short.val(currencies["short"]);
				$short.trigger("change");
			}
		}
		$("#trade_modal").modal();
	});
}

function smfCreate(smf_id, n){
	var detail = n + "_detail";
	var title = n + "_title";
	var modal = n + "_modal";
	var alert = n + "_alert";
	
	var info = {};
	info.tp = "smf";
	info.category = SMF;
	info.issuetype = $("#assetType").val();
	var assetForm = getData(fetch(getURL("assetForm"), {assetType:info.issuetype}));
	var smfForm = assetForm.smfForm;
	info.messageHeader = assetForm.messageHeader || "smfoperation";
	var e = $.extend(true, {}, tradeEditForm)
	e.source.on = false;
	smfForm["sets"] = smfForm.createSets;
	smfForm.forms.splice(0,1);
	smfForm.buttons.splice(1,1);
	
	e.form.form = smfForm;
	e.form.inputs = info;
	e.form.func = smfForm.func;
	e.event.create.on = true;
	e.event.create.source.address = "smfCreate";
	e.event.update.on = false;
	
	e.event.create.source.func = function(dataFor, new_id){
		$("#" + smf_id).val(new_id);
		$("#" + smf_id).trigger("change");
		$("#" + smf_id).prop("disabled", true);
	}
	$("#" + detail).empty();
	genTradeEditForm(detail, info["tp"], e.source, e.form, e.event, 4, alert, false);
	
	$("#" + title).text("新建-" + translate["smf"]);
	$("#trade_modal").modal("hide");
	
	$("#" + modal).find(".modal-footer").find("button").text("返回");
	$("#" + modal).modal();
	
	$("#" + title).text(translate["smf"] + "创建");
}

function smfUpdate(smf_id, n){
	$smf = $("#" + smf_id);
	var smfID = $smf.val().split("|")[0];
	
	invoke(getURL("smfDetail"), {id:smfID}, function(data){
		var detail = n + "_detail";
		var title = n + "_title";
		var modal = n + "_modal";
		var alert = n + "_alert"
		
		var info = {};
		info.issuetype = $("#assetType").val();;
		info.tp = "smf";
		info.category = SMF;
		
		var assetForm = getData(fetch(getURL("assetForm"), {assetType:info["issuetype"]}));
		var smfForm = assetForm.smfForm;
		info.messageHeader = assetForm.messageHeader || "smfoperation";
		smfForm["sets"] = smfForm.editSets;
		smfForm.buttons.splice(0,1);
		$.each(smfForm.forms, function(i, form){
			if (data.data instanceof Array){
				info[form.cat] = data.data[i];
			}
			else{
				info[form.cat] = data.data[form.cat];
			}
		});
		var f = $.extend(true, {}, tradeEditForm)
	    f.source.on = false;
		f.source.value = data.data;
		
		f.form.form = smfForm;
		f.form.inputs = info;
		f.form.func = smfForm.func;
		
		f.event.update.on = true;
		f.event.update.source.inputs["id"] = smfID;
		f.event.update.source.address = "smfUpdate";
		
		f.event.create.on = false;
		
	    $("#" + detail).empty();
		genTradeEditForm(detail, info["tp"], f.source, f.form, f.event, 4, alert, true);
		
		$("#trade_modal").modal("hide");
		$("#" + title).text("编号：" + smfID + "-" + translate[info.tp] + "详情");
		$("#" + modal).find(".modal-footer").find("button").text("返回");
		$("#" + modal).modal();
		
	}, "trade_alert");
	
}

function fxOptionTradeLongShort(sec_id, lot_id, type_id, longC_id, longP_id, shortC_id, shortP_id, port_id, tradeDate_id){
	var cusip = $("#" + sec_id).val()||"";
	var portid = $("#" + port_id).val();
	
	var tradeType = $("#" + type_id).val();
	var BUY = 780;
	var SELL = 781;
	
	var FULL_EXCERCISE = 782;
	var NET_EXCERCISE = 783;
	var ABONDON_EXCERCISE = 784;
	
	var $longC = $("#" + longC_id);
	var $longP = $("#" + longP_id);
	var $shortC = $("#" + shortC_id);
	var $shortP = $("#" + shortP_id);
	var $trd = $("#" + tradeDate_id);
	var $lot = $("#" + lot_id);
	
	var currencies = fetch(getURL("smfLongShortCurrency"), {id:cusip}, "trade_alert");
	var principals = fetch(getURL("smfLongShortPrincipal"), {id:cusip}, "trade_alert");
	
	if (tradeType == SELL){
		$longC.val(currencies["short"]);
		$shortC.val(currencies["long"]);
		
		$longP.val(principals["short"]);
		$shortP.val(principals["long"]);
		$longP.trigger("change");
	}
	else if (tradeType == BUY){
		$longC.val(currencies["long"]);
		$shortC.val(currencies["short"]);
		
		$longP.val(principals["long"]);
		$shortP.val(principals["short"]);
		$longP.trigger("change");
	}
	
	else if (tradeType == FULL_EXCERCISE || tradeType == NET_EXCERCISE || tradeType == ABONDON_EXCERCISE){
		var currentAmts = fetch(getURL("smfCurrentAmounts"), {id:cusip, port:portid, date:$trd.val(), contract:$lot.val()||""}, "trade_alert");
		if (currentAmts >=0 ){
			$longC.val(currencies["long"]);
			$shortC.val(currencies["short"]);
			
			$longP.val(principals["long"]);
			$shortP.val(principals["short"]);
			$longP.trigger("change");
		}
		else{
			$longC.val(currencies["short"]);
			$shortC.val(currencies["long"]);
			
			$longP.val(principals["short"]);
			$shortP.val(principals["long"]);
			$longP.trigger("change");
		}
	}
	
	$.each([$trd, $lot], function(i,v){
		$(v).off("change").on("change", function(){
			if (tradeType == SELL){
				$longC.val(currencies["short"]);
				$shortC.val(currencies["long"]);
				
				$longP.val(principals["short"]);
				$shortP.val(principals["long"]);
				$longP.trigger("change");
			}
			else if (tradeType == BUY){
				$longC.val(currencies["long"]);
				$shortC.val(currencies["short"]);
				
				$longP.val(principals["long"]);
				$shortP.val(principals["short"]);
				$longP.trigger("change");
			}
			
			else if (tradeType == FULL_EXCERCISE || tradeType == NET_EXCERCISE || tradeType == ABONDON_EXCERCISE){
				var currentAmts = fetch(getURL("smfCurrentAmounts"), {id:cusip, port:portid, date:$trd.val(), contract:$lot.val()||""}, "trade_alert");
				if (currentAmts >=0 ){
					$longC.val(currencies["long"]);
					$shortC.val(currencies["short"]);
					
					$longP.val(principals["long"]);
					$shortP.val(principals["short"]);
					$longP.trigger("change");
				}
				else{
					$longC.val(currencies["short"]);
					$shortC.val(currencies["long"]);
					
					$longP.val(principals["short"]);
					$shortP.val(principals["long"]);
					$longP.trigger("change");
				}
			}
		});
	});
	
}

function fxOptionLongShort(ex_id, type_id, long_id, short_id){
	var CALL = 0;
	var PUT = 1;
	var CNY = 1;

	var $type = $("#" + type_id);
	var $long = $("#" + long_id);
	var $short = $("#" + short_id);
	var $ex = $("#" + ex_id);
	
	$.each([$type, $long, $short], function(i,v){
		$(v).on("change", function(){
			if ($type.val() == CALL){
				$ex.val(($long.val()||0) / ($short.val()||1))
			}
			else{
				$ex.val(($short.val()||0) / ($long.val()||1))
			}
		});
	});
	
	$ex.off("change").on("change", function(){
		if ($type.val() == CALL){
			$long.val(($short.val()||0) * ($ex.val()||1))
		}
		else{
			$short.val(($long.val()||0) * ($ex.val()||1))
		}
	});
	
}
function fxOptionExLabel(ex_id, type_id, long_id, short_id){
	var CALL = 0;
	var PUT = 1;
	var CNY = 1;

	var $type = $("#" + type_id);
	var $long = $("#" + long_id);
	var $short = $("#" + short_id);

	var priceCurrency;
	var baseCurrency;
	var $label = $("#" + ex_id).parent().prev();
	var preL = $label.text();
	if ($type.val() == CALL){
		priceCurrency = $long.val()||CNY;
		baseCurrency = $short.val()||CNY;
	}
	else{
		priceCurrency = $short.val()||CNY;
		baseCurrency = $long.val()||CNY;
	}

	var currencies = {};
	$.each($("#longCurrency").find("option"), function(i, opt){
		currencies[$(opt).prop("value")] = $(opt).text();
	});

	$label.text(preL + " " + currencies[priceCurrency] + "/" + currencies[baseCurrency]);

	$.each([$type, $long, $short], function(i,v){
		$(v).off("change").on("change", function(){
			if ($type.val() == CALL){
				priceCurrency = $long.val()||CNY;
				baseCurrency = $short.val()||CNY;
			}
			else{
				priceCurrency = $short.val()||CNY;
				baseCurrency = $long.val()||CNY;
			}

			var currenies = {};
			$.each($("#longCurrency").find("option"), function(i, opt){
				currencies[$(opt).prop("value")] = $(opt).text();
			});

			$label.text(preL + " " + currencies[priceCurrency] + "/" + currencies[baseCurrency]);
		});
	});
}

function fxOptionSettleCash(longP_id, longFX_id, shortP_id, shortFX_id, settleFX_id, settleCash_id){
	var $longP = $("#" + longP_id);
	var $shortP = $("#" + shortP_id);
	
	var $longFX = $("#" + longFX_id);
	var $shortFX = $("#" + shortFX_id);
	var $settleFX = $("#" + settleFX_id);
	
	var settleCash = ($longP.val() * $longFX.val() - $shortP.val()*$shortFX.val()) / $settleFX.val();
	$("#" + settleCash_id).val(settleCash);
	
	$.each([$longP, $longFX, $shortFX, $settleFX], function(i,v){
		$(v).off("change").on("change", function(){
			var settleCash = ($longP.val() * $longFX.val() - $shortP.val()*$shortFX.val()) / $settleFX.val();
			$("#" + settleCash_id).val(settleCash);
		});
	});
}

function performanceFee(port_id, secid_id, contract_id, shares_id, expense_id, clawback_id, tradeDate_id){
	var $port = $("#" + port_id);
	var $secid = $("#" + secid_id);
	var $contract = $("#" + contract_id);
	var $shares = $("#" + shares_id);
	var $tradeDate = $("#" + tradeDate_id);
	
	var $expense = $("#" + expense_id);
	var $clawback = $("#" + clawback_id);
	
	var fee = fetch(getURL("portPerformanceFeeDetail"),{port:$port.val(), secid:$secid.val(), contract:$contract.val()},"alert_all");
	if (fee && fee.data && fee.data.activeFlag == true){
		$.each([$contract, $shares, $tradeDate], function(i,v){
			$(v).on("change", function(){
				if ($tradeDate.val() != "" && $shares.val() != ""){
					var exps = fetch(getURL("portPerformanceFee"), {port:$port.val(), secid:$secid.val(), contract:$contract.val(),end:$tradeDate.val(), shares:$shares.val()}, "trade_alert");
					$expense.val(exps['performanceFee']);
					$clawback.val(exps['clawback']);
					$expense.trigger("change");
				}
			});
		});
	}
	else{
		$expense.prop("disabled", true);
		$clawback.prop("disabled", true);
		$expense.prop("editable", false);
		$clawback.prop("editable", false);
		$expense.val(0);
		$clawback.val(0);
	}
	
}

function incomePayable(port_id, secid_id, contract_id, shares_id, income_id, tradeDate_id){
	var $port = $("#" + port_id);
	var $secid = $("#" + secid_id);
	var $contract = $("#" + contract_id);
	var $shares = $("#" + shares_id);
	var $tradeDate = $("#" + tradeDate_id);
	
	var $income = $("#" + income_id);
	
	$.each([$contract, $shares, $tradeDate], function(i,v){
		$(v).on("change", function(){
			if ($tradeDate.val() != "" && $shares.val() != ""){
				var incomePayable = fetch(getURL("portIncomePayable"), {port:$port.val(), secid:$secid.val(), contract:$contract.val(),tradeDate:$tradeDate.val(), shares:$shares.val()||0}, "trade_alert");
				$income.val(incomePayable||0);
				$income.trigger("change");
			}
		});
	});
}

function incomeSalePayable(port_id, secid_id, contract_id, shares_id, income_id, tradeDate_id){
	var $port = $("#" + port_id);
	var $secid = $("#" + secid_id);
	var $contract = $("#" + contract_id);
	var $shares = $("#" + shares_id);
	var $tradeDate = $("#" + tradeDate_id);
	
	var $income = $("#" + income_id);
	
	$.each([$contract, $shares, $tradeDate], function(i,v){
		$(v).on("change", function(){
			if ($tradeDate.val() != "" && $shares.val() != ""){
				var incomeSalePayable = fetch(getURL("incomeSalePayable"), {port:$port.val(), secid:$secid.val(), contract:$contract.val(),tradeDate:$tradeDate.val(), shares:$shares.val()||0}, "trade_alert");
				$income.val(incomeSalePayable||0);
				$income.trigger("change");
			}
		});
	});
}

function batchNAV(port_id, secid_id, contract_id, price_id, tradeDate_id){
	var $port = $("#" + port_id);
	var $secid = $("#" + secid_id);
	var $contract = $("#" + contract_id);
	var $tradeDate = $("#" + tradeDate_id);
	
	var $price = $("#" + price_id);
	
	$.each([$contract, $tradeDate], function(i,v){
		$(v).on("change", function(){
			if ($tradeDate.val() != ""){
				var nav = fetch(getURL("portBatchNAV"), {port:$port.val(), secid:$secid.val(), contract:$contract.val(),tradeDate:$tradeDate.val()}, "trade_alert");
				$price.val(nav||0);
				$price.trigger("change");
			}
		});
	});
}

function checkPerformanceFee(port_id, secid_id, contract_id, fee_id, clawBack_id, tradeDate_id){
	var $port = $("#" + port_id);
	var $secid = $("#" + secid_id);
	var $contract = $("#" + contract_id);
	var $tradeDate = $("#" + tradeDate_id);
	var $fee = $("#" + fee_id);
	var $clawBack_id = $("#" + clawBack_id);
	
	var fee = fetch(getURL("portPerformanceFeeDetail"),{port:$port.val(), secid:$secid.val(), contract:$contract.val()},"alert_all");
	if (fee.data){
		$fee.prop("disabled", false);
		$clawBack.prop("disabled", false);
		$fee.val(0);
		$clawBack.val(0);
	}
	
}

function expensePayable(port_id, secid_id, contract_id, expType_id, expense_id, tradeDate_id){
	var $port = $("#" + port_id);
	var $secid = $("#" + secid_id);
	var $contract = $("#" + contract_id);
	var $tradeDate = $("#" + tradeDate_id);
	var $expType = $("#" + expType_id);
	
	var $expense = $("#" + expense_id);
	
	$.each([$expType, $tradeDate], function(i,v){
		$(v).on("change", function(){
			if ($tradeDate.val() != ""){
				var exp = fetch(getURL("portExpensePayable"), {port:$port.val(), secid:$secid.val(), contract:$contract.val(), expenseType:$expType.val(), tradeDate:$tradeDate.val()}, "trade_alert");
				$expense.val(exp||0);
				$expense.trigger("change");
			}
		});
	});
}

function enableItem(checkbox_id, item_id){
	var $checkbox = $("#" + checkbox_id);
	var $item = $("#" + item_id);
	
	$checkbox.on("change", function(){
		if($checkbox.is(":checked")){
			$item.prop("disabled", false);
		}
		else{
			$item.prop("disabled", true);
		}
	});
}
	
