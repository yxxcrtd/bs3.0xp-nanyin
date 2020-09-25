(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var productCodeTemp = $('#productCode').val();
        var productCode = null;
        if(productCodeTemp){
            productCode = productCodeTemp.join(",");
        }
        return {
            productCode: productCode,
            registrationCode:$('#registrationCode').val(),
            customerType:$('#customerType').val(),
            startDateStart:$('#startDate').startDate(),
            startDateEnd:$('#startDate').endDate(),
            dataDateStart:$('#dataDate').startDate(),
            dateDateEnd:$('#dataDate').endDate()
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
            {field:'repStatusDate', width:150, title: 'RepRaiseSumRegist001.dataDate',type:'date',sortField:'REP_STATUS_DATE'}
            ,{field:'repIssuePartyName', width:150, title: 'RepRaiseSumRegist001.productCode',sortField:'REP_ISSUE_PARTY_NAME'}
            ,{field:'repIssuePartyId', width:150, title: 'RepRaiseSumRegist001.publisherCode',sortField:'REP_ISSUE_PARTY_ID'}
            ,{field:'repFinprodRegId', width:150, title: 'RepRaiseSumRegist001.registrationCode',sortField:'REP_FINPROD_REG_ID'}
            ,{field:'repPerSum', width:150, title: 'RepRaiseSumRegist001.individualInvestorNum',sortField:'REP_PER_SUM'}
            ,{field:'repLegalpersonSum', width:150, title: 'RepRaiseSumRegist001.legalInvestorNum',sortField:'REP_LEGALPERSON_SUM'}
            ,{field:'repNLegalpersonSum', width:150, title: 'RepRaiseSumRegist001.notLegalInvestorNum',sortField:'REP_N_LEGALPERSON_SUM'}
            ,{field:'repActRaiseSum', width:150, title: 'RepRaiseSumRegist001.actualCollection',sortField:'REP_ACT_RAISE_SUM',type:'number'}
            ,{field:'repShareSum', width:150, title: 'RepRaiseSumRegist001.totalShare',sortField:'REP_SHARE_SUM',type:'number'}
            ,{field:'repIsOtherSaleStr', width:150, title: 'RepRaiseSumRegist001.judge',sortField:'REP_IS_OTHER_SALE'}
            ,{field:'repOtherSaleSum', width:150, title: 'RepRaiseSumRegist001.salesAmount',sortField:'REP_OTHER_SALE_SUM',type:'number'}
            ,{field:'repSaleAreaStr', width:150, title: 'RepRaiseSumRegist001.areaAndAmount',sortField:'REP_AREA_RAISE_SUM'}
            ,{field:'repSubscriptionCcy', width:150, title: 'RepRaiseSumRegist001.currency',sortField:'REP_SUBSCRIPTION_CCY'}
            ,{field:'repRemark', width:150, title: 'RepRaiseSumRegist001.remark',sortField:'REP_REMARK'}            
        ]
    });        
    var getParamObj = function(){
        if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
        }
        var repFinprodId = new Array();
        if (selArr || selArr.length > 0) {
            $.each(selArr, function(i, v) {  
                if(v.repFinprodId != null && v.repFinprodId != ''){
                    repFinprodId[i] = v.repFinprodId;
                }
            });
        }
        return {
            repFinprodId:repFinprodId.join(',')
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
        $('#RepRaiseSumRegist001_btn_search').click(function() {
            table.load();            
        });
        $('#RepRaiseSumRegist001_btn_export').click(function() {
            exportExcel();
        });                    
    });
}());