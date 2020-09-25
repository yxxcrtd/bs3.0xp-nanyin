$(function() {

    /**
     * 获取表单参数
     */
  
    var getSearchParams = function() {
        return {
        	sharesId : $('#sharesId').val(),
        	dealDateString : $('#dealDate').val()
        };
    };
    
     var table=$("#dataTable").joyintable({
    
    	 ajax: {
             url: 'findPage',
             type: 'POST',
             data: function() {
                 return getSearchParams();
             }
         },
         
        dblclick:function(table, data) {
        	var dealDate = new Date(parseInt(data.dealDate));
            dealDate= dealDate.format('yyyy-MM-dd');
             Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                     basePath + "invest/basicdata/mstshareprice/MstSharePrice002/init",
                     "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,
					            	 sharesId:data.sharesId,
					                 dealDate:dealDate});
         },
        /*
         * 定义表头
         */
        cols:[
             {field:'sharesId',width:200, title: 'MstSharePrice001.sharesId', sortField:'shares_id'}
            ,{field:'finprodAbbr',width:200, title: 'MstSharePrice001.finprodAbbr', sortField:'finprod_abbr'}
            ,{field:'sShares',width:200,type:'number', title: 'MstSharePrice001.sShares' , sortField:'s_shares'}
            ,{field:'cShares',width:200, title: 'MstSharePrice001.cShares',type:'number', sortField:'c_shares'}
            ,{field:'dealDate',width:200, title: 'MstSharePrice001.dealDate',type:'date', sortField:'deal_date'}
            ,{field:'preClosePrice',width:200, title: 'MstSharePrice001.preClosePrice',type:'number', sortField:'pre_close_price'}
            ,{field:'openPrice',width:200, title: 'MstSharePrice001.openPrice',type:'number', sortField:'open_price'}
            ,{field:'highPrice',width:200, title: 'MstSharePrice001.highPrice',type:'number', sortField:'high_price'}
            ,{field:'lowPrice',width:200, title: 'MstSharePrice001.lowPrice',type:'number', sortField:'low_price'}
            ,{field:'closePrice',width:200, title: 'MstSharePrice001.closePrice',type:'number', sortField:'close_price'}
            ,{field:'change',width:200, title: 'MstSharePrice001.change',type:'number', sortField:'change'}
            ,{field:'changePercent',width:200, title: 'MstSharePrice001.changePercent',type:'number', sortField:'change_percent'}
            ,{field:'dealValume',width:200, title: 'MstSharePrice001.dealValume',type:'number', sortField:'deal_valume'}
            ,{field:'dealAmount',width:200, title: 'MstSharePrice001.dealAmount',type:'number', sortField:'deal_amount'}
            ,{field:'avgPrice',width:200, title: 'MstSharePrice001.avgPrice',type:'number', sortField:'avg_price'}
            ,{field:'preClosePriceAdj',width:200, title: 'MstSharePrice001.preClosePriceAdj',type:'number', sortField:'pre_close_price_adj'}
            ,{field:'openPriceAdj',width:200, title: 'MstSharePrice001.openPriceAdj',type:'number', sortField:'open_price_adj'}
            ,{field:'highPriceAdj',width:200, title: 'MstSharePrice001.highPriceAdj',type:'number', sortField:'high_price_adj'}
            ,{field:'lowPriceAdj',width:200, title: 'MstSharePrice001.lowPriceAdj',type:'number', sortField:'low_price_adj'}
            ,{field:'closePriceAdj',width:200, title: 'MstSharePrice001.closePriceAdj',type:'number', sortField:'close_price_adj'}
            ,{field:'factorAdj',width:200, title: 'MstSharePrice001.factorAdj',type:'number', sortField:'factor_adj'}
            ,{field:'tradeStatusStr',width:200, title: 'MstSharePrice001.tradeStatus', sortField:'trade_status'}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
            ]
    });
     
     /**
      * 绑定编辑事件
      */
     table.bind('click', '.edit', function(data, index) {
         data.pageModel = PAGE_MODEL_EDIT;
         var dealDate = new Date(parseInt(data.dealDate));
         dealDate= dealDate.format('yyyy-MM-dd');
         Joyin.ajaxJson(basePath + 'invest/basicdata/mstshareprice/MstSharePrice002/check', data, function(result) {
             Joyin.dialog(JoyinUtil.getLabel("MstSharePrice002.edit"),
                     basePath + "invest/basicdata/mstshareprice/MstSharePrice002/init",
                     "95%", "90%", {pageModel:PAGE_MODEL_EDIT,
            	 					sharesId:data.sharesId,
            	                     dealDate:dealDate},function() {
                                     table.reload();
               });
         });
     });
     
     /**
      * 绑定删除事件
      */
     table.bind('click', '.delt', function(data, index) {
         var object = {selectedItem: [data], operateType:"delete"};
         Joyin.ajaxJson(basePath + 'invest/basicdata/mstshareprice/MstSharePrice001/check', object, function(result) {
             Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                 WorkFlow.doDeleteList(basePath + 'invest/basicdata/mstshareprice/MstSharePrice001/delete', object, "MstSharePrice001_btn_delete", "MstSharePrice002",function() {
                 	table.reload();
                 });
             });
         });
     });
     
    /**
     * 以excel形式导出数据
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    
    $(function() {
        
            // 绑定按钮事件
            $('#MstSharePrice001_btn_search').click(function() {
            	if($('#sharesId').val()||$('#dealDate').val()){
            		table.load();
            	}else{
            		Joyin.error(JoyinUtil.getMessage("MST_SHAREPRICE_0001"));
            	}
            });
            $('#MstSharePrice001_btn_add').click(function() {
                Joyin.dialog(JoyinUtil.getLabel("MstSharePrice002"),
                            basePath + "invest/basicdata/mstshareprice/MstSharePrice002/init",
                            "95%", "90%", {pageModel:PAGE_MODEL_NEW});
            });
           $('#MstSharePrice001_btn_export').click(function(){
        	   if($('#sharesId').val()||$('#dealDate').val()){
        		   exportExcel();
        	   }
           })
           
           // 导入按钮点击事件
           $('#MstSharePrice001_btn_import').click(function() {
               Joyin.confirm("请确认是否要导入数据？", function() {
                   Joyin.uploadFile(function(fileName) {
                       Joyin.ajax(basePath + 'invest/basicdata/mstshareprice/MstSharePrice001/checkConfirmMsgForImportData', {fileName: fileName}, function() {
                           Joyin.ajax(basePath + 'invest/basicdata/mstshareprice/MstSharePrice001/importFile', {fileName: fileName});
                       });
                   },function() {
                       table.reload();
                   });
               });
           });
           
          /* $('#MstYieldCurveInfo001_btn_delete').click(function() {
               if(table!=null && table!=''){
                   var selArr = table.getAllCheckedRows();
               }
               if (!selArr || selArr.length == 0) {
                   Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                   return;
               }
               var object = {selectedItem: selArr, operateType:"delete"};
               Joyin.ajaxJson(basePath + 'invest/basicdata/yieldcurveinfo/MstYieldCurveInfo001/check', object, function(result) {
                   Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                       WorkFlow.doDeleteList(basePath + 'invest/basicdata/yieldcurveinfo/MstYieldCurveInfo001/delete', object, "MstYieldCurveInfo001_btn_delete", "MstYieldCurveInfo002",function() {
                        	table.reload();
                       });                  
                   });
               });
           });*/
      });
}());
    
