function rndStr(len) {
  len = len || 32;
  var $chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefhijkmnprstwxyz0123456789";
  var maxPos = $chars.length;
  var str = "";
  for (i = 0; i < len; i++) {
    str += $chars.charAt(Math.floor(Math.random() * maxPos));
  }
  return str;
}

function paramCopy(obj) {
  if (typeof obj != "object") {
    return obj;
  }
  var newobj = {};
  for (var attr in obj) {
    newobj[attr] = paramCopy(obj[attr]);
  }
  return newobj;
}

function genMenu(){
	var titles = getData("/menu");
	var header = titles.header;
	var menus = titles.menus;
	
	var $T = $("<header class='navbar navbar-default' id='header' role='banner'></header>");
	var $T_container = $("<div class='container-fluid'></div>");
	
	var $T_header = $("<div class='col-md-2'></div>");
	var $T_menu = $("<div class='col-md-10'></div>");
	
	$T_header.append("<div class='navbar-header'><a class='navbar-brand'><img alt='Brand' src='" +  site_logo + "'>" + header.name + "</a></div>");
	$T_container.append($T_header);
	
//	var perms = $.parseJSON($.cookie(jixu_menu_cookie));
//	if (! perms){
//		perms = setMenuPermission();
//	}
	var perms = setMenuPermission()
	
	$T_uls = $("<div class='collapse navbar-collapse'></div>");
	$T_nav = $("<ul class='nav navbar-nav'></ul>");
	$.each(menus, function(index, menu){	
		if (menu.type == "li" && perms[menu.url]){
			$T_nav.append("<li id='" + menu.id + "'><a href='" + prefix + menu.url + "'>" + menu.name + "</a></li>");
		}
		else if (menu.type == "dropdown"){
			var $li = $("<li id='" + menu.id + "' class='dropdown'>");
			var $a = $("<a href='#' class='dropdown-toggle' data-toggle='dropdown' role='button' aria-haspopup='true' aria-expanded='false'>系统配置<span class='caret'></span></a>");
			$li.append($a);
			var $ul = $("<ul class='dropdown-menu'></ul>");
			$.each(menu.items, function(index, item){
				if (perms[item.url]){
					$ul.append("<li><a href='" + prefix + item.url + "'>" + item.name + "</a></li>");
				}
			});
			$li.append($ul);
			$T_nav.append($li);
		}
	});
	$T_uls.append($T_nav);
	$T_uls.append(genUserBar());
	$T_menu.append($T_uls);
	$T_container.append($T_menu);
	$T.append($T_container);

	return $T;
}

function genUserBar(){
	//var username = $.cookie(jixu_user_cookie);
	var username = "适配器管理员"
	var $bar = $("<ul class='nav navbar-nav navbar-right'><ur>");
	var $dropdown = $("<li class='dropdown'></li>");
	var $user = $("<a href='#' class='dropdown-toggle' data-toggle='dropdown' role='button' aria-haspopup='true' aria-expanded='false'>当前用户:  " + username + "<span class='caret'></a>");
	$dropdown.append($user);
	var $menu = $("<ul class='dropdown-menu'><li><a href='#' id='user_profile'>资料</a></li><li><a href='#' id='user_logout'>登出</a></li></ul>");
	$dropdown.append($menu);
	$bar.append($dropdown);
	
	$(document).on("click", "#user_logout", function(){
		invoke(getURL("logout"),{}, function(status){
			if (status){
				$.removeCookie(jixu_user_cookie);
				$.removeCookie(jixu_menu_cookie);
				window.location.href="jixulogin.do";
			}
		},"alertAll");
		return false;
	});
	return $bar;
}

function setMenuPermission(){
	var perms = $.cookie(jixu_menu_cookie);
	if (! perms){
		perms = {};
		var titles = getData("/menu");
		var menus = titles.menus;
		$.each(menus, function(i, menu){
			if (menu.type == "li"){
				perms[menu.url] = fetch(getURL(verifyPermFunc), {checkURL:menu.url})["status"];
			}
			else if (menu.type == "dropdown"){
				$.each(menu.items, function(i, item){
					perms[item.url] = fetch(getURL(verifyPermFunc), {checkURL:item.url})["status"];
				});
			}
		});
		$.cookie("jixu_menu_permissions", JSON.stringify(perms), {path: '/'});
	}
	else {
		perms = $.parseJSON(perms);
	}
	return perms;
}

function setDatePick(){
	$(".form_datetime").datetimepicker({
		format: 'yyyy-mm-dd',
		pickTime: false,
		minView: 2,
		autoclose: true,
		language: 'zh-CN'
	});
}

function getData(file_name){
	var data = null;
	$.ajax({
		url: prefix + "/data" + file_name + ".json",
		async: false,
		dataType: "json",
		success: function(json){
			data = json;
		}	
	});
	return data;
}

function getForm(form_name) {
	var data = null;
	$.ajax({
		url: getFormURL(form_name),
		async: false,
		dataType: "json",
		success: function(json){
			data = json;
		}	
	});
	return data;
}

function fetch(address, inputs, alert) {
	var data = null;
	logInfo(adp_urls_r[address], inputs, []);
	$.ajax({
		 url: address,
		 async: false,
	     type: "POST",
	     data: inputs,
	     success: function(json){
	    	if (json == '' || typeof(json) == 'undefined'){
	    		data = ''
	    	}
	    	else{
	    		data = $.parseJSON(json);
	    	}
			if (typeof(alert) == "undefined"){
    			alert = "alertAll";
    		}
			if (typeof(data.issue) == "object"){
				$("#" + alert).append(genAlert(data.issue.level, data.issue.detail));
				data = null;
			}
	     },
		error: function(xhr, ajaxOptions, thrownError){
			if (typeof(alert) == "undefined"){
    			alert = "alertAll";
    		}
    	 	var msg = "调用方法'" + address + "' 时发生错误;";
    	 	$("#" + alert).empty();
			$("#" + alert).append(genAlert("fail", msg));
		}
	});
	return data;
}

function invoke(address, inputs, func, alert) {
	$.ajax({
		 url: address,
		 async: true,
	     type: "POST",
	     data: inputs,
	     beforeSend: function(){
	    	 $("#spinner").show(); 
	     },
	     success: function(json){
	    	var data;
	    	if (typeof(json) == "undefined" || json == ''){
	    		data = ''
	    	}
	    	else{
	    		data = $.parseJSON(json);
	    	}
	    	
	    	if (typeof(alert) == "undefined"){
    			alert = "alertAll";
    		}
	    	if (typeof(data.issue) == "object"){
	    		alerts(alert, data.issue.level, data.issue.detail);
				//$("#spinner").hide();
				return;
			}
    		if (typeof(data) == "object" && Object.keys(data).length <= 0){
				//alerts(alert, "warn", "查询结果为空!");
				//$("#spinner").hide();
    		}
    		if (typeof(data.data) == "object" && Object.keys(data.data).length <= 0){
    			//alerts(alert, "warn", "查询结果为空!");
				//$("#spinner").hide();
    		}
    		if (typeof(data.data) == "undefined"){
				//$("#spinner").hide();
				data.data = [];
	    	}
    		func(data);
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
				 //$("#spinner").hide();
				console.log(xhr.responseText);
		 },
		 complete: function(){
			 $("#spinner").hide();
		 }
	});
}

function loginInvoke(address, inputs, func, alert) {
	$.ajax({
		 url: address,
		 async: true,
	     type: "POST",
	     data: inputs,
	     beforeSend: function(){
	    	 $("#loginSpin").show(); 
	     },
	     success: function(json){
	    	var data = $.parseJSON(json);
	    	if (typeof(alert) == "undefined"){
    			alert = "alertAll";
    		}
	    	if (typeof(data.issue) == "object"){
	    		alerts(alert, data.issue.level, data.issue.detail);
				$("#loginSpin").hide();
				return;
			}
    		if (typeof(data) == "object" && Object.keys(data).length <= 0){
				alerts(alert, "warn", "查询结果为空!");
				$("#loginSpin").hide();
    		}
    		if (typeof(data.data) == "object" && Object.keys(data.data).length <= 0){
    			alerts(alert, "warn", "查询结果为空!");
				$("#loginSpin").hide();
    		}
    		if (typeof(data.data) == "undefined"){
				$("#loginSpin").hide();
				data.data = [];
	    	}
    		$("#loginSpin").hide();
    		func(data);
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
				$("#loginSpin").hide();
				console.log(xhr.responseText);
		 },
		 complete: function(){
			 
		 }
	});
}

function getURL(name) {
	return prefix + adp_urls[name];
}

function getFormURL(name) {
	//console.log("Render Form Name: " + name);
	//console.log("Render Form URL: " + form_folder + forms[name]);
	return prefix + form_folder + adp_forms[name];
}

function drawModal(target_id, name){
	var $div = $("<div class='modal lg' id='" + name + "_modal' tabindex='-1' role='dialog' aria-labelledby='" + name + "_modal' aria-hidden='true'><div class='modal-dialog'></div></div>") 
	var $content = $("<div class='modal-content'></div>");
	var $header = $("<div class='modal-header'></div>")
	$header.append("<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>x</button>");
	$header.append("<h3 class='modal-title' id='" + name + "_title'></h3>");
	var $body = $("<div class='modal-body'><div id='" + name + "_alert'></div><div id='" + name + "_detail'></div></div>");
	var $footer = $("<div class='modal-footer'><button type='button' class='btn btn-default' data-dismiss='modal'>取消</button>");
	$content.append($header);
	$content.append($body);
	$content.append($footer);
	$div.append($content);
	$div.insertBefore($("#" + target_id));
	$("#" + name + "_modal").on("show.bs.modal", function (e) {
		  $("#" + name + "_alert").empty();
	})
}

function drawSmallModal(target_id, name){
	var $div = $("<div class='modal sm' id='" + name + "_modal' tabindex='-1' role='dialog' aria-labelledby='" + name + "_modal' aria-hidden='true'><div class='modal-dialog'></div></div>") 
	var $content = $("<div class='modal-content'></div>");
	var $header = $("<div class='modal-header'></div>")
	$header.append("<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>x</button>");
	$header.append("<h3 class='modal-title' id='" + name + "_title'></h3>");
	var $body = $("<div class='modal-body'><div id='" + name + "_alert'></div><div id='" + name + "_detail'></div></div>");
	var $footer = $("<div class='modal-footer'><button type='button' class='btn btn-default' data-dismiss='modal'>取消</button>");
	$content.append($header);
	$content.append($body);
	$content.append($footer);
	$div.append($content);
	$div.insertBefore($("#" + target_id));
	$("#" + name + "_modal").on("show.bs.modal", function (e) {
		  $("#" + name + "_alert").empty();
	})
}

function drawPortfolioSelect(form_template){
	drawFilterForm("title_form", "portfolio", form_template, {}, false);
	updateSelectOptions($("#portfolio_port_filter"), fetch(getURL("portShortList"), {client:$("#portfolio_client_filter").val()}));
	$("#portfolio_client_filter").on("change", function(){
		var options = fetch(getURL("portShortList"), {client:$("#portfolio_client_filter").val()});
		updateSelectOptions($("#portfolio_port_filter"), options);
	});
}

function drawPagTable(target_id, address, inputs, preFunc, postFunc, alert){
	if (!alert){
		alert = "alertAll";
	}
	inputs.num = 1;
	logInfo(address, inputs, []);
	invoke(getURL(address), inputs, function(data){
		$("#" + target_id).empty();
		if (typeof(preFunc) == "function"){
			data = preFunc(data);
		}
		var iid = drawTable(target_id, data);
		if (typeof(postFunc) == "function"){
			postFunc();
		}
		if (data.pages){
			var $pagination = genPagination(iid, data.pages);
			$pagination.on("page", function(event, num){
				inputs.num = num;
				logInfo(address, inputs, []);
				invoke(getURL(address), inputs, function(data){
					if (typeof(preFunc) == "function"){
						data = preFunc(data);
					}
					$("#" + iid).remove();
					iid = drawTable(target_id, data);
					$("#" + iid + "_pag").insertAfter($("#" + iid));
					if (typeof(postFunc) == "function"){
						postFunc();
					}
				});
			});
		}
	},alert);
}

function drawTable(dom_id, val){
	var iid = dom_id + "_table";
	var $table = $("<table class='table table-hover'></table>");
	$table.attr("id", iid);
	var titles = val.titles;
	var data;
	if(val.data){
		data = val.data;
	}
	else{
		data = [];
	}
	var data = val.data;
	$title_tr = $("<tr><th></th></tr>");
	for (var i =0; i< titles.length; i++){
		$title_tr.append("<th>" + titles[i] + "</th>");
	}
	$title_tr.append("<th></th>")
	$table.append($title_tr);
	
	for (var i=0; i < data.length; i++) {
		$tr = $("<tr><th></th></tr>");
		for (var j=0; j < titles.length; j++){
			$tr.append("<td>" + data[i][j] + "</td>");
		}
		$table.append($tr); 
	}
	$("#" + dom_id).append($table);
	return iid;
}

