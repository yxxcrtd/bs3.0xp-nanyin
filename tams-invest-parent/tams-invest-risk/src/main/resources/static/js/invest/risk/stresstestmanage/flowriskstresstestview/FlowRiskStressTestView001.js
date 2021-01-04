/**
 * 压力测试
 */

/**
 * 弹出压力测试报告页面
 * 
 * @returns
 */
var openDetailResult = function() {
	Joyin
			.dialog(
					JoyinUtil.getLabel("FlowRiskStressTestView002"),
					JoyinUtil
							.getRealPath("invest/risk/stresstestmanage/flowriskstresstestview/FlowRiskStressTestView002/init"),
					"1200", "90%", {
						pageModel : PAGE_MODEL_NEW
					}, function() {
						//table.reload();
					});
}




$(function() {
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
	/**
	 * 获取查询参数
	 */
	var getSearchParams = function() {
		var testIds='';
		//情景ids
		var sceneIds='';
		if(!($("#testId").val()==null)){	
			testIds = $('#testId').val().join(',');
		}
		if(!($("#sceneIds").val()==null)){	
			sceneIds = $('#sceneIds').val().join(',');
		}
		return {
			exeStatus:	$('#STRESS_TEST_EXE_STATUS').val(),			//执行状态
			openMode:$('#openMode').val(),//适用产品
			baseDate:	$('#baseDate').val(),			//基准日期
			analysisMDate: 	$('#analysisMDate').val(),	//分析结束日
			testDate:	$('#testDate').val(),			//测试日期
			testId: testIds,	//测试id
			sceneId: sceneIds,	//测试id
        }	
	};

	/**
	 * 定义数据表格
	 */
	var table = $("#dataTable")
			.joyintable(
					{
						ajax : {
							//url : 'findPage',
							url : basePath +'/invest/risk/stresstestmanage/flowriskstresstestview/FlowRiskStressTestView001/findOnePage',
							type : 'POST',
							data : function() {
								return getSearchParams();
							}
						},
						//loadonce : true,
						checkbox : false,
						cols : [
								{
									field : 'testName',
									width : 150,
									title : 'FlowRiskStressTestView001。testName',
									sortable : true,
									fixed:'left'
								},
								{
									field : 'testId',
									width : 150,
									title : 'FlowRiskStressTestView001.testId',
									sortable : true
								},
								{
									field : 'sceneId',
									width : 150,
									title : 'FlowRiskStressTestView001.sceneId',
									sortable : true
								},
								{
									field : 'sceneName',
									width : 150,
									title : 'FlowRiskStressTestView001.sceneName',
									sortable : true
								},
								{
									field : 'openModeStr',
									width : 150,
									title : 'FlowRiskStressTestView001.openModeStr',
									sortable : true,
									sortField:'openMode'
								},
								{
									field : 'startTime',
									width : 150,
									title : 'FlowRiskStressTestView001.startTime',
									sortable : true
								},
								{
									field : 'finishedTime',
									width : 150,
									title : 'FlowRiskStressTestView001.finishedTime',
									sortable : true
								},
								{
									field : 'baseDate',
									width : 120,
									title : 'FlowRiskStressTestView001.baseDate',
									sortable : true
								},
								{
									field : 'analysisVDate',
									width : 120,
									title : 'FlowRiskStressTestView001.analysisVDate',
									sortable : true
								},
								{
									field : 'analysisMDate',
									width : 120,
									title : 'FlowRiskStressTestView001.analysisMDate',
									sortable : true
								},
								{
									field : 'remark',
									width : 100,
									title : 'FlowRiskStressTestView001.remark',
									sortable : true
								},
//								{
//									field : 'exeStatusStr',
//									width : 149,
//									title : 'FlowRiskStressTestView001.exeStatusStr',
//									sortable : true,
//									sortField:'exeStatus'
//								},
								{field: 'exeStatusStr', width: 149, sortField: "exeStatus", title: 'FlowRiskStressTestView001.exeStatusStr',
					                render: function (data, type, row, meta) {
					                    if (row.exeStatusStr == '已完成') {
					                        return '<a class="btn layui-btn exeStatus" title="已完成" style="background:#81bb5e;color:white;">已完成</a>';
					                    }else if(row.exeStatusStr == '异常') {
					                        return '<a class="btn layui-btn exeStatus" title="异常" style="background:#ff5e69;;color:white;">&nbsp;&nbsp;异常&nbsp;&nbsp;</a>';
					                    }else {
                                            return '<a class="btn layui-btn exeStatus" title="执行中" style="background:white;color:black;border:1px solid #e6e6e6;">执行中</a>';
                                        }

					                }
					            },
								{
									field : 'action',
									width : 60,
									title : '操作',
//									fixed:'right',
									render : function(data, type, row, meta) {
										if(row.exeStatus=='01' && purviewElements.indexOf('_batch')!=-1){
										return '<span class="jyiconfont jyicon-detail batch btn" title="详细结果" style="color:#208fff;margin-left:6px;"></span>';
										}
										if(row.exeStatus=='03'&&purviewElements.indexOf('_manual')!=-1){
											//return '<i class="iconfont icon-reset manual btn" title="重新运行"></i>';
											return '<i class="layui-icon layui-icon-refresh-3 manual btn" style="color: #1E9FFF;" title="重新运行"></i>  '
										}
									},
									sortable : false
								} ]
					});
	/**
	 * 弹出新建压力测试执行页面
	 * 
	 * @returns
	 */
	function openNew() {
		Joyin.dialog(
			JoyinUtil.getLabel("FlowRiskStressTest001"),
			JoyinUtil.getRealPath("invest/risk/stresstestmanage/flowriskstresstest/FlowRiskStressTest001/init"),
				"1000", "91%", {
					pageModel : PAGE_MODEL_NEW
			}, function() {
				//table.reload();
		});
	}
	/**
	 * 画面加载完成
	 */
	$(function() {
		// 绑定按钮事件
		$('#FlowRiskStressTestView001_btn_search').click(function() {
			table.load();
		});
		$('#FlowRiskStressTestView001_btn_new').click(function() {
			openNew();
		});
	   $('#FlowRiskStressTestView001_btn_export').click(function() {
	            exportExcel();
	        });
		table.bind('click', '.batch', function(data, index) {
		    var object = {selectedItem: [data], operateType:"select_data",listId:data.listId};//请求参数
		    Joyin.dialog('压力测试详情',
		            basePath + "invest/risk/stresstestmanage/flowriskstresstestview/FlowRiskStressTestView002/init",
		            "1200", "90%", data);
		    
		 //   Joyin.dialog(JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("ChannelAsset002.title"), '../ChannelAsset002/init', '98%', '98%');
		    
		});
		table.bind('click', '.manual', function(data, index) {
		    var object = {testId:data.testId,testName:data.testName};//请求参数
            Joyin.ajaxJson(basePath + "invest/risk/stresstestmanage/flowriskstresstestview/FlowRiskStressTestView001/restart",object,function (result){
            	Joyin.alert("后台已执行！请在压力测试概览中查看！",1,function(){
            		table.load();
            	});
            });
		});
	});
}());