(function () {
    var returnVal={
        isPayWithTrade:'Y',
        tranFeeAmt:0,
        settlementFee:0,
        commission:0
    };
    // 委托指令dto
    var etrEntrust;
    // 委托指令附属表dto
    var etrEntrustAdd;
    var fTradeIdFromCstp = fTradeId;
    // 委托金额
    var entrustAmt;
    // 委托的回购利率
    var entrustPrice;
    // 委托的回购起息日
    var entrustVdate;
    // 委托的回购到期日
    var entrustMdate;
    // 委托的回购期限From
    var entrustTermFrom;
    // 委托的回购期限To
    var entrustTermTo;
    // 委托的期限品种
    var entrustTermType;
    //多委托存储位置
    var entrustList;
    var entrustSurAmtAll;
    var isPs04=false;
    var isIntVal=true;
    // add by wangguoxian 费用设置 20190807 start
    var isPayWithTrade = $("#isPayWithTrade").val()?$("#isPayWithTrade").val():YES_OR_NO_Y;//佣金是否随交易支付，默认为是
    $("#isPayWithTrade").val(isPayWithTrade);
    // add by wangguoxian 费用设置 20190807 end
    var getriskdata = function (i) {
        var mun=null;
        var object = $.serializeToObject('#submitForm', true);
        var BussRepoSecDto=null;
        if(object!=null&&object!=""){
            BussRepoSecDto=object.bussRepoSecPtl.pledgeBondList;
        }
        var list=[];
        // add by xkni 当前界面风险试算时什么都不选择直接点风险试算按钮所需默认值 20190912 end
        var obj1 = {
            timePoint: MY_SUP_TIME_TYPE_02,
            valDate: $('#tradeDate').val(),
           // portfolioCode: getriskPara3[i].portfolioId,
            tradeAmount: $('#prinAmt').val(),
            assetType: getFinProdType2FromPs(),
            tradeDirection:$('#ps').val(),
            isEnabled: '01'
        }
        for(var i in BussRepoSecDto){
            if(BussRepoSecDto[i].prinAmt!=null&&BussRepoSecDto[i].prinAmt!=" "&&BussRepoSecDto[i].prinAmt>0){
                var obj = {
                        timePoint: MY_SUP_TIME_TYPE_02,
                        valDate: $('#tradeDate').val(),
                        portfolioCode: BussRepoSecDto[i].portfolioId,
                        tradeAmount: BussRepoSecDto[i].prinAmt,
                        assetType: getFinProdType2FromPs(),
                        tradeDirection:$('#ps').val(),
                        isEnabled: '01',
                        index: null,
                        mun: i
                    };
                    //修改obj -> object
                    if($('#ps').val() == BACK_LEND_DIRECTION_03){
                        obj.assetType = FINPROD_TYPE_F02;
                    }else if($('#ps').val() == BACK_LEND_DIRECTION_04){
                        obj.assetType = FINPROD_TYPE_F03;
                    }else{
                        obj.assetType = null;
                    }
                    list[i]=obj;
                }

        }
        var object = {
            valDate: ['tradeDate', 'TrdRepoTrade002.tradeDate'],
            portfolioCode: ['portfolioId', 'TrdRepoTrade002.portfolioId'],
            tradeAmount: ['prinAmt', 'TrdRepoTrade002.prinAmt'],
            assetType: ['ps', 'TrdFundInvestTrade001.finprodId'],
            tradeDirection:['ps', 'TrdRepoTrade002.ps'],
            data: list.length>0?list:obj1
        };
        return object;
    };


    function saveOrSubmit(operateType) {
        if (!$("#prinAmt").val() || $("#prinAmt").val()*1 <= 0) {
            Joyin.error("本金金额必须大于0")
            return;
        }
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
        debugger;
        var s;
        var maxTradeamt = 0;
        if(BaseRiskMathComm) {
            s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
            if(!s && s != ''){
                return;
            }
            if (window.BaseRiskMathComm.isCalcConditionChanged(getriskdata())){
                return;
            }
        }
        var isTrue = window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged();
        // 检查生成划款指令参数是否发生变化
        if (isTrue) {
            return;
        }

        if($('#fTradeId').val()&&$('#prinAmt').val()&&(pageModel==PAGE_MODEL_NEW||pageModel==PAGE_MODEL_EDIT)){
            var isTrue = false;
            var params = {};
            params.fTradeId = $('#fTradeId').val();
            params.tradeAmt = $('#prinAmt').val();
            params.tradeType = 'PLEDGE';
            params.workflowId = $('#workflowId').val();
            params.ps = $('#ps').val();
            Joyin.ajaxJson(JoyinUtil.getRealPath('/invest/trade/trdrepotrade/TrdRepoTrade002/isAllTradeAmtByFTradeId'), params, function (result) {
                if(result&&!result.data){
                    isTrue = true;
                    Joyin.error("成交编号获得的劵面总额不得大于已存在的劵面总额！");
                }
            },false);

            if(isTrue){
                return;
            }
        }
        //检查组合分配的和金额信息是否一致
        if($('#prinAmt').val()){
            var number = 0;
            for(var i=0;i<window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows().length;i++){
                if(window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows()[i].prinAmt){
                    number =  JoyinMath.doAdd(number,window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows()[i].prinAmt,2);
                }
                if (parseFloat(window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows()[i].prinAmt) > parseFloat(maxTradeamt)){
					maxTradeamt = window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows()[i].prinAmt;
				}
            }
            if(JoyinMath.doAdd($('#prinAmt').val(),0,2)!=number){
                Joyin.warn("组合分配本金总额和金额信息本金总额不一致！");
                return;
            }
        }

        //校验本金金额不能大于剩余委托额度
        var entrustId = $("#entrustId").val();
        if(entrustId){
            for(var i=0;i<window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows().length;i++){
                var item = window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows()[i];
                if(item.prinAmt && item.entrustSurAmt && Number(item.prinAmt)>Number(item.entrustSurAmt)){
                    Joyin.warn("本金金额不能大于剩余委托额度！");
                    return;
                }
            }
        }

        //如果是交易所逆回购，校验交易金额不能大于交易所资金账户可用现金 start
        if(($('#tradeMarket').val()=='03'||$('#tradeMarket').val()=='04') && $('#ps').val()=='04'){
            for(var i=0;i<window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows().length;i++){
                var item = window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows()[i];
                var amt;
                if(item.trdAcctAbleCash == undefined || item.trdAcctAbleCash == null){
                    amt = 0;
                }else{
                    amt = item.trdAcctAbleCash;
                }
                if(item.tradeAmt && Number(item.tradeAmt)>Number(amt)){
                    Joyin.warn("交易金额不能大于交易所资金账户可用现金，需要进行银证转账！");
                    return;
                }
            }
        }
        //如果是交易所逆回购，校验交易金额不能大于交易所资金账户可用现金 end

        //检查组合分配的和金额信息是否一致
        if($('#rebackIntAmt').val()){
            var number = 0;
            for(var i=0;i<window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows().length;i++){
                if(window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows()[i].rebackIntAmt){
                    number =  JoyinMath.doAdd(number,window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows()[i].rebackIntAmt,2);
                }
            }
            if(JoyinMath.doAdd($('#rebackIntAmt').val(),0,2)!=number){
                Joyin.warn("组合分配回购利息和金额信息回购利息不一致！");
                return;
            }
        }
        if($('#mdateSettleAmt').val()){
            var number = 0;
            for(var i=0;i<window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows().length;i++){
                if(window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows()[i].mdateSettleAmt){
                    number =  JoyinMath.doAdd(number,window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows()[i].mdateSettleAmt,2);
                }
            }
            if(JoyinMath.doAdd($('#mdateSettleAmt').val(),0,2)!=number){
                Joyin.warn("组合分配到期结算金额和金额信息到期结算金额不一致！");
                return;
            }
        }
        if($('#tradeAmtAndFee').val()&&($('#tradeMarket').val()=='03'||$('#tradeMarket').val()=='04')){
            var number = 0;
            for(var i=0;i<window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows().length;i++){
                if(window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows()[i].tradeAmt){
                    number =  JoyinMath.doAdd(number,window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows()[i].tradeAmt,2);
                }
            }
            if(JoyinMath.doAdd($('#tradeAmtAndFee').val(),0,2)!=number){
                Joyin.warn("组合分配交易金额和金额信息交易金额不一致！");
                return;
            }
        }
        if($('#procedureAmt').val()&&$('#tradeMarket').val()=='01'){
            var number = 0;
            for(var i=0;i<window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows().length;i++){
                if(window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows()[i].tranFeeAmt){
                    number =  JoyinMath.doAdd(number,window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows()[i].tranFeeAmt,2);
                }
            }
            if(JoyinMath.doAdd($('#procedureAmt').val(),0,2)!=number){
                Joyin.warn("组合分配交易手续费和金额信息交易手续费不一致！");
                return;
            }
        }

        if($('#settleAmt').val()&&$('#tradeMarket').val()=='01'){
            var number = 0;
            for(var i=0;i<window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows().length;i++){
                if(window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows()[i].settlementFee){
                    number =  JoyinMath.doAdd(number,window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows()[i].settlementFee,2);
                }
            }
            if(JoyinMath.doAdd($('#settleAmt').val(),0,2)!=number){
                Joyin.warn("组合分配结算费和金额信息结算费不一致！");
                return;
            }
        }
        if($('#commissionAmt').val()&&($('#tradeMarket').val()=='03'||$('#tradeMarket').val()=='04')){
            var number = 0;
            for(var i=0;i<window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows().length;i++){
                if(window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows()[i].commission){
                    number =  JoyinMath.doAdd(number,window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows()[i].commission,2);
                }
            }
            if(JoyinMath.doAdd($('#commissionAmt').val(),0,2)!=number){
                Joyin.warn("组合分配佣金和金额信息佣金不一致！");
                return;
            }
        }
        //回购类型！=质押式竞价回购

        if($('#ps').val()=='03'&&$("#repoType").val()!=TARGET_BACK_TYPE_H02){
            var length = window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTblDetailed.getAllRows().length;
            if(length==0){
                Joyin.warn("正回购请添加质押劵信息！");
                return;
            }
        }

        //银行间+逆回购交易 判断质押券汇总信息是否有数据，如果无会导致抵押券丢失 20200711 start
        if($('#tradeMarket').val()=='01' && $('#ps').val()=='04'){
            var length = window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTblDetailed.getAllRows().length;
            if(length==0){
                Joyin.warn("请点击自动分配，添加质押劵信息！");
                return;
            }
        }
        //银行间+逆回购交易 判断质押券汇总信息是否有数据，如果无会导致抵押券丢失 20200711 end

        var object = $.serializeToObject('#submitForm', true);
        object.pageModel = pageModel;
        object.operateType = operateType;
//        object.isGuarantee = $('#portfolioId').select2('data')[0].object; // 是否保本
        if (FileCommon) {
            object.uploadFileList = FileCommon.getUploadFileInfo();
        }
        // 委托金额
        object.entrustAmt = entrustAmt;
        // 委托的回购利率
        object.entrustPrice = entrustPrice;
        // 委托的回购起息日
        object.entrustVdate = entrustVdate;
        // 委托的回购到期日
        object.entrustMdate = entrustMdate;
        // 委托的回购期限From
        object.entrustTermFrom = entrustTermFrom;
        // 委托的回购期限To
        object.entrustTermTo = entrustTermTo;
        // 委托的期限品种
        object.entrustTermType = entrustTermType;
        object.zhilingtype='02';//多组合
        // 资产可用持仓
        object.assetPosition = $("#bussTrdAvailableCashQuery_assetPosition").val();
        
        if ($('#trdInvestPersonInfo_counterId').select2('data') && $('#trdInvestPersonInfo_counterId').select2('data')[0]) {
            object.counterType = $('#trdInvestPersonInfo_counterId').select2('data')[0].object;
            object.counterId = $('#trdInvestPersonInfo_counterId').val();
        }
        object.maxTradeamt = maxTradeamt;
        object.finprodType2 = getFinProdType2FromPs();
        object.inputValue1 = 'F07';
        object.inputValue2 = $('#repoType').val();
        Joyin.ajaxJson(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade003/check', object, function (result) {
        	Joyin.ajaxJson(basePath + 'invest/trade/TrdCommonController/check', object, function(result1) {
	            Joyin.confirm(result.data, function () {
	                if (operateType === 'save') {
	                    WorkFlow.doSaveList(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade003/save', object, "TrdRepoTrade002_btn_save","TrdRepoTrade002");
	                } else {
	                    WorkFlow.doSubmitList(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade003/save', object, "TrdRepoTrade002_btn_submit","TrdRepoTrade002",null, function(result){
						      window.BaseRiskMathComm.reloadRiskTable(result);
	 				    });
	                }
	            });
        	});
        },false);
    }
    var checkAndAssign = function (el, value, editable) {
        if (value != null && value != '') {
            $(el).val(value).trigger('change').trigger('blur');
            if (!editable) {
                $(el).attr("disabled", true);
            }
        }
    };
    var checkNotEmpty = function () {
        for (var i = 0; i < arguments.length; i++) {
            if (arguments[i] == null || arguments[i] == '') {
                return false;
            }
        }
        return true;
    };

    /**
     * 交易场所、回购类型、交易方向改变时
     */
    var repoTypeOnChange = function (isInit) {
        var repoType = $("#repoType").val();
        //深交所 协议 期限品种非必填
        if($("#tradeMarket").val()==TRADE_PLACE_03&&repoType == TARGET_BACK_TYPE_H01){
            $('#termTypeRebackDiv').parent().find('.must').hide();
            $('#termTypeReback').removeAttr("required");
            $("#termTypeReback").attr("disabled", true);
            $("#termTypeReback").val('');
        }else{
            $('#termTypeRebackDiv').parent().find('.must').show();
            $("#termTypeReback").attr("required",true);
            $("#termTypeReback").prop("disabled", false);
        }
        if (repoType == TARGET_BACK_TYPE_H02) {
        	$("#term").prop("disabled", true);
        }else{
        	$("#term").prop("disabled", false);
        }
        
        // var ps = $("#ps").val();
        // 1) 当回购类型为质押式协议回购时，期限品种为文本框，期限品种和回购期限均由业务人员手动输入；
        if (repoType == TARGET_BACK_TYPE_H01) {
            if (!isInit) {
                $("#termTypeReback").val("");
            }
            $('#termTypeReback').prop('disabled', true);
            $("#term").prop("disabled", false);
            if (etrEntrust && !etrEntrust.chlId) { // 待办事项不是委托，通道会清空，所以判断是：在委托没有通道的情况下
                $("#chlId").val('')
            }
            $("#chlId").trigger('change');
            if ($("#secAcctId").val()) {
                $("#chlId").prop("disabled", true);
            }
            else {
                $("#chlId").prop("disabled", false);
            }
        } else if (repoType == TARGET_BACK_TYPE_H02)
        // 买断式回购从回购资产交易画面移除了，本处不作判断
        // 2) 当回购类型为质押式竞价回购和买断式回购时，期限品种为文本框，文本框支持输入联想，期限品种枚举值为：
        {
            if (!isInit) {
                $("#termTypeReback").val("");
                $("#term").val("").trigger("change");
            }
            // $("#term").prop("disabled", true);
            $('#termTypeReback').prop('disabled', false);
            $("#termTypeReback").bind("change", function () {
                var selectedData = $('#termTypeReback').data("selected");
                if (selectedData) {
                    $("#term").val(selectedData["object"]).trigger("change");
                }
            });
            $("#chlId").val('').prop("disabled", true).trigger('change');
        }
        // 3) 当回购类型为质押式竞价回购，页面无质押券信息，可使用标准券额度显示，（此处需求有【交易方向为正回购时】的限制，经确认不需要）
        if (repoType == TARGET_BACK_TYPE_H02) {
            if (window && window.BussRepoSecPtl && window.BussRepoSecPtl["bussRepoSecPtl"]) {
                // 质押券信息清除并隐藏
                $("#Ps04Repo").hide();
                window.BussRepoSecPtl["bussRepoSecPtl"].clearHideRepo();
                if(window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.table){
                    window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{field:'addPledgeInfo',visible: false}]);
                    window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.clear();
                }
            }
            $("#avaStandardStockPositionDiv").show();
            refreshAvaStandardStockPosition();
        } else {
            if (window && window.BussRepoSecPtl && window.BussRepoSecPtl["bussRepoSecPtl"]) {
                // 质押券信息恢复显示
                if($("#ps").val()=='04'){
                    $("#Ps04Repo").show();
                }
                
                window.BussRepoSecPtl["bussRepoSecPtl"].showRepo();
                if(window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.table){
                    window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{field:'addPledgeInfo',visible: true}]);
                    window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.clear();
                }
            }
            if (!isInit) {
                $("#avaStandardStockPosition").val("");
            }
            $("#avaStandardStockPositionDiv").hide();
        }
        if (pageModel != PAGE_MODEL_REFERENCE) {//查看页面
            $('#termTypeReback').tradeRepTypeChange({
                termType: 'termTypeReback',
                tradeMarket: $(tradeMarket).val(),
                tradeP02: TRADE_PLACE_02,
                tradeP03: TRADE_PLACE_03,
                tradeP01: TRADE_PLACE_01,
                repoType: $('#repoType').val(),
                repoTypeH02: TARGET_BACK_TYPE_H02,
                repoTypeH01: TARGET_BACK_TYPE_H01
            });
        }
    };

    /**
     * 交易场所、回购类型、交易方向改变时
     */
    var tradeMarketOnChange = function (isInit) {
        var tradeMarket = $("#tradeMarket").val();
        //深圳证券交易所:回购类型为质押式竞价回购  add by start 
        var $repoType = $('#repoType');
        if(tradeMarket == TRADE_PLACE_03){
            $repoType.updateChosenArr({
                item: [TARGET_BACK_TYPE_H02],
                chosen: TARGET_BACK_TYPE_H02,
                disabled: false
            });
        }
        //深圳证券交易所:回购类型为质押式竞价回购  add by end
        //如果是质押式协议回购则隐藏期限品种
        if ($('#repoType').val() && $('#repoType').val()== TARGET_BACK_TYPE_H01) {
            $('#termTypeReback').prop('disabled', true);
        }else {
            $('#termTypeReback').prop('disabled', false);
        }
        // 上海证券交易所、深圳证券交易所时
        if (tradeMarket == TRADE_PLACE_02 || tradeMarket == TRADE_PLACE_03) {
            // 4) 清算速度只能为T+1，资金可用日显示；
            $("#actCapDays").prop("disabled", true);
            $("#settleSpeedStr").val(ACCOUNT_SPEED_02).trigger("change").prop("disabled", true);
            $("#avaFundsDateDiv").show();
            $("#vpayDateDiv").show();
            $("#mpayDateDiv").show();
            $('#tradeAmtAndFeeDiv').show();
            calcAvaFundsDate();
            if (!isInit) {
                $("#fTradeId").val("").trigger("change", isInit);
            }
            if(!$("#fTradeId").val()){
                $("#fTradeId").prop("disabled", false);
            }
            // add by wangguoxian 费用设置 20190719 start
            $("#procedureAmtDiv").hide();
            $("#settleAmtDiv").hide();
            $("#commissionAmtDiv").show();
            if(window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.table){
                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{field:'tranFeeAmt',visible: false}]);
                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{field:'settlementFee',visible: false}]);//false就是不显示
                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{field:'tradeAmt',visible: true}]);
                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{field:'commission',visible: true}]);
                if($('#ps').val()=='04'){
                    window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{field:'trdAcctAbleCash',visible: true}]);
                }
                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.clear();
            }
            // add by wangguoxian 费用设置 20190719 end
            
            if($('#ps').val()=='04'){
                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{field:'trdAcctAbleCash',visible: true}]); 
            }else{
                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{field:'trdAcctAbleCash',visible: false}]); 
            }
        }
        // 当交易场所是银行间时
        if (tradeMarket == TRADE_PLACE_01) {
            // 2) 清算速度默认为T+0，资金可用日隐藏；
            $("#actCapDays").prop("disabled", true);
            $("#settleSpeedStr").val(ACCOUNT_SPEED_01).trigger("change").prop("disabled", false);
            if (!isInit) {
                $("#avaFundsDate").val("");
            }
            $("#avaFundsDateDiv").hide();
            $("#vpayDateDiv").hide();
            $("#mpayDateDiv").hide();
            $('#tradeAmtAndFeeDiv').hide();

            // 3) 可以输入银行间成交编号，相关交易要素根据成交编号自动带出（取银行间下行数据）；
            if(!$("#fTradeId").val()){
                $("#fTradeId").prop("disabled", false);
            }
            // add by wangguoxian 费用设置 20190719 start
            $("#procedureAmtDiv").show();
            $("#settleAmtDiv").show();
            $("#commissionAmtDiv").hide();
            if(window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.table){
                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{field:'tranFeeAmt',visible: true}]);
                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{field:'settlementFee',visible: true}]);
                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{field:'tradeAmt',visible: false}]);
                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{field:'commission',visible: false}]);
                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{field:'trdAcctAbleCash',visible: false}]);
                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.clear();
            }
            // add by wangguoxian 费用设置 20190719 end
            //add by nixiukuan 当交易场所选择银行间时候隐藏回购类型 20191025 start
            $("#repoTypeDiv").hide();
            //add by nixiukuan 当交易场所选择 20191025 end
            
            window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{field:'trdAcctAbleCash',visible: false}]);
        }
        else
        {
            //add by nixiukuan 当交易场所选择银行间时候隐藏回购类型 20191025 start
            $("#repoTypeDiv").show();
            //add by nixiukuan 当交易场所选择 20191025 end
        }
        if (pageModel != PAGE_MODEL_REFERENCE) {
            $('#termTypeReback').tradeRepTypeChange({
                termType: 'termTypeReback',
                tradeMarket: tradeMarket,
                tradeP02: TRADE_PLACE_02,
                tradeP03: TRADE_PLACE_03,
                tradeP01: TRADE_PLACE_01,
                repoType: $('#repoType').val(),
                repoTypeH02: TARGET_BACK_TYPE_H02,
                repoTypeH01: TARGET_BACK_TYPE_H01
            });
        }
        // add by wangguoxian 费用设置 20190719 start
        if(!tradeMarket){
            $("#procedureAmtDiv").hide();
            $("#settleAmtDiv").hide();
            $("#commissionAmtDiv").hide(); 
        }
        // add by wangguoxian 费用设置 20190719 end
    };

    //回购类型枚举值变化单独方法 add by wangwei5 start
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
    //回购类型枚举值变化单独方法 add by wangwei5 end
    /**
     * 交易方向改变时
     */
    /*var psOnChange = function (isInit) {
        var ps = $("#ps").val();
        // 当交易方向是逆回购时
        if (ps == BACK_LEND_DIRECTION_04) {
            // 组合可用现金、托管户剩余现金显示，自动带出，不可修改，允许透支，计算规则见通用规则
            window.BussTrdAvailableCashQuery["bussTrdAvailableCashQuery"].showCash();
            refreshCashAndBalance();
        } else
        // 当交易方向为正回购时
        if (ps == BACK_LEND_DIRECTION_03) {
            // 组合可用现金隐藏
            window.BussTrdAvailableCashQuery["bussTrdAvailableCashQuery"].hideCash();
        }
    };*/

    /**
     * 起息日、到期日改变时，自动计算回购期限
     */
    var vmdateOnChangeCalcTerm = function () {
        var vdate = $("#vdate").val();
        var mdate = $("#mdate").val();
        var calendarId = $('#calendarId').val();
        var fTradeId =  $('#fTradeId').val();
        /*if (vdate && mdate) {
            var days = (Date.ofDate(mdate) - Date.ofDate(vdate)) / (1000 * 60 * 60 * 24);
            $("#term").val(days);
        }*/
        if (vdate  && calendarId&&!fTradeId) {
            var object={
                    vdate:vdate,
                    term:$("#term").val(),
                    calendarId:calendarId
            };
            Joyin.ajaxSync(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade003/getMdate',object,
                    function (data) {
                        if (data && data.tradeDate && data.tradeDate) {
                        	$("#mdate").val(data.tradeDate).trigger('change');
                            vmdateOnChangeCalcDate($('#mdate').val(),$('#mpayDate'));
                            if($('#tradeMarket').val() == TRADE_PLACE_01){
                                if(data.cashholdingdays){
                                    $('#actCapDays').val(data.cashholdingdays).trigger('blur').trigger('change');
                                }
                            }
                        }
           });
        }
    };

    /**
     * 本金金额、回购利率、回购期限、计息基础改变时，刷新回购利息
     */
    var refreshRebackIntAmt = function (varTerm) {
        var prinAmt = $("#prinAmt").val();
        var rebackPrice = $("#rebackPrice").val();
        var actCapDays = $("#actCapDays").val();
        var basis = $("#basis").val();
        if (prinAmt && rebackPrice && actCapDays && basis) {
            var number = JoyinMath.doMul(prinAmt,rebackPrice,8);
            var num =  JoyinMath.doMul(JoyinMath.doDiv(number,100,8),actCapDays,8);
            var result = JoyinMath.doDiv(num,basis == BASIS_A_360 ? 360 : 365,2);
            $("#rebackIntAmt").val(result).trigger("change").trigger("blur");
        }
//        if (varTerm == 'term') {
//            var re = /^[0-9]*$/;
//            if(term && re.test(term)){
//                var vdate = $('#vdate').val();
//                if(vdate){
//                    var d1 = new Date(vdate).getTime();
//                    var day = parseInt(term*24*60*60*1000);
//                    var mdate = new Date(d1+day);
//                    $('#mdate').val(mdate.format("yyyy-MM-dd"));
//                }
//            }
//        }
    };

    /**
     * 组合代码、证券托管户、交易日、到期日改变时，刷新可使用标准券额度
     */
    var refreshAvaStandardStockPosition = function () {
        if ($("#avaStandardStockPositionDiv").is(":visible")
            && $("#portfolioId").val()
            && $("#secAcctId").val()
            && $("#tradeDate").val()
            && $("#mdate").val()) {
            var params = {};
            params.portfolioId = $("#portfolioId").val();
            params.secAcctId = $("#secAcctId").val();
            params.tradeDate = $("#tradeDate").val();
            params.mdate = $("#mdate").val();
            $("#avaStandardStockPosition").val('');
            Joyin.ajaxSync(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade003/getAvaStandardStockPosition',
                $.serializeToObject('#submitForm', true),
                function (data) {
                    if (data) {
                        checkAndAssign("#avaStandardStockPosition", data);
                    }
                    else {
                        $("#avaStandardStockPosition").val('');
                    }
                });
        }
    };

    /**
     * 本金金额、回购利息改变时
     */
    var prinAmtRebackIntAmtOnChange = function () {
        var prinAmt = $("#prinAmt").val();
        var rebackIntAmt = $("#rebackIntAmt").val();
        if (prinAmt && rebackIntAmt) {
            $("#mdateSettleAmt").val((Number(prinAmt) + Number(rebackIntAmt)).toFixed(2)).trigger("blur");
        }
    };

    /**
     * 刷新托管户剩余现金、组合T日可用现金、组合T+1日可用现金
     */
    var refreshCashAndBalance = function () {
        var params = {};
        params.portfolioId = $("#portfolioId").val();
        params.calendarId = $("#calendarId").val();
        params.ccy = $("#ccy").val();
        // 通道交易 新增 强成西 2019/03/26 start
        params.chlId = $("#chlId").val();
        // 通道交易 新增 强成西 2019/03/26 end
        params.tradeDate = $("#tradeDate").val();
        if (window.BussTrdAvailableCashQuery && window.BussTrdAvailableCashQuery["bussTrdAvailableCashQuery"]) {
            window.BussTrdAvailableCashQuery["bussTrdAvailableCashQuery"].updateCash(function () {
                return params
            });
            params.acctType = ACCT_TYPE_A01;
            window.BussTrdAvailableCashQuery["bussTrdAvailableCashQuery"].updateSecAcctBalance(function () {
                return params
            });
        }
    };