function addTableFormTr(target_id, id, dataFor, form_name, inputs, disabled){
	var form_data = getForm(form_name);
	var forms = form_data.forms;
	var titles = form_data.titles;
	var buttons = form_data.buttons;
	
	var $form = $("<form class='form-horizontal' id='" + dataFor + "_" + id + "'></form>");
	var $table = $("<table class='table table-hover'></table>");
	
	if ($("#" + dataFor + "_title").length <= 0){
		var $title_tr = $("<tr id='"+ dataFor + "_title'><th></th></tr>");
		for (var i = 1; i < titles.length; i++){
			$title_tr.append("<th>" + titles[i] + "</th>");
		}
		$title_tr.append("<th></th>");
		if ($("#" + target_id).find("table").length <= 0){
			$table.append($title_tr);
		}
		else{
			$title_tr.insertBefore($("#" + target_id).find("table").find("tr").eq(0));
		}
	}
	var $tr = $("<tr data-for='" + dataFor + "_tr' id='" + id + "'><th></th></tr>");
	forms[0].value = id;
	var rules = {};
	
	for (var j = 1; j < forms.length; j++) {
		var $td = $("<td></td>");
		var dom_id = dataFor + "_" + id + "_" + forms[j].label;
		var r = forms[j].rules;
		if (typeof(r) != "undefined" && r !="" && Object.keys(r).length > 0){
			rules[dom_id] = r;
		}
		var ed = true;
		if (typeof(forms[j].editable) != "undefined"){
			ed = forms[j].editable;
		}
		
		if (forms[j].type == "input") {
			var $input = genInput(dom_id, dataFor + "_" + id, forms[j].value, disabled, ed, "sm");
			$td.append($input);
		}
		else if (forms[j].type == "select") {
			var options;
			if (forms[j].options.type == "fetch"){
				if (typeof(forms[j].options.inputs) != "undefined"){
					options = fetch(getURL(forms[j].options.url), forms[j].options.inputs);
				}
				else {
					options = fetch(getURL(forms[j].options.url), {});
				}
			}
			else if (forms[j].options.type == "function"){
				options = fetch(getURL(forms[j].options.url), inputs[id + "_" + forms[j].label]);
			}
			else if (forms[j].options.type == "value"){
				options = forms[j].options.values;
			}
			
			$select = genSelect(dom_id, dataFor + "_" + id, options, forms[j].value, disabled, ed, "sm");
			$td.append($select);
		}
		else if (forms[j].type == "auto") {
			var options;
			if (forms[j].options.type == "fetch"){
				options = fetch(getURL(forms[j].options.url), "");
			}
			else if (forms[j].options.type == "function"){
				options = fetch(getURL(forms[j].options.url), inputs[id + "_" + forms[j].label]);
			}
			else if (forms[j].options.type == "value"){
				options = forms[j].options.values;
			}
			
			$auto = genAuto(dom_id, dataFor + "_" + id, options, forms[j].value, disabled, ed, "sm");
			$td.append($auto);
		}
		else if (forms[j].type == "label") {
			$td.append(forms[j].value);
		}
		else if (forms[j].type == "date") {
			var $input = genDateInput(dom_id, dataFor + "_" + id, forms[j].value, disabled, ed, "sm");
			$td.append($input);
		}
		else if (forms[j].type == "checkbox"){
			var $checkbox = genCheckbox2(dom_id, dataFor + "_" + id, forms[j].value, disabled, ed)
			$td.append($checkbox);
		}
		$tr.append($td);
	}
	var $b_td = $("<td></td>");
	for (var k = 0; k < buttons.length; k++){
		var dom_id = dataFor + "_" + id + "_" + buttons[k].label
		if (buttons[k].label == "submit") {
			$b_td.append($("<button id='" + dom_id + "' class='btn btn-success' data-for='" + dataFor + "_submit'>" + buttons[k].value +"</button>"));
		}
		else if (buttons[k].label == "update") {
			$b_td.append($("<button id='" + dom_id + "' class='btn btn-success' data-for='" + dataFor + "_update'>" + buttons[k].value +"</button>"));
		}
		else if (buttons[k].label == "delete") {
			$b_td.append($("<button id='" + dom_id + "' class='btn btn-danger' data-for='" + dataFor + "_delete'>" + buttons[k].value +"</button>"));
		}
		else if (buttons[k].label == "insertUp") {
			$b_td.append($("<button id='" + dom_id + "' class='btn btn-info' data-for='" + dataFor + "_insertUp'><span class='glyphicon glyphicon-arrow-up' aria-hidden='true'></span>" + buttons[k].value +"</button>"));
		}
		else if (buttons[k].label == "insertDown") {
			$b_td.append($("<button id='" + dom_id + "' class='btn btn-info' data-for='" + dataFor + "_insertDown'><span class='glyphicon glyphicon-arrow-down' aria-hidden='true'></span>" + buttons[k].value +"</button>"));
		}
	}
	$tr.append($b_td);
	$table.append($tr);
	$form.append($table);
	$("#" + target_id).append($form);
	$("#" + target_id).find("th").length 
	$form.validate({rules:rules, errorClass: "form-error"});
}

var tableForm = {
		source : {
			on:true,
			address:"",
			inputs:{},
			value:[]
		},
		form : {
			name: "",
			inputs: {},
			func: ""
		},

		event : {
			add:{
				on:true,
				source:{
					address:"",
					inputs: {},
					func:""
				},
				form:{
					name: "",
					inputs: {},
					func: ""
				}
			},
			update:{
				on:true,
				full:false,
				source:{
					address:"",
					inputs: {},
					func:""
				}
			},
			del:{
				on:true,
				source:{
					address:"",
					inputs: {},
					func:""
				}
			},
			insertUp:{
				on:true,
				source:{
					address:"",
					inputs: {},
					func:""
				}
			},
			insertDown:{
				on:true,
				source:{
					address:"",
					inputs: {},
					func:""
				}
			}
		}
}

function genAdvanceTable(target_id, dataFor, source, form, event, alert, disabled){
	if (typeof(source["on"])=="undefined" || source["on"]){
		logInfo(source.address, source.inputs, []);
		invoke(getURL(source.address), source.inputs, function(data){
			drawAdvanceTable(target_id, dataFor, form.name, data.data, form.inputs, alert, disabled);
			if (typeof(form.func) == "function"){
				form.func(data);
			}
		},alert);
	}
	else {
		drawAdvanceTable(target_id, dataFor, form.name, source.value, form.inputs, alert, disabled)
		setDatePick();
		if (typeof(form.func) == "function"){
			form.func(source.value);
		}
	}
}

function drawAdvanceTable(target_id, dataFor, form_name, values, inputs, alert_id, disabled){
	var $table = $("<table class='table table-hover'></table>");
	var form_data = getForm(form_name);
	var forms = form_data.forms;
	var buttons = form_data.buttons;
	var titles = form_data.titles;
	var $title_tr = $("<tr id='" + dataFor +"_title'><th></th></tr>");
	for (var i = 1; i < titles.length; i++){
		$title_tr.append("<th>" + titles[i] + "</th>");
	}
	$title_tr.append("<th></th>");
	$table.append($title_tr);
	var $formDiv = $("<div id='form_" + dataFor + "_div'></div>")
	$formDiv.append($table);
	
	for (var i = 0; i < values.length; i++){
		var id = values[i][0];
		var $tr = $("<tr data-for='" + dataFor + "_tr' id='" + id + "'><th></th></tr>");
		var rules = {};
		for (var j = 1; j < forms.length; j++) {
				var dom_id = dataFor + "_" + id + "_" + forms[j].label;
				
				var $td = $("<td></td>");
				if (typeof(values[i][j]) != "undefined" && values[i].length > 0) {
					forms[j].value = values[i][j];
				}
				
				var ed = true;
				if (typeof(forms[j].editable) != "undefined"){
					ed = forms[j].editable;
				}
				
				if (forms[j].type == "input") {
					var $input = genInput(dom_id, dataFor + "_" + id, forms[j].value, disabled, ed, "sm");
					$td.append($input);
				}
				else if (forms[j].type == "select") {
					var options;
					if (forms[j].options.type == "fetch"){
						if (typeof(forms[j].options.inputs) != "undefined"){
							options = fetch(getURL(forms[j].options.url), forms[j].options.inputs);
						}
						else {
						options = fetch(getURL(forms[j].options.url), {});
					}
				}
				else if (forms[j].options.type == "function"){
					options = fetch(getURL(forms[j].options.url), inputs[id + "_" + forms[j].label]);
				}
				else if (forms[j].options.type == "value"){
					options = forms[j].options.values;
				}
				
				$select = genSelect(dom_id, dataFor + "_" + id, options, forms[j].value, disabled, ed, "sm");
				$td.append($select);
				}
				else if (forms[j].type == "auto"){
					if (forms[j].options.type == "fetch"){
						options = fetch(getURL(forms[j].options.url), "");
					}
					else if (forms[j].options.type == "function"){
						options = fetch(getURL(forms[j].options.url), inputs[id + "_" + forms[j].label]);
					}
					else if (forms[j].options.type == "value"){
						options = forms[j].options.values;
					}
					
					$auto = genAuto(dom_id, dataFor + "_" + id, options, forms[j].value, disabled, ed, "sm");
					$td.append($auto);
				}
				
				else if (forms[j].type == "date") {
					var $input = genDateInput(dom_id, dataFor + "_" + id, forms[j].value, disabled, ed, "sm");
					$td.append($input);
				}
				else if (forms[j].type == "label") {
					var $p = $("<p></p>");
					$p.attr("data-for", dataFor + "_" + id);
					$p.attr("id", dom_id);
					$p.append(forms[j].value);
					$td.append($p);
				}
				$tr.append($td);
		}
		var $b_td = $("<td></td>");
		for (var k = 0; k < buttons.length; k++){
			var dom_id = dataFor + "_" + id + "_" + buttons[k].label
			if (buttons[k].label == "submit") {
				$b_td.append($("<button id='" + dom_id + "' class='btn btn-success' data-for='" + dataFor + "_'>" + buttons[k].value +"</button>"));
			}
			else if (buttons[k].label == "update") {
				$b_td.append($("<button id='" + dom_id + "' class='btn btn-primary' data-for='" + dataFor + "_update'>" + buttons[k].value +"</button>"));
			}
			else if (buttons[k].label == "delete") {
				$b_td.append($("<button id='" + dom_id + "' class='btn btn-danger' data-for='" + dataFor + "_delete'>" + buttons[k].value +"</button>"));
			}
		}
		
		$tr.append($b_td);
		$table.append($tr);
	}
	$("#" + target_id).append($formDiv);
}


