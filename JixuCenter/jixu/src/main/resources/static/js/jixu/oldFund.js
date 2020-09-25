var NAV = {};
var TB = {};
var SECPOS = {};
var CASHPOS = {};
var RCPY = {};
var COA = [];
var PORT;
var CLIENT;
var CONFIG;
var BANKACCTS;
var SECACCTS;

//Function for OLD_FUND
function drawNAVConfig(target_id, data){
	var options = {};
	options.target = target_id;
	options.tp = "nav";
	options.form = "nav_process";
	options.value = data;
	options.alert = "alertAll";
	drawConfigEditForm(options);
}

function drawTBFilter(target, dataFor, formName, display, inputs){
	var options = {};
	options.target = "tb_import_filter";
	options.display = display;
	options.inputs = inputs;
	options.url = "importTB";
	options.func = function(data){
		TB = data;
		TB.newAccts = {};
		drawTBConfig("tb_result", TB.info);
		alerts(["alertAll"], "success", "余额表上传，并处理成功!");
	}
	
	drawFilterForm(target, dataFor, formName, {}, false);
	$("#" + target).append("<div id='" + display + "'></div>");
	
	fileUploadConfig(options)
	
}

function drawTBConfig(target_id, data){
	var options = {};
	options.target = target_id;
	options.tp = "tb";
	options.value = data;
	options.form = "tb_table"; 
	
	options.formFunc = function(data){
		$("#tb_add").remove();
		$.each(data, function(i, v){
			var id = v[0];
			var dataFor = "tb";
			$("#" + dataFor + "_" + id + "_notice").css('color', 'red');
			$("#" + dataFor + "_" + id + "_mapAcct").typeahead({source:COA}); 
			
			var $btd = $("#" + id).find("td").last();
			var $bt_del = $("<button class='btn btn-danger' data-for='mapDel'>删除科目</button>");
			$bt_del.attr("id", dataFor + "_" + id + "_mapDel");
			var $bt_add = $("<button data-for='mapAdd'>创建科目</button>");
			$bt_add.attr("id", dataFor + "_" + id + "_mapAdd");
			
			var hasNew = false;
			if (TB.newAccts){
				if (TB.newAccts[id]){
					hasNew = true;
				}
			}
			if (hasNew){
				$bt_add.attr("class", "btn btn-primary");
				$bt_add.prop("add", false);
				$bt_add.text("更新科目");
				
				$bt_del.prop("disabled", false);
				$("#" + dataFor + "_" + id + "_mapAcct").prop("disabled", true);
			}
			else{
				$bt_add.attr("class", "btn btn-success");
				$bt_add.prop("add", true);
				$bt_add.text("创建科目");
				
				$bt_del.prop("disabled", true);
				$("#" + dataFor + "_" + id + "_mapAcct").prop("disabled", false);
			}
			$btd.append($bt_add);
			$btd.append("&nbsp;");
			$btd.append($bt_del);
		});
		
		$("button[data-for='mapDel']").on("click", function(){
			var id = $(this).attr("id").split("_")[1];
			var dataFor = "tb";
			var $mapAdd = $("#" + dataFor + "_" + id + "_mapAdd");
			var $mapDel = $("#" + dataFor + "_" + id + "_mapDel");
			var $mapAcct = $("#" + dataFor + "_" + id + "_mapAcct");
			
			if(confirm("是否确认删除新建的科目?")){
				delete TB.newAccts[id];
				$mapAcct.prop("disabled", false);
				$mapAcct.val("");
				
				$mapDel.prop("disabled", true);
				
				$mapAdd.attr("class", "btn btn-success");
				$mapAdd.prop("add", true);
				$mapAdd.text("创建科目");
			}
			return false;
		});
		
		$("button[data-for='mapAdd']").on("click", function(){
			var id = $(this).attr("id").split("_")[1];
			tbMapDetail(id);
			return false;
		});
	};
	
	options.alert = "alertAll";
	drawConfigTableForm2(options);
}

function tbMapDetail(id){
	var options = {};
	options.target = "tbAcct_detail";
	options.tp = "tbAcct";
	options.form = "tbAcct_add";
	options.label = "新建科目";
	
	var hasNew = ! $("#tbAcct_" + id + "_mapAdd").prop("add");
	
	if (hasNew){
		options.value = TB.newAccts[id];
	}
	else {
		options.value = [];
	}
	
	options.formFunc = function(data){
		$("#tbAcct_closeAcct").typeahead({source:COA});
	};
	
	options.saveFunc = function (e) {
		var result = loadEditForm("tbAcct", id);
		if (result){
			TB.newAccts[id] = result;
			
			var dataFor = "tb";
			var $mapAdd = $("#" + dataFor + "_" + id + "_mapAdd");
			var $mapDel = $("#" + dataFor + "_" + id + "_mapDel");
			var $mapAcct = $("#" + dataFor + "_" + id + "_mapAcct");
			var content = result[1] + "|" + result[2];
			
			$mapAdd.attr("class", "btn btn-primary");
			$mapAdd.prop("add", false);
			$mapAdd.text("更新科目");
			
			$mapDel.prop("disabled", false);
			$mapAcct.val(content);
			$mapAcct.prop("disabled", true);
			
			COA.push(content);
			
			$("#alertAll").empty();
			$("#alertAll").append(genAlert("success", "科目创建成功!"));
			$("#tbAcct_modal").modal('hide');
		}
		else{
			$("#tbAcct_alert").empty();
			$("#tbAcct_alert").append(genAlert("fail", "仍有信息没有填写完整，无法完成!"));
		}
		
		return false;
	}
	
	options.alert = "tbAcct_alert";
	drawConfigEditDetail(options);
}

