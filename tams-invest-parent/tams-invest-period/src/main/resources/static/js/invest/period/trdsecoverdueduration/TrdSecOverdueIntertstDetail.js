$(function () {

    var cols;
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var obj =  {};
       obj.finprodId = finprodId;
       obj.mdate = mdate;
       obj.secAcctId = secAcctId;
        if(liBranch){
            obj.liBranch = liBranch;
        }
        if(bjBranch){
            obj.bjBranch = bjBranch;
        }
        obj.payType = payType;
        obj.theoryPenaltyInt=theoryPenaltyInt;
        return obj;
    };
    var defaultOption = {
        ajax: {
            url: 'intertstDetailFindPage',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getSearchParams();//获取请求参数
            }//ajax请求数据
        },
        cols: [
            {field:'totleTradeId',sortField:'totleTradeId', width:200,title: '业务编号'}//组合代码
            ,{field:'tradeStatus',sortField:'tradeStatus', width:150,title: '有效状态'}//组合代码
            ,{field:'settleDate',sortField:'settleDate',width:150,title:'资金到账日',type:'date', digit:2}//除权日
            ,{field:'accountingAmt',sortField:'accountingAmt',width:170,title:'实收本息总额',type:'number', digit:2}//到账日
            ,{field:'isRevoke',width:0, visible : false}
        ],
        /**
         * 定义表头
         */
        sortfont:true
    };
    if(payType=='no'){//未结清
        var colss = [
            {field:'residueAmount',sortField:'residueAmount',width:170,title:'剩余应收金额',type:'number', digit:2}
            ,{field: 'operation', width:150, title: '撤销操作', type: 'action', buttons: ['edit'],
                render: function (data, type, row, meta) {
                	if(row.isRevoke&&row.isRevoke=='Y'){
                        return '<i class="btn layui-icon detail" data-ignore="true" title="撤销">&#xe60a;</i>';
                    }
                	return;
                }
            }
        ]
        defaultOption.cols =  defaultOption.cols.concat(colss);
    }else{
        var colss = [
            {field: 'operation', width:150, title: '撤销操作', type: 'action', buttons: ['edit'],
                render: function (data, type, row, meta) {
                	if(row.isRevoke&&row.isRevoke=='Y'){
                        return '<i class="btn layui-icon detail" data-ignore="true" title="撤销">&#xe60a;</i>';
                    }
                	return;
                }
            }
        ]
        defaultOption.cols =  defaultOption.cols.concat(colss);
    }

    /**
     * 定义逾期未结清数据表格
     */

    var table=$("#IntertstTable").joyintable(defaultOption);


    /**
     * 撤销操作
     */
    table.bind('click', '.detail', function(data, index) {
        if(data.tradeStatus){
            if(data.tradeStatus=='流程中'){//流程中
                Joyin.error('流程中的数据不能做撤销操作！')
                return;
            }else{
                if(data.isRevoke&&data.isRevoke=='Y'){//有效最新的一笔才能撤销
                    var object = {};
                    object.busiCode = data.totleTradeId;
                    
                    Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                    result = result.replace(/\s+/g,"");
                    if (!result) {
                        result = '无';
                    }
                        object.revokeReason = result;
                        object.operateType = "revoke";
                        WorkFlow.doRevoke(basePath+'invest/period/trdsecoverdueduration/TrdSecOverdueDuration002/revoke', object, "TrdSecOverdueDuration002_btn_revoke", "TrdSecOverdueDuration002");
                        table.table ? table.reload() : table.load();
                    });
                    //调用还本收息的撤销操作
                    /*Joyin.ajaxJson(basePath+'invest/period/trdsecoverdueduration/TrdSecOverdueDuration002/revoke',param,function (result) {
                        if(!result){
                            table.table ? table.reload() : table.load();
                        }
                    },false);*/
                }
            }
        }
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