function genTableForm(target_id, dataFor, source, form, event, alert, disabled){
	var ff = getForm(form.name);
	var messageHeader = ff.messageHeader;
	
	if (typeof(source["on"])=="undefined" || source["on"]){
		source.inputs.num = 1;
		logInfo(source.address, source.inputs, []);
		invoke(getURL(source.address), source.inputs, function(data){
			$("#" + target_id).empty();
			var iid = drawTableForm(target_id, dataFor, form.name, data.data, form.inputs, alert, disabled);
			if (data.pages){
				var $pagination = genPagination(iid, data.pages);
				$pagination.on("page", function(event, num){
					source.inputs.num = num;
					logInfo(source.address, source.inputs, []);
					invoke(getURL(source.address), source.inputs, function(data){
						$("#" + iid).remove();
						iid = drawTableForm(target_id, dataFor, form.name, data.data, form.inputs, alert, disabled);
						$("#" + iid + "_pag").insertAfter($("#" + iid));
						if (typeof(form.func) == "function"){
							form.func(data);
						}
					});
				});
			}
			//drawTableSimpleForm(target_id, dataFor, data, form.name)
			setDatePick();
			if (typeof(event.add) != "undefined"){
				attachAdd();
			}
			if (typeof(event.update) != "undefined"){
				attachUpdate();
			}
			if (typeof(event.del) != "undefined"){
				attachDelete();
			}
			if (typeof(event.insertUp) != "undefined"){
				attachInsertUp();
			}
			if (typeof(event.insertDown) != "undefined"){
				attachInsertDown();
			}
			if (typeof(form.func) == "function"){
				form.func(data);
			}
		},alert);
	}
	else {
		drawTableForm(target_id, dataFor, form.name, source.value, form.inputs, alert, disabled)
		//drawTableSimpleForm(target_id, dataFor, source.value, form.name)
		setDatePick();
		if (typeof(event.add) != "undefined"){
			attachAdd();
		}
		if (typeof(event.update) != "undefined"){
			attachUpdate();
		}
		if (typeof(event.del) != "undefined"){
			attachDelete();
		}
		if (typeof(event.insertUp) != "undefined"){
			attachInsertUp();
		}
		if (typeof(event.insertDown) != "undefined"){
			attachInsertDown();
		}
		if (typeof(form.func) == "function"){
			form.func(source.value);
		}
	}
	
	function attachUpdate(){
		$(document).off("click", "button[data-for='" + dataFor + "_update']");
		$(document).on("click", "button[data-for='" + dataFor +"_update']", function(){
			var real_id = $(this).closest("tr").attr("id");
			var id = $(this).attr("id").split("_")[1];
			var dataFor = $(this).attr("id").split("_")[0];
			if (typeof(event.update["on"]) == "undefined" || event.update["on"]){
				if ($(this).text() == "保存"){
		 			if ($("#" + dataFor + "_" + id).valid()){
		 				if (confirm("是否确认保存?")){
		 					var btn = $(this).button('操作中...')
			     			var params = [];
		 					var param_names = [];
		 					var json = {};
				 			json.header = messageHeader||dataFor + "Update";
				 			if (real_id.indexOf("TEM") != 0){
				 				json.id = real_id.split("_")[0];
				 			}
				 			json.operation = "UPDATE";
		 					if (event.update.full){
		 						$.each($("#form_" + dataFor + "_div").find("form"), function(index, f){
		 							var itemID = $(f).attr("id").split("_")[1];
		 							var item = [];
		 							$.each($("[data-for='" + dataFor + "_" + itemID + "']"), function(index, value){
					     				if ($(value).prop("type") == "checkbox"){
					     					item.push($(value).is(":checked"));
					     					json[$(value).attr("name").split('_')[2]] = $(value).is(":checked");
					     				}
					     				else {
					     					item.push($(value).val());
					     					if($(value).attr("name")){
					     						json[$(value).attr("name").split('_')[2]] = $(value).val();
					     					}
					     				}
					     			});
		 							params.push(item);
		 							param_names = "params";
		 						});
		 					}
		 					else{
		 						$.each($("[data-for='" + dataFor + "_" + id + "']"), function(index, value){
				     				if ($(value).prop("type") == "checkbox"){
				     					params.push($(value).is(":checked"));
				     					json[$(value).attr("name").split('_')[2]] = $(value).is(":checked");
				     				}
				     				else {
				     					params.push($(value).val());
				     					if($(value).attr("name")){
				     						json[$(value).attr("name").split('_')[2]] = $(value).val();
				     					}
				     				}
				     				if($(value).attr("name")){
			     						param_names.push($(value).attr("name").split('_')[2]);
			     					}
				     				
				     			});
		 					}
		 					if (event.update.source.inputs.pid){
				 				json.pid = event.update.source.inputs.pid;
				 			}
			     			event.update.source.inputs["params"] = JSON.stringify(params);
			     			event.update.source.inputs["json"] = JSON.stringify(json);
			     			//如果是新增项
			     			if (real_id.indexOf("TEM")==0){
			     				json.header = messageHeader||dataFor + "Create";
			     				json.operation = "ADD";
			     				event.update.source.inputs["json"] = JSON.stringify(json);
			     				logInfo(event.add.source.address, event.update.source.inputs, param_names);
			     				
			     				invoke(getURL(event.add.source.address), event.update.source.inputs, function(data){
			     					$("[data-for='" + dataFor + "_" + id + "']").prop("disabled", true);
		     		     			$("#" + dataFor + "_" + id + "_update").show();
		     	                	$("#" + real_id).attr("id", data);
		     	                	$(btn).attr("class", "btn btn-primary")
		     	                	$(btn).text("更新");
		     	                	$("#" + alert).empty();
			 	                	$("#" + alert).append(genAlert("success", "编号" + data + " " + adp_translate[dataFor] + "创建成功!"));
			 	                	if (typeof(event.update.source.func) == "function"){
			 	                		event.update.source.func(id, dataFor);
			 	                	}
			     				}, alert);
			     			}
			     			//如果是已有项目
			     			else{
			     				event.update.source.inputs["id"] = real_id;
			     				logInfo(event.update.source.address, event.update.source.inputs, param_names);
			     				invoke(getURL(event.update.source.address), event.update.source.inputs, function(data){
			     					$("[data-for='" + dataFor + "_" + id + "']").prop("disabled", true);
		     		     			$("#" + dataFor + "_" + id + "_update").show();
		     		     			$(btn).attr("class", "btn btn-primary")
		     		     			$(btn).text("更新");
		     		     			$("#" + alert).empty();
			 	                	$("#" + alert).append(genAlert("success", "编号" + real_id + " " + adp_translate[dataFor] + "更新成功!"));
			 	                	if (typeof(event.update.source.func) == "function"){
				 	   					event.update.source.func(id, dataFor);
				 	                }
			     				}, alert);
			     			} 
			 			}
		 			}
				}
				else {
					var checkURL = getURL(event.update.source.address).replace(prefix, "");
					var checkResult = fetch(getURL(verifyPermFunc), {checkURL:checkURL});
					var flag = checkResult["status"];
					var urlName = checkResult["urlName"];
					if (flag){
						$.each($("[data-for='" + dataFor + "_" + id + "']"), function(index, value){
							if ($(value).prop("editable") == false){
								$(value).prop("disabled", true);
							}
							else {
								$(value).prop("disabled", false);
							}
						});
			   			//$("[data-for='" + dataFor + "_" + id + "']").prop("disabled", false);
			   			$(this).attr("class", "btn btn-success")
						$(this).text("保存");
			   			if (typeof(event.update.source.func) == "function"){
	 	   					event.update.source.func(id, dataFor);
	 	                }
					}
					else{
						$("#" + alert).empty();
						$("#" + alert).append(genAlert("fail", "没有权限调用 " + urlName));
					}
				}	
				}
				else{
					if (typeof(event.update.source.func) == "function"){
						event.update.source.func(id, dataFor);
					}
				}
			return false;
		});
	}
	
	function attachDelete(){
		$(document).off("click", "button[data-for='" + dataFor + "_delete']");
		$(document).on("click", "button[data-for='" + dataFor + "_delete']", function(){
			var dataFor = $(this).attr("id").split("_")[0];
			var $tr = $(this).closest("tr");
			var $form = $(this).closest("form");
			var itemID = $tr.attr("id");
			if (typeof(event.del["on"]) == "undefined" || event.del["on"]){
				if (confirm("是否确认删除?")){
					var $title = $("#" + dataFor + "_title");
					if (itemID.indexOf("TEM")==0){
						$tr.closest("form").remove();
						if ($("#" + target_id).find("table").length > 1){
							if(itemID == $("#" + target_id).find("table").eq(0).find("button").eq(0).attr("id").split("_")[1]){
								var $title_tr = $("<tr id='title'><th></th></tr>");
								var titles = getForm(form.name).titles;
								for (var i = 1; i < titles.length; i++){
									$title_tr.append("<th>" + titles[i] + "</th>");
								}
								$title_tr.append("<th></th>");
								$title_tr.insertBefore($("#" + target_id).find("table").eq(1).find("tr").eq(0));
							}
						}
						if (typeof(event.del.source.func) == "function"){
							event.del.source.func(itemID, dataFor);
						}
					}
					else{
						event.del.source.inputs["id"] = itemID;
						logInfo(event.del.source.address, event.del.source.inputs, []);
						invoke(getURL(event.del.source.address), event.del.source.inputs, function(data){
							$("#" + alert).empty();
		                	$("#" + alert).append(genAlert("success", "编号" + itemID + " " + adp_translate[dataFor] + " 已被成功删除！"));
		                	$form.remove();
		                	if ($("#" + target_id).find("table").length > 1){
								if(itemID == $("#" + target_id).find("table").eq(0).find("button").eq(0).attr("id").split("_")[1]){
									var $title_tr = $("<tr id='title'><th></th></tr>");
									var titles = getForm(form.name).titles;
									for (var i = 1; i < titles.length; i++){
										$title_tr.append("<th>" + titles[i] + "</th>");
									}
									$title_tr.append("<th></th>");
									$title_tr.insertBefore($("#" + target_id).find("table").eq(1).find("tr").eq(0));
								}
							}
		                	if (typeof(event.del.source.func) == "function"){
		    					event.del.source.func(itemID, dataFor);
		    				}
						},alert);
					}
					
				}
					
				}
				else{
					if (typeof(event.del.source.func) == "function"){
						event.del.source.func(itemID, dataFor);
					}
				}
			
			return false;
		});
	}
	
	function attachAdd(){
		$(document).off("click", "button[data-for='" + dataFor + "_add']");
		$(document).on("click", "button[data-for='" + dataFor + "_add']", function(){
			var checkURL = getURL(event.add.source.address).replace(prefix, "");
			var checkResult = fetch(getURL(verifyPermFunc), {checkURL:checkURL});
			var flag = checkResult["status"];
			var urlName = checkResult["urlName"];
			
			if (flag){
				var dataFor = $(this).attr("id").split("_")[0];
				var id = "TEM" + new Date().valueOf();
				if (typeof(event.add["on"]) == "undefined" || event.add["on"]){
					addTableFormTr("form_" + dataFor + "_div" , id, dataFor, event.add.form.name, event.add.form.inputs, false);
					$.each($("[data-for='" + dataFor + "_" + id + "']"), function(index, value){
						if ($(value).prop("editable") == false){
							$(value).prop("disabled", true);
						}
						else {
							$(value).prop("disabled", false);
						}
					});
					setDatePick();
					if (typeof(event.add.source.func) == "function"){
						event.add.source.func(id, dataFor);
					}
				}
				else{
					if (typeof(event.add.source.func) == "function"){
						event.add.source.func(id, dataFor);
					}
				}
			}
			else{
				$("#" + alert).empty();
				$("#" + alert).append(genAlert("fail", "没有权限调用 " + urlName));
			}
			return false;
		});
	}
	
	function attachInsertUp(){
		$(document).off("click", "button[data-for='" + dataFor + "_insertUp']");
		$(document).on("click", "button[data-for='" + dataFor + "_insertUp']", function(){
			var checkURL = getURL(event.add.source.address).replace(prefix, "");
			var checkResult = fetch(getURL(verifyPermFunc), {checkURL:checkURL});
			var flag = checkResult["status"];
			var urlName = checkResult["urlName"];
			
			if (flag){
				var dataFor = $(this).attr("id").split("_")[0];
				var selfID = dataFor + "_" + $(this).attr("id").split("_")[1]
				var id = "TEM" + new Date().valueOf();
				if (typeof(event.insertUp["on"]) == "undefined" || event.insertUp["on"]){
					addTableFormTr("form_" + dataFor + "_div" , id, dataFor, event.add.form.name, event.add.form.inputs, false);
					$.each($("[data-for='" + dataFor + "_" + id + "']"), function(index, value){
						if ($(value).prop("editable") == false){
							$(value).prop("disabled", true);
						}
						else {
							$(value).prop("disabled", false);
						}
					});
					setDatePick();
					if ($("#" + selfID).find("tr").length > 1){
						$("#" + dataFor + "_title").insertBefore("#" + id);
					}
					$("#" + dataFor + "_" + id).insertBefore($("#" + selfID));
					if (typeof(event.insertUp.source.func) == "function"){
						event.insertUp.source.func(id, dataFor);
					}
				}
				else{
					if (typeof(event.insertUp.source.func) == "function"){
						event.insertUp.source.func(id, dataFor);
					}
				}
			}
			else{
				$("#" + alert).empty();
				$("#" + alert).append(genAlert("fail", "没有权限调用 " + urlName));
			}
			return false;
		});
	}
	
	function attachInsertDown(){
		$(document).off("click", "button[data-for='" + dataFor + "_insertDown']");
		$(document).on("click", "button[data-for='" + dataFor + "_insertDown']", function(){
			var checkURL = getURL(event.add.source.address).replace(prefix, "");
			var checkResult = fetch(getURL(verifyPermFunc), {checkURL:checkURL});
			var flag = checkResult["status"];
			var urlName = checkResult["urlName"];
			
			if (flag){
				var dataFor = $(this).attr("id").split("_")[0];
				var selfID = dataFor + "_" + $(this).attr("id").split("_")[1]
				var id = "TEM" + new Date().valueOf();
				if (typeof(event.insertUp["on"]) == "undefined" || event.insertUp["on"]){
					addTableFormTr("form_" + dataFor + "_div" , id, dataFor, event.add.form.name, event.add.form.inputs, false);
					$.each($("[data-for='" + dataFor + "_" + id + "']"), function(index, value){
						if ($(value).prop("editable") == false){
							$(value).prop("disabled", true);
						}
						else {
							$(value).prop("disabled", false);
						}
					});
					setDatePick();
					$("#" + dataFor + "_" + id).insertAfter($("#" + selfID));
					if (typeof(event.insertDown.source.func) == "function"){
						event.insertDown.source.func(id, dataFor);
					}
				}
				else{
					if (typeof(event.insertDown.source.func) == "function"){
						event.insertDown.source.func(id, dataFor);
					}
				}
			}
			else{
				$("#" + alert).empty();
				$("#" + alert).append(genAlert("fail", "没有权限调用 " + urlName));
			}
			return false;
		});
	}
}

function form2table(tr_id, item_id, dataFor, form_name){
	var form_data = getForm(form_name);
	var buttons = form_data.buttons;
	var $tr = $("#" + tr_id);
	var tds = $tr.find("td");
	var id = item_id;
	
	$.each($("[data-for='" + dataFor + "_" + item_id + "']"), function(index, f){
		var val = $(f).val();
		if ($(f).prop("type") == "select-one"){
			val = $(f).find("option[value='" + val + "']").text();
		}
		var $td = $(f).parent();
		$td.empty();
		$td.text(val);
	});
	var $b_td = tds.eq(tds.length - 1);
	$b_td.empty();
	
	for (var k = 0; k < buttons.length; k++){
		$b_td.append("&nbsp;&nbsp;")
		var dom_id = dataFor + "_" + id + "_" + buttons[k].label
		if (buttons[k].label == "submit") {
			$b_td.append($("<button id='" + dom_id + "' class='btn btn-success' data-for='" + dataFor + "_'>" + buttons[k].value +"</button>"));
		}
		else if (buttons[k].label == "update") {
			if (buttons[k].value == "保存"){
				$b_td.append($("<button id='" + dom_id + "' class='btn btn-success' data-for='" + dataFor + "_update'>" + buttons[k].value +"</button>"));
			}
			else{
				$b_td.append($("<button id='" + dom_id + "' class='btn btn-primary' data-for='" + dataFor + "_update'>" + buttons[k].value +"</button>"));
			}
		}
		else if (buttons[k].label == "delete") {
			$b_td.append($("<button id='" + dom_id + "' class='btn btn-danger' data-for='" + dataFor + "_delete'>" + buttons[k].value +"</button>"));
		}
	}
}

function table2form(tr_id, item_id, dataFor, form_name, disabled, cons){
	var form_data = getForm(form_name);
	form_data.forms.splice(0,1);
	var buttons = form_data.buttons;
	
	var $tr = $("#" + tr_id);
	var tds = $tr.find("td");
	var id = item_id;
	var update = (tds.length > 0);
	if (! update){
		$tr = $("<tr><th></th></tr>");
		$tr.attr("id", tr_id);
	}
	
	$.each(form_data.forms, function(index, form){
		var dom_id = dataFor + "_" + id + "_" + form.label;
		var val;
		var $td = $("<td></td>");
		if (update){
			$td = $(tds[index]);
			val = $td.text();
			$td.empty();
		}
		
		var ed = true;
		if (typeof(form.editable) != "undefined"){
			ed = form.editable;
		}
		
		if (form.type == "input") {
			var $input = genInput(dom_id, dataFor + "_" + id, val, disabled, ed, "sm");
			$td.append($input);
		}
		else if (form.type == "select") {
			var options;
			if (form.options.type == "fetch"){
				if (typeof(form.options.inputs) != "undefined"){
					options = fetch(getURL(form.options.url), form.options.inputs);
				}
				else {
				options = fetch(getURL(form.options.url), {});
				}
			}
			else if (form.options.type == "value"){
				options = form.options.values;
			}
			else if (form.options.type = "cons"){
				options = cons[form.label];
			}
			$select = genSelect(dom_id, dataFor + "_" + id, options, val2key(options, val), disabled, ed, "sm");
			$td.append($select);
		}
		else if (form.type == "auto"){
			if (form.options.type == "fetch"){
				options = fetch(getURL(form.options.url), "");
			}
			else if (form.options.type == "value"){
				options = form.options.values;
			}
			
			$auto = genAuto(dom_id, dataFor + "_" + id, options, val, disabled, ed, "sm");
			$td.append($auto);
		}
		
		else if (form.type == "date") {
			var $input = genDateInput(dom_id, dataFor + "_" + id, val, disabled, ed, "sm");
			$td.append($input);
		}
		else if (form.type == "label") {
			$td.append(form.value);
		}
		else if (form.type == "checkbox"){
			var $checkbox = genCheckbox(dom_id, dataFor + "_" + id, val, disabled, ed)
			$td.append($checkbox);
		}
		if (! update){
			$tr.append($td);
		}
	});
	
	var $b_td;
	if (! update){
		var $b_td = $("<td></td>");
		for (var k = 0; k < buttons.length; k++){
			$b_td.append("&nbsp;&nbsp;")
			var dom_id = dataFor + "_" + id + "_" + buttons[k].label
			if (buttons[k].label == "submit") {
				$b_td.append($("<button id='" + dom_id + "' class='btn btn-success' data-for='" + dataFor + "_'>" + buttons[k].value +"</button>"));
			}
			else if (buttons[k].label == "update") {
				if (buttons[k].value == "保存"){
					$b_td.append($("<button id='" + dom_id + "' class='btn btn-success' data-for='" + dataFor + "_update'>" + buttons[k].value +"</button>"));
				}
				else{
					$b_td.append($("<button id='" + dom_id + "' class='btn btn-primary' data-for='" + dataFor + "_update'>" + buttons[k].value +"</button>"));
				}
			}
			else if (buttons[k].label == "delete") {
				$b_td.append($("<button id='" + dom_id + "' class='btn btn-danger' data-for='" + dataFor + "_delete'>" + buttons[k].value +"</button>"));
			}
		}
		$tr.append($b_td);
	}
	return $tr;
}

