$(function() {
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

    //获取计息要素信息元素id
    var getInterestFactorElementById = function(id,type) {
        if(type=="div"){
            return $('#' + 'finInterestFactorDto' + '_' + id+'_'+'div');
        }else{
            return $('#' + 'finInterestFactorDto' + '_' + id);
        }
    };
    
    
    function saveOrSubmit(operateType) {
        var  couponSpecies = getInterestFactorElementById('couponSpecies').val();
        var object = $.serializeToObject('#submitForm', true);
        object.operateType = operateType;
        object.pageModel = pageModel;
        //
        if (object.tradeMarketId != null) {
			object.finprodMarketId = object.finprodMarketId + object.tradeMarketId;
		}else {
			object.finprodMarketId = object.finprodMarketId + AssetCommon.setTradeMarketId(object.finprodMarketId);
		}
      //文件信息
        if(FileCommon){
            object.fileArray = FileCommon.getUploadFileInfo();
        }
       /* // 付息计划
        if(FinPayInterestStdInfo){
            object.finPayInterestStdInfoDto.couponSpecies = couponSpecies;
            object.finPayInterestStdInfoDto.finCashPlanList = FinPayInterestStdInfo.getAllRows(); 
        }
        // 还本计划
        object.bussFinRepaymentDto.vdate = $('#vdate').val();
        object.bussFinRepaymentDto.mdate = $('#mdate').val();
        if(BussFinRepayment){
            object.bussFinRepaymentDto.finCashPlanList = BussFinRepayment.getAllRows();
        }
        // 行权计划
        object.busiFinExercisePlanDto = {};
        // 行权计划列表有值且是否行权选择是的场合，才保存行权计划
        if(BusiFinExercisePlan && object.isRight == YES_OR_NO_Y){
            object.busiFinExercisePlanDto.finExercisePlanList = BusiFinExercisePlan.getAllRows();
            object.busiFinExercisePlanDto.finCashPlanList = FinPayInterestStdInfo.getAllRows();// 行权计划业务验证需要支付计划
        }
        //评级信息
        object.busiFinBondRatingInfoDto = {};
        if(BusiFinBondRatingInfo){
            object.busiFinBondRatingInfoDto.mstBondCreditRatingList = BusiFinBondRatingInfo.getAllRows();  
        }
        //登记托管机构
        object.bussFinBondRepInfoDto.repRegistOrg = $("#registOrg").val();*/
        /*object.finPayInterestStdInfoDto.mdate = $('#mdate').val();*/
        Joyin.ajaxJson(basePath + 'invest/asset/tenderbond/FinTenderBondTargetAsset002/check', object, function(
                result) {
        	finalSava(object,operateType,result);
            //息票品种为贴现、零息时不作付息计划 、还本计划check
            /*if(couponSpecies==COUPON_SPECIES_02||couponSpecies==COUPON_SPECIES_03){
                if(isTransInAsset){
                    Joyin.confirm(JoyinUtil.getMessage('FIN_ASSET_W_0002'),function(){
                            finalSava(object,operateType,result);
                        });
                 }else{
                     finalSava(object,operateType,result);
                 }
            }else{
                if(isTransInAsset){
                    Joyin.confirm(JoyinUtil.getMessage('FIN_ASSET_W_0002'),function(){
                        if (FinPayInterestStdInfo && FinPayInterestStdInfo.comfirmCheck()&&FinPayInterestStdInfo.check()&&BussFinRepayment.isComfirmCheck()) {
                            finalSava(object,operateType,result);
                        }
                    })
                }else{
                    if (FinPayInterestStdInfo && FinPayInterestStdInfo.comfirmCheck()&&FinPayInterestStdInfo.check()&&BussFinRepayment.isComfirmCheck()) {
                        finalSava(object,operateType,result);
                    }
                }
            }*/
        });
    }
    
    /**
     * 最终提交
     */
    var finalSava = function(object,operateType,result){
        Joyin.confirm(result.data, function() {
            if (operateType === 'save') {
                WorkFlow.doSaveList(basePath + 'invest/asset/tenderbond/FinTenderBondTargetAsset002/save',
                        object, "FinTenderBondTargetAsset002_btn_save");
            } else {
                WorkFlow.doSubmitList(basePath + 'invest/asset/tenderbond/FinTenderBondTargetAsset002/save',
                        object, "FinTenderBondTargetAsset002_btn_submit");
            }
        });
    }
    /**
     * 是否含权债change事件
     */
    var isRightChange = function(){
        var isRight = $('#isRight')[0].checked;
        if(isRight){
            $('#busiFinExercisePlanDto_div').show();
        }else{
            $('#busiFinExercisePlanDto_div').hide();
        }
    }
    
    /**
     * 计息要素画面显示/隐藏
     */
    var showFinInterestFactorDiv = function(){
        var obj = $('#finInterestFactorDto_div');
        getInterestFactorElementById('investRate','div').hide();
        getInterestFactorElementById('loadRate','div').hide();
        getInterestFactorElementById('referRate','div').hide();
        getInterestFactorElementById('buyOutRate','div').hide();
    }
    
    var issuerChange = function(){
        var customerId = $('#issuer').val();
        AssetCommon.getRepInfoByIssuer(customerId);
    }

    var resetForm = function(id,value,pageId){
    	//计息 付息 还本
    	if (id == 'finInterestFactorDto') {
			$.each(value, function(id,value){
				resetForm(id,value,'finInterestFactorDto');
			});
		}else if(id == 'finPayInterestStdInfoDto'){
			$.each(value, function(id,value){
				resetForm(id,value,'finPayInterestStdInfoDto');
			});
		}else{
			var element;
	    	if(pageId != '' && pageId != undefined){
				id = pageId + '_' + id;
			}
	    	if($('#'+id).size() > 0){
	    		element = $('#'+id);
	    	}else{
	    		element = $('input[name=' + id + ']');
	    	}
	    	
	    	//下拉框特殊处理
	    	if (element.hasClass('input-select')) {
	    		element.textSelect('setValue', value);
			}else if(element.hasClass('input-checkbox')){
				//单选按钮特殊处理
	    		element.val(value).trigger('change');
	    	}else if ($('#' + id).attr('lay-skin') === 'switch') {
	    		//switch 特殊处理
				$('#' + id).prop('checked', false);
				// 是否,有无
				if (value === 'Y' || value === '01') {
					$('#' + id).prop('checked', true);
				}
	    	}else{
	    		element.val(value);
	    	}
		}
    	
    }
    	 
	    var setData = function(id, value ,pageId) {
			// 计息
			if (id == 'finInterestFactorDto' ) {
				$.each(value, function(id, value) {
					setData(id, value , 'finInterestFactorDto');
				});
			} else if (id == 'finPayInterestStdInfoDto') {
				// 付息
				$.each(value, function(id, value) {
					setData(id, value , 'finPayInterestStdInfoDto');
				});
				// 表格
				$('#generationPlanTable').data('joyintable').reload({
					data : value.finCashPlanList,
					url : null
				});
			} else if (id == 'bussFinRepaymentDto') {
				// 还本
				// 表格
				$('#bussFinRepaymentTable').data('joyintable').reload({
					data : value.finCashPlanList,
					url : null
				});
			} else if (id == 'busiFinExercisePlanDto') {
				// 行权

			} else {
				// 基本信息
				var element;
		    	if(pageId != '' && pageId != undefined){
					id = pageId + '_' + id;
				}
		    	if($('#'+id).size() > 0){
		    		element = $('#'+id);
		    	}else{
		    		element = $('input[name=' + id + ']');
		    	}
		    	
		    	//下拉框特殊处理
		    	if (element.hasClass('input-select')) {
		    		 if (id == 'tradeMarket') {
		    			 AssetCommon.setTradeMarketId(value);
		    			 }
		    		 element.textSelect('setValue', value);
				}else if (element.hasClass('input-dateSelect')) {
		    		//日期框特殊处理
		    		element.val(new Date(value).format('yyyy-MM-dd'));
				}else if(element.hasClass('input-checkbox')){
					//单选按钮特殊处理
		    		element.val(value).trigger('change');
		    	}else if ($('#' + id).attr('lay-skin') === 'switch') {
		    		//switch 特殊处理
					$('#' + id).prop('checked', false);
					// 是否,有无
					if (value === 'Y' || value === '01') {
						$('#' + id).prop('checked', true);
					}
		    	}else{
		    		element.val(value);
		    	}
			}
	    }
    //从接口获取债券信息
    var getInterfBondInfo = function(){
    	var finprodMarketId =  $('#finprodMarketId').val();
    	if (finprodMarketId) {
    		Joyin.ajax(basePath + 'invest/asset/tenderbond/FinTenderBondTargetAsset002/getInterfBondDto?finprodMarketId=' 
   				 + finprodMarketId + '&tradeMarketId=' + $('#tradeMarketId').val(),'' , function(result){
    			if (result.data[0]) {
    				Joyin.error(result.data[0].message);
				} else {
                    //组件赋值 先清后赋
                    $.each(Joyin.curfromData['FinTenderBondTargetAsset002'],function(id, value){
                        resetForm(id, value.odata, '');
                    });
                    $.each(result.data, function(id, value) {
                        setData(id, value, '');
                    });

				
				}
    			  layui.form.render();
   		 });
		} else {
			Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel('FinTenderBondTargetAsset002.finprodId')));
		}
    }
    var issuerChange = function(){
        var customerId = $('#issuer').val();
        AssetCommon.getRepInfoByIssuer(customerId);
    }
    window.componentLoadCallback = function(isDone, pagePath, params){
        if(isDone){
            //计息要素展示/隐藏
            showFinInterestFactorDiv();
            isRightChange();
            //息票品种
            /*if(FinInterestFactor){
                getInterestFactorElementById('couponSpecies').change(FinInterestFactor.handleCouponSpecies).trigger("change");
            }*/
          //该资产存在交易
            if(isTransInAsset){
                $('#vdate').attr('disabled',true).trigger('change');
                /*$('#mdate').attr('disabled',true).trigger('change');*/
                $('#ccy').attr('disabled',true).trigger('change');
                $('#tradeMarket').attr('disabled',true).trigger('change');
                $('#isRight').attr('disabled',true).trigger('change');
                $('#tradeMarket').attr('disabled',true).trigger('change');
                getInterestFactorElementById('rate').attr('disabled',true).trigger('change');
                getInterestFactorElementById('couponSpecies').attr('disabled',true).trigger('change');
                getInterestFactorElementById('indexId').attr('disabled',true).trigger('change');
                getInterestFactorElementById('coefficient').attr('disabled',true).trigger('change');
                getInterestFactorElementById('spreadRate').attr('disabled',true).trigger('change');
                //登记托管机构
                $('#bussFinBondRepInfoDto_repRegistOrg').attr('disabled',true).trigger('change');
                //置灰付息计划生成计划按钮
                /*$('#finPayInterestStdInfoDto_btn_generationPlan').off('click').prop('disabled', true).addClass('layui-btn-disabled');*/
            }
        }
    }
    var init = function(){
        //资产代码拆分
        var finprodMarketId =  $('#finprodMarketId').val();
        if (finprodMarketId){
            $('#finprodMarketId').val(finprodMarketId.split('.')[0]);
            $('#tradeMarketId').val('.' + finprodMarketId.split('.')[1]);
        }
    	//如果授信模式=='02'，则显示批复号和实际授信主体
    	if(CE_MODEL == '02'){
    		$('#actualCreditProvidDiv').show();
    		$('#pflshDiv').show();
    	}
    	else{
    		$('#actualCreditProvidDiv').hide();
    		$('#pflshDiv').hide();
    	}
    	var hapCreditAmt = $("#hapCreditAmt").val();
    	if(hapCreditAmt && hapCreditAmt>0){
    		$("#pflsh").attr("disabled",true).trigger('change');
    	}
    	if($("#creditFlag").val() == 'Y'){
    		$("#actualCreditProvid").attr("required", true).trigger('change');
    	}
    	if($("#creditFlag").val() != 'Y'){
    		$("#actualCreditProvid").attr("required", false).trigger('change');
    	}
    	star();
    }
    $(function() {
    	init();
        //修改  资产代码置灰
        if(pageModel!=PAGE_MODEL_NEW){
            $("#finprodMarketId").attr('disabled',true);
			$('.btn-save').hide();
        }
        //是否含权债change
        layui.form.on('switch(isRight)',function(){
            isRightChange();
        });
        // 绑定按钮事件
        $('#FinTenderBondTargetAsset002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#FinTenderBondTargetAsset002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $('#tradeMarket').change(function(){
			AssetCommon.setTradeMarketId($('#tradeMarket').val());
		});
        $('#issuer').change(issuerChange);
        $('#tradeMarket').change(function(){
			AssetCommon.setTradeMarketId($('#tradeMarket').val());
		});
        $('#magnifier').click(getInterfBondInfo);
        $('#mdate,#vdate').change(function(obj){
            obj.type = FINPROD_TYPE_TG_F01;
            AssetCommon.checkDate(obj);
        });

        $("#pflsh").change(function(){
        	var str = $('#pflsh').select2('data')[0].object.split(',');
        	if(str != null && str.length==3){
        		$("#seqno").val(str[0]);
        		$("#creditMstId").val(str[1]);
        		$("#creditFlag").val(str[2]);
        	}
        	if(str[2] == 'Y'){
        		$("#actualCreditProvid").attr("required", true).trigger('change');
        	}
        	if(str[2] != 'Y'){
        		$("#actualCreditProvid").attr("required", false).trigger('change');
        	}
        	star();
        });

        /*type4 and type5's automatic match added by wangguiqi on 2019/7/31 -start*/
        $("#type9,#type10,#type11,#type12").change(function(){
        	window.BussAssetFiveType.doAssetFiveTypeChange(IS_SHOW_TYPE,'F01','type9','type10','type11','type12','type4','type5');
        });
        /*type4 and type5's automatic match added by wangguiqi on 2019/7/31 -end*/
      //是否展示资产四五级分类
        if(IS_SHOW_TYPE == '01'){
        	$('#type11Div').show();
        	$('#type12Div').show();
        }else{
        	$('#type11Div').hide();
        	$('#type12Div').hide();
        }
    });
});
