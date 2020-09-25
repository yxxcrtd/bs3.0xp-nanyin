$(function() {
    var forecastTable = null;
    var c_forecastTable = $("#forecastTable");
    var getParam = function(queryType) {
        return {
            queryDate: $.trim($('#queryDate').val()),
            depType: $.trim($('#depType').val()),
            queryType:queryType
        }
    };

    var reloadForecastTable = function(table1) {
        Joyin.ajax(JoyinUtil.getRealPath('/invest/product/forecast/MaintainFixIncome001/getTargetName'),
                getParam('forecast'), function(result) {
            var cols2 = [];
            if(result) {
                cols2 = [
                         {field:'queryDate',visible: false},
                         {field:'depType',visible: false},
                         {field:'flowType',visible: false},
                         {field:'targetType',visible: false},
                         {field:'targetTypeStr', width:190, title: ' ',sortable:false},
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
                    height: 200,
                    page: false,
                    cellMinWidth : 100, 
                    cols: cols2,
                    beforeEditCell: function(preVal, field, rowData){
                        return true; 
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
    
    function reloadTable() {
        reloadForecastTable($("#forecastTable"));
    }
    
    var onSaveAction = function() {
        var object = $.serializeToObject('#submitForm', true);
        object.queryDate = $.trim($('#queryDate').val());
        object.depType = $.trim($('#depType').val());
        object.forecastList = forecastTable.getAllRows();
        Joyin.ajaxJson('/invest/product/forecast/MaintainFixIncome001/check',object, function(result) {
            Joyin.confirm(result.data, function() {
                 WorkFlow.doSaveList('/invest/product/forecast/MaintainFixIncome001/save', object, "MaintainFixIncome001_btn_save");
                })
            })
    }
    
    var queryDateChange = function(){
        var queryDate = $.trim($('#queryDate').val());
        if($.trim($('#queryDate').val())==="" || $.trim($('#queryDate').val()) === null){
            return;
        } else {
            
            if(forecastTable.table) {
                $("#divForecast").children().remove();
                $("#divForecast").append(c_forecastTable);
                reloadForecastTable(c_forecastTable);
            }
            
            
        }
    }

    $(document).ready(function() {
        reloadTable();
        $('#MaintainFixIncome001_btn_save').click(function(){
            onSaveAction();
        });
        $('#queryDate').change(function() {
            queryDateChange();
        })
    });
}());