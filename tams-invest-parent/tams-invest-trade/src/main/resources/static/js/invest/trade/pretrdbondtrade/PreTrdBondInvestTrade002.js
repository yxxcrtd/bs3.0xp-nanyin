

(function () {
    
    var entrustNum; // 委托时的券面总额
    var entrustPrice; // 委托时的净价
    var entrustYield; // 委托时的到期收益率
    var entrustExerYield; // 委托时的行权收益率
    var commissionAmt=0;// 佣金
    var fTradeIdFromCstp = fTradeId;
    var entrustData;
    var tableAllRows;
    var rowDatas = [];
    var tradeAmtAll=0;
    var isCSTP = false;
    var zcdm,jyfx,jycs,jypt,tdao;
    var isChange = false;
    var returnValAll={
            isPayWithTrade:'Y',
            procedureAmt:0,
            settleAmt:0,
            commissionAmt:0,
            stampDutyAmt:0,
            stampDutyIsPayWithTrade:'Y'
        };
    // add by wangguoxian 费用设置 20190807 start
    var isPayWithTrade = $("#isPayWithTrade").val()?$("#isPayWithTrade").val():YES_OR_NO_Y;//佣金是否随交易支付，默认为是
    $("#isPayWithTrade").val(isPayWithTrade);
    // add by wangguoxian 费用设置 20190807 end
    var stampDutyIsPayWithTrade = $("#stampDutyIsPayWithTrade").val()?$("#stampDutyIsPayWithTrade").val():YES_OR_NO_Y;//印花是否随交易支付，默认为是
    $("#stampDutyIsPayWithTrade").val(stampDutyIsPayWithTrade);
    var getriskdata = function (i) {
        var mun=null;
        var object = $.serializeToObject('#submitForm', true);
        var bussTrdTransfenpeiDto=null;
        if(object!=null&&object!=""){
            bussTrdTransfenpeiDto=object.bussTransfenpei.trdTransfenpeiDtoList;
        }
        var list=[];
        for(var i in bussTrdTransfenpeiDto){
            var obj = {
                    timePoint:MY_SUP_TIME_TYPE_02,
                    valDate:$('#settleDate').val(),
                    portfolioCode:bussTrdTransfenpeiDto[i].portfolioId,
                    tradeAmount:bussTrdTransfenpeiDto[i].fpriceAmt,
                    assetType:FINPROD_TYPE_F01,
                    isEnabled:'01',
                    assetCode:$('#finprodId').val(),
                    tradeDirection:$('#ps').val(),
                    index: null,
                    mun: i}
            list[i]=obj;
        }
//        if($('#windtypeone').val() && $('#windtypeone').val() != ''){
//            for(var i in list){
//                list[i].assetSecType = $('#windtypeone').val();
//            }
//        }

        var object = {
        		valDate:['settleDate','TrdPlanRateInvestTrade002.settleDate'],
        		portfolioCode:['portfolioId','TrdBondInvestTrade002.portfolioId'],
        		tradeAmount:['fpriceAmt','TrdBondInvestTrade002.fpriceAmt'],
        		assetType:['windtypeone','TrdBondInvestTrade002.windtypeone'],
        		tradeDirection:['ps','TrdLendInvestTrade002.ps'],
        		data:list[0]?list[0]:{}
        		}
        return object;
    }
    function saveOrSubmit(operateType) {
        var s;
        if(typeof(BaseRiskMathComm) != 'undefined') {
        if(BaseRiskMathComm) {
            s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
            if(!s && s != ''){
                return;
            }
            if (window.BaseRiskMathComm.isCalcConditionChanged(getriskdata())){
                return;
                }
            }
        }
        // 检查计算元素是否发生变化
        if (checkCalcParam()) {
            //---update by jjpei 20190731---信息显示有误
            Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0013",
                ["计算要素", "点击计算按钮"]));
            //---update by jjpei 20190731---信息显示有误
            return;
        }

        var isTrue = chectRowsIsChange();
        if(isTrue){
            //---update by jjpei 20190731---信息显示有误
            Joyin.error("组合分配中的计算要素已变更，请重现点击计算按钮!");
            //---update by jjpei 20190731---信息显示有误
            return;
        }

        // 检查生成划款指令参数是否发生变化
//        if (window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()) {
//            return;
//        }

        //检查组合分配的和金额信息是否一致
        if($('#tradeAmt').val()){
            var number = 0;
            for(var i=0;i<table.getAllRows().length;i++){
                if(table.getAllRows()[i].tradeAmt){
                    number =  JoyinMath.doAdd(number,table.getAllRows()[i].tradeAmt,2);
                }
            }
            // if(JoyinMath.doAdd($('#tradeAmt').val()*10000,0,2)!=number){
            if(JoyinMath.doAdd($('#tradeAmt').val(),0,2)!=number){
                Joyin.error("组合分配劵面总额和金额信息券面总额不一致！");
                return;
            }
        }
        if($('#fpriceAmt').val()){
            var number = 0;
            var dataRows = table.getAllRows();
            for(var i=0;i<table.getAllRows().length;i++){
                if("T01"==$("#ps").val()){
                    if(dataRows[i].fpriceAmt && dataRows[i].bussTrdAvailableCashQueryavalibleCash){
                        if(parseFloat(dataRows[i].fpriceAmt) > parseFloat(dataRows[i].bussTrdAvailableCashQueryavalibleCash)){
                            Joyin.error("交易方向为买入时，全价总额不得大于组合剩余可用现金！");
                            return;
                        }
                    }
                }
                if(table.getAllRows()[i].fpriceAmt){
                    number =  JoyinMath.doAdd(number,table.getAllRows()[i].fpriceAmt,2);
                }
            }
            if(JoyinMath.doAdd($('#fpriceAmt').val(),0,2)!=number){
                Joyin.error("组合分配全价总额和金额信息全价总额不一致！");
                return;
            }
        }

        if($('#fTradeId').val()&&$('#tradeAmt').val()&&(pageModel==PAGE_MODEL_NEW||pageModel==PAGE_MODEL_EDIT)){//新建
            var isTrue = false;
            var params = {};
            params.fTradeId = $('#fTradeId').val();
            // params.tradeAmt = $('#tradeAmt').val()*10000;
            params.tradeAmt = $('#tradeAmt').val();
            params.workflowId = $('#workflowId').val();
            Joyin.ajaxJson(JoyinUtil.getRealPath('/invest/trade/bond/TrdBondInvestTrade002/isAllTradeAmtByFTradeId'), params, function (result) {
                if(result&&!result.data){
                    isTrue = true;
                    Joyin.error("成交编号获得的劵面总额不得大于已存在的劵面总额！");
                }
            },false);
            if(isTrue){
                return;
            }
        }
        var object = $.serializeToObject('#submitForm', true);
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.fpriceAmt = $('#fpriceAmt').val();
        if(object.eff_time){
        var time = new Date().format("yyyy-MM-dd") + " " + object.eff_time;
        object.effTime = time;
        }
        /*if(FileCommon) {
            object.uploadFileList = FileCommon.getUploadFileInfo();
        }*/
        // object.tradeAmt = $('#tradeAmt').val()*10000;
        object.tradeAmt = $('#tradeAmt').val();
        object.entrustNum = entrustNum; // 委托时的券面总额
        object.entrustPrice = entrustPrice; // 委托时的净价
        object.entrustYield = entrustYield; // 委托时的到期收益率
        object.entrustExerYield = entrustExerYield; // 委托时的行权收益率
        object.assetPosition = $("#bussTrdAvailableCashQuery_assetPosition").val(); // 资产可用持仓
        object.bondFirstSign = $("#bondFirstSign").val(); //中标结果页面进入的标识 add by gejiaao 2019/8/9
        object.deviation=  parseFloat($("#deviationDegreeLabel").text())/100;//偏离度获取界面值并将其转换为数值型by赵东东 2019.9.24
        var data = $("#chlId").find("option:selected").data("data");
        if (data && data.object) {
            object.payType = data.object;
        }
        if ($('#trdInvestPersonInfo_counterId').select2('data') && $('#trdInvestPersonInfo_counterId').select2('data')[0]) {
            object.counterType = $('#trdInvestPersonInfo_counterId').select2('data')[0].object;;
        }
        Joyin.ajaxJson(basePath + 'invest/trade/pretrdbondtrade/PreTrdBondInvestTrade002/check', object, function (result) {
            Joyin.confirm(result.data, function () {
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/trade/pretrdbondtrade/PreTrdBondInvestTrade002/save', object, "PreTrdBondInvestTrade002_btn_save","PreTrdBondInvestTrade002");
                } else {
                    WorkFlow.doSubmitList(basePath + 'invest/trade/pretrdbondtrade/PreTrdBondInvestTrade002/save', object, "PreTrdBondInvestTrade002_btn_submit","PreTrdBondInvestTrade002");
                }
            });
        },false);
        

    };

    //检查组合分配是否发生变化
    var chectRowsIsChange = function(){
        var isChange = false;
        if (typeof tableAllRowsJiSun != 'undefined') {
            var tableData =  table.getAllRows();
            if(tableAllRowsJiSun.length!=tableData.length){
                return true;
            }
            $.each(tableData,function (index,data) {
                for (var key in data) {
                    if (key == "createTime" || key == "updateTime"
                        ||key=='index'|| key == "LAY_TABLE_INDEX"|| key == "LAY_CHECKED") {
                        continue;
                    }
                    var currentDealObj = tableAllRowsJiSun[index][key];
                    var backupDealObj = data[key];
                    if (typeof currentDealObj != "object" && key != "procedureAmt"&&key != "settleAmt"
                        && currentDealObj != backupDealObj) {
                        isChange = true;
                    }
                }
            });
        }
        return isChange;
    };


    function checkCalcParam() {
        if (typeof saveCalcParam != 'undefined') {
            var currentCalcParam = getCalcParam();
            for ( var key in currentCalcParam) {
                //---update by jjpei 20190731---排除riskMathFlag----
                if (key == 'selectValue'||key=='riskMathFlag'||key=='counterId'||key=='secAcctId'
                	||key=='acctNo'||key=='acctName'||key=='acctBankDep'||key=='acctBankDepoPayno'
                		||key=='secAcctName'||key=='registOrg'||key=='counterTrader') {
                    continue;
                }
                //---update by jjpei 20190731---排除riskMathFlag----
                var currentDealObj = currentCalcParam[key];
                var backupDealObj = saveCalcParam[key];

                if (typeof currentDealObj != "object"
                    && currentDealObj != backupDealObj) {
                    return true;
                }
            }
        }
    };

    var checkAndAssign = function(el, value, editable){
        if(value != null && value != ''){
            $(el).val(value).trigger('change').trigger('blur');
            if(!editable){
                $(el).attr("disabled",true);
            }
        }
    };
    var checkNotEmpty = function () {
        for(var i = 0; i < arguments.length; i++){
            if(arguments[i] == null || arguments[i] == ''){
                return false;
            }
        }
        return true;
    }
    var etrEntrust;
    /**
     * 计算交割日
     */
    var calcSettleDate = function() {
        var settleSpeed = $("#settleSpeedText").val();
        if (settleSpeed == ACCOUNT_SPEED_T0) {
            $("#settleDate").val($("#tradeDate").val()).trigger("change");
        }
        else if(settleSpeed == ACCOUNT_SPEED_T1) {
        	if ($('#finprodId').val()) {
            Joyin.ajax(basePath + 'invest/trade/bond/TrdBondInvestTrade003/getNextWorkDay', {
                'calendarId': $("#calendarId").val(),
                'tradeDate': $("#tradeDate").val()
            }, function (date) {
                $("#settleDate").val(date).trigger("change");
            });
        	}
        } else {
            $("#settleDate").val("").trigger("change");
        }
    };
    
    $.fn.updateChosenArr = function updateChosenArr(obj){
		var $this = $(this),arr = obj.dataResult || JSON.parse($this.attr('data-result') || '[]'),item = obj.item;
		$this.empty();
		$this.append($('<option value>'));
		for(var i in arr){
			if (!arr.hasOwnProperty(i))continue;
			if (obj.item && obj.item.length > 0){
				if ($.inArray(arr[i].id,item) >= 0)
					$('<option>').attr('value', arr[i].id).text(arr[i].text).appendTo($this);
			} else
				$('<option>').attr('value', arr[i].id).text(arr[i].text).appendTo($this)
		}
		if (obj.chosen)
			$this.val(obj.chosen);
		if (obj.disabled === true || obj.disabled === false)
			$this.attr('disabled', obj.disabled);
		$this.trigger("chosen:updated").trigger('change');
		if (obj.callback)
			obj.callback($this);
		return $this;
	}



    
    /**
     * 画面联动处理
     * 1.2交易市场
     */
    $("#tradeMarketMode").on("change", function () {
        var tradeMarketMode = $("#tradeMarketMode").val();
        if (tradeMarketMode == SHARES_TRADE_MARKET_01) {
            checkAndAssign("#ps", PS_T01);
            checkAndAssign("#unitInt", '0');
            checkAndAssign("#intAmt", '0');
        } else {
            if ($("#entrustId").val()) {
                $("#ps").attr("disabled", true);
            }else if ($("#fTradeId").val()){
                $("#ps").attr("disabled", true);
            } else {
            	if($("#zhilingtype").val() =="02"){
                	window.BussTrdAvailableCashQuery && window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'].hideCash(true);
                }
                $("#ps").attr("disabled", false);
            }
            $("#intAmt").attr("disabled", false);
        }
    });


    /**
     * 1.4清算速度、交易日、交割日之间有联动关系
     */
    $("#settleSpeedText").on("change", function () {
        calcSettleDate();
    });
    /**
     * 交易日历变化，联动交割日
     */
    $("#calendarId").on("change", function () {
        calcSettleDate();
    });

    /**
     * 1.5资产代码选择时
     * 资产名称文本框根据资产代码选择自动显示对应名称;
     * 同时带出资产相关信息，包括起息日，到期日，发行日，交易场所，币种，交易日历，计息基础等;
     */
    $('#finprodId').on('change', function () {
        var finprodId = $('#finprodId').val();
        var data = $('#finprodId').select2('data')[0].object;
        if($('#finprodId').select2('data')[0].id){
        	if (data && data.finprodId) {// 资产名称文本框根据资产代码选择自动显示对应名称;    
        		// 同时带出资产相关信息，包括起息日，到期日，发行日，交易场所，币种，交易日历，计息基础等;
        		
                $("#finprodName").val($('#finprodId').select2('data')[0].object.finprodAbbr);
       

             $("#basis").val(data.basis).trigger('change');
             $("#type9").val(data.type9).trigger('change');
            $("#vdate").val(data.vdate).trigger('change');
            $("#mdate").val(data.mdate).trigger('change');
            showOrhide();


            if (data.tradeMarket) {
                $("#tradeMarket").val(data.tradeMarket).trigger('change');
                //add by  zhangnian 20190910 start
                if(data.tradeMarket == TRADE_PLACE_05){
                    $('#tradeMarketMode').val(SHARES_TRADE_MARKET_02).trigger('change');
                    $('#tradeMarketMode').prop("disabled", true);
                }else{$('#tradeMarketMode').prop("disabled", false)}
                //add by  zhangnian 20190910 end
            }
            $("#ccy").val(data.ccy).trigger('change');


            // 控制显示行权收益率
            exerYieldDivContrl();
            } 
        	 updateCash();
             updateSecAcctBalance();
             // 控制显示行权收益率
             exerYieldDivContrl();
        } else {
            // 资产名称文本框根据资产代码选择自动显示对应名称;
        	showOrhide();
            $("#finprodName").val("").trigger('change');
            // 同时带出资产相关信息，包括起息日，到期日，发行日，交易场所，币种，交易日历，计息基础等;
            $("#vdate").val("").trigger('change');
            $("#mdate").val("").trigger('change');
            $("#ccy").val("").trigger('change');
            $("#calendarId").val("").trigger('change');
            $("#basis").val("").trigger('change');
            $("#type9").val("").trigger('change');
            
        }
        // 1.6计算偏离度
        calcDeviationDegree();
    });
    
    function showOrhide(){
    	if($('#finprodId').val()){
            $("#base_Div").show();
            $("#date_Div").show();
    	}else{
    	$("#base_Div").hide();
        $("#date_Div").hide();
    	}
    }

    
    /**
     * 资产名称查看
     * 若点击资产名称后面的【查看】按钮跳转到本资产的详情画面
     */
    $('#TrdBondInvestTrade002_btn_viewAsset').click(function(){
        if ($('#finprodId').val()) {
            Joyin.dialog(JoyinUtil.getLabel("FinBondTargetAsset002.title"), basePath
                    + "invest/asset/bond/FinBondTargetAsset002/init", "95%", "90%", {
                pageModel : PAGE_MODEL_REFERENCE,
                finprodId : $('#finprodId').val()
            });
        }
    })
    $("#portfolioId").on("change", function (e, isInit) {
        updateSecAcctBalance();
        updateCash();
        // 债券投资 改变组合 需联动 证券账户 ,证券托管户应清空选择项
        if (!isInit && pageModel !=PAGE_MODEL_REFERENCE&&pageModel !=PAGE_MODEL_EDIT) {
            $("#secManageAcctId").val('').trigger("change");
        }
    });
    /**
     * 证券托管户变化更新资产可用持仓
     */
    $("#secManageAcctId").on("change", function () {
        if ($("#secManageAcctId").val()) {
            $("#chlId").val('').prop("disabled", true);
        }
        else {
            $("#chlId").prop("disabled", false);
        }
        updateCash();
    });   


   
    // 一体化对应etliu end
    
    /**
     * 数值项目change后记录至隐藏值
     */
    $("#unitCprice").on("change", function() {
        $("#unitCprice_calc").val("");
    });
    $("#unitInt").on("change", function() {
        $("#unitInt_calc").val("");
    });
    $("#unitFprice").on("change", function() {
        $("#unitFprice_calc").val("");
    });
    $("#cpriceAmt").on("change", function() {
        $("#cpriceAmt_calc").val("");
    });
    $("#intAmt").on("change", function() {
        $("#intAmt_calc").val("");
    });
    $("#fpriceAmt").on("change", function() {
        $("#fpriceAmt_calc").val("");
    });
    $("#yield").on("change", function() {
        $("#yield_calc").val("");
        if($('#ps').val()=='T01'){//买入
            if(entrustYield&&Number($(this).val())<Number(entrustYield)){
                Joyin.warn("买入时，到期收益率不能小于委托的到期收益率");
            }
        }else if($('#ps').val()=='T02'){//卖出
            if(entrustYield&&Number($(this).val())<Number(entrustYield)){
                Joyin.warn("卖出时，到期收益率不能大于委托的到期收益率");
            }
        }
    });
    $("#exerYield").on("change", function() {
        $("#exerYield_calc").val("");
        if($('#ps').val()=='T01'){//买入
            if(entrustExerYield&&Number($(this).val())<Number(entrustExerYield)){
                Joyin.warn("买入时，行权收益率不能小于委托的行权收益率");
            }
        }else if($('#ps').val()=='T02'){//卖出
            if(entrustExerYield&&Number($(this).val())<Number(entrustExerYield)){
                Joyin.warn("卖出时，行权收益率不能大于委托的行权收益率");
            }
        }
    });

   

    function getCalcParam(type,data) {
        var paramObj = $.serializeToObject('#submitForm', true);
        if(type&&type=='02'){
            paramObj.portfolioId = data.portfolioId;
            paramObj.secManageAcctId = data.secManageAcctIdDuo;
        }
        paramObj.calculateField = $("[name=calculateField]:checked").val();
        // paramObj.tradeAmt = $("#tradeAmt").val()*10000;
        paramObj.tradeAmt = $("#tradeAmt").val();
        paramObj.unitCprice = $("#unitCprice").val();
        paramObj.unitInt = $("#unitInt").val();
        paramObj.unitFprice = $("#unitFprice").val();
        paramObj.cpriceAmt = $("#cpriceAmt").val();
        paramObj.intAmt = $("#intAmt").val();
        paramObj.fpriceAmt = $("#fpriceAmt").val();
        paramObj.exerYield = $("#exerYield").val();
        paramObj.yield = $("#yield").val();
        if(data&&data.isEntrust&&data.isEntrust=='YES'){
            paramObj.calculateField = "unitCprice";
            paramObj.tradeAmt = data.tradeAmt;
            paramObj.ps = data.ps;
        }
        // 隐藏域如果有值，使用隐藏域
        if ($("#unitCprice_calc").val()) {
            paramObj.unitCprice = $("#unitCprice_calc").val();
        }
        if ($("#unitInt_calc").val()) {
            paramObj.unitInt = $("#unitInt_calc").val();
        }
        if ($("#unitFprice_calc").val()) {
            paramObj.unitFprice = $("#unitFprice_calc").val();
        }
        if ($("#cpriceAmt_calc").val()) {
            paramObj.cpriceAmt = $("#cpriceAmt_calc").val();
        }
        if ($("#intAmt_calc").val()) {
            paramObj.intAmt = $("#intAmt_calc").val();
        }
        if ($("#fpriceAmt_calc").val()) {
            paramObj.fpriceAmt = $("#fpriceAmt_calc").val();
        }
        if ($("#exerYield_calc").val()) {
            paramObj.exerYield = $("#exerYield_calc").val();
        }
        if ($("#yield_calc").val()) {
            paramObj.yield = $("#yield_calc").val();
        }

        // 计算之前，先将应计利息清空
        var tradeMarketMode = $("#tradeMarketMode").val();
        if (tradeMarketMode != SHARES_TRADE_MARKET_01) {
            paramObj.unitInt = null;
        }
        // add by wangguoxian 费用设置 20190808 start
        // 交易手续费，结算费，佣金，通道费，交易金额不再计入计算要素
        paramObj.procedureAmt = null;
        paramObj.settleAmt = null;
        paramObj.commissionAmt = null;
        paramObj.channelFee = null;
        paramObj.tradeAmtAndFee = null;
        paramObj.layTableCheckbox = null;
        // add by wangguoxian 费用设置 20190808 end
        return paramObj;
    }

     function extracted() {//平均分配
             if ($("#unitInt").val()) {
                 //应计利息总额
                 extractedIntAmt()
             } else {
                 extractedIntAmt(0)
             }
             //计算净价总额
             if ($("#unitCprice").val()) {
              extractedCpriceAmt();
             }
             //计算结算费和交易手续费和佣金等数据
             if($('#tradeAmt').val()){
                 extractedJiSuan(false,returnValAll);
             }
            if ($("#unitFprice").val()) {
                //获取i行的券面总额
                //全价总额
                var fpriceAmtSum = Number(0);
                for (var i = 0; i < table.getAllRows().length; i++) {
                    if((table.getAllRows()[i].cpriceAmt || table.getAllRows()[i].cpriceAmt == 0)
                        &&(table.getAllRows()[i].intAmt || table.getAllRows()[i].intAmt == 0)){
                        var num = JoyinMath.doAdd(table.getAllRows()[i].cpriceAmt,table.getAllRows()[i].intAmt,2);
                        table.renderRow(i, {"fpriceAmt": num});
                        fpriceAmtSum = JoyinMath.doAdd(fpriceAmtSum, num, 2);
                    }
                }
                if(table.getAllRows().length>0){
                    $("#fpriceAmt").val(JoyinMath.doAdd(fpriceAmtSum, 0, 2)).trigger('change').trigger("blur");
                }else{
                    $("#fpriceAmt").val(JoyinMath.doAdd($("#intAmt").val(), $("#cpriceAmt").val(), 2)).trigger("blur").trigger('change');
                }
            } else {
                table.renderRow(i, {"fpriceAmt": 0});
            }
    }

    function extractedTime(){//时间优先
        // var tradeAmt = $('#tradeAmt').val()*10000;
        var tradeAmt = $('#tradeAmt').val();
        tradeAmt = JoyinMath.doAdd(tradeAmt,0,2);
       if(tradeAmt){
           var all = 0;
           var one  = 0;
           for (var i = 0; i < table.getAllRows().length; i++) {
               var tradeAmt1 = table.getAllRows()[i].tradeAmt;
               var doEntrustMoney = table.getAllRows()[i].doEntrustMoney;
               tradeAmt1 = JoyinMath.doAdd(tradeAmt1,0,2);
               all = JoyinMath.doAdd(all,tradeAmt1,2);
               //如果第一列的值比劵面总额大的话
               if(i==0&&Number(tradeAmt1)>Number(tradeAmt)){
                   if(Number(JoyinMath.doAdd(tradeAmt, 0, 2))<=Number(doEntrustMoney)){
                       table.renderRow(0, {"tradeAmt": JoyinMath.doAdd(tradeAmt, 0, 2)});
                   }else{
                       table.renderRow(0, {"tradeAmt": doEntrustMoney});
                   }
                   for(var j = 1; j <table.getAllRows().length; j++){
                       table.renderRow(j, {"tradeAmt": JoyinMath.doDiv(0, 0, 2)});
                   }
                   break;
               }else{
                   if(Number(all)>Number(tradeAmt)){
                      var num =  JoyinMath.doMinus(tradeAmt,one,2);
                      if(Number(num)<=Number(doEntrustMoney)){
                          table.renderRow(i, {"tradeAmt": num});
                          one = JoyinMath.doAdd(one,num,2);
                      }else{
                          table.renderRow(i, {"tradeAmt": doEntrustMoney});
                          one = JoyinMath.doAdd(one,doEntrustMoney,2);
                      }
                   }else{
                       var num = JoyinMath.doAdd(tradeAmt1, 0, 2);
                       one = JoyinMath.doAdd(one,tradeAmt1,2);
                       if(Number(num)<=Number(doEntrustMoney)){
                           table.renderRow(i, {"tradeAmt": num});
                       }else{
                           table.renderRow(i, {"tradeAmt": doEntrustMoney});
                       }
                   }
               }
           }
       }
    }

    function extractedNumber() {//数量优先
        // var tradeAmt = $('#tradeAmt').val()*10000;
        var tradeAmt = $('#tradeAmt').val();
        if(tradeAmt){
            var allRows = getAllRowsForEntrustId();
            //先按照劵面总额排序
            function sortprice(a,b){
                return b.tradeAmt-a.tradeAmt;
            }
            allRows =  allRows.sort(sortprice);
            table.clear();
            table.addRow(allRows);
            setMoney(allRows);
            extractedTime();
        }
    }

    function extractedJiSun() {
        var paramObj = getCalcParam();
        paramObj.settleSpeed = paramObj.settleSpeedText
        paramObj.invAim = 0;
        Joyin.ajaxJson(JoyinUtil.getRealPath('/invest/trade/pretrdbondtrade/PreTrdBondInvestTrade002/calculateData'), paramObj, function (result) {
            if (result && result.data) {
                var numberAll = 0;
                var data = result.data;
                if (!isCSTP) {
                    // $("#tradeAmt").val((data.tradeAmt)/10000).trigger("blur");
                    $("#tradeAmt").val((data.tradeAmt)).trigger("blur");
                    $("#unitCprice").val(data.unitCprice).trigger("blur");
                    $("#unitFprice").val(data.unitFprice).trigger("blur");
                    $("#cpriceAmt").val(data.cpriceAmt).trigger("blur");
                    $("#intAmt").val(data.intAmt).trigger("blur");
                    $("#unitInt").val(data.unitInt).trigger("blur");
                }
                if ($("#zhilingtype").val() == "02") {
                    extracted();//平均分配方式
                } else if (!isCSTP) {
                    $("#fpriceAmt").val(JoyinMath.doAdd(Number($("#intAmt").val()) + Number($("#cpriceAmt").val()), 0, 2)).trigger("blur").trigger('change');
                }
                if (!isCSTP) {
                    $("#exerYield").val(data.exerYield).trigger("blur");
                    $("#yield").val(data.yield).trigger("blur");
                    // 记录隐藏值
                    $("#unitCprice_calc").val(data.unitCprice);
                    $("#unitFprice_calc").val(data.unitFprice);
                    $("#cpriceAmt_calc").val(data.cpriceAmt);
                    $("#fpriceAmt_calc").val(data.fpriceAmt);
                    $("#exerYield_calc").val(data.exerYield);
                    $("#yield_calc").val(data.yield);
                    $("#intAmt_calc").val(data.intAmt);
                    $("#unitInt_calc").val(data.unitInt);
                }
                saveCalcParam = getCalcParam();
            }
            //计算交易金额
            tableAllRowsJiSun = table.getAllRows();
        });
    }


    function isFTradeId(){
            extractedJiSun();
            extracted();//平均分配方式
    };
    /**
     * 1.7计算按钮点击事件
     */
    $('#PreTrdBondInvestTrade002_btn_calculate').click(function() {
        extractedJiSun();
        
    });
    /**
     * 净价change事件
     * 1.6计算偏离度
     */
    $("#unitCprice").on("change", function () {
        if($('#ps').val()=='T01'){//买入
            if(entrustPrice&&Number($(this).val())>Number(entrustPrice)){
                Joyin.warn("买入时，净价不能大于委托的净价");
            }
        }else if($('#ps').val()=='T02'){//卖出
            if(entrustPrice&&Number($(this).val())<Number(entrustPrice)){
                Joyin.warn("卖出时，净价不能小于委托的净价");
            }
        }
        calcDeviationDegree();
        //多组合指令类型净价改变影响净价总额
        if($("#zhilingtype").val()=="02"){
           // updateBussTrdTransfenpeiTable();
        }
    });
    /**
     * 1.6计算偏离度
     */
    var calcDeviationDegree = function() {
        if ($("#finprodId").val() && $("#unitCprice").val()) {
            var paramObj = {};
            paramObj.finprodId = $('#finprodId').val();
            if($('#finprodId').select2('data')[0].object){
                paramObj.finprodMarketId = $('#finprodId').select2('data')[0].object.finprodMarketId;
            }
            paramObj.unitCprice = $("#unitCprice").val();
            Joyin.ajax(JoyinUtil.getRealPath('/invest/trade/bond/TrdBondInvestTrade003/calcDeviationDegree'), paramObj, function (data) {
                $("#deviationDegreeLabel").text(data);
            });
        } else {
            $("#deviationDegreeLabel").text("");
        }
    };

    function getAllRowsForEntrustId(){
        tradeAmtAll = 0;
        var rowDatas = [];
        var entrustId = $("#entrustId").val();
        var bondFirstSign = $("#bondFirstSign").val();
        if(entrustId){
            Joyin.ajaxJson(basePath + 'invest/trade/bond/TrdBondInvestTrade003/getEntrustInfos', {'entrustId':entrustId,'bondFirstSign':bondFirstSign},function (data) {
                // 基本要素信息由投资委托带出，其中有：委托代码、组合代码、资产代码、清算速度、交易方向、交易场所、交易平台、交易市场、投资目的等，不可编辑
                // 金融要素信息由投资委托带出，其中有：币种、券面总额、净价、到期收益率、行权收益率等
                entrustData = data;
                function sortprice(a,b){
                    return a.etrEntrust.createTime-b.etrEntrust.createTime
                }
                data =  entrustData.sort(sortprice);
                if(data){
                    $.each(data,function (index,element) {
                        var rowData={};
                        var params ={};
                        // 计算交割日
                        calcSettleDate();
                        if(element.etrEntrust){
                            rowData.ps =  element.etrEntrust.ps;
                            rowData.entrustId = element.etrEntrust.entrustId;//委托代码
                            rowData.portfolioId = element.etrEntrust.portfolioId;//多组合组合代码
                            if(element.doEntrustMoney){
                                rowData.doEntrustMoney =JoyinMath.doMul(element.doEntrustMoney,100,2) ;
                            }
                            if( element.etrEntrust.entrustNum){
                                rowData.tradeAmt = element.etrEntrust.entrustNum;//多组合券面总额
                                tradeAmtAll = JoyinMath.doAdd(tradeAmtAll,element.etrEntrust.entrustNum,2);
                            }
                            if(element.etrEntrust.price){
                                rowData.unitCprice  = element.etrEntrust.price;//多组合净价
                            }
                            if(element.etrEntrust.invAim){
                                rowData.investaim = element.etrEntrust.invAim;//投资目的
                            }
                            if(element.etrEntrust.entrustAmt){
                                rowData.tradeAmtAndFee = element.etrEntrust.entrustAmt;//交易金额
                            }
                            if(element.etrEntrustAdd){
                                rowData.secManageAcctId=element.etrEntrustAdd.secAcctId;
                                if(element.etrEntrustAdd.windtypeone){
                                    checkAndAssign("#windtypeone", data[0].etrEntrustAdd.assetType1); // 债券一级分类
                                }
                                if(element.etrEntrustAdd.secAcctId){
                                    rowData.secManageAcctIdDuo = element.etrEntrustAdd.secAcctId;//证券托管户
                                    $('#secManageAcctId').val(element.etrEntrustAdd.secAcctId);
                                }
                            }
                        }
                        //组合T和T+1可以现金
                        if(element.etrEntrust.portfolioId&&$("#calendarId").val() && $("#ccy").val() && $("#tradeDate").val()){
                            if($('#chlId').val()){
                                params.chlId = $('#chlId').val();
                            }
                            params.portfolioId=element.etrEntrust.portfolioId;
                            params.calendarId=$("#calendarId").val();
                            params.tradeDate=$("#tradeDate").val();
                            params.acctTypeList = 'A01,A07';
                            params.ccy=$("#ccy").val();
                            Joyin.ajaxJson(JoyinUtil.getRealPath('/component/trade/BussTrdAvailableCashQuery/getAvailableCash'), params, function (cashdata) {
                                if(cashdata.T_0){
                                    rowData.bussTrdAvailableCashQueryavalibleCash =  cashdata.T_0;
                                }
                                if(cashdata.T_1){
                                    rowData.bussTrdAvailableCashQueryavalibleCash1 =  cashdata.T_1;
                                }
                                if(cashdata.T_2){
                                    rowData.channelAvailableCash =  cashdata.T_2;
                                }
                            },false);
                        }
                        //交易场所可用资金
                        if($('#tradeMarket').val()&&($('#tradeMarket').val()=='03'||$('#tradeMarket').val()=='04')
                            && element.etrEntrustAdd.secAcctId&&element.etrEntrust.portfolioId){
                            var param = {};
                            param.portfolioId=element.etrEntrust.portfolioId;
                            param.secManageAcctId=element.etrEntrustAdd.secAcctId;
                            param.ccy=$('#ccy').val();
                            param.calendarId = $('#calendarId').val();
                            Joyin.ajaxJson(JoyinUtil.getRealPath('/invest/trade/bond/TrdBondInvestTrade003/getBankCash'), param, function (result) {
                                if(result){
                                    rowData.accAvailableCash = Number(result);
                                }

                            },false);
                        }
                        //交易方向为卖出时显示资产可用持仓
                        if(element.etrEntrustAdd.secAcctId&&element.etrEntrust.portfolioId
                            && $("#calendarId").val() && $("#ccy").val() && $("#tradeDate").val()&&!($('#ps').val()=='T01')){

                            params.portfolioId=element.etrEntrust.portfolioId;
                            params.finprodId=$("#finprodId").val();
                            params.calendarId=$("#calendarId").val();
                            params.tradeDate=$("#tradeDate").val();
                            params.acctTypeList = 'A01,A07';
                            params.ccy=$("#ccy").val();
                            params.multiParam=100;
                            params.secAcctId=element.etrEntrustAdd.secAcctId;
                            Joyin.ajaxJson(JoyinUtil.getRealPath('/component/trade/BussTrdAvailableCashQuery/getAvailableCash'), params, function (cashdata) {
                                if( cashdata.position){
                                    rowData.assetsavailablepositions =  cashdata.position;
                                }
                            },false);
                        }
                        rowDatas.push(rowData)
                    });
                }
            },false);
        }
        return rowDatas;
    }
    function psAndspeed(){
    	if($("#entrustId").val()){
    		$("#ps").prop("disabled", true);
    		$("#settleSpeedText").prop("disabled", true);
    	}
    }
    function getEntrustByEntrustId() {
    	var workflowId = $('#workflowId').val();
    	if (workflowId && workflowId != null && workflowId != '') {
    		return;
    	}
        var tradeAmtAllEntrust = 0;
        /**
         * 若从委托场合点击交易录单
         */
        var entrustId = $("#entrustId").val();
        var bondFirstSign = $("#bondFirstSign").val();
        if(fTradeIdFromCstp){
            $('#fTradeId').textSelect('setValue',fTradeIdFromCstp);
            $('#fTradeId').attr('disabled',true);
            // fTradeIdChangeAction();
        }
        if(entrustId && entrustId != null && entrustId != ''){
            $('#PreTrdBondInvestTrade002_distribution').attr('disabled',false);
            Joyin.ajaxJson(basePath + 'invest/trade/bond/TrdBondInvestTrade003/getEntrustInfos', {'entrustId':entrustId,'bondFirstSign':bondFirstSign},function (data) {
                // 基本要素信息由投资委托带出，其中有：委托代码、组合代码、资产代码、清算速度、交易方向、交易场所、交易平台、交易市场、投资目的等，不可编辑
                // 金融要素信息由投资委托带出，其中有：币种、券面总额、净价、到期收益率、行权收益率等
                tradeAmtAll = 0;
                rowDatas = [];
                entrustData = data;
                function sortprice(a,b){
                    return a.etrEntrust.createTime-b.etrEntrust.createTime
                }
                data =  entrustData.sort(sortprice);
                if(data){
                    if(data[0]){
                        tdao = data[0].chlId;
                        deleteSelect('chlId');
                        $('#chlId').textSelect('setValue',  data[0].chlId);//通道;
                        $('#chlId').attr('disabled',true);
                    }
                    if(data[0].etrEntrust){
                        if(data[0].settleSpeedStr){
                            checkAndAssign("#settleSpeedText", data[0].settleSpeedStr); // 清算速度
                        }
                        if(data[0].etrEntrust.finprodId){
                            zcdm = data[0].etrEntrust.finprodId;
                            deleteSelect('finprodId');
                            $('#finprodId').textSelect('setValue', data[0].etrEntrust.finprodId);//债券代码;
                            $('#finprodId').attr('disabled',true);
                        }
                        if(data[0].finprodName){
                            //资产名称文本框根据资产代码选择自动显示对应名称;
                            $("#finprodName").val( data[0].finprodName).trigger('change');
                        }
                        if(data[0].etrEntrust.tradeMarket){
                            setTimeout(function () {
                                jycs = data[0].etrEntrust.tradeMarket;
                                checkAndAssign("#tradeMarket",   data[0].etrEntrust.tradeMarket); // 交易场所
                            },1500)
                        }
                        if(data[0].etrEntrust.tradePlat){
                            jypt = data[0].etrEntrust.tradePlat;
                            checkAndAssign("#tradePlat",   data[0].etrEntrust.tradePlat); // 交易平台
                        }
                        if(data[0].etrEntrust.tradeMarketMode){
                            checkAndAssign("#tradeMarketMode",   data[0].etrEntrust.tradeMarketMode); // 交易市场
                        }
                        if(data[0].etrEntrust.ps){
                            setTimeout(function () {
                                jyfx = data[0].etrEntrust.ps;
                                checkAndAssign("#ps",   data[0].etrEntrust.ps); // 交易方向
                            },1500)
                        }
                        if(data[0].etrEntrust.invAim){
                            checkAndAssign("#invAim",   data[0].etrEntrust.invAim); // 投资目的
                        }
                        if(data[0].etrEntrust.ccy){
                            checkAndAssign("#ccy",   data[0].etrEntrust.ccy); // 币种
                        }
                        if(data[0].etrEntrust.calendarId){
                            checkAndAssign("#calendarId",  data[0].etrEntrust.calendarId); // 交易日历
                        }
                        if(data[0].etrEntrust.price){
                            checkAndAssign("#unitCprice", data[0].etrEntrust.price, true); // 净价
                            entrustPrice = data[0].etrEntrust.price;
                        }

//                        if(data[0].etrEntrustAdd.matureYield){
//                            checkAndAssign("#yield",  data[0].etrEntrustAdd.matureYield); // 到期收益
//                            entrustYield = data[0].etrEntrustAdd.matureYield;
//                        }
//                        if(data[0].etrEntrustAdd.exerYield){
//                            checkAndAssign("#exerYield",  data[0].etrEntrustAdd.exerYield); // 行权收益
//                            entrustExerYield = data[0].etrEntrustAdd.exerYield;
//                        }
                        if(data[0].etrEntrust.counterId){
                            deleteSelect('trdInvestPersonInfo_counterId');
                            $('#trdInvestPersonInfo_counterId').textSelect('setValue', data[0].etrEntrust.counterId);//交易对手;
                            $('#trdInvestPersonInfo_counterId').attr('disabled',true);
                        }
                        if(data[0].etrEntrust.counterTrader){
                            checkAndAssign("#etrInvestPersonInfo_counterTrader",  data[0].etrEntrust.counterTrader); // 对手方交易员
                        }
                    }
                    $.each(data,function (index,element) {
                        var rowData={};
                        var params ={};
                        // 计算交割日
                        calcSettleDate();
                        if(element.etrEntrust){
                            rowData.ps =  element.etrEntrust.ps;
                            rowData.entrustId = element.etrEntrust.entrustId;//委托代码
                            rowData.portfolioId = element.etrEntrust.portfolioId;//多组合组合代码
                            if(element.doEntrustMoney){
                                rowData.doEntrustMoney =JoyinMath.doMul(element.doEntrustMoney,100,2) ;
                            }
                            if( element.etrEntrust.entrustNum){
                                rowData.tradeAmt = element.etrEntrust.entrustNum;//多组合券面总额
                                tradeAmtAll = JoyinMath.doAdd(tradeAmtAll,element.etrEntrust.entrustNum,2);
                                tradeAmtAllEntrust = JoyinMath.doAdd(tradeAmtAllEntrust,element.etrEntrust.entrustNum,2);
                            }
                            if(element.etrEntrust.price){
                                rowData.unitCprice  = element.etrEntrust.price;//多组合净价
                            }
                            if(element.etrEntrust.invAim){
                                rowData.investaim = element.etrEntrust.invAim;//投资目的
                            }
                            if(element.etrEntrust.entrustAmt){
                                rowData.tradeAmtAndFee = element.etrEntrust.entrustAmt;//交易金额
                            }
                            if(element.etrEntrustAdd){
                                rowData.secManageAcctId=element.etrEntrustAdd.secAcctId;
                                if(element.etrEntrustAdd.windtypeone){
                                    checkAndAssign("#windtypeone", data[0].etrEntrustAdd.assetType1); // 债券一级分类
                                }
                                if(element.etrEntrustAdd.secAcctId){
                                    rowData.secManageAcctIdDuo = element.etrEntrust.portfolioId+element.etrEntrustAdd.secAcctId;//证券托管户
                                    $('#secManageAcctId').val(element.etrEntrustAdd.secAcctId);
                                }
                            }
                        }
                        //组合T和T+1可以现金
                        if(element.etrEntrust.portfolioId&&$("#calendarId").val() && $("#ccy").val() && $("#tradeDate").val()){
                            if($('#chlId').val()){
                                params.chlId = $('#chlId').val();
                            }
                            params.portfolioId=element.etrEntrust.portfolioId;
                            params.calendarId=$("#calendarId").val();
                            params.tradeDate=$("#tradeDate").val();
                            params.acctTypeList = 'A01,A07';
                            params.ccy=$("#ccy").val();
                            Joyin.ajaxJson(JoyinUtil.getRealPath('/component/trade/BussTrdAvailableCashQuery/getAvailableCash'), params, function (cashdata) {
                                if(cashdata.T_0){
                                    rowData.bussTrdAvailableCashQueryavalibleCash =  cashdata.T_0;
                                }
                                if(cashdata.T_1){
                                    rowData.bussTrdAvailableCashQueryavalibleCash1 =  cashdata.T_1;
                                }
                                if(cashdata.T_2){
                                    rowData.channelAvailableCash =  cashdata.T_2;
                                }
                            },false);
                        }
                        //交易场所可用资金
                        if($('#tradeMarket').val()&&($('#tradeMarket').val()=='03'||$('#tradeMarket').val()=='04')
                            && element.etrEntrustAdd.secAcctId&&element.etrEntrust.portfolioId){
                            var param = {};
                            param.portfolioId=element.etrEntrust.portfolioId;
                            param.secManageAcctId=element.etrEntrustAdd.secAcctId;
                            param.ccy=$('#ccy').val();
                            param.calendarId = $('#calendarId').val();
                            Joyin.ajaxJson(JoyinUtil.getRealPath('/invest/trade/bond/TrdBondInvestTrade003/getBankCash'), param, function (result) {
                                if(result){
                                    rowData.accAvailableCash = Number(result);
                                }

                            },false);
                        }
                        //交易方向为卖出时显示资产可用持仓
                        if(element.etrEntrustAdd.secAcctId&&element.etrEntrust.portfolioId
                            && $("#calendarId").val() && $("#ccy").val() && $("#tradeDate").val()&&!($('#ps').val()=='T01')){

                            params.portfolioId=element.etrEntrust.portfolioId;
                            params.finprodId=$("#finprodId").val();
                            params.calendarId=$("#calendarId").val();
                            params.tradeDate=$("#tradeDate").val();
                            params.acctTypeList = 'A01,A07';
                            params.ccy=$("#ccy").val();
                            params.multiParam=100;
                            params.secAcctId=element.etrEntrustAdd.secAcctId;
                            Joyin.ajaxJson(JoyinUtil.getRealPath('/component/trade/BussTrdAvailableCashQuery/getAvailableCash'), params, function (cashdata) {
                                if( cashdata.position){
                                    rowData.assetsavailablepositions =  cashdata.position;
                                }
                            },false);
                        }
                        rowDatas.push(rowData)
                    });
                }
            },false);
            // $('#tradeAmt').val(tradeAmtAll/10000).trigger("blur");
            $('#tradeAmt').val(tradeAmtAll).trigger("blur");
            setTimeout(function () {
                table.addRow(rowDatas);
                setMoney(rowDatas);
                if(fTradeId){//cstp匹配交易过来的时候
                    if(prinamt<tradeAmtAllEntrust){
                        // $('#tradeAmt').val(prinamt/10000).trigger('change').trigger("blur");
                        $('#tradeAmt').val(prinamt).trigger('change').trigger("blur");
                        extractedNumber();
                    }
                }
                $('.btn-table-add').remove();
                setTimeout(function () {
                    $('.delt').remove();
                },1000)
            },1500);
        }
    }

    function setMoney(rowDatas){
        $.each(rowDatas,function (index,element) {
            element.LAY_TABLE_INDEX = index;
            element.tradeAmt = tradeAmtAll;
            element.isEntrust = 'YES';
            element.secManageAcctIdDuo=element.secManageAcctId;
            element.ps =  element.ps;
//            extractedJiSunFeiYong(element);
        })
    }
    //画面初始化
    var init = function() {
        initPayType();
        showOrhide();
        psAndspeed();
        getEntrustByEntrustId();
        
        exerYieldDivContrl();
        
    };
    window.componentLoadCallback = function(isDone, pagePath, params){
        if(isDone){
            if(typeof(BaseRiskMathComm) != 'undefined') {
            var riskparam = getriskdata();
            if (pageModel==PAGE_MODEL_NEW){
                riskparam.data =null;
            }
            window.BaseRiskMathComm.getparam(riskparam);
            }
            if (formTodoList) {
//                window.BussTrdTransferOrder['bussTrdTransferOrder'].saveCalcCondition();
            }
            window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'].showCash();
            if(entrustId != ''){
                if(etrEntrust){
                    window.TrdInvestPersonInfo002['trdInvestPersonInfo'].setEntrust(function () {
                        return etrEntrust;
                    });
                }
            }
            
            exerYieldDivContrl();

            // 设置默认选中净价
            $("#calculateField_unitCprice").attr("checked", true);
            layui.form.render('radio');
        }
    };
    /**
     * 控制显示行权收益率
     */
    var exerYieldDivContrl = function() {
        // 11、  是否含权债为是，且未来有行权计划时，行权收益率显示，其余情况不显示；
//        if ($("#isRight").val() == YES_OR_NO_Y) {
        var paramObj_isExerYieldDivShow = {};
        paramObj_isExerYieldDivShow.finprodId = $('#finprodId').val();
        paramObj_isExerYieldDivShow.tradeDate = $('#tradeDate').val();
        Joyin.ajax(basePath + 'invest/trade/bond/TrdBondInvestTrade003/isExerYieldDivShow', paramObj_isExerYieldDivShow, function (data) {
            if (data == YES_OR_NO_Y) {
                $("#exerYieldDiv").show();
            } else {
                $("#exerYieldDiv").hide();
            }
        });
    };
    
    var updateSecAcctBalance = function(){
        window.BussTrdAvailableCashQuery && window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'].updateSecAcctBalance(function () {
            return {
                'ccy': $("#ccy").val(),
                'portfolioId': $("#portfolioId").val(),
                'tradeDate':$('#tradeDate').val()
            }
        });
    };

    var updateCash = function(){
        window.BussTrdAvailableCashQuery && window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'].updateCash(function () {
            return {
                'tradeDate': $("#tradeDate").val(),
                'calendarId': $("#calendarId").val(),
                'ccy': $("#ccy").val(),
                'portfolioId': $("#portfolioId").val(),
                'finprodId': $("#finprodId").val(),
                'secAcctId': $("#secManageAcctId").val(),
                'multiParam' : 100
                // 一体化对应etliu start
//                , 'invAim': $("#invAim").val()
                // 一体化对应etliu end
                // 通道交易 新增 强成西 2019/03/26 start
//                , 'chlId': $("#chlId").val()
                // 通道交易 新增 强成西 2019/03/26 end
            }
        });
    };


    


    /**
     * 画面加载完成
     */
    $(function() {
        //初始化画面
        init();

        // 绑定按钮事件
        $('#PreTrdBondInvestTrade002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#PreTrdBondInvestTrade002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
      //风险试算
        $('#PreTrdBondInvestTrade002_btn_risk').click(function(){
            Joyin.ajax(basePath + 'invest/trade/bond/TrdBondInvestTrade003/selectIndex',{},function(result){
                if(result){
                    var index=result;
                    var object1=getriskdata();
                    /*var list2=object1.data;
                    for(var i in list2){
                        object1.data[i].mun=list2.length;
                        object1.data[i].index=index;
                    }*/
                    //window.BaseRiskMathComm.initBaseRiskMathCommPage(object1);
                    
                }
                //window.BaseRiskMathComm.initBaseRiskMathCommPage2(object1);
                $("#riskMathFlag").val("02")
            });
        });

        /**
         * 画面联动处理
         * 1.1交易场所
         */
        $('#tradeMarket').on("change", function () {
            var tradeMarket = $('#tradeMarket').val();
            if (tradeMarket) {
                if (tradeMarket == TRADE_PLACE_01) {

                    table.clear();

                    // 选择【银行间】时，
                    // 1)交易平台默认为银行间且不可修改；
                    // 2)需输入银行间成交编号，相关交易要素根据成交编号作为输入参数调用接口自动带出;
                    if(!$("#fTradeId").val()){
                        $("#fTradeId").attr("disabled", false);
                    }
                    $("#fTradeId").associateInput();

         
                } else if (tradeMarket == TRADE_PLACE_02 || tradeMarket == TRADE_PLACE_03) {

                    table.clear();
                     if($('#fTradeId').val()){
                         table.addRow(getAllRowsForEntrustId());
                     }
                    // 选择【上海证券交易所】或【深圳证券交易所】时，
                    // 1)交易平台可自主选择;
//                    if($("#entrustId").val() != ''){
//                        $("#tradePlat").attr("disabled", true);
//                    }else{
//                        $("#tradePlat").attr("disabled", false);
//                    }
                    // 3)无交易成交单 此项不可填
                     $("#fTradeId").val("").attr("disabled", true);
                    //
                    //----add by 裴家俊  2019/03/28-----
//                    $("#procedureAmtDiv").hide();
//                    $("#procedureAmtRateDiv").hide();
//                    $("#settleAmtDiv").hide();
//                    $("#commissionAmtDiv").show();
//                    $("#commissionAmtRateDiv").show();
                } else {
//                    table.showColumns([{field:'accAvailableCash',visible: false}]);
                    table.clear();
                    // 1)交易平台可自主选择;
                    // 1)交易平台默认为其它，可改
//                    checkAndAssign("#tradePlat", SHARES_TRADE_PLATFORM_99, true);
                    if($("#entrustId").val() != ''){
//                        $("#tradePlat").attr("disabled", true);
                    }else{
//                        $("#tradePlat").attr("disabled", false);
                    }
                    // 3)无交易成交单 此项不可填
                    $("#fTradeId").val("").trigger("change").attr("disabled", true);


                }
            }
        });


        /**
         * 画面联动处理
         * 1.3交易方向
         */
        $("#ps").on("change", function () {
            var ps = $("#ps").val();
            // 一体化对应etliu start
//        if (ps == PS_T02 || ps == PS_T03) {
            if (ps == PS_T02 || ps == PS_T03 || ps == PS_T39) {
                //隐藏多行表单的字段
                // $('#tradeAmtAndFeeForm').val('').hide();//交易金额隐藏
                table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash',visible: false}]);
                table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash1',visible: false}]);
//                table.showColumns([{field:'channelAvailableCash',visible: false}]);
//                table.showColumns([{field:'accAvailableCash',visible: false}]);
                // table.showColumns([{field:'assetsavailablepositions',visible: false}]);
                table.clear();
                // 一体化对应etliu end
                if (pageModel == '0') {
                    $("#yield").val("").trigger("change").attr("disabled", true);
                    $("#exerYield").val("").trigger("change").attr("disabled", true);
                }
                $("#calculateField_yield").attr("disabled", true);
                $("#calculateField_exerYield").attr("disabled", true);
                if($("#zhilingtype").val() =="02"){
                    window.BussTrdAvailableCashQuery && window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'].hidePosition(true);
                }else{
                    window.BussTrdAvailableCashQuery && window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'].showPosition(true);
                }
            }
            else if (ps == PS_T01) {
                // $('#tradeAmtAndFeeForm').show();
                table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash',visible: true}]);
                table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash1',visible: true}]);
//                table.showColumns([{field:'channelAvailableCash',visible: true}]);
                if($('#tradeMarket').val()=='03'||$('#tradeMarket').val()=='04'){
//                    table.showColumns([{field:'accAvailableCash',visible: true}]);
                }
                // table.showColumns([{field:'assetsavailablepositions',visible: true}]);
                table.clear();
                 if($('#fTradeId').val()){
                     table.addRow(getAllRowsForEntrustId());
                 }
                $("#yield").attr("disabled", false);
                $("#exerYield").attr("disabled", false);
                $("#calculateField_yield").attr("disabled", false);
                $("#calculateField_exerYield").attr("disabled", false);
                if($("#zhilingtype").val() =="02"){
                    window.BussTrdAvailableCashQuery && window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'].hideCash(true);
                }else{
                    window.BussTrdAvailableCashQuery && window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'].showCash(true);
                }
            }
            else {
                if($("#zhilingtype").val() =="02"){
                    window.BussTrdAvailableCashQuery && window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'].hideCash(true);
                }else{
                    window.BussTrdAvailableCashQuery && window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'].showCash(true);
                }
            }
        });

        $('#fTradeId').on("change",function(){
            if (pageModel==PAGE_MODEL_NEW&&!fTradeId) {
                fTradeIdChangeAction();
            }
        });
        if(isCstp&&fTradeId){
            fTradeIdChangeAction();
        }
    });

    // 当是"债券中标结果查询"中进入来缴款时，为部分数据进行初始化
    function initPayType(){
        var bondFirstSign = $("#bondFirstSign").val();

        if (!bondFirstSign) {
            $('#payType_div').hide();
            return;
        }
        $('#settleSpeedText').attr("disabled", false);
        $('#yield').attr("disabled", true);
        $('#tradeAmt').attr("disabled", false);

    }
    var updateBussTrdTransfenpeiTable =function(){
    	for(var i=0;i<table.getAllRows().length;i++){
    		if($("#unitCprice").val()){
    			//获取i行的券面总额
        		var tradeAmt=table.getAllRows()[i].tradeAmt;
        		table.renderRow(i, {"cpriceAmt": Number($("#unitCprice").val())*Number(tradeAmt)/100});
    		}else{
    			table.renderRow(i, {"cpriceAmt": 0});
    		}
    	}
    };

   
	//按照比例计算净价总额
    function extractedCpriceAmt() {
        // var tradeAmtAll = $('#tradeAmt').val()*10000;
        var tradeAmtAll = $('#tradeAmt').val();
        var all = 0;
        for (var i = 0; i < table.getAllRows().length; i++) {
            var tradeAmt = table.getAllRows()[i].tradeAmt;
            var result = JoyinMath.doDiv(tradeAmt, tradeAmtAll, 2);
            if ($('#cpriceAmt').val()) {
                if(table.getAllRows().length>1){
                    if (i != table.getAllRows().length - 1) {//非最后一列
                        var cpriceAmt =  JoyinMath.doMul(isNaN(tradeAmt)?0:tradeAmt,$('#cpriceAmt').val(),2);
                        cpriceAmt = JoyinMath.doDiv(cpriceAmt,tradeAmtAll,2);
                        all = JoyinMath.doAdd(all, cpriceAmt, 2);
                        table.renderRow(i, {"cpriceAmt": cpriceAmt});
                    } else {
                        table.renderRow(i, {"cpriceAmt": JoyinMath.doMinus($('#cpriceAmt').val(), all, 2)});
                    }
                }else{
                    var cpriceAmt =  JoyinMath.doMul(isNaN(tradeAmt)?0:tradeAmt,$('#cpriceAmt').val(),2);
                    cpriceAmt = JoyinMath.doDiv(cpriceAmt,tradeAmtAll,2);
                    all = JoyinMath.doAdd(all, cpriceAmt, 2);
                    table.renderRow(i, {"cpriceAmt": cpriceAmt});
                }

            }
        }
    }
    //按照比例计算应计利息总额
    function extractedIntAmt(intAmtPara) {
        // var tradeAmtAll = $('#tradeAmt').val()*10000;
        var tradeAmtAll = $('#tradeAmt').val();
        var all = 0;
        for (var i = 0; i < table.getAllRows().length; i++) {
            var tradeAmt = table.getAllRows()[i].tradeAmt;
            if (typeof intAmtPara != 'undefined') {
                table.renderRow(i, {"intAmt": intAmtPara});
            } else if ($('#intAmt').val()) {
                if(table.getAllRows().length>1){
                    if (i != table.getAllRows().length - 1) {//非最后一列
                        var intAmt =  JoyinMath.doMul(tradeAmt, $('#intAmt').val(),2);
                        intAmt =  JoyinMath.doDiv(intAmt,tradeAmtAll,2);
                        all = JoyinMath.doAdd(all, intAmt, 2);
                        table.renderRow(i, {"intAmt": intAmt});
                    } else {
                        table.renderRow(i, {"intAmt": JoyinMath.doMinus($('#intAmt').val(), all, 2)});
                    }
                }else{
                    var intAmt =  JoyinMath.doMul(tradeAmt, $('#intAmt').val(),2);
                    intAmt =  JoyinMath.doDiv(intAmt,tradeAmtAll,2);
                    all = JoyinMath.doAdd(all, intAmt, 2);
                    table.renderRow(i, {"intAmt": intAmt});
                }

            }
        }
    }

    function extractedJiSuan(isTrue,returnVal) {
        // var tradeAmtAl = $('#tradeAmt').val()*10000;
        var tradeAmtAl = $('#tradeAmt').val();
        var alltradeAmt = 0;
        for (var i = 0; i < table.getAllRows().length; i++) {
            var tradeAmt = table.getAllRows()[i].tradeAmt;
            var doEntrustMoney = table.getAllRows()[i].doEntrustMoney;
            var result = JoyinMath.doDiv(tradeAmt, tradeAmtAl, 2);
            //先设置劵面总额
            if(isTrue){
                if($('#tradeAmt').val()){
                    if(table.getAllRows().length>1){
                        if (i != table.getAllRows().length - 1) {//非最后一列
                            var num  = JoyinMath.doMul(tradeAmt,tradeAmtAl,2);
                            var iNum = JoyinMath.doDiv(num,tradeAmtAll,2);
                            if(Number(iNum)<=Number(doEntrustMoney)){
                                table.renderRow(i, {"tradeAmt": iNum});
                                alltradeAmt = JoyinMath.doAdd(alltradeAmt,iNum,2);
                            }else{
                                table.renderRow(i, {"tradeAmt": doEntrustMoney});
                                alltradeAmt = JoyinMath.doAdd(alltradeAmt,doEntrustMoney,2);
                            }
                        }else{
                            var numTo = JoyinMath.doMinus(tradeAmtAl,alltradeAmt,2);
                            if(Number(numTo)<=Number(doEntrustMoney)){
                                table.renderRow(i, {"tradeAmt": numTo});
                            }else{
                                table.renderRow(i, {"tradeAmt": doEntrustMoney});
                            }
                        }
                    }else{
                        var num  = JoyinMath.doMul(tradeAmt,tradeAmtAl,2);
                        var iNum = JoyinMath.doDiv(num,tradeAmtAll,2);
                        if(Number(iNum)<=Number(doEntrustMoney)){
                            table.renderRow(i, {"tradeAmt": iNum});
                            alltradeAmt = JoyinMath.doAdd(alltradeAmt,iNum,2);
                        }else{
                            table.renderRow(i, {"tradeAmt": doEntrustMoney});
                            alltradeAmt = JoyinMath.doAdd(alltradeAmt,doEntrustMoney,2);
                        }
                    }

                }
            }

        }
    }
    var initfenpeiTable = function(){
		$("#portfolioId").attr("disabled", true);
		$('#bussTrdAvailableCashQuery_div_cash').show();
		$("#tradeAmt").attr("disabled", false);
		$("#fpriceAmt").attr("disabled", false);
			//组合分配页面展示
			$('#bussTrdTransfenpei_div').show();
			//委托代码置灰
			$("#portfolioId").attr("disabled", true);
			$("#portfolioId ").empty();
			window.BussTrdAvailableCashQuery && window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'].hideCash(true);
			$("#tradeAmt").attr("disabled", false);
			//多组合时全价总额自动计算
			$("#fpriceAmt").attr("disabled", false);
			if(!$("#bussTrdTransfenpeiTable").data('joyintable')){}
	  
	};

    /**
     * 银行间成交编号输入时从cstp查询数据带出
     */
    var fTradeIdChangeAction = function(){
        var isTrue = false;
        var fTradeId = $('#fTradeId').val();
        $('#finprodId').attr('disabled', false);
        $('#ps').attr('disabled', false);
        $("#settleSpeedText").attr('disabled', false);
        $("#tradeMarketMode").attr('disabled', false);

        // 设置默认选中净价
        $("#calculateField_unitCprice").attr("checked", true);
        layui.form.render('radio');

        $('#unitCprice').attr('disabled', false);
        $('#cpriceAmt').attr('disabled', false);
        $('#intAmt').attr('disabled', false);
        $('#tradeAmt').attr('disabled', false);
        $('#fpriceAmt').attr('disabled', false);
        $('#yield').attr('disabled', false);
        $('#trdInvestPersonInfo_counterTrader').attr('disabled', false);
        $('#trdInvestPersonInfo_counterId').attr('disabled', false);
        if (fTradeId && fTradeId != ''){
            isCSTP = true;
            Joyin.ajax(basePath + 'invest/trade/bond/TrdBondInvestTrade002/querySecCstpInfoByNo', {fedealno : fTradeId}, function(result){
                if (result){
                    if (result.secid && result.secid != ''){
                        deleteSelect('finprodId');
                        $('#finprodId').textSelect('setValue', result.secid);//债券代码;
                        if($('#finprodId').val()){
                            $('#finprodId').attr('disabled', true);
                        }
                        // 交易市场
                        $('#tradeMarketMode').val(SHARES_TRADE_MARKET_02).trigger('change');
                        if($('#tradeMarketMode').val()){
                            $('#tradeMarketMode').attr('disabled', true);
                        }
                    }else{
                        $('#finprodName').val('');
                        deleteSelect('tradeMarket');
                        deleteSelect('tradeMarketMode');
//                        deleteSelect('tradePlat');
                        deleteSelect('type9');
//                        deleteSelect('type10');
//                        deleteSelect('type11');
                        deleteSelect('finprodId');
                        $('#finprodId').attr('disabled', false);
                        $('#tradeMarketMode').attr('disabled', false);
                    }
                    if (result.ccy && result.ccy != ''){
                        $('#ccy').val(result.ccy).trigger('change');//币种代码
                        if($('#ccy').val()){
                            $('#ccy').attr('disabled', true);
                        }
                    }else{
                        $('#ccy').val('');
                        $('#ccy').attr('disabled', false);
                    }
                    // 投资类型
                    if (result.ps && result.ps != ''){
                        $('#ps').val(result.ps).trigger('change');// 买卖标识
                        if($('#ps').val()){
                            $('#ps').attr('disabled', true);
                        }
                    }else{
                        deleteSelect('ps');
                        $('#ps').attr('disabled', false);
                    }
                    if (result.price8 && result.price8 != ''){
                        $('#unitCprice').val(result.price8).trigger('change');// 价格
                        if($('#unitCprice').val()){
                            $('#unitCprice').attr('disabled', true);
                        }
                    }else{
                        $('#unitCprice').val('');
                        $('#unitCprice').attr('disabled', false);
                    }
                    if (result.yield8 && result.yield8 != ''){
                        var number =  result.yield8*100;
                        $('#yield').val(number).trigger('change');// 收益率
                        if($('#yield').val()){
                            $('#yield').attr('disabled', true);
                        }
                    }else{
                        $('#yield').val('');
                        $('#yield').attr('disabled', false);
                    }
                    // 交易对手号代码
                    // 票值
                    if (result.prinamt && result.prinamt != ''){
                        // $('#tradeAmt').val((result.prinamt)/10000).trigger('change').trigger("blur");// 本金金额(券面总额)
                        $('#tradeAmt').val(result.prinamt).trigger('change').trigger("blur");
                        if($('#tradeAmt').val()){
                            $('#tradeAmt').attr('disabled', false);
                        }
                    }else{
                        $('#tradeAmt').val('');
                        $('#tradeAmt').attr('disabled', false);
                    }
                    if (result.settamt && result.settamt != ''){
                        $('#fpriceAmt').val(result.settamt).trigger('change').trigger("blur");// 结算金额(全价总额)
                        if($('#fpriceAmt').val()){
                            $('#fpriceAmt').attr('disabled', true);
                        }
                    }else{
                        $('#fpriceAmt').val('');
                        $('#fpriceAmt').attr('disabled', false);
                    }
                    // 产品
                    // 业务类型
                    // 交易员
                    if (result.convintamt && result.convintamt != ''){
                        $('#intAmt').val(result.convintamt).trigger('change').trigger("blur");// 转换利息金额
                        if($('#intAmt').val()){
                            $('#intAmt').attr('disabled', true);
                        }
                    }else{
                        $('#intAmt').val('');
                        $('#intAmt').attr('disabled', false);
                    }
                    // 有效状态
                    // 经纪人
                    // 录入日期
                    if (result.dealDateStr){
                        $('#tradeDate').val(result.dealDateStr).trigger('change');// 交易日期
                        if($('#tradeDate').val()){
                            $('#tradeDate').attr('disabled', true);
                        }
                    }else{
                        $('#tradeDate').val($('#formatDate').val());
                        $('#tradeDate').attr('disabled', true);
                    }

                    if (result.settDateStr){
                        $('#settleDate').val(result.settDateStr).trigger('change');// 交割日
                        if($('#settleDate').val()){
                            $('#settleDate').attr('disabled', true);
                        }
                    }else{
                        $('#settleDate').val('');
                        $('#settleDate').attr('disabled', true);
                    }
                    // 冲销日期
                    // 更新的次数
                    // 原始交易编号
                    if (result.dealtext && result.dealtext != ''){
                        $('textarea[name="remark"]').val(result.dealtext).trigger('change')
                        // $('#remark').val(result.dealtext).trigger('change');// 备注
                    }else{
                        $('textarea[name="remark"]').val('');
                    }
                    // 更新时间
                    if (result.cno && result.cno != ''){
                        $('#trdInvestPersonInfo_counterId').textSelect('setValue', result.cno);// 交易对手
                    }else{
                        deleteSelect('trdInvestPersonInfo_counterId');
                    }
                    if (result.clienttrader && result.clienttrader != ''){
                        $('#trdInvestPersonInfo_counterTrader').val(result.clienttrader);// 对手方交易员
                        if($('#trdInvestPersonInfo_counterTrader').val()){
                            $('#trdInvestPersonInfo_counterTrader').attr('disabled', true);
                        }
                    }else{
                        $('#trdInvestPersonInfo_counterTrader').val('');
                        $('#trdInvestPersonInfo_counterTrader').attr('disabled', false);
                    }
                    if (result.countertelephone && result.countertelephone != ''){
                        $('#trdInvestPersonInfo_contactInfo').val(result.countertelephone);// 联系方式
                        if($('#trdInvestPersonInfo_contactInfo').val()){
                            $('#trdInvestPersonInfo_contactInfo').attr('disabled', true);
                        }
                    }else{
                        $('#trdInvestPersonInfo_contactInfo').val('');
                        $('#trdInvestPersonInfo_contactInfo').attr('disabled', false);
                    }
                    // 根据CSTP金额信息计算其他要素
                    // 清算速度
                    if (result.settleSpeed){
                        $('#settleSpeedText').attr('disabled', true);
                        $("#settleSpeedText").val(result.settleSpeed).trigger('change');
                    }else{
                        deleteSelect('settleSpeed');
                        $('#settleSpeedText').trigger('change');
                        $('#settleSpeedText').attr('disabled', false);
                        $('#settleDate').val('');
                    }
                    // 单位应计利息
                    if (result.accruedinterestamt||result.unitInt){
                        if(result.accruedinterestamt){
                            $('#unitInt').val(result.accruedinterestamt).trigger('blur');
                        }
                        if(result.unitInt){
                            $('#unitInt').val(result.unitInt).trigger('blur');
                        }
                        if($('#unitInt').val()){
                            $('#unitInt').attr('disabled', true);
                        }
                    }else {
                        $('#unitInt').val('');
                        $('#unitInt').attr('disabled', false);
                    }
                    // 全价
                    if (result.dirtyprice && result.dirtyprice != ''){
                        $('#unitFprice').val(result.dirtyprice).trigger('blur');
                        if($('#unitFprice').val()){
                            $('#unitFprice').attr('disabled', true);
                        }
                    }else{
                        $('#unitFprice').val();
                        $('#unitFprice').attr('disabled', false);
                    }
                    // 净价总额 = 净价 * 券面总额 / 100
                    if (result.prinamt && result.prinamt != '' && result.price8 && result.price8 != ''){
                        $('#cpriceAmt').val(JoyinMath.doDiv(JoyinMath.doMul(result.price8, result.prinamt), 100, 2)).trigger('blur');
                        if($('#cpriceAmt').val()){
                            $('#cpriceAmt').attr('disabled', true);
                        }
                    }else{
                        $('#cpriceAmt').val('');
                        $('#cpriceAmt').attr('disabled', false);
                    }
                    // 行权收益率
                    if (result.strikeyield && result.strikeyield != ''){
                        $('#exerYield').val(result.strikeyield).trigger('blur');
                        if($('#exerYield').val()){
                            $('#exerYield').attr('disabled', true);
                        }
                    }else{
                        $('#exerYield').val('');
                        $('#exerYield').attr('disabled', false);
                    }

                    // 银行间成交编号联动费用设置
                    calcFeeDataByFTradeId();
                    table.clear();
                    table.addRow(getAllRowsForEntrustId());
                    setMoney(getAllRowsForEntrustId())
                }
            })
        }else{
            isCSTP = false;
            if ($('#finprodId').val()){
                $('#finprodName').val('');
                deleteSelect('finprodId');
                $('#finprodId').attr('disabled', false);
            }
            if($('#tradeMarket').val()){
                deleteSelect('tradeMarket');
            }
            if($('#tradeMarketMode').val()){
                deleteSelect('tradeMarketMode');
                $('#tradeMarketMode').attr('disabled', false);
            }
            if($('#type9').val()){
                deleteSelect('type9');
            }
//            if($('#type10').val()){
//                deleteSelect('type10');
//            }
//            if($('#type11').val()){
//                deleteSelect('type11');
//            }
            if ($('#ccy').val()){
                $('#ccy').val('');
                $('#ccy').attr('disabled', false);
            }
            // 投资类型
            if ($('#ps').val()){
                deleteSelect('ps');
                $('#ps').attr('disabled', false);
            }
            if ($('#unitCprice').val()){
                $('#unitCprice').val('');
                $('#unitCprice').attr('disabled', false);
            }
            if ($('#yield').val()){
                $('#yield').val('');
                $('#yield').attr('disabled', false);
            }
            // 交易对手号代码
            // 票值
            if ($('#tradeAmt').val()){
                $('#tradeAmt').val('');
                $('#tradeAmt').attr('disabled', false);
            }
            if ($('#fpriceAmt').val()){
                $('#fpriceAmt').val('');
                $('#fpriceAmt').attr('disabled', false);
            }
            // 产品
            // 业务类型
            // 交易员
            if ($('#intAmt').val()){
                $('#intAmt').val('');
                $('#intAmt').attr('disabled', false);
            }
            // 有效状态
            // 经纪人
            // 录入日期
            if ($('#tradeDate').val()){
                $('#tradeDate').val($('#formatDate').val());
                $('#tradeDate').attr('disabled', true);
            }
            if ($('#settleDate').val()){
                $('#settleDate').val('');
                $('#settleDate').attr('disabled', true);
            }
            // 冲销日期
            // 更新的次数
            // 原始交易编号
            if ( $('#remark').val()){
                $('#remark').val('');
            }
            // 更新时间
            if ($('#trdInvestPersonInfo_counterId').val()){
                deleteSelect('trdInvestPersonInfo_counterId');
            }
            if ( $('#trdInvestPersonInfo_counterTrader').val()){
                $('#trdInvestPersonInfo_counterTrader').val('');
                $('#trdInvestPersonInfo_counterTrader').attr('disabled', false);
            }
            if ( $('#trdInvestPersonInfo_contactInfo').val()){
                $('#trdInvestPersonInfo_contactInfo').val('');
                $('#trdInvestPersonInfo_contactInfo').attr('disabled', false);
            }
            // 根据CSTP金额信息计算其他要素
            // 清算速度
            if ($('#settleSpeedText').val()){
                deleteSelect('settleSpeedText');
                $('#settleSpeedText').trigger('change');
                $('#settleSpeedText').attr('disabled', false);
                $('#settleDate').val('');
            }
            // 单位应计利息
            if ( $('#unitInt').val()){
                $('#unitInt').val('');
                $('#unitInt').attr('disabled', false);
            }
            // 全价
            if (  $('#unitFprice').val()){
                $('#unitFprice').val('');
                $('#unitFprice').attr('disabled', false);
            }
            // 净价总额 = 净价 * 券面总额 / 100
            if (  $('#cpriceAmt').val()){
                $('#cpriceAmt').val('');
                $('#cpriceAmt').attr('disabled', false);
            }
            // 行权收益率
            if (  $('#exerYield').val()){
                $('#exerYield').val('');
                $('#exerYield').attr('disabled', false);
            }
            if($('#bussTrdAvailableCashQuery_assetPosition').val()){
                $('#bussTrdAvailableCashQuery_assetPosition').val('');
                $('#bussTrdAvailableCashQuery_assetPosition').attr('disabled', false);
            }
            // 银行间成交编号联动费用设置
            calcFeeDataByFTradeId();
            table.clear();
            table.addRow(getAllRowsForEntrustId());
            setMoney(getAllRowsForEntrustId())
        }
    }

    var calcFeeDataByFTradeId = function () {
        var paramObj = $.serializeToObject('#submitForm', true);
        paramObj.calculateField = $("[name=calculateField]:checked").val();
        // paramObj.tradeAmt = $("#tradeAmt").val()*10000;
        paramObj.tradeAmt = $("#tradeAmt").val();
        paramObj.unitCprice = $("#unitCprice").val();
        paramObj.unitInt = $("#unitInt").val();
        paramObj.unitFprice = $("#unitFprice").val();
        paramObj.cpriceAmt = $("#cpriceAmt").val();
        paramObj.intAmt = $("#intAmt").val();
        paramObj.fpriceAmt = $("#fpriceAmt").val();
        paramObj.exerYield = $("#exerYield").val();
        paramObj.yield = $("#yield").val();

        // 计算之前，先将应计利息清空
        var tradeMarketMode = $("#tradeMarketMode").val();
        if (tradeMarketMode != SHARES_TRADE_MARKET_01) {
            paramObj.unitInt = null;
        }
    };

    var deleteSelect = function (id) {
        $('#'+id).val('').trigger('change');
    }
	/**
     * 参数是json字符串的ajax请求，必须是Post请求
     */
    var ajaxJsonSync = function (url, param, callback) {
        var elementIdForLog = Joyin.getElementId(url);
        if (url && url.indexOf("pageIdForLog") == -1) {
            if (url.indexOf("?") != -1) {
                if (pageId) {
                    url += "&pageIdForLog=" + pageId + "&elementIdForLog=" + elementIdForLog;
                }

            } else {
                if (pageId) {
                    url += "?pageIdForLog=" + pageId + "&elementIdForLog=" + elementIdForLog;
                }
            }
        }
        // 是否为待办事项标识
        if (param) {
            url += (url.indexOf("?") != -1 ? '&' : '?') + 'isTodoList=' + isTodoList;
        }
        param = JSON.stringify(param);
        if(url && url.indexOf(basePath) != 0 && url.indexOf('/') == 0) {
            url = JoyinUtil.getRealPath(url);
        }
        // “保存”、“提交”操作时，记录画面修改值
        if(elementIdForLog && (elementIdForLog.indexOf('_submit') != -1 || elementIdForLog.indexOf('_save') != -1)) {
            var data = JSON.parse(param);
            if (elementIdForLog.indexOf('_submit') != -1 && data.businessInfo) {
                JoyinUtil.setModifyFormData(JSON.parse(data.businessInfo));
            } else {
                JoyinUtil.setModifyFormData(data);
            }
        }
        $.ajax({
            type: "POST",
            url: url,
            dataType: "json",
            contentType: "application/json",
            data: param,
            async:false,
            success: function (result) {
                Joyin.processResult(result, callback);
            }
        });
    };
    //是否展示资产四五级分类
    /*if(IS_SHOW_TYPE == '01'){
    	$('#type11Div').show();
    }else{
    	$('#type11Div').hide();
    }*/
}());
