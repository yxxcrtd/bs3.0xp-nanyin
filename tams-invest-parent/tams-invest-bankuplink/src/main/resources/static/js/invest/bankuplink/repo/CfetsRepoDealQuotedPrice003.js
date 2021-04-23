(function(){

    /**
     * 是否跨托管
     * @type {boolean}
     */
    var showAmtOrg = false;
    /**
     * 组合分配
     */
    var dzhTable = $("#CfetsRepoDealQuotedPrice003_dzh_table").joyintable({
        height:100,
        searchOnInit:true,
        data : ptlAllocationList ? ptlAllocationList : [],
        page : false,
        cols:[
            {field:'entrustId', width:180, title: '指令代码'}
            ,{field:'portfolioId', width:150, title: '组合代码'}
            ,{field:'portfolioName', width:150, title: '组合名称'}
            ,{field:'avaNum', width:150,type:'number', title: '剩余额度'}
            ,{field:'fpriceAmt', width:150,type:'number', title: '交易金额', edit : true}
            ,{field:'cbFpriceAmt', width:150,type:'number', title: '交易本金(中债登)', edit : true,visible:false}
            ,{field:'scFpriceAmt', width:150,type:'number', title: '交易本金(上清所)', edit : true,visible:false}
            ,{field:'termDays', width:150, title: '回购期限'}
            ,{field:'price', width:150,type:'number', title: '回购利率%'}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}
        ],
        beforeEditCell:function(preVal, field, rowData){
            if(showAmtOrg&&field=='fpriceAmt'){
                return false;
            }
            return true;
        },
        editCell : function (value, data, field, cell, column) {

        }
        //loadonce:true
    });

    /**
     * 绑定删除事件
     */
    dzhTable.bind('click', '.delt', function(data, index) {
        dzhTable.removeRow(data);
        // 设置添加质押券共通参数
        setBussRepoSecInput();
    });

    /**
     * 分配列表是否显示中债和上清
     * @param item
     */
    var isshowColumns = function (item) {
        if(item){
            dzhTable.showColumns([{field:'cbFpriceAmt', visible: true},{field:'scFpriceAmt', visible: true}]);
        }else{
            dzhTable.showColumns([{field:'cbFpriceAmt', visible: false},{field:'scFpriceAmt', visible: false}]);
        }
    }
    /**
     * 计算结束后 刷新组合分配列表
     * @param item
     */
    var reloadPtlAllocationList = function (item) {
        $.each(item,function (i,e) {
            dzhTable.renderRow(e.LAY_TABLE_INDEX, {"fpriceAmt": e.fpriceAmt,"cbFpriceAmt": e.cbFpriceAmt,"scFpriceAmt": e.scFpriceAmt});
        });
    }

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
    }
    /**
     * 对手方
     */
    var counterIdChangeAction = function(){
        $('#CfetsRepoDealQuotedPrice002_counterTrader').val('').trigger('change');
    }

    var showAmgOrgFunction = function (pledgeBondList){
        var allData = pledgeBondList;
        showAmtOrg = false; // 是否显示上清和中债的交易金额区分
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
        isshowColumns(showAmtOrg);
    }


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
                    row.avaPledgePrin = avaPledgeNumber.dividedBy(divd);
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
        showAmtOrg = false; // 是否显示上清和中债的交易金额区分
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
                //多组合
                if(isDzh == 'Y'){
                    row.pledgePrin = JoyinMath.doMul(row.pledgePrin,10000);
                }
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
        //多组合  正回购 主动刷新组合分配列表
        var ps = $('#CfetsRepoDealQuotedPrice002_ps').val();
        if(ps == '03'){
            refreshFpriceAmt2();
        }
        isshowColumns(showAmtOrg);
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
     * 设置添加质押券共通参数
     */
    var setBussRepoSecInput = function () {
        debugger;
        //组合分配列表
        var portfolioId = "";
        var ptlAllocationList = dzhTable.getAllRows();
        if(ptlAllocationList&&ptlAllocationList.length>0){
            $.each(ptlAllocationList,function (i,e) {
                portfolioId += e.portfolioId+",";
            });
        }
        var data = {
            portfolioId: portfolioId,
            ps: $("#CfetsRepoDealQuotedPrice002_ps").val(),
            secAcctId: $('#secAcctId').val(),
            repoType: $('#repoType').val(),
            chlId: $('#chlId').val(),
            //add by 张年  2019/08/05 start
            tradeMarket:$('#tradeMarket').val(),
            //add by 张年  2019/08/05 end
            quotaPageModel:$('#CfetsBondDealQuotedPrice002_hidden_quotaPageModel').val()
        };
        var object = {
            //portfolioId: ['portfolioId', 'TrdRepoTrade002.portfolioId'],
            ps: ['ps', 'TrdRepoTrade002.ps'],
            secAcctId: ['secAcctId', 'TrdRepoTrade002.secAcctId'],
            chlId: ['chlId', 'TrdRepoTrade002.chlId'],
            repoType: ['repoType', 'TrdRepoTrade002.repoType'],
            //add by 张年  2019/08/05 start
            tradeMarket: ['tradeMarket', 'TrdRepoTrade002.tradeMarket'],
            //add by 张年  2019/08/05 end
            quotaPageModel: ['quotaPageModel', '操作类型'],
            data: data
        };
        if (window && window.BussRepoSec && window.BussRepoSec["bussRepoSec"]) {
            window.BussRepoSec["bussRepoSec"].setInputParam(object);
        }
    };

    $(function () {
        if(dialogueRecord){
            $("#CfetsBondDealQuotedPrice002_dialogueRecord").html(dialogueRecord);
        }
        var quotaPageModel = $("#CfetsBondDealQuotedPrice002_hidden_quotaPageModel").val();
        if(pageModel == PAGE_MODEL_NEW&&quotaPageModel === "new"&&$('#CfetsRepoDealQuotedPrice002_term').val()=="0"){
            $('#CfetsRepoDealQuotedPrice002_term').val("");
        }
        if(quotaPageModel == "edit"){
            $("#dialogueRecord_div").show();
            $("#CfetsRepoDealQuotedPrice002_entrustId_search").css({'margin-top':'187px'});
        }else{
            $("#dialogueRecord_div").hide();
            $("#CfetsRepoDealQuotedPrice002_entrustId_search").css({'margin-top':'35px'});
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
                basePath + "invest/bankuplink/repo/CfetsRepoDealQuotedPrice005/init", "99%", "80%", {
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
         * 保存按钮按下处理
         */
        $('#CfetsRepoDealQuotedPrice003_btn_save').click(function(){
            saveAction();
        })

        /**
         * 提交按钮按下处理
         */
        $('#CfetsRepoDealQuotedPrice003_btn_submit').click(function(){
            submitAction();
        })
        /**
         * 取消按钮按下处理
         */
        $('#CfetsRepoDealQuotedPrice003_btn_cancel2').click(function(){
            $('#CfetsRepoDealQuotedPrice003_btn_cancel').trigger("click");
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
            $("#CfetsRepoDealQuotedPrice003_btns_1").toggle(false);
            $("#CfetsRepoDealQuotedPrice003_btns_2").toggle(true);
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

        /**
         * 组合分配 添加
         */
        $('#CfetsRepoDealQuotedPrice003_dzh_add_btn').click(function(){
            //添加后列表 初始化页面 排除已经存在的分配
            var entrustIds = "";
            var tableResult = dzhTable.getAllRows();
            for(var i in tableResult){
                entrustIds += tableResult[i].entrustId+",";
            }
            Joyin.dialog(JoyinUtil.getLabel("CfetsRepoDealQuotedPrice005"),
                basePath + "invest/bankuplink/repo/CfetsRepoDealQuotedPrice005/init", "99%", "80%", {
                    pageModel : PAGE_MODEL_NEW,
                    ps : $('#CfetsRepoDealQuotedPrice002_ps').val(),
                   /* entrustIdOne : $('#CfetsRepoDealQuotedPrice002_entrustId').val(),*/
                    mySide : $('#CfetsRepoDealQuotedPrice002_mySide').val(),
                    settleSpreed : $('#CfetsRepoDealQuotedPrice002_settleSpreed').val(),
                    term:$('#CfetsRepoDealQuotedPrice002_term').val(),
                    isDzh:"Y",
                    entrustIds:entrustIds
                },function () {
                    // 设置添加质押券共通参数
                    setBussRepoSecInput();
                })
        });

        /**
         * 计算
         */
        $('#CfetsRepoDealQuotedPrice003_btn_cal').click(function () {
            refreshFpriceAmt();
        })
        setTimeout(function () {
            isshowColumns(showAmtOrg);
        },1000);

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
        $("#CfetsRepoDealQuotedPrice003_btn_deal").addClass("layui-btn-disabled");
        $("#CfetsRepoDealQuotedPrice003_btn_deal").unbind("click",dealAction)
        $("#CfetsRepoDealQuotedPrice003_btn_reject").addClass("layui-btn-disabled");
        $("#CfetsRepoDealQuotedPrice003_btn_reject").unbind("click",rejectAction)
        if(entrustId || ibuFlag_answerQuoteWithOutEntrust != "01"){
            $('#CfetsRepoDealQuotedPrice002_portfolioName').attr('disabled', false);
            $("#CfetsRepoDealQuotedPrice003_btn_deal").removeClass("layui-btn-disabled");
            $("#CfetsRepoDealQuotedPrice003_btn_deal").bind("click",dealAction)
            $("#CfetsRepoDealQuotedPrice003_btn_reject").removeClass("layui-btn-disabled");
            $("#CfetsRepoDealQuotedPrice003_btn_reject").bind("click",rejectAction)
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
        var object = $.serializeToObject('#CfetsRepoDealQuotedPrice003_submitForm',true);
        object.quoteId = $("#CfetsRepoDealQuotedPrice002_quoteId").val();
        object.tradeStatus = $("#CfetsRepoDealQuotedPrice002_tradeStatus").val();
        object.operation = "CfetsRepoDealQuotedPrice001.reply.deal";
        object.operatorType = 'done';
        object.quotaPageModel = "dialogueResponse";
        object.isDzh = "Y";
        object.isKtg = showAmtOrg?"Y":"N";
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
        WorkFlow.doSubmitList(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/save', object, "CfetsRepoDealQuotedPrice003_btn_done", null, function(result){
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
        var object = $.serializeToObject('#CfetsRepoDealQuotedPrice003_submitForm');
        object.quoteId = $("#CfetsRepoDealQuotedPrice002_quoteId").val();
        object.tradeStatus = $("#CfetsRepoDealQuotedPrice002_tradeStatus").val();
        object.operatorType = 'save';
        //构造质押券list
        handlepledgeBondList(object);
        object.operation = "CfetsRepoDealQuotedPrice001.save"
        object.isDzh = isDzh;
        object.isKtg = showAmtOrg?"Y":"N";
        Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/check', object, function(result) {
            if (result.data){
                Joyin.confirm(result.data, function() {
                    WorkFlow.doSaveList(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice003/save', object, "CfetsRepoDealQuotedPrice003_btn_save", null, function(result){
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
        var object = $.serializeToObject('#CfetsRepoDealQuotedPrice003_submitForm');
        object.quoteId = $("#CfetsRepoDealQuotedPrice002_quoteId").val();
        object.tradeStatus = $("#CfetsRepoDealQuotedPrice002_tradeStatus").val();
        object.operation = "CfetsRepoDealQuotedPrice001.submit"
        object.operatorType = 'submit';
        //构造质押券list
        handlepledgeBondList(object);
        object.isDzh = isDzh;
        object.isKtg = showAmtOrg?"Y":"N";
        Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/check', object, function(result) {
            if (result.data){
                Joyin.confirm(result.data, function() {
                    WorkFlow.doSubmitList(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice003/save', object, "CfetsRepoDealQuotedPrice003_btn_submit", null, function(result){
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
                item.pledgePrin =JoyinMath.doMul(val.pledgePrin,10000);
                item.pledgeRate = val.pledgeRatio;
                item.netPrice = val.firstNetPrice;
                item.repRegistOrgStr = val.repRegistOrgStr;
                item.mdate = val.mdate;
                item.portfolioId = val.portfolioId;
                result.push(item);
            })
        }
        obj.pledgeBondList = result;
    }

    // 正回购质押列表公开接口
    if (window.CfetsRepoDealQuotedPrice002 == undefined) {
        window.CfetsRepoDealQuotedPrice002 = {};
    }

    /**
     *  组合分配列表添加操作，确认后 重新刷新列表
     * @param result
     */
    var reloadPtlAllocation = function(result){
        for (var i in result) {
            var obj = result[i];
            obj.termDays = obj.term;
            dzhTable.addRow(obj);
        }
        //刷新回购利率
        refreshPrice();
    }

    /**
     * 组合列表分配后 刷新回购利率
     * 规则:正回购选择最小，逆回购选择最大
     */
    var refreshPrice = function(){
        var result;
        var ps = $('#CfetsRepoDealQuotedPrice002_ps').val();
        var ptlAllocations = dzhTable.getAllRows();
        if(ptlAllocations){
            var result = $('#CfetsRepoDealQuotedPrice002_price').val()?$('#CfetsRepoDealQuotedPrice002_price').val():ptlAllocations[0].price;
            for(var i in ptlAllocations){
                if(ps == '03'){
                    if(result>=ptlAllocations[i].price){
                        result = ptlAllocations[i].price;
                    }
                }
                else if(ps == '04'){
                    if(result<=ptlAllocations[i].price){
                        result = ptlAllocations[i].price;
                    }
                }
            }
            $('#CfetsRepoDealQuotedPrice002_price').val(result);
        }
    }

    /**
     * 重新计算组合分配列表的交易金额
     */
    var refreshFpriceAmt = function(){
        var distribution = $('#distribution').val();
        var dealAmt = $('#CfetsRepoDealQuotedPrice002_dealAmt').val();
        //交易本金（中债登）
        var cbDealAmt = $('#CfetsRepoDealQuotedPrice002_cbDealAmt').val();
        //交易本金（上清所）
        var scDealAmt = $('#CfetsRepoDealQuotedPrice002_scDealAmt').val();
        //组合分配列表
        var ptlAllocationList = dzhTable.getAllRows();
        if(distribution==""){
            Joyin.error("请选择分配方式!");
            return;
        }
        if(dealAmt ==""){
            Joyin.error("请填写交易金额!");
            return;
        }
        if(showAmtOrg){
            if(cbDealAmt ==""){
                Joyin.error("请填写交易金额(中债)!");
                return;
            }
            if(scDealAmt ==""){
                Joyin.error("请填写交易金额(上清)!");
                return;
            }
        }
        if(ptlAllocationList.length==0){
            Joyin.error("组合分配列表为空，请添加!");
            return;
        }
        var object = $.serializeToObject('#CfetsRepoDealQuotedPrice003_submitForm');
        //升序排序
        ptlAllocationList = ptlAllocationList.sort(function (a,b) {
            return a.avaNum - b.avaNum;
        })
        //平均分配  优先分配中债
        if(distribution == '139'){
            var size = ptlAllocationList.length;
            //平均值
            var ava = JoyinMath.doDiv(dealAmt,size,2,1);
            //分配汇总
            var avaTotal = 0;
            if(showAmtOrg){
                var avacb = JoyinMath.doDiv(cbDealAmt,size,2,1);
                var avasc = JoyinMath.doDiv(scDealAmt,size,2,1);
                var avaTotalcb = 0;
                var avaTotalsc = 0;
            }
            $.each(ptlAllocationList,function (i,e) {
                if(showAmtOrg){
                    if (Number(e.avaNum) >= Number(avacb)) {
                        e.cbFpriceAmt = avacb;
                        var avaNum = JoyinMath.doMinus(e.avaNum,avacb);
                        if(Number(avaNum)>Number(avasc)){
                            e.scFpriceAmt = avasc;
                        }else{
                            e.scFpriceAmt = avaNum;
                        }
                        var sumCbSc = JoyinMath.doAdd(e.cbFpriceAmt,e.scFpriceAmt);
                        e.fpriceAmt = sumCbSc;
                        e.avaNum = JoyinMath.doMinus(e.avaNum,sumCbSc);
                    } else {
                        e.cbFpriceAmt = e.avaNum;
                        e.scFpriceAmt = 0;
                        e.fpriceAmt = e.avaNum;
                        e.avaNum = 0;
                    }
                }
                else{
                    if (Number(e.avaNum) >= Number(ava)) {
                        e.fpriceAmt = ava;
                        e.avaNum = JoyinMath.doMinus(e.avaNum,ava);
                    } else {
                        e.fpriceAmt = e.avaNum;
                        e.avaNum = 0;
                    }
                }
                if(showAmtOrg){
                    avaTotalcb = JoyinMath.doAdd(avaTotalcb,e.cbFpriceAmt);
                    avaTotalsc = JoyinMath.doAdd(avaTotalsc,e.scFpriceAmt);
                }else{
                    avaTotal = JoyinMath.doAdd(avaTotal,e.fpriceAmt);
                }
                });
        }
        //比例分配
        if(distribution == '143'){
            //剩余额度汇总
            var avaNumTotal = 0;
            $.each(ptlAllocationList,function (i,e) {
                avaNumTotal = JoyinMath.doAdd(avaNumTotal,e.avaNum);
            });
            $.each(ptlAllocationList,function (i,e) {
                if(showAmtOrg){
                    e.cbFpriceAmt = JoyinMath.doDiv(JoyinMath.doMul(cbDealAmt,e.avaNum),avaNumTotal,2,1);
                    e.scFpriceAmt = JoyinMath.doDiv(JoyinMath.doMul(scDealAmt,e.avaNum),avaNumTotal,2,1);
                    e.fpriceAmt = JoyinMath.doAdd(e.cbFpriceAmt,e.scFpriceAmt);
                }else{
                    e.fpriceAmt = JoyinMath.doDiv(JoyinMath.doMul(dealAmt,e.avaNum),avaNumTotal,2,1);
                }
                e.avaNum = JoyinMath.doMinus(e.avaNum,e.fpriceAmt);
                if(showAmtOrg){
                    avaTotalcb = JoyinMath.doAdd(avaTotalcb,e.cbFpriceAmt);
                    avaTotalsc = JoyinMath.doAdd(avaTotalsc,e.scFpriceAmt);
                }else{
                    avaTotal = JoyinMath.doAdd(avaTotal,e.fpriceAmt);
                }
                }
            );
        }
        //尾差总数
        var tailDifferenceObj = {};
        if(showAmtOrg){
            var tailDifferencecb = JoyinMath.doMinus(cbDealAmt,avaTotalcb);
            var tailDifferencesc = JoyinMath.doMinus(scDealAmt,avaTotalsc);
            tailDifferenceObj.tailDifferencecb = tailDifferencecb;
            tailDifferenceObj.tailDifferencesc = tailDifferencesc;
        }else{
            var tailDifference = JoyinMath.doMinus(dealAmt,avaTotal);
            tailDifferenceObj.tailDifference = tailDifference;
        }
        handleTailDifferenceNhg(tailDifferenceObj,ptlAllocationList,100);
        //刷新列表
        isshowColumns(showAmtOrg);
        reloadPtlAllocationList(ptlAllocationList);
    }

    /**
     *  正回购(主动计算)
     */
    var refreshFpriceAmt2 = function(){
        var distribution = $('#distribution').val();
        var dealAmt = $('#CfetsRepoDealQuotedPrice002_dealAmt').val();
        //交易本金（中债登）
        var cbDealAmt = $('#CfetsRepoDealQuotedPrice002_cbDealAmt').val();
        //交易本金（上清所）
        var scDealAmt = $('#CfetsRepoDealQuotedPrice002_scDealAmt').val();
        //组合分配列表
        var ptlAllocationList = dzhTable.getAllRows();
        var object = $.serializeToObject('#CfetsRepoDealQuotedPrice003_submitForm');
        //质押券列表
        var pledgeBondList = object.bussRepoSec.pledgeBondList;
        //组合分组
        var portfolioIdArr = [];
        var portfolioIdArr2 = [];
        if(pledgeBondList&&pledgeBondList.length>0){
            $.each(pledgeBondList,function (i,e) {
                if(portfolioIdArr2.indexOf(e.portfolioId) === -1){
                    portfolioIdArr2.push(e.portfolioId);
                }
            });
            //重构数组
            $.each(portfolioIdArr2,function (i,e) {
                var portfolioIdGroup = {};
                portfolioIdGroup.portfolioId = e;
                portfolioIdArr.push(portfolioIdGroup);
            });
            for(var i in portfolioIdArr){
                var pledgePrinTotal = 0;var pledgePrinTotalCb = 0;var pledgePrinTotalSc = 0;
                for(var j in pledgeBondList){
                    if(showAmtOrg){
                        if(portfolioIdArr[i].portfolioId == pledgeBondList[j].portfolioId){
                            if(pledgeBondList[j].repRegistOrg == '02'){
                                pledgePrinTotalSc = JoyinMath.doAdd(pledgePrinTotalSc,JoyinMath.doMul(JoyinMath.doMul(pledgeBondList[j].pledgePrin,10000),pledgeBondList[j].pledgeRatio/100,6));
                            }else{
                                pledgePrinTotalCb = JoyinMath.doAdd(pledgePrinTotalCb,JoyinMath.doMul(JoyinMath.doMul(pledgeBondList[j].pledgePrin,10000),pledgeBondList[j].pledgeRatio/100,6));
                            }
                        }
                        pledgePrinTotal = JoyinMath.doAdd(pledgePrinTotal,JoyinMath.doAdd(pledgePrinTotalCb,pledgePrinTotalSc));
                    }else{
                        if(portfolioIdArr[i].portfolioId == pledgeBondList[j].portfolioId){
                            pledgePrinTotal = JoyinMath.doAdd(pledgePrinTotal,JoyinMath.doMul(JoyinMath.doMul(pledgeBondList[j].pledgePrin,10000),pledgeBondList[j].pledgeRatio/100,6));
                        }
                    }
                }
                if(showAmtOrg){
                    portfolioIdArr[i].pledgePrinTotalCb = pledgePrinTotalCb;
                    portfolioIdArr[i].pledgePrinTotalSc = pledgePrinTotalSc;
                    portfolioIdArr[i].pledgePrinTotal = pledgePrinTotal;
                }else{
                    portfolioIdArr[i].pledgePrinTotal = pledgePrinTotal;
                }
            }
        }
        //组合分配列表
        if(ptlAllocationList&&ptlAllocationList.length>0){
            for(var i in portfolioIdArr){
                var avaNumTotal = 0;
                for(var j in ptlAllocationList){
                    if(portfolioIdArr[i].portfolioId == ptlAllocationList[j].portfolioId){
                        avaNumTotal = JoyinMath.doAdd(avaNumTotal,ptlAllocationList[j].avaNum);
                    }
                }
                portfolioIdArr[i].avaNumTotal = avaNumTotal;
            }
            for(var i in ptlAllocationList){
                for(var j in portfolioIdArr){
                    if(ptlAllocationList[i].portfolioId == portfolioIdArr[j].portfolioId){
                        if(showAmtOrg){
                            var cbFpriceAmt = JoyinMath.doDiv(JoyinMath.doMul(portfolioIdArr[j].pledgePrinTotalCb,ptlAllocationList[i].avaNum),portfolioIdArr[j].avaNumTotal,2,1);
                            var scFpriceAmt = JoyinMath.doDiv(JoyinMath.doMul(portfolioIdArr[j].pledgePrinTotalSc,ptlAllocationList[i].avaNum),portfolioIdArr[j].avaNumTotal,2,1);
                            //交易金额不可超过剩余额度
                            if(Number(cbFpriceAmt)>=Number(ptlAllocationList[i].avaNum)){
                                ptlAllocationList[i].cbFpriceAmt = ptlAllocationList[i].avaNum;
                                ptlAllocationList[i].scFpriceAmt = Number(0);
                                ptlAllocationList[i].avaNum = Number(0);
                            }else{
                                ptlAllocationList[i].cbFpriceAmt = cbFpriceAmt;
                                var avaNumTemp = JoyinMath.doMinus(ptlAllocationList[i].avaNum,ptlAllocationList[i].cbFpriceAmt);
                                if(Number(avaNumTemp)>Number(scFpriceAmt)){
                                    ptlAllocationList[i].scFpriceAmt = scFpriceAmt;
                                    ptlAllocationList[i].avaNum = JoyinMath.doMinus(avaNumTemp,scFpriceAmt);;
                                }else{
                                    ptlAllocationList[i].scFpriceAmt = avaNumTemp;
                                    ptlAllocationList[i].avaNum = Number(0);
                                }
                            }
                            ptlAllocationList[i].fpriceAmt = JoyinMath.doAdd(ptlAllocationList[i].cbFpriceAmt,ptlAllocationList[i].scFpriceAmt);
                        }else{
                            var fpriceAmt = JoyinMath.doDiv(JoyinMath.doMul(portfolioIdArr[j].pledgePrinTotal,ptlAllocationList[i].avaNum),portfolioIdArr[j].avaNumTotal,2,1);
                            //交易金额不可超过剩余额度
                            if(Number(fpriceAmt)>=Number(ptlAllocationList[i].avaNum)){
                                ptlAllocationList[i].fpriceAmt = ptlAllocationList[i].avaNum;
                                ptlAllocationList[i].avaNum = Number(0);
                            }else{
                                ptlAllocationList[i].fpriceAmt = fpriceAmt;
                                ptlAllocationList[i].avaNum = JoyinMath.doMinus(ptlAllocationList[i].avaNum,ptlAllocationList[i].fpriceAmt);
                            }
                        }
                    }
                }
            }
        }
        //每个组合剩余尾差
        for(var i in portfolioIdArr){
            //组合的交易金额汇总
            var fpriceAmtTotal = 0;var fpriceAmtTotalCb = 0;var fpriceAmtTotalSc = 0;
            for(var j in ptlAllocationList){
                if(portfolioIdArr[i].portfolioId = ptlAllocationList[j].portfolioId){
                    if(showAmtOrg){
                        fpriceAmtTotalCb = JoyinMath.doAdd(fpriceAmtTotalCb,ptlAllocationList[j].cbFpriceAmt);
                        fpriceAmtTotalSc = JoyinMath.doAdd(fpriceAmtTotalSc,ptlAllocationList[j].scFpriceAmt);
                    }else{
                        fpriceAmtTotal = JoyinMath.doAdd(fpriceAmtTotal,ptlAllocationList[j].fpriceAmt);
                    }
                }
            }
            if(showAmtOrg){
                portfolioIdArr[i].tailDifferencecb = JoyinMath.doMinus(portfolioIdArr[i].pledgePrinTotalCb,fpriceAmtTotalCb);
                portfolioIdArr[i].tailDifferencesc = JoyinMath.doMinus(portfolioIdArr[i].pledgePrinTotalSc,fpriceAmtTotalSc);
            }else{
                portfolioIdArr[i].tailDifference = JoyinMath.doMinus(portfolioIdArr[i].pledgePrinTotal,fpriceAmtTotal);
            }
        }
        handleTailDifferenceZhg(portfolioIdArr,ptlAllocationList,100);
        //刷新列表
        isshowColumns(showAmtOrg);
        reloadPtlAllocationList(ptlAllocationList);
    }

    /**
     * 尾差处理(逆回购)
     * @param tailDifferencebj
     * @param list
     * @param unit 尾差单位
     */
    var  handleTailDifferenceNhg = function(tailDifferenceObj,ptlAllocationList,unit){
        if(showAmtOrg){
            handleTailDifferenceNhg2(tailDifferenceObj.tailDifferencecb,ptlAllocationList,unit,"2");
            handleTailDifferenceNhg2(tailDifferenceObj.tailDifferencesc,ptlAllocationList,unit,"3");
        }else{
            handleTailDifferenceNhg2(tailDifferenceObj.tailDifference,ptlAllocationList,unit,"1");
        }
    }

    /**
     *  尾差处理
     * @param tailDifference
     * @param ptlAllocationList
     * @param unit
     * @param flag  1表示交易金额  2表示中债金额 3表示上清金额
     */
    var handleTailDifferenceNhg2 = function (tailDifference,ptlAllocationList,unit,flag) {
        //尾差比例
        var entrustIdArr = [];
        var tailDifferencePre = JoyinMath.doDiv(1,unit,unit.length-1);
        var length = ptlAllocationList.length;
        while(Number(tailDifference)>0&&length>0){
            if(Number(tailDifference)>=1){
                //尾差大于1 始终按委托条数平均拆分尾差
                tailDifferencePre = JoyinMath.doDiv(tailDifference,length,unit.length-1);
            }
            $.each(ptlAllocationList,function (i,e) {
                if(Number(e.avaNum) <= Number(0)||Number(tailDifference)<=Number(0)) {
                    if(entrustIdArr.indexOf(e.entrustId)<0){
                        entrustIdArr.push(e.entrustId);
                        length--;
                    }
                    return;
                }else{
                    if(flag =='2'){
                        e.cbFpriceAmt = JoyinMath.doAdd(e.cbFpriceAmt,tailDifferencePre);
                    }
                    if(flag =='3'){
                        e.scFpriceAmt = JoyinMath.doAdd(e.scFpriceAmt,tailDifferencePre);
                    }
                    e.fpriceAmt = JoyinMath.doAdd(e.fpriceAmt,tailDifferencePre);
                    e.avaNum = JoyinMath.doMinus(e.avaNum,tailDifferencePre);
                    tailDifference = JoyinMath.doMinus(tailDifference,tailDifferencePre);
                }
            });
        }
    }

    /**
     * 尾差处理(正回购)
     * @param tailDifference
     * @param list
     * @param unit 尾差单位
     */
    var  handleTailDifferenceZhg = function(portfolioIdArr,ptlAllocationList){
        //剩余额度  降序排列
        ptlAllocationList = ptlAllocationList.sort(function (a,b) {
            return b.avaNum - a.avaNum;
        });
        if(showAmtOrg){
            handleTailDifferenceZhg2(portfolioIdArr,ptlAllocationList,'2');
            handleTailDifferenceZhg2(portfolioIdArr,ptlAllocationList,'3');
        }else{
            handleTailDifferenceZhg2(portfolioIdArr,ptlAllocationList,'1')
        }
    }

    /**
     * @param portfolioIdArr
     * @param ptlAllocationList
     * @param flag 1表示交易金额  2表示中债金额 3表示上清金额
     */
    var  handleTailDifferenceZhg2 = function(portfolioIdArr,ptlAllocationList,flag){
        for(var i in portfolioIdArr){
            if(flag=='1'){
                var tailDifference = portfolioIdArr[i].tailDifference;
            }
            if(flag=='2'){
                var tailDifference = portfolioIdArr[i].tailDifferencecb;
            }
            if(flag=='3'){
                var tailDifference = portfolioIdArr[i].tailDifferencesc;
            }
            while(Number(tailDifference)>0){
                for(var j in ptlAllocationList){
                    if(portfolioIdArr[i].portfolioId==ptlAllocationList[j].portfolioId){
                        if(flag=='2'){
                            var cbFpriceAmt = ptlAllocationList[j].cbFpriceAmt;
                        }
                        if(flag=='3'){
                            var scFpriceAmt = ptlAllocationList[j].scFpriceAmt;
                        }
                        var fpriceAmt = ptlAllocationList[j].fpriceAmt;
                        var avaNum = ptlAllocationList[j].avaNum;
                        if(Number(ptlAllocationList[j].avaNum) <= Number(0)||Number(tailDifference)<=Number(0)) {
                            return;
                        }else{
                            if(flag=='2'){
                                cbFpriceAmt = JoyinMath.doAdd(cbFpriceAmt,tailDifference);
                            }
                            if(flag=='3'){
                                scFpriceAmt = JoyinMath.doAdd(scFpriceAmt,tailDifference);
                            }
                            fpriceAmt = JoyinMath.doAdd(fpriceAmt,tailDifference);
                            avaNum = JoyinMath.doMinus(avaNum,tailDifference);
                            //超支
                            if(Number(avaNum)>=0){
                                tailDifference = 0;
                                if(flag=='2'){
                                    ptlAllocationList[j].cbFpriceAmt = cbFpriceAmt;
                                }
                                if(flag=='3'){
                                    ptlAllocationList[j].scFpriceAmt = scFpriceAmt;
                                }
                                ptlAllocationList[j].fpriceAmt = fpriceAmt;
                            }else{
                                tailDifference = JoyinMath.doMinus(tailDifference,ptlAllocationList[j].avaNum);
                                if(flag=='2'){
                                    ptlAllocationList[j].cbFpriceAmt = JoyinMath.doAdd(ptlAllocationList[j].avaNum,ptlAllocationList[j].cbFpriceAmt);
                                }
                                if(flag=='3'){
                                    ptlAllocationList[j].scFpriceAmt = JoyinMath.doAdd(ptlAllocationList[j].avaNum,ptlAllocationList[j].scFpriceAmt);
                                }
                                ptlAllocationList[j].fpriceAmt = JoyinMath.doAdd(ptlAllocationList[j].avaNum,ptlAllocationList[j].fpriceAmt);
                            }
                        }
                    }
                }
            }
        }
    }
    window.CfetsRepoDealQuotedPrice003 = {
        addPledgeBond: addPledgeBond,
        reloadPtlAllocation: reloadPtlAllocation,
        calculateAmt:calculateAmt
    };

    window.componentLoadCallback = function (isDone, pagePath, params) {
        if(isDone){
            $('#totalPrinAmt_div').hide();
            $('#zzdTotalPrinAmt_div').hide();
            $('#sqsTotalPrinAmt_div').hide();
            $('#bussRepoSec_op_div').css('float','left');
            //CFETS质押式回购报价修改界面，当交易方向为逆回购时，根据需求，不允许添加质押券
            if(operation == 'CfetsRepoDealQuotedPrice001.edit'&&$('#CfetsRepoDealQuotedPrice002_ps').val()=='04'){
                $('#bussRepoSec_btn_add').addClass('layui-btn-disabled').off();
            }
            //多组合成交和拒绝画面质押券添加和删除操作不可置灰
            /*if(operation == 'CfetsRepoDealQuotedPrice001.reply'){
                $('#bussRepoSec_btn_add').addClass('layui-btn-disabled').off();
                $('#bussRepoSec_btn_delete').addClass('layui-btn-disabled').off();
            }*/
            var ps = $("#CfetsRepoDealQuotedPrice002_ps").val()
            if(ps){
                $('#CfetsRepoDealQuotedPrice002_repoInfo_'+ps).toggle(true);
                //交易方向为正回购时，分配方式不可选，计算按钮置灰
                if(ps=='03'){
                    $('#distribution').val("").attr('disabled',true).trigger('change');
                    $('#CfetsRepoDealQuotedPrice003_btn_cal').addClass('layui-btn-disabled').off();
                }
            }
            $('#CfetsRepoDealQuotedPrice002_term').trigger('change');
            //多组合回购利率 重刷
            refreshPrice();
            // 设置添加质押券共通参数
            setBussRepoSecInput();
        }
    }
}());