function genTableSimpleForm(target_id, dataFor, source, form, event, alert, disabled, cons){
	if (typeof(source["on"])=="undefined" || source["on"]){
		source.inputs.num = 1;
		logInfo(source.address, source.inputs, []);
		invoke(getURL(source.address), source.inputs, function(data){
			$("#" + target_id).empty();
			var iid = drawTableSimpleForm(target_id, dataFor, data, form.name, cons);
			setDatePick();
			if (data.pages){
				var $pagination = genPagination(iid, data.pages);
				$pagination.on("page", function(event, num){
					source.inputs.num = num;
					logInfo(source.address, source.inputs, []);
					invoke(getURL(source.address), source.inputs, function(data){
						$("#" + iid).remove();
						iid = drawTableSimpleForm(target_id, dataFor, data, form.name, cons);
						$("#" + iid + "_pag").insertAfter($("#" + iid));
						if (typeof(form.func) == "function"){
							form.func(data);
						}
					});
				});
			}
			if (typeof(form.func) == "function"){
				form.func(data);
			}
			if (typeof(event.add) != "undefined"){
				attachAdd();
			}
			if (typeof(event.update) != "undefined"){
				attachUpdate();
			}
			if (typeof(event.del) != "undefined"){
				attachDelete();
				
			}
		},alert);
	}
	else {
		drawTableSimpleForm(target_id, dataFor, source.value, form.name, cons);
		setDatePick();
		if (typeof(form.func) == "function"){
			form.func(source.value);
		}
		if (typeof(event.add) != "undefined"){
			attachAdd();
		}
		if (typeof(event.update) != "undefined"){
			attachUpdate();
		}
		if (typeof(event.del) != "undefined"){
			attachDelete();
		}
	}
	
	function attachUpdate(){
		$(document).off("click", "button[data-for='" + dataFor + "_update']");
		$(document).on("click", "button[data-for='" + dataFor +"_update']", function(){
			var real_id = $(this).closest("tr").attr("id");
			var id = $(this).attr("id").split("_")[1];
			var dataFor = $(this).attr("id").split("_")[0];
			if (typeof(event.update["on"]) == "undefined" || event.update["on"]){
				if ($(this).text() == "保存"){
	 				if (confirm("是否确认保存?")){
	 					var btn = $(this).button('操作中...')
		     			var params = [];
	 					var param_names = [];
	 					var json = {};
			 			json.header = dataFor + "Update";
			 			if (real_id.indexOf("TEM") != 0){
			 				json.id = real_id.split("_")[0];
			 			}
			 			json.operation = "UPDATE";
			 			
		     			$.each($("[data-for='" + dataFor + "_" + id + "']"), function(index, value){
		     				if ($(value).prop("type") == "checkbox"){
		     					params.push($(value).is(":checked"));
		     					json[$(value).attr("name").split('_')[2]] = $(value).is(":checked");
		     				}
		     				else {
		     					params.push($(value).val());
		     					if($(value).attr("name")){
		     						json[$(value).attr("name").split('_')[2]] = $(value).val();
		     					}
		     				}
		     				if($(value).attr("name")){
	     						param_names.push($(value).attr("name").split('_')[2]);
	     					}
		     				
		     			});
		     			event.update.source.inputs["params"] = JSON.stringify(params);
		     			event.update.source.inputs["json"] = JSON.stringify(json);
		     			//如果是新增项
		     			if (real_id.indexOf("TEM")==0){
		     				json.header = dataFor + "Create";
		     				json.operation = "ADD";
		     				event.update.source.inputs["json"] = JSON.stringify(json);
		     				logInfo(event.add.source.address, event.update.source.inputs, param_names);
		     				invoke(getURL(event.add.source.address), event.update.source.inputs, function(data){
		     					//Change form to table
		     					form2table(real_id, id, dataFor, form.name);
	     	                	$("#" + real_id).attr("id", data);
	     	                	$(btn).attr("class", "btn btn-primary")
	     	                	$(btn).text("更新");
	     	                	$("#" + alert).empty();
		 	                	$("#" + alert).append(genAlert("success", "编号" + data + " " + adp_translate[dataFor] + "创建成功!"));
		     				}, alert);
		     			}
		     			//如果是已有项目
		     			else{
		     				event.update.source.inputs["id"] = real_id.split("_")[0];
		     				logInfo(event.update.source.address, event.update.source.inputs, param_names);
		     				invoke(getURL(event.update.source.address), event.update.source.inputs, function(data){
		     					//change form to table
		     					form2table(real_id, id, dataFor, form.name);
	     		     			$(btn).attr("class", "btn btn-primary")
	     		     			$(btn).text("更新");
	     		     			$("#" + alert).empty();
		 	                	$("#" + alert).append(genAlert("success", "编号" + real_id.split("_")[0] + " " + adp_translate[dataFor] + "更新成功!"));
		     				}, alert);
		     			} 
		 			}
				}
				else {
					var checkURL = getURL(event.update.source.address).replace(prefix, "");
					var checkResult = fetch(getURL(verifyPermFunc), {checkURL:checkURL});
					var flag = checkResult["status"];
					var urlName = checkResult["urlName"];
					if (flag){
						table2form(real_id, id, dataFor, form.name, false, cons);
						$.each($("[data-for='" + dataFor + "_" + id + "']"), function(index, value){
	 						if ($(value).prop("editable") == false){
	 							$(value).prop("disabled", true);
	 						}
	 						else {
	 							$(value).prop("disabled", false);
	 						}
	 					});
						setDatePick();
			   			$(this).attr("class", "btn btn-success")
						$(this).text("保存");
			   			if (typeof(event.update.source.func) == "function"){
							event.update.source.func(id, dataFor);
						}
					}
					else{
						$("#" + alert).empty();
						$("#" + alert).append(genAlert("fail", "没有权限调用 " + urlName));
					}
				}
			}
			else{
				if (typeof(event.update.source.func) == "function"){
					event.update.source.func(id, dataFor);
				}
			}
			
			return false;
		});
	}
	
	function attachDelete(){
		$(document).off("click", "button[data-for='" + dataFor + "_delete']");
		$(document).on("click", "button[data-for='" + dataFor + "_delete']", function(){
			var dataFor = $(this).attr("id").split("_")[0];
			var $tr = $(this).closest("tr");
			var itemID = $tr.attr("id").split("_")[0];
			if (typeof(event.del["on"]) == "undefined" || event.del["on"]){
				if (confirm("是否确认删除?")){
					if (itemID.indexOf("TEM")==0){
						$tr.remove();
						if (typeof(event.del.source.func) == "function"){
							event.del.source.func(itemID, dataFor);
						}
					}
					else{
						event.del.source.inputs["id"] = itemID;
						logInfo(event.del.source.address, event.del.source.inputs, []);
						invoke(getURL(event.del.source.address), event.del.source.inputs, function(data){
							$("#" + alert).empty();
		                	$("#" + alert).append(genAlert("success", "编号" + itemID + " " + adp_translate[dataFor] + " 已被成功删除！"));
		                	$tr.remove();
		                	if (typeof(event.del.source.func) == "function"){
		    					event.del.source.func(itemID, dataFor);
		    				}
						},alert);
					}
					
				}
				
			}
			else{
				if (typeof(event.del.source.func) == "function"){
					event.del.source.func(itemID, dataFor);
				}
			}
			return false;
		});
	}
	
	function attachAdd(){
		$(document).off("click", "button[data-for='" + dataFor + "_postadd']");
		$(document).on("click", "button[data-for='" + dataFor + "_postadd']", function(){
			$("button[data-for='" + dataFor + "_preadd']").click();
			$("html, body").scrollTop($("#alertAll").offset().top);
			return false;
		});
		
		$(document).off("click", "button[data-for='" + dataFor + "_preadd']");
		$(document).on("click", "button[data-for='" + dataFor + "_preadd']", function(){
			var checkURL = getURL(event.add.source.address).replace(prefix, "");
			var checkResult = fetch(getURL(verifyPermFunc), {checkURL:checkURL});
			var flag = checkResult["status"];
			var urlName = checkResult["urlName"];
			if (flag){
				var dataFor = $(this).attr("id").split("_")[0];
				var id = "TEM" + new Date().valueOf();
				if (typeof(event.add["on"]) == "undefined" || event.add["on"]){
					var $tr = table2form(id + "_" + dataFor, id, dataFor, event.add.form.name, false, cons);
					var trs = $("#" + dataFor + "_table").find("tr");
					if (trs.length > 0){
						$tr.insertAfter($("#" + dataFor + "_table").find("tr").eq(0));
					}
					else{
						$("#" + dataFor + "_table").append($tr);
					}
					$.each($("[data-for='" + dataFor + "_" + id + "']"), function(index, value){
						if ($(value).prop("editable") == false){
							$(value).prop("disabled", true);
						}
						else {
							$(value).prop("disabled", false);
						}
					});
					setDatePick();
					if (typeof(event.add.source.func) == "function"){
						event.add.source.func(id, dataFor);
					}
				}
				else{
					if (typeof(event.add.source.func) == "function"){
						event.add.source.func(id, dataFor);
					}
				}
			}
			else {
				$("#" + alert).empty();
				$("#" + alert).append(genAlert("fail", "没有权限调用 " + urlName));
			}
			return false;
		});
	}
}

function drawTableSimpleForm(target_id, dataFor, val, form_name, cons){
	var $table = $("<table class='table table-hover'></table>");
	$table.attr("id", dataFor + "_table");
	var data;
	if (val.length <= 0){
		data = [];
	}
	else{
		data = val.data;
	}
	var form_data = getForm(form_name);
	var buttons = form_data.buttons;
	var titles = form_data.titles;
	var forms = form_data.forms;
	
	$title_tr = $("<tr><th></th></tr>");
	for (var i = 1; i< titles.length; i++){
		$title_tr.append("<th>" + titles[i] + "</th>");
	}
	$title_tr.append("<th></th>")
	$table.append($title_tr);
	
	for (var i=0; i < data.length; i++) {
		var id = data[i][0];
		var $tr = $("<tr><th></th></tr>");
		$tr.attr("id", data[i][0] + "_" + dataFor);
		$tr.attr("data-for", dataFor);
		for (var j = 1; j < titles.length; j++){
			var $td = $("<td></td>");
			if (typeof(data[i][j]) == "object"){
				var $ul = $("<ul></ul>")
				$.each(data[i][j], function(i,v){
					$ul.append("<li>" + v + "</li>");
				});
				$td.append($ul);
				
			}
			else{
				if (forms[j].type == "select") {
					var options;
					if (forms[j].options.type == "fetch"){
						if (typeof(forms[j].options.inputs) != "undefined"){
							options = fetch(getURL(forms[j].options.url), forms[j].options.inputs);
						}
						else {
						options = fetch(getURL(forms[j].options.url), {});
					}
					}
					else if (forms[j].options.type == "function"){
						options = fetch(getURL(forms[j].options.url), inputs[id + "_" + forms[j].label]);
					}
					else if (forms[j].options.type == "value"){
						options = forms[j].options.values;
					}
					else if (forms[j].options.type == "cons"){
						options = cons[forms[j].label];
					}
					var key = data[i][j];
					$td.append(options[key]);
				}
				else{
					$td.append(data[i][j]);
				}
			}
			$tr.append($td);
		}
		
		
		var $b_td = $("<td></td>");
		for (var k = 0; k < buttons.length; k++){
			$b_td.append("&nbsp;&nbsp;")
			var dom_id = dataFor + "_" + id + "_" + buttons[k].label
			if (buttons[k].label == "submit") {
				$b_td.append($("<button id='" + dom_id + "' class='btn btn-success' data-for='" + dataFor + "_'>" + buttons[k].value +"</button>"));
			}
			else if (buttons[k].label == "update") {
				if (buttons[k].value == "保存"){
					$b_td.append($("<button id='" + dom_id + "' class='btn btn-success' data-for='" + dataFor + "_update'>" + buttons[k].value +"</button>"));
				}
				else{
					$b_td.append($("<button id='" + dom_id + "' class='btn btn-primary' data-for='" + dataFor + "_update'>" + buttons[k].value +"</button>"));
				}
			}
			else if (buttons[k].label == "delete") {
				$b_td.append($("<button id='" + dom_id + "' class='btn btn-danger' data-for='" + dataFor + "_delete'>" + buttons[k].value +"</button>"));
			}
		}
		$tr.append($b_td);
		$table.append($tr); 
	}
	
	$("#" + target_id).append($table);
	
	//var $addDiv = $("<div id='postadd_" + dataFor + "_div' class='col-md-offset-10 col-md-2'></div>")
	//var dom_id = dataFor + "_postadd"
	//$addDiv.append("<button id='" + dom_id + "' class='btn btn-success' data-for='" + dom_id + "'>增加</button>");
	//$addDiv.insertAfter($("#" + dataFor + "_table"));
	$("#preadd_" + dataFor + "_div" ).remove();
	var $addDiv = $("<div id='preadd_" + dataFor + "_div' class='col-md-offset-10 col-md-2'></div>")
	var dom_id = dataFor + "_preadd";
	$addDiv.append("<button id='" + dom_id + "' class='btn btn-success' data-for='" + dom_id + "'>增加</button>");
	$addDiv.insertBefore($("#" + dataFor + "_table"));
	return $table.attr("id");
}

