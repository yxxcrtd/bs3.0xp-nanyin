$(function() {

    /**
     * 获取表单参数
     */
  
    var getSearchParams = function() {
        return {
        	curveCode : $('#curveCode').val(),
            curveType : $('#curveType').val(),
            curveDate : $('#curveDate').val()
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
             var curveDate = new Date(parseInt(data.curveDate));
             curveDate= curveDate.format('yyyy-MM-dd');
             Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                     basePath + "common/basicdata/marketdata/yieldcurveinfo/ComYieldCurveInfo002/init",
                     "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,
            	                     curveCode:data.curveCode,
                                     curveDate:curveDate,
                                     curveType:data.curveType});
         },
        /*
         * 定义表头
         */
        cols:[
             {field:'curveDate',width:200, title: 'ComYieldCurveInfo001.curveDate',type:'date'}
             //修改数据字典值为查询值 modified by lishuping 20191008 start
            ,{field:'curveCode',width:200, title: 'ComYieldCurveInfo001.curveName'}
            //修改数据字典值为查询值 modified by lishuping 20191008 end
            ,{field:'keyPoint',width:200, title: 'ComYieldCurveInfo001.keyPoint',type:'number'}
            ,{field:'yield',width:200, title: 'ComYieldCurveInfo001.yield',type:'number'}
            ,{field:'curveType',width:200, title: 'ComYieldCurveInfo001.curveType', visible:false}
            ,{field:'curveTypeName',width:200, sortField:'curveType', title: 'ComYieldCurveInfo001.curveType'}
            ,{field:'n',width:200, title: 'N',type:'number'}
            ,{field:'k',width:200, title: 'K',type:'number'}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
            ]
    });
     
     /**
      * 绑定编辑事件
      */
     table.bind('click', '.edit', function(data, index) {
         data.pageModel = PAGE_MODEL_EDIT;
         var curveDate = new Date(parseInt(data.curveDate));
         curveDate= curveDate.format('yyyy-MM-dd');
         Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/yieldcurveinfo/ComYieldCurveInfo002/check', data, function(result) {
             Joyin.dialog(JoyinUtil.getLabel("ComYieldCurveInfo002.edit"),
                     basePath + "common/basicdata/marketdata/yieldcurveinfo/ComYieldCurveInfo002/init",
                     "95%", "90%", {pageModel:PAGE_MODEL_EDIT,
            	                     curveCode:data.curveCode,
            	                     curveDate:curveDate,
                                     curveType:data.curveType},function() {
                                     table.reload();
               });
         });
     });
     
     /**
      * 绑定删除事件
      */
     table.bind('click', '.delt', function(data, index) {
         var object = {selectedItem: [data], operateType:"delete"};
         Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/yieldcurveinfo/ComYieldCurveInfo001/check', object, function(result) {
             Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                 WorkFlow.doDeleteList(basePath + 'common/basicdata/marketdata/yieldcurveinfo/ComYieldCurveInfo001/delete', object, "ComYieldCurveInfo001_btn_delete", "ComYieldCurveInfo002",function() {
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
            $('#ComYieldCurveInfo001_btn_search').click(function() {
                table.load();
                $('#ComYieldCurveInfo001_btn_delete').show();
            });
            $('#ComYieldCurveInfo001_btn_add').click(function() {
                Joyin.dialog(JoyinUtil.getLabel("ComYieldCurveInfo002"),
                            basePath + "common/basicdata/marketdata/yieldcurveinfo/ComYieldCurveInfo002/init",
                            "60%", "90%", {pageModel:PAGE_MODEL_NEW});
            });
           $('#ComYieldCurveInfo001_btn_export').click(function(){
               exportExcel();
           })
           
           $('#ComYieldCurveInfo001_btn_delete').click(function() {
               if(table!=null && table!=''){
                   var selArr = table.getAllCheckedRows();
               }
               if (!selArr || selArr.length == 0) {
                   Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                   return;
               }
               var object = {selectedItem: selArr, operateType:"delete"};
               Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/yieldcurveinfo/ComYieldCurveInfo001/check', object, function(result) {
                   Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                       WorkFlow.doDeleteList(basePath + 'common/basicdata/marketdata/yieldcurveinfo/ComYieldCurveInfo001/delete', object, "ComYieldCurveInfo001_btn_delete", "ComYieldCurveInfo002",function() {
                        	table.reload();
                       });                  
                   });
               });
           });
      });
}());
    
