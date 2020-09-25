function marketCreate(target_id, tp, alert_id, nav_date, modal){
	$("#" + target_id).empty();
	
	if (tp == "smf"){
		var info = {};
		info.tp = tp;
		info.category = smf;
		info.date = nav_date;
		info.issuetype = $("#smf_select_filter").val();
		//info.type = $("#smf_select_filter").val();
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
		
		genTradeEditForm(target_id, info["tp"], e.source, e.form, e.event, 4, alert_id, false);
	}
	else{
		var e = $.extend(true, {}, editForm);
		e.source.on = false;
		var category;
		if (tp == "price"){
			category = price;
			e.form.func = function(data){
				$("#price_date").val(nav_date);
			}
		}
		else if (tp == "ex"){
			category = fxRate;
			e.form.func = function(data){
				$("#ex_date").val(nav_date);
			}
		}
		e.form.inputs["source"] = {category:category, type:ALL};
		e.form.inputs["security"] = {config:ALL, type:ALL};
		e.form.name = tp + "_add"
		
		e.event.create.source.address = tp + "Create";
		e.event.update.on = false;
		
		genEditForm(target_id, tp, e.source, e.form, e.event, 4, alert_id, false);
	}
	if (modal){
		$("#" + modal + "_title").text(translate[tp] + "创建");
		$("#" + modal + "_modal").modal();
	}
	else{
		$("#" + tp + "_title").text(translate[tp] + "创建");
		$("#" + tp + "_modal").modal();
	}
	
}

function marketDetail(target_id, tp, alert_id){
	var t = $.extend(true, {}, checkTable);
		
	if (tp == "price"){
		t.source.inputs = {config:JSON.stringify([$("#price_resource_filter").val()]),
				  smf:$("#price_smf_filter").val(),
				  start:$("#time_start_filter").val(),
				  end:$("#time_end_filter").val()};
		t.event.detail.form.inputs["source"] = {category:price, type:ALL};
		t.event.detail.form.inputs["security"] = {config:ALL, type:ALL};
		t.event.detail.form.name = tp + "_edit"
		t.event.add.form.name = tp + "_add"
	}
	else if (tp == "ex"){
		t.source.inputs = {config:JSON.stringify([$("#ex_resource_filter").val()]),
				  baseCurrency:$("#ex_base_filter").val(),
				  localCurrency:$("#ex_local_filter").val(),
				  start:$("#time_start_filter").val(),
				  end:$("#time_end_filter").val()};
		t.event.detail.form.inputs["source"] = {category:fxRate, type:ALL};
		t.event.detail.form.name = tp + "_edit"
		t.event.add.form.name = tp + "_add"
	}
	else if (tp == "smf"){
		t.source.inputs = {config:JSON.stringify([$("#smf_resource_filter").val()]),
				  type:$("#smf_select_filter").val(),
				  smf:$("#smf_smf_filter").val(),
				  start:$("#time_start_filter").val(),
				  end:$("#time_end_filter").val()};
		
		t.event.detail.on = false;
		t.event.detail.form.title_id = tp + "_title";
		t.event.detail.source.inputs["issuetype"] = $("#smf_select_filter").val();
		t.event.detail.source.func = function(itemID, tp, data, inputs){
			var ui = {};
			ui.target = "smf_detail";
			ui.title = "smf_title";
			ui.alert = "smf_alert";
			ui.modal = "smf_modal";
			smfDetail(ui, itemID, tp, data, inputs);
		};
	}
	t.source.address = tp;
	t.event.detail.source.address = tp + "Detail";
	
	t.event.add.on = false;
	t.event.del.source.address = tp + "Remove";
	t.event.update.source.address = tp + "Update";
	t.event.create.source.address = tp + "Create";
	$("#" + target_id).empty();
	genTableWithCheck(target_id, tp, t.source, t.event, ["detail", "del"], alert_id, false);
	
}

function smfDetail(ui,itemID, tp, data, inputs){
	var info = {};
	info.issuetype = inputs.issuetype;
	info.category = smf;
	info.tp = tp;
	
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
	f.event.update.source.inputs["id"] = itemID;
	f.event.update.source.address = "smfUpdate";
	
	f.event.create.on = false;
	
    $("#" + ui.target).empty();
	genTradeEditForm(ui.target, info["tp"], f.source, f.form, f.event, 4, ui.alert, true);
	
	$("#" + ui.title).text("编号：" + itemID + "-" + translate[info.tp] + "详情");
	$("#" + ui.modal).modal();
}

function wfMarketDetail(target_id, tp, configs, alert_id){
	var t = $.extend(true, {}, checkTable);
	var resources = [];
	var config = $("#" + tp + "List_resource_filter").val();
	if (config == ALL){
		resources = configs;
	}
	else {
		resources = [config];
	}
	
	if (tp == "smf"){
		t.source.inputs = {config:JSON.stringify(resources),
		  		   type:$("#smfList_select_filter").val(),
		  		   smf:$("#smfList_smf_filter").val(),
		  		   start:NAV_DATE,
		  		   end:NAV_DATE};

		t.event.detail.on = false;
		t.event.detail.form.title_id = tp + "_title";
		t.event.detail.source.inputs["issuetype"] = $("#smfList_select_filter").val();
		t.event.detail.source.func = function(itemID, tp, data, inputs){
			var ui = {};
			ui.target = "content_detail";
			ui.title = "content_title";
			ui.alert = "content_alert";
			ui.modal = "content_modal";
			smfDetail(ui, itemID, tp, data, inputs);
		};
	}
	
	else if (tp == "price"){
		t.source.inputs = {config:JSON.stringify(resources),
				  		   smf:$("#priceList_smf_filter").val(),
				           start:NAV_DATE,
				           end:NAV_DATE};
		t.event.detail.form.inputs["source"] = {category:price, type:ALL};
		t.event.detail.form.inputs["security"] = {config:ALL, type:ALL};
		t.event.detail.form.name = tp + "_edit"
		}
	else if (tp == "ex"){
		t.source.inputs = {config:JSON.stringify(resources),
						   baseCurrency:$("#exList_base_filter").val(),
						   localCurrency:$("#exList_local_filter").val(),
						   start:NAV_DATE,
						   end:NAV_DATE};
		t.event.detail.form.inputs["source"] = {category:fxRate, type:ALL};
		t.event.detail.form.name = tp + "_edit"
	}
	
	t.source.address = tp;
	t.event.detail.source.address = tp + "Detail";
	t.event.detail.form.title_id = "content_title";
	t.event.detail.form.alert_id = "content_alert";
	t.event.detail.form.target_id = "content_detail";
	t.event.detail.form.modal_id = "content_modal";
	
	t.event.add.on = false;
	t.event.del.on = false;
	t.event.create.on = false;
	t.event.update.source.address = tp + "Update";
	
		
	$("#" + target_id).empty();
	genTableWithCheck(target_id, tp, t.source, t.event, ["detail"], alert_id, false);
}
