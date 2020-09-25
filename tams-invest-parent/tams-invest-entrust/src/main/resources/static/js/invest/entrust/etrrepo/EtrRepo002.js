/**
 * 回购投资委托新建
 */
(function() {

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
    var getriskdata = function () {
        if($('#ps').val() == ps_03){
            var obj = {tradeDirection:$('#ps').val(),timePoint:MY_SUP_TIME_TYPE_02,valDate:sysDate,portfolioCode:$('#portfolioId').val(),tradeAmount:$('#entrustAmt').val(),assetType:fintype_zhg,isEnabled:'01'}
        }else if($('#ps').val() == ps_04){
            var obj = {tradeDirection:$('#ps').val(),timePoint:MY_SUP_TIME_TYPE_02,valDate:sysDate,portfolioCode:$('#portfolioId').val(),tradeAmount:$('#entrustAmt').val(),assetType:fintype_nhg,isEnabled:'01'}
        }else{
            var obj = {tradeDirection:$('#ps').val(),timePoint:MY_SUP_TIME_TYPE_02,valDate:sysDate,portfolioCode:$('#portfolioId').val(),tradeAmount:$('#entrustAmt').val(),assetType:fintype_zhg,isEnabled:'01'}
        }
        var object = {tradeDirection:['ps','EtrRepo002.ps'],portfolioCode:['portfolioId','EtrRepo002.portfolioId'],tradeAmount:['entrustAmt','EtrRepo002.entrustAmt'],assetType:['assetType1','EtrRepo002.assetType1'],data:obj}
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

        if(!$('#securitiesCust').val()&&!$('#chlId').val()){
            Joyin.warn("证券账户和通道不能同时为空！");
            return;
        }
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#searchForm');
        object.etrInvestPersonInfo.invManagerId = $('#etrInvestPersonInfo_invManagerId').val();
        object.operateType = operateType;
		object.assetType1 = $('#assetType1').val();
        object.pageModel = pageModel;
        object.entrustId = $('#entrustId').val();
        object.entrustStatus = $('#entrustStatus').val();
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
        object.inputValue1 = PINAN_ASSET_TYPE_F07;
        object.inputValue2 = $('#assetType1').val();
        object.inputValue3 = $('#ps').val();
        Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/check', object, function(result1) {
        	Joyin.ajaxJson(basePath + 'invest/entrust/etrrepo/EtrRepo002/checkAuthorization', object, function(result2) {
	            Joyin.ajaxJson(basePath + 'invest/entrust/etrrepo/EtrRepo002/check',object,
	                function(result) {
	                    Joyin.confirm(result.data,function() {
	                        if (operateType === 'save') {
	                            WorkFlow.doSaveList(basePath+'invest/entrust/etrrepo/EtrRepo002/save',object,"EtrRepo002_btn_save");
	                        } else {
	                            WorkFlow.doSubmitList(basePath+'invest/entrust/etrrepo/EtrRepo002/save',object,"EtrRepo002_btn_submit");
	                        }
	                    });
	                });
        	});
        });
    }
  //查询组合可用现金
    var getPtlCashPositionInfo = function(){
        var data = {portfolioId:$('#portfolioId').val(),ccy:$('#ccy').val(),vdate:$('#vdate').val(),calId:$('#calendarId').val()}
        Joyin.ajaxJson(basePath + 'invest/entrust/etrrepo/EtrRepo002/getProAvailableCash',data,
                function(result) {
            $('#proAvailableCash').val(result.cashT).trigger('blur');
            $('#proAvailableCash1').val(result.cashT1).trigger('blur');
            $('#accAvailableCash').val(result.cashT3).trigger('blur');
        });
    }
    //查询标准券额度
    var getPtlSecPositionInfo = function(){
        if (!$('#portfolioId').val() || !$("#securitiesCust").val() || !$("#ccy").val() || !$("#vdate").val()) {
            return;
        }
        var data = {portfolioId:$('#portfolioId').val(),manageId:$("#securitiesCust").val(),vdate:$("#vdate").val(), ccy: $("#ccy").val()}
        Joyin.ajaxJson(basePath + 'invest/entrust/etrrepo/EtrRepo002/selectStandardAmt',data,
                function(result) {
            $('#assetAvailablePosition').val(result).trigger('blur');
        });
    }
    /**
     * 判断是否节假日
     */
    var judgeIsHday = function(){
        object = {mdateAdd:$('#mdateAdd').val(),calId:$('#calendarId').val()}
        Joyin.ajaxJson(basePath + 'invest/entrust/etrrepo/EtrRepo002/judgeIsHday',object,
          function(result) {
            if(result.msg){
                Joyin.alert(result.msg);
                $('#mdateAdd').val(result.mdateAdd2);
            }
        });
    }
    /**
     * 弹出添加画面
     * @returns
     */
    function showDialog() {
        Joyin.dialog(JoyinUtil.getLabel('EtrRepo002.addPledge'), '../TrdRepoTrade003/init', '90%', '90%');
    }
    /**
     * 设置添加质押券共通参数
     */
    var setBussRepoSecInput = function () {
        var data = {
            portfolioId: $('#portfolioId').val(),
            securitiesCust: $('#securitiesCust').val(),
            chlId: $('#chlId').val(),
            assetType1: $('#assetType1').val(),
            ps: $("#ps").val()
            // add 自动导入质押券参数 lizhi start
            ,vdate: $("#vdate").val()
            ,entrustAmt: $("#entrustAmt").val()
            // add 自动导入质押券参数 lizhi end
        }
        var object = {
            portfolioId: ['portfolioId', 'EtrRepo002.portfolioId'],
            ps: ['ps', 'EtrRepo002.ps'],
            securitiesCust: ['securitiesCust', 'EtrRepo002.securitiesCust'],
            chlId: ['chlId','invest.channelId'],
            assetType1: ['assetType1','EtrRepo002.assetType1'],
            // add 自动导入质押券参数 lizhi start
            vdate: ['vdate','EtrRepo002.vdate'],
            entrustAmt: ['entrustAmt','EtrRepo002.entrustAmt'],
            // add 自动导入质押券参数 lizhi end
            data: data
        }
        window.BussRepoSec["bussRepoSec"].setInputParam(object);
    };
    
    window.componentLoadCallback = function (isDone, pagePath, params) {
        if (isDone) {
         // 设置添加质押券共通参数
            setBussRepoSecInput();
            $('#ps').change();
            $('#finprodId').change();
            $('#tradeMarket').trigger("change", true);
            var riskparam = getriskdata();
            if (pageModel==PAGE_MODEL_NEW){
                riskparam.data =null;
        }
            window.BaseRiskMathComm.getparam(riskparam);
        }
    };

    // 进入流程/编辑时 显示通道剩余现金 add by 言伟 2019/03/28 Start
    if (payType == '02'){
        $('#chlId_value_div').show()
        if (cash)
            $('#channelAvailableCash').val(cash)
    }
    // 进入流程/编辑时 显示通道剩余现金 add by 言伟 2019/03/28 End


    /**
     * 绑定事件
     */
    $(function() {
    	// 打开页面的时候如果是吉林银行并且交易场所为银行间则价格模式显示限价指令
    	// add by ZhouYu 2019-12-19 start
    	if($('#tradeMarket').val() == BANK_MARKET && BANK_CODE == '004'){
    		$('#priceModel').val(BANK_PRICE_MODEL).trigger('change');
    	}
    	// add by ZhouYu 2019-12-19 end
        /**
         * 保存
         */
        $('#EtrRepo002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        /**
         * 提交
         */
        $('#EtrRepo002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        /**
         * 风险试算
         */
        $('#EtrRepo002_btn_riskcal').click(function(){
            // window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
            var object = $.serializeToObject('#searchForm');
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/0/RSK002/F07',object,getriskdata());
        });

        // 通道交易 新增 言伟 2019/03/28 start
        $("#chlId").change(function(){
        	if ($("#chlId").val()) {
                if(pageModel==PAGE_MODEL_NEW) {
                    $("#securitiesCust").val('').trigger("change").prop("disabled", true);
                }
        	}
        	else {
        		$("#securitiesCust").prop("disabled", false);
        	}
            if (this.value)
                return queryChlCash({finprodId: $('#portfolioId').val(),chlId: this.value});
            $("#chlId_value_div").hide();
            setBussRepoSecInput();
        })
        function queryChlCash(object){
            Joyin.ajaxJson(basePath + 'invest/entrust/etrrepo/EtrRepo002/queryChlCash', object,
                function(result) {
                    if (result.payType == '02'){
                        $('#channelAvailableCash').val(result.cash)
                        $("#chlId_value_div").show();
                        setBussRepoSecInput();
                        return;
                    }
                    $("#chlId_value_div").hide();
                    setBussRepoSecInput();
                    // if(result.msg){
                    //     Joyin.alert(result.msg);
                    //     $('#mdateAdd').val(result.mdateAdd2);
                    // }
                });
            }
        // 通道交易 新增 言伟 2019/03/28 End

        /**
         * 交易方向
         */
        $('#ps').change(function() {
            var ps= $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var calendarId = $("#calendarId").val();
            var vdate = $('#vdate').val();
            var securitiesCust = $('#securitiesCust').val();
                if(ps == ps_04){
                    $('#proAvailableCash_div').show();
                    $('#proAvailableCash1_div').show();
                    $('#accAvailableCash_div').show();
//                    $('#entrustAmt').attr('disabled',false);
//                    $('#entrustNum').attr('disabled',true);
                    $('#assetAvailablePosition_div').hide();
                    if(ccy && portfolioId && vdate){
                        getPtlCashPositionInfo();
                    }
                    $('#bussRepoSec_main').show();
                }else if(ps == ps_03){
                    $('#proAvailableCash_div').hide();
                    $('#proAvailableCash1_div').hide();
                    $('#accAvailableCash_div').hide();
//                    $('#entrustAmt').attr('disabled',true);
//                    $('#entrustNum').attr('disabled',false);
                    if(portfolioId && securitiesCust){
                        getPtlSecPositionInfo();
                    }
                    if ($('#assetType1').val() == repoTypeH02) {
                    	$('#assetAvailablePosition_div').show();
                    } else {
                    	$('#assetAvailablePosition_div').hide();
                    }
                }
            if ($('#assetType1').val() == repoTypeH02) {
                $('#bussRepoSec_main').hide();
            } else {
                $('#bussRepoSec_main').show();
            }
                setBussRepoSecInput();
        });
        
        $('#ccy').change(function(){
            var ps= $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var vdate = $('#vdate').val();
            var securitiesCust = $('#securitiesCust').val();
            if(ps == ps_04 && ccy && portfolioId && vdate){
                getPtlCashPositionInfo();
            }
            if(ps == ps_03 && ccy && portfolioId && securitiesCust ){
                getPtlSecPositionInfo();
            }
        });
        
        $('#portfolioId').change(function(){
            if($('#securitiesCust').val()){
                if(pageModel != PAGE_MODEL_REFERENCE){
                    deleteSelect('securitiesCust')
                }
            }
            var ps= $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var securitiesCust = $('#securitiesCust').val();
            var vdate = $('#vdate').val();
            if(ps == ps_04 && ccy && portfolioId && vdate){
                getPtlCashPositionInfo();
            }else if(ps == ps_03 && portfolioId && securitiesCust){
                getPtlSecPositionInfo();
            }
            setBussRepoSecInput();
        });

        var deleteSelect = function (id) {
            $('#'+id).textSelect('setValue','');
            $.each($('#'+id).find('option'),function (index,element) {
                if( $('#'+id).find('option').eq(index).val()){
                    $('#'+id).find('option').eq(index).remove();
                }
            });
        }
        $('#vdate').change(function(){
            var ps= $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var vdate = $('#vdate').val();
            if(ps == ps_04 && ccy && portfolioId && vdate){
                getPtlCashPositionInfo();
            }
            // 设置添加质押券共通参数
            setBussRepoSecInput();
        });
        $('#entrustAmt').change(function(){
            // 设置添加质押券共通参数
            setBussRepoSecInput();
        });
        
        $('#calendarId').change(function(){
            var ps= $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var calendarId = $("#calendarId").val();
            if(ps == ps_04 && ccy && portfolioId){
                getPtlCashPositionInfo();
            }
            
            var mdateAdd = $('#mdateAdd').val();
            if(mdateAdd && calendarId){
                judgeIsHday();
            }
        });

        $('#tradeMarket').on("change", function (e, isInit) {
            var tradeMarket = $('#tradeMarket').val();
            var assetType1 = $('#assetType1').val();
            
            if(tradeMarket == tradeP02){
            	$('#termType_div').show();
              //--modify by 裴家俊  20190524-----
                if (pageModel != PAGE_MODEL_REFERENCE) {
                    $('#settleSpreed').val(speed0).attr('disabled',true).trigger('change');
                    $('#assetType1').val(repoTypeH02).attr('disabled', false).trigger('change', isInit);
                }
            }else if (tradeMarket == tradeP03){
                if (pageModel != PAGE_MODEL_REFERENCE) {
                    $('#assetType1').val(repoTypeH02).attr('disabled', false).trigger('change', isInit);
                    $('#settleSpreed').val(speed0).attr('disabled', true).trigger('change');
                }
            }
//            if(tradeMarket == tradeP02 || tradeMarket == tradeP03){
//                if(assetType1 == repoTypeH01){
//                    $('#termType_div').hide();
//                }else{
//                    $('#termType_div').show();
//                }
//                $('#settleSpreed').val(speed1).attr('disabled',true).trigger('change');
//                //--modify by 裴家俊  20190524-----
//                $('#assetType1').val(repoTypeH02).attr('disabled',false).change();
//            }
            else if(tradeMarket == tradeP01){
                if (pageModel != PAGE_MODEL_REFERENCE){
                    $('#settleSpreed').val(speed0).attr('disabled',false).trigger('change');
                }
                $('#termType_div').show();
                $('#assetType1').val(repoTypeH01).attr('disabled',true).change();               
                if (pageModel == PAGE_MODEL_NEW) {
                    $("#chlId").val('').trigger("change").prop("disabled", false);
                }
            }else{
                $('#assetType1').attr('disabled',false).change();
                    if (pageModel == PAGE_MODEL_NEW) {
                        $("#chlId").val('').trigger("change").prop("disabled", false);
                    }
            }
            if (isInit || pageModel == PAGE_MODEL_REFERENCE || pageModel ==PAGE_MODEL_EDIT) {
                return;
            }
             
            $('#termType').tradeRepTypeChange({termType:  'termType',
                tradeMarket: tradeMarket,
                tradeP02: tradeP02,
                tradeP03: tradeP03,
                tradeP01: tradeP01,
                repoType: $('#assetType1').val(),
                repoTypeH02: repoTypeH02,
                repoTypeH01: repoTypeH01});
            
            if ($('#assetType1').val() == repoTypeH02) {
            	$("#repoTerm").val("").prop("disabled", true);
            }else{
            	$("#repoTerm").prop("disabled", false);
            }
            
            // 如果是吉林银行，当交易场所为银行间的时候价格模式默认限价模式
            // add by ZhouYu 2019-12-19 start
            if(tradeMarket == BANK_MARKET && BANK_CODE == '004'){
            	$('#priceModel').val(BANK_PRICE_MODEL).trigger('change');
            }
         // add by ZhouYu 2019-12-19 end
        });

        //bug:YUCHUBS-429 guyunfei 2019/08/31 start
        $('#tradeMarket').change(function () {
        	//add by gaopeng 20191112 选择交易场所时清空证券账户  start
            if(pageModel==PAGE_MODEL_NEW) {
                $("#securitiesCust").val('').trigger('change');
            }
        	//add by gaopeng 20191112 选择交易场所时清空证券账户  end
            var tradeMarket = $('#tradeMarket').val();
            if(tradeMarket == tradeP01){
                $('#calendarId').textSelect('setValue','03');
            }
            if(tradeMarket == tradeP02){
                $('#calendarId').textSelect('setValue','04');
            }
            if(tradeMarket == tradeP03){
                $('#calendarId').textSelect('setValue','05');
                	//默认回购类型质押式竞价回购
                            	if(pageModel!=PAGE_MODEL_REFERENCE){
                            		$("#assetType1").textSelect('setValue', repoTypeH02);
                                    $('#assetType1').attr('disabled',false);
                                    var $repoType = $('#assetType1');
                                    //深圳证券交易所:回购类型为质押式竞价回购  add by start
                                    if($("#tradeMarket").val() == tradeP03){
                                        $repoType.updateChosenArr({
                                            item: [repoTypeH02],
                                            chosen: repoTypeH02,
                                            disabled: false
                                        });
                                    }
                                    //深圳证券交易所:回购类型为质押式竞价回购  add by end
                            	}
            }
            if (tradeMarket == tradeP01){
                $('#assetType1Div').hide();
            } else {
                $('#assetType1Div').show();
            }
        });
        //bug:YUCHUBS-429 guyunfei 2019/08/31 end--
        
        $('#settleSpreed').change(function(){
            var settleSpreed = $('#settleSpreed').val();
            if(settleSpreed == speed1){
                $('#availableDay_div').show();
            }else{
                $('#availableDay_div').hide();
            }
        });
        
        $('#repoTerm').change(function(){
            var repoTerm = $('#repoTerm').val();
            var re = /^[0-9]*$/;
            if(re.test(repoTerm)){
                var vdateAdd = $('#vdateAdd').val();
                if(vdateAdd){
                    var d1 = Date.ofDate(vdateAdd).getTime();
                    var day = parseInt($('#repoTerm').val()*24*60*60*1000);
                    var mdateAdd = Date.ofDate(d1+day);
                    $('#mdateAdd').val(mdateAdd.format("yyyy-MM-dd")).change();
                }
            }
        });
        
        $('#assetType1').on("change", function (e, isInit) {
            var assetType1 = $('#assetType1').val();
            var tradeMarket = $('#tradeMarket').val();
            if(assetType1 == repoTypeH02 && $('#ps').val() == ps_03){
                $('#assetAvailablePosition_div').show();
            }else{
                $('#assetAvailablePosition_div').hide();
            }
            if(assetType1 == repoTypeH01){
                if(tradeMarket == tradeP02 || tradeMarket == tradeP03){
//                    $('#termType_div').hide();
//                }else{
                    $('#termType_div').show();
                }
            }else{
                $('#termType_div').show();
            }
            if ($('#assetType1').val() == repoTypeH02 ) {
                if (pageModel == PAGE_MODEL_NEW) {
                    $("#chlId").val('').trigger("change").prop("disabled", true);
                }
            	$('#bussRepoSec_main').hide();
            } else {
                if (!$('#securitiesCust').val()) {
                    $("#chlId").prop("disabled", false);
                }
            	$('#bussRepoSec_main').show();
            }
            setBussRepoSecInput();
            if (isInit || pageModel == PAGE_MODEL_REFERENCE || pageModel ==PAGE_MODEL_EDIT) {
                return;
            }
            $('#termType').tradeRepTypeChange({termType: 'termType',
                tradeMarket: tradeMarket, 
                tradeP02: tradeP02, 
                tradeP03: tradeP03, 
                tradeP01: tradeP01,
                repoType: assetType1,
                repoTypeH02: repoTypeH02,
                repoTypeH01: repoTypeH01});
           
            if (assetType1 == repoTypeH02) {
            	$("#repoTerm").val("").prop("disabled", true);
            }else{
            	$("#repoTerm").prop("disabled", false);
            }
        });
        
        $('#vdateAdd').change(function(){
            var vdateAdd = $('#vdateAdd').val();
            var repoTerm = $('#repoTerm').val();
            var mdateAdd = $('#mdateAdd').val();
            if(vdateAdd && repoTerm){
                var d1 = Date.ofDate(vdateAdd).getTime();
                var day = parseInt($('#repoTerm').val()*24*60*60*1000);
                var mdateAdd = Date.ofDate(d1+day);
                $('#mdateAdd').val(mdateAdd.format("yyyy-MM-dd")).change();
            }
            if(vdateAdd && mdateAdd){
                if(Date.ofDate(vdateAdd).getTime()>Date.ofDate(mdateAdd).getTime()){
                    Joyin.error("起息日不能大于到期日");
                    $("#vdateAdd").val('');
                    return;
                }
                var datev = Date.ofDate(vdateAdd).getTime();
                var datem = Date.ofDate(mdateAdd).getTime();
                var total = (datem-datev)/1000;
                var day = parseInt(total / (24*60*60));
                $('#repoTerm').val(day);
            }
            
        });
        
        $('#mdateAdd').change(function(){
            var vdateAdd = Date.ofDate($('#vdateAdd').val());
            var mdateAdd = Date.ofDate($('#mdateAdd').val());
            var calId = $('#calendarId').val();
            if(mdateAdd && calId){
                judgeIsHday();
            }
            if(vdateAdd && mdateAdd){
                if(vdateAdd.getTime()>mdateAdd.getTime()){
                    Joyin.error("到期日不能小于起息日");
                    $("#mdateAdd").val('');
                    return;
                }
                var datev = Date.ofDate(vdateAdd).getTime();
                var datem = Date.ofDate(mdateAdd).getTime();
                var total = (datem-datev)/1000;
                var day = parseInt(total / (24*60*60));
                $('#repoTerm').val(day);
            }
        });
        
        $('#securitiesCust').change(function(){
        	if ($("#securitiesCust").val()) {
        	    $("#chlId").val('').prop("disabled", true);
        	}else if ($('#assetType1').val() != repoTypeH02) {
                $("#chlId").prop("disabled", false);
        	}
            var ps= $('#ps').val();
            var portfolioId = $('#portfolioId').val();
            var securitiesCust = $('#securitiesCust').val();
            if(ps == ps_03 && portfolioId && securitiesCust){
                getPtlSecPositionInfo();
            }
            setBussRepoSecInput();
        });
        
        $('#termType').change(function(){
            var termType = $('#termType').val();
            if(termType == GC001){
                $('#repoTerm').val(1).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == GC002){
                $('#repoTerm').val(2).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == GC003){
                $('#repoTerm').val(3).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == GC004){
                $('#repoTerm').val(4).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == GC007){
                $('#repoTerm').val(7).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == GC014){
                $('#repoTerm').val(14).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == GC028){
                $('#repoTerm').val(28).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == GC091){
                $('#repoTerm').val(91).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == GC182){
                $('#repoTerm').val(182).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == R001){
                $('#repoTerm').val(1).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == R007){
                $('#repoTerm').val(7).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == R014){
                $('#repoTerm').val(14).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == R021){
                $('#repoTerm').val(21).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == RC001){
                $('#repoTerm').val(1).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == RC003){
                $('#repoTerm').val(3).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == RC007){
                $('#repoTerm').val(7).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == R_001){
                $('#repoTerm').val(1).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == R_002){
                $('#repoTerm').val(2).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == R_003){
                $('#repoTerm').val(3).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == R_004){
                $('#repoTerm').val(4).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == R_007){
                $('#repoTerm').val(7).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == R_014){
                $('#repoTerm').val(14).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == R_028){
                $('#repoTerm').val(28).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == R_091){
                $('#repoTerm').val(91).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == R_182){
                $('#repoTerm').val(182).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == R1M){
                $('#repoTerm').val(30).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == R2M){
                $('#repoTerm').val(60).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == R3M){
                $('#repoTerm').val(90).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == R4M){
                $('#repoTerm').val(120).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else if(termType == R6M){
                $('#repoTerm').val(180).attr('disabled',true).change();
                $('#mdateAdd').attr('disabled',true);
            }else{
                $('#mdateAdd').attr('disabled',false);
                $('#repoTerm').attr('disabled',false);
            }
        });
        /**
         * 价格模式
         */
        $('#priceModel').change(function(){
            if($('#priceModel').val() == PRICEMODEL_03){
                $('#price').val('').attr('disabled',true);
                $('#price').removeAttr("required");
            }else{
            	$('#price').attr('disabled',false);
                $('#price').attr("required", "true");
            }
            star();
        });
        if(pageModel == '1'){
            $('#priceModel').trigger('change');
        }
        //竞价模式下修改页面通道不可选 add by yujinfa start
         if(pageModel==PAGE_MODEL_EDIT&&$('#assetType1').val() == repoTypeH02){
              $("#chlId").prop("disabled", true);
         }
         //竞价模式下修改页面通道不可选 add by yujinfa end

    });
}());
