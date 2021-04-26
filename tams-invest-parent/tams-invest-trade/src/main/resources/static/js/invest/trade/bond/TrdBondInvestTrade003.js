var table;

(function () {
    var returnValAll={
        isPayWithTrade:'Y',
        procedureAmt:0,
        settleServiceAmt:0,
        settleAmt:0,
        commissionAmt:0,
        channelFee:0,
        stampDutyAmt:0,
        exchangeAmt:0,
        stampDutyIsPayWithTrade:'Y',
        settleIsPayWithTrade:'Y',
        exchangeIsPayWithTrade:'Y'
    };
    var entrustNum; // 委托时的券面总额
    var entrustPrice; // 委托时的净价
    var entrustYield; // 委托时的到期收益率
    var entrustExerYield; // 委托时的行权收益率
    var commissionAmt=0;// 佣金
    var channelFee=0;// 通道费
    var fTradeIdFromCstp = fTradeId;
    var entrustData;
    var tableAllRows;
    var rowDatas = [];
    var tradeAmtAll=0;
    var isCSTP = false;
    var zcdm,jyfx,jycs,jypt,tdao;
    var isChange = false;
    var isOpen = false;
    var isautobond = false;
    // add by wangguoxian 费用设置 20190807 start
    var isPayWithTrade = $("#isPayWithTrade").val()?$("#isPayWithTrade").val():YES_OR_NO_Y;//佣金是否随交易支付，默认为是
    $("#isPayWithTrade").val(isPayWithTrade);
    // add by wangguoxian 费用设置 20190807 end
    var stampDutyIsPayWithTrade = $("#stampDutyIsPayWithTrade").val()?$("#stampDutyIsPayWithTrade").val():YES_OR_NO_Y;//印花是否随交易支付，默认为是
    $("#stampDutyIsPayWithTrade").val(stampDutyIsPayWithTrade);
    var settleIsPayWithTrade = $("#settleIsPayWithTrade").val()?$("#settleIsPayWithTrade").val():YES_OR_NO_Y;//结算费是否随交易支付，默认为是
    var exchangeIsPayWithTrade = $("#exchangeIsPayWithTrade").val()?$("#exchangeIsPayWithTrade").val():YES_OR_NO_Y;//经手费是否随交易支付，默认为是
    $("#settleIsPayWithTrade").val(settleIsPayWithTrade);
    $("#exchangeIsPayWithTrade").val(exchangeIsPayWithTrade);
    var getriskdata = function () {
        var mun=null;
        var object = $.serializeToObject('#submitForm', true);
        var bussTrdTransfenpeiDto=null;
        if(object!=null&&object!=""&&object.bussTrdTransfenpeiDto!=null&&object.bussTrdTransfenpeiDto!=""){
            bussTrdTransfenpeiDto=object.bussTrdTransfenpeiDto;
        }
        var list=[];
        for(var i in bussTrdTransfenpeiDto){
            if(bussTrdTransfenpeiDto[i].fpriceAmt!=0&&bussTrdTransfenpeiDto[i].fpriceAmt!=null&&bussTrdTransfenpeiDto[i].fpriceAmt!=" "){
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
                        mun: mun}
                list[i]=obj;
            }
        }
        if($('#windtypeone').val() && $('#windtypeone').val() != ''){
            for(var i in list){
                list[i].assetSecType = $('#windtypeone').val();
            }
        }
        var object = {valDate:['settleDate','TrdPlanRateInvestTrade002.settleDate'],portfolioCode:['portfolioId','TrdBondInvestTrade002.portfolioId'],tradeAmount:['fpriceAmt','TrdBondInvestTrade002.fpriceAmt'],assetType:['windtypeone','TrdBondInvestTrade002.windtypeone'],tradeDirection:['ps','TrdLendInvestTrade002.ps'],data:list}
        return object;
    }

	function saveOrSubmit(operateType) {
		if (GEN_PAY_ORDERS_AUTO == '01') {
			window.BussTrdTransferOrder['bussTrdTransferOrder'].generateOrders(function () {
				realSaveOrSubmit(operateType);
			});
		}
		else {
			realSaveOrSubmit(operateType);
		}
	}

    function realSaveOrSubmit(operateType) {
		var s;
		var maxTradeamt = 0;
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

		/*var isTrue = chectRowsIsChange();
		if(isTrue){
			//---update by jjpei 20190731---信息显示有误
			Joyin.error("组合分配中的计算要素已变更，请重现点击计算按钮!");
			//---update by jjpei 20190731---信息显示有误
			return;
		}*/

		// 检查生成划款指令参数是否发生变化
		if (window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()) {
			return;
		}

		if($('#chlId').val()){
			for(var i=0;i<table.getAllRows().length;i++){
				if(table.getAllRows()[i].secManageAcctIdDuo){
					Joyin.warn("通道和多组合证券账户不能同时选择！");
					return;
				}
			}
		}
		if(!$('#chlId').val()){
			for(var i=0;i<table.getAllRows().length;i++){
				if(!table.getAllRows()[i].secManageAcctIdDuo){
					Joyin.warn("通道和多组合证券账户不能同时为空值！");
					return;
				}
			}
		}

		//检查组合分配的和金额信息是否一致
		if($('#tradeAmt').val()){
			var number = 0;
			for(var i=0;i<table.getAllRows().length;i++){
				var tradeAmt=table.getAllRows()[i].tradeAmt==" "?0:table.getAllRows()[i].tradeAmt;
				if (parseFloat(tradeAmt) > parseFloat(maxTradeamt)){
					maxTradeamt = table.getAllRows()[i].tradeAmt==" "?0:table.getAllRows()[i].tradeAmt;
				}
				if(tradeAmt){
					number =  JoyinMath.doAdd(number,tradeAmt,2);
				}
			}
			if(JoyinMath.doAdd($('#tradeAmt').val(),0,2)!=number){
				Joyin.warn("组合分配劵面总额和金额信息券面总额不一致！");
				return;
			}
		}
		if($('#fpriceAmt').val()){
			var number = 0;
			for(var i=0;i<table.getAllRows().length;i++){
				var fpriceAmt=table.getAllRows()[i].fpriceAmt==" "?0:table.getAllRows()[i].fpriceAmt;
				if(fpriceAmt){
					number =  JoyinMath.doAdd(number,fpriceAmt,2);
				}
			}
			if(JoyinMath.doAdd($('#fpriceAmt').val(),0,2)!=number){
				Joyin.warn("组合分配全价总额和金额信息全价总额不一致！");
				return;
			}
		}
		if($('#tradeAmtAndFee').val()){
			var number = 0;
			for(var i=0;i<table.getAllRows().length;i++){
				var tradeAmtAndFee=table.getAllRows()[i].tradeAmtAndFee==" "?0:table.getAllRows()[i].tradeAmtAndFee;
				if(tradeAmtAndFee){
					number =  JoyinMath.doAdd(number,tradeAmtAndFee,2);
				}
			}
			if(JoyinMath.doAdd($('#tradeAmtAndFee').val(),0,2)!=number){
				Joyin.warn("组合分配交易总额和金额信息交易总额不一致！");
				return;
			}
		}
		if(true){
			var number = 0;
			for(var i=0;i<table.getAllRows().length;i++){
				var procedureAmt=table.getAllRows()[i].procedureAmt==" "?0:table.getAllRows()[i].procedureAmt;
				if(procedureAmt){
					number =  JoyinMath.doAdd(number,procedureAmt,2);
				}
			}
			if(JoyinMath.doAdd($('#procedureAmt').val()?$('#procedureAmt').val():0,0,2)!=number){
				Joyin.warn("组合分配交易手续费和金额信息交易手续费不一致！");
				return;
			}
		}
		if(true){
            var number = 0;
            for(var i=0;i<table.getAllRows().length;i++){
                var settleServiceAmt=table.getAllRows()[i].settleServiceAmt==" "?0:table.getAllRows()[i].settleServiceAmt;
                if(settleServiceAmt){
                    number =  JoyinMath.doAdd(number,settleServiceAmt,2);
                }
            }
            if(JoyinMath.doAdd($('#settleServiceAmt').val()?$('#settleServiceAmt').val():0,0,2)!=number){
                Joyin.warn("组合分配结算服务费和金额信息结算服务费不一致！");
                return;
            }
        }
		if(true){
			var number = 0;
			for(var i=0;i<table.getAllRows().length;i++){
				var settleAmt=table.getAllRows()[i].settleAmt==" "?0:table.getAllRows()[i].settleAmt;
				if(settleAmt){
					number =  JoyinMath.doAdd(number,settleAmt,2);
				}
			}
			if(JoyinMath.doAdd($('#settleAmt').val()?$('#settleAmt').val():0,0,2)!=number){
				Joyin.warn("组合分配结算费和金额信息结算费不一致！");
				return;
			}
		}
		if(true){
            var number = 0;
            for(var i=0;i<table.getAllRows().length;i++){
                var exchangeAmt=table.getAllRows()[i].exchangeAmt==" "?0:table.getAllRows()[i].exchangeAmt;
                if(exchangeAmt){
                    number =  JoyinMath.doAdd(number,exchangeAmt,2);
                }
            }
            if(JoyinMath.doAdd($('#exchangeAmt').val()?$('#exchangeAmt').val():0,0,2)!=number){
                Joyin.warn("组合分配经手费和金额信息结算费不一致！");
                return;
            }
        }
		if($('#commissionAmt').val()){
			var number = 0;
			for(var i=0;i<table.getAllRows().length;i++){
				var commissionAmt=table.getAllRows()[i].commissionAmt==" "?0:table.getAllRows()[i].commissionAmt;
				if(commissionAmt){
					number =  JoyinMath.doAdd(number,commissionAmt,2);
				}
			}
			if(JoyinMath.doAdd($('#commissionAmt').val(),0,2)!=number){
				Joyin.warn("组合分配佣金和金额信息佣金不一致！");
				return;
			}
		}
		if($('#stampDutyAmt').val()){
			var number = 0;
			for(var i=0;i<table.getAllRows().length;i++){
				var stampDutyAmt=table.getAllRows()[i].stampDutyAmt==" "?0:table.getAllRows()[i].stampDutyAmt;
				if(stampDutyAmt){
					number =  JoyinMath.doAdd(number,stampDutyAmt,2);
				}
			}
			if(JoyinMath.doAdd($('#stampDutyAmt').val(),0,2)!=number){
				Joyin.warn("组合分配印花税和金额信息印花税不一致！");
				return;
			}
		}

		if($('#fTradeId').val()&&$('#tradeAmt').val()&&(pageModel==PAGE_MODEL_NEW||pageModel==PAGE_MODEL_EDIT)){//新建
			var isTrue = false;
			var params = {};
			params.fTradeId = $('#fTradeId').val();
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
		if ($('#select2-finprodId-container').attr('title')){
			object.finprodName = $('#select2-finprodId-container').attr('title');
		}
		object.pageModel = pageModel;
		object.operateType = operateType;
		if(FileCommon) {
			object.uploadFileList = FileCommon.getUploadFileInfo();
		}
		object.settleAmt=$("#settleAmt").val();
        object.exchangeAmt=$("#exchangeAmt").val();
        object.commissionAmt=$("#commissionAmt").val();
		object.entrustNum = entrustNum; // 委托时的券面总额
		object.entrustPrice = entrustPrice; // 委托时的净价
		object.entrustYield = entrustYield; // 委托时的到期收益率
		object.entrustExerYield = entrustExerYield; // 委托时的行权收益率
		object.assetPosition = $("#bussTrdAvailableCashQuery_assetPosition").val(); // 资产可用持仓
		object.bondFirstSign = $("#bondFirstSign").val(); //中标结果页面进入的标识 add by gejiaao 2019/8/9
		object.deviation=  parseFloat($("#deviationDegreeLabel").text())/100;//偏离度获取界面值并将其转换为数值型by赵东东 2019.9.24
		var data = $("#chlId").find("option:selected").data("data");
		if (data && data.object) {
			object.payType = data.object.payType;
			object.collectType = data.object.collectType;
		}
		if ($('#trdInvestPersonInfo_counterId').select2('data') && $('#trdInvestPersonInfo_counterId').select2('data')[0]) {
			object.counterType = $('#trdInvestPersonInfo_counterId').select2('data')[0].object;
			object.counterId = $('#trdInvestPersonInfo_counterId').select2('data')[0].id;
		}
		//update by lwj 20200325 券面总额大于资产可用持仓提示 start
		object.maxTradeamt = maxTradeamt;
		Joyin.ajaxJson(basePath + 'invest/trade/bond/TrdBondInvestTrade003/check', object, function(result) {
			Joyin.ajaxJson(basePath + 'invest/trade/TrdCommonController/check', object, function(result1) {
                if(isautobond == true) {
                    Joyin.alert("资产第一次接入系统，请确认并完善【债券标的】页面资产要素完整！", 1, function () {
                        Joyin.confirm(result.data, function () {
                            if (operateType === 'save') {
                                WorkFlow.doSaveList(basePath + 'invest/trade/bond/TrdBondInvestTrade003/save', object, "TrdBondInvestTrade002_btn_save");
                            } else {
                                WorkFlow.doSubmitList(basePath + 'invest/trade/bond/TrdBondInvestTrade003/save', object, "TrdBondInvestTrade002_btn_submit", pageId, null, function (result) {
                                    window.BaseRiskMathComm.reloadRiskTable(result);
                                });
                            }
                        });
                    });
                }else{
                    Joyin.confirm(result.data, function () {
                        if (operateType === 'save') {
                            WorkFlow.doSaveList(basePath + 'invest/trade/bond/TrdBondInvestTrade003/save', object, "TrdBondInvestTrade002_btn_save");
                        } else {
                            WorkFlow.doSubmitList(basePath + 'invest/trade/bond/TrdBondInvestTrade003/save', object, "TrdBondInvestTrade002_btn_submit", pageId, null, function (result) {
                                window.BaseRiskMathComm.reloadRiskTable(result);
                            });
                        }
                    });
                }
		      },false);
		});
		      //update by lwj 20200325 券面总额大于资产可用持仓提示 end
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
                    if (typeof currentDealObj != "object" && key != "procedureAmt" && key != "settleServiceAmt" &&key != "settleAmt" && key != "exchangeAmt"
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
                if (key == 'selectValue'||key=='riskMathFlag' || key == 'TrdBondInvestTrade003_distribution') {
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
        	$(el).val(value);
            if(!editable){
                $(el).attr("disabled",true);
            }
            $(el).trigger('change').trigger('blur');
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
    	 var settleSpeed = $("#settleSpeed").val();
         if(settleSpeed){
         if (settleSpeed == ACCOUNT_SPEED_T0) {
             $("#settleDate").val($("#tradeDate").val()).trigger("change");
         }
             else{
                 //下拉框暂时转换实际天数只有T+0 T+1 T+2
                 settleSpeed=settleSpeed==ACCOUNT_SPEED_T1?"1":"2";
         	if ($('#finprodId').val()) {
                     Joyin.ajaxSync(basePath + 'invest/trade/bond/TrdBondInvestTrade003/getNextWorkDay', {
                 'calendarId': $("#calendarId").val(),
                         'tradeDate': $("#tradeDate").val(),
                         'settleSpeed': settleSpeed
             }, function (date) {
                         $("#settleDate").val(date);
             });
         	}
             }
         }
         else {
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
     * 交易平台联动
     */
    $('#tradeMarket,#tradeMarketMode').on("change", function (e, isInit) {
        var tradeMarket = $('#tradeMarket').val();
        var tradeMarketMode = $('#tradeMarketMode').val();
        var $tradePlat = $('#tradePlat');

        if(pageModel == PAGE_MODEL_NEW || pageModel == PAGE_MODEL_EDIT && $('#tradeMarketMode').val() == SHARES_TRADE_MARKET_02){
            if ($('#finprodId').val != null && $('#finprodId').val != ''){
                var data = $('#finprodId').select2('data')[0].object;
                if (data && data.isIssueAsset == 'Y' && $("#tradeMarketMode").val() == SHARES_TRADE_MARKET_02){
                    $('#finprodId').val('').trigger("change");
                }
            }
        }
        
        if (pageModel != PAGE_MODEL_REFERENCE&&!$("#entrustId").val()) {
            var key = tradeMarket + tradeMarketMode;
            switch (key) {
                //如果是交易场所+交易市场 == 银行间+一级市场，就可以选择01和99
                case TRADE_PLACE_01 + SHARES_TRADE_MARKET_01:
                    table.showColumns([{field:'settleAmt',visible: false}]);
                    table.showColumns([{field:'exchangeAmt',visible: false}]);
                    //隐藏并置空结算费和经手费
                    $("#settleAmtDiv").hide().trigger("change");
                    $("#exchangeAmtDiv").hide().trigger("change");
                    $("#settleAmt").val('').trigger("change");
                    $("#exchangeAmt").val('').trigger("change");
                    $("#commissionAmt").val('').trigger("change");
                    $("#tradeAmtAndFee").val('').trigger("change");
                    $("#fpriceAmt").val('').trigger("change");
                    $tradePlat.updateChosenArr({
                        item: [SHARES_TRADE_PLATFORM_01,SHARES_TRADE_PLATFORM_99],
                        chosen: SHARES_TRADE_PLATFORM_01,
                        disabled: false
                    });
                    break;
                case TRADE_PLACE_01 + SHARES_TRADE_MARKET_02:
                    //如果是交易场所+交易市场 == 银行间+二级市场，只能选择01且置灰
                    table.showColumns([{field:'settleAmt',visible: false}]);
                    table.showColumns([{field:'exchangeAmt',visible: false}]);
                    //隐藏并置空结算费和经手费
                    $("#settleAmtDiv").hide();
                    $("#exchangeAmtDiv").hide();
                    $("#settleAmt").val('').trigger("change");
                    $("#exchangeAmt").val('').trigger("change");
                    $("#commissionAmt").val('').trigger("change");
                    $("#tradeAmtAndFee").val('').trigger("change");
                    $("#fpriceAmt").val('').trigger("change");
                    $tradePlat.updateChosenArr({
                        item: [SHARES_TRADE_PLATFORM_01],
                        chosen: SHARES_TRADE_PLATFORM_01,
                        disabled: true
                    });
                    break;
                case TRADE_PLACE_02 + SHARES_TRADE_MARKET_01:
                    //如果是交易场所+交易市场 == 上交所+一级市场，置空且置灰
                    table.showColumns([{field:'settleAmt',visible: false}]);
                    table.showColumns([{field:'exchangeAmt',visible: false}]);
                    //隐藏并置空结算费和经手费
                    $("#settleAmtDiv").hide();
                    $("#exchangeAmtDiv").hide();
                    $("#settleAmt").val('').trigger("change");
                    $("#exchangeAmt").val('').trigger("change");
                    $("#commissionAmt").val('').trigger("change");
                    $("#tradeAmtAndFee").val('').trigger("change");
                    $("#fpriceAmt").val('').trigger("change");
                    $('#tradePlat').val("").trigger('change');
                    $('#tradePlat').prop("disabled",true);
                    break;
                case TRADE_PLACE_02 + SHARES_TRADE_MARKET_02:
                    table.showColumns([{field:'settleAmt',visible: true}]);
                    table.showColumns([{field:'exchangeAmt',visible: true}]);
                    //显示结算费和经手费
                    $("#settleAmtDiv").show();
                    $("#exchangeAmtDiv").show();
                    $("#settleAmt").val('').trigger("change");
                    $("#exchangeAmt").val('').trigger("change");
                    $("#commissionAmt").val('').trigger("change");
                    $("#tradeAmtAndFee").val('').trigger("change");
                    $("#fpriceAmt").val('').trigger("change");
                    //如果是交易场所+交易市场 == 上交所+二级市场，“交易平台”枚举值：竞价撮合平台、固定收益平台、大宗交易平台；
                    $tradePlat.updateChosenArr({
                        item: [SHARES_TRADE_PLATFORM_02,SHARES_TRADE_PLATFORM_03,SHARES_TRADE_PLATFORM_04],
                        // chosen: SHARES_TRADE_PLATFORM_02,
                        disabled: false
                    });
                    break;
                case TRADE_PLACE_03 + SHARES_TRADE_MARKET_01:
                    //如果是交易场所+交易市场 == 深交所+一级市场，置空且置灰
                    table.showColumns([{field:'settleAmt',visible: false}]);
                    table.showColumns([{field:'exchangeAmt',visible: false}]);
                    //隐藏并置空结算费和经手费
                    $("#settleAmtDiv").hide();
                    $("#exchangeAmtDiv").hide();
                    $("#settleAmt").val('').trigger("change");
                    $("#exchangeAmt").val('').trigger("change");
                    $("#commissionAmt").val('').trigger("change");
                    $("#tradeAmtAndFee").val('').trigger("change");
                    $("#fpriceAmt").val('').trigger("change");
                    $('#tradePlat').val("").trigger('change');
                    $('#tradePlat').prop("disabled",true);
                    break;
                case TRADE_PLACE_03 + SHARES_TRADE_MARKET_02:
                    //如果是交易场所+交易市场 == 深交所+二级市场，“交易平台”枚举值：竞价撮合平台、固定收益平台、大宗交易平台；
                    table.showColumns([{field:'settleAmt',visible: true}]);
                    table.showColumns([{field:'exchangeAmt',visible: true}]);
                    //显示结算费和经手费
                    $("#settleAmtDiv").show();
                    $("#exchangeAmtDiv").show();
                    $("#settleAmt").val('').trigger("change");
                    $("#exchangeAmt").val('').trigger("change");
                    $("#commissionAmt").val('').trigger("change");
                    $("#tradeAmtAndFee").val('').trigger("change");
                    $("#fpriceAmt").val('').trigger("change");
                    $tradePlat.updateChosenArr({
                        item: ['02','05'],
                        // chosen: SHARES_TRADE_PLATFORM_02,
                        disabled: false
                    });
                    break;
                case TRADE_PLACE_05 + SHARES_TRADE_MARKET_02:
                    //如果是交易场所+交易市场 == 机构间私募系统+二级市场，“交易平台”枚举值：竞价撮合平台、固定收益平台、大宗交易平台；
                    $tradePlat.updateChosenArr({
                        item: [SHARES_TRADE_PLATFORM_99],
                        // chosen: SHARES_TRADE_PLATFORM_02,
                        disabled: false
                    });
                    break;
                default:
                    //隐藏并置空结算费和经手费
                    $("#settleAmtDiv").hide();
                    $("#exchangeAmtDiv").hide();
                    $("#settleAmt").val('').trigger("change");
                    $("#exchangeAmt").val('').trigger("change");
                    $("#commissionAmt").val('').trigger("change");
                    $("#tradeAmtAndFee").val('').trigger("change");
                    $("#fpriceAmt").val('').trigger("change");
                    if (isInit || pageModel == PAGE_MODEL_REFERENCE || pageModel ==PAGE_MODEL_EDIT) {
                        break;
                    }
                    if($("#entrustId").val()){
                        $('#tradePlat').prop("disabled",true);
                    }else{
                        $tradePlat.updateChosenArr({
                            disabled: false
                        });
                    }
                    break;
            }
        }
    });

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
            /*var tradeMarket = $('#tradeMarket').val();
            if (tradeMarket == TRADE_PLACE_02 || tradeMarket == TRADE_PLACE_03) {
                $("#commissionAmt").prop("disabled", true).val(0).trigger('blur').trigger('change');
            }
            else {
                $("#commissionAmt").prop("disabled", false).val(commissionAmt).trigger('blur').trigger('change');
            }*/
        } else {
            // $("#commissionAmt").prop("disabled", false).val(commissionAmt).trigger('blur').trigger('change');
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
//            $("#unitInt").attr("disabled", false);
            $("#intAmt").attr("disabled", false);
        }
    });


    /**
     * 1.4清算速度、交易日、交割日之间有联动关系
     */
    $("#settleSpeed").on("change", function () {
        calcSettleDate();
    });
    /**
     * 交易日历变化，联动交割日
     */
    $("#calendarId").on("change", function () {
        calcSettleDate();
    });
//    /**
//     * 债券一级分类change事件
//     */
//    $('#windtypeone').on('change', function() {
//        if ($('#windtypeone').val()) {
//            if ($('#finprodId').val()) {
//                var data = $('#finprodId').select2('data')[0].object;
//                if(data.windtypeone && $('#windtypeone').val() == data.windtypeone) {
//                    // 已经选中的债券对应的债券一级分类和选中的债券一级分类一致，则不清空已选择资产代码
//                } else {
//                    $("#finprodId").val("").trigger('change');
//                }
//            }
//        }
//    });
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
                $("#finprodName").val($('#finprodId').select2('data')[0].id);
            // 同时带出资产相关信息，包括起息日，到期日，发行日，交易场所，币种，交易日历，计息基础等;
            $("#vdate").val(data.vdate).trigger('change');
            $("#mdate").val(data.mdate).trigger('change');
            $("#idate").val(data.idate).trigger('change');
            $("#type6").val(data.type6).trigger('change');
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
            $("#calendarId").val(data.calendarId).trigger('change');
            $("#basis").val(data.basis).trigger('change');
            // 新增资产二、三、四级分类  王贵奇  2019/7/30 开始
            $("#type9").val(data.type9).trigger('change');
            $("#type10").val(data.type10).trigger('change');
            $("#type11").val(data.type11).trigger('change');
            // 新增资产二、三、四级分类  王贵奇  2019/7/30 结束
//            if(data.windtypeone && !$("#windtypeone").val()) {
//                $("#windtypeone").val(data.windtypeone).trigger('change');
//            }
                $("#windtypeone").val(data.windtypeone).trigger('change');
                      
            /*// 是否含权债
            $("#isRight").val(data.isRight).trigger('change');*/
            // 控制显示行权收益率
            exerYieldDivContrl();
            } else {
	        	//自动加券 yjm
	        	var autoCoupon = AutoCoupon.autoBondTradeCoupon($('#finprodId').val());
                isautobond = true;
	            // 资产名称文本框根据资产代码选择自动显示对应名称;
	            $("#finprodName").val(autoCoupon.finprodAbbr).trigger('change');
	            // 同时带出资产相关信息，包括起息日，到期日，发行日，交易场所，币种，交易日历，计息基础等;
	            $("#vdate").val(autoCoupon.vdate).trigger('change');
	            $("#mdate").val(autoCoupon.mdate).trigger('change');
	            $("#idate").val(autoCoupon.idate).trigger('change');
	            if (autoCoupon.tradeMarket) {
	                $("#tradeMarket").val(autoCoupon.tradeMarket).trigger('change');
	            }
	            $("#ccy").val(autoCoupon.ccy).trigger('change');
	            $("#calendarId").val(autoCoupon.calendarId).trigger('change');
	            $("#basis").val(autoCoupon.basis).trigger('change');
	            $("#windtypeone").val(autoCoupon.windtypeone).trigger('change');
	            $("#finProd_id1").val(autoCoupon.finprodId).trigger('change');
	            $("#windtypeone").val(autoCoupon.type4).trigger('change');
	           // $('#finprodId').textSelect('setValue', autoCoupon.finprodId);
	            $("#type9").val(data.type9).trigger('change');
                $("#type10").val(data.type10).trigger('change');
                $("#type11").val(data.type11).trigger('change');
			}
        	 updateCash();
             updateSecAcctBalance();
             
             /*// 是否含权债
             $("#isRight").val(data.isRight).trigger('change');*/
             // 控制显示行权收益率
             exerYieldDivContrl();
        } else {
            // 资产名称文本框根据资产代码选择自动显示对应名称;
            $("#finprodName").val("").trigger('change');
            // 同时带出资产相关信息，包括起息日，到期日，发行日，交易场所，币种，交易日历，计息基础等;
            $("#vdate").val("").trigger('change');
            $("#mdate").val("").trigger('change');
            $("#idate").val("").trigger('change');
            $("#ccy").val("").trigger('change');
            $("#calendarId").val("").trigger('change');
            $("#basis").val("").trigger('change');
            
            // add by etliu 
            $("#windtypeone").val("").trigger('change');
        }
        // 1.6计算偏离度
        calcDeviationDegree();
    });
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
        
        // 将甲类户对应的交易手续费、结算费、佣金设置为不可编辑
