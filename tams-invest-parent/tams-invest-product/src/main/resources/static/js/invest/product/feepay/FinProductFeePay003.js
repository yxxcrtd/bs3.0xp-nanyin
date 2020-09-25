/**
 * 开放计划共通JS
 * @auth zhengyan
 */
(function(){

	/**
     * 定义数据表格
     */
    var bussFinProductFeePlanTable = $('#feePositionDetailTable').joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('/invest/product/feepay/FinProductFeePay003/queryFeePositionDetail'),
            type: 'POST',
            data: function() {
                return {finprodId : finprodId, sFinprodId : sFinprodId, branch : branch, calcStartDate : calcStartDate, calcEndDate : calcEndDate, feeId : feeId, feeType: feeType};
            }
        },
        searchOnInit: true,
        height: 200,
        page: false,
        checkbox: false,
        loadonce: true,
        rowno: true,
        cols:[
             {field:'finprodId', title: 'FinProductFeePay003.table.finprodId',sortable:false, width: 150}
            ,{field :'sFinprodId',width:150,title : 'FinProductFeePay002.taxTable.sonFinprodId',visible : sFinprodId != ''}
			,{field:'feeName', title: 'FinProductFeePay003.table.feeName',sortable:false, width: 150}
			,{field:'feeType', title: 'FinProductFeePay003.table.feeType',sortable:false, width: 150}
			,{field:'calcStartDate', title: 'FinProductFeePay003.table.calStartDate',sortable:false, type:'date', width: 150}
			,{field:'calcEndDate', title: 'FinProductFeePay003.table.calEndDate',sortable:false, type:'date', width: 150}
			,{field:'prinAmt', title: 'FinProductFeePay003.table.prinamt',sortable:false, type:'number', width: 150}
			,{field:'basis', title: 'FinProductFeePay003.table.basis',sortable:false, width: 150}
			,{field:'feeRate', title: 'FinProductFeePay003.table.feeRate',sortable:false, type:'number', width: 150, digit: '4'}
			,{field:'payAmt', title: 'FinProductFeePay003.table.payAmt',sortable:false, type:'number', width: 150}
        ]
    });
    
    
    $(function() {
    	
    });
}());