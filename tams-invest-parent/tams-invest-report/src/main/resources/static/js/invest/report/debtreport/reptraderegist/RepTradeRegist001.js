(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var repFinprodIdTemp = $('#repFinprodId').val();
        var repFinprodId = null;
        if(repFinprodIdTemp){
            repFinprodId = repFinprodIdTemp.join(",");
        }
        var repFinprodRegIdTemp = $('#repFinprodRegId').val();
        var repFinprodRegId = null;
        if(repFinprodRegIdTemp){
            repFinprodRegId = repFinprodRegIdTemp.join(",");
        }
        return {
            repFinprodId: repFinprodId,
            repFinprodRegId:repFinprodRegId,
            repBankAssetCode:$('#repBankAssetCode').val(),
            repBankTradeCode:$('#repBankTradeCode').val(),
            repTradeDateStart:$('#repTradeDate').startDate(),
            repTradeDateEnd:$('#repTradeDate').endDate(),
            repStatusDateStart:$('#repStatusDate').startDate(),
            repStatusDateEnd:$('#repStatusDate').endDate(),
            repSendStatus:$('#repSendStatus').val()
        }
    };    
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({        
        ajax:{
            url:'findPage',
            type:'POST',
            data:function (){
                return getSearchParams();
            }
        },
        cols:[
             {field:'repStatusDate', width:150, title: 'RepTradeRegist001.repStatusDate',type:'date',sortField:'REP_STATUS_DATE'}
            ,{field:'finprodId', width:150, title: 'RepTradeRegist001.repFinprodId',sortField:'FINPROD_ID'}
            ,{field:'finprodName', width:150, title: 'RepTradeRegist001.repFinprodName',sortField:'F.FINPROD_NAME'}
            ,{field:'repFinprodRegId', width:150, title: 'RepTradeRegist001.repFinprodRegId',sortField:'REP_FINPROD_REG_ID'}
            ,{field:'repIssuePartyId', width:150, title: 'RepTradeRegist001.repIssuePartyId',sortField:'REP_ISSUE_PARTY_ID'}
            ,{field:'repBankTradeCode', width:150, title: 'RepTradeRegist001.repBankTradeCode',sortField:'REP_BANK_TRADE_CODE'}
            ,{field:'repBankAssetCode', width:150, title: 'RepTradeRegist001.repBankAssetCode',sortField:'REP_BANK_ASSET_CODE'}//
            ,{field:'ccyStr', width:150, title: 'RepTradeRegist001.repCcy',sortField:'repCcy',sortField:'REP_CCY'}
            ,{field:'repOccurMoney', width:150, title: 'RepTradeRegist001.repOccurMoney',sortField:'REP_OCCUR_MONEY'}//
            ,{field:'repMoneyCny', width:150, title: 'RepTradeRegist001.repMoneyCny',sortField:'REP_OCCUR_MONEY'}
            ,{field:'repCount', width:150, title: 'RepTradeRegist001.repCount',type:'number',sortField:'REP_COUNT'}
            ,{field:'repMoneyFlowTypeStr', width:150, title: 'RepTradeRegist001.repMoneyFlowType',sortField:'REP_MONEY_FLOW_TYPE'}
            ,{field:'repMoneyFlowTypeExp', width:150, title: 'RepTradeRegist001.repMoneyFlowTypeExp',sortField:'REP_MONEY_FLOW_TYPE_EXP'}
            ,{field:'repTradeDate', width:150, title: 'RepTradeRegist001.repTradeDate',type:'date',sortField:'REP_MONEY_FLOW_TYPE_EXP'}
            ,{field:'repTradeComponent', width:150, title: 'RepTradeRegist001.repTradeComponent',sortField:'REP_TRADE_COMPONENT'}
            ,{field:'repUnitPriceFull', width:150, title: 'RepTradeRegist001.repUnitPriceFull',sortField:'REP_UNIT_PRICE_FULL'} 
            ,{field:'repUnitPriceNet', width:150, title: 'RepTradeRegist001.repUnitPriceNet',sortField:'REP_UNIT_PRICE_NET'} 
            ,{field:'repExpireYield', width:150, title: 'RepTradeRegist001.repExpireYield',sortField:'REP_EXPIRE_YIELD'} 
            ,{field:'repDelCode', width:150, title: 'RepTradeRegist001.repDelCode',sortField:'REP_DEL_CODE'} 
            ,{field:'repRemark', width:150, title: 'RepTradeRegist001.repRemark',sortField:'REP_REMARK'} 
        ]
    });        
    var getParamObj = function(){
        if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
        }
        var primaryKey = new Array();
        if (selArr || selArr.length > 0) {
            $.each(selArr, function(i, v) {  
                if(v.repBankTradeCode != null && v.repBankTradeCode != '' && v.repMoneyFlowType != null && v.repMoneyFlowType != ''){
                    primaryKey[i] = v.repBankTradeCode+'_'+v.repMoneyFlowType;
                }
            });
        }
        return {
            primaryKey:primaryKey
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
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#RepTradeRegist001_btn_search').click(function() {
            table.load();            
        });
        $('#RepTradeRegist001_btn_export').click(function() {
            exportExcel();
        });                    
    });
}());