function manageManualEntry(target_id, title_id, alert_id, port_id, port_options, start_date, end_date){
	var $title = $("<div id='vlist_title'></div>");
	var $target = $("#" + target_id);
	var accts = fetch(getURL("acctList"), {portID: port_id});
	$("#" + title_id).text("管理 手工分录");
	$target.append($title);
	
	drawFilterForm("vlist_title", "voucherList", "manual_list_filter", {}, true);
	setDatePick();
	updateSelectOptions($("#voucherList_port_filter"), port_options);
	$("#voucherList_port_filter").val(port_id);
	$("#voucherList_start_filter").val(start_date);
	$("#voucherList_end_filter").val(end_date);
	$("#voucherList_filter").append("&nbsp;");
	
	fetchManualEntry("voucherList_list", "manual_title", "manual_alert", port_id, port_options, start_date, end_date);
	
	$("#voucherList_create_btn").on("click", function(){
		var port = $("#voucherList_port_filter").val();
		var start = $("#voucherList_start_filter").val()
		var end = $("#voucherList_end_filter").val();
		genManualEntry("manualCreate_detail", "manualCreate_title", "manualCreate_alert", port, port_options, start);
		return false;
	});
	
	$("#voucherList_search_btn").on("click", function(){
		var port = $("#voucherList_port_filter").val();
		var start = $("#voucherList_start_filter").val()
		var end = $("#voucherList_end_filter").val();
		fetchManualEntry("voucherList_list", "manual_title", "manual_alert", port, port_options, start, end);
		return false;
	});
}

function fetchManualEntry(target_id, title_id, alert_id, port_id, port_options, start_date, end_date){
	 var inputs = {voucher: MANUAL, 
			  	   currency: ALL, 
			  	   secid: "",
			  	   acctNum: "",
			  	   portfolio: port_id, 
			  	   start: start_date, 
			  	   end: end_date};

	var t = $.extend(true, {}, checkTable);
	t.source.address = "voucherList";
	t.source.inputs = inputs;
	
	t.event.add.on = false;
	t.event.del.on = true;
	t.event.detail.on = false;
	t.event.del.source.address = "voucherRemove";
	t.event.detail.source.address = "voucherDetail";
	t.event.detail.source.func = function(id, dataFor, data){
		showManualDetail("manualEdit_detail", "manualEdit", id, data, port_id, port_options);
	};
	genTableWithCheck("voucherList_list", "voucherList", t.source, t.event, ["detail", "del"], "manual_alert", false);
	return false;
}

function showManualDetail(target_id, modal, voucher_id, data, port_id, port_options){
	var modal_id = modal + "_modal";
	var title_id = modal + "_title";
	var alert_id = modal + "_alert";
	
	//Change data.data to table form's format
	var dt = [];
	$.each(data.data, function(i, dd){
		dt.push([]);
		dt[i].push("TEM" + i);
		var debitAmount = dd[5];
		var localAmount;
		var funcAmount;
		
		if (debitAmount > 0){
			dt[i].push(DEBIT);
			funcAmount = dd[5];
			localAmount = dd[8];
		}
		else{
			dt[i].push(CREDIT);
			funcAmount = dd[6];
			localAmount = dd[9];
		}
		dt[i].push(dd[0] + "|" + dd[1]);
		//TODO: get currency code from currency name
		dt[i].push(1);
		dt[i].push(funcAmount);
		//TODO: get currency code from currency name
		dt[i].push(1);
		dt[i].push(localAmount);
		dt[i].push(dd[2]);
		dt[i].push(dd[11]);
		dt[i].push(dd[12]);
	});
	
	$("#" + target_id).empty();
	var $title = $("<div id='ve_title'></div>");
	var $target = $("#" + target_id);
	var accts = fetch(getURL("acctList"), {portID: port_id});
	$("#" + title_id).text("更新 手工分录");
	
	$("#voucherEdit_create").remove();
	$target.append($title);
	drawFilterForm("ve_title", "voucherEdit", "manual_edit_filter", {}, true);
	$("#voucherEdit_filter").append("&nbsp;&nbsp;");
	updateSelectOptions($("#voucherEdit_port_filter"), port_options);
	$("#voucherEdit_port_filter").val(port_id);
	$("#voucherEdit_port_filter").prop("disabled", true);

	var f = $.extend(true, {}, tableForm);	
	f.source.on = false;
	f.source.value = dt;
	
	f.form.name = "voucher_add";
	f.form.func = function(data){
		var $addBtn = $("#voucherEdit_add"); 
		$addBtn.text("增加凭证栏目");
		$addBtn.appendTo($("#voucherEdit_filter"));
		
		var $createBtn = $("<button id='voucherEdit_create' class='btn btn-primary'>更新凭证</button>");
		$createBtn.insertBefore($("#" + modal_id + " div.modal-footer button"));
		
		$("#form_voucherEdit_div .form-control").prop("disabled", true);
		$("[data-for='voucherEdit_delete']").prop("disabled", true);
		$("#voucherEdit_add").prop("disabled", true);
		
		$createBtn.off("click");
		$createBtn.on("click", function(){
			if ($createBtn.text() == "更新凭证"){
				$("#form_voucherEdit_div .form-control").prop("disabled", false);
				$("[data-for='voucherEdit_delete']").prop("disabled", false);
				$("#voucherEdit_add").prop("disabled", false);
				$("#voucherEdit_create").text("保存凭证");
				$("#voucherEdit_create").attr("class", "btn btn-success")
			}
			else{
				if (confirm("是否确认保存？")){
					var voucher = [];
					var status = true;
					var fms = $("#form_voucherEdit_div form");
					$.each(fms, function(i, f){
						if ($(f).valid()){
							var id = $(f).attr("id").split("_")[1];
							var dataFor = "voucherEdit";
							var vl = [];
							$.each($("[data-for='" + dataFor + "_" + id + "']"), function(index, value){
			     				if ($(value).prop("type") == "checkbox"){
			     					vl.push($(value).is(":checked"));
			     				}
			     				else {
			     					vl.push($(value).val());
			     				}
			     				
			     			});
							voucher.push(vl);
						}
						else {
							status = false;
						}
					});
					if (status){
						invoke(getURL("voucherUpdate"), {id:voucher_id, voucher:JSON.stringify(voucher)}, function(data){
							$("#form_voucherEdit_div .form-control").prop("disabled", true);
							$("[data-for='voucherEdit_delete']").prop("disabled", true);
							$("#voucherEdit_add").prop("disabled", true);
							$("#voucherEdit_create").text("更新凭证");
							$("#voucherEdit_create").attr("class", "btn btn-primary");
							$("#" + alert_id).empty();
				         	$("#" + alert_id).append(genAlert("success", "编号：" + data + " " + translate["voucher"] + "修改成功!"));
						}, alert_id);
					}
				}
			}
		});
	};
	
	f.event.update.on = false;
	f.event.add.form.name = "voucher_add";
	f.event.add.source.address = "voucherCreate";
	f.event.add.source.func = function(id, dataFor){
		$("#" + dataFor + "_" + id + "_funcCurrency").val(fetch(getURL("portCurrency"), {id:port_id}))
		$("#" + dataFor + "_" + id + "_acct").typeahead({source:accts});  
	};
	genTableForm(target_id, "voucherEdit", f.source, f.form, f.event, alert_id, true);
	$("#" + modal_id).modal();
}

