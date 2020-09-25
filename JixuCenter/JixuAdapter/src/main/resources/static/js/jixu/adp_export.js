//产生接口的基本配置界面
function drawInfo(target_id, exportID){
	var e = $.extend(true, {}, editForm);
	e.source.address = "exportDetail";
	e.source.inputs = {id:exportID};
	e.form.name = "export_edit";
	e.event.update.source.editFunc = function(){
		if ($("#export_autodo").is(":checked")){
			$("#export_autoDateFormat").prop("disabled", false);
			$("#export_fromDay").prop("disabled", false);
			$("#export_autoTime").prop("disabled", false);
			$("#export_autoTime").prop("disabled", false);
			$("#export_moveto").prop("disabled", false);
			$("#export_holiday").prop("disabled", false);
			$("#export_startDate").prop("disabled", false);
			$("#export_startTime").prop("disabled", false);
		}
		else{
			$("#export_autoDateFormat").prop("disabled", true)
			$("#export_fromDay").prop("disabled", true);
			$("#export_autoTime").prop("disabled", true);
			$("#export_autoTime").prop("disabled", true);
			$("#export_moveto").prop("disabled", true);
			$("#export_holiday").prop("disabled", true);
			$("#export_startDate").prop("disabled", true);
			$("#export_startTime").prop("disabled", true);
		}
	}
	
	e.form.func = function(data){
		$("#exportName").text(adp_translate["export"] + ":"  + data.data.name);
		$("#export_resource").hide();
		$("#export_deliverType").prop("disabled", true)
		$("#export_autodo").on("change", function(){
			if ($("#export_autodo").is(":checked")){
				$("#export_autoDateFormat").prop("disabled", false);
				$("#export_fromDay").prop("disabled", false);
				$("#export_autoTime").prop("disabled", false);
				$("#export_autoTime").prop("disabled", false);
				$("#export_moveto").prop("disabled", false);
				$("#export_holiday").prop("disabled", false);
				$("#export_startDate").prop("disabled", false);
				$("#export_startTime").prop("disabled", false);
			}
			else{
				$("#export_autoDateFormat").prop("disabled", true)
				$("#export_fromDay").prop("disabled", true);
				$("#export_autoTime").prop("disabled", true);
				$("#export_autoTime").prop("disabled", true);
				$("#export_moveto").prop("disabled", true);
				$("#export_holiday").prop("disabled", true);
				$("#export_startDate").prop("disabled", true);
				$("#export_startTime").prop("disabled", true);
			}
		});
		if (data.data.deliverType == DB){
			$("#fieldset_db").show();
			$("#fieldset_file").hide();
		}
		else {
			$("#fieldset_db").hide();
			$("#fieldset_file").show();
		}
		$("#export_dbFormat").on("change", function(){
			if ($(this).val() == 0){
				$("#export_dbURL").val("[hostname]:[port]:[dbname]");
			}
			else{
				$("#export_dbURL").val("[hostname]:[port]:[dbname]");
			}
		});
		$("#export_dbTest_btn").on("click", function(){
			var inputs = {
					"dbURL":$("#export_dbURL").val()||"",
					"dbFormat":$("#export_dbFormat").val(),
					"dbUser":$("#export_user").val()||"",
					"dbPwd":$("#export_pwd").val()||""
			};
			invoke(getURL("dbTest"), inputs, function(data){
				if (data == 1){
					alert("连接成功!");
				}
				else{
					alert("连接失败!");
				}
			});
		});
		drawBasic("basic_display", exportID, data.data.deliverType);
		
	}
	$("#info_display").empty();
	e.event.update.source.address = "exportUpdate";
	e.event.update.source.inputs = {id:exportID};
	e.event.create.on = false;

	genEditForm(target_id, "export", e.source, e.form, e.event, 3, "alertAll", true);
	$("a[href='#info_display']").tab("show");
	return false;
}

