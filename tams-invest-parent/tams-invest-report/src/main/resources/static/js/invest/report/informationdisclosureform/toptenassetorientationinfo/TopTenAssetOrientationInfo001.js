$(function() {
	
	layui.use('laydate', function(){
		  var laydate = layui.laydate;
		  
		  //执行一个laydate实例
		  laydate.render({
		    elem: '#date' //指定元素
		    ,max: 0
		    ,showBottom: false
		    ,value: new Date()
		  });
		  
		});
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            fundCode: $('#date').val(),
            fundManager: $('#product').val(),
        };
    };

    /**
     * 定义数据表格
     */
    
    var table=$("#dataTable").joyintable({
        data:[
              {
            	  "serialNumber":"C",
                  "productCode":"01234",
                  "productName":"system",
                  "assetName":"2018-11-01",
                  "assetTypeName":"fadfe",
                  "assets":"2018-11-01",
                  "investment":"fadfe"
              },
              {
            	  "serialNumber":"C",
                  "productCode":"01234",
                  "productName":"system",
                  "assetName":"2018-11-01",
                  "assetTypeName":"fadfe",
                  "assets":"2018-11-01",
                  "investment":"fadfe"
              },
              {
            	  "serialNumber":"C",
                  "productCode":"01234",
                  "productName":"system",
                  "assetName":"2018-11-01",
                  "assetTypeName":"fadfe",
                  "assets":"2018-11-01",
                  "investment":"fadfe"
              }
              ],
              checkbox:false,
              /**
               * 定义表格双击事件
               */
//        dblclick:function(table, data) {
//            Joyin.dialog(JoyinUtil.getLabel("PortfolioManageInfo002.title"),
//                    basePath + "plugins/portfoliomanageInfo/PortfolioManageInfo002/init",
//                    "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,
//                                    deptCode:data.deptCode,
//                                    status:data.status});
//        },
        /*
         * 定义表头
         */
        cols:[
            {field:'serialNumber', title: 'TopTenAssetOrientationInfo001.serialNumber'}
            ,{field:'productCode',  title: 'TopTenAssetOrientationInfo001.productCode'}
            ,{field:'productName',title:'TopTenAssetOrientationInfo001.productName'}
            ,{field:'assetName',title:'TopTenAssetOrientationInfo001.assetName'}
            ,{field:'assetTypeName', title:'TopTenAssetOrientationInfo001.assetTypeName'}
            ,{field:'assets', title:'TopTenAssetOrientationInfo001.assets'}
            ,{field:'investment', title:'TopTenAssetOrientationInfo001.investment'}
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
    var exportExcel = function() {
    	if(table.getAllRows() && table.getAllRows().length > 0){
    		var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
    	}else{
    		Joyin.alert(JoyinUtil.getLabel("TopTenAssetOrientationInfo001.message"),2);
    	}
        
    };

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#TopTenAssetOrientationInfo001_btn_search').click(function() {
            table.load();
        });
       
        $('#TopTenAssetOrientationInfo001_btn_export').click(function() {
            exportExcel();
        });
    });
}());