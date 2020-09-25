/**
 * 自定义现金流新建
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
    	// 划款指令隐藏时，自动生成划款指令信息
    	generateOrders(function () {
	    	// 检查生成划款指令参数是否发生变化
	        if (window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()) {
	            return;
	        }
	    	/**
	    	 * 获取列表参数
	    	 */
	        var object = $.serializeToObject('#submitForm');
	        object.operateType = operateType;
	        object.pageModel = pageModel;
	        object.portfolioName = $("#portfolioId").select2('data')[0].text;
	        Joyin.ajaxJson(basePath + 'invest/portfolio/ptlcashflow/PtlCashFlow002/check', object, function(result) {
	            Joyin.confirm(result.data, function() {
	                if(operateType === 'save') {
	                    WorkFlow.doSaveList(basePath + 'invest/portfolio/ptlcashflow/PtlCashFlow002/save', object, "PtlCashFlow002_btn_save");
	                }
	                else {
	                    WorkFlow.doSubmitList(basePath + 'invest/portfolio/ptlcashflow/PtlCashFlow002/save', object, "PtlCashFlow002_btn_submit");
	                }
	            });
	        });
    	});
    }
    var init = function(){
    	var wfl_operType = $("#wfl_operType").val();
    	if(pageModel==PAGE_MODEL_EDIT){
    		if(wfl_operType == null || wfl_operType != 'NEW'){
    			$('#finprodMarketId').attr("disabled",true);
        		$('#effDate').attr("disabled",true);
    		}
    	}
    	if(pageModel==PAGE_MODEL_NEW){
    		$('#isClean').attr("required",false);
    		$('#isClean').next().addClass('disabled').find('li').off('click');
    	}
    	if(pageModel != PAGE_MODEL_REFERENCE){
    		var customCashType = $('#customCashType').val();
        	if(customCashType != null && customCashType != CUSTOM_CASHFLOW_TYPE_04){
        		$('#isClean').attr("required",false);
        		$('#isClean').val("").trigger('change');
        		$('#isClean').next().addClass('disabled').find('li').off('click');
        	}
        	else{
        		$('#isClean').attr("required",true).trigger('change');
        	}
        	star();
    	}
    };
    //add by lizhi 2020-2-26 划款指令是否自动生成  start
    var generateOrders = function (callback) {
    	// 如果还款指令设置为隐藏，则自动生成划款指令
    	if(GEN_PAY_ORDERS_AUTO =='01'){
    		$('#bussTrdTransferOrder_btn_generate').click();
    	}
    	// 回调方法
	    if (callback && typeof callback === 'function') {
	        callback();
	    }
    }
    //add by lizhi 2020-2-26 划款指令是否自动生成  end
    $(function() {
    	init();
    	// 是否显示划款指令
        if(GEN_PAY_ORDERS_AUTO=='01'){
        	$('#bussTrdTransferOrder_div').hide() ;
        }else{
        	$('#bussTrdTransferOrder_div').show() ;
        }
        // 按钮事件
        $('#PtlCashFlow002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#PtlCashFlow002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $('#customCashType').change(function(){
        	var customCashType = $('#customCashType').val();
        	if(customCashType != null && customCashType != CUSTOM_CASHFLOW_TYPE_04){
        		$('#isClean').attr("required",false);
        		$('#isClean').val("").trigger('change');
        		$('#isClean').next().addClass('disabled').find('li').off('click');
        	}
        	else{
        		$('#isClean').attr("required",true).trigger('change');
        	}
        	star();
        })
        $('#finprodMarketId').change(function(){
        	var str = $('#finprodMarketId').select2('data')[0].text;
        	$("#finprodAbbr").val(str);
        	$("#finprodId").val($('#finprodMarketId').select2('data')[0].object);
        });
    });
}());

window.componentLoadCallback = function(isDone, pagePath, params) {
    if (isDone && window.BussTrdTransferOrder) {
    	window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function() {
    		/**
	    	 * 获取列表参数
	    	 */
	        var object = $.serializeToObject('#submitForm');
	        var isOk = false;
	        object.type = "getBussTrdTransferOrder";
	        object.operateType = "getBuss";
	        $.ajaxSetup({async: false});
	        Joyin.ajaxJson(basePath + 'invest/portfolio/ptlcashflow/PtlCashFlow002/check', object, function(result) {
        		isOk = result.errorList.length == 0;
	        }, false);
	        
	        if(isOk) {
	        	return {
	        		isTotalTrade: YES_OR_NO_N
	        		,bankLendingType: null
	        		,portfolioId: $("#portfolioId").val()
	        		,deal: {
	        			finprodType2: F23,
	        			ps: $("#ps").val(),
	        			tradeDate: $("#settleDate").val(),
	        			tradeAmt: $("#tradeAmt").val(),
	        			ccy: $("#ccy").val(),
	        			counterId:$("#counterId").val()
	        		}
	        	};
	        }
	        return null;
    	});
    }
}