function drawParam(target_id, exportID){
	var f = $.extend(true, {}, tableForm);
	var funcs = fetch(getURL("interfaceParamFunctions"), {});
	var funcMap = fetch(getURL("interfaceParamFunctionMap"), {});
	
	f.source.address = "interfaceParamColDetail";
	f.source.inputs = {id:exportID};
	f.form.name = "paramCol_table";
	f.form.func = function(data){
		$.each(data.data, function(i, v){
			var id = v[0];
			var func = v[2];
			var $func = $("#paramCol_" + id + "_func");
			updateSelectOptions($func, funcs);
			$func.val(func);
			if (func != 0){
				$func.popover({
					content:funcMap[func],
					trigger:"hover",
					title:funcs[func] + " 用法详情："
				});
			}
			$func.on("change", function(){
				var func = $(this).val();
				$(this).popover();
				if (func != 0){
					var options = {trigger:"hover"};
					$(this).popover(options);
					$(this).data("bs.popover").options.content = funcMap[func];
					$(this).data("bs.popover").options.title = funcs[func] + "用法详情";
					$(this).popover("show");
				}
				
			});
			
		});
		$("#paramCol_add").text("增加" + adp_translate["paramCol"]);
	};
	f.event.del.source.address = "interfaceParamColRemove";
	f.event.del.source.inputs = {pid:exportID};
	
	f.event.update.source.address = "interfaceParamColUpdate";
	f.event.update.source.inputs = {pid:exportID};
	f.event.update.full = false;
	
	f.event.add.source.address = "interfaceParamColCreate";
	f.event.add.source.inputs = {pid:exportID};
	f.event.add.form.name = "paramCol_add";
	f.event.add.source.func = function(id, dataFor){
		var $func = $("#" + dataFor + "_" + id + "_func");
		updateSelectOptions($func, funcs);
		var func = $func.val();
		if (func != 0){
			$func.popover({
				content:funcMap[func],
				trigger:"hover",
				title:funcs[func] + " 用法详情："
			});
		}
		$func.on("change", function(){
			var func = $(this).val();
			if (func != 0){
				var options = {trigger:"hover"};
				$(this).popover(options);
				$(this).data("bs.popover").options.content = funcMap[func];
				$(this).data("bs.popover").options.title = funcs[func] + "用法详情";
				$(this).popover("show");
			}
		});
	};
	f.event.insertUp.source.func = f.event.add.source.func;
	f.event.insertDown.source.func = f.event.add.source.func;
	
	genTableForm(target_id, "paramCol", f.source, f.form, f.event, "alertAll", true);
	return false;
}

//产生接口的基础列设置界面
function drawBasic(target_id, exportID, basicType){
	var f = $.extend(true, {}, tableForm);
	var sources = fetch(getURL("exportAdpDatasource"), {id:exportID});
	var validators = fetch(getURL("interfaceValidators"), {});
	
	
	f.source.address = "exportBasicColDetail";
	f.source.inputs = {id:exportID};
	f.form.name = "basicCol_table";
	f.form.func = function(data){
		$.each(data.data, function(i, v){
			var id = v[0];
			var validator = v[5];
			updateSelectOptions($("#basicExportCol_" + id + "_validator"), validators);
			$("#basicExportCol_" + id + "_validator").val(validator);
		});
		$("#basicExportCol_add").text("增加" + adp_translate["basicExportCol"]);
	};
	f.event.del.source.address = "exportBasicColRemove";
	f.event.del.source.inputs = {pid:exportID};
	f.event.del.source.func = function(id, dataFor){
		updateCols(exportID);
	}
	
	f.event.update.source.address = "exportBasicColUpdate";
	f.event.update.source.inputs = {pid:exportID};
	f.event.update.source.func = function(id, dataFor){
		updateCols(exportID);
	}
	f.event.add.source.address = "exportBasicColCreate";
	f.event.add.source.inputs = {pid:exportID};
	f.event.add.form.name = "basicCol_add";
	
	f.event.add.source.func = function(id, dataFor){
		updateSelectOptions($("#" + dataFor + "_" + id + "_validator"), validators);
	};
	genTableForm(target_id, "basicExportCol", f.source, f.form, f.event, "alertAll", true);
	
	return false;
}

function drawAdvance(target_id, exportID){
	var f = $.extend(true, {}, tableForm);
	var funcs = fetch(getURL("exportFunctions"), {});
	var funcMap = fetch(getURL("exportFunctionMap"), {});
	
	f.source.address = "exportAdvanceColDetail";
	f.source.inputs = {id:exportID};
	f.form.name = "advanceCol_table";
	f.form.func = function(data){
		$.each(data.data, function(i, v){
			var id = v[0];
			var func = v[2];
			var $func = $("#advanceCol_" + id + "_func");
			updateSelectOptions($func, funcs);
			$func.val(func);
			if (func != 0){
				$func.popover({
					content:funcMap[func],
					trigger:"hover",
					title:funcs[func] + " 用法详情："
				});
			}
			$func.on("change", function(){
				var func = $(this).val();
				$(this).popover();
				if (func != 0){
					var options = {trigger:"hover"};
					$(this).popover(options);
					$(this).data("bs.popover").options.content = funcMap[func];
					$(this).data("bs.popover").options.title = funcs[func] + "用法详情";
					$(this).popover("show");
				}
				
			});
			
		});
		$("#advanceCol_add").text("增加" + adp_translate["advanceCol"]);
	};
	f.event.del.source.address = "exportAdvanceColRemove";
	f.event.del.source.inputs = {pid:exportID};
	f.event.del.source.func = function(id, dataFor){
		updateCols(exportID);
	}
	
	f.event.update.source.address = "exportAdvanceColUpdate";
	f.event.update.source.inputs = {pid:exportID};
	f.event.update.full = true;
	f.event.update.source.func = function(id, dataFor){
		updateCols(exportID);
	}
	
	f.event.add.source.address = "exportAdvanceColCreate";
	f.event.add.source.inputs = {pid:exportID};
	f.event.add.form.name = "advanceCol_add";
	f.event.add.source.func = function(id, dataFor){
		var $func = $("#" + dataFor + "_" + id + "_func");
		updateSelectOptions($func, funcs);
		var func = $func.val();
		if (func != 0){
			$func.popover({
				content:funcMap[func],
				trigger:"hover",
				title:funcs[func] + " 用法详情："
			});
		}
		$func.on("change", function(){
			var func = $(this).val();
			if (func != 0){
				var options = {trigger:"hover"};
				$(this).popover(options);
				$(this).data("bs.popover").options.content = funcMap[func];
				$(this).data("bs.popover").options.title = funcs[func] + "用法详情";
				$(this).popover("show");
			}
		});
	};
	f.event.insertUp.source.func = f.event.add.source.func;
	f.event.insertDown.source.func = f.event.add.source.func;
	
	genTableForm(target_id, "advanceCol", f.source, f.form, f.event, "alertAll", true);
	return false;
}

