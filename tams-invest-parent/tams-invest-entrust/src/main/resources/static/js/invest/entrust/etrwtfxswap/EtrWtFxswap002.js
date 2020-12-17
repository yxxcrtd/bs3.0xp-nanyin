/**
 * 外汇掉期委托新建
 */
(function() {

    //---add by jjpei 20190718----start---获取风险试算画面要素与Label提示文本
    var getRiskData = function () {
        var obj = {tradeDirection:$('#tradePs').val(),timePoint:MY_SUP_TIME_TYPE_01,valDate:sysDate,portfolioCode:$('#portfolioId').val(),tradeAmount:$('#tradeAmt').val(),assetType:fintype,isEnabled:USE_STATUS_01}
        var object = {tradeDirection:['tradePs','EtrWtFxswap002.tradePs'],portfolioCode:['portfolioId','EtrWtFxswap002.portfolioId'],tradeAmount:['tradeAmt','EtrWtFxswap002.tradeAmt'],data:obj}
        return object;
    }
    //---add by jjpei 20190718----end---获取风险试算画面要素与Label提示文本

    /**
     * 保存或提交
     * 
     * @param operateType 操作类型（save,submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        //----add by jjpei 20190718 风险试算----start------
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
        //----add by jjpei 20190718 风险试算----end------
        //交易方向
        var tradePs = $('#tradePs').val();
        //交易货币可用持仓
        var tradeCcyAvailablePosition = $('#tradeCcyAvailablePosition').val();
        //交易货币金额
        var tradeAmt = $('#tradeAmt').val();
        
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#searchForm');
        object.etrInvestPersonInfo.invManagerId = $('#etrInvestPersonInfo_invManagerId').val();
        object.operateType = operateType;
        object.pageModel = pageModel;
        object.entrustId = $('#entrustId').val();
        object.finprodId =$('#finprodIdhid').val();
        object.entrustStatus = $('#entrustStatus').val();
        object.finvdate = $('#finvdate').val();
        object.finmdate = $('#finmdate').val();
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
        object.inputValue1 = PINAN_ASSET_TYPE_F15;
        object.entrustAmt = object.tradeAmt;
        Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/check', object, function(result1) {
        	Joyin.ajaxJson(basePath + 'invest/entrust/etrwtfxswap/EtrWtFxswap002/checkAuthorization', object, function(result2) {
	            Joyin.ajaxJson(basePath + 'invest/entrust/etrwtfxswap/EtrWtFxswap002/check',object,
	                function(result) {
	                    //交易方向为卖/买,交易货币金额大于交易货币可用持仓时,进行提醒,但仍可以提交
	                    if(tradePs == tradePsValue && JoyinMath.doMinus(tradeAmt,tradeCcyAvailablePosition) > 0){
	                        Joyin.confirm(JoyinUtil.getMessage('BUSS_COM_E_0101'),function() {
	                            if (operateType === 'save') {
	                                WorkFlow.doSaveList(basePath+'invest/entrust/etrwtfxswap/EtrWtFxswap002/save',object,"EtrWtFxswap002_btn_save");
	                            } else {
	                                WorkFlow.doSubmitList(basePath+'invest/entrust/etrwtfxswap/EtrWtFxswap002/save',object,"EtrWtFxswap002_btn_submit");
	                            }
	                        });
	                    }else{
	                        Joyin.confirm(result.data,function() {
	                            if (operateType === 'save') {
	                                WorkFlow.doSaveList(basePath+'invest/entrust/etrwtfxswap/EtrWtFxswap002/save',object,"EtrWtFxswap002_btn_save");
	                            } else {
	                                WorkFlow.doSubmitList(basePath+'invest/entrust/etrwtfxswap/EtrWtFxswap002/save',object,"EtrWtFxswap002_btn_submit");
	                            }
	                        });
	                    }
	                });
        	});
        })
    }
    window.componentLoadCallback = function(isDone, pagePath, params) {
        if (isDone) {
            //----add by jjpei 20190718 风险试算----start------
            if(typeof(BaseRiskMathComm) != 'undefined') {
                var riskParam = getRiskData();
                if (pageModel == PAGE_MODEL_NEW) {
                    riskParam.data = null;
                }
                window.BaseRiskMathComm.getparam(riskParam);
            }
            //----add by jjpei 20190718 风险试算----end------
            init();
        }
    };
    
    /**
     * 查询交易货币可用持仓
     */
    function tradeCcyProAvailableCash(){
        var tradePs = $('#tradePs').val();
        if(tradePs == tradePsValue){
            var data = {portfolioId:$('#portfolioId').val(),ccy:$('#tradeCcy').val(),vdate:sysDate}
            if (!data.portfolioId || !data.ccy) {
                return;
            }
            Joyin.ajaxJson(basePath + 'invest/entrust/etrwtfxswap/EtrWtFxswap002/getProAvailableCash',data,
                    function(result) {
                $('#tradeCcyAvailablePosition').val(result).trigger('blur');
            }); 
        }
    }
    /**
     * 查询交易货币账户
     */
    function tradeCcyAcctData(){
        var tradeCcyAcctData = {portfolioId:$('#portfolioId').val(),ccy:$('#tradeCcy').val(),acctType:acctType}
        if (!tradeCcyAcctData.portfolioId || !tradeCcyAcctData.ccy) {
            return;
        }
        Joyin.ajaxJson(basePath + 'invest/entrust/etrwtfxswap/EtrWtFxswap002/selectPtlCapAcctNo',tradeCcyAcctData,
                function(result) {
            $("#tradeCcyAcct").empty();
            $.each(result,function(n,value) {  
                var op2 = document.createElement("option");
                $(op2).val(value.id);
                $(op2).text(value.text);
                $('#tradeCcyAcct').append(op2);
          }); 
        });
    }
    
    /**
     * 查询对应货币账户
     */
    function contraCcyAcctData(){
        var contraCcyAcctData = {portfolioId:$('#portfolioId').val(),ccy:$('#contraCcy').val(),acctType:acctType}
        if (!contraCcyAcctData.portfolioId || !contraCcyAcctData.ccy) {
            return;
        }
        Joyin.ajaxJson(basePath + 'invest/entrust/etrwtfxswap/EtrWtFxswap002/selectPtlCapAcctNo',contraCcyAcctData,
           function(result) {
            $("#contraCcyAcct").empty();
        $.each(result,function(n,value) {  
           var op2 = document.createElement("option");
           $(op2).val(value.id);
           $(op2).text(value.text);
           $('#contraCcyAcct').append(op2);
         }); 
       });
    }
   
    var init= function(){
        $('#termType').change();
    }
    /**
     * 绑定事件
     */
    $(function() {
        /**
         * 保存
         */
        $('#EtrWtFxswap002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        /**
         * 提交
         */
        $('#EtrWtFxswap002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        /**
         * 风险试算
         */
        $('#EtrWtFxswap002_btn_riskcal').click(function(){
            //window.BaseRiskMathComm.initBaseRiskMathCommPage(getRiskData());
            var object = $.serializeToObject('#searchForm');
            object.finprodId =$('#finprodIdhid').val();
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/0/RSK002/F15',object,getRiskData());
        });
        
        /**
         * 货币对和交易货币联动
         */
/*       $('#ccyPair').change(function(){
            $("#tradeCcy").empty();
            var opt = new Option("","");
            $('#tradeCcy').append(opt);
            var ccyPair = $('#ccyPair').val();
            var strs= new Array(); //定义一数组 
            strs=ccyPair.split("/");
            for(var i=0;i<strs.length;i++){
                //先创建好select里面的option元素
                var option = document.createElement("option");
                //转换DOM对象为JQ对象,好用JQ里面提供的方法 给option的value赋值
                $(option).val(strs[i]);
                //给option的text赋值,这就是你点开下拉框能够看到的东西
                $(option).text(strs[i]);
                //获取select 下拉框对象,并将option添加进select
                $('#tradeCcy').append(option);
            }
            $("#contraCcy").empty();
        });*/
        /**
         * 交易货币、对应货币联动
         */
        $('#tradeCcy').change(function(){
            if($('#ccyPair').val() == ""){
                $("#tradeCcy").empty();
                $("#contraCcy").empty();
                return;
            }
            var index=document.getElementById("tradeCcy").selectedIndex;
            var length=document.getElementById("tradeCcy").length;
            var tradeCcy=$('#tradeCcy').val();
            if(tradeCcy == ""){
               $("#contraCcy").empty();
            }
            var total=3;
            if(index != 0){
                $("#contraCcy").empty();
                if(length > 2){
                    var option = document.createElement("option");
                    $(option).val(document.getElementById("tradeCcy").options[total-index].value);
                    $(option).text(document.getElementById("tradeCcy").options[total-index].text);
                    $('#contraCcy').append(option);
                    $("#contraCcy option[value="+document.getElementById("tradeCcy").options[total-index].value+"]").prop("selected",true);
                }
                tradeCcyProAvailableCash();
                tradeCcyAcctData();
                contraCcyAcctData();
            }
          
        });
        /**
         * 交易方向为卖/买时带出交易可用持仓
         */
        $('#tradePs').change(function(){
            $('#tradeCcyAvailablePosition').val(null).trigger('blur');
            var tradePs = $('#tradePs').val();
           if(tradePs == tradePsValue){
               tradeCcyProAvailableCash();
           }
        });
        /**
         * 组合代码联动
         */
        $('#portfolioId').change(function(){
            tradeCcyAcctData();
            contraCcyAcctData();
            $('#tradeCcyAvailablePosition').val(null).trigger('blur');
            tradeCcyProAvailableCash();
        });
        /**
         * 期限类型与近端交割日、远端交割日联动
         */
        $('#termType').change(function(){
            $('#remoteDeliveryDate').attr('disabled',false).trigger('change');
            $('#proximalDeliveryDate').attr('disabled',false).trigger('change');
            var termType = $('#termType').val();
           if(termType == termType3 || termType == termType4 || termType == termType5){
               $('#remoteDeliveryDate').val(null).trigger('blur');
               $('#proximalDeliveryDate').val(null).trigger('blur');
               $('#remoteDeliveryDate').attr('disabled',true).trigger('change');
               $('#proximalDeliveryDate').attr('disabled',true).trigger('change');
               
           }else if(termType == termType2){
               $('#proximalDeliveryDate').val(null).trigger('blur');
               $('#proximalDeliveryDate').attr('disabled',true).trigger('change');
           }
        });
    });
}());