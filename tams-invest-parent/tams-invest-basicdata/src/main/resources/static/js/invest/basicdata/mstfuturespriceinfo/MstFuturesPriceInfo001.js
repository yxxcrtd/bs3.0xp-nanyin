(function(){
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            futuresId: $("#futuresId").val(),
            windCode:$("#windCode").val(),
            tradeDateStart:$("#tradeDate").startDate(),
            tradeDateEnd:$("#tradeDate").endDate()

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

        cols:[
            {field:'futuresId', title: 'MstFuturesPriceInfo002.futuresId',width : 150}//合约代码
            ,{field:'futuresIdWithMarket', visible:false}//合约代码
            ,{field:'windCode',title: 'MstFuturesPriceInfo002.windCode',width:150}//Wind代码
            ,{field:'tradeDate', title: 'MstFuturesPriceInfo002.tradeDate',width:150,type:'date'}//行情日期
            ,{field:'preSettlePrice', title: 'MstFuturesPriceInfo002.preSettlePrice',width:150,type:'number'}//前结算价
            ,{field:'openPrice', title: 'MstFuturesPriceInfo002.openPrice', width : 150,type: 'number'}//开盘价
            ,{field:'closePrice', title: 'MstFuturesPriceInfo002.closePrice', width : 150,type: 'number'}//收盘价
            ,{field:'highPrice', title: 'MstFuturesPriceInfo002.highPrice', width : 150,type: 'number'}//最高价
            ,{field:'lowPrice', title: 'MstFuturesPriceInfo002.lowPrice', width : 150,type: 'number'}//最低价
            ,{field:'settlePrice', title: 'MstFuturesPriceInfo002.settlePrice', width : 150,type: 'number'}//结算价
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit','delt']}
        ]
    });

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.tradeDate = new Date(data.tradeDate).format('yyyy-MM-dd');
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstfuturespriceinfo/MstFuturesPriceInfo002/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("MstFuturesPriceInfo002.edit"),
                basePath + "invest/basicdata/mstfuturespriceinfo/MstFuturesPriceInfo002/init",
                "95%", "90%", {pageModel:PAGE_MODEL_EDIT, futuresId:data.futuresIdWithMarket,tradeDate:data.tradeDate},function() {
                    table.reload();
                });
        });
    });


    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstfuturespriceinfo/MstFuturesPriceInfo001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/basicdata/mstfuturespriceinfo/MstFuturesPriceInfo001/delete', object, "MstFuturesPriceInfo001_btn_delete", "MstFuturesPriceInfo002",function() {
                    table.reload();
                });
            });
        });

    });




    /**
     * 绑定导出事件
     */
    var exportExcel = function () {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };



    //点击按钮事件
    $(function() {
        //查询
        $('#MstFuturesPriceInfo001_btn_search').click(function() {
            table.load();
        });

        $('#MstFuturesPriceInfo001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("MstFuturesPriceInfo002"),
                basePath + "invest/basicdata/mstfuturespriceinfo/MstFuturesPriceInfo002/init",
                "95%", "90%", {pageModel:PAGE_MODEL_NEW});
        });

        $('#MstFuturesPriceInfo001_btn_export').click(function () {
            exportExcel();
        });
        //重置
        $('#MstFuturesPriceInfo001_btn_reset').click(function(){
            $('#windCode').val('').trigger('change');
            $('#futuresId').val('').trigger('change');
            $("#tradeDate").val('');
            table.clear();
        });
    });


}());
