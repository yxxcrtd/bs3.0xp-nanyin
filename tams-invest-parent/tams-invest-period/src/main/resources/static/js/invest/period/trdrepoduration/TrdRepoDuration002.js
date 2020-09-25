(function () {
    /**
     * 页面初始化
     */
    var init = function() {
        if ($('#type6').val()==repoType_jjrepo){
            $('#repoInfoTable_div').hide();
        } else {
            $('#repoInfoTable_div').show();
        }
        if (bustype==='T56'){
            $('#endInfoTable_div').show();
            //深交所 协议 期限品种非必填
            if($("#tradeMarket").val()==TRADE_PLACE_04){
                $("#termTypeReback").val("");
                $('#termTypeReback').prop('disabled', true);
            }else{
                $("#termTypeReback").attr("required",true);
                $("#termTypeReback").prop("disabled", false);
            }

        } else {
            $('#endInfoTable_div').hide();
        }
        //逾期返售
        //5、	银行间手续费默认200，不随交易支付；
        if($("#ps").val() == PS_YQFS){
            //到期结算金额		必填	文本	文本框	自动带入，不可修改
            $("#fpriceAmt").attr("disabled","true");
            if($("#tradeMarket").val()==JYCS_YHJ&&!$("#actTradeFee").val()){
                $("#actTradeFee").val("200");
                $("#actTradeFee").trigger("change");
            }
            if($("#tradeMarket").val()!=JYCS_YHJ){
                $("#actTradeFee").attr("disabled","true");
            }
        }
        if($("#tradeMarket").val()==JYCS_YHJ){ 
        	 $(".type6Wrap").hide();
        	 $("#repoTypeDiv").hide();
        }else{
        	 $(".type6Wrap").show();
        	 $("#repoTypeDiv").show();
        }
        if($("#chlId").val()){
            $("#channelFee_div").show();
        }

    }




    /**
     * 定义数据表格
     */
    var repoInfoTable = $("#repoInfoTable").joyintable({
        rowno: true,
        searchOnInit:true,
        ajax: {
            url: basePath + 'invest/period/trdrepoduration/TrdRepoDuration002/findRepoInfo',
            type: 'POST',
            data: {finprodId: finprodId}
        },
        checkbox: false,
        page: false,
        cols: [
            {field: 'finprodMarketId', width: "23%", title: '债券代码'}
            , {field: 'finprodAbbr', width: "23%", title: '债券简称'}
            , {field: 'qty', width: "23%", title: '质押券面总额', type: 'money'}
            , {field: 'pledgeRatio', width: "23%", title: '折算比例(%)', type: 'money'}
        ]
    });

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
        // 划款指令参数变更校验
        if(window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()){
            return ;
        }
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#informationForm', true);
        //文件信息
        if(FileCommon) {
            object.uploadFileList = FileCommon.getUploadFileInfo();
        }
        if(!(object.bussTrdTransferOrder)||object.bussTrdTransferOrder.length==0){
            return Joyin.alert(JoyinUtil.getMessage("PTL_COM_E_0007"),2);
        }
        object.operateType = operateType;
        if($("#chlId").val()){
        	object.collectType = $("#chlId").select2("data")[0].object.collectType;
        }
        object.pageModel = PAGE_MODEL_NEW;
        pageModel = PAGE_MODEL_NEW;
        Joyin.ajaxJson(basePath + 'invest/period/trdrepoduration/TrdRepoDuration002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/period/trdrepoduration/TrdRepoDuration002/save', object, "TrdRepoDuration002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/period/trdrepoduration/TrdRepoDuration002/save', object, "TrdRepoDuration002_btn_submit");
                }
            });
        });
    }

    /**
     *	计算通道费
     * @returns
     */
    function tradeAmtChange (){
        var tradeAmt = '';
    	 if($("#chlId").val()&&$("#chlId").select2("data")[0].object.collectType==Collect_Type_01){
             tradeAmt = JoyinMath.doMinus($("#fpriceAmt").val(), $("#channelFee").val(), 2);
    	 }else{
             tradeAmt = $("#fpriceAmt").val();
    	 }
        var penaltyIntamt = $("#penaltyIntamt").val();
    	 if(penaltyIntamt){
             tradeAmt = JoyinMath.doAdd(tradeAmt,penaltyIntamt,2);
         }
        $('#tradeAmt').val(tradeAmt);
    }

    /**
     *	计算到期结算金额
     * @returns
     */
    function calFpriceAmt (){
        //提前终止利率
        var terminateRate = $("#terminateRate").val();
        //本金金额
        var theoryPrin = $("#theoryPrin").val();
        // 终止日
        var deadDate = $("#deadDate").val();
        //起息日
        var vDate = $("#vDate").val();
        if(terminateRate){
            // 提前终止时到期结算金额=本金金额+本金金额*提前终止利率*（终止日-起息日）/365，允许修改；
            var fpriceAmt = JoyinMath.doAdd(theoryPrin,JoyinMath.doMul(JoyinMath.doMul(theoryPrin,JoyinMath.doDiv(terminateRate,100,2)),
                JoyinMath.doDiv(DateUtil.daysBetween(deadDate,vDate),365,15)));
            $("#fpriceAmt").val(fpriceAmt);
            $("#fpriceAmt").trigger("change");
        }
    }
    window.componentLoadCallback = function(isDone, pagePath, params) {
        if (isDone) {
            window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                var deal = JSON.parse(dealString);
                var ieFlag=null;
                if (bustype === PS_DQXZ) {//到期续做时，出轧差值
                    deal.tradeAmt = $("#setAmt").val();
                    deal.ps=PS_DQXZ;
                    var prinAmt = $("#theoryPrinex").val();//续做本金
                    var commissionAmt = $("#commissionAmt").val();//佣金
                    var fpriceAmt=$("#fpriceAmt").val();//到期结算金额
                    if(prinAmt==null  || prinAmt==""){
                        prinAmt=0;
                    }
                    if(commissionAmt==null || commissionAmt==""){
                        commissionAmt=0;
                    }
                    if(fpriceAmt==null || fpriceAmt==""){
                        fpriceAmt=0;
                    }
                    let temp;
                    if (finprodtye === finprotype02) {//正回购 5、	交易方向为正回购时，结算金额=|到期结算金额-（续做本金-佣金-通道费）|
                        if($("#isPayWithTrade").val() && $("#isPayWithTrade").val()=== YES_OR_NO_N){//通道费用支付方式为回款前扣除
                            temp = Number(fpriceAmt) - Number(prinAmt);
                        }else{
                            temp = Number(fpriceAmt) - (Number(prinAmt) - Number(commissionAmt));
                        }
                       if(temp>0){
                           ieFlag= IE_FLAG_02;//为应付金额，收支方向为支出
                           $('#ieFlag').val(IE_FLAG_02).trigger("blur");
                       }else {
                           ieFlag= IE_FLAG_01;//为应收金额，收支方向为收入
                           $('#ieFlag').val(IE_FLAG_01).trigger("blur");
                       }
                    } else if(finprodtye ===finprotype03) {//逆回购
                        if ($("#isPayWithTrade").val() && $("#isPayWithTrade").val()=== YES_OR_NO_N) {
                            temp = Number(fpriceAmt) - Number(prinAmt);
                        } else {
                            temp = Number(fpriceAmt) - (Number(prinAmt) + Number(commissionAmt));
                        }
                        if(temp>0){
                            ieFlag= IE_FLAG_01;//为应收金额，收支方向为收入
                            $('#ieFlag').val(IE_FLAG_01).trigger("blur");
                        }else {
                            ieFlag= IE_FLAG_02;//为应付金额，收支方向为支出
                            $('#ieFlag').val(IE_FLAG_02).trigger("blur");
                        }
                    }
                } else {
                    deal.tradeAmt = $("#tradeAmt").val();
                    }
                //提前终止使用终止日生成划款指令
                deal.settleDate = $("#ps").val()==PS_TQZZ ?$("#deadDate").val():$("#settleDate").val();
                // 通道交易 新增 强成西 2019/03/26 start
                deal.chlId = $("#chlId").val();
                // 通道交易 新增 强成西 2019/03/26 end
                return {
                    'isTotalTrade': YES_OR_NO_Y
                    , 'bankLendingType': null
                    , 'portfolioId': portfolioId
                    , 'deal':deal
                    ,'ieFlag':ieFlag
                };

            });
            if (formTodoList) {
                window.BussTrdTransferOrder['bussTrdTransferOrder'].saveCalcCondition();
            }
        }
    }

    /**
     * 画面加载完成
     */
    $(function () {
        init();
        $('#TrdRepoDuration002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TrdRepoDuration002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $('#fpriceAmt').on('change',function() {
            tradeAmtChange();
        });
        $('#channelFee').on('change',function() {
            tradeAmtChange();
        });

        //提前到期时增加 到期率的计算
        if($("#ps").val() == PS_TQZZ){
            $('#terminateRate').on('change',function() {
                calFpriceAmt();
            });
        }
        //逾期返售 罚息金额
        if($("#ps").val() == PS_YQFS){
            //4、	到期结算金额+罚息金额=实际结算金额，罚息金额和实际结算金额输入一个自动联动另一个；
            //实际结算金额
            $('#tradeAmt').on('change',function() {
                var fpriceAmt = $("#fpriceAmt").val();
                var tradeAmt = $('#tradeAmt').val();
                if(fpriceAmt&&tradeAmt){
                    $('#penaltyIntamt').val(JoyinMath.doMinus(tradeAmt,fpriceAmt));
                    $('#penaltyIntamt').trigger("blur");
                }
            });
             //    罚息金额
            $('#penaltyIntamt').on('change',function() {
                tradeAmtChange ();
            });
        }

        $('#termex').on("change", function () {
            // 回购期限改变时，自动计算续做到期日
            termexOnChangeCalcTerm();
            refreshRebackIntAmt();
        });
        // 续做本金金额、续做回购利息改变时,自动计算续做到期结算金额
        $('#theoryPrinex,#theoryIntex').on("change", function(){
            prinAmtRebackIntAmtOnChange();
            tradeAmtCommissionAmtOnChange();
            calFee();//算佣金
        });
        // 续做本金金额、回购利率,续做到期日改变时，刷新续做回购利息
        $('#theoryPrinex, #intRatex, #mDatex').on("change", function () {
            calFee();//算佣金
            refreshRebackIntAmt();
        });
        // 到期结算金额，佣金刷新结算金额
        $('#fpriceAmt,#commissionAmt').on("change", function () {
            tradeAmtCommissionAmtOnChange();
        });
        $('#termTypeReback').on("change", function () {
            calFee();//算佣金费
        termTypeChangeAction();
        });
    });

    /**
     * 回购期限改变时，自动计算到期日
     */
    var termexOnChangeCalcTerm = function () {
        var mdate = $("#mDate").val();
        var terme = $("#termex").val();
        if(terme===undefined || terme===''){
            terme=0;
        }
        if (terme && mdate) {
            var tradeDate = addDate(mdate, terme);
            $("#mDatex").val(tradeDate);
        }else{
            $("#mDatex").val(mdate);
        }
    };
    /**
     * 续做本金金额、续做回购利息改变时,自动计算续做到期结算金额
     */
    var prinAmtRebackIntAmtOnChange = function () {
        var prinAmt = $("#theoryPrinex").val();
        var rebackIntAmt = $("#theoryIntex").val();
        if (prinAmt && rebackIntAmt) {
            $("#tradeAmtex").val((Number(prinAmt) + Number(rebackIntAmt)).toFixed(2)).trigger("blur");
        }
    };

    /**
     * 续做本金金额、续做回购利率、续做到期日改变时，刷新续做回购利息
     */
    var refreshRebackIntAmt = function () {
        var prinAmt = $("#theoryPrinex").val();
        var rebackPrice = $("#intRatex").val();
        var vdate=$("#mDate").val();
        var mdate=$("#mDatex").val();
        var actCapDays="";
        if(vdate && mdate){
            Joyin.ajaxSync(basePath + 'invest/period/trdrepoduration/TrdRepoDuration002/getBetweenDays',
                {vdate:vdate,mdate:mdate},
                function (data) {
                    if(data){
                        actCapDays=data;
                    }
                });
        }
        var basis = $("#basis").val();
        if (prinAmt && rebackPrice && actCapDays && basis) {
            var number = JoyinMath.doMul(prinAmt,rebackPrice,8);
            var num =  JoyinMath.doMul(JoyinMath.doDiv(number,100,8),actCapDays,8);
            var result = JoyinMath.doDiv(num,basis === "A/360" ? 360 : 365,2);
            $("#theoryIntex").val(result).trigger("change").trigger("blur");
        }

    };

    // 续做到期结算金额、佣金，续做本金 改变时,自动计算结算金额
    var tradeAmtCommissionAmtOnChange = function () {
        var prinAmt = $("#theoryPrinex").val();//续做本金
        var commissionAmt = $("#commissionAmt").val();//佣金
        var fpriceAmt=$("#fpriceAmt").val();//到期结算金额
        if(prinAmt==null  || prinAmt==""){
            prinAmt=0;
        }
        if(commissionAmt==null || commissionAmt==""){
            commissionAmt=0;
        }
        if(fpriceAmt==null || fpriceAmt==""){
            fpriceAmt=0;
        }
        if(channelFee==null || channelFee==""){
            channelFee=0;
        }
        let temp;
        if (finprodtye === finprotype02) {//正回购 5、	交易方向为正回购时，结算金额=|到期结算金额-（续做本金-佣金-通道费）|
            if($("#isPayWithTrade").val() && $("#isPayWithTrade").val()===YES_OR_NO_N){//佣金支付方式为随佣金支付
                temp = Number(fpriceAmt) - (Number(prinAmt));
            }else{
                temp = Number(fpriceAmt) - (Number(prinAmt) - Number(commissionAmt));
            }
            if(temp>0){
                ieFlag= IE_FLAG_02;//为应付金额，收支方向为支出
                $('#ieFlag').val(IE_FLAG_02).trigger("blur");
            }else {
                ieFlag= IE_FLAG_01;//为应收金额，收支方向为收入
                $('#ieFlag').val(IE_FLAG_01).trigger("blur");
            }
            $("#setAmt").val(Math.abs(temp).toFixed(2)).trigger("blur");
        } else {
            if ($("#isPayWithTrade").val() && $("#isPayWithTrade").val()=== YES_OR_NO_N) {
                temp = Number(fpriceAmt) - (Number(prinAmt));
            } else {
                temp = Number(fpriceAmt) - (Number(prinAmt) + Number(commissionAmt));
            }
            if(temp>0){
                ieFlag= IE_FLAG_01;//为应收金额，收支方向为收入
                $('#ieFlag').val(IE_FLAG_01).trigger("blur");
            }else {
                ieFlag= IE_FLAG_02;//为应付金额，收支方向为支出
                $('#ieFlag').val(IE_FLAG_02).trigger("blur");
            }
            $("#setAmt").val(Math.abs(temp).toFixed(2)).trigger("blur");
        }

    };

    //日期加减
    function addDate(date,days){
        if(days == undefined || days == '') {
            days = 1;
        }
        var date = new Date(date);
        date = date.DateAdd('d', days);
        return date.format();
    };

    /**
     * 期限品种联动期限
     */
    var termTypeChangeAction = function(){
        var termType = $('#termTypeReback').val();
        if ($('#termTypeReback').val()){
            var repoterm = '';
            if (termType == R001){
                repoterm = 1;
            }else if (termType == R007){
                repoterm = 7;
            }else if (termType == R014){
                repoterm = 14;
            }else if (termType == R021){
                repoterm = 21;
            }else if (termType == R1M){
                repoterm = 30;
            }else if (termType == R2M){
                repoterm = 60;
            }else if (termType == R3M){
                repoterm = 90;
            }else if (termType == R4M){
                repoterm = 120;
            }else if (termType == R6M){
                repoterm = 180;
            }else if (termType == R9M){
                repoterm = 270;
            }
            $('#termex').val(repoterm).trigger('change');
        }
    };

    /**
     * 计算费用
     */
    var calFee = function(){
        if(!$("#mDate").val()){
            return;
        }
        if(secManageAcctId==null || secManageAcctId===""){//证券户为空不在计算
            return;
        }

        if(!$("#theoryPrinex").val()){
            return;
        }
        if($("#tradeMarket").val() == JYCS_YHJ){
            return;
        }
        if (pageModel == PAGE_MODEL_REFERENCE){
            // 审批人审批或者生效的交易不再重新计算
            return;
        }
        var theoryPrinex = $("#theoryPrinex").val();//续做本金
        var mDate= $("#mDate").val();//到期日-首期交易的起息日
        var tradeMarket=$("#tradeMarket").val();//交易场所
        var termTypeReback=$("#termTypeReback").val();//费用品种
        var object = {mDate:mDate,
            theoryPrinex:theoryPrinex,
            tradeMarket:tradeMarket,
            termTypeReback:termTypeReback,
            secAcctId:secManageAcctId,
            finProdtye2:finprodtye
        };

        Joyin.ajaxSync('/invest/period/trdrepoduration/TrdRepoDuration002/getFeeDetail', object, function (data) {
            $("#isPayWithTrade").val(data.commission.isPayWithTrade).trigger("blur");
                if(pageModel==PAGE_MODEL_EDIT) {
                        $("#commissionAmt").val(data.commission.feeAmt).trigger("blur");
                }
        });
    };


}());

