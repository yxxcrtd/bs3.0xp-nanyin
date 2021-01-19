//@ sourceURL=TrdFundInvestTrade003.js
/**
 * 基金投资交易确认
 */
$(function () {
	let changeType = null;

    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        var netDate = $('#netDate').val();
        var finprodId = $('#finprodId').select2('data')[0].object.finprodId;
        var tradeAmt = $('#tradeAmt').val();
        var type6 = $('#finprodId').select2('data')[0].object.type6;
        var unitCprice;
        if ($('#ps').val() == ps_T18) {
            unitCprice = 1;
        } else if (netDate && finprodId && type6 == type_02) {
            var data = {finprodId: finprodId, netDate: netDate}
            Joyin.ajaxJson(basePath + 'invest/trade/fund/TrdFundInvestTrade003/getPrice', data, function (result) {
                unitCprice = result;
            }, false);
        }
        if(!unitCprice){
            unitCprice = 1;
        }

        return {
            totleTradeId : totleTradeId,
            finprodId : finprodId,
            vdate : $("#tradeDate").val(),
            ccy : $("#ccy").val(),
            calId: $('#calendarId').val(),
            chlId: $("#chlId").val(),
            secAcctId: $("#secManageAcctId").val(),
            unitCprice : unitCprice,
            ps : $("#ps").val()
        }
    };

    var getriskdata = function () {
        var data = $('#finprodId').select2('data')[0].object;
        var finprodId;
        if (data && data.finprodId) {
            finprodId= data.finprodId;
        }

        var mun=null;
        var object = $.serializeToObject('#submitForm', true);
        var bussTrdTransfenpeiDto=null;
        if(object!=null&&object!=''&&object.bussTrdTransfenpeiDto!=null&&object.bussTrdTransfenpeiDto!=''){
            bussTrdTransfenpeiDto=object.bussTrdTransfenpeiDto;
        }
        var list=[];
        for(var i in bussTrdTransfenpeiDto){
            var obj = {
                timePoint: MY_SUP_TIME_TYPE_02,
                valDate: $('#tradeDate').val(),
                portfolioCode: bussTrdTransfenpeiDto[i].portfolioId,
                tradeAmount:bussTrdTransfenpeiDto[i].tradeAmt,
                assetType: FINPROD_TYPE_F06,
                isEnabled: '01',
                assetCode: finprodId,
                tradeDirection: $('#ps').val()
            }
            list[i]=obj;
        }
        var object = {
            valDate: ['tradeDate', 'TrdFundInvestTrade002.tradeDate'],
            portfolioCode: ['portfolioId', 'TrdFundInvestTrade002.portfolioId'],
            tradeAmount: ['tradeAmt', 'TrdFundInvestTrade002.tradeAmt'],
            assetType: ['type6', 'TrdFundInvestTrade002.type6'],
            tradeDirection: ['ps', 'TrdFundInvestTrade002.ps'],
            data: list
        }
        return object;
    }

    var table = $("#bussTrdTransfenpeiTable").joyintable({
        ajax: {
            url: 'selectTrdTransfenpeiList',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        searchOnInit:true,
        height:'225px',
        page: false,
        checkbox: false,
        loadonce: true,
        cols:[
            {field:'entrustId', width:100, title: '委托代码',sortable: false,disabled:true}
            ,{field:'doEntrustMoney', width:150, title: '剩余委托额度',sortable: false}
            ,{field:'portfolioId', width:150,  title: '组合代码',edit:false,sortable: false,
                editOption:{type:'select', id:'portfolioId', multiple: false, showField:'text',searchable: true,
                    url: JoyinUtil.getRealPath('/business/common/CommonSelect/selectPortfolioId?act=true')
                }
            }

            ,{field:'tradeAmt', width:150, title: '交易金额',sortable: false,type:'number', digit:2}
            ,{field:'shareAmt', width:150, title: '交易份额',sortable: false,type:'number', digit:2}
            ,{field:'changeInShareAmt', width:150, title: '转入基金确认份额',sortable: false,type:'number',visible:false, digit:2}
            ,{field:'carryAmt', width:150, title: '待结转份额',sortable: false,type:'number', visible:false,digit:2}
            ,{field:'unitCprice', width:150, title: '单位净值',sortable: false,type:'number', digit:4}
            // ,{field:'unitCpriceIn', width:150, title: '转入基金单位净值',sortable: false,type:'number', digit:2}

            ,{field:'feeAmtPay', width:150, title: ps_X == ps_T05?'申购费':'赎回费',edit:false,sortable: false,type:'number', digit:2}
            // ,{field:'feeAmtPayPurchase', width:150, title: '申购费',edit:true,sortable: false,type:'number', digit:2}
            ,{field:'channelFee', width:150, title: '通道费',edit:false,sortable: false,type:'number', digit:2}
            ,{field:'commissionAmt', width:150, title: '佣金',edit:false,sortable: false,type:'number',visible:false, digit:2}
            ,{field:'bussTrdAvailableCashQueryavalibleCash', width:150, title: '组合T日可用现金',sortable: false,type:'number', digit:2}
            ,{field:'bussTrdAvailableCashQueryavalibleCash1', width:150, title: '组合T+1日可用现金',sortable: false,type:'number', digit:2}
            ,{field:'channelAvailableCash', width:150, title: '通道可用现金',sortable: false,type:'number', digit:2}
            ,{field:'tradeMarketAccAvailableCash', width:150, title: '交易所资金账户可用现金',sortable: false,type:'number', digit:2}
            ,{field:'assetsavailablepositions', width:150,  title: '资产可用持仓',sortable: false,type:'number', digit:2}
            ,{field:'tradeAmtHid', width:150,  title: 'tradeAmtHid赎回确认时存：初始本金金额，认申购确认时存：本金-随交易支付的费用',sortable: false,type:'number', digit:2,visible:false}
            ,{field:'tradeAmtHid2', width:150,  title: 'tradeAmtHid2存初始本金金额，不会修改，认申购确认用',sortable: false,type:'number', digit:2 ,visible:false}
            ,{field:'bTradeId', width:150,  title: '嘿嘿阿哒',sortable: false,visible:false}
        ],
        done: function () {
            var tradeAmt = 0;
            var shareAmt = 0;
            var tradeAmtHid = 0;
            var tradeAmtHid2 = 0;
            for(var i = 0; i < table.getAllRows().length; i++){
                tradeAmt = JoyinMath.doAdd(tradeAmt, table.getAllRows()[i].tradeAmt, 2);
                shareAmt = JoyinMath.doAdd(shareAmt, table.getAllRows()[i].shareAmt, 2);
                tradeAmtHid = JoyinMath.doAdd(tradeAmtHid, table.getAllRows()[i].tradeAmtHid, 2);
                tradeAmtHid2 = JoyinMath.doAdd(tradeAmtHid2, table.getAllRows()[i].tradeAmtHid2, 2);
            }
            $('#tradeAmt').val(tradeAmt).trigger('blur').change();
            $('#shareAmt').val(shareAmt).trigger('blur').change();
            $('#tradeAmtHid').val(tradeAmtHid).trigger('blur').change();
            $('#tradeAmtHid2').val(tradeAmtHid2).trigger('blur').change();
        }
    });


    function saveOrSubmit(operateType) {
        var s;
        if(BaseRiskMathComm&&$('#ps').val()==ps_T19) {
            s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
            if(!s && s != ''){
                return;
            }
            if (window.BaseRiskMathComm.isCalcConditionChanged(getriskdata())){
                return;
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
        object.portfolioId = 'dzh';

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

        //组合中金额赋值
        debugger;
        for(var i in object.bussTrdTransfenpeiDto){
            if (object.ps == ps_T05 && object.bussTrdTransfenpeiDto[i].tradeAmt) {
                var obj = {row:i,
                    tradeAmt:table.getAllRows()[i].tradeAmt,
                    shareAmt:table.getAllRows()[i].shareAmt,
                    feeAmtPay:table.getAllRows()[i].feeAmtPay,
                    unitCprice:table.getAllRows()[i].unitCprice,
                    channelFee:table.getAllRows()[i].channelFee,
                    commissionAmt:table.getAllRows()[i].commissionAmt
                };
                object.bussTrdTransfenpeiDto[i].prinAmt = object.bussTrdTransfenpeiDto[i].tradeAmt - getPayWithTradeFee_DZH(obj);
            } else if (object.bussTrdTransfenpeiDto[i].shareAmt && object.unitCprice) {
                object.bussTrdTransfenpeiDto[i].prinAmt = Number(object.bussTrdTransfenpeiDto[i].shareAmt) * Number(object.unitCprice);
            } else {
                object.bussTrdTransfenpeiDto[i].prinAmt = object.bussTrdTransfenpeiDto[i].tradeAmt;
            }
            var intAmt = 0;
            if (object.bussTrdTransfenpeiDto[i].carryAmt) {
                intAmt = object.bussTrdTransfenpeiDto[i].carryAmt;
                object.bussTrdTransfenpeiDto[i].intAmt = object.bussTrdTransfenpeiDto[i].carryAmt;
            }
            object.bussTrdTransfenpeiDto[i].fpriceAmt = object.bussTrdTransfenpeiDto[i].prinAmt + Number(intAmt);
        }


        object.finprodMarketId = $('#finprodId').val();
        object.finprodId = $('#finprodId').select2('data')[0].object.finprodId;
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

        Joyin.ajaxJson(basePath + 'invest/trade/fund/TrdFundInvestTrade005/check', object, function (result) {
        	Joyin.ajaxJson(basePath + 'invest/trade/fund/TrdFundInvestTrade005/checkAuthorization', object, function(result1) {
	            Joyin.confirm(result.data, function () {
	                if (operateType === 'save') {
	                    if (object.ps == ps_T24) {
	                        WorkFlow.doSaveList(basePath + 'invest/trade/fund/TrdFundInvestTrade005/save', object, "TrdFundInvestTrade003_btn_total", "TrdFundInvestTrade005");
	                    } else {
	                        WorkFlow.doSaveList(basePath + 'invest/trade/fund/TrdFundInvestTrade005/save', object, "TrdFundInvestTrade003_btn_save", "TrdFundInvestTrade005");
	                    }
	                } else {
	                    if (object.ps == ps_T24) {
	                        WorkFlow.doSubmitList(basePath + 'invest/trade/fund/TrdFundInvestTrade005/save', object, "TrdFundInvestTrade003_btn_total", "TrdFundInvestTrade005");
	                    } else {
	                        WorkFlow.doSubmitList(basePath + 'invest/trade/fund/TrdFundInvestTrade005/save', object, "TrdFundInvestTrade003_btn_submit", "TrdFundInvestTrade005");
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

        var tradeAmtXX = 0;
        var shareAmtXX = 0;
        var tradeAmtHidXX = 0;
        var tradeAmtHid2XX = 0;
        for(var i = 0; i < table.getAllRows().length; i++){
            tradeAmtXX = JoyinMath.doAdd(tradeAmtXX, table.getAllRows()[i].tradeAmt, 2);
            shareAmtXX = JoyinMath.doAdd(shareAmtXX, table.getAllRows()[i].shareAmt, 2);
            tradeAmtHidXX = JoyinMath.doAdd(tradeAmtHidXX, table.getAllRows()[i].tradeAmtHid, 2);
            tradeAmtHid2XX = JoyinMath.doAdd(tradeAmtHid2XX, table.getAllRows()[i].tradeAmtHid2, 2);
        }
        $('#tradeAmt').val(tradeAmtXX).trigger('blur').change();
        $('#shareAmt').val(shareAmtXX).trigger('blur').change();
        $('#tradeAmtHid').val(tradeAmtHidXX).trigger('blur').change();
        $('#tradeAmtHid2').val(tradeAmtHid2XX).trigger('blur').change();


    	  if ($("#ps").val() == ps_T19) {
              if ( $("#chlId").val() && $("#chlId").select2("data")[0] && $("#chlId").select2("data")[0].object
                  && $("#chlId").select2("data")[0].object.collectType == collectType_01) {
                  $("#channelAmt_div").show();
                  $("#channelAmt").prop("disabled", false);
                  table.showColumns([{field:'channelFee',visible: true}]);
              }
          } else if ($("#ps").val() ==ps_T24) {
              $("#channelAmt_div").show();
              table.showColumns([{field:'channelFee',visible: true}]);
          }else {
              $("#channelAmt_div").hide();
              table.showColumns([{field:'channelFee',visible: false}]);
          }

        $('#finprodId').on('change', function (e, isInit) {
            var finprodId = $('#finprodId').select2('data')[0].object.finprodId;
            var ps = $('#ps').val();
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
            // if ((ps == ps_T05 || ps == ps_T18) && finprodId && portfolioId) {
            //     getPtlCashPositionInfo();
            // }
            // if ((ps == ps_T19 || ps == ps_T24) && finprodId && portfolioId) {
            //     getPtlSecPositionInfo();
            // }
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
            var type6 = $('#type6').val();
            $(".changein").hide();
            $("#purchaseFee_div1").hide();
            table.showColumns([{field:'feeAmtPayPurchase',visible: false}]);
            $("#changeInShareAmt_div").hide();
            table.showColumns([{field:'changeInShareAmt',visible: false}]);
            $(".unitCprice_in_div").hide();
            table.showColumns([{field:'channelFee',visible: false}]);
            // 基金转换特殊处理
             if ((ps == ps_T19 || ps == ps_T24)) {
                // $('#bussTrdAvailableCashQueryavalibleCash_div').hide();
                table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash',visible: false}]);
                $('.channel_cash').hide();
                // $('#bussTrdAvailableCashQueryavalibleCash1_div').hide();
                table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash1',visible: false}]);
                // $('#assetAvailablePosition_div').show();
                table.showColumns([{field:'assetsavailablepositions',visible: true}]);
                $('#expectedArrivalDate_div').show();
                $('#transferInformation').show();
                if ($('#type6').val() == type_01) {
                    $('#carryAmt_div').show();
                    table.showColumns([{field:'carryAmt',visible: true}]);
                } else {
                    $('#carryAmt_div').hide();
                    table.showColumns([{field:'carryAmt',visible: false}]);
                }
                $('#purchase').hide();
                $('#redemp').show();
                if ($("#tradeMarket").val() == TRADE_PLACE_03 || $("#tradeMarket").val() == TRADE_PLACE_04) {
                    $('#tradeMarketAccAvailableCash_div').show();
                    table.showColumns([{field:'tradeMarketAccAvailableCash',visible: true}]);
                    $('.commissionFee_class').show();
                    table.showColumns([{field:'commissionAmt',visible: true}]);
                } else {
                    $('#tradeMarketAccAvailableCash_div').hide();
                    table.showColumns([{field:'tradeMarketAccAvailableCash',visible: false}]);
                    $('.commissionFee_class').hide();
                    table.showColumns([{field:'commissionAmt',visible: false}]);
                }
//                $('#tradeAmt').attr('disabled',true);
                $('#shareAmt').attr('disabled', true);
                if (ps == ps_T24 && single == 1) {
                    $('#expectedArrivalDate').attr('disabled', false);
                    $('#expectedArrivalDate').attr('data-ignore', true);
                    $('#TrdFundInvestTrade005_btn_save').attr('data-ignore', true).removeClass("layui-btn-disabled");
                    ;
                    $('#TrdFundInvestTrade005_btn_submit').attr('data-ignore', true).removeClass("layui-btn-disabled");
                    ;
                    $('#TrdFundInvestTrade005_btn_risk').attr('data-ignore', true).removeClass("layui-btn-disabled");
                    ;
                }
                if ( $("#chlId").val() && $("#chlId").select2("data")[0] && $("#chlId").select2("data")[0].object
                    && $("#chlId").select2("data")[0].object.collectType == collectType_01) {
                    $("#channelAmt_div").show();
                    $("#channelAmt").prop("disabled", false);
                    table.showColumns([{field:'channelFee',visible: true}]);
                }
                // if (finprodId && portfolioId) {
                //     getPtlSecPositionInfo();
                // }
            } else {
                // $('#bussTrdAvailableCashQueryavalibleCash_div').show();
                table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash',visible: true}]);
                $('.channel_cash').show();
                // $('#bussTrdAvailableCashQueryavalibleCash1_div').show();
                table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash1',visible: true}]);
                // $('#assetAvailablePosition_div').hide();
                table.showColumns([{field:'assetsavailablepositions',visible: false}]);
                $('#expectedArrivalDate_div').hide();
                $('#transferInformation').hide();
                $('#carryAmt_div').hide();
                table.showColumns([{field:'carryAmt',visible: false}]);
                if (ps == ps_T05) {
                    $('#purchase').show();
                    $('#subscribe').hide();
                    if ($("#tradeMarket").val() == TRADE_PLACE_03 || $("#tradeMarket").val() == TRADE_PLACE_04) {
                        $('.commissionFee_class').show();
                        table.showColumns([{field:'commissionAmt',visible: true}]);
                    } else {
                        $('.commissionFee_class').hide();
                        table.showColumns([{field:'commissionAmt',visible: false}]);
                    }
                } else {
                    $('#purchase').hide();
                    $('#subscribe').show();
                }
                if ($("#tradeMarket").val() == TRADE_PLACE_03 || $("#tradeMarket").val() == TRADE_PLACE_04) {
                    $('#tradeMarketAccAvailableCash_div').show();
                    table.showColumns([{field:'tradeMarketAccAvailableCash',visible: true}]);
                } else {
                    $('#tradeMarketAccAvailableCash_div').hide();
                    table.showColumns([{field:'tradeMarketAccAvailableCash',visible: false}]);
                }
                $('#redemp').hide();
//                $('#tradeAmt').attr('disabled',false);
//                $('#shareAmt').attr('disabled',true);
//                 if (finprodId && portfolioId) {
//                     getPtlCashPositionInfo();
//                 }
            }
        });
        //modify by gaopeng 20191018 页面优化 end
        /**
         * 基金类型
         */
        $('#type6').on('change', function () {
            if (!$("#changeToFinprodMarketId").val()) {
                var ps = $('#ps').val();
                if ($('#type6').val() == type_01 && (ps == ps_T19 || ps == ps_T24)) {
                    $('#carryAmt_div').show();
                    table.showColumns([{field:'carryAmt',visible: true}]);
                } else {
                    $('#carryAmt_div').hide();
                    table.showColumns([{field:'carryAmt',visible: false}]);
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
            debugger;
            var netDate = $('#netDate').val();
            var finprodId = $('#finprodId').select2('data')[0].object.finprodId;
            var tradeAmt = $('#tradeAmt').val();
            var type6 = $('#type6').val();
            if ($('#ps').val() == ps_T18) {
                $('#unitCprice').val(1).trigger('blur').change();
                unitCpriceChange_DZH(1);
            } else if (netDate && finprodId && type6 == type_02) {
                var data = {finprodId: finprodId, netDate: netDate, tradeType:'dzh'}
                Joyin.ajaxJson(basePath + 'invest/trade/fund/TrdFundInvestTrade003/getPrice', data, function (result) {
                    if(result == 100){
                        $('#unitCprice').val('');
                        unitCpriceChange_DZH(100);
                    }else{
                        $('#unitCprice').val(result).trigger('blur').trigger('change', isInit);
                        unitCpriceChange_DZH(result);
                    }

                });
            }
        });

        /**
         * 组合分配：单位净值  净值日期发生改变引起组合分配中单位净值发生改变
         */
        var unitCpriceChange_DZH = function (unitCprice) {
            debugger;
            for(var i=0;i<table.getAllRows().length;i++){
                var obj = {row:i,
                    tradeAmt:table.getAllRows()[i].tradeAmt,
                    shareAmt:table.getAllRows()[i].shareAmt,
                    feeAmtPay:table.getAllRows()[i].feeAmtPay,
                    unitCprice:unitCprice,
                    channelFee:table.getAllRows()[i].channelFee,
                    commissionAmt:table.getAllRows()[i].commissionAmt
                }
                if(unitCprice == 100){
                    table.renderRow(i,  {"unitCprice": ' '});
                }else{
                    table.renderRow(i,  {"unitCprice": unitCprice});
                    unitCpriceDZHChange_DZH(obj);
                }
            }
            if(unitCprice != 100){
                tradeAmtDistribution();
            }
            shareAmtDistribution();
        }


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
                    var tradeAmtHid = shareAmt * unitCprice;
                    $('#tradeAmt').val(tradeAmtHid).trigger('blur');
                    $('#tradeAmtHid').val(tradeAmtHid).trigger('blur');
                    $('#feeAmtPay').change();
                }
            } else if (ps == ps_T24) {
                if (unitCprice != null && unitCprice != '' && shareAmt) {
                    $('#tradeAmt').val(tradeAmt).trigger('blur');
                    $('#tradeAmtHid').val(tradeAmt).trigger('blur');
                    $('#feeAmtPay').change();
                }
            }
            unitCpriceChange_DZH(unitCprice);
        });

        /**
         * 认购费/申购费/赎回费
         */
        $('#feeAmtPay,#commissionFee,#feeAmtPayPurchase').change(function () {
            var ps = $('#ps').val();
             if (ps == ps_T05 || ps == ps_T18) {
                var tradeAmtHid2 = $('#tradeAmtHid2').val();
                if (tradeAmtHid2) {
                    $('#tradeAmtHid').val(Number(tradeAmtHid2) - getPayWithTradeFee()).change().trigger('blur');
                    $('#tradeAmt').val($('#tradeAmtHid2').val());
                } else {
                    $('#tradeAmtHid').val(tradeAmtHid2).trigger('blur');
                }
                feeAmtPayDistribution();
                commissionAmtDistributionByTra();
                tradeAmtDistribution();
                tradeAmtHidChange_DZH();
                shareAmtDistribution();
            } else if (ps == ps_T19) {
                var tradeAmtHid = $('#tradeAmtHid').val();
                if (tradeAmt) {
                    $('#tradeAmt').val(Number(tradeAmtHid) - getPayWithTradeFee()).trigger('blur');
                    $('#carryAmt').change();
                } else {
                    $('#tradeAmt').val(tradeAmtHid).trigger('blur');
                    $('#carryAmt').change();
                }
                feeAmtRedemDistribution();
                commissionAmtDistributionBySha();
                tradeAmtDistribution();
                tradeAmtHidChange_DZH();
                shareAmtDistribution();
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
                $('#shareAmt').val((Number(tradeAmt) - getPayWithTradeFee()) / unitCprice).trigger('blur');
                // tradeAmtChange_DZH();
            }
        });

        $('#shareAmt').change(function () {
            var ps = $('#ps').val();
            if (ps == ps_T19) {
                var unitCprice = $('#unitCprice').val();
                var shareAmt = $('#shareAmt').val();
                var carryAmt = $('#carryAmt').val() || 0;
                if (unitCprice && shareAmt) {
                    $('#tradeAmt').val(unitCprice * shareAmt + parseFloat(carryAmt) - getPayWithTradeFee()).trigger('blur');
                    // shareAmtChange_DZH();
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
            var channelAmt = $('#channelAmt').val() || 0;
            var feeAmtPay = $('#feeAmtPay').val() || 0;
            var tradeAmtHid = $('#tradeAmtHid').val() || 0;
            var carryAmt = $('#carryAmt').val() || 0;
            var unitCprice = $('#unitCprice').val() || 1;
            var shareAmt = $('#shareAmt').val() || 0;
            if ($("#ps").val() == ps_T19 && $("#chlId").val() && $("#chlId").select2("data")[0] && $("#chlId").select2("data")[0].object
                && $("#chlId").select2("data")[0].object.collectType == collectType_01) {
                $('#tradeAmt').val(unitCprice * shareAmt + parseFloat(carryAmt) - getPayWithTradeFee()).trigger('blur');
                channelFeeRedemDistribution();
                // tradeAmtDistributionByChannelAmt();
                tradeAmtDistribution();
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
                if (shareAmt && unitCprice) {
                    $('#tradeAmt').val(parseFloat(shareAmt) * parseFloat(unitCprice) + parseFloat(carryAmt) - getPayWithTradeFee()).trigger('blur');
                    carryAmtDistribution();
                }
            }
        });

        window.componentLoadCallback = function (isDone, pagePath, params) {
            if (isDone) {
                var riskparam = getriskdata();
                if (pageModel==PAGE_MODEL_NEW){
                    riskparam.data =null;
                }
                window.BaseRiskMathComm.getparam(riskparam);
                window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                    var list = new Array();
                    var tempList = table.getAllRows();
                    for(var row in tempList){
                        var dealInfo = {};
                        dealInfo.counterId = $('#trdInvestPersonInfo_counterId').val();
                        dealInfo.tradeAmt = tempList[row].tradeAmt;
                        dealInfo.profitType = $('#profitType').val();
                        dealInfo.ps = $('#ps').val();
                        dealInfo.finprodId = $('#finprodId').select2('data')[0].object.finprodId;
                        dealInfo.portfolioId = tempList[row].portfolioId;
                        dealInfo.ccy = $('#ccy').val();
                        dealInfo.chlId = $("#chlId").val();
                        dealInfo.tradeDate = $('#tradeDate').val();
                        dealInfo.finprodType2 = $('#finprodType2').val();
                        dealInfo.tradeMarket = $('#tradeMarket').val();
                        dealInfo.secManageAcctId = $('#secManageAcctId').val();
                        if (dealInfo.ps == ps_T24) {
                            dealInfo.tradeDate = $('#expectedArrivalDate').val();
                        }
                        list.push(dealInfo);
                    }

                    return {
                        'isTotalTrade':YES_OR_NO_N
                        ,'bankLendingType':null
                        ,'portfolioId':'dzh'
                        ,'jiaoyiType':'dzh'
                        ,'deal':list
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
        // if ($('#ps').val() == ps_T19 && $('#type6').val() == type_01) {
        //         //     if (!$('#carryAmt').val()) {
        //         //         getcarryAmt();
        //         //     }
        //         // }
        // else if ($('#ps').val() == ps_T66 && $('#changeToFinprodMarketId').val() && $('#changeToFinprodMarketId').select2('data')[0].object) {
        //     var inFinProfitType = $('#changeToFinprodMarketId').select2('data')[0].object.profitType;
        //     if (($('#ps').val() == ps_T66 && inFinProfitType == PROFITTYPE_VALUE_03)) {
        //         if (!$('#carryAmt').val()) {
        //             getcarryAmt(true);
        //         }
        //     }
        // }
        // var ps = $('#ps').val();
        // if (ps == ps_T66 && pageModel != PAGE_MODEL_REFERENCE) {
        //     calcInShareAmt();
        // }
    };

    var getPayWithTradeFee_DZH = function (object) {
        var ps = $('#ps').val();
        var feeAmtPay = object.feeAmtPay;
        if (!feeAmtPay) {
            feeAmtPay = 0;
        }
        var channelFee = object.channelFee;
        if (!channelFee) {
            channelFee = 0;
        }
        if (!($("#chlId").val() && $("#chlId").select2("data")[0] && $("#chlId").select2("data")[0].object
            && $("#chlId").select2("data")[0].object.collectType == collectType_01)) {
            channelFee = 0;
        }
        // 佣金
        var commissionAmt = object.commissionAmt;
        if (!commissionAmt) {
            commissionAmt = 0;
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
                            commissionAmt = 0;
                        }
                        // $("#isPayWithTrade").val(result.isPayWithTrade);
                    }
                    // else {
                    //     $("#isPayWithTrade").val('');
                    // }
                }, false);
        }
        return Number(feeAmtPay) + Number(channelFee) + Number(commissionAmt)
    };

    // 组合分配：单位净值Change  单位净值发生改变引起组合分配中金额发生改变
    var unitCpriceDZHChange_DZH = function (object) {
        var unitCprice = object.unitCprice;
        var tradeAmt = object.tradeAmt;
        var ps = $('#ps').val();
        var shareAmt = object.shareAmt;
        var row = object.row;

        // if (ps == ps_T66 && (typeof isInit === 'undefined' || !isInit)) {
        //     // calcInShareAmt();
        // }
        // else
        if (ps == ps_T05 || ps == ps_T18) {
            // var tradeAmtHid = $('#tradeAmtHid').val();
            if (unitCprice != null && unitCprice != '' && unitCprice != 0) {
                // $('#shareAmt').val((Number(tradeAmt) - getPayWithTradeFee()) / unitCprice).trigger('blur');
                table.renderRow(row,  {"shareAmt": (Number(tradeAmt) - getPayWithTradeFee_DZH(object)) / unitCprice});
            }
        } else if ((ps == ps_T19)) {
            if (unitCprice != null && unitCprice != '' && shareAmt) {
                var tradeAmtHid = shareAmt * unitCprice;
                // $('#tradeAmt').val(tradeAmtHid).trigger('blur');
                // $('#tradeAmtHid').val(tradeAmtHid).trigger('blur');
                // $('#feeAmtPay').change();
                table.renderRow(row,  {"tradeAmt": tradeAmtHid});
                table.renderRow(row,  {"tradeAmtHid": tradeAmtHid});

            }
        } else if (ps == ps_T24) {
            if (unitCprice != null && unitCprice != '' && shareAmt) {
                // $('#tradeAmt').val(tradeAmt).trigger('blur');
                // $('#tradeAmtHid').val(tradeAmt).trigger('blur');
                // $('#feeAmtPay').change();
                table.renderRow(row,  {"tradeAmt": tradeAmt});
                table.renderRow(row,  {"tradeAmtHid": tradeAmt});
            }
        }
    }

    function shareAmtDistribution() {
        if ($('#ps').val() != ps_T05 || $('#unitCprice').val() == null || $('#unitCprice').val() == ''){
            return;
        }
        var allRows = table.getAllRows();
        //先按照本金金额排序
        function sortValue(a,b){
            return a.tradeAmt-b.tradeAmt;
        }
        allRows =  allRows.sort(sortValue);
        table.clear();
        table.addRow(allRows);

        var shareAmtAll = $('#shareAmt').val();
        var rownum = 0;
        var sumAmt = 0;
        //寻求表格数据本金金额非零最后索引值
        for(var i=0;i<table.getAllRows().length;i++){
            var a = table.getAllRows()[i].shareAmt;
            if(a && Number(a) != Number(0)){
                rownum = i;
            }
        }
        for (var i = 0; i < table.getAllRows().length; i++) {
            var carryAmt = table.getAllRows()[i].carryAmt;
            var tradeAmt = table.getAllRows()[i].tradeAmt;
            var unitCprice = $('#unitCprice').val();
            var object = {row:i,
                tradeAmt:table.getAllRows()[i].tradeAmt,
                shareAmt:table.getAllRows()[i].shareAmt,
                feeAmtPay:table.getAllRows()[i].feeAmtPay,
                unitCprice:table.getAllRows()[i].unitCprice,
                channelFee:table.getAllRows()[i].channelFee,
                commissionAmt:table.getAllRows()[i].commissionAmt
            }
            var shareAmtz = 0;
            if(i== rownum){//最后一个组合采用倒减法
                shareAmtz = JoyinMath.doMinus(shareAmtAll,sumAmt,2);
            }else{
                shareAmtz = (Number(tradeAmt) - getPayWithTradeFee_DZH(object)) / unitCprice;
                sumAmt = JoyinMath.doAdd(sumAmt, shareAmtz, 2);
            }
            table.renderRow(i, {'shareAmt': JoyinMath.doAdd(shareAmtz,0,2)});
            // table.renderRow(i, {'shareAmt': shareAmtz});
            //
            // table.renderRow(row,  {"shareAmt": (Number(tradeAmt) - getPayWithTradeFee_DZH(object)) / unitCprice});
            // table.renderRow(i, {'shareAmt': tradeAmtHid / unitCprice});
        }
    }

    //feeAmtPay申购费   按tradeAmt占比
    function feeAmtPayDistribution(){
        var feeAmtPay = 0;
        var feeAmtPayAll = $("#feeAmtPay").val() || 0;
        var tradeAmtAll = $("#tradeAmt").val();
        if(tradeAmtAll){
            var rownum = 0;
            var sumAmt = 0;
            //寻求表格数据本金金额非零最后索引值
            for(var i=0;i<table.getAllRows().length;i++){
                var a = table.getAllRows()[i].tradeAmt;
                if(a && Number(a) != Number(0)){
                    rownum = i;
                }
            }
            for(var i=0;i<table.getAllRows().length;i++){
                var aa = table.getAllRows()[i].tradeAmt;
                if(i== rownum&& Number(aa) != Number(0)){//最后一个组合采用倒减法
                    if(aa ){
                        feeAmtPay = JoyinMath.doMinus(feeAmtPayAll,sumAmt,2);
                    }else{
                        feeAmtPay = JoyinMath.doAdd(0,0,2);
                    }
                }else{
                    if(aa && Number(aa) != Number(0)){
                        var scale = JoyinMath.doMul(feeAmtPayAll,aa,6);
                        feeAmtPay = JoyinMath.doDiv(scale, tradeAmtAll, 2);
                    }else{
                        feeAmtPay = JoyinMath.doAdd(0,0,2);
                    }
                    sumAmt = JoyinMath.doAdd(sumAmt, feeAmtPay, 2);
                }
                table.renderRow(i,  {"feeAmtPay": feeAmtPay});
            }
        }
    }
    //feeAmtPay赎回费   按shareAmt占比
    function feeAmtRedemDistribution(){
        var feeAmtPay = 0;
        var feeAmtPayAll = $("#feeAmtPay").val() || 0;
        var shareAmtAll = $("#shareAmt").val();
        if(shareAmtAll){
            var rownum = 0;
            var sumAmt = 0;
            //寻求表格数据本金金额非零最后索引值
            for(var i=0;i<table.getAllRows().length;i++){
                var a = table.getAllRows()[i].shareAmt;
                if(a && Number(a) != Number(0)){
                    rownum = i;
                }
            }
            for(var i=0;i<table.getAllRows().length;i++){
                var aa = table.getAllRows()[i].shareAmt;
                if(i== rownum&& Number(aa) != Number(0)){//最后一个组合采用倒减法
                    if(aa ){
                        feeAmtPay = JoyinMath.doMinus(feeAmtPayAll,sumAmt,2);
                    }else{
                        feeAmtPay = JoyinMath.doAdd(0,0,2);
                    }
                }else{
                    if(aa && Number(aa) != Number(0)){
                        var scale = JoyinMath.doMul(feeAmtPayAll,aa,6);
                        feeAmtPay = JoyinMath.doDiv(scale, shareAmtAll, 2);
                    }else{
                        feeAmtPay = JoyinMath.doAdd(0,0,2);
                    }
                    sumAmt = JoyinMath.doAdd(sumAmt, feeAmtPay, 2);
                }
                table.renderRow(i,  {"feeAmtPay": feeAmtPay});
            }
        }
    }

    //commissionAmt佣金  按tradeAmt占比
    function commissionAmtDistributionByTra(){
        var feeAmtPay = 0;
        var feeAmtPayAll = $("#commissionFee").val() || 0;
        var tradeAmtAll = $("#tradeAmt").val();
        if(tradeAmtAll){
            var rownum = 0;
            var sumAmt = 0;
            //寻求表格数据本金金额非零最后索引值
            for(var i=0;i<table.getAllRows().length;i++){
                var a = table.getAllRows()[i].tradeAmt;
                if(a && Number(a) != Number(0)){
                    rownum = i;
                }
            }
            for(var i=0;i<table.getAllRows().length;i++){
                var aa = table.getAllRows()[i].tradeAmt;
                if(i== rownum&& Number(aa) != Number(0)){//最后一个组合采用倒减法
                    if(aa ){
                        feeAmtPay = JoyinMath.doMinus(feeAmtPayAll,sumAmt,2);
                    }else{
                        feeAmtPay = JoyinMath.doAdd(0,0,2);
                    }
                }else{
                    if(aa && Number(aa) != Number(0)){
                        var scale = JoyinMath.doMul(feeAmtPayAll,aa,6);
                        feeAmtPay = JoyinMath.doDiv(scale, tradeAmtAll, 2);
                    }else{
                        feeAmtPay = JoyinMath.doAdd(0,0,2);
                    }
                    sumAmt = JoyinMath.doAdd(sumAmt, feeAmtPay, 2);
                }
                table.renderRow(i,  {"commissionAmt": feeAmtPay});
            }
        }
    }
    //commissionAmt佣金   按shareAmt占比
    function commissionAmtDistributionBySha(){
        var feeAmtPay = 0;
        var feeAmtPayAll = $("#commissionFee").val() || 0;
        var shareAmtAll = $("#shareAmt").val();
        if(shareAmtAll){
            var rownum = 0;
            var sumAmt = 0;
            //寻求表格数据本金金额非零最后索引值
            for(var i=0;i<table.getAllRows().length;i++){
                var a = table.getAllRows()[i].shareAmt;
                if(a && Number(a) != Number(0)){
                    rownum = i;
                }
            }
            for(var i=0;i<table.getAllRows().length;i++){
                var aa = table.getAllRows()[i].shareAmt;
                if(i== rownum&& Number(aa) != Number(0)){//最后一个组合采用倒减法
                    if(aa ){
                        feeAmtPay = JoyinMath.doMinus(feeAmtPayAll,sumAmt,2);
                    }else{
                        feeAmtPay = JoyinMath.doAdd(0,0,2);
                    }
                }else{
                    if(aa && Number(aa) != Number(0)){
                        var scale = JoyinMath.doMul(feeAmtPayAll,aa,6);
                        feeAmtPay = JoyinMath.doDiv(scale, shareAmtAll, 2);
                    }else{
                        feeAmtPay = JoyinMath.doAdd(0,0,2);
                    }
                    sumAmt = JoyinMath.doAdd(sumAmt, feeAmtPay, 2);
                }
                table.renderRow(i,  {"commissionAmt": feeAmtPay});
            }
        }
    }

    //通道费
    function channelFeeRedemDistribution(){
        var channelFee = 0;
        var feeAmtPayAll = $("#channelAmt").val() || 0;
        var shareAmtAll = $("#shareAmt").val();
        if(shareAmtAll){
            var rownum = 0;
            var sumAmt = 0;
            //寻求表格数据本金金额非零最后索引值
            for(var i=0;i<table.getAllRows().length;i++){
                var a = table.getAllRows()[i].shareAmt;
                if(a && Number(a) != Number(0)){
                    rownum = i;
                }
            }
            for(var i=0;i<table.getAllRows().length;i++){
                var aa = table.getAllRows()[i].shareAmt;
                if(i== rownum&& Number(aa) != Number(0)){//最后一个组合采用倒减法
                    if(aa ){
                        channelFee = JoyinMath.doMinus(feeAmtPayAll,sumAmt,2);
                    }else{
                        channelFee = JoyinMath.doAdd(0,0,2);
                    }
                }else{
                    if(aa && Number(aa) != Number(0)){
                        var scale = JoyinMath.doMul(feeAmtPayAll,aa,6);
                        channelFee = JoyinMath.doDiv(scale, shareAmtAll, 2);
                    }else{
                        channelFee = JoyinMath.doAdd(0,0,2);
                    }
                    sumAmt = JoyinMath.doAdd(sumAmt, channelFee, 2);
                }
                table.renderRow(i,  {"channelFee": channelFee});
            }
        }
    }

    //carryAmt待结转金额   按shareAmt占比
    function carryAmtDistribution(){
        debugger;
        var shareAmt = 0;
        for(var i = 0; i < table.getAllRows().length; i++){
            shareAmt = JoyinMath.doAdd(shareAmt, table.getAllRows()[i].shareAmt, 2);
        }
        var feeAmtPay = 0;
        var feeAmtPayAll = $("#carryAmt").val() || 0;
        var shareAmtAll = shareAmt;
        if(shareAmtAll){
            var rownum = 0;
            var sumAmt = 0;
            //寻求表格数据本金金额非零最后索引值
            for(var i=0;i<table.getAllRows().length;i++){
                var a = table.getAllRows()[i].shareAmt;
                if(a && Number(a) != Number(0)){
                    rownum = i;
                }
            }
            for(var i=0;i<table.getAllRows().length;i++){
                var aa = table.getAllRows()[i].shareAmt;
                if(i== rownum&& Number(aa) != Number(0)){//最后一个组合采用倒减法
                    if(aa ){
                        feeAmtPay = JoyinMath.doMinus(feeAmtPayAll,sumAmt,2);
                    }else{
                        feeAmtPay = JoyinMath.doAdd(0,0,2);
                    }
                }else{
                    if(aa && Number(aa) != Number(0)){
                        var scale = JoyinMath.doMul(feeAmtPayAll,aa,6);
                        feeAmtPay = JoyinMath.doDiv(scale, shareAmtAll, 2);
                    }else{
                        feeAmtPay = JoyinMath.doAdd(0,0,2);
                    }
                    sumAmt = JoyinMath.doAdd(sumAmt, feeAmtPay, 2);
                }
                table.renderRow(i,  {"carryAmt": feeAmtPay});
            }
        }
        tradeAmtDistributionByCarryAmt();
    }

    // function tradeAmtDistributionByChannelAmt() {
    //     var ps = $('#ps').val();
    //     if (ps == ps_T66 && (pageModel == PAGE_MODEL_NEW || pageModel == PAGE_MODEL_EDIT)) {
    //
    //     }
    //     else {
    //         if ($("#ps").val() == ps_T19 && $("#chlId").val() && $("#chlId").select2("data")[0] && $("#chlId").select2("data")[0].object
    //             && $("#chlId").select2("data")[0].object.collectType == collectType_01) {
    //             for (var i = 0; i < table.getAllRows().length; i++) {
    //                 var channelFee = table.getAllRows()[i].channelFee;
    //                 var tradeAmtHid = table.getAllRows()[i].tradeAmtHid;
    //                 var carryAmt = table.getAllRows()[i].carryAmt;
    //                 var object = {row:i,
    //                     tradeAmt:table.getAllRows()[i].tradeAmt,
    //                     shareAmt:table.getAllRows()[i].shareAmt,
    //                     feeAmtPay:table.getAllRows()[i].feeAmtPay,
    //                     unitCprice:table.getAllRows()[i].unitCprice,
    //                     channelFee:table.getAllRows()[i].channelFee,
    //                     commissionAmt:table.getAllRows()[i].commissionAmt
    //                 }
    //                 // $('#tradeAmt').val(parseFloat(tradeAmtHid) + parseFloat(carryAmt) - getPayWithTradeFee()).trigger('blur');
    //                 table.renderRow(i, {"tradeAmt": parseFloat(tradeAmtHid) + parseFloat(carryAmt) - getPayWithTradeFee_DZH(object)});
    //             }
    //         }
    //     }
    // }

    function tradeAmtDistribution() {
        debugger;
        var allRows = table.getAllRows();
        //先按照本金金额排序
        function sortValue(a,b){
            return a.shareAmt-b.shareAmt;
        }
        allRows =  allRows.sort(sortValue);
        table.clear();
        table.addRow(allRows);

        var tradeAmtAll = $('#tradeAmt').val();
        var rownum = 0;
        var sumAmt = 0;
        //寻求表格数据本金金额非零最后索引值
        for(var i=0;i<table.getAllRows().length;i++){
            var a = table.getAllRows()[i].tradeAmt;
            if(a && Number(a) != Number(0)){
                rownum = i;
            }
        }
        for (var i = 0; i < table.getAllRows().length; i++) {
            var carryAmt = table.getAllRows()[i].carryAmt;
            var shareAmt = table.getAllRows()[i].shareAmt;
            var unitCprice = $('#unitCprice').val();
            var object = {row:i,
                tradeAmt:table.getAllRows()[i].tradeAmt,
                shareAmt:table.getAllRows()[i].shareAmt,
                feeAmtPay:table.getAllRows()[i].feeAmtPay,
                unitCprice:table.getAllRows()[i].unitCprice,
                channelFee:table.getAllRows()[i].channelFee,
                commissionAmt:table.getAllRows()[i].commissionAmt
            }
            var ps = $('#ps').val();
            if (ps == ps_T05) {//认购确认
                var tradeAmtHid2 = table.getAllRows()[i].tradeAmtHid2;
                if (tradeAmtHid2) {
                    // $('#tradeAmtHid').val(Number(tradeAmtHid2) - getPayWithTradeFee()).change().trigger('blur');
                    // $('#tradeAmt').val($('#tradeAmtHid2').val());
                    table.renderRow(i, {'tradeAmt': JoyinMath.doAdd(tradeAmtHid2,0,2)});
                    table.renderRow(i, {'tradeAmtHid': Number(tradeAmtHid2) - getPayWithTradeFee_DZH(object)});
                } else {
                    // $('#tradeAmtHid').val(tradeAmtHid2).trigger('blur');
                    table.renderRow(i, {'tradeAmtHid': tradeAmtHid2});
                }
            } else if (ps == ps_T19) {//赎回确认
                var tradeAmtHid = table.getAllRows()[i].tradeAmtHid;
                if (tradeAmt) {
                    // $('#tradeAmt').val(Number(tradeAmtHid) - getPayWithTradeFee()).trigger('blur');
                    // $('#carryAmt').change();
                    // 1、交易金额=交易份额*单位净值-赎回费-通道费【非货币基金】
                    // 2、交易金额=交易份额+待结转份额-通道费-赎回费【货币基金】
                    if ($('#type6').val() == type_01) {
                        table.renderRow(i, {'tradeAmt': Number(tradeAmtHid) + Number(carryAmt) - getPayWithTradeFee_DZH(object)});
                    }else{//非货币基金处理交易金额时最后一行的交易金额采用倒减法
                        var tradeAmtz = 0;
                        if(i== rownum){//最后一个组合采用倒减法
                            tradeAmtz = JoyinMath.doMinus(tradeAmtAll,sumAmt,2);
                        }else{
                            tradeAmtz = Number(shareAmt)*Number(unitCprice) - getPayWithTradeFee_DZH(object);
                            sumAmt = JoyinMath.doAdd(sumAmt, tradeAmtz, 2);
                        }
                        table.renderRow(i, {'tradeAmt': JoyinMath.doAdd(tradeAmtz,0,2)});
                    }
                } else {
                    // $('#tradeAmt').val(tradeAmtHid).trigger('blur');
                    // $('#carryAmt').change();
                    table.renderRow(i, {'tradeAmt': tradeAmtHid});
                }
            }
        }
    }

    function tradeAmtDistributionByCarryAmt() {

        var ps = $('#ps').val();
        var unitCprice = $('#unitCprice').val();
        for (var i = 0; i < table.getAllRows().length; i++) {
            var carryAmt = table.getAllRows()[i].carryAmt;
            var shareAmt = table.getAllRows()[i].shareAmt || 0;
            var object = {row:i,
                tradeAmt:table.getAllRows()[i].tradeAmt,
                shareAmt:table.getAllRows()[i].shareAmt,
                feeAmtPay:table.getAllRows()[i].feeAmtPay,
                unitCprice:unitCprice,
                channelFee:table.getAllRows()[i].channelFee,
                commissionAmt:table.getAllRows()[i].commissionAmt
            }
            if (shareAmt && unitCprice) {
                // $('#tradeAmt').val(parseFloat(shareAmt) * parseFloat(unitCprice) + parseFloat(carryAmt) - getPayWithTradeFee()).trigger('blur');
                table.renderRow(i, {"tradeAmt": parseFloat(shareAmt) * parseFloat(unitCprice) + parseFloat(carryAmt) - getPayWithTradeFee_DZH(object)});
            }
        }
    }

    /**
     * 交易本金
     */
    function tradeAmtHidChange_DZH(){
        var ps = $('#ps').val();
        var unitCprice = $('#unitCprice').val();
        if (ps == ps_T05 || ps == ps_T18) {
            for (var i = 0; i < table.getAllRows().length; i++) {
                var tradeAmtHid = table.getAllRows()[i].tradeAmtHid;
                if (unitCprice && tradeAmtHid) {
                    // $('#shareAmt').val(tradeAmtHid / unitCprice).trigger('blur');
                    table.renderRow(i, {'shareAmt': JoyinMath.doAdd(tradeAmtHid / unitCprice,0,2)});
                }
            }
        }
    }

    // function tradeAmtChange_DZH() {
    //     debugger;
    //     var ps = $('#ps').val();
    //     var unitCprice = $('#unitCprice').val();
    //     for (var i = 0; i < table.getAllRows().length; i++) {
    //         var tradeAmt = table.getAllRows()[i].tradeAmt;
    //         var carryAmt = table.getAllRows()[i].carryAmt || 0;
    //         if (unitCprice && tradeAmt) {
    //             var object = {row:i,
    //                 tradeAmt:table.getAllRows()[i].tradeAmt,
    //                 shareAmt:table.getAllRows()[i].shareAmt,
    //                 feeAmtPay:table.getAllRows()[i].feeAmtPay,
    //                 unitCprice:unitCprice,
    //                 channelFee:table.getAllRows()[i].channelFee,
    //                 commissionAmt:table.getAllRows()[i].commissionAmt
    //             }
    //             // $('#shareAmt').val((Number(tradeAmt) - getPayWithTradeFee()) / unitCprice).trigger('blur');
    //             table.renderRow(i, {'shareAmt': (Number(tradeAmt) + getPayWithTradeFee_DZH(object)-carryAmt) / unitCprice});
    //         }
    //     }
    // }

    // function shareAmtChange_DZH() {
    //     debugger;
    //     var ps = $('#ps').val();
    //     var unitCprice = $('#unitCprice').val();
    //     if (ps == ps_T19) {
    //         // for (var i = 0; i < table.getAllRows().length; i++) {
    //         //     var shareAmt = table.getAllRows()[i].shareAmt;
    //         //     var carryAmt = table.getAllRows()[i].carryAmt || 0;
    //         //     if (unitCprice && shareAmt) {
    //         //         var object = {
    //         //             row: i,
    //         //             tradeAmt: table.getAllRows()[i].tradeAmt,
    //         //             shareAmt: table.getAllRows()[i].shareAmt,
    //         //             feeAmtPay: table.getAllRows()[i].feeAmtPay,
    //         //             unitCprice: unitCprice,
    //         //             channelFee: table.getAllRows()[i].channelFee,
    //         //             commissionAmt: table.getAllRows()[i].commissionAmt
    //         //         }
    //         //         // $('#tradeAmt').val(unitCprice * shareAmt + parseFloat(carryAmt) - getPayWithTradeFee()).trigger('blur');
    //         //         table.renderRow(i, {'tradeAmt': unitCprice * shareAmt + parseFloat(carryAmt) - getPayWithTradeFee_DZH(object)});
    //         //     }
    //         // }
    //         tradeAmtDistribution();
    //     }
    // }
    /**
     * 画面加载完成
     */
    $(function () {
        table.load();
        //初始化画面
        init();
        // 绑定按钮事件
        $('#TrdFundInvestTrade005_btn_save').click(function () {
            saveOrSubmit('save');
        });
        $('#TrdFundInvestTrade005_btn_submit').click(function () {
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
        $('#TrdFundInvestTrade005_btn_risk').click(function(){
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
            // window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/1/RSK002/F02', object,getriskdata());
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfBatchData/1/RSK002/F02', object,getriskdata());
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