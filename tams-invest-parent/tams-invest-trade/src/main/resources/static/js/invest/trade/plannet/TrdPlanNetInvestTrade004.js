/**
 * 计划类（净值型）多组合交易新建
 */
$(function() {
	var table = $("#bussTrdTransfenpeiTable").joyintable({
        data:[],
        searchOnInit:true,
        height:'225px',
        cols:[
            {field:'entrustId',  width:200, title: 'TrdInvestFenPeiInfo.entrustId', sortable:false, type:'text'}
            ,{field:'portfolioId', width:100,  title: 'TrdInvestFenPeiInfo.busiId', edit:true, sortable: false,
                editOption:{type:'select',id:'portfolioId', multiple: false, showField:'id', searchable: true,
                    url: JoyinUtil.getRealPath('/business/common/CommonSelect/selectPortfolioId?act=true')
                }
            }
             ,{field:'doEntrustMoney', width:150, title: 'TrdPlanNetInvestTrade004.doEntrustMoney', sortable:false, type:'number', digit:2}
             ,{field:'tradeAmt', width:150, title: 'TrdPlanNetInvestTrade004.tradeAmt',edit: true, sortable:false, type:'number', digit:2}
             ,{field:'shareAmt', width:150, title: 'TrdPlanNetInvestTrade004.shareAmt',edit: true, sortable:false, type:'number', digit:2}
             ,{field:'bussTrdAvailableCashQueryavalibleCash', width:150, title: 'TrdPlanNetInvestTrade004.ptlCashAmtT', sortable:false, type:'number', digit:2}
             ,{field:'bussTrdAvailableCashQueryavalibleCash1', width:150, title: 'TrdPlanNetInvestTrade004.ptlCashAmtT1', sortable:false, type:'number', digit:2}
             ,{field:'assetAvailablePosition', width:150, title: 'TrdPlanNetInvestTrade004.assetAvailablePosition', sortable:false, type:'number', digit:2}
             ,{field:'operation', width:20, title: 'SysCommon.operation', sortable:false, type: 'action', buttons: ['delt']}
         ],
         addbtn:true,
         done:function(){
      	   this.bind('click', '.delt', function(data, index) {
                 table.deleteRow(index);
             });
         },
         beforeEditCell:function(value, field, data){
      	   if("portfolioId"==field && $('#entrustId').val()){
      		   return false;
      	   }
      	   if("shareAmt"==field && $('#ps').val()==ps_T04){
      		   return false;
      	   }
      	   if("shareAmt"==field && $('#ps').val()==ps_mr){
      		   return false;
      	   }
      	   if("tradeAmt"==field && $('#ps').val()==ps_T06){
      		   return false;
      	   }
      	   if("tradeAmt"==field && $('#ps').val()==ps_mc){
      		   return false;
      	   }
      	   return true;
         },
         editCell: function(value, data, field, cell, column) {
       	   var params = {};
       	   if("portfolioId" == field){
       		   //交易方向为买入/申购时，查询组合T/T+1日可用现金，为卖出/赎回时，查询资产可用持仓
       		   if(ps_mr==$("#ps").val() || ps_T04==$("#ps").val()){
                      var rowData = {};
           		   table.renderRow(data.LAY_TABLE_INDEX, {"assetAvailablePosition":''})
           		   if(value && value.trim()
                           && $("#calendarId").val() && $("#ccy").val() && $("#tradeDate").val()){
           			   params.portfolioId=value;
           			   params.finprodId=$("#finprodId").val();
           			   params.calendarId=$("#calendarId").val();
           			   params.tradeDate=$("#tradeDate").val();
           			   params.acctTypeList = ACCT_TYPE_A01 + "," + ACCT_TYPE_A07;
           			   params.ccy=$("#ccy").val();
           			   Joyin.ajaxJson(JoyinUtil.getRealPath('/component/trade/BussTrdAvailableCashQuery/getAvailableCash'), params, function (cashdata) {
           				   table.renderRow(data.LAY_TABLE_INDEX, {"bussTrdAvailableCashQueryavalibleCash": cashdata.T_0});
           				   table.renderRow(data.LAY_TABLE_INDEX, {"bussTrdAvailableCashQueryavalibleCash1": cashdata.T_1});
           				   table.renderRow(data.LAY_TABLE_INDEX, {"assetAvailablePosition": cashdata.position});
           			   });
           		   }
           	   } else if(ps_mc==$("#ps").val() || ps_T06==$("#ps").val()){
           		   table.renderRow(data.LAY_TABLE_INDEX, {"bussTrdAvailableCashQueryavalibleCash": ''});
           		   table.renderRow(data.LAY_TABLE_INDEX, {"bussTrdAvailableCashQueryavalibleCash1": ''});
           		   table.renderRow(data.LAY_TABLE_INDEX, {"assetAvailablePosition": ''});
           		   //交易方向为卖出时显示资产可用持仓
           		   params.portfolioId=value;
                      params.finprodId=$("#finprodId").val();
                      params.calendarId=$("#calendarId").val();
                      params.tradeDate=$("#tradeDate").val();
                      params.acctTypeList = ACCT_TYPE_A01 + "," + ACCT_TYPE_A07;
                      params.ccy=$("#ccy").val();
                      Joyin.ajaxJson(JoyinUtil.getRealPath('/component/trade/BussTrdAvailableCashQuery/getAvailableCash'), params, function (cashdata) {
                          table.renderRow(data.LAY_TABLE_INDEX, {"assetAvailablePosition": cashdata.position});
                      });
           	   }  
       	   } 
       	   
	       	if("tradeAmt"==field && value){
	 		   if(ps_mr==$("#ps").val()){
	 			   //交易份额按比例分配
	 			   shareAmtDistribution();
	 		   }
	 	   }
	 	   
	 	   if("shareAmt"==field && value){
	 		   if(ps_mc==$("#ps").val()){
	 			   //卖出交易份额联动交易金额，交易金额按比例分配
	 			   tradeAmtDistribution();
	 		   }
	 	   }
         }
	});
	
	var entrustData;
	var isChange = false;
    
    var getriskdata = function () {
    	var ps = $('#ps').val();
		var mun=null;
        var object = $.serializeToObject('#submitForm', true);
        var bussTrdTransfenpeiDto=null;
        if(object!=null&&object!=''&&object.bussTrdTransfenpeiDto!=null&&object.bussTrdTransfenpeiDto!=''){
            bussTrdTransfenpeiDto=object.bussTrdTransfenpeiDto;
        }
        var list=[];
		for(var i in bussTrdTransfenpeiDto){
			var obj = {
				timePoint: MY_SUP_TIME_TYPE_02,
				tradeDirection: $('#ps').val(),
				valDate: $('#tradeDate').val(),
				portfolioCode: bussTrdTransfenpeiDto[i].portfolioId,
				assetType: FINPROD_TYPE_F17,
				assetCode: $('#finprodId').val(),
                index: null,
                mun: mun
			};
			if($('#type7').val() && $('#type7').val() != ''){
                obj.assetSecType = $('#type7').val();
            }
			if (ps == ps_T06){
				if(Number(bussTrdTransfenpeiDto[i].shareAmt) == Number(0)){
					continue;
	        	}
				obj.tradeAmount = bussTrdTransfenpeiDto[i].shareAmt;
				obj.isEnabled = '01';
			}else{
				if(Number(bussTrdTransfenpeiDto[i].tradeAmt) == Number(0)){
					continue;
	        	}
				obj.tradeAmount = bussTrdTransfenpeiDto[i].tradeAmt;
			}
			list[i]=obj;
		}
		if(list.length>1){
			var object = {
					tradeDirection:['ps','TrdPlanNetInvestTrade001.ps'],
					valDate:['tradeDate','TrdPlanCcyInvestTrade002.tradeDate'],
					portfolioCode:['portfolioId','TrdPlanCcyInvestTrade002.portfolioId'],
					tradeAmount:['tradeAmt','TrdPlanCcyInvestTrade002.tradeAmt'],
					assetType:['type6','TrdPlanCcyInvestTrade002.type6'],
					data:list
			};
			if(ps == ps_T06){
				object.tradeAmount = ['shareAmt','TrdPlanNetInvestTrade002.tradeSha']
			}
			return object;
		}else{
			var object = {
					tradeDirection:['ps','TrdPlanNetInvestTrade001.ps'],
					valDate:['tradeDate','TrdPlanCcyInvestTrade002.tradeDate'],
					portfolioCode:['portfolioId','TrdPlanCcyInvestTrade002.portfolioId'],
					tradeAmount:['tradeAmt','TrdPlanCcyInvestTrade002.tradeAmt'],
					assetType:['type6','TrdPlanCcyInvestTrade002.type6'],
					data:list
			};
			if(ps == ps_T06){
				object.tradeAmount = ['shareAmt','TrdPlanNetInvestTrade002.tradeSha']
			}
			return object;
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
    			if((ps_T04 ==$('#ps').val() || ps_mr ==$('#ps').val()) && !table.getAllRows()[i].tradeAmt){
    				Joyin.error('组合分配列表第'+(i+1)+'行交易金额不能为空！');
                    return true;
    			}
    			if((ps_T06 ==$('#ps').val() || ps_mc ==$('#ps').val()) && !table.getAllRows()[i].shareAmt){
    				Joyin.error('组合分配列表第'+(i+1)+'行交易份额不能为空！');
                    return true;
    			}
    		}
    	}
    	
        if($('#tradeAmt').val()){
        	var number = 0;
        	var tradeAmt = 0;
        	var doEntrustMoney = 0;
        	for(var i=0;i<table.getAllRows().length;i++){
        		tradeAmt = JoyinMath.doAdd(table.getAllRows()[i].tradeAmt, 0, 2);
        		if(tradeAmt){
        			number =  JoyinMath.doAdd(number,tradeAmt,2);
        		}
        		if(table.getAllRows()[i].doEntrustMoney){
        			doEntrustMoney = JoyinMath.doAdd(table.getAllRows()[i].doEntrustMoney, 0, 2);
        			if(Number(tradeAmt)>Number(doEntrustMoney)){
        				Joyin.error('组合分配列表第'+(i+1)+'行交易金额不能大于剩余可用额度!');
        				return true;
        			}
        		}
        	}
        	if(JoyinMath.doAdd($('#tradeAmt').val(),0,2)!=number){
        		Joyin.error('组合分配交易金额和金额信息交易金额不一致！');
        		return true;
        	}
        }
        
        if($('#shareAmt').val()){
        	var number = 0;
        	var shareAmt = 0;
        	for(var i=0;i<table.getAllRows().length;i++){
        		shareAmt = JoyinMath.doAdd(table.getAllRows()[i].shareAmt, 0, 2);
        		if(shareAmt){
        			number =  JoyinMath.doAdd(number,shareAmt,2);
        		}
        	}
        	if(JoyinMath.doAdd($('#shareAmt').val(),0,2)!=number){
        		Joyin.error('组合分配交易份额和金额信息交易份额不一致！');
        		return true;
        	}
        }
        
    }

    function saveOrSubmit(operateType) {
        var s;
        if(BaseRiskMathComm&&$('#ps').val()!=ps_T06) {
            s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
            if(!s && s != ''){
                return;
            }
            if (window.BaseRiskMathComm.isCalcConditionChanged(getriskdata())){
                return;
            }
        }
        //校验组合分配列表
        if(checkFeiPeiAmt()){
        	return;
        }
        
        if (window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()) {
            return;
        }
        var object = $.serializeToObject('#submitForm', true);
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.tradeId = $('#tradeId').val();
        object.prinAmt = $('#tradeAmtHid').val();
        object.settleSpreed = $('#settleSpreed').val();
        
        if(FileCommon) {
            object.fileArray = FileCommon.getUploadFileInfo();
            if(object.fileArray && _.isArray(object.fileArray) && object.fileArray.length > 0){
                $.each(object.fileArray, function(index,obj){
                    obj.seqNo = obj.fileNo;
                });
            }
        }
        var portfolioId;
    	for(var bussTrdTransfenpeiOrder in object.bussTrdTransfenpeiDto){
    		portfolioId = object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].portfolioId + ',' + portfolioId;
    	}
		object.portfolioId = portfolioId;
        if ($('#trdInvestPersonInfo_counterId').select2('data') && $('#trdInvestPersonInfo_counterId').select2('data')[0]) {
            object.counterType = $('#trdInvestPersonInfo_counterId').select2('data')[0].object;
            object.counterId = $('#trdInvestPersonInfo_counterId').select2('data')[0].id;
        }
        
        if($('#tradeAmt').val()){
        	var maxTradeamt = 0;
        	for(var i=0;i<table.getAllRows().length;i++){
        		if (parseFloat(table.getAllRows()[i].tradeAmt) > parseFloat(maxTradeamt)){
						maxTradeamt = table.getAllRows()[i].tradeAmt;
					}
        	}
        	object.maxTradeamt = maxTradeamt;
        }
        object.inputValue1 = 'F04';
        Joyin.ajaxJson(basePath + 'invest/trade/plannet/TrdPlanNetInvestTrade004/check', object, function(result) {
        	Joyin.ajaxJson(basePath + 'invest/trade/TrdCommonController/check', object, function(result1) {
	            Joyin.confirm(result.data, function() {
	                if(operateType === 'save') {
	                    WorkFlow.doSaveList(basePath + 'invest/trade/plannet/TrdPlanNetInvestTrade004/save', object, "TrdPlanNetInvestTrade002_btn_save",function(result){
	
					     });
	                }
	                else {
	                    WorkFlow.doSubmitList(basePath + 'invest/trade/plannet/TrdPlanNetInvestTrade004/save', object, "TrdPlanNetInvestTrade002_btn_submit", pageId,null, function(result){
						      window.BaseRiskMathComm.reloadRiskTable(result);
					   });
	                }
	            });
        	});
        });
    };
    
    //查询资产管理人
    var selectManeger = function(){
        if($('#finprodId').val()){
            var data = $('#finprodId').select2('data')[0].object;
            Joyin.ajaxJson(basePath + 'invest/trade/fund/TrdFundInvestTrade002/selectManeger',{issuer:data.manager},
                    function(result) {
                $("#trdInvestPersonInfo_counterId").textSelect('setValue',result.id);
            });
        }
    }
    
    /**
     * 画星星
     */
    var star = function() {
        // 加红星星
        // 查找label
        var _findPrevLabel = function(element, level) {
            var $el = element.prev();
            if (!$el.is('label') && level > 0) {
                $el = $el.find('label');
                if ($el.length === 0) {
                    return _findPrevLabel(element.parent(), level - 1);
                }
            }
            return $el;
        };

        // 非查看模式
        if (typeof pageModel !== 'undefined'
                && (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_NEW)) {
            $('.must').each(function() {
                $(this).remove();
            })
            $('*[required]').each(function() {
                var $el = $(this);
                var $target = _findPrevLabel($el, 3);
                if ($target.find('> .must').length === 0) {
                    $target.append($('<i class="must">*</i>'));
                }
            });
        }
    }
    
    /**
     * 动态设置不可编辑
     */
    var checkAndAssign = function(el, value, editable){
        if(value != null && value != ''){
            $(el).val(value).trigger('change');
            if(!editable){
                $(el).attr("disabled",true);
            }
        }
    };
	
    //画面初始化
    var init = function() {
    	table.load();
        var entrustId = $("#entrustId").val();
        
        if(entrustId){
        	table.showColumns([{field:'operation',visible: false}]);
        }
        
        $('#finprodId').on('change', function () {
            var finprodId = $('#finprodId').val();
            var ps = $('#ps').val();
            var portfolioId =  $('#portfolioId').val();
            var ccy = $('#ccy').val();
            if(finprodId){
                var data = $('#finprodId').select2('data')[0].object;
                $("#finprodName").val($('#finprodId').select2('data')[0].id);
                $("#calendarId").val(data.calendarId).trigger('change');
                $("#ccy").val(data.ccy).trigger('change');
                $("#type6").val(data.type6).trigger('change');
                /*添加资产二级-四级分类 zhangpuyu 2019/7/31 start*/
                $("#type9").val(data.type9).trigger('change');
                $("#type10").val(data.type10).trigger('change');
                $("#type11").val(data.type11).trigger('change');
                /*添加资产二级-四级分类 zhangpuyu 2019/7/31 end*/
                $("#type7").val(data.type7).trigger('change');
                $("#tradeMarket").val(data.tradeMarket).trigger('change');
                if ((ps == ps_T04 || ps == ps_T06) &&　!entrustId) {
                    $('#trdInvestPersonInfo_counterId option').remove();
//                $("#trdInvestPersonInfo_counterId").textSelect('setValue',data.issuer);
                    selectManeger();
                }
                //add  by zhangnian  20190917  start
                if($('#ps').val()==ps_T04){
                    $("#settleSpreed").val(data.purSpreed).change();
                }else if($('#ps').val()==ps_T06){
                    $("#settleSpreed").val(data.redSpreed).change();
                }
                //add  by zhangnian  20190917  end
            }
            ChannelAssetCommon.setChannelSelectObject($("#chlId"), $('#finprodId').val(), $("#entrustId").val(), function () {

            });
            if($("#type7").val()== plansType01 && ps == ps_T06){
                $('#tradeAmt').val('').attr('disabled',true);
                $('#shareAmt').attr('disabled',false);
            }else{
            	$('#tradeAmt').attr('disabled',false);
            	$('#shareAmt').attr('disabled',false);
            }
            $('#netDate').change();
            
        });

        /**
         * 交易方向
         */
        $('#ps').on('change',function(){
            var ps = $('#ps').val();
            var finprodId = $('#finprodId').val();
            var portfolioId =  $('#portfolioId').val();
            var ccy = $('#ccy').val();
            if(ps == ps_T04){
            	table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash',visible: true}]);
                table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash1',visible: true}]);
                table.showColumns([{field:'assetAvailablePosition',visible: false}]);
                table.showColumns([{field:'shareAmt',visible: false}]);
                $('.expectedArrivalDate_div').hide();
                $('#transferInformation').show();
                $('#tradeSha_div').hide();
                $('#shareAmt').val("");
                $('#tradeAmt_div').show();
                $('#tradeAmt').attr('disabled',false);
                $('#date_div').hide();
                $('#settleSpreed_div').show();
                if(finprodId){
                    $('#trdInvestPersonInfo_counterId option').remove()
//                    $("#trdInvestPersonInfo_counterId").textSelect('setValue',$('#finprodId').select2('data')[0].object.issuer);
                    selectManeger();
                    var data = $('#finprodId').select2('data')[0].object;
                    $("#settleSpreed").val(data.purSpreed);
                }
            }else if(ps == ps_T06){
            	table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash',visible: false}]);
                table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash1',visible: false}]);
                table.showColumns([{field:'assetAvailablePosition',visible: true}]);
                table.showColumns([{field:'shareAmt',visible: true}]);
                $('.expectedArrivalDate_div').show();
                $('#transferInformation').hide();
                $('#tradeSha_div').show();
                $("#type7").change();
                $('#date_div').hide();
                $('#settleSpreed_div').show();
                if(finprodId){
                    $('#trdInvestPersonInfo_counterId option').remove()
//                    $("#trdInvestPersonInfo_counterId").textSelect('setValue',$('#finprodId').select2('data')[0].object.issuer);
                    selectManeger();
                    var data = $('#finprodId').select2('data')[0].object;
                    $("#settleSpreed").val(data.redSpreed);
                }
//                $('#tradeAmt_div').hide();
//                $('#tradeAmt').val("");
                if(finprodId && $("#type7").val()== plansType01 && ps == ps_T06){
                    $('#tradeAmt').val('').attr('disabled',true);
                    $('#shareAmt').attr('disabled',false);
                }
            }else if(ps == ps_mr){
            	table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash',visible: true}]);
                table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash1',visible: true}]);
                table.showColumns([{field:'assetAvailablePosition',visible: false}]);
                table.showColumns([{field:'shareAmt',visible: true}]);
                $('#date_div').show();
                $('.expectedArrivalDate_div').hide();
                $('#transferInformation').show();
                $('#tradeSha_div').show();
//                $('#purchaseFee_div').show();
//                $('#redemptionFee_div').hide();
                $('#tradeAmt').attr('disabled',false);
                $('#shareAmt').attr('disabled',false);
                $('#settleSpreed_div').hide();
                $('#netDate').change();
            }else if(ps == ps_mc){
            	table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash',visible: false}]);
                table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash1',visible: false}]);
                table.showColumns([{field:'assetAvailablePosition',visible: true}]);
                table.showColumns([{field:'shareAmt',visible: true}]);
                $('#date_div').show();
                $('.expectedArrivalDate_div').show();
                $('#transferInformation').show();
                $('#tradeSha_div').show();
//                $('#purchaseFee_div').hide();
//                $('#redemptionFee_div').show();
                $('#tradeAmt').attr('disabled',false);
                $('#shareAmt').attr('disabled',false);
                $('#settleSpreed_div').hide();
                $('#netDate').change();
            }
            // 交易方向变更 清空交易对手 yjm 2019/07/01 start
            if ($('#trdInvestPersonInfo_counterId').select2('data') && $('#trdInvestPersonInfo_counterId').select2('data')[0]) {
                var 交易对手 =  $('#trdInvestPersonInfo_counterId').select2('data')[0].object;
                if (typeof 交易对手 != 'undefined') {
                    if ((交易对手 != 'N' && (ps == ps_mc || ps == ps_mr)) || (ps && 交易对手 && 交易对手 != 'S' && ps != ps_mc && ps != ps_mr)) {
                        clearTrader();
                    }
                }
            }
            //交易方向变更 清空交易对手 yjm 2019/07/01 end
            
            //清算速度
            $('#settleSpreed').trigger('change');
        });

        var clearTrader = function () {
            $('#trdInvestPersonInfo_counterId').val('').change();
            $('#trdInvestPersonInfo_counterTrader').val('');
            $('#trdInvestPersonInfo_contactInfo').val('');
        }

     // zhangnian add 20190917 交易日期+清算速度 联动计算确认日期 start
        var calcCfmDate = function() {
            var tradedate = $('#tradeDate').val();
            var settleSpeed = $('#settleSpreed').val();
            var calendarId = $('#calendarId').val();
            if (!tradedate || !settleSpeed || !calendarId) {
                return;
            }
                Joyin.ajaxSync(basePath + 'invest/trade/TrdCommonController/getNextWorkDay', {
                    'calendarId': calendarId,
                    'tradeDate': tradedate,
                    'settleSpreed':settleSpeed
                }, function (result) {
                    $("#purCfmDate").val(result).trigger("change");
                    $('#expectedArrivalDate').val(result).trigger("change");
                });
        }
        // zhangnian add 20190917 交易日期+清算速度 联动计算确认日期 end
        $('#tradeAmt').change(function(){
            var tradeAmt = $('#tradeAmt').val();
            var ps = $('#ps').val();
            if(ps == ps_T04 || ps == ps_T06){
                if(tradeAmt){
                    $('#shareAmt').val('').attr('disabled',true).attr('required',false);
                }else{
                    $('#shareAmt').attr('disabled',false).attr('required',true);
                }
                star();
            }
            if(ps == ps_mr || ps == ps_mc){
                var feeAmtPay = $('#feeAmtPay').val();
                var unitCprice = $('#unitCprice').val();
                if(unitCprice && $('#tradeAmt').val()){
                    $('#shareAmt').val($('#tradeAmt').val()/unitCprice).trigger('blur');
                }
            }
        });
        
        /**
         * 净值日期
         */
        $('#netDate').change(function(){
            var netDate = $('#netDate').val();
            var finprodId = $('#finprodId').val();
            var tradeAmt = $('#tradeAmt').val();
            if(netDate && finprodId){
                var data = {finprodId:finprodId,netDate:netDate}
                Joyin.ajaxJson(basePath + 'invest/trade/plannet/TrdPlanNetInvestTrade003/getPrice', data , function(result) {
                    $('#unitCprice').val(result).trigger('blur').change();
                });
            }
        });
        /**
         * 单位净价
         */
        $('#unitCprice').change(function(){
            var unitCprice = $('#unitCprice').val();
            var tradeAmt = $('#tradeAmt').val();
            var ps = $('#ps').val();
            var shareAmt = $('#shareAmt').val();
//            if(ps == ps_mr || ps == ps_mc){
//                if(unitCprice != null && unitCprice != '' && unitCprice != 0){
//                    $('#shareAmt').val(tradeAmt/unitCprice).trigger('blur');
//                }
//            }else 
            if((ps == ps_mr || ps == ps_mc)){
                if(unitCprice != null && unitCprice != '' && shareAmt){
                    $('#tradeAmt').val(shareAmt*unitCprice).trigger('blur').attr('disabled',true);
                }else if(unitCprice != null && unitCprice != '' && unitCprice != 0 && tradeAmt){
                    $('#shareAmt').val(tradeAmt/unitCprice).trigger('blur').attr('disabled',true);
                }
            }
        });
        $('#shareAmt').change(function(){
            var shareAmt = $('#shareAmt').val();
            var ps = $('#ps').val();
            if(ps == ps_T04 || ps == ps_T06){
                if(shareAmt){
                    $('#tradeAmt').val('').attr('disabled',true).attr('required',false);
                }else{
                    $('#tradeAmt').attr('disabled',false).attr('required',true);
                }
                star();
            }
            if(ps == ps_mr || ps == ps_mc){
                var unitCprice = $('#unitCprice').val();
                if(unitCprice && shareAmt){
                    $('#tradeAmt').val(unitCprice*shareAmt).trigger('blur');
                }
            }
        });
        
        /**
         * 清算速度change事件
         */
        $('#settleSpreed').change(function(){
            var settleSpeed = $('#settleSpreed').val();
        	var tradeDate = $('#tradeDate').val();
        	if(pageModel==PAGE_MODEL_NEW){
                if (settleSpeed && settleSpeed != '' && tradeDate && tradeDate != ''){
                    /*var num = 0;
                    if (settleSpeed === settleSpreed_T1){
                        num = 1;
                    }else if(settleSpeed === settleSpreed_T2){
                        num = 2;
                    }*/
                   /* var num = settleSpeed;
                    $('#expectedArrivalDate').val(DateUtil.parse(tradeDate).DateAdd('d', num).Format('yyyy-MM-dd')).trigger('change');*/
                }
                if($("#settleSpreed_div").is(":hidden")){
                    var num = 0;
                    $('#expectedArrivalDate').val(DateUtil.parse(tradeDate).DateAdd('d', num).Format('yyyy-MM-dd')).trigger('change');
                }
                //计算确认日期
                calcCfmDate();
            }
        });

        $('#entrustId').change(function(){
            var entrustId = $('#entrustId').val();
            if(entrustId){
                $('#portfolioId').attr('disabled',true);
                $('#ps').attr('disabled',true);
                $('#finprodId').attr('disabled',true);
            }else{
                $('#portfolioId').attr('disabled',false);
                $('#ps').attr('disabled',false);
                $('#finprodId').attr('disabled',false);
            }
        });
        //add by yujiaping 2020-11-23 ZGBS-13新建赎回交易，修改确认日期，预计到账日根据确认日期联动：预计到账日=确认日 start
        $('#purCfmDate').change(function(){
        	var purCfmDate = $('#purCfmDate').val();
        	if(purCfmDate){       		
        		$('#expectedArrivalDate').val(purCfmDate).trigger('change');
        	}
        });
        //add by yujiaping 2020-11-23 ZGBS-13新建赎回交易，修改确认日期，预计到账日根据确认日期联动：预计到账日=确认日 end
        window.componentLoadCallback = function(isDone, pagePath, params){
            if(isDone){
            	var riskparam = getriskdata();
                if (pageModel==PAGE_MODEL_NEW){
                    riskparam.data =null;
                }
                window.BaseRiskMathComm.getparam(riskparam);
                table.addRow(getAllRowsForEntrustId());
                star();
                window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                	if (!$("#tradeAmt").val()) {
                        Joyin.error("请输入交易金额")
                        return;
                    }
                    if (!$("#tradeDate").val()) {
                        Joyin.error("请输入交易日期")
                        return;
                    }
                    if (!$("#ccy").val()) {
                        Joyin.error("请选择币种")
                        return;
                    }
                    if (!$("#finprodId").val()) {
                        Joyin.error("请选择资产")
                        return;
                    }
                    var list = new Array();
	            	var tmpList = table.getAllRows();
	            	for(var dealObj in tmpList){
	            		if(Number(tmpList[dealObj].tradeAmt) == Number(0)){
	            			continue;
	                	}
	            		var dealinfo = {};
	            		dealinfo.counterId = $('#trdInvestPersonInfo_counterId').val();
	            		dealinfo.tradeAmt = tmpList[dealObj].tradeAmt; 
	            		dealinfo.portfolioId = tmpList[dealObj].portfolioId;
	            		dealinfo.profitType = $('#profitType').val();
	            		dealinfo.ps = $('#ps').val();
	            		dealinfo.finprodId = $('#finprodId').val();
	            		dealinfo.ccy = $('#ccy').val();
	            		dealinfo.tradeDate = $('#tradeDate').val();
	            		dealinfo.finprodType2 = $('#finprodType2').val();
	            		list.push(dealinfo);
	        		}
                    return {
                        'isTotalTrade':YES_OR_NO_N
                        ,'bankLendingType':null
                        ,'portfolioId':'dzh'
                        ,'jiaoyiType':'dzh'
                        ,'deal':list
                    };
                });
            }
        }
        
        $('#ps').change();
        $('#finprodId').change();
        $('#tradeAmt').change();
        $('#shareAmt').change();
        $('#entrustId').change();
    };
    
    /**
	 * 交易份额按比例分配
	 * @returns
	 */
	function shareAmtDistribution(){
    	var shareAmt = 0;
    	var shareAmtAll = $("#shareAmt").val(); 
		 var tradeAmtAll = $("#tradeAmt").val();
		 if(tradeAmtAll && shareAmtAll){
			 var rownum = 0;
			 var sumAmt = 0;
			 //寻求表格数据本金金额非零最后索引值
			 for(var i=0;i<table.getAllRows().length;i++){
				 var a = table.getAllRows()[i].tradeAmt;
				 if(a && Number(a) != Number(0)){
					 rownum = i;
				 }
			 }
			 for(var i=0;i<table.getAllRows().length;i++){
				 var aa = table.getAllRows()[i].tradeAmt;
				 if(i== rownum&& Number(aa) != Number(0)){//最后一个组合采用倒减法
					 if(aa ){
						 shareAmt = JoyinMath.doMinus(shareAmtAll,sumAmt,2); 
					 }else{
						 shareAmt = JoyinMath.doAdd(0,0,2); 
					 }					 
				 }else{					 					 
					 if(aa && Number(aa) != Number(0)){
						 var scale = JoyinMath.doMul(shareAmtAll,aa,6);
						 shareAmt = JoyinMath.doDiv(scale, tradeAmtAll, 2);
					 }else{
						 shareAmt = JoyinMath.doAdd(0,0,2);
					 }
					 sumAmt = JoyinMath.doAdd(sumAmt, shareAmt, 2);
				 }
				 table.renderRow(i,  {"shareAmt": shareAmt});				 
			 }
		 }		
    }
	
	/**
	 * 交易金额按比例分配
	 * @returns
	 */
	function tradeAmtDistribution(){
    	var tradeAmt = 0;
    	var tradeAmtAll = $("#tradeAmt").val(); 
		 var shareAmtAll = $("#shareAmt").val();
		 if(shareAmtAll && tradeAmtAll){
			 var rownum = 0;
			 var sumAmt = 0;
			 //寻求表格数据本金金额非零最后索引值
			 for(var i=0;i<table.getAllRows().length;i++){
				 var a = table.getAllRows()[i].shareAmt;
				 if(a && Number(a) != Number(0)){
					 rownum = i;
				 }
			 }
			 for(var i=0;i<table.getAllRows().length;i++){
				 var aa = table.getAllRows()[i].shareAmt;
				 if(i== rownum&& Number(aa) != Number(0)){//最后一个组合采用倒减法
					 if(aa ){
						 tradeAmt = JoyinMath.doMinus(tradeAmtAll,sumAmt,2); 
					 }else{
						 tradeAmt = JoyinMath.doAdd(0,0,2); 
					 }					 
				 }else{					 					 
					 if(aa && Number(aa) != Number(0)){
						 var scale = JoyinMath.doMul(tradeAmtAll,aa,6);
						 tradeAmt = JoyinMath.doDiv(scale, shareAmtAll, 2);
					 }else{
						 tradeAmt = JoyinMath.doAdd(0,0,2);
					 }
					 sumAmt = JoyinMath.doAdd(sumAmt, tradeAmt, 2);
				 }
				 table.renderRow(i,  {"tradeAmt": tradeAmt});				 
			 }
		 }		
    }
    /**
     * 平均分配
     * @returns
     */
    function extracted() {
    	var tradeAmt = $('#tradeAmt').val();
	    if(tradeAmt){
	    	tradeAmt = JoyinMath.doAdd(tradeAmt,0,2);
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
	   			    amt = JoyinMath.doMinus(tradeAmt,sumAmt,2);
	   		    }else{
	   		    	var scale = JoyinMath.doMul(tradeAmt,doEntrustMoney,6);
					amt = JoyinMath.doDiv(scale, entrustMoneyAll, 2);
	   		    }				 
	    		if(Number(amt)>Number(doEntrustMoney)){
	    			table.renderRow(i, {"tradeAmt": JoyinMath.doAdd(doEntrustMoney, 0, 2)});
	    			sumAmt = JoyinMath.doAdd(sumAmt, doEntrustMoney, 2);
	    		}else{
	    			table.renderRow(i, {"tradeAmt": JoyinMath.doAdd(amt, 0, 2)});
	    			sumAmt = JoyinMath.doAdd(sumAmt, amt, 2);
	    		}
	    	}
    	}
	}
    
    function extracted2() {
    	var shareAmt = $('#shareAmt').val();
	    if(shareAmt){
	    	shareAmt = JoyinMath.doAdd(shareAmt,0,2);
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
	   			    amt = JoyinMath.doMinus(shareAmt,sumAmt,2);
	   		    }else{
	   		    	var scale = JoyinMath.doMul(shareAmt,doEntrustMoney,6);
					amt = JoyinMath.doDiv(scale, entrustMoneyAll, 2);
	   		    }				 
	    		if(Number(amt)>Number(doEntrustMoney)){
	    			table.renderRow(i, {"shareAmt": JoyinMath.doAdd(doEntrustMoney, 0, 2)});
	    			sumAmt = JoyinMath.doAdd(sumAmt, doEntrustMoney, 2);
	    		}else{
	    			table.renderRow(i, {"shareAmt": JoyinMath.doAdd(amt, 0, 2)});
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
	  var tradeAmt = $('#tradeAmt').val();
	  if(tradeAmt){
		  tradeAmt = JoyinMath.doAdd(tradeAmt,0,2);
	      var sumAmt  = JoyinMath.doAdd(0,0,2);
	      for (var i = 0; i < table.getAllRows().length; i++) {
	    	    var doEntrustMoney = table.getAllRows()[i].doEntrustMoney;
	    		if(Number( JoyinMath.doAdd(sumAmt,doEntrustMoney,2))>Number(tradeAmt)){
	    			table.renderRow(i, {"tradeAmt": JoyinMath.doMinus(tradeAmt,sumAmt,2)});
	    			for(var j = i+1; j <table.getAllRows().length; j++){
	    	            table.renderRow(j, {"tradeAmt": JoyinMath.doAdd(0, 0, 2)});
	    	        }
	    			break;
	    		}else{
	    			table.renderRow(i, {"tradeAmt": JoyinMath.doAdd(doEntrustMoney,0,2)});
	    			sumAmt = JoyinMath.doAdd(sumAmt,doEntrustMoney,2);
	    		}
	      }
	  }
	}
	
	function extractedTime2(){
		  table.reload();
		  var shareAmt = $('#shareAmt').val();
		  if(shareAmt){
			  shareAmt = JoyinMath.doAdd(shareAmt,0,2);
		      var sumAmt  = JoyinMath.doAdd(0,0,2);
		      for (var i = 0; i < table.getAllRows().length; i++) {
		    	    var doEntrustMoney = table.getAllRows()[i].doEntrustMoney;
		    		if(Number( JoyinMath.doAdd(sumAmt,doEntrustMoney,2))>Number(shareAmt)){
		    			table.renderRow(i, {"shareAmt": JoyinMath.doMinus(shareAmt,sumAmt,2)});
		    			for(var j = i+1; j <table.getAllRows().length; j++){
		    	            table.renderRow(j, {"shareAmt": JoyinMath.doAdd(0, 0, 2)});
		    	        }
		    			break;
		    		}else{
		    			table.renderRow(i, {"shareAmt": JoyinMath.doAdd(doEntrustMoney,0,2)});
		    			sumAmt = JoyinMath.doAdd(sumAmt,doEntrustMoney,2);
		    		}
		      }
		  }
		}
    
    function extractedNumber() {//数量优先
    	var tradeAmt = $('#tradeAmt').val();
  	   if(tradeAmt){
  	       var allRows = table.getAllRows();
  	       //先按照剩余委托额度排序
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
  	    		   if(Number( JoyinMath.doAdd(sumAmt,amt,2))>Number(tradeAmt)){
  	    			    var num = JoyinMath.doMinus(tradeAmt,sumAmt,5);
  		    			table.renderRow(i, {"tradeAmt": JoyinMath.doDiv(num,list.length+1,2)});
  		    			for(var k = 0;k<list.length;k++){
  		    				table.renderRow(list[k], {"tradeAmt": JoyinMath.doDiv(num,list.length+1,2)});
  		    			}
  		    			for(var m = i+list.length+1; m <table.getAllRows().length; m++){
  		    	            table.renderRow(m, {"tradeAmt": JoyinMath.doAdd(0, 0, 2)});
  		    	        }
  		    			break;
  		    		}else{
  		    			table.renderRow(i, {"tradeAmt": JoyinMath.doAdd(doEntrustMoney,0,2)});
  		    			for(var k = 0;k<list.length;k++){
  		    				table.renderRow(list[k], {"tradeAmt": JoyinMath.doAdd(doEntrustMoney,0,2)});	
  		    			}
  		    			sumAmt = JoyinMath.doAdd(sumAmt,amt,2);
  		    			i = i+list.length;
  		    		}
  	    	   }else{
  	    		   if(Number( JoyinMath.doAdd(sumAmt,doEntrustMoney,2))>Number(tradeAmt)){
  		    			table.renderRow(i, {"tradeAmt": JoyinMath.doMinus(tradeAmt,sumAmt,2)});
  		    			for(var j = i+1; j <table.getAllRows().length; j++){
  		    	            table.renderRow(j, {"tradeAmt": JoyinMath.doAdd(0, 0, 2)});
  		    	        }
  		    			break;
  		    		}else{
  		    			table.renderRow(i, {"tradeAmt": JoyinMath.doAdd(doEntrustMoney,0,2)});
  		    			sumAmt = JoyinMath.doAdd(sumAmt,doEntrustMoney,2);
  		    		}
  	    	   }
  	    	   
  	       }
  	   }
  	}
    
    function extractedNumber2() {//数量优先
    	var shareAmt = $('#shareAmt').val();
  	   if(shareAmt){
  	       var allRows = table.getAllRows();
  	       //先按照剩余委托额度排序
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
  	    		   if(Number( JoyinMath.doAdd(sumAmt,amt,2))>Number(shareAmt)){
  	    			    var num = JoyinMath.doMinus(shareAmt,sumAmt,5);
  		    			table.renderRow(i, {"shareAmt": JoyinMath.doDiv(num,list.length+1,2)});
  		    			for(var k = 0;k<list.length;k++){
  		    				table.renderRow(list[k], {"shareAmt": JoyinMath.doDiv(num,list.length+1,2)});
  		    			}
  		    			for(var m = i+list.length+1; m <table.getAllRows().length; m++){
  		    	            table.renderRow(m, {"shareAmt": JoyinMath.doAdd(0, 0, 2)});
  		    	        }
  		    			break;
  		    		}else{
  		    			table.renderRow(i, {"shareAmt": JoyinMath.doAdd(doEntrustMoney,0,2)});
  		    			for(var k = 0;k<list.length;k++){
  		    				table.renderRow(list[k], {"shareAmt": JoyinMath.doAdd(doEntrustMoney,0,2)});	
  		    			}
  		    			sumAmt = JoyinMath.doAdd(sumAmt,amt,2);
  		    			i = i+list.length;
  		    		}
  	    	   }else{
  	    		   if(Number( JoyinMath.doAdd(sumAmt,doEntrustMoney,2))>Number(shareAmt)){
  		    			table.renderRow(i, {"shareAmt": JoyinMath.doMinus(shareAmt,sumAmt,2)});
  		    			for(var j = i+1; j <table.getAllRows().length; j++){
  		    	            table.renderRow(j, {"shareAmt": JoyinMath.doAdd(0, 0, 2)});
  		    	        }
  		    			break;
  		    		}else{
  		    			table.renderRow(i, {"shareAmt": JoyinMath.doAdd(doEntrustMoney,0,2)});
  		    			sumAmt = JoyinMath.doAdd(sumAmt,doEntrustMoney,2);
  		    		}
  	    	   }
  	    	   
  	       }
  	   }
  	}
    
    function extractedZiDongFenPei() {
        if ($('#tradeAmt').val()) {
            if ($('#TrdPlanNetInvestTrade004_distribution').val() == '139') {//平均分配
            	extracted();
            } else if ($('#TrdPlanNetInvestTrade004_distribution').val() == '140') {//时间优先
                extractedTime();
            } else if ($('#TrdPlanNetInvestTrade004_distribution').val() == '141') {//数量优先
                extractedNumber();
            } 
        }
    }
    
    function extractedZiDongFenPei2() {
        if ($('#shareAmt').val()) {
            if ($('#TrdPlanNetInvestTrade004_distribution').val() == '139') {//平均分配
            	extracted2();
            } else if ($('#TrdPlanNetInvestTrade004_distribution').val() == '140') {//时间优先
                extractedTime2();
            } else if ($('#TrdPlanNetInvestTrade004_distribution').val() == '141') {//数量优先
                extractedNumber2();
            }
        }
    }
    
    function getAllRowsForEntrustId(){
        var rowDatas = [];
        var entrustId = $("#entrustId").val();
        if(entrustId){
        	$('#TrdPlanNetInvestTrade004_distribution').attr('disabled',false);
            Joyin.ajaxJson(basePath + 'invest/trade/plannet/TrdPlanNetInvestTrade004/getEntrustInfos', {'entrustId':entrustId},function (data) {
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
                                rowData.doEntrustMoney =element.doEntrustMoney;
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
                        //交易方向为卖出时显示资产可用持仓
                        if(element.etrEntrust.portfolioId
                            && $("#calendarId").val() && $("#ccy").val() && $("#tradeDate").val()){

                            params.portfolioId=element.etrEntrust.portfolioId;
                            params.finprodId=$("#finprodId").val();
                            params.calendarId=$("#calendarId").val();
                            params.tradeDate=$("#tradeDate").val();
                            params.acctTypeList = 'A01,A07';
                            params.ccy=$("#ccy").val();
                            Joyin.ajaxJson(JoyinUtil.getRealPath('/component/trade/BussTrdAvailableCashQuery/getAvailableCash'), params, function (cashdata) {
                                if( cashdata.position){
                                    rowData.assetAvailablePosition =  cashdata.position;
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
     * 平均分配
     * @returns
     */
    
    
    
    
    /**
     * 画面加载完成
     */
    $(function() {
        //初始化画面
        init();
        // 绑定按钮事件
        $('#TrdPlanNetInvestTrade004_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TrdPlanNetInvestTrade004_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $('#TrdPlanNetInvestTrade002_btn_view').click(function(){
            var finprodId = $('#finprodId').val();
            if(finprodId){
                Joyin.dialog(JoyinUtil.getLabel("FinPlanNetAsset002"),
                        basePath + "invest/asset/plan/planNet/FinPlanNetAsset002/init",
                        "95%", "90%",{pageModel:PAGE_MODEL_REFERENCE,
                        finprodId: finprodId});
            }
        });
        // 自动分配
        $('#TrdPlanNetInvestTrade004_btn_autoAllocation').click(function () {
        	var ps = $('#ps').val();
        	if(ps == ps_T04 || ps==ps_mr){
        		extractedZiDongFenPei();
     		    shareAmtDistribution();
        	}else{
        		extractedZiDongFenPei2();
        		tradeAmtDistribution();
        	}
        });
        // 风险试算
        $('#TrdPlanNetInvestTrade004_btn_risk').click(function(){
          //检查组合分配的和金额信息是否一致
            if($('#tradeAmt').val()){
                var number = 0;
                for(var i=0;i<table.getAllRows().length;i++){
                    var tradeAmt=table.getAllRows()[i].tradeAmt==" "?0:table.getAllRows()[i].tradeAmt;
                    if(tradeAmt){
                        number =  JoyinMath.doAdd(number,tradeAmt,2);
                    }
                }
                if(JoyinMath.doAdd($('#tradeAmt').val(),0,2)!=number){
                    Joyin.warn("组合分配中交易金额累加与金额信息中的交易金额不一致");
                    return;
                }
            }
            if($("#ps").val()==ps_T06){
                return;
            }
            var object = $.serializeToObject('#submitForm', true);
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfBatchData/1/RSK002/F04',object,getriskdata());
//        	var object = $.serializeToObject('#submitForm', true);
//            var bussTrdTransfenpeiDto=null;
//            if(object!=null&&object!=''&&object.bussTrdTransfenpeiDto!=null&&object.bussTrdTransfenpeiDto!=''){
//                bussTrdTransfenpeiDto=object.bussTrdTransfenpeiDto;
//            }
//            var list=[];
//    		for(var i in bussTrdTransfenpeiDto){
//    			var obj = {
//    				portfolioCode: bussTrdTransfenpeiDto[i].portfolioId,
//    			};
//    			if ($('#ps').val() == ps_T06){
//    				if(Number(bussTrdTransfenpeiDto[i].shareAmt) == Number(0)){
//    					continue;
//    	        	}
//    				obj.tradeAmount = bussTrdTransfenpeiDto[i].shareAmt;
//    			}else{
//    				if(Number(bussTrdTransfenpeiDto[i].tradeAmt) == Number(0)){
//    					continue;
//    	        	}
//    				obj.tradeAmount = bussTrdTransfenpeiDto[i].tradeAmt;
//    			}
//    			list[i]=obj;
//    		}
//            if(list.length > 1){
//            	Joyin.ajax(basePath + 'invest/trade/planccy/TrdPlanCcyInvestTrade004/selectIndex',{},function(result){
//            		if(result){
//            			var index=result;
//            			var object1=getriskdata();
//            			var list2=object1.data;
//            			for(var i in list2){
//            				object1.data[i].mun=list2.length;
//            				object1.data[i].index=index;
//            			}
//            			//window.BaseRiskMathComm.initBaseRiskMathCommPage2(object1);
//                        $("#riskMathFlag").val("02")
//            		}
//            	});
//            }else{
//            	//window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
//                $("#riskMathFlag").val("02")
//            }
        })
        //是否展示资产四五级分类
        if(IS_SHOW_TYPE == '01'){
        	$('#selector5').show();
        	}else{
        	$('#selector5').hide();
        	}
    });
}());