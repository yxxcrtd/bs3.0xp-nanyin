 /**
 * 计划类（货币型）投资交易确认
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
		  	unitCprice : $("#unitCprice").val(),
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
             ,{field:'remainEntrustAmt',  width:100,title: 'TrdInvestFenPeiInfo.remainEntrustAmt', sortable:false,edit:false,type:'number', digit:2} //剩余委托额度
             ,{field:'portfolioId', width:100,  title: 'TrdInvestFenPeiInfo.busiId',sortable: false}//组合代码
             ,{field:'tradeAmt',width:100, title: 'TrdPlanCcyInvestTrade002.tradeAmt',sortable:false,type:'number', digit:2} //交易金额
             ,{field:'shareAmt',width:100, title: 'TrdPlanCcyInvestTrade002.tradeSha',sortable:false,type:'number', digit:2} //交易份额
             ,{field:'intAmt',width:100, title: 'TrdPlanCcyInvestTrade003.carryAmt',sortable:false,type:'number', digit:2, visible:xps == ps_T19?true:false} //待结转份额
             ,{field:'feeAmtPay',width:100, title: xps == ps_T05?'申购费':'赎回费',sortable:false,type:'number', digit:2} //申购费/赎回费
             ,{field:'bussTrdAvailableCashQueryavalibleCash', width:100,title: 'TrdInvestFenPeiInfo.bussTrdAvailableCashQueryavalibleCash',sortable:false,type:'number', digit:2, visible:xps == ps_T05?true:false} //组合T日可用现金
             ,{field:'bussTrdAvailableCashQueryavalibleCash1',width:100, title: 'TrdInvestFenPeiInfo.bussTrdAvailableCashQueryavalibleCash1',sortable:false,type:'number', digit:2, visible:xps == ps_T05?true:false} //组合T+1日可用现金
             ,{field:'assetsavailablepositions',width:100, title: 'TrdInvestFenPeiInfo.assetsavailablepositions',sortable:false,type:'number', digit:2, visible:xps == ps_T19?true:false} //资产可用持仓
//             ,{field:'operation', width:100,title: 'SysCommon.operation',sortable:false, type:'action', buttons: ['delt']}
           ],
        done:function(){
        	var tradeAmt = 0;
        	var shareAmt = 0;
        	for(var i = 0; i < table.getAllRows().length; i++){
        		tradeAmt = JoyinMath.doAdd(tradeAmt, table.getAllRows()[i].tradeAmt, 2);
        		shareAmt = JoyinMath.doAdd(shareAmt, table.getAllRows()[i].shareAmt, 2);
        	}
        	$('#tradeAmt').val(tradeAmt).trigger('blur').change();
        	$('#shareAmt').val(shareAmt).trigger('blur').change();
        }
		});
	
	/**
	 * 交易金额按比例分配
	 * @returns
	 */
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
				//待结转份额
				var carryAmt = JoyinMath.doAdd(table.getAllRows()[i].intAmt, 0, 2);
				//赎回费
				var feeAmtPay = JoyinMath.doAdd(table.getAllRows()[i].feeAmtPay, 0, 2);
				//交易金额 = （交易份额+待结转份额）*单位净价 - 赎回费
				var tradeAmt = JoyinMath.doMinus(JoyinMath.doMul(JoyinMath.doAdd(shareAmt, carryAmt, 2), unitCprice, 2), feeAmtPay);
				table.renderRow(i, {'tradeAmt' : tradeAmt});
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
		 tradeAmtDistribution();
    }
	
	/**
	 * 申购费/赎回费按比例分配
	 * @returns
	 */
	function feeAmtPayDistribution(){
    	var feeAmtPay = 0;
    	var feeAmtPayAll = $("#feeAmtPay").val() || 0; 
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
		 
		 tradeAmtDistribution();
    }
	
	var getriskdata = function () {
        var mun=null;
        var object = $.serializeToObject('#submitForm', true);
        var bussTrdTransfenpeiDto=null;
        if(object!=null&&object!=''&&object.bussTrdTransfenpeiDto!=null&&object.bussTrdTransfenpeiDto!=''){
            bussTrdTransfenpeiDto=object.bussTrdTransfenpeiDto;
        }
        var list=[];
        for(var i in bussTrdTransfenpeiDto){
        	if(Number(bussTrdTransfenpeiDto[i].tradeAmt) == Number(0)){
        		continue;
        	}
            var obj = {
                    timePoint: MY_SUP_TIME_TYPE_02,
                    valDate: $('#tradeDate').val(),
                    portfolioCode: bussTrdTransfenpeiDto[i].portfolioId,
                    tradeAmount: bussTrdTransfenpeiDto[i].tradeAmt,
                    assetType: 'F17',
                    isEnabled: '01',
                    assetCode: $('#finprodId').val(),
                    tradeDirection: $('#ps').val(),
                    index: null,
                    mun: mun}
            list[i]=obj;
        }
        var object = {
        		tradeDirection:['ps','TrdPlanNetInvestTrade001.ps'],
        		valDate:['tradeDate','TrdPlanCcyInvestTrade002.tradeDate'],
        		portfolioCode:['portfolioId','TrdPlanCcyInvestTrade002.portfolioId'],
        		tradeAmount:['shareAmt','TrdPlanCcyInvestTrade003.tradeSha'],
        		assetType:['type6','TrdPlanCcyInvestTrade002.type6'],
        		data:list
        		}  
    	return object;
    }
	
    function saveOrSubmit(operateType) {
    	if (window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()) {
            return;
        }
        var object = $.serializeToObject('#submitForm',true);
        object.pageModel = pageModel;
        object.operateType = operateType;
//        object.tradeId = $('#tradeId').val();
//        object.bTradeId = $('#bTradeId').val();
//        if($('#ps').val() == ps_T05){
//        	object.prinAmt = $('#tradeAmtHid2').val();
//        }else{
//        	object.prinAmt = $('#tradeAmtHid').val();
//        }
        
        if(FileCommon) {
            if(pageModel == PAGE_MODEL_REFERENCE) {
                object.fileArray = $('#fileUploader').data('data');
            }
            else {
                object.fileArray = FileCommon.getUploadFileInfo();
            }
            if(object.fileArray && _.isArray(object.fileArray)){
                $.each(object.fileArray, function(index,obj){
                    obj.seqNo = obj.fileNo;
                });
            }
        }
        Joyin.ajaxJson(basePath + 'invest/trade/planccy/TrdPlanCcyInvestTrade005/check', object, function(result) {
        	Joyin.ajaxJson(basePath + 'invest/trade/planccy/TrdPlanCcyInvestTrade005/checkAuthorization', object, function(result1) {
	            Joyin.confirm(result.data, function() {
	                if(operateType === 'save') {
	                    WorkFlow.doSaveList(basePath + 'invest/trade/planccy/TrdPlanCcyInvestTrade005/save', object, "TrdPlanCcyInvestTrade003_btn_save");
	                }
	                else {
	                    WorkFlow.doSubmitList(basePath + 'invest/trade/planccy/TrdPlanCcyInvestTrade005/save', object, "TrdPlanCcyInvestTrade003_btn_submit");
	                }
	            });
        	});
        });
    };
    
    //画面初始化
    var init = function(){
    	
        $('#finprodId').on('change', function () {
            var finprodId = $('#finprodId').val();
            var ps = $('#ps').val();
            if(finprodId){
                var data = $('#finprodId').select2('data')[0].object;
                $("#finprodName").val($('#finprodId').select2('data')[0].id);
                $("#calendarId").val(data.calendarId).trigger('change');
                $("#ccy").val(data.ccy).trigger('change');
                $("#type6").val(data.type6).trigger('change');
                $("#type7").val(data.type7).trigger('change');
                /*添加二三四级联动 lishuping 2019/8/6 start*/
                $("#type9").val(data.type9).trigger('change');
                $("#type10").val(data.type10).trigger('change');
                $("#type11").val(data.type11).trigger('change');
                /*添加二三四级联动 lishuping 2019/8/6 start*/
                // $("#trdInvestPersonInfo_counterId").val(data.manager).trigger('change');
            }
            ChannelAssetCommon.setChannelSelectObject($("#chlId"), finprodId, false, function () {
            });

            var netDate = $('#netDate').val();
            var tradeAmt = $('#tradeAmt').val();
            var type6 = $('#type6').val();
            if(netDate && finprodId){
                var data = {finprodId:finprodId,netDate:netDate}
                Joyin.ajaxJson(basePath + 'invest/trade/planccy/TrdPlanCcyInvestTrade003/getPrice', data , function(result) {
                    $('#unitCprice').val(result).trigger('blur').change();
                });
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
                $('#proAvailableCash_div').hide();
                $('#proAvailableCash1_div').hide();
                $('#accAvailableCash_div').hide();
                $('#assetAvailablePosition_div').show();
                $('#expectedArrivalDate_div').show();
                $('#transferInformation').show();
                $('#carryAmt_div').show();
                $('#purchase').hide();
                $('#redemp').show();
                if(ps == ps_T24 && single==1){
                    $('#expectedArrivalDate').attr('disabled',false);
                    $('#expectedArrivalDate').attr('data-ignore',true);
                    $('#TrdPlanCcyInvestTrade003_btn_save').attr('data-ignore',true).removeClass("layui-btn-disabled");;
                    $('#TrdPlanCcyInvestTrade003_btn_submit').attr('data-ignore',true).removeClass("layui-btn-disabled");;
                    $('#TrdPlanCcyInvestTrade003_btn_risk').attr('data-ignore',true).removeClass("layui-btn-disabled");;
                }
            }else{
                $('#proAvailableCash_div').show();
                $('#proAvailableCash1_div').show();
                $('#accAvailableCash_div').show();
                $('#assetAvailablePosition_div').hide();
                $('#expectedArrivalDate_div').hide();
                $('#transferInformation').hide();
                $('#carryAmt_div').hide();
                $('#purchase').show();
                $('#redemp').hide();
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
            if(ps == ps_T05){
                if(unitCprice != null && unitCprice != '' && unitCprice != 0){
                    $('#shareAmt').val(tradeAmt/unitCprice).trigger('blur');
                }
            }else if((ps == ps_T19)){
                if(unitCprice != null && unitCprice != '' && shareAmt){
                    $('#tradeAmt').val(shareAmt*unitCprice).trigger('blur').attr('disabled',true);
                    $('#tradeAmtHid').val(shareAmt*unitCprice);
                }else if(unitCprice != null && unitCprice != '' && unitCprice != 0 && tradeAmt){
                    $('#shareAmt').val(tradeAmt/unitCprice).trigger('blur').attr('disabled',true);
                }
            }
        });
        /**
         * 申购费/赎回费
         */
        $('#feeAmtPay').change(function(){
        	var ps = $('#ps').val();

        	if(ps == ps_T05 || ps == ps_T19){
        	    var feeAmtPay = $('#feeAmtPay').val();
                var tradeAmt = $('#tradeAmtHid').val();
                if(Number(feeAmtPay)>= Number(tradeAmt)){
                    if(ps == ps_T05){
                        Joyin.error("申购费不能大于交易金额");
                    }else{
                        Joyin.error("赎回费不能大于交易金额");
                    }
                    $('#feeAmtPay').val('').trigger('change');
                    return;
                }

        	}

        	if(ps == ps_T05){
	            var feeAmtPay = $('#feeAmtPay').val();
	            var tradeAmt = $('#tradeAmtHid').val();
	            var unitCprice = $('#unitCprice').val();
	            //var channelFee = $('#channelFee').val();
                //feeAmtPay = JoyinMath.doAdd(feeAmtPay, channelFee);   
                // 查看页面重复减费用 yjm start 20190909
                if(feeAmtPay && tradeAmt && unitCprice){
                    if (pageModel != PAGE_MODEL_REFERENCE) {
                        $('#shareAmt').val((tradeAmt - feeAmtPay) / unitCprice).trigger('blur');
                        $('#tradeAmtHid2').val(tradeAmt - feeAmtPay);
                    }else {
                        $('#shareAmt').val(tradeAmt / unitCprice).trigger('blur');
                        $('#tradeAmtHid2').val(tradeAmt);
                    }
                }else if(!feeAmtPay){
                    if (pageModel != PAGE_MODEL_REFERENCE) {
                        $('#tradeAmt').val(tradeAmt).change().trigger('blur');
                    }
                    $('#tradeAmtHid2').val(tradeAmt);
                }
                // 查看页面重复减费用 yjm end 20190909
                shareAmtDistribution();
            }else if(ps == ps_T19){
        		$('#carryAmt').change();
        		tradeAmtDistribution();
        	}
        	feeAmtPayDistribution();
        });
        /**
         * 交易金额
         */
        $('#tradeAmt').change(function(){
            var feeAmtPay = $('#feeAmtPay').val();
            var tradeAmt = $('#tradeAmt').val();
            var unitCprice = $('#unitCprice').val();
            if(unitCprice && $('#tradeAmt').val()){
                $('#shareAmt').val($('#tradeAmt').val()/unitCprice).trigger('blur');
                $('#carryAmt').change();
            }
            tradeAmtDistribution();
        });
        
        $('#shareAmt').change(function(){
            var unitCprice = $('#unitCprice').val();
            var shareAmt = $('#shareAmt').val();
            if(unitCprice && shareAmt){
                $('#tradeAmt').val(unitCprice*shareAmt).trigger('blur');
                $('#tradeAmtHid').val(unitCprice*shareAmt);
                $('#carryAmt').change();
            }
            shareAmtDistribution();
        });
        
        $('#carryAmt').change(function(){
        	var carryAmt = $('#carryAmt').val();
        	var feeAmtPay = $('#feeAmtPay').val();
        	var tradeAmtHid = $('#tradeAmtHid').val();
        	//var channelFee = $('#channelFee').val();
        	if(!carryAmt){
        		carryAmt = 0;
        	}
        	if(!feeAmtPay){
        		feeAmtPay = 0;
        	}
           // feeAmtPay = JoyinMath.doAdd(feeAmtPay, channelFee);
        	$('#tradeAmt').val(parseFloat(tradeAmtHid)+parseFloat(carryAmt)-parseFloat(feeAmtPay)).trigger('blur');
        	carryAmtDistribution();
        	tradeAmtDistribution();
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
	            		dealinfo.portfolioId = tmpList[dealObj].portfolioId;
	            		dealinfo.tradeAmt = tmpList[dealObj].tradeAmt; 
	            		dealinfo.profitType = $('#profitType').val();
	            		dealinfo.ps = $('#ps').val();
	            		dealinfo.finprodId = $('#finprodId').val();
	            		dealinfo.ccy = $('#ccy').val();
	            		dealinfo.tradeDate = $('#tradeDate').val();
	            		if(dealinfo.ps == ps_T24){
	            			dealinfo.tradeDate = $('#expectedArrivalDate').val();
	                    }
	            		dealinfo.chlId = $('#chlId').val();
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
//                if(entrustId != ''){
//                    if(etrEntrust){
//                        window.TrdInvestPersonInfo002['trdInvestPersonInfo'].setEntrust(function () {
//                            return etrEntrust;
//                        });
//                    }
//                }
            }
        }
        $('#ps').change();
        $('#finprodId').change();
        $('#unitCprice').change();
        $('#feeAmtPay').change();
    };
    
    /**
     * 画面加载完成
     */
    $(function() {
        //初始化画面
        init(); 
        // 绑定按钮事件
        $('#TrdPlanCcyInvestTrade005_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TrdPlanCcyInvestTrade005_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $('#TrdPlanCcyInvestTrade003_btn_view').click(function(){
            var finprodId = $('#finprodId').val();
            if(finprodId){
                Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                        basePath + "invest/asset/plan/plancur/FinPlanAssetC002/init",
                        "95%", "90%",{pageModel:PAGE_MODEL_REFERENCE,
                        finprodId: finprodId});
            }
        });
        //风险试算
//        $('#TrdPlanCcyInvestTrade005_btn_risk').click(function(){
//        	Joyin.ajax(basePath + 'invest/trade/planccy/TrdPlanCcyInvestTrade004/selectIndex',{},function(result){
//                if(result){
//                    var index=result;
//                    var object1=getriskdata();
//                    var list2=object1.data;
//                    for(var i in list2){
//                        object1.data[i].mun=list2.length;
//                        object1.data[i].index=index;
//                    }
//                    window.BaseRiskMathComm.initBaseRiskMathCommPage2(object1);
//                }
//            });
//        })
      //是否展示资产四五级分类
        if(IS_SHOW_TYPE == '01'){
        	$('#type11Div').show();
        }else{
        	$('#type11Div').hide();
        }
    });
});
