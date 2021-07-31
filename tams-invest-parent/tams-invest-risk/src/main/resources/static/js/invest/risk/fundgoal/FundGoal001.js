(function(){
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            finprodId:$('#finprodId').val(),
        }
    };

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },

        cols:[
            {field:'finprodMarket', title: 'FundGoal001.finprodId',width : 150}//资产代码
            ,{field:'finprodAbbr', sortField:'FINPROD_ABBR',title: 'FundGoal002.finprodAbbr',width:150}//资产简称
            ,{field:'goalDate', title: 'FundGoal002.goalDate',width:150,type:'date'}//投向维护日
            ,{field:'nextPubDate', title: 'FundGoal002.nextPubDate',width:150,type:'date'}//下一投向公布日
            ,{field:'bEquityPer', title: 'FundGoal002.equityPer', width : 150,type: 'number'}//权益类占比
            ,{field:'bCreditorRightsPer', title: 'FundGoal002.creditorRightsPer', width : 150,type: 'number'}//债权类占比
            ,{field:'bCommodityDervPer', title: 'FundGoal002.commodityDervPer', width : 150,type: 'number'}//商品及衍生品占比
            ,{field:'bDebtPer', title: 'FundGoal002.debtPer', width : 150,type: 'number'}//负债占比
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit']}
        ]
    });

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.goalDate = new Date(data.goalDate).format('yyyy-MM-dd');
        Joyin.ajaxJson(basePath + 'invest/risk/fundgoal/FundGoal002/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("FundGoal002.edit"),
                basePath + "invest/risk/fundgoal/FundGoal002/init",
                "95%", "90%", {pageModel:PAGE_MODEL_EDIT,
                   finprodId:data.finprodId,goalDate:data.goalDate},function() {
                    table.reload();
                });
        });
    });



    //点击按钮事件
    $(function() {
        //查询
        $('#FundGoal001_btn_search').click(function() {
            table.load();
        });

        $('#FundGoal001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("FundGoal002"),
                basePath + "invest/risk/fundgoal/FundGoal002/init",
                "95%", "90%", {pageModel:PAGE_MODEL_NEW});
        });


        $('#FundGoal001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete",pageModel:'02'};
            Joyin.ajaxJson(basePath + 'invest/risk/fundgoal/FundGoal001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/risk/fundgoal/FundGoal001/delete', object, "FundGoal001_btn_delete", "FundGoal002",function() {
                        table.reload();
                    });
                });
            });
        });

        //重置
        $('#FundGoal001_btn_reset').click(function(){
            $('#conditionArea').find("select").val("").trigger("change");
            table.clear();
        });
    });


}());
