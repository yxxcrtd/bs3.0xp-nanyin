/**
 * 基金类标的信息新建
 */
(function () {

    //计算最后交割日
    var calMdate = function(){
        var vdate = $('#lastTradeDate').val();//最后交易日
        var futuresType = $("#futuresType").val();
        if(!futuresType){
            return;
        }
        if(futuresType == 'FU02'){
            $('#lastSettleDate').val(vdate);
        }
        else {
            var calendarId = $("#calendarId").val();
            if(vdate&&calendarId){
                var d1 = new Date(vdate).getTime();
                Joyin.ajax(basePath + 'invest/asset/futures/FinFutures002/getNextWorkDay', {
                    'calendarId': calendarId,
                    'tradeDate': vdate
                }, function (date) {
                    $("#lastSettleDate").val(date).trigger("change");
                });
            }else{
                $("#lastSettleDate").val('').trigger('change');
            }
        }
    }

    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#submitForm', false);
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.pageId = pageId;
        object.finprodType2 = finprodType2;
        if (object.finprodId) {
			object.finprodMarketId = object.futuresContractPre + object.contractCode + object.chlFinprodId.substr(object.chlFinprodId.indexOf('.'), object.chlFinprodId.length);
		} else {
			object.finprodMarketId = object.futuresContractPre + object.contractCode + object.chlFinprodId;
		}
        if(FileCommon) {
            object.fileArray = FileCommon.getUploadFileInfo();
            if(object.fileArray && _.isArray(object.fileArray)){
                $.each(object.fileArray, function(index,obj){
                    obj.seqNo = obj.fileNo;
                });
            }
        }
        Joyin.ajaxJson(basePath + 'invest/asset/futures/FinFutures002/check', object, function(result) {
            Joyin.confirm(result.data, function () {
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/asset/futures/FinFutures002/save', object, "FinFutures002_btn_save");
                } else {
                    WorkFlow.doSubmitList(basePath + 'invest/asset/futures/FinFutures002/save', object, "FinFutures002_btn_submit");
                }
            });
        })
    }

    var pageElementCtrl = function (){
        if (pageModel == PAGE_MODEL_EDIT){
            $("#futuresContractPre").attr("disabled",true);
            $("#contractCode").attr("disabled",true);
        }
        if("Y" == isTransInAsset){
            $("#futuresContractId").attr("disabled",true);
            $("#tradeMarket").attr("disabled",true);
            $("#deliveryMethod").attr("disabled",true);
            $("#remark").attr("disabled",true);
            $("#multFileBtn").attr("disabled",true);
        }
    }

    /**
     * 画面加载完成
     */
    $(function () {
        $("#tradeMarket").change(function () {
            var tradeMarket = $("#tradeMarket").val();
            if(tradeMarket == TRADE_MARKET_12){
                $("#chlFinprodId").val(".CFFEX");
            }
            else {
                $("#chlFinprodId").val("");
            }
        });
        
        var pre = $('#futuresContractPre').val();
        if(pre){
        	if(pre == 'TS'){
            	$("#contractMultiplier").val("20000");
            }
            else if(pre == 'TF'){
            	$("#contractMultiplier").val("10000");
            }
            else if(pre == 'T'){
            	$("#contractMultiplier").val("10000");
            }
            else if(pre == 'IF'){
            	$("#contractMultiplier").val("300");
            }
            else if(pre == 'IC'){
            	$("#contractMultiplier").val("200");
            }
            else if(pre == 'IH'){
            	$("#contractMultiplier").val("300");
            }
        	$("#contractMultiplier").trigger("change");
        }
        $("#contractMultiplier").trigger("change");
        
        $("#futuresContractPre").change(function () {
            var prefix = $('#futuresContractPre').val();
            if(!prefix){
                $("#futuresType").textSelect('setValue', '');
                $("#futuresContractId").textSelect('setValue', '');
                $("#contractMultiplier").val("");
                $("#tradeMarket").textSelect('setValue', '');
            }
            else if(prefix == 'TS'){
            	$("#futuresContractId").textSelect('setValue', '01');//2年期国债
            	$("#futuresType").textSelect('setValue', 'FU01');//国债期货
            	$("#contractMultiplier").val("20000");
            	$("#tradeMarket").textSelect('setValue',TRADE_MARKET_12);// 中国金融期货交易所
            }
            else if(prefix == 'TF'){
            	$("#futuresContractId").textSelect('setValue', '02');//5年期国债
            	$("#futuresType").textSelect('setValue', 'FU01');//国债期货
            	$("#contractMultiplier").val("10000");
            	$("#tradeMarket").textSelect('setValue',TRADE_MARKET_12);// 中国金融期货交易所
            }
            else if(prefix == 'T'){
            	$("#futuresContractId").textSelect('setValue', '03');//10年期国债
            	$("#futuresType").textSelect('setValue', 'FU01');//国债期货
            	$("#contractMultiplier").val("10000");
            	$("#tradeMarket").textSelect('setValue',TRADE_MARKET_12);// 中国金融期货交易所
            }
            else if(prefix == 'IF'){
            	$("#futuresContractId").textSelect('setValue', '04');//沪深300指数
            	$("#futuresType").textSelect('setValue', 'FU02');//股指期货
            	$("#contractMultiplier").val("300");
            	$("#tradeMarket").textSelect('setValue',TRADE_MARKET_12);// 中国金融期货交易所
            }
            else if(prefix == 'IC'){
            	$("#futuresContractId").textSelect('setValue', '05');//中证500指数
            	$("#futuresType").textSelect('setValue', 'FU02');//股指期货
            	$("#contractMultiplier").val("200");
            	$("#tradeMarket").textSelect('setValue',TRADE_MARKET_12);// 中国金融期货交易所
            }
            else if(prefix == 'IH'){
            	$("#futuresContractId").textSelect('setValue', '06');//上证50指数
            	$("#futuresType").textSelect('setValue', 'FU02');//股指期货
            	$("#contractMultiplier").val("300");
            	$("#tradeMarket").textSelect('setValue',TRADE_MARKET_12);// 中国金融期货交易所
            }
            $("#futuresType").trigger("change");
            $("#futuresContractId").trigger("change");
            $("#contractMultiplier").trigger("change");
            $("#tradeMarket").trigger("change");
            /*if(prefix =='TF' || prefix == 'TS' || prefix == 'T' || prefix == 'IC' || prefix == 'IH' || prefix == 'IF' ){
                $("#tradeMarket").textSelect('setValue',TRADE_MARKET_12);// 中国金融期货交易所
            }
            else {
                $("#tradeMarket").textSelect('setValue', '');
                $("#tradeMarket").trigger("change");
            }*/
        });
        
        $("#lastTradeDate").change(function(){
            calMdate();
        });

        // 绑定按钮事件
        $('#FinFutures002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#FinFutures002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });

        pageElementCtrl()
    });
}());
