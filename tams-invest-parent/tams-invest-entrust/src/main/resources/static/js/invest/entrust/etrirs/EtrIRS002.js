/**
 * 利率互换投资委托新建
 */
(function() {

    var getriskdata = function () {
        var obj = {tradeDirection:'T01',timePoint:MY_SUP_TIME_TYPE_02,valDate:sysDate,portfolioCode:$('#portfolioId').val(),tradeAmount:$('#entrustAmt').val(),assetType:fintype_spotex,isEnabled:'01'}
        var object = {portfolioCode:['portfolioId','EtrIRS.portfolioId'],tradeAmount:['entrustAmt','EtrIRS.entrustAmt'],assetType:['assetType1','EtrIRS.type6'],data:obj}
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
        /*if(object.termType != null && object.termType != ''){
            object.termType = object.termType.join(",");
        }*/
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
//        object.ps = $('#ps').val();
        object.inputValue1 = PINAN_ASSET_TYPE_F16;
        Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/check', object, function(result1) {
        	Joyin.ajaxJson(basePath + 'invest/entrust/etrirs/EtrIRS002/checkAuthorization', object, function(result2) {
	            Joyin.ajaxJson(basePath + 'invest/entrust/etrirs/EtrIRS002/check',object,
	                function(result) {
	                    Joyin.confirm(result.data,function() {
	                        if (operateType === 'save') {
	                            WorkFlow.doSaveList(basePath+'invest/entrust/etrirs/EtrIRS002/save',object,"EtrIRS002_btn_save");
	                        } else {
	                            WorkFlow.doSubmitList(basePath+'invest/entrust/etrirs/EtrIRS002/save',object,"EtrIRS002_btn_submit");
	                        }
	                    });
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
            $('#portfolioId').change();
            
            //
            $('#vdate').change();
        }
        /**
         * 保存
         */
        $('#EtrIRS002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        /**
         * 提交
         */
        $('#EtrIRS002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        /**
         * 风险试算
         */
        $('#EtrIRS002_btn_riskcal').click(function(){
            //window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
            //-----------add by jjpei 2020-01-07 风险新版本 start----------
            var object = $.serializeToObject('#searchForm', true);
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/0/RSK002/F16', object,getriskdata());
            //-----------add by jjpei 2020-01-07 风险新版本 end----------
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

