(function() {
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var templateId = $('#templetName').val();
    	if(templateId){
    		templateId = templateId.join(',');
    	}
        return {
        	templetName: templateId,
        	dealMode: $('#dealMode').val(),
        	profitType: $('#profitType').val(),
        }
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
            Joyin.dialog(JoyinUtil.getLabel("FinProductModule002.refrence"),
                    basePath + "invest/product/productelement/module/FinProductModule002/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,templateId:data.templateId});
        },
        
        cols:[
             {field:'finprodId', width:150 , title: 'FinProductModule001.finprodId',visible: false}
            ,{field:'templateId', width:200 , title: 'FinProductModule001.templetId'}
            ,{field:'templateName', width:150 , title: 'FinProductModule001.templetName'}
            ,{field:'dealModeStr', width:150 , sortField:"dealMode", title: 'FinProductModule001.dealMode'}
            ,{field:'profitTypeStr', width:150 , sortField:"profitType",title: 'FinProductModule001.profitType'}
            ,{field:'operationTypeStr', width:150 , sortField:"operationType",title: 'FinProductModule001.operationType'}
            ,{field:'profitFlagStr', width:150 ,title: 'FinProductModule001.prodProfitFlag',sortField:"profitFlag"}  
            ,{field:'remark', width:150 ,title: 'FinProductModule001.remark'}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = 'edit';
        Joyin.ajaxJson('/invest/product/productelement/module/FinProductModule001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("FinProductModule002.edit"),
                    basePath + "invest/product/productelement/module/FinProductModule002/init",
                    "95%", "90%",{templateId : data.templateId, pageModel : PAGE_MODEL_EDIT},function() {
                                        table.reload();
                                    });
        });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
    	//data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = 'delete';
        Joyin.ajaxJson('/invest/product/productelement/module/FinProductModule001/check', data, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/product/productelement/module/FinProductModule001/delete', data, 
                        "FinProductModule001_btn_delete", "FinProductModule002",function() {
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
        Joyin.dialog(JoyinUtil.getLabel("FinProductModule002"),
                JoyinUtil.getRealPath("invest/product/productelement/module/FinProductModule002/init"),
                "98%", "95%",{pageModel : PAGE_MODEL_NEW},function(){table.reload();}
        );
    }

    /**
     * 画面加载完成
     */
    $(function() {
    	// 绑定按钮事件
        $('#FinProductModule001_btn_search').click(function() {
            table.load();
            $('#FinProductModule001_btn_delete').show();
        });
        $('#FinProductModule001_btn_export').click(function() {
            exportExcel();
        });
        $('#FinProductModule001_btn_add').click(function() {
            openNew();
        });
        $('#FinProductModule001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var selectedItem = [];
            $.each(selArr,function(i,v){
                v.finprodName = v.templetName;
            	selectedItem.push({productModuleInfo:v});
            })
            var object = {selectedItem: selectedItem, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/product/productelement/module/FinProductModule001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/product/productelement/module/FinProductModule001/delete', object, 
                            "FinProductModule001_btn_delete","FinProductModule002");
                });
            });
        });
    });
}());