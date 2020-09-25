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
    	return $.serializeToObject('#searchForm');
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
        cols: [[
                 {field:'project',  title: 'OwnerEquityChange001.project',sortable: false, rowspan: 2,fixed:'left',width:345}
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
