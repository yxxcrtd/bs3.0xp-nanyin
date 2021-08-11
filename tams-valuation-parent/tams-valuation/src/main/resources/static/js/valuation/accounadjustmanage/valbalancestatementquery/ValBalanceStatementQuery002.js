$(function () {

    var getJiZhangDate=function(x){
        Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/valbalancestatementquery/ValBalanceStatementQuery002/getJiZhangDate', getParams(x), function(result) {
            $('#jizhangDate').val(result.data);
            table1.load();
        });
    };
    var getParams = function (x) {
        return{
            valDates:$('#formatDate').val(),
            desDate:x
        }
    };

    /**
     * 获取查询余额流水信息参数
     */
    var getPageParams = function() {
        return {
            jizhangDate: $('#jizhangDate').val(),
            booksetId: $('#booksetId').val(),
            id: $('#id').val(),
            detailSubjectNoTail: $('#detailSubjectNoTail').val()
        }
    };
    /**
     * 获取查询凭证信息参数
     */
    var getSeqNo = function() {
        return {
            seqNo: $('#seqNo').val()
        }
    };
    /**
     * 获取查询交易信息参数
     */
    var getTradeId = function() {
        return {
            tradeId: $('#tradeId').val(),
            booksetId:$('#booksetId').val()
        }
    };
    /**
     * 余额流水信息
     * @type {jQuery}
     */
    var table1=$("#dataTable1").joyintable({
        ajax: {
            url: 'findPage1',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        // height: 400,
        page : false,
        checkbox:false,
        click:function(table, data) {
            $('#seqNo').val(data.seqNo);
            if(!table2.table) {
                table2.load();
            }
            else {
                table2.reload();
            }
            $('#tradeId').val(data.tradeId);
            if(!table3.table) {
                table3.load();
            }
            else {
                table3.reload();
            }
        },
        cols: [
            {field:'bookDate', width:150, title: '记账日期',type:'date'}
            ,{field:'seqNo', width:150, title: '记账流水号',render : function(data, type, row, meta) {
                    return "<a id='table1SeqNo' class='pro-link closePriceLink' style='cursor: pointer;'>"+data+"</ a>";
                }}
            ,{field:'finprodAbbr',width:150,  title: '资产/负债名称'}
            ,{field:'subjectName', width:150, title: '科目名称'}
            ,{field:'cdFlag', width:150, title: '借贷'}
            ,{field:'ccyFmt', width:150, title: '原币币种'}
            ,{field:'bCcyFmt', width:150, title: '记账币种'}
            ,{field:'happenAmt', type:"money", digit: 4, width:150,  title: '原币金额'}
            ,{field:'bHappenAmt', type:"money", digit: 4, width:150, title: '本币金额'}
            ,{field:'happenNumber', type:"money", digit: 4, width:150, title: '数量'}
            ,{field:'marketValue', type:"money",digit: 4, width:150, title: '原币余额'}
            ,{field:'valuePercent',type:"money",digit: 4, width:150,  title: '本币余额'}
            ,{field:'tradeId', width:150, title: '业务编号',render : function(data, type, row, meta) {
                    return "<a id='table1Yewu' class='pro-link closePriceLink' style='cursor: pointer;'>"+data+"</ a>";
                }}
            ,{field:'ywlb', width:150, title: '业务类别'}
        ]
    });

    /**
     * 余额流水信息表中的记账流水号链接
     */
    table1.bind('click', '#table1SeqNo', function(data, index) {
        console.log(data);
        debugger;
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson('/valuation/accounadjustmanage/valbalancestatementquery/ValBalanceStatementQuery002/check', data, function(result) {
            var tradeId=data.tradeId;
            document.getElementById('vocher').innerText='凭证信息:'+tradeId;
            $('#seqNo').val(data.seqNo);
            if(!table2.table) {
                table2.load();
            }
            else {
                table2.reload();
            }
        });
    });

    /**
     * 余额流水信息表中的业务编号链接
     */
    table1.bind('click', '#table1Yewu', function(data, index) {
        debugger;
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson('/valuation/accounadjustmanage/valbalancestatementquery/ValBalanceStatementQuery002/check', data, function(result) {
            $('#tradeId').val(data.tradeId);
            if(!table3.table) {
                table3.load();
            }
            else {
                table3.reload();
            }
        });
    });

    /**
     * 凭证信息
     * @type {jQuery}
     */
    var table2=$("#dataTable2").joyintable({
        ajax: {
            url: 'findPage2',
            type: 'POST',
            data: function() {
                return getSeqNo();
            }
        },
        //height: 400,
        page : false,
        checkbox:false,
        cols: [
            {field:'bookDate', width:150, title: '生成日期',type:'date'}
            ,{field:'subjectName', width:150, title: '科目名称'}
            ,{field:'cdFlag',width:150,  title: '借贷'}
            ,{field:'ccyFmt', width:150, title: '原币币种'}
            ,{field:'bCcyFmt', width:150, title: '记账币种'}
            ,{field:'happenAmt', type:"money", digit: 4, width:150,  title: '原币金额'}
            ,{field:'bHappenAmt', type:"money", digit: 4, width:150, title: '本币金额'}
            ,{field:'happenNumber', type:"money", digit: 4, width:150, title: '数量'}
            ,{field:'tradeId', width:150, title: '业务编号'}
            ,{field:'offsetFlag', width:150, title: '冲销标识'}
        ]
    });

    /**
     * 交易信息
     */
    var table3=$("#dataTable3").joyintable({
        ajax: {
            url: 'findPage3',
            type: 'POST',
            data: function() {
                return getTradeId();
            }
        },
        //height: 400,
        page : false,
        checkbox:false,
        cols: [
            {field:'tradeId', width:150, title: '交易编号',render : function(data, type, row, meta) {
                    return "<a id='table3Yewu' class='pro-link closePriceLink' style='cursor: pointer;'>"+data+"</ a>";
                }}
            ,{field:'booksetId',width:150,  title: '帐套代码'}
            ,{field:'finprodId', width:150, title: '资产代码',render : function(data, type, row, meta) {
                    return "<a id='table3Zichan' class='pro-link closePriceLink' style='cursor: pointer;'>"+data+"</ a>";
                }}
            ,{field:'finprodAbbr', width:150, title: '资产简称'}
            ,{field:'finprodType', width:150, title: '资产类型'}
            ,{field:'tradePlay',width:150, title: '交易平台'}
            ,{field:'tradeAmt', type:"money",digit: 4, width:150, title: '券面总额/交易金额'}
            ,{field:'tradeDate', width:150, title: '交易日期',type:'date'}
            ,{field:'busiType', width:150, title: '业务类型'}
            ,{field:'psCode', width:150, title: '交易方向'}
        ]
    });

    /**
     * 交易信息表中的业务编号链接
     */
    table3.bind('click', '#table3Yewu', function(data, index) {
        debugger;
        Joyin.ajaxJson('/valuation/accounadjustmanage/valbalancestatementquery/ValBalanceStatementQuery002/check', data, function(result) {
            var bondBusitypeArr =  ['02','20','24'];//债券
            var planBusitypeArr =  ['09'];//计划类资产
            var depositBusitypeArr =  ['03','28'];//存款类资产
            var lendBusitypeArr =  ['01'];//拆借交易
            var fundBusitypeArr =  ['06','07'];//基金类资产
            var repurchaseBusitypeArr =  ['04', '05'];//回购交易
            var currentBusitypeArr =  ['15'];//活期存款收息交易查询
            var tradeBusitypeArr =  ['19'];//交易账户收划款查询
            var bondLendBusitypeArr =  ['14'];//债券借贷交易查询
            var overdueBusitypeArr =  ['37'];//逾期罚息交易查询
            if(bondBusitypeArr.indexOf(data.busiType)>=0){
                Joyin.dialog(JoyinUtil.getLabel("查看债券详情"),
                    JoyinUtil.getRealPath("/valuation/trademanage/investtradequery/bandtrade/BondTradeQuery001/init"),
                    "90%", "85%", {pageModel:PAGE_MODEL_REFERENCE,tradeId:data.tradeId,prodId:data.booksetId,ps:data.psCode},
                    function(){});
            }else if(planBusitypeArr.indexOf(data.busiType)>=0){
                Joyin.dialog(JoyinUtil.getLabel("查看计划类资产详情"),
                    JoyinUtil.getRealPath("/valuation/trademanage/investtradequery/planassetsdeal/ValPlanAssetsDeal001/init"),
                    "90%", "85%", {pageModel:PAGE_MODEL_REFERENCE,tradeId:data.tradeId,prodId:data.booksetId,ps:data.psCode},
                    function(){});
            }else if(depositBusitypeArr.indexOf(data.busiType)>=0){
                Joyin.dialog(JoyinUtil.getLabel("查看存款类资产交易"),
                    JoyinUtil.getRealPath("/valuation/trademanage/investtradequery/posittrade/DepositLoanTrade001/init"),
                    "90%", "85%", {pageModel:PAGE_MODEL_REFERENCE, tradeId:data.tradeId,prodId:data.booksetId},
                    function(){});
            }else if(lendBusitypeArr.indexOf(data.busiType)>=0){
                Joyin.dialog(JoyinUtil.getLabel("查看拆借交易详情"),
                    JoyinUtil.getRealPath("/valuation/trademanage/investtradequery/lendtrade/LendTradeQuery001/init"),
                    "90%", "85%", {pageModel:PAGE_MODEL_REFERENCE,tradeId:data.tradeId,prodId:data.booksetId},
                    function(){});
            }else if(fundBusitypeArr.indexOf(data.busiType)>=0){
                Joyin.dialog(JoyinUtil.getLabel("查看基金类资产详情"),
                    JoyinUtil.getRealPath("/valuation/trademanage/investtradequery/fundcurrency/FundCurrency001/init"),
                    "90%", "85%", {pageModel:PAGE_MODEL_REFERENCE,tradeId:data.tradeId,ps:data.psCode,busiType:data.busiType,prodId:data.booksetId},
                    function(){});
            }else if(repurchaseBusitypeArr.indexOf(data.busiType)>=0){
                Joyin.dialog(JoyinUtil.getLabel("查看回购交易详情"),
                    JoyinUtil.getRealPath("/valuation/trademanage/investtradequery/repurchasetrade/RepurchaseTradeQuery001/init"),
                    "90%", "85%", {pageModel:PAGE_MODEL_REFERENCE,tradeId:data.tradeId,prodId:data.booksetId},
                    function(){});
            }else if(currentBusitypeArr.indexOf(data.busiType)>=0){
                Joyin.dialog(JoyinUtil.getLabel("查看活期存款交易"),
                    JoyinUtil.getRealPath("/valuation/trademanage/investtradequery/currentdeposit/CurrentDeposit001/init"),
                    "90%", "85%", {pageModel:PAGE_MODEL_REFERENCE, tradeId:data.tradeId,prodId:data.booksetId},
                    function(){});
            }else if(tradeBusitypeArr.indexOf(data.busiType)>=0){
                Joyin.dialog(JoyinUtil.getLabel("查看交易账户信息"),
                    JoyinUtil.getRealPath("/valuation/trademanage/investtradequery/tradeaccount/TradeAccount001/init"),
                    "90%", "85%", {pageModel:PAGE_MODEL_REFERENCE, tradeId:data.tradeId,prodId:data.booksetId},
                    function(){});
            }else if(bondLendBusitypeArr.indexOf(data.busiType)>=0){
                Joyin.dialog(JoyinUtil.getLabel("查看债券借贷交易查询借贷"),
                    JoyinUtil.getRealPath("/valuation/trademanage/investtradequery/bondlend/ValBondLend001/init"),
                    "90%", "85%", {pageModel:PAGE_MODEL_REFERENCE,tradeId:data.tradeId,ps:data.psCode,prodId:data.booksetId},
                    function(){});
            }else if(overdueBusitypeArr.indexOf(data.busiType)>=0) {
                Joyin.dialog(JoyinUtil.getLabel("查看逾期交易信息"),
                    JoyinUtil.getRealPath("/valuation/trademanage/investtradequery/overduepenalty/ValOverduePenalty001/init"),
                    "90%", "85%", {pageModel: PAGE_MODEL_REFERENCE,tradeId: data.tradeId, ps: data.psCode, prodId: data.booksetId},
                    function () {});
            }
        });
    });

    /**
     * 交易信息表中的组合代码链接
     */
    // table3.bind('click', '#table3Zuhe', function(data, index) {
    //     debugger;
    //     data.pageModel = PAGE_MODEL_EDIT;
    //     Joyin.ajaxJson('/valuation/accounadjustmanage/valbalancestatementquery/ValBalanceStatementQuery002/check', data, function(result) {
    //         Joyin.dialog(JoyinUtil.getLabel("数据溯源信息查看"),
    //             "/valuation/accounadjustmanage/valbalancestatementquery/ValBalanceStatementQuery002/init",
    //             "95%", "90%",data,function() {
    //                 table.reload();
    //             });
    //
    //     });
    // });

    /**
     * 交易信息表中的资产代码链接
     */
    table3.bind('click', '#table3Zichan', function(data, index) {
        Joyin.ajaxJson('/valuation/accounadjustmanage/valbalancestatementquery/ValBalanceStatementQuery002/check', data, function(result) {
            debugger;
            var finprodType=data.finprodType;
            var buybackFinprod=["F02","F03","F04","F05"];
            if(finprodType=="F01"){
                Joyin.dialog(JoyinUtil.getLabel("查看债券详情"),
                    basePath + "valuation/basicdata/asset/bond/FinBondTargetAsset002/init",
                    "90%", "85%", {pageModel:PAGE_MODEL_REFERENCE,
                        finprodId:data.finprodId,  //金融产品代码
                        valDate:data.valDate,//估值日
                        finprodMarketId:data.finprodMarketId//市场代码
                    });
            }else if(finprodType=="F06"){
                Joyin.dialog(JoyinUtil.getLabel("查看基金详情"),
                    basePath + "valuation/basicdata/asset/fund/FinFundTarget002/init",
                    "90%", "85%", {pageModel:PAGE_MODEL_REFERENCE,finprodId:data.finprodId});
            }else if(finprodType=="F17"){//计划类
                Joyin.dialog(JoyinUtil.getLabel("查看计划类资产"),
                    JoyinUtil.getRealPath("/valuation/assets/plan/ValPlannedassetquery002/init"),
                    "90%", "85%", {pageModel:PAGE_MODEL_EDIT, finprodId:data.finprodId, profitType:data.profitTypeCode},
                    function(){

                    });
            }else if(finprodType=="F07"||finprodType=="F21"){//存款类
                Joyin.dialog(JoyinUtil.getLabel("查看存款类资产"),
                    basePath + "valuation/basicdata/asset/depo/FinDepositTarget002/init",
                    "90%", "85%", {pageModel:PAGE_MODEL_REFERENCE,
                        finprodId:data.finprodId,  //金融产品代码
                        assetType:data.assetType
                    });
            }else if(buybackFinprod.indexOf(finprodType)>=0){//回购类
                Joyin.dialog(JoyinUtil.getLabel("查看回购类资产"),
                    JoyinUtil.getRealPath("/valuation/assets/repurchase/ValRepurchaseAssetQuery002/init"),
                    "90%", "85%", {pageModel:PAGE_MODEL_REFERENCE,
                        finprodId:data.finprodId,finprodType:data.finprodType},
                    function(){
                    });
            }else if(finprodType=="F08"||finprodType=="F09"){//拆借类
                Joyin.dialog(JoyinUtil.getLabel("查看拆借类资产"),
                    JoyinUtil.getRealPath("/valuation/assets/loanmark/ValLoanMarkquery002/init"),
                    "90%", "85%", {pageModel:PAGE_MODEL_REFERENCE,finprodId:data.finprodId,finprodType:data.finprodType},
                    function(){
                        //table.reload();
                    });
            }
        });
    });

    /**
     * 画面加载完成
     */
    $(function() {
        table1.load();

        //table2.load();
        // table3.load();
        /**
         * 绑定查看交易信息按钮事件
         */
        $('#ValBalanceStatementQuery002_btn_view').click(function() {
            var tradeIds=document.getElementById('vocher').innerText;
            var index=tradeIds.indexOf(':');
            var tradeId=tradeIds.substring(index+1);
            $('#tradeId').val(tradeId);
            if(!table3.table) {
                table3.load();
            }
            else {
                table3.reload();
            }
        });
        /**
         * 绑定今天按钮事件
         */
        $('#ValBalanceStatementQuery002_btn_search').click(function() {
            getJiZhangDate(0);
        });
        /**
         * 绑定昨天按钮事件
         */
        $('#ValBalanceStatementQuery002_btn_search2').click(function() {
            getJiZhangDate(1);
        });
        /**
         * 绑定前天按钮事件
         */
        $('#ValBalanceStatementQuery002_btn_search3').click(function() {
            getJiZhangDate(2);
        });
        /**
         * 绑定近5天按钮事件
         */
        $('#ValBalanceStatementQuery002_btn_search4').click(function() {
            getJiZhangDate(5);
        });
    });
});
