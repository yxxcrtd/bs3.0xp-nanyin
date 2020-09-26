$(function() {
  
/**
 * 导出表格
 */
//        var exportExcel = function() {
//            if (table != null && table != '') {
//                var selArr = table.getAllCheckedRows();
//                var datas = JSON.stringify(selArr);
//            }
//            if (!selArr || selArr.length == 0) {
//                var paramObj = getSearchParams();
//                Joyin.exportExcel('export', paramObj);
//            } else {
//                Joyin.exportExcel('export', {
//                    datas : datas
//                });
//            }
//        };
    var exportExcel = function() {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
            var selAll = table.getAllRows();
        }
        
        if (!selAll || selAll.length == 0) {
            Joyin.error(JoyinUtil.getMessage("表格无数据！"));
            return;
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
            qrydate: $('#qrydate').val(),     
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
        loadonce:true,
        checkbox: true,
        cellMinWidth : 100,
        cols : [ 
        {field : 'orderId', title : '序号', sortable : false}, 
        {field : 'prodId', title : '产品代码', sort : true,sortable : false}, 
        {field : 'prodName',title : '产品名称',sort : true,sortable : false}, 
        {field : 'mDate', title : '产品到期日',width:120, sort : true,sortable : false}, 
        {field : 'finprodTypeFmt', title : '资产类型',sortField:'finprodType', sort : true,sortable : false}, 
        {field : 'finprodId', title : '资产代码', sort : true,sortable : false}, 
        {field : 'finprodName',title : '资产名称',sort : true,sortable : false}, 
        {field : 'subjectNo',title : '异常科目代码', sort : true ,sortable : false}, 
        {field : 'subjectName', title : '异常科目名称',width:240,sort : true ,sortable : false},  
        {field : 'balFlagFmt',title : '余额方向', sortField:'balFlag',width:120,sort : true,sortable : false}, 
        {field : 'oAmt', title : '科目余额',type:"money",width:120,sort : true,sortable : false }
        ]
    });
    
    /**
     * 筛选
     */
    $('#ValBalanceUnusualQuery001_btn_search').click(function() {
        var bookDate = $('#qrydate').val();
        if(bookDate==''||typeof(bookDate) == "undefined"){
            Joyin.error(JoyinUtil.getMessage("日期不能为空"));  
            return;
        }else{
            table.load();
        }
    });
    
    /**
     * 导出
     */
    $('#ValBalanceUnusualQuery001_btn_export').click(function() {
        exportExcel();
    });

});