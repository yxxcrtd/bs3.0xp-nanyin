/**
 * 项目类投资委托查询
 */
(function(){
    /**
     * 获取查询参数
     */
    var searchform =function(){ 
        var data = $.serializeToObject('#searchForm');
        if($('#ccy').val()){
            data.ccy = $('#ccy').val();
        }
        if($('#portfolioId').val()){
            data.portfolioId = $('#portfolioId').val().join(",");
        }
        return data;
    };
    /** 
     * 新建
     */
    function showDialog() {
        Joyin.dialog(JoyinUtil.getLabel('EtrProject002'), basePath +'invest/entrust/etrproject/EtrProject002/init', '98%', '95%',function(){table.reload();});
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
            Joyin.dialog(JoyinUtil.getLabel("EtrProject002.reference"),
                    JoyinUtil.getRealPath("invest/entrust/etrproject/EtrProject002/init"),
                    "98%", "95%", data,
                    function(){table.reload();});
        },
        cols:[
            {field:'entrustStatus',visible:false}
            ,{field:'busEntrustStatus', title: 'EtrProject001.entrustStatus', width:'10%',sortField:"entrustStatus"}
            ,{field:'entrustId',visible:false}
            ,{field:'portfolioId', title: 'EtrProject001.portfolioId', width:'10%',
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link portfolioIdLink' style='cursor: pointer;'>"+row.portfolioId+"</ a>";
                },sortField:"portfolioId"
            }
            ,{field:'portfolioName', title: 'EtrProject001.portfolioName', width:'10%',sortField:"portfolioName"}
            ,{field:'assetType1',visible:false}
            ,{field:'busAssetType1', title: 'EtrProject001.assetType1', width:'10%',sortField:"assetType1",visible:false}
            // 项目类资产五级分类改造 add by renping 2019-08-01 start
            ,{field:'type9Name', title: 'EtrProject001.type9', width:'10%',sortField:"type9"}
            ,{field:'type10Name', title: 'EtrProject001.type10', width:'10%',sortField:"type10"}
            ,{field:'type11Name', title: 'EtrProject001.type11', width:'10%',sortField:"type11",visible:IS_SHOW_TYPE == '01'?true:false}
            // 项目类资产五级分类改造 add by renping 2019-08-01 end
            ,{field:'finprodId', visible:false}
            ,{field:'finprodMarketId', title: 'EtrProject001.finprodId', width:'10%',
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link finprodIdLink' style='cursor: pointer;'>"+data+"</ a>";
                },sortField:"finprodMarketId"
            }
            ,{field:'finprodName', title: 'EtrProject001.finprodName', width:'10%',sortField:"finprodName"}
//            新增输出项投资经理 yzg 20190903 start
            ,{field:'invManagerId', title: 'EtrProject001.invManagerId', width:'10%',sortField:"invManagerId"}
            // 新增输出项投资经理 yzg 20190903 end
            ,{field:'ps', visible:false}
            ,{field:'busPs', title: 'EtrProject001.ps', width:'10%',sortField:'position',sortField:"ps"}
            ,{field:'ccy',visible:false}
            ,{field:'busCcy', title: 'EtrProject001.ccy', width:'10%',sortField:"ccy"}
            ,{field:'vdate', title: 'EtrProject001.vdate', width:'10%',type:'date',sortField:"vdate"}
            ,{field:'mdate', title: 'EtrProject001.mdate', width:'10%',type:'date',sortField:"mdate"}
            ,{field:'entrustAmt',type:'number', title: 'EtrProject001.entrustAmt', width:'10%',sortField:"entrustAmt"}
            ,{field:'finishAmt',type:'number', title: 'EtrProject001.finishAmt', width:'10%',sortField:"finishAmt"}
            ,{field:'transactioncom', title: 'EtrProject001.transactioncom',render : function(data, type, row, meta){
                return "<div class='layui-progress layui-progress-big' style='margin-top:5px;'><div class='layui-progress-bar layui-bg-green' style='width:"+(row.finishAmt/row.entrustAmt*100).toFixed(2)+"%'><span class='layui-progress-text'>"+(row.finishAmt/row.entrustAmt*100).toFixed(2)+"%</span></div></div>";
            }, width:'10%',sortable:false}
            ,{field:'action', title: '操作' , type:'action',buttons: ['edit'] , width:'10%'}
            ],
    });
    /**
     * 导出
     */
    var exportExcel = function(){       
        Joyin.exportExcel(basePath+"invest/entrust/etrproject/EtrProject001/export", searchform());
    };

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.operateType = "edit";
        data.pageModel=PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'invest/entrust/etrproject/EtrProject002/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("EtrProject002.edit"),
                    JoyinUtil.getRealPath("invest/entrust/etrproject/EtrProject002/init"),
                    "98%", "95%", data,
               function(){table.reload();});
        });
    });
