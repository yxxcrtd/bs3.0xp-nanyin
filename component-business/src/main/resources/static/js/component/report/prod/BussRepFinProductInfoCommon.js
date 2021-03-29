/**
 * 中债报备信息共通业务组件
 * @auth zhangliang
 */
(function(){

	var getElementById = function(id) {
		return $('#' + bussRepFinProductInfo + '_' + id);
	};
	
	var getElementRepById = function(id) {
		return $('#' + pageId + '_' + id);
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
//	 	var repIsYield = getElementById('repIsYield')[0].checked;
//	 	if (repIsYield){
//	 		getElementById('repYieldComment').prop('disabled',false);
//	 		getElementById('repIsYieldBasis').prop('disabled',false);
//	 		getElementById('repExpAvgYield').prop('disabled',false);
//	 	}else{
//	 		getElementById('repIsYieldBasis').val('').trigger('change').prop('disabled',true);
//	 		getElementById('repExpAvgYield').val('').trigger('change').prop('disabled',true);
//	 		getElementById('repYieldComment').val('').trigger('change').prop('disabled',true);
//	 	}
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
			getElementById('repOpenMode').val('').trigger('change').prop('disabled',true);
			getElementById('repHolidayOnFlag').val('').trigger('change').prop('disabled',true);
		}else{
			getElementById('repOpenCircle').prop('disabled',false);
			getElementById('repOpenCircleDays').prop('disabled',false);
			getElementById('repRuleComment').prop('disabled',false);
			getElementById('repFirstOpenDay').prop('disabled',false);
			getElementById('repAvgOpenCount').prop('disabled',false);
			getElementById('repOpenBuss').prop('disabled',false);
			getElementById('repOpenBussRemark').prop('disabled',false);
			getElementById('repOpenMode').prop('disabled',false);
			getElementById('repHolidayOnFlag').prop('disabled',false);
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
	var changeRepOpenCircle = function(){
		var repOpenCircle = getElementById('repOpenCircle').val();
		if(repOpenCircle !='99'){
			getElementById('repOpenCircleDays').val("").prop('disabled',true);
		}else{
			getElementById('repOpenCircleDays').prop('disabled',false);
		}
	};
	
	//是否设置最短持有期限 如果是 则 最短持有期限（天）必输 ，否则置灰 yjm start 20190807
	var isShortTermChange = function(){
		var isShortTerm = getElementById('isShortTerm').val();
		if (isShortTerm == '01'){
			getElementById('shortTerm').attr('disabled', false);
            getElementById('isFreeRedem').attr('disabled', false);
		} else{
            getElementById('shortTerm').val('').attr('disabled', true);
            getElementById('isFreeRedem').val('').trigger('change').attr('disabled', true);
		}
	};
    //是否设置最短持有期限 如果是 则 最短持有期限（天）必输 ，否则置灰 yjm end 20190807
	


	var repInitAction = function(input_finprodId){
		Joyin.ajax(JoyinUtil.getRealPath('component/report/prod/BussRepFinProductInfo/queryRepInfoByFinprodId'), {finprodId : input_finprodId}, function(data){
			FinProductCommon.initPageDataAction(bussRepFinProductInfo, data);
			//update zhangweiqi 
            if(data.repCashPrinCcy){
			 $("#bussFinProductSaleParamDto_repCashPrinCcy").val(data.repCashPrinCcy.split(",")).trigger('change');
			}	
			if(data.repCashProfCcy){
			  $("#bussFinProductSaleParamDto_repCashProfCcy").val(data.repCashProfCcy.split(",")).trigger('change');
			}		
			//$("#bussFinProductSaleParamDto_repInvestorType").val(data.repInvestorType.split(",")).trigger('change');
			// 募集金额改成元 yzg 20191126 start 
			if(data.repRaiseAmtPlan){
				 $("#bussFinProductSaleParamDto_raiseAmtPlan").val(data.repRaiseAmtPlan);	
			}
			// 募集金额改成元 yzg 20191126 end 
		})
	}
	//根据行内标识码获取申报登记信息
	var repInitByRepFinprodIdAction = function(input_repFinprodInsId){
		Joyin.ajax(JoyinUtil.getRealPath('component/report/prod/BussRepFinProductInfo/queryRepInfoByRepFinprodId'), {repFinprodInsId : input_repFinprodInsId}, function(data){
			FinProductCommon.initPageDataAction(bussRepFinProductInfo, data);
			// 产品申报要素带入数据不可修改 add by yzg 20191113 start
			FinProductCommon.initDisabledChange(bussRepFinProductInfo, data);
			// 产品申报要素带入数据不可修改 add by yzg 20191113 end
			//update zhangweiqi 
            if(data.repCashPrinCcy){
			 $("#bussFinProductSaleParamDto_repCashPrinCcy").val(data.repCashPrinCcy.split(",")).trigger('change');
			}
			if(data.repCashProfCcy){
			  $("#bussFinProductSaleParamDto_repCashProfCcy").val(data.repCashProfCcy.split(",")).trigger('change');
			}
			//$("#bussFinProductSaleParamDto_repInvestorType").val(data.repInvestorType.split(",")).trigger('change');
			//募集金额单位改为元 yzg 20191126 start
			// 计划募集金额
			if(data.repRaiseAmtPlan){
				 $("#bussFinProductSaleParamDto_raiseAmtPlan").val(data.repRaiseAmtPlan);	
			}
			//募集金额单位改为元 yzg 20191126 end
			//产品名称
			getElementRepById("finprodName").val(data.repFinprodName);
			//产品收益标识
			getElementRepById("profitFlag").val(data.repProfitType).trigger('change');
			//募集方式
			$("#bussFinProductSaleParamDto_issueType").val(data.repIssueType);
			//投资性质
			if(data.repInvestNature){
				getElementRepById("investNature").val(data.repInvestNature).trigger('change');
			}
			//是否现金管理类
			getElementRepById("isCashManage").val(data.repIsCashManage).trigger('change');
			//销售地区
			if(data.repSaleArea){
				 $("#bussFinProductSaleParamDto_saleArea").val(data.repSaleArea.split(",")).trigger('change');
			}
			//认购起点
			 $("#bussFinProductSaleParamDto_toffStart").val(data.repSaleAmtStr);
			//募集币种
			if(data.repRaiseCcy){
			 $("#bussFinProductSaleParamDto_ccy").val(data.repRaiseCcy).trigger('change');
			} 
			//募集开始日 结束日
			var repRaisePstrdate = Date.ofDate(data.repRaisePstrdate).format('yyyy-MM-dd');
			var repRaisePenddate = Date.ofDate(data.repRaisePenddate).format('yyyy-MM-dd');
			//$("#bussFinProductSaleParamDto_firstSaleVdate").val(repRaisePstrdate);
			//$("#bussFinProductSaleParamDto_firstSaleMdate").val(repRaisePenddate);
			//募集开始日（从） （到）
			getElementRepById("repRaisePstrdate").val(repRaisePstrdate);
			getElementRepById("repRaisePenddate").val(repRaisePenddate);
			//产品风险等级
			if(data.repRiskLevel){
				getElementRepById("riskLevel").val(data.repRiskLevel).trigger('change');
			}
			if(data.repFinprodInsId){
                getElementRepById("finprodMarketId").val(data.repFinprodInsId).trigger('change');
            }
		})
	}
	window.BussRepFinProductInfo = {
			repInitAction : repInitAction,
			repIsYieldChange : repIsYieldChange,
			repCreditFlagChange : repCreditFlagChange,
			repInitByRepFinprodIdAction : repInitByRepFinprodIdAction
	}
	//add by yejiejie 2019-3-8 start
	var repFinprodStageCAction = function(){
		getElementById('repFinprodStage').val($('#'+repFinprodStageC).val());
	};
	//add by yejiejie 2019-3-8 end
	//add by tuqirong 2019-03-26 start
	var riskLevelAction = function(){
		getElementById('repRiskLevel').val($('#'+riskLevel).val()).trigger('change');
	};
	//add by tuqirong 2019-03-26 end
	//add by chenxiaoqiu 20191216
	var fromOrto = function() {
		// b) 募集起始日（从）≤募集起始日（到）
		var str = getElementById('repRaisePstrdate').val();
		var end = getElementById('repRaisePenddate').val();
		if (str && end) {
			var days = (Date.ofDate(end) - Date.ofDate(str))/ (24 * 60 * 60 * 1000);
			if (days < 0) {
				Joyin.error(JoyinUtil.getMessage("募集起始日（从）必须小于等于 募集起始日（到）",JoyinUtil.getLabel("BussRepFinProductInfo.repRaisePstrdate")));
				getElementById('repRaisePstrdate').val('');
				return;
			}
		}
		var vdate = $('#bussFinProductSaleParamDto_firstSaleVdate').val();
		if (vdate != ''&&str && end && vdate) {
			var day1 = (Date.ofDate(vdate) - Date.ofDate(str))/ (24 * 60 * 60 * 1000);
			var day2 = (Date.ofDate(end) - Date.ofDate(vdate))/ (24 * 60 * 60 * 1000);
			if (day1 < 0 || day2 < 0) {
				Joyin.error(JoyinUtil.getMessage("募集开始日必须在募集起始日（从）和募集起始日（到）之间",JoyinUtil.getLabel("BussRepFinProductInfo.repRaisePstrdate")));
				$('#bussFinProductSaleParamDto_firstSaleVdate').val('');
				return;
			}
		}
	};
	$(function() {
//		if(repProductIsMerger === yesOrNo_Y){
			//发行方式联动
	        if ($('#' + pageId + '_operationType').val() == '01') {
	            //封闭式置灰 中债报送tab的 是否设置最短持有期限 和 最短持有期限（天） start yjm 20190807
	            getElementById('isShortTerm').val('').trigger('change').attr('disabled', true);
	            //封闭式置灰 中债报送tab的 是否设置最短持有期限 和 最短持有期限（天） end yjm 20190807
	        }
			// 画面目标客户类型联动事件处理
	    	//getElementById('repInvestorType').change(repInvestorTypeChange);
	    	// 资金投向地区
			getElementById('repInvestArea').change(repInvestAreaChange);
			//判断（从）是否小于（到）
	        getElementById('repRaisePstrdate').change(fromOrto);
	        getElementById('repRaisePenddate').change(fromOrto);
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
	        getElementById('isShortTerm').change(isShortTermChange);
			// 收益率分档情况说明
			getElementById('repYieldComment_showRule').click(function(){
				Joyin.alert(JoyinUtil.getLabel("BussRepFinProductInfo.repYieldComment.showRule"));
			});
			// 规律开放周期
			getElementById('repOpenCircle').change(changeRepOpenCircle);
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
	        	//add by yejiejie 2019-3-8 start      	
	        	$('#'+repFinprodStageC).change(function(){
	        		
	        		repFinprodStageCAction();
	        	});
	        	//add by yejiejie 2019-3-8 end
	        	//add by tuqirong 2019-03-26 start
	        	$('#'+riskLevel).change(function() {
	        		
	        		riskLevelAction();
				});
	        	//add by tuqirong 2019-03-26 end
	    	}
//		}
//		else{
//	    	disablePageElement(pageId+'_bussRepFinProductInfoDto', 1);
//            getElementById('repManageMode').attr('disabled', false);
//            getElementById('customerType').attr('disabled', false);
//		}
//    	//隐藏目标客户类型【该字段净值化产品已放到募集要素内
//    	 getElementById('repInvestorType').parent().parent().hide();

//add by yangwenjun 20201117 草稿箱功能行内识别码带出中债报送信息相关处理 start
        if(isDraft){
		    var repFinprodId = $('#FinNetValueProductBuild002_repFinprodInsId').val();
                	if(repFinprodId){
                		BussRepFinProductInfo.repInitByRepFinprodIdAction(repFinprodId);
                	};
		}
//add by yangwenjun 20201117 草稿箱功能行内识别码带出中债报送信息相关处理 end
        
        //系统参数SVR_IS_SUBSIDIARY 01 是子公司时   ‘新老产品标记’置灰置空
        if(svrIsSubsidiary == '01'){
        	getElementById('isFhxgcp').prop('disabled', true);        
        }else{  
        	getElementById('isFhxgcp').prop('disabled',false);      
        }
	});
}());
