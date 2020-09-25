/**
 * 项目类投资委托查询
 */
(function(){
    /**
     * 获取查询参数
     */
    var searchform =function(){ 
        var data = $.serializeToObject('#searchForm');
        if($('#portfolioId').val()){
            data.portfolioId = $('#portfolioId').val().join(",");
        }
        return data;
    };
    /** 
     * 新建
     */
    function showDialog() {
        Joyin.dialog(JoyinUtil.getLabel('EtrRepo002'), basePath +'invest/entrust/etrrepo/EtrRepo002/init', '98%', '95%',function(){table.reload();});
    }
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return searchform();
            }
        },
        dblclick:function(table, data) {
            data.operateType = "dblclick";
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("EtrRepo002.refrence"),
                    JoyinUtil.getRealPath("invest/entrust/etrrepo/EtrRepo002/init"),
                    "98%", "95%", data,
                    function(){table.reload();});
        },
        cols:[
            {field:'entrustStatus',visible:false}
            ,{field:'busEntrustStatus', title: 'EtrRepo001.entrustStatus', width:'10%',sortField:"entrustStatus"}
            ,{field:'entrustId',visible:false}
            ,{field:'portfolioId', title: 'EtrRepo001.portfolioId', width:'10%',
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link portfolioIdLink' style='cursor: pointer;'>"+row.portfolioId+"</ a>";
                },sortField:"portfolioId"
            }
            ,{field:'portfolioName', title: 'EtrRepo001.portfolioName', width:'10%',sortField:"portfolioName"}
            ,{field:'assetType1',visible:false}
            ,{field:'busAssetType1', title: 'EtrRepo001.assetType1', width:'10%',sortField:"assetType1"}
            ,{field:'termType', visible:false}
            ,{field:'busTermType', title: 'EtrRepo001.termType', width:'10%',sortField:"termType"}
            ,{field:'ps', visible:false}
            ,{field:'busPs', title: 'EtrRepo001.ps', width:'10%',sortField:'position',sortField:"ps"}
            ,{field:'price',type:'number',type:'number', title: 'EtrRepo001.price', width:'10%',sortField:'position',sortField:"price"}
            ,{field:'ccy',visible:false}
            ,{field:'busCcy', title: 'EtrRepo001.ccy', width:'10%',sortField:"ccy"}
            ,{field:'vdate', title: 'EtrRepo001.vdate', width:'10%',type:'date',sortField:"vdate"}
            ,{field:'mdate', title: 'EtrRepo001.mdate', width:'10%',type:'date',sortField:"mdate"}
            ,{field:'entrustAmt',type:'number',type:'number', title: 'EtrRepo001.entrustAmt', width:'10%',sortField:"entrustAmt"}
            ,{field:'finishAmt',type:'number',type:'number', title: 'EtrRepo001.finishAmt', width:'10%',sortField:"finishAmt"}
            ,{field: 'priceModelStr', title: 'EtrStock001.priceModel', width: '10%', sortField: "priceModel"} //新增价格模式	王贵奇	2019/10/10
            ,{field:'transactioncom', title: 'EtrRepo001.transactioncom',render : function(data, type, row, meta){
                return "<div class='layui-progress layui-progress-big' style='margin-top:5px;'><div class='layui-progress-bar layui-bg-green' style='width:"+(row.finishAmt/row.entrustAmt*100).toFixed(2)+"%'><span class='layui-progress-text'>"+(row.finishAmt/row.entrustAmt*100).toFixed(2)+"%</span></div></div>";
            }, width:'10%',sortable:false}
            ,{field:'action', title: '操作' , type:'action',buttons: ['edit'] , width:'10%'}
            ],
    });
    /**
     * 导出
     */
    var exportExcel = function(){       
        Joyin.exportExcel(basePath+"invest/entrust/etrrepo/EtrRepo001/export", searchform());
    };

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.operateType = "edit";
        data.pageModel=PAGE_MODEL_EDIT;
        data.calendarId="01";
        data.settleSpreed="01";
        data.tradeMarket="01";
        Joyin.ajaxJson(basePath + 'invest/entrust/etrrepo/EtrRepo002/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("EtrRepo002.edit"),
                    JoyinUtil.getRealPath("invest/entrust/etrrepo/EtrRepo002/init"),
                    "98%", "95%", data,
               function(){table.reload();});
        });
    });
