function navDetail(date, dataFor, data, inputs){
	invoke(getURL("navDetail"), {portID:inputs.portfolio, navDate:date}, function(data){
		var options = {};
		options.title = "nav_title";
		options.alert = "nav_alert";
		options.detail = "nav_detail";
		options.modal = "nav_modal";
		options.data = data;
		drawNavDetail(options);
	},"alertAll");
	
}

function cashDetail(accountID, dataFor, data, inputs){
	var inputs = {accountID:accountID, portID:inputs.portfolio, start:inputs.start, end:inputs.end, num:1};
	invoke(getURL("cashDetail"), inputs, function(data){
		var options = {};
		options.title = "cash_title";
		options.alert = "cash_alert";
		options.detail = "cash_detail";
		options.modal = "cash_modal";
		options.data = data;
		drawCashDetail(options);
		
		if (data.pages){
			var $pagination = genPagination(options.detail, data.pages);
			$pagination.on("page", function(event, num){
				inputs.num = num;
				invoke(getURL("cashDetail"), inputs, function(data){
					$("#cash_detail").empty();
					options.data = data;
					drawCashDetail(options);
				});
			});
		}
	},"alertAll");
}

function positionDetail(secID, dataFor, data, inputs){
	var inputs = {secID:inputs.id, portID:inputs.portfolio, start:inputs.start, end:inputs.end, num:1};
	var options = {};
	options.title = "position_title";
	options.alert = "position_alert";
	options.detail = "position_detail";
	options.modal = "position_modal";
	options.inputs = inputs;
	
	options.trades = fetch(getURL("positionTradeDetail"), inputs, "alertAll");
	options.amorts = fetch(getURL("positionAmortDetail"), inputs, "alertAll");
	drawPositionDetail(options);
}

function drawNavDetail(options){
	var $title = $("#" + options.title);
	var $alert = $("#" + options.alert);
	var $detail = $("#" + options.detail);
	var $modal = $("#" + options.modal);
	
	$title.text("净值详情");
	$detail.empty();
	
	drawEditForm(options.detail, "navDetail", "nav_detail", {}, options.data.data, 4, true);
	$modal.modal();
}

function drawCashDetail(options){
	var $title = $("#" + options.title);
	var $alert = $("#" + options.alert);
	var $detail = $("#" + options.detail);
	var $modal = $("#" + options.modal);
	
	$title.text("现金流水");
	$detail.empty();
	
	$.each(options.data.data, function(i, v){
		if (v[3] != "-" && v[3] != ""){
			options.data.data[i][3] = "<a href='#' id='cashPos_voucher_" + v[3] + "' data-for='cashPos_voucher'>#" + v[3]+ "</a>";
		}	
	});
	drawTable(options.detail, options.data);
	$("a[data-for='cashPos_voucher']").on("click", function(){
		var voucherNum = $(this).attr("id").split("_")[2];
		invoke(getURL("voucherDetail"), {id:voucherNum}, function(data){
			$("#cashVoucher_detail").empty();
			$("#cashVoucher_title").text("凭证详情 -" + voucherNum);
	        drawTable("cashVoucher_detail", data);
			$("#cashVoucher_modal").modal();
			$("#cash_modal").modal("hide");
		}, options.alert)
	});
	
	$("#cashVoucher_modal").on("hidden.bs.modal", function(){
		$("#cash_modal").modal();
	});
	
	$("a[href='#cashBasic_display']").tab("show");
	$modal.modal();
}

function drawPositionDetail(options){
	var $title = $("#" + options.title);
	var $alert = $("#" + options.alert);
	var $detail = $("#" + options.detail);
	var $modal = $("#" + options.modal);
	
	$title.text("头寸详情:  " + options.inputs.secID );
	$detail.empty();
	
	var tabs =  {};
	if (options.amorts.data.length == 0){
		tabs = {"secTrade":"交易详情"};
	}
	else {
		tabs = {"secTrade":"交易详情", "secAmort":"摊销计划"};
	}
	genTabs(options.detail, "secPos_tabs", "secPose_cons", tabs);
	$("a[href='#secTrade_display']").tab("show");
	
	$.each(options.trades.data, function (i, v){
		if (v[3]!="" && v[3]!= "-"){
			options.trades.data[i][3] = "<a href='#' id='secPos_voucher_" + v[3] + "' data-for='secPos_voucher'>#" + v[3]+ "</a>";
		}
		if (v[4]!="" && v[4]!= "-"){
			options.trades.data[i][4] = "<a href='#' id='secPos_voucher_" + v[4] + "' data-for='secPos_voucher'>#" + v[4]+ "</a>";
		}
	});
	drawTable("secTrade_display", options.trades);
	$("a[data-for='secPos_voucher']").on("click", function(){
		var voucherNum = $(this).attr("id").split("_")[2];
		invoke(getURL("voucherDetail"), {id:voucherNum}, function(data){
			$("#positionVoucher_detail").empty();
			$("#positionVoucher_title").text("凭证详情 -" + voucherNum);
	        drawTable("positionVoucher_detail", data);
			$("#positionVoucher_modal").modal();
			$("#position_modal").modal("hide");
		}, options.alert);
	});
	
	$("#positionVoucher_modal").on("hidden.bs.modal", function(){
		$("#position_modal").modal();
	});
	
	drawTable("secAmort_display", options.amorts);
	
	$modal.modal();
	$modal.on('hidden.bs.modal', function (e) {
		 $detail.empty();
	});
}