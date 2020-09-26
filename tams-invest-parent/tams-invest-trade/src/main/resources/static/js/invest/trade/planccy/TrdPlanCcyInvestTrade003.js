 /**
 * 计划类（货币型）投资交易确认
 */
$(function() {
    var getriskdata = function () {
        var obj = {timePoint:MY_SUP_TIME_TYPE_02,valDate:$('#tradeDate').val(),portfolioCode:$('#portfolioId').val(),tradeAmount:$('#shareAmt').val(),assetType:$('#type6').val(),isEnabled:'01',assetCode:$('#finprodId').val()}
        var object = {valDate:['tradeDate','TrdPlanCcyInvestTrade002.tradeDate'],portfolioCode:['portfolioId','TrdPlanCcyInvestTrade002.portfolioId'],tradeAmount:['shareAmt','TrdPlanCcyInvestTrade003.tradeSha'],assetType:['type6','TrdPlanCcyInvestTrade002.type6'],data:obj}
        return object;
    }
    function saveOrSubmit(operateType) {
    	// 划款指令隐藏时，自动生成划款指令信息
    	generateOrders(function () {
    		 var s;
             if(BaseRiskMathComm&&$('#ps').val()==ps_T19) {
                 s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
                 if(!s && s != ''){
                     return;
                 }
                 if (window.BaseRiskMathComm.isCalcConditionChanged(getriskdata())){
                     return;
                 }
             }
	    	if (window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()) {
	            return;
	        }
	        var object = $.serializeToObject('#submitForm',true);
	        if ($('#select2-finprodId-container').attr('title')){
	            object.finprodName = $('#select2-finprodId-container').attr('title');
	        }
	        object.pageModel = pageModel;
	        object.operateType = operateType;
	        object.tradeId = $('#tradeId').val();
	        object.bTradeId = $('#bTradeId').val();
	        if($('#ps').val() == ps_T05){
	        	object.prinAmt = $('#tradeAmtHid2').val();
	        }else{
	        	object.prinAmt = $('#tradeAmtHid').val();
	        }
	        
	        if(FileCommon) {
	            if(pageModel == PAGE_MODEL_REFERENCE) {
	                object.fileArray = $('#fileUploader').data('data');
	            }
	            else {
	                object.fileArray = FileCommon.getUploadFileInfo();
	            }
	            if(object.fileArray && _.isArray(object.fileArray)){
	                $.each(object.fileArray, function(index,obj){
	                    obj.seqNo = obj.fileNo;
	                });
	            }
	        }
	        debugger;
	        Joyin.ajaxJson(basePath + 'invest/trade/planccy/TrdPlanCcyInvestTrade003/check', object, function(result) {
	        	Joyin.ajaxJson(basePath + 'invest/trade/planccy/TrdPlanCcyInvestTrade003/checkAuthorization', object, function(result1) {
	        		Joyin.confirm(result.data, function() {
		                if(operateType === 'save') {
		                    WorkFlow.doSaveList(basePath + 'invest/trade/planccy/TrdPlanCcyInvestTrade003/save', object, "TrdPlanCcyInvestTrade003_btn_save");
		                }
		                else {
		                    WorkFlow.doSubmitList(basePath + 'invest/trade/planccy/TrdPlanCcyInvestTrade003/save', object, "TrdPlanCcyInvestTrade003_btn_submit");
		                }
	            	});
	            });
	        });
    	});
    };
    
  //查询组合可用现金
    var getPtlCashPositionInfo = function(){
        param = {portfolioId:$('#portfolioId').val(),ccy:$('#ccy').val(),vdate:$('#tradeDate').val(),calId:$('#calendarId').val(), chlId: $("#chlId").val()}
        Joyin.ajaxJson(basePath + 'invest/trade/planccy/TrdPlanCcyInvestTrade002/getProAvailableCash',param,
                function(result) {
            $('#proAvailableCash').val(result.cashT).trigger('blur');
            $('#proAvailableCash1').val(result.cashT1).trigger('blur');
            $('#accAvailableCash').val(result.cashT2).trigger('blur');
            $('#channelAvailableCash').val(result.cashTD).trigger('blur');
        });
    }  
    //查询组合可用持仓
    var getPtlSecPositionInfo = function(){
        param = {portfolioId:$('#portfolioId').val(),finprodId:$('#finprodId').val(),cdate:$('#tradeDate').val(), secAcctId: $("#chlId").val()}
        Joyin.ajaxJson(basePath + 'invest/trade/planccy/TrdPlanCcyInvestTrade002/getPtlSecPosition',param,
                function(result) {
            $('#assetAvailablePosition').val(result).trigger('blur');
        });
    }
  //画面初始化
    var init = function(){
        //赎回到账确认 预计到账日->实际到账日
        if($('#ps').val()!= ps_T24){//T24
            $("#expectedArrivalDate_label").text('预计到账日');
        }else{
            $("#expectedArrivalDate_label").text('实际到账日');
        }
        $('#finprodId').on('change', function () {
            var finprodId = $('#finprodId').val();
            var ps = $('#ps').val();
            var portfolioId =  $('#portfolioId').val();
            if(finprodId){
                var data = $('#finprodId').select2('data')[0].object;
                $("#finprodName").val($('#finprodId').select2('data')[0].id);
                $("#calendarId").val(data.calendarId).trigger('change');
                $("#ccy").val(data.ccy).trigger('change');
                $("#type6").val(data.type6).trigger('change');
                $("#type7").val(data.type7).trigger('change');
                /*添加二三四级联动 lishuping 2019/8/6 start*/
                $("#type9").val(data.type9).trigger('change');
                $("#type10").val(data.type10).trigger('change');
                $("#type11").val(data.type11).trigger('change');
                /*添加二三四级联动 lishuping 2019/8/6 start*/
                // $("#trdInvestPersonInfo_counterId").val(data.manager).trigger('change');
            }
            ChannelAssetCommon.setChannelSelectObject($("#chlId"), finprodId, false, function () {
                if(ps == ps_T05 && finprodId && portfolioId){
                    getPtlCashPositionInfo();
                }
                if((ps == ps_T19 || ps == ps_T24) && finprodId && portfolioId){
                    getPtlSecPositionInfo();
                }
            });

            var netDate = $('#netDate').val();
            var tradeAmt = $('#tradeAmt').val();
            var type6 = $('#type6').val();
            if(netDate && finprodId){
                var data = {finprodId:finprodId,netDate:netDate}
                Joyin.ajaxJson(basePath + 'invest/trade/planccy/TrdPlanCcyInvestTrade003/getPrice', data , function(result) {
                    $('#unitCprice').val(result).trigger('blur').change();
                });
            }
        });
        /**
         * 交易方向
         */
        $('#ps').on('change',function(){
            var ps = $('#ps').val();
            var finprodId = $('#finprodId').val();
            var portfolioId =  $('#portfolioId').val();
            if((ps == ps_T19 || ps == ps_T24)){
                $('#proAvailableCash_div').hide();
                $('#proAvailableCash1_div').hide();
                $('#accAvailableCash_div').hide();
                $('#assetAvailablePosition_div').show();
                $('#expectedArrivalDate_div').show();
                $('#transferInformation').show();
                $('#carryAmt_div').show();
                $('#purchase').hide();
                $('#redemp').show();
                if(ps == ps_T24 && single==1){
                    $('#expectedArrivalDate').attr('disabled',false);
                    $('#expectedArrivalDate').attr('data-ignore',true);
                    $('#TrdPlanCcyInvestTrade003_btn_save').attr('data-ignore',true).removeClass("layui-btn-disabled");;
                    $('#TrdPlanCcyInvestTrade003_btn_submit').attr('data-ignore',true).removeClass("layui-btn-disabled");;
                    $('#TrdPlanCcyInvestTrade003_btn_risk').attr('data-ignore',true).removeClass("layui-btn-disabled");;
                }
                if(finprodId && portfolioId){
                    getPtlSecPositionInfo();
                }
            }else{
                $('#proAvailableCash_div').show();
                $('#proAvailableCash1_div').show();
                $('#accAvailableCash_div').show();
                $('#assetAvailablePosition_div').hide();
                $('#expectedArrivalDate_div').hide();
                $('#transferInformation').hide();
                $('#carryAmt_div').hide();
                $('#purchase').show();
                $('#redemp').hide();
                if(finprodId && portfolioId){
                    getPtlCashPositionInfo();
                }
            }
        });
        /**
         * 单位净价
         */
        $('#unitCprice').change(function(){
            var unitCprice = $('#unitCprice').val();
            var tradeAmt = $('#tradeAmt').val();
            var ps = $('#ps').val();
            var shareAmt = $('#shareAmt').val();
            if(ps == ps_T05){
                if(unitCprice != null && unitCprice != '' && unitCprice != 0){
                    $('#shareAmt').val(tradeAmt/unitCprice).trigger('blur');
                }
            }else if((ps == ps_T19)){
                if(unitCprice != null && unitCprice != '' && shareAmt){
                    $('#tradeAmt').val(shareAmt*unitCprice).trigger('blur').attr('disabled',true);
                    $('#tradeAmtHid').val(shareAmt*unitCprice);
                }else if(unitCprice != null && unitCprice != '' && unitCprice != 0 && tradeAmt){
                    $('#shareAmt').val(tradeAmt/unitCprice).trigger('blur').attr('disabled',true);
                }
            }
        });
        /**
         * 组合代码
         */
        $('#portfolioId').change(function(){
           var portfolioId =  $('#portfolioId').val();
           var ps = $('#ps').val();
           var finprodId = $('#finprodId').val();
           if(ps == ps_T05 && finprodId && portfolioId){
               getPtlCashPositionInfo();
           }
           if((ps == ps_T19 || ps == ps_T24) && finprodId && portfolioId){
               getPtlSecPositionInfo();
           }
        });
        /**
         * 申购费/赎回费
         */
        $('#feeAmtPay').change(function(){
        	var ps = $('#ps').val();
        	if(ps == ps_T05){
	            var feeAmtPay = $('#feeAmtPay').val();
	            var tradeAmt = $('#tradeAmtHid').val();
	            var unitCprice = $('#unitCprice').val();
	            //var channelFee = $('#channelFee').val();
                //feeAmtPay = JoyinMath.doAdd(feeAmtPay, channelFee);   
                // 查看页面重复减费用 yjm start 20190909
                if(feeAmtPay && tradeAmt && unitCprice){
                    if (pageModel != PAGE_MODEL_REFERENCE) {
                        $('#shareAmt').val((tradeAmt - feeAmtPay) / unitCprice).trigger('blur');
                        $('#tradeAmtHid2').val(tradeAmt - feeAmtPay);
                    }else {
                        $('#shareAmt').val(tradeAmt / unitCprice).trigger('blur');
                        $('#tradeAmtHid2').val(tradeAmt);
                    }
                }else if(!feeAmtPay){
                    if (pageModel != PAGE_MODEL_REFERENCE) {
                        $('#tradeAmt').val(tradeAmt).change().trigger('blur');
                    }
                    $('#tradeAmtHid2').val(tradeAmt);
                }
                // 查看页面重复减费用 yjm end 20190909
                 
            }else if(ps == ps_T19){
        		$('#carryAmt').change();
        	}
        });
        /**
         * 交易金额
         */
        $('#tradeAmt').change(function(){
            var feeAmtPay = $('#feeAmtPay').val();
            var tradeAmt = $('#tradeAmt').val();
            var unitCprice = $('#unitCprice').val();
            if(unitCprice && $('#tradeAmt').val()){
                $('#shareAmt').val($('#tradeAmt').val()/unitCprice).trigger('blur');
                $('#carryAmt').change();
            }
        });
        
        $('#shareAmt').change(function(){
            var unitCprice = $('#unitCprice').val();
            var shareAmt = $('#shareAmt').val();
            if(unitCprice && shareAmt){
                $('#tradeAmt').val(unitCprice*shareAmt).trigger('blur');
                $('#tradeAmtHid').val(unitCprice*shareAmt);
                $('#carryAmt').change();
            }
        });
        
        $('#carryAmt').change(function(){
        	var carryAmt = $('#carryAmt').val();
        	var feeAmtPay = $('#feeAmtPay').val();
        	var tradeAmtHid = $('#tradeAmtHid').val();
        	//var channelFee = $('#channelFee').val();
        	if(!carryAmt){
        		carryAmt = 0;
        	}
        	if(!feeAmtPay){
        		feeAmtPay = 0;
        	}
           // feeAmtPay = JoyinMath.doAdd(feeAmtPay, channelFee);
        	$('#tradeAmt').val(parseFloat(tradeAmtHid)+parseFloat(carryAmt)-parseFloat(feeAmtPay)).trigger('blur');
        });
        $('#expectedArrivalDate').change(function(){
            var expectedArrivalDate = $('#expectedArrivalDate').val();
            if(expectedArrivalDate){
                window.BussTrdTransferOrder['bussTrdTransferOrder'].generateClick();
            }
        });
        
        window.componentLoadCallback = function(isDone, pagePath, params){
            if(isDone){
                var riskparam = getriskdata();
                if (pageModel==PAGE_MODEL_NEW){
                    riskparam.data =null;
                }
                window.BaseRiskMathComm.getparam(riskparam);
                window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                	var data = {};
                    data.counterId = $('#trdInvestPersonInfo_counterId').val();
                    data.tradeAmt = $('#tradeAmt').val();
                    data.profitType = $('#profitType').val();
                    data.ps = $('#ps').val();
                    data.finprodId = $('#finprodId').val();
                    data.ccy = $('#ccy').val();
                    data.chlId = $("#chlId").val();
                    data.tradeDate = $('#tradeDate').val();
                    if(data.ps == ps_T24){
                        data.tradeDate = $('#expectedArrivalDate').val();
                    }
                    data.finprodType2 = $('#finprodType2').val();
                    data.profitType = $('#profitType').val();
                    return {
                        'isTotalTrade':YES_OR_NO_N
                        ,'bankLendingType':null
                        ,'portfolioId':$("#portfolioId").val()
                        ,'deal':data
                    };
                });
//                if(entrustId != ''){
//                    if(etrEntrust){
//                        window.TrdInvestPersonInfo002['trdInvestPersonInfo'].setEntrust(function () {
//                            return etrEntrust;
//                        });
//                    }
//                }
            }
        }
        $('#ps').change();
        $('#finprodId').change();
        $('#unitCprice').change();
        $('#feeAmtPay').change();
    };
    //add by lizhi 2020-2-26 划款指令是否自动生成  start
    var generateOrders = function (callback) {
    	// 如果还款指令设置为隐藏，则自动生成划款指令
        // modify by jiangpeng 20200722 申购确认,赎回不生成划款指令
    	if(GEN_PAY_ORDERS_AUTO =='01'&&$("#ps").val()!=ps_T05&&$("#ps").val()!='T06'){
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
        // modify by jiangpeng 20200722 申购确认,赎回不显示划款指令
        if(GEN_PAY_ORDERS_AUTO=='01'||$("#ps").val()==ps_T05||$("#ps").val()=='T06'){
        	$('#transferInformation').hide() ;
        }else{
        	$('#transferInformation').show() ;
        }
        // 绑定按钮事件
        $('#TrdPlanCcyInvestTrade003_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TrdPlanCcyInvestTrade003_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $('#TrdPlanCcyInvestTrade003_btn_view').click(function(){
            var finprodId = $('#finprodId').val();
            if(finprodId){
                Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                        basePath + "invest/asset/plan/plancur/FinPlanAssetC002/init",
                        "95%", "90%",{pageModel:PAGE_MODEL_REFERENCE,
                        finprodId: finprodId});
            }
        });
      //风险试算
        $('#TrdPlanCcyInvestTrade003_btn_risk').click(function(){
            var object = $.serializeToObject('#submitForm',true);
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/1/RSK002/F09', object,getriskdata());
            
            window.BaseRiskMathComm.initBaseRiskMathCommPageCus(object);

        })
      //是否展示资产四五级分类
        if(IS_SHOW_TYPE == '01'){
        	$('#type11Div').show();
        }else{
        	$('#type11Div').hide();
        }
    });
});