//    /**
//     * 绑定删除事件
//     */
//    table.bind('click', '.delt', function(data, index) {
//        var object = {selectedItem: [data], operateType:"delete"};
//        Joyin.ajaxJson(basePath + 'invest/entrust/etrproject/EtrProject001/check', object, function(result) {
//            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
//               Joyin.ajaxJson(basePath + 'invest/entrust/etrproject/EtrProject001/delete', object, function() {
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
    table.bind('click','.finprodIdLink', function(row) {
        //在新建界面进行显示单行的详细信息
        Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                basePath + "invest/asset/project/FinProjectTargetAsset002/init",
                "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,
                                finprodId:row.finprodId,  //金融产品代码
                                //valDate:data.valDate,//估值日
                                //finprodMarketId:data.finprodMarketId//市场代码
                                });
    });
    /**
     * 编辑按钮事件
     */
    $(function () {
        /**
         * 查询按钮
         */
        $('#EtrProject001_btn_search').click(function() {
            table.load();
            $('#EtrProject001_btn_close').show();
            $('#EtrProject001_btn_revoke').show();
        });
        /**
         * 导出按钮
         */
        $('#EtrProject001_btn_export').click(function() {
            exportExcel();
        });
        /**
         * 新建
         */
        $('#EtrProject001_btn_add').click(function() {
            showDialog();
        });
        /**
         * 关闭委托
         */
        $('#EtrProject001_btn_close').click(function() {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
             }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
             }else {
                var object = {selectedItem: selArr, operateType:"close"};
                Joyin.ajaxJson(basePath + 'invest/entrust/etrproject/EtrProject001/check', object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage("ETR_C_0002"), function(){
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrproject/EtrProject001/close', object, function(result) {
                            table.reload();
                        });
                    });
                });
             }
        });
        /**
         * 撤销
         */
        $('#EtrProject001_btn_revoke').click(function() {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
             }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
             }else {
                var object = {selectedItem: selArr, operateType:"recall"};
                Joyin.ajaxJson(basePath + 'invest/entrust/etrproject/EtrProject001/check', object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage("BUSS_COM_C_0032","撤销"), function(){
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrproject/EtrProject001/recall', object, function(result) {
                            table.reload();
                        });
                    });
                });
             }
        });
      //是否展示资产四五级分类
        if(IS_SHOW_TYPE == '01'){
        	$('#assetType11Div').show();
        }else{
        	$('#assetType11Div').hide();
        }

        // 导入按钮点击事件
        $('#EtrProject001_btn_import').click(function() {
            // Joyin.confirm("请确认是否要导入数据？", function() {
                Joyin.uploadFile({pageId:'EtrProject003MTM'},function(fileName) {
                    Joyin.ajax(basePath + 'invest/entrust/etrproject/EtrProject003/checkImportData', {fileName: fileName}, function(checkResult) {
                                setTimeout( function () {
                                    Joyin.confirm(checkResult.data.confirmMsg,function() {
                                        var result={};
                                        result.operateType = "import";
                                        result.pageModel='';
                                        result.tempList = checkResult.data.correctData;
                                        Joyin.dialog(JoyinUtil.getLabel("EtrProject003"),
                                            JoyinUtil.getRealPath("/invest/entrust/etrproject/EtrProject003/init"),
                                            "98%", "95%", result,
                                            function(){table.reload();});
                                    });
                                    //数据是否大于100check
                                    if(checkResult.data.errorMsg){
                                        Joyin.error(checkResult.data.errorMsg);
                                        return;
                                    }
                                },1000);
                        }
                    );

                },function() {
                    table.reload();
                });
            // });
        });
    });
}());
