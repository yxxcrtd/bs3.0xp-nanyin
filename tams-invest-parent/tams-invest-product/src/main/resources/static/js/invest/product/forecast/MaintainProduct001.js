$(function() {
    var forecastTable = null;
    var netValueOpenforecastTable = null;
    var reserveDetailTable = null;
    var c_forecastTable = $("#forecastTable");
    var c_netValueOpenforecastTable = $("#netValueOpenforecastTable");
    var getParam = function(queryType) {
        return{
            queryDate: $.trim($('#queryDate').val()), 
            depType: $.trim($('#depType').val()), 
            queryType:queryType
        }
     };
    /**
     * 化加载流动性储备
     */
    var reloadReserveTable = function(table1) {
        Joyin.ajax(JoyinUtil.getRealPath('/invest/product/forecast/MaintainProduct001/getTargetName'),
                getParam('findReserve'), function(result) {
            var cols1 = [];
            if(result) {
                cols1 = [
                         {field:'queryDate',visible: false},
                         {field:'depType',visible: false},
                         {field:'flowType',visible: false},
                         {field:'targetType', width:191, title: '产品',sortable:false},
                         {field:'targetTimeone', width:191, title: result.targetName1,edit:true,type:'number',sortable:false,digit:2},
                         {field:'targetTimetwo', width:191, title: result.targetName2,edit:true,type:'number',sortable:false,digit:2},
                         {field:'targetTimethree', width:190, title: result.targetName3,edit:true,type:'number',sortable:false,digit:2},
                         {field:'targetTimefour', width:190, title: result.targetName4,edit:true,type:'number',sortable:false,digit:2},
                         {field:'targetTimefive', width:190, title: result.targetName5,edit:true,type:'number',sortable:false,digit:2},
                         {field:'targetTimesix', width:190, title: result.targetName6,edit:true,type:'number',sortable:false,digit:2}
                         ];   
            }

            var gridObj = {
                   ajax: {
                          url: 'findReserveDetail',
                          type: 'POST',
                          data: function() {
                              return getParam('findReserve');
                          }
                      },
                    checkbox: false,
                    height: 80,
                    page: false,
                    cellMinWidth : 100, 
                    cols: cols1,
                    beforeEditCell: function(preVal, field, rowData){
                        return true; 
                    },
                    editCell: function (value, row, field, cell, column) {
                        return true; 
                    },
                    done: function(table, allData){
                    }
            };
            reserveDetailTable = table1.joyintable(gridObj); 
            
            if(reserveDetailTable.table) {
                 reserveDetailTable.reload();
              } else {
                 reserveDetailTable.load();
            }
            
            
        });
    }
    /**
     * 加载流动性预测
     */
    var reloadForecastTable = function(table1) {
        Joyin.ajax(JoyinUtil.getRealPath('/invest/product/forecast/MaintainProduct001/getTargetName'),
                getParam('forecast'), function(result) {
            var cols2 = [];
            if(result) {
                cols2 = [
                         {field:'queryDate',visible: false},
                         {field:'depType',visible: false},
                         {field:'flowType',visible: false},
                         {field:'targetType',visible: false},
                         {field:'targetTypeStr', width:190, title: '产品',sortable:false},
                         {field:'targetTimeone', width:190, title: result.targetName1,edit:true,type:'number',sortable:false,digit:2},
                         {field:'targetTimetwo', width:190, title: result.targetName2,edit:true,type:'number',sortable:false,digit:2},
                         {field:'targetTimethree', width:190, title: result.targetName3,edit:true,type:'number',sortable:false,digit:2},
                         {field:'targetTimefour', width:190, title: result.targetName4,edit:true,type:'number',sortable:false,digit:2},
                         {field:'targetTimefive', width:190, title: result.targetName5,edit:true,type:'number',sortable:false,digit:2},
                         {field:'targetTimesix', width:190, title: result.targetName6,edit:true,type:'number',sortable:false,digit:2}
                         ];   
            }

            var gridObj = {
                   ajax: {
                          url: 'findForecast',
                          type: 'POST',
                          data: function() {
                              return getParam('forecast');
                          }
                      },
                    checkbox: false,
                    height: 170,
                    page: false,
                    cellMinWidth : 100, 
                    cols: cols2,
                    beforeEditCell: function(preVal, field, rowData){
                        if(rowData.targetType == TARGETTYPE_JJCPKFQJSS) {
                            return false; 
                        } else {
                            return true;
                        }
                         
                    },
                    editCell: function (value, row, field, cell, column) {
                        return true; 
                    },
                    done: function(table, allData){
                    }
            };
            forecastTable = table1.joyintable(gridObj); 
            
            if(forecastTable.table) {
                forecastTable.reload();
            } else {
                forecastTable.load();
            }
        });
    }
    
    /**
     * 加载净值型产品开放期净申赎预测
     */
    var reloadNetValueOpenforecastTable = function(table2){
        Joyin.ajax(JoyinUtil.getRealPath('/invest/product/forecast/MaintainProduct001/getTargetName'),
                getParam('netValueOpenforecast'), function(result) {
            var cols3 = [];
            if(result) {
                cols3 = [
                         {field:'targetName', width:165,sortable:false, title: '产品'},
                         {field:'targetType', width:165,sortable:false, title: '金额'},
                         {field:'targetTimeone', width:165, title: result.targetName1,edit:true,type:'number',sortable:false,digit:2},
                         {field:'targetTimetwo', width:165, title: result.targetName2,edit:true,type:'number',sortable:false,digit:2},
                         {field:'targetTimethree', width:165, title: result.targetName3,edit:true,type:'number',sortable:false,digit:2},
                         {field:'targetTimefour', width:165, title: result.targetName4,edit:true,type:'number',sortable:false,digit:2},
                         {field:'targetTimefive', width:165, title: result.targetName5,edit:true,type:'number',sortable:false,digit:2},
                         {field:'targetTimesix', width:165, title: result.targetName6,edit:true,type:'number',sortable:false,digit:2}
                         ];   
            }

            var gridObj = {
                   ajax: {
                          url: 'findNetValueOpenforecast',
                          type: 'POST',
                          data: function() {
                              return getParam('netValueOpenforecast');
                          }
                      },
                    checkbox: false,
                    height: 170,
                    page: false,
                    cellMinWidth : 100, 
                    cols: cols3,
                    beforeEditCell: function(preVal, field, rowData){
                        if(rowData.targetType === '净申购金额') {
                            return false; 
                        } else {
                            return true;
                        }
                    },
                    editCell: function (value, row, field, cell, column) {
                    	var amount = 0;
                    	var index = 0;
                    	var row1;
                    	if(row.targetType == '申购金额') {
                    		row1 = netValueOpenforecastTable.getRow(row.LAY_TABLE_INDEX + 1);
                    		index = row.LAY_TABLE_INDEX + 2
                    		
                    	} else {	
                    		row1 = netValueOpenforecastTable.getRow(row.LAY_TABLE_INDEX - 1);	
                    		index = row.LAY_TABLE_INDEX + 1
                    	} 
                    	if(field ==='targetTimeone') {
                    		amount = (parseFloat(value) - parseFloat(row1.targetTimeone?row1.targetTimeone:0))*(row.targetType == '申购金额'?1:-1);
                    		
                    		netValueOpenforecastTable.renderRow(index, {targetTimeone : amount});	
                    	} else if(field ==='targetTimetwo') {
                    		amount = parseFloat(value) - parseFloat(row1.targetTimetwo?row1.targetTimetwo:0);
                    		netValueOpenforecastTable.renderRow(index, {targetTimetwo : amount});	
                    	} else if(field ==='targetTimethree') {
                    		amount = parseFloat(value) - parseFloat(row1.targetTimethree?row1.targetTimethree:0);
                    		netValueOpenforecastTable.renderRow(index, {targetTimethree :amount});	
                    	} else if(field ==='targetTimefour') {
                    		amount = parseFloat(value) -  parseFloat(row1.targetTimefour?row1.targetTimefour:0);
                    		netValueOpenforecastTable.renderRow(index, {targetTimefour : amount});	
                    	} else if(field ==='targetTimefive') {
                    		amount = parseFloat(value) - parseFloat(row1.targetTimefive?row1.targetTimefive:0);
                    		netValueOpenforecastTable.renderRow(index, {targetTimefive : amount});	
                    	} else if(field ==='targetTimesix') {
                    		amount = parseFloat(value) - parseFloat(row1.targetTimesix?row1.targetTimesix:0);
                    		netValueOpenforecastTable.renderRow(index, {targetTimesix : amount});	
                    	}
                    	//修改流动性预测
                        changeForecast();
                        
                        return true; 
                    },
                    done: function(table, allData){
                    }
            };
            netValueOpenforecastTable = table2.joyintable(gridObj); 
            
            if(netValueOpenforecastTable.table) {
                netValueOpenforecastTable.reload();
            } else {
                netValueOpenforecastTable.load();
            }            
        });
    
    }
    
    function changeForecast(){
    	var netValueOpenforecasList = netValueOpenforecastTable.getAllRows();
    	var forecastList = forecastTable.getAllRows();
    	
    	if(netValueOpenforecasList && forecastList) {
    	 for(var i = 0;i<forecastList.length;i++) {
         	  if(forecastList[i].targetType === TARGETTYPE_JJCPKFQJSS) {
         		 var targetTimeone = 0;
         		 var targetTimetwo = 0;
         		 var targetTimethree = 0;
         		 var targetTimefour = 0;
         		 var targetTimefive = 0;
         		 var targetTimesix = 0;
             	 for(var j = 0;j<netValueOpenforecasList.length;j++) {
             		if(netValueOpenforecasList[j].targetType ==='净申购金额') {
             			targetTimeone = parseFloat(targetTimeone) + parseFloat(netValueOpenforecasList[j].targetTimeone?netValueOpenforecasList[j].targetTimeone:0);
             			targetTimetwo = parseFloat(targetTimetwo) + parseFloat(netValueOpenforecasList[j].targetTimetwo?netValueOpenforecasList[j].targetTimetwo:0);
             			targetTimethree = parseFloat(targetTimethree) + parseFloat(netValueOpenforecasList[j].targetTimethree?netValueOpenforecasList[j].targetTimethree:0);
             			targetTimefour = parseFloat(targetTimefour) + parseFloat(netValueOpenforecasList[j].targetTimefour?netValueOpenforecasList[j].targetTimefour:0);
             			targetTimefive = parseFloat(targetTimefive) + parseFloat(netValueOpenforecasList[j].targetTimefive?netValueOpenforecasList[j].targetTimefive:0);
                 		targetTimesix = parseFloat(targetTimesix) + parseFloat(netValueOpenforecasList[j].targetTimesix?netValueOpenforecasList[j].targetTimesix:0);	
             		}
         		 } 
             	 
             	forecastTable.renderRow(i, {targetTimeone : targetTimeone});
             	forecastTable.renderRow(i, {targetTimetwo : targetTimetwo});	
             	forecastTable.renderRow(i, {targetTimethree : targetTimethree});	
             	forecastTable.renderRow(i, {targetTimefour : targetTimefour});	
             	forecastTable.renderRow(i, {targetTimefive : targetTimefive});	
             	forecastTable.renderRow(i, {targetTimesix : targetTimesix});	
         	  }
    	 }
    	 
    	}
    }
    /**
     * 加载表格
     * @returns
     */
    function reloadTable() {
        reloadReserveTable($("#divReserveDetail"));
        reloadForecastTable($("#forecastTable"));
        reloadNetValueOpenforecastTable($("#netValueOpenforecastTable"));
    }
    /**
     * 保存
     */
    var onSaveAction = function() {
        var object = $.serializeToObject('#submitForm', true);
        object.queryDate = $.trim($('#queryDate').val());
        object.depType = $.trim($('#depType').val());
        object.reserveDetailList = reserveDetailTable.getAllRows();
        object.forecastList = forecastTable.getAllRows();
        object.netValueOpenforecasList = netValueOpenforecastTable.getAllRows();
        Joyin.ajaxJson('/invest/product/forecast/MaintainProduct001/check',object, function(result) {
            Joyin.confirm(result.data, function() {
                 WorkFlow.doSaveList('/invest/product/forecast/MaintainProduct001/save', object, "MaintainProduct001_btn_save");
                })
            })
    }
    
    /**
     * 切换日期
     */
    var queryDateChange = function(){
        var queryDate = $.trim($('#queryDate').val());
        if($.trim($('#queryDate').val())==="" || $.trim($('#queryDate').val()) === null){
            return;
        } else {
            if(reserveDetailTable.table) {
                reserveDetailTable.clear(reloadReserveTable($("#divReserveDetail")));
            }
            if(forecastTable.table) {
                $("#divForecast").children().remove();
                $("#divForecast").append(c_forecastTable);
                reloadForecastTable(c_forecastTable);
            }
            
            if(netValueOpenforecastTable.table) {

                $("#divNetValueOpenforecast").children().remove();
                $("#divNetValueOpenforecast").append(c_netValueOpenforecastTable);
                reloadNetValueOpenforecastTable(c_netValueOpenforecastTable);
            }  
        }
    }
    
    $(document).ready(function() {
        reloadTable();
        $('#MaintainProduct001_btn_save').click(function(){
            onSaveAction();
        });
        $('#queryDate').change(function() {
            queryDateChange();
        })
    });
}());