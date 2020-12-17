/**
 * 外汇即期委托新建
 */
(function() {

    //---add by jjpei 20190718----start---获取风险试算画面要素与Label提示文本
    var getRiskData = function () {
        var obj = {tradeDirection:$('#ps').val(),timePoint:MY_SUP_TIME_TYPE_01,valDate:sysDate,portfolioCode:$('#portfolioId').val(),tradeAmount:$('#entrustAmt').val(),assetType:fintype_spotex,isEnabled:USE_STATUS_01}
        var object = {tradeDirection:['ps','EtrWtSpotex.ps'],portfolioCode:['portfolioId','EtrWtSpotex.portfolioId'],tradeAmount:['entrustAmt','EtrWtSpotex.entrustAmt'],data:obj}
        return object;
    }
    //---add by jjpei 20190718----end---获取风险试算画面要素与Label提示文本
    /**
     * 保存或提交
     * 
     * @param operateType
     *            操作类型（save，submit）
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
                if (window.BaseRiskMathComm.isCalcConditionChanged(getRiskData())) {
                    return;
                }
            }
        }
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#searchForm');
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
        object.operateType = operateType;
        object.pageModel = pageModel;
        object.entrustId = $('#entrustId').val();
        object.entrustStatus = $('#entrustStatus').val();
//        object.termDays = $('#termDays').val().split('.')[0];
        object.ps = $('#ps').val();
        object.inputValue1 = PINAN_ASSET_TYPE_F13;
        Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/check', object, function(result1) {
        	Joyin.ajaxJson(basePath + 'invest/entrust/etrwtspotex/EtrWtSpotex002/checkAuthorization', object, function(result2) {
	            Joyin.ajaxJson(basePath + 'invest/entrust/etrwtspotex/EtrWtSpotex002/check',object,
	                function(result) {
	                    Joyin.confirm(result.data,function() {
	                        if (operateType === 'save') {
	                            WorkFlow.doSaveList(basePath+'invest/entrust/etrwtspotex/EtrWtSpotex002/save',object,"EtrWtSpotex002_btn_save");
	                        } else {
	                            WorkFlow.doSubmitList(basePath+'invest/entrust/etrwtspotex/EtrWtSpotex002/save',object,"EtrWtSpotex002_btn_submit");
	                        }
	                    });
	                });
        	});
        });
    }    
    /**
     * 组合代码，交易货币，对应货币联动处理
     */
    var qryMoneyAndAcct = function(){
        // 组合代码
        var portfolioId = $('#portfolioId').val();
        // 货币对
        var curPair = $('#curPair').val();
        // 交易货币
        var ccy = $('#ccy').val();
        // 对应货币
        var corCcy = $('#corCcy').val();
        if(portfolioId && curPair && ccy && corCcy){
            // 交易货币可用持仓
            var data = {portfolioId:portfolioId,ccy:ccy}
            Joyin.ajaxJson(basePath + 'invest/entrust/etrwtspotex/EtrWtSpotex002/getProAvailableCash',data,
                    function(result) {
                $('#proAvailableCash').val(result.cashTG).trigger('blur');
            });
            // 对应货币可用持仓
            data = {portfolioId:portfolioId,ccy:corCcy}
            Joyin.ajaxJson(basePath + 'invest/entrust/etrwtspotex/EtrWtSpotex002/getProAvailableCash',data,
                    function(result) {
                $('#corCurPos').val(result.cashTG).trigger('blur');
            });
            // 交易货币账户
            data = {portfolioId:portfolioId,ccy:ccy,acctType:acctType}
            Joyin.ajaxJson(basePath + 'business/common/CommonSelect/selectPtlCapAcctNoByRequestBody',data,
                    function(result) {
                $('#dealCurAcct').empty();
                if(result && result.length > 0){
                    for(i = 0,len=result.length;i < len;i++){
                        $('#dealCurAcct').append("<option value='"+result[i].id+"'>"+result[i].text+"</option>");
                    }
                    $('#dealCurAcct').val(result[0].id).trigger('change');
                }
                
            });
            // 对应货币账户
            data = {portfolioId:portfolioId,ccy:corCcy,acctType:acctType}
            Joyin.ajaxJson(basePath + 'business/common/CommonSelect/selectPtlCapAcctNoByRequestBody',data,
                    function(result) {
                $('#corCurAcct').empty();
                if(result && result.length > 0){
                    for(i = 0,len=result.length;i < len;i++){
                        $('#corCurAcct').append("<option value='"+result[i].id+"'>"+result[i].text+"</option>");
                    }
                    $('#corCurAcct').val(result[0].id).trigger('change');
                }                
            });
        }
    }
    
    /**
     * 货币对非空时，交易货币选择后，自动显示对应货币
     */
    var showCorCcy = function(){        
        var curPair = $('#curPair').val();
        var ccy = $('#ccy').val();
        if(curPair && ccy){
            var curPairArr = curPair.split('/');
            if(curPairArr[0] == ccy){
                $('#corCcy').val(curPairArr[1]).trigger('change');
            }else if(curPairArr[1] == ccy){
                $('#corCcy').val(curPairArr[0]).trigger('change');
            }
        }
    }
    /**
     * 计算预计对应货币金额
     */
    var calcExpCorAmt = function(){
        // 货币对
        var curPair = $('#curPair').val();
        // 交易货币
        var ccy = $('#ccy').val(); 
        // 即期汇率
        var price = $('#price').val();
        // 交易货币金额
        var entrustAmt = $('#entrustAmt').val(); 
        if(curPair && ccy && price && entrustAmt){
            if(curPair.indexOf(ccy) == 0){
                if(ccy == JPY){
                    $('#expCorAmt').val(JoyinMath.doDiv(JoyinMath.doMul(price , entrustAmt) , 100)).trigger('blur');
                }else{
                    $('#expCorAmt').val(JoyinMath.doMul(price , entrustAmt)).trigger('blur');
                }
            }else if(curPair.indexOf(ccy) > 0){
                if(ccy == JPY){
                    $('#expCorAmt').val(JoyinMath.doMul(JoyinMath.doDiv(entrustAmt , price) , 100 )).trigger('blur');
                }else{
                    $('#expCorAmt').val(JoyinMath.doDiv(entrustAmt , price)).trigger('blur');
                }
            }
        }else{
            $('#expCorAmt').val('').trigger('blur');
        }
        
    }

    //添加风险试算验证
    window.componentLoadCallback = function(isDone, pagePath, params) {
        if (isDone) {
            if(typeof(BaseRiskMathComm) != 'undefined') {
                var riskParam = getRiskData();
                if (pageModel == PAGE_MODEL_NEW) {
                    riskParam.data = null;
                }
                window.BaseRiskMathComm.getparam(riskParam);
            }
        }
    }
    /**
     * 绑定事件
     */
    $(function() {
        var init = function(){
            // 货币对和即期汇率联动,setDecimalPoint(货币对下拉框id,即期汇率文本框id)
            window.EtrForeingExchange.setDecimalPoint('curPair','price',0);
            $('#portfolioId').change();
            
            //
            $('#vdate').change();
        }
        /**
         * 保存
         */
        $('#EtrWtSpotex002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        /**
         * 提交
         */
        $('#EtrWtSpotex002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        /**
         * 风险试算
         */
        $('#EtrWtSpotex002_btn_riskcal').click(function(){
            //window.BaseRiskMathComm.initBaseRiskMathCommPage(getRiskData());
            var object = $.serializeToObject('#searchForm');
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/0/RSK002/F13',object,getRiskData());
        });
        /**
         * 货币对
         */
        $('#curPair').change(function(){
            // 货币对和即期汇率联动,setDecimalPoint(货币对下拉框id,即期汇率文本框id)
            window.EtrForeingExchange.setDecimalPoint('curPair','price',0);
            // 货币对,交易货币和对应货币联动
            var curPair = $('#curPair').val();
            var ccy = $('#ccy').val();
            if(curPair.indexOf(ccy) > -1){
                showCorCcy();
            }else{
                $('#ccy').val('').trigger('change');
            }
            //货币对和交易货币账户，对应货币账户，交易货币可用持仓，对应货币可用持仓
            qryMoneyAndAcct();
            // 预计对应货币金额联动
            window.EtrForeingExchange.calcExpCorAmt('curPair','ccy','price','entrustAmt','expCorAmt',JPY_CNY);
        });
        /**
         * 交易货币
         */
        $('#ccy').change(function(){
            showCorCcy();
            qryMoneyAndAcct();
            // 预计对应货币金额联动
            window.EtrForeingExchange.calcExpCorAmt('curPair','ccy','price','entrustAmt','expCorAmt',JPY_CNY);
        });
        $('#price,#entrustAmt').keyup(function(){
            // 预计对应货币金额联动
            window.EtrForeingExchange.calcExpCorAmt('curPair','ccy','price','entrustAmt','expCorAmt',JPY_CNY);
        });
        /**
         * 组合代码
         */
        $('#portfolioId').change(function(){
            qryMoneyAndAcct();
        });
        /**
         * 委托开始日
         */
        $('#vdate').change(function(){
            var vdate = Date.ofDate($('#vdate').val());
            var mdate = Date.ofDate($("#mdate").val());
            if(vdate.getTime() == mdate.getTime()){
                $("#vdateAdd").val(vdate.format("yyyy-MM-dd")).attr("disabled",true).change();
                $("#mdateAdd").attr("disabled",false);
            }
            else{
                $("#vdateAdd").attr("disabled",false);
                $("#mdateAdd").attr("disabled",false);
            }
        });
        /**
         * 委托结束日
         */
        $('#mdate').change(function(){
            var vdate = Date.ofDate($('#vdate').val());
            var mdate = Date.ofDate($("#mdate").val());
            if(vdate.getTime() == mdate.getTime()){
                $("#vdateAdd").val(vdate.format("yyyy-MM-dd")).attr("disabled",true).change();
                $("#mdateAdd").attr("disabled",false);
            }else{
                $("#vdateAdd").attr("disabled",false);
                $("#mdateAdd").attr("disabled",false);
            }
        });
        init();
    });
}());

