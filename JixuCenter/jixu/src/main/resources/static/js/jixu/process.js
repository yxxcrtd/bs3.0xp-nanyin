function saveData(){
		var tab = $("div.tab-content").find("div.active").attr("id");
		var result = false;
		var label = "";
		if (tab == "portPane"){
			result = loadEditForm("port");
			label = "投资组合配置";
			if (result){
				PORT.data = result;
				if (COA.legnth == 0){
					COA = fetch(getURL("acctListInCOA"), {coaID:result[10]});
				}
			}
		}
		else if (tab == "interest"){
			result = loadTableForm("form_interest_div");
			label = "银行利息配置";
			if (result){
				$.each(result, function(i, value){
					INTS[value[0]]["info"] = value;
				});
				$.each(INTS, function(k, v){
					if((! INTS[k]) || (! INTS[k].info)){
						delete INTS[k];
					}
				});
				drawBankAcctConfig("bank", BANKACCTS);
			}
		}
		else if(tab == "bank"){
			result = loadTableForm("form_bankAccount_div");
			label = "银行账户配置";
			if (result){
				$.each(result, function(i, value){
					BANKACCTS[value[0]]["info"] = value;
				});
				$.each(BANKACCTS, function(k, v){
					if((! BANKACCTS[k]) || (! BANKACCTS[k].info)){
						delete BANKACCTS[k];
					}
				});
				drawSecAcctConfig("secAcct", SECACCTS);
			}
		}
		
		else if(tab == "secAcct"){
			result = loadTableForm("form_secAcct_div");
			label = "证券账户配置";
			if (result){
				$.each(result, function(i, value){
					SECACCTS[value[0]]["info"] = value;
				});	
				$.each(SECACCTS, function(k, v){
					if((! SECACCTS[k]) || (! SECACCTS[k].info)){
						delete SECACCTS[k];
					}
				});
			}
		}
		
		else if(tab == "expensePane"){
			label = "投资组合费用配置";
			result = true;
		}
		
		else if(tab == "interface"){
			result = loadTableForm("form_portInterface_div");
			label = "接口配置";
			if (result){
				$.each(result, function(i, value){
					INTERFACES[value[0]]["info"] = value;
				});	
				$.each(INTERFACES, function(k, v){
					if((! INTERFACES[k]) || (! INTERFACES[k].info)){
						delete INTERFACES[k];
					}
				});
			}
		}
		else if (tab == "navPane"){
			result = loadEditForm("nav");
			label = "基金净值初始化";
			if (result){
				NAV.info = result;
			}
		}
		else if(tab == "tb"){
			result = loadTableForm2("form_tb_div", "tb");
			label = "余额表初始化";
			TB.info = result.params;
			TB.complete = result.complete;
		}
		else if(tab == "secPos"){
			result = loadTableForm2("form_secPos_div", "secPos");
			label = "证券头寸初始化";
			SECPOS.info = result.params;
			SECPOS.complete = result.complete;
		}
		else if(tab == "rcpy"){
			result = loadTableForm2("form_rcpy_div", "rcpy");
			label = "应收应付记录初始化";
			RCPY.info = result.params;
			RCPY.complete = result.complete
		}
		
		var re = result;
		
		if (!(result instanceof Array) && typeof(result)=="object"){
			re = result.complete;
		}
		if (re){
			$("#alertAll").empty();
			$("#alertAll").append(genAlert("success", label + "成功!"));
		}
		else {
			$("#alertAll").empty();
			$("#alertAll").append(genAlert("fail", "仍有信息没有填写完整，无法完成!"));
		}
}

function tabChangeEvent(){
	$('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
		    saveData();
	});
}

