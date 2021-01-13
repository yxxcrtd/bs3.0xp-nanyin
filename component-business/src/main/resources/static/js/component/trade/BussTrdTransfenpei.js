//@ sourceURL=BussTrdTransfenpei.js
var table;
(function () {
    var isCSTP = false;
	var tradeAmtAll=0;
	var returnValAll={
            isPayWithTrade:'Y',
            procedureAmt:0,
            settleAmt:0,
            commissionAmt:0,
            stampDutyAmt:0,
            stampDutyIsPayWithTrade:'Y'
        };
	
	//画面初始化
    var init = function() {
    	 table = $("#bussTransfenpei_Table").joyintable({
    	  data: (entity && entity.trdTransfenpeiDtoList) ? entity.trdTransfenpeiDtoList : [],
          searchOnInit:true,
          height:'225px',
          cols:[
              {field:'entrustId',  width:100,title: 'TrdInvestFenPeiInfo.entrustId',edit:true, sortable:false,type:'text'}
              ,{field:'portfolioId', width:200,  title: 'TrdInvestFenPeiInfo.busiId',edit:true,sortable: false,
                  editOption:{type:'select', multiple: false, searchable: true,id:"portfolioId",width:200,formatoption:true,
                      url: JoyinUtil.getRealPath('/business/common/CommonSelect/selectPortfolioId?act=true')
                  }
              }
              ,{field:'doEntrustMoney',  width:100,title: 'TrdInvestFenPeiInfo.doEntrustMoney',edit:true, sortable:false,edit:false,type:'number', digit:2}
               ,{field:'tradeAmt',  width:100,title: 'TrdInvestFenPeiInfo.tradeAmt',edit:true, sortable:false,type:'number', digit:2}
              // 本金金额
               ,{field:'cpriceAmt',width:100,  title: 'TrdInvestFenPeiInfo.cpriceAmt',sortable:false,type:'number', digit:2}
               ,{field:'intAmt',width:100, title: 'TrdInvestFenPeiInfo.intAmt',sortable:false,type:'number', digit:2}
               ,{field:'fpriceAmt',width:100, title: 'TrdInvestFenPeiInfo.fpriceAmt',sortable:false,type:'number', digit:2}
               ,{field:'bussTrdAvailableCashQueryavalibleCash', width:100,title: 'TrdInvestFenPeiInfo.bussTrdAvailableCashQueryavalibleCash',sortable:false,type:'number', digit:2}
               ,{field:'bussTrdAvailableCashQueryavalibleCash1',width:100, title: 'TrdInvestFenPeiInfo.bussTrdAvailableCashQueryavalibleCash1',sortable:false,type:'number', digit:2}
               ,{field:'operation', width:100,title: 'SysCommon.operation',sortable:false, type:'action', buttons: ['delt']}
          ],
          addbtn:true,
          done:function(){
              var data = table.getAllRows();
              var portfolioIdList = []
              if(data.length > 0){
                  $.each(data,function(i,v){
                      portfolioIdList.push(v.portfolioId);
                  });
                  $("#sec_portfolioId").val(portfolioIdList.join(","))
              }
              this.bind('click', '.delt', function(data, index) {
                  table.deleteRow(index);
              });

              if( !( $('#bussTrdTransfenpeidto_entrustId') && $('#bussTrdTransfenpeidto_entrustId').val() ) ){
                // 自动分配失效  分配方式失效
                $('#bussTransfenpei_distribution').attr('disabled',true);
                $('#bussTransfenpei_btn_autoAllocation').addClass('layui-btn-disabled');
              }

          },
          beforeEditCell:function(value, field, data){
              var entrustId = $("#entrustId").val();
              if(field=='portfolioId'&&entrustId){
                    return false;
              }
                if(field=='tradeAmt'&&entrustId){
                    return true;
                }
              
                if(field=="portfolioId"||field=="tradeAmt"){
                	return true;
                }else{
                    return false;
                }
            },
           editCell: function(value, data, field, cell, column){

              var returnVal = {};
        	   var params={};
			   //买入
        	   if("portfolioId"==field && "T01"==$("#ps").val()){
                   var rowData = {};
        		   if(value && value.trim()
                        && $("#calendarId").val() && $("#ccy").val() && $("#tradeDate").val()){
        			   params.portfolioId=value;
        			   params.calendarId=$("#calendarId").val();
        			   params.tradeDate=$("#tradeDate").val();
        			   params.acctTypeList = ACCT_TYPE_A01 + "," + ACCT_TYPE_A07;
        			   params.ccy=$("#ccy").val();
        			   Joyin.ajaxJson(JoyinUtil.getRealPath('/component/trade/BussTrdAvailableCashQuery/getAvailableCash'), params, function (cashdata) {
        				   table.renderRow(data.LAY_TABLE_INDEX, {"bussTrdAvailableCashQueryavalibleCash": cashdata.T_0});
        				   table.renderRow(data.LAY_TABLE_INDEX, {"bussTrdAvailableCashQueryavalibleCash1": cashdata.T_1});
        			   },false);
        		   }
        	   }else if("portfolioId"==field && "T01"!=$("#ps").val()){
        		   table.renderRow(data.LAY_TABLE_INDEX, {"bussTrdAvailableCashQueryavalibleCash": ''});
        		   table.renderRow(data.LAY_TABLE_INDEX, {"bussTrdAvailableCashQueryavalibleCash1": ''});

        	   }
        	   

        	   //只有券面总额发生变化才需要更新净价总额、应计利息总额
        	   if("tradeAmt"==field){
        	       isChange = true;
        	       //净价总额按比例分配
                    if(value&&$('#tradeAmt').val()){
                        extractedCpriceAmt();
                    }
                    //应计利息总额按照比率分配
                   if(value&&$('#tradeAmt').val()){
                       extractedIntAmt();
                   }
        		   //交易手续费和结算费按比计算
                   if(value&&$('#tradeAmt').val()){
                       extractedJiSuan(false,returnVal);
                   }
        	   }
        	   var data = table.getAllRows();
        	   var j=0;
               var portfolioIdList = []
                if(data.length > 0){
                    $.each(data,function(i,v){
                        portfolioIdList.push(v.portfolioId);
                        if (v.entrustId!=null&&v.entrustId!=''&&v.entrustId!='undefined') {
							j++;
						}
                    });
                    $("#sec_portfolioId").val(portfolioIdList.join(","))
                }
               // add by xingxiaokang 20200310 start ZGBUG-123无委托时，自动分配按钮置灰
//               if (j>0) {
//            	   $('#bussTransfenpei_btn_autoAllocation').parent().show();
//               }else{
//            	   $('#bussTransfenpei_btn_autoAllocation').parent().hide();
//               }
               // add by xingxiaokang 20200310 end
           }
	});
    };

	//按照比例计算净价总额
    function extractedCpriceAmt() {
        var tradeAmtAll = $('#tradeAmt').val();
        var all = 0;
        for (var i = 0; i < table.getAllRows().length; i++) {
            var tradeAmt = table.getAllRows()[i].tradeAmt;
            var result = JoyinMath.doDiv(tradeAmt, tradeAmtAll, 2);
            if ($('#cpriceAmt').val()) {
                if(table.getAllRows().length>1){
                    if (i != table.getAllRows().length - 1) {//非最后一列
                        var cpriceAmt =  JoyinMath.doMul(isNaN(tradeAmt)?0:tradeAmt,$('#cpriceAmt').val(),2);
                        cpriceAmt = JoyinMath.doDiv(cpriceAmt,tradeAmtAll,2);
                        all = JoyinMath.doAdd(all, cpriceAmt, 2);
                        table.renderRow(i, {"cpriceAmt": cpriceAmt});
                    } else {
                        table.renderRow(i, {"cpriceAmt": JoyinMath.doMinus($('#cpriceAmt').val(), all, 2)});
                    }
                }else{
                    var cpriceAmt =  JoyinMath.doMul(isNaN(tradeAmt)?0:tradeAmt,$('#cpriceAmt').val(),2);
                    cpriceAmt = JoyinMath.doDiv(cpriceAmt,tradeAmtAll,2);
                    all = JoyinMath.doAdd(all, cpriceAmt, 2);
                    table.renderRow(i, {"cpriceAmt": cpriceAmt});
                }

            }
        }
    }
    //按照比例计算应计利息总额
    function extractedIntAmt(intAmtPara) {
        var tradeAmtAll = $('#tradeAmt').val();
        var all = 0;
        for (var i = 0; i < table.getAllRows().length; i++) {
            var tradeAmt = table.getAllRows()[i].tradeAmt;
            if (typeof intAmtPara != 'undefined') {
                table.renderRow(i, {"intAmt": intAmtPara});
            } else if ($('#intAmt').val()) {
                if(table.getAllRows().length>1){
                    if (i != table.getAllRows().length - 1) {//非最后一列
                        var intAmt =  JoyinMath.doMul(tradeAmt, $('#intAmt').val(),2);
                        intAmt =  JoyinMath.doDiv(intAmt,tradeAmtAll,2);
                        all = JoyinMath.doAdd(all, intAmt, 2);
                        table.renderRow(i, {"intAmt": intAmt});
                    } else {
                        table.renderRow(i, {"intAmt": JoyinMath.doMinus($('#intAmt').val(), all, 2)});
                    }
                }else{
                    var intAmt =  JoyinMath.doMul(tradeAmt, $('#intAmt').val(),2);
                    intAmt =  JoyinMath.doDiv(intAmt,tradeAmtAll,2);
                    all = JoyinMath.doAdd(all, intAmt, 2);
                    table.renderRow(i, {"intAmt": intAmt});
                }

            }
        }
    }

    function extractedJiSuan(isTrue,returnVal) {
        var tradeAmtAl = $('#tradeAmt').val();
        var alltradeAmt = 0;
        for (var i = 0; i < table.getAllRows().length; i++) {
            var tradeAmt = table.getAllRows()[i].tradeAmt;
            var doEntrustMoney = table.getAllRows()[i].doEntrustMoney;
            var result = JoyinMath.doDiv(tradeAmt, tradeAmtAl, 2);
            //先设置劵面总额
            if(isTrue){
                if($('#tradeAmt').val()){
                    if(table.getAllRows().length>1){
                        if (i != table.getAllRows().length - 1) {//非最后一列
                            var num  = JoyinMath.doMul(tradeAmt,tradeAmtAl,2);
                            var iNum = JoyinMath.doDiv(num,tradeAmtAll,2);
                            if(Number(iNum)<=Number(doEntrustMoney)){
                                table.renderRow(i, {"tradeAmt": iNum});
                                alltradeAmt = JoyinMath.doAdd(alltradeAmt,iNum,2);
                            }else{
                                table.renderRow(i, {"tradeAmt": doEntrustMoney});
                                alltradeAmt = JoyinMath.doAdd(alltradeAmt,doEntrustMoney,2);
                            }
                        }else{
                            var numTo = JoyinMath.doMinus(tradeAmtAl,alltradeAmt,2);
                            if(Number(numTo)<=Number(doEntrustMoney)){
                                table.renderRow(i, {"tradeAmt": numTo});
                            }else{
                                table.renderRow(i, {"tradeAmt": doEntrustMoney});
                            }
                        }
                    }else{
                        var num  = JoyinMath.doMul(tradeAmt,tradeAmtAl,2);
                        var iNum = JoyinMath.doDiv(num,tradeAmtAll,2);
                        if(Number(iNum)<=Number(doEntrustMoney)){
                            table.renderRow(i, {"tradeAmt": iNum});
                            alltradeAmt = JoyinMath.doAdd(alltradeAmt,iNum,2);
                        }else{
                            table.renderRow(i, {"tradeAmt": doEntrustMoney});
                            alltradeAmt = JoyinMath.doAdd(alltradeAmt,doEntrustMoney,2);
                        }
                    }

                }
            }

        }
    }
    
  //分配方式
    $('#bussTransfenpei_btn_autoAllocation').click(function () {
        extractedZiDongFenPei();
    });
    
    function extractedZiDongFenPei() {
    	var isChange = false;
        if ($("#entrustId").val()) {
            //平均分配
            if ($('#bussTransfenpei_distribution').val() == '139') {
                isChange = false;
                table.clear();
                table.addRow(getAllRowsForEntrustId());
                setMoney(getAllRowsForEntrustId())
                extractedJiSuan(true,returnValAll);
                //触发计算
                isFTradeId();
                setTimeout(function () {
                    $('.btn-table-add').remove();
                    setTimeout(function () {
                        $('.delt').remove();
                    },1000)
                },1500);
            } else if ($('#bussTransfenpei_distribution').val() == '140') {//时间优先
                isChange = false;
                table.clear();
                table.addRow(getAllRowsForEntrustId());
                setMoney(getAllRowsForEntrustId())
                extractedTime();
                isFTradeId();
                extractedJiSuan(false,returnValAll);
                setTimeout(function () {
                    $('.btn-table-add').remove();
                    setTimeout(function () {
                        $('.delt').remove();
                    },1000)
                },1500);
            } else if ($('#bussTransfenpei_distribution').val() == '141') {//数量优先
                isChange = false;
                extractedNumber();
                //触发计算
                isFTradeId();
                extractedJiSuan(false,returnValAll);
                setTimeout(function () {
                    $('.btn-table-add').remove();
                    setTimeout(function () {
                        $('.delt').remove();
                    },1000)
                },1500);
            } else {
                //触发计算
                if(isChange){
                    extractedJiSuan(false,returnValAll);
                }else{
                    table.clear();
                    table.addRow(getAllRowsForEntrustId());
                    setMoney(getAllRowsForEntrustId())

                    extractedJiSuan(true,returnValAll);
                }
                isFTradeId();
                setTimeout(function () {
                    $('.btn-table-add').remove();
                    setTimeout(function () {
                        $('.delt').remove();
                    },1000)
                },1500);
            }
        }
    }
    
    function isFTradeId(){
        extractedJiSun();
        extracted();//平均分配方式
   };
   
   function extractedJiSun() {
       var paramObj = getCalcParam();
       paramObj.invAim = 0;
       Joyin.ajaxJson(JoyinUtil.getRealPath('/invest/trade/bond/TrdBondInvestTrade003/calculateData'), paramObj, function (result) {
           if (result && result.data) {
               var numberAll = 0;
               var data = result.data;
               if (!isCSTP) {
                   $("#tradeAmt").val(data.tradeAmt).trigger("blur");
                   $("#unitCprice").val(data.unitCprice).trigger("blur");
                   $("#unitFprice").val(data.unitFprice).trigger("blur");
                   $("#cpriceAmt").val(data.cpriceAmt).trigger("blur");
                   $("#intAmt").val(data.intAmt).trigger("blur");
                   $("#unitInt").val(data.unitInt).trigger("blur");
               }
               if ($("#zhilingtype").val() == "02") {
                   extracted();//平均分配方式
               } else if (!isCSTP) {
                   $("#fpriceAmt").val(JoyinMath.doAdd(Number($("#intAmt").val()) + Number($("#cpriceAmt").val()), 0, 2)).trigger("blur").trigger('change');
               }
               if (!isCSTP) {
                   $("#exerYield").val(data.exerYield).trigger("blur");
                   $("#yield").val(data.yield).trigger("blur");
                   // 记录隐藏值
                   $("#unitCprice_calc").val(data.unitCprice);
                   $("#unitFprice_calc").val(data.unitFprice);
                   $("#cpriceAmt_calc").val(data.cpriceAmt);
                   $("#fpriceAmt_calc").val(data.fpriceAmt);
                   $("#exerYield_calc").val(data.exerYield);
                   $("#yield_calc").val(data.yield);
                   $("#intAmt_calc").val(data.intAmt);
                   $("#unitInt_calc").val(data.unitInt);
               }
               saveCalcParam = getCalcParam();
           }
           //计算交易金额
           tableAllRowsJiSun = table.getAllRows();
       });
   }
   
   function extracted() {//平均分配
       if ($("#unitInt").val()) {
           //应计利息总额
           extractedIntAmt()
       } else {
           extractedIntAmt(0)
       }
       //计算净价总额
       if ($("#unitCprice").val()) {
        extractedCpriceAmt();
       }
       //计算结算费和交易手续费和佣金等数据
       if($('#tradeAmt').val()){
           extractedJiSuan(false,returnValAll);
       }
      if ($("#unitFprice").val()) {
          //获取i行的券面总额
          //全价总额
          var fpriceAmtSum = Number(0);
          for (var i = 0; i < table.getAllRows().length; i++) {
              if((table.getAllRows()[i].cpriceAmt || table.getAllRows()[i].cpriceAmt == 0)
                  &&(table.getAllRows()[i].intAmt || table.getAllRows()[i].intAmt == 0)){
                  var num = JoyinMath.doAdd(table.getAllRows()[i].cpriceAmt,table.getAllRows()[i].intAmt,2);
                  table.renderRow(i, {"fpriceAmt": num});
                  fpriceAmtSum = JoyinMath.doAdd(fpriceAmtSum, num, 2);
              }
          }
          if(table.getAllRows().length>0){
              $("#fpriceAmt").val(JoyinMath.doAdd(fpriceAmtSum, 0, 2)).trigger('change').trigger("blur");
          }else{
              $("#fpriceAmt").val(JoyinMath.doAdd($("#intAmt").val(), $("#cpriceAmt").val(), 2)).trigger("blur").trigger('change');
          }
      } else {
          table.renderRow(i, {"fpriceAmt": 0});
      }
}
   function extractedTime(){//时间优先
       var tradeAmt = $('#tradeAmt').val();
       tradeAmt = JoyinMath.doAdd(tradeAmt,0,2);
      if(tradeAmt){
          var all = 0;
          var one  = 0;
          for (var i = 0; i < table.getAllRows().length; i++) {
              var tradeAmt1 = table.getAllRows()[i].tradeAmt;
              var doEntrustMoney = table.getAllRows()[i].doEntrustMoney;
              tradeAmt1 = JoyinMath.doAdd(tradeAmt1,0,2);
              all = JoyinMath.doAdd(all,tradeAmt1,2);
              //如果第一列的值比劵面总额大的话
              if(i==0&&Number(tradeAmt1)>Number(tradeAmt)){
                  if(Number(JoyinMath.doAdd(tradeAmt, 0, 2))<=Number(doEntrustMoney)){
                      table.renderRow(0, {"tradeAmt": JoyinMath.doAdd(tradeAmt, 0, 2)});
                  }else{
                      table.renderRow(0, {"tradeAmt": doEntrustMoney});
                  }
                  for(var j = 1; j <table.getAllRows().length; j++){
                      table.renderRow(j, {"tradeAmt": JoyinMath.doDiv(0, 0, 2)});
                  }
                  break;
              }else{
                  if(Number(all)>Number(tradeAmt)){
                     var num =  JoyinMath.doMinus(tradeAmt,one,2);
                     if(Number(num)<=Number(doEntrustMoney)){
                         table.renderRow(i, {"tradeAmt": num});
                         one = JoyinMath.doAdd(one,num,2);
                     }else{
                         table.renderRow(i, {"tradeAmt": doEntrustMoney});
                         one = JoyinMath.doAdd(one,doEntrustMoney,2);
                     }
                  }else{
                      var num = JoyinMath.doAdd(tradeAmt1, 0, 2);
                      one = JoyinMath.doAdd(one,tradeAmt1,2);
                      if(Number(num)<=Number(doEntrustMoney)){
                          table.renderRow(i, {"tradeAmt": num});
                      }else{
                          table.renderRow(i, {"tradeAmt": doEntrustMoney});
                      }
                  }
              }
          }
      }
   }

   function extractedNumber() {//数量优先
       var tradeAmt = $('#tradeAmt').val();
       if(tradeAmt){
           var allRows = getAllRowsForEntrustId();
           //先按照劵面总额排序
           function sortprice(a,b){
               return b.tradeAmt-a.tradeAmt;
           }
           allRows =  allRows.sort(sortprice);
           table.clear();
           table.addRow(allRows);
           setMoney(allRows);
           extractedTime();
       }
   }

    function setMoney(rowDatas){
        $.each(rowDatas,function (index,element) {
            element.LAY_TABLE_INDEX = index;
            element.tradeAmt = tradeAmtAll;
            element.isEntrust = 'YES';
            element.secManageAcctIdDuo=element.secManageAcctId;
            element.ps =  element.ps;
//            extractedJiSunFeiYong(element);
        })
    }
    /**
     * 计算交割日
     */
    var calcSettleDate = function() {
        var settleSpeed = $("#settleSpeedText").val();
        if (settleSpeed == ACCOUNT_SPEED_T0) {
            $("#settleDate").val($("#tradeDate").val()).trigger("change");
        }
        else if(settleSpeed == ACCOUNT_SPEED_T1) {
            if ($('#finprodId').val()) {
                Joyin.ajax(basePath + 'invest/trade/bond/TrdBondInvestTrade003/getNextWorkDay', {
                    'calendarId': $("#calendarId").val(),
                    'tradeDate': $("#tradeDate").val()
                }, function (date) {
                    $("#settleDate").val(date).trigger("change");
                });
            }
        } else {
            $("#settleDate").val("").trigger("change");
        }
    };

    function getAllRowsForEntrustId(){
        tradeAmtAll = 0;
        var rowDatas = [];
        var entrustId = $("#entrustId").val();
        var bondFirstSign = $("#bondFirstSign").val();
        if(entrustId){
            Joyin.ajaxJson(basePath + 'invest/trade/bond/TrdBondInvestTrade003/getEntrustInfos', {'entrustId':entrustId,'bondFirstSign':bondFirstSign},function (data) {
                // 基本要素信息由投资委托带出，其中有：委托代码、组合代码、资产代码、清算速度、交易方向、交易场所、交易平台、交易市场、投资目的等，不可编辑
                // 金融要素信息由投资委托带出，其中有：币种、券面总额、净价、到期收益率、行权收益率等
                entrustData = data;
                function sortprice(a,b){
                    return a.etrEntrust.createTime-b.etrEntrust.createTime
                }
                data =  entrustData.sort(sortprice);
                if(data){
                    $.each(data,function (index,element) {
                        var rowData={};
                        var params ={};
                        // 计算交割日
                        calcSettleDate();
                        if(element.etrEntrust){
                            rowData.ps =  element.etrEntrust.ps;
                            rowData.entrustId = element.etrEntrust.entrustId;//委托代码
                            rowData.portfolioId = element.etrEntrust.portfolioId;//多组合组合代码
                            if(element.doEntrustMoney){
                                rowData.doEntrustMoney =JoyinMath.doMul(element.doEntrustMoney,100,2) ;
                            }
                            if( element.etrEntrust.entrustNum){
                                rowData.tradeAmt = element.etrEntrust.entrustNum;//多组合券面总额
                                tradeAmtAll = JoyinMath.doAdd(tradeAmtAll,element.etrEntrust.entrustNum,2);
                            }
                            if(element.etrEntrust.price){
                                rowData.unitCprice  = element.etrEntrust.price;//多组合净价
                            }
                            if(element.etrEntrust.invAim){
                                rowData.investaim = element.etrEntrust.invAim;//投资目的
                            }
                            if(element.etrEntrust.entrustAmt){
                                rowData.tradeAmtAndFee = element.etrEntrust.entrustAmt;//交易金额
                            }
                            if(element.etrEntrustAdd){
                                rowData.secManageAcctId=element.etrEntrustAdd.secAcctId;
                                if(element.etrEntrustAdd.windtypeone){
                                    checkAndAssign("#windtypeone", data[0].etrEntrustAdd.assetType1); // 债券一级分类
                                }
                                if(element.etrEntrustAdd.secAcctId){
                                    rowData.secManageAcctIdDuo = element.etrEntrustAdd.secAcctId;//证券托管户
                                    $('#secManageAcctId').val(element.etrEntrustAdd.secAcctId);
                                }
                            }
                        }
                        //组合T和T+1可以现金
                        if(element.etrEntrust.portfolioId&&$("#calendarId").val() && $("#ccy").val() && $("#tradeDate").val()){
                            if($('#chlId').val()){
                                params.chlId = $('#chlId').val();
                            }
                            params.portfolioId=element.etrEntrust.portfolioId;
                            params.calendarId=$("#calendarId").val();
                            params.tradeDate=$("#tradeDate").val();
                            params.acctTypeList = 'A01,A07';
                            params.ccy=$("#ccy").val();
                            Joyin.ajaxJson(JoyinUtil.getRealPath('/component/trade/BussTrdAvailableCashQuery/getAvailableCash'), params, function (cashdata) {
                                if(cashdata.T_0){
                                    rowData.bussTrdAvailableCashQueryavalibleCash =  cashdata.T_0;
                                }
                                if(cashdata.T_1){
                                    rowData.bussTrdAvailableCashQueryavalibleCash1 =  cashdata.T_1;
                                }
                                if(cashdata.T_2){
                                    rowData.channelAvailableCash =  cashdata.T_2;
                                }
                            },false);
                        }
                        //交易场所可用资金
                        if($('#tradeMarket').val()&&($('#tradeMarket').val()=='03'||$('#tradeMarket').val()=='04')
                            && element.etrEntrustAdd.secAcctId&&element.etrEntrust.portfolioId){
                            var param = {};
                            param.portfolioId=element.etrEntrust.portfolioId;
                            param.secManageAcctId=element.etrEntrustAdd.secAcctId;
                            param.ccy=$('#ccy').val();
                            param.calendarId = $('#calendarId').val();
                            Joyin.ajaxJson(JoyinUtil.getRealPath('/invest/trade/bond/TrdBondInvestTrade003/getBankCash'), param, function (result) {
                                if(result){
                                    rowData.accAvailableCash = Number(result);
                                }

                            },false);
                        }
                        //交易方向为卖出时显示资产可用持仓
                        if(element.etrEntrustAdd.secAcctId&&element.etrEntrust.portfolioId
                            && $("#calendarId").val() && $("#ccy").val() && $("#tradeDate").val()&&!($('#ps').val()=='T01')){

                            params.portfolioId=element.etrEntrust.portfolioId;
                            params.finprodId=$("#finprodId").val();
                            params.calendarId=$("#calendarId").val();
                            params.tradeDate=$("#tradeDate").val();
                            params.acctTypeList = 'A01,A07';
                            params.ccy=$("#ccy").val();
                            params.multiParam=100;
                            params.secAcctId=element.etrEntrustAdd.secAcctId;
                            Joyin.ajaxJson(JoyinUtil.getRealPath('/component/trade/BussTrdAvailableCashQuery/getAvailableCash'), params, function (cashdata) {
                                if( cashdata.position){
                                    rowData.assetsavailablepositions =  cashdata.position;
                                }
                            },false);
                        }
                        rowDatas.push(rowData)
                    });
                }
            },false);
        }
        return rowDatas;
    };
    function getCalcParam(type,data) {
        var paramObj = $.serializeToObject('#submitForm', true);
        if(type&&type=='02'){
            paramObj.portfolioId = data.portfolioId;
            paramObj.secManageAcctId = data.secManageAcctIdDuo;
        }
        paramObj.calculateField = $("[name=calculateField]:checked").val();
        paramObj.tradeAmt = $("#tradeAmt").val();
        paramObj.unitCprice = $("#unitCprice").val();
        paramObj.unitInt = $("#unitInt").val();
        paramObj.unitFprice = $("#unitFprice").val();
        paramObj.cpriceAmt = $("#cpriceAmt").val();
        paramObj.intAmt = $("#intAmt").val();
        paramObj.fpriceAmt = $("#fpriceAmt").val();
        paramObj.exerYield = $("#exerYield").val();
        paramObj.yield = $("#yield").val();
        if(data&&data.isEntrust&&data.isEntrust=='YES'){
            paramObj.calculateField = "unitCprice";
            paramObj.tradeAmt = data.tradeAmt;
            paramObj.ps = data.ps;
        }
        // 隐藏域如果有值，使用隐藏域
        if ($("#unitCprice_calc").val()) {
            paramObj.unitCprice = $("#unitCprice_calc").val();
        }
        if ($("#unitInt_calc").val()) {
            paramObj.unitInt = $("#unitInt_calc").val();
        }
        if ($("#unitFprice_calc").val()) {
            paramObj.unitFprice = $("#unitFprice_calc").val();
        }
        if ($("#cpriceAmt_calc").val()) {
            paramObj.cpriceAmt = $("#cpriceAmt_calc").val();
        }
        if ($("#intAmt_calc").val()) {
            paramObj.intAmt = $("#intAmt_calc").val();
        }
        if ($("#fpriceAmt_calc").val()) {
            paramObj.fpriceAmt = $("#fpriceAmt_calc").val();
        }
        if ($("#exerYield_calc").val()) {
            paramObj.exerYield = $("#exerYield_calc").val();
        }
        if ($("#yield_calc").val()) {
            paramObj.yield = $("#yield_calc").val();
        }

        // 计算之前，先将应计利息清空
        var tradeMarketMode = $("#tradeMarketMode").val();
        if (tradeMarketMode != SHARES_TRADE_MARKET_01) {
            paramObj.unitInt = null;
        }
        // add by wangguoxian 费用设置 20190808 start
        // 交易手续费，结算费，佣金，通道费，交易金额不再计入计算要素
        paramObj.procedureAmt = null;
        paramObj.settleAmt = null;
        paramObj.commissionAmt = null;
        paramObj.channelFee = null;
        paramObj.tradeAmtAndFee = null;
        paramObj.layTableCheckbox = null;
        // add by wangguoxian 费用设置 20190808 end
        return paramObj;
    }

    /**
     * 画面加载完成
     */
    $(function () {
//        if($('#entrustId').val()){
//            // 
//            pledgeBondTotalTbl.load();
//        }
         init();
//        var entrustId=param.data.entrustId;
//        if(entrustId==null||entrustId==""){
//            $("#bussTransfenpei_distribution").val('').prop("disabled", true);
////            $("#bussRepoSecPtl_main").find('div.layui-table-header').css('overflow','auto');
//        }
    });
    



}());