//        controllFeeItem();
    });

    $("#chlId").on("change", function () {
        if ($("#chlId").val()) {
            $("#secManageAcctId").val('').prop("disabled", true);
        }
        else {
            $("#secManageAcctId").prop("disabled", false);
        }
        updateCash();
	//add by lwj 20200326 通道选择联动 start
        changeTradeAmtAndFee();
	//add by lwj 20200326 通道选择联动 end
    });

    $("#channelFee").on("change", function () {
        changeTradeAmtAndFee();
    });

    $("#fpriceAmt").on("change", function () {
        changeTradeAmtAndFee();
    });

    $("#settleAmt").on("change", function () {
            changeTradeAmtAndFee();
    });

    $("#exchangeAmt").on("change", function () {
        changeTradeAmtAndFee();
    });

    var changeTradeAmtAndFee = function() {
        if ($("#ps").val() == PS_T01) {
            $("#tradeAmtAndFee").val('0');
            if($("#fpriceAmt").val()){
                $("#tradeAmtAndFee").val(JoyinMath.doAdd($("#fpriceAmt").val(), 0, 2)).trigger('blur');
            }
            if (stampDutyIsPayWithTrade == YES_OR_NO_Y) {
                $("#tradeAmtAndFee").val(JoyinMath.doAdd($("#tradeAmtAndFee").val(), $("#stampDutyAmt").val(), 2)).trigger('blur');
            }
             if (isPayWithTrade == YES_OR_NO_Y) {
                 $("#tradeAmtAndFee").val(JoyinMath.doAdd($("#tradeAmtAndFee").val(), $("#commissionAmt").val(), 2)).trigger('blur');
             }
            //若结算费不为空
            if($("#settleAmt").val()){
                if (settleIsPayWithTrade == YES_OR_NO_Y) {
                    $("#tradeAmtAndFee").val(JoyinMath.doAdd($("#tradeAmtAndFee").val(), $("#settleAmt").val(), 2)).trigger('blur');
                }
            }
            //若经手费不为空
            if($("#exchangeAmt").val()){
                if (exchangeIsPayWithTrade == YES_OR_NO_Y) {
                    $("#tradeAmtAndFee").val(JoyinMath.doAdd($("#tradeAmtAndFee").val(), $("#exchangeAmt").val(), 2)).trigger('blur');
                }
            }
             //内转买入 减去通道费
             if ($("#trdInvestPersonInfo_counterId").val()&&$("#trdInvestPersonInfo_counterId").select2('data')[0].object=='N') {
            	 $("#tradeAmtAndFee").val(JoyinMath.doMinus($("#tradeAmtAndFee").val(), $("#channelFee").val(), 2)).trigger('blur');
             }
        }
        else {
            if($("#fpriceAmt").val()){
                $("#tradeAmtAndFee").val(JoyinMath.doAdd($("#fpriceAmt").val(), 0, 2)).trigger('blur');
            }
            if (isPayWithTrade == YES_OR_NO_Y) {
                $("#tradeAmtAndFee").val(JoyinMath.doMinus($("#tradeAmtAndFee").val(), $("#commissionAmt").val(), 2)).trigger('blur');
            }
            //若结算费不为空
            if($("#settleAmt").val()){
                if (settleIsPayWithTrade == YES_OR_NO_Y) {
                    $("#tradeAmtAndFee").val(JoyinMath.doMinus($("#tradeAmtAndFee").val(), $("#settleAmt").val(), 2)).trigger('blur');
                }
            }
            //若经手费不为空
            if($("#exchangeAmt").val()){
                if (exchangeIsPayWithTrade == YES_OR_NO_Y) {
                    $("#tradeAmtAndFee").val(JoyinMath.doMinus($("#tradeAmtAndFee").val(), $("#exchangeAmt").val(), 2)).trigger('blur');
                }
            }
            if (stampDutyIsPayWithTrade == YES_OR_NO_Y) {
                 $("#tradeAmtAndFee").val(JoyinMath.doMinus($("#tradeAmtAndFee").val(), $("#stampDutyAmt").val(), 2)).trigger('blur');
             }
             if (($("#trdInvestPersonInfo_counterId").val()&&$("#trdInvestPersonInfo_counterId").select2('data')[0].object=='N')
            		 ||($("#trdInvestPersonInfo_counterId").val()&&$("#trdInvestPersonInfo_counterId").select2('data')[0].object!='N'
            			 &&($("#ps").val() == PS_T02||$("#ps").val() == PS_T03)&&$("#chlId").val()&&$("#chlId").select2("data")[0].object.collectType==Collect_Type_01)) {
            	 $("#tradeAmtAndFee").val(JoyinMath.doMinus($("#tradeAmtAndFee").val(), $("#channelFee").val(), 2)).trigger('blur');
             }else if(!$("#trdInvestPersonInfo_counterId").val()&&($("#ps").val() == PS_T02||$("#ps").val() == PS_T03)&&$("#chlId").val()&&$("#chlId").select2("data")[0].object.collectType==Collect_Type_01){
				$("#tradeAmtAndFee").val(JoyinMath.doMinus($("#tradeAmtAndFee").val(), $("#channelFee").val(), 2)).trigger('blur');
             }
        }
    }
    // 一体化对应etliu end


    /**
     * 投资目的变化更新资产可用持仓
     */
    $("#invAim").on("change", function () {
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
        //add by 裴家俊----start----------
        // modified by wangguoxian 费用设置 20190808 start
        // relatedTradeRepFee($("#commissionAmt"),$(this));
        changeTradeAmtAndFee();
        // modified by wangguoxian 费用设置 20190808 end
        //add by 裴家俊----end----------
    });
    $("#yield").on("change", function() {
        $("#yield_calc").val("");
        if($('#ps').val()=='T01'){//买入
            if(entrustYield&&Number($(this).val())<Number(entrustYield)){
                Joyin.warn("买入时，到期收益率不能小于委托的到期收益率");
            }
        }else if($('#ps').val()=='T02'){//卖出
            if(entrustYield && $(this).val() && Number($(this).val())<Number(entrustYield)){
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
            if(entrustYield && $(this).val() && Number($(this).val())<Number(entrustYield)){
                Joyin.warn("卖出时，行权收益率不能大于委托的行权收益率");
            }
        }
    });

    //----add by 裴家俊  ---start-
    $("#commissionAmt").on("change", function() {
        // modified by wangguoxian 费用设置 20190808 start
    	// relatedTradeRepFee($(this),$("#fpriceAmt"));
        changeTradeAmtAndFee();
        // modified by wangguoxian 费用设置 20190808 end
    });
    // modified by wangguoxian 费用设置 20190808 start
    /*var relatedTradeRepFee=function(commissionAmt,fpriceAmt){
    	if($(commissionAmt).val()&&$(fpriceAmt).val()&&isPayWithTrade == YES_OR_NO_Y){
        	if($("#ps").val()==PS_T01){
				$("#tradeAmtAndFee").val(Number($(fpriceAmt).val())+Number($(commissionAmt).val())).trigger('blur');
			}
			if($("#ps").val()==PS_T02){
				$("#tradeAmtAndFee").val(Number($(fpriceAmt).val())-Number($(commissionAmt).val())).trigger('blur');
			}
        }
    	else{
    		if($(fpriceAmt).val()){
    			$("#tradeAmtAndFee").val(Number($(fpriceAmt).val())).trigger('blur');;
    		}
    		else{
    			$("#tradeAmtAndFee").val("").trigger('blur');
    		}
    	}
    }*/
    // modified by wangguoxian 费用设置 20190808 end
  //----add by 裴家俊  ---end------

    function getCalcParam(type,data) {
        var paramObj = $.serializeToObject('#submitForm', true);
        if(type&&type=='02'){
            paramObj.portfolioId = data.portfolioId;
            paramObj.secManageAcctId = data.secManageAcctIdDuo;
        }
        paramObj.calculateField = $("[name=calculateField]:checked").val();
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
        paramObj.settleServiceAmt = null;
        paramObj.settleAmt = null;
        paramObj.commissionAmt = null;
        paramObj.channelFee = null;
        paramObj.tradeAmtAndFee = null;
        paramObj.exchangeAmt = null;
        paramObj.layTableCheckbox = null;
        // add by wangguoxian 费用设置 20190808 end
		delete paramObj['TrdBondInvestTrade003_distribution'];
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
            if ($("#unitFprice").val()&&$('#tradeAmt').val()) {
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
        var tradeAmt = $('#tradeAmt').val();
        if(tradeAmt){
            var allRows = getAllRowsForEntrustId();
            //先按照劵面总额排序
            function sortprice(a,b){
                return b.tradeAmt-a.tradeAmt;
            }
            allRows =  allRows.sort(sortprice);
            tableIsClear($("#entrustId").val());
            table.addRow(allRows);
            setMoney(allRows);
            extractedTime();
        }
    }

    function extradeAmtAndFeeed() {
        if ($("#zhilingtype").val() == "02") {
            if ($('#tradeAmtAndFee').val()) {
                var numberAll = 0;
                for (var i = 0; i < table.getAllRows().length; i++) {
                    var numberTwo = 0;
                    if (table.getAllRows()[i].fpriceAmt) {
                        numberTwo = table.getAllRows()[i].fpriceAmt;
                            if ($('#ps').val() == 'T01') {
                                if ($('#tradeMarket').val()) {
                                    //佣金是否随交易支付
                                    if (table.getAllRows()[i].commissionAmt && isPayWithTrade==YES_OR_NO_Y) {
                                        numberTwo = JoyinMath.doAdd(numberTwo, table.getAllRows()[i].commissionAmt, 2);
                                    }
                                    if (table.getAllRows()[i].settleAmt && settleIsPayWithTrade==YES_OR_NO_Y) {
                                        numberTwo = JoyinMath.doAdd(numberTwo, table.getAllRows()[i].settleAmt, 2);
                                    }
                                    if (table.getAllRows()[i].exchangeAmt && exchangeIsPayWithTrade==YES_OR_NO_Y) {
                                        numberTwo = JoyinMath.doAdd(numberTwo, table.getAllRows()[i].exchangeAmt, 2);
                                    }
                                    //印花是否随交易支付
                                    if (table.getAllRows()[i].stampDutyAmt && stampDutyIsPayWithTrade==YES_OR_NO_Y) {
                                        numberTwo = JoyinMath.doAdd(numberTwo, table.getAllRows()[i].stampDutyAmt, 2);
                                    }
                                }
                            } else {
                                if ($('#tradeMarket').val()) {
                                    //是否随交易支付
                                    if (table.getAllRows()[i].commissionAmt && isPayWithTrade==YES_OR_NO_Y) {
                                        numberTwo = JoyinMath.doMinus(numberTwo, table.getAllRows()[i].commissionAmt, 2);
                                    }
                                    if (table.getAllRows()[i].settleAmt && settleIsPayWithTrade==YES_OR_NO_Y) {
                                        numberTwo = JoyinMath.doMinus(numberTwo, table.getAllRows()[i].settleAmt, 2);
                                    }
                                    if (table.getAllRows()[i].exchangeAmt && exchangeIsPayWithTrade==YES_OR_NO_Y) {
                                        numberTwo = JoyinMath.doMinus(numberTwo, table.getAllRows()[i].exchangeAmt, 2);
                                    }
                                    //印花是否随交易支付
                                    if (table.getAllRows()[i].stampDutyAmt && stampDutyIsPayWithTrade==YES_OR_NO_Y) {
                                        numberTwo = JoyinMath.doMinus(numberTwo, table.getAllRows()[i].stampDutyAmt, 2);
                                    }
                                    if (($("#trdInvestPersonInfo_counterId").val()&&$("#trdInvestPersonInfo_counterId").select2('data')[0].object=='N')
						            		 ||($("#trdInvestPersonInfo_counterId").val()&&$("#trdInvestPersonInfo_counterId").select2('data')[0].object!='N'
						            			 &&($("#ps").val() == PS_T02||$("#ps").val() == PS_T03)&&$("#chlId").val()&&$("#chlId").select2("data")[0].object.collectType==Collect_Type_01)) {
						            	 numberTwo = JoyinMath.doMinus(numberTwo, table.getAllRows()[i].channelFee, 2);
						             }else if(!$("#trdInvestPersonInfo_counterId").val()&&($("#ps").val() == PS_T02||$("#ps").val() == PS_T03)&&$("#chlId").val()&&$("#chlId").select2("data")[0].object.collectType==Collect_Type_01){
										 numberTwo = JoyinMath.doMinus(numberTwo, table.getAllRows()[i].channelFee, 2);
						             }
                                }
                            }
                        table.renderRow(i, {"tradeAmtAndFee": JoyinMath.doAdd(numberTwo, 0, 2)});
                    }
                    numberAll = JoyinMath.doAdd(numberAll, numberTwo, 2);
                }
                if(table.getAllRows().length>0){
                    if(!$('#fTradeId').val()){
                        $("#tradeAmtAndFee").val(JoyinMath.doAdd(numberAll, 0, 2)).trigger('change').trigger("blur");
                    }
                }
            }
        }
    }

    function extractedJiSun() {
        if(isOpen){
            var paramObj = getCalcParam();
            Joyin.ajaxJson(JoyinUtil.getRealPath('/invest/trade/bond/TrdBondInvestTrade003/calculateData'), paramObj, function (result) {
                if (result && result.data) {
                    var numberAll = 0;
                    var data = result.data;
                    if (!isCSTP) {
                        $("#tradeAmt").val(data.tradeAmt).trigger("blur");
                        $("#unitCprice").val(data.unitCprice).trigger("blur");
                        $("#unitFprice").val(data.unitFprice).trigger("blur");
                        $("#cpriceAmt").val(data.cpriceAmt).trigger("blur");
                        $("#intAmt").val(data.intAmt).trigger("blur");
                        $("#unitInt").val(data.unitInt).trigger("blur");
                    }
                    if ($("#zhilingtype").val() == "02") {
                        extracted();//平均分配方式
                        if($("#entrustId").val()){
                            $("#fpriceAmt").val(JoyinMath.doAdd(Number($("#intAmt").val()) + Number($("#cpriceAmt").val()), 0, 2)).trigger("blur").trigger('change');
                        }
                    } else if (!isCSTP) {
                        $("#fpriceAmt").val(JoyinMath.doAdd(Number($("#intAmt").val()) + Number($("#cpriceAmt").val()), 0, 2)).trigger("blur").trigger('change');
                    }
                    if (!isCSTP) {
                        $("#exerYield").val(data.exerYield).trigger("blur");
                        $("#yield").val(data.yield).trigger("blur");
                        // 记录隐藏值
                        $("#unitCprice_calc").val($("#unitCprice").val());
                        $("#unitFprice_calc").val($("#unitFprice").val());
                        $("#cpriceAmt_calc").val($("#cpriceAmt").val());
                        $("#fpriceAmt_calc").val($("#fpriceAmt").val());
                        $("#exerYield_calc").val($("#exerYield").val());
                        $("#yield_calc").val($("#yield").val());
                        $("#intAmt_calc").val($("#intAmt").val());
                        $("#unitInt_calc").val($("#unitInt").val());
                    }
                    saveCalcParam = getCalcParam();
                }

                //---add by 裴家俊  费用设置  2019/03/28----start--------
                if (data.tradeFeeList && data.tradeFeeList.length > 0) {
                    // 交易手续费条数
                    var cntP = 0;
                    // 结算服务费条数
                    var cntSS = 0;
                    // 结算费条数
                    var cntS = 0;
                    // 经手费条数
                    var cntM = 0;
                    // 佣金条数
                    var cntC = 0;
                    var cntST = 0;
                    $.each(data.tradeFeeList, function (i, v) {
                        /*if (v.feeType == FEE_TYPE_02 && v.feeAmt) {
                            cntST++;
                            stampDutyIsPayWithTrade = v.isPayWithTrade;
                            $("#stampDutyIsPayWithTrade").val(stampDutyIsPayWithTrade);
                            stampDutyAmt = v.feeAmt;
                            changeTradeAmtAndFee();
                        }
                        if (v.feeType == FEE_TYPE_05 && v.feeAmt) {
                            cntC++;
                            isPayWithTrade = v.isPayWithTrade;
                            $("#isPayWithTrade").val(isPayWithTrade);
                            commissionAmt = v.feeAmt;
                            var feeAmt = v.feeAmt;
                            var tradeMarket = $('#tradeMarket').val();
                            changeTradeAmtAndFee();
                        }
                        if (v.feeType == FEE_TYPE_06 && v.feeAmt) {
                            cntP++;
                        }
                        if (v.feeType == FEE_TYPE_JSSXF && v.feeAmt) {
                            cntS++;
                        }*/
                        
                        // update by zhanggenglin 费用的计算保持与单组合一致 20290109 start
                        if(v.feeType==FEE_TYPE_05&&v.feeAmt){
                            cntC++;
                            isPayWithTrade = v.isPayWithTrade;
                            $("#isPayWithTrade").val(isPayWithTrade);
                            commissionAmt = v.feeAmt;
                            $("#commissionAmtRate").val(v.feeRate).trigger("blur");
                            var feeAmt = v.feeAmt;
                            var tradeMarket = $('#tradeMarket').val();
                            if (feeAmt) {
                                $("#commissionAmt").val(v.feeAmt).prop('disabled', false).trigger("blur").trigger("change");
                            }
                            changeTradeAmtAndFee();
                        }
                        if(v.feeType==FEE_TYPE_02&&v.feeAmt){
                            cntST++;
                            stampDutyIsPayWithTrade = v.isPayWithTrade;
                            $("#stampDutyIsPayWithTrade").val(stampDutyIsPayWithTrade);
                            stampDutyAmt = v.feeAmt;
                            var feeAmt = v.feeAmt;
                            var tradeMarket = $('#tradeMarket').val();
                            if (feeAmt) {
                                $("#stampDutyAmt").val(v.feeAmt).prop('disabled', false).trigger("blur").trigger("change");
                            }
                            changeTradeAmtAndFee();
                        }
                        if(v.feeType==FEE_TYPE_06&&v.feeAmt){
                            cntP++;
                            if($("#procedureAmt").val() == ""){
                            	$("#procedureAmt").val(v.feeAmt).trigger("blur");
                            }
                            $("#procedureAmtRate").val(v.feeRate).trigger("blur");
                        }
                        if(v.feeType==FEE_TYPE_JSSXF&&v.feeAmt){
                            cntSS++;
                            if($("#settleServiceAmt").val() == ""){
                            	$("#settleServiceAmt").val(v.feeAmt).trigger("blur");
                            }
                        }
                        //加入结算费经手费
                        if(v.feeType==FEE_TYPE_07&&v.feeAmt){
                            cntS++;
                            settleIsPayWithTrade = v.isPayWithTrade;
                            $("#settleIsPayWithTrade").val(settleIsPayWithTrade);
                            var feeAmt = v.feeAmt;
                            if (feeAmt) {
                                $("#settleAmt").val(v.feeAmt).prop('disabled', false).trigger("blur").trigger("change");
                            }
                            changeTradeAmtAndFee();
                        }
                        // if(v.feeType==FEE_TYPE_07&&v.feeAmt){
                        //     cntS++;
                        //     $("#settleAmt").val(v.feeAmt).trigger("blur");
                        // }
                        if(v.feeType==FEE_TYPE_07&&v.feeAmt){
                            cntM++;
                            exchangeIsPayWithTrade = v.isPayWithTrade;
                            $("#exchangeIsPayWithTrade").val(exchangeIsPayWithTrade);
                            var feeAmt = v.feeAmt;
                            if (feeAmt) {
                                $("#exchangeAmt").val(v.feeAmt).prop('disabled', false).trigger("blur").trigger("change");
                            }
                            changeTradeAmtAndFee();
                        }
                        // if(v.feeType==FEE_TYPE_01&&v.feeAmt){
                        //     cntM++;
                        //     $("#exchangeAmt").val(v.feeAmt).trigger("blur");
                        // }
                        extracted();//平均分配方式
                        // update by zhanggenglin 费用的计算保持与单组合一致 20290109 end
                    });
                    // 按照最新费用规则计算相关费用无结果则清空相关费用
                    if (cntP == 0) {
                        if (!$("#procedureAmt").val()) {
                            $("#procedureAmt").val("").trigger("blur").trigger("change");
                        }
                    }
                    if (cntSS == 0) {
                        if (!$("#settleServiceAmt").val()) {
                            $("#settleServiceAmt").val("").trigger("blur").trigger("change");
                        }
                    }
                    if (cntS == 0) {
                        if (!$("#settleAmt").val()) {
                            $("#settleAmt").val("").trigger("blur").trigger("change");
                        }
                    }
                    if (cntC == 0) {
                        if (!$("#commissionAmt").val()) {
                            $("#commissionAmt").val("").trigger("blur").trigger("change");
                        }
                    }
                    if (cntST == 0) {
                        if (!$("#stampDutyAmt").val()) {
                            $("#stampDutyAmt").val("").trigger("blur").trigger("change");
                        }
                    }
                    if(cntM == 0){
                        //经手费置空
                        if(!$("#exchangeAmt").val()){
                            $("#exchangeAmt").val("").trigger("blur");
                        }
                    }

                } else {
                    if (!$("#commissionAmt").val()) {
                        $("#commissionAmt").val("").trigger("change").trigger("blur");
                    }
                    if (!$("#procedureAmt").val()) {
                        $("#procedureAmt").val("").trigger("change").trigger("blur");
                    }
                    if (!$("#settleServiceAmt").val()) {
                        $("#settleServiceAmt").val("").trigger("change").trigger("blur");
                    }
                    if (!$("#settleAmt").val()) {
                        $("#settleAmt").val("").trigger("change").trigger("blur");
                    }
                    if (!$("#exchangeAmt").val()) {
                        $("#exchangeAmt").val("").trigger("change").trigger("blur");
                    }
                    if (!$("#stampDutyAmt").val()) {
                        $("#stampDutyAmt").val("").trigger("change").trigger("blur");
                    }
                    $("#procedureAmtRate").val("").trigger("blur");
                    $("#commissionAmtRate").val("").trigger("blur");
                    $("#transferAmt").val("").trigger("blur");
                }
                if ($("#zhilingtype").val() == "02") {
					extracted();//平均分配方式
				}
                //计算交易金额
                extradeAmtAndFeeed();
                tableAllRowsJiSun = table.getAllRows();
            });
        }
    }

    function extractedJiSunFeiYong(data) {
        var returnVal={
            isPayWithTrade:'Y',
            procedureAmt:0,
            settleServiceAmt:0,
            settleAmt:0,
            exchangeAmt:0,
            commissionAmt:0,
            channelFee:0,
            stampDutyAmt:0,
            stampDutyIsPayWithTrade:'Y',
            settleIsPayWithTrade:'Y',
            exchangeIsPayWithTrade:'Y'
        };
        var paramObj = getCalcParam('02',data);
        if(isOpen){
            Joyin.ajaxJson(JoyinUtil.getRealPath('/invest/trade/bond/TrdBondInvestTrade003/calculateData'), paramObj, function (result) {
                //---add by 裴家俊  费用设置  2019/03/28----start--------
                if (result.data.tradeFeeList && result.data.tradeFeeList.length > 0) {
                    // 交易手续费条数
                    var cntP = 0;
                    // 结算服务费条数
                    var cntSS = 0;
                    // 结算费条数
                    var cntS = 0;
                    // 经手费条数
                    var cntM = 0;
                    // 佣金条数
                    var cntC = 0;
                    var cntST = 0;
                    $.each(result.data.tradeFeeList, function (i, v) {//印花税
                        if(v.feeType==FEE_TYPE_02&&v.feeAmt){
                            cntST++;
                            returnVal.stampDutyIsPayWithTrade = v.isPayWithTrade;
                            returnVal.stampDutyAmt = v.feeAmt;
                            table.renderRow(data.LAY_TABLE_INDEX, {"stampDutyAmt": isNaN(v.feeAmt)?0:v.feeAmt});//设置印花税
                        }
                        if (v.feeType == FEE_TYPE_05 && v.feeAmt) {//佣金
                            cntC++;
                            // isPayWithTrade = v.isPayWithTrade;
                            returnVal.isPayWithTrade = v.isPayWithTrade;
                            returnVal.commissionAmt = v.feeAmt;
                            table.renderRow(data.LAY_TABLE_INDEX, {"commissionAmt": isNaN(v.feeAmt)?0:v.feeAmt});//设置佣金
                        }
                        if (v.feeType == FEE_TYPE_06 && v.feeAmt) {//交易手续费
                            cntP++;
                            returnVal.procedureAmt = v.feeAmt;
                            table.renderRow(data.LAY_TABLE_INDEX, {"procedureAmt": isNaN(v.feeAmt)?0:v.feeAmt});
                        }
                        if (v.feeType == FEE_TYPE_JSSXF && v.feeAmt) {//结算服务费
                            cntSS++;
                            returnVal.settleServiceAmt = v.feeAmt;
                            table.renderRow(data.LAY_TABLE_INDEX, {"settleServiceAmt": isNaN(v.feeAmt)?0:v.feeAmt});
                        }
                        //加入结算费经手费
                        // if(v.feeType==FEE_TYPE_07&&v.feeAmt){
                        //     cntS++;
                        //     returnVal.settleAmt = v.feeAmt;
                        //     table.renderRow(data.LAY_TABLE_INDEX, {"settleAmt": isNaN(v.feeAmt)?0:v.feeAmt});
                        // }
                        if (v.feeType == FEE_TYPE_07 && v.feeAmt) {
                            cntS++;
                            returnVal.settleIsPayWithTrade = v.isPayWithTrade;
                            returnVal.settleAmt = v.feeAmt;
                            table.renderRow(data.LAY_TABLE_INDEX, {"settleAmt": isNaN(v.feeAmt)?0:v.feeAmt});
                        }
                        if (v.feeType == FEE_TYPE_01 && v.feeAmt) {
                            cntM++;
                            returnVal.exchangeIsPayWithTrade = v.isPayWithTrade;
                            returnVal.exchangeAmt = v.feeAmt;
                            table.renderRow(data.LAY_TABLE_INDEX, {"exchangeAmt": isNaN(v.feeAmt)?0:v.feeAmt});
                        }
                        // if(v.feeType==FEE_TYPE_01&&v.feeAmt){
                        //     cntM++;
                        //     returnVal.exchangeAmt = v.feeAmt;
                        //     table.renderRow(data.LAY_TABLE_INDEX, {"exchangeAmt": isNaN(v.feeAmt)?0:v.feeAmt});
                        // }
                    });
                    // 按照最新费用规则计算相关费用无结果则清空相关费用
                    if (cntP == 0) {
                        table.renderRow(data.LAY_TABLE_INDEX, {"procedureAmt": ''});
                    }else{
                        var tranFeeAmtTotal = 0;
                        for (var i = 0; i < table.getAllRows().length; i++) {
                            if( table.getAllRows()[i].procedureAmt){
                                tranFeeAmtTotal =  JoyinMath.doAdd(tranFeeAmtTotal,table.getAllRows()[i].procedureAmt,2);
                            }
                        }
                        $('#procedureAmt').val(tranFeeAmtTotal);
                    }

                    if (cntSS == 0) {
                        table.renderRow(data.LAY_TABLE_INDEX, {"settleServiceAmt": ''});
                    }else{
                        var settleServiceAmtTotal = 0;
                        for (var i = 0; i < table.getAllRows().length; i++) {
                            if( table.getAllRows()[i].settleServiceAmt){
                                settleServiceAmtTotal =  JoyinMath.doAdd(tranFeeAmtTotal,table.getAllRows()[i].settleServiceAmt,2);
                            }
                        }
                        $('#settleServiceAmt').val(settleServiceAmtTotal);
                    }
                    if(cntM == 0){
                        table.renderRow(data.LAY_TABLE_INDEX, {"exchangeAmt":''});
                    }else{
                        var exchangeFeeTotal = 0;
                        for (var i = 0; i < table.getAllRows().length; i++) {
                            if( table.getAllRows()[i].exchangeAmt){
                                exchangeFeeTotal =  JoyinMath.doAdd(exchangeFeeTotal,table.getAllRows()[i].exchangeAmt,2);
                            }
                        }
                        $('#exchangeAmt').val(exchangeFeeTotal);
                    }
                    if (cntS == 0) {
                        table.renderRow(data.LAY_TABLE_INDEX, {"settleAmt":''});
                    }else{
                        var settlementFeeTotal = 0;
                        for (var i = 0; i < table.getAllRows().length; i++) {
                            if( table.getAllRows()[i].settleAmt){
                                settlementFeeTotal =  JoyinMath.doAdd(settlementFeeTotal,table.getAllRows()[i].settleAmt,2);
                            }
                        }
                        $('#settleAmt').val(settlementFeeTotal);
                    }
                    if (cntC == 0) {
                        table.renderRow(data.LAY_TABLE_INDEX, {"commissionAmt": ''})
                    }else{
                        var commissionTotal = 0;
                        for (var i = 0; i < table.getAllRows().length; i++) {
                            if( table.getAllRows()[i].commissionAmt){
                                commissionTotal =  JoyinMath.doAdd(commissionTotal,table.getAllRows()[i].commissionAmt,2);
                            }
                        }
                        $('#commissionAmt').val(commissionTotal);
                    }
                    if (cntST == 0) {
                        table.renderRow(data.LAY_TABLE_INDEX, {"stampDutyAmt": ''});
                    }else{
                        var tranFeeAmtTotal = 0;
                        for (var i = 0; i < table.getAllRows().length; i++) {
                            if( table.getAllRows()[i].stampDutyAmt){
                                tranFeeAmtTotal =  JoyinMath.doAdd(tranFeeAmtTotal,table.getAllRows()[i].stampDutyAmt,2);
                            }
                        }
                        $('#stampDutyAmt').val(tranFeeAmtTotal);
                    }
                } else {
                    table.renderRow(data.LAY_TABLE_INDEX, {"procedureAmt": ''});
                    table.renderRow(data.LAY_TABLE_INDEX, {"settleServiceAmt": ''});
                    table.renderRow(data.LAY_TABLE_INDEX, {"settleAmt":''});
                    table.renderRow(data.LAY_TABLE_INDEX, {"exchangeAmt":''});
                    table.renderRow(data.LAY_TABLE_INDEX, {"commissionAmt": ''});
                    table.renderRow(data.LAY_TABLE_INDEX, {"stampDutyAmt": ''});
                }
            },false);
        }
        returnValAll = returnVal;
        return returnVal;
    }


    function isFTradeId(){
        // if(!$('#fTradeId').val()){
        //     extractedJiSun();
        // }else{
        //     extracted();//平均分配方式
        //     extradeAmtAndFeeed();
        // }
            isOpen=true;
            extractedJiSun();
            extracted();//平均分配方式
            extradeAmtAndFeeed();
    };
    /**
     * 1.7计算按钮点击事件
     */
    $('#TrdBondInvestTrade003_btn_calculate').click(function() {
        isOpen=true;
        extractedJiSun();
//        extractedZiDongFenPei();//调用自动分配的方法
        // isFTradeId();
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

    function getEntrustByEntrustId() {
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
        if(entrustId){
            $('#TrdBondInvestTrade003_distribution').attr('disabled',false);
            $('#TrdBondInvestTrade003_btn_autoAllocation').removeClass('layui-btn-disabled');
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
                            checkAndAssign("#settleSpeed", data[0].settleSpeedStr); // 清算速度
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

                        if(data[0].etrEntrustAdd.matureYield){
                            checkAndAssign("#yield",  data[0].etrEntrustAdd.matureYield); // 到期收益
                            entrustYield = data[0].etrEntrustAdd.matureYield;
                        }
                        if(data[0].etrEntrustAdd.exerYield){
                            checkAndAssign("#exerYield",  data[0].etrEntrustAdd.exerYield); // 行权收益
                            entrustExerYield = data[0].etrEntrustAdd.exerYield;
                        }
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
            $('#tradeAmt').val(tradeAmtAll).trigger("blur");
            setTimeout(function () {
                table.addRow(rowDatas);
                setMoney(rowDatas);
                if(fTradeId){//cstp匹配交易过来的时候
                    if(prinamt<tradeAmtAllEntrust){
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
            extractedJiSunFeiYong(element);
        })
    }
    //画面初始化
    var init = function() {
        initPayType();
        zhilingtypeChangeAction();
        getEntrustByEntrustId();
        // <!-- 通道交易 新增 强成西 2019/03/26 start -->
        changeTradeAmtAndFee();
        exerYieldDivContrl();
        // <!-- 通道交易 新增 强成西 2019/03/26 start -->
        //$('#bussTrdTransfenpei_div').hide();
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
            window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                var rows = table.getAllRows();
                var isTrue = false;
                $.each(rows,function (index,data) {
                   if(!data.secManageAcctIdDuo){
                       isTrue = true;
                   }
                });
            	//全价总额不能为空
            	if (!$("#fpriceAmt").val() || typeof $("#fpriceAmt").val() == 'undefined'){
            		Joyin.error(JoyinUtil.getMessage('FW_E_0006',[JoyinUtil.getLabel("TrdBondInvestTrade002.fpriceAmt")]));
            		return null;
            	}
            	if (isTrue && (!$("#chlId").val() || typeof $("#chlId").val() == 'undefined')){
               		Joyin.error(JoyinUtil.getMessage('FW_E_0006',['组合分配证券账户或者通道']));
               		return null;
               	}
                var list;
                if($("#zhilingtype").val()=="02")
            	{
                	list = new Array();
	            	var tmpList = table.getAllRows();
	            	if(!tmpList || tmpList.length == 0){
	            		Joyin.error("组合分配不能为空");
	            	}
	            	for(var dealObj in tmpList)
	        		{	
	            	    if(tmpList[dealObj].fpriceAmt!=0&&tmpList[dealObj].fpriceAmt!=null&&tmpList[dealObj].fpriceAmt!=" "){
	            	        var dealinfo = {};
	                        dealinfo.finprodType2 = $("#finprodType2").val();
	                        dealinfo.settleDate = $("#settleDate").val();
	                        dealinfo.tradeDate = $("#tradeDate").val();
	                        dealinfo.ps = $("#ps").val();
	                        dealinfo.ccy = $("#ccy").val();
	                        if (null!=$('#tradeMarket').val()&&($('#tradeMarket').val()==TRADE_PLACE_02||$('#tradeMarket').val()==TRADE_PLACE_03)) {
                                if($("#tradeAmtAndFee").val()){
                                    dealinfo.tradeAmt = tmpList[dealObj].tradeAmtAndFee;
                                }
                                else{
                                    dealinfo.tradeAmt = tmpList[dealObj].fpriceAmt;
                                }
                            }
                            else{
                                dealinfo.tradeAmt = tmpList[dealObj].fpriceAmt;
                            }
//	                        dealinfo.tradeAmt = tmpList[dealObj].fpriceAmt; // TODO 多组合有通道费
	                        dealinfo.portfolioId = tmpList[dealObj].portfolioId;
	                        dealinfo.counterId = $("#trdInvestPersonInfo_counterId").val();
	                        dealinfo.tradeMarket = $("#tradeMarket").val();
	                        dealinfo.tradePlat = $("#tradePlat").val();
	                        dealinfo.secManageAcctId = tmpList[dealObj].secManageAcctIdDuo;
	                        // 通道交易 新增 强成西 2019/03/26 start
	                        dealinfo.chlId = $("#chlId").val();
	                        // 通道交易 新增 强成西 2019/03/26 end
	                        dealinfo.tradeMarketMode = $("#tradeMarketMode").val();
	                        list.push(dealinfo);
	            	    }else{
	            	    	Joyin.error("组合分配全价金额不能为空");
	            	    }
	        		}
            	} else {
            		list = new Array();
            		var dealObj = {};
            		dealObj.finprodType2 = $("#finprodType2").val();
                    dealObj.settleDate = $("#settleDate").val();
                    dealObj.tradeDate = $("#tradeDate").val();
                    dealObj.ps = $("#ps").val();
                    dealObj.ccy = $("#ccy").val();
                    //-----modify by 裴家俊  2018/04/05----start-----
                    if (null!=$('#tradeMarket').val()&&($('#tradeMarket').val()==TRADE_PLACE_02||$('#tradeMarket').val()==TRADE_PLACE_03)) {
                        if($("#tradeAmtAndFee").val()){
                            dealObj.tradeAmt = $("#tradeAmtAndFee").val();
                        }
                        else{
                            dealObj.tradeAmt = $("#fpriceAmt").val();
                        }
                    }
                    else{
                        dealObj.tradeAmt = $("#fpriceAmt").val();
                    }
                    //-----modify by 裴家俊  2018/04/05----end-----
                    // 通道交易 新增 强成西 2019/03/26 start
                    dealObj.tradeAmt = $("#tradeAmtAndFee").val();
                    // 通道交易 新增 强成西 2019/03/26 end
                    dealObj.portfolioId = $("#portfolioId").val();
                    dealObj.counterId = $("#trdInvestPersonInfo_counterId").val();
                    dealObj.tradeMarket = $("#tradeMarket").val();
                    dealObj.tradePlat = $("#tradePlat").val();
                    dealObj.secManageAcctId = $("#secManageAcctId").val();
                    // 通道交易 新增 强成西 2019/03/26 start
                    dealObj.chlId = $("#chlId").val();
                    // 通道交易 新增 强成西 2019/03/26 end
                    dealObj.tradeMarketMode = $("#tradeMarketMode").val();
                    list.push(dealObj);
            	}
                
                return {
                    'isTotalTrade':YES_OR_NO_N
                    ,'bankLendingType':null
                    ,'portfolioId':'dzh'
                    ,'jiaoyiType':'dzh'
                    ,'deal':list
                };
            }, true);
            if (formTodoList) {
                window.BussTrdTransferOrder['bussTrdTransferOrder'].saveCalcCondition();
            }
//            updateSecAcctBalance();
//            updateCash();
            window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'].showCash();
            if(entrustId != ''){
                if(etrEntrust){
                    window.TrdInvestPersonInfo002['trdInvestPersonInfo'].setEntrust(function () {
                        return etrEntrust;
                    });
                }
            }

          /*  $('#trdInvestPersonInfo_counterId').on("change",function(){
                var counterId = $('#trdInvestPersonInfo_counterId');
                if (counterId.select2('data') && counterId.select2('data')[0]){
                    var 交易对手 = counterId.select2('data')[0].object;
                    // 【债券投资委托】交易对手选择内部交易对手时，清算速度为T+0不可改
                    if (交易对手 == 'N') {
                        if (pageModel != PAGE_MODEL_REFERENCE) {
                            $('#settleSpeed').val("01").prop("disabled", true).trigger("change");
                        }
                    } else {
                        if (pageModel != PAGE_MODEL_REFERENCE) {
                            $('#settleSpeed').prop("disabled", false).trigger("change");
                        }
                    }
                }
            });*/
            // 触发画面联动处理
            // $("#tradeMarket").trigger("change");
            // $("#tradeMarketMode").trigger("change", true);
            // $("#ps").trigger("change");
            // $("#portfolioId").trigger("change", true);
            // $("#unitCprice").trigger("change");
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
//        } else {
//            $("#exerYieldDiv").hide();
//        }
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
                , 'invAim': $("#invAim").val()
                // 一体化对应etliu end
                // 通道交易 新增 强成西 2019/03/26 start
                , 'chlId': $("#chlId").val()
                // 通道交易 新增 强成西 2019/03/26 end
            }
        });
    };


    function extractedZiDongFenPei() {
        var entrustId = $("#entrustId").val();
        var bondFirstSign = $("#bondFirstSign").val();
        var distribution = $('#TrdBondInvestTrade003_distribution').val();
        if(distribution==null||distribution==''){
            Joyin.error("请选择分配方式！");
            return;
        }
        var ps = $('#ps').val();
        if(entrustId){
            Joyin.ajaxSync(basePath + 'invest/trade/bond/TrdBondInvestTrade003/getEntrustInfos2', {'entrustId':entrustId,'distribution':distribution,'ps':ps,'bondFirstSign':bondFirstSign},function (data) {
              //平均分配
                if ($('#TrdBondInvestTrade003_distribution').val() == '139') {
                    distribution3(data);
                    extractedJiSuan3();
                } else if ($('#TrdBondInvestTrade003_distribution').val() == '140') {//时间优先
                    distribution2(data,'entrustDate');
                    extractedJiSuan3();
                } else if ($('#TrdBondInvestTrade003_distribution').val() == '141') {//数量优先
                    distribution2(data,'tradeAmt');
                    extractedJiSuan3();
                } else if ($('#TrdBondInvestTrade003_distribution').val() == '142'){//价格优先
                    distribution2(data,'unitCprice');
                    extractedJiSuan3();
                }
            });
        }
    }
    
    function distribution3(data){
        var secTotalPrin = $('#tradeAmt').val()-0;
        var secTotalPrin2 = $('#tradeAmt').val()-0;
        var doEntrustMoneyTotle = 0;
        for(var i=0;i < data.length;i++){
            doEntrustMoneyTotle=doEntrustMoneyTotle+(data[i].doEntrustMoney-0);
        }
        
        for(var i=0;i < data.length;i++){
            var num=JoyinMath.doMul(data[i].doEntrustMoney,secTotalPrin2,2);
            var tradeAmt=JoyinMath.doDiv(num,doEntrustMoneyTotle,2);
            
            if(i==data.length-1){
                table.renderRow(i, {"entrustId":data[i].entrustId});
                table.renderRow(i, {"portfolioId":data[i].portfolioId});
                table.renderRow(i, {"doEntrustMoney":data[i].doEntrustMoney});
                table.renderRow(i, {"tradeAmt":secTotalPrin});
                secTotalPrin=secTotalPrin-(tradeAmt-0);
            }else{
                table.renderRow(i, {"entrustId":data[i].entrustId});
                table.renderRow(i, {"portfolioId":data[i].portfolioId});
                table.renderRow(i, {"doEntrustMoney":data[i].doEntrustMoney});
                table.renderRow(i, {"tradeAmt":tradeAmt});
                secTotalPrin=secTotalPrin-(tradeAmt-0);
            }
        }
    }
    
    function distribution2(data,distriData){
        var secTotalPrin = $('#tradeAmt').val()-0;
        for(var i=0;i < data.length;i++){
            var b=i;
            var tradeAmtTotle = data[i].doEntrustMoney-0;
            if(i!=data.length-1){
                for(var j=i+1;j < data.length+1;j++){
                    if(i!=data.length-1&&data[i][distriData]==data[j][distriData]){
                        tradeAmtTotle=tradeAmtTotle+(data[j].doEntrustMoney-0);
                        i++;
                    }else{
                        if(tradeAmtTotle!=data[i].doEntrustMoney){
                            tradeAmtTotle=tradeAmtTotle-0;
                            secTotalPrin=secTotalPrin-0;
                            if(tradeAmtTotle <= secTotalPrin){
                                for(var k=b;k<j;k++){
                                    if(k==data.length-1){
                                        table.renderRow(k, {"entrustId":data[k].entrustId});
                                        table.renderRow(k, {"portfolioId":data[k].portfolioId});
                                        table.renderRow(k, {"investaim":data[k].investaim});
                                        table.renderRow(k, {"doEntrustMoney":data[k].doEntrustMoney});
                                        table.renderRow(k, {"tradeAmt":secTotalPrin});
                                        secTotalPrin=JoyinMath.doMinus(secTotalPrin,secTotalPrin,2)-0;
                                    }else{
                                        table.renderRow(k, {"entrustId":data[k].entrustId});
                                        table.renderRow(k, {"portfolioId":data[k].portfolioId});
                                        table.renderRow(k, {"investaim":data[k].investaim});
                                        table.renderRow(k, {"doEntrustMoney":data[k].doEntrustMoney});
                                        table.renderRow(k, {"tradeAmt":data[k].doEntrustMoney});
                                        secTotalPrin=JoyinMath.doMinus(secTotalPrin,data[k].doEntrustMoney,2)-0;
                                    }
                                }
                            }else{
                                for(var k=b;k<j;k++){
                                    var div=JoyinMath.doMul(data[k].doEntrustMoney,secTotalPrin,2);
                                    var tradAmt=JoyinMath.doDiv(div,tradeAmtTotle,2);
                                    
                                    if(k==j-1){
                                        table.renderRow(k, {"entrustId":data[k].entrustId});
                                        table.renderRow(k, {"portfolioId":data[k].portfolioId});
                                        table.renderRow(k, {"investaim":data[k].investaim});
                                        table.renderRow(k, {"doEntrustMoney":data[k].doEntrustMoney});
                                        table.renderRow(k, {"tradeAmt":secTotalPrin});
                                        secTotalPrin=JoyinMath.doMinus(secTotalPrin,secTotalPrin,2)-0;
                                    }else{
                                        table.renderRow(k, {"entrustId":data[k].entrustId});
                                        table.renderRow(k, {"portfolioId":data[k].portfolioId});
                                        table.renderRow(k, {"investaim":data[k].investaim});
                                        table.renderRow(k, {"doEntrustMoney":data[k].doEntrustMoney});
                                        table.renderRow(k, {"tradeAmt":tradAmt});
                                        secTotalPrin=JoyinMath.doMinus(secTotalPrin,tradAmt,2)-0;
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
            }
            
            
            if(secTotalPrin<=0&&tradeAmtTotle==data[i].doEntrustMoney){
                table.renderRow(i, {"entrustId":data[i].entrustId});
                table.renderRow(i, {"portfolioId":data[i].portfolioId});
                table.renderRow(i, {"investaim":data[i].investaim});
                table.renderRow(i, {"doEntrustMoney":data[i].doEntrustMoney});
                table.renderRow(i, {"tradeAmt":" "});
                secTotalPrin=JoyinMath.doMinus(secTotalPrin,secTotalPrin,2);
            }else{
                if(tradeAmtTotle!=data[i].doEntrustMoney){
                    continue;
                }else{
                    if(i==data.length-1){
                        table.renderRow(i, {"entrustId":data[i].entrustId});
                        table.renderRow(i, {"portfolioId":data[i].portfolioId});
                        table.renderRow(i, {"investaim":data[i].investaim});
                        table.renderRow(i, {"doEntrustMoney":data[i].doEntrustMoney});
                        table.renderRow(i, {"tradeAmt":secTotalPrin});
                        secTotalPrin=JoyinMath.doMinus(secTotalPrin,secTotalPrin,2);
                    }else{
                        var amt = JoyinMath.doMinus(secTotalPrin,data[i].doEntrustMoney,2)-0;
                        if(amt<0){
                            table.renderRow(i, {"entrustId":data[i].entrustId});
                            table.renderRow(i, {"portfolioId":data[i].portfolioId});
                            table.renderRow(i, {"investaim":data[i].investaim});
                            table.renderRow(i, {"doEntrustMoney":data[i].doEntrustMoney});
                            table.renderRow(i, {"tradeAmt":secTotalPrin});
                            secTotalPrin = JoyinMath.doMinus(secTotalPrin,secTotalPrin,2)-0;
                        }else{
                            table.renderRow(i, {"entrustId":data[i].entrustId});
                            table.renderRow(i, {"portfolioId":data[i].portfolioId});
                            table.renderRow(i, {"investaim":data[i].investaim});
                            table.renderRow(i, {"doEntrustMoney":data[i].doEntrustMoney});
                            table.renderRow(i, {"tradeAmt":data[i].doEntrustMoney});
                            secTotalPrin = JoyinMath.doMinus(secTotalPrin,data[i].doEntrustMoney,2)-0;
                        }
                    }
                }
                
            }
        }
    }

    function extractedJiSuan3() {
        var tradeAmt = $('#tradeAmt').val()==""?0:$('#tradeAmt').val();
        var intAmt = $('#intAmt').val()==""?0:$('#intAmt').val();
        var cpriceAmt = $('#cpriceAmt').val()==""?0:$('#cpriceAmt').val();
        var tradeAmtAndFee = $('#tradeAmtAndFee').val()==""?0:$('#tradeAmtAndFee').val();
        var procedureAmt = $('#procedureAmt').val()==""?0:$('#procedureAmt').val();
        var settleServiceAmt = $('#settleServiceAmt').val()==""?0:$('#settleServiceAmt').val();
        var settleAmt = $('#settleAmt').val()==""?0:$('#settleAmt').val();
        var exchangeAmt = $('#exchangeAmt').val()==""?0:$('#exchangeAmt').val();
        var commissionAmt = $('#commissionAmt').val()==""?0:$('#commissionAmt').val();
        var stampDutyAmt = $('#stampDutyAmt').val()==""?0:$('#stampDutyAmt').val();
        var channelFee = $('#channelFee').val()==""?0:$('#channelFee').val();
        var ps=$('#ps').val();
        var chlId=$('#chlId').val();
        secTotalPrin=tradeAmt-0;
        
        var secTotalPrin2 = tradeAmt-0;
        
        var intAmtTotle = $('#intAmt').val()==""?0:$('#intAmt').val()-0;
        var cpriceAmtTotle = $('#cpriceAmt').val()==""?0:$('#cpriceAmt').val()-0;
        var procedureAmtTotle = $('#procedureAmt').val()==""?0:$('#procedureAmt').val()-0;
        var settleServiceAmtTotle = $('#settleServiceAmt').val()==""?0:$('#settleServiceAmt').val()-0;
        var settleAmtTotle = $('#settleAmt').val()==""?0:$('#settleAmt').val()-0;
        var exchangeAmtTotle = $('#exchangeAmt').val()==""?0:$('#exchangeAmt').val()-0;
        var commissionAmtTotle = $('#commissionAmt').val()==""?0:$('#commissionAmt').val()-0;
        var stampDutyAmtTotle = $('#stampDutyAmt').val()==""?0:$('#stampDutyAmt').val()-0;
        var channelFeeTotle = $('#channelFee').val()==""?0:$('#channelFee').val()-0;
        var tradeAmtAndFee2=0;


        for (var i = 0; i < table.getAllRows().length; i++) {
            var cpriceAmt4 = JoyinMath.doMul(table.getAllRows()[i].tradeAmt==" "?0:table.getAllRows()[i].tradeAmt,cpriceAmt,2);
            var intAmt4 = JoyinMath.doMul(table.getAllRows()[i].tradeAmt==" "?0:table.getAllRows()[i].tradeAmt,intAmt,2);
            var procedureAmt4 = JoyinMath.doMul(table.getAllRows()[i].tradeAmt==" "?0:table.getAllRows()[i].tradeAmt,procedureAmt,2);
            var settleServiceAmt4 = JoyinMath.doMul(table.getAllRows()[i].tradeAmt==" "?0:table.getAllRows()[i].tradeAmt,settleServiceAmt,2);
            var settleAmt4 = JoyinMath.doMul(table.getAllRows()[i].tradeAmt==" "?0:table.getAllRows()[i].tradeAmt,settleAmt,2);
            var exchangeAmt4 = JoyinMath.doMul(table.getAllRows()[i].tradeAmt==" "?0:table.getAllRows()[i].tradeAmt,exchangeAmt,2);
            var commissionAmt4 = JoyinMath.doMul(table.getAllRows()[i].tradeAmt==" "?0:table.getAllRows()[i].tradeAmt,commissionAmt,2);
            var stampDutyAmt4 = JoyinMath.doMul(table.getAllRows()[i].tradeAmt==" "?0:table.getAllRows()[i].tradeAmt,stampDutyAmt,2);
            var channelFee4 = JoyinMath.doMul(table.getAllRows()[i].channelFee==" "?0:table.getAllRows()[i].channelFee,channelFee,2);
            //先设置劵面总额

            if($('#tradeAmt').val()
               &&$('#unitCprice').val()
               &&table.getAllRows()[i].portfolioId){
                if(table.getAllRows().length>0){
                    if (i != table.getAllRows().length - 1) {//非最后一列
                        var cpriceAmt2 = JoyinMath.doDiv(cpriceAmt4,secTotalPrin,2)-0;
                        var intAmt2 = JoyinMath.doDiv(intAmt4,secTotalPrin,2)-0;
                        var fpriceAmt2 = JoyinMath.doAdd(cpriceAmt2,intAmt2,2)-0;
                        var procedureAmt2 = JoyinMath.doDiv(procedureAmt4,secTotalPrin,2)-0;
                        var settleServiceAmt2 = JoyinMath.doDiv(settleServiceAmt4,secTotalPrin,2)-0;
                        var settleAmt2 = JoyinMath.doDiv(settleAmt4,secTotalPrin,2)-0;
                        var exchangeAmt2 = JoyinMath.doDiv(exchangeAmt4,secTotalPrin,2)-0;
                        var commissionAmt2 = JoyinMath.doDiv(commissionAmt4,secTotalPrin,2)-0;
                        var stampDutyAmt2 = JoyinMath.doDiv(stampDutyAmt4,secTotalPrin,2)-0;
                        var channelFee2 = JoyinMath.doDiv(channelFee4,secTotalPrin,2)-0;
                        
                        
                        table.renderRow(i, {"cpriceAmt": cpriceAmt2});
                        table.renderRow(i, {"intAmt": intAmt2});
                        table.renderRow(i, {"fpriceAmt": fpriceAmt2});
                        table.renderRow(i, {"procedureAmt": procedureAmt2});
                        table.renderRow(i, {"settleServiceAmt": settleServiceAmt2});
                        table.renderRow(i, {"settleAmt": settleAmt2});
                        table.renderRow(i, {"exchangeAmt": exchangeAmt2});
                        table.renderRow(i, {"commissionAmt": commissionAmt2});
                        table.renderRow(i, {"stampDutyAmt": stampDutyAmt2});
                        table.renderRow(i, {"channelFee": channelFee2});
                        
                        if(ps=="T02"){
                            tradeAmtAndFee2=(fpriceAmt2-0)-(commissionAmt2-0)-(channelFee2-0)-(settleAmt2-0)-(exchangeAmt2-0);
                            table.renderRow(i, {"tradeAmtAndFee": tradeAmtAndFee2});
                        }else{
                            tradeAmtAndFee2=(fpriceAmt2-0)+(commissionAmt2-0)+(settleAmt2-0)+(exchangeAmt2-0);
                            table.renderRow(i, {"tradeAmtAndFee": tradeAmtAndFee2});
                        }
                        
                        intAmtTotle = JoyinMath.doMinus(intAmtTotle,intAmt2,2);
                        cpriceAmtTotle = JoyinMath.doMinus(cpriceAmtTotle,cpriceAmt2,2);
                        procedureAmtTotle = JoyinMath.doMinus(procedureAmtTotle,procedureAmt2,2);
                        settleServiceAmtTotle = JoyinMath.doMinus(settleServiceAmtTotle,settleServiceAmt2,2);
                        settleAmtTotle = JoyinMath.doMinus(settleAmtTotle,settleAmt2,2);
                        exchangeAmtTotle = JoyinMath.doMinus(exchangeAmtTotle,exchangeAmt2,2);
                        commissionAmtTotle = JoyinMath.doMinus(commissionAmtTotle,commissionAmt2,2);
                        stampDutyAmtTotle = JoyinMath.doMinus(stampDutyAmtTotle,stampDutyAmt2,2);
                        channelFeeTotle = JoyinMath.doMinus(channelFeeTotle,channelFee2,2);
                        
                        secTotalPrin2 = JoyinMath.doMinus(secTotalPrin2,table.getAllRows()[i].tradeAmt==" "?0:table.getAllRows()[i].tradeAmt,2);
                    }else{
                        var tradeAmt3=isNaN(table.getAllRows()[i].tradeAmt-0)?0:(table.getAllRows()[i].tradeAmt-0);
                        secTotalPrin2=isNaN(secTotalPrin2)?0:secTotalPrin2;
                        if(secTotalPrin2==tradeAmt3-0||table.getAllRows()[i].tradeAmt==null||table.getAllRows()[i].tradeAmt==""){
                            table.renderRow(i, {"tradeAmt": secTotalPrin2});
                            secTotalPrin2 = JoyinMath.doMinus(secTotalPrin2,secTotalPrin2,2);
                        }
                        if(table.getAllRows()[i].tradeAmt-0>0){
                            table.renderRow(i, {"cpriceAmt": cpriceAmtTotle});
                            table.renderRow(i, {"intAmt": intAmtTotle});
                            table.renderRow(i, {"fpriceAmt": JoyinMath.doAdd(cpriceAmtTotle,intAmtTotle,2)});
                            table.renderRow(i, {"procedureAmt": procedureAmtTotle});
                            table.renderRow(i, {"settleServiceAmt": settleServiceAmtTotle});
                            table.renderRow(i, {"settleAmt": settleAmtTotle});
                            table.renderRow(i, {"exchangeAmt": exchangeAmtTotle});
                            table.renderRow(i, {"commissionAmt": commissionAmtTotle});
                            table.renderRow(i, {"stampDutyAmt": stampDutyAmtTotle});
                            table.renderRow(i, {"channelFee": channelFeeTotle});
                            
                            if(ps=="T02"){
                                tradeAmtAndFee2=(JoyinMath.doAdd(cpriceAmtTotle,intAmtTotle,2)-0)-(commissionAmtTotle-0)-(channelFeeTotle-0)-(settleAmtTotle-0)-(exchangeAmtTotle-0);
                                table.renderRow(i, {"tradeAmtAndFee": tradeAmtAndFee2});
                            }else{
                                tradeAmtAndFee2=(JoyinMath.doAdd(cpriceAmtTotle,intAmtTotle,2)-0)+(commissionAmtTotle-0)+(settleAmtTotle-0)+(exchangeAmtTotle-0);
                                table.renderRow(i, {"tradeAmtAndFee": tradeAmtAndFee2});
                            }
                            
                            secTotalPrin2 = JoyinMath.doMinus(secTotalPrin2,table.getAllRows()[i].tradeAmt==" "?0:table.getAllRows()[i].tradeAmt,2);
                        }else{
                            for(var j = i; j>=0; j--){
                                if(table.getAllRows()[j].tradeAmt-0>0){
                                    var cpriceAmtLast = JoyinMath.doAdd(table.getAllRows()[j].cpriceAmt==" "?0:table.getAllRows()[j].cpriceAmt,cpriceAmtTotle,2);
                                    var intAmtLast    = JoyinMath.doAdd(table.getAllRows()[j].intAmt==" "?0:table.getAllRows()[j].intAmt,intAmtTotle,2);
                                    var fpriceAmtLast = JoyinMath.doAdd(cpriceAmtLast,intAmtLast,2);
                                    var procedureAmtLast = JoyinMath.doAdd(table.getAllRows()[j].procedureAmt==" "?0:table.getAllRows()[j].procedureAmt,procedureAmtTotle,2);
                                    var settleServiceAmtLast = JoyinMath.doAdd(table.getAllRows()[j].settleServiceAmt==" "?0:table.getAllRows()[j].settleServiceAmt,settleServiceAmtTotle,2);
                                    var settleAmtLast = JoyinMath.doAdd(table.getAllRows()[j].settleAmt==" "?0:table.getAllRows()[j].settleAmt,settleAmtTotle,2);
                                    var exchangeAmtLast = JoyinMath.doAdd(table.getAllRows()[j].exchangeAmt==" "?0:table.getAllRows()[j].exchangeAmt,exchangeAmtTotle,2);
                                    var commissionAmtLast = JoyinMath.doAdd(table.getAllRows()[j].commissionAmt==" "?0:table.getAllRows()[j].commissionAmt,commissionAmtTotle,2);
                                    var stampDutyAmtLast = JoyinMath.doAdd(table.getAllRows()[j].stampDutyAmt==" "?0:table.getAllRows()[j].stampDutyAmt,stampDutyAmtTotle,2);
                                    var channelFeeLast = JoyinMath.doAdd(table.getAllRows()[j].channelFee==" "?0:table.getAllRows()[j].channelFee,channelFeeTotle,2);
                                    table.renderRow(j, {"cpriceAmt": cpriceAmtLast});
                                    table.renderRow(j, {"intAmt": intAmtLast});
                                    table.renderRow(j, {"fpriceAmt": fpriceAmtLast});
                                    table.renderRow(j, {"procedureAmt": procedureAmtLast});
                                    table.renderRow(j, {"settleServiceAmt": settleServiceAmtLast});
                                    table.renderRow(j, {"settleAmt": settleAmtLast});
                                    table.renderRow(j, {"exchangeAmt": exchangeAmtLast});
                                    table.renderRow(j, {"commissionAmt": commissionAmtLast});
                                    table.renderRow(j, {"stampDutyAmt": stampDutyAmtLast});
                                    table.renderRow(i, {"channelFee": channelFeeLast});
                                    
                                    if(ps=="T02"){
                                        tradeAmtAndFee2=(fpriceAmtLast-0)-(commissionAmtLast-0)-(channelFeeLast-0)-(settleAmtLast-0)-(exchangeAmtLast-0);
                                        table.renderRow(j, {"tradeAmtAndFee": tradeAmtAndFee2});
                                    }else{
                                        tradeAmtAndFee2=(fpriceAmtLast-0)+(commissionAmtLast-0)+(settleAmtLast-0)+(exchangeAmtLast-0);
                                        table.renderRow(j, {"tradeAmtAndFee": tradeAmtAndFee2});
                                    }
                                    break;
                                }else{
                                	 var zero="0";
                                     table.renderRow(j, {"cpriceAmt": zero});
                                     table.renderRow(j, {"intAmt": zero});
                                     table.renderRow(j, {"fpriceAmt": zero});
                                     table.renderRow(j, {"procedureAmt": zero});
                                     table.renderRow(j, {"settleServiceAmt": zero});
                                     table.renderRow(j, {"settleAmt": zero});
                                     table.renderRow(j, {"exchangeAmt": zero});
                                     table.renderRow(j, {"commissionAmt": zero});
                                     table.renderRow(j, {"stampDutyAmt": zero});
                                     table.renderRow(i, {"channelFee": zero});
 									table.renderRow(j, {"tradeAmtAndFee": zero});
                                 }
                            }
                        }
                    }
                }
            }
            
            var params = {};
            //组合T和T+1可以现金
            if(table.getAllRows()[i].portfolioId&&$("#calendarId").val() && $("#ccy").val() && $("#tradeDate").val()){
                if($('#chlId').val()){
                    params.chlId = $('#chlId').val();
                }
                params.portfolioId=table.getAllRows()[i].portfolioId;
                params.calendarId=$("#calendarId").val();
                params.tradeDate=$("#tradeDate").val();
                params.acctTypeList = 'A01,A07';
                params.ccy=$("#ccy").val();
                Joyin.ajaxJson(JoyinUtil.getRealPath('/component/trade/BussTrdAvailableCashQuery/getAvailableCash'), params, function (cashdata) {
                    if(cashdata.T_0){
                        table.renderRow(i, {"bussTrdAvailableCashQueryavalibleCash": cashdata.T_0});
                    }
                    if(cashdata.T_1){
                        table.renderRow(i, {"bussTrdAvailableCashQueryavalibleCash1": cashdata.T_1});
                    }
                    if(cashdata.T_2){
                        table.renderRow(i, {"channelAvailableCash": cashdata.T_2});
                    }
                },false);
            }
            //交易场所可用资金
            if($('#tradeMarket').val()&&($('#tradeMarket').val()=='03'||$('#tradeMarket').val()=='04')
                && table.getAllRows()[i].secAcctId&&table.getAllRows()[i].portfolioId){
                var param = {};
                param.portfolioId=table.getAllRows()[i].portfolioId;
                param.secManageAcctId=table.getAllRows()[i].secAcctId;
                param.ccy=$('#ccy').val();
                param.calendarId = $('#calendarId').val();
                Joyin.ajaxJson(JoyinUtil.getRealPath('/invest/trade/bond/TrdBondInvestTrade003/getBankCash'), param, function (result) {
                    if(result){
                        table.renderRow(i, {"accAvailableCash": Number(result)});
                    }

                },false);
            }
            //交易方向为卖出时显示资产可用持仓
            if(table.getAllRows()[i].secAcctId&&table.getAllRows()[i].portfolioId
                && $("#calendarId").val() && $("#ccy").val() && $("#tradeDate").val()&&!($('#ps').val()=='T01')){

                params.portfolioId=table.getAllRows()[i].portfolioId;
                params.finprodId=$("#finprodId").val();
                params.calendarId=$("#calendarId").val();
                params.tradeDate=$("#tradeDate").val();
                params.acctTypeList = 'A01,A07';
                params.ccy=$("#ccy").val();
                params.multiParam=100;
                params.secAcctId=table.getAllRows()[i].secAcctId;
                Joyin.ajaxJson(JoyinUtil.getRealPath('/component/trade/BussTrdAvailableCashQuery/getAvailableCash'), params, function (cashdata) {
                    if( cashdata.position){
                        table.renderRow(i, {"assetsavailablepositions": cashdata.position});
                    }
                },false);
            }
            
        }
    }

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
        $('#TrdBondInvestTrade003_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TrdBondInvestTrade003_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
//        $('#TrdBondInvestTrade002_btn_risk').click(function() {
//            Joyin.dialog(JoyinUtil.getLabel("BaseRiskMathComm"),
//                    basePath + "business/common/BaseRiskMathComm/init",
//                    "65%", "80%",
//                    { pageModel: PAGE_MODEL_REFERENCE });
//        });
        //风险试算
        $('#TrdBondInvestTrade003_btn_risk').click(function(){
            //检查组合分配的和金额信息是否一致
              if($('#tradeAmt').val()){
                  var number = 0;
                  for(var i=0;i<table.getAllRows().length;i++){
                      var tradeAmt=table.getAllRows()[i].tradeAmt==" "?0:table.getAllRows()[i].tradeAmt;
                      if(tradeAmt){
                          number =  JoyinMath.doAdd(number,tradeAmt,2);
                      }
                  }
                  if(JoyinMath.doAdd($('#tradeAmt').val(),0,2)!=number){
                      Joyin.warn("组合分配劵面总额和金额信息券面总额不一致");
                      return;
                  }
              }

              //-----------add by jjpei 2020-01-07 风险新版本 start----------
              var object = $.serializeToObject('#submitForm', true);
              window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfBatchData/1/RSK002/F01',object,getriskdata());
              //-----------add by jjpei 2020-01-07 风险新版本 end----------
              /*Joyin.ajax(basePath + 'invest/trade/bond/TrdBondInvestTrade003/selectIndex',{},function(result){
                  if(result){
                      var index=result;
                      var object1=getriskdata();
                      var list2=object1.data;
                      for(var i in list2){
                          object1.data[i].mun=list2.length;
                          object1.data[i].index=index;
                      }
                      //window.BaseRiskMathComm.initBaseRiskMathCommPage2(object1);
                      $("#riskMathFlag").val("02");
                  }
              });*/
          });

        /**
         * 画面联动处理
         * 1.1交易场所
         */
        $('#tradeMarket').on("change", function () {
            var tradeMarket = $('#tradeMarket').val();
        	if(tradeMarket==TRADE_PLACE_05){
            	$('#tradeMarketMode').val(SHARES_TRADE_MARKET_02).prop('disabled', true).trigger('change');
            }else if (!$("#entrustId").val()){
            	$('#tradeMarketMode').prop('disabled', false);
            }
            if (tradeMarket) {
                if (tradeMarket == TRADE_PLACE_01) {
                    $('#commissionAmt').val('');
                    $('#commissionAmtDiv').hide();
                    $('#settleAmt').val('');
                    $('#settleAmtDiv').hide();
                    $('#exchangeAmt').val('');
                    $('#exchangeAmtDiv').hide();
                    $("#stampDutyAmtDiv").val('').hide();
                    table.showColumns([{field:'accAvailableCash',visible: false}]);
                    table.showColumns([{field:'commissionAmt',visible: false}]);
                    table.showColumns([{field:'procedureAmt',visible: true}]);
                    table.showColumns([{field:'settleServiceAmt',visible: true}]);
                    table.showColumns([{field:'settleAmt',visible: false}]);
                    table.showColumns([{field:'exchangeAmt',visible: false}]);
                    table.showColumns([{field:'stampDutyAmt',visible: false}]);
                    table.showColumns([{field:'tradeAmtAndFee',visible: true}]);
                    tableIsClear($("#entrustId").val());
                    // if($('#fTradeId').val()){
                    //     table.addRow(getAllRowsForEntrustId());
                    // }

                    // 选择【银行间】时，
                    // 1)交易平台默认为银行间且不可修改；
                    //checkAndAssign("#tradePlat", SHARES_TRADE_PLATFORM_01,true);
                    // 2)需输入银行间成交编号，相关交易要素根据成交编号作为输入参数调用接口自动带出;
                    if(!$("#fTradeId").val()){
                        $("#fTradeId").attr("disabled", false);
                    }
                    $("#fTradeId").associateInput();

                    //----add by 裴家俊  2019/03/28-----
                    $("#procedureAmtDiv").show();
                    $("#settleServiceAmtDiv").show();
                    $("#procedureAmtRateDiv").show();
                    $("#settleAmtDiv").hide();
                    $("#exchangeAmtDiv").hide();
                    $("#commissionAmtDiv").hide();
                    $("#commissionAmtRateDiv").hide();
//                $("#tradeAmtAndFeeDiv").hide();
                } else if (tradeMarket == TRADE_PLACE_02 || tradeMarket == TRADE_PLACE_03) {
                    $('#procedureAmt').val('');
                    $('#settleServiceAmt').val('');
                    $('#settleAmt').val('');
                    $('#exchangeAmt').val('');
                    table.showColumns([{field:'procedureAmt',visible: false}]);
                    table.showColumns([{field:'settleServiceAmt',visible: false}]);
                    table.showColumns([{field:'accAvailableCash',visible: true}]);
                    table.showColumns([{field:'commissionAmt',visible: true}]);
                    table.showColumns([{field:'tradeAmtAndFee',visible: true}]);
                    if($("#type6").val()&&$("#type6").val()==WINDTYPEONE_B17){
                        table.showColumns([{field:'stampDutyAmt',visible: true}]);
                        $("#stampDutyAmtDiv").show();
                    }else{
                        table.showColumns([{field:'stampDutyAmt',visible: false}]);
                        $("#stampDutyAmtDiv").val('').hide();
                    }
                    tableIsClear($("#entrustId").val());
                    // if($('#fTradeId').val()){
                    //     table.addRow(getAllRowsForEntrustId());
                    // }
                    // 选择【上海证券交易所】或【深圳证券交易所】时，
                    // 1)交易平台可自主选择;
                    if($("#entrustId").val() != ''){
                        $("#tradePlat").attr("disabled", true);
                    }else{
                        //$("#tradePlat").attr("disabled", false);
                    }
                    // 3)无交易成交单 此项不可填
                    // $("#fTradeId").val("").trigger("change").attr("disabled", true);
                    //
                    //----add by 裴家俊  2019/03/28-----
                    $("#procedureAmtDiv").hide();
                    $("#settleServiceAmtDiv").hide();
                    $("#procedureAmtRateDiv").hide();
                    $("#settleAmtDiv").hide();
                    $("#exchangeAmtDiv").hide();
                    $("#commissionAmtDiv").show();
                    $("#commissionAmtRateDiv").show();
//                $("#tradeAmtAndFeeDiv").show();
                    /*var tradeMarketMode = $("#tradeMarketMode").val();
                    if (tradeMarketMode == SHARES_TRADE_MARKET_01) {
                        $("#commissionAmt").prop("disabled", true).val(0).trigger('change');
                    }
                    else {
                        $("#commissionAmt").prop("disabled", false).val(commissionAmt).trigger('change');
                    }*/
                } else {
                    table.showColumns([{field:'accAvailableCash',visible: false}]);
                    table.showColumns([{field:'tradeAmtAndFee',visible: true}]);
                    tableIsClear($("#entrustId").val());
                    // if($('#fTradeId').val()){
                    //     table.addRow(getAllRowsForEntrustId());
                    // }
                    // 1)交易平台可自主选择;
                    // 1)交易平台默认为其它，可改
                    if($("#entrustId").val() != ''){
                        $("#tradePlat").attr("disabled", true);
                    }else{
                        checkAndAssign("#tradePlat", SHARES_TRADE_PLATFORM_99, true);
                        $("#tradePlat").attr("disabled", false);
                    }
                    // 3)无交易成交单 此项不可填
                    //$("#fTradeId").val("").trigger("change").attr("disabled", true);

                    //----add by 裴家俊  2019/03/28-----
                    $("#procedureAmtDiv").show();
                    $("#settleServiceAmtDiv").show();
                    $("#procedureAmtRateDiv").show();
                    $("#settleAmtDiv").show();
                    $("#exchangeAmtDiv").show();
                    $("#commissionAmtDiv").hide();
                    $("#commissionAmtRateDiv").hide();
//                $("#tradeAmtAndFeeDiv").hide();
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
                table.showColumns([{field:'channelAvailableCash',visible: false}]);
                table.showColumns([{field:'accAvailableCash',visible: false}]);
                // table.showColumns([{field:'assetsavailablepositions',visible: false}]);
                //modify by lwj 20200327 start
                    if($('#chlId').val() && 'T02'==$('#ps').val()){
                   	    table.showColumns([{field:'channelFee',visible: true}]);
                     }
                     $('#tradeAmtAndFee').show();
                  //modify by lwj 20200327 end  
                tableIsClear($("#entrustId").val());
                // if($('#fTradeId').val()){
                //     table.addRow(getAllRowsForEntrustId());
                // }
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
                $('#tradeAmtAndFee').show();
                table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash',visible: true}]);
                table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash1',visible: true}]);
                table.showColumns([{field:'channelAvailableCash',visible: true}]);
                if($('#tradeMarket').val()=='03'||$('#tradeMarket').val()=='04'){
                    table.showColumns([{field:'accAvailableCash',visible: true}]);
                }
                // table.showColumns([{field:'assetsavailablepositions',visible: true}]);
                tableIsClear($("#entrustId").val());
                // if($('#fTradeId').val()){
                //     table.addRow(getAllRowsForEntrustId());
                // }
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
            
            if (ps == PS_T02&&$("#chlId").val()){
                $("#channelFee_div").show();
            }else{
                $("#channelFee_div").hide();
            }
            
            changeTradeAmtAndFee();
        });
        //分配方式
        $('#TrdBondInvestTrade003_btn_autoAllocation').click(function () {
            if( !$('#TrdBondInvestTrade003_btn_autoAllocation').hasClass('layui-btn-disabled') ){
                extractedZiDongFenPei();
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
        $('#settleSpeed').attr("disabled", false);
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

    // 指令类型下拉框change事件处理
	$('#zhilingtype').change(function(){
		zhilingtypeChangeAction();
	});
	//按照比例计算净价总额
    function extractedCpriceAmt() {
        var tradeAmtAll = $('#tradeAmt').val();
        var all = 0;
        for (var i = 0; i < table.getAllRows().length; i++) {
            var tradeAmt = table.getAllRows()[i].tradeAmt;
            var result = JoyinMath.doDiv(tradeAmt, tradeAmtAll, 2);
            if ($('#cpriceAmt').val()&&tradeAmt) {
                if(table.getAllRows().length>1){
                    if (i != table.getAllRows().length - 1) {//非最后一列
                        var cpriceAmt =  JoyinMath.doMul(isNaN(tradeAmt)?0:tradeAmt,$('#cpriceAmt').val(),2);
                        cpriceAmt = JoyinMath.doDiv(cpriceAmt,tradeAmtAll,2);
                        all = JoyinMath.doAdd(all, cpriceAmt, 2);
                        all = isNaN(all)?0:all;
                        table.renderRow(i, {"cpriceAmt": isNaN(cpriceAmt)?0:cpriceAmt});
                    } else {
                        table.renderRow(i, {"cpriceAmt": JoyinMath.doMinus($('#cpriceAmt').val(), all, 2)});
                    }
                }else{
                    var cpriceAmt =  JoyinMath.doMul(isNaN(tradeAmt)?0:tradeAmt,$('#cpriceAmt').val(),2);
                    cpriceAmt = JoyinMath.doDiv(cpriceAmt,tradeAmtAll,2);
                    all = JoyinMath.doAdd(all, cpriceAmt, 2);
                    all = isNaN(all)?0:all;
                    table.renderRow(i, {"cpriceAmt": isNaN(cpriceAmt)?0:cpriceAmt});
                }

            }
        }
    }
    //按照比例计算结算费经手费
    function extractedSettleAmt() {
        var tradeAmtAll = $('#tradeAmt').val();
        var all = 0;
        for (var i = 0; i < table.getAllRows().length; i++) {
            var tradeAmt = table.getAllRows()[i].tradeAmt;
            var result = JoyinMath.doDiv(tradeAmt, tradeAmtAll, 2);
            if ($('#settleAmt').val()&&tradeAmt) {
                if(table.getAllRows().length>1){
                    if (i != table.getAllRows().length - 1) {//非最后一列
                        var settleAmt =  JoyinMath.doMul(isNaN(tradeAmt)?0:tradeAmt,$('#settleAmt').val(),2);
                        settleAmt = JoyinMath.doDiv(settleAmt,tradeAmtAll,2);
                        all = JoyinMath.doAdd(all, settleAmt, 2);
                        all = isNaN(all)?0:all;
                        table.renderRow(i, {"settleAmt": isNaN(settleAmt)?0:settleAmt});
                    } else {
                        table.renderRow(i, {"settleAmt": JoyinMath.doMinus($('#settleAmt').val(), all, 2)});
                    }
                }else{
                    var settleAmt =  JoyinMath.doMul(isNaN(tradeAmt)?0:tradeAmt,$('#settleAmt').val(),2);
                    settleAmt = JoyinMath.doDiv(settleAmt,tradeAmtAll,2);
                    all = JoyinMath.doAdd(all, settleAmt, 2);
                    all = isNaN(all)?0:all;
                    table.renderRow(i, {"settleAmt": isNaN(settleAmt)?0:settleAmt});
                }

            }
        }
    }
    //经手费
    function extractedExchangeAmt() {
        var tradeAmtAll = $('#tradeAmt').val();
            var all = 0;
            for (var i = 0; i < table.getAllRows().length; i++) {
                var tradeAmt = table.getAllRows()[i].tradeAmt;
                var result = JoyinMath.doDiv(tradeAmt, tradeAmtAll, 2);
                if ($('#exchangeAmt').val()&&tradeAmt) {
                    if(table.getAllRows().length>1){
                        if (i != table.getAllRows().length - 1) {//非最后一列
                            var exchangeAmt =  JoyinMath.doMul(isNaN(tradeAmt)?0:tradeAmt,$('#exchangeAmt').val(),2);
                            exchangeAmt = JoyinMath.doDiv(exchangeAmt,tradeAmtAll,2);
                            all = JoyinMath.doAdd(all, exchangeAmt, 2);
                            all = isNaN(all)?0:all;
                            table.renderRow(i, {"exchangeAmt": isNaN(exchangeAmt)?0:exchangeAmt});
                        } else {
                            table.renderRow(i, {"exchangeAmt": JoyinMath.doMinus($('#exchangeAmt').val(), all, 2)});
                        }
                    }else{
                        var exchangeAmt =  JoyinMath.doMul(isNaN(tradeAmt)?0:tradeAmt,$('#exchangeAmt').val(),2);
                        exchangeAmt = JoyinMath.doDiv(exchangeAmt,tradeAmtAll,2);
                        all = JoyinMath.doAdd(all, exchangeAmt, 2);
                        all = isNaN(all)?0:all;
                        table.renderRow(i, {"exchangeAmt": isNaN(exchangeAmt)?0:exchangeAmt});
                    }

                }
            }
    }
    //交易手续费
    function extractedProcedureAmt() {
        var tradeAmtAll = $('#tradeAmt').val();
            var all = 0;
            for (var i = 0; i < table.getAllRows().length; i++) {
                var tradeAmt = table.getAllRows()[i].tradeAmt;
                var result = JoyinMath.doDiv(tradeAmt, tradeAmtAll, 2);
                if ($('#procedureAmt').val()&&tradeAmt) {
                    if(table.getAllRows().length>1){
                        if (i != table.getAllRows().length - 1) {//非最后一列
                            var procedureAmt =  JoyinMath.doMul(isNaN(tradeAmt)?0:tradeAmt,$('#procedureAmt').val(),2);
                            procedureAmt = JoyinMath.doDiv(procedureAmt,tradeAmtAll,2);
                            all = JoyinMath.doAdd(all, procedureAmt, 2);
                            all = isNaN(all)?0:all;
                            table.renderRow(i, {"procedureAmt": isNaN(procedureAmt)?0:procedureAmt});
                        } else {
                            table.renderRow(i, {"procedureAmt": JoyinMath.doMinus($('#procedureAmt').val(), all, 2)});
                        }
                    }else{
                        var procedureAmt =  JoyinMath.doMul(isNaN(tradeAmt)?0:tradeAmt,$('#procedureAmt').val(),2);
                        procedureAmt = JoyinMath.doDiv(procedureAmt,tradeAmtAll,2);
                        all = JoyinMath.doAdd(all, procedureAmt, 2);
                        all = isNaN(all)?0:all;
                        table.renderRow(i, {"procedureAmt": isNaN(procedureAmt)?0:procedureAmt});
                    }

                }
            }
    }
    //结算服务费
    function extractedSettleServiceAmt() {
        var tradeAmtAll = $('#tradeAmt').val();
            var all = 0;
            for (var i = 0; i < table.getAllRows().length; i++) {
                var tradeAmt = table.getAllRows()[i].tradeAmt;
                var result = JoyinMath.doDiv(tradeAmt, tradeAmtAll, 2);
                if ($('#settleServiceAmt').val()&&tradeAmt) {
                    if(table.getAllRows().length>1){
                        if (i != table.getAllRows().length - 1) {//非最后一列
                            var settleServiceAmt =  JoyinMath.doMul(isNaN(tradeAmt)?0:tradeAmt,$('#settleServiceAmt').val(),2);
                            settleServiceAmt = JoyinMath.doDiv(settleServiceAmt,tradeAmtAll,2);
                            all = JoyinMath.doAdd(all, settleServiceAmt, 2);
                            all = isNaN(all)?0:all;
                            table.renderRow(i, {"settleServiceAmt": isNaN(settleServiceAmt)?0:settleServiceAmt});
                        } else {
                            table.renderRow(i, {"settleServiceAmt": JoyinMath.doMinus($('#settleServiceAmt').val(), all, 2)});
                        }
                    }else{
                        var settleServiceAmt =  JoyinMath.doMul(isNaN(tradeAmt)?0:tradeAmt,$('#settleServiceAmt').val(),2);
                        settleServiceAmt = JoyinMath.doDiv(settleServiceAmt,tradeAmtAll,2);
                        all = JoyinMath.doAdd(all, settleServiceAmt, 2);
                        all = isNaN(all)?0:all;
                        table.renderRow(i, {"settleServiceAmt": isNaN(settleServiceAmt)?0:settleServiceAmt});
                    }

                }
            }
    }

    //交易金额计算
    function extractedTradeAmtAndFee() {
        var tradeAmtAll = $('#tradeAmt').val();
            var all = 0;
            for (var i = 0; i < table.getAllRows().length; i++) {
                var tradeAmt = table.getAllRows()[i].tradeAmt;
                var result = JoyinMath.doDiv(tradeAmt, tradeAmtAll, 2);
                if ($('#tradeAmtAndFee').val()&&tradeAmt) {
                    if(table.getAllRows().length>1){
                        if (i != table.getAllRows().length - 1) {//非最后一列
                            var tradeAmtAndFee =  JoyinMath.doMul(isNaN(tradeAmt)?0:tradeAmt,$('#tradeAmtAndFee').val(),2);
                            tradeAmtAndFee = JoyinMath.doDiv(tradeAmtAndFee,tradeAmtAll,2);
                            all = JoyinMath.doAdd(all, tradeAmtAndFee, 2);
                            all = isNaN(all)?0:all;
                            table.renderRow(i, {"tradeAmtAndFee": isNaN(tradeAmtAndFee)?0:tradeAmtAndFee});
                        } else {
                            table.renderRow(i, {"tradeAmtAndFee": JoyinMath.doMinus($('#tradeAmtAndFee').val(), all, 2)});
                        }
                    }else{
                        var tradeAmtAndFee =  JoyinMath.doMul(isNaN(tradeAmt)?0:tradeAmt,$('#tradeAmtAndFee').val(),2);
                        tradeAmtAndFee = JoyinMath.doDiv(tradeAmtAndFee,tradeAmtAll,2);
                        all = JoyinMath.doAdd(all, tradeAmtAndFee, 2);
                        all = isNaN(all)?0:all;
                        table.renderRow(i, {"tradeAmtAndFee": isNaN(tradeAmtAndFee)?0:tradeAmtAndFee});
                    }

                }
            }
    }
    //全价总额计算
    function extractedFpriceAmt() {
        var tradeAmtAll = $('#tradeAmt').val();
            var all = 0;
            for (var i = 0; i < table.getAllRows().length; i++) {
                var tradeAmt = table.getAllRows()[i].tradeAmt;
                var result = JoyinMath.doDiv(tradeAmt, tradeAmtAll, 2);
                if ($('#fpriceAmt').val()&&tradeAmt) {
                    if(table.getAllRows().length>1){
                        if (i != table.getAllRows().length - 1) {//非最后一列
                            var fpriceAmt =  JoyinMath.doMul(isNaN(tradeAmt)?0:tradeAmt,$('#fpriceAmt').val(),2);
                            fpriceAmt = JoyinMath.doDiv(fpriceAmt,tradeAmtAll,2);
                            all = JoyinMath.doAdd(all, fpriceAmt, 2);
                            all = isNaN(all)?0:all;
                            table.renderRow(i, {"fpriceAmt": isNaN(fpriceAmt)?0:fpriceAmt});
                        } else {
                            table.renderRow(i, {"fpriceAmt": JoyinMath.doMinus($('#fpriceAmt').val(), all, 2)});
                        }
                    }else{
                        var fpriceAmt =  JoyinMath.doMul(isNaN(tradeAmt)?0:tradeAmt,$('#fpriceAmt').val(),2);
                        fpriceAmt = JoyinMath.doDiv(fpriceAmt,tradeAmtAll,2);
                        all = JoyinMath.doAdd(all, fpriceAmt, 2);
                        all = isNaN(all)?0:all;
                        table.renderRow(i, {"fpriceAmt": isNaN(fpriceAmt)?0:fpriceAmt});
                    }

                }
            }
    }
    //按照比例计算应计利息总额
    function extractedIntAmt(intAmtPara) {
        var tradeAmtAll = $('#tradeAmt').val();
        var all = 0;
        for (var i = 0; i < table.getAllRows().length; i++) {
            var tradeAmt = table.getAllRows()[i].tradeAmt;
            if(tradeAmt){
                if (typeof intAmtPara != 'undefined') {
                    table.renderRow(i, {"intAmt": isNaN(intAmtPara)?0:intAmtPara});
                } else if ($('#intAmt').val()) {
                    if(table.getAllRows().length>1){
                        if (i != table.getAllRows().length - 1) {//非最后一列
                            var intAmt2 = $('#intAmt').val();
                            var intAmt =  JoyinMath.doMul(tradeAmt, intAmt2,2);
                            intAmt =  JoyinMath.doDiv(intAmt,tradeAmtAll,2);
                            all = JoyinMath.doAdd(all, intAmt, 2);
                            all = isNaN(all)?0:all;
                            table.renderRow(i, {"intAmt": isNaN(intAmt)?0:intAmt});
                        } else {
                            table.renderRow(i, {"intAmt": JoyinMath.doMinus($('#intAmt').val(), all, 2)});
                        }
                    }else{
                        var intAmt =  JoyinMath.doMul(tradeAmt, $('#intAmt').val(),2);
                        intAmt =  JoyinMath.doDiv(intAmt,tradeAmtAll,2);
                        all = JoyinMath.doAdd(all, intAmt, 2);
                        all = isNaN(all)?0:all;
                        table.renderRow(i, {"intAmt": isNaN(intAmt)?0:intAmt});
                    }

                }
            }
        }
    }

    function extractedJiSuan(isTrue,returnVal) {
        var tradeAmtAl = $('#tradeAmt').val();
        var allprocedureAmt = 0;
        var allsettleServiceAmt = 0;
        var allsettleAmt = 0;
        var allexchangeAmt = 0;
        var allcommissionAmt = 0;
        var allstampDutyAmt = 0;
        var alltradeAmt = 0;
        var allchannelFee = 0;
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
                                table.renderRow(i, {"tradeAmt": isNaN(iNum)?0:iNum});
                                alltradeAmt = JoyinMath.doAdd(alltradeAmt,iNum,2);
                            }else{
                                table.renderRow(i, {"tradeAmt": isNaN(doEntrustMoney)?0:doEntrustMoney});
                                alltradeAmt = JoyinMath.doAdd(alltradeAmt,doEntrustMoney,2);
                            }
                        }else{
                            var numTo = JoyinMath.doMinus(tradeAmtAl,alltradeAmt,2);
                            if(Number(numTo)<=Number(doEntrustMoney)){
                                table.renderRow(i, {"tradeAmt": isNaN(numTo)?0:numTo});
                            }else{
                                table.renderRow(i, {"tradeAmt": isNaN(doEntrustMoney)?0:doEntrustMoney});
                            }
                        }
                    }else{
                        var num  = JoyinMath.doMul(tradeAmt,tradeAmtAl,2);
                        var iNum = JoyinMath.doDiv(num,tradeAmtAll,2);
                        if(Number(iNum)<=Number(doEntrustMoney)){
                            table.renderRow(i, {"tradeAmt": isNaN(iNum)?0:iNum});
                            alltradeAmt = JoyinMath.doAdd(alltradeAmt,iNum,2);
                        }else{
                            table.renderRow(i, {"tradeAmt": isNaN(doEntrustMoney)?0:doEntrustMoney});
                            alltradeAmt = JoyinMath.doAdd(alltradeAmt,doEntrustMoney,2);
                        }
                    }

                }
            }

            if (returnVal&&returnVal.procedureAmt==0) {
                if(table.getAllRows().length>1){
                    if (i != table.getAllRows().length - 1) {//非最后一列
	                    if(IS_AVERAGE_DISTRIBUTION_OF_EXPENSES=='Y'){
	            			var procedureAmt  =  JoyinMath.doDiv($('#procedureAmt').val()==""?0:$('#procedureAmt').val(),table.getAllRows().length,2);
	                        allprocedureAmt = JoyinMath.doAdd(allprocedureAmt, procedureAmt, 2);
	                        table.renderRow(i, {"procedureAmt": isNaN(procedureAmt)?0:procedureAmt});
            			}else{
							var procedureAmt  =  JoyinMath.doMul(tradeAmt,$('#procedureAmt').val()==""?0:$('#procedureAmt').val(),2);
	                        procedureAmt = JoyinMath.doDiv(procedureAmt,tradeAmtAl,2);
	                        allprocedureAmt = JoyinMath.doAdd(allprocedureAmt, procedureAmt, 2);
	                        table.renderRow(i, {"procedureAmt": isNaN(procedureAmt)?0:procedureAmt});
            			}
                    } else {
						var all=JoyinMath.doMinus($('#procedureAmt').val()==""?0:$('#procedureAmt').val(), allprocedureAmt, 2);
                        all=isNaN(all)?0:all;
                        table.renderRow(i, {"procedureAmt": all});
                    }
                }else{
                    var procedureAmt  =  JoyinMath.doMul(tradeAmt,$('#procedureAmt').val()==""?0:$('#procedureAmt').val(),2);
                    procedureAmt = JoyinMath.doDiv(procedureAmt,tradeAmtAl,2);
                    allprocedureAmt = JoyinMath.doAdd(allprocedureAmt, procedureAmt, 2);
                    table.renderRow(i, {"procedureAmt": isNaN(procedureAmt)?0:procedureAmt});
                }
            }

            if (returnVal&&returnVal.settleServiceAmt==0) {
                if(table.getAllRows().length>1){
                    if (i != table.getAllRows().length - 1) {//非最后一列
                        if(IS_AVERAGE_DISTRIBUTION_OF_EXPENSES=='Y'){
                            var settleServiceAmt  =  JoyinMath.doDiv($('#settleServiceAmt').val()==""?0:$('#settleServiceAmt').val(),table.getAllRows().length,2);
                            allsettleServiceAmt = JoyinMath.doAdd(allsettleServiceAmt, settleServiceAmt, 2);
                            table.renderRow(i, {"settleServiceAmt": isNaN(settleServiceAmt)?0:settleServiceAmt});
                        }else{
                            var settleServiceAmt  =  JoyinMath.doMul(tradeAmt,$('#settleServiceAmt').val()==""?0:$('#settleServiceAmt').val(),2);
                            settleServiceAmt = JoyinMath.doDiv(settleServiceAmt,tradeAmtAl,2);
                            allsettleServiceAmt = JoyinMath.doAdd(allsettleServiceAmt, settleServiceAmt, 2);
                            table.renderRow(i, {"settleServiceAmt": isNaN(settleServiceAmt)?0:settleServiceAmt});
                        }
                    } else {
                        var all=JoyinMath.doMinus($('#settleServiceAmt').val()==""?0:$('#settleServiceAmt').val(), allsettleServiceAmt, 2);
                        all=isNaN(all)?0:all;
                        table.renderRow(i, {"settleServiceAmt": all});
                    }
                }else{
                    var settleServiceAmt  =  JoyinMath.doMul(tradeAmt,$('#settleServiceAmt').val()==""?0:$('#settleServiceAmt').val(),2);
                    settleServiceAmt = JoyinMath.doDiv(settleServiceAmt,tradeAmtAl,2);
                    allsettleServiceAmt = JoyinMath.doAdd(allsettleServiceAmt, settleServiceAmt, 2);
                    table.renderRow(i, {"settleServiceAmt": isNaN(settleServiceAmt)?0:settleServiceAmt});
                }
            }

            if (returnVal&&returnVal.settleAmt==0) {
                if(table.getAllRows().length>1){
                    if (i != table.getAllRows().length - 1) {//非最后一列
	                    if(IS_AVERAGE_DISTRIBUTION_OF_EXPENSES=='Y'){
	            			var settleAmt  =  JoyinMath.doDiv($('#settleAmt').val()==""?0:$('#settleAmt').val(),table.getAllRows().length,2);
	                        allsettleAmt = JoyinMath.doAdd(allsettleAmt, settleAmt, 2);
	                        table.renderRow(i, {"settleAmt": isNaN(settleAmt)?0:settleAmt});
            			}else{
							var settleAmt  =  JoyinMath.doMul(tradeAmt,$('#settleAmt').val()==""?0:$('#settleAmt').val(),2);
	                        settleAmt = JoyinMath.doDiv(settleAmt,tradeAmtAl,2);
	                        allsettleAmt = JoyinMath.doAdd(allsettleAmt, settleAmt, 2);
	                        table.renderRow(i, {"settleAmt": isNaN(settleAmt)?0:settleAmt});
            			}
                    } else {
						var all=JoyinMath.doMinus($('#settleAmt').val()==""?0:$('#settleAmt').val(), allsettleAmt, 2);
                        all=isNaN(all)?0:all;
                        table.renderRow(i, {"settleAmt": all});
                    }
                }else{
					var settleAmt  =  JoyinMath.doMul(tradeAmt,$('#settleAmt').val()==""?0:$('#settleAmt').val(),2);
                    settleAmt = JoyinMath.doDiv(settleAmt,tradeAmtAl,2);
                    allsettleAmt = JoyinMath.doAdd(allsettleAmt, settleAmt, 2);
                    table.renderRow(i, {"settleAmt": isNaN(settleAmt)?0:settleAmt});
                }
            }
            if (returnVal&&returnVal.exchangeAmt==0) {
                if(table.getAllRows().length>1){
                    if (i != table.getAllRows().length - 1) {//非最后一列
                        if(IS_AVERAGE_DISTRIBUTION_OF_EXPENSES=='Y'){
                            var exchangeAmt  =  JoyinMath.doDiv($('#exchangeAmt').val()==""?0:$('#exchangeAmt').val(),table.getAllRows().length,2);
                            allexchangeAmt = JoyinMath.doAdd(allexchangeAmt, exchangeAmt, 2);
                            table.renderRow(i, {"exchangeAmt": isNaN(exchangeAmt)?0:exchangeAmt});
                        }else{
                            var exchangeAmt  =  JoyinMath.doMul(tradeAmt,$('#exchangeAmt').val()==""?0:$('#exchangeAmt').val(),2);
                            exchangeAmt = JoyinMath.doDiv(exchangeAmt,tradeAmtAl,2);
                            allexchangeAmt = JoyinMath.doAdd(allexchangeAmt, exchangeAmt, 2);
                            table.renderRow(i, {"exchangeAmt": isNaN(exchangeAmt)?0:exchangeAmt});
                        }
                    } else {
                        var all=JoyinMath.doMinus($('#exchangeAmt').val()==""?0:$('#exchangeAmt').val(), allexchangeAmt, 2);
                        all=isNaN(all)?0:all;
                        table.renderRow(i, {"exchangeAmt": all});
                    }
                }else{
                    var exchangeAmt  =  JoyinMath.doMul(tradeAmt,$('#exchangeAmt').val()==""?0:$('#exchangeAmt').val(),2);
                    exchangeAmt = JoyinMath.doDiv(exchangeAmt,tradeAmtAl,2);
                    allexchangeAmt = JoyinMath.doAdd(allexchangeAmt, exchangeAmt, 2);
                    table.renderRow(i, {"exchangeAmt": isNaN(exchangeAmt)?0:exchangeAmt});
                }
            }
            if (returnVal&&returnVal.commissionAmt==0) {
                if(table.getAllRows().length>1){
                    if (i != table.getAllRows().length - 1) {//非最后一列
	                    if(IS_AVERAGE_DISTRIBUTION_OF_EXPENSES=='Y'){
	            			var commissionAmt  =  JoyinMath.doDiv($('#commissionAmt').val()==""?0:$('#commissionAmt').val(),table.getAllRows().length,2);
	                        allcommissionAmt = JoyinMath.doAdd(allcommissionAmt, commissionAmt, 2);
	                        table.renderRow(i, {"commissionAmt": isNaN(commissionAmt)?0:commissionAmt});
            			}else{
							var commissionAmt  =  JoyinMath.doMul(tradeAmt,$('#commissionAmt').val()==""?0:$('#commissionAmt').val(),2);
	                        commissionAmt = JoyinMath.doDiv(commissionAmt,tradeAmtAl,2);
	                        allcommissionAmt = JoyinMath.doAdd(allcommissionAmt, commissionAmt, 2);
	                        table.renderRow(i, {"commissionAmt": isNaN(commissionAmt)?0:commissionAmt});
            			}
                    } else {
						var all=JoyinMath.doMinus($('#commissionAmt').val()==""?0:$('#commissionAmt').val(), allcommissionAmt, 2);
                        all=isNaN(all)?0:all;
                        table.renderRow(i, {"commissionAmt": all});
                    }
                }else{
                    var commissionAmt  =  JoyinMath.doMul(tradeAmt,$('#commissionAmt').val()==""?0:$('#commissionAmt').val(),2);
                    commissionAmt = JoyinMath.doDiv(commissionAmt,tradeAmtAl,2);
                    allcommissionAmt = JoyinMath.doAdd(allcommissionAmt, commissionAmt, 2);
                    table.renderRow(i, {"commissionAmt": isNaN(commissionAmt)?0:commissionAmt});
                }
            }

            if (returnVal&&returnVal.stampDutyAmt==0) {
                if(table.getAllRows().length>1){
                    if (i != table.getAllRows().length - 1) {//非最后一列
	                    if(IS_AVERAGE_DISTRIBUTION_OF_EXPENSES=='Y'){
	            			var stampDutyAmt  =  JoyinMath.doDiv($('#stampDutyAmt').val()==""?0:$('#stampDutyAmt').val(),table.getAllRows().length,2);
	                        allstampDutyAmtAmt = JoyinMath.doAdd(allprocedureAmt, stampDutyAmt, 2);
	                        table.renderRow(i, {"stampDutyAmt": isNaN(stampDutyAmt)?0:stampDutyAmt});
	            		}else{
							var stampDutyAmt  =  JoyinMath.doMul(tradeAmt,$('#stampDutyAmt').val()==""?0:$('#stampDutyAmt').val(),2);
	                        stampDutyAmt = JoyinMath.doDiv(stampDutyAmt,tradeAmtAl,2);
	                        allstampDutyAmt = JoyinMath.doAdd(allstampDutyAmt, stampDutyAmt, 2);
	                        table.renderRow(i, {"stampDutyAmt": isNaN(stampDutyAmt)?0:stampDutyAmt});
	            		}
                    } else {
						var all=JoyinMath.doMinus($('#stampDutyAmt').val()==""?0:$('#stampDutyAmt').val(), allstampDutyAmt, 2);
                        all=isNaN(all)?0:all;
                        table.renderRow(i, {"stampDutyAmt": all});
                    }
                }else{
					var stampDutyAmt  =  JoyinMath.doMul(tradeAmt,$('#stampDutyAmt').val()==""?0:$('#stampDutyAmt').val(),2);
                    stampDutyAmt = JoyinMath.doDiv(stampDutyAmt,tradeAmtAl,2);
                    allstampDutyAmt = JoyinMath.doAdd(allstampDutyAmt, stampDutyAmt, 2);
                    table.renderRow(i, {"stampDutyAmt": isNaN(stampDutyAmt)?0:stampDutyAmt});
                }
            }
            if (returnVal&&returnVal.channelFee==0) {
                if(table.getAllRows().length>1){
                    if (i != table.getAllRows().length - 1) {//非最后一列
                    	if(IS_AVERAGE_DISTRIBUTION_OF_EXPENSES=='Y'){
	            			var channelFee  =  JoyinMath.doDiv($('#channelFee').val()==""?0:$('#channelFee').val(),table.getAllRows().length,2);
	                        allchannelFee = JoyinMath.doAdd(allchannelFee, channelFee, 2);
	                        table.renderRow(i, {"channelFee": isNaN(channelFee)?0:channelFee});
	            		}else{
							var channelFee  =  JoyinMath.doMul(tradeAmt,$('#channelFee').val()==""?0:$('#channelFee').val(),2);
	                        channelFee = JoyinMath.doDiv(channelFee,tradeAmtAl,2);
	                        allchannelFee = JoyinMath.doAdd(allchannelFee, channelFee, 2);
	                        table.renderRow(i, {"channelFee": isNaN(channelFee)?0:channelFee});
	            		}
                    } else {
						var all=JoyinMath.doMinus($('#channelFee').val()==""?0:$('#channelFee').val(), allchannelFee, 2);
                        all=isNaN(all)?0:all;
                        table.renderRow(i, {"channelFee": all});
                    }
                }else{
                    var channelFee  =  JoyinMath.doMul(tradeAmt,$('#channelFee').val()==""?0:$('#channelFee').val(),2);
                    channelFee = JoyinMath.doDiv(channelFee,tradeAmtAl,2);
                    allchannelFee = JoyinMath.doAdd(allchannelFee, channelFee, 2);
                    table.renderRow(i, {"channelFee": isNaN(channelFee)?0:channelFee});
                }
            }
        }
    }
    var zhilingtypeChangeAction = function(){
		$("#portfolioId").attr("disabled", true);
		$('#bussTrdAvailableCashQuery_div_cash').show();
		$("#tradeAmt").attr("disabled", false);
		$("#fpriceAmt").attr("disabled", false);

		if($("#zhilingtype").val()=="02"){
			//组合分配页面展示
			$('#bussTrdTransfenpei_div').show();
			//委托代码置灰
			$("#portfolioId").attr("disabled", true);
			$("#portfolioId ").empty();
			window.BussTrdAvailableCashQuery && window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'].hideCash(true);
			$("#tradeAmt").attr("disabled", false);
			//多组合时全价总额自动计算
			$("#fpriceAmt").attr("disabled", false);
			if(!$("#bussTrdTransfenpeiTable").data('joyintable')){
				table = $("#bussTrdTransfenpeiTable").joyintable({
			          data:[],
			          searchOnInit:true,
			          height:'225px',
			          cols:[
			                // {field:'busiId', title: 'TrdInvestFenPeiInfo.busiId',sortable:false, edit:true, editOption:{type:'select',formatoption:true,parentIds:'secManageAcctId',url: JoyinUtil.getRealPath('/business/common/CommonSelect/selectPortfolioId?act=true')}}
                          {field:'entrustId',  width:100,title: 'TrdInvestFenPeiInfo.entrustId',edit:false, sortable:false,type:'text'}
                          ,{field:'doEntrustMoney',  width:100,title: 'TrdInvestFenPeiInfo.doEntrustMoney',edit:true, sortable:false,edit:false,type:'number', digit:2}
                          ,{field:'portfolioId', width:100,  title: 'TrdInvestFenPeiInfo.busiId',edit:true,sortable: false,
                              editOption:{type:'select',childrenIds:'secManageAcctIdDuo', id:'portfolioId', multiple: false, showField:'id',searchable: true,
                                  url: JoyinUtil.getRealPath('/business/common/CommonSelect/selectPortfolioId?act=true')
                              }
                          }
			               ,{field:'investaim', width:100, title: 'TrdInvestFenPeiInfo.investaim',edit:true, sortable:false,editOption:{type:'select',formatoption:true,result: INV_AIM}}
			               ,{field:'tradeAmt',  width:100,title: 'TrdInvestFenPeiInfo.tradeAmt',edit:true, sortable:false,type:'number', digit:2}
                          ,{field:'secManageAcctIdDuo', width:100,  title: 'EtrInvestBondInfo003.secManageAcctId',edit:true,sortable: false,
                              editOption:{type:'select',showField:'text',id:'secManageAcctIdDuo', parentIds:'portfolioId,tradeMarket', multiple: false,searchable: true,
                                  url: JoyinUtil.getRealPath('/invest/trade/bond/TrdBondInvestTrade002/getSecManageAccId')
                              }
                          }
                          // 本金金额
			               ,{field:'cpriceAmt',width:100,  title: 'TrdInvestFenPeiInfo.cpriceAmt',sortable:false,type:'number', digit:2}
			               ,{field:'intAmt',width:100, title: 'TrdInvestFenPeiInfo.intAmt',sortable:false,type:'number', digit:2}
			               ,{field:'fpriceAmt',width:100, title: 'TrdInvestFenPeiInfo.fpriceAmt',sortable:false,type:'number', digit:2}
                           ,{field:'procedureAmt',width:100, title: 'TrdInvestFenPeiInfo.procedureAmt',sortable:false,type:'number', edit:true,digit:2}
                           ,{field:'settleServiceAmt',width:100, title: 'TrdInvestFenPeiInfo.settleServiceAmt',sortable:false,type:'number', edit:true,digit:2}
                           ,{field:'commissionAmt',width:100, title: 'TrdInvestFenPeiInfo.commissionAmt',sortable:false,type:'number',edit:true, digit:2}
                           ,{field:'settleAmt',width:100, title: 'TrdInvestFenPeiInfo.settleAmt',sortable:false,type:'number', edit:true,digit:2}
                           ,{field:'exchangeAmt',width:100, title: 'TrdInvestFenPeiInfo.exchangeAmt',sortable:false,type:'number', edit:true,digit:2}
                          ,{field:'stampDutyAmt',width:100, title: 'TrdInvestFenPeiInfo.stampDutyAmt',sortable:false,type:'number', edit:true,digit:2}
                           ,{field:'channelFee',width:100, title: function(){return JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("TrdInvestFenPeiInfo.channelFee");}(),sortable:false,type:'number', digit:2, visible:false} //通道费
                           ,{field:'tradeAmtAndFee',width:100, title: 'TrdInvestFenPeiInfo.tradeAmtAndFee',sortable:false,type:'number', digit:2}
			               // ,{field:'tgSurpluscash',width:100, title: 'TrdInvestFenPeiInfo.tgSurpluscash',sortable:false}
			               ,{field:'bussTrdAvailableCashQueryavalibleCash', width:100,title: 'TrdInvestFenPeiInfo.bussTrdAvailableCashQueryavalibleCash',sortable:false,type:'number', digit:2}
			               ,{field:'bussTrdAvailableCashQueryavalibleCash1',width:100, title: 'TrdInvestFenPeiInfo.bussTrdAvailableCashQueryavalibleCash1',sortable:false,type:'number', digit:2}
			               ,{field:'assetsavailablepositions',width:100, title: 'TrdInvestFenPeiInfo.assetsavailablepositions',sortable:false,type:'number', digit:2}
                          ,{field:'channelAvailableCash', width:100, title:  function(){return JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("EtrInvestBondInfo002.channelAvailableCash");}(), sortable: false,type:'number', digit:2}
                          ,{field:'accAvailableCash', width:100, title: 'TrdInvestFenPeiInfo.accAvailableCash', sortable: false,type:'number', digit:2}
			               ,{field:'operation', width:100,title: 'SysCommon.operation',sortable:false, type:'action', buttons: ['delt']}
			           ],
			           addbtn:true,
			           done:function(){
			        	   this.bind('click', '.delt', function(data, index) {
			                   table.deleteRow(index);
			               });
			           },
                        beforeEditCell:function(value, field, data){
                            var entrustId = $("#entrustId").val();
			              if(field=='portfolioId'&&entrustId){
                                return false;
                          }
                            if(field=='investaim'&&entrustId){
                                return false;
                            }
                            if(field=='tradeAmt'&&entrustId){
                                return true;
                            }
                            if(field=='secManageAcctIdDuo'&&entrustId){
                                if(data.secManageAcctIdDuo){
                                    return false;
                                }else{
                                    return true;
                                }
                            }
                            if(field=="secManageAcctIdDuo"){
                                if($('#chlId').val()){
                                    table.renderRow(data.LAY_TABLE_INDEX,  {"secManageAcctIdDuo": ""});
                                    return false;
                                }else{
                                    return true;
                                }
                            }else{
                                return true;
                            }
                        },
			           editCell: function(value, data, field, cell, column){

			              var returnVal = {};
			        	   var params={};
//        				   table.renderRow(data.LAY_TABLE_INDEX,  {"investaim": $("#invAim").val()});
        				   //买入
			        	   if("portfolioId"==field && "T01"==$("#ps").val()){
                               if(value){
                                   table.renderRow(data.LAY_TABLE_INDEX,  {"secManageAcctIdDuo": ""});
                               }
                               var rowData = {};
			        		   table.renderRow(data.LAY_TABLE_INDEX, {"assetsavailablepositions":''})
			        		   if(value && value.trim()
			                        && $("#calendarId").val() && $("#ccy").val() && $("#tradeDate").val()){
			            	        if($('#chlId').val()){
                                    params.chlId = $('#chlId').val();
                                    }
			        			   params.portfolioId=value;
			        			   params.calendarId=$("#calendarId").val();
			        			   params.tradeDate=$("#tradeDate").val();
			        			   params.acctTypeList = ACCT_TYPE_A01 + "," + ACCT_TYPE_A07;
			        			   params.ccy=$("#ccy").val();
			        			   Joyin.ajaxJson(JoyinUtil.getRealPath('/component/trade/BussTrdAvailableCashQuery/getAvailableCash'), params, function (cashdata) {
			        				   table.renderRow(data.LAY_TABLE_INDEX, {"bussTrdAvailableCashQueryavalibleCash": cashdata.T_0});
			        				   table.renderRow(data.LAY_TABLE_INDEX, {"bussTrdAvailableCashQueryavalibleCash1": cashdata.T_1});
                                       table.renderRow(data.LAY_TABLE_INDEX, {"channelAvailableCash": cashdata.T_2});
			        			   });
			        			   //托管户剩余现金
			        			   if($("#ccy").val() && value && value.trim()) {
			        				   params.portfolioId=value;
			        				   params.acctType = ACCT_TYPE_A01;
			        		            Joyin.ajaxJson(JoyinUtil.getRealPath('/component/trade/BussTrdAvailableCashQuery/getSecManageAvailableCash'), params, function (balance) {
			        		            	table.renderRow(data.LAY_TABLE_INDEX, {"tgSurpluscash": balance});
			        		            });
			        		        }
			        			   //通道可用现金// if($('#chlId').val()&& value && value.trim()){
                                   //     var param = {};
                                   //     param.portfolioId=value;
                                   //     param.TongDao = $('#chlId').val();
                                   //     Joyin.ajaxJson(JoyinUtil.getRealPath('/invest/trade/bond/TrdBondInvestTrade003/getTongDaoCash'), param, function (result) {
                                   //         rowData.channelAvailableCash = Number(result);
                                   //         table.renderRow(data.LAY_TABLE_INDEX,rowData);
                                   //     });
                                   // }
			        		   }
			        	   }else if("portfolioId"==field && "T01"!=$("#ps").val()){
                               if(value){
                                   table.renderRow(data.LAY_TABLE_INDEX,  {"secManageAcctIdDuo": ""});
                               }
			        		   table.renderRow(data.LAY_TABLE_INDEX, {"bussTrdAvailableCashQueryavalibleCash": ''});
			        		   table.renderRow(data.LAY_TABLE_INDEX, {"bussTrdAvailableCashQueryavalibleCash1": ''});
			        		   table.renderRow(data.LAY_TABLE_INDEX, {"tgSurpluscash": ''});
                               if(value && value.trim()
                                   && $("#calendarId").val() && $("#ccy").val() && $("#tradeDate").val()){
                                   if($('#chlId').val()){
                                       params.chlId = $('#chlId').val();
                                   }
                                   params.portfolioId=value;
                                   params.calendarId=$("#calendarId").val();
                                   params.tradeDate=$("#tradeDate").val();
                                   params.acctTypeList = ACCT_TYPE_A01 + "," + ACCT_TYPE_A07;
                                   params.ccy=$("#ccy").val();
                                   params.multiParam = 100;
                                   params.finprodId = $("#finprodId").val();
                                   params.secAcctId =data.secManageAcctIdDuo;
                                   params.invAim = $("#invAim").val();
                                       Joyin.ajaxJson(JoyinUtil.getRealPath('/component/trade/BussTrdAvailableCashQuery/getAvailableCash'), params, function (cashdata) {
                                       table.renderRow(data.LAY_TABLE_INDEX, {"assetsavailablepositions": cashdata.position});
                                   },false);
                               }
			        	   }
			        	   if(field=='secManageAcctIdDuo'){
			        	       if(value&& value.trim()){
                                   $('input[name="secManageAcctId"]').val(value.trim());
                               }
			        	       
			        	       var b=0;
                               for(var i = 0; i < table.getAllRows().length; i++){
                                   if(!table.getAllRows()[i].secManageAcctIdDuo){
                                       b++;
                                   }
                               }
                               if(b==table.getAllRows().length){
                                   $('#chlId').attr("disabled", false);
                               }else{
                                   $('#chlId').val(" ");
                                   $('#chlId').attr("disabled", true);
                               }
                               
                               var rowData = {};
                               //交易场所可用资金
                               if($('#tradeMarket').val()&&($('#tradeMarket').val()=='03'||$('#tradeMarket').val()=='04')
                                   && value && value.trim()&&data.portfolioId){
                                   var param = {};
                                   param.portfolioId=data.portfolioId;
                                   param.secManageAcctId=value;
                                   param.ccy=$('#ccy').val();
                                   param.calendarId = $('#calendarId').val();
                                   Joyin.ajaxJson(JoyinUtil.getRealPath('/invest/trade/bond/TrdBondInvestTrade003/getBankCash'), param, function (result) {
                                       rowData.accAvailableCash = Number(result);
                                       table.renderRow(data.LAY_TABLE_INDEX,rowData);
                                   });
                               }
                               //交易方向为卖出时显示资产可用持仓
                               if(value && value.trim()&&data.portfolioId
                                   && $("#calendarId").val() && $("#ccy").val() && $("#tradeDate").val()&&!($('#ps').val()=='T01')){

                                   params.portfolioId=data.portfolioId;
                                   params.finprodId=$("#finprodId").val();
                                   params.calendarId=$("#calendarId").val();
                                   params.tradeDate=$("#tradeDate").val();
                                   params.acctTypeList = ACCT_TYPE_A01 + "," + ACCT_TYPE_A07;
                                   params.ccy=$("#ccy").val();
                                   params.multiParam=100;
                                   params.secAcctId=value;
                                   Joyin.ajaxJson(JoyinUtil.getRealPath('/component/trade/BussTrdAvailableCashQuery/getAvailableCash'), params, function (cashdata) {
                                       table.renderRow(data.LAY_TABLE_INDEX, {"assetsavailablepositions": cashdata.position});
                                   });
                               }
                               data.isEntrust='NO';
                                //设置手续费和结算费
//                               returnVal =  extractedJiSunFeiYong(data);
                           }

			        	   //只有券面总额发生变化才需要更新净价总额、应计利息总额
			        	   if("tradeAmt"==field){
			        	       isChange = true;
			        	       //净价总额按比例分配
                                if(value&&$('#tradeAmt').val()){
                                    extractedCpriceAmt();
                                }
                                //应计利息总额按照比率分配
                               if(value&&$('#tradeAmt').val()){
                                   extractedIntAmt();
                               }
			        		   //交易手续费和结算费按比计算
                               if(value&&$('#tradeAmt').val()){
                                   extractedJiSuan(false,returnVal);
                               }
                               //结算费经手费改变
                               if(value&&$('#tradeAmt').val()){
                                   extractedSettleAmt();
                               }
                               if(value&&$('#tradeAmt').val()){
                                   extractedExchangeAmt();
                               }
                               //当券面总额变化，手续费和服务费也相应变化
                               if(value&&$('#tradeAmt').val()){
                                   extractedProcedureAmt();
                               }
                               if(value&&$('#tradeAmt').val()){
                                   extractedSettleServiceAmt();
                               }
                               if(value&&$('#tradeAmt').val()){
                                   extractedFpriceAmt();
                               }
                               if(value&&$('#tradeAmt').val()){
                                   extractedTradeAmtAndFee();
                               }
                               var entrustId = $("#entrustId").val();
                               if(entrustId){
                                   extractedJiSuan3();
                               }
			        	   }
			        	   //费用改变的时候
			        	   if(field=='procedureAmt'||field=='settleServiceAmt'||field=='settleAmt'||field=='exchangeAmt'||field=='commissionAmt'||field=='stampDutyAmt'||field=='channelFee'){
			        	       var numberTwo = 0;

                               if (data.fpriceAmt) {
                                   numberTwo = data.fpriceAmt;
                                   if ($('#ps').val() == 'T01') {
                                       if ($('#tradeMarket').val()) {
                                           //佣金是否随交易支付
                                           if (data.commissionAmt && returnVal.isPayWithTrade=='Y') {
                                               numberTwo = JoyinMath.doAdd(numberTwo, data.commissionAmt, 2);
                                           }
                                           //结算费
                                           if (data.settleAmt && returnVal.settleIsPayWithTrade=='Y') {
                                               numberTwo = JoyinMath.doAdd(numberTwo, data.settleAmt, 2);
                                           }
                                           //经手费
                                           if (data.exchangeAmt && returnVal.exchangeIsPayWithTrade=='Y') {
                                               numberTwo = JoyinMath.doAdd(numberTwo, data.exchangeAmt, 2);
                                           }

                                           //印花是否随交易支付
                                           if (data.stampDutyAmt && returnVal.stampDutyIsPayWithTrade=='Y') {
                                               numberTwo = JoyinMath.doAdd(numberTwo, data.stampDutyAmt, 2);
                                           }
                                       }
                                   } else {
                                       if ($('#tradeMarket').val()) {
                                           //是否随交易支付
                                           if (data.commissionAmt && returnVal.isPayWithTrade=='Y') {
                                               numberTwo = JoyinMath.doMinus(numberTwo, data.commissionAmt, 2);
                                           }
                                           //印花是否随交易支付
                                           if (data.stampDutyAmt &&returnVal.stampDutyIsPayWithTrade=='Y') {
                                               numberTwo = JoyinMath.doMinus(numberTwo, data.stampDutyAmt, 2);
                                           }
                                           //结算费
                                           if (data.settleAmt && returnVal.settleIsPayWithTrade=='Y') {
                                               numberTwo = JoyinMath.doMinus(numberTwo, data.settleAmt, 2);
                                           }
                                           //经手费
                                           if (data.exchangeAmt && returnVal.exchangeIsPayWithTrade=='Y') {
                                               numberTwo = JoyinMath.doMinus(numberTwo, data.exchangeAmt, 2);
                                           }
                                           if (($("#trdInvestPersonInfo_counterId").val()&&$("#trdInvestPersonInfo_counterId").select2('data')[0].object=='N')
							            		 ||($("#trdInvestPersonInfo_counterId").val()&&$("#trdInvestPersonInfo_counterId").select2('data')[0].object!='N'
							            			 &&($("#ps").val() == PS_T02||$("#ps").val() == PS_T03)&&$("#chlId").val()&&$("#chlId").select2("data")[0].object.collectType==Collect_Type_01)) {
							            	 numberTwo = JoyinMath.doMinus(numberTwo, data.channelFee, 2);
							             }else if(!$("#trdInvestPersonInfo_counterId").val()&&($("#ps").val() == PS_T02||$("#ps").val() == PS_T03)&&$("#chlId").val()&&$("#chlId").select2("data")[0].object.collectType==Collect_Type_01){
											numberTwo = JoyinMath.doMinus(numberTwo, data.channelFee, 2);
							             }
                                       }
                                   }
                                   table.renderRow(data.LAY_TABLE_INDEX, {"tradeAmtAndFee": JoyinMath.doAdd(numberTwo, 0, 2)});
                               }
                           }
			           },
			           addclick:function(row){
                            row.investaim =  $("#invAim").val();
			           }
				});
			}
	  }else{
		  $('#bussTrdTransfenpei_div').hide();
	  }
	};
    $('#chlId').change(function () {
        if ($("#chlId").val()) {
            if($("#ps").val()&&$("#ps").val()==PS_T02){
                $("#channelFee_div").show();
            }else{
                $("#channelFee_div").hide();
            }
        }else{
            $("#channelFee_div").hide();
        }
        //modify by lwj 20200327 start
        if($('#chlId').val() && 'T02'==$('#ps').val()){
       	    table.showColumns([{field:'channelFee',visible: true}]);
         }
     	 //modify by lwj 20200327 end  
        if(!$(this).val()){
          var trs =  $('#bussTrdTransfenpei_div').find('.layui-table').eq(2).find('tr');
          $.each(trs,function (index,element) {
            var lengths =  $('#bussTrdTransfenpei_div').find('.layui-table').eq(2).find('tr').eq(index).find('td').length;
            if(($('#tradeMarket').val()=='03'||$('#tradeMarket').val()=='04')){
                $('#bussTrdTransfenpei_div').find('.layui-table').eq(2).find('tr').eq(index).find('td').eq(lengths-3).html('<div class="layui-table-cell laytable-cell-1-channelAvailableCash"></div>')
            }else{
                $('#bussTrdTransfenpei_div').find('.layui-table').eq(2).find('tr').eq(index).find('td').eq(lengths-2).html('<div class="layui-table-cell laytable-cell-1-channelAvailableCash"></div>')
            }
          })
        }
    });
    
    $("#channelFee").on("change", function () {
        changeTradeAmtAndFee();
    });

    /**
     * 银行间成交编号输入时从cstp查询数据带出
     */
    var fTradeIdChangeAction = function(){
        var isTrue = false;
        var fTradeId = $('#fTradeId').val();
        $('#finprodId').attr('disabled', false);
        // $('#portfolioId').attr('disabled', false);
        $('#ps').attr('disabled', false);
        $("#settleSpeed").attr('disabled', false);
        if (!$("#entrustId").val()){
        $("#tradeMarketMode").attr('disabled', false);
        }
        
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
            Joyin.ajax(basePath + 'invest/trade/bond/TrdBondInvestTrade002/querySecCstpInfoByNo', {fedealno : fTradeId,isCstpSure:isCstpSure}, function(result){
                if (result){
                    //委托验证成交编号带出来的值是否一致
                    // if($('#entrustId').val()&&$('#fTradeId').val()){
                    //     if(zcdm!=result.secid){
                    //         isTrue = true;
                    //         Joyin.warn("成交编号带出的资产代码和委托的资产代码不一致！");
                    //         return;
                    //     }
                    //     if(jyfx!=result.ps){
                    //         isTrue = true;
                    //         Joyin.warn("成交编号带出的交易方向和委托的交易方向不一致！");
                    //         return;
                    //     }
                    // }
                    var tradePlat = $('#tradePlat').val();
                    $('#tradeMarketMode').val(SHARES_TRADE_MARKET_02).trigger('change');
                    
                    var finprodId = $('#finprodId').val();
                    var finprodName2 = $('#finprodName');
                    var ps = $('#ps').val();
                    var tradePlat2 = $('#tradePlat').val();
                    //如果不走委托，cstp接口这里会校验不过去，暂时先注释掉  add by kyf  20200326
//                    if(result.secid!=finprodId||ps!=result.ps||tradePlat!=tradePlat2){
//                        $('#tradePlat').val(tradePlat);
//                        $('#settleSpeed').attr('disabled', true);
//                        $('#finprodId').attr('disabled', true);
//                        $('#tradeMarketMode').attr('disabled', true);
//                        deleteSelect('fTradeId');
//                        Joyin.error("资产代码或交易方向或交易平台与委托的不同！")
//                        return;
//                    }
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
                        deleteSelect('tradePlat');
                        deleteSelect('type9');
                        deleteSelect('type10');
                        deleteSelect('type11');
                        deleteSelect('finprodId');
                        $('#finprodId').attr('disabled', false);
                        if (!$("#entrustId").val()){
                        $('#tradeMarketMode').attr('disabled', false);
                        }
                        $('#tradePlat').attr('disabled', false);
                    }
                    //if (result.profolioId && result.profolioId != ''){
                    // $('#portfolioId').textSelect('setValue', result.profolioId);//组合代码
                    //  if($('#portfolioId').val()){
                    //      $('#portfolioId').attr('disabled', true);
                    //  }
                    // }else{
                    //     $('#bussTrdAvailableCashQuery_avalibleCash').val('');
                    //     $('#bussTrdAvailableCashQuery_avalibleCashT1').val('');
                    //     deleteSelect('portfolioId');
                    //     $('#portfolioId').attr('disabled', false);
                    //  }
//                    if (result.profolioId && result.profolioId != ''){
//                        $('#portfolioId').textSelect('setValue', result.profolioId);//组合代码
//                        if($('#portfolioId').val()){
//                            $('#portfolioId').attr('disabled', true);
//                        }
//                    }
//                     if (result.ourdepositaccountnumber && result.ourdepositaccountnumber != ''){
//                         $('#secManageAcctId').textSelect('setValue', result.ourdepositaccountnumber);//证券账户
//                         if($('#secManageAcctId').val()){
//                             $('#secManageAcctId').attr('disabled', true);
//                         }
//                     }else{
//                         deleteSelect('secManageAcctId');
//                         $('#secManageAcctId').attr('disabled', false);
//                     }
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
                        $('#tradeAmt').val(result.prinamt).trigger('change').trigger("blur");// 本金金额(券面总额)
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
                    if (result.invtype && result.invtype != ''){
                        $('#invAim').val(result.invtype).trigger('change');// 转换利息金额
                        if($('#invAim').val()){
                            $('#invAim').attr('disabled', true);
                        }
                    }else{
                        $('#invAim').val('').trigger('change');
                        $('#invAim').attr('disabled', false);
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
                        $('#settleSpeed').attr('disabled', true);
                        $("#settleSpeed").val(result.settleSpeed).trigger('change');
                        // var days = (new Date(result.settdateStr) - new Date(result.dealdateStr))/(24*60*60*1000);
                        // if (days == 0){
                        //     if($('#settleSpeed').val()){
                        //     }
                        // }else{
                        //     if($('#settleSpeed').val()){
                        //         $('#settleSpeed').attr('disabled', true);
                        //         $("#settleSpeed").val(ACCOUNT_SPEED_T1).trigger('change');
                        //     }
                        // }
                    }else{
                        deleteSelect('settleSpeed');
                        $('#settleSpeed').trigger('change');
                        $('#settleSpeed').attr('disabled', false);
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
                    tableIsClear($("#entrustId").val());
                    table.addRow(getAllRowsForEntrustId());
                    setMoney(getAllRowsForEntrustId())
                }
            })
        }else{
            var entrustId = $("#entrustId").val();
            if(!entrustId){
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
                    if (!$("#entrustId").val()){
                        $('#tradeMarketMode').prop('disabled', false);
                    }
                }
                if($('#tradePlat').val()){
                    deleteSelect('tradePlat');
                    $('#tradePlat').attr('disabled', false);
                }
                if($('#type9').val()){
                    deleteSelect('type9');
                }
                if($('#type10').val()){
                    deleteSelect('type10');
                }
                if($('#type11').val()){
                    deleteSelect('type11');
                }
                // if ($('#portfolioId').val()){
                //     $('#bussTrdAvailableCashQuery_avalibleCash').val('');
                //     $('#bussTrdAvailableCashQuery_avalibleCashT1').val('');
                //     deleteSelect('portfolioId');
                //     $('#portfolioId').attr('disabled', false);
                // }


                if ($('#invAim').val()){
                    deleteSelect('invAim')
                    $('#invAim').attr('disabled', false);
                }
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
                if ($('#settleSpeed').val()){
                    deleteSelect('settleSpeed');
                    $('#settleSpeed').trigger('change');
                    $('#settleSpeed').attr('disabled', false);
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
                tableIsClear($("#entrustId").val());
                table.addRow(getAllRowsForEntrustId());
                setMoney(getAllRowsForEntrustId())
                // getEntrustByEntrustId();
            }else{
                $('#settleSpeed').attr('disabled', true);
                $('#finprodId').attr('disabled', true);
                $('#tradeMarketMode').attr('disabled', true);
                $('#ps').attr('disabled', true);
            }
        }
    }

    var calcFeeDataByFTradeId = function () {
        var paramObj = $.serializeToObject('#submitForm', true);
        paramObj.calculateField = $("[name=calculateField]:checked").val();
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

        Joyin.ajaxJson(JoyinUtil.getRealPath('/invest/trade/bond/TrdBondInvestTrade002/calculateFeeData'), paramObj, function (result) {
            if (result && result.data) {
                var data = result.data;
                if(data.tradeFeeList&&data.tradeFeeList.length>0){
                    $.each(data.tradeFeeList,function(i,v){
                        if(v.feeType==FEE_TYPE_05&&v.feeAmt){
                            commissionAmt = v.feeAmt;
                            $("#commissionAmtRate").val(v.feeRate).trigger("blur");
                            var feeAmt = v.feeAmt;
                            var tradeMarket = $('#tradeMarket').val();
                            if (tradeMarket == TRADE_PLACE_02 || tradeMarket == TRADE_PLACE_03) {
                                var tradeMarketMode = $("#tradeMarketMode").val();
                                if (tradeMarketMode == SHARES_TRADE_MARKET_01) {
                                    $("#commissionAmt").val(0).prop('disabled', false).trigger("change");
                                    feeAmt = 0;
                                }
                            }
                            if (feeAmt) {
                                //$("#commissionAmt").val(v.feeAmt).prop('disabled', false).trigger("blur").trigger("change");
                            }

                            if($("#ps").val()==PS_T01){
                                $("#tradeRepAmt").val(Number(data.fpriceAmt)+Number(feeAmt)).trigger("blur");
                            }
                            if($("#ps").val()==PS_T02){
                                $("#tradeRepAmt").val(Number(data.fpriceAmt)-Number(feeAmt)).trigger("blur");
                            }
                        }
                        if(v.feeType==FEE_TYPE_02&&v.feeAmt){
                            stampDutyAmt = v.feeAmt;
                            var feeAmt = v.feeAmt;
                            var tradeMarket = $('#tradeMarket').val();
                            if (tradeMarket == TRADE_PLACE_02 || tradeMarket == TRADE_PLACE_03) {
                                var tradeMarketMode = $("#tradeMarketMode").val();
                                if (tradeMarketMode == SHARES_TRADE_MARKET_01) {
                                    $("#stampDutyAmt").val(0).prop('disabled', false).trigger("change");
                                    feeAmt = 0;
                                }
                            }
                            if (feeAmt) {
                               // $("#stampDutyAmt").val(v.feeAmt).prop('disabled', false).trigger("blur").trigger("change");
                            }

                        }
                        if(v.feeType==FEE_TYPE_06&&v.feeAmt){
                           // $("#procedureAmt").val(v.feeAmt).trigger("blur");
                          //  $("#procedureAmtRate").val(v.feeRate).trigger("blur");
                        }
                        if(v.feeType==FEE_TYPE_JSSXF&&v.feeAmt){
                            //$("#settleAmt").val(v.feeAmt).trigger("blur");
                        }
                        /*if(v.feeType==FEE_TYPE_04&&v.feeAmt){
                            $("#transferAmt").val(v.feeAmt).trigger("blur");
                        }*/
                    });
                }
                else {
                    $("#commissionAmt").val("").trigger("change").trigger("blur");
                    $("#stampDutyAmt").val("").trigger("change").trigger("blur");
                    $("#procedureAmt").val("");
                    $("#settleServiceAmt").val("");
                    $("#settleAmt").val("");
                    $("#exchangeAmt").val("");
                    $("#procedureAmtRate").val("");
                    $("#commissionAmtRate").val("");
                    $("#transferAmt").val("");
                }
            }
        });
    };

    // var deleteSelect = function (id) {
    //     $('#'+id).textSelect('setValue','');
    //     if($('#'+id).find('option').length>0){
    //         $('#'+id).find('option').remove();
    //     }
    // }
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
    if(IS_SHOW_TYPE == '01'){
    	$('#type11Div').show();
    }else{
    	$('#type11Div').hide();
    }
    var tableIsClear = function (entrustId) {
        if (!entrustId){
            table.clear();
        }
    }
}());