function fileUploadConfig(options){
	var $target = $("#" + options.target);
	var $display = $("#" + options.display);
	
	$target.fileupload({
		url:getURL(options.url),
		dataType:'json',
		formData:options.inputs,
		done: function (e, data){
			$("#spinner").hide();
		},
		add: function (e, data) {
			var fname = data.files[0].name;
            data.context = $("<button class='btn btn-primary'></button>").text('导入文件：' + fname)
                .insertBefore($display)
                .click(function () {
                	if (confirm("上传新文件会丢失原来所有配置，是否确认上传?")){
                		data.context = $("<p id='notice_p'></p>").text('导入中, 请稍后...').replaceAll($(this));
                        var jqXHR = data.submit()
                        .success(function (result, textStatus, jqXHR) {
                        	$("#notice_p").remove();
                        	$("#spinner").hide();
                        	var data = result;
                        	if (typeof(data.issue) == "object"){
                	    		alerts(["alertAll"], data.issue.level, data.issue.detail);
                			}
                        	else{
                        		options.func(result);
                        	}
                        })
                        .error(function (jqXHR, textStatus, errorThrown) {
                        	$("#notice_p").remove();
                        	$("#alertAll").empty();
                        	$("#alertAll").append(genAlert("fail", "数据上传中发生错误!"));
                        	$("#spinner").hide();
                        })
                        .complete(function (result, textStatus, jqXHR) {
                        	$("#notice_p").remove();
                        });
                	}
                });
        },
		progressall: function(e, data){
			$("#spinner").show();
		}
	});
}

function drawConfigEditForm(options){
	var $target = $("#" + options.target);
	$target.empty();
	
	var e = $.extend(true, {}, editForm);
	e.source.on = false;
	e.source.value = options.value;
	e.form.name = options.form;
	e.form.inputs= options.inputs;
	e.form.func = options.formFunc;
	genEditForm(options.target, options.tp, e.source, e.form, e.event, 3, "alertAll", false);
}

function drawConfigTableForm(options){
	var $target = $("#" + options.target);
	$target.empty();
	
	var f = $.extend(true, {}, tableForm);	
	f.source.on = false;
	f.source.value = options.value;
	
	f.form.name = options.form;
	f.form.func = options.formFunc;
	
	f.event.add.form.name = options.form;
	f.event.add.source.address = "dummy";
	f.event.add.source.func = options.addFunc;
	
	f.event.del.source.address = "dummy";
	f.event.del.source.func = options.delFunc;
	$target.append("<br/><br/>")
	genTableForm(options.target, options.tp, f.source, f.form, f.event, options.alert, false);
	
}

function drawConfigTableForm2(options){
	var $target = $("#" + options.target);
	$target.empty();
	
	var f = $.extend(true, {}, tableForm);	
	f.source.on = false;
	f.source.value = options.value;
	
	f.form.name = options.form;
	f.form.func = options.formFunc;
	
	f.event.add.form.name = options.form;
	f.event.add.source.address = "dummy";
	f.event.add.source.func = options.addFunc;
	
	f.event.del.source.address = "dummy";
	f.event.del.source.func = options.delFunc;
	$target.append("<br/><br/>")
	genAdvanceTable(options.target, options.tp, f.source, f.form, f.event, options.alert, false);
	
}

function drawConfigEditDetail(options){
	$("#" + options.tp + "_title").text(options.label);
	
	var $target = $("#" + options.target);
	$target.empty();
	
	var e = $.extend(true, {}, editForm);
	e.source.on = false;
	e.source.value = options.value;
	e.form.name = options.form;
	e.form.inputs= options.inputs;
	e.form.func = options.formFunc;
	genEditForm(options.target, options.tp, e.source, e.form, e.event, 3, options.alert, false);
	
	var $footer = $("#" + options.tp + "_modal .modal-footer");
	$footer.find("button").remove();
	var $btn = $("<button class='btn btn-success'>保存<button>");
	$footer.append($btn);
	$footer.find("button").eq(1).addClass("btn btn-default").text("关闭");
	$btn.on('click', options.saveFunc);
	
	$("#" + options.tp + "_modal").modal();
}

