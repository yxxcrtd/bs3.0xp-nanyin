(function() {
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
        	   var result = $.serializeToObject('#searchForm');
                   result.busiType = $("#busiType").val();        
                   result.approId = $("#approId").val();    
                   result.customerName= $('#customerName').val();
                   result.vdate= $('#vdate').val();
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
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/risk/creditrisklimitmanagement/objectlimitmanage/ObjectLimitManage002/init",
                    "65%", "80%", {pageModel:PAGE_MODEL_REFERENCE,seqno:data.seqno});
        },
        
        cols:[
             {field:'seqno', width:50 ,visible: false}
            ,{field:'creditMstId', width:50 ,visible: false}
            ,{field:'creditTagId', width:50 ,visible: false}
            ,{field:'customerId', width:150 , title: 'ObjectLimitManage001.customerId', sortField:'C.CUSTOMER_ID'}
            ,{field:'customerName', width:150 , title: 'ObjectLimitManage001.customerName', sortField:'C.CUSTOMER_NAME'}
            ,{field:'busiType',visible: false, width:10 , title: 'ObjectLimitManage001.busiType', sortField:'C.BUSI_TYPE'}
            ,{field:'busiTypeText', width:150 , title: 'ObjectLimitManage001.busiType', sortField:'C.BUSI_TYPE'}
            ,{field:'approId', width:150 , title: 'ObjectLimitManage001.approId', sortField:'C.APPOR_ID'}
            ,{field:'creditAmt', width:150 , title: 'ObjectLimitManage001.creditAmt', sortField:'C.CREDIT_AMT'}
            ,{field:'term', width:150 ,title: 'ObjectLimitManage001.term', sortField:'C.TERM'}           
            ,{field:'vdate', width:150 , title: 'ObjectLimitManage001.vdate', sortField:'C.VDATE',type:'date'}
            ,{field:'mdate', width:150 , title: 'ObjectLimitManage001.mdate', sortField:'C.MDATE',type:'date'}
            ,{field:'upTime', width:150 , title: 'ObjectLimitManage001.upTime', sortField:'C.upTime'}          
            ,{field:'codeStatus',visible: false,width:150 ,  title: 'ObjectLimitManage001.codeStatus', sortField:'codeStatus'}
            ,{field:'codeStatusText',width:150 ,  title: 'ObjectLimitManage001.codeStatus', sortField:'codeStatus'}
            ,{field:'operation',width:150 , title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = 'edit';
        Joyin.ajaxJson(basePath + 'invest/risk/creditrisklimitmanagement/objectlimitmanage/ObjectLimitManage002/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("ObjectLimitManage002.edit"),
                    basePath + "invest/risk/creditrisklimitmanagement/objectlimitmanage/ObjectLimitManage002/init",
                    "65%", "80%",data,function() {
                                    });
        });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/risk/creditrisklimitmanagement/objectlimitmanage/ObjectLimitManage001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/risk/creditrisklimitmanagement/objectlimitmanage/ObjectLimitManage001/delete', object, 
                        "ObjectLimitManage001_btn_delete", "ObjectLimitManage002",function() {
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
        Joyin.dialog(JoyinUtil.getLabel("ObjectLimitManage002"),
                JoyinUtil.getRealPath("invest/risk/creditrisklimitmanagement/objectlimitmanage/ObjectLimitManage002/init"),
                "65%", "80%",{pageModel : PAGE_MODEL_NEW},function(){}
        );
    }
    
    /**
     * 画面加载完成
     */
    $(function() {
    	// 绑定按钮事件
        $('#ObjectLimitManage001_btn_search').click(function() {
            table.load();
            $('#ObjectLimitManage001_btn_delete').show();
        });
        $('#ObjectLimitManage001_btn_export').click(function() {
            exportExcel();
        });
        $('#ObjectLimitManage001_btn_add').click(function() {
            openNew();
        });
        
        $('#ObjectLimitManage001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/risk/creditrisklimitmanagement/objectlimitmanage/ObjectLimitManage001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/risk/creditrisklimitmanagement/objectlimitmanage/ObjectLimitManage001/delete', object, 
                            "ObjectLimitManage001_btn_delete","ObjectLimitManage002");
                });
            });
        });
        
        // 导入按钮点击事件
        $('#ObjectLimitManage001_btn_import').click(function() {
        	var impType = '05';
        	if(impType == null || impType == ''){
        		Joyin.alert("请选择导入类型！");
        		return;
        	}
            Joyin.confirm("请确认是否要导入数据？", function() {
            	var options = {};
        		options.pageId = "OBQuotaImp001";
                Joyin.uploadFile(options,function(fileName) {
                	Joyin.ajax(basePath + 'invest/risk/creditrisklimitmanagement/objectlimitmanage/ObjectLimitManage001/importFile', {fileName: fileName,impType:'05'});
                },function() {
                    table.reload();
                });
            });
        });
        
    });
}());