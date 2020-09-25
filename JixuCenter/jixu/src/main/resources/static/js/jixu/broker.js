function secAcctCreate(target_id, brokerID, alert_id){
	var e = $.extend(true, {}, editForm);
	$("#" + target_id).empty();
	e.source.on = false;
	e.source.value = ["", brokerID];
	e.form.name = "secAcct_add";
	e.form.inputs["port"] = {client: ALL};
	e.form.func = function(){
		updateSelectOptions($("#secAcct_gl"), fetch(getURL("glListInPort"), {portID: $("#secAcct_port").val()}));
		$("#secAcct_port").on("change", function(){
			var gls = fetch(getURL("glListInPort"), {portID: $(this).val()});
			updateSelectOptions($("#cash_inflowBankAcct"), gls);
		});
	};
	
	e.event.update.on = false;
	e.event.create.on = false;
	e.event.create.source.func = secAcctSave;
	genEditForm(target_id, "secAcct" , e.source, e.form, e.event, 3, alert_id, false);
	secCashCreate(target_id, alert_id);
	$("#secAcct_modal").modal();
}

function secCashCreate(target_id, alert_id){
	$("#secCash_div").remove();
	var $div = $("<div id='secCash_div'></div>")
	var $title = $("<div class='col-md-1'></div>");
	$div.append($title);
	$div.append("<br><br>");
	$("#secAcct_title").text("创建" + translate["secAcct"]);
	$("#" + target_id).append($div);
	var f = $.extend(true, {}, tableForm);	
	f.source.on = false;
	f.form.name = "secCash_table";
	f.event.update.on = false;
	f.event.add.form.name = "secCash_add";
	f.event.add.source.address = "secCashCreate";
	f.event.add.source.func = function(id, dataFor){
		var $bank = $("#secCash_" + id + "_bank");
		var $bankAcct = $("#secCash_" + id + "_bankAccount");
		updateSelectOptions($bankAcct, fetch(getURL("unMapBankAccountList"), {bank: $bank.val()}));
		$bank.on("change", function(){
			var accounts = fetch(getURL("unMapBankAccountList"), {bank: $(this).val()});
			updateSelectOptions($bankAcct, accounts);
		});
	};
	genTableForm("secCash_div", "secCash", f.source, f.form, f.event, alert_id, true);
	$("#secCash_add").text("关联新的现金账户");
	$("#secCash_add").appendTo($title);
}

function secAcctSave(){
	var create = true;
	if ($("#secAcct").valid()){
		if (confirm("是否确认创建?")){
 			var params = [];
 			var secCash = [];
 			$.each($("[data-for='secAcct']"), function(index, value){
 				if ($(value).prop("type") == "checkbox"){
 					params.push($(value).is(":checked"));
 				}
 				else {
 					params.push($(value).val());
 				}
 			});
 			$.each($("[data-for='secCash_tr']"), function(index, tr){
 				var secCashID = $(tr).attr("id");
 				var secCashList = [];
 				if ($("#secCash_" + secCashID).valid()){
 					$.each($("[data-for='secCash_" + secCashID + "']"), function(index, secCash){
 						secCashList.push($(secCash).val());
 	     			});
 					secCash.push(secCashList);
 				}
 				else{
 					create = false;
 				}
 			});
 			if (create){
 				var address = "secAcctCreate"
	 			invoke(getURL(address),{pid:$("#secAcct_broker").val(), params:JSON.stringify(params), secCash:JSON.stringify(secCash)}, function(data){
	 				var new_id = data;
	            	$("#secAcct_alert").empty();
	            	$("[data-for='secAcct']").prop("disabled", true);
	            	$("[data-for*='secCash']").prop("disabled", true);
	            	$("#secAcct_alert").append(genAlert("success", "编号" + new_id + " " + translate["secAcct"] + "创建成功!"));
	     			$("#secAcct_create").prop("disabled", true);
	     			$("#secAcct_search_btn").trigger("click");
	 			}, "secAcct_alert");
 			}
 			
		}	
	}
}

