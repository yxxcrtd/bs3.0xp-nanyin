$(function(){
    var fileArray =[];
    var entrustId = '';
    var entity = '';
    var isOpics = '';
    var isHoliday = '';
    var initFlag = true;
    //---add by jjpei 20190718----start---获取风险试算画面要素与Label提示文本
    var getRiskData = function () {
        var obj = {timePoint:MY_SUP_TIME_TYPE_02,valDate:$('#tradeDate').val(),portfolioCode:$('#portfolioId').val(),assetType:FINPROD_TYPE_F13,tradeAmount:$('#nDealCurAmt').val(),isEnabled:USE_STATUS_01,tradeDirection:$('#ps').val()}
        var object = {valDate:['tradeDate','TrdFxswapInvestTrade001.tradeDate'],portfolioCode:['portfolioId','TrdFxswapInvestTrade001.portfolioId'],tradeAmount:['nDealCurAmt','TrdFxswapInvestTrade002.nDealCurAmt'],tradeDirection:['ps','TrdFxswapInvestTrade001.ps'],data:obj}
        return object;
    }
    //---add by jjpei 20190718----end---获取风险试算画面要素与Label提示文本
    window.componentLoadCallback = function(isDone, pagePath, params){
        if(isDone){
            //----add by jjpei 20190718 风险试算----start----
            var riskParam = getRiskData();
            if (pageModel==PAGE_MODEL_NEW){
                riskParam.data =null;
            }
            window.BaseRiskMathComm.getparam(riskParam);
            //----add by jjpei 20190718 风险试算----end----
            if(window.BussTrdTransferOrder && window.BussTrdTransferOrder['bussTrdTransferOrder']) {
                window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                    if (!$("#ps").val()) {
                        Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("TrdFxswapInvestTrade001.ps")))
                        return;
                    }
                    if (!$("#dealCur").val()) {
                        Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("TrdFxswapInvestTrade002.dealCur")))
                        return;
                    }
                    if (!$("#corCcy").val()) {
                        Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("TrdFxswapInvestTrade002.corCcy")))
                        return;
                    }
                    //查看模式不触发方法 add by gaopeng 20191210 start
                    if (!$("#vdate").val()&&pageModel!=PAGE_MODEL_REFERENCE) {
                        Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("TrdFxswapInvestTrade002.vdate")))
                        return;
                    }
                    if (!$("#nDealCurAmt").val()) {
                        Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("TrdFxswapInvestTrade002.nDealCurAmt")))
                        return;
                    }
                    if (!$("#nCorCurAmt").val()) {
                        Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("TrdFxswapInvestTrade002.nCorCurAmt")))
                        return;
                    }
                    if (!$("#mdate").val()&&pageModel!=PAGE_MODEL_REFERENCE) {
                        Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("TrdFxswapInvestTrade002.mdate")))
                        return;
                    }
                    //查看模式不触发方法 add by gaopeng 20191210 end
                    if (!$("#fDealCurAmt").val()) {
                        Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("TrdFxswapInvestTrade002.fDealCurAmt")))
                        return;
                    }
                    if (!$("#fCorCurAmt").val()) {
                        Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("TrdFxswapInvestTrade002.fCorCurAmt")))
                        return;
                    }
                    return tableInitData();
                }, true);
            }
            if(bussTrdTransferOrderDtos){
                window.BussTrdTransferOrder['bussTrdTransferOrder'].saveCalcCondition();
            }

            // 调用共同方法  根据货币对得到即期汇率的精度
            window.EtrForeingExchange.setDecimalPoint('curPair', 'spotRate', 0);
            // 调用共同方法  根据货币对得到近端全价的汇率精度，汇率对应小数位数+2位小数
            window.EtrForeingExchange.setDecimalPoint('curPair', 'nFprice', 2);
            // 调用共同方法  根据货币对得到远端全价的汇率精度，汇率对应小数位数+2位小数
            window.EtrForeingExchange.setDecimalPoint('curPair', 'fFprice', 2);
            var excDealTypeVal = $('#excDealType').val();
            if(excDealTypeVal && excDealTypeVal == FGN_CY_EXC_DEAL_TYPE ){
                $('#dealMode').val(OTC_DEAL_MODE);
                $('#settMode').val(BIL_SETT_SETT_MODE);
                $('#dealMode').attr("disabled", true).trigger('change');
                $('#settMode').attr("disabled", true).trigger('change');
            }
            // 委托带出人员信息时不可改
            if($('#trdInvestPersonInfo002Dto_counterId').val()){
                $('#trdInvestPersonInfo002Dto_counterId').attr('disabled',true).trigger('change');
            }
            if($('#trdInvestPersonInfo002Dto_counterTrader').val()){
                $('#trdInvestPersonInfo002Dto_counterTrader').attr('disabled',true);
            }
            if($('#trdInvestPersonInfo002Dto_contactInfo').val()){
                $('#trdInvestPersonInfo002Dto_contactInfo').attr('disabled',true);
            }
            available();
            initFlag = false;
        }
    };
    /**
     * 做成生成指令所需要的参数
     * 
     * @returns 生成指令信息所需参数
     */
    function tableInitData() {
        var object = $.serializeToObject('#basicInfoForm');
        object.trdInvestPersonInfo002Dto.isOutTrade = $('#trdInvestPersonInfo002Dto_isOutTrade').val();
            Joyin.ajaxSync(basePath + 'invest/trade/trdfxswapinvesttrade/TrdFxswapInvestTrade002/getEntity', object,function(result){
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
    
    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
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
	        if (window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()) {
	            return;
	        }
	        /**
	         * 获取表单数据
	         */
	        var object = $.serializeToObject('#basicInfoForm',true);
	        object.counterId = object.trdInvestPersonInfo002Dto.counterId;
	        object.operateType = operateType;
	        object.pageModel = pageModel;
	        if(FileCommon) {
	            object.fileArray = FileCommon.getUploadFileInfo()
	        }
	        object.inputValue1 = PINAN_ASSET_TYPE_F15;
	        if ($('#trdInvestPersonInfo002Dto_counterId').select2('data') && $('#trdInvestPersonInfo002Dto_counterId').select2('data')[0]) {
	            object.counterId=$('#trdInvestPersonInfo002Dto_counterId').select2('data')[0].id;
	        }
	        var obj = $.extend(true, {}, object);
	        if ($('#curPair').select2('data') && $('#curPair').select2('data')[0]) {
	        	obj.ccy=$('#curPair').select2('data')[0].id;
	        } 
	        if ($('#dealCur').select2('data') && $('#dealCur').select2('data')[0]) {
	        	obj.remark=$('#dealCur').select2('data')[0].id;
	        }  
	        obj.tradeAmt=object.nDealCurAmt;
	        obj.prinAmt=object.nCorCurAmt;
	        obj.finprodType2 = 'F13';
	        object.finprodType2 = 'F13';
	        Joyin.ajaxJson(basePath + 'invest/trade/TrdCommonController/check', obj, function(result1) {
	            Joyin.ajaxJson(basePath + 'invest/trade/trdfxswapinvesttrade/TrdFxswapInvestTrade002/check', object, function(result) {
	                /*var message = '';
	                if(object.ps && object.curPair && object.dealCur){
	                    var startCCY = object.curPair.substring(0,3);
	                    if((object.ps == PS_BUY_SELL && startCCY == object.corCcy) || (object.ps != PS_BUY_SELL && startCCY != object.corCcy)){
	                        if(object.nDealCurAmt && object.dealCurPos && Number(object.nDealCurAmt) > Number(object.dealCurPos)){
	                            message = JoyinUtil.getMessage("TRD_C_0002");
	                        }
	                    }
	                    if((object.ps != PS_BUY_SELL && startCCY == object.corCcy) || (object.ps == PS_BUY_SELL && startCCY != object.corCcy)){
	                        if(object.nCorCurAmt && object.corCurPos && Number(object.nCorCurAmt) > Number(object.corCurPos)){
	                            message = JoyinUtil.getMessage("TRD_C_0002");
	                        }
	                    }
	                }
	                if(message == ''){
	                    message = JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.save"));
	                    if (operateType === 'submit') {
	                        message = JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.submit"));
	                    }
	                }
	                if(operateType === 'save') {
	                    Joyin.confirm(message, function() {
	                        WorkFlow.doSaveList(basePath + 'invest/trade/trdfxswapinvesttrade/TrdFxswapInvestTrade002/save', object, "TrdFxswapInvestTrade002_btn_save");
	                    });
	                }else{
	                    Joyin.confirm(message, function() {
	                        WorkFlow.doSubmitList(basePath + 'invest/trade/trdfxswapinvesttrade/TrdFxswapInvestTrade002/save', object, "TrdFxswapInvestTrade002_btn_save");
	                    });
	                }*/
	            	//授信占用额度
//	                Joyin.ajaxJson(basePath + 'invest/trade/TrdCommonController/delLinesDeal',result1.data,function(result2) {});
	            	object.riskCreditListCommonDto = result1.data;
	            	Joyin.confirm(result.data, function() {
	                    if(operateType === 'save') {
	                        WorkFlow.doSaveList(basePath + 'invest/trade/trdfxswapinvesttrade/TrdFxswapInvestTrade002/save', object, "TrdFxswapInvestTrade002_btn_save");
	                    }
	                    else {
	                        WorkFlow.doSubmitList(basePath + 'invest/trade/trdfxswapinvesttrade/TrdFxswapInvestTrade002/save', object, "TrdFxswapInvestTrade002_btn_save");
	                    }
	                });
	            });
	        });   
	      });
	  };
    
    /*添加交割日与期限联动规则 gaopeng 2019/10/08 start*/
	/**
     * 近端交割日与期限根据交易日历和交易日期联动
     */
    var VDateOnChangeCalcTerm = function () {
        var tradeDate = $("#tradeDate").val();
        var vdate = $("#vdate").val();
        var calendarId = $('#calendarId').val();
        if (tradeDate  && calendarId) {
            var object={
            		tradeDate:tradeDate,
            		nTermTypeExc:$("#nTermTypeExc").val(),
                    calendarId:calendarId
            };
            Joyin.ajax(basePath + 'invest/trade/trdfxswapinvesttrade/TrdFxswapInvestTrade002/getvdate',object,
                    function (data) {
                        if (data && data.vdate && data.vdate) {
                        	$("#vdate").val(data.vdate).trigger('change');
                        }
           });
        }
    };
    
    /**
     * 远端交割日与期限根据交易日历和交易日期联动
     */
    var MDateOnChangeCalcTerm = function () {
        var tradeDate = $("#tradeDate").val();
        var mdate = $("#mdate").val();
        var calendarId = $('#calendarId').val();
        if (tradeDate  && calendarId) {
            var object={
            		tradeDate:tradeDate,
            		fTermTypeExc:$("#fTermTypeExc").val(),
                    calendarId:calendarId
            };
            Joyin.ajax(basePath + 'invest/trade/trdfxswapinvesttrade/TrdFxswapInvestTrade002/getmdate',object,
                    function (data) {
                        if (data && data.mdate && data.mdate) {
                        	$("#mdate").val(data.mdate).trigger('change');
                        }
           });
        }
    };
    /*添加交割日与期限联动规则 gaopeng 2019/10/08 end*/

    /**
     * 近端折美元金额计算
     */
    function nUsdAmtCal(){
        if($('#curPair').val() && $('#curPair').val().indexOf(USD_CCY) != -1){
            if($('#corCcy').val() == USD_CCY && $('#nCorCurAmt').val()){
                $('#nUsdAmt').val($('#nCorCurAmt').val()).trigger('blur');
            } else if($('#dealCur').val() == USD_CCY && $('#nDealCurAmt').val()){
                $('#nUsdAmt').val($('#nDealCurAmt').val()).trigger('blur');
            }
        }
    };

    /**
     * 远端折美元金额计算
     */
    function fUsdAmtCal(){
        if($('#curPair').val() && $('#curPair').val().indexOf(USD_CCY) != -1){
            if($('#corCcy').val() == USD_CCY && $('#fCorCurAmt').val()){
                $('#fUsdAmt').val($('#fCorCurAmt').val()).trigger('blur');
            } else if($('#dealCur').val() == USD_CCY && $('#fDealCurAmt').val()){
                $('#fUsdAmt').val($('#fDealCurAmt').val()).trigger('blur');
            }
        }
    };
    /**
     * 期限类型选择
     */
    function excFxsTermTypeChange(){
        var excFxsTermTypeVal = $('#excFxsTermType').val();
        var nTermTypeExcVal = $('#nTermTypeExc').val();
        var fTermTypeExcVal = $('#fTermTypeExc').val();
        var itemAll = [];
        var itemFwd = [];
        var TERM_TYPE_EXC = JSON.parse(TERM_TYPE_EXC_STR);
        for (var i in TERM_TYPE_EXC){
            if (!TERM_TYPE_EXC.hasOwnProperty(i)) continue;
            if(TERM_TYPE_EXC[i].id != TODAY_TERM_TYPE_EXC
                    && TERM_TYPE_EXC[i].id != TOM_TERM_TYPE_EXC
                    && TERM_TYPE_EXC[i].id != SPOT_TERM_TYPE_EXC){
                itemFwd.push(TERM_TYPE_EXC[i].id)
            }
            itemAll.push(TERM_TYPE_EXC[i].id)
        }
        if(excFxsTermTypeVal == SPOT_FXA_EXC_FXS_TERM_TYPE){
            $('#nTermTypeExc').updateChosenArr({
                item: itemAll,
                disabled: false
            });
            $('#fTermTypeExc').updateChosenArr({
                item: itemFwd,
                disabled: false
            });
        } else if(excFxsTermTypeVal == FXA_FXA_EXC_FXS_TERM_TYPE){
            $('#nTermTypeExc').updateChosenArr({
                item: itemFwd,
                disabled: false
            });
            $('#fTermTypeExc').updateChosenArr({
                item: itemFwd,
                disabled: false
            });
        } else {
            $('#nTermTypeExc').updateChosenArr({
                item: itemAll,
                disabled: false
            });
            $('#fTermTypeExc').updateChosenArr({
                item: itemAll,
                disabled: false
            });
        }
        if(excFxsTermTypeVal){
            if(excFxsTermTypeVal == OVERNIGHT_EXC_FXS_TERM_TYPE){
                $('#nTermTypeExc').val(TODAY_TERM_TYPE_EXC);
                $('#fTermTypeExc').val(TOM_TERM_TYPE_EXC);
                $('#nTermTypeExc').attr("disabled", true);
                $('#fTermTypeExc').attr("disabled", true);
            } else if(excFxsTermTypeVal == TOM_EXC_FXS_TERM_TYPE){
                $('#nTermTypeExc').val(TOM_TERM_TYPE_EXC);
                $('#fTermTypeExc').val(SPOT_TERM_TYPE_EXC);
                $('#nTermTypeExc').attr("disabled", true);
                $('#fTermTypeExc').attr("disabled", true);
            } else if(excFxsTermTypeVal == SPOT_EXC_FXS_TERM_TYPE){
                $('#nTermTypeExc').val(SPOT_TERM_TYPE_EXC);
                $('#fTermTypeExc').val(ONE_DAY_TERM_TYPE_EXC);
                $('#nTermTypeExc').attr("disabled", true);
                $('#fTermTypeExc').attr("disabled", true);
            } else if(excFxsTermTypeVal == SPOT_FXA_EXC_FXS_TERM_TYPE){
                $('#nTermTypeExc').val(SPOT_TERM_TYPE_EXC);
                $('#nTermTypeExc').attr("disabled", true);
                $('#fTermTypeExc').attr("disabled", false);
            } else {
                $('#nTermTypeExc').attr("disabled", false);
                $('#fTermTypeExc').attr("disabled", false);
            }
        }
        if(initFlag && nTermTypeExcVal){
            $('#nTermTypeExc').val(nTermTypeExcVal);
        }
        if(initFlag && fTermTypeExcVal){
            $('#fTermTypeExc').val(fTermTypeExcVal);
        }
        $('#nTermTypeExc').trigger('change');
        $('#fTermTypeExc').trigger('change');
    };

    /**
     * 交易类型选择时联动
     */
    function excDealTypeChange(){
        var excDealTypeVal = $('#excDealType').val();
        if(excDealTypeVal && excDealTypeVal == FGN_CY_EXC_DEAL_TYPE ){
            $('#dealMode').val(OTC_DEAL_MODE);
            $('#settMode').val(BIL_SETT_SETT_MODE);
            $('#dealMode').attr("disabled", true);
            $('#settMode').attr("disabled", true);
        } else if(!initFlag){
            $('#dealMode').val('');
            $('#settMode').val('');
            $('#dealMode').attr("disabled", false);
            $('#settMode').attr("disabled", false);
        }
        $('#dealMode').trigger('change');
        $('#settMode').trigger('change');
    };

    /**
     * 交易货币选择时联动
     */
    function dealCurChange(){
        window.EtrForeingExchange.showCorCcy('curPair','dealCur','corCcy');
    };
    
    /**
     * (近端)掉期全价计算
     * 即期汇率、(近端)掉期点、非空时联动
     */
    function nFpriceCalChange(){
    	if($('#spotRate').val() && $('#nPoint').val()){
   		 $('#nFprice').val(JoyinMath.doAdd($('#spotRate').val(), JoyinMath.doDiv($('#nPoint').val(), 10000, 12, JoyinMath.ROUND_HALF_UP), 6)).trigger('change');
    	}else {
            $('#nFprice').val('').trigger('change');
        }
    };
    /**
     * (远端)掉期全价计算
     * 即期汇率、(远端)掉期点、非空时联动
     */
    function fFpriceCalChange(){
    	if($('#spotRate').val() && $('#fPoint').val()){
            $('#fFprice').val(JoyinMath.doAdd($('#spotRate').val(), JoyinMath.doDiv($('#fPoint').val(), 10000, 12), 6)).trigger('change');
    	}else {
            $('#fFprice').val('').trigger('change');
        }
    };
    /**
     * （近端）对应货币金额计算
     * 货币对、交易货币、（近端）交易货币金额、（近端）掉期全价均输入、非空时联动
     */
    function nCurAmtCalChange(){
        var curPairVal = $('#curPair').val();
        var dealCurVal = $('#dealCur').val();
        if(curPairVal && dealCurVal && $('#nDealCurAmt').val() && $('#nFprice').val()){
            var ccyUnit = 1;
            if(curPairVal == JPY_CNY_CCY_PAIR){
                ccyUnit = 100;
            }
            var ccyArr = curPairVal.split("/");
            if(dealCurVal == ccyArr[0]){
                $('#nCorCurAmt').val(JoyinMath.doDiv(JoyinMath.doMul($('#nFprice').val(), $('#nDealCurAmt').val(), 12), ccyUnit, 2)).trigger('blur');
            } else if( dealCurVal == ccyArr[1]){
                $('#nCorCurAmt').val(JoyinMath.doMul(JoyinMath.doDiv($('#nDealCurAmt').val(), $('#nFprice').val(), 12), ccyUnit, 2)).trigger('blur');
            }
        } else {
            $('#nCorCurAmt').val('').trigger('blur');
        }
    };
    /**
     * （远端）对应货币金额计算
     * 货币对、交易货币、（远端）交易货币金额、（远端）掉期全价均输入、非空时联动
     */
    function fCurAmtCalChange(){
        var curPairVal = $('#curPair').val();
        var dealCurVal = $('#dealCur').val();
        if(curPairVal && dealCurVal && $('#fDealCurAmt').val() && $('#fFprice').val()){
            var ccyUnit = 1;
            if(curPairVal == JPY_CNY_CCY_PAIR){
                ccyUnit = 100;
            }
            var ccyArr = curPairVal.split("/");
            if(dealCurVal == ccyArr[0]){
                $('#fCorCurAmt').val(JoyinMath.doDiv(JoyinMath.doMul($('#fFprice').val(), $('#fDealCurAmt').val(), 12), ccyUnit, 2)).trigger('blur');

            } else if( dealCurVal == ccyArr[1]){
                $('#fCorCurAmt').val(JoyinMath.doMul(JoyinMath.doDiv($('#fDealCurAmt').val(), $('#fFprice').val(), 12), ccyUnit, 2)).trigger('blur');
            }
        } else {
            $('#fCorCurAmt').val('').trigger('blur');
        }
    };

    /**
     * 交易费用计算（按近端交易中的金额计算）
     * 货币对、交易货币、（近端）交易货币金额、（近端）掉期全价均输入、非空时联动
     */
    function feeAmtCalChange(){
        if(!$('#dealMode').val() || $('#dealMode').val() == BANK_EXCH_DEAL_MODE){
            return;
        }
        var curPairVal = $('#curPair').val();
        var dealCurVal = $('#dealCur').val();
        if(initFlag){
        	return ;
        }else if($('#excDealType').val() && curPairVal && dealCurVal && $('#nDealCurAmt').val() && $('#tradeDate').val() && $('#vdate').val() && $('#mdate').val() && $('#calendarId').val()){
            object = $.serializeToObject('#basicInfoForm',true);
            Joyin.ajaxJson(basePath+ "invest/trade/trdfxswapinvesttrade/TrdFxswapInvestTrade002/getFeeAmt",object,function(result){
                if(result) {
                    $('#feeAmt').val(result).trigger('blur');
                }
            });
        } else if(!initFlag){
            $('#feeAmt').val('').trigger('blur');
        }
    };

    /**
     * 界面初始化
     * @returns
     */
    function init(){
    	initFlag = true;
        if($('#entrustId').val() != null && $('#entrustId').val() != ''){
            $('#portfolioId').attr('disabled',true);
            $('#excDealType').attr('disabled',true).trigger('change');
            $('#curPair').attr('disabled',true).trigger('change');
            $('#ps').attr('disabled',true).trigger("change");
            $('#excFxsTermType').attr('disabled',true);
            $('#dealCur').attr('disabled',true).trigger('change');
            $('#corCcy').attr('disabled',true).trigger('change');
        }
        $('#excFxsTermType').trigger('change');
        $('#nDealCurAmt').trigger('change');
    };
    
    /**
     * 指令生成
     */
    $('#TrdFxswapInvestTrade002_generate').click (function(){
        $('#bussTrdTransferOrder_btn_generate').trigger('click');
//        var object = $.serializeToObject('#basicInfoForm');
//        if(object.loadRate != null){
//            object.loadRate = object.loadRate.valueOf()/100;
//        }
//        if($('#finprodMarketId').val() != null && $('#finprodMarketId').val() != ''){
//            object.finprodId= $("#finprodMarketId").select2("data")[0].object;
//        }else if(finprodId != null){
//            object.finprodId = finprodId;
//        }
        /**
        Joyin.ajaxJson(basePath+ "invest/trade/trdfxswapinvesttrade/TrdFxswapInvestTrade002/getOpics",object,function(result){
            var rows = table.getAllRows();
            for(var i in rows){
                table.removeRow(rows[i]);
            }
            table.addRow(result);
        });
        */
    });

    /**
     * 获取查询信息
     */
    var getParameters = function (){
        return{
            portfolioId:$('#portfolioId').val(),
            ps:$('#ps').val(),
            tradeDate:$('#tradeDate').val(),
            dealCur:$('#dealCur').val(),
            corCcy:$('#corCcy').val()
        };
    };
    /**
     * 获取持仓、现金余额、可用现金等
     * @param object json对象
     * @returns 获取持仓、现金余额、可用现金等
     */
    function available() {
        var object = getParameters();
        if (object.portfolioId != null
                && object.portfolioId != ''
                && ((object.dealCur != null && object.dealCur != '') || (object.corCcy != null && object.corCcy != ''))
                && object.tradeDate != null) {
            Joyin.ajax(basePath
                    + "invest/trade/trdfxswapinvesttrade/TrdFxswapInvestTrade002/available",
                    object, function(result) {
                        if(result[0] && result[0].name){
                            $('#dealCurPos').val(result[0].name).trigger('blur');
                        }
                        if(result[1] && result[1].name){
                            $('#corCurPos').val(result[1].name).trigger('blur');
                        }
                    });
        }
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
        init();
        // 是否显示划款指令
        if(GEN_PAY_ORDERS_AUTO=='01'){
        	$('#BussTrdTransferOrder').hide() ;
        }else{
        	$('#BussTrdTransferOrder').show() ;
        }
        $('#TrdFxswapInvestTrade002_btn_save').click(function() {
            saveOrSubmit('save');
        });
       
        //导出
        $('#TrdFxswapInvestTrade002_btn_submit').click(function() {
            saveOrSubmit("submit");
        });
        
       $('#TrdFxswapInvestTrade002_btn_riskMath').click(function(){
           //window.BaseRiskMathComm.initBaseRiskMathCommPage(getRiskData());
           //-----------add by jjpei 2020-01-07 风险新版本 start----------
           var object = $.serializeToObject('#basicInfoForm', true);
           window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/1/RSK002/F15', object,getRiskData());
           //-----------add by jjpei 2020-01-07 风险新版本 end----------
           window.BaseRiskMathComm.initBaseRiskMathCommPageCus(getriskdata());

       })
    });

    /*
     * 组合代码修改时联动
     */
    $('#portfolioId').change(function(){
        available();
    });

    /*
     * 货币对修改时联动
     */
    $('#curPair').change(function(){
    	if(!initFlag){
	        // 根据货币对获取交易日历
	        window.EtrForeingExchange.getCalendarIdByCurPair('curPair','calendarId');
        }
        // 调用共同方法  根据货币对得到即期汇率的精度
        window.EtrForeingExchange.setDecimalPoint('curPair', 'spotRate', 0);
        // 调用共同方法  根据货币对得到近端全价的汇率精度，汇率对应小数位数+2位小数
        window.EtrForeingExchange.setDecimalPoint('curPair', 'nFprice', 2);
        // 调用共同方法  根据货币对得到远端全价的汇率精度，汇率对应小数位数+2位小数
        window.EtrForeingExchange.setDecimalPoint('curPair', 'fFprice', 2);
        dealCurChange();
        available();
        nCurAmtCalChange();
        fCurAmtCalChange();
        nUsdAmtCal();
        fUsdAmtCal();
        feeAmtCalChange();
    });

    /*
     * 交易货币修改时联动
     */
    $('#dealCur').change(function(){
        dealCurChange();
        available();
        nCurAmtCalChange();
        fCurAmtCalChange();
        nUsdAmtCal();
        fUsdAmtCal();
        feeAmtCalChange();
    });

    /*
     * 近端交易货币金额修改时联动
     */
    $('#nDealCurAmt').change(function(){
        nCurAmtCalChange();
        nUsdAmtCal();
        feeAmtCalChange();
        // 远端的交易货币金额就等于近端的交易货币金额联动增加 add by renping 20191011 start
        $('#fDealCurAmt').val($('#nDealCurAmt').val()).trigger('blur');
        $('#fDealCurAmt').trigger('change');
        // 远端的交易货币金额就等于近端的交易货币金额联动增加 add by renping 20191011 end
    });
    /*
     * 近端掉期全价修改时联动
     */
    $('#nFprice').change(function(){
        nCurAmtCalChange();
        nUsdAmtCal();
        feeAmtCalChange();
    });
    /*
     * 远端交易货币金额修改时联动
     */
    $('#fDealCurAmt').change(function(){
        fCurAmtCalChange();
        fUsdAmtCal();
    });
    /*
     * 远端掉期全价修改时联动
     */
    $('#fFprice').change(function(){
        fCurAmtCalChange();
        fUsdAmtCal();
    });
    /*
     * 交易类型修改时联动
     */
    $('#excDealType').change(function(){
        excDealTypeChange();
        feeAmtCalChange();
    });
    /*
     * 即期汇率修改时联动
     */
    $('#spotRate').change(function(){
    	fFpriceCalChange();
    	nFpriceCalChange();
    });
    /*
     * 远端掉期点修改时联动
     */
    $('#fPoint').change(function(){
    	fFpriceCalChange();
    });
    /*
     * 近端掉期点修改时联动
     */
    $('#nPoint').change(function(){
    	nFpriceCalChange();
    });
    /*
     * 期限类型修改时联动
     */
    $('#excFxsTermType').change(function(){
        excFxsTermTypeChange();
    });
    /*
     * 交易模式修改时联动
     */
    $('#dealMode').change(function(){
        feeAmtCalChange();
    });
    /*添加交割日与期限联动规则 gaopeng 2019/10/08 start*/
    /*
     * 交易日修改时联动
     */
    $('#tradeDate').change(function(){
        available();
        feeAmtCalChange();
        if($('#nTermTypeExc').val()==""){
        	$("#vdate").val("").trigger('change');
        }
        if($('#fTermTypeExc').val()==""){
        	$("#mdate").val("").trigger('change');
        }
        if($('#tradeDate').val()==""){
        	$("#vdate").val("").trigger('change');
        	$("#mdate").val("").trigger('change');
        }
        if($('#calendarId').val()==""){
        	$("#vdate").val("").trigger('change');
        	$("#mdate").val("").trigger('change');
        }
        if($('#nTermTypeExc').val()!="BROKEN" && $('#calendarId').val()!="" && $('#tradeDate').val()!=""){
        	VDateOnChangeCalcTerm();
    	}else{
    		$("#vdate").val("").trigger('change');
    	}
        if($('#fTermTypeExc').val()!="BROKEN" && $('#calendarId').val()!="" && $('#tradeDate').val()!=""){
        	MDateOnChangeCalcTerm();
    	}else{
    		$("#mdate").val("").trigger('change');
    	}
    });
    /*
     * 近端期限修改时联动
     */
    $('#nTermTypeExc').change(function(){
    	 if($('#nTermTypeExc').val()==""){
         	$("#vdate").val("").trigger('change');
         }
         if($('#tradeDate').val()==""){
         	$("#vdate").val("").trigger('change');
         	$("#mdate").val("").trigger('change');
         }
         if($('#calendarId').val()==""){
         	$("#vdate").val("").trigger('change');
         	$("#mdate").val("").trigger('change');
         }
        if($('#nTermTypeExc').val()!="BROKEN" && $('#calendarId').val()!="" && $('#tradeDate').val()!=""){
        	VDateOnChangeCalcTerm();
    	}else{
    		$("#vdate").val("").trigger('change');
    	}
    });
    /*
     * 远端期限修改时联动
     */
    $('#fTermTypeExc').change(function(){
         if($('#fTermTypeExc').val()==""){
         	$("#mdate").val("").trigger('change');
         }
         if($('#tradeDate').val()==""){
         	$("#vdate").val("").trigger('change');
         	$("#mdate").val("").trigger('change');
         }
         if($('#calendarId').val()==""){
         	$("#vdate").val("").trigger('change');
         	$("#mdate").val("").trigger('change');
         }
        if($('#fTermTypeExc').val()!="BROKEN" && $('#calendarId').val()!="" && $('#tradeDate').val()!=""){
        	MDateOnChangeCalcTerm();
    	}else{
    		$("#mdate").val("").trigger('change');
    	}
    });
    /*
     * 交易日历修改时联动
     */
    $('#calendarId').change(function(){
        feeAmtCalChange();
        if($('#nTermTypeExc').val()==""){
        	$("#vdate").val("").trigger('change');
        }
        if($('#fTermTypeExc').val()==""){
        	$("#mdate").val("").trigger('change');
        }
        if($('#tradeDate').val()==""){
        	$("#vdate").val("").trigger('change');
        	$("#mdate").val("").trigger('change');
        }
        if($('#calendarId').val()==""){
        	$("#vdate").val("").trigger('change');
        	$("#mdate").val("").trigger('change');
        }
        if($('#nTermTypeExc').val()!="BROKEN" && $('#calendarId').val()!="" && $('#tradeDate').val()!=""){
        	VDateOnChangeCalcTerm();
    	}else{
    		$("#vdate").val("").trigger('change');
    	}
        if($('#fTermTypeExc').val()!="BROKEN" && $('#calendarId').val()!="" && $('#tradeDate').val()!=""){
        	MDateOnChangeCalcTerm();
    	}else{
    		$("#mdate").val("").trigger('change');
    	}
    });
    /*添加交割日与期限联动规则 gaopeng 2019/10/08 end*/
    /*
     * 远端交割日修改时联动
     */
    $('#mdate').change(function(){
        feeAmtCalChange();
    });
}());