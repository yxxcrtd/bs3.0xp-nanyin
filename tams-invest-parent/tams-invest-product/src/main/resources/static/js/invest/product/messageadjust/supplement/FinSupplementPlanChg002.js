/**
 * 产品业绩基准
 */

$(function() {
    
    var getParam = function(){
        return {
            finprodId:$("#finprodId").val(),
            accrStrDate:$("#accrStrDate").val(),
            accrEndDate:$("#splitDate").val(),
            cashId:$("#cashId").val()
            
        }
    };
    
	$('#splitDate').change(function(){
	    debugger;
		if(Date.ofDate($("#splitDate").val())<=Date.ofDate($("#accrStrDate").val())){
   		   Joyin.error(JoyinUtil.getMessage("拆分日期必须大于区间开始日!"));
   		   $("#splitDate").val('');
   		   return ;
   	    }
		if(Date.ofDate($("#splitDate").val())>=Date.ofDate($("#accrEndDate").val())){
	   		Joyin.error(JoyinUtil.getMessage("拆分日期必须小于区间结束日期!"));
	   		$("#splitDate").val('');
	   		return ;
	   	}
		if(Date.ofDate($("#splitDate").val())<Date.ofDate(sysDate)){
	   		Joyin.error(JoyinUtil.getMessage("拆分日期必须大于等于平台日期!"));
	   		$("#splitDate").val('');
			return ;
	    }
		
  	   Joyin.ajax(basePath + 'invest/product/messageadjust/supplement/FinSupplementPlanChg002/getAccrDate',
  	         getParam(), function(result){
            	$("#newAccrDate").val(result).trigger("change");
         });
	}
	);
	
	$("#FinSupplementPlanChg002_btn_submit").click(function(){
//		 var object={};
//	  	 object.accrStrDate=$("#accrStrDate").val();
//	  	 object.accrEndDate=$("#accrEndDate").val();
//	  	 object.accrDate=$("#accrDate").val();
//	  	 object.cashId=$("#cashId").val();
//	  	 object.finprodAbbr= $("#finprodAbbr").val();
//	  	 object.finprodId=$("#finprodId").val();
//	  	 object.finprodMarketId=$("#finprodMarketId").val();
//	  	 object.rn=$("#rn").val();
	     

	  	 
	     if(Date.ofDate($("#newAccrDate").val())>=Date.ofDate($("#accrEndDate").val())){
             Joyin.error(JoyinUtil.getMessage(FIN_PROD_E_0227));
             return ;
	     } else{
	         top.returnData=$("#newAccrDate").val();
	         var index = parent.layer
	         .getFrameIndex(window.name);
	         parent.layer.close(index);  
	         return;
	     }	
	});
}());