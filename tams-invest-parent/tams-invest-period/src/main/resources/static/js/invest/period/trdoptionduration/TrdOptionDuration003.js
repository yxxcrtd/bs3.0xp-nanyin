(function () {

    /**
     * 画面加载完成
     */
    $(function () {
        $('#TrdOptionDuration003_btn_save').click(function () {
            saveOrSubmit('save');
        });
        $('#TrdOptionDuration003_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
    });

    function saveOrSubmit(operateType) {
       /* //划款指令参数变更校验
        if(window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()){
            return ;
        }*/
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#informationForm', true);

      /*  //划款指令
        if(object.bussTrdTransferOrder.length==0){
            return Joyin.error('请先生成划款指令');
        }*/
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
                                        "TrdOptionDuration003_btn_save","TrdOptionDuration003");
                            } else {
                                WorkFlow
                                    .doSubmitList(
                                        basePath
                                        + 'invest/period/trdoptionduration/TrdOptionDuration002/save',
                                        object,
                                        "TrdOptionDuration003_btn_submit","TrdOptionDuration003");
                            }
                        });
                });
    }
   /* //回调函数
    window.componentLoadCallback = function(isDone, pagePath, params) {
        if (isDone&&window.BussTrdTransferOrder) {
            window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                var deal = {};
                deal.ccy = $("#ccy").val();
                deal.tradeAmt = $("#amount").val();
                //deal.settleDate = $("#settleDate").val();
                deal.ps = $('#ps').val();
                deal.tradeDate = $("#tradeDate").val();
                deal.finprodType2 = $("#finprodType2").val();
                deal.finprodId = $('#finprodId').val();
                deal.counterId = $('#counterId').val();
                /!* if(!deal.settleDate){
                     return Joyin.alert(JoyinUtil.getMessage("FW_E_0006",JoyinUtil.getLabel("TrdLendDuration001.settleDate")),2);
                 }
                 if(!deal.tradeAmt){
                     return Joyin.alert(JoyinUtil.getMessage("FW_E_0006",JoyinUtil.getLabel("TrdLendDuration001.tradeAmt")),2);
                 }*!/
                return {
                    'isTotalTrade': YES_OR_NO_Y
                    //, 'bankLendingType': $("#bankLendingType").val()
                    , 'portfolioId': $("#portfolioId").val()
                    , 'deal': eval(deal)
                    , 'ieFlag' : $("#sffx").val()
                };
            });
        }
    }*/
}());