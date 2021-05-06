$(function () {
	//左侧列表主键：Id -- 单击时使用
	var repId = "";
	// 获取查询参数
	var getOptParams = function () {
		return {
			repId: repId,
			prodId: $('#prodId').val(),
			endDate: $('#endDate').val(),
			reportType: $('#reportType').val()
		};
	}

//    =========================  报表指标设置（左侧列表）   =======================
	/**
	 * 设置表格：左侧指标列表    【点击左边的目录，右边表格会出现对应的数据；】
	 */
	var dataTable = $("#dataTable").joyintable({
		ajax: {
			url: 'queryRepFtpPathList',
			type: 'POST',
			data: {}
		},
		searchOnInit: false,
		cellMinWidth:100 ,
		click:function(table, data){
			//点击时刷新右侧条件
			repId = data.id;
			$('#endDateDiv').hide();
			$('#reportTypeDiv').hide();
			//非标投资报告
			if(repId == CHB_DISCLOSURE_REPORT_02) {
				$('#endDateDiv').show();
			}
			//定义报告
			if(repId == CHB_DISCLOSURE_REPORT_04) {
				$('#endDateDiv').show();
				$('#reportTypeDiv').show();
			}

		},
		checkbox:false,
		cols: [
			{field:'name', title: '报告名称', sortable: false},
			{field:'id', title: 'id', sortable: false, visible:false}
		],
		done:function(){
			$('#dataTable').next().find("thead").remove();// 删除表头
			//选择报表指标管理默认打开第一个指标库并展示详情
			var schArr = $('#dataTable').next().find("td");
			if( schArr != undefined && schArr.length > 0 ){
				schArr[0].click();// 默认选中第一个指标
			}
		}
	});


	var createDoc = function() {
		var obj = getOptParams();
		Joyin.ajaxJson(
			"check",
			obj,
			function(result) {
				if (result) {
					Joyin.ajax(
							basePath + 'svreport/chbreport/reportgenerate/ChbReportGenerate001/createReportDoc',
							obj,
							function(result) {

							});
				}
			});
	};

	var exportDoc = function() {
		var obj = getOptParams();
		Joyin.ajaxJson(
			"check",
			obj,
			function(result) {
				if (result) {
					Joyin.exportExcel('exportReportDoc', obj);
				}
			});
	};
	/**
	 * 页面加载完后执行
	 */
	$(function() {
		dataTable.load();//加载左侧列表
		// 绑定按钮事件
		$('#ChbReportGenerate001_btn_add').click(function () {
			createDoc();
		});
		$('#ChbReportGenerate001_btn_export').click(function () {
			exportDoc();
		});
	});

});

