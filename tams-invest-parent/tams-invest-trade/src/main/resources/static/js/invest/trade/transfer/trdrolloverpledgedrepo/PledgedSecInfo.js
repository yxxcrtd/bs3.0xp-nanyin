(function () {
    /**
     * 质押券拆分列表（Total）
     */
    var pledgeBondDetailTbl = $("#pledgedBondSplitList").joyintable({
        page: false,
        data: finDealPledgeList ? finDealPledgeList : [],
        height: 200,
        /**
         * 定义表头
         */
        cols: [
            // 债券代码
            {field: 'pledgeFinprodId',width: 150, title: '债券代码', sortable: false}
            // 债券简称
            , {field: 'finprodAbbr',width: 150,title: '债券简称',sortable: false}
            // 折算比例%
            , {field: 'pledgeRatio',width: 100,title: '折算比例%',type: 'number',digit: 2, sortable: false}
            //本次拆分指定质押券面总额
            , {field: 'desiPledgePrin',width: 200,title: '质押券面总额',type: 'money',edit:true,digit: 2, sortable: false}
        ]
    });
    /**
     * 画面加载完成
     */
    $(function () {
        // 初始化时，直接加载质押券列表
        pledgeBondDetailTbl.load();
    });
}());