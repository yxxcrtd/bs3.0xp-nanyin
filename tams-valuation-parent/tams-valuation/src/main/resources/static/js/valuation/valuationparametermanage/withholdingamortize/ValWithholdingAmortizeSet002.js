$(function () {
    
	/**
	 * 修改/新建
	 */
	var saveOrSubmit = function(operateType) {
		var object = $.serializeToObject('#dataForm');
		object.pageModel = pageModel;
		object.operateType = operateType;
		
		Joyin.ajaxJson(basePath + 'valuation/valuationparametermanage/withholdingamortize/ValWithholdingAmortizeSet002/check', object, function(result) {
//		    Joyin.confirm(result.data, function() {
		    	if(operateType === 'save'){
				    WorkFlow.doSaveList(basePath + 'valuation/valuationparametermanage/withholdingamortize/ValWithholdingAmortizeSet002/saveOrSubmit', 
							object, "ValWithholdingAmortizeSet002_btn_save");
				}else{// submit
					WorkFlow.doSubmitList(basePath + 'valuation/valuationparametermanage/withholdingamortize/ValWithholdingAmortizeSet002/saveOrSubmit', 
							object, "ValWithholdingAmortizeSet002_btn_submit");
				}
//		    });
		});
	};

    /**
     * 根据操作类型：显示或隐藏
     */
    var initShow = function () {
//		$("#c_finprodId2").hide();
//		$("#c_finprodId1").show();
        var operateType = $('#operateType').val();
        if(operateType === 'new') {
        }else if(operateType === 'edit') {
    		$("#c_booksetId1").hide();
    		$("#c_booksetId2").show();
    		$("#c_finprodId1").hide();
    		$("#c_finprodId2").show();
        	$("#booksetId").attr("disabled", "disabled");
        	$("#feeType").attr("disabled", "disabled");
        }else {
    		$("#c_booksetId1").hide();
    		$("#c_booksetId2").show();
    		$("#c_finprodId1").hide();
    		$("#c_finprodId2").show();
        	 $('#valuationDate').next().addClass('disabled').find('li').off('click');
        }
    };

//  绑定联动：业务编号-费用类型-金额（12月18日 新增：开始日期）
    $("#finprodId").change(function(){
		  var finprodId = $("#finprodId").val();
		  var booksetId = $("#booksetId").val();
		  Joyin.ajax(
				basePath + 'valuation/valuationparametermanage/withholdingamortize/ValWithholdingAmortizeSet002/getFinprodInfo', 
				{finprodId : finprodId,booksetId : booksetId},
				function (data) {
					  $("#feeType").val(data.feeType).trigger("change");
					  $("#prin").val(data.prin).trigger("change");
					  //12月18日 新增：开始日期
					  $("#vdate").val(data.tradeDate).trigger("change");
		  });
    });
    
//  12月18日 新增：开始日期大于等于支付日期
    $("#vdate").change(function(){
          var object = $.serializeToObject('#dataForm');
//       var vdate = $("#vdate").val();
//       var finprodId = object.finprodId;
          Joyin.ajax(
                  basePath + 'valuation/valuationparametermanage/withholdingamortize/ValWithholdingAmortizeSet002/getTradeDate', 
                  {finprodId : object.finprodId},
                  function (data) {
                        if(object.vdate != '' && object.vdate<data.tradeDate){
                            layer.alert('开始日期不能小于支付日期');
                            $('#vdate').val('');
                        }
            });
    });
    
    $(function() {
        // 绑定按钮事件
        $('#ValWithholdingAmortizeSet002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#ValWithholdingAmortizeSet002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });


        initShow();// 根据操作类型：显示或隐藏
        
//        $("select").each(function () {
//            var select = $(this);
//            var parentIds = $(this).attr("parentIds");
//            if (parentIds) {
//                var parentIdArr = parentIds.split(",");
//                for (var i = 0; i < parentIdArr.length; i++) {
//                    $("#" + parentIdArr[i] ).change(function () {
//                        select.val("").trigger("change");
//                    });
//                }
//            }
//        });

    });
});