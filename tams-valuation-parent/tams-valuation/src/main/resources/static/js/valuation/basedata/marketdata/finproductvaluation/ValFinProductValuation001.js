
$(function() {
	
	
	
	var table = $("#dataTable").joyintable({
        ajax : {
            url : 'tableLoading',
            type : 'POST',
            data : function() {
                return getSearchParams();
            }
        },
        //UAT-360  3：将双击点开查看的数据进行排序，数据消失。 需求无双击查看需求.直接将前台数据双击点开无意义 leipeng 20180906
//        dblclick:function(table , data) {
//            Joyin.dialog(JoyinUtil.getLabel("查看详情"), basePath
//                    + "valuation/basedata/marketdata/finproductvaluation/ValFinProductValuation002/init","90%",
//                    "90%", {pageModel:PAGE_MODEL_REFERENCE,datas:JSON.stringify(data)},
//                    function(){
//                        table.reload();
//                        pageModel : PAGE_MODEL_NEW
//                    },
//                    null
//            ); 
//        },
        cellMinWidth : 100,
        cols : [ 
                {field : 'finprodId', title : '基金代码', sort : true}, 
                {field : 'finprodAbbr', title : '基金名称', sort : true}, 
                {field : 'type1Fmt', sortField:'type1',title : '基金类型', sort : true}, 
                {field : 'valuation', align : "right", title : '净值/万分收益', sort : true},  
                {field : 'sevenDayRate',  title : '七日年化收益率(%)', sort : false}, 
                {field : 'valDate',sortField:'valDate',title : '估值日期', sort : true}, 
                {field : 'publishDate',sortField:'publishDate',title : '公布日期', sort : true}
               
               ]
    });


    var getSearchParams = function() {
        return {
            finprodId:$("#finprodId").val(),
            fundtype:$("#fundtype").val(),
            fundAbbr:$("#fundAbbr").val(),
            valuationStartDate : $('#valuationDate').startDate(),
            valuationEndDate : $('#valuationDate').endDate(),
        }
    }
    /**
     * 筛选
     */
    
    $('#ValFinProductValuation001_btn_search').click(function() {
    	table.load();
    });
    
//    /**
//     * 新建
//     */
//    $('#ValIntRateQuote001_btn_add').click(function() {
//        Joyin.dialog(JoyinUtil.getLabel("查看详情"), basePath
//                + "valuation/basedata/marketdata/finproductvaluation/ValFinProductValuation002/init","90%",
//                "90%", null,
//                function(){
//                    table.reload();
//                    pageModel : PAGE_MODEL_NEW
//                },
//                null
//        ); 
//    });
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
         * 导出
         */
        $('#ValFinProductValuation001_btn_export').click(function() {
            exportExcel();
        });
});