function drawUsers(target_id){
	drawFilterForm(target_id, "user", "user_filter", {}, true);
	$("#user_create_btn").on("click", function(){
		var e = $.extend(true, {}, editForm);
 		e.source.on = false;
		e.form.name = "user_add";
		e.form.func = function(){
			$("#user_active").prop("checked", true);
		}
		e.event.update.on = false;
		e.event.create.source.address = "userCreate";
		e.event.create.source.func = function(dataFor, id){
			$("div.modal-footer button").text("下一步");
			var saved = {};
			var full = fetch(getURL("clientList"), {}, "client_alert");
			var left = getLeftOps(full, saved);
			$("#client_title").text("用户 - 客户配置");
			$("#client_detail").empty();
			drawSelections("client_detail", "可选客户", "已选客户", "clientLeft", "clientSelected", left, saved, "client", id, "", "userClientUpdate", "client_alert");
			$("#client_modal").modal();
			
			$("#client_modal").on("hidden.bs.modal", function(){
				var saved = {};
				var clients = fetch(getURL("userClientDetail"), {id:id}, "port_alert");
				var full = {};
				$.each(clients, function(clientID, clientName){
					var ports = fetch(getURL("portShortList"), {client:clientID}, "port_alert");
					full = $.extend(true, full, ports);
				});
				saved = removeOps(full, saved);
				var left = getLeftOps(full, saved);
				$("#port_title").text("用户 - 组合配置");
				$("#port_detail").empty();
				drawSelections("port_detail", "可选组合", "已选组合", "portLeft", "portSelected", left, saved, "port", id, "", "userPortUpdate", "port_alert");
				$("#port_modal").modal();
			});
			$("#port_modal").on("hidden.bs.modal", function(){
				$("div.modal-footer button").text("关闭");
			});
		};
		$("#user_detail").empty();
		$("#user_title").text("创建用户");
		genEditForm("user_detail", "user", e.source, e.form, e.event, 3, "user_alert", false);
		$("#user_modal").modal();
	});
	$("#user_search_btn").on("click", function(){
		$("#client_modal").off("hidden.bs.modal");
		$("#port_modal").off("hidden.bs.modal");
		var t = $.extend(true, {}, checkTable);
		t.source.address = "users";
		t.source.inputs = {name:$("#user_name_filter").val(),
				           role:$("#user_role_filter").val(),
				           client:$("#user_client_filter").val()};
		t.source.func = function(data){
			var trs = $("tr[data-for='user']");
			var $title_tr = $(trs[0]).prev();
			$title_tr.append("<th>客户/组合配置</th>");
			$.each(trs, function(i, tr){
				var id = $(tr).attr("id");
				var $b_td = $("<td></td>");
				$b_td.append($("<button id='client_update_" + id + "' class='btn btn-primary' data-for='client_update'>客户配置</button>"));
				$b_td.append("&nbsp;");
				$b_td.append($("<button id='port_update_" + id + "' class='btn btn-success' data-for='port_update'>组合配置</button>"));
				$(tr).append($b_td);
			});
			$("[data-for='client_update']").on("click", function(){
				var id = $(this).attr("id").split("_")[2];
				var saved = fetch(getURL("userClientDetail"), {id:id}, "client_alert");
				var full = fetch(getURL("clientList"), {}, "client_alert");
				var left = getLeftOps(full, saved);
				$("#client_title").text("用户 - 客户配置");
				$("#client_detail").empty();
				drawSelections("client_detail", "可选客户", "已选客户", "clientLeft", "clientSelected", left, saved, "client", id, "", "userClientUpdate", "client_alert");
				$("#client_modal").modal();
			});
			$("[data-for='port_update']").on("click", function(){
				var id = $(this).attr("id").split("_")[2];
				var saved = fetch(getURL("userPortDetail"), {id:id}, "port_alert");
				var clients = fetch(getURL("userClientDetail"), {id:id}, "port_alert");
				var full = {};
				$.each(clients, function(clientID, clientName){
					var ports = fetch(getURL("portByClient"), {client:clientID}, "port_alert");
					full = $.extend(true, full, ports);
				});
				saved = removeOps(full, saved);
				var left = getLeftOps(full, saved);
				$("#port_title").text("用户 - 组合配置");
				$("#port_detail").empty();
				drawSelections("port_detail", "可选组合", "已选组合", "portLeft", "portSelected", left, saved, "port", id, "", "userPortUpdate", "port_alert");
				$("#port_modal").modal();
			});
		};
		t.event.detail.source.address = "userDetail";
		t.event.detail.form.name = "user_edit"
		t.event.detail.form.title_id = "user_title";
		t.event.detail.form.modal_id = "user_modal";
		t.event.detail.form.target_id = "user_detail";
		t.event.detail.form.alert_id = "user_alert";
		
		t.event.add.on = false;
		t.event.del.on = false;
		
		t.event.update.source.address = "userUpdate";
		$("#user_list").empty();
		genTableWithCheck("user_list", "user", t.source, t.event, ["detail"], "alertAll", false);
	});
}

