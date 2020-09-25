$(function() {
	var cols1 = [];
	var forecastTable = null;

	var c_forecastTable = $("#forecastTable");

	var getParam = function(queryType) {
		return {
			queryDate : $.trim($('#queryDate').val()),
			depType : $.trim($('#depType').val()),
			queryType : queryType
		}
	};

	var reloadForecastTable = function(table1) {
		Joyin
				.ajax(
						JoyinUtil
								.getRealPath('/invest/product/forecast/MaintainInvest001/getTargetName'),
						getParam('forecast'),
						function(result) {
							if (result) {
								cols2 = [ {
									field : 'queryDate',
									visible : false
								}, {
									field : 'depType',
									visible : false
								}, {
									field : 'flowType',
									visible : false
								}, {
									field : 'targetType',
									visible : false
								}, {
									field : 'targetTypeStr',
									width : 190,
									title : ' ',
									sortable : false
								}, {
									field : 'targetTimeone',
									width : 190,
									title : result.targetName1,
									edit : true,
									type : 'number',
									sortable:false,
									digit : 2
								}, {
									field : 'targetTimetwo',
									width : 190,
									title : result.targetName2,
									edit : true,
									type : 'number',
									sortable:false,
									digit : 2
								}, {
									field : 'targetTimethree',
									width : 190,
									title : result.targetName3,
									edit : true,
									type : 'number',
									sortable:false,
									digit : 2
								}, {
									field : 'targetTimefour',
									width : 190,
									title : result.targetName4,
									edit : true,
									type : 'number',
									sortable:false,
									digit : 2
								}, {
									field : 'targetTimefive',
									width : 190,
									title : result.targetName5,
									edit : true,
									type : 'number',
									sortable:false,
									digit : 2
								}, {
									field : 'targetTimesix',
									width : 190,
									title : result.targetName6,
									edit : true,
									type : 'number',
									sortable:false,
									digit : 2
								} ];
							}

							var gridObj = {
								ajax : {
									url : 'findForecast',
									type : 'POST',
									data : function() {
										return getParam('forecast');
									}
								},
								checkbox : false,
								height : 200,
								page : false,
								cellMinWidth : 100,
								cols : cols2,
								beforeEditCell : function(preVal, field,
										rowData) {
									if (rowData.targetType == TARGETTYPE_JJCPKFQJS3
											|| rowData.targetType == TARGETTYPE_JJCPKFQJS4) {
										return false;
									}
									return true;
								},

								editCell : function(value, data, row, field,
										cell, column) {

									var selArr = forecastTable.getAllRows();
									var getData = forecastTable.getRow(0);
									if (data.targetType === '11'
											|| data.targetType === '12') {
										if (row === "targetTimeone") {
											var getrow1 = selArr[1].targetTimeone;
											var getrow2 = selArr[2].targetTimeone;
											if (getrow1 == null
													|| getrow1 == '')
												var getrow1 = 0;
											if (getrow2 == null
													|| getrow2 == '')
												var getrow2 = 0;
											var data1 = parseFloat(getrow1)
													+ parseFloat(getrow2);
											forecastTable.renderRow(0, {
												targetTimeone : data1
											});
										}
										if (row === "targetTimetwo") {
											var getrow1 = selArr[1].targetTimetwo;
											var getrow2 = selArr[2].targetTimetwo;
											if (getrow1 == null
													|| getrow1 == '')
												var getrow1 = 0;
											if (getrow2 == null
													|| getrow2 == '')
												var getrow2 = 0;
											var data1 = parseFloat(getrow1)
													+ parseFloat(getrow2);
											forecastTable.renderRow(0, {
												targetTimetwo : data1
											});
										 
									} 
										 
										if (row === "targetTimethree") {
											var getrow1 = selArr[1].targetTimethree;
											var getrow2 = selArr[2].targetTimethree;
											if (getrow1 == null
													|| getrow1 == '')
												var getrow1 = 0;
											if (getrow2 == null
													|| getrow2 == '')
												var getrow2 = 0;
											var data1 = parseFloat(getrow1)
													+ parseFloat(getrow2);
											forecastTable.renderRow(0, {
												targetTimethree : data1
											});
										 
									} 
									if (row === "targetTimefour") {
												var getrow1 = selArr[1].targetTimefour;
												var getrow2 = selArr[2].targetTimefour;
												if (getrow1 == null
														|| getrow1 == '')
													var getrow1 = 0;
												if (getrow2 == null
														|| getrow2 == '')
													var getrow2 = 0;
												var data1 = parseFloat(getrow1)
														+ parseFloat(getrow2);
												forecastTable.renderRow(0, {
													targetTimefour : data1
												});
											 
										} 
										if (row === "targetTimefive") {
												var getrow1 = selArr[1].targetTimefive;
												var getrow2 = selArr[2].targetTimefive;
												if (getrow1 == null
														|| getrow1 == '')
													var getrow1 = 0;
												if (getrow2 == null
														|| getrow2 == '')
													var getrow2 = 0;
												var data1 = parseFloat(getrow1)
														+ parseFloat(getrow2);
												forecastTable.renderRow(0, {
													targetTimefive : data1
												});
									     } 
											if (row === "targetTimesix") {
												var getrow1 = selArr[1].targetTimesix;
												var getrow2 = selArr[2].targetTimesix;
												if (getrow1 == null
														|| getrow1 == '')
													var getrow1 = 0;
												if (getrow2 == null
														|| getrow2 == '')
													var getrow2 = 0;
												var data1 = parseFloat(getrow1)
														+ parseFloat(getrow2);
												forecastTable.renderRow(0, {
													targetTimesix : data1
												});
											 
										}
									}
								   if (data.targetType === '14'
												|| data.targetType === '15') {
												  
											if (row === "targetTimeone") {
												var getrow1 = selArr[4].targetTimeone;
												var getrow2 = selArr[5].targetTimeone;
												if (getrow1 == null
														|| getrow1 == '')
													var getrow1 = 0;
												if (getrow2 == null
														|| getrow2 == '')
													var getrow2 = 0;
												var data1 = parseFloat(getrow1)
														+ parseFloat(getrow2);
												forecastTable.renderRow(3, {
													targetTimeone : data1
												});
											}
											if (row === "targetTimetwo") {
												var getrow1 = selArr[4].targetTimetwo;
												var getrow2 = selArr[5].targetTimetwo;
												if (getrow1 == null
														|| getrow1 == '')
													var getrow1 = 0;
												if (getrow2 == null
														|| getrow2 == '')
													var getrow2 = 0;
												var data1 = parseFloat(getrow1)
														+ parseFloat(getrow2);
												forecastTable.renderRow(3, {
													targetTimetwo : data1
												});
											}
											if (row === "targetTimethree") {
												var getrow1 = selArr[4].targetTimethree;
												var getrow2 = selArr[5].targetTimethree;
												if (getrow1 == null
														|| getrow1 == '')
													var getrow1 = 0;
												if (getrow2 == null
														|| getrow2 == '')
													var getrow2 = 0;
												var data1 = parseFloat(getrow1)
														+ parseFloat(getrow2);
												forecastTable.renderRow(3, {
													targetTimethree : data1
												});
											}
											if (row === "targetTimefour") {
												var getrow1 = selArr[4].targetTimefour;
												var getrow2 = selArr[5].targetTimefour;
												if (getrow1 == null
														|| getrow1 == '')
													var getrow1 = 0;
												if (getrow2 == null
														|| getrow2 == '')
													var getrow2 = 0;
												var data1 = parseFloat(getrow1)
														+ parseFloat(getrow2);
												forecastTable.renderRow(3, {
													targetTimefour : data1
												});
											}
											if (row === "targetTimefive") {
												var getrow1 = selArr[4].targetTimefive;
												var getrow2 = selArr[5].targetTimefive;
												if (getrow1 == null
														|| getrow1 == '')
													var getrow1 = 0;
												if (getrow2 == null
														|| getrow2 == '')
													var getrow2 = 0;
												var data1 = parseFloat(getrow1)
														+ parseFloat(getrow2);
												forecastTable.renderRow(3, {
													targetTimefive : data1
												});
											}
											if (row === "targetTimesix") {
												var getrow1 = selArr[4].targetTimesix;
												var getrow2 = selArr[5].targetTimesix;
												if (getrow1 == null
														|| getrow1 == '')
													var getrow1 = 0;
												if (getrow2 == null
														|| getrow2 == '')
													var getrow2 = 0;
												var data1 = parseFloat(getrow1)
														+ parseFloat(getrow2);
												forecastTable.renderRow(3, {
													targetTimesix : data1
												});
											}
									} 
										
												 
											 
									
									return true;
								},
								done : function(table, allData) {

								}
							};
							forecastTable = table1.joyintable(gridObj);

							if (forecastTable.table) {
								forecastTable.reload();
							} else {
								forecastTable.load();
							}
						});
	}

	function reloadTable() {

		reloadForecastTable($("#forecastTable"));

	}

	var onSaveAction = function() {
 
		var object = $.serializeToObject('#submitForm', true);
		object.queryDate = $.trim($('#queryDate').val());
		object.depType = $.trim($('#depType').val());

		object.forecastList = forecastTable.getAllRows();

		Joyin.ajaxJson('/invest/product/forecast/MaintainInvest001/check', object,
				function(result) {
					Joyin.confirm(result.data, function() {
						WorkFlow.doSaveList(
								'/invest/product/forecast/MaintainInvest001/save',
								object, "MaintainInvest001_btn_save");
					})
				})
	}

	var queryDateChange = function() {
		var queryDate = $.trim($('#queryDate').val());
		if ($.trim($('#queryDate').val()) === ""
				|| $.trim($('#queryDate').val()) === null) {
			return;
		} else {

			if (forecastTable.table) {
				$("#divForecast").children().remove();
				$("#divForecast").append(c_forecastTable);
				reloadForecastTable(c_forecastTable);
			}

		}

	}

	$(document).ready(function() {
		reloadTable();
		$('#MaintainInvest001_btn_save').click(function() {
			onSaveAction();
		});
		$('#queryDate').change(function() {
			queryDateChange();
		})
	});
}());