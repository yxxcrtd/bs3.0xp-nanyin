(function(){

    
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
                        /*if(data.counterId){
                            $('#CfetsRepoDealQuotedPrice002_counterId').attr('disabled', true).textSelect('setValue',data.counterId);
                        }
                        else{
                        	$('#CfetsRepoDealQuotedPrice002_counterId').attr('disabled', false);
                        }*/
                        $('#CfetsRepoDealQuotedPrice002_term').val(data.term).trigger("change");
                        $('#CfetsRepoDealQuotedPrice002_price').val(data.price).trigger("blur");
                        $('#CfetsRepoDealQuotedPrice002_mySide').textSelect('setValue',data.mySide);
                        $('#CfetsRepoDealQuotedPrice002_mySideTrader').textSelect('setValue',data.mySideTrader);
                        $('#CfetsRepoDealQuotedPrice002_counterTrader').textSelect('setValue',data.counterTrader);
                        /*if(data.counterTrader){
                            $('#CfetsRepoDealQuotedPrice002_counterTrader').attr('disabled', true).textSelect('setValue',data.counterTrader);
                        }
                        else {
                            $('#CfetsRepoDealQuotedPrice002_counterTrader').attr('disabled', false);
                        }*/
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
    }
    /**
     * 对手方
     */
    var counterIdChangeAction = function(){
        $('#CfetsRepoDealQuotedPrice002_counterTrader').val('').trigger('change');
    }

    
    /*var repoInfoTable = $("#CfetsRepoDealQuotedPrice002_repoInfo_table").joyintable({
        height:100,
        searchOnInit:true,
        data : ((ps == "03") && pledgeBondList) ? pledgeBondList : [],
        page : false,
        loadonce : false,
        cols:[
           {field:'bondCode', width:150, title: 'CfetsRepoDealQuotedPrice002.repoInfo.table.bondCode'}
           ,{field:'bondAbbrName', width:150, title: 'CfetsRepoDealQuotedPrice002.repoInfo.table.bondAbbrName'}
           ,{field:'avaPledgePrin', width:150,type:'number', title: '可质押面额（万）'}
           ,{field:'pledgeNumber', width:180,type:'number', title: 'CfetsRepoDealQuotedPrice002.repoInfo.table.pledgeNumber'}
           /!*,{field:'avaPledgeNumber', width:180, title: 'CfetsRepoDealQuotedPrice002.repoInfo.table.avaPledgeNumber'}*!/
           ,{field:'pledgePrin',type:'number', width:180, digit:2,edit: operation == "CfetsRepoDealQuotedPrice001.reply"?false:true, title: 'CfetsRepoDealQuotedPrice002.repoInfo.table.pledgePrin'}
           ,{field:'pledgeRate',type:'number', width:180, digit:2,edit: operation == "CfetsRepoDealQuotedPrice001.reply"?false:true, title: 'CfetsRepoDealQuotedPrice002.repoInfo.table.pledgeRate'}
           ,{field:'netPrice', width:150, title: 'CfetsRepoDealQuotedPrice002.repoInfo.table.netPrice'}
           ,{field:'valueDateStr', width:150, title: 'CfetsRepoDealQuotedPrice002.repoInfo.table.valueDate'}
           ,{field:'mdateStr', width:150, title: 'CfetsRepoDealQuotedPrice002.repoInfo.table.mdate'}
           ,{field:'exerciseDateStr', width:150, title: 'CfetsRepoDealQuotedPrice002.repoInfo.table.exerciseDate'}
           ,{field:'repRegistOrgStr', width:150, title: 'CfetsRepoDealQuotedPrice002.repoInfo.table.repRegistOrg'}
           ,{field:'avaPledgeNumber', visible: false}
        ],
        editCell : function (value, data, field, cell, column) {
            if(field === "pledgePrin"){
                if (value && value != ''){
                    var datas = repoInfoTable.getAllRows();
                    datas[data.LAY_TABLE_INDEX].pledgePrin = new BigNumber(value).toFixed(2);
                    repoInfoTable.reload({data: datas});
                }
                // 录入的本次质押券面大于剩余可质押面额，则不允许录入（通过参数开关控制，如果是软阻挡则只做提示，可继续录入）
                var pledgePrin = new BigNumber(value);
                var avaPledgeNumber = new BigNumber(data.avaPledgeNumber);
                if(pledgePrin.gt(avaPledgeNumber)){
                    if(ibuFlag_repoOverAvaCanContinue != '02'){
                        var datas = repoInfoTable.getAllRows();
                        datas[data.LAY_TABLE_INDEX].pledgePrin = null;
                        repoInfoTable.reload({data: datas});
                        Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_R_006"));
                    }else{
                        Joyin.confirm(JoyinUtil.getMessage("CFETS_COMMON_R_007"), function () {
                        }, {}, function() {
                            var datas = repoInfoTable.getAllRows();
                            datas[data.LAY_TABLE_INDEX].pledgePrin = null;
                            repoInfoTable.reload({data: datas});
                        });
                    }
                }
            }else if (field === "pledgeRate"){
                if (value && value != ''){
                    var datas = repoInfoTable.getAllRows();
                    datas[data.LAY_TABLE_INDEX].pledgeRate = new BigNumber(value).toFixed(2);
                    repoInfoTable.reload({data: datas});
                }
            }
            calculateAmt(repoInfoTable);
        }
        //loadonce:true
    });*/
    /*var nrepoInfoTable = $("#CfetsRepoDealQuotedPrice002_nrepoInfo_table").joyintable({
        data : ((ps == "04") && pledgeBondList) ? pledgeBondList : [],
        height : 100,
        searchOnInit : true,
        page : false,
        loadonce : false,
        cols : [ {
            field : 'bondCode',
            width : 150,
            title : 'CfetsRepoDealQuotedPrice002.nrepoInfo.table.bondCode'
        }, {
            field : 'bondAbbrName',
            width : 150,
            title : 'CfetsRepoDealQuotedPrice002.nrepoInfo.table.bondAbbrName'
        }, {
            field : 'pledgePrin',
            type : 'number',
            width : 180,
            edit : operation == "CfetsRepoDealQuotedPrice001.reply"?false:true,
            title : 'CfetsRepoDealQuotedPrice002.nrepoInfo.table.pledgePrin'
        }, {
            field : 'pledgeRate',
            type : 'number',
            width : 180,
            edit : operation == "CfetsRepoDealQuotedPrice001.reply"?false:true,
            title : 'CfetsRepoDealQuotedPrice002.nrepoInfo.table.pledgeRate'
        }, {
            field : 'netPrice',
            width : 150,
            title : 'CfetsRepoDealQuotedPrice002.nrepoInfo.table.netPrice'
        }, {
            field : 'valueDateStr',
            width : 150,
            title : 'CfetsRepoDealQuotedPrice002.nrepoInfo.table.valueDate'
        }, {
            field : 'mdateStr',
            width : 150,
            title : 'CfetsRepoDealQuotedPrice002.nrepoInfo.table.mdate'
        }, {
            field : 'repRegistOrgStr',
            width : 150,
            title : 'CfetsRepoDealQuotedPrice002.nrepoInfo.table.repRegistOrg'
        } ],
        editCell : function(value, data, field, cell, column) {
            calculateAmt(nrepoInfoTable);
        }
    });*/

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
    }
    /**
     * 添加按钮按下处理
     */
    /*var addRepoInfoProcess = function (tab) {
        var data = $.serializeToObject('#CfetsRepoDealQuotedPrice002_submitForm');
        if(!data.ps){
            Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_E_024"));
            return;
        }
        if(!data.portfolioId){
            Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_E_025"));
            return;
        }
        if(!data.mySide){
            Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_E_026"));
            return;
        }
        if(!data.term){
            Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_E_027"));
            return;
        }
        if(!data.price){
            Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_E_028"));
            return;
        }
        var entity = {};
        if(tab == "repoInfo"){
            entity.pledgeBondList = repoInfoTable.getAllRows();
            Joyin.dialog(JoyinUtil.getLabel("CfetsRepoDealQuotedPrice002.repoInfo"),
                    JoyinUtil.getRealPath("invest/bankuplink/repo/CfetsRepoDealQuotedPrice003/init"),
                    "98%", "90%",{pageModel : PAGE_MODEL_NEW,
                    portfolioId:data.portfolioId,
                    secAcctId:data.secAcctId,
                    // add by zhangliang 20200617 正回购添加本方机构过滤条件
                    mySide:data.mySide,
                    entity: JSON.stringify(entity)},
                    function(){}
            );
        }else if(tab == "nrepoInfo"){
            entity.pledgeBondList = nrepoInfoTable.getAllRows();
            Joyin.dialog(JoyinUtil.getLabel("CfetsRepoDealQuotedPrice002.nrepoInfo"),
                    JoyinUtil.getRealPath("invest/bankuplink/repo/CfetsRepoDealQuotedPrice004/init"),
                    "98%", "90%",{pageModel : PAGE_MODEL_NEW,
                    portfolioId:data.portfolioId,
                    secAcctId:data.secAcctId,
                    entity: JSON.stringify(entity)},
                    function(){}
            );
        }else{
            return ;
        }
    };*/
    
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
                var pledgePrin = new BigNumber(row.pledgePrin?row.pledgePrin:0);
                pledgePrinTotal = pledgePrinTotal.plus(pledgePrin);
                // 折算比例
                var pledgeRate = new BigNumber(row.pledgeRatio?row.pledgeRatio:100).dividedBy(new BigNumber(100));
                if(row.repRegistOrg == '01'){
                    cbShareCtAmt = cbShareCtAmt.plus(pledgePrin.multipliedBy(divd).multipliedBy(pledgeRate));
                    cbDealAmt = cbDealAmt.plus(pledgePrin.multipliedBy(divd));
                }else if(row.repRegistOrg == '02'){
                    scShareCtAmt = scShareCtAmt.plus(pledgePrin.multipliedBy(divd).multipliedBy(pledgeRate));
                    scDealAmt = scDealAmt.plus(pledgePrin.multipliedBy(divd));
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
        $("#CfetsRepoDealQuotedPrice002_pledgePrinTotal").val(pledgePrinTotal).trigger('blur');
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
     * 质押券删除按钮方法
     */
    /*var removeRepoInfoProcess = function(tab){
        var table;
        if(tab=="repoInfo"){
            table = repoInfoTable;
        }else if(tab=="nrepoInfo"){
            table = nrepoInfoTable;
        }else{
            Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_E_030"));
            return ;
        }
        var pledgeRows = table.getAllCheckedRows();
        if (pledgeRows) {
            // 倒序删除，正序会存在问题
            for (var i = pledgeRows.length - 1; i >= 0; i--) {
                table.removeRow(pledgeRows[i]);
            }
            calculateAmt(table);
        }
    }*/

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

    $(function () {
    	if(dialogueRecord){
    		$("#CfetsBondDealQuotedPrice002_dialogueRecord").html(dialogueRecord);
    		$("#dialogueRecord_div").show();
    		$("#CfetsRepoDealQuotedPrice002_entrustId_search").css({'margin-top':'187px'});
    	}else{
    		$("#dialogueRecord_div").hide();
    		$("#CfetsRepoDealQuotedPrice002_entrustId_search").css({'margin-top':'35px'});
    	}
    	var quotaPageModel = $("#CfetsBondDealQuotedPrice002_hidden_quotaPageModel").val();
    	if(pageModel == PAGE_MODEL_NEW&&quotaPageModel === "new" && $('#CfetsRepoDealQuotedPrice002_term').val() == '0'){
    	    $('#CfetsRepoDealQuotedPrice002_term').val("");
        }
    	/*if(quotaPageModel == "edit"){
    		$("#dialogueRecord_div").show();
    		$("#CfetsRepoDealQuotedPrice002_entrustId_search").css({'margin-top':'187px'});
    	}else{
    		$("#dialogueRecord_div").hide();
    		$("#CfetsRepoDealQuotedPrice002_entrustId_search").css({'margin-top':'35px'});
    	}*/
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
         * 委托代码放大镜
         */
        $('#CfetsRepoDealQuotedPrice002_entrustId_search').click(function(){
            Joyin.dialog(JoyinUtil.getLabel("CfetsRepoDealQuotedPrice005"), 
                basePath + "invest/bankuplink/repo/CfetsRepoDealQuotedPrice005/init", "99%", "58%", {
                    pageModel : PAGE_MODEL_NEW, 
                    ps : $('#CfetsRepoDealQuotedPrice002_ps').val(),
                    entrustIdOne : $('#CfetsRepoDealQuotedPrice002_entrustId').val(),
                    mySide : $('#CfetsRepoDealQuotedPrice002_mySide').val(),
                    settleSpreed : $('#CfetsRepoDealQuotedPrice002_settleSpreed').val()
                })
        })
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
        });
//        calculateSettleDate();
        calculateActDays();
//        setSettleDate();
        
    	/**
    	 * 交易方向
    	 */
    	$('#CfetsRepoDealQuotedPrice002_ps').change(function(){
    	    var ps = $("#CfetsRepoDealQuotedPrice002_ps").val()
    	    //$('#CfetsRepoDealQuotedPrice002_repoInfo_03').toggle(false);
    	    //$('#CfetsRepoDealQuotedPrice002_repoInfo_04').toggle(false);
    	    //repoInfoTable.reload({data:[]});
    	    //nrepoInfoTable.reload({data:[]});
    	    if(ps){
    	        $('#CfetsRepoDealQuotedPrice002_repoInfo_'+ps).toggle(true);
    	    }
            setBussRepoSecInput();
    	});
        /**
         * 质押券信息（正回购）添加按钮点击事件
         */
       /* $('#CfetsRepoDealQuotedPrice002_repoInfo_btn1').click(function(){
            addRepoInfoProcess("repoInfo");
        });
        /!**
         * 质押券信息（正回购）删除按钮点击事件
         *!/
        $('#CfetsRepoDealQuotedPrice002_repoInfo_btn2').click(function(){
            removeRepoInfoProcess("repoInfo");
        });
        /!**
         * 质押券信息（逆回购）添加按钮点击事件
         *!/
        $('#CfetsRepoDealQuotedPrice002_nrepoInfo_btn1').click(function(){
            addRepoInfoProcess("nrepoInfo");
        });
        /!**
         * 质押券信息（逆回购）删除按钮点击事件
         *!/
        $('#CfetsRepoDealQuotedPrice002_nrepoInfo_btn2').click(function(){
            removeRepoInfoProcess("nrepoInfo");
        });*/
        
        /**
         * 保存按钮按下处理
         */
        $('#CfetsRepoDealQuotedPrice002_btn_save').click(function(){
            saveAction();
        })
        
        /**
         * 提交按钮按下处理
         */
        $('#CfetsRepoDealQuotedPrice002_btn_submit').click(function(){
            submitAction();
        })
        /**
         * 取消按钮按下处理
         */
        $('#CfetsRepoDealQuotedPrice002_btn_cancel2').click(function(){
            $('#CfetsRepoDealQuotedPrice002_btn_cancel').trigger("click");
        })
//        settleDateChange();
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
            //disableElementById('#CfetsRepoDealQuotedPrice002_settleSpreed');
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
        // add by zhangliang 20200727 回复场合如果委托代码已选，组合不可编辑
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
    	WorkFlow.doSubmitList(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/save', object, "CfetsRepoDealQuotedPrice002_btn_done", null, function(result){
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
                    Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/reject', object, function(result){
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
                	WorkFlow.doSaveList(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/save', object, "CfetsRepoDealQuotedPrice002_btn_save", null, function(result){
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
                	WorkFlow.doSubmitList(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/save', object, "CfetsRepoDealQuotedPrice002_btn_submit", null, function(result){
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
    if (window.CfetsRepoDealQuotedPrice002 == undefined) {
        window.CfetsRepoDealQuotedPrice002 = {};
    }
    /**
     * 刷新券面总额合计、质押券折算总金额
     */
    var refreshFace = function(result){
        $("#CfetsRepoDealQuotedPrice002_pledgePrinTotal").val(result.pledgePrinTotal).trigger('blur');
        $("#CfetsRepoDealQuotedPrice002_shareCtAmt").val(result.shareCtAmt).trigger("blur");
    }
    window.CfetsRepoDealQuotedPrice002 = {
        addPledgeBond: addPledgeBond,
        saveEntrustSelectData: saveEntrustSelectData,
        calculateAmt:calculateAmt
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