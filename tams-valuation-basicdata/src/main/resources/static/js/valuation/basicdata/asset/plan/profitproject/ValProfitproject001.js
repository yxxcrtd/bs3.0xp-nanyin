/**
 * ValProfitproject001 非标债权及利率型资产
 */
$(function() {
    /**
     * 获取查询参数
     *
     */
   var getSearchParams = function() {
        var finprodMarketId = $('#finprodMarketId').val();
        var assetType = $('#assetType').val();
        var startVDate = $('#vdate').startDate();
        var endVDate = $('#vdate').endDate();
        var startMDate = $('#mdate').startDate();
        var endMDate = $('#mdate').endDate();
        return {
            finprodMarketId : finprodMarketId,
            assetType : assetType,
            startVDate : startVDate,
            endVDate : endVDate,
            startMDate : startMDate,
            endMDate : endMDate
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
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "valuation/basicdata/asset/plan/profitproject/ValProfitproject002/init",
                    "99%", "99%", {pageModel:PAGE_MODEL_REFERENCE,
                                   finprodId:data.finprodId});
        },
        cols:[
             {field:'finprodId',visible :false}
            ,{field:'issuer',visible :false}
            ,{field:'calendarId',visible :false}
            ,{field:'capiIncomeFeature',visible :false}
            ,{field:'trusteeId',visible :false}
            ,{field:'issueAmt',visible :false}
            ,{field:'investmentAttributes',visible :false}
            ,{field:'capiIncomeFeature',visible :false}
            ,{field:'finprodMarketId',width:150,title: 'ValProfitproject001.finProdMarketId'}
            ,{field:'finprodType2',visible :false}
            ,{field:'finprodName',width:150,title: 'ValProfitproject001.finProdName'}
            ,{field:'assetTypeStr',sortField:"assetType",width:150,title: 'ValProfitproject001.assetType'}
            ,{field:'vdate',width:150, title: 'ValProfitproject001.vDate',type:'date'}
            ,{field:'mdate',width:150, title: 'ValProfitproject001.mDate',type:'date'}
            ,{field:'profitTypeStr',sortField:"profitType",width:150,title: 'ValProfitproject001.profitType'}
            ,{field:'basisStr',sortField:"basis",width:150,title: 'ValProfitproject001.basis'}
            ,{field:'issuer',width:160, sortField:"issuer",title: '发行人'}
            ,{field:'financier',width:160, sortField:"financier",title: '融资人'}
            ,{field:'dataSourceStr', width:150 ,sortField:"dataSource",title: 'ValProfitproject001.dataSource'}
            ,{field:'updateUser', width:150 ,sortField:"updateUser",title: 'ValProfitproject001.updateUser'}
            ,{field:'updateTime', width:150 ,sortField:"updateTime",title: 'ValProfitproject001.updateTime',type:'date'}
            ,{field:'operation',width:150,title: 'SysCommon.operation', type:'action', buttons: ['edit',{clas: 'iconfont icon-copy',title: '复制'}, 'delt']}
        ]
    });    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        // Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/plan/profitproject/ValProfitproject001/check', data, function(result) {
        Joyin.ajax(basePath + 'valuation/basicdata/asset/ValAssetCommon/editCheck', data, function(editResult){
            Joyin.dialog(JoyinUtil.getLabel("ValProfitproject002.edit"),
                    basePath + "valuation/basicdata/asset/plan/profitproject/ValProfitproject002/init",
                    "99%", "99%", {pageModel:PAGE_MODEL_EDIT,//请求的page_model
                    finprodId:data.finprodId,  //金融产品代码
                    finprodMarketId:data.finprodMarketId//市场代码
                    },function() {
                                        table.reload();
                                    });
        });
        // });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/plan/profitproject/ValProfitproject001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'valuation/basicdata/asset/plan/profitproject/ValProfitproject001/delete', object,
                        "ValProfitproject001_btn_delete", "ValProfitproject002",function() {
                    table.reload();
                });
            });
        });
    });

    /**
     * 绑定复制事件
     */
    table.bind('click', '.icon-copy', function(data, index) {
        data.pageModel = PAGE_MODEL_NEW;
        data.copyFlag = YES_OR_NO_Y
        // Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/plan/profitproject/ValProfitproject001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("ValProfitproject002.copy"),
                basePath + "valuation/basicdata/asset/plan/profitproject/ValProfitproject002/init",
                "95%", "95%", {pageModel:PAGE_MODEL_NEW,//请求的page_model
                    finprodId:data.finprodId,  //金融产品代码
                    finprodMarketId:data.finprodMarketId,//市场代码
                    copyFlag:YES_OR_NO_Y
                },function() {
                    table.reload();
                });
        // });
    });
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        // var paramObj = getSearchParams();
        // Joyin.exportExcel('export', paramObj);

        if (table) {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length === 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ValProfitproject001_btn_search').click(function() {
            table.load();
        });
        $('#ValProfitproject001_btn_export').click(function() {
            exportExcel();
        });
        $('#ValProfitproject001_btn_create').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("ValProfitproject002"),
                        basePath + "valuation/basicdata/asset/plan/profitproject/ValProfitproject002/init",
                        "99%", "99%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#ValProfitproject001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) { 
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/plan/profitproject/ValProfitproject001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                        WorkFlow.doDeleteList(basePath + 'valuation/basicdata/asset/plan/profitproject/ValProfitproject001/delete', object,
                            "ValProfitproject001_btn_delete","ValProfitproject002");
                });
            });
        });
    });
}());