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
        return obj;
    };
    /**
     * 定义逾期未结清数据表格
     */

    var table=$("#receTable").joyintable({
        ajax: {
            url: 'recePositionDetailFindPage',//请求路径
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
             {field:'portfolioId',sortField:'portfolioId', width:155,title: '组合代码'}//组合代码
            ,{field:'portfolioName',sortField:'portfolioName', width:155,title: '组合名称'}//组合代码
            ,{field:'theoryAmt',sortField:'theoryAmt',width:120,title:'理论本息总额',type:'number', digit:2}//除权日
            ,{field:'fpriceAmt',sortField:'fpriceAmt',width:120,title:'实收本息总额',type:'number', digit:2}//到账日
            ,{field:'theoryPrin',sortField:'theoryPrin',width:100,title:'未支付本金', type:'number',digit:2}//除权日
            ,{field:'theoryInt',sortField:'theoryInt',width:100,title:'未支付利息',type:'number', digit:2}//到账日
            ,{field:'theoryPenaltyInt',sortField:'theoryPenaltyInt',width:120,title:'未付罚息总额',type:'number', digit:2}//除权日
            ,{field:'penaltyIntamt',sortField:'penaltyIntamt',width:120,title:'实收罚息总额', type:'number',digit:2}//到账日
            ,{field:'childPortfolioIds', visible : false}
            ,{field: 'operation',width:100, title: 'SysCommon.operation', type: 'action', buttons: ['edit'],
                render: function (data, type, row, meta) {
                    if(row.childPortfolioIds){
                        return '<i class="btn layui-icon detail" data-ignore="true" title="明细">&#xe60a;</i>';
                    }
                }
            }
        ]
    });

    table.bind('click', '.detail', function(data, index) {
        var param = {};
        param.finprodId = finprodId;
        if(liBranch){
            param.liBranch = liBranch;
        }
        if(bjBranch){
            param.bjBranch = bjBranch;
        }
        param.secAcctId = secAcctId;
        param.portfolioId = data.portfolioId;
        Joyin.dialog('子组合持仓明细信息', JoyinUtil.getRealPath('invest/period/trdsecoverdueduration/TrdSecOverdueDuration001/initRecePositionDetailByPortfolioId'), '90%', '90%',param);
        }
    )

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