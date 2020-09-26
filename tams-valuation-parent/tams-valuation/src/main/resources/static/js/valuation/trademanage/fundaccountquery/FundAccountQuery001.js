$(function() {
  
/**
 * 导出表格
 */
        var exportExcel = function() {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
                var datas = JSON.stringify(selArr);
            }
            if (!selArr || selArr.length == 0) {
                var paramObj = getSearchParams();
                Joyin.exportExcel('export', paramObj);
            } else {
                Joyin.exportExcel('export', {
                    datas : datas
                });
            }
        };

    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            acctType: $.trim($('#acctType').val()), //账户类型
            acct: $.trim($('#acct').val()) //账户
        }
    };
/**
 * 表格加载
 */
    var table = $("#dataTable").joyintable({
        ajax : {
            url : 'findPage',
            type : 'POST',
            data : function() {
                return getSearchParams();
            }
        },
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("查看资金账户信息"),
                    JoyinUtil.getRealPath("/valuation/trademanage/fundaccountquery/FundAccountQuery002/init"),
                    "70%", "70%", {pageModel:PAGE_MODEL_REFERENCE,acctNo:data.acctNo},
                    function(){});
        },
        checkbox: true,
        cellMinWidth : 100,
        cols : [ {field : 'acctNo', title : '账号',width:140, sort : true}, 
        {field : 'acctName', title : '账户名',width:260, sort : true}, 
        {field : 'acctTypeFt',title : '账户类型',width:100,sort : true,sortField:'acctType'}, 
        {field : 'bankDep',title : '开户行', width:200,sort : true}, 
        {field : 'inOutBankFt', title : '行内行外',width:100,sort : true,sortField:'inOutBank' },  
        {field : 'corrNo',title : '开户行联行行号', width:160,sort : true}, 
        {field : 'largePayno', title : '大额支付系统号',width:120,sort : true },
        {field : 'blnAreaFt', title : '境内境外', width:100,sort : true,sortField:'blnArea'}, 
        {field : 'swiftCode', title : 'SWIFT CODE', width:160,sort : true }
        ]
    });
    
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#FundAccountQuery001_btn_search').click(function() {
            table.load();
        });
        $('#FundAccountQuery001_btn_export').click(function() {
            exportExcel();
        });
    });

});