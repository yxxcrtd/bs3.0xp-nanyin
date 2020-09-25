/**
 * 存款存续期
 */
$(function() {
	function formatDateByMis(mis){
		if(mis){
			var tempDate = new Date(mis);
			return  tempDate.format();
		}
		else{
			return null;
		}
	}

	function saveOrSubmit(operateType) {
		if (GEN_PAY_ORDERS_AUTO == '01') {
			window.BussTrdTransferOrder['bussTrdTransferOrder'].generateOrders(function () {
				realSaveOrSubmit(operateType);
			});
		}
		else {
			realSaveOrSubmit(operateType);
		}
	}

	function realSaveOrSubmit(operateType) {
//		划款指令参数变更校验
		if(window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()){
			return ;
		}
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#informationForm',true);
        object.operateType = operateType;
        object.opType = opType;
		object.pageModel = PAGE_MODEL_NEW;
        Joyin.ajaxJson(basePath + 'invest/period/trddepoduration/TrdDepoDuration002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
				var elementId = 'TrdDepoDuration002_btn_tyjk';
                if(operateType === 'save') {
					if (object.assetType == DEPO_ASSET_TYPE_01) {
						elementId = 'TrdDepoDuration002_btn_save';
					}
                    WorkFlow.doSaveList(basePath + 'invest/period/trddepoduration/TrdDepoDuration002/save', object, elementId);
                }
                else {
					if (object.assetType == DEPO_ASSET_TYPE_01) {
						elementId = 'TrdDepoDuration002_btn_submit';
					}
                    WorkFlow.doSubmitList(basePath + 'invest/period/trddepoduration/TrdDepoDuration002/save', object, elementId);
                }
            });
        });
    }
	var triValueChange = function(id,data, defaultValue){
		if(data){
			$('#'+id).val(data).trigger("change").trigger('blur');
		}
		else{
			if (typeof defaultValue != 'undefined') {
				$('#'+id).val(defaultValue).trigger("change");
			}
			else {
				//$('#'+id).val('0.00').trigger("change");
				$('#'+id).val(' ').trigger("change");
			}
		}
	};
	var isNotNull = function(value){
		return value && value != "";
	}
	var calResiPrincipal = function(){
		var finprodMarketId = $('#finprodMarketId').val();
		var portfolioId = $('#portfolioId').val();
		var mdateProd =  $('#mdateProd').val();
        var chlId = $('#chlId').val();
		if(isNotNull(finprodMarketId)&&isNotNull(portfolioId)&&isNotNull(mdateProd)){
			Joyin.ajax(basePath + "invest/period/trddepoduration/TrdDepoDuration002/calResiPrincipal",
					{finprodMarketId:finprodMarketId,portfolioId:portfolioId,mdateProd:mdateProd, chlId: chlId},
					function(result){
					if(result.data){
						triValueChange('remainPrinAmt',result.data.RESIPRINCIPAL);
					}
					else{
						triValueChange('remainPrinAmt');
					}
			});
		}
		else{
			triValueChange('remainPrinAmt');
		}
		
	}
//	4.提前支取利率、提前支取金额输入后，联动算出 理论应计利息、理论本息总额																				
//	理论应计利息 = 画面.提前支取金额*画面.提前支取利率*（计息结束日-计息开始日）/计息基础																		
//																			
//	理论本息总额 = 理论应计利息+提前支取金额																		
//																			
//如上述影响计算参数变化，根据变化联动计算，如参数被清空，则联动清空。																			
	var earlyWithdrawalLink = function(){
		if (!$('#earlyWithdrawalRate').val() || !$("#mdateProd").val()) {
			triValueChange("intAmt", 0);
			return;
		}
		var earlyWithdrawalRate = parseFloat($('#earlyWithdrawalRate').val())/100;
		var earlyWithdrawalAmt = parseFloat($('#earlyWithdrawalAmt').val());
		var vdateProd = $("#vdateProd").val();
		var mdateProd = $("#mdateProd").val();
		var basis = parseInt($('#basis').val().split('/')[1]);
		if(isNotNull(mdateProd)&&isNotNull(earlyWithdrawalRate)&&isNotNull(earlyWithdrawalAmt)){
			var intAmt = earlyWithdrawalAmt*earlyWithdrawalRate*DateUtil.daysBetween(vdateProd,mdateProd)/basis;
			var fpriceAmt = intAmt + earlyWithdrawalAmt;
			triValueChange("theoryInt",intAmt);
			triValueChange("fpriceAmt",fpriceAmt);
//			triValueChange("intAmtReal",intAmt);
            triValueChange("intAmt",intAmt);
		}
		else{
			triValueChange("intAmt");
			triValueChange("fpriceAmt");
//			triValueChange("intAmtReal");
            triValueChange("intAmt");
			triValueChange("tradeAmt");
		}
	}
	window.componentLoadCallback = function(isDone, pagePath, params) {
        if (isDone) {
            window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
				if (!$("#tradeAmt").val()) {
					Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("TrdDepoDuration001.tradeAmt")));
					return;
				}
				if (!$("#tradeDate").val()) {
					Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("TrdDepoDuration001.fundAtDate")))
					return;
				}
				if (!$("#finprodMarketId").val()) {
					Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("TrdDepoDuration001.tradeAmt")));
					return;
				}
            	var deal = {};
            	deal.ccy = $("#ccy").val();
                deal.tradeAmt = $("#tradeAmt").val();
