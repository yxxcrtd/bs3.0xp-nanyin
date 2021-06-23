/**
 * 行情和交易信息
 */
(function(){

    //试算结果
    var riskResultdata;
    var cluflag = true;
    //默认新增指令
    var pageModel = PAGE_MODEL_NEW;

    /**
     * 返回表单数据
     */
    function getSearchParams() {
        var object = $.serializeToObject('#sumbitForm');

        // 投资人员信息
        object.etrInvestPersonInfo = {
            invManagerId:  $('#invManagerId').val(),
            room: $('#room').val(),
            counterId: $('#counterId').val(),
        };

        object.finprodId = $('#finprodIdhid').val();
        object.pageModel = pageModel;
        object.entrustId = $("#entrustId").val();
        object.entrustStatus = $("#entrustStatus").val()
        return object;
    }

    /**
     * 获取风险试算数据
     */
    var getriskdata = function () {
        var object = $.serializeToObject('#sumbitForm');
        var entrustAmt = $('#entrustAmt').val();
        var priceModel = $('#priceModel').val();
        var entrustNum = $('#entrustNum').val();
        var finprodId = $('#finprodIdhid').val();
        var param = {
            'finprodId':finprodId,
            'valDate':iPlatformDate,
        }

        if(priceModel==PRICE_MODEL_PRM03 && !entrustAmt && entrustNum){
            Joyin.ajaxSync(basePath + 'invest/entrust/etrstockconnect/EtrStockConnect002/getHkseClosePrice',param,function (ret) {
                if(ret.data){
                    entrustAmt = JoyinMath.doMul(ret.data,$('#entrustNum').val());
                }
            })
        }

        var obj = {}
        obj = {
            tradeDirection: object.ps,
            timePoint: MY_SUP_TIME_TYPE_02,
            valDate: iPlatformDate,
            portfolioCode: object.portfolioId,
            tradeAmount: entrustAmt,
            assetType: object.finprodType2,
            isEnabled: '01',
            assetCode: object.finprodId
        }
        var riskdata = {
            tradeDirection: ['ps', 'EtrStockConnect002.ps'],
            portfolioCode: ['portfolioId', 'EtrStockConnect002.portfolioId'],
            tradeAmount: ['entrustNum','EtrStockConnect002.entrustAmt'],
            assetType: ['assetType1', 'EtrStockConnect002.finprodType2'],
            counterId: ['counterId', 'EtrStockConnect002.counterId'],
            data: obj
        }
        return riskdata;
    }

    /**
     * 风险试算URL
     * @returns
     */
    var getRiskInterfUrl = function () {
        var riskInterfUrl;
        riskInterfUrl = basePath +  'riskrds/interf/doInfData/0/RSK002/F11';
        return riskInterfUrl;
    }

    /**
     * 指令提交或者保存
     */
    function saveOrSubmit(operateType) {
        var s;
        var object =getSearchParams();

        object.operateType = operateType;
        var riskdata = getriskdata();
        if(BaseRiskMathComm) {
            s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
            if(!s && s != ''){
                return;
            }
            if (window.BaseRiskMathComm.isCalcConditionChanged(riskdata)){
                return;
            }
        }

        Joyin.ajaxJson(basePath + '/invest/entrust/etrstockconnect/EtrStockConnect002/check',object,
            function(result) {
                Joyin.confirm(result.data,function() {
                    if (object.operateType === 'save') {
                        WorkFlow.doSaveList(basePath+'/invest/entrust/etrstockconnect/EtrStockConnect002/save',object,"EtrStockConnect002_btn_save",'EtrStockConnect002',function() {
                            doReset(true);
                            if(BaseRiskMathComm) {
                                BaseRiskMathComm.resetRiskMathFlag();
                            }
                            JoyinUtil.disableButton("EtrStockConnect002_btn_save", false);
                        })
                    }else {
                        WorkFlow.doSubmitList(basePath+'/invest/entrust/etrstockconnect/EtrStockConnect002/save',object,"EtrStockConnect002_btn_submit",'EtrStockConnect002',function() {
                            doReset(true);
                            //提交后风险试算标识置为01
                            if(BaseRiskMathComm) {
                                BaseRiskMathComm.resetRiskMathFlag();
                            }
                            JoyinUtil.disableButton("EtrStockConnect002_btn_submit", false);
                        })
                    }
                });
            });
    }

    /**
     * 风险试算
     */
    function doRiskcal() {
        var object = getSearchParams();
        if(object) {
            var riskdata = getriskdata();
            var riskInterfUrl = getRiskInterfUrl();
            if(riskdata && riskInterfUrl) {
                window.BaseRiskMathComm.initBaseRiskMathRedis(riskInterfUrl, object,riskdata,riskMathRedisCallback);
            }
        }

    }
    /**
     * 风险试算的回调函数
     */
    function riskMathRedisCallback(object,cluflag) {
        EtrStockConnect002.riskResultdata = object;
        EtrStockConnect002.cluflag = cluflag;
        Joyin.dialog(JoyinUtil.getLabel('EtrStockConnectRiskcal'), '/invest/entrust/etrstockconnect/EtrStockConnectRiskcal/init', '60%', '75%',null);
    }

    /**
     * 取消,清空指令数据
     */
    var doReset = function(opt) {
        $("#entrustId").val('').trigger('change');
        $("#entrustStatus").val('').trigger('change');
        // $("#parentPortfolioId").val('').trigger('change');
        $("#portfolioId").val('').trigger('change');
        $("#finprodId").val('').trigger('change');
        $("#hkseMarket").val('').trigger('change');
        $("#ps").val('').trigger('change');
        $("#targetType").val('').trigger('change');
        $("#priceModel").val('').trigger('change');
        $("#price").val('').trigger('change');
        $("#entrustNum").val('').trigger('change');
        $("#entrustAmt").val('').trigger('change');
        $("#entrustAmtHk").val('').trigger('change');
        $("#referenceRate").val('').trigger('change');
        $("#saleCode").val('').trigger('change');
        $('#secAcctId').val('').trigger('change');

        //设置指令记录操作类型
        pageModel = PAGE_MODEL_NEW;

        //释放修改时的锁定
        // $('#parentPortfolioId').prop("disabled", false);
        $('#portfolioId').prop("disabled", false);
        $('#finprodId').prop("disabled", false);
        $('#hkseMarket').prop("disabled", false);
        $('#priceModel').prop("disabled", false);
        $('#ps').prop("disabled", false);
        $('#price').prop("disabled", false);

        //清空风险试算结果
        riskResultdata = null;
        cluflag = true;
    }

    /**
     * 双击指令管理
     */
    var dbclickRefresh001 = function(data){
        setEntrust001PageItems(data);
        //双击校验后，将风险试算标识重置
        //window.parent.dbclickRefreshRisk();
    }

    /**
     * 根据传入的数据，设置画面元素
     */
    var setEntrust001PageItems = function(param) {
        setPageItemsFlag = true;
        if (param) {
            if (param.entrustId) {
                $("#entrustId").val(param.entrustId).trigger("change");//委托代码
                $("#businessId").val(param.entrustId).trigger("change");//业务代码
            }
            if (param.entrustStatus) {
                $("#entrustStatus").val(param.entrustStatus).trigger("change");//委托状态
            }
            if (param.portfolioId) {
                // $("#parentPortfolioId").textSelect('setValue',param.parentPortfolioId);//组合代码
                $("#portfolioId").textSelect('setValue',param.portfolioId);//组合代码
            }
            if (param.finprodMarketId) {
                $("#finprodId").textSelect('setValue',param.finprodMarketId);//资产代码
            }
            if (param.hkseMarket) {
                $("#hkseMarket").textSelect('setValue',param.hkseMarket);//港股通市场
            }
            if (param.secAcctId) {
                $("#secAcctId").val(param.secAcctId).trigger("change");//证券账户
            }
            if (param.targetType) {
                $("#targetType").textSelect('setValue',param.targetType);//目标类型
            }
            if (param.priceModel) {
                $("#priceModel").val(param.priceModel).trigger("change");//价格模式
            }
            if (param.ps) {
                $("#ps").textSelect('setValue',param.ps);//交易方向
            }
            if (param.price) {
                $("#price").val(param.price).trigger("change");//指令价格
            }
            if (param.entrustNum) {
                $("#entrustNum").val(param.entrustNum).trigger("change");//指令数量
            }
            if (param.entrustAmtHk) {
                $("#entrustAmtHk").val(param.entrustAmtHk).trigger("change");//券面总额
            }
            if (param.entrustAmt) {
                $("#entrustAmt").val(param.entrustAmt).trigger("change");//券面总额
            }
            pageModel = PAGE_MODEL_EDIT;

            //修改时锁定
            // $('#parentPortfolioId').prop("disabled", true);
            $('#portfolioId').prop("disabled", true);
            $('#finprodId').prop("disabled", true);
            $('#hkseMarket').prop("disabled", true);
            $('#priceModel').prop("disabled", true);
            $('#ps').prop("disabled", true);
            if(param.priceModel && param.priceModel === PRICE_MODEL_PRM02 ){
                $('#price').prop("disabled", true);
            }
        }
    }

    /**
     * 双击持仓自动填充表单
     */
    var dbclickRefresh002 = function (data) {
        setEntrust002PageItems(data);
        //双击校验后，将风险试算标识重置
        //window.parent.dbclickRefreshRisk();
    };

    /**
     * 根据传入的数据，设置画面元素
     */
    var setEntrust002PageItems = function (param) {
        setPageItemsFlag = true;
        $("#ps").textSelect('setValue',PS_T02);
        $("#priceModel").textSelect('setValue',PRICE_MODEL_PRM02);
        $("#targetType").textSelect('setValue',TARGET_CLASS_01);
        if (param) {
            // if (param.parentPortfolioId) {
            //     $("#parentPortfolioId").textSelect('setValue',param.parentPortfolioId);
            // }
            if (param.portfolioId) {
                $("#portfolioId").textSelect('setValue',param.portfolioId);
            }
            if (param.finprodMarketId) {
                $("#finprodId").textSelect('setValue',param.finprodMarketId);
            }
            if (param.hkseMarket) {
                var data = {
                    finprodId: $('#finprodIdhid').val(),
                };
                Joyin.ajaxJson(basePath + 'invest/entrust/etrstockconnect/EtrStockConnect002/getFinProdInfo', data,function (result) {
                    if (result && result.data) {
                        var data = result.data;
                        if(data.hkseMarket && data.hkseMarket===HKSE_MARKET_04){
                            $('#hkseMarket').textSelect('setValue',param.hkseMarket);
                        }
                    }
                });
            }
            if (param.price) {
                $("#price").val(param.price).trigger("change");
            }
            if (param.sellNum) {
                $("#entrustNum").val(param.sellNum).trigger("change");
            }
        }
        pageModel = PAGE_MODEL_NEW;
    };

    //查询组合可用现金
    function getPtlCashPositionInfo() {
        var portfolioId = $('#portfolioId').val();
        var acctNo = $("#acctNo").val();
        var ccy = $("#ccy").val();
        var hkseMarket = $("#hkseMarket").val();
        if(!portfolioId || !ccy || !acctNo || !tradeMarket){
           return;
        }

        var data = {
            portfolioId: portfolioId,
            ccy: ccy,
            acctId:acctNo
        };
        Joyin.ajaxJson(basePath + 'invest/entrust/etrstockconnect/EtrStockConnect002/getProAvailableCash', data,function (result) {
            if(result){
                if(tradeMarket == HKSE_MARKET_01){
                    setCashPosition(result.cashSH,'cash');
                    $("#proAvailableCash").val(result.cashSH);
                }else{
                    setCashPosition(result.cashSZ,'cash');
                    $("#proAvailableCash").val(result.cashSZ);
                }
            }else{
                setCashOrNum('cash');
                $("#proAvailableCash").val(Number(0));
            }
        });
    }
    //查询组合可用持仓(可卖数量)
    function selectAvailableNum() {

        var finprodId = $('#finprodIdhid').val();
        var portfolioId = $("#portfolioId").val();
        var secAcctId = $("#secAcctId").val();
        var ccy = $("#ccy").val();
        if (!portfolioId || !ccy || !finprodId || !secAcctId){
            return
        }

        var data = {
            portfolioId: portfolioId,
            ccy: ccy,
            finprodId: finprodId,
            secAcctId:secAcctId
        }
        Joyin.ajaxJson(basePath + 'invest/entrust/etrstockconnect/EtrStockConnect002/selectAvailableNum', data,function (result) {
            if(result){
                setCashPosition(result,'num');
                $("#assetAvailablePosition").val(result);
            }else{
                setCashOrNum('num');
                $("#assetAvailablePosition").val(Number(0));
            }
        });
    }
    /**
     * 持仓数量
     */
    var setPositionNum = function() {
        var portfolioId = $("#portfolioId").val();
        var finprodMarketId = $('#finprodId').val();
        var secAcctId = $("#secAcctId").val();
        // 如果金融产品类型不是“债券”，则可以不需要投资目的
        if(portfolioId && finprodMarketId && secAcctId) {
            var param = {
                portfolioId: portfolioId,
                finprodId: finprodMarketId,
                secAcctId:secAcctId,
            };
            Joyin.ajaxJson(basePath + 'invest/entrust/etrstockconnect/EtrStockConnect001/findPositionsPage2', param, function(result) {
                if(result.root.length != 0){
                    setCashPosition(result.root[0].positionsNum,'pos');
                }else{
                    setCashOrNum('pos');
                }
            }, false);
        }
        else {
            setCashOrNum('pos');
        }

    };

    /**
     * 新建page设置持仓，现金信息
     */
    var setCashPosition = function(result,type) {
        if('cash'==type) {
            $("#accAvailableCash").text(formatMoney(result));
        }else if('num'==type) {
            $("#availableNum").text(formatMoney(result));
        }else if('pos'==type) {
            $("#positionNum").text(formatMoney(result));
        }
    }
    function setCashOrNum(type) {
        if('cash'==type) {
            $("#accAvailableCash").text('0.00');
        } else if('num'==type) {
            $("#availableNum").text('0.00');
        }else if('pos'==type) {
            $("#positionNum").text('0.00');
        }
    }
    var formatMoney = function (s, n)
    {
        //区分正负数，且整数位%3==0的负数  显示异常
        var flag="";
        n = n > 0 && n <= 20 ? n : 2;
        var s = parseFloat((s + '').replace(/[^\d\.-]/g, '')).toFixed(n) + '';
        if(s.indexOf("-")>=0){
            flag = "-";
            s = s.substring(1,s.length);
        }
        var l = s.split('.') [0].split('').reverse(),
            r = s.split('.') [1];
        var t = '';
        for (var i = 0; i < l.length; i++)
        {
            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? ',' : '');
        }
        return (flag+t.split('').reverse().join('') + '.' + r).trim();

    };

    /**
     * 格式化指令价格
     */
    var formatPrice = function($price,$price2) {
        var $secIdObj = $("#secIdObjRelative").val();
        if ($secIdObj) {
            var $obj = JSON.parse($secIdObj);
            $.extend($obj, {
                price: $price
            });
            var precision = getMinPrice($obj).scale;
            if (precision >= 0) {
                setScale($obj);
                if($price2){
                    return (Math.round($price2/$obj.minPrice)*$obj.minPrice).toFixed(precision);
                }
                else
                    return (Math.round($price/$obj.minPrice)*$obj.minPrice).toFixed(precision);
            }
        }
        return $price;
    }

    //计算数量或金额
    var calNumAndAmt = function (calFlag) {
        var priceModel = $('#priceModel').val();
        var price = $('#price').val();
        var ps = $('#ps').val();
        var closePrice = $('#closePrice').val();

        if (!calFlag || !priceModel) {
            return;
        }

        var object = $.serializeToObject('#sumbitForm');
        object.calFlag = calFlag;
        if(ps){
            if(ps === PS_T01 || ps === PS_T46 || ps === PS_T47){
                object.referenceRate = $('#referenceRateOut').val();
            }else {
                object.referenceRate = $('#referenceRateIn').val();
            }
        }else{
            object.referenceRate = null;
        }

        if(!object.finprodId) {
            object.finprodId = $("#finprodIdhid").val();
        }

        Joyin.ajaxJson(basePath + 'invest/entrust/etrstockconnect/EtrStockConnect002/calNumAndAmt', object,
            function (result) {
                if (result && result.data) {
                    var data = result.data;
                    if(calFlag === 'entrustAmtHk'){
                        if (data.entrustNum) {
                            $('#entrustNum').val(data.entrustNum).trigger("blur");
                        } else {
                            $('#entrustNum').val('');
                        }
                        if (data.entrustAmtHk) {
                            $('#entrustAmtHk').val(data.entrustAmtHk).trigger("blur");
                        } else {
                            $('#entrustAmtHk').val('');
                        }
                        if (data.entrustAmt) {
                            $('#entrustAmt').val(data.entrustAmt).trigger("blur");
                        } else {
                            $('#entrustAmt').val('');
                        }
                    }
                    else if (calFlag === 'entrustNum'){
                        if (data.entrustNum) {
                            $('#entrustNum').val(data.entrustNum).trigger("blur");
                        } else {
                            $('#entrustNum').val('');
                        }
                        if (data.entrustAmtHk) {
                            $('#entrustAmtHk').val(data.entrustAmtHk).trigger("blur");
                        } else {
                            $('#entrustAmtHk').val('');
                        }
                        if (data.entrustAmt) {
                            $('#entrustAmt').val(data.entrustAmt).trigger("blur");
                        } else {
                            $('#entrustAmt').val('');
                        }
                    }
                }
            });
    }

    //查询申报代码
    function getSaleCode() {
        $('#saleCode').val('').trigger('blur');
        if(!$('#ps').val()){
            return;
            if($('#ps').val()!= PS_T46 && $('#ps').val()!= PS_T47){
                return;
            }
        }

        if(!$('#finprodIdhid').val()){
            return;
        }
        var data = {
            finprodId: $('#finprodIdhid').val(),
        };
        Joyin.ajaxJson(basePath + 'invest/entrust/etrstockconnect/EtrStockConnect002/getSaleCode', data,function (result) {
            if (result && result.data) {
                $('#saleCode').val(result.data).trigger('blur');
            }
        });
    }

    //自动带出港股通市场信息
    function getMarketByFinProdId() {
        if(!$('#finprodIdhid').val()){
            return;
        }
        var data = {
            finprodId: $('#finprodIdhid').val(),
        };
        Joyin.ajaxJson(basePath + 'invest/entrust/etrstockconnect/EtrStockConnect002/getFinProdInfo', data,function (result) {
            if (result && result.data) {
                var data = result.data;
                if(data.hkseMarket && (data.hkseMarket===HKSE_MARKET_01 || data.hkseMarket===HKSE_MARKET_02)){
                    $('#hkseMarket').val(data.hkseMarket).prop("disabled", true).trigger('change');
                }else if(data.hkseMarket && data.hkseMarket===HKSE_MARKET_04){
                    $('#hkseMarket').val('').prop("disabled", false).trigger('change');
                }else{
                    $('#hkseMarket').val('').prop("disabled", true).trigger('change');
                }
            }
        });
    }

    //查询参考汇率
    function getReferenceRate() {
        var hkseMarket = $('#hkseMarket').val();
        if(!hkseMarket || !iPlatformDate){
            return;
        }
        var data = {
            hkseMarket: hkseMarket,
            vdate: iPlatformDate,
        };
        Joyin.ajaxJson(basePath + 'invest/entrust/etrstockconnect/EtrStockConnect002/getReferenceRate', data,function (result) {
            if (result && result.data) {
                var data = result.data;
                if(data.referenceRateIn && data.referenceRateOut){
                    $('#referenceRateIn').val(data.referenceRateIn).trigger('change');
                    $('#referenceRateOut').val(data.referenceRateOut).trigger('change');
                    $('#referenceRateStr').val(parseFloat(data.referenceRateIn).toFixed(4) + "买/ "+parseFloat(data.referenceRateOut).toFixed(4) + "卖");
                    if (todoList == false) {
                        parent.EtrStockConnectHq.setReferenceRate(data);
                    }

                }else{
                    $('#referenceRateIn').val('');
                    $('#referenceRateOut').val('');
                    $('#referenceRateStr').val('');
                    if (todoList == false) {
                        parent.EtrStockConnectHq.setCashOrNum();
                    }
                }
            }
        });
    }

    /**
     * 根据参数获取资金账户下拉框的值
     * portfolioId:组合代码
     * acctNo:资金账户
     */
    var getAcctNoSelectList = function(portfolioId, acctNo) {
        var result = null;
        var param = {portfolioId:portfolioId, acctNo:acctNo};
        var hkseMarket = $("#hkseMarket").val();
        if(hkseMarket){
            if(hkseMarket == HKSE_MARKET_01){
                param.tradeMarket = TRADE_PLACE_03;   //沪港通
            }else if(hkseMarket == HKSE_MARKET_02){
                param.tradeMarket = TRADE_PLACE_04;
            }
        }

        Joyin.ajaxSync(basePath + 'invest/entrust/etrexchangetrade/EtrExchangeTrade001/getPtlSecAcctInfo', param, function(list) {
            result = list;
        });
        return result;
    };

    /**
     * 联动资金账户下拉框。
     */
    var linkAcctNo = function(init) {
        var portfolioId = $("#portfolioId").val();
        if(portfolioId) {
            var list = getAcctNoSelectList(portfolioId, null);
            var acctNo = '';
            if(list && list.length == 1) {
                var acctNo = list[0].id;
                $("#acctNo").val(acctNo).trigger('change');
            }
            else {
                $("#acctNo").val('').trigger('change');
            }
        }
        else {
            $("#acctNo").val('').trigger('change');
        }
    };

    //查询证券账户
    function getSecAcctInfo() {
        $('#acctId').val('');
        var hkseMarket = $('#hkseMarket').val();
        var portfolioId = $('#portfolioId').val();

        if(!hkseMarket || !portfolioId){
            return;
        }
        var data = {
            hkseMarket: hkseMarket,
            portfolioId: portfolioId,
        };
        Joyin.ajaxJson(basePath + 'invest/entrust/etrstockconnect/EtrStockConnect002/getSecManageAcctId', data,function (result) {
            if (result) {
                if(result.secAcctId){
                    $('#secAcctId').val(result.secAcctId).trigger('change');
                }
            }
        });
    }

    /**
     * 左下行情信息联动
     */
    var getLeftMarketInfo = function () {
        var finprodMarketId = $("#finprodId").val();
        var hkseMarket = $("#hkseMarket").val();
        if (finprodId /*&& hkseMarket*/ && todoList == false) {
            parent.EtrStockConnectHq.queryStockInfo(finprodMarketId,hkseMarket);
        }
    }

    /**
     * 画星星
     */
    var star = function() {
        // 加红星星
        // 查找label
        var _findPrevLabel = function(element, level) {
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
            $('.must').each(function() {
                $(this).remove();
            })
            $('*[required]').each(function() {
                var $el = $(this);
                var $target = _findPrevLabel($el, 3);
                if ($target.find('> .must').length === 0) {
                    $target.append($('<i class="must">*</i>'));
                }
            });
        }
    }

    /**
     *联动带出，指令价格
     **/
    function setQuotaPrice(quotaPrice) {
        if($("#priceModel").val() != PRICE_MODEL_PRM03){
            $("#price").val(quotaPrice).trigger('blur').trigger('change');
        }
    }

    /**
     * 初始化
     */
    function init(){
        //配售代码
        var ps = $('#ps').val();
        if(ps === PS_T46 || ps === PS_T47){
            $("#saleCode_dev").show();
        }else{
            $("#saleCode_dev").hide();
        }
        //初始化时查询
        getPtlCashPositionInfo();
        selectAvailableNum();

        // 非代办过来不显示
        if (todoList == false) {
            $('#EtrStockConnect002_btn_riskcal').css('display', 'none');
        }else{
            getReferenceRate();
        }
    }

    /**
     * 对外公开的接口
     */
    parent.EtrStockConnect002 = {
        getSearchParams:getSearchParams,
        getriskdata:getriskdata,
        getRiskInterfUrl:getRiskInterfUrl,
        dbclickRefresh001:dbclickRefresh001,
        dbclickRefresh002:dbclickRefresh002,
        doReset: doReset,
        formatPrice: formatPrice,
        setQuotaPrice:setQuotaPrice,
        riskResultdata:riskResultdata,
        cluflag:cluflag,
    }

    EtrStockConnect002 = {
        getSearchParams: getSearchParams,
        getriskdata: getriskdata,

        riskResultdata: riskResultdata,
        cluflag: cluflag
    }


    $(function() {
        init();

        // 绑定提交按钮
        $('#EtrStockConnect002_btn_submit').click(function () {
            saveOrSubmit('submit');
        });

        // 绑定保存按钮
        $('#EtrStockConnect002_btn_save').click(function () {
            saveOrSubmit('save');
        });

        // 绑定取消按钮
        $('#EtrStockConnect002_btn_reset').click(function () {
            doReset();
        });

        // 绑定风险按钮
        $('#EtrStockConnect002_btn_riskcal').click(function () {
            doRiskcal();
        });
        

        // 组合名称
        $("#portfolioId").change(function() {
            getPtlCashPositionInfo();
            selectAvailableNum();
            setPositionNum();
            getSecAcctInfo();//证券账户
            linkAcctNo();//资金账户

        });
        // 资产代码
        $("#finprodId").change(function() {
            var finprodMarketId = $('#finprodId').val();
            if (finprodMarketId) {
                var data = $('#finprodId').select2('data')[0].object;
                $('#finprodIdhid').val(data.finprodId).trigger('change');
            }else{
                $('#finprodIdhid').val('').trigger('change');
            }
            getMarketByFinProdId();//资产自动带出港股通市场
            getLeftMarketInfo();  //行情信息
            selectAvailableNum(); //可卖
            setPositionNum();
            getSaleCode();//查询配售代码

        });
        // 港股通市场
        $("#hkseMarket").change(function() {
            getReferenceRate();  //获取参考汇率
            getSecAcctInfo();//证券账户
            linkAcctNo();//资金账户
            getPtlCashPositionInfo();//港股通可用现金

        });
        // 证券账户
        $("#secAcctId").change(function() {
            getLeftMarketInfo();  //行情信息
            setPositionNum(); //持仓
            selectAvailableNum(); //可卖

        });
        // 资金账户
        $("#acctNo").change(function() {
            getPtlCashPositionInfo(); //组合可用现金
        });
        // 委托方向
        $("#ps").change(function() {
            var ps = $('#ps').val();
            var targetType = $("#targetType").val();
            if(ps === PS_T46 || ps === PS_T47){
                $("#saleCode_dev").show();
                $('#priceModel').val(PRICE_MODEL_PRM01).trigger('change').prop("disabled", true);
            }else{
                $("#saleCode_dev").hide();
                $('#priceModel').val('').trigger('change').prop("disabled", false);
            }
            //买入、卖出汇率使用切换
            if(targetType && targetType === TARGET_CLASS_01){
                calNumAndAmt('entrustNum');
            }else{
                calNumAndAmt('entrustAmtHk');
            }
            getSaleCode();//查询配售代码

        });
        // 价格模式
        $("#priceModel").change(function() {
            var priceModel = $("#priceModel").val();
            var targetType = $("#targetType").val();
            if(priceModel) {
                if(priceModel == PRICE_MODEL_PRM03){
                    $('#price').val('').prop("disabled", true).removeAttr("required");
                    if(targetType && targetType == TARGET_CLASS_01) {
                        $('#entrustAmtHk').val('').prop("disabled", true);
                    }else{
                        $('#entrustNum').val('').prop("disabled", true);
                    }
                }else{
                    $('#price').prop("disabled", false).attr("required",true);
                }
                //切换
                if(targetType && targetType === TARGET_CLASS_01){
                    calNumAndAmt('entrustNum');
                }else{
                    calNumAndAmt('entrustAmtHk');
                }
            }
            star();
        });
        // 目标类型
        $("#targetType").change(function() {
            var targetType = $("#targetType").val();
            var priceModel = $("#priceModel").val();
            if(targetType){
                if(targetType == TARGET_CLASS_01) {
                    $("#entrustNum").prop("disabled", false).attr("required",true);
                    $("#entrustAmtHk").val('').prop("disabled", true);
                    calNumAndAmt('entrustNum');
                }
                else {
                    $("#entrustAmtHk").prop("disabled",  false).attr("required",true);
                    $("#entrustNum").val('').prop("disabled", true);
                    calNumAndAmt('entrustAmtHk');
                }
            }
            star();
        });
        // 委托价格
        $("#price").change(function() {
            var price = $("#price").val();
            var entrustNum = $("#entrustNum").val();
            var entrustAmtHk = $("#entrustAmtHk").val();
            var targetType = $("#targetType").val();
            if(price){
                if(targetType && targetType === TARGET_CLASS_01){
                    calNumAndAmt('entrustNum');
                }else{
                    calNumAndAmt('entrustAmtHk');
                }
            }

        });
        // 委托数量
        $("#entrustNum").change(function() {
            var entrustNum = $("#entrustNum").val();
            if (entrustNum) {
                calNumAndAmt('entrustNum');
            }

        });
        // 委托金额
        $("#entrustAmtHk").change(function() {
            var entrustAmtHk = $("#entrustAmtHk").val();
            if (entrustAmtHk) {
                calNumAndAmt('entrustAmtHk');
            }

        });
        // 参考汇率
        $("#referenceRateIn, referenceRateOut").change(function() {
            var targetType = $("#targetType").val();
            if(targetType){
                if(targetType == TARGET_CLASS_01) {
                    calNumAndAmt('entrustNum');
                }
                else {
                    calNumAndAmt('entrustAmtHk');
                }
            }
        });

    });
}());