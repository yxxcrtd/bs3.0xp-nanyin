$(function () {
	//左侧列表主键：Id -- 单击时使用
	var repId = "";
	// 获取查询参数
	var getOptParams = function () {
		return {
			repId: repId,
			prodId: $('#prodId').val(),
			endDate: $('#endDate').val(),
			reportType: $('#reportType').val(),
			year: $('#year').val()
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
			$('#yearDiv').hide();
			$('#emptyDiv').hide();
			$('#detailTableDiv').hide();
			$('#dqTipsDiv').hide();
			$('#ChbReportGenerate001_btn_search').hide();
			$('#detailTableDiv').html('<table class="layui-table" id="detailTable"></table>');
			//成立公告
			if(repId == CHB_DISCLOSURE_REPORT_01) {
				$('#endDateDiv').show();
				$("#endDateLabel").html("成立日期");
				$('#emptyDiv').show();
				$('#detailTableDiv').show();
				initDetailTable();
				$('#ChbReportGenerate001_btn_search').show();
			}
			//非标投资报告
			if(repId == CHB_DISCLOSURE_REPORT_02) {
				$('#endDateDiv').show();
				$("#endDateLabel").html("变动日");
				$('#emptyDiv').show();
				$('#detailTableDiv').show();
				initDetailTable();
				$('#ChbReportGenerate001_btn_search').show();
			}
			//到期公告
			if(repId == CHB_DISCLOSURE_REPORT_03) {
				$('#endDateDiv').show();
				$("#endDateLabel").html("到期日");
				$('#emptyDiv').show();
				$('#detailTableDiv').show();
				initDetailTable();
				$('#ChbReportGenerate001_btn_search').show();
			}
			//定期公告
			if(repId == CHB_DISCLOSURE_REPORT_04) {
				$('#reportTypeDiv').show();
				$('#yearDiv').show();
				$('#dqTipsDiv').show();
			}
			//净值披露
			if(repId == CHB_DISCLOSURE_REPORT_05){
				$('#endDateDiv').show();
				$("#endDateLabel").html("净值日期");
				$('#emptyDiv').show();
				$('#detailTableDiv').show();
				initDetailTable();
				$('#ChbReportGenerate001_btn_search').show();
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

	/**
	 * 获取展示的列
	 * @returns {[]}
	 */
	var getCols = function() {
		var cols = [];
		//成立公告
		if(repId == CHB_DISCLOSURE_REPORT_01) {
			cols = [
				{field:'prodIdName', title: '产品名称（代码）', width:400},
				{field:'endDateFmt', title: '成立日期', width:140},
				{field:'createStatus', title: '生成状态', width:130},
				{field:'createTimeFmt', title: '生成时间', width:180},
				{field:'prodName', title: 'prodName', sortable: false, visible:false},
				{field:'endDate', title: 'endDate', sortable: false, visible:false},
				{field:'prodId', title: 'prodId', sortable: false, visible:false}
			];
		}
		//非标投资报告
		else if(repId == CHB_DISCLOSURE_REPORT_02) {
			cols = [
				{field:'prodIdName', title: '产品名称（代码）', width:400},
				{field:'endDateFmt', title: '变动日', width:140},
				{field:'createStatus', title: '生成状态', width:130},
				{field:'createTimeFmt', title: '生成时间', width:180},
				{field:'prodName', title: 'prodName', sortable: false, visible:false},
				{field:'endDate', title: 'endDate', sortable: false, visible:false},
				{field:'prodId', title: 'prodId', sortable: false, visible:false}
			];
		}
		//到期公告
		else if(repId == CHB_DISCLOSURE_REPORT_03) {
			cols = [
				{field:'prodIdName', title: '产品名称（代码）', width:400},
				{field:'endDateFmt', title: '到期日', width:140},
				{field:'createStatus', title: '生成状态', width:130},
				{field:'createTimeFmt', title: '生成时间', width:180},
				{field:'prodName', title: 'prodName', sortable: false, visible:false},
				{field:'endDate', title: 'endDate', sortable: false, visible:false},
				{field:'prodId', title: 'prodId', sortable: false, visible:false}
			];
		}
		//定期公告
		else if(repId == CHB_DISCLOSURE_REPORT_04) {

		}
		//净值披露
		else if(repId == CHB_DISCLOSURE_REPORT_05){
			cols = [
				{field:'prodIdName', title: '产品名称（代码）', width:400},
				{field:'endDateFmt', title: '净值日期', width:140},
				{field:'createStatus', title: '生成状态', width:130},
				{field:'createTimeFmt', title: '生成时间', width:180},
				{field:'prodName', title: 'prodName', sortable: false, visible:false},
				{field:'endDate', title: 'endDate', sortable: false, visible:false},
				{field:'prodId', title: 'prodId', sortable: false, visible:false}
			];
		}
		//默认
		else {
			cols = [
				{field:'prodIdName', title: '产品名称（代码）', width:400},
				{field:'endDateFmt', title: '日期', width:140},
				{field:'createStatus', title: '生成状态', width:130},
				{field:'createTimeFmt', title: '生成时间', width:180},
				{field:'prodName', title: 'prodName', sortable: false, visible:false},
				{field:'endDate', title: 'endDate', sortable: false, visible:false},
				{field:'prodId', title: 'prodId', sortable: false, visible:false}
			];
		}
		return cols;
	};

	/**
	 * 数据展示
	 * @type {jQuery}
	 */
	var detailTable = null;
	var initDetailTable = function() {
		detailTable = $("#detailTable").joyintable({
			ajax: {
				url: 'queryDetailData',
				type: 'POST',
				data: function() {
					return getOptParams();
				}
			},
			searchOnInit: false,
			cellMinWidth:120 ,
			checkbox:true,
			cols: getCols()
		});
	}

	var createDoc = function() {
		var obj = getOptParams();
		if(repId == CHB_DISCLOSURE_REPORT_04) {
			if($('#reportType').val() == null || $('#reportType').val() == "") {
				Joyin.error("报告类型！");
				return;
			}
			if($('#year').val() == null || $('#year').val() == "") {
				Joyin.error("请先选择查询年份！");
				return;
			}
		}
		else {
			if (detailTable != null && detailTable != '') {
				var selArr = detailTable.getAllCheckedRows();
				if(selArr == null || selArr.length == 0) {
					Joyin.error("请先勾选要生成的数据！");
					return;
				}
				else {
					var datas = JSON.stringify(selArr);
					obj.datas = datas;
				}
			}
			else {
				Joyin.error("请先勾选要生成的数据！");
				return;
			}
		}
		Joyin.ajaxJson(
			"check",
			obj,
			function(result) {
				if (result) {
					Joyin.ajax(
							basePath + 'svreport/chbreport/reportgenerate/ChbReportGenerate001/createReportDoc',
							obj,
							function(result) {
								if(repId == CHB_DISCLOSURE_REPORT_04) {
								}
								else {
									detailTable.reload();
								}
							});
				}
			});
	};

	// var exportDoc = function() {
	// 	var obj = getOptParams();
	// 	Joyin.ajaxJson(
	// 		"check",
	// 		obj,
	// 		function(result) {
	// 			if (result) {
	// 				Joyin.exportExcel('exportReportDoc', obj);
	// 			}
	// 		});
	// };
	var queryData =  function() {
		//成立公告
		if(repId == CHB_DISCLOSURE_REPORT_01) {
			if($('#endDate').val() == null || $('#endDate').val() == "") {
				Joyin.error("请先选择成立日期！");
				return;
			}
		}
		//非标投资报告
		if(repId == CHB_DISCLOSURE_REPORT_02) {
			if($('#endDate').val() == null || $('#endDate').val() == "") {
				Joyin.error("请先选择变动日！");
				return;
			}
		}
		//到期公告
		if(repId == CHB_DISCLOSURE_REPORT_03) {
			if($('#endDate').val() == null || $('#endDate').val() == "") {
				Joyin.error("请先选择到期日！");
				return;
			}
		}
		//定期公告
		if(repId == CHB_DISCLOSURE_REPORT_04) {

		}
		//净值披露
		if(repId == CHB_DISCLOSURE_REPORT_05){
			if($('#endDate').val() == null || $('#endDate').val() == "") {
				Joyin.error("请先选择净值日期！");
				return;
			}
			if($('#prodId').val() == null || $('#prodId').val() == "") {
				Joyin.error("请先选择产品代码！");
				return;
			}
		}
		detailTable.load();
	}
	/**
	 * 页面加载完后执行
	 */
	$(function() {
		dataTable.load();//加载左侧列表
		// 绑定按钮事件
		$('#ChbReportGenerate001_btn_add').click(function () {
			createDoc();
		});
		// $('#ChbReportGenerate001_btn_export').click(function () {
		// 	exportDoc();
		// });
		$('#ChbReportGenerate001_btn_search').click(function () {
			queryData();
		});
		layui.laydate.render({
			elem: '#year',
			type: 'year',
			format: 'yyyy'
		});

	});

});

