(function(){
	
	var templetIdChangeAction = function(){
		var finprodId = $('#FinProductScheduling002_SELECT_templetId').val();
		if (finprodId){
			Joyin.confirm(JoyinUtil.getMessage('FIN_PROD_E_0002'), function() {
				$('#FinProductScheduling002_SELECT_issueMode').val('').trigger("change");// 发行方式
				$('#FinProductScheduling002_SELECT_ccy').textSelect('setValue', ''); // 币种
				$('#FinProductScheduling002_SELECT_branch').textSelect('setValue', ''); //分行
				Joyin.ajax(basePath + 'invest/product/productelement/scheduling/FinProductScheduling002/queryFinProductByFinprodId', {finprodId : finprodId}, function(result){
					if(result){
						$('#FinProductScheduling002_SELECT_issueMode').val(result.entrustType).trigger("change");// 发行方式
						$('#FinProductScheduling002_SELECT_ccy').textSelect('setValue', result.ccy); // 币种
						$('#FinProductScheduling002_SELECT_branch').textSelect('setValue', result.entruster); //分行
						
						// 申报参数初始化
						BussRepFinProductInfo.repInitAction(finprodId);
					}
				});
	        });
		}
	};
	
	var onSaveOrSubmitAction = function(operateType){
		var object = $.serializeToObject('#FinProductScheduling002_submitForm', true);
		
		if (object){
			// 分行多选处理
			if (object.branchBank){
				object.branchBank = object.branchBank.join(',');
			}
			
			// 处理申报登记信息业务组件的参数
			if (object.bussRepFinProductInfoDto){
				if (object.bussRepFinProductInfoDto.repInvestCountry){
					object.bussRepFinProductInfoDto.repInvestCountry = object.bussRepFinProductInfoDto.repInvestCountry.join(',');
				}
				if (object.bussRepFinProductInfoDto.repRiskType){
					object.bussRepFinProductInfoDto.repRiskType = object.bussRepFinProductInfoDto.repRiskType.join(',');
				}
				if (object.bussRepFinProductInfoDto.repSaleArea){
					object.bussRepFinProductInfoDto.repSaleArea = object.bussRepFinProductInfoDto.repSaleArea.join(',');
				}
				if (object.bussRepFinProductInfoDto.repCreditType){
					object.bussRepFinProductInfoDto.repCreditType = object.bussRepFinProductInfoDto.repCreditType.join(',');
				}
				if (object.bussRepFinProductInfoDto.repOutDepCtry){
					object.bussRepFinProductInfoDto.repOutDepCtry = object.bussRepFinProductInfoDto.repOutDepCtry.join(',');
				}
	        	// 兑付本金币种
	        	if (object.bussRepFinProductInfoDto.repCashPrinCcy){
	        		object.bussRepFinProductInfoDto.repCashPrinCcy = object.bussRepFinProductInfoDto.repCashPrinCcy.join(',');
	        	}
	        	// 兑付收益币种
	        	if (object.bussRepFinProductInfoDto.repCashProfCcy){
	        		object.bussRepFinProductInfoDto.repCashProfCcy = object.bussRepFinProductInfoDto.repCashProfCcy.join(',');
	        	}
			}
		}
		
		object.operateType = operateType;
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'invest/product/productelement/scheduling/FinProductScheduling002/check',object, function(result) {
            Joyin.confirm(result.data, function() {
            	if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/product/productelement/scheduling/FinProductScheduling002/save', object, "FinProductScheduling002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/product/productelement/scheduling/FinProductScheduling002/save', object, "FinProductScheduling002_btn_submit");
                }
                })
            })
	};
	

	
	var vdateMdateChangeAction = function(){
		var vdate = $('#FinProductScheduling002_DATE_vdate').val();
		var mdate = $('#FinProductScheduling002_DATE_mdate').val();
		if (vdate && mdate){
			var days = (Date.ofDate(mdate) - Date.ofDate(vdate))/(24*60*60*1000);
			if (days <= 0){
				$('#FinProductScheduling002_SELECT_termType').val('').trigger('change');
			}else if (days >= 0 && days <= 7){
				$('#FinProductScheduling002_SELECT_termType').val(REPTERM_02).trigger('change');
			}else if (days > 7 && days <= 31){
				$('#FinProductScheduling002_SELECT_termType').val(REPTERM_03).trigger('change');
			}else if (days > 31 && days <= 92){
				$('#FinProductScheduling002_SELECT_termType').val(REPTERM_04).trigger('change');
			}else if (days > 92 && days <= 184){
				$('#FinProductScheduling002_SELECT_termType').val(REPTERM_05).trigger('change');
			}else if (days > 184 && days <= 366){
				$('#FinProductScheduling002_SELECT_termType').val(REPTERM_06).trigger('change');
			}else if (days > 366){
				$('#FinProductScheduling002_SELECT_termType').val(REPTERM_07).trigger('change');
			}
		}else{
			$('#FinProductScheduling002_SELECT_termType').val('').trigger('change');
		}
	}
	
	$(function() {
		
		// 新建可编辑、到经办可编辑，修改不可 编辑、到代办也不可编辑
		if ($('#wfl_operType').val()){
			if ($('#wfl_operType').val() != 'NEW'){
				$('#FinProductScheduling002_finprodMarketId').prop('disabled', true);
			}
		}else{
			if (pageModel != PAGE_MODEL_NEW){
				$('#FinProductScheduling002_finprodMarketId').prop('disabled', true);
			}
		}
		
		// 产品模板下拉框change事件处理
		$('#FinProductScheduling002_SELECT_templetId').change(function(){
			templetIdChangeAction();
		});
		// 保存按钮执行事件
		$('#FinProductScheduling002_btn_save').click(function(){
			onSaveOrSubmitAction('save');
		});
		// 提交按钮执行事件
		$('#FinProductScheduling002_btn_submit').click(function(){
			onSaveOrSubmitAction('submit');
		});
		
		// 起息日、到期日联动处理产品期限
		$('#FinProductScheduling002_DATE_vdate').change(function(){
			vdateMdateChangeAction();
		});
		$('#FinProductScheduling002_DATE_mdate').change(function(){
			vdateMdateChangeAction();
		});
	});
}());