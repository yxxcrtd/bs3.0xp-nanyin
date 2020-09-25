/**
 * 通道资金划拨管理
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
            chlId:$('#chlId').val(),
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
                    basePath + "invest/portfolio/ptlchlfundallocation/PtlChlFundAllocation002/init",
                    "98%", "90%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
                    portfolioId:data.portfolioId,
                    orderId:data.orderId});//主键组合代码
        },
        cols:[
            {field:'orderStatusStr', width:80 , title: 'PtlChlFundAllocation001.orderStatus',sortField:'orderStatus'}
            ,{field:'orderId', width:150 , title: '业务编号',sortField:'orderId'}
            ,{field:'portfolioId', width:130 , title: 'PtlChlFundAllocation001.portfolioId',sortField:'portfolioId'}
            ,{field:'portfolioName', width:140 , title: 'PtlChlFundAllocation001.portfolioName',sortField:'portfolioName'}
            ,{field:'chlId', width:130 , title: function(){return JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("ChannelAsset002.finprodIdBak");}(),sortField:'chlId'}
            ,{field:'chlName', width:140 , title: function(){return JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("ChannelAsset002.finprodNameBak");}(),sortField:'chlName'}
            ,{field:'counterAcctNo', width:150 ,title: 'PtlChlFundAllocation001.counterAcctNo',sortField:'counterAcctNo'}
            ,{field:'myAcctNo', width:150 ,title: 'PtlChlFundAllocation001.myAcctNo',sortField:'myAcctNo'}
            ,{field:'payTypeStr', width:100 ,title: 'PtlChlFundAllocation001.payType',sortField:'payType'}
            ,{field:'settleDate', width:90 , title: 'PtlChlFundAllocation001.settleDate',type:'date',sortField:'settleDate'}
            ,{field:'settleAmt', width:130 , title: 'PtlChlFundAllocation001.settleAmt',type:'number',sortField:'settleAmt'}
            // 划款类型选择通道回款增加本金和收益 add by renping 20190929 start
            ,{field:'prinAmt', width:130 , title: 'PtlChlFundAllocation001.prinAmt',type:'number',sortField:'prinAmt'}
            ,{field:'intAmt', width:130 , title: 'PtlChlFundAllocation001.intAmt',type:'number',sortField:'intAmt'}
            // 划款类型选择通道回款增加本金和收益 add by renping 20190929 end
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
        $('#PtlChlFundAllocation001_btn_search').click(function() {
            table.load();//加载表格
        });
        
        $('#PtlChlFundAllocation001_btn_export').click(function() {
            exportExcel();
        });
        //新建事件
        $('#PtlChlFundAllocation001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("PtlChlFundAllocation002"),
                basePath + "invest/portfolio/ptlchlfundallocation/PtlChlFundAllocation002/init",
                "98%", "90%", {pageModel:PAGE_MODEL_NEW});
        });
        
        $('#PtlChlFundAllocation001_btn_revoke').click(function(){
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
                Joyin.error(JoyinUtil.getMessage("已撤销的交易不允许撤销", pageStatusText));
                return;
            }
            var checkObj = selArr[0];
            checkObj.operateType = "revoke";
            Joyin.ajaxJson(basePath + 'invest/portfolio/ptlchlfundallocation/PtlChlFundAllocation001/check', checkObj, function(result) {//数据验证
                Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                    result = result.replace(/\s+/g,"");
                    if (!result) {
                        result = '无';
                    }
                    checkObj.revokeReason = result;
                    WorkFlow.doRevokeList(basePath + 'invest/portfolio/ptlchlfundallocation/PtlChlFundAllocation001/revoke', checkObj, "PtlChlFundAllocation001_btn_revoke", "PtlChlFundAllocation002");//删除
                });
            });
        })
    });
}());