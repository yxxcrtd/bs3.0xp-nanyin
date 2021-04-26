(function () {
    /**
     * 页面初始化
     */
	var acctNoList;
    var calcConditionObjBackUp;
    /**
     * 定义数据表格
     */
    var tableInitData = function () {
        var object = $.serializeToObject('#informationForm');
        var dtos = [];
        if(object.fixMdate){
            var dealFix = {};
            dealFix.finprodType2=finprodType2;
            if(object.irsType == irsType_payFloat){
                dealFix.ps=ps_rec;
            } else {
                dealFix.ps=ps_pay;
            }
            dealFix.tradeDate=object.fixActPayDate;
            dealFix.tradeAmt=object.fixIntAmt;
            dealFix.ccy=object.ccy;
            dealFix.counterId=object.counterId;
            dealFix.portfolioId=object.portfolioId;
            dtos.push(dealFix);
        }
        if(object.floatMdate){
            var dealFloat = {};
            dealFloat.finprodType2=finprodType2;
            if(object.irsType == irsType_payFloat){
                dealFloat.ps=ps_pay;
            } else {
                dealFloat.ps=ps_rec;
            }
            dealFloat.tradeDate=object.floatActPayDate;
            dealFloat.tradeAmt=object.floatIntAmt;
            dealFloat.ccy=object.ccy;
            dealFloat.counterId=object.counterId;
            dealFloat.portfolioId=object.portfolioId;
            dtos.push(dealFloat);
        }
        return {
            'isTotalTrade': YES_OR_NO_N
            , 'bankLendingType': null
            , 'portfolioId': object.portfolioId
            , 'invAim' : null
            , 'deal':dtos
        };
    }
    
    /**
     * 页面初始化
     */
    var init = function () {
        //add by jiangpeng on 20210422 start TISHKBS-1803
        $("#fixActPayDate").change(function () {
            isTodoGenerateOrders();
        });
        $("#floatActPayDate").change(function () {
            isTodoGenerateOrders();
        });
        //add by jiangpeng on 20210422 end TISHKBS-1803
    }
    //add by jiangpeng on 20210422 start TISHKBS-1803
    /**
     * 总集复核时修改实际支付日,指令随着变更
     */
    function isTodoGenerateOrders() {
        if (isTodo) {
            let irsType = $("#irsType").val();
            let param = {};//判断条件
            let dateMap = {};//日期参数
            param.isOnlyOneDate = true;
            param.ps_pay = '02';
            param.irsType = irsType === irsType_payFloat;
            param.isTrdIRS = true;
            dateMap.floatMdate = $("#floatActPayDate").val();
            dateMap.fixMdate = $("#fixActPayDate").val();
            window.BussTrdTransferOrder['bussTrdTransferOrder'].updateGenerateOrders(dateMap,param);
        }
    }
    //add by jiangpeng on 20210422 end TISHKBS-1803

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

    /**
     * 保存或提交
     */
    function realSaveOrSubmit(operateType) {
        if (window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()) {
            return;
        }
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#informationForm', true);
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + 'invest/period/trdirsduration/TrdIRSDuration002/check', object, function (result) {
            Joyin.confirm(result.data, function () {
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/period/trdirsduration/TrdIRSDuration002/save', object, "TrdIRSDuration002_btn_save", "TrdIRSDuration002", function () {
                        if (parent.TrdIRSDuration001) {
                            parent.TrdIRSDuration001.reloadTable();
                        }
                    });
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/period/trdirsduration/TrdIRSDuration002/save', object, "TrdIRSDuration002_btn_submit", "TrdIRSDuration002", function () {
                        if (parent.TrdIRSDuration001) {
                            parent.TrdIRSDuration001.reloadTable();
                        }
                    });
                }
            });
        });
    }

    /**
     * 指令生成
     */
    $('#TrdFxswapInvestTrade002_generate').click (function(){
        $('#bussTrdTransferOrder_btn_generate').trigger('click');
    });

    window.componentLoadCallback = function (isDone, pagePath, params) {
        if (isDone) {
            if($("#fixMdate").val()){
                $(".gdll").show();
            } else {
                $(".gdll").hide();
            }
            if($("#floatMdate").val()){
                $(".fdlx").show();
            } else {
                $(".fdlx").hide();
            }
            window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                if ($("#fixMdate").val() && !$("#fixIntAmt").val()) {
                    Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("TrdIRSDuration002.fixIntAmt")))
                    return;
                }
                if ($("#fixMdate").val() && !$("#fixActPayDate").val()) {
                    Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("TrdIRSDuration002.fixActPayDate")))
                    return;
                }
                if ($("#floatMdate").val() && !$("#floatIntAmt").val()) {
                    Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("TrdIRSDuration002.floatIntAmt")))
                    return;
                }
                if ($("#floatMdate").val() && !$("#floatActPayDate").val()) {
                    Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("TrdIRSDuration002.floatActPayDate")))
                    return;
                }
                return tableInitData();
            }, true);
            if (formTodoList) {
                window.BussTrdTransferOrder['bussTrdTransferOrder'].saveCalcCondition();
            }
        }
    }

    /**
     * 画面加载完成
     */
    $(function () {
        init();
        $('#TrdIRSDuration002_btn_save').click(function () {
            saveOrSubmit('save');
        });
        $('#TrdIRSDuration002_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
    });
}());

