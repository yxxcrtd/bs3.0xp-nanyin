$(function() {
    var fileArray =[];
    var entrustNum; // 委托金额
    var entrustIntRate; // 委托所输利率
    var getriskdata = function () {
        var obj = {timePoint:MY_SUP_TIME_TYPE_02,valDate:$('#tradeDate').val(),portfolioCode:$('#portfolioId').val(),tradeAmount:$('#prin').val(),assetType:$('#paBussType').val(),isEnabled:'01',tradeDirection:$('#busiPs').val()}
        var finType = '';
        var finMng = '';
        if($('#busiPs').val() == BACK_LEND_DIRECTION_01){
            if($('#paBussType').val() == BANK_LENDING_TYPE_02){
                 obj.assetType = FINPROD_TYPE_F08;//选特拆也当作拆入
            }else if(!$('#paBussType').val() || $('#paBussType').val() == ''){
                finType = "paBussType";
                finMng = 'TrdLendInvestTrade002.paBussType';
                obj.assetType = null;
            }
            else{
                obj.assetType = FINPROD_TYPE_F08;
            }
        }else if($('#busiPs').val() == BACK_LEND_DIRECTION_02){
            obj.assetType = FINPROD_TYPE_F09;
        }else if(finMng == '' && (!$('#busiPs').val() || $('#busiPs').val() == '')){
            finMng = 'TrdDepositInvestTrade001.finprodType';
        }else{

        }
        var object = {valDate:['tradeDate','TrdLendInvestTrade002.tradeDate'],portfolioCode:['portfolioId','TrdBondInvestTrade002.portfolioId'],tradeAmount:['prin','TrdLendInvestTrade002.prin'],assetType:[finType,finMng],tradeDirection:['busiPs','TrdLendInvestTrade002.ps'],data:obj}
        return object;
    }

    function saveOrSubmit(operateType) {
    	// 划款指令隐藏时，自动生成划款指令信息
    	generateOrders(function () {
	    	//---add by 裴家俊 2019/03/25-----start---
	    	 var s;
	         if(BaseRiskMathComm) {
	             s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
	             if(!s && s != ''){
	                 return;
	             }
	             if (window.BaseRiskMathComm.isCalcConditionChanged(getriskdata())){
	                 return;
	             }
	         }
	       //---add by 裴家俊 2019/03/25-----end---
	        // 检查生成划款指令参数是否发生变化
	        if (window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()) {
	            return;
	        }
	        
	        var object = $.serializeToObject('#submitForm', true);
	        object.pageModel = pageModel;
	        object.operateType = operateType;
	        object.isGuarantee = $('#portfolioId').select2('data')[0].object; // 是否保本
	        object.entrustNum = entrustNum; // 委托金额
	        object.entrustIntRate = entrustIntRate; // 委托所输利率
	        object.avalibleCash = $("#bussTrdAvailableCashQuery_avalibleCash").val(); // 组合T日可用现金
	        if(FileCommon) {
	            object.fileArray = FileCommon.getUploadFileInfo();
	        }
	        object.inputValue1 = PINAN_ASSET_TYPE_F08;
	        
	        //modify by wj 20200429 TISHKBS-310   拆借交易提交报错 页面无ps属性
//	        object.inputValue2 = $('#ps').val(); 

	        object.inputValue2 = $('#busiPs').val(); 
	        object.ps = $('#busiPs').val(); 
	        
	        if ($('#trdInvestPersonInfo_counterId').select2('data') && $('#trdInvestPersonInfo_counterId').select2('data')[0]) {
	            object.counterType = $('#trdInvestPersonInfo_counterId').select2('data')[0].object;
	            object.counterId = $('#trdInvestPersonInfo_counterId').select2('data')[0].id;
	        }
	        object.prinAmt = $('#prin').val();
	        object.inputValue3 = object.paBussType;
	        Joyin.ajaxJson(basePath + 'invest/trade/TrdCommonController/check', object, function(result1) {
		        Joyin.ajaxJson(basePath + 'invest/trade/lend/TrdLendInvestTrade002/check', object, function(result) {
		        	object.riskCreditListCommonDto = result1.data;
		        	Joyin.confirm(result.data, function() {
		                if(operateType === 'save') {
		                    WorkFlow.doSaveList(basePath + 'invest/trade/lend/TrdLendInvestTrade002/save', object, "TrdLendInvestTrade002_btn_save");
		                }
		                else {
		                    WorkFlow.doSubmitList(basePath + 'invest/trade/lend/TrdLendInvestTrade002/save', object, "TrdLendInvestTrade002_btn_submit");
		                }
		            });
		        });
	        });
    	});
    };
    var checkAndAssign = function(el, value, editable){
        if(value != null && value != ''){
            $(el).val(value).trigger('change').trigger('blur');
            if(!editable){
                $(el).attr("disabled",true);
            }
        }
    };
    /**
     * 委托带值，若该项目已经有值，表示是流程中或者查看数据，不需要覆盖委托的值
     */
    var checkAndAssignForEntrust = function(el, value, editable){
        if ($(el).val()) {
            return;
        }

        if(value != null && value != ''){
            $(el).val(value).trigger('change').trigger('blur');
            if(!editable){
                $(el).attr("disabled",true);
            }
        }
    };
    var checkNotEmpty = function (arguments) {
        for(var i = 0; i < arguments.length; i++){
            if(arguments[i] == null || arguments[i] == ''){
                return false;
            }
        }
        return true;
    }
    var etrEntrust;
    //画面初始化
    var init = function(){
        var entrustId = $("#entrustId").val();
        if(entrustId != ''){
            // 2、在交易查询页面点击【交易录单】按钮进入拆借指令交易新建页面场合时：
            // 基本要素信息由投资委托带出，其中有：委托代码、组合代码、交易日历、交易方向、币种字段等，不可编辑
            Joyin.ajaxJson(basePath + 'invest/trade/bond/TrdBondInvestTrade002/getEntrustInfo', {entrustId:entrustId},function (data) {
                if(data != null && data.etrEntrustAdd != null) {
                    var etrEntrustAdd = data.etrEntrustAdd;
                    checkAndAssign("#paBussType", etrEntrustAdd.assetType1);
                    checkAndAssign("#term", etrEntrustAdd.termDays, true);
//                    if (etrEntrustAdd.vdate) {
//                        checkAndAssignForEntrust("#vdate", Date.ofDate(etrEntrustAdd.vdate).format("yyyy-MM-dd"), true);
//                    }
                    if (etrEntrustAdd.mdate) {
                        checkAndAssignForEntrust("#mdate", Date.ofDate(etrEntrustAdd.mdate).format("yyyy-MM-dd"), true);
                    }
                }
                if(data != null && data.etrEntrust != null){
                    etrEntrust = data.etrEntrust;
                    checkAndAssign("#portfolioId", etrEntrust.portfolioId);
                    $('#portfolioId').textSelect('setValue', etrEntrust.portfolioId);
                    // 交易方向
                    if (etrEntrust.finprodType2 == FINPROD_TYPE_TG_F08) {
                        checkAndAssign("#busiPs", BACK_LEND_DIRECTION_01);
                    } else if (etrEntrust.finprodType2 == FINPROD_TYPE_TG_F09) {
                        checkAndAssign("#busiPs", BACK_LEND_DIRECTION_02);
                    } else if (etrEntrust.finprodType2 == FINPROD_TYPE_TG_F25) {
                        checkAndAssign("#busiPs", BACK_LEND_DIRECTION_01);
                    } else {
                        checkAndAssign("#busiPs", etrEntrust.ps);
                    }
                    checkAndAssign("#calendarId", etrEntrust.calendarId);
                    $('#calendarId').textSelect('setValue', etrEntrust.calendarId);
                    checkAndAssign("#ccy", etrEntrust.ccy);
                    checkAndAssignForEntrust("#prin", etrEntrust.entrustAmt, true);
                    entrustNum = etrEntrust.entrustAmt;
                    
                    if (etrEntrust.price) {
                        checkAndAssignForEntrust("#intRate", etrEntrust.price, true);
                        entrustIntRate = etrEntrust.price;
                    }
                }
            });
        }
        $("#tradeDate").on('change', function () {
            updateCash();
        });
        $("#calendarId").on('change', function () {
            updateCash();
        });
        $("#ccy").on('change', function () {
            updateCash();
            updateSecAcctBalance();
        });
        /**
         * 1.5划款信息与OPICS信息显示
         * 根据选择的组合是否保本来动态显示划款与OPICS信息
         */
        $("#portfolioId").on('change', function () {
            var isGuarantee = $('#portfolioId').select2('data')[0].object;
//            if(isGuarantee == YES_OR_NO_Y) {
//                $("#bussTrdTransferOrder_div").hide();
//                // TODO OPICS信息隐藏
//            } else {
//                $("#bussTrdTransferOrder_div").show();
//                // TODO OPICS信息展示
//            }
            updateCash();
            updateSecAcctBalance();
        });
        /**
         * 1.3交易方向
         * 选择拆入时：【组合T日可用现金】、【组合T+1日可用现金】、【托管户剩余现金】字段隐藏
         * 选择拆出时：【组合T日可用现金】、【组合T+1日可用现金】、【托管户剩余现金】字段显示
         */
        $("#busiPs").on("change", function () {
            var busiPs = $("#busiPs").val();
            if(busiPs == BACK_LEND_DIRECTION_01){
                window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'].hideCash();
            }
            else if(busiPs == BACK_LEND_DIRECTION_02){
                window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'].showCash(true);
                $("#bussTrdAvailableCashQuery_div_channelCash").hide();
            }
        });
        /**
         * 1.1拆借类型
         * 选择【普通拆借】时，交易方向可选，包括拆入和拆出；
         * 选择【特殊目的载体】时，交易方向默认是拆入且不可选；
         */
        $("#paBussType").on("change", function () {
            var paBussType = $("#paBussType").val();
            if (paBussType == BANK_LENDING_TYPE_02) {
                checkAndAssign("#busiPs", BACK_LEND_DIRECTION_01);
                $("#busiPs").trigger("change");
            }
            else {
                // 非查看模式
                if(!(pageModel != null && typeof pageModel !== 'undefined' && (pageModel === PAGE_MODEL_REFERENCE
                        || (typeof isReference !== 'undefined' && isReference.toString() === 'true')))) {
                    $("#busiPs").attr("disabled", false);
                }
                if ($("#entrustId").val()) {
                    $("#busiPs").attr("disabled", true);
                }
            }
        });
        $("#vdate").on("change", function() {
            updateTerm();
        });
        $("#mdate").on("change", function() {
            updateTerm();
        });
        $("#term").on("change", function() {
            updateMdate();
        });
        /**
         * 根据起息日和到期日自动计算期限
         */
        var updateTerm = function() {
            var vdate = $("#vdate").val();
            var mdate = $("#mdate").val();
            if (vdate && mdate) {
                var paramObj = {};
                paramObj.vdate = vdate;
                paramObj.mdate = mdate;
                Joyin.ajax(basePath + 'component/trade/BussTrdCommon/calcTerm', paramObj, function(result) {
                    $("#term").val(result);
                });
            } else {
                $("#term").val("");
            }
        };
        /**
         * 根据起息日和期限自动计算到期日
         */
        var updateMdate = function() {
            var vdate = $("#vdate").val();
            var term = $("#term").val();
            if (vdate && term) {
                var paramObj = {};
                paramObj.vdate = vdate;
                paramObj.term = term;
                Joyin.ajax(basePath + 'component/trade/BussTrdCommon/calcMdate', paramObj, function(result) {
                    $("#mdate").val(result);
                });
            } else {
                $("#mdate").val("");
            }
        };
        /**
         * 1.3 查询组合T日可用现金
         * 1.4 查询组合T+1日可用现金
         */
        var updateCash = function(){
            window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'].updateCash(function () {
                return {
                    'tradeDate': $("#tradeDate").val(),
                    'calendarId': $("#calendarId").val(),
                    'ccy': $("#ccy").val(),
                    'portfolioId': $("#portfolioId").val()
                }
            });
        };
        /**
         * 1.5 查询托管户剩余现金
         */
        var updateSecAcctBalance = function(){
            window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'].updateSecAcctBalance(function () {
                return {
                    'ccy': $("#ccy").val(),
                    'tradeDate': $("#tradeDate").val(),
                    'portfolioId': $("#portfolioId").val()
                }
            });
        };
        window.componentLoadCallback = function(isDone, pagePath, params){
            if(isDone){
                var riskparam = getriskdata();
                if (pageModel==PAGE_MODEL_NEW){
                    riskparam.data =null;
                }
                window.BaseRiskMathComm.getparam(riskparam);
                window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                    var finprodType2 = "";
                    if ($("#busiPs").val() == BACK_LEND_DIRECTION_01) {
                        finprodType2 = FINPROD_TYPE_TG_F08;
                    } else {
                        finprodType2 = FINPROD_TYPE_TG_F09;
                    }
                    return {
                        'isTotalTrade':YES_OR_NO_N
                        ,'bankLendingType':$("#paBussType").val()
                        ,'portfolioId':$("#portfolioId").val()
                        ,'deal':{
                            'finprodType2' : finprodType2,
                            'tradeDate' : $('#tradeDate').val(),
                            'ps' : PS_T01,
                            'ccy' : $('#ccy').val(),
                            'tradeAmt' : $("#prin").val(),
                            'bankLendingType' : $("#paBussType").val(),
                            'counterId' : $("#trdInvestPersonInfo_counterId").val()
                        }
                    };
                });
