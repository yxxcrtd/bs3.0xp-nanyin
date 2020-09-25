function drawPortBank(portID){
	var tp = "secAcct";
	//var portID = $("#portID").text();;
	var t = $.extend(true, {}, checkTable);
	t.source.address = "secAcctsInPort";
	t.source.inputs = {pid:portID};
	
	t.event.add.on = false;
	
	t.event.detail.on = false;
	t.event.detail.source.address = "secAcctDetail";
	t.event.detail.source.func = secAcctDetail;
	t.event.detail.form.target_id = "secAcct_detail";
	t.event.detail.form.alert_id = "secAcct_alert";
	
	t.event.del.on = false;
	
	t.event.update.on = false;
	t.event.update.source.func = secAcctUpdate;
	
	$("#" + tp + "_list").empty();
	genTableWithCheck("bank_display", tp, t.source, t.event, ["detail"], "alertAll", false);
	return false;
}

function drawPortExpense(portID){
	//var portID = $("#portID").text();
	var t = $.extend(true, {}, checkTable);
	t.source.address = "portExpenseList";
	t.source.inputs = {pid:portID};
	var gls = fetch(getURL("acctList"), {portID:portID});

	var add_func = function(id, dataFor){
        $("#portExpense_basisAcct").typeahead({source:gls});
		$("#portExpense_drAcct").typeahead({source:gls});
		$("#portExpense_crAcct").typeahead({source:gls});

		updateSelectOptions($("#portExpense_month"), fetch(getURL("expenseMonth"), {freq:$("#portExpense_frequency").val()}));
		
		if ($("#portExpense_prenav").is(":checked")){
			$("#portExpense_priority").prop("disabled", false);
		}
		else{
			$("#portExpense_priority").prop("disabled", true);
		}
		
		$("#portExpense_prenav").on("change", function(e){
			if ($(this).is(':checked')){
				$("#portExpense_priority").prop("disabled", false);
			}
			else{
				$("#portExpense_priority").prop("disabled", true);
			}
		});
		$("#portExpense_frequency").off("change");
		$("#portExpense_frequency").on("change", function(){
			var v = $(this).val();
			updateSelectOptions($("#portExpense_month"), fetch(getURL("expenseMonth"), {freq:v}));
		});
	}
	var detail_func = function(data){
		var gls = fetch(getURL("acctList"), {portID:portID});
		$("#portExpense_drAcct").typeahead({source:gls});
		$("#portExpense_crAcct").typeahead({source:gls});
		updateSelectOptions($("#portExpense_month"), fetch(getURL("expenseMonth"), {freq:$("#portExpense_frequency").val()}));
		
//		if ($("#portExpense_prenav").is(":checked")){
//			$("#portExpense_priority").prop("disabled", false);
//		}
//		else{
//			$("#portExpense_priority").prop("disabled", true);
//		}
		$("#portExpense_prenav").off("change");
		$("#portExpense_prenav").on("change", function(e){
			if ($(this).is(':checked')){
				$("#portExpense_priority").prop("disabled", false);
			}
			else{
				$("#portExpense_priority").prop("disabled", true);
			}
		});
		
		$("#portExpense_month").val(data.data[12]);
		$("#portExpense_frequency").off("change");
		$("#portExpense_frequency").on("change", function(){
			var v = $(this).val();
			updateSelectOptions($("#portExpense_month"), fetch(getURL("expenseMonth"), {freq:v}));
		});
	}
	
	t.event.detail.source.address = "portExpenseDetail";
	t.event.detail.form.func = detail_func;
	t.event.detail.form.name = "portExpense_edit";

	t.event.add.source.on = false;
	t.event.add.source.func = add_func;
	t.event.add.form.name = "portExpense_add";
	t.event.add.form.title_id = "portExpense_title";
	t.event.add.form.alert_id = "portExpense_alert";
	t.event.add.form.target_id = "portExpense_detail";
	t.event.add.form.modal_id = "portExpense_modal";
	
	t.event.del.source.address = "portExpenseRemove";
	t.event.del.source.inputs = {pid:portID};
	
	t.event.update.source.address = "portExpenseUpdate";
	t.event.update.source.inputs = {pid:portID};
	t.event.update.source.func = function(){
		if ($("#portExpense_prenav").is(":checked")){
			$("#portExpense_priority").prop("disabled", false);
		}
		else{
			$("#portExpense_priority").prop("disabled", true);
		}
	};
	
	t.event.create.source.address = "portExpenseCreate";
	t.event.create.source.inputs = {pid:portID};
	
	$("#expense_display").empty();
	genTableWithCheck("expense_display", "portExpense", t.source, t.event, ["detail", "del", "add"], "alertAll", false);
}

