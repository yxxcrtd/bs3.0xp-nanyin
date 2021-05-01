/**
 * 市场日历信息
 */
(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var startDate=$('#cdate').startDate();
        var endDate=$('#cdate').endDate();
        if($('#calendarType').val()){
            var calendarType=$('#calendarType').val().join(',');
        }
            return {
                calendarType : calendarType,
                ishDay : $('#ishDay').val(),
                startDate : startDate,
                endDate : endDate
        }
    };
    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax : {
            url : 'queryList',
            type : 'POST',
            data : function() {
                return getSearchParams();
            }
        },
        cols : [ {
            field : 'cdate',
            title : 'ComMarketCalendarInfo001.Date',type:'date'
        }, {
            field : 'calendarName',
            title : 'ComMarketCalendarInfo001.calendarType'
        }, {
            field : 'isHolidayStr', sortField:'isHoliday',
            title : 'ComMarketCalendarInfo001.ishDay'
        } ,{
            field : 'yorn',
            visible: false
        }, {
            field : 'calendarId',
            visible: false
        }]
    });
//    render : function(data, type, row, meta) {
//        if (data == yes) {
//            return '<span class="layui-badge-rim layui-bg-blue">是</span>';
//        }
//        return '<span class="layui-badge-rim layui-bg-gray">否</span>';
//    }
    /**
     * 导出
     */
    var exportExcel = function() {   
        Joyin.exportExcel(basePath +"common/basicdata/marketdata/mstmarketcalendarinfo/ComMarketCalendarInfo001/export", getSearchParams());
    };
    /**
     * 绑定按钮事件
     */
    $(function() {
        /**
         * 查询按钮
         */
        $('#ComMarketCalendarInfo001_btn_search').click(function() {
            table.load();
            $('#ComMarketCalendarInfo001_btn_sethday').show();
            $('#ComMarketCalendarInfo001_btn_setwday').show();
        });
        /**
         * 导出按钮
         */
        $('#ComMarketCalendarInfo001_btn_export').click(function() {
            exportExcel();
        });
        /**
         * 导入按钮
         */
        $('#ComMarketCalendarInfo001_btn_import').click(function() {
            Joyin.confirm(JoyinUtil.getLabel("ComMarketCalendarInfo001.confirm"), function() {
                Joyin.uploadFile(function(fileName) {
                    Joyin.ajax(basePath +"common/basicdata/marketdata/mstmarketcalendarinfo/ComMarketCalendarInfo001/importFile", {
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
        /**
         * 设为节假日
         */
        $('#ComMarketCalendarInfo001_btn_sethday').click(
           function() {
               if (table != null && table != '') {
                  var selArr = table.getAllCheckedRows();
               }
               if (!selArr || selArr.length == 0) {
                  Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                  return;
               } else {
                  var count = 0;
                  for (var i = 0; i < selArr.length; i++) {
                      if (selArr[i].yorn == yes) {
                          count++;
                      }
                  }
                  if (count != 0) {
                      layer.msg(JoyinUtil.getLabel("ComMarketCalendarInfo001.pselectwday"));
                  } else { 
                      Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("ComMarketCalendarInfo001.sethday")),function() {
                          Joyin.ajaxJson(basePath +"common/basicdata/marketdata/mstmarketcalendarinfo/ComMarketCalendarInfo001/sethday",selArr, function() {
                              table.reload();
                          });
                      });
                  }
               }
           });
        /**
         * 设为工作日
         */
        $('#ComMarketCalendarInfo001_btn_setwday').click(
                function() {
                    if (table != null && table != '') {
                        var selArr = table.getAllCheckedRows();
                     }
                     if (!selArr || selArr.length == 0) {
                        Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                        return;
                     } else {
                        var count = 0;
                        for (var i = 0; i < selArr.length; i++) {
                            if (selArr[i].yorn == no) {
                                count++;
                            }
                        }
                        if (count != 0) {
                            layer.msg(JoyinUtil.getLabel("ComMarketCalendarInfo001.pselecthday"));
                        } else {         
                            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("ComMarketCalendarInfo001.setmday")), function() {  
                                Joyin.ajaxJson(basePath +"common/basicdata/marketdata/mstmarketcalendarinfo/ComMarketCalendarInfo001/setwday",selArr, function() {
                                    table.reload();
                                });
                            });    
                        }
                     }
                    
                });

        });
}());