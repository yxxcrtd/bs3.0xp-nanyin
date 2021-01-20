//@ sourceURL=TrdFundInvestTrade003.js
/**
 * 基金投资交易确认
 */
$(function () {
	let changeType = null;
    var getriskdata = function () {
        var data = $('#finprodId').select2('data')[0].object;
        var finprodId;
        if (data && data.finprodId) {
            finprodId= data.finprodId;
        }
        var obj = {timePoint:MY_SUP_TIME_TYPE_02,valDate:$('#tradeDate').val(),portfolioCode:$('#portfolioId').val(),tradeAmount:$('#tradeAmt').val(),assetType:FINPROD_TYPE_F06,isEnabled:'01',assetCode:finprodId,tradeDirection:$('#ps').val()}
        var object = {valDate:['tradeDate','TrdFundInvestTrade002.tradeDate'],portfolioCode:['portfolioId','TrdFundInvestTrade002.portfolioId'],tradeAmount:['tradeAmt','TrdFundInvestTrade002.tradeAmt'],assetType:['type6','TrdFundInvestTrade002.type6'],tradeDirection:['ps','TrdFundInvestTrade002.ps'],data:obj}
        return object;
    }
    function saveOrSubmit(operateType) {
        var s;
        if(typeof(BaseRiskMathComm) != 'undefined') {
        if(BaseRiskMathComm&&$('#ps').val()==ps_T19) {
            s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
            if(!s && s != ''){
                return;
            }
            if (window.BaseRiskMathComm.isCalcConditionChanged(getriskdata())){
                return;
                }
            }
        }
        if (window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()) {
            return;
        }
        var object = $.serializeToObject('#submitForm',true);
        if ($('#select2-finprodId-container').attr('title')){
            object.finprodName = $('#select2-finprodId-container').attr('title');
        }
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.tradeId = $('#tradeId').val();
        object.bTradeId = $('#bTradeId').val();
        if (object.ps == ps_T05 && object.tradeAmt) {
            object.prinAmt = object.tradeAmt - getPayWithTradeFee();
        } else if (object.shareAmt && object.unitCprice) {
            object.prinAmt = Number(object.shareAmt) * Number(object.unitCprice);
        } else {
            object.prinAmt = object.tradeAmt;
        }
        var intAmt = 0;
        if (object.intAmt) {
            intAmt = object.intAmt;
        }
        object.fpriceAmt = object.prinAmt + Number(intAmt);
        object.finprodMarketId = $('#finprodId').val();
        object.finprodId = $('#finprodId').select2('data')[0].object.finprodId;
        // add by xr 赎回到账，因无交易对手导致授权校验报错 Start
        if (object.ps == ps_T24) {
            object.counterId = $('#trdInvestPersonInfo_counterId').val();
        }
        // add by xr 赎回到账，因无交易对手导致授权校验报错 end
//        if(object.ps == ps_T05){
//            object.tradeAmt = $('#tradeAmtHid').val();
//            object.fpriceAmt = $('#tradeAmt').val();
//        }else{
//            object.tradeAmt = $('#tradeAmt').val();
//            object.prinAmt = $('#tradeAmtHid').val();
//            object.fpriceAmt = $('#tradeAmtHid').val();
//        }
        var data = $("#chlId").find("option:selected").data("data");
        if (data && data.object) {
            object.payType = data.object.payType;
            object.collectType = data.object.collectType;
        }

        if (FileCommon) {
            if (pageModel == PAGE_MODEL_REFERENCE) {
                object.fileArray = $('#fileUploader').data('data');
            } else {
                object.fileArray = FileCommon.getUploadFileInfo();
            }
            if (object.fileArray && _.isArray(object.fileArray)) {
                $.each(object.fileArray, function (index, obj) {
                    obj.seqNo = obj.fileNo;
                });
            }
        }

        if ($('#changeToFinprodMarketId').val() && $('#changeToFinprodMarketId').select2('data')[0].object && $('#changeToFinprodMarketId').select2('data')[0].object.finprodId) {
            object.rFinprodId = $('#changeToFinprodMarketId').select2('data')[0].object.finprodId;
            var unitCprice = getInputValue('unitCprice', 1);
            object.tradeAmt = JoyinMath.doMul($("#shareAmt").val(), unitCprice, 2);
        }

        if ($('#ps').val() == ps_T66) {
            var inFinProfitType = $('#changeToFinprodMarketId').select2('data')[0].object.profitType;
            var outFinProfitType = $('#finprodId').select2('data')[0].object.profitType;
            object.inFinProfitType = inFinProfitType;
            object.outFinProfitType = outFinProfitType;
        }

        Joyin.ajaxJson(basePath + 'invest/trade/fund/TrdFundInvestTrade003/check', object, function (result) {
        	Joyin.ajaxJson(basePath + 'invest/trade/fund/TrdFundInvestTrade003/checkAuthorization', object, function(result1) {
	            Joyin.confirm(result.data, function () {
	                if (operateType === 'save') {
	                    if (object.ps == ps_T24) {
	                        WorkFlow.doSaveList(basePath + 'invest/trade/fund/TrdFundInvestTrade003/save', object, "TrdFundInvestTrade003_btn_total", "TrdFundInvestTrade003");
	                    } else {
	                        WorkFlow.doSaveList(basePath + 'invest/trade/fund/TrdFundInvestTrade003/save', object, "TrdFundInvestTrade003_btn_save", "TrdFundInvestTrade003");
	                    }
	                } else {
	                    if (object.ps == ps_T24) {
	                        WorkFlow.doSubmitList(basePath + 'invest/trade/fund/TrdFundInvestTrade003/save', object, "TrdFundInvestTrade003_btn_total", "TrdFundInvestTrade003");
	                    } else {
	                        WorkFlow.doSubmitList(basePath + 'invest/trade/fund/TrdFundInvestTrade003/save', object, "TrdFundInvestTrade003_btn_submit", "TrdFundInvestTrade003");
	                    }
	                }
	            });
        	});
        });
    };
    var getPayWithTradeFee = function () {
        var ps = $('#ps').val();
        var feeAmtPay = $('#feeAmtPay').val();
        if (!feeAmtPay) {
            feeAmtPay = 0;
        }
        var channelAmt = $('#channelAmt').val();
        if (!channelAmt) {
            channelAmt = 0;
        }
        if (!($("#chlId").val() && $("#chlId").select2("data")[0] && $("#chlId").select2("data")[0].object
            && $("#chlId").select2("data")[0].object.collectType == collectType_01)) {
            channelAmt = 0;
        }
        // 佣金
        var commissionFee = $('#commissionFee').val();
        if (!commissionFee) {
            commissionFee = 0;
        } else if (ps && $('#finprodId').val()) {
            param = {
                tradeMarket: $('#tradeMarket').val(),
                ps: ps,
                tradeDate: $('#tradeDate').val(),
                type6: $("#type6").val(),
                secManageAcctId: $("#secManageAcctId").val()
            }
            Joyin.ajaxJson(basePath + 'invest/trade/fund/TrdFundInvestTrade002/getCommissionFeeInfo', param,
                function (result) {
                    if (result && result.isPayWithTrade) {
                        if (result.isPayWithTrade == YES_OR_NO_N) {
                            commissionFee = 0;
                        }
                        $("#isPayWithTrade").val(result.isPayWithTrade);
                    } else {
                        $("#isPayWithTrade").val('');
                    }
                }, false);
        }
        return Number(feeAmtPay) + Number(channelAmt) + Number(commissionFee)
    };
    //查询组合可用现金
    var getPtlCashPositionInfo = function () {
        param = {
            portfolioId: $('#portfolioId').val(), ccy: $('#ccy').val(), vdate: $('#tradeDate').val(),
            calId: $('#calendarId').val(), chlId: $("#chlId").val(), secAcctId: $("#secManageAcctId").val()
        }
        Joyin.ajaxJson(basePath + 'invest/trade/fund/TrdFundInvestTrade002/getProAvailableCash', param,
            function (result) {
                $('#proAvailableCash').val(result.cashT).trigger('blur');
                $('#proAvailableCash1').val(result.cashT1).trigger('blur');
                $('#channelAvailableCash').val(result.T_2).trigger('blur');
                $('#accAvailableCash').val(result.cashT3).trigger('blur');
                // 场内基金改造 add by renping 20191117 start
                // 交易所时，证券托管户现金
                if ($("#tradeMarket").val() == TRADE_PLACE_03 || $("#tradeMarket").val() == TRADE_PLACE_04) {
                    $('#tradeMarketAccAvailableCash').val(result.cashS).trigger('blur');
                } else {
                    $('#tradeMarketAccAvailableCash').val('').trigger('blur');
                }
                // 场内基金改造 add by renping 20191117 end
            });
    }
    //查询组合可用持仓
    var getPtlSecPositionInfo = function () {
        var secAcctId = $("#secManageAcctId").val();
        if (!secAcctId) {
            secAcctId = $("#chlId").val();
        }
        param = {
            portfolioId: $('#portfolioId').val(),
            finprodId: $('#finprodId').select2('data')[0].object.finprodId,
            cdate: $('#tradeDate').val(),
            secAcctId: secAcctId
        }
        Joyin.ajaxJson(basePath + 'invest/trade/fund/TrdFundInvestTrade002/getPtlSecPosition', param,
            function (result) {
                $('#assetAvailablePosition').val(result).trigger('blur');
            });
    }
    //查询待结转份额
    var getcarryAmt = function (isChange) {
        param = {
            carryDate: $('#tradeDate').val(),
            cid: $('#calendarId').val(),
            portfolioId: $('#portfolioId').val(),
            finprodId: isChange && $('#ps').val() == ps_T66 ? $('#changeToFinprodMarketId').select2('data')[0].object.finprodId : $('#finprodId').select2('data')[0].object.finprodId,
            secAcctId: $("#chlId").val()
        }
        Joyin.ajaxJson(basePath + 'invest/trade/fund/TrdFundInvestTrade003/getcarryAmt', param,
            function (result) {
                $('#carryAmt').val(result).trigger('blur').change();
            });
    }

    function getInputValue(inputId, defaultValue) {
        var inputValue = $('#' + inputId).val();
        if (!inputValue) {
            if (defaultValue) {
                inputValue = defaultValue;
            }
            else {
                inputValue = 0;
            }
        }
        return inputValue;
    }

    function calcInShareAmt() {
        if (!$('#changeToFinprodMarketId').val()) {
            return;
        }
        var shareAmt = $('#shareAmt').val();

        var unitCprice = getInputValue('unitCprice', 1);

        var unitCpriceIn = getInputValue('unitCpriceIn', 1);

        var carryAmt = getInputValue('carryAmt');

        var feeAmtPay = getInputValue('feeAmtPay');

        var feeAmtPayPurchase = getInputValue('feeAmtPayPurchase');

        var channelAmt = getInputValue('channelAmt');

        if (!($("#chlId").val() && $("#chlId").select2("data")[0] && $("#chlId").select2("data")[0].object
            && $("#chlId").select2("data")[0].object.collectType == collectType_01)) {
            channelAmt = 0;
        }

        var feeAmt = Number(feeAmtPay) + Number(channelAmt) + Number(feeAmtPayPurchase);
        var inFinProfitType = $('#changeToFinprodMarketId').select2('data')[0].object.profitType;
        if (inFinProfitType == PROFITTYPE_VALUE_03) {
            // 转入基金确认份额=交易份额*转出基金单位净值-赎回费-申购费
            $('#changeInShareAmt').val(((Number(shareAmt) + Number(carryAmt)) * unitCprice - feeAmt)).trigger('blur');

        } else if (inFinProfitType == PROFITTYPE_VALUE_01) {
            // 转入基金确认份额=（交易份额*转出基金单位净值-赎回费-申购费）/转入基金单位净值
            $('#changeInShareAmt').val((Number(shareAmt) * unitCprice - feeAmt) / unitCpriceIn).trigger('blur');
        }
    }

    //画面初始化
    var init = function () {


        if($('#ps').val()!= ps_T24){//T24
            $("#expectedArrivalDate_label").text('预计到账日');
        }else{
            $("#expectedArrivalDate_label").text('实际到账日');
        }
    	  if ($("#ps").val() == ps_T19) {
              $("#channelAmt_div").show();
              $("#channelAmt").prop("disabled", false);
              $("#tradeAmt").prop("disabled", false);
          } else if ($("#ps").val() ==ps_T24) {
              $("#channelAmt_div").show();
          }else {
              $("#channelAmt_div").hide();
          }

        $('#finprodId').on('change', function (e, isInit) {
            var finprodId = $('#finprodId').select2('data')[0].object.finprodId;
            var ps = $('#ps').val();
            var portfolioId = $('#portfolioId').val();
            if (finprodId) {
                var data = $('#finprodId').select2('data')[0].object;
                $("#finprodName").val($('#finprodId').val()).trigger('change');
                if (data.trmFundAbbr) {
                    $('#trmFundAbbr_div').show();
                    $("#trmFundAbbr").val(data.trmFundAbbr);
                } else {
                    $("#trmFundAbbr").val("");
                    $('#trmFundAbbr_div').hide();
                }
                $("#trmFundType").val(data.trmFundType).trigger('change');
                $("#calendarId").val(data.calendarId).trigger('change');
                $("#ccy").val(data.ccy).trigger('change');
                $("#type6").val(data.type6).trigger('change');
                // 新增资产二、三、四级分类 王贵奇 2019/8/5 开始
                $("#type9").val(data.type9).trigger('change');
                $("#type10").val(data.type10).trigger('change');
                $("#type11").val(data.type11).trigger('change');
                // 新增资产二、三、 四级分类 王贵奇 2019/8/5 结束
            }
            if ((ps == ps_T05 || ps == ps_T18) && finprodId && portfolioId) {
                getPtlCashPositionInfo();
            }
            if ((ps == ps_T19 || ps == ps_T24) && finprodId && portfolioId) {
                getPtlSecPositionInfo();
            }
            if ($('#netDate').val()) {
                $('#netDate').trigger("change", isInit);
            }
//            var netDate = $('#netDate').val();
//            var tradeAmt = $('#tradeAmt').val();
//            var type6 = $('#type6').val();
//            if(netDate && finprodId && type6 == type_02){
//                var data = {finprodId:finprodId,netDate:netDate}
//                Joyin.ajaxJson(basePath + 'invest/trade/fund/TrdFundInvestTrade003/getPrice', data , function(result) {
//                    $('#unitCprice').val(result).trigger('blur').change();
//                });
//            }
        });
        //modify by gaopeng 20191018 页面优化 start

        $("#changeToFinprodMarketId").change(function () {
            if ($("#changeToFinprodMarketId").val() && $('#changeToFinprodMarketId').select2('data')[0].text) {
                $("#changeToFinprodName").val($('#changeToFinprodMarketId').select2('data')[0].id);
            } else {
                $("#changeToFinprodName").val("")
            }
        })

        /**
         * 交易方向
         *   货币基金转货币基金：changeType 1
         *    币种、交易份额、待结转份额、净值日期、赎回费、申购费、转入基金确认份额=交易份额*转出基金单位净值-赎回费-申购费
         *    货币基金转非货币基金：changeType 2
         *    币种、交易份额、待结转份额、净值日期、转入基金单位净值、赎回费、申购费、转入基金确认份额=（交易份额*转出基金单位净值-赎回费-申购费）/转入基金单位净值
         *    非货币基金转货币基金：changeType 3
         *    币种、交易份额、净值日期、转出基金单位净值、赎回费、申购费、转入基金确认份额=交易份额*转出基金单位净值-赎回费-申购费
         *    非货币基金转非货币基金：changeType 4
         *    币种、交易份额、净值日期、转出基金单位净值、转入基金单位净值、赎回费、申购费、转入基金确认份额=（交易份额*转出基金单位净值-赎回费-申购费）/转入基金单位净值。
         */
        $('#ps').on('change', function () {
            var ps = $('#ps').val();
            var finprodId = $('#finprodId').select2('data')[0].object.finprodId;
            var portfolioId = $('#portfolioId').val();
            var type6 = $('#type6').val();
            $(".changein").hide();
            $("#purchaseFee_div1").hide();
            $("#changeInShareAmt_div").hide();

            // 基金转换特殊处理
            if (ps == ps_T66) {
                $('#proAvailableCash_div').hide();
                $('.channel_cash').hide();
                $('#proAvailableCash1_div').hide();
                $('#assetAvailablePosition_div').hide();
                $('#expectedArrivalDate_div').hide();
                $('#transferInformation').hide();
                $('#accAvailableCash_c_div').hide();
                $(".changein").show();
                $("#purchaseFee_div1").show();
                $('#purchase').hide();
                $('.tradeAmt_div').hide();
                $('#redemp').show();
                $('#netDate_div').show();
                $('#changeInShareAmt_div').show();
                $(".unitCprice_out_div").hide();
                $(".unitCprice_in_div").hide();
                $("#shareAmt_label").text("转出基金确认份额");
                if ($("#chlId").val()) {
                    $("#channelAmt_div").show();
                }
                var outFinProfitType = $('#finprodId').select2('data')[0].object.profitType;
                var inFinProfitType = $('#changeToFinprodMarketId').select2('data')[0].object.profitType;
                if (outFinProfitType == PROFITTYPE_VALUE_03 && inFinProfitType == PROFITTYPE_VALUE_03) {
                    // 货币基金转货币基金：
                    // 币种、交易份额、待结转份额、净值日期、赎回费、申购费、转入基金确认份额=交易份额*转出基金单位净值-赎回费-申购费
                    $('#carryAmt_div').show();
                    $('#netDate_div').hide();
                    changeType = 1;

                } else if (outFinProfitType == PROFITTYPE_VALUE_03 && inFinProfitType == PROFITTYPE_VALUE_01) {
                    // 货币基金转非货币基金：
                    // 币种、交易份额、待结转份额、净值日期、转入基金单位净值、赎回费、申购费、转入基金确认份额=（交易份额*转出基金单位净值-赎回费-申购费）/转入基金单位净值
                    $('#carryAmt_div').show();
                    $(".unitCprice_in_div").show();
                    changeType = 2;

                } else if (outFinProfitType == PROFITTYPE_VALUE_01 && inFinProfitType == PROFITTYPE_VALUE_03) {
                    // 非货币基金转货币基金：
                    // 币种、交易份额、净值日期、转出基金单位净值、赎回费、申购费、转入基金确认份额=交易份额*转出基金单位净值-赎回费-申购费
                    $('#carryAmt_div').hide();
                    $(".unitCprice_out_div").show();
                    $("#unitCprice_out_label").text("转出基金单位净值");
                    changeType = 3;

                } else if (outFinProfitType == PROFITTYPE_VALUE_01 && inFinProfitType == PROFITTYPE_VALUE_01) {
                    // 非货币基金转非货币基金：
                    // 币种、交易份额、净值日期、转出基金单位净值、转入基金单位净值、赎回费、申购费、转入基金确认份额=（交易份额*转出基金单位净值-赎回费-申购费）/转入基金单位净值。
                    $('#carryAmt_div').hide();
                    $(".unitCprice_out_div").show();
                    $(".unitCprice_in_div").show();
                    $("#unitCprice_out_label").text("转出基金单位净值");
                    changeType = 4;

                }
            } else if ((ps == ps_T19 || ps == ps_T24)) {
                $('#proAvailableCash_div').hide();
                $('.channel_cash').hide();
                $('#proAvailableCash1_div').hide();
                $('#assetAvailablePosition_div').show();
                $('#expectedArrivalDate_div').show();
                $('#transferInformation').show();
                if ($('#type6').val() == type_01) {
                    $('#carryAmt_div').show();
                } else {
                    $('#carryAmt_div').hide();
                }
                $('#purchase').hide();
                $('#redemp').show();
                if ($("#tradeMarket").val() == TRADE_PLACE_03 || $("#tradeMarket").val() == TRADE_PLACE_04) {
                    $('#tradeMarketAccAvailableCash_div').show();
                    $('.commissionFee_class').show();
                } else {
                    $('#tradeMarketAccAvailableCash_div').hide();
                    $('.commissionFee_class').hide();
                }
//                $('#tradeAmt').attr('disabled',true);
                $('#shareAmt').attr('disabled', true);
                if (ps == ps_T24 && single == 1) {
                    $('#expectedArrivalDate').attr('disabled', false);
                    $('#expectedArrivalDate').attr('data-ignore', true);
                    $('#TrdFundInvestTrade003_btn_save').attr('data-ignore', true).removeClass("layui-btn-disabled");
                    ;
                    $('#TrdFundInvestTrade003_btn_submit').attr('data-ignore', true).removeClass("layui-btn-disabled");
                    ;
                    $('#TrdFundInvestTrade003_btn_risk').attr('data-ignore', true).removeClass("layui-btn-disabled");
                    ;
                }
                if (finprodId && portfolioId) {
                    if($("#calendarId").val()!=''){
                        getPtlCashPositionInfo();
                    }
                }
            } else {
                $('#proAvailableCash_div').show();
                $('.channel_cash').show();
                $('#proAvailableCash1_div').show();
                $('#assetAvailablePosition_div').hide();
                $('#expectedArrivalDate_div').hide();
                $('#transferInformation').hide();
                $('#carryAmt_div').hide();
                if (ps == ps_T05) {
                    $('#purchase').show();
                    $('#subscribe').hide();
                    if ($("#tradeMarket").val() == TRADE_PLACE_03 || $("#tradeMarket").val() == TRADE_PLACE_04) {
                        $('.commissionFee_class').show();
                    } else {
                        $('.commissionFee_class').hide();
                    }
                } else {
                    $('#purchase').hide();
                    $('#subscribe').show();
                }
                if ($("#tradeMarket").val() == TRADE_PLACE_03 || $("#tradeMarket").val() == TRADE_PLACE_04) {
                    $('#tradeMarketAccAvailableCash_div').show();
                } else {
                    $('#tradeMarketAccAvailableCash_div').hide();
                }
                $('#redemp').hide();
//                $('#tradeAmt').attr('disabled',false);
//                $('#shareAmt').attr('disabled',true);
                if (finprodId && portfolioId) {
                    if($("#calendarId").val()!=''){
                        getPtlCashPositionInfo();
                    }
                }
            }
        });
        //modify by gaopeng 20191018 页面优化 end
        /**
         * 基金类型
         */
        $('#type6').on('change', function () {
            if (!$("#changeToFinprodMarketId").val()) {
                var ps = $('#ps').val();
                var trmFundType = $("#trmFundType").val()
                if (pageModel == PAGE_MODEL_REFERENCE && trmFundType && trmFundType == '05'
                    && (ps == ps_T05 || ps == ps_T19 || ps == ps_T24)) {
                    $('#unitCprice').val('0.01').change().trigger('blur');
                    return;
                }

                if ($('#type6').val() == type_01 && (ps == ps_T19 || ps == ps_T24)) {
                    $('#carryAmt_div').show();
                } else {
                    $('#carryAmt_div').hide();
                }
                if ($('#type6').val() == type_01 && ($("#tradeMarket").val() == TRADE_PLACE_03 || $("#tradeMarket").val() == TRADE_PLACE_04)) {
                    $('#unitCprice').val(100).change().trigger('blur');
                    $('#netDate_div').hide();
                } else if ($('#type6').val() == type_01) {
                    $('#unitCprice').val(1).change().trigger('blur');
                    $('#netDate_div').hide();
                } else {
                    $('#netDate_div').show();
                }
            }
        });
        /**
         * 净值日期
         */
        $('#netDate').change(function (e, isInit) {
            var netDate = $('#netDate').val();
            var finprodId = $('#finprodId').select2('data')[0].object.finprodId;
            var tradeAmt = $('#tradeAmt').val();
            var type6 = $('#type6').val();
            if ($('#ps').val() == ps_T18) {
                $('#unitCprice').val(1).trigger('blur').change();
            } else if (netDate && finprodId && type6 == type_02) {
                var data = {finprodId: finprodId, netDate: netDate}
                if (pageModel != PAGE_MODEL_REFERENCE) {
                	 Joyin.ajaxJson(basePath + 'invest/trade/fund/TrdFundInvestTrade003/getPrice', data, function (result) {
                         $('#unitCprice').val(result).trigger('blur').trigger('change', isInit);
                     });
				}else{
					 $('#unitCprice').val(UNIT_CPRICE).trigger('blur').trigger('change', isInit);
				} 
            }

            if ($('#ps').val() == ps_T66) {
                var changeToFinprodMarketId = $('#changeToFinprodMarketId').select2('data')[0].object.finprodId;
                var inFinProfitType = $('#changeToFinprodMarketId').select2('data')[0].object.profitType;
                if (netDate && changeToFinprodMarketId && inFinProfitType == PROFITTYPE_VALUE_01) {
                    var data = {finprodId: changeToFinprodMarketId, netDate: netDate}
                    if (pageModel != PAGE_MODEL_REFERENCE) {
	                    Joyin.ajaxJson(basePath + 'invest/trade/fund/TrdFundInvestTrade003/getPrice', data, function (result) {
	                        $('#unitCpriceIn').val(result).trigger('blur').trigger('change', isInit);
	                    });
                    }else{
	   					 $('#unitCprice').val(UNIT_CPRICE).trigger('blur').trigger('change', isInit);
	   				} 
                }
            }
        });


        /**
         * 单位净价
         */
        $('#unitCpriceIn').change(function (e, isInit) {
            var ps = $('#ps').val();
            if (ps == ps_T66 && (typeof isInit === 'undefined' || !isInit)) {
                calcInShareAmt();
            }else  if (pageModel != PAGE_MODEL_REFERENCE) {
                calcInShareAmt();
            }
        });

        /**
         * 单位净价
         */
        $('#unitCprice').change(function (e, isInit) {
            var unitCprice = $('#unitCprice').val();
            var tradeAmt = $('#tradeAmt').val();
            var ps = $('#ps').val();
            var shareAmt = $('#shareAmt').val();

            if (ps == ps_T66 && (typeof isInit === 'undefined' || !isInit)) {
                calcInShareAmt();
            }
            else if (ps == ps_T05 || ps == ps_T18) {
                var tradeAmtHid = $('#tradeAmtHid').val();
                if (unitCprice != null && unitCprice != '' && unitCprice != 0) {
                    $('#shareAmt').val((Number(tradeAmt) - getPayWithTradeFee()) / unitCprice).trigger('blur');
                }
            } else if ((ps == ps_T19)) {
                if (unitCprice != null && unitCprice != '' && shareAmt) {
                    // var tradeAmtHid = shareAmt * unitCprice;
                    // $('#tradeAmt').val(tradeAmtHid).trigger('blur');
                    // $('#tradeAmtHid').val(tradeAmtHid).trigger('blur');
                    $('#feeAmtPay').change();
                }
            } else if (ps == ps_T24) {
                if (unitCprice != null && unitCprice != '' && shareAmt) {
                    $('#tradeAmt').val(tradeAmt).trigger('blur');
                    $('#tradeAmtHid').val(tradeAmt).trigger('blur');
                    $('#feeAmtPay').change();
                }
            }
        });
        /**
         * 组合代码
         */
        $('#portfolioId').change(function () {
            var portfolioId = $('#portfolioId').val();
            var ps = $('#ps').val();
            var finprodId = $('#finprodId').select2('data')[0].object.finprodId;
            if (ps == ps_T05 && finprodId && portfolioId) {
                getPtlCashPositionInfo();
            }
            if ((ps == ps_T19 || ps == ps_T24) && finprodId && portfolioId) {
                getPtlSecPositionInfo();
            }
        });
        /**
         * 认购费/申购费/赎回费
         */
        $('#feeAmtPay,#commissionFee,#feeAmtPayPurchase').change(function () {
            var ps = $('#ps').val();
            if (ps == ps_T66 && (pageModel == PAGE_MODEL_NEW || pageModel == PAGE_MODEL_EDIT)) {
                calcInShareAmt();
            }
            else if (ps == ps_T05 || ps == ps_T18) {
                var tradeAmtHid2 = $('#tradeAmtHid2').val();
                if (tradeAmtHid2) {
                    $('#tradeAmtHid').val(Number(tradeAmtHid2) - getPayWithTradeFee()).change().trigger('blur');
                    $('#tradeAmt').val($('#tradeAmtHid2').val());
                } else {
                    $('#tradeAmtHid').val(tradeAmtHid2).trigger('blur');
                }
            } else if (ps == ps_T19) {
                var tradeAmtHid = $('#tradeAmtHid').val();
                if (tradeAmt) {
                    $('#tradeAmt').val(Number(tradeAmtHid) - getPayWithTradeFee()).trigger('blur');
                    $('#carryAmt').change();
                } else {
                    $('#tradeAmt').val(tradeAmtHid).trigger('blur');
                    $('#carryAmt').change();
                }
            }
        });
        /**
         * 交易本金
         */
        $('#tradeAmtHid').change(function () {
            var tradeAmtHid = $('#tradeAmtHid').val();
            var ps = $('#ps').val();
            var unitCprice = $('#unitCprice').val();
            if (ps == ps_T05 || ps == ps_T18) {
                if (unitCprice && tradeAmtHid) {
                    $('#shareAmt').val(tradeAmtHid / unitCprice).trigger('blur');
                }
            }
        });
        /**
         * 交易金额
         */
        $('#tradeAmt').change(function () {
            var tradeAmt = $('#tradeAmt').val();
            var ps = $('#ps').val();
            var unitCprice = $('#unitCprice').val();
            if (unitCprice && tradeAmt) {
                if ($("#ps").val() != ps_T19) {
                    $('#shareAmt').val((Number(tradeAmt) - getPayWithTradeFee()) / unitCprice).trigger('blur');
                }
            }
        });

        $('#shareAmt').change(function () {
            var ps = $('#ps').val();
            if (ps == ps_T19) {
                var unitCprice = $('#unitCprice').val();
                var shareAmt = $('#shareAmt').val();
                if (unitCprice && shareAmt) {
                    // $('#tradeAmt').val(unitCprice * shareAmt + parseFloat(carryAmt) - getPayWithTradeFee()).trigger('blur');
                }
            }
        });
        /**
         * 预计到账日
         */
        $('#expectedArrivalDate').change(function () {
            var expectedArrivalDate = $('#expectedArrivalDate').val();
            if (expectedArrivalDate) {
                window.BussTrdTransferOrder['bussTrdTransferOrder'].generateClick();
            }
        });

        $("#channelAmt").change(function () {
            var ps = $('#ps').val();
            if (ps == ps_T66 && (pageModel == PAGE_MODEL_NEW || pageModel == PAGE_MODEL_EDIT)) {
                calcInShareAmt();
            }
            else {
                var channelAmt = $('#channelAmt').val() || 0;
                var feeAmtPay = $('#feeAmtPay').val() || 0;
                var tradeAmtHid = $('#tradeAmtHid').val() || 0;
                var carryAmt = $('#carryAmt').val() || 0;
                if ($("#ps").val() == ps_T19 && $("#chlId").val() && $("#chlId").select2("data")[0] && $("#chlId").select2("data")[0].object
                    && $("#chlId").select2("data")[0].object.collectType == collectType_01) {
                    // $('#tradeAmt').val(parseFloat(tradeAmtHid) + parseFloat(carryAmt) - getPayWithTradeFee()).trigger('blur');
                }
            }
        })

        /**
         * 待结转份额
         */
        $('#carryAmt').change(function () {
            var ps = $('#ps').val();
            if (ps == ps_T66 && (pageModel == PAGE_MODEL_NEW || pageModel == PAGE_MODEL_EDIT)) {
                calcInShareAmt();
            }
            else {
                var shareAmt = $('#shareAmt').val() || 0;
                var carryAmt = $('#carryAmt').val() || 0;
                var unitCprice = $('#unitCprice').val();
                if (shareAmt && unitCprice && ps != ps_T19) {
                    $('#tradeAmt').val(parseFloat(shareAmt) * parseFloat(unitCprice) + parseFloat(carryAmt) - getPayWithTradeFee()).trigger('blur');
                }
            }
        });

        window.componentLoadCallback = function (isDone, pagePath, params) {
            if (isDone) {
                if(typeof(BaseRiskMathComm) != 'undefined') {
                var riskparam = getriskdata();
                if (pageModel==PAGE_MODEL_NEW){
                    riskparam.data =null;
                }
                window.BaseRiskMathComm.getparam(riskparam);
                }
                window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                    var data = {};
                    data.counterId = $('#trdInvestPersonInfo_counterId').val();
                    data.tradeAmt = $('#tradeAmt').val();
                    data.profitType = $('#profitType').val();
                    data.ps = $('#ps').val();
                    data.finprodId = $('#finprodId').select2('data')[0].object.finprodId;
                    data.ccy = $('#ccy').val();
                    data.chlId = $("#chlId").val();
                    data.tradeDate = $('#tradeDate').val();
                    data.finprodType2 = $('#finprodType2').val();
                    data.profitType = $('#profitType').val();
                    data.tradeMarket = $('#tradeMarket').val();
                    data.secManageAcctId = $('#secManageAcctId').val();
                    if (data.ps == ps_T24) {
                        data.tradeDate = $('#expectedArrivalDate').val();
                    }
                    return {
                        'isTotalTrade': YES_OR_NO_N
                        , 'bankLendingType': null
                        , 'portfolioId': $("#portfolioId").val()
                        , 'deal': data
                    };
                });
//                if(entrustId != ''){
//                    if(etrEntrust){
//                        window.TrdInvestPersonInfo002['trdInvestPersonInfo'].setEntrust(function () {
//                            return etrEntrust;
//                        });
//                    }
//                }
            }
        }

        $('#ps').change();
        $('#type6').change();
        $('#finprodId').trigger("change", true);
        if ($('#ps').val() == ps_T19 && $('#type6').val() == type_01) {
            if (!$('#carryAmt').val()) {
                getcarryAmt();
            }
        }
        else if ($('#ps').val() == ps_T66 && $('#changeToFinprodMarketId').val() && $('#changeToFinprodMarketId').select2('data')[0].object) {
            var inFinProfitType = $('#changeToFinprodMarketId').select2('data')[0].object.profitType;
            if (($('#ps').val() == ps_T66 && inFinProfitType == PROFITTYPE_VALUE_03)) {
                if (!$('#carryAmt').val()) {
                    getcarryAmt(true);
                }
            }
        }
        var ps = $('#ps').val();
        if (ps == ps_T66 && pageModel != PAGE_MODEL_REFERENCE) {
            calcInShareAmt();
        }
    };

    /**
     * 画面加载完成
     */
    $(function () {
        //初始化画面
        init();
        // 绑定按钮事件
        $('#TrdFundInvestTrade003_btn_save').click(function () {
            saveOrSubmit('save');
        });
        $('#TrdFundInvestTrade003_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
        $('#TrdFundInvestTrade003_btn_view').click(function () {
            var finprodId = $('#finprodId').select2('data')[0].object.finprodId;
            if (finprodId) {
                Joyin.dialog(JoyinUtil.getLabel("FinFundTarget002.detail"),
                    basePath + "invest/asset/fund/FinFundTarget002/init",
                    "95%", "90%", {
                        pageModel: PAGE_MODEL_REFERENCE,
                        finprodId: finprodId
                    });
            }
        });
        //风险试算
        $('#TrdFundInvestTrade003_btn_risk').click(function(){
            //-----------add by jjpei 2020-01-07 风险新版本 start----------
            //---申购确认与到账不需要风险试算----
            if($("#ps").val()!=ps_T19){
                return;
            }
            var object = $.serializeToObject('#submitForm', true);
            var data = $('#finprodId').select2('data')[0].object;
            if (data && data.finprodId) {
                object.finprodId = data.finprodId;
                object.finprodMarketId = $('#finprodId').val();
            }
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/1/RSK002/F02', object,getriskdata());
            //-----------add by jjpei 2020-01-07 风险新版本 end----------
        })
         // 是否展示资产四五级分类
         if (IS_SHOW_TYPE == '01') {
             $('#type11Div').show();
         } else {
             $('#type11Div').hide();
         }
     });
 });