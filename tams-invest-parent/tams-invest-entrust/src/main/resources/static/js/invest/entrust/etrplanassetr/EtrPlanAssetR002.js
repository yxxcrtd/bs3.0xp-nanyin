/**
 * 计划类净值委托新建
 */
(function() {

    var getriskdata = function () {
        var entrustAmt = $('#entrustAmt').val();
        var entrustNum = $('#entrustNum').val();
        var ps = $('#ps').val();
        if(entrustAmt){
            var obj = {tradeDirection:$('#ps').val(),timePoint:MY_SUP_TIME_TYPE_02,valDate:sysDate,portfolioCode:$('#portfolioId').val(),tradeAmount:$('#entrustAmt').val(),assetType:fintype,isEnabled:'01',assetCode:$('#finprodIdhid').val()}
            var object = {tradeDirection:['ps','EtrPlanAssetR002.ps'],portfolioCode:['portfolioId','EtrPlanAssetR002.portfolioId'],tradeAmount:['entrustAmt','EtrPlanAssetR002.entrustAmt'],assetType:['assetType1','EtrPlanAssetR002.assetType1'],data:obj}
        }else if(entrustNum){
            var obj = {tradeDirection:$('#ps').val(),timePoint:MY_SUP_TIME_TYPE_02,valDate:sysDate,portfolioCode:$('#portfolioId').val(),tradeAmount:$('#entrustNum').val(),assetType:fintype,isEnabled:'01',assetCode:$('#finprodIdhid').val()}
            var object = {tradeDirection:['ps','EtrPlanAssetR002.ps'],portfolioCode:['portfolioId','EtrPlanAssetR002.portfolioId'],tradeAmount:['entrustNum','EtrPlanAssetR002.entrustNum'],assetType:['assetType1','EtrPlanAssetR002.assetType1'],data:obj}
        }else if('T01'==ps){
            var obj = {tradeDirection:$('#ps').val(),timePoint:MY_SUP_TIME_TYPE_02,valDate:sysDate,portfolioCode:$('#portfolioId').val(),tradeAmount:$('#entrustAmt').val(),assetType:fintype,isEnabled:'01',assetCode:$('#finprodIdhid').val()}
            var object = {tradeDirection:['ps','EtrPlanAssetR002.ps'],portfolioCode:['portfolioId','EtrPlanAssetR002.portfolioId'],tradeAmount:['entrustAmt','EtrPlanAssetR002.entrustAmt'],assetType:['assetType1','EtrPlanAssetR002.assetType1'],data:obj}
        }else{
            var obj = {tradeDirection:$('#ps').val(),timePoint:MY_SUP_TIME_TYPE_02,valDate:sysDate,portfolioCode:$('#portfolioId').val(),tradeAmount:$('#entrustNum').val(),assetType:fintype,isEnabled:'01',assetCode:$('#finprodIdhid').val()}
            var object = {tradeDirection:['ps','EtrPlanAssetR002.ps'],portfolioCode:['portfolioId','EtrPlanAssetR002.portfolioId'],tradeAmount:['entrustNum','EtrPlanAssetR002.entrustNum'],assetType:['assetType1','EtrPlanAssetR002.assetType1'],data:obj}
        }
        return object;
    }
    /**
	 * 保存或提交
	 * 
	 * @param operateType
	 *            操作类型（save，submit）
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
        var object = $.serializeToObject('#searchForm');
        if ($('#select2-finprodId-container').attr('title')){
            object.finprodName = $('#select2-finprodId-container').attr('title');
        }
        object.etrInvestPersonInfo.invManagerId = $('#etrInvestPersonInfo_invManagerId').val();
        object.operateType = operateType;
        object.pageModel = pageModel;
        object.entrustId = $('#entrustId').val();
        object.finprodId =$('#finprodIdhid').val();
        object.finvdate = $('#finvdate').val();
        object.finmdate = $('#finmdate').val();
        object.entrustStatus = $('#entrustStatus').val();
        if(object.etrInvestPersonInfo.trader != null && object.etrInvestPersonInfo.trader != ''){
            object.etrInvestPersonInfo.trader = object.etrInvestPersonInfo.trader.join(",");
        }
        if ($('#etrInvestPersonInfo_counterId').select2('data') && $('#etrInvestPersonInfo_counterId').select2('data')[0]) {
            object.counterType = $('#etrInvestPersonInfo_counterId').select2('data')[0].object;;
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
        	Joyin.ajaxJson(basePath + 'invest/entrust/etrplanassetr/EtrPlanAssetR002/checkAuthorization', object, function(result2) {
	            Joyin.ajaxJson(basePath + 'invest/entrust/etrplanassetr/EtrPlanAssetR002/check',object,
	                function(result) {
	                    Joyin.confirm(result.data,function() {
	                        if (operateType === 'save') {
	                            WorkFlow.doSaveList(basePath+'invest/entrust/etrplanassetr/EtrPlanAssetR002/save',object,"EtrPlanAssetR002_btn_save");
	                        } else {
	                            WorkFlow.doSubmitList(basePath+'invest/entrust/etrplanassetr/EtrPlanAssetR002/save',object,"EtrPlanAssetR002_btn_submit");
	                        }
	                    });
	                });
        	});
        });
    }
  // 查询组合可用现金
    var getPtlCashPositionInfo = function(){
        var data = {portfolioId:$('#portfolioId').val(),ccy:$('#ccy').val(),vdate:$('#vdate').val(),calId:$('#calendarId').val(), chlId:$("#chlId").val()}
        Joyin.ajaxJson(basePath + 'invest/entrust/etrplanassetr/EtrPlanAssetR002/getProAvailableCash',data,
                function(result) {
            $('#proAvailableCash').val(result.cashT).trigger('blur');
            $('#proAvailableCash1').val(result.cashT1).trigger('blur');
            $('#accAvailableCash').val(result.cashTG).trigger('blur');
            $('#channelAvailableCash').val(result.cashTD).trigger('blur');
        });
    }  
    // 查询组合可用持仓
    var getPtlSecPositionInfo = function(){
        var data = {portfolioId:$('#portfolioId').val(),finprodId:$('#finprodIdhid').val(),ccy:$('#ccy').val(), secAcctId:$("#chlId").val()}
        Joyin.ajaxJson(basePath + 'invest/entrust/etrplanassetr/EtrPlanAssetR002/getAssetAvailablePosition',data,
                function(result) {
            $('#assetAvailablePosition').val(result).trigger('blur');;
        });
    }
    // 添加风险试算验证
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

        var init = function(){
            $('#ps').change();
            if ($('#finprodId').val()){
                $('#finprodId').change();
            }
        }
        /**
		 * 保存
		 */
        $('#EtrPlanAssetR002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        /**
		 * 提交
		 */
        $('#EtrPlanAssetR002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        /**
		 * 风险试算
		 */
        $('#EtrPlanAssetR002_btn_riskcal').click(function(){
            // window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
            var object = $.serializeToObject('#searchForm');
            if($('#entrustNum').val()) {
                object.entrustAmt =$('#entrustNum').val();//把份额直接赋值给金额
            }
            object.finprodId =$('#finprodIdhid').val();
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/0/RSK002/F03',object,getriskdata());
        });
        /**
		 * 交易方向
		 */
        $('#ps').change(function() {
            var ps= $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var vdate = $('#vdate').val();
            var finprodIdhid = $('#finprodIdhid').val();
                if(ps == T04){
                    $('#proAvailableCash_div').show();
                    $('#proAvailableCash1_div').show();
                    // $('#accAvailableCash_div').show();
                    $('#div_channelCash').show();
                    $('#entrustAmt').attr('disabled',false);
                    $('#entrustNum').attr('disabled',true);
                    $('#entrustNum').val("");
                    $('#assetAvailablePosition_div').hide();
                    if(ccy && portfolioId && vdate){
                        getPtlCashPositionInfo();
                    }
                }else if(ps == T06){
                    $('#proAvailableCash_div').hide();
                    $('#proAvailableCash1_div').hide();
                    // $('#accAvailableCash_div').hide();
                    $('#div_channelCash').hide();
                    $('#entrustAmt').attr('disabled',true);
                    $('#entrustAmt').val("");
                    $('#entrustNum').attr('disabled',false);
                    $('#assetAvailablePosition_div').show();
                    if(portfolioId && finprodIdhid){
                        getPtlSecPositionInfo();
                    }
                }
        });
        /**
		 * 资产代码
		 */
        $('#finprodId').on('change', function () {
            var finprodId = $('#finprodId').val();
            var ps= $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var vdate = $('#vdate').val();
            var finprodIdhid = $('#finprodIdhid').val();
            if(finprodId){
                var data = $('#finprodId').select2('data')[0].object;
                $('#finprodIdhid').val(data.finprodId).trigger('change');
                $('#finprodName').val(data.finprodId).trigger('change');
                $('#calendarId').val(data.calendarId).trigger('change');
                $('#finvdate').val(new Date(data.finvdate)).trigger('change');
                $('#finmdate').val(new Date(data.finmdate)).trigger('change');
                $('#ccy').val(data.ccy).trigger('change');
                $('#assetType1').val(data.assetType1).trigger('change');
                <!--增加，3,4、zhuyongkang 2019/7/29  start -->
                $('#assetType10').val(data.assetType10).trigger('change');
                $('#assetType11').val(data.assetType11).trigger('change');
                <!--增加，3,4、zhuyongkang 2019/7/29  end -->
            }else {
                $('#finprodIdhid').val('').trigger('change');
                $('#finprodName').val('').trigger('change');
                $('#calendarId').val('').trigger('change');
                $('#finvdate').val('').trigger('change');
                $('#finmdate').val('').trigger('change');
                $('#ccy').val('').trigger('change');
                $('#assetType1').val('').trigger('change');
                <!--、zhuyongkang 2019/7/29  start -->
                $('#assetType10').val('').trigger('change');
                $('#assetType11').val('').trigger('change');
                <!--、zhuyongkang 2019/7/29  end -->
            }
            ChannelAssetCommon.setChannelSelectObject($("#chlId"), data.finprodId, false, function () {
            });
        });

        $("#chlId").change(function () {
            var ps= $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var vdate = $('#vdate').val();
            if(ps == T04 && ccy && portfolioId && vdate){
                getPtlCashPositionInfo();
            }
            if(ps == T06 && portfolioId){
                getPtlSecPositionInfo();
            }
        })

        $('#ccy').change(function(){
            var ps= $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var vdate = $('#vdate').val();
            var finprodIdhid = $('#finprodIdhid').val();
            if(ps == T04 && ccy && portfolioId && vdate){
                getPtlCashPositionInfo();
            }
            if(ps == T06 && ccy && portfolioId && finprodIdhid){
                getPtlSecPositionInfo();
            }
        });
        
        $('#portfolioId').change(function(){
            var ps= $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var vdate = $('#vdate').val();
            var finprodIdhid = $('#finprodIdhid').val();
            if(ps == T04 && ccy && portfolioId && vdate){
                getPtlCashPositionInfo();
            }else if(ps == T06 && portfolioId && finprodIdhid){
                getPtlSecPositionInfo();
            }
        });
        
        $('#vdate').change(function(){
            var ps= $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var vdate = $('#vdate').val();
            var finprodIdhid = $('#finprodIdhid').val();
            if(ps == T04 && ccy && portfolioId && vdate){
                getPtlCashPositionInfo();
            }
        });
        // 是否展示资产四五级分类
        if(IS_SHOW_TYPE == '01'){
        	$('#assetType11Div').show();
        }else{
        	$('#assetType11Div').hide();
        }
        init();
    });
}());
