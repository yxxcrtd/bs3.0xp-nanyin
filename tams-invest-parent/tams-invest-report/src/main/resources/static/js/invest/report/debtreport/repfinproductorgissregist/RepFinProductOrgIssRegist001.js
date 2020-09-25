$(function() {
	

    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var str = $('#repFinprodName').select2('data');
    	var repFinprodNameTemp = new Array();
    	if(str && str.length > 0){
    		$.each(str, function(i, v) {
    			if(v.object){
    				repFinprodNameTemp[i] = v.object
    			}
    		})
    	}
    	var repFinprodName= null;
        if(repFinprodNameTemp != null)
        	repFinprodName=repFinprodNameTemp.join(",");
        return {
        	repFinprodName: repFinprodName,
        	repInvestorType:$('#repInvestorType').val(),
        	productStartingSDate:$('#productStartingDate').startDate(),
        	productStartingEDate:$('#productStartingDate').endDate(),
        	repStatusSDate:$('#repStatusDate').startDate(),
        	repStatusEDate:$('#repStatusDate').endDate()
        }
    };

    /**
     * 定义数据表格
     */
    
    var table=$("#dataTable").joyintable({
    	ajax: { url: 'findPage', type: 'POST', data: function() {
            return getSearchParams(); } },
           
        /*
         * 定义表头
         */
        cols:[
              {field:'repFinprodId', width : 120, visible: false}
            ,{field:'repStatusDate', width:100, title: 'RepFinProductOrgIssRegist001.repStatusDate',type:'date', sortField:'REP_STATUS_DATE'}
            ,{field:'repFinprodName',width:150,  title: 'RepFinProductOrgIssRegist001.repFinprodName', sortField:'REP_STATUS_DATE'}
            ,{field:'repFinprodInsId',width:150,title:'RepFinProductOrgIssRegist001.repFinprodInsId', sortField:'REP_FINPROD_INS_ID'}
            ,{field:'repIssuePartyId',width:120,title:'RepFinProductOrgIssRegist001.repIssuePartyId', sortField:'REP_ISSUE_PARTY_ID'}
            ,{field:'repApproverId',width:180, title:'RepFinProductOrgIssRegist001.repApproverId', sortField:'REP_APPROVER_ID'}
            ,{field:'repApproverName',width:180, title:'RepFinProductOrgIssRegist001.repApproverName', sortField:'REP_APPROVER_NAME'}
            ,{field:'repDesignerId',width:180, title:'RepFinProductOrgIssRegist001.repDesignerId', sortField:'REP_DESIGNER_ID'}
            ,{field:'repDesignerName', width:180, title: 'RepFinProductOrgIssRegist001.repDesignerName', sortField:'REP_DESIGNER_NAME'}
            ,{field:'repManagerId',width:180,title:'RepFinProductOrgIssRegist001.repManagerId', sortField:'REP_MANAGER_ID'}
            ,{field:'repManagerName',width:180,title:'RepFinProductOrgIssRegist001.repManagerName', sortField:'REP_MANAGER_NAME'}
            ,{field:'repLiaisonName',width:180, title:'RepFinProductOrgIssRegist001.repLiaisonName', sortField:'REP_LIAISON_NAME'}
            ,{field:'repLiaisonTel', width:180,title:'RepFinProductOrgIssRegist001.repLiaisonTel', sortField:'REP_LIAISON_TEL'}
            ,{field:'repLiaisonMobile',width:150, title:'RepFinProductOrgIssRegist001.repLiaisonMobile', sortField:'REP_LIAISON_MOBILE'}
            ,{field:'repLiaisonEmail', width:150, title: 'RepFinProductOrgIssRegist001.repLiaisonEmail', sortField:'REP_LIAISON_EMAIL'}
            ,{field:'repProfitType',visible: false}
            ,{field:'repProfitTypeStr',width:120,title:'RepFinProductOrgIssRegist001.repProfitType', sortField:'REP_PROFIT_TYPE'}
            ,{field:'repTermStr',sortField:'repTerm',width:120,title:'RepFinProductOrgIssRegist001.repTerm', sortField:'REP_TERM'}
            ,{field:'repInvestorTypeStr',sortField:'repInvestorType',width:120,title:'RepFinProductOrgIssRegist001.repInvestorType', sortField:'REP_INVESTOR_TYPE'}
            ,{field:'repInvestAreaStr',sortField:'repInvestArea',width:180, title:'RepFinProductOrgIssRegist001.repInvestArea', sortField:'REP_INVEST_AREA'}
            ,{field:'repInvestCountry', width:150, title: 'RepFinProductOrgIssRegist001.repInvestCountry', sortField:'REP_INVEST_COUNTRY'}
            ,{field:'repFinanSerModelStr',sortField:'repFinanSerModel',width:120,title:'RepFinProductOrgIssRegist001.repFinanSerModel', sortField:'REP_FINAN_SER_MODEL'}
            ,{field:'repOperateModeStr',sortField:'repOperateMode',width:120,title:'RepFinProductOrgIssRegist001.repOperateMode', sortField:'REP_OPERATE_MODE'}
            ,{field:'repBookModeStr',sortField:'repBookMode',width:120, title:'RepFinProductOrgIssRegist001.repBookMode', sortField:'REP_BOOK_MODE'}
            ,{field:'repCmmodeStr',sortField:'repCmmode',width:120, title:'RepFinProductOrgIssRegist001.repCmmode', sortField:'REP_CMMODE'}
            ,{field:'repPmmodeStr',sortField:'repPmmode',width:120, title:'RepFinProductOrgIssRegist001.repPmmode', sortField:'REP_PMMODE'}
            ,{field:'repMname', width:150, title: 'RepFinProductOrgIssRegist001.repMname', sortField:'REP_MNAME'}
            ,{field:'repPricedModeStr',sortField:'repPricedMode',width:150,title:'RepFinProductOrgIssRegist001.repPricedMode', sortField:'REP_PRICED_MODE'}
            ,{field:'repInvAssetTypeStr',sortField:'repInvAssetType',width:120,title:'RepFinProductOrgIssRegist001.repInvAssetType', sortField:'REP_INV_ASSET_TYPE'}
            ,{field:'repConPrdTraget',width:150, title:'RepFinProductOrgIssRegist001.repConPrdTraget', sortField:'REP_CON_PRD_TRAGET'}
            ,{field:'repIsYieldStr',sortField:'repIsYield',width:150, title:'RepFinProductOrgIssRegist001.repIsYield', sortField:'REP_IS_YIELD'}
            ,{field:'repHighYield',width:150, title:'RepFinProductOrgIssRegist001.repHighYield', sortField:'REP_HIGH_YIELD'}
            ,{field:'repLowYield', width:150, title: 'RepFinProductOrgIssRegist001.repLowYield', sortField:'REP_LOW_YIELD'}
            ,{field:'repIsYieldBasisStr',sortField:'repIsYieldBasis',width:150,title:'RepFinProductOrgIssRegist001.repIsYieldBasis', sortField:'REP_IS_YIELD_BASIS'}
            ,{field:'repSaleArea',width:150,title:'RepFinProductOrgIssRegist001.repSaleArea', sortField:'REP_SALE_AREA'}
            ,{field:'repRaiseCcyStr',sortField:'repRaiseCcy',width:150, title:'RepFinProductOrgIssRegist001.repRaiseCcy', sortField:'REP_RAISE_CCY'}
            ,{field:'repCashPrinCcyStr',sortField:'repCashPrinCcy', width:150, title: 'RepFinProductOrgIssRegist001.repCashPrinCcy', sortField:'REP_CASH_PRIN_CCY'}
            ,{field:'repCashProfCcyStr',sortField:'repCashProfCcy',width:150,title:'RepFinProductOrgIssRegist001.repCashProfCcy', sortField:'REP_CASH_PROF_CCY'}
            ,{field:'repSaleFeeRate',width:150,title:'RepFinProductOrgIssRegist001.repSaleFeeRate', sortField:'REP_SALE_FEE_RATE'}
            ,{field:'repTruFeeRate',width:150, title:'RepFinProductOrgIssRegist001.repTruFeeRate', sortField:'REP_TRU_FEE_RATE'}
            ,{field:'repInDepName',width:150, title:'RepFinProductOrgIssRegist001.repInDepName', sortField:'REP_IN_DEP_NAME'}
            ,{field:'repInDepCode',width:150, title:'RepFinProductOrgIssRegist001.repInDepCode', sortField:'REP_IN_DEP_CODE'}
            ,{field:'repOutDepCtry',width:150,  title: 'RepFinProductOrgIssRegist001.repOutDepCtry', sortField:'REP_OUT_DEP_CTRY'}
            ,{field:'repOutDepName',width:150,title:'RepFinProductOrgIssRegist001.repOutDepName', sortField:'REP_OUT_DEP_NAME'}
            ,{field:'repRiskType',visible: false}
            ,{field:'repRiskTypeStr',width:150,title:'RepFinProductOrgIssRegist001.repRiskType', sortField:'REP_OUT_DEP_NAME'}
            ,{field:'repRiskLevelStr',sortField:'repRiskLevel',width:150, title:'RepFinProductOrgIssRegist001.repRiskLevel', sortField:'REP_RISK_LEVEL'}
            ,{field:'repTermRightFlagStr', sortField:'repTermRightFlag',width:150,title:'RepFinProductOrgIssRegist001.repTermRightFlag', sortField:'REP_TERM_RIGHT_FLAG'}
            ,{field:'repCusRedeemFlagStr',sortField:'repCusRedeemFlag',width:150, title:'RepFinProductOrgIssRegist001.repCusRedeemFlag', sortField:'REP_CUS_REDEEM_FLAG'}
            ,{field:'repFinprodBrand', width:150, title: 'RepFinProductOrgIssRegist001.repFinprodBrand', sortField:'REP_FINPROD_BRAND'}
            ,{field:'repFinprodStage',width:150,title:'RepFinProductOrgIssRegist001.repFinprodStage', sortField:'REP_FINPROD_STAGE'}
            ,{field:'repContModeStr',sortField:'repContMode',width:150,title:'RepFinProductOrgIssRegist001.repContMode', sortField:'REP_CONT_MODE'}
            ,{field:'repCoopName',width:150, title:'RepFinProductOrgIssRegist001.repCoopName', sortField:'REP_COOP_NAME'}
            ,{field:'repInvestPrinDateStr',sortField:'repInvestPrinDate',width:150,  title: 'RepFinProductOrgIssRegist001.repInvestPrinDate', sortField:'REP_INVEST_PRIN_DATE'}
            ,{field:'repInvestProfDateStr',sortField:'repInvestProfDate',width:150,title:'RepFinProductOrgIssRegist001.repInvestProfDate', sortField:'REP_INVEST_PROF_DATE'}
            ,{field:'repCreditFlagStr',sortField:'repCreditFlag',width:150,title:'RepFinProductOrgIssRegist001.repCreditFlag', sortField:'REP_CREDIT_FLAG'}
            ,{field:'repCreditTypeStr',sortField:'repCreditType',width:150, title:'RepFinProductOrgIssRegist001.repCreditType', sortField:'REP_CREDIT_TYPE'}
            ,{field:'repCreditModeStr',sortField:'repCreditMode',width:150, title:'RepFinProductOrgIssRegist001.repCreditMode', sortField:'REP_CREDIT_MODE'}
            ,{field:'repRaisePstrdate',width:150, title:'RepFinProductOrgIssRegist001.repRaisePstrdate',type:'date', sortField:'REP_RAISE_PSTRDATE'}
            ,{field:'repRaisePenddate', width:150, title: 'RepFinProductOrgIssRegist001.repRaisePenddate',type:'date', sortField:'REP_RAISE_PENDDATE'}
            ,{field:'repPrdVdate',width:150,title:'RepFinProductOrgIssRegist001.vdate',type:'date', sortField:'REP_PRD_VDATE'}
            ,{field:'repPrdMdate',width:150,title:'RepFinProductOrgIssRegist001.mdate',type:'date', sortField:'REP_PRD_MDATE'}
            ,{field:'isItStructured',visible: false}
            ,{field:'isItStructuredStr',width:150,title:'RepFinProductOrgIssRegist001.isItStructured', sortField:'isItStructured'}
            ,{field:'hierarchicalCategory',width:150, title:'RepFinProductOrgIssRegist001.hierarchicalCategory', sortField:'hierarchicalCategory'}
            ,{field:'repYieldComment',width:150, title:'RepFinProductOrgIssRegist001.yieldSpread', sortable:false}
            ,{field:'repOpenModeStr',sortField:'repOpenMode',width:150, title:'RepFinProductOrgIssRegist001.repOpenMode', sortField:'REP_OPEN_MODE'}
            ,{field:'repOpenCircleStr',sortField:'repOpenCircle',width:150, title:'RepFinProductOrgIssRegist001.repOpenCircle', sortField:'REP_OPEN_CIRCLE'}
            ,{field:'repOpenCircleDays',width:150, title:'RepFinProductOrgIssRegist001.repOpenCircleDays', sortField:'REP_OPEN_CIRCLE_DAYS'}
            ,{field:'repRuleComment',width:150, title:'RepFinProductOrgIssRegist001.repRuleComment', sortable:false}
            ,{field:'repFirstOpenDay',width:150, title:'RepFinProductOrgIssRegist001.repFirstOpenDay',type:'date', sortField:'REP_FIRST_OPEN_DAY'}
            ,{field:'repHolidayOnFlag',width:150, title:'RepFinProductOrgIssRegist001.repHolidayOnFlag', sortField:'REP_HOLIDAY_ON_FLAG'}
            ,{field:'repAvgOpenCount',width:150, title:'RepFinProductOrgIssRegist001.repAvgOpenCount', sortField:'REP_AVG_OPEN_COUNT'}
            ,{field:'repOpenBussStr',sortField:'repOpenBuss',width:150, title:'RepFinProductOrgIssRegist001.repOpenBuss', sortField:'REP_OPEN_BUSS'}
            ,{field:'repOpenBussRemark',width:150, title:'RepFinProductOrgIssRegist001.repOpenBussRemark', sortable:false}
            ,{field:'repRemark', width:150, title: 'RepFinProductOrgIssRegist001.repRemark', sortable:false}
        ]
    });
    
    /**
     * 绑定编辑事件
     */
//    table.bind('click', '.edit', function(data, index) {
//        data.pageModel = PAGE_MODEL_EDIT;
//            Joyin.dialog(JoyinUtil.getLabel("PortfolioManageInfo002.title"),
//                    basePath + "plugins/portfoliomanageInfo/PortfolioManageInfo002/init",
//                    "80%", "80%", {pageModel:PAGE_MODEL_EDIT,
//                                    fundCode:data.fundCode,
//                                    fundType:data.fundType,
//                                    valuationDay:data.valuationDay,
//                                    thousandsOfIncome:data.thousandsOfIncome,
//                                    fundManager:data.fundManager
//                                    
//                                    });
//    });
    
    /**
     * 绑定删除事件
     */
//    table.bind('click', '.delt', function(data, index) {
//        var object = {selectedItem: [data], operateType:"delete"};
//            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
//                WorkFlow.doDeleteList(basePath + 'plugins/portfoliomanageInfo/PortfolioManageInfo002/delete', object, "PortfolioManageInfo001_btn_delete", "PortfolioManageInfo002");
//        });
//    });

    /**
     * 导出选中的表格数据
     */
    var getParamObj = function(){
    	if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
        }
    	var repFinprodId = new Array();
    	if (selArr || selArr.length > 0) {
    		$.each(selArr, function(i, v) {  
	    	    if(v.repFinprodId != null || v.repFinprodId != ''){
	    	    	repFinprodId[i] = v.repFinprodId;
	    	    }
    		});
    	}
    	return {
    		repFinprodId:repFinprodId
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
    	$('#RepFinProductOrgIssRegist001_btn_filter').attr("style","display:none;");
    }
    /**
     * 画面加载完成
     */
    $(function() {
    	init();
        // 绑定按钮事件
        $('#RepFinProductOrgIssRegist001_btn_search').click(function() {
            table.load();
        });
       
        $('#RepFinProductOrgIssRegist001_btn_export').click(function() {
            exportExcel();
        });
    });
}());