(function () {

    // 委托指令dto
    var etrEntrust;
    // 委托指令附属表dto
    var etrEntrustAdd;

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

    var checkAndDisabled = function(el){
        if($(el).val()){
            $(el).attr("disabled",true);
        }
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
        var object = $.serializeToObject('#submitForm', true);
        object.pageModel = pageModel;
        object.operateType = operateType;
//        object.isGuarantee = $('#portfolioId').select2('data')[0].object; // 是否保本
     /*   if (FileCommon) {
            object.uploadFileList = FileCommon.getUploadFileInfo();
        }*/
        // 委托金额
        object.entrustAmt = entrustAmt;
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

        // 资产可用持仓
        object.assetPosition = $("#bussTrdAvailableCashQuery_assetPosition").val();
        Joyin.ajaxJson(basePath + 'invest/trade/pretrdrepotrade/PreTrdRepoTrade002/check', object, function (result) {
            Joyin.confirm(result.data, function () {
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/trade/pretrdrepotrade/PreTrdRepoTrade002/save', object, "PreTrdRepoTrade002_btn_save","PreTrdRepoTrade002");
                } else {
                    WorkFlow.doSubmitList(basePath + 'invest/trade/pretrdrepotrade/PreTrdRepoTrade002/save', object, "PreTrdRepoTrade002_btn_submit","PreTrdRepoTrade002");
                }
            });
        },false);
    }


    function extractedTableRendow() {
        if (BACK_LEND_DIRECTION_04 == $("#ps").val()) {//逆回购
            if (window.BussRepoSecPtl['bussRepoSecPtl'].pledgeBondTotalTbl.table) {
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
                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.showColumns([{field:'trdAcctAbleCash',visible: false}]);
                window.BussRepoSecPtl["bussRepoSecPtl"].pledgeBondTotalTbl.clear();
            }
        }
    }
    /**
     * 设置添加质押券共通参数
     */
    var setBussRepoSecInput = function () {
        var data = {
            portfolioId: $('#portfolioId').val(),
            ps: $("#ps").val(),
            secAcctId: $('#secAcctId').val(),
            term: $("#repoTerm").val(),
            basis: $("#basis").val(),
            prinAmt: $("#prinAmt").val(),
            rebackPrice: $("#entrustPrice").val(),
            rebackIntAmt: $("#rebackIntAmt").val(),
            mdateSettleAmt: $("#mdateSettleAmt").val(),
            tradeAmtAndFee: $("#tradeamt").val(),
            calendarId: $("#calendarId").val(),
            ccy: $("#ccy").val(),
            tradeDate: $("#tradeDate").val(),
        }
        var object = {
            portfolioId: ['portfolioId', 'TrdRepoTrade002.portfolioId'],
            ps: ['ps', 'TrdRepoTrade002.ps'],
            secAcctId: ['secAcctId', 'TrdRepoTrade002.secAcctId'],

            term: ['term', 'TrdRepoTrade002.term'],
            basis: ['basis', 'TrdRepoTrade002.basis'],

            prinAmt: ['prinAmt', 'TrdRepoTrade002.prinAmt'],
            rebackPrice: ['rebackPrice', 'TrdRepoTrade002.rebackPrice'],
            rebackIntAmt: ['rebackIntAmt', 'TrdRepoTrade002.rebackIntAmt'],
            mdateSettleAmt: ['mdateSettleAmt', 'TrdRepoTrade002.mdateSettleAmt'],

            tradeAmtAndFee: ['tradeAmtAndFee', 'TrdRepoTrade002.tradeAmtAndFee'],

            calendarId: $('calendarId','TrdRepoTrade002.calendarId'),
            ccy: $('ccy','TrdRepoTrade002.ccy'),
            tradeDate: $('tradeDate','TrdRepoTrade002.tradeDate'),
            data: data
        };
        window.BussRepoSecPtl["bussRepoSecPtl"].setInputParam(object);
    };
    window.componentLoadCallback = function (isDone, pagePath, params) {
        if (isDone) {
            settleSpeedChangeAction();
            repoTermChangeAction();
            vmdateOnChangeCalcTerm();
            refreshRebackIntAmt();
            prinAmtRebackIntAmtOnChange();

            if($('#isEntrust').val()){
                checkAndDisabled('#ps');
                checkAndDisabled('#repoTerm');
                checkAndDisabled('#mdate');
                checkAndDisabled('#settleSpeedText');
            }
        }
    };
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
        var object = {
            valDate: ['tradeDate', 'TrdRepoTrade002.tradeDate'],
            portfolioCode: ['portfolioId', 'TrdRepoTrade002.portfolioId'],
            tradeAmount: ['prinAmt', 'TrdRepoTrade002.prinAmt'],
            assetType: ['assetType', 'TrdFundInvestTrade001.finprodId'],
            tradeDirection:['ps', 'TrdRepoTrade002.ps'],
            data: list.length>0?list[0]:obj1
        };
        return object;
    };


    /**
     * 根据交易方向，确定资产类型：金融产品类型_投管用_债券质押式正回购、金融产品类型_投管用_债券质押式逆回购
     * @returns
     */
    function getFinProdType2FromPs() {
        return ($("#ps").val() == null || $("#ps").val() == "") ? "" : ($("#ps").val() == BACK_LEND_DIRECTION_03 ? FINPROD_TYPE_TG_F02 : FINPROD_TYPE_TG_F03);
    }
    var settleSpeedChangeAction = function(){
        var settleSpeed = $('#settleSpeedText').val();
        var tradeDate = $('#tradeDate').val();
        if (settleSpeed && settleSpeed != '' && tradeDate && tradeDate != ''){
            var num = 0;
            if (settleSpeed === SETTLESPEED_1){
                num = 1;
            }else if(settleSpeed === SETTLESPEED_2){
                num = 2;
            }
            $('#vdate').val(DateUtil.parse(tradeDate).DateAdd('d', num).Format('yyyy-MM-dd')).trigger('change');
        }

    };

    /**
     * 起息日、到期日改变时，自动计算回购期限
     */
    var vmdateOnChangeCalcTerm = function () {
        var vdate = $("#vdate").val();
        var mdate = $("#mdate").val();
        var calendarId = $('#calendarId').val();
        var re = /^[0-9]*$/;
        var repoterm = $('#repoTerm').val();
        if(!repoterm || !re.test(repoterm)){
            return
        }
        if (vdate  && calendarId) {
            var object={
                vdate:vdate,
                term:$("#repoTerm").val(),
                calendarId:calendarId
            };
            Joyin.ajaxSync(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade003/getMdate',object,
                function (data) {
                    if (data && data.tradeDate && data.tradeDate) {

                        $('#mdate').val(data.tradeDate).trigger('blur').trigger('change');
                        $('#actCapDays').val(data.term).trigger('blur').trigger('change');
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
     * 回购期限
     */
    var repoTermChangeAction = function(){

        var repoterm = $('#repoTerm').val();
        var re = /^[0-9]*$/;
        if(repoterm && re.test(repoterm)){
            var vdate = $('#vdate').val();
            if(vdate){
                //回购期限联动期限品种
                $('#termType').val(getTermType(vdate,repoterm)).trigger('change');
                vmdateOnChangeCalcTerm();
            }
        }else{
            $('#termType').val("").trigger('change');
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
     * 本金金额、回购利率、回购期限、计息基础改变时，刷新回购利息
     */
    var refreshRebackIntAmt = function (varTerm) {
        var prinAmt = $("#prinAmt").val();
        var rebackPrice = $("#entrustPrice").val();
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
     * 回购期限
     */
    $(function () {
        $("#calendarId").textSelect('setValue', '03');
        $('#PreTrdRepoTrade002_btn_save').click(function () {
            saveOrSubmit('save');
        });
        $('#PreTrdRepoTrade002_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
        // 清算速度 / 到期日
        $('#repoTerm').change(function(){
            repoTermChangeAction();
            refreshRebackIntAmt();
            setBussRepoSecInput();
            vmdateOnChangeCalcTerm();
        });

        $('#ps').on("change", function () {

            // 设置添加质押券共通参数
            setBussRepoSecInput();
            extractedTableRendow();
        });

        // 清算速度
        $('#settleSpeedText').change(function(){
            debugger
            settleSpeedChangeAction();
        });

        $('#vdate').on("change", function () {
            // 起息日、到期日改变时，自动计算回购期限
            vmdateOnChangeCalcTerm();
        });
        $('#mdate').on("change", function () {
            //如果是节假日顺延
            if (pageModel != PAGE_MODEL_REFERENCE){
                var mdate = $('#mdate').val();
                var calendarId = $("#calendarId").val();
                $('#actCapDays').val(DateUtil.daysBetween(mdate,$('#vdate').val()));
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
                                $("#mdate").val(date);
                                $('#actCapDays').val(DateUtil.daysBetween(date,$('#vdate').val()));
                            });
                        }
                    },false);
                }
            } 
           
        }); 
        // 本金金额、回购利率、回购期限、计息基础改变时，刷新回购利息
        $('#prinAmt, #entrustPrice,  #basis').on("change", function () {
            refreshRebackIntAmt();
            prinAmtRebackIntAmtOnChange();
            setBussRepoSecInput();
        });
        // TODO:交易所场合，如何根据成交编号，带出成交单上的信息，带出的信息不可修改；(等待接口开发后添加）
        // TODO:风险违规时校验处理（待共通完善后添加）
        // 风险试算
        $('#PreTrdRepoTrade002_btn_risk').click(function () {
            Joyin.ajax(basePath + 'invest/trade/trdrepotrade/TrdRepoTrade003/selectIndex',{},function(result) {
                if (result) {


                    var object1 = getriskdata();


                    //window.BaseRiskMathComm.initBaseRiskMathCommPage(object1);

                }
                //window.BaseRiskMathComm.initBaseRiskMathCommPage2(object1);
                $("#riskMathFlag").val("02")
            });
        });
        var deleteSelect = function (id) {
            $('#'+id).val('').trigger('change');
        }
    })
}());