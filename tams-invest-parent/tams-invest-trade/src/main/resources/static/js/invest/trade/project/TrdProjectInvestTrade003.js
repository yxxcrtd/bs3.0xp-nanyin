(function () {
	var entrustData;
	var table = $('#bussTrdTransfenpeiTable').joyintable({
    	data:[],
        searchOnInit:true,
        height:'225px',
        cols:[
              {field:'entrustId',  width:100,title: 'TrdInvestFenPeiInfo.entrustId', sortable:false,type:'text'} //委托代码
              ,{field:'doEntrustMoney',  width:100,title: 'TrdInvestFenPeiInfo.remainEntrustAmt', sortable:false,edit:false,type:'number', digit:2} //剩余委托额度
              ,{field:'portfolioId', width:100,  title: 'TrdInvestFenPeiInfo.busiId',edit:$('#entrustId').val() ? false : true,
                  editOption:{type:'select', id:'portfolioId', multiple: false, showField:'text',searchable: true,
                      url: JoyinUtil.getRealPath('/business/common/CommonSelect/selectPortfolioId?act=true')
                  },sortable: false
              }//组合代码
             ,{field:'prinAmt',width:100,  title: 'TrdInvestFenPeiInfo.prinAmt', edit:true, sortable:false,type:'number', digit:2} //本金金额
             ,{field:'cpriceAmt',width:100,  title: 'TrdInvestFenPeiInfo.cpriceAmt',sortable:false,type:'number', digit:2} //净价总额
             ,{field:'intAmt',width:100, title: 'TrdInvestFenPeiInfo.intAmt',sortable:false,type:'number', digit:2} //应计利息总额
             ,{field:'channelFee',width:100, title: function(){return JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("TrdInvestFenPeiInfo.channelFee");}(),sortable:false,type:'number', digit:2, visible:false} //通道费
             ,{field:'tradeAmt',width:100, title: 'TrdInvestFenPeiInfo.tradeAmt2',sortable:false,type:'number', digit:2} //实付金额
             ,{field:'commissionAmt',width:100, title: 'TrdInvestFenPeiInfo.commissionAmt2',sortable:false,type:'number', digit:2} //手续费
             /*,{field:'channelFee', width:100, title: '通道费',edit:false,sortable: false,visible: true,type:'number', digit:2}*/
             ,{field:'bussTrdAvailableCashQueryavalibleCash', width:100,title: 'TrdInvestFenPeiInfo.bussTrdAvailableCashQueryavalibleCash',sortable:false,type:'number', digit:2} //组合T日可用现金
             ,{field:'bussTrdAvailableCashQueryavalibleCash1',width:100, title: 'TrdInvestFenPeiInfo.bussTrdAvailableCashQueryavalibleCash1',sortable:false,type:'number', digit:2} //组合T+1日可用现金
             ,{field:'channelAvailableCash', width:100, title: function(){return JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("TrdInvestFenPeiInfo.channelAvailableCash");}(), sortable: false,type:'number', digit:2} //通道可用现金
             ,{field:'assetsavailablepositions',width:100, title: 'TrdInvestFenPeiInfo.assetsavailablepositions',sortable:false,type:'number', digit:2} //资产可用持仓
             ,{field:'operation', width:100,title: 'SysCommon.operation',sortable:false, type:'action', buttons: ['delt']}
           ],
           addbtn:true,
           done:function(){
        	   this.bind('click', '.delt', function(data, index) {
                   table.deleteRow(index);
               });
           },
           beforeEditCell:function(value, field, data){
        	   return true;
           },
           editCell: function(value, data, field, cell, column){
        	   var params={};
			   //买入
        	   if('portfolioId'==field && 'T01'==$('#ps').val()){
                   var rowData = {};
        		   table.renderRow(data.LAY_TABLE_INDEX, {'assetsavailablepositions':' '})
        		   if(value && value.trim() && $('#calendarId').val() && $('#ccy').val() && $('#tradeDate').val()){
            	        if($('#chlId').val()){
                        params.chlId = $('#chlId').val();
                        }
        			   params.portfolioId=value;
        			   params.calendarId=$('#calendarId').val();
        			   params.tradeDate=$('#tradeDate').val();
        			   params.acctTypeList = ACCT_TYPE_A01 + ',' + ACCT_TYPE_A07;
        			   params.ccy=$('#ccy').val();
        			   Joyin.ajaxJson(JoyinUtil.getRealPath('/component/trade/BussTrdAvailableCashQuery/getAvailableCash'), params, function (cashdata) {
        				   table.renderRow(data.LAY_TABLE_INDEX, {'bussTrdAvailableCashQueryavalibleCash': cashdata.T_0});
        				   table.renderRow(data.LAY_TABLE_INDEX, {'bussTrdAvailableCashQueryavalibleCash1': cashdata.T_1});
                           table.renderRow(data.LAY_TABLE_INDEX, {'channelAvailableCash': cashdata.T_2});
        			   });
        		   }else{
        			   table.renderRow(data.LAY_TABLE_INDEX, {'bussTrdAvailableCashQueryavalibleCash': ' '});
            		   table.renderRow(data.LAY_TABLE_INDEX, {'bussTrdAvailableCashQueryavalibleCash1': ' '});
        		   }
        	   }else if('portfolioId'==field && 'T02'==$('#ps').val()){
        		   table.renderRow(data.LAY_TABLE_INDEX, {'bussTrdAvailableCashQueryavalibleCash': ' '});
        		   table.renderRow(data.LAY_TABLE_INDEX, {'bussTrdAvailableCashQueryavalibleCash1': ' '});
        		   if(value && value.trim() && $('#calendarId').val() && $('#ccy').val() && $('#tradeDate').val()){
        			   //交易方向为卖出时显示资产可用持仓
        			   params.portfolioId=value;
        			   params.finprodId=$('#finprodId').val();
        			   params.calendarId=$('#calendarId').val();
        			   params.tradeDate=$('#tradeDate').val();
        			   params.chlId=$('#chlId').val();
        			   params.acctTypeList = ACCT_TYPE_A01 + ',' + ACCT_TYPE_A07;
        			   params.ccy=$('#ccy').val();
        			   Joyin.ajaxJson(JoyinUtil.getRealPath('/component/trade/BussTrdAvailableCashQuery/getAvailableCash'), params, function (cashdata) {
        				   table.renderRow(data.LAY_TABLE_INDEX, {'assetsavailablepositions': cashdata.position});
        			   });
        		   }else{
        			   table.renderRow(data.LAY_TABLE_INDEX, {'assetsavailablepositions':' '}) 
        		   }
        	   }
               
               if('prinAmt'==field && value){
            	   if(value&&$('#prinAmt').val()){
            		   var obj = {flag:false,row:data.LAY_TABLE_INDEX,value:value}
            		   cpriceAmtDistribution();
               		   intAmtDistribution(obj);
            		   channelFeeDistribution();
            		   commissionAmtDistribution();
            		   tradeAmtAndFeeDistribution();
                   }
               }
           }
		});
		
        var entrustPrinAmt;

        var initFlag = true;
        var getriskdata = function () {
            var mun=null;
            var object = $.serializeToObject('#informationForm', true);
            var bussTrdTransfenpeiDto=null;
            if(object!=null&&object!=''&&object.bussTrdTransfenpeiDto!=null&&object.bussTrdTransfenpeiDto!=''){
                bussTrdTransfenpeiDto=object.bussTrdTransfenpeiDto;
            }
            var list=[];
            for(var i in bussTrdTransfenpeiDto){
                var obj = {
                        timePoint:MY_SUP_TIME_TYPE_02,
                        valDate:$('#settleDate').val(),
                        portfolioCode:bussTrdTransfenpeiDto[i].portfolioId,
                        tradeAmount:bussTrdTransfenpeiDto[i].tradeAmt,
                        assetType:FINPROD_TYPE_F18,
                        isEnabled:'01',
                        assetCode:$('#finprodId').val(),
                        tradeDirection:$('#ps').val(),
                        index: null,
                        mun: mun}
                list[i]=obj;
            }
            if($('#projectType').val() && $('#projectType').val() != ''){
                for(var i in list){
                    list[i].assetSecType = $('#projectType').val();
                }
            }
            var object = {
            		assetCode: ['finprodMarketId', 'TrdBondInvestTrade002.finprodId'],
                    valDate: ['settleDate', 'TrdPlanRateInvestTrade002.settleDate'],
                    portfolioCode: ['portfolioId', 'TrdBondInvestTrade002.portfolioId'],
                    tradeAmount: ['tradeAmt', 'TrdProjectInvestTrade002.fpriceAmt'],
                    assetType: ['projectType', 'TrdDepositInvestTrade001.finprodType'],
                    tradeDirection:['ps','TrdLendInvestTrade002.ps'],
            		data:list
            }
            return object;
        }
        
        function getAllRowsForEntrustId(){
            var rowDatas = [];
            var entrustId = $('#entrustId').val();
            if(entrustId){
            	$('#TrdProjectInvestTrade003_distribution').attr('disabled',false);
                ajaxJsonSync(basePath + 'invest/trade/project/TrdProjectInvestTrade003/getEntrustInfos', {'entrustId':entrustId},function (data) {
                    entrustData = data;
                    function sortprice(a,b){
                        return a.etrEntrust.createTime-b.etrEntrust.createTime
                    }
                    data =  entrustData.sort(sortprice);
                    if(data){
                        $.each(data,function (index,element) {
                            var rowData={};
                            var params ={};
                            if(element.etrEntrust){
                                rowData.ps =  element.etrEntrust.ps;
                                rowData.entrustId = element.etrEntrust.entrustId;//委托代码
                                rowData.portfolioId = element.etrEntrust.portfolioId;//多组合组合代码
                                if(element.doEntrustMoney){
                                    rowData.doEntrustMoney =element.doEntrustMoney ;
                                }
//                                if( element.etrEntrust.entrustAmt){
//                                    rowData.prinAmt = element.etrEntrust.entrustAmt;//多组合本金金额
//                                }
                            }
                            //组合T和T+1可以现金
                            if(element.etrEntrust.portfolioId&&$('#calendarId').val() && $('#ccy').val() && $('#tradeDate').val()){
                                if($('#chlId').val()){
                                    params.chlId = $('#chlId').val();
                                }
                                params.portfolioId=element.etrEntrust.portfolioId;
                                params.calendarId=$('#calendarId').val();
                                params.tradeDate=$('#tradeDate').val();
                                params.acctTypeList = 'A01,A07';
                                params.ccy=$('#ccy').val();
                                ajaxJsonSync(JoyinUtil.getRealPath('/component/trade/BussTrdAvailableCashQuery/getAvailableCash'), params, function (cashdata) {
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
                            //交易方向为卖出时显示资产可用持仓
                            if(element.etrEntrustAdd.secAcctId&&element.etrEntrust.portfolioId
                                && $('#calendarId').val() && $('#ccy').val() && $('#tradeDate').val()&&!($('#ps').val()=='T01')){

                                params.portfolioId=element.etrEntrust.portfolioId;
                                params.finprodId=$('#finprodId').val();
                                params.calendarId=$('#calendarId').val();
                                params.tradeDate=$('#tradeDate').val();
                                params.acctTypeList = 'A01,A07';
                                params.ccy=$('#ccy').val();
                                params.multiParam=100;
                                params.secAcctId=element.etrEntrustAdd.secAcctId;
                                ajaxJsonSync(JoyinUtil.getRealPath('/component/trade/BussTrdAvailableCashQuery/getAvailableCash'), params, function (cashdata) {
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
        }
        /**
         * 画面初始化
         */
        function init() {
            if ($('#entrustId').val()) {
                entrustPrinAmt = $('#prinAmt').val();
                $('#portfolioId').prop('disabled', true);
                $('#finprodMarketId').prop('disabled', true).trigger('change');
                setInvestRate($('#finprodId').val());
                $('#ps').prop('disabled', true).trigger('change');
                $('#settleSpeed').prop('disabled', true).trigger('change');
                $('#commissionRate').prop('disabled', false).trigger('change');
                //代志伟  2019-07-18 手续费根据手续费率带出，不可编辑
                $('#unitCprice').trigger('blur');
                $('#cpriceAmt').val(entrustPrinAmt).trigger('blur');
            	table.showColumns([{field:'operation',visible: false}]);
            }
            if (ps == T40) {
                $('#ps').prop('disabled', true).trigger('change');
                $('#ps').next().find('li:lt(2)').remove();
            }
            else {
                $('#ps').next().find('li:eq(2)').remove();
            }
            setInvestRate($('#finprodId').val());
            if($('#chlId')){
            	var finprodId = null;
                if($('#finprodMarketId').val()){
                	finprodId= $('#finprodMarketId').find('option:selected').data('data').object.finprodId
                }
                ChannelAssetCommon.setChannelSelectObject($('#chlId'), finprodId, $('#entrustId').val(), function () {

                });
            }
            initFlag =false;
            table.addRow(getAllRowsForEntrustId());
        }

        /**investRate
         * 利息总额计算
         */
        var intAmtCalcu = function () {
            if (pageModel == PAGE_MODEL_REFERENCE) {
                return;
            }
            //$('#intAmt').val('');
            $('#unitIntAmt').val('');
            $('#unitFprice').val('');
            var object = {
//                portfolioId: $('#portfolioId').val(),
                finprodId: $('#finprodId').val(),
                settleDate: $('#settleDate').val(),
                basis: $('#basis').val(),
                investRate: $('#investRate').val().valueOf() / 100,
                prinAmt: $('#prinAmt').val()
            }
            if (object.finprodId && object.investRate && object.settleDate && object.prinAmt) {
                Joyin.ajaxJson(basePath + 'invest/trade/trdplanrateinvesttrade/TrdPlanRateInvestTrade002/getIntAmt', object, function (result) {
                    //update by liuwenjuan 应计利息总额多组合分配 start
                    //update by xr 应计利息总额多组合分配 start
                    $('#intAmt').val(result.data).trigger('blur');
                    //加上精度计算，  yjm 20190725
                    var unitIntAmt = JoyinMath.doDiv(JoyinMath.doMul($('#intAmt').val()==""?result.data*1:$('#intAmt').val()*1, 100, 6), object.prinAmt*1, 4);
                    $('#unitIntAmt').val(unitIntAmt).trigger('blur');
                    $('#unitFprice').val(JoyinMath.doAdd(unitIntAmt, $('#unitCprice').val(), 6)).trigger('blur');
                    //卖出时减去手续费 yjm start 20190903
                    var commissionAmt = $('#commissionAmt').val();
                    var ps = $('#ps').val();
                    if (ps && ps != T01) {
                        //卖出
                        commissionAmt = commissionAmt * -1;
                    }
                    //卖出时减去手续费 yjm start 20190903
                    $('#tradeAmt').val(JoyinMath.doAdd(JoyinMath.doAdd($('#intAmt').val()==""?result.data*1:$('#intAmt').val()*1, object.prinAmt*1), commissionAmt, 6)).trigger('blur');
                    var obj = {flag:true,intAmtAll:$('#intAmt').val()==""?result.data:$('#intAmt').val()}
                    //update by liuwenjuan 应计利息总额多组合分配 end
    	    	    intAmtDistribution(obj);
                    tradeAmtAndFeeDistribution(); 
                });
            }
        }

        function projectAssetView() {
            var finprodId = $('#finprodId').val();
            Joyin.dialog(JoyinUtil.getLabel('FinProjectTargetAsset002.title'), basePath + 'invest/asset/project/FinProjectTargetAsset002/init',
                '98%', '98%', {
                    pageModel: PAGE_MODEL_REFERENCE,
                    finprodId: finprodId
                });
        }

        function prinAmtChange() {
            var prinAmt = $('#prinAmt').val();
            $('#unitCprice').val(100);
            if (prinAmt) {
                $('#cpriceAmt').val(prinAmt).trigger('blur');
                cpriceAmtDistribution(); 
                intAmtCalcu();
                getCommissionAmt();
            }
        }

        /**
         * 清算速度变更
         */
        function settleSpeedChange() {
            var settleSpeed = $('#settleSpeed').val();
            var tradeDate = $('#tradeDate').val();
            var settleSpeedValue=0;
            if (settleSpeed == SETTLE_SPEED01) {
                $('#settleDate').val(tradeDate);
                //若清算速度由T+1改为T+0的时候也需要进行应计利息的计算 start yjm 20190724
                intAmtCalcu();
                //若清算速度由T+1改为T+0的时候也需要进行应计利息的计算 start end 20190724
            } else {
            	if (settleSpeed == SETTLE_SPEED02) {
                	settleSpeedValue=1;
                }
                else if (settleSpeed == SETTLE_SPEED03) {
                	settleSpeedValue=2;
                }
                var data = $('#finprodMarketId').find('option:selected').data('data');
                var calendarId = data.object ? data.object.calendarId || '' : ''
                if (calendarId && tradeDate) {
                    Joyin.ajax(basePath + 'invest/trade/bond/TrdBondInvestTrade002/getNextWorkDay', {
                        tradeDate: tradeDate,
                        calendarId: calendarId,
                        settleSpeed: settleSpeedValue
                    }, function (data) {
                        $('#settleDate').val(data);
                        intAmtCalcu();
                    })
                }
            }
        }

        window.componentLoadCallback = function (isDone, pagePath, params) {
            if (isDone) {
                var riskparam = getriskdata();
                if (pageModel==PAGE_MODEL_NEW){
                    riskparam.data =null;
                }
                window.BaseRiskMathComm.getparam(riskparam);
//                ChannelAssetCommon.setChannelSelect($('#chlId'), finprodId, $('#entrustId').val(), function () {
                    psChange(true);
                    init();
                    window.BussTrdTransferOrder["bussTrdTransferOrder"].setStlCommandInput(function () {
                    	if (!$('#finprodId').val()) {
                    		Joyin.error('请选择资产')
                    		return;
                    	}
                    	if (!$('#tradeDate').val()) {
                    		Joyin.error('请输入资金到账日')
                    		return;
                    	}
                    	if (!$('#ccy').val()) {
                    		Joyin.error('请选择币种')
                    		return;
                    	}
                    	if (!$('#prinAmt').val()) {
                            Joyin.error('请输入本金金额')
                            return;
                        }
                        if (!$('#tradeAmt').val()) {
                            Joyin.error('请输入实付金额')
                            return;
                        }
                        //校验组合分配列表
                        if(checkFeiPeiAmt()){
                        	return;
                        }
                        var list = new Array();
    	            	var tmpList = table.getAllRows();
    	            	if(tmpList.length>0){
    	            		for(var dealObj in tmpList)
    	            		{	
    	            			var dealinfo = {};
    	            			dealinfo.finprodType2 = FINPROD_TYPE_F18;
    	            			dealinfo.settleDate = $('#settleDate').val();
    	            			dealinfo.tradeDate = $('#tradeDate').val();
    	            			dealinfo.ps = $('#ps').val();
    	            			dealinfo.ccy = $('#ccy').val();
    	            			dealinfo.tradeAmt = tmpList[dealObj].tradeAmt; 
    	            			dealinfo.portfolioId = tmpList[dealObj].portfolioId;
    	            			dealinfo.finprodId = $('#finprodId').val(),
    	            			dealinfo.counterId = $('#trdInvestPersonInfo_counterId').val();
    	            			dealinfo.chlId = $('#chlId').val();
    	            			list.push(dealinfo);
    	            		}
    	            	}
                        return {
                            'isTotalTrade':YES_OR_NO_N
                            ,'bankLendingType':null
                            ,'portfolioId':'dzh'
                            ,'jiaoyiType':'dzh'
                            ,'deal':list
                        };
                    },true);
//                });
                  //交易对手改变时  影响通道费金额，内部交易对手不可以填，外部交易对手 不随交易
                    $('#trdInvestPersonInfo_counterId').on('change',function(){
                    	checkChannelFee();
                    	commissionAmtChange();
                    });
            }
        }
        
        /**
	     * 标的挂通道 内转交易 通道费置灰
	     * 外部卖出显示可填  其他置灰
         */
        var checkChannelFee = function(){
            var ps = $('#ps').val();
            if(ps==T02&&!$('#trdInvestPersonInfo_counterId').val()){
            	$("#channelFee").attr('disabled',false);
            }
            else if(ps==T02&&$('#trdInvestPersonInfo_counterId').val()&&$("#trdInvestPersonInfo_counterId").select2('data')[0].object!='N'){
            	$("#channelFee").attr('disabled',false);
            }
            else{
            	$("#channelFee").val("").attr('disabled',true);
            }
            channelFeeDistribution();
        }
        
        function tradeAmtChange() {
            /*买入 全价总额 = 实付金额 - 费用 ； 卖出 全价总额 = 实付金额 + 费用*/
            var tradeAmt = $('#tradeAmt').val();
            var ps = $('#ps').val();
            var channelFee = $('#channelFee').val();
            //手续费
            var commissionAmt = $('#commissionAmt').val()||0;
           if (ps == T01) {
//                channelFee = channelFee * -1;
               commissionAmt = commissionAmt * -1;
           }
            //卖出时存在通道费 其他情况无通道费
	    //update by lwj 20200326 start
        //     if (ps == T02) {
        //     	channelFee = channelFee * -1;
        //         //commissionAmt = commissionAmt * -1;
        //     }
	    //update by lwj 20200326 end
            //update by panbin 2020-3-27 start
            var data = $('#chlId').find('option:selected').data('collecttype');
            if(ps==T01){
                //买入时不计算通道费
                channelFee=0;
            }
            else if (ps==T02 && data!=collectType_01){
                //卖出时 无通道或通道的费用收取方式不是回款前扣除
                channelFee=0;
            }
            else if (ps==T02 && data && data==collectType_01) {
                channelFee = channelFee * -1;
            }
            //update by panbin 2020-3-27 end
            tradeAmt =JoyinMath.doAdd(JoyinMath.doAdd(tradeAmt, channelFee), commissionAmt);
            
            //update by 张年  20190827 end
            var intAmt = $('#intAmt').val();
            var prinAmt = $('#prinAmt').val();
            if (tradeAmt && intAmt) {
                var cpriceAmt = JoyinMath.doMinus(tradeAmt, intAmt);
                $('#cpriceAmt').val(cpriceAmt).trigger('blur');
                if (prinAmt) {
                    var oldUnitCprice = $('#unitCprice').val();
                    var unitCprice = JoyinMath.doMul(JoyinMath.doDiv(cpriceAmt, prinAmt, 8), 100, 6);
                    $('#unitCprice').val(unitCprice).trigger('blur');
                    var unitIntAmt = $('#unitIntAmt').val();
                    if (unitIntAmt) {
                        $('#unitFprice').val(JoyinMath.doAdd($('#unitIntAmt').val(), $('#unitCprice').val(), 6)).trigger('blur');
                    }
                }
                cpriceAmtDistribution();
                tradeAmtAndFeeDistribution(); 
            } 
        }

        var commissionAmtChange = function () {
            if (pageModel == PAGE_MODEL_REFERENCE) {
                return;
            }
            var ps = $('#ps').val();
            var commissionAmt = $('#commissionAmt').val();
            if (ps == T02) {
                commissionAmt = commissionAmt *-1;
            }
            var channelFee = $('#channelFee').val();
//            var channelFee = 0;
            var prinAmt = $('#prinAmt').val();
            var intAmt = $('#intAmt').val();
            var tradeAmt = JoyinMath.doAdd(intAmt, prinAmt);            
            if(ps == T40&&initFlag){
            	return;
            }
            var data = $('#chlId').find('option:selected').data('collecttype');
            if(ps==T01){
            	//买入时不计算通道费
            	channelFee=0;
            }else if(ps==T02 && data && data.object && data.object.collectType==collectType_01){
            }else if (($('#trdInvestPersonInfo_counterId').val()&&$('#trdInvestPersonInfo_counterId').select2('data')[0].object=='N')
           		 ||($('#trdInvestPersonInfo_counterId').val()&&$('#trdInvestPersonInfo_counterId').select2('data')[0].object!='N'
           			&& ps==T02 && data && data==collectType_01)){
            	//卖出时 且通道存在 通道收取方式为回款前扣除 
            	channelFee = $('#channelFee').val();
            }else if(ps==T02 && data && data==collectType_01 && !$('#trdInvestPersonInfo_counterId').val()){
            	//卖出时 且通道存在 通道收取方式为回款前扣除
            	channelFee = $('#channelFee').val();
            }
            //if (ps == T01) {
            $('#tradeAmt').val(JoyinMath.doAdd(JoyinMath.doAdd(tradeAmt, commissionAmt), channelFee)).trigger('blur').trigger('change');
            //}
            //else {
                //$('#tradeAmt').val(JoyinMath.doMinus(JoyinMath.doMinus(tradeAmt, commissionAmt), channelFee)).trigger('blur').trigger('change');
            //}
            commissionAmtDistribution();
            tradeAmtAndFeeDistribution(); 
        }

        function psChange(isInit) {
            if (!isInit) {
                table.clear();
            }
            var ps = $('#ps').val();
            if (ps == T01) {//买入
            	$('#channelFee_div').hide();
            	$('#channelFee').val('');
//            	table.showColumns([{field:'channelAvailableCash',visible: true}]);
//            	table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash',visible: true}]);
//                table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash1',visible: true}]);
//                table.showColumns([{field:'assetsavailablepositions',visible: false}]);
            	 //modify by hxy 20200325 start
            	if('T01'==$('#ps').val() && $('#finprodMarketId').val()){
            		table.showColumns([{field:'channelFee',visible: false}]);
            	}
            	 //modify by hxy 20200325 end

            } else if(ps == T02){//卖出
//            	if($('#chlId').val()){
//            		$('#channelFee_div').show();
//            		table.showColumns([{field:'channelFee',visible: true}]);
//            	}else{
//            		$('#channelFee').val('');
            		//$('#channelFee_div').show();
//            	}
//            	table.showColumns([{field:'channelAvailableCash',visible: false}]);
//                table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash',visible: false}]);
//                table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash1',visible: false}]);
//                table.showColumns([{field:'assetsavailablepositions',visible: true}]);
            	   channelFeeIsShow(); 
            }
            commissionAmtChange();
        }
        
        function finprodMarketIdChange() {
            var optionData = $('#finprodMarketId').find('option:selected').data('data');
            if (optionData && optionData.object) {
                finprodId = optionData.object ? optionData.object.finprodId || '' : ''
            	$('#finprodId').val(finprodId);
                $('#finprodName').val(optionData.object.finprodAbbr);
                $('#ccy').val(optionData.object.ccy);
                $('#finmdate').val(new Date(optionData.object.mdate));
                $('#calendarId').val(optionData.object.calendarId);
                settleSpeedChange();
                var finprodType = optionData.object ? optionData.object.finprodType || '' : ''
                if (finprodType) {
                    $('#projectType').val(finprodType).trigger('change');
                }
                // 项目类资产五级分类改造 add by renping 2019-08-01 start
                var type9 = optionData.object ? optionData.object.type9 || '' : ''
                	if (type9) {
                		$('#type9').val(type9).trigger('change');
                	}
                var type10 = optionData.object ? optionData.object.type10 || '' : ''
                	if (type10) {
                		$('#type10').val(type10).trigger('change');
                	}
                var type11 = optionData.object ? optionData.object.type11 || '' : ''
                	if (type11) {
                		$('#type11').val(type11).trigger('change');
                	}
                // 项目类资产五级分类改造 add by renping 2019-08-01 end
                setInvestRate(finprodId, true);
                ChannelAssetCommon.setChannelSelectObject($('#chlId'), finprodId, $('#entrustId').val(), function () {

                });
            }
            channelFeeIsShow();
        }
        //modify by hxy 20200325 start
        //判断列表中通道和通道费是否显示
	    function channelFeeIsShow(){
        	if($('#finprodMarketId').val()){
        		if($('#chlId').val() && 'T02'==$('#ps').val()){
                    var data = $('#chlId').find('option:selected').data('collecttype');
                    if(data && data==collectType_01){
	                      $('#channelFee_div').show();
	                  	  table.showColumns([{field:'channelFee',visible: true}]);
                   }else{
	                  	 $('#channelFee_div').hide();
	                   	 $('#channelFee').val('');
	                   	table.showColumns([{field:'channelFee',visible: false}]);
                   }
              }else{
              	     $('#channelFee_div').hide();
              	     $('#channelFee').val('');
                 	table.showColumns([{field:'channelFee',visible: false}]);
              }	
           }
        }
        /**
         * 应计利息总额change事件
         */
        function intAmtChange(){
        	//update by liuwenjuan 应计利息总额多组合分配 start
        	intAmtCalcu();
        	//update by liuwenjuan 应计利息总额多组合分配 end
        	commissionAmtChange();
            $('#unitFprice').val(JoyinMath.doAdd($('#intAmt').val(), $('#unitCprice').val(), 6)).trigger('blur');
        }

        /**
         * 获取投资收益率
         * @param finprodId 金融产品代码
         * @param calcInt 是否计算利息
         */
        function setInvestRate(finprodId, calcInt) {
            if (finprodId) {
                Joyin.ajax(basePath + 'invest/trade/project/TrdProjectInvestTrade002/findInvestRate', {
                    finprodId: finprodId, settleDate: $('#settleDate').val()
                }, function (data) {
//                    $('#investRate').val(data.INVEST_RATE).trigger('blur');
                    //加上精度计算，否则会和资产端值不一样 yjm 20190725
                    $('#investRate').val(JoyinMath.doMul(data.INVEST_RATE, 100, 6)).trigger('blur');
                    $('#basis').val(data.BASIS);
                    if (calcInt) {
                        intAmtCalcu();
                    }
                })
            }
        }
        
        function checkFeiPeiAmt(){
        	//检查组合分配的和金额信息是否一致
        	if(!table.getAllRows().length>0){
        		 Joyin.error('请输入组合分配列表！');
                 return true;
        	}else{
        		for(var i=0;i<table.getAllRows().length;i++){
        			if(!table.getAllRows()[i].portfolioId){
        				Joyin.error('组合分配列表第'+(i+1)+'行组合代码不能为空！');
                        return true;
        			}
        			if(!table.getAllRows()[i].prinAmt){
        				Joyin.error('组合分配列表第'+(i+1)+'行本金金额不能为空！');
                        return true;
        			}
        		}
        	}
        	if($('#entrustId').val()){
        		if($('#prinAmt').val()){
        			var number = 0;
        			var prinAmt = 0;
        			var doEntrustMoney = 0;
        			for(var i=0;i<table.getAllRows().length;i++){
        				prinAmt = table.getAllRows()[i].prinAmt.replace(',', '');
        				doEntrustMoney = table.getAllRows()[i].doEntrustMoney.replace(',', '');
        				if(prinAmt){
        					number =  JoyinMath.doAdd(number,prinAmt,2);
        				}
        				if(Number(prinAmt)>Number(doEntrustMoney)){
        					Joyin.error('组合分配本金金额不能大于剩余可用额度!');
        					return true;
        				}
        			}
        			if(JoyinMath.doAdd($('#prinAmt').val(),0,2)!=number){
        				Joyin.error('组合分配本金金额和金额信息本金金额不一致！');
        				return true;
        			}
        		}
        	}
            
            if($('#cpriceAmt').val()){
                var number = 0;
                var cpriceAmt = 0;
                for(var i=0;i<table.getAllRows().length;i++){
                	cpriceAmt = table.getAllRows()[i].cpriceAmt.replace(',', '');
                    if(cpriceAmt){
                        number =  JoyinMath.doAdd(number,cpriceAmt,2);
                    }
                }
                if(JoyinMath.doAdd($('#cpriceAmt').val(),0,2)!=number){
                    Joyin.error('组合分配净价总额和金额信息净价总额不一致！');
                    return true;
                }
            }
            
            if($('#intAmt').val()){
                var number = 0;
                var intAmt = 0;
                for(var i=0;i<table.getAllRows().length;i++){
                	intAmt = table.getAllRows()[i].intAmt.replace(',', '');
                    if(intAmt){
                        number =  JoyinMath.doAdd(number,intAmt,2);
                    }
                }
                if(JoyinMath.doAdd($('#intAmt').val(),0,2)!=number){
                    Joyin.error('组合分配应计利息总额和金额信息应计利息总额不一致！');
                    return true;
                }
            }
            
            if($('#channelFee').val()){
                var number = 0;
                var channelFee = 0;
                for(var i=0;i<table.getAllRows().length;i++){
                	channelFee = table.getAllRows()[i].channelFee.replace(',', '');
                    if(channelFee){
                        number =  JoyinMath.doAdd(number,channelFee,2);
                    }
                }
                if(JoyinMath.doAdd($('#channelFee').val(),0,2)!=number){
                    Joyin.error('组合分配通道费和金额信息通道费不一致！');
                    return true;
                }
            }
            
            if($('#tradeAmt').val()){
            	var number = 0;
            	var tradeAmt = 0;
            	for(var i=0;i<table.getAllRows().length;i++){
            		tradeAmt = table.getAllRows()[i].tradeAmt.replace(',', '');
            		if(tradeAmt){
            			number =  JoyinMath.doAdd(number,tradeAmt,2);
            		}
            	}
            	if(JoyinMath.doAdd($('#tradeAmt').val(),0,2)!=number){
            		Joyin.error('组合分配实付金额和金额信息实付金额不一致！');
            		return true;
            	}
            }
            
            if($('#commissionAmt').val()){
                var number = 0;
                var commissionAmt = 0;
                for(var i=0;i<table.getAllRows().length;i++){
                	commissionAmt = table.getAllRows()[i].commissionAmt.replace(',', '');
                    if(commissionAmt){
                        number =  JoyinMath.doAdd(number,commissionAmt,2);
                    }
                }
                if(JoyinMath.doAdd($('#commissionAmt').val(),0,2)!=number){
                    Joyin.error('组合分配手续费和金额信息手续费不一致！');
                    return true;
                }
            }
            
        }

        function saveOrSubmit(operateType) {
        	// 划款指令隐藏时，自动生成划款指令信息
        	generateOrders(function () {
	            var s;
	            
	            //校验组合分配列表
	            if(checkFeiPeiAmt()){
	            	return;
	            }
	            
	            if(BaseRiskMathComm) {
	                s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
	                if(!s && s != ''){
	                    return;
	                }
	                if (window.BaseRiskMathComm.isCalcConditionChanged(getriskdata())){
	                    return;
	                }
	            }
	            // 检查生成划款指令参数是否发生变化
	            if (window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()) {
	                return;
	            }
	            
	            
	            /**
	             * 获取列表参数
	             */
	            var object = $.serializeToObject('#informationForm', true);
	            object.pageModel = pageModel;
	            object.operateType = operateType;
	            if (FileCommon) {
	                object.uploadFileList = FileCommon.getUploadFileInfo();
	            }
	            var portfolioId;
				for(var bussTrdTransfenpeiOrder in object.bussTrdTransfenpeiDto){
					portfolioId = object.bussTrdTransferOrder[bussTrdTransfenpeiOrder].portfolioId + ',' + portfolioId;
	//              object.entrustId =object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].entrustId;
	//				object.portfolioId = object.bussTrdTransferOrder[bussTrdTransfenpeiOrder].portfolioId;
	//				object.prinAmt = object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].prinAmt;
	//        		object.cpriceAmt = object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].cpriceAmt;
	//        		object.intAmt = object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].intAmt;
	//        		object.channelFee = object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].channelFee;
	//        		object.commissionAmt = object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].commissionAmt;
	//        		object.tradeAmt = object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].tradeAmt;
				}
				object.portfolioId = portfolioId;
	    		// 设置交易日历
	            var finprodMarketIdData = $('#finprodMarketId').find('option:selected').data('data');
	            var calendarId = finprodMarketIdData.object ? finprodMarketIdData.object.calendarId || '' : '';
	            var finprodType = finprodMarketIdData.object ? finprodMarketIdData.object.finprodType || '' : '';
	            object.calendarId = calendarId;
	            object.projectType = finprodType;
	            //退回的流程重新提交的时候不用再进行金额校验 yjm  start 20190725
	            if (workflowId){
	                object.workflowId = workflowId;
	            }
	            //退回的流程重新提交的时候不用再进行金额校验 yjm end  20190725
	            if ($('#trdInvestPersonInfo_counterId').select2('data') && $('#trdInvestPersonInfo_counterId').select2('data')[0]) {
	                object.counterType = $('#trdInvestPersonInfo_counterId').select2('data')[0].object;;
	            }
	            var data = $('#chlId').find('option:selected').data('collecttype');
	            if (data) {
	                object.collectType = data;
	            }
	            data = $('#chlId').find('option:selected').data('paytype');
	            if (data) {
	                object.payType = data;
	            }
	            
	            if($('#tradeAmt').val()){
					var maxTradeamt = 0;
					for(var i=0;i<table.getAllRows().length;i++){
						tradeAmt = table.getAllRows()[i].tradeAmt.replace(',', '');
						if(tradeAmt){
							if (parseFloat(tradeAmt) > parseFloat(maxTradeamt)){
								maxTradeamt = tradeAmt;
							}
						}
					}
					object.maxTradeamt = maxTradeamt;
				}
	            object.finprodType2 = 'F18';
	            object.counterId = $('#trdInvestPersonInfo_counterId').val();
	            Joyin.ajaxJson(basePath + 'invest/trade/project/TrdProjectInvestTrade003/check', object, function (result) {
	            	Joyin.ajaxJson(basePath + 'invest/trade/TrdCommonController/check', object, function(result1) {
		                Joyin.confirm(result.data, function () {
		                    var elementId;
		                    if (operateType === 'save') {
		                        elementId = 'TrdProjectInvestTrade002_btn_save';
		                        if (ps == T40) {
		                            elementId = 'TrdProjectInvestTrade002_btn_total'
		                        }
		                        WorkFlow.doSaveList(basePath + 'invest/trade/project/TrdProjectInvestTrade003/save', object, elementId,function(result){
	
		  					     });
		                    } else {
		                        elementId = 'TrdProjectInvestTrade002_btn_submit';
		                        if (ps == T40) {
		                            elementId = 'TrdProjectInvestTrade002_btn_total'
		                        }
		                        WorkFlow.doSubmitList(basePath + 'invest/trade/project/TrdProjectInvestTrade003/save', object, elementId, pageId,null, function(result){
		  						      window.BaseRiskMathComm.reloadRiskTable(result);
		  					    });
		                    }
		                });
		            });
	            });
        	});
        }

        var getCommissionAmt = function(){
            if ($('#commissionRate').val() && $('#prinAmt').val()){
                var commissionRate = new BigNumber($('#commissionRate').val()).toFixed(2, JoyinMath.ROUND_HALF_UP);
                var prinAmt = new BigNumber($('#prinAmt').val()).toFixed(2, JoyinMath.ROUND_HALF_UP);
                $('#commissionAmt').val(JoyinMath.doDiv(JoyinMath.doMul(prinAmt,commissionRate),100)).trigger('change').trigger('blur');
            }
            else {
                $('#commissionAmt').val('').trigger('change');
            }
            commissionAmtDistribution(); 
        }
        
        /**
         * 平均分配
         * @returns
         */
        function extracted() {
        	var prinAmt = $('#prinAmt').val();
    	    if(prinAmt){
    	    	prinAmt = JoyinMath.doAdd(prinAmt,0,2);
    	    	//总剩余委托额度
    	    	var entrustMoneyAll = JoyinMath.doAdd(0,0,2);
    	    	for(var i = 0;i<table.getAllRows().length;i++){
    	    		entrustMoneyAll = JoyinMath.doAdd(entrustMoneyAll,table.getAllRows()[i].doEntrustMoney,2);
    	    	}
    	    	var rownum = table.getAllRows().length-1;
    	   	    var sumAmt = 0;
    	    	for(var i = 0;i<table.getAllRows().length;i++){
    	    		var doEntrustMoney = table.getAllRows()[i].doEntrustMoney;
    	    		doEntrustMoney = JoyinMath.doAdd(doEntrustMoney,0,2);
    	    		var amt = JoyinMath.doAdd(0,0,2);
    	    		if(i== rownum){//最后一个组合采用倒减法
    	   			    amt = JoyinMath.doMinus(prinAmt,sumAmt,2);
    	   		    }else{
    	   		    	var scale = JoyinMath.doMul(prinAmt,doEntrustMoney,6);
    					amt = JoyinMath.doDiv(scale, entrustMoneyAll, 2);
    	   		    }				 
    	    		if(Number(amt)>Number(doEntrustMoney)){
    	    			table.renderRow(i, {"prinAmt": JoyinMath.doAdd(doEntrustMoney, 0, 2)});
    	    			sumAmt = JoyinMath.doAdd(sumAmt, doEntrustMoney, 2);
    	    		}else{
    	    			table.renderRow(i, {"prinAmt": JoyinMath.doAdd(amt, 0, 2)});
    	    			sumAmt = JoyinMath.doAdd(sumAmt, amt, 2);
    	    		}
    	    	}
        	}
    	}
        
        /**
         * 时间优先
         */
    	function extractedTime(){
    	  table.reload();
    	  var prinAmt = $('#prinAmt').val();
    	  if(prinAmt){
    		  prinAmt = JoyinMath.doAdd(prinAmt,0,2);
    	      var sumAmt  = JoyinMath.doAdd(0,0,2);
    	      for (var i = 0; i < table.getAllRows().length; i++) {
    	    	    var doEntrustMoney = table.getAllRows()[i].doEntrustMoney;
    	    		if(Number( JoyinMath.doAdd(sumAmt,doEntrustMoney,2))>Number(prinAmt)){
    	    			table.renderRow(i, {"prinAmt": JoyinMath.doMinus(prinAmt,sumAmt,2)});
    	    			for(var j = i+1; j <table.getAllRows().length; j++){
    	    	            table.renderRow(j, {"prinAmt": JoyinMath.doAdd(0, 0, 2)});
    	    	        }
    	    			break;
    	    		}else{
    	    			table.renderRow(i, {"prinAmt": JoyinMath.doAdd(doEntrustMoney,0,2)});
    	    			sumAmt = JoyinMath.doAdd(sumAmt,doEntrustMoney,2);
    	    		}
    	      }
    	  }
    	}
        
    	/**
         * 数量优先
         */
    	function extractedNumber() {
    	   var prinAmt = $('#prinAmt').val();
    	   if(prinAmt){
    	       var allRows = table.getAllRows();
    	       //先按照本金金额排序
    	       function sortValue(a,b){
    	           return b.doEntrustMoney-a.doEntrustMoney;
    	       }
    	       allRows =  allRows.sort(sortValue);
    	       table.clear();
    	       table.addRow(allRows);
    	       var sumAmt  = JoyinMath.doAdd(0,0,2);
    	       for (var i = 0; i < table.getAllRows().length; i++) {
    	    	   var list = new Array();
    	    	   var doEntrustMoney = JoyinMath.doAdd(table.getAllRows()[i].doEntrustMoney,0,2);
    	    	   for (var j = i+1; j < table.getAllRows().length; j++) {
    	    		   if(Number(doEntrustMoney) == Number(JoyinMath.doAdd(table.getAllRows()[j].doEntrustMoney,0,2))){
    	    			   list.push(j);
    	    		   }
    	    	   }
    	    	   if(list.length>0){
    	    		   var amt  = JoyinMath.doAdd(doEntrustMoney,0,2);
    	    		   for(var k = 0;k<list.length;k++){
    	    			   amt = JoyinMath.doAdd(amt,table.getAllRows()[list[k]].doEntrustMoney,2);
    	    		   }
    	    		   if(Number( JoyinMath.doAdd(sumAmt,amt,2))>Number(prinAmt)){
    	    			    var num = JoyinMath.doMinus(prinAmt,sumAmt,5);
    		    			table.renderRow(i, {"prinAmt": JoyinMath.doDiv(num,list.length+1,2)});
    		    			for(var k = 0;k<list.length;k++){
    		    				table.renderRow(list[k], {"prinAmt": JoyinMath.doDiv(num,list.length+1,2)});
    		    			}
    		    			for(var m = i+list.length+1; m <table.getAllRows().length; m++){
    		    	            table.renderRow(m, {"prinAmt": JoyinMath.doAdd(0, 0, 2)});
    		    	        }
    		    			break;
    		    		}else{
    		    			table.renderRow(i, {"prinAmt": JoyinMath.doAdd(doEntrustMoney,0,2)});
    		    			for(var k = 0;k<list.length;k++){
    		    				table.renderRow(list[k], {"prinAmt": JoyinMath.doAdd(doEntrustMoney,0,2)});	
    		    			}
    		    			sumAmt = JoyinMath.doAdd(sumAmt,amt,2);
    		    			i = i+list.length;
    		    		}
    	    	   }else{
    	    		   if(Number( JoyinMath.doAdd(sumAmt,doEntrustMoney,2))>Number(prinAmt)){
    		    			table.renderRow(i, {"prinAmt": JoyinMath.doMinus(prinAmt,sumAmt,2)});
    		    			for(var j = i+1; j <table.getAllRows().length; j++){
    		    	            table.renderRow(j, {"prinAmt": JoyinMath.doAdd(0, 0, 2)});
    		    	        }
    		    			break;
    		    		}else{
    		    			table.renderRow(i, {"prinAmt": JoyinMath.doAdd(doEntrustMoney,0,2)});
    		    			sumAmt = JoyinMath.doAdd(sumAmt,doEntrustMoney,2);
    		    		}
    	    	   }
    	    	   
    	       }
    	   }
    	}
    	
    	/**
         * 组合分配-净价总额分配
         */
        function cpriceAmtDistribution(){
        	var cpriceAmt = 0;
        	var cpriceAmtAll = $("#cpriceAmt").val(); 
    		 var prinAmtAll = $("#prinAmt").val();
    		 if(prinAmtAll && cpriceAmtAll){
    			 var rownum = 0;
    			 var sumAmt = 0;
    			 //寻求表格数据本金金额非零最后索引值
    			 for(var i=0;i<table.getAllRows().length;i++){
    				 var a = table.getAllRows()[i].prinAmt;
    				 if(a && Number(a) != Number(0)){
    					 rownum = i;
    				 }
    			 }
    			 for(var i=0;i<table.getAllRows().length;i++){
    				 var aa = table.getAllRows()[i].prinAmt;
    				 if(i== rownum&& Number(aa) != Number(0)){//最后一个组合采用倒减法
    					 if(aa ){
    						 cpriceAmt = JoyinMath.doMinus(cpriceAmtAll,sumAmt,2); 
    					 }else{
    						 cpriceAmt = JoyinMath.doAdd(0,0,2); 
    					 }					 
    				 }else{					 					 
    					 if(aa && Number(aa) != Number(0)){
    						 var scale = JoyinMath.doMul(cpriceAmtAll,aa,6);
    						 cpriceAmt = JoyinMath.doDiv(scale, prinAmtAll, 2);
    					 }else{
    						 cpriceAmt = JoyinMath.doAdd(0,0,2);
    					 }
    					 sumAmt = JoyinMath.doAdd(sumAmt, cpriceAmt, 2);
    				 }
    				 table.renderRow(i,  {"cpriceAmt": cpriceAmt});				 
    			 }
    			 tradeAmtAndFeeDistribution();
    		 }		
        }
    	
    	/**
         * 组合分配-应计利息总额分配
         */
        function intAmtDistribution(object){
        	var intAmt = 0;
        	var intAmtAll;
    		 var prinAmtAll = $("#prinAmt").val();
    		 if(object.flag){
    			 intAmtAll = object.intAmtAll;
    		 }else{
    			 intAmtAll = $("#intAmt").val(); 
    		 }		 
    		 if(prinAmtAll && intAmtAll){
    			 var rownum = 0;
    			 var sumAmt = 0;
    			 //寻求表格数据本金金额非零最后索引值
    			 for(var i=0;i<table.getAllRows().length;i++){
    				 var a ;
    				 if(object.row == i && !object.flag){
    					 a = object.value;
    				 }else{
    					 a = table.getAllRows()[i].prinAmt;
    				 }
    				 if(a && Number(a) != Number(0)){
    					 rownum = i;
    				 }
    			 }
    			 for(var i=0;i<table.getAllRows().length;i++){
    				 var aa ;
    				 if(object.row == i && !object.flag){
    					 aa = object.value;
    				 }else{
    					 aa = table.getAllRows()[i].prinAmt;
    				 }
    				 if(i== rownum&& Number(aa) != Number(0)){//最后一个组合采用倒减法
    					 if(aa ){
    						 intAmt = JoyinMath.doMinus(intAmtAll,sumAmt,2); 
    					 }else{
    						 intAmt = JoyinMath.doAdd(0,0,2); 
    					 }					 
    				 }else{					 					 
    					 if(aa && Number(aa) != Number(0)){
    						 var scale = JoyinMath.doMul(intAmtAll,aa,6);
    						 intAmt = JoyinMath.doDiv(scale, prinAmtAll, 2);
    					 }else{
    						 intAmt = JoyinMath.doAdd(0,0,2);
    					 }
    					 sumAmt = JoyinMath.doAdd(sumAmt, intAmt, 2);
    				 }
    				 table.renderRow(i,  {"intAmt": intAmt});				 
    			 }
    			 tradeAmtAndFeeDistribution();
    		 }		
        }
        
        /**
         * 组合分配-通道费计算
         */
        var channelFeeDistribution = function() {
        	//update by lwj 20200326 通道费事件 start
		commissionAmtChange();
		//update by lwj 20200326 通道费事件 end
        	 var channelFee = 0;
    		 var prinAmtAll = $("#prinAmt").val();
    		 var channelFeeAll = $("#channelFee").val(); 		 
    		 if(prinAmtAll && channelFeeAll){
    			 var rownum =0;
    			 var sumAmt = 0;
    			//寻求表格数据本金金额非零最后索引值
    			 for(var i=0;i<table.getAllRows().length;i++){
    				 var a = table.getAllRows()[i].prinAmt;
    				 if(a && Number(a) != Number(0)){
    					 rownum = i;
    				 }
    			 }
    			 for(var i=0;i<table.getAllRows().length;i++){
    				 if(i== rownum){//最后一个组合采用倒减法
    					 channelFee = JoyinMath.doMinus(channelFeeAll,sumAmt,2);
    				 }else{
    					 if(table.getAllRows()[i].prinAmt && Number(table.getAllRows()[i].prinAmt)!= Number(0)){
    						 var scale = JoyinMath.doMul(channelFeeAll,table.getAllRows()[i].prinAmt,6);
    						 channelFee = JoyinMath.doDiv(scale, prinAmtAll, 2);
    					 }else{
    						 channelFee = JoyinMath.doAdd(0,0,2);
    					 }
    					 sumAmt = JoyinMath.doAdd(sumAmt, channelFee, 2);
    				 }
    				 table.renderRow(i,  {"channelFee": channelFee});				 
    			 }
    			 tradeAmtAndFeeDistribution();
    		 }else{
    			 for(var i=0;i<table.getAllRows().length;i++){				 
    				 table.renderRow(i,  {"channelFee": JoyinMath.doAdd(0, 0, 2)});				 
    			 }
    			 tradeAmtAndFeeDistribution();
    		 }	
    		 channelFeeIsShow();
        }
        
        /**
         * 组合分配-手续费计算
         */
        var commissionAmtDistribution = function() {
        	 var commissionAmt = 0;
    		 var prinAmtAll = $("#prinAmt").val();
    		 var commissionAmtAll = $("#commissionAmt").val(); 		 
    		 if(prinAmtAll && commissionAmtAll){
    			 var rownum =0;
    			 var sumAmt = 0;
    			//寻求表格数据本金金额非零最后索引值
    			 for(var i=0;i<table.getAllRows().length;i++){
    				 var a = table.getAllRows()[i].prinAmt;
    				 if(a && Number(a) != Number(0)){
    					 rownum = i;
    				 }
    			 }
    			 for(var i=0;i<table.getAllRows().length;i++){
    				 if(i== rownum){//最后一个组合采用倒减法
    					 commissionAmt = JoyinMath.doMinus(commissionAmtAll,sumAmt,2);
    				 }else{
    					 if(table.getAllRows()[i].prinAmt && Number(table.getAllRows()[i].prinAmt)!= Number(0)){
    						 var scale = JoyinMath.doMul(commissionAmtAll,table.getAllRows()[i].prinAmt,6);
    						 commissionAmt = JoyinMath.doDiv(scale, prinAmtAll, 2);
    					 }else{
    						 commissionAmt = JoyinMath.doAdd(0,0,2);
    					 }
    					 sumAmt = JoyinMath.doAdd(sumAmt, commissionAmt, 2);
    				 }
    				 table.renderRow(i,  {"commissionAmt": commissionAmt});				 
    			 }
    			 tradeAmtAndFeeDistribution();
    		 }else{
    			 for(var i=0;i<table.getAllRows().length;i++){				 
    				 table.renderRow(i,  {"commissionAmt": JoyinMath.doAdd(0, 0, 2)});				 
    			 }
    			 tradeAmtAndFeeDistribution();
    		 }	
        }
        
        /**
         * 组合分配-交易金额计算
         */
        function tradeAmtAndFeeDistribution(){
			var ps = $('#ps').val();
			var prinAmt = 0;//本金金额
			var intAmt = 0; //应计利息总额
            var commissionAmt = 0;//手续费
            var tradeAmt = 0;//实付金额
            var channelFee = 0;//通道费
            //add by panbin 2020-3-27 start
            var cpriceAmt = 0;//净价总额
            //add by panbin 2020-3-27 end
            for (var i = 0; i < table.getAllRows().length; i++) {
				if(table.getAllRows()[i].prinAmt){
					prinAmt = JoyinMath.doAdd(table.getAllRows()[i].prinAmt, 0, 2);
					intAmt = JoyinMath.doAdd(table.getAllRows()[i].intAmt, 0, 2);
					commissionAmt = JoyinMath.doAdd(table.getAllRows()[i].commissionAmt, 0, 2);
					channelFee = JoyinMath.doAdd(table.getAllRows()[i].channelFee, 0, 2);
                    //add by panbin 2020-3-27 start
                    cpriceAmt = JoyinMath.doAdd(table.getAllRows()[i].cpriceAmt, 0, 2);
                    //add by panbin 2020-3-27 end
					if(ps == T01){
						//买入时，实付金额 = 本金金额+ 应计利息总额  + 手续费
                        //买入时，实付金额 = 净价总额+ 应计利息总额  + 手续费 update bu panbin 2020-3-27 start
						tradeAmt = JoyinMath.doAdd(JoyinMath.doAdd(cpriceAmt, intAmt, 2), commissionAmt, 2);
                        //买入时，实付金额 = 净价总额+ 应计利息总额  + 手续费 update bu panbin 2020-3-27 end
						table.renderRow(i, {'tradeAmt' : tradeAmt});
					}else{
						//卖出时，实付金额 = 本金金额+ 应计利息总额  - 手续费
                        //卖出时，实付金额 = 净价总额+ 应计利息总额  - 手续费 update bu panbin 2020-3-27 start
						tradeAmt = JoyinMath.doMinus(JoyinMath.doAdd(JoyinMath.doAdd(cpriceAmt, intAmt, 2),channelFee,2), commissionAmt, 2);
                        //卖出时，实付金额 = 净价总额+ 应计利息总额  - 手续费 update bu panbin 2020-3-27 end
						table.renderRow(i, {'tradeAmt' : tradeAmt});
					}
				}
			}
		}
        
        function extractedZiDongFenPei() {
            if ($('#entrustId').val()) {
                if ($('#TrdProjectInvestTrade003_distribution').val() == '139') {//平均分配
                	extracted();
                	setTimeout(function () {
                    	$('.btn-table-add').remove();
                    	setTimeout(function () {
                    		$('.delt').remove();
                    	},1000)
                    },1500);
                } else if ($('#TrdProjectInvestTrade003_distribution').val() == '140') {//时间优先
                    extractedTime();
                    setTimeout(function () {
                    	$('.btn-table-add').remove();
                    	setTimeout(function () {
                    		$('.delt').remove();
                    	},1000)
                    },1500);
                } else if ($('#TrdProjectInvestTrade003_distribution').val() == '141') {//数量优先
                    extractedNumber();
                    setTimeout(function () {
                    	$('.btn-table-add').remove();
                    	setTimeout(function () {
                    		$('.delt').remove();
                    	},1000)
                    },1500);
                } 
                var obj = {flag:true,intAmtAll:$("#intAmt").val()}
                cpriceAmtDistribution();
        	    intAmtDistribution(obj);
                channelFeeDistribution();
                commissionAmtDistribution();
     		    tradeAmtAndFeeDistribution();
            }
        }
        //add by lizhi 2020-2-26 划款指令是否自动生成  start
        var generateOrders = function (callback) {
        	// 如果还款指令设置为隐藏，则自动生成划款指令
        	if(GEN_PAY_ORDERS_AUTO =='01'){
        		$('#bussTrdTransferOrder_btn_generate').click();
        	}
        	// 回调方法
    	    if (callback && typeof callback === 'function') {
    	        callback();
    	    }
        }
        //add by lizhi 2020-2-26 划款指令是否自动生成  end
        /**
         * 画面加载完成
         */
        $(function () {
            $('#commissionRate').change(getCommissionAmt);
            $('#TrdProjectInvestTrade001_btn_view').click(projectAssetView)
            $('#prinAmt').change(prinAmtChange);
            $('#ps').change(psChange);
            $('#commissionAmt').change(commissionAmtChange);
            $('#channelFee').change(channelFeeDistribution);
            $('#chlId').change(channelFeeDistribution);
            $('#tradeAmt').change(tradeAmtChange);
            $('#settleSpeed').change(settleSpeedChange);
            $('#finprodMarketId').change(finprodMarketIdChange);
            // 是否显示划款指令
            if(GEN_PAY_ORDERS_AUTO=='01'){
            	$('#bussTrdTransferOrder_div').hide() ;
            }else{
            	$('#bussTrdTransferOrder_div').show() ;
            }
            $('#TrdProjectInvestTrade002_btn_save').click(function () {
                saveOrSubmit('save');
            });
            $('#TrdProjectInvestTrade002_btn_submit').click(function () {
                saveOrSubmit('submit');
            });
            //分配方式
            $('#TrdProjectInvestTrade003_btn_autoAllocation').click(function () {
            	if($('#prinAmt').val()){
            		extractedZiDongFenPei();
            	}else{
            		Joyin.error('请输入本金金额！');
                    return true;
            	}
            });
            //风险试算
            $('#TrdProjectInvestTrade003_btn_risk').click(function () {
            	//-----------add by jjpei 2020-01-07 风险新版本 start----------
                //检查组合分配的本金金额和金额信息是否一致
      	        if($('#prinAmt').val()){
      	        	var amt = 0;
      	            for(var i=0;i<table.getAllRows().length;i++){
      	            	if(table.getAllRows()[i].prinAmt){
      	            		amt = JoyinMath.doAdd(amt, table.getAllRows()[i].prinAmt, 2);
      	            	}
      	    		 }
      	            if(JoyinMath.doAdd($('#prinAmt').val(),0,2)!=amt){
      	                Joyin.warn("组合分配本金金额和金额信息本金金额不一致！");
      	                return;
      	            }
      	        }
                var object = $.serializeToObject('#informationForm', true);
                window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfBatchData/1/RSK002/F06',object,getriskdata());
//                window.BaseRiskMathComm.initBaseRiskMathCommPage2(getriskdata());
                //-----------add by jjpei 2020-01-07 风险新版本 end----------
                  /*Joyin.ajax(basePath + 'invest/trade/project/TrdProjectInvestTrade003/selectIndex',{},function(result){
                    if(result){
                        var index=result;
                        var object1=getriskdata();
                        var list2=object1.data;
                        for(var i in list2){
                            object1.data[i].mun=list2.length;
                            object1.data[i].index=index;
                        }
                        //window.BaseRiskMathComm.initBaseRiskMathCommPage2(object1);
                        $("#riskMathFlag").val("02");
                    }
                });*/
            });
        });
        /**
         * 参数是json字符串的ajax请求，必须是Post请求
         */
        var ajaxJsonSync = function (url, param, callback) {
            var elementIdForLog = Joyin.getElementId(url);
            if (url && url.indexOf('pageIdForLog') == -1) {
                if (url.indexOf('?') != -1) {
                    if (pageId) {
                        url += '&pageIdForLog=' + pageId + '&elementIdForLog=' + elementIdForLog;
                    }

                } else {
                    if (pageId) {
                        url += '?pageIdForLog=' + pageId + '&elementIdForLog=' + elementIdForLog;
                    }
                }
            }
            // 是否为待办事项标识
            if (param) {
                url += (url.indexOf('?') != -1 ? '&' : '?') + 'isTodoList=' + isTodoList;
            }
            param = JSON.stringify(param);
            if(url && url.indexOf(basePath) != 0 && url.indexOf('/') == 0) {
                url = JoyinUtil.getRealPath(url);
            }
            // “保存”、“提交”操作时，记录画面修改值
            if(elementIdForLog && (elementIdForLog.indexOf('_submit') != -1 || elementIdForLog.indexOf('_save') != -1)) {
                var data = JSON.parse(param);
                if (elementIdForLog.indexOf('_submit') != -1 && data.businessInfo) {
                    JoyinUtil.setModifyFormData(JSON.parse(data.businessInfo));
                } else {
                    JoyinUtil.setModifyFormData(data);
                }
            }
            $.ajax({
                type: 'POST',
                url: url,
                dataType: 'json',
                contentType: 'application/json',
                data: param,
                async:false,
                success: function (result) {
                    Joyin.processResult(result, callback);
                }
            });
        };
      //是否展示资产四五级分类
        if(IS_SHOW_TYPE == '01'){
        	$('#selector5').show();
        	}else{
        	$('#selector5').hide();
        	}
        //应计利息change
        $('#intAmt').change(intAmtChange);
    }
    ()
)
;