function drawConfigTableDetail(options){
	$("#" + options.tp + "_title").text(options.label);
	$("#" + options.tp + "_detail").empty();
	
	$("#" + options.tp + "_div").remove();
	var $div = $("<div id='" + options.tp + "_div'></div>")
	var $title = $("<div class='col-md-1'></div>");
	$div.append($title);
	$div.append("<br><br>");
	$("#" + options.tp + "_detail").append($div);
	var f = $.extend(true, {}, tableForm);	
	f.source.on = false;
	f.source.value = options.value;
	f.form.name = options.form;
	f.form.func = options.formFunc;
	
	f.event.update.on = false;
	
	f.event.add.form.name = options.form;
	f.event.add.source.func = options.addFunc;
	f.event.add.source.address = "dummy";
	
	f.event.del.source.address = "dummy";
	f.event.del.source.func = options.delFunc;
	
	genTableForm(options.tp + "_div", options.tp, f.source, f.form, f.event, options.alert, false);
	$("#"+ options.tp + "_add").text("增加" + options.label);
	$("#"+ options.tp + "_add").appendTo($title);
	$("#"+ options.tp + "_modal").modal();
	
	var $footer = $("#" + options.tp + "_modal .modal-footer");
	$footer.find("button").remove();
	var $btn = $("<button class='btn btn-success'>保存<button>");
	$footer.append($btn);
	$footer.find("button").eq(1).addClass("btn btn-default").text("关闭");
	$btn.on('click', options.saveFunc);
	return false;
}

function loadTableForm2(target_id, dataFor){
	var complete = true;
	var params = [];
	var result = {};
	$("#form_tb_div").find("tr[data-for='tb_tr']")
	$.each($("#" + target_id).find("tr[data-for='" + dataFor + "_tr']"), function(i, tr){
		var param = [];
		var id = $(tr).attr("id");
		param.push(id);
		$.each($(tr).find("[data-for='" + dataFor + "_" + id + "']"), function(j, inp){
			var content = $(inp).val();
			if (content == ""){
				content = $(inp).text();
			}
			if (content == ""){
				if (typeof($(inp).prop("type")) != "undefined" && $(inp).prop("type") == "text"){
					complete = false;
				}
			}
			param.push(content);
		});
		params.push(param);
	});
	result.complete = complete;
	result.params = params;
	return result;
}

function loadTableForm(target_id){
	var complete = true;
	var params = [];
	$.each($("#" + target_id).find("form"), function(i, form){
		if ($(form).valid()){
			var param = [];
			var id = $(form).attr("id").split("_")[1];
			param.push(id);
			$.each($(form).find(".form-control"), function(j, inp){
				if ($(inp).prop("type") == "checkbox"){
					if ($(inp).is(":checked")){
						param.push(1);
					}
					else{
						param.push(0);
					}
					
				}
				else{
					param.push($(inp).val());
				}
			});
			params.push(param);
		}
		else{
			complete = false;
			return false;
		}
	});
	if(complete){
		return params;
	}
	else{
		return false;
	}
}

function loadEditFormValue(target_id, id, dataFor){
	var $form = $("#" + target_id);
	var params = [];
	if (id){
		params.push(id);
	}
	var rule = ".form-control";
	if(dataFor){
		rule = "[data-for='" + dataFor + "']";
	}
	$.each($form.find(rule), function(i, inp){
		if ($(inp).prop("type") == "checkbox"){
				if ($(inp).is(":checked")){
					params.push("是");
				}
				else{
					params.push("否");
				}
		}
		else if ($(inp).prop("type") == "select-one"){
			var v = $(inp).val();
			$.each($(inp).find("option"), function(j, op){
				if (v == $(op).attr("value")){
					params.push($(op).text());
					return false;
				}
			});
		}
		else{
			params.push($(inp).val());
		}
	});
	return params;
}

function loadEditForm(target_id, id, dataFor){
	var $form = $("#" + target_id);
	var params = [];
	if ($form.valid()){
		if (id){
			params.push(id);
		}
		var rule = ".form-control";
		if(dataFor){
			rule = "[data-for='" + dataFor + "']";
		}
		$.each($form.find(rule), function(i, inp){
			if ($(inp).prop("type") == "checkbox"){
					params.push($(inp).is(":checked"));
			}
			else{
				params.push($(inp).val());
			}
		})
	}
	else{
		params = false;
	}
	return params;
}

function loadEditFormToMap(target_id, id, dataFor){
	var $form = $("#" + target_id);
	var params = {};
	if ($form.valid()){
		if (id){
			params["id"] = id;
		}
		var rule = ".form-control";
		if(dataFor){
			rule = "[data-for='" + dataFor + "']";
		}
		$.each($form.find(rule), function(i, inp){
			if ($(inp).prop("type") == "checkbox"){
					params[$(inp).attr("id")] = $(inp).is(":checked");
			}
			else{
				params[$(inp).attr("id")]  = $(inp).val()
			}
		})
	}
	else{
		params = false;
	}
	return params;
}