function drawTableForm(target_id, dataFor, form_name, values, inputs, alert_id, disabled){
	//var $table = $("<table class='table table-hover'></table>");
	var form_data = getForm(form_name);
	var forms = form_data.forms;
	var buttons = form_data.buttons;
	var titles = form_data.titles;
	var $title_tr = $("<tr id='" + dataFor +"_title'><th></th></tr>");
	for (var i = 1; i < titles.length; i++){
		$title_tr.append("<th>" + titles[i] + "</th>");
	}
	$title_tr.append("<th></th>");
	
	var $formDiv = $("<div id='form_" + dataFor + "_div'></div>")
	$formDiv.append($table);
	if(! values){
		values = [];
	}
	for (var i = 0; i < values.length; i++){
		var $table = $("<table class='table table-hover'></table>");
		if (i == 0){
			$table.append($title_tr);
		}
		var id = values[i][0];
		var $tr = $("<tr data-for='" + dataFor + "_tr' id='" + id + "'><th></th></tr>");
		var $form = $("<form class='form-horizontal' id='" + dataFor + "_" + id + "'></form>");
		var rules = {};
		for (var j = 1; j < forms.length; j++) {
				var dom_id = dataFor + "_" + id + "_" + forms[j].label;
				
				var r = forms[j].rules;
				if (typeof(r) != "undefined" && r !="" && Object.keys(r).length > 0){
					rules[dom_id] = r;
				}
				
				var $td = $("<td></td>");
				if (typeof(values[i][j]) != "undefined" && values[i].length > 0) {
					forms[j].value = values[i][j];
				}
				
				var ed = true;
				if (typeof(forms[j].editable) != "undefined"){
					ed = forms[j].editable;
				}
				
				if (forms[j].type == "input") {
					var $input = genInput(dom_id, dataFor + "_" + id, forms[j].value, disabled, ed, "sm");
					$td.append($input);
				}
				else if (forms[j].type == "select") {
					var options;
					if (forms[j].options.type == "fetch"){
						if (typeof(forms[j].options.inputs) != "undefined"){
							options = fetch(getURL(forms[j].options.url), forms[j].options.inputs);
						}
						else {
						options = fetch(getURL(forms[j].options.url), {});
					}
				}
				else if (forms[j].options.type == "function"){
					options = fetch(getURL(forms[j].options.url), inputs[id + "_" + forms[j].label]);
				}
				else if (forms[j].options.type == "value"){
					options = forms[j].options.values;
				}
				
				$select = genSelect(dom_id, dataFor + "_" + id, options, forms[j].value, disabled, ed, "sm");
				$td.append($select);
			}
			else if (forms[j].type == "auto"){
				if (forms[j].options.type == "fetch"){
					options = fetch(getURL(forms[j].options.url), "");
				}
				else if (forms[j].options.type == "function"){
					options = fetch(getURL(forms[j].options.url), inputs[id + "_" + forms[j].label]);
				}
				else if (forms[j].options.type == "value"){
					options = forms[j].options.values;
				}
				
				$auto = genAuto(dom_id, dataFor + "_" + id, options, forms[j].value, disabled, ed, "sm");
				$td.append($auto);
			}
			
			else if (forms[j].type == "date") {
				var $input = genDateInput(dom_id, dataFor + "_" + id, forms[j].value, disabled, ed, "sm");
				$td.append($input);
			}
			else if (forms[j].type == "label") {
				$td.append(forms[j].value);
			}
			else if (forms[j].type == "checkbox"){
				var $checkbox = genCheckbox2(dom_id, dataFor + "_" + id, forms[j].value, disabled, ed)
				$td.append($checkbox);
			}
			$tr.append($td);
		}
		var $b_td = $("<td></td>");
		for (var k = 0; k < buttons.length; k++){
			var dom_id = dataFor + "_" + id + "_" + buttons[k].label
			if (buttons[k].label == "submit") {
				$b_td.append($("<button id='" + dom_id + "' class='btn btn-success' data-for='" + dataFor + "_'>" + buttons[k].value +"</button>"));
			}
			else if (buttons[k].label == "update") {
				$b_td.append($("<button id='" + dom_id + "' class='btn btn-primary' data-for='" + dataFor + "_update'>" + buttons[k].value +"</button>"));
			}
			else if (buttons[k].label == "delete") {
				$b_td.append($("<button id='" + dom_id + "' class='btn btn-danger' data-for='" + dataFor + "_delete'>" + buttons[k].value +"</button>"));
			}
			else if (buttons[k].label == "insertUp") {
				$b_td.append($("<button id='" + dom_id + "' class='btn btn-info' data-for='" + dataFor + "_insertUp'><span class='glyphicon glyphicon-arrow-up' aria-hidden='true'></span>" + buttons[k].value +"</button>"));
			}
			else if (buttons[k].label == "insertDown") {
				$b_td.append($("<button id='" + dom_id + "' class='btn btn-info' data-for='" + dataFor + "_insertDown'><span class='glyphicon glyphicon-arrow-down' aria-hidden='true'></span>" + buttons[k].value +"</button>"));
			}
		}
		
		$tr.append($b_td);
		$table.append($tr);
		$form.append($table);
		$formDiv.append($form);
		$form.validate({rules:rules, errorClass: "form-error"});
	}
	$("#" + target_id).append($formDiv);
	$("#add_" + dataFor + "_div").remove();
	var $addDiv = $("<div id='add_" + dataFor + "_div' class='col-md-offset-10 col-md-2'></div>")
	var dom_id = dataFor + "_add";
	$addDiv.append("<button id='" + dom_id + "' class='btn btn-success' data-for='" + dom_id + "'>增加</button>");
	$("#" + target_id).append($addDiv);
	return $formDiv.attr("id");
}

var checkTable = {
		source : {
			on:true,
			address:"",
			inputs:{},
			value:[]
		},
		event : {
			create:{
				on:true,
				source:{
					address:"",
					inputs: {},
					func:""
				}
			},
			update:{
				on:true,
				source:{
					address:"",
					inputs: {},
					func:""
				}
			},
			execute:{
				on:true,
				source:{
					address:"",
					inputs: {},
					func:""
				}
			},
			reject:{
				on:true,
				source:{
					address:"",
					inputs: {},
					func:""
				}
			},
			detail:{
				on:true,
				source:{
					address:"",
					inputs: {},
					func:""
				},
				form:{
					name:"",
					inputs:{},
					func:"",
					title_id:"",
					target_id:"",
					modal_id:"",
					alert_id:""
				}
			},
			del:{
				on:true,
				source:{
					address:"",
					inputs: {},
					func:""
				},
				form:{
					name:"",
					inputs:{},
					func:""
				}
			},
			add:{
				on:true,
				source:{
					address:"",
					inputs: {},
					func:""
				},
				form:{
					name:"",
					inputs:{},
					func:"",
					title_id:"",
					target_id:"",
					modal_id:"",
					alert_id:""
				}
			}
		}
}

function genTableWithCheck(target_id, dataFor, source, event, buttons, alert, new_window){
	//处理数据源和表格内容，并将表格画到界面上；
	if (source["on"]){
		source.inputs.num = 1;
		logInfo(source.address, source.inputs, []);
		invoke(getURL(source.address), source.inputs, function(data){
			$("#" + target_id).empty();
			var iid = drawTableWithCheck(target_id, dataFor, data, buttons, new_window);
			setDatePick();
			if (data.pages){
				var $pagination = genPagination(iid, data.pages);
				$pagination.on("page", function(event, num){
					source.inputs.num = num;
					logInfo(source.address, source.inputs, []);
					invoke(getURL(source.address), source.inputs, function(data){
						$("#" + iid).remove();
						iid = drawTableWithCheck(target_id, dataFor, data, buttons, new_window);
						$("#" + iid + "_pag").insertAfter($("#" + iid));
						if (typeof(source.func) == "function"){
							source.func(data);
						}
					});
				});
			}
			if (typeof(event.detail) != "undefined"){
				attachDetail();
			}
			if (typeof(event.del) != "undefined"){
				attachDelete();
			}
			if (typeof(event.add) != "undefined"){
				attachAdd();
			}
			if (typeof(event.execute) != "undefined"){
				attachExecute();
			}
			if (typeof(event.reject) != "undefined"){
				attachReject();
			}
			if (typeof(source.func) == "function"){
				source.func(data);
			}
		}, alert);
	}
	else {
		drawTableWithCheck(target_id, dataFor, source.value, buttons, new_window);
		setDatePick();
		if (typeof(event.detail) != "undefined"){
			attachDetail();
		}
		if (typeof(event.del) != "undefined"){
			attachDelete();
		}
		if (typeof(event.add) != "undefined"){
			attachAdd();
		}
		if (typeof(event.execute) != "undefined"){
			attachExecute();
		}
		if (typeof(event.reject) != "undefined"){
			attachReject();
		}
		if (typeof(source.func) == "function"){
			source.func(source.value);
		}
	}
	
	function attachDetail(){
		var modal = dataFor + "_modal";
		if (event.detail.form.modal_id != ""){
			modal = event.detail.form.modal_id;
		}
		var target = dataFor + "_detail";
		var alert = dataFor + "_alert";
		var title = dataFor + "_title";
		if (event.detail.form.target_id != ""){
			target = event.detail.form.target_id;
		}
		if (event.detail.form.alert_id != ""){
			alert = event.detail.form.alert_id;
		}
		if (event.detail.form.title_id != ""){
			title = event.detail.form.title_id;
		}
		$(document).off("dblclick").on("dblclick", "tr[data-for='" + dataFor + "'] td", function(){
			if (! new_window){
				var checkURL = getURL(event.detail.source.address).replace(prefix, "");
				var checkResult = fetch(getURL(verifyPermFunc), {checkURL:checkURL});
				var flag = checkResult["status"];
				var urlName = checkResult["urlName"];
				if (flag){
					var itemID = $(this).parent().attr("id");
					event.detail.source.inputs["id"] = itemID;
					if (event.detail["on"]){
						
						$("#" + title).text(adp_translate[dataFor] + "详情-编号" + itemID);
				        
				        var f = $.extend(true, {}, editForm)
				        f.source = event.detail.source;
						f.source.inputs["id"] = itemID;
						f.form = event.detail.form;
						f.event.update = event.update;
						f.event.update.source.inputs["id"] = itemID;
						f.event.create.on = false;
						$("#" + target).empty();
						genEditForm(target, dataFor, f.source, f.form, f.event, 4, alert, true);
						$("#" + modal).modal();
						if (typeof(event.detail.source.func) == "function"){
							event.detail.source.func(itemID, dataFor);
						}
					}
					else{
						if (typeof(event.detail.source.func) == "function"){
							logInfo(event.detail.source.address, event.detail.source.inputs, []);
							invoke(getURL(event.detail.source.address), event.detail.source.inputs, function(data){
								event.detail.source.func(itemID, dataFor, data, event.detail.source.inputs);
							},alert);
						}
					}
				}
				else{
					$("#alertAll").empty();
					$("#alertAll").append(genAlert("fail", "没有权限调用 " + urlName));
				}
			}
		});
		$(document).off("click", "button[data-for='" + dataFor + "_detail']");
		$(document).on("click", "button[data-for='" + dataFor + "_detail']", function(){
			var checkURL = getURL(event.detail.source.address).replace(prefix, "");
			var checkResult = fetch(getURL(verifyPermFunc), {checkURL:checkURL});
			var flag = checkResult["status"];
			var urlName = checkResult["urlName"];
			if (flag){
				var itemID = $(this).attr("id").split("_")[1];
				event.detail.source.inputs["id"] = itemID;
				if (event.detail["on"]){
					
					$("#" + title).text(adp_translate[dataFor] + "详情-编号" + itemID);
			        $("#" + target).empty();
			        
			        var f = $.extend(true, {}, editForm)
			        f.source = event.detail.source;
					f.source.inputs["id"] = itemID;
					f.form = event.detail.form;
					f.event.update = event.update;
					f.event.update.source.inputs["id"] = itemID;
					f.event.create.on = false;
					$("#" + target).empty();
					genEditForm(target, dataFor, f.source, f.form, f.event, 4, alert, true);
					$("#" + modal).modal();
					if (typeof(event.detail.source.func) == "function"){
						event.detail.source.func(itemID, dataFor);
					}
				}
				else{
					if (typeof(event.detail.source.func) == "function"){
						logInfo(event.detail.source.address, event.detail.source.inputs, []);
						invoke(getURL(event.detail.source.address), event.detail.source.inputs, function(data){
							event.detail.source.func(itemID, dataFor, data);
						},alert);
					}
				}
			}
			else {
				$("#alertAll").empty();
				$("#alertAll").append(genAlert("fail", "没有权限调用 " + urlName));
			}
		});
	}
	
	function attachDelete(){
		$(document).off("click", "button[data-for='" + dataFor + "_delete']");
		$(document).on("click", "button[data-for='" + dataFor + "_delete']", function(){	
			var itemID = $(this).attr("id").split("_")[1];
			if (event.del["on"]){
				if (confirm("是否确认删除?")){
		    		var $btn = $(this);
		    		event.del.source.inputs["id"] = itemID;
		    		logInfo(event.del.source.address, event.del.source.inputs, []);
		    		invoke(getURL(event.del.source.address), event.del.source.inputs, function(data){
		    			$("#" + alert).empty();
	                	$("#" + alert).append(genAlert("success", "编号" + itemID + " " + adp_translate[dataFor] + " 已被成功删除！"));
	                	$btn.closest("tr").remove();
	                	if (typeof(event.del.source.func) == "function"){
	    					event.del.source.func(itemID, dataFor);
	    				}
		    		}, alert);
				}
			}
			else{
				if (typeof(event.del.source.func) == "function"){
					event.del.source.func(itemID, dataFor);
				}
			}
		return false;
		});
	}
	
	function attachAdd(){
		var modal = dataFor + "_modal";
		if (event.add.modal_id != ""){
			modal = event.add.form.modal_id;
		}
		var target = dataFor + "_detail";
		var alert = dataFor + "_alert";
		var title = dataFor + "_titile";
		if (event.add.form.target_id != ""){
			target = event.add.form.target_id;
		}
		if (event.add.form.alert_id != ""){
			alert = event.add.form.alert_id;
		}
		if (event.add.form.title_id != ""){
			title = event.add.form.title_id;
		}
		$(document).off("click", "button[data-for='" + dataFor + "_add']");
		$(document).on("click", "button[data-for='" + dataFor + "_add']", function(){
			var checkURL = getURL(event.create.source.address).replace(prefix, "");
			var checkResult = fetch(getURL(verifyPermFunc), {checkURL:checkURL});
			var flag = checkResult["status"];
			var urlName = checkResult["urlName"];
			if (flag){
				if (event.add["on"]){
					$("#" + title).text(adp_translate[dataFor] + "创建 ");
				    $("#" + target).empty();
				    
				    var f = $.extend(true, {}, editForm)
			        f.source = event.add.source;
					f.form = event.add.form;
					f.event.create = event.create;
					f.event.update.on = false;
					
					genEditForm(target, dataFor, f.source, f.form, f.event, 4, alert, false);
					$("#" + modal).modal();
					if (typeof(event.add.source.func) == "function"){
						event.add.source.func(dataFor);
					}
				}
				else{
					if (typeof(event.add.source.func) == "function"){
						event.add.source.func(dataFor);
					}
				}
			}
			else {
				$("#alertAll").empty();
				$("#alertAll").append(genAlert("fail", "没有权限调用 " + urlName));
			}
		});
	}
	
	function attachExecute(){
		$(document).off("click", "button[data-for='" + dataFor + "_execute']");
		$(document).on("click", "button[data-for='" + dataFor + "_execute']", function(){	
			var itemID = $(this).attr("id").split("_")[1];
			if (event.execute["on"]){
				if (confirm("是否确认执行?")){
		    		var $btn = $(this);
		    		event.execute.source.inputs["id"] = itemID;
		    		logInfo(event.execute.source.address, event.execute.source.inputs, []);
		    		invoke(getURL(event.execute.source.address), event.execute.source.inputs, function(data){
		    			$("#" + alert).empty();
	                	$("#" + alert).append(genAlert("success", "编号" + itemID + " " + adp_translate[dataFor] + " 已被成功执行！"));
	                	if (typeof(event.execute.source.func) == "function"){
	    					event.execute.source.func(itemID, dataFor);
	    				}
		    		}, alert);
				}
			}
			else{
				if (typeof(event.execute.source.func) == "function"){
					event.execute.source.func(itemID, dataFor);
				}
			}
		return false;
		});
	}
	
	function attachReject(){
		$(document).off("click", "button[data-for='" + dataFor + "_reject']");
		$(document).on("click", "button[data-for='" + dataFor + "_reject']", function(){	
			var itemID = $(this).attr("id").split("_")[1];
			if (event.reject["on"]){
				if (confirm("是否确认拒绝?")){
		    		var $btn = $(this);
		    		event.reject.source.inputs["id"] = itemID;
		    		logInfo(event.reject.source.address, event.reject.source.inputs);
		    		invoke(getURL(event.reject.source.address), event.reject.source.inputs, function(data){
		    			$("#" + alert).empty();
	                	$("#" + alert).append(genAlert("success", "编号" + itemID + " " + adp_translate[dataFor] + " 已被成功执行！"));
	                	if (typeof(event.reject.source.func) == "function"){
	    					event.reject.source.func(itemID, dataFor);
	    				}
		    		}, alert);
				}
			}
			else{
				if (typeof(event.reject.source.func) == "function"){
					event.reject.source.func(itemID, dataFor);
				}
			}
		return false;
		});
	}
}

