/**
 * 佣金设置查询
 */
(function() {
	/**
	 * 获取查询参数
	 */
	var getSearchParams = function() {
		var objDiv= $("#AssetDeclarationCodeMaintenance001_DIV");
		var object = $.serializeToObject('#assetDeclarationCodeForm');
		object.assetsType = $('#assetsType').val() == null ? $('#assetsType').val() : $('#assetsType').val().join(',');
		object.finprodId = $('#finprodId').val() == null ? $('#finprodId').val() : $('#finprodId').val().join(',');
		object.othCodeType = $('#othCodeType').val() == null ? $('#othCodeType').val() : $('#othCodeType').val().join(',');
		object.vdateStart = objDiv.find("input[name='vdate']").startDate();//起息日
		object.vdateEnd = objDiv.find("input[name='vdate']").endDate();//起息日
		return object;
	};
	// 新建
	function showDialog() {
		Joyin.dialog(JoyinUtil.getLabel('AssetDeclarationCodeMaintenance002'), basePath
				+ 'invest/basicdata/assetdeclarationcodemaintenance/AssetDeclarationCodeMaintenance002/init',
				'70%', '65%', {
					pageModel : PAGE_MODEL_NEW
				}, function() {
					table.reload();
				});
	}

	/**
	 * 定义数据表格
	 */
	var table = $("#dataTable").joyintable({
						ajax : {
							url : 'findPage',
							type : 'POST',
							data : function() {
								return getSearchParams();
							}
						},
						dblclick : function(table, data) {
							data.valueDate = new Date(data.valueDate).format();
							data.pageModel = PAGE_MODEL_REFERENCE;
							data.operateType = 'detail';
							console.log(data);
							Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
											JoyinUtil.getRealPath("invest/basicdata/assetdeclarationcodemaintenance/AssetDeclarationCodeMaintenance002/init"),
											"70%", "65%", data, function() {
												table.reload();
											});
						},

						cols : [
						{
							field : 'branch',
							visible : false,
							width : 150
						},
						// modify by dubaojun 2020-07-30 画面资产代码展示为市场代码 start
                        {
                            field : 'finprodId',
                            visible : false
                        }, {
							field : 'assetsTypeName',
							title : 'AssetDeclarationCodeMaintenance001.assetsType',
							sortField : 'assetsType',
							width : 200
						}, {
							field : 'finprodMarketId',
							title : 'AssetDeclarationCodeMaintenance001.finprodId',
							sortField : 'finprodMarketId',
							width : 200
						},
							{
								field : 'finprodName',
								title : '资产名称',
								sortField : 'finprodName',
								width : 200
							},
						// modify by dubaojun 2020-07-30 画面资产代码展示为市场代码 end
						 {
							field : 'othCodeTypeName',
							title : '业务类型',
							sortField : 'othCodeType',
							width : 200
						}, {
							field : 'othCode',
							title : 'AssetDeclarationCodeMaintenance001.othCode',
							sortField : 'othCode',
							width : 200
						},
							{
								field : 'vdateStart',
								title : '申报起始日',
								sortField : 'vdateStart',
								width : 200,
								render : function(data, type, row, meta) {
									if (row.othCodeType == "02")
										return null;
									else
										return row.vdateStart;
								}
							},
							{
								field : 'vdateEnd',
								title : '申报终止日',
								sortField : 'vdateEnd',
								width : 200,
								render : function(data, type, row, meta) {
									if (row.othCodeType == "02")
										return null;
									else
										return row.vdateEnd;
								}
							},
							{
								field : 'assetsPrice',
								title : '申报价格',
								sortField : 'assetsPrice',
								width : 200,
								render : function(data, type, row, meta) {
									if (row.othCodeType == "02")
										return null;
									else
										return row.assetsPrice;
								}
							},
							{
							field : 'action',
							title : '操作',
							width : 200,
							type : 'action',
							buttons : ['delt' ]
						} ],
					});

	var exportExcel = function() {
		Joyin.exportExcel('export', getSearchParams());
	};

	/**
	 * 绑定编辑事件
	 */
	table.bind('click','.edit',function(data, index) {
						data.pageModel = PAGE_MODEL_EDIT;
						data.operateType = 'edit';
						Joyin.dialog(JoyinUtil.getLabel("AssetDeclarationCodeMaintenance001.edit"),
															basePath+ "invest/basicdata/assetdeclarationcodemaintenance/AssetDeclarationCodeMaintenance002/init",
															"70%", "65%", data,
															function() {
																table.reload();
															});
						});
	/**
	 * 绑定删除事件
	 */
	table.bind('click', '.delt', function(data, index) {
			var object = {
				finprodId : data.finprodId,
			    othCodeType : data.othCodeType,
			    operateType : 'delete'
			};
			Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function() {
				Joyin.ajaxJson(basePath + 'invest/basicdata/assetdeclarationcodemaintenance/AssetDeclarationCodeMaintenance001/save', object, function(result) {
					table.reload();// 表格删除行
				});
			}, function() {
			})
		});

	$(function() {
		// 绑定按钮事件
		$('#AssetDeclarationCodeMaintenance001_btn_search').click(function() {
			table.load();
			// $('#AssetDeclarationCodeMaintenance001_btn_edit').show();
			// $('#AssetDeclarationCodeMaintenance001_btn_delete').show();
		});
		$('#AssetDeclarationCodeMaintenance001_btn_import').click(function() {
			Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("AssetDeclarationCodeMaintenance001.import")), function() {
			Joyin.uploadFile(function(fileName) {
                Joyin.ajax(basePath +"invest/basicdata/assetdeclarationcodemaintenance/AssetDeclarationCodeMaintenance001/importFile", {
                    fileName : fileName
                },function(result){
                    if(!result || !($.isArray(result) && result.length > 0)) {
                        layui.layer.closeAll()
                        table.reload();
                    }
                });
            });
		  });
		});
		$('#AssetDeclarationCodeMaintenance001_btn_export').click(function() {
			exportExcel();
		});
		$('#AssetDeclarationCodeMaintenance001_btn_add').click(function() {
			showDialog();
		});
		$('#AssetDeclarationCodeMaintenance001_btn_edit').click(
                function() {
                    if (table != null && table != '') {
                        var selArr = table.getAllCheckedRows();
                    }
                    if (!selArr || selArr.length == 0 || selArr.length > 1) {
                        Joyin.error(JoyinUtil.getMessage("请从查询列表中选中一条数据，且不支持多选！"));
                        return;
                    }
                    var object = selArr[0];
                    object.pageModel = PAGE_MODEL_EDIT;
                    object.operateType = "edit";
                    Joyin.dialog(JoyinUtil.getLabel("AssetDeclarationCodeMaintenance001.edit"),
							basePath + "invest/basicdata/assetdeclarationcodemaintenance/AssetDeclarationCodeMaintenance002/init",
							"60%", "50%", object,
							function() {
								table.reload();
							});          
                });
		
		$('#AssetDeclarationCodeMaintenance001_btn_delete').click(
                function() {
                    if (table != null && table != '') {
                        var selArr = table.getAllCheckedRows();
                    }
                    if (!selArr || selArr.length == 0) {
                        Joyin.error(JoyinUtil.getMessage("请至少选中一条数据进行删除！"));
                        return;
                    }
                    var object = {selectedItem: selArr, operateType:"delete"};
                    Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function() {
        				Joyin.ajaxJson(basePath + 'invest/basicdata/assetdeclarationcodemaintenance/AssetDeclarationCodeMaintenance001/save', object, function(result) {
        					table.reload();// 表格删除行
        				});
        			}, function() {
        			})                   
                });
});
}());