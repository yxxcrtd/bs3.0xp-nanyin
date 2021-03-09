//@ sourceURL=TrdFundInvestTrade004.js
/**
 * 基金投资交易新建
 */
$(function () {
    // 场内基金改造 add by renping 20191117 start
    var initFlag = true;
    //交易金额or单位净值  1:交易金额  2：单位净值
    // var unitCpriceFlag;
    // 计算交易金额
    var calcTradeAmt = function () {
        if (!initFlag && !isTradeMarketFundMrMcDeal()) {
            var unitCprice = $('#unitCprice').val();
            var ps = $('#ps').val();
            var shareAmt = $('#shareAmt').val();
            if (unitCprice && shareAmt && ps == ps_mc) {
                $('#tradeAmt').val(unitCprice * shareAmt).trigger('blur');
                changeTradeAmtAndFee();
                $('#tradeAmtHid').val(unitCprice * shareAmt);
                if ($('#type6').val() == type_01) {
                    $('#carryAmt').change();
                }
            }
        } else if (!initFlag) {
            var unitCprice = $('#unitCprice').val();
            var tradeAmt = $('#tradeAmt').val();
            var ps = $('#ps').val();
            var shareAmt = $('#shareAmt').val();
            var carryAmt = $('#carryAmt').val();
            if (!unitCprice || !shareAmt) {
                return;
            }
            if (!carryAmt) {
                carryAmt = 0;
            }
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
            var chlFee = $('#channelAmt').val();
            if ($('#chlId').val()) {
                var data = $('#chlId').select2('data')[0].object;
                if (data && data.collectType) {
                    $('#collectType').val(data.collectType);
                    if (data.collectType != collectType_01) {
                        chlFee = 0;
                    }
                }
            }
            if ($("#ps").val() == ps_mr || $("#ps").val() == ps_T04 || $("#ps").val() == ps_T52) {
                $("#tradeAmt").val(JoyinMath.doAdd(JoyinMath.doAdd(Number(shareAmt) * Number(unitCprice), chlFee, 2), commissionFee, 2)).trigger('blur');
                $('#tradeAmtHid').val($('#tradeAmt').val());
            } else {
                $("#tradeAmt").val(JoyinMath.doMinus(JoyinMath.doMinus(Number(shareAmt) * Number(unitCprice), chlFee, 2), commissionFee, 2)).trigger('blur');
                $('#tradeAmtHid').val($('#tradeAmt').val());
            }
            changeTradeAmtAndFee();
            if ($('#type6').val() == type_01) {
                $('#carryAmt').change();
            }
        }
    };

    /**
     * 仅画面控制联动，不牵涉任何其他change事件，避免死循环新copy的方法
     */
    var pageItemDisplay = function () {
        
        var ps = $('#ps').val();
        var data = $('#finprodId').select2('data')[0].object;
        // var portfolioId = $('#portfolioId').val();
        var ccy = $('#ccy').val();
        var type6 = $('#type6').val();
        $('#approvalType_div').show();
        $('#fTradeId_div').show();
        if (ps == ps_T04 || ps == ps_T52) {
            // $('#bussTrdAvailableCashQueryavalibleCash_div').show();//组合T日可用现金
            // $('#bussTrdAvailableCashQueryavalibleCash1_div').show();//组合T+1日可用现金
            table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash',visible: true}]);
            table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash1',visible: true}]);

            
            //交易方向为买入，且交易场所为深交所和上交所时才显示该列
            table.showColumns([{field:'tradeMarketAccAvailableCash',visible: false}]);
//            if ($("#secManageAcctId").val() && ($("#tradeMarket").val() == TRADE_PLACE_03 || $("#tradeMarket").val() == TRADE_PLACE_04)) {
//                // $('#tradeMarketAccAvailableCash_div').show();
//                table.showColumns([{field:'tradeMarketAccAvailableCash',visible: true}]);
//            } else {
//                // $('#tradeMarketAccAvailableCash_div').hide();
//                table.showColumns([{field:'tradeMarketAccAvailableCash',visible: false}]);
//            }
            
            $('#tradePrice_label').hide();//交易价格
            $('.commissionFee_class').hide();//佣金
            table.showColumns([{field:'commissionAmt',visible: false}]);
            // $('.channel_cash').show();//通道可用现金
            table.showColumns([{field:'channelAvailableCash',visible: true}]);
            // $('#Assetsavailablepositions_div').hide();//资产可用持仓
            table.showColumns([{field:'assetsavailablepositions',visible: false}]);
            $('#expectedArrivalDate_div').hide();//预计到账日
            $('#transferInformation').show();
            if (ps == ps_T52) {
                $('#settleSpreed1_div').hide();//确认速度
            } else {
                $('#settleSpreed1_div').show();
            }
            $('#settleSpreed2_div').show();//确认日期
            // $('#confirmDate').hide();//lable:确认日期
            // $('#trdDate').show();//lable交易日期
            $('#carryAmt_div').hide();//待结转份额
            table.showColumns([{field:'carryAmt',visible: false}]);
            $('#unitCprice_div').hide();//单位净值
            table.showColumns([{field:'unitCprice',visible: false}]);
            $('#netDate_div').hide();//净值日期
            $('#purchaseFee_div').hide();
            $('#tradeSha_div').hide();//交易份额
            table.showColumns([{field:'shareAmt',visible: false}]);
            $('#tradeAmt_div').show();//交易金额
            table.showColumns([{field:'tradeAmt',visible: true}]);
            $('#tradeAmt').attr("disabled", false);
            // var aaa = $("#dataTable").find('tradeAmt');
            // var BBB = $("#dataTable").find('td');
            // var ccc = $("#dataTable");
            // var ddd = $("#dataTable");
            // $("#dataTable").find('tradeAmt').data('edit', false);
            // table.reload({cols: cols1});
            $(".channelAmt_class").hide();//通道费-划款金额
            table.showColumns([{field:'channelFee',visible: false}]);
            table.showColumns([{field:'tradeAmtAndFee',visible: false}]);
            if (!initFlag) {
                $('#shareAmt').val("");
                $("#channelAmt").val("");
                for(var i=0;i<table.getAllRows().length;i++){
                    table.renderRow(i,  {"shareAmt": ''});
                    table.renderRow(i,  {"channelFee": ''});
                }
            }
            // if (data && data.finprodId && portfolioId && ccy) {
            //     getPtlCashPositionInfo();//查询组合可用现金
            // }
        } else if (ps == ps_T06 || $("#ps").val() == T65) {
            // $('#bussTrdAvailableCashQueryavalibleCash_div').hide();
            // $('#bussTrdAvailableCashQueryavalibleCash1_div').hide();
            table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash',visible: false}]);
            table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash1',visible: false}]);
            // $('#tradeMarketAccAvailableCash_div').hide();
            table.showColumns([{field:'tradeMarketAccAvailableCash',visible: false}]);
            $('#tradePrice_label').hide();
            $('.commissionFee_class').hide();
            table.showColumns([{field:'commissionAmt',visible: false}]);
            // $('.channel_cash').hide();
            table.showColumns([{field:'channelAvailableCash',visible: false}]);
            // $('#Assetsavailablepositions_div').show();
            table.showColumns([{field:'assetsavailablepositions',visible: true}]);
            $('#expectedArrivalDate_div').show();
            $('#transferInformation').hide();
            $('#settleSpreed1_div').show();
            $('#settleSpreed2_div').show();
            // $('#confirmDate').hide();
            // $('#trdDate').show();
            $('#carryAmt_div').hide();
            table.showColumns([{field:'carryAmt',visible: false}]);
            $('#unitCprice_div').hide();
            table.showColumns([{field:'unitCprice',visible: false}]);
            $('#netDate_div').hide();
            $('#purchaseFee_div').hide();
            $('#tradeSha_div').show();
            table.showColumns([{field:'shareAmt',visible: true}]);
            $('#tradeAmt_div').hide();
            table.showColumns([{field:'tradeAmt',visible: false}]);
            $('#shareAmt').attr("disabled", false);
            $(".channelAmt_class").hide();
            table.showColumns([{field:'channelFee',visible: false}]);
            table.showColumns([{field:'tradeAmtAndFee',visible: false}]);
            if (!initFlag) {
                $('#tradeAmt').val("");
                $("#channelAmt").val("");
                for(var i=0;i<table.getAllRows().length;i++){
                    table.renderRow(i,  {"shareAmt": ''});
                    table.renderRow(i,  {"channelFee": ''});
                }
            }
            // if (data && data.finprodId && portfolioId) {
            //     getPtlSecPositionInfo();
            // }
        } else if (ps == ps_mr) {
            // $('#bussTrdAvailableCashQueryavalibleCash_div').show();
            // $('#bussTrdAvailableCashQueryavalibleCash1_div').show();
            table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash',visible: true}]);
            table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash1',visible: true}]);
            $('#unitCprice_div').show();
            table.showColumns([{field:'unitCprice',visible: true}]);
            if ($("#tradeMarket").val() == TRADE_PLACE_03 || $("#tradeMarket").val() == TRADE_PLACE_04) {
                // $('#tradeMarketAccAvailableCash_div').show();
                table.showColumns([{field:'tradeMarketAccAvailableCash',visible: true}]);
                $('#tradePrice_label').show();
                $('#unitCprice_label').hide();
                $('#unitCprice').prop("disabled", false);
                $('.commissionFee_class').show();
                table.showColumns([{field:'commissionAmt',visible: true}]);
                $(".channelAmt_class").hide();
                table.showColumns([{field:'channelFee',visible: false}]);
                table.showColumns([{field:'tradeAmtAndFee',visible: false}]);
            } else {
                // $('#tradeMarketAccAvailableCash_div').hide();
                table.showColumns([{field:'tradeMarketAccAvailableCash',visible: false}]);
                $('#tradePrice_label').hide();
                $('#unitCprice_label').show();
                $('#unitCprice').prop("disabled", true);
                $('.commissionFee_class').hide();
                table.showColumns([{field:'commissionAmt',visible: false}]);
                $(".channelAmt_class").hide();
                table.showColumns([{field:'channelFee',visible: false}]);
                table.showColumns([{field:'tradeAmtAndFee',visible: false}]);
            }
            //内部交易对手显示
            if($('#trdInvestPersonInfo_counterId')&&$('#trdInvestPersonInfo_counterId').select2('data') && $('#trdInvestPersonInfo_counterId').select2('data')[0].object =='N'){
            	$(".channelAmt_class").show();
                table.showColumns([{field:'channelFee',visible: true}]);
                table.showColumns([{field:'tradeAmtAndFee',visible: true}]);
            }
            if (isTradeMarketDeal()) {
                $('#carryAmt').prop("disabled", true);
                $('#shareAmt').attr("disabled", false);
                $('#tradeAmt').attr("disabled", false);
            } else {
                $('#carryAmt').prop("disabled", false);
                $('#shareAmt').attr("disabled", true);
                $('#tradeAmt').attr("disabled", false);
            }

            // $('.channel_cash').show();
            table.showColumns([{field:'channelAvailableCash',visible: true}]);
            // $('#Assetsavailablepositions_div').hide();
            table.showColumns([{field:'assetsavailablepositions',visible: false}]);
            $('#expectedArrivalDate_div').hide();
            $('#transferInformation').show();
            $('#settleSpreed1_div').hide();
            $('#settleSpreed2_div').hide();
            // $('#confirmDate').show();
            // $('#trdDate').hide();
            if ($('#type6').val() == type_01) {
                $('#carryAmt_div').show();
                table.showColumns([{field:'carryAmt',visible: true}]);
                $('#netDate_div').hide();
            } else {
                $('#carryAmt_div').hide();
                table.showColumns([{field:'carryAmt',visible: false}]);
                $('#netDate_div').show();
            }

            $('#purchaseFee_div').show();
            $('#purchase').show();
            $('#redemp').hide();
            $('#tradeSha_div').show();
            table.showColumns([{field:'shareAmt',visible: true}]);
            $('#tradeAmt_div').show();
            table.showColumns([{field:'tradeAmt',visible: true}]);
            // if (data && data.finprodId && portfolioId && ccy) {
            //     getPtlCashPositionInfo();
            // }
            //买入不应展示审批类别
            $('#approvalType_div').hide();
            $('#fTradeId_div').hide();
        } else if (ps == ps_mc) {
            // $('#bussTrdAvailableCashQueryavalibleCash_div').hide();
            // $('#bussTrdAvailableCashQueryavalibleCash1_div').hide();
            table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash',visible: false}]);
            table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash1',visible: false}]);
            // $('#tradeMarketAccAvailableCash_div').hide();
            table.showColumns([{field:'tradeMarketAccAvailableCash',visible: false}]);
            $('#unitCprice_div').show();
            table.showColumns([{field:'unitCprice',visible: true}]);
            if ($("#tradeMarket").val() == TRADE_PLACE_03 || $("#tradeMarket").val() == TRADE_PLACE_04) {
                $('#tradePrice_label').show();
                $('#unitCprice_label').hide();
                $('#unitCprice').prop("disabled", false);
                $('.commissionFee_class').show();
                table.showColumns([{field:'commissionAmt',visible: true}]);
            } else {
                $('#tradePrice_label').hide();
                $('#unitCprice_label').show();
                $('#unitCprice').prop("disabled", true);
                $('.commissionFee_class').hide();
                table.showColumns([{field:'commissionAmt',visible: false}]);
            }
            $('#carryAmt').prop("disabled", false);
            // $('.channel_cash').hide();
            table.showColumns([{field:'channelAvailableCash',visible: false}]);
            // $('#Assetsavailablepositions_div').show();
            table.showColumns([{field:'assetsavailablepositions',visible: true}]);
            $('#expectedArrivalDate_div').show();
            $('#transferInformation').show();
            $('#settleSpreed1_div').hide();
            $('#settleSpreed2_div').hide();
            // $('#confirmDate').show();
            // $('#trdDate').hide();
            if ($('#type6').val() == type_01) {
                $('#carryAmt_div').show();
                table.showColumns([{field:'carryAmt',visible: true}]);
                $('#netDate_div').hide();
            } else {
                $('#carryAmt_div').hide();
                table.showColumns([{field:'carryAmt',visible: false}]);
                $('#netDate_div').show();
            }
            $('#purchaseFee_div').show();
            $('#purchase').hide();
            $('#redemp').show();
            $('#tradeSha_div').show();
            table.showColumns([{field:'shareAmt',visible: true}]);
            $('#shareAmt').attr("disabled", false);
            $('#tradeAmt_div').show();
            table.showColumns([{field:'tradeAmt',visible: true}]);
            $('#tradeAmt').attr("disabled", false);
            // if (data && data.finprodId && portfolioId) {
            //     getPtlSecPositionInfo();
            // }
            if ($("#chlId").val()) {
                $(".channelAmt_class").show();
                table.showColumns([{field:'channelFee',visible: true}]);
                table.showColumns([{field:'tradeAmtAndFee',visible: true}]);
            }
            //卖出不应展示审批类别
            $('#approvalType_div').hide();
            $('#fTradeId_div').hide();
        }
        changeTradeAmtAndFee();
        unitCpriceName();
    };

    var unitCpriceName = function () {
        var ps = $('#ps').val();
        if(ps == ps_mr || ps == ps_mc) {
            if ($("#tradeMarket").val() == TRADE_PLACE_03 || $("#tradeMarket").val() == TRADE_PLACE_04) {
                $("[data-field='unitCprice']").find('span').text('交易价格');
            } else {
                $("[data-field='unitCprice']").find('span').text('单位净值');
            }
        }
    }

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({

        data:BussTrdTransfenpeiDto==null?[]:BussTrdTransfenpeiDto,
        searchOnInit: true,
        loadonce: true,
        page: false,
        addbtn: true,
        height : 200,
        cols:[
            {field:'entrustId', width:100, title: '委托代码',sortable: false,disabled:true}
            ,{field:'portfolioId', width:100,  title: '组合代码',edit:true,sortable: false,
                editOption:{type:'select', id:'portfolioId', multiple: false, showField:'text',searchable: true,
                    url: JoyinUtil.getRealPath('/business/common/CommonSelect/selectPortfolioId?act=true')
                }
            }
            ,{field:'doEntrustMoney', width:100, title: '剩余委托额度',sortable: false}
            ,{field:'tradeAmt', width:150, title: '交易金额',edit:true,sortable: false,type:'number', digit:2}
            ,{field:'shareAmt', width:150, title: '交易份额',edit:true,sortable: false,type:'number', digit:2}
            ,{field:'carryAmt', width:150, title: '待结转份额',edit:true,sortable: false,type:'number', digit:2}
            ,{field:'unitCprice', width:150, title: '单位净值',edit:true,sortable: false,type:'number', digit:4}

            ,{field:'channelFee', width:150, title: '通道费',edit:true,sortable: false,type:'number',visible:false, digit:2}
            ,{field:'commissionAmt', width:150, title: '佣金',edit:true,sortable: false,type:'number', digit:2}
            ,{field:'bussTrdAvailableCashQueryavalibleCash', width:150, title: '组合T日可用现金',sortable: false,type:'number', digit:2}
            ,{field:'bussTrdAvailableCashQueryavalibleCash1', width:150, title: '组合T+1日可用现金',sortable: false,type:'number', digit:2}
            ,{field:'channelAvailableCash', width:150, title: '通道可用现金',sortable: false,type:'number', digit:2}
            ,{field:'tradeMarketAccAvailableCash', width:150, title: '交易所资金账户可用现金',sortable: false,type:'number', digit:2}
            ,{field:'assetsavailablepositions', width:150,  title: '资产可用持仓',sortable: false,type:'number', digit:2}
            ,{field:'tradeAmtAndFee', width:150,  title: '划款金额',sortable: false,type:'number', digit:2,visible:false}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}//编辑和删除事件
        ],
        done:function(){
            this.bind('click', '.delt', function(data, index) {
                table.deleteRow(index);
            });
        },
        beforeEditCell: function(preVal, field, rowData){
            // if("tradeAmt"==field){
            //
            // }
            if("shareAmt"==field){
                var ps = $('#ps').val();
                 if (ps == ps_mr) {
                    if (isTradeMarketDeal()) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            if("carryAmt"==field){
                var ps = $('#ps').val();
                 if (ps == ps_mr) {
                    if (isTradeMarketDeal()) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
            if("unitCprice"==field){
                var ps = $('#ps').val();
                if (ps == ps_mr) {
                    if ($("#tradeMarket").val() == TRADE_PLACE_03 || $("#tradeMarket").val() == TRADE_PLACE_04) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (ps == ps_mc) {
                    if ($("#tradeMarket").val() == TRADE_PLACE_03 || $("#tradeMarket").val() == TRADE_PLACE_04) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            if("channelFee"==field){
                if ($("#chlId").val()) {
                    return true;
                } else {
                    return false;
                }
            }
            return true;
        },
        editCell: function(value, data, field, cell, column){
            
            var unitCprice = $('#unitCprice').val();
            var obj = {row:data.LAY_TABLE_INDEX,
                        portfolioId:data.portfolioId,
                        tradeAmt:data.tradeAmt,
                        shareAmt:data.shareAmt,
                        carryAmt:data.carryAmt,
                        unitCprice:unitCprice,
                        channelFee:data.channelFee,
                        commissionAmt:data.commissionAmt}
            if("portfolioId"==field){
                var portfolioId = value;
                var ps = $('#ps').val();
                var finprodIdData = $('#finprodId').select2('data')[0].object;
                var ccy = $('#ccy').val();
                var unitCprice = $('#unitCprice').val();
                if ((ps == ps_T04 || ps == ps_mr || ps == ps_T52) && finprodIdData && finprodIdData.finprodId && portfolioId) {
                    getPtlCashPositionInfo(obj);
                }
                if (((ps == ps_T06 || $("#ps").val() == T65) || ps == ps_mc) && finprodIdData && finprodIdData.finprodId && portfolioId) {
                    getPtlSecPositionInfo(obj);
                }
                if(unitCprice) {
                    table.renderRow(data.LAY_TABLE_INDEX,  {"unitCprice": unitCprice});
                }
                //查询待结转份额
                if((ps == ps_mr || ps == ps_mc )&& $('#type6').val() == type_01) {
                    var param = {
                        carryDate: $('#tradeDate').val(),
                        cid: $('#calendarId').val(),
                        portfolioId: data.portfolioId,
                        finprodId: $('#finprodId').select2('data')[0].object.finprodId,
                        secAcctId: $("#chlId").val()
                    }
                    Joyin.ajaxJson(basePath + 'invest/trade/fund/TrdFundInvestTrade003/getcarryAmt', param,
                        function (result) {
                            table.renderRow(data.LAY_TABLE_INDEX,  {"carryAmt": result});
                        }, false);
                }
            }
            if("channelFee"==field || "commissionAmt"==field){
                calcTradeAmt_DZH(obj);
                changeTradeAmtAndFee_DZH(obj);
            }
            if("tradeAmt"==field){
                tradeAmtDZHChange_DZH(obj);
            }
            if("shareAmt"==field){
                var ps = $('#ps').val();
                //卖出时根据交易份额反算待结转份额
                if(data.shareAmt && ps == ps_mc && $('#type6').val() == type_01) {
                    var param = {
                        carryDate: $('#tradeDate').val(),
                        cid: $('#calendarId').val(),
                        portfolioId: data.portfolioId,
                        finprodId: $('#finprodId').select2('data')[0].object.finprodId,
                        secAcctId: $("#chlId").val()
                    }
                    Joyin.ajaxJson(basePath + 'invest/trade/fund/TrdFundInvestTrade003/getcarryAmt', param,
                        function (result) {
                            if(result != null) {
                                var carryAmtz = JoyinMath.doAdd(parseFloat(result)/data.assetsavailablepositions*data.shareAmt,0,2);
                                obj.carryAmt = carryAmtz;
                                table.renderRow(data.LAY_TABLE_INDEX, {"carryAmt": carryAmtz});
                            }
                        }, false);
                }
                calcTradeAmt_DZH(obj);
            }
            if("unitCprice"==field){
                unitCpriceDZHChange_DZH(obj);
            }
            if("carryAmt"==field){
                carryAmtDZHChange_DZH(obj);
            }
        }
    });

    // 场内基金改造 add by renping 20191117 end
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
                assetType: FINPROD_TYPE_F06,
                isEnabled: '01',
                assetCode: finprodId,
                tradeDirection: $('#ps').val()
            }
            if (ps == ps_T06 || $("#ps").val() == T65){
                if(Number(bussTrdTransfenpeiDto[i].shareAmt) == Number(0)){
                    continue;
                }
                obj.tradeAmount = bussTrdTransfenpeiDto[i].shareAmt;
                obj.isEnabled = '01';
            }else{
                if(Number(bussTrdTransfenpeiDto[i].tradeAmt) == Number(0)){
                    continue;
                }
                obj.tradeAmount = bussTrdTransfenpeiDto[i].tradeAmt;
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
        if(ps == ps_T06 || $("#ps").val() == T65){
            object.tradeAmount = ["shareAmt", "TrdFundInvestTrade002.tradeSha"];
        }
        return object;
    }

    function saveOrSubmit(operateType) {
        //添加组合分配 与 金额信息相关的校验
    	//1. 配置参数
    	var keyChName = ["交易金额","交易份额","待结转份额","通道费","佣金","划款金额"];
    	var keyId = ["tradeAmt","shareAmt","carryAmt","channelAmt","commissionFee","tradeAmtAndFee"];
    	var tableId = ["tradeAmt","shareAmt","carryAmt","channelFee","commissionAmt","tradeAmtAndFee"];
    	var object,value,rowNum
    	//2. 获取组合分配的table
    	var rows = table.getAllRows();
    	//3. 循环处理
    	for(j=0;j<keyChName.length;j++){
    		//3.1获取金额信息对应元素
    		object = $("#"+keyId[j]);
    		//3.2 获取该元素的显示状态，显示才执行校验
    		if(!object.is(':hidden')){
    			//3.3 获取该元素值，并保留到两位小数
    			value = JoyinMath.doAdd(object.val(),0,2);
    			//3.4 统计表中元素值
    			rowNum = 0;
           	 	for(var i = 0; i < rows.length; i++){
           	 		rowNum = JoyinMath.doAdd(rowNum, rows[i][tableId[j]], 2);
           	 	}
           	 	//3.5 执行校验
           	 	if(rowNum!=value){
           	 		Joyin.error("金额信息中"+keyChName[j]+"不等于组合分配中对应列之和");
           	 		return ;
           	 	}
    		}
    	}
    	//4. 单位净值特殊处理
    	//4.1 获取金额信息中的单位净值
    	object = $("#unitCprice");
    	//4.2 获取该元素的显示状态，显示才执行校验
    	if(!object.is(':hidden')){
    		//4.3 获取该元素值
    		value = JoyinMath.doAdd(object.val(),0,2);
    		//4.4 对数值列进行循环判断
       	 	for(var i = 0; i < rows.length; i++){
       	 		rowNum = JoyinMath.doAdd(0, rows[i]["unitCprice"], 2);
       	 		//4.5 执行校验
           	 	if(rowNum!=value){
           	 		Joyin.error("组合分配中第"+(i+1)+"行单位净值不等于金额元素中的单位净值");
           	 		return ;
           	 	}
       	 	}
    	}
    	
    	// 划款指令隐藏时，自动生成划款指令信息
    	generateOrders(function () {
	        var s;
	        if (BaseRiskMathComm&&$("#ps").val()!=ps_T06 ) {
	            s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
	            if (!s && s != '') {
	                return;
	            }
	            if (window.BaseRiskMathComm.isCalcConditionChanged(getriskdata())) {
	                return;
	            }
	        }


	        if (window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()) {
	            return;
	        }

	        //合同号
            if($('#ps').val() != ps_mr && $('#ps').val() != ps_mc) {
                if (!$('#fTradeId').val() || $('#fTradeId').val().trim() == null || $('#fTradeId').val().trim() == '') {
                    Joyin.error("请输入合同号！");
                    return;
                }
            }
            //校验组合分配列表
            if(checkFeiPeiAmt()){
                return;
            }

	        var object = $.serializeToObject('#submitForm', true);
	        if ($('#select2-finprodId-container').attr('title')){
	            object.finprodName = $('#select2-finprodId-container').attr('title');
	        }
	        object.pageModel = pageModel;
	        object.operateType = operateType;
	        object.tradeId = $('#tradeId').val();

            if(FileCommon) {
                object.fileArray = FileCommon.getUploadFileInfo();
                if(object.fileArray && _.isArray(object.fileArray) && object.fileArray.length > 0){
                    $.each(object.fileArray, function(index,obj){
                        obj.seqNo = obj.fileNo;
                    });
                }
            }
	        var tradeMarket = $("#tradeMarket").val();
	        var ps = $("#ps").val();
	        if (ps == T65 && tradeMarket != TRADE_PLACE_06) {
	            Joyin.error("只有场外基金才能做转换。");
	            return;
	        }
	
	        if ($('#changeToFinprodMarketId').val() && $('#changeToFinprodMarketId').select2('data')[0].object) {
	            tradeMarket = $('#changeToFinprodMarketId').select2('data')[0].object.tradeMarket
	            if (ps == T65 && tradeMarket && tradeMarket != TRADE_PLACE_06) {
	                Joyin.error("转入基金是场外基金才能做转换。");
	                return;
	            }
	
	            var issuer1 = $('#finprodId').select2('data')[0].object.issuer;
	            var issuer = $('#changeToFinprodMarketId').select2('data')[0].object.issuer;
	            if (issuer1 && issuer && issuer1 != issuer) {
	                Joyin.error("转入基金和转出基金必须属于同一管理人。");
	                return;
	            }
	        }
	
	        if ($('#changeToFinprodMarketId').val() && $('#changeToFinprodMarketId').select2('data')[0].object && $('#changeToFinprodMarketId').select2('data')[0].object.finprodId) {
	            object.rFinprodId = $('#changeToFinprodMarketId').select2('data')[0].object.finprodId;
	        }





	        if (object.shareAmt && object.unitCprice) {
	            object.prinAmt = Number(object.shareAmt) * Number(object.unitCprice);
	        } else {
	            object.prinAmt = object.tradeAmt;
	        }
	        //交易金额赋值
	        if(object.tradeAmtAndFee){
	        	object.tradeAmt = object.tradeAmtAndFee;
	        }
	        var intAmt = 0;
	        if (object.intAmt) {
	            intAmt = object.intAmt;
	        }
	        object.fpriceAmt = object.prinAmt + Number(intAmt);

	        //组合金额赋值
            var portfolioId;
            for(var i in object.bussTrdTransfenpeiDto){
                portfolioId = object.bussTrdTransfenpeiDto[i].portfolioId + ',' + portfolioId;

                if (object.bussTrdTransfenpeiDto[i].shareAmt && object.bussTrdTransfenpeiDto[i].unitCprice) {
                    object.bussTrdTransfenpeiDto[i].prinAmt = JoyinMath.doAdd(Number(object.bussTrdTransfenpeiDto[i].shareAmt) * Number(object.bussTrdTransfenpeiDto[i].unitCprice),0,2);
                } else {
                    object.bussTrdTransfenpeiDto[i].prinAmt = JoyinMath.doAdd(object.bussTrdTransfenpeiDto[i].tradeAmt,0,2);
                }
                //交易金额赋值
                if(object.bussTrdTransfenpeiDto[i].tradeAmtAndFee){
                    object.bussTrdTransfenpeiDto[i].tradeAmt = JoyinMath.doAdd(object.bussTrdTransfenpeiDto[i].tradeAmtAndFee,0,2);
                }
                var intAmt = 0;
                if (object.bussTrdTransfenpeiDto[i].carryAmt) {
                    intAmt = object.bussTrdTransfenpeiDto[i].carryAmt;
                    object.bussTrdTransfenpeiDto[i].intAmt = JoyinMath.doAdd(object.bussTrdTransfenpeiDto[i].carryAmt,0,2);
                }
                object.bussTrdTransfenpeiDto[i].fpriceAmt = JoyinMath.doAdd(object.bussTrdTransfenpeiDto[i].prinAmt + Number(intAmt),0,2);
            }
            object.portfolioId = 'dzh';

            if($('#tradeAmt').val()){
                var maxTradeamt = 0;
                for(var i=0;i<table.getAllRows().length;i++){
                    if (parseFloat(table.getAllRows()[i].tradeAmt) > parseFloat(maxTradeamt)){
                        maxTradeamt = table.getAllRows()[i].tradeAmt;
                    }
                }
                object.maxTradeamt = maxTradeamt;
            }

	        var data = $("#chlId").find("option:selected").data("data");
	        if (data && data.object) {
	            object.payType = data.object.payType;
	        }
	
	        if (FileCommon) {
	            object.fileArray = FileCommon.getUploadFileInfo();
	            if (object.fileArray && _.isArray(object.fileArray)) {
	                $.each(object.fileArray, function (index, obj) {
	                    obj.seqNo = obj.fileNo;
	                });
	            }
	        }
	
	        if ($('#trdInvestPersonInfo_counterId').select2('data') && $('#trdInvestPersonInfo_counterId').select2('data')[0]) {
	            object.counterType = $('#trdInvestPersonInfo_counterId').select2('data')[0].object;
	            object.counterId=$('#trdInvestPersonInfo_counterId').select2('data')[0].id;
	        }
	        

	        var data = $('#finprodId').select2('data')[0].object;
	        if (data && data.finprodId) {
	            object.finprodId = data.finprodId;
	            object.finprodMarketId = $('#finprodId').val();
                Joyin.ajaxJson(basePath + 'invest/trade/fund/TrdFundInvestTrade004/check', object, function (result) {
	                Joyin.ajaxJson(basePath + 'invest/trade/TrdCommonController/check', object, function(result1) {
	                	object.riskCreditListCommonDto = result1.data;
	                	Joyin.confirm(result.data, function () {
	                        if (operateType === 'save') {
	                            // WorkFlow.doSaveList(basePath + 'invest/trade/fund/TrdFundInvestTrade002/save', object, "TrdFundInvestTrade002_btn_save");
                                WorkFlow.doSaveList(basePath + 'invest/trade/fund/TrdFundInvestTrade004/save', object, "TrdFundInvestTrade002_btn_save",function(result){

                                });
	                        } else {
	                            // WorkFlow.doSubmitList(basePath + 'invest/trade/fund/TrdFundInvestTrade002/save', object, "TrdFundInvestTrade002_btn_submit");
                                WorkFlow.doSubmitList(basePath + 'invest/trade/fund/TrdFundInvestTrade004/save', object, "TrdFundInvestTrade002_btn_submit", pageId,null, function(result){
                                    window.BaseRiskMathComm.reloadRiskTable(result);
                                });
	                        }
	                    });
	                });
	            });
	        } else {
	            Joyin.error("请输入资产代码。")
	        }
    	 });   
	};

    function checkFeiPeiAmt(){
        //检查组合分配的和金额信息是否一致
        if(!table.getAllRows().length>0){
            Joyin.error('请输入组合分配列表！');
            return true;
        }else{
            for(var i=0;i<table.getAllRows().length;i++){
                if(!table.getAllRows()[i].portfolioId){
                    Joyin.error('组合分配列表第'+(i+1)+'行组合代码不能为空！');
                    return true;
                }
                if((ps_T04 ==$('#ps').val() || ps_mr ==$('#ps').val()) && !table.getAllRows()[i].tradeAmt){
                    Joyin.error('组合分配列表第'+(i+1)+'行交易金额不能为空！');
                    return true;
                }
                if((ps_T06 ==$('#ps').val() || ps_mc ==$('#ps').val()) && !table.getAllRows()[i].shareAmt){
                    Joyin.error('组合分配列表第'+(i+1)+'行交易份额不能为空！');
                    return true;
                }
            }
        }

        if($('#tradeAmt').val()){
            var number = 0;
            var tradeAmt = 0;
            var doEntrustMoney = 0;
            for(var i=0;i<table.getAllRows().length;i++){
                tradeAmt = JoyinMath.doAdd(table.getAllRows()[i].tradeAmt, 0, 2);
                if(tradeAmt){
                    number =  JoyinMath.doAdd(number,tradeAmt,2);
                }
                if(table.getAllRows()[i].doEntrustMoney){
                    doEntrustMoney = JoyinMath.doAdd(table.getAllRows()[i].doEntrustMoney, 0, 2);
                    if(Number(tradeAmt)>Number(doEntrustMoney)){
                        Joyin.error('组合分配列表第'+(i+1)+'行交易金额不能大于剩余可用额度!');
                        return true;
                    }
                }
            }
            if(JoyinMath.doAdd($('#tradeAmt').val(),0,2)!=number){
                Joyin.error('组合分配交易金额和金额信息交易金额不一致！');
                return true;
            }
        }

        if($('#shareAmt').val()){
            var number = 0;
            var shareAmt = 0;
            for(var i=0;i<table.getAllRows().length;i++){
                shareAmt = JoyinMath.doAdd(table.getAllRows()[i].shareAmt, 0, 2);
                if(shareAmt){
                    number =  JoyinMath.doAdd(number,shareAmt,2);
                }
            }
            if(JoyinMath.doAdd($('#shareAmt').val(),0,2)!=number){
                Joyin.error('组合分配交易份额和金额信息交易份额不一致！');
                return true;
            }
        }

    }

    // <!-- 通道交易 新增 强成西 2019/03/26 start -->
    $("#channelAmt,#commissionFee").on("change", function () {
        calcTradeAmt();
        changeTradeAmtAndFee();
    });

    $("#tradeAmt").on("change", function () {
        changeTradeAmtAndFee();
    });

    var changeTradeAmtAndFee = function() {
       /* if ($("#ps").val() == ps_mr || $("#ps").val() == ps_T04) {
            $("#tradeAmtAndFee").val(JoyinMath.doAdd($("#tradeAmt").val(), $("#channelAmt").val(), 2)).trigger('blur');
        }
        else {
            $("#tradeAmtAndFee").val(JoyinMath.doMinus($("#tradeAmt").val(), $("#channelAmt").val(), 2)).trigger('blur');
        }*/
    	 if ($("#ps").val() == ps_mr || $("#ps").val() == ps_mc) {
    		 if ($('#trdInvestPersonInfo_counterId').select2('data') && $('#trdInvestPersonInfo_counterId').select2('data')[0]) {
    			 var 交易对手 =  $('#trdInvestPersonInfo_counterId').select2('data')[0].object;
    			 if (交易对手 == 'N') {
    				 $("#tradeAmtAndFee").val(JoyinMath.doMinus($("#tradeAmt").val(), $("#channelAmt").val(), 2)).trigger('blur');
    			 } else {
    				 //外部卖出
    				 if($("#ps").val() == ps_mc &&$("#chlId").val()&&$("#chlId").select2("data")[0].object.collectType==collectType_01){
    					 $("#tradeAmtAndFee").val(JoyinMath.doMinus($("#tradeAmt").val(), $("#channelAmt").val(), 2)).trigger('blur');
    				 }
    			 }
    		 }
         }
    	/*if ($("#ps").val() == PS_T02&&$("#chlId").val()&&$("#chlId").select2("data")[0].object.collectType==Collect_Type_01) {
          	 $("#tradeAmtAndFee").val(JoyinMath.doMinus($("#tradeAmtAndFee").val(), $("#channelFee").val(), 2)).trigger('blur');
    	}*/

    }


    $("#chlId").on("change", function () {
        if ($("#chlId").val()) {
            $("#secManageAcctId").textSelect('setValue', '');
            $("#secManageAcctId").prop("disabled", true);
            $("#secManageAcctId").trigger("change");
            $("#channelAmt").prop("disabled", false);
            $("#channelAmt").trigger("change");
            if($("#ps").val() == ps_mc) {
                $(".channelAmt_class").show();
                table.showColumns([{field: 'channelFee', visible: true}]);
                table.showColumns([{field: 'tradeAmtAndFee', visible: true}]);
            }
        } else {
            $("#secManageAcctId").prop("disabled", false);
            $("#channelAmt").prop("disabled", true);
            $("#channelAmt").val('').trigger("change");
        }
        selectManeger();
        // getPtlCashPositionInfo();
        // getPtlSecPositionInfo();
    });

    // <!-- 通道交易 新增 强成西 2019/03/26 end -->
    $("#secManageAcctId").on("change", function () {
        if ($("#secManageAcctId").val()) {
            // 通道的change会计算组合现金数据
            $("#chlId").val('').trigger("change");
            $("#chlId").prop("disabled", true);
            $("#channelAmt").val("").trigger("blur");
            $("#channelAmt").trigger("change");
            $("#channelAmt").prop("disabled", true);
            // 
        } else {
            $("#chlId").prop("disabled", false);
            $("#channelAmt").prop("disabled", false);

        }
        pageItemDisplay(); // 内部有剩余现金查询
    });

    //查询组合可用现金
    var getPtlCashPositionInfo = function (object) {
        
        if(!object){
            return;
        }
        var portfolioId = object.portfolioId;
        var row = object.row;
        if (!portfolioId || !$('#ccy').val() || !$('#tradeDate').val()) {
            return;
        }
        param = {
            portfolioId: portfolioId,
            ccy: $('#ccy').val(),
            vdate: $('#tradeDate').val(),
            calId: $('#calendarId').val(),
            chlId: $("#chlId").val(),
            secAcctId: $("#secManageAcctId").val()
        }
        Joyin.ajaxJson(basePath + 'invest/trade/fund/TrdFundInvestTrade002/getProAvailableCash', param,
            function (result) {
                // $('#bussTrdAvailableCashQueryavalibleCash').val(result.cashT).trigger('blur');
                // $('#bussTrdAvailableCashQueryavalibleCash1').val(result.cashT1).trigger('blur');
                table.renderRow(row,  {"bussTrdAvailableCashQueryavalibleCash": result.cashT});
                table.renderRow(row,  {"bussTrdAvailableCashQueryavalibleCash1": result.cashT1});
                // <!-- 通道交易 新增 强成西 2019/03/26 start -->
                // $('#channelAvailableCash').val(result.T_2).trigger('blur');
                table.renderRow(row,  {"channelAvailableCash": result.T_2});
                // <!-- 通道交易 新增 强成西 2019/03/26 end -->
                $('#accAvailableCash').val(result.cashT3).trigger('blur');

                // 场内基金改造 add by renping 20191117 start
                // 交易所时，证券托管户现金
                if ($("#tradeMarket").val() == TRADE_PLACE_03 || $("#tradeMarket").val() == TRADE_PLACE_04) {
                    // $('#tradeMarketAccAvailableCash').val(result.cashS).trigger('blur');
                    table.renderRow(row,  {"tradeMarketAccAvailableCash": result.cashS});
                } else {
                    // $('#tradeMarketAccAvailableCash').val('').trigger('blur');
                    table.renderRow(row,  {"tradeMarketAccAvailableCash": ' '});
                }
                // 场内基金改造 add by renping 20191117 end
            });
    }
    //查询组合可用持仓
    var getPtlSecPositionInfo = function (object) {
        if(!object){
            return;
        }
        var portfolioId = object.portfolioId;
        var row = object.row;
        var data = $('#finprodId').select2('data')[0].object;
        if (data && data.finprodId && portfolioId) {
            var finprodId = data.finprodId;
            var secAcctId = $("#secManageAcctId").val();
            if (!secAcctId) {
                secAcctId = $("#chlId").val();
            }
            param = {portfolioId: portfolioId, finprodId: finprodId, cdate: $('#tradeDate').val(), secAcctId: secAcctId}
            Joyin.ajaxJson(basePath + 'invest/trade/fund/TrdFundInvestTrade002/getPtlSecPosition', param,
                function (result) {
                    // $('#Assetsavailablepositions').val(result).trigger('blur');
                    table.renderRow(row,  {"assetsavailablepositions": result});
                },false);
        }
    }
    // 场内基金改造 add by renping 20191117 start
    // 判断是否为交易所交易
    var isTradeMarketDeal = function () {
        if (isTradeMarketFundMrMcDeal()
            && ($("#chlId").val() || $("#trdInvestPersonInfo_counterId").val() == COUNTER_PARTY_ID_SHANGHAI
                || $("#trdInvestPersonInfo_counterId").val() == COUNTER_PARTY_ID_SHENZHEN)) {
            return true;
        } else {
            return false;
        }
    }

    // 判断是否为交易所基金买入卖出交易
    var isTradeMarketFundMrMcDeal = function () {
        if (($('#ps').val() == ps_mr || $('#ps').val() == ps_mc)
            && ($("#tradeMarket").val() == TRADE_PLACE_03 || $("#tradeMarket").val() == TRADE_PLACE_04)) {
            return true;
        } else {
            return false;
        }
    }
    // 交易对手变更时联动处理
    var counterIdOnChange = function () {
        if (isTradeMarketDeal() && !$("#chlId").val()) {
            getPtlCashPositionInfo();
        }
        if (pageModel != PAGE_MODEL_REFERENCE) {
            var counterId = $('#trdInvestPersonInfo_counterId');
            if (counterId.select2('data') && counterId.select2('data')[0]) {
                var 交易对手 =  counterId.select2('data')[0].object;
                // 【债券投资委托】交易对手选择内部交易对手时，清算速度为T+0不可改
                if (交易对手 == 'N') {
                        $('#commissionFee').val('').prop("disabled", true).trigger("change");
                        var rows = table.getAllRows();
                        for(var i = 0; i < rows.length; i++){
                        	table.renderRow(i,  {"commissionAmt": ''});
                        }
                        $("div[lay-id='dataTable']").find("div.layui-table-main").find("td[data-field='commissionAmt']").removeAttr("lay-event");
                        $("div[lay-id='dataTable']").find("div.layui-table-main").find("td[data-field='commissionAmt']").removeAttr("title");

                } else {
                    $('#commissionFee').prop("disabled", false).trigger("change");
                    $("div[lay-id='dataTable']").find("div.layui-table-main").find("td[data-field='commissionAmt']").attr('lay-event',"editor");
                }
            }
//            //计算费用
//            calcTradeAmt();
    	}
        // pageItemDisplay();
        if (!isTradeMarketDeal() && $("#ps").val() == ps_mr) {
            $('#unitCprice').change();
        }
    }
    // 交易场所变更时联动处理
    var tradeMarketChange = function () {
        if ($("#tradeMarket").val() == TRADE_PLACE_03 || $("#tradeMarket").val() == TRADE_PLACE_04) {
            if ($("#chlId").val()) {
                $("#chlId").prop("disabled", false);
                $("#secManageAcctId").prop("disabled", true);
            } else if ($("#secManageAcctId").val()) {
                $("#chlId").prop("disabled", true);
                $("#secManageAcctId").prop("disabled", false);
            } else {
                $("#chlId").prop("disabled", false);
                $("#secManageAcctId").prop("disabled", false);
            }
        } else {
            $("#chlId").prop("disabled", false);
            $("#secManageAcctId").prop("disabled", true);
        }
        pageItemDisplay();
    }
    // 场内基金改造 add by renping 20191117 end
    //查询资产管理人
    var selectManeger = function () {
        if (initFlag) {
            return;
        }
        var data = $('#finprodId').select2('data')[0].object;
        var ps = $("#ps").val()
        if (data && data.finprodId) {
            if ((ps == ps_mr || ps == ps_mc) && $("#tradeMarket").val() == TRADE_PLACE_03 && !$("#chlId").val()) {
                $("#trdInvestPersonInfo_counterId").textSelect('setValue', COUNTER_PARTY_ID_SHANGHAI)
                $("#trdInvestPersonInfo_counterId").trigger('change');
                $("#trdInvestPersonInfo_counterId").prop('disabled', false);
            } else if ((ps == ps_mr || ps == ps_mc) && $("#tradeMarket").val() == TRADE_PLACE_04 && !$("#chlId").val()) {
                $("#trdInvestPersonInfo_counterId").textSelect('setValue', COUNTER_PARTY_ID_SHENZHEN);
                $("#trdInvestPersonInfo_counterId").trigger('change');
                $("#trdInvestPersonInfo_counterId").prop('disabled', false);
            } else if ((ps == ps_mr || ps == ps_mc)
                && $('#trdInvestPersonInfo_counterId').val()
                && $('#trdInvestPersonInfo_counterId').select2('data')[0].object != 'N'
                && $("#tradeMarket").val() != TRADE_PLACE_03
                && $("#tradeMarket").val() != TRADE_PLACE_04) {
                $('#trdInvestPersonInfo_counterId').val('').trigger('change');
                $("#trdInvestPersonInfo_counterId").prop('disabled', false);
            } else if ((ps != ps_mr && ps != ps_mc) && !$("#chlId").val()) {
                Joyin.ajaxJson(basePath + 'invest/trade/fund/TrdFundInvestTrade002/selectManeger', {issuer: data.issuer},
                    function (result) {
                        $("#trdInvestPersonInfo_counterId").textSelect('setValue', result.id);
                        $("#trdInvestPersonInfo_counterId").trigger('change');
                        $("#trdInvestPersonInfo_counterId").prop('disabled', true);
                    });
            } else if ($('#chlId').val() && $('#trdInvestPersonInfo_counterId').val()
                && ($('#trdInvestPersonInfo_counterId').select2('data')[0].object == 'N'
                    || $("#trdInvestPersonInfo_counterId").val() == COUNTER_PARTY_ID_SHANGHAI
                    || $("#trdInvestPersonInfo_counterId").val() == COUNTER_PARTY_ID_SHENZHEN)) {
                $('#trdInvestPersonInfo_counterId').val('').trigger('change');
            } else {
                $("#trdInvestPersonInfo_counterId").prop('disabled', false);
            }
        } else {
            $("#trdInvestPersonInfo_counterId").prop('disabled', false);
        }
    }
    /**
     * 动态设置不可编辑
     */
    var checkAndAssign = function (el, value, editable) {
        if (value != null && value != '') {
            $(el).val(value).trigger('change');
            if (!editable) {
                $(el).attr("disabled", true);
            }
        }
    };
    /**
     * 交易方向发生变化清空交易对手
     */
    var clearTrader = function () {
        $('#trdInvestPersonInfo_counterId').val('').trigger('change');
        $('#trdInvestPersonInfo_counterTrader').val('');
        $('#trdInvestPersonInfo_contactInfo').val('');
    }


    //画面初始化
    var init = function () {

        var entrustId = $("#entrustId").val();

        $('#finprodId').on('change', function () {
            var data = $('#finprodId').select2('data')[0].object;
            $("#settleSpreed").val('').change();
            if (data) {
                var finprodId = data.finprodId;
                var ps = $('#ps').val();
                // var portfolioId = $('#portfolioId').val();
                var ccy = $('#ccy').val();
                if (finprodId) {
                    $("#finprodName").val(data.finprodId).trigger('change');
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
                    $("#tradeMarket").val(data.tradeMarket).trigger('change');
                    $("#type6").val(data.type6).trigger('change');
                    //新增资产二、三、四级分类	王贵奇	2019/7/30	开始
                    $("#type9").val(data.type9).trigger('change');
                    $("#type10").val(data.type10).trigger('change');
                    $("#type11").val(data.type11).trigger('change');
                    //新增资产二、三、四级分类	王贵奇	2019/7/30	结束
                    if ((ps == ps_T04 || (ps == ps_T06 || $("#ps").val() == T65) || ps == ps_T52) && !entrustId) {
                        $('#trdInvestPersonInfo_counterId option').remove()
//                $("#trdInvestPersonInfo_counterId").textSelect('setValue',data.issuer);
                        selectManeger();
                    } else {
                        selectManeger();
                    }
                    //add  by zhangnian  20190917  start
                    if ($('#ps').val() == ps_T04) {
                        $("#settleSpreed").val(data.purSpreed).change();
                    } else if ((ps == ps_T06 || $("#ps").val() == T65)) {
                        $("#settleSpreed").val(data.redSpreed).change();
                    }
                    //add  by zhangnian  20190917  end
                } else {
                    //自动 加券
                    var autoCoupon = AutoCoupon.autoFundTradeCoupon($('#finprodId').select2('data')[0].text);
                    $('#finprodId').textSelect('setValue', autoCoupon.finprodMarketId);
                    $("#finprodName").val(autoCoupon.finprodName).trigger('change');
                    $("#calendarId").val(autoCoupon.calendarId).trigger('change');
                    $("#ccy").val(autoCoupon.ccy).trigger('change');
                    $("#type6").val(autoCoupon.type4).trigger('change');
                    $("#tradeMarket").val(autoCoupon.tradeMarket).trigger('change');
                    if ((ps == ps_T04 || (ps == ps_T06 || $("#ps").val() == T65) || ps == ps_T52) && !entrustId) {
                        $('#trdInvestPersonInfo_counterId option').remove()
                        selectManeger();
                    }

                }
            }
            // if ((ps == ps_T04 || ps == ps_mr || ps == ps_T52) && finprodId && portfolioId) {
            //     getPtlCashPositionInfo();
            // }
            // if (((ps == ps_T06 || $("#ps").val() == T65) || ps == ps_mc) && finprodId && portfolioId) {
            //     getPtlSecPositionInfo();
            // }
            $('#netDate').change();
        });

        // 场内基金改造 add by renping 20191117 start
        // 绑定按钮事件
        $('#tradeMarket').on('change', tradeMarketChange);
        // 场内基金改造 add by renping 20191117 end

        /**
         * 交易方向
         */
        $('#ps').on('change', function () {
            if (!initFlag) {
                $("#changeToFinprodMarketId").val('').trigger('change');
            }
            $(".changein").hide();
            var ps = $('#ps').val();
            var data = $('#finprodId').select2('data')[0].object;
            // var portfolioId = $('#portfolioId').val();
            var ccy = $('#ccy').val();
            var type6 = $('#type6').val();
            $("#settleSpreed").val('').change();
            if (ps == ps_T04 || ps == ps_T52) {
                $('#bussTrdAvailableCashQueryavalibleCash_div').show();
                $('#bussTrdAvailableCashQueryavalibleCash1_div').show();
                // $('#accAvailableCash_div').show();
                // <!-- 通道交易 新增 强成西 2019/03/26 start -->
                $('.channel_cash').show();
                // <!-- 通道交易 新增 强成西 2019/03/26 end -->
                $('#Assetsavailablepositions_div').hide();
                $('#expectedArrivalDate_div').hide();
                $('#transferInformation').show();
                $('#settleSpreed1_div').show();
                $('#settleSpreed2_div').show();
                // $('#confirmDate').hide();
                // $('#trdDate').show();
                $('#carryAmt_div').hide();
                $('#unitCprice_div').hide();
                $('#netDate_div').hide();
                $('#purchaseFee_div').hide();
                $('#tradeSha_div').hide();
//                $('#shareAmt').val("");
                $('#tradeAmt_div').show();
                $('#tradeAmt').attr("disabled", false);
                $(".channelAmt_class").hide();
//                $("#channelAmt").val("");
                if (data && data.finprodId) {
                    $('#trdInvestPersonInfo_counterId option').remove()
//                    $("#trdInvestPersonInfo_counterId").textSelect('setValue',$('#finprodId').select2('data')[0].object.issuer);
                    selectManeger();
                    $("#settleSpreed").val(data.purSpreed).change();
                }
                // if (data && data.finprodId && portfolioId && ccy) {
                //     getPtlCashPositionInfo();
                // }
            } else if (ps == ps_T06 || $("#ps").val() == T65) {
                $('#bussTrdAvailableCashQueryavalibleCash_div').hide();
                $('#bussTrdAvailableCashQueryavalibleCash1_div').hide();
                // $('#accAvailableCash_div').hide();
                // <!-- 通道交易 新增 强成西 2019/03/26 start -->
                $('.channel_cash').hide();
                // <!-- 通道交易 新增 强成西 2019/03/26 end -->
                $('#Assetsavailablepositions_div').show();
                $('#expectedArrivalDate_div').show();
                $('#transferInformation').hide();
                $('#settleSpreed1_div').show();
                $('#settleSpreed2_div').show();
                // $('#confirmDate').hide();
                // $('#trdDate').show();
                $('#carryAmt_div').hide();
                $('#unitCprice_div').hide();
                $('#netDate_div').hide();
                $('#purchaseFee_div').hide();
                $('#tradeSha_div').show();
                $('#tradeAmt_div').hide();
//                $('#tradeAmt').val("");
                $('#shareAmt').attr("disabled", false);
                $(".channelAmt_class").hide();
//                $("#channelAmt").val("");
                if (data && data.finprodId) {
                    $('#trdInvestPersonInfo_counterId option').remove()
//                    $("#trdInvestPersonInfo_counterId").textSelect('setValue',$('#finprodId').select2('data')[0].object.issuer);
                    selectManeger();
                    $("#settleSpreed").val(data.redSpreed).change();
                }
                // if (data && data.finprodId && portfolioId) {
                //     getPtlSecPositionInfo();
                // }
                if ($("#ps").val() == T65) {
                    $(".changein").show();
                }
                //-----add by 裴家俊 切换到赎回时清空风险试算列表 start ----
                if ($("#ps").val() == ps_T06 && $("#baseRiskMathCommTable").data('joyintable')!=null) {
                    $("#riskTrialSpan").empty();
                    $("#baseRiskMathCommTable").data('joyintable').reload({data:[]});
                    $("#riskMathFlag").val("01");
                }
                //-----add by 裴家俊 切换到赎回时清空风险试算列表 end ----
            } else if (ps == ps_mr) {
                $('#bussTrdAvailableCashQueryavalibleCash_div').show();
                $('#bussTrdAvailableCashQueryavalibleCash1_div').show();
                // $('#accAvailableCash_div').show();
                // <!-- 通道交易 新增 强成西 2019/03/26 start -->
                $('.channel_cash').show();
                // <!-- 通道交易 新增 强成西 2019/03/26 end -->
                $('#Assetsavailablepositions_div').hide();
                $('#expectedArrivalDate_div').hide();
                $('#transferInformation').show();
                $('#settleSpreed1_div').hide();
                $('#settleSpreed2_div').hide();
                // $('#confirmDate').show();
                // $('#trdDate').hide();
                $('#unitCprice_div').show();
                if ($('#type6').val() == type_01) {
                    $('#carryAmt_div').show();
                    $('#netDate_div').hide();
                } else {
                    $('#netDate_div').show();
                    $('#netDate').change();
                }
                $('#purchaseFee_div').show();
                $('#purchase').show();
                $('#redemp').hide();
                $('#tradeSha_div').show();
                $('#shareAmt').attr("disabled", true);
                $('#tradeAmt_div').show();
                $('#tradeAmt').attr("disabled", false);
                // if (data && data.finprodId && portfolioId && ccy) {
                //     getPtlCashPositionInfo();
                // }
                // $(".channelAmt_class").show();
                $('#netDate').change();
            } else if (ps == ps_mc) {
                $('#bussTrdAvailableCashQueryavalibleCash_div').hide();
                $('#bussTrdAvailableCashQueryavalibleCash1_div').hide();
                // $('#accAvailableCash_div').hide();
                // <!-- 通道交易 新增 强成西 2019/03/26 start -->
                $('.channel_cash').hide();
                // <!-- 通道交易 新增 强成西 2019/03/26 end -->
                $('#Assetsavailablepositions_div').show();
                $('#expectedArrivalDate_div').show();
                $('#transferInformation').show();
                $('#settleSpreed1_div').hide();
                $('#settleSpreed2_div').hide();
                // $('#confirmDate').show();
                // $('#trdDate').hide();
                if ($('#type6').val() == type_01) {
                    $('#carryAmt_div').show();
                    $('#netDate_div').hide();
                } else {
                    $('#carryAmt_div').hide();
                    $('#netDate_div').show();
                    $('#netDate').change();
                }
                $('#unitCprice_div').show();
                $('#purchaseFee_div').show();
                $('#purchase').hide();
                $('#redemp').show();
                $('#tradeSha_div').show();
                $('#shareAmt').attr("disabled", false);
                $('#tradeAmt_div').show();
                $('#tradeAmt').attr("disabled", true);
                // if (data && data.finprodId && portfolioId) {
                //     getPtlSecPositionInfo();
                // }
                // $(".channelAmt_class").show();
                $('#netDate').change();
            }
            <!-- 通道交易 新增 强成西 2019/03/26 start -->
            // changeTradeAmtAndFee();
            <!-- 通道交易 新增 强成西 2019/03/26 end -->

            // 交易方向变更 清空交易对手 yjm 2019/07/01 start
            if ($('#trdInvestPersonInfo_counterId').select2('data') && $('#trdInvestPersonInfo_counterId').select2('data')[0]) {
                var 交易对手 = $('#trdInvestPersonInfo_counterId').select2('data')[0].object;
                if (typeof 交易对手 != 'undefined') {
                    if ((交易对手 != 'N' && (ps == ps_mc || ps == ps_mr) && !isTradeMarketDeal())
                        || (ps && 交易对手 && 交易对手 != 'S' && ps != ps_mc && ps != ps_mr)) {
                        clearTrader();
                    }
                }
            }
            selectManeger();
            //交易方向变更 清空交易对手 yjm 2019/07/01 end
            // 画面控制联动处理
            pageItemDisplay();
        });

        /**
         * 基金类型
         */
        $('#type6').on('change', function () {
            var ps = $('#ps').val();
            if ($('#type6').val() == type_01 && (ps == ps_mc || ps == ps_mr)) {
                $('#carryAmt_div').show();
                table.showColumns([{field:'carryAmt',visible: true}]);
            } else {
                $('#carryAmt_div').hide();
                table.showColumns([{field:'carryAmt',visible: false}]);
            }
            if ($('#type6').val() == type_01 && (ps == ps_mc || ps == ps_mr)) {
                if (!initFlag && !isTradeMarketFundMrMcDeal()) {
                    $('#unitCprice').val(1).change();
                } else if (!initFlag) {
                    $('#unitCprice').val('').change();
                }
                $('#unitCprice').trigger('blur');
                $('#netDate_div').hide();
            } else if (ps == ps_mc || ps == ps_mr) {
                if (!initFlag) {
                    $('#unitCprice').val('').change();
                }
                $('#netDate_div').show();
            }
        });


        /**
         * 清算速度change事件
         */
        $('#settleSpreed').change(function () {
            var settleSpeed = $('#settleSpreed').val();
            var tradeDate = $('#tradeDate').val();
            if ($('#ps').val() == ps_T52) {

            } else if ($('#ps').val() == ps_T52 && (!settleSpeed || !tradeDate)) {
                return;
            }
            //计算确认日期
            calcCfmDate();
            if (pageModel == PAGE_MODEL_NEW) {
                /*if (settleSpeed && settleSpeed != '' && tradeDate && tradeDate != ''){
                    /!*var num = 0;
                    if (settleSpeed === settleSpreed_T1){
                        num = 1;
                    }else if(settleSpeed === settleSpreed_T2){
                        num = 2;
                    }*!/
                    var num = settleSpeed;
                    $('#expectedArrivalDate').val(DateUtil.parse(tradeDate).DateAdd('d', num).Format('yyyy-MM-dd')).trigger('change');
                }
                if($("#settleSpreed_div").is(":hidden")){
                    var num = 0;
                    $('#expectedArrivalDate').val(DateUtil.parse(tradeDate).DateAdd('d', num).Format('yyyy-MM-dd')).trigger('change');
                }*/
            }
        });

        /**
         * 净值日期
         */
        $('#netDate').change(function () {
            
            var netDate = $('#netDate').val();
            var data = $('#finprodId').select2('data')[0].object;
            var tradeAmt = $('#tradeAmt').val();
            var type6 = $('#type6').val();
            if (isTradeMarketFundMrMcDeal()) {
                return;
            }
            if ((!initFlag || $('#entrustId').val()) && netDate && data && data.finprodId && type6 == type_02) {
                var data = {finprodId: data.finprodId, netDate: netDate}
                Joyin.ajaxJson(basePath + 'invest/trade/fund/TrdFundInvestTrade003/getPrice', data, function (result) {
                    $('#unitCprice').val(result).trigger('blur').change();
                    // unitCpriceChange_DZH(result);
                });
            } else if ((!initFlag || $('#entrustId').val()) && type6 == type_01 && $('#ps').val() != ps_mr && $('#ps').val() != ps_mc) {
                if (data && data.etfType == ETF_TYPE_06) {
                    $('#unitCprice').val(100).trigger('blur');
                    // unitCpriceChange_DZH(100);
                } else if (data && data.trmFundType == TRM_FUND_TYPE_05) {
                    $('#unitCprice').val(0.01).trigger('blur');
                    // unitCpriceChange_DZH(0.01);
                } else {
                    $('#unitCprice').val(1).change();
                    $('#unitCprice').trigger('blur');
                    // unitCpriceChange_DZH(1);
                }
            }
        });

        /**
         * 组合分配：单位净值  净值日期发生改变引起组合分配中单位净值发生改变
         */
        var unitCpriceChange_DZH = function (unitCprice) {
            for(var i=0;i<table.getAllRows().length;i++){
                var obj = {row:i,
                    tradeAmt:table.getAllRows()[i].tradeAmt,
                    shareAmt:table.getAllRows()[i].shareAmt,
                    carryAmt:table.getAllRows()[i].carryAmt,
                    unitCprice:unitCprice,
                    channelFee:table.getAllRows()[i].channelFee,
                    commissionAmt:table.getAllRows()[i].commissionAmt}
                table.renderRow(i,  {"unitCprice": unitCprice});
                unitCpriceDZHChange_DZH(obj);
            }
        }

        /**
         * 单位净价
         */
        $('#unitCprice').change(function () {
            var unitCprice = $('#unitCprice').val();
            var tradeAmt = $('#tradeAmt').val();
            var ps = $('#ps').val();
            var shareAmt = $('#shareAmt').val();
            if (!initFlag && !isTradeMarketDeal()) {
                if (ps == ps_mr) {
                    if (unitCprice != null && unitCprice != '' && unitCprice != 0 && tradeAmt) {
                        $('#shareAmt').val(tradeAmt / unitCprice).trigger('blur');

                    }
                } else if (ps == ps_mc) {
                    if (unitCprice != null && unitCprice != '' && shareAmt) {
                        $('#tradeAmt').val(shareAmt * unitCprice).trigger('blur');
                        $('#tradeAmtHid').val(shareAmt * unitCprice);
                    }
                }
            } else if (!initFlag) {
                var unitCprice = $('#unitCprice').val();
                var tradeAmt = $('#tradeAmt').val();
                var ps = $('#ps').val();
                var shareAmt = $('#shareAmt').val();
                var carryAmt = $('#carryAmt').val();
                if (!carryAmt) {
                    carryAmt = 0;
                }
                if (unitCprice && shareAmt) {
                    calcTradeAmt();
                }
            }
            unitCpriceChange_DZH(unitCprice);
        });



        /**
         * 交易金额
         */

        $('#tradeAmt').change(function () {
            if (!initFlag && !isTradeMarketFundMrMcDeal()) {
                var feeAmtPay = $('#feeAmtPay').val();
                var tradeAmt = $('#tradeAmt').val();
                var ps = $('#ps').val();
                var unitCprice = $('#unitCprice').val();
                $('#tradeAmtHid').val(tradeAmt);
                if (unitCprice && tradeAmt && ps == ps_mr) {
                    var carryAmt = 0
                    if ($('#carryAmt').val()) {
                        carryAmt = $('#carryAmt').val();
                    }
                    $('#shareAmt').val($('#tradeAmt').val() / unitCprice - carryAmt).trigger('blur');
//                    if($('#type6').val() == type_01){
//                        $('#carryAmt').change();
//                    }
                }
            }
            changeTradeAmtAndFee();
        });

        /**
         * 交易份额
         */
        $('#shareAmt').change(function () {
            calcTradeAmt();
        });

        /**
         * 待结转份额
         */
        $('#carryAmt').change(function () {
            var carryAmt = $('#carryAmt').val();
            var tradeAmtHid = $('#tradeAmtHid').val();
            if (!carryAmt) {
                carryAmt = 0;
            }
            if (!initFlag) {
                $('#tradeAmt').val(parseFloat(tradeAmtHid) + parseFloat(carryAmt)).trigger('blur');
                changeTradeAmtAndFee();
            }
        });
        $('#entrustId').change(function () {
            var entrustId = $('#entrustId').val();
            if (entrustId) {
                $('#portfolioId').attr('disabled', true);
                $('#ps').attr('disabled', true);
                var data = $('#finprodId').select2('data')[0].object;
                if (data && data.finprodId) {
                    $('#finprodId').attr('disabled', true);
                } else {
                    $('#finprodId').attr('disabled', false);
                }
                $('#changeToFinprodMarketId').prop('disabled', true);
                $('#TrdDepositInvestTrade003_btn_autoAllocation').removeClass('layui-btn-disabled');
            } else {
                $('#portfolioId').attr('disabled', false);
                $('#ps').attr('disabled', false);
                $('#finprodId').attr('disabled', false);
            }
        });

        $("#changeToFinprodMarketId").change(function () {
            if ($("#changeToFinprodMarketId").val() && $('#changeToFinprodMarketId').select2('data')[0].text) {
                $("#changeToFinprodName").val($('#changeToFinprodMarketId').select2('data')[0].id);
            } else {
                $("#changeToFinprodName").val("")
            }
        })
        
        //确认日期联动预计到账日
        $("#purCfmDate").change(function(){
        	$("#expectedArrivalDate").val($("#purCfmDate").val());
        });

        // zhangnian add 20190917 交易日期+清算速度 联动计算确认日期 start
        var calcCfmDate = function () {
            var selectData = $('#finprodId').select2('data')[0].object;
            if ($('#ps').val() == ps_T52) {
                if (selectData) {
                    if (pageModel == PAGE_MODEL_NEW) {
                        $("#purCfmDate").val(selectData.vdate).trigger("change");
                        return;
                    }
                }
            } else {
                if (isTradeMarketDeal() && $('#tradeDate').val()) {
                    if ($('#type6').val() == type_01 ||
                        (selectData.etfType == ETF_TYPE_03 || selectData.etfType == ETF_TYPE_04 || selectData.etfType == ETF_TYPE_05)) {
                        $('#settleSpreed').val("0");
                        if (pageModel == PAGE_MODEL_NEW) {
                            $("#purCfmDate").val($('#tradeDate').val()).trigger("change");
                        }
                        $("#expectedArrivalDate").val($('#tradeDate').val()).trigger("change");
                        return;
                    } else {
                        $('#settleSpreed').val("1");
                    }
                }
                var tradedate = $('#tradeDate').val();
                var settleSpeed = $('#settleSpreed').val();
                var calendarId = $('#calendarId').val();
                if (settleSpeed && tradeDate) {
                    Joyin.ajaxSync(basePath + 'invest/trade/TrdCommonController/getNextWorkDay', {
                        'calendarId': calendarId,
                        'tradeDate': tradedate,
                        'settleSpreed': settleSpeed
                    }, function (result) {
                        if (pageModel == PAGE_MODEL_NEW) {
                            $("#purCfmDate").val(result).trigger("change");
                        }
                        //update by zhangnian 20191102  start 预计到账日需要节假日推迟
                        $("#expectedArrivalDate").val(result).trigger("change");
                        //update by zhangnian 20191102  end 预计到账日需要节假日推迟
                    });
                }
            }
        }
        // zhangnian add 20190917 交易日期+清算速度 联动计算确认日期 end

        window.componentLoadCallback = function (isDone, pagePath, params) {
            if (isDone) {
                // var riskparam = getriskdata();
                // if (pageModel == PAGE_MODEL_NEW) {
                //     riskparam.data = null;
                // }
                // window.BaseRiskMathComm.getparam(riskparam);
                window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                    var selectData = $('#finprodId').select2('data')[0].object;
                    if (!selectData) {
                        Joyin.error("请选择资产代码。")
                        return;
                    }
                    var list = new Array();
                    var tempList = table.getAllRows();
                    if(!tempList || tempList.length == 0){
                        Joyin.error("组合分配不能为空");
                        return;
                    }
                    for(var row in tempList){
                        var dealInfo = {};
                        dealInfo.counterId = $('#trdInvestPersonInfo_counterId').val();
                        if (($("#ps").val() == ps_mr || $("#ps").val() == ps_mc) && tempList[row].tradeAmtAndFee != null) {
                            dealInfo.tradeAmt = tempList[row].tradeAmtAndFee;
                        } else {
                            dealInfo.tradeAmt = tempList[row].tradeAmt;
                        }
                        dealInfo.profitType = $('#profitType').val();
                        dealInfo.ps = $('#ps').val();
                        dealInfo.finprodId = selectData.finprodId;
                        dealInfo.portfolioId = tempList[row].portfolioId;
                        dealInfo.ccy = $('#ccy').val();
                        dealInfo.tradeDate = $('#tradeDate').val();
                        dealInfo.finprodType2 = $('#finprodType2').val();
                        dealInfo.profitType = $('#profitType').val();
                        // <!-- 通道交易 新增 强成西 2019/03/26 start -->
                        dealInfo.chlId = $('#chlId').val();
                        // <!-- 通道交易 新增 强成西 2019/03/26 end -->
                        dealInfo.tradeMarket = $('#tradeMarket').val();
                        dealInfo.secManageAcctId = $('#secManageAcctId').val();
                        list.push(dealInfo);
                    }

                    if (!($('#ps').val() == ps_mr || $('#ps').val() == ps_mc)
                        && ($("#tradeMarket").val() == TRADE_PLACE_03 || $("#tradeMarket").val() == TRADE_PLACE_04)
                        && !$('#chlId').val() && !$('#secManageAcctId').val()) {
                        Joyin.error("交易所基金交易时，" + JoyinUtil.getMessage('TRD_E_0014',JoyinUtil.getMessage('invest.channelId')))
                        return;
                    }
                    return {
                        'isTotalTrade':YES_OR_NO_N
                        ,'bankLendingType':null
                        ,'portfolioId':'dzh'
                        ,'jiaoyiType':'dzh'
                        ,'deal':list
                    };
                });
                if (pageModel == PAGE_MODEL_EDIT) {
                    window.BussTrdTransferOrder['bussTrdTransferOrder'].saveCalcCondition()
                }
                $("#trdInvestPersonInfo_counterId").on("change", counterIdOnChange);
                pageItemDisplay();
                initFlag = false;
            }
        }

        $('#ps').change();
        $('#type6').change();
        $('#finprodId').change();
        $('#shareAmt').change();
        $('#entrustId').change();
    };

    //add by lizhi 2020-2-26 划款指令是否自动生成  start
    var generateOrders = function (callback) {
    	// 如果还款指令设置为隐藏，则自动生成划款指令
    	if(GEN_PAY_ORDERS_AUTO =='01'){
    		$('#bussTrdTransferOrder_btn_generate').click();
    	}
    	// 回调方法
	    if (callback && typeof callback === 'function') {
	        callback();
	    }
    }
    //add by lizhi 2020-2-26 划款指令是否自动生成  end

    /**
     * 组合分配：联动、计算处理   start
     */
    // 组合分配：计算交易金额
    var calcTradeAmt_DZH = function (object) {    
        var row = object.row;
        if (!initFlag && !isTradeMarketFundMrMcDeal()) {
            var unitCprice = object.unitCprice;
            var ps = $('#ps').val();
            var shareAmt = object.shareAmt;
            if (unitCprice && shareAmt && ps == ps_mc) {
                // $('#tradeAmt').val(unitCprice * shareAmt).trigger('blur');
                table.renderRow(row,  {"tradeAmt": unitCprice * shareAmt});
                object.tradeAmt = unitCprice * shareAmt;
                changeTradeAmtAndFee_DZH(object);
                // $('#tradeAmtHid').val(unitCprice * shareAmt);
                if ($('#type6').val() == type_01) {
                    // $('#carryAmt').change();
                    carryAmtDZHChange_DZH(object);
                }
            }
        } else if (!initFlag) {
            var unitCprice = object.unitCprice;
            var tradeAmt = object.tradeAmt;
            var ps = $('#ps').val();
            var shareAmt = object.shareAmt;
            var carryAmt = object.carryAmt;
            if (!unitCprice || !shareAmt) {
                return;
            }
            if (!carryAmt) {
                carryAmt = 0;
            }
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
                        } else {
                            // $("#isPayWithTrade").val('');
                        }
                    }, false);

            }
            var chlFee = object.channelFee;
            if ($('#chlId').val()) {
                var data = $('#chlId').select2('data')[0].object;
                if (data && data.collectType) {
                    // $('#collectType').val(data.collectType);
                    if (data.collectType != collectType_01) {
                        chlFee = 0;
                    }
                }
            }
            if ($("#ps").val() == ps_mr || $("#ps").val() == ps_T04 || $("#ps").val() == ps_T52) {
                // $("#tradeAmt").val(JoyinMath.doAdd(JoyinMath.doAdd(Number(shareAmt) * Number(unitCprice), chlFee, 2), commissionFee, 2)).trigger('blur');
                table.renderRow(row,  {"tradeAmt": JoyinMath.doAdd(JoyinMath.doAdd(Number(shareAmt) * Number(unitCprice), chlFee, 2), commissionAmt, 2)});
                object.tradeAmt = JoyinMath.doAdd(JoyinMath.doAdd(Number(shareAmt) * Number(unitCprice), chlFee, 2), commissionAmt, 2);
                // $('#tradeAmtHid').val($('#tradeAmt').val());
            } else {
                // $("#tradeAmt").val(JoyinMath.doMinus(JoyinMath.doMinus(Number(shareAmt) * Number(unitCprice), chlFee, 2), commissionFee, 2)).trigger('blur');
                table.renderRow(row,  {"tradeAmt": JoyinMath.doMinus(JoyinMath.doMinus(Number(shareAmt) * Number(unitCprice), chlFee, 2), commissionAmt, 2)});
                object.tradeAmt = JoyinMath.doMinus(JoyinMath.doMinus(Number(shareAmt) * Number(unitCprice), chlFee, 2), commissionAmt, 2);
                // $('#tradeAmtHid').val($('#tradeAmt').val());
            }
            changeTradeAmtAndFee_DZH(object);
            if ($('#type6').val() == type_01) {
                // $('#carryAmt').change();
                carryAmtDZHChange_DZH(object);
            }
        }
    };
    // 组合分配：计算TradeAmtAndFee
    var changeTradeAmtAndFee_DZH = function(object) {
        var row = object.row;
        var tradeAmt = object.tradeAmt;
        var channelFee = object.channelFee;
        if ($("#ps").val() == ps_mr || $("#ps").val() == ps_mc) {
            if ($('#trdInvestPersonInfo_counterId').select2('data') && $('#trdInvestPersonInfo_counterId').select2('data')[0]) {
                var 交易对手 =  $('#trdInvestPersonInfo_counterId').select2('data')[0].object;
                if (交易对手 == 'N') {
                    // $("#tradeAmtAndFee").val(JoyinMath.doMinus($("#tradeAmt").val(), $("#channelAmt").val(), 2)).trigger('blur');
                    table.renderRow(row,  {"tradeAmtAndFee": JoyinMath.doMinus(tradeAmt, channelFee, 2)});
                } else {
                    //外部卖出
                    if($("#ps").val() == ps_mc &&$("#chlId").val()&&$("#chlId").select2("data")[0].object.collectType==collectType_01){
                        // $("#tradeAmtAndFee").val(JoyinMath.doMinus($("#tradeAmt").val(), $("#channelAmt").val(), 2)).trigger('blur');
                        table.renderRow(row,  {"tradeAmtAndFee": JoyinMath.doMinus(tradeAmt, channelFee, 2)});
                    }
                }
            }
        }
        /*if ($("#ps").val() == PS_T02&&$("#chlId").val()&&$("#chlId").select2("data")[0].object.collectType==Collect_Type_01) {
               $("#tradeAmtAndFee").val(JoyinMath.doMinus($("#tradeAmtAndFee").val(), $("#channelFee").val(), 2)).trigger('blur');
        }*/

    }
     // 组合分配：单位净值Change  单位净值发生改变引起组合分配中金额发生改变
    var unitCpriceDZHChange_DZH = function (object) {
        var ps = $('#ps').val();
        var row = object.row;
        var unitCprice = object.unitCprice;
        var tradeAmt = object.tradeAmt;
        var shareAmt = object.shareAmt;
        if (!initFlag && !isTradeMarketDeal()) {
            if (ps == ps_mr) {
                if (unitCprice != null && unitCprice != '' && unitCprice != 0 && tradeAmt) {
                    // $('#shareAmt').val(tradeAmt / unitCprice).trigger('blur');
                    table.renderRow(row,  {"shareAmt": tradeAmt / unitCprice});
                }
            }
        }
        if (unitCprice && shareAmt) {
            calcTradeAmt_DZH(object);
        }
    }
    //组合分配：交易金额Change
    var tradeAmtDZHChange_DZH = function (object) {
        if (!initFlag && !isTradeMarketFundMrMcDeal()) {
            var ps = $('#ps').val();
            var row = object.row;
            var tradeAmt = object.tradeAmt;
            var unitCprice = object.unitCprice;
            if (unitCprice && tradeAmt && ps == ps_mr) {
                var carryAmt = 0;
                if (object.carryAmt) {
                    carryAmt = object.carryAmt;
                }
                // $('#shareAmt').val($('#tradeAmt').val() / unitCprice - carryAmt).trigger('blur');
                table.renderRow(row,  {"shareAmt": tradeAmt / unitCprice - carryAmt});
                object.shareAmt = tradeAmt / unitCprice - carryAmt;
            }
        }
        changeTradeAmtAndFee_DZH(object);
    }
    //组合分配：待结转份额Change
    var carryAmtDZHChange_DZH = function (object) {
        var row = object.row;
        var carryAmt = object.carryAmt || 0;
        var shareAmt = object.shareAmt || 0;
        if (!carryAmt) {
            carryAmt = 0;
        }
        if (!initFlag) {
            // $('#tradeAmt').val(parseFloat(tradeAmtHid) + parseFloat(carryAmt)).trigger('blur');
            table.renderRow(row,  {"tradeAmt": parseFloat(shareAmt) + parseFloat(carryAmt)});
            object.tradeAmt = parseFloat(shareAmt) + parseFloat(carryAmt);
            changeTradeAmtAndFee_DZH(object);
        }
    }
     // 组合分配：联动、计算处理   end

    /**
     * 画面加载完成
     */
    $(function () {
        //初始化画面
        init();
        // 是否显示划款指令
        if(GEN_PAY_ORDERS_AUTO=='01'){
        	$('#transferInformation').hide() ;
        }else{
        	$('#transferInformation').show() ;
        }
        // 绑定按钮事件
        $('#TrdFundInvestTrade004_btn_save').click(function () {
                saveOrSubmit('save');
        });
        $('#TrdFundInvestTrade004_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
        $('#TrdFundInvestTrade004_btn_view').click(function () {
            var data = $('#finprodId').select2('data')[0].object;
            if (data && data.finprodId) {
                var finprodId = data.finprodId;
                Joyin.dialog(JoyinUtil.getLabel("FinFundTarget002.detail"),
                    basePath + "invest/asset/fund/FinFundTarget002/init",
                    "95%", "90%", {
                        pageModel: PAGE_MODEL_REFERENCE,
                        finprodId: finprodId
                    });
            }
        });
        //风险试算
        $('#TrdFundInvestTrade004_btn_risk').click(function(){
            //window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
            //-----------add by jjpei 2020-01-07 风险新版本 start----------
            //---赎回申请不需要风险试算----
            if($("#ps").val()==ps_T06){
                return;
            }
            var object = $.serializeToObject('#submitForm', true);
            if(object.shareAmt && object.unitCprice && !object.tradeAmt){
                object.tradeAmt = Number(object.shareAmt)*Number(object.unitCprice);
            }
            for(var i=0;i<table.getAllRows().length;i++){

            }
            var data = $('#finprodId').select2('data')[0].object;
            if (data && data.finprodId) {
                object.finprodId = data.finprodId;
                object.finprodMarketId = $('#finprodId').val();
            }
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfBatchData/1/RSK002/F02', object,getriskdata());
            //-----------add by jjpei 2020-01-07 风险新版本 end----------'riskrds/interf/doInfBatchData/1/RSK002/F04'
            // window.BaseRiskMathComm.initBaseRiskMathCommPageCus(getriskdata());

        })
        //是否展示资产四五级分类
        if (IS_SHOW_TYPE == '01') {
            $('#selector5').show();
        } else {
            $('#selector5').hide();
        }
    });

}());
