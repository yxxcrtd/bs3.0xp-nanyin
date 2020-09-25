/**
 * FinNetProductBonusScheme002 净值型分红方案登记
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
    var i = 0;
    //画面联动：输入产品代码，联动显示产品名称和产品简称
    var finprodMarketIdChange = function() {
        var finprodMarketId = $("#finprodMarketId").val();
        var str = $('#finprodMarketId').select2('data')[0].object.split(',');
    	if(str != null && str.length==8){
    		//$('#finprodAbbr').val(str[0]);
    		//$('#finprodName').val(str[1]);
    		$('#finprodId').val(str[2]);
            $('#unitvalueDec').val(str[3]);
            $('#purSpeed').val(str[4]);
            $('#finccy').textSelect('setValue', str[5]); // 币种
            $('#dealMode').val(str[6]);
            $('#portfolioId').val(str[7]);
            var delDate = $('#delDate').val();
          //权益登记日
            if (delDate) {
                var regDate = Date.ofDate(delDate).DateAdd('d', JoyinMath.doMinus($("#purSpeed").val(),1)).format('yyyy-MM-dd');
                $('#regDate').val(regDate).trigger("change");
            }
    	}
    	if($('#dealMode').val() == '11'){
			$('#prodStyle').attr("disabled",false);
    		//交易类型 下拉枚举值联动
        	prodStyleSelectInit();
    	}
    	else{
			$("#prodStyle").val('').parent().parent().hide();
//			$('#prodStyle').attr("disabled",true);
        	baseDateChange();
            vluRefDateChange();
    	}

        
    };
  //画面联动：输入产品代码，联动显示产品名称和产品简称
    var prodStyleChange = function() {
    	
        baseDateChange();
        vluRefDateChange();
    };
    //交易类型 下拉枚举值联动初始化
    var prodStyleSelectInit = function(){
    	//产品代码
    	var finprodId = $('#finprodId').val(); 
//    	$('#prodStyle').empty();
    	Joyin.ajaxJson(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme002/getProdStyleList', 
        	{'finprodId':finprodId}, 
        	function(result) {
        		if(result != null && result.length > 0){
    	            $("#prodStyle option").remove();
    	            $('#prodStyle').append("<option value=''>请选择...</option>");
            		$(result).each(function (i, n) {
                    	$('#prodStyle').append("<option value='"+n.finprodId+"'>"+n.prodType+"</option>");
                    });
            		$('#prodStyle').val(PROD_STYLE).parent().parent().show();
        		} else {
    				$("#prodStyle").val('').parent().parent().hide();
        		}
        	}
        );
    };
    
    //收益分配基准日联动
    var baseDateChange = function () {
        var baseDate = $("#baseDate").val();
        if(baseDate == null || baseDate ==''){
            $('#tdyUnpaidIntincexp').val('');
        }
        else if($('#dealMode').val() =='11'){
        	var object = {'finprodId':$('#prodStyle').val(), baseDate:baseDate};
            //获取基准日未分配利润
            Joyin.ajax(getTdyUnpaidIntincexp, object, function (data) {
                if(data){
                    $('#tdyUnpaidIntincexp').val(data.data);
                }
            })
        }
        else if($('#finprodId').val()){
            var object = {'finprodId':$('#finprodId').val(), baseDate:baseDate};
            //获取基准日未分配利润
            Joyin.ajax(getTdyUnpaidIntincexp, object, function (data) {
                if(data){
                    $('#tdyUnpaidIntincexp').val(data.data);
                }
            })
        }
        var bonuScalcFlag = $('#bonuScalcFlag').val();
        var tdyUnitValue = $('#tdyUnitValue').val();
        var tdyUnpaidIntincexp = $('#tdyUnpaidIntincexp').val();
        var unitvalueDec = $('#unitvalueDec').val();
        var isNetMormalize = $("input:radio[name=isNetMormalize]:checked").val();
        if(isNetMormalize == 'Y'){
          //如果分红方案为按单位分红时
            if(bonuScalcFlag == bonuScalcFlagUnit){
                $("#unitBonusVlu").prop('disabled',true).trigger("change");
                if(unitvalueDec && tdyUnitValue) {
                    $("#unitBonusVlu").val(Number(JoyinMath.doMinus(tdyUnitValue, 1, parseInt(unitvalueDec)))).trigger("change");
                }
            }
            else if(unitvalueDec && tdyUnpaidIntincexp){
                $("#totalAmt").val(tdyUnpaidIntincexp).trigger("change");
            }
        }
        else{
        	
            //如果分红方案为按单位分红时
     		if(bonuScalcFlag == bonuScalcFlagUnit){
     			//分红方案不置灰
                $("#unitBonusVlu").prop('disabled',false).trigger("change");
                //单位分红金额清空
                $("#unitBonusVlu").val('').trigger("change");
                //分红后单位净值清空
                $("#netvalue").val('').trigger("change");
                $('#isNetMormalize').textSelect('setValue', 'N');
            }
            else {
                $("#totalAmt").val('').trigger("change");
                $("#netvalue").val('').trigger("change");
                $('#isNetMormalize').textSelect('setValue', 'N');
            }
     		star();
        }
        netvalueChange();
    };
    //净值参考日联动
    var vluRefDateChange = function () {
        var delDate = $("#delDate").val();
        var finprodId ='';
        var dealMode = $("#dealMode").val();
        if(dealMode == '11'){
        	finprodId = $("#prodStyle").val();
        }
        else{
        	finprodId = $("#finprodId").val();
        }
        var object = {'delDate':delDate,'finprodId':finprodId};
        if(delDate) {
            //获取净值参考日
            Joyin.ajax(getVluRefDate, object, function (data) {
                if (data) {
                    $('#vluRefDate').val(data.data);
                    var vluRefDate1 = $("#vluRefDate").val();
                    var object1 = {'vluRefDate':vluRefDate1,'finprodId':finprodId};
                    if(vluRefDate1 && finprodId){
                        //获取参考日净值和参考日份额
                        Joyin.ajax(getTdyUnitValueAndTdyShareAmt, object1, function (data) {
                            if (data) {
                                $('#tdyUnitValue').val(data.tdyUnitValue);
                                $('#tdyShareAmt').val(data.tdyShareAmt);
                                //获得分红净资产
                                $('#tdyAssetAmt').val(data.tdyAssetAmt);
                                bonuScalcFlagChange();
                            }
                        });
                    }

                }
            })
        }
    };
    //除权日联动
    var delDateChange = function () {
        var delDate = $("#delDate").val();
        var purSpeed = $('#purSpeed').val();
        var bonusDate = $('#bonusDate').val();
        if(delDate == null || delDate ==''){
            $('#regDate').val('');
            $('#vluRefDate').val('');
            $('#tdyUnpaidIntincexp').val('');
            $('#tdyUnitValue').val('');
            $('#tdyShareAmt').val('');
            $('#bonusDate').val('');
        }
        else {
            if(purSpeed) {
                //权益登记日联动
                var regDate = Date.ofDate(delDate).DateAdd('d',JoyinMath.doMinus(purSpeed,1) ).format('yyyy-MM-dd');
                $('#regDate').val(regDate).trigger("change");
            }
            vluRefDateChange();
            //红利下发日
            if(inintNumber >= 0 ){
                if(bonusDate || bonusDate == '') {
                    var date = Date.ofDate(delDate).DateAdd('d', 1).format('yyyy-MM-dd');
                    $('#bonusDate').val(date);
                }
            }
        }
        netvalueChange();
    };

    //分红方案联动
    var bonuScalcFlagChange = function (isInit) {
        var bonuScalcFlag = $('#bonuScalcFlag').val();
        if(bonuScalcFlag == bonuScalcFlagUnit){
            $(".unitBonusVlu").show();
            $(".totalAmt").hide();
            if($('#netValueNormalize').prop("checked")){
                $("#unitBonusVlu").prop('disabled',true).trigger("change");
            }
        }
        else{
            $(".totalAmt").show();
            $(".unitBonusVlu").hide();
        }
        if(isInit){
        	//初始化结束
        	return;
        }
        var tdyUnitValue = $('#tdyUnitValue').val();
        var tdyUnpaidIntincexp = $('#tdyUnpaidIntincexp').val();
        var unitvalueDec = $('#unitvalueDec').val();
        var isNetMormalize = $("input:radio[name=isNetMormalize]:checked").val();
        if(isNetMormalize == 'Y'){
          //如果分红方案为按单位分红时
            if(bonuScalcFlag == bonuScalcFlagUnit){
                $("#unitBonusVlu").prop('disabled',true).trigger("change");
                if(unitvalueDec && tdyUnitValue) {
                    $("#unitBonusVlu").val(Number(JoyinMath.doMinus(tdyUnitValue, 1, parseInt(unitvalueDec)))).trigger("change");
                }
            }
            else if(unitvalueDec && tdyUnpaidIntincexp){
                $("#totalAmt").val(tdyUnpaidIntincexp).trigger("change");
            }
        }
        else{
        	
            //如果分红方案为按单位分红时
     		if(bonuScalcFlag == bonuScalcFlagUnit){
     			//分红方案不置灰
                $("#unitBonusVlu").prop('disabled',false).trigger("change");
                //单位分红金额清空
                $("#unitBonusVlu").val('').trigger("change");
                //分红后单位净值清空
                $("#netvalue").val('').trigger("change");
                $('#isNetMormalize').textSelect('setValue', 'N');
            }
            else {
                $("#totalAmt").val('').trigger("change");
                $("#netvalue").val('').trigger("change");
                $('#isNetMormalize').textSelect('setValue', 'N');
            }
     		star();
        }
        netvalueChange();
    };
    //净值归一选中事件
    var netValueNormalizeChecked = function (isInit) {
        if (isInit) {
            return;
        }
        var bonuScalcFlag = $('#bonuScalcFlag').val();
        var tdyUnitValue = $('#tdyUnitValue').val();
        var tdyUnpaidIntincexp = $('#tdyUnpaidIntincexp').val();
        var unitvalueDec = $('#unitvalueDec').val();
        // if($('#netValueNormalize').prop("checked")){
        if(i == 1){
            if(bonuScalcFlag == bonuScalcFlagUnit){
                $("#unitBonusVlu").prop('disabled',true).trigger("change");
                if(unitvalueDec && tdyUnitValue) {
                    $("#unitBonusVlu").val(Number(JoyinMath.doMinus(tdyUnitValue, 1, parseInt(unitvalueDec)))).trigger("change");
                }
            }
            else if(unitvalueDec && tdyUnpaidIntincexp){
                $("#totalAmt").val(tdyUnpaidIntincexp).trigger("change");
            }
        }
        else {
            if(bonuScalcFlag == bonuScalcFlagUnit){
                $("#unitBonusVlu").prop('disabled',false).trigger("change");
                $("#unitBonusVlu").val('').trigger("change");
                $("#netvalue").val('').trigger("change");
                $('#isNetMormalize').textSelect('setValue', 'N');
            }
            else {
                $("#totalAmt").val('').trigger("change");
                $("#netvalue").val('').trigger("change");
                $('#isNetMormalize').textSelect('setValue', 'N');
            }
        }
        netvalueChange();
    };
    
    //计算分红后单位净值
    var netvalueChange = function () {
        var bonuScalcFlag = $('#bonuScalcFlag').val();
        var tdyUnitValue = $('#tdyUnitValue').val();//参考日净值
        var unitBonusVlu = $('#unitBonusVlu').val();//单位分红金额
        var unitvalueDec = $('#unitvalueDec').val();//单位净值小数位
        var tdyShareAmt = $('#tdyShareAmt').val();//参考日份额
        var totalAmt = $("#totalAmt").val();//分红总金额
        var vluRefDate = $("#vluRefDate").val();
        var finprodId = $("#finprodId").val();
        var tdyAssetAmt = $('#tdyAssetAmt').val();//分红前净资产
        if(bonuScalcFlag == bonuScalcFlagUnit && tdyUnitValue && unitBonusVlu && unitvalueDec){
            $("#netvalue").val(JoyinMath.doMinus(Number(tdyUnitValue),Number(unitBonusVlu),parseInt(unitvalueDec)));
            
        }
        if(vluRefDate && finprodId && bonuScalcFlag != bonuScalcFlagUnit && unitvalueDec && tdyShareAmt && totalAmt && tdyAssetAmt){
            var minus = JoyinMath.doMinus(Number(tdyAssetAmt),Number(totalAmt),parseInt(unitvalueDec))
            if(Number(tdyShareAmt)==0){
                $("#netvalue").val(0);
            }else{
                $("#netvalue").val(JoyinMath.doDiv(Number(minus),Number(tdyShareAmt),parseInt(unitvalueDec)));
            }
        }
    };
    
    var isNetMormalizeChange = function(){
    	var isNetMormalize = $('#isNetMormalize').val();
    	if(isNetMormalize=='Y'){
    		var bonuScalcFlag = $('#bonuScalcFlag').val();
            var tdyUnitValue = $('#tdyUnitValue').val();
            var tdyUnpaidIntincexp = $('#tdyUnpaidIntincexp').val();
            var unitvalueDec = $('#unitvalueDec').val();
            if(tdyUnitValue && tdyUnitValue<1){
           	 JoyinUtil.showMessage("净值小于1，不能净值归1,请重新选择!","error");
           	 $('#isNetMormalize').textSelect('setValue', 'N');
           	 return;
            }
            //如果分红方案为按单位分红时
            if(bonuScalcFlag == bonuScalcFlagUnit){
                $("#unitBonusVlu").prop('disabled',true).trigger("change");
                if(unitvalueDec && tdyUnitValue) {
                    $("#unitBonusVlu").val(Number(JoyinMath.doMinus(tdyUnitValue, 1, parseInt(unitvalueDec)))).trigger("change");
                }
            }
            else if(unitvalueDec && tdyUnpaidIntincexp){
                $("#totalAmt").val(tdyUnpaidIntincexp).trigger("change");
            }
            netvalueChange();
    		star();
    	}else{
    		var bonuScalcFlag = $('#bonuScalcFlag').val();
            var tdyUnitValue = $('#tdyUnitValue').val();
            var tdyUnpaidIntincexp = $('#tdyUnpaidIntincexp').val();
            var unitvalueDec = $('#unitvalueDec').val();
            //如果分红方案为按单位分红时
     		if(bonuScalcFlag == bonuScalcFlagUnit){
     			//分红方案不置灰
                $("#unitBonusVlu").prop('disabled',false).trigger("change");
                //单位分红金额清空
                $("#unitBonusVlu").val('').trigger("change");
                //分红后单位净值清空
                $("#netvalue").val('').trigger("change");
            }
            else {
                $("#totalAmt").val('').trigger("change");
                $("#netvalue").val('').trigger("change");
            }
     		star();
    	}
    	
    };
    //初始化
    var init = function () {
        if(pageModel == PAGE_MODEL_NEW){
            delDateChange();
        }
        var obj = $('#finprodMarketId').select2('data')[0].object;
        if(obj){
        	var str = obj.split(',');
        	if(str != null && str.length==8){
                $('#unitvalueDec').val(str[3]);
        	}
        }
        var finprodId ='';
        var dealMode = $("#dealMode").val();
        if(dealMode == '11'||(!dealMode&&PROD_STYLE)){
        	finprodId = $("#prodStyle").val();
        }
        else{
        	finprodId = $("#finprodId").val();
        }
//        var finprodId = $("#finprodId").val();
        var vluRefDate1 = $("#vluRefDate").val();
        var object1 = {'vluRefDate':vluRefDate1,'finprodId':finprodId};
        if(vluRefDate1 && finprodId){
            //获取参考日净值和参考日份额
            Joyin.ajax(getTdyUnitValueAndTdyShareAmt, object1, function (data) {
                if (data) {
                	//参考日净值保留4位小数
                    $('#tdyUnitValue').val(parseFloat(data.tdyUnitValue).toFixed(4));
                    $('#tdyShareAmt').val(data.tdyShareAmt);
                    //获得分红净资产
                    $('#tdyAssetAmt').val(data.tdyAssetAmt);
                }
            });
        }
    	
        bonuScalcFlagChange(true);
        
    }

    //判断是提交或是保存
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm', true);
        object.unitBonusVlu = $("#unitBonusVlu").val();
        object.totalAmt = $("#totalAmt").val();
        object.pageModel = pageModel;
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme002/save', object, "FinNetProductBonusScheme002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme002/save', object, "FinNetProductBonusScheme002_btn_submit");
                }
            });
        });
    };
    /**
     * 画面加载完成
     *
     */
    $(function() {
         init();
         $("#prodStyle").val('').parent().parent().hide();
         
         prodStyleSelectInit();
        // 绑定按钮事件
        $('#FinNetProductBonusScheme002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#FinNetProductBonusScheme002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        //产品款型联动	
        $('#finprodMarketId').change(function() {
            finprodMarketIdChange();
        });
        //产品代码联动
        $('#prodStyle').change(function() {
        	prodStyleChange();
        });
        //收益分配基准日联动
        $('#baseDate').change(function() {
            baseDateChange();
        });
        //除权日联动
        $('#delDate').change(function() {
            inintNumber += 1;
            delDateChange();
        });
        //分红方案联动
        $('#bonuScalcFlag').change(function() {
            bonuScalcFlagChange();
        });

        //单位分红金额联动
        $('#unitBonusVlu').change(function() {
            netvalueChange();
        });
        //分红总金额联动
        $('#totalAmt').change(function() {
            netvalueChange();
        });
        
        
        $('#isNetMormalize').change(function() {
            isNetMormalizeChange();
        });
    });
}());

