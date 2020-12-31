/**
 * 存款委托新建
 */
(function() {

	var getriskdata = function () {
        var obj = {tradeDirection:$('#ps').val(),timePoint:MY_SUP_TIME_TYPE_02,valDate:sysDate,portfolioCode:$('#portfolioId').val(),tradeAmount:$('#entrustAmt').val(),assetType:fintype,isEnabled:'01',assetCode:$('#finprodIdhid').val()}
        var object = {assetCode: ['finprodMarketId', 'EtrBrowDepoInfo001.finprodId'],tradeDirection:['ps','EtrBrowDepoInfo001.ps'],portfolioCode:['portfolioId','EtrBrowDepoInfo001.portfolioId'],tradeAmount:['entrustAmt','EtrBrowDepoInfo001.entrustAmt'],assetType:['finprodType2','EtrBrowDepoInfo001.finprodType2'],data:obj}
        return object;
    }
    /**
     * 保存或提交
     * 
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
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
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#searchForm', true);
        
        // 指令授信 start
        object.inputValue1 = 'F05';
        object.counterId = object.bussEtrInvestPersonInfoDto.counterId;
        object.tradeAmt = object.entrustAmt;
        object.prinAmt = object.entrustAmt;
        object.tradeDate = $('#vdate').val();
        // 指令授信 end
        
        if ($('#select2-finprodMarketId-container').attr('title')){
            object.finprodName = $('#select2-finprodMarketId-container').attr('title');
        }
        if(!object.bussEtrInvestPersonInfoDto){
			object.bussEtrInvestPersonInfoDto={};
		}
        object.bussEtrInvestPersonInfoDto.invManagerId = $('#bussEtrInvestPersonInfoDto_invManagerId').val();
        if(object.bussEtrInvestPersonInfoDto.trader != null && object.bussEtrInvestPersonInfoDto.trader != ''){
        	object.bussEtrInvestPersonInfoDto.trader = object.bussEtrInvestPersonInfoDto.trader.join(",");
        }
        if(object.finprodId != null && object.finprodId != ''){
        	object.finprodId = $('#finprodMarketId').select2('data')[0].object.finprodId;
        	object.price = $('#finprodMarketId').select2('data')[0].object.price/100;
        }
		if ($('#bussEtrInvestPersonInfoDto_counterId').select2('data') && $('#bussEtrInvestPersonInfoDto_counterId').select2('data')[0]) {
			object.counterType = $('#bussEtrInvestPersonInfoDto_counterId').select2('data')[0].object;;
		}
        if(FileCommon) {
    		object.fileArray = FileCommon.getUploadFileInfo();
    		if(object.fileArray && _.isArray(object.fileArray)){
    			$.each(object.fileArray, function(index,obj){
    				obj.seqNo = obj.fileNo;
    			});
    		}
    	}
        object.entrustId = $("#entrustId").val();
        object.operateType = operateType;
        object.pageModel = pageModel;
        object.proAvailableCash = $('#proAvailableCash').val();
//        if(object.ps == T01){
//        	if($('#proAvailableCash').val() != null && $('#proAvailableCash').val() != '' && $('#entrustAmt').val() != null && $('#entrustAmt').val() != ''){
//        		if(parseInt($('#entrustAmt').val()) > parseInt($('#proAvailableCash').val())){
//        			Joyin.error("委托金额必须小于等于组合T日可用现金");
//        			return;
//        		}
//        	}
//        }
        if(object.ps == T02){
        	object.assetAvailablePosition = $('#assetAvailablePosition').val();
        }

        if ($("#getChannelId").val() !=null && $("#getChannelId").val() !='') {
			object.chlId = $('#getChannelId').val();
		}
        Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/check', object, function(result1) {
	        Joyin.ajaxJson(basePath + 'invest/entrust/etrbrowdepoinfo/EtrBrowDepoInfo002/checkAuthorization', object, function(result2) {
		        Joyin.ajaxJson(basePath + 'invest/entrust/etrbrowdepoinfo/EtrBrowDepoInfo002/check',object,
		          function(result) {
		              Joyin.confirm(result.data,function() {
		                   if (operateType === 'save') {
		                       WorkFlow.doSaveList(basePath+'invest/entrust/etrbrowdepoinfo/EtrBrowDepoInfo002/save',object,"EtrBrowDepoInfo002_btn_save");
		                   } else {
		                       WorkFlow.doSubmitList(basePath+'invest/entrust/etrbrowdepoinfo/EtrBrowDepoInfo002/save',object,"EtrBrowDepoInfo002_btn_submit");
		                   }
		               });
		           });
	        });
        });
    }

    //画面联动,通道带出通道可用现金
    $("#getChannelId").change(function(){
		getProAvailableCash();
        getAssetAvailablePosition();
        if ($("#getChannelId").val()) {
			$("#chlId_value_div").show();
		}
        else {
			$("#chlId_value_div").hide();
		}
	});

	function getChlCash() {
		if ($("#getChannelId").val() && $("#portfolioId").val() && $('#ccy').val()) {
			var data = {};
			var portfolioId = $("#portfolioId").val();
			var chlFinprodId = $("#getChannelId").val();
			data.portfolioId = portfolioId;
			data.chlFinprodId = chlFinprodId;
			data.ccy = $('#ccy').val();
			Joyin.ajaxJson(basePath + 'invest/entrust/etrbrowdepoinfo/EtrBrowDepoInfo002/getChannelAvailableCash', data,
				function (result) {
					$('#getChannelIdValue').val(result);
					$("#chlId_value_div").show();
				});
		}
	}


	//画面联动，资产代码带出资产名称
    $("#finprodMarketId").change(function(){
    	var finprodId = $('#finprodMarketId').val();
        var data = $('#finprodMarketId').select2('data')[0].object;
    	if($("#finprodMarketId").val() != null && $("#finprodMarketId").val() != ''){
			$("#finprodName").val($('#finprodMarketId').select2('data')[0].id);
        	$("#dateStart").val(Date.ofDate($('#finprodMarketId').select2('data')[0].object.dateStart).format());
        	$("#dateEnd").val(Date.ofDate($('#finprodMarketId').select2('data')[0].object.dateEnd).format());
        	$("#termDays").val($('#finprodMarketId').select2('data')[0].object.termDays);
        	$("#price").val($('#finprodMarketId').select2('data')[0].object.price).trigger('blur');
        	$("#finprodType2").val($('#finprodMarketId').select2('data')[0].object.finprodType2).trigger("change");
        	// 新增资产二、三、四级分类 王贵奇 2019/8/1 开始
        	$("#assetType1").val($('#finprodMarketId').select2('data')[0].object.type9).trigger("change");
        	$("#assetType10").val($('#finprodMarketId').select2('data')[0].object.type10).trigger("change");
        	$("#assetType11").val($('#finprodMarketId').select2('data')[0].object.type11).trigger("change");
        	// 新增资产二、三、四级分类 王贵奇 2019/8/1 结束
    		$("#ccy").val(data.ccy).trigger("change");
    		$("#ccy").attr("disabled",true);
    		$('#iusseAmt').val($('#finprodMarketId').select2('data')[0].object.issueAmt);
        	$('#finprodIdhid').val($('#finprodMarketId').select2('data')[0].object.finprodId);
        	finprodId=$('#finprodMarketId').select2('data')[0].object.finprodId;

        	//新增活期存放逻辑 add by gejiaao 20191209
			if ($('#finprodMarketId').select2('data')[0].object.finprodType2 == 'D03'){
				$("#dateEnd_div").hide();
				$("#termDays_div").hide();
				$('#bussEtrInvestPersonInfoDto_counterId').textSelect('setValue',$('#finprodMarketId').select2('data')[0].object.issuer);
				$("#bussEtrInvestPersonInfoDto_counterId").attr("disabled",true);
				if ($('#ps').val() != T01) {
					$("#isAgreementEnd_div").show();
				}
			}else {
				$("#dateEnd_div").show();
				$("#termDays_div").show();
				$("#bussEtrInvestPersonInfoDto_counterId").attr("disabled",false);
				$("#isAgreementEnd_div").hide();
			}
    	}
    	else{
    		if(pageModel == PAGE_MODEL_NEW){
    			$("#ccy").val('').trigger("change");
    		}
    		$("#ccy").attr("disabled",false);
    		$("#finprodName").val("");
        	$("#dateStart").val("");
        	$("#dateEnd").val("");
        	$("#termDays").val("");
        	$("#price").val("").trigger('blur');
        	$("#finprodType2").val("");
            $('#iusseAmt').val('');
    	}
		getProAvailableCash();
    	getAssetAvailablePosition();
		// getChlCash();
    	ChannelAssetCommon.setChannelSelectObject($("#getChannelId"), finprodId, false, function () {

        });
    })
    
    $("#portfolioId").change(function(){
    	getProAvailableCash();
    	// getProAvailableCash1();
    	getAssetAvailablePosition();
		// getChlCash();
    })
    //获取组合可用持仓
    function getAssetAvailablePosition(){
    	if(($("#portfolioId").val() != '' && $("#portfolioId").val() != null) && ($("#ccy").val() != '' && $("#ccy").val() != null) && $('#ps').val() == T02
    		&& ($("#finprodMarketId").val() != '' && $("#finprodMarketId").val() != null)){
			var data = {portfolioId:$('#portfolioId').val(),ccy:$('#ccy').val(),finprodId:$('#finprodMarketId').select2('data')[0].object.finprodId,
                secAcctId : $('#getChannelId').val()};
            Joyin.ajaxJson(basePath + 'invest/entrust/etrbrowdepoinfo/EtrBrowDepoInfo002/getAssetAvailablePosition',data,
                    function(result) {
                $('#assetAvailablePosition').val(result).trigger('blur');
            });
		}
    }
    //获取T+1日可用现金
    function getProAvailableCash1(){ 
    	if($('#finprodMarketId').val() != null && $('#finprodMarketId').val() != '' ){
    		if(($("#portfolioId").val() != '' && $("#portfolioId").val() != null) && ($("#ccy").val() != '' && $("#ccy").val() != null)){
        		var data = {portfolioId:$('#portfolioId').val(),ccy:$('#ccy').val(),finprodId:$('#finprodMarketId').select2('data')[0].object.finprodId}
                Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/getProAvailableCash',data,
                        function(result) {
                	$('#proAvailableCash1').val(result.cashT1).trigger('blur');
                });
        	}
    	}
    }
    //获取T日可用现金
    function getProAvailableCash(){
		// 获取T+1日可用，必须要有资产代码和交易日期 start yjm 20191107
        var finprodId = '';
		if ($('#finprodMarketId').val()) {
            finprodId = $('#finprodMarketId').select2('data')[0];
            if (finprodId && finprodId.object){
                finprodId = finprodId.object.finprodId;
            }
        }
        // 获取T+1日可用，必须要有资产代码和交易日期 end yjm 20191107
    	if(($("#portfolioId").val() != '' && $("#portfolioId").val() != null) && ($("#ccy").val() != '' && $("#ccy").val() != null)){
    		var data = {portfolioId:$('#portfolioId').val(),ccy:$('#ccy').val(), chlFinprodId: $("#getChannelId").val(),
				finprodId:finprodId};
            Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/getProAvailableCash',data,
                    function(result) {
            	$('#proAvailableCash').val(result.cashT).trigger('blur');
            	$('#accAvailableCash').val(result.cashT3).trigger('blur');
				$('#proAvailableCash1').val(result.cashT1).trigger('blur');
				$('#getChannelIdValue').val(result.cashT2).trigger('blur');
            });
    	}
    }
    $("#ccy").change(function(){
    	getProAvailableCash();
    	// getProAvailableCash1();
    	getAssetAvailablePosition();
    })
    function psChange(){
    	if($("#ps").val()==T01){
    		$("#pschange").show();
    		// $("#pschange1").show();
    		$("#pschange2").hide();
    		$('#assetAvailablePosition').val('').trigger('blur');
    		getProAvailableCash();
        	// getProAvailableCash1();

			$("#isAgreementEnd_div").hide();
    	}
    	else{
    		$("#pschange").hide();
    		// $("#pschange1").hide();
    		$("#pschange2").show();
    		$('#proAvailableCash').val('').trigger('blur');
    		$('#proAvailableCash1').val('').trigger('blur');

			if ($("#finprodType2").val()== 'D03') {
				$("#isAgreementEnd_div").show();
			}
    		getAssetAvailablePosition();


    	}
    }

    //交易方向联动
    $("#ps").change(function(){
    	psChange();
    })
    
    function init(){
    	psChange();
    	// if($("#ccy").val() == null || $("#ccy").val() ==''){
    	// 	$("#ccy").val('CNY').trigger("change");
    	// }
    }
    //添加风险试算验证
    window.componentLoadCallback = function(isDone, pagePath, params) {
        if (isDone) {
            var riskparam = getriskdata();
            if (pageModel==PAGE_MODEL_NEW){
                riskparam.data =null;
            }
            window.BaseRiskMathComm.getparam(riskparam);
        }
    }
    /**
     * 绑定事件
     */
    $(function() {
    	init();
        /**
         * 保存
         */
        $('#EtrBrowDepoInfo002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        /**
         * 提交
         */
        $('#EtrBrowDepoInfo002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        /**
         * 风险试算
         */
        $('#EtrBrowDepoInfo002_btn_riskCalculate').click(function(){
            // window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
			var object = $.serializeToObject('#searchForm', true);

			if(object.finprodId != null && object.finprodId != ''){
				object.finprodId = $('#finprodMarketId').select2('data')[0].object.finprodId;
				object.price = $('#finprodMarketId').select2('data')[0].object.price/100;
			}
			window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/0/RSK002/F05',object,getriskdata());
        });

      //是否展示资产四五级分类
        if(IS_SHOW_TYPE == '01'){
        	$('#assetType11Div').show();
        }else{
        	$('#assetType11Div').hide();
        }
    });
}());