$(function () {
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        return {
            schemeId: $('#schemeId').val()
        }
    };
	var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
		searchOnInit: true,
		cols: [
		    {field:'secTypeDesc', width:100, title: 'ValuationSolutionManage002.secType', sortField:'secType'}
            ,{field:'tradeMarketDesc', width:120, title: 'ValuationSolutionManage002.tradeMarket', sortField:'tradeMarket'}
		    ,{field:'secDetailTypeDesc', width:120, title: 'ValuationSolutionManage002.secDetailType', sortField:'secDetailType'}
            ,{field:'invAimDesc', width:420, title: 'ValuationSolutionManage002.invAim', sortField:'invAim'}
            ,{field:'valuationMethodDesc', width:140, title: 'ValuationSolutionManage002.valuationMethod', sortField:'valuationMethod'}
            ,{field:'valuationPriceDesc', width:100, title: 'ValuationSolutionManage002.valuationPrice', sortField:'valuationPrice'}
            ,{field:'valuationDateDesc', width:120, title: 'ValuationSolutionManage002.valuationDate', sortField:'valuationDate'}
            ,{field:'quotaDigit', width:120, title: 'ValuationSolutionManage002.quotaDigit'}
            ,{field:'vdate', width:120, title: 'ValuationSolutionManage002.vdate', render: function (data, type, row, meta) {
                if(data && !isNaN(data)) {
                    var date = new Date(parseInt(data));
                    return date.format('yyyy-MM-dd');
                }
                return data;
            }}
            ,{field:'mdate', width:120, title: 'ValuationSolutionManage002.mdate', render: function (data, type, row, meta) {
                if(data && !isNaN(data)) {
                    var date = new Date(parseInt(data));
                    return date.format('yyyy-MM-dd');
                }
                return data;
            }}
//            ,{fixed:'right', width:90, title:'操作', align:'center', type:'action', buttons: []}
		]
	  });

    /**
     * 画面加载完成
     */
    $(function() {
    });
});