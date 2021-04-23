(function(){
    /**
     * 获取查询参数
     */
	var getSearchParams = function() {
        return {
        	acctName: $('#acctName').val()
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
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("MstCaptialAccount002.reference"),
                    JoyinUtil.getRealPath("invest/basicdata/mstcaptialaccount/MstCaptialAccount002/init"),
                    "98%", "95%", data,
                    function(){table.reload();});
        },
        cols:[
             
            {field:'busiAcctNo',width:'20%', sortField:'BUSI_ACCT_NO', title: 'MstCaptialAccount001.accountId'}
            ,{field:'acctName',width:'20%', sortField:'ACCT_NAME', title: 'MstCaptialAccount001.accountName'}
            ,{field:'bankDep',width:'20%', sortField:'BANK_DEP', title: 'MstCaptialAccount002.bankAccount'}
            ,{field:'largePayno',width:'20%', sortField:'LARGE_PAYNO', title: 'MstCaptialAccount002.accountOutlet'}
            ,{field:'swiftCode',width:'20%', sortField:'SWIFT_CODE', title: 'MstCaptialAccount002.swiftCode'}
            ,{field:'ccyStr',width:'20%', sortField:'CCY', title: 'MstCaptialAccount002.currency'}
            ,{field:'acctNo',width:'20%', visible: false}
            ,{field:'ccy',width:'20%', visible: false}
            ,{field:'operation', title: '操作' , type:'action',buttons: ['edit', 'delt']}
        ],
        //loadonce:true
    });
  
    var exportExcel = function(){       
        Joyin.exportExcel('export', getSearchParams());
    };
  
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.operateType = "edit";
        data.pageModel=PAGE_MODEL_EDIT;
//        Joyin.ajaxJson(basePath + 'invest/basicdata/mstcaptialaccount/MstCaptialAccount002/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("MstCaptialAccount002.edit"),
                    basePath + "invest/basicdata/mstcaptialaccount/MstCaptialAccount002/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_EDIT,
            						acctNo:data.acctNo,
            						ccy:data.ccy},function(){table.reload();});
//        });
    });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstcaptialaccount/MstCaptialAccount001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/basicdata/mstcaptialaccount/MstCaptialAccount001/delete', object, "MstCaptialAccount001_btn_delete", "MstCaptialAccount002",function() {
                	table.reload();
                });
            });
        });
    });
    
    $(function () {
        // 绑定按钮事件
        $('#MstCaptialAccount001_btn_search').click(function() {
            table.load();
            $('#MstCaptialAccount001_btn_delete').show();
        });
        
        $('#MstCaptialAccount001_btn_export').click(function() {
            exportExcel();
        });
        $('#MstCaptialAccount001_btn_add').click(function() {
        	Joyin.dialog(JoyinUtil.getLabel("MstCaptialAccount002"),
                    basePath + "invest/basicdata/mstcaptialaccount/MstCaptialAccount002/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#MstCaptialAccount001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/basicdata/mstcaptialaccount/MstCaptialAccount001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/basicdata/mstcaptialaccount/MstCaptialAccount001/delete', object, "MstCaptialAccount001_btn_delete", "MstCaptialAccount002");
                });
            });
        });
    });
}());