/**
 * 计划类货币委托查询
 */
(function(){
    /**
     * 获取查询参数
     */
    var searchform =function(){ 
       var data = $.serializeToObject('#searchForm');
       if($('#assetType1').val()){
           data.assetType1 = $('#assetType1').val().join(",");
       }
      /* 添加三-四级分类查询参数 lishuping 2019/7/29 start*/
        if($('#assetType10').val()){
            data.assetType10 = $('#assetType10').val().join(",");
        }
        if($('#assetType11').val()){
            data.assetType11 = $('#assetType11').val().join(",");
        }
        /* 添加三-四级分类查询参数 lishuping 2019/7/29 end*/
       if($('#portfolioId').val()){
           data.portfolioId = $('#portfolioId').val().join(",");
       }
       return data;
    };
    /** 
     * 新建
     */
    function showDialog() {
        Joyin.dialog(JoyinUtil.getLabel('EtrPlanAssetC002'), basePath +'invest/entrust/etrplanassetc/EtrPlanAssetC002/init', '98%', '95%',function(){table.reload();});
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
            Joyin.dialog(JoyinUtil.getLabel("EtrPlanAssetC002.refence"),
                    JoyinUtil.getRealPath("invest/entrust/etrplanassetc/EtrPlanAssetC002/init"),
                    "98%", "95%", data,
                    function(){table.reload();});
        },
        cols:[
            {field:'entrustStatus',visible:false}
            ,{field:'busEntrustStatus', title: 'EtrPlanAssetC001.entrustStatus', width:'10%',sortField:"entrustStatus"}
            ,{field:'entrustId',visible:false}
            ,{field:'portfolioId', title: 'EtrPlanAssetC001.portfolioId', width:'10%',
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link portfolioIdLink' style='cursor: pointer;'>"+row.portfolioId+"</ a>";
                },sortField:"portfolioId"
            }
            ,{field:'portfolioName', title: 'EtrPlanAssetC001.portfolioName', width:'10%',sortField:"portfolioName"}
            /*更改表头名称 lishuping 2019/7/29 start*/
            ,{field:'assetType1',visible:false}
            ,{field:'busAssetType1', title: 'EtrPlanAssetC001.type9', width:'10%',sortField:"assetType1"}
            /*更改表头名称 lishuping 2019/7/29 end*/
            /*添加三-四级表单头 lishuping 2019/7/29 start*/
            ,{field:'assetType10',visible:false}
            ,{field:'busAssetType10', title: 'EtrPlanAssetC001.type10', width:'10%',sortField:"assetType10"}
            ,{field:'assetType11',visible:false}
            ,{field:'busAssetType11', title: 'EtrPlanAssetC001.type11', width:'10%',sortField:"assetType11",visible:IS_SHOW_TYPE == '01'?true:false}
            /*添加三-四级表单头 lishuping 2019/7/29 end*/
            ,{field:'finprodId', visible:false}
            ,{field:'finprodMarketId', title: 'EtrPlanAssetC001.finprodId', width:'10%',
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link finprodIdLink' style='cursor: pointer;'>"+data+"</ a>";
                },sortField:"finprodMarketId"
            }
            ,{field:'finprodName', title: 'EtrPlanAssetC001.finprodName', width:'10%',sortField:"finprodName"}
            ,{field:'ps', visible:false}
            ,{field:'busPs', title: 'EtrPlanAssetC001.ps', width:'10%',sortField:"ps"}
            ,{field:'ccy',visible:false}
            ,{field:'busCcy', title: 'EtrPlanAssetC001.ccy', width:'10%',sortField:"ccy"}
            ,{field:'vdate', title: 'EtrPlanAssetC001.vdate', width:'10%',type:'date',sortField:"vdate"}
            ,{field:'mdate', title: 'EtrPlanAssetC001.mdate', width:'10%',type:'date',sortField:"mdate"}
            ,{field:'busEntrustAmt',type:'number', title: 'EtrPlanAssetC001.entrustAmt', width:'10%',sortField:"entrustAmt"}
            ,{field:'finishAmt',type:'number', title: 'EtrPlanAssetC001.finishAmt', width:'10%',sortField:"finishAmt"}
            ,{field:'transactioncom', title: 'EtrPlanAssetC001.transactioncom',render : function(data, type, row, meta){
                return "<div class='layui-progress layui-progress-big' style='margin-top:5px;'><div class='layui-progress-bar layui-bg-green' style='width:"+(row.finishAmt/row.busEntrustAmt*100).toFixed(2)+"%'><span class='layui-progress-text'>"+(row.finishAmt/row.busEntrustAmt*100).toFixed(2)+"%</span></div></div>";
            }, width:'10%',sortable:false}
            ,{field:'action', title: '操作' , type:'action',buttons: ['edit'] , width:'10%'}
            ],
    });
    /**
     * 导出
     */
    var exportExcel = function(){       
        Joyin.exportExcel(basePath+"invest/entrust/etrplanassetc/EtrPlanAssetC001/export", searchform());
    };

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.operateType = "edit";
        data.pageModel=PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'invest/entrust/etrplanassetc/EtrPlanAssetC001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("EtrPlanAssetC002.edit"),
                    JoyinUtil.getRealPath("invest/entrust/etrplanassetc/EtrPlanAssetC002/init"),
                    "98%", "95%", data,
               function(){table.reload();});
        });
    });
    /**
     * 绑定删除事件
     */
