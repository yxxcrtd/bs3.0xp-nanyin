/**
 * 投资端费用明细
 * @auth malinlin
 */
(function(){

	/**
     * 定义数据表格
     */
    var bussFinProductFeePlanTable = $('#feePositionDetailTable').joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('/invest/portfolio/feepay/InvestFeePay003/queryFeePositionDetail'),
            type: 'POST',
            data: function() {
                return {secAcctId : secAcctId, ptlId : ptlId, calcStartDate : calcStartDate, calcEndDate : calcEndDate, feeType: feeType, confirmFlag: confirmFlag, tradeId: tradeId};
            }
        },
        searchOnInit: true,
        height: 200,
        page: false,
        checkbox: false,
        loadonce: true,
        rowno: true,
        cols:[
             {field:'portFolioName', title: 'InvestFeePay003.table.portFolioName',sortable:false, width: 150}
            ,{field :'tradeTypeStr',width:150,title : 'InvestFeePay003.table.tradeType',sortable:false, width: 150}
            ,{field :'psStr',width:150,title : 'InvestFeePay003.table.tradePs',sortable:false, width: 150}
            ,{field :'assetCode',width:150,title : 'InvestFeePay003.table.trustCode',sortable:false, width: 150}
            ,{field :'assetName',width:150,title : 'InvestFeePay003.table.trustName',sortable:false, width: 150}
            ,{field :'tradeAmt',width:150,title : 'InvestFeePay003.table.tradeAmt',sortable:false, type:'number', width: 150, visible:false}
            ,{field :'tradeDate',width:150,title : 'InvestFeePay003.table.tradeDate',sortable:false, type:'date', width: 150}
            ,{field :'feeTypeStr',width:150,title : 'InvestFeePay003.table.feeType',sortable:false, width: 150}
            ,{field :'payAmt',width:150,title : 'InvestFeePay003.table.payAmt',sortable:false, type:'number', width: 150}
        ]
    });
    
}());