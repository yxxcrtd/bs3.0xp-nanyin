//产生接口的基本配置界面
function drawInfo(target_id, exportID){
	var e = $.extend(true, {}, editForm);
	e.source.address = "exportDetail";
	e.source.inputs = {id:exportID};
	e.form.name = "export_edit";
	e.form.func = function(data){
		$("#exportName").text(translate["export"] + "："  + data.data[0]);
	}
	$("#info_display").empty();
	e.event.update.source.address = "exportUpdate";
	e.event.update.source.inputs = {id:exportID};
	e.event.create.on = false;

	genEditForm(target_id, "export", e.source, e.form, e.event, 3, "alertAll", true);
	$("a[href='#info_display']").tab("show");
	return false;
}

//产生接口的基础列设置界面
function drawBasic(target_id, exportID){
	var f = $.extend(true, {}, tableForm);
	var sources = fetch(getURL("exportDatasource"), {id:exportID});
	
	f.source.address = "exportBasicColDetail";
	f.source.inputs = {id:exportID};
	f.form.name = "export_basicCol_table";
	f.form.func = function(data){
		$.each(data.data, function(i, v){
			var id = v[0];
			var source = v[2]
			updateSelectOptions($("#basicExportCol_" + id + "_source"), sources);
			$("#basicExportCol_" + id + "_source").val(source);
		});
		$("#basicExportCol_add").text("增加" + translate["basicExportCol"]);
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
	f.event.add.form.name = "export_basicCol_add";
	f.event.add.source.func = function(id, dataFor){
		updateSelectOptions($("#basicExportCol_" + id + "_source"), sources);
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
		$("#advanceCol_add").text("增加" + translate["advanceCol"]);
	};
	f.event.del.source.address = "exportAdvanceColRemove";
	f.event.del.source.inputs = {pid:exportID};
	f.event.del.source.func = function(id, dataFor){
		updateCols(exportID);
	}
	
	f.event.update.source.address = "exportAdvanceColUpdate";
	f.event.update.source.inputs = {pid:exportID};
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
		$("#filterCol_add").text("增加" + translate["filterCol"]);
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
		$("#templateCol_add").text("增加" + translate["templateCol"]);
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