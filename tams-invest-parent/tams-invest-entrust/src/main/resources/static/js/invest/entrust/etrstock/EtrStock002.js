//@ sourceUrl=EtrStock002.js
/**
 * 股票委托新建
 */
(function () {

    //---add by jjpei 20190718----start---获取风险试算画面要素与Label提示文本
    var getRiskData = function () {
        var priceModel = $("#priceModel").val();
        var tradeAmt=null;
        if($('#entrustNum').val()&&$('#price').val()){
            tradeAmt=$('#entrustNum').val()*$('#price').val();
        }else if($('#entrustAmt').val()){
            tradeAmt=$('#entrustAmt').val();
        }
        else if(priceModel==PRICEMODEL_03&&!tradeAmt&&$('#entrustNum').val()&&$('#finprodIdhid').val()){
            Joyin.ajaxSync(basePath + 'invest/entrust/etrstock/EtrStock002/getClosePrice',{'finprodMarketId':$('#finprodIdhid').val(),'valDate':sysDate},function (ret) {
                tradeAmt = JoyinMath.doMul(ret.data,$('#entrustNum').val());
            })
        }
        var obj = {tradeDirection:$('#ps').val(),timePoint:MY_SUP_TIME_TYPE_01,valDate:sysDate,portfolioCode:$('#portfolioId').val(),tradeAmount:tradeAmt,assetType:fintype,isEnabled:USE_STATUS_01,assetCode:$('#finprodIdhid').val()}
        var object = {assetCode: ['finprodMarketId', 'EtrStock002.finprodId'],tradeDirection:['ps','EtrStock002.ps'],portfolioCode:['portfolioId','EtrStock002.portfolioId'],tradeAmount:['','EtrStock002.priceAndNum'],data:obj}
        return object;
    }
    //---add by jjpei 20190718----end---获取风险试算画面要素与Label提示文本

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
            if (window.BaseRiskMathComm.isCalcConditionChanged(getRiskData())){
                return;
            }
        }

        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#searchForm');
        if ($('#select2-finprodMarketId-container').attr('title')){
            object.finprodName = $('#select2-finprodMarketId-container').attr('title');
        }
        // 投资管理人BussEtrInvestPersonInfo
        object.etrInvestPersonInfo.invManagerId = $('#etrInvestPersonInfo_invManagerId').val();
        //操作类型
        object.operateType = operateType;
        object.pageModel = pageModel;
        object.entrustId = $('#entrustId').val();
        /*object.entrustAmt = $('#entrustNum').val();*/
        object.finprodId = $('#finprodIdhid').val();
        object.finvdate = $('#finvdate').val();
        object.finmdate = $('#finmdate').val();
        object.entrustStatus = $('#entrustStatus').val();
        if (object.etrInvestPersonInfo.trader != null && object.etrInvestPersonInfo.trader !== '') {
            object.etrInvestPersonInfo.trader = object.etrInvestPersonInfo.trader.join(",");
        }
        if (FileCommon) {
            object.fileArray = FileCommon.getUploadFileInfo();
            if (object.fileArray && _.isArray(object.fileArray)) {
                $.each(object.fileArray, function (index, obj) {
                    obj.seqNo = obj.fileNo;
                });
            }
        }
		/*if($('#priceModel').val() == PRICEMODEL_03){
			Joyin.ajaxJson(basePath + 'invest/entrust/etrstock/EtrStock002/getClosePriceByPara',{finprodMarketId:object.finprodMarketId},
					function(result) {
				object.closePrice = result;
			}, false);
		}*/
        Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/check', object, function(result1) {
        	Joyin.ajaxJson(basePath + 'invest/entrust/etrstock/EtrStock002/checkAuthorization', object, function(result2) {
	            Joyin.ajaxJson(basePath + 'invest/entrust/etrstock/EtrStock002/check', object,function (result) {
	                Joyin.confirm(result.data, function () {
	                    if (operateType === 'save') {
	                        WorkFlow.doSaveList(basePath + 'invest/entrust/etrstock/EtrStock002/save', object, "EtrStock002_btn_save");
	                    } else {
	                        WorkFlow.doSubmitList(basePath + 'invest/entrust/etrstock/EtrStock002/save', object, "EtrStock002_btn_submit");
	                    }
	                });
	            });
        	});
        });
    }

    //查询组合可用现金
    function getPtlCashPositionInfo() {
        if ($("#portfolioId").val() == null || $("#portfolioId").val() == '' ||
            $("#ccy").val() == null || $("#ccy").val() == '' || $('#vdate').val() == null || $('#vdate').val() == ''){
            return
        }
        var data = {
            portfolioId: $('#portfolioId').val(),
            ccy: $('#ccy').val(),
            vdate: $('#vdate').val(),
            calId: $('#calendarId').val()
        };
        Joyin.ajaxJson(basePath + 'invest/entrust/etrstock/EtrStock002/getProAvailableCash', data,function (result) {
            $('#proAvailableCash').val(result.cashT).trigger('blur');
            $('#proAvailableCash1').val(result.cashT1).trigger('blur');
            $('#accAvailableCash').val(result.cashT3).trigger('blur');
            $('#tradeMarketAccAvailableCash').val(result.cashT4).trigger('blur');
        });
    }
    //查询组合可用持仓
    function getPtlSecPositionInfo() {
        if ($("#portfolioId").val() == null || $("#portfolioId").val() == '' || $("#ccy").val() == null || $("#ccy").val() == ''){
            return
        }
        if($('#finprodMarketId').find("option:checked").data("data")){
            finprodId = $('#finprodMarketId').find("option:checked").data("data").object.finprodId
        }
        var data = {portfolioId: $('#portfolioId').val(), ccy: $('#ccy').val(), finprodId: finprodId, secAcctId: $('#secAcctId').val()}
        Joyin.ajaxJson(basePath + 'invest/entrust/etrstock/EtrStock002/getAssetAvailablePosition', data,function (result) {
            $('#assetAvailablePosition').val(result).trigger('blur');
        });
    }
    //通道可用现金查询
    function getProTongDaoCash(){
        if ($('#chlId').val() == null || $('#chlId').val() == '' || $("#portfolioId").val() == null || $("#portfolioId").val() == ''){
            return
        }
        var data = {portfolioId:$('#portfolioId').val(),TongDao:$("#chlId").val()}
        Joyin.ajaxJson(basePath + 'invest/entrust/etrstock/EtrStock002/getTongDaoCash',data,function(result) {
            $('#chlIdAmt').val(result).trigger('blur');
        });
    }

    function init() {
    	$('#secAcctId').on("change",function(){
    		getPtlSecPositionInfo();
    	});
        $('#portfolioId').change();
        $('#vdate').change();
        $('#finprodMarketId').change();
        $('#ps').change();
        $('#board').change();
        $('#tradeMarket').change();
        $("#chlId").change();
        $("#secAcctId").change();
        // 触发交易方式by赵东东 2019.9.4 start
        $('#dealMode').change();
        // 触发交易方式by赵东东 2019.9.4 start
    }
    /**
     * 画星星
     */
    function star() {
        // 加红星星
        // 查找label
        var _findPrevLabel = function (element, level) {
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
            $('.must').each(function () {
                $(this).remove();
            });
            $('*[required]').each(function () {
                var $el = $(this);
                var $target = _findPrevLabel($el, 3);
                if ($target.find('> .must').length === 0) {
                    $target.append($('<i class="must">*</i>'));
                }
            });
        }
    }


    /**
     * 当交易方向为买入/配股/增发（配售）时，显示组合可用现金、托管户剩余现金，交易所资金账户可用现金
     * proAvailableCash  proAvailableCash1 accAvailableCash  tradeMarketAccAvailableCash
     */
    function showCash(flag){
        if (flag){
            $('#assetAvailablePosition_div').hide();
            $('#proAvailableCash_div').show();
            $('#proAvailableCash1_div').show();
            $('#tradeMarketAccAvailableCash_div').show();
        }else {
            $('#assetAvailablePosition_div').show();
            $('#proAvailableCash_div').hide();
            $('#proAvailableCash1_div').hide();
            $('#tradeMarketAccAvailableCash_div').hide();
        }

    }
    //添加风险试算验证
    window.componentLoadCallback = function(isDone, pagePath, params) {
        if (isDone) {
            var riskParam = getRiskData();
            if (pageModel==PAGE_MODEL_NEW){
                riskParam.data =null;
            }
            window.BaseRiskMathComm.getparam(riskParam);
        }
    }

    /**
     * 绑定事件
     */
    $(function () {

        /**
         * 保存
         */
        $('#EtrStock002_btn_save').click(function () {
            saveOrSubmit('save');
        });
        /**
         * 提交
         */
        $('#EtrStock002_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
        /**
         * 风险试算
         */
        $('#EtrStock002_btn_riskcal').click(function () {
            // window.BaseRiskMathComm.initBaseRiskMathCommPage(getRiskData());
            var object = $.serializeToObject('#searchForm');
            var priceModel = $("#priceModel").val();
            if($('#entrustNum').val()&&$('#price').val()){
                object.entrustAmt=$('#entrustNum').val()*$('#price').val();
            }else if($('#entrustAmt').val()){
                object.entrustAmt=$('#entrustAmt').val();
            }
            else if(priceModel==PRICEMODEL_03&&$('#entrustNum').val()&&$('#finprodIdhid').val()){
                Joyin.ajaxSync(basePath + 'invest/entrust/etrstock/EtrStock002/getClosePrice',{'finprodMarketId':$('#finprodIdhid').val(),'valDate':sysDate},function (ret) {
                    object.entrustAmt = JoyinMath.doMul(ret.data,$('#entrustNum').val());
                })
            }
            object.finprodId = $('#finprodIdhid').val();
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/0/RSK002/F11',object,getRiskData());
        });

        //通道change事件
        $("#chlId").change(function() {
            if ($("#chlId").val()) {
                $("#secAcctId").val('');
                $("#secAcctId").prop("disabled", true);
                $('#secAcctId').removeAttr("required");
                $('#tradeMarketAccAvailableCash_div').hide();
                getProTongDaoCash();
            }else {
                $("#chlIdAmt").val('')
                $("#secAcctId").prop("disabled", false);
                $("#secAcctId").attr("required",true);
                $('#tradeMarketAccAvailableCash_div').show();
            }
            star();
        })

        $("#secAcctId").on("change", function () {
            if ($("#secAcctId").val()) {
                $("#chlId").val('').prop("disabled", true);
                $("#chlIdAmt").val('')
            }else {
                $("#chlId").prop("disabled", false);
            }
            star();
        });

        $('#dealMode').change(function () {
            var dealMode = $('#dealMode').val();
            //委托价格必填
            /*if (dealMode === dealMode_03){
                $('#price').removeAttr("required");
            }else {
                $('#price').attr("required",true);
            }*/
            // 默认交易方式选择竞价交易by赵东东 2019.9.4 start
            if(dealMode=='')
            {
                //设置默认选中第一项
               // $("#dealMode option:first").prop("selected", 'selected');
                $('#dealMode').val($('#dealModeMsg').val());
            }
            // 默认交易方式选择竞价交易by赵东东 2019.9.4 end
            // 画星星
            star();
        });
        /**
         * 交易方向
         */
        $('#ps').change(function () {
            var ps = $('#ps').val();
            switch (ps) {
                case T01:
                    $('#code_div').hide();
                    $('#board').change();
                    // 隐藏托管户剩余现金by赵东东 2019.9.2 start
                    $('#accAvailableCash_div').hide();
                    // 隐藏托管户剩余现金by赵东东 2019.9.2 end
                    showCash(true);
                    getPtlCashPositionInfo();
                    if(pageModel==PAGE_MODEL_NEW){
                        $('#priceModel').val('').trigger('change').attr('disabled',false);
                    } else if(pageModel==PAGE_MODEL_EDIT){
                        $('#priceModel').trigger('change').attr('disabled',false);
                    }
                    break;
                case T02:
                    $('#code_div').hide();
                    showCash(false);
                    $('#board').change();
                    $('#accAvailableCash_div').hide();
                    getPtlSecPositionInfo();
                    if(pageModel==PAGE_MODEL_NEW){
                        $('#priceModel').val('').trigger('change').attr('disabled',false);
                    } else if(pageModel==PAGE_MODEL_EDIT){
                        $('#priceModel').trigger('change').attr('disabled',false);
                    }
                    break;
                case T48:
                    $('#code_div').hide();
                    $('#accAvailableCash_div').hide();
                    showCash(true);
                    getPtlCashPositionInfo();
                    $('#priceModel').val(PRICEMODEL_01).trigger('change').attr('disabled',true);
                    break;
                case T46:
                    $('#code_div').show();
                    // 隐藏托管户剩余现金by赵东东 2019.9.2 start
                    $('#accAvailableCash_div').hide();
                    // 隐藏托管户剩余现金by赵东东 2019.9.2 end
                    showCash(true);
                    getPtlCashPositionInfo();
                    $('#priceModel').val(PRICEMODEL_01).trigger('change').attr('disabled',true);
                    break;
                case T47:
                    $('#code_div').show();
                    // 隐藏托管户剩余现金by赵东东 2019.9.2 start
                    $('#accAvailableCash_div').hide();
                    // 隐藏托管户剩余现金by赵东东 2019.9.2 end
                    showCash(true);
                    getPtlCashPositionInfo();
                    $('#priceModel').val(PRICEMODEL_01).trigger('change').attr('disabled',true);
                    break;
                default:
                    $('#code_div').hide();
                	$('#priceModel').val('').trigger('change').attr('disabled',false);
                    return;
            }
            $("#chlId").change();
            // 画星星
            star();
        });
        /**
         * 资产代码联动
         */
        $('#finprodMarketId').on('change', function () {
            var finprodMarketId = $('#finprodMarketId').val();
            var ps = $('#ps').val()
            if (finprodMarketId) {
                var data = $('#finprodMarketId').select2('data')[0].object;
                $('#finprodName').val($('#finprodMarketId').select2('data')[0].id).trigger('change');
                $('#finprodIdhid').val(data.finprodId).trigger('change');
                $('#ccy').val(data.ccy).trigger('change');
                $('#calendarId').val(data.calendarId).trigger('change');
                $('#tradeMarket').val(data.tradeMarket).trigger('change');
                $('#board').val(data.board).trigger('change');
            }
            if (ps === T01 || ps === T48 || ps === T46 || ps === T47) {
                getPtlCashPositionInfo();
            }
            if (ps === T02) {
                getPtlSecPositionInfo();
            }
        });


        //组合代码change事件
        $('#portfolioId').change(function () {
            var ps = $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var vdate = $('#vdate').val();
            if (ps === T01 || ps === T48 || ps === T46 || ps === T47) {
                getPtlCashPositionInfo();
            }
            if (ps === T02) {
                getPtlSecPositionInfo();
            }
        });

        //委托日change事件
        $('#vdate').change(function () {
            var ps = $('#ps').val();
            if (ps === T01 || ps === T48|| ps === T46 || ps === T47) {
                getPtlCashPositionInfo();
            }
        });
        init();
        // 画星星
        star();
        
        //委托金额change事件
        $('#entrustAmt').on('change',function () {
            //存在委托金额
        	if ($('#entrustAmt').val()) {
                $("#entrustNum").val('').prop("disabled", true).removeAttr("required");
//                $('#price').val('').prop("disabled", true).removeAttr("required");
                $('#entrustAmt').attr("required",true);
            }else{
            	$("#entrustNum").prop("disabled", false).removeAttr("required");
//            	$('#price').prop("disabled", false).removeAttr("required");
                $('#entrustAmt').removeAttr("required");
            }
    	 // 画星星
            star();
        });
        
      //委托数量change事件
        $('#entrustNum').on('change',function () {
            //存在委托数量
        	if ($('#entrustNum').val()||$('#price').val()) {
                $("#entrustAmt").val('').prop("disabled", true).removeAttr("required");
                $('#entrustNum').attr("required",true);
//                $('#price').attr("required",true);
            }else{
            	 $("#entrustAmt").prop("disabled", false).removeAttr("required");
            	 $('#entrustNum').removeAttr("required");
//            	 $('#price').removeAttr("required");
            }
        	// 画星星
            star();
        });
      //委托数量change事件
        /*$('#price').on('change',function () {
            //存在委托数量
        	if ($('#entrustNum').val()||$('#price').val()) {
                $("#entrustAmt").val('').prop("disabled", true).removeAttr("required");
                $('#entrustNum').attr("required",true);
                $('#price').attr("required",true);
            }else{
            	 $("#entrustAmt").prop("disabled", false).removeAttr("required");
            	 $('#entrustNum').removeAttr("required");
            	 $('#price').removeAttr("required");
            }
        	// 画星星
            star();
        });*/
        
        /**
         * 价格模式
         */
        $('#priceModel').change(function(){
            if($('#priceModel').val() == PRICEMODEL_03){
                $('#price').val('').prop("disabled", true).removeAttr("required");
            }else{
            	$('#price').prop("disabled", false).attr("required",true);
            }
            star();
        });
        
        //初始化页面隐藏 无交易方向隐藏
        if(!$("#ps").val()){
        	$('#assetAvailablePosition_div').hide();
            $('#proAvailableCash_div').hide();
            $('#proAvailableCash1_div').hide();
            $('#tradeMarketAccAvailableCash_div').hide();
        }
        
    });
}());