function drawTableWithCheck(target_id, dataFor, val, buttons, new_window){
	var iid = dataFor + "_tableWithCheck";
	var $table = $("<table class='table table-hover'></table>");
	$table.attr("id", iid);
	var titles = val.titles;
	var data = val.data;
	$title_tr = $("<tr><th></th></tr>");
	for (var i =0; i< titles.length; i++){
		$title_tr.append("<th>" + titles[i] + "</th>");
	}
	$title_tr.append("<th></th>")
	$table.append($title_tr);
	
	for (var i=0; i < data.length; i++) {
		var $tr = $("<tr><th></th></tr>");
		$tr.attr("id", data[i][0]);
		$tr.attr("data-for", dataFor);
		for (var j=0; j < titles.length; j++){
			var $td = $("<td></td>");
			if (typeof(data[i][j]) == "object"){
				var $ul = $("<ul></ul>")
				if(data[i][j]){
					$.each(data[i][j], function(i,v){
						$ul.append("<li>" + v + "</li>");
					});
				}
				$td.append($ul);
				
			}
			else{
				$td.append(data[i][j]);
			}
			$tr.append($td);
		}
		var $b_td = $("<td></td>");
		for (var k = 0; k < buttons.length; k++){
			$b_td.append("&nbsp;&nbsp;");
			var domid = dataFor + "_" + data[i][0]
			if (buttons[k]== "detail") {
				if (new_window){
					$b_td.append("<a class='btn btn-primary' href='" + getURL(dataFor) + "?id=" + data[i][0]+ "' target='_blank'>查看</a>");
				}
			}
			else if (buttons[k]== "check") {
				$b_td.append($("<button id='" + domid + "_check' class='btn btn-primary' data-for='" + dataFor + "_check'>查看</button>"));
			}
			else if (buttons[k] == "del" || buttons[k] == "delete") {
				$b_td.append($("<button id='" + domid + "_delete' class='btn btn-danger' data-for='" + dataFor + "_delete'>删除</button>"));
			}
			else if (buttons[k] == "update") {
				$b_td.append($("<button id='" + domid + "_delete' class='btn btn-primary' data-for='" + dataFor + "_update'>更新</button>"));
			}
			else if (buttons[k] == "execute") {
				$b_td.append($("<button id='" + domid + "_execute' class='btn btn-success' data-for='" + dataFor + "_execute'>执行</button>"));
			}
			else if (buttons[k] == "reject") {
				$b_td.append($("<button id='" + domid + "_reject' class='btn btn-danger' data-for='" + dataFor + "_reject'>拒绝</button>"));
			}
			else if (buttons[k]== "log") {
				$b_td.append($("<button id='" + domid + "_log' class='btn btn-info' data-for='" + dataFor + "_log'>日志</button>"));
			}
		}
		$tr.append($b_td);
		$table.append($tr); 
	}
	
	$("#" + target_id).append($table);
	if (buttons.indexOf("add") >= 0){
		var $div = $("<div class='col-md-offset-10 col-md-2'></div>");
		var dom_id = dataFor + "_add";
		var $add_btn = $("<button id='" + dom_id + "' class='btn btn-success' data-for='" + dom_id + "'>增加</button>");
		$div.append($add_btn);
		$("#" + target_id).append($div);
	}
	return iid;
}

var editForm = {
		source : {
			on:true,
			address:"",
			inputs:{},
			value:[]
		},
		form : {
			name: "",
			inputs: {},
			func: ""
		},

		event : {
			create:{
				on:true,
				source:{
					address:"",
					inputs: {},
					func:""
				}
			},
			update:{
				on:true,
				source:{
					address:"",
					inputs: {},
					func:""
				}
			}
		}
}

