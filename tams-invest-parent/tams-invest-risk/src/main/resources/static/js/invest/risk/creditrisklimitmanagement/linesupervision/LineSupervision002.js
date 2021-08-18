(function() {

    /**
     * 定义数据表格
     */
    var table1 = {

        /**
         * 双击查看
         */
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("LineSupervision002"),
                    basePath + "invest/risk/creditrisklimitmanagement/linesupervision/LineSupervision002/init",
                    "95%", "90%", data);
        },
        
        cols:[
            {field:'finprodName', width:150 , title: 'LineSupervision002.finprodName'}
            ,{field:'finprodId', width:150 , title: 'LineSupervision002.finprodId'}
            ,{field:'tradeId', width:150 , title: 'LineSupervision002.tradeId'}
            ,{field:'creditName', width:150 , title: 'LineSupervision002.creditName'}
            ,{field:'creditTagName', width:150 , title: 'CeLine002.creditTagName'}
            ,{field:'usingCredit', width:150 , title: 'LineSupervision001.usingCredit'}
            ,{field:'adjustAmt', width:150 , title: 'LineSupervision002.adjustAmt'}
            ,{field:'tradeFace', width:150 , title: 'LineSupervision001.tradeFace'}
            ,{field:'deductRule', width:150 , title: 'CeLine002.deductRule'}
            ,{field:'tradeDate', width:150 , title: 'LineSupervision002.tradeDate'}
            ,{field:'adjustVdate', width:150 , title: 'LineSupervision002.adjustVdate'}
            ,{field:'timeLimitOut', width:150 , title: 'LineSupervision002.timeLimitOut'}
            ,{field:'tradeCcy', width:150 , title: 'LineSupervision002.tradeCcy'}
            ,{field:'discountRate', width:150 , title: 'LineSupervision002.discountRate'}
            ,{field:'astPlanId', width:150 , title: 'LineSupervision002.astPlanId'}
            ,{field:'astPlanName', width:150 , title: 'LineSupervision002.astPlanName'}
        ]
    };

    var table=$("#dataTable2").joyintable(table1);
    
    /**
     * 画面加载完成
     */
    $(function() {

        if (creditCustomerMstList) {
            table.reload({data: JSON.parse(creditCustomerMstList)});
        }


    });
}());