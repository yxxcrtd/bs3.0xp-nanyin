
(function(){
	var getElementById = function(id) {
		return $('#' + bussFinProductOpenPlanDto + '_' + id);
	};
	
	/**
	 * 初始化加载tableFlag 
	 */
	var initFlag = false;
	//控件flag
    var flag = false;
	// 快速录入标志
	var quickFlag = false;
    
	var getSearchParams = function(){
		var param = {};
		param.quickFlag = quickFlag;
		if (!initFlag){
			param.finprodId = openPlanFinprodId;
			param.workflowId = workflowId;
			param.entityName = bussFinProductOpenPlanDto;
		}else{
			param.pageId = pageId;
			param.dealMode = $('#' + dealMode).val();
			param.vdate = $('#' + vdate).val();
			param.mdate = $('#' + mdate).val();
			param.tradCalender = $('#' + tradCalender).val();
			param.raiseStrDate = $('#' + raiseStrDate).val();
			param.raiseEndDate = $('#' + raiseEndDate).val();
			param.purConfDays = $('#' + purConfDays).val();
			if ($('#' + isCycle).next().find("em").text() === '是') {
				param.isCycle = 'Y';
	        } else {
	        	param.isCycle = 'N';
	        }
			param.closeTerm = $('#' + closeTerm).val();
			
			param.openMode = getElementById('openMode').val();
			param.openDays = getElementById('openDays').val();
			param.openCycle = getElementById('openCycle').val();
			param.openCycleUnit = getElementById('openCycleUnit').val();
			if (getElementById('dayOfWeek').val()){
				param.dayOfWeek = getElementById('dayOfWeek').val().join(",");
			}
			param.openDate = getElementById('div').find(':radio:checked').val();
			if (getElementById('dayOfMonth').val()){
				param.dayOfMonth = getElementById('dayOfMonth').val().join(",");
			}
			if (getElementById('workdayOfMonth').val()){
				param.workdayOfMonth = getElementById('workdayOfMonth').val().join(",");
			}
			param.calcMethod = getElementById('calcMethod').val();
			param.firstOpenDate = getElementById('firstOpenDate').val();
			param.tradedayRule = getElementById('tradedayRule').val();
			param.calcRule = getElementById('calcRule').val();
			if ($('#hdayOpenFlag').prop('checked')) {
				param.hdayOpenFlag = 'Y';
	        } else {
	        	param.hdayOpenFlag = 'N';
	        }
		}
		return param;
	}
	
	var checkInputParam = function (){
		initFlag = true;
		Joyin.ajaxJson(JoyinUtil.getRealPath('component/prod/BussFinProductOpenPlan/checkInputParam'), getSearchParams(), function(result){
			if (result.data){
				Joyin.confirm(result.data, function(){
					table.table ? table.reload() : table.load();
	            });
			}else{
				table.table ? table.reload() : table.load();
			}
		})
	}
	
	if (pageId == 'FinProductBuild002'||pageId === 'FinNetValueProductBuild002'){
		/**
	     * 定义数据表格
	     */
		var table = getElementById('dataTable').joyintable({ 
		    	ajax: {
		            url: JoyinUtil.getRealPath('component/prod/BussFinProductOpenPlan/previewList'),
		            type: 'POST',
		            data: function() {
		                return getSearchParams();
		            }
		        },
		        searchOnInit: pageModel != PAGE_MODEL_NEW && (openModeEntityValue == OPENMODE_01 || openModeEntityValue == OPENMODE_03),
		        height: 300,
		        page: false,
		        checkbox: false,
		        loadonce: true,
		        rowno: true,
		        cols:[
					 {field:'openDateStr', title: 'FinProductOpenRule.table.openCycleStartDate',sortable:false, type:'date', width: 300}
					,{field:'openDateEnd', title: 'FinProductOpenRule.table.openCycleEndDate',sortable:false, type:'date', width: 300}
					,{field:'openVdate', title: 'FinProductOpenRule.table.openCycleVdate',sortable:false, type:'date', width: 300}
					,{field:'openMdate', title: 'FinProductOpenRule.table.openCycleMdate',sortable:false, type:'date', width: 300}
		        ]
		    });
	}
	
	var openModeChange = function (){
		var selectedValue = getElementById('openMode').val();
		// 定期开放或者未勾选
		if (!selectedValue || selectedValue == OPENMODE_01){
			getElementById('openDays').prop('disabled', false);
			getElementById('openCycle').prop('disabled', false);
			getElementById('dayOfWeek').prop('disabled', false);
			getElementById('div').find(':radio').prop('disabled', false);
			getElementById('dayOfMonth').prop('disabled', false);
			getElementById('workdayOfMonth').prop('disabled', false);
			getElementById('openCycleUnit').trigger('change').prop('disabled', false);
			getElementById('calcMethod').prop('disabled', false);
			//getElementById('firstOpenDate').prop('disabled', false);
			getElementById('tradedayRule').trigger('change').prop('disabled', false);
			getElementById('calcRule').prop('disabled', false);
			getElementById('hdayOpenFlag').prop('disabled', false);
			getElementById('tableDiv').show();
		}
		// 高频开放
		else if (selectedValue == OPENMODE_02){
			getElementById('openDays').prop('disabled', true).val('').trigger('change');
			getElementById('openCycle').prop('disabled', true).val('').trigger('change');
			getElementById('openCycleUnit').prop('disabled', true).val('').trigger('change');
			getElementById('dayOfWeek').prop('disabled', true).val('').trigger('change');
			getElementById('div').find(':radio').prop('disabled', true).prop('checked', false);
			getElementById('dayOfMonth').prop('disabled', true).val('').trigger('change');
			getElementById('workdayOfMonth').prop('disabled', true).val('').trigger('change');
			getElementById('tradedayRule').prop('disabled', true).val('').trigger('change');
			getElementById('calcRule').prop('disabled', true).val('').trigger('change');
			getElementById('calcMethod').prop('disabled', false);
			//getElementById('firstOpenDate').prop('disabled', false);
			if ($('#' + dealMode).val() === DISPOSE_PATTERN_07) {
			    getElementById('hdayOpenFlag').prop('checked', true);
			}
			getElementById('tableDiv').hide();
			if (table && table.table){
				table.clear();
			}
		}
		// 周期开放
		else if (selectedValue == OPENMODE_03){
			if(pageModel === PAGE_MODEL_NEW){
				getElementById('openDays').val('1');
			}
			getElementById('openDays').prop('disabled', false);
			getElementById('openCycle').prop('disabled', false);
			if(pageModel === PAGE_MODEL_NEW){
				getElementById('openCycleUnit').val(PERIOD_UNIT_01).trigger('change');
				getElementById('tradedayRule').val(TDAY_RULE_02).trigger('change');
			}
			getElementById('openCycleUnit').prop('disabled', false);
			getElementById('tradedayRule').prop('disabled', false);
			getElementById('calcRule').prop('disabled', false);
			getElementById('calcMethod').prop('disabled', false);
			//getElementById('firstOpenDate').prop('disabled', false);
			getElementById('hdayOpenFlag').prop('disabled', false);
			getElementById('dayOfWeek').prop('disabled', true).val('').trigger('change');
			getElementById('div').find(':radio').prop('disabled', true).prop('checked', false);
			getElementById('dayOfMonth').prop('disabled', true).val('').trigger('change');
			getElementById('workdayOfMonth').prop('disabled', true).val('').trigger('change');
			getElementById('tableDiv').show();
		}
		layui.form.render();
	}
	
	var openCycleUnitChange = function (){
		var selectedValue = getElementById('openCycleUnit').val();
		var openMode = getElementById('openMode').val();
		if (openMode && openMode === OPENMODE_01){
			if (selectedValue == PERIOD_UNIT_01){
				getElementById('dayOfWeek').val('').prop('disabled', true).trigger('change');
				getElementById('div').find(':radio').prop('disabled', true).prop('checked', false);
				getElementById('dayOfMonth').val('').prop('disabled', true).trigger('change');
				getElementById('workdayOfMonth').val('').prop('disabled', true).trigger('change');
			}
			else if (selectedValue == PERIOD_UNIT_02){
				getElementById('dayOfWeek').prop('disabled', false);
				getElementById('div').find(':radio').prop('disabled', true).prop('checked', false);
				getElementById('dayOfMonth').val('').prop('disabled', true).trigger('change');
				getElementById('workdayOfMonth').val('').prop('disabled', true).trigger('change');
			}
			else if (selectedValue == PERIOD_UNIT_03 || selectedValue == PERIOD_UNIT_04){
				getElementById('dayOfWeek').val('').prop('disabled', true).trigger('change');
				getElementById('div').find(':radio').prop('disabled', false).prop('checked', false);
				//重置check选项
				if(flag){
                    getElementById('dayOfMonth').val('').prop('disabled', false).trigger('change');
                    getElementById('workdayOfMonth').val('').prop('disabled', true).trigger('change');
                }
                else{
                //	待优化
					setTimeout(function(){
						if(!getElementById('workdayOfMonth').val()){
							getElementById('workdayOfMonth').prop('disabled', true).trigger('change');
                            getElementById('dayOfMonth').prop('disabled', false).trigger('change');
						}
						else{
							getElementById('workdayOfMonth').prop('disabled', false).trigger('change');
                            getElementById('dayOfMonth').prop('disabled', true).trigger('change');
						}
                        flag=true;
					},0);
				}
				if(pageModel === PAGE_MODEL_NEW || getElementById('div').find(':radio:checked').length === 0) {
					getElementById('div').find(':radio[value="0"]').prop('checked', true);
				}
			}
		}else{
			getElementById('dayOfWeek').val('').prop('disabled', true).trigger('change');
			getElementById('div').find(':radio').prop('disabled', true).prop('checked', false);
			getElementById('dayOfMonth').val('').prop('disabled', true).trigger('change');
			getElementById('workdayOfMonth').val('').prop('disabled', true).trigger('change');
		}
        layui.form.render();
	}
	
	var weekChange = function (){
		var selectedValue = getElementById('dayOfWeek').val();
		
		if (selectedValue){
			getElementById('div').find(':radio').prop('disabled', true).prop('checked', false);
			getElementById('dayOfMonth').val('').prop('disabled', true).trigger('change');
			getElementById('workdayOfMonth').val('').prop('disabled', true).trigger('change');
		}
		layui.form.render();
	}
	
	var calcMethodChange = function (){
		var selectedValue = getElementById('calcMethod').val();
		
		if (selectedValue){
			if (selectedValue == CALC_METHOD_01){
				getElementById('firstOpenDate').val('').prop('disabled', true);
			}
			else if (selectedValue == CALC_METHOD_03){
				getElementById('firstOpenDate').prop('disabled', false);
			}
		}
		else{
			if (!finprodId && !workflowId){
				getElementById('firstOpenDate').val('')
			}
			getElementById('firstOpenDate').prop('disabled', true);
		}
	}
	
	var parentOpenModeChange = function (){
        var selectedValue = $('#' + operationType).val();
        
        if (selectedValue){
            if (selectedValue == ISSUE_MODE_01){
                getElementById('div').show();
            }
            else {
                getElementById('div').hide();
            }
        }
        else{
            getElementById('div').hide();
        }
    }	
	var parentDealModeChange = function () {
		var selectedValue = $('#' + dealMode).val();
		var operationTypeValue = $('#' + operationType).val();
		if (selectedValue) {
		    first = false;
			// 非普通
			if (selectedValue == DISPOSE_PATTERN_01) {
				getElementById('div').hide();
			}
			else{
			    if (operationTypeValue == ISSUE_MODE_01) {
    				getElementById('div').show();
    				if (selectedValue == DISPOSE_PATTERN_05 || selectedValue == DISPOSE_PATTERN_06 || selectedValue == DISPOSE_PATTERN_07){
    					getElementById('openMode').val(OPENMODE_02).trigger('change').prop('disabled', true);
    					if (selectedValue == DISPOSE_PATTERN_06){
    						getElementById('hdayOpenFlag').next().removeClass('layui-form-onswitch').trigger('change');
    	                    getElementById('hdayOpenFlag').next().find("em").text('否');
    	                    getElementById('hdayOpenFlag').prop('disabled', true);
    					}
    					if (selectedValue == DISPOSE_PATTERN_07){
    						getElementById('hdayOpenFlag').next().addClass('layui-form-onswitch').trigger('change');
    	                    getElementById('hdayOpenFlag').next().find("em").text('是');
    	                    getElementById('hdayOpenFlag').prop('disabled', true);
    					}
    				}else{
    					if (pageModel === PAGE_MODEL_NEW){
    						getElementById('openMode').val(OPENMODE_01).trigger('change');
    					}
    	            	getElementById('openMode').prop('disabled', false);
    	            }
			    } else {
			        getElementById('div').hide();
			    }
			}
		} else {
			getElementById('div').hide();
		}
	}
	
	var openPlanInitAction = function(input_finprodId){
		Joyin.ajax(JoyinUtil.getRealPath('component/prod/BussFinProductOpenPlan/queryOpenPlanInfoByFinprodId'), {finprodId : input_finprodId}, function(data){
    		FinProductCommon.initPageDataAction(bussFinProductOpenPlanDto, data);
    	})
    	if (pageId == 'FinProductBuild002'||pageId === 'FinNetValueProductBuild002'){
    		openPlanFinprodId = input_finprodId;
    		quickFlag = true;
    		table.table ? table.reload() : table.load();
    	}
	}
	
	window.BussFinProductOpenPlan = {
		openPlanInitAction : openPlanInitAction
	}
	
    $(function() {
    	// 父画面开放类型联动事件
    	$('#' + operationType).change(parentOpenModeChange);
    	// 父画面处理模式联动事件
    	parentDealModeChange();
    	$('#' + dealMode).change(parentDealModeChange);
        // 画面开放模式联动事件
        openModeChange();
    	getElementById('openMode').change(openModeChange);
    	// 画面开放周期单位联动事件
    	getElementById('openCycleUnit').change(openCycleUnitChange);
    	// 画面周几联动事件
    	getElementById('dayOfWeek').change(weekChange);
    	// 画面推算方法联动事件
    	getElementById('calcMethod').change(calcMethodChange);
    	// 几号、第几工作日勾选标识
    	layui.form.on('radio(openDateFilter)', function(data){
    		if(data) {
                if(data.value === '0') {
                	getElementById('dayOfMonth').prop('disabled', false).prop('readonly', false);
                	getElementById('workdayOfMonth').val('').trigger('change').prop('disabled', true).prop('readonly', true);
                } else {
                	getElementById('workdayOfMonth').prop('disabled', false).prop('readonly', false);
                	getElementById('dayOfMonth').val('').trigger('change').prop('disabled', true).prop('readonly', true);
                }
            }
		});
    	// 预览按钮点击事件
		getElementById('btn_preview').click(checkInputParam);
    });
}());