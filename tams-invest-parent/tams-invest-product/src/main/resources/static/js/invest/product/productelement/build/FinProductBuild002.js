(function(){
	//add by tuqirong 2019-03-25 start 定义开放计划中的确认天数
	var redSpeed;
	var purSpeed;
	//add by tuqirong 2019-03-25 end
	
	/**
	 * 初始化方法
	 */
	var finProductBuildInit = function(){
		//初始化-是否现金管理类产品和是否设置最短持有期限 ，开放式是必输，封闭式置灰， FinProductBuild002_operationType
		var operationType = FinProductCommon.getElementById('operationType').val();
		if(operationType == ISSUE_MODE_01){
            FinProductCommon.getElementById('isCashManage').prop('disabled', true).trigger("change");  
		}         
		
		//初始化-收益类型默认选择收益型且不可改
		FinProductCommon.getElementById('profitType').prop('disabled',true);
        //通过系统参数来判断产品与申报信息是否合并
        if(productIsMerger === YES){
        	$('#FinProductBuild002_repFinprodInsId').prop('disabled', true);        
        }
        else{
        	//申报页面不能编辑
//        	disablePageElement(pageId+'_bussRepFinProductInfoDto', 1);
        	$('#FinProductBuild002_repFinprodInsId').prop('disabled', false);        
        }
		//add by yejiejie 2019-2-20 start
		var dealMode = FinProductCommon.getElementById('dealMode').val();	
		if(dealMode=='10' || dealMode == '12'){
			//显示:投资周期、是否续投字段
			$("#investmentCycleDiv").show();
//			$("#continueFlagDiv").show();			
		}else{
			//隐藏:投资周期、是否续投字段
			$("#investmentCycleDiv").hide();
//			$("#continueFlagDiv").hide();
			FinProductCommon.getElementById("investmentCycle").val('');
			FinProductCommon.getElementById("continueFlag").val('');
		}
		if(dealMode == '12'|| dealMode == '11'){
			FinProductCommon.getElementById('yield').prop('required', false);
		} else {
			FinProductCommon.getElementById('yield').prop('required', true);
		}
        star();
        // layui.form.render('yield');
		//add by yejiejie 2019-2-20 end
		
		// 新建可编辑、到经办可编辑，修改不可 编辑、到代办也不可编辑
		if ($('#wfl_operType').val()){
			if ($('#wfl_operType').val() != 'NEW'){
				$('#FinProductBuild002_finprodMarketId').prop('disabled', true);
				$('#FinProductBuild002_btn_quickInput').prop('disabled', true).addClass('layui-btn-disabled');
				//add by yejiejie 2019-2-20 start
				var dealMode = FinProductCommon.getElementById('dealMode').val();	
				if(dealMode=='10' || dealMode == '12'){
					//显示:投资周期、是否续投字段
					$("#investmentCycleDiv").show();
//					$("#continueFlagDiv").show();			
				}else{
					//隐藏:投资周期、是否续投字段
					$("#investmentCycleDiv").hide();
//					$("#continueFlagDiv").hide();
					FinProductCommon.getElementById("investmentCycle").val('');
					FinProductCommon.getElementById("continueFlag").val('');
				}
				//add by yejiejie 2019-2-20 end
				//add by yejiejie 2019-3-12 start
				$('#FinProductBuild002_prodSeries').prop('disabled', true);
				$('#FinProductBuild002_prodFeature').prop('disabled', true);
				//add by yejiejie 2019-3-12 end
			}
		}else{
			if (pageModel != PAGE_MODEL_NEW || confirmFlag === YES){
				$('#FinProductBuild002_finprodMarketId').prop('disabled', true);
				$('#FinProductBuild002_btn_quickInput').prop('disabled', true).addClass('layui-btn-disabled');
				//add by yejiejie 2019-2-20 start
				var dealMode = FinProductCommon.getElementById('dealMode').val();	
				if(dealMode=='10' || dealMode == '12'){
					//显示:投资周期、是否续投字段
					$("#investmentCycleDiv").show();
//					$("#continueFlagDiv").show();			
				}else{
					//隐藏:投资周期、是否续投字段
					$("#investmentCycleDiv").hide();
//					$("#continueFlagDiv").hide();
					FinProductCommon.getElementById("investmentCycle").val('');
					FinProductCommon.getElementById("continueFlag").val('');
				}
				//add by yejiejie 2019-2-20 end
				//add by yejiejie 2019-3-12 start
//				$('#FinProductBuild002_prodSeries').prop('disabled', true);
//				$('#FinProductBuild002_prodFeature').prop('disabled', true);
				//add by yejiejie 2019-3-12 end
			}
		}
		
		// 待办及修改模式的初期化
        if (FinProductCommon.getElementById('finprodMarketId').val()) {
            // 处理模式相关项目封死
            if (FinProductCommon.getElementById('dealMode').val()) {
                // 收益类型
            	FinProductCommon.getElementById('profitType').prop('disabled', true);
            	
                if (FinProductCommon.getElementById('dealMode').val() === DISPOSE_PATTERN_08
                		|| FinProductCommon.getElementById('dealMode').val() === DISPOSE_PATTERN_09) {
                	// 开放类型
                	FinProductCommon.getElementById('openMode').prop('disabled', true).trigger('change');
                	// 利率类型
                	FinProductCommon.getElementById('calcType').val('').prop('disabled', true).trigger("change");
                	// 是否分层
                	//FinProductCommon.getElementById('isLay').prop('disabled',true).prop('checked', false);
                	// 预期收益率
                	//FinProductCommon.getElementById('yield').val('').prop('disabled', true);
                }else{
//                	FinProductCommon.getElementById('operationType').prop('disabled', true).trigger("change");
                }
            }
            // 开放类型是封闭式的时候，是否周期型封上
            if (FinProductCommon.getElementById('profitType').val() === ISSUE_MODE_01) {
            	FinProductCommon.getElementById('isCycle').prop('disabled', true);
            	$("#investmentCycleDiv").hide();
            }
            // 是否周期型是否的时候，封闭期限封上
            if (FinProductCommon.getElementById('isCycle').next().find("em").text() === '否') {
            	FinProductCommon.getElementById('closeDays').prop('disabled', true);
            }
            // 是否分层是否的时候，分层类型封上
            if (FinProductCommon.getElementById('isLay').next().find("em").text() === '否') {
            	FinProductCommon.getElementById('layType').prop('disabled', true);
            }else{
            	// 预期收益率
            	//FinProductCommon.getElementById('yield').val('').prop('disabled', true);
            }
        }
		//update by leipeng 20190627 【预期收益型产品】新建，到期日为节假日的产品保存提交至代办，代办中打开还提示‘产品到期日为节假日，请重新选择。
        //vdateMdateChangeAction();
	}
	
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
    }
	//行内标识码
	var repFinprodInsIdChange = function(){
		var repFinprodId = $('#FinProductBuild002_repFinprodInsId').val();
		// 中债报送数据初始化
    	if(repFinprodId){
    		BussRepFinProductInfo.repInitByRepFinprodIdAction(repFinprodId);
    	}
    	
	}
	/**
	 * 产品模板下拉框change事件处理
	 */
	var templetIdChangeAction = function(){
		var tmplId = $('#FinProductBuild002_tmplId').val();
		if (tmplId){
			FinProductCommon.initPageInfoAction(pageId, tmplId, finprodTypeTmpl);
		}
	};
	
	/**
	 * 产品代码快录
	 */
	var prodIdQuickInputAction = function(){
		var prodId = $('#FinProductBuild002_finprodMarketId').val();
		quickInput = true; 
		if (prodId){
			FinProductCommon.initPageInfoAction(pageId, prodId, finprodTypeProd);
		}else{
			Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038', JoyinUtil.getLabel('FinProductBuild002.finprodMarketId')))
		}
	}
	
	/**
	 * 起息日、到期日联动处理产品期限
	 */
	var vdateMdateChangeAction = function(){
		var vdate = $('#FinProductBuild002_vdate').val();
		var mdate = $('#FinProductBuild002_mdate').val();
		//add by tuqirong 2019-03-22 start 
		var isSus = $("#FinProductBuild002_isSus").next().find("em").text();
		//add by tuqirong 2019-03-22 end
		var dealMode = $('#FinProductBuild002_dealMode').val();
		if (dealMode === DISPOSE_PATTERN_05){
			$('#FinProductBuild002_closeDays').prop('disabled', true);
		}
		if (vdate && mdate){
			var days = (Date.ofDate(mdate) - Date.ofDate(vdate))/(24*60*60*1000);
			if(days<=0){
				Joyin.error(JoyinUtil.getMessage("起息日必须小于到期日", JoyinUtil.getLabel("FinNetValueProductBuild002.mdate")));
				$('#FinProductBuild002_mdate').val('');	
				return;
			}else{
				//modify by tuqirong 2019-03-22 start
				if (isSus == '是') {
					$('#FinProductBuild002_termDays').val('');
				}else {
					$('#FinProductBuild002_termDays').val(days);
				}
//			   $('#FinProductBuild002_termDays').val(days);
				//modify by tuqirong 2019-03-22 end
			}
			if (dealMode === DISPOSE_PATTERN_05){
				$('#FinProductBuild002_closeDays').val(days);
			}
		}else{
			$('#FinProductBuild002_termDays').val('');
			if (dealMode === DISPOSE_PATTERN_05){
				$('#FinProductBuild002_closeDays').val('');
			}
			return;
		}
		//add by yejiejie 2019-3-11
		//判断起息日是否为节假日
		if($('#FinProductBuild002_vdate').val()){
			if($('#FinProductBuild002_calendarId').val()){
				//根据交易日历判断是否为节假日
				isHoliday($('#FinProductBuild002_vdate').val(),$('#FinProductBuild002_calendarId').val(),1);
				
			}else{
				Joyin.error(JoyinUtil.getMessage("请选择交易日历", JoyinUtil.getLabel("FinProductBuild002.calendarId")));
				$('#FinProductBuild002_vdate').val('');
			}
		}
		//判断到期日是否为节假日
		if($('#FinProductBuild002_mdate').val()){
			if($('#FinProductBuild002_calendarId').val()){
				//根据交易日历判断是否为节假日
				isHoliday($('#FinProductBuild002_mdate').val(),$('#FinProductBuild002_calendarId').val(),2);
//				if(result){
//					//是节假日--进行校验--提示
//					Joyin.error(JoyinUtil.getMessage("到期日是节假日,请重新选择！！！"));
//					$('#FinProductBuild002_mdate').val('');
//				}
			}else{
				Joyin.error(JoyinUtil.getMessage("请选择交易日历", JoyinUtil.getLabel("FinProductBuild002.calendarId")));
				$('#FinProductBuild002_mdate').val('');
			}
		}
		//add by yejiejie 2019-3-11 end 
	};
	var isHoliday = function(date,calendarId,flage){
		var par = {};
		if(flage == 1){
			par = {'vdate':date,'calendarId':calendarId};
		}else if(flage ==2 ){
			par = {'mdate':date,'calendarId':calendarId};
		}
		Joyin.ajaxJson(basePath + 'invest/product/productelement/build/FinProductBuild002/isHoliday', par, function(result) {
			if(result && flage == 1){
				//是节假日--进行校验--提示
				Joyin.confirm(JoyinUtil.getMessage("FIN_PROD_E_0116",JoyinUtil.getLabel("FinProductBuild002.vdate")),function(){
				    $('#FinProductBuild002_vdate').val('');//起息日
				    $('#FinProductBuild002_termDays').val('');//期限
				});
			}
			if(result && flage == 2){
				//是节假日--进行校验--提示
				Joyin.confirm(JoyinUtil.getMessage("FIN_PROD_E_0116",JoyinUtil.getLabel("FinProductBuild002.mdate")),function(){
				    $('#FinProductBuild002_mdate').val('');//到期日
                    $('#FinProductBuild002_termDays').val('');//期限
                    $('#FinProductBuild002_liquidationDate').val('');//清盘日
				});
			}
		});
	};
	//add by yejiejie 2019-3-11 end
	var dateChange = function(){
		var mdate = $('#FinProductBuild002_mdate').val();
		if($("#FinProductBuild002_isSus").next().find("em").text() === '是'){
		    $('#FinProductBuild002_liquidationDate').val(mdate);
		    return;
		}
		//add by yejiejie 2019-2-22 增加产品清盘日的联动
		if($('#FinProductBuild002_mdate').val()){
			if($('#FinProductBuild002_calendarId').val()){
				getNextWorkDay(mdate,$('#FinProductBuild002_calendarId').val());
			}else{
				Joyin.error(JoyinUtil.getMessage("请选择交易日历", JoyinUtil.getLabel("FinProductBuild002.calendarId")));
				$('#FinProductBuild002_mdate').val('');
			}			
		}
		//add by yejiejie 2019-2-22 增加产品清盘日的联动
	}
	var getNextWorkDay = function(mdate,calendarId){
	    if(mdate !=null && mdate !=''){
    		var par = {'mdate':mdate,'calendarId':calendarId};
         	var bankCode = $("#bankCode").val().trim();
    		if (bankCode!='004') {
	    		Joyin.ajaxJson(basePath + 'invest/product/productelement/netvalueproduct/FinNetValueProductBuild002/getNextWorkDay', par, function(result) {
	    			if(result){
	    				$('#FinProductBuild002_liquidationDate').val(Date.ofDate(result).format("yyyy-MM-dd"));
	    			}
	    		});
	        }else{
    			$('#FinProductBuild002_liquidationDate').val(Date.ofDate(mdate).format("yyyy-MM-dd"));
    		}
	    }
	};
	//add by yejiejie 2019-1-1-24
	//发行方式的change事件
	var operationTypeChangeAction  = function(){
		
		var operationType = FinProductCommon.getElementById('operationType').val();
		//var dealMode = FinProductCommon.getElementById('dealMode').val();
		if(operationType == ISSUE_MODE_01){
			//add by gcl 2019-3-17 start
			$("#bussFinProductPayPlanDto_payCycle").val('01').trigger("change");
			//add by gcl 2019-3-17 end
			//add by yejiejie 2019-3-5 start
			//巨额赎回比例隐藏
			$("#hugeRedRatioDiv").hide();
			//add by yejiejie 2019-3-5 end
			$('#FinProductBuild002_isSus').prop("checked",false).trigger('change');
			$("#FinProductBuild002_isSus").next().find("em").text('否');
			$("#FinProductBuild002_isSus").next().removeClass("layui-form-onswitch");	
			$("#FinProductBuild002_isSus").next().addClass("layui-checkbox-disbaled layui-disabled");
            $("#FinProductBuild002_isSus").prop('disabled',true);
            //封闭式置灰 中债报送tab的 是否设置最短持有期限 和 最短持有期限（天） start yjm 20190807
            $('#bussRepFinProductInfoDto_isShortTerm').val('').trigger('change').attr('disabled',true);
            //封闭式置灰 中债报送tab的 是否设置最短持有期限 和 最短持有期限（天） end yjm 20190807
            //封闭式置灰 基本信息的 是否现金管理类产品 start yjm 20190828
            $('#FinProductBuild002_isCashManage').val('').trigger('change').attr('disabled',true);
            //封闭式置灰 基本信息的 是否现金管理类产品 end yjm 20190828
			FinProductCommon.isSus();
			
		}else{
			//add by gcl 2019-3-17 start
			$("#bussFinProductPayPlanDto_payCycle").val('02').trigger("change");
			//add by gcl 2019-3-17 end
			if(operationType == ISSUE_MODE_02){
				//yejiejie 2019-3-5
	    		//$('#FinProductBuild002_isSus').prop("checked",true).trigger('change');
	    		FinProductCommon.getElementById('isSus').is(':checked')
//				var chk = document.getElementById('FinProductBuild002_isSus');
//				chk.checked = true;
	    		//delete by wm 去除将发行方式修改为开放式的时候默认改为永续的操作 start
//				$("#FinProductBuild002_isSus").next().find("em").text('是');
//				$("#FinProductBuild002_isSus").next().addClass("layui-form-onswitch");
				//delete by wm 去除将发行方式修改为开放式的时候默认改为永续的操作 end
	    		FinProductCommon.isSus();
			}
			$("#FinProductBuild002_isSus").next().addClass("layui-checkbox-disbaled layui-disabled");
            $("#FinProductBuild002_isSus").prop('disabled',false);
			//add by yejiejie 2019-3-5 start
			//巨额赎回比例显示
			$("#hugeRedRatioDiv").show();				
			//add by yejiejie 2019-3-5 end
            //开放式 中债报送tab的 是否设置最短持有期限 和 最短持有期限（天） 必输 start yjm 20190807
            $('#bussRepFinProductInfoDto_isShortTerm').attr('disabled', false);
            //开放式 中债报送tab的 是否设置最短持有期限 和 最短持有期限（天） 必输 end yjm 20190807

            //开放式必输 基本信息的 是否现金管理类产品 start yjm 20190828
            $('#FinProductBuild002_isCashManage').attr('disabled', false);
            //开放式必输 基本信息的 是否现金管理类产品 end yjm 20190828
		}

	}
	//add by yejiejie 2019-1-1-24
	var dealModeChangeAction = function(){
		var dealMode = FinProductCommon.getElementById('dealMode').val();	
		if(dealMode=='10' || dealMode == '12'){
			//显示:投资周期、是否续投字段
			$("#investmentCycleDiv").show();
//			$("#continueFlagDiv").show();			
		}else{
			//隐藏:投资周期、是否续投字段
			$("#investmentCycleDiv").hide();
//			$("#continueFlagDiv").hide();
			FinProductCommon.getElementById("investmentCycle").val('');
			FinProductCommon.getElementById("continueFlag").val('');		
		}
		if(dealMode == '12'|| dealMode == '11'){
			FinProductCommon.getElementById('yield').prop('required', false);
		} else {
			FinProductCommon.getElementById('yield').prop('required', true);
		}
        star();
        // layui.form.render('yield');
	}
	//add by yejiejie 2019-1-1-24
	/**
	 * 保存或提交按钮执行事件
	 */
	var onSaveOrSubmitAction = function(operateType){
		var object = $.serializeToObject('#productBuildForm', $('#wfl_operType').val() == 'NEW');		
		object.operateType = operateType;
        object.pageModel = pageModel;
        object.confirmFlag = confirmFlag;
        //中债申报页面客户类型单选  yjm start 20190808 
        /*var customerTypeTemp = $('#bussRepFinProductInfoDto_customerType').val();
        var customerType= null;
        if(customerTypeTemp != null)
            customerType=customerTypeTemp.join(",");
        object.bussRepFinProductInfoDto.customerType = customerType;*/
        //中债申报页面客户类型多选  yjm end 20190808 
        //add by gcl 2019/3/18 start
        if(object.issueType == "null" ){object.issueType = null;}
       //add by gcl 2019/3/18 end
        //add by tuqirong 2019-03-25 start
        //附件信息
        object.fileCommonList = FileCommon.getUploadFileInfo();
        // add by tuqirong 2019-03-25 end
        // 基础信息
        // 是否分层
    	if ($('#FinProductBuild002_isLay').next().find("em").text() === '是') {
            object.isLay = 'Y';
        } else {
            object.isLay = 'N';
        }
        // 是否周期型
    	if ($('#FinProductBuild002_isCycle').next().find("em").text() === '是') {
            object.isCycle = 'Y';
        } else {
            object.isCycle = 'N';
        }
        // 分层类型
        if (object.layType) {
            object.layType = object.layType.join(',');
        }
        // 分行
        if (object.entruster) {
            object.entruster = object.entruster.join(',');
        }
        
        if(!draftoption&&!ProductPayPlan.checkPayPlan()) return false; //付息计划验证 yzg add 20191025
        if(!BussFinProductOpenPlan.chkClosePlan()) return false; //丁俊峰修改
//        if(openMode!='02'&&!BussFinProductOpenPlan.chkPayPlan()) return false;
        // 付息计划
        if (object.bussFinProductPayPlanDto){
        	object.bussFinProductPayPlanDto.vdate = object.vdate;
        	object.bussFinProductPayPlanDto.mdate = object.mdate;
        	object.bussFinProductPayPlanDto.calendarId = object.calendarId;
        }
        
        //发行方式为封闭式 开放计划为空 yzg 20191220 start
        if(object.operationType ==ISSUE_MODE_01){
        	object.bussFinProductOpenPlanDto = null;
        }
        //发行方式为封闭式 开放计划为空 yzg 20191220 end
        
        // 开放计划
        if (object.bussFinProductOpenPlanDto){
        	if (object.bussFinProductOpenPlanDto.dayOfWeek){
        		object.bussFinProductOpenPlanDto.dayOfWeek = object.bussFinProductOpenPlanDto.dayOfWeek.join(',');
        	}
        	if (object.bussFinProductOpenPlanDto.dayOfMonth){
        		object.bussFinProductOpenPlanDto.dayOfMonth = object.bussFinProductOpenPlanDto.dayOfMonth.join(',');
        	}
        	if (object.bussFinProductOpenPlanDto.workdayOfMonth){
        		object.bussFinProductOpenPlanDto.workdayOfMonth = object.bussFinProductOpenPlanDto.workdayOfMonth.join(',');
        	}
        	if(object.bussFinProductOpenPlanDto.weekOfMonth){
        	    object.bussFinProductOpenPlanDto.weekOfMonth = object.bussFinProductOpenPlanDto.weekOfMonth.join(',');
        	}
        	if(object.bussFinProductOpenPlanDto.workdayOfWeek){
        	    object.bussFinProductOpenPlanDto.workdayOfWeek = object.bussFinProductOpenPlanDto.workdayOfWeek.join(',');
        	}
        	if (!object.bussFinProductOpenPlanDto.hdayOpenFlag) {
        		object.bussFinProductSaleParamDto.hdayOpenFlag = 'N';
            }
        	if(object.operationType) {
        		object.bussFinProductOpenPlanDto.operationType = object.operationType;//发行方式不为空时，将发行方式传递到开放计划DTO里
        	}
        }
        var openMode ="";
        if (object.bussFinProductOpenPlanDto){
        	openMode =object.bussFinProductOpenPlanDto.openMode;//add by zlp 20190314 高频开发不校验开放计划
        }   
        if(!draftoption&&openMode!='02'&&!BussFinProductOpenPlan.chkOpenPlan()) return false;//add by zhangweiqi 开放计划元素调整后重新生成计划  
        // 赎回开放计划 add by yejiejie
        if (object.bussFinProductOpenPlanRedDto){
        	var openRedMode = object.bussFinProductOpenPlanRedDto.openMode;
        	if(openMode!='02'&&object.dealMode != DISPOSE_PATTERN_10 &&!BussFinProductOpenPlan.chkRedOpenPlan()){
        		return false;
        	}
        	if (object.bussFinProductOpenPlanRedDto.dayOfWeek){
        		object.bussFinProductOpenPlanRedDto.dayOfWeek = object.bussFinProductOpenPlanRedDto.dayOfWeek.join(',');
        	}
        	if (object.bussFinProductOpenPlanRedDto.dayOfMonth){
        		object.bussFinProductOpenPlanRedDto.dayOfMonth = object.bussFinProductOpenPlanRedDto.dayOfMonth.join(',');
        	}
        	if (object.bussFinProductOpenPlanRedDto.workdayOfMonth){
        		object.bussFinProductOpenPlanRedDto.workdayOfMonth = object.bussFinProductOpenPlanRedDto.workdayOfMonth.join(',');
        	}
        	if (!object.bussFinProductOpenPlanRedDto.hdayOpenFlag) {
        		object.bussFinProductOpenPlanRedDto.hdayOpenFlag = 'N';
            }
        }
        
        // 销售参数
        if (object.bussFinProductSaleParamDto){
        	// 销售渠道
        	if (object.bussFinProductSaleParamDto.saleChannel){
        		object.bussFinProductSaleParamDto.saleChannel = object.bussFinProductSaleParamDto.saleChannel.join(',');
        	}
        	// 销售地区
        	if (object.bussFinProductSaleParamDto.saleArea){
        		object.bussFinProductSaleParamDto.saleArea = object.bussFinProductSaleParamDto.saleArea.join(',');
        	}
        	// 是否可质押
        	if (!object.bussFinProductSaleParamDto.canPlge) {
        		object.bussFinProductSaleParamDto.canPlge = 'N';
            }
        	//add by yejiejie 2019-2-13 start
        	// 销售部门
        	if (object.saleDepartment){
        		object.saleDepartment = object.saleDepartment.join(',');
        	}

        	// 目标客户类型
        	if (object.bussFinProductSaleParamDto.investorType){
                object.bussFinProductSaleParamDto.investorType = object.bussFinProductSaleParamDto.investorType.join(',');
                if (object.bussRepFinProductInfoDto){
                    object.bussRepFinProductInfoDto.repInvestorType = object.bussFinProductSaleParamDto.investorType;
                }
            }
        	//add by yejiejie 2019-2-13 end
        	//add by zlp 2019-3-1 end
        	// 同业机构
        	if (object.sameOrg){
        	    object.sameOrg = object.sameOrg.join(',');
        		object.bussFinProductSaleParamDto.sameOrg = object.sameOrg;
        	}
        	//add by zlp 2019-3-1 end
        }
        // 估值信息
        if (object.bussFinProductValuationDto){
        	// 托管活期利息计提
        	if (!object.bussFinProductValuationDto.isCountingCurInt) {
        		object.bussFinProductValuationDto.isCountingCurInt = 'Y';
            }
        }
        
        // 中债报送信息
        if (object.bussRepFinProductInfoDto){
        	// 投资者风险偏好
        	if (object.bussRepFinProductInfoDto.repRiskType){
        		object.bussRepFinProductInfoDto.repRiskType = object.bussRepFinProductInfoDto.repRiskType.join(',');
        	}
        	// 产品增信机构类型
        	if (object.bussRepFinProductInfoDto.repCreditType){
        		object.bussRepFinProductInfoDto.repCreditType = object.bussRepFinProductInfoDto.repCreditType.join(',');
        	}
            // 产品投资国家或地区
        	if (object.bussRepFinProductInfoDto.repInvestCountry){
        		object.bussRepFinProductInfoDto.repInvestCountry = object.bussRepFinProductInfoDto.repInvestCountry.join(',');
        	}
            // 境外托管机构国别
        	if (object.bussRepFinProductInfoDto.repOutDepCtry){
        		object.bussRepFinProductInfoDto.repOutDepCtry = object.bussRepFinProductInfoDto.repOutDepCtry.join(',');
        	}

            //BSTOT-455 募集要素DIV里的募集币种、兑付本金币种、对付收益币种应该不能多选 guyunfei 20200111 start
        	// 兑付本金币种
        	/*if (object.bussRepFinProductInfoDto.repCashPrinCcy){
        		object.bussRepFinProductInfoDto.repCashPrinCcy = object.bussRepFinProductInfoDto.repCashPrinCcy.join(',');
        	}*/
        	// 兑付收益币种
        	/*if (object.bussRepFinProductInfoDto.repCashProfCcy){
        		object.bussRepFinProductInfoDto.repCashProfCcy = object.bussRepFinProductInfoDto.repCashProfCcy.join(',');
        	}*/
            //BSTOT-455 募集要素DIV里的募集币种、兑付本金币种、对付收益币种应该不能多选 guyunfei 20200111 end

        	//REP_IS_YIELD是否有预期收益率
            if(object.bussRepFinProductInfoDto.repIsYield && object.bussRepFinProductInfoDto.repIsYield === NO){
                object.bussRepFinProductInfoDto.repIsYield = SVR_HAVE_OR_NOT_02;
            }
            //REP_TERM_RIGHT_FLAG发行机构提前终止权标识、REP_CUS_REDEEM_FLAG客户赎回权标识、REP_CREDIT_FLAG产品增信标识
            if(object.bussRepFinProductInfoDto.repTermRightFlag && object.bussRepFinProductInfoDto.repTermRightFlag === NO){
                object.bussRepFinProductInfoDto.repTermRightFlag = SVR_HAVE_OR_NOT_02;
            }
            //REP_CUS_REDEEM_FLAG客户赎回权标识、REP_CREDIT_FLAG产品增信标识
            if(object.bussRepFinProductInfoDto.repCusRedeemFlag && object.bussRepFinProductInfoDto.repCusRedeemFlag === NO){
                object.bussRepFinProductInfoDto.repCusRedeemFlag = SVR_HAVE_OR_NOT_02;
            }
            //REP_CREDIT_FLAG产品增信标识
            if(object.bussRepFinProductInfoDto.repCreditFlag && object.bussRepFinProductInfoDto.repCreditFlag === NO){
                object.bussRepFinProductInfoDto.repCreditFlag = SVR_HAVE_OR_NOT_02;
            }
            //计划募集金额
            if(object.bussFinProductSaleParamDto) {
                object.bussRepFinProductInfoDto.repRaiseAmtPlan = object.bussFinProductSaleParamDto.raiseAmtPlan;
            }
            //募集起始日（从）
        	if(object.repRaisePstrdate) {
    	    object.bussRepFinProductInfoDto.repRaisePstrdate = object.repRaisePstrdate;
        	}
        	//募集起始日（从）（到）
        	if(object.repRaisePenddate) {
        		object.bussRepFinProductInfoDto.repRaisePenddate = object.repRaisePenddate;
        	}
        }
        var fundAccountTables = BussFinProductFundAccountInfo.table;
        var fundAccountTable = fundAccountTables.getAllRows();
        if(fundAccountTable&&fundAccountTable.length>0){
     	   for(var i=0;i<fundAccountTable.length;i++){
     		   if(fundAccountTable[i].feeType&& Array.isArray(fundAccountTable[i].feeType)){
     			   var feeType="";
     			  for(var j=0;j<fundAccountTable[i].feeType.length;j++){
     				   if(fundAccountTable[i].feeType[j])
     				   feeType+=fundAccountTable[i].feeType[j]+",";
     			   }
     			   fundAccountTable[i].feeType=feeType;
     		   }
     	   }
        }
        object.bussFinProductFundAccountInfoDto = fundAccountTable;
        var LayeredTable = BussFinProductLayered.getAllRows();
        //产品分层信息中：销售渠道、销售部门、目标客户类型支持多选
        if(LayeredTable&&LayeredTable.length>0){
       	   for(var i=0;i<LayeredTable.length;i++){
       		   //销售渠道
       		   if(LayeredTable[i].saleChannel&& Array.isArray(LayeredTable[i].saleChannel)){
       			   var saleChannel="";
       			  for(var j=0;j<LayeredTable[i].saleChannel.length;j++){
       				   if(LayeredTable[i].saleChannel[j])
       				   feeType+=LayeredTable[i].saleChannel[j]+",";
       			   }
       			LayeredTable[i].saleChannel=saleChannel;
       		   }
       		   
       		   //销售部门
       		   if(LayeredTable[i].saleDepartment&& Array.isArray(LayeredTable[i].saleDepartment)){
       			   var saleDepartment="";
       			  for(var j=0;j<LayeredTable[i].saleDepartment.length;j++){
       				   if(LayeredTable[i].saleDepartment[j])
       					saleDepartment+=LayeredTable[i].saleDepartment[j]+",";
       			   }
       			LayeredTable[i].saleDepartment=saleDepartment;
       		   }
       		   
       		   //销售渠道
       		   if(LayeredTable[i].tarCustomerType&& Array.isArray(LayeredTable[i].tarCustomerType)){
       			   var tarCustomerType="";
       			  for(var j=0;j<LayeredTable[i].tarCustomerType.length;j++){
       				   if(LayeredTable[i].tarCustomerType[j])
       					tarCustomerType+=LayeredTable[i].tarCustomerType[j]+",";
       			   }
       			LayeredTable[i].tarCustomerType=tarCustomerType;
       		   }
       	   }
          }
        object.bussFinProductLayeredInfoDto = LayeredTable;
        
        if(object.dealMode=="10") object.isCycle = 'Y';
        else  object.isCycle = 'N';
        if(object.dealMode=="11") object.isLay = 'Y';
        else  object.isLay = 'N';
        if(object.dealMode=="12"){
        	object.isLay = 'Y';
        	object.isCycle = 'Y';
        }/*else{ 
        	 object.isLay = 'N';
        	 object.isCycle = 'N';
        } remove by mhw 20190611*/
        Joyin.ajaxJson(basePath + 'invest/product/productelement/build/FinProductBuild002/check',object, function(result) {
    		Joyin.confirm(result.data, function() {
                	if(operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'invest/product/productelement/build/FinProductBuild002/save', object, "FinProductBuild002_btn_save");
                    }
                    else {
                        WorkFlow.doSubmitList(basePath + 'invest/product/productelement/build/FinProductBuild002/save', object, "FinProductBuild002_btn_submit");
                    }
                })
            })
	}
	
	var targetCustomer1ChangeAction=function(){
		var targetCustomer1=$('#FinProductBuild002_targetCustomer1').val();
		if(targetCustomer1=="02"){
			$("#bussFinProductSaleParamDto_repInvestorType").val("02").prop("disabled",true).trigger("change");
		}else if(targetCustomer1=="03"){
			$("#bussFinProductSaleParamDto_repInvestorType").val("04").prop("disabled",true).trigger("change");
		}
	};
	var targetCustomer2ChangeAction=function(){
		var targetCustomer2=$('#FinProductBuild002_targetCustomer2').val();
		if(targetCustomer2=="03"){
			$("#bussFinProductSaleParamDto_repInvestorType").val("01").prop("disabled",true).trigger("change");
		}else if(targetCustomer2=="02"){
			$("#bussFinProductSaleParamDto_repInvestorType").val("05").prop("disabled",true).trigger("change");
		}else if(targetCustomer2=="01"){
			$("#bussFinProductSaleParamDto_repInvestorType").val("03").prop("disabled",true).trigger("change");
		}
	};
	
	$(function() {
		finProductBuildInit();
		
		FinProductCommon.bindEvent();
		/**add by taoran 20200317*/
		//产品代码修改事件 不允许中文
		$('#FinProductBuild002_finprodMarketId').bind("change", function(){
			$('#FinProductBuild002_finprodMarketId').val(
					$('#FinProductBuild002_finprodMarketId').val().replace(/[^\a-\z\A-\Z0-9]/g,''));
		});
		/**add by taoran 20200317*/
		//行内标识码联动
		
		$('#FinProductBuild002_repFinprodInsId').change(function(){
			repFinprodInsIdChange();
		});
		// 发行方式下拉框change事件处理
		$('#FinProductBuild002_operationType').change(function(){
			//FinProductCommon.openModeSelect();
			operationTypeChangeAction();
		});
		// 产品处理模式下拉框change事件处理
		$('#FinProductBuild002_dealMode').change(function(){
			//FinProductCommon.openModeSelect();
			dealModeChangeAction();
		});
		
		
		// 起息日、到期日联动处理产品期限
		$('#FinProductBuild002_vdate').change(function(){
			vdateMdateChangeAction();
			//add by yejiejie 2019-3-1 start
			dateChange();
			//add by yejiejie 2019-3-1 end
		});
		$('#FinProductBuild002_mdate').change(function(){
			vdateMdateChangeAction();
			//add by yejiejie 2019-3-1 start
			dateChange();
			//add by yejiejie 2019-3-1 end
		});
		$('#FinProductBuild002_dealMode').change(function(){
			vdateMdateChangeAction();
		});
		
		// 快录按钮执行事件
		$('#FinProductBuild002_btn_quickInput').click(function(){
			prodIdQuickInputAction();
		});
		// 产品模板下拉框change事件处理
		$('#FinProductBuild002_tmplId').change(function(){
			templetIdChangeAction();
		});
		// 保存按钮执行事件
		$('#FinProductBuild002_btn_save').click(function(){
			var operationType2 =  $('#FinProductBuild002_operationType').val();
			var termDays2 =  $('#FinProductBuild002_termDays').val();
			if(operationType2 == "01" && termDays2 <90){
				Joyin.confirm(JoyinUtil.getMessage('封闭式产品产品期限小于90天?'),function(){
					onSaveOrSubmitAction('save');
                },null);
			}else{
				onSaveOrSubmitAction('save');
			}
			
		});
		// 提交按钮执行事件
		$('#FinProductBuild002_btn_submit').click(function(){
			var operationType2 =  $('#FinProductBuild002_operationType').val();
			var termDays2 =  $('#FinProductBuild002_termDays').val();
			if(!draftoption && operationType2 == "01" && termDays2 <90){
				Joyin.confirm(JoyinUtil.getMessage('封闭式产品产品期限小于90天?'),function(){
					onSaveOrSubmitAction('submit');
                },null);
			}else{
				onSaveOrSubmitAction('submit');
			}
			
		});
		//update zhangweiqi 增加目标客户类型一级分类和二级分类对于中债的目标客户类型的联动事件  begin
		$('#FinProductBuild002_targetCustomer1').change(function(){
			targetCustomer1ChangeAction();
		});
		$('#FinProductBuild002_targetCustomer2').change(function(){
			targetCustomer2ChangeAction();
		});
		//update zhangweiqi 增加目标客户类型一级分类和二级分类对于中债的目标客户类型的联动事件  end
	});
}());

//  add by wangyang1 [吉林个性化] 2019年9月26日 start 
function setFinprodAbbr() {
    var bankCode = $("#bankCode").val().trim();
    if (bankCode=='004') {
        var finprodMarketId = $("#FinProductBuild002_finprodMarketId").val().trim();
        if (finprodMarketId) {
            $("#FinProductBuild002_finprodAbbr").val(finprodMarketId);
        }
    }
}
//add by wangyang1 [吉林个性化] end
//add by taoran 分层信息目标客户类型修改联动
var tarCustomerTypeChange = function(){
	var LayeredTableInfo = BussFinProductLayered.getAllRows();
	for(var i in LayeredTableInfo){
		if(LayeredTableInfo[i].tarCustomerType && LayeredTableInfo[i].tarCustomerType.indexOf("04") != -1){
			$("#bussFinProductSaleParamDto_sameOrg").removeAttr("disabled");
			break;
		}
		else{
			$("#bussFinProductSaleParamDto_sameOrg").attr("disabled","disabled");
		}
	}
}