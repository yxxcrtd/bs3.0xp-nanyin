function drawToVerify(target_id, usr, startDate, endDate){
	var $target = $("#" + target_id);
	$target.empty();
	
	var t = $.extend(true, {}, checkTable);
	t.source.address = "toVerifyMsgList";
	t.source.inputs = {user:usr, startDate:startDate, endDate:endDate};
	t.source.func = function(data){$("button[data-for='toVerify_execute']").text("审核");}
	
	t.event.execute.source.address = "verifyAction";
	t.event.execute.source.func = function(itemID, dataFor){drawVerifyAll();}
	
	t.event.reject.source.address = "rejectAction";
	t.event.reject.source.func = function(itemID, dataFor){drawVerifyAll();}
	
	//TODO - 显示审核详情的方法
	t.event.detail.on = false;
	t.event.detail.source.address = "verifyMsgDetail";
	t.event.detail.source.inputs = {};
	t.event.detail.source.func = function(itemID, dataFor, data, inputs){
		var ffs = unifyForm(data.form, data.data);
		if (ffs && ffs != ""){
			drawVerifyForm("verifyDetail_detail", "verifyDetail", ffs, 4, true);
			$("#verifyDetail_title").text("审核 - " +  $("#" + itemID).find("td").eq(2).text());
			$("#verifyDetail_modal").modal();
		}
		
	};
	genTableWithCheck(target_id, "toVerify", t.source, t.event, ["execute", "reject"], "alertAll", false);
	return false;
}

function drawVerified(target_id, usr, startDate, endDate){
	var $target = $("#" + target_id);
	$target.empty(); 
	drawPagTable(target_id, "verifiedMsgList", {user:usr, startDate:startDate, endDate:endDate}, "", "");
}

function drawRejected(target_id, usr, startDate, endDate){
	var $target = $("#" + target_id);
	$target.empty();
	drawPagTable(target_id, "rejectedMsgList", {user:usr, startDate:startDate, endDate:endDate}, "", "");
}

function drawVerifyAll(){
	var startDate = $("#verify_start_filter").val();
	var endDate = $("#verify_end_filter").val();
	var usr = $("#verify_user_filter").val();
	
	if (!usr){
		usr = ALL;
	}
	
	if ((! startDate) || (! endDate)){
		startDate = getNowFormatDate();
		endDate = startDate;
	}
	drawToVerify("toVerify_display", usr, startDate, endDate);
	drawVerified("verified_display", usr, startDate, endDate);
	drawRejected("rejected_display", usr, startDate, endDate);
}