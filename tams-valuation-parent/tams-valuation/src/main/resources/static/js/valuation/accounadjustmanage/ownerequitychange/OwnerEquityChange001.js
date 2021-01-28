$(function () {
	var flag1 = false;
	var flag2 = false;
	if (hzSchemeSwitch == "ON") {
		flag1 = true;
		flag2 = false;
	} else {
		flag1 = false;
		flag2 = true;
	}
	/**
	 * 获取查询参数
	 */
	var getPageParams = function() {
		 var param =  $.serializeToObject('#searchForm');
	       if(param.booksetGroup){
	   		param.booksetGroup = param.booksetGroup.join(",");
	   	}else{
	           param.booksetGroup = null;
			}
	       return param;
	};

	var table=$("#dataTable").joyintable({
		ajax: {
			url: 'findPage',
			type: 'POST',
			data: function() {
				return getPageParams();
			}

		},
		checkbox: false,
		//tree:true,
		isMulti:true,
		loadonce:true,
		page : false,
		dblclick:function(table, data) {
			if ($("#reportDimen").val() == "1") {// 单账套
				return;
			}
			if ($("#booksetGroup").val() == ""|| $("#booksetGroup").val() == null) {
				return layui.layer.alert("账套组不能为空！");
			}
			var condition = $.serializeToObject('#searchForm');
			condition.project = data.project;
			condition.tIncomeFund = data.tIncomeFund;
			condition.tUnQuarterProfit = data.tUnQuarterProfit;
			condition.tOtherCompIncome = data.tOtherCompIncome;
			condition.tOwnerEquityTot = data.tOwnerEquityTot;
			condition.yIncomeFund = data.yIncomeFund;
			condition.yUnQuarterProfit = data.yUnQuarterProfit;
			condition.yOtherCompIncome = data.yOtherCompIncome;
			condition.yOwnerEquityTot = data.yOwnerEquityTot;
			condition.tableId = "AssetTable";
			condition.booksetGroup = $("#booksetGroup").val();
			condition.lastDimen = $("#lastDimen").val();
			Joyin.dialog(JoyinUtil.getLabel("所有者权益变动表-账套指标明细"),
				basePath + "valuation/accounadjustmanage/ownerequitychange/OwnerEquityChange002/init",
				"95%", "90%", condition, function(){
				});
		},
		cols: [[
			{field:'project',
				title: 'OwnerEquityChange001.project',
				sortable: false,
				rowspan: 2,
				width:345,
				fixed:'left',
				render: function (data, type, row, meta) {
					if($("#reportDimen").val() == "0"){
						return "<label style='color:blue;'>" + row.project + "</label>";
					}
				}

			}

			,{ title: 'OwnerEquityChange001.tHappenAmt',sortable: false,align:"center", colspan: 4}
			,{ title: 'OwnerEquityChange001.yHappenAmt',sortable: false,align:'center', colspan: 4}
		],[
			{field:'tIncomeFund', type:"money",sortable: false, title: 'OwnerEquityChange001.incomeFund',width:165}
			,{field:'tUnQuarterProfit', type:"money",sortable: false, title: 'OwnerEquityChange001.unQuarterProfit',width:165}
			,{field:'tOtherCompIncome', type:"money",sortable: false, title: 'OwnerEquityChange001.OtherCompIncome',width:165}
			,{field:'tOwnerEquityTot', type:"money",sortable: false, title: 'OwnerEquityChange001.ownerEquityTot',width:165}
			,{field:'yIncomeFund', type:"money",sortable: false, title: 'OwnerEquityChange001.incomeFund',width:165}
			,{field:'yUnQuarterProfit', type:"money",sortable: false, title: 'OwnerEquityChange001.unQuarterProfit',width:165}
			,{field:'yOtherCompIncome', type:"money",sortable: false, title: 'OwnerEquityChange001.OtherCompIncome',width:165}
			,{field:'yOwnerEquityTot', type:"money",sortable: false, title: 'OwnerEquityChange001.ownerEquityTot',width:165}
		]]
	});



	// 导出数据
	var exportExcel = function() {
		Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/ownerequitychange/OwnerEquityChange001/check',getPageParams(), function(result) {
			if (result.data) {
				Joyin.exportExcel('export', getPageParams());
			}
		});
	};

	/**
	 * 设置表头
	 */
	var showHeadInfo = function(){
		Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/ownerequitychange/OwnerEquityChange001/getHeadInfo', getPageParams(), function(result) {
			$("#org").html("编制单位： " + result.org);
			$("#statementType").html("报表类型： " + result.statementType);
			$("#statementDate").html("统计日期： " + result.statementDate);
		});
	}
	/**
	 * 清空日期 input
	 */
	var clearDateInput = function() {
		$("#year_reportDate").val("");
		$("#month_reportDate").val("");
		$("#date_reportDate").val("");
		$("#reportTerm").val("").trigger("change");
		$("#reportTerm").find("option").remove(); // 框架缺陷，防止同code不会更新

		$("#year_reportDate").val("");
		$("#month_reportDate").val("");
		$("#date_reportDate").val("");
	}

	/**
	 * 联动绑定 - 筛选条件
	 */
	var linkBind = function(){
		//$("#c_booksetId").hide();
		$("#c_booksetGroup").hide();
		$("#reportDimen").change(function() {
			$("#booksetId").val('');
			var reportDimen = $("#reportDimen").val();
			$("#booksets").val(reportDimen);
			if ('0' == reportDimen) { // 账套组
				$("#c_booksetId").hide();
				$("#booksetId").val("").trigger("change");// select清空
				$("#c_booksetGroup").show();
			} else {
				$("#c_booksetId").show();
				$("#c_booksetGroup").hide();
				$("#booksetGroup").val("").trigger("change");
			}
		});
		
	    //  绑定联动：账套-筛选条件-日期、期限
        $("#booksetId").change(function() {
            var booksetId = $("#booksetId").val();
            var reportTermType = $("#reportTermType").val();
            if(booksetId != "" && ['01','02','03','04','05'].indexOf(reportTermType) > -1){
                Joyin.ajax(
                        basePath + 'valuation/accounadjustmanage/ownerequitychange/OwnerEquityChange001/getDateInfo', 
                        {booksetId : booksetId,reportTermType : reportTermType},
                        function (data) {
                            if(reportTermType === '01'){
                                $("#date_reportDate").val(data.valDate).trigger("change");
                            }else if(reportTermType === '02'){
                                $("#month_reportDate").val(data.valDate).trigger("change");
                            }else if(['03','04'].indexOf(reportTermType) > -1){
                                $("#year_reportDate").val(data.valDate).trigger("change");
                                $("#reportTerm").textSelect('setValue',data.reportTerm);
                            }else if(reportTermType === '05'){
                                $("#year_reportDate").val(data.valDate).trigger("change");
                            }
                  });
            }else{
                $("#date_reportDate").val("").trigger("change");
                $("#month_reportDate").val("").trigger("change");
                $("#year_reportDate").val("").trigger("change");
                $("#reportTerm").textSelect('setValue',false);
            }
        });
        
        //  绑定联动：账套组-筛选条件-日期、期限
        $("#booksetGroup").change(function() {
        	 var booksetGroupArray = $("#booksetGroup").val();
             var booksetGroup = booksetGroupArray.toString();
            var reportTermType = $("#reportTermType").val();
            if(booksetGroup != "" && ['01','02','03','04','05'].indexOf(reportTermType) > -1){
                Joyin.ajax(
                        basePath + 'valuation/accounadjustmanage/ownerequitychange/OwnerEquityChange001/getDateInfo', 
                        {booksetGroup : booksetGroup,reportTermType : reportTermType},
                        function (data) {
                            if(reportTermType === '01'){
                                $("#date_reportDate").val(data.valDate).trigger("change");
                            }else if(reportTermType === '02'){
                                $("#month_reportDate").val(data.valDate).trigger("change");
                            }else if(['03','04'].indexOf(reportTermType) > -1){
                                $("#year_reportDate").val(data.valDate).trigger("change");
                                $("#reportTerm").textSelect('setValue',data.reportTerm);
                            }else if(reportTermType === '05'){
                                $("#year_reportDate").val(data.valDate).trigger("change");
                            }
                  });
            }else{
                $("#date_reportDate").val("").trigger("change");
                $("#month_reportDate").val("").trigger("change");
                $("#year_reportDate").val("").trigger("change");
                $("#reportTerm").textSelect('setValue',false);
            }
        });
		
		$("#reportTermType").change(function(){
		    
            var booksetId = $("#booksetId").val();
            var booksetGroupArray = $("#booksetGroup").val();
            if(booksetGroupArray){            	
            	var booksetGroup = booksetGroupArray.toString();
            }
            var reportTermType = $("#reportTermType").val();
            if(reportTermType != '' && (booksetId != '' || booksetGroup != '')){
                Joyin.ajax(
                        basePath + 'valuation/accounadjustmanage/ownerequitychange/OwnerEquityChange001/getDateInfo', 
                        {booksetId : booksetId,booksetGroup : booksetGroup,reportTermType : reportTermType},
                        function (data) {
                          if(reportTermType === '01'){
                          $("#date_reportDate").val(data.valDate).trigger("change");
                      }else if(reportTermType === '02'){
                          $("#month_reportDate").val(data.valDate).trigger("change");
                      }else if(['03','04'].indexOf(reportTermType) > -1){
                          $("#year_reportDate").val(data.valDate).trigger("change");
                          $("#reportTerm").textSelect('setValue',data.reportTerm);
                      }else if(reportTermType === '05'){
                          $("#year_reportDate").val(data.valDate).trigger("change");
                      }
                  });
            }else{
                $("#date_reportDate").val("").trigger("change");
                $("#month_reportDate").val("").trigger("change");
                $("#year_reportDate").val("").trigger("change");
                $("#reportTerm").textSelect('setValue',false);
            }
		    
			var laydate = layui.laydate;
			var arr1 = ["03", "04"] // 03-季报, 04-半年报
			var arr2 = ["03", "04", "05"] // 03-季报, 04-半年报, 05-年报
			var reportTermType = $("#reportTermType").val();
			if(arr1.indexOf(reportTermType) >= 0){// 报表期限类型为半年报或季报时，可以选择报表期限
				$("#c_reportTerm").show();
			}else{
				$("#c_reportTerm").hide();
			}
			if(arr2.indexOf(reportTermType) >= 0){// 03-季报, 04-半年报, 05-年报
				laydate.render({
					elem: '#year_reportDate',
					type: 'year',
					format: 'yyyy'
				});
				$("#c_year_reportDate").show();
				$("#c_month_reportDate").hide();
				$("#c_date_reportDate").hide();
				clearDateInput();
			}
			if(reportTermType == '02'){// 月报
				laydate.render({
					elem: '#month_reportDate',
					type: 'month',
					format: 'yyyy-MM'
				});
				$("#c_year_reportDate").hide();
				$("#c_month_reportDate").show();
				$("#c_date_reportDate").hide();
				clearDateInput();
			}
			if(reportTermType == '01'){// 日报
				laydate.render({
					elem: '#date_reportDate',
					type: 'date',
					format: 'yyyy-MM-dd'
				});
				$("#c_year_reportDate").hide();
				$("#c_month_reportDate").hide();
				$("#c_date_reportDate").show();
				clearDateInput();
			}
		});
		$("#year_reportDate").click(function(event){
			event.stopPropagation();// 阻止事件冒泡
		});
		$("#month_reportDate").click(function(event){
			event.stopPropagation();// 阻止事件冒泡
		});
		$("#date_reportDate").click(function(event){
			event.stopPropagation();// 阻止事件冒泡
		});
	}


	/**
	 * 画面加载完成
	 */
	$(function() {
		// 联动绑定 - 筛选条件
		linkBind();
		// 绑定查询按钮事件
		$('#OwnerEquityChange001_btn_search').click( function() {
			Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/ownerequitychange/OwnerEquityChange001/check', getPageParams(), function(result) {
				showHeadInfo(); // 设置表头
				if (result.data) {
					var data = result.data;
					var dateStart = data.valueDateStart;
					var datearrstart = dateStart.split("-");
					$("#valueDateStart").html(
						datearrstart[0] + "年" + datearrstart[1] + "月" + datearrstart[2] + "日");
					var dateEnd = data.valueDateEnd;
					var datearrend = dateEnd.split("-");
					$("#valueDateEnd").html(
						datearrend[0] + "年" + datearrend[1] + "月" + datearrend[2] + "日");
					$("#profttInfo").show();
				} else {
					$("#profttInfo").hide();
				}
				if (!table.table) {
					table.load();
				} else {
					table.reload();
				}

			});
		});
		//绑定导出按钮事件
		$('#OwnerEquityChange001_btn_export').click(function() {
			exportExcel();
		});
	});
});
