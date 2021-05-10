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

    /**
     * 设置拆分
     * @param tradeMarket
     * @param finprodMarketId
     */
    var setInitFinprodMarketId = function(tradeMarket, finprodMarketId) {
        var reObj = {};
        reObj.finprodMarketId = finprodMarketId;
        reObj.tradeMarket = tradeMarket;
        if (tradeMarket == '01') {//银行间
            reObj.tradeMarket = '.IB';
        }else if (tradeMarket == '03') {//上海证券交易所
            reObj.tradeMarket = '.SH';
        }else if (tradeMarket == '04') {//深圳证券交易所
            reObj.tradeMarket = '.SZ';
        }else if (tradeMarket == '09') {//OTC场外
            reObj.tradeMarket = '.OF';
        }else if (tradeMarket == '05') {//OTC场外
            reObj.tradeMarket = '.IOC';
        }else{//机构间私募系统
            reObj.tradeMarket = '';
        }
        if(reObj.tradeMarket != '') {
            var lastFinprodMarketId = finprodMarketId.substring(0,finprodMarketId.length - reObj.tradeMarket.length);
            reObj.finprodMarketId = lastFinprodMarketId;
        }
        return reObj;
    }

    //获取计息要素信息元素id
    var getInterestFactorElementById = function(id,type) {
        if(type=="div"){
            return $('#' + 'finInterestFactorDto' + '_' + id+'_'+'div');
        }else{
            return $('#' + 'finInterestFactorDto' + '_' + id);
        }
    };

    //获取付息信息元素id
    var getPayInterestStdInfoElementById = function(id,type) {
        if(type=="div"){
            return $('#' + 'finPayInterestStdInfoDto' + '_' + id+'_'+'div');
        }else{
            return $('#' + 'finPayInterestStdInfoDto' + '_' + id);
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
        var repAssetType = $('#repAssetType').val();
        //update by zhangnian 20190920 end
        var  couponSpecies = getInterestFactorElementById('couponSpecies').val();
        var object = $.serializeToObject('#submitForm', true); 
        object.operateType = operateType;
        object.pageModel = pageModel;
        object.finCashCalcRuleList = finCashCalcRuleList;
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

        object.finPayInterestStdInfoDto.mdate = $('#mdate').val();
        Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/bond/FinBondTargetAsset002/check', object, function(
                result) {
            if (FinPayInterestStdInfo && !FinPayInterestStdInfo.check()) {
                return ;
            }
            //息票品种为贴现、零息时不作付息计划 、还本计划check
            if(couponSpecies==COUPON_SPECIES_02||couponSpecies==COUPON_SPECIES_03){
                if(isTransInAsset && YES_OR_NO_Y != COPY_FLAG ){
                    Joyin.confirm(JoyinUtil.getMessage('FIN_ASSET_W_0002'),function(){
                            finalSava(object,operateType,result);
                        });
                 }else{
                     finalSava(object,operateType,result);
                 }
            }else{
                if(isTransInAsset && YES_OR_NO_Y != COPY_FLAG){
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
            Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/ValAssetCommon/isHoliday', {mdate:object.mdate,calendarId:object.calendarId}, function(isHoliday){
                if(isHoliday){
                    Joyin.confirm("到期日为节假日，是否确认提交", function() {
                        if (operateType === 'save') {
                            WorkFlow.doSaveList(basePath + 'valuation/basicdata/asset/bond/FinBondTargetAsset002/save',
                                object, "FinBondTargetAsset002_btn_save");
                        } else {
                            WorkFlow.doSubmitList(basePath + 'valuation/basicdata/asset/bond/FinBondTargetAsset002/save',
                                object, "FinBondTargetAsset002_btn_submit");
                        }
                    })
                }else{
                    Joyin.confirm(result.data, function() {
                        if (operateType === 'save') {
                            WorkFlow.doSaveList(basePath + 'valuation/basicdata/asset/bond/FinBondTargetAsset002/save',
                                object, "FinBondTargetAsset002_btn_save");
                        } else {
                            WorkFlow.doSubmitList(basePath + 'valuation/basicdata/asset/bond/FinBondTargetAsset002/save',
                                object, "FinBondTargetAsset002_btn_submit");
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
            if(BusiFinExercisePlan){
                BusiFinExercisePlan.clearAllRows();
            }
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

    /**
     * 付息信息画面显示/隐藏
     */
    var showFinPayInterestStdInfoDiv = function(){
        var obj = $('#finPayInterestStdInfoDto_div');
        getPayInterestStdInfoElementById('endTdayRule','div').hide();
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
			} else if (id == 'isRight') {
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
           else {
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
                        //修改债券快录资产代码后缀名出错 by 赵东东 start
                    	//AssetCommon.setTradeMarketId(value);
                      //修改债券快录资产代码后缀名出错 by 赵东东 end
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
		    	}else{
		    		element.val(value);
		    	}
			}
	    }
    //从接口获取债券信息
    var finCashCalcRuleList = [];//加券利息
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
    		Joyin.ajax(basePath + 'valuation/basicdata/asset/bond/FinBondTargetAsset002/getInterfBondDto?finprodMarketId='
   				 + finprodMarketId + '&tradeMarketId=' + $('#tradeMarketId').val(),'' , function(result){
    			if (result.data[0]) {
    				Joyin.error(result.data[0].message);
				} else {
                    finCashCalcRuleList = result.data.finCashCalcRuleList;
                    //组件赋值 先清后赋
                    $.each(Joyin.curfromData['FinBondTargetAsset002'],function(id, value){
                        resetForm(id, value.odata, '');
                    });
                    $.each(result.data, function(id, value) {
                        setData(id, value, '');
                    });
                    $('#repAssetType').val(result.data.repAssetType).trigger('change');
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
                conPriceChange();
    			  layui.form.render();
   		 });
		} else {
			Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel('FinBondTargetAsset002.finprodId')));
		}
    }
    var issuerChange = function(){
        var customerId = $('#issuer').val();
        //AssetCommon.getRepInfoByIssuer(customerId);
    }
    window.componentLoadCallback = function(isDone, pagePath, params){
        if(isDone){
            //计息要素展示/隐藏
            showFinInterestFactorDiv();
            //付息要素隐藏
            showFinPayInterestStdInfoDiv();
            isRightChange();
            //息票品种
            if(FinInterestFactor){
                getInterestFactorElementById('couponSpecies').change(FinInterestFactor.handleCouponSpecies).trigger("change");
            }
          //该资产存在交易 且为非 赋值界面
            if(isTransInAsset && YES_OR_NO_Y != COPY_FLAG){
                $('#vdate').attr('disabled',true).trigger('change');
                /*$('#mdate').attr('disabled',true).trigger('change');*/
                $('#ccy').attr('disabled',true).trigger('change');
                $('#tradeMarket').attr('disabled',true).trigger('change');
                $('#isRight').attr('disabled',true).trigger('change');
                getInterestFactorElementById('rate').attr('disabled',true).trigger('change');
                getInterestFactorElementById('couponSpecies').attr('disabled',true).trigger('change');
                getInterestFactorElementById('indexId').attr('disabled',true).trigger('change');
                getInterestFactorElementById('coefficient').attr('disabled',true).trigger('change');
                getInterestFactorElementById('spreadRate').attr('disabled',true).trigger('change');
                $('#registOrg').attr('disabled', true).trigger('change');
                $('#assetType').attr('disabled',true).trigger('change');
                //置灰付息计划生成计划按钮
                /*$('#finPayInterestStdInfoDto_btn_generationPlan').off('click').prop('disabled', true).addClass('layui-btn-disabled');*/
            }
           if(isHqTrade && YES_OR_NO_Y != COPY_FLAG){
        	   $('#isRight').attr('disabled',true).trigger('change');
           }
           /*$('#bussFinBondRepInfoDto_repAssetType_div').show();*/
        }
    }
    var init = function(){
       //资产代码拆分
       var finprodMarketId =  $('#finprodMarketId').val();
       var tradeMarket = $('#tradeMarket').val();
       if (finprodMarketId){
           // $('#finprodMarketId').val(finprodMarketId.split('.')[0]);
           // if (finprodMarketId.split('.')[0] != finprodMarketId) {
        	//    $('#tradeMarketId').val('.' + finprodMarketId.split('.')[1]);
           // }
           var reObj = setInitFinprodMarketId(tradeMarket, finprodMarketId);
           $('#tradeMarketId').val(reObj.tradeMarket);
           $('#finprodMarketId').val(reObj.finprodMarketId);

       } 
    	star();
    }

    /**
     * 改变核算分类的价格
     */
    var changeAcccountintType = function() {
        var assetType =  $('#assetType').val();
        var tradeMarket =  $('#tradeMarket').val();
        var blnArea =  $('#blnArea').val();
        Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/bond/FinBondTargetAsset002/getFinanceProductClass',
            {assetType:assetType,tradeMarket:tradeMarket,blnArea:blnArea}, function(data){
            if(data && data.accountintType) {
                $("#accountintType").textSelect('setValue',data.accountintType);
            }
            else {
                $("#accountintType").textSelect('setValue',"");
            }
        });
    }

    /**
     * 改变税务分类的价格
     */
    var changeTaxType = function() {
        var assetType =  $('#assetType').val();
        var tradeMarket =  $('#tradeMarket').val();
        var blnArea =  $('#blnArea').val();
        Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/bond/FinBondTargetAsset002/getFinanceProductTaxClass',
            {assetType:assetType,tradeMarket:tradeMarket,blnArea:blnArea}, function(data){
                if(data && data.taxType) {
                    $("#taxType").textSelect('setValue',data.taxType);
                }
                else {
                    $("#taxType").textSelect('setValue',"");
                }
            });
    }

    /**
     * 资产分类改变触发
     */
    var assetTypeChange = function(){
        //如果为可转债、可交换债
        if($('#assetType').val() == BOND_TYPE_028 || $('#assetType').val() == BOND_TYPE_029){
            $('#FinProductConvertible_div').show();
        }else{
            $('#FinProductConvertible_div').hide();
        }
    }

    var conPriceChange = function(){
        if($('#conPrice').val()!=""){
            $('#conPrent').val(JoyinMath.doDiv(100,$('#conPrice').val(),2));
        }
    }

    //复制跳转过来
    var copyFunc = function() {
        if(YES_OR_NO_Y == COPY_FLAG) {
            //$("#finprodMarketId").val("");
            //$("#finprodId").val("");
            //$("#circulationCode").val("");
            //$("#finprodName").val("");
            //$("#finprodAbbr").val("");
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
        $('#FinBondTargetAsset002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#FinBondTargetAsset002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        // $('#tradeMarket').change(function(){
		// 	AssetCommon.setTradeMarketId($('#tradeMarket').val());
		// });
        $('#issuer').change(issuerChange);
        $('#tradeMarket').change(function(){
			AssetCommon.setTradeMarketId($('#tradeMarket').val());
		});
        $('#magnifier').click(getInterfBondInfo);
        $('#mdate,#vdate').change(function(obj){
            obj.type = FINPROD_TYPE_F01;
            AssetCommon.checkDate(obj);
        });
        $('#tradeMarket,#blnArea,#assetType').change(function(){
            changeAcccountintType();
            changeTaxType();
        });
        $('#assetType').change(function(){
            assetTypeChange();
        });
        $("#pflsh").change(function(){
            // var str = '';
            // if($('#pflsh').select2('data')[0].object){
            //   str = $('#pflsh').select2('data')[0].object.split(',');
            // }
            //
        	// if(str != null && str.length==3){
        	// 	$("#seqno").val(str[0]);
        	// 	$("#creditMstId").val(str[1]);
        	// 	$("#creditFlag").val(str[2]);
        	// }
        	// if(str[2] == 'Y'){
        	// 	$("#actualCreditProvid").attr("required", true).trigger('change');
        	// }
        	// if(str[2] != 'Y'){
        	// 	$("#actualCreditProvid").attr("required", false).trigger('change');
        	// }
        	star();
        });


        $('#conFinprodMarketId').change(function () {
            var str = $('#conFinprodMarketId').select2('data')[0];
            if (str){
                $('#conFinprodId').val(str.object);
                $('#conFinprodName').textSelect('setValue', $('#conFinprodMarketId').val());
            }else{
                $('#conFinprodId').val("");
                $('#conFinprodName').textSelect('setValue', "");
            }
        })
        //转股价格
        $('#conPrice').change(function () {
            conPriceChange();
        })
        conPriceChange();
        copyFunc();
        assetTypeChange();
    });
});
