/**
 * 中债报备信息共通业务组件
 * @auth zhangliang
 */
(function(){

	/**
     * 添加必填标志
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
    };
	/**
	 * 保存或提交按钮执行事件
	 */
	var onSaveOrSubmitAction = function(operateType){
		var object = $.serializeToObject('#ProductReportRegister002_form',true);
		object.operateType = operateType;
        object.pageModel = pageModel;
        if(object.repInvestCountry){
        	object.repInvestCountry = object.repInvestCountry.join(",");
        }
        //境外托管机构
        if(object.repOutDepCtry){
        	object.repOutDepCtry = object.repOutDepCtry.join(",");
        }
        //投资者国别
        if(object.repRiskType){
        	object.repRiskType = object.repRiskType.join(",");
        }
        //产品销售区域
        if(object.repSaleArea){
        	object.repSaleArea = object.repSaleArea.join(",");
        }
        //增信机构类型
        if(object.repCreditType){
        	object.repCreditType = object.repCreditType.join(",");
        }
        //产品增信标识
        if(object.repCreditFlag && object.repCreditFlag === NO){
        	object.repCreditFlag = HAS_NO
        }
        //发行机构提前终止权标识
        if(object.repTermRightFlag && object.repTermRightFlag === NO){
        	object.repTermRightFlag = HAS_NO
        }
        //客户赎回权标识
        if(object.repCusRedeemFlag && object.repCusRedeemFlag === NO){
        	object.repCusRedeemFlag = HAS_NO
        }
        //判断发行机构代码是否已经维护 add by leipeng 20200727
        if(!object.repIssuePartyId){
        	Joyin.alert("请维护当前机构的发行机构代码",2);
            return ;
        }
        Joyin.ajaxJson(basePath + 'invest/product/productelement/reportregister/ProductReportRegister002/check',object, function(result) {
    		Joyin.confirm(result.data, function() {
                	if(operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'invest/product/productelement/reportregister/ProductReportRegister002/save', object, "ProductReportRegister002_btn_save");
                    }
                    else {
                        WorkFlow.doSubmitList(basePath + 'invest/product/productelement/reportregister/ProductReportRegister002/save', object, "ProductReportRegister002_btn_submit");
                    }
                })
            })
	};
	
	
	//资金投向地区联动
	$("#repInvestArea").change(function(){
		var repInvestArea = $("#repInvestArea").val();
		if(repInvestArea == saleAreaDeclareIn){
			$("#repInvestCountry").val('').trigger('change');
			$("#repInvestCountry").attr('disabled',true);
			//境外托管机构国别
			$("#repOutDepCtry").val('').trigger('change');
			$("#repOutDepCtry").attr('disabled',true);
			//境外托管机构名称
			$("#repOutDepName").val('').trigger('change');
			$("#repOutDepName").attr('disabled',true);
		}else{
			$("#repInvestCountry").attr('disabled',false);
			$("#repOutDepCtry").attr('disabled',false);
			$("#repOutDepName").attr('disabled',false);
		}
		if(repInvestArea == saleAreaDeclareOut){
			$("#repFinanSerModel").val('').trigger('change');
			$("#repFinanSerModel").attr('disabled',true);
			$("#repInDepName").val('').trigger('change');
			$("#repInDepName").attr('disabled',true);
		}else{
			$("#repFinanSerModel").attr('disabled',false);
			$("#repInDepName").attr('disabled',false);
		}
	});
	//产品管理模式联动
	$("#repPmmode").change(function(){
		var repPmmode = $("#repPmmode").val();
		if(repPmmode == prodManageModeBank || repPmmode == prodManageModeSpv){
			$("#repMname").val('').trigger('change');
			$("#repMname").attr("disabled",true);
		}else{
			$("#repMname").attr("disabled",false);
		}
	});
	//设置最短期限联动
	$("#isShortTerm").change(function(){
		var isShortTerm = $("#isShortTerm").val();
		if(isShortTerm == isShortTerm_no){
			$("#shortTerm").val('').trigger('change');
			$("#shortTerm").attr("disabled",true);
			$("#isFreeRedem").val('').trigger('change');
			$("#isFreeRedem").attr("disabled",true);
		}else{
			$("#shortTerm").attr("disabled",false);
			$("#isFreeRedem").attr("disabled",false);
		}
	});
	//募集币种联动
	$("#repRaiseCcy").change(function(){
		var repRaiseCcy = $("#repRaiseCcy").val();
		if(repRaiseCcy){
			$("#repCashPrinCcy").val(repRaiseCcy).trigger('change');
			$("#repCashProfCcy").val(repRaiseCcy).trigger('change');
		}
	});
	
	//合作模式联动
	$("#repContMode").change(function(){
		var repContMode = $("#repContMode").val();
		if(repContMode == copModeSigRun){
			$("#repCoopName").val('').trigger('change');
			$("#repCoopName").attr("disabled",true);
		}else{
			$("#repCoopName").attr("disabled",false);
		}
	});
	
	//产品增信机构联动
	var repCreditFlagChange = function(){
		var repCreditFlag = $("#repCreditFlag")[0].checked;
		if(!repCreditFlag){
			//产品增信机构类型
			$("#repCreditType").val('').trigger('change');
			$("#repCreditType").attr("disabled",true);
			//产品增信形式
			$("#repCreditMode").val('').trigger('change');
			$("#repCreditMode").attr("disabled",true);
		}else{
			$("#repCreditType").attr("disabled",false);
			$("#repCreditMode").attr("disabled",false);
		}
	};
	//运作模式联动
	$("#repOperateMode").change(function(){
		var repOperateMode = $("#repOperateMode").val();
			if(repOperateMode == prodRunModeCloNN || repOperateMode == prodRunModeCloN){
				//是否设置最短持有期限
				$("#isShortTerm").val('').trigger('change');
				$("#isShortTerm").attr("disabled",true);
				$("#shortTerm").val('').trigger('change');
				$("#shortTerm").attr("disabled",true);
				$("#isFreeRedem").val('').trigger('change');
				$("#isFreeRedem").attr("disabled",true);
				//add by yujiaping 2020-12-11 TISHKBS-1673 产品申报登记要素管理-产品运作模式为封闭式的置灰 是否现金管理类start
				$('#repIsCashManage').val('').trigger('change').attr('disabled',true);
				//add by yujiaping 2020-12-11 TISHKBS-1673 产品申报登记要素管理-产品运作模式为封闭式的置灰 是否现金管理类end
			}else{
				$("#isShortTerm").attr("disabled",false);
				$('#repIsCashManage').attr('disabled', false);
			}
	});
	/**
	 * 初始化
	 */
	var productReportRegister002Init = function(){
		$("#repCashPrinCcy").attr("disabled",true);
		$("#repCashProfCcy").attr("disabled",true);
		//编辑页面行内标识码不可修改
		if(pageModel == PAGE_MODEL_EDIT){
			$("#repFinprodInsId").attr("disabled",true);
		}
		//资金投向地区为境内
		var repInvestArea = $("#repInvestArea").val();
		if(repInvestArea != saleAreaDeclareInOut && repInvestArea != saleAreaDeclareOut){
			$("#repInvestCountry").val('').trigger('change');
			$("#repInvestCountry").attr('disabled',true);
			//境外托管机构国别
			$("#repOutDepCtry").val('').trigger('change');
			$("#repOutDepCtry").attr('disabled',true);
			//境外托管机构名称
			$("#repOutDepName").val('').trigger('change');
			$("#repOutDepName").attr('disabled',true);
		}
		if(repInvestArea != saleAreaDeclareIn && repInvestArea != saleAreaDeclareInOut){
			$("#repFinanSerModel").val('').trigger('change');
			$("#repFinanSerModel").attr('disabled',true);
			$("#repInDepName").val('').trigger('change');
			$("#repInDepName").attr('disabled',true);
		}
		//运作模式
		var repOperateMode = $("#repOperateMode").val();
		if(repOperateMode != prodRunModeOpeN && repOperateMode != prodRunModeOpeNN){
			//是否设置最短持有期限
			$("#isShortTerm").val('').trigger('change');
			$("#isShortTerm").attr("disabled",true);
			$("#shortTerm").val('').trigger('change');
			$("#shortTerm").attr("disabled",true);
			$("#isFreeRedem").val('').trigger('change');
			$("#isFreeRedem").attr("disabled",true);
		}
		//产品增信机构
		var repCreditFlag = $("#repCreditFlag")[0].checked;
		if(!repCreditFlag){
			//产品增信机构类型
			$("#repCreditType").val('').trigger('change');
			$("#repCreditType").attr("disabled",true);
			//产品增信形式
			$("#repCreditMode").val('').trigger('change');
			$("#repCreditMode").attr("disabled",true);
		}
		//合作模式
		var repContMode = $("#repContMode").val();
		if(repContMode == copModeSigRun || !repContMode){
			$("#repCoopName").val('').trigger('change');
			$("#repCoopName").attr("disabled",true);
		}
		//最短有效期限
		var isShortTerm = $("#isShortTerm").val();
		if(isShortTerm != isShortTerm_yes){
			$("#shortTerm").val('').trigger('change');
			$("#shortTerm").attr("disabled",true);
			$("#isFreeRedem").val('').trigger('change');
			$("#isFreeRedem").attr("disabled",true);
		}
		//产品管理模式
		var repPmmode = $("#repPmmode").val();
		if(repPmmode != prodManageModeActM && repPmmode != prodManageModeOthO){
			$("#repMname").val('').trigger('change');
			$("#repMname").attr("disabled",true);
		}
	}
	$(function() {
		star();
		// 产品增信标识
		repCreditFlagChange();
		layui.form.on('switch(repCreditFlagFilter)', repCreditFlagChange);
		productReportRegister002Init();
		// 保存按钮执行事件
		$('#ProductReportRegister002_btn_save').click(function(){
			onSaveOrSubmitAction('save');
		});
		// 提交按钮执行事件
		$('#ProductReportRegister002_btn_submit').click(function(){
			onSaveOrSubmitAction('submit');
		});
		
		$('#repInvTypeRat_showRule').click(function(){
			Joyin.alert(JoyinUtil.getLabel("BussRepFinProductInfo.repInvTypeRat.showRule"));
		});
	});
}());
