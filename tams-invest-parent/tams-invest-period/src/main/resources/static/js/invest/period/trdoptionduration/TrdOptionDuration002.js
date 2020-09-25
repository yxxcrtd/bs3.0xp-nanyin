(function () {

    /**
     * 画面加载完成
     */
    $(function () {
        $('#TrdOptionDuration002_btn_save').click(function () {
            saveOrSubmit('save');
        });
        $('#TrdOptionDuration002_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
    });

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
        //划款指令参数变更校验
        if(window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()){
            return ;
        }
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#informationForm', true);

        //划款指令
        if(object.bussTrdTransferOrder.length==0){
            return Joyin.error('请先生成划款指令');
        }

        //文件信息
        if(FileCommon) {
            object.uploadFileList = FileCommon.getUploadFileInfo();
        }
        object.operateType = operateType;
        Joyin
            .ajaxJson(
                basePath
                + 'invest/period/trdoptionduration/TrdOptionDuration002/check',
                object,
                function(result) {
                    Joyin.confirm(
                        result.data,
                        function() {
                            if (operateType === 'save') {
                                WorkFlow
                                    .doSaveList(
                                        basePath
                                        + 'invest/period/trdoptionduration/TrdOptionDuration002/save',
                                        object,
                                        "TrdOptionDuration002_btn_save","TrdOptionDuration002");
                            } else {
                                WorkFlow
                                    .doSubmitList(
                                        basePath
                                        + 'invest/period/trdoptionduration/TrdOptionDuration002/save',
                                        object,
                                        "TrdOptionDuration002_btn_submit","TrdOptionDuration002");
                            }
                        });
                });
    }

    //回调函数
    window.componentLoadCallback = function(isDone, pagePath, params) {
        if (isDone&&window.BussTrdTransferOrder) {
            window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                if (!$("#tradeAmt").val()) {
                    Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("TrdOptionDuration.tradeAmt")));
                    return;
                }
                if (!$("#zfDate").val()) {
                    Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("TrdOptionDuration.zfDate")));
                    return;
                }
                var deal = {};
                deal.ccy = $("#ccy").val();
                deal.tradeAmt = $("#tradeAmt").val();
                if('DQXQ' == $("#xqType").val()){
                    deal.ps = "T60";
                }else{
                    deal.ps = "T59";
                }
                deal.tradeDate = $("#zfDate").val();
                deal.finprodType2 = $("#finprodType2").val();
                deal.finprodId = $('#finprodId').val();
                deal.counterId = $('#counterId').val();
                return {
                    'isTotalTrade': YES_OR_NO_Y
                    , 'portfolioId': $("#portfolioId").val()
                    , 'deal': eval(deal)
                    , 'ieFlag' : $("#sffx").val()
                };
            });
        }
    }

}());