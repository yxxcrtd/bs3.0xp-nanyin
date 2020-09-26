$(function() {
    var fileArray =[];
    var entrustNum; // 委托金额
    var entrustIntRate; // 委托所输利率
    var getriskdata = function () {
        var finType = '';
        var finMng = '';
        var tradeAmount = $('#optFee').val() == null || '' ? $('#optFee').val() : 0;
        var obj = {timePoint:MY_SUP_TIME_TYPE_02,valDate:$('#tradeDate').val(), portfolioCode:$('#portfolioId').val(), tradeAmount:tradeAmount, assetType:FINPROD_TYPE_TG_F35,isEnabled:'01', tradeDirection:$('#ps').val()};
        var object = {valDate:['tradeDate','TrdOptionTrade001.tradeDate'],portfolioCode:['portfolioId','TrdBondInvestTrade002.portfolioId'],tradeAmount:['optFee','TrdOptionTrade002.optFee'],assetType:[finType,finMng],tradeDirection:['ps','TrdOptionTrade001.ps'],data:obj};
        return object;
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
	             if (window.BaseRiskMathComm.isCalcConditionChanged(getriskdata())){
	                 return;
	             }
	         }
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
	        Joyin.ajaxJson(basePath + 'invest/trade/option/TrdOptionTrade002/check', object, function(result) {
	            Joyin.confirm(result.data, function() {
	                if(operateType === 'save') {
	                    WorkFlow.doSaveList(basePath + 'invest/trade/option/TrdOptionTrade002/save', object, "TrdOptionTrade002_btn_save");
	                }
	                else {
	                    WorkFlow.doSubmitList(basePath + 'invest/trade/option/TrdOptionTrade002/save', object, "TrdOptionTrade002_btn_submit");
	                }
	            });
	        });
    	});
    }
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
    };
    /**
     * 查询组合T+1日可用现金
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

    var etrEntrust;
    //画面初始化
    var init = function() {
        if(entrustId != null && entrustId != ''){
            $('#portfolioId').attr("disabled", true);
            $('#targetId').attr("disabled", true);
            $('#contractType').attr("disabled", true);
            $('#ps').attr("disabled", true);
            $('#ccy').attr("disabled", true);
            $('#exerciseDate').attr("disabled", true);
            $('#exerciseType').attr("disabled", true);
        }

        $("#bussTrdAvailableCashQuery_div").hide();
        /**
         * 1.5划款信息
         * 根据选择的组合是否保本来动态显示划款
         */
        $("#portfolioId").on('change', function () {
            var isGuarantee = $('#portfolioId').select2('data')[0].object;
            var optFeePayType = $('#optFeePayType').val();
            if(isGuarantee == YES_OR_NO_Y || optFeePayType == OPT_FEE_PAY_TYPE_02) {
                $("#bussTrdTransferOrder_div").hide();
            } else {
            	// 系统参数GEN_PAY_ORDERS_AUTO不等于隐藏，才能显示 lizhi 2020-2-27
            	if(GEN_PAY_ORDERS_AUTO != '01'){
            		$("#bussTrdTransferOrder_div").show();
            	}
            }
            updateCash();
        });

        /**
         * 合约类型
         * 选择二元看涨、看涨、看跌时：【执行价格】必填、【敲出价格】、【高障碍价】、【高行权价】、【低障碍价】、【低行权价】不可填
         * 选择单鲨看涨时：【执行价格】、【敲出价格】必填、【高障碍价】、【高行权价】、【低障碍价】、【低行权价】不可填
         * 选择双鲨看涨时：【高障碍价】、【高行权价】、【低障碍价】、【低行权价】必填，【执行价格】、【敲出价格】不可填
         */
        $("#contractType").on("change", function () {
            var contractType = $("#contractType").val();
            if(contractType == null || contractType == ''){
                $("#exePrice").attr("required", true);
                $("#exePrice").attr("disabled", false);
            }
            else if(contractType == STRUCTURE_TYPE_01 || contractType == STRUCTURE_TYPE_02 || contractType == STRUCTURE_TYPE_03){
                $("#exePrice").attr("required", true);
                $("#knockPrice").attr("disabled", true);
                $("#lowHindPrice").attr("disabled", true);
                $("#lowExercPrice").attr("disabled", true);
                $("#highHindPrice").attr("disabled", true);
                $("#highExercPrice").attr("disabled", true);

                $("#knockPrice").val("");
                $("#lowHindPrice").val("");
                $("#lowExercPrice").val("");
                $("#highHindPrice").val("");
                $("#highExercPrice").val("");
                $("#exePrice").attr("disabled", false);
                $("#knockPrice").attr("required", false);
                $("#lowHindPrice").attr("required", false);
                $("#lowExercPrice").attr("required", false);
                $("#highHindPrice").attr("required", false);
                $("#highExercPrice").attr("required", false);

            }
            else if(contractType == STRUCTURE_TYPE_04){
                $("#exePrice").attr("required", true);
                $("#knockPrice").attr("required", true);
                $("#lowHindPrice").attr("disabled", true);
                $("#lowExercPrice").attr("disabled", true);
                $("#highHindPrice").attr("disabled", true);
                $("#highExercPrice").attr("disabled", true);

                $("#lowHindPrice").val("");
                $("#lowExercPrice").val("");
                $("#highHindPrice").val("");
                $("#highExercPrice").val("");
                $("#exePrice").attr("disabled", false);
                $("#knockPrice").attr("disabled", false);
                $("#lowHindPrice").attr("required", false);
                $("#lowExercPrice").attr("required", false);
                $("#highHindPrice").attr("required", false);
                $("#highExercPrice").attr("required", false);
            }
            else if(contractType == STRUCTURE_TYPE_05){
                $("#exePrice").attr("disabled", true);
                $("#knockPrice").attr("disabled", true);
                $("#lowHindPrice").attr("required", true);
                $("#lowExercPrice").attr("required", true);
                $("#highHindPrice").attr("required", true);
                $("#highExercPrice").attr("required", true);

                $("#exePrice").val("");
                $("#knockPrice").val("");
                $("#exePrice").attr("required", false);
                $("#knockPrice").attr("required", false);
                $("#lowHindPrice").attr("disabled", false);
                $("#lowExercPrice").attr("disabled", false);
                $("#highHindPrice").attr("disabled", false);
                $("#highExercPrice").attr("disabled", false);
            }
            star();
        });

        /**
         * 期权费类型联动
         * 选择先付期权费时：【期权费率】、【期权费】必输
         * 选择后付期权费时：【期权费率】必输、期权费置灰置空，不可输入
         */
        $("#optFeePayType").on("change", function(){
            var optFeePayType = $("#optFeePayType").val();
            if(optFeePayType == null || optFeePayType == ''){
                $("#optFee").attr("disabled", false);
                $("#optFee").attr("required", true);
            }
            if(optFeePayType == OPT_FEE_PAY_TYPE_01){
                $("#payDate").attr("required", true);
                $("#payDate").attr("disabled", false);
                if($("#payDate").val() == null || $("#payDate").val() == ''){
                    $("#payDate").val(platDate);
                }
                $("#optFeeRate").attr("required", true);
                $("#optFee").attr("required", true);
                $("#optFee").attr("disabled", false);
                // 系统参数GEN_PAY_ORDERS_AUTO不等于隐藏，才能显示 lizhi 2020-2-27
            	if(GEN_PAY_ORDERS_AUTO != '01'){
            		$("#bussTrdTransferOrder_div").show();
            	}
            }
            else if(optFeePayType == OPT_FEE_PAY_TYPE_02){
                $("#bussTrdTransferOrder_div").hide();
                $("#payDate").attr("required", false);
                $("#payDate").attr("disabled", true);
                $("#payDate").val("");
                $("#optFeeRate").attr("required", true);
                $("#optFee").val("");
                $("#optFee").attr("disabled", true);
                $("#optFee").attr("required", false);
            }
            star();
        });

        $("#tradeDate").on("change", function() {
            updateCash();
        });
        $("#vdate").on("change", function() {
            updateTerm();
        });
        $("#term").on("change", function() {
            updateMdate();
        });
        $("#calendarId").on("change", function() {
            updateMdate();
            updateCash();
        });

        /**
         * 画星星
         */
        function star() {
            // 加红星星
            // 查找label
            var _findPrevLabel = function (element, level) {
                var $el = element.prev();
                if (!$el.is('label') && level > 0) {
                    $el = $el.find('label');
                    if ($el.length === 0) {
                        return _findPrevLabel(element.parent(), level - 1);
                    }
                }
                return $el;
            };

            // 非查看模式
            if (typeof pageModel !== 'undefined'
                && (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_NEW)) {
                $('.must').each(function () {
                    $(this).remove();
                });
                $('*[required]').each(function () {
                    var $el = $(this);
                    var $target = _findPrevLabel($el, 3);
                    if ($target.find('> .must').length === 0) {
                        $target.append($('<i class="must">*</i>'));
                    }
                });
            }
        }

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
            var bondLendTerm = $('#term').val();
            var re = /^[0-9]*$/;
            if(bondLendTerm && re.test(bondLendTerm)){
                var vdate = $('#vdate').val();//起息日
                var calendarId = $("#calendarId").val(); //交易日历
                if(vdate&&calendarId){
                    var d1 = new Date(vdate).getTime();
                    var day = parseInt(bondLendTerm*24*60*60*1000);
                    var mdate = new Date(d1+day);
                    mdate=mdate.format("yyyy-MM-dd");
                    Joyin.ajaxJson(basePath + 'invest/trade/bondlend/TrdBondLendInvestTrade002/isDealCalendarHoliday', {
                        calendarId: calendarId,
                        mpayDate: mdate
                    }, function (date) {
                        if(date){
                            Joyin.ajax(basePath + 'invest/trade/bondlend/TrdBondLendInvestTrade002/getNextWorkDay', {
                                'calendarId': calendarId,
                                'tradeDate': mdate
                            }, function (date) {
                                $("#mdate").val(date).trigger("change");
                            });
                        }else{
                            $("#mdate").val(mdate).trigger('change');
                        }
                    },false);
                }else{
                    $("#mdate").val('').trigger('change');
                }
            }
        };

        window.componentLoadCallback = function(isDone, pagePath, params){
            if(isDone){
                var riskparam = getriskdata();
                if (pageModel==PAGE_MODEL_NEW){
                    riskparam.data =null;
                }
                window.BaseRiskMathComm.getparam(riskparam);
                window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                    if (!$("#optFee").val()) {
                        Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("TrdOptionTrade002.optFee")));
                        return;
                    }
                    if (!$("#payDate").val()) {
                        Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("TrdOptionTrade002.payDate")));
                        return;
                    }
                    return {
                        'isTotalTrade':YES_OR_NO_Y
                        ,'bankLendingType':$("#contractType").val()
                        ,'portfolioId':$("#portfolioId").val()
                        ,'deal':{
                            'finprodType2' : FINPROD_TYPE_TG_F35,
                            'tradeDate' : $('#payDate').val(),
                            'ps' : $('#ps').val(),
                            'ccy' : $('#ccy').val(),
                            'tradeAmt' : $("#optFee").val(),
                            'bankLendingType' : $("#contractType").val(),
                            'counterId' : $("#trdInvestPersonInfo_counterId").val()
                        }
                    };
                });
                updateCash();
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
        };

        // 触发画面联动处理
        $("#contractType").trigger("change");
        $("#optFeePayType").trigger("change");

    };
    //add by lizhi 2020-2-26 划款指令是否自动生成  start
    var generateOrders = function (callback) {
    	// 如果还款指令设置为隐藏，且页面参数需要自动生成指令（页面存在不生成指令情况）
    	if(GEN_PAY_ORDERS_AUTO =='01'){
    		// 根据选择的组合是否保本来动态显示划款指令
    		var isGuarantee = $('#portfolioId').select2('data')[0].object;
            var optFeePayType = $('#optFeePayType').val();
            if(!(isGuarantee == YES_OR_NO_Y || optFeePayType == OPT_FEE_PAY_TYPE_02)) {
            	// 期权费类型 不等于后付期权费，才需要生成指令
            	var optFeePayType = $("#optFeePayType").val();
                if(optFeePayType != OPT_FEE_PAY_TYPE_02){
                	$('#bussTrdTransferOrder_btn_generate').click();
                }
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
        $('#TrdOptionTrade002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TrdOptionTrade002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
      //风险试算（资产代码没有，待解决）
        $('#TrdOptionTrade002_btn_risk').click(function(){
            //window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
            //-----------add by jjpei 2020-01-07 风险新版本 start----------
            var object = $.serializeToObject('#submitForm', true);
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/1/RSK002/F18', object,getriskdata());
            //-----------add by jjpei 2020-01-07 风险新版本 end----------
            window.BaseRiskMathComm.initBaseRiskMathCommPageCus(getriskdata());

        })
    });
}());