function drawCashFilter(target, dataFor, formName, display, inputs){
	var options = {};
	options.target = "cashPos_import_filter";
	options.display = display;
	options.inputs = inputs;
	options.url = "importCashPosition";
	options.func = function(data){
		CASHPOS = data;
		drawCashPosConfig("cashPos_result", CASHPOS.info);
		alerts(["alertAll"], "success", "现金头寸上传，并处理成功!");
	}
	
	drawFilterForm(target, dataFor, formName, {}, false);
	$("#" + target).append("<div id='" + display + "'></div>");
	
	fileUploadConfig(options)
	
}

function drawCashPosConfig(target_id, data){
	var options = {};
	options.target = target_id;
	options.tp = "cashPos";
	options.value = data;
	options.form = "cashPos_table"; 
	
	options.formFunc = function(data){
		$("#cashPos_add").remove();
		$.each(data, function(i, v){
			var id = v[0];
			var dataFor = "cashPos";
			
			updateSelectOptions($("#" + dataFor + "_" + id + "_mapBankAcct"), BANKACCTS);
		});
	};
	
	options.alert = "alertAll";
	drawConfigTableForm2(options);
}

function drawSecFilter(target, dataFor, formName, display, inputs){
	var options = {};
	options.target = "secPos_import_filter";
	options.display = display;
	options.inputs = inputs;
	options.url = "importSecPosition";
	options.func = function(data){
		SECPOS = data;
		SECPOS.newSecs = {};
		drawSecPosConfig("secPos_result", SECPOS.info);
		alerts(["alertAll"], "success", "证券头寸上传，并处理成功!");
	}
	
	drawFilterForm(target, dataFor, formName, {}, false);
	$("#" + target).append("<div id='" + display + "'></div>");
	
	fileUploadConfig(options)
}

function drawSecPosConfig(target_id, data){
	var options = {};
	options.target = target_id;
	options.tp = "secPos";
	options.value = data;
	options.form = "secPos_table"; 
	
	options.formFunc = function(data){
		$("#secPos_add").remove();
		$.each(data, function(i, v){
			var id = v[0];
			var dataFor = "secPos";
			$("#" + dataFor + "_" + id + "_notice").css('color', 'red');
			$("#" + dataFor + "_" + id + "_mapSMF").typeahead({source:SMF});
			updateSelectOptions($("#" + dataFor + "_" + id + "_mapSecAcct"), SECACCTS);
			if (v[13] && v[13] != "0"){
				$("#" + dataFor + "_" + id + "_mapSecAcct").val(v[13]);
			}
			else{
				if (Object.keys(SECACCTS).length >0){
					$("#" + dataFor + "_" + id + "_mapSecAcct").val(Object.keys(SECACCTS)[0])
				}
				
			}
			
			var $btd = $("#" + id).find("td").last();
			var $bt_del = $("<button class='btn btn-danger' data-for='mapDel'>删除证券</button>");
			$bt_del.attr("id", dataFor + "_" + id + "_mapDel");
			var $bt_add = $("<button data-for='mapAdd'>新建证券</button>");
			$bt_add.attr("id", dataFor + "_" + id + "_mapAdd");
			
			var hasNew = false;
			if (SECPOS.newSecs){
				if (SECPOS.newSecs[id]){
					hasNew = true;
				}
			}
			if (hasNew){
				$bt_add.attr("class", "btn btn-primary");
				$bt_add.prop("add", false);
				$bt_add.text("更新证券");
				
				$bt_del.prop("disabled", false);
				$("#" + dataFor + "_" + id + "_mapSMF").prop("disabled", true);
			}
			else{
				$bt_add.attr("class", "btn btn-success");
				$bt_add.prop("add", true);
				$bt_add.text("新建证券");
				
				$bt_del.prop("disabled", true);
				$("#" + dataFor + "_" + id + "_mapSMF").prop("disabled", false);
			}
			$btd.append($bt_add);
			$btd.append("&nbsp;");
			$btd.append($bt_del);
		});
		
		$("button[data-for='mapDel']").on("click", function(){
			var id = $(this).attr("id").split("_")[1];
			var dataFor = "secPos";
			var $mapAdd = $("#" + dataFor + "_" + id + "_mapAdd");
			var $mapDel = $("#" + dataFor + "_" + id + "_mapDel");
			var $mapSMF = $("#" + dataFor + "_" + id + "_mapSMF");
			
			if(confirm("是否确认删除新建的证券?")){
				delete SECPOS.newSecs[id];
				$mapSMF.prop("disabled", false);
				$mapSMF.val("");
				
				$mapDel.prop("disabled", true);
				
				$mapAdd.attr("class", "btn btn-success");
				$mapAdd.prop("add", true);
				$mapAdd.text("新建证券");
			}
			return false;
		});
		
		$("button[data-for='mapAdd']").on("click", function(){
			var id = $(this).attr("id").split("_")[1];
			smfMapDetail(id);
			return false;
		});
	};
	
	options.alert = "alertAll";
	drawConfigTableForm2(options);
}

