(function() {
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var param = $.serializeToObject('#searchForm');
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
        
        cols:[
              {field:'xuhao', width:50 ,title: 'ObjectLimitOccupation001.xuhao'}
             ,{field:'seqno', width:50 ,visible: false}
             ,{field:'customerId', width:50 ,visible: false}
             ,{field:'customerName', width:150 , title: 'ObjectLimitOccupation001.object'}
             ,{field:'creditType', width:50 ,visible: false}
             ,{field:'creditTypeName', width:150 , title: 'ObjectLimitOccupation001.creditName'}
             ,{field:'term', width:50 ,visible: false}
             ,{field:'termUnit', width:50 ,visible: false}
             ,{field:'termUnitName', width:150 , title: 'ObjectLimitOccupation001.termUnitName'}
             ,{field:'ajustTypeName', width:150 , title: 'ObjectLimitOccupation001.ajustType'}
             ,{field:'amount', width:150 , type:'number', title: 'ObjectLimitOccupation001.totleamount'}
             ,{field:'cdate', width:150, title: 'ObjectLimitOccupation001.cdate', type: 'date'}
             ,{field:'rdate', width:150, title: 'ObjectLimitOccupation001.rdate', type: 'date'}
             ,{field:'creditMstId', width:50 ,visible: false}
             ,{field:'createUser', width:50 ,visible: false}
             ,{field:'realName', width:150 , title: 'ObjectLimitOccupation001.createUser'}
             ,{field:'remark', width:150 , title: 'ObjectLimitOccupation001.remark'}
             ,{field:'operation',width:150 , title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
         ]
    });    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
    	if ($("#createUser").val() && $("#createUser").val() != data.createUser) {
    		Joyin.error(JoyinUtil.getMessage("RSK_E_0088"));
    		return;
    	}
        data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = 'edit';
        data.startWfType="modify";
        Joyin.ajaxJson(basePath + 'invest/risk/creditrisklimitmanagement/objectlimitoccupation/ObjectLimitOccupation002/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("SingleLimitOccupation002.edit"),
                    basePath + "invest/risk/creditrisklimitmanagement/objectlimitoccupation/ObjectLimitOccupation002/init",
                    "95%", "90%",data,function() {
                                        table.reload();
                                    });
        });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
    	if ($("#createUser").val() && $("#createUser").val() != data.createUser) {
    		Joyin.error(JoyinUtil.getMessage("RSK_E_0088"));
    		return;
    	}
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/risk/creditrisklimitmanagement/objectlimitoccupation/ObjectLimitOccupation002/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/risk/creditrisklimitmanagement/objectlimitoccupation/ObjectLimitOccupation003/delete', object, 
                        "ObjectLimitOccupation003_btn_delete", "SingleLimitOccupation002",function() {
                    table.reload();
                });
            });
        });
    });

    
    /**
     * 画面加载完成
     */
    $(function() {
    	table.load();
    });
}());