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
            booksetId: $.trim($('#booksetId').val()),
            bookDateBegin: $('#bookDate').startDate(),
            bookDateEnd: $('#bookDate').endDate(),
            busiId: $.trim($('#busiId').val())
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
        checkbox: true,
        cellMinWidth : 100,
        cols : [ 
        {field : 'bookDate', title : '记账日期',width:100, sort : true}, 
        {field : 'booksetId',title : '账套代码',width:150,sort : true}, 
        {field : 'booksetName',title : '账套名称',width:180,sort : true}, 
        {field : 'tableIdFmt', sortField:'tableId', title : '业务类别',width:260,sort : true },
        {field : 'busiId', title : '资产/负债代码',width:180,sort : true },  
        {field : 'busiName', title : '资产/负债名称',width:180,sort : true },  
        {field : 'vouchNum', title : '凭证编号',width:180,sort : true },  
        {field : 'subjectNo', title : '科目号',width:180,sort : true },
        {field : 'subjectName', title : '科目名称', width:380,sort : true}, 
        {field : 'cdFlagFmt', sortField:'cdFlag',  title : '借贷', width:100,sort : true }, 
        // {field : 'ccyFmt', sortField:'ccy', title : '币种', width:160,sort : true},
        // {field : 'happenAmt', type:"money",sortField:'happenAmt', title : '金额', width:120,sort : true},
        {field : 'bCcy', sortField:'ccy', title : '币种', width:160,sort : true},
        {field : 'bHappenAmt', type:"money",sortField:'happenAmt', title : '金额', width:120,sort : true},
        {field : 'happenNumber', type:"number",sortField:'happenNumber', title : '数量',width:140, sort : true},
        {field : 'tradeId',title : '业务编号', width:160,sort : true},
        {field : 'pTradeId',title : '原始业务编号', width:160,sort : true},
        {field : 'bookTypeFmt', sortField:'bookType', title : '凭证类型',width:100,sort : true },
        {field : 'offsetFlagFmt', sortField:'offsetFlag', title : '冲销标识',width:100,sort : true },
        {field : 'vouchRemark',title : '摘要', width:300,sort : true}
        ]
    });
   
    /**
     * 筛选
     */
    $('#ValUnfairlendingQuery001_btn_search').click(function() {
        table.load();
    });
    
    /**
     * 重置
     */
    $('#ValUnfairlendingQuery001_btn_clear').click(function() {
        $('#booksetId option:selected').remove();
        $('#busiId').val('');     
        $('#bookDate').val('');
    });
    
    
    /**
     * 导出
     */
    $('#ValUnfairlendingQuery001_btn_export').click(function() {
        exportExcel();
    });


});