//                updateSecAcctBalance();
//                updateCash();
                window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'].showCash(true);
                $("#bussTrdAvailableCashQuery_div_channelCash").hide();
                if(entrustId != ''){
                    if(etrEntrust){
                        window.TrdInvestPersonInfo002['trdInvestPersonInfo'].setEntrust(function () {
                            return etrEntrust;
                        });
                    }
                }

                // 触发画面联动处理
                $("#portfolioId").trigger("change");
                $("#busiPs").trigger("change");
                $("#paBussType").trigger("change");
            }
        }
    };
    
    //add by lizhi 2020-2-26 划款指令是否自动生成  start
    var generateOrders = function (callback) {
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
        	$('#bussTrdTransferOrder_div').hide() ;
        }else{
        	$('#bussTrdTransferOrder_div').show() ;
        }
        // 绑定按钮事件
        $('#TrdLendInvestTrade002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TrdLendInvestTrade002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
      //风险试算（资产代码没有，待解决）
        $('#TrdLendInvestTrade002_btn_risk').click(function(){
            //window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
            //-----------add by jjpei 2020-01-07 风险新版本 start----------
            var object = $.serializeToObject('#submitForm', true);
             window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/1/RSK002/F08', object,getriskdata());
            //-----------add by jjpei 2020-01-07 风险新版本 end----------
             window.BaseRiskMathComm.initBaseRiskMathCommPageCus(getriskdata());

        })
    });
}());