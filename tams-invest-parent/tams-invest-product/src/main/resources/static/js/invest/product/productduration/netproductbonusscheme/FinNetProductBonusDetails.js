/**
 * 净值型分红交易明细
 */
$(function() {

	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var bTradeId = $('#bTradeId').val();
        return {
        	bTradeId : bTradeId
        }
    };
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
    	checkbox:false,
    	rowno:true,
    	page:false,
    	ajax: {
            url: 'queryBonusDetail',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        cols:[{field:'finprodMarketId',title: 'FinNetProductBonusScheme001.finprodMarketId', width:140}
            ,{field:'finprodAbbr', title: 'FinNetProductBonusScheme001.finprodAbbr', width:150}            
            ,{field:'tradeDate', title: 'FinNetProductBonusScheme005.confirmDate',type:'date', width:100}   
            ,{field:'psstr', title: 'FinNetProductBonusDetails.dealtype', width:150} 
            ,{field:'tradeAmt', title: 'FinNetProductBonusScheme005.bonusShareAmt', width:120}
            ,{field:'shareAmt', title: 'FinNetProductBonusScheme005.bonusShareNum', width:120}
            ,{field:'ccystr', title: 'FinNetProductBonusScheme001.ccy', width:120}
            ,{field:'settleDate', title: 'FinNetProductBonusDetails.payDate',type:'date', width:100}
            ,{field:'remark', title: 'FinNetProductBonusScheme001.remark'}
        ]
    });
    
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        table.load();
    });
}());