$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            tradeId: $.trim($('#tradeId').val()), //交易编号
        }
    };
    
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: '/valuation/trademanage/investtradequery/currentdeposit/CurrentDeposit002/findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
                
            }
        },
        checkbox: false,
        cellMinWidth : 100,
        searchOnInit:true,
        loadonce: true,
        cols: [
           {field:'num', title: '序号', sort: true},
           {field:'effDate', title: '计提日', sort: true},
           {field:'basis', title: '计提基数', sort: true},
           {field:'yield', title: '利率', sort: true},
           {field:'intAmt', title: '利息金额', sort: true}
        ]
    });
    
    /**
     * 定义数据表格
     */
    var table2=$("#dataTable2").joyintable({
        ajax: {
            url: '/valuation/trademanage/investtradequery/currentdeposit/CurrentDeposit002/findPage2',
            type: 'POST',
            data: function() {
                return getSearchParams();
                
            }
        },
        checkbox: false,
        cellMinWidth : 100,
        searchOnInit:true,
        loadonce: true,
        cols: [
           {field:'num', title: '序号', sort: true},
           {field:'prodId', title: '投资主体代码', sort: true},
           {field:'prodAbbr', title: '投资主体名称', sort: true},
           {field:'', title: '理论利息', sort: true},
           {field:'realIntAmt', title: '实际利息', sort: true}
        ]
    });

    
    /**
     * 画面加载完成
     */
    $(function() {

            table.reload();
            table2.reload();

    });
    
});