//                deal.settleDate = $("#settleDate").val();
                deal.ps = PS_DEFAULT;
                // 通道交易 新增 强成西 2019/03/26 start
                deal.chlId = $("#chlId").val();
                // 通道交易 新增 强成西 2019/03/26 end
                deal.tradeDate = $("#tradeDate").val();
                deal.finprodType2 = FINPRODTYPE_DEFAULT;
            	return {
            		'isTotalTrade': YES_OR_NO_Y
                    , 'bankLendingType': null
                    , 'portfolioId': $("#portfolioId").val()
                    , 'deal': eval(deal)
                };
            });
            
            if($('#finprodId').val()){
        		ChannelAssetCommon.setChannelSelectObject($("#chlId"),  $('#finprodId').val(), $("#entrustId").val(), function () {

    	        });
        	}
        }
    }
	$(function() {
    	if(CHANNEL){
    		$('#channelFee').prop('disabled',false);
    	}
    	
    	
		//资产代码的联动
		$('#finprodMarketId').change(function(){
			var finprodMarketId = $('#finprodMarketId').val();
			var mdateProd = $('#mdateProd').val();
			var finprodId=null;
			if(finprodMarketId || finprodMarketId !=''){
//				币种、资产类型、资产名称、起息日、到期日、计息开始日、计息基础、委托行、借款利率%、资产推荐费率%
				Joyin.ajax(basePath + "invest/period/trddepoduration/TrdDepoDuration002/finprodMarketIdLink",{finprodMarketId:finprodMarketId,mdateProd:mdateProd},
					function(result){
					if(result.data){
						triValueChange('ccy',result.data.CCY);
						triValueChange('assetType',result.data.ASSETTYPE);
						triValueChange('finprodMarketName',result.data.FINPRODNAME);
						triValueChange('vdate',formatDateByMis(result.data.VDATE));
						triValueChange('mdate',formatDateByMis(result.data.MDATE));
						triValueChange('vdateProd',formatDateByMis(result.data.VDATEPROD), '');
						triValueChange('basis',result.data.BASIS);
						triValueChange('entruster',result.data.ENTRUSTER);
						triValueChange('loadRate',result.data.LOADRATE);
						triValueChange('yield',result.data.YIELD);
						triValueChange('cashId',result.data.CASHID);
						triValueChange('finprodId',result.data.FINPRODID);
						finprodId=result.data.FINPRODID;
						ChannelAssetCommon.setChannelSelectObject($("#chlId"), finprodId, $("#entrustId").val(), function () {

				        });
					}
				});
			}
			else{
				triValueChange('ccy');
				triValueChange('assetType');
				triValueChange('finprodMarketName');
				triValueChange('vdate');
				triValueChange('mdate');
				triValueChange('vdateProd', '');
				triValueChange('basis');
				triValueChange('entruster');
				triValueChange('loadRate');
				triValueChange('yield');
				triValueChange('cashId');
				triValueChange('finprodId');
			}
			calResiPrincipal();
			ChannelAssetCommon.setChannelSelectObject($("#chlId"), null, $("#entrustId").val(), function () {

	        });
		});
		//组合代码联动
		$('#portfolioId').change(function(){
			var portfolioId = $('#portfolioId').val();
			//需求变更 存款存续期提前支取不要 托管账户 yjm start 20190918
			/*if(portfolioId || portfolioId !=''){
				Joyin.ajax(basePath + "invest/period/trddepoduration/TrdDepoDuration002/portfolioIdLink",{portfolioId:portfolioId},
						function(result){
					if(result.data){
						triValueChange('portfolioName',result.data.PORTFOLIONAME);
						triValueChange('secManageAcctId',result.data.SECMANAGEACCTID);
					}
				});
			}
			else{
				triValueChange('portfolioName');
				triValueChange('secManageAcctId');
			}*/
			if(portfolioId || portfolioId !=''){
				Joyin.ajax(basePath + "invest/period/trddepoduration/TrdDepoDuration002/portfolioIdLink",{portfolioId:portfolioId},
						function(result){
					if(result.data){
						triValueChange('portfolioName',result.data.PORTFOLIONAME);
					}
				});
			}
			else{
				triValueChange('portfolioName');
			}
            //需求变更 存款存续期提前支取不要 托管账户 yjm end 20190918
			calResiPrincipal();
		});
		$('#mdateProd').change(function(){
			calResiPrincipal();
			earlyWithdrawalLink();
			$('#finprodMarketId').trigger("change");
		});
		//通道变化获取
		$('#chlId').change(function () {
    	    // add by renping 2019-10-25 添加通道费 start
            if($('#chlId').val()){
                COLLECT_TYPE = $("#chlId").find("option:selected").data("collecttype");
    		    $('#channelFee').prop('disabled',false);
    		    $('#channel').val(true);
    		    $('#collectType').val(COLLECT_TYPE);
            } else {
                COLLECT_TYPE = '';
    		    $('#channelFee').prop('disabled',true);
    		    $('#channel').val(false);
    		    $('#collectType').val(COLLECT_TYPE);
            }
    	    // add by renping 2019-10-25 添加通道费 end
            calResiPrincipal();
        })
		$('#earlyWithdrawalRate').change(earlyWithdrawalLink);
		$('#earlyWithdrawalAmt').change(earlyWithdrawalLink);
		$('#TrdDepoDuration002_btn_save').click(function() {
            saveOrSubmit('save');
        });
		$('#intAmt').change(function(){
			//本金
			var remainPrinAmt = 0;
			if ($("#pingAnCodeSin").val() == pACode_10) {
				remainPrinAmt = $("#earlyWithdrawalAmt").val();
			}
			else if ($("#pingAnCodeSin").val() == pACode_40) {
				remainPrinAmt = $('#remainPrinAmt').val() ;
			}

			//交易金额
			var tradeAmt = $('#tradeAmt').val();
			var intAmt =  $('#intAmt').val();
			if(intAmt != null && intAmt != ''){
	    // add by renping 2019-10-15 添加通道费 start
				$("#tradeAmtReal").val(JoyinMath.doAdd(intAmt,remainPrinAmt)).trigger("blur");
				$("#tradeAmtReal").trigger("change");
			}
		});
		$('#tradeAmtReal').change(function(){
			//本金
			var remainPrinAmt = 0;
			if ($("#pingAnCodeSin").val() == pACode_10) {
				remainPrinAmt = $("#earlyWithdrawalAmt").val();
			}
			else if ($("#pingAnCodeSin").val() == pACode_40) {
				remainPrinAmt = $('#remainPrinAmt').val() ;
			}

			//利息
			var tradeAmtReal = $('#tradeAmtReal').val();
			if(tradeAmtReal != null && tradeAmtReal != ''){
				$("#intAmt").val(JoyinMath.doMinus(tradeAmtReal,remainPrinAmt,2)).trigger("blur");
				$('#channelFee').trigger('change');
			}
		});
    	$('#channelFee').change(function(){
    		var tradeAmtReal = $('#tradeAmtReal').val();
    		if(tradeAmtReal != '' && tradeAmtReal != null) {
	    		if(IS_CAL_LOADRATE&&IS_CAL_LOADRATE=='01'){
	    			if(COLLECT_TYPE == COLLECT_TYPE_01){//回款前扣除
		            	var channelFee = $("#channelFee").val();
		            	if(!channelFee){
		            		channelFee = 0;
		            	}
		            	 $("#tradeAmt").val(JoyinMath.doMinus(tradeAmtReal, channelFee)).trigger("blur");
		            } else {
		            	$("#tradeAmt").val(tradeAmtReal).trigger("blur");
		            }
	    		}else{
	    			$("#tradeAmt").val(tradeAmtReal).trigger("blur");
	    		}
    		}
    	});
    	// add by renping 2019-10-15 添加通道费 end
        $('#TrdDepoDuration002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
	});
}());
