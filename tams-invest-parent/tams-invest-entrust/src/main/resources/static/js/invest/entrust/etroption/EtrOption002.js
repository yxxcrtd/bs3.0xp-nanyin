/**
 * 期权委托新建
 */
(function() {
    var getriskdata = function () {
        var obj = {tradeDirection:$('#ps').val(),timePoint:MY_SUP_TIME_TYPE_02,valDate:sysDate,portfolioCode:$('#portfolioId').val(),tradeAmount:$('#entrustNum').val(),assetType:fintype,assetSecType:$('#assetType1').val(),isEnabled:'01',assetCode:$('#finprodId').val()}
        var object = {tradeDirection:['ps','EtrOption002.ps'],portfolioCode:['portfolioId','EtrOption002.portfolioId'],tradeAmount:['entrustAmt','EtrOption002.entrustNum'],assetType:['assetType1','EtrOption002.assetType1'],assetCode:['finprodId','EtrOption002.contractSubMatter'],data:obj}
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
        object.termDays = $('#termDays').val().split('.')[0];
        object.ps = $('#ps').val();
        Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/check', object, function(result1) {
            Joyin.ajaxJson(basePath + 'invest/entrust/etroption/EtrOption002/check',object,
                function(result) {
                    Joyin.confirm(result.data,function() {
                        if (operateType === 'save') {
                            WorkFlow.doSaveList(basePath+'invest/entrust/etroption/EtrOption002/save',object,"EtrOption002_btn_save");
                        } else {
                            WorkFlow.doSubmitList(basePath+'invest/entrust/etroption/EtrOption002/save',object,"EtrOption002_btn_submit");
                        }
                    });
                });
        });
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
        
        var init = function(){
            $('#assetType11').change();
            $('#assetType10').change();
        }
        /**
         * 保存
         */
        $('#EtrOption002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        /**
         * 提交
         */
        $('#EtrOption002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        /**
         * 风险试算
         */
        $('#EtrOption002_btn_riskcal').click(function(){
            //window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
            var object = $.serializeToObject('#searchForm');
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/0/RSK002/F18',object,getriskdata());
        });

        $('#assetType10').on('change', function () {
            var assetType10 = $('#assetType10').val();
            var finprodId = $('#finprodId').val();
            if(assetType10){
                var data = $('#assetType10').select2('val');
                $('#finprodId').val(data).trigger('change');

            }
        });
        /**
         * 合约类型
         */
        $('#assetType11').change(function(){
               switch ($('#assetType11').val()) {
                   case "01":
                   case "02":
                   case "03":  assetType11Change([false,true,true,true,true,true]); break;
                   case "04":   assetType11Change([false,false,true,true,true,true]);  break;
                   case "05":   assetType11Change([true,true,false,false,false,false]);  break;
                   default: break;
               }
        });
        function assetType11Change(arr){
            var arr1=["#exePrice" ,"#knockPrice","#lowHindPrice","#lowExercPrice","#highHindPrice","#highExercPrice"];
            for (let i = 0; i < arr1.length; i++) {
                $(arr1[i]).prop("disabled",arr[i]).change().prop("value",arr[i]==true?"":$(arr1[i]).val());
            }
        }
        init();
    });
}());

