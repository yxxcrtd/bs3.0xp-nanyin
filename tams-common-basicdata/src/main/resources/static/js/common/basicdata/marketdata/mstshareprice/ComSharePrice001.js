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
                     basePath + "common/basicdata/marketdata/mstshareprice/ComSharePrice002/init",
                     "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,
					            	 sharesId:data.sharesId,
					                 dealDate:dealDate});
         },
        /*
         * 定义表头
         */
        cols:[
             {field:'sharesId',width:200, title: 'ComSharePrice001.sharesId', sortField:'shares_id'}
            ,{field:'finprodAbbr',width:200, title: 'ComSharePrice001.finprodAbbr', sortField:'finprod_abbr'}
            ,{field:'sShares',width:200,type:'number', title: 'ComSharePrice001.sShares' , digit:3,sortField:'s_shares'}
            ,{field:'cShares',width:200, title: 'ComSharePrice001.cShares',type:'number',digit:3, sortField:'c_shares'}
            ,{field:'dealDate',width:200, title: 'ComSharePrice001.dealDate',type:'date', sortField:'deal_date'}
            ,{field:'preClosePrice',width:200, title: 'ComSharePrice001.preClosePrice',type:'number', digit:3, sortField:'pre_close_price'}
            ,{field:'openPrice',width:200, title: 'ComSharePrice001.openPrice',type:'number', digit:3, sortField:'open_price'}
            ,{field:'highPrice',width:200, title: 'ComSharePrice001.highPrice',type:'number', digit:3, sortField:'high_price'}
            ,{field:'lowPrice',width:200, title: 'ComSharePrice001.lowPrice',type:'number', digit:3, sortField:'low_price'}
            ,{field:'closePrice',width:200, title: 'ComSharePrice001.closePrice',type:'number', digit:3, sortField:'close_price'}
            ,{field:'change',width:200, title: 'ComSharePrice001.change',type:'number', digit:3, sortField:'change'}
            ,{field:'changePercent',width:200, title: 'ComSharePrice001.changePercent',type:'number', digit:3, sortField:'change_percent'}
            ,{field:'dealValume',width:200, title: 'ComSharePrice001.dealValume',type:'number', digit:3, sortField:'deal_valume'}
            ,{field:'dealAmount',width:200, title: 'ComSharePrice001.dealAmount',type:'number', digit:3, sortField:'deal_amount'}
            ,{field:'avgPrice',width:200, title: 'ComSharePrice001.avgPrice',type:'number', digit:3, sortField:'avg_price'}
            ,{field:'preClosePriceAdj',width:200, title: 'ComSharePrice001.preClosePriceAdj',type:'number', digit:3, sortField:'pre_close_price_adj'}
            ,{field:'openPriceAdj',width:200, title: 'ComSharePrice001.openPriceAdj',type:'number',digit:3, sortField:'open_price_adj'}
            ,{field:'highPriceAdj',width:200, title: 'ComSharePrice001.highPriceAdj',type:'number', digit:3, sortField:'high_price_adj'}
            ,{field:'lowPriceAdj',width:200, title: 'ComSharePrice001.lowPriceAdj',type:'number', digit:3,sortField:'low_price_adj'}
            ,{field:'closePriceAdj',width:200, title: 'ComSharePrice001.closePriceAdj',type:'number', digit:3, sortField:'close_price_adj'}
            ,{field:'factorAdj',width:200, title: 'ComSharePrice001.factorAdj',type:'number', digit:3, sortField:'factor_adj'}
            ,{field:'tradeStatusStr',width:200, title: 'ComSharePrice001.tradeStatus', sortField:'trade_status'}
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
//         Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/mstshareprice/ComSharePrice002/check', data, function(result) {
         Joyin.confirm("请确认是否修改?", function(){
             Joyin.dialog(JoyinUtil.getLabel("ValSharePrice002.edit"),
                 basePath + "common/basicdata/marketdata/mstshareprice/ComSharePrice002/init",
                 "95%", "90%", {pageModel:PAGE_MODEL_EDIT,
                     sharesId:data.sharesId,
                     dealDate:dealDate},function() {
                     table.reload();
                 });
         });
//         });
     });
     
     /**
      * 绑定删除事件
      */
     table.bind('click', '.delt', function(data, index) {
         var object = {selectedItem: [data], operateType:"delete"};
         Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/mstshareprice/ComSharePrice001/check', object, function(result) {
             Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                 WorkFlow.doDeleteList(basePath + 'common/basicdata/marketdata/mstshareprice/ComSharePrice001/delete', object, "ComSharePrice001_btn_delete", "ComSharePrice002",function() {
                 	table.reload();
                 });
             });
         });
     });
     
    /**
     * 以excel形式导出数据
     */
    var exportExcel = function() {
        /** modify by yanya on 20210225 增加页面选中数据行进行导出 start**/
        // var paramObj = getSearchParams();
        // Joyin.exportExcel('export', paramObj);
        var selArr = null;
        var datas = null;
        if (table) {
            selArr = table.getAllCheckedRows();
            datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length === 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
        /** modify by yanya on 20210225 增加页面选中数据行进行导出 end**/
    };
    
    $(function() {
        
            // 绑定按钮事件
            $('#ComSharePrice001_btn_search').click(function() {
//            	去掉查询校验
//            	if($('#sharesId').val()||$('#dealDate').val()){
//            		table.load();
//            	}else{
//            		Joyin.error(JoyinUtil.getMessage("VAL_SHAREPRICE_0001"));
//            	}
            	table.load();
            });
            $('#ComSharePrice001_btn_add').click(function() {
                Joyin.dialog(JoyinUtil.getLabel("ComSharePrice002"),
                            basePath + "common/basicdata/marketdata/mstshareprice/ComSharePrice002/init",
                            "95%", "90%", {pageModel:PAGE_MODEL_NEW});
            });
           $('#ComSharePrice001_btn_export').click(function(){
//        	   if($('#sharesId').val()||$('#dealDate').val()){
        		   exportExcel();
//        	   }
           })
           
		    /**
		     * 删除
		     */
		    $('#ComSharePrice001_btn_delete').click(function () {
		        if(table!=null && table!=''){
		            var selArr = table.getAllCheckedRows();
		        }
		        if (!selArr || selArr.length == 0) {
		            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
		            return;
		        }
		        var object = {selectedItem: selArr, operateType:"delete"};
		        Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/mstshareprice/ComSharePrice001/check', object, function(result) {
		             Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
		                 WorkFlow.doDeleteList(basePath + 'common/basicdata/marketdata/mstshareprice/ComSharePrice001/delete', object, "ComSharePrice001_btn_delete", "ValSharePrice002",function() {
		                 	table.reload();
		                 });
		             });
		         });
		    });
           
           /**
            * 导入按钮
            */
           $('#ComSharePrice001_btn_import').click(function() {
               Joyin.confirm("请确认是否要导入数据？", function() {
                   Joyin.uploadFile(function(fileName) {
                       Joyin.ajax(basePath +"common/basicdata/marketdata/mstshareprice/ComSharePrice001/importFile", {
                           fileName : fileName
                       },function(result){
                    	   if(!result || !($.isArray(result) && result.length > 0)){
                          		Joyin.ajax(
                                          basePath + 'common/basicdata/marketdata/mstshareprice/ComSharePrice001/importFileSave', 
                                          {fileName : fileName},function (data1) {
                                        	  layui.layer.closeAll()
                                              table.reload();
                                    });
                          	}
                    	   else if($.isArray(result) && result.length > 0 && result[0].rowNum != "") {
	                           	Joyin.ajax(
	                                       basePath + 'common/basicdata/marketdata/mstshareprice/ComSharePrice001/duplicateCheck', 
	                                       {fileName : fileName},function (data) {
	                                       	if(result.length == data.length){
	                                       		Joyin.confirm("导入文件存在"+data.length+"条数据重复，重复数据不导入，是否确认？", function(){
	                                       			Joyin.ajax(
	                                                           basePath + 'common/basicdata/marketdata/mstshareprice/ComSharePrice001/importFileSave', 
	                                                           {fileName : fileName},function (data1) {
	                                                        	   layui.layer.closeAll()
	                                                               table.reload();
	                                                        	   Joyin.alert("文件导入成功！", 0);
	                                                     });
	                                               }, {}, function() {
	                                               	layui.layer.closeAll()
	                                                table.reload();
	                                            });
	                                       	}
	                                 });
	                           }
                       });
                   });
               });
           });
           
      });
}());
    
