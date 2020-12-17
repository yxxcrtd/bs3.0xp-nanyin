//闭包
$(function () {
    function refreshInfo (){
        var ret = $("#portfolioId").select2("data");
        if(ret&&ret[0].object){
            //未支付本金
            if(ret[0].object.theoryPrin){
                $("#theoryPrin").val(ret[0].object.theoryPrin).trigger("blur");
                $("#prinAmt").val(ret[0].object.theoryPrin).trigger("blur");
            }
            //未支付利息
            if(ret[0].object.theoryInt){
                $("#theoryInt").val(ret[0].object.theoryInt).trigger("blur");
                $("#intAmt").val(ret[0].object.theoryInt).trigger("blur");
            }
            //罚息
            if(ret[0].object.theoryPenaltyInt){
                $("#theoryPenaltyInt").val(ret[0].object.theoryPenaltyInt).trigger("blur");
                $("#penaltyInt").val(ret[0].object.theoryPenaltyInt).trigger("blur");
            }
            $("#prinAmt").trigger("change");
            //理论罚息本金
            if(ret[0].object.theoryPenaltyIntBj){
                $("#theoryPenaltyIntBj").val(ret[0].object.theoryPenaltyIntBj);
            }
            //理论罚息利息
            if(ret[0].object.theoryPenaltyIntLx){
                $("#theoryPenaltyIntLx").val(ret[0].object.theoryPenaltyIntLx);
            }
        }
    }
    var getriskdata = function (PS) {
        var obj = {
            assetCode:$("#finprodId").val(),
            timePoint:MY_SUP_TIME_TYPE_02,
            valDate:$('#transferDate').val(),
            portfolioCode:$('#portfolioId').val(),
            tradeAmount:$('#tradeAmt').val(),
            isEnabled:'01',
            tradeDirection:PS,
            counterId:$("#trdInvestPersonInfo_counterId").val()
        };
        var finType = 'paBussType';
        var finMng = 'TrdDepositInvestTrade001.finprodType';
        obj.assetType = $("#finprodType2").val();
        var object = {valDate:['transferDate','TrdSecOverTransfer001.transferDate'],portfolioCode:['portfolioId','TrdSecOverTransfer001.portfolioId'],tradeAmount:['tradeAmt','TrdSecOverTransfer001.tradeAmt'],assetType:[finType,finMng],tradeDirection:['busiPs','TrdBondToStock002.ps'],data:obj}
        return object;
    };
    function saveOrSubmit(operateType) {
        var s;
        if(typeof(BaseRiskMathComm) != 'undefined') {
    	if(BaseRiskMathComm) {
    	if (window.BaseRiskMathComm.isCalcConditionChanged(getriskdata(PS_MC))){
                return;
                 }
    	       }
          }

        var object = $.serializeToObject('#submitForm',true);
        if(FileCommon) {
            object.uploadFileList = FileCommon.getUploadFileInfo();
        }
        object.operateType = operateType;
        if(window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()){
            return;
        }
        if(!(object.bussTrdTransferOrder)||object.bussTrdTransferOrder.length==0){
            return Joyin.error("请生成划款指令信息！");
        }
        Joyin.ajaxJson(basePath + 'invest/period/trdsecoverdueduration/TrdSecOverdueDuration003/check', object, function (result) {
            Joyin.confirm(result.data, function () {
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/period/trdsecoverdueduration/TrdSecOverdueDuration003/save', object, "TrdSecOverdueDuration003_btn_save", "TrdSecOverdueDuration003");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/period/trdsecoverdueduration/TrdSecOverdueDuration003/save', object, "TrdSecOverdueDuration003_btn_submit", "TrdSecOverdueDuration003");
                }
            });
        });
    }
    window.componentLoadCallback = function(isDone, pagePath, params){
        if(isDone){
            if(typeof(BaseRiskMathComm) != 'undefined') {
            var riskparam = getriskdata(PS_MC);
            if (pageModel==PAGE_MODEL_NEW){
                riskparam.data =null;
            }
            window.BaseRiskMathComm.getparam(riskparam);
            }
            window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                if($('#tradeAmt').val()==''){
                    return Joyin.error("请输入实际支付金额！");
                }
                if($("#trdInvestPersonInfo_counterId").val()==''){
                    return Joyin.error("请输入交易对手！");
                }

                return {
                    'isTotalTrade':YES_OR_NO_N
                    ,'bankLendingType':$("#type8").val()
                    ,'portfolioId':$("#portfolioId").val()
                    ,'deal':{
                        'finprodType2' : $("#finprodType2").val(),
                        'tradeDate' : $('#transferDate').val(),
                        'settleDate' :$('#transferDate').val(),
                        // 'tradeMarket':$("#tradeMarket").val(),
                        // "invAim":$("#invAim").val(),
                        'ps' : PS_MC,
                        'ccy' : CCY_CNY,
                        'tradeAmt' : $("#tradeAmt").val(),
                        'bankLendingType' : $("#type8").val(),
                        "secManageAcctId":$('#secManageAcctId').val(),
                        "chlId":$("#chlId").val(),
                        'counterId' : $("#trdInvestPersonInfo_counterId").val()
                    }
                };
            });
            if( pageModel != PAGE_MODEL_NEW  && $("#tradeAmt").val()&& parseInt($("#tradeAmt").val())!=0){
                window.BussTrdTransferOrder['bussTrdTransferOrder'].saveCalcCondition();
            }
        }
    };
    /**
     * 画面控件初始化后需要执行的方法
     */
    function init(){

    }
    //内部初始化
    $(function () {
        //初始化方法（也可以放在组件加载后）
        init();

        $('#TrdSecOverdueDuration003_btn_riskcal').click(function() {
            //window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata(PS_MC));
            $("#riskMathFlag").val("02");
        });
        $('#TrdSecOverdueDuration003_btn_save').click(function () {
            saveOrSubmit('save');
        });
        $('#TrdSecOverdueDuration003_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
        $("#portfolioId").change(refreshInfo);
        $("#prinAmt,#intAmt,#penaltyInt").change(function () {
            var prinAmt = $("#prinAmt").val();
            var intAmt = $("#intAmt").val();
            var penaltyInt = $("#penaltyInt").val();
            var tradeAmt = JoyinMath.doAdd(JoyinMath.doAdd(prinAmt,intAmt),penaltyInt);
            $("#tradeAmt").val(tradeAmt)
        })
    }());
}());