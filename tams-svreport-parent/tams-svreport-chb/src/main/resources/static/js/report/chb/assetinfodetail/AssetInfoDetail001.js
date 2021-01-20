(function() {
	var getSearchParams = function() {
		return {
			// 资产/负债类别
			assetType : $('#assetType').val() == null ? null : $(
				'#assetType').val().join(","),
			// 资产/负债二级类别
			assetType2 : $('#assetType2').val() == null ? null : $(
				'#assetType2').val().join(","),
			// 资产一级分类
			orgAssetType1 : $('#orgAssetType1').val() == null ? null : $(
				'#orgAssetType1').val().join(","),
			//资产二级分类
			orgAssetType2 : $('#orgAssetType2').val() == null ? null : $(
				'#orgAssetType2').val().join(","),
			// 行内资产编码
			assetCode : $('#assetCode').val() == null ? null : $(
				'#assetCode').val().join(","),
			//反馈状态
			dataStatus: $('#dataStatus').val()
		}
	};

	/**
	 * 定义数据表格
	 */
	var table = $('#dataTable').joyintable({
		ajax: {
			url: 'searchPage',
			type: 'POST',
			data: function() {
				return getSearchParams();
			}
		},
		rowno:false,
		cols:[
			{ field: 'assetUuid',width:0,sortField:'',visible:false},
			{ title: "反馈状态", field: 'dataStatusStr',width:80 ,sortField:'dataStatus'},
			{ title: "资产代码", field: 'assetCode',width:180 ,sortField:'assetCode'},
			{ title: "资产名称", field: 'assetName',width:300 ,sortField:'assetName'},
			{ title: "资产一级类型",field:'orgAssetType1Str', width:120,sortField:'orgAssetType1'},
			{ title: '资产二级分类', field:'orgAssetType2Str', width:120,sortField:'orgAssetType2'},
			{ title: "起息日",width:100,sortField:'vDateStr', field: 'vDateStr'},
			{ title: "到期日",width:100,sortField:'mDateStr', field: 'mDateStr'},
			{ title: '资产/负债类别', field:'assetTypeStr', width:180,sortField:'assetType'},
			{ title: '资产/负债二级类别', field:'assetType2Str', width:130,sortField:'assetType2'},
			{ title: '错误信息', field:'errMsg', width:130,sortField:'errMsg'},
			{ field:'action', title: '操作' , type:'action',buttons: ['edit', 'delt'] , width:100}
		]
	});

	/**
	 * 绑定编辑事件
	 */
	table.bind('click', '.edit', function(data, index) {
		data.operateType = "edit";
		data.pageModel="edit";
		Joyin.dialog(JoyinUtil.getLabel("资产基本信息修改"),
			basePath + "chb/assetinfodetail/AssetInfoDetail002/init",
			"95%", "90%", {pageModel:"edit",
				assetUuid:data.assetUuid,dataStatus:data.dataStatus},function(){table.reload();});
		// });
	});
	/**
	 * 绑定删除事件
	 */
	table.bind('click', '.delt', function(data, index) {
		var object = {assetUuid:data.assetUuid,dataStatus:data.dataStatus, operateType:"edit"};
		Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
			Joyin.ajax(basePath +'chb/assetinfodetail/AssetInfoDetail001/delete',object, function(result){
				table.reload();
			});
		});
	});

	$(function() {
		// 查询按钮点击事件
		$('#AssetInfoDetail001_btn_search').click(function() {
			table.load();
		});

		$('#AssetInfoDetail001_btn_add').click(function(){
			Joyin.dialog(JoyinUtil.getLabel("资产基本信息新增"),
				basePath + "chb/assetinfodetail/AssetInfoDetail002/init",
				"95%", "90%", {pageModel:"add",},
				function(){table.reload();});
		});


		// 导出按钮
		$('#AssetInfoDetail001_btn_export').click(function(){
			var expArr=table.getAllCheckedRows();
			if(expArr != null && expArr.length >0){
				Joyin.confirm('确认导出选中信息？',function(){
					exportExcel(1);
				})
			}else{
				Joyin.confirm('确认导出查询结果？',function(){
					exportExcel(0);
				})
			}
		})

		/**
		 * 导出
		 * flag:0 全部导出，选中导出
		 */
		var exportExcel = function(flag) {

			var selectedItem = table.getAllCheckedRows();
			var paramObj={};
			if(selectedItem.length==0){
				paramObj= getSearchParams();
			}
			else{
				//add by wm 针对特殊字符处理  jason 识别“'”字符时转换会有问题，将企转换为“&apos;” end
				paramObj.keyList = JSON.stringify(selectedItem);
			}
			Joyin.exportExcel('export', paramObj);
		};
	});
}());


