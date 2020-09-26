/**
 * 外汇远期投资交易
 */
(function(){

	var initFlag = true;
	var quotPre = 0;
	var entity = '';
    //---add by jjpei 20190718----start---获取风险试算画面要素与Label提示文本
    var getRiskData = function () {
        var obj = {timePoint:MY_SUP_TIME_TYPE_02,valDate:$('#tradeDate').val(),portfolioCode:$('#portfolioId').val(),assetType:FINPROD_TYPE_TG_F12,tradeAmount:$('#prinAmt').val(),isEnabled:USE_STATUS_01,tradeDirection:$('#ps').val()}
        var object = {valDate:['tradeDate','TrdForwexInvestTrade.tradeDate'],portfolioCode:['portfolioId','TrdForwexInvestTrade.portfolioId'],tradeAmount:['prinAmt','TrdForwexInvestTrade.prinAmt'],tradeDirection:['ps','TrdForwexInvestTrade.excPs'],data:obj}
        return object;
    }
    //---add by jjpei 20190718----end---获取风险试算画面要素与Label提示文本
	/**
	 * 清算模式change事件
	 */
	function deliveryTypeChange() {
    	//清算模式
        var deliveryType = $('#deliveryType').val();
        //清算模式为 差额清算
        //定价日和差额交割参考汇率必填，否则置灰不可填；定价日自动带出为交割日前2个工作日，可修改
        if(deliveryType==SETTMODE_DIF_SETT){
        	//定价日
    		calPricedDate();
        	$("#pricedDate").prop("disabled", false).trigger("change");
        	//差额交割参考汇率
        	$("#refRate").prop("disabled", false).trigger("change");
        }else{
        	//定价日
        	$("#pricedDate").val('').prop("disabled", true).trigger("change");
        	//差额交割参考汇率
        	$("#refRate").val('').prop("disabled", true).trigger("change");
        }
        // 交易方向为到期  清算模式为差额清算
        if($("#isDifSquEx").val() == ISDIFSQU_Y || $('#deliveryType').val()!=SETTMODE_DIF_SETT){
        	// 系统参数GEN_PAY_ORDERS_AUTO不等于隐藏，才能显示 lizhi 2020-2-27
        	if(GEN_PAY_ORDERS_AUTO != '01'){
        		$("#BussTrdTransferOrder").attr("style","display:display;");
        	}
        }else{
        	$("#BussTrdTransferOrder").attr("style","display:none;");
        }
        // 差额清算账户联动 add by renping 20191031 start
		if($('#deliveryType').val()!=SETTMODE_DIF_SETT){
			$("#dealAcct").val("");
			$("#corAcct").val("");
			$("#dealAcctDiv").attr("style","display:none;");
			$("#corAcctDiv").attr("style","display:none;");
		} else {
			$("#dealAcctDiv").attr("style","display:display;");
			$("#corAcctDiv").attr("style","display:display;");
		}
        // 差额清算账户联动 add by renping 20191031 end
    }
	/*添加交割日与期限联动规则 gaopeng 2019/10/08 start*/
	/**
     * 交割日与期限根据交易日历和交易日期联动
     */
    var tradeDateOnChangeCalcTerm = function () {
        var tradeDate = $("#tradeDate").val();
        var settleDate = $("#settleDate").val();
        var calendarId = $('#calendarId').val();
        if (tradeDate  && calendarId) {
            var object={
            		tradeDate:tradeDate,
                    termType:$("#termType").val(),
                    calendarId:calendarId
            };
            Joyin.ajax(basePath + 'invest/trade/forwex/TrdForwexInvestTrade002/getsettleDate',object,
                    function (data) {
                        if (data && data.settleDate && data.settleDate) {
                        	$("#settleDate").val(data.settleDate).trigger('change');
                        }
           });
        }
    };
    /*添加交割日与期限联动规则 gaopeng 2019/10/08 end*/
    
    /**
     * 交易类型change事件
     */
	function excDealTypeChange(){
    	//交易类型
        var excDealType = $('#excDealType').val();
        //交易类型为 外币对
        //交易模式只可为询价交易，清算模式只可为双边全额清算，不可修改
        if(excDealType == EXCDEALTYPE_FGN_CY){
        	//交易模式 可为询价交易 
        	$("#dealMode").val(DEALMODE_OTC).prop("disabled", true).trigger("change");
        	//清算模式 只可为双边全额清算
        	$("#deliveryType").val(SETTMODE_BIL_SETT).prop("disabled", true).trigger("change");
        }else{
        	//交易模式 
        	$("#dealMode").val('').prop("disabled", false).trigger("change");
        	//清算模式 
        	$("#deliveryType").val('').prop("disabled", false).trigger("change");
        }
    }
    
    /**
     * 计算远期全价  = 即期汇率+远期点/10^报价精度
     */
    function calUnitFprice(){

    	// 调用共同方法  根据货币对得到即期汇率的精度
    	window.EtrForeingExchange.setDecimalPoint('curPair','unitCprice',0);
    	
        var entrustId = $('#entrustId').val();
//        if(entrustId!=null && entrustId != ''){
//        	
//        }else{
        	if($("#unitCprice").val()&&$("#unitInt").val()){
        		//获取报价精度
            	var precision = +$("#unitCprice").attr('data-textrange');
            	// 计算远期全价  = 即期汇率+远期点/10^报价精度
            	var unitFprice = +$("#unitCprice").val()+$("#unitInt").val()/Math.pow(10, precision);
            	$("#unitFprice").val(unitFprice).trigger("change");
        	}
//        }
        

    	// 调用共同方法  远期全价保留即期汇率对应小数位数+2位小数
    	window.EtrForeingExchange.setDecimalPoint('curPair','unitFprice',2);
    }
    
    
    //显示可用持仓  
    function showAvailableCash(){
    	//组合代码 或者  货币对 或者 交易币种 为空 
    	if(!$('#portfolioId').val()||!$('#ccy').val()||!$('#curPair').val()){
	       	 $("#proAvailableCash").val('');
	       	 $("#corCurPos").val('');
    	}else{
    		//交易币种可用持仓
        	getAssetAvailablePosition($('#ccy').val(),$('#proAvailableCash'));
        	//对应币种可用持仓
        	getAssetAvailablePosition($('#corCcy').val(),$('#corCurPos'));
    	}
    }
    
    //根据币种ccy,组合代码    获取组合可用持仓  加载到cash控件 
    function getAssetAvailablePosition(ccy,cash){
    	if($("#portfolioId").val()&& ccy){
			var data = {portfolioId:$('#portfolioId').val(),ccy:ccy};
            Joyin.ajaxJson(basePath + 'invest/trade/forwex/TrdForwexInvestTrade002/getProAvailableCash',data,
                    function(result) {
            	cash.val(result.cashT).trigger('blur');
            });
		}
    }
    
    /**
     * 根据交易币种金额  远期全价 货币对 交易币种
     * 获取 预计对应货币金额
     */
    function getFpriceAmt(){
    	//委托金额，全价，货币对，交易币种  为空时   预计对应货币金额 置空
    	if(!$('#prinAmt').val()||!$("#unitFprice").val()||
    			!$('#curPair').val()||!$('#ccy').val()){
	       	 $("#fpriceAmt").val('');
    	}else{
    		// ex 货币单位  用于根据货币对是否为JPY/CNY，而是否需要再乘以或者除以 ex 
    		//  若交易货币是货币对中的前一个币种，如货币对为JPY/CNY，预计对应货币金额=远期全价*交易货币金额/100，对于其他货币对，预计对应货币金额=远期全价*交易货币金额；
    		//  若交易货币是货币对中后一个币种，如货币对为JPY/CNY，预计对应货币金额=交易货币金额/远期全价*100
    		var ex = 1;
    		if(CURPAIR_JPY_CNY==$('#curPair').val()){
    			ex = 100;
    		}
    		var fpriceAmt = 0;
    		//分割货币对 获取两个币种
    		var curPairArr = $('#curPair').val().split("/");
    		// 交易方向为
    		if($('#ccy').val()==curPairArr[0]){
    			fpriceAmt =  JoyinMath.doDiv(JoyinMath.doMul($("#unitFprice").val(),$('#prinAmt').val()),ex);
    		}
    		if($('#ccy').val()==curPairArr[1]&&$("#price").val()!=0){
    			fpriceAmt = JoyinMath.doMul(JoyinMath.doDiv($('#prinAmt').val(),$("#unitFprice").val()),ex);
    		}
    		// 加载预计对应货币金额 保留两位小数
    		if(!isNaN(fpriceAmt)){
        		$("#fpriceAmt").val(fpriceAmt).trigger('change');
    		}
    		
    		//获取折美元金额
    		getUsdAmt();
    	}
    }
    
    /**
     * 获取折美元金额
     */
    function getUsdAmt(){
    	//交易币种为美元
    	if($('#ccy').val()==CCY_USD){
    		$('#usdAmt').val($('#prinAmt').val());
    	}
    	//对应币种为美元
    	if($('#corCcy').val()==CCY_USD){
    		$('#usdAmt').val($('#fpriceAmt').val());
    	}
    	
    }
    
    /**
     * 交易币种change事件
     */
    function ccyChange(){
    	//货币对
    	var curPair = $('#curPair').val();
    	//交易货币
    	var ccy = $("#ccy").val();
    	if(ccy&&curPair){
    		//分割货币对 获取两个币种
    		var curPairArr = curPair.split("/");
    		//交易币种为货币对的第一个币种
    		if(curPairArr[0]==ccy){
            	$('#corCcy').val(curPairArr[1]).trigger('change');
    		}
    		//交易币种为货币对的第二个币种
    		if(curPairArr[1]==ccy){
            	$('#corCcy').val(curPairArr[0]).trigger('change');
    		}
    	}else{
    		//对应币种 置空
        	$('#corCcy').val('').trigger('change');
    	}
    }
    
    /**
     * 计算交易费用
     */
    function autoCalFeeAmt(){
    	if(initFlag){
			return;
		}
    	//交易模式为本行结售汇时，交易费用不自动计算
    	if($('#dealMode').val()!=DEALMODE_BANK_EXCH&&$('#curPair').val()&&$('#ccy').val()&&$('#prinAmt').val()){
    		//交易费用 
    		var feeAmt = 0;
    		//交易类型为人民币   交易费用=人民币交易金额*0.1/10000
    		if($('#excDealType').val() == EXCDEALTYPE_RMB_FX){
    			// 获取人民币交易金额
        		// 交易币种为人民币
        		if(CCY_CNY==$("#ccy").val()){
        			feeAmt = $('#prinAmt').val();
        		}
        		// 对应币种为人民币
        		if(CCY_CNY==$('#corCcy').val()){
        			feeAmt = $("#fpriceAmt").val();
        		}
        		feeAmt = feeAmt*0.1/10000;
    		}
    		//交易类型为外币对  交易费用=交易货币金额*0.05/10000
    		if($('#excDealType').val() == EXCDEALTYPE_FGN_CY&&$('#prinAmt').val()){
    			feeAmt = $('#prinAmt').val()*0.05/10000;
    		}

        	$('#feeAmt').val(feeAmt.toFixed(2));
    	}else{
        	$('#feeAmt').val('');
    	}
    }
    
    /**
     * 计算定价日
     */
    function calPricedDate(){
        var deliveryType = $('#deliveryType').val();
        //清算模式为 差额清算
    	//交易日历 交割日不为空
    	if($('#calendarId').val()&&$('#settleDate').val()&&deliveryType==SETTMODE_DIF_SETT){
            var data = {calendarId:$('#calendarId').val(),settleDate:$('#settleDate').val()};
            Joyin.ajaxJson(basePath + 'invest/trade/forwex/TrdForwexInvestTrade002/getBeforeTwoWorkDay',data,
                    function(result) {
            	if(result!=null){
                	$('#pricedDate').val(result.pricedDate).prop("disabled", false);
            	}
            });
    	}
    }
    
    /**
     * 根据货币对获取交易日历
     * getCalendarIdByCurPair
     */
    function getCalendarIdByCurPair(){
    	window.EtrForeingExchange.getCalendarIdByCurPair('curPair','calendarId');
    }
    
    /**
     * 根据委托中是否是差额清算的关联控制
     */
    function isDifSquLinked(){
    	var isDifSqu = $("#isDifSqu").val();

        var itemIsDifSquY = [];
        var itemIsDifSquN = [];
        var SETT_MODE = JSON.parse(SETT_MODE_STR);
        for (var i in SETT_MODE){
            if (!SETT_MODE.hasOwnProperty(i)) continue;
            if(SETT_MODE[i].id == SETTMODE_DIF_SETT){
            	itemIsDifSquY.push(SETT_MODE[i].id)
            }else{
            	itemIsDifSquN.push(SETT_MODE[i].id)
            }
        }
        
    	// 差额清算为是
    	if(ISDIFSQU_Y==isDifSqu){
            $('#deliveryType').updateChosenArr({
                item: itemIsDifSquY,
                disabled: false
            });
    	}
    	// 差额清算为否
    	if(ISDIFSQU_N==isDifSqu){
            $('#deliveryType').updateChosenArr({
                item: itemIsDifSquN,
                disabled: false
            });
    	}
    }
    
    
    window.componentLoadCallback = function(isDone, pagePath, params){
        if(isDone){
            //----add by jjpei 20190718 风险试算----start----
            var riskParam = getRiskData();
            if (pageModel==PAGE_MODEL_NEW){
                riskParam.data =null;
            }
            window.BaseRiskMathComm.getparam(riskParam);
            //----add by jjpei 20190718 风险试算----end----
            if($("#isDifSquEx").val() == ISDIFSQU_Y || $('#deliveryType').val()!=SETTMODE_DIF_SETT){
	            if(window.BussTrdTransferOrder && window.BussTrdTransferOrder['bussTrdTransferOrder']) {
	                window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(tableInitData, true);
	            }
            }
            if(!$('#feeAmt').val()){
                autoCalFeeAmt();
            }
        	// 调用共同方法  根据货币对得到即期汇率的精度
            quotPre = window.EtrForeingExchange.setDecimalPoint('curPair','unitCprice',0);
        	// 调用共同方法  根据货币对得到定价日即期的汇率精度
        	window.EtrForeingExchange.setDecimalPoint('curPair','pricedDateRate',0);
        	// 调用共同方法  远期全价保留即期汇率对应小数位数+2位小数
        	window.EtrForeingExchange.setDecimalPoint('curPair','unitFprice',2);

//        	// 用于查询对手方账户
//            $("#trdInvestPersonInfo_counterId").change(function(){
//            	$('#portfolioId_cor').val($('#trdInvestPersonInfo_counterId').val()).trigger("change");
//            });
            
        }
      //add by gaopeng 20191204 远期差额清算到期画面控制  start
    	if($("#isDifSquEx").val() == ISDIFSQU_Y){
    		$("#portfolioId").attr("disabled", true);
    		$("#excDealType").attr("disabled", true);
        	$("#curPair").attr("disabled", true);
        	$("#ps").attr("disabled", true);
        	$("#calendarId").attr("disabled", true);
        	$("#tradeDate").attr("disabled", true);
        	$("#termType").attr("disabled", true);
    		$("#settleDate").attr("disabled", true);
        	$("#ourRole").attr("disabled", true);
        	$("#dealMode").attr("disabled", true);
        	$("#deliveryType").attr("disabled", true);
        	$("#pricedDate").attr("disabled", true);
     		$("#refRate").attr("disabled", true);
    		$("#invAim").attr("disabled", true);
        	$("#unitCprice").attr("disabled", true);
        	$("#unitInt").attr("disabled", true);
        	$("#unitFprice").attr("disabled", true);
        	$("#ccy").attr("disabled", true);
        	$("#prinAmt").attr("disabled", true);
    		$("#proAvailableCash").attr("disabled", true);
        	$("#corCcy").attr("disabled", true);
        	$("#fpriceAmt").attr("disabled", true);
        	$("#corCurPos").attr("disabled", true);
        	$("#usdAmt").attr("disabled", true);
        	$("#trdInvestPersonInfo_counterId").attr("disabled", true);
        	$("#trdInvestPersonInfo_counterTrader").attr("disabled", true);
        	$("#trdInvestPersonInfo_contactInfo").attr("disabled", true);
        	$("#feeAmt").attr("disabled", true);
    		$("#dealAcct").attr("disabled", true);
        	$("#corAcct").attr("disabled", true);
    	}
    	//add by gaopeng 20191204 远期差额清算到期画面控制 end
    };

    function tableInitData() {
        var object = $.serializeToObject('#submitForm');
        Joyin.ajaxSync(basePath + 'invest/trade/forwex/TrdForwexInvestTrade002/getEntity', object, function(result){
            entity = result;
        }, true);
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
    
    function saveOrSubmit(operateType) {
    	// 划款指令隐藏时，自动生成划款指令信息
    	generateOrders(function () {
	       var s;
	       if(BaseRiskMathComm) {
	            s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
	            if(!s && s != ''){
	               return;
	           }
	            if (window.BaseRiskMathComm.isCalcConditionChanged(getRiskData())){
	                return;
	            }
	        }
	        // 检查生成划款指令参数是否发生变化
	       if($("#isDifSquEx").val() == ISDIFSQU_Y || $('#deliveryType').val()!=SETTMODE_DIF_SETT){
	           if (window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()) {
	               return;
	           } 
	       }
	        /**
	         * 获取列表参数
	         */
	        var object = $.serializeToObject('#submitForm');
	        if (FileCommon) {
	            object.uploadFileList = FileCommon.getUploadFileInfo();
	        }
	        //报价精度
	        object.quotPre = quotPre;
	        object.portfolioId = $('#portfolioId').val();
	        object.operateType = operateType;
	        object.inputValue1 = PINAN_ASSET_TYPE_F14; 
	        if ($('#trdInvestPersonInfo_counterId').select2('data') && $('#trdInvestPersonInfo_counterId').select2('data')[0]) {
	            object.counterId=$('#trdInvestPersonInfo_counterId').select2('data')[0].id;
	        }
	        var obj = $.extend(true, {}, object);
	        if ($('#curPair').select2('data') && $('#curPair').select2('data')[0]) {
	        	obj.ccy=$('#curPair').select2('data')[0].id;
	        } 
	        if ($('#ccy').select2('data') && $('#ccy').select2('data')[0]) {
	        	obj.remark=$('#ccy').select2('data')[0].id;
	        }
			Joyin.ajaxJson(basePath + 'invest/trade/TrdCommonController/check', obj, function(result1) {
				Joyin.ajaxJson(basePath + 'invest/trade/forwex/TrdForwexInvestTrade002/check', object, function (result) {
					 //授信占用额度
//	                Joyin.ajaxJson(basePath + 'invest/trade/TrdCommonController/delLinesDeal',result1.data,function(result2) {});
					Joyin.confirm(result.data, function () { 
						var elementId;
						object.riskCreditListCommonDto = result1.data;
						if (operateType === 'save') {
							elementId = "TrdForwexInvestTrade002_btn_save";
							WorkFlow.doSaveList(basePath + 'invest/trade/forwex/TrdForwexInvestTrade002/save', object, elementId);
						} else {
							elementId = "TrdForwexInvestTrade002_btn_submit";
							WorkFlow.doSubmitList(basePath + 'invest/trade/forwex/TrdForwexInvestTrade002/save', object, elementId);
						}
					});
				});
			});
    	});
	}
     
    /**
     * 指令生成
     */
    $('#TrdForwexInvestTrade002_generate').click (function(){
        if($("#isDifSquEx").val() == ISDIFSQU_Y || $('#deliveryType').val()!=SETTMODE_DIF_SETT){
            $('#bussTrdTransferOrder_btn_generate').trigger('click');
        }
    });
    //add by lizhi 2020-2-26 划款指令是否自动生成  start
    var generateOrders = function (callback) {
    	// 如果还款指令设置为隐藏，且页面参数需要自动生成指令（页面存在不生成指令情况）
    	if(GEN_PAY_ORDERS_AUTO =='01'){
    		if($("#isDifSquEx").val() == ISDIFSQU_Y || $('#deliveryType').val()!=SETTMODE_DIF_SETT){
    			$('#bussTrdTransferOrder_btn_generate').click();
    		}
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
    $(function () {
        /**
         * 组合代码change事件
         */
    	$("#portfolioId").change(function(){
    		showAvailableCash();
        });
    	
    	isDifSquLinked();
    	
    	/**
    	 * 清算模式change事件
    	 */
        $("#deliveryType").change(function(){
        	deliveryTypeChange();
        });
        
        /**
         * 交易类型change事件
         */
        $("#excDealType").change(function(){
        	excDealTypeChange();
        });
        
    	/**
         * 货币对change事件
         */
        $("#curPair").on('change',function(){
        	// 调用共同方法  根据货币对得到即期汇率的精度
        	quotPre = window.EtrForeingExchange.setDecimalPoint('curPair','unitCprice',0);
        	// 调用共同方法  根据货币对得到定价日即期的汇率精度
        	window.EtrForeingExchange.setDecimalPoint('curPair','pricedDateRate',0);
        	// 调用共同方法  远期全价保留即期汇率对应小数位数+2位小数
        	window.EtrForeingExchange.setDecimalPoint('curPair','unitFprice',2);
        	// 计算远期全价
        	calUnitFprice();
        	// 联动获取可用持仓
    		showAvailableCash();
    		// 自动计算交易费用
    		autoCalFeeAmt();
    		// 得到交易日历
    		getCalendarIdByCurPair();
        });
        
        /**
         * 即期汇率change事件
         */
        $('#unitCprice').change(function(){
        	calUnitFprice();
        });
        /*添加交割日与期限联动规则 gaopeng 2019/10/08 start*/
        /**
         * 期限change事件
         */
        $('#termType').change(function(){
        	if($('#termType').val()==""||$('#tradeDate').val()==""||$('#calendarId').val()==""){
        		$("#settleDate").val("").trigger('change');
        	}
        	if($('#termType').val()!="BROKEN"){
        		tradeDateOnChangeCalcTerm();
        	}else{
        		$("#settleDate").val("").trigger('change');
        	}
        });
        
        /**
         * 交易日期change事件
         */
        $('#tradeDate').change(function(){
        	if($('#termType').val()==""||$('#tradeDate').val()==""||$('#calendarId').val()==""){
        		$("#settleDate").val("").trigger('change');
        	}
        	if($('#termType').val()!="BROKEN"){
        		tradeDateOnChangeCalcTerm();
        	}else{
        		$("#settleDate").val("").trigger('change');
        	}
        });
       
        /**
         * 交易日历change事件
         */
        $('#calendarId').change(function(){
        	if($('#termType').val()==""||$('#tradeDate').val()==""||$('#calendarId').val()==""){
        		$("#settleDate").val("").trigger('change');
        	}
        	if($('#termType').val()!="BROKEN"){
        		tradeDateOnChangeCalcTerm();
        	}else{
        		$("#settleDate").val("").trigger('change');
        	}
        });
        /*添加交割日与期限联动规则 gaopeng 2019/10/08 end*/
        /**
         * 远期点change事件
         */
        $('#unitInt').change(function(){
        	calUnitFprice();
        });

        /**
         * 币种change事件
         */
        $("#ccy").change(function(){
        	//币种change事件
        	ccyChange();
        	//联动获取可用持仓
        	showAvailableCash();
        	//计算对应币种金额
        	getFpriceAmt();
        	//自动计算交易费用
        	autoCalFeeAmt();
        });
        
        /**
         * 交易币种金额change事件
         */
        $('#prinAmt').on('change',function(){
        	//计算对应币种金额
        	getFpriceAmt();
        	//自动计算交易费用
        	autoCalFeeAmt();
        });

        /**
         * 交易模式change事件
         */
        $('#dealMode').on('change',function(){
        	//自动计算交易费用
        	autoCalFeeAmt();
        });
        
        /**
         * 远期全价change事件
         */
        $('#unitFprice').change(function(){
        	//计算对应币种金额
        	getFpriceAmt();
        	//自动计算交易费用
        	autoCalFeeAmt();
        });
        // 是否显示划款指令
        if(GEN_PAY_ORDERS_AUTO=='01'){
        	$('#BussTrdTransferOrder').hide() ;
        }else{
        	$('#BussTrdTransferOrder').show() ;
        }
        /**
         * 保存
         */
        $('#TrdForwexInvestTrade002_btn_save').click(function () {
            saveOrSubmit('save');
        });
        /**
         * 提交
         */
        $('#TrdForwexInvestTrade002_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
        //风险试算
        $('#TrdForwexInvestTrade002_btn_risk').click(function () {
			//-----------add by jjpei 2020-01-07 风险新版本 start----------
			var object = $.serializeToObject('#submitForm', true);
			window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/1/RSK002/F14', object,getRiskData());
			//-----------add by jjpei 2020-01-07 风险新版本 end----------
        })
        
        /**
         * 交割日change事件
         */
        $("#settleDate").change(function(){
        	calPricedDate();
        });
        
        /**
         * 委托带出界面 ,需要置灰的控件
         */
        //委托代码
        var entrustId = $('#entrustId').val();
        if(entrustId!=null && entrustId != ''){
        	// 组合代码
        	$('#portfolioId').prop("disabled", true);
        	// 交易类型
        	$('#excDealType').prop("disabled", true);
        	// 货币对
        	$('#curPair').prop("disabled", true);
        	// 交易方向
        	$('#ps').prop("disabled", true);
        	// 期限品种
        	if($('#termType').val()){
            	$('#termType').prop("disabled", true);
        	}
        	// 投资目的
        	$('#invAim').prop("disabled", true);
        	// 交易货币
        	$('#ccy').prop("disabled", true);
        	// 对应货币
        	$('#corCcy').prop("disabled", true);
        	// 本方账户
        	$('#dealAcct').prop("disabled", true);
//        	// 对手方账户
//        	$('#corAcct').prop("disabled", true);
        }
        
        //根据清算模式 置灰按钮
        deliveryTypeChange();
        showAvailableCash();
        //交易类型
        var excDealType = $('#excDealType').val();
        //交易类型为 外币对
        //交易模式只可为询价交易，清算模式只可为双边全额清算，不可修改
        if(excDealType == EXCDEALTYPE_FGN_CY){
        	//交易模式 可为询价交易 
        	$("#dealMode").val(DEALMODE_OTC).prop("disabled", true).trigger("change");
        	//清算模式 只可为双边全额清算
        	$("#deliveryType").val(SETTMODE_BIL_SETT).prop("disabled", true).trigger("change");
        }else{
        	//交易模式 
        	$("#dealMode").prop("disabled", false).trigger("change");
        	//清算模式 
        	$("#deliveryType").prop("disabled", false).trigger("change");
        }
        
        // 交易方向为到期  清算模式为差额清算
        if($("#isDifSquEx").val() == ISDIFSQU_Y){
        	$("#difsqu").attr("style","display:block;");
        } else if($('#deliveryType').val() == SETTMODE_DIF_SETT){
        	$("#BussTrdTransferOrder").attr("style","display:none;");
        }
        initFlag=false;
    });
	
}());