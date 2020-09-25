var FinProductCommon = function() {
    return {
        
    	getElementById : function(id) {
    		return $('#' + pageId + '_' + id);
    	},
    	/**
         * 处理模式切换
         */
        dealModeSelect : function() {
            var dealMode = FinProductCommon.getElementById('dealMode').val();
            if (dealMode === '') {
                // 收益类型
            	FinProductCommon.getElementById('profitType').prop('disabled', false);
                
            	// 开放类型
            	FinProductCommon.getElementById('operationType').prop('disabled', false).trigger("change");
                // 利率类型
                FinProductCommon.getElementById('calcType').prop('disabled', false).trigger("change");
                // 是否周期型
                FinProductCommon.getElementById('isCycle').prop('checked', false).attr('disabled',false);
                // 封闭期限
                FinProductCommon.getElementById('closeDays').val('').trigger("change").prop('disabled', true);
                // 是否分层和分层类型
                FinProductCommon.getElementById('isLay').prop('checked', false).attr('disabled',false);
                FinProductCommon.getElementById('layType').prop('disabled', true);
                // 预期收益率（产品时需要）
                if (pageId === 'FinProductBuild002'||pageId === 'FinNetValueProductBuild002'){
                	FinProductCommon.getElementById('yield').prop('disabled', false);
                }
            }
            /* 当处理模式，选择净值型/货币型时
             *  产品类型联动带出净值型/货币型 ； 利率类型/是否分层/分层类型 置灰不可编辑
             */ 
            else if (dealMode === DISPOSE_PATTERN_08 || dealMode === DISPOSE_PATTERN_09) {
                // 收益类型
                if (dealMode === DISPOSE_PATTERN_08) {
                    FinProductCommon.getElementById('profitType').val(PROFIT_TYPE_03).trigger("change").prop('disabled', true);
                } else {
                    FinProductCommon.getElementById('profitType').val(PROFIT_TYPE_01).trigger("change").prop('disabled', true);
                }
                
                // --(开放类型)     发行方式
                FinProductCommon.getElementById('operationType').prop('disabled', false).trigger("change");
                // 利率类型
                FinProductCommon.getElementById('calcType').val('').prop('disabled', true).trigger("change");
                // 是否周期型
                FinProductCommon.getElementById('isCycle').prop('checked', false).attr('disabled',false);
                // 封闭期限
                FinProductCommon.getElementById('closeDays').val('').trigger("change").prop('disabled', true);
                // 是否分层和分层类型
                /*FinProductCommon.getElementById('isLay').prop('disabled',true).prop('checked', false);*/
//            	FinProductCommon.getElementById('layType').parent().prev().find('i.must').remove();
                FinProductCommon.getElementById('layType').val('').prop('disabled', true).trigger("change");
                // 预期收益率（产品时需要）
                if (pageId === 'FinProductBuild002'||pageId === 'FinNetValueProductBuild002'){
                	FinProductCommon.getElementById('yield').val('').prop('disabled', true);
                }
            } else {
                // 收益类型
            	if (pageId === 'FinProductBuild002'){
                   FinProductCommon.getElementById('profitType').val(PROFIT_TYPE_02).trigger("change").prop('disabled', true);
            	}
            	//周期、分档周期型 投资周期显示
            	if(dealMode === DISPOSE_PATTERN_01 || dealMode === DISPOSE_PATTERN_11){
            	    FinProductCommon.getElementById('investmentCycle').attr('checked',false).val("").parent().parent().hide();
            	} else {
            	    FinProductCommon.getElementById('investmentCycle').val("").parent().parent().show();
            	}
        		// 开放类型  add zhangweiqi 开发类型放开
               // FinProductCommon.getElementById('operationType').prop('disabled', true);
                if (dealMode === DISPOSE_PATTERN_01) {
                  //  FinProductCommon.getElementById('operationType').val(ISSUE_MODE_02).trigger("change");
                    
                    FinProductCommon.getElementById('continueFlag').attr('checked',false).val("").parent().parent().hide();
                    if (pageId === 'FinNetValueProductBuild002'){
                    	$("#"+pageId+"_continueFlag").val('').trigger("change").prop('disabled', false);
                    	$("#"+pageId+"_operationType").prop('disabled', false);
	                    $("#bussFinProductOpenPlanDto_sameFLag").prop('disabled', false).parent().parent().show();
                    }
                   
                } else {
                	 FinProductCommon.getElementById('continueFlag').val("").parent().parent().show();
                	 if (pageId === 'FinNetValueProductBuild002'){
                		 $("#"+pageId+"_continueFlag").val('N').trigger("change").prop('disabled', true);
//                		 $("#"+pageId+"_operationType").val('02').trigger("change").prop('disabled', true);
                		//根据需求要求去掉
//	                     $("#bussFinProductOpenPlanDto_sameFLag").prop('disabled', true).parent().parent().hide();
                	 }
                }
                // 利率类型
                FinProductCommon.getElementById('calcType').prop('disabled', false).trigger("change");
                
        		// 是否周期型
                if (dealMode === DISPOSE_PATTERN_03 || dealMode === DISPOSE_PATTERN_04 || dealMode === DISPOSE_PATTERN_05) {
                	FinProductCommon.getElementById('isCycle').prop('checked', true);
                } else {
                	FinProductCommon.getElementById('isCycle').prop('checked', false);
                }
                FinProductCommon.getElementById('isCycle').prop('disabled', true);
                // 封闭期限
                if (dealMode === DISPOSE_PATTERN_03 || dealMode === DISPOSE_PATTERN_04) {
                    FinProductCommon.getElementById('closeDays').prop('disabled', false);
                } else if (dealMode === DISPOSE_PATTERN_05){
                	var closeDays = '';
                	if (pageId === 'FinProductBuild002'||pageId === 'FinNetValueProductBuild002'){
                		closeDays = FinProductCommon.getElementById('termDays').val();
                	}
                    FinProductCommon.getElementById('closeDays').val(closeDays).prop('disabled', true);
                } else{
                	FinProductCommon.getElementById('closeDays').val('').prop('disabled', true);
                }
                
                // 是否分层和分层类型
                FinProductCommon.getElementById('isLay').prop('checked', false).attr('disabled',false);
                if(pageModel === PAGE_MODEL_NEW) {
                	FinProductCommon.getElementById('layType').val('').trigger('change');
//                	FinProductCommon.getElementById('layType').parent().prev().find('i.must').remove();
                }
                FinProductCommon.getElementById('layType').prop('disabled', true);
                // 预期收益率（产品时需要）
                if (pageId === 'FinProductBuild002'||pageId === 'FinNetValueProductBuild002'){
                	FinProductCommon.getElementById('yield').prop('disabled', false);
                }
            }
            layui.form.render('checkbox');
        },
        /**
         * 收益类型切换
         */
        profitTypeSelect : function(){
        	var profitType = FinProductCommon.getElementById('profitType').val();
            FinProductCommon.getElementById('calcType').parent().prev().find('i.must').remove();
        	if (profitType === '') {
        		// 开放类型
            	FinProductCommon.getElementById('operationType').prop('disabled', false).trigger("change");
                // 利率类型
                FinProductCommon.getElementById('calcType').prop('disabled', false).trigger("change");
                // 是否周期型
                FinProductCommon.getElementById('isCycle').prop('checked', false).attr('disabled',false);
                // 封闭期限
                FinProductCommon.getElementById('closeDays').val('').trigger("change").prop('disabled', true);
                // 是否分层和分层类型
                FinProductCommon.getElementById('isLay').prop('checked', false).attr('disabled',false);
                FinProductCommon.getElementById('layType').prop('disabled', true);
                // 预期收益率（产品时需要）
                if (pageId === 'FinProductBuild002'||pageId === 'FinNetValueProductBuild002'){
                	FinProductCommon.getElementById('yield').prop('disabled', false);
                }
        	}else if (profitType === PROFIT_TYPE_01 || profitType === PROFIT_TYPE_03){
        		
        		// 开放类型
                FinProductCommon.getElementById('operationType').prop('disabled', false).trigger("change");
                // 利率类型
                FinProductCommon.getElementById('calcType').val('').prop('disabled', true).trigger("change");
                // 是否周期型
                FinProductCommon.getElementById('isCycle').prop('checked', false).attr('disabled',false);
                // 封闭期限
                FinProductCommon.getElementById('closeDays').val('').trigger("change").prop('disabled', true);
                // 是否分层和分层类型
                FinProductCommon.getElementById('isLay').prop('checked', false).attr('disabled',true);
                FinProductCommon.getElementById('layType').val('').trigger("change").prop('disabled', true);
                // 预期收益率（产品时需要）
                if (pageId === 'FinProductBuild002'||pageId === 'FinNetValueProductBuild002'){
                	FinProductCommon.getElementById('yield').val('').prop('disabled', true);
                }
                //update zhangweiqi add 2019-01-16 begin
                
                if (pageId === 'FinNetValueProductBuild002'){
                	
                    if(profitType === PROFIT_TYPE_03){
                		FinProductCommon.getElementById('dealMode').val(DISPOSE_PATTERN_01).trigger("change").prop('disabled', true);
                		$("#"+pageId+"_investmentCycle").val('').parent().parent().css("display","none");
                		$("#"+pageId+"_continueFlag").val('').parent().parent().css("display","none");
                	}else{
                		 FinProductCommon.getElementById('dealMode').trigger("change").prop('disabled', false);
                		var isExist = false;
                		var count = $('#'+pageId+"_payType").find('option').length;
                		for(var i=0;i<count;i++){
                			if($('#'+pageId+"_payType").get(0).options[i].value =="03"){
                				isExist = true;break;
                				}
                			}
                		
                		
                		if(!isExist)
                		/*$("#"+pageId+"_payType").append('<option value="03" mka="混合模式" mkb="hunhemoshi" mkc="hhms">混合模式</option>');*/
                		if(FinProductCommon.getElementById('dealMode').val() != "01"){//add by yejiejie 2019-2-21
	                		$("#"+pageId+"_investmentCycle").val('').parent().parent().css("display","inline");
	                		$("#"+pageId+"_continueFlag").val('').parent().parent().css("display","inline");
                		}
                	}
                }
                //update zhangweiqi add 2019-01-16 end
        	} else {
        		// 开放类型
                //FinProductCommon.getElementById('operationType').prop('disabled', true).trigger("change");
                // 利率类型
                FinProductCommon.getElementById('calcType').prop('disabled', false).trigger("change");
                FinProductCommon.getElementById('calcType').parent().prev().append('<i class="must">*</i>');
                // 是否分层和分层类型
                FinProductCommon.getElementById('isLay').prop('checked', false).attr('disabled',false);
                FinProductCommon.getElementById('layType').prop('disabled', true);
                // 预期收益率（产品时需要）
                if (pageId === 'FinProductBuild002'||pageId === 'FinNetValueProductBuild002'){
                	FinProductCommon.getElementById('yield').prop('disabled', false);
                }
        	}
        	layui.form.render('checkbox');
        },
        /**
         * 开放类型切换
         */
        openModeSelect : function(){
        	var openMode = FinProductCommon.getElementById('operationType').val();
        	var profitType = FinProductCommon.getElementById('profitType').val();
        	if (openMode === ISSUE_MODE_01){
        		if (profitType === PROFIT_TYPE_02){
        			var dealMode = FinProductCommon.getElementById('dealMode').val();
            		// 是否周期型
                    if (dealMode === DISPOSE_PATTERN_03 || dealMode === DISPOSE_PATTERN_04 || dealMode === DISPOSE_PATTERN_05) {
                        FinProductCommon.getElementById('isCycle').prop('checked', true);
                    } else {
                        FinProductCommon.getElementById('isCycle').prop('checked', false);
                    }
                    FinProductCommon.getElementById('isCycle').prop('disabled', true);
                    // 封闭期限
                    if (dealMode === DISPOSE_PATTERN_03 || dealMode === DISPOSE_PATTERN_04) {
                        FinProductCommon.getElementById('closeDays').prop('disabled', false);
                    } else {
                        FinProductCommon.getElementById('closeDays').val('').prop('disabled', true);
                    }
            	}else{
            		// 是否周期型
            		FinProductCommon.getElementById('isCycle').prop('checked', false).attr('disabled',false);
                    // 封闭期限
                    FinProductCommon.getElementById('closeDays').val('').prop('disabled', true);
            	}
        	}else{
        		if (profitType === PROFIT_TYPE_02){
        			// 是否周期型
                    FinProductCommon.getElementById('isCycle').prop('checked', false).attr('disabled',true);
                    // 封闭期限
                    FinProductCommon.getElementById('closeDays').val('').prop('disabled', true);
        		}else{
        			// 是否周期型
        			FinProductCommon.getElementById('isCycle').prop('checked', false).attr('disabled',false);
                    // 封闭期限
                    FinProductCommon.getElementById('closeDays').val('').prop('disabled', true);
        		}
        	}
        	layui.form.render('checkbox');
        },
        /**
         * 是否周期联动处理封闭期限
         */
        isCycleSelect : function(){
        	var isCycle = FinProductCommon.getElementById('isCycle')[0].checked;
    		if (isCycle){
    			var dealMode = FinProductCommon.getElementById('dealMode').val();
    			if (dealMode === DISPOSE_PATTERN_05){
    				FinProductCommon.getElementById('closeDays').val('').prop('disabled', true);
    			}else{
    				FinProductCommon.getElementById('closeDays').prop('disabled', false);
    			}
    		}else{
    			FinProductCommon.getElementById('closeDays').val('').prop('disabled', true);
    		}
        },
        /**
         * 是否分层联动处理分层类型
         */
        isLayerSelect : function(){
        	var isLay = FinProductCommon.getElementById('isLay').is(':checked');
        	var layType = FinProductCommon.getElementById('layType');
        	if (isLay) {
        		layType.prop('disabled', false);
//        		layType.parent().prev().append('<i class="must">*</i>');
        		// 预期收益率（产品时需要）
        		if (pageId === 'FinProductBuild002'){
        			FinProductCommon.getElementById('yield').val('').prop('disabled', true);
                }
        	} else{
//        		layType.parent().prev().find('i.must').remove();
        		layType.val('').trigger('change').prop('disabled', true);
        		// 预期收益率（产品时需要）
                if (pageId === 'FinProductBuild002'){
                	var profitType = FinProductCommon.getElementById('profitType').val();
            		if (profitType === PROFIT_TYPE_02){
            			FinProductCommon.getElementById('yield').prop('disabled', false);
            		}else{
            			FinProductCommon.getElementById('yield').val('').prop('disabled', true);
            		}
                }
        	}
        },
        isSus:function(){
    		if((FinProductCommon.getElementById('isSus').next().find("em").text()==="是") || FinProductCommon.getElementById('isSus').is(':checked')){
    		 //add by zlp 20190320 start 是否永续选择是，清盘日为2099-12-31，产品期限为空
    		 FinProductCommon.getElementById('mdate').val('2099-12-31').prop('disabled',true);
    		 FinProductCommon.getElementById('actMdate').val('2099-12-31').prop('disabled',true);	
    		 /*FinProductCommon.getElementById('termDays').val('').prop('disabled',true);*/
    		 FinProductCommon.getElementById('liquidationDate').val('2099-12-31').prop('disabled',true);
    		 //add by dingjunfeng start
    		 $('#bussFinProductFloatFeeDto_eAccrDate').val('').prop('disabled',false);
    		 //add by dingjunfeng end
    		}else{
    			if ('2099-12-31' == FinProductCommon.getElementById('mdate').val()) {
					FinProductCommon.getElementById('mdate').val('').prop('disabled',false);
				}
    			else {
					FinProductCommon.getElementById('mdate').prop('disabled',false);
				}

    			FinProductCommon.getElementById('liquidationDate').val('').prop('disabled',false);
    			FinProductCommon.getElementById('actMdate').val('').prop('disabled',false);
    			/*add by dingjunfeng */
                var feeID=$("#bussFinProductFloatFeeDto_feeId").val();  /*bugTISHKBS-1157 增加判断浮动管理费是否选择，没有视为还没有设置浮动管理费*/
                if( feeID != '' && feeID != null){
    			    $('#bussFinProductFloatFeeDto_eAccrDate').val(FinProductCommon.getElementById('mdate').val()).prop('disabled',false);
    			//add by dingjunfeng end
    			}
    		}
    		 FinProductCommon.getElementById('mdate').trigger("change");//SHBS-1802
    	},
    	//add by yejiejie 2019-3-12 start
    	prodSeriesChange:function(){
    		if(FinProductCommon.getElementById('prodSeries').val()=='01'){
    			//福通----产品收益标志：03-非保本浮动收益   	
				FinProductCommon.getElementById('profitFlag').append(
                "<option value='03'>非保本浮动收益</option>");
				//add by gcl 2019/3/14 start
				FinProductCommon.getElementById('profitFlag').val("03").trigger("change");
				//add by gcl 2019/3/14 end
    		}else if(FinProductCommon.getElementById('prodSeries').val()=='02'){
    			//恒通----产品收益标志：01-保证收益	;02-保本浮动收益	
				FinProductCommon.getElementById('profitFlag').append(
                "<option value='01'>保证收益</option>"); 
				FinProductCommon.getElementById('profitFlag').append(
                "<option value='02'>保本浮动收益</option>");
    		}else{    			
    			FinProductCommon.getElementById('profitFlag').append(
                "<option value='01'>保证收益</option>"); 
				FinProductCommon.getElementById('profitFlag').append(
                "<option value='02'>保本浮动收益</option>");
				FinProductCommon.getElementById('profitFlag').append(
                "<option value='03'>非保本浮动收益</option>"); 
    		}
    	},
    	//add by yejiejie 2019-3-12 end
        /**
         * 发行方式改变触发的事件
         * 当“发行方式”选择集合时，分行字段可多选或为空，
         * 当“发行方式”选择单一时，分行字段默认必填；分行字段枚举值从部门管理模块里获取。 
         */
        issueModeSelect: function() {
        	// 发行方式
        	var value = $(this).val();
        	var branchBank = FinProductCommon.getElementById('entruster');
        	if(value === SCHEDUAL_ISSUE_02) {
        		branchBank.parent().prev().append('<i class="must">*</i>');
        	}
        	else {
        		branchBank.parent().prev().find('i.must').remove();
        	}
        },
        //add by yejiejie 2019-3-8 start
        //产品序列对应的字母
        prodSeriesStr: function(data){
        	var prodSeriesStr = null;
        	var prodSeriesVal = FinProductCommon.getElementById('prodSeries').val();
        	if(data ==1){
	        	if(prodSeriesVal == '01'){
	        		//福通--A
	        		prodSeriesStr ='A';
	        	}
	        	if(prodSeriesVal == '02'){
	        		//恒通--B
	        		prodSeriesStr ='B';
	        	}
        	}
        	if(data==2){
        		if(prodSeriesVal == '01'){
	        		//福通--A
	        		prodSeriesStr ='福通';
	        	}
	        	if(prodSeriesVal == '02'){
	        		//恒通--B
	        		prodSeriesStr ='恒通';
	        	}
        	}
        	return prodSeriesStr;
        },
        //产品特点对应的字母
        prodFeatureStr: function(){
        	var prodFeatureStr = null;
        	var prodFeatureVal = FinProductCommon.getElementById('prodFeature').val();
        	switch(prodFeatureVal){
	        	case "01":
	        		prodFeatureStr='A';
	        		break;
	        	case "02":
	        		prodFeatureStr='B';
	        		break;
	        	case "03":
	        		prodFeatureStr='C';
	        		break;
	        	case "04":
	        		prodFeatureStr='D';
	        		break;
	        	case "05":
	        		prodFeatureStr='E';
	        		break;
	        	case "06":
	        		prodFeatureStr='F';
	        		break;
	        	case "07":
	        		prodFeatureStr='G';
	        		break;
	        	case "08":
	        		prodFeatureStr='H';
	        		break;
	        	case "09":
	        		prodFeatureStr='I';
	        		break;
	        	case "10":
	        		prodFeatureStr='J';
	        		break;
	        	case "11":
	        		prodFeatureStr='K';
	        		break;
	        	case "12":
	        		prodFeatureStr='L';
	        		break;
	        	case "13":
	        		prodFeatureStr='M';
	        		break;
	        	case "14":
	        		prodFeatureStr='N';
	        		break;
	        	case "15":
	        		prodFeatureStr='O';
	        		break;
	        	case "16":
	        		prodFeatureStr='P';
	        		break;
	        	case "17":
	        		prodFeatureStr='Q';
	        		break;
	        	case "18":
	        		prodFeatureStr='R';
	        		break;
	        	case "19":
	        		prodFeatureStr='S';
	        		break;
	        	case "20":
	        		prodFeatureStr='T';
	        		break;
	        	case "21":
	        		prodFeatureStr='U';
	        		break;
	        	case "22":
	        		prodFeatureStr='V';
	        		break;
	        	case "23":
	        		prodFeatureStr='W';
	        		break;
        	}
        	return prodFeatureStr;
        },
        //产品期次
        repFinprodStageStr: function(){
        	var repFinprodStageVal = FinProductCommon.getElementById('repFinprodStageC').val();
        	if(repFinprodStageVal<10){
        		repFinprodStageVal ="00"+repFinprodStageVal;
        	}
        	if(repFinprodStageVal>9 && repFinprodStageVal<100){
        		repFinprodStageVal ="0"+repFinprodStageVal;
        	}
        	return repFinprodStageVal;
        },
        newDate: function(){
        	var myDate = new Date();
        	var a = myDate.getFullYear();
        	var b = a-2000;
        	return b;
        },
        //add by yejiejie 2019-3-8 end
        /**
         * 绑定事件
         */
        bindEvent: function() {
        	//add by gcl 2019/3/28 start
        	//$("#"+pageId+"_payType").append('<option value="03" mka="混合模式" mkb="hunhemoshi" mkc="hhms">混合模式</option>');
        	//add by gcl 2019/3/28 end
        	if(this.getElementById('profitType').val()!='02'){//add by yejiejie 2019-1-26
	            // 处理模式切换
	        	this.getElementById('dealMode').change(this.dealModeSelect);
        	}
            // 收益类型切换
        	this.getElementById('profitType').change(this.profitTypeSelect);
        	// 发行方式切换
        	//this.getElementById('entrustType').change(this.issueModeSelect);
            // 是否分层change事件处理
            layui.form.on('switch(isLayFilter)', function(data){
            	FinProductCommon.isLayerSelect();
            });
            // 是否周期change事件处理
            layui.form.on('switch(isCycleFilter)', function(data){
            	FinProductCommon.isCycleSelect();
            });
            layui.form.on('switch(isSus)', function(data){
            	FinProductCommon.isSus();
            });
        },
        /**
         * 画面数据加载(页面Id, 产品代码或模板代码, 数据字典:金融产品类型_投管用_理财产品或系列模板)
         */
        initPageInfoAction : function(pageId, finprodMarketId, finprodType){
        	Joyin.ajax(JoyinUtil.getRealPath('/invest/product/common/checkFinMarketIdExist'), {finprodMarketId : finprodMarketId, finprodType : finprodType}, function(result){
				if (result && result.data){
				    FinProductCommon.clearFinprodMarketId(pageId);
					Joyin.confirm(JoyinUtil.getMessage('FIN_PROD_E_0002'), function() {
						
						// 特殊处理逻辑
						if ($('#FinProductBuild002_quickFlag')){
							$('#FinProductBuild002_quickFlag').val('Y');
						}
						
//			        	if(finprodType != finprodTypeTmpl) {
			        		// 初始化页面
							var prodInfo = result.data;
							//add by tuqirong 2019-03-25 start 获取开放计划中的确认天数
							purSpeed = result.data.purSpeed;
							redSpeed = result.data.redSpeed;
							//add by tuqirong 2019-03-25 end
							//快录时过滤产品登记编码
							prodInfo.prodRegistCode = '';
			        		// 基础信息
				        	FinProductCommon.initPageDataAction(pageId, prodInfo);
							// 开放计划
							BussFinProductOpenPlan.openPlanInitAction(prodInfo.finprodId);

							if (pageId == 'FinProductBuild002'){
	    						// 付息计划
	    						ProductPayPlan.payPlanInitAction(prodInfo.finprodId);
							}
							// 分红计划
							 if (pageId == 'FinNetValueProductBuild002'){
							   BussFinProductBonusPlan.bonusPlanInitAction(prodInfo.finprodId);
							 }
				        	// 费用信息
							BussFinProductFeeInfo.feeInfoInitAction(prodInfo.finprodId);
							//投资周期列表 （只有周期型多固定期限展示）
							if(prodInfo.dealMode === DISPOSE_PATTERN_10 && prodInfo.period === TERM_TYPE_02){
								BussFinProductInvestPeriod.finProductInvestPeriodInitAction(prodInfo.finprodId);
							} 
							
							//update zhangweiqi 快录展示资金账户
							BussFinProductFundAccountInfo.productFundAccountInitAction(prodInfo.finprodId,prodInfo.bussFinProductFundAccountInfoDto);
							//业绩回补合版本相关 start wangcheng 20191025
							//回补信息
							if(pageId === 'FinNetValueProductBuild002' || pageId === 'FinNetValueProductModule002'){//add by hujinjin 
								BussFinProductAnapleroticCommon.anapleroticInfo(prodInfo.finprodId,prodInfo.finProductAnapleroticTabInfoDtoList);
							}
							//业绩回补合版本相关 end wangcheng 20191025

							// 业绩基准
							if (pageId === 'FinNetValueProductBuild002'){
							    if(prodInfo.profitType === PROFIT_TYPE_01 && ((prodInfo.dealMode === DISPOSE_PATTERN_10 && prodInfo.period === TERM_TYPE_02 ) || prodInfo.dealMode === DISPOSE_PATTERN_11)){
							        BusslayerBenchMarkInfo.benchMarkInitAction(prodInfo.finprodId);
							    } else if(prodInfo.profitType === PROFIT_TYPE_01 && (prodInfo.dealMode === DISPOSE_PATTERN_10 && prodInfo.period != TERM_TYPE_02 )){
							        BussBenchMarkInfo.benchMarkInitActionForCycle(prodInfo.finprodId, prodInfo.dealMode);
							        BussBenchMarkFloatInfo.benchMarkFloatInitActionForCycle(prodInfo.finprodId, prodInfo.dealMode);
							    }
							    else {
							        BussBenchMarkInfo.benchMarkInitAction(prodInfo.finprodId);
							        BussBenchMarkFloatInfo.benchMarkFloatInitAction(prodInfo.finprodId);
							    }
							}
							
							// 浮动管理费
							if (pageId === 'FinNetValueProductBuild002'){
							    if(prodInfo.profitType === PROFIT_TYPE_01 && ((prodInfo.dealMode === DISPOSE_PATTERN_10 && prodInfo.period === TERM_TYPE_02 )|| prodInfo.dealMode === DISPOSE_PATTERN_11)){
							    	var floatEffDate = prodInfo.vdate;
							    	if(floatEffDate){
							    		floatEffDate = Date.ofDate(floatEffDate).format('yyyy-MM-dd');;
							    	}
							        BussLayerFinProductFloatFee.floatFeeInitAction(prodInfo.finprodId, floatEffDate);
							    } else {
							        BussFinProductFloatFee.floatFeeInitAction(prodInfo.finprodId);
							    }
							    
							}
							


							// 销售参数
						  if (pageId === 'FinProductBuild002'||pageId === 'FinNetValueProductBuild002'){
								BussFinProductSaleParam.saleParamInitAction(prodInfo.finprodId);
								//update zhangweiqi 快录功能
								$("#bussFinProductSaleParamDto_ccy").val(prodInfo.ccy).trigger('change');
							    $("#bussFinProductSaleParamDto_issueType").val(prodInfo.issueType).trigger('change');
							    $("#bussFinProductSaleParamDto_saleDepartment").val(prodInfo.saleDepartment).trigger('change');
							    $("#bussFinProductSaleParamDto_repInvestorType").val(prodInfo.repInvestorType).trigger('change');
							    $("#bussFinProductSaleParamDto_sameOrg").val(prodInfo.sameOrg).trigger('change');
							}
							// 估值信息
							BussFinProductValuation.valInitAction(prodInfo.finprodId);
							//产品投向 add by yanya 20180824
							if (pageId === 'FinProductBuild002'||pageId === 'FinNetValueProductBuild002'){
								BussFinProductGoAl.valInitAction(prodInfo.finprodId);
								
							}
							// 投向描述
							//update zhangweiqi
							
							//BussFinProductGoalInfo.goalInfoInitAction(prodInfo.finprodId);
							// 信息披露文档
							//BussFinProductRevalDocument.revalDocInitAction(prodInfo.finprodId);

							//产品分层
							if(prodInfo.dealMode === DISPOSE_PATTERN_11){
								BussFinProductLayered.layeredInitAction(prodInfo.finprodId,prodInfo.bussFinProductLayeredInfoDto);
							}
							// 中债报送数据初始化
							BussRepFinProductInfo.repInitAction(prodInfo.finprodId);
							 
//			        	} else {
//			        		// 基础信息
//				        	FinProductCommon.initPageDataAction(pageId, result.data.productModuleInfo);
//				        	
//				        	purSpeed = 1;
//				        	redSpeed = 1;
//				        	// 开放计划
//				        	
//				        	BussFinProductOpenPlan.openPlanInitActionByTmpl(result.data.bussFinProductOpenPlanDto)
//				        	if (pageId == 'FinProductBuild002'){
//	    						// 付息计划
//				        		FinProductCommon.initPageDataAction('bussFinProductPayPlanDto', result.data.bussFinProductPayPlanDto);
//							}
//							// 分红计划
//							 if (pageId == 'FinNetValueProductBuild002'){
//								 FinProductCommon.initPageDataAction('bussFinProductBonusPlanDto', result.data.bussFinProductBonusPlanDto);
//							 }
//							// 费用信息 
//							 BussFinProductFeeInfo.feeInfoInitActionByTmpl(result.data.bussFinProductFeeInfoDto);
//							 
//							//投资周期列表 （只有周期型多固定期限展示） !!!
//							if(result.data.productModuleInfo.dealMode === DISPOSE_PATTERN_10 && result.data.productModuleInfo.period === TERM_TYPE_02){
//								//TODO
//							}
//							
//							
//							// 业绩基准
//							if (pageId === 'FinNetValueProductBuild002'){
//							    if(result.data.productModuleInfo.profitType === PROFIT_TYPE_01 && ((result.data.productModuleInfo.dealMode === DISPOSE_PATTERN_10 && result.data.productModuleInfo.period === TERM_TYPE_02 ) || result.data.productModuleInfo.dealMode === DISPOSE_PATTERN_11)){
//							    	BusslayerBenchMarkInfo.benchMarkInitActionTmpl(result.data.bussBenchMarkLayeredInfoDto.bussBenchMarkLayeredInfoDtoList);
//							    } 
//							    else if(result.data.productModuleInfo.profitType === PROFIT_TYPE_01 && (result.data.productModuleInfo.dealMode === DISPOSE_PATTERN_10 && result.data.productModuleInfo.period != TERM_TYPE_02 )){
//							    	FinProductCommon.initPageDataAction('bussBenchMarkInfoDto', result.data.bussBenchMarkInfoDto);
//							    	FinProductCommon.initPageDataAction('bussBenchMarkFloatInfoDto', result.data.bussBenchMarkFloatInfoDto);
//							    }
//							    else {
//							    	FinProductCommon.initPageDataAction('bussBenchMarkInfoDto', result.data.bussBenchMarkInfoDto);
//							    	FinProductCommon.initPageDataAction('bussBenchMarkFloatInfoDto', result.data.bussBenchMarkFloatInfoDto);
//							    }
//							}
//							
//							// 浮动管理费
//							if (pageId === 'FinNetValueProductBuild002'){
//							    if(result.data.productModuleInfo.profitType === PROFIT_TYPE_01 && ((result.data.productModuleInfo.dealMode === DISPOSE_PATTERN_10 && result.data.productModuleInfo.period === TERM_TYPE_02 )|| result.data.productModuleInfo.dealMode === DISPOSE_PATTERN_11)){
//							    	BussLayerFinProductFloatFee.floatFeeInitActionTmpl(result.data.bussBenchMarkLayeredInfoDto.bussBenchMarkLayeredInfoDtoList);
//							    } else {
//							    	
//							    	FinProductCommon.initPageDataAction('bussFinProductFloatFeeDto', result.data.bussFinProductFloatFeeDto);
//							    }
//							    
//							}
//							
//							
//							// 销售参数
//							  if (pageId === 'FinProductBuild002'||pageId === 'FinNetValueProductBuild002'){
//									FinProductCommon.initPageDataAction('bussFinProductSaleParamDto', result.data.bussFinProductSaleParamDto);
//									$("#bussFinProductSaleParamDto_ccy").val(result.data.productModuleInfo.ccy).trigger('change');
//								    $("#bussFinProductSaleParamDto_issueType").val(result.data.productModuleInfo.issueType).trigger('change');
//								    if(result.data.productModuleInfo.saleDepartment){
//					    				$("#bussFinProductSaleParamDto_saleDepartment").val(result.data.productModuleInfo.saleDepartment.split(",")).trigger('change');
//				    				}
//				    				$("#bussFinProductSaleParamDto_repInvestorType").val(result.data.productModuleInfo.repInvestorType).trigger('change');
//				    				if(result.data.productModuleInfo.sameOrg){
//				    					$("#bussFinProductSaleParamDto_sameOrg").val(result.data.productModuleInfo.sameOrg.split(",")).trigger('change');
//				    				}
//								    //add by yzg 收益型托管机构单独处理
//								    if(pageId === 'FinProductBuild002'){
//								    	$("#FinProductBuild002_truOrg").textSelect('setValue',result.data.productModuleInfo.trusteeId).trigger('change');
//								    }
//								}
//								// 估值信息
//								FinProductCommon.initPageDataAction('bussFinProductValuationDto', result.data.bussFinProductValuationDto);
//								//产品投向 add by yanya 20180824
//								if (pageId === 'FinProductBuild002'||pageId === 'FinNetValueProductBuild002'){
//									BussFinProductGoAl.valInitAction(result.data.finprodId);
//									
//								}
//
//								//产品分层
//								if(result.data.productModuleInfo.dealMode === DISPOSE_PATTERN_11){
//									BussFinProductLayered.layeredInitActionTmpl(result.data.bussFinProductLayeredInfoDto);
//								}
//								// 中债报送数据初始化
//								FinProductCommon.initPageDataAction('bussRepFinProductInfoDto', result.data.bussRepFinProductInfoDto);
//			        	}

					});
				} else {
                    FinProductCommon.clearFinprodMarketId(pageId);
                }
			});
        },
        
        clearFinprodMarketId : function(pageId){
            if (pageId != 'FinProductBuild002'&&pageId != 'FinNetValueProductBuild002'){
                $('#FinProductModule002_finprodMarketId').val('');
            }
        },
        
        /**
         * 产品标识码带出申报信息不可修改
         * yzg add 20191113 start 
         */
        initDisabledChange : function(entityName, data){
        	$.each(data, function(id,value){
        		if ($('#' + entityName + '_' + id).length > 0){
        			if(id == 'repInvestArea' || id == 'repCmmode'){
        				$('#' + entityName + '_' + id).prop("disabled",true).trigger('change');
        			}else{
        				$('#' + entityName + '_' + id).prop("disabled",true);
        			}
        		}
        		if(entityName == 'bussRepFinProductInfoDto'){
        			$('#' + entityName + '_' + 'repFinprodBrand').prop("disabled",true);
        			$('#' + entityName + '_' + 'isShortTerm').prop("disabled",true);
        			$('#' + entityName + '_' + 'repInDepCode').prop("disabled",true);
        			$('#' + entityName + '_' + 'repRemark').prop("disabled",true);
        		}
        	})
        },
        
        /**
         * 画面数据加载
         */
        initPageDataAction : function(entityName, data){
        	// 清空值
//        	$("[id^='" + entityName + "_']").val('').trigger('change');
        	$.each(data, function(id,value){
        		//if("bussFinProductOpenPlanDto"==entityName)
				if ($('#' + entityName + '_' + id).length > 0){
					//update by gcl 2019/3/17 start
					// 快速录入不需要模板代码、市场代码、金融产品代码,组合代码
					if (id != 'tmplId' && id != 'finprodMarketId' && id != 'finprodId'&& id != 'ptlId'){
						//update by gcl 2019/3/17 end	
						// switch 特殊处理
						if ($('#' + entityName + '_' + id).attr('lay-skin') === 'switch'){
							$('#' + entityName + '_' + id).prop('checked', false);
							// 是否,有无
							if (value === 'Y' || value === '01'){
								$('#' + entityName + '_' + id).prop('checked', true);
							}
							layui.form.render('checkbox');
							
							if (id === 'isLay'){
								FinProductCommon.isLayerSelect();
							}
							if (id === 'isSus'){
								FinProductCommon.isSus();
							}
							if (id === 'isCycle'){
								FinProductCommon.isCycleSelect();
							}
							// 中债的 有无预期收益率  和  增信标识
							if (id === 'repIsYield'){
								BussRepFinProductInfo.repIsYieldChange();
							}
							if (id === 'repCreditFlag'){
								BussRepFinProductInfo.repCreditFlagChange();
							}
						}else{
							// 清空值
							$('#' + entityName + '_' + id).val('');
							
							// 日期特殊处理
							if ($('#' + entityName + '_' + id).hasClass('input-dateSelect')){
								$('#' + entityName + '_' + id).val(Date.ofDate(value).format('yyyy-MM-dd'));
							}
							// data-url 下拉框
							else if ($('#' + entityName + '_' + id).attr("data-url")){
								$('#' + entityName + '_' + id).textSelect('setValue', value); 
							}else{
								// 多选下拉框特殊处理
								if ($('#' + entityName + '_' + id).attr('multiple') === 'multiple'){
									$('#' + entityName + '_' + id).val(value.split(",")).trigger('change');
									
									// 开放计划特殊处理radio
									if (entityName === 'bussFinProductOpenPlanDto' && value != ''){
										if (id === 'dayOfMonth'){
											$('#' + entityName + '_div').find(':radio[value="0"]').prop('checked', true);
											$('#' + entityName + '_div').find(':radio[value="1"]').prop('checked', false);
											$('#' + entityName + '_div').find(':radio[value="2"]').prop('checked', false);
											$('#' + entityName + '_div').find(':radio').prop('disabled', false);
											layui.form.render();
										}
										if (id === 'workdayOfMonth'){
											$('#' + entityName + '_div').find(':radio[value="0"]').prop('checked', false);
											$('#' + entityName + '_div').find(':radio[value="1"]').prop('checked', true);
											$('#' + entityName + '_div').find(':radio[value="2"]').prop('checked', false);
											$('#' + entityName + '_div').find(':radio').prop('disabled', false);
											layui.form.render();
										}
										if (id === 'weekOfMonth'){
										    $('#' + entityName + '_div').find(':radio[value="0"]').prop('checked', false);
                                            $('#' + entityName + '_div').find(':radio[value="1"]').prop('checked', false);
                                            $('#' + entityName + '_div').find(':radio[value="2"]').prop('checked', true);
                                            $('#' + entityName + '_div').find(':radio').prop('disabled', false);
                                            layui.form.render();
                                        }
									}
								} else if(id === 'profitType' && entityName === 'FinProductBuild002'){
								    $('#' + entityName + '_' + id).val(PROFIT_TYPE_02).trigger('change');
								} else{
									$('#' + entityName + '_' + id).val(value).trigger('change');
								}
							}
						}
					}
				}
			});
        	//add by tuqirong 2019-03-25 start 给开放计划的确认天数赋值
			if (entityName=="bussFinProductOpenPlanDto") {
				$('#' + entityName + '_redSpeed').val(redSpeed);
				$('#' + entityName + '_purSpeed').val(purSpeed);
			}
			//add by tuqirong 2019-03-25 end
		// 根据参数决定是否显示时间值 01是 02否 add by chenxiaoqiu 20191122
			if (pageId === 'FinProductBuild002'|| pageId === 'FinNetValueProductBuild002') {
				if (isDisplayDate == NO && quickInput) {
					// 清空募集开始日 募集结束 日
					$("#bussFinProductSaleParamDto_firstSaleVdate").val('');
					$("#bussFinProductSaleParamDto_firstSaleMdate").val('');
					// 首次计提日 计提结束日
					$("#bussFinProductFloatFeeDto_provisionFirstDay").val('');
					$("#bussFinProductFloatFeeDto_eAccrDate").val('');
					//首次付费结束日
					$("#bussFinProductFloatFeeDto_firstPayDate").val('');
					$("#bussFinProductPayPlanDto_firstPayDate").val('');
					// 清空起息日 到息日
					$('#' + pageId + '_vdate').val('');
					$('#' + pageId + '_mdate').val('');
					// 清空清盘日
					$('#' + pageId +'_liquidationDate').val('');
					// 清空首次开放日
					$("#bussFinProductOpenPlanDto_firstOpenDate").val('');
					$("#bussFinProductOpenPlanRedDto_firstOpenDate").val('');
				}
			}
        },
        /**
         * 产品明细查看
         */
        showProdDetail : function(finprodId){
        	Joyin.dialog(JoyinUtil.getLabel("FinProductBuild002.reference"),
                    JoyinUtil.getRealPath("invest/product/productelement/build/FinProductBuild002/init"),
                    "98%", "95%",{pageModel : PAGE_MODEL_REFERENCE, finprodId : finprodId}
            );
        },
        //判断募集开始日是否在（从）和（到）之间
        firstSaleVdateChange : function(){
        	var str = $('#bussRepFinProductInfoDto_repRaisePstrdate').val();
        	var end = $('#bussRepFinProductInfoDto_repRaisePenddate').val();
        	var vdate = $('#bussFinProductSaleParamDto_firstSaleVdate').val();
        	if (str && end && vdate){
    			var day1 = (Date.ofDate(vdate) - Date.ofDate(str))/(24*60*60*1000);
    			var day2 = (Date.ofDate(end) - Date.ofDate(vdate))/(24*60*60*1000);
    			if(day1 < 0 || day2 < 0){
    				Joyin.error(JoyinUtil.getMessage("募集开始日必须在募集起始日（从）和募集起始日（到）之间", JoyinUtil.getLabel("BussRepFinProductInfo.repRaisePstrdate")));
    				$('#bussFinProductSaleParamDto_firstSaleVdate').val('');	
    				return;
    			}
    	}
        	
        }
    }
}();