// <!-- 通道交易 新增 强成西 2019/03/26 start -->
    $("#channelFee").on("change", function () {
        changeTradeAmtAndFee();
    });

    $("#prinAmt").on("change", function () {
        // add by wangguoxian 费用设置 20190807 start
        calFee();
        // add by wangguoxian 费用设置 20190807 end
        changeTradeAmtAndFee();
        // window.BussRepoSecPtl['bussRepoSecPtl'].extractedCpriceAmt();
        // window.BussRepoSecPtl['bussRepoSecPtl'].extractedJiSuan();
    });

    // modified by wanguoxian 费用设置 20190807 start
    var changeTradeAmtAndFee = function () {
        if (BACK_LEND_DIRECTION_04 == $("#ps").val()) {
            $("#tradeAmtAndFee").val(JoyinMath.doAdd($("#prinAmt").val(), $("#channelFee").val(), 2)).trigger('blur');
            if (isPayWithTrade == YES_OR_NO_Y) {
                $("#tradeAmtAndFee").val(JoyinMath.doAdd($("#tradeAmtAndFee").val(), $("#commissionAmt").val(), 2)).trigger('blur');
            }
        }
        else {
            $("#tradeAmtAndFee").val(JoyinMath.doMinus($("#prinAmt").val(), $("#channelFee").val(), 2)).trigger('blur');
            if (isPayWithTrade == YES_OR_NO_Y) {
                $("#tradeAmtAndFee").val(JoyinMath.doMinus($("#tradeAmtAndFee").val(), $("#commissionAmt").val(), 2)).trigger('blur');
            }
        }
    };
    // modified by wanguoxian 费用设置 20190807 start
    // <!-- 通道交易 新增 强成西 2019/03/26 end -->

    // add by wangguoxian 费用设置 20190719 start

    /**
     * 计算费用
     */
    var calFee = function(){
        if(!$("#tradeDate").val()){
            return;
        }
        /*if(!$("#secAcctId").val()){
            return;
        }*/
        if(!$("#ps").val()){
            return;
        }
        if(!$("#prinAmt").val()){
            return;
        }
        if($("#tradeMarket").val() == TRADE_PLACE_01 && !$("#termTypeReback").val()){
            return;
        }
        if (pageModel == PAGE_MODEL_REFERENCE){
            // 审批人审批或者生效的交易不再重新计算
            return;
        }
        $("#commissionAmt").val('').trigger("blur");
        $("#procedureAmt").val('').trigger("blur");
        $("#settleAmt").val('').trigger("blur");
        var object = $.serializeToObject('#submitForm', true);
        Joyin.ajaxJson(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade003/calFee', object, function (result) {
            if (result) {
                var tradeFeeList = result.tradeFeeList;
                if(tradeFeeList && tradeFeeList.length>0){
                    // 交易手续费条数
                    var cntP = 0;
                    // 结算费条数
                    var cntS = 0;
                    // 佣金条数
                    var cntC = 0;
                    $.each(tradeFeeList,function(i,v){
                        // 佣金设置
                        if(v.feeType==FEE_TYPE_05&&v.feeAmt){
                            cntC++;
                            isPayWithTrade = v.isPayWithTrade;
                            $("#isPayWithTrade").val(isPayWithTrade);
                            $("#commissionAmt").val(v.feeAmt).trigger("blur");
                            // 交易方向为逆回购并且随交易支付，交易金额 = 本金金额 + 随交易支付的费用
                            changeTradeAmtAndFee();
                        }
                        if(v.feeType==FEE_TYPE_06){
                            cntP++;
                            if (v.feeAmt) {
                                $("#procedureAmt").val(v.feeAmt).trigger("blur");
                            }
                            else {
                                $("#procedureAmt").val(0).trigger("blur");
                            }
                            // $("#tradeAmtAndFee").val($("#prinAmt").val()).trigger("blur");
                        }
                        else if(v.feeType==FEE_TYPE_08){
                            cntS++;
                            if (v.feeAmt) {
                                $("#settleAmt").val(v.feeAmt).trigger("blur");
                            }
                            else {
                                $("#settleAmt").val(0).trigger("blur");
                            }
                            // $("#tradeAmtAndFee").val($("#prinAmt").val()).trigger("blur");
                        }


                    });
                    // 按照最新费用规则计算相关费用无结果则清空相关费用
                    if(cntP == 0){
                        $("#procedureAmt").val("").trigger("blur");
                    }
                    if(cntS == 0){
                        $("#settleAmt").val("").trigger("blur");
                    }
                    if(cntC == 0){
                        $("#commissionAmt").val("").trigger("blur");
                    }
                }else{
                    $("#commissionAmt").val('').trigger("blur");
                    $("#procedureAmt").val('').trigger("blur");
                    $("#settleAmt").val('').trigger("blur");

                }
            }
        },false);
    };
    // add by wangguoxian 费用设置 20190719 end

    /**
     * 计算资金可用日
     */
    var calcAvaFundsDate = function () {
        var settleSpeedStr = $("#settleSpeedStr").val();
        if (settleSpeedStr == ACCOUNT_SPEED_02 && $("#calendarId").val() && $("#tradeDate").val()) {
            Joyin.ajaxSync(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade003/getNextWorkDay', {
                'calendarId': $("#calendarId").val(),
                'tradeDate': $("#tradeDate").val()
            }, function (date) {
                $("#avaFundsDate").val(date).trigger("change");
            });
        }
    };

    /**
     * 设置添加质押券共通参数
     */
    var setBussRepoSecInput = function () {
        var data = {
            portfolioId: $('#portfolioId').val(),
            ps: $("#ps").val(),
            secAcctId: $('#secAcctId').val(),
            repoType: $('#repoType').val(),
            chlId: $('#chlId').val(),
            term: $("#term").val(),
            basis: $("#basis").val(),
            channelFee: $("#channelFee").val(),
            prinAmt: $("#prinAmt").val(),
            rebackPrice: $("#rebackPrice").val(),
            rebackIntAmt: $("#rebackIntAmt").val(),
            mdateSettleAmt: $("#mdateSettleAmt").val(),
            channelFee: $("#channelFee").val(),
            tradeAmtAndFee: $("#tradeAmtAndFee").val(),
            procedureAmt: $("#procedureAmt").val(),
            settleAmt: $("#settleAmt").val(),
            commissionAmt: $("#commissionAmt").val(),
            avalibleCash: $("#bussTrdAvailableCashQuery_avalibleCash").val(),
            avalibleCashT1: $("#bussTrdAvailableCashQuery_avalibleCashT1").val(),
            calendarId: $("#calendarId").val(),
            ccy: $("#ccy").val(),
            tradeDate: $("#tradeDate").val(),
            dzh:"dzh"
        }
        var object = {
            portfolioId: ['portfolioId', 'TrdRepoTrade002.portfolioId'],
            ps: ['ps', 'TrdRepoTrade002.ps'],
            secAcctId: ['secAcctId', 'TrdRepoTrade002.secAcctId'],
            chlId: ['chlId', 'TrdRepoTrade002.chlId'],
            repoType: ['repoType', 'TrdRepoTrade002.repoType'],
            term: ['term', 'TrdRepoTrade002.term'],
            basis: ['basis', 'TrdRepoTrade002.basis'],
            channelFee: ['channelFee', 'TrdRepoTrade002.channelFee'],
            prinAmt: ['prinAmt', 'TrdRepoTrade002.prinAmt'],
            rebackPrice: ['rebackPrice', 'TrdRepoTrade002.rebackPrice'],
            rebackIntAmt: ['rebackIntAmt', 'TrdRepoTrade002.rebackIntAmt'],
            mdateSettleAmt: ['mdateSettleAmt', 'TrdRepoTrade002.mdateSettleAmt'],
            channelFee: ['channelFee', 'TrdRepoTrade002.channelFee'],
            tradeAmtAndFee: ['tradeAmtAndFee', 'TrdRepoTrade002.tradeAmtAndFee'],
            procedureAmt: ['procedureAmt', 'TrdRepoTrade002.procedureAmt'],
            settleAmt: ['settleAmt', 'TrdRepoTrade002.settleAmt'],
            commissionAmt: ['commissionAmt', 'TrdRepoTrade002.commissionAmt'],
            avalibleCash: $('avalibleCash','TrdRepoTrade002.avalibleCash'),
            avalibleCashT1: $('avalibleCashT1','TrdRepoTrade002.avalibleCashT1'),
            calendarId: $('calendarId','TrdRepoTrade002.calendarId'),
            ccy: $('ccy','TrdRepoTrade002.ccy'),
            tradeDate: $('tradeDate','TrdRepoTrade002.tradeDate'),
            data: data
        };
        window.BussRepoSecPtl["bussRepoSecPtl"].setInputParam(object);
        if($('#ps').val()=="04"){
            window.BussRepoSec["bussRepoSec"].setInputParam(object);
        }
    };
    //    多组合处理组件列表信息
    var dealPledgeBondTotal = function (data) {
        var ret= $.extend(data.etrEntrust,data.etrEntrustAdd,data.othInfo.entrustOthInfo);
        if(data.avaStandardStockPosition){
            ret.avaStandardStockPosition = data.avaStandardStockPosition;
        }
        //本金金额
        ret.prinAmt = ret.entrustSurAmt;
        //回购利率
        ret.rebackPrice = ret.price * 100;
        // param.portfolioId=ret.portfolioId;
        ret["avalibleCash"]=ret.T_0;
        ret["avalibleCashT1"]=ret.T_1;
        ret.channelAvailableCash = ret.T_2;
        if($('#ps').val()=='04'&&$('#tradeMarket').val()&&($('#tradeMarket').val()=='03'||$('#tradeMarket').val()=='04')
            && ret.secAcctId &&ret.portfolioId){
            var rowData = {};
            var param = {};
            param.portfolioId=ret.portfolioId;
            param.secManageAcctId=ret.secAcctId;
            param.ccy=$('#ccy').val();
            param.calendarId = $('#calendarId').val();
            Joyin.ajaxJson(JoyinUtil.getRealPath('/invest/trade/bond/TrdBondInvestTrade003/getBankCash'), param, function (result) {
                if(result){
                    ret.trdAcctAbleCash =  Number(result);
                }
            },false);
        }
        // var returnValForRet = calFeeDuozuhe(ret);
        //     returnVal = returnValForRet;
        return ret;
    }
    // //    多组合处理抵押品列表
    // var dealPledgeBond = function (data) {
    //     var retArr = [];
    //     $.each(data.etrPledgeList,function (index,value) {
    //         var tmp = $.extend(value,data.etrEntrust,data.etrEntrustAdd,data.othInfo.entrustOthInfo);
    //         tmp.pledgeRatio = tmp.pledgeRatio * 100;
    //         tmp.pledgePrin =tmp.pledgeAmt;
    //         tmp.bondCode = tmp.finprodId,
    //         retArr.push(tmp);
    //     });
    //     return retArr;
