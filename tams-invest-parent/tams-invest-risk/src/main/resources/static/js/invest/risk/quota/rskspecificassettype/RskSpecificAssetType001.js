$(function() {
    var columns = [
        {field:'specificId', visible: false}
        ,{field:'type1Name', sortField:"TYPE_1", width:140 , title: 'RskSpecificAssetType001.type1'}
        ,{field:'type2Name', sortField:"TYPE_2", width:140 , title: 'RskSpecificAssetType001.type2'}
        ,{field:'type3Name', sortField:"TYPE_3", width:140 , title: 'RskSpecificAssetType001.type3'}
        ,{field:'type4Name', sortField:"TYPE_4", width:140 , title: 'RskSpecificAssetType001.type4'}
        ,{field:'finprodMarketId', sortField:"FINPROD_MARKET_ID", width:140 , title: 'RskSpecificAssetType001.finprodId'}
        ,{field:'finprodName', sortField:"FINPROD_NAME", width:140 , title: 'RskSpecificAssetType001.finprodName'}
        ,{field:'isBadassetName', sortField:"IS_BADASSET", width:150 , title: 'RskSpecificAssetType001.isBadasset'}
        ,{field:'isRegulationName', sortField:"IS_REGULATION", width:150 , title: 'RskSpecificAssetType001.isRegulation'}
        ,{field:'isCreditAssetName', sortField:"IS_CREDIT_ASSET", width:150 , title: 'RskSpecificAssetType001.isCreditAsset'}
        ,{field:'isFlowLimitedStr', sortField:"IS_FLOW_LIMITED", width:150 , title: '是否流动性受限'}
        ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
    ];
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var param = $.serializeToObject('#searchForm');
        if(param.type1){
            param.type1 = $('#type1').val().join(",");
        }
        if(param.type2){
            param.type2 = $('#type2').val().join(",");
        }
        if(param.type3){
            param.type3 = $('#type3').val().join(",");
        }
        if(param.type4){
            param.type4 = $('#type4').val().join(",");
        }
        return param;
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
        /**
         * 双击查看
         */
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                basePath + "invest/risk/quota/rskspecificassettype/RskSpecificAssetType002/init",
                "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,specificId:data.specificId});
        },

        cols:columns
    });
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'invest/risk/quota/rskspecificassettype/RskSpecificAssetType001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("RskSpecificAssetType001"),
                basePath + "invest/risk/quota/rskspecificassettype/RskSpecificAssetType002/init",
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
        Joyin.ajaxJson(basePath + 'invest/risk/quota/rskspecificassettype/RskSpecificAssetType001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/risk/quota/rskspecificassettype/RskSpecificAssetType001/delete', object,
                    "RskSpecificAssetType001_btn_delete", "RskSpecificAssetType002",function() {
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
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#RskSpecificAssetType001_btn_search').click(function() {
            table.load();
            $('#RskSpecificAssetType001_btn_delete').show();
        }).click();
        $('#RskSpecificAssetType001_btn_export').click(function() {
            exportExcel();
        });
        $('#RskSpecificAssetType001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("RskSpecificAssetType001"),
                basePath + "invest/risk/quota/rskspecificassettype/RskSpecificAssetType002/init",
                "98%", "68%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#RskSpecificAssetType001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/risk/quota/rskspecificassettype/RskSpecificAssetType001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/risk/quota/rskspecificassettype/RskSpecificAssetType001/delete', object,
                        "RskSpecificAssetType001_btn_delete","RskSpecificAssetType002",function () {
                            table.reload();//删除事件请求及回调函数
                        });
                });
            });
        });

    });
}());