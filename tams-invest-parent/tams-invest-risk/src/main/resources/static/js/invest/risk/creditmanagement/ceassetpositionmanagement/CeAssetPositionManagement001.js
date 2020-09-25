$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
//        var param = $.serializeToObject('#searchForm');
        return  $.serializeToObject('#searchForm');
    };

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({ 
        ajax: {
            url: 'findPage',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getSearchParams();//获取请求参数
            }//ajax请求数据
        },
        /**
         * 定义表格名称
         */
        cols:[
            {field:'ptlId', title: 'CeAssetPositionManagement001.portfolioId'}//委外组合代码
            ,{field:'assetType', title: 'CeAssetPositionManagement001.assetType'}//资产类型
            ,{field:'finprodId', title: 'CeAssetPositionManagement001.assetCode'}//资产代码
            ,{field:'finprodName', title: 'CeAssetPositionManagement001.assetName'}//资产名称
            ,{field:'valDate', title: 'CeAssetPositionManagement001.positionDay', type:'date'}//持仓日
            ,{field:'assetPosition', title: 'CeAssetPositionManagement001.openInterest'}//持仓量
//            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}//编辑和删除事件
            
        ]
    });
    
//    /**
//     * 绑定编辑事件
//     */
//    table.bind('click', '.edit', function(data, index) {
//        data.pageModel = PAGE_MODEL_EDIT;
//        Joyin.ajaxJson(basePath + 'invest/portfolio/ptlmanagementinfo/PtlManagementInfo001/check', data, function(result) {//数据验证
//            Joyin.dialog(JoyinUtil.getLabel("PtlManagementInfo002.edit"),
//                basePath +"invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
//                "98%", "90%", {pageModel:PAGE_MODEL_EDIT,//page_model:编辑
//                                portfolioId:data.portfolioId});//主键组合代码
//        });
//    });
    
//    /**
//     * 绑定删除事件
//     */
//    table.bind('click', '.delt', function(data, index) {
//        var object = {selectedItem: [data], operateType:"delete"};
//        Joyin.ajaxJson(basePath + 'invest/portfolio/ptlmanagementinfo/PtlManagementInfo001/check', object, function(result) {//数据验证
//            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
//                WorkFlow.doDeleteList(basePath + 'invest/portfolio/ptlmanagementinfo/PtlManagementInfo001/delete', object, "PtlManagementInfo001_btn_delete", "PtlManagementInfo002");//删除
//            });
//        });
//    });
    /**
     * 导出
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();//获取参数
        Joyin.exportExcel('export', paramObj);//导出
    };
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定查询按钮
        $('#CeAssetPositionManagement001_btn_search').click(function() {
            table.load();//加载表格
        });
        //绑定导入事件
//        $('#CeAssetPositionManagement001_btn_import').click(function() {
//            Joyin.confirm("请确认是否要导入数据？", function() {
//                Joyin.uploadFile(function(fileName) {
//                    Joyin.ajax(basePath + "invest/risk/creditmanagement/ceassetpositionmanagement/CeAssetPositionManagement001/importFile", {fileName: fileName});
//                },function() {
//                    table.load();
//                });
//            });
//        });
        //绑定导出按钮
        $('#CeAssetPositionManagement001_btn_export').click(function() {
            exportExcel();//导出
        });
//        //绑定新建按钮
//        $('#CeAssetPositionManagement001_btn_import').click(function() {
//            Joyin.dialog(JoyinUtil.getLabel("CeAssetPositionManagement002"),
//                    basePath+ "invest/risk/creditmanagement/ceassetpositionmanagement/CeAssetPositionManagement002/init",
//                        "60%", "70%", {pageModel:PAGE_MODEL_NEW});
//        });
        //导入
        $('#CeAssetPositionManagement001_btn_import').click(function() {
            Joyin.confirm(JoyinUtil.getLabel("请确认是否要导入数据"), function() {
                Joyin.uploadFile(function(fileName) {
                    Joyin.ajax(basePath + "invest/risk/creditmanagement/ceassetpositionmanagement/CeAssetPositionManagement002/importFile", {
                        fileName : fileName
                    },function(result){
                        if(!result || !($.isArray(result) && result.length > 0)) {
                            layui.layer.closeAll()
                            table.reload();
                        }
                    });
                });
            });
        });
    });
}());