// }
    /**bussRepoSecPtl
     * 多组合交易0
     */
    var initMultiPtlInfo = function(){
        if(window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.table){
            //查询组合剩余现金用参数
            var params ={
                calendarId:$("#calendarId").val(),
                tradeDate:$("#tradeDate").val(),
                acctTypeList:ACCT_TYPE_A01,
                ccy:$("#ccy").val(),
            };
            if(fTradeId){//CSTP匹配交易过来的时候
                extractedREntrustList('isNumber',false,false);
                if(repoamt<entrustSurAmtAll){
                    $('#prinAmt').val(repoamt).trigger('blur').trigger('change');
                    window.BussRepoSecPtl["bussRepoSecPtl"].extractedTime();
                    window.BussRepoSecPtl["bussRepoSecPtl"].extractedJiSuan(false,returnVal);
                    window.BussRepoSecPtl["bussRepoSecPtl"].extractedCpriceAmt();
                }else{
                    $('#prinAmt').val(entrustSurAmtAll).trigger('blur').trigger('change');
                }
            }else{
                extractedREntrustList('isTime',true);
            }
        }
        else{
            setTimeout(function () {
                initMultiPtlInfo();
            },500);
        }
    }

    function extractedREntrustList(sortName,isTrue,IsTrue) {
        entrustSurAmtAll = 0;
        if (entrustList) {
            var number = 0
            if(sortName=='isTime'){
                entrustList = entrustList.sort(function (a, b) {
                    return a.etrEntrust.createTime - b.etrEntrust.createTime;
                });
            }else{
                entrustList = entrustList.sort(function (a, b) {
                    return b.othInfo.entrustOthInfo.entrustSurAmt - a.othInfo.entrustOthInfo.entrustSurAmt;
                });
            }
            $.each(entrustList, function (index, val) {
                var ret = dealPledgeBondTotal(val);
                ret.LAY_TABLE_INDEX = index;
                //组合分配
                window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.addRow(ret);
                var returnValForRet = window.BussRepoSecPtl['bussRepoSecPtl'].calFee(ret);
                returnVal = returnValForRet;
                // window.BussRepoSecPtl['bussRepoSecPtl'].extractedJiSuan('',returnVal);
                number = JoyinMath.doAdd(number, ret.prinAmt);
                entrustSurAmtAll = JoyinMath.doAdd(entrustSurAmtAll, ret.prinAmt);
                // 质押券汇总信息
                if (val.othInfo.pledgeBondInfoList && val.othInfo.pledgeBondInfoList.length > 0) {
                    for(var i=0;i<val.othInfo.pledgeBondInfoList.length;i++){
                        var num=JoyinMath.doMul(val.othInfo.pledgeBondInfoList[i].pledgePrin,ret.prinAmt==" "?'0':ret.prinAmt,2);
                        var prinLast=JoyinMath.doDiv(num,ret.entrustAmtBefore,2);
                        val.othInfo.pledgeBondInfoList[i].pledgePrin=prinLast;
                    }
                   
                    window.BussRepoSecPtl['bussRepoSecPtl'].addPledgeBond(val.othInfo.pledgeBondInfoList);
                }
                // window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTblDetailed;
            });
            if(isTrue){
                $('#prinAmt').val(number).trigger('blur').trigger('change');
            }
            if(IsTrue){
                window.BussRepoSecPtl['bussRepoSecPtl'].extractedJiSuan(true,returnVal);
            }else{
                window.BussRepoSecPtl['bussRepoSecPtl'].extractedJiSuan(false,returnVal);
            }
            window.BussRepoSecPtl['bussRepoSecPtl'].extractedCpriceAmt();
            $('.btn-table-add').remove();
            setTimeout(function () {
                // $('.btn_add_plegin').hide()
                $('.delt').remove();
            }, 1000)
        }
    }
    // 委托进入场合，根据委托代码设置画面信息
    var initPageInfoFromEntrust = function () {
        /**
         * 若从委托场合点击交易录单
         */
        var entrustId = $("#entrustId").val();
//        var str = entrustId.split(',');
//        entrustId=str[0];
        if (entrustId) {
            Joyin.ajaxSync(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade003/getEntrustInfo', {'entrustId': entrustId,"isNeedOth":'Y'},
                function (data) {
                    // 基本要素信息由投资委托带出，其中有：
                    // 委托代码、组合代码、回购类型、交易场所、交易方向、
                    // 清算速度、交易日历、期限品种、起息日、到期日、资金可用日、证券托管户，不可编辑
                    // 金融要素信息由投资委托带出，其中有：币种、本金金额、回购利率%
                    if (data && data.etrEntrust) {
                        etrEntrust = data.etrEntrust;
                        entrustList = data.baseEtrInvestInfoDtoList;
                        window.testData = data;
                        // 组合代码
                        // checkAndAssign("#portfolioId", etrEntrust.portfolioId);
                        // $("#portfolioId").textSelect('setValue', etrEntrust.portfolioId);
                        // 交易场所
                        checkAndAssign("#tradeMarket", etrEntrust.tradeMarket);
                        // 交易日历
                        checkAndAssign("#calendarId", etrEntrust.calendarId);
                        $("#calendarId").textSelect('setValue', etrEntrust.calendarId);
                        // 币种
                        checkAndAssign("#ccy", etrEntrust.ccy);
                        // 本金金额
                        if (!$("#prinAmt").val()) {
//                            checkAndAssign("#prinAmt", etrEntrust.entrustAmt, true);
                            window.BussRepoSecPtl['bussRepoSecPtl'].setStlCommandInput(function () {
                                return {
                                      'prinAmt': $('#prinAmt').val()
                                };
                            });
                        }
                        // 交易对手
                        checkAndAssign("#trdInvestPersonInfo_counterId", etrEntrust.counterId, true);
                        $("#trdInvestPersonInfo_counterId").textSelect('setValue', etrEntrust.counterId);
                        // 对手方交易员
                        checkAndAssign("#trdInvestPersonInfo_counterTrader", etrEntrust.counterTrader, true);
                        // 联系方式
                        checkAndAssign("#trdInvestPersonInfo_contactInfo", etrEntrust.contactInfo, true);
                        entrustAmt = etrEntrust.entrustAmt;
                        $("#chlId").textSelect('setValue', etrEntrust.chlId);
                    }
                    if (data != null) {
                        // 清算速度
                        checkAndAssign("#settleSpeedStr", data.settleSpeedStr);
                    }
                    if (data != null && data.etrEntrustAdd != null) {
                        etrEntrustAdd = data.etrEntrustAdd;
                        // 回购类型
                        checkAndAssign("#repoType", etrEntrustAdd.assetType1);
                        // 交易方向
                        checkAndAssign("#ps", etrEntrustAdd.busiPs);
                        // 期限品种
                        checkAndAssign("#termTypeReback", etrEntrustAdd.termType);
                        if (etrEntrustAdd.vdate) {
                            var vdateYMD = Date.ofDate(etrEntrustAdd.vdate).format('yyyy-MM-dd');
                            // 起息日
                            checkAndAssign("#vdate", vdateYMD);
                            // 委托的回购起息日
                            entrustVdate = vdateYMD;
                        }
                        if (etrEntrustAdd.mdate) {
                            var mdateStr = Date.ofDate(etrEntrustAdd.mdate).format('yyyy-MM-dd');
                            // 到期日
                            checkAndAssign("#mdate", mdateStr);
                            // 委托的回购到期日
                            entrustMdate = etrEntrustAdd.mdate;
                        }
                        // 期限(天)
                        checkAndAssign("#term", etrEntrustAdd.termDays);
                        // 资金可用日
                        if (etrEntrustAdd.cashUsedate) {
                            checkAndAssign("#avaFundsDate", Date.ofDate(etrEntrustAdd.cashUsedate).format('yyyy-MM-dd'));
                        }
                        // 证券托管户
                        checkAndAssign("#secAcctId", etrEntrustAdd.secAcctId);
                        $("#secAcctId").textSelect('setValue', etrEntrustAdd.secAcctId);
                        $("#secAcctId").trigger("change");
                        // 回购利率%
                        if (!$("#rebackPrice").val()) {
                            checkAndAssign("#rebackPrice", etrEntrust.price*100, true);
                        }
                        // 委托的回购利率
                        entrustPrice = JoyinMath.doMul(etrEntrust.price,100,6);
                        // 委托的回购期限From
                        entrustTermFrom = etrEntrustAdd.termDays;
                        // 委托的回购期限To
                        entrustTermTo = etrEntrustAdd.termDays2;
                        // 委托的期限品种
                        entrustTermType = etrEntrustAdd.termType;
                    }

                    if (data && data.etrEntrust && data.etrEntrust.chlId) {
                        $("#chlId").prop("disabled", true).trigger('change');
                    }
                });
        }
    };


    window.componentLoadCallback = function (isDone, pagePath, params) {
        if (isDone) {
            var riskparam = getriskdata();
            if (riskparam!=null&&pageModel==PAGE_MODEL_NEW){
                riskparam.data =null;
            }
            var result=null;
            window.BaseRiskMathComm.getparam(riskparam);
            Joyin.ajaxSync(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade003/selectIndex',{},function(result){
            window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                var rows =  window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.getAllRows();
                var isTrue = false;
                $.each(rows,function (index,data) {
                    if(!data.secAcctId){
                        isTrue = true;
                    }
                });
                if (isTrue && (!$("#chlId").val() || typeof $("#chlId").val() == 'undefined')){
                    Joyin.error(JoyinUtil.getMessage('FW_E_0006',['组合分配证券账户或者通道']));
                    return null;
                }
                var object = $.serializeToObject('#submitForm', true);
                var BussRepoSecDto=null;
                if(object!=null&&object!=""){
                    BussRepoSecDto=object.bussRepoSecPtl.pledgeBondList;
                }
                var a=0;
                a=a+1;
                result=result+a;
                var list = [];
                for(var i in BussRepoSecDto){
                    var dealinfo = {};
                    if(BussRepoSecDto[i].prinAmt!=null&&BussRepoSecDto[i].prinAmt!=" "&&BussRepoSecDto[i].prinAmt>0){
                        dealinfo.finprodType2 = getFinProdType2FromPs();
                        dealinfo.settleDate = $("#tradeDate").val();
                        dealinfo.tradeDate = $("#tradeDate").val();
                        dealinfo.ps = PS_T01;
                        dealinfo.ccy = $("#ccy").val();
                        dealinfo.tradeAmt =  BussRepoSecDto[i].prinAmt; // TODO 多组合有通道费
                        dealinfo.portfolioId = BussRepoSecDto[i].portfolioId;
                        dealinfo.counterId = $("#trdInvestPersonInfo_counterId").val();
                        dealinfo.tradeMarket = $("#tradeMarket").val();
                        // dealinfo.tradePlat = $("#tradePlat").val();
                        dealinfo.secManageAcctId = BussRepoSecDto[i].secAcctId;
                        dealinfo.branch = $("#noBranchNoValue").val();
                        // 通道交易 新增 强成西 2019/03/26 start
                        dealinfo.chlId = $("#chlId").val();
                        dealinfo.index= result;
                        // 通道交易 新增 强成西 2019/03/26 end
                        // dealObj.portfolioId = BussRepoSecDto[i].portfolioId;
                        // dealObj.finprodType2 = getFinProdType2FromPs();
                        // dealObj.settleDate = $("#tradeDate").val();
                        // dealObj.tradeDate = $("#tradeDate").val();
                        // dealObj.ps = PS_T01;
                        // dealObj.ccy = $("#ccy").val();
                        // dealObj.tradeAmt = BussRepoSecDto[i].prinAmt;
                        // dealObj.chlId = $("#chlId").val();
                        // dealObj.counterId = $("#trdInvestPersonInfo_counterId").val();
                        // dealObj.tradeMarket = $("#tradeMarket").val();
                        // dealObj.secManageAcctId = BussRepoSecDto[i].secAcctId;
                        // dealObj.branch = $("#noBranchNoValue").val();
                        // dealObj.index= result;
                        list.push(dealinfo);
                    }
                 }
                return {
                    'isTotalTrade': YES_OR_NO_N
                    , 'bankLendingType': null
                    , 'portfolioId': 'dzh'
                    , 'jiaoyiType':'dzh'
                    , 'deal': list
                    ,'repPageId': pageId
                };
            });
            });
