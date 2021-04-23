//@ sourceURL=EtrInvestBondInfo002.js
/**
 * 债券投资委托新建
 */
(function () {
    var initFlag = true;
    var isautobond = false;
    var getriskdata = function () {
        var obj = {
            tradeDirection: $('#ps').val(),
            timePoint: MY_SUP_TIME_TYPE_02,
            valDate: sysDate,
            portfolioCode: $('#portfolioId').val(),
            tradeAmount: $('#entrustNum').val(),
            assetType: fintype,
            isEnabled: '01',
            assetCode: $('#finprodIdhid').val()
        }
        var object = {
            tradeDirection: ['ps', 'EtrInvestBondInfo002.ps'],
            portfolioCode: ['portfolioId', 'EtrInvestBondInfo002.portfolioId'],
            tradeAmount: ['entrustNum', 'EtrInvestBondInfo002.entrustAmt'],
            assetType: ['assetType1', 'EtrInvestBondInfo002.bondType'],
            data: obj
        }
        return object;
    }

    /**
     * 保存或提交
     *
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        var s;
        if(typeof(BaseRiskMathComm) != 'undefined') {
            if (BaseRiskMathComm) {
                s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
                if (!s && s != '') {
                    return;
                }
                if (window.BaseRiskMathComm.isCalcConditionChanged(getriskdata())) {
                    return;
                }
            }
        }
        if ($('#priceModel').val() == 'PRM02') {//限价指令
            if ($('#isRight').val() == 'Y') {//为含权债的时候
                if (!$('#price').val() && !$('#matureYield').val() && !$('#exerYield').val()) {
                    Joyin.warn("含权债时净价、到期收益率、行权收益率三选一必填");
                    return;
                }
            } else {
                if (!$('#price').val() && !$('#matureYield').val()) {
                    Joyin.warn("净价、到期收益率二选一必填");
                    return;
                }
            }
        }

        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#searchForm');
        if ($('#select2-finprodId-container').attr('title')){
            object.finprodName = $('#select2-finprodId-container').attr('title');
        }
        object.operateType = operateType;
        object.pageModel = pageModel;
        object.entrustId = $('#entrustId').val();
        object.entrustStatus = $('#entrustStatus').val();
        object.finvdate = $('#finvdate').val();
        if ($('#etrInvestPersonInfo_counterId').select2('data') && $('#etrInvestPersonInfo_counterId').select2('data')[0]) {
            object.counterType = $('#etrInvestPersonInfo_counterId').select2('data')[0].object;
        }
        object.finmdate = $('#finmdate').val();
        object.etrInvestPersonInfo.invManagerId = $('#etrInvestPersonInfo_invManagerId').val();
        if (object.etrInvestPersonInfo.trader != null && object.etrInvestPersonInfo.trader != '') {
            object.etrInvestPersonInfo.trader = object.etrInvestPersonInfo.trader.join(",");
        }
        if (FileCommon) {
            object.fileArray = FileCommon.getUploadFileInfo();
            if (object.fileArray && _.isArray(object.fileArray)) {
                $.each(object.fileArray, function (index, obj) {
                    obj.seqNo = obj.fileNo;
                });
            }
        }
        /*if($('#priceModel').val() == PRICEMODEL_03){
            Joyin.ajaxJson(basePath + 'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo002/getClosePriceByPara',{finprodMarketId:object.finprodMarketId},
                    function(result) {
                object.closePrice = result;
            }, false);
        }*/
        Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/check', object, function(result1) {
        	Joyin.ajaxJson(basePath + 'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo002/checkAuthorization', object, function(result2) {
	            Joyin.ajaxJson(basePath + 'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo002/check', object,
	                function (result) {
                        if(isautobond == true){
                            Joyin.alert("资产第一次接入系统，请确认并完善【债券标的】页面资产要素完整！",1,function () {
                                Joyin.confirm(result.data, function () {
                                    if (operateType === 'save') {
                                        WorkFlow.doSaveList(basePath + 'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo002/save', object, "EtrInvestBondInfo002_btn_save");
                                    } else {
                                        WorkFlow.doSubmitList(basePath + 'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo002/save', object, "EtrInvestBondInfo002_btn_submit");
                                    }
                                });
                            });
                        }else{
                            Joyin.confirm(result.data, function () {
                                if (operateType === 'save') {
                                    WorkFlow.doSaveList(basePath + 'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo002/save', object, "EtrInvestBondInfo002_btn_save");
                                } else {
                                    WorkFlow.doSubmitList(basePath + 'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo002/save', object, "EtrInvestBondInfo002_btn_submit");
                                }
                            });
                        }
	                });
        	});
        });
    }

    var getIsautobond = function(){
        return isautobond;
    }

    window.EtrInvestBondInfo002 = {
        getIsautobond: getIsautobond
    };

    //查询组合可用现金
    var getPtlCashPositionInfo = function () {
        if (!$('#portfolioId').val() || !$('#ccy').val()) {
            return;
        }
        var data = {
            portfolioId: $('#portfolioId').val(),
            ccy: $('#ccy').val(),
            vdate: $('#vdate').val(),
            calId: $('#calendarId').val(),
            chlFinprodId: $("#chlFinprodId").val()
        }
        Joyin.ajaxJson(basePath + 'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo002/getProAvailableCash', data,
            function (result) {
                $('#proAvailableCash').val(result.cashT).trigger('blur');
                $('#proAvailableCash1').val(result.cashT1).trigger('blur');
                $('#channelAvailableCash').val(result.cashT2).trigger('blur');
                $('#accAvailableCash').val(result.cashT3).trigger('blur');
            });
    }
    //查询组合可用持仓
    var getPtlSecPositionInfo = function () {
        var portfolioId1 = $('#portfolioId').val();
        var invAim1 = $('#invAim').val();
        var secManageAcctId = $('#secManageAcctId').val();
        var chlId = $('#chlFinprodId').val();
        if (portfolioId1 == '' || portfolioId1 == null || typeof (portfolioId1) == undefined
            || invAim1 == '' || invAim1 == null || typeof (invAim1) == undefined
            || (!secManageAcctId && !chlId)
        ) {
            $("#assetAvailablePosition").val('')
            $("#assetAvailablePosition1").val('')
            $("#SecManageAvailableCash").val('')
            $("#proAvailableCash").val('')
            $("#proAvailableCash1").val('')
            return;
        }
        var data = {
            portfolioId: portfolioId1,
            calId: $('#calendarId').val(),
            finprodId: $('#finprodIdhid').val(),
            ccy: $('#ccy').val(),
            secAcctId: $("#secManageAcctId").val(),
            chlId: $('#chlFinprodId').val()
        }
        // 一体化对应etliu start
        data.invAim = $('#invAim').val();
        // 一体化对应etliu end

        Joyin.ajaxJson(basePath + 'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo002/getAssetAvailablePosition', data,
            function (result) {
                $('#assetAvailablePosition').val(result.position*100).trigger('blur');
                $('#assetAvailablePosition1').val(result.positionT1*100).trigger('blur');
            });
    }

    //到期日小于委托结束日，给出提示
    var checkFinMdate = function () {
        if ($('#finprodId').val()) {
            var finMdate = (new Date($("#finprodId").select2('data')[0].object.finmdate)).format('yyyy-MM-dd');
            if (new Date(finMdate).DateDiff('d', $("#mdate").val()) > 0) {
                Joyin.error(JoyinUtil.getMessage("ETR_X_007"));
                $("#finprodId").val('').trigger('change');
            }
        }
    }

    //净价、行权收益率、到期收益率联动
    var calData = function (calflag) {
       // if (pageModel != PAGE_MODEL_REFERENCE) {
            var ps = $('#ps').val();
            // if (ps != T01  || !$('#price').val()) {
            //     return;
            // }
            if (!ps) {
                return;
            }
            if (!$('#priceModel').val() || !($('#priceModel').val() == 'PRM01' || $('#priceModel').val() == 'PRM02')) {
                return;
            }
            if (!calflag) {
                return;
            }
            if (!($("#finprodId").val())) {
                return
            }
            var object = $.serializeToObject('#searchForm');
            object.calflag = calflag;
            //        object.operateType = operateType;
            object.pageModel = pageModel;
            object.entrustId = $('#entrustId').val();
            object.entrustStatus = $('#entrustStatus').val();
            object.finvdate = $('#finvdate').val();
            object.finmdate = $('#finmdate').val();
            object.finprodId = $('#finprodIdhid').val();
            object.etrInvestPersonInfo.invManagerId = $('#etrInvestPersonInfo_invManagerId').val();
            if (object.etrInvestPersonInfo.trader != null && object.etrInvestPersonInfo.trader != '') {
                object.etrInvestPersonInfo.trader = object.etrInvestPersonInfo.trader.join(",");
            }
            if (object.bussCombDistribtList != null) {
                if (object.bussCombDistribtList.length > 0) {
                    var entrustNum = 0.00;
                    for (var index in object.bussCombDistribtList) {
                        var item = object.bussCombDistribtList[index];
                        if (item.entrustNum == null || item.entrustNum == '') return;
                        entrustNum += parseFloat(item.entrustNum);
                    }
                    object.entrustNum = String(entrustNum);
                }
            }
            if (typeof FileCommon != "undefined") {
                object.fileArray = FileCommon.getUploadFileInfo();
                if (object.fileArray && _.isArray(object.fileArray)) {
                    $.each(object.fileArray, function (index, obj) {
                        obj.seqNo = obj.fileNo;
                    });
                }
            }
            Joyin.ajaxJson(basePath + 'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo002/calData', object,
                function (result) {
                    if (result && result.data) {
                        var data = result.data;
                        if (data.price) {
                            $('#price').val(data.price).trigger("blur");
                            $("#fpriceAmt").val(data.unitFprice).trigger("blur");
                        } else {
                            $('#price').val('');
                            $("#fpriceAmt").val('');
                        }
                        if (pageModel != PAGE_MODEL_REFERENCE) {
                            $('#exerYield').val(data.exerYield).trigger("blur");
                            $('#matureYield').val(data.matureYield).trigger("blur");
                            //add by 张年  20190903  start
                            //$("#fpriceAmt").val(data.unitFprice).trigger("blur");
                            //add by 张年  20190903  endEtrInvestBondInfo002.html
                        }
                    }
                });
        //}
    }
    /**
     * 画星星
     */
    var star = function () {
        // 加红星星
        // 查找label
        var _findPrevLabel = function (element, level) {
            var $el = element.prev();
            if (!$el.is('label') && level > 0) {
                $el = $el.find('label');
                if ($el.length === 0) {
                    return _findPrevLabel(element.parent(), level - 1);
                }
            }
            return $el;
        };

        // 非查看模式
        if (typeof pageModel !== 'undefined'
            && (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_NEW)) {
            $('.must').each(function () {
                $(this).remove();
            })
            $('*[required]').each(function () {
                var $el = $(this);
                var $target = _findPrevLabel($el, 3);
                if ($target.find('> .must').length === 0) {
                    $target.append($('<i class="must">*</i>'));
                }
            });
        }
    }

    var init = function () {
        $('#ps').change();
        if ($('#finprodId').val() != null && $('#finprodId').val() != '') {
            $('#finprodId').change();
        }
        $('#portfolioId').change();
        $('#tradeMarket').change();
        $('#tradeMarketMode').change();

        initFlag = false;
        // 画星星
        star();
        if (shortSellingSupported == '02') {
            $(".shortSellingSupported").hide();
        }
    }

    //添加风险试算验证
    window.componentLoadCallback = function (isDone, pagePath, params) {
        if (isDone) {
            if(typeof(BaseRiskMathComm) != 'undefined') {
                var riskparam = getriskdata();
                if (pageModel == PAGE_MODEL_NEW) {
                    riskparam.data = null;
                }
                window.BaseRiskMathComm.getparam(riskparam);
            }
            if (initFlag) {
                init();
            }
            $('#isRight').trigger('change');
        }
    }
    /**
     * 绑定事件
     */
    $(function () {
        /**
         * 保存
         */
        $('#EtrInvestBondInfo002_btn_save').click(function () {
            saveOrSubmit('save');
        });
        /**
         * 提交
         */
        $('#EtrInvestBondInfo002_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
        /**
         * 风险试算
         */
        $('#EtrInvestBondInfo002_btn_riskcal').click(function () {
            // window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
            var object = $.serializeToObject('#searchForm');
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/0/RSK002/F01', object,getriskdata());
        });
        /**
         * 交易场所
         */
        $('#tradeMarket').change(function () {
            var tradeMarket = $('#tradeMarket').val();
            if (tradeMarket == bankRoom || tradeMarket == shse || tradeMarket == szse) {
                $('#tradeMarketMode').attr("required", "true");
            } else {
                $('#tradeMarketMode').attr("required", "false");
            }
            if (tradeMarket == shse || tradeMarket == szse) {

            }
            // 画星星
            star();
        });
        /**
         * 交易场所
         */
        $('#tradeMarket').change(function () {
            var tradeMarket = $('#tradeMarket').val();
            if (tradeMarket == bankRoom) {
                $('#tradePlat').val(bankPlat).trigger('change').attr('disabled', true);
            } else {
                $('#tradePlat').attr('disabled', false);
            }
        });
        /**
         * 交易市场
         */
        $('#tradeMarketMode').change(function () {
            var tradeMarketMode = $('#tradeMarketMode').val();
            if (tradeMarketMode == oneMarket) {
                $('#ps').prop('disabled', true).val(T01).trigger('change');
            } else {
                if (!initFlag) {
                    $('#ps').prop('disabled', false).trigger('change');
                }
            }

            if ($('#price').val()) {
                calData('price');
            }
        });
        /**
         * 交易方向
         */
        $('#ps').change(function () {
                var ps = $('#ps').val();
                var ccy = $('#ccy').val();
                var portfolioId = $('#portfolioId').val();
                var finprodIdhid = $('#finprodIdhid').val();
                var vdate = $('#vdate').val();
                if ($('#ps').val() == T01) {
                    $('#proAvailableCash_div').show();
                    $('#proAvailableCash1_div').show();
                    // $('#accAvailableCash_div').show();
                    // 通道交易 新增 强成西 2019/03/26 start
                    if ($("#chlFinprodId").val()) {
                        $("#accAvailableCash_c_div").show();
                    }
                    // 通道交易 新增 强成西 2019/03/26 end
                    $('#finprodId').removeAttr("required");
                    if ($('#isRight').val() != N) {
                        $('#exerYield').removeAttr("disabled");
                    }
                    $('#matureYield').removeAttr("disabled");
                    $('#assetAvailablePosition_div').hide();
                    $('#assetAvailablePosition1_div').hide();
                    if (ccy && portfolioId && vdate) {
                        getPtlCashPositionInfo();
                    }
                    $("#ownNum_label").text('卖空购回券面总额');
                } else if ($('#ps').val() == T02) {
                    $('#proAvailableCash_div').hide();
                    $('#proAvailableCash1_div').hide();
                    // $('#accAvailableCash_div').hide();
                    // 通道交易 新增 强成西 2019/03/26 start
                    $("#accAvailableCash_c_div").hide();
                    // 通道交易 新增 强成西 2019/03/26 end
                    $('#finprodId').attr("required", "true");
                    $('#exerYield').attr("disabled", true);
                    $('#matureYield').attr("disabled", true);
                    $('#exerYield').val("");
                    $('#matureYield').val("");
                    $('#assetAvailablePosition_div').show();
                    $('#assetAvailablePosition1_div').show();
                    if (portfolioId && finprodIdhid) {
                        getPtlSecPositionInfo();
                    }
                    $("#ownNum_label").text('融入券券面总额');
                    // 一体化对应etliu start
                    //                }else  if ($('#ps').val() == T03) {
                } else if ($('#ps').val() == T03 || $('#ps').val() == T39) {
                    // 一体化对应etliu end
                    $('#finprodId').attr("required", "true");
                    $('#exerYield').attr("disabled", true);
                    $('#matureYield').attr("disabled", true);
                    $('#exerYield').val("");
                    $('#matureYield').val("");
                    $('#proAvailableCash_div').hide();
                    $('#proAvailableCash1_div').hide();
                    // $('#accAvailableCash_div').hide();
                    // 通道交易 新增 强成西 2019/03/26 start
                    $("#accAvailableCash_c_div").hide();
                    // 通道交易 新增 强成西 2019/03/26 end
                    $('#assetAvailablePosition_div').show();
                    $('#assetAvailablePosition1_div').show();
                    if (portfolioId && finprodIdhid) {
                        getPtlSecPositionInfo();
                    }
                    //                    $('#assetAvailablePosition_div').hide();
                    //                    $('#assetAvailablePosition1_div').hide();
                }
                if (pageModel != PAGE_MODEL_REFERENCE) {
                    if ($('#price').val()) {
                        calData('price');
                    }
                }
                // 画星星
                star();
        });

        /**
         * 资产代码
         */

        $('#finprodId').change(function () {
            var ps = $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var vdate = $('#vdate').val();
            var finprodId = $('#finprodId').val();
            if (finprodId) {
                var data = $('#finprodId').select2('data')[0].object;
                if (!data.finprodId) {
                    //自动加券
                    data = AutoCoupon.autoBondTradeCoupon($('#finprodId').val());
                    isautobond = true;
                    data.finvdate = data.vdate;
                    data.finmdate = data.mdate;
                    data.assetType1 = data.type9;
                    data.assetType10 = data.type10;
                    data.assetType11 = data.type11;
                }
                // 不为空
                $('#price').attr("required", false);
                $('#exerYield').attr("required", false);
                $('#matureYield').attr("required", false);
                //var data = $('#finprodId').select2('data')[0].object;
                $('#finprodIdhid').val(data.finprodId).trigger('change');
                $('#finprodName').val(finprodId).trigger('change');
                if (!initFlag) {
                    $('#tradeMarket').val(data.tradeMarket).trigger('change');
                }
                $('#tradeMarket').attr('disabled', true);
                $('#isRight').val(data.isRight).trigger('change');
                $('#calendarId').val(data.calendarId).trigger('change');
                $('#assetType1').val(data.assetType1).trigger('change');
                $('#assetType10').val(data.assetType10).trigger('change');//新增资产三级分类 王贵奇 2019/7/27
                $('#assetType11').val(data.assetType11).trigger('change');//新增资产四级分类 王贵奇 2019/7/27
                $('#finvdate').val(new Date(data.finvdate)).trigger('change');
                $('#finmdate').val(new Date(data.finmdate)).trigger('change');
                $('#ccy').val(data.ccy).trigger('change').attr('disabled', true);
                if (ps == T01 && ccy && portfolioId && vdate) {
                    getPtlCashPositionInfo();
                }
                // 一体化对应etliu start
//                if((ps == T02 ||ps == T03) && portfolioId){
                if ((ps == T02 || ps == T03 || ps == T39) && portfolioId) {
                    // 一体化对应etliu end
                    getPtlSecPositionInfo();
                }
                Joyin.ajax(basePath + 'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo002/getAvailableNum?finprodId=' + data.finprodId, null,
                    function (result) {
                        $('#availableNum').val(result);
                    });
                checkFinMdate();
                if ($('#price').val()) {
                    calData('price');
                }
            } else {
                // 为空
                $('#price').attr("required", true);
                $('#exerYield').attr("required", true);
                $('#matureYield').attr("required", true);
                $('#finprodIdhid').val('').trigger('change');
                $('#finprodName').val('').trigger('change');
                $('#calendarId').val('').trigger('change');
                $('#assetType1').val('').trigger('change');
                $('#assetType10').val('').trigger('change');//新增资产三级分类 王贵奇 2019/7/27
                $('#assetType11').val('').trigger('change');//新增资产四级分类 王贵奇 2019/7/27
                $('#proAvailableCash1').val('');
                $('#channelAvailableCash').val('');
                $('#availableNum').val('');
                $('#tradeMarket').val('').trigger('change').attr('disabled', false);
                $('#ccy').val('').trigger('change').attr('disabled', false);
                $('#isRight').val('').trigger('change');

                $('#exerYield').val('').trigger('change').trigger('blur');
                $('#matureYield').val('').trigger('change').trigger('blur');
            }
            // 画星星
            star();
        });

        // 通道交易 新增 强成西 2019/03/26 start
        $("#chlFinprodId").change(function () {
            if (!$("#chlFinprodId").val()) {
                $("#accAvailableCash_c_div").hide();
                $("#secManageAcctId").prop('disabled', false);
                return;
            } else {
                $("#secManageAcctId").val('').prop('disabled', true).trigger('change');
                $("#accAvailableCash_c_div").show();
            }
            var ps = $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var vdate = $('#vdate').val();
            var finprodId = $('#finprodIdhid').val();
            if (ps == T01 && ccy && portfolioId && vdate) {
                getPtlCashPositionInfo();
            }
            if ($('#price').val()) {
                calData('price');
            }
        })
        // 通道交易 新增 强成西 2019/03/26 end

        /**
         * 币种
         */
        $('#ccy').change(function () {
            var ps = $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var vdate = $('#vdate').val();
            var finprodId = $('#finprodIdhid').val();
            if (ps == T01 && ccy && portfolioId && vdate) {
                getPtlCashPositionInfo();
            }
            // 一体化对应etliu start
//            if((ps == T02 || ps == T03) && ccy && portfolioId && finprodId){
            if ((ps == T02 || ps == T03 || ps == T39) && ccy && portfolioId && finprodId) {
                // 一体化对应etliu end
                getPtlSecPositionInfo();
            }
            getSecManageAvailableCash();
        });

        /**
         * 组合代码
         */
        $('#portfolioId').change(function () {
            var ps = $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var vdate = $('#vdate').val();
            var finprodId = $('#finprodIdhid').val();
            if (pageModel != PAGE_MODEL_REFERENCE && !initFlag) {
                $("#secManageAcctId").val('')
            }
            $("#secManageAcctId").trigger('change');
            if (portfolioId) {
                var data = $('#portfolioId').select2('data')[0].object;
                //$('#profitType').val(data).trigger('change');
                //组合代码和投资目的联动  yzg 20190904 start
                if (pageModel == PAGE_MODEL_NEW) {
                    var profitType = $('#portfolioId').select2('data')[0].col;
                    if (profitType == type01) {
                        $('#invAim').val(aim01).trigger('change');
                    } else if (profitType == type03) {
                        $('#invAim').val(aim02).trigger('change');
                    } else if (profitType == type02) {
                        $('#invAim').val(aim02).trigger('change');
                    } else {
                        $('#invAim').val('').trigger('change');
                    }
                }
                //组合代码和投资目的联动  yzg 20190904 end
            }
            if (ps == T01 && ccy && portfolioId && vdate) {
                getPtlCashPositionInfo();
                // 一体化对应etliu start
//            }else if((ps == T02 || ps == T03) && portfolioId && finprodId){
            } else if ((ps == T02 || ps == T03 || ps == T39) && portfolioId && finprodId) {
                // 一体化对应etliu end
                getPtlSecPositionInfo();
            }
            getSecManageAvailableCash();
        });

        /**
         * 证券托管户
         */
        $('#secManageAcctId').change(function () {
            if ($("#secManageAcctId").val()) {
                $("#chlFinprodId").val('').prop('disabled', true).trigger('change')
            } else {
                $("#chlFinprodId").prop('disabled', false)
            }
            getSecManageAvailableCash();
            getPtlSecPositionInfo();
        });

        // 一体化对应etliu start
        /**
         * 投资目的变化更新资产可用持仓
         */
        $("#invAim").on("change", function () {
            getPtlSecPositionInfo();
        });
        // 一体化对应etliu end
        $.fn.updateChosenArr = function updateChosenArr(obj) {
            var $this = $(this), arr = obj.dataResult || JSON.parse($this.attr('data-result') || '[]'), item = obj.item;
            $this.empty();
            $this.append($('<option value>'));
            for (var i in arr) {
                if (!arr.hasOwnProperty(i)) continue;
                if (obj.item && obj.item.length > 0) {
                    if ($.inArray(arr[i].id, item) >= 0)
                        $('<option>').attr('value', arr[i].id).text(arr[i].text).appendTo($this);
                } else
                    $('<option>').attr('value', arr[i].id).text(arr[i].text).appendTo($this)
            }
            if (obj.chosen)
                $this.val(obj.chosen);
            if (obj.disabled === true || obj.disabled === false)
                $this.attr('disabled', obj.disabled);
            $this.trigger("chosen:updated").trigger('change');
            if (obj.callback)
                obj.callback($this);
            return $this;
        }

        /**
         * 委托开始日
         */
        $('#vdate').change(function () {
            var ps = $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var vdate = $('#vdate').val();
            var finprodId = $('#finprodIdhid').val();
            if (ps == T01 && ccy && portfolioId && vdate) {
                getPtlCashPositionInfo();
            }

            if ($('#price').val()) {
                calData('price');
            }
        });

        /**
         * 委托终止日
         */
        $('#mdate').change(function () {
            checkFinMdate();
            if ($('#price').val()) {
                calData('price');
            }
        });

        /**
         * 清算速度
         */
        $('#settleSpreed').change(function () {
            if ($('#price').val() || $('#exerYield').val() || $('#matureYield').val()) {
            	if ($('#price').val()) {
                    calData('price');
                }
            }
        });
        // by 赵东东 20200227 start
        /**
         * 上交所固收平台123代码段的公司债，资产支持证券和中小企业私募债，清算速度为T+0或T+1,其他债券的清算速度为T+0,不可改。
         */
        $('#finprodId,#tradePlat,#settleSpreed').change(function (){
            var finprodId = $('#finprodId').val();
            var finprodIdarr;
            var fId;
            var finprodId1;
            if(finprodId){
            	 finprodIdarr = finprodId.split(".");
            	 finprodId1 = finprodIdarr[0];
            }
            if(finprodId && finprodId.length>3 && finprodId1.length==6){
             fId = finprodId.substring(0,3);
            }
            var tradeMarket = $('#tradeMarket').val();
            var tradePlat = $('#tradePlat').val();
            var assetType10 = $('#assetType10').val();
            var assetType11 = $('#assetType11').val();
            var settleSpreed = $('#settleSpreed').val();
            if(settleSpreed && fId == '123' && tradeMarket == '03' && tradePlat == '03' ) {
                if(assetType10 == '12' || assetType11== '012' || assetType11 =='014' ){
                    if(!(settleSpreed == 0 || settleSpreed ==1)){
                    	Joyin.alert(JoyinUtil.getMessage("ETR_BOND_1111"));
                    	$('#settleSpreed').val('');
                    }
                }else{
                	$('#settleSpreed').val('0').attr('disabled', true);
                }
            }else{
            	$('#settleSpreed').attr('disabled', false);
            }
        });
        // by 赵东东 20200227 end
        /**
         * 净价
         */
        $('#price').change(function () {
            var ps = $('#ps').val();
            var entrustNum = $('#entrustNum').val();
            if (entrustNum == null) entrustNum = 'null';
            if ($('#finprodId').val() && entrustNum && $(this).val()) {
                calData('price');
            } else if (!$('#finprodId').val() && ps == T01) {
                if ($('#price').val()) {
                    $('#exerYield').attr("disabled", true);
                    $('#matureYield').attr("disabled", true);
                    $('#exerYield').val("");
                    $('#matureYield').val("");
                } else {
                    $('#exerYield').removeAttr("disabled");
                    $('#matureYield').removeAttr("disabled");
                }
            }
        });
        /**
         * 行权收益率
         */
        $('#exerYield').change(function () {
            var ps = $('#ps').val();
            var entrustNum = $('#entrustNum').val();
            if ($('#finprodId').val() && entrustNum) {
                if ($('#exerYield').val()){
                    calData('exerYield');
                }
            } else if (!$('#finprodId').val() && ps == T01) {
                if ($('#exerYield').val()) {
                    $('#price').attr("disabled", true);
                    $('#matureYield').attr("disabled", true);
                    $('#price').val("");
                    $('#matureYield').val("");
                } else {
                    $('#price').removeAttr("disabled");
                    $('#matureYield').removeAttr("disabled");
                }
            }
        });
        /**
         * 到期收益率
         */
        $('#matureYield').change(function () {
            var ps = $('#ps').val();
            var entrustNum = $('#entrustNum').val();
            if ($('#finprodId').val() && entrustNum) {
            	 if ($('#matureYield').val()){
                     calData('matureYield');
                 }
            } else if (!$('#finprodId').val() && ps == T01) {
                if ($('#matureYield').val()) {
                    $('#price').attr("disabled", true);
                    $('#exerYield').attr("disabled", true);
                    $('#price').val("");
                    $('#exerYield').val("");
                } else {
                    $('#price').removeAttr("disabled");
                    $('#exerYield').removeAttr("disabled");
                }
            }
        });
        /**
         * 融入券券面总额
         */
        $('#entrustIntegrateNum').change(function () {
            var entrustNum = $("#entrustNum").val();
            if (!entrustNum) {
                entrustNum = 0;
            }
            var entrustIntegrateNum = $("#entrustIntegrateNum").val();
            var entrustOwnNum = JoyinMath.doMinus(entrustNum, entrustIntegrateNum, 2, JoyinMath.ROUND_HALF_UP)
            if (entrustOwnNum < 0) {
                $("#entrustOwnNum").val(entrustNum).trigger("blur");
                $("#entrustIntegrateNum").val(0).trigger("blur");
            } else {
                $("#entrustOwnNum").val(entrustOwnNum).trigger("blur");
            }
        });
        /**
         * 本方券券面总额
         */
        $('#entrustOwnNum').change(function () {
            var entrustNum = $("#entrustNum").val();
            if (!entrustNum) {
                entrustNum = 0;
            }
            var entrustOwnNum = $("#entrustOwnNum").val();

            var entrustIntegrateNum = JoyinMath.doMinus(entrustNum, entrustOwnNum, 2, JoyinMath.ROUND_HALF_UP)
            if (entrustIntegrateNum < 0) {
                $("#entrustNum").val(entrustOwnNum).trigger("blur");
                $("#entrustIntegrateNum").val(0).trigger("blur");
            } else {
                $("#entrustIntegrateNum").val(entrustIntegrateNum).trigger("blur");
            }
        });

        $('#entrustNum').change(function () {
            var ps = $('#ps').val();
            var entrustNum = $('#entrustNum').val();
            var price = $('#price').val();
            var exerYield = $('#exerYield').val();
            var matureYield = $('#matureYield').val();

            var entrustIntegrateNum = $("#entrustIntegrateNum").val();
            if (!entrustIntegrateNum) {
                $("#entrustIntegrateNum").val(0)
                entrustIntegrateNum = 0;
            }

            var entrustOwnNum = JoyinMath.doMinus(entrustNum, entrustIntegrateNum, 2, JoyinMath.ROUND_HALF_UP);
            if (entrustOwnNum < 0) {
                $("#entrustOwnNum").val(entrustNum).trigger("blur");
                $("#entrustIntegrateNum").val(0).trigger("blur");
            } else {
                $("#entrustOwnNum").val(entrustOwnNum).trigger("blur");
            }


            if ($('#finprodId').val() && ps == T01 && entrustNum) {
                if (price || exerYield || matureYield) {
                	  if ($('#price').val()) {
                          calData('price');
                      }
                      }
                  }
              });
        /**
         * 收益类型
         */
        $('#profitType').change(function () {
            var profitType = $('#profitType').val();
            if (!initFlag) {
                if (profitType == type01) {
                    $('#invAim').val(aim01).trigger('change');
                } else if (profitType == type03) {
                    $('#invAim').val(aim02).trigger('change');
                } else if (profitType == type02) {
                    $('#invAim').val(aim02).trigger('change');
                } else {
                    $('#invAim').val('').trigger('change');
                }
            }
        });
        /**
         * 是否行权
         */
        $('#isRight').change(function () {
            var isRight = $('#isRight').val();
            if (isRight == N) {
                $('#exerYield').val('').attr('disabled', true);
            } else {
                $('#exerYield').attr('disabled', false);
            }
        });
        /**
         * 交易所资金账户可用现金
         * 王贵奇 2019/9/5
         */
        $('#ps,#tradeMarket,#tradeMarketMode').change(function () {
            if ($('#ps').val() == T01 && ($('#tradeMarket').val() == shse || $('#tradeMarket').val() == szse) && $('#tradeMarketMode').val() == marketLv2) {
                $('#SecManageAvailableCash_div').show();
            } else {
                $('#SecManageAvailableCash_div').hide();
            }
        });
        /**
         * 价格模式
         */
        $('#priceModel').change(function () {
            if (pageModel != PAGE_MODEL_REFERENCE) {
                if ($('#priceModel').val() == PRICEMODEL_03) {
                    $('#price').val('').attr('disabled', true);
                    $('#fpriceAmt').val('');
                    $('#matureYield').val('').attr('disabled', true);
                    $('#exerYield').val('').attr('disabled', true);
                } else {
                    $('#price').attr('disabled', false);
                    $('#matureYield').attr('disabled', false);
                    $('#exerYield').attr('disabled', false);
                    $('#isRight').trigger('change');
                }
                if ($('#price').val()) {
                    calData('price');
                }
            }
        });
        var getSecManageAvailableCash = function () {
            if (!($('#tradeMarket').val() == shse || $('#tradeMarket').val() == szse)) return;
            if ($('#portfolioId').val() != '' && $('#portfolioId').val() != undefined && $('#ccy').val() != '' && $('#secManageAcctId').val() != '' && $('#secManageAcctId').val() != undefined) {
                var param = {
                    portfolioId: $('#portfolioId').val(),
                    ccy: $('#ccy').val(),
                    calendarId: $('#calendarId').val(),
                    secManageAcctId: $('#secManageAcctId').val()
                };
                Joyin.ajaxJson(basePath + 'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo003/getSecManageAvailableCash', param,
                    function (result) {
                        if (result) {
                            $('#SecManageAvailableCash').val(result).trigger('blur');
                        }
                    });
            } else {
                $('#SecManageAvailableCash').val('').trigger('blur');
            }
        }
        /**
         * 交易平台联动
         */
        $('#tradeMarket,#tradeMarketMode').on("change", function (e, isInit) {
            var tradeMarket = $('#tradeMarket').val();
            var tradeMarketMode = $('#tradeMarketMode').val();
            var $tradePlat = $('#tradePlat');
            var tradePlat = $tradePlat.val();
            if (initFlag && pageModel != PAGE_MODEL_REFERENCE) {
                var key = tradeMarket + tradeMarketMode;
                switch (key) {
                    //如果是交易场所+交易市场 == 银行间+一级市场，就可以选择01和99
                    case TRADE_PLACE_01 + SHARES_TRADE_MARKET_01:
                        $tradePlat.updateChosenArr({
                            item: [SHARES_TRADE_PLATFORM_01, SHARES_TRADE_PLATFORM_99],
                            chosen: tradePlat
                        });
                        break;
                    case TRADE_PLACE_01 + SHARES_TRADE_MARKET_02:
                        //如果是交易场所+交易市场 == 银行间+二级市场，只能选择01且置灰
                        $tradePlat.updateChosenArr({
                            item: [SHARES_TRADE_PLATFORM_01],
                            chosen: tradePlat
                        });
                        break;
                    case TRADE_PLACE_02 + SHARES_TRADE_MARKET_01:
                        //如果是交易场所+交易市场 == 上海证券交易所+一级市场;
                        $tradePlat.updateChosenArr({
                            disabled: true,
                            chosen: tradePlat
                        });
                        break;
                    case TRADE_PLACE_02 + SHARES_TRADE_MARKET_02:
                        //如果是交易场所+交易市场 == 上海证券交易所+二级市场;
                        $tradePlat.updateChosenArr({
                            item: ['02', '03', '04'],
                            chosen: tradePlat
                        });
                        break;
                    case TRADE_PLACE_03 + SHARES_TRADE_MARKET_01:
                        //如果是交易场所+交易市场 == 深圳证券交易所+一级市场;
                        $tradePlat.updateChosenArr({
                            disabled: true,
                            chosen: tradePlat
                        });
                        break;
                    case TRADE_PLACE_03 + SHARES_TRADE_MARKET_02:
                        //如果是交易场所+交易市场 == 深圳证券交易所+二级市场;
                        $tradePlat.updateChosenArr({
                            item: ['02', '05'],
                            chosen: tradePlat
                        });
                        break;
                    default:
                        if (isInit || pageModel == PAGE_MODEL_REFERENCE || pageModel == PAGE_MODEL_EDIT) {
                            break;
                        }
                        if ($("#entrustId").val() != '') {
                            $('#tradePlat').prop("disabled", true);
                        }
                        break;
                }
            }
            else if (pageModel != PAGE_MODEL_REFERENCE && !initFlag) {
                var key = tradeMarket + tradeMarketMode;
                switch (key) {
                    //如果是交易场所+交易市场 == 银行间+一级市场，就可以选择01和99
                    case TRADE_PLACE_01 + SHARES_TRADE_MARKET_01:
                        $tradePlat.updateChosenArr({
                            item: [SHARES_TRADE_PLATFORM_01, SHARES_TRADE_PLATFORM_99],
                            chosen: SHARES_TRADE_PLATFORM_01,
                            disabled: false
                        });
                        break;
                    case TRADE_PLACE_01 + SHARES_TRADE_MARKET_02:
                        //如果是交易场所+交易市场 == 银行间+二级市场，只能选择01且置灰
                        $tradePlat.updateChosenArr({
                            item: [SHARES_TRADE_PLATFORM_01],
                            chosen: SHARES_TRADE_PLATFORM_01,
                            disabled: true
                        });
                        break;
                    case TRADE_PLACE_02 + SHARES_TRADE_MARKET_01:
                        //如果是交易场所+交易市场 == 上海证券交易所+一级市场;
                        $tradePlat.updateChosenArr({
                            disabled: true
                        });
                        break;
                    case TRADE_PLACE_02 + SHARES_TRADE_MARKET_02:
                        //如果是交易场所+交易市场 == 上海证券交易所+二级市场;
                        $tradePlat.updateChosenArr({
                            item: ['02', '03', '04'],
                            disabled: false
                        });
                        break;
                    case TRADE_PLACE_03 + SHARES_TRADE_MARKET_01:
                        //如果是交易场所+交易市场 == 深圳证券交易所+一级市场;
                        $tradePlat.updateChosenArr({
                            disabled: true
                        });
                        break;
                    case TRADE_PLACE_03 + SHARES_TRADE_MARKET_02:
                        //如果是交易场所+交易市场 == 深圳证券交易所+二级市场;
                        $tradePlat.updateChosenArr({
                            item: ['02', '05'],
                            disabled: false
                        });
                        break;
                    default:
                        if (isInit || pageModel == PAGE_MODEL_REFERENCE || pageModel == PAGE_MODEL_EDIT) {
                            break;
                        }
                        if ($("#entrustId").val() != '') {
                            $('#tradePlat').prop("disabled", true);
                        } else {
                            $tradePlat.updateChosenArr({
                                disabled: false
                            });
                        }
                        break;
                }
            }
        });
        //是否展示资产四五级分类
        if (IS_SHOW_TYPE == '01') {
            $('#assetType11Div').show();
        } else {
            $('#assetType11Div').hide();
        }
    });
}());
