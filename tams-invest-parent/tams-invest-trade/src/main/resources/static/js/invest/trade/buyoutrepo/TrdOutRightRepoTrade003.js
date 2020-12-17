(function(){
	/**
	 * 资产代码
	 */
	var secIdChangeAction = function(){
		$('#TrdOutRightRepoTrade002_finprodName').val($("#TrdOutRightRepoTrade002_finprodId").select2('data')[0].object);
	};
    function getriskdata() {
        var mun=null;
        var object = $.serializeToObject('#TrdOutRightRepoTrade002_submitForm', true);
        var trdOutRightRepoTradePtl=null;
        if(object!=null&&object!=""&&object.trdOutRightRepoTradePtl!=null&&object.trdOutRightRepoTradePtl!=""){
            trdOutRightRepoTradePtl=object.trdOutRightRepoTradePtl;
        }
        var list=[];
        var obj=null;
        for(var i in trdOutRightRepoTradePtl){
            if(trdOutRightRepoTradePtl[i].tradeRepAmt!=0&&trdOutRightRepoTradePtl[i].tradeRepAmt!=null&&trdOutRightRepoTradePtl[i].tradeRepAmt!=" "){
                obj = {
                        timePoint: MY_SUP_TIME_TYPE_DEAL,
                        valDate: $('#TrdOutRightRepoTrade002_tradeDate').val(),
                        portfolioCode: trdOutRightRepoTradePtl[i].portfolioId,
                        tradeAmount: trdOutRightRepoTradePtl[i].tradeRepAmt,
                        assetType: getFinProdType2FromPs(),
                        tradeDirection:$('#TrdOutRightRepoTrade002_ps').val(),
                        isEnabled: '01',
                        index: null,
                        mun: mun
                    }
                list[i]=obj;
            }
        }
        
        if($('#TrdOutRightRepoTrade002_ps').val() == BACK_LEND_DIRECTION_FORWARD){
            for(var i in trdOutRightRepoTradePtl){
                list[i].assetType = FINPROD_TYPE_TG_F04;
            }
        }else if($('#TrdOutRightRepoTrade002_ps').val() == BACK_LEND_DIRECTION_NEGATIVE){
            for(var i in trdOutRightRepoTradePtl){
                obj.assetType = FINPROD_TYPE_TG_F05;
            }
        }else{
            if(obj){
                obj.assetType = null;
            }
        }
        var object = {
            valDate: ['tradeDate', 'TrdOutRightRepoTrade002.dealDate'],
            portfolioCode: ['portfolioId', 'TrdOutRightRepoTrade002.pltId'],
            tradeAmount: ['fSettleAmt', 'TrdOutRightRepoTrade002.amtInfo.firstSettleAmt'],
            /* tradeAmount: ['fFprice', 'TrdOutRightRepoTrade002.amtInfo.firstFullPrice'],*/
            /* assetType: ['finprodId', 'TrdOutRightRepoTrade002.finprodMarketId'],*/
            tradeDirection:['ps', 'TrdOutRightRepoTrade002.ps'],
            data: list
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
		if (vdate && mdate){
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
        if(!$("#TrdOutRightRepoTrade002_secAcctId").val()){
            return;
        }
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
		Joyin.ajaxJson(basePath + 'invest/trade/buyoutrepo/TrdOutRightRepoTrade003/calFee', object, function (result) {
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
            table.showColumns([{field:'assetsavailablepositions',visible: false}]);
            table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash',visible: true}]);
            table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash1',visible: true}]);
            table.showColumns([{field:'channelAvailableCash',visible: true}]);
        }
        // 当交易方向为正回购时
        else if (ps == BACK_LEND_DIRECTION_FORWARD) {
            // 组合可用现金隐藏
            window.BussTrdAvailableCashQuery["bussTrdOutRightRepoTrade"].hideCash();
            window.BussTrdAvailableCashQuery["bussTrdOutRightRepoTrade"].showPosition();
            table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash',visible: false}]);
            table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash1',visible: false}]);
            table.showColumns([{field:'channelAvailableCash',visible: false}]);
            table.showColumns([{field:'assetsavailablepositions',visible: true}]);
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
    
    var calcClickAction2 = function(){
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
		/*var calculateField = $("[name=calculateField]:checked").val();
		if (calculateField == 'fCleanPrice'){
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
        $('#CombinedAmount_div').hide();
        if (isDone) {
        	
        	// 刷新托管户剩余现金、组合T日可用现金、组合T+1日可用现金
        	psChangeAction();

        	var checkparam = getcheckdata();
            if(typeof(BaseRiskMathComm) != 'undefined') {
                var riskparam = getriskdata();
            if (pageModel==PAGE_MODEL_NEW){
            	checkparam =null;
                riskparam.data =null;
                window.BaseRiskMathComm.getparam(riskparam);
                } else {
            }
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
            	var list = new Array();
            	var tmpList = table.getAllRows();
                for(var i in tmpList)
            	{
                    if(tmpList[i].tradeRepAmt!=0&&tmpList[i].tradeRepAmt!=null&&tmpList[i].tradeRepAmt!=" "){
                        var dealObj = {};
                        dealObj.finprodType2 = getFinProdType2FromPs();
                        dealObj.settleDate = $("#TrdOutRightRepoTrade002_tradeDate").val();
                        dealObj.tradeDate = $("#TrdOutRightRepoTrade002_tradeDate").val();
                        dealObj.ps = PS_T01;
                        dealObj.ccy = $("#TrdOutRightRepoTrade002_ccy").val();
                        dealObj.tradeAmt = tmpList[i].tradeRepAmt;
                        dealObj.chlId = $("#chlId").val();
                        dealObj.portfolioId = tmpList[i].portfolioId;
                        dealObj.counterId = $("#trdOutRightRepoPersonInfo_counterId").val();
                        dealObj.tradeMarket = $("#TrdOutRightRepoTrade002_tradeMarket").val();
                        dealObj.secManageAcctId = tmpList[i].secManageAcctId;
                        dealObj.branch = noBranchNoValue;
                        list.push(dealObj);
                    }
            	}
                
                return {
                    'isTotalTrade': YES_OR_NO_N
                    ,'bankLendingType': null
                    ,'portfolioId':'dzh'
                    ,'jiaoyiType':'dzh'
                    ,'deal': list
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
       /* $('#trdInvestPersonInfo_counterId').on("change",function(){
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
	        var maxTradeamt = 0;
            if(typeof(BaseRiskMathComm) != 'undefined') {
	        if(BaseRiskMathComm) {
	            s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
	            if(!s && s != ''){
	                return;
	            }
	            if (window.BaseRiskMathComm.isCalcConditionChanged(getriskdata())){
	                return;
                    }
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
	    	    var tradeDate=$("#TrdOutRightRepoTrade002_tradeDate").val();
	    		if(entrustTermStart>repoterm||entrustTermEnd<repoterm){
	    			Joyin.error("请输入合理的回购期限！");
	    			return;
	    		}
	    		/*var date=new Date(tradeDate); 
	    		date.setDate(date.getDate()+repoterm); 
	    		
	    		var endDate2=new Date(endDate); 
	    		if(endDate2.getTime()<date.getTime()){
	    		    Joyin.error("请输入合理的回购期限！");
	                return;
	    		}*/
	    	}
	    	
	    	//检查组合分配的和金额信息是否一致
	    	var secTotalPrin2=JoyinMath.doMul($('#TrdOutRightRepoTrade002_secTotalPrin').val(),10000,2);
	        if(secTotalPrin2){
	            var number = 0;
	            for(var i=0;i<table.getAllRows().length;i++){
	                if(table.getAllRows()[i].tradeAmt){
	                    number =  JoyinMath.doAdd(number,table.getAllRows()[i].tradeAmt,2);
	                }
	                if (parseFloat(table.getAllRows()[i].firstSettleAmt) > parseFloat(maxTradeamt)){
						maxTradeamt = table.getAllRows()[i].firstSettleAmt;
					}
	            }
	            if(secTotalPrin2!=number){
	                Joyin.warn("组合分配劵面总额和金额信息券面总额不一致！");
	                return;
	            }
	        }
	
	        var object = $.serializeToObject('#TrdOutRightRepoTrade002_submitForm', true);
	        object.pageModel = pageModel;
	        object.operateType = operateType;
	//        object.isGuarantee = $('#TrdOutRightRepoTrade002_pltId').select2('data')[0].object; // 是否保本
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
			
			object.maxTradeamt = maxTradeamt;
			object.counterType = object.trdOutRightRepoPersonInfo.isOutTrade;
			object.counterId = object.trdOutRightRepoPersonInfo.counterId;
			object.tradeAmt = object.secTotalPrin;
			object.prinAmt = object.secTotalPrin;
			object.inputValue1 = 'F10';
	        Joyin.ajaxJson(basePath + 'invest/trade/buyoutrepo/TrdOutRightRepoTrade003/check', object, function (result) {
	        	Joyin.ajaxJson(basePath + 'invest/trade/TrdCommonController/check', object, function(result1) {
		            Joyin.confirm(result.data, function () {
		                if (operateType === 'save') {
		                    WorkFlow.doSaveList(basePath + 'invest/trade/buyoutrepo/TrdOutRightRepoTrade003/save', object, "TrdOutRightRepoTrade002_btn_save",function(result){
	
						     });
		                } else {
		                    WorkFlow.doSubmitList(basePath + 'invest/trade/buyoutrepo/TrdOutRightRepoTrade003/save', object, "TrdOutRightRepoTrade002_btn_submit", pageId,null, function(result){
							      window.BaseRiskMathComm.reloadRiskTable(result);
		 				    });
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
    			
    		$('#TrdOutRightRepoTrade002_vdate').val(DateUtil.parse(tradeDate).DateAdd('d', num).Format('yyyy-MM-dd')).trigger('change');
    	}
    	
    }

    var tradeMarketChange = function () {
		if (TRADE_MARKET_01 == $("#TrdOutRightRepoTrade002_tradeMarket").val()) {
			$("#TrdOutRightRepoTrade002_settleSpeed").val(SETTLESPEED_0).trigger('change')
		}
		else {
			$("#TrdOutRightRepoTrade002_settleSpeed").prop("disabled", false).trigger('change')
		}
	}

    var init = function(){
        $('#CombinedAmount_div').hide();
        $('#TrdOutRightRepoTrade002_termType').attr('disabled',true);
    	var entrustId = $('#TrdOutRightRepoTrade002_entrustId').val();
    	if (entrustId && entrustId != ''){
    	    zhilingtypeChangeAction();
    	    distribution003(true);
    	    $('#TrdOutRightRepoTrade003_distribution').attr('disabled',false);
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
    		
		}else{
		    $('#bussTrdTransfenpei_div').show();
	        zhilingtypeChangeAction();
		}

		$("#TrdOutRightRepoTrade002_tradeMarket").change(tradeMarketChange);
//		 tradeMarketChange();
    }
    
    function distribution2(data,distriData){
        var secTotalPrin = $('#TrdOutRightRepoTrade002_secTotalPrin').val()*10000;
        for(var i=0;i < data.length;i++){
            var b=i;
            var tradeAmtTotle = data[i].doEntrustMoney;
            if(i!=data.length-1){
                for(var j=i+1;j < data.length+1;j++){
                    if(i!=data.length-1&&data[i][distriData]==data[j][distriData]){
                        tradeAmtTotle=JoyinMath.doAdd(tradeAmtTotle,data[j].doEntrustMoney,2)-0;
                        i++;
                    }else{
                        if(tradeAmtTotle!=data[i].doEntrustMoney){
                            if(tradeAmtTotle-0<=secTotalPrin-0){
                                for(var k=b;k<j;k++){
                                    if(k==data.length-1){
                                        table.renderRow(k, {"entrustId":data[k].entrustId});
                                        table.renderRow(k, {"portfolioId":data[k].portfolioId});
                                        table.renderRow(k, {"doEntrustMoney":data[k].doEntrustMoney});
                                        table.renderRow(k, {"tradeAmt":secTotalPrin});
                                        secTotalPrin=JoyinMath.doMinus(secTotalPrin,secTotalPrin,2)-0;
                                    }else{
                                        table.renderRow(k, {"entrustId":data[k].entrustId});
                                        table.renderRow(k, {"portfolioId":data[k].portfolioId});
                                        table.renderRow(k, {"doEntrustMoney":data[k].doEntrustMoney});
                                        table.renderRow(k, {"tradeAmt":data[k].doEntrustMoney});
                                        secTotalPrin=JoyinMath.doMinus(secTotalPrin,data[k].doEntrustMoney,2)-0;
                                    }
                                }
                            }else{
                                for(var k=b;k<j;k++){
                                    
                                    var div=JoyinMath.doMul(data[k].doEntrustMoney,secTotalPrin,2);
                                    var tradAmt=JoyinMath.doDiv(div,tradeAmtTotle,2);
                                    if(k==j-1){
                                        table.renderRow(k, {"entrustId":data[k].entrustId});
                                        table.renderRow(k, {"portfolioId":data[k].portfolioId});
                                        table.renderRow(k, {"doEntrustMoney":data[k].doEntrustMoney});
                                        table.renderRow(k, {"tradeAmt":secTotalPrin});
                                        secTotalPrin=JoyinMath.doMinus(secTotalPrin,secTotalPrin,2)-0;
                                    }else{
                                        table.renderRow(k, {"entrustId":data[k].entrustId});
                                        table.renderRow(k, {"portfolioId":data[k].portfolioId});
                                        table.renderRow(k, {"doEntrustMoney":data[k].doEntrustMoney});
                                        table.renderRow(k, {"tradeAmt":tradAmt});
                                        secTotalPrin=JoyinMath.doMinus(secTotalPrin,tradAmt,2)-0;
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
            }
            
            
            if(secTotalPrin<=0&&tradeAmtTotle==data[i].doEntrustMoney){
                table.renderRow(i, {"entrustId":data[i].entrustId});
                table.renderRow(i, {"portfolioId":data[i].portfolioId});
                table.renderRow(i, {"doEntrustMoney":data[i].doEntrustMoney});
                table.renderRow(i, {"tradeAmt":" "});
            }else{
                if(tradeAmtTotle!=data[i].doEntrustMoney){
                    continue;
                }else{
                    if(i==data.length-1){
                        table.renderRow(i, {"entrustId":data[i].entrustId});
                        table.renderRow(i, {"portfolioId":data[i].portfolioId});
                        table.renderRow(i, {"doEntrustMoney":data[i].doEntrustMoney});
                        table.renderRow(i, {"tradeAmt":secTotalPrin});
                        secTotalPrin=JoyinMath.doMinus(secTotalPrin,secTotalPrin,2)-0;
                    }else{
                        var amt = secTotalPrin-(data[i].tradeAmt-0);
                        if(amt<0){
                            table.renderRow(i, {"entrustId":data[i].entrustId});
                            table.renderRow(i, {"portfolioId":data[i].portfolioId});
                            table.renderRow(i, {"doEntrustMoney":data[i].doEntrustMoney});
                            table.renderRow(i, {"tradeAmt":secTotalPrin});
                            secTotalPrin=JoyinMath.doMinus(secTotalPrin,secTotalPrin,2)-0;
                        }else{
                            table.renderRow(i, {"entrustId":data[i].entrustId});
                            table.renderRow(i, {"portfolioId":data[i].portfolioId});
                            table.renderRow(i, {"doEntrustMoney":data[i].doEntrustMoney});
                            table.renderRow(i, {"tradeAmt":data[i].doEntrustMoney});
                            secTotalPrin=JoyinMath.doMinus(secTotalPrin,data[i].doEntrustMoney==" "?0:data[i].doEntrustMoney,2)-0;
                        }
                    }
                }
                
            }
        }
    }
    
    function distribution3(data){
        var secTotalPrin = $('#TrdOutRightRepoTrade002_secTotalPrin').val()*10000;
        var secTotalPrin2 = $('#TrdOutRightRepoTrade002_secTotalPrin').val()*10000;
        var doEntrustMoneyTotle = 0;
        for(var i=0;i < data.length;i++){
            doEntrustMoneyTotle=JoyinMath.doAdd(doEntrustMoneyTotle,data[i].doEntrustMoney,2)-0;
        }
        
        for(var i=0;i < data.length;i++){
            var num=JoyinMath.doMul(data[i].doEntrustMoney,secTotalPrin2,2)-0;
            var tradeAmt=JoyinMath.doDiv(num,doEntrustMoneyTotle,2)-0;
            
            if(i==data.length-1){
                table.renderRow(i, {"entrustId":data[i].entrustId});
                table.renderRow(i, {"portfolioId":data[i].portfolioId});
                table.renderRow(i, {"doEntrustMoney":data[i].doEntrustMoney});
                table.renderRow(i, {"tradeAmt":secTotalPrin});
                secTotalPrin=JoyinMath.doMinus(secTotalPrin,secTotalPrin,2)-0;
            }else{
                table.renderRow(i, {"entrustId":data[i].entrustId});
                table.renderRow(i, {"portfolioId":data[i].portfolioId});
                table.renderRow(i, {"doEntrustMoney":data[i].doEntrustMoney});
                table.renderRow(i, {"tradeAmt":tradeAmt});
                secTotalPrin=JoyinMath.doMinus(secTotalPrin,tradeAmt,2)-0;
            }
        }
    }
    
    function distribution003(val) {
        var entrustId = $('#TrdOutRightRepoTrade002_entrustId').val();
        var distribution = $('#TrdOutRightRepoTrade003_distribution').val();
        var ps = $('#TrdOutRightRepoTrade002_ps').val();
        Joyin.ajaxSync(basePath + 'invest/trade/buyoutrepo/TrdOutRightRepoTrade003/getEntrustInfo', {'entrustId':entrustId,'distribution':distribution,'ps':ps,"isNeedOth":'Y'},function (data) {
            if ($('#TrdOutRightRepoTrade003_distribution').val() == '139') {//平均分配
                distribution3(data);
                extractedJiSuan();
            }else if ($('#TrdOutRightRepoTrade003_distribution').val() == '140') {//时间优先
                distribution2(data,'entrustDate');
                extractedJiSuan();
            }else if ($('#TrdOutRightRepoTrade003_distribution').val() == '141') {//数量优先
                distribution2(data,'tradeAmt');
                extractedJiSuan();
            }else if ($('#TrdOutRightRepoTrade003_distribution').val() == '142') {//价格优先
                distribution2(data,'repoRate');
                extractedJiSuan();
            }else{
                if(val){
                    var rowDatas = [];
                    for(var i = 0; i<data.length ;i++){
                        var rowData={};
                        rowData.entrustId=data[i].entrustId;
                        rowData.portfolioId=data[i].portfolioId;
                        rowData.doEntrustMoney=data[i].doEntrustMoney;
                        rowData.tradeAmt=data[i].tradeAmt;
                        rowData.repoRate=data[i].repoRate;
                        rowData.LAY_TABLE_INDEX = i;
                        rowDatas.push(rowData);
                    }
                    setTimeout(function () {
                        table.addRow(rowDatas);
                    },2000);
                }
                extractedJiSuan();
            }
        });
    }
    
    var zhilingtypeChangeAction = function(){
        $('#bussTrdAvailableCashQuery_div_cash').show();
        $('#bussTrdTransfenpei_div').show();
        $("#portfolioId").attr("disabled", true);
        $("#portfolioId ").empty();
        window.BussTrdAvailableCashQuery && window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'].hideCash(true);
        if(!$("#bussTrdTransfenpeiTable").data('joyintable')){
            table = $("#bussTrdTransfenpeiTable").joyintable({
                  data:TrdOutRightRepoTradePtl==null?[]:TrdOutRightRepoTradePtl,
                  searchOnInit:true,
                  height:'225px',
                  cols:[
                      {field:'entrustId',  width:100,title: 'TrdOutRightRepoTrade003.entrustId',edit:false, sortable:false,type:'text'}
                      ,{field:'portfolioId', width:100,  title: 'TrdOutRightRepoTrade003.busiId',edit:true,sortable: false,
                          editOption:{type:'select',childrenIds:'secManageAcctId', id:'portfolioId', multiple: false, showField:'id',searchable: true,
                              url: JoyinUtil.getRealPath('/business/common/CommonSelect/selectPortfolioId?act=true')
                          }
                      }
                      ,{field:'doEntrustMoney',  width:100,title: 'TrdOutRightRepoTrade003.doEntrustMoney',edit:true, sortable:false,edit:false,type:'number', digit:2}
                      ,{field:'secManageAcctId', width:100,  title: 'TrdOutRightRepoTrade003.secManageAcctId',edit:true,sortable: false,
                          editOption:{type:'select',showField:'text',id:'secManageAcctId', parentIds:'portfolioId,TrdOutRightRepoTrade002_tradeMarket', multiple: false,searchable: true,
                              url: JoyinUtil.getRealPath('/invest/trade/trdrepotrade/TrdRepoTrade002/getSecManageAcctId')
                          }
                        }
                      ,{field:'tradeAmt',  width:100,title: 'TrdOutRightRepoTrade003.tradeAmt',edit:true, sortable:false,type:'number', digit:2}
                      ,{field:'repoRate',width:100,  title: 'TrdOutRightRepoTrade003.repoRate',sortable:false,type:'number', digit:2}
                      ,{field:'firstIntAmt',width:100, title: 'TrdOutRightRepoTrade003.firstIntAmt',sortable:false,type:'number', edit:true, digit:2}
                      ,{field:'firstSettleAmt',width:100, title: 'TrdOutRightRepoTrade003.firstSettleAmt',sortable:false,type:'number', edit:true, digit:2}
                      ,{field:'matIntAmt',width:100, title: 'TrdOutRightRepoTrade003.matIntAmt',sortable:false,type:'number', edit:true,digit:2}
                      ,{field:'matSettleAmt',width:100, title: 'TrdOutRightRepoTrade003.matSettleAmt',sortable:false,type:'number', edit:true,digit:2}
                      ,{field:'procedureAmt',width:100, title: 'TrdOutRightRepoTrade003.procedureAmt',sortable:false,type:'number',edit:true, digit:2}
                      ,{field:'settleAmt',width:100, title: 'TrdOutRightRepoTrade003.settleAmt',sortable:false,type:'number', edit:true,digit:2}
                      ,{field:'tradeRepAmt',width:100, title: 'TrdOutRightRepoTrade003.tradeRepAmt',sortable:false,type:'number', edit:true, digit:2}
                      ,{field:'bussTrdAvailableCashQueryavalibleCash', width:100,title: 'TrdOutRightRepoTrade003.bussTrdAvailableCashQueryavalibleCash',sortable:false,type:'number', digit:2}
                      ,{field:'bussTrdAvailableCashQueryavalibleCash1',width:100, title: 'TrdOutRightRepoTrade003.bussTrdAvailableCashQueryavalibleCash1',sortable:false,type:'number', digit:2}
                      ,{field:'channelAvailableCash',width:100, title: function(){return JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("TrdOutRightRepoTrade003.channelAvailableCash");}() ,sortable:false,type:'number', digit:2}
                      ,{field:'assetsavailablepositions', width:100, title: 'TrdOutRightRepoTrade003.assetsavailablepositions', sortable: false,type:'number', digit:2}
                      ,{field:'operation', width:100,title: 'SysCommon.operation',sortable:false, type:entrustId?'text':'action', buttons: ['delt']}
                   ],
                   addbtn:true,
                   done:function(){
                       this.bind('click', '.delt', function(data, index) {
                           table.deleteRow(index);
                       });
                   },
                   beforeEditCell:function(value, field, data){
                      var entrustId = $("#TrdOutRightRepoTrade002_entrustId").val();
                      if(field=='portfolioId'&&entrustId){
                          return false;
                      }
                      
                      if(field=='firstIntAmt'&&entrustId){
                          return false;
                      }
                      if(field=='firstSettleAmt'&&entrustId){
                          return false;
                      }
                      if(field=='matIntAmt'&&entrustId){
                          return false;
                      }
                      if(field=='matSettleAmt'&&entrustId){
                          return false;
                      }
                      if(field=='procedureAmt'&&entrustId){
                          return false;
                      }
                      if(field=='settleAmt'&&entrustId){
                          return false;
                      }
                      if(field=='tradeRepAmt'&&entrustId){
                          return false;
                      }
                      if(field=="secManageAcctId"){
                        if($('#chlId').val()){
                            table.renderRow(data.LAY_TABLE_INDEX,  {"secManageAcctId": ""});
                            return false;
                        }else{
                            return true;
                        }
                      }else{
                        return true;
                      }
                    },
                   editCell: function(value, data, field, cell, column){
                       var params={};
                       table.renderRow(data.LAY_TABLE_INDEX,  {"repoRate": $("#TrdOutRightRepoTrade002_repoRate").val()});
                       //买入
                       if("portfolioId"==field && "04"==$("#TrdOutRightRepoTrade002_ps").val()){
                           if(value){
                               table.renderRow(data.LAY_TABLE_INDEX,  {"secManageAcctId": ""});
                           }
                           var rowData = {};
                           table.renderRow(data.LAY_TABLE_INDEX, {"channelAvailableCash":''})
                           if(value && value.trim()
                                && $("#TrdOutRightRepoTrade002_dealCalId").val() && $("#TrdOutRightRepoTrade002_ccy").val() && $("#TrdOutRightRepoTrade002_tradeDate").val()){
                                if($('#chlId').val()){
                                params.chlId = $('#chlId').val();
                                }
                               params.portfolioId=value;
                               params.calendarId=$("#TrdOutRightRepoTrade002_dealCalId").val();
                               params.tradeDate=$("#TrdOutRightRepoTrade002_tradeDate").val();
                               params.acctTypeList = ACCT_TYPE_A01 + "," + ACCT_TYPE_A07;
                               params.ccy=$("#TrdOutRightRepoTrade002_ccy").val();
                               Joyin.ajaxJson(JoyinUtil.getRealPath('/component/trade/BussTrdAvailableCashQuery/getAvailableCash'), params, function (cashdata) {
                                   table.renderRow(data.LAY_TABLE_INDEX, {"bussTrdAvailableCashQueryavalibleCash": cashdata.T_0});
                                   table.renderRow(data.LAY_TABLE_INDEX, {"bussTrdAvailableCashQueryavalibleCash1": cashdata.T_1});
                                   table.renderRow(data.LAY_TABLE_INDEX, {"channelAvailableCash": cashdata.T_2});
                               });
                           }
                       }else if("portfolioId"==field && "03"!=$("#TrdOutRightRepoTrade002_ps").val()){
                           if(value){
                               table.renderRow(data.LAY_TABLE_INDEX,  {"secManageAcctId": ""});
                           }
                           table.renderRow(data.LAY_TABLE_INDEX, {"bussTrdAvailableCashQueryavalibleCash": ''});
                           table.renderRow(data.LAY_TABLE_INDEX, {"bussTrdAvailableCashQueryavalibleCash1": ''});
                       }
                       if(field=='secManageAcctId'){
                           if(value&& value.trim()){
                               $('input[name="secManageAcctId"]').val(value.trim());
                           }
                           
                           var b=0;
                           for(var i = 0; i < table.getAllRows().length; i++){
                               if(!table.getAllRows()[i].secManageAcctId){
                                   b++;
                               }
                           }
                           if(b==table.getAllRows().length){
                               $('#chlId').attr("disabled", false);
                           }else{
                               $('#chlId').val(" ");
                               $('#chlId').attr("disabled", true);
                           }
                           
                           var rowData = {};
                           //交易场所可用资金
                           if($('#TrdOutRightRepoTrade002_tradeMarket').val()&&($('#TrdOutRightRepoTrade002_tradeMarket').val()=='03'||$('#TrdOutRightRepoTrade002_tradeMarket').val()=='04')
                               && value && value.trim()&&data.portfolioId){
                               var param = {};
                               param.portfolioId=data.portfolioId;
                               param.secManageAcctId=value;
                               param.ccy=$('#TrdOutRightRepoTrade002_ccy').val();
                               param.calendarId = $('#TrdOutRightRepoTrade002_dealCalId').val();
                               Joyin.ajaxJson(JoyinUtil.getRealPath('/invest/trade/bond/TrdBondInvestTrade003/getBankCash'), param, function (result) {
                                   rowData.accAvailableCash = Number(result);
                                   table.renderRow(data.LAY_TABLE_INDEX,rowData);
                               });
                           }
                           //交易方向为卖出时显示资产可用持仓
                           if(value && value.trim()&&data.portfolioId
                               && $("#TrdOutRightRepoTrade002_dealCalId").val() && $("#TrdOutRightRepoTrade002_ccy").val() && $("#TrdOutRightRepoTrade002_tradeDate").val()&&!($('#TrdOutRightRepoTrade002_ps').val()=='04')){

                               params.portfolioId=data.portfolioId;
                               params.finprodId=$("#TrdOutRightRepoTrade002_finprodId").val();
                               params.calendarId=$("#TrdOutRightRepoTrade002_dealCalId").val();
                               params.tradeDate=$("#TrdOutRightRepoTrade002_tradeDate").val();
                               params.acctTypeList = ACCT_TYPE_A01 + "," + ACCT_TYPE_A07;
                               params.ccy=$("#TrdOutRightRepoTrade002_ccy").val();
                               params.multiParam=100;
                               params.secAcctId=value;
                               Joyin.ajaxJson(JoyinUtil.getRealPath('/component/trade/BussTrdAvailableCashQuery/getAvailableCash'), params, function (cashdata) {
                                   table.renderRow(data.LAY_TABLE_INDEX, {"assetsavailablepositions": cashdata.position});
                               });
                           }
                           
                           var entrustId = $("#TrdOutRightRepoTrade002_entrustId").val();
                           if(entrustId){
                               
                           }
                       }

                       //只有券面总额发生变化才需要更新净价总额、应计利息总额
                       if("TrdOutRightRepoTrade002_secTotalPrin"==field){
                           isChange = true;
                           //净价总额按比例分配
                            if(value&&$('#TrdOutRightRepoTrade002_secTotalPrin').val()){
                                extractedCpriceAmt();
                            }
                            //应计利息总额按照比率分配
                           if(value&&$('#TrdOutRightRepoTrade002_secTotalPrin').val()){
                               extractedIntAmt();
                           }
                           //交易手续费和结算费按比计算
                           if(value&&$('#TrdOutRightRepoTrade002_secTotalPrin').val()){
                               extractedJiSuan(false);
                           }
                       }
                       //费用改变的时候
                       if(field=='procedureAmt'||field=='settleAmt'){
                           var numberTwo = 0;
                           //计算交易金额
                           if (data.fpriceAmt) {
                               numberTwo = data.fpriceAmt;
                               if ($('#TrdOutRightRepoTrade002_ps').val() == '04') {
                                   //不是银行间
                                   if ($('#TrdOutRightRepoTrade002_tradeMarket').val() && ($('#TrdOutRightRepoTrade002_tradeMarket').val() == '03' || $('#TrdOutRightRepoTrade002_tradeMarket').val() == '04')) {
                                       //佣金是否随交易支付
                                       if (data.commissionAmt && isPayWithTrade==YES_OR_NO_Y) {
                                           numberTwo = JoyinMath.doAdd(numberTwo, data.commissionAmt, 2);
                                       }
                                       //印花是否随交易支付
                                       if (data.stampDutyAmt && stampDutyIsPayWithTrade==YES_OR_NO_Y) {
                                           numberTwo = JoyinMath.doAdd(numberTwo, data.stampDutyAmt, 2);
                                       }
                                   }
                               } else {
                                   if ($('#TrdOutRightRepoTrade002_tradeMarket').val() && ($('#TrdOutRightRepoTrade002_tradeMarket').val() == '03' || $('#TrdOutRightRepoTrade002_tradeMarket').val() == '04')) {
                                       //是否随交易支付
                                       if (data.commissionAmt && isPayWithTrade==YES_OR_NO_Y) {
                                           numberTwo = JoyinMath.doMinus(numberTwo, data.commissionAmt, 2);
                                       }
                                       //印花是否随交易支付
                                       if (data.stampDutyAmt && stampDutyIsPayWithTrade==YES_OR_NO_Y) {
                                           numberTwo = JoyinMath.doMinus(numberTwo, data.stampDutyAmt, 2);
                                       }
                                   }
                               }
                               table.renderRow(data.LAY_TABLE_INDEX, {"tradeRepAmt": JoyinMath.doAdd(numberTwo, 0, 2)});
                           }
                       }
                   }
            });
        }
    }
    
    function extractedJiSuan() {
        var repoRate = $('#TrdOutRightRepoTrade002_repoRate').val();
        var secTotalPrin = $('#TrdOutRightRepoTrade002_secTotalPrin').val();
        var fIntAmt = $('#TrdOutRightRepoTrade002_fIntAmt').val();
        var fSettleAmt = $('#TrdOutRightRepoTrade002_fSettleAmt').val();
        var mSettleIntAmt = $('#TrdOutRightRepoTrade002_mSettleIntAmt').val();
        var mSettleAmt = $('#TrdOutRightRepoTrade002_mSettleAmt').val();
        var procedureAmt = $('#procedureAmt').val();
        var settleAmt = $('#settleAmt').val();
        var tradeAmtAndFee = $('#tradeAmtAndFee').val();
        secTotalPrin=secTotalPrin*10000;
        
        var secTotalPrin2 = $('#TrdOutRightRepoTrade002_secTotalPrin').val()*10000;
        
        var fIntAmtTotle = Number(0);
        var fSettleAmtTotle = Number(0);
        var mSettleIntAmtTotle = Number(0);
        var mSettleAmtTotle = Number(0);
        var procedureAmtTotle = Number(0);
        var settleAmtTotle = Number(0);
        var tradeAmtAndFeeTotle = Number(0);
        
        var zero='0';
        
        for (var i = 0; i < table.getAllRows().length; i++) {
            var fIntAmt4 = JoyinMath.doMul(table.getAllRows()[i].tradeAmt==" "?zero:table.getAllRows()[i].tradeAmt,fIntAmt,2)-0;
            var fSettleAmt4 = JoyinMath.doMul(table.getAllRows()[i].tradeAmt==" "?zero:table.getAllRows()[i].tradeAmt,fSettleAmt,2)-0;
            var mSettleIntAmt4 = JoyinMath.doMul(table.getAllRows()[i].tradeAmt==" "?zero:table.getAllRows()[i].tradeAmt,mSettleIntAmt,2)-0; 
            var mSettleAmt4 = JoyinMath.doMul(table.getAllRows()[i].tradeAmt==" "?zero:table.getAllRows()[i].tradeAmt,mSettleAmt,2)-0;  
            var procedureAmt4 = JoyinMath.doMul(table.getAllRows()[i].tradeAmt==" "?zero:table.getAllRows()[i].tradeAmt,procedureAmt,2)-0; 
            var settleAmt4 = JoyinMath.doMul(table.getAllRows()[i].tradeAmt==" "?zero:table.getAllRows()[i].tradeAmt,settleAmt,2)-0; 
            var tradeAmtAndFee4 = JoyinMath.doMul(table.getAllRows()[i].tradeAmt==" "?zero:table.getAllRows()[i].tradeAmt,tradeAmtAndFee,2)-0; 
            //先设置劵面总额
            if($('#TrdOutRightRepoTrade002_secTotalPrin').val()
               &&$('#TrdOutRightRepoTrade002_repoRate').val()
               &&$('#TrdOutRightRepoTrade002_fCleanPrice').val()
               &&table.getAllRows()[i].portfolioId){
                table.renderRow(i, {"repoRate": repoRate});
                if(table.getAllRows().length>1){
                    if (i != table.getAllRows().length - 1) {//非最后一列
                        var fIntAmt2 = JoyinMath.doDiv(fIntAmt4,secTotalPrin,2)-0;
                        var fSettleAmt2 = JoyinMath.doDiv(fSettleAmt4,secTotalPrin,2)-0;
                        var mSettleIntAmt2 = JoyinMath.doDiv(mSettleIntAmt4,secTotalPrin,2)-0;
                        var mSettleAmt2 = JoyinMath.doDiv(mSettleAmt4,secTotalPrin,2)-0;
                        var procedureAmt2 = JoyinMath.doDiv(procedureAmt4,secTotalPrin,2)-0;
                        var settleAmt2 = JoyinMath.doDiv(settleAmt4,secTotalPrin,2)-0;
                        var tradeAmtAndFee2 = JoyinMath.doDiv(tradeAmtAndFee4,secTotalPrin,2)-0;
                        
                        fIntAmtTotle = JoyinMath.doAdd(fIntAmtTotle,fIntAmt2,2)-0;
                        fSettleAmtTotle = JoyinMath.doAdd(fSettleAmtTotle,fSettleAmt2,2)-0;
                        mSettleIntAmtTotle = JoyinMath.doAdd(mSettleIntAmtTotle,mSettleIntAmt2,2)-0;
                        mSettleAmtTotle = JoyinMath.doAdd(mSettleAmtTotle,mSettleAmt2,2)-0;
                        procedureAmtTotle = JoyinMath.doAdd(procedureAmtTotle,procedureAmt2,2)-0;
                        settleAmtTotle = JoyinMath.doAdd(settleAmtTotle,settleAmt2,2)-0;
                        tradeAmtAndFeeTotle = JoyinMath.doAdd(tradeAmtAndFeeTotle,tradeAmtAndFee2,2)-0;
                        
                        if(fIntAmt2==0||fIntAmt2==" "){
                            fIntAmt2="0";
                        }
                        table.renderRow(i, {"firstIntAmt": fIntAmt2});
                        table.renderRow(i, {"firstSettleAmt": fSettleAmt2});
                        table.renderRow(i, {"matIntAmt": mSettleIntAmt2});
                        table.renderRow(i, {"matSettleAmt": mSettleAmt2});
                        if(procedureAmt){
                            table.renderRow(i, {"procedureAmt": procedureAmt2});
                        }else{
                            table.renderRow(i, {"procedureAmt": ""});
                        }
                        if(settleAmt){
                            table.renderRow(i, {"settleAmt": settleAmt2});
                        }else{
                            table.renderRow(i, {"settleAmt": ""});
                        }
                        table.renderRow(i, {"tradeRepAmt": tradeAmtAndFee2});
                        
                        secTotalPrin2=JoyinMath.doMinus(secTotalPrin2,table.getAllRows()[i].tradeAmt==" "?0:table.getAllRows()[i].tradeAmt,2); 
                    }else{
                        if((table.getAllRows()[i].tradeAmt==null||table.getAllRows()[i].tradeAmt==""||(table.getAllRows()[i].tradeAmt-0)==0)&&secTotalPrin2>0){
                            table.renderRow(i, {"tradeAmt": secTotalPrin2});
                            secTotalPrin2= secTotalPrin2 - secTotalPrin2;
                        }else if(secTotalPrin2-0!=table.getAllRows()[i].tradeAmt-0){
                            Joyin.error("组合分配交易总额和金额信息交易总额不一致");
                        }
                        if(table.getAllRows()[i].tradeAmt-0>0){
                            var fIntAmtTotle2 = JoyinMath.doMinus(fIntAmt,fIntAmtTotle,2);
                            var fSettleAmtTotle2 = JoyinMath.doMinus(fSettleAmt,fSettleAmtTotle,2);
                            var mSettleIntAmtTotle2 = JoyinMath.doMinus(mSettleIntAmt,mSettleIntAmtTotle,2); 
                            var mSettleAmtTotle2 = JoyinMath.doMinus(mSettleAmt,mSettleAmtTotle,2);
                            var procedureAmtTotle2 = JoyinMath.doMinus(procedureAmt,procedureAmtTotle,2); 
                            var settleAmtTotle2 = JoyinMath.doMinus(settleAmt,settleAmtTotle,2);
                            var tradeAmtAndFeeTotle2 = JoyinMath.doMinus(tradeAmtAndFee,tradeAmtAndFeeTotle,2);
                            
                            table.renderRow(i, {"firstIntAmt": fIntAmtTotle2});
                            table.renderRow(i, {"firstSettleAmt": fSettleAmtTotle2});
                            table.renderRow(i, {"matIntAmt": mSettleIntAmtTotle2});
                            table.renderRow(i, {"matSettleAmt": mSettleAmtTotle2});
                            if(procedureAmt){
                                table.renderRow(i, {"procedureAmt": procedureAmtTotle2});
                               
                            }else{
                                table.renderRow(i, {"procedureAmt": ""});
                                
                            }
                            if(settleAmt){
                                table.renderRow(i, {"settleAmt": settleAmtTotle2});
                            }else{
                                table.renderRow(i, {"settleAmt": ""});
                            }
                            table.renderRow(i, {"tradeRepAmt": tradeAmtAndFeeTotle2});
                            
                            var tradeAmt3= isNaN(table.getAllRows()[i].tradeAmt-0)?0:isNaN(table.getAllRows()[i].tradeAmt-0);
                            secTotalPrin2=isNaN(secTotalPrin2)?0:secTotalPrin2;
                        }else{
                            for(var j = i; j>=0; j--){
                                if(table.getAllRows()[j].tradeAmt-0>0){
                                    table.renderRow(j, {"firstIntAmt": JoyinMath.doAdd(table.getAllRows()[j].firstIntAmt==" "?0:table.getAllRows()[j].firstIntAmt,fIntAmtTotle2,2)});
                                    table.renderRow(j, {"firstSettleAmt": JoyinMath.doAdd(table.getAllRows()[j].firstSettleAmt==" "?0:table.getAllRows()[j].firstSettleAmt,fSettleAmtTotle2,2)});
                                    table.renderRow(j, {"matIntAmt": JoyinMath.doAdd(table.getAllRows()[j].matIntAmt==" "?0:table.getAllRows()[j].matIntAmt,mSettleIntAmtTotle2,2)});
                                    table.renderRow(j, {"matSettleAmt": JoyinMath.doAdd(table.getAllRows()[j].matSettleAmt==" "?0:table.getAllRows()[j].matSettleAmt,mSettleAmtTotle2,2)});
                                    table.renderRow(j, {"procedureAmt": JoyinMath.doAdd(table.getAllRows()[j].procedureAmt==" "?0:table.getAllRows()[j].procedureAmt,procedureAmtTotle2,2)});
                                    table.renderRow(j, {"settleAmt": JoyinMath.doAdd(table.getAllRows()[j].settleAmt==" "?0:table.getAllRows()[j].settleAmt,settleAmtTotle2,2)});
                                    table.renderRow(j, {"tradeRepAmt": JoyinMath.doAdd(table.getAllRows()[j].tradeRepAmt==" "?0:table.getAllRows()[j].tradeRepAmt,tradeAmtAndFeeTotle2,2)});
                                    table.renderRow(j, {"tradeAmt": JoyinMath.doAdd(table.getAllRows()[j].tradeAmt==" "?0:table.getAllRows()[j].tradeAmt,secTotalPrin2,2)});
                                    break;
                                }else{
                                    var zero="0";
                                    table.renderRow(j, {"firstIntAmt": zero});
                                    table.renderRow(j, {"firstSettleAmt": zero});
                                    table.renderRow(j, {"matIntAmt": zero});
                                    table.renderRow(j, {"matSettleAmt": zero});
                                    table.renderRow(j, {"procedureAmt": zero});
                                    table.renderRow(j, {"settleAmt": zero});
                                    table.renderRow(j, {"tradeRepAmt": zero});
                                    table.renderRow(j, {"tradeAmt": zero});
                                }
                            }
                        }
                    }
                }
            }
            
            var params = {};
            //组合可用现金
            if(table.getAllRows()[i].portfolioId && table.getAllRows()[i].portfolioId .trim()
                    && $("#TrdOutRightRepoTrade002_dealCalId").val() && $("#TrdOutRightRepoTrade002_ccy").val() && $("#TrdOutRightRepoTrade002_tradeDate").val()
                    &&($('#TrdOutRightRepoTrade002_ps').val()=='04')){
                    if($('#chlId').val()){
                        params.chlId = $('#chlId').val();
                    }
                   params.portfolioId=table.getAllRows()[i].portfolioId;
                   params.calendarId=$("#TrdOutRightRepoTrade002_dealCalId").val();
                   params.tradeDate=$("#TrdOutRightRepoTrade002_tradeDate").val();
                   params.acctTypeList = ACCT_TYPE_A01 + "," + ACCT_TYPE_A07;
                   params.ccy=$("#TrdOutRightRepoTrade002_ccy").val();
                   Joyin.ajaxJson(JoyinUtil.getRealPath('/component/trade/BussTrdAvailableCashQuery/getAvailableCash'), params, function (cashdata) {
                       table.renderRow(i, {"bussTrdAvailableCashQueryavalibleCash": cashdata.T_0});
                       table.renderRow(i, {"bussTrdAvailableCashQueryavalibleCash1": cashdata.T_1});
                       table.renderRow(i, {"channelAvailableCash": cashdata.T_2});
                   },false);
            }
            //交易方向为卖出时显示资产可用持仓
            if(table.getAllRows()[i].secManageAcctId && table.getAllRows()[i].secManageAcctId.trim()&&table.getAllRows()[i].portfolioId
                && $("#TrdOutRightRepoTrade002_dealCalId").val() && $("#TrdOutRightRepoTrade002_ccy").val() && $("#TrdOutRightRepoTrade002_tradeDate").val()&&!($('#TrdOutRightRepoTrade002_ps').val()=='04')){

                params.portfolioId=table.getAllRows()[i].portfolioId;
                params.finprodId=$("#TrdOutRightRepoTrade002_finprodId").val();
                params.calendarId=$("#TrdOutRightRepoTrade002_dealCalId").val();
                params.tradeDate=$("#TrdOutRightRepoTrade002_tradeDate").val();
                params.acctTypeList = ACCT_TYPE_A01 + "," + ACCT_TYPE_A07;
                params.ccy=$("#TrdOutRightRepoTrade002_ccy").val();
                params.multiParam=100;
                params.secAcctId=table.getAllRows()[i].secManageAcctId;
                Joyin.ajaxJson(JoyinUtil.getRealPath('/component/trade/BussTrdAvailableCashQuery/getAvailableCash'), params, function (cashdata) {
                    table.renderRow(i, {"assetsavailablepositions": cashdata.position});
                },false);
            }
        }
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
        	calcClickAction2();
//        	extractedJiSuan();
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
        	$("#TrdOutRightRepoTrade002_secAcctId").val('').trigger('change');
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


        $('#TrdOutRightRepoTrade003_btn_risk').click(function () {
            //风险新版本 add by panbin 2020-3-25 start
            //检查组合分配的和金额信息是否一致
            if($('#TrdOutRightRepoTrade002_secTotalPrin').val()){
                var number = 0;
                for(var i=0;i<table.getAllRows().length;i++){
                    var tradeAmt=table.getAllRows()[i].tradeAmt==" "?0:table.getAllRows()[i].tradeAmt;
                    if(tradeAmt){
                        number =  JoyinMath.doAdd(number,tradeAmt,2);
                    }
                }
                if(JoyinMath.doAdd(JoyinMath.doMul($('#TrdOutRightRepoTrade002_secTotalPrin').val(),10000,2),0,2)!=number){
                    Joyin.warn("组合分配劵面总额和金额信息券面总额不一致！");
                    return;
                }
            }
            var object = $.serializeToObject('#TrdOutRightRepoTrade002_submitForm', true);
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfBatchData/1/RSK002/F10',object,getriskdata());
            //风险新版本 add by panbin 2020-3-25 end
            /*Joyin.ajax(basePath + 'invest/trade/buyoutrepo/TrdOutRightRepoTrade003/selectIndex',{},function(result){
                if(result){
                    var index=result;
                    var object1=getriskdata();
                    var list2=object1.data;
                    for(var i in list2){
                        object1.data[i].mun=list2.length;
                        object1.data[i].index=index;
                    }
                    //window.BaseRiskMathComm.initBaseRiskMathCommPage2(object1);
                    $("#riskMathFlag").val("02");
                }
            });*/
        });
        
        // 绑定按钮事件
        $('#TrdOutRightRepoTrade003_btn_save').click(function () {
            saveOrSubmit('save');
        });
        $('#TrdOutRightRepoTrade003_btn_submit').click(function () {
            saveOrSubmit('submit');
        });

		// 银行间成交编号(当交易场所选择银行间时，银行间成交编号可编辑)
		$('#dealBussId').on('change',function(){
			if (pageModel==PAGE_MODEL_NEW) {
				fTradeIdChangeAction();
			}
		});
		
		//分配方式
        $('#TrdOutRightRepoTrade003_btn_autoAllocation').click(function () {
            var ccy=$("#TrdOutRightRepoTrade002_ccy").val();
            if(ccy==null||ccy==''||ccy==undefined){
                       Joyin.error("请选择币种信息");
                       return;
            }

            var entrustId = $('#TrdOutRightRepoTrade002_entrustId').val();
            if(entrustId){
                distribution003(false);
            }else{
                extractedJiSuan();
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
				Joyin.ajax(basePath + 'invest/trade/buyoutrepo/TrdOutRightRepoTrade003/queryRepoCstpInfoByNo', {fedealno : dealBussId}, function(result){
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
							$('#TrdOutRightRepoTrade002_termType').attr('disabled', false);
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
					$('#TrdOutRightRepoTrade002_termType').attr('disabled', false);
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