//            window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'].showCash();
            if (entrustId != '') {
                if (etrEntrust) {
                    window.TrdInvestPersonInfo002['trdInvestPersonInfo'].setEntrust(function () {
                        return etrEntrust;
                    });
                }
            }
            // 隐藏资产可用持仓
            window.BussTrdAvailableCashQuery["bussTrdAvailableCashQuery"].hidePosition();
            window.BussTrdAvailableCashQuery["bussTrdAvailableCashQuery"].hideCash();
            tradeMarketOnChange(true);
            // 委托进入场合，根据委托代码设置画面信息
            // 初始化时，根据画面信息，获取当前最新托管户剩余现金、组合T日可用现金、组合T+1日可用现金
            repoTypeOnChange(true);
            if(fTradeIdFromCstp){
                $('#fTradeId').textSelect('setValue',fTradeIdFromCstp);
                $('#fTradeId').attr('disabled',true);
            }
            initPageInfoFromEntrust();
            $('#ps').trigger('change');
//            psOnChange(true);

            if (formTodoList) {
                window.BussTrdTransferOrder['bussTrdTransferOrder'].saveCalcCondition();
            }
            // 设置添加质押券共通参数
            setBussRepoSecInput();
            prinAmtRebackIntAmtOnChange();
            //处理完质押券之后处理多组合组件
            initMultiPtlInfo();
            
            var object = $.serializeToObject('#submitForm', true);
            var ListDetailed=[];
            if(object.bussRepoSecPtl){
                if(isIntVal){
                    ListDetailed=object.bussRepoSecPtl.pledgeBondListDetailed;
                    window.BussRepoSecPtl["bussRepoSecPtl"].isIntVal2(ListDetailed,isIntVal);
                    isIntVal=false;
                }
            }
            
            //分配方式
            $('#bussRepoSecPtl_btn_autoAllocation').click(function () {
                if (!$("#prinAmt").val() || $("#prinAmt").val()*1 <= 0) {
                    Joyin.error("本金金额必须大于0")
                    return;
                }
                if($("#entrustId").val()){
                    extractedZiDongFenPei2();
                }else{
                    extractedZiDongFenPei();
                }
                window.BussRepoSecPtl["bussRepoSecPtl"].pleBondTotalVal();
            });
            if($("#entrustId").val()){
                if(window.BussTrdTransferOrder['bussTrdTransferOrder'].bussTrdTransferOrderTable){
                    window.BussTrdTransferOrder['bussTrdTransferOrder'].bussTrdTransferOrderTable.clear();
                }
            }
        }
    };
    
    function extractedZiDongFenPei2() {
        var entrustId = $("#entrustId").val();
        if (entrustId) {
            Joyin.ajaxSync(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade003/getEntrustInfo', {'entrustId': entrustId,"isNeedOth":'Y'},
            function (data) {
                var result=data.baseEtrInvestInfoDtoList;
                var rets=[];
                $.each(result, function (index, val) {
                    var ret = dealPledgeBondTotal(val);
                    ret.createTime=result[index].etrEntrust.createTime;
                    ret.LAY_TABLE_INDEX = index;
                    rets.push(ret);
                })
                if ($('#bussRepoSecPtl_distribution').val() == '139') {//平均分配
                    window.BussRepoSecPtl["bussRepoSecPtl"].distribution3(rets);
                    window.BussRepoSecPtl["bussRepoSecPtl"].extractedJiSuan3();
                } else if ($('#bussRepoSecPtl_distribution').val() == '140') {//时间优先
                    rets = rets.sort(function (a, b) {
                        return a.createTime - b.createTime;
                    });
                    window.BussRepoSecPtl["bussRepoSecPtl"].distribution2(rets,'createTime');
                    window.BussRepoSecPtl["bussRepoSecPtl"].extractedJiSuan3();
                } else if ($('#bussRepoSecPtl_distribution').val() == '141') {//数量优先
                    rets = rets.sort(function (a, b) {
                        return b.entrustSurAmt - a.entrustSurAmt;
                    });
                    window.BussRepoSecPtl["bussRepoSecPtl"].distribution2(rets,'prinAmt');
                    window.BussRepoSecPtl["bussRepoSecPtl"].extractedJiSuan3();
                }else if ($('#bussRepoSecPtl_distribution').val() == '142') {//价格优先
                    var ps=$('#ps').val();
                    if(ps=='04'){//逆回购回购利率从低到高
                        rets = rets.sort(function (a, b) {
                            return a.rebackPrice - b.rebackPrice;
                        });
                    }else if(ps=='03'){//正回购回购利率从高到低
                        rets = rets.sort(function (a, b) {
                            return b.rebackPrice - a.rebackPrice;
                        });
                    }
                    
                    window.BussRepoSecPtl["bussRepoSecPtl"].distribution2(rets,'rebackPrice');
                    window.BussRepoSecPtl["bussRepoSecPtl"].extractedJiSuan3();
                }
            });
        }
    }

    function extractedZiDongFenPei() {
        if ($("#entrustId").val()) {
            //平均分配
            if ($('#bussRepoSecPtl_distribution').val() == '139') {
                if($('#prinAmt_pinyun_isChange').val()=='true'){
                    window.BussRepoSecPtl["bussRepoSecPtl"].extractedJiSuan(false,returnVal);
                    window.BussRepoSecPtl["bussRepoSecPtl"].extractedCpriceAmt();
                }else{
                    window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.clear();
                    extractedREntrustList('isTime',false,true);
                }
                $('#prinAmt_shijian_isChange').val('false');
                $('#prinAmt_shuliang_isChange').val('false');
            } else if ($('#bussRepoSecPtl_distribution').val() == '140') {//时间优先
                if($('#prinAmt_shijian_isChange').val()=='true'){
                    window.BussRepoSecPtl["bussRepoSecPtl"].extractedJiSuan(false,returnVal);
                    window.BussRepoSecPtl["bussRepoSecPtl"].extractedCpriceAmt();
                }else{
                    window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.clear();
                    extractedREntrustList('isTime',false,false);
                    window.BussRepoSecPtl["bussRepoSecPtl"].extractedTime();
                    window.BussRepoSecPtl["bussRepoSecPtl"].extractedJiSuan(false,returnVal);
                    window.BussRepoSecPtl["bussRepoSecPtl"].extractedCpriceAmt();
                }
                $('#prinAmt_pinyun_isChange').val('false');
                $('#prinAmt_shuliang_isChange').val('false');
            } else if ($('#bussRepoSecPtl_distribution').val() == '141') {//数量优先
                if($('#prinAmt_shuliang_isChange').val()=='true'){
                    window.BussRepoSecPtl["bussRepoSecPtl"].extractedJiSuan(false,returnVal);
                    window.BussRepoSecPtl["bussRepoSecPtl"].extractedCpriceAmt();
                }else{
                    window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.clear();
                    extractedREntrustList('isNumber',false,false);
                    window.BussRepoSecPtl["bussRepoSecPtl"].extractedTime();
                    window.BussRepoSecPtl["bussRepoSecPtl"].extractedJiSuan(false,returnVal);
                    window.BussRepoSecPtl["bussRepoSecPtl"].extractedCpriceAmt();
                }
                $('#prinAmt_pinyun_isChange').val('false');
                $('#prinAmt_shijian_isChange').val('false');
            } else {
                if($('#prinAmt_pinyun_isChange').val()=='true'){
                    window.BussRepoSecPtl["bussRepoSecPtl"].extractedJiSuan('',returnVal);
                    window.BussRepoSecPtl["bussRepoSecPtl"].extractedCpriceAmt();
                }else{
                    window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.clear();
                    extractedREntrustList('isTime',false,true);
                }
                $('#prinAmt_shijian_isChange').val('false');
                $('#prinAmt_shuliang_isChange').val('false');
            }
        }
    }



    /**
     * 根据交易方向，确定资产类型：金融产品类型_投管用_债券质押式正回购、金融产品类型_投管用_债券质押式逆回购
     * @returns
     */
    function getFinProdType2FromPs() {
        return ($("#ps").val() == null || $("#ps").val() == "") ? "" : ($("#ps").val() == BACK_LEND_DIRECTION_03 ? FINPROD_TYPE_TG_F02 : FINPROD_TYPE_TG_F03);
    }
    var settleSpeedChangeAction = function(){
    	var settleSpeed = $('#settleSpeedStr').val();
    	var tradeDate = $('#tradeDate').val();
    	if (settleSpeed && settleSpeed != '' && tradeDate && tradeDate != ''){
    		var num = 0;
    		if (settleSpeed === SETTLESPEED_1){
    			num = 1;
    		}else if(settleSpeed === SETTLESPEED_2){
    			num = 2;
    		}
    		$('#vdate').val(DateUtil.parse(tradeDate).DateAdd('d', num).Format('yyyy-MM-dd')).trigger('change');
    		vmdateOnChangeCalcDate($('#vdate').val(),$('#vpayDate'));
    	}
    	
    };
    
    /**
     * 起息日、到期日改变时，自动计算首次交收日、到期交收日、实际占款天数
     */
    var vmdateOnChangeCalcDate = function (date,dom) {
    	if(pageModel!=PAGE_MODEL_REFERENCE){
    		if($("#repoType").val()&&$("#repoType").val()==TARGET_BACK_TYPE_H01){
    			dom.val(date).trigger('change');
    			if($('#tradeMarket').val() != TRADE_PLACE_01){
                    caculateDays($("#vpayDate").val(),$("#mpayDate").val());
                }else{
                    caculateDays($("#vdate").val(),$("#mdate").val());
                }
    		}else {
    			var vdate = date;
                var term = '1';
                var calendarId = $('#calendarId').val();
                if (vdate && mdate && calendarId) {
                    Joyin.ajaxSync(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade003/getMdate',
                            {vdate:vdate,term:term,calendarId:calendarId},
                            function (data) {
                                if (data && data.tradeDate && data.tradeDate) {
                                    dom.val(data.tradeDate).trigger('change');
                                    if($('#tradeMarket').val() != TRADE_PLACE_01){
                                        caculateDays($("#vpayDate").val(),$("#mpayDate").val());
                                    }else{
                                        caculateDays($("#vdate").val(),$("#mdate").val());
                                    }
                                }
                   });
                }
    		}
    	}
    };
    
    var caculateDays = function(vdate,mdate){
        var fTradeId =  $('#fTradeId').val();
        if(vdate && mdate&&!fTradeId){
            Joyin.ajaxSync(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade003/getBetweenDays',
                    {vdate:vdate,mdate:mdate},
                    function (data) {
                        if(data){
                            $('#actCapDays').val(data).trigger('change');
                        }
                    });
        }
    };

    var getriskPara = function () {
        var object = $.serializeToObject('#submitForm', true);
        var u=0;
        if(object.bussRepoSecPtl!=null&&object.bussRepoSecPtl!=""){
            if(object.bussRepoSecPtl.pledgeBondList!=null&&object.bussRepoSecPtl.pledgeBondList!=""){
                var pledgeBondList=object.bussRepoSecPtl.pledgeBondList;
                var pledgeBondList2=[];
                for(var i in pledgeBondList){
                    if(pledgeBondList2.length>0){
                        var k=0;
                        for(var j in pledgeBondList2){
                            if(pledgeBondList2[j].portfolioId==PledgeBondList[i].portfolioId){
                                k=k+1;
                            }
                        }
                        if(k==0){
                            pledgeBondList2[u]=PledgeBondList.get(i);
                            u=u+1;
                        }
                    }else{
                        pledgeBondList2[u]=PledgeBondList.get(i);
                        u=u+1;
                    }
                }
            }
        }
        return pledgeBondList2;
    };
    
    var vpayDateChange =function(){
    	if(pageModel!=PAGE_MODEL_REFERENCE){
    		if($('#tradeMarket').val() != TRADE_PLACE_01){
                var mpayDate = $('#mpayDate').val();
                var vpayDate = $('#vpayDate').val();
                var calendarId = $("#calendarId").val();
                if(vpayDate&&calendarId){
                    Joyin.ajaxJson(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade002/isDealCalendarHoliday', {
                           calendarId: calendarId,
                           mpayDate: vpayDate
                        }, function (date) {
                            if(date){
                                Joyin.ajaxSync(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade002/getNextWorkDay', {
                                   'calendarId': calendarId,
                                   'tradeDate': vpayDate
                                }, function (date) {
                                   $("#vpayDate").val(date);
                                   if(mpayDate){
                                       caculateDays($("#vpayDate").val(),mpayDate);
                                   }
                                });
                            }
                            if(mpayDate){
                                caculateDays($("#vpayDate").val(),mpayDate);
                            }
                        },false);
                }
            }
    	}
    }
    
    var mpayDateChange = function(){
        if($('#tradeMarket').val() != TRADE_PLACE_01){
            var mpayDate = $('#mpayDate').val();
            var vpayDate = $('#vpayDate').val();
            var calendarId = $("#calendarId").val();
            if(calendarId && mpayDate){
            Joyin.ajaxJson(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade002/isDealCalendarHoliday', {
                   calendarId: calendarId,
                   mpayDate: mpayDate
                }, function (date) {
                    if(date){
                        Joyin.ajaxSync(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade002/getNextWorkDay', {
                           'calendarId': calendarId,
                           'tradeDate': mpayDate
                        }, function (date) {
                           $("#mpayDate").val(date);
                           if(mpayDate){
                               caculateDays(vpayDate,$("#mpayDate").val());
                           }
                        }, false);
                    }
                    if(mpayDate){
                        caculateDays(vpayDate,$("#mpayDate").val());
                    }
                }, false);
            }
        }
    }
    var mdateChange =function(){
//        if($('#tradeMarket').val() != TRADE_PLACE_01){
            var mdate = $('#mdate').val();
            var vdate = $('#vdate').val();
            var calendarId = $("#calendarId").val();
            if(mdate&&calendarId){
                Joyin.ajaxJson(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade002/isDealCalendarHoliday', {
                       calendarId: calendarId,
                       mpayDate: mdate
                    }, function (date) {
                        if(date){
                            Joyin.ajaxSync(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade002/getNextWorkDay', {
                               'calendarId': calendarId,
                               'tradeDate': mdate
                            }, function (date) {
                               $("#mdate").val(date).trigger('change');
                               if(vdate&&($('#tradeMarket').val() == TRADE_PLACE_01)){
                                   caculateDays($("#vdate").val(),$("#mdate").val());
                               }
                            });
                        }
//                        if(mdate){
//                            caculateDays($("#vdate").val(),mdate);
//                        }
                    },false);
            }