function drawPortInterface(portID){
	//var portID = $("#portID").text();
	var f = $.extend(true, {}, tableForm);
	
	var func = function(id, dataFor, data){
		$config = $("#" + dataFor + "_" + id + "_config");
		$type = $("#" + dataFor + "_" + id + "_type");
		$asset = $("#" + dataFor + "_" + id + "_asset");
		
		updateSelectOptions($config, fetch(getURL("importConfigList"), {category: $type.val(), type: ALL}));
		var assets = {0:"全部"};
		if ($type.val() == price || $type.val() == tran || $type.val() == corpAction){
			assets = fetch(getURL("assets"), {});
		}
		updateSelectOptions($config, fetch(getURL("importConfigList"), {category: $type.val(), type: ALL}));
		updateSelectOptions($asset, assets);
		if (data){
			$config.val(data[3]);
			$asset.val(data[2]);
		}
		$type.on("change", function(){
			updateSelectOptions($config, fetch(getURL("importConfigList"), {portfolios: "all", category: $type.val(), type: ALL}));
			var assets = {0:"全部"};
			if ($type.val() == price || $type.val() == tran || $type.val() == corpAction){
				assets = fetch(getURL("assets"), {});
			}
			updateSelectOptions($asset, assets);
		});
	}
	
	f.source.address = "portInterfaceList";
	f.source.inputs = {pid:portID};
	f.form.name = "portInterface_table";
	f.form.func = function(data){
		for (var i =0; i < data.data.length; i++){
			var id = data.data[i][0];
			var dd = data.data[i];
			func (id, "portInterface", dd);
		}
	}
	f.event.update.source.address = "portInterfaceUpdate";
	f.event.update.source.inputs = {pid:portID};
	f.event.update.source.func = func;
	
	f.event.del.source.address = "portInterfaceRemove";
	f.event.del.source.inputs = {pid:portID};
	
	f.event.add.source.address = "portInterfaceCreate";
	f.event.add.source.inputs = {pid:portID};
	f.event.add.source.func = func;
	f.event.add.form.name = "portInterface_add";
	$("#interface_display").empty();
	genTableForm("interface_display", "portInterface", f.source, f.form, f.event, "alertAll", true);
}

function drawPortExport(portID){
	//var portID = $("#portID").text();
	var f = $.extend(true, {}, tableForm);
	
	var func = function(id, dataFor){
		$config = $("#" + dataFor + "_" + id + "_config");
		$type = $("#" + dataFor + "_" + id + "_type");
		$step = $("#" + dataFor + "_" + id + "_step");
			
		updateSelectOptions($config, fetch(getURL("exportConfigList"), {resource:$type.val()}));
		
		$type.on("change", function(){
			updateSelectOptions($config, fetch(getURL("exportConfigList"), {resource: $type.val()}));
		});
	}
	
	f.source.address = "portExportList";
	f.source.inputs = {pid:portID};
	f.form.name = "portExport_table";
	f.form.func = function(data){
		for (var i =0; i < data.data.length; i++){
			var id = data.data[i][0];
			func (id, "portExport");
		}
	}
	
	f.event.update.source.address = "portExportUpdate";
	f.event.update.source.inputs = {pid:portID};
	f.event.update.source.func = func;
	
	f.event.del.source.address = "portExportRemove";
	f.event.del.source.inputs = {pid:portID};
	
	f.event.add.source.address = "portExportCreate";
	f.event.add.source.inputs = {pid:portID};
	f.event.add.source.func = func;
	f.event.add.form.name = "portExport_add";
	$("#export_display").empty();
	genTableForm("export_display", "portExport", f.source, f.form, f.event, "alertAll", true);
}

