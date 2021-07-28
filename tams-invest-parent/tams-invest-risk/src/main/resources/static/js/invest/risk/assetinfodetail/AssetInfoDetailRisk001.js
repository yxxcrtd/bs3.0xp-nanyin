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
			{ title: "反馈状态", field: 'dataStatusStr',width:80 ,sortField:'dataStatus',visible:false},
			{ title: "行内资产/负债编码", field: 'assetCode',width:180 ,sortField:'asset_code'},
			{ title: "资产名称", field: 'assetName',width:300 ,sortField:'asset_name'},
			{ title: "资产一级类型",field:'orgAssetType1Str', width:120,sortField:'org_asset_type1',visible:false},
			{ title: '资产二级分类', field:'orgAssetType2Str', width:120,sortField:'org_asset_type2',visible:false},
			{ title: "起息日",width:100,sortField:'vDateStr', field: 'vDateStr',visible:false},
			{ title: "到期日",width:100,sortField:'mDateStr', field: 'mDateStr',visible:false},
			{ title: '资产/负债类别', field:'assetTypeStr', width:280,sortField:'asset_type'},
			{ title: '资产/负债二级类别', field:'assetType2Str', width:130,sortField:'asset_type2',visible:false},
			{ title: '错误信息', field:'errMsg', width:130,sortable:false,visible:false},
			{ title: '交易场所', field: 'tradeMarketStr',width:180 ,sortField:'TRADE_MARKET'},
			{ title: '币种', field: 'ccyStr',width:180 ,sortField:'ccy'},
			{ field:'action', title: '操作' , type:'action',buttons: ['edit', 'delt'] , width:180}
		]
	});

	/**
	 * 绑定编辑事件
	 */
	table.bind('click', '.edit', function(data, index) {
		data.operateType = "edit";
		data.pageModel="edit";
		Joyin.dialog(JoyinUtil.getLabel("资产要素信息修改"),
			basePath + "invest/risk/assetinfodetail/AssetInfoDetailRisk002/init",
			"95%", "90%", {pageModel:PAGE_MODEL_EDIT,
				assetUuid:data.assetUuid,dataStatus:data.dataStatus},function(){table.reload();});
		// });
	});
	/**
	 * 绑定删除事件
	 */
	table.bind('click', '.delt', function(data, index) {
		var object = {assetUuid:data.assetUuid,assetType2:data.assetType2,assetCode:data.assetCode,operateType:"edit"};
		Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
			Joyin.ajax(basePath +'invest/risk/assetinfodetail/AssetInfoDetailRisk001/delete',object, function(result){
				table.reload();
			});
		});
	});

	$(function() {
		// 查询按钮点击事件
		$('#AssetInfoDetailRisk001_btn_search').click(function() {
			table.load();
		});

		$('#AssetInfoDetailRisk001_btn_add').click(function(){
			Joyin.dialog(JoyinUtil.getLabel("资产要素信息新增"),
				basePath + "invest/risk/assetinfodetail/AssetInfoDetailRisk002/init",
				"98%", "98%", {pageModel:PAGE_MODEL_NEW,},
				function(){table.reload();});
		});


		// 导入按钮
		$('#AssetInfoDetailRisk001_btn_import').click(function() {
			Joyin.confirm("请确认是否要导入数据？", function() {
				var options = {};
				options.pageId = "AssetInfoDetailRisk001_2";
				options.exts = 'zip|xls|xlsx';
				Joyin.uploadFile(options,function(fileName) {
					Joyin.ajax(basePath + 'invest/risk/assetinfodetail/AssetInfoDetailRisk001/checkConfirmMsgForImportData', {fileName: fileName}, function() {
						Joyin.ajax(basePath + 'invest/risk/assetinfodetail/AssetInfoDetailRisk001/importFile', {fileName: fileName},function () {
							table.reload();
						});
					});
				},function() {
					table.reload();
				});
			});
		});
		
		// 导出按钮
		$('#AssetInfoDetailRisk001_btn_export').click(function(){
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


