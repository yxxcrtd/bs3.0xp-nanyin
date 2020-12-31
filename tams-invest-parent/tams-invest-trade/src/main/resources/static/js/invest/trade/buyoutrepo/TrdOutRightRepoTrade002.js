(function(){
	
	/**
	 * 资产代码
	 */
	var secIdChangeAction = function(){
		$('#TrdOutRightRepoTrade002_finprodName').val($("#TrdOutRightRepoTrade002_finprodId").select2('data')[0].id);
	};
    function getriskdata() {
        var obj = {
            timePoint: MY_SUP_TIME_TYPE_DEAL,
            valDate: $('#TrdOutRightRepoTrade002_tradeDate').val(),
            portfolioCode: $('#TrdOutRightRepoTrade002_pltId').val(),
            tradeAmount: $('#TrdOutRightRepoTrade002_fSettleAmt').val(),
            assetType: getFinProdType2FromPs(),
            tradeDirection:$('#TrdOutRightRepoTrade002_ps').val(),
            /*assetCode:$("#TrdOutRightRepoTrade002_finprodId").val(),*/
            isEnabled: '01'
        }
        if($('#TrdOutRightRepoTrade002_ps').val() == BACK_LEND_DIRECTION_FORWARD){
            obj.assetType = FINPROD_TYPE_TG_F04;
        }else if($('#TrdOutRightRepoTrade002_ps').val() == BACK_LEND_DIRECTION_NEGATIVE){
            obj.assetType = FINPROD_TYPE_TG_F05;
        }else{
            obj.assetType = null;
        }
        var object = {
            valDate: ['tradeDate', 'TrdOutRightRepoTrade002.dealDate'],
            portfolioCode: ['portfolioId', 'TrdOutRightRepoTrade002.pltId'],
            tradeAmount: ['fSettleAmt', 'TrdOutRightRepoTrade002.amtInfo.firstSettleAmt'],
            /* tradeAmount: ['fFprice', 'TrdOutRightRepoTrade002.amtInfo.firstFullPrice'],*/
            /* assetType: ['finprodId', 'TrdOutRightRepoTrade002.finprodMarketId'],*/
            tradeDirection:['ps', 'TrdOutRightRepoTrade002.ps'],
            data: obj
        }
        return object;
    }
	// modified by wangguoxian 费用设置 新增期限品种 20190723 start
	/**
	 * 期限品种
	 */
	var termTypeChangeAction = function(){
		var termType = $('#TrdOutRightRepoTrade002_termType').val();
		if (termType && termType != ''){
			var repoterm = '';
			if (termType == REPO_TYPE_R001 || termType == TERM_TYPE_REBACK_GC001 || termType == TERM_TYPE_REBACK_R_001 || termType == TERM_TYPE_REBACK_RC001){
				repoterm = 1;
			}else if(termType == TERM_TYPE_REBACK_GC002 || termType == TERM_TYPE_REBACK_R_002){
			    repoterm = 2;
			}else if(termType == TERM_TYPE_REBACK_GC003 || termType == TERM_TYPE_REBACK_R_003 || termType == TERM_TYPE_REBACK_RC003){
                repoterm = 3;
            }else if(termType == TERM_TYPE_REBACK_GC004 || termType == TERM_TYPE_REBACK_R_004){
                repoterm = 4;
            }else if (termType == REPO_TYPE_R007 || termType == TERM_TYPE_REBACK_GC007 || termType == TERM_TYPE_REBACK_R_007 || termType == TERM_TYPE_REBACK_RC007){
				repoterm = 7;
			}else if (termType == REPO_TYPE_R014 || termType == TERM_TYPE_REBACK_GC014 || termType == TERM_TYPE_REBACK_R_014){
				repoterm = 14;
			}else if (termType == REPO_TYPE_R021){
				repoterm = 21;
			}else if (termType == TERM_TYPE_REBACK_GC028 || termType == TERM_TYPE_REBACK_R_028){
                repoterm = 28;
			}else if (termType == REPO_TYPE_R1M){
				repoterm = 30;
			}else if (termType == REPO_TYPE_R2M){
				repoterm = 60;
			}else if (termType == REPO_TYPE_R3M){
				repoterm = 90;
			}else if (termType == TERM_TYPE_REBACK_GC091 || termType == TERM_TYPE_REBACK_R_091){
                repoterm = 91;
			}else if (termType == REPO_TYPE_R4M){
				repoterm = 120;
			}else if (termType == REPO_TYPE_R6M){
				repoterm = 180;
			}else if (termType == TERM_TYPE_REBACK_GC182 || termType == TERM_TYPE_REBACK_R_182){
                repoterm = 182;
			}
			/*$('#TrdOutRightRepoTrade002_mdate').attr('disabled',true);
			$('#TrdOutRightRepoTrade002_repoterm').val(repoterm).trigger('change').attr('disabled',true);*/
			$('#TrdOutRightRepoTrade002_repoterm').val(repoterm).trigger('change');
		}else{
			$('#TrdOutRightRepoTrade002_mdate').attr('disabled',false);
			$('#TrdOutRightRepoTrade002_repoterm').attr('disabled',false);
		}
	};
	// modified by wangguoxian 费用设置 新增期限品种 20190723 end

	/**
	 * 回购期限
	 */
	var repoTermChangeAction = function(){
		var repoterm = $('#TrdOutRightRepoTrade002_repoterm').val();
		var re = /^[0-9]*$/;
        if(repoterm && re.test(repoterm)){
            var vdate = $('#TrdOutRightRepoTrade002_vdate').val();
            if(vdate){
                var d1 = new Date(vdate).getTime();
                var day = parseInt(repoterm*24*60*60*1000);
                var mdate = new Date(d1+day);
                $('#TrdOutRightRepoTrade002_mdate').val(mdate.format("yyyy-MM-dd")).change();
                //回购期限联动期限品种
            	$('#TrdOutRightRepoTrade002_termType').val(getTermType(vdate,repoterm)).trigger('change');
            }
        }else{
        	$('#TrdOutRightRepoTrade002_termType').val("").trigger('change');
        }
	}
	
	/**
	 * 起息日联动处理产品期限
	 */
	var vdateChangeAction = function(){
		var termType = $('#TrdOutRightRepoTrade002_termType').val();
		if (termType && termType != ''){
			if ($('#TrdOutRightRepoTrade002_vdate').val()){
				repoTermChangeAction();
			}else{
				$('#TrdOutRightRepoTrade002_mdate').val('').trigger('change');
			}
		}else{
			vdateMdateChangeAction();
		}
	};
	
	/**
	 * 起息日、到期日联动处理产品期限
	 */
	var vdateMdateChangeAction = function(){
		//推算到期日  判断是否节假日 是则顺延
        var mdate = $('#TrdOutRightRepoTrade002_mdate').val();
        var calendarId = $("#TrdOutRightRepoTrade002_dealCalId").val();
        var dealBussId = $('#dealBussId').val();
        if(mdate&&calendarId){
            Joyin.ajaxJson(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade002/isDealCalendarHoliday', {
                   calendarId: calendarId,
                   mpayDate: mdate
                }, function (date) {
                    if(date){
                        Joyin.ajaxSync(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade002/getNextWorkDay', {
                           'calendarId': calendarId,
                           'tradeDate': mdate
                        }, function (date) {
                           $("#TrdOutRightRepoTrade002_mdate").val(date);
                        });
                    }
                },false);
        }else{
        	$('#TrdOutRightRepoTrade002_actAmtDays').val('');
        	return;
        }
		var vdate = $('#TrdOutRightRepoTrade002_vdate').val();
		var mdate = $('#TrdOutRightRepoTrade002_mdate').val();
		if (vdate && mdate&&!dealBussId){
			var days = (new Date(mdate) - new Date(vdate))/(24*60*60*1000);
			$('#TrdOutRightRepoTrade002_actAmtDays').val(days);
			/*$('#TrdOutRightRepoTrade002_repoterm').val(days);*/
		}else{
			$('#TrdOutRightRepoTrade002_actAmtDays').val('');
			/*$('#TrdOutRightRepoTrade002_repoterm').val('');*/
		}
	};
    // add by wangguoxian 费用设置 20190719 start

    /**
     * 计算费用
     */
    var calFee = function(){
        if(!$("#TrdOutRightRepoTrade002_tradeDate").val()){
            return;
        }

        if(!$("#chlId").val()){//如果通道为空，校验证券账户
			if(!$("#TrdOutRightRepoTrade002_secAcctId").val()){
				return;
			}
		}

        // if(!$("#TrdOutRightRepoTrade002_secAcctId").val()){
        //     return;
        // }
        if(!$("#TrdOutRightRepoTrade002_ps").val()){
            return;
        }
        /*if(!$("#TrdOutRightRepoTrade002_fSettleAmt").val()){
            return;
        }*/
        if($("#TrdOutRightRepoTrade002_tradeMarket").val() == TRADE_PLACE_01 && !$("#TrdOutRightRepoTrade002_termType").val()){
            return;
        }
		if (pageModel == PAGE_MODEL_REFERENCE){
			// 审批人审批或者生效的交易不再重新计算
			return;
		}

        var object = $.serializeToObject('#TrdOutRightRepoTrade002_submitForm', true);
		Joyin.ajaxJson(basePath + 'invest/trade/buyoutrepo/TrdOutRightRepoTrade002/calFee', object, function (result) {
			if (result) {
				var tradeFeeList = result.tradeFeeList;
				if(tradeFeeList && tradeFeeList.length>0){
                    // 交易手续费条数
                    var cntP = 0;
                    // 结算费条数
                    var cntS = 0;
					$.each(tradeFeeList,function(i,v){
						// 佣金设置
						/*if(v.feeType==FEE_TYPE_05&&v.feeAmt){
							$("#commissionAmt").val(v.feeAmt).trigger("blur");
							// 交易方向为逆回购并且随交易支付，交易金额 = 本金金额 + 随交易支付的费用
							if($("#TrdOutRightRepoTrade002_ps").val() == BACK_LEND_DIRECTION_NEGATIVE && v.isPayWithTrade == YES_OR_NO_Y){
								$("#tradeAmtAndFee").val(JoyinMath.doAdd($("#TrdOutRightRepoTrade002_fSettleAmt").val(),v.feeAmt));
							}
							if($("#TrdOutRightRepoTrade002_ps").val() == BACK_LEND_DIRECTION_FORWARD){
								$("#tradeAmtAndFee").val(JoyinMath.doMinus($("#TrdOutRightRepoTrade002_fSettleAmt").val(),v.feeAmt));
							}
						}*/
						if(v.feeType==FEE_TYPE_06&&v.feeAmt){
							cntP++;
							$("#procedureAmt").val(v.feeAmt).trigger("blur");
							$("#procedureAmtRate").val(v.feeRate)
						}
						if(v.feeType==FEE_TYPE_08&&v.feeAmt){
							cntS++;
							$("#settleAmt").val(v.feeAmt).trigger("blur");
							$("#settleAmtRate").val(v.feeRate)
						}

					});
                    // 按照最新费用规则计算相关费用无结果则清空相关费用
                    if(cntP == 0){
                        $("#procedureAmt").val("").trigger("blur");
                    }
                    if(cntS == 0){
                        $("#settleAmt").val("").trigger("blur");
                    }
				}else{
					$("#procedureAmt").val('').trigger("blur");
					$("#settleAmt").val('').trigger("blur");
				}
			}
		});
    };
    // add by wangguoxian 费用设置 20190719 end


