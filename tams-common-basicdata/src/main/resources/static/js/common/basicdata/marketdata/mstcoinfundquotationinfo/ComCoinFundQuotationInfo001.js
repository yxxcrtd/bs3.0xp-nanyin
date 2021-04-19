$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            finprodId: $('#finprodId').val(),//基金代码
            fundManager: $('#fundManager').val(),//基金管理人
            valDate : $('#valDate').val(),//估值日
        };
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
     * 定义表格双击事件
     */
        dblclick:function(table, data) {
            //在新建界面进行显示单行的详细信息
            data.finprodId = $("#finprodId_hidden").val();
            Joyin.dialog(JoyinUtil.getLabel("ComCoinFundQuotationInfo001.title"),
                    basePath + "common/basicdata/marketdata/mstcoinfundquotationinfo/ComCoinFundQuotationInfo002/init",
                    "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,
                                    valDate:data.valDate,//估值日
                                    finprodMarketId:data.finprodMarketId//市场代码
                                    });
        },
        /**
         * 定义表头
         */
        cols:[
            {field:'finprodMarketId',width:'15%',  title: 'ComCoinFundQuotationInfo001.finprodId'}//基金代码
            ,{field:'finprodAbbr',width:'15%',  title: 'ComCoinFundQuotationInfo001.productName'}//基金名称
            ,{field:'finprodChiType',width:'15%',title:'ComCoinFundQuotationInfo002.finprodType', sortField:'finprodType2'}//基金类型
            ,{field:'valDate',width:'15%',title:'ComCoinFundQuotationInfo002.valuationTime',type:'date'}//估值日期
            ,{field:'publishDate',width:'15%',title:'ComCoinFundQuotationInfo002.publishDate',type:'date'}//估值日期
            ,{field:'valuation',width:'15%', title:'ComCoinFundQuotationInfo002.valuation'}//万分收益
            ,{field:'fundManager',width:'15%', title: 'ComCoinFundQuotationInfo001.fundManager'}//基金管理人
            ,{field:'day7YearYield',width:'15%', title:'ComCoinFundQuotationInfo002.day7YearYield'}//七日年化收益率(%)
            ,{field:'totalValue',width:'10%', title:'ComCoinFundQuotationInfo002.totalValue'}//累计净值
            ,{field:'restNetValue',width:'10%', title:'ComCoinFundQuotationInfo002.restNetValue'}//复权单位净值
            ,{field:'importDay',width:'15%', title: 'ComCoinFundQuotationInfo002.importDay',type:'date'}//导入日
            ,{field:'cShares',width:'10%', title:'ComCoinFundQuotationInfo002.cShares'}//基金总份额
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}//编辑和删除事件
        ]
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/mstcoinfundquotationinfo/ComCoinFundQuotationInfo001/check', data, function(result) {//请求路径，请求参数回调函数
            Joyin.dialog(JoyinUtil.getLabel("ComCoinFundQuotationInfo001.title"),
                    basePath + "common/basicdata/marketdata/mstcoinfundquotationinfo/ComCoinFundQuotationInfo002/init",//打开新界面
                    "80%", "80%", {pageModel:PAGE_MODEL_EDIT,//请求的page_model
                                    valDate:data.valDate,//估值日
                                    finprodMarketId:data.finprodMarketId//市场代码
                                    },function(){
                                        table.reload();//回调函数，表格刷新
                                    });   
        });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};//请求参数
        Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/mstcoinfundquotationinfo/ComCoinFundQuotationInfo001/check', object, function(result) {//请求路径，请求参数回调函数
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){//删除事件的请求
                WorkFlow.doDeleteList(basePath + 'common/basicdata/marketdata/mstcoinfundquotationinfo/ComCoinFundQuotationInfo001/delete', object,"ComCoinFundQuotationInfo001_btn_delete", "ComCoinFundQuotationInfo002", function(){
                    table.reload();//删除事件请求及回调函数
                });
            });
        });
    });
    
    /**
     * 导出选中的表格数据
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();//请求参数json格式
        Joyin.exportExcel('export', paramObj);//导出（共通）
    };

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定查询事件
        $('#ComCoinFundQuotationInfo001_btn_search').click(function() {
            table.load();
            $('#ComCoinFundQuotationInfo001_btn_delete').show();
        });
       //导出事件
        $('#ComCoinFundQuotationInfo001_btn_export').click(function() {
            exportExcel();
        });
        //新建事件
        $('#ComCoinFundQuotationInfo001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("ComCoinFundQuotationInfo002.title"),
                        basePath + "common/basicdata/marketdata/mstcoinfundquotationinfo/ComCoinFundQuotationInfo002/init",
                        "60%", "75%", {pageModel:PAGE_MODEL_NEW});
        });
        //删除事件
        $('#ComCoinFundQuotationInfo001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();//获取选择行
            }
            if (!selArr || selArr.length == 0) {//判断是否选择，未选返回错误
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
                Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/mstcoinfundquotationinfo/ComCoinFundQuotationInfo001/check', object, function(result) {//数据验证
                    Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){//删除事件
                        WorkFlow.doDeleteList(basePath + 'common/basicdata/marketdata/mstcoinfundquotationinfo/ComCoinFundQuotationInfo001/delete', object, "ComCoinFundQuotationInfo001_btn_delete", "ComCoinFundQuotationInfo002");//删除工作流
                    });
                });
        });
      //hsgexinghua   add by zhangxing1 20200220 start
        // 导入按钮点击事件
        $('#ComCoinFundQuotationInfo001_btn_import').click(function() {
            Joyin.confirm("请确认是否要导入数据？", function() {
                Joyin.uploadFile(function(fileName) {
                    Joyin.ajax(basePath + 'common/basicdata/marketdata/mstcoinfundquotationinfo/ComCoinFundQuotationInfo001/checkConfirmMsgForImportData', {fileName: fileName}, function( result) {
                        Joyin.ajax(basePath + 'common/basicdata/marketdata/mstcoinfundquotationinfo/ComCoinFundQuotationInfo001/importFile', {fileName: fileName});
                    });
                },function() {
                    table.reload();
                });
            });
        });
        //hsgexinghua  add by zhangxing1 20200220 end
    });
}());