function drawPort(portID){
	//var portID = $("#portID").text();
	var e = $.extend(true, {}, editForm);
	e.source.address = "portDetail";
	e.source.inputs = {id:portID};
	e.form.name = "port_edit";
	e.form.inputs["mother"] = {client:function(data){return data.data[1];}};
	e.form.inputs["glgroup"] = {coaID:function(data){return data.data[11];}};
	e.form.func = function(data){
		$("#port_coa").on("change", function(){
       		var ops = fetch(getURL("glListInCOA"), {coaID:$(this).val()});
       		updateSelectOptions($("#port_glgroup"), ops);
       	});
		$("#portName").text("基金名称："  + data.data[2]);
	}
	$("#basic_display").empty();
	e.event.update.source.address = "portUpdate";
	e.event.update.source.inputs = {id:portID};
	e.event.create.on = false;
	
	genEditForm("basic_display", "port", e.source, e.form, e.event, 3, "alertAll", true);
	$("a[href='#basic_display']").tab("show");
}

function drawPortAggregation(portID){
	//var portID = $("#portID").text();
	
	var saved = fetch(getURL("subPorts"), {id:portID}, "alertAll");
	var full = fetch(getURL("allPorts"), {id:portID}, alertAll);
	var left = getLeftOps(full, saved);
	$("#aggregate_display").empty();
	
	var id = portID;
	var name = $("#portName").text();
	drawSelections("aggregate_display", "可选组合", "已选组合", "grpLeft", "grpSelected", left, saved, "subPort", id, name, "subPortsUpdate");
}

function drawPortSub(portID){
	//var portID = $("#portID").text();
	drawTradeFilterForm("sub_display", "sub", getForm("port_sub_filter"), {}, true);
	var inputs = {}
	inputs.portID = portID;
	inputs.start = $("#sub_start_filter").val()||"1900-01-01";
	inputs.end = $("#sub_end_filter").val()||"2099-01-01";
	drawPagTable("sub_list", "portSubList", inputs, "", "");
	
	$("#sub_search_btn").on("click", function(){
		var inputs = {}
		inputs.portID = portID;
		inputs.start = $("#sub_start_filter").val()||"1900-01-01";
		inputs.end = $("#sub_end_filter").val()||"2099-01-01";
		
		drawPagTable("sub_list", "portSubList", inputs, "", "");
	})
	
	$(document).on("click", "#sub_list_table td",
				function() {
					$("#sub_list_table tr[choose='true']").css("background-color", "");
					$("#sub_list_table tr[choose='true']").attr("choose",false);

					$(this).parent().css("background-color", "#ccc");
					$(this).parent().attr("choose", true);
					var lot = $($(this).parent().find("td")[1]).text() + "|" + $($(this).parent().find("td")[2]).text() + "|" + $($(this).parent().find("td")[3]).text()  ;
					$("#sub_red_btn").attr("choose", lot);
					$("#sub_payment_btn").attr("choose", lot);
	});
	
	$("#sub_red_btn").on("click", function() {
		var lot_id = $(this).attr("choose");
		if (lot_id) {
			alert(lot_id);
		}	
		else {
			alert("请选择要操作的批次!");
		}
	});
	
	$("#sub_payment_btn").on("click", function() {
		var lot_id = $(this).attr("choose");
		if (lot_id) {
			alert(lot_id);
		}	
		else {
			alert("请选择要操作的批次!");
		}
	});
	
}

function drawPortCustomise(portID){
	//var portID = $("#portID").text();
	var f = $.extend(true, {}, tableForm);
	
	f.source.address = "portCustomiseList";
	f.source.inputs = {pid:portID};
	f.form.name = "portCustom_table";
	
	f.event.update.source.address = "portCustomiseUpdate";
	f.event.update.source.inputs = {pid:portID};
	
	f.event.del.source.address = "portCustomiseRemove";
	f.event.del.source.inputs = {pid:portID};
	
	f.event.add.source.address = "portCustomiseCreate";
	f.event.add.source.inputs = {pid:portID};
	f.event.add.form.name = "portCustom_add";
	
	$("#customise_display").empty();
	genTableForm("customise_display", "portCustomise", f.source, f.form, f.event, "alertAll", true);
}