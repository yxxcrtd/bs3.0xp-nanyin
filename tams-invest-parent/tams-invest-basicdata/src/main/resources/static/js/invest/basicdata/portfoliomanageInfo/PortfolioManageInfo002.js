$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            fundCode: $('#portfolioName').val(),
            fundManager: $('#createUser').val(),
            valuationDay : $('#createDate').val(),
            valuationSource:$('#status').val(),
        };
    };

    /**
     * 定义数据表格
     */
    
    var prendingTable=$("#prendingTable").joyintable({
        data:[
              {
            	  "status":"a",
                  "productCode":"01",
                  "productAbbreviation":"system",
                  "linkedGroup":"2012-11-12"
              },
              {
            	  "status":"b",
                  "productCode":"2",
                  "productAbbreviation":"system",
                  "linkedGroup":"2013-11-21"
              },
              {
            	  "status":"c",
                  "productCode":"3",
                  "productAbbreviation":"system",
                  "linkedGroup":"2014-11-11"
              }
              ],
              searchOnInit:true,
              page:false,
        /*
         * 定义表头
         */
        cols:[
            {field:'status', title: 'PortfolioManageInfo001.status'}
            ,{field:'productCode',  title: 'PortfolioManageInfo002.productCode'}
            ,{field:'productAbbreviation',title:'PortfolioManageInfo002.productAbbreviation'}
            ,{field:'linkedGroup',title:'PortfolioManageInfo002.linkedGroup'}
        ]
    });
    
    var selectedTable=$("#selectedTable").joyintable({
        page:false,
        /*
         * 定义表头
         */
        cols:[
            {field:'status', title: 'PortfolioManageInfo001.status'}
            ,{field:'productCode',  title: 'PortfolioManageInfo002.productCode'}
            ,{field:'productAbbreviation',title:'PortfolioManageInfo002.productAbbreviation'}
            ,{field:'linkedGroup',title:'PortfolioManageInfo002.linkedGroup'}
        ]
    });
    
    /**
     * 进行数据的选择
     */
    
    function select(){
    	var seleItem = prendingTable.getAllCheckedRows();
    	if(seleItem != null && seleItem.length > 0){
    		for(var item=seleItem.length;item>=0;item--){//倒序删除，正序会存在问题
    			prendingTable.removeRow(seleItem[item]);//每次添加时进行删除，可以抵消增加重复行的问题
    		}
    		selectedTable.addRow(seleItem);
    	}else{
    		Joyin.alert(JoyinUtil.getLabel("PortfolioManageInfo002.unSelect"),2);
    	}
    }
    
    /**
     * 数据的取消选择
     */
    
    function dele(){
    	var rows = selectedTable.getAllCheckedRows();
    	if( rows != null &&  rows.length > 0 ){//判断是否选中元素
    		for(var row=rows.length;row>=0;row--){//倒序删除，正序会存在问题
    			selectedTable.removeRow(rows[row]);
    			prendingTable.addRow(rows[row]);
    		}
    	}else{
    		Joyin.alert(JoyinUtil.getLabel("CustomerNatureInfo001.notNull"),2);
    	}
    	
    }
    
    /**
     * 进行保存和提交操作
     * @param operateType
     * @returns
     */
    function saveOrSubmit(operateType) {
    	if(operateType === 'save'){
    		Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.save")), function() {
    		WorkFlow.doSaveList(basePath + 'invest/basicdata/portfoliomanageInfo/PortfolioManageInfo002/save', object, "CustomerNatureInfo001_btn_save");
    		});		
    	}else{
    		Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.submit")), function() {
    		WorkFlow.doSubmitList(basePath + 'invest/basicdata/portfoliomanageInfo/PortfolioManageInfo002/submit', object, "CustomerNatureInfo001_btn_submit");
    		});
    	}
    };

    /**
     * 画面加载完成
     */
    $(function() {
    	selectedTable.load();
        // 绑定按钮事件
        /*$('#PortfolioManageInfo001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("PortfolioManageInfo002.title"),
                        basePath + "invest/basicdata/portfoliomanageInfo/PortfolioManageInfo002/init",
                        "80%", "70%", {pageModel:PAGE_MODEL_NEW});
        });*/
        $("#PortfolioManageInfo002_btn_save").click(function(){
            saveOrSubmit('save');
        });
        
        $("#PortfolioManageInfo002_btn_submit").click(function(){
            saveOrSubmit('submit');
        });
        
        $("#selectBtn").click(function(){
        	select();
        });
        	
        
        $("#cancelBtn").click(function(){
        	dele();
        });
        
    });
}());