(function(){
    /*对话和请求按钮点击标志位*/
    var duihuaFlag = false;
    var qingqiuFlag = false;
    //试算结果
    var riskResultdata;
    var cluflag;
    /**
	 * 委托代码
	 */
    var entrustIdChangeAction = function(){
        var entrustId = $('#CfetsRepoDealQuotedPrice002_entrustId').val();
        if(entrustId && entrustId != ''){
            Joyin.ajax(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/queryEntrustInfoByEntrustId', {entrustId : entrustId}, function(result) {
                if (result.data){
                    var data = result.data;
                    $('#CfetsRepoDealQuotedPrice002_portfolioName').attr('disabled', true).textSelect('setValue',data.portfolioId);
                    // 回复操作，或者 回复方修改操作，不覆盖指令信息
                    if((!operation || operation != "CfetsRepoDealQuotedPrice001.reply")
                            && !(operation == 'CfetsRepoDealQuotedPrice001.edit'
                                && $('#CfetsRepoDealQuotedPrice002_quoteRoleType').val() == quoteRoleTypeReply)){
                        $('#CfetsRepoDealQuotedPrice002_mySide').textSelect('setValue',data.partyId);
                        $('#CfetsRepoDealQuotedPrice002_ps').attr("disabled",true).textSelect('setValue',data.ps);
                        if(data.settleSpreed){
                            $('#CfetsRepoDealQuotedPrice002_settleSpreed').attr('disabled', true).textSelect('setValue', data.settleSpreed);
                        }
                        $('#CfetsRepoDealQuotedPrice002_counterId').textSelect('setValue',data.counterId);
                        $('#CfetsRepoDealQuotedPrice002_term').val(data.term).trigger("change");
                        $('#CfetsRepoDealQuotedPrice002_price').val(data.price).trigger("blur");
                        $('#CfetsRepoDealQuotedPrice002_mySide').textSelect('setValue',data.mySide);
                        $('#CfetsRepoDealQuotedPrice002_mySideTrader').textSelect('setValue',data.mySideTrader);
                        $('#CfetsRepoDealQuotedPrice002_counterTrader').textSelect('setValue',data.counterTrader);
                        $('#CfetsRepoDealQuotedPrice002_secAcctId').val(data.secAcctId);
                    }
                }else{
                    $('#CfetsRepoDealQuotedPrice002_portfolioName').attr('disabled', false);
                    $('#CfetsRepoDealQuotedPrice002_mySide').textSelect('setValue',"");
                    $('#CfetsRepoDealQuotedPrice002_ps').textSelect('setValue',"");
                }
            });
        }
        else{
            if(operation != "CfetsRepoDealQuotedPrice001.reply"){
                $('#CfetsRepoDealQuotedPrice002_ps').attr("disabled",false).textSelect('setValue',"");
                $('#CfetsRepoDealQuotedPrice002_portfolioName').attr('disabled', false);
                $('#CfetsRepoDealQuotedPrice002_counterTrader').attr('disabled', false);
                $('#CfetsRepoDealQuotedPrice002_counterId').attr('disabled', false);
                $('#CfetsRepoDealQuotedPrice002_settleSpreed').attr('disabled', false).textSelect('setValue',"");
            }
        }
        if(operation == "CfetsRepoDealQuotedPrice001.reply"){
            var entrustId  = $('#CfetsRepoDealQuotedPrice002_entrustId').val();
            var quoteRoleType = $("#CfetsRepoDealQuotedPrice002_quoteRoleType").val();
            if(quoteRoleType == quoteRoleTypeReply){
                bindReplyFunction();
            }
        }
    };
    
    /**
	 * 组合名称
	 */
    var portfolioNameChangeAction = function(){
    	var portfolioId = $('#CfetsRepoDealQuotedPrice002_portfolioName').val();
		$('#CfetsRepoDealQuotedPrice002_portfolioId').val(portfolioId);
        setBussRepoSecInput();
    }
    /**
     * 本方
     */
    var mySideChangeAction = function(){
        $('#CfetsRepoDealQuotedPrice002_mySideTrader').val('').trigger('change');
        // add by zhangliang 20200630 start
        var mySideTemp = $('#CfetsRepoDealQuotedPrice002_mySide').val();
        if (mySideTemp == mySideInit){
        	$('#CfetsRepoDealQuotedPrice002_mySideTrader').val(mySideTraderInit).trigger('change');
        }else{
            var traderInit = $('#CfetsRepoDealQuotedPrice002_mySide').select2('data')[0].object;
            if(traderInit){
                $('#CfetsRepoDealQuotedPrice002_mySideTrader').textSelect('setValue', traderInit);
            }
        }
    };
    /**
     * 对手方
     */
    var counterIdChangeAction = function(){
        $('#CfetsRepoDealQuotedPrice002_counterTrader').val('').trigger('change');
    };
    var showAmgOrgFunction = function (pledgeBondList){
        var allData = pledgeBondList;
        var showAmtOrg = false; // 是否显示上清和中债的交易金额区分
        if (allData && allData.length>0) {
            var initOrg = allData[0].repRegistOrg;
            for (var i in allData) {
                var row = allData[i];
                if(!showAmtOrg && initOrg != row.repRegistOrg && i>0){
                    showAmtOrg = true;
                }
            }
        }
        $("#CfetsRepoDealQuotedPrice002_amtOrg").toggle(showAmtOrg);
        $("#CfetsRepoDealQuotedPrice002_cbDealAmt").attr("disabled",!showAmtOrg);
        $("#CfetsRepoDealQuotedPrice002_scDealAmt").attr("disabled",!showAmtOrg);
    };
    var dbclickEtr = function (data) {
        if (data.length != 1 || data == null) {
            parent.CfetsRepoDealQuotedPrMain.errorShow("请选择一条指令执行的数据！");
            return;
        } else {
            setEntrust(data);
        }
    };

    /**
     * 根据传入的数据，设置画面元素
     */
    var setEntrust = function (param) {
        duihuaFlag = true;
        if (duihuaFlag) {
            if (param) {
                if (param[0].entrustId) {
                    $("#CfetsRepoDealQuotedPrice002_entrustId").textSelect('setValue', param[0].entrustId);//委托代码
                }
                if (param[0].portfolioId) {
                    $("#CfetsRepoDealQuotedPrice002_portfolioName").textSelect('setValue', param[0].portfolioId);//组合代码
                }
                //交易日期为平台日
                $("#CfetsRepoDealQuotedPrice002_tradeDate").val(platDate);
                if (param[0].ps) {
                    $("#CfetsRepoDealQuotedPrice002_ps").val(param[0].ps).trigger("change");//交易方向
                }
                if (param[0].settleSpreed) {
                    $("#CfetsRepoDealQuotedPrice002_settleSpreed").textSelect('setValue', param[0].settleSpreed);
                }
                if (param[0].term) {
                    $("#CfetsRepoDealQuotedPrice002_term").val(param[0].term).trigger("change");//回购期限
                }
                //交易金额
                if (param[0].avaNum) {
                    $("#CfetsRepoDealQuotedPrice002_dealAmt").val(param[0].avaNum).trigger("change");
                }
                //回购利率
                if (param[0].price) {
                    $("#CfetsRepoDealQuotedPrice002_price").val(param[0].price).trigger("change");
                }
                /*添加限制*/
                $("#CfetsRepoDealQuotedPrice002_entrustId").attr("disabled",true);
                $("#CfetsRepoDealQuotedPrice002_portfolioName").attr("disabled",true);
                $("#CfetsRepoDealQuotedPrice002_tradeDate").attr("disabled",true);
                $("#CfetsRepoDealQuotedPrice002_ps").attr("disabled",true);
                $("#CfetsRepoDealQuotedPrice002_settleSpreed").attr("disabled",true);
                $("#CfetsRepoDealQuotedPrice002_term").attr("disabled",true);
                $("#CfetsRepoDealQuotedPrice002_termType").attr("disabled",true);
                $("#CfetsRepoDealQuotedPrice002_vSettleDate").attr("disabled",true);
                $("#CfetsRepoDealQuotedPrice002_mSettleDate").attr("disabled",true);
                $("#CfetsRepoDealQuotedPrice002_actDays").attr("disabled",true);
                $("#CfetsRepoDealQuotedPrice002_cbDealAmt").attr("disabled",true);
                $("#CfetsRepoDealQuotedPrice002_scDealAmt").attr("disabled",true);
                $("#CfetsRepoDealQuotedPrice002_mIntAmt").attr("disabled",true);
                $("#CfetsRepoDealQuotedPrice002_mTradeAmt").attr("disabled",true);
                /*添加限制*/
            }
        }
    };
    /**
     * 保存按钮按下，添加子画面传回的质押券信息
     */
    var addPledgeBond = function (pledgeRows,tab) {
        // 列表中未勾选
        if (!pledgeRows || pledgeRows.length == 0) {
            Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_E_029"));
            return;
        } else {
            var table;
            if(tab=="repoInfo"){
                table = repoInfoTable;
            }else if(tab=="nrepoInfo"){
                table = nrepoInfoTable;
            }else{
                Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_E_030"));
                return ;
            }
            table.clear(function () {
                var divd = new BigNumber(10000);
                for (var i in pledgeRows) {
                    var row = pledgeRows[i];
                    // 今日已质押券面
                    var pledgeNumber = new BigNumber(row.pledgeNumber?row.pledgeNumber:0);
                    row.pledgeNumber = pledgeNumber.dividedBy(divd);
                    // 剩余可质押面额
                    var avaPledgeNumber = new BigNumber(row.avaPledgeNumber?row.avaPledgeNumber:0);
                    row.avaPledgeNumber = avaPledgeNumber.dividedBy(divd);
                    // 本次质押券面
                    var pledgePrin = new BigNumber(row.pledgePrin?row.pledgePrin:0);
                    row.pledgePrin = pledgePrin.dividedBy(divd);
                    // 折算比例
                    row.pledgeRate = row.pledgeRate ? row.pledgeRate : 100;
                    table.addRow(row);
                }
            });
            calculateAmt(table);
        }
    };
    
    $('#bussRepoSec_totalPrinAmt').change(function(){
    	alert("");
    })
    
    /**
     * 计算券面总额合计，质押券折算总金额，交易金额上清，交易金额中债，交易金额，回购利息，到期结算金额字段
     */
    var calculateAmt = function (table){
        var pledgeRows = table.getAllRows();
        var divd = new BigNumber(1);
        var pledgePrinTotal = new BigNumber(0); // 券面总额合计(万)
        var zzTotalAmt = new BigNumber(0); // 中债登券面总额合计(万)
        var sqTotalAmt = new BigNumber(0); // 上清所券面总额合计(万)
        var shareCtAmt = new BigNumber(0); // 质押券折算总金额
        var cbShareCtAmt = new BigNumber(0); // 质押券折算总金额中债
        var scShareCtAmt = new BigNumber(0); // 质押券折算总金额上清
        var dealAmt = new BigNumber(0); // 交易金额
        var cbDealAmt = new BigNumber(0); // 交易金额中债
        var scDealAmt = new BigNumber(0); // 交易金额上清
        var priceAmt = new BigNumber(0); // 回购利息
        var mTradeAmt = new BigNumber(0); // 到期结算金额
        var price = new BigNumber($("#CfetsRepoDealQuotedPrice002_price").val()).dividedBy(new BigNumber(100));// 回购利率
        var showAmtOrg = false; // 是否显示上清和中债的交易金额区分
        if (pledgeRows&&pledgeRows.length>0) {
            var initOrg = pledgeRows[0].repRegistOrg;
            for (var i in pledgeRows) {
                var row = pledgeRows[i];
                if(!row.pledgeRatio){
                    return;
                }
                if(!showAmtOrg && initOrg != row.repRegistOrg && i>0){
                    showAmtOrg = true;
                }
                // 本次质押券面
                row.pledgePrin = JoyinMath.doMul(row.pledgePrin, 10000);
                var pledgePrin = new BigNumber(row.pledgePrin?row.pledgePrin:0);
                /*pledgePrinTotal = pledgePrinTotal.plus(pledgePrin);*/
                // 折算比例
                var pledgeRate = new BigNumber(row.pledgeRatio?row.pledgeRatio:100).dividedBy(new BigNumber(100));
                /*计算券面总额合计，中债登券面总额合计，上清所券面总额合计*/
                pledgePrinTotal = pledgePrinTotal.plus(pledgePrin.multipliedBy(divd).multipliedBy(pledgeRate));
                if(row.repRegistOrg == '01'){
                    cbShareCtAmt = cbShareCtAmt.plus(pledgePrin.multipliedBy(divd).multipliedBy(pledgeRate));
                    cbDealAmt = cbDealAmt.plus(pledgePrin.multipliedBy(divd));
                    zzTotalAmt = zzTotalAmt.plus(pledgePrin.multipliedBy(divd).multipliedBy(pledgeRate))
                }else if(row.repRegistOrg == '02'){
                    scShareCtAmt = scShareCtAmt.plus(pledgePrin.multipliedBy(divd).multipliedBy(pledgeRate));
                    scDealAmt = scDealAmt.plus(pledgePrin.multipliedBy(divd));
                    sqTotalAmt = sqTotalAmt.plus(pledgePrin.multipliedBy(divd).multipliedBy(pledgeRate));
                }
            }
            dealAmt = cbDealAmt.plus(scDealAmt);
            shareCtAmt = cbShareCtAmt.plus(scShareCtAmt);
            var actDays = $('#CfetsRepoDealQuotedPrice002_actDays').val();
            if(actDays){
                priceAmt = shareCtAmt.multipliedBy(price).multipliedBy(new BigNumber(actDays)).dividedBy(new BigNumber(365));
                mTradeAmt = shareCtAmt.plus(priceAmt);
            }
        }
        /*券面总额单位为万元*/
        pledgePrinTotal = JoyinMath.doDiv(pledgePrinTotal, 10000);
        $("#CfetsRepoDealQuotedPrice002_pledgePrinTotal").val(pledgePrinTotal).trigger('blur');
        $("#CfetsRepoDealQuotedPrice002_zzTotalAmt").val(zzTotalAmt).trigger('blur');
        $("#CfetsRepoDealQuotedPrice002_sqTotalAmt").val(sqTotalAmt).trigger('blur');
        $("#CfetsRepoDealQuotedPrice002_shareCtAmt").val(shareCtAmt).trigger("blur");
        $("#CfetsRepoDealQuotedPrice002_cbDealAmt").val(cbShareCtAmt).trigger("blur").attr("disabled",!showAmtOrg);
        $("#CfetsRepoDealQuotedPrice002_scDealAmt").val(scShareCtAmt).trigger("blur").attr("disabled",!showAmtOrg);
        $("#CfetsRepoDealQuotedPrice002_cbShareCtAmt").val(cbShareCtAmt).trigger("blur");
        $("#CfetsRepoDealQuotedPrice002_scShareCtAmt").val(scShareCtAmt).trigger("blur");
        $("#CfetsRepoDealQuotedPrice002_dealAmt").attr("disabled",showAmtOrg).val(shareCtAmt).trigger("blur");
        $("#CfetsRepoDealQuotedPrice002_mIntAmt").val(priceAmt.toFormat(2)).trigger("blur");
        $("#CfetsRepoDealQuotedPrice002_mTradeAmt").val(mTradeAmt.toFormat(2)).trigger("blur");
        $("#CfetsRepoDealQuotedPrice002_amtOrg").toggle(showAmtOrg);
    }
    /**
     * 根据回购期限和清算速度计算首期结算日和到期结算日
     */
    var calculateSettleDate = function (){
        var term = parseInt($("#CfetsRepoDealQuotedPrice002_term").val());
        if(term){
            if (term <= 1) {
                $('#CfetsRepoDealQuotedPrice002_termType').textSelect('setValue', "R001");
            }
            else if (term > 1 && term <= 7) {
                $('#CfetsRepoDealQuotedPrice002_termType').textSelect('setValue', "R007");
            }
            else if (term > 7 && term <= 14) {
                $('#CfetsRepoDealQuotedPrice002_termType').textSelect('setValue', "R014");
            }
            else if (term > 14 && term <= 21) {
                $('#CfetsRepoDealQuotedPrice002_termType').textSelect('setValue', "R021");
            }
            else if (term > 21 && term <= 30) {
                $('#CfetsRepoDealQuotedPrice002_termType').textSelect('setValue', "R1M");
            }
            else if (term > 30 && term <= 60) {
                $('#CfetsRepoDealQuotedPrice002_termType').textSelect('setValue', "R2M");
            }
            else if (term > 60 && term <= 90) {
                $('#CfetsRepoDealQuotedPrice002_termType').textSelect('setValue', "R3M");
            }
            else if (term > 90 && term <= 120) {
                $('#CfetsRepoDealQuotedPrice002_termType').textSelect('setValue', "R4M");
            }
            else if (term > 120 && term <= 150) {
                $('#CfetsRepoDealQuotedPrice002_termType').textSelect('setValue', "R5M");
            }
            else if (term > 150 && term <= 180) {
                $('#CfetsRepoDealQuotedPrice002_termType').textSelect('setValue', "R6M");
            }
            else if (term > 180 && term <= 270) {
                $('#CfetsRepoDealQuotedPrice002_termType').textSelect('setValue', "R9M");
            }
            else {
                $('#CfetsRepoDealQuotedPrice002_termType').textSelect('setValue', "R1Y");
            }
        }
        setSettleDate();
    }
    

    var initTermType = function (){
        var term = parseInt($("#CfetsRepoDealQuotedPrice002_term").val());
        var termType = parseInt($("#CfetsRepoDealQuotedPrice002_termType").val());
        if(term && !termType){
            if (term <= 1) {
                $('#CfetsRepoDealQuotedPrice002_termType').textSelect('setValue', "R001");
            }
            else if (term > 1 && term <= 7) {
                $('#CfetsRepoDealQuotedPrice002_termType').textSelect('setValue', "R007");
            }
            else if (term > 7 && term <= 14) {
                $('#CfetsRepoDealQuotedPrice002_termType').textSelect('setValue', "R014");
            }
            else if (term > 14 && term <= 21) {
                $('#CfetsRepoDealQuotedPrice002_termType').textSelect('setValue', "R021");
            }
            else if (term > 21 && term <= 30) {
                $('#CfetsRepoDealQuotedPrice002_termType').textSelect('setValue', "R1M");
            }
            else if (term > 30 && term <= 60) {
                $('#CfetsRepoDealQuotedPrice002_termType').textSelect('setValue', "R2M");
            }
            else if (term > 60 && term <= 90) {
                $('#CfetsRepoDealQuotedPrice002_termType').textSelect('setValue', "R3M");
            }
            else if (term > 90 && term <= 120) {
                $('#CfetsRepoDealQuotedPrice002_termType').textSelect('setValue', "R4M");
            }
            else if (term > 120 && term <= 150) {
                $('#CfetsRepoDealQuotedPrice002_termType').textSelect('setValue', "R5M");
            }
            else if (term > 150 && term <= 180) {
                $('#CfetsRepoDealQuotedPrice002_termType').textSelect('setValue', "R6M");
            }
            else if (term > 180 && term <= 270) {
                $('#CfetsRepoDealQuotedPrice002_termType').textSelect('setValue', "R9M");
            }
            else {
                $('#CfetsRepoDealQuotedPrice002_termType').textSelect('setValue', "R1Y");
            }
        }
    }
    
    /**
     * 计算结算日
     */
    var setSettleDate = function (){
        var settleSpreed = $("#CfetsRepoDealQuotedPrice002_settleSpreed").val();
        var term = parseInt($("#CfetsRepoDealQuotedPrice002_term").val());
        if(settleSpreed == settleSpreedT0){
            $("#CfetsRepoDealQuotedPrice002_vSettleDate").val(platDate);
            var vDate = DateUtil.parse(platDate);
            var mDate = new Date(vDate.setDate(vDate.getDate()+term));
            Joyin.ajax(basePath + 'invest/bankuplink/common/CfetsQuotedCommon/getWorkDay', {
                'tradeDate': mDate.format("yyyy-MM-dd")
            }, function (date) {
                $("#CfetsRepoDealQuotedPrice002_mSettleDate").val(date);
                settleDateChange();
            });
        }
        else if(settleSpreed == settleSpreedT1){
            Joyin.ajax(basePath + 'invest/bankuplink/common/CfetsQuotedCommon/getWorkDay', {
                'tradeDate': platDate,'settleSpreed':settleSpreed
            }, function (date) {
                $("#CfetsRepoDealQuotedPrice002_vSettleDate").val(date);
                var vDate = DateUtil.parse(date);
                var mDate = new Date(vDate.setDate(vDate.getDate()+term));
                Joyin.ajax(basePath + 'invest/bankuplink/common/CfetsQuotedCommon/getWorkDay', {
                    'tradeDate': mDate.format("yyyy-MM-dd")
                }, function (mSettleDate) {
                    $("#CfetsRepoDealQuotedPrice002_mSettleDate").val(mSettleDate);
                    settleDateChange();
                });
            });
        }
        if(term && !$('#CfetsRepoDealQuotedPrice002_termType').val()){
            calculateSettleDate();
        }
    }
    
    /**
     * 券面总额合计计算交易金额，回购利息，到期结算金额
     */
    var calculateDealAmt = function () {
        if($("#CfetsRepoDealQuotedPrice002_shareCtAmt").val()){
            var pledgePrinTotal = new BigNumber($("#CfetsRepoDealQuotedPrice002_shareCtAmt").val()); // 券面总额合计(万)
            // 交易金额
            $("#CfetsRepoDealQuotedPrice002_dealAmt").val(pledgePrinTotal).trigger("blur");
            calculateMamt();
        }
    }
    
    /**
     * 根据交易金额中债，交易金额上清计算交易金额，回购利息，到期结算金额
     */
    var calculateDealAmtByCbAndSc = function () {
        var cbDealAmt = new BigNumber($("#CfetsRepoDealQuotedPrice002_cbDealAmt").val()); // 交易金额中债
        var scDealAmt = new BigNumber($("#CfetsRepoDealQuotedPrice002_scDealAmt").val()); // 交易金额上清
        var dealAmt = cbDealAmt.plus(scDealAmt); // 交易金额
        $("#CfetsRepoDealQuotedPrice002_dealAmt").val(dealAmt).trigger("blur");
        calculateMamt();
    }
    
    /** 
     * 根据交易金额,回购利率，占款天数计算回购利息和到期结算金额
     */
    var calculateMamt = function (){
        if(!$("#CfetsRepoDealQuotedPrice002_dealAmt").val()){
            return;
        }
        var dealAmt = new BigNumber($("#CfetsRepoDealQuotedPrice002_dealAmt").val()); // 交易金额
        if(!$("#CfetsRepoDealQuotedPrice002_price").val()){
            return;
        }
        var price = new BigNumber($("#CfetsRepoDealQuotedPrice002_price").val()).dividedBy(new BigNumber(100));// 回购利率
        var priceAmt = new BigNumber(0); // 回购利息
        var mTradeAmt = new BigNumber(0); // 到期结算金额
        var actDays = $('#CfetsRepoDealQuotedPrice002_actDays').val();
        if(actDays){
            priceAmt = dealAmt.multipliedBy(price).multipliedBy(new BigNumber(actDays)).dividedBy(new BigNumber(365));
            mTradeAmt = dealAmt.plus(priceAmt);
        }
        $("#CfetsRepoDealQuotedPrice002_mIntAmt").val(priceAmt.toFormat(2)).trigger("blur");
        $("#CfetsRepoDealQuotedPrice002_mTradeAmt").val(mTradeAmt.toFormat(2)).trigger("blur");
    }

    /**
     * 结算日change
     */
    var settleDateChange = function(){
        calculateActDays();
        calculateMamt();
    }
    /**
     * 计算时间占款天数
     */
    var calculateActDays = function (){
        var vSettleDate = $('#CfetsRepoDealQuotedPrice002_vSettleDate').val();
        var mSettleDate = $('#CfetsRepoDealQuotedPrice002_mSettleDate').val();
        if (vSettleDate && mSettleDate) {
            var vdate = DateUtil.parse(vSettleDate);
            var mdate = DateUtil.parse(mSettleDate);
            if (vdate > mdate) {
                Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_E_031"));
                return;
            }
            var actDays = DateUtil.daysBetween(vSettleDate, mSettleDate);
            $('#CfetsRepoDealQuotedPrice002_actDays').val(actDays);
        }
    }
    
    /**
     * 根据id设置元素不可编辑
     */
    var disableElementById = function (id){
        var value = $(id).val();
        if(value && value !=""){
            $(id).attr("disabled",true);
        }
    }
    
    /**
     * 正回购联动质押券
     * 如果我方作为回复方，质押券列表不能变，联动显示可质押和已质押
     */
    var pledgeListChangeAction = function(){
    	var ps = $('#CfetsRepoDealQuotedPrice002_ps').val();
    	if (ps && ps == psZHG){
    		var portfolioName = $('#CfetsRepoDealQuotedPrice002_portfolioName').val();
        	var mySide = $('#CfetsRepoDealQuotedPrice002_mySide').val();
        	if (portfolioName && mySide){
        		if (operation == "CfetsRepoDealQuotedPrice001.reply"){
        			// 如果我方作为回复方，在切换组合代码时需要根据画面显示的质押券关联显示可质押和已质押
        			var object = $.serializeToObject('#CfetsRepoDealQuotedPrice002_submitForm',true);
        			//object.pledgeBondList = repoInfoTable.getAllRows();
                    handlepledgeBondList(object,true);
        			Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/handlePledgeList', object, function(result) {
        	            if (result){
        	                //页面反显 重新赋值
                            if(result.pledgeBondList&&object.bussRepoSec.pledgeBondList){
                                    $.each(result.pledgeBondList,function(i,val1){
                                        $.each(object.bussRepoSec.pledgeBondList,function(i,val2){
                                            if(val1.finprodId == val2.finprodId){
                                                val1.pledgeRatio = val2.pledgeRatio;
                                                val1.repRegistOrgStr = val2.repRegistOrgStr;
                                                val1.repRegistOrg = val2.repRegistOrg;
                                                return;
                                            }
                                        })
                                    });
                            }
                            if (window && window.BussRepoSec && window.BussRepoSec["bussRepoSec"]) {
                                window.BussRepoSec["bussRepoSec"].pledgeBondTotalTbl.reload({data:result.pledgeBondList});
                            }
        	            }
        	        });
        		}else{
                    if (window && window.BussRepoSec && window.BussRepoSec["bussRepoSec"]) {
                        window.BussRepoSec["bussRepoSec"].pledgeBondTotalTbl.reload({data:[]});
                    }
        		}
        	}
    	}
    }
    /**
     * 单组合画面文件上传002
     */
    function doFileupload() {
        var object = {
            businessId: $('#CfetsRepoDealQuotedPrice002_entrustId').val()
        };
        Joyin.dialog(JoyinUtil.getLabel("附件上传"),
            "/invest/bankuplink/reponew/CfetsRepoDealQuotedPrFile/init", "60%", "75%", object);
    }
    /**
     * 设置添加质押券共通参数
     */
    var setBussRepoSecInput = function () {
        var data = {
            portfolioId: $('#CfetsRepoDealQuotedPrice002_portfolioName').val(),
            ps: $("#CfetsRepoDealQuotedPrice002_ps").val(),
            secAcctId: $('#secAcctId').val(),
            repoType: $('#repoType').val(),
            chlId: $('#chlId').val(),
            //add by 张年  2019/08/05 start
            tradeMarket:$('#tradeMarket').val(),
            //add by 张年  2019/08/05 end
            quotaPageModel:$('#CfetsBondDealQuotedPrice002_hidden_quotaPageModel').val(),
            ignoreFlowTradeId: $('#CfetsRepoDealQuotedPrice002_quoteId').val()
        };
        var object = {
            portfolioId: ['portfolioId', 'TrdRepoTrade002.portfolioId'],
            ps: ['ps', 'TrdRepoTrade002.ps'],
            secAcctId: ['secAcctId', 'TrdRepoTrade002.secAcctId'],
            chlId: ['chlId', 'TrdRepoTrade002.chlId'],
            repoType: ['repoType', 'TrdRepoTrade002.repoType'],
            //add by 张年  2019/08/05 start
            tradeMarket: ['tradeMarket', 'TrdRepoTrade002.tradeMarket'],
            //add by 张年  2019/08/05 end
            quotaPageModel: ['quotaPageModel', '操作类型'],
            ignoreFlowTradeId: ['ignoreFlowTradeId', '忽略可用流水的报价编号'],
            data: data
        };
        if (window && window.BussRepoSec && window.BussRepoSec["bussRepoSec"]) {
            window.BussRepoSec["bussRepoSec"].setInputParam(object);
        }
    };
    /**
     * 获取风险试算参数
     *
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function getSearchParams() {
        var object = $.serializeToObject("#CfetsRepoDealQuotedPrice002_submitForm");
        return object;
    }
    /**
     * 对外公开的接口
     */
    parent.CfetsRepoDealQuotedPr002 = {
        dbclickEtr: dbclickEtr,
        riskResultdata: riskResultdata,
        cluflag: cluflag
    };
    CfetsRepoDealQuotedPr002 = {
        dbclickEtr: dbclickEtr,
        riskResultdata: riskResultdata,
        cluflag: cluflag
    };

    $(function () {
    	if(dialogueRecord){
    		$("#CfetsBondDealQuotedPrice002_dialogueRecord").html(dialogueRecord);
    		$("#dialogueRecord_div").show();
    		$("#CfetsRepoDealQuotedPrice002_entrustId_search").css({'margin-top':'187px'});
    	}else{
    		$("#dialogueRecord_div").hide();
    		$("#CfetsRepoDealQuotedPrice002_entrustId_search").css({'margin-top':'35px'});
    	}
        $("#CfetsBondDealQuotedPrice002_dialogueRecord").hide();
    	var quotaPageModel = $("#CfetsBondDealQuotedPrice002_hidden_quotaPageModel").val();
    	if(pageModel == PAGE_MODEL_NEW&&quotaPageModel === "new" && $('#CfetsRepoDealQuotedPrice002_term').val() == '0'){
    	    $('#CfetsRepoDealQuotedPrice002_term').val("");
        }
        /**
         * 本方和本方交易员是否可编辑
         */
        if(ibuFlag_ouTraderIdCanEdit == "N"){
            $('#CfetsRepoDealQuotedPrice002_mySideTrader').attr("disabled",true);
        }
        var quotaPageModel = $("#CfetsBondDealQuotedPrice002_hidden_quotaPageModel").val();
        if(quotaPageModel == "edit"){
        	$('#CfetsRepoDealQuotedPrice002_mySide').attr("disabled",true);
        	$('#CfetsRepoDealQuotedPrice002_mySideTrader').attr("disabled",true);
        	$('#CfetsRepoDealQuotedPrice002_settleType').attr("disabled",true);
        	$('#CfetsRepoDealQuotedPrice002_termType').attr("disabled",true);
        	//币种置灰
            $('#CfetsRepoDealQuotedPrice002_ccy').attr('disabled',true);
            if(editOperation){
            	$('#CfetsRepoDealQuotedPrice002_ps').attr('disabled',true);
            }
        }
        /**
         * 显示正回购或者逆回购表格
         */
        if(ps){
            $('#CfetsRepoDealQuotedPrice002_repoInfo_'+ps).toggle(true);
        }
    	/**
    	 * 委托代码
    	 */
    	$('#CfetsRepoDealQuotedPrice002_entrustId').change(function(){
    		entrustIdChangeAction();
        });
    	/**
    	 * 回购利率
    	 */
    	$('#CfetsRepoDealQuotedPrice002_price').change(function(){
    	    calculateMamt();
    	});
    	/**
    	 * 质押券折算总金额
    	 */
    	$('#CfetsRepoDealQuotedPrice002_shareCtAmt').change(function(){
    	    calculateDealAmt();
    	});
    	/**
    	 * 交易金额中债
    	 */
    	$('#CfetsRepoDealQuotedPrice002_cbDealAmt').change(function(){
    	    calculateDealAmtByCbAndSc();
    	});
    	/**
    	 * 交易金额上清
    	 */
    	$('#CfetsRepoDealQuotedPrice002_scDealAmt').change(function(){
    	    calculateDealAmtByCbAndSc();
    	});
    	/**
    	 * 交易金额
    	 */
    	$('#CfetsRepoDealQuotedPrice002_dealAmt').change(function(){
    	    calculateMamt();
    	});
    	
    	/**
    	 * 组合名称
    	 */
    	$('#CfetsRepoDealQuotedPrice002_portfolioName').change(function(){
    		portfolioNameChangeAction();
    	    pledgeListChangeAction();
        });
    	/**
    	 * 本方
    	 */
    	$('#CfetsRepoDealQuotedPrice002_mySide').change(function(){
    	    mySideChangeAction();
    	    pledgeListChangeAction();
    	});
    	/**
    	 * 对手方方
    	 */
    	$('#CfetsRepoDealQuotedPrice002_counterId').change(function(){
    	    counterIdChangeAction();
    	});
    	/**
    	 * 债券币种
    	 */
    	$('#CfetsRepoDealQuotedPrice002_ccy').change(function(){
    	    var data = $('#CfetsRepoDealQuotedPrice002_ccy').val();
            if (data){
                $('#CfetsRepoDealQuotedPrice002_settleCcy').textSelect('setValue',data);
            }
    	});

        /**
         * 首期结算日
         */
        $('#CfetsRepoDealQuotedPrice002_vSettleDate').change(function(){
            settleDateChange();
        });
        
        /**
         * 到期结算日
         */
        $('#CfetsRepoDealQuotedPrice002_mSettleDate').change(function(){
            settleDateChange();
        });


        /**
         * 清算速度
         */
        $('#CfetsRepoDealQuotedPrice002_settleSpreed').change(function(){
            calculateSettleDate();
        });
        /**
         * 回购期限
         */
        $('#CfetsRepoDealQuotedPrice002_term').change(function(){
            calculateSettleDate();
            /*联动左下角行情信息*/
            //左下行情信息联动
            var chlFinprodId = '';
            var finprodIdhid = '';
            var portfolioId = $("#CfetsRepoDealQuotedPrice002_portfolioName").val();
            var termType = $("#CfetsRepoDealQuotedPrice002_term").val();
            var vdate = $("#tradeDate").val();
            var ccy = "CNY";
            parent.CfetsRepoDealQuotedPrHq.querySecinfo(finprodIdhid,portfolioId,chlFinprodId, termType);
            window.CfetsRepoDealQuotedPrInitCash.queryCash(ccy,vdate,portfolioId);
        });
        calculateActDays();
    	/**
    	 * 交易方向
    	 */
    	$('#CfetsRepoDealQuotedPrice002_ps').change(function(){
    	    var ps = $("#CfetsRepoDealQuotedPrice002_ps").val()
    	    if(ps){
    	        $('#CfetsRepoDealQuotedPrice002_repoInfo_'+ps).toggle(true);
    	    }
            setBussRepoSecInput();
    	});
        /**
         * 保存按钮按下处理
         */
        $('#CfetsRepoDealQuotedPrice002_btn_save').click(function(){
            saveAction();
        });
        /**
         * 提交按钮按下处理
         */
        $('#CfetsRepoDealQuotedPrice002_btn_submit').click(function(){
            submitAction();
        });
        /**
         * 附件上传
         */
        $('#CfetsRepoDealQuotedPrice002_btn_fileupload').click(function(){
            doFileupload();
        });
        /**
         * 风险试算
         */
        $('#CfetsRepoDealQuotedPrice002_btn_riskcal').click(function(){
            /*doRiskcal();*/
        });
        /**
         * 对话按钮点击事件
         */
        $('#cfetsRepoDealQuotedPre002_duihua').click(function () {
            /*接收001页面的传值*/
            duihuaFlag = true;
            qingqiuFlag = false;
            /**
             * 对外公开的接口
             */
            parent.CfetsRepoDealQuotedPr001.getEtrInfoChange('cfetsRepoDealQuotedPre002');
        });

        $('#CfetsRepoDealQuotedPrice002_portfolioId_duihua_search').click(function () {
            parent.CfetsRepoDealQuotedPrMain.clickPortfolioId($("#CfetsRepoDealQuotedPrice002_portfolioName").val());
        });
        /**
         * 取消按钮按下处理
         */
        $('#CfetsRepoDealQuotedPrice002_btn_cancel2').click(function(){
            $('#CfetsRepoDealQuotedPrice002_btn_cancel').trigger("click");
        });
        showAmgOrgFunction(pledgeBondList);
        setBussRepoSecInput();
        disableElementById('#CfetsRepoDealQuotedPrice002_portfolioName');
        disableElementById('#CfetsRepoDealQuotedPrice002_counterId');
        disableElementById('#CfetsRepoDealQuotedPrice002_counterTrader');
        if(operation == "CfetsRepoDealQuotedPrice001.reply"){
            // 回复功能,除委托代码，组合代码，外其他不可编辑
            disabledElement();
            $("#CfetsRepoDealQuotedPrice002_btns_1").toggle(false);
            $("#CfetsRepoDealQuotedPrice002_btns_2").toggle(true);
          //无指令时，组合可改
			var mySideTemp = $('#CfetsRepoDealQuotedPrice002_mySide').val();
			if(mySideTemp){
				$('#CfetsBondDealQuotedPrice002_responseMySide').val(mySideTemp);
			}
        }
        if(operation != "CfetsRepoDealQuotedPrice001.reply"){
            disableElementById('#CfetsRepoDealQuotedPrice002_entrustId');
        }
        if($("#CfetsRepoDealQuotedPrice002_entrustId").val()){
            disableElementById('#CfetsRepoDealQuotedPrice002_ps');
            if(!$("#CfetsRepoDealQuotedPrice002_termType").val() && pageModel != PAGE_MODEL_REFERENCE){
                calculateSettleDate();
            }
        }
        initTermType();
    });
    /**
     * 回复功能,除委托代码，组合代码，本方，本方交易员外其他不可编辑
     */
    var disabledElement = function () {
        var inputs = $("#CfetsRepoDealQuotedPrice002_submitForm input");
        for(var i in  inputs){
            inputs[i].disabled=true;
        }
        var quoteRoleType = $("#CfetsRepoDealQuotedPrice002_quoteRoleType").val();
        var selects = $("#CfetsRepoDealQuotedPrice002_submitForm select");
        for(var i in  selects){
            if(quoteRoleType==quoteRoleTypeEnquiry || selects[i].name != "entrustId"){
                selects[i].disabled=true;
            }
        }
        var btns = $("#CfetsRepoDealQuotedPrice002_submitForm a");
        for (var i in btns){
            if(btns[i].id 
                    && (btns[i].id.startsWith("CfetsRepoDealQuotedPrice002_repoInfo")
                    || btns[i].id.startsWith("CfetsRepoDealQuotedPrice002_nrepoInfo"))){
                btns[i].classList.add("layui-btn-disabled");
                $(btns[i]).unbind("click")
            }
        }
        if(quoteRoleType==quoteRoleTypeEnquiry){
            $("#CfetsRepoDealQuotedPrice002_entrustId_search").unbind("click").css("cursor","not-allowed");
        }
        bindReplyFunction();
    }
    /**
     * 绑定回复方法
     */
    var bindReplyFunction = function (){
        var entrustId = $('#CfetsRepoDealQuotedPrice002_entrustId').val();
        $("#CfetsRepoDealQuotedPrice002_btn_deal").addClass("layui-btn-disabled");
        $("#CfetsRepoDealQuotedPrice002_btn_deal").unbind("click",dealAction)
        $("#CfetsRepoDealQuotedPrice002_btn_reject").addClass("layui-btn-disabled");
        $("#CfetsRepoDealQuotedPrice002_btn_reject").unbind("click",rejectAction)
        if(entrustId || ibuFlag_answerQuoteWithOutEntrust != "01"){
            $('#CfetsRepoDealQuotedPrice002_portfolioName').attr('disabled', false);
            $("#CfetsRepoDealQuotedPrice002_btn_deal").removeClass("layui-btn-disabled");
            $("#CfetsRepoDealQuotedPrice002_btn_deal").bind("click",dealAction)
            $("#CfetsRepoDealQuotedPrice002_btn_reject").removeClass("layui-btn-disabled");
            $("#CfetsRepoDealQuotedPrice002_btn_reject").bind("click",rejectAction)
        }
        if (entrustId){
        	$('#CfetsRepoDealQuotedPrice002_portfolioName').attr('disabled', true);
        }
    }
    /**
     * 成交
     */
    var dealAction = function (){
        var object = $.serializeToObject('#CfetsRepoDealQuotedPrice002_submitForm',true);
        object.quoteId = $("#CfetsRepoDealQuotedPrice002_quoteId").val();
        object.tradeStatus = $("#CfetsRepoDealQuotedPrice002_tradeStatus").val();
        object.operation = "CfetsRepoDealQuotedPrice001.reply.deal";
        object.operatorType = 'done';
        object.quotaPageModel = "dialogueResponse";
        //构造质押券list
        handlepledgeBondList(object);
        Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/checkRepoDeal', object, function(result) {
            if (result.data){
            	Joyin.confirm(result.data, function(){
            	    sendDeal(object);
            	});
            }
        });
    }
    /**
     * 成交发送
     */
    var sendDeal = function (object){
    	WorkFlow.doSubmitList(basePath + 'invest/bankuplink/reponew/CfetsRepoDealQuotedPr002/save', object, "CfetsRepoDealQuotedPrice002_btn_done", null, function(result){
            var index = window.parent.layer.getFrameIndex(window.name);
            window.parent.layer.close(index);
        })
    }
    /**
     * 拒绝
     */
    var rejectAction = function (){
        var object = $.serializeToObject('#CfetsRepoDealQuotedPrice002_submitForm');
        object.quoteId = $("#CfetsRepoDealQuotedPrice002_quoteId").val();
        object.tradeStatus = $("#CfetsRepoDealQuotedPrice002_tradeStatus").val();
        object.operation = "CfetsRepoDealQuotedPrice001.reply.reject"
        Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/checkEdit', object, function(result) {
            if (result.data){
                Joyin.confirm(JoyinUtil.getMessage("CFETS_COMMON_E_033"), function() {
                    Joyin.ajaxJson(basePath + 'invest/bankuplink/reponew/CfetsRepoDealQuotedPr002/reject', object, function(result){
                        var index = window.parent.layer.getFrameIndex(window.name);
                        window.parent.layer.close(index);
                    })
                })
            }
        });
    }
    /**
     * 保存
     */
    var saveAction = function(){
        var object = $.serializeToObject('#CfetsRepoDealQuotedPrice002_submitForm');
        object.quoteId = $("#CfetsRepoDealQuotedPrice002_quoteId").val();
        object.tradeStatus = $("#CfetsRepoDealQuotedPrice002_tradeStatus").val();
        object.operatorType = 'save';
        //构造质押券list
        handlepledgeBondList(object);
        object.operation = "CfetsRepoDealQuotedPrice001.save"
        Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/check', object, function(result) {
            if (result.data){
                Joyin.confirm(result.data, function() {
                	WorkFlow.doSaveList(basePath + 'invest/bankuplink/reponew/CfetsRepoDealQuotedPr002/save', object, "CfetsRepoDealQuotedPrice002_btn_save", null, function(result){
                        var index = window.parent.layer.getFrameIndex(window.name);
                        window.parent.layer.close(index);
                    })
                })
            }
        });
    };
    /**
     * 提交
     */
    var submitAction = function(){
        var object = $.serializeToObject('#CfetsRepoDealQuotedPrice002_submitForm');
        object.quoteId = $("#CfetsRepoDealQuotedPrice002_quoteId").val();
        object.tradeStatus = $("#CfetsRepoDealQuotedPrice002_tradeStatus").val();
        object.operation = "CfetsRepoDealQuotedPrice001.submit"
        object.operatorType = 'submit';
        //构造质押券list
        handlepledgeBondList(object);
        Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/check', object, function(result) {
            if (result.data){
                Joyin.confirm(result.data, function() {
                	WorkFlow.doSubmitList(basePath + 'invest/bankuplink/reponew/CfetsRepoDealQuotedPr002/save', object, "CfetsRepoDealQuotedPrice002_btn_submit", null, function(result){
                        var index = window.parent.layer.getFrameIndex(window.name);
                        window.parent.layer.close(index);
                    })
                })
            }
        });
    };

    /**
     * 输入的质押券面大于可质押券面总额  软控 硬控
     */
    var isControllPledgePrinTip = function (obj) {
        if(obj.bussRepoSec.pledgeBondList){
            $.each(obj.bussRepoSec.pledgeBondList,function(i,val){
                var avaPledgePrin = new BigNumber(val.avaPledgePrin);
                var pledgePrin = new BigNumber(val.pledgePrin);
                if(pledgePrin.gt(avaPledgeNumber)){
                    return true;
                }
            })
        }
        return false;
    }
    /**
     * 构造质押券list
     * @param obj
     */
    var handlepledgeBondList = function (obj,flag) {
        var result = [];
        if(obj.bussRepoSec.pledgeBondList){
            $.each(obj.bussRepoSec.pledgeBondList,function(i,val){
                var item = {};
                item.finprodId = val.finprodId;
                item.bondCode = val.bondCode;
                item.bondAbbrName =val.bondAbbrName;
                if(!flag){
                	item.avaPledgePrin =val.avaPledgePrin;
                }
                item.pledgePrin =val.pledgePrin;
                item.pledgeRate = val.pledgeRatio;
                item.netPrice = val.firstNetPrice;
                item.repRegistOrgStr = val.repRegistOrgStr;
                item.mdate = val.mdate;
                result.push(item);
            })
        }
        obj.pledgeBondList = result;
    }
    /**
     * 接收子页面返回的委托信息
     */
    var saveEntrustSelectData = function(entrustInfo){
        if (entrustInfo){
            $('#CfetsRepoDealQuotedPrice002_entrustId').textSelect('setValue',entrustInfo.entrustId);
            $('#CfetsRepoDealQuotedPrice002_entrustId').trigger("change");
        }
    }
    // 正回购质押列表公开接口
    if (window.CfetsRepoDealQuotedPr002 == undefined) {
        window.CfetsRepoDealQuotedPr002 = {};
    }
    /**
     * 刷新券面总额合计、质押券折算总金额
     */
    var refreshFace = function(result){
        $("#CfetsRepoDealQuotedPrice002_pledgePrinTotal").val(result.pledgePrinTotal).trigger('blur');
        $("#CfetsRepoDealQuotedPrice002_shareCtAmt").val(result.shareCtAmt).trigger("blur");
    }
    window.CfetsRepoDealQuotedPr002 = {
        addPledgeBond: addPledgeBond,
        saveEntrustSelectData: saveEntrustSelectData,
        calculateAmt:calculateAmt,
    };
    
    window.componentLoadCallback = function (isDone, pagePath, params) {
            //待办中及选完委托后放大镜不可用
            var entrustId = $('#CfetsRepoDealQuotedPrice002_entrustId').val();
            if(workflowIdBuss||entrustId){
                $("#CfetsRepoDealQuotedPrice002_entrustId_search").unbind("click").css("cursor","not-allowed");
            }
            // 设置添加质押券共通参数
            setBussRepoSecInput();
            if(isDone){
                //
                $('#totalPrinAmt_div').hide();
                $('#zzdTotalPrinAmt_div').hide();
                $('#sqsTotalPrinAmt_div').hide();
                $('#bussRepoSec_op_div').css('float','left');
                //CFETS质押式回购报价修改界面，当交易方向为逆回购时，根据需求，不允许添加质押券
                if(operation == 'CfetsRepoDealQuotedPrice001.edit'&&$('#CfetsRepoDealQuotedPrice002_ps').val()=='04'){
                    $('#bussRepoSec_btn_add').addClass('layui-btn-disabled').off();
                }
                if(operation == 'CfetsRepoDealQuotedPrice001.reply'){
                    $('#bussRepoSec_btn_add').addClass('layui-btn-disabled').off();
                    $('#bussRepoSec_btn_delete').addClass('layui-btn-disabled').off();
                }
            }
        }
}());