//    table.bind('click', '.delt', function(data, index) {
//        var object = {selectedItem: [data], operateType:"delete"};
//        Joyin.ajaxJson(basePath + 'invest/entrust/etrplanassetc/EtrPlanAssetC001/check', object, function(result) {
//            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
//               Joyin.ajaxJson(basePath + 'invest/entrust/etrplanassetc/EtrPlanAssetC001/delete', object, function() {
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
                 basePath
                + "invest/asset/plan/plancur/FinPlanAssetC002/init",
                "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,
                                finprodId:row.finprodId,  //金融产品代码
                                //valDate:data.valDate,//估值日
                                //finprodMarketId:data.finprodMarketId//市场代码
                                });
    });
    // add by zhangpuyu 2020/1/2 start 
    // 导入按钮点击事件
    $('#EtrPlanAssetC001_btn_import').click(function() {
        Joyin.uploadFile({pageId : 'EtrPlanAssetC003MTM'}, function(fileName) {
            Joyin.ajax(basePath + 'invest/entrust/etrplanassetc/EtrPlanAssetC003/checkImportData', {fileName: fileName}, function(checkResult) {
                setTimeout( function () {
                    if(checkResult.data.errorMsg){
                        Joyin.error(checkResult.data.errorMsg);
                        return;
                    }
                    Joyin.confirm(checkResult.data.confirmMsg,function() {
                        var result = {};
                        result.tempList = checkResult.data.correctData;
                        Joyin.dialog(JoyinUtil.getLabel("EtrPlanAssetC003.add"),
                            JoyinUtil.getRealPath("/invest/entrust/etrplanassetc/EtrPlanAssetC003/init"),
                            "98%", "95%", result,
                            function(){table.reload();});
                    });
                },1000);
            });
        },function() {
            table.reload();
        });
    });
    // add by zhangpuyu 2020/1/2 end 
    /**
     * 编辑按钮事件
     */
    $(function () {
        /**
         * 查询按钮
         */
        $('#EtrPlanAssetC001_btn_search').click(function() {
            table.load();
            $('#EtrPlanAssetC001_btn_close').show();
            $('#EtrPlanAssetC001_btn_revoke').show();
        });
        /**
         * 导出按钮
         */
        $('#EtrPlanAssetC001_btn_export').click(function() {
            exportExcel();
        });
        /**
         * 新建
         */
        $('#EtrPlanAssetC001_btn_add').click(function() {
            showDialog();
        });
        /**
         * 关闭委托
         */
        $('#EtrPlanAssetC001_btn_close').click(function() {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
             }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
             }
//            else if(selArr && selArr.length>1){
//                 Joyin.error(JoyinUtil.getMessage("ETR_COM_X"));
//                 return;
//             } 
            else {
                var object = {selectedItem: selArr, operateType:"close"};
                Joyin.ajaxJson(basePath + 'invest/entrust/etrplanassetc/EtrPlanAssetC001/check', object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage("ETR_C_0002"), function(){
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrplanassetc/EtrPlanAssetC001/close', object, function(result) {
                            table.reload();
                        });
                    });
                });
             }
        });
        /**
         * 撤销
         */
        $('#EtrPlanAssetC001_btn_revoke').click(function() {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
             }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
             }
//            else if(selArr && selArr.length>1){
//                 Joyin.error(JoyinUtil.getMessage("ETR_COM_X"));
//                 return;
//             } 
            else {
                var object = {selectedItem: selArr, operateType:"recall"};
                Joyin.ajaxJson(basePath + 'invest/entrust/etrplanassetc/EtrPlanAssetC001/check', object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage("BUSS_COM_C_0032","撤销"), function(){
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrplanassetc/EtrPlanAssetC001/recall', object, function(result) {
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
    });
}());