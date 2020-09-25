(function(){
	
	function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#FinOpenPlanMaintenance002_form',true);
        
        object.pageModel = pageModel;
        object.operateType = operateType;
        
        // 开放计划
        if (object.bussFinProductOpenPlanDto){
        	if (object.bussFinProductOpenPlanDto.dayOfWeek){
        		object.bussFinProductOpenPlanDto.dayOfWeek = object.bussFinProductOpenPlanDto.dayOfWeek.join(',');
        	}
        	if (object.bussFinProductOpenPlanDto.dayOfMonth){
        		object.bussFinProductOpenPlanDto.dayOfMonth = object.bussFinProductOpenPlanDto.dayOfMonth.join(',');
        	}
        	if (object.bussFinProductOpenPlanDto.workdayOfMonth){
        		object.bussFinProductOpenPlanDto.workdayOfMonth = object.bussFinProductOpenPlanDto.workdayOfMonth.join(',');
        	}
        	if (object.bussFinProductOpenPlanDto.weekOfMonth){
        		object.bussFinProductOpenPlanDto.weekOfMonth = object.bussFinProductOpenPlanDto.weekOfMonth.join(',');
        	}
        	if (object.bussFinProductOpenPlanDto.workdayOfWeek){
        		object.bussFinProductOpenPlanDto.workdayOfWeek = object.bussFinProductOpenPlanDto.workdayOfWeek.join(',');
        	}
        	if (!object.bussFinProductOpenPlanDto.hdayOpenFlag) {
        		object.bussFinProductSaleParamDto.hdayOpenFlag = 'N';
            }
        	if(object.operationType) {
        		object.bussFinProductOpenPlanDto.operationType = object.operationType;//发行方式不为空时，将发行方式传递到开放计划DTO里
        	}
        }
        
        //开放规则调整-周期、定期开放不允许修改为高频开放模式 add by yzg 20200214 start
		var openMode = object.bussFinProductOpenPlanDto.openMode;
		if(openModel_2 && (openModel_2 == OPENMODE_01 || openModel_2 == OPENMODE_03) && openMode == OPENMODE_02){
			Joyin.error(JoyinUtil.getMessage("周期开放和定期开放不允许修改为高频开放"));
			return;
		}
		if(openMode!=OPENMODE_02 &&!BussFinProductOpenPlan.chkOpenPlan()) return false;
		var openModeRed = object.bussFinProductOpenPlanRedDto.openMode;
		if(openModelRed_2 && (openModelRed_2 == OPENMODE_01 || openModelRed_2 == OPENMODE_03) && openModeRed == OPENMODE_02){
			Joyin.error(JoyinUtil.getMessage("周期开放和定期开放不允许修改为高频开放"));
			return;
		}
		if(openModeRed!=OPENMODE_02 &&!BussFinProductOpenPlan.chkRedOpenPlan()) return false;
		//开放规则调整-周期、定期开放不允许修改为高频开放模式 add by yzg 20200214 end
                      
        Joyin.ajaxJson(basePath + 'invest/product/messageadjust/maintenance/FinOpenPlanMaintenance002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/product/messageadjust/maintenance/FinOpenPlanMaintenance002/save', object, "FinOpenPlanMaintenance002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/maintenance/FinOpenPlanMaintenance002/save', object, "FinOpenPlanMaintenance002_btn_submit");
                }
            });
        });
    };
    
	//产品代码联动
    function finprodIdChange(finprodId){
    	
    	$('#bussFinProductOpenPlanDto_sameFLag').prop("checked",true).trigger("change");
    	    	
        Joyin.ajax(JoyinUtil.getRealPath('/invest/product/messageadjust/maintenance/FinOpenPlanMaintenance002/selectOpenPlanParamDto'), {finprodId : finprodId}, function(result){
        	$("#red_div").hide();
        	if (result){
        		redSpeed = result.redSpeed;
        		purSpeed = result.purSpeed;
        		$("#FinOpenPlanMaintenance002_dealMode").val(result.dealMode);
        		$("#FinOpenPlanMaintenance002_termType").val(result.termType);
        		$("#FinOpenPlanMaintenance002_investmentCycle").val(result.investmentCycle);
        		
        		$("#FinOpenPlanMaintenance002_calendarId").val(result.calendarId);
        		$("#FinOpenPlanMaintenance002_vdate").val(Date.ofDate(result.vdate).format("yyyy-MM-dd"));
        		$("#FinOpenPlanMaintenance002_mdate").val(Date.ofDate(result.mdate).format("yyyy-MM-dd"));
        		$("#finprodAbbr").val(result.finprodAbbr);
        		$("#FinOpenPlanMaintenance002_cashId").val(result.cashId);
        		$("#FinOpenPlanMaintenance002_openType").val(result.openType);
        		$("#FinOpenPlanMaintenance002_branch").val(result.branch);
        		$("#FinOpenPlanMaintenance002_cpriceRule").val(result.cpriceRule);
        		//$("#FinOpenPlanMaintenance002_period").val(result.termType);
        		if(finprodId){
        			BussFinProductOpenPlan.openPlanInitAction(finprodId);
        		}
        		//净值周期型
        		if(result.profitType == PROFIT_TYPE_01 && result.dealMode == '10'){
        			//单一固定期限
        			if(result.termType == TERM_TYPE_01){
        				var period = [{'investDays':result.investmentCycle+''}];
        				$("#periods").val(JSON.stringify(period));
        			}
        			//多固定期限
        			else if(result.termType == TERM_TYPE_02){
        				Joyin.ajax(JoyinUtil.getRealPath('/component/prod/BussFinProductInvestPeriod/queryBussFinProductInvestPeriodByFinprodId'), {finprodId : finprodId}, function(result){
        					if(result){
        						var selectedItem = [];
        						_.each(result,function (sel){
        		                   	selectedItem[result.length] = {investDays: sel.investDays + ''};
        		                });
        						$("#periods").val(JSON.stringify(selectedItem));
        					}
        				})
        			}
        		}
        		
        		
        		if(result.dealMode==='10' && (result.termType==='01' || result.termType === '02')){
        			$('#bussOpenPlandiv').show();
        		}else{
        			$('#bussOpenPlandiv').hide();
        		}
        		
        		// 处理模式为周期型，隐藏 “申赎同频”
        		if(result.dealMode==='10'){
        			$('#div1').hide();
        		}else{
        			$('#div1').show();
        		}
			}
		});
    };
    
    function nextOpenDayChange(newDate){
    	$("#bussFinProductOpenPlanDto_firstOpenDate").val(newDate);
    }
    
	/**
     * 画面加载完成
     */
    $(function() {
    	
    	//$('#FinNetValueProductBuild002_period').trigger('change').prop('disabled', false);
    	
    	//$("#FinOpenPlanMaintenance002_profitType").val(profitType);
    	
    	$('#FinOpenPlanMaintenance002_btn_save').click(function(){
    		saveOrSubmit('save');
        });
    	
    	$('#FinOpenPlanMaintenance002_btn_submit').click(function(){
    		saveOrSubmit('submit');
        });
    	
    	// 产品代码联动事件
    	$('#finprodId').change(function(){
    		
    		finprodIdChange($("#finprodId").val());
    	});
    	
    	// 下一开放日改变事件
    	$('#nextOpenDay').change(function(){
    		nextOpenDayChange($("#nextOpenDay").val());
    	});
    });
	
}());