function genManualEntry(target_id, title_id, alert_id, port_id, port_options, date){
	$("#manualCreate_detail").empty();
	var $title = $("<div id='vc_title'></div>");
	var $target = $("#" + target_id);
	var accts = fetch(getURL("acctList"), {portID: port_id});
	$("#" + title_id).text("创建 手工分录");
	
	$("#voucherDetail_create").remove();
	$target.append($title);
	drawFilterForm("vc_title", "voucherCreate", "manual_filter", {}, true);
	setDatePick();
	$("#voucherCreate_filter").append("&nbsp;&nbsp;");
	updateSelectOptions($("#voucherCreate_port_filter"), port_options);
	$("#voucherCreate_port_filter").val(port_id);
	$("#voucherCreate_date_filter").val(date);
	$("#voucherCreate_port_filter").prop("disabled", true);
	//$("#voucherCreate_date_filter").prop("disabled", true);
	
	var f = $.extend(true, {}, tableForm);	
	f.source.on = false;
	
	f.form.name = "voucher_table";
	f.form.func = function(data){
		var $addBtn = $("#voucherDetail_add"); 
		$addBtn.text("增加凭证栏目");
		$addBtn.appendTo($("#voucherCreate_filter"));
		
		var $createBtn = $("<button id='voucherDetail_create' class='btn btn-primary'>保存凭证</button>");
		$createBtn.insertBefore($("#manualCreate_modal div.modal-footer button"));
		
		$createBtn.on("click", function(){
			var port = $("#voucherCreate_port_filter").val();
			if (confirm("是否确认保存？")){
				var voucher = [];
				var status = true;
				var fms = $("#form_voucherDetail_div form");
				$.each(fms, function(i, f){
					if ($(f).valid()){
						var id = $(f).attr("id").split("_")[1];
						var dataFor = "voucherDetail";
						var vl = [];
						$.each($("[data-for='" + dataFor + "_" + id + "']"), function(index, value){
		     				if ($(value).prop("type") == "checkbox"){
		     					vl.push($(value).is(":checked"));
		     				}
		     				else {
		     					vl.push($(value).val());
		     				}
		     				
		     			});
						voucher.push(vl);
					}
					else {
						status = false;
					}
				});
				if (status){
					var dd = $("#voucherCreate_date_filter").val();
					if (typeof(dd) == "undefined" || dd ==""){
						$("#" + alert_id).empty();
			         	$("#" + alert_id).append(genAlert("fail", "请指定凭证时间后再保存凭证！"));
					}
					else{
						invoke(getURL("voucherCreate"), {portID:port_id, date:dd, voucher:JSON.stringify(voucher)}, function(data){
							$("#form_voucherDetail_div .form-control").prop("disabled", true);
							$("[data-for='voucherDetail_delete']").prop("disabled", true);
							$("#voucherDetail_add").prop("disabled", true);
							$("#voucherDetail_create").prop("disabled", true);
							$("#voucherCreate_date_filter").prop("disabled", true);
							$("#" + alert_id).empty();
				         	$("#" + alert_id).append(genAlert("success", "编号：" + data + " " + translate["voucher"] + "创建成功!"));
						}, alert_id);
					}
				}
			}
		});
	};
	
	f.event.update.on = false;
	f.event.add.form.name = "voucher_add";
	f.event.add.source.address = "voucherCreate";
	f.event.add.source.func = function(id, dataFor){
		$("#" + dataFor + "_" + id + "_funcCurrency").val(fetch(getURL("portCurrency"), {id:port_id}))
		$("#" + dataFor + "_" + id + "_acct").typeahead({source:accts});  
	};
	genTableForm(target_id, "voucherDetail", f.source, f.form, f.event, alert_id, true);
	$("#manualCreate_modal").modal();
}