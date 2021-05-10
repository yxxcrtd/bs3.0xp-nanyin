$(function() {

	var reportDimen = "01";
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
    	var searchObject = $.serializeToObject('#searchForm');
    	searchObject.profitType = $.trim($('#profitType').val());
        return searchObject;
    };
    var table = $("#dataTable").joyintable({
        ajax : {
            url : 'findPage',
            type : 'POST',
            data : function() {
                return getPageParams();
            }
        },
        checkbox : false,
        searchOnInit : false,
        loadonce : true,
        page : false,
		dblclick:function(table, data) {
			if (reportDimen == "1") {// 单账套
				return;
			}
			var condition = $.serializeToObject('#searchForm');
			condition.project = data.project.replace(new RegExp("&nbsp;","g"),"");
			condition.thisTermAmt = data.thisTermAmt;
			condition.lastTermAmt = data.lastTermAmt;
			condition.booksetGroup = $("#booksetGroup").val();
			//add by yanya 20190929 利润表年报变更为选择具体天数时，增加参数类型区分，后端区别处理日期类型
			condition.yearType = "ValProfitQuery001";
			condition.booksetGroup = $("#booksetGroup").val();
			Joyin.dialog(JoyinUtil.getLabel("利润表-账套指标明细"),
				basePath + "valuation/accounadjustmanage/valprofitquerynew/ValProfitNewQuery002/init",
				"95%", "90%", condition, function(){
				});
		},
        cols : [ {
            field : 'project',
            sortable : false,
			width : 500,
            title : '项目'
        }, {
            field : 'thisTermAmt',
            width : 450,
            type : "money",
            sortable : false,
            title : '本期/年金额'
        }, {
            field : 'lastTermAmt',
            width : 450,
            type : "money",
            sortable : false,
            title : '上期/年金额'
        }
        ]
    });
    
    // 导出数据
    var exportExcel = function() {
        Joyin.ajaxJson(basePath
                + 'valuation/accounadjustmanage/valprofitquerynew/ValProfitNewQuery001/check',
                getPageParams(), function(result) {
                    if (result.data) {
                        Joyin.exportExcel('export', getPageParams());
                    }
                });
    };

	/**
	 * 设置表头
	 */
	var showHeadInfo = function(){
		Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/valprofitquerynew/ValProfitNewQuery001/getHeadInfo', getPageParams(), function(result) {
			$("#org").html("编制单位： " + result.org);
			$("#statementType").html("报表类型： " + result.statementType);
			$("#statementDate").html("统计日期： " + result.statementDate);
		});
	}

	/**
	 * 联动绑定 - 筛选条件
	 */
	var linkBind = function(){
		// 报表维度为单张套，账套代码可编辑，否则不可编辑
//		$("#reportDimen").change(function(){
//			var reportDimen = $("#reportDimen").val();
//			if ("01" == reportDimen) {
//				$("#booksetId").attr('disabled',false);
//			} else {
//				$("#booksetId").val("");
//				$("#booksetId").attr('disabled',true);
//			}
//		});
		$("#c_booksetId").hide();
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
					basePath + 'valuation/accounadjustmanage/valprofitquery/ValProfitQuery001/getDateInfo',
					{booksetId : booksetId,reportTermType : reportTermType},
					function (data) {
						if(reportTermType === '01'){
							$("#date_reportDate").val(data.reportDate).trigger("change");
						}else if(reportTermType === '02'){
							$("#month_reportDate").val(data.reportDate).trigger("change");
						}else if(['03','04'].indexOf(reportTermType) > -1){
							$("#year_reportDate").val(data.reportDate).trigger("change");
							$("#reportTerm").val(data.reportTerm).trigger("change");
						}else if(reportTermType === '05'){
							$("#date_reportDate").val(data.reportDate).trigger("change");
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
			var booksetGroup = $("#booksetGroup").val();
			var reportTermType = $("#reportTermType").val();
			if(booksetGroup != "" && ['01','02','03','04','05'].indexOf(reportTermType) > -1){
				Joyin.ajax(
					basePath + 'valuation/accounadjustmanage/valprofitquery/ValProfitQuery001/getDateInfo',
					{booksetGroup : booksetGroup,reportTermType : reportTermType},
					function (data) {
						if(reportTermType === '01'){
							$("#date_reportDate").val(data.reportDate).trigger("change");
						}else if(reportTermType === '02'){
							$("#month_reportDate").val(data.reportDate).trigger("change");
						}else if(['03','04'].indexOf(reportTermType) > -1){
							$("#year_reportDate").val(data.reportDate).trigger("change");
							$("#reportTerm").val(data.reportTerm).trigger("change");
						}else if(reportTermType === '05'){
							$("#date_reportDate").val(data.reportDate).trigger("change");
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
			var booksetGroup = $("#booksetGroup").val();
			var reportTermType = $("#reportTermType").val();
			if(reportTermType != '' && (booksetId != '' || booksetGroup != '')){
				Joyin.ajax(
					basePath + 'valuation/accounadjustmanage/valprofitquery/ValProfitQuery001/getDateInfo',
					{booksetId : booksetId,booksetGroup : booksetGroup,reportTermType : reportTermType},
					function (data) {
						if(reportTermType === '01'){
							$("#date_reportDate").val(data.reportDate).trigger("change");
						}else if(reportTermType === '02'){
							$("#month_reportDate").val(data.reportDate).trigger("change");
						}else if(['03','04'].indexOf(reportTermType) > -1){
							$("#year_reportDate").val(data.reportDate).trigger("change");
							$("#reportTerm").textSelect('setValue',data.reportTerm);
						}else if(reportTermType === '05'){
							$("#date_reportDate").val(data.reportDate).trigger("change");
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
			//update yanya 20190912 年报原控件类型yyyy更为到天yyyy-MM-dd
			//var arr2 = ["03", "04", "05"] // 03-季报, 04-半年报, 05-年报
			var arr2 = ["03", "04"] // 03-季报, 04-半年报, 05-年报
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
			//add by yanya 20190912 年报原控件类型yyyy更为到天yyyy-MM-dd
			if(reportTermType == '05'){// 年报
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
	 * 查询按钮绑定
	 */
	var searchBind = function(){
		// 绑定按钮事件
        $('#ValProfitNewQuery001_btn_search').click(function() {
        	Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/valprofitquerynew/ValProfitNewQuery001/check', getPageParams(), function(result) {
        		reportDimen = $("#reportDimen").val();
        		showHeadInfo(); // 设置表头
        		if(!table.table) {
        			table.load();
                } else {
                	table.reload();
                }
    		});
        });
	}
	
    /**
     * 画面加载完成
     */
    $(function() {
    	
    	searchBind(); // 查询按钮绑定
    	linkBind(); // 联动绑定 - 筛选条件
    	
        //绑定导出按钮事件
        $('#ValProfitNewQuery001_btn_export').click(function() {
            exportExcel();
        });

    });
}());
