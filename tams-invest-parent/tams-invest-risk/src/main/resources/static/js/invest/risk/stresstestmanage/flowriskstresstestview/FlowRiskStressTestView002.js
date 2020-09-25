/**
 * 压力测试
 */
/**
 * 弹出压力测试现金流详情页面
 * 
 * @returns
 */
var openCashFlowDetail = function(portfolioId,flowTestId,testName,finprodId,finprodName,baseDate,startTime,testStartDate,testEndDate) {
	Joyin
			.dialog(
					JoyinUtil.getLabel("FlowRiskStressTestView003"),
					JoyinUtil
							.getRealPath("invest/risk/stresstestmanage/flowriskstresstestview/FlowRiskStressTestView003/init"),
					"1200", "98%", {
						pageModel : PAGE_MODEL_NEW,
						testId:"1",
						flowTestId:flowTestId,
						testName:testName,
						portfolioId:portfolioId,
						finprodId:finprodId,
						finprodName:finprodName,
						baseDate:baseDate,
						startTime:startTime,
						testStartDate:testStartDate,
						testEndDate:testEndDate,
					}, function() {
						//table.reload();
					});
};

$(function() {
	/**
	 * 点击【查询】获取查询参数
	 */
	var getSearchParams1 = function() {
		//alert($('#testId').val());
		return {
			testId: $('#testId').val(),	//测试id
        }	
	};
	
	/**
     * 初始化获取查询参数
     */
    var getSearchParams = function() {
        return  entity1;
    };
    var exportExcel = function() {
        var paramObj = getSearchParams1();
        Joyin.exportExcel('export', paramObj);
    };
	/**
     * 页面跳转初始化定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'findOnePage',
            type: 'POST',
            data: function () {
                return getSearchParams1();
            }
        },
        isMulti:true,
    	//loadonce:true,
    	//searchOnInit:true,
        checkbox:false,
        cols: [
               [
				  {field:'baseDate', visible:false}
				 ,{field:'testStartDate', visible:false}
				 ,{field:'testEndDate' , visible:false}
				 ,{field:'portfolioId' , visible:false}
	             ,{field:'portfolioName', title: '组合/产品名称', rowspan: 2,sortable:false,width: 120, render: function (data, type, row, meta) {
		 	            if(row.portfolioName == '汇总') {
		 	                return '<strong>' + data + '</strong>';
		 	            }
		 	            return data;
		 	        }}
	             ,{field:'finprodId', title: '产品编号', rowspan: 2,sortable:false, width: 100}
	             /*,{field:'finprodName' , title: '产品名称', rowspan: 2,sortable:false, width: 100}*/
	             ,{field:'finprodAmt', title: '产品规模', rowspan: 2,sortable:false, width: 100,type:'number'}           
	             ,{field:'purOfNetOutflow', title: '申赎净流出（基础）', rowspan: 2,sortable:false, width: 160,type:'number'}
	             ,{field:'cashBal', title: '现金余额', rowspan: 2,sortable:false, width: 100,type:'number'}
	             ,{field:'leverRate', title: '杠杆率（基准日）', rowspan: 2,sortable:false, width: 150,type:'number',digit:4}
	             ,{field:'oneLast', title: '一级流动性储备余额', rowspan: 2,sortable:false, width: 180,type:'number'}
	             ,{field:'twoLast', title: '二级流动性储备余额', rowspan: 2,sortable:false, width: 180,type:'number'}
	             ,{field:'inCount', title: '资金流入合计-资产端', rowspan: 2,sortable:false, width: 180,type:'number'}
	             ,{field:'outCount', title: '资金流出合计-资产端', rowspan: 2,sortable:false, width: 180,type:'number'}
	             ,{align : "center", title: '轻度情景',field:'ligValCol', colspan: 5, sortable : false}
	             ,{align : "center", title: '中度情景',field:'medValCol', colspan: 5, sortable : false}
	             ,{align : "center", title: '重度情景',field:'heaValCol', colspan: 5, sortable : false}
	             ,{
						field : 'action',
						width : 100,
						title : '现金流详情',
						rowspan: 2,
						fixed:'right',
						render : function(data, type, row, meta) {
							if(row.portfolioName != '汇总'){
							//return '<a style="border-bottom: 1px solid black" onclick="openCashFlowDetail("+")" id="FlowRiskStressTestView001_btn_cashFlowDetail">'+ '现金流详情' + '</a>';
							//return "<a onclick=\"openCashFlowDetail('"+row.portfolioId+"','"+row.flowTestId+"','"+row.testName+"','"+row.finprodId+"','"+row.finprodName+"','"+row.baseDate+"','"+row.startTime+"','"+row.testStartDate+"','"+row.testEndDate+"')\" + style='border-bottom: 1px solid black'>"+'现金流详情'+"</a>";
							return "<span onclick=\"openCashFlowDetail('"+row.portfolioId+"','"+row.flowTestId+"','"+row.testName+"','"+row.finprodId+"','"+row.finprodName+"','"+row.baseDate+"','"+row.startTime+"','"+row.testStartDate+"','"+row.testEndDate+"')\" class='jyiconfont jyicon-detail batch btn' title='现金流详情' style='color:#208fff;margin-left:6px;'></span>";
						
							}},
						sortable : false
					}
	           ],[
	             
	             {field:'ligValSceBre', title: '资金缺口', sortable:false,width: 130,type:'number'}
	             ,{field:'ligValRiskSlowBreOne', title: '一级风险缓释后缺口', sortable:false,width: 130,type:'number'}
	             ,{field:'ligValRiskSlowBre', title: '二级风险缓释后缺口', sortable:false,width: 130,type:'number'}
	             ,{field:'ligValFlowMonBre', title: '流动储备/资金缺口', sortable:false,width: 130,type:'number',digit:4}
	             ,{field:'ligValLeverRate', title: '杠杆率',sortable:false, width: 130,type:'number',digit:4}
	             
	             ,{field:'medValSceBre', title: '资金缺口',sortable:false, width: 130,type:'number'}
	             ,{field:'medValRiskSlowBreOne', title: '一级风险缓释后缺口', sortable:false,width: 130,type:'number'}
	             ,{field:'medValRiskSlowBre', title: '二级风险缓释后缺口', sortable:false,width: 130,type:'number'}
	             ,{field:'medValFlowMonBre', title: '流动储备/资金缺口',sortable:false, width: 130,type:'number',digit:4}
	             ,{field:'medValLeverRate', title: '杠杆率', sortable:false,width: 130,type:'number',digit:4}
	             
	             ,{field:'heaValSceBre', title: '资金缺口',sortable:false, width: 130,type:'number'}
	             ,{field:'heaValRiskSlowBreOne', title: '一级风险缓释后缺口',sortable:false, width: 130,type:'number'}
	             ,{field:'heaValRiskSlowBre', title: '二级风险缓释后缺口',sortable:false, width: 130,type:'number'}
	             ,{field:'heaValFlowMonBre', title: '流动储备/资金缺口',sortable:false, width: 130,type:'number',digit:4}
	             ,{field:'heaValLeverRate', title: '杠杆率', sortable:false,width: 130,type:'number',digit:4}
	           ]
             ]
            
    });
    

	
    /**
     * 画面加载完成
     */
    $(function() {
    	table.load();
    	// 绑定按钮事件
    	$('#FlowRiskStressTestView002_btn_export').click(function() {
           if(($('#testId').val())==null||($('#testId').val())==''){
        	   Joyin.error('请选择压力测试编号或名称！');
           }else{
        	   exportExcel();
           }
    	});
        $('#FlowRiskStressTestView002_btn_search').click(function() {
        	 Joyin.ajax(basePath + 'invest/risk/stresstestmanage/flowriskstresstestview/FlowRiskStressTestView002/queryDetByTestId', {testId: $('#testId').val()}, function (data) {
        		 //适用产品
        		 $('#openMode').val(data.openModeStr);
        		 //压力测试执行时间
        		 $('#startTime').val(data.startTime);
        		 //分析基准日期
        		 $('#baseDate').val(data.baseDate);
        		 //分析开始日期
        		 $('#analysisVDate').val(data.testStartDate);
        		 //分析结束日期
        		 $('#analysisMDate').val(data.testEndDate);
        		 //备注
        		 $('#remark').val(data.remark);
        		//情景编号和名称
        		 if(data.sceneIdAndsceneName!=':'){//判断是否是':',如果不是则赋值
        		 $('#sceneIdAndsceneName').val(data.sceneIdAndsceneName);
        		 }
        	 });
            table.load();
        });

        //点击情景弹出情景页面
        $('#sceneIdAndsceneName').click(function(){
        	var sceneIdAndsceneName=$('#sceneIdAndsceneName').val();
        	//取出情景代码
        	if(sceneIdAndsceneName!=null && sceneIdAndsceneName!=''){
        	var sceneId=sceneIdAndsceneName.substr(0,sceneIdAndsceneName.indexOf(':'));
        	Joyin.dialog(JoyinUtil.getLabel("FlowRiskSceneManage003"),
                    basePath + "invest/risk/stresstestmanage/flowriskscenemanage/FlowRiskSceneManage002/init",
                    "98%", "98%", {pageModel:PAGE_MODEL_REFERENCE,
        							sceneId:sceneId //情景id
                                    });
        	}else{
        		Joyin.error("无效的情景！！！");
        	}
        	
        });
    });
}());