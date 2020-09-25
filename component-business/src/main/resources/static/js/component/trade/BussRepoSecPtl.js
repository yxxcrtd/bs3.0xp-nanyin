//@ sourceURL=BussRepoSecPtl.js
(function () {
    var param = {};

    var entrustSurAmt;
    var pledgeBondVal=null;
    /**
     * 设置初始参数
     * @param paramObject
     */
    var setInputParam = function (data) {
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
    function extractedJiSuan(isTrue,returnVal) {
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
            if (returnVal&&returnVal.tranFeeAmt==0) {
                if(pledgeBondTotalTbl.getAllRows().length>1){
                    if (i != pledgeBondTotalTbl.getAllRows().length - 1) {//非最后一列
	                    if(IS_AVERAGE_DISTRIBUTION_OF_EXPENSES=='Y'){
	            			var procedureAmt  =  JoyinMath.doDiv($('#procedureAmt').val()==""?0:$('#procedureAmt').val(),pledgeBondTotalTbl.getAllRows().length,2);
	                        allprocedureAmt = JoyinMath.doAdd(allprocedureAmt, procedureAmt, 2);
	                        pledgeBondTotalTbl.renderRow(i, {"tranFeeAmt": isNaN(procedureAmt)?0:procedureAmt});
	            		}else{
							var procedureAmt  =  JoyinMath.doMul(tradeAmt,$('#procedureAmt').val()==""?0:$('#procedureAmt').val(),2);
	                        procedureAmt = JoyinMath.doDiv(procedureAmt,tradeAmtAl,2);
	                        allprocedureAmt = JoyinMath.doAdd(allprocedureAmt, procedureAmt, 2);
	                        pledgeBondTotalTbl.renderRow(i, {"tranFeeAmt": isNaN(procedureAmt)?0:procedureAmt});
	            		}
                    } else {
						var all=JoyinMath.doMinus($('#procedureAmt').val()==""?0:$('#procedureAmt').val(), allprocedureAmt, 2);
                        all=isNaN(all)?0:all;
                        pledgeBondTotalTbl.renderRow(i, {"tranFeeAmt": all});
                    }
                }else{
                    var procedureAmt  =  JoyinMath.doMul(tradeAmt,$('#procedureAmt').val()==""?0:$('#procedureAmt').val(),2);
                    procedureAmt = JoyinMath.doDiv(procedureAmt,tradeAmtAl,2);
                    allprocedureAmt = JoyinMath.doAdd(allprocedureAmt, procedureAmt, 2);
                    pledgeBondTotalTbl.renderRow(i, {"tranFeeAmt": isNaN(procedureAmt)?0:procedureAmt});
                }
            }
            if (returnVal&&returnVal.settlementFee==0) {
                if(pledgeBondTotalTbl.getAllRows().length>1){
                    if (i != pledgeBondTotalTbl.getAllRows().length - 1) {//非最后一列
	                    if(IS_AVERAGE_DISTRIBUTION_OF_EXPENSES=='Y'){
	            			var settleAmt  =  JoyinMath.doDiv($('#settleAmt').val()==""?0:$('#settleAmt').val(),pledgeBondTotalTbl.getAllRows().length,2);
	                        allsettleAmt = JoyinMath.doAdd(allsettleAmt, settleAmt, 2);
	                        pledgeBondTotalTbl.renderRow(i, {"settlementFee": isNaN(settleAmt)?0:settleAmt});
	            		}else{
							var settleAmt  =  JoyinMath.doMul(tradeAmt,$('#settleAmt').val()==""?0:$('#settleAmt').val(),2);
	                        settleAmt = JoyinMath.doDiv(settleAmt,tradeAmtAl,2);
	                        allsettleAmt = JoyinMath.doAdd(allsettleAmt, settleAmt, 2);
	                        pledgeBondTotalTbl.renderRow(i, {"settlementFee": isNaN(settleAmt)?0:settleAmt});
	            		}
                    } else {
						var all=JoyinMath.doMinus($('#settleAmt').val()==""?0:$('#settleAmt').val(), allsettleAmt, 2);
                        all=isNaN(all)?0:all;
                        pledgeBondTotalTbl.renderRow(i, {"settlementFee": all});
                    }
                }else{
					var settleAmt  =  JoyinMath.doMul(tradeAmt,$('#settleAmt').val()==""?0:$('#settleAmt').val(),2);
                    settleAmt = JoyinMath.doDiv(settleAmt,tradeAmtAl,2);
                    allsettleAmt = JoyinMath.doAdd(allsettleAmt, settleAmt, 2);
                    pledgeBondTotalTbl.renderRow(i, {"settlementFee": isNaN(settleAmt)?0:settleAmt});
                }
            }
            if (returnVal&&returnVal.commission==0) {
                if(pledgeBondTotalTbl.getAllRows().length>1){
                    if (i != pledgeBondTotalTbl.getAllRows().length - 1) {//非最后一列
	                    if(IS_AVERAGE_DISTRIBUTION_OF_EXPENSES=='Y'){
	            			var commissionAmt  =  JoyinMath.doDiv($('#commissionAmt').val()==""?0:$('#commissionAmt').val(),pledgeBondTotalTbl.getAllRows().length,2);
	                        allcommissionAmt = JoyinMath.doAdd(allcommissionAmt, commissionAmt, 2);
	                        pledgeBondTotalTbl.renderRow(i, {"commission": isNaN(commissionAmt)?0:commissionAmt});
	            		}else{
							var commissionAmt  =  JoyinMath.doMul(tradeAmt,$('#commissionAmt').val()==""?0:$('#commissionAmt').val(),2);
	                        commissionAmt = JoyinMath.doDiv(commissionAmt,tradeAmtAl,2);
	                        allcommissionAmt = JoyinMath.doAdd(allcommissionAmt, commissionAmt, 2);
	                        pledgeBondTotalTbl.renderRow(i, {"commission": isNaN(commissionAmt)?0:commissionAmt});
	            		}
                    } else {
						var all=JoyinMath.doMinus($('#commissionAmt').val()==""?0:$('#commissionAmt').val(), allcommissionAmt, 2);
                        all=isNaN(all)?0:all;
                        pledgeBondTotalTbl.renderRow(i, {"commission": all});
                    }
                }else{
					var commissionAmt  =  JoyinMath.doMul(tradeAmt,$('#commissionAmt').val()==""?0:$('#commissionAmt').val(),2);
                    commissionAmt = JoyinMath.doDiv(commissionAmt,tradeAmtAl,2);
                    allcommissionAmt = JoyinMath.doAdd(allcommissionAmt, commissionAmt, 2);
                    pledgeBondTotalTbl.renderRow(i, {"commission": isNaN(commissionAmt)?0:commissionAmt});
                }
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
     * 定义质押式回购多组合列表（Total）
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
            // 本金金额
            ,{field: 'prinAmt',title: 'BussRepoSecPtl.prinAmt',width:100,type: 'number',sortable: false,edit: true, digit:2}
            // 回购利率%
            ,{field: 'rebackPrice',title: 'BussRepoSecPtl.rebackPrice',width:100,type: 'number',edit: false,sortable: false, digit:2}
            // 回购利息
            ,{field: 'rebackIntAmt',title: 'BussRepoSecPtl.rebackIntAmt',width:100,type: 'number',edit: false,sortable: false, digit:2}
            // 到期结算金额
            ,{field: 'mdateSettleAmt',title: 'BussRepoSecPtl.mdateSettleAmt',width:100,type: 'number',edit: false,sortable: false, digit:2}
            // 交易手续费
            ,{field: 'tranFeeAmt',title: 'BussRepoSecPtl.tranFeeAmt',width:100,type: 'number',edit: true,sortable: false, digit:2}
            // 结算费
            ,{field: 'settlementFee',title: 'BussRepoSecPtl.settlementFee',width:100,type: 'number',edit: true,sortable: false, digit:2}
            // 交易金额
            ,{field: 'tradeAmt',title: 'BussRepoSecPtl.tradeAmt',width:100,type: 'number',edit: false,sortable: false, digit:2}
            // 质押券汇总券面总额
            ,{field: 'totalPrinAmt',title: 'BussRepoSecPtl.totalPrinAmt',width:100,type: 'number',edit: false,sortable: false, digit:2}
            // 佣金
            ,{field: 'commission',title: 'BussRepoSecPtl.commission',width:100,type: 'number',edit: true,sortable: false, digit:2}
            // 组合T日可用现金
            ,{field: 'avalibleCash',title: 'BussRepoSecPtl.avalibleCash',width:100,type: 'number',edit: false,sortable: false, digit:2}
            // 组合T+1日可用现金
            ,{field: 'avalibleCashT1',title: 'BussRepoSecPtl.avalibleCashT1',width:100,type: 'number',edit: false,sortable: false, digit:2}
            // 通道可用现金
            ,{field: 'channelAvailableCash',title: function(){return JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("BussRepoSecPtl.channelAvailableCash");}(),width:100,type: 'number',edit: false,sortable: false, digit:2}
            // 交易所资金账户可用现金
            ,{field: 'trdAcctAbleCash',title: 'BussRepoSecPtl.trdAcctAbleCash',width:100,type: 'number',edit: false,sortable: false, digit:2}
            // 可用标准券额度
            ,{field: 'avaStandardStockPosition',title: 'BussRepoSecPtl.avaStandardStockPosition',width:100,type: 'number',edit: false,sortable: false, digit:2}
            // 添加质押券
            ,{field: 'addPledgeInfo',width:100,title: 'BussRepoSecPtl.addPledgeInfo',
                render:function (data, type, row, meta) {
                return '<a class="btn_add_plegin" style="padding-left: 0px;display:block;width: 40px;height: 20px;line-height: 20px;text-align: center;background-color: rgb(38, 147, 255);border-radius: 2px;color: #fff;margin: 3px 15px;">添加</a>'}}
            //操作
            ,{field:'operation',title: 'SysCommon.operation',type:'action',buttons: buttons}
        ],
        addbtn:true,
        done:function(){
             if($('#ps').val()=='04'){
                 $('.laytable-cell-5-0-15').remove();
                 $('.laytable-cell-4-0-13').remove();
             }
             
             var entrustId = $("#entrustId").val();
             if(entrustId){
                 $('.btn_add_plegin').remove();
                 $('.laytable-cell-4-0-13').remove();
             }
             
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
            var entrustId = $("#entrustId").val();
            if (field === "pledgePrin") {
                tempPledgePrin = preVal.replace(/\,/g, '');
            }
            
            if (field === "portfolioId"&&entrustId) {
                return false;
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
            var returnVal={};
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
                //手续费和结算费
                returnVal = calFee(data);
                //ADD BY XR 账户和组合变更时也触发重新计算，否则在账户变更时，费用未重新计算导致提交不了
                extractedCpriceAmt();
                extractedJiSuan3();
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
                //ADD BY XR 账户和组合变更时也触发重新计算，否则在账户变更时，费用未重新计算导致提交不了
                extractedCpriceAmt();
                extractedJiSuan3();
            }

            if("prinAmt"==field){
                var value2=value.split(',');
                var prinAmtVal='';
                for(var i in value2){
                    prinAmtVal=(prinAmtVal+value2[i])-0;
                }
                pledgeBondTotalTbl.renderRow(data.LAY_TABLE_INDEX, {"prinAmt": prinAmtVal});
                returnVal = calFee(data);
                if($('#bussRepoSecPtl_distribution').val() == '139'){
                    $('#prinAmt_pinyun_isChange').val("true");
                }else if($('#bussRepoSecPtl_distribution').val() == '140'){
                    $('#prinAmt_shijian_isChange').val("true");
                }else if($('#bussRepoSecPtl_distribution').val() == '141'){
                    $('#prinAmt_shuliang_isChange').val("true");
                }
//                extractedJiSuan('',returnVal);
                extractedCpriceAmt();
                extractedJiSuan3();
            }
            if('tranFeeAmt'==field){//手续费改变的时候
                var tranFeeAmtTotal = 0;
                for (var i = 0; i < pledgeBondTotalTbl.getAllRows().length; i++) {
                    if( pledgeBondTotalTbl.getAllRows()[i].tranFeeAmt){
                        tranFeeAmtTotal =  JoyinMath.doAdd(tranFeeAmtTotal,pledgeBondTotalTbl.getAllRows()[i].tranFeeAmt,2);
                    }
                }
//                $('#procedureAmt').val(tranFeeAmtTotal);
            }
            if('settlementFee'==field){//结算费改变的时候
                var settlementFeeTotal = 0;
                for (var i = 0; i < pledgeBondTotalTbl.getAllRows().length; i++) {
                    if( pledgeBondTotalTbl.getAllRows()[i].settlementFee){
                        settlementFeeTotal =  JoyinMath.doAdd(settlementFeeTotal,pledgeBondTotalTbl.getAllRows()[i].settlementFee,2);
                    }
                }
//                $('#settleAmt').val(settlementFeeTotal);
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
                            if (data.commission && ($('#isPayWithTrade').val()=="Y"||returnVal.isPayWithTrade=='Y')) {
                                numberTwo = JoyinMath.doAdd(numberTwo, data.commission, 2);
                            }
                            if(!data.commission){
                                numberTwo = JoyinMath.doAdd(numberTwo, 0, 2);
                            }
                        }
                    } else if($('#ps').val() == '03'){
                        if ($('#tradeMarket').val() && ($('#tradeMarket').val() == '03' || $('#tradeMarket').val() == '04')) {
                            //是否随交易支付
                            if (data.commission && ($('#isPayWithTrade').val()=="Y"||returnVal.isPayWithTrade=='Y')) {
                                numberTwo = JoyinMath.doMinus(numberTwo, data.commission, 2);
                            }
                            if(!data.commission){
                                numberTwo = JoyinMath.doMinus(numberTwo, 0, 2);
                            }
                        }
                    }
                    pledgeBondTotalTbl.renderRow(data.LAY_TABLE_INDEX, {"tradeAmt": JoyinMath.doAdd(numberTwo, 0, 2)});
                    $('#commission').val(numberTwo);
                }
            }

        }
    });
    /**
     * 计算手续费和结算费用
     */
    var calFee = function(data){
        var returnVal={
            isPayWithTrade:'Y',
            tranFeeAmt:0,
            settlementFee:0,
            commission:0
        };
        if(!$("#tradeDate").val()){
            return;
        }
        if(!data.secAcctId){
            return;
        }
        if(!$("#ps").val()){
            return;
        }
        if(!data.prinAmt){
            return;
        }
        if($("#tradeMarket").val() == TRADE_PLACE_01 && !$("#termTypeReback").val()){
            return;
        }
        if (pageModel == PAGE_MODEL_REFERENCE){
            // 审批人审批或者生效的交易不再重新计算
            return;
        }
        var object = $.serializeToObject('#submitForm', true);
        object.secAcctId = data.secAcctId;
        object.portfolioId = data.portfolioId;
        object.prinAmt =data.prinAmt;
        var tradeAmtAndFee = object.tradeAmtAndFee;
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
                        var prinAmt =  data.prinAmt;
                        var feeAmt2;
                        if(!tradeAmtAndFee && Number(tradeAmtAndFee) > 0){
                            var num = JoyinMath.doDiv(prinAmt, tradeAmtAndFee, 2);
                            feeAmt2 = JoyinMath.doMul(num,v.feeAmt,2);
                        }
                        // 佣金设置
                        if(v.feeType=='B05'&&v.feeAmt){
                            cntC++;
                            returnVal.isPayWithTrade =v.isPayWithTrade;
                            returnVal.commission = v.feeAmt;
                            pledgeBondTotalTbl.renderRow(data.LAY_TABLE_INDEX, {"commission": feeAmt2});//设置佣金

                        }
                        if(v.feeType=='B06'){//手续费
                            cntP++;
                            if (v.feeAmt) {
                                returnVal.tranFeeAmt = v.feeAmt;
                                pledgeBondTotalTbl.renderRow(data.LAY_TABLE_INDEX, {"tranFeeAmt": feeAmt2});//手续费
                            }
                        }
                        else if(v.feeType=='B08'){//结算费
                            cntS++;
                            if (v.feeAmt) {
                                returnVal.settlementFee = v.feeAmt;
                                pledgeBondTotalTbl.renderRow(data.LAY_TABLE_INDEX, {"settlementFee": feeAmt2});//结算费
                            }
                        }


                    });
                    // 按照最新费用规则计算相关费用无结果则清空相关费用
                    if(cntP == 0){
                        pledgeBondTotalTbl.renderRow(data.LAY_TABLE_INDEX, {"tranFeeAmt": ''});//手续费
                    }else{
                    	 var tranFeeAmtTotal;
                    	 for (var i = 0; i < pledgeBondTotalTbl.getAllRows().length; i++) {
                             if( pledgeBondTotalTbl.getAllRows()[i].tranFeeAmt){
                                 tranFeeAmtTotal =  JoyinMath.doAdd(tranFeeAmtTotal,pledgeBondTotalTbl.getAllRows()[i].tranFeeAmt,2);
                             }
                         }
                         if (tranFeeAmtTotal != undefined) {
//                         	$('#procedureAmt').val(tranFeeAmtTotal);
                         }
                     }
                     if(cntS == 0){
                         pledgeBondTotalTbl.renderRow(data.LAY_TABLE_INDEX, {"settlementFee": ''});//结算费
                     }else{
                         var settlementFeeTotal;
                         for (var i = 0; i < pledgeBondTotalTbl.getAllRows().length; i++) {
                             if( pledgeBondTotalTbl.getAllRows()[i].settlementFee){
                                 settlementFeeTotal =  JoyinMath.doAdd(settlementFeeTotal,pledgeBondTotalTbl.getAllRows()[i].settlementFee,2);
                             }
                         }
                        if (settlementFeeTotal != undefined) {
//                         	$('#settleAmt').val(settlementFeeTotal);
                         }
                     }
                    if(cntC == 0) {
                        pledgeBondTotalTbl.renderRow(data.LAY_TABLE_INDEX, {"commission": ''});//设置佣金
                    }else{
                        var commissionTotal = 0;
                        for (var i = 0; i < pledgeBondTotalTbl.getAllRows().length; i++) {
                            if( pledgeBondTotalTbl.getAllRows()[i].commission){
                                commissionTotal =  JoyinMath.doAdd(commissionTotal,pledgeBondTotalTbl.getAllRows()[i].commission,2);
                            }
                        }
//                        $('#commissionAmt').val(commissionTotal);
                    }
                }else{
                    pledgeBondTotalTbl.renderRow(data.LAY_TABLE_INDEX, {"tranFeeAmt": ''});//手续费
                    pledgeBondTotalTbl.renderRow(data.LAY_TABLE_INDEX, {"settlementFee": ''});//结算费
                    pledgeBondTotalTbl.renderRow(data.LAY_TABLE_INDEX, {"commission": ''});//设置佣金
                }
            }
        },false);
            return returnVal;
    };

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
            , {field: 'pledgeRrPrin',title: '质押融入券券面总额',type: 'number',edit: true,sortable: false, visible: false}
            //质押券汇总券面总额
            , {field: 'pledgePrin',title: '质押券汇总券面总额',type: 'number',edit: false,sortable: false}
            //折算比例%
            ,{field: 'pledgeRatio',title: 'BussRepoSecPtl.pledgeBondTotalTbl.colmodel.pledgeRatio',type: 'number',edit: false,sortable: false}
            //可质押本方券券面总额
            ,{field: 'avaPledgeBfPrin',title: '可质押本方券券面总额',type: 'number',edit: false,sortable: false}
            //可质押融入券券面总额
            ,{field: 'avaPledgeRrPrin',title: '可质押融入券券面总额',type: 'number',edit: false,sortable: false, visible: false}
            //可质押券面总额
            ,{field: 'avaPledgePrin',title: '可质押券面总额',type: 'number',edit: false}
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
                            finprodId: param.data.finprodId,
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
        debugger;
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
//             createAuto(null,$('#entrustId').val());
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
        }else{
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
    
    var pleBondTotalVal=function(){
        var object = $.serializeToObject('#submitForm', true);
        var ps=object.ps;
        var prinAmt=object.prinAmt;
        var bussRepoSec=[];
        var bussRepoSecPtl=[];
        if(object.bussRepoSec&&object.bussRepoSecPtl){
            bussRepoSec=object.bussRepoSec.pledgeBondList;
            bussRepoSecPtl=object.bussRepoSecPtl.pledgeBondList;
        }
        
        if(ps==04){
            if(bussRepoSec&&bussRepoSecPtl){
                pledgeBondTotalTblDetailed.clear();
                var para = [];
                for(var j=0; j < bussRepoSec.length;j++){
                    var pledgePrin=bussRepoSec[j].pledgePrin;
                    var pledgePrin2=bussRepoSec[j].pledgePrin;
                    for(var i=0;i< bussRepoSecPtl.length;i++){
                        var rowData={};
                        var num=bussRepoSecPtl[i].prinAmt/prinAmt;
                        var bussRepoSecVal=pledgePrin*num;
                        
                        rowData.entrustId = bussRepoSecPtl[i].entrustId;
                        rowData.bondCode = bussRepoSec[j].bondCode;
                        rowData.bondAbbrName = bussRepoSec[j].bondAbbrName;
                        rowData.secTypeStr = bussRepoSec[j].secTypeStr;
                        rowData.repRegistOrgStr = bussRepoSec[j].repRegistOrgStr;
                        rowData.portfolioId = bussRepoSecPtl[i].portfolioId;
                        rowData.portfolioName = bussRepoSecPtl[i].portfolioName;
                        rowData.pledgeRatio = bussRepoSec[j].pledgeRatio;
                                        
                        if(i==bussRepoSecPtl.length-1){
                            rowData.pledgePrin=pledgePrin2;
                        }else{
                            rowData.pledgePrin=bussRepoSecVal;
                            pledgePrin2=pledgePrin2-bussRepoSecVal;
                        }
                        para.push(rowData);
                    }
                }
                
                pledgeBondTotalTblDetailed.addRow(para);
                $('.laytable-cell-4-0-13').remove();
//                pledgeBondTotalTblDetailed.showColumns([{field:'avaPledgeBfPrin', visible: true}, {field:'avaPledgeRrPrin',visible: false}]);
            }
        }
    }
    
    function tabVal(data,i){
        pledgeBondTotalTbl.renderRow(i, {"entrustId":data[i].entrustId});
        pledgeBondTotalTbl.renderRow(i, {"portfolioId":data[i].portfolioId});
        pledgeBondTotalTbl.renderRow(i, {"entrustSurAmt":data[i].entrustSurAmt});
        pledgeBondTotalTbl.renderRow(i, {"secAcctId":data[i].secAcctId});
        pledgeBondTotalTbl.renderRow(i, {"avalibleCash":data[i].avalibleCash});
        pledgeBondTotalTbl.renderRow(i, {"avalibleCashT1":data[i].avalibleCashT1});
        pledgeBondTotalTbl.renderRow(i, {"channelAvailableCash":data[i].channelAvailableCash});
        pledgeBondTotalTbl.renderRow(i, {"trdAcctAbleCash":data[i].trdAcctAbleCash});
        pledgeBondTotalTbl.renderRow(i, {"avaStandardStockPosition":data[i].avaStandardStockPosition});
    }
    
    //质押券中的质押金额分配
    function repVal(data,i){
        debugger;
        var entrustId = $("#entrustId").val();
        var ps = $("#ps").val();
        if(entrustId&&ps=='03'){
            var totalPrinAmt='0';
            if(pledgeBondVal){
                for(var j in pledgeBondVal){
                    if(data[i].entrustId==pledgeBondVal[j].entrustId){
                        var num=JoyinMath.doMul(pledgeBondVal[j].pledgePrin,pledgeBondTotalTbl.getAllRows()[i].prinAmt==" "?'0':pledgeBondTotalTbl.getAllRows()[i].prinAmt,2);
                        var prinLast=JoyinMath.doDiv(num,data[i].entrustSurAmt,2);
                        pledgeBondTotalTblDetailed.renderRow(j, {"pledgePrin":prinLast});
                        totalPrinAmt=JoyinMath.doAdd(totalPrinAmt,prinLast,2);
                    }
                }
                
                pledgeBondTotalTbl.renderRow(i, {"totalPrinAmt":totalPrinAmt});
            }
        }
    }
    
    function distribution3(data){
        var object = $.serializeToObject('#submitForm', true);
        var secTotalPrin = object.prinAmt-0;
        var secTotalPrin2 = object.prinAmt-0;
        
        var doEntrustMoneyTotle = 0;
        for(var i=0;i < data.length;i++){
            doEntrustMoneyTotle=doEntrustMoneyTotle+(data[i].entrustSurAmt-0);
        }
        
        for(var i=0;i < data.length;i++){
            var num=JoyinMath.doMul(data[i].entrustSurAmt,secTotalPrin2,2);
            var tradeAmt=JoyinMath.doDiv(num,doEntrustMoneyTotle,2);
            
            if(i==data.length-1){
                pledgeBondTotalTbl.renderRow(i, {"prinAmt":secTotalPrin});
                
                tabVal(data,i);
                
                secTotalPrin=secTotalPrin-(tradeAmt-0);
            }else{
                pledgeBondTotalTbl.renderRow(i, {"prinAmt":tradeAmt});
                
                tabVal(data,i);
                
                secTotalPrin=secTotalPrin-(tradeAmt-0);
            }
        }
    }
    
    function distribution2(data,distriData){
        debugger;
        var object = $.serializeToObject('#submitForm', true);
        var secTotalPrin = object.prinAmt-0;
        for(var i=0;i < data.length;i++){
            var b=i;
            var tradeAmtTotle = data[i].entrustSurAmt-0;
            if(i!=data.length-1){
                for(var j=i+1;j < data.length+1;j++){
                    if(j!=data.length&&data[i][distriData]==data[j][distriData]){
                        tradeAmtTotle=tradeAmtTotle+(data[j].entrustSurAmt-0);
                        i++;
                    }else{
                        if(tradeAmtTotle!=data[b].entrustSurAmt){
                            tradeAmtTotle=tradeAmtTotle-0;
                            secTotalPrin=secTotalPrin-0;
                            if(tradeAmtTotle <= secTotalPrin){
                                for(var k=b;k<j;k++){
                                    if(k==data.length-1){
                                        pledgeBondTotalTbl.renderRow(k, {"prinAmt":secTotalPrin});
                                        
                                        tabVal(data,k);
                                        
                                        secTotalPrin=JoyinMath.doMinus(secTotalPrin,secTotalPrin,2)-0;
                                    }else{
                                        pledgeBondTotalTbl.renderRow(k, {"prinAmt":data[k].entrustSurAmt});
                                        
                                        tabVal(data,k);
                                        
                                        secTotalPrin=JoyinMath.doMinus(secTotalPrin,data[k].entrustSurAmt,2)-0;
                                    }
                                }
                            }else{
                                for(var k=b;k<j;k++){
                                    var div=JoyinMath.doMul(data[k].entrustSurAmt,secTotalPrin,2);
                                    var tradAmt=JoyinMath.doDiv(div,tradeAmtTotle,2);
                                    var secTotalPrin2;
                                    if(k==b){
                                        secTotalPrin2=secTotalPrin;
                                    }
                                    
                                    if(k==j-1){
                                        pledgeBondTotalTbl.renderRow(k, {"prinAmt":secTotalPrin2});
                                        
                                        tabVal(data,k);
                                        
                                        secTotalPrin=JoyinMath.doMinus(secTotalPrin,tradeAmtTotle,2)-0;
                                    }else{
                                        pledgeBondTotalTbl.renderRow(k, {"prinAmt":tradAmt});
                                        
                                        tabVal(data,k);
                                        
                                        secTotalPrin2=JoyinMath.doMinus(secTotalPrin2,tradAmt,2)-0;
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
            }
            
            
            if(secTotalPrin<=0&&tradeAmtTotle==data[i].entrustSurAmt){
                pledgeBondTotalTbl.renderRow(i, {"prinAmt":" "});
                
                tabVal(data,i);
                
                secTotalPrin=JoyinMath.doMinus(secTotalPrin,secTotalPrin,2);
            }else{
                if(tradeAmtTotle!=data[i].entrustSurAmt){
                    continue;
                }else{
                    if(i==data.length-1){
                        pledgeBondTotalTbl.renderRow(i, {"prinAmt":secTotalPrin});
                        
                        tabVal(data,i);
                        
                        secTotalPrin=JoyinMath.doMinus(secTotalPrin,secTotalPrin,2);
                    }else{
                        var amt = JoyinMath.doMinus(secTotalPrin,data[i].entrustSurAmt,2)-0;
                        if(amt<0){
                            pledgeBondTotalTbl.renderRow(i, {"prinAmt":secTotalPrin});
                            
                            tabVal(data,i);
                            
                            secTotalPrin = JoyinMath.doMinus(secTotalPrin,secTotalPrin,2)-0;
                        }else{
                            pledgeBondTotalTbl.renderRow(i, {"prinAmt":data[i].entrustSurAmt});
                            
                            tabVal(data,i);
                            
                            secTotalPrin = JoyinMath.doMinus(secTotalPrin,data[i].entrustSurAmt,2)-0;
                        }
                    }
                }
                
            }
        }
    }
    
    function extractedJiSuan3() {
        debugger;
        var zero="0";
        var object = $.serializeToObject('#submitForm', true);
        var tradeAmt = object.prinAmt==""?zero:object.prinAmt;
        var rebackIntAmt = object.rebackIntAmt==""?zero:object.rebackIntAmt;
        var procedureAmt = object.procedureAmt==""?zero:object.procedureAmt;
        var settleAmt = object.settleAmt==""?zero:object.settleAmt;
        var commissionAmt = object.commissionAmt==""?zero:object.commissionAmt;
        
        var rebackPrice = object.rebackPrice==""?zero:object.rebackPrice;
        var rebackPrice = object.rebackPrice==""?zero:object.rebackPrice;
        
        var ps=$('#ps').val();
        var chlId=$('#chlId').val();
        secTotalPrin=tradeAmt-0;
        
        var secTotalPrin2 = tradeAmt-0;
        
        var rebackIntAmtTotle = object.rebackIntAmt==""?zero:object.rebackIntAmt;
        var procedureAmtTotle = object.procedureAmt==""?zero:object.procedureAmt;
        var settleAmtTotle = object.settleAmt==""?zero:object.settleAmt;
        var commissionAmtTotle = object.commissionAmt==""?zero:object.commissionAmt;
        
        for (var i = 0; i < pledgeBondTotalTbl.getAllRows().length; i++) {
            pledgeBondTotalTbl.renderRow(i, {"rebackPrice": rebackPrice});
            
            var rebackIntAmt4 = JoyinMath.doMul(pledgeBondTotalTbl.getAllRows()[i].prinAmt==" "?zero:pledgeBondTotalTbl.getAllRows()[i].prinAmt,rebackIntAmt,2);
            var procedureAmt4 = JoyinMath.doMul(pledgeBondTotalTbl.getAllRows()[i].prinAmt==" "?zero:pledgeBondTotalTbl.getAllRows()[i].prinAmt,procedureAmt,2);
            var settleAmt4 = JoyinMath.doMul(pledgeBondTotalTbl.getAllRows()[i].prinAmt==" "?zero:pledgeBondTotalTbl.getAllRows()[i].prinAmt,settleAmt,2);
            var commissionAmt4 = JoyinMath.doMul(pledgeBondTotalTbl.getAllRows()[i].prinAmt==" "?zero:pledgeBondTotalTbl.getAllRows()[i].prinAmt,commissionAmt,2);
            //先设置劵面总额
            if(object.prinAmt
               &&object.rebackPrice
               &&pledgeBondTotalTbl.getAllRows()[i].portfolioId){
                if(pledgeBondTotalTbl.getAllRows().length>0){
                    if (i != pledgeBondTotalTbl.getAllRows().length - 1) {//非最后一列
                    if(IS_AVERAGE_DISTRIBUTION_OF_EXPENSES=='Y'){
	                        var rebackIntAmt2 = JoyinMath.doDiv(rebackIntAmt,pledgeBondTotalTbl.getAllRows().length,2);
	                        var procedureAmt2 = JoyinMath.doDiv(procedureAmt,pledgeBondTotalTbl.getAllRows().length,2);
	                        var settleAmt2 = JoyinMath.doDiv(settleAmt,pledgeBondTotalTbl.getAllRows().length,2);
	                        var commissionAmt2 = JoyinMath.doDiv(commissionAmt,pledgeBondTotalTbl.getAllRows().length,2);
	            		}else{
	                        var rebackIntAmt2 = JoyinMath.doDiv(rebackIntAmt4,secTotalPrin,2);
	                        var procedureAmt2 = JoyinMath.doDiv(procedureAmt4,secTotalPrin,2);
	                        var settleAmt2 = JoyinMath.doDiv(settleAmt4,secTotalPrin,2);
	                        var commissionAmt2 = JoyinMath.doDiv(commissionAmt4,secTotalPrin,2);
	            		}
                        
                        var mdateSettleAmt2 = JoyinMath.doAdd(pledgeBondTotalTbl.getAllRows()[i].prinAmt,rebackIntAmt2,2);
                        var tradeAmtAndFee2='0';
                        if(ps==03){
                            tradeAmtAndFee2 = JoyinMath.doMinus(pledgeBondTotalTbl.getAllRows()[i].prinAmt,commissionAmt2,2);
                        }else if(ps==04){
                            tradeAmtAndFee2 = JoyinMath.doAdd(pledgeBondTotalTbl.getAllRows()[i].prinAmt,commissionAmt2,2);
                        }
                        
                        pledgeBondTotalTbl.renderRow(i, {"rebackIntAmt": rebackIntAmt2});
                        pledgeBondTotalTbl.renderRow(i, {"mdateSettleAmt": mdateSettleAmt2});
                        pledgeBondTotalTbl.renderRow(i, {"tranFeeAmt": procedureAmt2});
                        pledgeBondTotalTbl.renderRow(i, {"settlementFee": settleAmt2});
                        pledgeBondTotalTbl.renderRow(i, {"commission": commissionAmt2});
                        pledgeBondTotalTbl.renderRow(i, {"tradeAmt": tradeAmtAndFee2});
                        
                        if(secTotalPrin2>0){
                            rebackIntAmtTotle = JoyinMath.doMinus(rebackIntAmtTotle,rebackIntAmt2,2);
                            procedureAmtTotle = JoyinMath.doMinus(procedureAmtTotle,procedureAmt2,2);
                            settleAmtTotle = JoyinMath.doMinus(settleAmtTotle,settleAmt2,2);
                            commissionAmtTotle = JoyinMath.doMinus(commissionAmtTotle,commissionAmt2,2);
                        }
                        
                        var tradeAmt3=isNaN(pledgeBondTotalTbl.getAllRows()[i].prinAmt-0)?0:(pledgeBondTotalTbl.getAllRows()[i].prinAmt-0);
                        secTotalPrin2=isNaN(secTotalPrin2)?0:secTotalPrin2;
                        if(secTotalPrin2==tradeAmt3-0&&(pledgeBondTotalTbl.getAllRows()[i].prinAmt==null||pledgeBondTotalTbl.getAllRows()[i].prinAmt==""||pledgeBondTotalTbl.getAllRows()[i].prinAmt==0)){
                            pledgeBondTotalTbl.renderRow(i, {"prinAmt": secTotalPrin2});
                            secTotalPrin2 = JoyinMath.doMinus(secTotalPrin2,secTotalPrin2,2);
                        }
                        if(secTotalPrin2<=0){
                            pledgeBondTotalTbl.renderRow(i, {"prinAmt": '0'});
                        }
                        
                        var pledgeBondTotalTblVal=pledgeBondTotalTbl.getAllRows();
                        repVal(pledgeBondTotalTblVal,i);
                        secTotalPrin2 = JoyinMath.doMinus(secTotalPrin2,pledgeBondTotalTbl.getAllRows()[i].prinAmt==" "?zero:pledgeBondTotalTbl.getAllRows()[i].prinAmt,2);
                    }else{
                        var tradeAmt3=isNaN(pledgeBondTotalTbl.getAllRows()[i].prinAmt-0)?0:(pledgeBondTotalTbl.getAllRows()[i].prinAmt-0);
                        secTotalPrin2=isNaN(secTotalPrin2)?0:secTotalPrin2;
                        if(secTotalPrin2==tradeAmt3-0&&(pledgeBondTotalTbl.getAllRows()[i].prinAmt==null||pledgeBondTotalTbl.getAllRows()[i].prinAmt==""||pledgeBondTotalTbl.getAllRows()[i].prinAmt==0)){
                            pledgeBondTotalTbl.renderRow(i, {"prinAmt": secTotalPrin2});
                            secTotalPrin2 = JoyinMath.doMinus(secTotalPrin2,secTotalPrin2,2);
                        }
                        
                        if(secTotalPrin2<=0){
                            pledgeBondTotalTbl.renderRow(i, {"prinAmt": '0'});
                        }
                        if(pledgeBondTotalTbl.getAllRows()[i].prinAmt-0>0){
                            
                            var mdateSettleAmt3 = JoyinMath.doAdd(pledgeBondTotalTbl.getAllRows()[i].prinAmt,rebackIntAmtTotle,2);
                            var tradeAmtAndFee3='0';
                            if(ps==03){
                                tradeAmtAndFee3 = JoyinMath.doMinus(pledgeBondTotalTbl.getAllRows()[i].prinAmt,commissionAmtTotle,2);
                            }else if(ps==04){
                                tradeAmtAndFee3 = JoyinMath.doAdd(pledgeBondTotalTbl.getAllRows()[i].prinAmt,commissionAmtTotle,2);
                            }
                            
                            pledgeBondTotalTbl.renderRow(i, {"rebackIntAmt": rebackIntAmtTotle});
                            pledgeBondTotalTbl.renderRow(i, {"mdateSettleAmt": mdateSettleAmt3});
                            pledgeBondTotalTbl.renderRow(i, {"tranFeeAmt": procedureAmtTotle});
                            pledgeBondTotalTbl.renderRow(i, {"settlementFee": settleAmtTotle});
                            pledgeBondTotalTbl.renderRow(i, {"commission": commissionAmtTotle});
                            pledgeBondTotalTbl.renderRow(i, {"tradeAmt": tradeAmtAndFee3});
                            
                            var pledgeBondTotalTblVal=pledgeBondTotalTbl.getAllRows();
                            repVal(pledgeBondTotalTblVal,i);
                            
                            secTotalPrin2 = JoyinMath.doMinus(secTotalPrin2,pledgeBondTotalTbl.getAllRows()[i].prinAmt==" "?zero:pledgeBondTotalTbl.getAllRows()[i].prinAmt,2);
                        }else{
                            for(var j = i; j>=0; j--){
                                if(pledgeBondTotalTbl.getAllRows()[j].prinAmt-0>0){
                                    var rebackIntAmtLast = JoyinMath.doAdd(pledgeBondTotalTbl.getAllRows()[j].rebackIntAmt==" "?zero:pledgeBondTotalTbl.getAllRows()[j].rebackIntAmt,rebackIntAmtTotle,2);
                                    var procedureAmtLast = JoyinMath.doAdd(pledgeBondTotalTbl.getAllRows()[j].tranFeeAmt==" "?zero:pledgeBondTotalTbl.getAllRows()[j].tranFeeAmt,procedureAmtTotle,2);
                                    var settleAmtLast = JoyinMath.doAdd(pledgeBondTotalTbl.getAllRows()[j].settlementFee==" "?zero:pledgeBondTotalTbl.getAllRows()[j].settlementFee,settleAmtTotle,2);
                                    var commissionAmtLast = JoyinMath.doAdd(pledgeBondTotalTbl.getAllRows()[j].commission==" "?zero:pledgeBondTotalTbl.getAllRows()[j].commission,commissionAmtTotle,2);
                                   
                                    var mdateSettleAmt4 = JoyinMath.doAdd(pledgeBondTotalTbl.getAllRows()[j].prinAmt,rebackIntAmtLast,2);
                                    var tradeAmtAndFee4='0';
                                    if(ps==03){
                                        tradeAmtAndFee4 = JoyinMath.doMinus(pledgeBondTotalTbl.getAllRows()[j].prinAmt,commissionAmtLast,2);
                                    }else if(ps==04){
                                        tradeAmtAndFee4 = JoyinMath.doAdd(pledgeBondTotalTbl.getAllRows()[j].prinAmt,commissionAmtLast,2);
                                    }
                                    
                                    pledgeBondTotalTbl.renderRow(j, {"rebackIntAmt": rebackIntAmtLast});
                                    pledgeBondTotalTbl.renderRow(j, {"mdateSettleAmt": mdateSettleAmt4});
                                    pledgeBondTotalTbl.renderRow(j, {"tranFeeAmt": procedureAmtLast});
                                    pledgeBondTotalTbl.renderRow(j, {"settlementFee": settleAmtLast});
                                    pledgeBondTotalTbl.renderRow(j, {"commission": commissionAmtLast});
                                    pledgeBondTotalTbl.renderRow(j, {"tradeAmt": tradeAmtAndFee4});
                                    
                                    var pledgeBondTotalTblVal=pledgeBondTotalTbl.getAllRows();
                                    repVal(pledgeBondTotalTblVal,i);
                                    
                                    break;
                                }else{
                                    pledgeBondTotalTbl.renderRow(j, {"rebackIntAmt": zero});
                                    pledgeBondTotalTbl.renderRow(j, {"mdateSettleAmt": zero});
                                    pledgeBondTotalTbl.renderRow(j, {"tranFeeAmt": zero});
                                    pledgeBondTotalTbl.renderRow(j, {"settlementFee": zero});
                                    pledgeBondTotalTbl.renderRow(j, {"commission": zero});
                                    pledgeBondTotalTbl.renderRow(j, {"tradeAmt": zero});
                                    
                                    var pledgeBondTotalTblVal=pledgeBondTotalTbl.getAllRows();
                                    repVal(pledgeBondTotalTblVal,i);
                                }
                            }
                        }
                    }
                }
            }
            
        }
    }
    
    function isIntVal2(ListDetailed,isIntVal){
        if(isIntVal){
            pledgeBondVal=ListDetailed;
        }
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
        distribution3:distribution3,
        distribution2:distribution2,
        extractedJiSuan3:extractedJiSuan3,
        extractedCpriceAmt:extractedCpriceAmt,
        entrustSurAmtAll:entrustSurAmtAll,
        extractedTime:extractedTime,
        calFee:calFee,
        isIntVal2:isIntVal2,
        pleBondTotalVal:pleBondTotalVal
    };
}());