$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	productCode: $('#productCode').val(),
        	productRegistrationCode:$('#productRegistrationCode').val(),
        	issuingWay:$('#issuingWay').val(),      	
        	productType: $('#productType').val(),
        	tradeType:$('#tradeType').val(),
        	dataDate:$('#dataDate').val(),        	     	
        }
    };
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({ 
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/report/debtreport/productrenewalregistrationinfo/ProductRenewalRegistration001/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,
            	productCode:data.productCode,
            	productRegistrationCode:data.productRegistrationCode});
        },
        data:[             
            {'dataDate':'2018-07-31', 'wealthManagementProductCode':'001',
            'distributorCode':'001', 'businessCommencementDate':'2018-07-31',
            'closingDate':'2018-07-31', 'accumulatedPurchaseShares':'0.01',
            'accumulatedRedemptionShare':'0.01', 'balanceOfProducts':'1000',
            'productShare':'10000', 'netValueOfTheInitial':'1000',
            'netvalueOfTheProduct':'120', 'theCumulativeNetValue':'50',
            'theNetValueOfTheCurrency':'0.1', 'translatedYuanInitialNetWorth':'0.01',
            'translatedIntoRMBNetValue':'0.02', 'translatedYuanAccumulatedNetValue':'0.03',
            'realizedRateOfReturn':'0.02', 'theLatestExpectedYield':'0.03',
            'bankRealizedIncome':'0.02', 'currencyAndPurchasePaymentInformation':'0.03',
           'remark':'这是第一条数据'},
           {'dataDate':'2018-07-31', 'wealthManagementProductCode':'001',
               'distributorCode':'001', 'businessCommencementDate':'2018-07-31',
               'closingDate':'2018-07-31', 'accumulatedPurchaseShares':'0.01',
               'accumulatedRedemptionShare':'0.01', 'balanceOfProducts':'1000',
               'productShare':'10000', 'netValueOfTheInitial':'1000',
               'netvalueOfTheProduct':'120', 'theCumulativeNetValue':'50',
               'theNetValueOfTheCurrency':'0.1', 'translatedYuanInitialNetWorth':'0.01',
               'translatedIntoRMBNetValue':'0.02', 'translatedYuanAccumulatedNetValue':'0.03',
               'realizedRateOfReturn':'0.02', 'theLatestExpectedYield':'0.03',
               'bankRealizedIncome':'0.02', 'currencyAndPurchasePaymentInformation':'0.03',
              'remark':'这是第条数据'},
              {'dataDate':'2018-07-31', 'wealthManagementProductCode':'001',
                  'distributorCode':'001', 'businessCommencementDate':'2018-07-31',
                  'closingDate':'2018-07-31', 'accumulatedPurchaseShares':'0.01',
                  'accumulatedRedemptionShare':'0.01', 'balanceOfProducts':'1000',
                  'productShare':'10000', 'netValueOfTheInitial':'1000',
                  'netvalueOfTheProduct':'120', 'theCumulativeNetValue':'50',
                  'theNetValueOfTheCurrency':'0.1', 'translatedYuanInitialNetWorth':'0.01',
                  'translatedIntoRMBNetValue':'0.02', 'translatedYuanAccumulatedNetValue':'0.03',
                  'realizedRateOfReturn':'0.02', 'theLatestExpectedYield':'0.03',
                  'bankRealizedIncome':'0.02', 'currencyAndPurchasePaymentInformation':'0.03',
                 'remark':'这是第三条数据'},
                 {'dataDate':'2018-07-31', 'wealthManagementProductCode':'001',
                     'distributorCode':'001', 'businessCommencementDate':'2018-07-31',
                     'closingDate':'2018-07-31', 'accumulatedPurchaseShares':'0.01',
                     'accumulatedRedemptionShare':'0.01', 'balanceOfProducts':'1000',
                     'productShare':'10000', 'netValueOfTheInitial':'1000',
                     'netvalueOfTheProduct':'120', 'theCumulativeNetValue':'50',
                     'theNetValueOfTheCurrency':'0.1', 'translatedYuanInitialNetWorth':'0.01',
                     'translatedIntoRMBNetValue':'0.02', 'translatedYuanAccumulatedNetValue':'0.03',
                     'realizedRateOfReturn':'0.02', 'theLatestExpectedYield':'0.03',
                     'bankRealizedIncome':'0.02', 'currencyAndPurchasePaymentInformation':'0.03',
                    'remark':'这是第四条数据'}
            
        ],
        cols:[
            {field:'dataDate', width:150,title: 'ProductRenewalRegistration001.dataDate'}
            ,{field:'wealthManagementProductCode', width:100, title: 'ProductRenewalRegistration001.wealthManagementProductCode'}         
            ,{field:'distributorCode', width:100, title: 'ProductRenewalRegistration001.distributorCode'}
            ,{field:'businessCommencementDate', width:150, title: 'ProductRenewalRegistration001.businessCommencementDate'}          
            ,{field:'closingDate', width:150, title: 'ProductRenewalRegistration001.closingDate'}            
            ,{field:'accumulatedPurchaseShares', width:100, title: 'ProductRenewalRegistration001.accumulatedPurchaseShares'}
            ,{field:'accumulatedRedemptionShare', width:100, title: 'ProductRenewalRegistration001.accumulatedRedemptionShare'}           
            ,{field:'balanceOfProducts', width:100, title: 'ProductRenewalRegistration001.balanceOfProducts'}          
            ,{field:'productShare', width:100, title: 'ProductRenewalRegistration001.productShare'}          
            ,{field:'netValueOfTheInitial', width:100, title: 'ProductRenewalRegistration001.netValueOfTheInitial'}         
            ,{field:'netvalueOfTheProduct', width:100, title: 'ProductRenewalRegistration001.netvalueOfTheProduct'}          
            ,{field:'theCumulativeNetValue', width:100, title: 'ProductRenewalRegistration001.theCumulativeNetValue'}           
            ,{field:'theNetValueOfTheCurrency', width:100, title: 'ProductRenewalRegistration001.theNetValueOfTheCurrency'}          
            ,{field:'translatedYuanInitialNetWorth', width:100, title: 'ProductRenewalRegistration001.translatedYuanInitialNetWorth'}           
            ,{field:'translatedIntoRMBNetValue', width:100, title: 'ProductRenewalRegistration001.translatedIntoRMBNetValue'}           
            ,{field:'translatedYuanAccumulatedNetValue', width:100, title: 'ProductRenewalRegistration001.translatedYuanAccumulatedNetValue'}           
            ,{field:'realizedRateOfReturn', width:100, title: 'ProductRenewalRegistration001.realizedRateOfReturn'}
            ,{field:'theLatestExpectedYield', width:100, title: 'ProductRenewalRegistration001.theLatestExpectedYield'}
            ,{field:'bankRealizedIncome', width:100, title: 'ProductRenewalRegistration001.bankRealizedIncome'}
            ,{field:'currencyAndPurchasePaymentInformation', width:100, title: 'ProductRenewalRegistration001.currencyAndPurchasePaymentInformation'}
            ,{field:'remark', width:100, title: 'ProductRenewalRegistration001.remark'}          
        ]
    });
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ProductRenewalRegistration001_btn_search').click(function() {
            table.load();            
        });
        $('#ProductRenewalRegistration001_btn_export').click(function() {
            exportExcel();
        });
    });
}());