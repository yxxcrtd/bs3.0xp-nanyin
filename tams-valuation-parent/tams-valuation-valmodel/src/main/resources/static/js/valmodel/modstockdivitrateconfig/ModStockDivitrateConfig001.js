/**
 * 预期股息增长率配置
 */
(function(){
    /**
     * 获取查询参数
     */
	var getSearchParams = function() {
        return {
        	finprodId: $('#finprodId').val()
        }
    };
    //新建
    function showDialog() {
        Joyin.dialog('永续固收模型配置新建', basePath +'valmodel/modstockdivitrateconfig/ModStockDivitrateConfig002/init', '50%', '75%');
    }
    
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
                JoyinUtil.getRealPath("valmodel/modstockdivitrateconfig/ModStockDivitrateConfig002/init"),
                "50%", "75%",{pageModel: PAGE_MODEL_REFERENCE,seqno: data.seqno},function(){table.reload();});
        },

        cols:[
             
            {field: 'seqno', visible: false}
            ,{field:'finprodId',  width:200, title: 'ModStockDivitrateConfig001.finprodId',sortable:false},
            {field:'finProdName',  title: 'ModStockDivitrateConfig001.finProdName',sortable:false}
            ,{field:'secDetailTypeModelStr',  width:200,title: 'ModStockDivitrateConfig001.secTypeModel',sortable:false}
            ,{field:'secTypeModelStr', title: 'ModStockDivitrateConfig001.secDetailTypeModel',sortable:false,visible: false}
            ,{field:'stockIntarte', width:200, title: 'ModStockDivitrateConfig001.stockIntarte', type:'number',digit:4, sortable:false}
            ,{field:'action', title: '操作' , type:'action',buttons: ['edit', 'delt'] , width:150}
        ],
    });
    
    var exportExcel = function(){       
        Joyin.exportExcel('export',getSearchParams()) 
    };
  
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel=PAGE_MODEL_EDIT;
        data.operateType='edit'
        Joyin.ajaxJson(basePath + 'valmodel/modstockdivitrateconfig/ModStockDivitrateConfig002/check', data, function(result) {
            Joyin.dialog('永续固收模型配置编辑',
                    basePath + "valmodel/modstockdivitrateconfig/ModStockDivitrateConfig002/init",
                    "50%", "75%",data,
                    function(){table.reload();});
        });
        
    });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
    	var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'valmodel/modstockdivitrateconfig/ModStockDivitrateConfig001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'valmodel/modstockdivitrateconfig/ModStockDivitrateConfig001/delete', object, "ModStockDivitrateConfig001_btn_delete", "ModStockDivitrateConfig002",function(){
                    table.reload();
                });
            });
        });
    });

    $(function () {
        // 绑定按钮事件
        $('#ModStockDivitrateConfig001_btn_search').click(function() {
            table.load();
            $('#ModStockDivitrateConfig001_btn_delete').show();
        });
        /*$('#ModStockDivitrateConfig001_btn_export').click(function() {
            exportExcel();
        });*/
		$('#ModStockDivitrateConfig001_btn_export').hide();
        $('#ModStockDivitrateConfig001_btn_add').click(function() {
            showDialog();
        });
        $('#ModStockDivitrateConfig001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'valmodel/modstockdivitrateconfig/ModStockDivitrateConfig001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'valmodel/modstockdivitrateconfig/ModStockDivitrateConfig001/delete', object, "ModStockDivitrateConfig001_btn_delete", "ModStockDivitrateConfig002");
                });
            });
        });
    });
}());