function genEditForm(target_id, dataFor, source, form, event, num_in_line, alert, disabled){
	//处理数据源和表格内容，并将表格画到界面上；
	var ff = getForm(form.name);
	var messageHeader = ff.messageHeader;
	
	if (typeof(source["on"])=="undefined" || source["on"]){
		logInfo(source.address, source.inputs, []);
		invoke(getURL(source.address), source.inputs, function(data){
			for (var key in form.inputs){
				for (var k in form.inputs[key]){
					if (typeof(form.inputs[key][k]) == "function"){	
						form.inputs[key][k] = form.inputs[key][k](data);
					}
				}
				
			}
			drawEditForm(target_id, dataFor, form.name, form.inputs, data.data, num_in_line, disabled);
			setDatePick();
			if (typeof(event.update) != "undefined"){
				attachUpdate();
			}
			if (typeof(event.create) != "undefined"){
				attachCreate();
			}
			if (typeof(form.func) == "function"){
				form.func(data);
			}
		}, alert);
	}
	else {
		drawEditForm(target_id, dataFor, form.name, form.inputs, source.value, num_in_line, disabled);
		setDatePick()
		if (typeof(event.update) != "undefined"){
			attachUpdate();
		}
		if (typeof(event.create) != "undefined"){
			attachCreate();
		}
		if (typeof(form.func) == "function"){
			form.func(source.value);
		}
	}
	
	//处理update事件
	function attachUpdate(){
		$("#" + dataFor + "_update").off("click").on("click", function(){
				if (typeof(event.update["on"]) == "undefined" || event.update["on"]){
					if ($(this).text() == "保存"){
						if ($("#" + dataFor).valid()){
							if (confirm("是否确认保存?")){
								var btn = $(this).button('操作中...')
				     			var params = [];
								var json = {};
								var param_names = [];
								
					 			json.header = messageHeader || dataFor + "Update";
					 			json.id = event.update.source.inputs.id;
					 			if (event.update.source.inputs.pid){
					 				json.pid = event.update.source.inputs.pid;
					 			}
					 			json.operation = "UPDATE";
				 			
				     			$.each($("[data-for='" + dataFor + "']"), function(index, value){
				     				if ($(value).prop("type") == "checkbox"){
				     					params.push($(value).is(":checked"));
				     					json[$(value).attr("name").split('_')[1]] = $(value).is(":checked");
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
				     					if($(value).attr("name")){
				     						json[$(value).attr("name").split('_')[1]] = l;
				     					}
				     				}
				     				else {
				     					params.push($(value).val());
				     					if($(value).attr("name")){
				     						json[$(value).attr("name").split('_')[1]] = $(value).val()||"";
				     					}
				     				}
				     				if($(value).attr("name")){
				     					param_names.push($(value).attr("name").split('_')[1])
				     				}
				     			});
				     			event.update.source.inputs["params"] = JSON.stringify(params);
				     			event.update.source.inputs["json"] = JSON.stringify(json);
				     			logInfo(event.update.source.address, event.update.source.inputs, param_names);
				     			
				     			invoke(getURL(event.update.source.address),event.update.source.inputs, function(data){
				     				$("#" + alert).empty();
			 	                	$("#" + alert).append(genAlert("success", "编号" + event.update.source.inputs.id + " " + adp_translate[dataFor] + "保存成功!"));
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
						 	if (typeof(event.update.source.editFunc) == "function"){
	 	                		event.update.source.editFunc(event.update.source.inputs.id, dataFor);
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
	
	//处理create事件
	function attachCreate(){
		$("#" + dataFor + "_create").off("click").on("click", function(){
			if (typeof(event.create["on"]) == "undefined" || event.create["on"]){
				if ($("#" + dataFor).valid()){
					if (confirm("是否确认创建?")){
						var btn = $(this).button('操作中...')
		     			var params = [];
						var json = {};
						var param_names = [];
			 			json.header = messageHeader||dataFor + "Create";
			 			if(event.create.source.inputs.pid){
			 				json.pid = event.create.source.inputs.pid;
			 			}
			 			json.operation = "ADD";
		     			$.each($("[data-for='" + dataFor + "']"), function(index, value){
		     				if ($(value).prop("type") == "checkbox"){
		     					params.push($(value).is(":checked"));
		     					json[$(value).attr("name").split('_')[1]] = $(value).is(":checked");
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
		     					if($(value).attr("name")){
		     						json[$(value).attr("name").split('_')[1]] = l;
		     					}
		     				}
		     				else {
		     					params.push($(value).val());
		     					if($(value).attr("name")){
		     						json[$(value).attr("name").split('_')[1]] =  $(value).val()||"";
		     					}
		     				}
		     				if ($(value).attr("name")){
		     					param_names.push($(value).attr("name").split('_')[1]);
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
		                	$("#" + alert).append(genAlert("success", "编号" + new_id + " " + adp_translate[dataFor] + "创建成功!"));
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

function genEditForm2(target_id, dataFor, source, form, event, num_in_line, alert, disabled){
	//处理数据源和表格内容，并将表格画到界面上；
	var ff = getForm(form.name);
	var messageHeader = ff.messageHeader;
	
	if (typeof(source["on"])=="undefined" || source["on"]){
		logInfo(source.address, source.inputs, []);
		invoke(getURL(source.address), source.inputs, function(data){
			for (var key in form.inputs){
				for (var k in form.inputs[key]){
					if (typeof(form.inputs[key][k]) == "function"){	
						form.inputs[key][k] = form.inputs[key][k](data);
					}
				}
				
			}
			drawEditForm(target_id, dataFor, form.name, form.inputs, data.data, num_in_line, disabled);
			setDatePick();
			if (typeof(event.update) != "undefined"){
				attachUpdate();
			}
			if (typeof(event.create) != "undefined"){
				attachCreate();
			}
			if (typeof(form.func) == "function"){
				form.func(data);
			}
		}, alert);
	}
	else {
		drawEditForm(target_id, dataFor, form.name, form.inputs, source.value, num_in_line, disabled);
		setDatePick()
		if (typeof(event.update) != "undefined"){
			attachUpdate();
		}
		if (typeof(event.create) != "undefined"){
			attachCreate();
		}
		if (typeof(form.func) == "function"){
			form.func(source.value);
		}
	}
	
	//处理update事件
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
					 			json.header = messageHeader||dataFor + "Update";
					 			if (event.update.source.inputs.pid){
					 				json.pid = event.update.source.inputs.pid;
					 			}
					 			json.operation = "UPDATE";
					 			json.id = event.update.source.inputs.id;
				     			$.each($("[data-for='" + dataFor + "']"), function(index, value){
				     				if ($(value).prop("type") == "checkbox"){
				     					params.push($(value).is(":checked"));
				     					if($(value).attr("name")){
				     						json[$(value).attr("name").split("_")[1]] = $(value).is(":checked");
				     					}
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
				     						json[$(value).attr("name").split("_")[1]] = $(value).val();
				     					}
				     				}
				     				if($(value).attr("name")){
			     						param_names.push([$(value).attr("name").split("_")[1]]);
			     					}
				     			});
				     			event.update.source.inputs["params"] = JSON.stringify(params);
				     			event.update.source.inputs["json"] = JSON.stringify(json);
				     			logInfo(event.update.source.address, event.update.source.inputs, param_names);
				     			
				     			invoke(getURL(event.update.source.address),event.update.source.inputs, function(data){
				     				$("#" + alert).empty();
			 	                	$("#" + alert).append(genAlert("success", "编号" + event.update.source.inputs.id + " " + adp_translate[dataFor] + "保存成功!"));
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
							if (typeof(event.update.source.editFunc) == "function"){
								event.update.source.editFunc(event.update.source.inputs.id, dataFor);
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
	
	//处理create事件
	function attachCreate(){
		$("#" + dataFor + "_create").off("click").on("click", function(){
			if (typeof(event.create["on"]) == "undefined" || event.create["on"]){
				if ($("#" + dataFor).valid()){
					if (confirm("是否确认创建?")){
						var btn = $(this).button('操作中...')
		     			var params = [];
						var param_names = [];
						var json = {};
			 			json.header = messageHeader + "Create";
			 			if (event.update.source.inputs.pid){
			 				json.pid = event.update.source.inputs.pid;
			 			}
			 			json.operation = "UPDATE";
		     			$.each($("[data-for='" + dataFor + "']"), function(index, value){
		     				if ($(value).prop("type") == "checkbox"){
		     					params.push($(value).is(":checked"));
		     					if($(value).attr("name")){
		     						json[$(value).attr("name").split("_")[1]] = $(value).is(":checked");
		     					}
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
		     						json[$(value).attr("name").split("_")[1]] = $(value).val();
		     					}
		     				}
		     				if($(value).attr("name")){
	     						param_names.push($(value).attr("name").split("_")[1]);
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
		                	$("#" + alert).append(genAlert("success", "编号" + new_id + " " + adp_translate[dataFor] + "创建成功!"));
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

function drawEditForm(target_id, dataFor, form_name, inputs, values, num_in_line, disabled){
	$form = $("<form class='form-horizontal' id='" + dataFor + "'></form>");
	$checkboxes = $("<div id='checkboxes'></div>");
	var form_data = getForm(form_name);
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
		if(s.label){
			$field.attr("id", "fieldset_" + s.label);
		}
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
			var dom_id = dataFor + "_" + forms[i].cat;
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
			else if (forms[i].type == "textarea") {
				$form_group.append("<label for='" + dom_id + "' class='col-md-1 control-label'>" + forms[i].label + "</label>");
				var $input = genTextArea(dom_id, dataFor, forms[i].value, dis, ed);
				$div.append($input);
			} 
			else if (forms[i].type == "pwd"){
				$form_group.append("<label for='" + dom_id + "' class='col-md-1 control-label'>" + forms[i].label + "</label>");
				var $input = genPwd(dom_id, dataFor, forms[i].value, dis, ed);
				$div.append($input);
			}
			else if (forms[i].type == "button"){
				$form_group.append("<label for='" + dom_id + "' class='col-md-1 control-label'>" + forms[i].label + "</label>");
				var $input = genButton(dom_id, "success", forms[i].label);
				$div.append($input);
			}
			else if (forms[i].type == "select") {
				var dom_id = dataFor + "_" + forms[i].cat;
				var mul = forms[i].multiple;
				
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
					options = fetch(getURL(forms[i].options.url), inputs[forms[i].cat]);
				}
				else{
					options = forms[i].options.values;
				}
				$select = genSelect(dom_id, dataFor, options, forms[i].value, dis, ed, "", mul);
				$div.append($select);
			}
			else if (forms[i].type == "auto") {
				var dom_id = dataFor + "_" + forms[i].cat;
				$form_group.append("<label for='" + dom_id + "' class='col-md-1 control-label'>" + forms[i].label + "</label>");
				var options;
				
				if (forms[i].options.type == "fetch"){
					options = fetch(getURL(forms[i].options.url), "");
				}
				else if (forms[i].options.type == "function"){
					options = fetch(getURL(forms[i].options.url), inputs[forms[i].cat]);
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
					options = fetch(getURL(forms[i].options.url), inputs[forms[i].cat]);
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
	var $div = $("<div class='col-md-offset-11 col-md-1'></div>")
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

function drawFilterForm(target_id, dataFor, form_name, inputs, addList){
	var data = getForm(form_name);
	
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
				options = fetch(getURL(data[i].options.url), inputs["" + i]);
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
				options = fetch(getURL(data[i].options.url), inputs["" + i]);
			}
			else{
				options = data[i].options.values;
			}
			$form_group.append("&nbsp;&nbsp;");
			$form_group.append(genAuto(dom_id, dataFor + "_filter", options, value, false));
			$form.append($form_group);
		}
		else if (type == "checkbox"){
			var $label = $("<label for='" + dom_id + "'>" + label + "</label>");
			$form_group.append($label);
			$form_group.append("&nbsp;&nbsp;");
			var $checkbox = genCheckbox(dom_id, dataFor + "_filter", value, false, true);
			$form_group.append($checkbox);
			$form.append($form_group);
		}
		else if (type == "button"){
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
		
		else if (type == "a") {
			var $a = genLink(dom_id, dataFor + "_filter", prefix + value, label, false);
			$form_group.append($a);
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
	setDatePick();
}

function drawFilterForm2(target_id, dataFor, form_name, inputs, addList){
	var data = getForm(form_name);
	
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
				options = fetch(getURL(data[i].options.url), inputs["" + i]);
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
				options = fetch(getURL(data[i].options.url), inputs["" + i]);
			}
			else{
				options = data[i].options.values;
			}
			$form_group.append("&nbsp;&nbsp;");
			$form_group.append(genAuto(dom_id, dataFor + "_filter", options, value, false));
			$form.append($form_group);
		}
		else if (type == "button"){
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

var orgChart = {
		source : {
			on:true,
			address:"",
			inputs:{},
			value:[]
		},
		event : {
			update:{
				on:true,
				location:"",
				source:{
					address:"",
					inputs: {},
					func:""
				}
			}
		}
}

function drawOrgChart(target_id, btn_id, dataFor, data, alert){
	var org_chart = $("#" + target_id).orgChart({
        data: data,
        showControls: true,
        allowEdit: true,
        newNodeText: "子类别",
        onAddNode: function(node){
            org_chart.newNode(node.data.id); 
        },
        onDeleteNode: function(node){
        	var nodeId = "" + node.data.id;
        	if (node.data.parent == 0){
        		$("#" + alert).empty();
            	$("#" + alert).append(genAlert("fail", "无法删除根节点！"));
        	}
            if (nodeId.indexOf("NEW") == 0){
            	org_chart.deleteNode(node.data.id);	
            }
            else {
            	$("#" + alert).empty();
            	$("#" + alert).append(genAlert("warn", "已经保存成功的" + adp_translate[dataFor] + "无法删除！"));
            } 
        }
    });
	
	var $div = $("<div class='col-md-offset-9 col-md-3'></div>");
	$div.append("<button id='" + dataFor + "_update' class='btn btn-success'>保存</button>");
	$("#" + btn_id).append($div);
	return org_chart;
	
}

function genOrgChart(target_id, btn_id, dataFor, source, event, alert){
	//处理数据源和表格内容，并将表格画到界面上；
	if (typeof(source["on"])=="undefined" || source["on"]){
		logInfo(source.address, source.inputs, []);
		invoke(getURL(source.address), source.inputs, function(data){
			var org_chart = drawOrgChart(target_id, btn_id, dataFor, data, alert);
			if (typeof(event.update) != "undefined"){
				attachUpdate(org_chart);
			}
			
		}, alert);
	}
	else {
		var org_chart = drawOrgChart(target_id, btn_id, dataFor, source.value, alert);
		if (typeof(event.update) != "undefined"){
			attachUpdate(org_chart);
		}
	}
	
	//处理update事件
	function attachUpdate(chart){
		$("#" + dataFor + "_update").off("click").on("click", function(){
			var params = JSON.stringify(chart.getData());
			var id = source.inputs.id;
			if (confirm("是否确认保存?")){
				logInfo(event.update.source.address, {id:id, params:params}, "params");
				invoke(getURL(event.update.source.address), {id:id, params:params}, function(data){
					$("#" + alert).empty();
	             	$("#" + alert).append(genAlert("success", adp_translate[dataFor] + "保存成功!"));
	             	$("#" + target_id).empty();
	             	$("#" + btn_id).empty();
	             	updateSelectOptions($("#" + dataFor + "_table_filter"), fetch(getURL(dataFor + "List"), {}));
				}, alert);
			}
			
		});
	}
}

function drawSelections(target_id, title1, title2, select1, select2, options1, options2, dataFor, id, name, updateURL, alert){
	if (typeof(alert) == "undefined"){
		alert = "alertAll";
	}
	var $div = $("<div></div>");
	var $row1 = $("<div class='row'><div class='col-md-2 col-md-offset-1'><h4>" + title1 + "</h4></div><div class='col-md-2 col-md-offset-1'><h4>" + title2 + "</h4></div></div>");
	$div.append($row1);
	
	var $row2 = $("<div class='row'></div>");
	
	var $div_select1 = $("<div class='col-md-2 col-md-offset-1'></div>");
	var $select1 = $("<select multiple></select>");
	$select1.attr("id", select1);
	$select1.css({width:"200px", height:"400px"});
	$select1.attr("data-for", dataFor);
	for (var key in options1) {
		$option = $("<option>" + options1[key] + "</option>")
		$option.attr("value", key);
		$select1.append($option);
	}
	$div_select1.append($select1);
	$row2.append($div_select1);
	
	var $div_btns = $("<div class='col-md-1'><br><br><br><br><br></div>");
	var btns = ["add", "addAll", "remove", "removeAll"];
	var btns_t = ["&gt;|", "&gt;&gt;", "&lt;|", "&lt;&lt;"];
	$.each(btns, function(i,btn){
		var $button;
		if (btn == "add" || btn == "addAll"){
			$button = $("<div><button class='btn btn-info' id='" + btn + "_" + dataFor + "' data-for='" + dataFor + "' style='width:60px;'>" + btns_t[i] + "</button></div>")
		}
		else{
			$button = $("<div><button class='btn btn-danger' id='" + btn + "_" + dataFor + "' data-for='" + dataFor + "' style='width:60px;'>" + btns_t[i] + "</button></div>")
		}
		$div_btns.append($button);
		$div_btns.append("<br>");
	});
	$row2.append($div_btns);
	
	var $div_select2 = $("<div class='col-md-2'></div>");
	var $select2 = $("<select multiple></select>");
	$select2.attr("id", select2);
	$select2.css({width:"200px", height:"400px"});
	$select2.attr("data-for", dataFor);
	for (var key in options2) {
		$option = $("<option>" + options2[key] + "</option>")
		$option.attr("value", key);
		$select2.append($option);
	}
	$div_select2.append($select2);
	$row2.append($div_select2);
	$div.append($row2);
	
	var $div_update = $("<div class='row'><div class='col-md-1 col-md-offset-5'></div></div>");
	var $btn_update = $("<div><button class='btn btn-success' id='update_" + dataFor + "'>保存</button></div>")
	$div_update.append($btn_update);
	$div.append($div_update);
	
	$("#" + target_id).append($div);
	
	$("#add_" + dataFor).on("click", function(){
		var $options = $("#" + select1 + " option:selected");
		$options.appendTo($select2);
	});
	$("#addAll_" + dataFor).on("click", function(){
		var $options = $("#" + select1 + " option");
		$options.appendTo($select2);
	});
	$select1.on("dblclick", function(){
		var $options = $("option:selected", this);
		$options.appendTo($select2);
	});
	$("#remove_" + dataFor).on("click", function(){
		var $options = $("#" + select2 + " option:selected");
		$options.appendTo($select1);
	});
	$("#removeAll_" + dataFor).on("click", function(){
		var $options = $("#" + select2 + " option");
		$options.appendTo($select1);
	});
	$select2.on("dblclick", function(){
		var $options = $("option:selected", this);
		$options.appendTo($select1);
	});
	$btn_update.on("click", function(){
		var $btn = $(this);
		var cat = $(this).text();
		if (cat === "保存"){
			var params = [];
			if (confirm("是否确认保存")){
				$.each($("#" + select2 + " option"), function(i, option){
					params.push($(option).val());
				});
				invoke(getURL(updateURL), {id:id, name:name, params:JSON.stringify(params)}, function(data){
					$("[data-for='" + dataFor + "']").prop("disabled", true);
					$("#" + alert).empty();
					$("#" + alert).append(genAlert("success", "编号" + id + " " + adp_translate[dataFor] + "保存成功!"));
					$btn.text("更新");
					$btn.addClass("btn btn-primary");
				}, "alertAll")
			}
		}
		else {
			$("[data-for='" + dataFor + "']").prop("disabled", false);
			$btn.text("保存");
			$btn.addClass("btn btn-success");
		}
	});
}

function updateSelectOptions(select, options){
	var old_v = select.val();
	select.empty();
	if (! options){
		options = [];
	}
	select.find("option").remove();
	for (var key in options){
		select.append("<option value='" + key + "'>" + options[key] + "</option>")
	}
	if (Object.keys(options).indexOf(old_v) >= 0){
		select.val(old_v);
	}
	else{
		select.val(Object.keys(options)[0]);
	}
	
}

function genSelect(dom_id, dataFor, options, value, disable, editable, cls, mul){
	var $select = $("<select class='form-control' data-for='" + dataFor + "'></select>");
	if (dom_id != "") {
		$select.attr('id', dom_id);
		$select.attr('name', dom_id);
	}
	
	if (options.length == 0 && (typeof(value) !="undefined" || value != "")){
		options[value] = value;
	}
	
	for (var key in options) {
		$option = $("<option>" + options[key] + "</option>")
		$option.attr("value", key);
		$select.append($option);
	}
	
	if ((typeof(value)=="undefined" || value == "") && Object.keys(options).length > 0){
		$select.val(Object.keys(options)[0]);
	}
	else {
		$select.val(value);	
	}
	
	if (disable) {
		$select.prop("disabled", true);
	}
	else {
		$select.prop("disabled", false);
	}
	if (typeof(editable) != "undefined"){
		$select.prop("editable", editable);
	}
	else{
		$select.prop("editable", true);
	}
	
	if (typeof(cls) != "undefined"){
		$select.addClass(cls);
	}
	if (typeof(mul) != "undefined" && mul){
		$select.attr("multiple", true);
		$select.attr("size", 2);
	}
	return $select;
}

function genAuto(dom_id, dataFor, options, value, disabled, editable, cls){
	var $input = $("<input class='form-control' data-provide='typeahead' type='text' data-for='" + dataFor + "'>");
	
	if (dom_id != ""){
		$input.attr('id', dom_id);
		$input.attr('name', dom_id);
	}
	
	$input.val(value);
	if (disabled) {
		$input.prop("disabled", true);
	}
	else {
		$input.prop("disabled", false);
	}
	var ops = [];
	if (typeof(options) == "object"){
		$.each(options, function(key, value){
			//ops.push(key + "|" + value);
			ops.push(value);
		});
	}
	else {
		ops = options;
	}
	if (ops.length > 0){
		$input.typeahead({source:ops});
	}
	if (typeof(editable) != "undefined"){
		$input.prop("editable", editable);
	}
	else {
		$input.prop("editable", true);
	}
	if (typeof(cls) != "undefined"){
		$input.addClass(cls);
	}
	return $input;
}

function genInput(dom_id, dataFor, value, disabled, editable, cls){
	var $input = $("<input class='form-control' type='text' data-for='" + dataFor + "'>");
	if (dom_id != ""){
		$input.attr('id', dom_id);
		$input.attr('name', dom_id);
	}
	
	$input.val(value);

	if (disabled) {
		$input.prop("disabled", true);
	}
	else {
		$input.prop("disabled", false);
	}
	if (typeof(editable) != "undefined"){
		$input.prop("editable", editable);
	}
	else{
		$input.prop("editable", true);
	}
	if (typeof(cls) != "undefined"){
		$input.addClass(cls);
	}
	return $input;
}

function genTextArea(dom_id, dataFor, value, disabled, editable, cls){
	var $input = $("<textarea style='margin: 0px -734.325px 0px 0px; width: 946px; height: 227px;' class='form-control' type='text' data-for='" + dataFor + "'><textarea>");
	if (dom_id != ""){
		$input.attr('id', dom_id);
		$input.attr('name', dom_id);
	}
	
	$input.val(value);

	if (disabled) {
		$input.prop("disabled", true);
	}
	else {
		$input.prop("disabled", false);
	}
	if (typeof(editable) != "undefined"){
		$input.prop("editable", editable);
	}
	else{
		$input.prop("editable", true);
	}
	if (typeof(cls) != "undefined"){
		$input.addClass(cls);
	}
	return $input;
}

function genPwd(dom_id, dataFor, value, disabled, editable, cls){
	var $input = $("<input class='form-control' type='password' data-for='" + dataFor + "'>");
	if (dom_id != ""){
		$input.attr('id', dom_id);
		$input.attr('name', dom_id);
	}
	
	$input.val(value);

	if (disabled) {
		$input.prop("disabled", true);
	}
	else {
		$input.prop("disabled", false);
	}
	if (typeof(editable) != "undefined"){
		$input.prop("editable", editable);
	}
	else{
		$input.prop("editable", true);
	}
	if (typeof(cls) != "undefined"){
		$input.addClass(cls);
	}
	return $input;
}

function genLink(dom_id, dataFor, link, value){
	var $a = $("<a href='" + link + "' target='_blank'></a>");
	$a.attr("data-for", dataFor);
	if (dom_id != ""){
		$a.attr('id', dom_id);
		$a.attr('name', dom_id);
	}
	
	$a.text(value);
	return $a;
}

function genDateInput(dom_id, dataFor, value, disabled, editable, cls){
	var $input = $("<input type='text' size='16' value='' class='form-control form_datetime' data-for='" + dataFor + "'>");
	if (dom_id != ""){
		$input.attr('id', dom_id);
		$input.attr('name', dom_id);
	}
	$input.val(value);
	if (disabled) {
		$input.prop("disabled", true);
	}
	else {
		$input.prop("disabled", false);
	}
	if (typeof(editable) != "undefined"){
		$input.prop("editable", editable);
	}
	else{
		$input.prop("editable", true);
	}
	if (typeof(cls) != "undefined"){
		$input.addClass(cls);
	}
	return $input;
}

function genCheckbox(dom_id, dataFor, value, disabled, editable){
	var $check = $("<input type='checkbox' data-for='" + dataFor + "'>");
	if (dom_id != ""){
		$check.attr('id', dom_id);	
		$check.attr('name', dom_id);
	}
	if (value){
		$check.attr("checked", true);
	}
	else {
		$check.attr("checked", false);
	}
	if (disabled) {
		$check.prop("disabled", true);
	}
	else {
		$check.prop("disabled", false);
	}
	if (typeof(editable) != "undefined"){
		$check.prop("editable", editable);
	}
	else {
		$check.prop("editable", true);
	}
	return $check;
	
}

function genCheckbox2(dom_id, dataFor, value, disabled, editable){
	var $check = $("<input class='form-control' type='checkbox' data-for='" + dataFor + "'>");
	if (dom_id != ""){
		$check.attr('id', dom_id);	
		$check.attr('name', dom_id);
	}
	if (value){
		$check.attr("checked", true);
	}
	else {
		$check.attr("checked", false);
	}
	if (disabled) {
		$check.prop("disabled", true);
	}
	else {
		$check.prop("disabled", false);
	}
	if (typeof(editable) != "undefined"){
		$check.prop("editable", editable);
	}
	else {
		$check.prop("editable", true);
	}
	return $check;
	
}


function genButton(dom_id, type, label){
	return $("<a href='#' role='button' id='" + dom_id + "_btn' class='btn btn-" + type + "'>" + label +"</a>");
}

function genBtn(dom_id, type, label){
	return $("<button id='" + dom_id + "_btn' class='btn btn-" + type + "'>" + label +"</butotn>");
}

function genFileUpload(dom_id, dataFor, cls, label){
	var $span = $("<span class='btn btn-" + cls + " fileinput-button'></span>");
	var $i = $("<i class='glyphicon glyphicon-plus'></i>");
	var $label = $("<span>" + label + "</span>")
	var $input = $("<input class='form-control' id='fileupload' type='file' name='files'>");
	$input.attr("id", dom_id);
	$input.attr("data-for", dataFor);
	$span.append($i);
	$span.append($label);
	$span.append($input);
	return $span;
}

function genRadios(dom_id, dataFor, values){
	for (var i = 0; i < values.length; i++){
		var id = values[i][0];
		var value = values[i][0];
		var label = values[i][2];

		var $l = $("<label class='checkbox-inline'></label>");
		var $input = $("<input type='checkbox' id='ratio_" + id + "' data-for='" + dataFor + "'>" + label + "</input>");
		$input.val(value);

		$l.append($input);
		$("#" + dom_id).append($l);
	}
}

function genCheckboxes(dom_id, dataFor, options, value, disable, editable){
	var $div = $("<div></div>");
	$div.prop("type", "checkboxes")
	if (dom_id != "") {
		$div.attr('id', dom_id);
		$div.attr('name', dom_id);
	}
	$div.attr("data-for", dataFor);
	
	for (var key in options) {
		var $ratio = $("<label class='checkbox-inline'></label>");
		var $input = $("<input type='checkbox'>" + options[key] + "</input>");
		$input.attr("id", dom_id + "_" + key);
		$input.attr("value", key);
		if (typeof(value) == "object"){
			if (value.indexOf(parseInt(key)) >= 0){
				$input.attr("checked", true);
			}
			else{
				$input.attr("checked", false);
			}
		}
		else{
			$input.attr("checked", false);
		}
		if (disable) {
			$input.prop("disabled", true);
		}
		else {
			$input.prop("disabled", false);
		}
		
		$ratio.append($input);
		$div.append($ratio);
	}
	if (typeof(editable) != "undefined"){
		$div.prop("editable", editable);
	}
	else{
		$div.prop("editable", true);
	}
	return $div;
}

function alerts(alts, level, msg){
	var a_list = [];
	if (typeof(alts) == "string"){
		a_list.push(alts);
	}
	else if (typeof(alts) == "object"){
		a_list = alts;
	}
	else {
		a_list.push("alertAll");
	}
	$.each(a_list, function(i, alert){
		$("#" + alert).empty();
		$("#" + alert).append(genAlert(level, msg));
	});
	return;
}
function genAlert(cls, message){
	var clss = "";
	var msg = "";
	if (cls == "success"){
		clss = "alert alert-success alert-dismissible";
		msg = "成功： " + message;
	}
	else if (cls == "info"){
		clss = "alert alert-info alert-dismissible";
		msg = "信息： " + message;
	}
	else if (cls == "warn"){
		clss = "alert alert-warning alert-dismissible";
		msg = "警告： " + message; 
	}
	else if (cls == "fail"){
		clss = "alert alert-danger alert-dismissible";
		msg = "失败： " + message; 
	}
	
	var $alert = $("<div role='alert'></div>");
	$alert.addClass(clss);
	$alert.text(msg);
	return $alert;
}

function genTabs(target_id, ul_id, content_id, tabs){
	var $ul = $("<ul class='nav nav-tabs' role='tablist'></ul>");
	$ul.attr("id", ul_id);
	
	var $con = $("<div class='tab-content'></div>");
	$con.attr("id", content_id);
	
	$.each(tabs, function(key, value){
		var $li = $("<li role='presentation'></li>");
		$li.attr("id", key + "_tab");
		var $a = $("<a aria-controls='nav_display' role='tab' data-toggle='tab'></a>");
		$a.attr("id", key + "_a");
		$a.attr("href", "#" + key + "_display");
		$a.text(value);
		$li.append($a);
		$ul.append($li);
		
		var $d = $("<div role='tabpanel' class='tab-pane fade'></div>");
		$d.attr("id", key + "_display");
		$con.append($d);
	});
	$("#" + target_id).append($ul);
	$("#" + target_id).append($con);
}

function addTabs(ul_id, content_id, tabs){
	var $ul = $("#" + ul_id);
	var $con = $("#" + content_id);
	$.each(tabs, function(key, value){
		var $li = $("<li role='presentation'></li>");
		$li.attr("id", key + "_tab");
		var $a = $("<a aria-controls='nav_display' role='tab' data-toggle='tab'></a>");
		$a.attr("id", key + "_a");
		$a.attr("href", "#" + key + "_display");
		$a.text(value);
		$li.append($a);
		$ul.append($li);
		
		var $d = $("<div role='tabpanel' class='tab-pane fade'></div>");
		$d.attr("id", key + "_display");
		$con.append($d);
	});
}

function removeAlert(){
	setTimeout(function(){
		$("#alertAll").empty();
		removeAlert();
	}, 15000);	
}

function spin(){
	$("#ajax_loader").bind("ajaxSend", function(){
		$(this).show();
	}).bind("ajaxStop", function(){
		$(this).hide();
	}).bind("ajaxError", function(){
		$(this).hide();
	});
}

function getFormatDate(date) {
	var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    return year + seperator1 + month + seperator1 + strDate;
}

function getNowFormatDate() {
    var date = new Date();
    return getFormatDate(date);
}

function getNewDate(ds, days){
	var date = new Date(Date.parse(ds.replace(/-/g,"/")));
	var r_date = new Date(date - 0 + days * 86400000);
	return getFormatDate(r_date);
}

function lastDate(datestr){
	var yy = datestr.split("-")[0];
	var mm = datestr.split("-")[1];
	var dd = datestr.split("-")[2];
	
	var lastdate = new Date(yy, mm, 0);
	return lastdate.getDate() == dd;
}

function val2key(array, val){
	for (var key in array){
		var this_val = array[key];
		if (this_val == val){
			return key;
			break;
		}
	}
}

function getLeftOps(full, used){
	var left = {};
	$.each(full, function(key, value){
		if (! used.hasOwnProperty(key)){
			left[key] = value;
		}
	});
	return left;
}

function removeOps(full, used){
	var left = {};
	$.each(used, function(key, value){
		if (full.hasOwnProperty(key)){
			left[key] = value;
		}
	});
	return left;
}

function genPagination(target_id, pages){
	$("#" + target_id + "_pag").remove();
	var pagid = target_id + "_pag";
	var $pagDiv = $("<div class='pagination'></div>");
	var $pageP = $("<p></p>");
	$pageP.attr("id", pagid);
	$pageP.css("text-align", "center");
	$pageP.append($pagDiv);
	$pageP.insertAfter($("#" + target_id));
	$pagDiv.bootpag({
		total:pages,
		page:1,
		maxVisible:10,
		leaps: true,
	    firstLastUse: true,
	    first: '1 ←',
	    last: '→ ' + pages,
	    wrapClass: 'pagination',
	    activeClass: 'active',
	    disabledClass: 'disabled',
	    nextClass: 'next',
	    prevClass: 'prev',
	    lastClass: 'last',
	    firstClass: 'first'
	});
	return $pagDiv;
}

function genPagination2(target_id, pages){
	$("#" + target_id + "_pag").remove();
	var pagid = target_id + "_pag";
	var $pagDiv = $("<div class='pagination'></div>");
	var $pageP = $("<p></p>");
	$pageP.attr("id", pagid);
	$pageP.css("text-align", "center");
	$pageP.append($pagDiv);
	($("#" + target_id)).append($pageP);
	$pagDiv.bootpag({
		total:pages,
		page:1,
		maxVisible:10,
		leaps: true,
	    firstLastUse: true,
	    first: '1 ←',
	    last: '→ ' + pages,
	    wrapClass: 'pagination',
	    activeClass: 'active',
	    disabledClass: 'disabled',
	    nextClass: 'next',
	    prevClass: 'prev',
	    lastClass: 'last',
	    firstClass: 'first'
	});
	return $pagDiv;
}

function unifyForm(form_name, values){
	var form_data = getData(form_name);
	if ((! form_data) || (! values)){
		return "";
	}
	var i = 0;
	
	var ffs;
	var titles;
	var sets;
	var inputs = {};
	var func = [];
	if (form_name.indexOf("assetForms") > 0){
		ffs = form_data.smfForm.forms;
		sets = form_data.smfForm.editSets;
	}
	else if (form_name.indexOf("tradeForms") > 0){
		ffs = form_data.forms;
		sets = form_data.editSets;
		func = form_data.func;
	}
	else{
		ffs = form_data.forms;
		sets = form_data.sets;
	}
	titles = form_data.titles;
	
	$.each(ffs, function(i, f){
		if (form_name.indexOf("add_form") > 0){
			ffs[i].cat = ffs[i].label;
			ffs[i].label = titles[i];
		}
		if (values instanceof Array){
			ffs[i].value = values[i];
			inputs[ffs[i].cat] = values[i];
		}
		else{
			ffs[i].value = values[ffs[i].cat];
			inputs[ffs[i].cat] = values[ffs[i].cat];
		}
	});
	if (inputs.port){
		inputs["ports"] = [inputs.port];
 		inputs["ports_str"] = JSON.stringify(inputs.ports);
 		inputs["client"] = fetch(getURL("clientByPort"), {portID:inputs.port});;
	}
	if (form_name.indexOf("assetForms") > 0){
		inputs["category"] = smf;
	}
	if (form_name.indexOf("caForms") > 0){
		inputs["category"] = corpAction;
	}
	if (form_name.indexOf("price") > 0){
		inputs["category"] = price;
	}
	if (form_name.indexOf("fxRate") > 0){
		inputs["category"] = fxRate;
	}
	var result = {};
	result.forms = ffs;
	result.sets = sets;
	result.inputs = inputs;
	result.func = func;
	return result;
}

function drawVerifyForm(target_id, dataFor, form_data, num_in_line, disabled){
	$form = $("<form class='form-horizontal' id='" + dataFor + "'></form>");
	$checkboxes = $("<div id='checkboxes'></div>");
	var forms = form_data.forms;
	var sets = form_data.sets;
	var inputs = form_data.inputs;
	var func = form_data.func;
	
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
			var tt = forms[i].type;
			var dom_id = forms[i].cat;
			if (tt == "input" || tt == "date" || tt == "auto") {
				$form_group.append("<label for='" + dom_id + "' class='col-md-1 control-label'>" + forms[i].label + "</label>");
				var $input = genInput(dom_id, dataFor, forms[i].value, dis, ed);
				$div.append($input);
			} 
			else if (tt == "pwd"){
				$form_group.append("<label for='" + dom_id + "' class='col-md-1 control-label'>" + forms[i].label + "</label>");
				var $input = genPwd(dom_id, dataFor, forms[i].value, dis, ed);
				$div.append($input);
			}
			else if (tt == "select") {
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
					var inpss = {};
					if (inps){
						$.each(inps, function(k,v){
							if (typeof(v) == "string"){
								if(inputs[v]){
									inpss[k] = inputs[v];
								}
							}
							else{
								inpss[k] = inps[k];
							}
						});
						options = fetch(getURL(forms[i].options.url), inpss);
					}
					else{
						options = {};
					}
					
				}
				else{
					options = {};
				}
				
				$select = genSelect(dom_id, dataFor, options, forms[i].value, dis, ed);
				$div.append($select);
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
					options = fetch(getURL(forms[i].options.url), inputs[forms[i].cat]);
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
	$("#" + target_id).append("</br>")
	$("#" + target_id).empty();
	$("#" + target_id).append($form);
	if (func.length > 0){
		var funcBody = "";
		$.each(func, function(i,str){
			funcBody = funcBody + str + ";"; 
		});
		var formFunc = function(d){eval(funcBody)};
		formFunc(inputs);
	}
}

function getParameter(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

function logInfo(address, inputs, params){
//	console.log(address + ":{");
//	console.log('	header:"' + address + '",');
//	var inps = JSON.parse(JSON.stringify(inputs));
//	if (typeof(inps) === "object"){
//		$.each(inps, function(k, v){
//			inps[k] = k;
//		});
//		if(inps.params){
//			inps.params = params;
//		}
//	}
//	
//	else{
//		inps = {};
//	}
//	console.log("	inputs:" + JSON.stringify(inps));
//	console.log("},");
}

	

