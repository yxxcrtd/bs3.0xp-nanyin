$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	exchargeCode: $('#exchargeCode').val(),
        	tradeDateBegin: $('#tradeDate').startDate(),
        	tradeDateEnd: $('#tradeDate').endDate()
        };
    };

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        dblclick:function(table, data) {
        	 var tradeDate = Date.ofDate(parseInt(data.tradeDate));
             tradeDate= tradeDate.format('yyyy-MM-dd');
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "submitmaintenance/exchangerateinfo/SvrMstExchangeQuotationInfo002/init",
                    "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,
            	                    exchargeCode:data.exchargeCode,
            	                    tradeDate:tradeDate});
        },            
        cols:[{field:'exchargeCode',width:200,  title: '货币对'}
            ,{field:'tradeDate',width:200,  title: '交易日期',type:'date'}   
            ,{field:'openPrice',width:200, title: '开盘价（元）',type:'number', digit:5}
            ,{field:'closePrice',width:200, title: '收盘价（元）',type:'number', digit:5}
            ,{field:'midPrice',width:200, title: '中间价（元）',type:'number', digit:5}
            ,{field:'highPrice',width:200, title: '最高价（元）',type:'number', digit:5}
            ,{field:'lowPrice',width:200, title: '最低价（元）',type:'number', digit:5}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        var tradeDate = Date.ofDate(parseInt(data.tradeDate));
        tradeDate= tradeDate.format('yyyy-MM-dd');
        Joyin.ajaxJson(basePath + 'submitmaintenance/exchangerateinfo/SvrMstExchangeQuotationInfo002/check', data, function(result) {
        	Joyin.dialog(JoyinUtil.getLabel("MstExchangeQuotationInfo002.edit"),
                    basePath + "submitmaintenance/exchangerateinfo/SvrMstExchangeQuotationInfo002/init",
                    "95%", "80%", {pageModel:PAGE_MODEL_EDIT,
            	                    exchargeCode:data.exchargeCode,
            	                    tradeDate:tradeDate},function() {
                                        table.reload();
                                    });
        });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
      var object = data;
      var keyList = [];
      keyList.push(data.exchargeCode +'|'+Date.ofDate(data.tradeDate).format());
        Joyin.ajaxJson(basePath + 'submitmaintenance/exchangerateinfo/SvrMstExchangeQuotationInfo001/check', object, function(result) {
        	Joyin.confirm("请确认是否要删除选中记录？", function() {
                Joyin.ajax('delete', {deleteKeys: keyList.join(',')}, function() {
                    table.reload();
                });
            });
        });
    });

    var exportExcel = function() {
    	if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
         }
    	var param = getSearchParams();
         if (!selArr || selArr.length == 0) {
         } else {
        	 param.keyList = JSON.stringify(selArr);
         }
        Joyin.exportExcel('export', param);
    };

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#SvrMstExchangeQuotationInfo001_btn_search').click(function() {
            table.load();
            $('#SvrMstExchangeQuotationInfo001_btn_delete').show();
        });
        $('#SvrMstExchangeQuotationInfo001_btn_export').click(function() {
            exportExcel();
        });
        $('#SvrMstExchangeQuotationInfo001_btn_add').click(function() {
        
            Joyin.dialog(JoyinUtil.getLabel("MstExchangeQuotationInfo002"),
                        basePath + "submitmaintenance/exchangerateinfo/SvrMstExchangeQuotationInfo002/init",
                        "60%", "75%", {pageModel:PAGE_MODEL_NEW});
        }); 
        $('#SvrMstExchangeQuotationInfo001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            var keyList = [];
            $.each(selArr, function(i, row) {
            	keyList.push(row.exchargeCode +'|'+Date.ofDate(row.tradeDate).format());
            });
              Joyin.ajaxJson(basePath + 'submitmaintenance/exchangerateinfo/SvrMstExchangeQuotationInfo001/check', object, function(result) {
              	Joyin.confirm("请确认是否要删除选中记录？", function() {
                      Joyin.ajax('delete', {deleteKeys: keyList.join(',')}, function() {
                          table.reload();
                      });
                  });
              });
        });

        // 导入按钮点击事件
        $('#SvrMstExchangeQuotationInfo001_btn_import').click(function() {
            Joyin.confirm("请确认是否要导入数据？", function() {
                Joyin.uploadFile(function(fileName) {
                    Joyin.ajax(basePath + 'submitmaintenance/exchangerateinfo/SvrMstExchangeQuotationInfo001/checkConfirmMsgForImportData', {fileName: fileName}, function() {
                        Joyin.ajax(basePath + 'submitmaintenance/exchangerateinfo/SvrMstExchangeQuotationInfo001/importFile', {fileName: fileName});
                    });
                },function() {
                    table.reload();
                });
            });
        });
     });
}());