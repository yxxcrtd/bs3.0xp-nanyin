$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            
        }
    };

    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable(
            {
                /*
                 * ajax: { url: 'findPage', type: 'POST', data: function() {
                 * return getSearchParams(); } },
                 */

                checkbox:false,
                data : [

                {
                    'serialNumber' : '001','financingCustomer' : '张三','remainFinancingPeriod' : '3',
                    'investmentProductIncomeOfYear' : '5%','investmentModel' : '信托产品','cooperativeInstitution' : '李四',
                     'CorrespondingProductCode':'005','portfolioRatio':'1%'
                }
                ],
                cols : [ 
                         {field : 'serialNumber', width : 120, title : 'NonStandardCreditAssetDetails001.serialNumber'}
                       , {field : 'financingCustomer', width : 150, title : 'NonStandardCreditAssetDetails001.financingCustomer',sortable:false}
                       , {field : 'remainFinancingPeriod', width : 150, title : 'NonStandardCreditAssetDetails001.remainFinancingPeriod',sortable:false}
                       , {field : 'investmentProductIncomeOfYear', width : 150, title : 'NonStandardCreditAssetDetails001.investmentProductIncomeOfYear',sortable:false}
                       , {field : 'investmentModel', width : 150, title : 'NonStandardCreditAssetDetails001.investmentModel',sortable:false}
                       , {field : 'cooperativeInstitution', width : 150, title : 'NonStandardCreditAssetDetails001.cooperativeInstitution',sortable:false}
                       , {field : 'CorrespondingProductCode', width : 150, title : 'NonStandardCreditAssetDetails001.CorrespondingProductCode',sortable:false}
                       , {field : 'portfolioRatio', width : 170, title : 'NonStandardCreditAssetDetails001.portfolioRatio',sortable:false}
                       
                ]
            });

    /**
     * 绑定上传事件
     */
    table.bind('click','.upload',function(){
        Joyin.confirm("请确认是否要导入数据？", function() {
            Joyin.uploadFile(function(fileName) {
                Joyin.ajax('importFile', {fileName: fileName});
            },function() {
                table.load();
            });
        });
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
        $('#NonStandardCreditAssetDetails001_btn_search').click(function() {
            table.load();
            $('#NonStandardCreditAssetDetails001_btn_delete').show();
        });
        $('#NonStandardCreditAssetDetails001_btn_export').click(function() {
            exportExcel();
        });
        
    });
}());