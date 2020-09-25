(function () {

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

    }

    //画面初始化
    var init = function() {
        exerYieldDivContrl();
        if(pageModel == '3'){
            $("#replyType_div").css('display', 'block');
        }

    }

    function getCalParam(){
        var paramObj = $.serializeToObject('#submitForm', true);
        paramObj.calculateField = $("[name=calculateField]:checked").val();
        //券面总额
        paramObj.prinamt = $("#prinamt").val();
        //净价
        paramObj.cleanprice = $("#cleanprice").val();
        //应计利息
        paramObj.accuir = $("#accuir").val();
        //应计利息总额
        paramObj.accuiramt = $("#accuiramt").val();
        //全价
        paramObj.dirtyprice = $("#dirtyprice").val();
        //到期收益率
        paramObj.matureyield = $("#matureyield").val();
        //行权收益率
        paramObj.exerciseyield = $("#exerciseyield").val();
        //全价总额
        paramObj.dpriceamt = $("#dpriceamt").val();
        //净价总额
        paramObj.cpriceamt = $("#cpriceamt").val();

        // 行权计算时用数据库中的理论行权日 add by renping 20191105 start
        if(paramObj.finprodId && ($("#ps").val() == PS_T03 || $("#ps").val() == PS_T39)) {
            Joyin.ajaxJson(JoyinUtil.getRealPath('/interf/cfetsup/secdialogquote/FxSecdialogQuote002/getExerciseDate'), paramObj, function (result) {
                if (result && result.data) {
                    paramObj.settleDate = result.data;
                }
            }, false);
        }
        // 行权计算时用数据库中的理论行权日 add by renping 20191105 end
        return paramObj;
    }

    $("#FxSecdialogQuote002_btn_calculate").click(function () {

        var paramObj = getCalParam();
        Joyin.ajaxJson(JoyinUtil.getRealPath('/interf/cfetsup/secdialogquote/FxSecdialogQuote002/calculateData'),paramObj,function(result){
            if(result && result.data){
                var data = result.data;
                $("#prinamt").val(data.prinamt);
                $("#cleanprice").val(data.cleanprice);
                $("#accuir").val(data.accuir);
                $("#accuiramt").val(data.accuiramt);
                $("#dirtyprice").val(data.dirtyprice);
                $("#matureyield").val(data.matureyield);
                $("#exerciseyield").val(data.exerciseyield);
                $("#dpriceamt").val(data.dpriceamt);
                $("#cpriceamt").val(data.cpriceamt);
            }
        });

    });

    //组合代码联动
    $("#portfolioId").change(function(){
        portfolioIdChange();
        getUsableCash();
    });

    var portfolioIdChange = function(){
        if($("#portfolioId").val() != null && $("#portfolioId").val() != ''){
            $("#portfolioName").val($("#portfolioId").select2("data")[0].object);
        }else{
            $("#portfolioName").val('');
        }
    }

    $("#ps").on("change",function(){
        var ps = $("#ps").val();
        if(ps){
            if(ps =='P'){
                $("#avaliblecash_div").hide();
                $("#avalibleholdposition_div").hide();
            }else{
                $("#avaliblecash_div").show();
                $("#avalibleholdposition_div").show();
            }
        }
    });

    //债券代码联动
    $("#seccode").change(function(){
        //债券简称显示
        secChange();
        // 控制显示行权收益率
        exerYieldDivContrl();
    });

    var secChange = function(){
        if($("#seccode").val() != null && $("#seccode").val() != ''){
           $("#secname").val($("#seccode").select2("data")[0].object);
        }else{
            $("#secname").val('');
        }
    }

    /**
     * 控制显示行权收益率
     */
    var exerYieldDivContrl = function() {
        // 11、  是否含权债为是，且未来有行权计划时，行权收益率显示，其余情况不显示；
        var paramObj_isExerYieldDivShow = {};
        paramObj_isExerYieldDivShow.seccode = $('#seccode').val();
        paramObj_isExerYieldDivShow.tradeDate = $('#dealdate').val();
        Joyin.ajaxSync(basePath + 'interf/cfetsup/secdialogquote/FxSecdialogQuote002/isExerYieldDivShow', paramObj_isExerYieldDivShow, function (data) {
            if (data == 'Y') {
                $("#exerciseyieldDiv").show();
            } else {
                $("#exerciseyieldDiv").hide();
            }
        });
    };

    /**
     * 查询组合T日可用现金
     */
    function getUsableCash() {
        var tradeDate = $("#dealdate").val();
        if(tradeDate == null || tradeDate == ''){
            return;
        }
        var portfolioId = $("#portfolioId").val();
        if(portfolioId == null || portfolioId == ''){
            return;
        }
        var object = {
            tradeDate : tradeDate,
            portfolioId : portfolioId,
            ccy: 'CNY',
            calendarId:TRANSACTION_CALENDAR
        }
        Joyin.ajaxJson(basePath + 'component/trade/BussTrdAvailableCashQuery/getAvailableCash', object, function(result) {
            console.log(result);
            if(result){
                $("#avaliblecash").val(result.T_0);
            }
        });
    }

    /**
     * 1.4清算速度、交易日、交割日之间有联动关系
     */
    $("#settlespeed").on("change", function () {
        calcSettleDate();
    });
    /**
     * 交易日历变化，联动交割日
     */
    $("#calendarId").on("change", function () {
        calcSettleDate();
    });

    /**
     * 计算交割日
     */
    var calcSettleDate = function() {
        var settlespeed = $("#settlespeed").val();
        if (settlespeed == '01') {
            $("#settledate").val($("#dealdate").val());
        }
        else if(settlespeed == '02') {
                Joyin.ajaxSync(basePath + 'interf/cfetsup/secdialogquote/FxSecdialogQuote002/getNextWorkDay', {
                    'calendarId': $("#calendarId").val(),
                    'dealdate': $("#dealdate").val()
                }, function (date) {
                    $("#settledate").val(date);
                });
        }else if(settlespeed == '03'){
                var dealdate= DateUtil.parse($("#dealdate").val()).DateAdd('d', 1).Format('yyyy-MM-dd')
                Joyin.ajaxSync(basePath + 'interf/cfetsup/secdialogquote/FxSecdialogQuote002/getNextWorkDay', {
                    'calendarId': $("#calendarId").val(),
                    'dealdate': dealdate
                }, function (date) {
                    $("#settledate").val(date);
                });
        }
        else {
            $("#settledate").val("");
        }

    };

    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm', true);
        object.pageModel = pageModel;
        object.btnOperateType = operateType;
        object.operateType = pageModel;
        Joyin.ajaxJson(basePath + 'interf/cfetsup/secdialogquote/FxSecdialogQuote002/check', object, function (result) {
            Joyin.confirm(result.data, function () {
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'interf/cfetsup/secdialogquote/FxSecdialogQuote002/save', object, "FxSecdialogQuote002_btn_save");
                } else {
                    WorkFlow.doSubmitList(basePath + 'interf/cfetsup/secdialogquote/FxSecdialogQuote002/save', object, "FxSecdialogQuote002_btn_submit");
                }
            });
        },false);
    }
    /**
     * 画面加载完成
     */
    $(function() {

        //初始化画面
        init();

        // 绑定按钮事件
        $('#FxSecdialogQuote002_btn_save').click(function () {
            saveOrSubmit('save');
        });
        $('#FxSecdialogQuote002_btn_submit').click(function () {
            saveOrSubmit('submit');
        });

    });


}());
