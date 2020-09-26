//@ sourceUrl=TrdConBond002.js
var table;

(function () {
    var getriskdata = function () {
        var obj = {timePoint:MY_SUP_TIME_TYPE_02,valDate:$('#purchDate').val(),portfolioCode:$('#portfolioId').val(),tradeAmount:$('#tradeAmt').val(),assetType:FINPROD_TYPE_F01,isEnabled:'01',assetCode:$('#finprodId').val(),tradeDirection:$('#ps').val()}
        if($('#windtypeone').val() && $('#windtypeone').val() != ''){
            obj.assetSecType = $('#windtypeone').val();
        }
        var object = {valDate:['settleDate','TrdPlanRateInvestTrade002.settleDate'],portfolioCode:['portfolioId','TrdConBond002.portfolioId'],tradeAmount:['fpriceAmt','TrdConBond002.fpriceAmt'],assetType:['windtypeone','TrdConBond002.windtypeone'],tradeDirection:['ps','TrdLendInvestTrade002.ps'],data:obj}
        return object;
    }
    function saveOrSubmit(operateType) {
    	// 划款指令隐藏时，自动生成划款指令信息
    	generateOrders(function () {
	        var s;
	        if(BaseRiskMathComm) {
	            s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
	            if(!s && s != ''){
	                return;
	            }
	            if (window.BaseRiskMathComm.isCalcConditionChanged(getriskdata())){
	                return;
	            }
	        }
	
	        // 检查计算元素是否发生变化
	        if (checkCalcParam()) {
	            //---update by jjpei 20190731---信息显示有误
	            Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0013",
	                ["计算要素", "点击计算按钮"]));
	            //---update by jjpei 20190731---信息显示有误
	            return;
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
	
	        Joyin.ajaxJson(basePath + 'invest/trade/conbond/TrdConBond002/check', object, function(result) {
		        Joyin.confirm(result.data, function() {
		            if(operateType === 'save') {
		                WorkFlow.doSaveList(basePath + 'invest/trade/conbond/TrdConBond002/save', object, "TrdConBond002_btn_save");
		            }
		            else {
		                WorkFlow.doSubmitList(basePath + 'invest/trade/conbond/TrdConBond002/save', object, "TrdConBond002_btn_submit");
		            }
		        });
		    });
    	});
    };

    function checkCalcParam() {
        if (typeof saveCalcParam != 'undefined') {
            var currentCalcParam = getCalcParam();
            for ( var key in currentCalcParam) {
                //---update by jjpei 20190731---排除riskMathFlag----
                if (key == 'selectValue'||key=='riskMathFlag' || key=='remark') {
                    continue;
                }
                //---update by jjpei 20190731---排除riskMathFlag----
                var currentDealObj = currentCalcParam[key];
                var backupDealObj = saveCalcParam[key];

                if (typeof currentDealObj != "object"
                    && currentDealObj != backupDealObj) {
                    return true;
                }
            }
        }
    }

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
    $('#TrdConBond002_btn_viewAsset').click(function(){
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
        if (!isInit && pageModel !=PAGE_MODEL_REFERENCE&&pageModel !=PAGE_MODEL_EDIT&&(!$("#entrustId").val()&&!$('#secManageAcctId').attr("initvalue"))) {
            $("#secManageAcctId").val('').trigger("change");
        }
    });
    /**
     * 证券托管户变化更新资产可用持仓
     */
    $("#secManageAcctId").on("change", function () {
        if ($("#secManageAcctId").val()) {
            $("#chlId").val('').prop("disabled", true);
        }
        else {
            $("#chlId").prop("disabled", false);
        }
        updateCash();
        
        // 将甲类户对应的交易手续费、结算费、佣金设置为不可编辑
//        controllFeeItem();
    });

    $("#chlId").on("change", function () {
        if ($("#chlId").val()) {
            $("#secManageAcctId").val('').prop("disabled", true);
        }
        else {
            $("#secManageAcctId").prop("disabled", false);
        }
        updateCash();
    });




    //画面初始化
    var init = function() {
        /**
         * 若从委托场合点击交易录单
         */
        var entrustId = $("#entrustId").val();
        if(entrustId != ''){
        	$("#finprodId").attr("disabled", true);
        	$("#portfolioId").attr("disabled", true);
        	$("#purchModle").attr("disabled", true);
        	$("#purchType").attr("disabled", true);
        	$("#invAim").attr("disabled", true);
        	if($("#secManageAcctId").val()){
        		
        	}
        	if($("#chlId").val()){
        		
        	}
        	$("#secManageAcctId").attr("disabled", true);
        	$("#chlId").attr("disabled", true);
         }
        
    	purchModelAndPurchTypeChange()
    };
    
    window.componentLoadCallback = function(isDone, pagePath, params){
        if(isDone){
            var riskparam = getriskdata();
            if (pageModel==PAGE_MODEL_NEW){
                riskparam.data =null;
            }
            window.BaseRiskMathComm.getparam(riskparam);
            window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                var selectData = $('#finprodId').select2('data')[0].object;
                if (!selectData) {
                    Joyin.error("请选择资产代码。")
                    return;
                }
                var data = {};
                data.counterId = $('#trdInvestPersonInfo_counterId').val();
                //优先配售  使用申购券面总额
                //普通配售  有保证金 使用保证金
                if ($("#purchType").val()==PURCH_TYPE_01) {
                    data.tradeAmt = $("#tradeAmt").val();
                }
                else if($("#depositAmt").val()){
                    data.tradeAmt = $("#depositAmt").val();
                }
                if(!data.tradeAmt){
                	Joyin.error("请填写申购券面总额或保证金!");
                	return ;
                }
                //交易场所为申购方式
                data.tradeMarket = $("#purchModle").val();
                data.portfolioId = $('#portfolioId').val();
                data.ps = $('#ps').val();
                data.finprodId = selectData.finprodId;
                data.ccy = $('#ccy').val();
                data.tradeDate = $('#purchDate').val();
                data.finprodType2 = $('#finprodType2').val();
                data.profitType = $('#profitType').val();
                // <!-- 通道交易 新增 强成西 2019/03/26 start -->
                data.secManageAcctId = $("#secManageAcctId").val();
                data.chlId = $('#chlId').val();
                // <!-- 通道交易 新增 强成西 2019/03/26 end -->
                return {
                    'isTotalTrade':YES_OR_NO_N
                    ,'bankLendingType':null
                    ,'portfolioId':$("#portfolioId").val()
                    ,'deal':data
                    ,'dealMode':$("#purchType").val()//交易方式为申购方式
                };
            });
//            window.BussTrdTransferOrder && window.BussTrdTransferOrder['bussTrdTransferOrder'] && window.BussTrdTransferOrder['bussTrdTransferOrder'].saveCalcCondition();
            window.BussTrdAvailableCashQuery && window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'] && window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'].showCash();
           
            if($("#purchModle").val()==PURCH_MODLE_01&&$("#purchType").val()==PURCH_TYPE_02){
                $("#bussTrdTransferOrder_div").hide();
            }else{
                // 系统参数GEN_PAY_ORDERS_AUTO不等于隐藏，才能显示 lizhi 2020-2-27
                if(GEN_PAY_ORDERS_AUTO != '01'){
                    $("#bussTrdTransferOrder_div").show();
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
     * 保证金联动，清算指令
     */
    var purchModelAndPurchTypeChange = function(){
    	if(pageModel!= PAGE_MODEL_REFERENCE){
    		//4)	申购方式为网下申购且申购类型为普通申购时，保证金金额默认为500000，可修改；其他方式下保证金金额为0，不可修改；
        	//保证金默认
        	if($("#purchModle").val()==PURCH_MODLE_02&&$("#purchType").val()==PURCH_TYPE_02){
        		$("#depositAmt").val(500000).attr("disabled", false);
        	}else{
        		$("#depositAmt").val(0).attr("disabled", true);
        	}
        	//2)	申购类型为优先配售时，确认日期、缴款日期默认等于申购日期，不可修改；
        	//3)	申购类型为普通申购时，确认日期默认是申购日期T+2日，可修改；网上申购时缴款日期默认为T+2日，网下申购时缴款日期默认为为T+2日，可以修改；
        	if($("#purchType").val()){
        		if($("#purchType").val()==PURCH_TYPE_01){
            		$("#tradeDate").val($("#purchDate").val()).attr("disabled", true);
            		$("#settleDate").val($("#purchDate").val()).attr("disabled", true);
            	}else{
            		$('#tradeDate').val(DateUtil.parse($("#purchDate").val()).DateAdd('d', 2).Format('yyyy-MM-dd')).attr("disabled", false);
            		$('#settleDate').val(DateUtil.parse($("#purchDate").val()).DateAdd('d', 2).Format('yyyy-MM-dd')).attr("disabled", false);
            	}
        	}
    	}
    	//清算指令是否生成
    	//网上 普通 不生成
    	if($("#purchModle").val()==PURCH_MODLE_01&&$("#purchType").val()==PURCH_TYPE_02){
    		$("#bussTrdTransferOrder_div").hide();
    	}else{
    		// 系统参数GEN_PAY_ORDERS_AUTO不等于隐藏，才能显示 lizhi 2020-2-27
        	if(GEN_PAY_ORDERS_AUTO != '01'){
        		$("#bussTrdTransferOrder_div").show();
        	}
    	}
    }
    //add by lizhi 2020-2-26 划款指令是否自动生成  start
    var generateOrders = function (callback) {
    	// 如果还款指令设置为隐藏，且页面参数需要自动生成指令（页面存在不生成指令情况）
    	if(GEN_PAY_ORDERS_AUTO =='01'){
    		if(!($("#purchModle").val()==PURCH_MODLE_01 && $("#purchType").val()==PURCH_TYPE_02)){
    			$('#bussTrdTransferOrder_btn_generate').click();
    		}
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
        //初始化画面
        init();
        // 是否显示划款指令
        if(GEN_PAY_ORDERS_AUTO=='01'){
        	$('#bussTrdTransferOrder_div').hide() ;
        }else{
        	$('#bussTrdTransferOrder_div').show() ;
        }
        // 绑定按钮事件
        $('#TrdConBond002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TrdConBond002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
//        $('#TrdConBond002_btn_risk').click(function() {
//            Joyin.dialog(JoyinUtil.getLabel("BaseRiskMathComm"),
//                    basePath + "business/common/BaseRiskMathComm/init",
//                    "65%", "80%",
//                    { pageModel: PAGE_MODEL_REFERENCE });
//        });
      //风险试算
        $('#TrdConBond002_btn_risk').click(function(){
            //window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
            //-----------add by jjpei 2020-01-07 风险新版本 start----------
            var object = $.serializeToObject('#submitForm', true);
             window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/1/RSK002/F01', object,getriskdata());
            //-----------add by jjpei 2020-01-07 风险新版本 end----------
             window.BaseRiskMathComm.initBaseRiskMathCommPageCus(getriskdata());

        })
        if($("#finprodId").val()){
        	$("#finprodId").trigger("change");
        }
        if($("#secManageAcctId").val()){
        	$("#chlId").attr("disabled", true);
        }
    	if($("#chlId").val()){
    		$("#secManageAcctId").attr("disabled", true);
        }
    });


    
	$('#purchModle').on('change',function(){
		purchModelAndPurchTypeChange();
	});
	
	$('#purchType').on('change',function(){
		purchModelAndPurchTypeChange();
	});
	

    
}());
