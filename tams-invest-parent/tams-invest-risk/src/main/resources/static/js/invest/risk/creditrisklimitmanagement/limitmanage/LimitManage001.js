(function() {
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
        	   var result = $.serializeToObject('#searchForm');
               if ($("#customerName").val()) {
                   result.customerId = $("#customerName").val().join(",");
               } else {
                   result.customerId = null;
               }
               if ($("#orgType").val()) {
                   result.orgType = $("#orgType").val().join(",");
               } else {
                   result.orgType = null;
               }
               if ($("#orgLevel").val()) {
                   result.orgLevel = $("#orgLevel").val().join(",");
               } else {
                   result.orgLevel = null;
               }
               result.ccy= $('#ccy').val();
               result.codeStatus= $('#codeStatus').val();

            return result;
    };
    /**
     * 定义数据表格
     */
    var table=$("#dataTable2").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        /**
         * 双击查看
         */
        rowno: true,
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/risk/creditrisklimitmanagement/limitmanage/LimitManage002/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,customerId:data.customerId,pflsh:data.pflsh,mdate:data.mdate,ccy:data.ccy});
        },
        
        cols:[
             {field:'seqno', width:10 ,visible: false}
            ,{field:'pflsh', width:150 , title: 'LimitManage001.pflsh', sortField:'A.PFLSH'}
            ,{field:'customerName', width:150 , title: 'LimitManage001.customerName', sortField:'C.CUSTOMER_NAME'}
            ,{field:'customerId', width:150 , title: 'LimitManage001.customerId', sortField:'A.CUSTOMER_ID'}
            ,{field:'ccy',visible: false, width:150 , title: 'LimitManage001.ccy', sortField:'A.ccy'}
            ,{field:'ccyText', width:150 , title: 'LimitManage001.ccy', sortField:'A.ccy'}
            ,{field:'creditAmt',width:150 ,  title: 'LimitManage001.creditAmt', sortField:'A.CREDIT_AMT'}
            ,{field:'mdate',width:150 ,  title: 'LimitManage001.mdate', type:'date', sortField:'A.MDATE'}
            ,{field:'orgType',width:150 ,  title: 'LimitManage001.orgType', sortField:'A.customerClass'}
            ,{field:'orgLevel',width:150 ,  title: 'LimitManage001.orgLevel', sortField:'A.customerLevel'}
            ,{field:'codeStatusText',width:150 ,  title: 'LimitManage001.status'}
            ,{field:'codeStatus',visible: false,width:10 ,  title: 'LimitManage001.status'}
            ,{field:'operation',width:150 , title: 'SysCommon.operation', type:'action', buttons: ['edit']}
        ]
    });    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        var object = {};
    	object.pageModel = PAGE_MODEL_EDIT;
        object.operateType = 'edit';
        object.pflsh=data.pflsh;
        object.mdate=new Date(data.mdate);
        object.customerId=data.customerId;
        object.ccy=data.ccy;
        Joyin.ajaxJson(basePath + 'invest/risk/creditrisklimitmanagement/limitmanage/LimitManage001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("LimitManage002.edit"),
                    basePath + "invest/risk/creditrisklimitmanagement/limitmanage/LimitManage002/init",
                    "99%", "99%",object,function() {
                                    });
        });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/risk/creditrisklimitmanagement/limitmanage/LimitManage001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/risk/creditrisklimitmanagement/limitmanage/LimitManage001/delete', object, 
                        "LimitManage001_btn_delete", "LimitManage002",function() {
                });
            });
        });
    });

    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    /**
     * 弹出新建画面
     * @returns
     */
    function openNew() {
        Joyin.dialog(JoyinUtil.getLabel("LimitManage002"),
                JoyinUtil.getRealPath("invest/risk/creditrisklimitmanagement/limitmanage/LimitManage002/init"),
                "99%", "99%",{pageModel : PAGE_MODEL_NEW},function(){}
        );
    }
    
    /**
     * 画面加载完成
     */
    $(function() {
    	// 绑定按钮事件
        $('#LimitManage001_btn_search').click(function() {
            table.load();
            $('#LimitManage001_btn_delete').show();
        });
        $('#LimitManage001_btn_export').click(function() {
            exportExcel();
        });
        $('#LimitManage001_btn_add').click(function() {
            openNew();
        });
        
        $('#LimitManage001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/risk/creditrisklimitmanagement/limitmanage/LimitManage001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/risk/creditrisklimitmanagement/limitmanage/LimitManage001/delete', object, 
                            "LimitManage001_btn_delete","LimitManage002");
                });
            });
        });
    });
}());