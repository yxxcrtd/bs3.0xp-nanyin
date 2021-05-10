$(function () {

    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        // 获取列表参数
        var object = $.serializeToObject('#dataForm');
        object.operateType = $('#operateType').val();
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'valuation/valuationparametermanage/valtrdaccountmfconfig/ValTrdAccountmfConfig002/check', object, function(result) {
//            Joyin.confirm("是否确定执行？", function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'valuation/valuationparametermanage/valtrdaccountmfconfig/ValTrdAccountmfConfig002/save', object, "ValTrdAccountmfConfig002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'valuation/valuationparametermanage/valtrdaccountmfconfig/ValTrdAccountmfConfig002/save', object, "ValTrdAccountmfConfig002_btn_submit");
                }
//            });
        });
    }
    
    /**
     * 绑定联动
     */
    var linkBind=function() {
    	// 费用类型和支付频率设定初始值
	   	$("#feeType").textSelect('setValue',"B13");
		$("#payCycle").textSelect('setValue',"04");
		
		var operateType = $('#operateType').val();
		if(operateType == 'edit'){
			$('#booksetId').attr("disabled",true);
		}
		
		var positionStartDate = $("#positionStartDate").val();
		var booksetId = $("#booksetId").val();
		Joyin.ajax(
                basePath + 'valuation/valuationparametermanage/valtrdaccountmfconfig/ValTrdAccountmfConfig002/dateCheck', 
                {positionStartDate : positionStartDate,booksetId : booksetId},function (data) {
                	if(data < 0){
                		$('#positionStartDate').attr("disabled",true);
                	}
          });
    	
    	// 计提日期默认为账套当前日期
//		if(operateType == "new"){
			$('#booksetId').change(function(){
	            var booksetId = $('#booksetId').val();
	            if(booksetId ==''|| booksetId==undefined){
	                $('#positionStartDate').val('');
	            }
	            else{
	                $('#positionStartDate').val($('#booksetId').select2('data')[0].object).trigger('change');
	                var booksetDate = new Date($('#booksetId').select2('data')[0].object);
	                booksetDate = + booksetDate + 1000*60*60*24;
	                booksetDate = new Date(booksetDate);
	                //booksetDate = booksetDate.getFullYear()+"-"+(booksetDate.getMonth()+1)+"-"+booksetDate.getDate();
	                booksetDate = dateFtt("yyyy-MM-dd",booksetDate);
	                $('#positionEndDate').val(booksetDate).trigger('change');
	            }
	        })
//		}
//    	// 计提开始日不能大于计提结束日
//    	$("#positionStartDate").change(function(){
//        	var positionStartDate = $("#positionStartDate").val();
//        	var positionEndDate = $("#positionEndDate").val();
//        	if(positionStartDate != "" && positionEndDate!= "" && positionStartDate > positionEndDate){
//        		$("#positionStartDate").val("").trigger("change");// select清空
//            	layer.msg("计提开始日不能大于计提结束日！");
//        	}
//        });
//    	// 计提结束日不能小于计提开始日
//        $("#positionEndDate").change(function(){
//        	var positionStartDate = $("#positionStartDate").val();
//        	var positionEndDate = $("#positionEndDate").val();
//        	if(positionStartDate != "" && positionEndDate!= "" && positionStartDate > positionEndDate){
//        		$("#positionEndDate").val("").trigger("change");// select清空
//            	layer.msg("计提结束日不能小于计提开始日！");
//        	}
//        });
    };
    
    function dateFtt(fmt,date) {
	     var o = { 
		     "M+" : date.getMonth()+1,
		     "d+" : date.getDate(),
		     "h+" : date.getHours(),
		     "m+" : date.getMinutes(),
		     "s+" : date.getSeconds(),
		     "q+" : Math.floor((date.getMonth()+3)/3),
		     "S" : date.getMilliseconds()
	     }; 
	     if(/(y+)/.test(fmt)) 
	     fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	     for(var k in o) 
	     if(new RegExp("("+ k +")").test(fmt)) 
	     fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
	     return fmt; 
    }
    
    $(function() {
    	
    	linkBind(); // 联动
        
        // $('#ValTrdAccountmfConfig002_btn_save').click(function() {
        //     saveOrSubmit('save');
        // });
        $('#ValTrdAccountmfConfig002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });

    });
});
