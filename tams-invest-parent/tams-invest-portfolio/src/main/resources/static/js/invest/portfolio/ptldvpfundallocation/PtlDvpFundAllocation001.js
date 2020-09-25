/**
 * DVP资金调拨管理
 */

(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var portfolioId = $('#portfolioId').val();
        if(portfolioId && portfolioId != ''){
            portfolioId = portfolioId.join(',');
        }else {
            portfolioId = null;
        }
        
        return {
            portfolioId:portfolioId,
            counterAcctNo:$('#counterAcctNo').val(),
            myAcctNo:$('#myAcctNo').val(),
            payType:$('#payType').val(),
            dataStatus:$('#dataStatus').val(),
            dataDateStart:$('#dataDate').startDate(),
            dataDateEnd:$('#dataDate').endDate()
        };
    };
    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/portfolio/ptldvpfundallocation/PtlDvpFundAllocation002/init",
                    "98%", "90%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
                    portfolioId:data.portfolioId,
                    orderId:data.orderId?data.orderId:'',
                    workflowId:data.workflowId?data.workflowId:''});//主键组合代码
        },
        cols:[
            {field:'orderStatusStr', width:80 , title: 'PtlDvpFundAllocation001.orderStatus',sortField:'orderStatus'}
            ,{field:'tradeId', width:150 , title: '业务编号',sortField:'tradeId'}
            //,{field:'orderId', width:150 , title: '指令编号',sortField:'orderId'}
            ,{field:'portfolioId', width:200 , title: 'PtlDvpFundAllocation001.portfolioId',sortField:'portfolioId'}
            ,{field:'portfolioName', width:150 , title: 'PtlDvpFundAllocation001.portfolioName',sortField:'portfolioName',visible:false}
            ,{field:'counterAcctNo', width:200 ,title: 'PtlDvpFundAllocation001.counterAcctNo',sortField:'counterAcctNo'}
            ,{field:'myAcctNo', width:200 ,title: 'PtlDvpFundAllocation001.myAcctNo',sortField:'myAcctNo'}
            ,{field:'payTypeStr', width:150 ,title: 'PtlDvpFundAllocation001.payType',sortField:'payType'}
            ,{field:'settleAmt', width:150 , title: 'PtlDvpFundAllocation001.settleAmt',type:'number',sortField:'settleAmt'}
            ,{field:'settleDate', width:110 , title: 'PtlDvpFundAllocation001.settleDate',type:'date',sortField:'settleDate'}
            ,{field:'workflowId',visible:false}
        ]
    });
    
    /**
     * 导出
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();//获取参数
        Joyin.exportExcel('export', paramObj);//导出
    };
    
    /**
     * 画面加载完成
     */
    $(function() {
        $('#PtlDvpFundAllocation001_btn_search').click(function() {
            table.load();//加载表格
        });
        
        $('#PtlDvpFundAllocation001_btn_export').click(function() {
            exportExcel();
        });
        //新建事件
        $('#PtlDvpFundAllocation001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("PtlDvpFundAllocation002"),
                basePath + "invest/portfolio/ptldvpfundallocation/PtlDvpFundAllocation002/init",
                "98%", "90%", {pageModel:PAGE_MODEL_NEW});
        });
        
        $('#PtlDvpFundAllocation001_btn_revoke').click(function(){
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();//判断表格不为空，然后获取选择的数据
            }
            if (!selArr || selArr.length == 0) {//判断是否选择，如果没有则报错COMMON_E_0002
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }else if(selArr.length > 1){
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                return;
            }else if(selArr[0].orderStatus != pageStatus){
                Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0054", pageStatusText));
                return;
            }
            var checkObj = selArr[0];
            checkObj.operateType = "revoke";
            Joyin.ajaxJson(basePath + 'invest/portfolio/ptldvpfundallocation/PtlDvpFundAllocation001/check', checkObj, function(result) {//数据验证
                Joyin.confirm(result.data, function() {
                    Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                    result = result.replace(/\s+/g,"");
                    if (!result) {
                        result = '无';
                    }
                        checkObj.revokeReason = result;
                        WorkFlow.doRevokeList(basePath + 'invest/portfolio/ptldvpfundallocation/PtlDvpFundAllocation001/revoke', checkObj, "PtlDvpFundAllocation001_btn_revoke", "PtlDvpFundAllocation002");//删除
                    });
                });
            });
        })
    });
}());