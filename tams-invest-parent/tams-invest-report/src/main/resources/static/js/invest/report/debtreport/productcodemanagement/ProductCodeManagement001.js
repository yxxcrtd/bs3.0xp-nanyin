(function(){
    /**
     * 获取查询参数
     */
    var searchform = $.serializeToObject('#searchForm');
    
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
       /* ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },*/
//        dblclick:function(table, data) {
//            data.pageModel=PAGE_MODEL_REFERENCE;
//            Joyin.dialog(JoyinUtil.getLabel("ProductStartAndEndAnno001"),
//                    JoyinUtil.getRealPath("plugins/fundaccountmanagement/FundAccountManagement002/init"),
//                    "98%", "95%", data,
//                    function(){table.load();});
//        },
        data:[ {'dataDate':'2018-7-30','productCode':'123443','financialPcode':'987567','valueDate':'2018-9-30','expiryDate':'2018-12-30','targetCustomerType':'大客户','pid':'4321'}
               ,{'dataDate':'2018-7-30','productCode':'123443','financialPcode':'987567','valueDate':'2018-9-30','expiryDate':'2018-12-30','targetCustomerType':'大客户','pid':'4321'}
               ,{'dataDate':'2018-7-30','productCode':'123443','financialPcode':'987567','valueDate':'2018-9-30','expiryDate':'2018-12-30','targetCustomerType':'大客户','pid':'4321'}
               ,{'dataDate':'2018-7-30','productCode':'123443','financialPcode':'987567','valueDate':'2018-9-30','expiryDate':'2018-12-30','targetCustomerType':'大客户','pid':'4321'}
               ,{'dataDate':'2018-7-30','productCode':'123443','financialPcode':'987567','valueDate':'2018-9-30','expiryDate':'2018-12-30','targetCustomerType':'大客户','pid':'4321'}
               ,{'dataDate':'2018-7-30','productCode':'123443','financialPcode':'987567','valueDate':'2018-9-30','expiryDate':'2018-12-30','targetCustomerType':'大客户','pid':'4321'}
               ,{'dataDate':'2018-7-30','productCode':'123443','financialPcode':'987567','valueDate':'2018-9-30','expiryDate':'2018-12-30','targetCustomerType':'大客户','pid':'4321'}
               ,{'dataDate':'2018-7-30','productCode':'123443','financialPcode':'987567','valueDate':'2018-9-30','expiryDate':'2018-12-30','targetCustomerType':'大客户','pid':'4321'}
               ,{'dataDate':'2018-7-30','productCode':'123443','financialPcode':'987567','valueDate':'2018-9-30','expiryDate':'2018-12-30','targetCustomerType':'大客户','pid':'4321'}
               ,{'dataDate':'2018-7-30','productCode':'123443','financialPcode':'987567','valueDate':'2018-9-30','expiryDate':'2018-12-30','targetCustomerType':'大客户','pid':'4321'}
               ,{'dataDate':'2018-7-30','productCode':'123443','financialPcode':'987567','valueDate':'2018-9-30','expiryDate':'2018-12-30','targetCustomerType':'大客户','pid':'4321'}
        ],
        
        
        cols:[
            {field:'dataDate', title: 'ProductCodeManagement001.dataDate'}
            ,{field:'productCode', title: 'ProductCodeManagement001.productCode'}
            ,{field:'financialPcode', title: 'ProductCodeManagement001.financialPcode'}
            ,{field:'valueDate', title: 'ProductCodeManagement001.valueDate'}
            ,{field:'expiryDate', title: 'ProductCodeManagement001.expiryDate'}
            ,{field:'targetCustomerType', title: 'ProductCodeManagement001.targetCustomerType'}
            ,{field:'pid', visible: false}
        ],
        loadonce:true
    });
  
    var exportExcel = function(){       
        Joyin.exportExcel('export', searchform);
    };
  
    
    $(function () {
        // 绑定按钮事件
        $('#ProductCodeManagement001_btn_search').click(function() {
            table.load();
        });
        $('#ProductCodeManagement001_btn_export').click(function() {
            exportExcel();
        });
        $('#ProductCodeManagement001_btn_import').click(function() {
            Joyin.confirm("请确认是否要导入数据？", function() {
                Joyin.uploadFile(function(fileName) {
                    Joyin.ajax('importFile', {fileName: fileName});
                },function() {
                    table.load();
                });
            });
        });
    });
}());