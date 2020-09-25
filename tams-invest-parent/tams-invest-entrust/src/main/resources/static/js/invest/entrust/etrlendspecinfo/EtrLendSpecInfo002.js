/**
 * 拆解委托新建
 */
(function() {

    var getriskdata = function () {
        if($('#assetType1').val() == S02){
            var obj = {tradeDirection:$('#ps').val(),timePoint:MY_SUP_TIME_TYPE_02,valDate:sysDate,portfolioCode:$('#portfolioId').val(),tradeAmount:$('#entrustAmt').val(),assetType:fintype_cr,isEnabled:'01',assetCode:$('#finprodIdhid').val()}
        }
        else if($('#ps').val() == T32){
            var obj = {tradeDirection:$('#ps').val(),timePoint:MY_SUP_TIME_TYPE_02,valDate:sysDate,portfolioCode:$('#portfolioId').val(),tradeAmount:$('#entrustAmt').val(),assetType:fintype_cr,isEnabled:'01',assetCode:$('#finprodIdhid').val()}
        }else if($('#ps').val() == T33){
            var obj = {tradeDirection:$('#ps').val(),timePoint:MY_SUP_TIME_TYPE_02,valDate:sysDate,portfolioCode:$('#portfolioId').val(),tradeAmount:$('#entrustAmt').val(),assetType:fintype_cc,isEnabled:'01',assetCode:$('#finprodIdhid').val()}
        }else{
            var obj = {tradeDirection:$('#ps').val(),timePoint:MY_SUP_TIME_TYPE_02,valDate:sysDate,portfolioCode:$('#portfolioId').val(),tradeAmount:$('#entrustAmt').val(),assetType:fintype_cr,isEnabled:'01',assetCode:$('#finprodIdhid').val()}
        }
        var object = {tradeDirection:['ps','EtrLendSpecInfo002.ps'],portfolioCode:['portfolioId','EtrLendSpecInfo002.portfolioId'],tradeAmount:['entrustAmt','EtrLendSpecInfo002.entrustAmt'],assetType:['assetType1','EtrLendSpecInfo002.type6'],data:obj}
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
        object.inputValue1 = PINAN_ASSET_TYPE_F08;
        object.inputValue2 = $('#ps').val();
        Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/check', object, function(result1) {
        	Joyin.ajaxJson(basePath + 'invest/entrust/etrlendspecinfo/EtrLendSpecInfo002/checkAuthorization', object, function(result2) {
	            Joyin.ajaxJson(basePath + 'invest/entrust/etrlendspecinfo/EtrLendSpecInfo002/check',object,
	                function(result) {
	                    Joyin.confirm(result.data,function() {
	                        if (operateType === 'save') {
	                            WorkFlow.doSaveList(basePath+'invest/entrust/etrlendspecinfo/EtrLendSpecInfo002/save',object,"EtrLendSpecInfo002_btn_save");
	                        } else {
	                            WorkFlow.doSubmitList(basePath+'invest/entrust/etrlendspecinfo/EtrLendSpecInfo002/save',object,"EtrLendSpecInfo002_btn_submit");
	                        }
	                    });
                });
        	});
        });
    }
    /**
     * 判断是否节假日
     */
    var judgeIsHday = function(){
        object = {mdateAdd:$('#mdateAdd').val(),calId:$('#calendarId').val()}
        Joyin.ajaxJson(basePath + 'invest/entrust/etrlendspecinfo/EtrLendSpecInfo002/judgeIsHday',object,
          function(result) {
        });
    }
    /**
     * 组合可用现金
     */
    var proMoney = function(){
        var data = {portfolioId:$('#portfolioId').val(),ccy:$('#ccy').val(),calId:$('#calendarId').val()}
        Joyin.ajaxJson(basePath + 'invest/entrust/etrlendspecinfo/EtrLendSpecInfo002/getProAvailableCash',data,
                function(result) {
            $('#proAvailableCash').val(result.cashT).trigger('blur');
            $('#proAvailableCash1').val(result.cashT1).trigger('blur');
            $('#accAvailableCash').val(result.cashTG).trigger('blur');
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
            $('#assetType1').change();
            $('#ps').change();
            $('#vdate').change();
        }
        /**
         * 保存
         */
        $('#EtrLendSpecInfo002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        /**
         * 提交
         */
        $('#EtrLendSpecInfo002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        /**
         * 风险试算
         */
        $('#EtrLendSpecInfo002_btn_riskcal').click(function(){
            // window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
            var object = $.serializeToObject('#searchForm');
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/0/RSK002/F08',object,getriskdata());
        });
        /**
         * 拆借类型
         */
        $('#assetType1').change(function(){
        	if (pageModel != PAGE_MODEL_REFERENCE) {
	            if($('#assetType1').val()==S01){
	                $("#ps").prop("disabled",false).change();
	            }else{
	                $("#ps").prop("disabled",true).val(T32).change();
	            }
        	}
        });
        /**
         * 交易方向
         */
        $('#ps').change(function(){
            if($('#ps').val()==T33){
                $('#proAvailableCash_div').show();
                $('#proAvailableCash1_div').show();
                // $('#accAvailableCash_div').show();
                var portfolioId = $('#portfolioId').val();
                var ccy = $('#ccy').val();
                if(portfolioId && ccy){
                    proMoney();
                }
            }else{
                $('#proAvailableCash_div').hide();
                $('#proAvailableCash1_div').hide();
                // $('#accAvailableCash_div').hide();
            }
        });
        /**
         * 币种
         */
        $('#ccy').change(function(){
            var portfolioId = $('#portfolioId').val();
            var ccy = $('#ccy').val(); 
            var ps = $('#ps').val();
            if(portfolioId && ccy && ps == T33){
                proMoney();
            }
        });
        /**
         * 组合代码
         */
        $('#portfolioId').change(function(){
            var portfolioId = $('#portfolioId').val();
            var ccy = $('#ccy').val(); 
            var ps = $('#ps').val();
            if(portfolioId && ccy && ps == T33){
                proMoney();
            }
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
            	//若委托开始日和结束日不为同一天，起息日和到期日为空不可填  yzg 20190904 start
                $("#vdateAdd").attr("disabled",true);
                $("#mdateAdd").attr("disabled",true);
                $("#vdateAdd").val('');
                $("#mdateAdd").val('');
              //若委托开始日和结束日不为同一天，起息日和到期日为空不可填  yzg 20190904 end
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
            	//若委托开始日和结束日不为同一天，起息日和到期日为空不可填  yzg 20190904 start
                $("#vdateAdd").attr("disabled",true);
                $("#mdateAdd").attr("disabled",true);
                $("#vdateAdd").val('');
                $("#mdateAdd").val('');
                //若委托开始日和结束日不为同一天，起息日和到期日为空不可填  yzg 20190904 end
            }
        });
        /**
         * 到期日
         */
        $('#mdateAdd').change(function(){
           var vdate = Date.ofDate($('#vdate').val());
           var mdate = Date.ofDate($("#mdate").val());
           var vdateAdd = Date.ofDate($('#vdateAdd').val());
           var mdateAdd = Date.ofDate($("#mdateAdd").val());
           var calId = $('#calendarId').val();
           if(mdateAdd !=null && mdateAdd != '' && calId != null && calId != ''){
               judgeIsHday();
           }
           if(vdateAdd.getTime()>mdateAdd.getTime()){
               Joyin.error("到期日不能小于起息日");
               $("#mdateAdd").val(vdateAdd.format("yyyy-MM-dd"));
               $('#termDays').val(0);
               return;
           }
           if(vdateAdd != null && vdateAdd != '' && mdateAdd != null && mdateAdd != ''){
              var d1 = vdateAdd.getTime();
              var d2 = mdateAdd.getTime();
              var total = (d2-d1)/1000;
              var day = parseInt(total / (24*60*60));
              $('#termDays').val(day);
           }
        });
//        /**
//         * 起息日
//         */
//        $('#vdateAdd').change(function(){
//           var vdate = Date.ofDate($('#vdate').val());
//           var mdate = Date.ofDate($("#mdate").val());
//           var vdateAdd = Date.ofDate($('#vdateAdd').val());
//           var mdateAdd = Date.ofDate($("#mdateAdd").val());
//           if(vdateAdd.getTime()>mdateAdd.getTime()){
//               Joyin.error("起息日不能大于到期日");
//               $("#vdateAdd").val(mdateAdd.format("yyyy-MM-dd"));
//               $('#termDays').val(0);
//               return;
//           }
//           if(vdate.getTime() == mdate.getTime() && vdateAdd != null && vdateAdd != ''){
//              var d1 = vdateAdd.getTime();
//              var d2 = mdateAdd.getTime();
//              var total = (d2-d1)/1000;
//              var day = parseInt(total / (24*60*60));
//              $('#termDays').val(day);
//           }
//        });
        /**
         * 期限
         */
        $('#termDays').change(function(){
            var vdate = Date.ofDate($('#vdate').val());
            var mdate = Date.ofDate($("#mdate").val());
            var vdateAdd = Date.ofDate($('#vdateAdd').val());
            if(vdateAdd != null && vdateAdd != ''){
                var d1 = vdateAdd.getTime();
                var day = parseInt($('#termDays').val()*24*60*60*1000);
                var mdateAdd = Date.ofDate(d1+day);
                $('#mdateAdd').val(mdateAdd.format("yyyy-MM-dd"));
            }
        });
        
        $('#calendarId').change(function(){
            var mdateAdd = Date.ofDate($("#mdateAdd").val());
            var calId = $('#calendarId').val();
            var portfolioId = $('#portfolioId').val();
            var ccy = $('#ccy').val(); 
            var ps = $('#ps').val();
            if(mdateAdd !=null && mdateAdd != '' && calId != null && calId != ''){
                judgeIsHday();
            }
            if(portfolioId && ccy && ps == T33){
                proMoney();
            }
        });
        init();
    });
}());

