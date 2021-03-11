$(function () {
    var table = $("#TransferCommon001_table").joyintable({
        page: false,
        data: entityList ? entityList : [],
        height: 200,
        /**
         * 定义表头
         */
        cols: [
            // 组合代码
            {field: 'portfolioId',width: 150, title: '组合代码', sortable: false}
            // 组合名称
            , {field: 'portfolioName',width: 150,title: '组合名称',sortable: false}
            // 起息日
            , {field: 'vdateStr',width: 150,title: '起息日', sortable: false}
            //到期日
            , {field: 'mdateStr',width: 150,title: '到期日', sortable: false}
            // 组合可用现金
            , {field: 'availableCash',width: 150,title: '组合可用现金',type: 'money', digit: 2, sortable: false}
        ]
    });
    /**
     * 画面加载完成
     */
    $(function() {
        table.load();
    });
});