/**
 * 中债报备信息共通业务组件
 * @auth zhangliang
 */
(function(){

	var getElementById = function(id) {
		return $('#' + bussRepFinProductInfo + '_' + id);
	};
	
//	var repInvestorTypeChange = function(){
//		var selectedValue = getElementById('repInvestorType').val();
//		
//		if (selectedValue && pageId != 'FinProductScheduling002'){
//			// 收益率分档情况说明：当目标客户类型为：私人银行客户专属、机构客户专属，且是否分层为是的产品才允许填写，否则置灰不可填。
//			if ((selectedValue == SALETARGET_DECLARE_01 || selectedValue == SALETARGET_DECLARE_02)
//					&& $('#' + isLayer)[0].checked){
//				getElementById('repYieldComment').prop('disabled',false);
//			}else{
//				if(pageModel === PAGE_MODEL_NEW) {
//					getElementById('repYieldComment').val('');
//				}
//				getElementById('repYieldComment').prop('disabled',true);
//			}
//		}
//	}
	
	var repInvestAreaChange = function(){
		var repInvestArea = getElementById('repInvestArea').val();
		if (repInvestArea === saleAreaDeclareOut || repInvestArea === saleAreaDeclareInOut){
			getElementById('repInvestCountry').prop('disabled',false);
			getElementById('repOutDepCtry').prop('disabled',false);
			getElementById('repOutDepName').prop('disabled',false);
		}else{
			getElementById('repInvestCountry').val('').trigger('change').prop('disabled',true);
			getElementById('repOutDepCtry').val('').trigger('change').prop('disabled',true);
			getElementById('repOutDepName').val('').trigger('change').prop('disabled',true);
		}
		if (repInvestArea === saleAreaDeclareIn || repInvestArea === saleAreaDeclareInOut){
			getElementById('repFinanSerModel').prop('disabled',false);
			getElementById('repInDepName').prop('disabled',false);
			getElementById('repInDepCode').prop('disabled',false);
		}else{
			getElementById('repFinanSerModel').val('').trigger('change').prop('disabled',true);
			getElementById('repInDepName').val('').trigger('change').prop('disabled',true);
			getElementById('repInDepCode').val('').trigger('change').prop('disabled',true);
		}
	}
	
	var repPmmodeChange = function(){
		var repPmmode = getElementById('repPmmode').val();
		if (repPmmode === prodManageModeActM || repPmmode === prodManageModeOthO){
			getElementById('repMname').prop('disabled',false);
		}else{
			getElementById('repMname').val('').trigger('change').prop('disabled',true);
		}
	}
	
	var repIsYieldChange = function(){
		var repIsYield = getElementById('repIsYield')[0].checked;
		if (repIsYield){
			getElementById('repYieldComment').prop('disabled',false);
			getElementById('repIsYieldBasis').prop('disabled',false);
			getElementById('repExpAvgYield').prop('disabled',false);
		}else{
			getElementById('repIsYieldBasis').val('').trigger('change').prop('disabled',true);
			getElementById('repExpAvgYield').val('').trigger('change').prop('disabled',true);
			getElementById('repYieldComment').val('').trigger('change').prop('disabled',true);
		}
	}
	
	var repContModeChange = function(){
		var repContMode = getElementById('repContMode').val();
		if (repContMode === copModeSigRun){
			getElementById('repCoopName').val('').trigger('change').prop('disabled',true);
		}else{
			getElementById('repCoopName').prop('disabled',false);
		}
	}
	
	var repCreditFlagChange = function(){
		var repIsYield = getElementById('repCreditFlag')[0].checked;
		if (repIsYield){
			getElementById('repCreditType').prop('disabled',false);
			getElementById('repCreditMode').prop('disabled',false);
		}else{
			getElementById('repCreditType').val('').trigger('change').prop('disabled',true);
			getElementById('repCreditMode').val('').trigger('change').prop('disabled',true);
		}
	}
	
	var repOperateModeChange = function(){
		var repOperateMode = getElementById('repOperateMode').val();
		if (repOperateMode === prodRunModeCloN || repOperateMode === prodRunModeCloNN){
			getElementById('repOpenCircle').val('').trigger('change').prop('disabled',true);
			getElementById('repOpenCircleDays').val('').trigger('change').prop('disabled',true);
			getElementById('repRuleComment').val('').trigger('change').prop('disabled',true);
			getElementById('repFirstOpenDay').val('').trigger('change').prop('disabled',true);
			getElementById('repAvgOpenCount').val('').trigger('change').prop('disabled',true);
			getElementById('repOpenBuss').val('').trigger('change').prop('disabled',true);
			getElementById('repOpenBussRemark').val('').trigger('change').prop('disabled',true);
			
		}else{
			getElementById('repOpenCircle').prop('disabled',false);
			getElementById('repOpenCircleDays').prop('disabled',false);
			getElementById('repRuleComment').prop('disabled',false);
			getElementById('repFirstOpenDay').prop('disabled',false);
			getElementById('repAvgOpenCount').prop('disabled',false);
			getElementById('repOpenBuss').prop('disabled',false);
			getElementById('repOpenBussRemark').prop('disabled',false);
		}
	}
	
	var repTermChangeAction = function(){
		var param = {};
		param.prodVdate = $('#' + vdateElementId).val();
		param.prodMdate = $('#' + mdateElementId).val();
		param.operationType = $('#' + operationType).val();// 开放类型
		param.openMode = $('#' + openMode).val();// 开放模式
		param.openCycle = $('#' + openCycle).val();// 开放周期
		param.openCycleUnit = $('#' + openCycleUnit).val();// 开放周期单位
		
		Joyin.ajaxJson(JoyinUtil.getRealPath('component/report/prod/BussRepFinProductInfo/calcProductTermInfo'), param, function(result){
			if (result){
				getElementById('repTerm').val(result.data).trigger('change');
			}
		})
	};
	
	var repInitAction = function(input_finprodId){
		Joyin.ajax(JoyinUtil.getRealPath('component/report/prod/BussRepFinProductInfo/queryRepInfoByFinprodId'), {finprodId : input_finprodId}, function(data){
			FinProductCommon.initPageDataAction(bussRepFinProductInfo, data);
		})
	}
	
	window.BussRepFinProductInfo = {
			repInitAction : repInitAction,
			repIsYieldChange : repIsYieldChange,
			repCreditFlagChange : repCreditFlagChange
	}

	$(function() {
		// 画面目标客户类型联动事件处理
    	//getElementById('repInvestorType').change(repInvestorTypeChange);
    	// 资金投向地区
    	getElementById('repInvestArea').change(repInvestAreaChange);
    	// 产品管理模式
    	getElementById('repPmmode').change(repPmmodeChange);
    	// 是否有预期收益率
    	repIsYieldChange();
		layui.form.on('switch(repIsYieldFilter)', repIsYieldChange);
		// 合作模式
		getElementById('repContMode').change(repContModeChange);
		// 产品增信标识
		repCreditFlagChange();
		layui.form.on('switch(repCreditFlagFilter)', repCreditFlagChange);
		// 产品运作模式
		getElementById('repOperateMode').change(repOperateModeChange);
		// 投资资产种类及比例
		getElementById('repInvTypeRat_showRule').click(function(){
			Joyin.alert(JoyinUtil.getLabel("BussRepFinProductInfo.repInvTypeRat.showRule"));
		});
		// 收益率分档情况说明
		getElementById('repYieldComment_showRule').click(function(){
			Joyin.alert(JoyinUtil.getLabel("BussRepFinProductInfo.repYieldComment.showRule"));
		});
		
    	// 产品新建时联动改变期限品种
    	if (pageId === 'FinProductBuild002'||pageId === 'FinNetValueProductBuild002'){
    		// 主画面起息日和到期日联动改变期限品种
        	$('#' + vdateElementId).change(function(){
        		repTermChangeAction();
    		});
        	$('#' + mdateElementId).change(function(){
        		repTermChangeAction();
    		});
        	$('#' + operationType).change(function(){
        		repTermChangeAction();
    		});
        	$('#' + openMode).change(function(){
        		repTermChangeAction();
    		});
        	$('#' + openCycle).change(function(){
        		repTermChangeAction();
    		});
        	$('#' + openCycleUnit).change(function(){
        		repTermChangeAction();
    		});
    	}
	});
}());