function drawFilter(target_id, exportID){
	var f = $.extend(true, {}, tableForm);
	var filterOps = fetch(getURL("interfaceFilterOperators"), {});
	var logicOps = fetch(getURL("interfaceLogicOperators"), {}); 
	var cols = fetch(getURL("exportCols"), {id:exportID});
	
	f.source.address = "exportFilterColDetail";
	f.source.inputs = {id:exportID};
	f.form.name = "filterCol_table";
	f.form.func = function(data){
		$.each(data.data, function(i, v){
			var id = v[0];
			var opt = v[2];
			var logic = v[4];
			var col = v[1];
			updateSelectOptions($("#filterCol_" + id + "_opt"), filterOps);
			$("#filterCol_" + id + "_opt").val(opt);
			updateSelectOptions($("#filterCol_" + id + "_logic"), logicOps);
			$("#filterCol_" + id + "_logic").val(logic);
			updateSelectOptions($("#filterCol_" + id + "_col"), cols);
			$("#filterCol_" + id + "_col").val(col);
		});
		$("#filterCol_add").text("增加" + adp_translate["filterCol"]);
	};
	f.event.del.source.address = "exportFilterColRemove";
	f.event.del.source.inputs = {pid:exportID};
	
	f.event.update.source.address = "exportFilterColUpdate";
	f.event.update.source.inputs = {pid:exportID};
	
	f.event.add.source.address = "exportFilterColCreate";
	f.event.add.source.inputs = {pid:exportID};
	f.event.add.form.name = "filterCol_add";
	f.event.add.source.func = function(id, dataFor){
		updateSelectOptions($("#" + dataFor + "_" + id + "_opt"), filterOps);
		updateSelectOptions($("#" + dataFor + "_" + id + "_logic"), logicOps);
		var new_cols = fetch(getURL("exportCols"), {id:exportID});
		updateSelectOptions($("#" + dataFor + "_" + id + "_col"), new_cols);
	};
	
	genTableForm(target_id, "filterCol", f.source, f.form, f.event, "alertAll", true);
	return false;
}


function drawTemplate(target_id, exportID){
	var f = $.extend(true, {}, tableForm);
	var cols = fetch(getURL("exportCols"), {id:exportID});
	
	f.source.address = "exportTemplateColDetail";
	f.source.inputs = {id:exportID};
	f.form.name = "templateCol_table";
	f.form.func = function(data){
		$.each(data.data, function(i, v){
			var id = v[0];
			var col = v[3];
			updateSelectOptions($("#templateCol_" + id + "_col"), cols);
			$("#templateCol_" + id + "_col").val(col);
		});
		$("#templateCol_add").text("增加" + adp_translate["templateCol"]);
	};
	f.event.del.source.address = "exportTemplateColRemove";
	f.event.del.source.inputs = {pid:exportID};
	
	f.event.update.source.address = "exportTemplateColUpdate";
	f.event.update.source.inputs = {pid:exportID};
	
	f.event.add.source.address = "exportTemplateColCreate";
	f.event.add.source.inputs = {pid:exportID};
	f.event.add.form.name = "templateCol_add";
	f.event.add.source.func = function(id, dataFor){
		var new_cols = fetch(getURL("exportCols"), {id:exportID});
		updateSelectOptions($("#" + dataFor + "_" + id + "_col"), new_cols);
	};
	
	genTableForm(target_id, "templateCol", f.source, f.form, f.event, "alertAll", true);
	return false;
}

function updateCols(exportID){
	var cols = fetch(getURL("exportCols"), {id:exportID});
	
	//update active message cols
	var selects = $("tr[data-for='templateCol_tr'] select");
	$.each(selects, function(i, s){
		if (! $(s).prop("disabled")){
			updateSelectOptions($(s), cols);
		}
	});
	
	//update active filter cols
	var selects = $("tr[data-for='filterCol_tr'] select");
	$.each(selects, function(i, s){
		var cat = $(s).attr("id").split("_")[2];
		if ((! $(s).prop("disabled")) && cat == "col"){
			updateSelectOptions($(s), cols);
		}
	});
}