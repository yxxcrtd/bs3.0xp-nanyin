$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            counterType: $('#counterType').val(),
            counterId : $('#counterId').val(),
            counterName : $('#counterName').val()
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
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "common/basicdata/comtradeopponent/ComTradeOpponent002/init",
                    "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,
                                    counterType:data.counterType,
                                    counterId:data.counterId});
        },
        /*
         * 定义表头
         */  
        cols:[
             {field:'counterTypeStr',width:'25%', title: 'ComTradeOpponent001.counterType',sortField:'counterType'}
            ,{field:'counterId',width:'25%',  title: 'ComTradeOpponent001.counterId'}
            ,{field:'counterName',  title: 'ComTradeOpponent001.counterName'}
            // ,{field:'managerId',width:'20%',  title: 'MstTradeOpponent001.managerId'}
            // ,{field:'trusteeId',width:'20%',  title: 'MstTradeOpponent001.trusteeId'}
            // ,{field:'type1Str',width:'20%',  title: 'MstTradeOpponent001.type1',sortField:'type1'}
            // ,{field:'type2Str',width:'20%',  title: 'MstTradeOpponent001.type2',sortField:'type2'}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', /*buttons: ['edit', 'delt']*/
	        	render: function (data, type, row, meta) {
	                if (row.counterType == '05'){
	                    return "";
	                }
	                else{
	                    var str = '<i class="btn layui-icon edit" title="编辑">&#xe642;</i>      ';
	                    str = str + '<i class="btn layui-icon delt" title="删除">&#xe640;</i>';
	                    return str;
	                }
	            }
            }]
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.bankTrustCode = 'null';
        Joyin.ajaxJson(basePath + 'common/basicdata/comtradeopponent/ComTradeOpponent002/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("ComTradeOpponent002.edit"),
                    basePath + "common/basicdata/comtradeopponent/ComTradeOpponent002/init",
                    "95%", "80%", {pageModel:PAGE_MODEL_EDIT,
                                    counterType:data.counterType,
                                    counterId:data.counterId});
            });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'common/basicdata/comtradeopponent/ComTradeOpponent001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'common/basicdata/comtradeopponent/ComTradeOpponent001/delete', object, "ComTradeOpponent001_btn_delete", "ComTradeOpponent002",function(result){
                    table.reload();
                });
            });
        });
    });
    /**
     * 导出
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
        $('#ComTradeOpponent001_btn_search').click(function() {
            table.load();
            $('#ComTradeOpponent001_btn_delete').show();
        });
        $('#ComTradeOpponent001_btn_export').click(function() {
            exportExcel();
        });
        $('#ComTradeOpponent001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("ComTradeOpponent002"),
                        basePath + "common/basicdata/comtradeopponent/ComTradeOpponent002/init",
                        "95%", "95%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#ComTradeOpponent001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
             Joyin.ajaxJson(basePath + 'common/basicdata/comtradeopponent/ComTradeOpponent001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'common/basicdata/comtradeopponent/ComTradeOpponent001/delete', object, "ComTradeOpponent001_btn_delete", "ComTradeOpponent002",function(){
                        table.reload();
                    });
                });
             });
        });
    });
}());