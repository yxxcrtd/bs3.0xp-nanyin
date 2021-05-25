/**
 * 拆借存续期确认
 */
$(function() {
	function saveOrSubmit(operateType) {
	    if($('#tradeAmt').val()==''&&$('#pingAnCodeSin').val() ==  BACK_LEND_DIRECTION_01){
            Joyin.error("请输入实付本息和");
            return;
        }
        // modify by zhangrui1 SNSTZJY-418 start
        //if($('#actRecPrincipa').val()==''==''&&$('#pingAnCodeSin').val() ==  BACK_LEND_DIRECTION_02){
        if($('#actRecPrincipa').val()==''&&$('#pingAnCodeSin').val() ==  BACK_LEND_DIRECTION_02){
        // modify by zhangrui1 SNSTZJY-418 end
            Joyin.error("请输入实收本息和");
            return;
        }
        generateOrders(function () {
            //		划款指令参数变更校验
            if(window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()){
                return ;
            }
            var object = $.serializeToObject('#submitForm',true);
            /*if(object.bussTrdTransferOrder.length==0){
                return Joyin.error('请先生成划款指令');
            }*/
            object.operateType = operateType;
            object.pageModel = PAGE_MODEL_NEW;
            //文件信息
            if(FileCommon) {
                object.uploadFileList = FileCommon.getUploadFileInfo();
            }
            Joyin.ajaxJson(basePath + 'invest/period/trdlendduration/TrdLendDuration002/check', object, function(result) {
                Joyin.confirm(result.data, function() {
                    if(operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'invest/period/trdlendduration/TrdLendDuration002/save', object, "TrdLendDuration002_btn_save");
                    }
                    else {
                        WorkFlow.doSubmitList(basePath + 'invest/period/trdlendduration/TrdLendDuration002/save', object, "TrdLendDuration002_btn_submit");
                    }
                });
            });
        })
    }
    var generateOrders = function (callback) {
        if(GEN_PAY_ORDERS_AUTO =='01'){
            $('#bussTrdTransferOrder_btn_generate').click();
        }
        // 回调方法
        if (callback && typeof callback === 'function') {
            callback();
        }
    }

    function formatNumber(value, decimal) {
        var num = (value || 0).toString();
        if(decimal && $.isNumeric(decimal)) {
            num = parseFloat(value || 0).toFixed(decimal);
        }
        var result = '', last = '', index = num.indexOf('.');
        if(index > 0) {
            last = num.substring(index);
            num = num.substring(0, index);
        }
        while (num.length > 3) {
            result = ',' + num.slice(-3) + result;
            num = num.slice(0, num.length - 3);
        }
        if (num) { result = num + result; }
        return result + last;
    };

	//回调函数
	window.componentLoadCallback = function(isDone, pagePath, params) {
        if (isDone&&window.BussTrdTransferOrder) {
            window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                var deal = {};
                deal.ccy = $("#ccy").val();
                deal.tradeAmt = $("#tradeAmt").val() ? $("#tradeAmt").val() : $("#actRecPrincipa").val();
                deal.settleDate = $("#settleDate").val();
                deal.ps = $('#ps').val();
                deal.tradeDate = $("#tradeDate").val();
                deal.finprodType2 = $("#finprodType2").val();
                deal.finprodId = $('#finprodId').val();
                deal.counterId = $('#counterId').val();
                if (!deal.settleDate) {
                    return Joyin.alert(JoyinUtil.getMessage("FW_E_0006", JoyinUtil.getLabel("TrdLendDuration001.settleDate")), 2);
                }
                if (!deal.tradeAmt) {
                    return Joyin.alert(JoyinUtil.getMessage("FW_E_0006", JoyinUtil.getLabel("TrdLendDuration001.tradeAmt")), 2);
                }
                return {
                    'isTotalTrade': YES_OR_NO_Y
                    , 'bankLendingType': $("#bankLendingType").val()
                    , 'portfolioId': $("#actPortfolioId").val()
                    , 'deal': eval(deal)
                };
            });
            //页面加载完全
            // 是否显示划款指令
            if (GEN_PAY_ORDERS_AUTO == '01') {
                $('#bussTrdTransferOrder_div').hide();
            } else {
                $('#bussTrdTransferOrder_div').show();
            }
            //到期确认画面初期化时，实付本息和应该默认=到期本息和
            if ($('#actRecPrincipa').val() == ''&&$('#pingAnCodeSin').val() ==  BACK_LEND_DIRECTION_02) {
                $('#actRecPrincipa').val($('#theoryAmt').val());
            }
            if($('#tradeAmt').val() == ''&&$('#pingAnCodeSin').val() ==  BACK_LEND_DIRECTION_01){
                $('#tradeAmt').val(formatNumber($('#theoryAmt').val()));
            }

        }
    }
	/**
     * 画面加载完成
     */
    $(function() {
        // 是否显示划款指令
        if(GEN_PAY_ORDERS_AUTO=='01'){
            $('#bussTrdTransferOrder_div').hide() ;
        }else{
            $('#bussTrdTransferOrder_div').show() ;
        }
    	// 绑定按钮事件
        $('#TrdLendDuration002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TrdLendDuration002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());
