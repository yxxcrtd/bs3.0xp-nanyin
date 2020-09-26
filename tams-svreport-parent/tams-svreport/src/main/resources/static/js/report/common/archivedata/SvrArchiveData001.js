	var colList = [];
	// 左侧列表主键:方案Id --单击时使用
	var s_id = null;
	var indexName = null;
	var allLoadFalg = false;
	// 右侧表单数据是否加载过
	var allLoadFalg = false;

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
		$
				.ajax({
					url : basePath
							+ "svreport/common/archivedata/SvrArchiveData001/queryColumnTypeByTableId",
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

	var table = $("#dataTable").joyintable({
		ajax : {
			url : 'findPage',
			type : 'POST',
			traditional : true,
			data : function() {
				return getSearchParams();
			},
		},
		cols : [ colList ],
		page : true
	});

	// 已选择字段列表
	var selectedColumns = [];
	
	function getColumns() {
		var tableId = $('#baseTableName').val();
		$("#columnType").empty();
		$("#selectedColumns").empty();
		if (typeof (tableId) == "undefined" || tableId == null || tableId == "") {
			$("#columnType").empty();
			$("#selectedColumns").hide();
			$('#selectedColumns').html("");selectedColumns = [];
//			$("#dataTable").hide();
//			return;
		}
		var table = layui.table;
		var cols = [];
		var i = 1;
		// $("#dataTable").show();
		$
				.ajax({
					url : basePath
							+ "svreport/common/archivedata/SvrArchiveData001/queryColmnInfo",
					async : false,
					type : 'POST',
					traditional : true,
					data : {
						tableId : $('#baseTableName').val(),
					},
					success : function(result) {
						var jsonObj = eval("(" + result + ")");
						cols[0] = {
							type : 'checkbox',
							fixed : 'left'
						};
						for ( var key in jsonObj) {
							cols[i] = {
								field : key,
								title : jsonObj[key],
								width : 100
							};
							i++;
						}
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
		Joyin
				.ajax(
						basePath
								+ "svreport/common/archivedata/SvrArchiveData001/queryColumnByTableId",
						{
							tableId : tableId
						}, function(result) {
							

							if (result.lenght == 0) {
								return;
							}
							var jsonObj = eval("(" + result + ")");
							var initData = [];
							for ( var key in jsonObj) {
//								$("#columnType").append(
//										"<option value='" + key + "'>"
//												+ jsonObj[key] + "</option>");
								var curData = {"id": key, "text": jsonObj[key]};
								initData.push(curData);
								
							}
							$("#columnType").select2({
//						        tags: true,
						        placeholder: '请选择字段',
						        multiple: true,
						        data: initData,
						        allowClear:true,
						    });

							
						});
	}
	
	
	
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
				url : basePath + "svreport/common/archivedata/SvrArchiveData001/queryColumnNameByCondition",
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
				url : basePath + "svreport/common/archivedata/SvrArchiveData001/queryColumnTypeByCondition",
				async : false,
				data : {
					tableId : tableId,
					curColumn : curColumn
				},
				success : function(result) {
					$("#selectedColumns").show();
					if (result == 'T') {
						$("#selectedColumns")
						.append(
								"<div class=\"layui-inline layui-col-md3\" id=\""
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
										+ "\" class=\"layui-input form-input input-lenovoselect\" >"
										+ "</div>");
					} else if (result == 'S') {
						var colunmInofList = queryColumnInfoList(
								tableId, curColumn);
						var content = "<div class=\"layui-inline layui-col-md3\" id=\"" 
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
								+ " class=\"layui-input form-input input-select\" "
								+ "allowClear=\"true\" multiple=\"multiple\">"
						for ( var key in colunmInofList) {
							content += "<option value='" + key
									+ "'>" + colunmInofList[key]
									+ "</option>"
						}
						content += "</select></div></div>"
						$("#selectedColumns").append(content);
						$("#selectedColumns").find('select')
								.textSelect();
					} else if (result == 'D') {
						$("#selectedColumns")
						.append(
								"<div class=\"layui-inline layui-col-md3\" id=\"" 
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
										+ "</div>"
										+ "<div class=\"time-icon\"><i class=\"iconfont icon-date\"></i></div></div>");
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
			// 触发查询按钮
			$('#SvrArchiveData001_btn_search').trigger('click')
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

	$(function() {
		// 查询按钮事件
		$('#SvrArchiveData001_btn_search').click(function() {
			// table.load();
			showTables();
		});
	});

	function showTables() {
		var h = $("#selectedColumns").outerHeight(true);
		var divheight = 110 + h;
		var tableId = $('#baseTableName').val();
		if (typeof (tableId) == "undefined" || tableId == null || tableId == "") {
			layer.alert('请选择数据源表', {
				icon : 2,
				skin : 'layer-ext-moon'
			});
			return;
		}
		;
		var columnId = getColumnParams();
		var table = layui.table;
		layui
				.use(
						[ 'table' ],
						function() {
							var table = layui.table;
							table
									.render({
										elem : '#dataTable',
										type : 'POST',
										where : {
											tableId : $('#baseTableName').val(),
											columnIds : columnId
										},
										url : basePath
												+ "svreport/common/archivedata/SvrArchiveData001/findPage",
										page : true,
										height : 'full-' + divheight,
										cols : [ colList ]
									});
						});
	}

	var queryColumnInfoList = function(tableId, columnId) {
		var colunmList;
		$
				.ajax({
					url : basePath
							+ "svreport/common/archivedata/SvrArchiveData001/queryColumnInfoList",
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


	

	var getSaveParam = function() {
		return {
			planName : $('#planName').val(),
			tableId : $('#baseTableName').val(),
			columns : $('#columnType').val()
		}
	}

	function setData(filterId) {
		var valueList = [];
		var initData = [];
		$("#columnType").val(valueList).trigger("change");
		$.ajax({
			url : basePath
					+ "svreport/common/archivedata/SvrArchiveData001/queryPlanByfilterId",
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
//						var curData = {"id": key, "text": colunmList[key]};
						valueList.push(key);
					}
				}
				$.ajax({
					url : basePath + "svreport/common/archivedata/SvrArchiveData001/queryColumnByTableId",
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
//					 tags: true,
				     placeholder: '请选择字段',
				     multiple: true,
				     data: initData,
				     allowClear:true
				});
				$("#columnType").val(valueList).trigger("change");
//				s_id = null;
			}
		});
		

	}

	var getSearchKey = function() {
        return {
        	searchKey: $('#searchKey').val()
        }
    };

