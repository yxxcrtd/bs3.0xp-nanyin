$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            group: $('#group').val(),
            product: $('#product').val(),
            assetType: $('#assetType').val(),
            assetCode: $('#assetCode').val(),
            showLevel: $('#showLevel').val(),
            owner: $('#showLevel').val(),
            date: $('#date').val()
        }
    };    
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({        
        data:[             
              {'product':'产品一号', 'assetType':'债券', 'firstClass':'债券一级分类','secondClass':'债券二级分类',
               'assetCode':'ZC001', 'assetName':'资产一号', 'currentNum':'10000000','deadLine':'2018-07-30',
               'startDate':'2018-05-30','endDate':'2018-08-01', 'basis':'A/360', 'yield':'50%','yieldToMaturity':
               '50%','amortize':'5000000', 'market':'6000000', 'publisher':'发行者一号','currency':'人民币','owner':'团队一号'},
              {'product':'产品二号', 'assetType':'存放同业', 'firstClass':'存放同业','secondClass':'存放同业',
               'assetCode':'ZC002', 'assetName':'资产二号', 'currentNum':'20000000','deadLine':'2018-07-30',
               'startDate':'2018-05-30','endDate':'2018-08-01', 'basis':'A/360', 'yield':'50%','yieldToMaturity':
               '50%','amortize':'5000000', 'market':'6000000', 'publisher':'发行者二号','currency':'人民币','owner':'团队二号'}
          ],
        cols:[
            {field:'product', width:150, title: 'Balance001.product'}
            ,{field:'assetType', width:150, title: 'Balance001.assetType'}
            ,{field:'firstClass', width:150, title: 'Balance001.firstClass'}
            ,{field:'secondClass', width:150, title: 'Balance001.secondClass'}
            ,{field:'assetCode', width:150, title: 'Balance001.assetCode'}
            ,{field:'assetName', width:150, title: 'Balance001.assetName'}
            ,{field:'currentNum', width:150, title: 'Balance001.currentNum'}
            ,{field:'deadLine', width:150, title: 'Balance001.deadLine'}
            ,{field:'startDate', width:150, title: 'Balance001.startDate'}
            ,{field:'endDate', width:150, title: 'Balance001.endDate'}
            ,{field:'basis', width:150, title: 'Balance001.basis'}
            ,{field:'yield', width:150, title: 'Balance001.yield'}
            ,{field:'yieldToMaturity', width:150, title: 'Balance001.yieldToMaturity'}
            ,{field:'amortize', width:150, title: 'Balance001.amortize'}
            ,{field:'market', width:150, title: 'Balance001.market'}
            ,{field:'publisher', width:150, title: 'Balance001.publisher'}
            ,{field:'currency', width:150, title: 'Balance001.currency'}
            ,{field:'owner', width:150, title: 'Balance001.owner'}
        ]
    });        
    /**
     * 导出事件
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#Balance001_btn_search').click(function() {
            table.load();            
        });
        $('#Balance001_btn_export').click(function() {
            exportExcel();
        });                    
    });
}());