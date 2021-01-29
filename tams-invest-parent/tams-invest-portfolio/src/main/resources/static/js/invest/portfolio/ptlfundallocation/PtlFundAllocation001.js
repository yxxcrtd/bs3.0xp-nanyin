/**
 * 资金划拨管理
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
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/portfolio/ptlfundallocation/PtlFundAllocation002/init",
                    "98%", "90%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
                    portfolioId:data.portfolioId,
                    orderId:data.orderId});//主键组合代码
        },
        cols:[
        	// 调整画面表格列宽 by 赵东东 2019.10.29 start
            {field:'orderStatusStr',width:150, title: 'PtlFundAllocation001.orderStatus',sortField:'ORDER_STATUS'}
            ,{field:'orderId',title: '业务编号',sortField:'ORDER_ID'}
            ,{field:'portfolioId', title: 'PtlFundAllocation001.portfolioId',sortField:'PORTFOLIO_ID'}
            ,{field:'portfolioName',  title: 'PtlFundAllocation001.portfolioName',sortField:'PORTFOLIO_NAME'}
            ,{field:'counterAcctNo', title: 'PtlFundAllocation001.counterAcctNo',sortField:'COUNTER_ACCT_NO'}
            ,{field:'myAcctNo', title: 'PtlFundAllocation001.myAcctNo',sortField:'MY_ACCT_NO'}
            ,{field:'payTypeStr', title: 'PtlFundAllocation001.payType',sortField:'PAY_TYPE'}
            ,{field:'settleAmt', title: 'PtlFundAllocation001.settleAmt',type:'number',sortField:'SETTLE_AMT'}
            ,{field:'settleDate',width:200 , title: 'PtlFundAllocation001.settleDate',type:'date',sortField:'SETTLE_DATE'}
            // 调整画面表格列宽 by 赵东东 2019.10.29 end
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
        $('#PtlFundAllocation001_btn_search').click(function() {
            table.load();//加载表格
        });
        
        $('#PtlFundAllocation001_btn_export').click(function() {
            exportExcel();
        });
       //新建事件
        $('#PtlFundAllocation001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("PtlFundAllocation002"),
                basePath + "invest/portfolio/ptlfundallocation/PtlFundAllocation002/init",
                "98%", "90%", {pageModel:PAGE_MODEL_NEW});
        });
        
        $('#PtlFundAllocation001_btn_revoke').click(function(){
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();//判断表格不为空，然后获取选择的数据
            }
            if (!selArr || selArr.length == 0) {//判断是否选择，如果没有则报错COMMON_E_0002
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }else if(selArr.length > 1){
                Joyin.error(JoyinUtil.getLabel("PtlFundAllocation001.onlyOne"));
                return;
            }else if(selArr[0].orderStatus != pageStatus){
                Joyin.error(JoyinUtil.getLabel("PtlFundAllocation001.cancelStatus"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"revoke",orderId:selArr[0].orderId};//请求参数
            Joyin.ajaxJson(basePath + 'invest/portfolio/ptlfundallocation/PtlFundAllocation001/check', object, function(result) {//数据验证
                Joyin.confirm(JoyinUtil.getMessage('COMMON_C_0001',JoyinUtil.getMessage(JoyinUtil.getLabel("SysCommon.revoke"))), function(){
//                    WorkFlow.doRevokeList(basePath + 'invest/portfolio/ptlfundallocation/PtlFundAllocation001/revoke', object, "PtlFundAllocation001_btn_revoke", "PtlFundAllocation002");//删除
                	WorkFlow.doDeleteList(basePath + 'invest/portfolio/ptlfundallocation/PtlFundAllocation001/revoke', object, "PtlFundAllocation001_btn_revoke", "PtlFundAllocation002",function(){
                        table.reload();//回调事件请求及回调函数
                    });
                });
            });
        })
    });
}());