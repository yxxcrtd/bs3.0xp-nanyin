/**
 * 计划类（净值型）多组合投资交易确认
 */
$(function() {
	
	/**
     * 获取查询参数
     */
    var getPageParams = function() {
        return {
        	totleTradeId : totleTradeId,
		  	finprodId : $("#finprodId").val(),
		  	calendarId : $("#calendarId").val(),
		  	tradeDate : $("#tradeDate").val(),
		  	acctTypeList : ACCT_TYPE_A01 + "," + ACCT_TYPE_A07,
		  	ccy : $("#ccy").val(),
		  	unitCprice : $("#unitCprice").val() ? $("#unitCprice").val() : '1',
		  	ps : $("#ps").val()
        	
        }
    };
	
    var table = $("#bussTrdTransfenpeiTable").joyintable({
		ajax: {
            url: 'selectTrdTransfenpeiList',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        searchOnInit:true,
        height:'225px',
        page: false,
        checkbox: false,
        loadonce: true,
        cols:[
              {field:'tradeId', width:100, title: '交易编号',visible:false} //申购确认的前置交易编号
             ,{field:'entrustId',  width:100,title: 'TrdInvestFenPeiInfo.entrustId', sortable:false,type:'text'} //委托代码
             ,{field:'doEntrustMoney',  width:100,title: 'TrdInvestFenPeiInfo.remainEntrustAmt', sortable:false, type:'number', digit:2} //剩余委托额度
             ,{field:'portfolioId', width:100,  title: 'TrdInvestFenPeiInfo.busiId',sortable: false}//组合代码
             ,{field:'tradeAmt',width:100, title: 'TrdPlanCcyInvestTrade002.tradeAmt',sortable:false,type:'number', digit:2} //交易金额
             ,{field:'shareAmt',width:100, title: 'TrdPlanCcyInvestTrade002.tradeSha',sortable:false,type:'number', digit:2} //交易份额
             ,{field:'feeAmtPay',width:100, title: xps == ps_T05?'申购费':'赎回费',sortable:false,type:'number', digit:2} //申购费/赎回费
             ,{field:'bussTrdAvailableCashQueryavalibleCash', width:100,title: 'TrdInvestFenPeiInfo.bussTrdAvailableCashQueryavalibleCash',sortable:false,type:'number', digit:2} //组合T日可用现金
             ,{field:'bussTrdAvailableCashQueryavalibleCash1',width:100, title: 'TrdInvestFenPeiInfo.bussTrdAvailableCashQueryavalibleCash1',sortable:false,type:'number', digit:2} //组合T+1日可用现金
             ,{field:'assetsavailablepositions',width:100, title: 'TrdInvestFenPeiInfo.assetsavailablepositions',sortable:false,type:'number', digit:2} //资产可用持仓
//             ,{field:'operation', width:100,title: 'SysCommon.operation',sortable:false, type:'action', buttons: ['delt']}
           ],
        done:function(){
        	var tradeAmt = 0;
        	var shareAmt = 0;
        	var unitCprice = $('#unitCprice').val();
        	var ps = $("#ps").val();
        	var isDisabled = $("#tradeAmt").prop("disabled")
        	for(var i = 0; i < table.getAllRows().length; i++){
        		tradeAmt = JoyinMath.doAdd(tradeAmt, table.getAllRows()[i].tradeAmt, 2);

        		if( unitCprice && unitCprice!= 1 && ps == ps_T19 && isDisabled){
                    table.renderRow(i,  {"shareAmt": JoyinMath.doDiv(table.getAllRows()[i].tradeAmt,unitCprice,2)});
        		}

        		shareAmt = JoyinMath.doAdd(shareAmt, table.getAllRows()[i].shareAmt, 2);
        	}
        	$('#tradeAmt').val(tradeAmt).trigger('change');
        	$('#shareAmt').val(shareAmt).trigger('change');
        	var ps = $('#ps').val();
        	if(ps==ps_T19 || ps==ps_T05){
        	    $('#tradeAmtHid').val(tradeAmt).trigger('change');
        	}
        }
		});
    
    function feeAmtPayDistribution(){
    	var feeAmtPay = 0;
    	var feeAmtPayAll = $("#feeAmtPay").val() || 0; 
		 var tradeAmtAll = $("#tradeAmt").val();
		 if(tradeAmtAll){
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
						 feeAmtPay = JoyinMath.doMinus(feeAmtPayAll,sumAmt,2); 
					 }else{
						 feeAmtPay = JoyinMath.doAdd(0,0,2); 
					 }					 
				 }else{					 					 
					 if(aa && Number(aa) != Number(0)){
						 var scale = JoyinMath.doMul(feeAmtPayAll,aa,6);
						 feeAmtPay = JoyinMath.doDiv(scale, tradeAmtAll, 2);
					 }else{
						 feeAmtPay = JoyinMath.doAdd(0,0,2);
					 }
					 sumAmt = JoyinMath.doAdd(sumAmt, feeAmtPay, 2);
				 }
				 table.renderRow(i,  {"feeAmtPay": feeAmtPay});				 
			 }
		 }	
		 
		 tradeAmtDistribution();
    }
    
    function feeAmtRedemDistribution(){
    	var feeAmtPay = 0;
    	var feeAmtPayAll = $("#feeAmtRedem").val() || 0;
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
						 feeAmtPay = JoyinMath.doMinus(feeAmtPayAll,sumAmt,2); 
					 }else{
						 feeAmtPay = JoyinMath.doAdd(0,0,2); 
					 }					 
				 }else{					 					 
					 if(aa && Number(aa) != Number(0)){
						 var scale = JoyinMath.doMul(feeAmtPayAll,aa,6);
						 feeAmtPay = JoyinMath.doDiv(scale, shareAmtAll, 2);
					 }else{
						 feeAmtPay = JoyinMath.doAdd(0,0,2);
					 }
					 sumAmt = JoyinMath.doAdd(sumAmt, feeAmtPay, 2);
				 }
				 table.renderRow(i,  {"feeAmtPay": feeAmtPay});				 
			 }
		 }	
    }

    function feeAmtRedemByTradeAmt(){

        var feeAmtPay = 0;
        var feeAmtPayAll = $("#feeAmtRedem").val() || 0;
         var tradeAmtAll = $("#tradeAmt").val();
         if(tradeAmtAll){
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
                         feeAmtPay = JoyinMath.doMinus(feeAmtPayAll,sumAmt,2);
                     }else{
                         feeAmtPay = JoyinMath.doAdd(0,0,2);
                     }
                 }else{
                     if(aa && Number(aa) != Number(0)){
                         var scale = JoyinMath.doMul(feeAmtPayAll,aa,6);
                         feeAmtPay = JoyinMath.doDiv(scale, tradeAmtAll, 2);
                     }else{
                         feeAmtPay = JoyinMath.doAdd(0,0,2);
                     }
                     sumAmt = JoyinMath.doAdd(sumAmt, feeAmtPay, 2);
                 }
                 table.renderRow(i,  {"feeAmtPay": feeAmtPay});
             }
         }
    }
    
    function tradeAmtDistribution(){
    	//单位净价
		var unitCprice = $('#unitCprice').val();
		var ps = $('#ps').val();
		for (var i = 0; i < table.getAllRows().length; i++) {
			if(ps == ps_T05){
				//交易金额
				var tradeAmt = JoyinMath.doAdd(table.getAllRows()[i].tradeAmt, 0, 2);
				//申购费
				var feeAmtPay = JoyinMath.doAdd(table.getAllRows()[i].feeAmtPay, 0, 2);
				//申购确认，交易份额=（交易金额-申购费）/单位净价
				shareAmt = JoyinMath.doDiv(JoyinMath.doMinus(tradeAmt, feeAmtPay, 2), unitCprice, 2);
				table.renderRow(i, {'shareAmt' : shareAmt});
			}else{
				//交易份额
				var shareAmt = JoyinMath.doAdd(table.getAllRows()[i].shareAmt, 0, 2);
				//赎回费
				var feeAmtPay = JoyinMath.doAdd(table.getAllRows()[i].feeAmtPay, 0, 2);
				//交易金额 = 交易份额*单位净价 - 赎回费
				var tradeAmt = JoyinMath.doMinus(JoyinMath.doMul(shareAmt, unitCprice, 2), feeAmtPay);
				table.renderRow(i, {'tradeAmt' : tradeAmt});
			}
		}
    }

    function shareAmtFenpei(){
        //单位净价
        var tradeAmt = $('#tradeAmt').val();
        var shareAmt = $('#shareAmt').val();
        var ps = $('#ps').val();
        if(shareAmt && (ps == ps_T05 || ps == ps_T19) ){
            for (var i = 0; i < table.getAllRows().length; i++) {
                    //交易金额
                    var tradeAmtTmp = JoyinMath.doAdd(table.getAllRows()[i].tradeAmt, 0, 2);
                    //申购确认，
                    var shareAmtTmp = JoyinMath.doDiv(JoyinMath.doMul(tradeAmtTmp, shareAmt),tradeAmt,2);
                    table.renderRow(i, {'shareAmt' : shareAmtTmp});


            }
        }
     }


     function tradeAmtFenpei(){
             //单位净价
             var tradeAmt = $('#tradeAmt').val();
             var shareAmt = $('#shareAmt').val();
             var ps = $('#ps').val();
             if(tradeAmt && ps == ps_T19){
                 for (var i = 0; i < table.getAllRows().length; i++) {

                         //交易 份额
                         var shareAmtTmp = JoyinMath.doAdd(table.getAllRows()[i].shareAmt, 0, 2);

                         //赎回确认，交易金额= 份额/总份额*总金额
                         var tradeAmtTmp = JoyinMath.doMul(JoyinMath.doDiv(shareAmtTmp, shareAmt, 2),tradeAmt,2);
                         table.renderRow(i, {'tradeAmt' : tradeAmtTmp});

                 }
             }
          }
	
    function saveOrSubmit(operateType) {
    	// 划款指令隐藏时，自动生成划款指令信息
    	generateOrders(function () {
	        if (window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()) {
	            return;
	        }
	        var object = $.serializeToObject('#submitForm',true);
	        object.pageModel = pageModel;
	        object.operateType = operateType;
	        object.tradeId = $('#tradeId').val();
	        object.bTradeId = $('#bTradeId').val();
	        object.prinAmt = $('#tradeAmtHid').val();
	        object.portfolioId = 'dzh';
	        if(FileCommon) {
	            if(pageModel == PAGE_MODEL_REFERENCE) {
	                object.fileArray = $('#fileUploader').data('data');
	            }
	            else {
	                object.fileArray = FileCommon.getUploadFileInfo();
	            }
	            if(object.fileArray && _.isArray(object.fileArray) && object.fileArray.length > 0) {
	                $.each(object.fileArray, function(index,obj){
	                    obj.seqNo = obj.fileNo;
	                });
	            } else {
	            	Joyin.error('请上传确认单！');
	            	return;
	            }
	        }
	        
	        Joyin.ajaxJson(basePath + 'invest/trade/plannet/TrdPlanNetInvestTrade005/check', object, function(result) {
	        	Joyin.ajaxJson(basePath + 'invest/trade/plannet/TrdPlanNetInvestTrade005/checkAuthorization', object, function(result1) {
		            Joyin.confirm(result.data, function() {
		                if(operateType === 'save') {
		                    if(object.ps == ps_T24){
		                        WorkFlow.doSaveList(basePath + 'invest/trade/plannet/TrdPlanNetInvestTrade005/save', object, "TrdPlanNetInvestTrade005_btn_total","TrdPlanNetInvestTrade005");
		                    }else{
		                        WorkFlow.doSaveList(basePath + 'invest/trade/plannet/TrdPlanNetInvestTrade005/save', object, "TrdPlanNetInvestTrade003_btn_save","TrdPlanNetInvestTrade005");
		                    }
		                }
		                else {
		                    if(object.ps == ps_T24){
		                        WorkFlow.doSubmitList(basePath + 'invest/trade/plannet/TrdPlanNetInvestTrade005/save', object, "TrdPlanNetInvestTrade005_btn_total","TrdPlanNetInvestTrade005");
		                    }else{
		                        WorkFlow.doSubmitList(basePath + 'invest/trade/plannet/TrdPlanNetInvestTrade005/save', object, "TrdPlanNetInvestTrade003_btn_submit","TrdPlanNetInvestTrade005");
		                    }
		                }
		            });
	        	});
	        });
    	});
    };
    
    // 画面初始化
    var init = function(){
        
    	$('#finprodId').on('change', function () {
            var finprodId = $('#finprodId').val();
            var ps = $('#ps').val();
            var portfolioId =  $('#portfolioId').val();
            if(finprodId){
                var data = $('#finprodId').select2('data')[0].object;
                $("#finprodName").val($('#finprodId').select2('data')[0].id);
                $("#calendarId").val(data.calendarId).trigger('change');
                $("#ccy").val(data.ccy).trigger('change');
                $("#type6").val(data.type6).trigger('change');
                /*添加二三四级联动 zhangpuyu 2019/8/7 start*/
                $("#type9").val(data.type9).trigger('change');
                $("#type10").val(data.type10).trigger('change');
                $("#type11").val(data.type11).trigger('change');
                /*添加二三四级联动 zhangpuyu 2019/8/7 start*/
                // $("#trdInvestPersonInfo_counterId").val(data.issuer).trigger('change');
                $("#type7").val(data.type7).trigger('change');
            }
        });
    	/**
         * 交易方向
         */
        $('#ps').on('change',function(){
            var ps = $('#ps').val();
            var finprodId = $('#finprodId').val();
            var portfolioId =  $('#portfolioId').val();
            if((ps == ps_T19 || ps == ps_T24)){
                table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash',visible: false}]);
                table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash1',visible: false}]);
                table.showColumns([{field:'assetsavailablepositions',visible: true}]);
                $('#expectedArrivalDate_div').show();
                $('#transferInformation').show();
                $('#purchaseFee_div').hide();
                $('#redemptionFee_div').show();
                //赎回确认的时候如果交易金额或者交易份额为空的时候可编辑 yjm start 20191014
                if ($('#tradeAmt').val()){
                    $('#tradeAmt').attr('disabled',true);
                } else {
                    $('#tradeAmt').attr('disabled',false);
                }
                if ($('#shareAmt').val()){
                    $('#shareAmt').attr('disabled',true);
                } else {
                    $('#shareAmt').attr('disabled',false);
                }
                //赎回确认的时候如果交易金额或者交易份额为空的时候可编辑 yjm end 20191014
                // $('#channelFee_div').show();
                if(ps == ps_T24 && single==1){
                    $('#expectedArrivalDate').attr('disabled',false);
                    $('#expectedArrivalDate').attr('data-ignore',true);
                    $('#TrdPlanNetInvestTrade003_btn_save').attr('data-ignore',true).removeClass("layui-btn-disabled");;
                    $('#TrdPlanNetInvestTrade003_btn_submit').attr('data-ignore',true).removeClass("layui-btn-disabled");;
                    $('#TrdPlanNetInvestTrade003_btn_risk').attr('data-ignore',true).removeClass("layui-btn-disabled");;
                }
            }else{
            	table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash',visible: true}]);
                table.showColumns([{field:'bussTrdAvailableCashQueryavalibleCash1',visible: true}]);
                table.showColumns([{field:'assetsavailablepositions',visible: false}]);
                $('#expectedArrivalDate_div').hide();
                $('#transferInformation').hide();
                $('#carryAmt_div').hide();
                $('#purchaseFee_div').show();
                $('#redemptionFee_div').hide();
                $('#tradeAmt').attr('disabled',true);
                $('#shareAmt').attr('disabled',false);
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
//            if(unitCprice){
//                table.table? table.reload() : table.load();
//            }

            var ps = $('#ps').val();
            var shareAmt = $('#shareAmt').val();
            if(ps == ps_T05){
                var tradeAmtHid = $('#tradeAmtHid').val();
                var feeAmtPay = $('#feeAmtPay').val();
                // 申购确认，调整交易份额  交易份额不应显示反算理论值 yjm 20190726
                if( unitCprice != null && unitCprice != '' && unitCprice != 0){
                    $('#shareAmt').val(JoyinMath.doDiv(JoyinMath.doMinus(tradeAmtHid,feeAmtPay,2),unitCprice,2)).trigger('blur');
                }
            }else if((ps == ps_T19 || ps == ps_T24)){
                var isDisabled = $("#tradeAmt").prop("disabled");
                var tradeAmt = $('#tradeAmt').val();
                var redFee = $('#feeAmtRedem').val();
                if(isDisabled){
                    if(unitCprice != null && unitCprice != '' && unitCprice != 0 && tradeAmt){
                        $('#shareAmt').val( JoyinMath.doDiv(JoyinMath.doMinus(tradeAmt,redFee,2) ,unitCprice,2)  ).trigger('change');
                    }
                }else{
                    if(unitCprice != null && unitCprice != '' && shareAmt){
                        $('#tradeAmt').val(shareAmt*unitCprice).trigger('blur');
                        $('#tradeAmtHid').val(shareAmt*unitCprice).trigger('blur');
                        $('#feeAmtRedem').change();
                    }
                }
//                var tradeAmt = $('#tradeAmt').val();
//                if(unitCprice != null && unitCprice != '' && shareAmt){
//                    $('#tradeAmt').val(shareAmt*unitCprice).trigger('blur');
//                    $('#tradeAmtHid').val(shareAmt*unitCprice).trigger('blur');
//                    $('#feeAmtRedem').change();
//                }else if(unitCprice != null && unitCprice != '' && unitCprice != 0 && tradeAmt){
//
//                    $('#shareAmt').val(tradeAmt/unitCprice).trigger('blur');
//                }
            }
        });
        /**
         * 申购费
         */
        $('#feeAmtPay').change(function(){
            var feeAmtPay = $('#feeAmtPay').val();
            var tradeAmt = $('#tradeAmt').val();
            /*var channelFee = $('#channelFee').val();
            if(!channelFee){
                channelFee=0
            }*/
            if(!feeAmtPay){
                feeAmtPay=0;
            }
            if(tradeAmt){
                $('#tradeAmtHid').val(Number(tradeAmt)-Number(feeAmtPay)).trigger('blur');
                var unitCprice = $('#unitCprice').val();
                if(unitCprice){
                    $('#shareAmt').val($('#tradeAmtHid').val()/unitCprice).trigger('blur');
                }
                feeAmtPayDistribution();
                tradeAmtDistribution();
            }
        });
        /**
         * 赎回费
         */
        $('#feeAmtRedem').change(function(){
            if(!$('#unitCprice').val()){
                $('#feeAmtRedem').val('');
                Joyin.error("请先维护单位净值");
                return;
            }
            var feeAmtRedem = $('#feeAmtRedem').val();
            var tradeAmt = $('#tradeAmtHid').val();
            var unitCprice = $('#unitCprice').val();
            //var channelFee = $('#channelFee').val();
            if(!feeAmtRedem){
                feeAmtRedem = 0;
            }
            /*if(!channelFee){
                channelFee=0
            }*/

            var isDisabled = $("#tradeAmt").prop("disabled");

            if(isDisabled){
                // 如果金额不允许编辑 则影响的是份额
                $('#shareAmt').val(JoyinMath.doDiv(JoyinMath.doAdd(tradeAmt,feeAmtRedem,2) ,unitCprice,2)  ).trigger('change');
                feeAmtRedemByTradeAmt();

            }else{
                    $('#tradeAmt').val(Number(tradeAmt)-Number(feeAmtRedem)).trigger('blur');
                    feeAmtRedemDistribution();
                    tradeAmtDistribution();
            }

        });
        
        /**
         * 交易金额
         */
        $('#tradeAmt').on('blur',function(){
            var feeAmtPay = $('#feeAmtPay').val();
            var tradeAmt = $('#tradeAmt').val();
            var unitCprice = $('#unitCprice').val();
            var ps = $('#ps').val();
            if(unitCprice && $('#tradeAmt').val() && ps != ps_T19 && ps != ps_T24){
                $('#shareAmt').val($('#tradeAmt').val()/unitCprice).trigger('blur');
            }

            // 如果是赎回确认 并且是输入金额
            if(ps == ps_T19){
                // 输入份额 多组合列表中重新计算
                tradeAmtFenpei();
            }

        });
        //需求提出不要反算金额 yjm start 20191029
        /*$('#shareAmt').change(function(){
        	if($('#ps').val() == ps_T19){
	            var unitCprice = $('#unitCprice').val();
	            var shareAmt = $('#shareAmt').val();
	            if(unitCprice && shareAmt){
	                $('#tradeAmt').val(unitCprice*shareAmt).trigger('blur');
	            }
        	}
        });*/
        //需求提出不要反算金额 yjm end 20191029

        $('#shareAmt').on('blur',function(){
            if($('#ps').val() == ps_T05 || $('#ps').val() == ps_T19){
                // 输入份额 多组合列表中重新计算
                shareAmtFenpei();
            }
        });
        
        $('#expectedArrivalDate').change(function(){
            var expectedArrivalDate = $('#expectedArrivalDate').val();
            if(expectedArrivalDate){
                window.BussTrdTransferOrder['bussTrdTransferOrder'].generateClick();
            }
         });
        
        window.componentLoadCallback = function(isDone, pagePath, params){
            if(isDone){
            	window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
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
	            		dealinfo.ps = $('#ps').val();
	            		dealinfo.finprodId = $('#finprodId').val();
	            		dealinfo.ccy = $('#ccy').val();
	            		dealinfo.tradeDate = $('#tradeDate').val();
	            		dealinfo.finprodType2 = $('#finprodType2').val();
	            		dealinfo.profitType = $('#profitType').val();
	            		if($('#ps').val() == ps_T24){
	            			dealinfo.tradeDate = $('#expectedArrivalDate').val();
	            		}
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
        $('#netDate').change();

    };
    
    //add by lizhi 2020-2-26 划款指令是否自动生成  start
    var generateOrders = function (callback) {
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
    $(function() {
    	table.load();
        //初始化画面
        init();
        // 是否显示划款指令
        if(GEN_PAY_ORDERS_AUTO=='01'){
        	$('#transferInformation').hide() ;
        }else{
        	$('#transferInformation').show() ;
        }
        // 绑定按钮事件
        $('#TrdPlanNetInvestTrade005_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TrdPlanNetInvestTrade005_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $('#TrdPlanNetInvestTrade003_btn_view').click(function(){
            var finprodId = $('#finprodId').val();
            if(finprodId){
                Joyin.dialog(JoyinUtil.getLabel("FinPlanNetAsset002"),
                        basePath + "invest/asset/plan/planNet/FinPlanNetAsset002/init",
                        "95%", "90%",{pageModel:PAGE_MODEL_REFERENCE,
                        finprodId: finprodId});
            }
        });
        //风险试算
//        $('#TrdPlanNetInvestTrade005_btn_risk').click(function(){
//            var obj = {timePoint:MY_SUP_TIME_TYPE_02,valDate:$('#tradeDate').val(),portfolioCode:'dzh',tradeAmount:$('#tradeAmt').val(),assetType:FINPROD_TYPE_F17,isEnabled:'01',assetCode:$('#finprodId').val(),tradeDirection:$('#ps').val()}
//            var object = {valDate:['tradeDate','TrdPlanNetInvestTrade004.tradeDate'],portfolioCode:['portfolioId','TrdPlanNetInvestTrade004.portfolioId'],tradeAmount:['shareAmt','TrdPlanNetInvestTrade003.tradeSha'],assetType:['type6','TrdPlanNetInvestTrade002.type6'],tradeDirection:['ps','TrdPlanNetInvestTrade001.ps'],data:obj} 
//            window.BaseRiskMathComm.initBaseRiskMathCommPage2(object);
//        })
        
    });
});
