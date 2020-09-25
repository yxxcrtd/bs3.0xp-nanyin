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
        var deal = {};
        deal.finprodType2=finprodType2;
        deal.ps=object.beforeEndPs;
        deal.tradeDate=object.beforeEndDate;
        deal.tradeAmt=object.beforeEndAmt;
        deal.ccy=object.ccy;
        deal.counterId=object.counterId;
        deal.portfolioId=object.portfolioId;
        return {
            'isTotalTrade': YES_OR_NO_N
            , 'bankLendingType': null
            , 'portfolioId': object.portfolioId
            , 'invAim' : null
            , 'deal':deal
        };
    }
    
    /**
     * 页面初始化
     */
    var init = function () {

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
        Joyin.ajaxJson(basePath + 'invest/period/trdirsduration/TrdIRSDuration003/check', object, function (result) {
            Joyin.confirm(result.data, function () {
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/period/trdirsduration/TrdIRSDuration003/save', object, "TrdIRSDuration003_btn_save", "TrdIRSDuration003", function () {
                        if (parent.TrdIRSDuration001) {
                            parent.TrdIRSDuration001.reloadTable();
                        }
                    });
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/period/trdirsduration/TrdIRSDuration003/save', object, "TrdIRSDuration003_btn_submit", "TrdIRSDuration003", function () {
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
            window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                if (!$("#beforeEndDate").val()) {
                    Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("TrdIRSDuration003.beforeEndDate")))
                    return;
                }
                if (!$("#beforeEndPs").val()) {
                    Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("TrdIRSDuration003.beforeEndPs")))
                    return;
                }
                if (!$("#beforeEndAmt").val()) {
                    Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("TrdIRSDuration003.beforeEndAmt")))
                    return;
                }
                return tableInitData();
            });
            if (formTodoList) {
                window.BussTrdTransferOrder['bussTrdTransferOrder'].saveCalcCondition();
            }
            if($("#finprodId").val() && (!$("#restPrin").val() || $("#restPrin").val() <= 0)){
                Joyin.alert("该交易剩余名义本金不足，请选择其他交易！", 0, function(){
                    $('#finprodId').val('').trigger('change');
                });
                return;
            }
        }
    }

    /**
     * 画面加载完成
     */
    $(function () {
        init();
        $('#TrdIRSDuration003_btn_save').click(function () {
            saveOrSubmit('save');
        });
        $('#TrdIRSDuration003_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
        $('#finprodId').bind('change', function () {
            if(!$('#finprodId').val()){
                $('#portfolioId').val('').trigger('change');
                $('#tradeDate').val('');
                $('#irsType').val('').trigger('change');
                $('#vdate').val('');
                $('#mdate').val('');
                $('#term').val('');
                $('#ccy').val('').trigger('change');
                $('#prin').val('');
                $('#restPrin').val('');
                $('#counterId').val('').trigger('change');
                return;
            }
            var finprodId = $('#finprodId').val();
            Joyin.ajaxJson(basePath + 'invest/period/trdirsduration/TrdIRSDuration003/findIrsDealInfo', {finprodId:finprodId},
                function (result) {
                if(result){
                    if(!result.restPrin || result.restPrin<=0){
                        Joyin.alert("该交易剩余名义本金不足，请选择其他交易！", 0, function(){
                            $('#finprodId').val('').trigger('change');
                            return;
                        });
                    }
                    $('#portfolioId').textSelect('setValue', result.portfolioId);
                    $('#portfolioId').trigger('change');
                    $('#tradeDate').val(new Date(result.tradeDate).format());
                    $('#irsType').val(result.irsType).trigger('change');
                    $('#vdate').val(new Date(result.vdate).format());
                    $('#mdate').val(new Date(result.mdate).format());
                    $('#term').val(result.term);
                    $('#ccy').val(result.ccy).trigger('change');
                    $('#prin').val(result.prin);
                    $('#restPrin').val(result.restPrin);
                    $('#counterId').textSelect('setValue', result.counterId);
                    $('#counterId').trigger('change');
                }
            });
        });
    });
}());

