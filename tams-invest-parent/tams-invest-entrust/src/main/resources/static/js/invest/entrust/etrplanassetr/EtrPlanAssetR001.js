/**
 * 计划类利率型查询
 */
(function(){
    /**
     * 获取查询参数
     */
    var searchform =function(){ 
        var object = $.serializeToObject('#searchForm');
        if(object.assetType1 != null && object.assetType1 != ''){
            object.assetType1 = object.assetType1.join(",");
        }
        /*增加查询参数、zhuyongkang 2019/7/29  start*/
        if(object.assetType10 != null && object.assetType10 != ''){
            object.assetType10 = object.assetType10.join(",");
        }if(object.assetType11 != null && object.assetType11 != ''){
            object.assetType11 = object.assetType11.join(",");
        }
        /*zhuyongkang 2019/7/29  end*/
        if(object.portfolioId != null && object.portfolioId != ''){
            object.portfolioId = object.portfolioId.join(",");
        }
        return object;
    };
    /** 
     * 新建
     */
    function showDialog() {
        Joyin.dialog(JoyinUtil.getLabel('EtrPlanAssetR002'), basePath +'invest/entrust/etrplanassetr/EtrPlanAssetR002/init', '98%', '95%');
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
            Joyin.dialog(JoyinUtil.getLabel("EtrPlanAssetR002.refrence"),
                    JoyinUtil.getRealPath("invest/entrust/etrplanassetr/EtrPlanAssetR002/init"),
                    "98%", "95%", data,
                    function(){table.reload();});
        },
        cols:[
            {field:'entrustStatusStr', title: 'EtrPlanAssetR001.entrustStatus', width:'10%',sortField:"entrustStatus"}
            ,{field:'entrustId', visible:false}
            /*添加投资经理 guyunfei 2019/08/31 start*/
            ,{field:'invManagerId', title: 'EtrPlanAssetR001.invManagerId', width:'10%',sortField:"invManagerId"}
            /*添加投资经理 guyunfei 2019/08/31 end*/
            ,{field:'portfolioId', title: 'EtrPlanAssetR001.portfolioId', width:'10%',
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link portfolioIdLink' style='cursor: pointer;'>"+row.portfolioId+"</ a>";
                },sortField:"portfolioId"
            }
            ,{field:'portfolioName', title: 'EtrPlanAssetR001.portfolioName', width:'10%',sortField:"portfolioName"}
            ,{field:'assetType1', visible:false}
            /*添加3.4级查询、zhuyongkang 2019/7/29  start*/
            ,{field:'assetType1Str', title: 'EtrPlanAssetR001.assetType1', width:'10%',sortField:"assetType1"}
            ,{field:'assetType10Str', title: 'EtrPlanAssetR001.assetType10', width:'10%',sortField:"assetType10"}
            ,{field:'assetType11Str', title: 'EtrPlanAssetR001.assetType11', width:'10%',sortField:"assetType11",visible:IS_SHOW_TYPE == '01'?true:false}
            /*zhuyongkang 2019/7/29  end*/
            ,{field:'finprodId', visible:false}
            ,{field:'finprodMarketId', title: 'EtrPlanAssetR001.finprodId', width:'10%',
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link finprodIdLink' style='cursor: pointer;'>"+data+"</ a>";
                },sortField:"finprodMarketId"
            }
            ,{field:'finprodName', title: 'EtrPlanAssetR001.finprodName', width:'10%',sortField:"finprodName"}
            ,{field:'psStr', title: 'EtrPlanAssetR001.ps', width:'10%',sortField:"ps"}
            ,{field:'ccyStr', title: 'EtrPlanAssetR001.ccy', width:'10%',sortField:"ccy"}
            ,{field:'vdate', title: 'EtrPlanAssetR001.vdate', width:'10%',type:'date',sortField:"vdate"}
            ,{field:'mdate', title: 'EtrPlanAssetR001.mdate', width:'10%',type:'date',sortField:"mdate"}
            ,{field:'entrustAmtStr',type:'number', title: 'EtrPlanAssetR001.entrustAmt1', width:'10%',sortField:"entrustAmt"}
            ,{field:'finishAmt',type:'number', title: 'EtrPlanAssetR001.finishAmt', width:'10%',sortField:"finishAmt"}
            ,{field:'transactioncom', title: 'EtrPlanAssetR001.transactioncom',render : function(data, type, row, meta){
                return "<div class='layui-progress layui-progress-big' style='margin-top:5px;'><div class='layui-progress-bar layui-bg-green' style='width:"+(Math.min(row.finishAmt/row.entrustAmtStr,1)*100).toFixed(2)+"%'><span class='layui-progress-text'>"+(Math.min(row.finishAmt/row.entrustAmtStr,1)*100).toFixed(2)+"%</span></div></div>";
            }, width:'10%',sortable:false}
            ,{field:'remark', title: 'EtrPlanAssetR001.remark', width:'10%',sortable:false}
            ,{field:'action', title: '操作' , type:'action',buttons: ['edit'] , width:'10%'}
            ],
    });
    /**
     * 导出
     */
    var exportExcel = function(){       
        Joyin.exportExcel(basePath+"invest/entrust/etrplanassetr/EtrPlanAssetR001/export", searchform());
    };

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.valueDate = new Date(data.valueDate).format();
        data.pageModel=PAGE_MODEL_EDIT;
        data.operateType='edit'
        Joyin.ajaxJson(basePath + 'invest/entrust/etrplanassetr/EtrPlanAssetR002/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("EtrPlanAssetR002.edit"),
                    basePath + "invest/entrust/etrplanassetr/EtrPlanAssetR002/init",
                    "98%", "95%",data,
                    function(){table.reload();});
        });
        
    });
    /**
     * 绑定删除事件
     */
