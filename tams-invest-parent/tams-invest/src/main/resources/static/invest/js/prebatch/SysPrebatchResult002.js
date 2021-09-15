
(function(){
    /**
     * 获取查询参数
     */
    var searchform =function(){
        var param =  $.serializeToObject('#searchForm');
        param.seqNo = $("#seqNo").val();
        if(param.portfolioId){
            param.portfolioId = param.portfolioId.join(",");
        }
        if(param.finprodId){
            param.finprodId = param.finprodId.join(",");
        }
        return param;

    };


    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return searchform();
            }
        },
        cols:[
             {field:'taskIdStr', title: 'SysPrebatchResult001.taskId', width:'12%',sortField:'task_Id'}
            ,{field:'portfolioId', title: 'SysPrebatchResult002.portfolioId', width:'12%',sortField:'portfolio_id'}
            ,{field:'portfolioName', title: 'SysPrebatchResult002.portfolioName', width:'12%',sortField:'portfolio_name'}
            ,{field:'finprodId', title: 'SysPrebatchResult002.finprodId', width:'10%',sortField:'finprod_Id'}
            ,{field:'finprodName', title: 'SysPrebatchResult002.finprodName', width:'10%',sortField:'finprod_name'}
            ,{field:'tradeId', title: 'SysPrebatchResult002.tradeId', width:'12%',sortField:'trade_id'}
            ,{field:'tradeAmt', title: 'SysPrebatchResult002.tradeAmt', width:'8%',sortField:'trade_Amt'}
            ,{field:'nextUser', title: 'SysPrebatchResult002.nextUser', width:'12%',sortable:false}
            ,{field:'remark', title: 'SysPrebatchResult002.remark', width:'12%',sortable:false}
            ,{field:'seqNo', visible: false}
            ,{field:'batchSeqNo', visible: false}
            ,{field:'resultSeqNo', visible: false}
        ],
        checkbox: false,
        searchOnInit:true
    });

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.operateType = "edit";
        data.pageModel=PAGE_MODEL_EDIT;
        Joyin.dialog(JoyinUtil.getLabel("SysPrebatchResult002"),
                JoyinUtil.getRealPath("invest/prebatch/SysPrebatchResult002/init"),
                "98%", "95%", data,
           function(){table.reload();});
    });
    /**
     * 编辑按钮事件
     */
    $(function () {
        /**
         * 查询按钮
         */
        $('#SysPrebatchResult002_btn_search').click(function() {
            table.load();
        });


    });
}());