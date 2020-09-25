/**
 * FinNetProductBonusScheme003 净值型分红方案确认
 */
(function() {    

    //分红方案联动
    var bonuScalcFlagChange = function (isInit) {
        var bonuScalcFlag = $('#bonuScalcFlag').val();
        if(bonuScalcFlag == bonuScalcFlagUnit){
            $(".unitBonusVlu").show();
            $(".totalAmt").hide();
        }
        else{
            $(".totalAmt").show();
            $(".unitBonusVlu").hide();
        }
    };
    
    //实际分红方案确认初始化
    var actBonusInit = function (isInit) {
    	if(isInit){
    		
    		var regDate = $("#regDate").val();
    		
            var finprodId = '';
            if(PROD_STYLE){
            	finprodId = PROD_STYLE;
            }
            else{
            	finprodId = $("#finprodId").val();
            }
            var object1 = {'vluRefDate':regDate,'finprodId':finprodId};
            if(regDate && finprodId){
                //获取权益登记日份额
                Joyin.ajax(getTdyUnitValueAndTdyShareAmt, object1, function (data) {
                    if (data) {
                        //权益登记日份额
                    	$('#regDateNum').val(data.tdyShareAmt);
                    }
                    
                  //分红规则
                    var bonusRule = $('#bonuScalcFlag').val();
                    //份额
                    var regDateNum = $('#regDateNum').val();
                    //单位分红 金额
                    var unitBonusVlu = $('#unitBonusVlu').val();
                    //总分红金额
                    var totalAmt = $('#totalAmt').val();
                    if(bonusRule=='01'){
                    	$('#actUnitBonusVlu').val(unitBonusVlu);
                    	if(regDateNum && unitBonusVlu){
                    		var actTotalAmt = JoyinMath.doMul(regDateNum, unitBonusVlu);
                        	$('#actTotalAmt').val(actTotalAmt).trigger("change");
                    	}            	
                    }else if(bonusRule=='02'){
                    	//总额分红
                    	$('#actTotalAmt').val(totalAmt).trigger("change");
                    	//delete by wm 根据需求要求，去除此控制  start 2019-07-09 
                    	//按总额分红净值归1时，实际分红总金额默认等于方案的分红总金额不能改
//                        var isNetMormalize = $('#isNetMormalize').val();
//                        if(isNetMormalize=='Y'){
//                        	$("#actTotalAmt").prop('disabled',true)
//                        }
                    	//delete by wm 根据需求要求，去除此控制  end 2019-07-09 
                    }
                });
            }
            
    		
    	}
    	
    };

    //产品款型下拉枚举值联动初始化
    var prodStyleSelectInit = function(){
    	//产品代码
    	var finprodId = $('#finprodId').val(); 
//    	$('#prodStyle').empty();
    	Joyin.ajaxJson(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme002/getProdStyleList', 
        	{'finprodId':finprodId}, 
        	function(result) {
        		if(result != null && result.length > 0){
    	            $("#prodStyle option").remove();
    	            $('#prodStyle').append("<option value=''>请选择...</option>");
            		$(result).each(function (i, n) {
                    	$('#prodStyle').append("<option value='"+n.finprodId+"'>"+n.prodType+"</option>");
                    });
            		$('#prodStyle').val(PROD_STYLE).parent().parent().show();
        		} else {
    				$("#prodStyle").val('').parent().parent().hide();
        		}
        	}
        );
    };
    //初始化
    var init = function () {
        bonuScalcFlagChange(true);
        if(pageModel == PAGE_MODEL_NEW){
        	actBonusInit(true);
        }
        
        var operateType = $('#operateType').val();
    	if("revoke"==operateType || "delete"==operateType){
    		if("N"==isFromToDoList){
	    		$('#FinNetProductBonusScheme003_btn_save').hide();
	    		$('#FinNetProductBonusScheme003_btn_submit').hide();
	    		$('#FinNetProductBonusScheme003_btn_revoke').show();
    		}else{
    			$('#FinNetProductBonusScheme003_btn_revoke').hide();
    		}
    		//layui-btn-disabled
    	}else{
    		$('#FinNetProductBonusScheme003_btn_revoke').hide();
    	}
        
    }

    //判断是提交或是保存
    function saveOrSubmit(operateType) {
    	
        var object = $.serializeToObject('#submitForm', true);
        object.unitBonusVlu = $("#unitBonusVlu").val();
        object.totalAmt = $("#totalAmt").val();
        //add by gcl 2019/3/27 start
        object.actTotalAmt=$("#actTotalAmt").val();
        //add by gcl 2019/3/27 end
        object.actUnitBonusVlu = $("#actUnitBonusVlu").val();
        object.tradeId = $("#tradeId").val();        
        object.pageModel = pageModel;
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme003/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme003/save', object, "FinNetProductBonusScheme003_btn_save");
                }else if(operateType === 'submit'){
                    WorkFlow.doSubmitList(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme003/save', object, "FinNetProductBonusScheme003_btn_submit");
                }else if(operateType === 'revoke') {
                	WorkFlow.doDeleteList(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme003/doRevoke', object, "FinNetProductBonusScheme003_btn_revoke");
                }
            });
        });
    };
    /**
     * 画面加载完成
     *
     */
    $(function() {
        $("#prodStyle").val('').parent().parent().hide();
        //产品款型赋值
        prodStyleSelectInit();
        
        init();
        // 绑定按钮事件
        $('#FinNetProductBonusScheme003_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#FinNetProductBonusScheme003_btn_submit').click(function() {
            saveOrSubmit('submit');
        });

        $('#FinNetProductBonusScheme003_btn_revoke').click(function() {
            saveOrSubmit('revoke');
        });
    });
}());

