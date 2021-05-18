/**
 * 权益类资产委托新建
 */
(function() {

    var getriskdata = function () {
        var entrustAmt = $('#entrustAmt').val();
        var entrustNum = $('#entrustNum').val();
        if(entrustAmt){
            var obj = {tradeDirection:$('#ps').val(),timePoint:MY_SUP_TIME_TYPE_02,valDate:sysDate,portfolioCode:$('#portfolioId').val(),tradeAmount:$('#entrustAmt').val(),assetType:fintype,isEnabled:'01',assetCode:$('#finprodIdhid').val()}
            //---add by 裴家俊  2019/03/20  添加资产类型条件---start-----
            if($('#assetType2').val() && $('#assetType2').val() != ''){
                obj.assetSecType = $('#assetType2').val();
            }
            // add by zhangrui1 交易对手影响风险试算 start
            obj.counterId = $('#etrInvestPersonInfo_counterId').val();
            // add by zhangrui1 交易对手影响风险试算 end
            //---add by 裴家俊  2019/03/20  添加资产类型条件---end-----
            var object = {tradeDirection:['ps','EtrProjectNet002.ps'],portfolioCode:['portfolioId','EtrProjectNet002.portfolioId'],tradeAmount:['entrustAmt','EtrProjectNet002.entrustAmt'],assetType:['assetType1','EtrProjectNet002.assetType1'],data:obj}
        }else if(entrustNum){
            var obj = {tradeDirection:$('#ps').val(),timePoint:MY_SUP_TIME_TYPE_02,valDate:sysDate,portfolioCode:$('#portfolioId').val(),tradeAmount:$('#entrustNum').val(),assetType:fintype,isEnabled:'01',assetCode:$('#finprodIdhid').val(),amtType:'1'}
            //---add by 裴家俊  2019/03/20  添加资产类型条件---start-----
            if($('#assetType2').val() && $('#assetType2').val() != ''){
                obj.assetSecType = $('#assetType2').val();
            }
            // add by zhangrui1 交易对手影响风险试算 start
            obj.counterId = $('#etrInvestPersonInfo_counterId').val();
            // add by zhangrui1 交易对手影响风险试算 end
            //---add by 裴家俊  2019/03/20  添加资产类型条件---end-----
            var object = {tradeDirection:['ps','EtrProjectNet002.ps'],portfolioCode:['portfolioId','EtrProjectNet002.portfolioId'],tradeAmount:['entrustNum','EtrProjectNet002.entrustNum'],assetType:['assetType1','EtrProjectNet002.assetType1'],data:obj}
        }else{
            var obj = {tradeDirection:$('#ps').val(),timePoint:MY_SUP_TIME_TYPE_02,valDate:sysDate,portfolioCode:$('#portfolioId').val(),tradeAmount:$('#entrustAmt').val(),assetType:fintype,isEnabled:'01',assetCode:$('#finprodIdhid').val()}
            //---add by 裴家俊  2019/03/20  添加资产类型条件---start-----
            if($('#assetType2').val() && $('#assetType2').val() != ''){
                obj.assetSecType = $('#assetType2').val();
            }
            // add by zhangrui1 交易对手影响风险试算 start
            obj.counterId = $('#etrInvestPersonInfo_counterId').val();
            // add by zhangrui1 交易对手影响风险试算 end
            //---add by 裴家俊  2019/03/20  添加资产类型条件---end-----
            var object = {tradeDirection:['ps','EtrProjectNet002.ps'],portfolioCode:['portfolioId','EtrProjectNet002.portfolioId'],tradeAmount:$('#ps').val()==T01||$('#ps').val()==T02||$('#ps').val()==T06?['entrustNum','EtrProjectNet002.entrustNum']:['entrustAmt','EtrProjectNet002.entrustAmt'],assetType:['assetType1','EtrProjectNet002.assetType1'],data:obj}
        }
        // add by zhangrui1 交易对手影响风险试算 start
        object.counterId = ['counterId', 'EtrInvestPersonInfo.counterId'];
        // add by zhangrui1 交易对手影响风险试算 end
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
        object.finmdate = $('#mdate').val();
        object.entrustStatus = $('#entrustStatus').val();
        if ($('#etrInvestPersonInfo_counterId').select2('data') && $('#etrInvestPersonInfo_counterId').select2('data')[0]) {
            object.counterType = $('#etrInvestPersonInfo_counterId').select2('data')[0].object;;
        }
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
        Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/check', object, function(result1) {
            Joyin.ajaxJson(basePath + 'invest/entrust/etrprojectnet/EtrProjectNet002/check',object,
                function(result) {
                    Joyin.confirm(result.data,function() {
                        if (operateType === 'save') {
                            WorkFlow.doSaveList(basePath+'invest/entrust/etrprojectnet/EtrProjectNet002/save',object,"EtrProjectNet002_btn_save");
                        } else {
                            WorkFlow.doSubmitList(basePath+'invest/entrust/etrprojectnet/EtrProjectNet002/save',object,"EtrProjectNet002_btn_submit");
                        }
                    });
                });
        });
    }
    //查询组合可用现金
    var getPtlCashPositionInfo = function(){
        var data = {portfolioId:$('#portfolioId').val(),ccy:$('#ccy').val(),vdate:$('#vdate').val(),calId:$('#calendarId').val(), chlId: $("#chlId").val()}
        Joyin.ajaxJson(basePath + 'invest/entrust/etrprojectnet/EtrProjectNet002/getProAvailableCash',data,
            function(result) {
                $('#proAvailableCash').val(result.cashT).trigger('blur');
                //   $('#proAvailableCash1').val(result.cashT1).trigger('blur');
                $('#accAvailableCash').val(result.cashTG).trigger('blur');
                $('#channelAvailableCash').val(result.cashTD).trigger('blur');
            });
    }
    //查询组合可用持仓
    var getPtlSecPositionInfo = function(){
        var data = {portfolioId:$('#portfolioId').val(),finprodId:$('#finprodIdhid').val(),ccy:$('#ccy').val(),secAcctId:$("#chlId").val()}
        Joyin.ajaxJson(basePath + 'invest/entrust/etrprojectnet/EtrProjectNet002/getAssetAvailablePosition',data,
            function(result) {
                $('#assetAvailablePosition').val(result).trigger('blur');;
            });
    }
    var init = function(){
        if(pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_REFERENCE) {
            var ps= $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var vdate = $('#vdate').val();
            var finprodId = $('#finprodIdhid').val();
            
            if(ps == T04 || ps == T01){    
                if(ps == T01) {
                    $('#proAvailableCash_div').hide();
                }else {
                    $('#proAvailableCash_div').show();
                }
    
                $('#div_channelCash').hide();
                if(ps == T01){
                    $('#entrustAmt').attr('disabled',true);
                    $('#entrustAmt').attr('required',false);
                    $('#entrustNum').attr('disabled',false);
                    $('#entrustNum').attr('required',true);
                    $('#entrustAmt').val("");
                }
               else{
                    $('#entrustAmt').attr('disabled',false);
                    $('#entrustAmt').attr('required',true);
                    $('#entrustNum').attr('disabled',true);
                    $('#entrustNum').attr('required',false);
                }
                $('#assetAvailablePosition_div').hide();
            }
            else if(ps == T06 || ps == T02){
                $('#proAvailableCash_div').hide();
                $('#div_channelCash').hide();
                $('#entrustNum').attr('disabled',false);
                $('#assetAvailablePosition_div').show();
                $('#entrustAmt').attr('disabled',true);
                $('#entrustAmt').attr('required',false);
            }

            if (ps == T04 || ps == T06){
                $("#etrInvestPersonInfo_counterId").attr('required',false);
            } else  if (ps == T01 || ps == T02){
                $("#etrInvestPersonInfo_counterId").attr('required',true);
            }
            
         
            star();
        }
        
        if($('#finprodId').val() != null && $('#finprodId').val() != ''){
            $('#finprodId').change();
        }
    }
    /**
     * 画星星
     */
    var star = function() {
        // 加红星星
        // 查找label
        var _findPrevLabel = function(element, level) {
            var $el = element.prev();
            if (!$el.is('label') && level > 0) {
                $el = $el.find('label');
                if ($el.length === 0) {
                    return _findPrevLabel(element.parent(), level - 1);
                }
            }
            return $el;
        };

        // 非查看模式
        if (typeof pageModel !== 'undefined'
            && (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_NEW)) {
            $('.must').each(function() {
                $(this).remove();
            })
            $('*[required]').each(function() {
                var $el = $(this);
                var $target = _findPrevLabel($el, 3);
                if ($target.find('> .must').length === 0) {
                    $target.append($('<i class="must">*</i>'));
                }
            });
        }
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

        /**
         * 保存
         */
        $('#EtrProjectNet002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        /**
         * 提交
         */
        $('#EtrProjectNet002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        /**
         * 风险试算
         */
        $('#EtrProjectNet002_btn_riskcal').click(function(){
            // window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
            var object = $.serializeToObject('#searchForm');
            /* del by zhangrui1 start
            if($('#entrustNum').val()) {
                object.entrustAmt =$('#entrustNum').val();//把份额直接赋值给金额
            }
             * del by zhangrui1 end */
            object.finprodId =$('#finprodIdhid').val();
            object.finmdate = $('#mdate').val();
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/0/RSK002/F19',object,getriskdata());
        });
        /**
         * 交易方向
         */
        $('#ps').change(function() {
            var ps= $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var vdate = $('#vdate').val();
            var finprodId = $('#finprodIdhid').val();
            if(ps == T04 || ps == T01){
                if(ps == T01) {
                    $('#proAvailableCash_div').show();
                }else {
                    $('#proAvailableCash_div').hide();
                }


                $('#div_channelCash').hide();
                //$('#proAvailableCash1_div').show();
                //update by  zhangnian  2019115 start BSTOZ-1261
                /*$('#accAvailableCash_div').show();*/
                //update by  zhagnnain  20191115  end
                if(ps == T01){
                    $('#entrustAmt').attr('disabled',true);
                    $('#entrustAmt').attr('required',false);
                    /*$('.entrustAmt').show();*/
                    $('#entrustNum').attr('disabled',false);
                    $('#entrustNum').attr('required',true);
                    /*$('.entrustNum').hide();*/
                    $('#entrustAmt').val("");
                }
               else{
                    $('#entrustAmt').attr('disabled',false);
                    $('#entrustAmt').attr('required',true);
                    /*$('.entrustAmt').show();*/
                    $('#entrustNum').attr('disabled',true);
                    $('#entrustNum').attr('required',false);
                    /*$('.entrustNum').hide();*/
                    $('#entrustNum').val("");
                }
                $('#assetAvailablePosition_div').hide();
                if(ccy && portfolioId && vdate){
                    getPtlCashPositionInfo();
                }
            }else if(ps == T06 || ps == T02){
                $('#proAvailableCash_div').hide();
                $('#div_channelCash').hide();
                // $('#proAvailableCash1_div').hide();
                // $('#accAvailableCash_div').hide();
                $('#entrustNum').attr('disabled',false);
                $('#assetAvailablePosition_div').show();
                $('#entrustAmt').change();
                $('#entrustNum').change();
                /*$('.entrustNum').show();*/
                $('#entrustAmt').attr('disabled',true);
                $('#entrustAmt').val("");
                $('#entrustAmt').attr('required',false);
               /* $('.entrustAmt').hide();*/
                if(portfolioId && finprodId){
                    getPtlSecPositionInfo();
                }
            }
            if (ps == T04 || ps == T06){
                $("#etrInvestPersonInfo_counterId").attr('required',false);
            }
            if (ps == T01 || ps == T02){
                $("#etrInvestPersonInfo_counterId").attr('required',true);
            }
            // 画星星
            star();
            $("#etrInvestPersonInfo_counterId").empty();
            getUnitCprice();
        });
        /**
         * 资产代码
         */
        $('#finprodId').on('change', function () {
            var ps= $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var vdate = $('#vdate').val();
            var finprodIdhid = $('#finprodIdhid').val();
            var finprodId;
            if($('#finprodId').val() && $('#finprodId').select2('data') && $('#finprodId').select2('data')[0]){
                var data = $('#finprodId').select2('data')[0].object;
                finprodId = data.finprodId;
                $('#finprodIdhid').val(data.finprodId).trigger('change');
                $('#finprodName').val(data.finprodId).trigger('change');
                $('#finprodNameAdd').val($('#finprodId').val()).trigger('change');
                $('#calendarId').val(data.calendarId).trigger('change');
                $('#finvdate').val(new Date(data.finvdate)).trigger('change');
                $('#finmdate').val(new Date(data.finmdate)).trigger('change');
                $('#ccy').val(data.ccy).attr('disabled',true).trigger('change');
                $('#assetType1').val(data.assetType1).trigger('change');
                /* add by zhangpuyu 2019/7/30 start*/
                $('#assetType10').val(data.assetType10).trigger('change');
                $('#assetType11').val(data.assetType11).trigger('change');
                /* add by zhangpuyu 2019/7/30 end*/
                $('#assetType2').val(data.assetType2).trigger('change').change();
                $('#purSpeed').val(data.purSpeed).trigger('change');
                $('#redSpeed').val(data.redSpeed).trigger('change');
            }else{
                $('#finprodIdhid').val('').trigger('change');
                $('#finprodName').val('').trigger('change');
                $('#finprodNameAdd').val('').trigger('change');
                $('#calendarId').val('').trigger('change');
                $('#finvdate').val('').trigger('change');
                $('#finmdate').val('').trigger('change');
                $('#ccy').val('').attr('disabled',false).trigger('change');
                $('#assetType1').val('').trigger('change');
                $('#assetType2').val('').trigger('change').change();
                $('#purSpeed').val('').trigger('change');
                $('#redSpeed').val('').trigger('change');
            }
            getUnitCprice();
            ChannelAssetCommon.setChannelSelectObject($("#chlId"), finprodId, false, function () {

            });
        });

        $("#chlId").change(function () {
            var ps= $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var vdate = $('#vdate').val();

            if((ps == T04 || ps == T01) && ccy && portfolioId && vdate){
                getPtlCashPositionInfo();
            }
            if((ps == T06 || ps == T02) && portfolioId){
                getPtlSecPositionInfo();
            }
        })

        /**
         * 份额和净值  计算金额
         */
        var calEntrustAmt = function(unitCprice){
            var ps = $('#ps').val();
            var entrustNum = $('#entrustNum').val();
            if(ps == T01 || ps == T02){
                if(unitCprice && entrustNum){
                    $('#entrustAmt').val(unitCprice*entrustNum).trigger('blur');
                }
            }
        }

        /**
         * 获取净值
         */
        var getUnitCprice = function(){
            var netDate = $('#vdate').val();
            var finprodId = $('#finprodName').val();
            if(netDate && finprodId){
                var data = {finprodId:finprodId,netDate:netDate}
                Joyin.ajaxJson(basePath + 'invest/trade/projectnet/TrdProjectNetInvestTrade003/getPrice', data , function(result) {
                    if (result.success) {
                        calEntrustAmt(result.price)
                    }
                    else {
                       /* if(pageModel != PAGE_MODEL_REFERENCE){
                            Joyin.error("所选净值日无净值, 默认按1处理, 请维护净值后重新交易");
                        }*/
                        calEntrustAmt(1);
                    }
                });
            }
        }
        /**
         * 委托金额
         */
        $('#entrustAmt').change(function(){
            var ps= $('#ps').val();
            if(ps == T04){
                $('#entrustNum').attr('required',false);
            }
            else{
                $('#entrustNum').attr('required',true);
            }
            if((ps == T06 || ps == T02)){
                if($('#entrustAmt').val()){
                    $('#entrustNum').val("");

                }
            }
            // 画星星
            star();
        });
        /**
         * 委托份额
         */
        $('#entrustNum').change(function(){
            var ps= $('#ps').val();
            var entrustNum = $('#entrustNum').val();
            if((ps == T06 || ps == T02||ps == T01)){
                $('#entrustAmt').val("");
                $('#entrustAmt').attr('required',false);
                /*if(entrustNum){
                   /!* $('#entrustAmt').attr("disabled",true);*!/
                  /!*  $('#entrustAmt').val("");*!/
                    $('#entrustAmt').attr('required',false);
                }else{
                   /!* $('#entrustAmt').attr("disabled",false);*!/
                    /!*$('#entrustAmt').attr('required',true);*!/
                }*/
            }
            getUnitCprice();
            // 画星星
            star();
        });


        $('#ccy').change(function(){
            var ps= $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var vdate = $('#vdate').val();
            var finprodId = $('#finprodIdhid').val();
            if((ps == T04 || ps == T01) && ccy && portfolioId && vdate){
                getPtlCashPositionInfo();
            }
            if((ps == T06 || ps == T02) && ccy && portfolioId && finprodId){
                getPtlSecPositionInfo();
            }
        });

        $('#portfolioId').change(function(){
            var ps= $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var vdate = $('#vdate').val();
            var finprodId = $('#finprodIdhid').val();
            if((ps == T04 || ps == T01) && ccy && portfolioId && vdate){
                getPtlCashPositionInfo();
            }else if((ps == T06 || ps == T02) && portfolioId && finprodId){
                getPtlSecPositionInfo();
            }
        });

        $('#vdate').change(function(){
            var ps= $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var vdate = $('#vdate').val();
            var finprodId = $('#finprodIdhid').val();
            if((ps == T04 || ps == T01) && ccy && portfolioId && vdate){
                getPtlCashPositionInfo();
            }
        });
        //是否展示资产四五级分类
        if(IS_SHOW_TYPE == '01'){
            $('#assetType11Div').show();
        }else{
            $('#assetType11Div').hide();
        }
        init();
        // 画星星
        star();
    });
}());
