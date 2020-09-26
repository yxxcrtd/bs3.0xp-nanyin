$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            releaseDate: $('#releaseDate').val(),
        	intRateId: $('#intRateId').val(),
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
            //在新建界面进行显示单行的详细信息
            data.effDate = new Date(data.effDate).format();
            data.pageModel = PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("ValInterestRateQuotationInfo001"),
                    basePath + "valuation/basedata/marketdata/valinterestratequotationinfo/ValInterestRateQuotationInfo002/init",
                    "95%", "80%", data);
        },
       
        cols:[
             {field:'relaeseDate',width:200,  title: 'ValInterestRateQuotationInfo001.relaeseDate',type:'date'}
            ,{field:'intRateName',width:200,  title: 'ValInterestRateQuotationInfo001.intRateName', sortField:'intRateId'}
            ,{field:'ccyName',width:200,  title: 'ValInterestRateQuotationInfo001.ccy', sortField:'ccy'}
            ,{field:'intRate',width:200,  title: 'ValInterestRateQuotationInfo001.intRate',type:'number'}
            ,{field:'effDate',width:200, title: 'ValInterestRateQuotationInfo001.effDate',type:'date'}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
    	data.effDate = new Date(data.effDate).format();
        data.pageModel = PAGE_MODEL_EDIT;
         Joyin.ajaxJson(basePath + 'valuation/basedata/marketdata/valinterestratequotationinfo/ValInterestRateQuotationInfo002/check', data, function(result) { 
            Joyin.dialog(JoyinUtil.getLabel("ValInterestRateQuotationInfo002.edit"),
                    basePath + "valuation/basedata/marketdata/valinterestratequotationinfo/ValInterestRateQuotationInfo002/init",
                    "98%", "95%", data,function(){table.reload();});
         });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
         Joyin.ajaxJson(basePath + 'valuation/basedata/marketdata/valinterestratequotationinfo/ValInterestRateQuotationInfo001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'valuation/basedata/marketdata/valinterestratequotationinfo/ValInterestRateQuotationInfo001/delete', object, "ValInterestRateQuotationInfo001_btn_delete", "ValInterestRateQuotationInfo002",function() {
                    table.reload();
                });
            });
         }); 
    });

    /**
     * 导出选中的表格数据
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();//请求参数json格式
        Joyin.exportExcel('export', paramObj);//导出（共通）
    };

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ValInterestRateQuotationInfo001_btn_search').click(function() {
            table.load();
            $('#ValInterestRateQuotationInfo001_btn_delete').show();
        });
        $('#ValInterestRateQuotationInfo001_btn_export').click(function() {
            exportExcel();
        });
        $('#ValInterestRateQuotationInfo001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("ValInterestRateQuotationInfo002"),
                        basePath + "valuation/basedata/marketdata/valinterestratequotationinfo/ValInterestRateQuotationInfo002/init",
                        "60%", "75%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#ValInterestRateQuotationInfo001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'valuation/basedata/marketdata/valinterestratequotationinfo/ValInterestRateQuotationInfo001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'valuation/basedata/marketdata/valinterestratequotationinfo/ValInterestRateQuotationInfo001/delete', object, "ValInterestRateQuotationInfo001_btn_delete", "ValInterestRateQuotationInfo002",function() {
                        table.reload();
                    });
                });
        });
        });
    });
}());