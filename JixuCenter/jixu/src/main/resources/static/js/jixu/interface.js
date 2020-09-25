//产生接口的基本配置界面
function drawInfo(target_id, interfaceID){
	var e = $.extend(true, {}, editForm);
	e.source.address = "interfaceDetail";
	e.source.inputs = {id:interfaceID};
	e.form.name = "interface_edit";
	e.form.func = function(data){
		$("#interfaceName").text(translate["interface"] + "："  + data.data[0]);
	}
	$("#info_display").empty();
	e.event.update.source.address = "interfaceUpdate";
	e.event.update.source.inputs = {id:interfaceID};
	e.event.create.on = false;

	genEditForm(target_id, "interface", e.source, e.form, e.event, 3, "alertAll", true);
	$("a[href='#info_display']").tab("show");
	return false;
}

//产生接口的基础列设置界面
function drawBasic(target_id, interfaceID){
	var f = $.extend(true, {}, tableForm);
	var validators = fetch(getURL("interfaceValidators"), {});
	
	f.source.address = "interfaceBasicColDetail";
	f.source.inputs = {id:interfaceID};
	f.form.name = "basicCol_table";
	f.form.func = function(data){
		$.each(data.data, function(i, v){
			var id = v[0];
			var validator = v[5];
			updateSelectOptions($("#basicCol_" + id + "_validator"), validators);
			$("#basicCol_" + id + "_validator").val(validator);
		});
		$("#basicCol_add").text("增加" + translate["basicCol"]);
	};
	f.event.del.source.address = "interfaceBasicColRemove";
	f.event.del.source.inputs = {pid:interfaceID};
	f.event.del.source.func = function(id, dataFor){
		updateCols(interfaceID);
	}
	
	f.event.update.source.address = "interfaceBasicColUpdate";
	f.event.update.source.inputs = {pid:interfaceID};
	f.event.update.source.func = function(id, dataFor){
		updateCols(interfaceID);
	}
	f.event.add.source.address = "interfaceBasicColCreate";
	f.event.add.source.inputs = {pid:interfaceID};
	f.event.add.form.name = "basicCol_add";
	f.event.add.source.func = function(id, dataFor){
		updateSelectOptions($("#" + dataFor + "_" + id + "_validator"), validators);
	};
	
	genTableForm(target_id, "basicCol", f.source, f.form, f.event, "alertAll", true);
	return false;
}

