/**
 * 存款存续期
 */
$(function() {
    function formatDateByMis(mis){
        if(mis){
            var tempDate = new Date(mis);
            return  tempDate.format();
        }
        else{
            return null;
        }
    }

    function saveOrSubmit(operateType) {
        var intSettType = $('#intSettType').val();
        if(intSettType == INT_SETT_TYPE_02 && GEN_PAY_ORDERS_AUTO == '01') {
            window.BussTrdTransferOrder['bussTrdTransferOrder'].generateOrders(function () {
                realSaveOrSubmit(operateType);
            });
        }
        else {
            realSaveOrSubmit(operateType);
        }
    }

    function realSaveOrSubmit(operateType) {
        var intSettType = $('#intSettType').val();
        if(intSettType == INT_SETT_TYPE_02){
            //		划款指令参数变更校验
            if(window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()){
                return ;
            }
        }
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#informationForm',true);
        object.operateType = operateType;
        object.opType = opType;
        object.pageModel = PAGE_MODEL_NEW;
        object.payMonth = $("#payMonth").val() ? $("#payMonth").val().join(',') : '';
        object.dayValue = $("#dayValue").val() ? $("#dayValue").val().join(',') : '';
        object.tradeAmtReal = $('#intAmt').val()?$('#intAmt').val():0;
        object.tradeAmt = $('#intAmt').val()?$('#intAmt').val():0;
        object.isClean = 'Y';//默认已结清
        if($('#intSettType').val()==INT_SETT_TYPE_01){
            object.ps = PS_T12;
        }
        else{
            object.ps = PS_T22;
        }
        Joyin.ajaxJson(basePath + 'invest/period/trddepoduration/TrdDepoDuration004/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/period/trddepoduration/TrdDepoDuration004/save', object, "TrdDepoDuration004_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/period/trddepoduration/TrdDepoDuration004/save', object, "TrdDepoDuration004_btn_submit");
                }
            });
        });
    }
    var triValueChange = function(id,data, defaultValue){
        if(data){
            $('#'+id).val(data).trigger("change").trigger('blur');
        }
        else{
            if (typeof defaultValue != 'undefined') {
                $('#'+id).val(defaultValue).trigger("change");
            }
            else {
                $('#'+id).val('0.00').trigger("change");
            }
        }
    };
    var isNotNull = function(value){
        return value && value != "";
    }
    var calResiPrincipal = function(){
        var finprodMarketId = $('#finprodMarketId').val();
        var portfolioId = $('#portfolioId').val();
        var mdateProd =  $('#mdateProd').val();
        var opType = $('#opType').val();
        if(isNotNull(finprodMarketId)&&isNotNull(portfolioId)&&isNotNull(mdateProd)){
            Joyin.ajax(basePath + "invest/period/trddepoduration/TrdDepoDuration002/calResiPrincipal",
                {finprodMarketId:finprodMarketId,portfolioId:portfolioId,mdateProd:mdateProd,opType:opType},
                function(result){
                    if(result.data){
                        triValueChange('remainPrinAmt',result.data.RESIPRINCIPAL);
                    }
                    else{
                        triValueChange('remainPrinAmt');
                    }
                });
        }
        else{
            triValueChange('remainPrinAmt');
        }

    }

    /**
     * 理论应计利息
     */
    var calTheoryIntDemand = function () {
        var finprodMarketId = $('#finprodMarketId').val();
        var portfolioId = $('#portfolioId').val();
        var mdateProd =  $('#mdateProd').val();
        if(isNotNull(finprodMarketId)&&isNotNull(portfolioId)&&isNotNull(mdateProd)){
            Joyin.ajax(basePath + "invest/period/trddepoduration/TrdDepoDuration002/calTheoryIntDemand",
                {finprodMarketId:finprodMarketId,portfolioId:portfolioId,mdateProd:mdateProd},
                function(result){
                    if(result.data){
                        triValueChange('theoryInt',result.data.IN_TDY_INTINCEXP);
                    }
                    else{
                        triValueChange('theoryInt');
                    }
                });
        }
        else{
            triValueChange('theoryInt');
        }
    }

    /**
     * 结息方式为现金结息时才调用指令组件
     */
    var intSettTypeChange = function(){
        var intSettType = $('#intSettType').val();
        if(intSettType==INT_SETT_TYPE_02){
            $('#bussTrdTransferOrder_div').show();
        }else{
            $('#bussTrdTransferOrder_div').hide();
        }
    }
    window.componentLoadCallback = function(isDone, pagePath, params) {
        if (isDone) {
            intSettTypeChange();
            window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                if (!$("#intSettType").val()) {
                    Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("TrdDepoDuration001.intSettType")));
                    return;
                }
                if (!$("#intAmt").val()) {
                    Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("TrdDepoDuration001.intAmtReal")));
                    return;
                }
                if (!$("#tradeDate").val()) {
                    Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("TrdDepoDuration001.tradeDate2")))
                    return;
                }
                if (!$("#finprodMarketId").val()) {
                    Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("TrdDepoDuration001.finprodMarketId")));
                    return;
                }
                var deal = {};
                deal.ccy = $("#ccy").val();
                deal.tradeAmt = $("#intAmt").val();
                if($('#intSettType').val()==INT_SETT_TYPE_01){
                    deal.ps = PS_T12;
                }
                else{
                    deal.ps = PS_T22;
                }
                // 通道交易 新增 hxy 2020/07/12 start
                deal.chlId = $("#chlId").val();
                // 通道交易 新增 hxy 2020/07/12 end
                deal.tradeDate = $("#tradeDate").val();
                deal.finprodType2 = FINPRODTYPE_DEFAULT;
                return {
                    'isTotalTrade': YES_OR_NO_Y
                    , 'bankLendingType': null
                    , 'portfolioId': $("#portfolioId").val()
                    , 'deal': eval(deal)
                };
            });
            $('#finprodMarketId').trigger('change');
        }
    }
    $(function() {
        //资产代码的联动
        $('#finprodMarketId').change(function(){
            var finprodMarketId = $('#finprodMarketId').val();
            var mdateProd = $('#mdateProd').val();
            if(finprodMarketId || finprodMarketId !=''){
//				币种、资产类型、资产名称、起息日、到期日、计息开始日、计息基础、委托行、借款利率%、资产推荐费率%
                Joyin.ajax(basePath + "invest/period/trddepoduration/TrdDepoDuration002/finprodMarketIdLink",{finprodMarketId:finprodMarketId,mdateProd:mdateProd},
                    function(result){
                        if(result.data){
                            triValueChange('ccy',result.data.CCY);
                            triValueChange('finprodMarketName',result.data.FINPRODNAME);
                            triValueChange('vdateProd',formatDateByMis(result.data.VDATE));
                            triValueChange('mdate',formatDateByMis(result.data.MDATE));
                            triValueChange('basis',result.data.BASIS);
                            triValueChange('entruster',result.data.ENTRUSTER);
                            triValueChange('loadRate',result.data.LOADRATE);
                            triValueChange('yield',result.data.YIELD);
                            triValueChange('cashId',result.data.CASHID);
                            triValueChange('finprodId',result.data.FINPRODID);
                            triValueChange('payCycle',result.data.PAY_CYCLE);
                            if(result.data.PAY_MONTH){
                                triValueChange('payMonth',result.data.PAY_MONTH.split(','));
                            }
                            if(result.data.DAY_VALUE){
                                triValueChange('dayValue',result.data.DAY_VALUE.split(','));
                            }
                          
                            if($('#finprodId').val()){
                            	ChannelAssetCommon.setChannelSelectObject($("#chlId"),  $('#finprodId').val(), null, function () {
                                });
                            }
                            }
                            
                    });
            }
            else{
                triValueChange('ccy');
                triValueChange('finprodMarketName');
                triValueChange('vdate');
                triValueChange('mdate');
                /*triValueChange('vdateProd');*/
                triValueChange('basis');
                triValueChange('entruster');
                triValueChange('loadRate');
                triValueChange('yield');
                triValueChange('cashId');
                triValueChange('finprodId');
                $('#chlId').html('');
            }
            calResiPrincipal();
            calTheoryIntDemand();


        });
        //组合代码联动
        $('#portfolioId').change(function(){
            var portfolioId = $('#portfolioId').val();
            //需求变更 存款存续期提前支取不要 托管账户 yjm start 20190918
            if(portfolioId || portfolioId !=''){
                Joyin.ajax(basePath + "invest/period/trddepoduration/TrdDepoDuration002/portfolioIdLink",{portfolioId:portfolioId},
                        function(result){
                    if(result.data){
                        triValueChange('portfolioName',result.data.PORTFOLIONAME);
                    }
                });
            }
            else{
                triValueChange('portfolioName');
            }
            calResiPrincipal();
            calTheoryIntDemand();
        });
        $('#mdateProd').change(function(){
            calResiPrincipal();
            calTheoryIntDemand();
            $('#finprodMarketId').trigger("change");
        });
        $('#intSettType').change(function(){
            intSettTypeChange();
        })
        $('#TrdDepoDuration004_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $('#TrdDepoDuration004_btn_save').click(function() {
            saveOrSubmit('save');
        });
    });
}());
