//@ sourceUrl=EtrNewStock002.js
/**
 * 新股申购新建
 */
(function () {

    var getRiskData = function () {
        var priceModel = $("#priceModel").val();
        var tradeAmt=null;
        if($('#entrustNum').val()&&$('#price').val()){
            tradeAmt=$('#entrustNum').val()*$('#price').val();
        }else if($('#entrustAmt').val()){
            tradeAmt=$('#entrustAmt').val();
        }
        else if(priceModel==PRICEMODEL_03&&!tradeAmt&&$('#entrustNum').val()&&$('#finprodIdhid').val()){
            Joyin.ajaxSync(basePath + 'invest/entrust/etrnewstock/EtrNewStock002/getClosePrice',{'finprodMarketId':$('#finprodIdhid').val(),'valDate':sysDate},function (ret) {
                tradeAmt = JoyinMath.doMul(ret.data,$('#entrustNum').val());
            })
        }
        var obj = {tradeDirection:$('#ps').val(),timePoint:MY_SUP_TIME_TYPE_01,valDate:sysDate,portfolioCode:$('#portfolioId').val(),tradeAmount:tradeAmt,assetType:fintype,isEnabled:USE_STATUS_01,assetCode:$('#finprodIdhid').val()}
        var object = {assetCode: ['finprodMarketId', 'EtrNewStock002.finprodId'],tradeDirection:['ps','EtrNewStock002.ps'],portfolioCode:['portfolioId','EtrNewStock002.portfolioId'],tradeAmount:['','EtrNewStock002.priceAndNum'],data:obj}
        return object;
    }

    /**
     * 保存或提交
     *
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        /*var s;
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
        }*/
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#searchForm');
        if ($('#select2-finprodMarketId-container').attr('title')){
            object.finprodName = $('#select2-finprodMarketId-container').attr('title');
        }
        // 投资管理人BussEtrInvestPersonInfo
        object.etrInvestPersonInfo.invManagerId = $('#invManagerId').val();
        //操作类型
        object.operateType = operateType;
        object.pageModel = pageModel;
        object.entrustId = $('#entrustId').val();
        //申购方式
        object.purchModle = $('#purchModle').val();
        //申购时间
        object.purchDate = $('#purchDate').val();
        object.vdate = $('#purchDate').val();
        object.mdate = $('#purchDate').val();
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
			Joyin.ajaxJson(basePath + 'invest/entrust/etrnewstock/EtrNewStock002/getClosePriceByPara',{finprodMarketId:object.finprodMarketId},
					function(result) {
				object.closePrice = result;
			}, false);
		}*/
        Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/check', object, function(result1) {
        	Joyin.ajaxJson(basePath + 'invest/entrust/etrnewstock/EtrNewStock002/checkAuthorization', object, function(result2) {
	            Joyin.ajaxJson(basePath + 'invest/entrust/etrnewstock/EtrNewStock002/check', object,function (result) {
	                Joyin.confirm(result.data, function () {
	                    if (operateType === 'save') {
	                        WorkFlow.doSaveList(basePath + 'invest/entrust/etrnewstock/EtrNewStock002/save', object, "EtrNewStock002_btn_save");
	                    } else {
	                        WorkFlow.doSubmitList(basePath + 'invest/entrust/etrnewstock/EtrNewStock002/save', object, "EtrNewStock002_btn_submit");
	                    }
	                });
	            });
        	});
        });
    }

    function  computedAmount(){
        //申购数量
        var entrustNum = $(`#entrustNum`).val();
        //申购价格
        var price = $('#price').val();
        if(entrustNum !== null && entrustNum !== '' && price !== null && price !== ''){
            var pir = entrustNum * price;
            $('#entrustAmt').val(pir.toFixed(2));
        }
    }

    //查询组合可用现金
    function getPtlCashPositionInfo() {
        if ($("#portfolioId").val() == null || $("#portfolioId").val() == '' ||
            $("#ccy").val() == null || $("#ccy").val() == '' || $('#purchDate').val() == null || $('#purchDate').val() == ''){
            return
        }
        var data = {
            portfolioId: $('#portfolioId').val(),
            ccy: $('#ccy').val(),
            vdate: $('#purchDate').val(),
            calId: $('#calendarId').val()
        };
        Joyin.ajaxJson(basePath + 'invest/entrust/etrnewstock/EtrNewStock002/getProAvailableCash', data,function (result) {
            $('#proAvailableCash').val(result.cashT).trigger('blur');
           /* $('#proAvailableCash1').val(result.cashT1).trigger('blur');
            $('#accAvailableCash').val(result.cashT3).trigger('blur');
            $('#tradeMarketAccAvailableCash').val(result.cashT4).trigger('blur');*/
        });
    }

    function init() {
        $('#portfolioId').change();
        $('#purchDate').change();
        $('#finprodMarketId').change();
        $('#ps').change();
        $('#board').change();
        $('#tradeMarket').change();
        $("#chlId").change();
        $("#secAcctId").change();
        $('#dealMode').change();
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
    $(function () {

        /**
         * 保存
         */
        $('#EtrNewStock002_btn_save').click(function () {
            saveOrSubmit('save');
        });
        /**
         * 提交
         */
        $('#EtrNewStock002_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
        /**
         * 风险试算
         */
        $('#EtrNewStock002_btn_riskcal').click(function () {
            // window.BaseRiskMathComm.initBaseRiskMathCommPage(getRiskData());
            var object = $.serializeToObject('#searchForm');
            var priceModel = $("#priceModel").val();
            if($('#entrustNum').val()&&$('#price').val()){
                object.entrustAmt=$('#entrustNum').val()*$('#price').val();
            }else if($('#entrustAmt').val()){
                object.entrustAmt=$('#entrustAmt').val();
            }
            else if(priceModel==PRICEMODEL_03&&$('#entrustNum').val()&&$('#finprodIdhid').val()){
                Joyin.ajaxSync(basePath + 'invest/entrust/etrnewstock/EtrNewStock002/getClosePrice',{'finprodMarketId':$('#finprodIdhid').val(),'valDate':sysDate},function (ret) {
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

        /**
         * 资产代码联动
         */
        $('#finprodMarketId').on('change', function () {
            var finprodMarketId = $('#finprodMarketId').val();
            if (finprodMarketId) {
                var data = $('#finprodMarketId').select2('data')[0].object;
                $('#finprodName').val($('#finprodMarketId').select2('data')[0].id).trigger('change');
                $('#finprodIdhid').val(data.finprodId).trigger('change');
                $('#ccy').val(data.ccy).trigger('change');
                $('#calendarId').val(data.calendarId).trigger('change');
                $('#tradeMarket').val(data.tradeMarket).trigger('change');
                $('#board').val(data.board).trigger('change');
            }
            getPtlCashPositionInfo();

        });
        //申购方式change事件
        $('#buyMode').change(function () {
            var buyMode = $('#buyMode').val();
            if(buyMode !== null && buyMode !== ''){
                if("01" === buyMode || "02" === buyMode){
                    $('#entrustAmt').prop('disabled',true);
                    $('#entrustNum').prop('disabled',false);
                    $('#price').prop('disabled',false);
                }else{
                    $('#entrustNum').prop('disabled',true);
                    $('#price').prop('disabled',true);
                    $('#entrustAmt').prop('disabled',false);
                }
                $('#entrustNum').val('');
                $('#price').val('');
                $('#entrustAmt').val('');
            }
        });
        //申购方式change事件
        $('#entrustNum').change(function () {
            computedAmount();
        });
        //申购方式change事件
        $('#price').change(function () {
            computedAmount();
        });

        //组合代码change事件
        $('#portfolioId').change(function () {
            var ps = $('#ps').val();
            var ccy = $('#ccy').val();
            var portfolioId = $('#portfolioId').val();
            var vdate = $('#purchDate').val();
            getPtlCashPositionInfo();
        });

        init();
        // 画星星
        star();

    });
}());
