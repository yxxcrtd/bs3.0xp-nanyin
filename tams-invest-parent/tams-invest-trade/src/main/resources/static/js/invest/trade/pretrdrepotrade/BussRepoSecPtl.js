(function () {
    var param = {};

    var entrustSurAmt;

    /**
     * 设置初始参数
     * @param paramObject
     */
    var setInputParam = function (data) {
        if($("#ps").val()=='04'){
            pledgeBondTotalTbl.showColumns([{field:'addPledgeInfo',visible: false}]);
        }else{
            pledgeBondTotalTbl.showColumns([{field:'addPledgeInfo',visible: true}]);
        }
        var data = {
                ps: $("#ps").val(),
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
                entrustId: $("#entrustId").val(),
                avalibleCash: $("#bussTrdAvailableCashQuery_avalibleCash").val(),
                avalibleCashT1: $("#bussTrdAvailableCashQuery_avalibleCashT1").val(),
                calendarId: $("#calendarId").val(),
                ccy: $("#ccy").val(),
                tradeDate: $("#tradeDate").val(),
            }
            var object = {
                ps: ['ps', 'TrdRepoTrade002.ps'],
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
                entrustId: ['entrustId', 'TrdRepoTrade002.entrustId'],
                avalibleCash: $('avalibleCash','TrdRepoTrade002.avalibleCash'),
                avalibleCashT1: $('avalibleCashT1','TrdRepoTrade002.avalibleCashT1'),
                calendarId: $('calendarId','TrdRepoTrade002.calendarId'),
                ccy: $('ccy','TrdRepoTrade002.ccy'),
                tradeDate: $('tradeDate','TrdRepoTrade002.tradeDate'),
                data: data
            }
        param = object;
       // getElementById("ps").val(param.data.ps);
    }
    
    var setStlCommandInput = function (func, isMulti) {
        stlCommandInput = func;
        isMultitemp = isMulti;
    };

    /**
     * 重新加载质押券信息
     */
    var reloadPledgeBond = function(repoAssetList){
        pledgeBondTotalTbl.table ? pledgeBondTotalTbl.reload({data:repoAssetList, url:null}) : null;
    };
    
    var getElementById = function (id) {
        return $("#" + entityNameBussRepoSecPtl + "_" + id);
    };

    var tempPledgePrin;
    
    // 预览按钮在产品模板新建时不显示
    //增加ignore 属性
    var buttons = [{ text: '&#xe614;',ignore:true, clas: 'preview', title: '预览' }, { text: '&#xe640;',ignore:true, clas: 'delt', title: '删除' }];
    if (getElementById('pageId').val() != 'TrdRepoTrade003'){
        buttons = ['delt'];
    }

    var entrustSurAmtAll = function (number) {
        entrustSurAmt =number;
    }

    //按照比例计算到期结算金额和交易金额
    function extractedCpriceAmt() {
        var tradeAmtAll = $('#prinAmt').val();
        var all = 0;
        var all1 = 0;
        var all2 = 0;
        for (var i = 0; i < pledgeBondTotalTbl.getAllRows().length; i++) {
            if(!pledgeBondTotalTbl.getAllRows()[i].rebackPrice){
                pledgeBondTotalTbl.renderRow(i, {"rebackPrice":$('#rebackPrice').val()});
            }
            var tradeAmt = pledgeBondTotalTbl.getAllRows()[i].prinAmt;
            var commission =  pledgeBondTotalTbl.getAllRows()[i].commission;
            if ($('#mdateSettleAmt').val()) {//计算到期结算金额
                if (i != pledgeBondTotalTbl.getAllRows().length - 1) {//非最后一列
                    var cpriceAmt =  JoyinMath.doMul(isNaN(tradeAmt)?0:tradeAmt,$('#mdateSettleAmt').val(),2);
                    cpriceAmt = JoyinMath.doDiv(cpriceAmt,tradeAmtAll,2);
                    all = JoyinMath.doAdd(all, cpriceAmt, 2);
                    pledgeBondTotalTbl.renderRow(i, {"mdateSettleAmt": cpriceAmt});
                } else {
                    pledgeBondTotalTbl.renderRow(i, {"mdateSettleAmt": JoyinMath.doMinus($('#mdateSettleAmt').val(), all, 2)});
                }
            }
            if($('#tradeAmtAndFee').val()){
                if (i != pledgeBondTotalTbl.getAllRows().length - 1) {//非最后一列
                    var cpriceAmt =  JoyinMath.doMul(tradeAmt,$('#prinAmt').val(),2);
                    cpriceAmt = JoyinMath.doDiv(cpriceAmt,tradeAmtAll,2);
                    if($('#isPayWithTrade').val()=="Y"&&$('#ps').val()=='03'&&commission){
                        cpriceAmt = JoyinMath.doMinus(cpriceAmt,commission,2);
                    }else if($('#isPayWithTrade').val()=="Y"&&$('#ps').val()=='04'&&commission){
                        cpriceAmt = JoyinMath.doAdd(cpriceAmt,commission,2);
                    }
                    if(!commission){
                        cpriceAmt = JoyinMath.doAdd(cpriceAmt,0,2);
                    }
                    all1 = JoyinMath.doAdd(all1, cpriceAmt, 2);
                    pledgeBondTotalTbl.renderRow(i, {"tradeAmt": cpriceAmt});
                } else {
                    pledgeBondTotalTbl.renderRow(i, {"tradeAmt": JoyinMath.doMinus($('#tradeAmtAndFee').val(), all1, 2)});
                }
            }
            if($('#rebackIntAmt').val()){
                if (i != pledgeBondTotalTbl.getAllRows().length - 1) {//非最后一列
                    var cpriceAmt =  JoyinMath.doMul(tradeAmt,$('#rebackIntAmt').val(),2);
                    cpriceAmt = JoyinMath.doDiv(cpriceAmt,tradeAmtAll,2);
                    all2 = JoyinMath.doAdd(all2, cpriceAmt, 2);
                    pledgeBondTotalTbl.renderRow(i, {"rebackIntAmt": cpriceAmt});
                } else {
                    pledgeBondTotalTbl.renderRow(i, {"rebackIntAmt": JoyinMath.doMinus($('#rebackIntAmt').val(), all2, 2)});
                }
            }
        }
    }
    //计算结算费和手续费
    function extractedJiSuan(isTrue) {
        var tradeAmtAl = $('#prinAmt').val();
        var allprocedureAmt = 0;
        var allsettleAmt = 0;
        var allcommissionAmt = 0;
        var allstampDutyAmt = 0;
        var alltradeAmt = 0;
        for (var i = 0; i < pledgeBondTotalTbl.getAllRows().length; i++) {
            var tradeAmt = pledgeBondTotalTbl.getAllRows()[i].prinAmt;
            var doEntrustMoney = pledgeBondTotalTbl.getAllRows()[i].entrustSurAmt;
            if(isTrue){
                if($('#prinAmt').val()){
                    if (i != pledgeBondTotalTbl.getAllRows().length - 1) {//非最后一列
                        var num  = JoyinMath.doMul(tradeAmt,tradeAmtAl,2);
                        var iNum = JoyinMath.doDiv(num,entrustSurAmt,2);
                        if(Number(iNum)<=Number(doEntrustMoney)){
                            pledgeBondTotalTbl.renderRow(i, {"prinAmt": iNum});
                            alltradeAmt = JoyinMath.doAdd(alltradeAmt,iNum,2);
                        }else{
                            pledgeBondTotalTbl.renderRow(i, {"prinAmt": doEntrustMoney});
                            alltradeAmt = JoyinMath.doAdd(alltradeAmt,doEntrustMoney,2);
                        }
                    }else{
                        var numTo = JoyinMath.doMinus(tradeAmtAl,alltradeAmt,2);
                        if(Number(numTo)<=Number(doEntrustMoney)){
                            pledgeBondTotalTbl.renderRow(i, {"prinAmt": numTo});
                        }else{
                            pledgeBondTotalTbl.renderRow(i, {"prinAmt": doEntrustMoney});
                        }
                    }
                }
            }
             tradeAmt = pledgeBondTotalTbl.getAllRows()[i].prinAmt;
             doEntrustMoney = pledgeBondTotalTbl.getAllRows()[i].entrustSurAmt;
            if ($('#procedureAmt').val()) {
                if (i != pledgeBondTotalTbl.getAllRows().length - 1) {//非最后一列
                    var procedureAmt  =  JoyinMath.doMul(tradeAmt,$('#procedureAmt').val(),2);
                    procedureAmt = JoyinMath.doDiv(procedureAmt,tradeAmtAl,2);
                    allprocedureAmt = JoyinMath.doAdd(allprocedureAmt, procedureAmt, 2);
                    pledgeBondTotalTbl.renderRow(i, {"tranFeeAmt": procedureAmt});
                } else {
                    pledgeBondTotalTbl.renderRow(i, {"tranFeeAmt": JoyinMath.doMinus($('#procedureAmt').val(), allprocedureAmt, 2)});
                }

            }
            if ($('#settleAmt').val()) {
                if (i != pledgeBondTotalTbl.getAllRows().length - 1) {//非最后一列
                    var settleAmt  =  JoyinMath.doMul(tradeAmt,$('#settleAmt').val(),2);
                    settleAmt = JoyinMath.doDiv(settleAmt,tradeAmtAl,2);
                    allsettleAmt = JoyinMath.doAdd(allsettleAmt, settleAmt, 2);
                    pledgeBondTotalTbl.renderRow(i, {"settlementFee": settleAmt});
                } else {
                    pledgeBondTotalTbl.renderRow(i, {"settlementFee": JoyinMath.doMinus($('#settleAmt').val(), allsettleAmt, 2)});
                }

            }
            if ($('#commissionAmt').val()) {
                if (i != pledgeBondTotalTbl.getAllRows().length - 1) {//非最后一列
                    var commissionAmt  =  JoyinMath.doMul(tradeAmt,$('#commissionAmt').val(),2);
                    commissionAmt = JoyinMath.doDiv(commissionAmt,tradeAmtAl,2);
                    allcommissionAmt = JoyinMath.doAdd(allcommissionAmt, commissionAmt, 2);
                    pledgeBondTotalTbl.renderRow(i, {"commission": commissionAmt});
                } else {
                    pledgeBondTotalTbl.renderRow(i, {"commission": JoyinMath.doMinus($('#commissionAmt').val(), allcommissionAmt, 2)});
                }
            }else{
                pledgeBondTotalTbl.renderRow(i, {"commission": Number(0.00)});
            }
        }
    }

    function extractedTime(){//时间优先
        var tradeAmt = $('#prinAmt').val();
        tradeAmt = JoyinMath.doAdd(tradeAmt,0,2);
        if(tradeAmt){
            var all = 0;
            var one  = 0;
            for (var i = 0; i < pledgeBondTotalTbl.getAllRows().length; i++) {
                var tradeAmt1 = pledgeBondTotalTbl.getAllRows()[i].prinAmt;
                var doEntrustMoney = pledgeBondTotalTbl.getAllRows()[i].entrustSurAmt;
                tradeAmt1 = JoyinMath.doAdd(tradeAmt1,0,2);
                all = JoyinMath.doAdd(all,tradeAmt1,2);
                //如果第一列的值比劵面总额大的话
                if(i==0&&Number(tradeAmt1)>Number(tradeAmt)){
                    if(Number(JoyinMath.doAdd(tradeAmt, 0, 2))<=Number(doEntrustMoney)){
                        pledgeBondTotalTbl.renderRow(0, {"prinAmt": JoyinMath.doAdd(tradeAmt, 0, 2)});
                    }else{
                        pledgeBondTotalTbl.renderRow(0, {"prinAmt": doEntrustMoney});
                    }
                    for(var j = 1; j <pledgeBondTotalTbl.getAllRows().length; j++){
                        pledgeBondTotalTbl.renderRow(j, {"prinAmt": JoyinMath.doDiv(0, 0, 2)});
                    }
                    break;
                }else{
                    if(Number(all)>Number(tradeAmt)){
                        var num =  JoyinMath.doMinus(tradeAmt,one,2);
                        if(Number(num)<=Number(doEntrustMoney)){
                            pledgeBondTotalTbl.renderRow(i, {"prinAmt": num});
                            one = JoyinMath.doAdd(one,num,2);
                        }else{
                            pledgeBondTotalTbl.renderRow(i, {"prinAmt": doEntrustMoney});
                            one = JoyinMath.doAdd(one,doEntrustMoney,2);
                        }
                    }else{
                        var num = JoyinMath.doAdd(tradeAmt1, 0, 2);
                        one = JoyinMath.doAdd(one,tradeAmt1,2);
                        if(Number(num)<=Number(doEntrustMoney)){
                            pledgeBondTotalTbl.renderRow(i, {"prinAmt": num});
                        }else{
                            pledgeBondTotalTbl.renderRow(i, {"prinAmt": doEntrustMoney});
                        }
                    }
                }
            }
        }
    }

    /**
     * 定义质押券信息列表（Total）
     */
    var pledgeBondTotalTbl = getElementById("pledgeBondTotalList").joyintable({
        page: false,
        data: [],
        height: 200,
        /**
         * 定义表头
         */
        cols: [
            // 委托代码
            {field: 'entrustId',title: 'BussRepoSecPtl.entrustId',width:100,type: 'text',align: 'left',sortable: false,edit: false}
            // 组合代码
            ,{field: 'portfolioId',title: 'BussRepoSecPtl.portfolioId',width:100,type: 'text',align: 'left',sortable: false,
                edit: true,
                editOption:{type:'select',showField:'id',id:"portfolioId",width:100,childrenIds:"secAcctId",formatoption:true,url: JoyinUtil.getRealPath('/business/common/CommonSelect/selectPortfolioId?act=true')}
             }
            // 组合名称
            ,{field: 'portfolioName',title: 'BussRepoSecPtl.portfolioName',width:100,type: 'text',align: 'left',edit: false,sortable: false,visible:false}
            // 剩余委托额度
            ,{field: 'entrustSurAmt',title: 'BussRepoSecPtl.entrustSurAmt',width:100,type: 'number',align: 'left',edit: false,sortable: false,digit:2}
            // 证券账户
            ,{field: 'secAcctId',title: 'BussRepoSecPtl.secAcctId',width:100,type: 'text',align: 'left',edit:true,sortable:false,
                editOption:{type:'select',showField:'text',id:'secAcctId',width:100,parentIds:"portfolioId,tradeMarket",formatoption:true, url: JoyinUtil.getRealPath('/component/trade/BussRepoSecPtl/getSecManageAcctId')}}
            // 交易金额
            ,{field: 'tradeAmt',title: 'BussRepoSecPtl.tradeAmt',width:100,type: 'number',edit: false,sortable: false, digit:2}
            // 质押券汇总券面总额
            ,{field: 'totalPrinAmt',title: 'BussRepoSecPtl.totalPrinAmt',width:100,type: 'number',edit: false,sortable: false, digit:2}
           // 添加质押券
            ,{field: 'addPledgeInfo',width:100,title: 'BussRepoSecPtl.addPledgeInfo',
                render:function (data, type, row, meta) {
                return '<a class="btn_add_plegin" style="padding-left: 0px;display:block;width: 40px;height: 20px;line-height: 20px;text-align: center;background-color: rgb(38, 147, 255);border-radius: 2px;color: #fff;margin: 3px 15px;">添加</a>'}}
            //操作
            ,{field:'operation',title: 'SysCommon.operation',type:'action',buttons: buttons}
        ],
        addbtn:true,
        done:function(){
             this.bind('click', '.delt', function(data, index) {
                 var portfolioId=data.portfolioId;
                 var pbtd2=pledgeBondTotalTblDetailed.getAllRows();
                 for(var i in pbtd2){
                     if(pbtd2!=null&&pbtd2!=""){
                         if(pbtd2[i].portfolioId==portfolioId){
                             pledgeBondTotalTblDetailed.removeRow(pbtd2[i]);
                         }
                     }
                 }
                 var object = {selectedItem: [data], operateType:"delete"};
                 pledgeBondTotalTbl.deleteRow(index);
                 var pbtd1=pledgeBondTotalTbl.getAllRows();
                 if(data.LAY_TABLE_INDEX==0&&pbtd1.length==0){
//                     $("#bussRepoSecPtl_main").find('div.layui-table-header').css('overflow','auto');  
                 }
                 createAuto();
             });
        },
       beforeEditCell: function (preVal, field, rowData) {
            if (field === "pledgePrin") {
                tempPledgePrin = preVal.replace(/\,/g, '');
            }
           if(field=="secAcctId"){
               if($('#chlId').val()){
                   pledgeBondTotalTbl.renderRow(rowData.LAY_TABLE_INDEX,  {"secAcctId": ""});
                   return false;
               }else{
                   return true;
               }
           }else{
               return true;
           }
            return true;
        },
        editCell: function (value, data, field, cell, column) {
            //获取标准
            if(field=='secAcctId'){
                if(value&&$('#repoType').val()=='H02'){
                    var rowData = {};
                    var param = {};
                    param.portfolioId = data.portfolioId;
                    param.secAcctId = value;
                    Joyin.ajaxJson(JoyinUtil.getRealPath('/invest/trade/trdstandardbond/TrdStandardBond001/selectListData'), param, function (result) {
                        if(result[0]){
                            rowData.avaStandardStockPosition =JoyinMath.doAdd(result[0].avaStrdPrinAmtSum,0,2);
                            pledgeBondTotalTbl.renderRow(data.LAY_TABLE_INDEX, rowData);
                        }
                    },false);
                }
                //交易所可用资金
                if($('#ps').val()=='04'&&$('#tradeMarket').val()&&($('#tradeMarket').val()=='03'||$('#tradeMarket').val()=='04')
                    && value && value.trim()&&data.portfolioId){
                    var rowData = {};
                    var param = {};
                    param.portfolioId=data.portfolioId;
                    param.secManageAcctId=value;
                    param.ccy=$('#ccy').val();
                    param.calendarId = $('#calendarId').val();
                    Joyin.ajaxJson(JoyinUtil.getRealPath('/invest/trade/bond/TrdBondInvestTrade003/getBankCash'), param, function (result) {
                        if(result){
                            rowData.trdAcctAbleCash = Number(result);
                            pledgeBondTotalTbl.renderRow(data.LAY_TABLE_INDEX,rowData);
                        }
                    },false);
                }
            }
          /*  setInputParam();
            var avalibleCash=param.data.avalibleCash;
            var avalibleCashT1=param.data.avalibleCash;
            var ps = param.data.ps;
            
            if (ps == BACK_LEND_DIRECTION_04) {
                pledgeBondTotalTbl.renderRow(data.LAY_TABLE_INDEX, {avalibleCash:avalibleCash});
                pledgeBondTotalTbl.renderRow(data.LAY_TABLE_INDEX, {avalibleCashT1:avalibleCashT1});
            }*/
            setInputParam();
            if("portfolioId"==field){
                if(value){
                    pledgeBondTotalTbl.renderRow(data.LAY_TABLE_INDEX,  {"secAcctId": ""});
                }
                var params={};
                if(value &&$("#ccy").val() &&  $("#calendarId").val()&& $("#tradeDate").val()){
                    if($('#chlId').val()){
                        params.chlId = $('#chlId').val();
                    }
                    params.portfolioId=value;
                    params.calendarId=$("#calendarId").val();
                    params.tradeDate=$("#tradeDate").val();
                    params.acctTypeList = ACCT_TYPE_A01 + "," + ACCT_TYPE_A07;
                    params.ccy=$("#ccy").val();
                    //modify by wangpeng 20190903 组合为空时  查询抛出异常
                    if(params.portfolioId&&params.tradeDate&&params.ccy){
                        Joyin.ajaxJson(JoyinUtil.getRealPath('/component/trade/BussTrdAvailableCashQuery/getAvailableCash'), params, function (cashdata) {
                            pledgeBondTotalTbl.renderRow(data.LAY_TABLE_INDEX, {"avalibleCash": cashdata.T_0});
                            pledgeBondTotalTbl.renderRow(data.LAY_TABLE_INDEX, {"avalibleCashT1": cashdata.T_1});
                            pledgeBondTotalTbl.renderRow(data.LAY_TABLE_INDEX, {"channelAvailableCash": cashdata.T_2});
                        },false);
                    }
                    //modify by wangpeng 20190903 end
                }
            }

            if("prinAmt"==field){
                if($('#bussRepoSecPtl_distribution').val() == '139'){
                    $('#prinAmt_pinyun_isChange').val("true");
                }else if($('#bussRepoSecPtl_distribution').val() == '140'){
                    $('#prinAmt_shijian_isChange').val("true");
                }else if($('#bussRepoSecPtl_distribution').val() == '141'){
                    $('#prinAmt_shuliang_isChange').val("true");
                }
                extractedJiSuan();
                extractedCpriceAmt();
            }
            //费用改变的时候
            if(field=='commission'){
                var numberTwo = 0;
                //计算交易金额
                if (data.prinAmt) {
                    numberTwo = data.prinAmt;
                    if ($('#ps').val() == '04') {
                        //不是银行间
                        if ($('#tradeMarket').val() && ($('#tradeMarket').val() == '03' || $('#tradeMarket').val() == '04')) {
                            //佣金是否随交易支付
                            if (data.commission && $('#isPayWithTrade').val()=="Y") {
                                numberTwo = JoyinMath.doAdd(numberTwo, data.commission, 2);
                            }
                            if(!data.commission){
                                numberTwo = JoyinMath.doAdd(numberTwo, 0, 2);
                            }
                        }
                    } else if($('#ps').val() == '03'){
                        if ($('#tradeMarket').val() && ($('#tradeMarket').val() == '03' || $('#tradeMarket').val() == '04')) {
                            //是否随交易支付
                            if (data.commission && $('#isPayWithTrade').val()=="Y") {
                                numberTwo = JoyinMath.doMinus(numberTwo, data.commission, 2);
                            }
                            if(!data.commission){
                                numberTwo = JoyinMath.doMinus(numberTwo, 0, 2);
                            }
                        }
                    }
                    pledgeBondTotalTbl.renderRow(data.LAY_TABLE_INDEX, {"tradeAmt": JoyinMath.doAdd(numberTwo, 0, 2)});
                }
            }

        }
    });
    
    /**
     * 质押券汇总信息（Total）
     */
    var pledgeBondTotalTblDetailed = getElementById("pledgeBondTotalListDetailed").joyintable({
        page: false,
        data:  [],
        height: 200,
        /**
         * 定义表头
         */
        cols: [
            // 委托代码
            {field: 'entrustId',title: 'BussRepoSecPtl.entrustId',type: 'text',align: 'left',sortable: false,edit: false},
            // 债券代码
            {field: 'bondCode',title: 'BussRepoSecPtl.secId',type: 'text',align: 'left',sortable: false,edit: false}
            // 债券简称
            ,{field: 'bondAbbrName',title: 'BussRepoSecPtl.pledgeBondTotalTbl.colmodel.bondAbbrName',type: 'text',align: 'left',sortable: true,edit: false}
            // 债券类型
            ,{field: 'secTypeStr',title: 'BussRepoSecPtl.secType',type: 'text',align: 'left',edit: false,sortable: false}
            // 登记托管机构
            ,{field: 'repRegistOrgStr',title: 'BussRepoSecPtl.repRegistOrg',type: 'text',align: 'left',edit:false,sortable:false}
            // 组合代码
            ,{field: 'portfolioId',title: 'BussRepoSecPtl.portfolioId',type: 'text',sortable: false,edit: false}
            // 组合名称
            ,{field: 'portfolioName',title: 'BussRepoSecPtl.portfolioName',type: 'text',edit: false,sortable: false}
            // 质押本方券券面总额
            , {field: 'pledgeBfPrin',title: '质押本方券券面总额',type: 'number',edit: true,sortable: false}
            // 质押融入券券面总额
            , {field: 'pledgeRrPrin',title: '质押融入券券面总额',type: 'number',edit: true,sortable: false}
            //质押券汇总券面总额
            , {field: 'pledgePrin',title: '质押券汇总券面总额',type: 'number',edit: false,sortable: false}
            //折算比例%
            ,{field: 'pledgeRatio',title: 'BussRepoSecPtl.pledgeBondTotalTbl.colmodel.pledgeRatio',type: 'number',edit: false,sortable: false}
            //可质押本方券券面总额
            ,{field: 'avaPledgeBfPrin',title: '可质押本方券券面总额',type: 'number',edit: false,sortable: false}
            //可质押融入券券面总额
            ,{field: 'avaPledgeRrPrin',title: '可质押融入券券面总额',type: 'number',edit: false,sortable: false}
           //操作
           ,{field:'operation',title: '删除',type:'action',buttons: buttons}
        ],
        done:function(){
            this.bind('click', '.delt', function(data, index) {
                var pbtd2=pledgeBondTotalTbl.getAllRows();
                for(var i in pbtd2){
                    if(pbtd2[i].portfolioId==data.portfolioId){
                        var totalPrinAmt2=Number(pbtd2[i].totalPrinAmt)-Number(data.pledgePrin);
                        if(totalPrinAmt2==0){
                            var totalPrinAmt2="0";
                            pledgeBondTotalTbl.renderRow(i, {totalPrinAmt:totalPrinAmt2});
                        }else{
                            pledgeBondTotalTbl.renderRow(i, {totalPrinAmt:totalPrinAmt2});
                        }
                    }
                }
                pledgeBondTotalTblDetailed.deleteRow(index);
                createAuto();
            });
       },
        beforeEditCell: function (preVal, field, rowData) {
            if (field === "pledgePrin") {
                tempPledgePrin = preVal.replace(/\,/g, '');
            }
            return true;
        },
        checkbox:false
    });
    
    $('body').on('change','#portfolioId',function(event,data){
        if(data.value[0] != ''){
            var selectData  = $('#portfolioId').parents('td').data('sd')[0];
            var obj = {'portfolioId':data.value[0].concat('_',selectData.object)};
            Joyin.ajaxSync(basePath + "component/trade/BussRepoSecPtl/portfolioName",obj,function(result){
                pledgeBondTotalTbl.renderRow(data.rowIndex, {portfolioName:result.portfolioName});
            });
        }else{
            pledgeBondTotalTbl.renderRow(data.rowIndex, {portfolioName:''});
        }
    });
    
    pledgeBondTotalTbl.bind('click', 'a.btn_add_plegin', function(data,index) {
        var data = {
                portfolioId: data.portfolioId,
                portfolioName: data.portfolioName,
                ps: $("#ps").val(),
               tradeMarket: $("#tradeMarket").val(),
                secAcctId: data.secAcctId,
                repoType: data.repoType,
                chlId: $("#chlId").val()
            }
            var object = {
                portfolioId: ['portfolioId', 'TrdRepoTrade002.portfolioId'],
                portfolioName: ['portfolioName', 'TrdRepoTrade002.portfolioName'],
                ps: ['ps', 'TrdRepoTrade002.ps'],
                secAcctId: ['secAcctId', 'TrdRepoTrade002.secAcctId'],
                repoType: ['repoType', 'TrdRepoTrade002.repoType'],
                chlId: ['chlId', 'TrdRepoTrade002.chlId'],
                data: data
            }
        param = object;
        Joyin.ajaxJson(basePath + 'component/trade/BussRepoSecPtl/check',
                param, function () {
                    if (entity == null) {
                        entity = {};
                    }
                    entity.pledgeBondList = pledgeBondTotalTblDetailed.getAllRows();
                    $.each(entity.pledgeBondList,function (index,data) {
                        if(data.avaPledgeBfPrin){
                            data.avaPledgePrin = data.avaPledgeBfPrin;
                        }else if(data.avaPledgeRrPrin){
                            data.avaPledgePrin = data.avaPledgeRrPrin;
                        }
                    });
                    Joyin.dialog(JoyinUtil.getLabel('BussRepoSecPtl.addPledgeInfo'),basePath+
                        'component/trade/BussRepoSecPtlDialog/init', '98%', '98%',
                        {
                            portfolioId: param.data.portfolioId,
                            portfolioName: param.data.portfolioName,
                            ps: param.data.ps,
                            secAcctId: param.data.secAcctId,
                            tradeMarket :param.data.tradeMarket,
                            chlId: param.data.chlId,
                            entity: JSON.stringify(entity),
                            entityName: entityNameBussRepoSecPtl,
                            statusValid: statusValid,
                            bizCode: bizCode,
                            pageModel: pageModel,
                            operationType: operationType,
                            workflowId: workflowId
                        });
                },false);
        
    });


    /**
     * 保存按钮按下，添加子画面传回的质押券信息
     */
    var addPledgeBond = function (pledgeRows) {
        // 列表中未勾选
        if (!pledgeRows || pledgeRows.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        } else {
            var totalPrinAmt = Number(0);
            var portfolioId2 = null;
            for(var i in pledgeRows){
                totalPrinAmt=Number(totalPrinAmt)+Number(pledgeRows[i].pledgePrin);
                portfolioId2=pledgeRows[0].portfolioId;
            }
            var pbt=pledgeBondTotalTbl.getAllRows();
            for(var i in pbt){
                if(pbt[i].portfolioId==portfolioId2){
                    pledgeBondTotalTbl.renderRow(i, {totalPrinAmt:totalPrinAmt});
                }
            }
            if(pledgeRows!=null&&pledgeRows!=""){
                var pbtd=pledgeBondTotalTblDetailed.getAllRows();
                for(var i in pbtd){
                    if(pledgeRows[0].portfolioId == pbtd[i].portfolioId){
                        pledgeBondTotalTblDetailed.removeRow(pbtd[i]);
                    }
                }
                pledgeBondTotalTblDetailed.addRow(pledgeRows);
            }
             createAuto(null,$('#entrustId').val());
        }
    };

    /**
     * 清除数据并隐藏质押券信息
     */
    var clearHideRepo = function () {
        pledgeBondTotalTblDetailed.clear(function () {
            getElementById("totalPrinAmt").val(0).trigger('change').trigger('blur');
        });
        getElementById("main").hide();
    };

    /**
     * 显示质押券信息
     */
    var showRepo = function () {
        getElementById("main").show();
    };

    var createAuto = function (event,data){
        setInputParam();
        var entrustId2=param.data.entrustId;
        var TotlPrinAmt=param.data.prinAmt;
        var rebackPrice=param.data.rebackPrice;
        var rebackIntAmt=param.data.rebackIntAmt;
        var mdateSettleAmt=param.data.mdateSettleAmt;
        var channelFee=param.data.channelFee;
        var tradeAmtAndFee=param.data.tradeAmtAndFee;
        var procedureAmt=param.data.procedureAmt;
        var settleAmt=param.data.settleAmt;
        var commissionAmt=param.data.commissionAmt;
        
        var TotlPrinAmt3=param.data.prinAmt;
        var rebackPrice3=param.data.rebackPrice;
        var rebackIntAmt3=param.data.rebackIntAmt;
        var mdateSettleAmt3=param.data.mdateSettleAmt;
        var channelFee3=param.data.channelFee;
        var tradeAmtAndFee3=param.data.tradeAmtAndFee;
        var procedureAmt3=param.data.procedureAmt;
        var settleAmt3=param.data.settleAmt;
        var commissionAmt3=param.data.commissionAmt;
        
        var pbt=pledgeBondTotalTbl.getAllRows();
        var distribution=$("#bussRepoSecPtl_distribution").val();
        if(entrustId!=null&&entrustId!=""&&!data&&$('#ps').val()=='03'){//正回购
            var totalPrinAmtTotal=Number(0);
            for(var i in pbt){
                if(pbt[i].totalPrinAmt!=null&&pbt[i].totalPrinAmt!=""){
                    totalPrinAmtTotal=totalPrinAmtTotal+Number(pbt[i].totalPrinAmt);
                }
            }
            for(var i in pbt){
                if(pbt[i].totalPrinAmt!=null&&pbt[i].totalPrinAmt!=""&&pbt[i].totalPrinAmt!="0"){
                    if(i!=(pbt.length-1)){
                        var nu=Number(pbt[i].totalPrinAmt)/totalPrinAmtTotal;
                        var prinAmt2=Number(TotlPrinAmt)*nu;
                        var rebackIntAmt2=Number(rebackIntAmt)*nu;
                        var mdateSettleAmt2=Number(mdateSettleAmt)*nu;
                        var tradeAmtAndFee2=Number(tradeAmtAndFee)*nu;
                        var procedureAmt2=Number(procedureAmt)*nu;
                        var settleAmt2=Number(settleAmt)*nu;
                        var commissionAmt2=Number(commissionAmt)*nu;

                        TotlPrinAmt3=TotlPrinAmt-prinAmt2;
                        rebackIntAmt3=rebackIntAmt-rebackIntAmt2;
                        mdateSettleAmt3=mdateSettleAmt-mdateSettleAmt2;
                        tradeAmtAndFee3=tradeAmtAndFee-tradeAmtAndFee2;
                        procedureAmt3=procedureAmt-procedureAmt2;
                        settleAmt3=settleAmt-settleAmt2;
                        commissionAmt3=commissionAmt-commissionAmt2;
                        pledgeBondTotalTbl.renderRow(i, {prinAmt:prinAmt2});
                        pledgeBondTotalTbl.renderRow(i, {rebackPrice:rebackPrice});
                        pledgeBondTotalTbl.renderRow(i, {rebackIntAmt:rebackIntAmt2});
                        pledgeBondTotalTbl.renderRow(i, {mdateSettleAmt:mdateSettleAmt2});
                        pledgeBondTotalTbl.renderRow(i, {tradeAmt:tradeAmtAndFee2});
                        pledgeBondTotalTbl.renderRow(i, {tranFeeAmt:procedureAmt2});
                        pledgeBondTotalTbl.renderRow(i, {settlementFee:settleAmt2});
                        pledgeBondTotalTbl.renderRow(i, {commission:commissionAmt2});
                    }else{
                        pledgeBondTotalTbl.renderRow(i, {prinAmt:TotlPrinAmt3});
                        pledgeBondTotalTbl.renderRow(i, {rebackPrice:rebackPrice});
                        pledgeBondTotalTbl.renderRow(i, {rebackIntAmt:rebackIntAmt3});
                        pledgeBondTotalTbl.renderRow(i, {mdateSettleAmt:mdateSettleAmt3});
                        pledgeBondTotalTbl.renderRow(i, {tradeAmt:tradeAmtAndFee3});
                        pledgeBondTotalTbl.renderRow(i, {tranFeeAmt:procedureAmt3});
                        pledgeBondTotalTbl.renderRow(i, {settlementFee:settleAmt3});
                        pledgeBondTotalTbl.renderRow(i, {commission:commissionAmt3});
                    }
                }
                else{
                    var zero="0";
                    pledgeBondTotalTbl.renderRow(i, {prinAmt:zero});
                    pledgeBondTotalTbl.renderRow(i, {rebackPrice:zero});
                    pledgeBondTotalTbl.renderRow(i, {rebackIntAmt:zero});
                    pledgeBondTotalTbl.renderRow(i, {mdateSettleAmt:zero});
                    pledgeBondTotalTbl.renderRow(i, {tradeAmt:zero});
                    pledgeBondTotalTbl.renderRow(i, {tranFeeAmt:zero});
                    pledgeBondTotalTbl.renderRow(i, {settlementFee:zero});
                    pledgeBondTotalTbl.renderRow(i, {commission:zero});
                }
            }
        }else{
            // if(distribution=="139"){
            //     var i=pbt.length;
            //     TotlPrinAmt=TotlPrinAmt/i;
            //     var aa=pbt[0].prinAmt;
            //
            //     for(var j in pbt){
            //         pledgeBondTotalTbl.renderRow(j, {prinAmt:TotlPrinAmt});
            //     }
            // }
        }
    }
    
    /**
     * 画面加载完成
     */
    $(function () {
        if($('#entrustId').val()){
            // $('#bussRepoSecPtl_btn_autoAllocation').click(createAuto);
            // 加载质押券信息列表
            pledgeBondTotalTbl.load();
        }
         pledgeBondTotalTblDetailed.load();
        setInputParam();
        var entrustId=param.data.entrustId;
        if(entrustId==null||entrustId==""){
            $("#bussRepoSecPtl_distribution").val('').prop("disabled", true);
//            $("#bussRepoSecPtl_main").find('div.layui-table-header').css('overflow','auto');
        }
    });
    
    var showCash = function (autoHide) {
        pledgeBondTotalTblDetailed.clear();
        var pbt=pledgeBondTotalTbl.getAllRows();
        
        for(var i in pbt){
            var zero="0";
            pledgeBondTotalTbl.renderRow(i, {totalPrinAmt:zero});
        }
        createAuto();
    };
    
    var refreshCashAndBalance = function (autoHide) {
        var fun=autoHide();
        var pbt=pledgeBondTotalTbl.getAllRows();
        var avalibleCash=fun.avalibleCash;
        var avalibleCashT1=fun.avalibleCashT1;
        
        setInputParam();
        var ps=param.data.ps;
        if(fun.portfolioId!=null&&fun.portfolioId!=""){
            if (ps == BACK_LEND_DIRECTION_04) {
                for(var i in pbt){
                    if(pbt[i].portfolioId==fun.portfolioId){
                        pledgeBondTotalTbl.renderRow(i, {avalibleCash:avalibleCash});
                        pledgeBondTotalTbl.renderRow(i, {avalibleCashT1:avalibleCashT1});
                    }
                }
            }
        }
        return fun;
    };

    // 组合现金、持仓查询业务共通公开接口
    if (window.BussRepoSecPtl == undefined) {
        window.BussRepoSecPtl = {};
    }
    window.BussRepoSecPtl[entityNameBussRepoSecPtl] = {
        setInputParam: setInputParam,
        clearHideRepo: clearHideRepo,
        setStlCommandInput:setStlCommandInput,
        showRepo: showRepo,
        showCash: showCash,
        addPledgeBond: addPledgeBond,
        refreshCashAndBalance: refreshCashAndBalance,
        "pledgeBondTotalTbl":pledgeBondTotalTbl,
        "pledgeBondTotalTblDetailed":pledgeBondTotalTblDetailed,
        reloadPledgeBond: reloadPledgeBond,
        extractedJiSuan:extractedJiSuan,
        extractedCpriceAmt:extractedCpriceAmt,
        entrustSurAmtAll:entrustSurAmtAll,
        extractedTime:extractedTime
    };
}());