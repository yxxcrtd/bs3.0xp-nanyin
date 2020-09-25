$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	performBenchmarkReferenceName: $('#performBenchmarkReferenceName').val(),
        	performBenchmarkReferenceType : $('#performBenchmarkReferenceType').val()
        };
    };
    
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        /*ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },*/
        /*dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "system/department/SysDepartment002/init",
                    "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,
                                    deptCode:data.deptCode,
                                    status:data.status});
        },*/
        
        checkbox: false,
        page:false,
        isMulti:true,

        data:[
              {
	        	  'number':'23123',
	        	  'productName':'哈哈哈',
	        	  'productCode':'成分详情',
	        	  'investType':'21321',
	        	  'cash':'21312312',
	        	  'moneyMarketTool':'wqweqwe',
	        	  'bondAssets':'21312312'
	        	  
              },
              {
            	  'number':'3434234',
            	  'productName':'中债指数',
            	  'productCode':'成分详情',
            	  'investType':'12321',
            	  'cash':'21312312',
	        	  'moneyMarketTool':'wqweqwe',
	        	  'bondAssets':'21312312'
            	  
                  }
              ,
              {
            	  'number':'666564',
            	  'productName':'一年期国债收益率',
            	  'productCode':'成分详情',
            	  'investType':'213',
            	  'cash':'21312312',
	        	  'moneyMarketTool':'wqweqwe',
	        	  'bondAssets':'21312312'
                  }
              ],
        cols:[ 
				[
				 { title: 'PortfolioReport001.number', field: 'number', sortable: false, rowspan: 2, align:'center'}
				 , {title: 'PortfolioReport001.productName', field: 'productName', rowspan: 2, sortable: false,align:'center'}
				 , {title: 'PortfolioReport001.productCode', field: 'productCode', sortable: false,rowspan: 2,align:'center'}
				 , {title: 'PortfolioReport001.investType', field: 'investType', sortable: false, colspan: 3, align:'center'}
				],[
				 {title: 'PortfolioReport001.cash', field: 'cash', sortable: false,align:'center'}
				 , {title: 'PortfolioReport001.moneyMarketTool', field: 'moneyMarketTool', sortable: false,align:'center'}
				 , {title: 'PortfolioReport001.bondAssets', field: 'bondAssets', sortable: false,align:'center'} 
				] 
            ]
    });
    
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        /*Joyin.ajaxJson(basePath + 'system/department/SysDepartment002/check', data, function(result) {});*/
            Joyin.dialog(JoyinUtil.getLabel("PerformanceBenchmark002.edit"),
                    basePath + "plugins/performancebenchmark/PerformanceBenchmark002/init",
                    "95%", "80%", {pageModel:PAGE_MODEL_EDIT,
                                    deptCode:data.deptCode,
                                    status:data.status});
        
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        /*Joyin.ajaxJson(basePath + 'system/department/SysDepartment001/check', object, function(result) {});*/
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'system/department/SysDepartment001/delete', object, "PortfolioReport001_btn_delete", "ExchangeRateInformation002");
            });
        
    });
    
    /**
     * 成分情况链接
     */
    table.bind('click','.icon-detail', function(row) {
        var param={};
        //param.reportNo=row[0].reportNo;
        Joyin.dialog(JoyinUtil.getLabel("PerformanceBenchmark003"),
                basePath + "plugins/performancebenchmark/PerformanceBenchmark003/init",'100%','100%',param);
    });
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    
    
    var table2=$("#dataTable2").joyintable({
        /*ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },*/
       /* dblclick:function(table2, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "system/department/SysDepartment002/init",
                    "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,
                                    deptCode:data.deptCode,
                                    status:data.status});
        },*/
        
        checkbox: false,
        page:false,
        isMulti:true,

        data:[
              {
	        	  'number':'23123',
	        	  'productName':'哈哈哈',
	        	  'productCode':'成分详情',
	        	  'investType':'21321',
	        	  'bondAssets':'21312312',
	        	  'moneyMarketTool':'wqweqwe',
	        	  'otheAssets':'21312312'
	        	  
              },
              {
            	  'number':'3434234',
            	  'productName':'中债指数',
            	  'productCode':'成分详情',
            	  'investType':'12321',
            	  'bondAssets':'21312312',
	        	  'moneyMarketTool':'wqweqwe',
	        	  'otheAssets':'21312312'
            	  
                  }
              ,
              {
            	  'number':'666564',
            	  'productName':'一年期国债收益率',
            	  'productCode':'成分详情',
            	  'investType':'213',
            	  'bondAssets':'21312312',
	        	  'moneyMarketTool':'wqweqwe',
	        	  'otheAssets':'21312312'
                  }
              ],
              
        cols:[ 
				[
				 { title: 'PortfolioReport001.number', field: 'number', sortable: false, rowspan: 2, align:'center'}
				 , {title: 'PortfolioReport001.productName', field: 'productName', rowspan: 2, sortable: false,align:'center'}
				 , {title: 'PortfolioReport001.productCode', field: 'productCode', sortable: false,rowspan: 2,align:'center'}
				 , {title: 'PortfolioReport001.investType', field: 'investType', sortable: false, colspan: 3, align:'center'}
				],[
				 {title: 'PortfolioReport001.moneyMarketTool', field: 'moneyMarketTool', sortable: false,align:'center'}
				 , {title: 'PortfolioReport001.bondAssets', field: 'bondAssets', sortable: false,align:'center'} 
				 , {title: 'PortfolioReport001.otheAssets', field: 'otheAssets', sortable: false,align:'center'}
				] 
            ]
    });
    
    
    var table3=$("#dataTable3").joyintable({
        /*ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },*/
        /*dblclick:function(table3, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "system/department/SysDepartment002/init",
                    "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,
                                    deptCode:data.deptCode,
                                    status:data.status});
        },*/
        
        checkbox: false,
        page:false,
        isMulti:true,
        data:[{
      	  'number':'23123',
      	  'productName':'哈哈哈',
      	  'productCode':'成分详情',
      	  'investType':'21321',
      	  'fixedAssets':'21312312',
      	  'equityAssets':'wqweqwe',
      	  'assetsPortfoli':'21312312'
      	  
        },
        {
      	  'number':'3434234',
      	  'productName':'中债指数',
      	  'productCode':'成分详情',
      	  'investType':'12321',
      	  'fixedAssets':'21312312',
    	  'equityAssets':'wqweqwe',
    	  'assetsPortfoli':'21312312'
      	  
            }
        ,
        {
      	  'number':'666564',
      	  'productName':'一年期国债收益率',
      	  'productCode':'成分详情',
      	  'investType':'213',
      	'fixedAssets':'21312312',
    	  'equityAssets':'wqweqwe',
    	  'assetsPortfoli':'21312312'
            }
        ],
        cols:[ 
				[
				 { title: 'PortfolioReport001.number', field: 'number',sortable: false, rowspan: 2, align:'center'}
				 , {title: 'PortfolioReport001.productName', field: 'productName', rowspan: 2, sortable: false,align:'center'}
				 , {title: 'PortfolioReport001.productCode', field: 'productCode', sortable: false,rowspan: 2,align:'center'}
				 , {title: 'PortfolioReport001.investType', field: 'investType', sortable: false, colspan: 3, align:'center'}
				],[
				 {title: 'PortfolioReport001.fixedAssets', field: 'fixedAssets', sortable: false,align:'center'}
				 , {title: 'PortfolioReport001.equityAssets', field: 'equityAssets', sortable: false,align:'center'}
				 , {title: 'PortfolioReport001.assetsPortfoli', field: 'assetsPortfoli', sortable: false,align:'center'} 
				] 
            ]
    });
    
    /**
     * 表格切换
     */
    $(".tabJump").change(function(){
    	var selectedValue=$(this).val();

        if(selectedValue==0){
            $("#keep").show();
             $("#surplus").hide();
             $("#wisdom").hide();
        }
         if(selectedValue==1){

                $("#surplus").show();
                 $("#keep").hide();
                 $("#wisdom").hide();
        }
        if(selectedValue==2){

             $("#wisdom").show();
              $("#keep").hide();
              $("#surplus").hide();
     }
    });
    
    
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#PortfolioReport001_btn_search').click(function() {
            table.load();
            table2.load();
            table3.load();
            $('#PortfolioReport001_btn_delete').show();
        });
        $('#PortfolioReport001_btn_export').click(function() {
            exportExcel();
        });
        $('#PortfolioReport001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("PerformanceBenchmark002.add"),
                        basePath + "plugins/performancebenchmark/PerformanceBenchmark002/init",
                        "80%", "80%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#PortfolioReport001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'system/department/SysDepartment001/check', object, function(result) {});
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'system/department/SysDepartment001/delete', object, "PortfolioReport001_btn_delete", "SysDepartment002");
                });
            
        });
    });
}());