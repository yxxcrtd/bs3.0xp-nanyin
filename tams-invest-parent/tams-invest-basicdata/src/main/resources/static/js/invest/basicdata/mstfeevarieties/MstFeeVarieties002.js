/**
 * 新建费用品种信息
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
        var object = $.serializeToObject('#submitForm', true);
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.profitType = profitType;// 收益类型
		object.feeList = JSON.parse(feeList);// 费用名称在费用列表中已存在时
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstfeevarieties/MstFeeVarieties002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
            	if ($('#defalutApplyType').val()){
        			object.finprodMarketId = finprodMarketId;
        			Joyin.ajaxJson(basePath + 'invest/basicdata/mstfeevarieties/MstFeeVarieties002/finProductFeeSave', object, function(result){
            			if (result){
            				object.feeId = result.data;
            				top.returnData = object;
                    		var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                    		return;
            			}
            		})
            	}else{
            		if(operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'invest/basicdata/mstfeevarieties/MstFeeVarieties002/save', object, "MstFeeVarieties002_btn_save");
                    }
                     else {
                        WorkFlow.doSubmitList(basePath + 'invest/basicdata/mstfeevarieties/MstFeeVarieties002/save', object, "MstFeeVarieties002_btn_submit");
                     }
            	}
            });
        });
    }
    var init = function(){
    	if(pageModel==PAGE_MODEL_EDIT){
    	    //判断费用是否被使用
    		if(IS_USE_Y === IS_USE){
    		    $("#feeName").attr("disabled",false).trigger('change');
    		    $('#applyType').attr("disabled",true);
    		    $('#feeType').attr("disabled",true);
    		    $('#orgType').attr("disabled",true);
    		    $('#orgType2').attr("disabled",true);
    		    $('#chargeType').next().addClass('disabled').find('li').off('click');
    		    $('#basicBill').next().addClass('disabled').find('li').off('click');
                $('#basis').next().addClass('disabled').find('li').off('click');
    		}else if($('#feeType').val() == '12'){
    			$('#chargeType').attr("disabled",true).trigger('change');
        		$('#basicBill').attr("disabled",true).trigger('change');
        		$('#basis').attr("disabled",true).trigger('change');
    		}else if($('#chargeType').val() != CHARGE_TYPE_01){
    			$('#basicBill').attr("required",false);
        		$('#basis').attr("required",false);
        		$('#basicBill').val("").trigger('change');
        		$('#basis').val("").trigger('change');
        		$('#basicBill').next().addClass('disabled').find('li').off('click');
        		$('#basis').next().addClass('disabled').find('li').off('click');
    		}else{
    			$('#basicBill').attr("required",true);
        		$('#basis').attr("required",true);
    		}
    	} else if(pageModel==PAGE_MODEL_REFERENCE){
    		if($('#chargeType').val() != CHARGE_TYPE_01){
    			$('#basicBill').attr("required",false);
        		$('#basis').attr("required",false);
        		$('#basicBill').val("").trigger('change');
        		$('#basis').val("").trigger('change');
        		$('#basicBill').next().addClass('disabled').find('li').off('click');
        		$('#basis').next().addClass('disabled').find('li').off('click');
    		}else{
    			$('#basicBill').attr("required",true);
        		$('#basis').attr("required",true);
    		}
    	}else{
    		if($('#chargeType').val() != CHARGE_TYPE_01){
    			$('#basicBill').attr("required",false);
        		$('#basis').attr("required",false);
        		$('#basicBill').val("").trigger('change');
        		$('#basis').val("").trigger('change');
        		$('#basicBill').next().addClass('disabled').find('li').off('click');
        		$('#basis').next().addClass('disabled').find('li').off('click');
    		}else{
    			$('#basicBill').attr("required",true);
        		$('#basis').attr("required",true);
    		}
    	}
    	star();
    };
    /**
     * 画面加载完成
     */
    $(function() {
    	init();
        // 绑定按钮事件
        $('#MstFeeVarieties002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#MstFeeVarieties002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $('#chargeType').change(function(){
        	var chargeType = $('#chargeType').val();
        	if(chargeType != CHARGE_TYPE_01){
        		$('#basicBill').attr("required",false).trigger('change');
        		$('#basis').attr("required",false).trigger('change');
        		$('#basicBill').val("").trigger('change');
        		$('#basis').val("").trigger('change');
        		$('#basicBill').next().addClass('disabled').find('li').off('click').trigger('change');
        		$('#basis').next().addClass('disabled').find('li').off('click').trigger('change');
        	}
        	else if(pageModel != PAGE_MODEL_REFERENCE){
        	    if($('#defalutApplyType').val() && defalutBillBase){
        	        $('#basicBill').val(defalutBillBase).attr("disabled",true).trigger('change');
        	    } else {
        	        $('#basicBill').attr("disabled",false).trigger('change');
        	    }
        		$('#basis').attr("disabled",false).trigger('change');
        		$('#basicBill').attr("required",true).trigger('change');
        		$('#basis').attr("required",true).trigger('change');
        	}
        	var feeType = $("#feeType").val();
        	if(feeType != FEETYPE_04){
        		star();
        }
        });
        $("#applyType").change(function(){
        	if(pageModel != PAGE_MODEL_REFERENCE){
        		$("#feeType").val("").trigger('change');
        	}
        });
        $("#feeType").change(function(){
        	var feeType = $("#feeType").val();
        	//浮动管理费时 
        	if(feeType == '12'){
        		if($('#feeName').val()===FEENAME_04){
        			$('#feeName').val("").attr("disabled",false).trigger('change');
        		}
        		$('#chargeType').val("01").attr("disabled",true).trigger('change');
        		//$('#basicBill').val("03").attr("disabled",true).trigger('change');
        		$('#basis').val("A/365").attr("disabled",true).trigger('change');
        	}
        	//浮动差额中收时
        	else if(feeType == FEETYPE_04){
        		$('#feeName').val(FEENAME_04).attr("disabled",true).trigger('change');
        		$('#orgType').val(ORGTYPE_01).attr("disabled",true).trigger('change');
        		$('#chargeType').val(CHARGE_TYPE_01).attr("disabled",true).trigger('change');
        		$('#basicBill').val("").attr("disabled",true).trigger('change');
        		$('#basis').val("").attr("disabled",true).trigger('change');
        	}
        	else{
        		if($('#feeName').val()===FEENAME_04){
        			$('#feeName').val("").attr("disabled",false).trigger('change');
        		}
        		$('#orgType').val("").attr("disabled",false).trigger('change');
        		$('#chargeType').val("").attr("disabled",false).trigger('change');
        		$('#basicBill').val("").attr("disabled",false).trigger('change');
        		$('#basis').val("").attr("disabled",false).trigger('change');
        	}
        });
        $("#orgType").change(function(){
        	if(pageModel != PAGE_MODEL_REFERENCE){
        		$("#orgType2").val("").trigger('change');
        	}
        });
		if (pageModelBak == PAGE_MODEL_REFERENCE) {
			$("#chargeType").attr("disabled", true).trigger("change");
			$("#basicBill").attr("disabled", true).trigger("change");
			$("#basis").attr("disabled", true).trigger("change");
		}
    });
    
}());
