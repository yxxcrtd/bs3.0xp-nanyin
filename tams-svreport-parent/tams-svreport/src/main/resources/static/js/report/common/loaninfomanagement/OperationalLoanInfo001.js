	var colList = [];
	// 已选择字段列表
	var selectedColumns = [];
	//数据源表触发事件
	function getColumns() {
		var tableId = $('#baseTableName').val();
		$("#columnType").empty();
		$("#resultColumn").empty();
		$("#selectedColumns").empty();
		var table = layui.table;
		var cols = [];
		var i = 1;
		$.ajax({
			url : basePath + "common/infomanagement/SvrOperationalInfo001/queryColmnInfo",
			async : false,
			type : 'POST',
			traditional : true,
			data : {
				tableId : $('#baseTableName').val(),
				isSingleEdit : '01',
			},
			success : function(result) {
				var jsonObj = eval("(" + result + ")");
				cols[0] = {
					type : 'checkbox',
					fixed : 'left'
				};
				var resultData = [];
				for ( var key in jsonObj) {
					cols[i] = {
						field : key,
						title : jsonObj[key],
						width : 250
					};
					i++;
					var curData = {"id": key, "text": jsonObj[key]};
					resultData.push(curData);
				}
				//type : 'action',field:'operation'
				// var operation = {fixed : 'right', title: '操作', width : 130, align:'center', toolbar: '#buttonDemo'};
				// cols.push(operation);
				
				$("#resultColumn").select2({
			        placeholder: '请选择字段',
			        multiple: true,
			        data: resultData,
			        allowClear:true,
			    });
				colList = cols;
				layui.use([ 'table' ], function() {
					var table = layui.table;
					table.render({
						elem : '#dataTable',
						cols : [ cols ]
					});
				});
			}
		});
		Joyin.ajax( basePath + "common/infomanagement/SvrOperationalInfo001/queryColumnByTableId",
			{tableId : tableId}, function(result) {
				if (result.lenght == 0) {
					return;
				}
				var jsonObj = eval("(" + result + ")");
				var initData = [];
				for (var key in jsonObj) {
					var curData = {"id": key, "text": jsonObj[key]};
					initData.push(curData);
				}
				$("#columnType").select2({
			        placeholder: '请选择字段',
			        multiple: true,
			        data: initData,
			        allowClear:true,
			    });
		});
	}
	
	
	function setData(filterId) {
		var valueList = [];
		var initData = [];
		$("#columnType").val(valueList).trigger("change");
		$.ajax({
			url : basePath
					+ "common/infomanagement/SvrOperationalInfo001/queryPlanByfilterId",
			async : false,
			data : {
				filterId : filterId
			},
			success : function(result) {
				colunmList = eval("(" + result + ")");
				var tableId = colunmList['tableName'];
				// 数据源表已保存配置
				$('#baseTableName').val(tableId).trigger("change");
				for ( var key in colunmList) {
					if ("tableName" != key) {
						valueList.push(key);
					}
				}
				$.ajax({
					url : basePath + "common/infomanagement/SvrOperationalInfo001/queryColumnByTableId",
					async : false,
					data : {
						tableId : tableId
					},
					success : function(result) {
						$("#columnType").empty();
						var jsonObj = eval("(" + result + ")");
						for ( var key in jsonObj) {
							var curData = {"id": key, "text": jsonObj[key]};
							initData.push(curData);
						}
					}
					
				});
				$("#columnType").select2({
				     placeholder: '请选择字段',
				     multiple: true,
				     data: initData,
				     allowClear:true
				});
				$("#columnType").val(valueList).trigger("change");
			}
		});
	}
	
	//选择字段下拉框事件
	function showSelectColumns() {
		var tableId = $('#baseTableName').val();
		var columnIds = $('#columnType').val();
		if (typeof (columnIds) == "undefined" || columnIds == null
				|| columnIds == "") {
			$("#selectedColumns").hide();
			$('#selectedColumns').html("");
			selectedColumns = [];
			return;
		};
		// 当前需要操作字段
		var curColumns = [];
		// 更新时间默认值
		var defaultDate = '';
		// 当前操作字段名称
		var curColumnName;
		var id;
		// 新增选项
		if (columnIds.length > selectedColumns.length) {
			for (var i = 0; i < columnIds.length; i ++) {
				if (selectedColumns.indexOf(columnIds[i]) == -1) {
					curColumns.push(columnIds[i]);
				}
			}
			for (var i = 0; i < curColumns.length; i ++) {
			var	curColumn = curColumns[i];
			$.ajax({
				url : basePath + "common/infomanagement/SvrOperationalInfo001/queryColumnNameByCondition",
				async : false,
				data : {
					tableId : tableId,
					curColumn : curColumn
				},
				success : function(result) {
					curColumnName = result;
				}
			});
			
			$.ajax({
				url : basePath + "common/infomanagement/SvrOperationalInfo001/queryColumnTypeByCondition",
				async : false,
				data : {
					tableId : tableId,
					curColumn : curColumn
				},
				success : function(result) {
					$("#selectedColumns").show();
					if(i%3 ==0){
						var div = document.createElement("div");
						div.class ="layui-form-item";
						div.className = "layui-form-item";
						div.id = "div" + i;
						id = "div" + i;
						$("#selectedColumns").append(div);
					}
					if (result == 'T') {
						$("#selectedColumns").children("#"+id)
							.append(
								"<div class=\"layui-inline layui-col-md4\" id=\""
								+ tableId
								+ "-"
								+ curColumn
								+	"\">"
								+ " <label class=\"layui-form-label form-lable\">"
								+ curColumnName
								+ "</label><div class=\"layui-input-inline input-inline\">"
								+ "<input id=\""
								+ tableId
								+ "-"
								+ curColumn
								+ "-T"
								+ "\" placeholder=\"请输入\""
								+ "\" class=\"layui-input form-input input-lenovoselect\" >"
								+ "</div></div>");
					} else if (result == 'S') {
						var colunmInofList = queryColumnInfoList(
							tableId, curColumn);
						var content = "<div class=\"layui-inline layui-col-md4\" id=\""
							+ tableId
							+ "-"
							+ curColumn
							+	"\">"
							+ "<label class=\"layui-form-label form-lable\">"
							+ curColumnName
							+ "</label>"
							+ "<div class=\"layui-input-inline input-inline\">"
							+ "<select type=\"text\" id=\""
							+ tableId
							+ "-"
							+ curColumn
							+ "-S"
							+ "\" name=\"columnType\""
							+ "\" placeholder=\"请选择\""
							+ " class=\"layui-input form-input input-select\" "
							+ "allowClear=\"true\" multiple=\"multiple\">"
						for ( var key in colunmInofList) {
							content += "<option value='" + key
								+ "'>" + colunmInofList[key]
								+ "</option>"
						}
						content += "</select></div></div>"
						$("#selectedColumns").children("#"+id).append(content);
						$("#selectedColumns").children("#"+id).find('select')
							.textSelect();
					} else if (result == 'D') {
						$("#selectedColumns").children("#"+id)
							.append(
								"<div class=\"layui-inline layui-col-md4\" id=\""
								+ tableId
								+ "-"
								+ curColumn
								+	"\">"
								+ "<label class=\"layui-form-label form-lable\">"
								+ curColumnName
								+ "</label>"
								+ "<div class=\"layui-input-inline input-inline\" id=\"test1\">"
								+ "<input type=\"text\"  name=\"timeControl\"  "
								+ "class=\"layui-input form-input input-dateRangeSelect\" id=\""
								+ tableId
								+ "-"
								+ curColumn
								+ "-D"
								+ "\" placeholder=\"请选择日期区间\" />"
								+ "<div class=\"time-icon\"><i class=\"iconfont icon-date\"></i></div></div></div>");
						var laydate = layui.laydate;
						laydate.render({
							elem : $("#" + tableId + "-"
								+ curColumn + "-D")[0],
							range : true,
							value:defaultDate // 设置默认值
						});
					}
				}
			});
			}
			selectedColumns = columnIds;
			// 设置接口类型查询框默认值
			$('#CHB_INTERF_ERRODATA-INTERF_TYPE-T').val(interfType==null?'':interfType);
			// 触发查询按钮
			$('#loanplan_btn_search').trigger('click')
		};
		// 删除选项
		if (selectedColumns.length > columnIds.length) {
			for (var i = 0; i < selectedColumns.length; i ++) {
				if (columnIds.indexOf(selectedColumns[i]) == -1) {
					$("#" + tableId + "-" + selectedColumns[i]).remove();
				}
			}
			selectedColumns = columnIds;
		}
	}
	
	var getSearchParams = function() {
		var columnId = getColumnParams();
		return {
			tableId : $('#baseTableName').val(),
			columnIds : columnId
		}
	};

	var getColumnParams = function() {
		var tableId = $('#baseTableName').val();
		var colunmList = '';
		$.ajax({
			url : basePath + "common/infomanagement/SvrOperationalInfo001/queryColumnTypeByTableId",
			async : false,
			data : {
				tableId : tableId
			},
			success : function(result) {
				var jsonObj = eval("(" + result + ")");
				for ( var key in jsonObj) {
					var columnInfo = key.split(",");
					var columnId = tableId + "-" + columnInfo[0] + "-"
							+ jsonObj[key];
					if ($("#" + columnId).length > 0) {
						var obj = '';
						if (jsonObj[key] == 'T') {
							obj = columnInfo[0] + "+" + "T" + "+"
									+ $("#" + columnId).val();
						} else if (jsonObj[key] == 'S') {
							var sdata = $("#" + columnId).val();
							if (sdata != null) {
								obj = columnInfo[0] + "+" + "S" + "+"
										+ sdata.join(",");
							}

						} else if (jsonObj[key] == 'D') {
							var dates = $("#" + columnId).val();
							if (!(typeof (dates) == "undefined"
									|| dates == null || dates == "")) {
								var dateList = dates.split(" - ");
								obj = columnInfo[0] + "+" + "D" + "+"
										+ dateList[0] + ","
										+ dateList[1];
							}
						}
						colunmList = colunmList + obj + ";";
					}
				}
			}
		});
		return colunmList;
	};
	
	//监听工具条 
	layui.table.on('tool(dataTable)', function(obj){
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
	 
		if(layEvent === 'edit'){ //行内编辑按钮事件
			if(data.STATUS != "E:有效"){
	            return Joyin.error("只能编辑有效数据，请重新选择");
	        }
			data.pageModel = PAGE_MODEL_EDIT;
			data.operateType = "edit";
//			Joyin.ajaxJson(basePath + 'common/loaninfomanagement/OperationalLoanInfo001/CHB_LOAN_DETAIL_INFO/check', data, function(result) {
				Joyin.dialog(JoyinUtil.getLabel("OperationalLoanInfo001.edit"),
						basePath + "svreport/loaninfomanagement/OperationalLoanInfo002/init",
						"98%", "95%", data, function() {
					table.reload();
				});
//			});
		} else if(layEvent === 'del'){ // 行内删除事件
			if(data.STATUS != "E:有效"){
	            return Joyin.error("只能删除有效数据，请重新选择");
	        }
//	        Joyin.ajaxJson('check', data, function(result){
//	            if(result){
			var param = {};
			param.ASSET_UUID = data.ASSET_UUID
            Joyin.confirm("请确认是否要删除选中记录？", function() {
                Joyin.ajax(basePath + "common/loaninfomanagement/OperationalLoanInfo001/delete", param, function() {
                	table.reload();
                });
            });
//	            }
//	        });
		}
	});
	
	var table = $("#dataTable").joyintable({
		ajax : {
			url : 'findPage',
			type : 'POST',
			traditional : true,
			data : function() {
				return getSearchParams();
			},
		},
		cols : colList.length != 0 ? [ colList ] : null,
		page : true
	});

	function showTables() {
		var h = $("#selectedColumns").outerHeight(true);
		var divheight = 110 + h;
		var tableId = $('#baseTableName').val();
		var columnId = getColumnParams();
		layui.use([ 'table' ], function() {
			var table = layui.table;
			table.render({
				elem : '#dataTable',
				type : 'POST',
				where : {
					tableId : $('#baseTableName').val(),
					columnIds : columnId
				},
				url : basePath + "common/loaninfomanagement/OperationalLoanInfo001/findPage",
				page : true,
				height : 'full-' + divheight,
				cols : [ colList ]
			});
		});
	}

	var queryColumnInfoList = function(tableId, columnId) {
		var colunmList;
		$.ajax({
			url : basePath + "common/infomanagement/SvrOperationalInfo001/queryColumnInfoList",
			async : false,
			data : {
				tableId : tableId,
				columnId : columnId
			},
			success : function(result) {
				colunmList = eval("(" + result + ")");
			}
		});
		return colunmList;
	}
	
	/**
	 * 页面加载完后执行
	 */
	$(function() {
		var interfType = $("#interfType").val();
		if (typeof (interfType) == "undefined" || interfType == null || interfType == "") {
			return;
		}
		$('#baseTableName').val(interfType).trigger("change");
		
		setData(filterId);
		showTables();
		// 查询按钮事件
		$('#loanplan_btn_search').click(function() {
			showTables();
		});
		
		//新建按钮绑定事件
	    $('#OperationalLoanInfo001_btn_add').click(function() {
	        Joyin.dialog(JoyinUtil.getLabel("新建"),
                JoyinUtil.getRealPath("/svreport/loaninfomanagement/OperationalLoanInfo002/init"),
                "98%", "95%", {pageModel:PAGE_MODEL_NEW},
                function(data){
                	table.reload();
                }); 
	    });
		
		// 导出按钮点击事件
		$('#loanplan_btn_export').click(function() {
			var table = layui.table;
			var checkStatus = table.checkStatus('dataTable');
			var selectedData = checkStatus.data;
			var jasonData = JSON.stringify(selectedData);
			var tableId = $('#baseTableName').val();
			var tableName = $('#baseTableName').val();
			var columnIds = getColumnParams();
			var param = {};
			param.tableId = tableId;
			param.columnIds = columnIds;
			param.jasonData = jasonData;
			param.tableName = tableName;
			if (typeof (tableId) == "undefined" || tableId == null
					|| tableId == "") {
				layer.alert('请选择数据源表', {
					icon : 2,
					skin : 'layer-ext-moon'
				});
				return;
			}
			Joyin.exportExcel(basePath + 'common/loaninfomanagement/OperationalLoanInfo001/export', param);

		});

		// 导入按钮点击事件
		$('#OperationalLoanInfo001_btn_import').click(function() {
			Joyin.confirm(JoyinUtil.getMessage('CHB_ISIMPORT'), function() {
				Joyin.uploadFile(/*{	
					hideTpl : true
			       },*/ function(fileName) {
					Joyin.ajax(basePath + 'common/loaninfomanagement/OperationalLoanInfo001/checkConfirmMsgForImportData', {fileName : fileName}, function(result) {
						if (result.length == 0) {
							Joyin.ajax(basePath + 'common/loaninfomanagement/OperationalLoanInfo001/importFile', {fileName : fileName}, function(result){
								Joyin.ajax(basePath + 'common/infomanagement/SvrOperationalInfo001/linkSvrReport', {fileName : fileName});
							});
						} 
					});
				}, function() {
					table.reload();
				});
			});
		});
		
		//删除按钮点击事件
		$('#OperationalLoanInfo001_btn_delete').click(function(){
			var table = layui.table;
			// 选中行数据
			var checkStatus = table.checkStatus('dataTable');
			var selectedData = checkStatus.data;
			var jasonData = JSON.stringify(selectedData);
			var tableId = $('#baseTableName').val();
			var columnIds = getColumnParams();
			var param = {};
			param.tableId = tableId;
			param.columnIds = columnIds;
			param.jasonData = jasonData;
			if(selectedData.length == 0 || !selectedData){
				Joyin.error("请勾选一条数据删除！");
			}else{
				/*Joyin.confirm("请确认是否要删除选中记录？",function(){
					Joyin.ajax(basePath + "common/infomanagement/SvrOperationalInfo001/deleteData", param, function() {
						table.reload();
					});
				});*/
				Joyin.confirm("确认删除选定数据？", function() {
					Joyin.ajax(basePath+'common/infomanagement/SvrOperationalInfo001/deleteData', {
						jasonData : jasonData,
						tableId : tableId
					}, function(result) {
						if (result > 0) {
							layer.alert('成功删除' + result + '条数据', {
								icon : 1,
								skin : 'layer-ext-moon'
							});
							showTables();
							Joyin.ajax(basePath+'common/infomanagement/SvrOperationalInfo001/linkSvrReportAfterDelete', {
								jasonData : jasonData,
								tableId : tableId
							}, function(result) {

							});
						} else {
							layer.alert('没有删除数据', {
								icon : 2,
								skin : 'layer-ext-moon'
							});
						}
					});
				});
			}
		});

	});	
	