//        }
    }
    var vdateChange =function(){
//        if($('#tradeMarket').val() != TRADE_PLACE_01){
            var mdate = $('#mdate').val();
            var vdate = $('#vdate').val();
            var calendarId = $("#calendarId").val();
            if(vdate&&calendarId){
                Joyin.ajaxJson(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade002/isDealCalendarHoliday', {
                       calendarId: calendarId,
                       mpayDate: vdate
                    }, function (date) {
                        if(date){
                            Joyin.ajaxSync(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade002/getNextWorkDay', {
                               'calendarId': calendarId,
                               'tradeDate': vdate
                            }, function (date) {
                               $("#vdate").val(date).trigger('change');
                                vmdateOnChangeCalcDate($('#vdate').val(),$('#vpayDate'));
                               if(mdate&&($('#tradeMarket').val() == TRADE_PLACE_01)){
                                   caculateDays($("#vdate").val(),$("#mdate").val());
                               }
                            });
                        }
//                        if(mdate){
//                            caculateDays($("#vdate").val(),mdate);
//                        }
                    },false);
            }
//        }
    }
    /**
     * 交易场所联动交易日历
     */
    var tradeMarketChangeCalendarId = function(){
    	if($("#tradeMarket").val()){
    		if($("#tradeMarket").val()==TRADE_PLACE_01){
    			//银行间
    			$("#calendarId").textSelect('setValue', '03');
                $('#repoType').val('H01').trigger('change');
                $('#repoType').attr('disabled',true);
    		}else if($("#tradeMarket").val()==TRADE_PLACE_02){
    			//上交所
    			$("#calendarId").textSelect('setValue', '04');
                $('#repoType').val('H02').trigger('change');
                $('#repoType').attr('disabled',false);
                $('#settleSpeedStr').val('01').trigger('change');
    		}else if($("#tradeMarket").val()==TRADE_PLACE_03){
    			//深交所
    			$("#calendarId").textSelect('setValue', '05');
                $('#repoType').val('H02').trigger('change');
                $('#repoType').attr('disabled',false);
                $('#settleSpeedStr').val('01').trigger('change');
    		}
    	}
    }

    /**
     * 期限品种联动期限
     */
    var termTypeChangeAction = function(){
        var termType = $('#termTypeReback').val();
//		if($("#repoType").val()&&$("#repoType").val()==TARGET_BACK_TYPE_H02){
        if(($("#tradeMarket").val()==TRADE_PLACE_02 && $("#repoType").val()!=TARGET_BACK_TYPE_H01)|| $("#tradeMarket").val()==TRADE_PLACE_03){
	        if ($('#termTypeReback').val()){
	            var repoterm = '';
	            if (termType == R001 || termType == GC001 || termType == R_001 || termType == RC001){
	                repoterm = 1;
	            }else if(termType == GC002 || termType == R_002){
	                repoterm = 2;
	            }else if(termType == GC003 || termType == R_003 || termType == RC003){
	                repoterm = 3;
	            }else if(termType == GC004 || termType == R_004){
	                repoterm = 4;
	            }else if (termType == R007 || termType == GC007 || termType == R_007 || termType == RC007){
	                repoterm = 7;
	            }else if (termType == R014 || termType == GC014 || termType == R_014){
	                repoterm = 14;
	            }else if (termType == R021){
	                repoterm = 21;
	            }else if (termType == GC028 || termType == R_028){
	                repoterm = 28;
	            }else if (termType == R1M){
	                repoterm = 30;
	            }else if (termType == R2M){
	                repoterm = 60;
	            }else if (termType == R3M){
	                repoterm = 90;
	            }else if (termType == GC091 || termType == R_091){
	                repoterm = 91;
	            }else if (termType == R4M){
	                repoterm = 120;
	            }else if (termType == R6M){
	                repoterm = 180;
	            }else if (termType == GC182 || termType == R_182){
	                repoterm = 182;
	            }
	            /*$('#TrdOutRightRepoTrade002_mdate').attr('disabled',true);*/
	            $('#term').val(repoterm).trigger('change');
	        }
        }
        if($("#tradeMarket").val()==TRADE_PLACE_01 || ($("#tradeMarket").val()==TRADE_PLACE_02 && $("#repoType").val()==TARGET_BACK_TYPE_H01)){
			var repoterm = $('#term').val();
			var re = /^[0-9]*$/;
	        if(repoterm && re.test(repoterm)){
	            var vdate = $('#vdate').val();
	            if(vdate){
	                //回购期限联动期限品种
	            	$('#termTypeReback').val(getTermType(vdate,repoterm));
	            }
	        }else{
	        	$('#termTypeReback').val("");
	        }
		} 
//		}

    };
    
    /**
	 * 回购期限
	 */
	var repoTermChangeAction = function(){
		if($("#tradeMarket").val()==TRADE_PLACE_01){
			var repoterm = $('#term').val();
			var re = /^[0-9]*$/;
	        if(repoterm && re.test(repoterm)){
	            var vdate = $('#vdate').val();
	            if(vdate){
	                //回购期限联动期限品种
	            	$('#termTypeReback').val(getTermType(vdate,repoterm)).trigger('change');
	            }
	        }else{
	        	$('#termTypeReback').val("").trigger('change');
	        }
		} else if($("#tradeMarket").val()==TRADE_PLACE_02 && $("#repoType").val()==TARGET_BACK_TYPE_H01){
		   	var repoterm = $('#term').val();
			var re = /^[0-9]*$/;
	        if(repoterm && re.test(repoterm)){
	            var vdate = $('#vdate').val();
	            if(vdate){
	                //回购期限联动期限品种
	            	$('#termTypeReback').val(getTermType(vdate,repoterm)).trigger('change');
	            }
	        }else{
	        	$('#termTypeReback').val("").trigger('change');
	        }  
		}
	}
	 
	/**
     * 获取期限品种  date(yyyy-MM-dd),repoterm
     */
    function getTermType(date,repoterm) {
    	if(repoterm&&repoterm<=21){
    		//期限品种小于21天
    		if(repoterm==1){
    			return R001;
    		}
    		if(repoterm>1&&repoterm<=7){
    			return R007;
    		}
    		if(repoterm>7&&repoterm<=14){
    			return R014;
    		}
    		if(repoterm>14&&repoterm<=21){
    			return R021;
    		}
    	}
    	var day=0;
    	//date为空时  返回0
    	if(date&&repoterm){
    		//获取日期
    		date =DateUtil.parse(date);
    		//获取之后月00天 即本月最后一天
    		day= new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+1,0).getDate();
    		if(repoterm<=day){
    			return R1M;
    		}
    		day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+2,0).getDate();
    		if(repoterm<=day){
    			return R2M;
    		}
    		day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+3,0).getDate();
    		if(repoterm<=day){
    			return R3M;
    		}
    		day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+4,0).getDate();
    		if(repoterm<=day){
    			return R4M;
    		}
    		day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+5,0).getDate();
    		day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+6,0).getDate();
    		if(repoterm<=day){
    			return R6M;
    		}
    		day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+7,0).getDate();
    		day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+8,0).getDate();
    		day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+9,0).getDate();
    		if(repoterm<=day){
    			return R9M;
    		}
    		day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+10,0).getDate();
    		day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+11,0).getDate();
    		day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+12,0).getDate();
    		if(repoterm<=day){
    			return R1Y;
    		}
    	}
    	return null;
    }

	/**
	 * 起息日联动首付日
	 */
	var vpayDateChangeByvdate = function(){
		if(pageModel!=PAGE_MODEL_REFERENCE){
//			if ($("#calendarId").val() && $("#vdate").val()) {
//	            Joyin.ajaxSync(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade003/getNextWorkDay', {
//	                'calendarId': $("#calendarId").val(),
//	                'tradeDate': $("#vdate").val()
//	            }, function (date) {
//	                $("#vpayDate").val(date).trigger("change");
//	            });
//	        }
	        vmdateOnChangeCalcDate($('#vdate').val(),$('#vpayDate'));
//			$("#vpayDate").val($("#vdate").val()).trigger('change');
		}
	}
	/**
	 * 到期日联动到付日
	 */
	var mpayDateChangeBymdate = function(){
		var settleSpeed = $('#settleSpeedStr').val();
		var num =0;
		if (settleSpeed === SETTLESPEED_1){
			num = 1;
		}else if(settleSpeed === SETTLESPEED_2){
			num = 2;
		}
        if ($("#vdate").val() && $("#mdate").val()) {
            var days = (Date.ofDate($("#mdate").val()) - Date.ofDate($("#vdate").val())) / (1000 * 60 * 60 * 24);
            $("#term").val(days);
        }
		 if($("#mdate").val()&&$("#term").val()){
			 var d1 = new Date($("#mdate").val()).getTime();
	         var day = parseInt(num*24*60*60*1000);
	         var mdate = new Date(d1+day);
	         $('#mpayDate').val(mdate.format("yyyy-MM-dd")).trigger('change');
		 }
	}

    function extractedTableRendow() {
        if (BACK_LEND_DIRECTION_04 == $("#ps").val()) {//逆回购
            if (window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.table) {
                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{
                    field: 'avalibleCash',
                    visible: true
                }]);
                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{
                    field: 'avalibleCashT1',
                    visible: true
                }]);
                if($('#tradeMarket').val()=='04'||$('#tradeMarket').val()=='03'){
                    window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{field:'trdAcctAbleCash',visible: true}]);
                }
                if ($('#chlId').val()) {
                    window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{
                        field: 'channelAvailableCash',
                        visible: true
                    }]);
                } else {
                    window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{
                        field: 'channelAvailableCash',
                        visible: false
                    }]);
                }
                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.clear();
            }
        } else {
            if (window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.table) {
                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{
                    field: 'avalibleCash',
                    visible: false
                }]);
                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{
                    field: 'avalibleCashT1',
                    visible: false
                }]);
                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{
                    field: 'channelAvailableCash',
                    visible: false
                }]);
