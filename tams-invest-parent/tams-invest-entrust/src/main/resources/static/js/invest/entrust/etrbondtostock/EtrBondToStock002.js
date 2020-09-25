/**
 * 债转股委托新建
 */
(function() {

    var getriskdata = function () {
        var obj = {
            assetCode:$("#finprodId").val(),
            timePoint:MY_SUP_TIME_TYPE_02,
            valDate:sysDate,
            portfolioCode:$('#portfolioId').val(),
            tradeAmount:$('#stocketrPriceTotal').val(),
            assetType:PA_TYPE_ZQMC,
            isEnabled:'01',
            tradeDirection:PS_ZQMC
        };
        var finType = 'paBussType';
        var finMng = 'TrdDepositInvestTrade001.finprodType';
        obj.assetType = fintype;
        var object = {portfolioCode:['portfolioId','EtrBondToStock001.portfolioId'],tradeAmount:['shareAmt','EtrBondToStock002.stocketrPriceTotal'],assetType:[finType,finMng],tradeDirection:['busiPs','TrdBondToStock002.ps'],data:obj}
        return object;
    };
	
	//查询持仓信息
    var getPositionInfo =function(){
    	$("#stocketrPriceTotal").val('').trigger("change");
        //转债代码
        var finprodId = $("#finprodId").val();
        //组合代码
        var portfolioId = $("#portfolioId").val();
        var cdate = platDate;
        if(finprodId&&portfolioId&&cdate){
            var params={
                    "portfolioId":portfolioId,
                    "finprodId":finprodId,
                    'cdate':platDate,
                    "ccy":$("#ccy").val(),
                    "invAim":$("#invAim").val(),
                    "secAcctId":$('#secAcctId').val(),
                    "chlId":$("#chlId").val()
                };
            Joyin.ajax(basePath + 'invest/entrust/etrbondtostock/EtrBondToStock002/getPositionInfo',params,function (ret) {
                if(ret&&ret.data){
                    if(ret.data.holdetrPriceTotal){
                        $("#holdetrPriceTotal").val(ret.data.holdetrPriceTotal);
                    }
                    if(ret.data.conetrPriceTotal){
                        $("#conetrPriceTotal").val(ret.data.conetrPriceTotal);
                    }
                }
            });
        }
    };
	
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
        object.entrustId = $("#entrustId").val();
        object.operateType = operateType;
        object.pageModel = pageModel;
        object.entrustStatus = $('#entrustStatus').val();
        object.etrInvestPersonInfo.invManagerId = $('#etrInvestPersonInfo_invManagerId').val();
        if(object.etrInvestPersonInfo.trader != null && object.etrInvestPersonInfo.trader != ''){
        	object.etrInvestPersonInfo.trader = object.etrInvestPersonInfo.trader.join(",");
        }
        if(FileCommon) {
    		object.fileArray = FileCommon.getUploadFileInfo();
    		if(object.fileArray && _.isArray(object.fileArray)){
    			$.each(object.fileArray, function(index,obj){
    				obj.seqNo = obj.fileNo;
    			});
    		}
    	}
        Joyin.ajaxJson(basePath + 'invest/entrust/etrbondtostock/EtrBondToStock002/checkAuthorization', object, function(result2) {
	        Joyin.ajaxJson(basePath + 'invest/entrust/etrbondtostock/EtrBondToStock002/check',object,
	          function(result) {
	              Joyin.confirm(result.data,function() {
	                   if (operateType === 'save') {
	                       WorkFlow.doSaveList(basePath+'invest/entrust/etrbondtostock/EtrBondToStock002/save',object,"EtrBondToStock002_btn_save");
	                   } else {
	                       WorkFlow.doSubmitList(basePath+'invest/entrust/etrbondtostock/EtrBondToStock002/save',object,"EtrBondToStock002_btn_submit");
	                   }
	               });
	           });
        });
    }
    
    //转股券面总额带出
    $("#stocketrPriceTotal").change(function(){
    	if($("#conPrice").val()){
    		$("#expConNum").val(($("#stocketrPriceTotal").val()/$("#conPrice").val()).toFixed(2)).trigger("change");
    	}
    	if($("#sharePrice").val()&&$("#expConNum").val()){
    		$("#expConValue").val(($("#expConNum").val()*$("#sharePrice").val()).toFixed(2)).trigger("change");
    	}
    })

	//画面联动，转债代码带出
    $("#finprodId").change(function(){
    	var finprodId = $('#finprodId').val();
    	if($("#finprodId").val() != null && $("#finprodId").val() != ''){
    		$("#finprodName").val($('#finprodId').select2('data')[0].text).trigger("change");
        	$("#shareId").val($('#finprodId').select2('data')[0].object.shareId).trigger("change");
        	$("#shareName").val($('#finprodId').select2('data')[0].object.shareName).trigger("change");
        	$("#conShareId").val($('#finprodId').select2('data')[0].object.conShareId).trigger("change");
        	$("#tradeMarket").val($('#finprodId').select2('data')[0].object.tradeMarket).trigger("change");
        	$("#conVdate").val(Date.ofDate($('#finprodId').select2('data')[0].object.conVdate).format());
        	$("#conPrice").val($('#finprodId').select2('data')[0].object.conPrice).trigger("change");
        	$("#sharePrice").val($('#finprodId').select2('data')[0].object.sharePrice).trigger("change");
        	$("#shareFinprodId").val($('#finprodId').select2('data')[0].object.shareFinprodId).trigger("change");
        	$("#prec").val($('#finprodId').select2('data')[0].object.prec).trigger("change");
        	$("#preb").val($('#finprodId').select2('data')[0].object.preb).trigger("change");
    		$("#ccy").val($('#finprodId').select2('data')[0].object.ccy).trigger("change");
        	finprodId=$('#finprodId').select2('data')[0].object.finprodId;
    	}
    	else{
    		if(pageModel == PAGE_MODEL_NEW){
    			$("#ccy").val('').trigger("change");
        		$("#finprodName").val('').trigger("change");
            	$("#shareId").val('').trigger("change");
            	$("#shareName").val('').trigger("change");
            	$("#tradeMarket").val('').trigger("change");
            	$("#conShareId").val('').trigger("change");
        		$("#conPrice").val('').trigger("change");
    		}
    	}
    })
    $("#portfolioId").change(function(){
    	
    })
    
    /**
     * 将资产代码带出的值置灰不可修改
     */
    var init = function() {
        if ($("#finprodId").val()){
        	$("#ccy").trigger("change").attr("disabled", true);
    		$("#finprodName").trigger("change").attr("disabled", true);
        	$("#shareId").trigger("change").attr("disabled", true);
        	$("#shareName").trigger("change").attr("disabled", true);
        	$("#tradeMarket").trigger("change").attr("disabled", true);
        	$("#conShareId").trigger("change").attr("disabled", true);
        }
    }
  /*  //添加风险试算验证
    window.componentLoadCallback = function(isDone, pagePath, params) {
        if (isDone) {
            var riskparam = getriskdata();
            if (pageModel==PAGE_MODEL_NEW){
                riskparam.data =null;
            }
            window.BaseRiskMathComm.getparam(riskparam);
        }
    }*/
    /**
     * 绑定事件
     */
    $(function() {
    	init();
    	//通过债转股点击将转债代码与投资目的置灰
    	if(MODEL_SHOW=='01'){
    		$("#invAim").trigger("change").attr("disabled", true);
    		$("#finprodId").trigger("change").attr("disabled", true);
    	}else{
    		$("#invAim").trigger("change").attr("disabled", false);
    		$("#finprodId").trigger("change").attr("disabled", false);
    	}
    	
        /**
         * 保存
         */
        $('#EtrBondToStock002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        /**
         * 提交
         */
        $('#EtrBondToStock002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        
        $('#secAcctId').change(function(){
            if ($("#secAcctId").val()) {
                $("#chlId").val('').prop("disabled", true);
            }else {
                $("#chlId").prop("disabled", false);
            }
        });
        
        if($("#chlId").val()!=null&&$("#chlId").val()!=''){
        	$("#secAcctId").prop("disabled", true);
        }
        
        if($("#secAcctId").val()!=null&&$("#secAcctId").val()!=''){
        	$("#chlId").prop("disabled", true);
        }
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
        });
        $("#portfolioId,#finprodId,#invAim,#chlId,#secAcctId").change(getPositionInfo);
        var deleteSelect = function (id) {
            $('#'+id).textSelect('setValue','');
            $.each($('#'+id).find('option'),function (index,element) {
                if( $('#'+id).find('option').eq(index).val()){
                    $('#'+id).find('option').eq(index).remove();
                }
            });
        }
   /*     *//**
         * 风险试算
         */
        $('#EtrBondToStock002_btn_riskcal').click(function(){
            //window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
            var object = $.serializeToObject('#searchForm');
            object.entrustAmt=$('#stocketrPriceTotal').val();
            object.ps=etrPs;
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/0/RSK002/F01',object,getriskdata());
        });
    });
}());