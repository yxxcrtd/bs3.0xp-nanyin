$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	queryDate: $('#queryDate').val(),
        	productLine:$('#productLine').val(),
        	productCode:$('#productCode').val(),      	
        	productAbbreviation: $('#productAbbreviation').val(),
        	combination:$('#combination').val(),
        	processingMode:$('#processingMode').val(),        	     	
        	isItLayered: $('#isItLayered').val(),
        	salesDepartments:$('#salesDepartments').val(),
        	rangeOfCommencementDate:$('#rangeOfCommencementDate').val(),
        	maturityRange:$('#maturityRange').val()
        }
    };
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({ 
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/report/productbalancestatementinfo/ProductBalanceStatement001/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,
            	productCode:data.productCode,
            	productAbbreviation:data.productAbbreviation});
        },
        data:[             
            {'productCode':'001', 'productAbbreviation':'LC',
            'productName':'平安', 'productLine':'理财',
            'processingMode':'现金', 'salesDepartments':'资管',
            'productModel':'A', 'subscribeToCurrency':'美金',
            'subscriptionAmount':'10000', 'balance':'1000',
            'rangeOfCommencementDate':'2018-07-30', 'dueDate':'2018-07-30',
            'ExpectedReturn':'0.1', 'hostingRate':'0.01',
            'sellingRate':'0.02', 'manageRate':'0.03',
            'otherRate':'0.01', 'issuingWay':'单一'},  
            {'productCode':'001', 'productAbbreviation':'LC',
             'productName':'平安', 'productLine':'理财',
              'processingMode':'现金', 'salesDepartments':'资管',
              'productModel':'A', 'subscribeToCurrency':'美金',
              'subscriptionAmount':'10000', 'balance':'1000',
               'rangeOfCommencementDate':'2018-07-30', 'dueDate':'2018-07-30',
               'ExpectedReturn':'0.1', 'hostingRate':'0.01',
               'sellingRate':'0.02', 'manageRate':'0.03',
               'otherRate':'0.01', 'issuingWay':'单一'},
              {'productCode':'001', 'productAbbreviation':'LC',
                  'productName':'平安', 'productLine':'理财',
                  'processingMode':'现金', 'salesDepartments':'资管',
                   'productModel':'A', 'subscribeToCurrency':'美金',
                  'subscriptionAmount':'10000', 'balance':'1000',
                   'rangeOfCommencementDate':'2018-07-30', 'dueDate':'2018-07-30',
                    'ExpectedReturn':'0.1', 'hostingRate':'0.01',
                  'sellingRate':'0.02', 'manageRate':'0.03',
                    'otherRate':'0.01', 'issuingWay':'单一'},
                    {'productCode':'001', 'productAbbreviation':'LC',
                        'productName':'平安', 'productLine':'理财',
                        'processingMode':'现金', 'salesDepartments':'资管',
                        'productModel':'A', 'subscribeToCurrency':'美金',
                        'subscriptionAmount':'10000', 'balance':'1000',
                        'rangeOfCommencementDate':'2018-07-30', 'dueDate':'2018-07-30',
                        'ExpectedReturn':'0.1', 'hostingRate':'0.01',
                        'sellingRate':'0.02', 'manageRate':'0.03',
                        'otherRate':'0.01', 'issuingWay':'单一'}
        ],
        cols:[
            {field:'productCode', width:100,title: 'ProductBalanceStatement001.productCode'}
            ,{field:'productAbbreviation', width:150, title: 'ProductBalanceStatement001.productAbbreviation',render: function (data, type, row, meta) {
                return "<a  class='iconfont icon-detail pro-link' style='cursor: pointer;'>这是一个连接</a>";
            }}
            ,{field:'productName', width:100, title: 'ProductBalanceStatement001.productName'}
            
            ,{field:'productLine', width:100, title: 'ProductBalanceStatement001.productLine'}
            ,{field:'processingMode', width:100, title: 'ProductBalanceStatement001.processingMode'}
            ,{field:'salesDepartments', width:100, title: 'ProductBalanceStatement001.salesDepartments'}
            ,{field:'productModel', width:100, title: 'ProductBalanceStatement001.productModel'}
            ,{field:'subscribeToCurrency', width:100, title: 'ProductBalanceStatement001.subscribeToCurrency'}
            ,{field:'subscriptionAmount', width:100, title: 'ProductBalanceStatement001.subscriptionAmount'}
            ,{field:'balance', width:100, title: 'ProductBalanceStatement001.balance'}
            ,{field:'rangeOfCommencementDate', width:150, title: 'ProductBalanceStatement001.rangeOfCommencementDate'}
            ,{field:'dueDate', width:150, title: 'ProductBalanceStatement001.dueDate'}
            ,{field:'ExpectedReturn', width:100, title: 'ProductBalanceStatement001.ExpectedReturn'}
            ,{field:'hostingRate', width:100, title: 'ProductBalanceStatement001.hostingRate'}
            ,{field:'sellingRate', width:100, title: 'ProductBalanceStatement001.sellingRate'}
            ,{field:'manageRate', width:100, title: 'ProductBalanceStatement001.manageRate'}
            ,{field:'otherRate', width:100, title: 'ProductBalanceStatement001.otherRate'}
            ,{field:'issuingWay', width:100, title: 'ProductBalanceStatement001.issuingWay'}
           
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
        $('#ProductBalanceStatement001_btn_search').click(function() {
            table.load();            
        });
//        debtSubmitInfo.table.bind('click','.icon-detail', function(row) {
//            var param={};
//            param.reportNo=row[0].reportNo;
//            Joyin.dialog("反馈信息详情页",detailUrl,'900px','90%',param);
//        });
        $('#ProductBalanceStatement001_btn_export').click(function() {
            exportExcel();
        });
    });
}());