/**
 * FinStockInfo001 股票明的查询、删除和导出
 */
(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        var data = $.serializeToObject('#searchForm');
        return data;
    };
    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function () {
                return getSearchParams();
            }
        },
        /**
         * 双击查看
         */
        dblclick: function (table, data) {
            Joyin.dialog(JoyinUtil.getLabel("FinStockInfo002.title"),
                basePath + "invest/asset/stock/FinStockInfo002/init",
                "99%", "99%", {
                    pageModel: PAGE_MODEL_REFERENCE,
                     finprodId: data.finprodId,
                    finprodMarketId:data.finprodMarketId//市场代码
                });
        },
        /**
         * 定义表头
         */
cols: [
            {field:'finprodId',  title: 'FinStockInfo001.finprodId',visible:false},//金融产品代码
            {field:'finprodMarketId',  title: 'FinStockInfo001.finprodMarketId',sortField:"finprodMarketId"}  //资产代码
            ,{field:'finprodAbbr',  title: 'FinStockInfo001.finprodAbbr',sortField:"finprodAbbr"} //资产名称
        ,{field:'tradeMarketAbbr',  title: 'FinStockInfo001.fintradeMarket',sortField:"tradeMarket"} //交易场所
        ,{field:'boardName',  title: 'FinStockInfo001.board',sortField:"board"}//上市板
            ,{field:'customerName',  title: 'FinStockInfo001.customerName',sortField:"customerName"}//公司名称
        ,{field:'vdate',  title: 'FinStockInfo001.IpoDate',type:'date'}//上市日期
            ,{field:'sShares',  title: 'FinStockInfo001.allStock',sortField:"sShares"} //总股本
            ,{field:'cShares',  title: 'FinStockInfo001.currentCapital',sortField:"cShares"} //流通股本
        ,{field:'shOrZhName',  title: 'FinStockInfo001.shOrZh',sortField:"shOrZh"}//是否沪港通或深港通
        ,{field:'isMarginFinprodName',title:'FinStockInfo001.isMarginFinprod',sortField:"isMarginFinprod"}//是否融资融券标的
       // ,{field:'ccy',  title: 'FinStockInfo001.ccy', sortField:'ccy'}//币种
            ,{field:'ccyAbbr', sortField:"ccy",width:150, title: 'FinStockInfo001.ccy'}//币种
        ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}        //编辑和删除事件
         ]

    });
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
            Joyin.ajaxJson(basePath + 'invest/asset/stock/FinStockInfo001/editCheck', data, function(editResult){
                Joyin.dialog(JoyinUtil.getLabel("FinStockInfo002.editTitle"),
                    basePath + "invest/asset/stock/FinStockInfo002/init",
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
        Joyin.ajaxJson(basePath + 'invest/asset/stock/FinStockInfo001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/asset/stock/FinStockInfo001/delete', object,
                    "FinStockInfo001_btn_delete", "FinStockInfo002",function() {
                        table.reload();
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
     * 弹出新建画面
     * @returns
     */
    function showDialog() {
        Joyin.dialog(JoyinUtil.getLabel('FinStockInfo002.newTitle'), '../FinStockInfo002/init', '98%', '98%')
    }
    /**
     * 画面加载完成
     */
    $(function () {
        //设置人民币默认值
        $('#ccy').textSelect('setValue', 'CNY');
        // //设置交易场所下拉框枚举
        // $('#tradeMarket').textSelect('setValue',null);
        // 绑定按钮事件
        $('#FinDepositTarget001_btn_add').click(function () {
            table.load();
        });
        //导出
        $('#FinStockInfo001_btn_export').click(function () {
            exportExcel();
        });
        //初始化查询
        $('#FinStockInfo001_btn_search').click(function () {
            table.load();
            $('#FinStockInfo001_btn_delete').show();
        });
        //新建(点击打开新建画面)
        $('#FinStockInfo001_btn_create').click(function () {
            Joyin.dialog(JoyinUtil.getLabel("FinStockInfo002"),
                basePath + "invest/asset/stock/FinStockInfo002/init",
                "99%", "99%", {pageModel: PAGE_MODEL_NEW});
        });
        //删除
        $('#FinStockInfo001_btn_delete').click(function () {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType: "delete"};
            Joyin.ajaxJson(basePath + 'invest/asset/FinStockInfo001/check', object, function (result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")), function () {
                    WorkFlow.doDeleteList(basePath + 'invest/asset/FinStockInfo001/delete', object,
                        "FinStockInfo001_btn_delete", "FinStockInfo002");
                });
            });
        });
    });
}());
