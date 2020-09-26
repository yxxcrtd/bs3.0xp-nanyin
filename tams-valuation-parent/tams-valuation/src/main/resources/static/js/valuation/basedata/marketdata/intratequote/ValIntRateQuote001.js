
$(function() {

    
    
    var table = $("#dataTable").joyintable({
        ajax : {
            url : 'findPage',
            type : 'POST',
            data : function() {
                return getSearchParams();
            }
        },
      dblclick:function(table , data) {
          console.log(data);
          Joyin.dialog(JoyinUtil.getLabel("查看详情"), basePath
                  + "valuation/basedata/marketdata/intratequote/ValIntRateQuote002/init","90%",
                  "90%", {pageModel:PAGE_MODEL_REFERENCE,datas:JSON.stringify(data)},
                  function(){
                      //table.reload();
                      pageModel : PAGE_MODEL_NEW
                  },
                  null
          ); 
},
//         searchOnInit : true,
        cellMinWidth : 100,
        // loadonce : true,
        cols : [ 
        	{field : 'intRateId',fixed : true,title : '利率代码'}, 
        	{field : 'rateName',title : '利率名称'}, 
//        	{field : 'rateType',title : '利率类型'}, 
        	{field : 'relaeseDate',title : '发布日'}, 
        	{field : 'effDate',title : '生效日'},
        	{field : 'intRate',align : "right", title : '利率值(%)'}, 
        	{field : 'quoCcy',title : '币种'} 
//        	{field : 'quoSrc',title : '数据来源'}
        	]
    });

    var getSearchParams = function() {
        
//        var intRateIdT =$("#intRateIdT").val();
//        if(intRateId==null||intRateId==''){
            return {
//              rateType:$("#rateType").val(),
                rateName:$("#rateName").val(),
                intRateId:$("#intRateId").val(),
                relaeseStartDate : $('#relaeseDate').startDate(),
                relaeseEndDate : $('#relaeseDate').endDate(),
                effStartDate : $('#effDate').startDate(),
                effEndDate : $('#effDate').endDate(),
//            } 
//        }else{
//            return {
//                intRateId:$("#intRateIdT").val(),
//            }
        }
    }
    /**
     * 筛选
     */
    $('#ValIntRateQuote001_btn_search').click(function() {
        table.load();
    });
    
    /**
     * 新建
     */
    $('#ValIntRateQuote001_btn_add').click(function() {
        Joyin.dialog(JoyinUtil.getLabel("查看详情"), basePath
                + "valuation/basedata/marketdata/intratequote/ValIntRateQuote002/init","90%",
                "90%", null,
                function(){
                    table.reload();
                    pageModel : PAGE_MODEL_NEW
                },
                null
        ); 
    });
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
        $('#ValIntRateQuote001_btn_export').click(function() {
            exportExcel();
        });
        
        $('#ValIntRateQuote001_btn_reset').click(function() {
            js_method(basePath);
        });
        
        
        
        $(document).ready(function(){  
            var intRateIdT =$("#intRateIdT").val();
            if(intRateIdT==null||intRateIdT==''){
                
            }else{
                
                table.load();
                $("#intRateIdT").val("");
            }
            
            
        });
        
        
});