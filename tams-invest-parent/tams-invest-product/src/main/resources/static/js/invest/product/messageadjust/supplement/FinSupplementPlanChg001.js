/**
 * 产品业绩基准
 */

$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var param = $.serializeToObject('#searchForm',true);
        if(param.layFinprodId) {
            param.layFinprodId = param.layFinprodId.join(",");
        }
        debugger;
        return param;
    };
    
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
    	ajax: {
    		url : JoyinUtil.getRealPath('invest/product/messageadjust/supplement/FinSupplementPlanChg001/findPage'),
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
       
        addbtn : false,
        searchOnInit : true,
        rowno: false,
        cols:[
             {field:'cashId', visible: false}
            ,{field:'rn', visible: false}
            ,{field:'supplementStatus',visible: false}
            ,{filed:"finprodId",visible:false}
            ,{field:'finprodMarketId', title: 'FinSupplementPlanChg001.finprodMarketId', width:150}
            ,{field:'finprodAbbr',title: 'FinSupplementPlanChg001.finprodAbbr', width:250}
            ,{field:'prodStyle',title: 'FinSupplementPlanChg001.prodStyle', width:150}
            ,{field:'accrStrDate',title: 'FinSupplementPlanChg001.accrStrDate', width:200,type:'date'}
            ,{field:'accrEndDate',title: 'FinSupplementPlanChg001.accrEndDate', width:200,type:'date'}
            ,{field:'accrDate',title: 'FinSupplementPlanChg001.accrDate', width:200,type:'date'}
            ,{field:'supplementStatusStr',title: 'FinSupplementPlanChg001.supplementStatus', width:100}
            ,{field:'layFinprodId',visible: false}
            ,{field:'isLay',visible: false}
            ,{field:'isAvailable',visible: false}
            
        ],
        done: function(table1, allData) {
//        	for(var i=0;i<table1.length;i++){
//        		if($('#workflowId').val()){
//        			$("input[name='layTableCheckbox']").each(function(m,o){
//        				o.disabled=false;
//        		    });
//        			table.checkRow(i);
//        		}
//        	}
        }
    });    
    
    /**
     * 绑定导出事件
     */
   
    var init = function(){
    	//隐藏查询和筛选按钮
    	
    	var workflowId = $("#workflowId").val();
    	if(workflowId!=""&&workflowId!=undefined){
    		$('#finprodMarketId').change();
    		/*
    		if(dataList!=null&&dataList.length>0)
    		table.reload({data:dataList}) ;
    		*/
    	}
    	table.table?table.reload():table.load();
    };
    /**
     * 画面加载完成
     */
    $(function() {
    	init();
        // 产品代码联动
        $('#finprodMarketId').change(function(){
            if($('#finprodMarketId').select2('data')[0].object) {
                var str = $('#finprodMarketId').select2('data')[0].object.split(',');
                if(str != null && str.length==3){
                    $("#finprodId").val(str[0]);
                    $("#isLay").val(str[1]);
                    $("#finprodAbbr").val(str[2]);
                }  
            }

            //table.load();
            //$('#FinOpenPlanMaintenance001_btn_delete').show();
        });
      //提交
        $('#FinSupplementPlanChg001_btn_submit').click(function(){
        	saveOrSubmit('submit');
        });
        //查询
        $('#FinSupplementPlanChg001_btn_search').click(function() {
        	if($("#finprodId").val()==""||$("#finprodId").val()==undefined){
        		Joyin.error(JoyinUtil.getMessage("产品简称不能为空", JoyinUtil.getLabel("finprodMarketId")));
        		return;
        	}
        	table.table?table.reload():table.load();
        });
       
        //拆分
        $("#FinSupplementPlanChg001_btn_split").click(function(){
            if(table.getAllCheckedRows().length!=1){
                Joyin.error(JoyinUtil.getMessage("请选择一条记录拆分!"));
                return ;
            }
            
            if(table.getAllCheckedRows()[0].supplementStatus!=SUPPLEMENT_STATUS_02){
                Joyin.error(JoyinUtil.getMessage("只能拆分未贴补状态的计划!"));
                return ;
            }
            
            if(table.getAllCheckedRows()[0].isAvailable !=YES_OR_NO_Y) {
                Joyin.error(JoyinUtil.getMessage(FIN_PROD_E_0228));
                return ; 
            }
            
         var row=table.getAllCheckedRows()[0];
         
         
         var dialogIndex = Joyin.dialog(JoyinUtil.getLabel("FinSupplementPlanChg002"),
                 "/invest/product/messageadjust/supplement/FinSupplementPlanChg002/init",
                 "98%", "95%", row, function(data) {
             if(!data) return;
             // 新建回补 返回列
             var param = getSearchParams();
             param.dataList = table.getAllRows();
             param.delList = table.getAllCheckedRows();
             param.delList[0].newAccrDate = DateUtil.parse(data);
             param.opType="02";
            
             Joyin.ajaxJson(basePath + 'invest/product/messageadjust/supplement/FinSupplementPlanChg001/splitPlan',
                     param, function(result){
                 table.clear(function () {
                     if(result.length>0) {
                         for(var i=0;i<result.length;i++) {
                             table.addRow(result[i]);
                         }  
                     }
                 });  
             }); 
           });
        });
        
       //合并
       $("#FinSupplementPlanChg001_btn_join").click(function(){
    	   if(table.getAllCheckedRows().length==0){
    		   Joyin.error(JoyinUtil.getMessage("没有选中的记录，不能合并!"));
			   return ;
    	   }
           var object = getSearchParams();
           object.dataList = table.getAllRows();
           object.delList = table.getAllCheckedRows();
    	   object.opType="01";
    	   Joyin.ajaxJson(basePath + 'invest/product/messageadjust/supplement/FinSupplementPlanChg001/check', object, function(result) {
               Joyin.ajaxJson(basePath + 'invest/product/messageadjust/supplement/FinSupplementPlanChg001/joinPlan',
                       object, function(result){
                   table.clear(function () {
                       if(result.length>0) {
                           for(var i=0;i<result.length;i++) {
                               table.addRow(result[i]);
                           }  
                       }
                   });  
               }); 
           });
       
    	});
         
    });
    
    function saveOrSubmit(operateType) {
        var object= getSearchParams();
        object.dataList = table.getAllRows();   	   
       	object.opType="03";
        Joyin.ajaxJson(basePath + 'invest/product/messageadjust/supplement/FinSupplementPlanChg001/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                // modify by jiangpeng on 20210507 start
                // TISHKBS-2071 CLONE - 【付费计划调整】选择产品提交后，提交按钮置灰，切换产品后还是置灰，必须将画面关闭重新打开才能操作
                   WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/supplement/FinSupplementPlanChg001/save', object, "FinSupplementPlanChg001_btn_submit"
                       ,null, function (){
                           JoyinUtil.disableButton("FinSupplementPlanChg001_btn_submit", false);
                       }
                   );
                // modify by jiangpeng on 20210507 end
           });
       });
    };
}());