//    table.bind('click', '.delt', function(data, index) {
//        var object = {selectedItem: [data], operateType:"delete"};
//        Joyin.ajaxJson(basePath + 'invest/entrust/etrplanassetr/EtrPlanAssetR001/check', object, function(result) {
//            Joyin.confirm(JoyinUtil.getMessage("BUSS_COM_C_0024",JoyinUtil.getLabel("SysCommon.delete")), function(){
//                Joyin.ajaxJson(basePath + 'invest/entrust/etrplanassetr/EtrPlanAssetR001/delete', object, function(result){
//                    table.reload();
//                    
//                });
//            });
//        }); //画面提示message【BUSS_COM_C_0024：请确认是否要删除?】                                                                    
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
                basePath + "invest/asset/plan/planProfit/FinPlanProfitAsset002/init",
                "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,
                                finprodId:row.finprodId,  //金融产品代码
                                //valDate:data.valDate,//估值日
                                //finprodMarketId:data.finprodMarketId//市场代码
                                });
    });
    // add by zhangpuyu 2020/1/2 start 
    // 导入按钮点击事件
 // 导入按钮点击事件
    $('#EtrPlanAssetR001_btn_import').click(function() {
        Joyin.uploadFile({pageId : 'EtrPlanAssetR003MTM'}, function(fileName) {
            Joyin.ajax(basePath + 'invest/entrust/etrplanassetr/EtrPlanAssetR003/checkImportData', {fileName: fileName}, function(checkResult) {
                setTimeout( function () {
                    if(checkResult.data.errorMsg){
                        Joyin.error(checkResult.data.errorMsg);
                        return;
                    }
                    Joyin.confirm(checkResult.data.confirmMsg,function() {
                        var result = {};
                        result.tempList = checkResult.data.correctData;
                        Joyin.dialog(JoyinUtil.getLabel("EtrPlanAssetR003.add"),
                            JoyinUtil.getRealPath("/invest/entrust/etrplanassetr/EtrPlanAssetR003/init"),
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
        $('#EtrPlanAssetR001_btn_search').click(function() {
            table.load();
            $('#EtrPlanAssetR001_btn_close').show();
            $('#EtrPlanAssetR001_btn_revoke').show();
        });
        /**
         * 导出按钮
         */
        $('#EtrPlanAssetR001_btn_export').click(function() {
            exportExcel();
        });
        /**
         * 新建
         */
        $('#EtrPlanAssetR001_btn_add').click(function() {
            showDialog();
        });
        $('#EtrPlanAssetR001_btn_close').click(function(){
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
//            else if(selArr && selArr.length>1){
//                Joyin.error(JoyinUtil.getMessage("ETR_COM_X"));
//                return;
//            }// 画面提示message【ETR_C_0002：请确认是否要关闭委托？】                                                             
            var object = {selectedItem: selArr, operateType:"close"};
            Joyin.ajaxJson(basePath + 'invest/entrust/etrplanassetr/EtrPlanAssetR001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("ETR_C_0002"), function(){
                    Joyin.ajaxJson(basePath + 'invest/entrust/etrplanassetr/EtrPlanAssetR001/close', object, function(){
                        
                        table.reload();
                    });
                });
            });
        })
        
        /** 
         * 撤销委托
         */
        $('#EtrPlanAssetR001_btn_revoke').click(function(){
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
//            else if(selArr && selArr.length>1){
//                Joyin.error(JoyinUtil.getMessage("ETR_COM_X"));
//                return;
//            }//BUSS_COM_C_0032
            var object = {selectedItem: selArr, operateType:"recall"};
            Joyin.ajaxJson(basePath + 'invest/entrust/etrplanassetr/EtrPlanAssetR001/check', object, function(result) {
                //确认是否撤回
                Joyin.confirm(JoyinUtil.getMessage("BUSS_COM_C_0032",JoyinUtil.getLabel("ETR_REVERT")), function(){
                    Joyin.ajaxJson(basePath + 'invest/entrust/etrplanassetr/EtrPlanAssetR001/recall', object, function(result){
                        
                        table.reload();
                    });
                });
            });
        })
        //是否展示资产四五级分类
        if(IS_SHOW_TYPE == '01'){
        	$('#assetType11Div').show();
        }else{
        	$('#assetType11Div').hide();
        }
    });
}());