function smfMapDetail(id){
	
	var hasNew = ! $("#secPos_" + id + "_mapAdd").prop("add");
	
	if (hasNew){
		var data = SECPOS.newSecs[id];
		
		var ui = {};
		ui.target = "mapSMF_detail";
		ui.title = "mapSMF_title";
		ui.alert = "mapSMF_alert";
		ui.modal = "mapSMF_modal";
		var inputs = {};
		inputs.issuetype = data["issuetype"];
		var dd = {};
		dd.data = data;
		
		smfDetail(ui, id, "smf", dd, inputs);
		
		$("#smf_update").remove();
		$.each($("#smf [data-for='smf']"), function(index, value){
			if ($(value).prop("editable") == false){
				$(value).prop("disabled", true);
			}
			else {
				$(value).prop("disabled", false);
			}
		});
	}
	else {
		$("#preSMF_title").text("证券主文件创建");
		$("#preSMF_modal").modal();
		$("#smf_create_btn").on("click", function(){
			$("#mapSMF_title").text("证券主文件创建");
			marketCreate("mapSMF_detail", "smf", "mapSMF_alert", getNowFormatDate());
			$("#preSMF_modal").modal("hide");
			$("#mapSMF_modal").modal();
			$("#smf_create").remove();
		});
	}
	
	var save_func = function(){
		var result = loadEditFormToMap("smf", id);
		if (result){
			SECPOS.newSecs[id] = result;
			
			var dataFor = "secPos";
			var $mapAdd = $("#" + dataFor + "_" + id + "_mapAdd");
			var $mapDel = $("#" + dataFor + "_" + id + "_mapDel");
			var $mapSMF = $("#" + dataFor + "_" + id + "_mapSMF");
			var content = result.secid + "|" + result.securityName;
			
			$mapAdd.attr("class", "btn btn-primary");
			$mapAdd.prop("add", false);
			$mapAdd.text("更新证券");
			
			$mapDel.prop("disabled", false);
			$mapSMF.val(content);
			$mapSMF.prop("disabled", true);
			
			SMF.push(content);
			
			$("#alertAll").empty();
			$("#alertAll").append(genAlert("success", "证券创建成功!"));
			$("#mapSMF_modal").modal('hide');
		}
		else{
			$("#mapSMF_alert").empty();
			$("#mapSMF_alert").append(genAlert("fail", "仍有信息没有填写完整，无法完成!"));
		}
		return false;
	}
	
	var $footer = $("#mapSMF_modal .modal-footer");
	$footer.find("button").remove();
	var $btn = $("<button class='btn btn-success'>保存<button>");
	$footer.append($btn);
	$footer.find("button").eq(1).addClass("btn btn-default").text("关闭");
	$btn.on('click', save_func);
	
}

function drawRCPYFilter(target, dataFor, formName, display, inputs){
	var options = {};
	options.target = "rcpy_import_filter";
	options.display = display;
	options.inputs = inputs;
	options.url = "importRCPY";
	options.func = function(data){
		RCPY = data;
		drawRCPYConfig("rcpy_result", RCPY.info);
		alerts(["alertAll"], "success", "应收应付记录上传，并处理成功!");
	}
	
	drawFilterForm(target, dataFor, formName, {}, false);
	$("#" + target).append("<div id='" + display + "'></div>");
	
	fileUploadConfig(options)
	
}

function drawRCPYConfig(target_id, data){
	var options = {};
	options.target = target_id;
	options.tp = "rcpy";
	options.value = data;
	options.form = "rcpy_table"; 
	
	options.formFunc = function(data){
		$("#rcpy_add").remove();
		$.each(data, function(i, v){
			var id = v[0];
			var dataFor = "rcpy";
			$("#" + dataFor + "_" + id + "_rpType").prop("disabled", true);
			$("#" + dataFor + "_" + id + "_mapAcct").typeahead({source:COA});
			updateSelectOptions($("#" + dataFor + "_" + id + "_mapBankAcct"), BANKACCTS);
			$("#" + dataFor + "_" + id + "_mapBankAcct").val(v[8]);
		});
	};
	
	options.alert = "alertAll";
	drawConfigTableForm2(options);
}

