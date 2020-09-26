var table;

(function () {
        //---add by jjpei 20190718----start---获取风险试算画面要素与Label提示文本
    var getRiskData = function () {
        var obj = {timePoint:MY_SUP_TIME_TYPE_02,valDate:$('#tradeDate').val(),portfolioCode:$('#portfolioId').val(),assetType:FINPROD_TYPE_F10,tradeAmount:$('#tradeAmt').val(),isEnabled:USE_STATUS_01,assetCode:$('#finprodId').val(),tradeDirection:$('#ps').val()}
        var object = {valDate:['tradeDate','TrdStock002.tradeDate'],portfolioCode:['portfolioId','TrdStock002.portfolioId'],tradeAmount:['tradeAmt','TrdStock002.tradeAmt'],tradeDirection:['ps','TrdStock002.ps'],data:obj}
        return object;
    }
    //---add by jjpei 20190718----end---获取风险试算画面要素与Label提示文本
    function saveOrSubmit(operateType) {
        var s;
        if(BaseRiskMathComm) {
            s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
            if(!s && s != ''){
                return;
            }
            if (window.BaseRiskMathComm.isCalcConditionChanged(getRiskData())){
                return;
            }
        }
        
        // 检查生成划款指令参数是否发生变化
        if (window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()) {
            return;
        }
        var object = $.serializeToObject('#submitForm', true);
        object.pageModel = pageModel;
        object.operateType = operateType;
        //股票交易不能做内部交易 yjm start 20191119
        if (object.trdInvestPersonInfo && object.trdInvestPersonInfo.isOutTrade == 'N'){
            Joyin.error("股票交易不可做内部交易！");
            return false;
        }
        //股票交易不能做内部交易 yjm end 20191119
        if(FileCommon) {
            object.uploadFileList = FileCommon.getUploadFileInfo();
        }
        object.assetPosition = $("#bussTrdAvailableCashQuery_assetPosition").val(); // 资产可用持仓
        var data = $("#chlId").find("option:selected").data("data");
        if (data && data.object) {
            object.payType = data.object;
        }
        if ($('#trdInvestPersonInfo_counterId').select2('data') && $('#trdInvestPersonInfo_counterId').select2('data')[0]) {
            object.counterType = $('#trdInvestPersonInfo_counterId').select2('data')[0].object;
            object.counterId = $('#trdInvestPersonInfo_counterId').select2('data')[0].id;
        }
        Joyin.ajaxJson(basePath + 'invest/trade/TrdCommonController/check', object, function(result1) {
	        Joyin.ajaxJson(basePath + 'invest/trade/stockdeal/TrdStock002/check', object, function(result) {
		          Joyin.confirm(result.data, function() {
		              if(operateType === 'save') {
		                  WorkFlow.doSaveList(basePath + 'invest/trade/stockdeal/TrdStock002/save', object, "TrdStock002_btn_save");
		              }
		              else {
		                  WorkFlow.doSubmitList(basePath + 'invest/trade/stockdeal/TrdStock002/save', object, "TrdStock002_btn_submit");
		              }
		          });
	        });
        });
    };
    
    /**
     * 1.1资产代码选择时
     * 资产名称文本框根据资产代码选择自动显示对应名称;
     * 同时带出资产相关信息，交易场所，币种，交易日历;
     */
    var finprodIdChange= function(){

        var finprodId = $('#finprodId').val();
        var data = $('#finprodId').select2('data')[0].object;
        if($('#finprodId').select2('data')[0].id){
        	if (data && data.finprodId) {
        	// 资产名称文本框根据资产代码选择自动显示对应名称;
            $("#finprodName").val(data.finprodName).trigger('change');
            // 同时带出资产相关信息，包括上市版，交易场所，币种;
            $("#board").val(data.board).trigger('change');
            $("#tradeMarket").val(data.tradeMarket).trigger('change');
            $("#ccy").val(data.ccy).trigger('change');
//            $("#basis").val(data.basis).trigger('change');
            }
        	var ps = $('#ps').val()
        	if (ps === PS_T01 || ps === PS_T48 || ps === PS_T46 || ps === PS_T47) {
                getPtlCashPositionInfo();
            }
            if (ps === PS_T02) {
                getPtlSecPositionInfo();
            }
        } 
        
    
    }
    
  //组合代码change事件
    var portfolioIdChange = function(){
        var ps = $('#ps').val();
        var ccy = $('#ccy').val();
        var portfolioId = $('#portfolioId').val();
        var vdate = $('#tradeDate').val();
        if (ps === PS_T01 || ps === PS_T48 || ps === PS_T46 || ps === PS_T47) {
            getPtlCashPositionInfo();
        }
        if (ps === PS_T02) {
            getPtlSecPositionInfo();
        }
        //通道费
//        getProTongDaoCash();
    
    }
    
    /**
     * 画面联动处理
     * 
     * 上市板
     * 当上市板为科创板时，交易方式选项中增加盘后定价交易。否则，交易方式选项中去除盘后定价交易
     */
    var boardChange = function (){
        var dealMode = $('#dealMode').val();
        var board=$("#board").val();
    	if(board != BOARD_BD03 && dealMode == DEAL_MODE_03){
    		$("#board").val('');
    		Joyin.error("交易方式为盘后定价交易，上市板只能选择科创板");
    		return false;
    	}
    }
    
    /**
     *  交易方式
     * 
     * 当上市板为科创板时，交易方式选项中增加盘后定价交易。否则，交易方式选项中去除盘后定价交易
     * 当交易方式选择为转仓时，投资交易人员信息组件.交易对手只查询出内部对手的数据。否则，只查询外部对手的数据。
     * 当交易方式为大宗交易专场时，交割日可录入，其他默认等于交易日不可改。
     */
    var dealModeChange = function(){

	   	 var dealMode = $('#dealMode').val();
	   	 var withLockPeriod = $('#withLockPeriod').val();
	   	 //交易方式不为大宗交易或者不为大宗交易专场
	   	 if(dealMode != DEAL_MODE_02 && dealMode != DEAL_MODE_04){
	   		 //则是否有锁定期默认为否，且不可修改
	   		 $("#withLockPeriod").attr("disabled", true);
	   		 $("#withLockPeriod").val(WITH_LOCK_PERIOD_T02);
	   		 $("#lockMdate").val('');
	   		 $("#lockMdate").attr("disabled", true); 
	   	 }else {
	   		 //则是否有锁定期为是可选择可修改
	   		 $("#withLockPeriod").attr("disabled", false);
	//   		 $("#lockMdate").attr("disabled", false);
	   	 }
   	 	if (dealMode) {
        	var board=$("#board").val();
        	if(board != BOARD_BD03 && dealMode == DEAL_MODE_03){
        		 $('#dealMode').val('');
        		Joyin.error("只有上市板为科创板的才可选择盘后定价交易");
        		return false;
        	}
        	//当交易方式为大宗交易专场时，交割日可录入，其他默认等于交易日不可改。
            if (dealMode == DEAL_MODE_04) {
            	$("#settleDate").attr("disabled", false);
            } else {
            	var tradeDate = $('#tradeDate').val();
            	if(tradeDate != null){
            		$("#settleDate").val(tradeDate);
            	}
            	$("#settleDate").attr("disabled", true);
            }
        }
    
    }
    
    
    /**
     * 交易方式，交易方向与是否有锁定期联动
     * 交易方向为新股中签，或交易方式为大宗交易/大宗交易专场时可选择，其他情况默认为否不可改；
     */
    
    var psChange = function(){
   	 var dealMode = $('#dealMode').val();
   	 var withLockPeriod = $('#withLockPeriod').val();
	 	var ps=$("#ps").val();
		var saleCode=$("saleCode").val();
		$("#saleCode_div").attr("disabled", true);
		 if (ps == PS_T01 || ps == PS_T48 || ps == PS_T46 || ps == PS_T47 ) {
		 	//交易方向为买入、配股、增发（配售）、新股中签时显示此字段
			 
			 $('#Cash_div').show();
//			 $('#chlIdAmt_div').show();
			 $("#assetAvailablePosition_div").hide();
			 if($('#portfolioId')){
				 getPtlCashPositionInfo();
			 }
		 }else if(ps == PS_T02){
			 //卖出
			 $('#Cash_div').hide();
//			 $('#chlIdAmt_div').hide();
			 $("#assetAvailablePosition_div").show();
			 if($('#portfolioId')){
				 getPtlSecPositionInfo();
			 }
		} 
		 //配售 增发（配售）  配售代码可填
		 if(ps==PS_T46 || ps==PS_T47){
			   $("#saleCode").attr("disabled", false);
		}else{
			$("#saleCode").val('').prop("disabled", true);
		}	
//   	 交易方式，交易方向与是否有锁定期联动
//        * 交易方向为新股中签，或交易方式为大宗交易/大宗交易专场时可选择，其他情况默认为否不可改；
	   	 if(ps != PS_T48 && dealMode != DEAL_MODE_02 && dealMode != DEAL_MODE_04){
	   		 $("#withLockPeriod").attr("disabled", true);
	   		 $("#withLockPeriod").attr("value",WITH_LOCK_PERIOD_T02);
	   	 }else {
	   		 $("#withLockPeriod").attr("disabled", false);
	   		 var withLockPeriod = $('#withLockPeriod').val();
	   	 }
    }
       
    
    /**
     * 是否有锁定期与锁定截止日联动
     */
     var withLockPeriodChange = function(){
    	 var withLockPeriod = $("#withLockPeriod").val();
    	 var lockMdate = $("#lockMdate").val();
    	 if(withLockPeriod==WITH_LOCK_PERIOD_T02){
    		 $("#lockMdate").attr("disabled", true);
    	 }else{
    		 $("#lockMdate").attr("disabled", false);
    		 var lockMdate = $('#lockMdate').val();
    	 }
     
     }
    
    /**
     * 画面联动处理
     * 交易日
     */
     var tradeDateChange = function(){
         var tradeDate = $('#tradeDate').val();
         var dealMode = $('#dealMode').val();
         if (tradeDate && dealMode != DEAL_MODE_04 ) {
         	$("#settleDate").val(tradeDate);
         }
         getPtlCashPositionInfo()
     
     }
    
    
    
     /**
      * 动态修改佣金 印花税
      */
     var changeTradeAmtAndFee = function(flag) {
    	 if (pageModel != PAGE_MODEL_EDIT && pageModel != PAGE_MODEL_NEW){
         	return ;
         }
    	 if($("#ps").val() == PS_T48||$("#chlId").val()){//新股中签
         	//交易价格*交易数量  交易费用为零
              $("#tradeAmt").val(JoyinMath.doMul($("#unitCprice").val(),$("#shareAmt").val(), 2)).trigger('blur');
              $("#commission").val('').prop("disabled", true);
              $("#stampDuty").val('').prop("disabled", true);
              return ;
          }else{
        	  $("#commission").prop("disabled", false);
        	  $("#stampDuty").prop("disabled", false);
        	
          }
    	 
    	 if($('#tradeDate').val()&&$('#unitCprice').val()&&$('#shareAmt').val()
    			 &&$('#tradeMarket').val()&&$('#ps').val()&&$('#secManageAcctId').val()){
    		 var paramObj = {};
        	 paramObj.tradeDate = $('#tradeDate').val();//交易日
             paramObj.unitCprice = $('#unitCprice').val();//交易价格
             paramObj.shareAmt = $('#shareAmt').val();//交易数量
             paramObj.tradeMarket = $('#tradeMarket').val();//交易场所
             paramObj.ps = $('#ps').val();//交易方向
             paramObj.fpriceAmt=JoyinMath.doMul($('#unitCprice').val(),$('#shareAmt').val(),2);
             paramObj.prinAmt=JoyinMath.doMul($('#unitCprice').val(),$('#shareAmt').val(),2);
             paramObj.secManageAcctId = $('#secManageAcctId').val();//证券账户
             var AllfeeAmtPay =0 ;
             Joyin.ajaxSync('/invest/trade/stockdeal/TrdStock002/getFeeDetail', paramObj, function (data) {
            	 if(!$("#commissionIsPayWithTrade").val()||flag){
            		 $("#commissionIsPayWithTrade").val(data.commission.isPayWithTrade)
            	 }
            	 if(!$("#stampDutyIsPayWithTrade").val()||flag){
            		 $("#stampDutyIsPayWithTrade").val(data.stampDuty.isPayWithTrade)
            	 }
            	 /*$("#commissionIsPayWithTrade").val(data.commission.isPayWithTrade)
            	 $("#stampDutyIsPayWithTrade").val(data.stampDuty.isPayWithTrade)*/
            	 if(!$("#commission").val()||flag){
            		 $("#commission").val(data.commission.feeAmt).prop("disabled", false);
            	 }
            	 if(!$("#stampDuty").val()||flag){
            		 $("#stampDuty").val(data.stampDuty.feeAmt).prop("disabled", false);
            	 }
            	 if(!$("#commissionIsPayWithTrade").val()||$("#commissionIsPayWithTrade").val()=='Y'){
            		 AllfeeAmtPay =JoyinMath.doAdd(AllfeeAmtPay, $("#commission").val(), 2);
            	 }
            	 if(!$("#stampDutyIsPayWithTrade").val()||$("#stampDutyIsPayWithTrade").val()=='Y'){
            		 AllfeeAmtPay =JoyinMath.doAdd(AllfeeAmtPay, $("#stampDuty").val(), 2);
            	 }
             });
        	 //卖出
         	if ($("#ps").val() == PS_T02) {
                 $("#tradeAmt").val(JoyinMath.doMinus($("#unitCprice").val()*$("#shareAmt").val(), AllfeeAmtPay, 2)).trigger('blur');
             }
             else {
            	//交易价格*交易数量+总费用
                 $("#tradeAmt").val(JoyinMath.doAdd($("#unitCprice").val()*$("#shareAmt").val(), AllfeeAmtPay, 2)).trigger('blur');
             }
            
    	 }else if($('#unitCprice').val()&&$('#shareAmt').val()
    			 &&$('#channelFee').val()){
    		 //通道用户
    		//交易价格*交易数量+总费用
             $("#tradeAmt").val(JoyinMath.doMinus($("#unitCprice").val()*$("#shareAmt").val(), $('#channelFee').val(), 2)).trigger('blur');
    	 }
    	 
     }
    
     
    /**
     * 资产名称查看
     * 若点击资产名称后面的【查看】按钮跳转到本资产的详情画面
     */
    $('#TrdStock002_btn_viewAsset').click(function(){
        if ($('#finprodId').val()) {
            Joyin.dialog(JoyinUtil.getLabel("FinStockInfo002.title"), basePath
                    + "invest/asset/stock/FinStockInfo002/init", "95%", "90%", {
                pageModel : PAGE_MODEL_REFERENCE,
                finprodId : $('#finprodId').val()
                
            });
        }
    })
    
    /**
     * 通道change事件
     * 
     *//*
    var chlIdChange = function(){
        if ($("#chlId").val()) {
            $("#secManageAcctId").val('');
            $("#secManageAcctId").prop("disabled", true);
            $("#channelFee").prop("disabled", false);
            $('#tradeMarketAccAvailableCash_div').hide();
            $('#chlIdAmt_div').show();
            $("#commission").val('').prop("disabled", true);
            $("#stampDuty").val('').prop("disabled", true);
            getProTongDaoCash();
        }else {
            $("#chlIdAmt").val('');//通道可用金额
            $("#secManageAcctId").prop("disabled", false);//证卷账户
            $("#channelFee").val('').prop("disabled", true);//通道费
            $("#commission").prop("disabled", false);//佣金
            $("#stampDuty").prop("disabled", false);//印花税
            $('#chlIdAmt_div').hide();//通道可用金额模块
            $('#tradeMarketAccAvailableCash_div').show();
        }
	}*/
        
        
    //修改组合账号
    var secManageAcctIdChange = function (){
        if ($("#secManageAcctId").val()) {
//            $("#chlId").val('').prop("disabled", true);
//            $("#channelFee").val('').prop("disabled", true);
            $("#commission").prop("disabled", false);//佣金
            $("#stampDuty").prop("disabled", false);//印花税
//            $("#chlIdAmt").val('');
//            $('#chlIdAmt_div').hide();
        }else {
//            $("#chlId").prop("disabled", false);
//            $("#channelFee").val('').prop("disabled", false);
//            $("#commission").prop("disabled", true);//佣金
//            $("#stampDuty").prop("disabled", true);//印花税
//            $('#chlIdAmt_div').show();
        }
    
    }
    
    // 一体化对应etliu end
    
   
    //画面初始化
    var init = function() {
        
    	/**
         * 委托带出界面 ,需要置灰的控件
         */
        //委托代码
     // <!-- 通道交易 新增 强成西 2019/03/26 start -->
        /*changeTradeAmtAndFee();*/
        
        // <!-- 通道交易 新增 强成西 2019/03/26 start -->
//        $('#Cash_div').hide();
//		 $('#chlIdAmt_div').hide();
         getPtlSecPositionInfo();
         getPtlCashPositionInfo();
		 $("#assetAvailablePosition_div").hide();
		 
		 
		 /**
	      * 画面联动处理
	      * 交易价格 交易数量 交易日 交易场所 交易方向
	      */
	     $('#unitCprice').on("change", function () {
	    	 changeTradeAmtAndFee(true);
	     });
	     $('#shareAmt').on("change", function () {
	    	 changeTradeAmtAndFee(true);
	     });
	     $('#tradeDate').on("change", function () {
	    	 changeTradeAmtAndFee(true);
	     });
	     $('#tradeMarket').on("change", function () {
	    	 changeTradeAmtAndFee(true);
	     });
	     $('#channelFee').on("change", function () {
	    	 changeTradeAmtAndFee(true);
	     });
	     $('#stampDuty').on("change", function () {
	    	 changeTradeAmtAndFee();
	     });
	     $('#commission').on("change", function () {
	    	 changeTradeAmtAndFee();
	     });
	     
	     $("#secManageAcctId").on("change", function () {
	    	 secManageAcctIdChange();
	    	 getPtlSecPositionInfo();
	    	 getPtlCashPositionInfo();
	     });
	     $("#chlId").on("change",function() {
        	chlIdChange();
 		 });
	     $('#tradeDate').on("change", function () {
	     	tradeDateChange();
	     });
	     $("#withLockPeriod").on("change",function(){
	    	 withLockPeriodChange();
	     });
	     $("#ps").on("change",function(){
	     	psChange();
	     });
	     $('#finprodId').on('change', function () {
	     	finprodIdChange();
	     });
	     $('#portfolioId').on('change',function () {
	     	portfolioIdChange();
	     });
	     
	     $("#dealMode").on("change",function(){
	     	dealModeChange();
	     });
	     
	     $('#board').on("change", function () {
	     	boardChange();
	     });
	     

    };
    
    window.componentLoadCallback = function(isDone, pagePath, params){
        if(isDone){
		var riskParam = getRiskData();
	            if (pageModel==PAGE_MODEL_NEW){
	                riskParam.data =null;
	            }
        window.BaseRiskMathComm.getparam(riskParam);
        	window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
            	//全价总额不能为空
            	if (!$("#tradeAmt").val() || typeof $("#tradeAmt").val() == 'undefined'){
            		Joyin.error(JoyinUtil.getMessage('FW_E_0006',[JoyinUtil.getLabel("TrdStock002.tradeAmt")]));
            		return null;
            	}
                var list;
        		list = new Array();
        		var dealObj = {};
        		dealObj.finprodType2 = $("#finprodType2").val();
                dealObj.settleDate = $("#settleDate").val();
                dealObj.tradeDate = $("#tradeDate").val();
                dealObj.ps = $("#ps").val();
                dealObj.ccy = $("#ccy").val();
                dealObj.tradeAmt = $("#tradeAmt").val();
                dealObj.portfolioId = $("#portfolioId").val();
                dealObj.finprodId = $("#finprodId").val();
                dealObj.counterId = $("#trdInvestPersonInfo_counterId").val();
                dealObj.tradeMarket = $("#tradeMarket").val();
                /*dealObj.tradePlat = $("#tradePlat").val();*/
                dealObj.secManageAcctId = $("#secManageAcctId").val();
                // 通道交易 新增 强成西 2019/03/26 start
                dealObj.chlId = $("#chlId").val();
                // 通道交易 新增 强成西 2019/03/26 end
                list.push(dealObj);
                
                return {
                    'isTotalTrade':YES_OR_NO_N
                    ,'bankLendingType':null
                    ,'portfolioId':$("#portfolioId").val()
                    ,'deal':list
                    ,'dealMode':$("#dealMode").val()
                };
            }, true);
        }
    };
    
    /**
     * 画面加载完成
     */
    $(function() {
        //初始化画面
        init(); 
        // 绑定按钮事件
        $('#TrdStock002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TrdStock002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
      //风险试算
        $('#TrdStock002_btn_risk').click(function(){
            //window.BaseRiskMathComm.initBaseRiskMathCommPage(getRiskData());
          //-----------add by jjpei 2020-01-07 风险新版本 start----------
          var object = $.serializeToObject('#submitForm', true);
          window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/1/RSK002/F11', object,getRiskData());
          //-----------add by jjpei 2020-01-07 风险新版本 end----------
          window.BaseRiskMathComm.initBaseRiskMathCommPageCus(getriskdata());

      })
        
        /**
         * 委托代码
         */
   
        if($("#entrustId").val()){
        	if($('#portfolioId').val()){
            $('#portfolioId').attr('disabled',true);
        	}
            if($('#finprodId').val()){
            $('#finprodId').attr('disabled',true);
            }
            if($('#tradeMarket').val()){
            $('#tradeMarket').attr('disabled',true);
            }
            if($('#ps').val()){
            $('#ps').attr('disabled',true).trigger('change');
            }
            if($('#board').val()){
            $('#board').attr('disabled',true);
            }
            if($('#dealMode').val()){
            $('#dealMode').attr('disabled',true).trigger('change');
            }
            $('#chlId').trigger('change');
            $('#secManageAcctId').trigger('change');
            if($('#chlId').val()){
            $('#chlId').attr('disabled',true);
            }
            if($('#secManageAcctId').val()){
            $('#secManageAcctId').attr('disabled',true);
            }
            if($('#tradeDate').val()){
            $('#tradeDate').attr('disabled',true);
            }
            if($('#settleDate').val()){
            $('#settleDate').attr('disabled',true);
        }
            changeTradeAmtAndFee(true);
        }
        
        /**
         * 流程中
         */
        if($("#workflowId").val()){
        	 $('#chlId').trigger('change');
        	 $('#secManageAcctId').trigger('change');
        	 $('#ps').trigger('change');
        }
        if($("#tradeId").val()){
        	if (ps == PS_T01 || ps == PS_T48 || ps == PS_T46 || ps == PS_T47 ) {
    		 	//交易方向为买入、配股、增发（配售）、新股中签时显示此字段
    			 $('#Cash_div').show();
    			 $("#assetAvailablePosition_div").hide();
    		 }else if(ps == PS_T02){
    			 //卖出
    			 $('#Cash_div').hide();
    			 $("#assetAvailablePosition_div").show();
    		}
        }
        
        $('#ps').on("change", function () {
	    	 changeTradeAmtAndFee(true);
	     });
        $("#secManageAcctId").on("change", function () {
	    	 changeTradeAmtAndFee(true);
	     });
	     $("#chlId").on("change",function() {
	    	 changeTradeAmtAndFee(true);
		 });

        // 银行间成交编号(当交易场所选择银行间时，银行间成交编号可编辑)
        $('#fTradeId').change(function(){
            fTradeIdChangeAction();
        });

        /**
         * 银行间成交编号输入时从cstp查询数据带出
         */
        var fTradeIdChangeAction = function(){
            var fTradeId = $('#fTradeId').val();
            if (fTradeId && fTradeId != ''){
                Joyin.ajax(basePath + 'invest/trade/stockdeal/TrdStock002/querySecCstpInfoByNo', {fedealno : fTradeId}, function(result){
                    if (result){
                        if (result.saleCode && result.saleCode != ''){
                            $('#saleCode').val(result.saleCode);
                            $('#saleCode').trigger('change')
                            if($('#saleCode').val()){
                                $('#saleCode').attr('disabled', true);
                            }
                        }else{
                            $('#saleCode').val('');
                            $('#saleCode').attr('disabled', false);
                        }

                        if (result.finprodId && result.finprodId != ''){
                            $('#finprodId').textSelect('setValue',result.finprodId);
                            $('#finprodId').trigger('change')
                            if($('#finprodId').val()){
                                $('#finprodId').attr('disabled', true);
                            }
                        }else{
                            $('#finprodName').val('');
                            deleteSelect('finprodId');
                            deleteSelect('tradeMarket');
                            deleteSelect('board');
                            $('#finprodId').attr('disabled', false);
                        }
                        if (result.dealMode && result.dealMode != ''){
                            $('#dealMode').textSelect('setValue',result.dealMode);
                            $('#dealMode').trigger('change');
                            if($('#dealMode').val()){
                                $('#dealMode').attr('disabled', true);
                            }
                        }else{
                            deleteSelect('dealMode');
                            $('#dealMode').attr('disabled', false);
                        }
                        if (result.ps && result.ps != ''){
                            $('#ps').val(result.ps).trigger('change').attr('disabled', true);// 交易方向
                            if($('#ps').val()){
                                $('#ps').attr('disabled', true);
                            }
                        }else{
                            deleteSelect('ps')
                            $('#ps').attr('disabled', false);
                        }
                        if (result.secManageAcctId && result.secManageAcctId != ''){
                            $('#secManageAcctId').textSelect('setValue',result.secManageAcctId);// 交易方向
                            $('#secManageAcctId').trigger('change');
                            if($('#secManageAcctId').val()){
                                $('#secManageAcctId').attr('disabled', true);
                            }
                        }else{
                            deleteSelect('secManageAcctId');
                            $('#secManageAcctId').attr('disabled', false);
                        }
                        if (result.ccy && result.ccy != ''){
                            $('#ccy').val(result.ccy).trigger('change');//币种
                            if($('#ccy').val()){
                                $('#ccy').attr('disabled', true);
                            }
                        }else{
                            deleteSelect('ccy');
                            $('#ccy').attr('disabled', false);
                        }
                        if (result.tradeDateStr && result.tradeDateStr != ''){
                            $('#tradeDate').val(result.tradeDateStr).trigger('change');// 交易日期
                            if($('#tradeDate').val()){
                                $('#tradeDate').attr('disabled', true);
                            }
                        }else{
                            $('#tradeDate').val('');
                            $('#tradeDate').attr('disabled', false);
                        }
                        if (result.settleDateStr && result.settleDateStr != ''){
                            $('#settleDate').val(result.settleDateStr).trigger('change');// 交易日期
                            if($('#settleDate').val()){
                                $('#settleDate').attr('disabled', true);
                            }
                        }else{
                            $('#settleDate').val('');
                            $('#settleDate').attr('disabled', false);
                        }
                        if (result.unitCprice && result.unitCprice != ''){
                            $('#unitCprice').val(result.unitCprice).trigger('blur').trigger('change');// 交易日期
                            if($('#unitCprice').val()){
                                $('#unitCprice').attr('disabled', true);
                            }
                        }else{
                            $('#unitCprice').val('');
                            $('#unitCprice').attr('disabled', false);
                        }
                        if (result.shareAmt && result.shareAmt != ''){
                            $('#shareAmt').val(result.shareAmt).trigger('blur').trigger('change');// 交易日期
                            if($('#shareAmt').val()){
                                $('#shareAmt').attr('disabled', true);
                            }
                        }else{
                            $('#shareAmt').val('');
                            $('#shareAmt').attr('disabled', false);
                        }
                        if (result.tradeAmt && result.tradeAmt != ''){
                            $('#tradeAmt').val(result.tradeAmt).trigger('blur').trigger('change');// 交易日期
                            if($('#tradeAmt').val()){
                                $('#tradeAmt').attr('disabled', true);
                            }
                        }else{
                            $('#tradeAmt').val('');
                            $('#tradeAmt').attr('disabled', false);
                        }
                        if (result.commission && result.commission != ''){
                            $('#commission').val(result.commission).trigger('blur').trigger('change');// 交易日期
                            if($('#commission').val()){
                                $('#commission').attr('disabled', true);
                            }
                        }else{
                            $('#commission').val('');
                            $('#commission').attr('disabled', false);
                        }
                        if (result.stampDuty && result.stampDuty != ''){
                            $('#stampDuty').val(result.stampDuty).trigger('blur').trigger('change');// 交易日期
                            if($('#stampDuty').val()){
                                $('#stampDuty').attr('disabled', true);
                            }
                        }else{
                            $('#stampDuty').val('');
                            $('#stampDuty').attr('disabled', false);
                        }
                    }
                })
            }else{
                if ( $('#saleCode').val()){
                    $('#saleCode').val('');
                    $('#saleCode').attr('disabled', false);
                }

                if ($('#finprodId').val()){
                    $('#finprodName').val('');
                    deleteSelect('finprodId');
                    deleteSelect('tradeMarket');
                    deleteSelect('board');
                    $('#finprodId').attr('disabled', false);
                }
                if ($('#dealMode').val()){
                    deleteSelect('dealMode');
                    $('#dealMode').attr('disabled', false);
                }
                if ($('#ps').val()){
                    deleteSelect('ps')
                    $('#ps').attr('disabled', false);
                }
                if ($('#secManageAcctId').val()){
                    deleteSelect('secManageAcctId');
                    $('#secManageAcctId').attr('disabled', false);
                }
                if ($('#ccy').val()){
                    deleteSelect('ccy');
                    $('#ccy').attr('disabled', false);
                }
                if ($('#tradeDate').val()){
                    $('#tradeDate').val('');
                    $('#tradeDate').attr('disabled', false);
                }
                if ($('#settleDate').val()){
                    $('#settleDate').val('');
                    $('#settleDate').attr('disabled', false);
                }
                if ($('#unitCprice').val()){
                    $('#unitCprice').val('');
                    $('#unitCprice').attr('disabled', false);
                }
                if ($('#shareAmt').val()){
                    $('#shareAmt').val('');
                    $('#shareAmt').attr('disabled', false);
                }
                if ($('#tradeAmt').val()){
                    $('#tradeAmt').val('');
                    $('#tradeAmt').attr('disabled', false);
                }
                if ($('#commission').val()){
                    $('#commission').val('');
                    $('#commission').attr('disabled', false);
                }
                if ($('#stampDuty').val()){
                    $('#stampDuty').val('');
                    $('#stampDuty').attr('disabled', false);
                }
            }
        };



        var deleteSelect = function (id) {
            $('#'+id).val('').trigger('change');
        }
    });
    /**
     * 隐藏资产可用持仓
     */
    var hideAssetAvailablePosition_div = function(){
    	getElementById('assetAvailablePosition_div').hide();
    }
    /**
     * 隐藏资产可用现金与通道可用现金
     */
    var hideAssetAvailablePosition_div = function(){
    	getElementById('tradeMarketAccAvailableCash_div').hide();
    	getElementById('chlIdAmt_div').hide();
    }
    
    /**
     * 当交易方向为买入/配股/增发（配售）时，显示组合可用现金、托管户剩余现金，交易所资金账户可用现金
     * proAvailableCash  proAvailableCash1 accAvailableCash  tradeMarketAccAvailableCash
     */
    function showCash(flag){
        if (flag){
            $('#assetAvailablePosition_div').hide();
            $('#proAvailableCash_div').show();
            $('#proAvailableCash1_div').show();
            $('#tradeMarketAccAvailableCash_div').show();
        }else {
            $('#assetAvailablePosition_div').show();
            $('#proAvailableCash_div').hide();
            $('#proAvailableCash1_div').hide();
            $('#tradeMarketAccAvailableCash_div').hide();
        }

    }
    
  //通道可用现金查询
//    function getProTongDaoCash(){
//        if ($('#chlId').val() == null || $('#chlId').val() == '' || $("#portfolioId").val() == null || $("#portfolioId").val() == ''){
//            return
//        }
//        var data = {portfolioId:$('#portfolioId').val(),TongDao:$("#chlId").val()}
//        Joyin.ajaxJson(basePath + 'invest/trade/stockdeal/TrdStock002/getTongDaoCash',data,function(result) {
//            $('#chlIdAmt').val(result).trigger('blur');
//        });
//    }
  //查询组合可用持仓
    function getPtlSecPositionInfo() {
        if ($("#portfolioId").val() == null || $("#portfolioId").val() == '' || $("#ccy").val() == null || $("#ccy").val() == ''
        	|| $('#finprodId').val() == null || $('#finprodId').val() == ''){
            return
        }
        var data = {portfolioId: $('#portfolioId').val(), ccy: $('#ccy').val(),finprodId:$('#finprodId').val(),secAcctId:$('#secManageAcctId').val()}
        Joyin.ajaxJson(basePath + 'invest/trade/stockdeal/TrdStock002/getAssetAvailablePosition', data,function (result) {
            $('#assetAvailablePosition').val(result).trigger('blur');
        });
    }
  //查询组合可用现金
    function getPtlCashPositionInfo() {
        if ($("#portfolioId").val() == null || $("#portfolioId").val() == '' ||
            $("#ccy").val() == null || $("#ccy").val() == '' || $('#tradeDate').val() == null || $('#tradeDate').val() == ''){
            return
        }
        var data = {
            portfolioId: $('#portfolioId').val(),
            ccy: $('#ccy').val(),
            vdate: $('#tradeDate').val(),
            secAcctId:$('#secManageAcctId').val()
        };
        Joyin.ajaxJson(basePath + 'invest/trade/stockdeal/TrdStock002/getProAvailableCash', data,function (result) {
            $('#proAvailableCash').val(result.cashT).trigger('blur');//组合T日可用现金
            /*$('#accAvailableCash').val(result.cashTG).trigger('blur');//托管户剩余现金*/
            $('#tradeMarketAccAvailableCash').val(result.cashS).trigger('blur');//资金账户可用现金
        });
    }
    
}());