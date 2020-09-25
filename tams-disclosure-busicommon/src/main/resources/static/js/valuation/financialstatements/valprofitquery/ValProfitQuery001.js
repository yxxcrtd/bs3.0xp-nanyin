$(function() {

	var reportDimen = "01";
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
    	return $.serializeToObject('#searchForm');
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
        	if (reportDimen == "01") {
        		return;
        	}
        	var condition = $.serializeToObject('#searchForm');
        	condition.project = data.project.replace(new RegExp("&nbsp;","g"),"");
        	condition.thisTermAmt = data.thisTermAmt;
        	condition.lastTermAmt = data.lastTermAmt;
        	Joyin.dialog(JoyinUtil.getLabel("利润表-账套指标明细"),
        			basePath + "valuation/accounadjustmanage/valprofitquery/ValProfitQuery002/init",
        			"95%", "90%", condition, function(){
        			});
        },
        cols : [ {
            field : 'project',
            sortable : false,
            title : 'ValProfitQuery001.project'
        }, {
            field : 'thisTermAmt',
            width : 400,
            type : "money",
            sortable : false,
            title : 'ValProfitQuery001.thisTermAmt'
        }, {
            field : 'lastTermAmt',
            width : 400,
            type : "money",
            sortable : false,
            title : 'ValProfitQuery001.lastTermAmt'
        }
        ]
    });
    
    // 导出数据
    var exportExcel = function() {
        Joyin.ajaxJson(basePath
                + 'valuation/accounadjustmanage/valprofitquery/ValProfitQuery001/check',
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
		Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/valprofitquery/ValProfitQuery001/getHeadInfo', getPageParams(), function(result) {
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
		$("#reportDimen").change(function(){
			var reportDimen = $("#reportDimen").val();
			if ("01" == reportDimen) {
				$("#booksetId").attr('disabled',false);
			} else {
				$("#booksetId").val("");
				$("#booksetId").attr('disabled',true);
			}
		});
		$("#reportTermType").change(function(){
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
        $('#ValProfitQuery001_btn_search').click(function() {
        	Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/valprofitquery/ValProfitQuery001/check', getPageParams(), function(result) {
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
        $('#ValProfitQuery001_btn_export').click(function() {
            exportExcel();
        });

    });
}());
