$(function(){
	// 获取查询参数
	var getSearchParams = function(){
		return {
			prodId : $('#prodId').val() == null ? null : $('#prodId').val().join(","),
			dealdateStart : $('#dealdate').startDate(),
			dealdateEnd : $('#dealdate').endDate()
		};
	}
	// 定义数据表格
	var table=$("#dataTable").joyintable({
		ajax: {
			url: 'findPage',
			type: 'POST',
			data: function() {
				return getSearchParams();
			}
		},
		rowno: true, // 显示行号
		searchOnInit: false, //初始化的时候就请求后台数据
		cols:[
			{ title: "产品代码", field: 'prodId',width:120},
			{ title: "产品名称", field: 'prodName',width:150},
			{ title: "变动日", field: 'dealdateFmt',width:130,sortField:'dealdate'},
			{ title: "理财信息系统登记编码", field: 'regCode',width:180},
			{ title: "投资性质", field: 'ivstorientationFmt',width:150,sortField:'ivstorientation'},
			{ title: "投资及收益币种", field: 'cashPrinCcyFmt',width:150,sortField:'cashPrinCcy'},
			{ title: "产品风险等级", field: 'riskLevelFmt',width:150,sortField:'riskLevel'},
			{ title: "产品成立日", field: 'vdateFmt',width:150,sortField:'vdate'},
			{ title: "产品管理人", field: 'managerName',width:130,sortField:'dealdate'},
			{ title: "产品托管人", field: 'depName',width:180},
			{field:'dealdate',  title: "dealdate", visible:false}
			//{ title: "操作", field: 'action',width:150,sortField:'action'},
		]
	})

	/**
	 * 导出
	 */
	var exportExcel = function() {
		var datas = null;
		if (table != null && table != '') {
			var selArr = table.getAllCheckedRows();
			datas = JSON.stringify(selArr);
		}
		if (!selArr || selArr.length == 0) {
			Joyin.alert("请选择要导出的数据！", 7,null, null);
		} else {
			Joyin.exportExcel('createDoc', {
				datas : datas
			});
		}
	};

	$(function(){
		$('#ChbInvestOperation001_btn_search').click(function() {
			table.load();
		});
		$('#ChbInvestOperation001_btn_export').click(function() {
			exportExcel();
		});
	});

})