// <!-- 通道交易 新增 强成西 2019/03/26 start -->
	$("#channelFee").on("change", function () {
		changeTradeAmtAndFee();
	});

	$("#TrdOutRightRepoTrade002_fSettleAmt").on("change", function () {
		changeTradeAmtAndFee();
	});

	$('#TrdOutRightRepoTrade002_secAcctId').on("change", function () {
		if ($("#TrdOutRightRepoTrade002_secAcctId").val()) {
			$("#chlId").val('').prop("disabled", true);
		}
		else {
			$("#chlId").prop("disabled", false);
		}
		// 组合代码、证券托管户、交易日、到期日改变时，刷新可使用标准券额度
		refreshCashAndBalance();
	});

	$("#chlId").on("change", function () {
		if ($("#chlId").val()) {
			$("#TrdOutRightRepoTrade002_secAcctId").val('').prop("disabled", true);
		}
		else {
			$("#TrdOutRightRepoTrade002_secAcctId").prop("disabled", false);
		}
		refreshCashAndBalance();
	});

	var changeTradeAmtAndFee = function() {
		if (BACK_LEND_DIRECTION_NEGATIVE == $("#TrdOutRightRepoTrade002_ps").val()) {
			$("#tradeAmtAndFee").val(JoyinMath.doAdd($("#TrdOutRightRepoTrade002_fSettleAmt").val(), $("#channelFee").val(), 2)).trigger('blur');
		}
		else {
			$("#tradeAmtAndFee").val(JoyinMath.doMinus($("#TrdOutRightRepoTrade002_fSettleAmt").val(), $("#channelFee").val(), 2)).trigger('blur');
		}
	}
	// <!-- 通道交易 新增 强成西 2019/03/26 end -->
	
	/**
     * 刷新托管户剩余现金、组合T日可用现金、组合T+1日可用现金
     */
    var refreshCashAndBalance = function () {
        var params = {};
        params.portfolioId = $("#TrdOutRightRepoTrade002_pltId").val();
        params.calendarId = $("#TrdOutRightRepoTrade002_dealCalId").val();
        params.ccy = $("#TrdOutRightRepoTrade002_ccy").val();
        params.tradeDate = $("#TrdOutRightRepoTrade002_tradeDate").val();
        params.finprodId = $("#TrdOutRightRepoTrade002_finprodId").val();
		// <!-- 通道交易 新增 强成西 2019/03/26 start -->
        params.chlId = $("#chlId").val();
        params.secAcctId = $("#TrdOutRightRepoTrade002_secAcctId").val();
		// <!-- 通道交易 新增 强成西 2019/03/26 end -->
		params.multiParam = 100;//资产可用持仓和债券保持一致，显示数量
        if (window.BussTrdAvailableCashQuery && window.BussTrdAvailableCashQuery["bussTrdOutRightRepoTrade"]) {
			window.BussTrdAvailableCashQuery["bussTrdOutRightRepoTrade"].updateCash(function () {
				return params
			});
			params.acctType = ACCT_TYPE_A01;
			window.BussTrdAvailableCashQuery["bussTrdOutRightRepoTrade"].updateSecAcctBalance(function () {
				return params
			});
		}
    };

    /**
     * 交易方向改变时
     */
    var psChangeAction = function () {
        var ps = $("#TrdOutRightRepoTrade002_ps").val();
        refreshCashAndBalance();
        // 当交易方向是逆回购时
        if (ps == BACK_LEND_DIRECTION_NEGATIVE) {
            // 组合可用现金、托管户剩余现金显示，自动带出，不可修改，允许透支，计算规则见通用规则
            window.BussTrdAvailableCashQuery["bussTrdOutRightRepoTrade"].showCash();
            window.BussTrdAvailableCashQuery["bussTrdOutRightRepoTrade"].hidePosition();
        }
        // 当交易方向为正回购时
        else if (ps == BACK_LEND_DIRECTION_FORWARD) {
            // 组合可用现金隐藏
            window.BussTrdAvailableCashQuery["bussTrdOutRightRepoTrade"].hideCash();
            window.BussTrdAvailableCashQuery["bussTrdOutRightRepoTrade"].showPosition();
        }
    };
    
    /**
     * 券面总额改变时
     * 可修改券面总额，修改后，同步调整首次结算金额和到期结算金额
     * 首次结算金额=券面总额*首次全价/100
     * 到期结算金额=券面总额*到期全价/100
     */
    var secTotalPrinChangeAction = function(){
    	calc3();
    	calc4();
    };
    
    /**
     * 首次全价=首次净价+首次应计利息；
     */
    var calc1 = function(){
    	var cleanPrice = $('#TrdOutRightRepoTrade002_fCleanPrice').val();// 首次净价
    	var intAmt = $('#TrdOutRightRepoTrade002_fIntAmt').val();// 首次应计利息
    	var fullPrice = JoyinMath.doAdd(cleanPrice, intAmt);// 首次全价=首次净价+首次应计利息；
    	$('#TrdOutRightRepoTrade002_fFprice').val(fullPrice);
    }
    
    /**
     * 首次全价=首次净价+首次应计利息；
     */
    var calc2 = function(){
    	var cleanPrice = $('#TrdOutRightRepoTrade002_mCleanPrice').val();// 首次净价
    	var intAmt = $('#TrdOutRightRepoTrade002_mSettleIntAmt').val();// 首次应计利息
    	var fullPrice = JoyinMath.doAdd(cleanPrice, intAmt);// 首次全价=首次净价+首次应计利息；
    	$('#TrdOutRightRepoTrade002_mFprice').val(fullPrice);
    }
    
    /**
     * 首次结算金额=券面总额*首次全价/100
     */
    var calc3 = function(){
    	var secTotalPrin = $('#TrdOutRightRepoTrade002_secTotalPrin').val()*10000;
    	var fFprice = $('#TrdOutRightRepoTrade002_fFprice').val();
    	if (fFprice && fFprice != '' && secTotalPrin && secTotalPrin != ''){
    		$('#TrdOutRightRepoTrade002_fSettleAmt').val(JoyinMath.doDiv(JoyinMath.doMul(secTotalPrin, fFprice), 100)).trigger('change').trigger('blur');
    	}else{
    		$('#TrdOutRightRepoTrade002_fSettleAmt').val('').trigger('change');
    	}
    	
    };
    
    /**
     * 到期结算金额=券面总额*到期全价/100
     */
    var calc4 = function(){
    	var secTotalPrin = $('#TrdOutRightRepoTrade002_secTotalPrin').val()*10000;
    	var mFprice = $('#TrdOutRightRepoTrade002_mFprice').val();
    	if (mFprice && mFprice != '' && secTotalPrin && secTotalPrin != ''){
    		$('#TrdOutRightRepoTrade002_mSettleAmt').val(JoyinMath.doDiv(JoyinMath.doMul(secTotalPrin, mFprice, 8), 100, 8)).trigger('change').trigger('blur');
    	}else{
    		$('#TrdOutRightRepoTrade002_mSettleAmt').val('').trigger('change');
    	}
    	
    };
 
    /**
     * 计算买断式回购利息
     */
    var calcBuyOutRepoImt = function(settleAmt){
    	var repoTerm = $('#TrdOutRightRepoTrade002_actAmtDays').val();//到期日-起息日
    	var repoRate = $('#TrdOutRightRepoTrade002_repoRate').val();// 回购利率
    	var basis = $('#TrdOutRightRepoTrade002_basis').val();// 计息基础
    	if (basis === BASIS_360){
    		basis = 360;
    	}else{
    		basis = 365;
    	}
		return JoyinMath.doDiv(JoyinMath.doMul(JoyinMath.doMul(settleAmt, repoTerm, 8), JoyinMath.doDiv(repoRate, 100, 8), 8), basis, 8);//回购区间利息=本金金额*（到期日-起息日）/回购交易计息基础*回购利率；
    }
    
    var calcClickAction = function(){
        //首次收益率%、到期收益率%参考债券交易到期收益率的计算方法
    	var paramObj = getcheckdata();
		var calcModel = $('#TrdOutRightRepoTrade002_calcModel').val();
		var calculateField = $("[name=calculateField]:checked").val();
        if(calcModel==CALC_MODEL_01){
			Joyin.ajaxJson(JoyinUtil.getRealPath('/invest/trade/buyoutrepo/TrdOutRightRepoTrade002/calculateDataCheck'), paramObj, function (result) {
				//输入首次净价
				paramObj.unitCprice = $('#TrdOutRightRepoTrade002_fCleanPrice').val();
				paramObj.settleDate = $('#TrdOutRightRepoTrade002_vdate').val();//首次应计利息的计算方法参考债券银行间交易的应计利息计算方法，交割日=起息日；
				Joyin.ajaxJson(JoyinUtil.getRealPath('/invest/trade/buyoutrepo/TrdOutRightRepoTrade002/calculateData'), paramObj, function (result) {
					if (result && result.data) {
						var data = result.data;
						$("#TrdOutRightRepoTrade002_fIntAmt").val(data.unitInt).trigger("blur");//首次应计利息
						$("#TrdOutRightRepoTrade002_fFprice").val(data.unitFprice).trigger("blur");//首次全价
						$("#TrdOutRightRepoTrade002_fYield").val(data.yield).trigger("blur");//首次收益率%
						//首次结算金额
						var fSettleAmt = JoyinMath.doDiv(JoyinMath.doMul(paramObj.tradeAmt, data.unitFprice, 12), 100, 8);//首次结算金额=券面总额*首次全价/100
						$('#TrdOutRightRepoTrade002_fSettleAmt').val(fSettleAmt).trigger('change').trigger('blur');
					}
				},false);
				//输入到期净价
				paramObj.unitCprice = $('#TrdOutRightRepoTrade002_mCleanPrice').val();
				paramObj.settleDate = $('#TrdOutRightRepoTrade002_mdate').val();//到期应计利息的计算方法参考债券交易的应计利息计算方法，交割日=到期日
				Joyin.ajaxJson(JoyinUtil.getRealPath('/invest/trade/buyoutrepo/TrdOutRightRepoTrade002/calculateData'), paramObj, function (result) {
					if (result && result.data) {
						var data = result.data;
						$("#TrdOutRightRepoTrade002_mSettleIntAmt").val(data.unitInt).trigger("blur");
						$("#TrdOutRightRepoTrade002_mFprice").val(data.unitFprice).trigger("blur");
						$("#TrdOutRightRepoTrade002_mYield").val(data.yield).trigger("blur");
						//到期结算金额
						var mSettleAmt = JoyinMath.doDiv(JoyinMath.doMul(paramObj.tradeAmt, data.unitFprice, 12), 100, 8);
						$('#TrdOutRightRepoTrade002_mSettleAmt').val(mSettleAmt).trigger('change').trigger('blur');//到期结算金额=券面总额*到期全价/100
					}
				},false);
				//计算回购利率
				paramObj.fSettleAmt = $('#TrdOutRightRepoTrade002_fSettleAmt').val();
				paramObj.mSettleAmt = $('#TrdOutRightRepoTrade002_mSettleAmt').val();
				Joyin.ajaxSync(basePath + 'invest/trade/buyoutrepo/TrdOutRightRepoTrade002/calRepoRate', paramObj, function (data) {
					$("#TrdOutRightRepoTrade002_repoRate").val(data).trigger("blur");//回购利率
					window.CalculateFieldCheckComm.getparam(window.CalculateFieldCheckComm.deepCopy(getcheckdata()));
				});
			},false)
        }
        else{
        	if(calcModel == CALC_MODEL_03) {
				paramObj.settleDate = $('#TrdOutRightRepoTrade002_vdate').val();
			}
        	else{
				paramObj.settleDate = $('#TrdOutRightRepoTrade002_mdate').val();
			}
			Joyin.ajaxJson(JoyinUtil.getRealPath('/invest/trade/buyoutrepo/TrdOutRightRepoTrade002/calculateData'), paramObj, function (result) {
				if (result && result.data) {
					var data = result.data;
					// 首期净价
					if(calcModel == CALC_MODEL_03) {
						/*if (calculateField == 'fCleanPrice'){*/
						calOtherByFCprice(data,paramObj);
					}
					// 到期净价
					else if(calcModel == CALC_MODEL_02) {
						/*else if (calculateField == 'mCleanPrice'){*/
						calOtherByMCprice(data,paramObj);
					}
//                // 记录隐藏值
//                $("#unitCprice_calc").val(data.unitCprice);
//                $("#unitInt_calc").val(data.unitInt);
//                $("#unitFprice_calc").val(data.unitFprice);
//                $("#cpriceAmt_calc").val(data.cpriceAmt);
//                $("#intAmt_calc").val(data.intAmt);
//                $("#fpriceAmt_calc").val(data.fpriceAmt);
//                $("#exerYield_calc").val(data.exerYield);
//                $("#yield_calc").val(data.yield);
				}
			},false);
		}
		// add by wangguoxian 费用设置 20190722 start
		calFee();
		// add by wangguoxian 费用设置 20190722 end
    };

    //add  by  zhangnian 20200106 start
	//通过首次净价计算其他指标
	var calOtherByFCprice = function(data,paramObj){
		$("#TrdOutRightRepoTrade002_fIntAmt").val(data.unitInt).trigger("blur");//首次应计利息
		$("#TrdOutRightRepoTrade002_fFprice").val(data.unitFprice).trigger("blur");//首次全价
		$("#TrdOutRightRepoTrade002_fYield").val(data.yield).trigger("blur");//首次收益率%

		// 计算其他项目
		var fSettleAmt = JoyinMath.doDiv(JoyinMath.doMul(paramObj.tradeAmt, data.unitFprice, 12), 100, 8);//首次结算金额=券面总额*首次全价/100
		$('#TrdOutRightRepoTrade002_fSettleAmt').val(fSettleAmt).trigger('change').trigger('blur');

		var repoRangeIntAmt = calcBuyOutRepoImt(fSettleAmt);//计算买断式回购利息
		fSettleAmt = $('#TrdOutRightRepoTrade002_fSettleAmt').val();//本金金额取页面金额，四舍五入之后值。
		var mSettleAmt = JoyinMath.doAdd(fSettleAmt, repoRangeIntAmt, 8);//到期结算金额=本金金额+回购区间利息；
		$('#TrdOutRightRepoTrade002_mSettleAmt').val(mSettleAmt).trigger('blur');

		// 根据全价总额计算到期其他项目
		paramObj.calculateField = 'fpriceAmt';
		paramObj.fpriceAmt = mSettleAmt;
		paramObj.settleDate = $('#TrdOutRightRepoTrade002_mdate').val();
		Joyin.ajaxJson(JoyinUtil.getRealPath('/invest/trade/buyoutrepo/TrdOutRightRepoTrade002/calculateData'), paramObj, function (result2) {
			if (result2 && result2.data) {
				$("#TrdOutRightRepoTrade002_mCleanPrice").val(result2.data.unitCprice).trigger("blur");//到期净价
				$("#TrdOutRightRepoTrade002_mSettleIntAmt").val(result2.data.unitInt).trigger("blur");//到期应计利息
				$("#TrdOutRightRepoTrade002_mFprice").val(result2.data.unitFprice).trigger("blur");//到期全价
				$("#TrdOutRightRepoTrade002_mYield").val(result2.data.yield).trigger("blur");//到期收益率%
			}
			window.CalculateFieldCheckComm.getparam(window.CalculateFieldCheckComm.deepCopy(getcheckdata()));
		});
	}
	//通过到期净价计算其他指标
	var calOtherByMCprice = function(data,paramObj){
		$("#TrdOutRightRepoTrade002_mSettleIntAmt").val(data.unitInt).trigger("blur");
		$("#TrdOutRightRepoTrade002_mFprice").val(data.unitFprice).trigger("blur");
		$("#TrdOutRightRepoTrade002_mYield").val(data.yield).trigger("blur");

		// 计算其他项目
		var mSettleAmt = JoyinMath.doDiv(JoyinMath.doMul(paramObj.tradeAmt, data.unitFprice, 12), 100, 8);
		$('#TrdOutRightRepoTrade002_mSettleAmt').val(mSettleAmt).trigger('change').trigger('blur');//到期结算金额=券面总额*到期全价/100

		var repoRangeIntAmt = calcBuyOutRepoImt(mSettleAmt);//计算买断式回购利息
		var fSettleAmt = JoyinMath.doAdd(mSettleAmt, -1 * repoRangeIntAmt);//到期结算金额=本金金额+回购区间利息；
		$('#TrdOutRightRepoTrade002_fSettleAmt').val(fSettleAmt).trigger('change').trigger('blur');

		// 根据全价总额计算到期其他项目
		paramObj.calculateField = 'fpriceAmt';
		paramObj.fpriceAmt = fSettleAmt;
		paramObj.settleDate = $('#TrdOutRightRepoTrade002_vdate').val();
		Joyin.ajaxJson(JoyinUtil.getRealPath('/invest/trade/buyoutrepo/TrdOutRightRepoTrade002/calculateData'), paramObj, function (result2) {
			if (result2 && result2.data) {
				$("#TrdOutRightRepoTrade002_fCleanPrice").val(result2.data.unitCprice).trigger("blur");//到期净价
				$("#TrdOutRightRepoTrade002_fIntAmt").val(result2.data.unitInt).trigger("blur");//到期应计利息
				$("#TrdOutRightRepoTrade002_fFprice").val(result2.data.unitFprice).trigger("blur");//到期全价
				$("#TrdOutRightRepoTrade002_fYield").val(result2.data.yield).trigger("blur");//到期收益率%
			}
			window.CalculateFieldCheckComm.getparam(window.CalculateFieldCheckComm.deepCopy(getcheckdata()));
		});
	}
	//add  by  zhangnian 20200106 start
    /**
     * 根据交易方向，确定资产类型：金融产品类型_投管用_债券质押式正回购、金融产品类型_投管用_债券质押式逆回购
     * @returns
     */
    function getFinProdType2FromPs() {
        return ($("#TrdOutRightRepoTrade002_ps").val() == null || $("#TrdOutRightRepoTrade002_ps").val() == "") ? "" : ($("#TrdOutRightRepoTrade002_ps").val() == BACK_LEND_DIRECTION_FORWARD ? FINPROD_TYPE_TG_F04 : FINPROD_TYPE_TG_F05);
    };
    
    var getcheckdata = function () {
		var calcModel = $('#TrdOutRightRepoTrade002_calcModel').val();
        var paramObj = {vdate:$("#TrdOutRightRepoTrade002_vdate").val(),mdate:$("#TrdOutRightRepoTrade002_mdate").val(),repRate:$("#TrdOutRightRepoTrade002_repoRate").val(),settleSpeed:$("#TrdOutRightRepoTrade002_settleSpeed").val(),
				basis:$("#TrdOutRightRepoTrade002_basis").val(),termType:$("#TrdOutRightRepoTrade002_termType").val(),repoterm:$("#TrdOutRightRepoTrade002_repoterm").val(),
				tradeMarket:$("#TrdOutRightRepoTrade002_tradeMarket").val()}
		paramObj.repoCalculateField = $("[name=calculateField]:checked").val();
		paramObj.fCleanPrice = $('#TrdOutRightRepoTrade002_fCleanPrice').val();
		paramObj.mCleanPrice = $('#TrdOutRightRepoTrade002_mCleanPrice').val();
		paramObj.repoVdate = $('#TrdOutRightRepoTrade002_vdate').val();
		paramObj.repoMdate = $('#TrdOutRightRepoTrade002_mdate').val();
		paramObj.repoRate = $('#TrdOutRightRepoTrade002_repoRate').val();

		paramObj.portfolioId = $('#TrdOutRightRepoTrade002_pltId').val();
		paramObj.finprodId = $('#TrdOutRightRepoTrade002_finprodId').val();
		paramObj.tradeAmt = $('#TrdOutRightRepoTrade002_secTotalPrin').val()*10000;
		paramObj.invAim = INV_AIM_TC;
		paramObj.tradeMarketMode = TRADE_PLACE_SEC;
		paramObj.calculateField = 'unitCprice';
        paramObj.entrustId = $('#TrdOutRightRepoTrade002_entrustId').val();
		paramObj.calcModel = calcModel;
		//var calculateField = $("[name=calculateField]:checked").val();
		/*if (calculateField == 'fCleanPrice'){
			paramObj.unitCprice = $('#TrdOutRightRepoTrade002_fCleanPrice').val();
			paramObj.settleDate = $('#TrdOutRightRepoTrade002_vdate').val();//首次应计利息的计算方法参考债券银行间交易的应计利息计算方法，交割日=起息日；
		}else if (calculateField == 'mCleanPrice'){
			paramObj.unitCprice = $('#TrdOutRightRepoTrade002_mCleanPrice').val();
			paramObj.settleDate = $('#TrdOutRightRepoTrade002_mdate').val();//到期应计利息的计算方法参考债券交易的应计利息计算方法，交割日=到期日
		}*/
		if(calcModel == CALC_MODEL_02){
			paramObj.unitCprice = $('#TrdOutRightRepoTrade002_mCleanPrice').val();
			paramObj.settleDate = $('#TrdOutRightRepoTrade002_mdate').val();//到期应计利息的计算方法参考债券交易的应计利息计算方法，交割日=到期日
		}
		else if(calcModel == CALC_MODEL_03){
			paramObj.unitCprice = $('#TrdOutRightRepoTrade002_fCleanPrice').val();
			paramObj.settleDate = $('#TrdOutRightRepoTrade002_vdate').val();//首次应计利息的计算方法参考债券银行间交易的应计利息计算方法，交割日=起息日；
		}
		//add by zhangniann 20200106  start
		paramObj.calendarId = $('#TrdOutRightRepoTrade002_dealCalId').val();
		paramObj.secTotalPrin = $('#TrdOutRightRepoTrade002_secTotalPrin').val()*10000;
		paramObj.mdate = $('#TrdOutRightRepoTrade002_mdate').val();
		//add by zhangnian  20200106  end
        return paramObj;
    }
    
    window.componentLoadCallback = function (isDone, pagePath, params) {
        if (isDone) {
        	
        	// 刷新托管户剩余现金、组合T日可用现金、组合T+1日可用现金
        	psChangeAction();
            var riskparam = getriskdata();

        	var checkparam = getcheckdata();
            if (pageModel==PAGE_MODEL_NEW){
            	checkparam =null;
                riskparam.data =null;
                window.BaseRiskMathComm.getparam(riskparam);
            }
            else {
                //window.BaseRiskMathComm.initBaseRiskMathCommPage(riskparam);
            }
            window.CalculateFieldCheckComm.getparam(checkparam);
            
            window.BussTrdTransferOrder['trdOutRightRepoTradeOrder'].setStlCommandInput(function () {
            	// 首次全价不能为空
            	if (!$("#TrdOutRightRepoTrade002_fFprice").val() || typeof $("#TrdOutRightRepoTrade002_fFprice").val() == 'undefined'){
            		Joyin.error(JoyinUtil.getMessage('FW_E_0006',[JoyinUtil.getLabel("TrdOutRightRepoTrade002.amtInfo.firstFullPrice")]));
            		return null;
            	}
            	// 券面总额不能为空
            	if (!$("#TrdOutRightRepoTrade002_secTotalPrin").val() || typeof $("#TrdOutRightRepoTrade002_secTotalPrin").val() == 'undefined'){
            		Joyin.error(JoyinUtil.getMessage('FW_E_0006',[JoyinUtil.getLabel("TrdOutRightRepoTrade002.amtInfo.bondTotalAmt")]));
            		return null;
            	}
            	// 交易方向不能为空
            	if (!$("#TrdOutRightRepoTrade002_ps").val() || typeof $("#TrdOutRightRepoTrade002_ps").val() == 'undefined'){
            		Joyin.error(JoyinUtil.getMessage('FW_E_0006',[JoyinUtil.getLabel("TrdOutRightRepoTrade002.ps")]));
            		return null;
            	}
            	
                var dealObj = {};
                dealObj.finprodType2 = getFinProdType2FromPs();
                dealObj.settleDate = $("#TrdOutRightRepoTrade002_tradeDate").val();
                dealObj.tradeDate = $("#TrdOutRightRepoTrade002_tradeDate").val();
                dealObj.ps = PS_T01;
                dealObj.ccy = $("#TrdOutRightRepoTrade002_ccy").val();
				dealObj.tradeAmt = $("#tradeAmtAndFee").val();
				dealObj.chlId = $("#chlId").val();
                dealObj.counterId = $("#trdOutRightRepoPersonInfo_counterId").val();
                dealObj.tradeMarket = $("#TrdOutRightRepoTrade002_tradeMarket").val();
                dealObj.secManageAcctId = $("#TrdOutRightRepoTrade002_secAcctId").val();
                dealObj.branch = noBranchNoValue;
                return {
                    'isTotalTrade': YES_OR_NO_N
                    , 'bankLendingType': null
                    , 'portfolioId': $("#TrdOutRightRepoTrade002_pltId").val()
                    , 'deal': dealObj
                };
            });

            if (formTodoList) {
                window.BussTrdTransferOrder['trdOutRightRepoTradeOrder'].saveCalcCondition();
            }

            // 设置默认选中首次净价
            $("#calculateField").attr("checked", true);
            layui.form.render('radio');
        }
        // add by 王国显 费用设置 20190722 start
        $('#TrdOutRightRepoTrade002_tradeMarket').trigger('change');
        // add by 王国显 费用设置 20190722 end
		/*$('#trdInvestPersonInfo_counterId').on("change",function(){
			var counterId = $('#trdInvestPersonInfo_counterId');
			if (counterId.select2('data') && counterId.select2('data')[0]) {
				var 交易对手 = counterId.select2('data')[0].object;
				// 【债券投资委托】交易对手选择内部交易对手时，清算速度为T+0不可改
				if (交易对手 == 'N') {
					if (pageModel != PAGE_MODEL_REFERENCE) {
						$('#settleSpeed').val("01").prop("disabled", true).trigger("change");
					}
				} else {
					if (pageModel != PAGE_MODEL_REFERENCE) {
						$('#settleSpeed').prop("disabled", false).trigger("change");
					}
				}
			}
		});*/
    };
    
    function saveOrSubmit(operateType) {
    	// 划款指令隐藏时，自动生成划款指令信息
    	generateOrders(function () {
    		var s;
			if(BaseRiskMathComm) {
				s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
				if(!s && s != ''){
					return;
				}
				if (window.BaseRiskMathComm.isCalcConditionChanged(getriskdata())){
					return;
				}
			}
			// 检查计算参数是否发生变化
			if (window.CalculateFieldCheckComm.isCalcConditionChanged(getcheckdata())){
				return;
			}
	
	        // 检查生成划款指令参数是否发生变化
	        if (window.BussTrdTransferOrder['trdOutRightRepoTradeOrder'].isCalcConditionChanged()) {
	            return;
	        }
			if($('#dealBussId').val()&&$('#TrdOutRightRepoTrade002_secTotalPrin').val()){
				var isTrue = false;
				var params = {};
				params.fTradeId = $('#dealBussId').val();
				params.tradeAmt = $('#TrdOutRightRepoTrade002_secTotalPrin').val();
				params.tradeType = 'BUYOUT';
				params.workflowId = $('#workflowId').val();
				params.ps = $('#ps').val();
				Joyin.ajaxJson(JoyinUtil.getRealPath('/invest/trade/trdrepotrade/TrdRepoTrade002/isAllTradeAmtByFTradeId'), params, function (result) {
					if(result&&!result.data){
						isTrue = true;
						Joyin.error("成交编号获得的劵面总额不得大于已存在的劵面总额！");
					}
				},false);
				if(isTrue){
					return;
				}
			}
			//交易中回购期限应在委托期限范围内
			var entrustId = $('#TrdOutRightRepoTrade002_entrustId').val();
	    	if (entrustId && entrustId != ''){
	    	    var entrustTermEnd=$("#TrdOutRightRepoTrade002_entrustInfo_entrustTermEnd").val()-0;
	            var entrustTermStart=$("#TrdOutRightRepoTrade002_entrustInfo_entrustTermStart").val()-0;
	            var repoterm=$("#TrdOutRightRepoTrade002_repoterm").val()-0;
	            if(entrustTermStart>repoterm||entrustTermEnd<repoterm){
	    			Joyin.error("请输入合理的回购期限！");
	    			return;
	    		}
	    	}
	
	        var object = $.serializeToObject('#TrdOutRightRepoTrade002_submitForm', true);
	        object.pageModel = pageModel;
	        object.operateType = operateType;
	        object.isGuarantee = $('#TrdOutRightRepoTrade002_pltId').select2('data')[0].object; // 是否保本
	        if (FileCommon) {
	            object.uploadFileList = FileCommon.getUploadFileInfo();
	        }
	
	        // 资产可用持仓
	        object.assetPosition = $("#bussTrdOutRightRepoTrade_assetPosition").val();
			object.secTotalPrin = JoyinMath.doMul(object.secTotalPrin, 10000, 2);
			if (object.secTotalPrin == "NaN") {
				object.secTotalPrin = 0;
			}
			var data = $("#chlId").find("option:selected").data("data");
			if (data && data.object) {
				object.payType = data.object;
			}
			object.inputValue1 = PINAN_ASSET_TYPE_F10;
			object.inputValue2 = $('#ps').val(); 
			if ($('#trdOutRightRepoPersonInfo_counterId').select2('data') && $('#trdOutRightRepoPersonInfo_counterId').select2('data')[0]) {
	            object.counterId=$('#trdOutRightRepoPersonInfo_counterId').select2('data')[0].id;
	        }
			object.prinAmt = object.secTotalPrin;
			object.finprodType2 = getFinProdType2FromPs();
			Joyin.ajaxJson(basePath + 'invest/trade/TrdCommonController/check', object, function(result1) {
				Joyin.ajaxJson(basePath + 'invest/trade/buyoutrepo/TrdOutRightRepoTrade002/check', object, function (result) {
					Joyin.confirm(result.data, function () {
						//授信占用额度
//		                Joyin.ajaxJson(basePath + 'invest/trade/TrdCommonController/delLinesDeal',result1.data,function(result2) {});
						object.riskCreditListCommonDto = result1.data;
						if (operateType === 'save') {
							WorkFlow.doSaveList(basePath + 'invest/trade/buyoutrepo/TrdOutRightRepoTrade002/save', object, "TrdOutRightRepoTrade002_btn_save");
						} else {
							WorkFlow.doSubmitList(basePath + 'invest/trade/buyoutrepo/TrdOutRightRepoTrade002/save', object, "TrdOutRightRepoTrade002_btn_submit");
						}
					});
				});
			});
    	});	
	};
    
    var settleSpeedChangeAction = function(){
    	var settleSpeed = $('#TrdOutRightRepoTrade002_settleSpeed').val();
    	var tradeDate = $('#TrdOutRightRepoTrade002_tradeDate').val();
    	if (settleSpeed && settleSpeed != '' && tradeDate && tradeDate != ''){
    		var num = 0;
    		if (settleSpeed === SETTLESPEED_1){
    			num = 1;
    		}else if(settleSpeed === SETTLESPEED_2){
    			num =2;
    		}
    			
    		//$('#TrdOutRightRepoTrade002_vdate').val(DateUtil.parse(tradeDate).DateAdd('d', num).Format('yyyy-MM-dd')).trigger('change');
            //推算起息日  判断是否节假日 是则顺延
			var vdate = DateUtil.parse(tradeDate).DateAdd('d', num).Format('yyyy-MM-dd');
            $("#TrdOutRightRepoTrade002_vdate").val(vdate).trigger('change');
            var calendarId = $("#TrdOutRightRepoTrade002_dealCalId").val();
            var dealBussId = $('#dealBussId').val();
            if(vdate&&calendarId){
                Joyin.ajaxJson(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade002/isDealCalendarHoliday', {
                    calendarId: calendarId,
                    mpayDate: vdate
                }, function (date) {
                    if(date){
                        Joyin.ajaxSync(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade002/getNextWorkDay', {
                            'calendarId': calendarId,
                            'tradeDate': vdate
                        }, function (date) {
                            $("#TrdOutRightRepoTrade002_vdate").val(date).trigger('change');
                        });
                    }
                },false);
            }
    	}
    	
    }  

    var tradeMarketChange = function () {
		if (TRADE_MARKET_01 == $("#TrdOutRightRepoTrade002_tradeMarket").val()) {
			//新建的时候需要联动，流程中以及产看，修改根据实际值带出来add by xueying 20200721 start
			if(pageModel == PAGE_MODEL_NEW){
				$("#TrdOutRightRepoTrade002_settleSpeed").val(SETTLESPEED_0).trigger('change')
			}
			//新建的时候需要联动，流程中以及产看，修改根据实际值带出来add by xueying 20200721 end
		}
		else {
			$("#TrdOutRightRepoTrade002_settleSpeed").prop("disabled", false).trigger('change')
		}
	}

    var init = function(){
    	if(pageModel == PAGE_MODEL_REFERENCE&&!$('#workflowId').val()){
			$('#calcModel_div').hide();
		}
    	$('#TrdOutRightRepoTrade002_termType').attr('disabled',true);
    	var entrustId = $('#TrdOutRightRepoTrade002_entrustId').val();
    	if (entrustId && entrustId != ''){
    		$('#TrdOutRightRepoTrade002_entrustId').attr('disabled',true);
    		$('#TrdOutRightRepoTrade002_pltId').attr('disabled',true);
    		$('#TrdOutRightRepoTrade002_tradeMarket').attr('disabled',true);
    		$('#TrdOutRightRepoTrade002_ps').attr('disabled',true);
    		$('#TrdOutRightRepoTrade002_finprodId').attr('disabled',true);
    		$('#TrdOutRightRepoTrade002_finprodName').attr('disabled',true);
    		$('#TrdOutRightRepoTrade002_settleSpeed').attr('disabled',true);
    		$('#TrdOutRightRepoTrade002_dealCalId').attr('disabled',true);
    		$('#TrdOutRightRepoTrade002_ccy').attr('disabled',true);
    		
			if($('#TrdOutRightRepoTrade002_entrustInfo_entrustVdate').val() && $('#TrdOutRightRepoTrade002_entrustInfo_entrustVdate').val() != "" ){
    			$('#TrdOutRightRepoTrade002_vdate').attr('disabled',true);
    		}
    		if($('#TrdOutRightRepoTrade002_entrustInfo_entrustMdate').val() && $('#TrdOutRightRepoTrade002_entrustInfo_entrustMdate').val() != "" ){
    			$('#TrdOutRightRepoTrade002_mdate').attr('disabled',true);
    		}
    		if ($('#TrdOutRightRepoTrade002_entrustInfo_entrustVdate').val() && $('#TrdOutRightRepoTrade002_entrustInfo_entrustVdate').val() != "" 
    				&& $('#TrdOutRightRepoTrade002_entrustInfo_entrustMdate').val() && $('#TrdOutRightRepoTrade002_entrustInfo_entrustMdate').val() != ""  ){
    			$('#TrdOutRightRepoTrade002_termType').attr('disabled',true);
        		$('#TrdOutRightRepoTrade002_repoterm').attr('disabled',true);
    		}
    		if(!$('#TrdOutRightRepoTrade002_termType').val()){
                $('#TrdOutRightRepoTrade002_termType').attr('disabled',true);
                //委托时 是否有回购期限品种  没有时 联动带出
                repoTermChangeAction();
			}
		}

		$("#TrdOutRightRepoTrade002_tradeMarket").change(tradeMarketChange);
//		 tradeMarketChange();
    }
    /**
     * 推算当月天数（传入起息日 yyyy-MM-dd
     */
    function getNumDate(date) {
    	//date为空时  返回0
    	if(date){
    		//获取日期
    		date =DateUtil.parse(date);
    		//获取之后月00天 即本月最后一天
    		return new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+1,0).getDate();
    	}
    	return 0;
    }
    
    /**
     * 获取期限品种  date(yyyy-MM-dd),repoterm
     */
    function getTermType(date,repoterm) {
    	if(repoterm&&repoterm<=21){
    		//期限品种小于21天
    		if(repoterm==1){
    			return REPO_TYPE_R001;
    		}
    		if(repoterm>1&&repoterm<=7){
    			return REPO_TYPE_R007;
    		}
    		if(repoterm>7&&repoterm<=14){
    			return REPO_TYPE_R014;
    		}
    		if(repoterm>14&&repoterm<=21){
    			return REPO_TYPE_R021;
    		}
    	}
    	var day=0;
    	//date为空时  返回0
    	if(date&&repoterm){
    		//获取日期
    		date =DateUtil.parse(date);
    		//获取之后月00天 即本月最后一天
    		day= new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+1,0).getDate();
    		if(repoterm<=day){
    			return REPO_TYPE_R1M;
    		}
    		day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+2,0).getDate();
    		if(repoterm<=day){
    			return REPO_TYPE_R2M;
    		}
    		day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+3,0).getDate();
    		if(repoterm<=day){
    			return REPO_TYPE_R3M;
    		}
    		day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+4,0).getDate();
    		if(repoterm<=day){
    			return REPO_TYPE_R4M;
    		}
    		day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+5,0).getDate();
    		day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+6,0).getDate();
    		if(repoterm<=day){
    			return REPO_TYPE_R6M;
    		}
    		day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+7,0).getDate();
    		day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+8,0).getDate();
    		day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+9,0).getDate();
    		if(repoterm<=day){
    			return REPO_TYPE_R9M;
    		}
    		day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+10,0).getDate();
    		day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+11,0).getDate();
    		day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+12,0).getDate();
    		if(repoterm<=day){
    			return REPO_TYPE_R1Y;
    		}
    	}
    	return null;
    }

	/**
	 * 计算模式change事件
	 */
	var calcModelChange = function(){
    	var calcModel = $('#TrdOutRightRepoTrade002_calcModel').val();
		if(calcModel == CALC_MODEL_01){
			$('#TrdOutRightRepoTrade002_repoRate').attr('disabled',true);
			$('#TrdOutRightRepoTrade002_fCleanPrice').attr('disabled',false);
			$('#TrdOutRightRepoTrade002_mCleanPrice').attr('disabled',false);
		}
		else if(calcModel == CALC_MODEL_02){
			$('#TrdOutRightRepoTrade002_repoRate').attr('disabled',false);
			$('#TrdOutRightRepoTrade002_fCleanPrice').attr('disabled',true);
			$('#TrdOutRightRepoTrade002_mCleanPrice').attr('disabled',false);
		}
		else if(calcModel == CALC_MODEL_03){
			$('#TrdOutRightRepoTrade002_repoRate').attr('disabled',false);
			$('#TrdOutRightRepoTrade002_fCleanPrice').attr('disabled',false);
			$('#TrdOutRightRepoTrade002_mCleanPrice').attr('disabled',true);
		}
		else{
			$('#TrdOutRightRepoTrade002_repoRate').attr('disabled',true);
			$('#TrdOutRightRepoTrade002_fCleanPrice').attr('disabled',true);
			$('#TrdOutRightRepoTrade002_mCleanPrice').attr('disabled',true);
		}
	}
	//add by lizhi 2020-2-26 划款指令是否自动生成  start
    var generateOrders = function (callback) {
    	// 如果还款指令设置为隐藏，则自动生成划款指令
    	if(GEN_PAY_ORDERS_AUTO =='01'){
    		$('#trdOutRightRepoTradeOrder_btn_generate').click();
    	}
    	// 回调方法
	    if (callback && typeof callback === 'function') {
	        callback();
	    }
    }
    //add by lizhi 2020-2-26 划款指令是否自动生成  end
	// 画面加载完成
	$(function(){
		// 初始化画面
		init();
		// 是否显示划款指令
        if(GEN_PAY_ORDERS_AUTO=='01'){
        	$('#bussTrdTransferOrder_div').hide() ;
        }else{
        	$('#bussTrdTransferOrder_div').show() ;
        }
		/**
		 *计算模式
		 */
		$('#TrdOutRightRepoTrade002_calcModel').on('change',function (){
			calcModelChange();
		});
		// 根据债券代码联动显示债券名称
		secIdChangeAction();
		$('#TrdOutRightRepoTrade002_finprodId').on('change',function(){
			secIdChangeAction();
		});
		
		/*// 期限品种
		$('#TrdOutRightRepoTrade002_termType').change(function(){
			termTypeChangeAction();
		});*/
		
		// 回购期限
		$('#TrdOutRightRepoTrade002_repoterm').on('change',function(){
			repoTermChangeAction();
		});
		
		// 画面到期日-画面.起息日联动实际占款天数
		$('#TrdOutRightRepoTrade002_vdate').on('change',function(){
			vdateChangeAction();			 
		});
		
		// 画面到期日-画面.起息日联动实际占款天数
		$('#TrdOutRightRepoTrade002_mdate').on('change',function(){
			vdateMdateChangeAction();
		});
		// 交易日历
        $('#TrdOutRightRepoTrade002_dealCalId').on('change',function(){
        	vdateMdateChangeAction();
            settleSpeedChangeAction();
		});
		
		// 清算速度
		$('#TrdOutRightRepoTrade002_settleSpeed').on('change',function(){
			settleSpeedChangeAction();
		});
		
		// 刷新托管户剩余现金、组合T日可用现金、组合T+1日可用现金
        $('#TrdOutRightRepoTrade002_pltId, #TrdOutRightRepoTrade002_dealCalId, #TrdOutRightRepoTrade002_ccy, #TrdOutRightRepoTrade002_finprodId').on("change", function () {
            refreshCashAndBalance();
        });

        // 交易方向
        $('#TrdOutRightRepoTrade002_ps').on('change',function(){
        	psChangeAction();
		});
        
        // 券面总额
        $('#TrdOutRightRepoTrade002_secTotalPrin').on('change',function(){
        	secTotalPrinChangeAction();
		});
        
        // 计算按钮
        $('#TrdOutRightRepoTrade002_btn_calculate').click(function () {
        	calcClickAction();
        });
        
        // 首次全价=首次净价+首次应计利息；
        $('#TrdOutRightRepoTrade002_fCleanPrice, #TrdOutRightRepoTrade002_fIntAmt').on('change',function(){
        	calc1();
		});
        
        // 到期全价=到期净价+到期应计利息；
        $('#TrdOutRightRepoTrade002_mCleanPrice, #TrdOutRightRepoTrade002_mSettleIntAmt').on('change',function(){
        	calc2();
		});
        
        // 首次全价
        $('#TrdOutRightRepoTrade002_fFprice').on('change',function(){
        	calc3();
		});
        
        // 到期全价
        $('#TrdOutRightRepoTrade002_mFprice').on('change',function(){
        	calc4();
		});
        // add by wangguoxian 费用设置 20190722 start
        $('#TrdOutRightRepoTrade002_tradeMarket').on('change',function(){
        	//add by gaopeng 20191112 选择交易场所时清空证券账户  start
			if (pageModel != PAGE_MODEL_REFERENCE&&pageModel!=1) {
				$("#TrdOutRightRepoTrade002_secAcctId").val('').trigger('change');
			}
        	//add by gaopeng 20191112 选择交易场所时清空证券账户  end
            var tradeMarket = $("#TrdOutRightRepoTrade002_tradeMarket").val();
            // 上海证券交易所、深圳证券交易所时
            if (tradeMarket == TRADE_PLACE_02 || tradeMarket == TRADE_PLACE_03) {
                $("#procedureAmtDiv").hide();
                $("#settleAmtDiv").hide();
                $("#commissionAmtDiv").show();
            }
            // 当交易场所是银行间时
            else if (tradeMarket == TRADE_PLACE_01) {
                $("#procedureAmtDiv").show();
                $("#settleAmtDiv").show();
                $("#commissionAmtDiv").hide();
            }
            else {
                $("#procedureAmtDiv").hide();
                $("#settleAmtDiv").hide();
                $("#commissionAmtDiv").hide();
            }
        });

        // add by wangguoxian 费用设置 20190722 end

        // 风险试算


        $('#TrdOutRightRepoTrade002_btn_risk').click(function () {

            //window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
			//-----------add by jjpei 2020-01-07 风险新版本 start----------
			var object = $.serializeToObject('#TrdOutRightRepoTrade002_submitForm', true);
			window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/1/RSK002/F10', object,getriskdata());
			//-----------add by jjpei 2020-01-07 风险新版本 end----------
            window.BaseRiskMathComm.initBaseRiskMathCommPageCus(getriskdata());

        });
        
        // 绑定按钮事件
        $('#TrdOutRightRepoTrade002_btn_save').click(function () {
            saveOrSubmit('save');
        });
        $('#TrdOutRightRepoTrade002_btn_submit').click(function () {
            saveOrSubmit('submit');
        });

		// 银行间成交编号(当交易场所选择银行间时，银行间成交编号可编辑)
		$('#dealBussId').on('change',function(){
			if (pageModel==PAGE_MODEL_NEW) {
				fTradeIdChangeAction();
			}
		});

		/**
		 * 银行间成交编号输入时从cstp查询数据带出
		 */
		var fTradeIdChangeAction = function(){
			var dealBussId = $('#dealBussId').val();

			$('#repoType').attr('disabled', false);
			$('#ps').attr('disabled', false);
			$("#vdate").attr('disabled', false);
			$('#mdate').attr('disabled', false);
			$('#term').attr('disabled', false);
			$('#basis').attr('disabled', false);
			$('#ccy').attr('disabled', false);
			$('#prinAmt').attr('disabled', false);
			$('#rebackPrice').attr('disabled', false);
			$('#trdInvestPersonInfo_counterId').attr('disabled', false);

			if (dealBussId && dealBussId != ''){
				Joyin.ajax(basePath + 'invest/trade/buyoutrepo/TrdOutRightRepoTrade002/queryRepoCstpInfoByNo', {fedealno : dealBussId}, function(result){
					if (result){
						if (result.dealdateStr && result.dealdateStr != ''){
							$('#TrdOutRightRepoTrade002_tradeDate').val(result.dealdateStr).trigger('change');// 交易日期
                            if($('#TrdOutRightRepoTrade002_tradeDate').val()){
                                $('#TrdOutRightRepoTrade002_tradeDate').attr('disabled', true);
                            }
						}else{
							$('#TrdOutRightRepoTrade002_tradeDate').val('');
							$('#TrdOutRightRepoTrade002_tradeDate').attr('disabled', false);
						}
						// if (result.portfolioId && result.portfolioId != ''){
						// 	$('#TrdOutRightRepoTrade002_pltId').textSelect('setValue', result.portfolioId);//组合代码
                        //     if($('#TrdOutRightRepoTrade002_pltId').val()){
                        //         $('#TrdOutRightRepoTrade002_pltId').attr('disabled', true);
                        //     }
						// }else{
						// 	$('#bussTrdOutRightRepoTrade_avalibleCash').val('');
						// 	$('#bussTrdOutRightRepoTrade_avalibleCashT1').val('');
						// 	deleteSelect('TrdOutRightRepoTrade002_pltId');
						// 	$('#TrdOutRightRepoTrade002_pltId').attr('disabled', false);
						// }
						if (result.ourdepositaccountnumber && result.ourdepositaccountnumber != ''){
							$('#TrdOutRightRepoTrade002_secAcctId').textSelect('setValue', result.ourdepositaccountnumber);//证券账户
                            if($('#TrdOutRightRepoTrade002_secAcctId').val()){
                                $('#TrdOutRightRepoTrade002_secAcctId').attr('disabled', true);
                            }
						}else{
							deleteSelect('TrdOutRightRepoTrade002_secAcctId');
							$('#TrdOutRightRepoTrade002_secAcctId').attr('disabled', false);
						}
						if (result.prodtype && result.prodtype != ''){
							$('#repoType').val(result.prodtype).trigger('change');// 回购类型
                            if($('#repoType').val()){
                                $('#repoType').attr('disabled', true);
                            }
						}else{
							deleteSelect('repoType');
							$('#repoType').attr('disabled', false);
						}
						if(result.market){
							$('#TrdOutRightRepoTrade002_tradeMarket').val(result.market).trigger('change');//交易场所
                            if($('#TrdOutRightRepoTrade002_tradeMarket').val()){
                                $('#TrdOutRightRepoTrade002_tradeMarket').attr('disabled', true);
                            }
							$('#TrdOutRightRepoTrade002_dealCalId').textSelect('setValue', result.market);//交易日历
                            if($('#TrdOutRightRepoTrade002_dealCalId').val()){
                                $('#TrdOutRightRepoTrade002_dealCalId').attr('disabled', true);
                            }
						}else{
							deleteSelect('TrdOutRightRepoTrade002_tradeMarket');
							deleteSelect('TrdOutRightRepoTrade002_dealCalId');
							$('#TrdOutRightRepoTrade002_tradeMarket').attr('disabled', false);
							$('#TrdOutRightRepoTrade002_dealCalId').attr('disabled', false);
							$('#chlId').attr('disabled', false);

						}

						if (result.ps && result.ps != ''){
							$('#TrdOutRightRepoTrade002_ps').val(result.ps).trigger('change');// 交易方向
                            if($('#TrdOutRightRepoTrade002_ps').val()){
                                $('#TrdOutRightRepoTrade002_ps').attr('disabled', true);
                            }
						}else{
							deleteSelect('TrdOutRightRepoTrade002_ps');
							$('#TrdOutRightRepoTrade002_ps').attr('disabled', false);
						}
						if (result.vdateStr && result.vdateStr != ''){
							$('#TrdOutRightRepoTrade002_vdate').val(result.vdateStr).trigger('change');// 起息日
                            if($('#TrdOutRightRepoTrade002_vdate').val()){
                                $('#TrdOutRightRepoTrade002_vdate').attr('disabled', true);
                            }
						}else {
							$('#TrdOutRightRepoTrade002_vdate').val('');
							$('#TrdOutRightRepoTrade002_vdate').attr('disabled', false);
						}
						if (result.mdateStr && result.mdateStr != ''){
							$('#TrdOutRightRepoTrade002_mdate').val(result.mdateStr).trigger('change');// 到期日
                            if($('#TrdOutRightRepoTrade002_mdate').val()){
                                $('#TrdOutRightRepoTrade002_mdate').attr('disabled', true);
                            }
						}else{
							$('#TrdOutRightRepoTrade002_mdate').val('');
							$('#TrdOutRightRepoTrade002_mdate').attr('disabled', false);
						}
						if (result.term && result.term != ''){
							$('#TrdOutRightRepoTrade002_repoterm').val(result.term).trigger('change');// 期限(天)
                            if($('#TrdOutRightRepoTrade002_repoterm').val()){
                                $('#TrdOutRightRepoTrade002_repoterm').attr('disabled', true);
                            }
						}else{
							$('#TrdOutRightRepoTrade002_repoterm').val('');
							$('#TrdOutRightRepoTrade002_repoterm').attr('disabled', false);
						}
						if (result.basis && result.basis != ''){
							$('#TrdOutRightRepoTrade002_basis').val(result.basis).trigger('change');// 计息基础
                            if($('#TrdOutRightRepoTrade002_basis').val()){
                                $('#TrdOutRightRepoTrade002_basis').attr('disabled', true);
                            }
						}
						/*else{
							deleteSelect('TrdOutRightRepoTrade002_basis');
							$('#TrdOutRightRepoTrade002_basis').attr('disabled', false);
						}*/
						//清算速度
						if (result.settleSpeed && result.settleSpeed != ''){
							$('#TrdOutRightRepoTrade002_settleSpeed').val(result.settleSpeed).trigger('change');
							if($('#TrdOutRightRepoTrade002_settleSpeed').val()){
								$('#TrdOutRightRepoTrade002_settleSpeed').attr('disabled', true);
							}
						}else{
							deleteSelect('TrdOutRightRepoTrade002_settleSpeed');
							$('#TrdOutRightRepoTrade002_settleSpeed').attr('disabled', false);
						}
						//期限品种
						if (result.termType && result.termType != ''){
							$('#TrdOutRightRepoTrade002_termType').val(result.termType).trigger('change');
							if($('#TrdOutRightRepoTrade002_termType').val()){
								$('#TrdOutRightRepoTrade002_termType').attr('disabled', true);
							}
						}else{
							deleteSelect('TrdOutRightRepoTrade002_termType');
							$('#TrdOutRightRepoTrade002_termType').attr('disabled', true);
						}


						if (result.ccy && result.ccy != ''){
							$('#TrdOutRightRepoTrade002_ccy').val(result.ccy).trigger('change');//币种
                            if($('#TrdOutRightRepoTrade002_ccy').val()){
                                $('#TrdOutRightRepoTrade002_ccy').attr('disabled', true);
                            }
						}else{
							deleteSelect('TrdOutRightRepoTrade002_ccy');
							$('#TrdOutRightRepoTrade002_ccy').attr('disabled', false);
						}
						if (result.repoamt && result.repoamt != ''){
							$('#prinAmt').val(result.repoamt).trigger('blur');//本金金额
                            if($('#prinAmt').val()){
                                $('#prinAmt').attr('disabled', true);
                            }
						}else{
							$('#prinAmt').val('');
							$('#prinAmt').attr('disabled', false);
						}
						if (result.reporate && result.reporate != ''){
							$('#rebackPrice').val(result.reporate).trigger('blur');//回购利率%
                            if($('#rebackPrice').val()){
                                $('#rebackPrice').attr('disabled', true);
                            }
						}else{
							$('#rebackPrice').val('');
							$('#rebackPrice').attr('disabled', false);
						}
						if (result.repointamt && result.repointamt != ''){
							$('#rebackIntAmt').val(result.repointamt).trigger('blur');//回购利息
                            if($('#rebackIntAmt').val()){
                                $('#rebackIntAmt').attr('disabled', true);
                            }
						}else {
							$('#rebackIntAmt').val('');
							$('#rebackIntAmt').attr('disabled', false);
						}
						if (result.mdealamt && result.mdealamt != ''){
							$('#mdateSettleAmt').val(result.mdealamt).trigger('blur');//到期结算金额
                            if($('#mdateSettleAmt').val()){
                                $('#mdateSettleAmt').attr('disabled', true);
                            }
						}else{
							$('#mdateSettleAmt').val('');
							$('#mdateSettleAmt').attr('disabled', false);
						}
						if (result.seller && result.seller != ''){
							$('#trdOutRightRepoPersonInfo_counterId').textSelect('setValue', result.seller);// 交易对手
                            if($('#trdOutRightRepoPersonInfo_counterId').val()){
                                $('#trdOutRightRepoPersonInfo_counterId').attr('disabled', true);
                            }
						}else{
							deleteSelect('trdOutRightRepoPersonInfo_counterId');
							$('#trdOutRightRepoPersonInfo_counterId').attr('disabled', false);
						}
						if (result.selltrader && result.selltrader != ''){
							$('#trdOutRightRepoPersonInfo_counterTrader').val(result.selltrader);// 对手方交易员
                            if($('#trdOutRightRepoPersonInfo_counterTrader').val()){
                                $('#trdOutRightRepoPersonInfo_counterTrader').attr('disabled', true);
                            }
						}else{
							$('#trdOutRightRepoPersonInfo_counterTrader').val('');
							$('#trdOutRightRepoPersonInfo_counterTrader').attr('disabled', false);
						}
						if (result.countertelephone && result.countertelephone != ''){
							$('#trdOutRightRepoPersonInfo_contactInfo').val(result.countertelephone);// 联系方式
                            if($('#trdOutRightRepoPersonInfo_contactInfo').val()){
                                $('#trdOutRightRepoPersonInfo_contactInfo').attr('disabled', true);
                            }
						}else{
							$('#trdOutRightRepoPersonInfo_contactInfo').val('');
							$('#trdOutRightRepoPersonInfo_contactInfo').attr('disabled', false);
						}
						if (result.facetotalamt && result.facetotalamt != ''){
							$('#bussRepoSec_totalPrinAmt').val(result.facetotalamt).trigger('blur');//汇总券面总额
                            if($('#bussRepoSec_totalPrinAmt').val()){
                                $('#bussRepoSec_totalPrinAmt').attr('disabled', true);
                            }
						}else{
							$('#bussRepoSec_totalPrinAmt').val('');
							$('#bussRepoSec_totalPrinAmt').attr('disabled', false);
						}
						if (result.dealtext && result.dealtext != ''){
							$('#remark').val(result.dealtext).trigger('change');// 备注
						}else{
							$('#remark').val('');
						}
						if (result.market && result.market != ''){
							$('#tradeMarket').val(result.market).trigger('change');// 交易市场
						}else{
							deleteSelect('tradeMarket');
						}
						if (result.secid && result.secid != ''){
							deleteSelect('TrdOutRightRepoTrade002_finprodId');
							$('#TrdOutRightRepoTrade002_finprodId').textSelect('setValue', result.secid);//标的资产代码
							if($('#TrdOutRightRepoTrade002_finprodId').find('option').eq(2).val()){
								$('#TrdOutRightRepoTrade002_finprodId').attr('disabled',true);
							}
							$('#TrdOutRightRepoTrade002_finprodId').trigger('change');
						}else{
							deleteSelect('TrdOutRightRepoTrade002_finprodId');
							$('#TrdOutRightRepoTrade002_finprodId').attr('disabled',false);
						}
						if(result.facetotalamt){
							var number = result.facetotalamt/10000;
							$('#TrdOutRightRepoTrade002_secTotalPrin').val(number).trigger('blur').trigger('change');//卷面总额
                            if($('#TrdOutRightRepoTrade002_secTotalPrin').val()){
                                $('#TrdOutRightRepoTrade002_secTotalPrin').attr('disabled', true);
                            }
						}else{
							deleteSelect('TrdOutRightRepoTrade002_settleSpeed');
							$('#TrdOutRightRepoTrade002_actAmtDays').val('');
							$('#TrdOutRightRepoTrade002_secTotalPrin').val('');
							$('#TrdOutRightRepoTrade002_secTotalPrin').attr('disabled', false);
						}
						if(result.reporate){
							var number = result.reporate*100;
							$('#TrdOutRightRepoTrade002_repoRate').val(number).trigger('change');//回购利
							$('#TrdOutRightRepoTrade002_repoRate').trigger('blur');//回购利
                            if($('#TrdOutRightRepoTrade002_repoRate').val()){
                                $('#TrdOutRightRepoTrade002_repoRate').attr('disabled', true);
                            }
						}else{
							$('#TrdOutRightRepoTrade002_repoRate').val('');
							$('#TrdOutRightRepoTrade002_repoRate').attr('disabled', false);
						}
						if(result.vcprice){
							$('#TrdOutRightRepoTrade002_fCleanPrice').val(result.vcprice).trigger('blur').trigger('change');//回购利
                            if($('#TrdOutRightRepoTrade002_fCleanPrice').val()){
                                $('#TrdOutRightRepoTrade002_fCleanPrice').attr('disabled', true);
                            }
						}else{
							$('#TrdOutRightRepoTrade002_fCleanPrice').val('');
							$('#TrdOutRightRepoTrade002_fCleanPrice').attr('disabled', false);
						}
						if(result.vinterest){
							$('#TrdOutRightRepoTrade002_fIntAmt').val(result.vinterest).trigger('blur').trigger('change');//回购利
                            if($('#TrdOutRightRepoTrade002_fIntAmt').val()){
                                $('#TrdOutRightRepoTrade002_fIntAmt').attr('disabled', true);
                            }
						}else{
							$('#TrdOutRightRepoTrade002_fIntAmt').val('');
							$('#TrdOutRightRepoTrade002_fIntAmt').attr('disabled', false);
						}
						if(result.vdprice){
							$('#TrdOutRightRepoTrade002_fFprice').val(result.vdprice).trigger('blur').trigger('change');//回购利
                            if($('#TrdOutRightRepoTrade002_fFprice').val()){
                                $('#TrdOutRightRepoTrade002_fFprice').attr('disabled', true);
                            }
						}else{
							$('#TrdOutRightRepoTrade002_fFprice').val('');
							$('#TrdOutRightRepoTrade002_fFprice').attr('disabled', false);
						}
						if(result.vyield){
							var number =result.vyield*100;
							$('#TrdOutRightRepoTrade002_fYield').val(number).trigger('blur').trigger('change');//回购利
							$('#TrdOutRightRepoTrade002_fYield').trigger('blur');//回购利
                            if($('#TrdOutRightRepoTrade002_fYield').val()){
                                $('#TrdOutRightRepoTrade002_fYield').attr('disabled', true);
                            }
						}else{
							$('#TrdOutRightRepoTrade002_fYield').val('');
							$('#TrdOutRightRepoTrade002_fYield').attr('disabled', false);
						}
						if(result.vdealamt){
							$('#TrdOutRightRepoTrade002_fSettleAmt').val(result.vdealamt).trigger('blur').trigger('change');//回购利
                            if($('#TrdOutRightRepoTrade002_fSettleAmt').val()){
                                $('#TrdOutRightRepoTrade002_fSettleAmt').attr('disabled', true);
                            }
						}else{
							$('#TrdOutRightRepoTrade002_fSettleAmt').val('');
							$('#TrdOutRightRepoTrade002_fSettleAmt').attr('disabled', false);
						}
						if(result.mcprice){
							$('#TrdOutRightRepoTrade002_mCleanPrice').val(result.mcprice).trigger('blur').trigger('change');//回购利
                            if($('#TrdOutRightRepoTrade002_mCleanPrice').val()){
                                $('#TrdOutRightRepoTrade002_mCleanPrice').attr('disabled', true);
                            }
						}else{
							$('#TrdOutRightRepoTrade002_mCleanPrice').val('');
							$('#TrdOutRightRepoTrade002_mCleanPrice').attr('disabled', false);
						}
						if(result.minterest){
							$('#TrdOutRightRepoTrade002_mSettleIntAmt').val(result.minterest).trigger('blur').trigger('change');//回购利
                            if($('#TrdOutRightRepoTrade002_mSettleIntAmt').val()){
                                $('#TrdOutRightRepoTrade002_mSettleIntAmt').attr('disabled', true);
                            }
						}else{
							$('#TrdOutRightRepoTrade002_mSettleIntAmt').val('');
							$('#TrdOutRightRepoTrade002_mSettleIntAmt').attr('disabled', false);
						}
						if(result.mdprice){
							$('#TrdOutRightRepoTrade002_mFprice').val(result.mdprice).trigger('blur').trigger('change');//回购利
                            if($('#TrdOutRightRepoTrade002_mFprice').val()){
                                $('#TrdOutRightRepoTrade002_mFprice').attr('disabled', true);
                            }
						}else{
							$('#TrdOutRightRepoTrade002_mFprice').val('');
							$('#TrdOutRightRepoTrade002_mFprice').attr('disabled', false);
						}
						if(result.myield){
							var number = result.myield*100;
							$('#TrdOutRightRepoTrade002_mYield').val(number).trigger('blur').trigger('change');//回购利
							$('#TrdOutRightRepoTrade002_mYield').trigger('blur');//回购利
                            if($('#TrdOutRightRepoTrade002_mYield').val()){
                                $('#TrdOutRightRepoTrade002_mYield').attr('disabled', true);
                            }
						}else{
							$('#TrdOutRightRepoTrade002_mYield').val('');
							$('#TrdOutRightRepoTrade002_mYield').attr('disabled', false);
						}
						if(result.mdealamt){
							$('#TrdOutRightRepoTrade002_mSettleAmt').val(result.mdealamt).trigger('blur').trigger('change');//回购利
                            if($('#TrdOutRightRepoTrade002_mSettleAmt').val()){
                                $('#TrdOutRightRepoTrade002_mSettleAmt').attr('disabled', true);
                            }
						}else{
							$('#TrdOutRightRepoTrade002_mSettleAmt').val('');
							$('#tradeAmtAndFee').val('');
							$('#TrdOutRightRepoTrade002_mSettleAmt').attr('disabled', false);
						}
						if(result.cashHoldingDays && result.cashHoldingDays != ''){
							$('#TrdOutRightRepoTrade002_actAmtDays').val(result.cashHoldingDays);// 实际占款天数
							if($('#TrdOutRightRepoTrade002_actAmtDays').val()){
								$('#TrdOutRightRepoTrade002_actAmtDays').attr('disabled', true);
							}
						}else{
							$('#TrdOutRightRepoTrade002_actAmtDays').val('');
							$('#TrdOutRightRepoTrade002_actAmtDays').attr('disabled', false);
						}
					}
				})
			}else{
				if ($('#TrdOutRightRepoTrade002_tradeDate').val()){
					$('#TrdOutRightRepoTrade002_tradeDate').val('');
					$('#TrdOutRightRepoTrade002_tradeDate').attr('disabled', false);
				}
				//清算速度
				if ($('#TrdOutRightRepoTrade002_settleSpeed').val()){
					deleteSelect('TrdOutRightRepoTrade002_settleSpeed');
					$('#TrdOutRightRepoTrade002_settleSpeed').attr('disabled', false);
				}
				//期限品种
				if ($('#TrdOutRightRepoTrade002_termType').val()){
					deleteSelect('TrdOutRightRepoTrade002_termType');
					$('#TrdOutRightRepoTrade002_termType').attr('disabled', true);
				}
				// if ($('#TrdOutRightRepoTrade002_pltId').val()){
				// 	$('#bussTrdOutRightRepoTrade_avalibleCash').val('');
				// 	$('#bussTrdOutRightRepoTrade_avalibleCashT1').val('');
				// 	deleteSelect('TrdOutRightRepoTrade002_pltId');
				// 	$('#TrdOutRightRepoTrade002_pltId').attr('disabled', false);
				// }
				if ($('#TrdOutRightRepoTrade002_secAcctId').val()){
					deleteSelect('TrdOutRightRepoTrade002_secAcctId');
					$('#TrdOutRightRepoTrade002_secAcctId').attr('disabled', false);
				}
				if ($('#repoType').val()){
					deleteSelect('repoType');
					$('#repoType').attr('disabled', false);
				}
				if($('#TrdOutRightRepoTrade002_tradeMarket').val()){
					deleteSelect('TrdOutRightRepoTrade002_tradeMarket');
					deleteSelect('TrdOutRightRepoTrade002_dealCalId');
					$('#TrdOutRightRepoTrade002_tradeMarket').attr('disabled', false);
					$('#TrdOutRightRepoTrade002_dealCalId').attr('disabled', false);
					$('#chlId').attr('disabled', false);
				}
				if ($('#TrdOutRightRepoTrade002_ps').val()){
					deleteSelect('TrdOutRightRepoTrade002_ps');
					$('#TrdOutRightRepoTrade002_ps').attr('disabled', false);
				}
				if ($('#TrdOutRightRepoTrade002_vdate').val()){
					$('#TrdOutRightRepoTrade002_vdate').val('');
					$('#TrdOutRightRepoTrade002_vdate').attr('disabled', false);
				}
				if ($('#TrdOutRightRepoTrade002_mdate').val()){
					$('#TrdOutRightRepoTrade002_mdate').val('');
					$('#TrdOutRightRepoTrade002_mdate').attr('disabled', false);
				}
				if ($('#TrdOutRightRepoTrade002_repoterm').val()){
					$('#TrdOutRightRepoTrade002_repoterm').val('');
					$('#TrdOutRightRepoTrade002_repoterm').attr('disabled', false);
				}
				if ($('#TrdOutRightRepoTrade002_basis').val()){
					/*deleteSelect('TrdOutRightRepoTrade002_basis');
					$('#TrdOutRightRepoTrade002_basis').attr('disabled', false);*/
					$('#TrdOutRightRepoTrade002_basis').val(BASIS_365).trigger('change');
				}
				if ($('#TrdOutRightRepoTrade002_ccy').val()){
					deleteSelect('TrdOutRightRepoTrade002_ccy');
					$('#TrdOutRightRepoTrade002_ccy').attr('disabled', false);
				}
				if ($('#prinAmt').val()){
					$('#prinAmt').val('');
					$('#prinAmt').attr('disabled', false);
				}
				if ($('#rebackPrice').val()){
					$('#rebackPrice').val('');
					$('#rebackPrice').attr('disabled', false);
				}
				if ($('#rebackIntAmt').val()){
					$('#rebackIntAmt').val('');
					$('#rebackIntAmt').attr('disabled', false);
				}
				if ($('#mdateSettleAmt').val()){
					$('#mdateSettleAmt').val('');
					$('#mdateSettleAmt').attr('disabled', false);
				}
				if ($('#trdOutRightRepoPersonInfo_counterId').val()){
					deleteSelect('trdOutRightRepoPersonInfo_counterId');
					$('#trdOutRightRepoPersonInfo_counterId').attr('disabled', false);
				}
				if ($('#trdOutRightRepoPersonInfo_counterTrader').val()){
					$('#trdOutRightRepoPersonInfo_counterTrader').val('');
					$('#trdOutRightRepoPersonInfo_counterTrader').attr('disabled', false);
				}
				if ($('#trdOutRightRepoPersonInfo_contactInfo').val()){
					$('#trdOutRightRepoPersonInfo_contactInfo').val('');
					$('#trdOutRightRepoPersonInfo_contactInfo').attr('disabled', false);
				}
				if (	$('#bussRepoSec_totalPrinAmt').val()){
					$('#bussRepoSec_totalPrinAmt').val('');
					$('#bussRepoSec_totalPrinAmt').attr('disabled', false);
				}
				if ($('#remark').val()){
					$('#remark').val('');
				}
				if ($('#tradeMarket').val()){
					deleteSelect('tradeMarket');
				}
				if ($('#TrdOutRightRepoTrade002_finprodId').val()){
					deleteSelect('TrdOutRightRepoTrade002_finprodId');
					$('#TrdOutRightRepoTrade002_finprodId').attr('disabled',false);
				}
				if($('#TrdOutRightRepoTrade002_secTotalPrin').val()){
					deleteSelect('TrdOutRightRepoTrade002_settleSpeed');
					$('#TrdOutRightRepoTrade002_actAmtDays').val('');
					$('#TrdOutRightRepoTrade002_secTotalPrin').val('');
					$('#TrdOutRightRepoTrade002_secTotalPrin').attr('disabled', false);
				}
				if($('#TrdOutRightRepoTrade002_repoRate').val()){
					$('#TrdOutRightRepoTrade002_repoRate').val('');
					$('#TrdOutRightRepoTrade002_repoRate').attr('disabled', false);
				}
				if($('#TrdOutRightRepoTrade002_fCleanPrice').val()){
					$('#TrdOutRightRepoTrade002_fCleanPrice').val('');
					$('#TrdOutRightRepoTrade002_fCleanPrice').attr('disabled', false);
				}
				if($('#TrdOutRightRepoTrade002_fIntAmt').val()){
					$('#TrdOutRightRepoTrade002_fIntAmt').val('');
					$('#TrdOutRightRepoTrade002_fIntAmt').attr('disabled', false);
				}
				if($('#TrdOutRightRepoTrade002_fFprice').val()){
					$('#TrdOutRightRepoTrade002_fFprice').val('');
					$('#TrdOutRightRepoTrade002_fFprice').attr('disabled', false);
				}
				if($('#TrdOutRightRepoTrade002_fYield').val()){
					$('#TrdOutRightRepoTrade002_fYield').val('');
					$('#TrdOutRightRepoTrade002_fYield').attr('disabled', false);
				}
				if($('#TrdOutRightRepoTrade002_fSettleAmt').val()){
					$('#TrdOutRightRepoTrade002_fSettleAmt').val('');
					$('#TrdOutRightRepoTrade002_fSettleAmt').attr('disabled', false);
				}
				if($('#TrdOutRightRepoTrade002_mCleanPrice').val()){
					$('#TrdOutRightRepoTrade002_mCleanPrice').val('');
					$('#TrdOutRightRepoTrade002_mCleanPrice').attr('disabled', false);
				}
				if($('#TrdOutRightRepoTrade002_mSettleIntAmt').val()){
					$('#TrdOutRightRepoTrade002_mSettleIntAmt').val('');
					$('#TrdOutRightRepoTrade002_mSettleIntAmt').attr('disabled', false);
				}
				if($('#TrdOutRightRepoTrade002_mFprice').val()){
					$('#TrdOutRightRepoTrade002_mFprice').val('');
					$('#TrdOutRightRepoTrade002_mFprice').attr('disabled', false);
				}
				if($('#TrdOutRightRepoTrade002_mYield').val()){
					$('#TrdOutRightRepoTrade002_mYield').val('');
					$('#TrdOutRightRepoTrade002_mYield').attr('disabled', false);
				}
				if($('#TrdOutRightRepoTrade002_mSettleAmt').val()){
					$('#TrdOutRightRepoTrade002_mSettleAmt').val('');
					$('#tradeAmtAndFee').val('');
					$('#TrdOutRightRepoTrade002_mSettleAmt').attr('disabled', false);
				}
			}
		};



		var deleteSelect = function (id) {
			$('#'+id).val('').trigger('change');
		}

	});
}())