$(function() {
    /**
     *  是否自动加券(发大镜操作)
     * **/
    var isAutoCoupon = false;
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
        //update by  zhangnian 20190920 start
        if(!isAutoCoupon){
            if (FinPayInterestStdInfo && FinPayInterestStdInfo.checkPlanChanged()) {
                Joyin.error("付息计划已修改，请确认。")
                return;
            }
        }
        //update by zhangnian 20190920 end
        var  couponSpecies = getInterestFactorElementById('couponSpecies').val();
        var object = $.serializeToObject('#submitForm', true); 
        object.operateType = operateType;
        object.pageModel = pageModel;
        //update by zhangnian 20200110 start
        //新建 快录提交时  清空主键  避免原标的的多于check
        if(isAutoCoupon&&pageModel==PAGE_MODEL_NEW){
            object.finprodId = "";
        }
        //update by zhangnian 20200110 end
        if (object.tradeMarketId != null) {
			object.finprodMarketId = object.finprodMarketId + object.tradeMarketId;
		}else {
			object.finprodMarketId = object.finprodMarketId + AssetCommon.setTradeMarketId(object.finprodMarketId);
		}
      //文件信息
/*        if(FileCommon){
            object.fileArray = FileCommon.getUploadFileInfo();
        }*/
        // 付息计划
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
        //登记托管机构
        //object.bussFinBondRepInfoDto.repRegistOrg = $("#registOrg").val();
        object.finPayInterestStdInfoDto.mdate = $('#mdate').val();
        //债券报送信息
//        object.bussFinBondRepInfoDto.finprodType2 = FINPROD_TYPE_TG_F01;
//        object.bussFinBondRepInfoDto.tradeMarket = $('#tradeMarket').val();
        Joyin.ajaxJson(basePath + 'valuation/assets/bond/ValBondTargetAsset002/check', object, function(
                result) {
            //息票品种为贴现、零息时不作付息计划 、还本计划check
            if(couponSpecies==COUPON_SPECIES_02||couponSpecies==COUPON_SPECIES_03){
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
            }
        });
    }
    
    /**
     * 最终提交
     */
    var finalSava = function(object,operateType,result){
            Joyin.ajaxJson(basePath + 'valuation/assets/bond/ValBondTargetAsset002/isHoliday', {mdate:object.mdate,calendarId:object.calendarId}, function(isHoliday){
                if(isHoliday){
                    Joyin.confirm("到日期为节假日，是否确认提交", function() {
                        if (operateType === 'save') {
                            WorkFlow.doSaveList(basePath + 'valuation/assets/bond/ValBondTargetAsset002/save',
                                object, "ValBondTargetAsset002_btn_save");
                        } else {
                            WorkFlow.doSubmitList(basePath + 'valuation/assets/bond/ValBondTargetAsset002/save',
                                object, "ValBondTargetAsset002_btn_submit");
                        }
                    })
                }else{
                    Joyin.confirm(result.data, function() {
                        if (operateType === 'save') {
                            WorkFlow.doSaveList(basePath + 'valuation/assets/bond/ValBondTargetAsset002/save',
                                object, "ValBondTargetAsset002_btn_save");
                        } else {
                            WorkFlow.doSubmitList(basePath + 'valuation/assets/bond/ValBondTargetAsset002/save',
                                object, "ValBondTargetAsset002_btn_submit");
                        }
                    })
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
        $('#calRateType_div').hide();
    }
    
    var issuerChange = function(){
        var customerId = $('#issuer').val();
        AssetCommon.getRepInfoByIssuer(customerId);
    }

    var resetForm = function(id,value,pageId){
        //update by zhangnian 20191112 start
        //可转债信息
        if(id == 'finProductConvertible'){
            $.each(value, function(id,value){
                resetForm(id,value,'');
            });
        }else
        //update by zhangnian 20191112 end
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
	    		element.val(value).trigger('change');
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
            //update by zhangnian 20191112 start
            //可转债信息
            if(id == 'finProductConvertible'){
                $.each(value, function(id, value) {
                    setData(id, value , '');
                });
            }else
            //update by zhangnian 20191112 end
			// 计息
			if (id == 'finInterestFactorDto' ) {
				$.each(value, function(id, value) {
                    if (id == 'intType'){
                        id = 'rateType';
                    }
                    // 债券新建新增快捷录入功能 add by renping 20191010 start
                    else if (id == 'highestRate'){
                    	id = 'rateMax';
                    }
                    else if (id == 'lowestRate'){
                    	id = 'rateMin';
                    }
                    else if (id == 'benchmarkId'){
                    	id = 'indexId';
                    }
                    // 债券新建新增快捷录入功能 add by renping 20191010 end
					setData(id, value , 'finInterestFactorDto');
				});
			} else if (id == 'finPayInterestStdInfoDto') {
				// 付息
				$.each(value, function(id, value) {		
				    if (id == 'firstPayDate' && $('#finPayInterestStdInfoDto_calcRule').val()=='01') {
				        value = null;
                    }
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
			}else if (id == 'isRight') {
                if (value === 'Y' || value === '01') {
                    $('#isRight').prop('checked', true);
                }
			    //是否含权债
                isRightChange();
				 
			}else if (id == 'busiFinExercisePlanDto'){
               // 行权 表格
                $('#busiFinExercisePlanTable').data('joyintable').reload({
                    data : value.finExercisePlanList,
                    url : null
                });
            }
            // 债券新建新增快捷录入功能 add by renping 20191010 start
            else if(id == 'fileArray'){
            	// 附件
        		$("#fileUploader").data('uploader','');
        		$("#fileUploader").attr('data-data',value);
        		$("#fileUploader").data('data',value);
        		$("#fileUploader").uploadFile()
            // 债券新建新增快捷录入功能 add by renping 20191010 end
			    
            }else {
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
                        element.val(value).trigger('change');
                    // 债券新建新增快捷录入功能 modify by renping 20191010 start
                        // 修改快录中债报送丢失交易流通场所 by 赵东东 2019.11.22 start
                    }else if(id == 'bussFinBondRepInfoDto_repTradeMarket'){
                    	AssetCommon.setTradeMarketId(value);
                    	element.val(value).trigger('change');
                    	// 修改快录中债报送丢失交易流通场所 by 赵东东 2019.11.22 end
                    }else if (id == 'issuer' || id == 'calendarId' || id == 'sponsor' || id == 'oFinprodId' || id == 'postOrg'||id=='conFinprodMarketId') {
                    // 债券新建新增快捷录入功能 modify by renping 20191010 end
                        //data-url 赋值要特殊处理
                        element.textSelect('setValue', value);
                    } else {
                        element.val(value).trigger('change');
                    }
                }else if (element.hasClass('input-dateSelect')) {
		    		//日期框特殊处理
		    		if (value){
                        element.val(new Date(value).format('yyyy-MM-dd'));
                    } 
				}else if(element.hasClass('input-checkbox')){
					//单选按钮特殊处理
		    		element.val(value).trigger('change');
		    	}else if ($('#' + id).attr('lay-skin') === 'switch') {
		    		//switch 特殊处理
					$('#' + id).prop('checked', false);
					$('#' + id).next().removeClass('layui-form-onswitch').trigger('change');
					$('#' + id).next().find("em").text('否');
					// 是否,有无
					if (value === 'Y' || value === '01') {
						$('#' + id).prop('checked', true);
						$('#' + id).next().addClass('layui-form-onswitch').trigger('change');
						$('#' + id).next().find("em").text('是');
					}
                    // 债券新建新增快捷录入功能 add by renping 20191010 end
		    	}else{
		    		element.val(value);
		    	}
			}
	    }
    //从接口获取债券信息
    var getInterfBondInfo = function(){
        isAutoCoupon = true;
    	var finprodMarketId =  $('#finprodMarketId').val();
        //拆分市场代码       
        var arr  = finprodMarketId.split('.');
        if (arr.length == 2){
            finprodMarketId = arr[0];
            $('#finprodMarketId').val(finprodMarketId);
            $('#tradeMarketId').val('.' + arr[1]);
        } 
        if (finprodMarketId) {
    		Joyin.ajax(basePath + 'valuation/assets/bond/ValBondTargetAsset002/getInterfBondDto?finprodMarketId=' 
   				 + finprodMarketId + '&tradeMarketId=' + $('#tradeMarketId').val(),'' , function(result){
    			if (result.data[0]) {
    				Joyin.error(result.data[0].message);
				} else {
                    //组件赋值 先清后赋
                    $.each(Joyin.curfromData['ValBondTargetAsset002'],function(id, value){
                        resetForm(id, value.odata, '');
                    });
                    $.each(result.data, function(id, value) {
                        setData(id, value, '');
                    });
                    // 是否次级债
                    if(result.data.baseFinProductBaseInfoList[0].finProductAdd){
	                    var isSecBond = result.data.baseFinProductBaseInfoList[0].finProductAdd.isSecBond;
	                    if (isSecBond == 'Y' || isSecBond == '01') {
	                        $('#isSecBond').prop('checked', true);
	                        $('#isSecBond').next().addClass('layui-form-onswitch').trigger('change');
	                        $('#isSecBond').next().find("em").text('是');
	                    } else {
	                        $('#isSecBond').prop('checked', false);
	                        $('#isSecBond').next().removeClass('layui-form-onswitch').trigger('change');
	                        $('#isSecBond').next().find("em").text('否');
	                    }
                    }
				}
    			  layui.form.render();
   		 });
		} else {
			Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel('ValBondTargetAsset002.finprodId')));
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
            if(FinInterestFactor){
                getInterestFactorElementById('couponSpecies').change(FinInterestFactor.handleCouponSpecies).trigger("change");
            }
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
                $('#registOrg').attr('disabled', true).trigger('change');
                //置灰付息计划生成计划按钮
                /*$('#finPayInterestStdInfoDto_btn_generationPlan').off('click').prop('disabled', true).addClass('layui-btn-disabled');*/
            }
           if(isHqTrade){
        	   $('#isRight').attr('disabled',true).trigger('change');
           }
        }
    }
    var init = function(){
    	var hapCreditAmt = $("#hapCreditAmt").val();
    	star();
    }

    var type4Change = function(){
        if($('#type4').val()==WINDTYPEONE_B14||$('#type4').val()==WINDTYPEONE_B15){
            $('#FinProductConvertible_div').show();
        }else{
            $('#FinProductConvertible_div').hide();
        }
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
        $('#ValBondTargetAsset002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#ValBondTargetAsset002_btn_submit').click(function() {
            saveOrSubmit('submit');
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
        $("#registOrg").change(function () {
            if($(this).val()=='04'){
                $('#bussFinBondRepInfoDto_repRegistorgComment').val('中证机构间报价系统股份有限公司');
            }else{
                $('#bussFinBondRepInfoDto_repRegistorgComment').val('');
            }
        });
        $('#type4').change(function () {
            type4Change();
        })
        type4Change();
    });
});
