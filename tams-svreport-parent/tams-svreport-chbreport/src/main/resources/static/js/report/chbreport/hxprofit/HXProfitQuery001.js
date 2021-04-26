$(function(){
	// 获取查询参数
	var getSearchParams = function(){
		return {
			reportDate:$('#month_reportDate').val()
		};
	}
	// 定义数据表格
	var table=$("#dataTable").joyintable({
		ajax: {
			url: 'searchPage',
			type: 'POST',
			data: function() {
				return getSearchParams();
			}
		},
		showDetail: true,//双击详情对比画面
		rowno: true, // 显示行号
		searchOnInit: false, //初始化的时候就请求后台数据
		cols:[
			{ field: 'registSeqno',visible:false},
			// { title: "序号", field: 'statusStr',width:150,sortField:'status'},
			{ title: "报告期", field: 'reportDate',width:150,sortField:'reportDate'},
			{ title: "操作", field: 'action',width:150,sortField:'action'},
			{field:'sendStatus', visible:false},
			{field:'status', visible:false},
			{field:'submitFeedbackStatus', visible:false}
		]
	})

	// 查询按钮
	$('#HXProfitQuery001_btn_search').click(function(){
		table.load();
	})

	// 导出按钮
	// $('#FinProfitQuery001_btn_export').click(function(){
	// 	var expArr=table.getAllCheckedRows();
	// 	if(expArr != null && expArr.length >0){
	// 		Joyin.confirm('确认导出选中信息？',function(){
	// 			exportExcel(1);
	// 		})
	// 	}else{
	// 		Joyin.confirm('确认导出查询结果？',function(){
	// 			exportExcel(0);
	// 		})
	// 	}
	// })
	/**
	 * 导出
	 * flag:0 全部导出，选中导出
	 */
	var exportExcel = function() {
		if (table != null && table != '') {
			var selArr = table.getAllCheckedRows();
			var datas = JSON.stringify(selArr);
		}
		if (!selArr || selArr.length == 0) {
			var paramObj = getSearchParams();
			Joyin.exportExcel('export', paramObj);
		} else {
			Joyin.exportExcel('export', {
				datas : datas
			});
		}
	};
	// var exportExcel = function(flag) {
	// 	if(flag == 0){
	// 		var param = getSearchParams();
	// 		Joyin.exportExcel("export",param);
	// 	}else{
	// 		var param = {};
	// 		var expArr=table.getAllCheckedRows();
	// 		var keyList = '';
	// 		layui.each(expArr,function(index,data){
	// 			if(index == expArr.length-1){
	// 				keyList = keyList + data.registSeqno+'';
	// 			}else{
	// 				keyList = keyList + data.registSeqno+',';
	// 			}
	// 		})
	// 		param.keyList = keyList;
	// 		Joyin.exportExcel("export",param);
	// 	}
	//
	// };


	function init(){
		layui.laydate.render({
			elem: '#month_reportDate',
			type: 'month',
			format: 'yyyy-MM'
		});
		$("#month_reportDate").click(function(event){
			event.stopPropagation();// 阻止事件冒泡
		});
		// 绑定按钮事件
		$('#SvrHXProfitQuery001_btn_search').click(function() {
			table.load();
		});
		$('#SvrHXProfitQuery001_btn_export').click(function() {
			exportExcel();
		});

	};

	init();

})