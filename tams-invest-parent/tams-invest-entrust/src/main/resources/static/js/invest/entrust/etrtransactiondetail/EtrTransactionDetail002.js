/**
 * 所有投资查询
 */
(function(){
    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return {entrustId:entrustId};
            }
        },
        cols:[ 
             {field:'entrustId',visible:false}
            ,{field:'portfolioId', title: 'EtrTransactionDetail001.portfolioId', width:'200',sortField:"portfolioId"}
            ,{field:'portfolioName', title: 'EtrTransactionDetail001.portfolioName', width:'200',sortField:"portfolioName"}
            ,{field:'tradeDate', title: 'EtrTransactionDetail002.tradeDate', width:'10%', type:'date',sortField:"tradeDate"}
            ,{field:'assetType',visible:false}
            ,{field:'finprodId',visible:false}
            ,{field:'finprodMarketId', title:assetType==F07?'EtrTransactionDetail002.termVriety':'EtrTransactionDetail001.finprodId', width:'200',sortField:"finprodMarketId"}
            ,{field:'finprodName', title:assetType!=F11?'EtrTransactionDetail002.finprodAbbr':'EtrTransactionDetail001.finprodName', width:'200',sortField:"finprodName",visible:assetType!=F07?true:false}
            ,{field:'ps', visible:false}
            ,{field:'psStr', title: 'EtrTransactionDetail001.ps', width:'10%'}
            ,{field:'tradeAmt',type:'number', title: assetType==F01?'EtrTransactionDetail001.entrustTotal':'EtrTransactionDetail002.tradeAmt', width:'200', type:'money',sortField:"tradeAmt"}
            ,{field:'shareAmt',type:'number', title: assetType==F02?'EtrTransactionDetail002.shareAmt':'EtrTransactionDetail002.tradeNum', width:'200', type:'money',sortField:"shareAmt",visible:(assetType!=F01&&assetType!=F07)?true:false}
            ,{field:'unitCprice',type:'number', title:assetType==F01?'EtrTransactionDetail002.unitCprice':(assetType==F07?'EtrTransactionDetail002.backRate':'EtrTransactionDetail002.price'), width:'200', type:'money',sortField:"unitCprice"}
            ,{field:'unitFprice',type:'number', title: 'EtrTransactionDetail002.unitFprice', width:'200', type:'money',sortField:"unitFprice",visible:assetType==F01?true:false}
            ,{field:'fpriceAmt',type:'number', title: 'EtrTransactionDetail002.fpriceAmt', width:'200', type:'money',sortField:"fpriceAmt",visible:assetType==F01?true:false}
        ],
    });
    
    /**
     * 初始化
     */
	    $(function() {
		var init = function() {
			if (entrustId != '') {
				table.table == null ? table.load() : table.reload();
			}
		};
		init();
	});
}());