//    /**
//     * 绑定删除事件
//     */
//    table.bind('click', '.delt', function(data, index) {
//        var object = {selectedItem: [data], operateType:"delete"};
//        Joyin.ajaxJson(basePath + 'invest/entrust/etrrepo/EtrRepo001/check', object, function(result) {
//            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
//               Joyin.ajaxJson(basePath + 'invest/entrust/etrrepo/EtrRepo001/delete', object, function() {
//                        table.reload();
//               });
//            });
//        });
//    });
    /**
     * 组合代码链接
     */
    table.bind('click','.portfolioIdLink', function(row) {
        Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
                "98%", "90%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
                portfolioId:row.portfolioId});//主键组合代码
    });
    /**
     * 资产代码链接
     */
//    table.bind('click','.finprodIdLink', function(row) {
//        //在新建界面进行显示单行的详细信息
//        Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
//                basePath + "asset/project/FinProjectTargetAsset002/init",
//                "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,
//                                finprodId:row.finprodId,  //金融产品代码
//                                //valDate:data.valDate,//估值日
//                                //finprodMarketId:data.finprodMarketId//市场代码
//                                });
//    });
    /**
     * 编辑按钮事件
     */
    $(function () {
        /**
         * 查询按钮
         */
        $('#EtrRepo001_btn_search').click(function() {
            table.load();
            $('#EtrRepo001_btn_close').show();
            $('#EtrRepo001_btn_revoke').show();
        });
        /**
         * 导出按钮
         */
        $('#EtrRepo001_btn_export').click(function() {
            exportExcel();
        });
        /**
         * 新建
         */
        $('#EtrRepo001_btn_add').click(function() {
            showDialog();
        });
        // 导入按钮点击事件
        $('#EtrRepo001_btn_import').click(function() {
            Joyin.uploadFile({pageId : 'EtrRepo003MTM'}, function(fileName) {
                Joyin.ajax(basePath + 'invest/entrust/etrrepo/EtrRepo003/checkImportData', {fileName: fileName}, function(checkResult) {
                    setTimeout( function () {
                        if(checkResult.data.errorMsg){
                            Joyin.error(checkResult.data.errorMsg);
                            return;
                        }
                        Joyin.confirm(checkResult.data.confirmMsg,function() {
                            var result = {};
                            result.tempList = checkResult.data.correctData;
                            Joyin.dialog(JoyinUtil.getLabel("EtrRepo003"),
                                JoyinUtil.getRealPath("/invest/entrust/etrrepo/EtrRepo003/init"),
                                "98%", "95%", result,
                                function(){table.reload();});
                        });
                    },1000);
                });
            },function() {
                table.reload();
            });
        });
        /**
         * 关闭委托
         */
        $('#EtrRepo001_btn_close').click(function() {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
             }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
             }else {
                var object = {selectedItem: selArr, operateType:"close"};
                Joyin.ajaxJson(basePath + 'invest/entrust/etrrepo/EtrRepo001/check', object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage("ETR_C_0002"), function(){
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrrepo/EtrRepo001/close', object, function(result) {
                            table.reload();
                        });
                    });
                });
             }
        });
        /**
         * 撤销
         */
        $('#EtrRepo001_btn_revoke').click(function() {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
             }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
             }else {
                var object = {selectedItem: selArr, operateType:"recall"};
                Joyin.ajaxJson(basePath + 'invest/entrust/etrrepo/EtrRepo001/check', object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage("BUSS_COM_C_0032","撤销"), function(){
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrrepo/EtrRepo001/recall', object, function(result) {
                            table.reload();
                        });
                    });
                });
             }
        });
        /**
         * 绑定质押券管理按钮事件
         */
        $('#EtrRepo001_btn_manage').click(function () {
            Joyin.dialog(JoyinUtil.getLabel("EtrStandardBond001"), basePath
                + "invest/entrust/etrstandardbond/EtrStandardBond001/init", "99%", "99%", {
                	pageModel: PAGE_MODEL_NEW
            	}, function () {
            });
        });
        
    });
}());