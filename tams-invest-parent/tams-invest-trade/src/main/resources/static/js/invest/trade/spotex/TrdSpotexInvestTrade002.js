/**
 * 外汇即期交易新建
 */
$(function() {

    //---add by jjpei 20190718----start---获取风险试算画面要素与Label提示文本
    var getRiskData = function () {
        var obj = {timePoint:MY_SUP_TIME_TYPE_02,valDate:$('#tradeDate').val(),portfolioCode:$('#portfolioId').val(),assetType:FINPROD_TYPE_F11,tradeAmount:$('#prinAmt').val(),isEnabled:USE_STATUS_01,tradeDirection:$('#ps').val()}
        var object = {valDate:['tradeDate','TrdSpotexInvestTrade.tradeDate'],portfolioCode:['portfolioId','TrdSpotexInvestTrade.portfolioId'],tradeAmount:['prinAmt','TrdSpotexInvestTrade.prinAmt'],tradeDirection:['ps','TrdSpotexInvestTrade.ps'],data:obj}
        return object;
    }
    //---add by jjpei 20190718----end---获取风险试算画面要素与Label提示文本

    function saveOrSubmit(operateType) {
    	// 划款指令隐藏时，自动生成划款指令信息
    	generateOrders(function () {
	        //---add by jjpei 20190718----start--风险试算---
	        var s;
            if(typeof(BaseRiskMathComm) != 'undefined') {
	        if(BaseRiskMathComm) {
	            s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
	            if(!s && s != ''){
	                return;
	            }
	            if (window.BaseRiskMathComm.isCalcConditionChanged(getRiskData())){
	                return;
                    }
	            }
	        }
	        //---add by jjpei 20190718----end---风险试算----
	    	if (window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()) {
	            return;
	        }
	    	
	        var object = $.serializeToObject('#submitForm', true);
	        object.pageModel = pageModel;
	        object.operateType = operateType;
	        object.tradeId = $('#tradeId').val();
	        if(FileCommon) {
	        	// 名称错误修改 modify by renping 20191031 start
	            object.uploadFileList = FileCommon.getUploadFileInfo();
	            if(object.uploadFileList && _.isArray(object.fileArray)){
	                $.each(object.uploadFileList, function(index,obj){
	                    obj.seqNo = obj.fileNo;
	                });
	            }
	        	// 名称错误修改 modify by renping 20191031 end
	        }
	        object.inputValue1 = PINAN_ASSET_TYPE_F13;
	        if ($('#trdInvestPersonInfo_counterId').select2('data') && $('#trdInvestPersonInfo_counterId').select2('data')[0]) {
	            object.counterId=$('#trdInvestPersonInfo_counterId').select2('data')[0].id;
	        }
	        var obj = $.extend(true, {}, object);
	        if ($('#curPair').select2('data') && $('#curPair').select2('data')[0]) {
	        	obj.ccy=$('#curPair').select2('data')[0].id;
	        } 
	        if ($('#dealCur').select2('data') && $('#dealCur').select2('data')[0]) {
	        	obj.remark=$('#dealCur').select2('data')[0].id;
	        }  
	        Joyin.ajaxJson(basePath + 'invest/trade/TrdCommonController/check', obj, function(result1) {
	            Joyin.ajaxJson(basePath + 'invest/trade/spotex/TrdSpotexInvestTrade002/check', object, function(result) {
	            	//授信占用额度
//	                Joyin.ajaxJson(basePath + 'invest/trade/TrdCommonController/delLinesDeal',result1.data,function(result2) {});
	            	object.riskCreditListCommonDto = result1.data;
	            	Joyin.confirm(result.data, function() {
	                    if(operateType === 'save') {
	                        WorkFlow.doSaveList(basePath + 'invest/trade/spotex/TrdSpotexInvestTrade002/save', object, "TrdSpotexInvestTrade002_btn_save");
	                    }
	                    else {
	                        WorkFlow.doSubmitList(basePath + 'invest/trade/spotex/TrdSpotexInvestTrade002/save', object, "TrdSpotexInvestTrade002_btn_submit");
	                    }
	                });
	            });
	        });
    	});    
    };
    /**
     * 交易货币，对应货币可用持仓
     */
    var qryMoneyAvailable = function(){
        // 组合代码
        var portfolioId = $('#portfolioId').val();
        // 货币对
        var curPair = $('#curPair').val();
        // 交易货币
        var dealCur = $('#dealCur').val();
        // 对应货币
        var corCur = $('#ccy').val();
        if(portfolioId && curPair && dealCur && corCur){
            // 交易货币可用持仓
            var data = {portfolioId:portfolioId,ccy:dealCur}
            Joyin.ajaxJson(basePath + 'invest/trade/spotex/TrdSpotexInvestTrade002/getProAvailableCash',data,
                    function(result) {
                $('#proAvailableCash').val(result.cashTG).trigger('blur');
            });
            // 对应货币可用持仓
            data = {portfolioId:portfolioId,ccy:corCur}
            Joyin.ajaxJson(basePath + 'invest/trade/spotex/TrdSpotexInvestTrade002/getProAvailableCash',data,
                    function(result) {
                $('#corProAvailableCash').val(result.cashTG).trigger('blur');
            });
        }
    }  
    // 当交易类型为外币对且交易模式选择询价交易时，清算模式自动带出双边全额清算且不可修改；否则，清算模式可选任意选项
    var setDeliveryType = function(){
        if($('#dealMode').val() == DEAL_MODE_01){
            $('#deliveryType').val(SETT_MODE_CENT_SETT).attr("disabled",true).trigger('change');
        }else if($('#excDealType').val() == EXC_DEAL_TYPE_FGN_CY 
                && $('#dealMode').val() == DEAL_MODE_OTC){
            $('#deliveryType').val(SETT_MODE_BIL_SETT).attr("disabled",true).trigger('change');
        }else{
            $('#deliveryType').attr("disabled",false);
        }
    }
    /**
     * 交易费用，折美元金额联动
     */
    var calFeeAmt = function(){
        // 货币对
        var curPairVal = $('#curPair').val();
        // 交易货币
        var dealCurVal = $('#dealCur').val(); 
        // 即期汇率
        var priceVal = $('#unitCprice').val();
        // 交易货币金额
        var dealCurAmtVal = $('#prinAmt').val();
        // 对应货币
        var corCcy = $('#ccy').val();
        // 对应货币金额
        var corCurAmtVal = $('#tradeAmt').val();
        // 交易类型
        var excDealTypeVal = $('#excDealType').val();
        // 交易模式
        var dealModeVal = $('#dealMode').val();
        // 我方角色
        var ourRoleVal = $('#ourRole').val();   
        if(curPairVal && dealCurVal && priceVal && dealCurAmtVal){
            // 交易类型为人民币外汇交易
            if(excDealTypeVal == EXC_DEAL_TYPE_RMB_FX ){
                var rmbAmt;
                if(dealCurVal == CCY_CNY){
                    rmbAmt =  dealCurAmtVal;
                }else{
                    rmbAmt = corCurAmtVal;
                }
                // 交易模式为竞价交易
                if(dealModeVal == DEAL_MODE_01){
                    $('#feeAmt').val(JoyinMath.doDiv(JoyinMath.doMul(rmbAmt,3),10000)).trigger('blur');
                }
                // 交易模式为询价交易或者撮合交易
                else if(dealModeVal == DEAL_MODE_OTC || dealModeVal == DEAL_MODE_MATCH){
                    $('#feeAmt').val(JoyinMath.doDiv(JoyinMath.doMul(rmbAmt,0.1),10000)).trigger('blur');
                }else{
                    $('#feeAmt').val('').trigger('blur');
                }
            }
            // 交易类型为外币对
            else if(excDealTypeVal == EXC_DEAL_TYPE_FGN_CY){
                // 我方角色为发起方
                if(ourRoleVal == OUR_ROLE_TAKER){
                    $('#feeAmt').val(JoyinMath.doDiv(JoyinMath.doMul(dealCurAmtVal,0.075),10000)).trigger('blur'); 
                }
                // 我方角色为报价方
                else if(ourRoleVal == OUR_ROLE_MAKER){
                    $('#feeAmt').val(JoyinMath.doDiv(JoyinMath.doMul(dealCurAmtVal,0.05),10000)).trigger('blur');
                }else{
                    $('#feeAmt').val('').trigger('blur');
                }
            }else{
                $('#feeAmt').val('').trigger('blur');
            }
            // 折美元金额联动
            if(dealCurVal == CCY_USD){
                $('#usdAmt').val(dealCurAmtVal).trigger('blur');
            }else if(corCcy == CCY_USD){
                $('#usdAmt').val(corCurAmtVal).trigger('blur');
            }
        }else{
            $('#feeAmt').val('').trigger('blur');
        }
    }
    
    
    /**
     * 计算交割日
     */
    var calcSettleDate = function() {
        var settleSpeed = $("#accountSpeed").val();
        var calendarId = $("#calendarId").val();
        var tradeDate = $("#tradeDate").val();
        if(settleSpeed && calendarId && tradeDate){
        	if (settleSpeed == ACCOUNT_SPEED_T0) {
                $("#settleDate").val(tradeDate).trigger("change");
            }
            else if(settleSpeed == ACCOUNT_SPEED_T1) {
                Joyin.ajax(basePath + 'invest/trade/spotex/TrdSpotexInvestTrade002/getNextWorkDay', {
                    'calendarId': calendarId,
                    'tradeDate': tradeDate,
                    'accountSpeed':ACCOUNT_SPEED_T1
                }, function (date) {
                    $("#settleDate").val(date).trigger("change");
                });
            }else if(settleSpeed == ACCOUNT_SPEED_T2) {
                Joyin.ajax(basePath + 'invest/trade/spotex/TrdSpotexInvestTrade002/getNextWorkDay', {
                    'calendarId': calendarId,
                    'tradeDate': tradeDate,
                    'accountSpeed':ACCOUNT_SPEED_T2
                }, function (date) {
                    $("#settleDate").val(date).trigger("change");
                });
            } else {
                $("#settleDate").val("").trigger("change");
            }
        }else{
        	$("#settleDate").val("").trigger("change");
        }
        
    }
    
    function tableInitData() {
        var object = $.serializeToObject('#submitForm');
            Joyin.ajaxSync(basePath + 'invest/trade/spotex/TrdSpotexInvestTrade002/getEntity', object,function(result){
                entity = result;
            });
            if(entity){
                entity.forEach(function(value){
                    value.portfolioId = $("#portfolioId").val();
                })
            }
            return {
                'isTotalTrade':YES_OR_NO_N
                ,'bankLendingType':null
                ,'portfolioId':$("#portfolioId").val()
                ,'deal': entity
            };
    };
  //画面初始化
    var init = function(){
        
        var entrustId = $("#entrustId").val();
        
        /**
         * 货币对
         */
        $('#curPair').change(function(){
            // 货币对和即期汇率联动,setDecimalPoint(货币对下拉框id,即期汇率文本框id)
            window.EtrForeingExchange.setDecimalPoint('curPair','unitCprice',0);
            // 货币对,交易货币和对应货币联动
            var curPair = $('#curPair').val();
            var dealCur = $('#dealCur').val();
            if(curPair.indexOf(dealCur) > -1){
                window.EtrForeingExchange.showCorCcy('curPair','dealCur','ccy');
            }else{
                $('#dealCur').val('').trigger('change');
            }
            // 交易货币,对应货币可用持仓查询
            qryMoneyAvailable();
            // 预计对应货币金额联动
            window.EtrForeingExchange.calcExpCorAmt('curPair','dealCur','unitCprice','prinAmt','tradeAmt',JPY_CNY);
            // 交易费用联动
            calFeeAmt();
        });
        /**
         * 交易货币
         */
        $('#dealCur').change(function(){
            window.EtrForeingExchange.showCorCcy('curPair','dealCur','ccy');
//            qryMoneyAndAcct();
            // 预计对应货币金额联动
            window.EtrForeingExchange.calcExpCorAmt('curPair','dealCur','unitCprice','prinAmt','tradeAmt',JPY_CNY);
            // 交易货币,对应货币可用持仓查询
            qryMoneyAvailable();
            // 交易费用联动
            calFeeAmt();
        });
        /**
         * 即期汇率,交易货币金额
         */
        $('#unitCprice,#prinAmt').keyup(function(){
            // 预计对应货币金额联动
            window.EtrForeingExchange.calcExpCorAmt('curPair','dealCur','unitCprice','prinAmt','tradeAmt',JPY_CNY);
            // 交易费用联动
            calFeeAmt();
        });
        /**
         * 外汇交易类型,交易模式
         */
        $('#excDealType,#dealMode').change(function(){
            // 清算模式联动
            setDeliveryType();
            // 交易费用联动
            calFeeAmt();
        });
        /**
         * 我方角色
         */
        $('#ourRole').change(function(){
            // 交易费用联动
            calFeeAmt();
        });
        /**
         * 交易日，清算速度，交易日历
         */
        $('#calendarId,#tradeDate,#accountSpeed').change(function(){
            // 交割日联动
            calcSettleDate();
        });
        
       
        /**
         * 组合代码
         */
        $('#portfolioId').change(function(){
        	// 交易货币,对应货币可用持仓查询
            qryMoneyAvailable();
        });
        /**
         * 委托代码
         */
        $('#entrustId').change(function(){
            var entrustId = $('#entrustId').val();
            if(entrustId){
                $('#portfolioId').attr('disabled',true).trigger('change');
                $('#excDealType').attr('disabled',true).trigger('change');
                $('#curPair').attr('disabled',true).trigger('change');
                $('#ps').attr('disabled',true).trigger('change');
                $('#accountSpeed').attr('disabled',true).trigger('change');
                $('#dealCur').attr('disabled',true).trigger('change');
                // $('#unitCprice').attr('disabled',true).trigger('change');
                
            }else{
            	 $('#portfolioId').attr('disabled',false).trigger('change');
                 $('#excDealType').attr('disabled',false).trigger('change');
                 $('#curPair').attr('disabled',false).trigger('change');
                 $('#ps').attr('disabled',false).trigger('change');
                 $('#accountSpeed').attr('disabled',false).trigger('change');
                 $('#dealCur').attr('disabled',false).trigger('change');
                 // $('#unitCprice').attr('disabled',false).trigger('change');
            }
        });
        
        
        window.componentLoadCallback = function(isDone, pagePath, params){
            if(isDone){
                if(typeof(BaseRiskMathComm) != 'undefined') {
                //----add by jjpei 20190718 风险试算----start----
                var riskParam = getRiskData();
                if (pageModel==PAGE_MODEL_NEW){
                    riskParam.data =null;
                }
                window.BaseRiskMathComm.getparam(riskParam);
                //----add by jjpei 20190718 风险试算----end----
                }
                if(window.BussTrdTransferOrder && window.BussTrdTransferOrder['bussTrdTransferOrder']) {
                    window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(tableInitData, true);
                }
                if(bussTrdTransferOrderDtos){
                    window.BussTrdTransferOrder['bussTrdTransferOrder'].saveCalcCondition();
                }
          }
        }
        
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
     * 画面加载完成
     */
    $(function() {
        //初始化画面
        init(); 
        // 是否显示划款指令
        if(GEN_PAY_ORDERS_AUTO=='01'){
        	$('#BussTrdTransferOrder').hide() ;
        }else{
        	$('#BussTrdTransferOrder').show() ;
        }
        // 绑定按钮事件
        $('#TrdSpotexInvestTrade002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TrdSpotexInvestTrade002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        //风险试算
        $('#TrdSpotexInvestTrade002_btn_risk').click(function(){
            //window.BaseRiskMathComm.initBaseRiskMathCommPage(getRiskData());
            //-----------add by jjpei 2020-01-07 风险新版本 start----------
            var object = $.serializeToObject('#submitForm', true);
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/1/RSK002/F13', object,getRiskData());
            //-----------add by jjpei 2020-01-07 风险新版本 end----------
           /* var ps = $('#ps').val();
            if (ps == ps_T04 || ps == ps_mr || ps == ps_mc){
                var obj = {timePoint:MY_SUP_TIME_TYPE_02,tradeDirection:$('#ps').val(),valDate:$('#tradeDate').val(),portfolioCode:$('#portfolioId').val(),tradeAmount:$('#tradeAmt').val(),assetType:FINPROD_TYPE_F17,assetCode:$('#finprodId').val()}
                var object = {tradeDirection:['ps','TrdPlanNetInvestTrade001.ps'],valDate:['tradeDate','TrdSpotexInvestTrade.tradeDate'],portfolioCode:['portfolioId','TrdSpotexInvestTrade.portfolioId'],tradeAmount:['tradeAmt','TrdSpotexInvestTrade.tradeAmt'],assetType:['type6','TrdSpotexInvestTrade.type6'],data:obj}
                window.BaseRiskMathComm.initBaseRiskMathCommPage(object);
            }else if (ps == ps_T06){
                var obj = {timePoint:MY_SUP_TIME_TYPE_02,tradeDirection:$('#ps').val(),valDate:$('#tradeDate').val(),portfolioCode:$('#portfolioId').val(),tradeAmount:$('#shareAmt').val(),assetType:FINPROD_TYPE_F17,isEnabled:'01',assetCode:$('#finprodId').val()}
                var object = {tradeDirection:['ps','TrdPlanNetInvestTrade001.ps'],valDate:['tradeDate','TrdSpotexInvestTrade.tradeDate'],portfolioCode:['portfolioId','TrdSpotexInvestTrade.portfolioId'],tradeAmount:['shareAmt','TrdSpotexInvestTrade003.tradeSha'],assetType:['type6','TrdSpotexInvestTrade.type6'],data:obj}
                window.BaseRiskMathComm.initBaseRiskMathCommPage(object);
            } else {
                var obj = {timePoint:MY_SUP_TIME_TYPE_02,tradeDirection:$('#ps').val(),valDate:$('#tradeDate').val(),portfolioCode:$('#portfolioId').val(),tradeAmount:$('#tradeAmt').val(),assetType:FINPROD_TYPE_F17,assetCode:$('#finprodId').val()}
                var object = {tradeDirection:['ps','TrdPlanNetInvestTrade001.ps'],valDate:['tradeDate','TrdSpotexInvestTrade.tradeDate'],portfolioCode:['portfolioId','TrdSpotexInvestTrade.portfolioId'],tradeAmount:['tradeAmt','TrdSpotexInvestTrade.tradeAmt'],assetType:['type6','TrdSpotexInvestTrade.type6'],data:obj}
                window.BaseRiskMathComm.initBaseRiskMathCommPage(object);
            }*/
            window.BaseRiskMathComm.initBaseRiskMathCommPageCus(getriskdata());

        })
    });
}());