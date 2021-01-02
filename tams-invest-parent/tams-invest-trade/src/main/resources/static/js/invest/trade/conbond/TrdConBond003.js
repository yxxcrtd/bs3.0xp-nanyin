var table;

(function () {
    var getriskdata = function () {
        var obj = {timePoint:MY_SUP_TIME_TYPE_02,valDate:$('#settleDate').val(),portfolioCode:$('#portfolioId').val(),tradeAmount:$('#winlotteryAmt').val(),assetType:FINPROD_TYPE_F01,isEnabled:'01',assetCode:$('#finprodId').val(),tradeDirection:$('#ps').val()}
        if($('#windtypeone').val() && $('#windtypeone').val() != ''){
            obj.assetSecType = $('#windtypeone').val();
        }
        var object = {valDate:['settleDate','TrdPlanRateInvestTrade002.settleDate'],portfolioCode:['portfolioId','TrdConBond003.portfolioId'],tradeAmount:['winlotteryAmt','TrdConBond001.winlotteryAmt'],assetType:['windtypeone','TrdConBond003.windtypeone'],tradeDirection:['ps','TrdLendInvestTrade002.ps'],data:obj}
        return object;
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
        // 检查生成划款指令参数是否发生变化
        if (window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()) {
            return;
        }

        var object = $.serializeToObject('#submitForm', true);
        object.pageModel = pageModel;
        object.operateType = operateType;
        if(FileCommon) {
            object.uploadFileList = FileCommon.getUploadFileInfo();
        }
        object.assetPosition = $("#bussTrdAvailableCashQuery_assetPosition").val(); // 资产可用持仓
        var data = $("#chlId").find("option:selected").data("data");
        if (data && data.object) {
            object.payType = data.object.payType;
            object.collectType = data.object.collectType;
        }
        if ($('#trdInvestPersonInfo_counterId').select2('data') && $('#trdInvestPersonInfo_counterId').select2('data')[0]) {
            object.counterType = $('#trdInvestPersonInfo_counterId').select2('data')[0].object;;
        }

        Joyin.ajaxJson(basePath + 'invest/trade/conbond/TrdConBond003/check', object, function(result) {
	        Joyin.confirm(result.data, function() {
	            /*if(operateType === 'save') {
	                WorkFlow.doSaveList(basePath + 'invest/trade/conbond/TrdConBond003/save', object, "TrdConBond003_btn_save");
	            }
	            else {
	                WorkFlow.doSubmitList(basePath + 'invest/trade/conbond/TrdConBond003/save', object, "TrdConBond003_btn_submit");
	            }
	            */
	            if(operateType === 'save') {
                    if(object.ps == PS_T58){
                        WorkFlow.doSaveList(basePath + 'invest/trade/conbond/TrdConBond003/save', object, "TrdConBond003_btn_total","TrdConBond003");
                    }else{
                        WorkFlow.doSaveList(basePath + 'invest/trade/conbond/TrdConBond003/save', object, "TrdConBond003_btn_save","TrdConBond003");
                    }
                }
                else {
                    if(object.ps == PS_T58){
                        WorkFlow.doSubmitList(basePath + 'invest/trade/conbond/TrdConBond003/save', object, "TrdConBond003_btn_total","TrdConBond003");
                    }else{
                        WorkFlow.doSubmitList(basePath + 'invest/trade/conbond/TrdConBond003/save', object, "TrdConBond003_btn_submit","TrdConBond003");
                    }
                }
	        });
	    });
  

    };


    /**
     * 1.5资产代码选择时
     * 资产名称文本框根据资产代码选择自动显示对应名称;
     * 同时带出资产相关信息，包括起息日，到期日，发行日，交易场所，币种，交易日历，计息基础等;
     */
    $('#finprodId').on('change', function () {
        var finprodId = $('#finprodId').val();
        var data = $('#finprodId').select2('data')[0].object;
        if($('#finprodId').select2('data')[0].id){
        	if (data && data.finprodId) {// 资产名称文本框根据资产代码选择自动显示对应名称;
            $("#finprodName").val(data.finprodAbbr).trigger('change');
            // 同时带出资产相关信息，包括起息日，到期日，发行日，交易场所，币种，交易日历，计息基础等;
            $("#vdate").val(data.vdate).trigger('change');
            $("#mdate").val(data.mdate).trigger('change');
            $("#idate").val(data.idate).trigger('change');
            $("#type6").val(data.type6).trigger('change');
            if (data.tradeMarket) {
            	$("#tradeMarket").val(data.tradeMarket);
            	if(pageModel!= PAGE_MODEL_REFERENCE&&!$('#secManageAcctId').attr("initvalue")){
            		$("#tradeMarket").trigger('change');
            	}
            }
            $("#ccy").val(data.ccy).trigger('change');
            $("#calendarId").val(data.calendarId).trigger('change');
            $("#basis").val(data.basis).trigger('change');
            
            // 新增资产二、三、四级分类  王贵奇  2019/7/30 开始
            $("#type9").val(data.type9).trigger('change');
            $("#type10").val(data.type10).trigger('change');
            $("#type11").val(data.type11).trigger('change');
            // 新增资产二、三、四级分类  王贵奇  2019/7/30 结束
//            if(data.windtypeone && !$("#windtypeone").val()) {
//                $("#windtypeone").val(data.windtypeone).trigger('change');
//            }
                $("#windtypeone").val(data.windtypeone).trigger('change');
                      
            /*// 是否含权债
            $("#isRight").val(data.isRight).trigger('change');*/
            // 控制显示行权收益率
            } else {
	        	//自动加券 yjm
	        	var autoCoupon = AutoCoupon.autoBondTradeCoupon($('#finprodId').val());
	            // 资产名称文本框根据资产代码选择自动显示对应名称;
	            $("#finprodName").val(autoCoupon.finprodAbbr).trigger('change');
	            // 同时带出资产相关信息，包括起息日，到期日，发行日，交易场所，币种，交易日历，计息基础等;
	            $("#vdate").val(autoCoupon.vdate).trigger('change');
	            $("#mdate").val(autoCoupon.mdate).trigger('change');
	            $("#idate").val(autoCoupon.idate).trigger('change');
	            if (autoCoupon.tradeMarket) {
	                $("#tradeMarket").val(autoCoupon.tradeMarket).trigger('change');
	            }
	            $("#ccy").val(autoCoupon.ccy).trigger('change');
	            $("#calendarId").val(autoCoupon.calendarId).trigger('change');
	            $("#basis").val(autoCoupon.basis).trigger('change');
	            $("#windtypeone").val(autoCoupon.windtypeone).trigger('change');
	            $("#finProd_id1").val(autoCoupon.finprodId).trigger('change');
	            $("#windtypeone").val(autoCoupon.type4).trigger('change');
	           // $('#finprodId').textSelect('setValue', autoCoupon.finprodId);
			}
        	 updateCash();
             
             /*// 是否含权债
             $("#isRight").val(data.isRight).trigger('change');*/
             // 控制显示行权收益率
        } else {
            // 资产名称文本框根据资产代码选择自动显示对应名称;
            $("#finprodName").val("").trigger('change');
            // 同时带出资产相关信息，包括起息日，到期日，发行日，交易场所，币种，交易日历，计息基础等;
            $("#vdate").val("").trigger('change');
            $("#mdate").val("").trigger('change');
            $("#idate").val("").trigger('change');
            $("#ccy").val("").trigger('change');
            $("#calendarId").val("").trigger('change');
            $("#basis").val("").trigger('change');
            
            // add by etliu 
            $("#windtypeone").val("").trigger('change');
        }
    });
    /**
     * 资产名称查看
     * 若点击资产名称后面的【查看】按钮跳转到本资产的详情画面
     */
    $('#TrdConBond003_btn_viewAsset').click(function(){
        if ($('#finprodId').val()) {
            Joyin.dialog(JoyinUtil.getLabel("FinBondTargetAsset002.title"), basePath
                    + "invest/asset/bond/FinBondTargetAsset002/init", "95%", "90%", {
                pageModel : PAGE_MODEL_REFERENCE,
                finprodId : $('#finprodId').val()
            });
        }
    })
    $("#portfolioId").on("change", function (e, isInit) {
        updateCash();
        // 债券投资 改变组合 需联动 证券账户 ,证券托管户应清空选择项
        if (!isInit && pageModel !=PAGE_MODEL_REFERENCE&&pageModel !=PAGE_MODEL_EDIT) {
            $("#secManageAcctId").val('').trigger("change");
        }
    });
    /**
     * 证券托管户变化更新资产可用持仓
     */
    $("#secManageAcctId").on("change", function () {
        /*if ($("#secManageAcctId").val()) {
            $("#chlId").val('').prop("disabled", true);
        }
        else {
            $("#chlId").prop("disabled", false);
        }*/
        updateCash();
        
        // 将甲类户对应的交易手续费、结算费、佣金设置为不可编辑
//        controllFeeItem();
    });

    $("#chlId").on("change", function () {
        /*if ($("#chlId").val()) {
            $("#secManageAcctId").val('').prop("disabled", true);
        }
        else {
            $("#secManageAcctId").prop("disabled", false);
        }*/
        updateCash();
    });

    /**
     * 中签券面联动
     */
    $("#winlotteryAmt").on("change", function () {
    	var winlotteryAmt= 0;
    	var depositAmt= 0;
    	if($("#winlotteryAmt").val()){
    		winlotteryAmt =Number($("#winlotteryAmt").val());
    	}
    	if($("#depositAmt").val()){
    		depositAmt =Number($("#depositAmt").val());
    	}
    	//中签金额大于保证金额
        if ($("#winlotteryAmt").val()&&winlotteryAmt>=depositAmt) {
            $("#refundAmt").val('').prop("disabled", true);
            $("#refundDate").val('').prop("disabled", true).trigger('change');
        	$("#supplementaryAmt").val(JoyinMath.doMinus($("#winlotteryAmt").val(),$("#depositAmt").val())).prop("disabled", false);
        }
        //中签金额小于保证金额
        else  if ($("#winlotteryAmt").val()&&winlotteryAmt<depositAmt) {
            $("#supplementaryAmt").val('').prop("disabled", true);
            $("#refundDate").prop("disabled", false);
            $("#settleDate").trigger('change');
        	$("#refundAmt").val(JoyinMath.doMinus($("#depositAmt").val(),$("#winlotteryAmt").val())).prop("disabled", false);
        }
        else {
        	$("#refundAmt").val('').prop("disabled", false);
            $("#refundDate").val('').prop("disabled", false).trigger('change');
            $("#supplementaryAmt").val('').prop("disabled", false);
        }
    });
    
    /**
     * 退款日期  默认为缴款日期
     */
    $("#settleDate").on("change", function () {
    	//中签金额大于保证金额
        if ($("#settleDate").val()) {
            $("#refundDate").val($("#settleDate").val());
        }
    });



    //画面初始化
    var init = function() {
        /**
         * 若从委托场合点击交易录单
         */
        var entrustId = $("#entrustId").val();
        if(pageModel!= PAGE_MODEL_REFERENCE){
        	$("#winlotteryAmt").trigger('change');
        }
        /**
         * 预计到账日
         */
        $('#actTradeDate').change(function(){
           var expectedArrivalDate = $('#actTradeDate').val();
           if(expectedArrivalDate){
               window.BussTrdTransferOrder['bussTrdTransferOrder'].generateClick();
           }
        });

    };
    
    window.componentLoadCallback = function(isDone, pagePath, params){
        if(isDone){
            if(typeof(BaseRiskMathComm) != 'undefined') {
            var riskparam = getriskdata();
            if (pageModel==PAGE_MODEL_NEW){
                riskparam.data =null;
            }
            window.BaseRiskMathComm.getparam(riskparam);
            }
            window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                var selectData = $('#finprodId').select2('data')[0].object;
                if (!selectData) {
                    Joyin.error("请选择资产代码。")
                    return;
                }
                var data = {};
                data.counterId = $('#trdInvestPersonInfo_counterId').val();
                var ieFlag = null;
                //取交易金额
                if($("#supplementaryAmt").val()){
                	data.tradeAmt=$("#supplementaryAmt").val();
                	ieFlag= IE_FLAG_02;//补缴款收支方向为支出
                }else if($("#refundAmt").val()){
                	data.tradeAmt=$("#refundAmt").val();
                	ieFlag=IE_FLAG_01;//退款 收支方向为收入
                }else{
                	Joyin.error("补缴款金额或者退款金额不能为空！")
                	return;
                }
                //交易场所为申购方式
                data.tradeMarket = $("#purchModle").val();
                data.portfolioId = $('#portfolioId').val();
                data.ps = $('#ps').val();
                data.finprodId = selectData.finprodId;
                data.ccy = $('#ccy').val();
                data.tradeDate = $('#settleDate').val();
                if($('#ps').val()==PS_T58){
                	data.tradeDate=$("#actTradeDate").val();
                }
                data.finprodType2 = $('#finprodType2').val();
                data.profitType = $('#profitType').val();
                data.secManageAcctId = $("#secManageAcctId").val();
                // <!-- 通道交易 新增 强成西 2019/03/26 start -->
                data.chlId = $('#chlId').val();
                // <!-- 通道交易 新增 强成西 2019/03/26 end -->
                return {
                    'isTotalTrade':YES_OR_NO_N
                    ,'bankLendingType':null
                    ,'portfolioId':$("#portfolioId").val()
                    ,'deal':data
                    ,'dealMode':$("#purchType").val()//交易方式为申购方式
                    ,'ieFlag':ieFlag
                };
            });
            window.BussTrdAvailableCashQuery && window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'] && window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'].showCash();
            $("#actTradeDate_div").hide();
            if($("#ps").val()&&$("#ps").val()==PS_T58){
            	$("#settleDate").attr('disabled',true);
            	$("#winlotteryAmt").attr('disabled',true);
            	$("#refundAmt").attr('disabled',true);
            	$("#refundDate").attr('disabled',true);
            	$("#trdInvestPersonInfo_counterId").attr('disabled',true);
            	$("#trdInvestPersonInfo_counterTrader").attr('disabled',true);
            	$("#trdInvestPersonInfo_contactInfo").attr('disabled',true);
            	$("#bussTrdTransferOrder_btn_generate").addClass('layui-btn-disabled');
            	$("#actTradeDate_div").show();
            }
            
            if($("#ps").val()&&$("#ps").val()==PS_T58 &&single==1){
            	$("#actTradeDate_div").show();
            	 $('#actTradeDate').attr('disabled',false);
                 $('#actTradeDate').attr('data-ignore',true);
                 $('#TrdConBond003_btn_save').attr('data-ignore',true).removeClass("layui-btn-disabled");;
                 $('#TrdConBond003_btn_submit').attr('data-ignore',true).removeClass("layui-btn-disabled");;
                 $('#TrdConBond003_btn_risk').attr('data-ignore',true).removeClass("layui-btn-disabled");;
//                 $('#actTradeDate').trigger('change')
                 if($("#actTradeDate").val()!=$("#refundDate").val()){
                	 window.BussTrdTransferOrder['bussTrdTransferOrder'].generateClick();
                 }
            }
            
            
            
        }
    };
    


    var updateCash = function(){
	   	 if ($("#portfolioId").val() == null || $("#portfolioId").val() == '' ||
		            $("#ccy").val() == null || $("#ccy").val() == '' || $('#purchDate').val() == null || $('#purchDate').val() == ''){
		            return
		 }
		 var data = {
		     portfolioId: $('#portfolioId').val(),
		     ccy: $('#ccy').val(),
		     vdate: $('#purchDate').val(),
		     secAcctId:$('#secManageAcctId').val(),
		     chlId:$("#chlId").val()
		 };
		 Joyin.ajaxJson(basePath + 'invest/trade/conbond/TrdConBond002/getProAvailableCash', data,function (result) {
		     $('#proAvailableCash').val(result.cashT).trigger('blur');//组合T日可用现金
		     $('#channelAvailableCash').val(result.cashC).trigger('blur');//通道可用现金
		     $('#tradeMarketCash').val(result.cashS).trigger('blur');//资金账户可用现金
		 });
		        
	};

    /**
     * 画面加载完成
     */
    $(function() {
        //初始化画面
        init();

        // 绑定按钮事件
        $('#TrdConBond003_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TrdConBond003_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
//        $('#TrdConBond003_btn_risk').click(function() {
//            Joyin.dialog(JoyinUtil.getLabel("BaseRiskMathComm"),
//                    basePath + "business/common/BaseRiskMathComm/init",
//                    "65%", "80%",
//                    { pageModel: PAGE_MODEL_REFERENCE });
//        });
      //风险试算
        $('#TrdConBond003_btn_risk').click(function(){
            // window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
             //-----------add by jjpei 2020-01-07 风险新版本 start----------
             var object = $.serializeToObject('#submitForm', true);
             window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/1/RSK002/F01', object,getriskdata());
             //-----------add by jjpei 2020-01-07 风险新版本 end----------
             window.BaseRiskMathComm.initBaseRiskMathCommPageCus(getriskdata());

         })
        if($("#finprodId").val()){
        	$("#finprodId").trigger("change");
        }
    });
    
    
}());