function drawRoles(target_id){
	drawFilterForm(target_id, "role", "userRole_filter", {}, true);
	$("#role_search_btn").on("click", function(){
		var $line = $("<div class='row' style='border-bottom: dotted; border-width: thin'></div>");
		var saved = fetch(getURL("userRoleDetail"), {id:$("#role_role_filter").val()}, "alertAll");
		var full = fetch(getURL("userGroups"), {});
		var left = getLeftOps(full, saved);
		$("#role_list").empty();
		$("#role_list").append($line);
		
		var id = $("#role_role_filter").val();
		var name = $("#role_role_filter option:selected").text();
		$("#role_list").append("<div><label>ID: </label><label id='roleID'>" + id + "</label></div>");
		$("#role_list").append("<div><label>名称: </label><label id='roleName'>" + name + "</label></div>");
		drawSelections("role_list", "可选用户组", "已选用户组", "grpLeft", "grpSelected", left, saved, "role", id, name, "userRoleUpdate");
	});
	
	$("#role_create_btn").on("click", function(){
		bootbox.prompt("请输入用户角色名称", function(result){
   	    	if (result == null){
   	    		bootbox.hideAll();
       		}
       		else {
       			if (result == ""){
       				$("#alertAll").empty();
   	                $("#alertAll").append(genAlert("fail", translate["role"] + "创建失败! 请输入用户角色名称!"));	
       			}
       			else {
       				invoke(getURL("userRoleCreate"), {name:result}, function(data){
       					var $line = $("<div class='row' style='border-bottom: dotted; border-width: thin'></div>");
           				var saved = {};
           				var full = fetch(getURL("userGroups"), {});
           				var left = getLeftOps(full, saved);
           				$("#role_list").empty();
           				$("#role_list").append($line);
           				var id = data;
           				var name = result;
           				$("#role_list").append("<div><label>ID: </label><label id='roleID'>" + id + "</label></div>");
           				$("#role_list").append("<div><label>名称: </label><label id='roleName'>" + name + "</label></div>");
           				drawSelections("role_list", "可选用户组", "已选用户组", "grpLeft", "grpSelected", left, saved, "role", id, name, "userRoleUpdate");
       				}, "alertAll");
       			}
       		}
    	});
	});
}

function drawGroups(target_id){
	drawFilterForm(target_id, "group", "userGroup_filter", {}, true);
	$("#group_search_btn").on("click", function(){
		var $line = $("<div class='row' style='border-bottom: dotted; border-width: thin'></div>");
		var saved = fetch(getURL("userGroupDetail"), {id:$("#group_group_filter").val()}, "alertAll");
		var full = fetch(getURL("userActions"), {});
		var left = getLeftOps(full, saved);
		$("#group_list").empty();
		$("#group_list").append($line);
		
		var id = $("#group_group_filter").val();
		var name = $("#group_group_filter option:selected").text();
		$("#group_list").append("<div><label>ID: </label><label id='groupID'>" + id + "</label></div>");
		$("#group_list").append("<div><label>名称: </label><label id='groupName'>" + name + "</label></div>");
		drawSelections("group_list", "可选用户行为", "已选用户行为", "actionLeft", "actionSelected", left, saved, "group", id, name, "userGroupUpdate");
	});
	
	$("#group_verify_btn").on("click", function(){
		var $line = $("<div class='row' style='border-bottom: dotted; border-width: thin'></div>");
		var saved = fetch(getURL("groupVerifyDetail"), {id:$("#group_group_filter").val()}, "alertAll");
		var full = fetch(getURL("verifyActions"), {});
		var left = getLeftOps(full, saved);
		$("#group_list").empty();
		$("#group_list").append($line);
		
		var id = $("#group_group_filter").val();
		var name = $("#group_group_filter option:selected").text();
		$("#group_list").append("<div><label>ID: </label><label id='groupID'>" + id + "</label></div>");
		$("#group_list").append("<div><label>名称: </label><label id='groupName'>" + name + "</label></div>");
		drawSelections("group_list", "可选审核行为", "已选审核行为", "verifyLeft", "verifySelected", left, saved, "verify", id, name, "groupVerifyUpdate");
	});
	
	$("#group_create_btn").on("click", function(){
		bootbox.prompt("请输入用户分组名称", function(result){
   	    	if (result == null){
   	    		bootbox.hideAll();
       		}
       		else {
       			if (result == ""){
       				$("#alertAll").empty();
   	                $("#alertAll").append(genAlert("fail", translate["group"] + "创建失败! 请输入用户分组名称!"));	
       			}
       			else {
       				invoke(getURL("userGroupCreate"), {name:result}, function(data){
       					var $line = $("<div class='row' style='border-bottom: dotted; border-width: thin'></div>");
           				var saved = {};
           				var full = fetch(getURL("userActions"), {});
           				var left = getLeftOps(full, saved);
           				$("#group_list").empty();
           				$("#group_list").append($line);
           				var id = data;
           				var name = result;
           				$("#group_list").append("<div><label>ID: </label><label id='groupID'>" + id + "</label></div>");
           				$("#group_list").append("<div><label>名称: </label><label id='groupName'>" + name + "</label></div>");
           				drawSelections("group_list", "可选用户行为", "已选用户行为", "grpLeft", "grpSelected", left, saved, "role", id, name, "userGroupUpdate");
       				}, "alertAll");
       			}
       		}
    	});
	});
}