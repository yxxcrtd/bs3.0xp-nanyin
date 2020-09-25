/**
 * 个人产品发行登记
 */
(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var repFinprodNameTemp = $('#repFinprodName').val();
        var repFinprodName= null;
        if(repFinprodNameTemp != null)
            repFinprodName=repFinprodNameTemp.join(",");
        
        return {
            repFinprodName: repFinprodName,
            repFinprodRegId:$('#repFinprodRegId').val(),
            issueType:$('#issueType').val(),
            profitType:$('#profitType').val(),
            tradeType:$('#tradeType').val(),
            dateSDate:$('#dataDate').startDate(),
            dateEDate:$('#dataDate').endDate()
        }
    };

    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable(
            {
                
                  ajax: { 
                          url: 'findPage', 
                          type: 'POST', 
                          data: function() {
                              return getSearchParams(); 
                              } 
                  },
                cols : [ 
                        {field : 'repFinprodId', width : 120, visible: false}
                       ,{field : 'repStatusDate', width : 120, title : 'RepFinProductRenewalRegist001.repStatusDate',type:'date',sortField:'REP_STATUS_DATE'}
                       , {field : 'repIssuePartyId', width : 150, title : 'RepFinProductRenewalRegist001.repIssuePartyId',sortField:'REP_ISSUE_PARTY_ID'} 
                       , {field : 'repFinprodRegId', width : 150, title : 'RepFinProductRenewalRegist001.repFinprodRegId',sortField:'REP_FINPROD_REG_ID'} 
                       , {field : 'repBankProdCode', width : 150, title : 'RepFinProductRenewalRegist001.repBankProdCode',sortField:'REP_BANK_PROD_CODE'} 
                       , {field : 'repBusVdate', width : 150, title : 'RepFinProductRenewalRegist001.repBusVdate',type:'date',sortField:'REP_BUS_VDATE'}
                       , {field : 'repBusMdate', width : 150, title : 'RepFinProductRenewalRegist001.repBusMdate',type:'date',sortField:'REP_BUS_MDATE'}
                       , {field : 'repSumApplyShare', width : 150, title : 'RepFinProductRenewalRegist001.repSumApplyShare',type:'number',sortField:'REP_SUM_APPLY_SHARE'}
                       , {field : 'repSumCashShare', width : 150, title : 'RepFinProductRenewalRegist001.repSumCashShare',type:'number',sortField:'REP_SUM_CASH_SHARE'}
                       , {field : 'repProdBalance', width : 150, title : 'RepFinProductRenewalRegist001.repProdBalance',type:'number',sortField:'REP_PROD_BALANCE'}
                       , {field : 'repProdShare', width : 150, title : 'RepFinProductRenewalRegist001.repProdShare',type:'number',sortField:'REP_PROD_SHARE'}
                       , {field : 'repInitNetval',width : 150, sortField:"repIsConstracte", title : 'RepFinProductRenewalRegist001.repInitNetval',type:'number',sortField:'REP_INIT_NETVAL'}
                       , {field : 'repProdNetval', width : 150, title : 'RepFinProductRenewalRegist001.repProdNetval',type:'number',sortField:'REP_PROD_NETVAL'}
                       , {field : 'repSumNetval', width : 200, title : 'RepFinProductRenewalRegist001.repSumNetval',type:'number',sortField:'REP_SUM_NETVAL'}
                       , {field : 'repNetvalCcyStr', width : 200, title : 'RepFinProductRenewalRegist001.repNetvalCcy',sortField:'REP_NETVAL_CCY'}
                       , {field : 'repInitNetvalCny', width : 200, title : 'RepFinProductRenewalRegist001.repInitNetvalCny',type:'number',sortField:'REP_INIT_NETVAL_CNY'}
                       , {field : 'repNetvalCny', width : 150,title : 'RepFinProductRenewalRegist001.repNetvalCny',type:'number', sortField:"REP_NETVAL_CNY"}
                       , {field : 'repSumNetvalCny', width : 200,title : 'RepFinProductRenewalRegist001.repSumNetvalCny',type:'number', sortField:"REP_SUM_NETVAL_CNY"}
                       , {field : 'repAchiYield', width : 200,title : 'RepFinProductRenewalRegist001.repAchiYield',type:'number', sortField:"REP_ACHI_YIELD"}
                       , {field : 'repNewExpectedYield', width : 200, title:'RepFinProductRenewalRegist001.repNewExpectedYield',type:'number',sortField:'REP_NEW_EXPECTED_YIELD'}
                       , {field : 'repAchiProfitCny', width : 200, title : 'RepFinProductRenewalRegist001.repAchiProfitCny',type:'number',sortField:'REP_ACHI_PROFIT_CNY'}
                       , {field : 'subscriptionHonour', width : 200, title : 'RepFinProductRenewalRegist001.subscriptionHonour',sortField:'REP_CCY'}
                       , {field : 'repRemark', width : 200, title : 'RepFinProductRenewalRegist001.repRemark',sortField:'REP_REMARK'}
                       
                ]
            });
    var getParamObj = function(){
        if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
        }
        var repFinprodId = new Array();
        if(!selArr || selArr.length <= 0){
            return;
        }else if (selArr && selArr.length > 0) {
            $.each(selArr, function(i, v) {  
                if(v.repFinprodId != null && v.repFinprodId != '' &&
                    v.repDurationRegDate != null){
                    repFinprodId[i] = v.repFinprodId+','+new Date(v.repDurationRegDate).format();
                }
            });
        }
        return {
            repFinprodId:repFinprodId.join('/')
        }
        
    }
    var exportExcel = function() {
        if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
        }
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        var paramObj = getParamObj();
        Joyin.exportExcel('export', paramObj);
    };
    var init = function(){
        //隐藏筛选按钮
        $('#RepFinProductPerIssRegist001_btn_filter').attr("style","display:none;");
    }
    /**
     * 画面加载完成
     */
    $(function() {
        init();
        // 绑定按钮事件
        $('#RepFinProductRenewalRegist001_btn_search').click(function() {
            table.load();
        });
        $('#RepFinProductRenewalRegist001_btn_export').click(function() {
            exportExcel();
        });
        
        
    });
}());