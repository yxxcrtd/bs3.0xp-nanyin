/**
 * 个人产品申报登记
 * huwenbin
 */
$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var finprodId='';
    	if($('#finprodMarketId').val() == null || $('#finprodMarketId').val() == ''){
    		finprodId = $('#finprodMarketId').val();
    	}else{
    		for(var i=0; i<$('#finprodMarketId').select2('data').length; i++){
    			if(i == $('#finprodMarketId').select2('data').length-1){
    				finprodId = finprodId + $('#finprodMarketId').select2('data')[i].object;
    			}
    			else{
    				finprodId = finprodId + $('#finprodMarketId').select2('data')[i].object+",";
    			}
    		}
    		
    	}
        return {
        	finprodId:finprodId,
        	startRepStatusDate:$("#repStatusDate").startDate(),
        	endRepStatusDate:$("#repStatusDate").endDate(),
        	start:$("#startEnd").startDate(),
        	end:$("#startEnd").endDate()
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
        cols:[
          	 {field : 'repFinprodId', width : 120, visible: false}
            ,{field:'repStatusDate', width:150, title: 'RepFinProductPerDeclare001.dataDate',sortField:'REP_STATUS_DATE',type:'date'}
            ,{field:'repFinprodName', width:150,  title: 'RepFinProductPerDeclare001.productName',sortField:'REP_FINPROD_NAME'}
            ,{field:'repFinprodInsId', width:150,  title: 'RepFinProductPerDeclare001.inlineIdentifyCode',sortField:'REP_FINPROD_INS_ID'}
            ,{field:'repFinprodBrand', width:150,  title: 'RepFinProductPerDeclare001.productBrand',sortField:'REP_FINPROD_BRAND'}
            ,{field:'repFinprodStage', width:150,  title: 'RepFinProductPerDeclare001.productPeriod',sortField:'REP_FINPROD_STAGE'}
            ,{field:'repIssuePartyId', width:150,  title: 'RepFinProductPerDeclare001.publisherCode',sortField:'REP_ISSUE_PARTY_ID'}
            ,{field:'repApproverId', width:150,  title: 'RepFinProductPerDeclare001.identityCardNumber',sortField:'REP_APPROVER_ID'}
            ,{field:'repApproverName',  width:150, title: 'RepFinProductPerDeclare001.identityName',sortField:'REP_APPROVER_NAME'}
            ,{field:'repDesignerId', width:150,  title: 'RepFinProductPerDeclare001.productDesignerIDCar',sortField:'REP_APPROVER_NAME'}
            ,{field:'repDesignerName', width:150,  title: 'RepFinProductPerDeclare001.productDesignerName',sortField:'REP_DESIGNER_NAME'}
            ,{field:'repManagerId', width:150,  title: 'RepFinProductPerDeclare001.investManagerIdentityCardNumber',sortField:'REP_MANAGER_ID'}
            ,{field:'repManagerName', width:150,  title: 'RepFinProductPerDeclare001.investManagerName',sortField:'REP_MANAGER_NAME'}
            ,{field:'repLiaisonName',  width:150, title: 'RepFinProductPerDeclare001.businessLiaisonName',sortField:'REP_LIAISON_NAME'}
            ,{field:'repLiaisonTel', width:150,  title: 'RepFinProductPerDeclare001.businessLiaisonMachine',sortField:'REP_LIAISON_TEL'}
            ,{field:'repLiaisonMobile', width:150,  title: 'RepFinProductPerDeclare001.businessLiaisonPhone',sortField:'REP_LIAISON_MOBILE'}
            ,{field:'repLiaisonEmail', width:150,  title: 'RepFinProductPerDeclare001.businessLiaisonMail',sortField:'REP_LIAISON_EMAIL'}
            ,{field:'repProfitTypeStr',  width:150, title: 'RepFinProductPerDeclare001.productIncomeType',sortField:'REP_PROFIT_TYPE'}
            ,{field:'repTermStr',  width:150, title: 'RepFinProductPerDeclare001.productTerm',sortField:'REP_TERM'}
            ,{field:'repInvestorTypeStr', width:150,  title: 'RepFinProductPerDeclare001.targetCustomerType',sortField:'REP_INVESTOR_TYPE'}
            ,{field:'repInvestorType', visible:false}
            ,{field:'repInvestAreaStr',  width:150, title: 'RepFinProductPerDeclare001.capitalInvestArea',sortField:'REP_INVEST_AREA'}
            ,{field:'repInvestCountryStr', width:150,  title: 'RepFinProductPerDeclare001.investCountryRegion',sortField:'REP_INVEST_COUNTRY'}
            ,{field:'repFinanSerModelStr', width:150,  title: 'RepFinProductPerDeclare001.financialServiceMode',sortField:'REP_FINAN_SER_MODEL'}
            ,{field:'repOperateModeStr', width:150,  title: 'RepFinProductPerDeclare001.productOperationMode',sortField:'REP_OPERATE_MODE'}
            ,{field:'repBookModeStr', width:150,  title: 'RepFinProductPerDeclare001.accountingMethod',sortField:'REP_BOOK_MODE'}
            ,{field:'repCmmodeStr', width:150,  title: 'RepFinProductPerDeclare001.productAssetMode',sortField:'REP_BOOK_MODE'}
            ,{field:'repPmmodeStr', width:150,  title: 'RepFinProductPerDeclare001.productManagementModel',sortField:'REP_PMMODE'}
            ,{field:'repMname', width:150,  title: 'RepFinProductPerDeclare001.realManagerName',sortField:'REP_MNAME'}
            ,{field:'repPricedModeStr', width:150,  title: 'RepFinProductPerDeclare001.productPricingMethod',sortField:'REP_PRICED_MODE'}
            ,{field:'repInvAssetTypeStr', width:150, title: 'RepFinProductPerDeclare001.investAssetsType',sortField:'REP_INV_ASSET_TYPE'}
            ,{field:'repConPrdTraget', width:150, title: 'RepFinProductPerDeclare001.structuralProductPeg',sortField:'REP_CON_PRD_TRAGET'}
            ,{field:'repContModeStr', width:150, title: 'RepFinProductPerDeclare001.cooperationModel',sortField:'REP_CON_PRD_TRAGET'}
            ,{field:'repCoopName', width:150, title: 'RepFinProductPerDeclare001.cooperateInstituteName',sortField:'REP_COOP_NAME'}
            ,{field:'repInvTypeRat', width:150,  title: 'RepFinProductPerDeclare001.investAssetsTypesProportions',sortField:'REP_INV_TYPE_RAT'}
            ,{field:'repIsYieldStr', width:150,  title: 'RepFinProductPerDeclare001.isThereExpectedReturnRate',sortField:'REP_IS_YIELD'}
            ,{field:'repHighYield', width:150,  title: 'RepFinProductPerDeclare001.maximumAnnualRate',sortField:'REP_HIGH_YIELD'}
            ,{field:'repLowYield', width:150, title: 'RepFinProductPerDeclare001.minimunAnnualRate',sortField:'REP_LOW_YIELD'}
            ,{field:'repIsYieldBasisStr', width:150, title: 'RepFinProductPerDeclare001.whetherBasisEstimating',sortField:'REP_IS_YIELD_BASIS'}
            ,{field:'repRiskTypeStr', width:150, title: 'RepFinProductPerDeclare001.investorRiskPrefer',sortField:'REP_RISK_TYPE'}
            ,{field:'repSaleAreaStr', width:150, title: 'RepFinProductPerDeclare001.productSalesArea',sortField:'REP_SALE_AREA'}
            ,{field:'repRaiseCcyStr', width:150, title: 'RepFinProductPerDeclare001.raisingCurrency',sortField:'REP_RAISE_CCY'}
            ,{field:'repCashPrinCcyStr', width:150, title: 'RepFinProductPerDeclare001.cashGGoldCoin',sortField:'REP_CASH_PRIN_CCY'}
            ,{field:'repCashProfCcyStr', width:150, title: 'RepFinProductPerDeclare001.cashReceiptsCurrency',sortField:'REP_CASH_PROF_CCY'}
            ,{field:'repSaleAmtStr', width:150, title: 'RepFinProductPerDeclare001.startPointSalesAmount',sortField:'REP_SALE_AMT_STR'}
            ,{field:'repRaiseAmtPlan', width:150, title: 'RepFinProductPerDeclare001.planRaiseMoney',sortField:'REP_RAISE_AMT_PLAN'}
            ,{field:'repRaisePstrdateStr', width:150, title: 'RepFinProductPerDeclare001.startDateFrom',sortField:'REP_RAISE_PSTRDATE'}
            ,{field:'repRaisePenddateStr', width:150, title: 'RepFinProductPerDeclare001.startDateTo',sortField:'REP_RAISE_PENDDATE'}
            ,{field:'repInvestPrinDateStr', width:150, title: 'RepFinProductPerDeclare001.capitalInvestmentAccountDate',sortField:'REP_INVEST_PRIN_DATE'}
            ,{field:'repInvestProfDateStr', width:150, title: 'RepFinProductPerDeclare001.earningsInvestmentAccountDate',sortField:'REP_INVEST_PROF_DATE'}
            ,{field:'repSaleFeeRate', width:150, title: 'RepFinProductPerDeclare001.salesFee',sortField:'REP_SALE_FEE_RATE'}
            ,{field:'repInDepName', width:150, title: 'RepFinProductPerDeclare001.domesticTrusteeshipAgencyName',sortField:'REP_SALE_FEE_RATE'}
            ,{field:'repInDepCode', width:150, title: 'RepFinProductPerDeclare001.domesticTrusteeshipAgency',sortField:'REP_IN_DEP_CODE'}
            ,{field:'repOutDepCtryStr', width:150, title: 'RepFinProductPerDeclare001.foreignTrusteeshipOrganizationCountry',sortField:'REP_OUT_DEP_CTRY'}
            ,{field:'repOutDepName', width:150,  title: 'RepFinProductPerDeclare001.overseasTrusteeshipAgencyName',sortField:'REP_OUT_DEP_NAME'}
            ,{field:'repTruFeeRate', width:150, title: 'RepFinProductPerDeclare001.trusteeshipRate',sortField:'REP_TRU_FEE_RATE'}
            ,{field:'repRiskLevelStr', width:150, title: 'RepFinProductPerDeclare001.productRiskGrade',sortField:'REP_RISK_LEVEL'}
            ,{field:'repTermRightFlagStr', width:150, title: 'RepFinProductPerDeclare001.issuerEarlyTermination',sortField:'REP_TERM_RIGHT_FLAG'}
            ,{field:'repCusRedeemFlagStr', width:150, title: 'RepFinProductPerDeclare001.customerRedemptionIdentity',sortField:'REP_CUS_REDEEM_FLAG'}
            ,{field:'repCreditFlagStr', width:150, title: 'RepFinProductPerDeclare001.productCreditSign',sortField:'REP_CREDIT_FLAG'}
            ,{field:'repCreditTypeStr', width:150, title: 'RepFinProductPerDeclare001.productUpdatingOrganizeType',sortField:'REP_CREDIT_TYPE'}
            ,{field:'repCreditModeStr', width:150, title: 'RepFinProductPerDeclare001.productUpdatingForm',sortField:'REP_CREDIT_MODE'}
            ,{field:'repRemark', width:150,  title: 'RepFinProductPerDeclare001.remarks',sortField:'REP_REMARK'}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt'], visible:false}
        ]
    });
    
    
    /**
     * 导出
     */
    /*var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };*/
    
    /*var getParamObj = function(){
    	if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
        }
    	var repFinprodId = new Array();
    	if (selArr || selArr.length > 0) {
    		$.each(selArr, function(i, v) {
    			if(v.repInvestorType == '03' || v.repInvestorType == '05'){
    				if(v.repFinprodId != null || v.repFinprodId != ''){
    	    	    	repFinprodId[i] = v.repFinprodId;
    	    	    }
	    	    }
    		});
    	}
    	var flag = true;
    	if (selArr || selArr.length > 0) {
    		$.each(selArr, function(index, obj) {  
	    	    if(obj.repInvestorType != '03' && obj.repInvestorType != '05'){
	    	    	flag = false;
	    	    }
    		});
    	}
    	if(!flag){
    		Joyin.confirm(JoyinUtil.getMessage("REP_E_0009"),function(){
    			return {
            		repFinprodId:repFinprodId
            	}
    		},function(){
    			return;
    		});
    	}else{
    		return {
        		repFinprodId:repFinprodId
        	}
    	}
    }*/
    var exportExcel = function() {
    	if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
        }
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        var repFinprodId = new Array();
    	if (selArr || selArr.length > 0) {
    		$.each(selArr, function(i, v) {
    			if(v.repInvestorType == '06' || v.repInvestorType == '05'){
    				if(v.repFinprodId != null || v.repFinprodId != ''){
    	    	    	repFinprodId[i] = v.repFinprodId;
    	    	    }
	    	    }
    		});
    	}
    	var flag = true;
    	if (selArr || selArr.length > 0) {
    		$.each(selArr, function(index, obj) {  
	    	    if(obj.repInvestorType != '06' && obj.repInvestorType != '05'){
	    	    	flag = false;
	    	    }
    		});
    	}
    	if(!flag){
    		Joyin.confirm(JoyinUtil.getMessage("REP_E_0009"),function(){
    			if(repFinprodId.length>0){
    				Joyin.exportExcel('export', {repFinprodId:repFinprodId});
    			}
    			else{
    				Joyin.error(JoyinUtil.getMessage("REP_E_00010"));
    			}
    		},function(){
    			return;
    		});
    	}else{
    		Joyin.exportExcel('export', {repFinprodId:repFinprodId});
    	}
	    
    };
    
    /**
     * 递归调用导出
     */
    var currentIndex = 0;
    var getCreateDeclaration = function(selArr){
    	if(currentIndex >= selArr.length){
    		return;
    	}
    	var obj ={selectedItem:new Array(selArr[currentIndex])};
    	Joyin.exportExcel(basePath+"invest/report/debtreport/repfinproductperdeclare/RepFinProductPerDeclare001/createDeclaration", {dtoStr:JSON.stringify(obj)},function(){
    		currentIndex++;
    		getCreateDeclaration(selArr);
    	});
    }
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#RepFinProductPerDeclare001_btn_search').click(function() {
            table.load();
        });
        $('#RepFinProductPerDeclare001_btn_export').click(function() {
            exportExcel();
        });
        $('#RepFinProductPerDeclare001_btn_createDeclaration').click(function() {
        	if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            currentIndex = 0;
            getCreateDeclaration(selArr);
        });
        
    });
}());