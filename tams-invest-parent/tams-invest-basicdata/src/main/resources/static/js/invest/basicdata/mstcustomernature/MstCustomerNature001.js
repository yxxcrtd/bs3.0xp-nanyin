/*
 * 客服性质管理
 */
$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var customerType = $('#customerType').val();
        var customerCharater=$('#customerCharater').val();
        return {
            customerName: $('#customerName').val(),
            customerType:customerType ? customerType.join(","):'',
            customerCharater:customerCharater ? customerCharater.join(","):''
        }
    };
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax : {
            url : 'selectCustomerInfo',
            type : 'POST',
            data : function() {
                return getSearchParams();
            }
        },
        cols:[
             {field:'customerId', width:'20%', title: 'MstCustomerNature001.customerId'}
            ,{field:'customerName', width:'20%', title: 'MstCustomerNature001.customerName'}
            //产品化_SNSTZJY-2163 客户性质管理 导出显示列与设定显示列不符 modify by libaisong 2020-11-16
           /* ,{field:'customerTypeStr', width:'20%', title: 'MstCustomerNature001.customerType',*/
            //产品化——SNSTZJY-2436客户性质管理：客户类型列点击排序，异常 add by longpeng 20201219 
            ,{field:'customerTypeStr', sortField:'customerType', width:'20%', title: 'MstCustomerNature001.customerType',
                render : function(data, type, row, meta) {
                    if (data =='01') {
                        return JoyinUtil.getLabel("MstCustomerNature001.business");
                    }
                    if (data =='02') {
                        return JoyinUtil.getLabel("MstCustomerNature001.natural");
                    }
                }}
            ,{field:'customerCharater', width:'40%', title: 'MstCustomerNature001.customerCharater'}
        ]
    });
    /**
     * 导出事件
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    /**
     * 画面加载完成
     */
$(function() {
    // 绑定按钮事件
    $('#MstCustomerNature001_btn_search').click(function() {
        table.load();
    });
    $('#MstCustomerNature001_btn_modification').click(function() {        
       var tableData = table.getAllCheckedRows();
       if(tableData.length==0 ||tableData.length>1){
           Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
           return;
       }
       if(tableData.length==1){
           //Joyin.ajaxJson(basePath + 'invest/basicdata/mstcustomernature/MstCustomerNature003/check', tableData[0], function(result) {
           Joyin.dialog(JoyinUtil.getLabel("MstCustomerNature003.edit"),
                   basePath + "invest/basicdata/mstcustomernature/MstCustomerNature003/init",
                   "95%", "90%", {pageModel:PAGE_MODEL_EDIT,
                       customerId:tableData[0].customerId},
                       function(){table.reload();});
           //});
       }
    });
    $('#MstCustomerNature001_btn_export').click(function() {
        exportExcel();
    });
    $('#MstCustomerNature001_btn_configuration').click(function() {
        Joyin.dialog(JoyinUtil.getLabel("MstCustomerNature002"),
                    basePath + "invest/basicdata/mstcustomernature/MstCustomerNature002/init", "95%",
                        "90%", {pageModel:PAGE_MODEL_NEW},
                        function(){table.reload();});
    });
});
}());