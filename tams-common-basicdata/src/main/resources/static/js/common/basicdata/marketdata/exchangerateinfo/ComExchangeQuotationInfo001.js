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
        	 var tradeDate = new Date(parseInt(data.tradeDate));
             tradeDate= tradeDate.format('yyyy-MM-dd');
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "common/basicdata/marketdata/exchangerateinfo/ComExchangeQuotationInfo002/init",
                    "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,
            	                    exchargeCode:data.exchargeCode,
            	                    tradeDate:tradeDate});
        },            
        cols:[{field:'exchargeCode',width:200,  title: 'ComExchangeQuotationInfo001.exchargeCode'}
            ,{field:'tradeDate',width:200,  title: 'ComExchangeQuotationInfo001.tradeDate',type:'date'}
            ,{field:'openPrice',width:200, title: 'ComExchangeQuotationInfo002.openPrice'}
            ,{field:'closePrice',width:200, title: 'ComExchangeQuotationInfo002.closePrice'}
            ,{field:'midPrice',width:200, title: 'ComExchangeQuotationInfo002.midPrice'}
            ,{field:'highPrice',width:200, title: 'ComExchangeQuotationInfo002.highPrice'}
            ,{field:'lowPrice',width:200, title: 'ComExchangeQuotationInfo002.lowPrice'}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        var tradeDate = new Date(parseInt(data.tradeDate));
        tradeDate= tradeDate.format('yyyy-MM-dd');
        Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/exchangerateinfo/ComExchangeQuotationInfo002/check', data, function(result) {
        	Joyin.dialog(JoyinUtil.getLabel("ComExchangeQuotationInfo002.edit"),
                    basePath + "common/basicdata/marketdata/exchangerateinfo/ComExchangeQuotationInfo002/init",
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
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/exchangerateinfo/ComExchangeQuotationInfo001/check', object, function(result) {
        	Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'common/basicdata/marketdata/exchangerateinfo/ComExchangeQuotationInfo001/delete', object, "ComExchangeQuotationInfo001_btn_delete", "ComExchangeQuotationInfo002",function() {
                    table.reload();
                });
            });
        });
    });

    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ComExchangeQuotationInfo001_btn_search').click(function() {
            table.load();
            $('#ComExchangeQuotationInfo001_btn_delete').show();
        });
        $('#ComExchangeQuotationInfo001_btn_export').click(function() {
            exportExcel();
        });
        $('#ComExchangeQuotationInfo001_btn_add').click(function() {
        
            Joyin.dialog(JoyinUtil.getLabel("ComExchangeQuotationInfo002"),
                        basePath + "common/basicdata/marketdata/exchangerateinfo/ComExchangeQuotationInfo002/init",
                        "60%", "88%", {pageModel:PAGE_MODEL_NEW});
        }); 
        $('#ComExchangeQuotationInfo001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/exchangerateinfo/ComExchangeQuotationInfo001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'common/basicdata/marketdata/exchangerateinfo/ComExchangeQuotationInfo001/delete', object, "ComExchangeQuotationInfo001_btn_delete", "ComExchangeQuotationInfo002",function() {
                    	table.reload();
                });      
             });
           });
        });
        
        // 导入按钮点击事件
        $('#ComExchangeQuotationInfo001_btn_import').click(function() {
            Joyin.confirm("请确认是否要导入数据？", function() {
                Joyin.uploadFile(function(fileName) {
                    Joyin.ajax(basePath + 'common/basicdata/marketdata/exchangerateinfo/ComExchangeQuotationInfo001/checkConfirmMsgForImportData', {fileName: fileName}, function() {
                        Joyin.ajax(basePath + 'common/basicdata/marketdata/exchangerateinfo/ComExchangeQuotationInfo001/importFile', {fileName: fileName});
                    });
                },function() {
                    table.reload();
                });
            });
        });
     });
}());