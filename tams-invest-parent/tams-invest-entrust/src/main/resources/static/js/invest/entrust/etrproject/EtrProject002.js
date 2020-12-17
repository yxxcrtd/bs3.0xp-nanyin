/**
 * 项目类委托新建
 */
(function() {
    var getriskdata = function () {
        var obj = {tradeDirection:$('#ps').val(),timePoint:MY_SUP_TIME_TYPE_02,valDate:sysDate,portfolioCode:$('#portfolioId').val(),tradeAmount:$('#entrustAmt').val(),assetType:fintype,isEnabled:'01',assetCode:$('#finprodIdhid').val()}
        //---add by 裴家俊  2019/03/20  添加资产类型条件---start-----
        if($('#assetType1').val() && $('#assetType1').val() != ''){
            obj.assetSecType = $('#assetType1').val();
        }
        //---add by 裴家俊  2019/03/20  添加资产类型条件---end-----
        var object = {tradeDirection:['ps','EtrProject002.ps'],portfolioCode:['portfolioId','EtrProject002.portfolioId'],tradeAmount:['entrustAmt','EtrProject002.entrustAmt'],assetType:['assetType1','EtrProject002.assetType1'],data:obj}
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
        if(typeof(BaseRiskMathComm) != 'undefined') {
            if (BaseRiskMathComm) {
                s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
                if (!s && s != '') {
                    return;
                }
                if (window.BaseRiskMathComm.isCalcConditionChanged(getriskdata())) {
                    return;
                }
            }
        }
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#searchForm');
        
        // 指令授信 start
        object.finprodType2 = 'F18';
        object.inputValue1 = 'F06';
        object.counterId = object.etrInvestPersonInfo.counterId;
        object.tradeAmt = object.entrustAmt;
        object.prinAmt = object.entrustAmt;
        object.tradeDate = $('#vdate').val();
        // 指令授信 end
        
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
	        Joyin.ajaxJson(basePath + 'invest/entrust/etrproject/EtrProject002/checkAuthorization', object, function(result2) {
		        Joyin.ajaxJson(basePath + 'invest/entrust/etrproject/EtrProject002/check',object,
		          function(result) {
		              Joyin.confirm(result.data,function() {
		                   if (operateType === 'save') {
		                       WorkFlow.doSaveList(basePath+'invest/entrust/etrproject/EtrProject002/save',object,"EtrProject002_btn_save");
		                   } else {
		                       WorkFlow.doSubmitList(basePath+'invest/entrust/etrproject/EtrProject002/save',object,"EtrProject002_btn_submit");
		                   }
		               });
		           });
	        });
        });
    }
  //查询组合可用现金
    var getPtlCashPositionInfo = function(){
        var data = {portfolioId:$('#portfolioId').val(),ccy:$('#ccy').val(),vdate:$('#vdate').val(),calId:$('#calendarId').val(),chlFinprodId: $("#chlId").val()}
        Joyin.ajaxJson(basePath + 'invest/entrust/etrproject/EtrProject002/getProAvailableCash',data,
                function(result) {
            $('#proAvailableCash').val(result.cashT).trigger('blur');
            $('#proAvailableCash1').val(result.cashT1).trigger('blur');
            $('#accAvailableCash').val(result.cashT3).trigger('blur');
            $('#channelAvailableCash').val(result.cashT2).trigger('blur');
        });
    }  
    //查询组合可用持仓
    var getPtlSecPositionInfo = function(){
        if (!$('#finprodIdhid').val()) {
            return;
        }
        var data = {portfolioId:$('#portfolioId').val(),finprodId:$('#finprodIdhid').val(),ccy:$('#ccy').val(), secAcctId: $("#chlId").val()}
        Joyin.ajaxJson(basePath + 'invest/entrust/etrproject/EtrProject002/getAssetAvailablePosition',data,
                function(result) {
            $('#assetAvailablePosition').val(result).trigger('blur');;
        });
    }
    //添加风险试算验证
    window.componentLoadCallback = function(isDone, pagePath, params) {
        if (isDone) {
            if(typeof(BaseRiskMathComm) != 'undefined') {
                var riskparam = getriskdata();
                if (pageModel == PAGE_MODEL_NEW) {
                    riskparam.data = null;
                }
                window.BaseRiskMathComm.getparam(riskparam);
            }
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
        $('#EtrProject002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        /**
         * 提交
         */
        $('#EtrProject002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        /**
         * 风险试算
         */
        $('#EtrProject002_btn_riskcal').click(function(){
            // window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
            var object = $.serializeToObject('#searchForm');
            object.finprodId =$('#finprodIdhid').val();
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/0/RSK002/F06',object,getriskdata());
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
                if(ps == T01){
                    $('#proAvailableCash_div').show();
                    $('#proAvailableCash1_div').show();
                    // $('#accAvailableCash_div').show();
                    $('#div_channelCash').show();
//                    $('#entrustAmt').attr('disabled',false);
//                    $('#entrustNum').attr('disabled',true);
                    $('#assetAvailablePosition_div').hide();
                    if(ccy && portfolioId && vdate){
                        getPtlCashPositionInfo();
                    }
                }else if(ps == T02){
                    $('#proAvailableCash_div').hide();
                    $('#proAvailableCash1_div').hide();
                    // $('#accAvailableCash_div').hide();
                    $('#div_channelCash').hide();
//                    $('#entrustAmt').attr('disabled',true);
//                    $('#entrustNum').attr('disabled',false);
                    $('#assetAvailablePosition_div').show();
                    if(portfolioId && finprodIdhid){
                        getPtlSecPositionInfo();
                    }
                }
        });

        function refreshPosition() {
            var ps = $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var vdate = $('#vdate').val();
            if (ps == T01 && ccy && portfolioId && vdate) {
                getPtlCashPositionInfo();
            }
            if (ps == T02 && portfolioId) {
                getPtlSecPositionInfo();
            }
        }

        /**
         * 资产代码
         */
        $('#finprodId').on('change', function () {
            var finprodId = $('#finprodId').val();            
            if(finprodId){
                var data = $('#finprodId').select2('data')[0].object;
                $('#finprodIdhid').val(data.finprodId).trigger('change')
                $('#finprodName').val(data.finprodId).trigger('change');
                $('#calendarId').val(data.calendarId).trigger('change');
                $('#finvdate').val(new Date(data.finvdate)).trigger('change');
                $('#finmdate').val(new Date(data.finmdate)).trigger('change');
                $('#ccy').val(data.ccy).attr('disabled',true).trigger('change');
                $('#assetType1').val(data.assetType1).trigger('change');
                ChannelAssetCommon.setChannelSelectObject($("#chlId"), data.finprodId, false, function () {

                });
                // 项目类资产五级分类改造 add by renping 2019-08-01 start
                $('#type9').val(data.type9).trigger('change');
                $('#type10').val(data.type10).trigger('change');
                $('#type11').val(data.type11).trigger('change');
                // 项目类资产五级分类改造 add by renping 2019-08-01 end
            }else {
                $('#finprodIdhid').val('').trigger('change')
                $('#finprodName').val('').trigger('change');
                $('#calendarId').val('').trigger('change');
                $('#finvdate').val('').trigger('change');
                $('#finmdate').val('').trigger('change');
                $('#ccy').val('').attr('disabled',false).trigger('change');
                $('#assetType1').val('').trigger('change');
                // 项目类资产五级分类改造 add by renping 2019-08-01 start
                $('#type9').val('').trigger('change');
                $('#type10').val('').trigger('change');
                $('#type11').val('').trigger('change');
                // 项目类资产五级分类改造 add by renping 2019-08-01 end
                refreshPosition();
            }
        });

        $("#chlId").change(refreshPosition)
        
        $('#ccy').change(function(){
            var ps= $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var vdate = $('#vdate').val();
            var finprodIdhid = $('#finprodIdhid').val();
            if(ps == T01 && ccy && portfolioId && vdate){
                getPtlCashPositionInfo();
            }
            if(ps == T02 && ccy && portfolioId && finprodIdhid){
                getPtlSecPositionInfo();
            }
        });
        
        $('#portfolioId').change(function(){
            var ps= $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var vdate = $('#vdate').val();
            var finprodIdhid = $('#finprodIdhid').val();
            if(ps == T01 && ccy && portfolioId && vdate){
                getPtlCashPositionInfo();
            }else if(ps == T02 && portfolioId && finprodIdhid){
                getPtlSecPositionInfo();
            }
        });
        
        $('#vdate').change(function(){
            var ps= $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var vdate = $('#vdate').val();
            var finprodIdhid = $('#finprodIdhid').val();
            if(ps == T01 && ccy && portfolioId && vdate){
                getPtlCashPositionInfo();
            }
        });
        if(IS_SHOW_TYPE == '01'){
        	$('#assetType11Div').show();
        }else{
        	$('#assetType11Div').hide();
        }
        init();
    });
}());
