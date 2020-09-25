/**
 * 产品存续期交易查询 明细查看画面
 */
(function() {
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        var pageParam =  {
                finprodId: finprodId
            ,prodId: prodId
            ,cashDate: cashDate
            ,profitType: profitType
            ,isLay: isLay
            ,yield: yield
        };

        return pageParam;
    };

    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        cols:[
            {field:'sonProdId', width:120, title: 'FinProductDurDeal002.table.sonProdId'}
            ,{field:'customLevel', width:120, title: 'FinProductDurDeal002.table.customLevel'}
            ,{field:'amtMax', width:120, title: 'FinProductDurDeal002.table.amtMax', type: 'money'}
            ,{field:'amtMin', width:120, title: 'FinProductDurDeal002.table.amtMin', type: 'money'}
            ,{field:'termMax', width:120, title: 'FinProductDurDeal002.table.termMax'}
            ,{field:'termMin', width:120, title: 'FinProductDurDeal002.table.termMin'}
            ,{field:'cashDate', width:120, title: 'FinProductDurDeal001.table.cashDate', type: 'date'}
            ,{field:'tdyBuyMoney', width:130, title: 'FinProductDurDeal002.table.tdyBuyMoney', type: 'money'}
            ,{field:'tdyBuyQty', width:130, title: 'FinProductDurDeal002.table.tdyBuyQty', type: 'money'}
            ,{field:'tdyRedMoney', width:130, title: 'FinProductDurDeal002.table.tdyRedMoney', type: 'money'}
            ,{field:'tdyRedQty', width:130, title: 'FinProductDurDeal002.table.tdyRedQty', type: 'money'}
            ,{field:'tdyCarriedInterest', width:130, title: 'FinProductDurDeal002.table.tdyCarriedInterest', type: 'money'}
            ,{field:'tdyCarriedMoney', width:140, title: 'FinProductDurDeal002.table.tdyCarriedMoney', type: 'money'}
            ,{field:'tdyForceIncreaseMoney', width:130, title: 'FinProductDurDeal002.table.tdyForceIncreaseMoney', type: 'money'}
            ,{field:'tdyForceIncreaseQty', width:130, title: 'FinProductDurDeal002.table.tdyForceIncreaseQty', type: 'money'}
            ,{field:'tdyForceReduceMoney', width:130, title: 'FinProductDurDeal002.table.tdyForceReduceMoney', type: 'money'}
            ,{field:'tdyForceReduceQty', width:130, title: 'FinProductDurDeal002.table.tdyForceReduceQty', type: 'money'}
            ,{field:'yield', width:200, title: 'FinProductDurDeal002.table.yield', type: 'money', digit: 4}
        ]
    });

    $(function () {
        // 初期化加载表格数据
        table.load();
    });
}());