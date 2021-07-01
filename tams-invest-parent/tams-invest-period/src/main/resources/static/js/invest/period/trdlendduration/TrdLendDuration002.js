/**
 * 拆借存续期确认
 */
$(function() {
	function saveOrSubmit(operateType) {
//		划款指令参数变更校验
		if(window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()){
			return ;
		}
    	var object = $.serializeToObject('#submitForm');
    	if(object.bussTrdTransferOrder.length==0){
    		return Joyin.error('请先生成划款指令');
    	}
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
    }
	//回调函数
	window.componentLoadCallback = function(isDone, pagePath, params) {
        if (isDone&&window.BussTrdTransferOrder) {
            window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
            	var deal = {};
            	deal.ccy = $("#ccy").val();
                deal.tradeAmt = $("#tradeAmt").val()?$("#tradeAmt").val():$("#actRecPrincipa").val();
                deal.settleDate = $("#settleDate").val();
                deal.ps = $('#ps').val();
                deal.tradeDate = $("#tradeDate").val();
                deal.finprodType2 = $("#finprodType2").val();
                deal.finprodId = $('#finprodId').val();
                deal.counterId = $('#counterId').val();
            	if(!deal.settleDate){
            		return Joyin.alert(JoyinUtil.getMessage("FW_E_0006",JoyinUtil.getLabel("TrdLendDuration001.settleDate")),2);
            	}
            	if(!deal.tradeAmt){
            		return Joyin.alert(JoyinUtil.getMessage("FW_E_0006",JoyinUtil.getLabel("TrdLendDuration001.tradeAmt")),2);
            	}
            	return {
            		'isTotalTrade': YES_OR_NO_Y
                    , 'bankLendingType': $("#bankLendingType").val()
                    , 'portfolioId': $("#actPortfolioId").val()
                    , 'deal': eval(deal)
                };
            });
        }
    }
	/**
     * 画面加载完成
     */
    $(function() {
    	// 绑定按钮事件
        $('#TrdLendDuration002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TrdLendDuration002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        //add by jiangpeng on 20210422 start TISHKBS-1803
        $("#settleDate").change(function () {
            if(isTodo){
                let param = {};
                let dateMap = {};
                param.isOnlyOneDate = true;
                dateMap.settleDate = $("#settleDate").val();
                window.BussTrdTransferOrder['bussTrdTransferOrder'].updateGenerateOrders(dateMap,param);
            }
        });
        //add by jiangpeng on 20210422 end TISHKBS-1803
    });
}());
