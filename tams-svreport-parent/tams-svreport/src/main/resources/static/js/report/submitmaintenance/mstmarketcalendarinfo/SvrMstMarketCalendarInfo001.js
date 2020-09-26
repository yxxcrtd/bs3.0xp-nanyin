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
            field : 'cdate',width:'40%',
            title : 'MstMarketCalendarInfo001.Date',type:'date'
        }, {
            field : 'calendarName',width:'40%',
            title : 'MstMarketCalendarInfo001.calendarType'
        }, {
        	 field : 'isHolidayStr',width:'20%', sortField:'isHoliday',
             title : 'MstMarketCalendarInfo001.ishDay'
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
    	if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
         }
    	 var param = getSearchParams();
         if (!selArr || selArr.length == 0) {
         } else {
        	 param.keyList = JSON.stringify(selArr);
         }
        Joyin.exportExcel(basePath +"submitmaintenance/mstmarketcalendarinfo/SvrMstMarketCalendarInfo001/export", param);
    };
    /**
     * 绑定按钮事件
     */
    $(function() {
        /**
         * 查询按钮
         */
        $('#SvrMstMarketCalendarInfo001_btn_search').click(function() {
            table.load();
            $('#SvrMstMarketCalendarInfo001_btn_sethday').show();
            $('#SvrMstMarketCalendarInfo001_btn_setwday').show();
        });
        /**
         * 导出按钮
         */
        $('#SvrMstMarketCalendarInfo001_btn_export').click(function() {
            exportExcel();
        });
        /**
         * 导入按钮
         */
        $('#SvrMstMarketCalendarInfo001_btn_import').click(function() {
            Joyin.confirm(JoyinUtil.getLabel("MstMarketCalendarInfo001.confirm"), function() {
                Joyin.uploadFile(function(fileName) {
                    Joyin.ajax(basePath +"submitmaintenance/mstmarketcalendarinfo/SvrMstMarketCalendarInfo001/importFile", {
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
        $('#SvrMstMarketCalendarInfo001_btn_sethday').click(
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
                      layer.msg(JoyinUtil.getLabel("MstMarketCalendarInfo001.pselectwday"));
                  } else { 
                      Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("MstMarketCalendarInfo001.sethday")),function() {
                          Joyin.ajaxJson(basePath +"submitmaintenance/mstmarketcalendarinfo/SvrMstMarketCalendarInfo001/sethday",selArr, function() {
                              table.reload();
                          });
                      });
                  }
               }
           });
        /**
         * 设为工作日
         */
        $('#SvrMstMarketCalendarInfo001_btn_setwday').click(
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
                            layer.msg(JoyinUtil.getLabel("MstMarketCalendarInfo001.pselecthday"));
                        } else {         
                            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("MstMarketCalendarInfo001.setmday")), function() {  
                                Joyin.ajaxJson(basePath +"submitmaintenance/mstmarketcalendarinfo/SvrMstMarketCalendarInfo001/setwday",selArr, function() {
                                    table.reload();
                                });
                            });    
                        }
                     }
                    
                });

        });
}());