//                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{field:'trdAcctAbleCash',visible: false}]);
                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.clear();
            }
        }
    }

    /**
     * 画面加载完成
     */
    $(function () {
        $('#Ps04Repo').hide();
        $('#basis').attr('disabled',true);
        $('#settleSpeedStr').val('01').trigger('change');
        // 绑定按钮事件
        $('#TrdRepoTrade003_btn_save').click(function () {
            saveOrSubmit('save');
        });
        $('#TrdRepoTrade003_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
        $('#tradeMarket').on("change", function () {
            // 交易场所、回购类型、交易方向改变时
            tradeMarketOnChange();
            // add by wangguoxian 费用设置 20190807 start
            calFee();
            changeTradeAmtAndFee();
            repoTermChangeAction();
            // add by wangguoxian 费用设置 20190807 end
        });
        $('#repoType').on("change", function () {
            // 交易场所、回购类型、交易方向改变时
            repoTypeOnChange();
            settleSpeedChangeAction();
        });
        $('#ps').on("change", function () {
            debugger;
            // 交易场所、回购类型、交易方向改变时
            // tradeMarketRepoTypePsOnChange();
            // 交易方向改变时
            //psOnChange();
            // 组合代码、证券托管户、交易日、到期日改变时，刷新可使用标准券额度
            //refreshAvaStandardStockPosition();
            // 设置添加质押券共通参数
            setBussRepoSecInput();
            // add by wangguoxian 费用设置 20190807 start
            calFee();
            changeTradeAmtAndFee();
            extractedTableRendow();
            // add by wangguoxian 费用设置 20190807 end
            
            if($('#ps').val()=="04"){
                if($('#repoType').val()!="H02"){
                    $('#Ps04Repo').show();
                }
                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{field:'addPledgeInfo',visible: false}]);
                $('.laytable-cell-5-0-15').remove();
                $('.laytable-cell-4-0-13').remove();
                
                if($('#repoType').val()=="03"||$('#repoType').val()=="04"||$('#repoType').val()=="H02"||$('#repoType').val()=="H01"){
                    window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{field:'trdAcctAbleCash',visible: true}]);
                }else{
                    window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{field:'trdAcctAbleCash',visible: false}]);
                }
            }else{
                if($('#repoType').val()!="H02"){
                    window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{field:'addPledgeInfo',visible: true}]);
                }
                $('#Ps04Repo').hide();
                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.load();
                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{field:'trdAcctAbleCash',visible: false}]);
            }
        });
        $("#chlId").on("change", function () {
            if ($("#chlId").val()) {
                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{
                    field: 'channelAvailableCash',
                    visible: true
                }]);
                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.clear();
            }else{
                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{
                    field: 'channelAvailableCash',
                    visible: false
                }]);
                if(pageModel==PAGE_MODEL_NEW&&!entrustId){
                    //清空质押劵
                    window.BussRepoSec["bussRepoSecPtl"].pledgeBondTotalTbl.clear();
                }
                // window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.clear();
            }
            refreshCashAndBalance();
            setBussRepoSecInput();
        });
        $('#vdate').on("change", function () {
            // 起息日、到期日改变时，自动计算回购期限
        	vmdateOnChangeCalcTerm();
            /*vmdateOnChangeCalcDate($('#vdate').val(),$('#vpayDate'));*/
            vpayDateChangeByvdate();
            vdateChange();
        });
        $('#mdate').on("change", function () {
            // 起息日、到期日改变时，自动计算回购期限
            /*vmdateOnChangeCalcTerm();*/
            vmdateOnChangeCalcDate($('#mdate').val(),$('#mpayDate'));
            /*mpayDateChangeBymdate();*/
            // 组合代码、证券托管户、交易日、到期日改变时，刷新可使用标准券额度
            refreshAvaStandardStockPosition();
            mdateChange();
        });
      //实际占款天数刷新回购利息
        $('#actCapDays').on("change", function () {
            refreshRebackIntAmt();
        });
        $('#calendarId, #tradeMarket,#term').on("change", function () {
        	vmdateOnChangeCalcTerm();
            /*vmdateOnChangeCalcDate($('#vdate').val(),$('#vpayDate'));*/
            /*vmdateOnChangeCalcDate($('#mdate').val(),$('#mpayDate'));*/
            /*vpayDateChangeByvdate();*/
        });
        $('#tradeMarket').on("change", function () {
        	tradeMarketChangeCalendarId();
        });

        // add by zhanggenglin 首期交付日,到期交付日变动时 20190807 start
        $('#vpayDate').on("change", function(){
        		vpayDateChange()
        });
        $('#mpayDate').on("change",function(){
        		mpayDateChange()
        });
       // add by zhanggenglin 首期交付日,到期交付日变动时 20190807 end
        $('#procedureAmt,#settleAmt,#commissionAmt').on('change',function () {
            window.BussRepoSecPtl["bussRepoSecPtl"].extractedJiSuan('',returnVal);
            window.BussRepoSecPtl["bussRepoSecPtl"].extractedCpriceAmt();
        });

        // 本金金额、回购利息改变时
        $('#prinAmt, #rebackIntAmt').on("change", function () {
            prinAmtRebackIntAmtOnChange();
            // window.BussRepoSecPtl["bussRepoSecPtl"].extractedJiSuan();
            // window.BussRepoSecPtl["bussRepoSecPtl"].extractedCpriceAmt();
        });

        // $('#prinAmt').on('change',function () {
        //     if($("#entrustId").val()){
        //         window.BussRepoSecPtl["bussRepoSecPtl"].extractedJiSuan();
        //         window.BussRepoSecPtl["bussRepoSecPtl"].extractedCpriceAmt();
        //     }
        // });

        $('#trdInvestPersonInfo_counterId').on("change",function(){
            var counterId = $('#trdInvestPersonInfo_counterId');
            if (counterId.select2('data') && counterId.select2('data')[0]) {
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
        });

        // 手工改变佣金时
        $("#commissionAmt").on("keyup", function() {
            changeTradeAmtAndFee();
        });
        $('#calendarId, #ccy').on("change", function () {
            // 刷新托管户剩余现金、组合T日可用现金、组合T+1日可用现金
            refreshCashAndBalance();
        });
        /*$('body').on('change','#secAcctId',function(event,data){
            // 刷新托管户剩余现金、组合T日可用现金、组合T+1日可用现金
            refreshCashAndBalance();
            // 组合代码、证券托管户、交易日、到期日改变时，刷新可使用标准券额度
            refreshAvaStandardStockPosition();
            // 设置添加质押券共通参数
            setBussRepoSecInput();
            $("#secAcctId").val('').trigger('change');
        });*/
        // 刷新托管户剩余现金、组合T日可用现金、组合T+1日可用现金
      /*  $('body').on('change','#portfolioId',function(event,data){
            // 刷新托管户剩余现金、组合T日可用现金、组合T+1日可用现金
            refreshCashAndBalance();
            // 设置添加质押券共通参数
            setBussRepoSecInput();
        });*/
       /*  $('#portfolioId, #calendarId, #ccy').on("change", function () {
             refreshCashAndBalance();
             // 设置添加质押券共通参数
             setBussRepoSecInput();
         });*/
        // 本金金额、回购利率、回购期限、计息基础改变时，刷新回购利息
        $('#prinAmt, #rebackPrice,  #basis').on("change", function () {
            refreshRebackIntAmt();
            setBussRepoSecInput();
        });

       /* $('#secAcctId').on("change", function () {
            var repoType = $("#repoType").val();
            if ($("#secAcctId").val()) {
                $("#chlId").val('').prop("disabled", true);
            }
            else if ((!etrEntrust || !etrEntrust.chlId) && repoType == TARGET_BACK_TYPE_H01) {
                $("#chlId").prop("disabled", false);
            }
            // 组合代码、证券托管户、交易日、到期日改变时，刷新可使用标准券额度
            refreshAvaStandardStockPosition();
            setBussRepoSecInput();
            // add by wangguoxian 费用设置 20190807 start
            calFee();
            changeTradeAmtAndFee();
            // add by wangguoxian 费用设置 20190807 end
        });*/
        // TODO:交易所场合，如何根据成交编号，带出成交单上的信息，带出的信息不可修改；(等待接口开发后添加）
        // TODO:风险违规时校验处理（待共通完善后添加）
        // 风险试算
        $('#TrdRepoTrade003_btn_risk').click(function () {
            //风险新版本 add by panbin 2020-3-25 start
            //检查组合分配的和金额信息是否一致
            if($('#prinAmt').val()){
                var number = 0;
                for(var i=0;i<window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows().length;i++){
                    var rows = window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.getAllRows();
                    var prinAmt=rows[i].prinAmt==" "?0:rows[i].prinAmt;
                    if(prinAmt){
                        number =  JoyinMath.doAdd(number,prinAmt,2);
                    }
                }
                if(JoyinMath.doAdd($('#prinAmt').val(),0,2)!=number){
                    Joyin.warn("组合分配本金金额和金额信息本金金额不一致！");
                    return;
                }
            }
            var object = $.serializeToObject('#submitForm', true);
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfBatchData/1/RSK002/F07',object,getriskdata());
            //风险新版本 add by panbin 2020-3-25 end
            /* Joyin.ajax(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade003/selectIndex',{},function(result) {
                 if (result) {

                    // var index = result;
                     var object1 = getriskdata();
                   //  var list2 = object1.data;
                    /!* for (var i in list2) {
                         object1.data[i].mun = list2.length;
                         object1.data[i].index = index;
                     }*!/
                     //window.BaseRiskMathComm.initBaseRiskMathCommPage(object1);

                 }
                 //window.BaseRiskMathComm.initBaseRiskMathCommPage2(object1);
                 $("#riskMathFlag").val("02");
        });*/
        });

        // 清算速度
        $('#settleSpeedStr').change(function(){
			settleSpeedChangeAction();
		});
		// add by wangguoxian 费用设置 20190807 start
        // 交易日，期限品种改变时
        $("#tradeDate").change(function(){
            calFee();
            changeTradeAmtAndFee();
        });
        // 期限品种改变时
        $("#termTypeReback").on("keyup blur",function(){
            calFee();
            changeTradeAmtAndFee();
            termTypeChangeAction();
        });
        // add by wangguoxian 费用设置 20190807 end
        $("#termTypeReback").on("change",function(){
            calFee();
            termTypeChangeAction();
        });
        
        $("#term").on("change",function(){
            repoTermChangeAction(); 
        });
        
        if(isCstp&&fTradeId){
            fTradeIdChangeAction();
        }
        window.calFee=calFee;
    });

    $('body').on('change','#secAcctId',function(event,data){
        /*var repoType = $("#repoType").val();
        if ($("#secAcctId").val()) {
            $("#chlId").val('').prop("disabled", true);
        }
        else if ((!etrEntrust || !etrEntrust.chlId) && repoType == TARGET_BACK_TYPE_H01) {
            $("#chlId").prop("disabled", false);
        }
        // 组合代码、证券托管户、交易日、到期日改变时，刷新可使用标准券额度
        refreshAvaStandardStockPosition();
        setBussRepoSecInput();*/
        // add by wangguoxian 费用设置 20190807 start
//        calFee();
//        changeTradeAmtAndFee();
        // add by wangguoxian 费用设置 20190807 end
        
//        var object = $.serializeToObject('#submitForm', true);
//        var BussRepoSecDto=null;
        
        // 刷新托管户剩余现金、组合T日可用现金、组合T+1日可用现金
        refreshCashAndBalance();
        // 组合代码、证券托管户、交易日、到期日改变时，刷新可使用标准券额度
        refreshAvaStandardStockPosition();
        // var i=0;
        // var secAcctId2  = $('#secAcctId').val();
        // if(secAcctId2!=null&&secAcctId2!=''){
        //     i=i+1;
        // }
        //
        // if(i!=0){
        //     $("#chlId").val('').prop("disabled", true);
        // }else{
        //     $("#chlId").val('').prop("disabled", false);
        // }
    });
    // 银行间成交编号(当交易场所选择银行间时，银行间成交编号可编辑)
    $('#fTradeId').change(function(){
        if (pageModel==PAGE_MODEL_NEW&&!fTradeId){
            fTradeIdChangeAction();
        }
    });

    /**
     * 银行间成交编号输入时从cstp查询数据带出
     */
    var fTradeIdChangeAction = function(e, isInit){
        var fTradeId = $('#fTradeId').val();

        $('#repoType').attr('disabled', false);
        $('#ps').attr('disabled', false);
        $("#vdate").attr('disabled', false);
        $('#mdate').attr('disabled', false);
        $('#term').attr('disabled', false);
        /*$('#basis').attr('disabled', false);*/
        $('#ccy').attr('disabled', false);
        $('#prinAmt').attr('disabled', false);
        $('#rebackPrice').attr('disabled', false);
        $('#trdInvestPersonInfo_counterId').attr('disabled', false);

        if (fTradeId && fTradeId != ''){
            Joyin.ajaxSync(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade002/queryRepoCstpInfoByNo', {fedealno : fTradeId,isCstpSure:isCstpSure}, function(result){
                if (result){
                    if (result.dealdateStr && result.dealdateStr != ''){
                        $('#tradeDate').val(result.dealdateStr).trigger('change');// 交易日期
                        if($('#tradeDate').val()){
                            $('#tradeDate').attr('disabled', true);
                        }
                    }else{
                        $('#tradeDate').val('');
                        $('#tradeDate').attr('disabled', false);
                    }
                    // if (result.portfolioId && result.portfolioId != ''){
                    //     $('#portfolioId').textSelect('setValue', result.portfolioId);//组合代码
                    //     if($('#portfolioId').val()){
                    //         $('#portfolioId').attr('disabled', true);
                    //     }
                    // }else{
                    //     $('#bussTrdAvailableCashQuery_avalibleCash').val('');
                    //     $('#bussTrdAvailableCashQuery_avalibleCashT1').val('');
                    //     deleteSelect('portfolioId');
                    //     $('#portfolioId').attr('disabled', false);
                    // }
                    if (result.ourdepositaccountnumber && result.ourdepositaccountnumber != ''){
                        $('#secAcctId').textSelect('setValue', result.ourdepositaccountnumber);//证券账户
                        if($('#secAcctId').val()){
                            $('#secAcctId').attr('disabled', true);
                        }
                    }else{
                        deleteSelect('secAcctId');
                        deleteSelect('chlId');
                        $('#secAcctId').attr('disabled', false);
                        $('#chlId').attr('disabled', false);
                    }
                    if (result.prodtype && result.prodtype != ''){
                        $('#repoType').val(result.prodtype).trigger('change');// 回购类型
                        if($('#repoType').val()){
                            $('#repoType').attr('disabled', true);
                        }
                    }else{
                        deleteSelect('repoType');
                        $('#repoType').attr('disabled', false);
                    }
                    if(result.market){
                        $('#tradeMarket').val(result.market).trigger('change');//交易场所
                        if($('#tradeMarket').val()){
                            $('#tradeMarket').attr('disabled', true);
                        }
                        $('#calendarId').textSelect('setValue', result.market);//交易日历
                        if($('#calendarId').val()){
                            $('#calendarId').attr('disabled', true);
                        }
                    }else{
                        deleteSelect('tradeMarket');
                        deleteSelect('calendarId');
                        $('#tradeMarket').attr('disabled', false);
                        $('#calendarId').attr('disabled', false);
                    }

                    if (result.ps && result.ps != ''){
                        $('#ps').val(result.ps).trigger('change').attr('disabled', true);// 交易方向
                        if($('#ps').val()){
                            $('#ps').attr('disabled', true);
                        }
                    }else{
                        deleteSelect('ps');
                        $('#ps').attr('disabled', false);
                    }
                    if (result.vdateStr && result.vdateStr != ''){
                        $('#vdate').val(result.vdateStr).trigger('change');// 起息日
                        if($('#vdate').val()){
                            $('#vdate').attr('disabled', true);
                        }
                    }else{
                        $('moneyAvaDate').val('');
                        $('vpayDate').val('');
                        $('#vdate').val('');
                    }
                    if (result.mdateStr && result.mdateStr != ''){
                        $('#mdate').val(result.mdateStr).trigger('change');// 到期日
                        if($('#mdate').val()){
                            $('#mdate').attr('disabled', true);
                        }
                    }else{
                        $('#actCapDays').val('');
                        $('#avaFundsDate').val('');
                        $('#mpayDate').val('');
                        $('#mdate').val('');
                    }
                    if (result.term && result.term != ''){
                        $('#term').val(result.term).trigger('change');// 期限(天)
                        if($('#term').val()){
                            $('#term').attr('disabled', true);
                        }
                    }else{
                        $('#term').val('');
                        $('#term').attr('disabled', false);
                    }
                    if (result.basis && result.basis != ''){
                        $('#basis').val(result.basis).trigger('change');// 计息基础
                        if($('#basis').val()){
                            $('#basis').attr('disabled', true);
                        }
                    }
                    /*else{
                       deleteSelect('basis');
                        $('#basis').attr('disabled', false);
                    }*/
                    if (result.ccy && result.ccy != ''){
                        $('#ccy').val(result.ccy).trigger('change');//币种
                        if($('#ccy').val()){
                            $('#ccy').attr('disabled', true);
                        }
                    }else{
                        deleteSelect('ccy');
                        $('#ccy').attr('disabled', false);
                    }
                    if (result.repoamtcstp && result.repoamtcstp != ''){
                        $('#prinAmt').val(result.repoamtcstp).trigger('blur');//本金金额
                        if( $('#prinAmt').val()){
                            $('#prinAmt').attr('disabled', true);
                        }
                    }else{
                        $('#prinAmt').val('');
                        $('#prinAmt').attr('disabled', false);
                    }
                    if (result.reporate && result.reporate != ''){
                        var number =   result.reporate*100;
                        $('#rebackPrice').val(number).trigger('blur');//回购利率%
                        if($('#rebackPrice').val()){
                            $('#rebackPrice').attr('disabled', true);
                        }
                    }else{
                        $('#rebackPrice').val('');
                        $('#rebackPrice').attr('disabled', false);
                    }
                    if (result.repointamt && result.repointamt != ''){
                        $('#rebackIntAmt').val(result.repointamt).trigger('blur');//回购利息
                        if($('#rebackIntAmt').val()){
                            $('#rebackIntAmt').attr('disabled', true);
                        }
                    }else{
                        $('#rebackIntAmt').val('');
                        $('#rebackIntAmt').attr('disabled', false);
                    }
                    if (result.mdealamt && result.mdealamt != ''){
                        $('#mdateSettleAmt').val(result.mdealamt).trigger('blur');//到期结算金额
                        if($('#mdateSettleAmt').val()){
                            $('#mdateSettleAmt').attr('disabled', true);
                        }
                    }else{
                        $('#mdateSettleAmt').val('');
                        $('#tradeAmtAndFee').val('');
                        $('#mdateSettleAmt').attr('disabled', false);
                        $('#tradeAmtAndFee').attr('disabled', false);
                    }
                    if (result.seller && result.seller != ''){
                        $('#trdInvestPersonInfo_counterId').textSelect('setValue', result.seller);// 交易对手
                        if( $('#trdInvestPersonInfo_counterId').val()){
                            $('#trdInvestPersonInfo_counterId').attr('disabled', true);
                        }
                    }else{
                        deleteSelect('trdInvestPersonInfo_counterId');
                        $('#trdInvestPersonInfo_counterId').attr('disabled', false);
                    }
                    if (result.selltrader && result.selltrader != ''){
                        $('#trdInvestPersonInfo_counterTrader').val(result.selltrader);// 对手方交易员
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
                    if (result.facetotalamt && result.facetotalamt != ''){
                        $('#bussRepoSec_totalPrinAmt').val(result.facetotalamt).trigger('blur');//汇总券面总额
                        if($('#bussRepoSec_totalPrinAmt').val()){
                            $('#bussRepoSec_totalPrinAmt').attr('disabled', true);
                        }
                    }else {
                        $('#bussRepoSec_totalPrinAmt').val('');
                        $('#bussRepoSec_totalPrinAmt').attr('disabled', false);
                    }
                    if (result.dealtext && result.dealtext != ''){
                        $('#remark').val(result.dealtext).trigger('change');// 备注
                    }else{
                        $('#remark').val('');
                    }
                    if (result.market && result.market != ''){
                        $('#tradeMarket').val(result.market).trigger('change');// 交易市场
                    }else{
                        deleteSelect('settleSpeedStr');
                        deleteSelect('tradeMarket');
                        $('#settleSpeedStr').attr('disabled', false);
                    }
                    if (result.termType && result.termType != ''){
                        $('#repoType').val('H01');
                        $('#termTypeReback').val(result.termType).trigger('change');// 期限品种
                    }else{
                        deleteSelect('termTypeReback');
                    }
                    if(result.settleSpeed && result.settleSpeed != ''){
                        $('#settleSpeedStr').val(result.settleSpeed);// 清算速度
                        if($('#settleSpeedStr').val()){
                            $('#settleSpeedStr').attr('disabled', true);
                        }
                    }else{
                        $('#settleSpeedStr').val('');
                        $('#settleSpeedStr').attr('disabled', false);
                    }
                    if(result.cashHoldingDays && result.cashHoldingDays != ''){
                        $('#actCapDays').val(result.cashHoldingDays);// 实际占款天数
                        if($('#actCapDays').val()){
                            $('#actCapDays').attr('disabled', true);
                        }
                    }else{
                        $('#actCapDays').val('');
                        $('#actCapDays').attr('disabled', false);
                    }

                    if (_.isArray(result.pledgeBondInfoDtoList)){                    	
                        if (result.prodtype != TARGET_BACK_TYPE_H02) {
                            if (window && window.BussRepoSec && window.BussRepoSec["bussRepoSec"]) {
                                window.BussRepoSec["bussRepoSec"].reloadPledgeBond(result.pledgeBondInfoDtoList);
                            }
                        }
                    }else{
                        $('#vpayDate').val('');
                        if (window && window.BussRepoSec && window.BussRepoSec["bussRepoSec"]) {
                            window.BussRepoSec["bussRepoSec"].reloadPledgeBond('');
                        }
                    }
                }
            })
        }else if (isInit){
            if ( $('#tradeDate').val()){
                $('#tradeDate').val($('#formatDate').val());
                $('#tradeDate').attr('disabled', true);
            }
            if ( $('#settleSpeedStr').val()){
                $('#settleSpeedStr').val('');
                $('#settleSpeedStr').attr('disabled', false);
            }
            // if ( $('#portfolioId').val()){
            //     $('#bussTrdAvailableCashQuery_avalibleCash').val('');
            //     $('#bussTrdAvailableCashQuery_avalibleCashT1').val('');
            //     deleteSelect('portfolioId');
            //     $('#portfolioId').attr('disabled', false);
            // }
            if (  $('#secAcctId').val()){
                deleteSelect('secAcctId');
                deleteSelect('chlId');
                $('#secAcctId').attr('disabled', false);
                $('#chlId').attr('disabled', false);
            }
            if ( $('#repoType').val()){
                deleteSelect('repoType');
                $('#repoType').attr('disabled', false);
            }
            if( $('#tradeMarket').val()){
                deleteSelect('tradeMarket');
                deleteSelect('calendarId');
                $('#tradeMarket').attr('disabled', false);
                $('#calendarId').attr('disabled', false);
            }

            if ( $('#ps').val()){
                deleteSelect('ps');
                $('#ps').attr('disabled', false);
            }
            if ( $('#vdate').val()){
                $('moneyAvaDate').val('');
                $('vpayDate').val('');
                $('#vdate').val('');
            }
            if ( $('#mdate').val()){
                $('#actCapDays').val('');
                $('#avaFundsDate').val('');
                $('#mpayDate').val('');
                $('#mdate').val('');
            }
            if ($('#term').val()){
                $('#term').val('');
                $('#term').attr('disabled', false);
            }
            if ( $('#basis').val()){
                /*deleteSelect('basis');
                $('#basis').attr('disabled', false);*/
                $('#basis').val(BASIS_A_365).trigger('change');
            }
            if ( $('#ccy').val()){
                deleteSelect('ccy');
                $('#ccy').attr('disabled', false);
            }
            if ( $('#prinAmt').val()){
                $('#prinAmt').val('');
                $('#prinAmt').attr('disabled', false);
            }
            if ( $('#rebackPrice').val()){
                $('#rebackPrice').val('');
                $('#rebackPrice').attr('disabled', false);
            }
            if ( $('#rebackIntAmt').val()){
                $('#rebackIntAmt').val('');
                $('#rebackIntAmt').attr('disabled', false);
            }
            if (  $('#mdateSettleAmt').val()){
                $('#mdateSettleAmt').val('');
                $('#tradeAmtAndFee').val('');
                $('#mdateSettleAmt').attr('disabled', false);
                $('#tradeAmtAndFee').attr('disabled', false);
            }
            if (  $('#trdInvestPersonInfo_counterId').val()){
                deleteSelect('trdInvestPersonInfo_counterId');
                $('#trdInvestPersonInfo_counterId').attr('disabled', false);
            }
            if ( $('#trdInvestPersonInfo_counterTrader').val()){
                $('#trdInvestPersonInfo_counterTrader').val('');
                $('#trdInvestPersonInfo_counterTrader').attr('disabled', false);
            }
            if ( $('#trdInvestPersonInfo_contactInfo').val()){
                $('#trdInvestPersonInfo_contactInfo').val('');
                $('#trdInvestPersonInfo_contactInfo').attr('disabled', false);
            }
            if ( $('#bussRepoSec_totalPrinAmt').val()){
                $('#bussRepoSec_totalPrinAmt').val('');
                $('#bussRepoSec_totalPrinAmt').attr('disabled', false);
            }
            if ( $('#remark').val()){
                $('#remark').val('');
            }
            if ($('#tradeMarket').val()){
                deleteSelect('settleSpeedStr');
                deleteSelect('tradeMarket');
                $('#settleSpeedStr').attr('disabled', false);
            }
            if ( $('#termTypeReback').val()){
                deleteSelect('termTypeReback');
            }
            if($('#settleSpeedStr').val()){
                deleteSelect('settleSpeedStr');
            }
            $('#vpayDate').val('');
            if (window && window.BussRepoSecPtl && window.BussRepoSecPtl["bussRepoSecPtl"]) {
             window.BussRepoSecPtl["bussRepoSecPtl"].reloadPledgeBond('');
            }
            setTimeout(function () {
                $('#bussTrdAvailableCashQuery_avalibleCash').val('');
                $('#bussTrdAvailableCashQuery_avalibleCashT1').val('');
            },1500)

        }
    };

    var deleteSelect = function (id) {
        $('#'+id).val('').trigger('change');
    }

  /*  $('body').on('change','#ps',function(event,data){
        window.BussRepoSecPtl["bussRepoSecPtl"].showCash();
    });*/
}());