/**
 * RskEvent001
 */
(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var param = $.serializeToObject('#searchForm');
        if(param.eventCode) {
        	param.eventCode = param.eventCode.join(",");
        }
        else{
            param.eventCode=null;
        }
        return param;
    };
    
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: "findPage",
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
                basePath + "invest/riskredis/quota/rskevent/RskEvent002/init",
                "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,
                    eventCode: data.eventCode});
        },
        cols:[
            {field:'eventCode',title: 'RskEvent001.eventCode'}
            ,{field:'eventName',title: 'RskEvent001.eventName'}
            ,{field:'updateTime',title:'RskEvent001.updateDate',type:'date'}
            ,{field:'remark', title: 'RskEvent001.remark'}
/*            ,{field:'relateParam',width:120,title: 'RskEvent001.relPara',sortable:false, render:function(data, type, row, meta){
                return '<a title="RskEvent001.setRelPara" class="relParamEdit" style="font-size:12px;">设置</a>' +
                    '<a title="RskEvent001.queryRelPara" class="relParamQuery" style="margin-left:5px;font-size:12px;">查看</a>';
                }}*/
            ,{field:'relateParam',width:200,title: 'RskEvent001.relPara',sortable:false, render:function(data, type, row, meta){
                var buttons = '';
                if(purviewElements.indexOf('_manual')!=-1){
                       buttons += '<a title="设置" class="btn layui-btn manual" style="font-size:12px;color:white">设置</a>';
                 }
                if(purviewElements.indexOf('_batch')!=-1){
                		buttons += '<a title="查看" class="btn layui-btn batch" style="margin-left:5px;font-size:12px;color:white">查看</a>';
                }

                return buttons;
            		}
            	}

            
            ,{field:'operation',width:100,title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });

    /**
     * 绑定编辑关联指标参数事件
     */
    table.bind('click', '.manual', function(data, index) {
        Joyin.dialog(JoyinUtil.getLabel("RskEvent001.editRelPara"),
            basePath + "invest/riskredis/quota/rskevent/RskEvent003/init",
            "95%", "90%", {pageModel:PAGE_MODEL_EDIT,
                eventCode: data.eventCode,eventName: data.eventName},function() {
                table.reload();
            });
    });

    /**
     * 绑定编辑关联指标参数事件
     */
    table.bind('click', '.batch', function(data, index) {
        Joyin.dialog(JoyinUtil.getLabel("RskEvent001.editRelPara"),
            basePath + "invest/riskredis/quota/rskevent/RskEvent003/init",
            "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,
                eventCode: data.eventCode,eventName: data.eventName},function() {
                table.reload();
            });
    });

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
    	data.operateType = 'edit';
        data.startWfType="modify";
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.dialog(JoyinUtil.getLabel("RskEvent001.edit"),
                basePath + "invest/riskredis/quota/rskevent/RskEvent002/init",
                "95%", "90%", {pageModel:PAGE_MODEL_EDIT,
                    eventCode: data.eventCode},function() {
                    table.reload();
                });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
    	 var object = {selectedItem: [data], operateType:"delete"};
         Joyin.ajaxJson(basePath + 'invest/riskredis/quota/rskevent/RskEvent002/check', object, function(result) {
             Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                 WorkFlow.doDeleteList(basePath + 'invest/riskredis/quota/rskevent/RskEvent001/delete', object,
                         "RskEvent001_btn_delete", "RskEvent002",function() {
                     table.reload();
                 });
             });
         });
    });
    
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
    	var paramObj = getSearchParams();//请求参数json格式
        Joyin.exportExcel('export', paramObj);
    };


    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#RskEvent001_btn_search').click(function() {
            table.load();
        });
        $('#RskEvent001_btn_export').click(function() {
            exportExcel();
        });
        $('#RskEvent001_btn_create').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("RskEvent002"),
                basePath + "invest/riskredis/quota/rskevent/RskEvent002/init",
                "95%", "90%", {pageModel:PAGE_MODEL_NEW, operateType:'create'},function () {
                    table.reload();
                });
        });
        $('#RskEvent001_btn_delete').click(function() {
            var selArr = table.getAllCheckedRows();
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/riskredis/quota/rskevent/RskEvent002/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/riskredis/quota/rskevent/RskEvent001/delete', object,
                            "RskEvent001_btn_delete","RskEvent002");
                });
            });
        });
    });

}());