function drawAdvance(target_id, interfaceID){
	var f = $.extend(true, {}, tableForm);
	var funcs = fetch(getURL("interfaceFunctions"), {});
	var funcMap = fetch(getURL("interfaceFunctionMap"), {});
	
	f.source.address = "interfaceAdvanceColDetail";
	f.source.inputs = {id:interfaceID};
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
	f.event.del.source.address = "interfaceAdvanceColRemove";
	f.event.del.source.inputs = {pid:interfaceID};
	f.event.del.source.func = function(id, dataFor){
		updateCols(interfaceID);
	}
	
	f.event.update.source.address = "interfaceAdvanceColUpdate";
	f.event.update.source.inputs = {pid:interfaceID};
	f.event.update.full = true;
	f.event.update.source.func = function(id, dataFor){
		updateCols(interfaceID);
	}
	
	f.event.add.source.address = "interfaceAdvanceColCreate";
	f.event.add.source.inputs = {pid:interfaceID};
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

function drawFilter(target_id, interfaceID){
	var f = $.extend(true, {}, tableForm);
	var filterOps = fetch(getURL("interfaceFilterOperators"), {});
	var logicOps = fetch(getURL("interfaceLogicOperators"), {}); 
	var cols = fetch(getURL("interfaceCols"), {id:interfaceID});
	
	f.source.address = "interfaceFilterColDetail";
	f.source.inputs = {id:interfaceID};
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
	f.event.del.source.address = "interfaceFilterColRemove";
	f.event.del.source.inputs = {pid:interfaceID};
	
	f.event.update.source.address = "interfaceFilterColUpdate";
	f.event.update.source.inputs = {pid:interfaceID};
	
	f.event.add.source.address = "interfaceFilterColCreate";
	f.event.add.source.inputs = {pid:interfaceID};
	f.event.add.form.name = "filterCol_add";
	f.event.add.source.func = function(id, dataFor){
		updateSelectOptions($("#" + dataFor + "_" + id + "_opt"), filterOps);
		updateSelectOptions($("#" + dataFor + "_" + id + "_logic"), logicOps);
		var new_cols = fetch(getURL("interfaceCols"), {id:interfaceID});
		updateSelectOptions($("#" + dataFor + "_" + id + "_col"), new_cols);
	};
	
	genTableForm(target_id, "filterCol", f.source, f.form, f.event, "alertAll", true);
	return false;
}

function drawMap(target_id, interfaceID){
	var f = $.extend(true, {}, tableForm);
	var cols = fetch(getURL("interfaceCols"), {id:interfaceID});
	
	f.source.address = "interfaceMapColDetail";
	f.source.inputs = {id:interfaceID};
	f.form.name = "mapCol_table";
	f.form.func = function(data){
		$.each(data.data, function(i, v){
			var id = v[0];
			updateSelectOptions($("#mapCol_" + id + "_col"), cols);
			$("#mapCol_" + id + "_col").val(v[1]);
		});
		$("#mapCol_add").text("增加" + translate["mapCol"]);
	};
	
	f.event.del.source.address = "interfaceMapColRemove";
	f.event.del.source.inputs = {pid:interfaceID};
	
	f.event.update.source.address = "interfaceMapColUpdate";
	f.event.update.source.inputs = {pid:interfaceID};
	f.event.update.source.func = function(id, dataFor){
		var new_cols = fetch(getURL("interfaceCols"), {id:interfaceID});
		var $col = $("#" + dataFor + "_" + id + "_col");
		var value = $col.val();
		updateSelectOptions($col, new_cols);
		$col.val(value);
	};
	
	f.event.add.source.address = "interfaceMapColCreate";
	f.event.add.source.inputs = {pid:interfaceID};
	f.event.add.form.name = "mapCol_add";
	f.event.add.source.func = function(id, dataFor){
		var new_cols = fetch(getURL("interfaceCols"), {id:interfaceID});
		var $col = $("#" + dataFor + "_" + id + "_col");
		var value = $col.val();
		updateSelectOptions($col, new_cols);
		$col.val(value);
	};
	
	genTableForm(target_id, "mapCol", f.source, f.form, f.event, "alertAll", true);
	return false;
}

function drawMessage(target_id, interfaceID){
	$("#" + target_id).append("<div id='messageFilter'></div>");
	$("#" + target_id).append("<div id='messageKeys'></div>");
	
	drawFilterForm("messageFilter", "messageKey", "messageKey_filter", {}, false);
	$("<textarea id='messageText' class='form-control' rows='5'></textarea>").insertAfter($("#messageKey_filter"));
	$("<br/>").insertAfter($("#messageKey_filter"));
	
	//当选择message type时，自动获取消息模板
	var $msg = $("#messageText");
	var msgTXT = fetch(getURL("interfaceMessage"), {id:interfaceID});
	if (msgTXT){
		$msg.val(msgTXT);
		$("#messageKey_update_btn").attr("class", "btn btn-primary");
		$("#messageKey_update_btn").text("更新模板");
		$msg.prop("disabled", true);
	}
	else {
		$("#messageKey_update_btn").attr("class", "btn btn-success");
		$("#messageKey_update_btn").text("保存模板");
		$msg.prop("disabled", false);
	}
	
	$("#messageKey_create_btn").on("click", function(){
		createNewMsgs("messageKeys", interfaceID);
	});
	
	$("#messageKey_update_btn").on("click", function(){
		if ($(this).text() == "更新模板"){
			$msg.prop("disabled", false);
			$(this).attr("class", "btn btn-success");
         	$(this).text("保存模板");
		}
		else{
			if (confirm("是否确认更新?")){
				invoke(getURL("interfaceMessageUpdate"), {id:interfaceID, msg:$msg.val()}, function(){
					$msg.prop("disabled", true);
					$("#messageKey_update_btn").attr("class", "btn btn-primary");
					$("#messageKey_update_btn").text("更新模板");
		         	$("#alertAll").empty();
		         	$("#alertAll").append(genAlert("success", "数据接口消息模板更新成功!"));
				},"alertAll")
			}
		}
	});
	
	//如果有消息配置存在则将消息配置显示
	var messages = fetch(getURL("interfaceMsgColDetail"), {id:interfaceID});
	drawMessageKeyTable(interfaceID, messages.data);
	return false;
}

function drawMessageKeyTable(interfaceID, data){
	var cols = fetch(getURL("interfaceCols"), {id:interfaceID});
	var f = $.extend(true, {}, tableForm);	
	f.source.on = false;
	f.source.value = data;
	f.form.name = "msgCol_table";
	f.form.func = function(data){
		$.each(data, function(i, v){
			var id = v[0];
			updateSelectOptions($("#msgCol_" + id + "_col"), cols);
			$("#msgCol_" + id + "_col").val(v[2]);
			$("#msgCol_" + id + "_key").prop("disabled", true);
		});
		if (data.length <= 0){
			createNewMsgs(messageKeys, interfaceID)
		}
		$("#add_msgCol_div").remove();
	};
	
	f.event.del.source.address = "interfaceMsgColRemove";
	f.event.del.source.inputs = {pid:interfaceID};
	
	f.event.update.source.address = "interfaceMsgColUpdate";
	f.event.update.source.inputs = {pid:interfaceID};
	f.event.update.source.func = function(id, dataFor){
		var new_cols = fetch(getURL("interfaceCols"), {id:interfaceID});
		var $col = $("#" + dataFor + "_" + id + "_col");
		var $key = $("#" + dataFor + "_" + id + "_key");
		var value = $col.val();
		updateSelectOptions($col, new_cols);
		$col.val(value);
		$key.prop("disabled", true);
	};
	
	f.event.add.source.address = "interfaceMsgColCreate";
	f.event.add.source.inputs = {pid:interfaceID};
	f.event.add.form.name = "msgCol_add";
	f.event.add.source.func = function(id, dataFor){
		var new_cols = fetch(getURL("interfaceCols"), {id:interfaceID});
		var $col = $("#" + dataFor + "_" + id + "_col");
		var $key = $("#" + dataFor + "_" + id + "_key");
		var value = $col.val();
		updateSelectOptions($col, new_cols);
		$col.val(value);
		$key.prop("disabled", true);
	};
	$("#messageKeys").empty();
	genTableForm("messageKeys", "msgCol", f.source, f.form, f.event, "alertAll", true);
}

function createNewMsgs(target_id, interfaceID){
	var keywords = fetch(getURL("interfaceMsgKeyWords"), {id:interfaceID});
	var currkeys = [];
	var dataFor = "msgCol";
	var cols = fetch(getURL("interfaceCols"), {id:interfaceID});
	$.each($("#form_msgCol_div form"),function(i,f){
		var id = $(f).attr("id").split("_")[1];
		currkeys.push($("#msgCol_" + id + "_key").val());
	});
	
	$.each(keywords, function(i, word){
		if (currkeys.indexOf(word) < 0){
			var newid = "TEM" + new Date().valueOf();
			addTableFormTr("form_msgCol_div" , newid, dataFor, "msgCol_add", {}, false);
			$("#msgCol_" + newid + "_key").val(word);
			$("#msgCol_" + newid + "_key").prop("disabled", true);
			updateSelectOptions($("#msgCol_" + newid + "_col"), cols);
		}
	});
}

function updateCols(interfaceID){
	var cols = fetch(getURL("interfaceCols"), {id:interfaceID});
	
	//update active message cols
	var selects = $("tr[data-for='msgCol_tr'] select");
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
	
	//update active map cols
	var selects = $("tr[data-for='mapCol_tr'] select");
	$.each(selects, function(i, s){
		var cat = $(s).attr("id").split("_")[2];
		if ((! $(s).prop("disabled")) && cat == "col"){
			updateSelectOptions($(s), cols);
		}
	});
	
}

function executeInterface(target_id, title_id, alert_id, ifDetail, interfaceID){
	$("#" + target_id).empty();
	$("#" + title_id).text("接口配置执行");
	var uploadURL = "uploadImportData";
	var importURL = "importData";
	
	if(ifDetail){
		$("#" + title_id).text("接口配置执行(可查看详细日志)");
		uploadURL = "uploadImportDataDebug";
		importURL = "importDataDebug";
	}
	
	drawFilterForm(target_id, "execute", "interface_execute_filter", {}, true);
	$("#" + target_id).append("<div id='result'></div>");
	//var interfaceID = $("#interfaceID").text(); 
	$("#execute_import_filter").fileupload({
		url:getURL(uploadURL),
		dataType:'json',
		formData:{id:interfaceID, 
				  port: -1,
				  workflowStep: price,
				  date:$("#execute_date_filter").val()},
		done: function (e, data){
			$("#result").empty();
			$("#spinner").hide();
			if(data.result.issue){
				$("#" + alert_id).empty();
				$("#" + alert_id).append(genAlert(data.result.issue.level, data.result.issue.detail));
			}
			else{
				drawTable("result", data.result);
			}
		},
		progressall: function(e, data){
			$("#spinner").show();
			$("#result").empty();
			$("#result").append("<h4>数据导入中，请稍后......</h4>");
		}
	});
	
	$("#execute_import_btn").off("click");
	$("#execute_import_btn").on("click", function(){
		$("#result").empty();
		if (confirm("是否需要导入?")){
			$("#result").append("<h4>数据导入中，请稍后......</h4>");
			invoke(getURL(importURL), {id:interfaceID, 
										  port:-1, 
										  workflowStep:price,
										  date:$("#execute_date_filter").val()}, function(data){
				$("#result").empty();
				drawTable("result", data);
			}, alert_id);
		}
		else{
			$("#result").empty();
		}
	});
	return false;
}