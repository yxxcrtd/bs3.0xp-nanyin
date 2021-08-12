(function() {
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var param = $.serializeToObject('#searchForm');
        if ($("#creditTag").val()) {
        	param.creditTag = $("#creditTag").val().join(",");
        } else {
        	param.creditTag = null;
        }
        return param;
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
        /**
         * 双击查看
         */
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/risk/creditmanagement/ceassetgroup/SingleLimitOccupation002/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,creditRuleId:data.creditRuleId});
        },
        
        cols:[
              {field:'seqno', width:50 ,title: 'SingleLimitOccupation001.seqno'}
             ,{field:'finprodId', width:150 , title: 'SingleLimitOccupation001.finprodId'}
             ,{field:'finprodName', width:150 , title: 'SingleLimitOccupation001.finprodName'}
             ,{field:'astType1Name', width:150 , title: 'SingleLimitOccupation001.finprodType1'}
             ,{field:'astType2Name', width:150 , title: 'SingleLimitOccupation001.finprodType2'}
             ,{field:'astType3Name', width:150 , title: 'SingleLimitOccupation001.finprodType3'}
             ,{field:'astType4Name', width:150 , title: 'SingleLimitOccupation001.assestType'}
             ,{field:'deductObjTypeName', width:150 ,title: 'SingleLimitOccupation001.deTarget'}
             ,{field:'creditTagName', width:150 ,title: 'SingleLimitOccupation001.creditTag'}
             ,{field:'remark', width:150 ,title: 'SingleLimitOccupation001.remark'}
             ,{field:'creditRuleId', width:50 ,visible: false}
             ,{field:'operation',width:150 , title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
         ]
    });    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = 'edit';
        data.startWfType="modify";
        Joyin.ajaxJson(basePath + 'invest/risk/creditrisklimitmanagement/singlelimitoccupation/SingleLimitOccupation001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("SingleLimitOccupation002.edit"),
                    basePath + "invest/risk/creditrisklimitmanagement/singlelimitoccupation/SingleLimitOccupation002/init",
                    "95%", "90%",data,function() {
                                        table.reload();
                                    });
        });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/risk/creditrisklimitmanagement/singlelimitoccupation/SingleLimitOccupation001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/risk/creditrisklimitmanagement/singlelimitoccupation/SingleLimitOccupation001/delete', object, 
                        "SingleLimitOccupation001_btn_delete", "SingleLimitOccupation002",function() {
                    table.reload();
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
        Joyin.dialog(JoyinUtil.getLabel("SingleLimitOccupation002"),
                JoyinUtil.getRealPath("invest/risk/creditrisklimitmanagement/singlelimitoccupation/SingleLimitOccupation002/init"),
                "95%", "90%",{pageModel : PAGE_MODEL_NEW},function(){table.reload();}
        );
    }
    
    /**
     * 画面加载完成
     */
    $(function() {
    	// 绑定按钮事件
        $('#SingleLimitOccupation001_btn_search').click(function() {
            table.load();
            $('#SingleLimitOccupation001_btn_delete').show();
        });
        $('#SingleLimitOccupation001_btn_export').click(function() {
            exportExcel();
        });
        $('#SingleLimitOccupation001_btn_add').click(function() {
            openNew();
        });
        
        $('#SingleLimitOccupation001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/risk/creditrisklimitmanagement/singlelimitoccupation/SingleLimitOccupation001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/risk/creditrisklimitmanagement/singlelimitoccupation/SingleLimitOccupation001/delete', object, 
                            "SingleLimitOccupation001_btn_delete","SingleLimitOccupation002");
                });
            });
        });
    });
}());