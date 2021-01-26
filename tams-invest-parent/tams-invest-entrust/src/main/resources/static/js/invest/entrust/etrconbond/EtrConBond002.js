/**
 * 可转债委托新建
 */
(function(){
    var getriskdata = function () {
        var obj = {tradeDirection:etrPs,timePoint:MY_SUP_TIME_TYPE_02,valDate:sysDate,portfolioCode:$('#portfolioId').val(),tradeAmount:$('#purchBondTotal').val(),assetType:fintype,assetSecType:$('#assetType1').val(),isEnabled:'01',assetCode:$('#finprodIdhid').val()}
        var object = {tradeDirection:['ps','EtrConBond002.ps'],portfolioCode:['portfolioId','EtrConBond002.portfolioId'],tradeAmount:['purchBondTotal','EtrConBond001.purchBondTotal'],assetType:['assetType1','EtrConBond002.assetType1'],assetCode:['finprodId','EtrBondFirstLBidding002.finprodId'],data:obj}
        return object;
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
     * 资产代码
     */
    $('#finprodId').change(function() {

        var ccy = $('#ccy').val();
        var portfolioId = $('#portfolioId').val();
        var finprodId = $('#finprodId').val();
        if(finprodId){
        	var data = $('#finprodId').select2('data')[0].object;
            $('#finprodIdhid').val(data.finprodId).trigger('change');
            $('#finprodName').val(data.finprodName).trigger('change');
            $('#tradeMarket').val(data.tradeMarket).trigger('change').attr('disabled',true);
            $('#assetType1').val(data.assetType1).trigger('change').attr('disabled',true);
            $('#ccy').val(data.ccy).trigger('change').attr('disabled',true);
            if(ccy && portfolioId){
                getPtlCashPositionInfo();
            }
        } else {
            $('#finprodIdhid').val('').trigger('change');
            $('#finprodName').val('').trigger('change');
            $('#tradeMarket').val('').trigger('change').attr('disabled',false);
            $('#ccy').val(ccy).trigger('change').attr('disabled',false);
            $('#assetType1').val('').trigger('change');
        }
        // 画星星
        star();
    });
    
    /**
     * 组合代码
     */
    $('#portfolioId').change(function(){
        var ccy = $('#ccy').val();
        var portfolioId = $('#portfolioId').val();
        if(portfolioId){
            var data = $('#portfolioId').select2('data')[0].object;
            $('#profitType').val(data).trigger('change');
        }
        if(ccy && portfolioId){
            getPtlCashPositionInfo();
        } 
    });

    //联动通道可用现金
    $("#chlId").on("change", function () {
        if ($('#chlId').val()) {
            var data = {portfolioId:$('#portfolioId').val(),ccy:$('#ccy').val(),secAcctId:$('#secAcctId').val(),chlId:$('#chlId').val(),vdate:$('#entrustDate').val()}
            Joyin.ajaxJson(basePath + 'invest/trade/conbond/TrdConBond002/getProAvailableCash',data,
                function(result) {
                    $('#channelAvailableCash').val(result.cashC).trigger('blur');
            });
        }else {
            $('#channelAvailableCash').val('').trigger('blur');
        }
    });
    
  //查询组合可用现金
    var getPtlCashPositionInfo = function(){
        var data = {portfolioId:$('#portfolioId').val(),secAcctId:$('#secAcctId').val(),ccy:$('#ccy').val(),vdate:$('#entrustDate').val()}
        Joyin.ajaxJson(basePath + 'invest/trade/conbond/TrdConBond002/getProAvailableCash',data,
                function(result) {
            $('#proAvailableCash').val(result.cashT).trigger('blur');
            $('#tradeMarketCash').val(result.cashS).trigger('blur');
        });
    }  
   
    
    /**
     * 保存或提交
     * 
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
    	//风险试算 start
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
    	//风险试算 end
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#searchForm',true);
        object.operateType = operateType;
        object.pageModel = pageModel;
        object.entrustId = $('#entrustId').val();
        object.entrustStatus = $('#entrustStatus').val();
        object.etrInvestPersonInfo.invManagerId = $('#etrInvestPersonInfo_invManagerId').val();
        if(object.etrInvestPersonInfo.trader != null && object.etrInvestPersonInfo.trader != ''){
            object.etrInvestPersonInfo.trader = object.etrInvestPersonInfo.trader;//.join(",");
        }
        if(FileCommon) {
            object.fileArray = FileCommon.getUploadFileInfo();
            if(object.fileArray && _.isArray(object.fileArray)){
                $.each(object.fileArray, function(index,obj){
                    obj.seqNo = obj.fileNo;
                });
            }
        }
        Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/check', object, function(result1) {
        	Joyin.ajaxJson(basePath + 'invest/entrust/etrconbond/EtrConBond002/checkAuthorization', object, function(result2) {
	            Joyin.ajaxJson(basePath + 'invest/entrust/etrconbond/EtrConBond002/check',object,
	                function(result) {
	                    Joyin.confirm(result.data,function() {
	                        if (operateType === 'save') {
	                            WorkFlow.doSaveList(basePath+'invest/entrust/etrconbond/EtrConBond002/save',object,"EtrConBond002_btn_save");
	                        } else {
	                            WorkFlow.doSubmitList(basePath+'invest/entrust/etrconbond/EtrConBond002/save',object,"EtrConBond002_btn_submit");
	                        }
	                    });
	                });
        	});
        })
    }
    
    window.componentLoadCallback = function(isDone, pagePath, params){
        if (isDone) {
            if(typeof(BaseRiskMathComm) != 'undefined') {
            var riskparam = getriskdata();
            if (typeof riskparam != 'undefined') {
                if (pageModel==PAGE_MODEL_NEW){
                    riskparam.data =null;
                }
                window.BaseRiskMathComm.getparam(riskparam);
                }
            }
        }
    	$('#etrInvestPersonInfo_trader').attr('multiple',false);
    }

    /**
     * 将资产代码带出的值置灰不可修改
     */
    var init = function() {
        $("#invAim").trigger("change").attr("disabled", true);
        if (entity.finprodId){
            $("#assetType1").trigger("change").attr("disabled", true);
            $("#ccy").trigger("change").attr("disabled", true);
            $("#tradeMarket").trigger("change").attr("disabled", true);
            $("#entrustDate").trigger("change").attr("disabled", true);
        }
        if ($("#secAcctId").val()) {
            $("#chlId").val('').prop("disabled", true);
        }else {
            $("#chlId").prop("disabled", false);
        }
        if ($("#chlId").val()) {
            $("#secAcctId").val('').trigger("change").prop("disabled", true);
        } else {
            $("#secAcctId").prop("disabled", false);
        }
    }

    $(function(){
        star();
        init();
	    /**
	     * 保存
	     */
	    $('#EtrConBond002_save').click(function() {
	        saveOrSubmit('save');
	    });
	    /**
	     * 提交
	     */
	    $('#EtrConBond002_btn_submit').click(function() {
	        saveOrSubmit('submit');
	    });
	   
	    
	 // 代办显示点击查看风险试算结果  Start
        /**
         * 代办点击查看风险试算结果
         */
        if(todoList==true){
            $("#riskOfTrialLink").show();
        }else{
            $("#riskOfTrialLink").hide();
        }
        $("#riskOfTrialLink").click(function() {
            Joyin.dialog(JoyinUtil.getLabel("BaseRiskMathComm.riskAndCredit"),
                basePath + "component/common/RiskMathAndCredit/init",
                "90%", "90%",{workflowId:workflowId,tradeId:tradeId,TodoList:todoList});
        });
        // 代办显示点击查看风险试算结果   End

        /**
	     * 风险试算
	     */
	    $('#EtrConBond002_btn_riskcal').click(function(){
        	/*var object = $.serializeToObject('#searchForm',true);
            object.entrustId = $('#entrustId').val();
            object.entrustStatus = $('#entrustStatus').val();
            object.finvdate = $('#finvdate').val();
            object.finmdate = $('#finmdate').val();
            object.etrInvestPersonInfo.invManagerId = $('#etrInvestPersonInfo_invManagerId').val();
            var initParam = getriskdata();
            
            Joyin.ajaxJson(basePath + 'component/common/BaseRiskMathComm/check', initParam, function(resultresult) {
            	//window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
        		});*/
            var object = $.serializeToObject('#searchForm',true);
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/0/RSK002/F01',object,getriskdata());
	    });
	})

    $('#secAcctId').change(function(){
        if ($("#secAcctId").val()) {
            $("#chlId").val('').prop("disabled", true);
        }else {
            $("#chlId").prop("disabled", false);
        }
        //add by mashukai ZGBSS-239 证券账户更改时交易所资金账户可用现金没有带出来 start
        if ($('#portfolioId').val() != null && $('#portfolioId').val() != ''){
            getPtlCashPositionInfo();
        }
        //add by mashukai ZGBSS-239 证券账户更改时交易所资金账户可用现金没有带出来 end
    });

    $("#chlId").change(function(){
        if ($("#chlId").val()) {
            $("#secAcctId").val('').trigger("change").prop("disabled", true);
        } else {
            $("#secAcctId").prop("disabled", false);
        }
    })


    $('#portfolioId').change(function(){
        if($('#secAcctId').val()){
            deleteSelect('secAcctId')
        }
        //add by mashukai ZGBSS-239 修正清除产品代码时交易所资金账户可用现金不清零  start
        if ($('#portfolioId').val() == null || $('#portfolioId').val() == ''){
              $('#tradeMarketCash').val('').trigger("change");
        }
        //add by mashukai ZGBSS-239 修正清除产品代码时交易所资金账户可用现金不清零  end
    });

    var deleteSelect = function (id) {
        $('#'+id).textSelect('setValue','');
        $.each($('#'+id).find('option'),function (index,element) {
            if( $('#'+id).find('option').eq(index).val()){
                $('#'+id).find('option').eq(index).remove();
            }
        });
    }
}());