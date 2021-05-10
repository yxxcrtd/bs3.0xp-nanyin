
/**
 * 基金类标的信息新建
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
        object.finprodType = finprodType2;
        if(object.bussFinIntPayDto != null){
        	object.bussFinIntPayDto.fundType = $('#fundType').val();
        }
        // if(object.bussRepFundInfoDto != null){
        // 	object.bussRepFundInfoDto.finprodType2 = finprodType2;
        // }
        if (object.finprodMarketId != null && object.finprodMarketId != ""){
            if (object.tradeMarketId != null) {
                object.finprodMarketId = object.finprodMarketId + object.tradeMarketId;
            }else {
                object.finprodMarketId = object.finprodMarketId + AssetCommon.setTradeMarketId(object.finprodMarketId);
            }
        }
        if ($('#capiIncomeFeature').next().find("em").text() === '保本') {
            object.capiIncomeFeature = '01';
        } else {
            object.capiIncomeFeature = '02';
        }
        // if (object.bussFinIntPayDto != null || object.bussFinIntPayDto != undefined){
        //     if ($('#bussFinIntPayDto_isCompoundInt').next().find("em").text() === '是') {
        //         object.bussFinIntPayDto.isCompoundInt = 'Y';
        //     } else {
        //         object.bussFinIntPayDto.isCompoundInt = 'N';
        //     }
        // }
        Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/fund/FinFundTarget002/check', object, function(result) {
            Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/ValAssetCommon/isHoliday', {
                mdate: object.mdate,
                calendarId: object.calendarId
            }, function (isHoliday) {
                if (isHoliday) {
                    Joyin.confirm("到期日为节假日，是否确认提交", function () {
                        if (operateType === 'save') {
                            WorkFlow.doSaveList(basePath + 'valuation/basicdata/asset/fund/FinFundTarget002/save', object, "FinFundTarget002_btn_save");
                        } else {
                            WorkFlow.doSubmitList(basePath + 'valuation/basicdata/asset/fund/FinFundTarget002/save', object, "FinFundTarget002_btn_submit");
                        }
                    })
                } else {
                    Joyin.confirm(result.data, function () {
                        if (operateType === 'save') {
                            WorkFlow.doSaveList(basePath + 'valuation/basicdata/asset/fund/FinFundTarget002/save', object, "FinFundTarget002_btn_save");
                        } else {
                            WorkFlow.doSubmitList(basePath + 'valuation/basicdata/asset/fund/FinFundTarget002/save', object, "FinFundTarget002_btn_submit");
                        }
                    });
                }
            })
        })
    }

    /**
     * 画面加载完成
     */
    $(function() {
    	// init();
        // 资产代码拆分
        if ($('#finprodMarketId').val()){
            var finprodMarketIdValue = $('#finprodMarketId').val().split(".")[0];
            $("#finprodMarketId").val(finprodMarketIdValue);
        }

        // 当进入新建或复制界面时，清空所有隐藏表单中的值，避免发生不必要的问题
        if (pageModel == PAGE_MODEL_NEW){
            $("#finprodId").val("");
            $("#tempTrmFundType").val("");
            $("#seqno").val("");
            $("#creditMstId").val("");
            $("#pfhId").val("");
            $("#hapCreditAmt").val("");
            $("#creditFlag").val("");
            $("#dataSource").val("");
        }

    	if($("#tradeMarket").val()=="09"){
    		$("#trmFundType").attr("disabled",true).trigger('change');
    	}

        if (pageModel == PAGE_MODEL_EDIT){
            $('#finprodMarketId').attr("disabled",true).trigger('change');
            $('#fundType').attr("disabled",true).trigger('change');
        }

        if (pageModel == PAGE_MODEL_EDIT || pageModel == PAGE_MODEL_REFERENCE || pageModel == PAGE_MODEL_NEW){
            var fundTypeValue = $("#fundType").val();
            if (fundTypeValue != "" && fundTypeValue != "01"){
                $(".page-component").hide();
            }
            var tradeMarketValue = $("#tradeMarket").val();
            var trmFundTypeValue = $("#trmFundType").val();
            var fundTypeValue = $("#fundType").val();
            /**
             * 当基金类型为货币基金，交易场所为场内；枚举值为ETF则联动为100；当枚举值为实时申赎货币则联动为0.01；其他默认为1
             */
            if (fundTypeValue == "01" && (tradeMarketValue == "03" || tradeMarketValue == "04") && trmFundTypeValue == "01"){
                $("#shareUnit").val("100");
            }else if (fundTypeValue == "01" && (tradeMarketValue == "03" || tradeMarketValue == "04") && trmFundTypeValue == "05"){
                $("#shareUnit").val("0.01");
            }else {
                $("#shareUnit").val("1");
            }

            // 资产代码后缀根据交易场所上交所03、深交所04、场外09联动（.SH .SZ .OF)
            if (tradeMarketValue == '03'){
                $("#tradeMarketId").val(".SH");
            }else if (tradeMarketValue == '04'){
                $("#tradeMarketId").val(".SZ");
            }else if (tradeMarketValue == '09'){
                $("#tradeMarketId").val(".OF");
            }
        }

        // 绑定按钮事件
        $('#FinFundTarget002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#FinFundTarget002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });

		$('#tradeMarket').change(function(){
            // tradeMarketChange();
            var tradeMarketValue = $("#tradeMarket").val();
            if (tradeMarketValue == '03' || tradeMarketValue == '04'){
                $("#ccy").val("CNY").trigger("change");
                $('#ccy').attr("disabled",true).trigger('change');
                $('#trmFundType').attr("disabled",false).trigger('change');
            }else {
                $("#ccy").val("").trigger("change");
                $('#ccy').attr("disabled",false).trigger('change');
                $('#trmFundType').attr("disabled",true).trigger('change');
                $('#trmFundType').val("").trigger('change');
            }

            // 资产代码后缀根据交易场所上交所03、深交所04、场外09联动（.SH .SZ .OF)
            if (tradeMarketValue == '03'){
                $("#tradeMarketId").val(".SH");
            }else if (tradeMarketValue == '04'){
                $("#tradeMarketId").val(".SZ");
            }else if (tradeMarketValue == '09'){
                $("#tradeMarketId").val(".OF");
            }

            /**
             * 通过资产类型、交易场所、境内外联动带出核算分类和税务分类
             * @type {jQuery|string|undefined}
             */
            var trmFundTypeValue = $("#trmFundType").val();
            var fundTypeValue = $("#fundType").val();
            var blnAreaValue = $("#blnArea").val();
            var object = {
                fundType: fundTypeValue,
                tradeMarket: tradeMarketValue,
                blnArea: blnAreaValue
            };

            /**
             * 当基金类型为货币基金，交易场所为场内；枚举值为ETF则联动为100；当枚举值为实时申赎货币则联动为0.01；其他默认为1
             */
            if (fundTypeValue == "01" && (tradeMarketValue == "03" || tradeMarketValue == "04") && trmFundTypeValue == "01"){
                $("#shareUnit").val("100");
            }else if (fundTypeValue == "01" && (tradeMarketValue == "03" || tradeMarketValue == "04") && trmFundTypeValue == "05"){
                $("#shareUnit").val("0.01");
            }else {
                $("#shareUnit").val("1");
            }

            getFinanceProductClassAndFinanceProductTaxClass();
		});

        $('#fundType').change(function(){
        	if(pageModel != PAGE_MODEL_REFERENCE){
        		var fundType = $("#fundType").val();
            	if(fundType == "01"){
            		$('#secondType').attr("disabled",true);
            		$('#secondType').val('').trigger('change');
            		$('#secondType').attr("required",false);
            		$('#bussFinIntPayDto_div').show();
                    $(".page-component").show();
            		$('#isCompoundInt').attr("disabled",true).trigger('change');
            	}else{
            		$('#secondType').attr("disabled",false);
            		$('#bussFinIntPayDto_div').hide();
                    $(".page-component").hide();
            		$('#isCompoundInt').val('');
            		$('#isCompoundInt').attr("disabled",true).trigger('change');
            	}

                /**
                 * 通过资产类型、交易场所、境内外联动带出核算分类和税务分类
                 * @type {jQuery|string|undefined}
                 */
                var trmFundTypeValue = $("#trmFundType").val();
            	var tradeMarketValue = $("#tradeMarket").val();
            	var blnAreaValue = $("#blnArea").val();
            	var object = {
                    fundType: fundType,
                    tradeMarket: tradeMarketValue,
                    blnArea: blnAreaValue
                };

                /**
                 * 当基金类型为货币基金，交易场所为场内；枚举值为ETF则联动为100；当枚举值为实时申赎货币则联动为0.01；其他默认为1
                 */
            	if (fundType == "01" && (tradeMarketValue == "03" || tradeMarketValue == "04") && trmFundTypeValue == "01"){
            	    $("#shareUnit").val("100");
                }else if (fundType == "01" && (tradeMarketValue == "03" || tradeMarketValue == "04") && trmFundTypeValue == "05"){
                    $("#shareUnit").val("0.01");
                }else {
                    $("#shareUnit").val("1");
                }
                getFinanceProductClassAndFinanceProductTaxClass();
        	}
        	star();
        });

        $('#blnArea').change(function () {
            /**
             * 通过资产类型、交易场所、境内外联动带出核算分类和税务分类
             * @type {jQuery|string|undefined}
             */
            getFinanceProductClassAndFinanceProductTaxClass();
        });

        $('#trmFundType').change(function () {
            var tradeMarketValue = $("#tradeMarket").val();
            var trmFundTypeValue = $("#trmFundType").val();
            var fundTypeValue = $("#fundType").val();
            /**
             * 当基金类型为货币基金，交易场所为场内；枚举值为ETF则联动为100；当枚举值为实时申赎货币则联动为0.01；其他默认为1
             */
            if (fundTypeValue == "01" && (tradeMarketValue == "03" || tradeMarketValue == "04") && trmFundTypeValue == "01"){
                $("#shareUnit").val("100");
            }else if (fundTypeValue == "01" && (tradeMarketValue == "03" || tradeMarketValue == "04") && trmFundTypeValue == "05"){
                $("#shareUnit").val("0.01");
            }else {
                $("#shareUnit").val("1");
            }
            getFinanceProductClassAndFinanceProductTaxClass();
        });

        /**
         * 通过资产类型、交易场所、境内外、场内基金类型联动带出核算分类和税务分类
         * @type {jQuery|string|undefined}
         */
        function getFinanceProductClassAndFinanceProductTaxClass() {
            var fundTypeValue = $("#fundType").val();
            var tradeMarketValue = $("#tradeMarket").val();
            var blnAreaValue = $("#blnArea").val();
            var trmFundTypeValue = $("#trmFundType").val();
            var object = {};
            /**
             * 当基金类型为私募基金(06) 、FOF(09)时，不需要场内基金类型联动带出核算分类和税务分类
             */
            if (fundTypeValue == "06" || fundTypeValue == "09"){
                object = {
                    fundType: fundTypeValue,
                    tradeMarket: tradeMarketValue,
                    blnArea: blnAreaValue
                };
            }else {
                object = {
                    fundType: fundTypeValue,
                    tradeMarket: tradeMarketValue,
                    blnArea: blnAreaValue,
                    trmFundType: trmFundTypeValue
                };
            }
            if (fundTypeValue!=""&&tradeMarketValue!=""&&blnAreaValue!=""){
                Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/fund/FinFundTarget002/getFinanceProductClass', object, function(result){
                    $('#hsfl').val(result.hsfl).trigger("change");
                });
                Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/fund/FinFundTarget002/getFinanceProductTaxClass', object, function(result){
                    $('#swfl').val(result.swfl).trigger("change");
                });
            }
        }

    });

}());
