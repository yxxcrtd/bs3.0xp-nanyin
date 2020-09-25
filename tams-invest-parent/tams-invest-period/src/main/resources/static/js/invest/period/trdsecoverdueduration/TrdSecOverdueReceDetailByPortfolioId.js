$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var obj =  {};
       obj.finprodId = finprodId;
       if(liBranch){
           obj.liBranch = liBranch;
       }
       if(bjBranch){
           obj.bjBranch = bjBranch;
       }
        obj.secAcctId = secAcctId;
       obj.portfolioId = portfolioId;
        return obj;
    };
    /**
     * 定义逾期未结清数据表格
     */

    var table=$("#receTable").joyintable({
        ajax: {
            url: 'findRecePositionDetailByPortfolioId',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getSearchParams();//获取请求参数
            }//ajax请求数据
        },
        /**
         * 定义表头
         */
        sortfont:true,
        cols:[
             {field:'portfolioId',sortField:'portfolioId', width:150,title: '组合代码'}//组合代码
            ,{field:'portfolioName',sortField:'portfolioName', width:150,title: '组合名称'}//组合代码
            ,{field:'theoryAmt',sortField:'theoryAmt',width:120,title:'理论本息总额',type:'number', digit:2}//除权日
            ,{field:'fpriceAmt',sortField:'fpriceAmt',width:120,title:'实收本息总额',type:'number', digit:2}//到账日
            ,{field:'theoryPrin',sortField:'theoryPrin',width:100,title:'未支付本金',type:'number', digit:2}//除权日
            ,{field:'theoryInt',sortField:'theoryInt',width:100,title:'未支付利息',type:'number', digit:2}//到账日
            ,{field:'theoryPenaltyInt',sortField:'theoryPenaltyInt',width:120,title:'未付罚息总额',type:'number', digit:2}//除权日
            ,{field:'penaltyIntamt',sortField:'penaltyIntamt',width:120,title:'实收罚息总额',type:'number', digit:2}//到账日
        ]
    });

    /**
     * 导出选中的表格数据
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();//请求参数
        Joyin.exportExcel('export', paramObj);//导出（共通）
    };
    /**
     * 画面加载完成
     */
    $(function() {
       table.load();
    });
}());