function secAcctUpdate(id, dataFor){
	if ($("#" + dataFor + "_update").text() == "保存"){
		if ($("#" + dataFor).valid()){
			if (confirm("是否确认保存?")){
				var btn = $(this).button('操作中...')
     			var params = [];
				var expenses = [];
     			$.each($("[data-for='" + dataFor + "']"), function(index, value){
     				if ($(value).prop("type") == "checkbox"){
     					params.push($(value).is(":checked"));
     				}
     				else {
     					params.push($(value).val());
     				}
     			});
     			
     			invoke(getURL("secAcctUpdate"),{id:id, params:JSON.stringify(params)}, function(data){
     				$("#" + dataFor + "_alert").empty();
     				$("#" + dataFor + "_alert").append(genAlert("success", "编号" + id + " " + translate[dataFor] + "保存成功!"));
	                	$("[data-for='" + dataFor + "']").prop("disabled", true);
	                	$("[data-for='expense']").prop("disabled", true);
	                	$("#" + dataFor + "_update").attr("class", "btn btn-primary")
	         			$("#" + dataFor + "_update").text("更新");
     			}, dataFor + "_alert");
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
		$.each($("[data-for='expense']"), function(index, value){
				$(value).prop("disabled", false);
			});
		$("#" + dataFor + "_update").attr("class", "btn btn-success")
		$("#" + dataFor + "_update").text("保存");
	}
}
function secAcctDetail(itemID, tp, data){
		$("#secAcct_title").text(translate[tp] + "详情-编号" + itemID);
        $("#secAcct_detail").empty();
        
        var f = $.extend(true, {}, editForm)
        f.source.on = false;
		f.source.value = data.data;
		f.form.name = "secAcct_edit";
		f.form.func = function(){
			updateSelectOptions($("#secAcct_gl"), fetch(getURL("glListInPort"), {portID: $("#secAcct_port").val()}));
			$("#secAcct_gl").val(data.data[6]);
			$("#secAcct_port").on("change", function(){
				var gls = fetch(getURL("glListInPort"), {portID: $(this).val()});
				updateSelectOptions($("#cash_inflowBankAcct"), gls);
			});
		};
		f.form.inputs["port"] = {client:ALL};
		f.form.inputs["gl"] = {portID:data.data[4]};
		f.event.update.on = false;
		f.event.update.source.inputs["id"] = itemID;
		f.event.update.source.func = secAcctUpdate;
		f.event.update.source.address = "secAcctUpdate";
		f.event.create.on = false;
		genEditForm("secAcct_detail", tp, f.source, f.form, f.event, 3, "secAcct_alert", true);
		secCashDetail(itemID, fetch(getURL("secCashDetail"), {id:itemID}));
		$("#secAcct_modal").modal();
}

function secCashDetail(acctID, cashes){
	$("#secCash_div").remove();
	var $div = $("<div id='secCash_div'></div>")
	var $title = $("<div class='col-md-1'></div>");
	$div.append($title);
	$div.append("<br><br>");
	var dataFor = "secCash";
	
	$("#secAcct_detail").append($div);
	var f = $.extend(true, {}, tableForm);	
	f.source.on = false;
	f.source.value = cashes.data;
	f.form.name = "secCash_table";
	f.form.func = function(data){
		$.each(data, function(i, v){
			var id = v[0];
			var $bank = $("#" + dataFor + "_" + id + "_bank");
			var $bankAcct = $("#" + dataFor + "_" + id + "_bankAccount");
			var $major = $("#" + dataFor + "_" + id + "_major");
			updateSelectOptions($bankAcct, fetch(getURL("unMapBankAccountList"), {bank: $bank.val()}));
			$bankAcct.val(v[2]);
			$major.val(v[3]);
		});
	};
	f.event.del.source.address = "secCashRemove";
	f.event.del.source.inputs = {pid:acctID};
	f.event.update.source.address = "secCashUpdate";
	f.event.update.source.inputs = {pid:acctID};
	//f.event.update.source.func = function(){
	//	$("[data-for='secCash_update']").remove();
	//};
	f.event.add.source.address = "secCashCreate";
	f.event.add.source.inputs = {pid:acctID};
	f.event.add.form.name = "secCashUpdate_add";
	f.event.add.source.func = function(id, dataFor){
		var $bank = $("#" + dataFor + "_" + id + "_bank");
		var $bankAcct = $("#" + dataFor + "_" + id + "_bankAccount");
		updateSelectOptions($bankAcct, fetch(getURL("unMapBankAccountList"), {bank: $bank.val()}));
		$bank.on("change", function(){
			var accounts = fetch(getURL("unMapBankAccountList"), {bank: $(this).val()});
			updateSelectOptions($bankAcct, accounts);
		});
	};
	genTableForm("secCash_div", "secCash", f.source, f.form, f.event, "secAcct_alert", true);
	$("#secCash_add").text("关联新的现金账户");
	$("#secCash_add").appendTo($title);
	
}

function seatNumberDetail(){
	var broker = $("#seatNumber_broker_filter").val();
    var f = $.extend(true, {}, tableForm);
	f.source.address = "seatNumbers";
	f.source.inputs = {broker: broker};
	f.form.name = "seatNumber_table";
	
	f.event.update.source.address = "seatNumberUpdate";
	f.event.update.source.inputs = {broker:broker};
	f.event.del.source.address = "seatNumberRemove";
	f.event.add.source.address = "seatNumberCreate";
	f.event.add.form.name = "seatNumber_add";
	$("#seatNumber_list").empty();
	genTableForm("seatNumber_list", "seatNumber", f.source, f.form, f.event, "alertAll", true, {});
    return false;
}