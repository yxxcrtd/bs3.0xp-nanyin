/**
 * 计划类（货币型）新建
 */
$(function() {
	var entrustData;
	
	var table = $("#bussTrdTransfenpeiTable").joyintable({
    	data: [],
        searchOnInit:true,
        height:'225px',
        cols:[
              {field:'entrustId',  width:100,title: 'TrdInvestFenPeiInfo.entrustId', sortable:false,type:'text'} //委托代码
              ,{field:'doEntrustMoney',  width:100,title: 'TrdInvestFenPeiInfo.remainEntrustAmt', sortable:false,type:'number', digit:2} //剩余委托额度
              ,{field:'portfolioId', width:100,  title: 'TrdInvestFenPeiInfo.busiId',edit: true,
                  editOption:{type:'select', id:'portfolioId', multiple: false, showField:'text',searchable: true,
                      url: JoyinUtil.getRealPath('/business/common/CommonSelect/selectPortfolioId?act=true')
                  },sortable: false
              }//组合代码
             ,{field:'tradeAmt',width:100, title: 'TrdPlanCcyInvestTrade002.tradeAmt',edit: true, sortable:false,type:'number', digit:2} //交易金额
             ,{field:'shareAmt',width:100, title: 'TrdPlanCcyInvestTrade002.tradeSha',edit: true, sortable:false,type:'number', digit:2} //交易份额
             ,{field:'intAmt',width:100, title: 'TrdPlanCcyInvestTrade003.carryAmt',sortable:false,type:'number', digit:2} //待结转份额
             ,{field:'bussTrdAvailableCashQueryavalibleCash', width:100,title: 'TrdInvestFenPeiInfo.bussTrdAvailableCashQueryavalibleCash',sortable:false,type:'number', digit:2} //组合T日可用现金
             ,{field:'bussTrdAvailableCashQueryavalibleCash1',width:100, title: 'TrdInvestFenPeiInfo.bussTrdAvailableCashQueryavalibleCash1',sortable:false,type:'number', digit:2} //组合T+1日可用现金
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
           editCell: function(value, data, field, cell, column){
        	   var params={};
        	   if("portfolioId"==field){
        		   //交易方向为买入/申购时，查询组合T/T+1日可用现金，为卖出/赎回时，查询资产可用持仓
        		   if(ps_mr==$("#ps").val() || ps_T04==$("#ps").val()){
                       var rowData = {};
            		   table.renderRow(data.LAY_TABLE_INDEX, {"assetsavailablepositions":''})
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
            			   });
            		   }
            	   }else if(ps_mc==$("#ps").val() || ps_T06==$("#ps").val()){
            		   table.renderRow(data.LAY_TABLE_INDEX, {"bussTrdAvailableCashQueryavalibleCash": ''});
            		   table.renderRow(data.LAY_TABLE_INDEX, {"bussTrdAvailableCashQueryavalibleCash1": ''});
            		   //交易方向为卖出时显示资产可用持仓
            		   if(value && value.trim()
                               && $("#calendarId").val() && $("#ccy").val() && $("#tradeDate").val() && $("#finprodId").val()){
            			   params.portfolioId=value;
            			   params.finprodId=$("#finprodId").val();
            			   params.calendarId=$("#calendarId").val();
            			   params.tradeDate=$("#tradeDate").val();
            			   params.acctTypeList = ACCT_TYPE_A01 + "," + ACCT_TYPE_A07;
            			   params.ccy=$("#ccy").val();
            			   Joyin.ajaxJson(JoyinUtil.getRealPath('/component/trade/BussTrdAvailableCashQuery/getAvailableCash'), params, function (cashdata) {
            				   table.renderRow(data.LAY_TABLE_INDEX, {"assetsavailablepositions": cashdata.position});
            			   });
            		   }
            	   }  
        	   } 
        	   
        	   if("tradeAmt"==field && value){
        		   if(ps_mr==$("#ps").val()){
        			   //交易份额按比例分配
        			   shareAmtDistribution();
        			   //待结转份额按比例分配
        			   carryAmtDistribution();
        		   }
        	   }
        	   
        	   if("shareAmt"==field && value){
        		   if(ps_mc==$("#ps").val()){
        			   //卖出交易份额联动交易金额，交易金额按比例分配
        			   tradeAmtDistribution();
        			   carryAmtDistribution();
        		   }
        	   }
           }
		});
	
	
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
	 * 待结转份额按比例分配
	 * @returns
	 */
	function carryAmtDistribution(){
    	var intAmt = 0;
    	var intAmtAll = $("#carryAmt").val() || 0; 
		 var shareAmtAll = $("#shareAmt").val();
		 if(shareAmtAll){
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
						 intAmt = JoyinMath.doMinus(intAmtAll,sumAmt,2); 
					 }else{
						 intAmt = JoyinMath.doAdd(0,0,2); 
					 }					 
				 }else{					 					 
					 if(aa && Number(aa) != Number(0)){
						 var scale = JoyinMath.doMul(intAmtAll,aa,6);
						 intAmt = JoyinMath.doDiv(scale, shareAmtAll, 2);
					 }else{
						 intAmt = JoyinMath.doAdd(0,0,2);
					 }
					 sumAmt = JoyinMath.doAdd(sumAmt, intAmt, 2);
				 }
				 table.renderRow(i,  {"intAmt": intAmt});				 
			 }
		 }		
    }
	
    var getriskdata = function () {
    	var ps = $('#ps').val();
		var mun=null;
        var object = $.serializeToObject('#submitForm', true);
        var bussTrdTransfenpeiDto=null;
        if(object!=null&&object!=''&&object.bussTrdTransfenpeiDto!=null&&object.bussTrdTransfenpeiDto!=''){
            bussTrdTransfenpeiDto=object.bussTrdTransfenpeiDto;
        }
        var list=[];
        if (bussTrdTransfenpeiDto != null && object.bussTrdTransfenpeiDto.length > 0){
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
						// continue;
					}
					obj.tradeAmount = bussTrdTransfenpeiDto[i].tradeAmt;
				}
				list[i]=obj;
			}
		}else {
			var obj = {
				timePoint: MY_SUP_TIME_TYPE_02,
				tradeDirection: $('#ps').val(),
				valDate: $('#tradeDate').val(),
				portfolioCode: null,
				assetType: FINPROD_TYPE_F17,
				assetCode: $('#finprodId').val(),
				index: null,
				mun: mun
			};
			if($('#type7').val() && $('#type7').val() != ''){
				obj.assetSecType = $('#type7').val();
			}
			list[0]=obj;
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
				object.tradeAmount = ['shareAmt','TrdPlanCcyInvestTrade003.tradeSha'];
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
				object.tradeAmount = ['shareAmt','TrdPlanCcyInvestTrade003.tradeSha'];
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
                Joyin.error('组合分配待结转份额和金额信息待结转份额不一致！');
                return true;
            }
        }
        
        if($('#tradeAmt').val()){
        	var number = 0;
        	var tradeAmt = 0;
        	var doEntrustMoney = 0;
        	for(var i=0;i<table.getAllRows().length;i++){
        		tradeAmt = table.getAllRows()[i].tradeAmt.replace(',', '');
        		if(tradeAmt){
        			number =  JoyinMath.doAdd(number,tradeAmt,2);
        		}
        		if(table.getAllRows()[i].doEntrustMoney){
        			doEntrustMoney = table.getAllRows()[i].doEntrustMoney.replace(',', '');
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
        		shareAmt = table.getAllRows()[i].shareAmt.replace(',', '');
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
		if(typeof(BaseRiskMathComm) != 'undefined') {
        if(BaseRiskMathComm) {
            s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
            if(!s && s != ''){
                return;
            }
            if (window.BaseRiskMathComm.isCalcConditionChanged(getriskdata())){
                return;
				}
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
//        object.prinAmt = $('#tradeAmtHid').val();
        if(FileCommon) {
            object.fileArray = FileCommon.getUploadFileInfo();
            if(object.fileArray && _.isArray(object.fileArray)){
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
            object.counterId = $('#trdInvestPersonInfo_counterId').val();
        }
        
        if($('#tradeAmt').val()){
        	var tradeAmt = 0;
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


        //可用持仓效验
        var flag1 = true;
        var message = "";
        if (object.bussTrdTransferOrder.length) {
            for (var bussTrdTransferOrder in object.bussTrdTransferOrder) {
                for (var bussTrdTransfenpeiOrder in object.bussTrdTransfenpeiDto) {
                    if ((object.bussTrdTransferOrder[bussTrdTransferOrder].portfolioId == object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].portfolioId) &&
                        (JoyinMath.doAdd(object.bussTrdTransferOrder[bussTrdTransferOrder].settleAmt, 0, 2) == JoyinMath.doAdd(object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].tradeAmt, 0, 2))) {
                        if ( (object.ps === 'T01' || object.ps === 'T04' ) && object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].tradeAmt && object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].bussTrdAvailableCashQueryavalibleCash && Number(object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].tradeAmt) >  Number(object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].bussTrdAvailableCashQueryavalibleCash) ) {
                                message = JoyinUtil.getMessage("TRD_C_0002");
                                flag1 = false;
                        } else if (object.ps == 'T02' && object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].shareAmt && object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].assetsavailablepositions && Number(object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].shareAmt) > Number(object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].assetsavailablepositions)) {
                            Joyin.warn("资产可用持仓必须大于交易份额。");
                            return;
                        }
                    }
                }
            }
        }
        //赎回的时候没有划款指令，单独写
        if(object.bussTrdTransfenpeiDto.length){
            for (var bussTrdTransfenpeiOrder in object.bussTrdTransfenpeiDto) {
                if (object.ps == 'T06' && object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].shareAmt && object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].assetsavailablepositions && Number(object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].shareAmt) > Number(object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].assetsavailablepositions)) {
                    Joyin.warn("资产可用持仓必须大于交易份额。");
                    return;
                }
            }
        }
        if (flag1) {
            message = JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.save"));
            if (operateType === 'submit') {
                message = JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.submit"));
            }
        }


        object.inputValue1 = 'F09';
        Joyin.ajaxJson(basePath + 'invest/trade/planccy/TrdPlanCcyInvestTrade004/check', object, function(result) {
        	//授信检查使用
        	object.prinAmt = object.tradeAmt;
        	Joyin.ajaxJson(basePath + 'invest/trade/TrdCommonController/check', object, function(result1) {
        		delete object.prinAmt;

        		if (result.data && message != result.data) {
                    message += result.data;
                }

	            Joyin.confirm(message, function() {
	                if(operateType === 'save') {
	                    WorkFlow.doSaveList(basePath + 'invest/trade/planccy/TrdPlanCcyInvestTrade004/save', object, "TrdPlanCcyInvestTrade002_btn_save",function(result){
	
					     });
	                }
	                else {
	                    WorkFlow.doSubmitList(basePath + 'invest/trade/planccy/TrdPlanCcyInvestTrade004/save', object, "TrdPlanCcyInvestTrade002_btn_submit", pageId,null, function(result){
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
            if ($('#TrdPlanCcyInvestTrade004_distribution').val() == '139') {//平均分配
            	extracted();
            	setTimeout(function () {
                    $('.btn-table-add').remove();
                    setTimeout(function () {
                        $('.delt').remove();
                    },1000)
                },1500);
            } else if ($('#TrdPlanCcyInvestTrade004_distribution').val() == '140') {//时间优先
                extractedTime();
                setTimeout(function () {
                    $('.btn-table-add').remove();
                    setTimeout(function () {
                        $('.delt').remove();
                    },1000)
                },1500);
            } else if ($('#TrdPlanCcyInvestTrade004_distribution').val() == '141') {//数量优先
                extractedNumber();
                setTimeout(function () {
                    $('.btn-table-add').remove();
                    setTimeout(function () {
                        $('.delt').remove();
                    },1000)
                },1500);
            } 
        }
    }
    
    function extractedZiDongFenPei2() {
        if ($('#shareAmt').val()) {
            if ($('#TrdPlanCcyInvestTrade004_distribution').val() == '139') {//平均分配
            	extracted2();
            	setTimeout(function () {
                    $('.btn-table-add').remove();
                    setTimeout(function () {
                        $('.delt').remove();
                    },1000)
                },1500);
            } else if ($('#TrdPlanCcyInvestTrade004_distribution').val() == '140') {//时间优先
                extractedTime2();
                setTimeout(function () {
                    $('.btn-table-add').remove();
                    setTimeout(function () {
                        $('.delt').remove();
                    },1000)
                },1500);
            } else if ($('#TrdPlanCcyInvestTrade004_distribution').val() == '141') {//数量优先
                extractedNumber2();
                setTimeout(function () {
                    $('.btn-table-add').remove();
                    setTimeout(function () {
                        $('.delt').remove();
                    },1000)
                },1500);
            }
        }
    }

    
    function getAllRowsForEntrustId(){
        var rowDatas = [];
        var entrustId = $("#entrustId").val();
        var ps = $("#ps").val();
        if(entrustId){
        	$('#TrdPlanCcyInvestTrade004_distribution').attr('disabled',false);
            Joyin.ajaxJson(basePath + 'invest/trade/planccy/TrdPlanCcyInvestTrade003/getEntrustInfos', {'entrustId':entrustId, 'ps':ps},function (data) {
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
//                            if( element.etrEntrust.entrustAmt){
//                                rowData.tradeAmt = element.etrEntrust.entrustAmt;//多组合交易金额
//                            }
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
                        if(element.etrEntrust.portfolioId && $("#calendarId").val() && $("#ccy").val() && $("#tradeDate").val()){
                            params.portfolioId=element.etrEntrust.portfolioId;
                            params.finprodId=$("#finprodId").val();
                            params.calendarId=$("#calendarId").val();
                            params.tradeDate=$("#tradeDate").val();
                            params.acctTypeList = 'A01,A07';
                            params.ccy=$("#ccy").val();
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
    }
  //画面初始化
    var init = function(){
    	table.load();
        var entrustId = $("#entrustId").val();
        
        if(entrustId){
        	table.showColumns([{field:'operation',visible: false}]);
        }
        
        $('#finprodId').on('change', function () {
            var finprodId = $('#finprodId').val();
            var ps = $('#ps').val();
            if(finprodId){
                var data = $('#finprodId').select2('data')[0].object;
                $("#finprodName").val($('#finprodId').select2('data')[0].id);
                $("#calendarId").val(data.calendarId).trigger('change');
                $("#ccy").val(data.ccy).trigger('change');
                $("#type6").val(data.type6).trigger('change');
                /*添加资产二级-四级分类 lishuping 2019/7/30 start*/
                $("#type9").val(data.type9).trigger('change');
                $("#type10").val(data.type10).trigger('change');
                $("#type11").val(data.type11).trigger('change');
                /*添加资产二级-四级分类 lishuping 2019/7/30 end*/
                $("#type7").val(data.type7).trigger('change');
                $("#tradeMarket").val(data.tradeMarket).trigger('change');
                if ((ps == ps_T04 || ps == ps_T06) &&　!entrustId) {
                    $('#trdInvestPersonInfo_counterId option').remove()
//                $("#trdInvestPersonInfo_counterId").textSelect('setValue',data.issuer);
                    selectManeger();
                }
            }

            ChannelAssetCommon.setChannelSelectObject($("#chlId"), finprodId, false, function () {

            });
            
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
            	//交易方向为申购时 组合分配列表 组合T/T+1日可用现金显示，资产可用持仓，待结转份额隐藏
            	table.showColumns([{field:'intAmt',visible: false}]);
            	table.showColumns([{field:'tradeAmt',visible: true}]);
            	table.showColumns([{field:'shareAmt',visible: false}]);
            	table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash',visible: true}]);
                table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash1',visible: true}]);
                table.showColumns([{field:'assetsavailablepositions',visible: false}]);
                $('#transferInformation').show();
                $('#tradeSha_div').hide();
                $('#shareAmt').val('');
                $('#tradeAmt_div').show();
                $('#tradeAmt').attr('disabled',false);
                $('#confirmDate').hide();
                $('#trdDate').show();
                $('#settleSpreed_div').show();
                $('#unitCprice_div').hide();
                $('#purchaseFee_div').hide();
                $('#carryAmt_div').hide();
                if(finprodId){
                    $('#trdInvestPersonInfo_counterId option').remove()
                    $("#trdInvestPersonInfo_counterId").textSelect('setValue',$('#finprodId').select2('data')[0].object.manager);
                }
            }else if(ps == ps_T06){
            	//交易方向为赎回时 组合分配列表 组合T/T+1日可用现金，待结转份额隐藏，资产可用持仓显示
            	table.showColumns([{field:'intAmt',visible: false}]);
            	table.showColumns([{field:'tradeAmt',visible: false}]);
            	table.showColumns([{field:'shareAmt',visible: true}]);
            	table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash',visible: false}]);
                table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash1',visible: false}]);
                table.showColumns([{field:'assetsavailablepositions',visible: true}]);
                $('#expectedArrivalDate_div').show();
                $('#transferInformation').hide();
                $('#tradeSha_div').show();
                $('#shareAmt').attr('disabled',false);
                $("#type7").change();
                $('#tradeAmt_div').hide();
                $('#tradeAmt').val('');
                // 非查看模式
                // if (typeof pageModel !== 'undefined'
                //         && (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_NEW)) {
                // 	$('#settleSpreed').val(settlet1).change();
                // }
                $('#confirmDate').hide();
                $('#trdDate').show();
                $('#settleSpreed_div').show();
                $('#unitCprice_div').hide();
                $('#purchaseFee_div').hide();
                $('#carryAmt_div').hide();
                if(finprodId){
                    $('#trdInvestPersonInfo_counterId option').remove()
                    $("#trdInvestPersonInfo_counterId").textSelect('setValue',$('#finprodId').select2('data')[0].object.manager);
                }
            }else if(ps == ps_mr){
            	//交易方向为买入时 组合分配列表 组合T/T+1日可用现金，待结转份额显示，资产可用持仓隐藏
            	table.showColumns([{field:'intAmt',visible: true}]);
            	table.showColumns([{field:'tradeAmt',visible: true}]);
            	table.showColumns([{field:'shareAmt',visible: true}]);
            	table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash',visible: true}]);
                table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash1',visible: true}]);
                table.showColumns([{field:'assetsavailablepositions',visible: false}]);
                $('#expectedArrivalDate_div').hide();
                $('#transferInformation').show();
                $('#tradeSha_div').show();
                $('#shareAmt').attr('disabled',true);
                $('#tradeAmt_div').show();
                $('#tradeAmt').attr('disabled',false);
                $('#confirmDate').show();
                $('#trdDate').hide();
                $('#settleSpreed_div').hide();
                $('#unitCprice_div').show();
                $('#purchaseFee_div').show();
                $('#purchase').show();
                $('#redemp').hide();
                $('#carryAmt_div').show();
            }else if(ps == ps_mc){
            	//交易方向为卖出时 组合分配列表 组合T/T+1日可用现金隐藏，资产可用持仓，待结转份额显示
            	table.showColumns([{field:'intAmt',visible: true}]);
            	table.showColumns([{field:'tradeAmt',visible: true}]);
            	table.showColumns([{field:'shareAmt',visible: true}]);
            	table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash',visible: false}]);
                table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash1',visible: false}]);
                table.showColumns([{field:'assetsavailablepositions',visible: true}]);
                $('#expectedArrivalDate_div').show();
                $('#transferInformation').show();
                $('#tradeSha_div').show();
                $("#type7").change();
                $('#shareAmt').attr('disabled',false);
                $('#tradeAmt_div').show();
                $('#tradeAmt').attr('disabled',true);
                $('#settleSpreed').val(settlet1).change();
                $('#confirmDate').show();
                $('#trdDate').hide();
                $('#settleSpreed_div').hide();
                $('#unitCprice_div').show();
                $('#purchaseFee_div').show();
                $('#purchase').hide();
                $('#redemp').show();
                $('#carryAmt_div').show();
            }

            //清算速度
            $('#settleSpreed').trigger('change');
            
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
        });

        /**
         * 交易方向发生变化清空交易对手
         */
        var clearTrader = function () {
            $('#trdInvestPersonInfo_counterId').val('').trigger('change');
            $('#trdInvestPersonInfo_counterTrader').val('');
            $('#trdInvestPersonInfo_contactInfo').val('');
        }

        /**
         * 单位净价
         */
        $('#unitCprice').change(function(){
            var unitCprice = $('#unitCprice').val();
            var tradeAmt = $('#tradeAmt').val();
            var ps = $('#ps').val();
            var shareAmt = $('#shareAmt').val();
            if(ps == ps_mr){
                if(unitCprice != null && unitCprice != '' && unitCprice != 0){
                    $('#shareAmt').val(tradeAmt/unitCprice).trigger('blur');
                }
            }else if((ps == ps_mc)){
                if(unitCprice != null && unitCprice != '' && shareAmt){
                    $('#tradeAmt').val(shareAmt*unitCprice).trigger('blur').attr('disabled',true);
                    $('#tradeAmtHid').val(shareAmt*unitCprice);
                }else if(unitCprice != null && unitCprice != '' && unitCprice != 0 && tradeAmt){
                    $('#shareAmt').val(tradeAmt/unitCprice).trigger('blur').attr('disabled',true);
                }
            }
        });
        /**
         * 交易金额
         */
        $('#tradeAmt').change(function(){
            var ps = $('#ps').val();
            var tradeAmt = $('#tradeAmt').val();
            var unitCprice = $('#unitCprice').val();
            $('#tradeAmtHid').val(tradeAmt);
            if(ps==ps_mr || ps == ps_mc){
                if(unitCprice && $('#tradeAmt').val()){
                    $('#shareAmt').val($('#tradeAmt').val()/unitCprice).trigger('blur');
                    $('#carryAmt').change();
                    //交易份额按比例分配
                    shareAmtDistribution();
                }
            }
            tradeAmtDistribution();
        });
        /**
         * 交易份额
         */
        $('#shareAmt').change(function(){
        	var ps = $('#ps').val();
            if(ps==ps_mc){
	            var unitCprice = $('#unitCprice').val();
	            var shareAmt = $('#shareAmt').val();
	            if(unitCprice && shareAmt){
	                $('#tradeAmt').val(unitCprice*shareAmt).trigger('blur');
	                $('#tradeAmtHid').val(unitCprice*shareAmt);
	                $('#carryAmt').change();
	                tradeAmtDistribution();
	            }
            }
            shareAmtDistribution();
        });
        /**
         * 待结转份额
         */
        $('#carryAmt').change(function(){
        	var carryAmt = $('#carryAmt').val();
        	var tradeAmtHid = $('#tradeAmtHid').val();
        	if(!carryAmt){
        		carryAmt = 0;
        	}
        	$('#tradeAmt').val(parseFloat(tradeAmtHid)+parseFloat(carryAmt)).trigger('blur');
        	tradeAmtDistribution();
 			carryAmtDistribution();
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
        
        /**
         * 清算速度change事件
         */
        $('#settleSpreed').change(function(){
            var settleSpeed = $('#settleSpreed').val();
        	var tradeDate = $('#tradeDate').val();
        	if (settleSpeed && settleSpeed != '' && tradeDate && tradeDate != ''){
        		var num = 0;
        		if (settleSpeed === settlet1){
        			num = 1;
        		}else if(settleSpeed === settlet2){
        			num = 2;
        		}
        		$('#expectedArrivalDate').val(DateUtil.parse(tradeDate).DateAdd('d', num).Format('yyyy-MM-dd')).trigger('change');
        	}
        	if($("#settleSpreed_div").is(":hidden")){
        		var num = 0;
        		$('#expectedArrivalDate').val(DateUtil.parse(tradeDate).Format('yyyy-MM-dd')).prop("disabled",false).trigger('change');
        	}
        });
        
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
        
        window.componentLoadCallback = function(isDone, pagePath, params){

            if(isDone && window.BussTrdTransferOrder){
				if(typeof(BaseRiskMathComm) != 'undefined') {
                var riskparam = getriskdata();
                if (pageModel==PAGE_MODEL_NEW){
                    riskparam.data =null;
                    window.BaseRiskMathComm.getparam(riskparam);

					} else {
                    //window.BaseRiskMathComm.initBaseRiskMathCommPage(riskparam);
					$("#riskMathFlag").val("02")
					}
                }
                $('#trdInvestPersonInfo_counterTrader').attr('required',true);
                $('#trdInvestPersonInfo_contactInfo').attr('required',true);
                table.addRow(getAllRowsForEntrustId());
            	star();
                window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                	if (!$("#tradeAmt").val()) {
                        Joyin.error("请输入交易金额")
                        return;
                    }
                    if (!$("#tradeDate").val()) {
                        Joyin.error("请输入资金到账日")
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
	            		dealinfo.chlId = $('#chlId').val();
	            		dealinfo.finprodType2 = $('#finprodType2').val();
	            		dealinfo.profitType = $('#profitType').val();
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
//        $('#tradeAmt').change();
        $('#shareAmt').change();
        $('#entrustId').change();
//        table.load();
//        table.addRow(getAllRowsForEntrustId());
    };
    
    /**
     * 画面加载完成
     */
    $(function() {
        //初始化画面
        init();

		//非查看模式存在委托代码时组合分配按钮不置灰
		debugger;
		if($('#entrustId').val() && typeof pageModel !== 'undefined'
			&& (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_NEW)){
			$('#TrdPlanCcyInvestTrade004_btn_autoAllocation').prop('disabled',false).removeClass('layui-btn-disabled');
		}
        
        // 绑定按钮事件
        $('#TrdPlanCcyInvestTrade004_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TrdPlanCcyInvestTrade004_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $('#TrdPlanCcyInvestTrade002_btn_view').click(function(){
            var finprodId = $('#finprodId').val();
            if(finprodId){
                Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                        basePath + "invest/asset/plan/plancur/FinPlanAssetC002/init",
                        "95%", "90%",{pageModel:PAGE_MODEL_REFERENCE,
                        finprodId: finprodId});
            }
        });
      //分配方式
        $('#TrdPlanCcyInvestTrade004_btn_autoAllocation').click(function () {
        	var ps = $('#ps').val();
        	if(ps == ps_T04 || ps==ps_mr){
        		extractedZiDongFenPei();
     		    shareAmtDistribution();
        	}else{
        		extractedZiDongFenPei2();
        		tradeAmtDistribution();
        	}
        	carryAmtDistribution();
		   
        });
      //风险试算
        $('#TrdPlanCcyInvestTrade004_btn_risk').click(function(){
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
                    Joyin.warn("组合分配交易金额和金额信息交易金额不一致");
                    return;
                }
            }
            if($("#ps").val()==ps_T06){
                return;
            }
            var object = $.serializeToObject('#submitForm', true);
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfBatchData/1/RSK002/F09',object,getriskdata());
            
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
//						$("#riskMathFlag").val("02")
//            		}
//            	});
//            }else{
//            	//window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
//				$("#riskMathFlag").val("02")
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