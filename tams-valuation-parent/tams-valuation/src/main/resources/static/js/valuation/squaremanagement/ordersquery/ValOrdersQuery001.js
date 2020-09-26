$(function () {
    
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            tradeId: $('#tradeId').val(),
            busiType: $('#busiType').val(),     
            ps: $('#ps').val(),     
            settleDateStart: $('#settleDateStart').val(),     
            settleDateEnd: $('#settleDateEnd').val()   
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
		checkbox: false,
		cellMinWidth : 100,
		cols: [
		   {field:'orderId', title: '指令编号',width:100, sort: true},
		   {field:'busiType', title: '业务类型',width:100, sort: true},
		   {field:'orderType', title: '指令类型',width:100, sort: true},
		   {field:'orderNum', title: '指令序号',width:100},
		   {field:'settleDate', title: '结算日期',width:100, sort: true},
		   {field:'tradeId', title: '交易编号',width:100},
		   {field:'myAcctNo', title: '本方资金账户',width:100},
		   {field:'ps', title: '收付方向',width:100, sort: true},
		   {field:'counterAcctNo', title: '对方资金账户',width:120},
		   {field:'settleAmt', title: '结算金额',width:100, sort: true},
		   {field:'settleStatus', title: '结算状态',width:100, sort: true},
		   {field:'booksetId', title: '账套代码',width:100},
		   {field:'booksetDate', title: '录入日',width:100, sort: true},
		   {field:'bookDate', title: '入账日',width:100, sort: true}
		],
	});
	
//	var exportExcel = function() {
//        var paramObj = getSearchParams();
//        Joyin.exportExcel('export', paramObj);
//    };
	
	/**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ValOrdersQuery001_btn_search').click(function() {
            table.load();
        });
//        $('#ValFinprodTradeQuery001_btn_export').click(function() {
//            exportExcel();
//        });
    });
	
});